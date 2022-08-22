package org.necrotic.client.graphics;

import org.necrotic.Configuration;
import org.necrotic.client.cache.node.NodeSub;
import org.necrotic.client.world.Rasterizer;


import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.util.Arrays;
import java.util.Hashtable;

public class DrawingArea extends NodeSub {

	public static float[] depthBuffer;
	public static int raster[];
	public static int width;
	public static int height;
	public static int topY;
	public static int clipBottom;
	public static int topX;
	public static int bottomX;
	public static int maxRight;
	public static int centerY;
	public static int middleY;

	public static void drawStroke(int xPos, int yPos, int width, int height, int color, int strokeWidth) {

		drawVerticalStrokeLine(xPos, yPos, height, color, strokeWidth);
		drawVerticalStrokeLine((xPos + width) - strokeWidth, yPos, height, color, strokeWidth);
		drawHorizontalStrokeLine(xPos, yPos, width, color, strokeWidth);
		drawHorizontalStrokeLine(xPos, (yPos + height) - strokeWidth, width, color, strokeWidth);

	}

	private static void drawVerticalStrokeLine(int xPosition, int yPosition, int height, int hexColor, int strokeWidth) {
		if (xPosition < topX || xPosition >= bottomX) {
			return;
		}
		if (yPosition < topY) {
			height -= topY - yPosition;
			yPosition = topY;
		}
		if (yPosition + height > clipBottom) {
			height = clipBottom - yPosition;
		}
		int pixelIndex = xPosition + yPosition * width;
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			for (int x = 0; x < strokeWidth; x++) {
				raster[pixelIndex + x + rowIndex * width] = hexColor;
			}
		}
	}

	private static void drawHorizontalStrokeLine(int xPos, int yPos, int w, int hexColor, int strokeWidth) {
		if (yPos < topY || yPos >= clipBottom) {
			return;
		}
		if (xPos < topX) {
			w -= topX - xPos;
			xPos = topX;
		}
		if (xPos + w > bottomX) {
			w = bottomX - xPos;
		}
		int index = xPos + yPos * width;
		int leftWidth = width - w;
		for (int x = 0; x < strokeWidth; x++) {
			for (int y = 0; y < w; y++) {
				raster[index++] = hexColor;
			}
			index += leftWidth;
		}

	}

	public static void drawAlphaFilledPixels(int xPos, int yPos, int pixelWidth, int pixelHeight, int color, int alpha) {// method586
		if (xPos < topX) {
			pixelWidth -= topX - xPos;
			xPos = topX;
		}
		if (yPos < topY) {
			pixelHeight -= topY - yPos;
			yPos = topY;
		}
		if (xPos + pixelWidth > bottomX) {
			pixelWidth = bottomX - xPos;
		}
		if (yPos + pixelHeight > clipBottom) {
			pixelHeight = clipBottom - yPos;
		}
		color = ((color & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((color & 0xff00) * alpha >> 8 & 0xff00);
		int k1 = 256 - alpha;
		int l1 = width - pixelWidth;
		int i2 = xPos + yPos * width;
		//color = Client.instance.hueShift(Client.instance.particleTick).getRGB();
		for (int j2 = 0; j2 < pixelHeight; j2++) {
			for (int k2 = -pixelWidth; k2 < 0; k2++) {
				int l2 = raster[i2];
				l2 = ((l2 & 0xff00ff) * k1 >> 8 & 0xff00ff) + ((l2 & 0xff00) * k1 >> 8 & 0xff00);
				raster[i2++] = color + l2;
			}
			i2 += l1;
		}
	}

	public static void fillCircle(int x, int y, int depth, int radius, int color, int alpha) {
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
			if (pos > Rasterizer.depthBuffer.length - 1) {
				pos = Rasterizer.depthBuffer.length - 1;
			}
			if (Rasterizer.depthBuffer[pos] >= depth - radius - 15 || Rasterizer.depthBuffer[pos++] >= depth + radius + 15) {
				for (int ix = x1; ix <= x2; ix++) {
					int r2 = (raster[pos] >> 16 & 0xff) * a2;
					int g2 = (raster[pos] >> 8 & 0xff) * a2;
					int b2 = (raster[pos] & 0xff) * a2;
					raster[pos++] = ((r1 + r2 >> 8) << 16) + ((g1 + g2 >> 8) << 8) + (b1 + b2 >> 8);
				}
			}
		}
	}

	public static void drawLine(int yPos, int color, int widthToDraw, int xPos) {
		if (yPos < topY || yPos >= clipBottom) {
			return;
		}
		if (xPos < topX) {
			widthToDraw -= topX - xPos;
			xPos = topX;
		}
		if (xPos + widthToDraw > bottomX) {
			widthToDraw = bottomX - xPos;
		}
		int base = xPos + yPos * width;
		for (int ptr = 0; ptr < widthToDraw; ptr++) {
			raster[base + ptr] = color;
		}

	}

	public static void setDrawingArea(int yBottom, int xTop, int xBottom, int yTop) {
		if (xTop < 0) {
			xTop = 0;
		}
		if (yTop < 0) {
			yTop = 0;
		}
		if (xBottom > width) {
			xBottom = width;
		}
		if (yBottom > height) {
			yBottom = height;
		}
		topX = xTop;
		topY = yTop;
		bottomX = xBottom;
		clipBottom = yBottom;
		//viewportRX = bottomX - 0;
		//	viewport_centerX = bottomX / 2;
		//viewport_centerY = bottomY / 2;
	}

	public static void transparentBox(int i, int j, int k, int l, int i1, int opac) {
		int j3 = 256 - opac;
		if (k < topX) {//clipLeft
			i1 -= topX - k;
			k = topX;
		}
		if (j < topY) {//clipTop
			i -= topY - j;
			j = topY;
		}
		if (k + i1 > bottomX) {//clipRight
			i1 = bottomX - k;
		}
		if (j + i > clipBottom) {//clipBottom
			i = clipBottom - j;
		}
		int k1 = width - i1;
		int l1 = k + j * width;
		for (int i2 = -i; i2 < 0; i2++) {
			for (int j2 = -i1; j2 < 0; j2++) {
				int i3 = raster[l1];
				raster[l1++] = ((l & 0xff00ff) * opac + (i3 & 0xff00ff) * j3 & 0xff00ff00) + ((l & 0xff00) * opac + (i3 & 0xff00) * j3 & 0xff0000) >> 8;
			}
			l1 += k1;
		}

	}

	public static void transparentBox(int i, int j, int k, int l, int i1, int j1, int opac) {
		int j3 = 256 - opac;
		if (k < topX) {
			i1 -= topX - k;
			k = topX;
		}
		if (j < topY) {
			i -= topY - j;
			j = topY;
		}
		if (k + i1 > bottomX) {
			i1 = bottomX - k;
		}
		if (j + i > clipBottom) {
			i = clipBottom - j;
		}
		int k1 = width - i1;
		int l1 = k + j * width;
		for (int i2 = -i; i2 < 0; i2++) {
			for (int j2 = -i1; j2 < 0; j2++) {
				int i3 = raster[l1];
				raster[l1++] = ((l & 0xff00ff) * opac + (i3 & 0xff00ff) * j3 & 0xff00ff00) + ((l & 0xff00) * opac + (i3 & 0xff00) * j3 & 0xff0000) >> 8;
			}
			l1 += k1;
		}

	}

	
	/*
	 * public static void transparentBox(int i, int j, int k, int l, int i1, int opac) {
		int j3 = 256 - opac;
		if (k < topX) {//clipLeft
			i1 -= topX - k;
			k = topX;
		}
		if (j < topY) {//clipTop
			i -= topY - j;
			j = topY;
		}
		if (k + i1 > bottomX) {//clipRight
			i1 = bottomX - k;
		}
		if (j + i > bottomY) {//clipBottom
			i = bottomY - j;
		}
		int k1 = width - i1;
		int l1 = k + j * width;
		for (int i2 = -i; i2 < 0; i2++) {
			for (int j2 = -i1; j2 < 0; j2++) {
				int i3 = pixels[l1];
				pixels[l1++] = ((l & 0xff00ff) * opac + (i3 & 0xff00ff) * j3 & 0xff00ff00) + ((l & 0xff00) * opac + (i3 & 0xff00) * j3 & 0xff0000) >> 8;
			}
			l1 += k1;
		}

	}
	 */
	public static void defaultDrawingAreaSize() {//setDefaultBounds
		topX = 0;
		topY = 0;
		bottomX = width;
		clipBottom = height;
		maxRight = bottomX - 0;
		centerY = bottomX / 2;
	}

	public static void drawAlphaGradient(int x, int y, int gradientWidth, int gradientHeight, int startColor, int endColor, int alpha) {
		int k1 = 0;
		int l1 = 0x10000 / gradientHeight;
		if (x < topX) {
			gradientWidth -= topX - x;
			x = topX;
		}
		if (y < topY) {
			k1 += (topY - y) * l1;
			gradientHeight -= topY - y;
			y = topY;
		}
		if (x + gradientWidth > bottomX) {
			gradientWidth = bottomX - x;
		}
		if (y + gradientHeight > clipBottom) {
			gradientHeight = clipBottom - y;
		}
		int i2 = width - gradientWidth;
		int result_alpha = 256 - alpha;
		int total_pixels = x + y * width;
		for (int k2 = -gradientHeight; k2 < 0; k2++) {
			int gradient1 = 0x10000 - k1 >> 8;
			int gradient2 = k1 >> 8;
			int gradient_color = ((startColor & 0xff00ff) * gradient1 + (endColor & 0xff00ff) * gradient2 & 0xff00ff00) + ((startColor & 0xff00) * gradient1 + (endColor & 0xff00) * gradient2 & 0xff0000) >>> 8;
			int color = ((gradient_color & 0xff00ff) * alpha >> 8 & 0xff00ff) + ((gradient_color & 0xff00) * alpha >> 8 & 0xff00);
			for (int k3 = -gradientWidth; k3 < 0; k3++) {
				int pixel_pixels = raster[total_pixels];
				pixel_pixels = ((pixel_pixels & 0xff00ff) * result_alpha >> 8 & 0xff00ff) + ((pixel_pixels & 0xff00) * result_alpha >> 8 & 0xff00);
				raster[total_pixels++] = color + pixel_pixels;
			}
			total_pixels += i2;
			k1 += l1;
		}
	}

	public static void drawFilledPixels(int x, int y, int pixelWidth, int pixelHeight, int color) {// method578
		if (x < topX) {
			pixelWidth -= topX - x;
			x = topX;
		}
		if (y < topY) {
			pixelHeight -= topY - y;
			y = topY;
		}
		if (x + pixelWidth > bottomX) {
			pixelWidth = bottomX - x;
		}
		if (y + pixelHeight > clipBottom) {
			pixelHeight = clipBottom - y;
		}
		int j1 = width - pixelWidth;
		int k1 = x + y * width;
		for (int l1 = -pixelHeight; l1 < 0; l1++) {
			for (int i2 = -pixelWidth; i2 < 0; i2++) {
				raster[k1++] = color;
			}
			k1 += j1;
		}
	}

	public static void drawHorizontalLine(int i, int j, int k, int l) {
		if (i < topY || i >= clipBottom) {
			return;
		}
		if (l < topX) {
			k -= topX - l;
			l = topX;
		}
		if (l + k > bottomX) {
			k = bottomX - l;
		}
		int i1 = l + i * width;
		for (int j1 = 0; j1 < k; j1++) {
			raster[i1 + j1] = j;
		}

	}// proper name. solid fill rect.

	public static void fillRect(int posX, int posY, int w, int h, int rgb) {
		if (posX < topX) {
			width -= topX - posX;
			posX = topX;
		}
		if (posY < topY) {
			height -= topY - posY;
			posY = topY;
		}
		if (posX + width > bottomX) {
			width = bottomX - posX;
		}
		if (posY + height > clipBottom) {
			height = clipBottom - posY;
		}
		int k1 = width - w;
		int l1 = posX + posY * width;
		for (int i2 = -h; i2 < 0; i2++) {
			for (int j2 = -w; j2 < 0; j2++) {
				raster[l1++] = rgb;
			}

			l1 += k1;
		}

	}

	public static void drawPixels(int height, int posY, int posX, int color, int width) {
		if (posX < topX) {
			width -= topX - posX;
			posX = topX;
		}
		if (posY < topY) {
			height -= topY - posY;
			posY = topY;
		}
		if (posX + width > bottomX) {
			width = bottomX - posX;
		}
		if (posY + height > clipBottom) {
			height = clipBottom - posY;
		}
		int k1 = DrawingArea.width - width;
		int l1 = posX + posY * DrawingArea.width;
		for (int i2 = -height; i2 < 0; i2++) {
			for (int j2 = -width; j2 < 0; j2++) {
				raster[l1++] = color;
			}

			l1 += k1;
		}

	}

	public static void fillPixels(int offSetX, int width, int height, int l, int offSetY) {
		drawHorizontalLine(offSetY, l, width, offSetX);
		drawHorizontalLine(offSetY + height - 1, l, width, offSetX);
		method341(offSetY, l, height, offSetX);
		method341(offSetY, l, height, offSetX + width - 1);
	}

	public static void drawRect(int x, int y, int w, int h, int rgb) {
		drawHorizontalLine(x, y, w, rgb);
		drawHorizontalLine(x, y + h - 1, w, rgb);
		drawVerticalLine(x, y, h, rgb);
		drawVerticalLine(x + w - 1, y, h, rgb);
	}


	public static void drawVerticalLine(int x, int y, int h, int rgb) {
		if (x < topX || x >= bottomX) {
			return;
		}
		if (y < topY) {
			h -= topY - y;
			y = topY;
		}
		if (y + h > clipBottom) {
			h = clipBottom - y;
		}
		int j1 = x + y * width;
		for (int k1 = 0; k1 < h; k1++) {
			raster[j1 + k1 * width] = rgb;
		}

	}

	private static void method341(int i, int j, int k, int l) {
		if (l < topX || l >= bottomX) {
			return;
		}
		if (i < topY) {
			k -= topY - i;
			i = topY;
		}
		if (i + k > clipBottom) {
			k = clipBottom - i;
		}
		int j1 = l + i * width;
		for (int k1 = 0; k1 < k; k1++) {
			raster[j1 + k1 * width] = j;
		}

	}

	public static void fillRect(int xPos, int yPos, int areaWidth, int areaHeight, int color, int transparency) {
		if (xPos < topX) {
			areaWidth -= topX - xPos;
			xPos = topX;
		}
		if (yPos < topY) {
			areaHeight -= topY - yPos;
			yPos = topY;
		}
		if (xPos + areaWidth > bottomX) {
			areaWidth = bottomX - xPos;
		}
		if (yPos + areaHeight > clipBottom) {
			areaHeight = clipBottom - yPos;
		}
		int opacity = 256 - transparency;
		int red = (color >> 16 & 0xff) * transparency;
		int green = (color >> 8 & 0xff) * transparency;
		int blue = (color & 0xff) * transparency;
		int xOffset = width - areaWidth;
		int pixel = xPos + yPos * width;
		for (int y = 0; y < areaHeight; y++) {
			for (int x = -areaWidth; x < 0; x++) {
				int originRed = (raster[pixel] >> 16 & 0xff) * opacity;
				int originGreen = (raster[pixel] >> 8 & 0xff) * opacity;
				int oritinBlue = (raster[pixel] & 0xff) * opacity;
				int blindedColor = (red + originRed >> 8 << 16) + (green + originGreen >> 8 << 8) + (blue + oritinBlue >> 8);
				raster[pixel++] = blindedColor;
			}

			pixel += xOffset;
		}
	}

	public static void initDrawingArea(int h, int w, int ai[], float[] depth) {
		raster = ai;
		width = w;
		height = h;
		setBounds(0, 0, w, h);
		if(Configuration.DEPTH_BUFFER)
			depthBuffer = depth;
	}

	public static void method335(int i, int j, int k, int l, int i1, int k1) {
		if (k1 < topX) {
			k -= topX - k1;
			k1 = topX;
		}
		if (j < topY) {
			l -= topY - j;
			j = topY;
		}
		if (k1 + k > bottomX) {
			k = bottomX - k1;
		}
		if (j + l > clipBottom) {
			l = clipBottom - j;
		}
		int l1 = 256 - i1;
		int i2 = (i >> 16 & 0xff) * i1;
		int j2 = (i >> 8 & 0xff) * i1;
		int k2 = (i & 0xff) * i1;
		int k3 = width - k;
		int l3 = k1 + j * width;
		for (int i4 = 0; i4 < l; i4++) {
			for (int j4 = -k; j4 < 0; j4++) {
				int l2 = (raster[l3] >> 16 & 0xff) * l1;
				int i3 = (raster[l3] >> 8 & 0xff) * l1;
				int j3 = (raster[l3] & 0xff) * l1;
				int k4 = (i2 + l2 >> 8 << 16) + (j2 + i3 >> 8 << 8) + (k2 + j3 >> 8);
				raster[l3++] = k4;
			}

			l3 += k3;
		}
	}

	public static void method338(int i, int j, int k, int l, int i1, int j1) {
		method340(l, i1, i, k, j1);
		method340(l, i1, i + j - 1, k, j1);

		if (j >= 3) {
			method342(l, j1, k, i + 1, j - 2);
			method342(l, j1 + i1 - 1, k, i + 1, j - 2);
		}
	}

	private static void method340(int i, int j, int k, int l, int i1) {
		if (k < topY || k >= clipBottom) {
			return;
		}
		if (i1 < topX) {
			j -= topX - i1;
			i1 = topX;
		}
		if (i1 + j > bottomX) {
			j = bottomX - i1;
		}
		int j1 = 256 - l;
		int k1 = (i >> 16 & 0xff) * l;
		int l1 = (i >> 8 & 0xff) * l;
		int i2 = (i & 0xff) * l;
		int i3 = i1 + k * width;
		for (int j3 = 0; j3 < j; j3++) {
			int j2 = (raster[i3] >> 16 & 0xff) * j1;
			int k2 = (raster[i3] >> 8 & 0xff) * j1;
			int l2 = (raster[i3] & 0xff) * j1;
			int k3 = (k1 + j2 >> 8 << 16) + (l1 + k2 >> 8 << 8) + (i2 + l2 >> 8);
			raster[i3++] = k3;
		}

	}

	private static void method342(int i, int j, int k, int l, int i1) {
		if (j < topX || j >= bottomX) {
			return;
		}
		if (l < topY) {
			i1 -= topY - l;
			l = topY;
		}
		if (l + i1 > clipBottom) {
			i1 = clipBottom - l;
		}
		int j1 = 256 - k;
		int k1 = (i >> 16 & 0xff) * k;
		int l1 = (i >> 8 & 0xff) * k;
		int i2 = (i & 0xff) * k;
		int i3 = j + l * width;
		for (int j3 = 0; j3 < i1; j3++) {
			int j2 = (raster[i3] >> 16 & 0xff) * j1;
			int k2 = (raster[i3] >> 8 & 0xff) * j1;
			int l2 = (raster[i3] & 0xff) * j1;
			int k3 = (k1 + j2 >> 8 << 16) + (l1 + k2 >> 8 << 8) + (i2 + l2 >> 8);
			raster[i3] = k3;
			i3 += width;
		}

	}

	public static void setAllPixelsToZero() {
		int i = width * height;
		for (int j = 0; j < i; j++) {
			raster[j] = 0;
		}

	}

	public static void setBounds(int posX, int posY, int width, int height) {
		if (posX < 0) {
			posX = 0;
		}

		if (posY < 0) {
			posY = 0;
		}

		if (width > DrawingArea.width) {
			width = DrawingArea.width;
		}

		if (height > DrawingArea.height) {
			height = DrawingArea.height;
		}

		DrawingArea.topX = posX;
		DrawingArea.topY = posY;
		DrawingArea.bottomX = width;
		DrawingArea.clipBottom = height;
		DrawingArea.maxRight = bottomX - 0;
		DrawingArea.centerY = bottomX / 2;
		middleY = clipBottom / 2;
	}

	public static void resetDepthBuffer() {
		if (depthBuffer == null || depthBuffer.length != raster.length) {
			depthBuffer = new float[raster.length];
		}
		Arrays.fill(depthBuffer, raster.length);
		
		
	}
	
	public DrawingArea() {
	}

	public static void drawRoundedRectangle(int x, int y, int width, int height, int color, int alpha, boolean filled, boolean shadowed) {
		if (shadowed) {
			drawRoundedRectangle(x + 1, y + 1, width, height, 0, alpha, filled, false);
		}
		if (alpha == -1) {
			if (filled) {
				drawHorizontalLine(y + 1, color, width - 4, x + 2);// method339
				drawHorizontalLine(y + height - 2, color, width - 4, x + 2);// method339
				drawPixels(height - 4, y + 2, x + 1, color, width - 2);// method336
			}
			drawHorizontalLine(y, color, width - 4, x + 2);// method339
			drawHorizontalLine(y + height - 1, color, width - 4, x + 2);// method339
			method341(y + 2, color, height - 4, x);// method341
			method341(y + 2, color, height - 4, x + width - 1);// method341
			drawPixels(1, y + 1, x + 1, color, 1);// method336
			drawPixels(1, y + 1, x + width - 2, color, 1);// method336
			drawPixels(1, y + height - 2, x + width - 2, color, 1);// method336
			drawPixels(1, y + height - 2, x + 1, color, 1);// method336
		} else if (alpha != -1) {
			if (filled) {
				method340(color, width - 4, y + 1, alpha, x + 2);// method340
				method340(color, width - 4, y + height - 2, alpha, x + 2);// method340
				method335(color, y + 2, width - 2, height - 4, alpha, x + 1);// method335
			}
			method340(color, width - 4, y, alpha, x + 2);// method340
			method340(color, width - 4, y + height - 1, alpha, x + 2);// method340
			method342(color, x, alpha, y + 2, height - 4);// method342
			method342(color, x + width - 1, alpha, y + 2, height - 4);// method342
			method335(color, y + 1, 1, 1, alpha, x + 1);// method335
			method335(color, y + 1, 1, 1, alpha, x + width - 2);// method335
			method335(color, y + height - 2, 1, 1, alpha, x + 1);// method335
			method335(color, y + height - 2, 1, 1, alpha, x + width - 2);// method335
		}
	}


	public static void fillRectangle(int color, int y, int widthz, int heightz, int opacity, int x) {
		fillRect(x, y, widthz, heightz, color, opacity);
	}

	public static void drawArc(double x, double y, double width1, double height1, int stroke, double start, double sweep, int color, int alpha, int closure, boolean fill) {
		Graphics2D graphics = createGraphics(raster, width, height);
		final Color CONVERTED_COLOR = new Color((color >> 16 & 0xff), (color >> 8 & 0xff), (color & 0xff), alpha);
		graphics.setColor(CONVERTED_COLOR);
		RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		render.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		graphics.setRenderingHints(render);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		graphics.setStroke(new BasicStroke(Math.max(stroke, 1)));

		// Closure types - OPEN(0), CHORD(1), PIE(2)
		Arc2D.Double arc = new Arc2D.Double(x + stroke, y + stroke, width1, height1, start, sweep, closure);

		if (fill) {
			graphics.fill(arc);
			graphics.setColor(Color.decode("0x3f372c").darker());
		}
		graphics.draw(arc);
	}

	private static final ColorModel COLOR_MODEL = new DirectColorModel(32, 0xff0000, 0xff00, 0xff);

	public static Graphics2D createGraphics(int[] pixels, int width, int height) {
		return new BufferedImage(COLOR_MODEL, java.awt.image.Raster.createWritableRaster(COLOR_MODEL.createCompatibleSampleModel(width, height), new DataBufferInt(pixels, width * height), null), false, new Hashtable<>()).createGraphics();
	}
}