package org.necrotic.client.world;

import org.necrotic.Configuration;
import org.necrotic.client.*;
import org.necrotic.client.cache.definition.ItemDefinition;
import org.necrotic.client.cache.ondemand.OnDemandFetcherParent;
import org.necrotic.client.graphics.DrawingArea;
import org.necrotic.client.graphics.particles.ParticleAttachment;
import org.necrotic.client.graphics.particles.ParticleDefinition;
import org.necrotic.client.graphics.particles.Vector;
import org.necrotic.client.io.ByteBuffer;
import org.necrotic.client.renderable.Animable;

import java.awt.*;
import java.util.ArrayList;

public class Model extends Animable {

    private boolean scaledVertices;
    private int offX = 0;
    private int offY = 0;
    private int offZ = 0;
    private int lastRenderedRotation = 0;
    private int[] verticesParticle;
    private static final int POLYGON_COUNT = 20_000;
    private static final int VERTICES_COUNT = 6_000;
    public static boolean aBoolean1684;
    private static boolean aBooleanArray1663[] = new boolean[POLYGON_COUNT];
    private static boolean outOfReach[] = new boolean[POLYGON_COUNT];
    public static Class21 modelsArray[];
    public static Class21 modelsArrayOSRS[];
    public static Class21 rs3ModelsArray[];
    public static Model aModel_1621 = new Model(true);
    private static int anInt1681;
    private static int anInt1682;
    private static int anInt1683;
    public static int anInt1685;
    public static int anInt1686;// sit.
    public static int anInt1687;
    private static int anIntArray1622[] = new int[VERTICES_COUNT];
    private static int anIntArray1623[] = new int[VERTICES_COUNT];
    private static int anIntArray1624[] = new int[VERTICES_COUNT];
    private static int anIntArray1625[] = new int[VERTICES_COUNT];
    private static int anIntArray1665[] = new int[POLYGON_COUNT];
    private static int anIntArray1666[] = new int[POLYGON_COUNT];
    private static int anIntArray1667[] = new int[POLYGON_COUNT];
    private static int anIntArray1668[] = new int[POLYGON_COUNT];
    private static int anIntArray1669[] = new int[POLYGON_COUNT];
    private static int anIntArray1670[] = new int[POLYGON_COUNT];
    private static int vertexPerspectiveDepth[] = new int[POLYGON_COUNT];
    private static int depthListIndices[] = new int[1800];
    private static int anIntArray1673[] = new int[12];
    private static int anIntArray1675[] = new int[VERTICES_COUNT];
    private static int anIntArray1676[] = new int[VERTICES_COUNT];
    private static int anIntArray1677[] = new int[12];
    private static int anIntArray1678[] = new int[10];
    private static int anIntArray1679[] = new int[10];
    private static int anIntArray1680[] = new int[10];
    public static int mapObjIds[] = new int[1300];
    public static int anIntArray1688[] = new int[1200];
    private static int faceLists[][] = new int[8000][512];
    private static int anIntArrayArray1674[][] = new int[12][VERTICES_COUNT];
    private static OnDemandFetcherParent aOnDemandFetcherParent_1662;
    public static int SINE[];
    public static int COSINE[];
    private static int[] modelIntArray3;
    private static int[] modelIntArray4;
    private static boolean[] newmodel;
    public static int modelTriangleCounts[];

    static {
        SINE = Rasterizer.SINE;
        COSINE = Rasterizer.COSINE;
        modelIntArray3 = Rasterizer.anIntArray1482;
        modelIntArray4 = Rasterizer.anIntArray1469;
    }


    public static boolean tooManyPolygons(int modelId) {
        return (modelId < 0) ? false
                : (modelId >= modelTriangleCounts.length) ? false
                : modelTriangleCounts[modelId] > Configuration.MAX_POLY_MODELS;
    }

    public boolean tooManyPolygons() {
        return numberOfTriangleFaces >= Configuration.MAX_POLY_MODELS;
    }

    public static void method459(int i, OnDemandFetcherParent onDemandFetcherParent) {
        modelTriangleCounts = new int[150_000];
        modelsArray = new Class21[141000];
        modelsArrayOSRS = new Class21[100000];
        rs3ModelsArray = new Class21[141000];
        newmodel = new boolean[141000];
        aOnDemandFetcherParent_1662 = onDemandFetcherParent;
    }

    public static void method460(byte[] tmp, int i, Class21[] array) {
        try {
            if (tmp == null) {
                Class21 class21 = array[i] = new Class21();
                class21.anInt369 = 0;
                class21.anInt370 = 0;
                class21.anInt371 = 0;
                return;
            }

            ByteBuffer buffer = new ByteBuffer(tmp);
            buffer.position = tmp.length - 18;
            Class21 class21_1 = array[i] = new Class21();
            class21_1.aByteArray368 = tmp;
            class21_1.anInt369 = buffer.getUnsignedShort();
            class21_1.anInt370 = buffer.getUnsignedShort();
            class21_1.anInt371 = buffer.getUnsignedByte();
            int k = buffer.getUnsignedByte();
            int l = buffer.getUnsignedByte();
            int i1 = buffer.getUnsignedByte();
            int j1 = buffer.getUnsignedByte();
            int k1 = buffer.getUnsignedByte();
            int l1 = buffer.getUnsignedShort();
            int i2 = buffer.getUnsignedShort();
            int j2 = buffer.getUnsignedShort();
            int k2 = buffer.getUnsignedShort();
            int l2 = 0;
            class21_1.anInt372 = l2;
            l2 += class21_1.anInt369;
            class21_1.anInt378 = l2;
            l2 += class21_1.anInt370;
            class21_1.anInt381 = l2;

            if (l == 255) {
                l2 += class21_1.anInt370;
            } else {
                class21_1.anInt381 = -l - 1;
            }

            class21_1.anInt383 = l2;

            if (j1 == 1) {
                l2 += class21_1.anInt370;
            } else {
                class21_1.anInt383 = -1;
            }

            class21_1.anInt380 = l2;

            if (k == 1) {
                l2 += class21_1.anInt370;
            } else {
                class21_1.anInt380 = -1;
            }

            class21_1.anInt376 = l2;

            if (k1 == 1) {
                l2 += class21_1.anInt369;
            } else {
                class21_1.anInt376 = -1;
            }

            class21_1.anInt382 = l2;

            if (i1 == 1) {
                l2 += class21_1.anInt370;
            } else {
                class21_1.anInt382 = -1;
            }

            class21_1.anInt377 = l2;
            l2 += k2;
            class21_1.anInt379 = l2;
            l2 += class21_1.anInt370 * 2;
            class21_1.anInt384 = l2;
            l2 += class21_1.anInt371 * 6;
            class21_1.anInt373 = l2;
            l2 += l1;
            class21_1.anInt374 = l2;
            l2 += i2;
            class21_1.anInt375 = l2;
            l2 += j2;
        } catch (Exception _ex) {
        }
    }

    public static Model fetchModel(int j, boolean rs3, boolean osrs) {
        if (!rs3 && !osrs && modelsArray == null) {
            return null;
        }
        if (rs3 && rs3ModelsArray == null) {
            return null;
        }
        if (osrs && modelsArrayOSRS == null) {
            return null;
        }
        if (j == 0)
            return null;
        Class21 class21 = osrs ? modelsArrayOSRS[j] :  rs3 ? rs3ModelsArray[j] : modelsArray[j];

        if (class21 == null) {
            aOnDemandFetcherParent_1662.get(osrs ? Client.OSRS_MODEL_IDX - 1 : Client.MODEL_IDX - 1, j & 0xffff);
            return null;
        } else {
            return new Model(j, rs3, osrs);
        }
    }

    public static Model fetchModel(int j) {
        return fetchModel(j, false, false);
    }

    public static boolean method463(int i) {
        return method463(i, false, false);
    }

    public static boolean method463(int i, boolean rs3, boolean osrs) {
        if (!rs3 && !osrs && modelsArray == null) {
            return false;
        }
        if (osrs && modelsArrayOSRS == null) {
            return false;
        }
        if (rs3 && rs3ModelsArray == null) {
            return false;
        }

        Class21 class21 = osrs ? modelsArrayOSRS[i] : rs3 ? rs3ModelsArray[i] : modelsArray[i];

        if (class21 == null) {
            aOnDemandFetcherParent_1662.get(osrs ? Client.OSRS_MODEL_IDX - 1 : Client.MODEL_IDX - 1, i);
            return false;
        } else {
            return true;
        }
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
        modelsArray = null;
        modelsArrayOSRS = null;
        rs3ModelsArray = null;
        aBooleanArray1663 = null;
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
        SINE = null;
        COSINE = null;
        modelIntArray3 = null;
        modelIntArray4 = null;
    }

    private boolean aBoolean1618;
    public boolean aBoolean1659;
    public Class33 aClass33Array1660[];
    public int numberOfVerticeCoordinates;
    public int anInt1630;
    private int anInt1641;
    private int texture_faces;
    public int anInt1646;
    public int anInt1647;
    public int anInt1648;
    public int anInt1649;
    public int anInt1650;
    public int anInt1651;
    private int diagonal3D;
    private int anInt1653;
    public int anInt1654;
    public int vertex_position_x[];
    public int vertex_position_y[];
    public int vertex_position_z[];
    public int triangle_edge_a[];
    public int triangle_edge_b[];
    public int triangle_edge_c[];
    private int anIntArray1634[];
    private int anIntArray1635[];
    private int anIntArray1636[];
    public int render_type[];
    private int face_render_priorities[];
    private int anIntArray1639[];
    public int face_color[];
    private int texture_edge_a[];
    private int texture_edge_b[];
    private int texture_edge_c[];
    private int bone_skin[];
    private int muscle_skin[];
    public int vertexSkin[][];
    public int triangleSkin[][];
    int highest;

    private Model(boolean flag) {
        aBoolean1618 = true;
        aBoolean1659 = false;

        if (!flag) {
            aBoolean1618 = !aBoolean1618;
        }
    }


    public void copy(Model model, boolean shareAlpha) {

        aClass33Array1660 = model.aClass33Array1660;
        anInt1646 = model.anInt1646;
        anInt1647 = model.anInt1647;
        anInt1648 = model.anInt1648;
        anInt1649 = model.anInt1649;
        anInt1650 = model.anInt1650;
        anInt1651 = model.anInt1651;
        diagonal3D = model.diagonal3D;
        anInt1653 = model.anInt1653;
        anInt1654 = model.anInt1654;
        anIntArray1634 = model.anIntArray1634;
        anIntArray1635 = model.anIntArray1635;
        anIntArray1636 = model.anIntArray1636;

        face_color = model.face_color;
        texture_edge_a = model.texture_edge_a;
        texture_edge_b = model.texture_edge_b;
        texture_edge_c = model.texture_edge_c;

        vertexSkin = model.vertexSkin;
        triangleSkin = model.triangleSkin;

        aBoolean1618 = true;
        aBoolean1659 = false;
        numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
        anInt1630 = model.anInt1630;
        texture_faces = model.texture_faces;

        verticesParticle = new int[numberOfVerticeCoordinates];
        vertex_position_x = new int[numberOfVerticeCoordinates];
        vertex_position_y = new int[numberOfVerticeCoordinates];
        vertex_position_z = new int[numberOfVerticeCoordinates];

        for (int j = 0; j < numberOfVerticeCoordinates; j++) {
            verticesParticle[j] = model.verticesParticle[j];
            vertex_position_x[j] = model.vertex_position_x[j];
            vertex_position_y[j] = model.vertex_position_y[j];
            vertex_position_z[j] = model.vertex_position_z[j];
        }

        face_color = new int[anInt1630];

        for (int k = 0; k < anInt1630; k++) {
            face_color[k] = model.face_color[k];
        }

        anIntArray1639 = new int[anInt1630];

        if (model.anIntArray1639 == null) {
            for (int l = 0; l < anInt1630; l++) {
                anIntArray1639[l] = 0;
            }
        } else {
            for (int i1 = 0; i1 < anInt1630; i1++) {
                anIntArray1639[i1] = model.anIntArray1639[i1];
            }
        }

        bone_skin = model.bone_skin;
        muscle_skin = model.muscle_skin;
        render_type = model.render_type;
        triangle_edge_a = model.triangle_edge_a;
        triangle_edge_b = model.triangle_edge_b;
        triangle_edge_c = model.triangle_edge_c;
        face_render_priorities = model.face_render_priorities;
        anInt1641 = model.anInt1641;
        texture_edge_a = model.texture_edge_a;
        texture_edge_b = model.texture_edge_b;
        texture_edge_c = model.texture_edge_c;
        scaledVertices = model.scaledVertices;
    }

