package org.necrotic.client;

import java.util.Arrays;


public class RSGraphics {

	public static int[] pixels;
	public static float[] depth;

	public static int width;
	public static int height;
	public static int clipY;
	public static int clipTopY;
	public static int clipX;
	public static int clipTopX;
	public static int lastX;
	public static int clipCenterX;
	public static int clipCenterY;

	// GradientPaint
	public static void fillRectGradient(int x, int y, int w, int h, int bottomRGB, int topRGB, int alpha) {
		int k1 = 0;
		int l1 = 0x10000 / h;
		if (x < clipX) {
			w -= clipX - x;
			x = clipX;
		}
		if (y < clipY) {
			k1 += (clipY - y) * l1;
			h -= clipY - y;
			y = clipY;
		}
		if (x + w > clipTopX) {
			w = clipTopX - x;
		}
		if (y + h > clipTopY) {
			h = clipTopY - y;
		}
		int i2 = width - w;
		int result_alpha = 256 - alpha;
		int total_pixels = x + y * width;
		for (int k2 = -h; k2 < 0; k2++) {
			int gradient1 = 0x10000 - k1 >> 8;
			int gradient2 = k1 >> 8;
			int gradient_color = ((bottomRGB & 0xff00ff) * gradient1 + (topRGB & 0xff00ff) * gradient2 & 0xff00ff00) + ((bottomRGB & 0xff00) * gradient1 + (topRGB & 0xff00) * gradient2 & 0xff0000) >>> 8;
			int color = ((gradient_color & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((gradient_color & 0xff00) * alpha >> 8 & 0xff00);
			for (int k3 = -w; k3 < 0; k3++) {
				int pixel_pixels = pixels[total_pixels];
				pixel_pixels = ((pixel_pixels & 0xff00ff) * result_alpha >> 8 & 0xff00ff) + ((pixel_pixels & 0xff00) * result_alpha >> 8 & 0xff00);
				pixels[total_pixels++] = color + pixel_pixels;
			}

			total_pixels += i2;
			k1 += l1;
		}

	}

	// proper name.
	public static void create(int height, int width, int pixels[], float depth[]) {
		RSGraphics.depth = depth;
		RSGraphics.pixels = pixels;
		RSGraphics.width = width;
		RSGraphics.height = height;

		if (depth != null) {
			Arrays.fill(depth, Integer.MAX_VALUE);
		}

		setClip(0, 0, width, height);
	}

	// custom code sets clip to full screen lol.
	public static void setClip() {
		clipX = 0;
		clipY = 0;
		clipTopX = width;
		clipTopY = height;
		lastX = clipTopX - 1;
		clipCenterX = clipTopX / 2;
	}

	// proper name (notice that topX and topY means x+width, y+height)
	public static void setClip(int x, int y, int topX, int topY) {
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		if (topX > width) {
			topX = width;
		}
		if (topY > height) {
			topY = height;
		}
		clipX = x;
		clipY = y;
		clipTopX = topX;
		clipTopY = topY;
		lastX = clipTopX;
		clipCenterX = clipTopX / 2;
		clipCenterY = clipTopY / 2;
	}

	// proper name.
	public static void clear() {
		int i = width * height;
		for (int j = 0; j < i; j++) {
			pixels[j] = 0;
		}
		if (depth != null) {
			Arrays.fill(depth, Integer.MAX_VALUE);
		}
	}

	// proper name. transparent fill rect.
	public static void fillRect(int x, int y, int w, int h, int rgb, int alpha) {
		if (x < clipX) {
			w -= clipX - x;
			x = clipX;
		}
		if (y < clipY) {
			h -= clipY - y;
			y = clipY;
		}
		if (x + w > clipTopX) {
			w = clipTopX - x;
		}
		if (y + h > clipTopY) {
			h = clipTopY - y;
		}
		int l1 = 256 - alpha;
		int i2 = (rgb >> 16 & 0xff) * alpha;
		int j2 = (rgb >> 8 & 0xff) * alpha;
		int k2 = (rgb & 0xff) * alpha;
		int k3 = width - w;
		int offset = x + y * width;
		for (int i4 = 0; i4 < h; i4++) {
			for (int j4 = -w; j4 < 0; j4++) {
				int l2 = (pixels[offset] >> 16 & 0xff) * l1;
				int i3 = (pixels[offset] >> 8 & 0xff) * l1;
				int j3 = (pixels[offset] & 0xff) * l1;
				int k4 = (i2 + l2 >> 8 << 16) + (j2 + i3 >> 8 << 8) + (k2 + j3 >> 8);
				pixels[offset++] = k4;
			}
			offset += k3;
		}

	}

	// proper name. solid fill rect.
	public static void fillRect(int x, int y, int w, int h, int rgb) {
		if (x < clipX) {
			w -= clipX - x;
			x = clipX;
		}
		if (y < clipY) {
			h -= clipY - y;
			y = clipY;
		}
		if (x + w > clipTopX) {
			w = clipTopX - x;
		}
		if (y + h > clipTopY) {
			h = clipTopY - y;
		}
		int k1 = width - w;
		int l1 = x + y * width;
		for (int i2 = -h; i2 < 0; i2++) {
			for (int j2 = -w; j2 < 0; j2++) {
				pixels[l1++] = rgb;
			}

			l1 += k1;
		}

	}

	// TODO rename.
	public static void drawRect(int x, int y, int w, int h, int rgb) {
		drawHorizontalLine(x, y, w, rgb);
		drawHorizontalLine(x, y + h - 1, w, rgb);
		drawVerticalLine(x, y, h, rgb);
		drawVerticalLine(x + w - 1, y, h, rgb);
	}

	public static void fillCircle(int x, int y, int radius, int color, int alpha) {
		int y1 = y - radius;
		if (y1 < 0) {
			y1 = 0;
		}
		int y2 = y + radius;
		if (y2 >= height) {
			y2 = height - 1;
		}
		int a2 = 256 - alpha;
		int r1 = (color >> 16 & 0xff) * alpha;
		int g1 = (color >> 8 & 0xff) * alpha;
		int b1 = (color & 0xff) * alpha;
		for (int iy = y1; iy <= y2; iy++) {
			int dy = iy - y;
			int dist = (int) Math.sqrt(radius * radius - dy * dy);
			int x1 = x - dist;
			if (x1 < 0) {
				x1 = 0;
			}
			int x2 = x + dist;
			if (x2 >= width) {
				x2 = width - 1;
			}
			int pos = x1 + iy * width;
			for (int ix = x1; ix <= x2; ix++) {
				int r2 = (pixels[pos] >> 16 & 0xff) * a2;
				int g2 = (pixels[pos] >> 8 & 0xff) * a2;
				int b2 = (pixels[pos] & 0xff) * a2;
				pixels[pos++] = (r1 + r2 >> 8 << 16) + (g1 + g2 >> 8 << 8) + (b1 + b2 >> 8);
			}
		}
	}

	// TODO rename.
	public static void drawRect(int x, int y, int w, int h, int rgb, int alpha) {
		drawHorizontalLine(x, y, w, rgb, alpha);
		drawHorizontalLine(x, y + h - 1, w, rgb, alpha);
		if (h >= 3) {
			drawVerticalLine(x, y + 1, h - 2, rgb, alpha);
			drawVerticalLine(x + w - 1, y + 1, h - 2, rgb, alpha);
		}
	}

	public static void drawHorizontalLine(int x, int y, int w, int rgb) {
		if (y < clipY || y >= clipTopY) {
			return;
		}
		if (x < clipX) {
			w -= clipX - x;
			x = clipX;
		}
		if (x + w > clipTopX) {
			w = clipTopX - x;
		}
		int i1 = x + y * width;
		for (int j1 = 0; j1 < w; j1++) {
			pixels[i1 + j1] = rgb;
		}
	}

	public static void drawHorizontalLine(int x, int y, int w, int rgb, int alpha) {
		if (y < clipY || y >= clipTopY) {
			return;
		}
		if (x < clipX) {
			w -= clipX - x;
			x = clipX;
		}
		if (x + w > clipTopX) {
			w = clipTopX - x;
		}
		int j1 = 256 - alpha;
		int k1 = (rgb >> 16 & 0xff) * alpha;
		int l1 = (rgb >> 8 & 0xff) * alpha;
		int i2 = (rgb & 0xff) * alpha;
		int i3 = x + y * width;
		for (int j3 = 0; j3 < w; j3++) {
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = (k1 + j2 >> 8 << 16) + (l1 + k2 >> 8 << 8) + (i2 + l2 >> 8);
			pixels[i3++] = k3;
		}

	}

	public static void drawVerticalLine(int x, int y, int h, int rgb) {
		if (x < clipX || x >= clipTopX) {
			return;
		}
		if (y < clipY) {
			h -= clipY - y;
			y = clipY;
		}
		if (y + h > clipTopY) {
			h = clipTopY - y;
		}
		int j1 = x + y * width;
		for (int k1 = 0; k1 < h; k1++) {
			pixels[j1 + k1 * width] = rgb;
		}

	}

	public static void drawVerticalLine(int x, int y, int height, int rgb, int alpha) {
		if (x < clipX || x >= clipTopX) {
			return;
		}
		if (y < clipY) {
			height -= clipY - y;
			y = clipY;
		}
		if (y + height > clipTopY) {
			height = clipTopY - y;
		}
		int j1 = 256 - alpha;
		int k1 = (rgb >> 16 & 0xff) * alpha;
		int l1 = (rgb >> 8 & 0xff) * alpha;
		int i2 = (rgb & 0xff) * alpha;
		int i3 = x + y * width;
		for (int j3 = 0; j3 < height; j3++) {
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = (k1 + j2 >> 8 << 16) + (l1 + k2 >> 8 << 8) + (i2 + l2 >> 8);
			pixels[i3] = k3;
			i3 += width;
		}

	}

	RSGraphics() {

	}

}
