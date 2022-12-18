package org.necrotic.client.cache.definition;


public class ItemDef3 {
    private ItemDef3() {
    }

    public byte[] customSpriteLocation;

    public static ItemDefinition newIDS1(ItemDefinition itemDef, int id) {


        ItemDefinition itemdefedit;
        ItemDefinition itemdefyogipic;
        ItemDefinition itemDef21;
        ItemDefinition def;
        switch (id) {

            case 23100:
                itemDef.copyItem(6585);
                itemDef.name = "Halloween basket";
                itemDef.modelID = 130567;
                itemDef.maleEquip1 = 130568;
                itemDef.femaleEquip1 = 130568;
                itemDef.maleWieldX = 10;
                itemDef.maleWieldY = 10;
                break;
            case 23101:
                itemDef.copyItem(6585);
                itemDef.name = "Halloween gloves";
                itemDef.modelID = 130564;
                itemDef.maleEquip1 = 130565;
                itemDef.femaleEquip1 = 130565;
                itemDef.maleWieldY = 3;
                break;

            case 23102:
                itemDef.copyItem(5074);
                itemDef.name = "Spooky Bat Pet";
                itemDef.modelID = 130569;
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;


                itemDef.modelZoom = 1636;
                itemDef.rotationX = 2000;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 2;
                break;
            case 23103:
                itemDef.copyItem(5074);
                itemDef.name = "Spooky Kraken Pet";
                itemDef.modelID = 130562;
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                break;
            case 23105:
                itemDef.copyItem(1959);
                itemDef.name = "Spooky Kraken Token";
                break;
            case 23104:
                itemDef.copyItem(23105);
                itemDef.note = 7478;
                itemDef.noteTemplate = 23105;
                itemDef.name = "@or2@Spooky Kraken Token";
                itemDef.modelID = ItemDefinition.get(7478).modelID;
                itemDef.actions = new String[]{"Claim", null, null, null, null};
                break;
            case 23106:
                itemDef.copyItem(11790);
                itemDef.name = "Reaper hood upgrade";
                break;
            case 23107:
                itemDef.copyItem(23106);
                itemDef.note = 23108;
                itemDef.noteTemplate = 23106;
                itemDef.name = "Reaper hood upgrade";
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 23108:
                itemDef.copyItem(19864);
                itemDef.rdc2 = 4566;
                itemDef.modelZoom = 530;
                break;

            case 23109:
                itemDef.copyItem(7120);
                itemDef.name = "Halloween box";
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{56};
                break;


            case 23110:
                itemDef.copyItem(3687);
                itemDef.name = "Gold Season Pass";
                itemDef.rdc2 = 2352;
                itemDef.stackable = false;
                itemDef.actions = new String[]{"Claim", null, null, null, "Destroy"};
                break;

            case 23211:
                itemDef.copyItem(4278);
                itemDef.name = "Instance Token (u)";
                itemDef.rdc2 = 2352;
                itemDef.stackIDs = new int[]{23212, 23213};
                break;
            case 23212:
                itemDef.copyItem(4279);
                itemDef.name = "Instance Token (u)";
                itemDef.rdc2 = 2352;
                break;
            case 23213:
                itemDef.copyItem(4281);
                itemDef.name = "Instance Token (u)";
                itemDef.rdc2 = 2352;
                break;
            case 23214:
                itemDef.copyItem(11137);
                itemDef.name = "Invention lamp";
                itemDef.rdc2 = 1234;
                break;

            case 23215:
                itemDef.copyItem(12852);
                itemDef.name = "<col=2986cc>Global Boss Token</col>";
                itemDef.rdc2 = 91456;
                break;

            case 23210:
                itemDef.copyItem(15004);
                itemDef.name = "<col=2986cc>Onyx Casket</col>";
                itemDef.rdc2 = 12000;
                break;

            case 23086:
                itemDef.copyItem(1);
                itemDef.name = "Halls of Ammo";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100202;
                itemDef.rotationX = 0;
                itemDef.rotationY = 520;
                itemDef.modelZoom = 800;
                break;

            case 23408:
                itemDef.copyItem(5074);
                itemDef.name = "Fenrir Pet";
                itemDef.modelID = 100196;
                itemDef.modelZoom = 2700;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23409:
                itemDef.copyItem(5074);
                itemDef.name = "Green Fenrir Pet";
                itemDef.modelID = 100197;
                itemDef.modelZoom = 2700;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23410:
                itemDef.copyItem(5074);
                itemDef.name = "Red Fenrir Pet";
                itemDef.modelID = 100198;
                itemDef.modelZoom = 2700;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23411:
                itemDef.copyItem(5074);
                itemDef.name = "Blood Odin Pet";
                itemDef.modelID = 100199;
                itemDef.modelZoom = 3500;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.colorChange = new double[]{0.9, 0.3, 0.3};
                break;
            case 23412:
                itemDef.copyItem(5074);
                itemDef.name = "Heimdall Pet";
                itemDef.modelID = 100200;
                itemDef.modelZoom = 3500;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23413:
                itemDef.copyItem(5074);
                itemDef.name = "Demon Pet";
                itemDef.modelID = 100076;
                itemDef.modelZoom = 5000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23414:
                itemDef.copyItem(5074);
                itemDef.name = "Kil'jaeden Pet";
                itemDef.modelID = 100077;
                itemDef.modelZoom = 5500;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23415:
                itemDef.copyItem(5074);
                itemDef.name = "Skreeg Pet";
                itemDef.modelID = 100078;
                itemDef.modelZoom = 5000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23416:
                itemDef.copyItem(5074);
                itemDef.name = "Orix Pet";
                itemDef.modelID = 100079;
                itemDef.modelZoom = 5000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23417:
                itemDef.copyItem(5074);
                itemDef.name = "Crystal orc Pet";
                itemDef.modelID = 100080;
                itemDef.modelZoom = 5500;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23220:
                itemDef.copyItem(17011);
                itemDef.name = "Blood Sanguinesti staff";
                itemDef.rdc2 = 800;
                break;
            case 23221:
                itemDef.copyItem(12535);
                itemDef.name = "Blood Vitur Scythe";
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{52};
                break;
            case 23222:
                itemDef.copyItem(5012);
                itemDef.name = "Blood Twisted bow";
                itemDef.rdc3 = 750;
                break;
            case 23223:
                itemDef.copyItem(12159);
                itemDef.name = "<col=9f0808>Blood Attachment</col>";
                itemDef.actions = new String[]{null, null, "Disassemble", null, "Destroy"};
                itemDef.colorChange = new double[]{1.1, 0.2, 0.2};
                itemDef.stackable = false;
                break;
            case 23224:
                itemDef.copyItem(4082);
                itemDef.name = "<col=d5d5d5>Light shard</col>";
                itemDef.rdc = 127;
                itemDef.modelOffsetY = -2;
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                
                break;
            case 23225:
                itemDef.copyItem(12159);
                itemDef.name = "Celestial Attachment";
                itemDef.actions = new String[]{null, null, "Disassemble", null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{0, 15556, 16685, 16677, 16565, 16573};
                itemDef.newColors = new int[]{36700, 45759, 45759, 45759, 46003, 35666};
                break;

            case 10723:
                itemDef.name = "Halloween mask";
                break;
            case 19929:
                itemDef.name = "Halloween body";
                break;
            case 19928:
                itemDef.name = "Halloween legs";
                break;
            case 11789:
                itemDef.name = "Reaper Hood";
                break;
            case 11790:
                itemDef.name = "Reaper Hood (u)";
                break;

            case 23200:
                itemDef.copyItem(3578);
                itemDef.name = "Special Goodiebag";
                itemDef.rdc2 = 12456;
                break;
            case 23201:
                itemDef.copyItem(3578);
                itemDef.name = "Mega Goodiebag";
                itemDef.rdc2 = 5672;
                break;
            case 23202:
                itemDef.copyItem(3578);
                itemDef.name = "Grand Goodiebag";
                itemDef.rdc2 = 8255;
                break;
            case 23204:
                itemDef.copyItem(3578);
                itemDef.name = "Owner Jewelry Goodiebag";
                itemDef.modelID = 62005;
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                break;
            case 23205:
                itemDef.copyItem(3578);
                itemDef.name = "Elite Goodiebag";
                itemDef.colorChange = new double[]{0.3, 0.3, 2};
                break;


            case 23112:
                itemDef.copyItem(23113);
                itemDef.name = "Treasure Hunter";
                itemDef.modelID = 100236;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelZoom = 1200;
                break;

            case 23113:
                itemDef.copyItem(1);
                itemDef.name = "Treasure Key 1";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100231;
                itemDef.rotationX = 0;
                itemDef.rotationY = 520;
                itemDef.modelZoom = 800;
                break;
            case 23114:
                itemDef.copyItem(23113);
                itemDef.name = "Treasure Key 2";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100232;
                break;
            case 23115:
                itemDef.copyItem(23113);
                itemDef.name = "Treasure Key 3";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100233;
                itemDef.rotationX = 0;
                itemDef.rotationY = 520;
                itemDef.modelZoom = 800;
                break;
            case 23116:
                itemDef.copyItem(23113);
                itemDef.name = "Treasure Key 4";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100234;
                itemDef.rotationX = 0;
                itemDef.rotationY = 520;
                itemDef.modelZoom = 800;
                break;
            case 23117:
                itemDef.copyItem(23113);
                itemDef.name = "Master Treasure Key";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100235;
                itemDef.modelZoom = 400;
                break;


            case 23044:
                itemDef.copyItem(1);
                itemDef.name = "Aura (T1)";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.modelID = 100123;
                itemDef.rotationX = 0;
                itemDef.modelZoom = 2000;
                break;
            case 23045:
                itemDef.copyItem(23044);
                itemDef.name = "Aura (T2)";
                itemDef.modelID = 100124;
                break;
            case 23046:
                itemDef.copyItem(23044);
                itemDef.name = "Aura (T3)";
                itemDef.modelID = 100125;
                break;
            case 23047:
                itemDef.copyItem(23044);
                itemDef.name = "Aura (T4)";
                itemDef.modelID = 100126;
                break;
            case 23048:
                itemDef.copyItem(23044);
                itemDef.name = "Aura (T5)";
                itemDef.modelID = 100127;
                break;
            case 23049:
                itemDef.copyItem(23044);
                itemDef.name = "Aura (T6)";
                itemDef.modelID = 100128;
                break;


            case 23161:
                itemDef.copyItem(5074);
                itemDef.name = "Wicked Demon Pet";
                itemDef.modelID = 100237;
                itemDef.modelZoom = 5500;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23162:
                itemDef.copyItem(5074);
                itemDef.name = "Evil Dragon Pet";
                itemDef.modelID = 100238;
                itemDef.modelZoom = 4200;
                itemDef.rotationY = 520;
                itemDef.rotationX = 250;
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23163:
                itemDef.copyItem(5074);
                itemDef.name = "Obsidian Golem Pet";
                itemDef.modelID = 100239;
                itemDef.modelZoom = 5000;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23164:
                itemDef.copyItem(5074);
                itemDef.name = "Red Rammus Pet";
                itemDef.modelID = 100240;
                itemDef.modelZoom = 4500;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                break;


            case 23118:
                itemDef.copyItem(2436);
                itemDef.name = "Infinite healing potion (T1)";
                itemDef.modelID = 100250;
                itemDef.modelZoom = 1000;
                break;
            case 23119:
                itemDef.copyItem(23118);
                itemDef.name = "Infinite healing potion (T2)";
                itemDef.modelID = 100251;
                break;
            case 23120:
                itemDef.copyItem(23118);
                itemDef.name = "Infinite healing potion (T3)";
                itemDef.modelID = 100252;
                break;
            case 23121:
                itemDef.copyItem(23118);
                itemDef.name = "Infinite prayer potion (T1)";
                itemDef.modelID = 100253;
                break;
            case 23122:
                itemDef.copyItem(23118);
                itemDef.name = "Infinite prayer potion (T2)";
                itemDef.modelID = 100254;
                break;
            case 23123:
                itemDef.copyItem(23118);
                itemDef.name = "Infinite prayer potion (T3)";
                itemDef.modelID = 100255;
                break;
            case 23124:
                itemDef.copyItem(23118);
                itemDef.name = "Infinite overload potion (T1)";
                itemDef.modelID = 100256;
                break;
            case 23125:
                itemDef.copyItem(23118);
                itemDef.name = "Infinite overload potion (T2)";
                itemDef.modelID = 100257;
                break;
            case 23126:
                itemDef.copyItem(23118);
                itemDef.name = "Infinite overload potion (T3)";
                itemDef.modelID = 100258;
                break;

            //EASTER
            case 23000:
                itemDef.copyItem(6585);
                itemDef.name = "Carrot Necklace";
                itemDef.modelID = 100051;
                itemDef.maleEquip1 = 100052;
                itemDef.femaleEquip1 = 100052;
                itemDef.modelZoom = 577;
                itemDef.rotationX = 220;
                itemDef.rotationY = 381;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 11;
                break;
            case 23002:
                itemDef.copyItem(6199);
                itemDef.name = "Fortune Chest";
                itemDef.modelID = 100054;
                itemDef.modelZoom = 936;
                itemDef.rotationX = 110;
                itemDef.rotationY = 127;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -6;
                break;
            case 23003:
                itemDef.copyItem(12855);
                itemDef.name = "Easter Tickets";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100056;
                itemDef.modelZoom = 1874;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23004:
                itemDef.copyItem(12855);
                itemDef.name = "Orange Easter Egg";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100057;
                itemDef.modelZoom = 535;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23005:
                itemDef.copyItem(23004);
                itemDef.name = "Cyan Easter Egg";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100058;
                break;
            case 23006:
                itemDef.copyItem(23004);
                itemDef.name = "Purple Easter Egg";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100059;
                break;
            case 23007:
                itemDef.copyItem(23004);
                itemDef.name = "Red Easter Egg";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100060;
                break;

            case 23008:
                itemDef.copyItem(12855);
                itemDef.name = "White Easter Dye";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100061;
                itemDef.modelZoom = 769;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                break;
            case 23009:
                itemDef.copyItem(23008);
                itemDef.name = "Green Easter Dye";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100062;
                itemDef.actions[2] = "Disassemble";
                break;
            case 23010:
                itemDef.copyItem(23008);
                itemDef.name = "Gold Easter Dye";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100063;
                itemDef.actions[2] = "Disassemble";
                break;
            case 23011:
                itemDef.copyItem(23008);
                itemDef.name = "Blue Easter Dye";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100064;
                itemDef.actions[2] = "Disassemble";
                break;
            case 23012:
                itemDef.copyItem(23008);
                itemDef.name = "Red Easter Dye";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 100065;
                itemDef.actions[2] = "Disassemble";
                break;

            case 23013:
                itemDef.copyItem(1053);
                itemDef.name = "Easter Bunny Mask";
                itemDef.modelID = 100066;
                itemDef.maleEquip1 = 100067;
                itemDef.femaleEquip1 = 100067;
                itemDef.modelZoom = 1505;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.actions[2] = "Disassemble";
                break;
            case 23014:
                itemDef.copyItem(23013);
                itemDef.name = "Green Easter Bunny Mask";
                itemDef.modelID = 100068;
                itemDef.maleEquip1 = 100069;
                itemDef.femaleEquip1 = 100069;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 23015:
                itemDef.copyItem(23014);
                itemDef.name = "Golden Easter Bunny Mask";
                itemDef.modelID = 100070;
                itemDef.maleEquip1 = 100071;
                itemDef.femaleEquip1 = 100071;
                break;
            case 23016:
                itemDef.copyItem(23014);
                itemDef.name = "Blue Easter Bunny Mask";
                itemDef.modelID = 100072;
                itemDef.maleEquip1 = 100073;
                itemDef.femaleEquip1 = 100073;
                break;
            case 23017:
                itemDef.copyItem(23014);
                itemDef.name = "Red Easter Bunny Mask";
                itemDef.modelID = 100074;
                itemDef.maleEquip1 = 100075;
                itemDef.femaleEquip1 = 100075;
                break;

            case 23018:
                itemDef.copyItem(5074);
                itemDef.name = "Armoured Bunny Pet";
                itemDef.modelID = 100050;
                itemDef.modelZoom = 4000;
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.modelZoom = 1781;
                itemDef.rotationX = 60;
                itemDef.rotationY = 172;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -22;
                break;


            case 23020:
                itemDef.copyItem(19670);
                itemDef.name = "Vote Scroll";
                itemDef.rdc2 = 15345;
                break;


            case 19336:
            case 19337:
            case 19338:
            case 19339:
            case 19340:
                itemDef.rdc2 = 814488;
                break;
            case 7394:
            case 10689:
                //case 2597:
                itemDef.rdc2 = 2299;
                break;
            case 21372:
                itemDef.rdc2 = 24929;
                break;
            case 21373:
                itemDef.rdc2 = 10843;
                break;
            case 21374:
                itemDef.rdc2 = 88753;
                break;
            case 20555://vitur
            case 13887:
            case 13905:
            case 13867:
            case 15877:
            case 15922:
            case 15933:
            case 16021:
            case 8816:
            case 8817:
            case 8818:
            case 18356:
            case 12931:
            case 15511:
            case 1149:
            case 1249:
            case 3204:
            case 1305:
            case 1215:
            case 1377:
            case 1434:
            case 7158:
                
                break;
            case 17043:
                itemDef.name = "Leafy coif";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 18332:
                itemDef.name = "Leafy Longbow";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 13591:
                itemDef.name = "@yel@Raids [1] Key";
                itemDef.stackable = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 3578:
                itemDef.name = "<col=FF0000><shad=0>Owner Cape Goodiebag";
                break;
            case 17175:
                itemDef.name = "Leafy body";


                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 17321:
                itemDef.name = "Leafy chaps";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 10696:
                itemDef.name = "Leafy boots";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 15026:
                itemDef.name = "Leafy gloves";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 14733:
                itemDef.name = "Sorcery hat";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 14732:
                itemDef.name = "Sorcery robe top";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 14734:
                itemDef.name = "Sorcery robe bottom";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 14377:
                itemDef.name = "Sorcery Staff";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 10865:
                itemDef.name = "Sorcery boots";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 12864:
                itemDef.name = "Sorcery gloves";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                break;
            case 4367:
                itemDef.name = "Art's cape";
                //	itemDef.modelID = 65448;
                //	itemDef.maleWearId = 65449;
                //	itemDef.femaleWearId = 65449;
                itemDef.modelID = 65333;
                itemDef.maleEquip1 = 65334;
                itemDef.femaleEquip1 = 65334;
                itemDef.oldColors = new int[]{59};
                itemDef.newColors = new int[]{66};
                itemDef.rdc2 = 762432;
                itemDef.modelZoom = 1385;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                
                break;
            case 7996:
                itemDef.name = "robin's cape";
                //	itemDef.modelID = 65448;
                //	itemDef.maleWearId = 65449;
                //	itemDef.femaleWearId = 65449;
                itemDef.modelID = 64365;
                itemDef.maleEquip1 = 64366;
                itemDef.femaleEquip1 = 64366;
                itemdefedit = ItemDefinition.get(2414);
                //	itemDef.modelID = 65333;
                //itemDef.maleWearId = 65334;
                //itemDef.femaleWearId = 65334;
                //itemDef.actions = itemdefedit.actions;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};

                break;
            case 10946:
                itemDef.name = "$1 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                itemDef.actions[3] = "Exchange-All";
                itemdefedit = ItemDefinition.get(761);
                itemDef.modelID = itemdefedit.modelID;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.itemdefedit = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelZoom = 1300;
                break;
            case 7997:
                itemDef.name = "Exciter Cape";
                //	itemDef.modelID = 65448;
                //	itemDef.maleWearId = 65449;
                //	itemDef.femaleWearId = 65449;
                itemDef.modelID = 64367;
                itemDef.maleEquip1 = 64368;
                itemDef.femaleEquip1 = 64368;
                itemdefedit = ItemDefinition.get(2414);
                //	itemDef.modelID = 65333;
                //itemDef.maleWearId = 65334;
                //itemDef.femaleWearId = 65334;
                //itemDef.actions = itemdefedit.actions;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{95};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};

                break;
            case 7995:
                itemDef.name = "Owner cape";
                itemDef.modelID = 65448;
                itemDef.maleEquip1 = 65449;
                itemDef.femaleEquip1 = 65449;
                itemdefedit = ItemDefinition.get(2414);
                itemDef.actions = itemdefedit.actions;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.oldColors = new int[]{57};
                itemDef.newColors = new int[]{52};
                break;
            case 11000:
                itemDef.name = "@red@Owner Attachment";
                itemDef.modelID = 65448;
                itemDef.maleEquip1 = 65449;
                itemDef.femaleEquip1 = 65449;
                itemdefedit = ItemDefinition.get(2414);
                itemDef.actions = itemdefedit.actions;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                break;
            case 5594:
                itemDef.name = "Katherine's cape";
                itemDef.modelID = 64315;

                itemdefedit = ItemDefinition.get(6570);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 65447;
                itemDef.maleEquip1 = 65446;
                itemDef.femaleEquip1 = 65446;
                itemDef.actions = itemdefedit.actions;
                itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[2]; // same here
                itemDef.oldColors[0] = 54; // the texture that it currently has
                itemDef.newColors[0] = 88;
                itemDef.oldColors[1] = 65; // the texture that it currently has
                itemDef.newColors[1] = 88; // the new texture u want it to have
                itemDef.stackable = false;
                itemDef.value = 1;
                break;
            case 15219:
                itemDef.name = "Del's cape";
                itemDef.modelID = 65333;
                itemDef.maleEquip1 = 65334;
                itemDef.femaleEquip1 = 65334;
                itemDef.oldColors = new int[]{57};
                itemDef.newColors = new int[]{78};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 36232;
                itemDef.modelZoom = 1385;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                break;
            case 20591:
                itemDef.name = "@gre@Rage Cape";
                itemDef.modelID = 65333;
                itemDef.maleEquip1 = 65334;
                itemDef.femaleEquip1 = 65334;
                itemdefedit = ItemDefinition.get(4367);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //	itemDef.actions = itemdefedit.actions;
                itemDef.rdc2 = 29295;
                itemDef.oldColors = new int[]{57};
                itemDef.newColors = new int[]{71};
                itemDef.modelZoom = 1385;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                break;
            case 11320:
                itemDef.copyItem(4708);
                itemDef.name = "<col=eed81d><shad=9283>Dragon Rider helm<shad-1>";
                itemDef.modelID = 100338;
                itemDef.maleEquip1 = 100339;
                itemDef.femaleEquip1 = 100339;
                itemDef.modelZoom = 900;
                
                break;
            case 11321:
                itemDef.copyItem(4712);
                itemDef.name = "<col=eed81d><shad=9283>Dragon Rider platebody<shad-1>";
                itemDef.modelID = 100346;
                itemDef.maleEquip1 = 100347;
                itemDef.femaleEquip1 = 100347;
                itemDef.modelZoom = 1500;
                itemDef.stackable = false;
                
                break;
            case 11322:
                itemDef.copyItem(4714);
                itemDef.name = "<col=eed81d><shad=9283>Dragon Rider legs<shad-1>";
                itemDef.modelID = 100348;
                itemDef.maleEquip1 = 100349;
                itemDef.femaleEquip1 = 100349;
                itemDef.modelZoom = 1800;
                
                break;
            case 18819:
                itemDef.name = "<shad=1><col=FF0000>Infinity Slayer Ring";
                itemdefedit = ItemDefinition.get(13281);
                itemDef.actions = itemdefedit.actions;
                break;
            case 11421:
                itemDef.copyItem(4708);
                itemDef.name = "<col=57d0fd><shad=9283>Purifier hood<shad-1>";
                itemDef.modelID = 100327;
                itemDef.maleEquip1 = 100328;
                itemDef.femaleEquip1 = 100328;
                itemDef.modelZoom = 900;
                
                break;
            case 11422:
                itemDef.copyItem(4712);
                itemDef.name = "<col=57d0fd><shad=9283>Purifier robetop<shad-1>";
                itemDef.modelID = 100335;
                itemDef.maleEquip1 = 100336;
                itemDef.femaleEquip1 = 100336;
                itemDef.modelZoom = 1500;
                
                break;
            case 11423:
                itemDef.copyItem(4714);
                itemDef.name = "<col=57d0fd><shad=9283>Purifier robebottom<shad-1>";
                itemDef.modelID = 100331;
                itemDef.maleEquip1 = 100332;
                itemDef.femaleEquip1 = 100332;
                itemDef.modelZoom = 1800;
                
                break;
            case 11340:
                itemDef.copyItem(4708);
                itemDef.name = "<col=fb9ae5><shad=9283>Judicator helm<shad-1>";
                itemDef.modelID = 100359;
                itemDef.maleEquip1 = 100360;
                itemDef.femaleEquip1 = 100360;
                itemDef.modelZoom = 900;
                
                break;
            case 22121:
                itemDef.name = "@yel@Golden Scratch Card";
                itemdefedit = ItemDefinition.get(455);
                itemDef.actions = new String[]{"Scratch", null, null, null, "Destroy"};
                itemDef.modelID = 100053;
                itemDef.modelZoom = 1171;
                itemDef.rotationX = 322;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -3;
                break;
            case 11341:
                itemDef.copyItem(4712);
                itemDef.name = "<col=fb9ae5><shad=9283>Judicator body<shad-1>";
                itemDef.modelID = 100363;
                itemDef.maleEquip1 = 100364;
                itemDef.femaleEquip1 = 100364;
                itemDef.modelZoom = 1500;
                
                break;
            case 11342:
                itemDef.copyItem(4714);
                itemDef.name = "<col=fb9ae5><shad=9283>Judicator chaps<shad-1>";
                itemDef.modelID = 100361;
                itemDef.maleEquip1 = 100362;
                itemDef.femaleEquip1 = 100362;
                itemDef.modelZoom = 1800;
                
                break;

            case 8087:
                itemDef.copyItem(4710);
                itemDef.name = "<col=eed81d><shad=9283>Dragon Rider lance<shad-1>";
                itemDef.modelID = 100342;
                itemDef.maleEquip1 = 100343;
                itemDef.femaleEquip1 = 100343;
                itemDef.modelZoom = 2300;
                itemDef.rotationX = 1100;
                itemDef.rotationY = 450;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                
                break;

            case 8088:
                itemDef.copyItem(4710);
                itemDef.name = "<col=fb9ae5><shad=9283>Judicator crossbow<shad-1>";
                itemDef.modelID = 100355;
                itemDef.maleEquip1 = 100356;
                itemDef.femaleEquip1 = 100356;
                itemDef.modelZoom = 1400;
                itemDef.rotationX = 1100;
                itemDef.rotationY = 450;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                
                break;
            case 8001:
                itemDef.name = "Bryce's crossbow";
                itemDef.modelID = 64495;
                itemDef.maleEquip1 = 64496;
                itemDef.femaleEquip1 = 64496;
                itemdefedit = ItemDefinition.get(9185);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1700;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                itemDef.maleModelScale = 2;
                itemDef.maleWieldX = -18;
                itemDef.maleWieldY = -32;
                itemDef.maleWieldZ = -5;
                itemDef.oldColors = new int[]{38, 40665, 40549};
                itemDef.newColors = new int[]{5, 946, 946};
                break;
            case 8089:
                itemDef.copyItem(4710);
                itemDef.name = "<col=57d0fd><shad=9283>Purifier staff<shad-1>";
                itemDef.modelID = 100333;
                itemDef.maleEquip1 = 100334;
                itemDef.femaleEquip1 = 100334;
                itemDef.modelZoom = 2300;
                itemDef.rotationX = 1100;
                itemDef.rotationY = 450;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                
                break;
            case 19888:
                itemDef.name = "Collector necklace @cya@II";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                

                break;
            case 18823:

                itemDef.name = "Collector ring @cya@II";
                
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 18888:

                itemdefedit = ItemDefinition.get(19888);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.name = "Collector necklace @mag@III";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 18818:
                itemdefedit = ItemDefinition.get(18823);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.name = "Collector ring @mag@III";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 21816:
                itemDef.name = "Warrior ticket";
                //itemDef.rdc2 = 45367587;
                break;

            case 21815:
                itemDef.name = "Archer ticket";
                itemDef.rdc2 = 45367587;
                break;
            case 21814:
                itemDef.name = "Wizard ticket";
                itemDef.rdc2 = 35734;
                break;
            case 14910:
                itemDef.name = "Demonic Torva full helm";

                itemdefedit = ItemDefinition.get(14008);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14911:
                itemDef.name = "Demonic Torva platebody";

                itemdefedit = ItemDefinition.get(14009);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14912:
                itemDef.name = "Demonic Torva platelegs";

                itemdefedit = ItemDefinition.get(14010);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14913:
                itemDef.name = "Demonic gloves";

                itemdefedit = ItemDefinition.get(5556);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;

            case 14914:
                itemDef.name = "Demonic boots";

                itemdefedit = ItemDefinition.get(20119);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14915:
                itemDef.name = "Demonic Sword";

                itemdefedit = ItemDefinition.get(20536);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                
                break;
            case 14916:
                itemDef.name = "Nature pernix cowl";

                itemdefedit = ItemDefinition.get(14011);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14917:
                itemDef.name = "Nature pernix body";

                itemdefedit = ItemDefinition.get(14012);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14918:
                itemDef.name = "Nature pernix chaps";

                itemdefedit = ItemDefinition.get(14013);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14919:
                itemDef.name = "Nature Bow";

                itemdefedit = ItemDefinition.get(14684);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                
                break;
            case 14920:
                itemDef.name = "Nature cape";

                itemdefedit = ItemDefinition.get(14022);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 6833:
                itemDef.name = "Goodiebag Box";
                itemDef.rdc2 = 12512;
                itemDef.actions = new String[]{"Open", null, null, null, "Destroy"};

                break;
            case 14921:
                itemDef.name = "Icey virtus mask";

                itemdefedit = ItemDefinition.get(14014);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14922:
                itemDef.name = "Icey virtus robe top";

                itemdefedit = ItemDefinition.get(14015);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14923:
                itemDef.name = "Icey virtus legs";

                itemdefedit = ItemDefinition.get(14016);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 14924:
                itemDef.name = "Icey staff of festive";

                itemdefedit = ItemDefinition.get(18667);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;
            case 14925:
                itemDef.name = "Icey shield";

                itemdefedit = ItemDefinition.get(14577);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = itemdefedit.actions;
                itemDef.stackable = false;
                //	itemDef.rdc2 = 19518;
                break;
            case 15115:
                itemDef.name = "<shad=1><col=00e673>Extreme helm";
                itemDef.modelID = 65192;
                itemDef.maleEquip1 = 65193;
                itemDef.femaleEquip1 = 65193;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 88205;
                break;
            case 15116:
                itemDef.name = "<shad=1><col=00e673>Extreme body";
                itemDef.modelID = 65194;
                itemDef.maleEquip1 = 65195;
                itemDef.femaleEquip1 = 65195;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 88205;
                break;
            case 15117:
                itemDef.name = "<shad=1><col=00e673>Extreme legs";
                itemDef.modelID = 65196;
                itemDef.maleEquip1 = 65197;
                itemDef.femaleEquip1 = 65197;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 88205;
                break;
            case 15118:
                itemDef.name = "<shad=1><col=00e673>Extreme gloves";
                itemDef.modelID = 65198;
                itemDef.maleEquip1 = 65199;
                itemDef.femaleEquip1 = 65199;
                itemdefedit = ItemDefinition.get(7462);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 88205;
                break;
            case 15119:
                itemDef.name = "<shad=1><col=00e673>Extreme boots";
                itemDef.modelID = 65200;
                itemDef.maleEquip1 = 65200;
                itemDef.femaleEquip1 = 65200;
                itemdefedit = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 88205;
                break;
            case 15121:
                itemDef.name = "Extreme spear";
                itemDef.modelID = 65201;
                itemDef.maleEquip1 = 65202;
                itemDef.femaleEquip1 = 65202;
                itemdefedit = ItemDefinition.get(1237);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 88205;
                break;
            case 19331:
                itemDef.name = "Frozen staff";
                itemdefedit = ItemDefinition.get(15486);
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;
                
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 7792950;
                break;
            case 15448:
                itemDef.name = "Groudon-flame power";
                
                break;
            case 15449:
                itemDef.name = "Extreme Aura";
                break;
            case 15450:
                itemDef.name = "Custom Extreme Aura";
                break;
            case 3107:
                itemDef.name = "Groudon-flame boots";
                
                break;
            case 13640:
                itemDef.name = "Groudon-flame staff";
                
                break;
            case 13964:
                itemDef.name = "Groudon-flame shield";
                
                break;
            //case 13964:itemDef.name = "Groudon-flame shield";break;
            case 11317:
                ItemDefinition votingpoting = ItemDefinition.get(12479);
                itemDef.modelID = 64304;
                itemDef.modelOffsetX = votingpoting.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = votingpoting.modelOffsetY;
                itemDef.modelZoom = 2000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Bumble bee pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                itemDef.rdc2 = 664466;
                break;
            case 5074:
                ItemDefinition jokerpet = ItemDefinition.get(12479);
                itemDef.modelID = 64508;
                itemDef.modelOffsetX = jokerpet.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = jokerpet.modelOffsetY;
                itemDef.modelZoom = 2000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Joker pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                //	itemDef.rdc2 = 664466;
                break;
            case 21934:
                itemDef.name = "Groudon-flame helm";
                itemDef.modelID = 65112;
                itemDef.maleEquip1 = 65113;
                itemDef.femaleEquip1 = 65113;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 40; // the new texture u want it to have
                itemDef.stackable = false;
                
                // itemDef.setTexture(matid, textureid);
                // itemDef.applyTexturing(model, id);
                // itemDef.rdc2 = 8822;
                break;
            case 19918:
                itemDef.name = "Groudon-flame body";
                itemDef.modelID = 65114;
                itemDef.maleEquip1 = 65115;
                itemDef.femaleEquip1 = 65115;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 40; // the new texture u want it to have
                // itemDef.rdc2 = 8822;
                
                break;
            case 19913:
                itemDef.name = "Groudon-flame legs";
                itemDef.modelID = 65116;
                itemDef.maleEquip1 = 65117;
                itemDef.femaleEquip1 = 65117;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 40; // the new texture u want it to have
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                
                break;
            case 20060:
                itemDef.name = "Lytsu void helm";

                itemDef.modelID = 65385;
                itemDef.maleEquip1 = 65386;
                itemDef.femaleEquip1 = 65386;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{88};
                break;
            case 20062:
                itemDef.name = "Lytsu void body";
                itemDef.modelID = 65387;
                itemDef.maleEquip1 = 65388;
                itemDef.femaleEquip1 = 65388;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{88};

                break;
            case 20063:
                itemDef.name = "Lytsu Void legs";

                itemDef.modelID = 65389;
                itemDef.maleEquip1 = 65390;
                itemDef.femaleEquip1 = 65390;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{88};

                break;
            case 20073:
                itemDef.name = "Lytsu void boots";
                itemdefedit = ItemDefinition.get(7114);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64177;
                itemDef.maleEquip1 = 64178;
                itemDef.femaleEquip1 = 64178;
                itemDef.actions = itemdefedit.actions;
                //	itemDef.rdc2 = 47333;
                break;

            case 19800:
                itemDef.name = "Lytsu void Teddybear";
                itemDef.modelID = 64034;
                itemDef.maleEquip1 = 64035;
                itemDef.femaleEquip1 = 64035;
                itemdefedit = ItemDefinition.get(8848);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;

                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19802:
                itemDef.name = "Lytsu void gloves";
                itemdefedit = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1100;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64175;
                itemDef.maleEquip1 = 64176;
                itemDef.femaleEquip1 = 64176;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;

                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 14050:
                itemDef.name = "Cerulean helm";

                itemDef.modelID = 65385;
                itemDef.maleEquip1 = 65386;
                itemDef.femaleEquip1 = 65386;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{91};
                break;
            case 14051:
                itemDef.name = "Cerulean body";
                itemDef.modelID = 65387;
                itemDef.maleEquip1 = 65388;
                itemDef.femaleEquip1 = 65388;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{91};

                break;
            case 14052:
                itemDef.name = "Cerulean legs";

                itemDef.modelID = 65389;
                itemDef.maleEquip1 = 65390;
                itemDef.femaleEquip1 = 65390;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{91};

                break;
            case 14053:
                itemDef.copyItem(18881);
                itemDef.name = "Cerulean boots";
                itemDef.newColors = new int[1];
                itemDef.oldColors = new int[1];
                itemDef.oldColors[0] = 60;
                itemDef.newColors[0] = 91;
                break;
            case 14054:
                itemDef.name = "Alex void Shield";
                itemdefedit = ItemDefinition.get(12930);
                itemDef.modelID = itemdefedit.modelID;
                itemDef.maleEquip1 = itemdefedit.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit.femaleEquip1;

                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1600;
                itemDef.rotationY = itemdefedit.rotationY;

                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 14055:
                itemDef.name = "Cerulean gloves";
                itemdefedit = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1100;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64175;
                itemDef.maleEquip1 = 64176;
                itemDef.femaleEquip1 = 64176;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;

                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 14056:
                itemDef.name = "Alex Lunite bow";
                itemDef.modelID = 64358;
                itemDef.maleEquip1 = 64359;
                itemDef.femaleEquip1 = 64359;
                itemdefedit = ItemDefinition.get(9185);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1700;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};

                break;
            case 11181:
                itemDef.name = "X File gloves";
                itemdefedit = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1200;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64277;
                itemDef.maleEquip1 = 64278;
                itemDef.femaleEquip1 = 64278;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                //	itemDef.rdc2 = 8423432;
                break;
            case 11182:
                itemDef.name = "X File boots";
                itemdefedit = ItemDefinition.get(7114);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64279;
                itemDef.maleEquip1 = 64279;
                itemDef.femaleEquip1 = 64279;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                //	itemDef.rdc2 = 8423432;
                break;


            case 11183:
                itemDef.name = "X File helm";
                itemDef.modelID = 65272;
                itemDef.maleEquip1 = 65273;
                itemDef.femaleEquip1 = 65273;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 19188731;//777245
                break;

            case 11184:
                itemDef.name = "X File body";
                itemDef.modelID = 65274;
                itemDef.maleEquip1 = 65275;
                itemDef.femaleEquip1 = 65275;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 19188731;
                break;

            case 11179:
                itemDef.name = "X File legs";
                itemDef.modelID = 65276;
                itemDef.maleEquip1 = 65277;
                itemDef.femaleEquip1 = 65277;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                // itemDef.rdc2 = 23945;
                itemDef.rdc2 = 19188731;
                break;
            case 11759:
                itemDef.name = "x file Teddybear";
                itemDef.modelID = 64034;
                itemDef.maleEquip1 = 64035;
                itemDef.femaleEquip1 = 64035;
                itemdefedit = ItemDefinition.get(8848);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;

                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 11762:
                itemDef.name = "x file cape";
                itemDef.modelID = 64315;

                itemdefedit = ItemDefinition.get(6570);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 65447;
                itemDef.maleEquip1 = 65446;
                itemDef.femaleEquip1 = 65446;
                itemDef.actions = itemdefedit.actions;
                itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[2]; // same here
                itemDef.oldColors[0] = 54; // the texture that it currently has
                itemDef.newColors[0] = 56;
                itemDef.oldColors[1] = 65; // the texture that it currently has
                itemDef.newColors[1] = 56; // the new texture u want it to have
                itemDef.stackable = false;
                itemDef.value = 1;
                break;
            case 11763:
                itemDef.name = "Freiza helm";
                itemDef.modelID = 64374;
                itemDef.maleEquip1 = 64369;
                itemDef.femaleEquip1 = 64369;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                //itemDef.rdc2 = 8423432;
                break;

            case 11764:
                itemDef.name = "Freiza body";
                itemDef.modelID = 64370;
                itemDef.maleEquip1 = 64371;
                itemDef.femaleEquip1 = 64371;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                //	itemDef.rdc2 = 8423432;
                break;

            case 11765:
                itemDef.name = "Freiza legs";
                itemDef.modelID = 64372;
                itemDef.maleEquip1 = 64373;
                itemDef.femaleEquip1 = 64373;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                // itemDef.rdc2 = 23945;
                //	itemDef.rdc2 = 8423432;
                break;
            case 11766:
                itemDef.name = "Freiza gloves";
                itemdefedit = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1200;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64277;
                itemDef.maleEquip1 = 64278;
                itemDef.femaleEquip1 = 64278;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                

                //	itemDef.rdc2 = 8423432;
                break;
            case 11767:
                itemDef.name = "Freiza boots";
                itemdefedit = ItemDefinition.get(7114);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64279;
                itemDef.maleEquip1 = 64279;
                itemDef.femaleEquip1 = 64279;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                

                //	itemDef.rdc2 = 8423432;
                break;
            case 9478:
                itemDef.name = "Super buu helm";
                itemDef.modelID = 64375;
                itemDef.maleEquip1 = 64376;
                itemDef.femaleEquip1 = 64376;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //itemDef.rdc2 = 8423432;
                

                break;

            case 9479:
                itemDef.name = "Super buu body";
                itemDef.modelID = 64377;
                itemDef.maleEquip1 = 64378;
                itemDef.femaleEquip1 = 64378;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;

            case 9480:
                itemDef.name = "Super buu legs";
                itemDef.modelID = 64379;
                itemDef.maleEquip1 = 64380;
                itemDef.femaleEquip1 = 64380;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                //	itemDef.rdc2 = 8423432;
                break;
            case 9481:
                itemDef.name = "Perfect cell helm";
                itemDef.modelID = 64386;
                itemDef.maleEquip1 = 64387;
                itemDef.femaleEquip1 = 64387;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                //itemDef.rdc2 = 8423432;
                break;

            case 9482:
                itemDef.name = "Perfect cell body";
                itemDef.modelID = 64388;
                itemDef.maleEquip1 = 64389;
                itemDef.femaleEquip1 = 64389;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                //	itemDef.rdc2 = 8423432;
                break;

            case 9483:
                itemDef.name = "Perfect cell legs";
                itemDef.modelID = 64390;
                itemDef.maleEquip1 = 64391;
                itemDef.femaleEquip1 = 64391;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                // itemDef.rdc2 = 23945;
                //	itemDef.rdc2 = 8423432;
                break;
            case 2947:

                itemDef.modelID = 64362;
                itemDef.modelOffsetX = 2;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = 80;
                itemDef.rotationY = 204;
                itemDef.rotationX = 20;
                itemDef.modelZoom = 2100;
                itemDef.name = "Frieza Pet (first form)";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 2948:

                itemDef.modelID = 64363;
                itemDef.modelOffsetX = 2;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = 80;
                itemDef.rotationY = 204;
                itemDef.rotationX = 20;
                itemDef.modelZoom = 2100;
                itemDef.name = "Frieza Pet (second form)";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 2949:

                itemDef.modelID = 64364;
                itemDef.modelOffsetX = 2;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = 80;
                itemDef.rotationY = 204;
                itemDef.rotationX = 20;
                itemDef.modelZoom = 2100;
                itemDef.name = "Frieza Pet (final form)";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 9929:
                itemDef.name = "Lytsu void Rifle";
                ItemDefinition itemDef2 = ItemDefinition.get(20538);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 65024;
                itemDef.femaleEquip1 = 65025;
                itemDef.maleEquip1 = 65025;
                itemDef.modelOffsetX = 1;
                //	itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = 30;
                itemDef.modelZoom = 2100;
                itemDef.rotationY = 200;
                itemDef.rotationX = itemDef2.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 26:
                itemDef.name = "Sassy Aura";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};

                break;
            case 18768:
                itemDef.name = "Dragonball Saga box";
                itemDef.rdc2 = 2233311;
                break;
            case 7543:
                itemDef.name = "Perfect cell Rifle";
                ItemDefinition itemdefedit1 = ItemDefinition.get(20538);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 65024;
                itemDef.femaleEquip1 = 65025;
                itemDef.maleEquip1 = 65025;
                itemDef.modelOffsetX = 1;
                //	itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = 30;
                itemDef.modelZoom = 2100;
                itemDef.rotationY = 200;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                

                break;
            case 7544:
                itemDef.name = "Perfect Cell Teddybear";
                itemDef.modelID = 64034;
                itemDef.maleEquip1 = 64035;
                itemDef.femaleEquip1 = 64035;
                itemdefedit1 = ItemDefinition.get(8848);
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit1.rotationY;

                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 8410:
                itemDef.name = "Elite Sword (AoE)";
                itemdefedit1 = ItemDefinition.get(4587);
                itemDef.modelID = 139915;
                itemDef.femaleEquip1 = 139916;
                itemDef.maleEquip1 = 139916;
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[]{59};
                itemDef.oldColors = new int[]{106};
                break;
            case 8411:
                itemDef.name = "Elite Crossbow (AoE)";
                itemdefedit1 = ItemDefinition.get(859);
                itemDef.modelID = 139917;
                itemDef.femaleEquip1 = 139918;
                itemDef.maleEquip1 = 139918;
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[]{59};
                itemDef.oldColors = new int[]{105};
                break;
            case 8412:
                itemDef.name = "Elite Staff (AoE)";
                itemdefedit1 = ItemDefinition.get(4675);
                itemDef.modelID = 139913;
                itemDef.femaleEquip1 = 139914;
                itemDef.maleEquip1 = 139914;
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[]{59};
                itemDef.oldColors = new int[]{105};
                break;
            case 3737:
                itemDef.name = "Scarlet AoE Sword";
                itemdefedit1 = ItemDefinition.get(4587);
                itemDef.modelID = 64395;
                itemDef.femaleEquip1 = 64396;
                itemDef.maleEquip1 = 64396;
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                

                break;
            case 3738:
                itemDef.name = "Herbal AoE Bow";
                itemdefedit1 = ItemDefinition.get(859);
                itemDef.modelID = 64397;
                itemDef.femaleEquip1 = 64398;
                itemDef.maleEquip1 = 64398;
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                

                //	itemDef.rdc2  = 20483;
                break;
            case 3739:
                itemDef.name = "Azure AoE Staff";
                itemdefedit1 = ItemDefinition.get(4675);
                itemDef.modelID = 64399;
                itemDef.femaleEquip1 = 64400;
                itemDef.maleEquip1 = 64400;
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                

                break;
            case 7545:
                itemdefedit1 = ItemDefinition.get(7535);
                itemDef.name = "Perfect Cell apparatus";
                itemDef.modelID = itemdefedit1.modelID;
                itemDef.maleEquip1 = itemdefedit1.maleEquip1;
                itemDef.femaleEquip1 = itemdefedit1.femaleEquip1;

                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;

                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 6545:

                itemDef.modelID = 64385;
                itemDef.modelOffsetX = 2;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = 80;
                itemDef.rotationY = 204;
                itemDef.rotationX = 20;
                itemDef.modelZoom = 2100;
                itemDef.name = "Perfect cell pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 15279:

                itemDef.modelID = 64381;
                itemDef.modelOffsetX = 2;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = 80;
                itemDef.rotationY = 204;
                itemDef.rotationX = 20;
                itemDef.modelZoom = 2100;
                itemDef.name = "Majin Buu Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 15278:

                itemDef.modelID = 64383;
                itemDef.modelOffsetX = 2;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = 80;
                itemDef.rotationY = 204;
                itemDef.rotationX = 20;
                itemDef.modelZoom = 2100;
                itemDef.name = "Super Buu Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 16249:
                itemDef.name = "Super buu Staff";
                

                break;
            case 16265:
                itemDef.name = "Super buu boots";
                

                break;
            case 15832:
                itemDef.name = "Super buu ward";
                

                break;
            case 1485:
                itemDef.name = "Landen Cape";
                ItemDefinition SoulCape = ItemDefinition.get(19709);
                itemDef.modelID = 64393;
                itemDef.femaleEquip1 = 64394;
                itemDef.maleEquip1 = 64394;
                itemDef.modelOffsetX = 20;
                itemDef.modelOffsetY = 100;
                itemDef.modelZoom = 2300;
                itemDef.rotationY = 300;
                itemDef.rotationX = 1000;
                itemDef.stackable = false;
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 96;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 1486:
                itemDef.name = "Creeper Cape";
                ItemDefinition SoulCape1 = ItemDefinition.get(19709);
                itemDef.modelID = 64393;
                itemDef.femaleEquip1 = 64394;
                itemDef.maleEquip1 = 64394;
                itemDef.modelOffsetX = 20;
                itemDef.modelOffsetY = 100;
                itemDef.modelZoom = 2300;
                itemDef.rotationY = 300;
                itemDef.rotationX = 1000;
                itemDef.stackable = false;
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 97;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                

                break;
            case 3740:
                itemDef.name = "Yogi helm";
                itemDef.modelID = 64401;
                itemDef.maleEquip1 = 64402;
                itemDef.femaleEquip1 = 64402;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 23453;
                break;
            case 3741:
                itemDef.name = "Yogi body";
                itemDef.modelID = 64403;
                itemDef.maleEquip1 = 64404;
                itemDef.femaleEquip1 = 64404;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 23453;
                break;
            case 3742:
                itemDef.name = "Yogi legs";
                itemDef.modelID = 64405;
                itemDef.maleEquip1 = 64406;
                itemDef.femaleEquip1 = 64406;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 23453;
                break;
            case 3743:
                itemDef.name = "Yogi Sword";
                itemdefedit1 = ItemDefinition.get(4587);
                itemDef.modelID = 64395;
                itemDef.femaleEquip1 = 64396;
                itemDef.maleEquip1 = 64396;
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.rdc2 = 23453;
                break;
            case 3744:
                itemDef.name = "Yogi Bow";
                itemdefedit1 = ItemDefinition.get(859);
                itemDef.modelID = 64397;
                itemDef.femaleEquip1 = 64398;
                itemDef.maleEquip1 = 64398;
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.rdc2 = 23453;
                break;
            case 3745:
                itemDef.name = "Yogi Staff";
                itemdefedit1 = ItemDefinition.get(4675);
                itemDef.modelID = 64399;
                itemDef.femaleEquip1 = 64400;
                itemDef.maleEquip1 = 64400;
                itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit1.modelOffsetY;
                itemDef.modelZoom = itemdefedit1.modelZoom;
                itemDef.rotationY = itemdefedit1.rotationY;
                itemDef.rotationX = itemdefedit1.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.rdc2 = 23453;
                break;
            case 3720:
                itemDef.name = "Scarlet helm";
                itemDef.modelID = 64407;
                itemDef.maleEquip1 = 64408;
                itemDef.femaleEquip1 = 64408;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                //itemDef.rdc =1931;
                break;
            case 3721:
                itemDef.name = "Scarlet body";
                itemDef.modelID = 64409;
                itemDef.maleEquip1 = 64410;
                itemDef.femaleEquip1 = 64410;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //itemDef.rdc2= 1931;
                

                break;
            case 3722:
                itemDef.name = "Scarlet legs";
                itemDef.modelID = 64411;
                itemDef.maleEquip1 = 64412;
                itemDef.femaleEquip1 = 64412;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;
            case 3726:
                itemDef.name = "Azure helm";
                itemDef.modelID = 64419;
                itemDef.maleEquip1 = 64420;
                itemDef.femaleEquip1 = 64420;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 3728:
                itemDef.name = "Azure body";
                itemDef.modelID = 64421;
                itemDef.maleEquip1 = 64422;
                itemDef.femaleEquip1 = 64422;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 3730:
                itemDef.name = "Azure legs";
                itemDef.modelID = 64423;
                itemDef.maleEquip1 = 64424;
                itemDef.femaleEquip1 = 64424;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;
            case 3723:
                itemDef.name = "Herbal helm";
                itemDef.modelID = 64413;
                itemDef.maleEquip1 = 64414;
                itemDef.femaleEquip1 = 64414;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;
            case 3724:
                itemDef.name = "Herbal body";
                itemDef.modelID = 64415;
                itemDef.maleEquip1 = 64416;
                itemDef.femaleEquip1 = 64416;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 3725:
                itemDef.name = "Herbal legs";
                itemDef.modelID = 64417;
                itemDef.maleEquip1 = 64418;
                itemDef.femaleEquip1 = 64418;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;
            case 8100:
                itemDef.name = "Light guild helmet";

                itemDef.modelID = 64428;
                itemDef.maleEquip1 = 64429;
                itemDef.femaleEquip1 = 64429;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

                break;
            case 8101:
                itemDef.name = "Light guild body";
                itemDef.modelID = 64430;
                itemDef.maleEquip1 = 64431;
                itemDef.femaleEquip1 = 64431;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;


                break;
            case 8102:
                itemDef.name = "Light guild legs";

                itemDef.modelID = 64432;
                itemDef.maleEquip1 = 64433;
                itemDef.femaleEquip1 = 64433;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
//itemDef.rdc2  = 47473432;

                break;
            case 8103:
                itemDef.name = "Light guild boots";
                itemdefedit = ItemDefinition.get(7114);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64434;
                itemDef.maleEquip1 = 64434;
                itemDef.femaleEquip1 = 64434;
                itemDef.actions = itemdefedit.actions;

                break;

            case 8104:
                itemDef.name = "Light guild gloves";
                itemdefedit = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1100;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64436;
                itemDef.maleEquip1 = 64436;
                itemDef.femaleEquip1 = 64436;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";

                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

//	itemDef.rdc2  = 732223;
                break;
            case 8105:
                itemDef.name = "Dark guild helmet";

                itemDef.modelID = 64437;
                itemDef.maleEquip1 = 64438;
                itemDef.femaleEquip1 = 64438;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

                break;
            case 8106:
                itemDef.name = "Dark guild body";
                itemDef.modelID = 64439;
                itemDef.maleEquip1 = 64440;
                itemDef.femaleEquip1 = 64440;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;


                break;
            case 8107:
                itemDef.name = "Dark guild legs";

                itemDef.modelID = 64441;
                itemDef.maleEquip1 = 64442;
                itemDef.femaleEquip1 = 64442;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;


                break;
            case 8108:
                itemDef.name = "Dark guild boots";
                itemdefedit = ItemDefinition.get(7114);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64443;
                itemDef.maleEquip1 = 64443;
                itemDef.femaleEquip1 = 64443;
                itemDef.actions = itemdefedit.actions;

                break;

            case 8109:
                itemDef.name = "Dark guild gloves";
                itemdefedit = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1100;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64444;
                itemDef.maleEquip1 = 64445;
                itemDef.femaleEquip1 = 64445;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";

                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

                break;
            case 13128:
                itemDef.name = "Dark guild Flail";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 729987;
                break;
            case 8110:
                itemDef.name = "Light guild sword";
                itemDef.modelID = 64446;
                itemDef.maleEquip1 = 64447;
                itemDef.femaleEquip1 = 64447;
                itemdefedit = ItemDefinition.get(22034);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1500;
                itemDef.rotationY = 400;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //itemDef.rdc2  =11234633;//225992

                break;
            case 11318:
                ItemDefinition adminsraichu = ItemDefinition.get(12479);
                itemDef.modelID = 64448;
                itemDef.modelOffsetX = adminsraichu.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = adminsraichu.modelOffsetY;
                itemDef.modelZoom = 2000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "@yel@Raichu pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                //itemDef.rdc2 = 664466;
                break;
            case 11319:
                ItemDefinition zorbak = ItemDefinition.get(12479);
                itemDef.modelID = 64476;
                itemDef.modelOffsetX = zorbak.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = zorbak.modelOffsetY;
                itemDef.modelZoom = 2000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Zorbak (elite) pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                //itemDef.rdc2 = 664466;
                break;
            case 11310:
                ItemDefinition zorbak2 = ItemDefinition.get(12479);
                itemDef.modelID = 97666;
                itemDef.modelOffsetX = zorbak2.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = zorbak2.modelOffsetY;
                itemDef.modelZoom = 6500;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Youtube JR Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                //itemDef.rdc2 = 664466;
                break;
            case 21600:
                itemDef.name = "TheHammer Torva helm";
                itemDef.modelID = 64449;
                itemDef.maleEquip1 = 64450;
                itemDef.femaleEquip1 = 64450;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;


                break;

            case 21601:
                itemDef.name = "TheHammer Torva body";
                itemDef.modelID = 64451;
                itemDef.maleEquip1 = 64452;
                itemDef.femaleEquip1 = 64452;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

                break;

            case 21602:
                itemDef.name = "TheHammer Torva legs";
                itemDef.modelID = 64453;
                itemDef.maleEquip1 = 64454;
                itemDef.femaleEquip1 = 64454;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //itemDef.rdc2 = 2039208;
                break;
            case 21603:
                itemDef.name = "TheHammer wings";
                itemDef.modelID = 64455;
                itemDef.maleEquip1 = 64456;
                itemDef.femaleEquip1 = 64456;
                itemdefedit = ItemDefinition.get(18509);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 21604:
                itemDef.name = "TheHammer kiteshield";
                itemDef.modelID = 64457;
                itemDef.maleEquip1 = 64458;
                itemDef.femaleEquip1 = 64458;
                itemdefedit = ItemDefinition.get(18509);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 21780:
                itemDef.name = "TheHammer gloves";
                itemDef.modelID = 65136;
                itemDef.maleEquip1 = 65137;
                itemDef.femaleEquip1 = 65137;
                itemdefedit = ItemDefinition.get(7462);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;
            case 21605:
                itemDef.name = "TheHammer boots";
                itemDef.modelID = 64459;
                itemDef.maleEquip1 = 64459;
                itemDef.femaleEquip1 = 64459;
                itemdefedit = ItemDefinition.get(11732);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 21606:
                itemDef.name = "TheHammer Rifle";
                itemdefedit = ItemDefinition.get(20538);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 64491;
                itemDef.femaleEquip1 = 64492;
                itemDef.maleEquip1 = 64492;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                break;
            case 5068:
                itemDef.name = "Joker helm";
                itemDef.modelID = 64497;
                itemDef.maleEquip1 = 64498;
                itemDef.femaleEquip1 = 64498;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                


                break;

            case 5069:
                itemDef.name = "Joker body";
                itemDef.modelID = 64499;
                itemDef.maleEquip1 = 64500;
                itemDef.femaleEquip1 = 64500;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;

            case 5070:
                itemDef.name = "Joker legs";
                itemDef.modelID = 64501;
                itemDef.maleEquip1 = 64502;
                itemDef.femaleEquip1 = 64502;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;


            case 5071:
                itemDef.name = "Joker gloves";
                itemDef.modelID = 64503;
                itemDef.maleEquip1 = 64504;
                itemDef.femaleEquip1 = 64504;
                itemdefedit = ItemDefinition.get(7462);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
//	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                
                break;
            case 5072:
                itemDef.name = "Joker boots";
                itemDef.modelID = 64505;
                itemDef.maleEquip1 = 64505;
                itemDef.femaleEquip1 = 64505;
                itemdefedit = ItemDefinition.get(11732);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 5073:
                itemDef.name = "Joker Rpg";
                itemdefedit = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 64506;
                itemDef.femaleEquip1 = 64507;
                itemDef.maleEquip1 = 64507;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.rdc2 = 2412432;
                break;
            case 14060:
                itemDef.name = "Crystalized helm";
                itemDef.modelID = 64513;
                itemDef.maleEquip1 = 64514;
                itemDef.femaleEquip1 = 64514;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = 1;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = 200;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                


                break;

            case 14061:
                itemDef.name = "Crystalized body";
                itemDef.modelID = 64515;
                itemDef.maleEquip1 = 64516;
                itemDef.femaleEquip1 = 64516;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;

            case 14062:
                itemDef.name = "Crystalized legs";
                itemDef.modelID = 64517;
                itemDef.maleEquip1 = 64518;
                itemDef.femaleEquip1 = 64518;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;


            case 14063:
                itemDef.name = "Crystalized gloves";
                itemDef.modelID = 64519;
                itemDef.maleEquip1 = 64520;
                itemDef.femaleEquip1 = 64520;
                itemdefedit = ItemDefinition.get(20458);
                itemDef.modelOffsetX = 30;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 400;
                itemDef.rotationY = 240;
                itemDef.rotationX = 20;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1];
                itemDef.oldColors = new int[1];
                itemDef.oldColors[0] = 40;
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                

