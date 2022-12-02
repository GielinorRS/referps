package com.ruse.world.content.cardpacks;

import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.util.Stopwatch;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.ruse.world.content.cardpacks.CardPack.CardPackRarity.*;

public class CardPack {

    private Player player;
    private int itemId;
    private CardPackReward[] rewards;
    private boolean opening;
    private Stopwatch stopwatch = new Stopwatch();

    public CardPack(Player player) {
        this.player = player;
    }

    public boolean handleButton(int buttonId) {
        if (buttonId == 141506)
            openAgain();
        if (buttonId == 141006) {
            openAgain();
        }
        if (buttonId == 141007) {
            openPack();
        }
        return false;
    }

    public void openAgain() {
        if (opening)
            return;
        if (!stopwatch.elapsed(200)) {
            return;
        }
        if (itemId > 0) {
            if (!player.getInventory().contains(itemId)) {
                player.sendMessage("You do not have a " + ItemDefinition.forId(itemId).getName());
                return;
            }
            opening = true;
            player.getPacketSender().sendInterface(141000);
            player.getPacketSender().sendInterfaceItemModel(25359, itemId);

            int interfaceId = 141007;
            for (int i = 0; i < 6; i++) {
                player.getPacketSender().sendSpriteChange(interfaceId++, 1563);
                player.getPacketSender().sendItemOnInterface(interfaceId++, -1, 1);
            }
        }
    }

    public void handleCardClick(int item) {
        if (opening)
            return;
        this.itemId = item;
        if (item > 0) {
            player.getPacketSender().sendInterface(141500);
            player.getPacketSender().sendInterfaceItemModel(25358, itemId);
        }
    }

    public void openPack() {
        opening = false;
        if (player.getInventory().contains(itemId)) {
            player.getInventory().delete(itemId, 1);
            player.getPacketSender().sendInterface(141000);

            int interfaceId = 141007;
            boolean noSpace = false;
            for (int i = 0; i < 6; i++) {
                CardPackReward PRIZE = getLoot(loot);
                player.getPacketSender().sendSpriteChange(interfaceId++, PRIZE.getRarity().getSpriteID());
                player.getPacketSender().sendItemOnInterface(interfaceId++, PRIZE.getId(), PRIZE.getAmount());
                if (PRIZE.getRarity() != SILVER && CardPackRates.forId(itemId).getRates()[PRIZE.getRarity().ordinal()] <= 10) {
                    String message = "<col=a200ff>" + player.getUsername() + " <col=ff812f>has received<col=a200ff>"
                            + (PRIZE.getAmount() > 1 ? " x" + PRIZE.getAmount() : "") + " "
                            + ItemDefinition.forId(PRIZE.getId()).getName() + "<col=ff812f> from a <col=0084ff>" +
                            ItemDefinition.forId(itemId).getName() + "!";
                    World.sendNewsMessage(message);
                }
                if ((!ItemDefinition.forId(PRIZE.getId()).isStackable()
                        && player.getInventory().getFreeSlots() == 0)
                        || (ItemDefinition.forId(PRIZE.getId()).isStackable() && !player.getInventory().contains(PRIZE.getId())
                        && player.getInventory().getFreeSlots() == 0)) {
                    noSpace = true;
                    player.depositItemBank(new Item(PRIZE.getId(), PRIZE.getAmount()), false);
                } else {
                    player.getInventory().add(new Item(PRIZE.getId(), PRIZE.getAmount()), false);
                }
            }
            player.getInventory().refreshItems();
            if (noSpace)
                player.sendMessage("Some items were deposited into your bank.");
        }
        stopwatch.reset();
    }


    public CardPackReward getLoot(CardPackReward[] loot) {
        HashMap<Double, ArrayList<CardPackReward>> dropRates = new HashMap<>();
        ArrayList<CardPackReward> potentialDrops = new ArrayList<>();

        for (CardPackReward drop : loot) {
            if (drop == null)
                continue;
            double divisor = CardPackRates.forId(itemId).getRates()[drop.getRarity().ordinal()];
            if (!dropRates.containsKey(divisor)) {
                ArrayList<CardPackReward> items = new ArrayList<>();
                items.add(drop);
                dropRates.put(divisor, items);
            } else {
                dropRates.get(divisor).add(drop);
            }
        }

        CardPackRarity rarity = getDropTable(Misc.getRandomDouble() * 100);

        for (double dropRate : dropRates.keySet()) {
            if (dropRate == CardPackRates.forId(itemId).getRates()[rarity.ordinal()]) {
                potentialDrops.add(dropRates.get(dropRate).get(Misc.getRandom(dropRates.get(dropRate).size() - 1)));
            }
        }

        if (potentialDrops.size() > 0) {
            return potentialDrops.get(Misc.getRandom((potentialDrops.size() - 1)));
        } else {
            return getLoot(loot);
        }
    }


