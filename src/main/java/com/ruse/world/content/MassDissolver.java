package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.model.container.impl.Bank;
import com.ruse.util.Misc;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.entity.impl.player.Player;

public class MassDissolver {

    public static final int[] tier1Items = {4151, 4716, 4720, 4722, 11235, 4732, 4736, 4738, 15486, 4708, 4712, 4714, 6570, 13262,
            18353, 11732, 6585, 6737, 7462, 11846, 11848, 11850, 11852, 11854, 11856,
    4745, 4747, 4749, 4751, 4753, 4755, 4757, 4759, 4710, 4718, 4734,
    };

    public static final int[] tier2Items = {18686, 13996, 13913, 13919, 18799, 18834, 18801, 18800, 5095, 19140, 19139, 19138,
            4411, 19887, 22078, 19123, 11617, 3909, 3318};

    public static final int[] tier3Items = {22077, 6927, 6928, 6929, 19136, 6930, 6931, 6932, 6936, 6933, 6934, 6935, 12634,
            17291, 22079, 6937, 15418, 3324, 3905};


    public static void itemClick(Player player){
        if (player.getInventory().contains(23570)){
            DialogueManager.start(player, 8020);
            player.setDialogueActionId(8021);
         //   DialogueManager.sendItemStatementTwo(player, 4151, 122240, "okok", "opk123");
        }
    }

    public static void dissolveTier1(Player player){
        dissolve(player, tier1Items, 1);
    }


    public static void dissolveTier2(Player player){
        dissolve(player, tier2Items, 2);
    }


    public static void dissolveTier3(Player player){
        dissolve(player, tier3Items, 3);
    }


    public static void dissolve(Player player, int[] items, int tier){
        player.getPacketSender().sendInterfaceRemoval();
        int total = 0;
        for (int item : items){
            int tab = Bank.getTabForItem(player, item);
            if (player.getBank(tab).contains(item)){
                total += player.getDissolving().getTotalOrbAmount(item) * player.getBank(tab).getAmount(item);
                player.getBank(tab).delete(item, player.getBank(tab).getAmount(item), false);
            }
        }
        if (total == 0){
            player.sendMessage("You don't have any tier " + tier + " items in your bank to dissolve.");
            return;
        }

        total/= 2;
        player.getInventory().delete(23570, 1);
        player.getInventory().add(12855, total);
        player.sendMessage("@blu@All the tier "+tier+" items in your bank has been dissolved for " + Misc.insertCommasToNumber(total) + " Upgrade tokens.");
    }
}
