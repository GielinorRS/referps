package org.necrotic.client.world;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)

public final class PlainTile {

	public PlainTile(int i, int j, int k, int l, int i1, int j1, boolean flag, boolean tex) {
		aBoolean721 = true;
		anInt716 = i;
		anInt717 = j;
		neColor = k;
		anInt719 = l;
		texture = i1;
		anInt722 = j1;
		aBoolean721 = flag;
		textured = tex;
	}

	public final int anInt716;
	public final int anInt717;
	public final int neColor;
	public final int anInt719;
	public final int texture;
	public boolean aBoolean721;
	public final int anInt722;
	public final boolean textured;
}