    public Model(boolean flag, boolean flag1, boolean flag2, Model model) {
        aBoolean1618 = true;
        aBoolean1659 = false;
        numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
        anInt1630 = model.anInt1630;
        texture_faces = model.texture_faces;

        if (flag2) {
            verticesParticle = model.verticesParticle;
            vertex_position_x = model.vertex_position_x;
            vertex_position_y = model.vertex_position_y;
            vertex_position_z = model.vertex_position_z;
        } else {
            verticesParticle = new int[numberOfVerticeCoordinates];
            vertex_position_x = new int[numberOfVerticeCoordinates];
            vertex_position_y = new int[numberOfVerticeCoordinates];
            vertex_position_z = new int[numberOfVerticeCoordinates];

            for (int j = 0; j < numberOfVerticeCoordinates; j++) {
                verticesParticle[j] = model.verticesParticle[j];
                vertex_position_x[j] = model.vertex_position_x[j];
                vertex_position_y[j] = model.vertex_position_y[j];
                vertex_position_z[j] = model.vertex_position_z[j];
            }
        }

        if (flag) {
            face_color = model.face_color;
        } else {
            face_color = new int[anInt1630];

            for (int k = 0; k < anInt1630; k++) {
                face_color[k] = model.face_color[k];
            }
        }

        if (flag1) {
            anIntArray1639 = model.anIntArray1639;
        } else {
            anIntArray1639 = new int[anInt1630];

            if (model.anIntArray1639 == null) {
                for (int l = 0; l < anInt1630; l++) {
                    anIntArray1639[l] = 0;
                }
            } else {
                for (int i1 = 0; i1 < anInt1630; i1++) {
                    anIntArray1639[i1] = model.anIntArray1639[i1];
                }
            }
        }

        bone_skin = model.bone_skin;
        muscle_skin = model.muscle_skin;
        render_type = model.render_type;
        triangle_edge_a = model.triangle_edge_a;
        triangle_edge_b = model.triangle_edge_b;
        triangle_edge_c = model.triangle_edge_c;
        face_render_priorities = model.face_render_priorities;
        anInt1641 = model.anInt1641;
        texture_edge_a = model.texture_edge_a;
        texture_edge_b = model.texture_edge_b;
        texture_edge_c = model.texture_edge_c;
        scaledVertices = model.scaledVertices;
    }