    public CardPackRarity getDropTable(double rate) {
        double[] rates = new double[CardPackRarity.values().length];
        for (CardPackRarity r : CardPackRarity.values()) {
            rates[r.ordinal()] = CardPackRates.forId(itemId).getRates()[r.ordinal()]
                    + (r.ordinal() > 0 ? rates[r.ordinal() - 1] : 0);
        }
        for (int i = 0; i < rates.length; i++) {
            if (rate <= rates[i]) {
                return CardPackRarity.values()[i];
            }
        }
        return BRONZE;
    }


    @Getter
    @AllArgsConstructor
    public static class CardPackReward {
        private int id;
        private int amount;
        private CardPackRarity rarity;
    }

    @Getter
    @AllArgsConstructor
    public enum CardPackRarity {
        LEGENDARY(1568),//150m+
        DIAMOND(1567),//50-150m
        GOLD(1566),//20-30mm
        SILVER(1565),//5-10m
        BRONZE(1564),//1m
        ;

        private int spriteID;
    }

    @Getter
    @AllArgsConstructor
    public enum CardPackRates {
        // LEGENDARY(23479, PackRates.diamondRates),//150m+
        DIAMOND(23478, PackRates.diamondRates),//50-150m
        GOLD(23477, PackRates.goldRates),//20-30mm
        SILVER(23476, PackRates.silverRates),//5-10m
        BRONZE(23475, PackRates.bronzeRates),//1m
        ;
        private int itemId;
        private double[] rates;

        public static CardPackRates forId(int id) {
            for (CardPackRates data : CardPackRates.values()) {
                if (data.getItemId() == id) {
                    return data;
                }
            }
            return null;
        }
    }

    public static ArrayList<CardPackReward> getRewards(int itemId) {
        ArrayList<CardPackReward> rewards = new ArrayList<>();
        ArrayList<CardPackRarity> rarities = new ArrayList<>();
        for (int i = 0; i < CardPackRates.forId(itemId).getRates().length; i++) {
            if (CardPackRates.forId(itemId).getRates()[i] > 0)
                rarities.add(CardPackRarity.values()[i]);
        }

        for (CardPackReward packReward : loot) {
            if (rarities.contains(packReward.getRarity())) {
                rewards.add(packReward);
            }
        }

        return rewards;

    }

