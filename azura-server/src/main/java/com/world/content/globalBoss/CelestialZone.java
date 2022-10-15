package com.world.content.globalBoss;

import com.ruse.GameSettings;
import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.CustomObjects;
import com.ruse.world.content.EffectTimer;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.skill.impl.smithing.SmithingData;
import com.ruse.world.content.titles.Titles;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.transportation.TeleportType;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CelestialZone {

    public static boolean gameActive = true;
    public static long massMessageTimer = System.currentTimeMillis();
    public static long INTERVAL = TimeUnit.MINUTES.toMillis(180);//180
    public static int gameTicks = 1000;

    public static String getTimeLeft() {
        long ms = ((INTERVAL) - (System.currentTimeMillis() - massMessageTimer));
        return String.format("%dhrs %dmin %ds",
                TimeUnit.MILLISECONDS.toHours(ms) - TimeUnit.HOURS.toHours(TimeUnit.MILLISECONDS.toDays(ms)),
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    public static void initialize() {
      /*  if ((System.currentTimeMillis() - massMessageTimer >= (INTERVAL))) {
            spawn();
            INTERVAL = TimeUnit.MINUTES.toMillis(180);//180
        }
        if (gameActive)
            processGame();*/
    }

    public static void processGame() {
       /* if (gameTicks == 0) {
            World.sendMessage("<img=856>@blu@[Celestial]<img=856> <col=29a7cc>The Celestial Portal has closed!");
            gameActive = false;
        }else{
            gameTicks --;
            if (gameTicks == 900 || gameTicks == 500){
                sendMessage("<col=29a7cc>The Celestial Zone will close in "+(gameTicks / 100)+" minutes.");
            }
            if (gameTicks == 100){
                sendMessage("<col=29a7cc>The Celestial Zone will close in one minute.");
            }
            if (gameTicks == 50){
                sendMessage("<col=29a7cc>The Celestial Zone will close in 30 seconds.");
            }
        }*/
    }

    public static void sendMessage(String message) {
        for (Player p : World.getPlayers()) {
            if (p == null)
                continue;
            p.setSoulInPouch(0);
            if (p.getLocation().equals(Locations.Location.CELESTIAL_ZONE)) {
                p.sendMessage(message);
            }
        }
    }


    public static void spawn() {
        gameActive = true;
       /* DiscordMessager.sendGlobal("The Celestial Zone Portal has opened for 10 minutes (Celestials only)", Color.cyan);
        World.sendMessage("<img=856>@blu@[Celestial]<img=856> <col=29a7cc>The Celestial Zone Portal has opened for 10 minutes (Celestials only)");

        massMessageTimer = System.currentTimeMillis();
        gameTicks = 1000;
        for (Player p : World.getPlayers()) {
            if (p == null)
                continue;
            if (p.getLocation() == Locations.Location.CELESTIAL_ZONE)
                p.getPA().sendEffectTimerSeconds(600, EffectTimer.CELESTIAL_ZONE);
        }*/
    }


    public static void celestialPortal(Player player) {
        if (player.isCelestial()) {
            if (gameActive) {
                player.sendMessage("@mag@You have entered the Celestial Zone!");
                TeleportHandler.teleportPlayer(player, new Position(2528, 4574, 0), TeleportType.NORMAL);
            } else {
                DialogueManager.sendStatement(player, "The Celestial Zone Portal will open in " + CelestialZone.getTimeLeft());
            }
        } else {
            DialogueManager.sendStatement(player, "You must be a Celestial to do this.");
        }
    }

    public static void movePlayer(Player player) {
        player.moveTo(new Position(2652, 4004));
        feedFire(player);
    }

    public static void pickMushroom(Player player, GameObject gameObject) {
        if (!gameActive) {
            player.getPacketSender().sendMessage("The Celestial Zone is not open.");
            return;
        }
            if (!player.getClickDelay().elapsed(1200))
            return;
        if (player.getInventory().isFull()) {
            player.getPacketSender().sendMessage("You don't have enough free inventory space.");
            return;
        }
        player.performAnimation(new Animation(881));
        player.getInventory().add(23750, 1);
        player.getPacketSender().sendMessage("You pick some Celestial mushroom.");

        CustomObjects.globalObjectRespawnTask(new GameObject(12005, gameObject.getPosition(), gameObject.getType(), gameObject.getFace()),
                gameObject, 5);

        player.getClickDelay().reset();
    }


    public static void smeltBar(Player player) {
        if (!gameActive) {
            player.getPacketSender().sendMessage("The Celestial Zone is not open.");
            return;
        }
        if (!player.getInventory().contains(23748)) {
            player.sendMessage("You need Celestial Ores to do this.");
            return;
        }
        int amount = player.getInventory().getAmount(23748);

        player.getSkillManager().stopSkilling();
        player.getPacketSender().sendInterfaceRemoval();
        player.performAnimation(new Animation(896));
        player.setCurrentTask(new Task(2, player, true) {
            int amountMade = 0;

            @Override
            public void execute() {
                if (!player.getInventory().contains(23748)) {
                    stop();
                    return;
                }
                player.performAnimation(new Animation(896));

                player.getInventory().delete(23748, 1);
                player.getInventory().add(23749, 1);

                amountMade++;
                if (amountMade >= amount)
                    stop();
            }
        });
        TaskManager.submit(player.getCurrentTask());
    }

    public static void useItem(Player player, int item) {
        if (item == 23351 || item == 23352 || item == 23353 || item == 7995 || item == 22111) {
            if (player.isCelestial()) {
                player.sendMessage("@red@You are already a Celestial!");
            } else {
                if (player.getAssassinsGuild().getTier() == 4) {
                    if (player.getInventory().contains(item)) {
                        player.getInventory().delete(item, 1);
                        if (player.isSacrificedCelestialItem()) {
                            player.sendMessage("<col=29a7cc>You sacrificed 2/2 Owner items to the Celestial being.");
                            World.sendMessage("@blu@<img=856>[Celestial]<img=856><col=29a7cc>" + player.getUsername() + " is now a Celestial!");

                            DiscordMessager.sendWebhook(player.getUsername() + " - CELESTIAL " , Color.magenta,
                                    "https://discord.com/api/webhooks/959301658939121704/lbuXqGJHSCQkc2F53hcYeyGXJjME8f0PpbrPoc2-1xbtrz1YBAZhs7IOTYVUNoWfkGP0");

                            player.getTitlesManager().unlock(Titles.CELESTIAL);
                            player.setCelestial(true);
                            player.getUpdateFlag().flag(Flag.APPEARANCE);
                        } else {
                            player.setSacrificedCelestialItem(true);
                            player.sendMessage("<col=29a7cc>You sacrificed 1/2 Owner items to the Celestial being.");
                        }
                    }
                } else {
                    player.sendMessage("@red@You must be an Assassin Master to sacrifice items to the Celestial being.");
                }
            }
        }
    }

    public static void feedFire(Player player) {
        if (!gameActive) {
            player.getPacketSender().sendMessage("The Celestial Zone is not open.");
            return;
        }
        for (CelestialItems celestialItems : CelestialItems.values()) {
            if (player.getInventory().contains(celestialItems.getItemId())) {
                double amount = player.getInventory().getAmount(celestialItems.getItemId()) * celestialItems.getEnergy()  ;
                amount *= 1.1;

                double boost = 1D;
                if (player.getEquipment().contains(23834)) {
                    boost += 1.2D;
                }else {
                    for (int i = 23729; i <= 23734; i++) {
                        if (player.getEquipment().contains(i)) {
                            boost += 0.2D;
                        }
                    }
                }
                amount *= boost;

                if (amount <= 1)
                    amount = 1;

                System.out.println("amount: " + amount);

                player.sendMessage("<col=29a7cc>You feed the fire x" + player.getInventory().getAmount(celestialItems.getItemId()) + " "
                        + ItemDefinition.forId(celestialItems.getItemId()).getName() + " and receive " + amount + " Celestial energy.");

                player.getInventory().delete(celestialItems.getItemId(), player.getInventory().getAmount(celestialItems.getItemId()));
                player.getInventory().add(23736, (int) amount);
            }
        }
    }

    @AllArgsConstructor
    @Getter
    public enum CelestialItems {
        SCALES(23751, 1.5),
        MUSHROOMS(23750, 1.2),
        BARS(23749, 2.2),
        ORES(23748, 1.0),

        ;
        private int itemId;
        private double energy;
    }

}