    public Model(boolean flag, boolean flag1, Model model) {
        aBoolean1618 = true;
        aBoolean1659 = false;
        numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
        anInt1630 = model.anInt1630;
        texture_faces = model.texture_faces;

        if (flag) {
            vertex_position_y = new int[numberOfVerticeCoordinates];

            for (int j = 0; j < numberOfVerticeCoordinates; j++) {
                vertex_position_y[j] = model.vertex_position_y[j];
            }
        } else {
            vertex_position_y = model.vertex_position_y;
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

            render_type = new int[anInt1630];

            if (model.render_type == null) {
                for (int l = 0; l < anInt1630; l++) {
                    render_type[l] = 0;
                }
            } else {
                for (int i1 = 0; i1 < anInt1630; i1++) {
                    render_type[i1] = model.render_type[i1];
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
            render_type = model.render_type;
        }

        verticesParticle = model.verticesParticle;
        vertex_position_x = model.vertex_position_x;
        vertex_position_z = model.vertex_position_z;
        face_color = model.face_color;
        anIntArray1639 = model.anIntArray1639;
        face_render_priorities = model.face_render_priorities;
        anInt1641 = model.anInt1641;
        triangle_edge_a = model.triangle_edge_a;
        triangle_edge_b = model.triangle_edge_b;
        triangle_edge_c = model.triangle_edge_c;
        texture_edge_a = model.texture_edge_a;
        texture_edge_b = model.texture_edge_b;
        texture_edge_c = model.texture_edge_c;
        super.modelHeight = model.modelHeight;
        anInt1650 = model.anInt1650;
        anInt1653 = model.anInt1653;
        diagonal3D = model.diagonal3D;
        anInt1646 = model.anInt1646;
        anInt1648 = model.anInt1648;
        anInt1649 = model.anInt1649;
        anInt1647 = model.anInt1647;
        scaledVertices = model.scaledVertices;
    }

    private Model(int modelId) {
        this(modelId, false, false);
    }

    boolean post800(byte[] data) {
        return data[0] == 1;
    }

    boolean pre800(byte[] data) {
        return data[data.length - 1] == -1 && data[data.length - 2] == -1;
    }

    private Model(int modelId, boolean rs3, boolean osrs) {

        byte[] is = osrs ? modelsArrayOSRS[modelId].aByteArray368 : rs3 ? rs3ModelsArray[modelId].aByteArray368 : modelsArray[modelId].aByteArray368;
        boolean null_textures = false;



        if (osrs) {
            if (face_render_priorities != null) {
                for (int z = 0; z < this.face_render_priorities.length; z++) {
                    this.face_render_priorities[z] = 10;
                }
            }
        }

        if (rs3) {
            decode876(is, modelId);
            null_textures = true;
        } else {
            if (is[is.length - 1] == -1 && is[is.length - 2] == -1) {
                read622Model(is, modelId);
            } else {

                    readOldModel(osrs ? modelsArrayOSRS : rs3 ? rs3ModelsArray : modelsArray, modelId);
            }
        }

        modelTriangleCounts[modelId] = numberOfVerticeCoordinates;

        /*
         * PRI FIX what does ths code doe?
         */
        int[] priorityFix = new int[]{
                64448, 100_009, 130568, 130565, 131486, 65169};
        for (int z : priorityFix) {
            if (modelId == z) {
                if (face_render_priorities == null) {
                    face_render_priorities = new int[anInt1630];
                }
                for (z = 0; z < this.face_render_priorities.length; z++) {
                    this.face_render_priorities[z] = 10;
                }
            }
        }


        if (face_render_priorities == null) {
            face_render_priorities = new int[anInt1630];
        }
        for (int z = 0; z < this.face_render_priorities.length; z++) {
            this.face_render_priorities[z] = 10;
        }


        int[][] attachments = ParticleAttachment.getAttachments(modelId);

        if (attachments != null) {
            for (int[] attach : attachments) {
                if (attach[0] == -1) {
                    for (int i : triangle_edge_a) verticesParticle[i] = attach[1] + 1;
                } else if (attach[0] == -2) {
                    for (int i : triangle_edge_b) verticesParticle[i] = attach[1] + 1;
                } else if (attach[0] == -3) {
                    for (int i : triangle_edge_c) verticesParticle[i] = attach[1] + 1;
                } else if (attach[0] == -4) {
                    for (int i : triangle_edge_a) verticesParticle[i] = attach[1] + 1;
                    for (int i : triangle_edge_b) verticesParticle[i] = attach[1] + 1;
                    for (int i : triangle_edge_c) verticesParticle[i] = attach[1] + 1;
                } else {
                    verticesParticle[attach[0]] = attach[1] + 1;
                }
            }
        }

        if (null_textures) {
            texture_faces = 0;
        }

    }

    public void recolour(int i, int j) {
        for (int k = 0; k < numberOfTriangleFaces; k++)
            if (face_color[k] == i) {
                face_color[k] = j;
            }
    }

    public Model(int i, Model amodel[]) {
        try {
            aBoolean1618 = true;
            aBoolean1659 = false;
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            numberOfVerticeCoordinates = 0;
            anInt1630 = 0;
            texture_faces = 0;
            anInt1641 = -1;

            for (int k = 0; k < i; k++) {
                Model model = amodel[k];

                if (model != null) {
                    numberOfVerticeCoordinates += model.numberOfVerticeCoordinates;
                    anInt1630 += model.anInt1630;
                    texture_faces += model.texture_faces;
                    flag |= model.render_type != null;

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

                    flag2 |= model.anIntArray1639 != null;
                    flag3 |= model.muscle_skin != null;
                    scaledVertices |= model.scaledVertices;
                }
            }

            verticesParticle = new int[numberOfVerticeCoordinates];
            vertex_position_x = new int[numberOfVerticeCoordinates];
            vertex_position_y = new int[numberOfVerticeCoordinates];
            vertex_position_z = new int[numberOfVerticeCoordinates];
            bone_skin = new int[numberOfVerticeCoordinates];
            triangle_edge_a = new int[anInt1630];
            triangle_edge_b = new int[anInt1630];
            triangle_edge_c = new int[anInt1630];
            texture_edge_a = new int[texture_faces];
            texture_edge_b = new int[texture_faces];
            texture_edge_c = new int[texture_faces];

            if (flag) {
                render_type = new int[anInt1630];
            }

            if (flag1) {
                face_render_priorities = new int[anInt1630];
            }

            if (flag2) {
                anIntArray1639 = new int[anInt1630];
            }

            if (flag3) {
                muscle_skin = new int[anInt1630];
            }

            face_color = new int[anInt1630];
            numberOfVerticeCoordinates = 0;
            anInt1630 = 0;
            texture_faces = 0;
            int l = 0;

            for (int i1 = 0; i1 < i; i1++) {
                Model model_1 = amodel[i1];

                if (model_1 != null) {
                    for (int j1 = 0; j1 < model_1.anInt1630; j1++) {
                        if (flag) {
                            if (model_1.render_type == null) {
                                render_type[anInt1630] = 0;
                            } else {
                                int k1 = model_1.render_type[j1];

                                if ((k1 & 2) == 2) {
                                    k1 += l << 2;
                                }

                                render_type[anInt1630] = k1;
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
                            if (model_1.anIntArray1639 == null) {
                                anIntArray1639[anInt1630] = 0;
                            } else {
                                anIntArray1639[anInt1630] = model_1.anIntArray1639[j1];
                            }
                        }

                        if (flag3 && model_1.muscle_skin != null) {
                            muscle_skin[anInt1630] = model_1.muscle_skin[j1];
                        }

                        face_color[anInt1630] = model_1.face_color[j1];

                        triangle_edge_a[anInt1630] = method465(model_1, model_1.triangle_edge_a[j1]);
                        triangle_edge_b[anInt1630] = method465(model_1, model_1.triangle_edge_b[j1]);
                        triangle_edge_c[anInt1630] = method465(model_1, model_1.triangle_edge_c[j1]);

                        anInt1630++;
                    }

                    for (int l1 = 0; l1 < model_1.texture_faces; l1++) {
                        texture_edge_a[texture_faces] = method465(model_1, model_1.texture_edge_a[l1]);
                        texture_edge_b[texture_faces] = method465(model_1, model_1.texture_edge_b[l1]);
                        texture_edge_c[texture_faces] = method465(model_1, model_1.texture_edge_c[l1]);
                        texture_faces++;
                    }

                    l += model_1.texture_faces;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        texture_faces = 0;
        anInt1641 = -1;

        for (int k = 0; k < i; k++) {
            Model model = amodel[k];
            if (model != null) {
                numberOfVerticeCoordinates += model.numberOfVerticeCoordinates;
                anInt1630 += model.anInt1630;
                texture_faces += model.texture_faces;
                flag1 |= model.render_type != null;
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
                flag3 |= model.anIntArray1639 != null;
                flag4 |= model.face_color != null;
                scaledVertices |= model.scaledVertices;
            }
        }

        verticesParticle = new int[numberOfVerticeCoordinates];
        vertex_position_x = new int[numberOfVerticeCoordinates];
        vertex_position_y = new int[numberOfVerticeCoordinates];
        vertex_position_z = new int[numberOfVerticeCoordinates];
        triangle_edge_a = new int[anInt1630];
        triangle_edge_b = new int[anInt1630];
        triangle_edge_c = new int[anInt1630];
        anIntArray1634 = new int[anInt1630];
        anIntArray1635 = new int[anInt1630];
        anIntArray1636 = new int[anInt1630];
        texture_edge_a = new int[texture_faces];
        texture_edge_b = new int[texture_faces];
        texture_edge_c = new int[texture_faces];
        if (flag1) {
            render_type = new int[anInt1630];
        }
        if (flag2) {
            face_render_priorities = new int[anInt1630];
        }
        if (flag3) {
            anIntArray1639 = new int[anInt1630];
        }
        if (flag4) {
            face_color = new int[anInt1630];
        }
        numberOfVerticeCoordinates = 0;
        anInt1630 = 0;
        texture_faces = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < i; j1++) {
            Model model_1 = amodel[j1];
            if (model_1 != null) {
                int k1 = numberOfVerticeCoordinates;
                for (int l1 = 0; l1 < model_1.numberOfVerticeCoordinates; l1++) {
                    int x = model_1.vertex_position_x[l1];
                    int y = model_1.vertex_position_y[l1];
                    int z = model_1.vertex_position_z[l1];
                    int p = model_1.verticesParticle[l1];
                    if (scaledVertices && !model_1.scaledVertices) {
                        x <<= 2;
                        y <<= 2;
                        z <<= 2;
                    }
                    vertex_position_x[numberOfVerticeCoordinates] = x;
                    vertex_position_y[numberOfVerticeCoordinates] = y;
                    vertex_position_z[numberOfVerticeCoordinates] = z;
                    verticesParticle[numberOfVerticeCoordinates] = p;
                    numberOfVerticeCoordinates++;
                }

                for (int i2 = 0; i2 < model_1.anInt1630; i2++) {
                    triangle_edge_a[anInt1630] = model_1.triangle_edge_a[i2] + k1;
                    triangle_edge_b[anInt1630] = model_1.triangle_edge_b[i2] + k1;
                    triangle_edge_c[anInt1630] = model_1.triangle_edge_c[i2] + k1;
                    anIntArray1634[anInt1630] = model_1.anIntArray1634[i2];
                    anIntArray1635[anInt1630] = model_1.anIntArray1635[i2];
                    anIntArray1636[anInt1630] = model_1.anIntArray1636[i2];
                    if (flag1) {
                        if (model_1.render_type == null) {
                            render_type[anInt1630] = 0;
                        } else {
                            int j2 = model_1.render_type[i2];
                            if ((j2 & 2) == 2) {
                                j2 += i1 << 2;
                            }
                            render_type[anInt1630] = j2;
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
                        if (model_1.anIntArray1639 == null) {
                            anIntArray1639[anInt1630] = 0;
                        } else {
                            anIntArray1639[anInt1630] = model_1.anIntArray1639[i2];
                        }
                    }
                    if (flag4 && model_1.face_color != null) {
                        face_color[anInt1630] = model_1.face_color[i2];
                    }

                    anInt1630++;
                }

                for (int k2 = 0; k2 < model_1.texture_faces; k2++) {
                    texture_edge_a[texture_faces] = model_1.texture_edge_a[k2] + k1;
                    texture_edge_b[texture_faces] = model_1.texture_edge_b[k2] + k1;
                    texture_edge_c[texture_faces] = model_1.texture_edge_c[k2] + k1;
                    texture_faces++;
                }

                i1 += model_1.texture_faces;
            }
        }

        method466();
    }

    public void method464(Model model, boolean flag) {
        numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
        anInt1630 = model.anInt1630;
        texture_faces = model.texture_faces;
        if (anIntArray1622.length < numberOfVerticeCoordinates) {
            anIntArray1622 = new int[numberOfVerticeCoordinates + 10000];
            anIntArray1623 = new int[numberOfVerticeCoordinates + 10000];
            anIntArray1624 = new int[numberOfVerticeCoordinates + 10000];
        }
        verticesParticle = new int[numberOfVerticeCoordinates];
        vertex_position_x = anIntArray1622;
        vertex_position_y = anIntArray1623;
        vertex_position_z = anIntArray1624;
        for (int k = 0; k < numberOfVerticeCoordinates; k++) {
            verticesParticle[k] = model.verticesParticle[k];
            vertex_position_x[k] = model.vertex_position_x[k];
            vertex_position_y[k] = model.vertex_position_y[k];
            vertex_position_z[k] = model.vertex_position_z[k];
        }

        if (flag) {
            anIntArray1639 = model.anIntArray1639;
        } else {
            if (anIntArray1625.length < anInt1630) {
                anIntArray1625 = new int[anInt1630 + 100];
            }
            anIntArray1639 = anIntArray1625;
            if (model.anIntArray1639 == null) {
                for (int l = 0; l < anInt1630; l++) {
                    anIntArray1639[l] = 0;
                }

            } else {
                for (int i1 = 0; i1 < anInt1630; i1++) {
                    anIntArray1639[i1] = model.anIntArray1639[i1];
                }

            }
        }
        render_type = model.render_type;
        face_color = model.face_color;
        face_render_priorities = model.face_render_priorities;
        anInt1641 = model.anInt1641;
        triangleSkin = model.triangleSkin;
        vertexSkin = model.vertexSkin;
        triangle_edge_a = model.triangle_edge_a;
        triangle_edge_b = model.triangle_edge_b;
        triangle_edge_c = model.triangle_edge_c;
        anIntArray1634 = model.anIntArray1634;
        anIntArray1635 = model.anIntArray1635;
        anIntArray1636 = model.anIntArray1636;
        texture_edge_a = model.texture_edge_a;
        texture_edge_b = model.texture_edge_b;
        texture_edge_c = model.texture_edge_c;
        scaledVertices = model.scaledVertices;
    }

    private final int method465(Model model, int i) {
        int j = -1;
        int p = model.verticesParticle[i];
        int k = model.vertex_position_x[i];
        int l = model.vertex_position_y[i];
        int i1 = model.vertex_position_z[i];
        if (scaledVertices && !model.scaledVertices) {
            k <<= 2;
            l <<= 2;
            i1 <<= 2;
        }
        for (int j1 = 0; j1 < numberOfVerticeCoordinates; j1++) {
            if (k != vertex_position_x[j1] || l != vertex_position_y[j1] || i1 != vertex_position_z[j1]) {
                continue;
            }
            j = j1;
            break;
        }

        if (j == -1) {
            verticesParticle[numberOfVerticeCoordinates] = p;
            vertex_position_x[numberOfVerticeCoordinates] = k;
            vertex_position_y[numberOfVerticeCoordinates] = l;
            vertex_position_z[numberOfVerticeCoordinates] = i1;
            if (model.bone_skin != null) {
                bone_skin[numberOfVerticeCoordinates] = model.bone_skin[i];
            }
            j = numberOfVerticeCoordinates++;
        }
        return j;
    }

    public void method466() {
        super.modelHeight = 0;
        anInt1650 = 0;
        anInt1651 = 0;
        for (int i = 0; i < numberOfVerticeCoordinates; i++) {
            int j = vertex_position_x[i];
            int k = vertex_position_y[i];
            int l = vertex_position_z[i];
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
            if (i1 > anInt1650) {
                anInt1650 = i1;
            }
        }
        anInt1650 = (int) (Math.sqrt(anInt1650) + 0.98999999999999999D);
        anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight) + 0.98999999999999999D);
        diagonal3D = anInt1653 + (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
    }

    public void method467() {
        super.modelHeight = 0;
        anInt1651 = 0;
        for (int i = 0; i < numberOfVerticeCoordinates; i++) {
            int j = vertex_position_y[i];
            if (-j > super.modelHeight) {
                super.modelHeight = -j;
            }
            if (j > anInt1651) {
                anInt1651 = j;
            }
        }

        anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight) + 0.98999999999999999D);
        diagonal3D = anInt1653 + (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
    }

    private void method468(int i) {
        super.modelHeight = 0;
        anInt1650 = 0;
        anInt1651 = 0;
        anInt1646 = 0xf423f;
        anInt1647 = 0xfff0bdc1;
        anInt1648 = 0xfffe7961;
        anInt1649 = 0x1869f;
        for (int j = 0; j < numberOfVerticeCoordinates; j++) {
            int k = vertex_position_x[j];
            int l = vertex_position_y[j];
            int i1 = vertex_position_z[j];
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
            if (j1 > anInt1650) {
                anInt1650 = j1;
            }
        }

        anInt1650 = (int) Math.sqrt(anInt1650);
        anInt1653 = (int) Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight);
        if (i != 21073) {
            return;
        } else {
            diagonal3D = anInt1653 + (int) Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651);
            return;
        }
    }

    public void createBones() {
        if (bone_skin != null) {
            int ai[] = new int[256];
            int j = 0;
            for (int l = 0; l < numberOfVerticeCoordinates; l++) {
                int j1 = bone_skin[l];
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
                int l2 = bone_skin[j2];
                vertexSkin[l2][ai[l2]++] = j2;
            }

            bone_skin = null;
        }
        if (muscle_skin != null) {
            int ai1[] = new int[256];
            int k = 0;
            for (int i1 = 0; i1 < anInt1630; i1++) {
                int l1 = muscle_skin[i1];
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
                int i3 = muscle_skin[k2];
                triangleSkin[i3][ai1[i3]++] = k2;
            }

            muscle_skin = null;
        }
    }


    public void createBonesRS3() {

        boolean bool_0 = true;
        if (bone_skin != null) {
            int[] ints_0 = new int[800];
            int int_0 = 0;
            int int_1 = bool_0 ? numberOfVerticeCoordinates : highest;

            int int_3;
            for (int int_2 = 0; int_2 < int_1; int_2++) {
                int_3 = bone_skin[int_2];
                if (int_3 >= 0) {
                    ++ints_0[int_3];
                    if (int_3 > int_0) {
                        int_0 = int_3;
                    }
                }
            }

            int[][] ints_1 = new int[int_0 + 1][];

            for (int_3 = 0; int_3 <= int_0; int_3++) {
                ints_1[int_3] = new int[ints_0[int_3]];
                ints_0[int_3] = 0;
            }

            for (int_3 = 0; int_3 < int_1; int_3++) {
                int int_4 = bone_skin[int_3];
                if (int_4 >= 0) {
                    ints_1[int_4][ints_0[int_4]++] = int_3;
                }
            }
            vertexSkin = ints_1;
        }

        if (muscle_skin != null) {
            int[] ints_0 = new int[256];
            int int_0 = 0;

            int int_2;
            for (int int_1 = 0; int_1 < faces; int_1++) {
                int_2 = muscle_skin[int_1];
                if (int_2 >= 0) {
                    ++ints_0[int_2];
                    if (int_2 > int_0) {
                        int_0 = int_2;
                    }
                }
            }

            int[][] ints_1 = new int[int_0 + 1][];

            for (int_2 = 0; int_2 <= int_0; int_2++) {
                ints_1[int_2] = new int[ints_0[int_2]];
                ints_0[int_2] = 0;
            }

            for (int_2 = 0; int_2 < faces; int_2++) {
                int int_3 = muscle_skin[int_2];
                if (int_3 >= 0) {
                    ints_1[int_3][ints_0[int_3]++] = int_2;
                }
            }

            triangleSkin = ints_1;
        }
    }

    public void applyTransform(int i) {
applyTransform(i, false);
    }
        public void applyTransform(int i, boolean osrs) {
        if (vertexSkin == null) {
            return;
        }
        if (i == -1) {
            return;
        }
        FrameReader class36 = FrameReader.forId(i, osrs);
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

    public void interpolateFrames(int firstFrame, int nextFrame, int end, int cycle, boolean osrs) {
        if (!Configuration.TWEENING_ENABLED) {
            applyTransform(nextFrame, osrs);
            return;
        }
        try {
            if (vertexSkin != null && firstFrame != -1) {
                FrameReader currentAnimation = FrameReader.forId(firstFrame, osrs);
                if (currentAnimation == null) {
                    applyTransform(nextFrame, osrs);
                    return;
                }
                SkinList list1 = currentAnimation.skinList;
                anInt1681 = 0;
                anInt1682 = 0;
                anInt1683 = 0;
                FrameReader nextAnimation = null;
                SkinList list2 = null;
                if (nextFrame != -1) {
                    nextAnimation = FrameReader.forId(nextFrame, osrs);
                    if (nextAnimation.skinList != list1)
                        nextAnimation = null;
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
            applyTransform(firstFrame, osrs);
        }
    }

    public void method471(int ai[], int j, int k, boolean osrs) {
        if (k == -1) {
            return;
        }
        if (ai == null || j == -1) {
            applyTransform(k, osrs);
            return;
        }
        FrameReader class36 = FrameReader.forId(k, osrs);
        if (class36 == null) {
            return;
        }
        FrameReader class36_1 = FrameReader.forId(j, osrs);
        if (class36_1 == null) {
            applyTransform(k, osrs);
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
                        anInt1681 += vertex_position_x[j6] >> (scaledVertices ? 2 : 0);
                        anInt1682 += vertex_position_y[j6] >> (scaledVertices ? 2 : 0);
                        anInt1683 += vertex_position_z[j6] >> (scaledVertices ? 2 : 0);
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
                        vertex_position_x[j5] += j << (scaledVertices ? 2 : 0);
                        vertex_position_y[j5] += k << (scaledVertices ? 2 : 0);
                        vertex_position_z[j5] += l << (scaledVertices ? 2 : 0);
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
                        vertex_position_x[k5] -= anInt1681 << (scaledVertices ? 2 : 0);
                        vertex_position_y[k5] -= anInt1682 << (scaledVertices ? 2 : 0);
                        vertex_position_z[k5] -= anInt1683 << (scaledVertices ? 2 : 0);
                        int k6 = (j & 0xff) * 8;
                        int l6 = (k & 0xff) * 8;
                        int i7 = (l & 0xff) * 8;
                        if (i7 != 0) {
                            int j7 = SINE[i7];
                            int i8 = COSINE[i7];
                            int l8 = vertex_position_y[k5] * j7 + vertex_position_x[k5] * i8 >> 16;
                            vertex_position_y[k5] = vertex_position_y[k5] * i8 - vertex_position_x[k5] * j7 >> 16;
                            vertex_position_x[k5] = l8;
                        }
                        if (k6 != 0) {
                            int k7 = SINE[k6];
                            int j8 = COSINE[k6];
                            int i9 = vertex_position_y[k5] * j8 - vertex_position_z[k5] * k7 >> 16;
                            vertex_position_z[k5] = vertex_position_y[k5] * k7 + vertex_position_z[k5] * j8 >> 16;
                            vertex_position_y[k5] = i9;
                        }
                        if (l6 != 0) {
                            int l7 = SINE[l6];
                            int k8 = COSINE[l6];
                            int j9 = vertex_position_z[k5] * l7 + vertex_position_x[k5] * k8 >> 16;
                            vertex_position_z[k5] = vertex_position_z[k5] * k8 - vertex_position_x[k5] * l7 >> 16;
                            vertex_position_x[k5] = j9;
                        }
                        vertex_position_x[k5] += anInt1681 << (scaledVertices ? 2 : 0);
                        vertex_position_y[k5] += anInt1682 << (scaledVertices ? 2 : 0);
                        vertex_position_z[k5] += anInt1683 << (scaledVertices ? 2 : 0);
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
                        vertex_position_x[l5] -= anInt1681 << (scaledVertices ? 2 : 0);
                        vertex_position_y[l5] -= anInt1682 << (scaledVertices ? 2 : 0);
                        vertex_position_z[l5] -= anInt1683 << (scaledVertices ? 2 : 0);
                        vertex_position_x[l5] = vertex_position_x[l5] * j / 128;
                        vertex_position_y[l5] = vertex_position_y[l5] * k / 128;
                        vertex_position_z[l5] = vertex_position_z[l5] * l / 128;
                        vertex_position_x[l5] += anInt1681 << (scaledVertices ? 2 : 0);
                        vertex_position_y[l5] += anInt1682 << (scaledVertices ? 2 : 0);
                        vertex_position_z[l5] += anInt1683 << (scaledVertices ? 2 : 0);
                    }
                }
            }
            return;
        }
        if (i == 5 && triangleSkin != null && anIntArray1639 != null) {
            for (int j2 = 0; j2 < i1; j2++) {
                int k3 = ai[j2];
                if (k3 < triangleSkin.length) {
                    int ai4[] = triangleSkin[k3];
                    for (int element : ai4) {
                        int i6 = element;
                        anIntArray1639[i6] += j * 8;
                        if (anIntArray1639[i6] < 0) {
                            anIntArray1639[i6] = 0;
                        }
                        if (anIntArray1639[i6] > 255) {
                            anIntArray1639[i6] = 255;
                        }
                    }
                }
            }
        }
    }

    public void method473() {
        for (int j = 0; j < numberOfVerticeCoordinates; j++) {
            int k = vertex_position_x[j];
            vertex_position_x[j] = vertex_position_z[j];
            vertex_position_z[j] = -k;
        }
    }

    public void rotateX(int i) {
        int k = SINE[i];
        int l = COSINE[i];

        for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
            int j1 = vertex_position_y[i1] * l - vertex_position_z[i1] * k >> 16;
            vertex_position_z[i1] = vertex_position_y[i1] * k + vertex_position_z[i1] * l >> 16;
            vertex_position_y[i1] = j1;
        }
    }

    public void rotate90() {
        for (int i = 0; i < numberOfVerticeCoordinates; i++) {
            int i_10_ = vertex_position_x[i];
            vertex_position_x[i] = vertex_position_z[i];
            vertex_position_z[i] = -i_10_;
        }
    }

    public void rotate180() {
        for (int i = 0; i < numberOfVerticeCoordinates; i++) {
            vertex_position_x[i] = -vertex_position_x[i];
            vertex_position_z[i] = -vertex_position_z[i];
        }
    }

    public void translate(int i, int j, int l) {
        if (scaledVertices) {
            i <<= 2;
            j <<= 2;
            l <<= 2;
        }
        for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
            vertex_position_x[i1] += i;
            vertex_position_y[i1] += j;
            vertex_position_z[i1] += l;
        }
    }

