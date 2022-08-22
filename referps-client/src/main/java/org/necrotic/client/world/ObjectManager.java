package org.necrotic.client.world;

import org.necrotic.Configuration;
import org.necrotic.client.Class4;
import org.necrotic.client.Client;
import org.necrotic.client.cache.definition.FloorDefinitions;
import org.necrotic.client.cache.definition.ObjectDefinition;
import org.necrotic.client.cache.definition.OverLayFlo317;
import org.necrotic.client.cache.ondemand.OnDemandFetcher;
import org.necrotic.client.io.ByteBuffer;
import org.necrotic.client.renderable.Animable;
import org.necrotic.client.renderable.RenderableObject;

public final class ObjectManager {

	public static int currentPlane;
	public static int maximumPlane = 99;
	private static final int anIntArray137[] = {1, 0, -1, 0};
	private static final int anIntArray140[] = {16, 32, 64, 128};
	private static final int anIntArray144[] = {0, -1, 0, 1};
	private static final int anIntArray152[] = {1, 2, 4, 8};
	public static boolean lowDetail = true;

	private static int method170(int i, int j) {
		int k = i + j * 57;
		k = k << 13 ^ k;
		int l = k * (k * k * 15731 + 0xc0ae5) + 0x5208dd0d & 0x7fffffff;
		return l >> 19 & 0xff;
	}

	private static int method172(int i, int j) {
		int k = method176(i + 45365, j + 0x16713, 4) - 128 + (method176(i + 10294, j + 37821, 2) - 128 >> 1) + (method176(i, j, 1) - 128 >> 2);
		k = (int) (k * 0.29999999999999999D) + 35;

		if (k < 10) {
			k = 10;
		} else if (k > 60) {
			k = 60;
		}

		return k;
	}

	public static void method173(ByteBuffer stream, OnDemandFetcher class42_sub1) {
		label0:
		{
			int i = -1;
			do {
				int j = stream.getUSmart2();
				if (j == 0) {
					break label0;
				}
				i += j;
				ObjectDefinition class46 = ObjectDefinition.forID(i);
				class46.method574(class42_sub1);
				do {
					int k = stream.getSmart();
					if (k == 0) {
						break;
					}
					stream.getUnsignedByte();
				} while (true);
			} while (true);
		}
	}

	private static int method176(int i, int j, int k) {
		int l = i / k;
		int i1 = i & k - 1;
		int j1 = j / k;
		int k1 = j & k - 1;
		int l1 = method186(l, j1);
		int i2 = method186(l + 1, j1);
		int j2 = method186(l, j1 + 1);
		int k2 = method186(l + 1, j1 + 1);
		int l2 = method184(l1, i2, i1, k);
		int i3 = method184(j2, k2, i1, k);
		return method184(l2, i3, k1, k);
	}

	public static boolean method178(int i, int j) {
		ObjectDefinition class46 = ObjectDefinition.forID(i);
		if (j == 11) {
			j = 10;
		}
		if (j >= 5 && j <= 8) {
			j = 4;
		}
		return class46.method577(j);
	}

	private static int method184(int i, int j, int k, int l) {
		int i1 = 0x10000 - Rasterizer.COSINE[k * 1024 / l] >> 1;
		return (i * (0x10000 - i1) >> 16) + (j * i1 >> 16);
	}

	private static int method186(int i, int j) {
		int k = method170(i - 1, j - 1) + method170(i + 1, j - 1) + method170(i - 1, j + 1) + method170(i + 1, j + 1);
		int l = method170(i - 1, j) + method170(i + 1, j) + method170(i, j - 1) + method170(i, j + 1);
		int i1 = method170(i, j);
		return k / 16 + l / 8 + i1 / 4;
	}

	private static int method187(int i, int j) {
		if (i == -1) {
			return 0xbc614e;
		}
		j = j * (i & 0x7f) / 128;
		if (j < 2) {
			j = 2;
		} else if (j > 126) {
			j = 126;
		}
		return (i & 0xff80) + j;
	}

