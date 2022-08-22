package org.necrotic.client;

/**
 * @author Stan Enum holding all possible data types to be packed into the cache
 */
public enum PackingTypes {

	MODELS(1, "Models"), ANIMATIONS(2, "Animations"), SOUNDS(3, "Sounds"), MAPS(4, "Maps");

	private int index;
	private String type;

	/**
	 * @param cacheIndex
	 * @param cacheType
	 */
	PackingTypes(int cacheIndex, String cacheType) {
		this.index = cacheIndex;
		this.type = cacheType;
	}

	/**
	 * @return the cache index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the cache type
	 */
	public String getType() {
		return type;
	}

}
