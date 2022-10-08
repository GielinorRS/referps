package com.ruse.world.content.instanceMananger;

import com.ruse.model.Position;
import com.ruse.model.RegionInstance.RegionInstanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InstanceData {

	CHARMANDER(1023, "Dark Bloodveld", 1500),
	BULBASAUR(1233, "Rusted Gargoyle", 2000),
	SQUIRTLE(1234, "Crazy Witch", 1800),
	THE_MUMMY( 13747, "Ice Demon", 2500),
	PREDATOR( 12343, "Predator", 1400),
	CYANTRIX(12886, "Cyantrix", 2500),
	BULWARK(10103, "Bulwark", 2500),
	//SHADOW_WARRIOR(606, "Shadow Warrior", 1500),
	//COL_WARRIOR(607, "Col Warrior", 1500),
	LORD(603, "Lord", 1500),
	FRACTITE_DEMON(12843, "Fractite Demon", 3000),
	JOYX_DRAGON(53, "Joyx Dragon", 2500),
	MAGE_BEAST(8018, "Mage Beast", 1400),
	TROLL_KING( 13635, "Troll King", 2500),
	AVATAR_TITAN(8008, "Avatar Titan", 2000),
	ANGEL_LUGIA(3308, "Angel Lugia", 3000),
	LUCIEN(3117, "Lucien", 4400),
	HERCULES( 201, "Hercules", 1500),
	SATAN(202, "Satan" , 1500),
	ZEUS(203, "Zeus", 1500),
	GROUDON( 8010, "Groudon", 2000),
	FENRIR(9810, "Fenrir", 1200),
	BORK(7134, "Bork", 3200),
	FRIEZA(252, "Frieza", 1300),
	PERFECT_CELL( 449, "Perfect Cell", 1300),
	SUPER_BUU(452, "Super Buu", 1300),
	SCARLET_FALCON(2949, "Scarlet Falcon", 4000),
	HERBAL_ROGUE( 2342, "Herbal Rogue", 4500),
	AZURE_BEAST(3831, "Azure Beast", 4200),
	JOKER(185, "Joker", 1400),
	CRYSTAL_QUEEN(6430, "Crystal Queen", 1700),

	FACELESS_ASSASSIN(10007, "Faceless Assassin", 1100),
	LOTUS_WARRIOR(10008, "Lotus Warrior", 1100),
	SHADOW_HUNTER(10009, "Shadow Hunter", 1100),

	THUNDER_DEMON(10032, "Avianse", 1100),

	;

	private int npcId;
	private String name;
	private int zoom;
}