	public static void method188(SceneGraph worldController, int i, int j, int k, int l, CollisionMap class11, int ai[][][], int i1, int j1, int k1) {
		if (l == 4) {
			l = 0;
		}
		int l1 = ai[l][i1][j];
		int i2 = ai[l][i1 + 1][j];
		int j2 = ai[l][i1 + 1][j + 1];
		int k2 = ai[l][i1][j + 1];
		int l2 = l1 + i2 + j2 + k2 >> 2;
		ObjectDefinition class46 = ObjectDefinition.forID(j1);
		int i3 = i1 + (j << 7) + (j1 << 14 & 0x7fff) + 0x40000000;
		if (!class46.interactive) {
			i3 += 0x80000000;
		}
		byte byte1 = (byte) ((i << 6) + k);
		if (k == 22) {
			Object obj;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj = class46.modelAt(22, i, l1, i2, j2, k2, -1);
			} else {
				obj = new RenderableObject(j1, i, 22, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.addGroundDecoration(k1, l2, j, (Animable) obj, byte1, i3, i1, j1);
			if (class46.solid && class46.interactive) {
				class11.clipTableSet(j, i1);
			}
			return;
		}
		if (k == 10 || k == 11) {
			Object obj1;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj1 = class46.modelAt(10, i, l1, i2, j2, k2, -1);
			} else {
				obj1 = new RenderableObject(j1, i, 10, i2, j2, l1, k2, class46.animation, true);
			}
			if (obj1 != null) {
				int j5 = 0;
				if (k == 11) {
					j5 += 256;
				}
				int k4;
				int i5;
				if (i == 1 || i == 3) {
					k4 = class46.length;
					i5 = class46.width;
				} else {
					k4 = class46.width;
					i5 = class46.length;
				}
				worldController.addObject(i3, byte1, l2, i5, (Animable) obj1, k4, k1, j5, j, i1, j1);
			}
			if (class46.solid) {
				class11.method212(class46.impenetrable, class46.width, class46.length, i1, j, i);
			}
			return;
		}
		if (k >= 12) {
			Object obj2;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj2 = class46.modelAt(k, i, l1, i2, j2, k2, -1);
			} else {
				obj2 = new RenderableObject(j1, i, k, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.addObject(i3, byte1, l2, 1, (Animable) obj2, 1, k1, 0, j, i1, j1);
			if (class46.solid) {
				class11.method212(class46.impenetrable, class46.width, class46.length, i1, j, i);
			}
			return;
		}
		if (k == 0) {
			Object obj3;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj3 = class46.modelAt(0, i, l1, i2, j2, k2, -1);
			} else {
				obj3 = new RenderableObject(j1, i, 0, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.method282(anIntArray152[i], (Animable) obj3, i3, j, byte1, i1, null, l2, 0, k1, j1);
			if (class46.solid) {
				class11.method211(j, i, i1, k, class46.impenetrable);
			}
			return;
		}
		if (k == 1) {
			Object obj4;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj4 = class46.modelAt(1, i, l1, i2, j2, k2, -1);
			} else {
				obj4 = new RenderableObject(j1, i, 1, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.method282(anIntArray140[i], (Animable) obj4, i3, j, byte1, i1, null, l2, 0, k1, j1);
			if (class46.solid) {
				class11.method211(j, i, i1, k, class46.impenetrable);
			}
			return;
		}
		if (k == 2) {
			int j3 = i + 1 & 3;
			Object obj11;
			Object obj12;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj11 = class46.modelAt(2, 4 + i, l1, i2, j2, k2, -1);
				obj12 = class46.modelAt(2, j3, l1, i2, j2, k2, -1);
			} else {
				obj11 = new RenderableObject(j1, 4 + i, 2, i2, j2, l1, k2, class46.animation, true);
				obj12 = new RenderableObject(j1, j3, 2, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.method282(anIntArray152[i], (Animable) obj11, i3, j, byte1, i1, (Animable) obj12, l2, anIntArray152[j3], k1, j1);
			if (class46.solid) {
				class11.method211(j, i, i1, k, class46.impenetrable);
			}
			return;
		}
		if (k == 3) {
			Object obj5;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj5 = class46.modelAt(3, i, l1, i2, j2, k2, -1);
			} else {
				obj5 = new RenderableObject(j1, i, 3, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.method282(anIntArray140[i], (Animable) obj5, i3, j, byte1, i1, null, l2, 0, k1, j1);
			if (class46.solid) {
				class11.method211(j, i, i1, k, class46.impenetrable);
			}
			return;
		}
		if (k == 9) {
			Object obj6;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj6 = class46.modelAt(k, i, l1, i2, j2, k2, -1);
			} else {
				obj6 = new RenderableObject(j1, i, k, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.addObject(i3, byte1, l2, 1, (Animable) obj6, 1, k1, 0, j, i1, j1);
			if (class46.solid) {
				class11.method212(class46.impenetrable, class46.width, class46.length, i1, j, i);
			}
			return;
		}
		if (class46.adjustToTerrain) {
			if (i == 1) {
				int k3 = k2;
				k2 = j2;
				j2 = i2;
				i2 = l1;
				l1 = k3;
			} else if (i == 2) {
				int l3 = k2;
				k2 = i2;
				i2 = l3;
				l3 = j2;
				j2 = l1;
				l1 = l3;
			} else if (i == 3) {
				int i4 = k2;
				k2 = l1;
				l1 = i2;
				i2 = j2;
				j2 = i4;
			}
		}
		if (k == 4) {
			Object obj7;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj7 = class46.modelAt(4, 0, l1, i2, j2, k2, -1);
			} else {
				obj7 = new RenderableObject(j1, 0, 4, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.method283(i3, j, i * 512, k1, 0, l2, (Animable) obj7, i1, byte1, 0, anIntArray152[i]);
			return;
		}
		if (k == 5) {
			int j4 = 16;
			int l4 = worldController.method300(k1, i1, j);
			if (l4 > 0) {
				j4 = ObjectDefinition.forID(worldController.fetchWallObjectNewUID(k1, i1, j)/*
				 * l4
				 * >>
				 * 14
				 * &
				 * 0x7fff
				 */).decorDisplacement;
			}
			Object obj13;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj13 = class46.modelAt(4, 0, l1, i2, j2, k2, -1);
			} else {
				obj13 = new RenderableObject(j1, 0, 4, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.method283(i3, j, i * 512, k1, anIntArray137[i] * j4, l2, (Animable) obj13, i1, byte1, anIntArray144[i] * j4, anIntArray152[i]);
			return;
		}
		if (k == 6) {
			Object obj8;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj8 = class46.modelAt(4, 0, l1, i2, j2, k2, -1);
			} else {
				obj8 = new RenderableObject(j1, 0, 4, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.method283(i3, j, i, k1, 0, l2, (Animable) obj8, i1, byte1, 0, 256);
			return;
		}
		if (k == 7) {
			Object obj9;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj9 = class46.modelAt(4, 0, l1, i2, j2, k2, -1);
			} else {
				obj9 = new RenderableObject(j1, 0, 4, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.method283(i3, j, i, k1, 0, l2, (Animable) obj9, i1, byte1, 0, 512);
			return;
		}
		if (k == 8) {
			Object obj10;
			if (class46.animation == -1 && class46.morphisms == null) {
				obj10 = class46.modelAt(4, 0, l1, i2, j2, k2, -1);
			} else {
				obj10 = new RenderableObject(j1, 0, 4, i2, j2, l1, k2, class46.animation, true);
			}
			worldController.method283(i3, j, i, k1, 0, l2, (Animable) obj10, i1, byte1, 0, 768);
		}
	}

	public static boolean method189(int i, byte[] is, int i_250_) // xxx bad
	// method,
	// decompiled
	// with JODE
	{
		boolean bool = true;
		try {
			ByteBuffer stream = new ByteBuffer(is);
			int i_252_ = -1;
			for (; ; ) {
				int i_253_ = stream.getSmart();
				if (i_253_ == 0) {
					break;
				}
				i_252_ += i_253_;
				int i_254_ = 0;
				boolean bool_255_ = false;
				for (; ; ) {
					if (bool_255_) {
						int i_256_ = stream.getSmart();
						if (i_256_ == 0) {
							break;
						}
						stream.getUnsignedByte();
					} else {
						int i_257_ = stream.getSmart();
						if (i_257_ == 0) {
							break;
						}
						i_254_ += i_257_ - 1;
						int i_258_ = i_254_ & 0x3f;
						int i_259_ = i_254_ >> 6 & 0x3f;
						int i_260_ = stream.getUnsignedByte() >> 2;
						int i_261_ = i_259_ + i;
						int i_262_ = i_258_ + i_250_;
						if (i_261_ > 0 && i_262_ > 0 && i_261_ < 103 && i_262_ < 103) {
							ObjectDefinition class46 = ObjectDefinition.forID(i_252_);
							if (i_260_ != 22 || !lowDetail || class46.interactive || class46.obstructsGround) {
								bool &= class46.method579();
								bool_255_ = true;
							}
						}
					}
				}
			}
		} catch (Exception e) {

		}
		return bool;
	}

	private final byte[][][] overlays;
	private final byte[][][] shading;
	private final byte[][][] overlayTypes;
	private final byte[][][] underlays;
	private final byte[][][] overlayOrientations;
	private final byte[][][] tileFlags;
	private final int landscapeSizeX;
	private final int landscapeSizeY;
	private final int[] blendedHue;
	private final int[] blendedSaturation;
	private final int[] blendedLightness;
	private final int[] blendedDivisor;
	private final int[] blendDirectionTracker;
	private final int[][] tileLightingIntensity;
	private final int[][][] tileHeights;
	private final int[][][] tileCullingBitmap;

	public ObjectManager(byte settings[][][], int map[][][]) {
		maximumPlane = 99;
		landscapeSizeX = 104;
		landscapeSizeY = 104;
		tileHeights = map;
		tileFlags = settings;
		underlays = new byte[4][landscapeSizeX][landscapeSizeY];
		overlays = new byte[4][landscapeSizeX][landscapeSizeY];
		overlayTypes = new byte[4][landscapeSizeX][landscapeSizeY];
		overlayOrientations = new byte[4][landscapeSizeX][landscapeSizeY];
		tileCullingBitmap = new int[4][landscapeSizeX + 1][landscapeSizeY + 1];
		shading = new byte[4][landscapeSizeX + 1][landscapeSizeY + 1];
		tileLightingIntensity = new int[landscapeSizeX + 1][landscapeSizeY + 1];
		blendedHue = new int[landscapeSizeY];
		blendedSaturation = new int[landscapeSizeY];
		blendedLightness = new int[landscapeSizeY];
		blendedDivisor = new int[landscapeSizeY];
		blendDirectionTracker = new int[landscapeSizeY];
	}

	public final void createRegionScene(CollisionMap collisionMaps[], SceneGraph worldController) {
		for (int j = 0; j < 4; j++) {
			for (int k = 0; k < 104; k++) {
				for (int i1 = 0; i1 < 104; i1++) {
					if ((tileFlags[j][k][i1] & 1) == 1) {
						int k1 = j;
						if ((tileFlags[1][k][i1] & 2) == 2) {
							k1--;
						}
						if (k1 >= 0) {
							collisionMaps[k1].clipTableSet(i1, k);
						}
					}
				}

			}

		}
		for (int plane = 0; plane < 4; plane++) {
			byte shadowIntensity[][] = shading[plane];
			byte initialDirectionalIntensity = 96;
			char specularDistributionFactor = '\u0300';
			byte directionalLightX = -50;
			byte directionalLightZ = -10;
			byte directionalLightY = -50;
			int directionalLightLength = (int) Math.sqrt(directionalLightX * directionalLightX + directionalLightZ * directionalLightZ + directionalLightY * directionalLightY);
			int normalizedDistributionFactor = specularDistributionFactor * directionalLightLength >> 8;
			for (int x = 1; x < landscapeSizeY - 1; x++) {
				for (int y = 1; y < landscapeSizeX - 1; y++) {
					int heightDifferenceX = tileHeights[plane][y + 1][x] - tileHeights[plane][y - 1][x];
					int heightDifferenceY = tileHeights[plane][y][x + 1] - tileHeights[plane][y][x - 1];
					int normalLength = (int) Math.sqrt(heightDifferenceX * heightDifferenceX + 0x10000 + heightDifferenceY * heightDifferenceY);
					int normalizedX = (heightDifferenceX << 8) / normalLength;
					int normalizedZ = 0x10000 / normalLength;
					int normalizedY = (heightDifferenceY << 8) / normalLength;
					int directionalLightIntensity = initialDirectionalIntensity + (directionalLightX * normalizedX + directionalLightZ * normalizedZ + directionalLightY * normalizedY) / normalizedDistributionFactor;
					int weightedShadowIntensity = (shadowIntensity[y - 1][x] >> 2) + (shadowIntensity[y + 1][x] >> 3) + (shadowIntensity[y][x - 1] >> 2) + (shadowIntensity[y][x + 1] >> 3) + (shadowIntensity[y][x] >> 1);
					tileLightingIntensity[y][x] = directionalLightIntensity - weightedShadowIntensity;
				}

			}

			for (int k5 = 0; k5 < landscapeSizeY; k5++) {
				blendedHue[k5] = 0;
				blendedSaturation[k5] = 0;
				blendedLightness[k5] = 0;
				blendedDivisor[k5] = 0;
				blendDirectionTracker[k5] = 0;
			}

			for (int x = -5; x < landscapeSizeX + 5; x++) {
				for (int z = 0; z < landscapeSizeY; z++) {
					int xForwardOffset = x + 5;
					if (xForwardOffset >= 0 && xForwardOffset < landscapeSizeX) {
						int underlayId = underlays[plane][xForwardOffset][z] & 0xff;
						if (underlayId > 0) {
							if (underlayId >= FloorDefinitions.underlays.length) {
								underlayId = FloorDefinitions.underlays.length;
							}
							FloorDefinitions flo = FloorDefinitions.underlays[underlayId - 1];
							blendedHue[z] += flo.blendHue;
							blendedSaturation[z] += flo.saturation;
							blendedLightness[z] += flo.luminance;
							blendedDivisor[z] += flo.chroma;
							blendDirectionTracker[z]++;
						}
					}
					int xBackwardOffset = x - 5;
					if (xBackwardOffset >= 0 && xBackwardOffset < landscapeSizeX) {
						int underlayId = underlays[plane][xBackwardOffset][z] & 0xff;
						if (underlayId > 0) {
							if (underlayId >= FloorDefinitions.underlays.length) {
								underlayId = FloorDefinitions.underlays.length;
							}
							FloorDefinitions flo_1 = FloorDefinitions.underlays[underlayId - 1];
							blendedHue[z] -= flo_1.blendHue;
							blendedSaturation[z] -= flo_1.saturation;
							blendedLightness[z] -= flo_1.luminance;
							blendedDivisor[z] -= flo_1.chroma;
							blendDirectionTracker[z]--;
						}
					}
				}

				if (x >= 1 && x < landscapeSizeX - 1) {
					int zHue = 0;
					int zSaturation = 0;
					int zLightness = 0;
					int brightness = 0;
					int count = 0;
					for (int y = -5; y < landscapeSizeY + 5; y++) {
						int zForwardOffset = y + 5;
						if (zForwardOffset >= 0 && zForwardOffset < landscapeSizeY) {
							zHue += blendedHue[zForwardOffset];
							zSaturation += blendedSaturation[zForwardOffset];
							zLightness += blendedLightness[zForwardOffset];
							brightness += blendedDivisor[zForwardOffset];
							count += blendDirectionTracker[zForwardOffset];
						}
						int zBackwardOffset = y - 5;
						if (zBackwardOffset >= 0 && zBackwardOffset < landscapeSizeY) {
							zHue -= blendedHue[zBackwardOffset];
							zSaturation -= blendedSaturation[zBackwardOffset];
							zLightness -= blendedLightness[zBackwardOffset];
							brightness -= blendedDivisor[zBackwardOffset];
							count -= blendDirectionTracker[zBackwardOffset];
						}
						if (y >= 1 && y < landscapeSizeY - 1 && (!(lowDetail || Configuration.TOGGLE_ROOF_OFF) || (tileFlags[0][x][y] & 2) != 0 || (tileFlags[plane][x][y] & 0x10) == 0 && getCollisionPlane(y, plane, x) == currentPlane)) {
							if (plane < maximumPlane) {
								maximumPlane = plane;
							}
							int underlayA = underlays[plane][x][y] & 0xff;
							int overlayA = overlays[plane][x][y] & 0xff;
							if (underlayA > 0 || overlayA > 0) {
								int j19 = tileHeights[plane][x][y];
								int k19 = tileHeights[plane][x + 1][y];
								int l19 = tileHeights[plane][x + 1][y + 1];
								int i20 = tileHeights[plane][x][y + 1];
								int j20 = tileLightingIntensity[x][y];
								int k20 = tileLightingIntensity[x + 1][y];
								int l20 = tileLightingIntensity[x + 1][y + 1];
								int i21 = tileLightingIntensity[x][y + 1];
								int j21 = -1;
								int k21 = -1;
								if (underlayA > 0) {
									int l21 = zHue * 256 / brightness;
									int j22 = zSaturation / count;
									int l22 = zLightness / count;
									j21 = method177(l21, j22, l22);
									if (l22 < 0) {
										l22 = 0;
									} else if (l22 > 255) {
										l22 = 255;
									}
									k21 = method177(l21, j22, l22);
								}
								if (plane > 0) {
									boolean occlude = true;
									if (underlayA == 0 && overlayTypes[plane][x][y] != 0) {
										occlude = false;
									}
									if (overlayA > OverLayFlo317.overLayFlo317s.length) {
										overlayA = OverLayFlo317.overLayFlo317s.length - 1;
									}
									if (overlayA > 0 && !FloorDefinitions.overlays[overlayA - 1].occlude) {
										occlude = false;
									}
									if (occlude && j19 == k19 && j19 == l19 && j19 == i20) {
										tileCullingBitmap[plane][x][y] |= 0x924;
									}
								}
								int i22 = 0;
								if (j21 != -1) {
									i22 = Rasterizer.hsl2rgb[method187(k21, 96)];
								}
								if (overlayA == 0) {
									worldController.method279(plane, x, y, 0, 0, -1, 154, j19, k19, l19, i20, method187(j21, j20), method187(j21, k20), method187(j21, l20), method187(j21, i21), 0, 0, 0, 0, i22, 0, false);
								} else {
									int k22 = overlayTypes[plane][x][y] + 1;
									byte byte4 = overlayOrientations[plane][x][y];
									if (overlayA > OverLayFlo317.overLayFlo317s.length) {
										overlayA = OverLayFlo317.overLayFlo317s.length - 1;
									}
									OverLayFlo317 flo_2 = OverLayFlo317.overLayFlo317s[overlayA - 1];
									int floorTexture = flo_2.textureId;
									int j23;
									int mapTexture;
									if (floorTexture >= 0 && floorTexture <= 50) {
										mapTexture = Rasterizer.method369(floorTexture);
										j23 = -1;
									} else if (flo_2.rgb == 0xff00ff) {
										mapTexture = 0;
										j23 = -2;
										floorTexture = -1;
									} else if (flo_2.rgb == 0x333333) {
										mapTexture = Rasterizer.hsl2rgb[method185(flo_2.anInt399, 96)];
										j23 = -2;
										floorTexture = -1;
									} else {
										j23 = method177(flo_2.anInt394, flo_2.anInt395, flo_2.anInt396);
										mapTexture = Rasterizer.hsl2rgb[method185(flo_2.anInt399, 96)];
									}
									
									
									if (overlayA - 1 == 54) {
										mapTexture = flo_2.rgb = 0x8B8B83;
										j23 = -2;
										floorTexture = -1;
									} else if (overlayA - 1 == 111) {
										mapTexture = Rasterizer.method369(1);
										j23 = -1;
										floorTexture = 1;
									}
									if (j23 == 111) { // Water
										mapTexture = Rasterizer.method369(1);
										j23 = -1;
										floorTexture = 1;
									} else if (j23 == 53) { // Blue at duel
										// arena
										mapTexture = flo_2.rgb = 0xAA9166;
										floorTexture = -1;
									} else if (j23 == 52) { // Green in duel
										// arena
										mapTexture = flo_2.rgb = 0x736836;
										floorTexture = -1;
									} else if (j23 == 125) { // Roofs, duel
										// arena
										mapTexture = flo_2.rgb = 0xAA9166;
										j23 = -1;
										floorTexture = 32;
									} else if (j23 == 135) { // Water at duel
										// arena
										mapTexture = Rasterizer.method369(1);
										j23 = -2;
										floorTexture = -1;
									} else if (j23 == 6041) { // Al kharid
										// floors
										mapTexture = flo_2.rgb = 0xAA9166;
										j23 = -1;
										floorTexture = 32;
									} else if (j23 == 63) { // Seer's court
										// stairs
										mapTexture = flo_2.rgb = 0x767676;
										j23 = -2;
										floorTexture = -1;
									} else if (j23 == 177) { // Castle Wars,
										// lobby floor
										mapTexture = flo_2.rgb = 0x4D4D4D;
										j23 = method177(0, 0, 55);
										floorTexture = -1;
									} else if (j23 == 72) { // Cliffside at
										// ogres
										mapTexture = flo_2.rgb = 0x483B21;
										j23 = method177(25, 146, 24);
									} else if (j23 == 6363 || j23 == 549) { // Dirt
										// banks,
										// etc
										mapTexture = 0x483B21;
										j23 = method177(25, 146, 24);
									} else if (j23 == 40) {// Grey roads
										mapTexture = flo_2.rgb;
										floorTexture = -1;
									} else if (j23 == 6363) { // river bank
										// (brown shit)
										// 508
										mapTexture = 0x483B21;
										j23 = method177(25, 146, 24);
									} else if (j23 == 127) {
										mapTexture = Rasterizer.method369(25);
										j23 = -1;
										floorTexture = 25;
									}
									worldController.method279(plane, x, y, k22, byte4, floorTexture, 154, j19, k19, l19, i20, method187(j21, j20), method187(j21, k20), method187(j21, l20), method187(j21, i21), method185(j23, j20), method185(j23, k20), method185(j23, l20), method185(j23, i21), i22, mapTexture, floorTexture >= 0 && floorTexture <= 50);
								}
							}
						}
					}

				}
			}

			for (int j8 = 1; j8 < landscapeSizeY - 1; j8++) {
				for (int i10 = 1; i10 < landscapeSizeX - 1; i10++) {
					worldController.method278(plane, i10, j8, getCollisionPlane(j8, plane, i10));
				}

			}

		}

		worldController.method305(-10, -50, -50);
		for (int j1 = 0; j1 < landscapeSizeX; j1++) {
			for (int l1 = 0; l1 < landscapeSizeY; l1++) {
				if ((tileFlags[1][j1][l1] & 2) == 2) {
					worldController.method276(l1, j1);
				}
			}

		}

		int i2 = 1;
		int j2 = 2;
		int k2 = 4;
		for (int l2 = 0; l2 < 4; l2++) {
			if (l2 > 0) {
				i2 <<= 3;
				j2 <<= 3;
				k2 <<= 3;
			}
			for (int i3 = 0; i3 <= l2; i3++) {
				for (int k3 = 0; k3 <= landscapeSizeY; k3++) {
					for (int i4 = 0; i4 <= landscapeSizeX; i4++) {
						if ((tileCullingBitmap[i3][i4][k3] & i2) != 0) {
							int k4 = k3;
							int l5 = k3;
							int i7 = i3;
							int k8 = i3;
							for (; k4 > 0 && (tileCullingBitmap[i3][i4][k4 - 1] & i2) != 0; k4--) {
								;
							}
							for (; l5 < landscapeSizeY && (tileCullingBitmap[i3][i4][l5 + 1] & i2) != 0; l5++) {
								;
							}
							label0:
							for (; i7 > 0; i7--) {
								for (int j10 = k4; j10 <= l5; j10++) {
									if ((tileCullingBitmap[i7 - 1][i4][j10] & i2) == 0) {
										break label0;
									}
								}

							}

							label1:
							for (; k8 < l2; k8++) {
								for (int k10 = k4; k10 <= l5; k10++) {
									if ((tileCullingBitmap[k8 + 1][i4][k10] & i2) == 0) {
										break label1;
									}
								}

							}

							int l10 = (k8 + 1 - i7) * (l5 - k4 + 1);
							if (l10 >= 8) {
								char c1 = '\360';
								int k14 = tileHeights[k8][i4][k4] - c1;
								int l15 = tileHeights[i7][i4][k4];
								SceneGraph.method277(l2, i4 * 128, l15, i4 * 128, l5 * 128 + 128, k14, k4 * 128, 1);
								for (int l16 = i7; l16 <= k8; l16++) {
									for (int l17 = k4; l17 <= l5; l17++) {
										tileCullingBitmap[l16][i4][l17] &= ~i2;
									}

								}

							}
						}
						if ((tileCullingBitmap[i3][i4][k3] & j2) != 0) {
							int l4 = i4;
							int i6 = i4;
							int j7 = i3;
							int l8 = i3;
							for (; l4 > 0 && (tileCullingBitmap[i3][l4 - 1][k3] & j2) != 0; l4--) {
								;
							}
							for (; i6 < landscapeSizeX && (tileCullingBitmap[i3][i6 + 1][k3] & j2) != 0; i6++) {
								;
							}
							label2:
							for (; j7 > 0; j7--) {
								for (int i11 = l4; i11 <= i6; i11++) {
									if ((tileCullingBitmap[j7 - 1][i11][k3] & j2) == 0) {
										break label2;
									}
								}

							}

							label3:
							for (; l8 < l2; l8++) {
								for (int j11 = l4; j11 <= i6; j11++) {
									if ((tileCullingBitmap[l8 + 1][j11][k3] & j2) == 0) {
										break label3;
									}
								}

							}

							int k11 = (l8 + 1 - j7) * (i6 - l4 + 1);
							if (k11 >= 8) {
								char c2 = '\360';
								int l14 = tileHeights[l8][l4][k3] - c2;
								int i16 = tileHeights[j7][l4][k3];
								SceneGraph.method277(l2, l4 * 128, i16, i6 * 128 + 128, k3 * 128, l14, k3 * 128, 2);
								for (int i17 = j7; i17 <= l8; i17++) {
									for (int i18 = l4; i18 <= i6; i18++) {
										tileCullingBitmap[i17][i18][k3] &= ~j2;
									}

								}

							}
						}
						if ((tileCullingBitmap[i3][i4][k3] & k2) != 0) {
							int i5 = i4;
							int j6 = i4;
							int k7 = k3;
							int i9 = k3;
							for (; k7 > 0 && (tileCullingBitmap[i3][i4][k7 - 1] & k2) != 0; k7--) {
								;
							}
							for (; i9 < landscapeSizeY && (tileCullingBitmap[i3][i4][i9 + 1] & k2) != 0; i9++) {
								;
							}
							label4:
							for (; i5 > 0; i5--) {
								for (int l11 = k7; l11 <= i9; l11++) {
									if ((tileCullingBitmap[i3][i5 - 1][l11] & k2) == 0) {
										break label4;
									}
								}

							}

							label5:
							for (; j6 < landscapeSizeX; j6++) {
								for (int i12 = k7; i12 <= i9; i12++) {
									if ((tileCullingBitmap[i3][j6 + 1][i12] & k2) == 0) {
										break label5;
									}
								}

							}

							if ((j6 - i5 + 1) * (i9 - k7 + 1) >= 4) {
								int j12 = tileHeights[i3][i5][k7];
								SceneGraph.method277(l2, i5 * 128, j12, j6 * 128 + 128, i9 * 128 + 128, j12, k7 * 128, 4);
								for (int k13 = i5; k13 <= j6; k13++) {
									for (int i15 = k7; i15 <= i9; i15++) {
										tileCullingBitmap[i3][k13][i15] &= ~k2;
									}

								}

							}
						}
					}

				}

			}

		}

	}

	public final void initiateVertexHeights(int i, int j, int l, int i1) {
		for (int j1 = i; j1 <= i + j; j1++) {
			for (int k1 = i1; k1 <= i1 + l; k1++) {
				if (k1 >= 0 && k1 < landscapeSizeX && j1 >= 0 && j1 < landscapeSizeY) {
					shading[0][k1][j1] = 127;

					if (k1 == i1 && k1 > 0) {
						tileHeights[0][k1][j1] = tileHeights[0][k1 - 1][j1];
					}

					if (k1 == i1 + l && k1 < landscapeSizeX - 1) {
						tileHeights[0][k1][j1] = tileHeights[0][k1 + 1][j1];
					}

					if (j1 == i && j1 > 0) {
						tileHeights[0][k1][j1] = tileHeights[0][k1][j1 - 1];
					}

					if (j1 == i + j && j1 < landscapeSizeY - 1) {
						tileHeights[0][k1][j1] = tileHeights[0][k1][j1 + 1];
					}
				}
			}
		}
	}

	public void spawnObjectToWorld(int regionY, SceneGraph sceneGraph, CollisionMap map, int type, int z, int regionX, int id, int rotation) {
		if (lowDetail && (tileFlags[0][regionX][regionY] & 2) == 0) {
			if ((tileFlags[z][regionX][regionY] & 0x10) != 0)
				return;
			if (getCollisionPlane(regionY, z, regionX) != currentPlane)
				return;
		}


		maximumPlane = Math.min(z, maximumPlane);

		ObjectDefinition definition = ObjectDefinition.forID(id);
		boolean flag = rotation == 1 || rotation == 3;
		int sizeY = flag ? definition.length : definition.width;
		int sizeX = flag ? definition.width : definition.length;

		flag = this.landscapeSizeX >= sizeY + regionX;
		int modX = flag ? regionX + (sizeY >> 1) : regionX;
		int modX1 = flag ? regionX + (sizeY + 1 >> 1) : regionX + 1;

		flag = this.landscapeSizeY >= sizeX + regionY;
		int modY = flag ? (sizeX >> 1) + regionY : regionY;
		int modY1 = flag ? regionY + (sizeX + 1 >> 1) : regionY + 1;

		int centre = tileHeights[z][modX][modY];
		int east = tileHeights[z][modX1][modY];
		int northEast = tileHeights[z][modX1][modY1];
		int north = tileHeights[z][modX][modY1];
		int mean = centre + east + northEast + north >> 2;
		/*int centre = tileHeights[z][regionX][regionY];
		int east = tileHeights[z][regionX + 1][regionY];
		int northEast = tileHeights[z][regionX + 1][regionY + 1];
		int north = tileHeights[z][regionX][regionY + 1];
		int mean = centre + east + northEast + north >> 2;

		int mX = Client.currentRegionX - 6;
		int mY = Client.currentRegionY - 6;
		int actualX = mX * 8 + regionX;
		int actualY = mY * 8 + regionY;

		if(actualX >= 3209 && actualX <= 3217 && actualY >= 3425 &&  actualY <= 3433){
			return;
		}
	*/
		int key = regionX + (regionY << 7) + (id << 14) + 0x40000000;

		if (!definition.interactive)
			key += 0x80000000;

		byte objectKey = (byte) ((rotation << 6) + type);
		if (type == 22) {
			if (lowDetail && !definition.interactive && !definition.obstructsGround)
				return;
			Animable object;
			if (definition.animation == -1 && definition.morphisms == null)
				object = definition.modelAt(22, rotation, centre, east, northEast, north, -1);
			else
				object = new RenderableObject(id, rotation, 22, east, northEast, centre, north, definition.animation, true);

			sceneGraph.addGroundDecoration(z, mean, regionY, object, objectKey, key, regionX, id);
			if (definition.solid && definition.interactive && map != null)
				map.clipTableSet(regionY, regionX);
			return;
		}
		if (type == 10 || type == 11) {
			Animable object;
			if (definition.animation == -1 && definition.morphisms == null)
				object = definition.modelAt(10, rotation, centre, east, northEast, north, -1);
			else
				object = new RenderableObject(id, rotation, 10, east, northEast, centre, north, definition.animation, true);

			if (object != null) {
				int yaw = 0;
				if (type == 11)
					yaw += 256;
				int width;
				int length;
				if (rotation == 1 || rotation == 3) {
					width = definition.length;
					length = definition.width;
				} else {
					width = definition.width;
					length = definition.length;
				}
				if (sceneGraph.addObject(key, objectKey, mean, length, ((Animable) (object)), width, z, yaw, regionY, regionX, id) && definition.castsShadow) {
					Model model;
					if (object instanceof Model)
						model = (Model) object;
					else
						model = definition.modelAt(10, rotation, centre, east, northEast, north, -1);

					if (model != null) {
						for (int dx = 0; dx <= width; dx++) {
							for (int dy = 0; dy <= length; dy++) {
								int l5 = Math.max(30, model.boundingPlaneRadius / 4);

								if (l5 > shading[z][regionX + dx][regionY + dy])
									shading[z][regionX + dx][regionY + dy] = (byte) l5;
							}

						}

					}
				}
			}
			if (definition.solid && map != null)
				map.method212(definition.impenetrable, definition.width, definition.length, regionX, regionY, rotation);
			return;
		}
		if (type >= 12) {
			Animable object;
			if (definition.animation == -1 && definition.morphisms == null)
				object = definition.modelAt(type, rotation, centre, east, northEast, north, -1);
			else
				object = new RenderableObject(id, rotation, type, east, northEast, centre, north, definition.animation, true);

			sceneGraph.addObject(key, objectKey, mean, 1, object, 1, z, 0, regionY, regionX, id);

			if (type >= 12 && type <= 17 && type != 13 && z > 0)
				tileCullingBitmap[z][regionX][regionY] |= 0x924;

			if (definition.solid && map != null)
				map.method212(definition.impenetrable, definition.width, definition.length, regionX, regionY, rotation);
			return;
		}
		if (type == 0) {
			Animable object;
			if (definition.animation == -1 && definition.morphisms == null)
				object = definition.modelAt(0, rotation, centre, east, northEast, north, -1);
			else
				object = new RenderableObject(id, rotation, 0, east, northEast, centre, north, definition.animation, true);

			sceneGraph.method282(anIntArray152[rotation], ((Animable) (object)), key, regionY, objectKey, regionX, null, mean, 0, z, id);

			if (rotation == 0) {
				if (definition.castsShadow) {
					shading[z][regionX][regionY] = 50;
					shading[z][regionX][regionY + 1] = 50;
				}
				if (definition.occludes)
					tileCullingBitmap[z][regionX][regionY] |= 0x249;

			} else if (rotation == 1) {
				if (definition.castsShadow) {
					shading[z][regionX][regionY + 1] = 50;
					shading[z][regionX + 1][regionY + 1] = 50;
				}
				if (definition.occludes)
					tileCullingBitmap[z][regionX][regionY + 1] |= 0x492;

			} else if (rotation == 2) {
				if (definition.castsShadow) {
					shading[z][regionX + 1][regionY] = 50;
					shading[z][regionX + 1][regionY + 1] = 50;
				}
				if (definition.occludes)
					tileCullingBitmap[z][regionX + 1][regionY] |= 0x249;

			} else if (rotation == 3) {
				if (definition.castsShadow) {
					shading[z][regionX][regionY] = 50;
					shading[z][regionX + 1][regionY] = 50;
				}
				if (definition.occludes)
					tileCullingBitmap[z][regionX][regionY] |= 0x492;
			}
			if (definition.solid && map != null)
				map.method211(regionY, rotation, regionX, type, definition.impenetrable);
			if (definition.decorDisplacement != 16)
				sceneGraph.method290(regionY, definition.decorDisplacement, regionX, z);
			return;
		}
		if (type == 1) {
			Animable object;
			if (definition.animation == -1 && definition.morphisms == null)
				object = definition.modelAt(1, rotation, centre, east, northEast, north, -1);
			else
				object = new RenderableObject(id, rotation, 1, east, northEast, centre, north, definition.animation, true);

			sceneGraph.method282(anIntArray140[rotation], object, key, regionY, objectKey, regionX, null, mean, 0, z, id);

			if (definition.castsShadow) {
				if (rotation == 0)
					shading[z][regionX][regionY + 1] = 50;
				else if (rotation == 1)
					shading[z][regionX + 1][regionY + 1] = 50;
				else if (rotation == 2)
					shading[z][regionX + 1][regionY] = 50;
				else if (rotation == 3)
					shading[z][regionX][regionY] = 50;
			}

			if (definition.solid && map != null)
				map.method211(regionY, rotation, regionX, type, definition.impenetrable);
			return;
		}
		if (type == 2) {
			int oppositeOrientation = rotation + 1 & 3;
			Animable obj11;
			Animable obj12;
			if (definition.animation == -1 && definition.morphisms == null) {
				obj11 = definition.modelAt(2, 4 + rotation, centre, east, northEast, north, -1);
				obj12 = definition.modelAt(2, oppositeOrientation, centre, east, northEast, north, -1);
			} else {
				obj11 = new RenderableObject(id, 4 + rotation, 2, east, northEast, centre, north, definition.animation, true);
				obj12 = new RenderableObject(id, oppositeOrientation, 2, east, northEast, centre, north, definition.animation, true);
			}

			sceneGraph.method282(anIntArray152[rotation], obj11, key, regionY, objectKey, regionX, obj12, mean, anIntArray152[oppositeOrientation], z, id);
			if (definition.occludes) {
				if (rotation == 0) {
					tileCullingBitmap[z][regionX][regionY] |= 0x249;
					tileCullingBitmap[z][regionX][regionY + 1] |= 0x492;
				} else if (rotation == 1) {
					tileCullingBitmap[z][regionX][regionY + 1] |= 0x492;
					tileCullingBitmap[z][regionX + 1][regionY] |= 0x249;
				} else if (rotation == 2) {
					tileCullingBitmap[z][regionX + 1][regionY] |= 0x249;
					tileCullingBitmap[z][regionX][regionY] |= 0x492;
				} else if (rotation == 3) {
					tileCullingBitmap[z][regionX][regionY] |= 0x492;
					tileCullingBitmap[z][regionX][regionY] |= 0x249;
				}
			}

			if (definition.solid && map != null)
				map.method211(regionY, rotation, regionX, type, definition.impenetrable);
			if (definition.decorDisplacement != 16)
				sceneGraph.method290(regionY, definition.decorDisplacement, regionX, z);
			return;
		}
		if (type == 3) {
			Object obj5;
			if (definition.animation == -1 && definition.morphisms == null)
				obj5 = definition.modelAt(3, rotation, centre, east, northEast, north, -1);
			else
				obj5 = new RenderableObject(id, rotation, 3, east, northEast, centre, north, definition.animation, true);
			sceneGraph.method282(anIntArray140[rotation], ((Animable) (obj5)), key, regionY, objectKey, regionX, null, mean, 0, z, id);
			if (definition.castsShadow)
				if (rotation == 0)
					shading[z][regionX][regionY + 1] = 50;
				else if (rotation == 1)
					shading[z][regionX + 1][regionY + 1] = 50;
				else if (rotation == 2)
					shading[z][regionX + 1][regionY] = 50;
				else if (rotation == 3)
					shading[z][regionX][regionY] = 50;
			if (definition.solid && map != null)
				map.method211(regionY, rotation, regionX, type, definition.impenetrable);
			return;
		}
		if (type == 9) {
			Object obj6;
			if (definition.animation == -1 && definition.morphisms == null)
				obj6 = definition.modelAt(type, rotation, centre, east, northEast, north, -1);
			else
				obj6 = new RenderableObject(id, rotation, type, east, northEast, centre, north, definition.animation, true);
			sceneGraph.addObject(key, objectKey, mean, 1, ((Animable) (obj6)), 1, z, 0, regionY, regionX, id);
			if (definition.solid && map != null)
				map.method212(definition.impenetrable, definition.width, definition.length, regionX, regionY, rotation);
			return;
		}
		if (definition.adjustToTerrain)//contouredGround)
			if (rotation == 1) {
				int j3 = north;
				north = northEast;
				northEast = east;
				east = centre;
				centre = j3;
			} else if (rotation == 2) {
				int k3 = north;
				north = east;
				east = k3;
				k3 = northEast;
				northEast = centre;
				centre = k3;
			} else if (rotation == 3) {
				int l3 = north;
				north = centre;
				centre = east;
				east = northEast;
				northEast = l3;
			}
		if (type == 4) {
			Object obj7;
			if (definition.animation == -1 && definition.morphisms == null)
				obj7 = definition.modelAt(4, 0, centre, east, northEast, north, -1);
			else
				obj7 = new RenderableObject(id, 0, 4, east, northEast, centre, north, definition.animation, true);
			sceneGraph.method283(key, regionY, rotation * 512, z, 0, mean, ((Animable) (obj7)), regionX, objectKey, 0, anIntArray152[rotation]);
			return;
		}
		if (type == 5) {
			int i4 = 16;
			int k4 = sceneGraph.method300(z, regionX, regionY);
			if (k4 > 0)
				i4 = ObjectDefinition.forID(k4 >> 14 & 0x7fff).decorDisplacement;
			Object obj13;
			if (definition.animation == -1 && definition.morphisms == null)
				obj13 = definition.modelAt(4, 0, centre, east, northEast, north, -1);
			else
				obj13 = new RenderableObject(id, 0, 4, east, northEast, centre, north, definition.animation, true);
			sceneGraph.method283(key, regionY, rotation * 512, z, anIntArray137[rotation] * i4, mean, ((Animable) (obj13)), regionX, objectKey, anIntArray144[rotation] * i4, anIntArray152[rotation]);
			return;
		}
		if (type == 6) {
			Object obj8;
			if (definition.animation == -1 && definition.morphisms == null)
				obj8 = definition.modelAt(4, 0, centre, east, northEast, north, -1);
			else
				obj8 = new RenderableObject(id, 0, 4, east, northEast, centre, north, definition.animation, true);
			sceneGraph.method283(key, regionY, rotation, z, 0, mean, ((Animable) (obj8)), regionX, objectKey, 0, 256);
			return;
		}
		if (type == 7) {
			Object obj9;
			if (definition.animation == -1 && definition.morphisms == null)
				obj9 = definition.modelAt(4, 0, centre, east, northEast, north, -1);
			else
				obj9 = new RenderableObject(id, 0, 4, east, northEast, centre, north, definition.animation, true);
			sceneGraph.method283(key, regionY, rotation, z, 0, mean, ((Animable) (obj9)), regionX, objectKey, 0, 512);
			return;
		}
		if (type == 8) {
			Object obj10;
			if (definition.animation == -1 && definition.morphisms == null)
				obj10 = definition.modelAt(4, 0, centre, east, northEast, north, -1);
			else
				obj10 = new RenderableObject(id, 0, 4, east, northEast, centre, north, definition.animation, true);
			sceneGraph.method283(key, regionY, rotation, z, 0, mean, ((Animable) (obj10)), regionX, objectKey, 0, 768);
		}
	}

	private int method177(int i, int j, int k) {
		if (k > 179) {
			j /= 2;
		}
		if (k > 192) {
			j /= 2;
		}
		if (k > 217) {
			j /= 2;
		}
		if (k > 243) {
			j /= 2;
		}
		return (i / 4 << 10) + (j / 32 << 7) + k / 2;
	}

	public final void method179(int i, int j, CollisionMap aclass11[], int l, int i1, byte abyte0[], int j1, int k1, int l1) {
		for (int i2 = 0; i2 < 8; i2++) {
			for (int j2 = 0; j2 < 8; j2++) {
				if (l + i2 > 0 && l + i2 < 103 && l1 + j2 > 0 && l1 + j2 < 103) {
					aclass11[k1].flags[l + i2][l1 + j2] &= 0xfeffffff;
				}
			}

		}
		ByteBuffer stream = new ByteBuffer(abyte0);
		for (int l2 = 0; l2 < 4; l2++) {
			for (int i3 = 0; i3 < 64; i3++) {
				for (int j3 = 0; j3 < 64; j3++) {
					if (l2 == i && i3 >= i1 && i3 < i1 + 8 && j3 >= j1 && j3 < j1 + 8) {
						method181(l1 + Class4.method156(j3 & 7, j, i3 & 7), 0, stream, l + Class4.method155(j, j3 & 7, i3 & 7), k1, j, 0);
					} else {
						method181(-1, 0, stream, -1, 0, 0, 0);
					}
				}

			}

		}

	}

	public final void method180(byte abyte0[], int i, int j, int k, int l, CollisionMap aclass11[]) {
		for (int i1 = 0; i1 < 4; i1++) {
			for (int j1 = 0; j1 < 64; j1++) {
				for (int k1 = 0; k1 < 64; k1++) {
					if (j + j1 > 0 && j + j1 < 103 && i + k1 > 0 && i + k1 < 103) {
						aclass11[i1].flags[j + j1][i + k1] &= 0xfeffffff;
					}
				}

			}

		}

		ByteBuffer stream = new ByteBuffer(abyte0);
		for (int l1 = 0; l1 < 4; l1++) {
			for (int i2 = 0; i2 < 64; i2++) {
				for (int j2 = 0; j2 < 64; j2++) {
					method181(j2 + i, l, stream, i2 + j, l1, 0, k);
				}
			}
		}
	}

	private void method181(int i, int j, ByteBuffer stream, int k, int l, int i1, int k1) {
		if (k >= 0 && k < 104 && i >= 0 && i < 104) {
			tileFlags[l][k][i] = 0;

			do {
				int l1 = stream.getUnsignedByte();

				if (l1 == 0) {
					if (l == 0) {
						tileHeights[0][k][i] = -method172(0xe3b7b + k + k1, 0x87cce + i + j) * 8;
						return;
					} else {
						tileHeights[l][k][i] = tileHeights[l - 1][k][i] - 240;
						return;
					}
				}

				if (l1 == 1) {
					int j2 = stream.getUnsignedByte();

					if (j2 == 1) {
						j2 = 0;
					}

					if (l == 0) {
						tileHeights[0][k][i] = -j2 * 8;
						return;
					} else {
						tileHeights[l][k][i] = tileHeights[l - 1][k][i] - j2 * 8;
						return;
					}
				}

				if (l1 <= 49) {
					overlays[l][k][i] = stream.getSignedByte();
					overlayTypes[l][k][i] = (byte) ((l1 - 2) / 4);
					overlayOrientations[l][k][i] = (byte) (l1 - 2 + i1 & 3);
				} else if (l1 <= 81) {
					tileFlags[l][k][i] = (byte) (l1 - 49);
				} else {
					underlays[l][k][i] = (byte) (l1 - 81);
				}
			} while (true);
		}

		do {
			int i2 = stream.getUnsignedByte();

			if (i2 == 0) {
				break;
			}

			if (i2 == 1) {
				stream.getUnsignedByte();
				return;
			}

			if (i2 <= 49) {
				stream.getUnsignedByte();
			}
		} while (true);
	}

	private int getCollisionPlane(int i, int j, int k) {
		if ((tileFlags[j][k][i] & 8) != 0) {
			return 0;
		}
		if (j > 0 && (tileFlags[1][k][i] & 2) != 0) {
			return j - 1;
		} else {
			return j;
		}
	}

	public final void readObjectMap(CollisionMap aclass11[], SceneGraph worldController, int i, int j, int k, int l, byte abyte0[], int i1, int j1, int k1) {
		label0:
		{
			ByteBuffer stream = new ByteBuffer(abyte0);
			int l1 = -1;
			do {
				int i2 = stream.getUSmart2();
				if (i2 == 0) {
					break label0;
				}
				l1 += i2;
				int j2 = 0;
				do {
					int k2 = stream.getSmart();
					if (k2 == 0) {
						break;
					}
					j2 += k2 - 1;
					int l2 = j2 & 0x3f;
					int i3 = j2 >> 6 & 0x3f;
					int j3 = j2 >> 12;
					int k3 = stream.getUnsignedByte();
					int l3 = k3 >> 2;
					int i4 = k3 & 3;
					if (j3 == i && i3 >= i1 && i3 < i1 + 8 && l2 >= k && l2 < k + 8) {
						ObjectDefinition class46 = ObjectDefinition.forID(l1);
						int j4 = j + Class4.method157(j1, class46.length, i3 & 7, l2 & 7, class46.width);
						int k4 = k1 + Class4.method158(l2 & 7, class46.length, j1, class46.width, i3 & 7);
						if (j4 > 0 && k4 > 0 && j4 < 103 && k4 < 103) {
							int l4 = j3;
							if ((tileFlags[1][j4][k4] & 2) == 2) {
								l4--;
							}
							CollisionMap class11 = null;
							if (l4 >= 0) {
								class11 = aclass11[l4];
							}
							spawnObjectToWorld(k4, worldController, class11, l3, l, j4, l1, i4 + j1 & 3);
						}
					}
				} while (true);
			} while (true);
		}
	}

	private int method185(int i, int j) {
		if (i == -2) {
			return 0xbc614e;
		}

		if (i == -1) {
			if (j < 0) {
				j = 0;
			} else if (j > 127) {
				j = 127;
			}

			j = 127 - j;
			return j;
		}

		j = j * (i & 0x7f) / 128;

		if (j < 2) {
			j = 2;
		} else if (j > 126) {
			j = 126;
		}

		return (i & 0xff80) + j;
	}

	public final void method190(int i, CollisionMap aclass11[], int j, SceneGraph worldController, byte abyte0[]) {
		label0:
		{
			ByteBuffer buffer = new ByteBuffer(abyte0);
			int l = -1;

			do {
				int i1 = buffer.getSmart();

				if (i1 == 0) {
					break label0;
				}

				l += i1;
				int j1 = 0;

				do {
					int k1 = buffer.getSmart();

					if (k1 == 0) {
						break;
					}

					j1 += k1 - 1;
					int l1 = j1 & 0x3f;
					int i2 = j1 >> 6 & 0x3f;
					int j2 = j1 >> 12;
					int k2 = buffer.getUnsignedByte();
					int l2 = k2 >> 2;
					int i3 = k2 & 3;
					int j3 = i2 + i;
					int k3 = l1 + j;

					if (j3 > 0 && k3 > 0 && j3 < 103 && k3 < 103) {
						int l3 = j2;

						if ((tileFlags[1][j3][k3] & 2) == 2) {
							l3--;
						}

						CollisionMap class11 = null;

						if (l3 >= 0) {
							if (l3 > aclass11.length) {
								l3 = aclass11.length - 1;
							}
							class11 = aclass11[l3];
						}

						spawnObjectToWorld(k3, worldController, class11, l2, j2, j3, l, i3);
					}
				} while (true);
			} while (true);
		}
	}

}