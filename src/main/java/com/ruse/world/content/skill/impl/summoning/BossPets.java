package com.ruse.world.content.skill.impl.summoning;

import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class BossPets {

	public enum BossPet {
		// PETS
		BROWN_CAT(770, 770, 1563),
		BLACK_CAT(771, 771, 1564),
		WHITE_CAT(769, 769, 1562),

		DOG(6960, 6960, 12514),
		TERRIER(6958, 6958, 12512),
		BULLDOG(6968, 6968, 12522),
		DALMATION(6964, 6964, 12518),
		// UPGRADABLE PETS
		SCORP_PET(271, 271, 456),
		UNICORN_PET(133, 133, 457),
		GRIZZLY_PET(105, 105, 458),

		//
		WOLF_PET(4414, 4414, 459),
		GORILLA_PET(3001, 3001, 460),
		MARIO_PET(1892, 1892, 5508),
		DONKEY_KONG_PET(1894, 1894, 5506),
		VEGETA_PET(5001, 5001, 461),
 		YOSHI_PET(3377, 3377, 462),
		ZORBAK(1906, 1906, 11319),
		ADMINPET(1801, 1801, 11318),
		LUCIFER(9013, 9013, 22107),
		IRON(9016, 9016, 22116),

		ETERNAL_PET(4969, 4969, 463),
		CHARIZARD_PET(1890, 1890, 5504),
		SONIC_PET(1893, 1893, 5507),
		SUPER_STAR_PET(1904, 1904, 5560),
		BUMBLEBEE(1902, 1902, 11317),
		DBZ(302, 302, 11314),
		EEARTHQUAKE(3309, 3309, 11315),

		VOTEPET(8802, 8802, 11316),
		GRINCH(189, 189, 5563),
		HOOKER(4444, 4444, 20511),
		FREEZA1(350, 350, 2947),
		FREEZA2(351, 351, 2948),
		FREEZA3(352, 352, 2949),
		SUPERCELL(928, 928, 6545),
		FATBUU(4000, 4000, 15279),
		SUPERBUU(4001, 4001, 15278),
		JOKERSPET(184, 184, 5074),
		ICEQUEENPET(6431, 6431, 14067),
		// ETERNAL_PET(4969, 4969, 463),
		//
		PETRACOON(6913, 6913, 12486), PETSQ(6919, 6919, 12490), PETMONKEY(6942, 6942, 12496),
		PETVULTURE(6945, 6945, 12498),
		FPKpet(1910, 1910, 11310),
		PET_CHAOS_ELEMENTAL(3200, 3033, 11995), PET_KING_BLACK_DRAGON(50, 3030, 11996),
		PET_GENERAL_GRAARDOR(6260, 3031, 11997), PET_TZTOK_JAD(2745, 3032, 11978),
		PET_CORPOREAL_BEAST(8133, 3034, 12001), PET_KREE_ARRA(6222, 3035, 12002),
		PET_KRIL_TSUTSAROTH(6203, 3036, 12003), PET_COMMANDER_ZILYANA(6247, 3037, 12004),
		PET_DAGANNOTH_SUPREME(2881, 3038, 12005), PET_DAGANNOTH_PRIME(2882, 3039, 12006),
		PET_DAGANNOTH_REX(2883, 3040, 11990), PET_FROST_DRAGON(51, 3047, 11991), PET_TORMENTED_DEMON(8349, 3048, 11992),
		PET_KALPHITE_QUEEN(1158, 3050, 11993), PET_SLASH_BASH(2060, 3051, 11994), PET_PHOENIX(8549, 3052, 11989),
		PET_BANDOS_AVATAR(4540, 3053, 11988), PET_NEX(13447, 3054, 11987), PET_JUNGLE_STRYKEWYRM(9467, 3055, 11986),
		PET_DESERT_STRYKEWYRM(9465, 3056, 11985), PET_ICE_STRYKEWYRM(9463, 3057, 11984),
		PET_GREEN_DRAGON(941, 3058, 11983), PET_BABY_BLUE_DRAGON(52, 3059, 11982), PET_BLUE_DRAGON(55, 3060, 11981),
		PET_BLACK_DRAGON(54, 3061, 11979),
		// customs below. No comma on last.
		PET_SKELETON_HELLHOUND(1575, 6302, 22014), PET_MAZE_GUARDIAN(3102, 6303, 22015),
		PET_SKELETON_WARLORD(6105, 6304, 22016), PET_PENGUIN(131, 6305, 22017), PET_DRUID(14, 6306, 22018),
		PET_MONKEY_GUARD(1455, 6307, 22019), PET_CLOCKWORK_CAT(3598, 6308, 22020), PET_JUBBLY_BIRD(3476, 6309, 22021),
		PET_DUST_DEVIL(1624, 6310, 22022), PET_CHINCHOMPA(5080, 6312, 22024), PET_GOLEM(1920, 6313, 22025),
		PET_CHAOS_DWARF(119, 6314, 22026), PET_SHARK(467, 6315, 22027), PET_GOBLIN_COOK(3413, 6316, 22028),
		PET_FIRE_ELEMENTAL(1019, 6317, 22029), PET_TREE_SPIRIT(655, 6318, 22030), PET_LEPRECHAUN(3021, 6319, 22031),
		PET_EVIL_CHICKEN(3375, 6320, 22032), PET_ABYSSAL_DEMON(1615, 6311, 22023), PET_ZULRAH_GREEN(2042, 6322, 22033),
		PET_ZULRAH_BLUE(2042, 6323, 22049), PET_ZULRAH_RED(2042, 6324, 22050), PET_WILDYWYRM(3334, 3062, 22055),

		EASTER_BUNNY(9021,23018),

		SPOOKY_BAT(9032,23102),
		SPOOKY_KRAKEN(9033,23103),

		RAMMUS_PET(9822, 23164),
		DEMON_PET(9819, 23161),
		GOLEM_PET(9821, 23163),
		DRAGON_PET(9820, 23162),

		FENRIR_PET(9830, 23408),
		GREEN_FENRIR_PET(9831, 23409),
		RED_FENRIR_PET(9832, 23410),
		ODIN_PET(9833, 23411),


		TURKEY(9861, 23300),
		TURKEY1(9862, 23301),
		TURKEY2(9863, 23302),
		TURKEY3(9864, 23303),
		TURKEY4(9865, 23304),
		TURKEY5(9866, 23305),
		TURKEY6(9867, 23306),
		TURKEY7(9868, 23309),


		SNOWMAN1(9872, 23382),
		SNOWMAN2(9873, 23383),
		SNOWMAN3(9874, 23384),
		SANTA(9875, 23385),
		SANTA1(9876, 23386),


		SARADOMIN(10002, 23658),
		ZAMORAK(10003, 23659),
		LEPRECHAUN(10004, 23661),

		BORK(10011, 23695),
		GOLEM(10012, 23697),
		EASTERBUNNY(10013, 23711),

		JINX(10121, 23726),

		MUSPAH(10024, 23830),
		SAM(10025, 23829),


		VORKI(28025, 23853),
		TARANIS(11873, 23855),
		HYDRA(28492, 23857),

		SILVERHAWK(10031, 23890),

		;

		BossPet(int npcId, int spawnNpcId, int itemId) {
			this.npcId = npcId;
			this.spawnNpcId = spawnNpcId;
			this.itemId = itemId;
		}
		BossPet(int npcId, int itemId) {
			this.npcId = npcId;
			this.spawnNpcId = npcId;
			this.itemId = itemId;
		}

		public int npcId, spawnNpcId, itemId;

		public static BossPet forId(int itemId) {
			for (BossPet p : BossPet.values()) {
				if (p != null && p.itemId == itemId) {
					return p;
				}
			}
			return null;
		}

		public static BossPet forSpawnId(int spawnNpcId) {
			for (BossPet p : BossPet.values()) {
				if (p != null && p.spawnNpcId == spawnNpcId) {
					return p;
				}
			}
			return null;
		}
	}

	public static boolean pickup(Player player, NPC npc) {
		BossPet pet = BossPet.forSpawnId(npc.getId());
		if (pet != null) {
			if (player.getInventory().getFreeSlots() < 1) {
				player.getPacketSender().sendMessage("You need a free inventory space to pick up a pet.");
				return false;
			}
			if (player.getSummoning().getFamiliar() != null
					&& player.getSummoning().getFamiliar().getSummonNpc() != null
					&& player.getSummoning().getFamiliar().getSummonNpc().isRegistered()) {
				if (player.getSummoning().getFamiliar().getSummonNpc().getId() == pet.spawnNpcId
						&& player.getSummoning().getFamiliar().getSummonNpc().getIndex() == npc.getIndex()) {
					player.getSummoning().unsummon(true, true);
					player.getPacketSender().sendMessage("You pick up your pet.");
					return true;
				} else {
					player.getPacketSender().sendMessage("This is not your pet to pick up.");
				}
			} else {
				player.getPacketSender().sendMessage("This is not your pet to pick up.");
			}
		}
		return false;
	}

}
