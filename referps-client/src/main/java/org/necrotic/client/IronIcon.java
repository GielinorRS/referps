package org.necrotic.client;

public enum IronIcon {

	ULTIMATE(839, 1), EXTREME(856, 1), NORMAL(840, 2);

	private final int icon, rights;

	private IronIcon(int icon, int rights) {
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
	 * @return IronIcon that holds same value as spriteId
	 * @author Crimson
	 * @since Jul 27, 2017
	 */
	public static IronIcon forSprite(int spriteId) {
		for (IronIcon mi : IronIcon.values()) {
			if (mi.getIcon() == spriteId) {
				return mi;
			}
		}
		return null;
	}

	/**
	 * @param rights
	 * @return IronIcon that holds same value as rights
	 * @author Crimson
	 * @since Jul 27, 2017
	 */
	public static IronIcon forRights(int rights) {
		for (IronIcon mi : IronIcon.values()) {
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
		for (IronIcon m : IronIcon.values()) {
			if (m.getRights() > maxValue) {
				maxValue = m.getRights();
			}
		}
		maxValue += 1;
		return maxValue;
	}

}
