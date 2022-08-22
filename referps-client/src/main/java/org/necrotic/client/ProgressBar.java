package org.necrotic.client;

public class ProgressBar extends RSInterface {

	public int colorTypes[];

	public int progressBarState;

	public boolean shadow;

	public int progressBarPercentage;

	public int defaultBarState;

	public boolean drawHorizontal;

	public int defaultColorTypes[];

	public ProgressBar(final int childId, final int width, final int height, final int[] colorTypes, boolean drawHorizontal, boolean shadow, final String tooltip, final int[] defaultColorTypes) {
		this.id = childId;
		this.type = 554;
		this.width = width;
		this.height = height;
		this.colorTypes = colorTypes;
		this.drawHorizontal = drawHorizontal;
		this.tooltip = tooltip;
		this.shadow = shadow;
		this.defaultColorTypes = defaultColorTypes;
		RSInterface.interfaceCache[childId] = this;
	}

}
