package org.necrotic.client.notification;

import org.necrotic.client.Client;
import org.necrotic.client.graphics.DrawingArea;

import java.awt.*;

public class AlertBox {

	private int width;
	private int height;
	private int openedHeight;
	private final String headerText;
	private final String[] lines;
	private boolean isHovered;
	private boolean closing = false;
	private boolean opening = false;
	private int color;

	private int opacitymax = 100;
	private int opacity = 10;
	private int addX;
	private int addY;
	private int spriteID;

	public AlertBox(int paramInt1, int paramInt2, int spriteId, int color, int opacity, String paramString, String... paramVarArgs) {
		width = paramInt1;
		height = paramInt2;
		openedHeight = paramInt2;
		headerText = paramString;
		lines = paramVarArgs;
		spriteID = spriteId;
		this.color = color;
		this.opacitymax = opacity;
	}

	public AlertBox(String paramString, int spriteId, int width, int height, int color, int opacity, String... paramVarArgs) {
		this(width, height, spriteId, color, opacity, paramString, paramVarArgs);
	}

	public void close() {
		closing = true;
	}

	public boolean done() {
		return closing && opacity < 10;
	}

	public void draw() {
		if (closing) {
			opacity -= 2;
			// headerOpacity -= 5;
			// addY -= 2;
			// width -= 1;
			height -= 2;
		}
		if (!closing && opacity == 10) {
			height = 10;
			opening = true;
		}

		int i = getStartX() + addX;
		int j = getStartY() + addY;
		// DrawingArea.drawAlphaGradient2(i, j + 25, width, getHeight() - 25,
		// paramColor.darker().getRGB(), paramColor.brighter().getRGB(), opacity);

		DrawingArea.drawRoundedRectangle(i, j + 5, width, getHeight() - 5, color, opacity, true, false);

		// DrawingArea.drawRectangle(j, 25, opacity, paramColor.darker().getRGB(),
		// width, i);
		if (!closing) {
			Client.instance.newRegularFont.drawCenteredString(headerText, getStartX(headerText), j + 25, Color.WHITE.getRGB(), 0);
			// Client.instance.oldSmallFont.drawCenteredText(Color.WHITE.getRGB(),
			// getStartX(headerText), headerText,
			// j + 25, true);
			for (int k = 0; k < lines.length; k++) {
				Client.instance.newSmallFont.drawCenteredString(lines[k], getStartX(lines[k]), getStartY(k), Color.WHITE.getRGB(), 0);
			}

			if (spriteID >= 0) {
				Client.spritesMap.get(spriteID).drawSprite3(i + 10, j + 8, opacity + 100);
				Client.spritesMap.get(spriteID).drawSprite3(i + width - 60, j + 8, opacity + 100);
			}
		}
	}

	public void processMouse(int paramInt1, int paramInt2) {
		if (closing) {
			return;
		}
		isHovered = paramInt1 >= getStartX() && paramInt1 <= getStartX() + width && paramInt2 >= getStartY() && paramInt2 <= getStartY() + getHeight() && opacity >= opacitymax - 30 && !opening;
		if (isHovered) {
			if (opacity > opacitymax - 30) {
				opacity -= 5;
			}
			Client.instance.menuActionName[1] = "Dismiss";
			Client.instance.menuActionID[1] = 476;
			Client.instance.menuActionRow = 2;
		} else {
			if (opacity <= opacitymax && opening) {
				height += 3;
			}
			if (opacity >= opacitymax || height == openedHeight) {
				opening = false;
				opacity = opacitymax;
			}
			if (opacity < opacitymax) {
				opacity += 2;
			}

		}
	}

	private int getStartX(String paramString) {
		return 254 - Client.instance.newSmallFont.getTextWidth(paramString) / paramString.length();
	}

	private int getStartY(int paramInt) {
		return getStartY() + 25 + 16 + paramInt * 14;
	}

	private int calculateRequiredExtraHeight() {
		return lines.length * 16 - 16;
	}

	private int getStartX() {
		return 254 - width / 2;
	}

	private int getStartY() {
		return (Client.instance.resize_canvas_requested ? Client.clientHeight - 171 : Client.clientHeight - 169) - getHeight();
	}

	private int getHeight() {
		return height + calculateRequiredExtraHeight();
	}
}
