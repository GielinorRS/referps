package com.ruse.world.content;

import com.ruse.model.container.impl.Equipment;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.world.content.skill.impl.summoning.BossPets;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestDRInterface {

    private static String[] text = new String[]{"Head", "Body", "Legs", "Weapon", "Shield", "Amulet", "Cape", "Gloves",
            "Boots", "Ring", "Auras", "Pets", "Costumes"};

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
            openInterface(player, 10);
            return true;
        } else if (buttonId == 100023) {
            openInterface(player, 11);
            return true;
        } else if (buttonId == 100024) {
            openInterface(player, 12);
            return true;
        }
        return false;
    }

    private static int[] slots = new int[]{
            Equipment.HEAD_SLOT,
            Equipment.BODY_SLOT,
            Equipment.LEG_SLOT,
            Equipment.WEAPON_SLOT,
            Equipment.SHIELD_SLOT,
            Equipment.AMULET_SLOT,
            Equipment.CAPE_SLOT,
            Equipment.HANDS_SLOT,
            Equipment.FEET_SLOT,
            Equipment.RING_SLOT,
            Equipment.AURA_SLOT,
            Equipment.COSTUME_SLOT,
    };

    public static void openInterface(Player player, int bonus) {
        player.setDrInterface(true);

        player.getPacketSender().sendString(100005, "Drop Rate Items");

        player.getPacketSender().sendString(100009, "Gear");
        player.getPacketSender().sendString(100015, "Accessories");
        player.getPacketSender().sendString(100021, "Misc");
        for (int i = 0; i < 5; i++) {
            player.getPacketSender().sendString(100010 + i, (bonus == i ? "@whi@Check " : "Check ") + text[i]);
        }
        for (int i = 5; i < 10; i++) {
            player.getPacketSender().sendString(100011 + i, (bonus == i ? "@whi@Check " : "Check ") + text[i]);
        }
        player.getPacketSender().sendString(100022, (bonus == 10 ? "@whi@Check " : "Check ") + text[10]);
        player.getPacketSender().sendString(100023, (bonus == 11 ? "@whi@Check " : "Check ") + text[11]);
        player.getPacketSender().sendString(100024, (bonus == 12 ? "@whi@Check " : "Check ") + text[12]);

        ArrayList<int[]> objects = new ArrayList<>();

        if (bonus <= 10 || bonus == 12) {
            for (int[] i : CustomDropUtils.DRITEMS) {
                if (i != null) {
                    ItemDefinition def = ItemDefinition.forId(i[0]);
                    if (def != null) {
                        if (bonus <= 10 && def.getEquipmentSlot() != slots[ bonus]) {
                            continue;
                        }
                        if (bonus == 12 && def.getEquipmentSlot() != slots[ 11]) {
                            continue;
                        }
                        if (!itemsToIgnore.contains(i[0]))
                        objects.add(i);
                    }
                }
            }
        } else if (bonus == 11) {
            for (int[] i : CustomDropUtils.DRPETS) {
                if (i != null) {
                    BossPets.BossPet def = BossPets.BossPet.forSpawnId(i[0]);
                    if (def != null) {
                        if (!(def.itemId >= 23301 && def.itemId <= 23308))
                            objects.add(new int[]{def.itemId, i[1]});
                    }
                }
            }
        }

        Collections.sort(objects, (p, p1) -> {
            if (p[1] == p1[1]) {
                return 0;
            } else if (p[1] > p1[1]) {
                return -1;
            } else {
                return 1;
            }
        });

        int interId = 100101;
        int size = (objects.size() >= 100 ? 100 : objects.size() <= 6 ? 6 : objects.size());
        for (int i = 0; i < size; i++) {
            if (objects.size() > i) {
                player.getPacketSender().sendString(interId++, "#" + (i + 1));
                player.getPacketSender().sendString(interId++, "" + objects.get(i)[1]);
                player.getPacketSender().sendString(interId++, "" + ItemDefinition.forId(objects.get(i)[0]).getName());
                player.getPacketSender().sendItemOnInterface(interId++, objects.get(i)[0], 1);
            } else {
                player.getPacketSender().sendString(interId++, "");
                player.getPacketSender().sendString(interId++, "");
                player.getPacketSender().sendString(interId++, "");
                player.getPacketSender().sendItemOnInterface(interId++, -1, 1);
            }
        }
        player.getPacketSender().sendInterface(100000);

        int scroll = size * 40 + 1;

        player.getPacketSender().setScrollBar(100050, scroll);

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
            add(23832);
            add(23891);
            add(23839);
            add(23373);



            add(23597);
            add(23594);
            add(23595);
            add(23596);
            add(23619);
            add(23620);
            add(23621);
            add(23838);


        }
    };


}