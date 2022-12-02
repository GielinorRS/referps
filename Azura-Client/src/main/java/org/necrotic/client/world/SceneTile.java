package org.necrotic.client.world;

import org.necrotic.client.cache.node.Node;

final class SceneTile extends Node {

	boolean visible;
	boolean rendersVisible;
	boolean aBoolean1324;
	SceneTile aClass30_Sub3_1329;
	ShapedTile aClass40_1312;
	PlainTile aClass43_1311;
	int plane;
	final int tileX;
	final int tileY;
	final int originalPlane;
	int anInt1317;
	int anInt1320;
	int anInt1321;
	int anInt1325;
	int anInt1326;
	int anInt1327;
	int anInt1328;
	final int[] anIntArray1319;
	Object1 obj1;
	Object2 obj2;
	Object3 obj3;
	Object4 obj4;
	final Object5[] obj5Array;

	SceneTile(int i, int j, int k) {
		obj5Array = new Object5[5];
		anIntArray1319 = new int[5];
		originalPlane = plane = i;
		tileX = j;
		tileY = k;
	}

}