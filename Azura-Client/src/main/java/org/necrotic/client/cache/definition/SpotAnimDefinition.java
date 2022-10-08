package org.necrotic.client.cache.definition;

import org.necrotic.client.List;
import org.necrotic.client.cache.Archive;
import org.necrotic.client.io.ByteBuffer;
import org.necrotic.client.world.Model;

import java.util.Objects;

public final class SpotAnimDefinition {

	public static List list = new List(30);
	public static SpotAnimDefinition[] cache;

	public static void load(Archive archive) {
		ByteBuffer stream = new ByteBuffer(archive.get("spotanim.dat"));
		int length = stream.getUnsignedShort();
		System.out.println("length: " + length);
		if (cache == null)
			cache = new SpotAnimDefinition[length + 1000];
		for (int j = 0; j < length + 1000; j++) {
			if (cache[j] == null)
				cache[j] = new SpotAnimDefinition();
			cache[j].id = j;
			if (j < length)
			cache[j].readValues(stream);
			switch (j) {
			case 2959:
				cache[2959].modelId = cache[2114].modelId;
				cache[2959].animationId = cache[2114].animationId;
				cache[2959].animation = cache[2114].animation;
				cache[2959].originalColours = new int[] {127, 49874, -1, -1, -1, -1};
				cache[2959].destColours = new int[] {0, 100, -1, -1, -1, -1};
				//cache[2959].modelId = cache[2114].modelId;
				break;
			}
		}
		custom();
	}

	private void copy(int toCopy) {
		SpotAnimDefinition newCopy = new SpotAnimDefinition();
		cache[id].modelId = cache[toCopy].modelId;
		cache[id].animationId = cache[toCopy].animationId;
		cache[id].animation = cache[toCopy].animation;
	}

		private static void custom() {
		cache[2274].modelId = cache[2281].modelId;
		cache[2274].animationId = cache[2281].animationId;
		cache[2274].rotation = 90;
		cache[2274].animation = cache[2281].animation;

		cache[78].offsetX = 30;



			cache[3000].copy(1627);
			//cache[3000].animationId =  cache[1627].animationId;
			//cache[3000].animation =  cache[1627].animation;
			cache[3000].originalColours = new int[]{
					65432
					,5016
					,5047
					,4023
					,1975
					,11200
					,9152
					,8128
					,7104
					,6080
					,5056
					,4032
					,3008
			};
			cache[3000].destColours = new int[]{
					42968
					,42968
					,42968
					,42968
					,40933
					,42968
					,40933
					,40933
					,40933
					,40933
					,42968
					,42968
					,42968
			};


			cache[3001].copy(1950);
			cache[3001].colorChange = new double[]{0,0,0};


	}

	public double[] colorChange = null;
	public Animation animation;
	private int id;
	public int modelId;
	public int animationId;
	public int sizeXY;
	public int sizeZ;
	public int rotation;
	public int shadow;
	public int lightness;
	private int[] originalColours;
	private  int[] destColours;

	public int offsetX;
	public int offsetY;
	public int offsetZ;

	private SpotAnimDefinition() {
		animationId = -1;
		originalColours = new int[6];
		destColours = new int[6];
		sizeXY = 128;
		sizeZ = 128;
		offsetX = 0;
		offsetY = 0;
		offsetZ = 0;
		colorChange = null;
	}

	public Model getModel() {
		Model model = (Model) list.insertFromCache(id);

		if (model != null) {
			return model;
		}

		model = Model.fetchModel(modelId);

		if (model == null) {
			return null;
		}

		for (int i = 0; i < 6; i++) {
			if (originalColours[0] != 0) {
				model.method476(originalColours[i], destColours[i]);
			}
		}


		if (offsetX != 0 || offsetY != 0 || offsetZ != 0) {
			model.translate(offsetX, offsetY, offsetZ);
		}

		if (colorChange != null)
			Objects.requireNonNull(model).tint(colorChange);


		list.removeFromCache(model, id);
		return model;
	}

	private void readValues(ByteBuffer stream) {
        do {
            int i = stream.getUnsignedByte();
            if (i == 0)
                return;
            if (i == 1) {
                modelId = stream.getUnsignedShort();
            }
            else if (i == 2) {
                animationId = stream.getUnsignedShort();
                if (Animation.cache != null)
                	animation = Animation.cache[animationId];
            } else if (i == 4)
                sizeXY = stream.getUnsignedShort();
            else if (i == 5)
                sizeZ = stream.getUnsignedShort();
            else if (i == 6)
                rotation = stream.getUnsignedShort();
            else if (i == 7)
                shadow = stream.getUnsignedByte();
            else if (i == 8)
                lightness = stream.getUnsignedByte();
            else if (i == 40) {
                int j = stream.getUnsignedByte();
                for (int k = 0; k < j; k++) {
                    originalColours[k] = stream.getUnsignedShort();
                    destColours[k] = stream.getUnsignedShort();
                }
            } else
                System.out.println("Error unrecognised spotanim config code: "
                        + i);
        } while (true);
    }
}