package org.necrotic.client;

public enum ModIcon {

	SUPPORT(832, 5), HELPER(832, 5),

	MODERATOR(828, 1), ADMINISTRATOR(829, 2), OWNER(830, 3), DEVELOPER(831, 4), CONTRIBUTOR(831, 6), REGULAR_DONATOR(832, 7), SUPER_DONATOR(833, 9), EXTREME_DONATOR(834, 11), SPONSOR_DONATOR(835, 12), YOUTUBER(837, 8);

	private final int icon, rights;

	private ModIcon(int icon, int rights) {
		this.icon = icon;
		this.rights = rights;
	}

	public int getIcon() {
		return icon;
	}

	public int getRights() {
		return rights;
	}

	/**
	 * @param spriteId
	 * @return ModIcon that holds same value as spriteId
	 * @author Crimson
	 * @since Jul 27, 2017
	 */
	public static ModIcon forSprite(int spriteId) {
		for (ModIcon mi : ModIcon.values()) {
			if (mi.getIcon() == spriteId) {
				return mi;
			}
		}
		return null;
	}

	/**
	 * @param rights
	 * @return ModIcon that holds same value as rights
	 * @author Crimson
	 * @since Jul 27, 2017
	 */
	public static ModIcon forRights(int rights) {
		for (ModIcon mi : ModIcon.values()) {
			if (mi.getRights() == rights) {
				return mi;
			}
		}
		return null;
	}

	/**
	 * @return the highest value of rights
	 * @author Crimson
	 * @since Jul 27, 2017
	 */
	public static final int getMaxRights() {
		int maxValue = 0;
		for (ModIcon m : ModIcon.values()) {
			if (m.getRights() > maxValue) {
				maxValue = m.getRights();
			}
		}
		maxValue += 1;
		return maxValue;
	}

}
