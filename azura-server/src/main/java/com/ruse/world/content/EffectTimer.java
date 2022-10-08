package com.ruse.world.content;

public enum EffectTimer {
	
	X2_DR_1HR(15355),
	X2_DDR_1HR(15356),
	X2_DMG_1HR(15357),
	X2_DR_30MIN(15358),
	X2_DMG_30MIN(15359),


	MELEE_HEART(23491),
	RANGED_HEART(23492),
	MAGIC_HEART(23493),
	CELESTIAL_ZONE(23736);
	
	EffectTimer(int clientSprite) {
		this.clientSprite = clientSprite;
	}
	
	private int clientSprite;
	
	public int getClientSprite() {
		return clientSprite;
	}
	
	public void setClientSprite(int sprite) {
		this.clientSprite = sprite;
	}
}
