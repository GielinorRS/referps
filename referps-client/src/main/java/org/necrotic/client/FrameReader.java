package org.necrotic.client;

import org.necrotic.client.io.ByteBuffer;

import java.util.HashMap;

public final class FrameReader {

	private static final int[] ai = new int[500];
	private static final int[] ai1 = new int[500];
	private static final int[] ai2 = new int[500];
	private static final int[] ai3 = new int[500];

	public static void load(int folder, byte[] data) {
		try {
			ByteBuffer buffer = new ByteBuffer(data);
			SkinList skinList = new SkinList(buffer);
			int k1 = buffer.getUnsignedShort();
			for (int l1 = 0; l1 < k1; l1++) {
				int file = buffer.getUnsignedShort();
				FrameReader frame = new FrameReader();
				frame.skinList = skinList;
				int j2 = buffer.getUnsignedByte();
				int l2 = 0;
				int k2 = -1;
				for (int i3 = 0; i3 < j2; i3++) {
					int j3 = buffer.getUnsignedByte();
					if (j3 > 0) {
						if (skinList.opcodes[i3] != 0) {
							for (int l3 = i3 - 1; l3 > k2; l3--) {
								if (skinList.opcodes[l3] != 0) {
									continue;
								}
								ai[l2] = l3;
								ai1[l2] = 0;
								ai2[l2] = 0;
								ai3[l2] = 0;
								l2++;
								break;
							}
						}
						ai[l2] = i3;
						short c = 0;
						if (skinList.opcodes[i3] == 3) {
							c = (short) 128;
						}
						if ((j3 & 1) != 0) {
							ai1[l2] = (short) buffer.getShort2();
						} else {
							ai1[l2] = c;
						}
						if ((j3 & 2) != 0) {
							ai2[l2] = buffer.getShort2();
						} else {
							ai2[l2] = c;
						}
						if ((j3 & 4) != 0) {
							ai3[l2] = buffer.getShort2();
						} else {
							ai3[l2] = c;
						}
						k2 = i3;
						l2++;
					}
				}
				frame.stepCount = l2;
				frame.opcodeLinkTable = new int[l2];
				frame.xOffset = new int[l2];
				frame.yOffset = new int[l2];
				frame.zOffset = new int[l2];
				for (int k3 = 0; k3 < l2; k3++) {
					frame.opcodeLinkTable[k3] = ai[k3];
					frame.xOffset[k3] = ai1[k3];
					frame.yOffset[k3] = ai2[k3];
					frame.zOffset[k3] = ai3[k3];
				}
				mapRS2.put((folder << 16) + file, frame);
			}
		} catch (Exception exception) {
			//exception.printStackTrace();
		}
	}

	public static void nullify() {
		mapRS2.clear();
		mapRS2 = null;
	}

	public static FrameReader forId(int i) {
		try {
			final FrameReader frames = mapRS2.get(i);
			if (frames != null) {
				return frames;
			}
			Client.instance.onDemandFetcher.requestFileData(1, i >> 16);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isNullFrame(int frame) {
		return frame == -1;
	}

	private FrameReader() {
	}

	public static HashMap<Integer, FrameReader> mapRS2 = new HashMap<>();

	public int displayLength;
	public SkinList skinList;
	public int stepCount;
	public int[] opcodeLinkTable;
	public int[] xOffset;
	public int[] yOffset;
	public int[] zOffset;
}