                break;
            case 14064:
                itemDef.name = "Crystalized boots";
                itemDef.modelID = 64521;
                itemDef.maleEquip1 = 64521;
                itemDef.femaleEquip1 = 64521;
                itemdefedit = ItemDefinition.get(11732);
                itemDef.modelOffsetX = 10;
                itemDef.modelOffsetY = -15;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = 1;
                itemDef.rotationX = 2000;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 14065:
                itemDef.name = "Crystalized sword";
                itemdefedit = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 64522;
                itemDef.femaleEquip1 = 64523;
                itemDef.maleEquip1 = 64523;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                
                break;
            case 14066:
                itemDef.name = "Crystalized wings";
                itemdefedit = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 64524;
                itemDef.femaleEquip1 = 64525;
                itemDef.maleEquip1 = 64525;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                
                //itemDef.rdc2 = 2412432;
                break;
            case 9054:
                itemDef.name = "Creeper helm";
                itemDef.modelID = 64513;
                itemDef.maleEquip1 = 64514;
                itemDef.femaleEquip1 = 64514;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = 1;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = 200;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                itemDef.rdc2 = 5523329;
                break;

            case 9055:
                itemDef.name = "Creeper body";
                itemDef.modelID = 64515;
                itemDef.maleEquip1 = 64516;
                itemDef.femaleEquip1 = 64516;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                itemDef.rdc2 = 5523329;
                break;

