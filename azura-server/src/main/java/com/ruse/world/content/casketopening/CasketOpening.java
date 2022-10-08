package com.ruse.world.content.casketopening;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.casketopening.impl.*;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

public class CasketOpening {

    private final Player player;
    private final int INTERFACE_ID = 110000;
    private boolean canCasketOpening = true;
    private Box SlotPrize;
    private Caskets currentCasket;

    public CasketOpening(Player player) {
        this.player = player;
    }


    public static Box getLoot(Box[] loot) {
        HashMap<Double, ArrayList<Box>> dropRates = new HashMap<>();
        ArrayList<Box> potentialDrops = new ArrayList<>();

        for (Box drop : loot) {
            if (drop == null)
                continue;
            double divisor = drop.getRate();
            if (!dropRates.containsKey(divisor)) {
                ArrayList<Box> items = new ArrayList<>();
                items.add(drop);
                dropRates.put(divisor, items);
            } else {
                dropRates.get(divisor).add(drop);
            }
        }
        for (double dropRate : dropRates.keySet()) {
            double rate = dropRate * 1000;
            if (Misc.getRandom(100000) <= rate) {
                potentialDrops.add(dropRates.get(dropRate).get(Misc.getRandom(dropRates.get(dropRate).size() - 1)));
            }
        }

        if (potentialDrops.size() > 0) {
            return potentialDrops.get(Misc.getRandom((potentialDrops.size() - 1)));
        } else {
            return loot[Misc.getRandom(1)];
        }
    }

    public static Box getLoot1(Box[] loot) {
        HashMap<Double, ArrayList<Box>> dropRates = new HashMap<>();
        ArrayList<Box> potentialDrops = new ArrayList<>();

        for (Box drop : loot) {
            if (drop == null)
                continue;
            double divisor = drop.getRate();
            if (!dropRates.containsKey(divisor)) {
                ArrayList<Box> items = new ArrayList<>();
                items.add(drop);
                dropRates.put(divisor, items);
            } else {
                dropRates.get(divisor).add(drop);
            }
        }
        for (double dropRate : dropRates.keySet()) {
            double rate = dropRate * 1000;
            if (Misc.getRandom(100000) <= rate) {
                potentialDrops.add(dropRates.get(dropRate).get(Misc.getRandom(dropRates.get(dropRate).size() - 1)));
            }
        }

        if (potentialDrops.size() > 0) {
            Box loots = potentialDrops.get(Misc.getRandom((potentialDrops.size() - 1)));
            if (loots.getId() == 23883 && Misc.getRandom(1) == 0) {
                return getLoot1(loot);
            }
            return loots;
        } else {
            return getLoot1(loot);
            // return loot[Misc.getRandom((loot.length - 1))];
        }
    }

    public boolean hasItems() {
        if (!player.getInventory().contains(getCurrentCasket().getItemID())) {
            player.sendMessage("You need a " + ItemDefinition.forId(getCurrentCasket().getItemID()).getName() + " to do this.");
            return false;
        }
        return true;
    }

    public boolean removeItems() {
        if (player.getInventory().getAmount(getCurrentCasket().getItemID()) >= 1) {
            player.getInventory().delete(getCurrentCasket().getItemID(), 1, false);
        }
        return false;
    }

    public void spin() {
        if (getCurrentCasket() == null) {
            return;
        }

        if (!canCasketOpening) {
            player.sendMessage("Please finish your current spin.");
            return;
        }
        if (hasItems()) {
            if (player.getInventory().getFreeSlots() == 0) {
                player.getPacketSender().sendMessage("You don't have enough free inventory space.");
                return;
            }
            removeItems();
            player.setSpinning(true);
            player.getMovementQueue().setLockMovement(true);
            player.sendMessage(":resetCasket");
            player.sendMessage(":spinCasket");
            process();
        }
    }

    public void quickSpin() {
        if (getCurrentCasket() == null) {
            return;
        }
        if (!canCasketOpening) {
            player.sendMessage("Please finish your current spin.");
            return;
        }
        if (hasItems()) {
            if (player.getInventory().getFreeSlots() == 0) {
                player.getPacketSender().sendMessage("You don't have enough free inventory space.");
                return;
            }
            removeItems();
            player.sendMessage(":resetCasket");
            processQuick();
        }
    }

