package com.ruse.model.container.impl;

import com.ruse.model.Item;
import com.ruse.model.container.ItemContainer;
import com.ruse.model.container.StackType;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.world.entity.impl.player.Player;

/**
 * Represents a player's equipment item container.
 * 
 * @author relex lawl
 */

public class Cosmetics extends ItemContainer {

	/**
	 * The Equipment constructor.
	 *
	 * @param player The player who's equipment is being represented.
	 */
	public Cosmetics(Player player) {
		super(player);
	}

	@Override
	public int capacity() {
		return 14;
	}

	@Override
	public StackType stackType() {
		return StackType.DEFAULT;
	}

	@Override
	public ItemContainer refreshItems() {
		getPlayer().getPacketSender().sendItemContainer(this, COSMETIC_INVENTORY_INTERFACE_ID);
		return this;
	}

	@Override
	public Cosmetics full() {
		return this;
	}

	/**
	 * The equipment inventory interface id.
	 */
	public static final int COSMETIC_INVENTORY_INTERFACE_ID = 18833;

	/**
	 * The helmet slot.
	 */
	public static final int COSMETIC_HEAD_SLOT = 0;

	/**
	 * The cape slot.
	 */
	public static final int COSMETIC_CAPE_SLOT = 1;

	/**
	 * The amulet slot.
	 */
	public static final int COSMETIC_AMULET_SLOT = 2;

	/**
	 * The weapon slot.
	 */
	public static final int COSMETIC_WEAPON_SLOT = 3;

	/**
	 * The chest slot.
	 */
	public static final int COSMETIC_BODY_SLOT = 4;

	/**
	 * The shield slot.
	 */
	public static final int COSMETIC_SHIELD_SLOT = 5;

	/**
	 * The bottoms slot.
	 */
	public static final int COSMETIC_LEG_SLOT = 7;

	/**
	 * The gloves slot.
	 */
	public static final int COSMETIC_HANDS_SLOT = 9;

	/**
	 * The boots slot.
	 */
	public static final int COSMETIC_FEET_SLOT = 10;

	/**
	 * The rings slot.
	 */
	public static final int COSMETIC_RING_SLOT = 12;

	/**
	 * The arrows slot.
	 */
	public static final int COSMETIC_AMMUNITION_SLOT = 13;
	public static final int COSMETIC_AURA_SLOT = 6;

	public boolean wearingNexAmours() {
		int head = getPlayer().getEquipment().getItems()[COSMETIC_HEAD_SLOT].getId();
		int body = getPlayer().getEquipment().getItems()[COSMETIC_BODY_SLOT].getId();
		int legs = getPlayer().getEquipment().getItems()[COSMETIC_LEG_SLOT].getId();
		boolean torva = head == 14008 && body == 14009 && legs == 14010;
		boolean pernix = head == 14011 && body == 14012 && legs == 14013;
		boolean virtus = head == 14014 && body == 14015 && legs == 14016;
		return torva || pernix || virtus;
	}

	public boolean wearingHalberd() {
		ItemDefinition def = ItemDefinition.forId(getPlayer().getEquipment().getItems()[Cosmetics.COSMETIC_WEAPON_SLOT].getId());
		return def != null && def.getName().toLowerCase().endsWith("halberd");
	}

	public boolean properEquipmentForWilderness() {
		int count = 0;
		for (Item item : getValidItems()) {
			if (item != null && item.tradeable())
				count++;
		}
		return count >= 3;
	}

	/**
	 * Gets the amount of item of a type a player has, for example, gets how many
	 * Zamorak items a player is wearing for GWD
	 * 
	 * @param p The player
	 * @param s The item type to search for
	 * @return The amount of item with the type that was found
	 */
	public static int getItemCount(Player p, String s, boolean inventory) {
		int count = 0;
		for (Item t : p.getEquipment().getItems()) {
			if (t == null || t.getId() < 1 || t.getAmount() < 1)
				continue;
			if (t.getDefinition().getName().toLowerCase().contains(s.toLowerCase()))
				count++;
		}
		if (inventory)
			for (Item t : p.getInventory().getItems()) {
				if (t == null || t.getId() < 1 || t.getAmount() < 1)
					continue;
				if (t.getDefinition().getName().toLowerCase().contains(s.toLowerCase()))
					count++;
			}
		return count;
	}
}