            case 9056:
                itemDef.name = "Creeper legs";
                itemDef.modelID = 64517;
                itemDef.maleEquip1 = 64518;
                itemDef.femaleEquip1 = 64518;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                itemDef.rdc2 = 5523329;
                break;


            case 9057:
                itemDef.name = "Creeper gloves";
                itemDef.modelID = 64519;
                itemDef.maleEquip1 = 64520;
                itemDef.femaleEquip1 = 64520;
                itemdefedit = ItemDefinition.get(20458);
                itemDef.modelOffsetX = 30;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 400;
                itemDef.rotationY = 240;
                itemDef.rotationX = 20;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1];
                itemDef.oldColors = new int[1];
                itemDef.oldColors[0] = 40;
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                itemDef.rdc2 = 5523329;
                
                break;
            case 9058:
                itemDef.name = "Creeper boots";
                itemDef.modelID = 64521;
                itemDef.maleEquip1 = 64521;
                itemDef.femaleEquip1 = 64521;
                itemdefedit = ItemDefinition.get(11732);
                itemDef.modelOffsetX = 10;
                itemDef.modelOffsetY = -15;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = 1;
                itemDef.rotationX = 2000;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 5523329;
                
                break;
            case 9059:
                itemDef.name = "Creeper sword";
                itemdefedit = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 64522;
                itemDef.femaleEquip1 = 64523;
                itemDef.maleEquip1 = 64523;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.rdc2 = 5523329;
                break;
            case 9060:
                itemDef.name = "Creeper wings";
                itemdefedit = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 64524;
                itemDef.femaleEquip1 = 64525;
                itemDef.maleEquip1 = 64525;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.rdc2 = 5523329;
                break;
            case 14067:
                ItemDefinition Crystalqueenpet = ItemDefinition.get(12479);
                itemDef.modelID = 64512;
                itemDef.modelOffsetX = Crystalqueenpet.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit1.modelOffsetX;
                itemDef.modelOffsetY = Crystalqueenpet.modelOffsetY;
                itemDef.modelZoom = 2500;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Crystal queen pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
