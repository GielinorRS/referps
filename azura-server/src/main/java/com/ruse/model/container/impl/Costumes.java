package com.ruse.model.container.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Costumes {

    ELF(23391, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT}, new int[]{23362, 23363, 23364, 23365, 23366}),
    GRINCH(23392, 0,  new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT}, new int[]{13025, 13027, 13023, 13029, 13031}),
    EVIL_SANTA(23393, 0,  new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT}, new int[]{23377, 23378, 23379, 23380, 23381}),

    LUNITE(23458, 10, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23356, 23357, 23358, 23359, 23360}),



    REAPER(23507, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23501, 23502, 23503, 23504, 23505}),

    GRANDMASTER(23509, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT},
            new int[]{23497, 23498, 23499}),


    SUPREME(23520, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{8100, 8101, 8102, 8103, 8104}),
    DARK_SUPREME(23522, 1, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{8105, 8106, 8107, 8108, 8109}),


    DARK_LORD(23554, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23548, 23549, 23550, 23551, 23552}),


    SHARK(23616, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23605, 23606, 23607, 23608, 23609}),

    SHADOW_SHARK(23618, 5, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23610, 23611, 23612, 23613, 23614}),


    ZAMORAK(23651, 25, new int[]{Equipment.HANDS_SLOT, Equipment.FEET_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HEAD_SLOT },
            new int[]{23645, 23646, 23647, 23648, 23649}),

    SARADOMIN(23653, 25,  new int[]{Equipment.HANDS_SLOT, Equipment.FEET_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HEAD_SLOT },
            new int[]{23622, 23623, 23624, 23625, 23626}),



    FACELESS_ASSASSIN(23699, 25, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23665, 23666, 23667, 23668, 23669}),

    DEATH_LOTUS(23701, 25, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23683, 23684, 23685, 23686, 23687}),

    SHADOW_HUNTER(23703, 25, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23572, 23573, 23574, 23575, 23576}),



    BATTLEGEAR(23772, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23760, 23761, 23762, 23763, 23764}),

    GODCRUSHER(23774, 5, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23765, 23766, 23767, 23768, 23769}),



    ARRAV(23809, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23795, 23796, 23797, 23798, 23799}),
    CURSED_ARRAV(23811, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT},
            new int[]{23802, 23803, 23804, 23805, 23806}),


    SACRIFICIAL(23834, 0, new int[]{Equipment.HEAD_SLOT, Equipment.BODY_SLOT, Equipment.LEG_SLOT, Equipment.HANDS_SLOT, Equipment.FEET_SLOT
            , Equipment.CAPE_SLOT},
            new int[]{23729, 23730, 23731, 23732, 23733, 23734}),

    ;
    private int itemId;
    private double damageBoost;
    private int[] costumeSlots;
    private int[] costumeItems;

    public static Costumes forID(int id) {
        for (Costumes c : Costumes.values()) {
            if (c.getItemId() == id)
                return c;
        }
        return null;
    }


    public static int getItem(int id, int slot) {
        Costumes costume = forID(id);
        if (costume == null)
            return -1;

        for (int i = 0; i < costume.getCostumeSlots().length; i++) {
            if (costume.getCostumeSlots()[i] == slot) {
                return costume.getCostumeItems()[i];
            }
        }
        return -1;
    }

}
