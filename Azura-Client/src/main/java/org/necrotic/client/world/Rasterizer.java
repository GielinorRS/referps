package org.necrotic.client.world;

import org.necrotic.Configuration;
import org.necrotic.client.Client;
import org.necrotic.client.cache.Archive;
import org.necrotic.client.graphics.Background;
import org.necrotic.client.graphics.DrawingArea;

public final class Rasterizer extends DrawingArea {

	public static float[] depthBuffer;
	private static final int TEXTURE_AMOUNT = 102;
	public static Background aBackgroundArray1474s[] = new Background[TEXTURE_AMOUNT];
	public static boolean aBoolean1462;
	private static boolean aBoolean1463;
	private static boolean[] aBooleanArray1475 = new boolean[TEXTURE_AMOUNT];
	public static int anInt1465;
	private static int anInt1473;
	private static int anInt1477;
	public static int anInt1481;
	private static int[] anIntArray1468;
	public static final int[] anIntArray1469;
	private static int[] anIntArray1476 = new int[TEXTURE_AMOUNT];
	public static int anIntArray1480[] = new int[TEXTURE_AMOUNT];
	public static int anIntArray1482[] = new int[0x10000];
	private static int[][] anIntArrayArray1478;
	private static int[][] anIntArrayArray1479 = new int[TEXTURE_AMOUNT][];
	private static int[][] anIntArrayArray1483 = new int[TEXTURE_AMOUNT][];
	public static int centerX;
	public static int centerY;
	public static int COSINE[];
	public static int lineOffsets[];
	public static boolean lowDetail = false;
	public static boolean notTextured = true;
	private static int[] OFFSETS_512_334 = null;
	private static int[] OFFSETS_765_503 = null;
	public static int SINE[];

	static {
		anIntArray1468 = new int[512];
		anIntArray1469 = new int[2048];
		SINE = new int[2479];
		COSINE = new int[2479];

		for (int i = 1; i < 512; i++) {
			anIntArray1468[i] = 32768 / i;
		}

		for (int j = 1; j < 2048; j++) {
			anIntArray1469[j] = 0x10000 / j;
		}

		for (int k = 0; k < 2048; k++) {
			SINE[k] = (int) (65536D * Math.sin(k * 0.0030679614999999999D));
			COSINE[k] = (int) (65536D * Math.cos(k * 0.0030679614999999999D));
		}

	}
	
	public static void clearDepthBuffer() {
		if (depthBuffer == null || depthBuffer.length != pixels.length) {
           depthBuffer = new float[pixels.length];
            }
            for (int i = 0; i < depthBuffer.length; i++) {
                depthBuffer[i] = Float.MAX_VALUE;
            }
    }

   public static void drawFog(int rgb, int begin, int end) {
        float length = end - begin;
        for (int i = 0; i < pixels.length; i++) {
            float factor = (depthBuffer[i] - begin) / length;
            pixels[i] = blend(pixels[i], rgb, factor);
        }
    }

    private static int blend(int c1, int c2, float factor) {
        if (factor >= 1f) {
            return c2;
        }
        if (factor <= 0f) {
            return c1;
        }

        int r1 = (c1 >> 16) & 0xff;
        int g1 = (c1 >> 8) & 0xff;
        int b1 = (c1) & 0xff;

        int r2 = (c2 >> 16) & 0xff;
        int g2 = (c2 >> 8) & 0xff;
        int b2 = (c2) & 0xff;

        int r3 = r2 - r1;
        int g3 = g2 - g1;
        int b3 = b2 - b1;

        int r = (int) (r1 + (r3 * factor));
        int g = (int) (g1 + (g3 * factor));
        int b = (int) (b1 + (b3 * factor));

        return (r << 16) + (g << 8) + b;
    }

	public static int[] getOffsets(int j, int k) {
		if (j == 512 && k == 334 && OFFSETS_512_334 != null) {
			return OFFSETS_512_334;
		}

		if (j == 765 + 1 && k == 503 && OFFSETS_765_503 != null) {
			return OFFSETS_765_503;
		}

		int[] t = new int[k];
		for (int l = 0; l < k; l++) {
			t[l] = j * l;
		}

		if (j == 512 && k == 334) {
			OFFSETS_512_334 = t;
		}

		if (j == 765 + 1 && k == 503) {
			OFFSETS_765_503 = t;
		}

		return t;
	}

	public static void method364() {
		lineOffsets = new int[DrawingArea.height];
		for (int j = 0; j < DrawingArea.height; j++) {
			lineOffsets[j] = DrawingArea.width * j;
		}

		centerX = DrawingArea.width / 2;
		centerY = DrawingArea.height / 2;
	}

	public static void clearTextureCache() {
		anIntArrayArray1478 = null;
		for (int j = 0; j < TEXTURE_AMOUNT; j++) {
			anIntArrayArray1479[j] = null;
		}

	}

	public static void initiateRequestBuffers() {
		if (anIntArrayArray1478 == null) {
			anInt1477 = 20;// was parameter
			if (lowDetail) {
				anIntArrayArray1478 = new int[anInt1477][16384];
			} else {
				anIntArrayArray1478 = new int[anInt1477][0x10000];
			}
			for (int k = 0; k < TEXTURE_AMOUNT; k++) {
				anIntArrayArray1479[k] = null;
			}

		}
	}

	public static void method368(Archive streamLoader) {
		anInt1473 = 0;

		for (int i = 0; i < TEXTURE_AMOUNT; i++) {
			try {
				aBackgroundArray1474s[i] = new Background(streamLoader, String.valueOf(i), 0);

				if (lowDetail && aBackgroundArray1474s[i].maxWidth == 128) {
					aBackgroundArray1474s[i].method356();
				} else {
					aBackgroundArray1474s[i].method357();
				}

				anInt1473++;
			} catch (Exception _ex) {
			}
		}
	}

	public static int method369(int i) {
		if (anIntArray1476[i] != 0) {
			return anIntArray1476[i];
		}

		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = anIntArrayArray1483[i].length;

		for (int k1 = 0; k1 < j1; k1++) {
			k += anIntArrayArray1483[i][k1] >> 16 & 0xff;
		l += anIntArrayArray1483[i][k1] >> 8 & 0xff;
			i1 += anIntArrayArray1483[i][k1] & 0xff;
		}

		int l1 = (k / j1 << 16) + (l / j1 << 8) + i1 / j1;
		l1 = method373(l1, 1.3999999999999999D);

		if (l1 == 0) {
			l1 = 1;
		}

		anIntArray1476[i] = l1;
		return l1;
	}

	public static void method370(int i) {
		if (anIntArrayArray1479[i] == null) {
			return;
		}

		anIntArrayArray1478[anInt1477++] = anIntArrayArray1479[i];
		anIntArrayArray1479[i] = null;
	}

	private static int[] method371(int i) {
		anIntArray1480[i] = anInt1481++;

		if (anIntArrayArray1479[i] != null) {
			return anIntArrayArray1479[i];
		}

		int ai[];

		if (anInt1477 > 0) {
			ai = anIntArrayArray1478[--anInt1477];
			anIntArrayArray1478[anInt1477] = null;
		} else {
			int j = 0;
			int k = -1;

			for (int l = 0; l < anInt1473; l++) {
				if (anIntArrayArray1479[l] != null && (anIntArray1480[l] < j || k == -1)) {
					j = anIntArray1480[l];
					k = l;
				}
			}

			ai = anIntArrayArray1479[k];
			anIntArrayArray1479[k] = null;
		}

		anIntArrayArray1479[i] = ai;
		Background background = aBackgroundArray1474s[i];
		int ai1[] = anIntArrayArray1483[i];

		if (lowDetail) {
			aBooleanArray1475[i] = false;

			for (int i1 = 0; i1 < 4096; i1++) {
				int i2 = ai[i1] = ai1[background.imgPixels[i1]] & 0xf8f8ff;

				if (i2 == 0) {
					aBooleanArray1475[i] = true;
				}

				ai[4096 + i1] = i2 - (i2 >>> 3) & 0xf8f8ff;
				ai[8192 + i1] = i2 - (i2 >>> 2) & 0xf8f8ff;
				ai[12288 + i1] = i2 - (i2 >>> 2) - (i2 >>> 3) & 0xf8f8ff;
			}
		} else {
			if (background.imgWidth == 64) {
				for (int j1 = 0; j1 < 128; j1++) {
					for (int j2 = 0; j2 < 128; j2++) {
						ai[j2 + (j1 << 7)] = ai1[background.imgPixels[(j2 >> 1) + (j1 >> 1 << 6)]];
					}
				}
			} else {
				for (int k1 = 0; k1 < 16384; k1++) {
					if (background.imgPixels[k1] < 0)
						ai[k1] = ai1[5];
					else
					ai[k1] = ai1[background.imgPixels[k1]];
				}
			}

			aBooleanArray1475[i] = false;

			for (int l1 = 0; l1 < 16384; l1++) {
				ai[l1] &= 0xf8f8ff;
				int k2 = ai[l1];

				if (k2 == 0) {
					aBooleanArray1475[i] = true;
				}

				ai[16384 + l1] = k2 - (k2 >>> 3) & 0xf8f8ff;
				ai[32768 + l1] = k2 - (k2 >>> 2) & 0xf8f8ff;
				ai[49152 + l1] = k2 - (k2 >>> 2) - (k2 >>> 3) & 0xf8f8ff;
			}
		}

		return ai;
	}

	public static void method372(double d) {
		Texture.setBrightness(d);
		int j = 0;

		for (int k = 0; k < 512; k++) {
			double d1 = k / 8 / 64D + 0.0078125D;
			double d2 = (k & 7) / 8D + 0.0625D;

			for (int k1 = 0; k1 < 128; k1++) {
				double d3 = k1 / 128D;
				double d4 = d3;
				double d5 = d3;
				double d6 = d3;

				if (d2 != 0.0D) {
					double d7;

					if (d3 < 0.5D) {
						d7 = d3 * (1.0D + d2);
					} else {
						d7 = d3 + d2 - d3 * d2;
					}

					double d8 = 2D * d3 - d7;
					double d9 = d1 + 0.33333333333333331D;

					if (d9 > 1.0D) {
						d9--;
					}

					double d10 = d1;
					double d11 = d1 - 0.33333333333333331D;

					if (d11 < 0.0D) {
						d11++;
					}

					if (6D * d9 < 1.0D) {
						d4 = d8 + (d7 - d8) * 6D * d9;
					} else if (2D * d9 < 1.0D) {
						d4 = d7;
					} else if (3D * d9 < 2D) {
						d4 = d8 + (d7 - d8) * (0.66666666666666663D - d9) * 6D;
					} else {
						d4 = d8;
					}

					if (6D * d10 < 1.0D) {
						d5 = d8 + (d7 - d8) * 6D * d10;
					} else if (2D * d10 < 1.0D) {
						d5 = d7;
					} else if (3D * d10 < 2D) {
						d5 = d8 + (d7 - d8) * (0.66666666666666663D - d10) * 6D;
					} else {
						d5 = d8;
					}

					if (6D * d11 < 1.0D) {
						d6 = d8 + (d7 - d8) * 6D * d11;
					} else if (2D * d11 < 1.0D) {
						d6 = d7;
					} else if (3D * d11 < 2D) {
						d6 = d8 + (d7 - d8) * (0.66666666666666663D - d11) * 6D;
					} else {
						d6 = d8;
					}
				}

				int l1 = (int) (d4 * 256D);
				int i2 = (int) (d5 * 256D);
				int j2 = (int) (d6 * 256D);
				int k2 = (l1 << 16) + (i2 << 8) + j2;
				k2 = method373(k2, d);

				if (k2 == 0) {
					k2 = 1;
				}

				anIntArray1482[j++] = k2;
			}
		}

		for (int l = 0; l < TEXTURE_AMOUNT; l++) {
			if (aBackgroundArray1474s[l] != null) {
				int ai[] = aBackgroundArray1474s[l].palette;
				anIntArrayArray1483[l] = new int[ai.length];

				for (int j1 = 0; j1 < ai.length; j1++) {
					anIntArrayArray1483[l][j1] = method373(ai[j1], d);

					if ((anIntArrayArray1483[l][j1] & 0xf8f8ff) == 0 && j1 != 0) {
						anIntArrayArray1483[l][j1] = 1;
					}
				}
			}
		}

		for (int i1 = 0; i1 < TEXTURE_AMOUNT; i1++) {
			method370(i1);
		}
	}