//	itemDef.rdc2 = 664466;
                break;
            case 14068:
                itemDef.name = "Garfield mask";
                itemDef.modelID = 64526;
                itemDef.maleEquip1 = 64527;
                itemDef.femaleEquip1 = 64527;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;


                break;

            case 14069:
                itemDef.name = "Garfield body";
                itemDef.modelID = 64528;
                itemDef.maleEquip1 = 64529;
                itemDef.femaleEquip1 = 64529;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

                break;

            case 14070:
                itemDef.name = "Garfield legs";
                itemDef.modelID = 64530;
                itemDef.maleEquip1 = 64531;
                itemDef.femaleEquip1 = 64531;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //itemDef.rdc2 = 2039208;
                break;


            case 14071:
                itemDef.name = "Garfield gloves";
                itemDef.modelID = 64532;
                itemDef.maleEquip1 = 64533;
                itemDef.femaleEquip1 = 64533;
                itemdefedit = ItemDefinition.get(20458);
                itemDef.modelOffsetX = 30;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 400;
                itemDef.rotationY = 240;
                itemDef.rotationX = 20;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;
            case 14072:
                itemDef.name = "Garfield boots";
                itemDef.modelID = 64534;
                itemDef.maleEquip1 = 64534;
                itemDef.femaleEquip1 = 64534;
                itemdefedit = ItemDefinition.get(11732);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 14073:
                itemDef.name = "Garfield Scarf";
                itemdefedit = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 64535;
                itemDef.femaleEquip1 = 64536;
                itemDef.maleEquip1 = 64536;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";

                break;
            case 21607:
                itemDef.name = "Custom Extreme helm";
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64194;
                itemDef.maleEquip1 = 64195;
                itemDef.femaleEquip1 = 64195;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 123829;//77777
                break;

            case 21608:
                itemDef.name = "Custom Extreme body";
                itemdefedit = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64196;
                itemDef.maleEquip1 = 64197;
                itemDef.femaleEquip1 = 64197;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 123829;
                break;
            case 21609:
                itemDef.name = "Custom Extreme legs";
                itemdefedit = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64198;
                itemDef.maleEquip1 = 64199;
                itemDef.femaleEquip1 = 64199;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 123829;
                break;
            case 21610:
                itemDef.name = "Custom Extreme Teddy bear";
                itemDef.modelID = 64034;
                itemDef.maleEquip1 = 64035;
                itemDef.femaleEquip1 = 64035;
                itemdefedit = ItemDefinition.get(8848);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemdefedit.rotationY;

                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 204929;
                break;

            case 21611:
                itemDef.name = "Custom Extreme gloves";
                itemdefedit = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64202;
                itemDef.maleEquip1 = 64203;
                itemDef.femaleEquip1 = 64203;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 123829;
                break;
            case 21612:
                itemDef.name = "Custom Extreme boots";
                itemdefedit = ItemDefinition.get(7114);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.modelID = 64204;
                itemDef.maleEquip1 = 64204;
                itemDef.femaleEquip1 = 64204;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 123829;
                break;
            case 21613:
                itemDef.name = "Custom Extreme owner cape";
                itemDef.modelID = 64462;
                itemDef.maleEquip1 = 64463;
                itemDef.femaleEquip1 = 64463;
                itemdefedit = ItemDefinition.get(2414);
