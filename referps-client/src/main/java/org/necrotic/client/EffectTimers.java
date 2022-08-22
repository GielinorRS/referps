package org.necrotic.client;

import org.necrotic.client.cache.definition.ItemDefinition;
import org.necrotic.client.graphics.DrawingArea;
import org.necrotic.client.graphics.Sprite;
import org.necrotic.client.graphics.gameframe.GameFrame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EffectTimers {

	// Timers
	private static List<EffectTimer> timers = new ArrayList<EffectTimer>();

	public static List<EffectTimer> getTimers() {
		return timers;
	}

	/**
	 * Adds a timer to the list.
	 *
	 * @param et
	 */
	public static void add(EffectTimer et) {

		// Check if timer already exists.. If so, simply update delay.
		for (EffectTimer timer : timers) {
			if (timer.getItemId() == et.getItemId()) {
				timer.setSeconds(et.getSecondsTimer().secondsRemaining());
				timer.setTime(et.getTime());
				return;
			}
		}

		// Add the timer since it wasn't found..
		timers.add(et);
	}


	/**
	 * Draws all of our timers onto the game screen.
	 */
	public static void draw() {
		int xDraw = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? Client.clientWidth - 325 : Client.clientWidth - 276;
		int yDraw = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? Client.clientHeight - 210 : Client.clientHeight - 170;

		if (GameFrame.getScreenMode() != GameFrame.ScreenMode.FIXED && Client.instance.getWidth() >= 1000) {
			yDraw = Client.clientHeight - 48;
		} else if (GameFrame.getScreenMode() != GameFrame.ScreenMode.FIXED && Client.instance.getWidth() >= 800) {
			yDraw = Client.clientHeight - 86;
		}
		Iterator<EffectTimer> it = timers.iterator();
		while (it.hasNext()) {
			EffectTimer timer = it.next();
			// If the timer has finished, remove it.
			if (timer.getSecondsTimer().finished()) {
				it.remove();
			} else {
				// Otherwise draw it..

				Sprite sprite = ItemDefinition.getSprite(timer.getItemId(), 1, 0, 1.25D, true);

				String string = formatMinutes(timer.getSecondsTimer().secondsRemaining());

				if (sprite != null) {
					if (GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED) {
						DrawingArea.method338(yDraw - 21, 28, 150, 0, 63, xDraw + 5);
						DrawingArea.method335(0x433A32, yDraw - 20, 61, 26, 150, xDraw + 6);

						Client.instance.newSmallFont.drawBasicString(string, xDraw + 32, yDraw - 1, 0xFFFFFF, 1);

						sprite.drawSprite1(xDraw + 2, yDraw - 23);
					} else {
						DrawingArea.method338(yDraw - 21, 28, 150, 0, 63, xDraw + 5);
						DrawingArea.method335(0x433A32, yDraw - 20, 61, 26, 150, xDraw + 6);

						Client.instance.newSmallFont.drawBasicString(string, xDraw + 32, yDraw - 1, 0xFFFFFF, 1);

						sprite.drawSprite(xDraw + 2, yDraw - 23);
					}

					yDraw -= 30;
				}
			}
		}
	}

	private static String formatMinutes(int seconds) {
		int i = (int) Math.floor(seconds / 60);
		int j = seconds - i * 60;
		String str1 = "" + i;
		String str2 = "" + j;
		if (j < 10) {
			str2 = "0" + str2;
		}
		if (i < 10) {
			str1 = "0" + str1;
		}
		return str1 + ":" + str2;
	}
}
