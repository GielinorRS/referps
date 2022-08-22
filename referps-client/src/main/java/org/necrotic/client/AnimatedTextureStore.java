package org.necrotic.client;

public enum AnimatedTextureStore {
	WATER_DROPLETS(17, 2), WATER(1, 1), WATFER(24, 1), MAGIC_TREE_STARS(36, 2), ROOF(50, 2), CUSTOM(51, 2), CUSTOM1(52, 2), CUSTOM2(53, 2), CUSTOM3(54, 2), CUSTOM4(55, 2), CUSTOM5(56, 2), CUSTOM6(57, 2), CUSTOM7(58, 2), CUSTOM8(59, 1), CUSTOM9(60, 1), CUSTOM10(61, 2), CUSTOM11(62, 2), CUSTOM12(63, 2), CUSTOM13(64, 2), CUSTOM14(65, 2), CUSTOM15(66, 2), CUSTOM16(67, 2), CUSTOM17(68, 3), CUSTOM18(69, 2), CUSTOM19(70, 4), CUSTOM20(71, 2), CUSTOM21(72, 2), CUSTOM22(73, 1), CUSTOM23(74, 2), CUSTOM24(75, 2), CUSTOM25(76, 2), CUSTOM26(77, 2), CUSTOM27(78, 2), CUSTOM28(79, 2), CUSTOM29(85, 2), CUSTOM31(80, 2), CUSTOM32(81, 2), CUSTOM33(82, 2), CUSTOM34(83, 2), CUSTOM35(84, 2), CUSTOM36(95, 2), IDK(34, 2), FIRECAPE(40, 2), SOULAND(96, 2), SOUL(97, 2), TEST(91, 2), TEST2(87, 1), SEVNITYNUNE(79, 2);

	private final int material_id;
	private final int speed;

	private AnimatedTextureStore(int material_id, int speed) {
		this.material_id = material_id;
		this.speed = speed;
	}

	public int getId() {
		return this.material_id;
	}

	public int getSpeed() {
		return this.speed;
	}
}