    public void openAll(int amount) {
        if (getCurrentCasket() == null) {
            return;
        }
        if (!canCasketOpening) {
            player.sendMessage("Please finish your current spin.");
            return;
        }
        for (int i = 0; i < amount; i++) {
            if (hasItems()) {
                if (player.getInventory().getFreeSlots() == 0) {
                    player.getPacketSender().sendMessage("You don't have enough free inventory space.");
                    player.getInventory().refreshItems();
                    return;
                }
                removeItems();
                Box[] loot = getCurrentCasket().getLoot();
                SlotPrize = getLoot1(loot);
                reward(SlotPrize.isAnnounce());
            } else {
                player.getInventory().refreshItems();
                return;
            }
        }
        player.getInventory().refreshItems();
    }

    public void process() {
        if (getCurrentCasket().getRequiredItem() != -1) {
            if (!player.getInventory().contains(getCurrentCasket().getRequiredItem())) {
                player.sendMessage("@blu@You need x1 " + ItemDefinition.forId(getCurrentCasket().getRequiredItem()).getName() + " to open this.");
                return;
            }
            player.getInventory().delete(getCurrentCasket().getRequiredItem(), 1);
        }
        SlotPrize = null;
        canCasketOpening = false;
        Box[] loot = getCurrentCasket().getLoot();

        SlotPrize = getLoot1(loot);

        boolean announce = SlotPrize.isAnnounce();

        for (int i = 0; i < 28; i++) {
            Box NOT_PRIZE = getLoot1(loot);
            sendItem(i, 23, SlotPrize.getId(), SlotPrize.getMax(), NOT_PRIZE.getId(), NOT_PRIZE.getMax(), 110501);
        }

        final boolean announceLoot = announce;
        TaskManager.submit(new Task(8, player, false) {

            @Override
            public void execute() {
                reward(announceLoot);
                player.getInventory().refreshItems();
                player.setSpinning(false);
                player.getMovementQueue().setLockMovement(false);
                stop();
            }
        });
    }

    public void processQuick() {
        if (getCurrentCasket().getRequiredItem() != -1) {
            if (!player.getInventory().contains(getCurrentCasket().getRequiredItem())) {
                player.sendMessage("@blu@You need x1 " + ItemDefinition.forId(getCurrentCasket().getRequiredItem()).getName() + " to open this.");
                return;
            }
            player.getInventory().delete(getCurrentCasket().getRequiredItem(), 1);
        }
        SlotPrize = null;
        canCasketOpening = false;
        Box[] loot = getCurrentCasket().getLoot();

        SlotPrize = getLoot1(loot);


        //  if (player.getUsername().equalsIgnoreCase("don draper")){
        //        SlotPrize = WeaponCasket.lootGolden[13];
        //   }
        boolean announce = SlotPrize.isAnnounce();
        for (int i = 0; i < 7; i++) {
            Box NOT_PRIZE = getLoot1(loot);
            sendItem(i, 3, SlotPrize.getId(), SlotPrize.getMax(), NOT_PRIZE.getId(), NOT_PRIZE.getMax(), 110501);
        }
        // player.getBank(0).add(new Item(SlotPrize.getId(), SlotPrize.getMax()), false);
        //   canCasketOpening = true;

        reward(announce);
        player.getInventory().refreshItems();
        player.setSpinning(false);
    }

    public void sendItem(int i, int prizeSlot, int PRIZE_ID, int prizeamount, int NOT_PRIZE, int amount,
                         int ITEM_FRAME) {
        if (i == prizeSlot) {
            player.sendMessage("casketopening##" + ITEM_FRAME + "##" + PRIZE_ID + "##" + prizeamount + "##" + i + "##");
        } else {
            player.sendMessage("casketopening##" + ITEM_FRAME + "##" + NOT_PRIZE + "##" + amount + "##" + i + "##");
        }
    }

