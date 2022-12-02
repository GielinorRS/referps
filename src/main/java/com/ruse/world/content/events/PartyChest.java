package com.ruse.world.content.events;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.model.input.impl.EnterAmountToBank;
import com.ruse.util.Misc;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.CustomObjects;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.entity.impl.GroundItemManager;
import com.ruse.world.entity.impl.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PartyChest {

    public static ArrayList<Item> ITEMS = new ArrayList<>();
    public static boolean droppingItems = false;
    public static boolean staffOnly = true;

    public static long INTERVAL = TimeUnit.MINUTES.toMillis(60);

    private static long massMessageTimer = System.currentTimeMillis();
    private static int tick = 0;
    public static boolean updateRequired = false;

    public static void sequence() {
        if (EventManager.CURRENT_EVENT == EventManager.Events.PARTY_CHEST) {
            if (!droppingItems && (System.currentTimeMillis() - massMessageTimer >= INTERVAL)) {
                massMessageTimer = System.currentTimeMillis();
                droppingItems = true;

                DiscordMessager.sendGlobal("The Drop Party chest has started dropping balloons! ::event", Color.MAGENTA);
                World.sendBroadcastMessage("The Drop Party chest has started dropping balloons! ::event", 300);
                World.sendMessage("<img=862>@blu@[Event]<img=862> <col=660000>" + "The Drop Party chest has started dropping balloons! ::event");
            }

            if (droppingItems) {
                dropBalloons();
            }
            tick++;
        }
    }

    public static void startDropParty(Player player, int minutes, boolean staff) {
        if (!ITEMS.isEmpty()){
            player.sendMessage("You cannot override the current Drop Party chest until it is complete.");
            return;
        }
        massMessageTimer = System.currentTimeMillis();
        EventManager.CURRENT_EVENT = EventManager.Events.PARTY_CHEST;
        staffOnly = staff;
        INTERVAL = TimeUnit.MINUTES.toMillis(minutes);
        droppingItems = false;

        DiscordMessager.sendGlobal("The Drop Party chest will drop items in "+minutes+" minutes! ::event " + (staff ? "(Staff-hosted)" : ""), Color.MAGENTA);

        World.sendBroadcastMessage("The Drop Party chest will drop items in "+minutes+" minutes! ::event " + (staff ? "(Staff-hosted)" : ""), 300);
        World.sendMessage("<img=862>@blu@[Event]<img=862> <col=660000>" + "The Drop Party chest will drop items in "+minutes+" minutes! ::event " + (staff ? "(Staff-hosted)" : ""));
        if (!staff)
            World.sendMessage("<img=862>@blu@[Event]<img=862> <col=660000>The Drop Party chest is open for all players to make donations!");
    }

    public static String getTimeLeft() {
        if (droppingItems)
            return "Dropping Now";
        if (EventManager.CURRENT_EVENT != EventManager.Events.PARTY_CHEST)
            return "N/A";
        long ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer));
        return String.format("%dhr %dmin %ds", TimeUnit.MILLISECONDS.toHours(ms),
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    public static ArrayList<GameObject> objects = new ArrayList<>();
    public static ArrayList<Position> positions = new ArrayList<>();
    public static int[][] balloonMarked = new int[17][15];

    public static ArrayList<Item> balloonItems = new ArrayList<>();

    public static void dropBalloons() {
        if (tick % 3 == 0){

            int i = 0;

            while (objects.size() < 220 && i <= 10) {
                int x = Misc.getRandom(16);
                int y =Misc.getRandom(14);

                Position pos = new Position(1688 + x, 4251 +  y);
                if (balloonMarked[x][y] == 0) {
                    balloonMarked[x][y] = 1;

                    if (Misc.getRandom(10) == 0 && ITEMS.size() >= 1) {
                        balloonItems.add(ITEMS.get(0));
                        ITEMS.remove(0);
                        balloonMarked[x][y] = 2;
                    }

                    positions.add(pos);
                    GameObject gameObject = new GameObject(115 + Misc.getRandom(7), pos, 10, 0);
                    objects.add(gameObject);
                    CustomObjects.spawnGlobalObject(gameObject);
                    i++;
                }
            }

            if (ITEMS.isEmpty()){
                droppingItems = false;
                EventManager.CURRENT_EVENT = null;
                World.sendMessage("<img=862>@blu@[Event]<img=862> <col=660000>" + "The Drop Party chest has finished dropping balloons");
            }
        }
    }


    public static void openInterface(Player player) {

        refreshInterface(player);

        player.getPacketSender().sendItemContainer(5064, player.getInventory());
        player.getPacketSender().sendInterfaceSet(146000, 5063);
    }

    public static void refreshInterface(Player player) {

        player.getPacketSender().sendString(146006, "Chest Dropping items in: @whi@" + getTimeLeft());

        int totalItems = ITEMS.size() <= 32 ? 32 : ITEMS.size();

        for (int i = 0; i < totalItems; i++) {
            Item item = ITEMS.size() > i ? ITEMS.get(i) : new Item(-1, 1);
            player.getPacketSender().sendItemOnInterface(146051 + i, item.getId(), item.getAmount());
        }

        int scroll = 43 + totalItems / 8 * 40;

        if (totalItems % 32 == 0)
            scroll -= 40;

        player.getPacketSender().setScrollBar(146050, scroll);


        for (int i = 0; i < 8; i++) {
            Item item = player.getPartyChestCoffer().size() > i ? player.getPartyChestCoffer().get(i) : new Item(-1, 1);
            player.getPacketSender().sendItemOnInterface(146011 + i, item.getId(), item.getAmount());
        }
    }


    public static void interfaceAction(Player player, int action, Item item) {
        if (player.getInterfaceId() == 146000) {
            if (player.getGameMode().isIronman()) {
                player.sendMessage("Ironmen cannot add items to the Drop Party chest!");
                return;
            }
            if (!player.getInventory().contains(item.getId()))
                return;
            if (action == 5){
                player.setInputHandling(new EnterAmountToBank(item.getId(), item.getSlot()));
                player.getPacketSender().sendEnterAmountPrompt("How many would you like to add?");
                return;
            }
            addItem(player, item);
            player.getPacketSender().sendItemContainer(5064, player.getInventory());
            refreshInterface(player);
        }
    }

    public static void addItem(Player player, Item item) {
        if (staffOnly && !player.getRights().isStaff()){
            player.sendMessage("Only staff can contribute to the Drop Party chest this time.");
            return;
        }
        if (!item.tradeable()) {
            player.sendMessage("This item is untradeable");
            return;
        }

            if (player.getPartyChestCoffer().size() < 8) {
            if (item.getAmount() >= player.getInventory().getAmount(item.getId()))
                item.setAmount(player.getInventory().getAmount(item.getId()));

            if (item.getDefinition().isStackable()) {
                player.getPartyChestCoffer().add(item);
                player.getInventory().delete(item);
            } else {
                int loop = item.getAmount();
                if (loop >= 8 - player.getPartyChestCoffer().size())
                    loop =  8 - player.getPartyChestCoffer().size();

                for (int i = 0 ; i < loop ; i ++) {
                    player.getPartyChestCoffer().add(new Item(item.getId(), 1));
                    player.getInventory().delete(new Item(item.getId(), 1));
                }
            }
            updateRequired = true;
        } else {
            player.sendMessage("You must click Accept before adding more items.");
        }
    }


    public static void addItems(Player player) {

        if (ITEMS.size() >= 400)
            player.sendMessage("The Party Chest is at full capacity!");

        if (player.getPartyChestCoffer().size() > 0) {
            for (Item item : player.getPartyChestCoffer()) {
                ITEMS.add(item);

                PlayerLogs.logPartyChest(player.getUsername(), "Item: " + ItemDefinition.forId(item.getId()).getName() + ", ID: " + item.getId()+ ", Amount: " + item.getAmount() );

            }
            player.getPartyChestCoffer().clear();
        } else {
            player.sendMessage("You don't have any items to contribute.");
        }
        refreshInterface(player);
    }

    public static void removeItem(Player player, int index) {
        if (player.getPartyChestCoffer().size() > index) {
            Item item = player.getPartyChestCoffer().get(index);
            player.getPartyChestCoffer().remove(index);
            player.getInventory().add(item);
            player.getPacketSender().sendItemContainer(5064, player.getInventory());
            updateRequired = true;
        }
        refreshInterface(player);
    }

    public static void locationProcess(Player player) {
            if (player.getInterfaceId() == 146000){
            player.getPacketSender().sendString(146006, "Chest Dropping items in: @whi@" + getTimeLeft());
                refreshInterface(player);
        }

    }

    public static void popBalloon(Player player, GameObject gameObject) {
        player.getMovementQueue().walkStep(gameObject.getPosition().getX() - player.getPosition().getX(), gameObject.getPosition().getY() - player.getPosition().getY());

        TaskManager.submit(new Task(1, player, true) {
            int tick = 0;
            @Override
            protected void execute() {
                if (tick >= 5)
                    stop();
                if (player.getPosition().equals(gameObject.getPosition())) {
                    player.performAnimation(new Animation(794));

                    int x = gameObject.getPosition().getX() - 1688;
                    int y = gameObject.getPosition().getY() - 4251;

                    if (balloonMarked[x][y] == 2) {
                        GroundItemManager.spawnGroundItem(player, new GroundItem(balloonItems.get(0), player.getPosition().copy(),
                                "PARTY CHEST", player.getHostAddress(), true, 100,
                                true
                                , 100));
                    }
                    balloonMarked[x][y] = 0;

                    for (GameObject object : objects) {
                        if (object != null && object.getPosition().equals(gameObject.getPosition())) {
                            objects.remove(object);
                            break;
                        }
                    }

                    CustomObjects.deleteGlobalObject(gameObject);

                    GameObject poppedBalloon = new GameObject(gameObject.getId() + 8, gameObject.getPosition(), 10, 0);
                    CustomObjects.globalObjectRemovalTask(poppedBalloon, 1);

                    player.sendMessage("You pop the balloon.");
                    stop();
                }
            }
        });
    }


    public static void loadBalloons(Player player) {
        for (GameObject object : objects){
            if (object != null)
                player.getPacketSender().sendObject(object);
        }
    }


}
