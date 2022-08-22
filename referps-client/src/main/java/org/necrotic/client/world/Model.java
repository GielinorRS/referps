package org.necrotic.client.world;

import org.necrotic.Configuration;
import org.necrotic.client.*;
import org.necrotic.client.cache.definition.FileOperations;
import org.necrotic.client.cache.ondemand.OnDemandFetcherParent;
import org.necrotic.client.graphics.DrawingArea;
import org.necrotic.client.io.ByteBuffer;
import org.necrotic.client.renderable.Animable;

import java.io.File;
import java.util.HashMap;

public class Model extends Animable {

	private boolean scaledVertices;
	private int offX = 0;
	private int offY = 0;
	private int offZ = 0;
	private int lastRenderedRotation = 0;
	private int[] verticesParticle;
	private static final int POLYGON_COUNT = 30000;
	public static boolean objectExists;
	private static boolean hasAnEdgeToRestrict[] = new boolean[POLYGON_COUNT];
	private static boolean outOfReach[] = new boolean[POLYGON_COUNT];
	public static Model aModel_1621 = new Model(true);
	private static int anInt1681;
	private static int anInt1682;
	private static int anInt1683;
	public static int currentCursorX;
	public static int currentCursorY;
	public static int objectsRendered;
	private static int anIntArray1622[] = new int[POLYGON_COUNT];
	private static int anIntArray1623[] = new int[POLYGON_COUNT];
	private static int anIntArray1624[] = new int[POLYGON_COUNT];
	private static int anIntArray1625[] = new int[POLYGON_COUNT];
	private static int anIntArray1665[] = new int[POLYGON_COUNT];
	private static int anIntArray1666[] = new int[POLYGON_COUNT];
	private static int anIntArray1667[] = new int[POLYGON_COUNT];
	private static int anIntArray1668[] = new int[POLYGON_COUNT];
	private static int anIntArray1669[] = new int[POLYGON_COUNT];
	private static int anIntArray1670[] = new int[POLYGON_COUNT];
	private static int vertexPerspectiveDepth[] = new int[POLYGON_COUNT];
	private static int depthListIndices[] = new int[1800];
	private static int anIntArray1673[] = new int[12];
	private static int anIntArray1675[] = new int[2000];
	private static int anIntArray1676[] = new int[2000];
	private static int anIntArray1677[] = new int[12];
	private static int anIntArray1678[] = new int[10];
	private static int anIntArray1679[] = new int[10];
	private static int anIntArray1680[] = new int[10];
	public static int mapObjIds[] = new int[1000];
	public static int anIntArray1688[] = new int[1000];
	private static int faceLists[][] = new int[8000][512];
	private static int anIntArrayArray1674[][] = new int[12][10000];
	private static OnDemandFetcherParent aOnDemandFetcherParent_1662;

	public static void method459(int i, OnDemandFetcherParent onDemandFetcherParent) {
		aOnDemandFetcherParent_1662 = onDemandFetcherParent;
	}

	public static HashMap<Integer, byte[]> mapRS2 = new HashMap<>();

	public static final File raw = new File(Signlink.getCacheDirectory() + "data" + File.separator + "raw" + File.separator);

	public static byte[] getData(final int model) {
		if (raw.exists()) {
			final String rawDat = raw.getAbsolutePath() + File.separator + model + ".dat";
			if (new File(rawDat).exists()) {
				load(FileOperations.readFile(rawDat), model);
			}
		}
		return mapRS2.get(model);
	}

	public static void load(byte[] data, int id) {
		if (data == null) {
			return;
		}
		mapRS2.put(id, data);
	}

	public static Model get(int model) {
		final byte[] data = getData(model);
		if (data != null) {
			return new Model(data, model);
		}
		aOnDemandFetcherParent_1662.get(model);
		return null;
	}

	public static boolean method463(int model) {
		final byte[] data = getData(model);
		if (data != null) {
			return true;
		}
		aOnDemandFetcherParent_1662.get(model);
		return false;
	}

	private static final int method481(int i, int j, int k) {
		if (i == 65535) {
			return 0;
		}

		if ((k & 2) == 2) {
			if (j < 0) {
				j = 0;
			} else if (j > 127) {
				j = 127;
			}

			j = 127 - j;
			return j;
		}

		j = j * (i & 0x7f) >> 7;

		if (j < 2) {
			j = 2;
		} else if (j > 126) {
			j = 126;
		}

		return (i & 0xff80) + j;
	}

	public static void nullify() {
		mapRS2.clear();
		mapRS2 = null;
		hasAnEdgeToRestrict = null;
		outOfReach = null;
		anIntArray1666 = null;
		anIntArray1667 = null;
		anIntArray1668 = null;
		anIntArray1669 = null;
		anIntArray1670 = null;
		vertexPerspectiveDepth = null;
		depthListIndices = null;
		faceLists = null;
		anIntArray1673 = null;
		anIntArrayArray1674 = null;
		anIntArray1675 = null;
		anIntArray1676 = null;
		anIntArray1677 = null;
	}

	private boolean aBoolean1618;
	public boolean aBoolean1659;
	public Class33 aClass33Array1660[];
	public int numberOfVerticeCoordinates;
	public int anInt1630;
	private int anInt1641;
	private int anInt1642;
	public int anInt1646;
	public int anInt1647;
	public int anInt1648;
	public int anInt1649;
	public int boundingPlaneRadius;
	public int anInt1651;
	private int diagonal3D;
	private int anInt1653;
	public int anInt1654;
	public int verticesXCoordinate[];
	public int verticesYCoordinate[];
	public int verticesZCoordinate[];
	public int faces_a[];
	public int faces_b[];
	public int faces_c[];
	private int anIntArray1634[];
	private int anIntArray1635[];
	private int anIntArray1636[];
	public int face_render_type[];
	private int face_render_priorities[];
	private int faces_alpha[];
	public int anIntArray1640[];
	private int texture_face_x[];
	private int texture_face_y[];
	private int texture_face_z[];
	private int anIntArray1655[];
	private int anIntArray1656[];
	public int vertexSkin[][];
	public int triangleSkin[][];

	private Model(boolean flag) {
		aBoolean1618 = true;
		aBoolean1659 = false;

		if (!flag) {
			aBoolean1618 = !aBoolean1618;
		}
	}

	public Model(boolean flag, boolean flag1, boolean flag2, Model model) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
		anInt1630 = model.anInt1630;
		anInt1642 = model.anInt1642;

		if (flag2) {
			verticesParticle = model.verticesParticle;
			verticesXCoordinate = model.verticesXCoordinate;
			verticesYCoordinate = model.verticesYCoordinate;
			verticesZCoordinate = model.verticesZCoordinate;
		} else {
			verticesParticle = new int[numberOfVerticeCoordinates];
			verticesXCoordinate = new int[numberOfVerticeCoordinates];
			verticesYCoordinate = new int[numberOfVerticeCoordinates];
			verticesZCoordinate = new int[numberOfVerticeCoordinates];

			for (int j = 0; j < numberOfVerticeCoordinates; j++) {
				verticesParticle[j] = model.verticesParticle[j];
				verticesXCoordinate[j] = model.verticesXCoordinate[j];
				verticesYCoordinate[j] = model.verticesYCoordinate[j];
				verticesZCoordinate[j] = model.verticesZCoordinate[j];
			}
		}

		if (flag) {
			anIntArray1640 = model.anIntArray1640;
		} else {
			anIntArray1640 = new int[anInt1630];

			for (int k = 0; k < anInt1630; k++) {
				anIntArray1640[k] = model.anIntArray1640[k];
			}
		}

		if (flag1) {
			faces_alpha = model.faces_alpha;
		} else {
			faces_alpha = new int[anInt1630];

			if (model.faces_alpha == null) {
				for (int l = 0; l < anInt1630; l++) {
					faces_alpha[l] = 0;
				}
			} else {
				for (int i1 = 0; i1 < anInt1630; i1++) {
					faces_alpha[i1] = model.faces_alpha[i1];
				}
			}
		}

