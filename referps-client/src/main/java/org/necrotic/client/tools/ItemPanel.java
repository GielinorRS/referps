package org.necrotic.client.tools;

import org.necrotic.client.cache.definition.ItemDefinition;
import org.necrotic.client.graphics.RSImageProducer;
import org.necrotic.client.graphics.Sprite;
import org.necrotic.client.world.Rasterizer;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ItemPanel extends JPanel {
	public ItemEditor itemEditor;
	private RSImageProducer imageProducer;
	private Sprite invBackground = null;
	private Sprite invTransBackground = null;
	public boolean saved = false;

	public ItemPanel(ItemEditor editor) {
		itemEditor = editor;
		imageProducer = new RSImageProducer(240, 334, this);
		invBackground = new Sprite("invedit");
		invTransBackground = new Sprite("invbacktrans");
	}

	public byte[] read(File file) {
		try {
			byte[] buffer = new byte[4096];
			ByteArrayOutputStream ous = new ByteArrayOutputStream();
			InputStream ios = new FileInputStream(file);
			int read = 0;

			while ((read = ios.read(buffer)) != -1) {
				ous.write(buffer, 0, read);
			}

			try {
				ous.close();
				ios.close();
			} catch (IOException e) {
			}

			return ous.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		imageProducer.initDrawingArea();
		Rasterizer.setAllPixelsToZero();

		Sprite inventoryModel = ItemDefinition.getSprite(itemEditor.getItemID(), 1, (itemEditor.isSelected() ? 0xffffff : 0));
		Sprite bigInventoryModel = ItemDefinition.getSprite(itemEditor.getItemID(), 1, 0, 3, false);

		invBackground.drawSprite(0, 0);
		invTransBackground.drawSprite(40, 44);

		//client.smallText.method389(true, 58, 0xffffff, "x: " + itemEditor.x + ", y: " + itemEditor.y, 245);

		if (inventoryModel != null) {
			inventoryModel.drawSprite(41, 45);
			bigInventoryModel.drawSprite(58, 100);
		} else {
			repaint();
		}

		imageProducer.drawGraphics(g, 0, 0);
	}
}