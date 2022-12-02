package com.ruse.world.content;

import com.ruse.model.container.impl.Equipment;
import com.ruse.world.content.combat.magic.CombatSpells;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum AutoCastSpell {
    STAFF1(17017, CombatSpells.BATTLESTAFF),
    STAFF2(5096, CombatSpells.DARKSTAFF),
    STAFF3(13641, CombatSpells.AVERNICBLAST),
    STAFF4(14377, CombatSpells.SORCEBLAST),
    STAFF5(14924, CombatSpells.ICEYBLAST),
    STAFF6(5095, CombatSpells.SKOLLSTAFF),
    STAFF7(6936, CombatSpells.VIXIESTAFF),
    STAFF8(8809, CombatSpells.PURPSTAFF),
    STAFF9(17600, CombatSpells.GIANTSTAFF),
    STAFF10(7640, CombatSpells.MOONSTAFF),
    STAFF11(18629, CombatSpells.LMOONSTAFF),
    STAFF12(8089, CombatSpells.MAXSTAFF),

    STAFF13(8412, CombatSpells.GMSTAFF),
    STAFF1312(23786, CombatSpells.GMSTAFF),
    STAFF13121(23818, CombatSpells.GMSTAFF),
    STAFF14(17011, CombatSpells.SANGSTAFF2),
    STAFF15(17013, CombatSpells.SANGGSTAFF),
    STAFF16(22114, CombatSpells.SANGGSTAFF),
    STAFF17(23220, CombatSpells.SANGGSTAFF),
    STAFF1172(23663, CombatSpells.ASTROGUN),
    STAFF117(23664, CombatSpells.SANGGSTAFF),
    STAFF18(23373, CombatSpells.SANGGSTAFF),
    STAFF19(13556, CombatSpells.SANGGSTAFF),
    STAFF20(16249, CombatSpells.BUUSTAFF),
    STAFF21(22092, CombatSpells.ARROWSTAFF),
    STAFF22(3739, CombatSpells.ARROWSTAFF),
    STAFF23(17712, CombatSpells.FLAMETHROWER),
    STAFF24(17704, CombatSpells.ASTROGUN),
    STAFF25(18356, CombatSpells.BATTLESTAFF),
    STAFF26(19331, CombatSpells.FROZEN),
    STAFF27(13640, CombatSpells.FIREG),

    STAFF28(23567, CombatSpells.SANGGSTAFF),

    STAFF29(23589, CombatSpells.SANGSTAFF2),
    STAFF30(23592, CombatSpells.SANGSTAFF2),

    STAFF31(23597, CombatSpells.ICE_BARRAGE1),

   // STAFF312(23832, CombatSpells.RIFLE),

    STAFF32(23638, CombatSpells.ICE_BARRAGE1),
    STAFF33(23639, CombatSpells.ICE_BARRAGE1),

    STAFF331(23842, CombatSpells.ICE_BARRAGE1),

    ;

    private int itemId;
    private CombatSpells spell;

    public static CombatSpells getAutoCastSpell(Player player) {
        for (AutoCastSpell d : AutoCastSpell.values()) {
            if (player.getEquipment().get(Equipment.WEAPON_SLOT).getId() == d.getItemId()) {
                return d.getSpell();
            }
        }
        return null;
    }


    public static AutoCastSpell getAutoCast(Player player) {
        for (AutoCastSpell d : AutoCastSpell.values()) {
            if (player.getEquipment().get(Equipment.WEAPON_SLOT).getId() == d.getItemId()) {
                return d;
            }
        }
        return null;
    }

}
