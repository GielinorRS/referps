package com.world.content.globalBoss;

import com.ruse.model.Position;
import com.ruse.model.input.Input;
import com.ruse.util.Stopwatch;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.awt.*;

public class HantoWarrior extends NPC {

    public static boolean alive = false;
    public static int NPC_ID = 9895;
    public static int AMOUNT_NEEDED = 250;
    public static int SACRIFICED = 0;

    public static final MassBossLocation LOCATION =
            new MassBossLocation(2847, 4575, 0, "::Hanto", "::Hanto");

    private static HantoWarrior current;

    public HantoWarrior(Position position) {
        super(NPC_ID, position);
    }

    private static Stopwatch TIMER = new Stopwatch();

    public static void initialize() {
        if (SACRIFICED >= AMOUNT_NEEDED) {
            spawn();
        }
        if (TIMER.elapsed(10 * 60_000)) {
            checkSpawn();
            TIMER.reset();
        }
    }

    public static void contribute(Player player, int amount) {
        if (alive) {
            player.sendMessage("@blu@The Hanto Warrior is already spawned.");
            return;
        }
        if (player.getInventory().getAmount(23569) >= 1) {
            if (player.getInventory().getAmount(23569) <= amount)
                amount = player.getInventory().getAmount(23569);

            if (amount >= AMOUNT_NEEDED - SACRIFICED)
                amount = AMOUNT_NEEDED - SACRIFICED;

            SACRIFICED += amount;

            if (amount > 0)
                player.getInventory().delete(23569, amount);

            World.sendMessage("<img=856><col=dd0b1f>[Hanto Warrior]<img=856> <col=9202FE>" + player.getUsername() + " has contributed " + amount + " Hanto Tokens");

            player.setHantoDR(player.getHantoDR() + amount);
            checkSpawn();

            player.sendMessage("@blu@You will now receive a " + player.getHantoDR() + "% Drop rate boost on your next Hanto kill.");
        } else {
            player.sendMessage("@blu@You don't have any Hanto Tokens in your inventory to sacrifice.");
        }
    }

    public static void sacrificeTicketsInput(Player player) {
        player.getPacketSender().sendEnterAmountPrompt("Sacrifice Hanto Tokens");
        player.setInputHandling(new HantoSacrificeInput());
    }

    public static void checkSpawn() {
        if (SACRIFICED >= AMOUNT_NEEDED) {
            spawn();
        } else {
            World.sendMessage("<img=856><col=dd0b1f>[Hanto Warrior]<img=856> @blu@" + (AMOUNT_NEEDED - SACRIFICED) + "<col=9202FE> more Hanto Tokens left for Hanto Warrior spawn.");
        }
    }

    public static void spawn() {
        if (alive == true) {
            return;
        }
        if (!TIMER.elapsed(2000)) {
            return;
        }
        SACRIFICED -= AMOUNT_NEEDED;
        MassBossLocation location = LOCATION;
        HantoWarrior instance = new HantoWarrior(location.copy());
        World.register(instance);
        setCurrent(instance);
        alive = true;

        DiscordMessager.sendGlobal("The Hanto Warrior has spawned at ::Hanto", Color.RED);
        World.sendBroadcastMessage("<col=9202FE>The Hanto Warrior has appeared <col=DD0B1F>" + location.getLocation(), 100);
        World.sendMessage("<img=856><col=dd0b1f>[Hanto Warrior]<img=856> <col=9202FE>" + "Hanto Warrior has appeared <col=DD0B1F>" + location.getLocation());
    }


    public static String getRemaining() {
        if (alive)
            return "Alive ::Hanto";

        return (AMOUNT_NEEDED - SACRIFICED) + " tokens left";
    }

    public static void handleDrop(NPC npc) {
        TIMER.reset();
        alive = false;
        setCurrent(null);
    }


    public static HantoWarrior getCurrent() {
        return current;
    }

    public static void setCurrent(HantoWarrior current) {
        HantoWarrior.current = current;
    }

    public static class HantoSacrificeInput extends Input {

        @Override
        public void handleAmount(Player player, int amount) {
            HantoWarrior.contribute(player, amount);
        }
    }

}