		anIntArray1655 = model.anIntArray1655;
		anIntArray1656 = model.anIntArray1656;
		face_render_type = model.face_render_type;
		faces_a = model.faces_a;
		faces_b = model.faces_b;
		faces_c = model.faces_c;
		face_render_priorities = model.face_render_priorities;
		anInt1641 = model.anInt1641;
		texture_face_x = model.texture_face_x;
		texture_face_y = model.texture_face_y;
		texture_face_z = model.texture_face_z;
		scaledVertices = model.scaledVertices;
	}

	public Model(boolean flag, boolean flag1, Model model) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
		anInt1630 = model.anInt1630;
		anInt1642 = model.anInt1642;

		if (flag) {
			verticesYCoordinate = new int[numberOfVerticeCoordinates];

			for (int j = 0; j < numberOfVerticeCoordinates; j++) {
				verticesYCoordinate[j] = model.verticesYCoordinate[j];
			}
		} else {
			verticesYCoordinate = model.verticesYCoordinate;
		}

		if (flag1) {
			anIntArray1634 = new int[anInt1630];
			anIntArray1635 = new int[anInt1630];
			anIntArray1636 = new int[anInt1630];

			for (int k = 0; k < anInt1630; k++) {
				anIntArray1634[k] = model.anIntArray1634[k];
				anIntArray1635[k] = model.anIntArray1635[k];
				anIntArray1636[k] = model.anIntArray1636[k];
			}

			face_render_type = new int[anInt1630];

			if (model.face_render_type == null) {
				for (int l = 0; l < anInt1630; l++) {
					face_render_type[l] = 0;
				}
			} else {
				for (int i1 = 0; i1 < anInt1630; i1++) {
					face_render_type[i1] = model.face_render_type[i1];
				}
			}

			super.aClass33Array1425 = new Class33[numberOfVerticeCoordinates];

			for (int j1 = 0; j1 < numberOfVerticeCoordinates; j1++) {
				Class33 class33 = super.aClass33Array1425[j1] = new Class33();
				Class33 class33_1 = model.aClass33Array1425[j1];
				class33.anInt602 = class33_1.anInt602;
				class33.anInt603 = class33_1.anInt603;
				class33.anInt604 = class33_1.anInt604;
				class33.anInt605 = class33_1.anInt605;
			}

			aClass33Array1660 = model.aClass33Array1660;
		} else {
			anIntArray1634 = model.anIntArray1634;
			anIntArray1635 = model.anIntArray1635;
			anIntArray1636 = model.anIntArray1636;
			face_render_type = model.face_render_type;
		}

		verticesParticle = model.verticesParticle;
		verticesXCoordinate = model.verticesXCoordinate;
		verticesZCoordinate = model.verticesZCoordinate;
		anIntArray1640 = model.anIntArray1640;
		faces_alpha = model.faces_alpha;
		face_render_priorities = model.face_render_priorities;
		anInt1641 = model.anInt1641;
		faces_a = model.faces_a;
		faces_b = model.faces_b;
		faces_c = model.faces_c;
		texture_face_x = model.texture_face_x;
		texture_face_y = model.texture_face_y;
		texture_face_z = model.texture_face_z;
		super.modelHeight = model.modelHeight;
		boundingPlaneRadius = model.boundingPlaneRadius;
		anInt1653 = model.anInt1653;
		diagonal3D = model.diagonal3D;
		anInt1646 = model.anInt1646;
		anInt1648 = model.anInt1648;
		anInt1649 = model.anInt1649;
		anInt1647 = model.anInt1647;
		scaledVertices = model.scaledVertices;
	}
	//come back here
	private Model(byte[] data, int modelId) {

		if (data[data.length - 1] == -1 && data[data.length - 2] == -1) {
			read622Model(data, modelId);
		} else {
			readOldModel(data);
		}
		int[] priorityFix = new int[]{64448, 100_009};
		for (int z : priorityFix) {
			if (modelId == z) {
				if (face_render_priorities == null) {
					face_render_priorities = new int[numberOfVerticeCoordinates];
				}
				for (z = 0; z < this.face_render_priorities.length; z++) {
					this.face_render_priorities[z] = 10;
				}
			}
		}
		/*int[][] attachments = ParticleAttachment.getAttachments(modelId);

		if (attachments != null) {
			for (int[] attach : attachments) {
				if (attach[0] == -1) {
					for (int i : faces_a) {
						verticesParticle[i] = attach[1] + 1;
					}
				} else if (attach[0] == -2) {
					for (int i : faces_b) {
						verticesParticle[i] = attach[1] + 1;
					}
				} else if (attach[0] == -3) {
					for (int i : faces_c) {
						verticesParticle[i] = attach[1] + 1;
					}
				} else if (attach[0] == -4) {
					for (int i : faces_a) {
						verticesParticle[i] = attach[1] + 1;
					}
					for (int i : faces_b) {
						verticesParticle[i] = attach[1] + 1;
					}
					for (int i : faces_c) {
						verticesParticle[i] = attach[1] + 1;
					}
				} else {
					verticesParticle[attach[0]] = attach[1] + 1;
				}*/
			//}
		//}

	}

	public void recolour(int i, int j) {
		for (int k = 0; k < numberOfTriangleFaces; k++) {
			if (anIntArray1640[k] == i) {
				anIntArray1640[k] = j;
			}
		}
	}

	public Model(int i, Model amodel[]) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		boolean flag = false;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		numberOfVerticeCoordinates = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		anInt1641 = -1;

		for (int k = 0; k < i; k++) {
			Model model = amodel[k];

			if (model != null) {
				numberOfVerticeCoordinates += model.numberOfVerticeCoordinates;
				anInt1630 += model.anInt1630;
				anInt1642 += model.anInt1642;
				flag |= model.face_render_type != null;

				if (model.face_render_priorities != null) {
					flag1 = true;
				} else {
					if (anInt1641 == -1) {
						anInt1641 = model.anInt1641;
					}

					if (anInt1641 != model.anInt1641) {
						flag1 = true;
					}
				}

				flag2 |= model.faces_alpha != null;
				flag3 |= model.anIntArray1656 != null;
				scaledVertices |= model.scaledVertices;
			}
		}

		verticesParticle = new int[numberOfVerticeCoordinates];
		verticesXCoordinate = new int[numberOfVerticeCoordinates];
		verticesYCoordinate = new int[numberOfVerticeCoordinates];
		verticesZCoordinate = new int[numberOfVerticeCoordinates];
		anIntArray1655 = new int[numberOfVerticeCoordinates];
		faces_a = new int[anInt1630];
		faces_b = new int[anInt1630];
		faces_c = new int[anInt1630];
		texture_face_x = new int[anInt1642];
		texture_face_y = new int[anInt1642];
		texture_face_z = new int[anInt1642];

		if (flag) {
			face_render_type = new int[anInt1630];
		}

		if (flag1) {
			face_render_priorities = new int[anInt1630];
		}

		if (flag2) {
			faces_alpha = new int[anInt1630];
		}

		if (flag3) {
			anIntArray1656 = new int[anInt1630];
		}

		anIntArray1640 = new int[anInt1630];
		numberOfVerticeCoordinates = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		int l = 0;

		for (int i1 = 0; i1 < i; i1++) {
			Model model_1 = amodel[i1];

			if (model_1 != null) {
				for (int j1 = 0; j1 < model_1.anInt1630; j1++) {
					if (flag) {
						if (model_1.face_render_type == null) {
							face_render_type[anInt1630] = 0;
						} else {
							int k1 = model_1.face_render_type[j1];

							if ((k1 & 2) == 2) {
								k1 += l << 2;
							}

							face_render_type[anInt1630] = k1;
						}
					}

					if (flag1) {
						if (model_1.face_render_priorities == null) {
							face_render_priorities[anInt1630] = model_1.anInt1641;
						} else {
							face_render_priorities[anInt1630] = model_1.face_render_priorities[j1];
						}
					}

					if (flag2) {
						if (model_1.faces_alpha == null) {
							faces_alpha[anInt1630] = 0;
						} else {
							faces_alpha[anInt1630] = model_1.faces_alpha[j1];
						}
					}

					if (flag3 && model_1.anIntArray1656 != null) {
						anIntArray1656[anInt1630] = model_1.anIntArray1656[j1];
					}

					anIntArray1640[anInt1630] = model_1.anIntArray1640[j1];
					faces_a[anInt1630] = method465(model_1, model_1.faces_a[j1]);
					faces_b[anInt1630] = method465(model_1, model_1.faces_b[j1]);
					faces_c[anInt1630] = method465(model_1, model_1.faces_c[j1]);
					anInt1630++;
				}

				for (int l1 = 0; l1 < model_1.anInt1642; l1++) {
					texture_face_x[anInt1642] = method465(model_1, model_1.texture_face_x[l1]);
					texture_face_y[anInt1642] = method465(model_1, model_1.texture_face_y[l1]);
					texture_face_z[anInt1642] = method465(model_1, model_1.texture_face_z[l1]);
					anInt1642++;
				}

				l += model_1.anInt1642;
			}
		}
	}

	public Model(Model amodel[]) {
		int i = 2;
		aBoolean1618 = true;
		aBoolean1659 = false;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		numberOfVerticeCoordinates = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		anInt1641 = -1;

		for (int k = 0; k < i; k++) {
			Model model = amodel[k];
			if (model != null) {
				numberOfVerticeCoordinates += model.numberOfVerticeCoordinates;
				anInt1630 += model.anInt1630;
				anInt1642 += model.anInt1642;
				flag1 |= model.face_render_type != null;
				if (model.face_render_priorities != null) {
					flag2 = true;
				} else {
					if (anInt1641 == -1) {
						anInt1641 = model.anInt1641;
					}
					if (anInt1641 != model.anInt1641) {
						flag2 = true;
					}
				}
				flag3 |= model.faces_alpha != null;
				flag4 |= model.anIntArray1640 != null;
				scaledVertices |= model.scaledVertices;
			}
		}

		verticesParticle = new int[numberOfVerticeCoordinates];
		verticesXCoordinate = new int[numberOfVerticeCoordinates];
		verticesYCoordinate = new int[numberOfVerticeCoordinates];
		verticesZCoordinate = new int[numberOfVerticeCoordinates];
		faces_a = new int[anInt1630];
		faces_b = new int[anInt1630];
		faces_c = new int[anInt1630];
		anIntArray1634 = new int[anInt1630];
		anIntArray1635 = new int[anInt1630];
		anIntArray1636 = new int[anInt1630];
		texture_face_x = new int[anInt1642];
		texture_face_y = new int[anInt1642];
		texture_face_z = new int[anInt1642];
		if (flag1) {
			face_render_type = new int[anInt1630];
		}
		if (flag2) {
			face_render_priorities = new int[anInt1630];
		}
		if (flag3) {
			faces_alpha = new int[anInt1630];
		}
		if (flag4) {
			anIntArray1640 = new int[anInt1630];
		}
		numberOfVerticeCoordinates = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		int i1 = 0;
		for (int j1 = 0; j1 < i; j1++) {
			Model model_1 = amodel[j1];
			if (model_1 != null) {
				int k1 = numberOfVerticeCoordinates;
				for (int l1 = 0; l1 < model_1.numberOfVerticeCoordinates; l1++) {
					int x = model_1.verticesXCoordinate[l1];
					int y = model_1.verticesYCoordinate[l1];
					int z = model_1.verticesZCoordinate[l1];
					int p = model_1.verticesParticle[l1];
					if (scaledVertices && !model_1.scaledVertices) {
						x <<= 2;
						y <<= 2;
						z <<= 2;
					}
					verticesXCoordinate[numberOfVerticeCoordinates] = x;
					verticesYCoordinate[numberOfVerticeCoordinates] = y;
					verticesZCoordinate[numberOfVerticeCoordinates] = z;
					verticesParticle[numberOfVerticeCoordinates] = p;
					numberOfVerticeCoordinates++;
				}

				for (int i2 = 0; i2 < model_1.anInt1630; i2++) {
					faces_a[anInt1630] = model_1.faces_a[i2] + k1;
					faces_b[anInt1630] = model_1.faces_b[i2] + k1;
					faces_c[anInt1630] = model_1.faces_c[i2] + k1;
					anIntArray1634[anInt1630] = model_1.anIntArray1634[i2];
					anIntArray1635[anInt1630] = model_1.anIntArray1635[i2];
					anIntArray1636[anInt1630] = model_1.anIntArray1636[i2];
					if (flag1) {
						if (model_1.face_render_type == null) {
							face_render_type[anInt1630] = 0;
						} else {
							int j2 = model_1.face_render_type[i2];
							if ((j2 & 2) == 2) {
								j2 += i1 << 2;
							}
							face_render_type[anInt1630] = j2;
						}
					}
					if (flag2) {
						if (model_1.face_render_priorities == null) {
							face_render_priorities[anInt1630] = model_1.anInt1641;
						} else {
							face_render_priorities[anInt1630] = model_1.face_render_priorities[i2];
						}
					}
					if (flag3) {
						if (model_1.faces_alpha == null) {
							faces_alpha[anInt1630] = 0;
						} else {
							faces_alpha[anInt1630] = model_1.faces_alpha[i2];
						}
					}
					if (flag4 && model_1.anIntArray1640 != null) {
						anIntArray1640[anInt1630] = model_1.anIntArray1640[i2];
					}

					anInt1630++;
				}

				for (int k2 = 0; k2 < model_1.anInt1642; k2++) {
					texture_face_x[anInt1642] = model_1.texture_face_x[k2] + k1;
					texture_face_y[anInt1642] = model_1.texture_face_y[k2] + k1;
					texture_face_z[anInt1642] = model_1.texture_face_z[k2] + k1;
					anInt1642++;
				}

				i1 += model_1.anInt1642;
			}
		}

		method466();
	}

	public void method464(Model model, boolean flag) {
		numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
		anInt1630 = model.anInt1630;
		anInt1642 = model.anInt1642;
		if (anIntArray1622.length < numberOfVerticeCoordinates) {
			anIntArray1622 = new int[numberOfVerticeCoordinates + 10000];
			anIntArray1623 = new int[numberOfVerticeCoordinates + 10000];
			anIntArray1624 = new int[numberOfVerticeCoordinates + 10000];
		}
		verticesParticle = new int[numberOfVerticeCoordinates];
		verticesXCoordinate = anIntArray1622;
		verticesYCoordinate = anIntArray1623;
		verticesZCoordinate = anIntArray1624;
		for (int k = 0; k < numberOfVerticeCoordinates; k++) {
			verticesParticle[k] = model.verticesParticle[k];
			verticesXCoordinate[k] = model.verticesXCoordinate[k];
			verticesYCoordinate[k] = model.verticesYCoordinate[k];
			verticesZCoordinate[k] = model.verticesZCoordinate[k];
		}

		if (flag) {
			faces_alpha = model.faces_alpha;
		} else {
			if (anIntArray1625.length < anInt1630) {
				anIntArray1625 = new int[anInt1630 + 100];
			}
			faces_alpha = anIntArray1625;
			if (model.faces_alpha == null) {
				for (int l = 0; l < anInt1630; l++) {
					faces_alpha[l] = 0;
				}

			} else {
				for (int i1 = 0; i1 < anInt1630; i1++) {
					faces_alpha[i1] = model.faces_alpha[i1];
				}

			}
		}
		face_render_type = model.face_render_type;
		anIntArray1640 = model.anIntArray1640;
		face_render_priorities = model.face_render_priorities;
		anInt1641 = model.anInt1641;
		triangleSkin = model.triangleSkin;
		vertexSkin = model.vertexSkin;
		faces_a = model.faces_a;
		faces_b = model.faces_b;
		faces_c = model.faces_c;
		anIntArray1634 = model.anIntArray1634;
		anIntArray1635 = model.anIntArray1635;
		anIntArray1636 = model.anIntArray1636;
		texture_face_x = model.texture_face_x;
		texture_face_y = model.texture_face_y;
		texture_face_z = model.texture_face_z;
		scaledVertices = model.scaledVertices;
	}

	private final int method465(Model model, int i) {
		int j = -1;
		int p = model.verticesParticle[i];
		int k = model.verticesXCoordinate[i];
		int l = model.verticesYCoordinate[i];
		int i1 = model.verticesZCoordinate[i];
		if (scaledVertices && !model.scaledVertices) {
			k <<= 2;
			l <<= 2;
			i1 <<= 2;
		}
		for (int j1 = 0; j1 < numberOfVerticeCoordinates; j1++) {
			if (k != verticesXCoordinate[j1] || l != verticesYCoordinate[j1] || i1 != verticesZCoordinate[j1]) {
				continue;
			}
			j = j1;
			break;
		}

		if (j == -1) {
			verticesParticle[numberOfVerticeCoordinates] = p;
			verticesXCoordinate[numberOfVerticeCoordinates] = k;
			verticesYCoordinate[numberOfVerticeCoordinates] = l;
			verticesZCoordinate[numberOfVerticeCoordinates] = i1;
			if (model.anIntArray1655 != null) {
				anIntArray1655[numberOfVerticeCoordinates] = model.anIntArray1655[i];
			}
			j = numberOfVerticeCoordinates++;
		}
		return j;
	}

	public void method466() {
		super.modelHeight = 0;
		boundingPlaneRadius = 0;
		anInt1651 = 0;
		for (int i = 0; i < numberOfVerticeCoordinates; i++) {
			int j = verticesXCoordinate[i];
			int k = verticesYCoordinate[i];
			int l = verticesZCoordinate[i];
			if (scaledVertices) {
				j >>= 2;
				k >>= 2;
				l >>= 2;
			}
			if (-k > super.modelHeight) {
				super.modelHeight = -k;
			}
			if (k > anInt1651) {
				anInt1651 = k;
			}
			int i1 = j * j + l * l;
			if (i1 > boundingPlaneRadius) {
				boundingPlaneRadius = i1;
			}
		}
		boundingPlaneRadius = (int) (Math.sqrt(boundingPlaneRadius) + 0.98999999999999999D);
		anInt1653 = (int) (Math.sqrt(boundingPlaneRadius * boundingPlaneRadius + super.modelHeight * super.modelHeight) + 0.98999999999999999D);
		diagonal3D = anInt1653 + (int) (Math.sqrt(boundingPlaneRadius * boundingPlaneRadius + anInt1651 * anInt1651) + 0.98999999999999999D);
	}

	public void method467() {
		super.modelHeight = 0;
		anInt1651 = 0;
		for (int i = 0; i < numberOfVerticeCoordinates; i++) {
			int j = verticesYCoordinate[i];
			if (-j > super.modelHeight) {
				super.modelHeight = -j;
			}
			if (j > anInt1651) {
				anInt1651 = j;
			}
		}

		anInt1653 = (int) (Math.sqrt(boundingPlaneRadius * boundingPlaneRadius + super.modelHeight * super.modelHeight) + 0.98999999999999999D);
		diagonal3D = anInt1653 + (int) (Math.sqrt(boundingPlaneRadius * boundingPlaneRadius + anInt1651 * anInt1651) + 0.98999999999999999D);
	}

	private void method468(int i) {
		super.modelHeight = 0;
		boundingPlaneRadius = 0;
		anInt1651 = 0;
		anInt1646 = 0xf423f;
		anInt1647 = 0xfff0bdc1;
		anInt1648 = 0xfffe7961;
		anInt1649 = 0x1869f;
		for (int j = 0; j < numberOfVerticeCoordinates; j++) {
			int k = verticesXCoordinate[j];
			int l = verticesYCoordinate[j];
			int i1 = verticesZCoordinate[j];
			if (scaledVertices) {
				k >>= 2;
				l >>= 2;
				i1 >>= 2;
			}
			if (k < anInt1646) {
				anInt1646 = k;
			}
			if (k > anInt1647) {
				anInt1647 = k;
			}
			if (i1 < anInt1649) {
				anInt1649 = i1;
			}
			if (i1 > anInt1648) {
				anInt1648 = i1;
			}
			if (-l > super.modelHeight) {
				super.modelHeight = -l;
			}
			if (l > anInt1651) {
				anInt1651 = l;
			}
			int j1 = k * k + i1 * i1;
			if (j1 > boundingPlaneRadius) {
				boundingPlaneRadius = j1;
			}
		}

		boundingPlaneRadius = (int) Math.sqrt(boundingPlaneRadius);
		anInt1653 = (int) Math.sqrt(boundingPlaneRadius * boundingPlaneRadius + super.modelHeight * super.modelHeight);
		if (i != 21073) {
			return;
		} else {
			diagonal3D = anInt1653 + (int) Math.sqrt(boundingPlaneRadius * boundingPlaneRadius + anInt1651 * anInt1651);
			return;
		}
	}

	public void createBones() {
		if (anIntArray1655 != null) {
			int ai[] = new int[256];
			int j = 0;
			for (int l = 0; l < numberOfVerticeCoordinates; l++) {
				int j1 = anIntArray1655[l];
				ai[j1]++;
				if (j1 > j) {
					j = j1;
				}
			}

			vertexSkin = new int[j + 1][];
			for (int k1 = 0; k1 <= j; k1++) {
				vertexSkin[k1] = new int[ai[k1]];
				ai[k1] = 0;
			}

			for (int j2 = 0; j2 < numberOfVerticeCoordinates; j2++) {
				int l2 = anIntArray1655[j2];
				vertexSkin[l2][ai[l2]++] = j2;
			}

			anIntArray1655 = null;
		}
		if (anIntArray1656 != null) {
			int ai1[] = new int[256];
			int k = 0;
			for (int i1 = 0; i1 < anInt1630; i1++) {
				int l1 = anIntArray1656[i1];
				ai1[l1]++;
				if (l1 > k) {
					k = l1;
				}
			}

			triangleSkin = new int[k + 1][];
			for (int i2 = 0; i2 <= k; i2++) {
				triangleSkin[i2] = new int[ai1[i2]];
				ai1[i2] = 0;
			}

			for (int k2 = 0; k2 < anInt1630; k2++) {
				int i3 = anIntArray1656[k2];
				triangleSkin[i3][ai1[i3]++] = k2;
			}

			anIntArray1656 = null;
		}
	}

	public void applyTransform(int i) {
		if (vertexSkin == null) {
			return;
		}
		if (i == -1) {
			return;
		}
		FrameReader class36 = FrameReader.forId(i);
		if (class36 == null) {
			return;
		}
		SkinList class18 = class36.skinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		for (int k = 0; k < class36.stepCount; k++) {
			int l = class36.opcodeLinkTable[k];
			method472(class18.opcodes[l], class18.skinList[l], class36.xOffset[k], class36.yOffset[k], class36.zOffset[k]);
		}

	}

	public void interpolateFrames(int firstFrame, int nextFrame, int end, int cycle) {
		if (!Configuration.TWEENING_ENABLED) {
			applyTransform(nextFrame);
			return;
		}
		try {
			if (vertexSkin != null && firstFrame != -1) {
				FrameReader currentAnimation = FrameReader.forId(firstFrame);
				if (currentAnimation == null) {
					applyTransform(nextFrame);
					return;
				}
				SkinList list1 = currentAnimation.skinList;
				anInt1681 = 0;
				anInt1682 = 0;
				anInt1683 = 0;
				FrameReader nextAnimation = null;
				SkinList list2 = null;
				if (nextFrame != -1) {
					nextAnimation = FrameReader.forId(nextFrame);
					if (nextAnimation.skinList != list1) {
						nextAnimation = null;
					}
					list2 = nextAnimation.skinList;
				}
				if (nextAnimation == null || list2 == null) {
					for (int i_263_ = 0; i_263_ < currentAnimation.stepCount; i_263_++) {
						int i_264_ = currentAnimation.opcodeLinkTable[i_263_];
						method472(list1.opcodes[i_264_], list1.skinList[i_264_], currentAnimation.xOffset[i_263_], currentAnimation.yOffset[i_263_], currentAnimation.zOffset[i_263_]);

					}
				} else {
					for (int i1 = 0; i1 < currentAnimation.stepCount; i1++) {
						int n1 = currentAnimation.opcodeLinkTable[i1];
						int opcode = list1.opcodes[n1];
						int[] skin = list1.skinList[n1];
						int x = currentAnimation.xOffset[i1];
						int y = currentAnimation.yOffset[i1];
						int z = currentAnimation.zOffset[i1];
						boolean found = false;
						for (int i2 = 0; i2 < nextAnimation.stepCount; i2++) {
							int n2 = nextAnimation.opcodeLinkTable[i2];
							if (list2.skinList[n2].equals(skin)) {
								if (opcode != 2) {
									x += (nextAnimation.xOffset[i2] - x) * cycle / end;
									y += (nextAnimation.yOffset[i2] - y) * cycle / end;
									z += (nextAnimation.zOffset[i2] - z) * cycle / end;
								} else {
									x &= 0xff;
									y &= 0xff;
									z &= 0xff;
									int dx = nextAnimation.xOffset[i2] - x & 0xff;
									int dy = nextAnimation.yOffset[i2] - y & 0xff;
									int dz = nextAnimation.zOffset[i2] - z & 0xff;
									if (dx >= 128) {
										dx -= 256;
									}
									if (dy >= 128) {
										dy -= 256;
									}
									if (dz >= 128) {
										dz -= 256;
									}
									x = x + dx * cycle / end & 0xff;
									y = y + dy * cycle / end & 0xff;
									z = z + dz * cycle / end & 0xff;
								}
								found = true;
								break;
							}
						}
						if (!found) {
							if (opcode != 3 && opcode != 2) {
								x = x * (end - cycle) / end;
								y = y * (end - cycle) / end;
								z = z * (end - cycle) / end;
							} else if (opcode == 3) {
								x = (x * (end - cycle) + (cycle << 7)) / end;
								y = (y * (end - cycle) + (cycle << 7)) / end;
								z = (z * (end - cycle) + (cycle << 7)) / end;
							} else {
								x &= 0xff;
								y &= 0xff;
								z &= 0xff;
								int dx = -x & 0xff;
								int dy = -y & 0xff;
								int dz = -z & 0xff;
								if (dx >= 128) {
									dx -= 256;
								}
								if (dy >= 128) {
									dy -= 256;
								}
								if (dz >= 128) {
									dz -= 256;
								}
								x = x + dx * cycle / end & 0xff;
								y = y + dy * cycle / end & 0xff;
								z = z + dz * cycle / end & 0xff;
							}
						}
						method472(opcode, skin, x, y, z);
					}
				}
			}

		} catch (Exception e) {
			applyTransform(firstFrame);
		}
	}

	public void method471(int ai[], int j, int k) {
		if (k == -1) {
			return;
		}
		if (ai == null || j == -1) {
			applyTransform(k);
			return;
		}
		FrameReader class36 = FrameReader.forId(k);
		if (class36 == null) {
			return;
		}
		FrameReader class36_1 = FrameReader.forId(j);
		if (class36_1 == null) {
			applyTransform(k);
			return;
		}
		SkinList class18 = class36.skinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		int l = 0;
		int i1 = ai[l++];
		for (int j1 = 0; j1 < class36.stepCount; j1++) {
			int k1;
			for (k1 = class36.opcodeLinkTable[j1]; k1 > i1; i1 = ai[l++]) {
				;
			}
			if (k1 != i1 || class18.opcodes[k1] == 0) {
				method472(class18.opcodes[k1], class18.skinList[k1], class36.xOffset[j1], class36.yOffset[j1], class36.zOffset[j1]);
			}
		}

		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		l = 0;
		i1 = ai[l++];
		for (int l1 = 0; l1 < class36_1.stepCount; l1++) {
			int i2;
			for (i2 = class36_1.opcodeLinkTable[l1]; i2 > i1; i1 = ai[l++]) {
				;
			}
			if (i2 == i1 || class18.opcodes[i2] == 0) {
				method472(class18.opcodes[i2], class18.skinList[i2], class36_1.xOffset[l1], class36_1.yOffset[l1], class36_1.zOffset[l1]);
			}
		}

	}

	private void method472(int i, int ai[], int j, int k, int l) {

		int i1 = ai.length;
		if (i == 0) {
			int j1 = 0;
			anInt1681 = 0;
			anInt1682 = 0;
			anInt1683 = 0;
			for (int k2 = 0; k2 < i1; k2++) {
				int l3 = ai[k2];
				if (l3 < vertexSkin.length) {
					int ai5[] = vertexSkin[l3];
					for (int j6 : ai5) {
						anInt1681 += verticesXCoordinate[j6] >> (scaledVertices ? 2 : 0);
						anInt1682 += verticesYCoordinate[j6] >> (scaledVertices ? 2 : 0);
						anInt1683 += verticesZCoordinate[j6] >> (scaledVertices ? 2 : 0);
						j1++;
					}

				}
			}

			if (j1 > 0) {
				anInt1681 = anInt1681 / j1 + j;
				anInt1682 = anInt1682 / j1 + k;
				anInt1683 = anInt1683 / j1 + l;
				return;
			} else {
				anInt1681 = j;
				anInt1682 = k;
				anInt1683 = l;
				return;
			}
		}
		if (i == 1) {
			for (int k1 = 0; k1 < i1; k1++) {
				int l2 = ai[k1];
				if (l2 < vertexSkin.length) {
					int ai1[] = vertexSkin[l2];
					for (int element : ai1) {
						int j5 = element;
						verticesXCoordinate[j5] += j << (scaledVertices ? 2 : 0);
						verticesYCoordinate[j5] += k << (scaledVertices ? 2 : 0);
						verticesZCoordinate[j5] += l << (scaledVertices ? 2 : 0);
					}

				}
			}

			return;
		}
		if (i == 2) {
			for (int l1 = 0; l1 < i1; l1++) {
				int i3 = ai[l1];
				if (i3 < vertexSkin.length) {
					int ai2[] = vertexSkin[i3];
					for (int element : ai2) {
						int k5 = element;
						verticesXCoordinate[k5] -= anInt1681 << (scaledVertices ? 2 : 0);
						verticesYCoordinate[k5] -= anInt1682 << (scaledVertices ? 2 : 0);
						verticesZCoordinate[k5] -= anInt1683 << (scaledVertices ? 2 : 0);
						int k6 = (j & 0xff) * 8;
						int l6 = (k & 0xff) * 8;
						int i7 = (l & 0xff) * 8;
						if (i7 != 0) {
							int j7 = Rasterizer.SINE[i7];
							int i8 = Rasterizer.COSINE[i7];
							int l8 = verticesYCoordinate[k5] * j7 + verticesXCoordinate[k5] * i8 >> 16;
							verticesYCoordinate[k5] = verticesYCoordinate[k5] * i8 - verticesXCoordinate[k5] * j7 >> 16;
							verticesXCoordinate[k5] = l8;
						}
						if (k6 != 0) {
							int k7 = Rasterizer.SINE[k6];
							int j8 = Rasterizer.COSINE[k6];
							int i9 = verticesYCoordinate[k5] * j8 - verticesZCoordinate[k5] * k7 >> 16;
							verticesZCoordinate[k5] = verticesYCoordinate[k5] * k7 + verticesZCoordinate[k5] * j8 >> 16;
							verticesYCoordinate[k5] = i9;
						}
						if (l6 != 0) {
							int l7 = Rasterizer.SINE[l6];
							int k8 = Rasterizer.COSINE[l6];
							int j9 = verticesZCoordinate[k5] * l7 + verticesXCoordinate[k5] * k8 >> 16;
							verticesZCoordinate[k5] = verticesZCoordinate[k5] * k8 - verticesXCoordinate[k5] * l7 >> 16;
							verticesXCoordinate[k5] = j9;
						}
						verticesXCoordinate[k5] += anInt1681 << (scaledVertices ? 2 : 0);
						verticesYCoordinate[k5] += anInt1682 << (scaledVertices ? 2 : 0);
						verticesZCoordinate[k5] += anInt1683 << (scaledVertices ? 2 : 0);
					}

				}
			}
			return;
		}
		if (i == 3) {
			for (int i2 = 0; i2 < i1; i2++) {
				int j3 = ai[i2];
				if (j3 < vertexSkin.length) {
					int ai3[] = vertexSkin[j3];
					for (int element : ai3) {
						int l5 = element;
						verticesXCoordinate[l5] -= anInt1681 << (scaledVertices ? 2 : 0);
						verticesYCoordinate[l5] -= anInt1682 << (scaledVertices ? 2 : 0);
						verticesZCoordinate[l5] -= anInt1683 << (scaledVertices ? 2 : 0);
						verticesXCoordinate[l5] = verticesXCoordinate[l5] * j / 128;
						verticesYCoordinate[l5] = verticesYCoordinate[l5] * k / 128;
						verticesZCoordinate[l5] = verticesZCoordinate[l5] * l / 128;
						verticesXCoordinate[l5] += anInt1681 << (scaledVertices ? 2 : 0);
						verticesYCoordinate[l5] += anInt1682 << (scaledVertices ? 2 : 0);
						verticesZCoordinate[l5] += anInt1683 << (scaledVertices ? 2 : 0);
					}
				}
			}
			return;
		}
		if (i == 5 && triangleSkin != null && faces_alpha != null) {
			for (int j2 = 0; j2 < i1; j2++) {
				int k3 = ai[j2];
				if (k3 < triangleSkin.length) {
					int ai4[] = triangleSkin[k3];
					for (int element : ai4) {
						int i6 = element;
						faces_alpha[i6] += j * 8;
						if (faces_alpha[i6] < 0) {
							faces_alpha[i6] = 0;
						}
						if (faces_alpha[i6] > 255) {
							faces_alpha[i6] = 255;
						}
					}
				}
			}
		}
	}

	public void method473() {
		for (int j = 0; j < numberOfVerticeCoordinates; j++) {
			int k = verticesXCoordinate[j];
			verticesXCoordinate[j] = verticesZCoordinate[j];
			verticesZCoordinate[j] = -k;
		}
	}

	public void rotateX(int i) {
		int k = Rasterizer.SINE[i];
		int l = Rasterizer.COSINE[i];

		for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
			int j1 = verticesYCoordinate[i1] * l - verticesZCoordinate[i1] * k >> 16;
			verticesZCoordinate[i1] = verticesYCoordinate[i1] * k + verticesZCoordinate[i1] * l >> 16;
			verticesYCoordinate[i1] = j1;
		}
	}

	public void scale(double factor) {
		for (int i = 0; i < numberOfVerticeCoordinates; ++i) {
			verticesYCoordinate[i] *= factor;
			verticesXCoordinate[i] *= factor;
			verticesZCoordinate[i] *= factor;
		}
	}

	public void translate(int i, int j, int l) {
		if (scaledVertices) {
			i <<= 2;
			j <<= 2;
			l <<= 2;
		}
		for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
			verticesXCoordinate[i1] += i;
			verticesYCoordinate[i1] += j;
			verticesZCoordinate[i1] += l;
		}
	}

	public void method476(int i, int j) {
		for (int k = 0; k < anInt1630; k++) {
			if (anIntArray1640[k] == i) {
				anIntArray1640[k] = j;
			}
		}
	}

	public void method477() {
		for (int j = 0; j < numberOfVerticeCoordinates; j++) {
			verticesZCoordinate[j] = -verticesZCoordinate[j];
		}
		for (int k = 0; k < anInt1630; k++) {
			int l = faces_a[k];
			faces_a[k] = faces_c[k];
			faces_c[k] = l;
		}
	}

	public void scaleT(int i, int j, int l) {
		for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
			verticesXCoordinate[i1] = verticesXCoordinate[i1] * i / 128;
			verticesYCoordinate[i1] = verticesYCoordinate[i1] * l / 128;
			verticesZCoordinate[i1] = verticesZCoordinate[i1] * j / 128;
		}

	}

	public final void light(int i, int j, int k, int l, int i1, boolean flag) {
		int j1 = (int) Math.sqrt(k * k + l * l + i1 * i1);
		int k1 = j * j1 >> 8;
		if (anIntArray1634 == null) {
			anIntArray1634 = new int[anInt1630];
			anIntArray1635 = new int[anInt1630];
			anIntArray1636 = new int[anInt1630];
		}
		if (super.aClass33Array1425 == null) {
			super.aClass33Array1425 = new Class33[numberOfVerticeCoordinates];
			for (int l1 = 0; l1 < numberOfVerticeCoordinates; l1++) {
				super.aClass33Array1425[l1] = new Class33();
			}

		}
		for (int i2 = 0; i2 < anInt1630; i2++) {
			if (anIntArray1640 != null && faces_alpha != null) {
				if (anIntArray1640[i2] == 65535
						/*
						 * || (anIntArray1640[i2] == 0 // Black Triangles 633 // Models
						 * - Fixes Gwd walls // & Black models )
						 */ || anIntArray1640[i2] == 16705) {
					faces_alpha[i2] = 255;
				}
			}
			int j2 = faces_a[i2];
			int l2 = faces_b[i2];
			int i3 = faces_c[i2];
			int j3 = verticesXCoordinate[l2] - verticesXCoordinate[j2] >> (scaledVertices ? 2 : 0);
			int k3 = verticesYCoordinate[l2] - verticesYCoordinate[j2] >> (scaledVertices ? 2 : 0);
			int l3 = verticesZCoordinate[l2] - verticesZCoordinate[j2] >> (scaledVertices ? 2 : 0);
			int i4 = verticesXCoordinate[i3] - verticesXCoordinate[j2] >> (scaledVertices ? 2 : 0);
			int j4 = verticesYCoordinate[i3] - verticesYCoordinate[j2] >> (scaledVertices ? 2 : 0);
			int k4 = verticesZCoordinate[i3] - verticesZCoordinate[j2] >> (scaledVertices ? 2 : 0);
			int l4 = k3 * k4 - j4 * l3;
			int i5 = l3 * i4 - k4 * j3;
			int j5;
			for (j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192 || j5 < -8192; j5 >>= 1) {
				l4 >>= 1;
				i5 >>= 1;
			}

			int k5 = (int) Math.sqrt(l4 * l4 + i5 * i5 + j5 * j5);
			if (k5 <= 0) {
				k5 = 1;
			}
			l4 = l4 * 256 / k5;
			i5 = i5 * 256 / k5;
			j5 = j5 * 256 / k5;

			if (face_render_type == null || (face_render_type[i2] & 1) == 0) {

				Class33 class33_2 = super.aClass33Array1425[j2];
				class33_2.anInt602 += l4;
				class33_2.anInt603 += i5;
				class33_2.anInt604 += j5;
				class33_2.anInt605++;
				class33_2 = super.aClass33Array1425[l2];
				class33_2.anInt602 += l4;
				class33_2.anInt603 += i5;
				class33_2.anInt604 += j5;
				class33_2.anInt605++;
				class33_2 = super.aClass33Array1425[i3];
				class33_2.anInt602 += l4;
				class33_2.anInt603 += i5;
				class33_2.anInt604 += j5;
				class33_2.anInt605++;

			} else {

				int l5 = i + (k * l4 + l * i5 + i1 * j5) / (k1 + k1 / 2);
				anIntArray1634[i2] = method481(anIntArray1640[i2], l5, face_render_type[i2]);

			}
		}

		if (flag) {
			method480(i, k1, k, l, i1);
		} else {
			aClass33Array1660 = new Class33[numberOfVerticeCoordinates];
			for (int k2 = 0; k2 < numberOfVerticeCoordinates; k2++) {
				Class33 class33 = super.aClass33Array1425[k2];
				Class33 class33_1 = aClass33Array1660[k2] = new Class33();
				class33_1.anInt602 = class33.anInt602;
				class33_1.anInt603 = class33.anInt603;
				class33_1.anInt604 = class33.anInt604;
				class33_1.anInt605 = class33.anInt605;
			}

		}
		if (flag) {
			method466();
			return;
		} else {
			method468(21073);
			return;
		}
	}


	public void light1(int i, int j, int k, int l, int i1, boolean flag) {
		try {
			int j1 = (int) Math.sqrt(k * k + l * l + i1 * i1);
			int k1 = j * j1 >> 8;
			if (anIntArray1634 == null) {
				anIntArray1634 = new int[numberOfTriangleFaces];
				anIntArray1635 = new int[numberOfTriangleFaces];
				anIntArray1636 = new int[numberOfTriangleFaces];
			}
			if (super.aClass33Array1425 == null) {
				super.aClass33Array1425 = new Class33[numberOfVerticeCoordinates];
				for (int l1 = 0; l1 < numberOfVerticeCoordinates; l1++) {
					super.aClass33Array1425[l1] = new Class33();
				}

			}
			for (int i2 = 0; i2 < numberOfTriangleFaces; i2++) {
				if (anIntArray1640 != null && faces_alpha != null) {
					if (anIntArray1640[i2] == 65535 || anIntArray1640[i2] == 1 || anIntArray1640[i2] == 16705 || anIntArray1640[i2] == 255) {
						faces_alpha[i2] = 255;
					}
				}
				int j2 = faces_a[i2];
				int l2 = faces_b[i2];
				int i3 = faces_c[i2];
				int j3 = verticesXCoordinate[l2] - verticesXCoordinate[j2];
				int k3 = verticesYCoordinate[l2] - verticesYCoordinate[j2];
				int l3 = verticesZCoordinate[l2] - verticesZCoordinate[j2];
				int i4 = verticesXCoordinate[i3] - verticesXCoordinate[j2];
				int j4 = verticesYCoordinate[i3] - verticesYCoordinate[j2];
				int k4 = verticesZCoordinate[i3] - verticesZCoordinate[j2];
				int l4 = k3 * k4 - j4 * l3;
				int i5 = l3 * i4 - k4 * j3;
				int j5;
				for (j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192 || j5 < -8192; j5 >>= 1) {
					l4 >>= 1;
					i5 >>= 1;
				}

				int k5 = (int) Math.sqrt(l4 * l4 + i5 * i5 + j5 * j5);
				if (k5 <= 0) {
					k5 = 1;
				}
				l4 = (l4 * 256) / k5;
				i5 = (i5 * 256) / k5;
				j5 = (j5 * 256) / k5;

				if (face_render_type == null || (face_render_type[i2] & 1) == 0) {

					Class33 class33_2 = super.aClass33Array1425[j2];
					class33_2.anInt602 += l4;
					class33_2.anInt603 += i5;
					class33_2.anInt604 += j5;
					class33_2.anInt605++;
					class33_2 = super.aClass33Array1425[l2];
					class33_2.anInt602 += l4;
					class33_2.anInt603 += i5;
					class33_2.anInt604 += j5;
					class33_2.anInt605++;
					class33_2 = super.aClass33Array1425[i3];
					class33_2.anInt602 += l4;
					class33_2.anInt603 += i5;
					class33_2.anInt604 += j5;
					class33_2.anInt605++;
					class33_2 = null;

				} else {

					int l5 = i + (k * l4 + l * i5 + i1 * j5) / (k1 + k1 / 2);
					anIntArray1634[i2] = method481(anIntArray1640[i2], l5, anIntArray1640[i2]);

				}
			}

			if (flag) {
				method480(i, k1, k, l, i1);
			} else {
				aClass33Array1660 = new Class33[numberOfVerticeCoordinates];
				for (int k2 = 0; k2 < numberOfVerticeCoordinates; k2++) {
					Class33 class33 = super.aClass33Array1425[k2];
					Class33 class33_1 = aClass33Array1660[k2] = new Class33();
					class33_1.anInt602 = class33.anInt602;
					class33_1.anInt603 = class33.anInt603;
					class33_1.anInt604 = class33.anInt604;
					class33_1.anInt605 = class33.anInt605;
				}

			}
			if (flag) {
				method466();
				return;
			} else {
				method468(21073);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final void method480(int i, int j, int k, int l, int i1) {
		for (int j1 = 0; j1 < anInt1630; j1++) {
			int k1 = faces_a[j1];
			int i2 = faces_b[j1];
			int j2 = faces_c[j1];
			if (face_render_type == null) {
				int i3 = anIntArray1640[j1];
				Class33 class33 = super.aClass33Array1425[k1];
				int k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
				anIntArray1634[j1] = method481(i3, k2, 0);
				class33 = super.aClass33Array1425[i2];
				k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
				anIntArray1635[j1] = method481(i3, k2, 0);
				class33 = super.aClass33Array1425[j2];
				k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
				anIntArray1636[j1] = method481(i3, k2, 0);
			} else if ((face_render_type[j1] & 1) == 0) {
				int j3 = anIntArray1640[j1];
				int k3 = face_render_type[j1];
				Class33 class33_1 = super.aClass33Array1425[k1];
				int l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604) / (j * class33_1.anInt605);
				anIntArray1634[j1] = method481(j3, l2, k3);
				class33_1 = super.aClass33Array1425[i2];
				l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604) / (j * class33_1.anInt605);
				anIntArray1635[j1] = method481(j3, l2, k3);
				class33_1 = super.aClass33Array1425[j2];
				l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604) / (j * class33_1.anInt605);
				anIntArray1636[j1] = method481(j3, l2, k3);
			}
		}

		super.aClass33Array1425 = null;
		aClass33Array1660 = null;
		anIntArray1655 = null;
		anIntArray1656 = null;
		if (face_render_type != null) {
			for (int l1 = 0; l1 < anInt1630; l1++) {
				if ((face_render_type[l1] & 2) == 2) {
					return;
				}
			}

		}
		anIntArray1640 = null;
	}

	public final void renderSingle(int j, int k, int l, int i1, int j1, int k1) {
		int i = 0;
		int l1 = Rasterizer.centerX;
		int i2 = Rasterizer.centerY;
		int j2 = Rasterizer.SINE[i];
		int k2 = Rasterizer.COSINE[i];
		int l2 = Rasterizer.SINE[j];
		int i3 = Rasterizer.COSINE[j];
		int j3 = Rasterizer.SINE[k];
		int k3 = Rasterizer.COSINE[k];
		int l3 = Rasterizer.SINE[l];
		int i4 = Rasterizer.COSINE[l];
		int j4 = j1 * l3 + k1 * i4 >> 16;
		for (int k4 = 0; k4 < numberOfVerticeCoordinates; k4++) {
			int l4 = verticesXCoordinate[k4] << (scaledVertices ? 0 : 2);
			int i5 = verticesYCoordinate[k4] << (scaledVertices ? 0 : 2);
			int j5 = verticesZCoordinate[k4] << (scaledVertices ? 0 : 2);
			if (k != 0) {
				int k5 = i5 * j3 + l4 * k3 >> 16;
				i5 = i5 * k3 - l4 * j3 >> 16;
				l4 = k5;
			}
			if (i != 0) {
				int l5 = i5 * k2 - j5 * j2 >> 16;
				j5 = i5 * j2 + j5 * k2 >> 16;
				i5 = l5;
			}
			if (j != 0) {
				int i6 = j5 * l2 + l4 * i3 >> 16;
				j5 = j5 * i3 - l4 * l2 >> 16;
				l4 = i6;
			}
			l4 += i1 << 2;
			i5 += j1 << 2;
			j5 += k1 << 2;
			int j6 = i5 * i4 - j5 * l3 >> 16;
			j5 = i5 * l3 + j5 * i4 >> 16;
			i5 = j6;
			anIntArray1667[k4] = (j5 >> 2) - j4;
			vertexPerspectiveDepth[k4] = (j5 >> 2);
			if (j5 == 0) {
				return;
			}
			anIntArray1665[k4] = l1 + (l4 << 9) / j5;
			anIntArray1666[k4] = i2 + (i5 << 9) / j5;
			if (anInt1642 > 0) {
				anIntArray1668[k4] = l4 >> 2;
				anIntArray1669[k4] = i5 >> 2;
				anIntArray1670[k4] = j5 >> 2;
			}
		}

		try {
			translateToScreen(false, false, 0, 0, 0);
			return;
		} catch (Exception _ex) {
			_ex.printStackTrace();
			return;
		}
	}

	@Override
	public void method443(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int uid, int newuid, int bufferOffset) {
		offX = j1 + Client.instance.xCameraPos;
		offY = k1 + Client.instance.zCameraPos;
		offZ = l1 + Client.instance.yCameraPos;
		lastRenderedRotation = i;
		int j2 = l1 * i1 - j1 * l >> 16;
		int k2 = k1 * j + j2 * k >> 16;
		int l2 = boundingPlaneRadius * k >> 16;
		int i3 = k2 + l2;
		if (i3 <= 50 || k2 >= 3500) {
			return;
		}
		int j3 = l1 * l + j1 * i1 >> 16;
		int k3 = j3 - boundingPlaneRadius << Client.viewDistance;
		if (k3 / i3 >= DrawingArea.centerY) {
			return;
		}
		int l3 = j3 + boundingPlaneRadius << Client.viewDistance;
		if (l3 / i3 <= -DrawingArea.centerY) {
			return;
		}
		int i4 = k1 * k - j2 * j >> 16;
		int j4 = boundingPlaneRadius * j >> 16;
		int k4 = i4 + j4 << Client.viewDistance;
		if (k4 / i3 <= -DrawingArea.middleY) {
			return;
		}
		int l4 = j4 + (super.modelHeight * k >> 16);
		int i5 = i4 - l4 << Client.viewDistance;
		if (i5 / i3 >= DrawingArea.middleY) {
			return;
		}
		int j5 = l2 + (super.modelHeight * j >> 16);
		boolean flag = false;
		if (k2 - j5 <= 50) {
			flag = true;
		}
		boolean flag1 = false;
		if (uid > 0 && objectExists) {
			int k5 = k2 - l2;
			if (k5 <= 50) {
				k5 = 50;
			}
			if (j3 > 0) {
				k3 /= i3;
				l3 /= k5;
			} else {
				l3 /= i3;
				k3 /= k5;
			}
			if (i4 > 0) {
				i5 /= i3;
				k4 /= k5;
			} else {
				k4 /= i3;
				i5 /= k5;
			}
			int i6 = currentCursorX - Rasterizer.centerX;
			int k6 = currentCursorY - Rasterizer.centerY;
			if (i6 > k3 && i6 < l3 && k6 > i5 && k6 < k4) {
				if (aBoolean1659) {
					mapObjIds[objectsRendered] = newuid;
					anIntArray1688[objectsRendered++] = uid;
				} else {
					flag1 = true;
				}
			}
		}
		int l5 = Rasterizer.centerX;
		int j6 = Rasterizer.centerY;
		int l6 = 0;
		int i7 = 0;
		if (i != 0) {
			l6 = Rasterizer.SINE[i];
			i7 = Rasterizer.COSINE[i];
		}
		for (int j7 = 0; j7 < numberOfVerticeCoordinates; j7++) {
			int k7 = verticesXCoordinate[j7] << (scaledVertices ? 0 : 2);
			int l7 = verticesYCoordinate[j7] << (scaledVertices ? 0 : 2);
			int i8 = verticesZCoordinate[j7] << (scaledVertices ? 0 : 2);
			if (i != 0) {
				int j8 = i8 * l6 + k7 * i7 >> 16;
				i8 = i8 * i7 - k7 * l6 >> 16;
				k7 = j8;
			}
			k7 += j1 << 2;
			l7 += k1 << 2;
			i8 += l1 << 2;
			int k8 = i8 * l + k7 * i1 >> 16;
			i8 = i8 * i1 - k7 * l >> 16;
			k7 = k8;
			k8 = l7 * k - i8 * j >> 16;
			i8 = l7 * j + i8 * k >> 16;
			l7 = k8;
			anIntArray1667[j7] = (i8 >> 2) - k2;
			vertexPerspectiveDepth[j7] = (i8 >> 2);
			if (i8 >= 50) {
				anIntArray1665[j7] = l5 + (k7 << Client.viewDistance) / i8;
				anIntArray1666[j7] = j6 + (l7 << Client.viewDistance) / i8;
			} else {
				anIntArray1665[j7] = -5000;
				flag = true;
			}
			if (flag || anInt1642 > 0) {
				anIntArray1668[j7] = k7 >> 2;
				anIntArray1669[j7] = l7 >> 2;
				anIntArray1670[j7] = i8 >> 2;
			}
		}
		try {
			translateToScreen(flag, flag1, uid, newuid, bufferOffset);
			return;
		} catch (Exception _ex) {
			return;
		}
	}

	private final void translateToScreen(boolean flag, boolean flag1, int i, int id, int bufferOffset) {
		for (int j = 0; j < diagonal3D; j++) {
			depthListIndices[j] = 0;
		}

		for (int k = 0; k < anInt1630; k++) {
			if (face_render_type == null || face_render_type[k] != -1) {
				int l = faces_a[k];
				int k1 = faces_b[k];
				int j2 = faces_c[k];
				int i3 = anIntArray1665[l];
				int l3 = anIntArray1665[k1];
				int k4 = anIntArray1665[j2];
				if (flag && (i3 == -5000 || l3 == -5000 || k4 == -5000)) {
					outOfReach[k] = true;
					int j5 = (anIntArray1667[l] + anIntArray1667[k1] + anIntArray1667[j2]) / 3 + anInt1653;
					faceLists[j5][depthListIndices[j5]++] = k;
				} else {
					if (flag1 && method486(currentCursorX, currentCursorY, anIntArray1666[l], anIntArray1666[k1], anIntArray1666[j2], i3, l3, k4)) {
						mapObjIds[objectsRendered] = id;
						anIntArray1688[objectsRendered++] = i;
						flag1 = false;
					}
					if ((i3 - l3) * (anIntArray1666[j2] - anIntArray1666[k1]) - (anIntArray1666[l] - anIntArray1666[k1]) * (k4 - l3) > 0) {
						outOfReach[k] = false;
						if (i3 < 0 || l3 < 0 || k4 < 0 || i3 > DrawingArea.maxRight || l3 > DrawingArea.maxRight || k4 > DrawingArea.maxRight) {
							hasAnEdgeToRestrict[k] = true;
						} else {
							hasAnEdgeToRestrict[k] = false;
						}
						int k5 = (anIntArray1667[l] + anIntArray1667[k1] + anIntArray1667[j2]) / 3 + anInt1653;
						faceLists[k5][depthListIndices[k5]++] = k;
					}
				}
			}
		}

		if (face_render_priorities == null) {
			for (int i1 = diagonal3D - 1; i1 >= 0; i1--) {
				int l1 = depthListIndices[i1];
				if (l1 > 0) {
					int ai[] = faceLists[i1];
					for (int j3 = 0; j3 < l1; j3++) {
						rasterise(ai[j3], bufferOffset);
					}

				}
			}

			return;
		}
		for (int j1 = 0; j1 < 12; j1++) {
			anIntArray1673[j1] = 0;
			anIntArray1677[j1] = 0;
		}

		for (int i2 = diagonal3D - 1; i2 >= 0; i2--) {
			int k2 = depthListIndices[i2];
			if (k2 > 0) {
				int ai1[] = faceLists[i2];
				for (int i4 = 0; i4 < k2; i4++) {
					int l4 = ai1[i4];
					int l5 = face_render_priorities[l4];
					int j6 = anIntArray1673[l5]++;
					anIntArrayArray1674[l5][j6] = l4;
					if (l5 < 10) {
						anIntArray1677[l5] += i2;
					} else if (l5 == 10) {
						anIntArray1675[j6] = i2;
					} else {
						anIntArray1676[j6] = i2;
					}
				}

			}
		}

		int l2 = 0;
		if (anIntArray1673[1] > 0 || anIntArray1673[2] > 0) {
			l2 = (anIntArray1677[1] + anIntArray1677[2]) / (anIntArray1673[1] + anIntArray1673[2]);
		}
		int k3 = 0;
		if (anIntArray1673[3] > 0 || anIntArray1673[4] > 0) {
			k3 = (anIntArray1677[3] + anIntArray1677[4]) / (anIntArray1673[3] + anIntArray1673[4]);
		}
		int j4 = 0;
		if (anIntArray1673[6] > 0 || anIntArray1673[8] > 0) {
			j4 = (anIntArray1677[6] + anIntArray1677[8]) / (anIntArray1673[6] + anIntArray1673[8]);
		}
		int i6 = 0;
		int k6 = anIntArray1673[10];
		int ai2[] = anIntArrayArray1674[10];
		int ai3[] = anIntArray1675;
		if (i6 == k6) {
			i6 = 0;
			k6 = anIntArray1673[11];
			ai2 = anIntArrayArray1674[11];
			ai3 = anIntArray1676;
		}
		int i5;
		if (i6 < k6) {
			i5 = ai3[i6];
		} else {
			i5 = -1000;
		}
		for (int l6 = 0; l6 < 10; l6++) {
			while (l6 == 0 && i5 > l2) {
				rasterise(ai2[i6++], bufferOffset);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6) {
					i5 = ai3[i6];
				} else {
					i5 = -1000;
				}
			}
			while (l6 == 3 && i5 > k3) {
				rasterise(ai2[i6++], bufferOffset);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6) {
					i5 = ai3[i6];
				} else {
					i5 = -1000;
				}
			}
			while (l6 == 5 && i5 > j4) {
				rasterise(ai2[i6++], bufferOffset);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6) {
					i5 = ai3[i6];
				} else {
					i5 = -1000;
				}
			}
			int i7 = anIntArray1673[l6];
			int ai4[] = anIntArrayArray1674[l6];
			for (int j7 = 0; j7 < i7; j7++) {
				rasterise(ai4[j7], bufferOffset);
			}

		}

		while (i5 != -1000) {
			rasterise(ai2[i6++], bufferOffset);
			if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
				i6 = 0;
				ai2 = anIntArrayArray1674[11];
				k6 = anIntArray1673[11];
				ai3 = anIntArray1676;
			}
			if (i6 < k6) {
				i5 = ai3[i6];
			} else {
				i5 = -1000;
			}
		}

		for (int m = 0; m < numberOfVerticeCoordinates; m++) {
			int n = m;

			int pid = verticesParticle[n] - 1;
			if (pid < 0) {
				continue;
			}

			/*ParticleDefinition def = ParticleDefinition.cache[pid];
			int pX = verticesXCoordinate[n] >> (scaledVertices ? 2 : 0);
			int pY = verticesYCoordinate[n] >> (scaledVertices ? 2 : 0);
			int pZ = verticesZCoordinate[n] >> (scaledVertices ? 2 : 0);
			if (lastRenderedRotation != 0) {
				int sine = Rasterizer.SINE[lastRenderedRotation];
				int cosine = Rasterizer.COSINE[lastRenderedRotation];
				int rotatedX = pZ * sine + pX * cosine >> 16;
				pZ = pZ * cosine - pX * sine >> 16;
				pX = rotatedX;
			}
			pX += offX;
			pZ += offZ;
			Vector basePos = new Vector(pX, -pY, pZ);
			for (int p = 0; p < def.getSpawnRate(); p++) {
				Particle particle = new Particle(def, basePos, pid);
				Client.instance.addParticle(particle);
			}*/
		}
	}

	private final void rasterise(int face, int key) {
		if (outOfReach[face]) {
			reduce(face, key);
			return;
		}
		int j = faces_a[face];
		int k = faces_b[face];
		int l = faces_c[face];
		Rasterizer.restrict_edges = hasAnEdgeToRestrict[face];
		if (faces_alpha == null) {
			Rasterizer.alpha = 0;
		} else {
			Rasterizer.alpha = faces_alpha[face];
		}
		int type = face_render_type == null ? 0 : face_render_type[face] & 3;
	
		//Models
		if (type == 0) {
			Rasterizer.drawGouraudTriangle(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], anIntArray1634[face], anIntArray1635[face], anIntArray1636[face], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l], key);
		} else if (type == 1) {
			Rasterizer.drawFlatTriangle(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], Rasterizer.hsl2rgb[anIntArray1634[face]], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l], key);
			//Textured Models
		} else if (type == 2) {
			int j1 = face_render_type[face] >> 2;
			int l1 = texture_face_x[j1];
			int j2 = texture_face_y[j1];
			int l2 = texture_face_z[j1];
			Rasterizer.drawTexturedTriangle(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], anIntArray1634[face], anIntArray1635[face], anIntArray1636[face], anIntArray1668[l1], anIntArray1668[j2], anIntArray1668[l2], anIntArray1669[l1], anIntArray1669[j2], anIntArray1669[l2], anIntArray1670[l1], anIntArray1670[j2], anIntArray1670[l2], anIntArray1640[face], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l], key);

		} else if (type == 3) {
			int k1 = face_render_type[face] >> 2;
			int i2 = texture_face_x[k1];
			int k2 = texture_face_y[k1];
			int i3 = texture_face_z[k1];
			Rasterizer.drawTexturedTriangle(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], anIntArray1634[face], anIntArray1634[face], anIntArray1634[face], anIntArray1668[i2], anIntArray1668[k2], anIntArray1668[i3], anIntArray1669[i2], anIntArray1669[k2], anIntArray1669[i3], anIntArray1670[i2], anIntArray1670[k2], anIntArray1670[i3], anIntArray1640[face], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l], key);
		}
	}

	private final void reduce(int i, int bufferOffset) {
		if (anIntArray1640 != null) {
			if (anIntArray1640[i] == 65535) {
				return;
			}
		}
		int j = Rasterizer.centerX;
		int k = Rasterizer.centerY;
		int l = 0;
		int i1 = faces_a[i];
		int j1 = faces_b[i];
		int k1 = faces_c[i];
		int l1 = anIntArray1670[i1];
		int i2 = anIntArray1670[j1];
		int j2 = anIntArray1670[k1];

		if (l1 >= 50) {
			anIntArray1678[l] = anIntArray1665[i1];
			anIntArray1679[l] = anIntArray1666[i1];
			anIntArray1680[l++] = anIntArray1634[i];
		} else {
			int k2 = anIntArray1668[i1];
			int k3 = anIntArray1669[i1];
			int k4 = anIntArray1634[i];
			if (j2 >= 50) {
				int k5 = (50 - l1) * Rasterizer.anIntArray1469[j2 - l1];
				anIntArray1678[l] = j + (k2 + ((anIntArray1668[k1] - k2) * k5 >> 16) << Client.viewDistance) / 50;
				anIntArray1679[l] = k + (k3 + ((anIntArray1669[k1] - k3) * k5 >> 16) << Client.viewDistance) / 50;
				anIntArray1680[l++] = k4 + ((anIntArray1636[i] - k4) * k5 >> 16);
			}
			if (i2 >= 50) {
				int l5 = (50 - l1) * Rasterizer.anIntArray1469[i2 - l1];
				anIntArray1678[l] = j + (k2 + ((anIntArray1668[j1] - k2) * l5 >> 16) << Client.viewDistance) / 50;
				anIntArray1679[l] = k + (k3 + ((anIntArray1669[j1] - k3) * l5 >> 16) << Client.viewDistance) / 50;
				anIntArray1680[l++] = k4 + ((anIntArray1635[i] - k4) * l5 >> 16);
			}
		}
		if (i2 >= 50) {
			anIntArray1678[l] = anIntArray1665[j1];
			anIntArray1679[l] = anIntArray1666[j1];
			anIntArray1680[l++] = anIntArray1635[i];
		} else {
			int l2 = anIntArray1668[j1];
			int l3 = anIntArray1669[j1];
			int l4 = anIntArray1635[i];
			if (l1 >= 50) {
				int i6 = (50 - i2) * Rasterizer.anIntArray1469[l1 - i2];
				anIntArray1678[l] = j + (l2 + ((anIntArray1668[i1] - l2) * i6 >> 16) << Client.viewDistance) / 50;
				anIntArray1679[l] = k + (l3 + ((anIntArray1669[i1] - l3) * i6 >> 16) << Client.viewDistance) / 50;
				anIntArray1680[l++] = l4 + ((anIntArray1634[i] - l4) * i6 >> 16);
			}
			if (j2 >= 50) {
				int j6 = (50 - i2) * Rasterizer.anIntArray1469[j2 - i2];
				anIntArray1678[l] = j + (l2 + ((anIntArray1668[k1] - l2) * j6 >> 16) << Client.viewDistance) / 50;
				anIntArray1679[l] = k + (l3 + ((anIntArray1669[k1] - l3) * j6 >> 16) << Client.viewDistance) / 50;
				anIntArray1680[l++] = l4 + ((anIntArray1636[i] - l4) * j6 >> 16);
			}
		}
		if (j2 >= 50) {
			anIntArray1678[l] = anIntArray1665[k1];
			anIntArray1679[l] = anIntArray1666[k1];
			anIntArray1680[l++] = anIntArray1636[i];
		} else {
			int i3 = anIntArray1668[k1];
			int i4 = anIntArray1669[k1];
			int i5 = anIntArray1636[i];
			if (i2 >= 50) {
				int k6 = (50 - j2) * Rasterizer.anIntArray1469[i2 - j2];
				anIntArray1678[l] = j + (i3 + ((anIntArray1668[j1] - i3) * k6 >> 16) << Client.viewDistance) / 50;
				anIntArray1679[l] = k + (i4 + ((anIntArray1669[j1] - i4) * k6 >> 16) << Client.viewDistance) / 50;
				anIntArray1680[l++] = i5 + ((anIntArray1635[i] - i5) * k6 >> 16);
			}
			if (l1 >= 50) {
				int l6 = (50 - j2) * Rasterizer.anIntArray1469[l1 - j2];
				anIntArray1678[l] = j + (i3 + ((anIntArray1668[i1] - i3) * l6 >> 16) << Client.viewDistance) / 50;
				anIntArray1679[l] = k + (i4 + ((anIntArray1669[i1] - i4) * l6 >> 16) << Client.viewDistance) / 50;
				anIntArray1680[l++] = i5 + ((anIntArray1634[i] - i5) * l6 >> 16);
			}
		}
		int j3 = anIntArray1678[0];
		int j4 = anIntArray1678[1];
		int j5 = anIntArray1678[2];
		int i7 = anIntArray1679[0];
		int j7 = anIntArray1679[1];
		int k7 = anIntArray1679[2];
		if ((j3 - j4) * (k7 - j7) - (i7 - j7) * (j5 - j4) > 0) {
			Rasterizer.restrict_edges = false;
			if (l == 3) {
				if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.maxRight || j4 > DrawingArea.maxRight || j5 > DrawingArea.maxRight) {
					Rasterizer.restrict_edges = true;
				}
				int l7;
				if (face_render_type == null) {
					l7 = 0;
				} else {
					l7 = face_render_type[i] & 3;
				}
				if (l7 == 0) {
					Rasterizer.drawGouraudTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], -1f, -1f, -1f, bufferOffset);
				} else if (l7 == 1) {
					Rasterizer.drawFlatTriangle(i7, j7, k7, j3, j4, j5, Rasterizer.hsl2rgb[anIntArray1634[i]], -1f, -1f, -1f, bufferOffset);
				} else if (l7 == 2) {
					/*
					 * int k9 = anIntArray1643[j8];
					int k10 = anIntArray1644[j8];
					int k11 = anIntArray1645[j8];
					 */
					int j8 = face_render_type[i] >> 2;
					int k9 = texture_face_x[j8];
					int k10 = texture_face_y[j8];
					int k11 = texture_face_z[j8];
					Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], anIntArray1668[k9], anIntArray1668[k10], anIntArray1668[k11], anIntArray1669[k9], anIntArray1669[k10], anIntArray1669[k11], anIntArray1670[k9], anIntArray1670[k10], anIntArray1670[k11], anIntArray1640[i], -1f, -1f, -1f, bufferOffset);
				} else if (l7 == 3) {
					int k8 = face_render_type[i] >> 2;
					int l9 = texture_face_x[k8];
					int l10 = texture_face_y[k8];
					int l11 = texture_face_z[k8];
					Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[l9], anIntArray1668[l10], anIntArray1668[l11], anIntArray1669[l9], anIntArray1669[l10], anIntArray1669[l11], anIntArray1670[l9], anIntArray1670[l10], anIntArray1670[l11], anIntArray1640[i], -1f, -1f, -1f, bufferOffset);
				}
			}
			if (l == 4) {
				if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.maxRight || j4 > DrawingArea.maxRight || j5 > DrawingArea.maxRight || anIntArray1678[3] < 0 || anIntArray1678[3] > DrawingArea.maxRight) {
					Rasterizer.restrict_edges = true;
				}
				int i8;
				if (face_render_type == null) {
					i8 = 0;
				} else {
					i8 = face_render_type[i] & 3;
				}
				if (i8 == 0) {
					Rasterizer.drawGouraudTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], -1f, -1f, -1f, bufferOffset);
					Rasterizer.drawGouraudTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], -1f, -1f, -1f, bufferOffset);
					return;
				}
				if (i8 == 1) {
					int l8 = Rasterizer.hsl2rgb[anIntArray1634[i]];
					Rasterizer.drawFlatTriangle(i7, j7, k7, j3, j4, j5, l8, -1f, -1f, -1f, bufferOffset);
					Rasterizer.drawFlatTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], l8, -1f, -1f, -1f, bufferOffset);
					return;
				}
				if (i8 == 2) {
					int i9 = face_render_type[i] >> 2;
					int i10 = texture_face_x[i9];
					int i11 = texture_face_y[i9];
					int i12 = texture_face_z[i9];
					Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], anIntArray1668[i10], anIntArray1668[i11], anIntArray1668[i12], anIntArray1669[i10], anIntArray1669[i11], anIntArray1669[i12], anIntArray1670[i10], anIntArray1670[i11], anIntArray1670[i12], anIntArray1640[i], -1f, -1f, -1f, bufferOffset);
					Rasterizer.drawTexturedTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], anIntArray1668[i10], anIntArray1668[i11], anIntArray1668[i12], anIntArray1669[i10], anIntArray1669[i11], anIntArray1669[i12], anIntArray1670[i10], anIntArray1670[i11], anIntArray1670[i12], anIntArray1640[i], -1f, -1f, -1f, bufferOffset);
					return;
				}
				if (i8 == 3) {
					int j9 = face_render_type[i] >> 2;
					int j10 = texture_face_x[j9];
					int j11 = texture_face_y[j9];
					int j12 = texture_face_z[j9];
					Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], anIntArray1640[i], -1f, -1f, -1f, bufferOffset);
					Rasterizer.drawTexturedTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], anIntArray1640[i], -1f, -1f, -1f, bufferOffset);
				}
			}
		}
	}

	private final boolean method486(int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
		if (j < k && j < l && j < i1) {
			return false;
		}
		if (j > k && j > l && j > i1) {
			return false;
		}
		if (i < j1 && i < k1 && i < l1) {
			return false;
		}
		return i <= j1 || i <= k1 || i <= l1;
	}

	private void read525Model(byte abyte0[], int modelID) {
		ByteBuffer nc1 = new ByteBuffer(abyte0);
		ByteBuffer nc2 = new ByteBuffer(abyte0);
		ByteBuffer nc3 = new ByteBuffer(abyte0);
		ByteBuffer nc4 = new ByteBuffer(abyte0);
		ByteBuffer nc5 = new ByteBuffer(abyte0);
		ByteBuffer nc6 = new ByteBuffer(abyte0);
		ByteBuffer nc7 = new ByteBuffer(abyte0);
		nc1.position = abyte0.length - 23;
		int numVertices = nc1.getUnsignedShort();
		int numTriangles = nc1.getUnsignedShort();
		int numTextureTriangles = nc1.getUnsignedByte();
		int l1 = nc1.getUnsignedByte();
		boolean bool = (0x1 & l1 ^ 0xffffffff) == -2;
		int i2 = nc1.getUnsignedByte();
		int j2 = nc1.getUnsignedByte();
		int k2 = nc1.getUnsignedByte();
		int l2 = nc1.getUnsignedByte();
		int i3 = nc1.getUnsignedByte();
		int j3 = nc1.getUnsignedShort();
		int k3 = nc1.getUnsignedShort();
		int l3 = nc1.getUnsignedShort();
		int i4 = nc1.getUnsignedShort();
		int j4 = nc1.getUnsignedShort();
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		byte[] x = null;
		byte[] O = null;
		byte[] J = null;
		byte[] F = null;
		byte[] cb = null;
		byte[] gb = null;
		byte[] lb = null;
		int[] kb = null;
		int[] y = null;
		int[] N = null;
		short[] D = null;
		int[] triangleColours2 = new int[numTriangles];
		if (numTextureTriangles > 0) {
			O = new byte[numTextureTriangles];
			nc1.position = 0;
			for (int j5 = 0; j5 < numTextureTriangles; j5++) {
				byte byte0 = O[j5] = nc1.getSignedByte();
				if (byte0 == 0) {
					k4++;
				}
				if (byte0 >= 1 && byte0 <= 3) {
					l4++;
				}
				if (byte0 == 2) {
					i5++;
				}
			}
		}
		int k5 = numTextureTriangles;
		int l5 = k5;
		k5 += numVertices;
		int i6 = k5;
		if (l1 == 1) {
			k5 += numTriangles;
		}
		int j6 = k5;
		k5 += numTriangles;
		int k6 = k5;
		if (i2 == 255) {
			k5 += numTriangles;
		}
		int l6 = k5;
		if (k2 == 1) {
			k5 += numTriangles;
		}
		int i7 = k5;
		if (i3 == 1) {
			k5 += numVertices;
		}
		int j7 = k5;
		if (j2 == 1) {
			k5 += numTriangles;
		}
		int k7 = k5;
		k5 += i4;
		int l7 = k5;
		if (l2 == 1) {
			k5 += numTriangles * 2;
		}
		int i8 = k5;
		k5 += j4;
		int j8 = k5;
		k5 += numTriangles * 2;
		int k8 = k5;
		k5 += j3;
		int l8 = k5;
		k5 += k3;
		int i9 = k5;
		k5 += l3;
		int j9 = k5;
		k5 += k4 * 6;
		int k9 = k5;
		k5 += l4 * 6;
		int l9 = k5;
		k5 += l4 * 6;
		int i10 = k5;
		k5 += l4;
		int j10 = k5;
		k5 += l4;
		int k10 = k5;
		k5 += l4 + i5 * 2;
		verticesParticle = new int[numVertices];
		int[] vertexX = new int[numVertices];
		int[] vertexY = new int[numVertices];
		int[] vertexZ = new int[numVertices];
		int[] facePoint1 = new int[numTriangles];
		int[] facePoint2 = new int[numTriangles];
		int[] facePoint3 = new int[numTriangles];
		anIntArray1655 = new int[numVertices];
		face_render_type = new int[numTriangles];
		face_render_priorities = new int[numTriangles];
		faces_alpha = new int[numTriangles];
		anIntArray1656 = new int[numTriangles];
		if (i3 == 1) {
			anIntArray1655 = new int[numVertices];
		}
		if (bool) {
			face_render_type = new int[numTriangles];
		}
		if (i2 == 255) {
			face_render_priorities = new int[numTriangles];
		} else {
		}
		if (j2 == 1) {
			faces_alpha = new int[numTriangles];
		}
		if (k2 == 1) {
			anIntArray1656 = new int[numTriangles];
		}
		if (l2 == 1) {
			D = new short[numTriangles];
		}
		if (l2 == 1 && numTextureTriangles > 0) {
			x = new byte[numTriangles];
		}
		triangleColours2 = new int[numTriangles];
		int[] texTrianglesPoint1 = null;
		int[] texTrianglesPoint2 = null;
		int[] texTrianglesPoint3 = null;
		if (numTextureTriangles > 0) {
			texTrianglesPoint1 = new int[numTextureTriangles];
			texTrianglesPoint2 = new int[numTextureTriangles];
			texTrianglesPoint3 = new int[numTextureTriangles];
			if (l4 > 0) {
				kb = new int[l4];
				N = new int[l4];
				y = new int[l4];
				gb = new byte[l4];
				lb = new byte[l4];
				F = new byte[l4];
			}
			if (i5 > 0) {
				cb = new byte[i5];
				J = new byte[i5];
			}
		}
		nc1.position = l5;
		nc2.position = k8;
		nc3.position = l8;
		nc4.position = i9;
		nc5.position = i7;
		int l10 = 0;
		int i11 = 0;
		int j11 = 0;
		for (int k11 = 0; k11 < numVertices; k11++) {
			int l11 = nc1.getUnsignedByte();
			int j12 = 0;
			if ((l11 & 1) != 0) {
				j12 = nc2.method421();
			}
			int l12 = 0;
			if ((l11 & 2) != 0) {
				l12 = nc3.method421();
			}
			int j13 = 0;
			if ((l11 & 4) != 0) {
				j13 = nc4.method421();
			}
			vertexX[k11] = l10 + j12;
			vertexY[k11] = i11 + l12;
			vertexZ[k11] = j11 + j13;
			l10 = vertexX[k11];
			i11 = vertexY[k11];
			j11 = vertexZ[k11];
			if (anIntArray1655 != null) {
				anIntArray1655[k11] = nc5.getUnsignedByte();
			}
		}
		nc1.position = j8;
		nc2.position = i6;
		nc3.position = k6;
		nc4.position = j7;
		nc5.position = l6;
		nc6.position = l7;
		nc7.position = i8;
		for (int i12 = 0; i12 < numTriangles; i12++) {
			triangleColours2[i12] = nc1.getUnsignedShort();
			if (l1 == 1) {
				face_render_type[i12] = nc2.getSignedByte();
				if (face_render_type[i12] == 2) {
					triangleColours2[i12] = 65535;
				}
				face_render_type[i12] = 0;
			}
			if (i2 == 255) {
				face_render_priorities[i12] = nc3.getSignedByte();
			}
			if (j2 == 1) {
				faces_alpha[i12] = nc4.getSignedByte();
				if (faces_alpha[i12] < 0) {
					faces_alpha[i12] = 256 + faces_alpha[i12];
				}
			}
			if (k2 == 1) {
				anIntArray1656[i12] = nc5.getUnsignedByte();
			}
			if (l2 == 1) {
				D[i12] = (short) (nc6.getUnsignedShort() - 1);
			}
			if (x != null) {
				if (D[i12] != -1) {
					x[i12] = (byte) (nc7.getUnsignedByte() - 1);
				} else {
					x[i12] = -1;
				}
			}
		}
		// /fix's triangle issue, but fucked up - no need, loading all 474-
		// models
		/*
		 * try { for(int i12 = 0; i12 < numTriangles; i12++) {
		 * triangleColours2[i12] = nc1.readUnsignedWord(); if(l1 == 1){
		 * anIntArray1637[i12] = nc2.readSignedByte(); } if(i2 == 255){
		 * anIntArray1638[i12] = nc3.readSignedByte(); } if(j2 == 1){
		 * anIntArray1639[i12] = nc4.readSignedByte(); if(anIntArray1639[i12] <
		 * 0) anIntArray1639[i12] = (256+anIntArray1639[i12]); } if(k2 == 1)
		 * anIntArray1656[i12] = nc5.readUnsignedByte(); if(l2 == 1) D[i12] =
		 * (short)(nc6.readUnsignedWord() - 1); if(x != null) if(D[i12] != -1)
		 * x[i12] = (byte)(nc7.readUnsignedByte() -1); else x[i12] = -1; } }
		 * catch (Exception ex) { }
		 */
		nc1.position = k7;
		nc2.position = j6;
		int k12 = 0;
		int i13 = 0;
		int k13 = 0;
		int l13 = 0;
		for (int i14 = 0; i14 < numTriangles; i14++) {
			int j14 = nc2.getUnsignedByte();
			if (j14 == 1) {
				k12 = nc1.method421() + l13;
				l13 = k12;
				i13 = nc1.method421() + l13;
				l13 = i13;
				k13 = nc1.method421() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 2) {
				i13 = k13;
				k13 = nc1.method421() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 3) {
				k12 = k13;
				k13 = nc1.method421() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 4) {
				int l14 = k12;
				k12 = i13;
				i13 = l14;
				k13 = nc1.method421() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
		}
		nc1.position = j9;
		nc2.position = k9;
		nc3.position = l9;
		nc4.position = i10;
		nc5.position = j10;
		nc6.position = k10;
		for (int k14 = 0; k14 < numTextureTriangles; k14++) {
			int i15 = O[k14] & 0xff;
			if (i15 == 0) {
				texTrianglesPoint1[k14] = nc1.getUnsignedShort();
				texTrianglesPoint2[k14] = nc1.getUnsignedShort();
				texTrianglesPoint3[k14] = nc1.getUnsignedShort();
			}
			if (i15 == 1) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				kb[k14] = nc3.getUnsignedShort();
				N[k14] = nc3.getUnsignedShort();
				y[k14] = nc3.getUnsignedShort();
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
			}
			if (i15 == 2) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				kb[k14] = nc3.getUnsignedShort();
				N[k14] = nc3.getUnsignedShort();
				y[k14] = nc3.getUnsignedShort();
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
				cb[k14] = nc6.getSignedByte();
				J[k14] = nc6.getSignedByte();
			}
			if (i15 == 3) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				kb[k14] = nc3.getUnsignedShort();
				N[k14] = nc3.getUnsignedShort();
				y[k14] = nc3.getUnsignedShort();
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
			}
		}
		if (i2 != 255) {
			for (int i12 = 0; i12 < numTriangles; i12++) {
				face_render_priorities[i12] = i2;
			}
		}
		anIntArray1640 = triangleColours2;
		numberOfVerticeCoordinates = numVertices;
		anInt1630 = numTriangles;
		verticesXCoordinate = vertexX;
		verticesYCoordinate = vertexY;
		verticesZCoordinate = vertexZ;
		faces_a = facePoint1;
		faces_b = facePoint2;
		faces_c = facePoint3;
	}

	private void read622Model(byte abyte0[], int modelID) {
		ByteBuffer nc1 = new ByteBuffer(abyte0);
		ByteBuffer nc2 = new ByteBuffer(abyte0);
		ByteBuffer nc3 = new ByteBuffer(abyte0);
		ByteBuffer nc4 = new ByteBuffer(abyte0);
		ByteBuffer nc5 = new ByteBuffer(abyte0);
		ByteBuffer nc6 = new ByteBuffer(abyte0);
		ByteBuffer nc7 = new ByteBuffer(abyte0);
		nc1.position = abyte0.length - 23;
		int numVertices = nc1.getUnsignedShort();
		int numTriangles = nc1.getUnsignedShort();
		int numTexTriangles = nc1.getUnsignedByte();
		int l1 = nc1.getUnsignedByte();
		boolean bool = (0x1 & l1 ^ 0xffffffff) == -2;
		boolean bool_26_ = (0x8 & l1) == 8;
		if (!bool_26_) {
			read525Model(abyte0, modelID);
			return;
		}
		int newformat = 0;
		if (bool_26_) {
			nc1.position -= 7;
			newformat = nc1.getUnsignedByte();
			scaledVertices = true;
			nc1.position += 6;
		}
		int i2 = nc1.getUnsignedByte();
		int j2 = nc1.getUnsignedByte();
		int k2 = nc1.getUnsignedByte();
		int l2 = nc1.getUnsignedByte();
		int i3 = nc1.getUnsignedByte();
		int j3 = nc1.getUnsignedShort();
		int k3 = nc1.getUnsignedShort();
		int l3 = nc1.getUnsignedShort();
		int i4 = nc1.getUnsignedShort();
		int j4 = nc1.getUnsignedShort();
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		byte[] x = null;
		byte[] O = null;
		byte[] J = null;
		byte[] F = null;
		byte[] cb = null;
		byte[] gb = null;
		byte[] lb = null;
		int[] kb = null;
		int[] y = null;
		int[] N = null;
		short[] D = null;
		int[] triangleColours2 = new int[numTriangles];
		if (numTexTriangles > 0) {
			O = new byte[numTexTriangles];
			nc1.position = 0;
			for (int j5 = 0; j5 < numTexTriangles; j5++) {
				byte byte0 = O[j5] = nc1.getSignedByte();
				if (byte0 == 0) {
					k4++;
				}
				if (byte0 >= 1 && byte0 <= 3) {
					l4++;
				}
				if (byte0 == 2) {
					i5++;
				}
			}
		}
		int k5 = numTexTriangles;
		int l5 = k5;
		k5 += numVertices;
		int i6 = k5;
		if (bool) {
			k5 += numTriangles;
		}
		if (l1 == 1) {
			k5 += numTriangles;
		}
		int j6 = k5;
		k5 += numTriangles;
		int k6 = k5;
		if (i2 == 255) {
			k5 += numTriangles;
		}
		int l6 = k5;
		if (k2 == 1) {
			k5 += numTriangles;
		}
		int i7 = k5;
		if (i3 == 1) {
			k5 += numVertices;
		}
		int j7 = k5;
		if (j2 == 1) {
			k5 += numTriangles;
		}
		int k7 = k5;
		k5 += i4;
		int l7 = k5;
		if (l2 == 1) {
			k5 += numTriangles * 2;
		}
		int i8 = k5;
		k5 += j4;
		int j8 = k5;
		k5 += numTriangles * 2;
		int k8 = k5;
		k5 += j3;
		int l8 = k5;
		k5 += k3;
		int i9 = k5;
		k5 += l3;
		int j9 = k5;
		k5 += k4 * 6;
		int k9 = k5;
		k5 += l4 * 6;
		int i_59_ = 6;
		if (newformat != 14) {
			if (newformat >= 15) {
				i_59_ = 9;
			}
		} else {
			i_59_ = 7;
		}
		int l9 = k5;
		k5 += i_59_ * l4;
		int i10 = k5;
		k5 += l4;
		int j10 = k5;
		k5 += l4;
		int k10 = k5;
		k5 += l4 + i5 * 2;
		verticesParticle = new int[numVertices];
		int[] vertexX = new int[numVertices];
		int[] vertexY = new int[numVertices];
		int[] vertexZ = new int[numVertices];
		int[] facePoint1 = new int[numTriangles];
		int[] facePoint2 = new int[numTriangles];
		int[] facePoint3 = new int[numTriangles];
		anIntArray1655 = new int[numVertices];
		face_render_type = new int[numTriangles];
		face_render_priorities = new int[numTriangles];
		faces_alpha = new int[numTriangles];
		anIntArray1656 = new int[numTriangles];
		if (i3 == 1) {
			anIntArray1655 = new int[numVertices];
		}
		if (bool) {
			face_render_type = new int[numTriangles];
		}
		if (i2 == 255) {
			face_render_priorities = new int[numTriangles];
		} else {
		}
		if (j2 == 1) {
			faces_alpha = new int[numTriangles];
		}
		if (k2 == 1) {
			anIntArray1656 = new int[numTriangles];
		}
		if (l2 == 1) {
			D = new short[numTriangles];
		}
		if (l2 == 1 && numTexTriangles > 0) {
			x = new byte[numTriangles];
		}
		triangleColours2 = new int[numTriangles];
		int[] texTrianglesPoint1 = null;
		int[] texTrianglesPoint2 = null;
		int[] texTrianglesPoint3 = null;
		if (numTexTriangles > 0) {
			texTrianglesPoint1 = new int[numTexTriangles];
			texTrianglesPoint2 = new int[numTexTriangles];
			texTrianglesPoint3 = new int[numTexTriangles];
			if (l4 > 0) {
				kb = new int[l4];
				N = new int[l4];
				y = new int[l4];
				gb = new byte[l4];
				lb = new byte[l4];
				F = new byte[l4];
			}
			if (i5 > 0) {
				cb = new byte[i5];
				J = new byte[i5];
			}
		}
		nc1.position = l5;
		nc2.position = k8;
		nc3.position = l8;
		nc4.position = i9;
		nc5.position = i7;
		int l10 = 0;
		int i11 = 0;
		int j11 = 0;
		for (int k11 = 0; k11 < numVertices; k11++) {
			int l11 = nc1.getUnsignedByte();
			int j12 = 0;
			if ((l11 & 1) != 0) {
				j12 = nc2.method421();
			}
			int l12 = 0;
			if ((l11 & 2) != 0) {
				l12 = nc3.method421();
			}
			int j13 = 0;
			if ((l11 & 4) != 0) {
				j13 = nc4.method421();
			}
			vertexX[k11] = l10 + j12;
			vertexY[k11] = i11 + l12;
			vertexZ[k11] = j11 + j13;
			l10 = vertexX[k11];
			i11 = vertexY[k11];
			j11 = vertexZ[k11];
			if (anIntArray1655 != null) {
				anIntArray1655[k11] = nc5.getUnsignedByte();
			}
		}
		nc1.position = j8;
		nc2.position = i6;
		nc3.position = k6;
		nc4.position = j7;
		nc5.position = l6;
		nc6.position = l7;
		nc7.position = i8;
		for (int i12 = 0; i12 < numTriangles; i12++) {
			triangleColours2[i12] = nc1.getUnsignedShort();
			if (l1 == 1) {
				face_render_type[i12] = nc2.getSignedByte();
				if (face_render_type[i12] == 2) {
					triangleColours2[i12] = 65535;
				}
				face_render_type[i12] = 0;
			}
			if (i2 == 255) {
				face_render_priorities[i12] = nc3.getSignedByte();
			}
			if (j2 == 1) {
				faces_alpha[i12] = nc4.getSignedByte();
				if (faces_alpha[i12] < 0) {
					faces_alpha[i12] = 256 + faces_alpha[i12];
				}
			}
			if (k2 == 1) {
				anIntArray1656[i12] = nc5.getUnsignedByte();
			}
			if (l2 == 1) {
				D[i12] = (short) (nc6.getUnsignedShort() - 1);
			}
			if (x != null) {
				if (D[i12] != -1) {
					x[i12] = (byte) (nc7.getUnsignedByte() - 1);
				} else {
					x[i12] = -1;
				}
			}
		}
		nc1.position = k7;
		nc2.position = j6;
		int k12 = 0;
		int i13 = 0;
		int k13 = 0;
		int l13 = 0;
		for (int i14 = 0; i14 < numTriangles; i14++) {
			int j14 = nc2.getUnsignedByte();
			if (j14 == 1) {
				k12 = nc1.method421() + l13;
				l13 = k12;
				i13 = nc1.method421() + l13;
				l13 = i13;
				k13 = nc1.method421() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 2) {
				i13 = k13;
				k13 = nc1.method421() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 3) {
				k12 = k13;
				k13 = nc1.method421() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 4) {
				int l14 = k12;
				k12 = i13;
				i13 = l14;
				k13 = nc1.method421() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
		}
		nc1.position = j9;
		nc2.position = k9;
		nc3.position = l9;
		nc4.position = i10;
		nc5.position = j10;
		nc6.position = k10;
		for (int k14 = 0; k14 < numTexTriangles; k14++) {
			int i15 = O[k14] & 0xff;
			if (i15 == 0) {
				texTrianglesPoint1[k14] = nc1.getUnsignedShort();
				texTrianglesPoint2[k14] = nc1.getUnsignedShort();
				texTrianglesPoint3[k14] = nc1.getUnsignedShort();
			}
			if (i15 == 1) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();

				if (newformat < 15) {
					kb[k14] = nc3.getUnsignedShort();

					if (newformat >= 14) {
						N[k14] = nc3.getTribyte(-1);
					} else {
						N[k14] = nc3.getUnsignedShort();
					}

					y[k14] = nc3.getUnsignedShort();
				} else {
					kb[k14] = nc3.getTribyte(-1);
					N[k14] = nc3.getTribyte(-1);
					y[k14] = nc3.getTribyte(-1);
				}

				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
			}
			if (i15 == 2) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();

				if (newformat >= 15) {
					kb[k14] = nc3.getTribyte(-1);
					N[k14] = nc3.getTribyte(-1);
					y[k14] = nc3.getTribyte(-1);
				} else {
					kb[k14] = nc3.getUnsignedShort();
					if (newformat < 14) {
						N[k14] = nc3.getUnsignedShort();
					} else {
						N[k14] = nc3.getTribyte(-1);
					}
					y[k14] = nc3.getUnsignedShort();
				}
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
				cb[k14] = nc6.getSignedByte();
				J[k14] = nc6.getSignedByte();
			}
			if (i15 == 3) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				if (newformat < 15) {
					kb[k14] = nc3.getUnsignedShort();
					if (newformat < 14) {
						N[k14] = nc3.getUnsignedShort();
					} else {
						N[k14] = nc3.getTribyte(-1);
					}
					y[k14] = nc3.getUnsignedShort();
				} else {
					kb[k14] = nc3.getTribyte(-1);
					N[k14] = nc3.getTribyte(-1);
					y[k14] = nc3.getTribyte(-1);
				}
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
			}
		}
		if (i2 != 255) {
			for (int i12 = 0; i12 < numTriangles; i12++) {
				face_render_priorities[i12] = i2;
			}
		}
		anIntArray1640 = triangleColours2;
		numberOfVerticeCoordinates = numVertices;
		anInt1630 = numTriangles;
		verticesXCoordinate = vertexX;
		verticesYCoordinate = vertexY;
		verticesZCoordinate = vertexZ;
		faces_a = facePoint1;
		faces_b = facePoint2;
		faces_c = facePoint3;
		if (!scaledVertices) {
			downscale();
		}
		translate(0, 6, 0);
		if (face_render_priorities != null) {
			for (int j = 0; j < face_render_priorities.length; j++) {
				face_render_priorities[j] = 10;
			}
		}
	}


	public int numberOfTriangleFaces;

	public void setTexture(int fromColor, int fromcolor, int tex) {
		int foundAmt = 0;
		int set2 = 0;
		anInt1642 = foundAmt;
		if (face_render_type == null) {
			face_render_type = new int[foundAmt];
		}
		if (anIntArray1640 == null) {
			anIntArray1640 = new int[foundAmt];
		}
		texture_face_x = new int[foundAmt];
		texture_face_y = new int[foundAmt];
		texture_face_z = new int[foundAmt];


		for (int i = 0; i < numberOfTriangleFaces; i++) {
			if (anIntArray1640[i] >= fromColor && anIntArray1640[i] <= fromcolor) {


				anIntArray1640[i] = tex;
				face_render_type[i] = 3 + set2;
				set2 += 4;
			}
		}
	}

	private void readOldModel(byte[] data) {
		aBoolean1659 = false;
		numberOfVerticeCoordinates = 0;
		ByteBuffer first = new ByteBuffer(data);
		ByteBuffer second = new ByteBuffer(data);
		ByteBuffer third = new ByteBuffer(data);
		ByteBuffer fourth = new ByteBuffer(data);
		ByteBuffer fifth = new ByteBuffer(data);
		first.position = data.length - 18;
		int i = first.readUnsignedWord();
		int i_320_ = first.readUnsignedWord();
		int i_321_ = first.getUnsignedByte();
		int i_322_ = first.getUnsignedByte();
		int i_323_ = first.getUnsignedByte();
		int i_324_ = first.getUnsignedByte();
		int i_325_ = first.getUnsignedByte();
		int i_326_ = first.getUnsignedByte();
		int i_327_ = first.readUnsignedWord();
		int i_328_ = first.readUnsignedWord();
		int i_329_ = first.readUnsignedWord();
		int i_330_ = first.readUnsignedWord();
		int i_331_ = 0;
		int i_332_ = i_331_;
		i_331_ += i;
		int i_333_ = i_331_;
		i_331_ += i_320_;
		int i_334_ = i_331_;
		if (i_323_ == 255) {
			i_331_ += i_320_;
		} else {
			i_334_ = -i_323_ - 1;
		}
		int i_335_ = i_331_;
		if (i_325_ == 1) {
			i_331_ += i_320_;
		} else {
			i_335_ = -1;
		}
		int i_336_ = i_331_;
		if (i_322_ == 1) {
			i_331_ += i_320_;
		} else {
			i_336_ = -1;
		}
		int i_337_ = i_331_;
		if (i_326_ == 1) {
			i_331_ += i;
		} else {
			i_337_ = -1;
		}
		int i_338_ = i_331_;
		if (i_324_ == 1) {
			i_331_ += i_320_;
		} else {
			i_338_ = -1;
		}
		int i_339_ = i_331_;
		i_331_ += i_330_;
		int i_340_ = i_331_;
		i_331_ += i_320_ * 2;
		int i_341_ = i_331_;
		i_331_ += i_321_ * 6;
		int i_342_ = i_331_;
		i_331_ += i_327_;
		int i_343_ = i_331_;
		i_331_ += i_328_;
		int i_344_ = i_331_;
		i_331_ += i_329_;
		numberOfVerticeCoordinates = i;
		anInt1630 = i_320_;
		anInt1642 = i_321_;
		verticesParticle = new int[i];
		verticesXCoordinate = new int[i];
		verticesYCoordinate = new int[i];
		verticesZCoordinate = new int[i];
		faces_a = new int[i_320_];
		faces_b = new int[i_320_];
		faces_c = new int[i_320_];
		texture_face_x = new int[i_321_];
		texture_face_y = new int[i_321_];
		texture_face_z = new int[i_321_];
		if (i_337_ >= 0) {
			anIntArray1655 = new int[i];
		}
		if (i_336_ >= 0) {
			face_render_type = new int[i_320_];
		}
		if (i_334_ >= 0) {
			face_render_priorities = new int[i_320_];
		} else {
			anInt1641 = -i_334_ - 1;
		}
		if (i_338_ >= 0) {
			faces_alpha = new int[i_320_];
		}
		if (i_335_ >= 0) {
			anIntArray1656 = new int[i_320_];
		}
		anIntArray1640 = new int[i_320_];
		first.position = i_332_;
		second.position = i_342_;
		third.position = i_343_;
		fourth.position = i_344_;
		fifth.position = i_337_;
		int i_345_ = 0;
		int i_346_ = 0;
		int i_347_ = 0;
		for (int i_348_ = 0; i_348_ < i; i_348_++) {
			int i_349_ = first.getUnsignedByte();
			int i_350_ = 0;
			if ((i_349_ & 0x1) != 0) {
				i_350_ = second.method421();
			}
			int i_351_ = 0;
			if ((i_349_ & 0x2) != 0) {
				i_351_ = third.method421();
			}
			int i_352_ = 0;
			if ((i_349_ & 0x4) != 0) {
				i_352_ = fourth.method421();
			}
			verticesXCoordinate[i_348_] = i_345_ + i_350_;
			verticesYCoordinate[i_348_] = i_346_ + i_351_;
			verticesZCoordinate[i_348_] = i_347_ + i_352_;
			i_345_ = verticesXCoordinate[i_348_];
			i_346_ = verticesYCoordinate[i_348_];
			i_347_ = verticesZCoordinate[i_348_];
			if (anIntArray1655 != null) {
				anIntArray1655[i_348_] = fifth.getUnsignedByte();
			}
		}
		first.position = i_340_;
		second.position = i_336_;
		third.position = i_334_;
		fourth.position = i_338_;
		fifth.position = i_335_;
		for (int i_353_ = 0; i_353_ < i_320_; i_353_++) {
			anIntArray1640[i_353_] = first.readUnsignedWord();
			if (face_render_type != null) {
				face_render_type[i_353_] = second.getUnsignedByte();
			}
			if (face_render_priorities != null) {
				face_render_priorities[i_353_] = third.getUnsignedByte();
			}
			if (faces_alpha != null) {
				faces_alpha[i_353_] = fourth.getUnsignedByte();
			}
			if (anIntArray1656 != null) {
				anIntArray1656[i_353_] = fifth.getUnsignedByte();
			}
		}
		first.position = i_339_;
		second.position = i_333_;
		int i_354_ = 0;
		int i_355_ = 0;
		int i_356_ = 0;
		int i_357_ = 0;
		for (int i_358_ = 0; i_358_ < i_320_; i_358_++) {
			int i_359_ = second.getUnsignedByte();
			if (i_359_ == 1) {
				i_354_ = first.method421() + i_357_;
				i_357_ = i_354_;
				i_355_ = first.method421() + i_357_;
				i_357_ = i_355_;
				i_356_ = first.method421() + i_357_;
				i_357_ = i_356_;
				faces_a[i_358_] = i_354_;
				faces_b[i_358_] = i_355_;
				faces_c[i_358_] = i_356_;
			}
			if (i_359_ == 2) {
				i_355_ = i_356_;
				i_356_ = first.method421() + i_357_;
				i_357_ = i_356_;
				faces_a[i_358_] = i_354_;
				faces_b[i_358_] = i_355_;
				faces_c[i_358_] = i_356_;
			}
			if (i_359_ == 3) {
				i_354_ = i_356_;
				i_356_ = first.method421() + i_357_;
				i_357_ = i_356_;
				faces_a[i_358_] = i_354_;
				faces_b[i_358_] = i_355_;
				faces_c[i_358_] = i_356_;
			}
			if (i_359_ == 4) {
				int i_360_ = i_354_;
				i_354_ = i_355_;
				i_355_ = i_360_;
				i_356_ = first.method421() + i_357_;
				i_357_ = i_356_;
				faces_a[i_358_] = i_354_;
				faces_b[i_358_] = i_355_;
				faces_c[i_358_] = i_356_;
			}
		}
		first.position = i_341_;
		for (int i_361_ = 0; i_361_ < i_321_; i_361_++) {
			texture_face_x[i_361_] = first.readUnsignedWord();
			texture_face_y[i_361_] = first.readUnsignedWord();
			texture_face_z[i_361_] = first.readUnsignedWord();
		}

		if (i == 83801 || i == 98005) {
			if (face_render_priorities != null) {
				for (int j1 = 0; j1 < face_render_priorities.length; j1++) {
					face_render_priorities[j1] = 11;
				}
			}
		}
	}

	//
	public void setTexture(int fromColor, int tex) {
		int foundAmt = 0;
		int set2 = 0;
		for (int i = 0; i < anIntArray1640.length; i++) {
			if (fromColor == anIntArray1640[i]) {
				foundAmt++;
			}
		}
		anInt1642 = foundAmt;
		if (face_render_type == null) {
			face_render_type = new int[foundAmt];
		}
		if (anIntArray1640 == null) {
			anIntArray1640 = new int[foundAmt];
		}
		texture_face_x = new int[foundAmt];
		texture_face_y = new int[foundAmt];
		texture_face_z = new int[foundAmt];
		int assigned = 0;
		for (int i = 0; i < anInt1630; i++) {
			if (fromColor == anIntArray1640[i]) {
				anIntArray1640[i] = tex;
				face_render_type[i] = 3 + set2;
				set2 += 4;
				texture_face_x[assigned] = faces_a[i];
				texture_face_y[assigned] = faces_b[i];
				texture_face_z[assigned] = faces_c[i];
				assigned++;
			}
		}
	}

	public void setTexture(int tex) {
		anInt1642 = anInt1630;
		int set2 = 0;
		if (face_render_type == null) {
			face_render_type = new int[anInt1630];
		}
		if (anIntArray1640 == null) {
			anIntArray1640 = new int[anInt1630];
		}
		texture_face_x = new int[anInt1630];
		texture_face_y = new int[anInt1630];
		texture_face_z = new int[anInt1630];

		for (int i = 0; i < anInt1630; i++) {
			anIntArray1640[i] = tex;
			face_render_type[i] = 3 + set2;
			set2 += 4;
			texture_face_x[i] = faces_a[i];
			texture_face_y[i] = faces_b[i];
			texture_face_z[i] = faces_c[i];
		}
	}

	public void method1337(int j) {
		for (int k = 0; k < anInt1630; k++) {
			anIntArray1640[k] = j;
		}
	}

	public void method1338(int j) {
		j += 100;
		int kcolor = 0;
		for (int k = 0; k < anInt1630; k++) {
			kcolor = anIntArray1640[k];
			if (k + j >= 0) {
				anIntArray1640[k] = kcolor + j;
			}
		}
	}

	public void method1339(int j) {
		j += 1;
		for (int k = 0; k < anInt1630; k++) {
			if (k + j >= 0) {
				anIntArray1640[k] = k + j;
			}
		}
	}

	public void downscale() {
		for (int i = 0; i < numberOfVerticeCoordinates; ++i) {
			verticesXCoordinate[i] = verticesXCoordinate[i] + 7 >> 2;
			verticesYCoordinate[i] = verticesYCoordinate[i] + 7 >> 2;
			verticesZCoordinate[i] = verticesZCoordinate[i] + 7 >> 2;
		}
	}

	public void scale2(int i) {
		for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
			verticesXCoordinate[i1] = verticesXCoordinate[i1] >> i;
			verticesYCoordinate[i1] = verticesYCoordinate[i1] >> i;
			verticesZCoordinate[i1] = verticesZCoordinate[i1] >> i;
		}
	}
}