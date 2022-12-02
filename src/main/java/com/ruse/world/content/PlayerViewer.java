package com.ruse.world.content;

import com.ruse.model.GameMode;
import com.ruse.model.Item;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.util.Misc;
import com.ruse.world.content.combat.Maxhits;
import com.ruse.world.content.skill.impl.slayer.SlayerTasks;
import com.ruse.world.entity.impl.player.Player;

public class PlayerViewer {


    public static void openInventory(Player player) {
        Player target = player.getCurrentPlayerViewing();

        player.getPacketSender().sendString(140002, "Viewing player: " + target.getUsername());
        displayInfo(player);

        displayInventory(player);
        player.getPacketSender().sendInterface(140000);
        player.getPacketSender().sendConfig(2357, 0);
    }

    public static void openEquipment(Player player) {
        Player target = player.getCurrentPlayerViewing();

        player.getPacketSender().sendString(140002, "Viewing player: " + target.getUsername());
        displayEquipment(player);
        player.getPacketSender().sendInterface(108000);
    }

    public static void openBank(Player player) {
        Player target = player.getCurrentPlayerViewing();

        player.getPacketSender().sendString(140002, "Viewing player: " + target.getUsername());
        displayBank(player);
        player.getPacketSender().sendInterface(140000);
    }

    public static void displayBank(Player player) {
        Player target = player.getCurrentPlayerViewing();

        for (int i = 0; i < 36; i++) {
            player.getPacketSender().sendItemOnInterface(140101 + i, -1, 1);
        }

        if (target == null)
            return;

        int index = 0;
        int items = 0;
        int slot = 0;
        for (int i = 0; i < 9; i++) {
            index = 0;
            for (Item item : target.getBank(i).getItems()) {
                if (target.getBank(i).getItems()[index].getId() != -1 && target.getBank(i).getItems()[index].getAmount() >= 1) {
                    player.getPacketSender().sendItemOnInterface(140101 + items, target.getBank(i).getItems()[index].getId(), target.getBank(i).getItems()[index].getAmount());
                    items++;
                }
                index++;
            }
        }
        player.getPacketSender().setScrollBar(140100, 39 + items / 6 * 34);

    }

    public static void displayInventory(Player player) {
        Player target = player.getCurrentPlayerViewing();
        for (int i = 0; i < 50; i++) {
            player.getPacketSender().sendItemOnInterface(140101 + i, -1, 1);
        }

        int index = 0;
        int interfaceID = 140101;
        for (Item item : target.getInventory().getItems()) {
            player.getPacketSender().sendItemOnInterface(interfaceID++, target.getInventory().getItems()[index].getId(),
                    target.getInventory().getItems()[index].getAmount());
            index++;
            if (index % 4 == 0)
                interfaceID += 2;
        }
        player.getPacketSender().setScrollBar(140100, 244);
    }

    public static void displayEquipment(Player player) {
        Player target = player.getCurrentPlayerViewing();

        player.getPacketSender().sendDuelEquipmentOther(target);
    }

