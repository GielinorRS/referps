package com.ruse.world.content;

import com.ruse.model.container.impl.Bank;
import com.ruse.util.Misc;
import com.ruse.world.entity.impl.player.Player;

import java.util.Arrays;
import java.util.HashSet;

public class BillBagConverter {

    public static final HashSet<Integer> remove = new HashSet<>(Arrays.asList(
            1053, // Green h'ween mask
            1054, // Green h'ween mask
            1057, // Red h'ween mask
            1058, // Red h'ween mask
            1055, // Blue h'ween mask
            1056, // Blue h'ween mask
            1038, // Red partyhat
            1039, // Red partyhat
            1040, // Yellow partyhat
            1041, // Yellow partyhat
            1042, // Blue partyhat
            1043, // Blue partyhat
            1044, // Green partyhat
            1045, // Green partyhat
            1046, // Purple partyhat
            1047, // Purple partyhat
            1048, // White partyhat
            1049, // White partyhat
            1050, // Santa hat
            1051, // Santa hat
            14008, // Torva full helm
            14009, // Torva platebody
            14009, // Torva platebody
            14010, // Torva platelegs
            14010, // Torva platelegs
            14011, // Pernix cowl
            14484, // Dragon claws
            14485, // Dragon claws
            13736, // Blessed spirit shield
            13737, // Blessed spirit shield
            13744, // Spectral spirit shield
            13745, // Spectral spirit shield
            13738, // Arcane spirit shield
            13739, // Arcane spirit shield
            13742, // Elysian spirit shield
            13743, // Elysian spirit shield
            6293, // Shadow spirit shield
            6294, // Shadow spirit shield
            18754, // Hellfire spirit shield
            11694, // Armadyl godsword
            11695, // Armadyl godsword
            11696, // Bandos godsword
            11697, // Bandos godsword
            11698, // Saradomin godsword
            11699, // Saradomin godsword
            11700, // Zamorak godsword
            11701, // Zamorak godsword
            15018, // Seers' ring (i)
            15019, // Archers' ring (i)
            15019, // Archers' ring (i)
            15020, // Warrior ring (i)
            15020, // Warrior ring (i)
            15220, // Berserker ring (i)
            12601, // Ring of the gods
            12603, // Tyrannical ring
            12605, // Treasonous ring
            20000, // Steadfast boots
            20001, // Glaiven boots
            20001, // Glaiven boots
            20002, // Ragefire boots
            20002, // Ragefire boots
            995,
            19670,
            757,
            11001,
            11002, 11003, 21058, 21050, 21051, 21052, 19994, 19989, 19988, 19992, 19984, 19985, 19986, 5500,
            10835, 5021,
            556, 558, 555, 554, 557, 559, 564, 562, 566, 9075, 563, 561, 560, 4698, 565, 9245
            ));


    public static void login(Player player){
        if (!player.isDeletedBillz()) {

            int totals = 0;

            for (int item : remove) {
                if (player.getInventory().contains(item)) {
                    totals += player.getInventory().getAmount(item) * player.getDissolving().getTotalOrbAmount(item);
                    player.getInventory().delete(item, player.getInventory().getAmount(item));
                }

                if (player.getEquipment().contains(item)) {
                    totals += player.getEquipment().getAmount(item) * player.getDissolving().getTotalOrbAmount(item);
                    player.getEquipment().delete(item, player.getEquipment().getAmount(item));
                }

                int tab = Bank.getTabForItem(player, item);
                if (player.getBank(tab).contains(item)) {
                    totals += player.getBank(tab).getAmount(item) * player.getDissolving().getTotalOrbAmount(item);
                    player.getBank(tab).delete(item, player.getBank(tab).getAmount(item));
                }
            }

            totals += (player.getMoneyInPouch() / 1_000_000);

            player.setMoneyInPouch(0);

            if (totals > 0) {
                player.getInventory().add(12855, totals);
                player.sendMessage("@blu@You received @red@" + Misc.insertCommasToNumber(totals) + "@blu@ Upgrade tokens for the removal of Billz exchange items");
            }
            player.setDeletedBillz(true);
        }

    }

}
