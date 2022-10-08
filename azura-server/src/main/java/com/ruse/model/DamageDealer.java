package com.ruse.model;

import com.ruse.world.entity.impl.player.Player;

public class DamageDealer {

	public DamageDealer(Player p, long damage) {
		this.p = p;
		this.damage = damage;
	}

	private Player p;
	private long damage;

	public Player getPlayer() {
		return this.p;
	}

	public long getDamage() {
		return this.damage;
	}
}