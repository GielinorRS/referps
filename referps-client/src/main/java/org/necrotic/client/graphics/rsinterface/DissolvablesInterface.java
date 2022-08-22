package org.necrotic.client.graphics.rsinterface;

import org.necrotic.client.RSInterface;
import org.necrotic.client.graphics.fonts.TextDrawingArea;

public class DissolvablesInterface extends RSInterface {
	public static void handle(TextDrawingArea[] TDA) {
		RSInterface widget = addInterface(39000);

		int childId = 39001;

		addSprite(childId++, 1294);

		addText(childId++, "Dissolvables List", 0xff9933, true, true, -1, TDA, 2);

		addHoverButton(childId++, 1016, 1, 16, 16, "Close", -1, childId, 1);
		addHoveredButton(childId++, 1017, 2, 16, 16, childId++);

		RSInterface scroll = addInterface(childId++);
		scroll.width = 455;
		scroll.height = 261;
		scroll.scrollMax = 700 * 20;

		scroll.totalChildren(700);

		int scrollFrame = 0;
		int scrollChildId = childId;
		int scrollStartY = 0;
		for (int i = 0; i < 700; i++) {
			addText(scrollChildId, "TEST" + scrollChildId, 0xff9933, false, true, -1, TDA, 2);
			scroll.child(scrollFrame++, scrollChildId++, 180, scrollStartY + 1);
			scrollStartY += 20;
		}

		widget.totalChildren(childId - 39001 - 1);

		childId = 39001;
		int frame = 0;

		widget.child(frame++, childId++, 11, 11);//Background

		widget.child(frame++, childId++, 255, 21);//Title

		widget.child(frame++, childId++, 473, 20);//Close
		widget.child(frame++, childId++, 473, 20);
		childId++;

		widget.child(frame++, childId++, 18, 47);//Scroll frame
	}
}