//	itemDef.modelID = 65333;
                //itemDef.maleWearId = 65334;
                //itemDef.femaleWearId = 65334;
                itemDef.actions = itemdefedit.actions;
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                //	itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.oldColors = new int[]{57};
                itemDef.newColors = new int[]{52};
//	itemDef.actions[3] = "<col=f8a6c1>Activate god mode";

                //itemDef.rdc2 = 762432;
//	itemDef.modelZoom = 1385;
//	itemDef.modelOffset1 = 0;
//	itemDef.modelOffsetY = 24;
//	itemDef.modelRotationY = 279;
//	itemDef.modelRotationX = 948;
                break;
            case 8828:
                itemDef.copyItem(4708);
                itemDef.modelID = 131483;
                itemDef.maleEquip1 = 131484;
                itemDef.femaleEquip1 = 131484;
                itemDef.modelZoom = 900;
                itemDef.name = "Elite helm (melee)";
                break;
            case 8829:
                itemDef.copyItem(4712);
                itemDef.modelID = 131485;
                itemDef.maleEquip1 = 131486;
                itemDef.femaleEquip1 = 131486;
                itemDef.modelZoom = 1500;
                itemDef.name = "Elite body (melee)";
                break;

            case 8833:
                itemDef.copyItem(4714);
                itemDef.modelID = 131487;
                itemDef.maleEquip1 = 131488;
                itemDef.femaleEquip1 = 131488;
                itemDef.modelZoom = 1800;
                itemDef.name = "Elite legs (melee)";
                break;

            case 15642:
                itemDef.copyItem(4708);
                itemDef.modelID = 139921;
                itemDef.maleEquip1 = 139920;
                itemDef.femaleEquip1 = 139920;
                itemDef.modelZoom = 900;
                itemDef.name = "Elite helm (ranged)";
                break;
            case 15643:
                itemDef.copyItem(4712);
                itemDef.modelID = 139923;
                itemDef.maleEquip1 = 139922;
                itemDef.femaleEquip1 = 139922;
                itemDef.modelZoom = 900;
                itemDef.name = "Elite body (ranged)";
                break;
            case 15644:
                itemDef.copyItem(4714);
                itemDef.modelID = 139924;
                itemDef.maleEquip1 = 139924;
                itemDef.femaleEquip1 = 139924;
                itemDef.modelZoom = 1800;
                itemDef.name = "Elite legs (ranged)";
                break;

            case 15645:
                itemDef.copyItem(4708);
                itemDef.modelID = 130888;
                itemDef.maleEquip1 = 130888;
                itemDef.femaleEquip1 = 130888;
                itemDef.modelZoom = 900;
                itemDef.name = "Elite helm (mage)";
                break;
            case 15646:
                itemDef.copyItem(4712);
                itemDef.modelID = 130889;
                itemDef.maleEquip1 = 130889;
                itemDef.femaleEquip1 = 130889;
                itemDef.modelZoom = 900;
                itemDef.name = "Elite body (mage)";
                break;
            case 15647:
                itemDef.copyItem(4714);
                itemDef.modelID = 130890;
                itemDef.maleEquip1 = 130890;
                itemDef.femaleEquip1 = 130890;
                itemDef.modelZoom = 1800;
                itemDef.name = "Elite legs (mage)";
                break;

            case 14176:
                itemDef.name = "Optimus suit helmet";
                itemDef.modelID = 64479;
                itemDef.maleEquip1 = 64480;
                itemDef.femaleEquip1 = 64480;
                itemdefedit = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //itemDef.rdc2  = 20483;
                break;
            case 14172:
                itemDef.name = "Optimus suit body";
                itemDef.modelID = 64481;
                itemDef.maleEquip1 = 64482;
                itemDef.femaleEquip1 = 64482;
                itemdefedit = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

                break;
            case 14174:
                itemDef.name = "Optimus suit legs";
                itemDef.modelID = 64483;
                itemDef.maleEquip1 = 64484;
                itemDef.femaleEquip1 = 64484;
                itemdefedit = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemdefedit.modelOffsetX;
                itemDef.modelOffsetY = itemdefedit.modelOffsetY;
                itemDef.modelZoom = itemdefedit.modelZoom;
                itemDef.rotationY = itemdefedit.rotationY;
                itemDef.rotationX = itemdefedit.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //itemDef.rdc2  = 20483;
                break;
            //cyan 444444
            case 20086:
                itemDef.copyItem(4708);
                itemDef.name = "Cursed helm";
                itemDef.modelID = 100279;
                itemDef.maleEquip1 = 100280;
                itemDef.femaleEquip1 = 100280;
                itemDef.modelZoom = 800;
                
                break;
            case 20087:
                itemDef.copyItem(4712);
                itemDef.name = "Cursed body";
                itemDef.modelID = 100272;
                itemDef.maleEquip1 = 100273;
                itemDef.femaleEquip1 = 100273;
                itemDef.modelZoom = 1500;
                
                break;
            case 20088:
                itemDef.copyItem(4714);
                itemDef.name = "Cursed legs";
                itemDef.modelID = 100277;
                itemDef.maleEquip1 = 100278;
                itemDef.femaleEquip1 = 100278;
                itemDef.modelZoom = 1800;
                
                break;
            case 20090:
                itemDef.name = "Cursed spirit shield";
                itemdefyogipic = ItemDefinition.get(1540);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64200;
                itemDef.maleEquip1 = 64201;
                itemDef.femaleEquip1 = 64201;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 44322;
                break;

            case 20091:
                itemDef.copyItem(4708);
                itemDef.name = "Cursed gloves";
                itemDef.modelID = 100275;
                itemDef.maleEquip1 = 100276;
                itemDef.femaleEquip1 = 100276;
                itemDef.modelZoom = 700;
                
                break;
            case 20089:
                itemDef.copyItem(4708);
                itemDef.name = "Cursed boots";
                itemDef.modelID = 100274;
                itemDef.maleEquip1 = 100274;
                itemDef.femaleEquip1 = 100274;
                itemDef.modelZoom = 700;
                
                break;
            case 20092:
                itemDef.name = "Cursed amulet";
                itemdefyogipic = ItemDefinition.get(295);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64205;
                itemDef.maleEquip1 = 64206;
                itemDef.femaleEquip1 = 64206;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 44322;
                break;
            case 20093:
                itemDef.name = "Cursed ring";
                itemdefyogipic = ItemDefinition.get(1635);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64207;
                itemDef.maleEquip1 = 64208;
                itemDef.femaleEquip1 = 64208;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 44322;
                break;
            case 20098:
                itemDef.name = "Cursed scimitar";
                itemdefyogipic = ItemDefinition.get(4587);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64209;
                itemDef.maleEquip1 = 64210;
                itemDef.femaleEquip1 = 64210;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 44322;
                break;
            case 20099:
                itemDef.name = "Cursed cape";
                itemdefyogipic = ItemDefinition.get(2413);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64211;
                itemDef.maleEquip1 = 64212;
                itemDef.femaleEquip1 = 64212;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 44322;
                break;
            case 20100:
                itemDef.name = "Cursed rapier";
                itemdefyogipic = ItemDefinition.get(4587);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64213;
                itemDef.maleEquip1 = 64214;
                itemDef.femaleEquip1 = 64214;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 44322;
                break;
            case 21062:
                itemDef.name = "Ruthless helm";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64194;
                itemDef.maleEquip1 = 64195;
                itemDef.femaleEquip1 = 64195;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;
            case 21063:
                itemDef.name = "Ruthless body";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64196;
                itemDef.maleEquip1 = 64197;
                itemDef.femaleEquip1 = 64197;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;
            case 21064:
                itemDef.name = "Ruthless legs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64198;
                itemDef.maleEquip1 = 64199;
                itemDef.femaleEquip1 = 64199;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;
            case 21065:
                itemDef.name = "Ruthless spirit shield";
                itemdefyogipic = ItemDefinition.get(1540);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64200;
                itemDef.maleEquip1 = 64201;
                itemDef.femaleEquip1 = 64201;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;

            case 21066:
                itemDef.name = "Ruthless gloves";
                itemdefyogipic = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64202;
                itemDef.maleEquip1 = 64203;
                itemDef.femaleEquip1 = 64203;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;
            case 21067:
                itemDef.name = "Ruthless boots";
                itemdefyogipic = ItemDefinition.get(7114);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64204;
                itemDef.maleEquip1 = 64204;
                itemDef.femaleEquip1 = 64204;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;
            case 21068:
                itemDef.name = "Ruthless amulet";
                itemdefyogipic = ItemDefinition.get(295);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64205;
                itemDef.maleEquip1 = 64206;
                itemDef.femaleEquip1 = 64206;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;
            case 21069:
                itemDef.name = "Ruthless ring";
                itemdefyogipic = ItemDefinition.get(1635);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64207;
                itemDef.maleEquip1 = 64208;
                itemDef.femaleEquip1 = 64208;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;
            case 21070:
                itemDef.name = "Ruthless scimitar";
                itemdefyogipic = ItemDefinition.get(4587);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64209;
                itemDef.maleEquip1 = 64210;
                itemDef.femaleEquip1 = 64210;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;
            case 21071:
                itemDef.name = "Ruthless cape";
                itemdefyogipic = ItemDefinition.get(2413);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = -50;
                itemDef.modelZoom = 2300;
                itemDef.rotationY = 200;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64211;
                itemDef.maleEquip1 = 64212;
                itemDef.femaleEquip1 = 64212;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 23662;
                break;
            case 21072:
                itemDef.name = "Ruthless rapier";
                itemdefyogipic = ItemDefinition.get(4587);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64213;
                itemDef.maleEquip1 = 64214;
                itemDef.femaleEquip1 = 64214;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 77743;
                break;
            case 21073:
                itemDef.name = "Ruthless Scimitar";
                itemdefyogipic = ItemDefinition.get(4587);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.maleEquip1 = itemdefyogipic.maleEquip1;
                itemDef.femaleEquip1 = itemdefyogipic.femaleEquip1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, "Upgrade", "Destroy"};
                itemDef.stackable = false;
                
                itemDef.rdc2 = 12124;
                break;
            case 13263:
                itemDef.actions = new String[]{null, "Wear", null, "Upgrade", "Destroy"};
                break;

            case 21075:
                itemDef.name = "Slayer helmet [Level 1]";
                itemdefyogipic = ItemDefinition.get(13263);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.maleEquip1 = itemdefyogipic.maleEquip1;
                itemDef.femaleEquip1 = itemdefyogipic.femaleEquip1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, "Upgrade", "Destroy"};
                itemDef.stackable = false;
                //	
                itemDef.rdc2 = 35363;
                break;
            case 21076:
                itemDef.name = "Slayer helmet [Level 2]";
                itemdefyogipic = ItemDefinition.get(13263);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.maleEquip1 = itemdefyogipic.maleEquip1;
                itemDef.femaleEquip1 = itemdefyogipic.femaleEquip1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, "Upgrade", "Destroy"};
                itemDef.stackable = false;
                //
                itemDef.rdc2 = 444444;
                break;
            case 21077:
                itemDef.name = "Slayer helmet [level 3]";
                itemdefyogipic = ItemDefinition.get(13263);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.maleEquip1 = itemdefyogipic.maleEquip1;
                itemDef.femaleEquip1 = itemdefyogipic.femaleEquip1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, "Upgrade", "Destroy"};
                itemDef.stackable = false;
                //
                itemDef.rdc2 = 8858;
                break;

            case 21078:
                itemDef.name = "Slayer helmet [Level 4]";
                itemdefyogipic = ItemDefinition.get(13263);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.maleEquip1 = itemdefyogipic.maleEquip1;
                itemDef.femaleEquip1 = itemdefyogipic.femaleEquip1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, "Upgrade", "Destroy"};
                itemDef.stackable = false;
                //
                itemDef.rdc2 = 8808080;//8808080
                break;
            case 21079:
                itemDef.name = "Slayer helmet [MAX]";
                itemdefyogipic = ItemDefinition.get(13263);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.maleEquip1 = itemdefyogipic.maleEquip1;
                itemDef.femaleEquip1 = itemdefyogipic.femaleEquip1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //
                itemDef.rdc2 = 74623;
                break;
            case 15230:
                itemDef.name = "Zeus full helm";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64165;
                itemDef.maleEquip1 = 64166;
                itemDef.femaleEquip1 = 64166;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{73};
                break;
            case 15231:
                itemDef.name = "Zeus body";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64167;
                itemDef.maleEquip1 = 64168;
                itemDef.femaleEquip1 = 64168;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{73};
                break;
            case 15232:
                itemDef.name = "Zeus legs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64169;
                itemDef.maleEquip1 = 64170;
                itemDef.femaleEquip1 = 64170;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{73};
                break;
            case 15233:
                itemDef.name = "Zeus Hammer";
                itemDef.modelID = 64171;
                itemDef.maleEquip1 = 64172;
                itemDef.femaleEquip1 = 64172;
                itemDef21 = ItemDefinition.get(4755);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                //itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{73};
                break;
            case 15234:
                itemDef.name = "Zeus shield";
                itemdefyogipic = ItemDefinition.get(1540);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64173;
                itemDef.maleEquip1 = 64174;
                itemDef.femaleEquip1 = 64174;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{73};
                break;
            case 21048:
                itemDef.name = "Fiya Sword+";
                itemDef.modelID = 64280;
                itemDef.maleEquip1 = 64281;
                itemDef.femaleEquip1 = 64281;
                itemDef21 = ItemDefinition.get(4755);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                //itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{73};
                break;
            case 21049:
                itemDef.name = "Fiya Shield+";
                itemdefyogipic = ItemDefinition.get(1540);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64282;
                itemDef.maleEquip1 = 64283;
                itemDef.femaleEquip1 = 64283;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{73};
                break;
            case 21031:
                itemDef.name = "Hades full helm";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64259;
                itemDef.maleEquip1 = 64260;
                itemDef.femaleEquip1 = 64260;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 2724624;
                break;
            case 21032:
                itemDef.name = "Hades body";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64261;
                itemDef.maleEquip1 = 64262;
                itemDef.femaleEquip1 = 64262;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 2724624;
                break;
            case 21033:
                itemDef.name = "Hades legs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64263;
                itemDef.maleEquip1 = 64264;
                itemDef.femaleEquip1 = 64264;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 2724624;
                break;
            case 21034:
                itemDef.name = "Hades Hammer";
                itemDef.modelID = 64265;
                itemDef.maleEquip1 = 64266;
                itemDef.femaleEquip1 = 64266;
                itemDef21 = ItemDefinition.get(4755);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                //itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 99957;
                break;
            case 21035:
                itemDef.name = "Hades shield";
                itemdefyogipic = ItemDefinition.get(1540);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64267;
                itemDef.maleEquip1 = 64268;
                itemDef.femaleEquip1 = 64268;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 2724624;
                break;
            case 15235:
                itemDef.name = "Electric gloves";
                itemdefyogipic = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = 1100;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64175;
                itemDef.maleEquip1 = 64176;
                itemDef.femaleEquip1 = 64176;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 15236:
                itemDef.name = "Electric boots";
                itemdefyogipic = ItemDefinition.get(7114);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64177;
                itemDef.maleEquip1 = 64178;
                itemDef.femaleEquip1 = 64178;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 11305:
                itemDef.name = "Earthquake full helm";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64151;
                itemDef.maleEquip1 = 64152;
                itemDef.femaleEquip1 = 64152;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 3462;
                break;
            case 11306:
                itemDef.name = "Earthquake platebody";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64153;
                itemDef.maleEquip1 = 64154;
                itemDef.femaleEquip1 = 64154;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 3462;
                break;

            case 11307:
                itemDef.name = "Earthquake platelegs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64155;
                itemDef.maleEquip1 = 64156;
                itemDef.femaleEquip1 = 64156;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 3462;
                break;
            case 11308:
                itemDef.name = "Earthquake battlesword";
                itemDef.modelID = 64157;
                itemDef.maleEquip1 = 64158;
                itemDef.femaleEquip1 = 64158;
                def = ItemDefinition.get(22034);
                itemDef.modelOffsetX = def.modelOffsetX;
                itemDef.modelOffsetY = def.modelOffsetY;
                itemDef.modelZoom = def.modelZoom;
                itemDef.rotationY = def.rotationY;
                itemDef.rotationX = def.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 3462;
                
                break;
            case 11300:
                itemDef.name = "Defender full helm";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64141;
                itemDef.maleEquip1 = 64142;
                itemDef.femaleEquip1 = 64142;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{58};
                break;
            case 11301:
                itemDef.name = "Defender fighterbody";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64143;
                itemDef.maleEquip1 = 64144;
                itemDef.femaleEquip1 = 64144;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{58};
                break;
            case 11302:
                itemDef.name = "Defender fighterlegs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64145;
                itemDef.maleEquip1 = 64146;
                itemDef.femaleEquip1 = 64146;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{58};
                break;
            case 11303:
                itemDef.name = "Defender kiteshield";
                itemdefyogipic = ItemDefinition.get(1540);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64147;
                itemDef.maleEquip1 = 64148;
                itemDef.femaleEquip1 = 64148;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{58};
                break;
            case 11304:
                itemDef.name = "Defender cape";
                itemdefyogipic = ItemDefinition.get(2413);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64149;
                itemDef.maleEquip1 = 64150;
                itemDef.femaleEquip1 = 64150;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{58};
                break;
            case 11315:
                ItemDefinition itemDef2111 = ItemDefinition.get(12479);
                itemDef.modelID = 64100;
                itemDef.modelOffsetX = itemDef2111.modelOffsetX;
                //itemDef.modelOffsetX = itemDef211.modelOffsetX;
                itemDef.modelOffsetY = itemDef2111.modelOffsetY;
                itemDef.modelZoom = 7950;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "<img=1355><col=f8ac00>double afk pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                break;
            case 11316:
                votingpoting = ItemDefinition.get(12479);
                itemDef.modelID = 130620;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "<img=28><col=f8ac00>Double vote pet";
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;

                itemDef.modelZoom = 1428;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 1;
                break;
            case 2098:
                itemDef.name = "Skilling ticket";
                itemDef.rdc2 = 12511;
                itemDef.stackable = true;
                itemDef21 = ItemDefinition.get(18652);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                //	itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.modelID = itemDef21.modelID;
                itemDef.actions = itemDef21.actions;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;

                break;
            case 20511:
                ItemDefinition hooker = ItemDefinition.get(12479);
                itemDef.modelID = 36071;
                itemDef.modelOffsetX = 2;
                //itemDef.modelOffsetX = itemDef211.modelOffsetX;
                itemDef.modelOffsetY = 80;
                itemDef.rotationY = 204;
                itemDef.rotationX = 20;
                itemDef.modelZoom = 2700;
                itemDef.name = "Hooker pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 5504:
                ItemDefinition charizard = ItemDefinition.get(12479);
                itemDef.modelID = 64067;
                itemDef.modelOffsetX = charizard.modelOffsetX;
                //itemDef.modelOffsetX = itemDef211.modelOffsetX;
                itemDef.modelOffsetY = charizard.modelOffsetY;
                itemDef.modelZoom = 4000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Charizard pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 5506:
                ItemDefinition donkeykong2 = ItemDefinition.get(12479);
                itemDef.modelID = 64225;
                itemDef.modelOffsetX = donkeykong2.modelOffsetX;
                //itemDef.modelOffsetX = itemDef211.modelOffsetX;
                itemDef.modelOffsetY = donkeykong2.modelOffsetY;
                itemDef.modelZoom = 4000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Donkeykong pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 5507:
                ItemDefinition sonicw = ItemDefinition.get(12479);
                itemDef.modelID = 64224;
                itemDef.modelOffsetX = sonicw.modelOffsetX;
                //itemDef.modelOffsetX = itemDef211.modelOffsetX;
                itemDef.modelOffsetY = sonicw.modelOffsetY;
                itemDef.modelZoom = 3000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Sonic pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 5508:
                ItemDefinition Mario = ItemDefinition.get(12479);
                itemDef.modelID = 64223;
                itemDef.modelOffsetX = Mario.modelOffsetX;
                //itemDef.modelOffsetX = itemDef211.modelOffsetX;
                itemDef.modelOffsetY = Mario.modelOffsetY;
                itemDef.modelZoom = 3000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Mario pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 5560:
                ItemDefinition superstar2 = ItemDefinition.get(12479);
                itemDef.modelID = 64305;
                itemDef.modelOffsetX = superstar2.modelOffsetX;
                //itemDef.modelOffsetX = itemDef211.modelOffsetX;
                itemDef.modelOffsetY = superstar2.modelOffsetY;
                itemDef.modelZoom = 2000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "Super Star pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 5563:
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = 10;
                itemDef.rotationX = 10;
                itemDef.modelID = 64306;

                itemDef.name = "Grinch pet @whi@X2 EXP@or1@";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;


            case 14819:
                itemDef.name = "x2 Slayer XP Certificate";
                break;

            case 14817:
                itemdefyogipic = ItemDefinition.get(14819);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.name = "x2 Invention XP Certificate";
                itemDef.rdc2 = 2592;
                break;
            case 14822:
                itemDef.name = "<img=15>VIP Slayer License";
                itemDef.actions = new String[]{"Claim", null, null, null, "Destroy"};
                break;
            case 28:
                itemDef.name = "Slayer chest key";
                itemdefyogipic = ItemDefinition.get(989);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = 760;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = 800;
                itemDef.modelID = 64299;
                itemDef.stackable = false;
                break;
            case 29:
                itemDef.name = "Locked slayer chest";
                itemdefyogipic = ItemDefinition.get(405);
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 1;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = 100;
                itemDef.rotationX = 90;
                itemDef.modelID = 64300;
                itemDef.modelZoom = 1100;
                itemDef.stackable = false;
                break;

            case 27:
                itemDef.name = "<img=15>VIP Slayer Gem";
                itemDef.actions = new String[]{"Teleport to task", null, "Teleport to VIP", null, "Destroy"};
                itemDef.rdc2 = 37483;
                itemdefyogipic = ItemDefinition.get(4155);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                break;
            case 14827:
                itemDef.name = "American pernix hood";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 200;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = 260;
                itemDef.rotationX = 20;
                itemDef.modelID = 64159;
                itemDef.maleEquip1 = 64160;
                itemDef.femaleEquip1 = 64160;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 14818:
                itemDef.name = "American pernix body";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = 1350;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64161;
                itemDef.maleEquip1 = 64162;
                itemDef.femaleEquip1 = 64162;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 15441:
            case 15442:
            case 15443:
            case 15444:
                
                break;
            case 13953:
            case 13954:
            case 13955:
            case 13956:
            case 13957:
                
                break;
            case 14820:
                itemDef.name = "American pernix legs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = 7;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64163;
                itemDef.maleEquip1 = 64164;
                itemDef.femaleEquip1 = 64164;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 21050:
                itemDef.name = "Slayermaster hood";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64284;
                itemDef.maleEquip1 = 64285;
                itemDef.femaleEquip1 = 64285;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 21051:
                itemDef.name = "Slayermaster body";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64286;
                itemDef.maleEquip1 = 64287;
                itemDef.femaleEquip1 = 64287;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 21052:
                itemDef.name = "Slayermaster legs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64288;
                itemDef.maleEquip1 = 64289;
                itemDef.femaleEquip1 = 64289;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 21025:
                itemDef.name = "Samurai head";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64247;
                itemDef.maleEquip1 = 64248;
                itemDef.femaleEquip1 = 64248;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 21026:
                itemDef.name = "Samurai body";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64249;
                itemDef.maleEquip1 = 64250;
                itemDef.femaleEquip1 = 64250;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 21027:
                itemDef.name = "Samurai legs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64251;
                itemDef.maleEquip1 = 64252;
                itemDef.femaleEquip1 = 64252;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 21028:
                itemDef.name = "eSamurai head";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64253;
                itemDef.maleEquip1 = 64254;
                itemDef.femaleEquip1 = 64254;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 923295;
                break;
            case 21029:
                itemDef.name = "eSamurai body";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64255;
                itemDef.maleEquip1 = 64256;
                itemDef.femaleEquip1 = 64256;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 923295;
                break;
            case 21030:
                itemDef.name = "eSamurai legs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64257;
                itemDef.maleEquip1 = 64258;
                itemDef.femaleEquip1 = 64258;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 923295;
                break;
            case 11009:
                itemDef.name = "Hotshot helm";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64186;
                itemDef.maleEquip1 = 64187;
                itemDef.femaleEquip1 = 64187;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 11010:
                itemDef.name = "Hotshot body";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64188;
                itemDef.maleEquip1 = 64189;
                itemDef.femaleEquip1 = 64189;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 11011:
                itemDef.name = "Hotshot legs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64190;
                itemDef.maleEquip1 = 64191;
                itemDef.femaleEquip1 = 64191;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 11012:
                itemDef.name = "Hotshot wings";
                itemdefyogipic = ItemDefinition.get(2413);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64192;
                itemDef.maleEquip1 = 64193;
                itemDef.femaleEquip1 = 64193;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 11001:
                itemDef.name = "Hiddenvally coif";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64179;
                itemDef.maleEquip1 = 64180;
                itemDef.femaleEquip1 = 64180;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 11002:
                itemDef.name = "Hiddenvally leatherbody";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64181;
                itemDef.maleEquip1 = 64182;
                itemDef.femaleEquip1 = 64182;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;

            case 2575:
                itemDef.name = "Goku Watch";
                
                break;
            case 11003:
                itemDef.name = "Hiddenvally leatherchaps";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64183;
                itemDef.maleEquip1 = 64184;
                itemDef.femaleEquip1 = 64184;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                break;
            case 11006:
                itemDef.name = "Forbidden coif";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64179;
                itemDef.maleEquip1 = 64180;
                itemDef.femaleEquip1 = 64180;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 29592;
                break;
            case 11007:
                itemDef.name = "Forbidden leatherbody";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64181;
                itemDef.maleEquip1 = 64182;
                itemDef.femaleEquip1 = 64182;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 29592;
                break;
            case 11008:
                itemDef.name = "Forbidden leatherchaps";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64183;
                itemDef.maleEquip1 = 64184;
                itemDef.femaleEquip1 = 64184;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                
                itemDef.rdc2 = 29592;
                break;
            case 11314:
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = 10;
                itemDef.rotationX = 10;
                itemDef.modelID = 64123;
                //itemDef.maleWearId = 64124;
                //itemDef.femaleWearId = 64124;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "<col=f8ac00>Goku Pet (x2 KC)";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 8868:
                itemDef.name = "<col=f8ac00>Betrayed key@lre@";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 21201:
                itemDef.name = "Betrayed key [<col=92d49a>Uncommon@lre@]";
                itemdefyogipic = ItemDefinition.get(8868);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 4710;
                break;
            case 21202:
                itemDef.name = "Betrayed key [<col=789eea>Rare@lre@]";
                itemdefyogipic = ItemDefinition.get(8868);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 888888;
                break;
            case 21203:
                itemDef.name = "Betrayed key [<col=c378ea>Legendary@lre@]";
                itemdefyogipic = ItemDefinition.get(8868);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 111111;
                break;
            case 21204:
                itemDef.name = "Betrayed key [<col=eae678>Exotic@lre@]";
                itemdefyogipic = ItemDefinition.get(8868);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 88757;
                break;
            case 9662:
                itemDef.name = "<col=f8ac00>Damned key@lre@";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 21205:
                itemDef.name = "Damned key [<col=92d49a>Uncommon@lre@]";
                itemdefyogipic = ItemDefinition.get(9662);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 4710;
                break;
            case 21206:
                itemDef.name = "Damned key [<col=789eea>Rare@lre@]";
                itemdefyogipic = ItemDefinition.get(9662);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 888888;
                break;
            case 21207:
                itemDef.name = "Damned key [<col=c378ea>Legendary@lre@]";
                itemdefyogipic = ItemDefinition.get(9662);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 111111;
                break;
            case 21208:
                itemDef.name = "Damned key [<col=eae678>Exotic@lre@]";
                itemdefyogipic = ItemDefinition.get(9662);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 88757;
                break;
            case 14471:
                itemDef.name = "<col=f8ac00>Hidden key@lre@";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 21209:
                itemDef.name = "Hidden key [<col=92d49a>Uncommon@lre@]";
                itemdefyogipic = ItemDefinition.get(14471);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 1896;
                break;
            case 21210:
                itemDef.name = "Hidden key [<col=789eea>Rare@lre@]";
                itemdefyogipic = ItemDefinition.get(14471);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 888888;
                break;
            case 21211:
                itemDef.name = "Hidden key [<col=c378ea>Legendary@lre@]";
                itemdefyogipic = ItemDefinition.get(14471);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 111111;
                break;
            case 21212:
                itemDef.name = "Hidden key [<col=eae678>Exotic@lre@]";
                itemdefyogipic = ItemDefinition.get(14471);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 88757;
                break;
            case 3468:
                itemDef.name = "<col=f8ac00>Cursed key@lre@";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 21213:
                itemDef.name = "Cursed key [<col=92d49a>Uncommon@lre@]";
                itemdefyogipic = ItemDefinition.get(3468);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 5364;
                break;
            case 21214:
                itemDef.name = "Cursed key [<col=789eea>Rare@lre@]";
                itemdefyogipic = ItemDefinition.get(3468);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 888888;
                break;
            case 21215:
                itemDef.name = "Cursed key [<col=c378ea>Legendary@lre@]";
                itemdefyogipic = ItemDefinition.get(3468);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 7326;
                break;
            case 21216:
                itemDef.name = "Cursed key [<col=eae678>Exotic@lre@]";
                itemdefyogipic = ItemDefinition.get(3468);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 88757;
                break;
            case 21217:
                itemDef.name = "Slayer xp lamp ";
                itemdefyogipic = ItemDefinition.get(19750);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 36262;
                break;
            case 15403:
                itemDef.name = "Sharp balmung";
                itemDef.rdc2 = 9224592;
                break;
            case 10887:
                itemDef.name = "Pirates ancor";
                itemDef.rdc2 = 9939;
                break;
            case 21219:
                itemDef.name = "Slayer xp lamp ";
                itemdefyogipic = ItemDefinition.get(19750);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = 750;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 36262;
                break;
            case 21218:
                itemDef.name = "Invention xp lamp ";
                itemdefyogipic = ItemDefinition.get(19750);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = itemdefyogipic.modelID;
                itemDef.actions = itemdefyogipic.actions;
                itemDef.rdc2 = 3344;
                break;
            case 21260:
                itemDef.name = "offhand glaive";
                itemDef.modelID = 64301;
                itemDef.maleEquip1 = 64302;
                itemDef.femaleEquip1 = 64302;
                def = ItemDefinition.get(22034);
                itemDef.modelOffsetX = def.modelOffsetX;
                itemDef.modelOffsetY = def.modelOffsetY;
                itemDef.modelZoom = def.modelZoom;
                itemDef.rotationY = def.rotationY;
                itemDef.rotationX = def.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 20505:
                itemDef.name = "saggy offhand glaive";
                itemDef.modelID = 64301;
                itemDef.maleEquip1 = 64302;
                itemDef.femaleEquip1 = 64302;
                def = ItemDefinition.get(22034);
                itemDef.modelOffsetX = def.modelOffsetX;
                itemDef.modelOffsetY = def.modelOffsetY;
                itemDef.modelZoom = def.modelZoom;
                itemDef.rotationY = def.rotationY;
                itemDef.rotationX = def.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //

                break;
            case 5424:
                itemDef.name = "madman offhand glaive";
                itemDef.modelID = 64301;
                itemDef.maleEquip1 = 64302;
                itemDef.femaleEquip1 = 64302;
                def = ItemDefinition.get(22034);
                itemDef.modelOffsetX = def.modelOffsetX;
                itemDef.modelOffsetY = def.modelOffsetY;
                itemDef.modelZoom = def.modelZoom;
                itemDef.rotationY = def.rotationY;
                itemDef.rotationX = def.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //

                break;
            case 21261:
                itemDef.name = "offhand glaive";
                itemDef.modelID = 64301;
                itemDef.maleEquip1 = 64302;
                itemDef.femaleEquip1 = 64302;
                def = ItemDefinition.get(22034);
                itemDef.modelOffsetX = def.modelOffsetX;
                itemDef.modelOffsetY = def.modelOffsetY;
                itemDef.modelZoom = def.modelZoom;
                itemDef.rotationY = def.rotationY;
                itemDef.rotationX = def.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                itemDef.rdc2 = 464646;
                break;
            case 21262:
                itemDef.name = "offhand glaive";
                itemDef.modelID = 64301;
                itemDef.maleEquip1 = 64302;
                itemDef.femaleEquip1 = 64302;
                def = ItemDefinition.get(22034);
                itemDef.modelOffsetX = def.modelOffsetX;
                itemDef.modelOffsetY = def.modelOffsetY;
                itemDef.modelZoom = def.modelZoom;
                itemDef.rotationY = def.rotationY;
                itemDef.rotationX = def.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                itemDef.rdc2 = 85722;
                break;
            case 21263:
                itemDef.name = "offhand glaive";
                itemDef.modelID = 64301;
                itemDef.maleEquip1 = 64302;
                itemDef.femaleEquip1 = 64302;
                def = ItemDefinition.get(22034);
                itemDef.modelOffsetX = def.modelOffsetX;
                itemDef.modelOffsetY = def.modelOffsetY;
                itemDef.modelZoom = def.modelZoom;
                itemDef.rotationY = def.rotationY;
                itemDef.rotationX = def.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                itemDef.rdc2 = 43722;
                break;
            case 455:
                itemDef.name = "Scratch Card";
                break;
            case 18599:
                itemDef.name = "Pink Bikini head";
                itemdefyogipic = ItemDefinition.get(4882);
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -60;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = 230;
                itemDef.rotationX = 200;
                itemDef.modelID = 64317;
                itemDef.maleEquip1 = 64318;
                itemDef.femaleEquip1 = 64318;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                break;
            case 18600:
                itemDef.name = "Bikini body";
                itemdefyogipic = ItemDefinition.get(5575);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = 1600;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64319;
                itemDef.maleEquip1 = 64320;
                itemDef.femaleEquip1 = 64320;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                break;
            case 18601:
                itemDef.name = "Bikini legs";
                itemdefyogipic = ItemDefinition.get(5576);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = 20;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64321;
                itemDef.maleEquip1 = 64322;
                itemDef.femaleEquip1 = 64322;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                break;
            case 18602:
                itemDef.name = "Bikini hands";
                itemdefyogipic = ItemDefinition.get(7461);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64323;
                itemDef.maleEquip1 = 64324;
                itemDef.femaleEquip1 = 64324;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                break;
            case 18603:
                itemDef.name = "Bikini feet";
                itemdefyogipic = ItemDefinition.get(7114);
                itemDef.modelOffsetX = itemdefyogipic.modelOffsetX;
                itemDef.modelOffsetY = itemdefyogipic.modelOffsetY;
                itemDef.modelZoom = itemdefyogipic.modelZoom;
                itemDef.rotationY = itemdefyogipic.rotationY;
                itemDef.rotationX = itemdefyogipic.rotationX;
                itemDef.modelID = 64325;
                itemDef.maleEquip1 = 64325;
                itemDef.femaleEquip1 = 64325;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                break;
            case 5012:
                itemDef.name = "Execution Twisted Bow";
                ItemDefinition tbow = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 65324;
                itemDef.femaleEquip1 = 65325;
                itemDef.maleEquip1 = 65325;
                itemDef.actions = tbow.actions;
                itemDef.modelOffsetX = tbow.modelOffsetX;
                itemDef.modelOffsetY = tbow.modelOffsetY;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = tbow.rotationY;
                itemDef.rotationX = tbow.rotationX;
                itemDef.stackable = false;
                break;
            case 5011:
                itemDef.name = "@whi@Light Twisted Bow";
                ItemDefinition tbow1 = ItemDefinition.get(1419);
                itemDef.modelID = 65324;
                itemDef.femaleEquip1 = 65325;
                itemDef.maleEquip1 = 65325;
                itemDef.actions = tbow1.actions;
                itemDef.modelOffsetX = tbow1.modelOffsetX;
                itemDef.modelOffsetY = tbow1.modelOffsetY;
                itemDef.modelZoom = tbow1.modelZoom;
                itemDef.rotationY = tbow1.rotationY;
                itemDef.rotationX = tbow1.rotationX;
                itemDef.stackable = false;
                itemDef.rdc2 = 888833;
                break;
            case 5010:
                itemDef.name = "Dan's Limited Bow";
                ItemDefinition tbow3 = ItemDefinition.get(1419);
                itemDef.modelID = 71222;
                itemDef.femaleEquip1 = 71223;
                itemDef.maleEquip1 = 71223;
                itemDef.actions = tbow3.actions;
                itemDef.modelOffsetX = tbow3.modelOffsetX;
                itemDef.modelOffsetY = tbow3.modelOffsetY;
                itemDef.modelZoom = tbow3.modelZoom;
                itemDef.rotationY = tbow3.rotationY;
                itemDef.rotationX = tbow3.rotationX;
                itemDef.stackable = false;
                break;
            case 8136:
                itemDef.name = "Blood Vitur";
                ItemDefinition vitruu = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 65320;
                itemDef.femaleEquip1 = 65321;
                itemDef.maleEquip1 = 65321;
                itemDef.actions = vitruu.actions;

                itemDef.modelOffsetX = vitruu.modelOffsetX;
                //	itemDef.modelOffsetX = vitruu.modelOffsetX;
                itemDef.modelOffsetY = vitruu.modelOffsetY;
                itemDef.modelZoom = 3800;
                itemDef.rotationY = vitruu.rotationY;
                itemDef.rotationX = vitruu.rotationX;
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{54};
                break;
            case 12535:
                itemDef.name = "Execution Vitur";
                ItemDefinition vitur22 = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 65320;
                itemDef.femaleEquip1 = 65321;
                itemDef.maleEquip1 = 65321;
                itemDef.actions = vitur22.actions;

                itemDef.modelOffsetX = vitur22.modelOffsetX;
                //	itemDef.modelOffsetX = vitruu.modelOffsetX;
                itemDef.modelOffsetY = vitur22.modelOffsetY;
                itemDef.modelZoom = 3800;
                itemDef.rotationY = vitur22.rotationY;
                itemDef.rotationX = vitur22.rotationX;
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{82};
                break;

            case 12537:
                itemDef.name = "Light Scythe Of Vitur";
                ItemDefinition vitur222 = ItemDefinition.get(1419);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 65320;
                itemDef.femaleEquip1 = 65321;
                itemDef.maleEquip1 = 65321;
                itemDef.actions = vitur222.actions;

                itemDef.modelOffsetX = vitur222.modelOffsetX;
                //	itemDef.modelOffsetX = vitruu.modelOffsetX;
                itemDef.modelOffsetY = vitur222.modelOffsetY;
                itemDef.modelZoom = 3800;
                itemDef.rotationY = vitur222.rotationY;
                itemDef.rotationX = vitur222.rotationX;
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{76};
                break;
            case 9940:
                itemDef.name = "Execution chainmace";
                ItemDefinition execution = ItemDefinition.get(4755);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = 65322;
                itemDef.femaleEquip1 = 65323;
                itemDef.maleEquip1 = 65323;
                itemDef.actions = execution.actions;

                itemDef.modelOffsetX = execution.modelOffsetX;
                //	itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = execution.modelOffsetY;
                itemDef.modelZoom = execution.modelZoom;
                itemDef.rotationY = execution.rotationY;
                itemDef.rotationX = execution.rotationX;
                itemDef.stackable = false;
                itemDef.rdc2 = 3645768;//23622

                break;
            case 9942:
                itemDef.name = "Execution Staff";

                ItemDefinition execution1 = ItemDefinition.get(21777);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = execution1.modelID;
                itemDef.femaleEquip1 = execution1.femaleEquip1;
                itemDef.maleEquip1 = execution1.maleEquip1;
                itemDef.actions = execution1.actions;

                itemDef.modelOffsetX = execution1.modelOffsetX;
                //	itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = execution1.modelOffsetY;
                itemDef.modelZoom = execution1.modelZoom;
                itemDef.rotationY = execution1.rotationY;
                itemDef.rotationX = execution1.rotationX;
                itemDef.stackable = false;
                itemDef.rdc2 = 85748;//23622

                break;
            case 9939:
                itemDef.copyItem(669);
                itemDef.name = "Execution wings";
                itemDef.modelID = 100270;
                itemDef.maleEquip1 = 100271;
                itemDef.femaleEquip1 = 100271;
                itemDef.modelZoom = 2300;
                itemDef.rotationX = 1050;
                itemDef.rotationY = 525;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 25;
                break;
            case 17011:
                itemDef.name = "Sanguinesti Execution Staff";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.modelID = 99513;
                itemDef.maleEquip1 = 99506;
                itemDef.femaleEquip1 = 99506;
                break;

            case 17013:
                itemDef.name = "@whi@Light Sanguinesti Staff";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.modelID = 99513;
                itemDef.maleEquip1 = 99506;
                itemDef.femaleEquip1 = 99506;
                itemDef.rdc2 = 888833;
                break;
            case 8273:
                itemDef.copyItem(4708);
                itemDef.name = "Execution gloves";
                itemDef.modelID = 100262;
                itemDef.maleEquip1 = 100263;
                itemDef.femaleEquip1 = 100263;
                itemDef.modelZoom = 700;
                break;
            case 8274:
                itemDef.copyItem(4708);
                itemDef.name = "Execution boots";
                itemDef.modelID = 100261;
                itemDef.maleEquip1 = 100261;
                itemDef.femaleEquip1 = 100261;
                itemDef.modelZoom = 700;
                break;
            case 9941:
                itemDef.actions = new String[5];
                itemDef.modelID = 19219;
                itemDef.name = "Execution blowpipe";
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
                itemDef.rdc2 = 666737;//23622
                break;

            case 4684:
                itemDef.copyItem(4708);
                itemDef.name = "Execution helm";
                itemDef.modelID = 100264;
                itemDef.maleEquip1 = 100265;
                itemDef.femaleEquip1 = 100265;
                itemDef.modelZoom = 800;
                break;
            case 4685:
                itemDef.copyItem(4712);
                itemDef.name = "Execution body";
                itemDef.modelID = 100259;
                itemDef.maleEquip1 = 100260;
                itemDef.femaleEquip1 = 100260;
                itemDef.modelZoom = 1500;
                break;
            case 4686:
                itemDef.copyItem(4714);
                itemDef.name = "Execution legs";
                itemDef.modelID = 100266;
                itemDef.maleEquip1 = 100267;
                itemDef.femaleEquip1 = 100267;
                itemDef.modelZoom = 1800;
                break;
        }
        return itemDef;
    }

    public static ItemDefinition itemDef(int id, ItemDefinition itemDef) {
        // TODO Auto-generated method stub
        return null;
    }

}