	private static int method373(int i, double d) {
		double d1 = (i >> 16) / 256D;
		double d2 = (i >> 8 & 0xff) / 256D;
		double d3 = (i & 0xff) / 256D;
		d1 = Math.pow(d1, d);
		d2 = Math.pow(d2, d);
		d3 = Math.pow(d3, d);
		int j = (int) (d1 * 256D);
		int k = (int) (d2 * 256D);
		int l = (int) (d3 * 256D);
		return (j << 16) + (k << 8) + l;
	}

	public static void drawGouraudTriangle(int y1, int y2, int y3, int x1, int x2, int x3, int hsl1, int hsl2, int hsl3, float z1, float z2, float z3, int bufferOffset) {
		bufferOffset = 0;
		int rgb1 = anIntArray1482[hsl1];
		int rgb2 = anIntArray1482[hsl2];
		int rgb3 = anIntArray1482[hsl3];
		int r1 = rgb1 >> 16 & 0xff;
		int g1 = rgb1 >> 8 & 0xff;
		int b1 = rgb1 & 0xff;
		int r2 = rgb2 >> 16 & 0xff;
		int g2 = rgb2 >> 8 & 0xff;
		int b2 = rgb2 & 0xff;
		int r3 = rgb3 >> 16 & 0xff;
		int g3 = rgb3 >> 8 & 0xff;
		int b3 = rgb3 & 0xff;
		int dx1 = 0;
		int dr1 = 0;
		int dg1 = 0;
		int db1 = 0;
		if (y2 != y1) {
			dx1 = (x2 - x1 << 16) / (y2 - y1);
			dr1 = (r2 - r1 << 16) / (y2 - y1);
			dg1 = (g2 - g1 << 16) / (y2 - y1);
			db1 = (b2 - b1 << 16) / (y2 - y1);
		}
		int dx2 = 0;
		int dr2 = 0;
		int dg2 = 0;
		int db2 = 0;
		if (y3 != y2) {
			dx2 = (x3 - x2 << 16) / (y3 - y2);
			dr2 = (r3 - r2 << 16) / (y3 - y2);
			dg2 = (g3 - g2 << 16) / (y3 - y2);
			db2 = (b3 - b2 << 16) / (y3 - y2);
		}
		int dx3 = 0;
		int dr3 = 0;
		int dg3 = 0;
		int db3 = 0;
		if (y3 != y1) {
			dx3 = (x1 - x3 << 16) / (y1 - y3);
			dr3 = (r1 - r3 << 16) / (y1 - y3);
			dg3 = (g1 - g3 << 16) / (y1 - y3);
			db3 = (b1 - b3 << 16) / (y1 - y3);
		}
		
		float x21 = x2 - x1;
		float y21 = y2 - y1;
		float x31 = x3 - x1;
		float y31 = y3 - y1;
		float z21 = z2 - z1;
		float z31 = z3 - z1;

		float div = x21 * y31 - x31 * y21;
		float depthSlope = (z21 * y31 - z31 * y21) / div;
		float depthScale = (z31 * x21 - z21 * x31) / div;
		
		if (y1 <= y2 && y1 <= y3) {
			if (y1 >= bottomY) {
				return;
			}
			if (y2 > bottomY) {
				y2 = bottomY;
			}
			if (y3 > bottomY) {
				y3 = bottomY;
			}
			z1 = z1 - depthSlope * x1 + depthSlope;
			if (y2 < y3) {
				x3 = x1 <<= 16;
				r3 = r1 <<= 16;
				g3 = g1 <<= 16;
				b3 = b1 <<= 16;
				if (y1 < 0) {
					x3 -= dx3 * y1;
					x1 -= dx1 * y1;
					r3 -= dr3 * y1;
					g3 -= dg3 * y1;
					b3 -= db3 * y1;
					r1 -= dr1 * y1;
					g1 -= dg1 * y1;
					b1 -= db1 * y1;
					z1 -= depthScale * y1;
					y1 = 0;
				}
				x2 <<= 16;
				r2 <<= 16;
				g2 <<= 16;
				b2 <<= 16;
				if (y2 < 0) {
					x2 -= dx2 * y2;
					r2 -= dr2 * y2;
					g2 -= dg2 * y2;
					b2 -= db2 * y2;
					y2 = 0;
				}
				if (y1 != y2 && dx3 < dx1 || y1 == y2 && dx3 > dx2) {
					y3 -= y2;
					y2 -= y1;
					for (y1 = lineOffsets[y1]; --y2 >= 0; y1 += width) {
						drawGouraudScanline(pixels, y1, x3 >> 16, x1 >> 16, r3, g3, b3, r1, g1, b1, z1, depthSlope, bufferOffset);
						x3 += dx3;
						x1 += dx1;
						r3 += dr3;
						g3 += dg3;
						b3 += db3;
						r1 += dr1;
						g1 += dg1;
						b1 += db1;
						z1 += depthScale;
					}
					while (--y3 >= 0) {
						drawGouraudScanline(pixels, y1, x3 >> 16, x2 >> 16, r3, g3, b3, r2, g2, b2, z1, depthSlope, bufferOffset);
						x3 += dx3;
						x2 += dx2;
						r3 += dr3;
						g3 += dg3;
						b3 += db3;
						r2 += dr2;
						g2 += dg2;
						b2 += db2;
						y1 += width;
						z1 += depthScale;
					}
					return;
				}
				y3 -= y2;
				y2 -= y1;
				for (y1 = lineOffsets[y1]; --y2 >= 0; y1 += width) {
					drawGouraudScanline(pixels, y1, x1 >> 16, x3 >> 16, r1, g1, b1, r3, g3, b3, z1, depthSlope, bufferOffset);
					x3 += dx3;
					x1 += dx1;
					r3 += dr3;
					g3 += dg3;
					b3 += db3;
					r1 += dr1;
					g1 += dg1;
					b1 += db1;
					z1 += depthScale;
				}
				while (--y3 >= 0) {
					drawGouraudScanline(pixels, y1, x2 >> 16, x3 >> 16, r2, g2, b2, r3, g3, b3, z1, depthSlope, bufferOffset);
					x3 += dx3;
					x2 += dx2;
					r3 += dr3;
					g3 += dg3;
					b3 += db3;
					r2 += dr2;
					g2 += dg2;
					b2 += db2;
					y1 += width;
					z1 += depthScale;
				}
				return;
			}
			x2 = x1 <<= 16;
			r2 = r1 <<= 16;
			g2 = g1 <<= 16;
			b2 = b1 <<= 16;
			if (y1 < 0) {
				x2 -= dx3 * y1;
				x1 -= dx1 * y1;
				r2 -= dr3 * y1;
				g2 -= dg3 * y1;
				b2 -= db3 * y1;
				r1 -= dr1 * y1;
				g1 -= dg1 * y1;
				b1 -= db1 * y1;
				z1 -= depthScale * y1;
				y1 = 0;
			}
			x3 <<= 16;
			r3 <<= 16;
			g3 <<= 16;
			b3 <<= 16;
			if (y3 < 0) {
				x3 -= dx2 * y3;
				r3 -= dr2 * y3;
				g3 -= dg2 * y3;
				b3 -= db2 * y3;
				y3 = 0;
			}
			if (y1 != y3 && dx3 < dx1 || y1 == y3 && dx2 > dx1) {
				y2 -= y3;
				y3 -= y1;
				for (y1 = lineOffsets[y1]; --y3 >= 0; y1 += width) {
					drawGouraudScanline(pixels, y1, x2 >> 16, x1 >> 16, r2, g2, b2, r1, g1, b1, z1, depthSlope, bufferOffset);
					x2 += dx3;
					x1 += dx1;
					r2 += dr3;
					g2 += dg3;
					b2 += db3;
					r1 += dr1;
					g1 += dg1;
					b1 += db1;
					z1 += depthScale;
				}
				while (--y2 >= 0) {
					drawGouraudScanline(pixels, y1, x3 >> 16, x1 >> 16, r3, g3, b3, r1, g1, b1, z1, depthSlope, bufferOffset);
					x3 += dx2;
					x1 += dx1;
					r3 += dr2;
					g3 += dg2;
					b3 += db2;
					r1 += dr1;
					g1 += dg1;
					b1 += db1;
					y1 += width;
					z1 += depthScale;
				}
				return;
			}
			y2 -= y3;
			y3 -= y1;
			for (y1 = lineOffsets[y1]; --y3 >= 0; y1 += width) {
				drawGouraudScanline(pixels, y1, x1 >> 16, x2 >> 16, r1, g1, b1, r2, g2, b2, z1, depthSlope, bufferOffset);
				x2 += dx3;
				x1 += dx1;
				r2 += dr3;
				g2 += dg3;
				b2 += db3;
				r1 += dr1;
				g1 += dg1;
				b1 += db1;
				z1 += depthScale;
			}
			while (--y2 >= 0) {
				drawGouraudScanline(pixels, y1, x1 >> 16, x3 >> 16, r1, g1, b1, r3, g3, b3, z1, depthSlope, bufferOffset);
				x3 += dx2;
				x1 += dx1;
				r3 += dr2;
				g3 += dg2;
				b3 += db2;
				r1 += dr1;
				g1 += dg1;
				b1 += db1;
				y1 += width;
				z1 += depthScale;
			}
			return;
		}
		if (y2 <= y3) {
			if (y2 >= bottomY) {
				return;
			}
			if (y3 > bottomY) {
				y3 = bottomY;
			}
			if (y1 > bottomY) {
				y1 = bottomY;
			}
			z2 = z2 - depthSlope * x2 + depthSlope;
			if (y3 < y1) {
				x1 = x2 <<= 16;
				r1 = r2 <<= 16;
				g1 = g2 <<= 16;
				b1 = b2 <<= 16;
				if (y2 < 0) {
					x1 -= dx1 * y2;
					x2 -= dx2 * y2;
					r1 -= dr1 * y2;
					g1 -= dg1 * y2;
					b1 -= db1 * y2;
					r2 -= dr2 * y2;
					g2 -= dg2 * y2;
					b2 -= db2 * y2;
					z2 -= depthScale * y2;
					y2 = 0;
				}
				x3 <<= 16;
				r3 <<= 16;
				g3 <<= 16;
				b3 <<= 16;
				if (y3 < 0) {
					x3 -= dx3 * y3;
					r3 -= dr3 * y3;
					g3 -= dg3 * y3;
					b3 -= db3 * y3;
					y3 = 0;
				}
				if (y2 != y3 && dx1 < dx2 || y2 == y3 && dx1 > dx3) {
					y1 -= y3;
					y3 -= y2;
					for (y2 = lineOffsets[y2]; --y3 >= 0; y2 += width) {
						drawGouraudScanline(pixels, y2, x1 >> 16, x2 >> 16, r1, g1, b1, r2, g2, b2, z2, depthSlope, bufferOffset);
						x1 += dx1;
						x2 += dx2;
						r1 += dr1;
						g1 += dg1;
						b1 += db1;
						r2 += dr2;
						g2 += dg2;
						b2 += db2;
						z2 += depthScale;
					}
					while (--y1 >= 0) {
						drawGouraudScanline(pixels, y2, x1 >> 16, x3 >> 16, r1, g1, b1, r3, g3, b3, z2, depthSlope, bufferOffset);
						x1 += dx1;
						x3 += dx3;
						r1 += dr1;
						g1 += dg1;
						b1 += db1;
						r3 += dr3;
						g3 += dg3;
						b3 += db3;
						y2 += width;
						z2 += depthScale;
					}
					return;
				}
				y1 -= y3;
				y3 -= y2;
				for (y2 = lineOffsets[y2]; --y3 >= 0; y2 += width) {
					drawGouraudScanline(pixels, y2, x2 >> 16, x1 >> 16, r2, g2, b2, r1, g1, b1, z2, depthSlope, bufferOffset);
					x1 += dx1;
					x2 += dx2;
					r1 += dr1;
					g1 += dg1;
					b1 += db1;
					r2 += dr2;
					g2 += dg2;
					b2 += db2;
					z2 += depthScale;
				}
				while (--y1 >= 0) {
					drawGouraudScanline(pixels, y2, x3 >> 16, x1 >> 16, r3, g3, b3, r1, g1, b1, z2, depthSlope, bufferOffset);
					x1 += dx1;
					x3 += dx3;
					r1 += dr1;
					g1 += dg1;
					b1 += db1;
					r3 += dr3;
					g3 += dg3;
					b3 += db3;
					y2 += width;
					z2 += depthScale;
				}
				return;
			}
			x3 = x2 <<= 16;
			r3 = r2 <<= 16;
			g3 = g2 <<= 16;
			b3 = b2 <<= 16;
			if (y2 < 0) {
				x3 -= dx1 * y2;
				x2 -= dx2 * y2;
				r3 -= dr1 * y2;
				g3 -= dg1 * y2;
				b3 -= db1 * y2;
				r2 -= dr2 * y2;
				g2 -= dg2 * y2;
				b2 -= db2 * y2;
				z2 -= depthScale * y2;
				y2 = 0;
			}
			x1 <<= 16;
			r1 <<= 16;
			g1 <<= 16;
			b1 <<= 16;
			if (y1 < 0) {
				x1 -= dx3 * y1;
				r1 -= dr3 * y1;
				g1 -= dg3 * y1;
				b1 -= db3 * y1;
				y1 = 0;
			}
			if (dx1 < dx2) {
				y3 -= y1;
				y1 -= y2;
				for (y2 = lineOffsets[y2]; --y1 >= 0; y2 += width) {
					drawGouraudScanline(pixels, y2, x3 >> 16, x2 >> 16, r3, g3, b3, r2, g2, b2, z2, depthSlope, bufferOffset);
					x3 += dx1;
					x2 += dx2;
					r3 += dr1;
					g3 += dg1;
					b3 += db1;
					r2 += dr2;
					g2 += dg2;
					b2 += db2;
					z2 += depthScale;
				}
				while (--y3 >= 0) {
					drawGouraudScanline(pixels, y2, x1 >> 16, x2 >> 16, r1, g1, b1, r2, g2, b2, z2, depthSlope, bufferOffset);
					x1 += dx3;
					x2 += dx2;
					r1 += dr3;
					g1 += dg3;
					b1 += db3;
					r2 += dr2;
					g2 += dg2;
					b2 += db2;
					y2 += width;
					z2 += depthScale;
				}
				return;
			}
			y3 -= y1;
			y1 -= y2;
			for (y2 = lineOffsets[y2]; --y1 >= 0; y2 += width) {
				drawGouraudScanline(pixels, y2, x2 >> 16, x3 >> 16, r2, g2, b2, r3, g3, b3, z2, depthSlope, bufferOffset);
				x3 += dx1;
				x2 += dx2;
				r3 += dr1;
				g3 += dg1;
				b3 += db1;
				r2 += dr2;
				g2 += dg2;
				b2 += db2;
				z2 += depthScale;
			}
			while (--y3 >= 0) {
				drawGouraudScanline(pixels, y2, x2 >> 16, x1 >> 16, r2, g2, b2, r1, g1, b1, z2, depthSlope, bufferOffset);
				x1 += dx3;
				x2 += dx2;
				r1 += dr3;
				g1 += dg3;
				b1 += db3;
				r2 += dr2;
				g2 += dg2;
				b2 += db2;
				y2 += width;
				z2 += depthScale;
			}
			return;
		}
		if (y3 >= bottomY) {
			return;
		}
		if (y1 > bottomY) {
			y1 = bottomY;
		}
		if (y2 > bottomY) {
			y2 = bottomY;
		}
		z3 = z3 - depthSlope * x3 + depthSlope;
		if (y1 < y2) {
			x2 = x3 <<= 16;
			r2 = r3 <<= 16;
			g2 = g3 <<= 16;
			b2 = b3 <<= 16;
			if (y3 < 0) {
				x2 -= dx2 * y3;
				x3 -= dx3 * y3;
				r2 -= dr2 * y3;
				g2 -= dg2 * y3;
				b2 -= db2 * y3;
				r3 -= dr3 * y3;
				g3 -= dg3 * y3;
				b3 -= db3 * y3;
				z3 -= depthScale * y3;
				y3 = 0;
			}
			x1 <<= 16;
			r1 <<= 16;
			g1 <<= 16;
			b1 <<= 16;
			if (y1 < 0) {
				x1 -= dx1 * y1;
				r1 -= dr1 * y1;
				g1 -= dg1 * y1;
				b1 -= db1 * y1;
				y1 = 0;
			}
			if (dx2 < dx3) {
				y2 -= y1;
				y1 -= y3;
				for (y3 = lineOffsets[y3]; --y1 >= 0; y3 += width) {
					drawGouraudScanline(pixels, y3, x2 >> 16, x3 >> 16, r2, g2, b2, r3, g3, b3, z3, depthSlope, bufferOffset);
					x2 += dx2;
					x3 += dx3;
					r2 += dr2;
					g2 += dg2;
					b2 += db2;
					r3 += dr3;
					g3 += dg3;
					b3 += db3;
					z3 += depthScale;
				}
				while (--y2 >= 0) {
					drawGouraudScanline(pixels, y3, x2 >> 16, x1 >> 16, r2, g2, b2, r1, g1, b1, z3, depthSlope, bufferOffset);
					x2 += dx2;
					x1 += dx1;
					r2 += dr2;
					g2 += dg2;
					b2 += db2;
					r1 += dr1;
					g1 += dg1;
					b1 += db1;
					y3 += width;
					z3 += depthScale;
				}
				return;
			}
			y2 -= y1;
			y1 -= y3;
			for (y3 = lineOffsets[y3]; --y1 >= 0; y3 += width) {
				drawGouraudScanline(pixels, y3, x3 >> 16, x2 >> 16, r3, g3, b3, r2, g2, b2, z3, depthSlope, bufferOffset);
				x2 += dx2;
				x3 += dx3;
				r2 += dr2;
				g2 += dg2;
				b2 += db2;
				r3 += dr3;
				g3 += dg3;
				b3 += db3;
				z3 += depthScale;
			}
			while (--y2 >= 0) {
				drawGouraudScanline(pixels, y3, x1 >> 16, x2 >> 16, r1, g1, b1, r2, g2, b2, z3, depthSlope, bufferOffset);
				x2 += dx2;
				x1 += dx1;
				r2 += dr2;
				g2 += dg2;
				b2 += db2;
				r1 += dr1;
				g1 += dg1;
				b1 += db1;
				z3 += depthScale;
				y3 += width;
			}
			return;
		}
		x1 = x3 <<= 16;
		r1 = r3 <<= 16;
		g1 = g3 <<= 16;
		b1 = b3 <<= 16;
		if (y3 < 0) {
			x1 -= dx2 * y3;
			x3 -= dx3 * y3;
			r1 -= dr2 * y3;
			g1 -= dg2 * y3;
			b1 -= db2 * y3;
			r3 -= dr3 * y3;
			g3 -= dg3 * y3;
			b3 -= db3 * y3;
			z3 -= depthScale * y3;
			y3 = 0;
		}
		x2 <<= 16;
		r2 <<= 16;
		g2 <<= 16;
		b2 <<= 16;
		if (y2 < 0) {
			x2 -= dx1 * y2;
			r2 -= dr1 * y2;
			g2 -= dg1 * y2;
			b2 -= db1 * y2;
			y2 = 0;
		}
		if (dx2 < dx3) {
			y1 -= y2;
			y2 -= y3;
			for (y3 = lineOffsets[y3]; --y2 >= 0; y3 += width) {
				drawGouraudScanline(pixels, y3, x1 >> 16, x3 >> 16, r1, g1, b1, r3, g3, b3, z3, depthSlope, bufferOffset);
				x1 += dx2;
				x3 += dx3;
				r1 += dr2;
				g1 += dg2;
				b1 += db2;
				r3 += dr3;
				g3 += dg3;
				b3 += db3;
				z3 += depthScale;
			}
			while (--y1 >= 0) {
				drawGouraudScanline(pixels, y3, x2 >> 16, x3 >> 16, r2, g2, b2, r3, g3, b3, z3, depthSlope, bufferOffset);
				x2 += dx1;
				x3 += dx3;
				r2 += dr1;
				g2 += dg1;
				b2 += db1;
				r3 += dr3;
				g3 += dg3;
				b3 += db3;
				z3 += depthScale;
				y3 += width;
			}
			return;
		}
		y1 -= y2;
		y2 -= y3;
		for (y3 = lineOffsets[y3]; --y2 >= 0; y3 += width) {
			drawGouraudScanline(pixels, y3, x3 >> 16, x1 >> 16, r3, g3, b3, r1, g1, b1, z3, depthSlope, bufferOffset);
			x1 += dx2;
			x3 += dx3;
			r1 += dr2;
			g1 += dg2;
			b1 += db2;
			r3 += dr3;
			g3 += dg3;
			b3 += db3;
			z3 += depthScale;
		}
		while (--y1 >= 0) {
			drawGouraudScanline(pixels, y3, x3 >> 16, x2 >> 16, r3, g3, b3, r2, g2, b2, z3, depthSlope, bufferOffset);
			x2 += dx1;
			x3 += dx3;
			r2 += dr1;
			g2 += dg1;
			b2 += db1;
			r3 += dr3;
			g3 += dg3;
			b3 += db3;
			y3 += width;
			z3 += depthScale;
		}
	}
	