    public static void displayInfo(Player player) {
        Player target = player.getCurrentPlayerViewing();

        int counter = 108051;

        player.getPacketSender().sendString(counter++, "Play Time:@whi@ " + Misc.getHoursPlayed((target.getTotalPlayTime() + player.getRecordedLogin().elapsed())));
        player.getPacketSender().sendString(counter++, "Money pouch:@whi@ " + Misc.insertCommasToNumber(target.getMoneyInPouch() + ""));

        player.getPacketSender().sendString(counter++, "Total Level:@whi@ " + target.getSkillManager().getTotalLevel());

        player.getPacketSender().sendString(counter++, "Total Exp:@whi@ " + Misc.insertCommasToNumber(target.getSkillManager().getTotalExp()));


        if (target.getGameMode() == GameMode.IRONMAN) {
            player.getPacketSender().sendString(counter++, "Game Mode: @whi@Ironman");
        } else if (target.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
            player.getPacketSender().sendString(counter++, "Mode: @whi@Ultimate Ironman");
        } else if (target.getGameMode() == GameMode.GROUP_IRONMAN) {
            player.getPacketSender().sendString(counter++, "Mode: @whi@Group Ironman");
        } else if (target.getGameMode() == GameMode.EXTREME_MODE) {
            player.getPacketSender().sendString(counter++, "Mode: @whi@Veteran");
        } else {
            player.getPacketSender().sendString(counter++, "Game Mode: @whi@Normal");
        }
        player.getPacketSender().sendString(counter++, "Rank: @whi@" + Misc.ucFirst(target.getRights().toString().replace("_", " ")));
        player.getPacketSender().sendString(counter++, "Donated: @whi@" + target.getAmountDonated());
        player.getPacketSender().sendString(counter++, "Bonus Exp: @whi@" + (target.getMinutesBonusExp() == -1 ? "Inactive" : target.getMinutesBonusExp() + " Minutes"));

        counter++;
        player.getPacketSender().sendString(counter++, "@yel@Misc:");

        player.getPacketSender().sendString(counter++, "Drop Rate: @whi@" + NPCDrops.dropRateBoost(target)  + "%");

        player.getPacketSender().sendString(counter++, "Melee Maxhit: @whi@" + (Maxhits.melee(target, target) / 10));
        player.getPacketSender().sendString(counter++, "Ranged Maxhit: @whi@" + (Maxhits.ranged(target, target) / 10));
        player.getPacketSender().sendString(counter++, "Magic Maxhit: @whi@" + (Maxhits.magic(target, target) / 10));


        counter++;


        counter++;
        player.getPacketSender().sendString(counter++, "@yel@SLAYER:");

        player.getPacketSender().sendString(counter++, "Slayer Points: @whi@" + target.getPointsHandler().getSlayerPoints());

        player.getPacketSender().sendString(counter++, "Task Type: @whi@" + (target.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK ? "N/A" : Misc
                .formatText(target.getSlayer().getTaskType().toString().toLowerCase().replaceAll("_", " "))));

        player.getPacketSender().sendString(counter++, (target.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK
                ? "Task: @whi@" + Misc.formatText(
                target.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))
                : "Task: @whi@" + Misc.formatText(
                target.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))
                + "s"));

        player.getPacketSender().sendString(counter++, "Task Amount: @whi@" + target.getSlayer().getAmountToSlay());
        player.getPacketSender().sendString(counter++, "Task Streak: @whi@" + target.getSlayer().getTaskStreak());

        counter++;
		player.getPacketSender().sendString(counter++, "@yel@POINTS:");

        player.getPacketSender().sendString(counter++, "NPC kill Count: @whi@ " + target.getPointsHandler().getNPCKILLCount());
        player.getPacketSender().sendString(counter++, "Boss Points: @whi@ " + target.getPointsHandler().getBossPoints());
        player.getPacketSender().sendString(counter++, "Member Points: @whi@" + target.getPointsHandler().getMemberPoints());
        player.getPacketSender().sendString(counter++, "Voting Points: @whi@ " + target.getPointsHandler().getVotingPoints());
        player.getPacketSender().sendString(counter++, "Slayer Points: @whi@" + target.getPointsHandler().getSlayerPoints());
        player.getPacketSender().sendString(counter++, "Loyalty Points: @whi@" + target.getPointsHandler().getLoyaltyPoints());
        player.getPacketSender().sendString(counter++, "Prestige Points: @whi@" + target.getPointsHandler().getPrestigePoints());
        player.getPacketSender().sendString(counter++, "Total Prestige: @whi@" + target.getPointsHandler().getTotalPrestiges());
        player.getPacketSender().sendString(counter++, "Event Points: @whi@ " + target.getPointsHandler().getEventPoints());
        player.getPacketSender().sendString(counter++, "Penguin Multiplier: @whi@ " + target.getPointsHandler().getPengRate() + "%");

    }

    public static boolean handleButton(Player player, int buttonId) {
        if (buttonId == 140006 || buttonId == 108006) {
            openInventory(player);
            return true;
        } else if (buttonId == 140007 || buttonId == 108007) {
            openEquipment(player);
            return true;
        } else if (buttonId == 140008 || buttonId == 108008) {
            openBank(player);
            return true;
        }
        return false;
    }

}
