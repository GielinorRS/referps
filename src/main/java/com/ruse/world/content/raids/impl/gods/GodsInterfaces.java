package com.ruse.world.content.raids.impl.gods;

import com.ruse.model.Item;
import com.ruse.util.Misc;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.raids.RaidDifficulty;
import com.ruse.world.entity.impl.player.Player;

public class GodsInterfaces {

    public static void openStartScreen(Player player) {
        GodsRaidsParty party = player.getGodsRaidsParty();
        if (party == null || !party.getOwner().equals(player)) {
            player.sendMessage("You must be the leader of your party to do this.");
            return;
        }
        if (party.getDifficulty() == null)
            party.setDifficulty(RaidDifficulty.EASY);

        player.getPacketSender().sendString(144017, Misc.insertCommasToNumber(party.getDifficulty().getGodsRequirement().getCost()) + " Upgrade Tokens");

        if (party.getDifficulty() == RaidDifficulty.EASY) {
            player.getPacketSender().sendString(144018, "10,000 Blood Odin KC");
        } else if (party.getDifficulty() == RaidDifficulty.INTERMEDIATE) {
            player.getPacketSender().sendString(144018, "100 Easy KC");
        } else if (party.getDifficulty() == RaidDifficulty.ADVANCED) {
            player.getPacketSender().sendString(144018, "200 Medium KC");
        }

        player.getPacketSender().sendConfig(4511, party.getDifficulty().ordinal());
        player.getPacketSender().sendInterface(144000);
    }


    public static void openCoffer(Player player) {

        int length = player.getGodsCoffer().size() >= 40 ? player.getGodsCoffer().size() : 40;

        for (int i = 0; i < length; i++) {
            Item item = player.getGodsCoffer().size() > i ? player.getGodsCoffer().get(i) : new Item(-1, 1);
            player.getPacketSender().sendItemOnInterface(144151 + i, item.getId(), item.getAmount());
        }

        int itemsScroll = (length / 8 * 41);
        if (itemsScroll <= 205)
            itemsScroll = 205;
        player.getPacketSender().setScrollBar(144150, itemsScroll);

        player.getPacketSender().sendString(144112, "Drop Rate: @whi@" + player.getIsleDropRate() + "%");

        player.getPacketSender().sendConfig(4511, 0);
        player.getPacketSender().sendInterface(144100);
        player.sendMessage("@red@Withdrawing from your coffer will reset your Isle of the Gods Drop rate to 0%");

    }

    public static void takeCoffer(Player player) {
        boolean took = false;
        for (int i = 0; i < player.getGodsCoffer().size();i ++ ) {
            Item item = player.getGodsCoffer().get(i);
            if (item != null)
            if (player.getInventory().hasSpaceFor(item.getId(), item.getAmount())) {
                player.getInventory().add(item.getId(), item.getAmount());
                took = true;
                player.getGodsCoffer().remove(i);
                i --;
            }
        }
        if (took) {
            player.sendMessage("Your coffer rewards were added to your inventory.");
            player.setIsleDropRate( 0);
        }
        openCoffer(player);
    }

    public static void bankCoffer(Player player) {

        for (Item item : player.getGodsCoffer()) {
            player.depositItemBank(item);
        }

        player.setIsleDropRate( 0);

        player.getGodsCoffer().clear();
        openCoffer(player);
    }

    public static void clickCofferItem(Player player, int interfaceId, int itemID) {
        int index = interfaceId - 13079;

        if (index < player.getGodsCoffer().size()) {
            if (itemID == player.getGodsCoffer().get(index).getId() &&
                    player.getInventory().hasSpaceFor(player.getGodsCoffer().get(index).getId(), player.getGodsCoffer().get(index).getAmount())) {
                player.setIsleDropRate( 0);
                player.getInventory().add(player.getGodsCoffer().get(index));
                player.sendMessage("x" + player.getGodsCoffer().get(index).getAmount() + " " + player.getGodsCoffer().get(index).getDefinition().getName() +
                        " has been added to your inventory.");
                player.getGodsCoffer().remove(index);
            } else {
                player.getPacketSender().sendMessage("You don't have enough free inventory space.");
            }
        }
        openCoffer(player);
    }


    public static void openRewards(Player player, Box[] loot) {
        int length = loot.length >= 40 ? loot.length : 40;
        int interID = 144551;

        for (int i = 0; i < length; i++) {
            Box item = loot.length > i ? loot[i] : new Box(-1, 0);
            int rate = (int) (100D / item.getRate());
            String chance = item.getRate() == 0 ? "" : item.getRate() >= 100 ? "Always" : item.getRate() >= 10 ? "Common" : ("1/" + rate);

            player.getPacketSender().sendItemOnInterface(interID++, item.getId(), item.getAmount());
            player.getPacketSender().sendString(interID++, chance);
        }

        int itemsScroll = (length / 8 * 41);
        if (itemsScroll <= 205)
            itemsScroll = 205;
        player.getPacketSender().setScrollBar(144550, itemsScroll);

        player.getPacketSender().sendConfig(4511, 1);
        player.getPacketSender().sendInterface(144500);

        player.getPacketSender().sendString(144516, "Drop Rate: @whi@" + player.getIsleDropRate() + "%");


    }

    public static void handleButton(Player player, int id) {

        switch (id) {
            case 144006:
                player.getGodsRaidsParty().setDifficulty(RaidDifficulty.EASY);
                openStartScreen(player);
                break;
            case 144008:
                player.getGodsRaidsParty().setDifficulty(RaidDifficulty.INTERMEDIATE);
                openStartScreen(player);
                break;
            case 144010:
                player.getGodsRaidsParty().setDifficulty(RaidDifficulty.ADVANCED);
                openStartScreen(player);
                break;
            case 144019:
                GodsRaids.start(player.getGodsRaidsParty(), player);
                break;


            case 144506:
                openCoffer(player);
                break;
            case 144108:
            case 144510:
                openRewards(player, GodsLoot.EASY);
                player.getPacketSender().sendConfig(4512, 0);
                break;
            case 144512:
                openRewards(player, GodsLoot.MEDIUM);
                player.getPacketSender().sendConfig(4512, 1);
                break;
            case 144514:
                openRewards(player, GodsLoot.HARD);
                player.getPacketSender().sendConfig(4512, 2);
                break;
            case 144110:
                takeCoffer(player);
                break;
            case 144111:
                bankCoffer(player);
                break;
        }

    }

}
