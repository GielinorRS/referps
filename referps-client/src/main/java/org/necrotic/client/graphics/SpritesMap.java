package org.necrotic.client.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardOpenOption.READ;

public class SpritesMap implements Closeable {

	private Map<Integer, Sprite> map = new HashMap<>();
	private Map<Integer, byte[]> data = new HashMap<>();
	private FileChannel dat;
	private FileChannel idx;
	private int id;

	public SpritesMap(final int id) {
		this.id = id;
	}

	public void init(String directory) throws IOException {
		dat = FileChannel.open(new File(directory + "main_file_sprites.dat").toPath(), READ);
		idx = FileChannel.open(new File(directory + "main_file_sprites.idx").toPath(), READ);
	}

	public Sprite get(int id) {
		if (id < 0) {
			return new Sprite();
		}
		final Map<Integer, Sprite> map = this.map;
		if (map.containsKey(id)) {
			return map.get(id);
		}
		final FileChannel dat = this.dat;
		final FileChannel idx = this.idx;
		try {
			int entries = Math.toIntExact(idx.size() / 10);
			if (id > entries) {
				return null;
			}
			idx.position(id * 10);
			ByteBuffer idxBuffer = ByteBuffer.allocate(10);
			idx.read(idxBuffer);
			idxBuffer.flip();
			int pos = (idxBuffer.get() & 0xFF) << 16 | (idxBuffer.get() & 0xFF) << 8 | idxBuffer.get() & 0xFF;
			int len = (idxBuffer.get() & 0xFF) << 16 | (idxBuffer.get() & 0xFF) << 8 | idxBuffer.get() & 0xFF;
			int offsetX = idxBuffer.getShort() & 0xFF;
			int offsetY = idxBuffer.getShort() & 0xFF;
			ByteBuffer datBuffer = ByteBuffer.allocate(len);
			dat.position(pos);
			dat.read(datBuffer);
			datBuffer.flip();
			try (InputStream input = new ByteArrayInputStream(datBuffer.array())) {
				BufferedImage image = ImageIO.read(input);
				if (image == null) {
					return null;
				}
				if (image.getType() != BufferedImage.TYPE_INT_ARGB) {
					image = convert(image, BufferedImage.TYPE_INT_ARGB);
				}
				int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
				Sprite sprite = new Sprite(image.getWidth(), image.getHeight(), offsetX, offsetY, pixels);
				if (id == 348 || id == 397) {
					data.put(id, datBuffer.array());
				}
				map.put(id, sprite);
				return sprite;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] getData(int id) {
		byte[] bytes = data.get(id);
		if (bytes != null) {
			return bytes;
		}
		get(id);
		return data.get(id);
	}

	BufferedImage convert(BufferedImage bimage, int type) {
		BufferedImage converted = new BufferedImage(bimage.getWidth(), bimage.getHeight(), type);
		converted.getGraphics().drawImage(bimage, 0, 0, null);
		return converted;
	}

	public void clear() {
		data.clear();
		map.clear();
	}

	@Override
	public void close() throws IOException {
		clear();
		data = null;
		map = null;
		dat.close();
		idx.close();
	}

}