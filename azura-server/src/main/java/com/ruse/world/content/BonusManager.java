package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.model.Prayerbook;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.Maxhits;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.entity.impl.player.Player;

import java.text.DecimalFormat;

public class BonusManager {
    public static final String[] BONUS_NAMES = new String[]{"Stab", "Slash", "Crush", "Magic", "Ranged", "Stab",
            "Slash", "Crush", "Magic", "Ranged", "Summoning", "Absorb Melee", "Absorb Magic", "Absorb Ranged",
            "Melee", "Ranged", "Magic", "Magic"};

    public static void update(Player player) {
        double[] bonuses = new double[18];
        for (Item item : player.getEquipment().getItems()) {
            ItemDefinition definition = ItemDefinition.forId(item.getId());
            for (int i = 0; i < definition.getBonus().length; i++) {
                bonuses[i] += definition.getBonus()[i];
            }
        }
        for (int i = 0; i < STRING_ID.length; i++) {
            if (i <= 4) {
                player.getBonusManager().attackBonus[i] = bonuses[i];
            } else if (i <= 9) {
                int index = i - 5;
                player.getBonusManager().defenceBonus[index] = bonuses[i];
                /*
                 * if(player.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 11283
                 * && !STRING_ID[i][1].contains("Magic")) { if(player.getDfsCharges() > 0) {
                 * player.getBonusManager().defenceBonus[index] += player.getDfsCharges();
                 * bonuses[i] += player.getDfsCharges(); } }
                 */
            } else if (i >= 14 && i <= 17 && i != 16) {
                int index = i - 14;
                player.getBonusManager().otherBonus[index] = bonuses[i];
            }
            player.getPacketSender().sendString(21190 + i,
                    BONUS_NAMES[i] + ": @whi@" + ((int) bonuses[i] > 0 ? "+" + (int) bonuses[i] : (int) bonuses[i]));
            //	player.getPacketSender().sendString(Integer.valueOf(STRING_ID[i][0]), STRING_ID[i][1] + ": " + bonuses[i]);
        }


        player.getPacketSender().sendString(21206, BONUS_NAMES[17] + ": @whi@" + ((int) bonuses[17] > 0 ? "+" + (int) bonuses[17] : (int) bonuses[17]));
        player.getPacketSender().sendString(21207, "");

        player.getPacketSender().sendString(21200, "Melee: @whi@"
                + (int) Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.MELEE, false)
                +
                (Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.MELEE, true) != (int) Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.MELEE, false)
                        ? "% (Slay: " + (int) Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.MELEE, true) + "%)" : "%"));
        player.getPacketSender().sendString(21201, "Ranged: @whi@"
                + (int) Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.RANGED, false)
		+
						(Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.RANGED, true) != (int) Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.RANGED, false)
				? "% (Slay: " + (int) Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.RANGED, true) + "%)" : "%"));

        player.getPacketSender().sendString(21202, "Magic: @whi@"
                + (int) Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.MAGIC, false)
		+
						(Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.MAGIC, true) != (int) Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.MAGIC, false)
				? "% (Slay: " + (int) Maxhits.getMaxHit(player, Maxhits.baseNPC, CombatType.MAGIC, true) + "%)" : "%"));

        player.getPacketSender().sendString(21203, "Overall: @whi@x"
                + df.format(Maxhits.getDamageBoost(player, Maxhits.baseNPC, CombatType.MAGIC)));


        player.getPacketSender().sendString(66106, "Drop Rate Bonus: @whi@" + NPCDrops.dropRateBoost(player));

        player.getPacketSender().sendString(66108, "Melee Maxhit: @whi@" + (Maxhits.melee(player, player) / 10));
        player.getPacketSender().sendString(66109, "Ranged Maxhit: @whi@" + (Maxhits.ranged(player, player) / 10));
        player.getPacketSender().sendString(66110, "Magic Maxhit: @whi@" + (Maxhits.magic(player, player) / 10));

    }
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public double[] getAttackBonus() {
        return attackBonus;
    }

    public double[] getDefenceBonus() {
        return defenceBonus;
    }

    public double[] getOtherBonus() {
        return otherBonus;
    }

    private double[] attackBonus = new double[5];

    private double[] defenceBonus = new double[9];

    private double[] otherBonus = new double[4];

    private static final String[][] STRING_ID = {{"1675", "Stab"}, // 0
            {"1676", "Slash"}, // 1
            {"1677", "Crush"}, // 2
            {"1678", "Magic"}, // 3
            {"1679", "Range"}, // 4

            {"1680", "Stab"}, // 5
            {"1681", "Slash"}, // 6
            {"1682", "Crush"}, // 7
            {"1683", "Magic"}, // 8
            {"1684", "Range"}, // 9
            {"15115", "Summoning"}, // 10
            {"15116", "Absorb Melee"}, // 11
            {"15117", "Absorb Magic"}, // 12
            {"15118", "Absorb Ranged"}, // 13

            {"1686", "Strength"}, // 14
            {"15119", "Ranged Strength"}, // 15
            {"1687", "Prayer"}, // 16
            {"15120", "Magic Damage"} // 17
    };

    public static final int ATTACK_STAB = 0, ATTACK_SLASH = 1, ATTACK_CRUSH = 2, ATTACK_MAGIC = 3, ATTACK_RANGE = 4,

    DEFENCE_STAB = 0, DEFENCE_SLASH = 1, DEFENCE_CRUSH = 2, DEFENCE_MAGIC = 3, DEFENCE_RANGE = 4,
            DEFENCE_SUMMONING = 5, ABSORB_MELEE = 6, ABSORB_MAGIC = 7, ABSORB_RANGED = 8,

    BONUS_STRENGTH = 0, RANGED_STRENGTH = 1, BONUS_PRAYER = 2, MAGIC_DAMAGE = 3;

    /**
     * CURSES
     **/

    public static void sendCurseBonuses(final Player p) {
        if (p.getPrayerbook() == Prayerbook.CURSES) {
            sendAttackBonus(p);
            sendDefenceBonus(p);
            sendStrengthBonus(p);
            sendRangedBonus(p);
            sendMagicBonus(p);
        }
    }

    public static void sendAttackBonus(Player p) {
        double boost = p.getLeechedBonuses()[0];
        int bonus = 0;
        if (p.getCurseActive()[CurseHandler.LEECH_ATTACK]) {
            bonus = 5;
        } else if (p.getCurseActive()[CurseHandler.TURMOIL])
            bonus = 15;
        bonus += boost;
        if (bonus < -25)
            bonus = -25;
        p.getPacketSender().sendString(690, "" + getColor(bonus) + "" + bonus + "%");
    }

    public static void sendRangedBonus(Player p) {
        double boost = p.getLeechedBonuses()[4];
        int bonus = 0;
        if (p.getCurseActive()[CurseHandler.LEECH_RANGED]) {
            bonus = 5;
        } else if (p.getCurseActive()[CurseHandler.TURMOIL])
            bonus = 23;
        bonus += boost;
        if (bonus < -25)
            bonus = -25;
        p.getPacketSender().sendString(693, "" + getColor(bonus) + "" + bonus + "%");
    }

    public static void sendMagicBonus(Player p) {
        double boost = p.getLeechedBonuses()[6];
        int bonus = 0;
        if (p.getCurseActive()[CurseHandler.LEECH_MAGIC]) {
            bonus = 5;
        } else if (p.getCurseActive()[CurseHandler.TURMOIL])
            bonus = 23;
        bonus += boost;
        if (bonus < -25)
            bonus = -25;
        p.getPacketSender().sendString(694, "" + getColor(bonus) + "" + bonus + "%");
    }

    public static void sendDefenceBonus(Player p) {
        double boost = p.getLeechedBonuses()[1];
        int bonus = 0;
        if (p.getCurseActive()[CurseHandler.LEECH_DEFENCE])
            bonus = 5;
        else if (p.getCurseActive()[CurseHandler.TURMOIL])
            bonus = 15;
        bonus += boost;
        if (bonus < -25)
            bonus = -25;
        p.getPacketSender().sendString(692, "" + getColor(bonus) + "" + bonus + "%");
    }

    public static void sendStrengthBonus(Player p) {
        double boost = p.getLeechedBonuses()[2];
        int bonus = 0;
        if (p.getCurseActive()[CurseHandler.LEECH_STRENGTH])
            bonus = 5;
        else if (p.getCurseActive()[CurseHandler.TURMOIL])
            bonus = 23;
        bonus += boost;
        if (bonus < -25)
            bonus = -25;
        p.getPacketSender().sendString(691, "" + getColor(bonus) + "" + bonus + "%");
    }

    public static String getColor(int i) {
        if (i > 0)
            return "@gre@+";
        if (i < 0)
            return "@red@";
        return "";
    }
}
