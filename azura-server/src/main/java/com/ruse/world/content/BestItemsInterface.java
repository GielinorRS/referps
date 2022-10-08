package com.ruse.world.content;

import com.ruse.model.container.impl.Equipment;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BestItemsInterface {
    private static String[] text = new String[]{"Stab", "Slash", "Crush", "Magic", "Range", "Stab", "Slash", "Crush",
            "Magic", "Range", "Strength", "Ranged Str", "Magic Damage"};
    public static boolean buttonClicked(Player player, int buttonId) {
        if (buttonId >= 100010 && buttonId <= 100014) {
            int index = (buttonId - 100010);
            openInterface(player, index);
            return true;
        } else if (buttonId >= 100016 && buttonId <= 100020) {
            int index = (buttonId - 100016) + 5;
            openInterface(player, index);
            return true;
        } else if (buttonId == 100022) {
            openInterface(player, 14);
            return true;
        } else if (buttonId == 100023) {
            openInterface(player, 15);
            return true;
        } else if (buttonId == 100024) {
            openInterface(player, 17);
            return true;
        }
        if (buttonId >= 100601 && buttonId <= 100612) {
            int index = buttonId-100601;
            if (index >= 11)
                index++;
            player.setSelectedEquipmentType(ItemDefinition.EquipmentType.forSlot(index));
            openInterface(player, 0);
            return true;
        }
        return false;
    }

    public static void openInterface(Player player, int bonus) {
        player.setDrInterface(false);


        player.getPacketSender().sendString(100005, "Item Stats");
        player.getPacketSender().sendString(100009, "Attack Bonuses");
        player.getPacketSender().sendString(100015, "Defence Bonuses");
        player.getPacketSender().sendString(100021, "Other Bonuses");

        for (int i = 0; i < 5; i++) {
            player.getPacketSender().sendString(100010 + i, (bonus == i ? "@whi@Check " : "Check ") + text[i]);
        }
        for (int i = 5; i < 10; i++) {
            player.getPacketSender().sendString(100011 + i, (bonus == i ? "@whi@Check " : "Check ") + text[i]);
        }
        player.getPacketSender().sendString(100022, (bonus == 14 ? "@whi@Check " : "Check ") + text[10]);
        player.getPacketSender().sendString(100023, (bonus == 15 ? "@whi@Check " : "Check ") + text[11]);
        player.getPacketSender().sendString(100024, (bonus == 17 ? "@whi@Check " : "Check ") + text[12]);

        List<ItemDefinition> objects = new ArrayList<>();

        for (ItemDefinition i : ItemDefinition.getDefinitions()) {
            if (i != null) {
                if (!i.isNoted() && i.getBonus()[bonus] > 0 && !itemsToIgnore.contains(i.getId())
                    /*&& i.getEquipmentSlot() == Equipment.AURA_SLOT*/)

                    objects.add(i);
            }
        }

        Collections.sort(objects, (p, p1) -> {
            if (p.getBonus()[bonus] == p1.getBonus()[bonus]) {
                return 0;
            } else if (p.getBonus()[bonus] > p1.getBonus()[bonus]) {
                return -1;
            } else {
                return 1;
            }
        });

        objects = filtered(player.getSelectedEquipmentType(), objects);

        int interId = 100102;
        int size = (objects.size() >= 100 ? 100 : objects.size());
        for (int i = 0; i < size; i++) {
            player.getPacketSender().sendString(interId++,
                    "" + objects.get(i).getBonus()[bonus]);
            player.getPacketSender().sendString(interId++, "" + objects.get(i).getName());
            player.getPacketSender().sendItemOnInterface(interId++, objects.get(i).getId(), 1);
            interId++;
        }
        player.getPacketSender().sendInterface(100000);

        player.getPacketSender().setScrollBar(100050, size * 40);

    }

    private static List<ItemDefinition> filtered(ItemDefinition.EquipmentType equipmentType, List<ItemDefinition> unfiltered) {
        List<ItemDefinition> toReturn = new ArrayList<>();
        for (ItemDefinition item : unfiltered) {
            int id = item.getId();
            ItemDefinition.EquipmentType itemType = ItemDefinition.forId(id).getEquipmentType();
            if (itemType.getSlot() == equipmentType.getSlot()) {
                toReturn.add(item);
            }
        }
        return toReturn;
    }

    public static List<Integer> itemsToIgnore = new ArrayList() {
        {
            add(23716);
            add(23717);
            add(23718);



            add(23663);
            add(23664);

            //customs
            add(23535);
            add(21613);
            add(23513);
            add(8001);
            add(5010);
            add(23455);
            add(23474);
            add(22089);
            add(23373);

            //end

            add(13801);


            add(5424);
            add(9253);
            add(22071);
            add(22076);
            add(21604);
            add(17694);
            add(17694);
            add(17694);
            add(17694);
            add(17694);
            add(17694);
            add(17694);
            add(17694);
            add(17694);
            add(17694);


            add(17694);
            add(17696);
            add(21603);
            add(5423);
            add(5432);
            add(7014);
            add(19800);
            add(19802);
            add(20060);
            add(20062);
            add(20063);
            add(20073);
            add(7642);
            add(17678);
            add(7643);
            add(20533);
            add(20551);
            add(20552);
            add(20558);
            add(11179);
            add(11181);
            add(11182);
            add(11183);
            add(11184);
            add(11759);
            add(11762);
            add(5594);

            add(12285);

            add(21780);
            add(21605);
            add(21602);
            add(21601);
            add(21600);
            add(22093);
            add(21606);
            add(15219);
            add(1485);
            add(22073);
            /*add(14055);
            add(14054);
            add(14053);
            add(14052);
            add(14051);
            add(14050);*/

            add(23014);
            add(23015);
            add(23016);
            add(23017);
            add(23013);
            add(23000);


            add(23597);
            add(23594);
            add(23595);
            add(23596);
            add(23619);
            add(23620);
            add(23621);
            add(23838);
            add(23719);
        }
    };


}