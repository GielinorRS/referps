package org.necrotic.client.cache.definition;

import org.necrotic.client.Client;
import org.necrotic.client.List;
import org.necrotic.client.cache.Archive;
import org.necrotic.client.graphics.DrawingArea;
import org.necrotic.client.graphics.Sprite;
import org.necrotic.client.graphics.fonts.RSFontSystem;
import org.necrotic.client.io.ByteBuffer;
import org.necrotic.client.world.Model;
import org.necrotic.client.world.Rasterizer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public final class ItemDefinition {
    public int rdc = 0;
    public int rdc2 = 0;
    public int rdc3 = 0;
    private static final int[] BLACK_FIX = {13101, 13672, 13675, 6568, 10636, 12158, 12159, 12160, 12161, 12162, 12163,
            12164, 12165, 12166, 12167, 12168, 12527, 18017, 18018, 18019, 18020, 3140, 13481, 14479, 14481, 19337,
            19342};
    private static ByteBuffer buffer;
    private static ItemDefinition[] cache;
    private static int cacheIndex;
    boolean animateInventory = true;
    private Object lentID;
    public static boolean isMembers = true;
    public static List mruNodes1 = new List(100);
    public static List mruNodes2 = new List(50);
    private static int[] streamIndices;
    public static int totalItems;


    public static void applyTexturing(Model model, int id) {
        switch (id) {
            /*case 22001:
                model.setTexture(56);
                break;
            case 22002:
                model.setTexture(52);
                break;
            case 22003:
                model.setTexture(51);
                break;
            case 22004:
                model.setTexture(57);
                break;

*/


            case 11790:
            case 23106:
                model.setTexture(57);
                break;

            case 299:
                model.setTexture(87);
                break;
            case 15084:
                model.setTexture(87);
                break;
            case 22006:
                model.setTexture(71);
                break;

            case 22005:
            case 23354:
                model.setTexture(52);
                break;
            case 7543:
            case 7544:
            case 7545:

                model.setTexture(92);
                break;
            case 16249:
            case 16265:
            case 15832:

                model.setTexture(86);
                break;
            case 17714:
            case 17686:
            case 15888:
            case 12994:
                model.setTexture(12);
                break;
            case 18683:
            case 13305:
            case 13306:
            case 13302:
            case 15511:
            case 11019:
                //	case 17606:
                model.setTexture(54);
                break;
            case 15328:
                model.setTexture(53);
                break;
            case 23779:
               // model.setTexture(74);
                break;
            case 15330:
                model.setTexture(76);
                break;
            case 23374:
                model.setTexture(52);
                break;
            case 21013:
                model.setTexture(55, 4018);
                break;
            case 4882:
            case 4888:
            case 4894:
            case 4900:
            case 18747:
            case 20460:
            case 20456:

                model.setTexture(71);
                break;
            case 17702:
            case 11766:
            case 11767:
                model.setTexture(58);
                break;
            case 22089:
            case 11759:
            case 11182:
            case 11181:
                model.setTexture(56);
                break;
            case 23832:
                model.setTexture(72);
                break;
            case 23839:
                model.setTexture(52);
                break;
            case 23891:
                model.setTexture(64);
                break;
            case 17043:
            case 17175:
            case 17321:
            case 15026:
            case 18332:
            case 10696:
                model.setTexture(68);
                break;
            /*case 7478:
                model.setTexture(76);
                break;*/
            case 20073:
            case 19800:
            case 19802:
            case 9929:
                model.setTexture(88);
                break;
            case 14733:
            case 14732:
            case 14734:
            case 14377:
            case 12864:
            case 10865:
                model.setTexture(69);
            case 14055:
            case 14056:
            case 14054:
                model.setTexture(91);
                break;
          /*  case 12630:

                model.setTexture(81);
                break;*/

            case 9080:
                model.setTexture(62);
                break;

            case 9081:
                model.setTexture(66);
                break;

            case 9082:
                model.setTexture(78);
                break;

            case 18883:
            case 17540:
                model.setTexture(76);
                break;
            case 18885:
                model.setTexture(78);

                break;
           /* case 12608:
                model.setTexture(66);
                break;*/
            case 4569:
                model.setTexture(60);
                break;
            case 6183:
                model.setTexture(61);
                break;
            case 15246:
            case 6855:
                model.setTexture(70);
                break;
            case 4570:
                model.setTexture(66);
                break;
            case 8840:
                model.setTexture(11, 10, 100);
                break;
            case 19892:
            case 11140:
            case 16137:

                model.setTexture(62);
                break;
            case 13640:
            case 3107:
            case 13964:
            case 15448:
                model.setTexture(40);

                break;
            case 15449:
                model.setTexture(64);

                break;
            case 15450:
                model.setTexture(62);

                break;
            case 20533:
                model.setTexture(51);
                break;
            case 7643:
                model.setTexture(80);
                break;
            case 14910:
            case 14911:
            case 14912:
            case 14913:
            case 14914:
            case 14915:
                model.setTexture(52);
                break;
            case 14921:
            case 14922:
            case 14923:
            case 14924:
            case 14925:
                model.setTexture(51);
                break;
            case 14916:
            case 14917:
            case 14918:
            case 14919:
            case 14920:
                model.setTexture(30);
                break;
            case 7642:
                model.setTexture(75);
                break;
            case 7927:
            case 20438:
                model.setTexture(57);
                break;

            case 19886:
            case 4446:
                model.setTexture(51);
                break;
            case 19888:
            case 18823:
                model.setTexture(60);
                break;
            case 18888:
            case 18818:
            case 16043:
            case 20118:
            case 12860:
            case 12565:
            case 2021:
            case 16140:
                model.setTexture(63);
                break;
            case 16133:
            case 15920:
                model.setTexture(67);
                break;


            case 20054:
            case 882:


            case 18784:
                model.setTexture(77);
                break;
            case 18783:
                model.setTexture(54);
                break;
            case 4387:
                model.setTexture(84);
                break;
            case 18665:
            case 3849:
                model.setTexture(82);
                break;
            case 16269:
            case 15943:
            case 15877:
                model.setTexture(76);
                break;
            case 19149:
            case 16055:
            case 16077:
            case 16066:
            case 16011:
            case 16114:
            case 12930:
                model.setTexture(60);
                break;
            case 16337:
            case 19887:
                model.setTexture(71);
            case 6629:
            case 15043:
            case 5424:
            case 5432:
                model.setTexture(81);
                break;
        }
    }

    public static void dumpItemModelsForId(int i) {
        try {
            ItemDefinition d = get(i);

            if (d != null) {
                int[] models = {d.maleEquip1, d.femaleEquip1, d.modelID,};

                for (int ids : models) {// 13655
                    if (ids > 0) {
                        try {
                            System.out.println("Dumping item model: " + ids);
                            byte abyte[] = Client.instance.decompressors[1].decompress(ids);
                            File map = new File("./models/" + ids + ".gz");
                            FileOutputStream fos = new FileOutputStream(map);
                            fos.write(abyte);
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static ItemDefinition get(int id) {
        for (int i = 0; i < 10; i++) {
            if (cache[i].id == id) {
                return cache[i];
            }
        }
        cacheIndex = (cacheIndex + 1) % 10;
        ItemDefinition itemDef = cache[cacheIndex];

        if (id > streamIndices.length){
            id = streamIndices.length - 1;
        }

        buffer.position = streamIndices[id];
        itemDef.id = id;
        //buffer.position = streamIndices[id];
        //	itemDef.readValues(buffer);

        itemDef.setDefaults();

        /*
         * if (Hardcode.readOSRSItem(itemDef)) { if (!itemDef.name.contains("hat") &&
         * !itemDef.name.contains("boot") && !itemDef.name.contains("cape")) {
         * itemDef.maleWieldY += 8; itemDef.femaleWieldY += 8; }
         *
         * if (itemDef.name.contains("hat")) { itemDef.maleWieldZ = 5;
         * itemDef.femaleWieldZ = 5; }
         *
         * } else { itemDef.readValues(buffer); }
         */
        itemDef.readValues(buffer);
        if (itemDef.oldColors != null) {
            for (int i2 = 0; i2 < itemDef.oldColors.length; i2++) {
                if (itemDef.newColors[i2] == 0) {
                    itemDef.newColors[i2] = 1;
                }
            }
        }

        for (int a : BLACK_FIX) {
            if (itemDef.id == a) {
                itemDef.oldColors = new int[1];
                itemDef.newColors = new int[1];
                itemDef.oldColors[0] = 0;
                itemDef.newColors[0] = 1;
            }
        }

        int customId = itemDef.id;
        itemDef = ItemDef2.newIDS(itemDef, id);
        itemDef = ItemDef3.newIDS1(itemDef, id);
        itemDef = ItemDef5.newIDS1(itemDef, id);
        itemDef = ItemDef4.newIDS(itemDef, id);


        if (customId >= 13700 && customId <= 13709) {
            /*
             * final ItemDefinition stat = get(14876); definition.name = "Tier " + (1 +
             * (customId - 13700)) + " Emblem"; definition.actions = stat.actions.clone();
             * //definition.modifiedModelColors = stat.modifiedModelColors.clone();
             * //definition.oldColors = stat.oldColors.clone();
             * definition.modelID = stat.modelID; definition.modelOffset1 =
             * stat.modelOffset1; definition.modelOffset2 = stat.modelOffset2;
             * definition.modelRotationY = stat.modelRotationY; definition.modelRotationX =
             * stat.modelRotationX; definition.groundActions = stat.groundActions;
             * definition.value = stat.value; definition.modelZoom = stat.modelZoom;
             * definition.certID = -1; definition.certTemplateID = -1; definition.stackable
             * = false;
             */
            itemDef.noteTemplate = -1;
            itemDef.note = -1;
            itemDef.stackable = false;
        }

        setCustomItemDefData(customId, itemDef);

        ItemDefMisc.setCustomItemDefData(customId, itemDef);
        ItemDefMisc2.setCustomItemDefData(customId, itemDef);

        if (itemDef.note != -1) {
            itemDef.toNote();
        }

        if (itemDef.lendTemplateID != -1) {
            itemDef.toLend();
        }

        if (!isMembers && itemDef.membersObject) {
            itemDef.name = "Members Object";
            itemDef.description = "Login to a members' server to use this object.".getBytes();
            itemDef.groundActions = null;
            itemDef.actions = null;
            itemDef.team = 0;
        }

        switch (itemDef.id) {

            case 20147:
                itemDef.oldColors = new int[2];
                itemDef.newColors = new int[2];
                itemDef.oldColors[0] = 4550;
                itemDef.newColors[0] = 1;
                itemDef.oldColors[1] = 4540;
                itemDef.newColors[1] = 1;
                break;
        }

        itemDef = ItemDef2.newIDS(itemDef, id);

        return FixedDefinitions.fix(itemDef, id); //
        //	return ItemDef3.newIDS1(itemDef, id);

    }

    private static void setCustomItemDefData(int customId, ItemDefinition itemDef) {

        if (customId == 13655) {
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.name = "Dragon kiteshield";
            itemDef.description = "A rare, protective kiteshield.".getBytes();
            itemDef.modelID = 13701;
            itemDef.modelZoom = 1560;
            itemDef.rotationY = 344;
            itemDef.rotationX = 1104;
            itemDef.rotationZ = 0;
            itemDef.modelOffsetX = -6;
            itemDef.modelOffsetY = -14;
            itemDef.maleEquip1 = 13700;
            itemDef.femaleEquip1 = 13700;
            itemDef.maleEquip2 = -1;
            itemDef.femaleEquip2 = -1;
            itemDef.maleDialogue = -1;
            itemDef.femaleDialogue = -1;
        }
        if (customId == 13996) {
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 64069;
            itemDef.maleEquip1 = 64070;// anInt165
            itemDef.femaleEquip1 = 64070;// anInt200
            itemDef.modelZoom = 467;
            itemDef.rotationY = 74;
            itemDef.rotationX = 0;
            itemDef.modelOffsetX = 0;
            itemDef.modelOffsetY = -4;
            itemDef.name = "Gilded helmet";
            itemDef.description = "A full-face helmet of Gilded.".getBytes();
            

        }
        if (customId == 13997) {
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 13995;
            itemDef.maleEquip1 = 13994;// anInt165
            itemDef.femaleEquip1 = 13994;// anInt200
            itemDef.modelZoom = 720;
            itemDef.rotationY = 396;
            itemDef.rotationX = 336;
            itemDef.modelOffsetX = 8;
            itemDef.modelOffsetY = 11;
            itemDef.name = "Death-touched Darts";
            itemDef.description = "Use these powerful darts to instantly slay any monster.".getBytes();
        }
        if (customId == 13999) {
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 13998;
            itemDef.maleEquip1 = 13999;// anInt165
            itemDef.femaleEquip1 = 13999;// anInt200
            itemDef.modelZoom = 789;
            itemDef.rotationY = 240;
            itemDef.rotationX = 60;
            itemDef.modelOffsetX = -1;
            itemDef.modelOffsetY = -23;
            itemDef.name = "Hydra claws";
            itemDef.description = "Viscosity has shaped them.".getBytes();
        }
        if (customId == 20051) {
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Open";
            itemDef.modelID = 61044;
            itemDef.modelZoom = 2300;
            itemDef.rotationY = 126;
            itemDef.rotationX = 1826;
            itemDef.modelOffsetX = 0;
            itemDef.modelOffsetY = 0;
            itemDef.name = "Archery kit";
        }
        if (customId == 22010) {
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 65527;// 65526;
            itemDef.maleEquip1 = 65527;// anInt165
            itemDef.femaleEquip1 = 65527;// anInt200
            itemDef.modelZoom = 2000;
            itemDef.rotationY = 240;
            itemDef.rotationX = 60;
            itemDef.modelOffsetX = -1;
            itemDef.modelOffsetY = -23;
            itemDef.name = "Ginrei Kojaku";
            itemDef.description = "Naruto is sooo overrated.".getBytes();
        }
        if (customId == 22011) {
            itemDef.name = "Ginrei Kojaku"; // Name
            itemDef.actions = new String[]{null, null, null, null, null};
            itemDef.noteTemplate = 22010;
            itemDef.note = 799;
            itemDef.rotationY = 552;
            itemDef.rotationX = 28;
            itemDef.modelOffsetX = 0;
            itemDef.modelOffsetY = 2;
            itemDef.modelID = 2429;
        }
        if (customId == 22012) {
            itemDef.modelID = 6277;
            itemDef.name = "Crimson's Katana"; // Name
            itemDef.modelZoom = 2025;
            itemDef.rotationY = 593;
            itemDef.rotationX = 2040;
            itemDef.modelOffsetX = 5;
            itemDef.modelOffsetY = 1;
            itemDef.value = 50000;
            itemDef.membersObject = true;
            itemDef.maleEquip1 = 5324;
            itemDef.femaleEquip1 = 5324;
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.actions[4] = "Destroy";
            itemDef.rdc2 = 25363;
        }

        if (customId == 20080) {
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.modelID = 65524;
            itemDef.maleEquip1 = 65522;// anInt165
            itemDef.maleEquip2 = 65523;
            itemDef.femaleEquip1 = 65522;// anInt200
            itemDef.modelZoom = 1506;
            itemDef.rotationY = 473;
            itemDef.rotationX = 2042;
            itemDef.modelOffsetX = 0;
            itemDef.modelOffsetY = 0;
            itemDef.name = "Metallica Shirt";
            itemDef.description = "Metallica.".getBytes();
        }
        if (customId == 20079) {
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 65520;
            itemDef.maleEquip1 = 65519;// anInt165
            itemDef.femaleEquip1 = 65519;// anInt200
            itemDef.modelZoom = 2128;
            itemDef.rotationY = 504;
            itemDef.rotationX = 0;
            itemDef.modelOffsetX = 0;
            itemDef.modelOffsetY = 1;
            itemDef.name = "Member Cape";
            itemDef.description = "It's a nice cape..".getBytes();
        }
        if (customId == 20081) {
            itemDef.modelID = 65270;
            itemDef.name = "200m Cape";
            itemDef.description = "We'd pat you on the back, but this cape would get in the way.".getBytes();
            itemDef.modelZoom = 1385;
            itemDef.modelOffsetX = 0;
            itemDef.modelOffsetY = 24;
            itemDef.rotationY = 279;
            itemDef.rotationX = 948;
            itemDef.oldColors = new int[]{65214, 65200, 65186, 62995, 64639};
            itemDef.newColors = new int[]{1, 6, 1, 5759, 5706};
            itemDef.maleEquip1 = 65297;
            itemDef.femaleEquip1 = 65297;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
        }
        if (customId == 22012) {
            itemDef.modelID = 6277;
            itemDef.name = "Crimson's Katana"; // Name
            itemDef.modelZoom = 2025;
            itemDef.rotationY = 593;
            itemDef.rotationX = 2040;
            itemDef.modelOffsetX = 5;
            itemDef.modelOffsetY = 1;
            itemDef.value = 50000;
            itemDef.membersObject = true;
            itemDef.maleEquip1 = 5324;
            itemDef.femaleEquip1 = 5324;
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.actions[4] = "Destroy";
            itemDef.rdc2 = 25363;
        }
        if (customId == 22035) {
            itemDef.name = "Armadyl Crossbow"; // Name
            itemDef.actions = new String[]{null, null, null, null, null};
            itemDef.noteTemplate = 22034;
            itemDef.note = 799;
            itemDef.stackable = true;
        }
        if (customId == 22042) {
            itemDef.name = "Black h'ween mask"; // Name
            itemDef.actions = new String[]{null, null, null, null, null};
            itemDef.noteTemplate = 22041;
            itemDef.note = 799;
            itemDef.stackable = true;
        }
        if (customId == 22046) {
            itemDef.name = "Dragonstone ring (e)"; // Name
            itemDef.actions = new String[]{null, null, null, null, null};
            itemDef.noteTemplate = 22045;
            itemDef.note = 799;
            itemDef.stackable = true;
        }
        if (customId == 22048) {
            itemDef.name = "Giant snake spine"; // Name
            itemDef.actions = new String[]{null, null, null, null, null};
            itemDef.noteTemplate = 22047;
            itemDef.note = 799;
            itemDef.stackable = true;
        }
        if (customId == 22065) { // maled
            itemDef.name = "Malediction ward";
            itemDef.actions = new String[]{null, null, null, null, null};
            itemDef.noteTemplate = 22064;
            itemDef.note = 799;
            itemDef.stackable = true;
        }

        if (customId == 22067) { // odium
            itemDef.actions = new String[]{null, null, null, null, null};
            itemDef.noteTemplate = 22066;
            itemDef.note = 799;
            itemDef.stackable = true;
        }
        if (customId == 22070) { // sword
            itemDef.name = "Shadowspike long";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65000;
            itemDef.femaleEquip1 = 65001;
            itemDef.maleEquip1 = 65001;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 22071) { // sword
            itemDef.name = "Sunrise sword";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65003;
            itemDef.femaleEquip1 = 65002;
            itemDef.maleEquip1 = 65002;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 22072) { // sword
            itemDef.name = "Death's sword";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65004;
            itemDef.femaleEquip1 = 65005;
            itemDef.maleEquip1 = 65005;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 22073) { // sword
            itemDef.name = "Forgiveness blade";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65006;
            itemDef.femaleEquip1 = 65007;
            itemDef.maleEquip1 = 65007;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            //itemDef.rdc2 = 981231;
        }
        if (customId == 20542) { // sword
            itemDef.name = "Satanic hellblade";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65006;
            itemDef.femaleEquip1 = 65007;
            itemDef.maleEquip1 = 65007;
            itemDef.modelOffsetX = -70;
            itemDef.rotationZ = 300;
            itemDef.modelOffsetY = 30;
            itemDef.modelZoom = 4000;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            
            itemDef.stackable = false;
            itemDef.rdc2 = 14432;
        }
        if (customId == 22074) { // sword
            itemDef.name = "Floreox scimitar";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65008;
            itemDef.femaleEquip1 = 65009;
            itemDef.maleEquip1 = 65009;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            itemDef.rdc2 = 78525;
            

        }
        if (customId == 22075) { // sword
            itemDef.name = "Starlight sword";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65010;
            itemDef.femaleEquip1 = 65011;
            itemDef.maleEquip1 = 65011;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 22076) { // sword
            itemDef.name = "Starbright long";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65010;
            itemDef.femaleEquip1 = 65011;
            itemDef.maleEquip1 = 65011;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            itemDef.rdc2 = 11020;
        }
        if (customId == 20533) { // sword
            itemDef.name = "Ryan's sycthe";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65189;
            itemDef.femaleEquip1 = 65190;
            itemDef.maleEquip1 = 65190;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;

        }
        if (customId == 7643) { // sword
            itemDef.name = "Ryan's sycthe";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65189;
            itemDef.femaleEquip1 = 65190;
            itemDef.maleEquip1 = 65190;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;

        }
        if (customId == 7642) { // sword
            itemDef.name = "Ryan's sycthe";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65189;
            itemDef.femaleEquip1 = 65190;
            itemDef.maleEquip1 = 65190;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;

        }
        if (customId == 22077) { // whip
            itemDef.name = "Brutal Tentacle";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65012;
            itemDef.femaleEquip1 = 65013;
            itemDef.maleEquip1 = 65013;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            itemDef.rdc2 = 5662;
            

        }
        if (customId == 22078) { // weapon
            itemDef.name = "Demon maul";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65014;
            itemDef.femaleEquip1 = 65015;
            itemDef.maleEquip1 = 65015;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            

        }
        if (customId == 12284) { // Darkcrab
            itemDef.name = "Scythe of hercules";
            ItemDefinition itemDef2 = ItemDefinition.get(1419);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65320;
            itemDef.femaleEquip1 = 65321;
            itemDef.maleEquip1 = 65321;
            itemDef.actions = itemDef2.actions;

            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = 400;
            itemDef.modelOffsetY = 1;
            itemDef.modelZoom = 3000;
            itemDef.rotationY = 600;
            itemDef.rotationX = 400;
            itemDef.stackable = false;

            
        }
        if (customId == 20553) { // Darkcrab
            itemDef.actions = new String[5];
            itemDef.modelID = 19219;
            itemDef.name = "PVM blowpipe";
            itemDef.modelZoom = 1158;
            itemDef.rotationX = 189;//189
            itemDef.rotationY = 768;//768
            itemDef.modelOffsetX = -7;
            itemDef.modelOffsetY = 4;
            itemDef.value = 20000000;
            itemDef.maleEquip1 = 14403;
            itemDef.femaleEquip1 = 14403;
            itemDef.actions[1] = "Wield";
            // itemDef.actions[2] = "Uncharge";
            //	itemDef.actions[3] = "Uncharge";
            itemDef.groundActions = new String[]{null, null, "Take", null, null};
            itemDef.rdc2 = 78787997;//23622
        }
        if (customId == 20554) { // Darkcrab
            itemDef.name = "Viggora's chainmace";
            ItemDefinition itemDef2 = ItemDefinition.get(4755);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65322;
            itemDef.femaleEquip1 = 65323;
            itemDef.maleEquip1 = 65323;
            itemDef.actions = itemDef2.actions;

            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            

            //	itemDef.rdc2 = 272356;
        }
        if (customId == 20555) { // Darkcrab
            itemDef.name = "Scythe of vitur";
            ItemDefinition itemDef2 = ItemDefinition.get(1419);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65320;
            itemDef.femaleEquip1 = 65321;
            itemDef.maleEquip1 = 65321;
            itemDef.actions = itemDef2.actions;

            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            

            //itemDef.rdc2 = 272356;
        }
        if (customId == 12285) { // Darkcrab
            itemDef.name = "Viggora's chainmace";
            ItemDefinition itemDef2 = ItemDefinition.get(4755);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65322;
            itemDef.femaleEquip1 = 65323;
            itemDef.maleEquip1 = 65323;
            itemDef.actions = itemDef2.actions;

            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 12283) { //
            itemDef.name = "Twisted Bow";
            ItemDefinition itemDef2 = ItemDefinition.get(1235);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65324;
            itemDef.femaleEquip1 = 65325;
            itemDef.maleEquip1 = 65325;
            itemDef.actions = itemDef2.actions;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 22078) { // weapon
            itemDef.name = "Demon maul";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65014;
            itemDef.femaleEquip1 = 65015;
            itemDef.maleEquip1 = 65015;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 19843) { // weapon
            itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
            itemDef.oldColors = new int[2]; // same here
            itemDef.oldColors[0] = 40; // the texture that it currently has
            itemDef.newColors[0] = 64; // the new texture u want it to have

            itemDef.oldColors[1] = 24; // the texture that it currently has
            itemDef.newColors[1] = 41; // the new texture u want it to have
            itemDef.name = "Archie minigun";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            itemDef.modelID = 65210;
            itemDef.femaleEquip1 = 65211;
            itemDef.maleEquip1 = 65211;// 95099
            itemDef.actions = new String[]{null, "Wield", null, null, "Destroy"};
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            
            itemDef.stackable = false;
        }
        if (customId == 19137) { // weapon
            itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
            itemDef.oldColors = new int[1]; // same here
            itemDef.oldColors[0] = 40; // the texture that it currently has
            itemDef.newColors[0] = 60; // the new texture u want it to have
            itemDef.name = "Iron minigun";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            itemDef.modelID = 65210;
            itemDef.femaleEquip1 = 65211;
            itemDef.maleEquip1 = 65211;// 95099
            itemDef.actions = new String[]{null, "Wield", null, null, "Destroy"};
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 19135) {
            itemDef.oldColors = new int[]{40, 24};
            itemDef.newColors = new int[]{66, 66};
            // itemDef.oldColors = new int[1]; // if only 1 texture is modified
            // this has to be 1, if 2 then 2 etc
            // itemDef.modifiedModelColors = new int[1]; // same here
            // itemDef.modifiedModelColors[0] = 40; // the texture that it currently has
            // itemDef.oldColors[0] = 66; // the new texture u want it to have
            itemDef.name = "Mithril minigun";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            itemDef.modelID = 65210;
            itemDef.femaleEquip1 = 65211;
            itemDef.maleEquip1 = 65211;// 95099
            itemDef.actions = new String[]{null, "Wield", null, null, "Destroy"};
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            
        }
        if (customId == 19136) { // weapon

            itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
            itemDef.oldColors = new int[2]; // same here
            itemDef.oldColors[0] = 40; // the texture that it currently has
            itemDef.newColors[0] = 66;
            itemDef.oldColors[1] = 24; // the texture that it currently has
            itemDef.newColors[1] = 75; // the new texture u want it to have
            itemDef.name = "Brutal minigun";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            itemDef.modelID = 65210;
            itemDef.femaleEquip1 = 65211;
            itemDef.maleEquip1 = 65211;// 95099
            itemDef.actions = new String[]{null, "Wield", null, null, "Destroy"};
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            

        }
        if (customId == 22079) { // weapon
            itemDef.name = "ankoue maul";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            
            itemDef.modelID = 65014;
            itemDef.femaleEquip1 = 65015;
            itemDef.maleEquip1 = 65015;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            itemDef.rdc2 = 123825;
        }
        if (customId == 22080) { // weapon
            itemDef.name = "Heavy chainsaw";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65016;
            itemDef.femaleEquip1 = 65017;
            itemDef.maleEquip1 = 65017;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            

        }
        if (customId == 22081) { // weapon
            itemDef.name = "Wooden chainsaw";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65016;
            itemDef.femaleEquip1 = 65017;
            itemDef.maleEquip1 = 65017;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            itemDef.rdc2 = 2364622;
        }
        if (customId == 22082) { // weapon
            itemDef.name = "dildo";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65018;
            itemDef.femaleEquip1 = 65019;
            itemDef.maleEquip1 = 65019;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            // itemDef.rdc2 = 2364622;
        }
        if (customId == 22083) { // weapon
            
            itemDef.copyItem(4710);
            itemDef.name = "Blast bow";
            itemDef.modelID = 100110;
            itemDef.maleEquip1 = 100111;
            itemDef.femaleEquip1 = 100111;
            itemDef.oldColors = new int[]{55};
            itemDef.newColors = new int[]{68};
            itemDef.stackable = false;

        }
        if (customId == 22084) { // weapon
            itemDef.copyItem(4710);
            itemDef.name = "Trinity hammers";
            itemDef.modelID = 100103;
            itemDef.maleEquip1 = 100104;
            itemDef.femaleEquip1 = 100104;
            itemDef.stackable = false;
            itemDef.oldColors = new int[]{52};
            itemDef.newColors = new int[]{52};
            
        }
        if (customId == 22085) { // weapon
            itemDef.name = "Karos Scimitar";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65430;
            itemDef.femaleEquip1 = 65431;
            itemDef.maleEquip1 = 65431;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            //	itemDef.rdc2 = 333333;
        }
        if (customId == 22086) { // weapon
            itemDef.name = "Karos offhand";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65430;
            itemDef.femaleEquip1 = 65432;
            itemDef.maleEquip1 = 65432;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;

        }
        if (customId == 22087) { // gun
            itemDef.name = "Burning Staff";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65020;
            itemDef.femaleEquip1 = 65021;
            itemDef.maleEquip1 = 65021;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 22088) { // gun
            itemDef.name = "AK-47";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65022;
            itemDef.femaleEquip1 = 65023;
            itemDef.maleEquip1 = 65023;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 22089) { // gun
            itemDef.name = "Rat's Golden Rifle";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65024;
            itemDef.femaleEquip1 = 65025;
            itemDef.maleEquip1 = 65025;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
        }
        if (customId == 22090) { // gun
            itemDef.name = "Golden Rifle";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65024;
            itemDef.femaleEquip1 = 65025;
            itemDef.maleEquip1 = 65025;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            itemDef.rdc2 = 99824;
        }
        if (customId == 22091) { // staff
            itemDef.name = "Legion scythe";
            ItemDefinition itemDef2 = ItemDefinition.get(15486);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65026;
            itemDef.femaleEquip1 = 65027;
            itemDef.maleEquip1 = 65027;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            itemDef.rdc2 = 235267;
        }
        if (customId == 22092) { // staff
            itemDef.copyItem(1391);
            itemDef.name = "Art's Staff";
            itemDef.modelZoom = 2000;
            itemDef.rotationZ = 10;
            itemDef.modelOffsetX = 10;
            itemDef.modelID = 100095;
            itemDef.maleEquip1 = 100096;
            itemDef.femaleEquip1 = 100096;
            itemDef.stackable = false;
            itemDef.oldColors = new int[]{52};
            itemDef.newColors = new int[]{79};
            
        }
        if (customId == 22093) { // sword
            itemDef.name = "Darklight long";
            ItemDefinition itemDef2 = ItemDefinition.get(20538);
            // itemDef.modelID = itemDef2.modelID;
            itemDef.modelID = 65010;
            itemDef.femaleEquip1 = 65011;
            itemDef.maleEquip1 = 65011;
            itemDef.modelOffsetX = itemDef2.modelOffsetX;
            itemDef.rotationZ = itemDef2.rotationZ;
            itemDef.modelOffsetY = itemDef2.modelOffsetY;
            itemDef.modelZoom = itemDef2.modelZoom;
            itemDef.rotationY = itemDef2.rotationY;
            itemDef.rotationX = itemDef2.rotationX;
            itemDef.stackable = false;
            itemDef.rdc2 = 3929;
        }
    }


    public static Sprite getSprite(int i, int j, int k) {
        if (k == 0) {
            Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);

            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }

            if (sprite != null) {
                return sprite;
            }
        }

        if (i > ItemDefinition.totalItems) {
            return null;
        }
        ItemDefinition definition = get(i);

        if (definition.stackIDs == null) {
            j = -1;
        }

        if (j > 1) {
            int i1 = -1;

            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= definition.stackAmounts[j1] && definition.stackAmounts[j1] != 0) {
                    i1 = definition.stackIDs[j1];
                }
            }

            if (i1 != -1) {
                definition = get(i1);
            }
        }

        Model model = definition.getInventoryModel(1);

        if (model == null) {
            return null;
        }

        Sprite sprite = null;

        if (definition.note != -1) {
            sprite = getSprite(definition.noteTemplate, 10, -1);

            if (sprite == null) {
                return null;
            }
        }

        if (definition.lendTemplateID != -1) {
            sprite = getSprite(definition.lendID, 50, 0);

            if (sprite == null) {
                return null;
            }
        }

        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.centerX;
        int l1 = Rasterizer.centerY;
        int ai[] = Rasterizer.lineOffsets;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.notTextured = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.clearDepthBuffer();
        Rasterizer.method364();
        int k3 = definition.modelZoom;

        if (k == -1) {
            k3 = (int) (k3 * 1.5D);
        }

        if (k > 0) {
            k3 = (int) (k3 * 1.04D);
        }

        int l3 = Rasterizer.SINE[definition.rotationY] * k3 >> 16;
        int i4 = Rasterizer.COSINE[definition.rotationY] * k3 >> 16;
        model.renderSingle(
                definition.rotationX,
                definition.rotationZ,
                definition.rotationY,
                definition.modelOffsetX, l3 + model.modelHeight / 2 + definition.modelOffsetY,
                i4 + definition.modelOffsetY);

        sprite2.outline(1);
        if (k > 0)
            sprite2.outline(16777215);
        if (k == 0)
            sprite2.shadow(3153952);
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);

        if (definition.note != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }

        if (definition.lendTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }

        /*
         * if (k == 0) { mruNodes1.removeFromCache(sprite2, i); }
         */
        // Client.instance.method37(i, -1);
        if (k == 0 && !definition.animateInventory) {
            mruNodes1.removeFromCache(sprite2, (long) i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
        DrawingArea.setBounds(k2, i3, l2, j3);
        Rasterizer.centerX = k1;
        Rasterizer.centerY = l1;
        Rasterizer.lineOffsets = ai;
        Rasterizer.notTextured = true;

        if (definition.stackable) {
            sprite2.maxWidth = 33;
        } else {
            sprite2.maxWidth = 32;
        }

        sprite2.maxHeight = j;
        return sprite2;
    }

    public static Sprite getSizedSprite(int i, int j, int k, int width, int height) {
        if (k == 0) {
            Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);

            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }

            if (sprite != null) {
                return sprite;
            }
        }

        if (i > ItemDefinition.totalItems) {
            return null;
        }
        ItemDefinition definition = get(i);

        if (definition.stackIDs == null) {
            j = -1;
        }

        if (j > 1) {
            int i1 = -1;

            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= definition.stackAmounts[j1] && definition.stackAmounts[j1] != 0) {
                    i1 = definition.stackIDs[j1];
                }
            }

            if (i1 != -1) {
                definition = get(i1);
            }
        }

        Model model = definition.getInventoryModel(1);

        if (model == null) {
            return null;
        }

        Sprite sprite = null;

        if (definition.note != -1) {
            sprite = getSprite(definition.noteTemplate, 10, -1);

            if (sprite == null) {
                return null;
            }
        }

        if (definition.lendTemplateID != -1) {
            sprite = getSprite(definition.lendID, 50, 0);

            if (sprite == null) {
                return null;
            }
        }

        Sprite sprite2 = new Sprite(width, height);
        int k1 = Rasterizer.centerX;
        int l1 = Rasterizer.centerY;
        int ai[] = Rasterizer.lineOffsets;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.notTextured = false;
        DrawingArea.initDrawingArea(height, width, sprite2.myPixels);
        DrawingArea.drawPixels(height, 0, 0, 0, width);
        Rasterizer.clearDepthBuffer();
        Rasterizer.method364();
        int k3 = definition.modelZoom;

        if (k == -1) {
            k3 = (int) (k3 * 1.5D);
        }

        if (k > 0) {
            k3 = (int) (k3 * 1.04D);
        }

        k3 /= (width / 32D);

        int l3 = Rasterizer.SINE[definition.rotationY] * k3 >> 16;
        int i4 = Rasterizer.COSINE[definition.rotationY] * k3 >> 16;
        model.renderSingle(
                definition.rotationX,
                definition.rotationZ,
                definition.rotationY,
                definition.modelOffsetX, l3 + model.modelHeight / 2 + definition.modelOffsetY,
                i4 + definition.modelOffsetY);

        sprite2.outline(1);
        if (k > 0)
            sprite2.outline(16777215);
        if (k == 0)
            sprite2.shadow(3153952);
        DrawingArea.initDrawingArea(height, width, sprite2.myPixels);

        if (definition.note != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = width;
            sprite.maxHeight = height;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }

        if (definition.lendTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = width;
            sprite.maxHeight = height;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }

        /*
         * if (k == 0) { mruNodes1.removeFromCache(sprite2, i); }
         */
        // Client.instance.method37(i, -1);
        if (k == 0 && !definition.animateInventory) {
            mruNodes1.removeFromCache(sprite2, (long) i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
        DrawingArea.setBounds(k2, i3, l2, j3);
        Rasterizer.centerX = k1;
        Rasterizer.centerY = l1;
        Rasterizer.lineOffsets = ai;
        Rasterizer.notTextured = true;

        if (definition.stackable) {
            sprite2.maxWidth = width;
        } else {
            sprite2.maxWidth = width;
        }

        sprite2.maxHeight = j;
        return sprite2;
    }

    public static HashMap<Integer, Sprite> spriteCacheEffectTimers = new HashMap<>();

    public static Sprite getSprite(int i, int j, int k, double zoom, boolean effectTimers) {
        if (k == 0 && zoom != -1) {
            Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);

            if (effectTimers) {
                sprite = spriteCacheEffectTimers.get(i);
            }

            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }

            if (sprite != null) {
                return sprite;
            }
        }

        ItemDefinition definition = get(i);

        if (definition.stackIDs == null) {
            j = -1;
        }

        if (j > 1) {
            int i1 = -1;

            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= definition.stackAmounts[j1] && definition.stackAmounts[j1] != 0) {
                    i1 = definition.stackIDs[j1];
                }
            }

            if (i1 != -1) {
                definition = get(i1);
            }
        }

        Model model = definition.getInventoryModel(1);

        if (model == null) {
            return null;
        }

        Sprite sprite = null;

        if (definition.note != -1) {
            sprite = getSprite(definition.noteTemplate, 10, -1);

            if (sprite == null) {
                return null;
            }
        }

        if (definition.lendTemplateID != -1) {
            sprite = getSprite(definition.lendID, 50, 0);

            if (sprite == null) {
                return null;
            }
        }

        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.centerX;
        int l1 = Rasterizer.centerY;
        int ai[] = Rasterizer.lineOffsets;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.notTextured = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.clearDepthBuffer();
        Rasterizer.method364();
        int k3 = definition.modelZoom;
        if (zoom != -1 && zoom != 0)
            k3 = (int) ((double) (k3 * zoom));
        if (k == -1) {
            k3 = (int) (k3 * 1.5D);
        }

        if (k > 0) {
            k3 = (int) (k3 * 1.04D);
        }

        int l3 = Rasterizer.SINE[definition.rotationY] * k3 >> 16;
        int i4 = Rasterizer.COSINE[definition.rotationY] * k3 >> 16;
        model.renderSingle(definition.rotationX, definition.rotationZ, definition.rotationY,
                definition.modelOffsetX, l3 + model.modelHeight / 2 + definition.modelOffsetY,
                i4 + definition.modelOffsetY);

        sprite2.outline(1);
        if (k > 0)
            sprite2.outline(16777215);
        if (k == 0)
            sprite2.shadow(3153952);
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);

        if (definition.note != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }

        if (definition.lendTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }

        /*
         * if (k == 0) { mruNodes1.removeFromCache(sprite2, i); }
         */
