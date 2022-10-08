package org.necrotic.client.graphics.rsinterface;
public class DamageDealer {

	public DamageDealer(String p, long damage) {
		this.p = p;
		this.damage = damage;
	}

	private String p;
	private long damage;

	public String getPlayer() {
		return this.p;
	}

	public long getDamage() {
		return this.damage;
	}
}