	public static void drawGouraudScanline(int[] dest, int offset, int x1, int x2, int r1, int g1, int b1, int r2, int g2, int b2, float z1, float z2, int bufferOffset) {
		int n = x2 - x1;
		if (n <= 0) {
			return;
		}
		r2 = (r2 - r1) / n;
		g2 = (g2 - g1) / n;
		b2 = (b2 - b1) / n;
		if (aBoolean1462) {
			if (x2 > DrawingArea.centerX) {
				n -= x2 - DrawingArea.centerX;
				x2 = DrawingArea.centerX;
			}
			if (x1 < 0) {
				n = x2;
				r1 -= x1 * r2;
				g1 -= x1 * g2;
				b1 -= x1 * b2;
				x1 = 0;
			}
		}
		if (x1 < x2) {
			offset += x1;
			z1 += z2 * x1;
			if (anInt1465 == 0) {
				while (--n >= 0) {
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = (r1 & 0xff0000) | (g1 >> 8 & 0xff00) | (b1 >> 16 & 0xff);
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					r1 += r2;
					g1 += g2;
					b1 += b2;
					offset++;
				}
			} else {
				int opacity = 256 - anInt1465;
				while (--n >= 0) {
					int rgb = r1 & 0xff0000 | g1 >> 8 & 0xff00 | b1 >> 16 & 0xff;
					rgb = ((rgb & 0xff00ff) * opacity >> 8 & 0xff00ff) + ((rgb & 0xff00) * opacity >> 8 & 0xff00);
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = rgb + ((dest[offset] & 0xff00ff) * anInt1465 >> 8 & 0xff00ff) + ((dest[offset] & 0xff00) * anInt1465 >> 8 & 0xff00);
						depthBuffer[offset] = z1;
					}
					offset++;
					z1 += z2;
					r1 += r2;
					g1 += g2;
					b1 += b2;
				}
			}
		}
	}

	public static void drawFlatTriangle(int y1, int y2, int y3, int x1, int x2, int x3, int rgb, float z1, float z2, float z3, int bufferOffset) {
		int dx1 = 0;
		if(y2 != y1) {
			dx1 = (x2 - x1 << 16) / (y2 - y1);
		}
		int dx2 = 0;
		if(y3 != y2) {
			dx2 = (x3 - x2 << 16) / (y3 - y2);
		}
		int dx3 = 0;
		if(y3 != y1) {
			dx3 = (x1 - x3 << 16) / (y1 - y3);
		}
		
		float x21 = x2 - x1;
		float y21 = y2 - y1;
		float x31 = x3 - x1;
		float y31 = y3 - y1;
		float z21 = z2 - z1;
		float z31 = z3 - z1;

		float div = x21 * y31 - x31 * y21;
		float depthSlope = (z21 * y31 - z31 * y21) / div;
		float depthScale = (z31 * x21 - z21 * x31) / div;
		
		if(y1 <= y2 && y1 <= y3) {
			if(y1 >= bottomY) {
				return;
			}
			if(y2 > bottomY) {
				y2 = bottomY;
			}
			if(y3 > bottomY) {
				y3 = bottomY;
			}
			z1 = z1 - depthSlope * x1 + depthSlope;
			if(y2 < y3) {
				x3 = x1 <<= 16;
				if(y1 < 0) {
					x3 -= dx3 * y1;
					x1 -= dx1 * y1;
					z1 -= depthScale * y1;
					y1 = 0;
				}
				x2 <<= 16;
				if(y2 < 0) {
					x2 -= dx2 * y2;
					y2 = 0;
				}
				if(y1 != y2 && dx3 < dx1 || y1 == y2 && dx3 > dx2) {
					y3 -= y2;
					y2 -= y1;
					for(y1 = lineOffsets[y1]; --y2 >= 0; y1 += width) {
						drawFlatScanline(pixels, y1, rgb, x3 >> 16, x1 >> 16, z1, depthSlope, bufferOffset);
						z1 += depthScale;
						x3 += dx3;
						x1 += dx1;
					}
					while(--y3 >= 0) {
						drawFlatScanline(pixels, y1, rgb, x3 >> 16, x2 >> 16, z1, depthSlope, bufferOffset);
						z1 += depthScale;
						x3 += dx3;
						x2 += dx2;
						y1 += width;
					}
					return;
				}
				y3 -= y2;
				y2 -= y1;
				for(y1 = lineOffsets[y1]; --y2 >= 0; y1 += width) {
					drawFlatScanline(pixels, y1, rgb, x1 >> 16, x3 >> 16, z1, depthSlope, bufferOffset);
					z1 += depthScale;
					x3 += dx3;
					x1 += dx1;
				}
				while(--y3 >= 0) {
					drawFlatScanline(pixels, y1, rgb, x2 >> 16, x3 >> 16, z1, depthSlope, bufferOffset);
					z1 += depthScale;
					x3 += dx3;
					x2 += dx2;
					y1 += width;
				}
				return;
			}
			x2 = x1 <<= 16;
			if(y1 < 0) {
				x2 -= dx3 * y1;
				x1 -= dx1 * y1;
				z1 -= depthScale * y1;
				y1 = 0;
			}
			x3 <<= 16;
			if(y3 < 0) {
				x3 -= dx2 * y3;
				y3 = 0;
			}
			if(y1 != y3 && dx3 < dx1 || y1 == y3 && dx2 > dx1) {
				y2 -= y3;
				y3 -= y1;
				for(y1 = lineOffsets[y1]; --y3 >= 0; y1 += width) {
					drawFlatScanline(pixels, y1, rgb, x2 >> 16, x1 >> 16, z1, depthSlope, bufferOffset);
					z1 += depthScale;
					x2 += dx3;
					x1 += dx1;
				}
				while(--y2 >= 0) {
					drawFlatScanline(pixels, y1, rgb, x3 >> 16, x1 >> 16, z1, depthSlope, bufferOffset);
					z1 += depthScale;
					x3 += dx2;
					x1 += dx1;
					y1 += width;
				}
				return;
			}
			y2 -= y3;
			y3 -= y1;
			for(y1 = lineOffsets[y1]; --y3 >= 0; y1 += width) {
				drawFlatScanline(pixels, y1, rgb, x1 >> 16, x2 >> 16, z1, depthSlope, bufferOffset);
				z1 += depthScale;
				x2 += dx3;
				x1 += dx1;
			}
			while(--y2 >= 0) {
				drawFlatScanline(pixels, y1, rgb, x1 >> 16, x3 >> 16, z1, depthSlope, bufferOffset);
				z1 += depthScale;
				x3 += dx2;
				x1 += dx1;
				y1 += width;
			}
			return;
		}
		if(y2 <= y3) {
			if(y2 >= bottomY) {
				return;
			}
			if(y3 > bottomY) {
				y3 = bottomY;
			}
			if(y1 > bottomY) {
				y1 = bottomY;
			}
			z2 = z2 - depthSlope * x2 + depthSlope;
			if(y3 < y1) {
				x1 = x2 <<= 16;
				if(y2 < 0) {
					x1 -= dx1 * y2;
					x2 -= dx2 * y2;
					z2 -= depthScale * y2;
					y2 = 0;
				}
				x3 <<= 16;
				if(y3 < 0) {
					x3 -= dx3 * y3;
					y3 = 0;
				}
				if(y2 != y3 && dx1 < dx2 || y2 == y3 && dx1 > dx3) {
					y1 -= y3;
					y3 -= y2;
					for(y2 = lineOffsets[y2]; --y3 >= 0; y2 += width) {
						drawFlatScanline(pixels, y2, rgb, x1 >> 16, x2 >> 16, z2, depthSlope, bufferOffset);
						z2 += depthScale;
						x1 += dx1;
						x2 += dx2;
					}
					while(--y1 >= 0) {
						drawFlatScanline(pixels, y2, rgb, x1 >> 16, x3 >> 16, z2, depthSlope, bufferOffset);
						z2 += depthScale;
						x1 += dx1;
						x3 += dx3;
						y2 += width;
					}
					return;
				}
				y1 -= y3;
				y3 -= y2;
				for(y2 = lineOffsets[y2]; --y3 >= 0; y2 += width) {
					drawFlatScanline(pixels, y2, rgb, x2 >> 16, x1 >> 16, z2, depthSlope, bufferOffset);
					z2 += depthScale;
					x1 += dx1;
					x2 += dx2;
				}
				while(--y1 >= 0) {
					drawFlatScanline(pixels, y2, rgb, x3 >> 16, x1 >> 16, z2, depthSlope, bufferOffset);
					z2 += depthScale;
					x1 += dx1;
					x3 += dx3;
					y2 += width;
				}
				return;
			}
			x3 = x2 <<= 16;
			if(y2 < 0) {
				x3 -= dx1 * y2;
				x2 -= dx2 * y2;
				z2 -= depthScale * y2;
				y2 = 0;
			}
			x1 <<= 16;
			if(y1 < 0) {
				x1 -= dx3 * y1;
				y1 = 0;
			}
			if(dx1 < dx2) {
				y3 -= y1;
				y1 -= y2;
				for(y2 = lineOffsets[y2]; --y1 >= 0; y2 += width) {
					drawFlatScanline(pixels, y2, rgb, x3 >> 16, x2 >> 16, z2, depthSlope, bufferOffset);
					z2 += depthScale;
					x3 += dx1;
					x2 += dx2;
				}
				while(--y3 >= 0) {
					drawFlatScanline(pixels, y2, rgb, x1 >> 16, x2 >> 16, z2, depthSlope, bufferOffset);
					z2 += depthScale;
					x1 += dx3;
					x2 += dx2;
					y2 += width;
				}
				return;
			}
			y3 -= y1;
			y1 -= y2;
			for(y2 = lineOffsets[y2]; --y1 >= 0; y2 += width) {
				drawFlatScanline(pixels, y2, rgb, x2 >> 16, x3 >> 16, z2, depthSlope, bufferOffset);
				z2 += depthScale;
				x3 += dx1;
				x2 += dx2;
			}
			while(--y3 >= 0) {
				drawFlatScanline(pixels, y2, rgb, x2 >> 16, x1 >> 16, z2, depthSlope, bufferOffset);
				z2 += depthScale;
				x1 += dx3;
				x2 += dx2;
				y2 += width;
			}
			return;
		}
		if(y3 >= bottomY) {
			return;
		}
		if(y1 > bottomY) {
			y1 = bottomY;
		}
		if(y2 > bottomY) {
			y2 = bottomY;
		}
		z3 = z3 - depthSlope * x3 + depthSlope;
		if(y1 < y2) {
			x2 = x3 <<= 16;
			if(y3 < 0) {
				x2 -= dx2 * y3;
				x3 -= dx3 * y3;
				z3 -= depthScale * y3;
				y3 = 0;
			}
			x1 <<= 16;
			if(y1 < 0) {
				x1 -= dx1 * y1;
				y1 = 0;
			}
			if(dx2 < dx3) {
				y2 -= y1;
				y1 -= y3;
				for(y3 = lineOffsets[y3]; --y1 >= 0; y3 += width) {
					drawFlatScanline(pixels, y3, rgb, x2 >> 16, x3 >> 16, z3, depthSlope, bufferOffset);
					z3 += depthScale;
					x2 += dx2;
					x3 += dx3;
				}
				while(--y2 >= 0) {
					drawFlatScanline(pixels, y3, rgb, x2 >> 16, x1 >> 16, z3, depthSlope, bufferOffset);
					z3 += depthScale;
					x2 += dx2;
					x1 += dx1;
					y3 += width;
				}
				return;
			}
			y2 -= y1;
			y1 -= y3;
			for(y3 = lineOffsets[y3]; --y1 >= 0; y3 += width) {
				drawFlatScanline(pixels, y3, rgb, x3 >> 16, x2 >> 16, z3, depthSlope, bufferOffset);
				z3 += depthScale;
				x2 += dx2;
				x3 += dx3;
			}
			while(--y2 >= 0) {
				drawFlatScanline(pixels, y3, rgb, x1 >> 16, x2 >> 16, z3, depthSlope, bufferOffset);
				z3 += depthScale;
				x2 += dx2;
				x1 += dx1;
				y3 += width;
			}
			return;
		}
		x1 = x3 <<= 16;
		if(y3 < 0) {
			x1 -= dx2 * y3;
			x3 -= dx3 * y3;
			z3 -= depthScale * y3;
			y3 = 0;
		}
		x2 <<= 16;
		if(y2 < 0) {
			x2 -= dx1 * y2;
			y2 = 0;
		}
		if(dx2 < dx3) {
			y1 -= y2;
			y2 -= y3;
			for(y3 = lineOffsets[y3]; --y2 >= 0; y3 += width) {
				drawFlatScanline(pixels, y3, rgb, x1 >> 16, x3 >> 16, z3, depthSlope, bufferOffset);
				z3 += depthScale;
				x1 += dx2;
				x3 += dx3;
			}
			while(--y1 >= 0) {
				drawFlatScanline(pixels, y3, rgb, x2 >> 16, x3 >> 16, z3, depthSlope, bufferOffset);
				z3 += depthScale;
				x2 += dx1;
				x3 += dx3;
				y3 += width;
			}
			return;
		}
		y1 -= y2;
		y2 -= y3;
		for(y3 = lineOffsets[y3]; --y2 >= 0; y3 += width) {
			drawFlatScanline(pixels, y3, rgb, x3 >> 16, x1 >> 16, z3, depthSlope, bufferOffset);
			z3 += depthScale;
			x1 += dx2;
			x3 += dx3;
		}
		while(--y1 >= 0) {
			drawFlatScanline(pixels, y3, rgb, x3 >> 16, x2 >> 16, z3, depthSlope, bufferOffset);
			z3 += depthScale;
			x2 += dx1;
			x3 += dx3;
			y3 += width;
		}
	}
	
	private static void drawFlatScanline(int dest[], int offset, int rgb, int x1, int x2, float z1, float z2, int bufferOffset) {
    	if(aBoolean1462) {
			if(x2 > DrawingArea.centerX) {
				x2 = DrawingArea.centerX;
			}
			if(x1 < 0) {
				z1 -= x1 * z2;
				x1 = 0;
			}
		}
		if(x1 >= x2) {
			return;
		}
		offset += x1;
		z1 += z2 * x1;
		int len = x2 - x1 >> 2;
		if(anInt1465 == 0) {
			while(--len >= 0)  {
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = rgb;
					depthBuffer[offset] = z1;
				}
				z1 += z2;
				offset++;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = rgb;
					depthBuffer[offset] = z1;
				}
				z1 += z2;
				offset++;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = rgb;
					depthBuffer[offset] = z1;
				}
				z1 += z2;
				offset++;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = rgb;
					depthBuffer[offset] = z1;
				}
				z1 += z2;
				offset++;
			}
			for(len = x2 - x1 & 3; --len >= 0;) {
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = rgb;
					depthBuffer[offset] = z1;
				}
				z1 += z2;
				offset++;
			}
			return;
		}
		
		int opacity = 256 - anInt1465;
		rgb = ((rgb & 0xff00ff) * opacity >> 8 & 0xff00ff) + ((rgb & 0xff00) * opacity >> 8 & 0xff00);
		while(--len >= 0) {
			if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
				dest[offset] = rgb + ((dest[offset] & 0xff00ff) * anInt1465 >> 8 & 0xff00ff) + ((dest[offset] & 0xff00) * anInt1465 >> 8 & 0xff00);
				depthBuffer[offset] = z1;
			}
			z1 += z2;
			offset++;
			if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
				dest[offset] = rgb + ((dest[offset] & 0xff00ff) * anInt1465 >> 8 & 0xff00ff) + ((dest[offset] & 0xff00) * anInt1465 >> 8 & 0xff00);
				depthBuffer[offset] = z1;
			}
			z1 += z2;
			offset++;
			if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
				dest[offset] = rgb + ((dest[offset] & 0xff00ff) * anInt1465 >> 8 & 0xff00ff) + ((dest[offset] & 0xff00) * anInt1465 >> 8 & 0xff00);
				depthBuffer[offset] = z1;
			}
			z1 += z2;
			offset++;
			if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
				dest[offset] = rgb + ((dest[offset] & 0xff00ff) * anInt1465 >> 8 & 0xff00ff) + ((dest[offset] & 0xff00) * anInt1465 >> 8 & 0xff00);
				depthBuffer[offset] = z1;
			}
			z1 += z2;
			offset++;
		}
		for(len = x2 - x1 & 3; --len >= 0;) {
			if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
				dest[offset] = rgb + ((dest[offset] & 0xff00ff) * anInt1465 >> 8 & 0xff00ff) + ((dest[offset] & 0xff00) * anInt1465 >> 8 & 0xff00);
				depthBuffer[offset] = z1;
			}
			z1 += z2;
			offset++;
		}
	}

	public static void drawTexturedTriangle(int y1, int y2, int y3, int x1, int x2, int x3, int c1, int c2, int c3, int tx1, int tx2, int tx3, int ty1, int ty2, int ty3, int tz1, int tz2, int tz3, int tex, float z1, float z2, float z3, int bufferOffset) {
		c1 = 0x7f - c1 << 1;
		c2 = 0x7f - c2 << 1;
		c3 = 0x7f - c3 << 1;
		//System.out.println("texL " + tex);
		int texels[] = method371(tex);
		aBoolean1463 = !aBooleanArray1475[tex];
		tx2 = tx1 - tx2;
		ty2 = ty1 - ty2;
		tz2 = tz1 - tz2;
		tx3 -= tx1;
		ty3 -= ty1;
		tz3 -= tz1;
		int l4 = tx3 * ty1 - ty3 * tx1 << (Client.log_view_dist == 9 ? 14 : 15);
		int i5 = ty3 * tz1 - tz3 * ty1 << 8;
		int j5 = tz3 * tx1 - tx3 * tz1 << 5;
		int k5 = tx2 * ty1 - ty2 * tx1 << (Client.log_view_dist == 9 ? 14 : 15);
		int l5 = ty2 * tz1 - tz2 * ty1 << 8;
		int i6 = tz2 * tx1 - tx2 * tz1 << 5;
		int j6 = ty2 * tx3 - tx2 * ty3 << (Client.log_view_dist == 9 ? 14 : 15);
		int k6 = tz2 * ty3 - ty2 * tz3 << 8;
		int l6 = tx2 * tz3 - tz2 * tx3 << 5;
		int i7 = 0;
		int j7 = 0;
		if (y2 != y1) {
			i7 = (x2 - x1 << 16) / (y2 - y1);
			j7 = (c2 - c1 << 16) / (y2 - y1);
		}
		int k7 = 0;
		int l7 = 0;
		if (y3 != y2) {
			k7 = (x3 - x2 << 16) / (y3 - y2);
			l7 = (c3 - c2 << 16) / (y3 - y2);
		}
		int i8 = 0;
		int j8 = 0;
		if (y3 != y1) {
			i8 = (x1 - x3 << 16) / (y1 - y3);
			j8 = (c1 - c3 << 16) / (y1 - y3);
		}
		
		float x21 = x2 - x1;
		float y21 = y2 - y1;
		float x31 = x3 - x1;
		float y31 = y3 - y1;
		float z21 = z2 - z1;
		float z31 = z3 - z1;

		float div = x21 * y31 - x31 * y21;
		float depthSlope = (z21 * y31 - z31 * y21) / div;
		float depthScale = (z31 * x21 - z21 * x31) / div;
		
		if (y1 <= y2 && y1 <= y3) {
			if (y1 >= bottomY) {
				return;
			}
			if (y2 > bottomY) {
				y2 = bottomY;
			}
			if (y3 > bottomY) {
				y3 = bottomY;
			}
			z1 = z1 - depthSlope * x1 + depthSlope;
			if (y2 < y3) {
				x3 = x1 <<= 16;
				c3 = c1 <<= 16;
				if (y1 < 0) {
					x3 -= i8 * y1;
					x1 -= i7 * y1;
					z1 -= depthScale * y1;
					c3 -= j8 * y1;
					c1 -= j7 * y1;
					y1 = 0;
				}
				x2 <<= 16;
				c2 <<= 16;
				if (y2 < 0) {
					x2 -= k7 * y2;
					c2 -= l7 * y2;
					y2 = 0;
				}
				int k8 = y1 - centerY;
				l4 += j5 * k8;
				k5 += i6 * k8;
				j6 += l6 * k8;
				if (y1 != y2 && i8 < i7 || y1 == y2 && i8 > k7) {
					y3 -= y2;
					y2 -= y1;
					y1 = lineOffsets[y1];
					while (--y2 >= 0) {
						drawTexturedScanline(pixels, texels, y1, x3 >> 16, x1 >> 16, c3, c1, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
						z1 += depthScale;
						x3 += i8;
						x1 += i7;
						c3 += j8;
						c1 += j7;
						y1 += width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					while (--y3 >= 0) {
						drawTexturedScanline(pixels, texels, y1, x3 >> 16, x2 >> 16, c3, c2, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
						z1 += depthScale;
						x3 += i8;
						x2 += k7;
						c3 += j8;
						c2 += l7;
						y1 += width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					return;
				}
				y3 -= y2;
				y2 -= y1;
				y1 = lineOffsets[y1];
				while (--y2 >= 0) {
					drawTexturedScanline(pixels, texels, y1, x1 >> 16, x3 >> 16, c1, c3, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
					z1 += depthScale;
					x3 += i8;
					x1 += i7;
					c3 += j8;
					c1 += j7;
					y1 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y3 >= 0) {
					drawTexturedScanline(pixels, texels, y1, x2 >> 16, x3 >> 16, c2, c3, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
					z1 += depthScale;
					x3 += i8;
					x2 += k7;
					c3 += j8;
					c2 += l7;
					y1 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			x2 = x1 <<= 16;
			c2 = c1 <<= 16;
			if (y1 < 0) {
				x2 -= i8 * y1;
				z1 -= depthScale * y1;
				x1 -= i7 * y1;
				c2 -= j8 * y1;
				c1 -= j7 * y1;
				y1 = 0;
			}
			x3 <<= 16;
			c3 <<= 16;
			if (y3 < 0) {
				x3 -= k7 * y3;
				c3 -= l7 * y3;
				y3 = 0;
			}
			int l8 = y1 - centerY;
			l4 += j5 * l8;
			k5 += i6 * l8;
			j6 += l6 * l8;
			if (y1 != y3 && i8 < i7 || y1 == y3 && k7 > i7) {
				y2 -= y3;
				y3 -= y1;
				y1 = lineOffsets[y1];
				while (--y3 >= 0) {
					drawTexturedScanline(pixels, texels, y1, x2 >> 16, x1 >> 16, c2, c1, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
					z1 += depthScale;
					x2 += i8;
					x1 += i7;
					c2 += j8;
					c1 += j7;
					y1 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y2 >= 0) {
					drawTexturedScanline(pixels, texels, y1, x3 >> 16, x1 >> 16, c3, c1, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
					z1 += depthScale;
					x3 += k7;
					x1 += i7;
					c3 += l7;
					c1 += j7;
					y1 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			y2 -= y3;
			y3 -= y1;
			y1 = lineOffsets[y1];
			while (--y3 >= 0) {
				drawTexturedScanline(pixels, texels, y1, x1 >> 16, x2 >> 16, c1, c2, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
				z1 += depthScale;
				x2 += i8;
				x1 += i7;
				c2 += j8;
				c1 += j7;
				y1 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while (--y2 >= 0) {
				drawTexturedScanline(pixels, texels, y1, x1 >> 16, x3 >> 16, c1, c3, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
				z1 += depthScale;
				x3 += k7;
				x1 += i7;
				c3 += l7;
				c1 += j7;
				y1 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		if (y2 <= y3) {
			if (y2 >= bottomY) {
				return;
			}
			if (y3 > bottomY) {
				y3 = bottomY;
			}
			if (y1 > bottomY) {
				y1 = bottomY;
			}
			z2 = z2 - depthSlope * x2 + depthSlope;
			if (y3 < y1) {
				x1 = x2 <<= 16;
				c1 = c2 <<= 16;
				if (y2 < 0) {
					x1 -= i7 * y2;
					x2 -= k7 * y2;
					z2 -= depthScale * y2;
					c1 -= j7 * y2;
					c2 -= l7 * y2;
					y2 = 0;
				}
				x3 <<= 16;
				c3 <<= 16;
				if (y3 < 0) {
					x3 -= i8 * y3;
					c3 -= j8 * y3;
					y3 = 0;
				}
				int i9 = y2 - centerY;
				l4 += j5 * i9;
				k5 += i6 * i9;
				j6 += l6 * i9;
				if (y2 != y3 && i7 < k7 || y2 == y3 && i7 > i8) {
					y1 -= y3;
					y3 -= y2;
					y2 = lineOffsets[y2];
					while (--y3 >= 0) {
						drawTexturedScanline(pixels, texels, y2, x1 >> 16, x2 >> 16, c1, c2, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
						z2 += depthScale;
						x1 += i7;
						x2 += k7;
						c1 += j7;
						c2 += l7;
						y2 += width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					while (--y1 >= 0) {
						drawTexturedScanline(pixels, texels, y2, x1 >> 16, x3 >> 16, c1, c3, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
						z2 += depthScale;
						x1 += i7;
						x3 += i8;
						c1 += j7;
						c3 += j8;
						y2 += width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					return;
				}
				y1 -= y3;
				y3 -= y2;
				y2 = lineOffsets[y2];
				while (--y3 >= 0) {
					drawTexturedScanline(pixels, texels, y2, x2 >> 16, x1 >> 16, c2, c1, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
					z2 += depthScale;
					x1 += i7;
					x2 += k7;
					c1 += j7;
					c2 += l7;
					y2 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y1 >= 0) {
					drawTexturedScanline(pixels, texels, y2, x3 >> 16, x1 >> 16, c3, c1, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
					z2 += depthScale;
					x1 += i7;
					x3 += i8;
					c1 += j7;
					c3 += j8;
					y2 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			x3 = x2 <<= 16;
			c3 = c2 <<= 16;
			if (y2 < 0) {
				x3 -= i7 * y2;
				z2 -= depthScale * y2;
				x2 -= k7 * y2;
				c3 -= j7 * y2;
				c2 -= l7 * y2;
				y2 = 0;
			}
			x1 <<= 16;
			c1 <<= 16;
			if (y1 < 0) {
				x1 -= i8 * y1;
				c1 -= j8 * y1;
				y1 = 0;
			}
			int j9 = y2 - centerY;
			l4 += j5 * j9;
			k5 += i6 * j9;
			j6 += l6 * j9;
			if (i7 < k7) {
				y3 -= y1;
				y1 -= y2;
				y2 = lineOffsets[y2];
				while (--y1 >= 0) {
					drawTexturedScanline(pixels, texels, y2, x3 >> 16, x2 >> 16, c3, c2, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
					z2 += depthScale;
					x3 += i7;
					x2 += k7;
					c3 += j7;
					c2 += l7;
					y2 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y3 >= 0) {
					drawTexturedScanline(pixels, texels, y2, x1 >> 16, x2 >> 16, c1, c2, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
					z2 += depthScale;
					x1 += i8;
					x2 += k7;
					c1 += j8;
					c2 += l7;
					y2 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			y3 -= y1;
			y1 -= y2;
			y2 = lineOffsets[y2];
			while (--y1 >= 0) {
				drawTexturedScanline(pixels, texels, y2, x2 >> 16, x3 >> 16, c2, c3, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
				z2 += depthScale;
				x3 += i7;
				x2 += k7;
				c3 += j7;
				c2 += l7;
				y2 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while (--y3 >= 0) {
				drawTexturedScanline(pixels, texels, y2, x2 >> 16, x1 >> 16, c2, c1, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
				z2 += depthScale;
				x1 += i8;
				x2 += k7;
				c1 += j8;
				c2 += l7;
				y2 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		if (y3 >= bottomY) {
			return;
		}
		if (y1 > bottomY) {
			y1 = bottomY;
		}
		if (y2 > bottomY) {
			y2 = bottomY;
		}
		z3 = z3 - depthSlope * x3 + depthSlope;
		if (y1 < y2) {
			x2 = x3 <<= 16;
			c2 = c3 <<= 16;
			if (y3 < 0) {
				x2 -= k7 * y3;
				x3 -= i8 * y3;
				z3 -= depthScale * y3;
				c2 -= l7 * y3;
				c3 -= j8 * y3;
				y3 = 0;
			}
			x1 <<= 16;
			c1 <<= 16;
			if (y1 < 0) {
				x1 -= i7 * y1;
				c1 -= j7 * y1;
				y1 = 0;
			}
			int k9 = y3 - centerY;
			l4 += j5 * k9;
			k5 += i6 * k9;
			j6 += l6 * k9;
			if (k7 < i8) {
				y2 -= y1;
				y1 -= y3;
				y3 = lineOffsets[y3];
				while (--y1 >= 0) {
					drawTexturedScanline(pixels, texels, y3, x2 >> 16, x3 >> 16, c2, c3, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
					z3 += depthScale;
					x2 += k7;
					x3 += i8;
					c2 += l7;
					c3 += j8;
					y3 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y2 >= 0) {
					drawTexturedScanline(pixels, texels, y3, x2 >> 16, x1 >> 16, c2, c1, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
					z3 += depthScale;
					x2 += k7;
					x1 += i7;
					c2 += l7;
					c1 += j7;
					y3 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			y2 -= y1;
			y1 -= y3;
			y3 = lineOffsets[y3];
			while (--y1 >= 0) {
				drawTexturedScanline(pixels, texels, y3, x3 >> 16, x2 >> 16, c3, c2, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
				z3 += depthScale;
				x2 += k7;
				x3 += i8;
				c2 += l7;
				c3 += j8;
				y3 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while (--y2 >= 0) {
				drawTexturedScanline(pixels, texels, y3, x1 >> 16, x2 >> 16, c1, c2, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
				z3 += depthScale;
				x2 += k7;
				x1 += i7;
				c2 += l7;
				c1 += j7;
				y3 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		x1 = x3 <<= 16;
		c1 = c3 <<= 16;
		if (y3 < 0) {
			x1 -= k7 * y3;
			x3 -= i8 * y3;
			z3 -= depthScale * y3;
			c1 -= l7 * y3;
			c3 -= j8 * y3;
			y3 = 0;
		}
		x2 <<= 16;
		c2 <<= 16;
		if (y2 < 0) {
			x2 -= i7 * y2;
			c2 -= j7 * y2;
			y2 = 0;
		}
		int l9 = y3 - centerY;
		l4 += j5 * l9;
		k5 += i6 * l9;
		j6 += l6 * l9;
		if (k7 < i8) {
			y1 -= y2;
			y2 -= y3;
			y3 = lineOffsets[y3];
			while (--y2 >= 0) {
				drawTexturedScanline(pixels, texels, y3, x1 >> 16, x3 >> 16, c1, c3, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
				z3 += depthScale;
				x1 += k7;
				x3 += i8;
				c1 += l7;
				c3 += j8;
				y3 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while (--y1 >= 0) {
				drawTexturedScanline(pixels, texels, y3, x2 >> 16, x3 >> 16, c2, c3, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
				z3 += depthScale;
				x2 += i7;
				x3 += i8;
				c2 += j7;
				c3 += j8;
				y3 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		y1 -= y2;
		y2 -= y3;
		y3 = lineOffsets[y3];
		while (--y2 >= 0) {
			drawTexturedScanline(pixels, texels, y3, x3 >> 16, x1 >> 16, c3, c1, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
			z3 += depthScale;
			x1 += k7;
			x3 += i8;
			c1 += l7;
			c3 += j8;
			y3 += width;
			l4 += j5;
			k5 += i6;
			j6 += l6;
		}
		while (--y1 >= 0) {
			drawTexturedScanline(pixels, texels, y3, x3 >> 16, x2 >> 16, c3, c2, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
			z3 += depthScale;
			x2 += i7;
			x3 += i8;
			c2 += j7;
			c3 += j8;
			y3 += width;
			l4 += j5;
			k5 += i6;
			j6 += l6;
		}
	}

	private static void drawTexturedScanline(int dest[], int src[], int offset, int x1, int x2, int hsl1, int hsl2, int t1, int t2, int t3, int t4, int t5, int t6, float z1, float z2, int bufferOffset) {
		int darken = 0;
		int srcPos = 0;
		if(x1 >= x2) {
			return;
		}
		int dl = (hsl2 - hsl1) / (x2 - x1);
		int n;
		if(aBoolean1462) {
			if(x2 > DrawingArea.centerX) {
				x2 = DrawingArea.centerX;
			}
			if(x1 < 0) {
				hsl1 -= x1 * dl;
				x1 = 0;
			}
		}
		if(x1 >= x2) {
			return;
		}
		n = x2 - x1 >> 3;
		offset += x1;
		z1 += z2 * x1;
		int j4 = 0;
		int l4 = 0;
		int l6 = x1 - centerX;
		t1 += (t4 >> 3) * l6;
		t2 += (t5 >> 3) * l6;
		t3 += (t6 >> 3) * l6;
		int l5 = t3 >> 14;
		if(l5 != 0) {
			darken = t1 / l5;
			srcPos = t2 / l5;
			if(darken < 0) {
				darken = 0;
			} else if(darken > 16256) {
				darken = 16256;
			}
		}
		t1 += t4;
		t2 += t5;
		t3 += t6;
		l5 = t3 >> 14;
		if(l5 != 0) {
			j4 = t1 / l5;
			l4 = t2 / l5;
			if(j4 < 7) {
				j4 = 7;
			} else if(j4 > 16256) {
				j4 = 16256;
			}
		}
		int j7 = j4 - darken >> 3;
		int l7 = l4 - srcPos >> 3;
		if(aBoolean1463) {
			while(n-- > 0) {
				int rgb;
				int l;
				rgb = src[((srcPos & 0x3f80) + (darken >> 7))];
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((rgb & 0xff00ff) * l & ~0xff00ff) + ((rgb & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
				offset++;
				z1 += z2;
				darken += j7;
				srcPos += l7;
				hsl1 += dl;
				rgb = src[((srcPos & 0x3f80) + (darken >> 7))];
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((rgb & 0xff00ff) * l & ~0xff00ff) + ((rgb & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
				offset++;
				z1 += z2;
				darken += j7;
				srcPos += l7;
				hsl1 += dl;
				rgb = src[((srcPos & 0x3f80) + (darken >> 7))];
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((rgb & 0xff00ff) * l & ~0xff00ff) + ((rgb & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
				offset++;
				z1 += z2;
				darken += j7;
				srcPos += l7;
				hsl1 += dl;
				rgb = src[((srcPos & 0x3f80) + (darken >> 7))];
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((rgb & 0xff00ff) * l & ~0xff00ff) + ((rgb & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
				offset++;
				z1 += z2;
				darken += j7;
				srcPos += l7;
				hsl1 += dl;
				rgb = src[((srcPos & 0x3f80) + (darken >> 7))];
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((rgb & 0xff00ff) * l & ~0xff00ff) + ((rgb & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
				offset++;
				z1 += z2;
				darken += j7;
				srcPos += l7;
				hsl1 += dl;
				rgb = src[((srcPos & 0x3f80) + (darken >> 7))];
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((rgb & 0xff00ff) * l & ~0xff00ff) + ((rgb & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
				offset++;
				z1 += z2;
				darken += j7;
				srcPos += l7;
				hsl1 += dl;
				rgb = src[((srcPos & 0x3f80) + (darken >> 7))];
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((rgb & 0xff00ff) * l & ~0xff00ff) + ((rgb & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
				offset++;
				z1 += z2;
				darken += j7;
				srcPos += l7;
				hsl1 += dl;
				rgb = src[((srcPos & 0x3f80) + (darken >> 7))];
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((rgb & 0xff00ff) * l & ~0xff00ff) + ((rgb & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
				offset++;
				z1 += z2;
				darken += j7;
				srcPos += l7;
				hsl1 += dl;
				t1 += t4;
				t2 += t5;
				t3 += t6;
				int i6 = t3 >> 14;
				if(i6 != 0) {
					j4 = t1 / i6;
					l4 = t2 / i6;
					if(j4 < 7) {
						j4 = 7;
					} else if(j4 > 16256) {
						j4 = 16256;
					}
				}
				j7 = j4 - darken >> 3;
				l7 = l4 - srcPos >> 3;
				hsl1 += dl;
			}
			for(n = x2 - x1 & 7; n-- > 0;) {
				int rgb;
				int l;
				rgb = src[((srcPos & 0x3f80) + (darken >> 7))];
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((rgb & 0xff00ff) * l & ~0xff00ff) + ((rgb & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
				z1 += z2;
				offset++;
				darken += j7;
				srcPos += l7;
				hsl1 += dl;
			}
			return;
		}
		while(n-- > 0) {
			int i9;
			int l;
			if((i9 = src[((srcPos & 0x3f80) + (darken >> 7))]) != 0) {
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((i9 & 0xff00ff) * l & ~0xff00ff) + ((i9 & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
			}
			z1 += z2;
			offset++;
			darken += j7;
			srcPos += l7;
			hsl1 += dl;
			if((i9 = src[((srcPos & 0x3f80) + (darken >> 7))]) != 0) {
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((i9 & 0xff00ff) * l & ~0xff00ff) + ((i9 & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
			}
			z1 += z2;
			offset++;
			darken += j7;
			srcPos += l7;
			hsl1 += dl;
			if((i9 = src[((srcPos & 0x3f80) + (darken >> 7))]) != 0) {
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((i9 & 0xff00ff) * l & ~0xff00ff) + ((i9 & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
			}
			z1 += z2;
			offset++;
			darken += j7;
			srcPos += l7;
			hsl1 += dl;
			if((i9 = src[((srcPos & 0x3f80) + (darken >> 7))]) != 0) {
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((i9 & 0xff00ff) * l & ~0xff00ff) + ((i9 & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
			}
			z1 += z2;
			offset++;
			darken += j7;
			srcPos += l7;
			hsl1 += dl;
			if((i9 = src[((srcPos & 0x3f80) + (darken >> 7))]) != 0) {
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((i9 & 0xff00ff) * l & ~0xff00ff) + ((i9 & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
			}
			z1 += z2;
			offset++;
			darken += j7;
			srcPos += l7;
			hsl1 += dl;
			if((i9 = src[((srcPos & 0x3f80) + (darken >> 7))]) != 0) {
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((i9 & 0xff00ff) * l & ~0xff00ff) + ((i9 & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
			}
			z1 += z2;
			offset++;
			darken += j7;
			srcPos += l7;
			hsl1 += dl;
			if((i9 = src[((srcPos & 0x3f80) + (darken >> 7))]) != 0) {
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((i9 & 0xff00ff) * l & ~0xff00ff) + ((i9 & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
			}
			z1 += z2;
			offset++;
			darken += j7;
			srcPos += l7;
			hsl1 += dl;
			if((i9 = src[((srcPos & 0x3f80) + (darken >> 7))]) != 0) {
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((i9 & 0xff00ff) * l & ~0xff00ff) + ((i9 & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
			}
			z1 += z2;
			offset++;
			darken += j7;
			srcPos += l7;
			hsl1 += dl;
			t1 += t4;
			t2 += t5;
			t3 += t6;
			int j6 = t3 >> 14;
			if(j6 != 0) {
				j4 = t1 / j6;
				l4 = t2 / j6;
				if(j4 < 7) {
					j4 = 7;
				} else if(j4 > 16256) {
					j4 = 16256;
				}
			}
			j7 = j4 - darken >> 3;
			l7 = l4 - srcPos >> 3;
			hsl1 += dl;
		}
		for(int l3 = x2 - x1 & 7; l3-- > 0;) {
			int j9;
			int l;
			if((j9 = src[((srcPos & 0x3f80) + (darken >> 7))]) != 0) {
				l = hsl1 >> 16;
				if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
					dest[offset] = ((j9 & 0xff00ff) * l & ~0xff00ff) + ((j9 & 0xff00) * l & 0xff0000) >> 8;
					depthBuffer[offset] = z1;
				}
			}
			z1 += z2;
			offset++;
			darken += j7;
			srcPos += l7;
			hsl1 += dl;
		}
	}

	private static int textureMipmap;

	private static int texelPos(int defaultIndex) {
		int x = defaultIndex & 127;
		int y = defaultIndex >> 7;
		x >>= textureMipmap;
		y >>= textureMipmap;
		return x + (y << 7 - textureMipmap);
	}

	public static void drawMaterializedTriangle(int y1, int y2, int y3, int x1, int x2, int x3, int hsl1, int hsl2, int hsl3, int tx1, int tx2, int tx3, int ty1, int ty2, int ty3, int tz1, int tz2, int tz3, int tex, float z1, float z2, float z3, int bufferOffset) {
		if (!Configuration.hdTexturing || Texture.get(tex) == null) {
			//drawGouraudTriangle(y1, y2, y3, x1, x2, x3, hsl1, hsl2, hsl3, z1, z2, z3, bufferOffset);
			return;
		}
		int area = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) >> 1;
        if (area < 0) {
            area = -area;
        }
        if (area > 4096) {
            textureMipmap = 1;
        } else if (area > 1024) {
            textureMipmap = 1;
        } else if (area > 256) {
            textureMipmap = 2;
        } else if (area > 64) {
            textureMipmap = 3;
        } else if (area > 16) {
            textureMipmap = 4;
        } else if (area > 4) {
            textureMipmap = 5;
        } else if (area > 1) {
            textureMipmap = 6;
        } else {
            textureMipmap = 7;
        }
		int[] texels = Texture.get(tex).mipmaps[textureMipmap];
		tx2 = tx1 - tx2;
		ty2 = ty1 - ty2;
		tz2 = tz1 - tz2;
		tx3 -= tx1;
		ty3 -= ty1;
		tz3 -= tz1;
		int l4 = tx3 * ty1 - ty3 * tx1 << (Client.log_view_dist == 9 ? 14 : 15);
		int i5 = ty3 * tz1 - tz3 * ty1 << 8;
		int j5 = tz3 * tx1 - tx3 * tz1 << 5;
		int k5 = tx2 * ty1 - ty2 * tx1 << (Client.log_view_dist == 9 ? 14 : 15);
		int l5 = ty2 * tz1 - tz2 * ty1 << 8;
		int i6 = tz2 * tx1 - tx2 * tz1 << 5;
		int j6 = ty2 * tx3 - tx2 * ty3 << (Client.log_view_dist == 9 ? 14 : 15);
		int k6 = tz2 * ty3 - ty2 * tz3 << 8;
		int l6 = tx2 * tz3 - tz2 * tx3 << 5;
		int i7 = 0;
		int j7 = 0;
		if (y2 != y1) {
			i7 = (x2 - x1 << 16) / (y2 - y1);
			j7 = (hsl2 - hsl1 << 15) / (y2 - y1);
		}
		int k7 = 0;
		int l7 = 0;
		if (y3 != y2) {
			k7 = (x3 - x2 << 16) / (y3 - y2);
			l7 = (hsl3 - hsl2 << 15) / (y3 - y2);
		}
		int i8 = 0;
		int j8 = 0;
		if (y3 != y1) {
			i8 = (x1 - x3 << 16) / (y1 - y3);
			j8 = (hsl1 - hsl3 << 15) / (y1 - y3);
		}
		
		float x21 = x2 - x1;
		float y32 = y2 - y1;
		float x31 = x3 - x1;
		float y31 = y3 - y1;
		float z21 = z2 - z1;
		float z31 = z3 - z1;

		float div = x21 * y31 - x31 * y32;
		float depthSlope = (z21 * y31 - z31 * y32) / div;
		float depthScale = (z31 * x21 - z21 * x31) / div;
		
		if (y1 <= y2 && y1 <= y3) {
			if (y1 >= bottomY) {
				return;
			}
			if (y2 > bottomY) {
				y2 = bottomY;
			}
			if (y3 > bottomY) {
				y3 = bottomY;
			}
			z1 = z1 - depthSlope * x1 + depthSlope;
			if (y2 < y3) {
				x3 = x1 <<= 16;
				hsl3 = hsl1 <<= 15;
				if (y1 < 0) {
					x3 -= i8 * y1;
					x1 -= i7 * y1;
					z1 -= depthScale * y1;
					hsl3 -= j8 * y1;
					hsl1 -= j7 * y1;
					y1 = 0;
				}
				x2 <<= 16;
				hsl2 <<= 15;
				if (y2 < 0) {
					x2 -= k7 * y2;
					hsl2 -= l7 * y2;
					y2 = 0;
				}
				int k8 = y1 - centerY;
				l4 += j5 * k8;
				k5 += i6 * k8;
				j6 += l6 * k8;
				if (y1 != y2 && i8 < i7 || y1 == y2 && i8 > k7) {
					y3 -= y2;
					y2 -= y1;
					y1 = lineOffsets[y1];
					while (--y2 >= 0) {
						drawMaterializedScanline(pixels, texels, y1, x3 >> 16, x1 >> 16, hsl3 >> 7, hsl1 >> 7, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
						x3 += i8;
						x1 += i7;
						z1 += depthScale;
						hsl3 += j8;
						hsl1 += j7;
						y1 += width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					while (--y3 >= 0) {
						drawMaterializedScanline(pixels, texels, y1, x3 >> 16, x2 >> 16, hsl3 >> 7, hsl2 >> 7, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
						x3 += i8;
						x2 += k7;
						z1 += depthScale;
						hsl3 += j8;
						hsl2 += l7;
						y1 += width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					
					return;
				}
				y3 -= y2;
				y2 -= y1;
				y1 = lineOffsets[y1];
				while (--y2 >= 0) {
					drawMaterializedScanline(pixels, texels, y1, x1 >> 16, x3 >> 16, hsl1 >> 7, hsl3 >> 7, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
					x3 += i8;
					x1 += i7;
					z1 += depthScale;
					hsl3 += j8;
					hsl1 += j7;
					y1 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y3 >= 0) {
					drawMaterializedScanline(pixels, texels, y1, x2 >> 16, x3 >> 16, hsl2 >> 7, hsl3 >> 7, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
					x3 += i8;
					x2 += k7;
					z1 += depthScale;
					hsl3 += j8;
					hsl2 += l7;
					y1 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				
				return;
			}
			x2 = x1 <<= 16;
			hsl2 = hsl1 <<= 15;
			if (y1 < 0) {
				x2 -= i8 * y1;
				x1 -= i7 * y1;
				z1 -= depthScale * y1;
				hsl2 -= j8 * y1;
				hsl1 -= j7 * y1;
				y1 = 0;
			}
			x3 <<= 16;
			hsl3 <<= 15;
			if (y3 < 0) {
				x3 -= k7 * y3;
				hsl3 -= l7 * y3;
				y3 = 0;
			}
			int l8 = y1 - centerY;
			l4 += j5 * l8;
			k5 += i6 * l8;
			j6 += l6 * l8;
			if (y1 != y3 && i8 < i7 || y1 == y3 && k7 > i7) {
				y2 -= y3;
				y3 -= y1;
				y1 = lineOffsets[y1];
				while (--y3 >= 0) {
					drawMaterializedScanline(pixels, texels, y1, x2 >> 16, x1 >> 16, hsl2 >> 7, hsl1 >> 7, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
					x2 += i8;
					x1 += i7;
					z1 += depthScale;
					hsl2 += j8;
					hsl1 += j7;
					y1 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y2 >= 0) {
					drawMaterializedScanline(pixels, texels, y1, x3 >> 16, x1 >> 16, hsl3 >> 7, hsl1 >> 7, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
					x3 += k7;
					x1 += i7;
					z1 += depthScale;
					hsl3 += l7;
					hsl1 += j7;
					y1 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				
				return;
			}
			y2 -= y3;
			y3 -= y1;
			y1 = lineOffsets[y1];
			while (--y3 >= 0) {
				drawMaterializedScanline(pixels, texels, y1, x1 >> 16, x2 >> 16, hsl1 >> 7, hsl2 >> 7, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
				x2 += i8;
				x1 += i7;
				z1 += depthScale;
				hsl2 += j8;
				hsl1 += j7;
				y1 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while (--y2 >= 0) {
				drawMaterializedScanline(pixels, texels, y1, x1 >> 16, x3 >> 16, hsl1 >> 7, hsl3 >> 7, l4, k5, j6, i5, l5, k6, z1, depthSlope, bufferOffset);
				x3 += k7;
				x1 += i7;
				z1 += depthScale;
				hsl3 += l7;
				hsl1 += j7;
				y1 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			
			return;
		}
		if (y2 <= y3) {
			if (y2 >= bottomY) {
				return;
			}
			if (y3 > bottomY) {
				y3 = bottomY;
			}
			if (y1 > bottomY) {
				y1 = bottomY;
			}
			z2 = z2 - depthSlope * x2 + depthSlope;
			if (y3 < y1) {
				x1 = x2 <<= 16;
				hsl1 = hsl2 <<= 15;
				if (y2 < 0) {
					x1 -= i7 * y2;
					x2 -= k7 * y2;
					z2 -= depthScale * y2;
					hsl1 -= j7 * y2;
					hsl2 -= l7 * y2;
					y2 = 0;
				}
				x3 <<= 16;
				hsl3 <<= 15;
				if (y3 < 0) {
					x3 -= i8 * y3;
					hsl3 -= j8 * y3;
					y3 = 0;
				}
				int i9 = y2 - centerY;
				l4 += j5 * i9;
				k5 += i6 * i9;
				j6 += l6 * i9;
				if (y2 != y3 && i7 < k7 || y2 == y3 && i7 > i8) {
					y1 -= y3;
					y3 -= y2;
					y2 = lineOffsets[y2];
					while (--y3 >= 0) {
						drawMaterializedScanline(pixels, texels, y2, x1 >> 16, x2 >> 16, hsl1 >> 7, hsl2 >> 7, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
						x1 += i7;
						x2 += k7;
						z2 += depthScale;
						hsl1 += j7;
						hsl2 += l7;
						y2 += width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					while (--y1 >= 0) {
						drawMaterializedScanline(pixels, texels, y2, x1 >> 16, x3 >> 16, hsl1 >> 7, hsl3 >> 7, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
						x1 += i7;
						x3 += i8;
						z2 += depthScale;
						hsl1 += j7;
						hsl3 += j8;
						y2 += width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					
					return;
				}
				y1 -= y3;
				y3 -= y2;
				y2 = lineOffsets[y2];
				while (--y3 >= 0) {
					drawMaterializedScanline(pixels, texels, y2, x2 >> 16, x1 >> 16, hsl2 >> 7, hsl1 >> 7, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
					x1 += i7;
					x2 += k7;
					z2 += depthScale;
					hsl1 += j7;
					hsl2 += l7;
					y2 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y1 >= 0) {
					drawMaterializedScanline(pixels, texels, y2, x3 >> 16, x1 >> 16, hsl3 >> 7, hsl1 >> 7, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
					x1 += i7;
					x3 += i8;
					z2 += depthScale;
					hsl1 += j7;
					hsl3 += j8;
					y2 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				
				return;
			}
			x3 = x2 <<= 16;
			hsl3 = hsl2 <<= 15;
			if (y2 < 0) {
				x3 -= i7 * y2;
				x2 -= k7 * y2;
				z2 -= depthScale * y2;
				hsl3 -= j7 * y2;
				hsl2 -= l7 * y2;
				y2 = 0;
			}
			x1 <<= 16;
			hsl1 <<= 15;
			if (y1 < 0) {
				x1 -= i8 * y1;
				hsl1 -= j8 * y1;
				y1 = 0;
			}
			int j9 = y2 - centerY;
			l4 += j5 * j9;
			k5 += i6 * j9;
			j6 += l6 * j9;
			if (i7 < k7) {
				y3 -= y1;
				y1 -= y2;
				y2 = lineOffsets[y2];
				while (--y1 >= 0) {
					drawMaterializedScanline(pixels, texels, y2, x3 >> 16, x2 >> 16, hsl3 >> 7, hsl2 >> 7, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
					x3 += i7;
					x2 += k7;
					z2 += depthScale;
					hsl3 += j7;
					hsl2 += l7;
					y2 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y3 >= 0) {
					drawMaterializedScanline(pixels, texels, y2, x1 >> 16, x2 >> 16, hsl1 >> 7, hsl2 >> 7, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
					x1 += i8;
					x2 += k7;
					z2 += depthScale;
					hsl1 += j8;
					hsl2 += l7;
					y2 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				
				return;
			}
			y3 -= y1;
			y1 -= y2;
			y2 = lineOffsets[y2];
			while (--y1 >= 0) {
				drawMaterializedScanline(pixels, texels, y2, x2 >> 16, x3 >> 16, hsl2 >> 7, hsl3 >> 7, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
				x3 += i7;
				x2 += k7;
				z2 += depthScale;
				hsl3 += j7;
				hsl2 += l7;
				y2 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while (--y3 >= 0) {
				drawMaterializedScanline(pixels, texels, y2, x2 >> 16, x1 >> 16, hsl2 >> 7, hsl1 >> 7, l4, k5, j6, i5, l5, k6, z2, depthSlope, bufferOffset);
				x1 += i8;
				x2 += k7;
				z2 += depthScale;
				hsl1 += j8;
				hsl2 += l7;
				y2 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			
			return;
		}
		if (y3 >= bottomY) {
			return;
		}
		if (y1 > bottomY) {
			y1 = bottomY;
		}
		if (y2 > bottomY) {
			y2 = bottomY;
		}
		z3 = z3 - depthSlope * x3 + depthSlope;
		if (y1 < y2) {
			x2 = x3 <<= 16;
			hsl2 = hsl3 <<= 15;
			if (y3 < 0) {
				x2 -= k7 * y3;
				x3 -= i8 * y3;
				z3 -= depthScale * y3;
				hsl2 -= l7 * y3;
				hsl3 -= j8 * y3;
				y3 = 0;
			}
			x1 <<= 16;
			hsl1 <<= 15;
			if (y1 < 0) {
				x1 -= i7 * y1;
				hsl1 -= j7 * y1;
				y1 = 0;
			}
			int k9 = y3 - centerY;
			l4 += j5 * k9;
			k5 += i6 * k9;
			j6 += l6 * k9;
			if (k7 < i8) {
				y2 -= y1;
				y1 -= y3;
				y3 = lineOffsets[y3];
				while (--y1 >= 0) {
					drawMaterializedScanline(pixels, texels, y3, x2 >> 16, x3 >> 16, hsl2 >> 7, hsl3 >> 7, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
					x2 += k7;
					x3 += i8;
					z3 += depthScale;
					hsl2 += l7;
					hsl3 += j8;
					y3 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while (--y2 >= 0) {
					drawMaterializedScanline(pixels, texels, y3, x2 >> 16, x1 >> 16, hsl2 >> 7, hsl1 >> 7, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
					x2 += k7;
					x1 += i7;
					z3 += depthScale;
					hsl2 += l7;
					hsl1 += j7;
					y3 += width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				
				return;
			}
			y2 -= y1;
			y1 -= y3;
			y3 = lineOffsets[y3];
			while (--y1 >= 0) {
				drawMaterializedScanline(pixels, texels, y3, x3 >> 16, x2 >> 16, hsl3 >> 7, hsl2 >> 7, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
				x2 += k7;
				x3 += i8;
				z3 += depthScale;
				hsl2 += l7;
				hsl3 += j8;
				y3 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while (--y2 >= 0) {
				drawMaterializedScanline(pixels, texels, y3, x1 >> 16, x2 >> 16, hsl1 >> 7, hsl2 >> 7, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
				x2 += k7;
				x1 += i7;
				z3 += depthScale;
				hsl2 += l7;
				hsl1 += j7;
				y3 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			
			return;
		}
		x1 = x3 <<= 16;
		hsl1 = hsl3 <<= 15;
		if (y3 < 0) {
			x1 -= k7 * y3;
			x3 -= i8 * y3;
			z3 -= depthScale * y3;
			hsl1 -= l7 * y3;
			hsl3 -= j8 * y3;
			y3 = 0;
		}
		x2 <<= 16;
		hsl2 <<= 15;
		if (y2 < 0) {
			x2 -= i7 * y2;
			hsl2 -= j7 * y2;
			y2 = 0;
		}
		int l9 = y3 - centerY;
		l4 += j5 * l9;
		k5 += i6 * l9;
		j6 += l6 * l9;
		if (k7 < i8) {
			y1 -= y2;
			y2 -= y3;
			y3 = lineOffsets[y3];
			while (--y2 >= 0) {
				drawMaterializedScanline(pixels, texels, y3, x1 >> 16, x3 >> 16, hsl1 >> 7, hsl3 >> 7, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
				x1 += k7;
				x3 += i8;
				z3 += depthScale;
				hsl1 += l7;
				hsl3 += j8;
				y3 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while (--y1 >= 0) {
				drawMaterializedScanline(pixels, texels, y3, x2 >> 16, x3 >> 16, hsl2 >> 7, hsl3 >> 7, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
				x2 += i7;
				x3 += i8;
				z3 += depthScale;
				hsl2 += j7;
				hsl3 += j8;
				y3 += width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			
			return;
		}
		y1 -= y2;
		y2 -= y3;
		y3 = lineOffsets[y3];
		while (--y2 >= 0) {
			drawMaterializedScanline(pixels, texels, y3, x3 >> 16, x1 >> 16, hsl3 >> 7, hsl1 >> 7, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
			x1 += k7;
			x3 += i8;
			z3 += depthScale;
			hsl1 += l7;
			hsl3 += j8;
			y3 += width;
			l4 += j5;
			k5 += i6;
			j6 += l6;
		}
		while (--y1 >= 0) {
			drawMaterializedScanline(pixels, texels, y3, x3 >> 16, x2 >> 16, hsl3 >> 7, hsl2 >> 7, l4, k5, j6, i5, l5, k6, z3, depthSlope, bufferOffset);
			x2 += i7;
			x3 += i8;
			z3 += depthScale;
			hsl2 += j7;
			hsl3 += j8;
			y3 += width;
			l4 += j5;
			k5 += i6;
			j6 += l6;
		}
	}

	private static final void drawMaterializedScanline(int[] dest, int[] texels, int offset, int x1, int x2, int hsl1, int hsl2, int t1, int t2, int t3, int t4, int t5, int t6, float z1, float z2, int bufferOffset) {
		if(x2 <= x1) {
			return;
		}
		int texPos = 0;
		int rgb = 0;
		if (aBoolean1462) {
			if (x2 > DrawingArea.centerX) {
				x2 = DrawingArea.centerX;
			}
			if (x1 < 0) {
				x1 = 0;
			}
		}
		if(x1 < x2) {
			offset += x1;
			z1 += z2 * x1;
			int n = x2 - x1 >> 2;
			int dhsl = 0;
			if(n > 0) {
				dhsl = (hsl2 - hsl1) * anIntArray1468[n] >> 15;
			}
			int dist = x1 - centerX;
			t1 += (t4 >> 3) * dist;
			t2 += (t5 >> 3) * dist;
			t3 += (t6 >> 3) * dist;
			int i_57_ = t3 >> 14;
			int i_58_;
			int i_59_;
			if(i_57_ != 0) {
				i_58_ = t1 / i_57_;
				i_59_ = t2 / i_57_;
			} else {
				i_58_ = 0;
				i_59_ = 0;
			}
			t1 += t4;
			t2 += t5;
			t3 += t6;
			i_57_ = t3 >> 14;
			int i_60_;
			int i_61_;
			if(i_57_ != 0) {
				i_60_ = t1 / i_57_;
				i_61_ = t2 / i_57_;
			} else {
				i_60_ = 0;
				i_61_ = 0;
			}
			texPos = (i_58_ << 18) + i_59_;
			int dtexPos = (i_60_ - i_58_ >> 3 << 18) + (i_61_ - i_59_ >> 3);
			n >>= 1;
			int light;
			if(n > 0) {
				do {
					hsl1 += dhsl;
					rgb = texels[texelPos((texPos & 0x3f80) + (texPos >>> 25))];
					light = ((hsl1 >> 8 & 0x7f) << 1) * (((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3) / 384;
					if (light > 127) {
						light = 127;
					}
					texPos += dtexPos;
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = anIntArray1482[(hsl1 >> 8 & 0xff80) | light];
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					offset++;
					rgb = texels[texelPos((texPos & 0x3f80) + (texPos >>> 25))];
					light = ((hsl1 >> 8 & 0x7f) << 1) * (((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3) / 384;
					if (light > 127) {
						light = 127;
					}
					texPos += dtexPos;
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = anIntArray1482[(hsl1 >> 8 & 0xff80) | light];
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					offset++;
					rgb = texels[texelPos((texPos & 0x3f80) + (texPos >>> 25))];
					light = ((hsl1 >> 8 & 0x7f) << 1) * (((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3) / 384;
					if (light > 127) {
						light = 127;
					}
					texPos += dtexPos;
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = anIntArray1482[(hsl1 >> 8 & 0xff80) | light];
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					offset++;
					rgb = texels[texelPos((texPos & 0x3f80) + (texPos >>> 25))];
					light = ((hsl1 >> 8 & 0x7f) << 1) * (((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3) / 384;
					if (light > 127) {
						light = 127;
					}
					texPos += dtexPos;
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = anIntArray1482[(hsl1 >> 8 & 0xff80) | light];
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					offset++;
					hsl1 += dhsl;
					rgb = texels[texelPos((texPos & 0x3f80) + (texPos >>> 25))];
					light = ((hsl1 >> 8 & 0x7f) << 1) * (((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3) / 384;
					if (light > 127) {
						light = 127;
					}
					texPos += dtexPos;
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = anIntArray1482[(hsl1 >> 8 & 0xff80) | light];
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					offset++;
					rgb = texels[texelPos((texPos & 0x3f80) + (texPos >>> 25))];
					light = ((hsl1 >> 8 & 0x7f) << 1) * (((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3) / 384;
					if (light > 127) {
						light = 127;
					}
					texPos += dtexPos;
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = anIntArray1482[(hsl1 >> 8 & 0xff80) | light];
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					offset++;
					rgb = texels[texelPos((texPos & 0x3f80) + (texPos >>> 25))];
					light = ((hsl1 >> 8 & 0x7f) << 1) * (((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3) / 384;
					if (light > 127) {
						light = 127;
					}
					texPos += dtexPos;
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = anIntArray1482[(hsl1 >> 8 & 0xff80) | light];
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					offset++;
					rgb = texels[texelPos((texPos & 0x3f80) + (texPos >>> 25))];
					light = ((hsl1 >> 8 & 0x7f) << 1) * (((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3) / 384;
					if (light > 127) {
						light = 127;
					}
					texPos += dtexPos;
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = anIntArray1482[(hsl1 >> 8 & 0xff80) | light];
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					offset++;
					i_58_ = i_60_;
					i_59_ = i_61_;
					t1 += t4;
					t2 += t5;
					t3 += t6;
					i_57_ = t3 >> 14;
					if(i_57_ != 0) {
						i_60_ = t1 / i_57_;
						i_61_ = t2 / i_57_;
					} else {
						i_60_ = 0;
						i_61_ = 0;
					}
					texPos = (i_58_ << 18) + i_59_;
					dtexPos = (i_60_ - i_58_ >> 3 << 18) + (i_61_ - i_59_ >> 3);
				} while(--n > 0);
			}
			n = x2 - x1 & 7;
			if(n > 0) {
				do {
					if((n & 3) == 0) {
						hsl1 += dhsl;
					}
					rgb = texels[texelPos((texPos & 0x3f80) + (texPos >>> 25))];
					light = ((hsl1 >> 8 & 0x7f) << 1) * (((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3) / 384;
					if (light > 127) {
						light = 127;
					}
					texPos += dtexPos;
					if (z1 < depthBuffer[offset] || z1 < depthBuffer[offset] + bufferOffset) {
						dest[offset] = anIntArray1482[(hsl1 >> 8 & 0xff80) | light];
						depthBuffer[offset] = z1;
					}
					z1 += z2;
					offset++;
				} while(--n > 0);
			}
		}
	}

	public static void nullify() {
		anIntArray1468 = null;
		anIntArray1468 = null;
		SINE = null;
		COSINE = null;
		lineOffsets = null;
		aBackgroundArray1474s = null;
		aBooleanArray1475 = null;
		anIntArray1476 = null;
		anIntArrayArray1478 = null;
		anIntArrayArray1479 = null;
		anIntArray1480 = null;
		anIntArray1482 = null;
		anIntArrayArray1483 = null;
	}

	public static void setBounds(int offSetX, int offSetY) {
		lineOffsets = new int[offSetY];

		for (int l = 0; l < offSetY; l++) {
			lineOffsets[l] = offSetX * l;
		}

		centerX = offSetX / 2;
		centerY = offSetY / 2;
	}

}