    public void reward(boolean announce) {
        if (SlotPrize == null) {
            return;
        }

        if (currentCasket == Caskets.DIVINE_CASE
                || currentCasket == Caskets.MONEY_CASE
                || currentCasket == Caskets.ANGELIC_CASE)
            player.getDailyTaskManager().submitProgressToIdentifier(39, 1);

        if (currentCasket == Caskets.SUMMER || currentCasket == Caskets.AUTUMN)
            player.getDailyTaskManager().submitProgressToIdentifier(43, 1);

        if (currentCasket.ordinal() <= 4)
        player.getDailyTaskManager().submitProgressToIdentifier(28, 1);

        player.getDailyTaskManager().submitProgressToIdentifier(5, 1);
        player.getInventory().add(new Item(SlotPrize.getId(), SlotPrize.getMax()), false);
        player.sendMessage(
                "@red@You won x" + SlotPrize.getMax() + " " + ItemDefinition.forId(SlotPrize.getId()).getName());

        player.getCollectionLogManager().addItem(getCurrentCasket().getItemID(), new Item(SlotPrize.getId(), SlotPrize.getMax()));

        if (announce) {
            String message = "@red@" + player.getUsername() + " <col=ff812f>has just received @red@"
                    + (SlotPrize.getMax() > 1 ? "x" + SlotPrize.getMax() : "") + " "
                    + ItemDefinition.forId(SlotPrize.getId()).getName() + "<col=ff812f> from a @red@" +
                    ItemDefinition.forId(getCurrentCasket().getItemID()).getName() + "!";
            World.sendNewsMessage(message);
        }

        canCasketOpening = true;
    }

    public void openInterface() {
        if (getCurrentCasket().getRequiredItem() != -1) {
            if (!player.getInventory().contains(getCurrentCasket().getRequiredItem())) {
                player.sendMessage("@blu@You need x1 " + ItemDefinition.forId(getCurrentCasket().getRequiredItem()).getName() + " to open this.");
                return;
            }
        }
        player.sendMessage(":resetCasket");

        player.getPacketSender().sendItemOnInterface(110009, 13759, 1);
        player.getPacketSender().sendItemOnInterface(110010, 13758, 1);

        Box[] loot = getCurrentCasket().getLoot();

        int length = loot.length;
        if (length >= 160)
            length = 160;
        if (length <= 16)
            length = 16;

        length += 8 - (length % 8);

        for (int i = 0; i < length; i++) {
            if (loot.length > i)
                player.getPacketSender().sendItemOnInterface(110101 + i, loot[i].getId(), loot[i].getMax());
            else
                player.getPacketSender().sendItemOnInterface(110101 + i, -1, 0);
        }

        for (int i = 0; i < length; i++) {
            if (loot.length > i)
                player.getPacketSender().sendString(110261 + i, "1/" + getRate(loot[i].getRate()));
            else
                player.getPacketSender().sendString(110261 + i, "");
        }
        int scroll = 9 + ((loot.length / 8) + 1) * 55;
        if (scroll <= 165)
            scroll = 165;
        player.getPacketSender().setScrollBar(110100, scroll);


        player.getPA().sendInterface(INTERFACE_ID);
    }

    public int getRate(double rate) {
        if (rate < 100 && rate >= 50)
            return 2;
        int result = (int) (100 / rate);
        return result;
    }


    public Caskets getCurrentCasket() {
        return currentCasket;
    }

    public void setCurrentCasket(Caskets currentCasket) {
        this.currentCasket = currentCasket;
    }

    @Getter
    @AllArgsConstructor
    public enum Caskets {

        SEPTEMBER(15002, RubyCasket.loot),
        SEPTEMBER2(15003, SilverCasket.loot),
        SEPTEMBER3(15004, DiamondCasket.loot),
        SEPTEMBER4(23210, OnyxCasket.loot),
        ZENYTE(23314, ZenyteCasket.loot),
        RAIDS2(18404, Raids2Box.loot),
        SLAYERBOXU(23479, SlayerBoxU.loot),
        SPRING(23708, SpringBox.loot),
        SUMMER(23826, SummerBox.loot),
        AUTUMN(23882, AutumnBox.loot),

        EASTER_EGG(11027, EasterEgg.loot),


        DIVINE_CASE(23782, 23780, DivineCase.loot),
        ANGELIC_CASE(23776, 23780, AngelicCase.loot),
        MONEY_CASE(23812, 23780, MoneyCase.loot),
        ;
        private int itemID;
        private int requiredItem = -1;
        private Box[] loot;

        Caskets(int itemID, Box[] loot) {
            this.itemID = itemID;
            this.loot = loot;
        }


        public Box[] getLoot() {


            if (this == EASTER_EGG)
                return EasterEgg.loot;
            if (this == DIVINE_CASE)
                return DivineCase.loot;
            if (this == ANGELIC_CASE)
                return AngelicCase.loot;
            if (this == MONEY_CASE)
                return MoneyCase.loot;
            return loot;
        }

    }

}