/*
        if (k == 0 && !definition.animateInventory) {
            mruNodes1.removeFromCache(sprite2, (long) i);
        }*/

        // Client.instance.method37(i, -1);

        if (k == 0 && i != 5572 && i != 5573 && i != 640 && i != 650 && i != 630&& !definition.animateInventory) {
            if (effectTimers) {
                spriteCacheEffectTimers.put(i, sprite2);
            } else {
                mruNodes1.removeFromCache(sprite2, (long) i);
            }
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
        DrawingArea.setBounds(k2, i3, l2, j3);
        Rasterizer.centerX = k1;
        Rasterizer.centerY = l1;
        Rasterizer.lineOffsets = ai;
        Rasterizer.notTextured = true;

        if (definition.stackable) {
            sprite2.maxWidth = 33;
        } else {
            sprite2.maxWidth = 32;
        }

        sprite2.maxHeight = j;
        return sprite2;
    }

    public static void nullify() {
        mruNodes2 = null;
        mruNodes1 = null;
        streamIndices = null;
        cache = null;
        buffer = null;
        spriteCacheEffectTimers = null;

    }
    public static void clearCache() {
        mruNodes2.unlinkAll();
        mruNodes1.unlinkAll();
    }

    public static void unpackConfig(Archive streamLoader) {
        buffer = new ByteBuffer(streamLoader.get("obj.dat"));
        ByteBuffer stream = new ByteBuffer(streamLoader.get("obj.idx"));
        totalItems = stream.getUnsignedShort() + 10000;
        streamIndices = new int[totalItems];
        int i = 2;

        for (int j = 0; j < totalItems; j++) {
            streamIndices[j] = i;
            i += stream.getUnsignedShort();
        }

        cache = new ItemDefinition[10];

        for (int k = 0; k < 10; k++) {
            cache[k] = new ItemDefinition();
        }
    }

    public String[] actions;
    private int anInt162;
    int femaleEquip2;
    public int maleEquip1;
    private int anInt166;
    public int scaleX;
    private int anInt173;
    public int maleDialogue;
    private int anInt184;
    private int anInt185;
    int maleEquip2; // male arms
    public int scaleZ;
    public int scaleY;

    public double maleModelScale = 1;

    private int anInt196;
    public int femaleDialogue;
    public int femaleEquip1;
    public int rotationZ;
    public int noteTemplate;
    public int note;
    public byte[] description;
    public byte femaleWieldX;
    public byte femaleWieldY;
    public byte femaleWieldZ;
    public String[] groundActions;
    public int id;
    public int lendID;
    private int lendTemplateID;
    public byte maleWieldX;
    public byte maleWieldY;
    public byte maleWieldZ;
    public boolean membersObject;
    public int modelID;
    public int modelOffsetX;
    public int modelOffsetY;
    public int rotationY;
    public int rotationX;
    public int modelZoom;
    public int[] oldColors;
    public String name;
    public int[] newColors;
    public boolean stackable;
    public int[] stackAmounts;
    public int[] stackIDs;
    public int team;
    public int value;
    public byte[] customSpriteLocation;

    public ItemDefinition() {
        id = -1;
    }

    public void copyItem(int id) {
        this.setDefaults();
        ItemDefinition target = ItemDefinition.get(id);
        this.modelID = target.modelID;
        this.maleEquip1 = target.maleEquip1;
        this.femaleEquip1 = target.femaleEquip1;
       // this.maleEquip2 = target.maleEquip2;
       // this.femaleEquip2 = target.femaleEquip2;
        this.modelZoom = target.modelZoom;
        this.rotationY = target.rotationY;
        this.rotationX = target.rotationX;
        this.rotationZ = target.rotationZ;
        this.modelOffsetX = target.modelOffsetX;
        this.modelOffsetY = target.modelOffsetY;
        this.actions = target.actions;
        this.maleDialogue = target.maleDialogue;
        this.stackable = target.stackable;
        this.stackIDs = target.stackIDs;
        this.stackAmounts = target.stackAmounts;
        this.scaleX = target.scaleX;
        this.scaleY = target.scaleY;
        this.scaleZ = target.scaleZ;
    }

    public Model getInventoryModel(int amount) {
        if (stackIDs != null && amount > 1) {
            int id = -1;

            for (int i = 0; i < 10; i++) {
                if (amount >= stackAmounts[i] && stackAmounts[i] != 0) {
                    id = stackIDs[i];
                }
            }

            if (id != -1) {
                return get(id).getInventoryModel(1);
            }
        }

        Model model = (Model) mruNodes2.insertFromCache(id);

        if (model != null) {
            return model;
        }

        model = Model.fetchModel(modelID, rs3, osrs);

        if (model == null) {
            return null;
        }

        if (oldColors != null) {
            for (int l = 0; l < oldColors.length; l++) {
                model.method476(oldColors[l], newColors[l]);
            }
        }

        if (scaleX != 128 || scaleY != 128 || scaleZ != 128) {
            model.scaleT(scaleX, scaleZ, scaleY);
        }
        if (rdc > 0)
            model.method1337(rdc);
        if (rdc2 != 0)
            model.method1338(rdc2);
        if (rdc3 != 0)
            model.method1339(rdc3);
        applyTexturing(model, id);

        if (colorChange != null)
            Objects.requireNonNull(model).tint(colorChange);

        model.light(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
        model.aBoolean1659 = true;
        mruNodes2.removeFromCache(model, id);
        return model;
    }

    public boolean dialogueModelFetched(int j) {
        int k = maleDialogue;
        int l = anInt166;

        if (j == 1) {
            k = femaleDialogue;
            l = anInt173;
        }

        if (k == -1) {
            return true;
        }

        boolean flag = true;

        if (!Model.method463(k, rs3, osrs)) {
            flag = false;
        }

        if (l != -1 && !Model.method463(l, rs3, osrs)) {
            flag = false;
        }

        return flag;
    }

    public Model method194(int j) {
        int k = maleDialogue;
        int l = anInt166;

        if (j == 1) {
            k = femaleDialogue;
            l = anInt173;
        }

        if (k == -1) {
            return null;
        }

        Model model = Model.fetchModel(k, rs3, osrs);

        if (scaleX != 128 || scaleY != 128 || scaleZ != 128) {
            model.scaleT(scaleX, scaleZ, scaleY);
        }
        if (rdc > 0)
            model.method1337(rdc);
        if (rdc2 != 0)
            model.method1338(rdc2);
        if (rdc3 != 0)
            model.method1339(rdc3);

        applyTexturing(model, id);
        if (l != -1) {
            Model model_1 = Model.fetchModel(l, rs3, osrs);
            Model models[] = {model, model_1};
            model = new Model(2, models);
        }

        if (oldColors != null) {
            for (int i1 = 0; i1 < oldColors.length; i1++) {
                model.method476(oldColors[i1], newColors[i1]);
            }
        }
        applyTexturing(model, id);
        if (colorChange != null)
            Objects.requireNonNull(model).tint(colorChange);
        return model;
    }

    public boolean method195(int j, boolean checkMaxPoly) {
        int k = maleEquip1;
        int l = maleEquip2;
        int i1 = anInt185;

        if (j == 1) {
            k = femaleEquip1;
            l = femaleEquip2;
            i1 = anInt162;
        }

        if(checkMaxPoly){
            if(Model.tooManyPolygons(k))
                k = -1;
            if(Model.tooManyPolygons(l))
                l = -1;
            if(Model.tooManyPolygons(i1))
                i1 = -1;
        }

        if (k == -1) {
            return true;
        }

        boolean flag = true;

        if (!Model.method463(k, rs3, osrs)) {
            flag = false;
        }

        if (l != -1 && !Model.method463(l, rs3, osrs)) {
            flag = false;
        }

        if (i1 != -1 && !Model.method463(i1, rs3, osrs)) {
            flag = false;
        }

        return flag;
    }

    public Model method196(int i, boolean checkMaxPoly) {
        int j = maleEquip1;
        int k = maleEquip2;
        int l = anInt185;

        if (i == 1) {
            j = femaleEquip1;
            k = femaleEquip2;
            l = anInt162;
        }

        if(checkMaxPoly){
            if(Model.tooManyPolygons(k))
                k = -1;
            if(Model.tooManyPolygons(l))
                l = -1;
            if(Model.tooManyPolygons(l))
                l = -1;
        }

        if (j == -1) {
            return null;
        }

        Model model = Model.fetchModel(j, rs3, osrs);

        if (scaleX != 128 || scaleY != 128 || scaleZ != 128) {
            model.scaleT(scaleX, scaleZ, scaleY);
        }
        if (rdc > 0)
            model.method1337(rdc);
        if (rdc2 != 0)
            model.method1338(rdc2);
        if (rdc3 != 0)
            model.method1339(rdc3);
        applyTexturing(model, id);
        if (colorChange != null)
            Objects.requireNonNull(model).tint(colorChange);
        if (k != -1) {
            if (l != -1) {
                Model model_1 = Model.fetchModel(k, rs3, osrs);
                Model model_3 = Model.fetchModel(l, rs3, osrs);
                Model model_1s[] = {model, model_1, model_3};
                model = new Model(3, model_1s);
            } else {
                Model model_2 = Model.fetchModel(k, rs3, osrs);
                Model models[] = {model, model_2};
                model = new Model(2, models);
            }
        }


        if (maleModelScale != 1) {
            model.scaleT((int)(128D / maleModelScale), (int)(128D /maleModelScale), (int)(128D /maleModelScale));
        }

        if (i == 0 && (maleWieldX != 0 || maleWieldY != 0 || maleWieldZ != 0)) {
            model.translate(maleWieldX, maleWieldY, maleWieldZ);
        }

        if (i == 1 && (femaleWieldX != 0 || femaleWieldY != 0 || femaleWieldZ != 0)) {
            model.translate(femaleWieldX, femaleWieldY, femaleWieldZ);
        }


        if (slot == 0){
            model.translate(0, 6, 0);
        }
        if (slot == 4 ){
            model.translate(0, 5, 0);
        }
        if (slot == 3  ||  slot == 2  ||  slot == 1){
            model.translate(0, 5, 0);
        }
        if (slot == 9){
            model.translate(0, 4, 0);
        }
        if (slot == 7){
            model.scaleT(128, 128, 133);
            model.translate(0, 5, 0);
        }

        if (switchHands) {
            model.rotate180();
        }

        if (oldColors != null) {
            for (int i1 = 0; i1 < oldColors.length; i1++) {
                model.method476(oldColors[i1], newColors[i1]);
            }
        }
        applyTexturing(model, id);
        if (colorChange != null)
            Objects.requireNonNull(model).tint(colorChange);
        return model;
    }

    public Model getInterfaceModel(int i) {
        if (stackIDs != null && i > 1) {
            int j = -1;

            for (int k = 0; k < 10; k++) {
                if (i >= stackAmounts[k] && stackAmounts[k] != 0) {
                    j = stackIDs[k];
                }
            }

            if (j != -1) {
                return get(j).getInterfaceModel(1);
            }
        }

        Model model = Model.fetchModel(modelID, rs3, osrs);
        if (model != null){

            if (scaleX != 128 || scaleY != 128 || scaleZ != 128) {
                model.scaleT(scaleX, scaleZ, scaleY);
            }



        if (rdc > 0)
            model.method1337(rdc);
        if (rdc2 != 0)
            model.method1338(rdc2);
        if (rdc3 != 0)
            model.method1339(rdc3);
        applyTexturing(model, id);


        }


        if (model == null) {
            return null;
        }

        if (oldColors != null) {
            for (int l = 0; l < oldColors.length; l++) {
                model.method476(oldColors[l], newColors[l]);
            }
        }
        applyTexturing(model, id);
        if (colorChange != null)
            Objects.requireNonNull(model).tint(colorChange);
        return model;
    }

    private void readValues(ByteBuffer buffer) {
        do {
            int opcode = buffer.getUnsignedByte();

            if (opcode == 0) {
                return;
            } else if (opcode == 1) {
                modelID = buffer.getUnsignedShort();
            } else if (opcode == 2) {
                name = buffer.getString();
            } else if (opcode == 3) {
                description = buffer.getBytes();
            } else if (opcode == 4) {
                modelZoom = buffer.getUnsignedShort();
            } else if (opcode == 5) {
                rotationY = buffer.getUnsignedShort();
            } else if (opcode == 6) {
                rotationX = buffer.getUnsignedShort();
            } else if (opcode == 7) {
                modelOffsetX = buffer.getUnsignedShort();

                if (modelOffsetX > 32767) {
                    modelOffsetX -= 0x10000;
                }
            } else if (opcode == 8) {
                modelOffsetY = buffer.getUnsignedShort();

                if (modelOffsetY > 32767) {
                    modelOffsetY -= 0x10000;
                }
            } else if (opcode == 10) {
                buffer.getUnsignedShort();
            } else if (opcode == 11) {
                stackable = true;
            } else if (opcode == 12) {
                value = buffer.getIntLittleEndian();
            } else if (opcode == 16) {
                membersObject = true;
            } else if (opcode == 23) {
                maleEquip1 = buffer.getUnsignedShort();
                maleWieldY = buffer.getSignedByte();
            } else if (opcode == 24) {
                maleEquip2 = buffer.getUnsignedShort();
            } else if (opcode == 25) {
                femaleEquip1 = buffer.getUnsignedShort();
                femaleWieldY = buffer.getSignedByte();
            } else if (opcode == 26) {
                femaleEquip2 = buffer.getUnsignedShort();
            } else if (opcode >= 30 && opcode < 35) {
                if (groundActions == null) {
                    groundActions = new String[5];
                }

                groundActions[opcode - 30] = buffer.getString();

                if (groundActions[opcode - 30].equalsIgnoreCase("hidden")) {
                    groundActions[opcode - 30] = null;
                }
            } else if (opcode >= 35 && opcode < 40) {
                if (actions == null) {
                    actions = new String[5];
                }

                actions[opcode - 35] = buffer.getString();
            } else if (opcode == 40) {
                int size = buffer.getUnsignedByte();
                oldColors = new int[size];
                newColors = new int[size];

                for (int k = 0; k < size; k++) {
                    oldColors[k] = buffer.getUnsignedShort();
                    newColors[k] = buffer.getUnsignedShort();
                }
            } else if (opcode == 78) {
                anInt185 = buffer.getUnsignedShort();
            } else if (opcode == 79) {
                anInt162 = buffer.getUnsignedShort();
            } else if (opcode == 90) {
                maleDialogue = buffer.getUnsignedShort();
            } else if (opcode == 91) {
                femaleDialogue = buffer.getUnsignedShort();
            } else if (opcode == 92) {
                anInt166 = buffer.getUnsignedShort();
            } else if (opcode == 93) {
                anInt173 = buffer.getUnsignedShort();
            } else if (opcode == 95) {
                rotationZ = buffer.getUnsignedShort();
            } else if (opcode == 97) {
                noteTemplate = buffer.getUnsignedShort();
            } else if (opcode == 98) {
                note = buffer.getUnsignedShort();
            } else if (opcode >= 100 && opcode < 110) {
                if (stackIDs == null) {
                    stackIDs = new int[10];
                    stackAmounts = new int[10];
                }

                stackIDs[opcode - 100] = buffer.getUnsignedShort();
                stackAmounts[opcode - 100] = buffer.getUnsignedShort();
            } else if (opcode == 110) {
                scaleX = buffer.getUnsignedShort();
            } else if (opcode == 111) {
                scaleY = buffer.getUnsignedShort();
            } else if (opcode == 112) {
                scaleZ = buffer.getUnsignedShort();
            } else if (opcode == 113) {
                anInt196 = buffer.getSignedByte();
            } else if (opcode == 114) {
                anInt184 = buffer.getSignedByte() * 5;
            } else if (opcode == 115) {
                team = buffer.getUnsignedByte();
            } else if (opcode == 121) {
                lendID = buffer.getUnsignedShort();
            } else if (opcode == 122) {
                lendTemplateID = buffer.getUnsignedShort();
            }
        } while (true);
    }

    private void setDefaults() {
        modelID = 0;
        switchHands = false;
        rs3 = false;
        osrs = false;
        colorChange = null;
        name = null;
        description = null;
        newColors = null;
        oldColors = null;
        modelZoom = 2000;
        rotationY = 0;
        rotationX = 0;
        rotationZ = 0;
        modelOffsetX = 0;
        modelOffsetY = 0;
        stackable = false;
        value = 1;
        membersObject = false;
        groundActions = null;
        actions = null;
        lendID = -1;
        lendTemplateID = -1;
        maleEquip1 = -1;
        maleEquip2 = -1;
        femaleEquip1 = -1;
        femaleEquip2 = -1;
        anInt185 = -1;
        anInt162 = -1;
        maleDialogue = -1;
        anInt166 = -1;
        femaleDialogue = -1;
        anInt173 = -1;
        stackIDs = null;
        stackAmounts = null;
        noteTemplate = -1;
        note = -1;
        scaleX = 128;
        scaleY = 128;
        scaleZ = 128;
        maleModelScale = 1;
        anInt196 = 0;
        anInt184 = 0;
        team = 0;
        rdc = 0;
        rdc2 = 0;
        rdc3 = 0;
        femaleWieldY = 0;
        femaleWieldX = 0;
        femaleWieldZ = 0;
        maleWieldX = 0;
        maleWieldZ = 0;
        maleWieldY = 0;
        slot = -1;
    }

    private void toLend() {
        ItemDefinition itemDef = get(lendTemplateID);
        actions = new String[5];
        modelID = itemDef.modelID;
        modelOffsetX = itemDef.modelOffsetX;
        rotationX = itemDef.rotationX;
        modelOffsetY = itemDef.modelOffsetY;
        modelZoom = itemDef.modelZoom;
        rotationY = itemDef.rotationY;
        rotationZ = itemDef.rotationZ;
        value = 0;
        ItemDefinition definition = get(lendID);
        anInt166 = definition.anInt166;
        newColors = definition.newColors;
        anInt185 = definition.anInt185;
        femaleEquip1 = definition.femaleEquip1;
        anInt173 = definition.anInt173;
        maleDialogue = definition.maleDialogue;
        groundActions = definition.groundActions;
        maleEquip1 = definition.maleEquip1;
        name = definition.name;
        maleEquip2 = definition.maleEquip2;
        membersObject = definition.membersObject;
        femaleDialogue = definition.femaleDialogue;
        femaleEquip2 = definition.femaleEquip2;
        anInt162 = definition.anInt162;
        oldColors = definition.oldColors;
        team = definition.team;

        if (definition.actions != null) {
            for (int i = 0; i < 4; i++) {
                actions[i] = definition.actions[i];
            }
        }

        actions[4] = "Discard";
    }

    private void toNote() {
        ItemDefinition definition = get(note);
        modelID = definition.modelID;
        modelZoom = definition.modelZoom;
        rotationY = definition.rotationY;
        rotationX = definition.rotationX;
        rotationZ = definition.rotationZ;
        modelOffsetX = definition.modelOffsetX;
        modelOffsetY = definition.modelOffsetY;
        oldColors = definition.oldColors;
        newColors = definition.newColors;
        rdc = definition.rdc;
        rdc2 = definition.rdc2;
        rdc3 = definition.rdc3;

        definition = get(noteTemplate);
        if (id < 23390)
        name = definition.name;
        membersObject = definition.membersObject;
        value = definition.value;
        String s = "a";
        char c = definition.name.charAt(0);

        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            s = "an";
        }

        description = ("Swap this note at any bank for " + s + " " + definition.name + ".").getBytes();
        if (id < 23390)
            stackable = true;
        else
            stackable = false;
    }

    public static void printDefinitionsForId(int itemId) {
        /* Print out Grain */
        ItemDefinition dumpitem = ItemDefinition.get(itemId);
        if (dumpitem.name != null) {
            System.out.println("Dumping: " + dumpitem.name);
        } else {
            System.out.println("ItemDefinition.get(" + itemId + ").name == null");
        }
        System.out.println("itemId: " + dumpitem.id);
        System.out.println("modelId: " + dumpitem.modelID);
        System.out.println("maleWearId: " + dumpitem.maleEquip1);
        System.out.println("femaleWearId: " + dumpitem.femaleEquip1);
        System.out.println("modelOffset1: " + dumpitem.modelOffsetX);
        System.out.println("modelOffSetX: " + dumpitem.rotationZ);
        System.out.println("modelOffSetY: " + dumpitem.modelOffsetY);
        System.out.println("modelRotationY: " + dumpitem.rotationY);
        System.out.println("modelRotationX: " + dumpitem.rotationX);
        System.out.println("modelZoom: " + dumpitem.modelZoom);
        // System.out.println("def "+dumpitem);
        if (dumpitem.oldColors != null) {
            for (int i = 0; i < dumpitem.oldColors.length; i++) {
                System.out.println("modifiedModelColors[" + i + "]: " + dumpitem.oldColors[i]);
            }
        }
        if (dumpitem.newColors != null) {
            for (int i = 0; i < dumpitem.newColors.length; i++) {
                System.out.println("oldColors[" + i + "]: " + dumpitem.newColors[i]);
            }
        }
        if (dumpitem.actions != null) {
            for (int i = 0; i < dumpitem.actions.length; i++) {
                System.out.println("Action[" + i + "]: " + dumpitem.actions[i]);
            }
        }
        if (dumpitem.groundActions != null) {
            for (int i = 0; i < dumpitem.groundActions.length; i++) {
                System.out.println("groundAction[" + i + "]: " + dumpitem.groundActions[i]);
            }
        }
    }

    public static void dump() {
        File f = new File("itemnames.txt");
        System.out.println("Dumping Item names..");
        // String[] variableNames = new String[] { "name", };
        try {
            f.createNewFile();
            BufferedWriter bf = new BufferedWriter(new FileWriter(f));
            for (int index = 0; index < totalItems ; index++) {
                ItemDefinition def = get(index);
                String name = def.name;

                name = RSFontSystem.removeColors(name);

                if (name != null) {
                    bf.write("Item Id: " + index);
                    bf.newLine();
                    bf.write("name: " + name);
                    bf.newLine();
                    bf.write("finish");
                    bf.newLine();
                    bf.newLine();
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Dumping Complete!");
    }


    public void recolor(int targetColor, int newColor) { // edited by Suic


        int firstFreeSlot = oldColors.length;

        if (oldColors != null) {
            firstFreeSlot = oldColors.length;
        }
        for (int i = 0; i < oldColors.length; i++) {
            if (oldColors[i] == 0) {
                firstFreeSlot = i;
            }
            if (oldColors[i] == targetColor) {
                System.out.println("Was same");
                newColors[i] = newColor;
                return;
            }
        }
        if (firstFreeSlot == oldColors.length) {
            int newLength = firstFreeSlot + 1;
            System.out.println("Set newLength(param) to firstFreeSlot + 1");
            int targetColors[] = new int[newLength];
            int newColors[] = new int[newLength];
            System.arraycopy(oldColors, 0, targetColors, 0, firstFreeSlot);
            System.arraycopy(newColors, 0, newColors, 0, firstFreeSlot);
            oldColors = targetColors;
            newColors = newColors;
        }

        oldColors[firstFreeSlot] = targetColor;
        newColors[firstFreeSlot] = newColor;
    }

    public double[] colorChange = null;
    public boolean rs3 = false;
    public boolean osrs = false;
    public int slot = -1;
    public boolean switchHands = false;
    public static ArrayList<Integer> switchHandsModels = new ArrayList<>();
    public static ArrayList<Integer> rs3GloveModels = new ArrayList<>();
    public static ArrayList<Integer> rs3BodyModels = new ArrayList<>();
    public static ArrayList<Integer> rs3HeadModels = new ArrayList<>();
    public static ArrayList<Integer> rs3LegModels = new ArrayList<>();

}