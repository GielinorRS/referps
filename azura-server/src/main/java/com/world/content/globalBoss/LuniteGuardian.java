package com.world.content.globalBoss;

import com.ruse.model.Position;
import com.ruse.model.input.Input;
import com.ruse.util.Stopwatch;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.awt.*;

public class LuniteGuardian extends NPC {

    public static boolean alive = false;
    public static int NPC_ID = 9880;
    public static int AMOUNT_NEEDED = 100;
    public static int SACRIFICED = 0;

    public static final MassBossLocation LOCATION =
            new MassBossLocation(2996, 2768,1, "::Guardian", "::Guardian");

    private static LuniteGuardian current;

    public LuniteGuardian(Position position) {
        super(NPC_ID, position);
    }


    private static Stopwatch TIMER = new Stopwatch();

    public static void initialize() {
        if (SACRIFICED >= AMOUNT_NEEDED) {
            spawn();
        }
        if (TIMER.elapsed(10 * 60_000)){
            checkSpawn();
            TIMER.reset();
        }
    }

    public static void sacrificeTicketsInput(Player player) {
        player.getPacketSender().sendEnterAmountPrompt("Sacrifice High-Tier Tickets");
        player.setInputHandling(new LuniteGuardianSacrificeInput());
    }

    public static void contribute(Player player, int amount) {
        if (alive){
            player.sendMessage("@blu@The Lunite Guardian is already spawned.");
            return;
        }
        if (player.getInventory().getAmount(23456) >= 1){
            if (player.getInventory().getAmount(23456) <= amount)
                amount = player.getInventory().getAmount(23456);

            if (amount >= AMOUNT_NEEDED - SACRIFICED)
                amount = AMOUNT_NEEDED - SACRIFICED;

            SACRIFICED += amount;

            if (amount > 0)
            player.getInventory().delete(23456, amount);

            if (amount >= 5)
                World.sendMessage("<img=856><col=0f9be9>[Lunite Guardian]<img=856> <col=e77507>" + player.getUsername() + " has sacrificed "+amount+" High-Tier Tickets");
            checkSpawn();
        }else{
            player.sendMessage("@blu@You don't have any High-tier tickets in your inventory to sacrifice.");
        }
    }

    public static void checkSpawn() {
        if (SACRIFICED >= AMOUNT_NEEDED){
            spawn();
        }else{
            World.sendMessage("<img=856><col=0f9be9>[Lunite Guardian]<img=856> @blu@" + (AMOUNT_NEEDED - SACRIFICED) + "<col=e77507> more High-Tier Tickets left for Lunite Guardian spawn.");
        }
    }

    public static void spawn() {
        if (alive == true) {
            return;
        }
        if (!TIMER.elapsed(2000)){
            return;
        }
        SACRIFICED -= AMOUNT_NEEDED;
        MassBossLocation location = LOCATION;
        LuniteGuardian instance = new LuniteGuardian(location.copy());
        World.register(instance);
        setCurrent(instance);
        alive = true;
        DiscordMessager.sendGlobal("The Lunite Guardian has spawned at ::Guardian", Color.ORANGE);
        World.sendBroadcastMessage("<col=e77507>The Lunite Guardian has appeared <col=e95a0b>" + location.getLocation(), 100);
        World.sendMessage("<img=856><col=0f9be9>[Lunite Guardian]<img=856> <col=e77507>" + "Lunite Guardian has appeared <col=e95a0b>" + location.getLocation());
    }


    public static String getRemaining() {
        if (alive)
            return "Alive ::Guardian";

        return (AMOUNT_NEEDED - SACRIFICED)  + " tickets left";
    }

    public static void handleDrop(NPC npc) {
        TIMER.reset();
        alive = false;
        setCurrent(null);
    }


    public static LuniteGuardian getCurrent() {
        return current;
    }

    public static void setCurrent(LuniteGuardian current) {
        LuniteGuardian.current = current;
    }


    public static class LuniteGuardianSacrificeInput extends Input {

        @Override
        public void handleAmount(Player player, int amount) {
            LuniteGuardian.contribute(player, amount);
        }
    }

}