package com.ruse.world.content.combat.effect;

import com.ruse.model.container.impl.Equipment;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class EquipmentBonus {

	public static int[] helms = { 23585, 23584, 23583 }; // 11665 == melee, 11664 == range, 11663 == mage
	public static int gloves = 23582;
	public static int[] normRobes = { 23580, 23581 };
	public static int[] eRobes = { 23586, 23587 };

	public static boolean voidElite(Player player) {
		if (wearingVoid(player) && (player.checkItem(Equipment.BODY_SLOT, eRobes[0])
				&& player.checkItem(Equipment.LEG_SLOT, eRobes[1]))) {
			return true;
		}
		return false;
	}

	public static boolean voidRange(Player player) {
		if (wearingVoid(player) && player.checkItem(Equipment.HEAD_SLOT, helms[1])) {
			return true;
		}
		return false;
	}

	public static boolean voidMelee(Player player) {
		if (wearingVoid(player) && player.checkItem(Equipment.HEAD_SLOT, helms[0])) {
			return true;
		}
		return false;
	}

	public static boolean voidMage(Player player) {
		if (wearingVoid(player) && player.checkItem(Equipment.HEAD_SLOT, helms[2])) {
			return true;
		}
		return false;
	}

	public static boolean wearingVoid(Player player) {
		boolean hasHelm = false;
		int correctEquipment = 0;
		if (player.checkItem(Equipment.BODY_SLOT, eRobes[0])
				|| player.checkItem(Equipment.BODY_SLOT, normRobes[0])) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.LEG_SLOT, eRobes[1])
				|| player.checkItem(Equipment.LEG_SLOT, normRobes[1])) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.HEAD_SLOT, helms[0])
				|| player.checkItem(Equipment.HEAD_SLOT, helms[1]) || player.checkItem(Equipment.HEAD_SLOT, helms[2])) {
			hasHelm = true;
			correctEquipment++;
		}
		if (player.checkItem(Equipment.HANDS_SLOT, gloves)) {
			correctEquipment++;
		}

		if (correctEquipment > 3 && hasHelm) {
			return true;
		}
		return false;
	}

}