    public static CardPackReward[] loot = { //

            new CardPackReward(4446, 1, BRONZE), // Collector ring
            new CardPackReward(19886, 1, BRONZE), // Collector necklace
            new CardPackReward(1486, 1, BRONZE), // Creeper Cape
            new CardPackReward(20489, 1, BRONZE), // Launch Chest
            new CardPackReward(20489, 2, BRONZE), // Launch Chest
            new CardPackReward(10946, 10, BRONZE), // $1 Scroll
            new CardPackReward(10946, 7, BRONZE), // $1 Scroll
            new CardPackReward(10946, 5, BRONZE), // $1 Scroll
            new CardPackReward(15288, 20, BRONZE), // Upgrade Token pack (100k)
            new CardPackReward(15288, 15, BRONZE), // Upgrade Token pack (100k)
            new CardPackReward(15288, 10, BRONZE), // Upgrade Token pack (100k)
            new CardPackReward(5022, 1000000, BRONZE), // PVM ticket
            new CardPackReward(5022, 750000, BRONZE), // PVM ticket
            new CardPackReward(6769, 1, BRONZE), // $5 Bond
            new CardPackReward(6769, 2, BRONZE), // $5 Bond
            new CardPackReward(4278, 200, BRONZE), // Instance Token
            new CardPackReward(4278, 250, BRONZE), // Instance Token
            new CardPackReward(4278, 300, BRONZE), // Instance Token
            new CardPackReward(15359, 3, BRONZE), // Double Damage Scroll (30min)
            new CardPackReward(15359, 5, BRONZE), // Double Damage Scroll (30min)
            new CardPackReward(15358, 3, BRONZE), // 100% DR Scroll (30min)
            new CardPackReward(15358, 5, BRONZE), // 100% DR Scroll (30min)
            new CardPackReward(19115, 200, BRONZE), // Extreme Mystery Box
            new CardPackReward(19114, 100, BRONZE), // Grand Mystery Box
            new CardPackReward(19114, 50, BRONZE), // Grand Mystery Box
            new CardPackReward(23456, 3, BRONZE), // High-Tier Ticket
            new CardPackReward(15290, 200, BRONZE), // Upgrade Token pack (5k)
            new CardPackReward(15290, 250, BRONZE), // Upgrade Token pack (5k)
            new CardPackReward(15290, 300, BRONZE), // Upgrade Token pack (5k)
            new CardPackReward(15289, 40, BRONZE), // Upgrade Token pack (25k)
            new CardPackReward(15289, 50, BRONZE), // Upgrade Token pack (25k)
            new CardPackReward(15289, 75, BRONZE), // Upgrade Token pack (25k)
            new CardPackReward(23046, 1, BRONZE), // Aura (T3)
            new CardPackReward(20488, 15, BRONZE), // OVERPOWERED CHEST
            new CardPackReward(20488, 10, BRONZE), // OVERPOWERED CHEST
            new CardPackReward(3739, 1, BRONZE), // Azure AoE Staff
            new CardPackReward(3738, 1, BRONZE), // Herbal AoE Bow
            new CardPackReward(3737, 1, BRONZE), // Scarlet AoE Sword
            new CardPackReward(8087, 1, BRONZE), // Dragon Rider lance
            new CardPackReward(8088, 1, BRONZE), // Judicator crossbow
            new CardPackReward(8089, 1, BRONZE), // Purifier staff
            new CardPackReward(11137, 200, BRONZE), // Antique lamp
            new CardPackReward(19888, 1, BRONZE), // Collector necklace II
            new CardPackReward(18823, 1, BRONZE), // Collector ring II
            new CardPackReward(23211, 30, BRONZE), // Instance Token (u)
            new CardPackReward(23211, 50, BRONZE), // Instance Token (u)
            new CardPackReward(23514, 1000, BRONZE), // Pvm Ticket pack (1k)
            new CardPackReward(23514, 750, BRONZE), // Pvm Ticket pack (1k)
            new CardPackReward(23515, 250, BRONZE), // Pvm Ticket pack (5k)
            new CardPackReward(23515, 200, BRONZE), // Pvm Ticket pack (5k)
            new CardPackReward(23516, 75, BRONZE), // Pvm Ticket pack (10k)
            new CardPackReward(23516, 100, BRONZE), // Pvm Ticket pack (10k)
            new CardPackReward(21218, 30, BRONZE), // Invention xp lamp
            new CardPackReward(21218, 25, BRONZE), // Invention xp lamp
            new CardPackReward(22006, 25, BRONZE), // Deathtouch Darts
            new CardPackReward(22006, 50, BRONZE), // Deathtouch Darts


            new CardPackReward(20489, 10, SILVER), // Launch Chest
            new CardPackReward(10946, 30, SILVER), // $1 Scroll
            new CardPackReward(10946, 20, SILVER), // $1 Scroll
            new CardPackReward(15288, 75, SILVER), // Upgrade Token pack (100k)
            new CardPackReward(15288, 30, SILVER), // Upgrade Token pack (100k)
            new CardPackReward(15288, 50, SILVER), // Upgrade Token pack (100k)
            new CardPackReward(5022, 2500000, SILVER), // PVM ticket
            new CardPackReward(5022, 4000000, SILVER), // PVM ticket
            new CardPackReward(6769, 4, SILVER), // $5 Bond
            new CardPackReward(6769, 6, SILVER), // $5 Bond
            new CardPackReward(4278, 750, SILVER), // Instance Token
            new CardPackReward(4278, 1000, SILVER), // Instance Token
            new CardPackReward(15359, 10, SILVER), // Double Damage Scroll (30min)
            new CardPackReward(15359, 20, SILVER), // Double Damage Scroll (30min)
            new CardPackReward(15358, 10, SILVER), // 100% DR Scroll (30min)
            new CardPackReward(15358, 20, SILVER), // 100% DR Scroll (30min)
            new CardPackReward(19114, 500, SILVER), // Grand Mystery Box
            new CardPackReward(19114, 250, SILVER), // Grand Mystery Box
            new CardPackReward(23456, 10, SILVER), // High-Tier Ticket
            new CardPackReward(23456, 20, SILVER), // High-Tier Ticket
            new CardPackReward(15290, 1000, SILVER), // Upgrade Token pack (5k)
            new CardPackReward(15290, 1250, SILVER), // Upgrade Token pack (5k)
            new CardPackReward(15290, 1500, SILVER), // Upgrade Token pack (5k)
            new CardPackReward(15289, 160, SILVER), // Upgrade Token pack (25k)
            new CardPackReward(15289, 200, SILVER), // Upgrade Token pack (25k)
            new CardPackReward(15289, 250, SILVER), // Upgrade Token pack (25k)
            new CardPackReward(23048, 1, SILVER), // Aura (T5)
            new CardPackReward(23049, 1, SILVER), // Aura (T6)
            new CardPackReward(20488, 50, SILVER), // OVERPOWERED CHEST
            new CardPackReward(20488, 75, SILVER), // OVERPOWERED CHEST
            new CardPackReward(11137, 1000, SILVER), // Antique lamp
            new CardPackReward(23211, 150, SILVER), // Instance Token (u)
            new CardPackReward(23211, 250, SILVER), // Instance Token (u)
            new CardPackReward(23514, 5000, SILVER), // Pvm Ticket pack (1k)
            new CardPackReward(23514, 4000, SILVER), // Pvm Ticket pack (1k)
            new CardPackReward(23515, 1250, SILVER), // Pvm Ticket pack (5k)
            new CardPackReward(23515, 1000, SILVER), // Pvm Ticket pack (5k)
            new CardPackReward(23516, 400, SILVER), // Pvm Ticket pack (10k)
            new CardPackReward(23516, 500, SILVER), // Pvm Ticket pack (10k)
            new CardPackReward(21218, 150, SILVER), // Invention xp lamp
            new CardPackReward(21218, 100, SILVER), // Invention xp lamp
            new CardPackReward(22006, 125, SILVER), // Deathtouch Darts
            new CardPackReward(22006, 250, SILVER), // Deathtouch Darts
            new CardPackReward(15003, 4, SILVER), // Silver Casket
            new CardPackReward(15002, 2, SILVER), // Ruby Casket
            new CardPackReward(23002, 1, SILVER), // Fortune Chest
            new CardPackReward(15004, 1, SILVER), // Diamond Casket
            new CardPackReward(10934, 1, SILVER), // $25 Bond
            new CardPackReward(18818, 1, SILVER), // Collector ring III
            new CardPackReward(18888, 1, SILVER), // Collector necklace III
            new CardPackReward(18881, 1, SILVER), // Supreme Boots
            new CardPackReward(18883, 1, SILVER), // Supreme gloves
            new CardPackReward(19810, 1, SILVER), // Supreme spirit shield
            new CardPackReward(10942, 2, SILVER), // $10 Bond
            new CardPackReward(9084, 1, SILVER), // Collector's Attachment
            new CardPackReward(10947, 25, SILVER), // Supreme Attachment
            new CardPackReward(8410, 1, SILVER), // Elite Sword (AoE)
            new CardPackReward(8411, 1, SILVER), // Elite Crossbow (AoE)
            new CardPackReward(8412, 1, SILVER), // Elite Staff (AoE)


            new CardPackReward(12630, 1, GOLD), // Donator's Aura
            new CardPackReward(9083, 1, GOLD), // Rage Attachment
            new CardPackReward(20591, 1, GOLD), // Rage Cape
            new CardPackReward(10949, 3, GOLD), // Light Attachment
            new CardPackReward(3578, 1, GOLD), // Owner's Cape Goodiebag
            new CardPackReward(23210, 1, GOLD), // Onyx Casket
            new CardPackReward(22121, 1, GOLD), // Golden Scratch Card
            new CardPackReward(23204, 1, GOLD), // Owner Jewelry Goodiebag
            new CardPackReward(10935, 1, GOLD), // $50 Bond
            new CardPackReward(23002, 2, GOLD), // Fortune Chest
            new CardPackReward(15004, 2, GOLD), // Diamond Casket
            new CardPackReward(5012, 1, GOLD), // Execution Twisted Bow
            new CardPackReward(12535, 1, GOLD), // Execution Vitur
            new CardPackReward(17011, 1, GOLD), // Sanguinesti Execution Staff
            new CardPackReward(4684, 1, GOLD), // Execution helm
            new CardPackReward(4685, 1, GOLD), // Execution body
            new CardPackReward(4686, 1, GOLD), // Execution legs
            new CardPackReward(20489, 25, GOLD), // Launch Chest
            new CardPackReward(10946, 75, GOLD), // $1 Scroll
            new CardPackReward(10946, 50, GOLD), // $1 Scroll
            new CardPackReward(6769, 10, GOLD), // $5 Bond
            new CardPackReward(6769, 12, GOLD), // $5 Bond
            new CardPackReward(19114, 1500, GOLD), // Grand Mystery Box
            new CardPackReward(23456, 30, GOLD), // High-Tier Ticket
            new CardPackReward(20488, 200, GOLD), // OVERPOWERED CHEST
            new CardPackReward(23211, 750, GOLD), // Instance Token (u)
            new CardPackReward(23516, 2000, GOLD), // Pvm Ticket pack (10k)
            new CardPackReward(15003, 6, GOLD), // Silver Casket
            new CardPackReward(15002, 3, GOLD), // Ruby Casket
            new CardPackReward(10934, 2, GOLD), // $25 Bond


            new CardPackReward(22113, 1, DIAMOND), // Dark Twisted Bow
            new CardPackReward(22114, 1, DIAMOND), // Dark Sanguinesti Staff
            new CardPackReward(22115, 1, DIAMOND), // Dark Scythe Of Vitur
            new CardPackReward(22105, 1, DIAMOND), // Lucifer's wings
            new CardPackReward(23314, 1, DIAMOND), // Zenyte Casket
            new CardPackReward(3578, 2, DIAMOND), // Owner's Cape Goodiebag
            new CardPackReward(23210, 2, DIAMOND), // Onyx Casket
            new CardPackReward(22121, 2, DIAMOND), // Golden Scratch Card
            new CardPackReward(23204, 2, DIAMOND), // Owner Jewelry Goodiebag
            new CardPackReward(10935, 2, DIAMOND), // $50 Bond
            new CardPackReward(23002, 4, DIAMOND), // Fortune Chest
            new CardPackReward(15004, 3, DIAMOND), // Diamond Casket
            new CardPackReward(10946, 125, DIAMOND), // $1 Scroll
            new CardPackReward(6769, 20, DIAMOND), // $5 Bond
            new CardPackReward(19114, 3000, DIAMOND), // Grand Mystery Box
            new CardPackReward(23456, 75, DIAMOND), // High-Tier Ticket
            new CardPackReward(15002, 5, DIAMOND), // Ruby Casket
            new CardPackReward(10934, 3, DIAMOND), // $25 Bond


            new CardPackReward(10943, 2, LEGENDARY), // $100 Bond
            new CardPackReward(15328, 1, LEGENDARY), // Rage pot
            new CardPackReward(23456, 100, LEGENDARY), // High-Tier Ticket
            new CardPackReward(23223, 6, LEGENDARY), // Blood attachments
            new CardPackReward(22110, 1, LEGENDARY), // Owner's Attachment
            new CardPackReward(7995, 1, LEGENDARY), // Owner's cape
            new CardPackReward(23314, 2, LEGENDARY), // Zenyte Casket
            new CardPackReward(10935, 4, LEGENDARY), // $50 Bond
            new CardPackReward(3578, 4, LEGENDARY), // Owner's Cape Goodiebag
            new CardPackReward(23210, 4, LEGENDARY), // Onyx Casket
            new CardPackReward(22121, 4, LEGENDARY), // Golden Scratch Card
            new CardPackReward(23204, 4, LEGENDARY), // Owner Jewelry Goodiebag
            new CardPackReward(4440, 1, LEGENDARY), // perk
            new CardPackReward(4442, 1, LEGENDARY), // perk

    };
}
