package org.necrotic.client.graphics;

import java.awt.*;
import java.awt.image.*;

public final class RSImageProducer implements ImageProducer, ImageObserver {

	private final int[] pixels;
	public final int width;
	public final int height;
	private final ColorModel aColorModel318;
	private ImageConsumer anImageConsumer319;
	private final Image image;
	public float[] depthPixels;

	public RSImageProducer(int w, int h, Component component) {
		width = w;
		height = h;
		pixels = new int[w * h];
		depthPixels = new float[width * height];
		aColorModel318 = new DirectColorModel(32, 0xff0000, 65280, 255);
		image = component.createImage(this);
		method239();
		component.prepareImage(image, this);
		method239();
		component.prepareImage(image, this);
		method239();
		component.prepareImage(image, this);
		initDrawingArea();
	}

	@Override
	public synchronized void addConsumer(ImageConsumer imageconsumer) {
		anImageConsumer319 = imageconsumer;
		imageconsumer.setDimensions(width, height);
		imageconsumer.setProperties(null);
		imageconsumer.setColorModel(aColorModel318);
		imageconsumer.setHints(14);
	}

	public void drawGraphics(Graphics g, int xPos, int yPos) {
		method239();
		g.drawImage(image, xPos, yPos, this);
	}

	@Override
	public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1) {
		return true;
	}

	public void initDrawingArea() {
		DrawingArea.initDrawingArea(height, width, pixels, depthPixels);
	}

	@Override
	public synchronized boolean isConsumer(ImageConsumer imageconsumer) {
		return anImageConsumer319 == imageconsumer;
	}

	private synchronized void method239() {
		if (anImageConsumer319 != null) {
			anImageConsumer319.setPixels(0, 0, width, height, aColorModel318, pixels, 0, width);
			anImageConsumer319.imageComplete(2);
		}
	}

	@Override
	public synchronized void removeConsumer(ImageConsumer imageconsumer) {
		if (anImageConsumer319 == imageconsumer) {
			anImageConsumer319 = null;
		}
	}

	@Override
	public void requestTopDownLeftRightResend(ImageConsumer imageconsumer) {
		System.out.println("TDLR");
	}

	@Override
	public void startProduction(ImageConsumer imageconsumer) {
		addConsumer(imageconsumer);
	}

	@Override
	public String toString() {
		return new StringBuffer().append("Width: ").append(width).append(" Height: ").append(height).toString();
	}

}