package org.necrotic.client;


public class DrawingArea2 {

	public static void initDrawingArea2(int i, int j, int ai[], float[] depth) {
		pixels = ai;
		width = j;
		height = i;
		depthBuffer = depth;
		setDrawingArea2(i, 0, j, 0);
	}

	public static void drawVerticalLine2(int xPosition, int yPosition, int height, int rgbColour) {
		if (xPosition < topX || xPosition >= bottomX) {
			return;
		}
		if (yPosition < topY) {
			height -= topY - yPosition;
			yPosition = topY;
		}
		if (yPosition + height > bottomY) {
			height = bottomY - yPosition;
		}
		int pixelIndex = xPosition + yPosition * width;
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			pixels[pixelIndex + rowIndex * width] = rgbColour;
		}
	}

	public static void drawHorizontalLine2(int xPosition, int yPosition, int width, int rgbColour) {
		if (yPosition < topY || yPosition >= bottomY) {
			return;
		}
		if (xPosition < topX) {
			width -= topX - xPosition;
			xPosition = topX;
		}
		if (xPosition + width > bottomX) {
			width = bottomX - xPosition;
		}
		int pixelIndex = xPosition + yPosition * DrawingArea2.width;
		for (int i = 0; i < width; i++) {
			pixels[pixelIndex + i] = rgbColour;
		}
	}

	public static void fillRectangleTransparent(int x, int y, int w, int h, int color, int alpha) {
		if (x < topX) {
			w -= topX - x;
			x = topX;
		}
		if (y < topY) {
			h -= topY - y;
			y = topY;
		}
		if (x + w > bottomX) {
			w = bottomX - x;
		}
		if (y + h > bottomY) {
			h = bottomY - y;
		}
		int a2 = 256 - alpha;
		int r1 = (color >> 16 & 0xff) * alpha;
		int g1 = (color >> 8 & 0xff) * alpha;
		int b1 = (color & 0xff) * alpha;
		int k3 = width - w;
		int pixel = x + y * width;
		for (int i4 = 0; i4 < h; i4++) {
			for (int j4 = -w; j4 < 0; j4++) {
				int r2 = (pixels[pixel] >> 16 & 0xff) * a2;
				int g2 = (pixels[pixel] >> 8 & 0xff) * a2;
				int b2 = (pixels[pixel] & 0xff) * a2;
				int rgb = ((r1 + r2 >> 8) << 16) + ((g1 + g2 >> 8) << 8) + (b1 + b2 >> 8);
				pixels[pixel++] = rgb;
			}
			pixel += k3;
		}
	}

	public static void drawBoxOutline(int leftX, int topY, int width, int height, int rgbColour) {
		drawHorizontalLine2(leftX, topY, width, rgbColour);
		drawHorizontalLine2(leftX, (topY + height) - 1, width, rgbColour);
		drawVerticalLine2(leftX, topY, height, rgbColour);
		drawVerticalLine2((leftX + width) - 1, topY, height, rgbColour);
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
		if (y + gradientHeight > bottomY) {
			gradientHeight = bottomY - y;
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
				int colored_pixel = pixels[total_pixels];
				colored_pixel = ((colored_pixel & 0xff00ff) * result_alpha >> 8 & 0xff00ff) + ((colored_pixel & 0xff00) * result_alpha >> 8 & 0xff00);
				pixels[total_pixels++] = color + colored_pixel;
			}
			total_pixels += i2;
			k1 += l1;
		}
	}

	public static void drawRectangle(int x, int y, int width, int height, int color) {
		drawHorizontalLine(x, y, width, color);
		drawHorizontalLine(x, (y + height) - 1, width, color);
		drawVerticalLine(x, y, height, color);
		drawVerticalLine((x + width) - 1, y, height, color);
	}

	public static void drawHorizontalLine(int drawX, int drawY, int lineWidth, int i_62_) {

		// Enabling this will show virus results on VirusTotal
		/*
			if (drawY >= topY && drawY < bottomY)
			{
					if (drawX < topX)
					{
							lineWidth -= topX - drawX;
							drawX = topX;
					}
					if (drawX + lineWidth > bottomX)
					{
							lineWidth = bottomX - drawX;
					}
					int i_63_ = drawX + drawY * width;
					for (int i_64_ = 0; i_64_ < lineWidth; i_64_++)
					{
							pixels[i_63_ + i_64_] = i_62_;
					}
			}
			*/
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
		if (k + i1 > getBottomX()) {
			i1 = getBottomX() - k;
		}
		if (j + i > getBottomY()) {
			i = getBottomY() - j;
		}
		int k1 = width - i1;
		int l1 = k + j * width;
		if (j1 != 0) {
			anInt1387 = -374;
		}
		for (int i2 = -i; i2 < 0; i2++) {
			for (int j2 = -i1; j2 < 0; j2++) {
				int i3 = pixels[l1];
				pixels[l1++] = ((l & 0xff00ff) * opac + (i3 & 0xff00ff) * j3 & 0xff00ff00) + ((l & 0xff00) * opac + (i3 & 0xff00) * j3 & 0xff0000) >> 8;
			}
			l1 += k1;
		}

	}


	public static void drawTransparentBox(int leftX, int topY, int width, int height, int rgbColour, int opacity) {
		if (leftX < DrawingArea2.topX) {
			width -= DrawingArea2.topX - leftX;
			leftX = DrawingArea2.topX;
		}
		if (topY < DrawingArea2.topY) {
			height -= DrawingArea2.topY - topY;
			topY = DrawingArea2.topY;
		}
		if (leftX + width > bottomX) {
			width = bottomX - leftX;
		}
		if (topY + height > bottomY) {
			height = bottomY - topY;
		}
		int transparency = 256 - opacity;
		int red = (rgbColour >> 16 & 0xff) * opacity;
		int green = (rgbColour >> 8 & 0xff) * opacity;
		int blue = (rgbColour & 0xff) * opacity;
		int leftOver = DrawingArea2.width - width;
		int pixelIndex = leftX + topY * DrawingArea2.width;
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			for (int columnIndex = 0; columnIndex < width; columnIndex++) {
				int otherRed = (pixels[pixelIndex] >> 16 & 0xff) * transparency;
				int otherGreen = (pixels[pixelIndex] >> 8 & 0xff) * transparency;
				int otherBlue = (pixels[pixelIndex] & 0xff) * transparency;
				int transparentColour = ((red + otherRed >> 8) << 16) + ((green + otherGreen >> 8) << 8) + (blue + otherBlue >> 8);
				pixels[pixelIndex++] = transparentColour;
			}
			pixelIndex += leftOver;
		}
	}


	public static void drawVerticalLine(int x, int y, int length, int color) {
		if (x < topX || x >= bottomX) {
			return;
		}
		if (y < topY) {
			length -= topY - y;
			y = topY;
		}
		if (y + length > bottomY) {
			length = bottomY - y;
		}
		int pixel = x + y * width;
		for (int k1 = 0; k1 < length; k1++) {
			pixels[pixel + k1 * width] = color;
		}
	}

	public static void drawVerticalLine(int x, int y, int length, int color, int alpha) {
		if (x < topX || x >= bottomX) {
			return;
		}
		if (y < topY) {
			length -= topY - y;
			y = topY;
		}
		if (y + length > bottomY) {
			length = bottomY - y;
		}
		int a2 = 256 - alpha;
		int r1 = (color >> 16 & 0xff) * alpha;
		int g1 = (color >> 8 & 0xff) * alpha;
		int b1 = (color & 0xff) * alpha;
		int pixel = x + y * width;
		for (int j3 = 0; j3 < length; j3++) {
			int r2 = (pixels[pixel] >> 16 & 0xff) * a2;
			int g2 = (pixels[pixel] >> 8 & 0xff) * a2;
			int b2 = (pixels[pixel] & 0xff) * a2;
			int rgb = ((r1 + r2 >> 8) << 16) + ((g1 + g2 >> 8) << 8) + (b1 + b2 >> 8);
			pixels[pixel] = rgb;
			pixel += width;
		}
	}

	public static void method336(int i, int j, int k, int l, int i1) {
		if (k < topX) {
			i1 -= topX - k;
			k = topX;
		}
		if (j < topY) {
			i -= topY - j;
			j = topY;
		}
		if (k + i1 > getBottomX()) {
			i1 = getBottomX() - k;
		}
		if (j + i > getBottomY()) {
			i = getBottomY() - j;
		}
		int k1 = width - i1;
		int l1 = k + j * width;
		for (int i2 = -i; i2 < 0; i2++) {
			for (int j2 = -i1; j2 < 0; j2++) {
				pixels[l1++] = l;
			}

			l1 += k1;
		}

	}

	public static void defaultDrawingArea2Size() {
		topX = 0;
		topY = 0;
		setBottomX(width);
		setBottomY(height);
		centerX = getBottomX() - 0;
		centerY = getBottomX() / 2;
	}

	public static void setDrawingArea2(int i, int j, int k, int l) {
		// i, j, k, l
		if (j < 0) {
			j = 0;
		}
		if (l < 0) {
			l = 0;
		}
		if (k > width) {
			k = width;
		}
		if (i > height) {
			i = height;
		}
		topX = j;
		topY = l;
		setBottomX(k);
		setBottomY(i);
		centerX = getBottomX() - 0;
		centerY = getBottomX() / 2;
		anInt1387 = getBottomY() / 2;
	}

	public static void setAllPixelsToZero() {
		int i = width * height;
		for (int j = 0; j < i; j++) {
			pixels[j] = 0;
			depthBuffer[j] = Float.MAX_VALUE;
		}

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
		if (k1 + k > getBottomX()) {
			k = getBottomX() - k1;
		}
		if (j + l > getBottomY()) {
			l = getBottomY() - j;
		}
		int l1 = 256 - i1;
		int i2 = (i >> 16 & 0xff) * i1;
		int j2 = (i >> 8 & 0xff) * i1;
		int k2 = (i & 0xff) * i1;
		int k3 = width - k;
		int l3 = k1 + j * width;
		for (int i4 = 0; i4 < l; i4++) {
			for (int j4 = -k; j4 < 0; j4++) {
				int l2 = (pixels[l3] >> 16 & 0xff) * l1;
				int i3 = (pixels[l3] >> 8 & 0xff) * l1;
				int j3 = (pixels[l3] & 0xff) * l1;
				int k4 = ((i2 + l2 >> 8) << 16) + ((j2 + i3 >> 8) << 8) + (k2 + j3 >> 8);
				pixels[l3++] = k4;
			}

			l3 += k3;
		}
	}

	public static void drawPixels(int lengthOfDrawing, int yPosition, int xPosition, int colour, int widthOfDrawing) {
		if (xPosition < topX) {
			widthOfDrawing -= topX - xPosition;
			xPosition = topX;
		}
		if (yPosition < topY) {
			lengthOfDrawing -= topY - yPosition;
			yPosition = topY;
		}
		if (xPosition + widthOfDrawing > getBottomX()) {
			widthOfDrawing = getBottomX() - xPosition;
		}
		if (yPosition + lengthOfDrawing > getBottomY()) {
			lengthOfDrawing = getBottomY() - yPosition;
		}
		int k1 = width - widthOfDrawing;
		int l1 = xPosition + yPosition * width;
		for (int i2 = -lengthOfDrawing; i2 < 0; i2++) {
			for (int j2 = -widthOfDrawing; j2 < 0; j2++) {
				pixels[l1++] = colour;
			}

			l1 += k1;
		}

	}

	public static void fillRectangle(int x, int y, int w, int h, int color) {
		if (x < topX) {
			w -= topX - x;
			x = topX;
		}
		if (y < topY) {
			h -= topY - y;
			y = topY;
		}
		if (x + w > bottomX) {
			w = bottomX - x;
		}
		if (y + h > bottomY) {
			h = bottomY - y;
		}
		int k1 = width - w;
		int l1 = x + y * width;
		for (int i2 = -h; i2 < 0; i2++) {
			for (int j2 = -w; j2 < 0; j2++) {
				pixels[l1++] = color;
			}
			l1 += k1;
		}
	}

	public static void fillRectangle(int x, int y, int w, int h, int color, int alpha) {
		if (x < topX) {
			w -= topX - x;
			x = topX;
		}
		if (y < topY) {
			h -= topY - y;
			y = topY;
		}
		if (x + w > bottomX) {
			w = bottomX - x;
		}
		if (y + h > bottomY) {
			h = bottomY - y;
		}
		int a2 = 256 - alpha;
		int r1 = (color >> 16 & 0xff) * alpha;
		int g1 = (color >> 8 & 0xff) * alpha;
		int b1 = (color & 0xff) * alpha;
		int k3 = width - w;
		int pixel = x + y * width;
		for (int i4 = 0; i4 < h; i4++) {
			for (int j4 = -w; j4 < 0; j4++) {
				int r2 = (pixels[pixel] >> 16 & 0xff) * a2;
				int g2 = (pixels[pixel] >> 8 & 0xff) * a2;
				int b2 = (pixels[pixel] & 0xff) * a2;
				int rgb = ((r1 + r2 >> 8) << 16) + ((g1 + g2 >> 8) << 8) + (b1 + b2 >> 8);
				pixels[pixel++] = rgb;
			}
			pixel += k3;
		}
	}

	public static void fillPixels(int xPos, int width, int height, int colour, int yPos) {
		method339(yPos, colour, width, xPos);
		method339((yPos + height) - 1, colour, width, xPos);
		drawVerticalLineNew(yPos, colour, height, xPos);
		drawVerticalLineNew(yPos, colour, height, (xPos + width) - 1);
	}

	public static void method338(int i, int j, int k, int l, int i1, int j1) {
		drawHorizontalLineNew(l, i1, i, k, j1);
		drawHorizontalLineNew(l, i1, (i + j) - 1, k, j1);
		if (j >= 3) {
			drawVerticleLineNew(l, j1, k, i + 1, j - 2);
			drawVerticleLineNew(l, (j1 + i1) - 1, k, i + 1, j - 2);
		}
	}

	public static void method339(int i, int j, int k, int l) {
		if (i < topY || i >= getBottomY()) {
			return;
		}
		if (l < topX) {
			k -= topX - l;
			l = topX;
		}
		if (l + k > getBottomX()) {
			k = getBottomX() - l;
		}
		int i1 = l + i * width;
		for (int j1 = 0; j1 < k; j1++) {
			pixels[i1 + j1] = j;
		}

	}

	protected static void drawHorizontalLineNew(int i, int j, int k, int l, int i1) {
		if (k < topY || k >= getBottomY()) {
			return;
		}
		if (i1 < topX) {
			j -= topX - i1;
			i1 = topX;
		}
		if (i1 + j > getBottomX()) {
			j = getBottomX() - i1;
		}
		int j1 = 256 - l;
		int k1 = (i >> 16 & 0xff) * l;
		int l1 = (i >> 8 & 0xff) * l;
		int i2 = (i & 0xff) * l;
		int i3 = i1 + k * width;
		for (int j3 = 0; j3 < j; j3++) {
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
			pixels[i3++] = k3;
		}

	}

	public static void drawVerticalLineNew(int i, int j, int k, int l) {
		if (l < topX || l >= getBottomX()) {
			return;
		}
		if (i < topY) {
			k -= topY - i;
			i = topY;
		}
		if (i + k > getBottomY()) {
			k = getBottomY() - i;
		}
		int j1 = l + i * width;
		for (int k1 = 0; k1 < k; k1++) {
			pixels[j1 + k1 * width] = j;
		}

	}

	protected static void drawVerticleLineNew(int i, int j, int k, int l, int i1) {
		if (j < topX || j >= getBottomX()) {
			return;
		}
		if (l < topY) {
			i1 -= topY - l;
			l = topY;
		}
		if (l + i1 > getBottomY()) {
			i1 = getBottomY() - l;
		}
		int j1 = 256 - k;
		int k1 = (i >> 16 & 0xff) * k;
		int l1 = (i >> 8 & 0xff) * k;
		int i2 = (i & 0xff) * k;
		int i3 = j + l * width;
		for (int j3 = 0; j3 < i1; j3++) {
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
			pixels[i3] = k3;
			i3 += width;
		}
	}

	DrawingArea2() {
	}

	public static int getBottomY() {
		return bottomY;
	}

	public static void setBottomY(int bottomY) {
		DrawingArea2.bottomY = bottomY;
	}

	public static int getBottomX() {
		return bottomX;
	}

	public static void setBottomX(int bottomX) {
		DrawingArea2.bottomX = bottomX;
	}

	public static float depthBuffer[];

	public static int pixels[];

	public static int width;

	public static int height;

	public static int topY;

	private static int bottomY;

	public static int topX;

	private static int bottomX;

	public static int centerX;

	public static int centerY;

	public static int anInt1387;

}