    public void method476(int i, int j) {
        for (int k = 0; k < anInt1630; k++) {
            if (face_color[k] == i) {
                face_color[k] = j;
            }
        }
    }

    public void method477() {
        for (int j = 0; j < numberOfVerticeCoordinates; j++) {
            vertex_position_z[j] = -vertex_position_z[j];
        }
        for (int k = 0; k < anInt1630; k++) {
            int l = triangle_edge_a[k];
            triangle_edge_a[k] = triangle_edge_c[k];
            triangle_edge_c[k] = l;
        }
    }

    public void scaleT(int i, int j, int l) {
        for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
            vertex_position_x[i1] = vertex_position_x[i1] * i / 128;
            vertex_position_y[i1] = vertex_position_y[i1] * l / 128;
            vertex_position_z[i1] = vertex_position_z[i1] * j / 128;
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
            if (face_color != null && anIntArray1639 != null) {
                if (face_color[i2] == 65535
                        /*
                         * || (anIntArray1640[i2] == 0 // Black Triangles 633 // Models
                         * - Fixes Gwd walls // & Black models )
                         */ || face_color[i2] == 16705) {
                    anIntArray1639[i2] = 255;
                }
            }
            int j2 = triangle_edge_a[i2];
            int l2 = triangle_edge_b[i2];
            int i3 = triangle_edge_c[i2];
            int j3 = vertex_position_x[l2] - vertex_position_x[j2] >> (scaledVertices ? 2 : 0);
            int k3 = vertex_position_y[l2] - vertex_position_y[j2] >> (scaledVertices ? 2 : 0);
            int l3 = vertex_position_z[l2] - vertex_position_z[j2] >> (scaledVertices ? 2 : 0);
            int i4 = vertex_position_x[i3] - vertex_position_x[j2] >> (scaledVertices ? 2 : 0);
            int j4 = vertex_position_y[i3] - vertex_position_y[j2] >> (scaledVertices ? 2 : 0);
            int k4 = vertex_position_z[i3] - vertex_position_z[j2] >> (scaledVertices ? 2 : 0);
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

            if (render_type == null || (render_type[i2] & 1) == 0) {

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
                anIntArray1634[i2] = method481(face_color[i2], l5, render_type[i2]);

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
                for (int l1 = 0; l1 < numberOfVerticeCoordinates; l1++)
                    super.aClass33Array1425[l1] = new Class33();

            }
            for (int i2 = 0; i2 < numberOfTriangleFaces; i2++) {
                if (face_color != null && anIntArray1639 != null) {
                    if (face_color[i2] == 65535 || face_color[i2] == 1 || face_color[i2] == 16705
                            || face_color[i2] == 255)
                        anIntArray1639[i2] = 255;
                }
                int j2 = triangle_edge_a[i2];
                int l2 = triangle_edge_b[i2];
                int i3 = triangle_edge_c[i2];
                int j3 = vertex_position_x[l2] - vertex_position_x[j2];
                int k3 = vertex_position_y[l2] - vertex_position_y[j2];
                int l3 = vertex_position_z[l2] - vertex_position_z[j2];
                int i4 = vertex_position_x[i3] - vertex_position_x[j2];
                int j4 = vertex_position_y[i3] - vertex_position_y[j2];
                int k4 = vertex_position_z[i3] - vertex_position_z[j2];
                int l4 = k3 * k4 - j4 * l3;
                int i5 = l3 * i4 - k4 * j3;
                int j5;
                for (j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192
                        || j5 < -8192; j5 >>= 1) {
                    l4 >>= 1;
                    i5 >>= 1;
                }

                int k5 = (int) Math.sqrt(l4 * l4 + i5 * i5 + j5 * j5);
                if (k5 <= 0)
                    k5 = 1;
                l4 = (l4 * 256) / k5;
                i5 = (i5 * 256) / k5;
                j5 = (j5 * 256) / k5;

                if (render_type == null || (render_type[i2] & 1) == 0) {

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
                    anIntArray1634[i2] = method481(face_color[i2], l5, face_color[i2]);

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
            int k1 = triangle_edge_a[j1];
            int i2 = triangle_edge_b[j1];
            int j2 = triangle_edge_c[j1];
            if (render_type == null) {
                int i3 = face_color[j1];
                Class33 class33 = super.aClass33Array1425[k1];
                int k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                anIntArray1634[j1] = method481(i3, k2, 0);
                class33 = super.aClass33Array1425[i2];
                k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                anIntArray1635[j1] = method481(i3, k2, 0);
                class33 = super.aClass33Array1425[j2];
                k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                anIntArray1636[j1] = method481(i3, k2, 0);
            } else if ((render_type[j1] & 1) == 0) {
                int j3 = face_color[j1];
                int k3 = render_type[j1];
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
        bone_skin = null;
        muscle_skin = null;
        if (render_type != null) {
            for (int l1 = 0; l1 < anInt1630; l1++) {
                if ((render_type[l1] & 2) == 2) {
                    return;
                }
            }

        }
        face_color = null;
    }

    public final void renderSingle(int j, int k, int l, int i1, int j1, int k1) {
        int i = 0;
        int l1 = Rasterizer.centerX;
        int i2 = Rasterizer.centerY;
        int j2 = SINE[i];
        int k2 = COSINE[i];
        int l2 = SINE[j];
        int i3 = COSINE[j];
        int j3 = SINE[k];
        int k3 = COSINE[k];
        int l3 = SINE[l];
        int i4 = COSINE[l];
        int j4 = j1 * l3 + k1 * i4 >> 16;
        for (int k4 = 0; k4 < numberOfVerticeCoordinates; k4++) {
            int l4 = vertex_position_x[k4] << (scaledVertices ? 0 : 2);
            int i5 = vertex_position_y[k4] << (scaledVertices ? 0 : 2);
            int j5 = vertex_position_z[k4] << (scaledVertices ? 0 : 2);
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
            if (texture_faces > 0) {
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
        int l2 = anInt1650 * k >> 16;
        int i3 = k2 + l2;
        if (i3 <= 50 || k2 >= 9000) {
            return;
        }
        int j3 = l1 * l + j1 * i1 >> 16;
        int k3 = j3 - anInt1650 << Client.log_view_dist;
        if (k3 / i3 >= DrawingArea.centerY) {
            return;
        }
        int l3 = j3 + anInt1650 << Client.log_view_dist;
        if (l3 / i3 <= -DrawingArea.centerY) {
            return;
        }
        int i4 = k1 * k - j2 * j >> 16;
        int j4 = anInt1650 * j >> 16;
        int k4 = i4 + j4 << Client.log_view_dist;
        if (k4 / i3 <= -DrawingArea.middleY) {
            return;
        }
        int l4 = j4 + (super.modelHeight * k >> 16);
        int i5 = i4 - l4 << Client.log_view_dist;
        if (i5 / i3 >= DrawingArea.middleY) {
            return;
        }
        int j5 = l2 + (super.modelHeight * j >> 16);
        boolean flag = false;
        if (k2 - j5 <= 50) {
            flag = true;
        }
        boolean flag1 = false;
        if (uid > 0 && aBoolean1684) {
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
            int i6 = anInt1685 - Rasterizer.centerX;
            int k6 = anInt1686 - Rasterizer.centerY;
            if (i6 > k3 && i6 < l3 && k6 > i5 && k6 < k4) {
                if (aBoolean1659) {
                    mapObjIds[anInt1687] = newuid;
                    anIntArray1688[anInt1687++] = uid;
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
            l6 = SINE[i];
            i7 = COSINE[i];
        }
        for (int j7 = 0; j7 < numberOfVerticeCoordinates; j7++) {
            int k7 = vertex_position_x[j7] << (scaledVertices ? 0 : 2);
            int l7 = vertex_position_y[j7] << (scaledVertices ? 0 : 2);
            int i8 = vertex_position_z[j7] << (scaledVertices ? 0 : 2);
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
                anIntArray1665[j7] = l5 + (k7 << Client.log_view_dist) / i8;
                anIntArray1666[j7] = j6 + (l7 << Client.log_view_dist) / i8;
            } else {
                anIntArray1665[j7] = -5000;
                flag = true;
            }
            if (flag || texture_faces > 0) {
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
            if (render_type == null || render_type[k] != -1) {
                int l = triangle_edge_a[k];
                int k1 = triangle_edge_b[k];
                int j2 = triangle_edge_c[k];
                int i3 = anIntArray1665[l];
                int l3 = anIntArray1665[k1];
                int k4 = anIntArray1665[j2];
                if (flag && (i3 == -5000 || l3 == -5000 || k4 == -5000)) {
                    outOfReach[k] = true;
                    int j5 = (anIntArray1667[l] + anIntArray1667[k1] + anIntArray1667[j2]) / 3 + anInt1653;
                    faceLists[j5][depthListIndices[j5]++] = k;
                } else {
                    if (flag1 && method486(anInt1685, anInt1686, anIntArray1666[l], anIntArray1666[k1], anIntArray1666[j2], i3, l3, k4)) {
                        mapObjIds[anInt1687] = id;
                        anIntArray1688[anInt1687++] = i;
                        flag1 = false;
                    }
                    if ((i3 - l3) * (anIntArray1666[j2] - anIntArray1666[k1]) - (anIntArray1666[l] - anIntArray1666[k1]) * (k4 - l3) > 0) {
                        outOfReach[k] = false;
                        if (i3 < 0 || l3 < 0 || k4 < 0 || i3 > DrawingArea.centerX || l3 > DrawingArea.centerX || k4 > DrawingArea.centerX) {
                            aBooleanArray1663[k] = true;
                        } else {
                            aBooleanArray1663[k] = false;
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

            ParticleDefinition def = ParticleDefinition.cache[pid];
            int pX = vertex_position_x[n] >> (scaledVertices ? 2 : 0);
            int pY = vertex_position_y[n] >> (scaledVertices ? 2 : 0);
            int pZ = vertex_position_z[n] >> (scaledVertices ? 2 : 0);
            if (lastRenderedRotation != 0) {
                int sine = SINE[lastRenderedRotation];
                int cosine = COSINE[lastRenderedRotation];
                int rotatedX = pZ * sine + pX * cosine >> 16;
                pZ = pZ * cosine - pX * sine >> 16;
                pX = rotatedX;
            }
            pX += offX;
            pZ += offZ;
            Vector basePos = new Vector(pX, -pY, pZ);
        }
    }

    private final void rasterise(int i, int bufferOffset) {
        if (outOfReach[i]) {
            method485(i, bufferOffset);
            return;
        }
        int j = triangle_edge_a[i];
        int k = triangle_edge_b[i];
        int l = triangle_edge_c[i];
        Rasterizer.aBoolean1462 = aBooleanArray1663[i];
        if (anIntArray1639 == null) {
            Rasterizer.anInt1465 = 0;
        } else {
            Rasterizer.anInt1465 = anIntArray1639[i];
        }
        int i1;
        if (render_type == null) {
            i1 = 0;
        } else {
            i1 = render_type[i] & 3;
        }
        if (i1 == 0) {
            Rasterizer.drawGouraudTriangle(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], anIntArray1634[i], anIntArray1635[i], anIntArray1636[i],
                    vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l], bufferOffset);
        } else if (i1 == 1) {
            Rasterizer.drawFlatTriangle(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], modelIntArray3[anIntArray1634[i]],
                    vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l], bufferOffset);
        } else if (i1 == 2) {
            int j1 = render_type[i] >> 2;
            int l1 = texture_edge_a[j1];
            int j2 = texture_edge_b[j1];
            int l2 = texture_edge_c[j1];
            Rasterizer.drawTexturedTriangle(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], anIntArray1634[i], anIntArray1635[i], anIntArray1636[i], anIntArray1668[l1], anIntArray1668[j2], anIntArray1668[l2], anIntArray1669[l1], anIntArray1669[j2], anIntArray1669[l2], anIntArray1670[l1], anIntArray1670[j2], anIntArray1670[l2], face_color[i],
                    vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l], bufferOffset);

        } else if (i1 == 3) {
            int k1 = render_type[i] >> 2;
            int i2 = texture_edge_a[k1];
            int k2 = texture_edge_b[k1];
            int i3 = texture_edge_c[k1];
            Rasterizer.drawTexturedTriangle(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[i2], anIntArray1668[k2], anIntArray1668[i3], anIntArray1669[i2], anIntArray1669[k2], anIntArray1669[i3], anIntArray1670[i2], anIntArray1670[k2], anIntArray1670[i3], face_color[i],
                    vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l], bufferOffset);
        }
    }

    private final void method485(int i, int bufferOffset) {
        if (face_color != null) {
            if (face_color[i] == 65535) {
                return;
            }
        }
        int j = Rasterizer.centerX;
        int k = Rasterizer.centerY;
        int l = 0;
        int i1 = triangle_edge_a[i];
        int j1 = triangle_edge_b[i];
        int k1 = triangle_edge_c[i];
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
                int k5 = (50 - l1) * modelIntArray4[j2 - l1];
                anIntArray1678[l] = j + (k2 + ((anIntArray1668[k1] - k2) * k5 >> 16) << Client.log_view_dist) / 50;
                anIntArray1679[l] = k + (k3 + ((anIntArray1669[k1] - k3) * k5 >> 16) << Client.log_view_dist) / 50;
                anIntArray1680[l++] = k4 + ((anIntArray1636[i] - k4) * k5 >> 16);
            }
            if (i2 >= 50) {
                int l5 = (50 - l1) * modelIntArray4[i2 - l1];
                anIntArray1678[l] = j + (k2 + ((anIntArray1668[j1] - k2) * l5 >> 16) << Client.log_view_dist) / 50;
                anIntArray1679[l] = k + (k3 + ((anIntArray1669[j1] - k3) * l5 >> 16) << Client.log_view_dist) / 50;
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
                int i6 = (50 - i2) * modelIntArray4[l1 - i2];
                anIntArray1678[l] = j + (l2 + ((anIntArray1668[i1] - l2) * i6 >> 16) << Client.log_view_dist) / 50;
                anIntArray1679[l] = k + (l3 + ((anIntArray1669[i1] - l3) * i6 >> 16) << Client.log_view_dist) / 50;
                anIntArray1680[l++] = l4 + ((anIntArray1634[i] - l4) * i6 >> 16);
            }
            if (j2 >= 50) {
                int j6 = (50 - i2) * modelIntArray4[j2 - i2];
                anIntArray1678[l] = j + (l2 + ((anIntArray1668[k1] - l2) * j6 >> 16) << Client.log_view_dist) / 50;
                anIntArray1679[l] = k + (l3 + ((anIntArray1669[k1] - l3) * j6 >> 16) << Client.log_view_dist) / 50;
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
                int k6 = (50 - j2) * modelIntArray4[i2 - j2];
                anIntArray1678[l] = j + (i3 + ((anIntArray1668[j1] - i3) * k6 >> 16) << Client.log_view_dist) / 50;
                anIntArray1679[l] = k + (i4 + ((anIntArray1669[j1] - i4) * k6 >> 16) << Client.log_view_dist) / 50;
                anIntArray1680[l++] = i5 + ((anIntArray1635[i] - i5) * k6 >> 16);
            }
            if (l1 >= 50) {
                int l6 = (50 - j2) * modelIntArray4[l1 - j2];
                anIntArray1678[l] = j + (i3 + ((anIntArray1668[i1] - i3) * l6 >> 16) << Client.log_view_dist) / 50;
                anIntArray1679[l] = k + (i4 + ((anIntArray1669[i1] - i4) * l6 >> 16) << Client.log_view_dist) / 50;
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
            Rasterizer.aBoolean1462 = false;
            if (l == 3) {
                if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.centerX || j4 > DrawingArea.centerX || j5 > DrawingArea.centerX) {
                    Rasterizer.aBoolean1462 = true;
                }
                int l7;
                if (render_type == null) {
                    l7 = 0;
                } else {
                    l7 = render_type[i] & 3;
                }
                if (l7 == 0) {
                    Rasterizer.drawGouraudTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], -1f, -1f, -1f, bufferOffset);
                } else if (l7 == 1) {
                    Rasterizer.drawFlatTriangle(i7, j7, k7, j3, j4, j5, modelIntArray3[anIntArray1634[i]], -1f, -1f, -1f, bufferOffset);
                } else if (l7 == 2) {
					/*
					 * int k9 = textures_face_a[j8];
					int k10 = textures_face_b[j8];
					int k11 = textures_face_c[j8];
					 */
                    int j8 = render_type[i] >> 2;
                    int k9 = texture_edge_a[j8];
                    int k10 = texture_edge_b[j8];
                    int k11 = texture_edge_c[j8];
                    Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], anIntArray1668[k9], anIntArray1668[k10], anIntArray1668[k11], anIntArray1669[k9], anIntArray1669[k10], anIntArray1669[k11], anIntArray1670[k9], anIntArray1670[k10], anIntArray1670[k11], face_color[i], -1f, -1f, -1f, bufferOffset);
                } else if (l7 == 3) {
                    int k8 = render_type[i] >> 2;
                    int l9 = texture_edge_a[k8];
                    int l10 = texture_edge_b[k8];
                    int l11 = texture_edge_c[k8];
                    Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[l9], anIntArray1668[l10], anIntArray1668[l11], anIntArray1669[l9], anIntArray1669[l10], anIntArray1669[l11], anIntArray1670[l9], anIntArray1670[l10], anIntArray1670[l11], face_color[i], -1f, -1f, -1f, bufferOffset);
                }
            }
            if (l == 4) {
                if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.centerX || j4 > DrawingArea.centerX || j5 > DrawingArea.centerX || anIntArray1678[3] < 0 || anIntArray1678[3] > DrawingArea.centerX) {
                    Rasterizer.aBoolean1462 = true;
                }
                int i8;
                if (render_type == null) {
                    i8 = 0;
                } else {
                    i8 = render_type[i] & 3;
                }
                if (i8 == 0) {
                    Rasterizer.drawGouraudTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], -1f, -1f, -1f, bufferOffset);
                    Rasterizer.drawGouraudTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], -1f, -1f, -1f, bufferOffset);
                    return;
                }
                if (i8 == 1) {
                    int l8 = modelIntArray3[anIntArray1634[i]];
                    Rasterizer.drawFlatTriangle(i7, j7, k7, j3, j4, j5, l8, -1f, -1f, -1f, bufferOffset);
                    Rasterizer.drawFlatTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], l8, -1f, -1f, -1f, bufferOffset);
                    return;
                }
                if (i8 == 2) {
                    int i9 = render_type[i] >> 2;
                    int i10 = texture_edge_a[i9];
                    int i11 = texture_edge_b[i9];
                    int i12 = texture_edge_c[i9];
                    Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], anIntArray1668[i10], anIntArray1668[i11], anIntArray1668[i12], anIntArray1669[i10], anIntArray1669[i11], anIntArray1669[i12], anIntArray1670[i10], anIntArray1670[i11], anIntArray1670[i12], face_color[i], -1f, -1f, -1f, bufferOffset);
                    Rasterizer.drawTexturedTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], anIntArray1668[i10], anIntArray1668[i11], anIntArray1668[i12], anIntArray1669[i10], anIntArray1669[i11], anIntArray1669[i12], anIntArray1670[i10], anIntArray1670[i11], anIntArray1670[i12], face_color[i], -1f, -1f, -1f, bufferOffset);
                    return;
                }
                if (i8 == 3) {
                    int j9 = render_type[i] >> 2;
                    int j10 = texture_edge_a[j9];
                    int j11 = texture_edge_b[j9];
                    int j12 = texture_edge_c[j9];
                    Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], face_color[i], -1f, -1f, -1f, bufferOffset);
                    Rasterizer.drawTexturedTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], face_color[i], -1f, -1f, -1f, bufferOffset);
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
        Class21 ModelDef_1 = modelsArray[modelID] = new Class21();
        ModelDef_1.aByteArray368 = abyte0;
        ModelDef_1.anInt369 = numVertices;
        ModelDef_1.anInt370 = numTriangles;
        ModelDef_1.anInt371 = numTextureTriangles;
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
        bone_skin = new int[numVertices];
        render_type = new int[numTriangles];
        face_render_priorities = new int[numTriangles];
        anIntArray1639 = new int[numTriangles];
        muscle_skin = new int[numTriangles];
        if (i3 == 1) {
            bone_skin = new int[numVertices];
        }
        if (bool) {
            render_type = new int[numTriangles];
        }
        if (i2 == 255) {
            face_render_priorities = new int[numTriangles];
        } else {
        }
        if (j2 == 1) {
            anIntArray1639 = new int[numTriangles];
        }
        if (k2 == 1) {
            muscle_skin = new int[numTriangles];
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
            if (bone_skin != null) {
                bone_skin[k11] = nc5.getUnsignedByte();
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
                render_type[i12] = nc2.getSignedByte();
                if (render_type[i12] == 2) {
                    triangleColours2[i12] = 65535;
                }
                render_type[i12] = 0;
            }
            if (i2 == 255) {
                face_render_priorities[i12] = nc3.getSignedByte();
            }
            if (j2 == 1) {
                anIntArray1639[i12] = nc4.getSignedByte();
                if (anIntArray1639[i12] < 0) {
                    anIntArray1639[i12] = 256 + anIntArray1639[i12];
                }
            }
            if (k2 == 1) {
                muscle_skin[i12] = nc5.getUnsignedByte();
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
        face_color = triangleColours2;
        numberOfVerticeCoordinates = numVertices;
        anInt1630 = numTriangles;
        vertex_position_x = vertexX;
        vertex_position_y = vertexY;
        vertex_position_z = vertexZ;
        triangle_edge_a = facePoint1;
        triangle_edge_b = facePoint2;
        triangle_edge_c = facePoint3;
    }

    public static ArrayList<Integer> debugArray = new ArrayList<>();

    private void decode876(byte[] data, int modelID) {
        /*anInt370 = 0;
        priority = (byte) 0;
        texture_faces = 0;*/
        ByteBuffer first = new ByteBuffer(data);
        ByteBuffer second = new ByteBuffer(data);
        ByteBuffer third = new ByteBuffer(data);
        ByteBuffer fourth = new ByteBuffer(data);
        ByteBuffer fifth = new ByteBuffer(data);
        ByteBuffer sixth = new ByteBuffer(data);
        ByteBuffer seventh = new ByteBuffer(data);


        int check = first.g1();
        if (check != 1) {
            System.out.println("Wrong: " + check);
        } else {
            first.getUnsignedByte();
            type = first.getUnsignedByte();
            boolean newFormat = type > 12;
            first.position = (data.length - 26);
            scaledVertices = newFormat;

            Class21 ModelDef_1 = rs3ModelsArray[modelID] = new Class21();
            ModelDef_1.aByteArray368 = data;
            int vertices = first.getUnsignedShort();
            int anInt370 = first.getUnsignedShort();
            texture_faces = first.getUnsignedShort();
            ModelDef_1.anInt369 = vertices;
            ModelDef_1.anInt370 = anInt370;
            ModelDef_1.anInt371 = texture_faces;

            faces = anInt370;
            numberOfVerticeCoordinates = vertices;
            anInt1630 = anInt370;

            int flag = first.getUnsignedByte();
            boolean bool = (flag & 0x1) == 1;
            boolean bool_9_ = (flag & 0x2) == 2;
            boolean bool_10_ = (flag & 0x4) == 4;
            boolean bool_11_ = (flag & 0x10) == 16;
            boolean bool_12_ = (flag & 0x20) == 32;
            boolean bool_13_ = (flag & 0x40) == 64;
            boolean bool_14_ = (flag & 0x80) == 128;
            int i_15_ = first.getUnsignedByte();
            int i_16_ = first.getUnsignedByte();
            int i_17_ = first.getUnsignedByte();
            int i_18_ = first.getUnsignedByte();
            int i_19_ = first.getUnsignedByte();
            int i_20_ = first.getUnsignedShort();
            int i_21_ = first.getUnsignedShort();
            int i_22_ = first.getUnsignedShort();
            int i_23_ = first.getUnsignedShort();
            int i_24_ = first.getUnsignedShort();
            int i_25_ = first.getUnsignedShort();
            int i_26_ = first.getUnsignedShort();
            if (!bool_11_) {
                if (i_19_ == 1) {
                    i_25_ = numberOfVerticeCoordinates;
                } else {
                    i_25_ = 0;
                }
            }
            if (!bool_12_) {
                if (i_17_ == 1) {
                    i_26_ = anInt370;
                } else {
                    i_26_ = 0;
                }
            }
            int i_27_ = 0;
            int i_28_ = 0;
            int i_29_ = 0;
            if (texture_faces > 0) {
                texture_type = new byte[texture_faces];
                first.position = 3;
                for (int i_30_ = 0; i_30_ < texture_faces; i_30_++) {
                    byte i_31_ = texture_type[i_30_] = first.getByte();
                    if (i_31_ == 0) {
                        i_27_++;
                    }
                    if (i_31_ >= 1 && i_31_ <= 3) {
                        i_28_++;
                    }
                    if (i_31_ == 2) {
                        i_29_++;
                    }
                }
            }
            int i_32_ = 3 + texture_faces;
            int i_33_ = i_32_;
            i_32_ += numberOfVerticeCoordinates;
            int i_34_ = i_32_;
            if (bool) {
                i_32_ += anInt370;
            }
            int i_35_ = i_32_;
            i_32_ += anInt370;
            int i_36_ = i_32_;
            if (i_15_ == 255) {
                i_32_ += anInt370;
            }
            int i_37_ = i_32_;
            i_32_ += i_26_;
            int i_38_ = i_32_;
            i_32_ += i_25_;
            int i_39_ = i_32_;
            if (i_16_ == 1) {
                i_32_ += anInt370;
            }
            int i_40_ = i_32_;
            i_32_ += i_23_;
            int i_41_ = i_32_;
            if (i_18_ == 1) {
                i_32_ += anInt370 * 2;
            }
            int i_42_ = i_32_;
            i_32_ += i_24_;
            int i_43_ = i_32_;
            i_32_ += anInt370 * 2;
            int i_44_ = i_32_;
            i_32_ += i_20_;
            int i_45_ = i_32_;
            i_32_ += i_21_;
            int i_46_ = i_32_;
            i_32_ += i_22_;
            int i_47_ = i_32_;
            i_32_ += i_27_ * 6;
            int i_48_ = i_32_;
            i_32_ += i_28_ * 6;
            int i_49_ = 6;
            if (type == 14) {
                i_49_ = 7;
            } else if (type >= 15) {
                i_49_ = 9;
            }
            int i_50_ = i_32_;
            i_32_ += i_28_ * i_49_;
            int i_51_ = i_32_;
            i_32_ += i_28_;
            int i_52_ = i_32_;
            i_32_ += i_28_;
            int i_53_ = i_32_;
            i_32_ += i_28_ + i_29_ * 2;
            int i_54_ = i_32_;
            int i_55_ = data.length;
            int i_56_ = data.length;
            int i_57_ = data.length;
            int i_58_ = data.length;
            if (bool_14_) {
                ByteBuffer eighth = new ByteBuffer(data);
                eighth.position = (data.length - 26);
                eighth.position -= data[eighth.position - 1];
                anInt1961 = eighth.getUnsignedShort();
                int i_60_ = eighth.getUnsignedShort();
                int i_61_ = eighth.getUnsignedShort();
                i_55_ = i_54_ + i_60_;
                i_56_ = i_55_ + i_61_;
                i_57_ = i_56_ + numberOfVerticeCoordinates;
                i_58_ = i_57_ + anInt1961 * 2;
            }
            verticesParticle = new int[numberOfVerticeCoordinates];
            vertex_position_x = new int[numberOfVerticeCoordinates];
            vertex_position_y = new int[numberOfVerticeCoordinates];
            vertex_position_z = new int[numberOfVerticeCoordinates];
            triangle_edge_a = new int[anInt370];
            triangle_edge_b = new int[anInt370];
            triangle_edge_c = new int[anInt370];
            if (i_19_ == 1) {
                bone_skin = new int[numberOfVerticeCoordinates];
            }
            if (bool) {
                render_type = new int[anInt370];
            }
            if (i_15_ == 255) {
                face_render_priorities = new int[anInt370];
            } else {
                //   priority = (byte) i_15_;
            }
            anIntArray1639 = new int[anInt370];
            if (i_16_ == 1) {
                anIntArray1639 = new int[anInt370];
            }
            if (i_17_ == 1) {
                muscle_skin = new int[anInt370];
            }
            short[] face_material = new short[anInt370];
            if (i_18_ == 1) {
                face_material = new short[anInt370];
            }
            byte[] face_texture = new byte[anInt370];
            if (i_18_ == 1 && (texture_faces > 0 || anInt1961 > 0)) {
                face_texture = new byte[anInt370];
            }
            face_color = new int[anInt370];
            if (texture_faces > 0) {
                texture_edge_a = new int[texture_faces];
                texture_edge_b = new int[texture_faces];
                texture_edge_c = new int[texture_faces];
            }
            first.position = i_33_;
            second.position = i_44_;
            third.position = i_45_;
            fourth.position = i_46_;
            fifth.position = i_38_;
            int i_62_ = 0;
            int i_63_ = 0;
            int i_64_ = 0;
            for (int i_65_ = 0; i_65_ < numberOfVerticeCoordinates; i_65_++) {
                int i_66_ = first.getUnsignedByte();
                int i_67_ = 0;
                if ((i_66_ & 0x1) != 0) {
                    i_67_ = second.getSmarter();
                }
                int i_68_ = 0;
                if ((i_66_ & 0x2) != 0) {
                    i_68_ = third.getSmarter();
                }
                int i_69_ = 0;
                if ((i_66_ & 0x4) != 0) {
                    i_69_ = fourth.getSmarter();
                }
                vertex_position_x[i_65_] = i_62_ + i_67_;
                vertex_position_y[i_65_] = i_63_ + i_68_;
                vertex_position_z[i_65_] = i_64_ + i_69_;
                i_62_ = vertex_position_x[i_65_];
                i_63_ = vertex_position_y[i_65_];
                i_64_ = vertex_position_z[i_65_];
                if (i_19_ == 1) {
                    if (bool_11_) {
                        bone_skin[i_65_] = fifth.getSmartOffset();
                    } else {
                        bone_skin[i_65_] = fifth.getUnsignedByte();
                        if (bone_skin[i_65_] == 255) {
                            bone_skin[i_65_] = -1;
                        }
                    }


                    if (modelID != 117171 && modelID != 117172 && modelID != 117173 && modelID != 94829&& modelID != 104389) {


                        if (bone_skin[i_65_] == 22
                                || bone_skin[i_65_] == 194
                                || bone_skin[i_65_] == 204
                                || bone_skin[i_65_] == 229
                                || bone_skin[i_65_] == 151
                                || bone_skin[i_65_] == 148
                                || bone_skin[i_65_] == 178
                                || bone_skin[i_65_] == 142
                                || bone_skin[i_65_] == 195
                                || bone_skin[i_65_] == 182
                                || bone_skin[i_65_] == 172
                                || bone_skin[i_65_] == 153
                                || bone_skin[i_65_] == 188
                                || bone_skin[i_65_] == 149
                                || bone_skin[i_65_] == 141
                                || bone_skin[i_65_] == 190) {
                            bone_skin[i_65_] = 28;
                        } else if (
                                bone_skin[i_65_] == 150
                                        || bone_skin[i_65_] == 215
                                        || bone_skin[i_65_] == 211
                                        || bone_skin[i_65_] == 19 || bone_skin[i_65_] == 209
                                        || bone_skin[i_65_] == 196
                                        || bone_skin[i_65_] == 183
                                        || bone_skin[i_65_] == 187
                                        || bone_skin[i_65_] == 184
                                        || bone_skin[i_65_] == 244
                                        || bone_skin[i_65_] == 206
                                        || bone_skin[i_65_] == 207
                                        || bone_skin[i_65_] == 243
                                        || bone_skin[i_65_] == 155
                                        || bone_skin[i_65_] == 146
                                        || bone_skin[i_65_] == 145) {
                            bone_skin[i_65_] = 27;
                        }
                        //}


                        //  if (ItemDefinition.rs3HeadModels.contains(modelID)) {//head

                        if (
                                bone_skin[i_65_] == 160
                                        || bone_skin[i_65_] == 179
                                        || bone_skin[i_65_] == 186
                                        || bone_skin[i_65_] == 191) {
                            bone_skin[i_65_] = 1;
                        }

                        // }
                        // if (ItemDefinition.rs3BodyModels.contains(modelID)) {//body


                        if (bone_skin[i_65_] == 175
                                || bone_skin[i_65_] == 147
                                || bone_skin[i_65_] == 253
                                || bone_skin[i_65_] == 169
                                || bone_skin[i_65_] == 241
                                || bone_skin[i_65_] == 216
                                || bone_skin[i_65_] == 143
                                || bone_skin[i_65_] == 230)
                            bone_skin[i_65_] = 8;


                        // }
                   /* if (ItemDefinition.rs3LegModels.contains(modelID)) {//leg
                        System.out.println("legs");
                        if (!debugArray.contains(bone_skin[i_65_])) {//
                            debugArray.add(bone_skin[i_65_]);
                            System.out.println("bone_skin[i_65_] : " + bone_skin[i_65_]);
                        }
                    }*/
                        if (bone_skin[i_65_] == 7 || bone_skin[i_65_] == 79
                                || bone_skin[i_65_] == 166
                                || bone_skin[i_65_] == 167 || bone_skin[i_65_] == 99)
                            bone_skin[i_65_] = 41;

                        if (bone_skin[i_65_] == 41)
                            bone_skin[i_65_] = 29;

                        if (bone_skin[i_65_] >= 173 && bone_skin[i_65_] <= 174)
                            bone_skin[i_65_] = 29;


                   /* if (modelID == 116183) {//glove
                        if (!debugArray.contains(bone_skin[i_65_])) {//glove
                            debugArray.add(bone_skin[i_65_]);

                            System.out.println("bone_skin[i_65_] : " + bone_skin[i_65_] + " - " + vertex_position_y[i_65_]);
                        }
                    }*/

                        if (bone_skin[i_65_] == 231
                                || bone_skin[i_65_] == 170
                                || bone_skin[i_65_] == 252
                                || bone_skin[i_65_] == 152
                                || bone_skin[i_65_] == 199
                                || bone_skin[i_65_] == 154
                                || bone_skin[i_65_] == 177
                                || bone_skin[i_65_] == 250
                                || bone_skin[i_65_] == 189
                                || bone_skin[i_65_] == 176
                                || bone_skin[i_65_] == 144
                                || bone_skin[i_65_] == 201
                                || bone_skin[i_65_] == 181)
                            bone_skin[i_65_] = 1;


                        if (bone_skin[i_65_] == 205
                                || bone_skin[i_65_] == 198)
                            bone_skin[i_65_] = 29;


                        if (bone_skin[i_65_] == 168
                                || bone_skin[i_65_] == 103
                                || bone_skin[i_65_] == 227
                                || bone_skin[i_65_] == 222
                                || bone_skin[i_65_] == 240
                                || bone_skin[i_65_] == 128)
                            bone_skin[i_65_] = 41;
                  /*
                   if (bone_skin[i_65_] >= 200 && bone_skin[i_65_]<= 300)
                        bone_skin[i_65_] = 1;

                */

                        if (ItemDefinition.switchHandsModels.contains(modelID)) {
                            if (bone_skin[i_65_] == 161)
                                bone_skin[i_65_] = 196;
                            else if (bone_skin[i_65_] == 163)
                                bone_skin[i_65_] = 200;
                            else
                                bone_skin[i_65_] = 50;
                        }
                    }
                }
            }
            if (anInt1961 > 0) {
                first.position = i_56_;
                second.position = i_57_;
                third.position = i_58_;
                int i_70_ = 0;
                int i_71_ = 0;
                for (/**/; i_70_ < numberOfVerticeCoordinates; i_70_++) {
                    i_71_ += first.getUnsignedByte();
                }
                for (i_70_ = 0; i_70_ < anInt1961; i_70_++) {
                    second.readShort();
                    third.readShort();
                }
            }
            first.position = i_43_;
            second.position = i_34_;
            third.position = i_36_;
            fourth.position = i_39_;
            fifth.position = i_37_;
            sixth.position = i_41_;
            seventh.position = i_42_;
            for (int i_72_ = 0; i_72_ < anInt370; i_72_++) {
                face_color[i_72_] = (short) first.getUnsignedShort();
                if (bool) {
                    render_type[i_72_] = second.getByte();
                }
                if (i_15_ == 255) {
                    face_render_priorities[i_72_] = third.getByte();
                }
                if (i_16_ == 1) {
                    anIntArray1639[i_72_] = fourth.getByte();
                }
                anIntArray1639[i_72_] = 0;
                if (face_color[i_72_] == 20287)
                    anIntArray1639[i_72_] = 255;
                if (face_color[i_72_] < -29060 && modelID == 23877)
                    anIntArray1639[i_72_] = 255;
                if (i_17_ == 1) {
                    if (bool_12_) {
                        muscle_skin[i_72_] = fifth.getSmartOffset();
                    } else {
                        muscle_skin[i_72_] = fifth.getUnsignedByte();
                        if (muscle_skin[i_72_] == 255) {
                            muscle_skin[i_72_] = -1;
                        }
                    }
                    if (ItemDefinition.switchHandsModels.contains(modelID)) {
                        if (muscle_skin[i_72_] == 55)//57
                            muscle_skin[i_72_] = 64;
                        else
                            muscle_skin[i_72_] = 29;
                    }

                }
                if (i_18_ == 1) {
                    face_material[i_72_] = (short) (sixth.getUnsignedShort() - 1);
                }
                if (face_texture != null) {
                    if (face_material[i_72_] != -1) {
                        if (type >= 16) {
                            face_texture[i_72_] = (byte) (seventh.gSmart1or2() - 1);
                        } else {
                            face_texture[i_72_] = (byte) (seventh.getUnsignedByte() - 1);
                        }
                    } else {
                        face_texture[i_72_] = (short) -1;
                    }
                }
            }
            highest = -1;
            first.position = i_40_;
            second.position = i_35_;
            third.position = i_55_;
            method3481(first, second, third);
            first.position = i_47_;
            second.position = i_48_;
            third.position = i_50_;
            fourth.position = i_51_;
            fifth.position = i_52_;
            sixth.position = i_53_;
            method3482(first, second, third, fourth, fifth, sixth);
            first.position = i_54_;
            if (bool_9_) {
                int i_73_ = first.getUnsignedByte();
                if (i_73_ > 0) {
                    for (int i_74_ = 0; i_74_ < i_73_; i_74_++) {
                        first.getUnsignedShort();
                        first.getUnsignedShort();
                    }
                }
                int i_78_ = first.getUnsignedByte();
                if (i_78_ > 0) {
                    for (int i_79_ = 0; i_79_ < i_78_; i_79_++) {
                        first.getUnsignedShort();
                        first.getUnsignedShort();
                    }
                }
            }
            if (bool_10_) {
                int i_82_ = first.getUnsignedByte();
                if (i_82_ > 0) {
                    for (int i_83_ = 0; i_83_ < i_82_; i_83_++) {
                        first.getUnsignedShort();
                        first.getUnsignedShort();
                        if (bool_13_) {
                            first.getSmartOffset();
                        } else {
                            first.getUnsignedByte();
                        }
                        first.getByte();
                    }
                }
            }
        }
    }

    public int faces;
    int anInt1961 = 0;
    public byte[] texture_type;
    public int type = 12;

    void method3481(ByteBuffer class523_sub34, ByteBuffer class523_sub34_110_, ByteBuffer class523_sub34_111_) {
        short i = 0;
        short i_112_ = 0;
        short i_113_ = 0;
        int i_114_ = 0;
        for (int i_115_ = 0; i_115_ < faces; i_115_++) {
            int i_116_ = class523_sub34_110_.g1();
            int i_117_ = i_116_ & 0x7;
            if (i_117_ == 1) {
                triangle_edge_a[i_115_] = i = (short) (class523_sub34.getSmarter() + i_114_);
                i_114_ = i;
                triangle_edge_b[i_115_] = i_112_ = (short) (class523_sub34.getSmarter() + i_114_);
                i_114_ = i_112_;
                triangle_edge_c[i_115_] = i_113_ = (short) (class523_sub34.getSmarter() + i_114_);
                i_114_ = i_113_;
                if (i > highest) {
                    highest = i;
                }
                if (i_112_ > highest) {
                    highest = i_112_;
                }
                if (i_113_ > highest) {
                    highest = i_113_;
                }
            }
            if (i_117_ == 2) {
                i_112_ = i_113_;
                i_113_ = (short) (class523_sub34.getSmarter() + i_114_);
                i_114_ = i_113_;
                triangle_edge_a[i_115_] = i;
                triangle_edge_b[i_115_] = i_112_;
                triangle_edge_c[i_115_] = i_113_;
                if (i_113_ > highest) {
                    highest = i_113_;
                }
            }
            if (i_117_ == 3) {
                i = i_113_;
                i_113_ = (short) (class523_sub34.getSmarter() + i_114_);
                i_114_ = i_113_;
                triangle_edge_a[i_115_] = i;
                triangle_edge_b[i_115_] = i_112_;
                triangle_edge_c[i_115_] = i_113_;
                if (i_113_ > highest) {
                    highest = i_113_;
                }
            }
            if (i_117_ == 4) {
                short i_118_ = i;
                i = i_112_;
                i_112_ = i_118_;
                i_113_ = (short) (class523_sub34.getSmarter() + i_114_);
                i_114_ = i_113_;
                triangle_edge_a[i_115_] = i;
                triangle_edge_b[i_115_] = i_112_;
                triangle_edge_c[i_115_] = i_113_;
                if (i_113_ > highest) {
                    highest = i_113_;
                }
            }
            if (anInt1961 > 0 && (i_116_ & 0x8) != 0) {
                class523_sub34_111_.g1();
                class523_sub34_111_.g1();
                class523_sub34_111_.g1();
            }
        }
        highest++;
    }

    void method3482(ByteBuffer class523_sub34, ByteBuffer class523_sub34_119_, ByteBuffer class523_sub34_120_,
                    ByteBuffer class523_sub34_121_, ByteBuffer class523_sub34_122_, ByteBuffer class523_sub34_123_) {
        for (int i = 0; i < texture_faces; i++) {
            int i_124_ = texture_type[i] & 0xff;
            if (i_124_ == 0) {
                texture_edge_a[i] = (short) class523_sub34.g2();
                texture_edge_b[i] = (short) class523_sub34.g2();
                texture_edge_c[i] = (short) class523_sub34.g2();
            }
            if (i_124_ == 1) {
                texture_edge_a[i] = (short) class523_sub34_119_.g2();
                texture_edge_b[i] = (short) class523_sub34_119_.g2();
                texture_edge_c[i] = (short) class523_sub34_119_.g2();
                if (type < 15) {
                    class523_sub34_120_.g2();
                    if (type < 14) {
                        class523_sub34_120_.g2();
                    } else {
                        class523_sub34_120_.getMedium();
                    }
                    class523_sub34_120_.g2();
                } else {
                    class523_sub34_120_.getMedium();
                    class523_sub34_120_.getMedium();
                    class523_sub34_120_.getMedium();
                }
                class523_sub34_121_.getByte();
                class523_sub34_122_.getByte();
                class523_sub34_123_.getByte();
            }
            if (i_124_ == 2) {
                texture_edge_a[i] = (short) class523_sub34_119_.g2();
                texture_edge_b[i] = (short) class523_sub34_119_.g2();
                texture_edge_c[i] = (short) class523_sub34_119_.g2();
                if (type < 15) {
                    class523_sub34_120_.g2();
                    if (type < 14) {
                        class523_sub34_120_.g2();
                    } else {
                        class523_sub34_120_.getMedium();
                    }
                    class523_sub34_120_.g2();
                } else {
                    class523_sub34_120_.getMedium();
                    class523_sub34_120_.getMedium();
                    class523_sub34_120_.getMedium();
                }
                class523_sub34_121_.getByte();
                class523_sub34_122_.getByte();
                class523_sub34_123_.getByte();
                class523_sub34_123_.getByte();
                class523_sub34_123_.getByte();
            }
            if (i_124_ == 3) {
                texture_edge_a[i] = (short) class523_sub34_119_.g2();
                texture_edge_b[i] = (short) class523_sub34_119_.g2();
                texture_edge_c[i] = (short) class523_sub34_119_.g2();
                if (type < 15) {
                    class523_sub34_120_.g2();
                    if (type < 14) {
                        class523_sub34_120_.g2();
                    } else {
                        class523_sub34_120_.getMedium();
                    }
                    class523_sub34_120_.g2();
                } else {
                    class523_sub34_120_.getMedium();
                    class523_sub34_120_.getMedium();
                    class523_sub34_120_.getMedium();
                }
                class523_sub34_121_.getByte();
                class523_sub34_122_.getByte();
                class523_sub34_123_.getByte();
            }
        }
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
        Class21 ModelDef_1 = modelsArray[modelID] = new Class21();
        ModelDef_1.aByteArray368 = abyte0;
        ModelDef_1.anInt369 = numVertices;
        ModelDef_1.anInt370 = numTriangles;
        ModelDef_1.anInt371 = numTexTriangles;
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
        if (newformat == 15) {
            newmodel[modelID] = true;
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
        bone_skin = new int[numVertices];
        render_type = new int[numTriangles];
        face_render_priorities = new int[numTriangles];
        anIntArray1639 = new int[numTriangles];
        muscle_skin = new int[numTriangles];
        if (i3 == 1) {
            bone_skin = new int[numVertices];
        }
        if (bool) {
            render_type = new int[numTriangles];
        }
        if (i2 == 255) {
            face_render_priorities = new int[numTriangles];
        } else {
        }
        if (j2 == 1) {
            anIntArray1639 = new int[numTriangles];
        }
        if (k2 == 1) {
            muscle_skin = new int[numTriangles];
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
            if (bone_skin != null) {
                bone_skin[k11] = nc5.getUnsignedByte();
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
                render_type[i12] = nc2.getSignedByte();
                if (render_type[i12] == 2) {
                    triangleColours2[i12] = 65535;
                }
                render_type[i12] = 0;
            }
            if (i2 == 255) {
                face_render_priorities[i12] = nc3.getSignedByte();
            }
            if (j2 == 1) {
                anIntArray1639[i12] = nc4.getSignedByte();
                if (anIntArray1639[i12] < 0) {
                    anIntArray1639[i12] = 256 + anIntArray1639[i12];
                }
            }
            if (k2 == 1) {
                muscle_skin[i12] = nc5.getUnsignedByte();
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
        face_color = triangleColours2;
        numberOfVerticeCoordinates = numVertices;
        anInt1630 = numTriangles;
        vertex_position_x = vertexX;
        vertex_position_y = vertexY;
        vertex_position_z = vertexZ;
        triangle_edge_a = facePoint1;
        triangle_edge_b = facePoint2;
        triangle_edge_c = facePoint3;
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
        texture_faces = foundAmt;
        if (render_type == null)
            render_type = new int[foundAmt];
        if (face_color == null)
            face_color = new int[foundAmt];
        texture_edge_a = new int[foundAmt];
        texture_edge_b = new int[foundAmt];
        texture_edge_c = new int[foundAmt];


        for (int i = 0; i < numberOfTriangleFaces; i++) {
            if (face_color[i] >= fromColor && face_color[i] <= fromcolor) {


                face_color[i] = tex;
                render_type[i] = 3 + set2;
                set2 += 4;
            }
        }
    }

    private void readOldModel(Class21[] is, int i) {
        int j = -870;
        aBoolean1618 = true;
        aBoolean1659 = false;
        Class21 class21 = is[i];
        numberOfVerticeCoordinates = class21.anInt369;
        anInt1630 = class21.anInt370;
        texture_faces = class21.anInt371;
        verticesParticle = new int[numberOfVerticeCoordinates];
        vertex_position_x = new int[numberOfVerticeCoordinates];
        vertex_position_y = new int[numberOfVerticeCoordinates];
        vertex_position_z = new int[numberOfVerticeCoordinates];
        triangle_edge_a = new int[anInt1630];
        triangle_edge_b = new int[anInt1630];
        while (j >= 0) {
            aBoolean1618 = !aBoolean1618;
        }
        triangle_edge_c = new int[anInt1630];
        texture_edge_a = new int[texture_faces];
        texture_edge_b = new int[texture_faces];
        texture_edge_c = new int[texture_faces];
        if (class21.anInt376 >= 0) {
            bone_skin = new int[numberOfVerticeCoordinates];
        }
        if (class21.anInt380 >= 0) {
            render_type = new int[anInt1630];
        }
        if (class21.anInt381 >= 0) {
            face_render_priorities = new int[anInt1630];
        } else {
            anInt1641 = -class21.anInt381 - 1;
        }
        if (class21.anInt382 >= 0) {
            anIntArray1639 = new int[anInt1630];
        }
        if (class21.anInt383 >= 0) {
            muscle_skin = new int[anInt1630];
        }
        face_color = new int[anInt1630];
        ByteBuffer stream = new ByteBuffer(class21.aByteArray368);
        stream.position = class21.anInt372;
        ByteBuffer stream_1 = new ByteBuffer(class21.aByteArray368);
        stream_1.position = class21.anInt373;
        ByteBuffer stream_2 = new ByteBuffer(class21.aByteArray368);
        stream_2.position = class21.anInt374;
        ByteBuffer stream_3 = new ByteBuffer(class21.aByteArray368);
        stream_3.position = class21.anInt375;
        ByteBuffer stream_4 = new ByteBuffer(class21.aByteArray368);
        stream_4.position = class21.anInt376;
        int k = 0;
        int l = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < numberOfVerticeCoordinates; j1++) {
            int k1 = stream.getUnsignedByte();
            int i2 = 0;
            if ((k1 & 1) != 0) {
                i2 = stream_1.method421();
            }
            int k2 = 0;
            if ((k1 & 2) != 0) {
                k2 = stream_2.method421();
            }
            int i3 = 0;
            if ((k1 & 4) != 0) {
                i3 = stream_3.method421();
            }

            vertex_position_x[j1] = k + i2;
            vertex_position_y[j1] = l + k2;
            vertex_position_z[j1] = i1 + i3;
            k = vertex_position_x[j1];
            l = vertex_position_y[j1];
            i1 = vertex_position_z[j1];
            if (bone_skin != null) {
                bone_skin[j1] = stream_4.getUnsignedByte();
            }
        }


        stream.position = class21.anInt379;
        stream_1.position = class21.anInt380;
        stream_2.position = class21.anInt381;
        stream_3.position = class21.anInt382;
        stream_4.position = class21.anInt383;
        for (int l1 = 0; l1 < anInt1630; l1++) {
            face_color[l1] = stream.getUnsignedShort();
            if (render_type != null) {
                render_type[l1] = stream_1.getUnsignedByte();
            }
            if (face_render_priorities != null) {
                face_render_priorities[l1] = stream_2.getUnsignedByte();
            }
            if (anIntArray1639 != null) {
                anIntArray1639[l1] = stream_3.getUnsignedByte();
            }
            if (muscle_skin != null) {
                muscle_skin[l1] = stream_4.getUnsignedByte();
            }
        }
        stream.position = class21.anInt377;
        stream_1.position = class21.anInt378;
        int j2 = 0;
        int l2 = 0;
        int j3 = 0;
        int k3 = 0;
        for (int l3 = 0; l3 < anInt1630; l3++) {
            int i4 = stream_1.getUnsignedByte();
            if (i4 == 1) {
                j2 = stream.method421() + k3;
                k3 = j2;
                l2 = stream.method421() + k3;
                k3 = l2;
                j3 = stream.method421() + k3;
                k3 = j3;
                triangle_edge_a[l3] = j2;
                triangle_edge_b[l3] = l2;
                triangle_edge_c[l3] = j3;
            }
            if (i4 == 2) {
                l2 = j3;
                j3 = stream.method421() + k3;
                k3 = j3;
                triangle_edge_a[l3] = j2;
                triangle_edge_b[l3] = l2;
                triangle_edge_c[l3] = j3;
            }
            if (i4 == 3) {
                j2 = j3;
                j3 = stream.method421() + k3;
                k3 = j3;
                triangle_edge_a[l3] = j2;
                triangle_edge_b[l3] = l2;
                triangle_edge_c[l3] = j3;
            }
            if (i4 == 4) {
                int k4 = j2;
                j2 = l2;
                l2 = k4;
                j3 = stream.method421() + k3;
                k3 = j3;
                triangle_edge_a[l3] = j2;
                triangle_edge_b[l3] = l2;
                triangle_edge_c[l3] = j3;
            }
        }
        stream.position = class21.anInt384;
        for (int j4 = 0; j4 < texture_faces; j4++) {
            texture_edge_a[j4] = stream.getUnsignedShort();
            texture_edge_b[j4] = stream.getUnsignedShort();
            texture_edge_c[j4] = stream.getUnsignedShort();
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
        printModelColours(this);
        int foundAmt = 0;
        int set2 = 0;
        for (int i = 0; i < face_color.length; i++)
            if (fromColor == face_color[i])
                foundAmt++;
        texture_faces = foundAmt;
        if (render_type == null)
            render_type = new int[foundAmt];
        if (face_color == null)
            face_color = new int[foundAmt];
        texture_edge_a = new int[foundAmt];
        texture_edge_b = new int[foundAmt];
        texture_edge_c = new int[foundAmt];
        int assigned = 0;
        for (int i = 0; i < anInt1630; i++) {
            if (fromColor == face_color[i]) {
                face_color[i] = tex;
                render_type[i] = 3 + set2;
                set2 += 4;
                texture_edge_a[assigned] = triangle_edge_a[i];
                texture_edge_b[assigned] = triangle_edge_b[i];
                texture_edge_c[assigned] = triangle_edge_c[i];
                assigned++;
            }
        }
    }

    public void setTexture(int tex) {
        texture_faces = anInt1630;
        int set2 = 0;
        if (render_type == null)
            render_type = new int[anInt1630];
        if (face_color == null)
            face_color = new int[anInt1630];
        texture_edge_a = new int[anInt1630];
        texture_edge_b = new int[anInt1630];
        texture_edge_c = new int[anInt1630];

        for (int i = 0; i < anInt1630; i++) {
            face_color[i] = tex;
            render_type[i] = 3 + set2;
            set2 += 4;
            texture_edge_a[i] = triangle_edge_a[i];
            texture_edge_b[i] = triangle_edge_b[i];
            texture_edge_c[i] = triangle_edge_c[i];
        }
    }

    public static void printModelColours(Model model) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : model.face_color) {
            list.add(i);
        }
        ArrayList<Integer> done = new ArrayList<Integer>();
        for (Integer i : list) {
            if (done.contains(i))
                continue;
            int amt = 0;
            for (Integer j : list) {
                if (j.intValue() == i.intValue())
                    amt++;
            }
            done.add(i);
        }
    }
    //

    public void method1337(int j) {
        for (int k = 0; k < anInt1630; k++) {
            face_color[k] = j;
        }
    }

    public void method1338(int j) {
        j += 100;
        int kcolor = 0;
        for (int k = 0; k < anInt1630; k++) {
            kcolor = face_color[k];
            if (k + j >= 0)
                face_color[k] = kcolor + j;
        }
    }

    public void method1339(int j) {
        j += 1;
        for (int k = 0; k < anInt1630; k++) {
            if (k + j >= 0)
                face_color[k] = k + j;
        }
    }

    public void downscale() {
        for (int i = 0; i < numberOfVerticeCoordinates; ++i) {
            vertex_position_x[i] = vertex_position_x[i] + 7 >> 2;
            vertex_position_y[i] = vertex_position_y[i] + 7 >> 2;
            vertex_position_z[i] = vertex_position_z[i] + 7 >> 2;
        }
    }

    public void scale2(int i) {
        for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
            vertex_position_x[i1] = vertex_position_x[i1] >> i;
            vertex_position_y[i1] = vertex_position_y[i1] >> i;
            vertex_position_z[i1] = vertex_position_z[i1] >> i;
        }
    }


    public void tint(double[] colorChange) {
        for (int k = 0; k < anInt1630; k++) {
            face_color[k] = change(face_color[k], colorChange);
        }
    }

    private short change(int hslColor, double[] colorChange) {
        Color c = JagexColor.RS2HSB_to_RGB1(hslColor);

        double red = (c.getRed() * colorChange[0]);
        double green = (c.getGreen() * colorChange[1]);
        double blue = (c.getBlue() * colorChange[2]);
        short hsl = (short) JagexColor.RGB_to_RS2HSB((int) (red), (int) (green),
                (int) (blue));
        return hsl;
    }

}