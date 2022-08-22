package org.necrotic.client;

import org.necrotic.client.cache.definition.MobDefinition;
import org.necrotic.client.entity.player.Player;
import org.necrotic.client.world.Model;

public class Partyhat {
	public String player_name;
	private Model model;
	public Player player;
	private int stream_currentOffset;
	private int anInt1702;
	private MobDefinition desc;
	private int team;
	private int[] equipment = new int[12];
	private int equipmentValueSetter;
	private int[] anIntArray1700 = new int[5];
	public boolean readyToUpdate = false;
	private final long aLong1718 = 0L;

	public Partyhat(String name, Player p, int[] equipment, int[] anIntArray1700) {
		this.player_name = name;
		this.player = p;
		this.equipment = equipment;
		this.anIntArray1700 = anIntArray1700;
	}

	public void setOffset(int offset) {
		this.stream_currentOffset = offset;
		;
	}


	public void setEquipmentValues(int array_index, int value) {
		this.equipment[array_index] = value;

	}

	public void setDesc(MobDefinition desc) {
		this.desc = desc;
	}

	public void setAnIntArray1700(int array_index, int anIntArray1700) {
		this.anIntArray1700[array_index] = anIntArray1700;
	}

	/**
	 * @return the player_name
	 */
	public String getPlayer_name() {
		return player_name;
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @return the player
	 */
	public Player getP() {
		return player;
	}

	/**
	 * @return the stream_currentOffset
	 */
	public int getStream_currentOffset() {
		return stream_currentOffset;
	}

	/**
	 * @return the equipment
	 */
	public int getEquipment(int array_index) {
		return equipment[array_index];
	}

	/**
	 * @return the equipmentValueSetter
	 */
	public int getEquipmentValueSetter() {
		return equipmentValueSetter;
	}

	/**
	 * @return the anIntArray1700
	 */
	public int getAnIntArray1700(int array_index) {
		return anIntArray1700[array_index];
	}

	public int randomColor(int random) {
		switch (random) {
			case 1:
				return 11200;
			case 2:
				return 43968;
			case 3:
				return 22464;
			case 4:
				return 51136;
			case 5:
				return 127;
			case 6:
				return 926;
		}
		return 0xffffff;
	}
}
