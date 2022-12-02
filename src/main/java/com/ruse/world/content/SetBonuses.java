package com.ruse.world.content;

import com.ruse.world.content.combat.CombatType;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;

@Getter
public enum SetBonuses {

    LUCIFER(0, 1.05,22100, 22101, 22102),

    REAPER(0, 1.075,23501, 23502, 23503, 23504, 23505),

    MYSTIC(0, 1.10,23465, 23466, 23467),
    HELLISH(0, 1.10, 23468, 23469, 23470),
    MIDNIGHT(0, 1.10, 23471, 23472, 23473),

    LUNITE(0, 1.10, 23394, 23395, 23396),

    MYSTIC_U(0, 1.15,23525, 23526, 23527),
    HELLISH_U(0, 1.15,23528, 23529, 23530),
    MIDNIGHT_U(0, 1.15, 23531, 23532, 23533),


    HANTO(0, 1.15,  23561, 23562, 23563),
    TANZ(0, 1.15, 23720, 23721, 23722),

    CELESTIAL(0, 1.20,  23743, 23744, 23745),

    WISDOM(0, 1.20,  23860, 23861, 23862),
    VENG(0, 1.20,  23870, 23871, 23872),
    DESTINY(0, 1.20,  23876, 23877, 23878),

    ;

    SetBonuses(double dropRate, double damageBoost, int... items) {
        this.dropRate = dropRate;
        this.damageBoost = damageBoost;
        this.items = items;
    }

    public static SetBonuses forID(Player player) {
        for (SetBonuses setBonuses : SetBonuses.values()) {
            boolean contains = true;
            for (int wear : setBonuses.getItems()) {
                if (!player.getEquipment().contains(wear))
                    contains = false;
            }
            if (contains)
                return setBonuses;
        }
        return null;
    }


    public static double getDamageBoost(Player player) {
        SetBonuses bonuses = forID(player);
        if (bonuses == null)
            return 1;
        return bonuses.getDamageBoost();
    }

    private double dropRate;
    private double damageBoost;
    private int[] items;
}
