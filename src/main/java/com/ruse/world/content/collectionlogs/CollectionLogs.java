package com.ruse.world.content.collectionlogs;

import com.ruse.model.Item;
import com.ruse.model.container.impl.Shop;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.VotingMinigame;
import com.ruse.world.content.boxes.*;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.casketopening.impl.*;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnimaRewards;
import com.ruse.world.content.deathsanctum.DeathSanctumRewards;
import com.ruse.world.content.minigames.impl.HallsOfAmmo;
import com.ruse.world.content.minigames.impl.TreasureHunter;
import com.ruse.world.content.raids.impl.gods.GodsLoot;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum CollectionLogs {
    ICE(CollectionLogType.MONSTERS, 13747, new Item(19114, 3)),
    PREDATOR(CollectionLogType.MONSTERS, 12343, new Item(19114, 3)),
    CYAN(CollectionLogType.MONSTERS, 12886, new Item(19114, 5)),
    BULWARK(CollectionLogType.MONSTERS, 10103, new Item(19114, 5)),
    MINI_LUCI(CollectionLogType.MONSTERS, 9011, true,new Item(6769, 3)),
   // NIHIL(CollectionLogType.MONSTERS, 9885, true,new Item(20488, 25)),
  //  PRYSM(CollectionLogType.MONSTERS, 9890, true,new Item(20488, 25)),
    DRAGONSTONE_DRAGON(CollectionLogType.MONSTERS, 9892, true,new Item(10942, 2)),
    ONYX_DRAGON(CollectionLogType.MONSTERS, 9893, true,new Item(10942, 2)),
    HYDRIX_DRAGON(CollectionLogType.MONSTERS, 9894, true,new Item(10942, 2)),
   // SHARK(CollectionLogType.MONSTERS, 9898, true,new Item(10942, 2)),
   // EMBER_GOLEM(CollectionLogType.MONSTERS, 9897, true,new Item(23704, 1)),
   // JINX(CollectionLogType.MONSTERS, 10120, true,new Item(23725, 1)),

  //  THUNDER_DEMON(CollectionLogType.MONSTERS, 11874, true,new Item(10942, 2)),
    AVIANSE(CollectionLogType.MONSTERS, 10032, true,new Item(10942, 2)),

  //  MUSPAH(CollectionLogType.MONSTERS, 10023, true,new Item(23831, 1)),
    IMP(CollectionLogType.ZONES, 1614,new Item(10025, 3)),
    LORD(CollectionLogType.ZONES, 603,new Item(10025, 5)),
    DEMON(CollectionLogType.ZONES, 12843,new Item(10025, 5)),
    DRAGON(CollectionLogType.ZONES, 53,new Item(10025, 6)),
    BEAST(CollectionLogType.ZONES, 8018,new Item(10025, 6)),
    TROLLKING(CollectionLogType.ZONES, 13635,new Item(10025, 8)),
    AVATAR(CollectionLogType.ZONES, 8008,new Item(10025, 10)),
    ANGEL(CollectionLogType.ZONES, 3308,new Item(19114, 15)),
    LUCIEN(CollectionLogType.ZONES, 3117,new Item(19114, 25)),
    HERCULES(CollectionLogType.ZONES, 201,new Item(19114, 50)),
    SATAN(CollectionLogType.ZONES, 202,new Item(20488, 15)),
    ZEUS(CollectionLogType.ZONES, 203,new Item(20488, 25)),
    GROUDON(CollectionLogType.ZONES, 8010, true,new Item(20488, 25)),
    RED_FENRIR(CollectionLogType.ZONES, 9810, true,new Item(20488, 30)),
    GREEN_FENRIR1(CollectionLogType.ZONES, 9811, true,new Item(20488, 30)),
    BLUE_FENRIR2(CollectionLogType.ZONES, 9812, true,new Item(20488, 30)),
    BORK(CollectionLogType.ZONES, 7134, true,new Item(20488, 50)),

    FRIEZA(CollectionLogType.BOSSES, 252, true,new Item(10946, 15)),
    PERFECT_CELL(CollectionLogType.BOSSES, 449, true,new Item(10946, 15)),
    SUPER_BUU(CollectionLogType.BOSSES, 452, true,new Item(6769, 4)),
    SCARLET(CollectionLogType.BOSSES, 2949, true,new Item(20488, 15)),
    HERBAL(CollectionLogType.BOSSES, 2342, true,new Item(20488, 15)),
    AZURE(CollectionLogType.BOSSES, 3831, true,new Item(20488, 15)),
    JOKER(CollectionLogType.BOSSES, 185, true,new Item(20488, 15)),
    CRYSTAL(CollectionLogType.BOSSES, 6430, true,new Item(10934, 1)),
    SUPREME(CollectionLogType.BOSSES, 440, true,new Item(10934, 1)),
    VASA(CollectionLogType.BOSSES, 1120, true,new Item(10949, 2)),
    ELITE(CollectionLogType.BOSSES, 8015, true,new Item(10942, 2)),
    MEGA(CollectionLogType.BOSSES, 4540, true,new Item(10942, 3)),
    INFERNAL(CollectionLogType.BOSSES, 12810, true,new Item(10942, 4)),
    LUCIFER(CollectionLogType.BOSSES, 9012, true,new Item(22106, 1)),
    DARK(CollectionLogType.BOSSES, 438, true,new Item(15004, 1)),
    ODIN(CollectionLogType.BOSSES, 9813, true,new Item(15004, 2)),
    //DARK_PRYSM(CollectionLogType.BOSSES, 9891, true,new Item(6769, 5)),
    //SHADOW_SHARK(CollectionLogType.BOSSES, 9899, true,new Item(15004, 1)),



    SARADOMIN(CollectionLogType.BOSSES, "Saradomin", GodsLoot.SARADOMIN, true,new Item(23653, 1)),
    ZAMORAK(CollectionLogType.BOSSES, "Zamorak", GodsLoot.ZAMORAK, true,new Item(23651, 1)),
    VORKATH(CollectionLogType.BOSSES, 28060, true,new Item(23314, 2)),
    SILVERHAWK(CollectionLogType.BOSSES, 10030, true,new Item(10942, 5)),

    VOTING_MINIGAME(CollectionLogType.MINIGAMES, "Voting Minigame", VotingMinigame.loot, true,new Item(15003, 2)),
    //RAIDS_1(CollectionLogType.MINIGAMES, "Raids 1", Raids1.rare1, true,new Item(18404, 15)),
    SANCTUM_OF_DEATH(CollectionLogType.MINIGAMES, "Sanctum of Death", DeathSanctumRewards.rare, true,new Item(23210, 1)),
    TREASURE_HUNTER(CollectionLogType.MINIGAMES, "Treasure Hunter", TreasureHunter.loot, true,new Item(10942, 3)),
    HALLS_OF_AMMO(CollectionLogType.MINIGAMES, "Halls of Ammo", HallsOfAmmo.loot, true,new Item(6769, 1)),
    PEST_CONTROL(CollectionLogType.MINIGAMES, "Pest Control", Shop.ShopManager.getShops().get(Shop.PEST_STORE).getItems(), true,new Item(15002, 2)),

    CHAMBERS_OF_ANIMA(CollectionLogType.MINIGAMES, "Chambers of Anima", ChambersOfAnimaRewards.rare, true,new Item(23314, 1)),

    //MINIGAMES

    MYSTERY_BOX(CollectionLogType.BOXES,6199,new Item(19116, 15), MBox.common,MBox.uncommon,MBox.rare),
    PVM_BOX(CollectionLogType.BOXES,PVMBox.ITEM_ID,new Item(23480, 1000), PVMBox.commonpvm, PVMBox.uncommonpvm, PVMBox.rarepvm),
    PVM_BOX_T2(CollectionLogType.BOXES, 23480,new Item(20488, 20), PVMBox.uncommonpvm, PVMBox.rarepvm, PVMBox.ultRare),
    SUPER_BOX(CollectionLogType.BOXES,19116,new Item(19115, 40), Super.common,Super.uncommon,Super.rare),
    EXTREME_BOX(CollectionLogType.BOXES,19115,new Item(19114, 50), Extreme.common,Extreme.uncommon,Extreme.rare),
    GRAND_BOX(CollectionLogType.BOXES,19114,new Item(20488, 40), Grand.common,Grand.uncommon,Grand.rare),
    OP_BOX(CollectionLogType.BOXES,20488, true,new Item(10942, 4), OP.common,OP.uncommon,OP.rare),
    VOTING_BOX(CollectionLogType.BOXES,15501,true, new Item(20489, 3), VotingMbox.common,VotingMbox.uncommon,VotingMbox.rare),
    PROGRESSIVE_BOX(CollectionLogType.BOXES,10025,new Item(7120, 40),  ProgressiveBox.commonpro,  ProgressiveBox.uncommonpro,  ProgressiveBox.rarepro),
    SLAYER_BOX(CollectionLogType.BOXES, 7120,new Item(23479, 20), SlayerBox.commonpro2,  SlayerBox.uncommonpro2,  SlayerBox.rarepro2),
    SLAYER_BOX_UPGRADED(CollectionLogType.BOXES, 23479,new Item(20489, 3), SlayerBoxU.loot, true),
    DRAGONBALL_BOX(CollectionLogType.BOXES,18768,new Item(6769, 2),  DragonballBox.common1,  DragonballBox.uncommon1,  DragonballBox.rare1),
    RAIDS_TWO_BOX(CollectionLogType.BOXES,18404,new Item(23464, 5), Raids2Box.loot, true),
    LAUNCH_CHEST(CollectionLogType.BOXES,20489, true, new Item(15003, 2),Launch.common,Launch.uncommon,Launch.rare),
    SILVER_CHEST(CollectionLogType.BOXES, 15003,new Item(15002, 2), SilverCasket.loot, true),
    RUBY_CHEST(CollectionLogType.BOXES,15002,new Item(15004, 1), RubyCasket.loot, true),
    DIAMOND_CHEST(CollectionLogType.BOXES,15004,new Item(23210, 1), DiamondCasket.loot, true),
    ONYX_CHEST(CollectionLogType.BOXES,23210,new Item(23314, 1), OnyxCasket.loot, true),
    ZENYTE_CHEST(CollectionLogType.BOXES,23314,new Item(23478, 1), ZenyteCasket.loot, true),
    ANGELIC_CASE(CollectionLogType.BOXES,23776,new Item(23478, 3), AngelicCase.loot, true),
    DIVINE_CASE(CollectionLogType.BOXES,23782,new Item(23478, 3), DivineCase.loot, true),
    MONEY_CASE(CollectionLogType.BOXES,23812,new Item(23478, 2), MoneyCase.loot, true),

    AUTUMN_BOX(CollectionLogType.BOXES,23882,new Item(23478, 3), AutumnBox.loot, true),

    ;

    CollectionLogs(CollectionLogType type, int npcID, Item reward) {
        this(type, npcID,  false, reward);
    }

    CollectionLogs(CollectionLogType type, int npcID, boolean announce, Item reward) {
        this.reward = reward;
        this.type = type;
        this.name = NpcDefinition.forId(npcID).getName();
        this.npcId = npcID;
        this.uniqueItems = new ArrayList<>();
        this.announce = announce;
        for (NPCDrops.NpcDropItem npcDropItem : NPCDrops.forId(npcID).getDropList()) {
            if (npcDropItem.getChance() >= 1000) {
                if (!uniqueItems.contains(npcDropItem.getId())) {
                    uniqueItems.add(npcDropItem.getId());
                }
            }
        }
    }

    CollectionLogs(CollectionLogType type, String name, Box[] loot, Item... reward) {
       this(type, name, loot, false, reward);
    }

    CollectionLogs(CollectionLogType type, String name, Item[] loot, boolean announce, Item... reward) {
        this.reward = reward[0];
        this.type = type;
        this.name = name;
        this.npcId = -1;
        this.uniqueItems = new ArrayList<>();
        this.announce = announce;
        for (Item item : loot) {
            if (item.getId() < 0)
                continue;
            if (!uniqueItems.contains(item.getId())) {
                uniqueItems.add(item.getId());
            }
        }
    }

    CollectionLogs(CollectionLogType type, String name, Box[] loot, boolean announce, Item... reward) {
        this.reward = reward[0];
        this.type = type;
        this.name = name;
        this.npcId = -1;
        this.uniqueItems = new ArrayList<>();
        this.announce = announce;
        for (Box item : loot) {
            if (!uniqueItems.contains(item.getId())) {
                uniqueItems.add(item.getId());
            }
        }
    }

    CollectionLogs(CollectionLogType type, int itemID, Item reward,Box[] loot,  boolean announce) {
        this.reward = reward;
        this.type = type;
        this.name = ItemDefinition.forId(itemID).getName();
        this.npcId = -1;
        this.itemId = itemID;
        this.uniqueItems = new ArrayList<>();
        this.announce = announce;
        for (Box item : loot) {
            if (!uniqueItems.contains(item.getId())) {
                uniqueItems.add(item.getId());
            }
        }
    }




    CollectionLogs(CollectionLogType type, int itemID, Item reward, int[]... items) {
       this(type, itemID, false, reward, items);
    }


    CollectionLogs(CollectionLogType type, int itemID, boolean announce, Item reward, int[]... items) {
        this.reward = reward;
        this.type = type;
        this.name = ItemDefinition.forId(itemID).getName();
        this.npcId = -1;
        this.itemId = itemID;
        this.uniqueItems = new ArrayList<>();
        this.announce = announce;
        for (int i = 0; i < items.length; i++) {
            for (int z = 0; z < items[i].length; z++) {
                if (!uniqueItems.contains(items[i][z])) {
                    uniqueItems.add(items[i][z]);
                }
            }
        }
    }

    public int getKillCount(Player player) {
        if (type.ordinal() <= CollectionLogType.BOSSES.ordinal()) {
            return KillsTracker.getTotalKillsForNpc(npcId, player);
        }
        if (type == CollectionLogType.BOXES
        || type == CollectionLogType.MINIGAMES){
            for (CollectionLogManager.LogProgress progress : player.getCollectionLogManager().getLogProgress()) {
                if (progress.getName().equalsIgnoreCase(name())) {
                    return progress.getCompleted();
                }
            }
        }
        return 0;
    }

    public static CollectionLogs forNpcId(int npcId) {
        for (CollectionLogs logs : CollectionLogs.values()) {
            if (logs.npcId == npcId)
                return logs;
        }
        if (npcId == 10000)
            return SARADOMIN;
        if (npcId == 10001)
            return ZAMORAK;
        return null;
    }
    public static CollectionLogs forItemId(int itemId) {
        for (CollectionLogs logs : CollectionLogs.values()) {
            if (logs.itemId == itemId)
                return logs;
        }
        return null;
    }


    public static CollectionLogs forName(String name) {
        for (CollectionLogs logs : CollectionLogs.values()) {
            if (logs.name().equalsIgnoreCase(name))
                return logs;
        }
        return null;
    }
    private CollectionLogType type;
    private int npcId;
    private int itemId;
    private boolean announce;
    private String name;
    private List<Integer> uniqueItems;
    private Item reward;

    public enum CollectionLogType {

        MONSTERS, ZONES, BOSSES, MINIGAMES, BOXES,
        ;

        CollectionLogType() {
        }
    }

}