package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.content.boxes.*;
import com.ruse.world.content.cardpacks.CardPack;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.casketopening.impl.*;
import com.ruse.world.content.minigames.impl.HallsOfAmmo;
import com.ruse.world.content.minigames.impl.TreasureHunter;
import com.ruse.world.content.skill.impl.thieving.Stalls;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;

public class PossibleLootInterface {

    public static void openInterface(Player player, LootData data) {
        player.getPacketSender().sendInterface(101000);

        int stringStart = 101126;
        for (LootData loot : LootData.values()) {
            player.getPacketSender().sendItemOnInterface(stringStart++, loot.getItemId(), 1);
            String name = loot.name != null ? loot.name : "          " + ItemDefinition.forId(loot.getItemId()).getName();
            player.getPacketSender().sendString(stringStart++, (loot == data ? "@whi@" : "") + name);
        }

        player.getPacketSender().setScrollBar(101100, LootData.values().length * 40);

        int index = 101251;
        int i = 0;
        for (Item item : data.getLoot()) {
            player.getPacketSender().sendItemOnInterface(index++, item.getId(), item.getAmount());
            i++;
        }
        int rows = (i / 7) + 1;
        if (rows <= 6)
            rows = 6;
        player.getPacketSender().setScrollBar(101250, 2 + (rows * 35));

        for (int z = i; z < (rows * 7 >= 42 ? rows * 7 : 42); z++) {
            player.getPacketSender().sendItemOnInterface(index++, -1, 1);
        }
    }

    public static boolean handleButton(Player player, int buttonID) {
        if (buttonID >= 101127 && buttonID <= 101200) {
            int index = (buttonID - 101127) / 2;

            if (index <= LootData.values().length)
                openInterface(player, LootData.values()[index]);

            return true;
        }
        return false;
    }

    public enum LootData {

        SUPER(19116, Super.common, Super.uncommon, Super.rare),
        EXTREME(19115, Extreme.common, Extreme.uncommon, Extreme.rare),
        GRAND(19114, Grand.common, Grand.uncommon, Grand.rare),
        OPchest(20488, OP.common, OP.uncommon, OP.rare),
        LAUNCH(20489, Launch.common, Launch.uncommon, Launch.rare),
        SILVER(15003, SilverCasket.loot),
        RUBY(15002, RubyCasket.loot),
        DIAMOND(15004, DiamondCasket.loot),
        ONYX(23210, OnyxCasket.loot),
        ZENYTE(23314, ZenyteCasket.loot),
        FORTUNE(23002, WheelOfFortune.REWARDS),

        OCAPE(3578, Ocape.rare),

        //HWEEN(23109,MBox.common,MBox.uncommon,MBox.rare),

        TH(23112, TreasureHunter.loot),

        HALLS(23086, HallsOfAmmo.loot),

        //  RAIDS_ONE(13591,Raids1.common,Raids1.uncommon,Raids1.rare1),

        RAIDS_TWO(18404, Raids2Box.loot),

        BRONZE_PACK(23475, CardPack.getRewards(23475)),
        SILVER_PACK(23476, CardPack.getRewards(23476)),
        GOLD_PACK(23477, CardPack.getRewards(23477)),
        DIAMOND_PACK(23478, CardPack.getRewards(23478)),

        DRAGONBALLBOX(18768, DragonballBox.common1, DragonballBox.uncommon1, DragonballBox.rare1),
        SLAYERBOX(7120, SlayerBox.commonpro2, SlayerBox.uncommonpro2, SlayerBox.rarepro2),
        SLAYERBOX_U(23479, SlayerBoxU.loot),
        PROGRESSIVEBOX(10025, ProgressiveBox.commonpro, ProgressiveBox.uncommonpro, ProgressiveBox.rarepro),
        PVMMBOX(PVMBox.ITEM_ID, PVMBox.commonpvm, PVMBox.uncommonpvm, PVMBox.rarepvm),
        PVMMBOX_T2(23480, PVMBox.uncommonpvm, PVMBox.rarepvm, PVMBox.ultRare),
        MBOX(6199, MBox.common, MBox.uncommon, MBox.rare),
        FPK_SOLDIER(-1, FPK.LOOT, "Youtube Soldier"),
        AFK1(-1, Stalls.loot1, "AFK Stall (1)"),
        AFK2(-1, Stalls.loot2, "AFK Stall (2)"),
        AFK3(-1, Stalls.loot3, "AFK Stall (3)"),
        AFK4(-1, Stalls.loot4, "Zenyte AFK Stall"),

        TANZ_STALL(-1, Stalls.tanzaniteLoot, "Tanzanite AFK Stall"),
        HYDRIX_STALL(-1, Stalls.hydrixLoot, "Hydrix AFK Stall"),

        TRAVELLING_MERCHANT(-1, TravellingMerchant.FIRST_ITEMS, "Travelling Merchant"),

        GOLDEN_SCRATCH_CARD(22121, new Item[]{new Item(7995), new Item(22113),
                new Item(22114), new Item(22115), new Item(10943), new Item(10943)},
                "      Gold Scratch Card"),
        MONEY_CASE(23812, MoneyCase.loot),
        DIVINE_CASE(23782, DivineCase.loot),
        ANGELIC_CASE(23776, AngelicCase.loot),
        ;

        private int itemId;
        private String name;
        private Item[] loot;

        LootData(int itemId, Item[] loot, String name) {
            this.itemId = itemId;
            this.loot = loot;
            this.name = name;
            int i = 0;
        }

        LootData(int itemId, Box[] loot, String name) {
            this.itemId = itemId;
            this.loot = new Item[loot.length];
            this.name = name;
            int i = 0;
            for (Box d : loot) {
                this.loot[i++] = new Item(d.getId(), d.getMax());
            }
        }


        LootData(int itemId, Box[] loot) {
            this.itemId = itemId;
            this.loot = new Item[loot.length];
            int i = 0;
            for (Box d : loot) {
                this.loot[i++] = new Item(d.getId(), d.getMax());
            }
        }


        LootData(int itemId, int[]... items) {
            this.itemId = itemId;

            ArrayList<Item> loot = new ArrayList<>();
            for (int i = 0; i < items.length; i++) {
                for (int z = 0; z < items[i].length; z++) {
                    loot.add(new Item(items[i][z]));
                }
            }
            this.loot = Misc.convertItems(loot);
        }
        /*LootData(int itemId, int[]... items) {
            this.itemId = itemId;

            ArrayList<Item> loot = new ArrayList<>();
                for (int z = 0; z < items[0].length; z++) {
                    loot.add(new Item(items[0][z]));
            }

            this.loot = Misc.convertItems(loot);
        }*/

        LootData(int itemId, Item[]... items) {
            this.itemId = itemId;

            ArrayList<Item> loot = new ArrayList<>();
            for (int i = 0; i < items.length; i++) {
                for (int z = 0; z < items[i].length; z++) {
                    loot.add(items[i][z]);
                }
            }
            this.loot = Misc.convertItems(loot);
        }

        LootData(int itemId, ArrayList<CardPack.CardPackReward> items) {
            this.itemId = itemId;

            ArrayList<Item> loot = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                loot.add(new Item(items.get(i).getId(), items.get(i).getAmount()));
            }
            this.loot = Misc.convertItems(loot);
        }

        public int getItemId() {
            return itemId;
        }

        public Item[] getLoot() {
            return loot;
        }

    }

}