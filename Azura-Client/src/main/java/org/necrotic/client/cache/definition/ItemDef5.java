package org.necrotic.client.cache.definition;

public class ItemDef5 {


    public static ItemDefinition newIDS1(ItemDefinition itemDef, int id) {


        ItemDefinition itemdefedit;
        ItemDefinition itemdefyogipic;
        ItemDefinition itemDef21;
        ItemDefinition def;
        switch (id) {

            case 23300:
                itemDef.copyItem(5074);
                itemDef.name = "Turkey Pet";
                itemDef.modelID = MobDefinition.get(8501).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                break;
            case 23301:
                itemDef.copyItem(23300);
                itemDef.name = "Turkey pet (Mutated)";
                itemDef.colorChange = new double[]{0.9, 0, 0};
                break;
            case 23302:
                itemDef.copyItem(23300);
                itemDef.name = "Turkey pet (Mutated)";
                itemDef.colorChange = new double[]{0, 0.8, 0};
                break;
            case 23303:
                itemDef.copyItem(23300);
                itemDef.name = "Turkey pet (Mutated)";
                itemDef.colorChange = new double[]{0, 0, 1.2};
                break;
            case 23304:
                itemDef.copyItem(23300);
                itemDef.name = "Turkey pet (Mutated)";
                itemDef.colorChange = new double[]{0.9, 0.5, 0};
                break;
            case 23305:
                itemDef.copyItem(23300);
                itemDef.name = "Turkey pet (Mutated)";
                itemDef.colorChange = new double[]{0.8, 0, 0.8};
                break;
            case 23306:
                itemDef.copyItem(23300);
                itemDef.name = "Turkey pet (Mutated)";
                itemDef.colorChange = new double[]{0.9, 0.9, 0};
                break;
            case 23307:
                itemDef.copyItem(4621);
                itemDef.name = "Turkey feather";
                itemDef.oldColors = new int[]{
                        7512, 4011, 8150, 8670, 2880, 6084,
                };
                itemDef.newColors = new int[]{
                        7481, 3360, 7481, 7481, 3345, 3345,
                };
                itemDef.actions = new String[]{"Claim", null, null, null, null};
                break;
            case 23308:
                itemDef.copyItem(19114);
                itemDef.name = "Thanksgiving Box";
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{99};
                break;
            case 23309:
                itemDef.copyItem(23300);
                itemDef.name = "Turkey pet (Mutated)";
                itemDef.colorChange = new double[]{3, 3, 4};
                break;
            case 23310:
                itemDef.copyItem(18887);
                itemDef.name = "Thanksgiving Boots";
                itemDef.oldColors = new int[]{60};
                itemDef.newColors = new int[]{99};
                break;
            case 23311:
                itemDef.copyItem(18418);
                itemDef.name = "Thanksgiving Party Hat";
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{99};
                break;
            case 23312:
                itemDef.copyItem(19810);
                itemDef.name = "Thanksgiving Spirit Shield";
                itemDef.oldColors = new int[]{62};
                itemDef.newColors = new int[]{99};
                break;
            case 17580:
                itemDef.name = "Turkey Pet Mutation";
                itemDef.rdc2 = 65155;
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 23313:
                itemDef.copyItem(6585);
                itemDef.name = "Turkey Amulet";
                itemDef.modelID = 139926;
                itemDef.maleEquip1 = 139927;
                itemDef.femaleEquip1 = 139927;
                itemDef.maleWieldY = 8;
                break;


            case 23314:
                itemDef.copyItem(15004);
                itemDef.name = "<col=ff7000>Zenyte Casket</col>";
                itemDef.modelID = 139753;
                break;


            case 23315:
                itemDef.copyItem(11216);
                itemDef.name = "Basic arrow (1)";
                itemDef.colorChange = new double[]{1.2, 0.1, 0.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23316:
                itemDef.copyItem(559);
                itemDef.name = "Basic rune (1)";
                itemDef.colorChange = new double[]{1.2, 0.1, 0.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23317:
                itemDef.copyItem(7636);
                itemDef.name = "Basic dust (1)";
                itemDef.colorChange = new double[]{1, 0.1, 0.1};
                itemDef.stackable = true;
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;

            case 23318:
                itemDef.copyItem(23315);
                itemDef.name = "Basic arrow (2)";
                itemDef.colorChange = new double[]{0.9, 0.1, 0.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23319:
                itemDef.copyItem(23316);
                itemDef.name = "Basic rune (2)";
                itemDef.colorChange = new double[]{0.9, 0.1, 0.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23320:
                itemDef.copyItem(23317);
                itemDef.name = "Basic dust (2)";
                itemDef.colorChange = new double[]{0.7, 0.1, 0.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;

            case 23321:
                itemDef.copyItem(23315);
                itemDef.name = "Basic arrow (3)";
                itemDef.colorChange = new double[]{0.6, 0.1, 0.1};
                itemDef.actions[2] = null;
                break;
            case 23322:
                itemDef.copyItem(23316);
                itemDef.name = "Basic rune (3)";
                itemDef.colorChange = new double[]{0.6, 0.1, 0.1};
                itemDef.actions[2] = null;
                break;
            case 23323:
                itemDef.copyItem(23317);
                itemDef.name = "Basic dust (3)";
                itemDef.colorChange = new double[]{0.4, 0.1, 0.1};
                itemDef.actions[2] = null;
                break;


            case 23324:
                itemDef.copyItem(23315);
                itemDef.name = "Death arrow (1)";
                itemDef.rdc = 45;
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23325:
                itemDef.copyItem(23316);
                itemDef.name = "Death rune (1)";
                itemDef.rdc = 45;
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23326:
                itemDef.copyItem(23317);
                itemDef.name = "Death dust (1)";
                itemDef.rdc = 45;
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;

            case 23327:
                itemDef.copyItem(23315);
                itemDef.name = "Death arrow (2)";
                itemDef.rdc = 30;
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23328:
                itemDef.copyItem(23316);
                itemDef.name = "Death rune (2)";
                itemDef.rdc = 30;
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23329:
                itemDef.copyItem(23317);
                itemDef.name = "Death dust (2)";
                itemDef.rdc = 30;
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;

            case 23330:
                itemDef.copyItem(23315);
                itemDef.name = "Death arrow (3)";
                itemDef.rdc = 15;
                itemDef.actions[2] = null;
                break;
            case 23331:
                itemDef.copyItem(23316);
                itemDef.name = "Death rune (3)";
                itemDef.rdc = 15;
                itemDef.actions[2] = null;
                break;
            case 23332:
                itemDef.copyItem(23317);
                itemDef.name = "Death dust (3)";
                itemDef.rdc = 15;
                itemDef.actions[2] = null;
                break;

            case 23333:
                itemDef.copyItem(23315);
                itemDef.name = "Healing arrow (1)";
                itemDef.colorChange = new double[]{0, 0, 4};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23334:
                itemDef.copyItem(23316);
                itemDef.name = "Healing rune (1)";
                itemDef.colorChange = new double[]{0.1, 0.1, 1.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23335:
                itemDef.copyItem(23317);
                itemDef.name = "Healing dust (1)";
                itemDef.colorChange = new double[]{0.1, 0.1, 1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;

            case 23336:
                itemDef.copyItem(23315);
                itemDef.name = "Healing arrow (2)";
                itemDef.colorChange = new double[]{0, 0, 2};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23337:
                itemDef.copyItem(23316);
                itemDef.name = "Healing rune (2)";
                itemDef.colorChange = new double[]{0.1, 0.1, 0.9};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23338:
                itemDef.copyItem(23317);
                itemDef.name = "Healing dust (2)";
                itemDef.colorChange = new double[]{0.1, 0.1, 0.8};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;

            case 23339:
                itemDef.copyItem(23315);
                itemDef.name = "Healing arrow (3)";
                itemDef.colorChange = new double[]{0, 0, 1};
                itemDef.actions[2] = null;
                break;
            case 23340:
                itemDef.copyItem(23316);
                itemDef.name = "Healing rune (3)";
                itemDef.colorChange = new double[]{0.1, 0.1, 0.5};
                itemDef.actions[2] = null;
                break;
            case 23341:
                itemDef.copyItem(23317);
                itemDef.name = "Healing dust (3)";
                itemDef.colorChange = new double[]{0.1, 0.1, 0.4};
                itemDef.actions[2] = null;
                break;


            case 23342:
                itemDef.copyItem(23315);
                itemDef.name = "AOE arrow (1)";
                itemDef.colorChange = new double[]{0, 5, 0};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23343:
                itemDef.copyItem(23316);
                itemDef.name = "AOE rune (1)";
                itemDef.colorChange = new double[]{0.1, 1.1, 0.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23344:
                itemDef.copyItem(23317);
                itemDef.name = "AOE dust (1)";
                itemDef.colorChange = new double[]{0.1, 1, 0.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;

            case 23345:
                itemDef.copyItem(23315);
                itemDef.name = "AOE arrow (2)";
                itemDef.colorChange = new double[]{0, 3, 0};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23346:
                itemDef.copyItem(23316);
                itemDef.name = "AOE rune (2)";
                itemDef.colorChange = new double[]{0.1, 0.7, 0.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;
            case 23347:
                itemDef.copyItem(23317);
                itemDef.name = "AOE dust (2)";
                itemDef.colorChange = new double[]{0.1, 0.7, 0.1};
                itemDef.actions = new String[]{null, "Wield", "Upgrade", null, null};
                break;

            case 23348:
                itemDef.copyItem(23315);
                itemDef.name = "AOE arrow (3)";
                itemDef.colorChange = new double[]{0, 0.5, 0};
                itemDef.actions[2] = null;
                break;
            case 23349:
                itemDef.copyItem(23316);
                itemDef.name = "AOE rune (3)";
                itemDef.colorChange = new double[]{0.1, 0.4, 0.1};
                itemDef.actions[2] = null;
                break;
            case 23350:
                itemDef.copyItem(23317);
                itemDef.name = "AOE dust (3)";
                itemDef.colorChange = new double[]{0.1, 0.4, 0.1};
                itemDef.actions[2] = null;
                break;


            case 23351:
                itemDef.copyItem(6585);
                itemDef.name = "Owner Amulet";
                itemDef.modelID = 62001;
                itemDef.maleEquip1 = 62000;
                itemDef.femaleEquip1 = 62000;
                itemDef.maleWieldY = 3;
                itemDef.maleWieldZ = 3;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23352:
                itemDef.copyItem(6737);
                itemDef.name = "Owner Ring";
                itemDef.modelID = 62002;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23353:
                itemDef.copyItem(6737);
                itemDef.name = "Owner Bracelet";
                itemDef.modelID = 62003;
                itemDef.maleEquip1 = 62004;
                itemDef.femaleEquip1 = 62004;
                itemDef.maleModelScale = 4;
                itemDef.maleWieldY = 4;
                itemDef.maleWieldZ = -1;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;

            case 23354:
                itemDef.copyItem(18334);
                itemDef.name = "Infernal Amulet";
                break;

            case 23355:
                itemDef.copyItem(6737);
                itemDef.name = "Owner minigun";
                itemDef.modelID = 139929;
                itemDef.maleEquip1 = 139930;
                itemDef.femaleEquip1 = 139930;
                break;


            case 23356:
                itemDef.copyItem(6737);
                itemDef.name = "Lunite helm";
                itemDef.modelID = 139931;
                itemDef.maleEquip1 = 139932;
                itemDef.femaleEquip1 = 139932;
                itemDef.actions = new String[]{null, "Wear", null, null, null};

                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{3917};
                break;
            case 23357:
                itemDef.copyItem(6737);
                itemDef.name = "Lunite body";
                itemDef.modelID = 139933;
                itemDef.maleEquip1 = 139934;
                itemDef.femaleEquip1 = 139934;
                itemDef.actions = new String[]{null, "Wear", null, null, null};

                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{3917};
                break;
            case 23358:
                itemDef.copyItem(6737);
                itemDef.name = "Lunite legs";
                itemDef.modelID = 139935;
                itemDef.maleEquip1 = 139936;
                itemDef.femaleEquip1 = 139936;
                itemDef.actions = new String[]{null, "Wear", null, null, null};

                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{3917};
                break;
            case 23359:
                itemDef.copyItem(6737);
                itemDef.name = "Lunite gloves";
                itemDef.modelID = 139937;
                itemDef.maleEquip1 = 139938;
                itemDef.femaleEquip1 = 139938;
                itemDef.actions = new String[]{null, "Wear", null, null, null};

                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{3917};
                break;
            case 23360:
                itemDef.copyItem(6737);
                itemDef.name = "Lunite boots";
                itemDef.modelID = 139939;
                itemDef.maleEquip1 = 139939;
                itemDef.femaleEquip1 = 139939;
                itemDef.actions = new String[]{null, "Wear", null, null, null};

                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{3917};
                break;
            case 23361:
                itemDef.copyItem(6737);
                itemDef.name = "Lunite shield";
                itemDef.modelID = 139940;
                itemDef.maleEquip1 = 139941;
                itemDef.femaleEquip1 = 139941;
                itemDef.actions = new String[]{null, "Wear", null, null, null};

                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{3917};
                break;

            case 23362:
                itemDef.copyItem(6737);
                itemDef.name = "Elf hat";
                itemDef.modelID = 139942;
                itemDef.maleEquip1 = 139943;
                itemDef.femaleEquip1 = 139943;
                itemDef.maleWieldY = 5;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23363:
                itemDef.copyItem(6737);
                itemDef.name = "Elf body";
                itemDef.modelID = 139944;
                itemDef.maleEquip1 = 139945;
                itemDef.femaleEquip1 = 139945;
                itemDef.maleWieldY = 5;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23364:
                itemDef.copyItem(6737);
                itemDef.name = "Elf legs";
                itemDef.modelID = 139946;
                itemDef.maleEquip1 = 139947;
                itemDef.femaleEquip1 = 139947;
                itemDef.maleWieldY = 5;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23365:
                itemDef.copyItem(6737);
                itemDef.name = "Elf gloves";
                itemDef.modelID = 139948;
                itemDef.maleEquip1 = 139949;
                itemDef.femaleEquip1 = 139949;
                itemDef.maleWieldY = 5;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23366:
                itemDef.copyItem(6737);
                itemDef.name = "Elf boots";
                itemDef.modelID = 139950;
                itemDef.maleEquip1 = 139950;
                itemDef.femaleEquip1 = 139950;
                itemDef.maleWieldY = 5;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 23367:
                itemDef.copyItem(6737);
                itemDef.name = "Elf hat";
                itemDef.modelID = 139951;
                itemDef.maleEquip1 = 139952;
                itemDef.femaleEquip1 = 139952;
                itemDef.maleWieldY = 5;
                break;
            case 23368:
                itemDef.copyItem(6737);
                itemDef.name = "Elf body";
                itemDef.modelID = 139953;
                itemDef.maleEquip1 = 139954;
                itemDef.femaleEquip1 = 139954;
                itemDef.maleWieldY = 5;
                break;
            case 23369:
                itemDef.copyItem(6737);
                itemDef.name = "Elf legs";
                itemDef.modelID = 139955;
                itemDef.maleEquip1 = 139956;
                itemDef.femaleEquip1 = 139956;
                itemDef.maleWieldY = 5;
                break;
            case 23370:
                itemDef.copyItem(6737);
                itemDef.name = "Elf gloves";
                itemDef.modelID = 139957;
                itemDef.maleEquip1 = 139958;
                itemDef.femaleEquip1 = 139958;
                break;
            case 23371:
                itemDef.copyItem(6737);
                itemDef.name = "Elf boots";
                itemDef.modelID = 139961;
                itemDef.maleEquip1 = 139961;
                itemDef.femaleEquip1 = 139961;
                itemDef.maleWieldY = 5;
                break;
            case 23372:
                itemDef.copyItem(6737);
                itemDef.name = "Elf cape";
                itemDef.modelID = 139959;
                itemDef.maleEquip1 = 139960;
                itemDef.femaleEquip1 = 139960;
                itemDef.maleWieldY = 5;
                break;


            case 23373:
                itemDef.copyItem(17011);
                itemDef.modelID = 39072;
                itemDef.maleEquip1 = 39055;
                itemDef.femaleEquip1 = 39055;
                itemDef.maleWieldY = 5;

                itemDef.name = "Weed's blunt";

                itemDef.modelZoom = 2064;
                itemDef.rotationX = 1050;
                itemDef.rotationY = 404;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 13;

                itemDef.colorChange = new double[]{0.4, 1.2, 0.4};
                break;

            case 23374:
                itemDef.copyItem(15328);
                itemDef.name = "Owner Potion";
                break;

            case 23375:
                itemDef.copyItem(14701);
                itemDef.name = "Auto-dissembler";
                itemDef.rdc2 = 24567;
                itemDef.actions = new String[]{"Check", null, null, null, null};
                break;


            case 23376:
                itemDef.copyItem(19114);
                itemDef.name = "Christmas Box";
                itemDef.oldColors = new int[]{63, 7, 11};
                itemDef.newColors = new int[]{101, 22417, 22417};
                itemDef.modelZoom = 1180;
                itemDef.rotationX = 67;
                itemDef.rotationY = 152;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -14;
                break;


            case 23377:
                itemDef.copyItem(1050);
                itemDef.name = "Evil Santa Hat";

                itemDef.oldColors = new int[]{933};
                itemDef.newColors = new int[]{8};
                break;
            case 23378:
                itemDef.copyItem(14595);
                itemDef.name = "Evil Santa Top";
                itemDef.oldColors = new int[]{924, 933, 928, 912, 940};
                itemDef.newColors = new int[]{4, 9, 8, 0, 13};

                itemDef.maleEquip2 = ItemDefinition.get(14595).maleEquip2;
                itemDef.femaleEquip2 = ItemDefinition.get(14595).femaleEquip2;
                break;
            case 23379:
                itemDef.copyItem(14603);
                itemDef.name = "Evil Santa Legs";
                itemDef.oldColors = new int[]{933, 940, 928, 924, 912};
                itemDef.newColors = new int[]{9, 13, 8, 4, 0};
                break;
            case 23380:
                itemDef.copyItem(14602);
                itemDef.name = "Evil Santa Gloves";
                itemDef.oldColors = new int[]{56340, 56346};
                itemDef.newColors = new int[]{4, 10};
                break;
            case 23381:
                itemDef.copyItem(14605);
                itemDef.name = "Evil Santa Boots";
                itemDef.oldColors = new int[]{56334, 56338, 56342};
                itemDef.newColors = new int[]{4, 8, 10};
                break;


            case 23382:
                itemDef.copyItem(5074);
                itemDef.name = "Snow Ranger Pet";
                itemDef.modelID = MobDefinition.get(14207).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                break;
            case 23383:
                itemDef.copyItem(5074);
                itemDef.name = "Snow Warrior Pet";
                itemDef.modelID = MobDefinition.get(14208).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                break;
            case 23384:
                itemDef.copyItem(5074);
                itemDef.name = "Snow Mage Pet";
                itemDef.modelID = MobDefinition.get(14209).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                break;
            case 23385:
                itemDef.copyItem(5074);
                itemDef.name = "Santa Pet";
                itemDef.modelID = MobDefinition.get(9400).npcModels[0];
                break;
            case 23386:
                itemDef.copyItem(5074);
                itemDef.name = "Evil Santa Pet";
                itemDef.modelID = MobDefinition.get(9400).npcModels[0];
                itemDef.oldColors = new int[]{666, 655, 671, 675, 670, 673, 680, 660};
                itemDef.newColors = new int[]{4, 8, 12, 0, 11, 7, 16, 10};
                break;


            case 23387:
                itemDef.copyItem(23377);
                itemDef.name = "Evil Santa Attachment";
                itemDef.oldColors = new int[]{933};
                itemDef.newColors = new int[]{8};
                break;
            case 23388:
                itemDef.copyItem(23387);
                itemDef.note = 23389;
                itemDef.noteTemplate = 23387;
                itemDef.name = "Evil Santa Attachment";
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 23389:
                itemDef.copyItem(19864);
                itemDef.modelZoom = 530;
                itemDef.rdc = 90;
                break;


            case 23390:
                itemDef.copyItem(3062);
                itemDef.stackable = false;
                itemDef.name = "Costume Box";
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 23391:
                itemDef.copyItem(23363);
                itemDef.noteTemplate = 23363;
                itemDef.note = 23390;
                itemDef.name = "Elf Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.stackable = false;
                break;
            case 23392:
                itemDef.copyItem(13027);
                itemDef.noteTemplate = 13027;
                itemDef.note = 23390;
                itemDef.name = "Grinch Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.stackable = false;
                break;
            case 23393:
                itemDef.copyItem(23378);
                itemDef.noteTemplate = 23378;
                itemDef.note = 23390;
                itemDef.name = "Evil Santa Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.stackable = false;
                break;
            case 10501:
                itemDef.actions = new String[]{"Claim", null, null, null, null};
                break;


            case 23394:
                itemDef.copyItem(23356);
                itemDef.name = "Lunite helm";

                itemDef.actions[2] = "Disassemble";
                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{32575};
                break;
            case 23395:
                itemDef.copyItem(23357);
                itemDef.name = "Lunite body";

                itemDef.actions[2] = "Disassemble";
                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{32575};
                break;
            case 23396:
                itemDef.copyItem(23358);
                itemDef.name = "Lunite legs";

                itemDef.actions[2] = "Disassemble";
                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{32575};//32716
                break;
            case 23397:
                itemDef.copyItem(23359);
                itemDef.name = "Lunite gloves";
                itemDef.actions[2] = "Disassemble";

                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{32575};
                break;
            case 23398:
                itemDef.copyItem(23360);
                itemDef.name = "Lunite boots";

                itemDef.actions[2] = "Disassemble";
                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{32575};
                break;
            case 23399:
                itemDef.copyItem(23361);
                itemDef.name = "Lunite shield";
                itemDef.actions[2] = "Disassemble";

                itemDef.oldColors = new int[]{4025};
                itemDef.newColors = new int[]{32575};
                break;

            case 23400:
                itemDef.copyItem(20592);
                itemDef.name = "Dan's Mask Add-on";
                break;
            case 23401:
                itemDef.copyItem(23400);
                itemDef.note = 23402;
                itemDef.noteTemplate = 23400;
                itemDef.name = "Dan's Mask Add-on";
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 23402:
                itemDef.copyItem(19864);
                itemDef.modelZoom = 530;
                itemDef.rdc = 800;
                break;
            case 23403:
                itemDef.copyItem(20592);
                itemDef.name = "Dan's Mask (u)";
                break;


            case 23450:
                itemDef.copyItem(23385);
                itemDef.name = "Santa Transformer";
                break;
            case 23451:
                itemDef.copyItem(23386);
                itemDef.name = "Evil Santa Transformer";
                itemDef.oldColors = new int[]{666, 655, 671, 675, 670, 673, 680, 660};
                itemDef.newColors = new int[]{4, 8, 12, 0, 11, 7, 16, 10};
                break;
            case 23452:
                itemDef.copyItem(7478);
                itemDef.modelZoom = 432;
                itemDef.rotationX = 254;
                itemDef.rotationY = 415;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 3;
                break;
            case 23453:
                itemDef.copyItem(23450);
                itemDef.note = 23452;
                itemDef.noteTemplate = 23450;
                itemDef.name = "Santa Transformer";
                itemDef.actions = new String[]{"Transform", null, null, null, null};
                break;
            case 23454:
                itemDef.copyItem(23451);
                itemDef.note = 23452;
                itemDef.noteTemplate = 23451;
                itemDef.name = "Evil Santa Transformer";
                itemDef.actions = new String[]{"Transform", null, null, null, null};
                itemDef.oldColors = new int[]{666, 655, 671, 675, 670, 673, 680, 660};
                itemDef.newColors = new int[]{4, 8, 12, 0, 11, 7, 16, 10};
                break;


            case 23455:
                itemDef.copyItem(17672);
                itemDef.name = "Doom Bow";
                itemDef.oldColors = new int[]{52363, 50980, 49423, 52370, 50979, 50968, 49417, 50995,
                        22, 26, 16};//33332, 31292, 31289, 36529, 34482, 32429,
                itemDef.newColors = new int[]{11062, 11062, 11062, 11062, 11062, 11062, 11062, 11062,
                        40, 50, 45};
                break;


            case 23456:
                itemDef.copyItem(19122);
                itemDef.name = "<col=2986cc>High-Tier Ticket</col>";
                itemDef.rdc2 = 34561;
                itemDef.actions = new String[]{null, null, null, null, null};
                break;


            case 23457:
                itemDef.copyItem(23357);
                itemDef.name = "Lunite Costume";
                break;
            case 23458:
                itemDef.copyItem(23457);
                itemDef.noteTemplate = 23457;
                itemDef.note = 23390;
                itemDef.name = "Lunite Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.stackable = false;
                break;


            case 23459:
                itemDef.copyItem(20592);
                itemDef.name = "Divine's Mask";
                itemDef.modelID = 139962;
                itemDef.maleEquip1 = 139962;
                itemDef.femaleEquip1 = 139962;
                itemDef.maleWieldY = 7;
                itemDef.maleWieldZ = -2;
                break;

            case 23460:
                itemDef.copyItem(451);
                itemDef.name = "Patronum Ore";
                itemDef.oldColors = new int[]{7062};
                itemDef.newColors = new int[]{54169};
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 23461:
                itemDef.copyItem(2363);
                itemDef.name = "Patronum Bar";
                itemDef.oldColors = new int[]{7062};
                itemDef.newColors = new int[]{54169};
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 23462:
                itemDef.copyItem(16383);
                itemDef.name = "Patronum Blade";
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                break;


            case 23463:
                itemDef.copyItem(13379);
                itemDef.name = "Sanctum Key Shards";
                itemDef.actions = new String[]{"Combine", null, null, null, null};
                itemDef.colorChange = new double[]{1.5, 0.1, 0.1};
                break;
            case 23464:
                itemDef.copyItem(18665);
                itemDef.name = "Sanctum Key";
                itemDef.colorChange = new double[]{0.6, 0.1, 0.1};
                itemDef.actions = new String[]{"Teleport", null, null, null, null};
                break;


            case 23465:
                itemDef.copyItem(23356);
                itemDef.name = "Mystic helm";
                itemDef.modelID = 139964;
                itemDef.maleEquip1 = 139965;
                itemDef.femaleEquip1 = 139965;
                itemDef.colorChange = new double[]{1, 1, 1.2};
                break;
            case 23466:
                itemDef.copyItem(23357);
                itemDef.name = "Mystic body";
                itemDef.modelID = 139966;
                itemDef.maleEquip1 = 139967;
                itemDef.femaleEquip1 = 139967;
                itemDef.colorChange = new double[]{1, 1, 1.2};
                break;
            case 23467:
                itemDef.copyItem(23358);
                itemDef.name = "Mystic legs";
                itemDef.modelID = 139968;
                itemDef.maleEquip1 = 139969;
                itemDef.femaleEquip1 = 139969;
                itemDef.colorChange = new double[]{1, 1, 1.2};
                break;

            case 23468:
                itemDef.copyItem(23356);
                itemDef.name = "Hellish helm";
                itemDef.modelID = 139970;
                itemDef.maleEquip1 = 139971;
                itemDef.femaleEquip1 = 139971;
                itemDef.colorChange = new double[]{1, 1, 1.2};
                break;
            case 23469:
                itemDef.copyItem(23357);
                itemDef.name = "Hellish body";
                itemDef.modelID = 139972;
                itemDef.maleEquip1 = 139973;
                itemDef.femaleEquip1 = 139973;
                itemDef.colorChange = new double[]{1, 1, 1.2};
                break;
            case 23470:
                itemDef.copyItem(23358);
                itemDef.name = "Hellish legs";
                itemDef.modelID = 139974;
                itemDef.maleEquip1 = 139975;
                itemDef.femaleEquip1 = 139975;
                itemDef.colorChange = new double[]{1, 1, 1.2};
                break;

            case 23471:
                itemDef.copyItem(23356);
                itemDef.name = "Midnight helm";
                itemDef.modelID = 139976;
                itemDef.maleEquip1 = 139977;
                itemDef.femaleEquip1 = 139977;
                itemDef.colorChange = new double[]{1, 1, 1.2};
                break;
            case 23472:
                itemDef.copyItem(23357);
                itemDef.name = "Midnight body";
                itemDef.modelID = 139978;
                itemDef.maleEquip1 = 139979;
                itemDef.femaleEquip1 = 139979;
                itemDef.colorChange = new double[]{1, 1, 1.2};
                break;
            case 23473:
                itemDef.copyItem(23358);
                itemDef.name = "Midnight legs";
                itemDef.modelID = 139980;
                itemDef.maleEquip1 = 139981;
                itemDef.femaleEquip1 = 139981;
                itemDef.colorChange = new double[]{1, 1, 1.2};
                break;


            case 23474:
                itemDef.copyItem(23222);
                itemDef.name = "Booboo's bow";
                itemDef.modelID = 139983;
                itemDef.maleEquip1 = 139984;
                itemDef.femaleEquip1 = 139984;
                itemDef.colorChange = new double[]{.8, .1, .8};
                break;

            case 23475:
                itemDef.copyItem(6199);
                itemDef.name = "Bronze Card Pack";
                itemDef.modelID = 139985;
                itemDef.modelZoom = 1013;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.oldColors = new int[]{36786, 28594};
                itemDef.newColors = new int[]{32716, 32716};
                break;
            case 23476:
                itemDef.copyItem(23475);
                itemDef.name = "Silver Card Pack";
                itemDef.modelID = 139985;
                itemDef.oldColors = new int[]{36786, 28594, 4521, 6066};
                itemDef.newColors = new int[]{32716, 32716, 50, 69};
                break;
            case 23477:
                itemDef.copyItem(23475);
                itemDef.name = "Gold Card Pack";
                itemDef.modelID = 139985;
                itemDef.oldColors = new int[]{36786, 28594, 4521, 6066};
                itemDef.newColors = new int[]{32716, 32716, 11186, 10945};
                break;
            case 23478:
                itemDef.copyItem(23475);
                itemDef.name = "Diamond Card Pack";
                itemDef.modelID = 139985;
                itemDef.oldColors = new int[]{36786, 28594, 4521, 6066};
                itemDef.newColors = new int[]{32716, 32716, 32716, 32741};
                break;


            case 23479:
                itemDef.copyItem(7120);
                itemDef.name = "Slayer Box (u)";
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{71};
                break;
            case 23480:
                itemDef.copyItem(7120);
                itemDef.name = "Pvm Box (t2)";
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{63};
                break;
            case 779:
                itemDef.name = "Crest part 1";
                itemDef.rdc3 = 5;
                break;
            case 780:
                itemDef.name = "Crest part 2";
                itemDef.rdc3 = 5;
                break;
            case 781:
                itemDef.name = "Crest part 3";
                itemDef.rdc3 = 5;
                break;
            case 782:
                itemDef.name = "Empty Crest";
                itemDef.rdc3 = 5;
                break;
            case 23481:
                itemDef.copyItem(15708);
                itemDef.name = "Learn Crest Creation";
                break;
            case 23482:
                itemDef.copyItem(1);
                itemDef.name = "Melee Crest (Light)";
                itemDef.modelID = 139986;
                itemDef.modelZoom = 1221;
                itemDef.rotationX = 2026;
                itemDef.rotationY = 477;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 6;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.oldColors = new int[]{0, 19, 6, 38, 31, 27, 23, 20};
                itemDef.newColors = new int[]{0, 19 + 80, 76, 38 + 80, 31 + 80, 27 + 80, 23 + 80, 20 + 80};
                itemDef.actions[2] = "Disassemble";
                break;
            case 23483:
                itemDef.copyItem(23482);
                itemDef.name = "Magic Crest (Light)";
                itemDef.modelID = 139987;
                itemDef.oldColors = new int[]{0, 19, 6, 38, 31, 27, 23, 20};
                itemDef.newColors = new int[]{0, 19 + 80, 76, 38 + 80, 31 + 80, 27 + 80, 23 + 80, 20 + 80};
                itemDef.actions[2] = "Disassemble";
                break;
            case 23484:
                itemDef.copyItem(23482);
                itemDef.name = "Ranged Crest (Light)";
                itemDef.modelID = 139988;
                itemDef.oldColors = new int[]{0, 19, 6, 38, 31, 27, 23, 20};
                itemDef.newColors = new int[]{0, 19 + 80, 76, 38 + 80, 31 + 80, 27 + 80, 23 + 80, 20 + 80};
                itemDef.actions[2] = "Disassemble";
                break;


            case 23485:
                itemDef.copyItem(23482);
                itemDef.name = "Melee Crest (Dark)";
                itemDef.modelID = 139986;
                itemDef.actions[2] = "Disassemble";
                break;
            case 23486:
                itemDef.copyItem(23482);
                itemDef.name = "Magic Crest (Dark)";
                itemDef.modelID = 139987;
                itemDef.actions[2] = "Disassemble";
                break;
            case 23487:
                itemDef.copyItem(23482);
                itemDef.name = "Ranged Crest (Dark)";
                itemDef.modelID = 139988;
                itemDef.actions[2] = "Disassemble";
                break;


            case 23488:
                itemDef.copyItem(23482);
                itemDef.name = "Melee Crest (Blood)";
                itemDef.modelID = 139986;
                itemDef.oldColors = new int[]{0, 19, 6, 38, 31, 27, 23, 20};
                itemDef.newColors = new int[]{0, 19 + 900, 792, 38 + 900, 31 + 900, 27 + 900, 23 + 900, 20 + 900};
                itemDef.actions[2] = "Disassemble";
                break;
            case 23489:
                itemDef.copyItem(23482);
                itemDef.name = "Magic Crest (Blood)";
                itemDef.modelID = 139987;
                itemDef.oldColors = new int[]{0, 19, 6, 38, 31, 27, 23, 20};
                itemDef.newColors = new int[]{0, 19 + 900, 792, 38 + 900, 31 + 900, 27 + 900, 23 + 900, 20 + 900};
                itemDef.actions[2] = "Disassemble";
                break;
            case 23490:
                itemDef.copyItem(23482);
                itemDef.name = "Ranged Crest (Blood)";
                itemDef.modelID = 139988;
                itemDef.oldColors = new int[]{0, 19, 6, 38, 31, 27, 23, 20};
                itemDef.newColors = new int[]{0, 19 + 900, 792, 38 + 900, 31 + 900, 27 + 900, 23 + 900, 20 + 900};
                itemDef.actions[2] = "Disassemble";
                break;

            case 23491:
                itemDef.copyItem(23482);
                itemDef.name = "Heart of the Warrior";
                itemDef.modelID = 139989;
                itemDef.modelZoom = 1168;
                itemDef.rotationX = 1690;
                itemDef.rotationY = 96;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -1;
                itemDef.colorChange = new double[]{0.9, 0.2, 0.2};
                itemDef.actions = new String[]{"Invigorate", null, null, null, null};
                break;
            case 23492:
                itemDef.copyItem(23491);
                itemDef.name = "Heart of the Ranger";
                itemDef.colorChange = new double[]{0.2, 0.9, 0.2};
                break;
            case 23493:
                itemDef.copyItem(23491);
                itemDef.name = "Heart of the Sorcerer";
                itemDef.colorChange = new double[]{0.2, 0.2, 0.9};
                break;

            case 23494:
                itemDef.copyItem(23491);
                itemDef.name = "Heart of the Gods";
                itemDef.colorChange = new double[]{2, 3.5, 1};
                break;

            case 23497:
                itemDef.copyItem(6737);
                itemDef.name = "Grandmaster hood";
                itemDef.modelID = 96496;
                itemDef.maleEquip1 = 96497;
                itemDef.femaleEquip1 = 96497;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23498:
                itemDef.copyItem(6737);
                itemDef.name = "Grandmaster body";
                itemDef.modelID = 96498;
                itemDef.maleEquip1 = 96499;
                itemDef.femaleEquip1 = 96499;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23499:
                itemDef.copyItem(6737);
                itemDef.name = "Grandmaster legs";
                itemDef.modelID = 96500;
                itemDef.maleEquip1 = 96501;
                itemDef.femaleEquip1 = 96501;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;

            case 23501:
                itemDef.copyItem(6737);
                itemDef.name = "Reaper helm";
                itemDef.modelID = 99971;
                itemDef.maleEquip1 = 99972;
                itemDef.femaleEquip1 = 99972;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23502:
                itemDef.copyItem(6737);
                itemDef.name = "Reaper body";
                itemDef.modelID = 99973;
                itemDef.maleEquip1 = 99974;
                itemDef.femaleEquip1 = 99974;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23503:
                itemDef.copyItem(6737);
                itemDef.name = "Reaper legs";
                itemDef.modelID = 99975;
                itemDef.maleEquip1 = 99976;
                itemDef.femaleEquip1 = 99976;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23504:
                itemDef.copyItem(6737);
                itemDef.name = "Reaper gloves";
                itemDef.modelID = 99977;
                itemDef.maleEquip1 = 99978;
                itemDef.femaleEquip1 = 99978;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23505:
                itemDef.copyItem(6737);
                itemDef.name = "Reaper boots";
                itemDef.modelID = 99979;
                itemDef.maleEquip1 = 99979;
                itemDef.femaleEquip1 = 99979;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 23506:
                itemDef.copyItem(23502);
                itemDef.name = "Reaper Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23507:
                itemDef.copyItem(23506);
                itemDef.noteTemplate = 23506;
                itemDef.note = 23390;
                itemDef.name = "Reaper Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 23508:
                itemDef.copyItem(23498);
                itemDef.name = "Grandmaster Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23509:
                itemDef.copyItem(23508);
                itemDef.noteTemplate = 23508;
                itemDef.note = 23390;
                itemDef.name = "Grandmaster Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 23510:
                itemDef.copyItem(23459);
                itemDef.name = "Divine's Mask Add-on";
                break;
            case 23511:
                itemDef.copyItem(23510);
                itemDef.note = 23402;
                itemDef.noteTemplate = 23510;
                itemDef.name = "Divine's Mask Add-on";
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 23512:
                itemDef.copyItem(23459);
                itemDef.name = "Divine's Mask (u)";
                itemDef.maleWieldY = 7;
                itemDef.maleWieldZ = -2;
                break;
            case 23513:
                itemDef.copyItem(22111);
                itemDef.name = "Rat's Aura";
                itemDef.modelID = 139990;
                itemDef.maleEquip1 = 139990;
                itemDef.femaleEquip1 = 139990;
                itemDef.modelZoom = 3650;
                itemDef.rotationX = 169;
                itemDef.rotationY = 161;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                break;

            case 23514:
                itemDef.copyItem(15289);
                itemDef.name = "Pvm ticket pack (1k)";
                itemDef.rdc2 = 123434;
                itemDef.actions[3] = "Convert";
                break;
            case 23515:
                itemDef.copyItem(15289);
                itemDef.name = "Pvm ticket pack (5k)";
                itemDef.colorChange = new double[]{1, 0.3, 0.3};
                itemDef.actions[3] = "Convert";
                break;
            case 23516:
                itemDef.copyItem(15289);
                itemDef.name = "Pvm ticket pack (10k)";
                itemDef.colorChange = new double[]{0.6, 0.1, 0.1};
                break;


            case 23517:
                itemDef.copyItem(4082);
                itemDef.name = "@bla@Dark shard</col>";
                itemDef.rdc = 20;
                itemDef.modelOffsetY = -2;
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                
                break;
            case 23518:
                itemDef.copyItem(4082);
                itemDef.name = "<col=9f0808>Blood shard</col>";
                itemDef.rdc = 946;
                itemDef.modelOffsetY = -2;
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                
                break;


            case 23519:
                itemDef.copyItem(8101);
                itemDef.name = "Supreme Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23520:
                itemDef.copyItem(23519);
                itemDef.noteTemplate = 23519;
                itemDef.note = 23390;
                itemDef.name = "Supreme Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23521:
                itemDef.copyItem(8106);
                itemDef.name = "Dark Supreme Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23522:
                itemDef.copyItem(23521);
                itemDef.noteTemplate = 23521;
                itemDef.note = 23390;
                itemDef.name = "Dark Supreme Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;

            case 23523:
                itemDef.copyItem(21780);
                itemDef.name = "Prysm gloves";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{55};
                itemDef.modelZoom = 639;
                itemDef.rotationX = 1737;
                itemDef.rotationY = 347;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = 4;
                break;
            case 23524:
                itemDef.copyItem(19945);
                itemDef.name = "Prysm boots";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{55};
                itemDef.maleModelScale = 0.85;
                itemDef.modelZoom = 702;
                itemDef.rotationX = 245;
                itemDef.rotationY = 152;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 6;
                itemDef.modelOffsetY = -16;
                break;

            case 23525:
                itemDef.copyItem(23465);
                itemDef.name = "Mystic helm (u)";
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.modelID = 100471;
                itemDef.maleEquip1 = 100296;
                itemDef.femaleEquip1 = 100420;
                itemDef.modelZoom = 868;
                itemDef.rotationY = 127;
                itemDef.rotationX = 172;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -9;
                break;
            case 23526:
                itemDef.copyItem(23466);
                itemDef.name = "Mystic body (u)";
                itemDef.rs3 = true;
                itemDef.modelID = 100475;
                itemDef.maleEquip1 = 100324;
                itemDef.femaleEquip1 = 100447;
                itemDef.modelZoom = 1782;
                itemDef.rotationY = 523;
                itemDef.rotationX = 7;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 1;
                itemDef.slot = 4;
                break;
            case 23527:
                itemDef.copyItem(23467);
                itemDef.name = "Mystic legs (u)";
                itemDef.rs3 = true;
                itemDef.modelID = 100473;
                itemDef.maleEquip1 = 100313;
                itemDef.femaleEquip1 = 100437;
                itemDef.modelZoom = 1782;
                itemDef.rotationY = 463;
                itemDef.rotationX = 1818;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                itemDef.slot = 7;
                break;

            case 23528:
                itemDef.copyItem(23468);
                itemDef.name = "Hellish helm (u)";
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.modelID = 53359;
                itemDef.maleEquip1 = 50483;
                itemDef.femaleEquip1 = 50480;
                itemDef.modelZoom = 625;
                itemDef.rotationY = 60;
                itemDef.rotationX = 106;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 0;
                break;
            case 23529:
                itemDef.copyItem(23469);
                itemDef.name = "Hellish body (u)";
                itemDef.rs3 = true;
                itemDef.modelID = 53396;
                itemDef.maleEquip1 = 50499;
                itemDef.femaleEquip1 = 50482;
                itemDef.modelZoom = 1400;
                itemDef.rotationY = 505;
                itemDef.rotationX = 7;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                itemDef.slot = 4;
                break;
            case 23530:
                itemDef.copyItem(23470);
                itemDef.name = "Hellish legs (u)";
                itemDef.rs3 = true;
                itemDef.modelID = 53395;
                itemDef.maleEquip1 = 50498;
                itemDef.femaleEquip1 = 50481;
                itemDef.modelZoom = 1776;
                itemDef.rotationY = 559;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 34;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 1;
                itemDef.slot = 7;
                break;

            case 23531:
                itemDef.copyItem(23471);
                itemDef.name = "Midnight helm (u)";
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.modelID = 62619;
                itemDef.maleEquip1 = 51610;
                itemDef.femaleEquip1 = 51607;
                itemDef.modelZoom = 625;
                itemDef.rotationY = 0;
                itemDef.rotationX = 195;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23532:
                itemDef.copyItem(23472);
                itemDef.name = "Midnight body (u)";
                itemDef.rs3 = true;
                itemDef.modelID = 62621;
                itemDef.maleEquip1 = 51612;
                itemDef.femaleEquip1 = 51609;
                itemDef.modelZoom = 1450;
                itemDef.rotationY = 500;
                itemDef.rotationX = 1990;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 4;
                itemDef.slot = 4;
                break;
            case 23533:
                itemDef.copyItem(23473);
                itemDef.name = "Midnight legs (u)";
                itemDef.rs3 = true;
                itemDef.modelID = 62620;
                itemDef.maleEquip1 = 51611;
                itemDef.femaleEquip1 = 51608;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 600;
                itemDef.rotationX = 34;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.slot = 7;
                break;

            case 23534:
                itemDef.copyItem(19000);
                itemDef.name = "Sanctum Crystal";
                itemDef.modelZoom = 619;
                itemDef.rotationX = 220;
                itemDef.rotationY = 305;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 11;
                itemDef.colorChange = new double[]{0.5, 0.5, 1.5};
                itemDef.stackable = true;
                itemDef.actions = new String[]{"Inspect", null, null, null, null};
                break;

            case 23535:
                itemDef.copyItem(6737);
                itemDef.name = "Rat's cape";
                itemDef.modelID = 139993;
                itemDef.maleEquip1 = 139994;
                itemDef.femaleEquip1 = 139994;

                itemDef.oldColors = new int[]{
                        4154, 4149, 4144, 4167, 4306, 4448, 3216, 3221, 4313,
                        126, 0, 1050, 35, 38488, 47, 43467, 96, 5, 21
                };
                itemDef.newColors = new int[]{
                        24, 29, 24, 34, 06, 18, 16, 21, 13,
                        7866, 47243, 1050, 35, 7866, 47, 7866, 96, 5, 21
                };
                itemDef.modelZoom = 3359;
                itemDef.rotationX = 0;
                itemDef.rotationY = 330;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -35;
                break;

            case 23536:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.name = "Rose petals";
                itemDef.actions = new String[]{"Claim", null, null, null, null};
                itemDef.modelID = 102044;
                itemDef.modelZoom = 307;
                itemDef.rotationX = 0;
                itemDef.rotationY = 256;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -47;
                break;
            case 23537:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.name = "Spear of despite";
                itemDef.modelID = 93515;
                itemDef.maleEquip1 = 93500;
                itemDef.femaleEquip1 = 93500;
                itemDef.modelZoom = 3243;
                itemDef.rotationY = 512;
                itemDef.rotationX = 1997;
                itemDef.rotationZ = 13;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;
            case 23538:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.name = "Bulwark of revenge";
                itemDef.modelID = 102049;
                itemDef.maleEquip1 = 101942;
                itemDef.femaleEquip1 = 101942;
                itemDef.modelZoom = 1872;
                itemDef.rotationY = 402;
                itemDef.rotationX = 981;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -6;
                break;
            case 23539:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.name = "Aegis of devotion";
                itemDef.modelID = 102050;
                itemDef.maleEquip1 = 101944;
                itemDef.femaleEquip1 = 101944;
                itemDef.modelZoom = 1872;
                itemDef.rotationY = 513;
                itemDef.rotationX = 951;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 6;
                break;
            case 23540:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.name = "Lovers medallion";
                itemDef.modelID = 101844;
                itemDef.maleEquip1 = 101844;
                itemDef.femaleEquip1 = 101843;
                itemDef.modelZoom = 822;
                itemDef.rotationY = 370;
                itemDef.rotationX = 182;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -58;
                break;
            case 23541:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.name = "Haters medallion";
                itemDef.modelID = 101842;
                itemDef.maleEquip1 = 101842;
                itemDef.femaleEquip1 = 101841;
                itemDef.modelZoom = 822;
                itemDef.rotationY = 392;
                itemDef.rotationX = 276;
                itemDef.rotationZ = 114;
                itemDef.modelOffsetX = 43;
                itemDef.modelOffsetY = -100;
                break;
            case 23542:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.name = "Cupid's halo";
                itemDef.modelID = 101838;
                itemDef.maleEquip1 = 101838;
                itemDef.femaleEquip1 = 101838;
                itemDef.modelZoom = 428;
                itemDef.rotationY = 936;
                itemDef.rotationX = 855;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 79;
                break;
            case 23543:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.name = "Demonic horns";
                itemDef.modelID = 101840;
                itemDef.maleEquip1 = 101840;
                itemDef.femaleEquip1 = 101840;
                itemDef.modelZoom = 266;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 94;
                break;
            case 23544:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.name = "Crown of the fallen";
                itemDef.modelID = 101839;
                itemDef.maleEquip1 = 101839;
                itemDef.femaleEquip1 = 101839;
                itemDef.modelZoom = 542;
                itemDef.rotationY = 505;
                itemDef.rotationX = 1024;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -6;
                break;
            case 23545:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Drink", null, null, null, null};
                itemDef.name = "Love potion";
                itemDef.modelID = 102043;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 559;
                itemDef.rotationY = 74;
                itemDef.rotationX = 1374;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 0;
                break;
            case 23546:
                itemDef.copyItem(1145);
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Eat", null, null, null, null};
                itemDef.name = "Heart Candy";
                itemDef.modelID = 93516;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 724;
                itemDef.rotationY = 308;
                itemDef.rotationX = 1808;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                break;/**/
            case 23547:
                itemDef.name = "Valentine Gift";
                itemDef.actions = new String[]{"Open", null, null, null, null};
                itemDef.rs3 = true;
                itemDef.modelID = 93506;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 526;
                itemDef.rotationY = 308;
                itemDef.rotationX = 138;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -4;
                break;


            case 23548:
                itemDef.name = "Dark lord head";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.rs3 = true;
                itemDef.modelID = 102559;
                itemDef.maleEquip1 = 102559;
                itemDef.femaleEquip1 = 102554;
                itemDef.modelZoom = 658;
                itemDef.rotationY = 1986;
                itemDef.rotationX = 7;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 69;
                itemDef.slot = 0;
                break;

            case 23549:
                itemDef.name = "Dark lord body";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.rs3 = true;
                itemDef.modelID = 102561;
                itemDef.maleEquip1 = 102561;
                itemDef.femaleEquip1 = 102556;
                itemDef.modelZoom = 1414;
                itemDef.rotationY = 35;
                itemDef.rotationX = 13;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 59;
                itemDef.slot = 4;
                break;

            case 23550:
                itemDef.name = "Dark lord legs";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.rs3 = true;
                itemDef.modelID = 102560;
                itemDef.maleEquip1 = 102560;
                itemDef.femaleEquip1 = 102555;
                itemDef.modelZoom = 1654;
                itemDef.rotationY = 1956;
                itemDef.rotationX = 2047;
                itemDef.rotationZ = 1798;
                itemDef.modelOffsetX = -43;
                itemDef.modelOffsetY = -7;
                break;

            case 23551:
                itemDef.name = "Dark lord feet";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.rs3 = true;
                itemDef.modelID = 102557;
                itemDef.maleEquip1 = 102557;
                itemDef.femaleEquip1 = 102557;
                itemDef.modelZoom = 886;
                itemDef.rotationY = 238;
                itemDef.rotationX = 283;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -35;
                break;

            case 23552:
                itemDef.name = "Dark lord hands";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.rs3 = true;
                itemDef.modelID = 102558;
                itemDef.maleEquip1 = 102558;
                itemDef.femaleEquip1 = 102558;
                itemDef.modelZoom = 620;
                itemDef.rotationY = 69;
                itemDef.rotationX = 1649;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 50;
                itemDef.slot = 9;
                break;

            case 23553:
                itemDef.copyItem(23549);
                itemDef.rs3 = true;
                itemDef.name = "Dark Lord Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23554:
                itemDef.copyItem(23553);
                itemDef.noteTemplate = 23553;
                itemDef.note = 23390;
                itemDef.name = "Dark Lord Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 23555:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.name = "Steadfast scale";
                itemDef.modelID = 104357;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 336;
                itemDef.rotationY = 267;
                itemDef.rotationX = 261;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 17;
                break;
            case 23556:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.name = "Glaiven wing-tip";
                itemDef.modelID = 104358;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 375;
                itemDef.rotationY = 273;
                itemDef.rotationX = 2021;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 16;
                break;
            case 23557:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.name = "Ragefire gland";
                itemDef.modelID = 104359;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 336;
                itemDef.rotationY = 144;
                itemDef.rotationX = 105;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -1;
                break;
            case 23558:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Emberkeen boots";
                itemDef.modelID = 104363;
                itemDef.maleEquip1 = 104385;
                itemDef.femaleEquip1 = 104385;
                itemDef.modelZoom = 750;
                itemDef.rotationY = 147;
                itemDef.rotationX = 51;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -7;
                itemDef.scaleY = 170;
                itemDef.scaleY = 150;
                break;
            case 23559:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Flarefrost boots";
                itemDef.modelID = 104364;
                itemDef.maleEquip1 = 104386;
                itemDef.femaleEquip1 = 104386;
                itemDef.modelZoom = 750;
                itemDef.rotationY = 147;
                itemDef.rotationX = 51;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -7;
                itemDef.scaleY = 170;
                itemDef.scaleY = 150;
                break;
            case 23560:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Hailfire boots";
                itemDef.modelID = 104362;
                itemDef.maleEquip1 = 104384;
                itemDef.femaleEquip1 = 104384;
                itemDef.modelZoom = 750;
                itemDef.rotationY = 147;
                itemDef.rotationX = 51;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -7;
                itemDef.scaleY = 170;
                itemDef.scaleY = 150;
                break;


            case 23561:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Hanto headpiece";
                itemDef.modelID = 114160;
                itemDef.maleEquip1 = 114160;
                itemDef.femaleEquip1 = 114153;
                itemDef.modelZoom = 1447;
                itemDef.rotationY = 50;
                itemDef.rotationX = 91;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 100;
                itemDef.slot = 0;
                itemDef.actions[2] = "Disassemble";
                break;

            case 23562:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Hanto cuirass";
                itemDef.modelID = 114162;
                itemDef.maleEquip1 = 114162;
                itemDef.femaleEquip1 = 114155;
                itemDef.modelZoom = 1711;
                itemDef.rotationY = 0;
                itemDef.rotationX = 25;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 46;
                itemDef.slot = 4;
                itemDef.actions[2] = "Disassemble";
                break;

            case 23563:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Hanto legguards";
                itemDef.modelID = 114161;
                itemDef.maleEquip1 = 114161;
                itemDef.femaleEquip1 = 114154;
                itemDef.modelZoom = 1875;
                itemDef.rotationY = 54;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 14;
                itemDef.actions[2] = "Disassemble";
                break;

            case 23564:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Hanto handguards";
                itemDef.modelID = 114159;
                itemDef.maleEquip1 = 114159;
                itemDef.femaleEquip1 = 114159;
                itemDef.modelZoom = 855;
                itemDef.rotationY = 1858;
                itemDef.rotationX = 1670;
                itemDef.rotationZ = 1239;
                itemDef.modelOffsetX = -23;
                itemDef.modelOffsetY = -97;
                itemDef.slot = 9;
                itemDef.actions[2] = "Disassemble";
                break;

            case 23565:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Hanto sabatons";
                itemDef.modelID = 114158;
                itemDef.maleEquip1 = 114158;
                itemDef.femaleEquip1 = 114158;
                itemDef.modelZoom = 983;
                itemDef.rotationY = 349;
                itemDef.rotationX = 1785;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 16;
                itemDef.actions[2] = "Disassemble";
                break;

            case 23566:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Hanto Halberd";
                itemDef.modelID = 114156;
                itemDef.maleEquip1 = 114156;
                itemDef.femaleEquip1 = 114156;
                itemDef.modelZoom = 3717;
                itemDef.rotationY = 1651;
                itemDef.rotationX = 1766;
                itemDef.rotationZ = 566;
                itemDef.modelOffsetX = 35;
                itemDef.modelOffsetY = 32;
                itemDef.actions[2] = "Disassemble";
                break;

            case 23567:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Hanto Staff";
                itemDef.modelID = 114163;
                itemDef.maleEquip1 = 114163;
                itemDef.femaleEquip1 = 114163;
                itemDef.modelZoom = 3618;
                itemDef.rotationY = 1602;
                itemDef.rotationX = 1811;
                itemDef.rotationZ = 451;
                itemDef.modelOffsetX = 69;
                itemDef.modelOffsetY = 51;
                itemDef.actions[2] = "Disassemble";
                break;

            case 23568:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Hanto Bow";
                itemDef.modelID = 114151;
                itemDef.maleEquip1 = 114151;
                itemDef.femaleEquip1 = 114151;
                itemDef.modelZoom = 2200;
                itemDef.rotationY = 707;
                itemDef.rotationX = 256;
                itemDef.rotationZ = 1683;
                itemDef.modelOffsetX = -75;
                itemDef.modelOffsetY = 26;
                itemDef.actions[2] = "Disassemble";
                break;

            case 23569:
                itemDef.copyItem(7478);
                itemDef.actions = new String[]{"Sacrifice", null, null, null, null};
                itemDef.name = "Hanto Token";
                itemDef.modelZoom = 473;
                itemDef.rotationX = 279;
                itemDef.rotationY = 440;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 2;
                itemDef.colorChange = new double[]{1.9, 1, 1.2};
                break;

            case 23570:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Activate", null, null, null, null};
                itemDef.name = "Mass Dissolver";
                itemDef.modelID = 104251;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 855;
                itemDef.rotationY = 561;
                itemDef.rotationX = 739;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -4;
                itemDef.modelOffsetY = 5;
                break;

            case 23571:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.name = "Pulse core";
                itemDef.modelID = 103415;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 658;
                itemDef.rotationY = 471;
                itemDef.rotationX = 1636;
                itemDef.rotationZ = 1717;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -15;
                break;


            case 23572:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shadow hunter hat";
                itemDef.modelID = 66211;
                itemDef.maleEquip1 = 83295;
                itemDef.femaleEquip1 = 83321;
                itemDef.modelZoom = 461;
                itemDef.rotationY = 298;
                itemDef.rotationX = 42;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 26;
                itemDef.slot = 0;
                break;

            case 23573:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shadow hunter chest";
                itemDef.modelID = 83309;
                itemDef.maleEquip1 = 83309;
                itemDef.femaleEquip1 = 83334;
                itemDef.modelZoom = 1366;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 43;
                itemDef.slot = 4;
                break;

            case 23574:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shadow hunter legs";
                itemDef.modelID = 66211;
                itemDef.maleEquip1 = 83298;
                itemDef.femaleEquip1 = 83328;
                itemDef.modelZoom = 1742;
                itemDef.rotationY = 526;
                itemDef.rotationX = 229;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 8;
                itemDef.modelOffsetY = 7;
                break;

            case 23575:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shadow hunter gloves";
                itemDef.modelID = 66211;
                itemDef.maleEquip1 = 83289;
                itemDef.femaleEquip1 = 83289;
                itemDef.modelZoom = 658;
                itemDef.rotationY = 485;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -4;
                itemDef.slot = 9;
                break;

            case 23576:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shadow hunter boots";
                itemDef.modelID = 66211;
                itemDef.maleEquip1 = 83285;
                itemDef.femaleEquip1 = 83285;
                itemDef.modelZoom = 615;
                itemDef.rotationY = 173;
                itemDef.rotationX = 2039;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -7;
                break;

            case 23577:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shadow hunter cape";
                itemDef.modelID = 66211;
                itemDef.maleEquip1 = 83314;
                itemDef.femaleEquip1 = 83314;
                itemDef.modelZoom = 1200;
                itemDef.rotationY = 88;
                itemDef.rotationX = 1980;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -2;
                break;


            case 23578:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Seren godbow";
                itemDef.modelID = 112981;
                itemDef.maleEquip1 = 113125;
                itemDef.femaleEquip1 = 113125;
                itemDef.modelZoom = 1948;
                itemDef.rotationY = 484;
                itemDef.rotationX = 1054;
                itemDef.rotationZ = 1939;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -3;
                break;

            case 23579:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Eldritch crossbow";
                itemDef.modelID = 74787;
                itemDef.maleEquip1 = 74750;
                itemDef.femaleEquip1 = 74750;
                itemDef.modelZoom = 1744;
                itemDef.rotationY = 222;
                itemDef.rotationX = 2030;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -81;
                break;


            case 23580:
                itemDef.rs3 = true;
                itemDef.slot = 4;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void knight top";
                itemDef.modelID = 67461;
                itemDef.maleEquip1 = 66686;
                itemDef.femaleEquip1 = 67077;
                itemDef.modelZoom = 1450;
                itemDef.rotationY = 470;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23581:
                itemDef.rs3 = true;
                itemDef.slot = 7;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void knight robes";
                itemDef.modelID = 67458;
                itemDef.maleEquip1 = 66595;
                itemDef.femaleEquip1 = 66984;
                itemDef.modelZoom = 1824;
                itemDef.rotationY = 443;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23582:
                itemDef.rs3 = true;
                itemDef.slot = 9;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void knight gloves";
                itemDef.modelID = 67447;
                itemDef.maleEquip1 = 66387;
                itemDef.femaleEquip1 = 66387;
                itemDef.modelZoom = 921;
                itemDef.rotationY = 486;
                itemDef.rotationX = 138;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 0;
                break;


            case 23583:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void mage helm";
                itemDef.modelID = 67460;
                itemDef.maleEquip1 = 66478;
                itemDef.femaleEquip1 = 66478;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 276;
                itemDef.rotationX = 99;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 42;
                break;

            case 23584:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void ranger helm";
                itemDef.modelID = 67459;
                itemDef.maleEquip1 = 66477;
                itemDef.femaleEquip1 = 66477;
                itemDef.modelZoom = 541;
                itemDef.rotationY = 222;
                itemDef.rotationX = 36;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 8;
                break;

            case 23585:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void melee helm";
                itemDef.modelID = 67462;
                itemDef.maleEquip1 = 66479;
                itemDef.femaleEquip1 = 66479;
                itemDef.modelZoom = 850;
                itemDef.rotationY = 108;
                itemDef.rotationX = 225;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWieldZ = -1;
                break;


            case 23586:
                itemDef.rs3 = true;
                itemDef.slot = 4;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Elite void knight top";
                itemDef.modelID = 67457;
                itemDef.maleEquip1 = 66687;
                itemDef.femaleEquip1 = 67079;
                itemDef.modelZoom = 1450;
                itemDef.rotationY = 470;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23587:
                itemDef.rs3 = true;
                itemDef.slot = 7;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Elite void knight robe";
                itemDef.modelID = 67450;
                itemDef.maleEquip1 = 66596;
                itemDef.femaleEquip1 = 66987;
                itemDef.modelZoom = 1824;
                itemDef.rotationY = 443;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23588:
                itemDef.rs3 = true;
                itemDef.name = "Void armour upgrade";
                itemDef.modelID = 96689;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 2632;
                itemDef.rotationY = 754;
                itemDef.rotationX = 270;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 3;
                break;

            case 23589:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void wand";
                itemDef.modelID = 67454;
                itemDef.maleEquip1 = 67887;
                itemDef.femaleEquip1 = 67887;
                itemDef.modelZoom = 1308;
                itemDef.rotationY = 297;
                itemDef.rotationX = 1284;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -10;
                itemDef.maleWieldY = 4;
                break;

            case 23590:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void sword";
                itemDef.modelID = 79267;
                itemDef.maleEquip1 = 79813;
                itemDef.femaleEquip1 = 79813;
                itemDef.modelZoom = 1744;
                itemDef.rotationY = 330;
                itemDef.rotationX = 1505;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWieldY = 4;
                break;

            case 23591:
                itemDef.rs3 = true;
                itemDef.name = "Void weapon upgrade";
                itemDef.modelID = 90441;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 1284;
                itemDef.rotationY = 526;
                itemDef.rotationX = 1010;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -3;
                break;


            case 23592:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void wand (u)";
                itemDef.modelID = 67454;
                itemDef.maleEquip1 = 67887;
                itemDef.femaleEquip1 = 67887;
                itemDef.modelZoom = 1308;
                itemDef.rotationY = 297;
                itemDef.rotationX = 1284;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -10;
                itemDef.maleWieldY = 4;
                itemDef.oldColors = new int[]{
                        -2396, -2379, -2388, -3261, -3374,
                        -3362, -3138, -2331
                };
                itemDef.newColors = new int[]{
                        11186, 11186, 11186, 11212, 11212, 11212,
                        11212, 11212, 11212,
                };
                break;

            case 23593:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Void sword (u)";
                itemDef.modelID = 79267;
                itemDef.maleEquip1 = 79813;
                itemDef.femaleEquip1 = 79813;
                itemDef.modelZoom = 1744;
                itemDef.rotationY = 330;
                itemDef.rotationX = 1505;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWieldY = 4;
                itemDef.colorChange = new double[]{0.9, 0.9, 0};
                break;


            case 23594:
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Infinty Aura";
                itemDef.modelID = 131173;
                itemDef.maleEquip1 = 131173;
                itemDef.femaleEquip1 = 131173;
                itemDef.modelZoom = 5000;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 56;
                itemDef.maleWieldY = 4;
                itemDef.maleModelScale = 1.8;
                itemDef.maleWieldY = -65;
                itemDef.oldColors = new int[]{
                        9021
                };
                itemDef.newColors = new int[]{
                        5
                };
                break;
            case 23595:
                itemDef.copyItem(20400);
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Infinty cape";
                break;
            case 23596:
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Infinty rapier";
                itemDef.modelID = 90673;
                itemDef.maleEquip1 = 90673;
                itemDef.femaleEquip1 = 90673;
                itemDef.modelZoom = 1615;
                itemDef.rotationX = 1211;
                itemDef.rotationY = 186;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -66;
                itemDef.modelOffsetY = 167;
                itemDef.maleWieldY = 4;
                break;


            case 23597:
                itemDef.copyItem(17011);
                itemDef.modelID = 39072;
                itemDef.maleEquip1 = 39055;
                itemDef.femaleEquip1 = 39055;
                itemDef.maleWieldY = 10;

                itemDef.name = "King of nightmares";

                itemDef.modelZoom = 2064;
                itemDef.rotationX = 1050;
                itemDef.rotationY = 404;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 13;

                itemDef.oldColors = new int[]{7326, 7333, 7442, 16, 61598, 61838, 61718, 18
                        , 8375, 35, 28, 20, 61962, 7097, 7108, 0
                };
                itemDef.newColors = new int[]{2, 4, 6, 105, 0, 0, 0, 105
                        , 25, 118, 112, 120, 0, 8, 10, 110
                };

                break;

            case 23600:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Staff of Sliske (ice)";
                itemDef.modelID = 24402;
                itemDef.maleEquip1 = 23877;
                itemDef.femaleEquip1 = 23877;
                itemDef.modelZoom = 3651;
                itemDef.rotationY = 500;
                itemDef.rotationX = 492;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23601:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Seren godbow (ice)";
                itemDef.modelID = 24397;
                itemDef.maleEquip1 = 20923;
                itemDef.femaleEquip1 = 20923;
                itemDef.modelZoom = 1948;
                itemDef.rotationY = 484;
                itemDef.rotationX = 1054;
                itemDef.rotationZ = 1939;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -3;
                break;

            case 23602:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Khopesh of Tumeken (ice)";
                itemDef.modelID = 24452;
                itemDef.maleEquip1 = 21160;
                itemDef.femaleEquip1 = 21160;
                itemDef.modelZoom = 921;
                itemDef.rotationY = 498;
                itemDef.rotationX = 1778;
                itemDef.rotationZ = 303;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -4;
                itemDef.maleWieldX = 5;
                itemDef.maleWieldY = 28;
                itemDef.maleModelScale = 0.8;
                break;

            case 23603:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Silver bladed wings";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 101856;
                itemDef.femaleEquip1 = 101856;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23604:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 2;
                itemDef.name = "Shark Necklace";
                itemDef.modelID = 75201;
                itemDef.maleEquip1 = 72087;
                itemDef.femaleEquip1 = 72170;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 318;
                itemDef.rotationX = 129;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 46;
                break;


            case 23605:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shark head";
                itemDef.modelID = 102607;
                itemDef.maleEquip1 = 102575;
                itemDef.femaleEquip1 = 102570;
                itemDef.modelZoom = 630;
                itemDef.rotationY = 52;
                itemDef.rotationX = 184;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -2;
                itemDef.modelOffsetY = -1;
                break;

            case 23606:
                itemDef.rs3 = true;
                itemDef.slot = 4;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shark body";
                itemDef.modelID = 102609;
                itemDef.maleEquip1 = 102577;
                itemDef.femaleEquip1 = 102572;
                itemDef.modelZoom = 1398;
                itemDef.rotationY = 397;
                itemDef.rotationX = 2046;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;

            case 23607:
                itemDef.rs3 = true;
                itemDef.slot = 7;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shark legs";
                itemDef.modelID = 102608;
                itemDef.maleEquip1 = 102576;
                itemDef.femaleEquip1 = 102571;
                itemDef.modelZoom = 1654;
                itemDef.rotationY = 394;
                itemDef.rotationX = 226;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;

            case 23608:
                itemDef.rs3 = true;
                itemDef.slot = 9;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shark hands";
                itemDef.modelID = 102606;
                itemDef.maleEquip1 = 102574;
                itemDef.femaleEquip1 = 102574;
                itemDef.modelZoom = 630;
                itemDef.rotationY = 316;
                itemDef.rotationX = 226;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 1;
                break;

            case 23609:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Shark feet";
                itemDef.modelID = 102605;
                itemDef.maleEquip1 = 102573;
                itemDef.femaleEquip1 = 102573;
                itemDef.modelZoom = 886;
                itemDef.rotationY = 238;
                itemDef.rotationX = 283;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -35;
                break;


            case 23610:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Burnt shark head";
                itemDef.modelID = 102602;
                itemDef.maleEquip1 = 102567;
                itemDef.femaleEquip1 = 102562;
                itemDef.modelZoom = 630;
                itemDef.rotationY = 52;
                itemDef.rotationX = 184;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -2;
                itemDef.modelOffsetY = -1;
                break;

            case 23611:
                itemDef.rs3 = true;
                itemDef.slot = 4;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Burnt shark body";
                itemDef.modelID = 102604;
                itemDef.maleEquip1 = 102569;
                itemDef.femaleEquip1 = 102564;
                itemDef.modelZoom = 1398;
                itemDef.rotationY = 397;
                itemDef.rotationX = 2046;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;

            case 23612:
                itemDef.rs3 = true;
                itemDef.slot = 7;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Burnt shark legs";
                itemDef.modelID = 102603;
                itemDef.maleEquip1 = 102568;
                itemDef.femaleEquip1 = 102563;
                itemDef.modelZoom = 1654;
                itemDef.rotationY = 394;
                itemDef.rotationX = 226;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;

            case 23613:
                itemDef.rs3 = true;
                itemDef.slot = 9;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Burnt shark hands";
                itemDef.modelID = 102601;
                itemDef.maleEquip1 = 102566;
                itemDef.femaleEquip1 = 102566;
                itemDef.modelZoom = 630;
                itemDef.rotationY = 316;
                itemDef.rotationX = 226;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 1;
                break;

            case 23614:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Burnt shark feet";
                itemDef.modelID = 102600;
                itemDef.maleEquip1 = 102565;
                itemDef.femaleEquip1 = 102565;
                itemDef.modelZoom = 886;
                itemDef.rotationY = 238;
                itemDef.rotationX = 283;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -35;
                break;


            case 23615:
                itemDef.copyItem(23606);
                itemDef.rs3 = true;
                itemDef.name = "Shark Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23616:
                itemDef.copyItem(23615);
                itemDef.noteTemplate = 23615;
                itemDef.note = 23390;
                itemDef.name = "Shark Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;

            case 23617:
                itemDef.copyItem(23611);
                itemDef.rs3 = true;
                itemDef.name = "Shadow Shark Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23618:
                itemDef.copyItem(23617);
                itemDef.noteTemplate = 23617;
                itemDef.note = 23390;
                itemDef.name = "Shadow Shark Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;

            case 23619:
                itemDef.copyItem(13333);
                itemDef.name = "Fenrir's Katana";
                itemDef.maleModelScale = 1.3;
                itemDef.maleWieldX = -7;
                itemDef.maleWieldY = -27;
                break;
            case 23620:
                itemDef.copyItem(7995);
                itemDef.name = "Fenrir's Cape";
                itemDef.oldColors = new int[]{57};
                itemDef.newColors = new int[]{51};
                break;
            case 23621:
                itemDef.copyItem(10709);
                itemDef.name = "Fenrir's Helm";
                break;


            case 23622:
                itemDef.rs3 = true;
                itemDef.slot = 9;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Saradomin gauntlets";
                itemDef.modelID = 88275;
                itemDef.maleEquip1 = 87658;
                itemDef.femaleEquip1 = 87658;
                itemDef.modelZoom = 786;
                itemDef.rotationY = 500;
                itemDef.rotationX = 162;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                break;

            case 23623:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Saradomin boots";
                itemDef.modelID = 88262;
                itemDef.maleEquip1 = 87656;
                itemDef.femaleEquip1 = 87656;
                itemDef.modelZoom = 983;
                itemDef.rotationY = 349;
                itemDef.rotationX = 1785;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 13;
                break;


            case 23624:
                itemDef.rs3 = true;
                itemDef.slot = 4;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Saradomin cuirass";
                itemDef.modelID = 88263;
                itemDef.maleEquip1 = 87717;
                itemDef.femaleEquip1 = 87781;
                itemDef.modelZoom = 1513;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 5;
                break;

            case 23625:
                itemDef.rs3 = true;
                itemDef.slot = 7;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Saradomin greaves";
                itemDef.modelID = 88265;
                itemDef.maleEquip1 = 87664;
                itemDef.femaleEquip1 = 87780;
                itemDef.modelZoom = 2105;
                itemDef.rotationY = 500;
                itemDef.rotationX = 1980;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 1;
                break;

            case 23626:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Saradomin helm";
                itemDef.modelID = 88269;
                itemDef.maleEquip1 = 87663;
                itemDef.femaleEquip1 = 87777;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 148;
                itemDef.rotationX = 2020;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -13;
                break;


            case 23627:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Relic helm of Saradomin";
                itemDef.modelID = 86186;
                itemDef.maleEquip1 = 86020;
                itemDef.femaleEquip1 = 86141;
                itemDef.modelZoom = 724;
                itemDef.rotationY = 154;
                itemDef.rotationX = 163;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -4;
                itemDef.modelOffsetY = -4;
                break;

            case 23628:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Relic helm of Zamorak";
                itemDef.modelID = 86180;
                itemDef.maleEquip1 = 86024;
                itemDef.femaleEquip1 = 86142;
                itemDef.modelZoom = 724;
                itemDef.rotationY = 154;
                itemDef.rotationX = 163;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -4;
                itemDef.modelOffsetY = -4;
                break;

            case 23629:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Saradomin godsword";
                itemDef.modelID = 75202;
                itemDef.maleEquip1 = 27728;
                itemDef.femaleEquip1 = 27728;
                itemDef.modelZoom = 2050;
                itemDef.rotationY = 222;
                itemDef.rotationX = 1958;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -28;
                break;

            case 23630:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Zamorak godsword";
                itemDef.modelID = 72891;
                itemDef.maleEquip1 = 72111;
                itemDef.femaleEquip1 = 72111;
                itemDef.modelZoom = 2128;
                itemDef.rotationY = 243;
                itemDef.rotationX = 1964;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;


            case 23631:
                itemDef.rs3 = true;
                itemDef.stackable = true;
                itemDef.name = "Sacred metal fragments";
                itemDef.modelID = 88289;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 855;
                itemDef.rotationY = 422;
                itemDef.rotationX = 1778;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 3;
                break;

            case 23632:
                itemDef.rs3 = true;
                itemDef.name = "Icyenic orb";
                itemDef.modelID = 88286;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 1426;
                itemDef.rotationY = 479;
                itemDef.rotationX = 2042;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 13;
                itemDef.modelOffsetY = -11;
                break;

            case 23633:
                itemDef.rs3 = true;
                itemDef.name = "Infernal orb";
                itemDef.modelID = 88288;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 1216;
                itemDef.rotationY = 429;
                itemDef.rotationX = 2006;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -3;
                break;

            case 23634:
                itemDef.rs3 = true;
                itemDef.name = "Icyenic greathammer head";
                itemDef.modelID = 88287;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 1367;
                itemDef.rotationY = 526;
                itemDef.rotationX = 1978;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -8;
                break;

            case 23635:
                itemDef.rs3 = true;
                itemDef.name = "Infernal greathammer head";
                itemDef.modelID = 88283;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 1495;
                itemDef.rotationY = 629;
                itemDef.rotationX = 1520;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -7;
                break;

            case 23636:
                itemDef.rs3 = true;
                itemDef.name = "Icyenic bowstaff";
                itemDef.modelID = 88285;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 743;
                itemDef.rotationY = 456;
                itemDef.rotationX = 1604;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 3;
                break;

            case 23637:
                itemDef.rs3 = true;
                itemDef.name = "Infernal bowstaff";
                itemDef.modelID = 88284;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 1149;
                itemDef.rotationY = 553;
                itemDef.rotationX = 1757;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -4;
                break;

            case 23638:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Icyenic staff";
                itemDef.modelID = 88282;
                itemDef.maleEquip1 = 87743;
                itemDef.femaleEquip1 = 87743;
                itemDef.modelZoom = 3378;
                itemDef.rotationY = 719;
                itemDef.rotationX = 2027;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 3;
                break;

            case 23639:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Infernal staff";
                itemDef.modelID = 88294;
                itemDef.maleEquip1 = 87746;
                itemDef.femaleEquip1 = 87746;
                itemDef.modelZoom = 2438;
                itemDef.rotationY = 734;
                itemDef.rotationX = 2027;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -4;
                break;

            case 23640:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Icyenic hammer";
                itemDef.modelID = 88281;
                itemDef.maleEquip1 = 87739;
                itemDef.femaleEquip1 = 87739;
                itemDef.modelZoom = 2566;
                itemDef.rotationY = 500;
                itemDef.rotationX = 81;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 0;
                break;

            case 23641:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Infernal hammer";
                itemDef.modelID = 88292;
                itemDef.maleEquip1 = 87740;
                itemDef.femaleEquip1 = 87740;
                itemDef.modelZoom = 2512;
                itemDef.rotationY = 317;
                itemDef.rotationX = 1988;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = 45;
                break;

            case 23642:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Icyenic bow";
                itemDef.modelID = 88278;
                itemDef.maleEquip1 = 87732;
                itemDef.femaleEquip1 = 87732;
                itemDef.modelZoom = 2566;
                itemDef.rotationY = 500;
                itemDef.rotationX = 81;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 0;
                break;

            case 23643:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Infernal bow";
                itemDef.modelID = 88293;
                itemDef.maleEquip1 = 87727;
                itemDef.femaleEquip1 = 87727;
                itemDef.modelZoom = 3334;
                itemDef.rotationY = 533;
                itemDef.rotationX = 1294;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 5;
                break;

            case 23644:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Drink", null, null, null, null};
                itemDef.name = "Godly potion";
                itemDef.modelID = 88264;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 758;
                itemDef.rotationY = 367;
                itemDef.rotationX = 94;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 5;
                break;


            case 23645:
                itemDef.rs3 = true;
                itemDef.slot = 9;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Zamorak gauntlets";
                itemDef.modelID = 88261;
                itemDef.maleEquip1 = 87659;
                itemDef.femaleEquip1 = 87659;
                itemDef.modelZoom = 786;
                itemDef.rotationY = 500;
                itemDef.rotationX = 162;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                break;

            case 23646:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Zamorak boots";
                itemDef.modelID = 88259;
                itemDef.maleEquip1 = 87657;
                itemDef.femaleEquip1 = 87657;
                itemDef.modelZoom = 983;
                itemDef.rotationY = 349;
                itemDef.rotationX = 1785;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 13;
                break;

            case 23647:
                itemDef.rs3 = true;
                itemDef.slot = 4;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Zamorak cuirass";
                itemDef.modelID = 88272;
                itemDef.maleEquip1 = 87718;
                itemDef.femaleEquip1 = 87782;
                itemDef.modelZoom = 1513;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 5;
                break;


            case 23648:
                itemDef.rs3 = true;
                itemDef.slot = 7;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Zamorak greaves";
                itemDef.modelID = 88267;
                itemDef.maleEquip1 = 87665;
                itemDef.femaleEquip1 = 87779;
                itemDef.modelZoom = 2105;
                itemDef.rotationY = 500;
                itemDef.rotationX = 1980;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 1;
                break;

            case 23649:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Warpriest of Zamorak helm";
                itemDef.modelID = 88271;
                itemDef.maleEquip1 = 87662;
                itemDef.femaleEquip1 = 87778;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 148;
                itemDef.rotationX = 2020;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -13;
                break;


            case 23650:
                itemDef.copyItem(23647);
                itemDef.rs3 = true;
                itemDef.name = "Zamorak Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23651:
                itemDef.copyItem(23650);
                itemDef.noteTemplate = 23650;
                itemDef.note = 23390;
                itemDef.name = "Zamorak Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;

            case 23652:
                itemDef.copyItem(23624);
                itemDef.rs3 = true;
                itemDef.name = "Saradomin Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23653:
                itemDef.copyItem(23652);
                itemDef.noteTemplate = 23652;
                itemDef.note = 23390;
                itemDef.name = "Saradomin Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 23654:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Colosseum hat";
                itemDef.modelID = 71873;
                itemDef.maleEquip1 = 71813;
                itemDef.femaleEquip1 = 71873;
                itemDef.modelZoom = 683;
                itemDef.rotationY = 0;
                itemDef.rotationX = 108;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23655:
                itemDef.rs3 = true;
                itemDef.slot = 4;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Colosseum body";
                itemDef.modelID = 71835;
                itemDef.maleEquip1 = 71835;
                itemDef.femaleEquip1 = 71892;
                itemDef.modelZoom = 987;
                itemDef.rotationY = 498;
                itemDef.rotationX = 13;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                break;

            case 23656:
                itemDef.rs3 = true;
                itemDef.slot = 7;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Colosseum legs";
                itemDef.modelID = 71879;
                itemDef.maleEquip1 = 71818;
                itemDef.femaleEquip1 = 71879;
                itemDef.modelZoom = 1118;
                itemDef.rotationY = 566;
                itemDef.rotationX = 2047;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -3;
                break;

            case 23657:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Colosseum boots";
                itemDef.modelID = 71802;
                itemDef.maleEquip1 = 71802;
                itemDef.femaleEquip1 = 71860;
                itemDef.modelZoom = 760;
                itemDef.rotationY = 195;
                itemDef.rotationX = 2028;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -4;
                itemDef.modelOffsetY = 1;
                break;


            case 23658:
                itemDef.copyItem(5074);
                itemDef.name = "Saradomin Pet";
                itemDef.modelID = MobDefinition.get(10000).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.rs3 = true;
                break;
            case 23659:
                itemDef.copyItem(5074);
                itemDef.name = "Zamorak Pet";
                itemDef.modelID = MobDefinition.get(10001).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.rs3 = true;
                break;


            case 23660:
                // itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Leprechaun ring";
                itemDef.modelID = 140008;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 328;
                itemDef.rotationX = 110;
                itemDef.rotationY = 228;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -22;
                break;

            case 23661:
                itemDef.copyItem(5074);
                itemDef.name = "Leprechaun Pet";
                itemDef.modelID = MobDefinition.get(9019).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.modelZoom = 660;
                itemDef.rotationX = 60;
                itemDef.rotationY = 172;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 1;
                break;

            case 23662:
                itemDef.copyItem(7740);
                itemDef.name = "Lucky brew";
                itemDef.colorChange = new double[]{0.5, 1.2, 0.5};
                break;

            case 23663:
                itemDef.copyItem(17704);
                itemDef.name = "Gnawlije's Gun";
                itemDef.oldColors = new int[]{
                        84
                };
                itemDef.newColors = new int[]{
                        32690
                };
                break;
            case 23664:
                itemDef.copyItem(17011);
                itemDef.name = "Icy's Staff";
                itemDef.modelID = 140010;
                itemDef.maleEquip1 = 140009;
                itemDef.femaleEquip1 = 140009;
                itemDef.maleWieldY = 10;
                break;


            case 23665:
                itemDef.rs3 = true;
                itemDef.slot = 0;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Faceless assassin head (blue)";
                itemDef.modelID = 9352;
                itemDef.maleEquip1 = 9352;
                itemDef.femaleEquip1 = 9085;
                itemDef.modelZoom = 888;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 85;
                break;

            case 23666:
                itemDef.rs3 = true;
                itemDef.slot = 4;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Faceless assassin top (blue)";
                itemDef.modelID = 8266;
                itemDef.maleEquip1 = 8266;
                itemDef.femaleEquip1 = 8158;
                itemDef.modelZoom = 1546;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 50;
                break;

            case 23667:
                itemDef.rs3 = true;
                itemDef.slot = 7;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Faceless assassin legs (blue)";
                itemDef.modelID = 8264;
                itemDef.maleEquip1 = 8264;
                itemDef.femaleEquip1 = 8156;
                itemDef.modelZoom = 1941;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 12;
                break;

            case 23668:
                itemDef.rs3 = true;
                itemDef.slot = 9;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Faceless assassin hands (blue)";
                itemDef.modelID = 8160;
                itemDef.maleEquip1 = 8160;
                itemDef.femaleEquip1 = 8160;
                itemDef.modelZoom = 757;
                itemDef.rotationY = 40;
                itemDef.rotationX = 343;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 48;
                break;

            case 23669:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Faceless assassin boots (blue)";
                itemDef.modelID = 8159;
                itemDef.maleEquip1 = 8159;
                itemDef.femaleEquip1 = 8159;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 175;
                itemDef.rotationX = 343;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 7;
                itemDef.modelOffsetY = -9;
                break;

            case 23670:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Faceless assassin cape (blue)";
                itemDef.modelID = 8267;
                itemDef.maleEquip1 = 8267;
                itemDef.femaleEquip1 = 8267;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 186;
                itemDef.rotationX = 126;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 85;
                break;


            case 23671:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Crescent blade";
                itemDef.modelID = 8288;
                itemDef.maleEquip1 = 8288;
                itemDef.femaleEquip1 = 8288;
                itemDef.modelZoom = 1400;
                itemDef.rotationY = 682;
                itemDef.rotationX = 1266;
                itemDef.rotationZ = 1279;
                itemDef.modelOffsetX = 45;
                itemDef.modelOffsetY = -83;
                break;

            case 23672:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Crescent blade offhand";
                itemDef.modelID = 8312;
                itemDef.maleEquip1 = 8312;
                itemDef.femaleEquip1 = 8312;
                itemDef.modelZoom = 1488;
                itemDef.rotationY = 694;
                itemDef.rotationX = 1269;
                itemDef.rotationZ = 222;
                itemDef.modelOffsetX = -43;
                itemDef.modelOffsetY = 18;
                break;

            case 23673:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Dusk warbow";
                itemDef.modelID = 8268;
                itemDef.maleEquip1 = 8268;
                itemDef.femaleEquip1 = 8268;
                itemDef.modelZoom = 3096;
                itemDef.rotationY = 1604;
                itemDef.rotationX = 1277;
                itemDef.rotationZ = 282;
                itemDef.modelOffsetX = -40;
                itemDef.modelOffsetY = 44;
                break;

            case 23674:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Eventide ripper";
                itemDef.modelID = 8270;
                itemDef.maleEquip1 = 8270;
                itemDef.femaleEquip1 = 8270;
                itemDef.modelZoom = 848;
                itemDef.rotationY = 458;
                itemDef.rotationX = 1280;
                itemDef.rotationZ = 1800;
                itemDef.modelOffsetX = 20;
                itemDef.modelOffsetY = -31;
                break;

            case 23675:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Eventide ripper offhand";
                itemDef.modelID = 8272;
                itemDef.maleEquip1 = 8272;
                itemDef.femaleEquip1 = 8272;
                itemDef.modelZoom = 464;
                itemDef.rotationY = 492;
                itemDef.rotationX = 1347;
                itemDef.rotationZ = 1200;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 1;
                break;

            case 23676:
                itemDef.rs3 = true;
                itemDef.slot = 3;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.name = "Sundown dagger";
                itemDef.modelID = 8275;
                itemDef.maleEquip1 = 8275;
                itemDef.femaleEquip1 = 8275;
                itemDef.modelZoom = 976;
                itemDef.rotationY = 1355;
                itemDef.rotationX = 1823;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 12;
                itemDef.modelOffsetY = 30;
                break;


            case 23677:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Assassin hat";
                itemDef.modelID = 71907;
                itemDef.maleEquip1 = 71816;
                itemDef.femaleEquip1 = 71870;
                itemDef.modelZoom = 461;
                itemDef.rotationY = 298;
                itemDef.rotationX = 42;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 26;
                break;

            case 23678:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Assassin chest";
                itemDef.modelID = 71916;
                itemDef.maleEquip1 = 71832;
                itemDef.femaleEquip1 = 71893;
                itemDef.modelZoom = 1447;
                itemDef.rotationY = 539;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 0;
                break;

            case 23679:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Assassin legs";
                itemDef.modelID = 71910;
                itemDef.maleEquip1 = 71821;
                itemDef.femaleEquip1 = 71876;
                itemDef.modelZoom = 1742;
                itemDef.rotationY = 526;
                itemDef.rotationX = 229;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 8;
                itemDef.modelOffsetY = 7;
                break;

            case 23680:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Assassin boots";
                itemDef.modelID = 71922;
                itemDef.maleEquip1 = 71804;
                itemDef.femaleEquip1 = 71862;
                itemDef.modelZoom = 615;
                itemDef.rotationY = 173;
                itemDef.rotationX = 2039;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -7;
                break;

            case 23681:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Assassin gloves";
                itemDef.modelID = 71809;
                itemDef.maleEquip1 = 71809;
                itemDef.femaleEquip1 = 71864;
                itemDef.modelZoom = 658;
                itemDef.rotationY = 485;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -4;
                break;

            case 23682:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 1;
                itemDef.name = "Assassin cape";
                itemDef.modelID = 58;
                itemDef.maleEquip1 = 58;
                itemDef.femaleEquip1 = 58;
                itemDef.modelZoom = 1200;
                itemDef.rotationY = 88;
                itemDef.rotationX = 1980;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -2;
                break;


            case 23683:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Superior Death Lotus hood";
                itemDef.modelID = 81365;
                itemDef.maleEquip1 = 82362;
                itemDef.femaleEquip1 = 82422;
                itemDef.modelZoom = 720;
                itemDef.rotationY = 57;
                itemDef.rotationX = 2033;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23684:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Superior Death Lotus chestplate";
                itemDef.modelID = 81361;
                itemDef.maleEquip1 = 82415;
                itemDef.femaleEquip1 = 82467;
                itemDef.modelZoom = 1579;
                itemDef.rotationY = 500;
                itemDef.rotationX = 2046;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -1;
                break;

            case 23685:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Superior Death Lotus chaps";
                itemDef.modelID = 81367;
                itemDef.maleEquip1 = 82367;
                itemDef.femaleEquip1 = 82426;
                itemDef.modelZoom = 1645;
                itemDef.rotationY = 500;
                itemDef.rotationX = 54;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -1;
                break;

            case 23686:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Superior Death Lotus tekoh";
                itemDef.modelID = 81363;
                itemDef.maleEquip1 = 82359;
                itemDef.femaleEquip1 = 82359;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 345;
                itemDef.rotationX = 1628;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                break;
            case 23687:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Superior Death Lotus tabi";
                itemDef.modelID = 81369;
                itemDef.maleEquip1 = 82356;
                itemDef.femaleEquip1 = 82356;
                itemDef.modelZoom = 720;
                itemDef.rotationY = 315;
                itemDef.rotationX = 1682;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 18;
                break;


            case 23688:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Coronet of Spring";
                itemDef.modelID = 86903;
                itemDef.maleEquip1 = 86881;
                itemDef.femaleEquip1 = 86889;
                itemDef.modelZoom = 420;
                itemDef.rotationY = 228;
                itemDef.rotationX = 102;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -38;
                itemDef.maleWieldY = -1;
                break;

            case 23689:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 1;
                itemDef.name = "Cloak of Spring";
                itemDef.modelID = 95812;
                itemDef.maleEquip1 = 95799;
                itemDef.femaleEquip1 = 95799;
                itemDef.modelZoom = 1974;
                itemDef.rotationY = 368;
                itemDef.rotationX = 228;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 4;
                break;


            case 23690:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Bork full helm";
                itemDef.modelID = 73572;
                itemDef.maleEquip1 = 71718;
                itemDef.femaleEquip1 = 73181;
                itemDef.modelZoom = 720;
                itemDef.rotationY = 24;
                itemDef.rotationX = 168;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                
                break;


            case 23691:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Bork platebody";
                itemDef.modelID = 73574;
                itemDef.maleEquip1 = 71721;
                itemDef.femaleEquip1 = 73183;
                itemDef.modelZoom = 1600;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                
                break;
            case 23692:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Bork platelegs";
                itemDef.modelID = 73573;
                itemDef.maleEquip1 = 71719;
                itemDef.femaleEquip1 = 73182;
                itemDef.modelZoom = 1800;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                
                break;


            case 23693:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Bork gauntlets";
                itemDef.modelID = 73570;
                itemDef.maleEquip1 = 71717;
                itemDef.femaleEquip1 = 71717;
                itemDef.modelZoom = 702;
                itemDef.rotationY = 353;
                itemDef.rotationX = 204;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 3;
                
                break;

            case 23694:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Bork boots";
                itemDef.modelID = 73569;
                itemDef.maleEquip1 = 71715;
                itemDef.femaleEquip1 = 71715;
                itemDef.modelZoom = 900;
                itemDef.rotationY = 207;
                itemDef.rotationX = 159;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 7;
                itemDef.modelOffsetY = -12;
                
                break;

            case 23695:
                itemDef.copyItem(5074);
                itemDef.name = "Bork Pet";
                itemDef.modelID = MobDefinition.get(7134).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                break;


            case 23696:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Assassin claws";
                itemDef.modelID = 8270;
                itemDef.maleEquip1 = 8270;
                itemDef.maleEquip2 = 8272;
                itemDef.femaleEquip1 = 8270;
                itemDef.femaleEquip2 = 8272;
                itemDef.modelZoom = 848;
                itemDef.rotationY = 458;
                itemDef.rotationX = 1280;
                itemDef.rotationZ = 1800;
                itemDef.modelOffsetX = 20;
                itemDef.modelOffsetY = -31;
                break;


            case 23697:
                itemDef.copyItem(5074);
                itemDef.name = "Ember Golem Pet";
                itemDef.modelID = MobDefinition.get(9897).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.modelZoom = 805;
                itemDef.rotationX = 60;
                itemDef.rotationY = 172;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;


            case 23698:
                itemDef.copyItem(23666);
                itemDef.rs3 = true;
                itemDef.name = "Faceless Assassin Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23699:
                itemDef.copyItem(23698);
                itemDef.noteTemplate = 23698;
                itemDef.note = 23390;
                itemDef.name = "Faceless Assassin Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23700:
                itemDef.copyItem(23684);
                itemDef.rs3 = true;
                itemDef.name = "Death Lotus Costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23701:
                itemDef.copyItem(23700);
                itemDef.noteTemplate = 23700;
                itemDef.note = 23390;
                itemDef.name = "Death Lotus Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23702:
                itemDef.copyItem(23573);
                itemDef.rs3 = true;
                itemDef.name = "Shadow Hunter costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23703:
                itemDef.copyItem(23702);
                itemDef.noteTemplate = 23702;
                itemDef.note = 23390;
                itemDef.name = "Shadow Hunter Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 23704:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 12;
                itemDef.name = "Ember Ring";
                itemDef.modelID = 84078;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 658;
                itemDef.rotationY = 283;
                itemDef.rotationX = 350;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 21;
                itemDef.oldColors = new int[]{
                        23737, 23747, 24269, 24289, 24264
                };
                itemDef.newColors = new int[]{
                        62132, 62132, 62132, 62132, 62132
                };
                break;

            case 23705:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 13;
                itemDef.name = "Assassin's quiver";
                itemDef.modelID = 101403;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 700;
                itemDef.rotationY = 299;
                itemDef.rotationX = 114;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -4;
                itemDef.modelOffsetY = 14;
                break;

            case 23706:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Gemstone kaseki";
                itemDef.modelID = 101635;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 381;
                itemDef.rotationX = 1955;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23707:
                itemDef.copyItem(14808);
                itemDef.actions = new String[]{"Open", null, null, null, null};
                itemDef.colorChange = new double[]{0.7, 0.6, 0.2};
                itemDef.name = "Assassin Costume Scroll";
                break;
            case 23708:
                itemDef.copyItem(18768);
                itemDef.actions = new String[]{"Open", null, null, null, null};
                itemDef.name = "Spring Box";
                itemDef.oldColors = new int[]{
                        2999, 926, 22410
                };
                itemDef.newColors = new int[]{
                        59365, 22476, 22476
                };
                break;
            case 23709:
                itemDef.copyItem(7995);
                itemDef.name = "Owner cape";
                itemDef.oldColors = new int[]{57};
                itemDef.newColors = new int[]{52};
                break;
            case 11027:
                itemDef.actions = new String[]{"Open", null, "Open-All", null, null};
                break;


            case 23710:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Eggsterminator";
                itemDef.modelID = 68949;
                itemDef.maleEquip1 = 68966;
                itemDef.femaleEquip1 = 68966;
                itemDef.modelZoom = 1053;
                itemDef.rotationY = 0;
                itemDef.rotationX = 1791;
                itemDef.rotationZ = 13;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWieldZ = -10;
                break;

            case 23711:
                itemDef.copyItem(5074);
                itemDef.name = "Easter bunny pet";
                itemDef.modelID = MobDefinition.get(13651).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.modelZoom = 660;
                itemDef.rotationX = 60;
                itemDef.rotationY = 172;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -11;
                break;

            case 23712:
                itemDef.copyItem(22111);
                itemDef.name = "Owner Aura (u)";
                break;

            case 23713:
                itemDef.copyItem(23351);
                itemDef.name = "Owner Amulet (u)";
                itemDef.maleWieldY = 3;
                itemDef.maleWieldZ = 3;
                itemDef.oldColors = new int[]{25, 6, 52};
                itemDef.newColors = new int[]{40, 25, 55};
                break;

            case 23714:
                itemDef.copyItem(23352);
                itemDef.name = "Owner Ring (u)";
                itemDef.oldColors = new int[]{25, 6, 0, 52};
                itemDef.newColors = new int[]{40, 25, 10, 55};
                break;

            case 23715:
                itemDef.copyItem(23353);
                itemDef.modelID = 62003;
                itemDef.maleEquip1 = 62004;
                itemDef.femaleEquip1 = 62004;
                itemDef.maleModelScale = 4;
                itemDef.maleWieldY = 4;
                itemDef.maleWieldZ = -1;
                itemDef.name = "Owner Bracelet (u)";
                itemDef.oldColors = new int[]{25, 6, 52};
                itemDef.newColors = new int[]{40, 25, 55};
                break;


            case 23716:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Smack crossbow";
                itemDef.modelID = 39645;
                itemDef.maleEquip1 = 39163;
                itemDef.femaleEquip1 = 39163;
                itemDef.modelZoom = 848;
                itemDef.rotationY = 267;
                itemDef.rotationX = 54;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 24;
                break;

            case 23717:
                itemDef.copyItem(7995);
                itemDef.name = "Angel Cape";
                itemDef.oldColors = new int[]{57};
                itemDef.newColors = new int[]{82};
                break;

            case 23718:
                itemDef.copyItem(17658);
                itemDef.name = "Teddy Sidekick";
                itemDef.modelZoom = 1262;
                itemDef.rotationX = 1791;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -4;
                itemDef.modelOffsetY = 4;
                break;

            case 23719:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "TM30's Sword";
                itemDef.modelID = 107563;
                itemDef.maleEquip1 = 107542;
                itemDef.femaleEquip1 = 107542;
                itemDef.modelZoom = 2434;
                itemDef.rotationY = 500;
                itemDef.rotationX = 768;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 6;
                itemDef.modelOffsetY = 0;
                break;


            case 23720:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Tanzanite helm";
                itemDef.modelID = 8098;
                itemDef.maleEquip1 = 117168;
                itemDef.femaleEquip1 = 117163;
                itemDef.modelZoom = 900;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23721:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Tanzanite body";
                itemDef.modelID = 8100;
                itemDef.maleEquip1 = 117170;
                itemDef.femaleEquip1 = 117165;
                itemDef.modelZoom = 1600;
                itemDef.rotationY = 520;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;


            case 23722:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Tanzanite legs";
                itemDef.modelID = 8099;
                itemDef.maleEquip1 = 117169;
                itemDef.femaleEquip1 = 117164;
                itemDef.modelZoom = 1900;
                itemDef.rotationY = 520;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23723:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Tanzanite gloves";
                itemDef.modelID = 8097;
                itemDef.maleEquip1 = 117167;
                itemDef.femaleEquip1 = 117167;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 520;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23724:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Tanzanite boots";
                itemDef.modelID = 8096;
                itemDef.maleEquip1 = 117166;
                itemDef.femaleEquip1 = 117166;
                itemDef.modelZoom = 850;
                itemDef.rotationY = 100;
                itemDef.rotationX = 260;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23725:
                itemDef.copyItem(21780);
                itemDef.name = "Jinx gloves";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.oldColors = new int[]{40};
                itemDef.newColors = new int[]{52};
                itemDef.modelZoom = 639;
                itemDef.rotationX = 1737;
                itemDef.rotationY = 347;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = 4;
                break;

            case 23726:
                itemDef.copyItem(5074);
                itemDef.name = "Jinx Pet";
                itemDef.modelID = MobDefinition.get(10120).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.modelZoom = 805;
                itemDef.rotationX = 60;
                itemDef.rotationY = 172;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;

            case 23729:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Sacrificial helm";
                itemDef.modelID = 94008;
                itemDef.maleEquip1 = 93916;
                itemDef.femaleEquip1 = 93925;
                itemDef.modelZoom = 758;
                itemDef.rotationY = 109;
                itemDef.rotationX = 331;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -3;
                break;


            case 23730:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Sacrificial body";
                itemDef.modelID = 94006;
                itemDef.maleEquip1 = 93919;
                itemDef.femaleEquip1 = 93928;
                itemDef.modelZoom = 1398;
                itemDef.rotationY = 358;
                itemDef.rotationX = 10;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 36;
                break;


            case 23731:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Sacrificial chaps";
                itemDef.modelID = 94005;
                itemDef.maleEquip1 = 93917;
                itemDef.femaleEquip1 = 93926;
                itemDef.modelZoom = 1654;
                itemDef.rotationY = 448;
                itemDef.rotationX = 271;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;
            case 23732:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Sacrificial gloves";
                itemDef.modelID = 94002;
                itemDef.maleEquip1 = 93915;
                itemDef.femaleEquip1 = 93915;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 498;
                itemDef.rotationX = 256;
                itemDef.rotationZ = 40;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 3;
                break;

            case 23733:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Sacrificial boots";
                itemDef.modelID = 94003;
                itemDef.maleEquip1 = 93914;
                itemDef.femaleEquip1 = 93924;
                itemDef.modelZoom = 855;
                itemDef.rotationY = 229;
                itemDef.rotationX = 135;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -42;
                break;

            case 23734:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 1;
                itemDef.name = "Sacrificial cape";
                itemDef.modelID = 94001;
                itemDef.maleEquip1 = 93920;
                itemDef.femaleEquip1 = 93920;
                itemDef.modelZoom = 2166;
                itemDef.rotationY = 274;
                itemDef.rotationX = 1239;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;

            case 23735:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 2;
                itemDef.name = "Sacrificial amulet";
                itemDef.modelID = 94004;
                itemDef.maleEquip1 = 93918;
                itemDef.femaleEquip1 = 93927;
                itemDef.modelZoom = 758;
                itemDef.rotationY = 373;
                itemDef.rotationX = 256;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -9;
                break;


            case 23736:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Celestial energy";
                itemDef.modelID = 100246;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 78;
                itemDef.rotationX = 1898;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23737:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Celestial bracelet";
                itemDef.modelID = 114198;
                itemDef.maleEquip1 = 114185;
                itemDef.femaleEquip1 = 114185;
                itemDef.modelZoom = 330;
                itemDef.rotationY = 148;
                itemDef.rotationX = 233;
                itemDef.rotationZ = 1919;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -2;
                itemDef.oldColors = new int[]{9152, -22464,};
                itemDef.newColors = new int[]{36683, 46003,};
                break;

            case 23738:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.name = "Celestial aura";
                itemDef.modelID = 101856;
                itemDef.maleEquip1 = 101856;
                itemDef.femaleEquip1 = 101856;
                itemDef.modelZoom = 2736;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 55;
                itemDef.oldColors = new int[]{-22502, -25537, 4518, -31165, 2477, 3480, -22446, -22506, 3478, 3609, -20426, -26537, -22460, 4507, -22491, -27556, 3630, 4524, 2580, -22465, -31157, -6063, 4517, 5525, -16351, -30626, 4510, -23438, -24507, 5551, -26542, 5698, 4525, -31649, -31035, -24504, 4504, 5701, -22488, 2734, -22450, -7134, 127, -20414, -21170, -19398, 5524, 3471, -22457, -7120, 3477, 2619, -23473, -20421, 4520, -24488, -22449, 3475, 2730, 5566, 2604, 5702, -29607, 5528, 3482, -29877, -25515, -24503, 5530, -29599, -22481, -23482, 5703, 3474, -31283, -17360, 4508, -24490, 4629, -22451, 4501, -19379, 3472, 3476, -21470, 3615, -27431, -22445, 3486, -23485, -21430, 2467, -23475, 4503, -26541, 5553, 3483, -6082, -31285, 3488, 3613, -24498, -26530, -31289, -26554, -23463, -21276, -30625, -31411, -16288, 4506, -28582, -22486, -28580, -22459, -15262, 3485, -7102, -22438, -29597, -22441, -24502, 3622, 3602, 4502, 5704, 2461, 4509, 5565, -19411, 4505, -26527, -19365, 4499, -30642, -31149, 5554, -22448, -30623, -28575, 2746, 3484, 3612, 5835, 5562, -30634, -20399, -25521, -22463, -22436,};
                itemDef.newColors = new int[]{40895, -25537, 40895, -31165, 45759, 46003, -22446, 45759, 46003, 45759, -20426, -26537, -22460, 46003, -22491, -27556, 46003, 46003, 46003, -22465, -31157, -6063, 46003, 46003, 46003, -30626, 46003, -23438, -24507, 46003, -26542, 46003, 46003, -31649, -31035, -24504, 46003, 46003, -22488, 45759, -22450, -7134, 127, -20414, -21170, -19398, 45759, 45759, -22457, -7120, 45759, 46003, -23473, -20421, 46003, -24488, -22449, 45759, 46003, 46003, 45759, 46003, -29607, 45759, 46003, -29877, -25515, -24503, 46003, -29599, -22481, -23482, 45759, 46003, -31283, -17360, 46003, -24490, 46003, -22451, 45759, -19379, 46003, 46003, -21470, 46003, -27431, -22445, 46003, -23485, -21430, 45759, -23475, 4503, -26541, 46003, 45759, -6082, -31285, 45759, 46003, -24498, -26530, -31289, -26554, -23463, -21276, -30625, -31411, -16288, 46003, -28582, -22486, -28580, -22459, -15262, 46003, -7102, -22438, -29597, -22441, -24502, 46003, 45644, 46003, 45759, 46003, 45759, 46003, -19411, 45759, -26527, -19365, 45759, -30642, -31149, 46003, -22448, -30623, -28575, 45759, 46003, 45759, 46003, 45759, -30634, -20399, -25521, -22463, -22436,};
                break;


            case 23739:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 1;
                itemDef.name = "Celestial cape";
                itemDef.modelID = 65270;
                itemDef.maleEquip1 = 65297;
                itemDef.femaleEquip1 = 65316;
                itemDef.modelZoom = 1316;
                itemDef.rotationY = 252;
                itemDef.rotationX = 1020;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 24;
                itemDef.oldColors = new int[]{-322, -2534, 961, -336, -2544, 5441, 5708, -61, 762, -2513, -911, -350, -2518, 20287, -2542, 5706, -2522, -2525, 5683, 5437, -897, -2527, 954, 383, 5714, -2244, 5694, 5759, 5669, -2541, 5463, 5481, -2538, -2537, 767, 945,};
                itemDef.newColors = new int[]{33624, 46003, 46003, 36554, 46003, 40895, 46003, 127, 127, 36692, 46003, 36550, 46003, 127, 46003, 46003, 127, 36683, 46003, 40895, 127, 33464, 46003, 46003, 46003, 46003, 127, 76, 76, 40895, 127, 127, 35677, 40895, 127, 127,};
                break;


            case 23740:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 2;
                itemDef.name = "Celestial necklace";
                itemDef.modelID = 14199;
                itemDef.maleEquip1 = 20537;
                itemDef.femaleEquip1 = 18919;
                itemDef.modelZoom = 500;
                itemDef.rotationY = 227;
                itemDef.rotationX = 115;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -31;
                itemDef.oldColors = new int[]{9155, -23410, -24416, -23496, -24426, -24498, -23401, -23523, -24509, -25578, -24477, -23493, -24499, -24489, -23513, 6592,};
                itemDef.newColors = new int[]{46003, -23410, -24416, 36799, -24426, 34644, -23401, -23523, 40895, -25578, 36591, 34662, 35775, 34653, -23513, 46003,};
                break;

            case 23741:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 12;
                itemDef.name = "Celestial ring";
                itemDef.modelID = 86474;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 286;
                itemDef.rotationX = 42;
                itemDef.rotationY = 194;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                itemDef.oldColors = new int[]{23370, 1, 19, 49, 9, 25,};
                itemDef.newColors = new int[]{34653, 36799, 36799, 38, 34654, 19,};
                break;

            case 23742:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 13;
                itemDef.name = "Celestial quiver";
                itemDef.modelID = 101404;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 700;
                itemDef.rotationY = 299;
                itemDef.rotationX = 114;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -4;
                itemDef.modelOffsetY = 14;
                itemDef.oldColors = new int[]{70, 19782, 19767, -28720, -28987, 106, 19, 19795, -22237, 80, 63, 15, -28988, 90, 88, 81, 74, 89, 13468, -29142, 97, 19779, 91, 17, -29124, 13483, -22248,};
                itemDef.newColors = new int[]{70, 32612, 34632, -28720, -28987, 106, 25, 34650, -22237, 80, 63, 15, -28988, 46003, 127, 127, 127, 32586, 46003, 46003, 46003, 33606, 91, 12, 34639, 32590, -22248,};
                break;


            case 23743:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Celestial head";
                itemDef.modelID = 111928;
                itemDef.maleEquip1 = 111928;
                itemDef.femaleEquip1 = 111923;
                itemDef.modelZoom = 629;
                itemDef.rotationY = 21;
                itemDef.rotationX = 1955;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 87;
                break;

            case 23744:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Celestial body";
                itemDef.modelID = 111930;
                itemDef.maleEquip1 = 111930;
                itemDef.femaleEquip1 = 111925;
                itemDef.modelZoom = 1600;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 46;
                break;

            case 23745:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Celestial legs";
                itemDef.modelID = 111929;
                itemDef.maleEquip1 = 111929;
                itemDef.femaleEquip1 = 111924;
                itemDef.modelZoom = 1800;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 8;
                break;

            case 23746:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Master camouflage hands";
                itemDef.modelID = 111927;
                itemDef.maleEquip1 = 111927;
                itemDef.femaleEquip1 = 111927;
                itemDef.modelZoom = 700;
                itemDef.rotationY = 87;
                itemDef.rotationX = 435;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 57;
                break;

            case 23747:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Master camouflage feet";
                itemDef.modelID = 111926;
                itemDef.maleEquip1 = 111926;
                itemDef.femaleEquip1 = 111926;
                itemDef.modelZoom = 750;
                itemDef.rotationY = 70;
                itemDef.rotationX = 1920;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -5;
                break;


            case 23748:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Celestial Ore";
                itemDef.modelID = 100228;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 859;
                itemDef.rotationY = 143;
                itemDef.rotationX = 402;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 0;
                itemDef.oldColors = new int[]{322, 326, -30130, 271, -30158, 305, -30178, 283, 294, 279, 274, 300, 317, 291, -30152, 310,};
                itemDef.newColors = new int[]{45759, 45759, 36700, 46003, 35666, 45759, 34659, 45759, 45759, 45759, 45759, 46003, 45759, 45759, 33618, 45759,};
                break;

            case 23749:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Celestial bar";
                itemDef.modelID = 73706;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 868;
                itemDef.rotationX = 1180;
                itemDef.rotationY = 196;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -25;
                itemDef.oldColors = new int[]{127, 31801, 31845, -32714,};
                itemDef.newColors = new int[]{45759, 36705, 34667, 46003,};
                break;

            case 23750:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Celestial mushroom";
                itemDef.modelID = 13432;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 391;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 5;
                itemDef.oldColors = new int[]{27805, 19499, 19493, 27815, 19489, 27787, 19534, 27835, 19524, 4253, 27795, 4241, 19505, 27803, 19515, 19538, 19483, 27790, 19511, 4259, 4235, 27820, 27810, 27825, 19521, 27797, 19487, 4247, 4265, 19532, 19495, 19481, 27800, 27785,};
                itemDef.newColors = new int[]{40895, 127, 40895, 40895, 127, 40895, 127, 127, 127, 40895, 40895, 40895, 127, 40895, 127, 127, 127, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895, 40895,};
                break;

            case 23751:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Celestial scales";
                itemDef.modelID = 32407;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 724;
                itemDef.rotationY = 532;
                itemDef.rotationX = 478;
                itemDef.rotationZ = 148;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 4;
                itemDef.oldColors = new int[]{7367, 7232, 7378, 7226, 7371, 7386, 7228, 7218,};
                itemDef.newColors = new int[]{45759, 34636, 34646, 45759, 33619, 45759, 34659, 45759,};
                break;


            case 23752:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Activate", null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Isle of the Gods DR Booster";
                itemDef.modelID = 100098;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 425;
                itemDef.rotationY = 260;
                itemDef.rotationX = 2047;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -44;
                break;

            case 23753:
                itemDef.rs3 = true;
                itemDef.stackable = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "SOD booster";
                itemDef.modelID = 100097;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 400;
                itemDef.rotationY = 314;
                itemDef.rotationX = 159;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.colorChange = new double[]{0.4, 0.1, 0.9};
                break;

            case 23754:
                itemDef.rs3 = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Slayer VIP Coupon";
                itemDef.modelID = 55304;
                itemDef.maleEquip1 = 55304;
                itemDef.femaleEquip1 = 55304;
                itemDef.modelZoom = 634;
                itemDef.rotationY = 332;
                itemDef.rotationX = 144;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 0;
                break;

            case 23755:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Unlock", null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Rammernaut scroll";
                itemDef.modelID = 2774;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = 255;
                itemDef.rotationX = 1719;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 50;
                itemDef.oldColors = new int[]{5440, 5452, 1938, 5427,};
                itemDef.newColors = new int[]{40895, 38, 40895, 37707,};
                break;

            case 23758:
                itemDef.copyItem(23558);
                itemDef.rs3 = true;
                itemDef.name = "Rammernaut boots";
                itemDef.oldColors = new int[]{667, -22256, 663, -30165, -19067, -32675, -30173, -22261, 661, -19066, -30193, 657, -30149, 659, -30181, -32693, -30157, -22265, -22262, 653, 647,};
                itemDef.newColors = new int[]{12, 101, 12, 40895, 101, 35643, 40895, 114, 38, 114, 101, 50, 40652, 12, 40665, 40895, 40652, 101, 101, 12, 12,};
                break;

            case 23759:
                itemDef.rs3 = true;
                itemDef.stackable = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Rammernaut flakes";
                itemDef.actions[2] = "Disassemble";
                itemDef.actions[3] = "Disassemble All";
                itemDef.modelID = 43204;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 356;
                itemDef.rotationX = 148;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 18;
                itemDef.oldColors = new int[]{13392, 13412,};
                itemDef.newColors = new int[]{37823, 50,};
                break;

            case 23760:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Warsuit hat";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 78591;
                itemDef.femaleEquip1 = 78612;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 512;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                itemDef.oldColors = new int[]{16311, 16323, 16347, -10304};
                itemDef.newColors = new int[]{20, 40, 60, 15};
                break;

            case 23761:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Warsuit body";
                itemDef.modelID = 78600;
                itemDef.maleEquip1 = 78600;
                itemDef.femaleEquip1 = 78623;
                itemDef.modelZoom = 1532;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 38;
                itemDef.oldColors = new int[]{16311, 16323, 16347};
                itemDef.newColors = new int[]{20, 40, 60};
                break;


            case 23762:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Warsuit legs";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 78594;
                itemDef.femaleEquip1 = 78620;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 512;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                itemDef.oldColors = new int[]{16311, 16323, 16347};
                itemDef.newColors = new int[]{20, 40, 60};
                break;

            case 23763:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Warsuit gloves";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 78585;
                itemDef.femaleEquip1 = 78585;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 512;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                itemDef.oldColors = new int[]{16311, 16323, 16347};
                itemDef.newColors = new int[]{20, 40, 60};
                break;

            case 23764:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Warsuit boots";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 78583;
                itemDef.femaleEquip1 = 78583;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 512;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                itemDef.oldColors = new int[]{16311, 16323, 16347};
                itemDef.newColors = new int[]{20, 40, 60};
                break;


            case 23765:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Warsuit hat";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 83297;
                itemDef.femaleEquip1 = 83322;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 512;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;

            case 23766:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Warsuit body";
                itemDef.modelID = 83310;
                itemDef.maleEquip1 = 83310;
                itemDef.femaleEquip1 = 83336;
                itemDef.modelZoom = 1574;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -10;
                break;

            case 23767:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Warsuit legs";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 83300;
                itemDef.femaleEquip1 = 83324;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 512;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;

            case 23768:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Warsuit gloves";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 83291;
                itemDef.femaleEquip1 = 83291;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 512;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;

            case 23769:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Warsuit boots";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 83284;
                itemDef.femaleEquip1 = 83284;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 512;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;

            case 23770:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 1;
                itemDef.name = "Warsuit cape";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 83315;
                itemDef.femaleEquip1 = 83315;
                itemDef.modelZoom = 600;
                itemDef.rotationY = 512;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;

            case 23771:
                itemDef.copyItem(23761);
                itemDef.rs3 = true;
                itemDef.name = "Battlegear costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.oldColors = new int[]{16311, 16323, 16347};
                itemDef.newColors = new int[]{20, 40, 60};
                break;
            case 23772:
                itemDef.copyItem(23771);
                itemDef.noteTemplate = 23771;
                itemDef.note = 23390;
                itemDef.name = "Battlegear Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;

            case 23773:
                itemDef.copyItem(23766);
                itemDef.rs3 = true;
                itemDef.name = "Godcrusher costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23774:
                itemDef.copyItem(23773);
                itemDef.noteTemplate = 23773;
                itemDef.note = 23390;
                itemDef.name = "Godcrusher Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 23775:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 2;
                itemDef.name = "Kril's Necklace";
                itemDef.modelID = 72703;
                itemDef.maleEquip1 = 72086;
                itemDef.femaleEquip1 = 72169;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 318;
                itemDef.rotationX = 129;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 46;
                itemDef.oldColors = new int[]{7023, 7033, -26852, 7034, -28059, -28052, 7002, -28051, -26837, -28071, 6936, -26853, 21519, -26842, -26838, -28070, -26784, -26785, 7001, 6143, -26843, 7022, 21518, -28080, 6142, -28058, -26827, -28079, -26826,};
                itemDef.newColors = new int[]{0, 0, -26852, 0, 61350, 61350, 0, 61350, 61350, 61350, 6936, -26853, 21519, -26842, -26838, 61350, 61350, 61350, 0, 0, -26843, 0, 21518, 61350, 12, 61106, 61106, 54169, 61106,};
                break;


            case 23776:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Open", null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Angelic case";
                itemDef.modelID = 117716;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 3064;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 0;
                
                break;
            case 23777:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Divine charge";
                itemDef.modelID = 109757;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 1488;
                itemDef.rotationY = 114;
                itemDef.rotationX = 1895;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23778:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Angelic dust";
                itemDef.modelID = 100244;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 720;
                itemDef.rotationY = 129;
                itemDef.rotationX = 1850;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                break;


            case 23779:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Drink", null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Angelic potion";
                itemDef.modelID = 61732;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -1;
                itemDef.oldColors = new int[]{
                        -31821,
                        676
                        , 680
                        , 650
                        , 674
                        , 656
                        , 679
                        , 673
                        , 663
                        , 668
                        , 681
                        , 667

                };
                itemDef.newColors = new int[]{
                        -30129,
                        127
                        , 127
                        , 127
                        , 127
                        , 127
                        , 127
                        , 127
                        , 127
                        , 127
                        , 127
                        , 127
                };
                break;


            case 23780:
                itemDef.copyItem(21511);
                itemDef.name = "Case key";
                itemDef.modelZoom = 909;
                itemDef.rotationX = 1;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 6;
                break;


            case 23781:
                itemDef.copyItem(4442);
                itemDef.name = "x1.75 Divine scroll";
                itemDef.colorChange = new double[]{0.8, 0.8, 1.2};
                itemDef.modelZoom = 1428;
                itemDef.rotationX = 1262;
                itemDef.rotationY = 533;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 17;
                itemDef.modelOffsetY = 19;
                break;

            case 23782:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Open", null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Divine case";
                itemDef.modelID = 68374;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 3064;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 0;
                
                break;


            case 23783:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Open", null, null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Crystal box";
                itemDef.modelID = 100240;
                itemDef.maleEquip1 = 100172;
                itemDef.femaleEquip1 = 100172;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 186;
                itemDef.rotationX = 1835;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -10;
                break;

            case 23784:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Combine", null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Crystal seed";
                itemDef.modelID = 100231;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;


            case 23785:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Crystal bow";
                itemDef.modelID = 99069;
                itemDef.maleEquip1 = 97324;
                itemDef.femaleEquip1 = 97324;
                itemDef.modelZoom = 1744;
                itemDef.rotationY = 239;
                itemDef.rotationX = 1988;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 13;
                itemDef.modelOffsetY = 71;
                break;


            case 23786:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Crystal wand";
                itemDef.modelID = 99079;
                itemDef.maleEquip1 = 98991;
                itemDef.femaleEquip1 = 98991;
                itemDef.modelZoom = 1366;
                itemDef.rotationX = 0;
                itemDef.rotationY = 237;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 21;
                break;


            case 23787:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Crystal halberd";
                itemDef.modelID = 99072;
                itemDef.maleEquip1 = 97326;
                itemDef.femaleEquip1 = 97326;
                itemDef.modelZoom = 2540;
                itemDef.rotationY = 559;
                itemDef.rotationX = 12;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 6;
                itemDef.modelOffsetY = 12;
                break;

            case 23788:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 5;
                itemDef.name = "Crystal shield";
                itemDef.modelID = 99075;
                itemDef.maleEquip1 = 97327;
                itemDef.femaleEquip1 = 97327;
                itemDef.modelZoom = 1488;
                itemDef.rotationY = 333;
                itemDef.rotationX = 204;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;


            case 23789:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Crystal helm";
                itemDef.modelID = 105590;
                itemDef.maleEquip1 = 105579;
                itemDef.femaleEquip1 = 105571;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 81;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 5;
                break;

            case 23790:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Crystal body";
                itemDef.modelID = 105593;
                itemDef.maleEquip1 = 105581;
                itemDef.femaleEquip1 = 105573;
                itemDef.modelZoom = 1809;
                itemDef.rotationY = 660;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 1;
                break;

            case 23791:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Crystal legs";
                itemDef.modelID = 105591;
                itemDef.maleEquip1 = 105580;
                itemDef.femaleEquip1 = 105572;
                itemDef.modelZoom = 1690;
                itemDef.rotationY = 660;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 1;
                break;

            case 23792:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Crystal boots";
                itemDef.modelID = 105592;
                itemDef.maleEquip1 = 105577;
                itemDef.femaleEquip1 = 105577;
                itemDef.modelZoom = 855;
                itemDef.rotationY = 108;
                itemDef.rotationX = 1858;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -4;
                break;

            case 23793:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Crystal gloves";
                itemDef.modelID = 105589;
                itemDef.maleEquip1 = 105578;
                itemDef.femaleEquip1 = 105578;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 324;
                itemDef.rotationX = 123;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 7;
                break;


            case 23794:
                itemDef.actions = new String[]{"Exchange", null, "Exchange-X", null, null};
                itemDef.name = "1k Token";
                itemDef.modelID = 140016;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 550;
                itemDef.rotationX = 661;
                itemDef.rotationY = 364;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 8;
                itemDef.modelOffsetY = -10;
                itemDef.stackable = true;
                itemDef.oldColors = new int[]{
                        5813,
                        9139,
                        26006
                };
                itemDef.newColors = new int[]{
                        -32667,
                        -27566,
                        -27554
                };
                break;


            case 23795:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Arrav's helmet";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 102544;
                itemDef.femaleEquip1 = 102536;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23796:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Arrav's gorget";
                itemDef.modelID = 102546;
                itemDef.maleEquip1 = 102546;
                itemDef.femaleEquip1 = 102538;
                itemDef.modelZoom = 1283;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 45;
                break;

            case 23797:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Arrav's platelegs";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 102545;
                itemDef.femaleEquip1 = 102537;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23798:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Arrav's greaves";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 102542;
                itemDef.femaleEquip1 = 102542;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23799:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Arrav's wrist guards";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 102543;
                itemDef.femaleEquip1 = 102543;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23800:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Arrav sword";
                itemDef.modelID = 102893;
                itemDef.maleEquip1 = 102893;
                itemDef.femaleEquip1 = 102893;
                itemDef.modelZoom = 2300;
                itemDef.rotationX = 788;
                itemDef.rotationY = 703;
                itemDef.rotationZ = 1737;
                itemDef.modelOffsetX = 84;
                itemDef.modelOffsetY = 0;
                break;

            case 23801:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 5;
                itemDef.name = "Arrav shield";
                itemDef.modelID = 102957;
                itemDef.maleEquip1 = 102909;
                itemDef.femaleEquip1 = 102909;
                itemDef.modelZoom = 1488;
                itemDef.rotationY = 356;
                itemDef.rotationX = 183;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;


            case 23802:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Cursed arrav helmet";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 102547;
                itemDef.femaleEquip1 = 102539;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23803:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Cursed arrav gorget";
                itemDef.modelID = 102549;
                itemDef.maleEquip1 = 102549;
                itemDef.femaleEquip1 = 102541;
                itemDef.modelZoom = 1325;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 45;
                break;

            case 23804:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Cursed arrav platelegs";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 102548;
                itemDef.femaleEquip1 = 102540;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23805:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Cursed arrav greaves";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 102542;
                itemDef.femaleEquip1 = 102542;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23806:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Cursed arrav wrist guards";
                itemDef.modelID = 2521;
                itemDef.maleEquip1 = 102543;
                itemDef.femaleEquip1 = 102543;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23807:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 5;
                itemDef.name = "Cursed Arrav shield";
                itemDef.modelID = 102907;
                itemDef.maleEquip1 = 102907;
                itemDef.femaleEquip1 = 102907;
                itemDef.modelZoom = 1677;
                itemDef.rotationX = 771;
                itemDef.rotationY = 372;
                itemDef.rotationZ = 508;
                itemDef.modelOffsetX = -71;
                itemDef.modelOffsetY = -101;
                break;


            case 23808:
                itemDef.copyItem(23796);
                itemDef.rs3 = true;
                itemDef.name = "Arrav costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23809:
                itemDef.copyItem(23808);
                itemDef.noteTemplate = 23808;
                itemDef.note = 23390;
                itemDef.name = "Arrav Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;

            case 23810:
                itemDef.copyItem(23803);
                itemDef.rs3 = true;
                itemDef.name = "Cursed Arrav costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23811:
                itemDef.copyItem(23810);
                itemDef.noteTemplate = 23810;
                itemDef.note = 23390;
                itemDef.name = "Cursed Arrav Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 15112:
                itemDef.actions = new String[]{"Fill", null, null, null, null};
                break;
            case 432:
                itemDef.name = "Zemouregal key";
                break;

            case 23812:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{"Open", null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Money case";
                itemDef.modelID = 117716;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 3064;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 0;
                
                itemDef.colorChange = new double[]{1.7, 0.9, 0};
                break;
            case 23813:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Clownface head";
                itemDef.modelID = 90982;
                itemDef.maleEquip1 = 90982;
                itemDef.femaleEquip1 = 91030;
                itemDef.modelZoom = 681;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 80;
                break;

            case 23814:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Sam's hat";
                itemDef.modelID = 96526;
                itemDef.maleEquip1 = 96507;
                itemDef.femaleEquip1 = 96517;
                itemDef.modelZoom = 659;
                itemDef.rotationY = 80;
                itemDef.rotationX = 1893;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -3;
                break;


            case 23815:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Attuned crystal weapon seed";
                itemDef.modelID = 100234;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -4;
                break;

            case 23816:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Attuned crystal armour seed";
                itemDef.modelID = 100230;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23817:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Attuned crystal bow";
                itemDef.modelID = 100218;
                itemDef.maleEquip1 = 100154;
                itemDef.femaleEquip1 = 100154;
                itemDef.modelZoom = 1755;
                itemDef.rotationY = 218;
                itemDef.rotationX = 1994;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 10;
                itemDef.modelOffsetY = 3;
                break;

            case 23818:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Attuned crystal wand";
                itemDef.modelID = 100241;
                itemDef.maleEquip1 = 100188;
                itemDef.femaleEquip1 = 100188;
                itemDef.modelZoom = 1360;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1994;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 23819:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wield", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Attuned crystal halberd";
                itemDef.modelID = 100223;
                itemDef.maleEquip1 = 100160;
                itemDef.femaleEquip1 = 100160;
                itemDef.modelZoom = 1360;
                itemDef.rotationY = 123;
                itemDef.rotationX = 1913;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = -5;
                break;

            case 23820:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 5;
                itemDef.name = "Attuned crystal shield";
                itemDef.modelID = 100236;
                itemDef.maleEquip1 = 100182;
                itemDef.femaleEquip1 = 100182;
                itemDef.modelZoom = 1290;
                itemDef.rotationY = 225;
                itemDef.rotationX = 174;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;


            case 23821:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Attuned crystal helm";
                itemDef.modelID = 105594;
                itemDef.maleEquip1 = 105584;
                itemDef.femaleEquip1 = 105575;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 81;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 5;
                break;


            case 23822:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Attuned crystal body";
                itemDef.modelID = 105595;
                itemDef.maleEquip1 = 105585;
                itemDef.femaleEquip1 = 105576;
                itemDef.modelZoom = 1809;
                itemDef.rotationY = 660;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 1;
                break;

            case 23823:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Attuned crystal legs";
                itemDef.modelID = 105591;
                itemDef.maleEquip1 = 105583;
                itemDef.femaleEquip1 = 105574;
                itemDef.modelZoom = 1690;
                itemDef.rotationY = 660;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 1;
                break;


            case 23824:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Attuned crystal boots";
                itemDef.modelID = 105592;
                itemDef.maleEquip1 = 105577;
                itemDef.femaleEquip1 = 105577;
                itemDef.modelZoom = 855;
                itemDef.rotationY = 108;
                itemDef.rotationX = 1858;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -4;
                break;


            case 23825:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 9;
                itemDef.name = "Attuned crystal gloves";
                itemDef.modelID = 105589;
                itemDef.maleEquip1 = 105582;
                itemDef.femaleEquip1 = 105582;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 324;
                itemDef.rotationX = 123;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 7;
                break;

            case 23826:
                itemDef.copyItem(18768);
                itemDef.actions = new String[]{"Open", null, null, null, null};
                itemDef.name = "Summer Box";
                itemDef.oldColors = new int[]{
                        2999, 926, 22410
                };
                itemDef.newColors = new int[]{
                        3967, 25548, 25548
                };
                break;

            case 23827:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Coronet of Summer";
                itemDef.modelID = 86896;
                itemDef.maleEquip1 = 86882;
                itemDef.femaleEquip1 = 86888;
                itemDef.modelZoom = 473;
                itemDef.rotationY = 228;
                itemDef.rotationX = 99;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -32;
                itemDef.oldColors = new int[]{18256, 13781};
                itemDef.newColors = new int[]{3032, 3032,};
                break;

            case 23828:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 1;
                itemDef.name = "Cloak of Summer";
                itemDef.modelID = 95811;
                itemDef.maleEquip1 = 95798;
                itemDef.femaleEquip1 = 95798;
                itemDef.modelZoom = 1974;
                itemDef.rotationY = 368;
                itemDef.rotationX = 228;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.oldColors = new int[]{20813, 22831};
                itemDef.newColors = new int[]{3967, 3967,};
                itemDef.modelOffsetY = 4;
                break;

            case 23829:
                itemDef.copyItem(5074);
                itemDef.rs3 = true;
                itemDef.name = "Uncle Sam pet";
                itemDef.oldColors = new int[]{16311, 16323, 16335, 16347, -10304, 8741, 6798,};
                itemDef.newColors = new int[]{-21612, -21600, -21588, -21576, -22407, -22407, -22407,};
                itemDef.modelID = 44923;
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.modelZoom = 100;
                itemDef.rotationX = 60;
                itemDef.rotationY = 190;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 55;
                break;

            case 23830:
                itemDef.copyItem(5074);
                itemDef.name = "Muspah Pet";
                itemDef.modelID = MobDefinition.get(10023).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.modelZoom = 1000;
                itemDef.rotationX = 60;
                itemDef.rotationY = 172;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -25;
                break;

            case 23831:
                itemDef.copyItem(17728);
                itemDef.name = "Muspah Shield";
                itemDef.modelZoom = 2000;
                itemDef.rotationX = 0;
                itemDef.rotationY = 520;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23832:
                itemDef.copyItem(22089);
                itemDef.name = "Dustsweeper";
                break;


            case 23833:
                itemDef.copyItem(23730);
                itemDef.rs3 = true;
                itemDef.name = "Sacrificial costume";
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;
            case 23834:
                itemDef.copyItem(23833);
                itemDef.noteTemplate = 23833;
                itemDef.note = 23390;
                itemDef.name = "Sacrificial Costume";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                break;


            case 23835:
                itemDef.rs3 = true;
                itemDef.stackable = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Gold Grinder booster";
                itemDef.modelID = 100097;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 400;
                itemDef.rotationY = 314;
                itemDef.rotationX = 159;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.oldColors = new int[]{-28495, -28536, -28524};
                itemDef.newColors = new int[]{11212, 11212, 11237};
                break;
            case 23836:
                itemDef.copyItem(12630);
                itemDef.name = "Moon Aura";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.oldColors = new int[]{50, 10478};
                itemDef.newColors = new int[]{57, 0};
                break;
            case 23837:
                itemDef.copyItem(782);
                itemDef.name = "Moon Crest";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 23838:
                itemDef.copyItem(23734);
                itemDef.name = "Team Cape X";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.modelID = 140018;
                itemDef.maleEquip1 = 140017;
                itemDef.femaleEquip1 = 140017;
                itemDef.modelZoom = 2166;
                itemDef.rotationY = 274;
                itemDef.rotationX = 1239;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                itemDef.maleWieldZ = 6;
                break;
            case 23839:
                itemDef.copyItem(22089);
                itemDef.name = "Bloody Woody";
                break;
            case 23840:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Leviathan Sword";
                itemDef.modelID = 24423;
                itemDef.maleEquip1 = 24282;
                itemDef.femaleEquip1 = 24282;
                itemDef.modelZoom = 2445;
                itemDef.rotationY = 419;
                itemDef.rotationX = 25;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 11;
                itemDef.modelOffsetY = -1;
                break;
            case 23841:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Leviathan Bow";
                itemDef.modelID = 24397;
                itemDef.maleEquip1 = 20923;
                itemDef.femaleEquip1 = 20923;
                itemDef.modelZoom = 1948;
                itemDef.rotationY = 484;
                itemDef.rotationX = 1054;
                itemDef.rotationZ = 1939;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -3;
                break;
            case 23842:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Leviathan Staff";
                itemDef.modelID = 24402;
                itemDef.maleEquip1 = 23877;
                itemDef.femaleEquip1 = 23877;
                itemDef.modelZoom = 3651;
                itemDef.rotationY = 500;
                itemDef.rotationX = 492;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;


/*
            case 23843:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Orb of corrupted anima";
                itemDef.modelID = 112972;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 303;
                itemDef.rotationX = 943;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -5;
                break;

            case 23844:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Orb of volcanic anima";
                itemDef.modelID = 112974;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 303;
                itemDef.rotationX = 943;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -5;
                break;

            case 23845:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Orb of pure anima";
                itemDef.modelID = 112973;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 303;
                itemDef.rotationX = 943;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -5;
                break;*/

            case 23846:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Dormant Leviathon bow";
                itemDef.modelID = 112980;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 2120;
                itemDef.rotationY = 556;
                itemDef.rotationX = 34;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 4;
                break;


            case 23847:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Dormant Leviathon staff";
                itemDef.modelID = 112983;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 3044;
                itemDef.rotationY = 732;
                itemDef.rotationX = 451;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 3;
                break;

            case 23848:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Dormant Leviathon sword";
                itemDef.modelID = 112986;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 1813;
                itemDef.rotationY = 732;
                itemDef.rotationX = 1586;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 8;
                break;

            case 23849:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Leviathon fragment 1";
                itemDef.modelID = 74548;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 658;
                itemDef.rotationY = 525;
                itemDef.rotationX = 700;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -4;
                itemDef.modelOffsetY = 1;
                break;

            case 23850:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Leviathon fragment 2";
                itemDef.modelID = 74551;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 592;
                itemDef.rotationY = 606;
                itemDef.rotationX = 1885;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 9;
                itemDef.modelOffsetY = 8;
                break;

            case 23851:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Leviathon fragment 3";
                itemDef.modelID = 74550;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 724;
                itemDef.rotationY = 566;
                itemDef.rotationX = 1252;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 0;
                break;

            case 23852:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Leviathon Sigil";
                itemDef.modelID = 74514;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 720;
                itemDef.rotationY = 552;
                itemDef.rotationX = 2047;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 3;
                break;

            case 23853:
                itemDef.copyItem(5074);
                itemDef.name = "Vorki Pet";
                itemDef.modelID = 35024;
                itemDef.scaleX = 128;
                itemDef.scaleY = 128;
                itemDef.scaleZ = 128;
                itemDef.modelZoom = 3242;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -38;
                itemDef.osrs = true;
                break;



            case 23854:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 12;
                itemDef.name = "Taranis Ring";
                itemDef.modelID = 84078;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 658;
                itemDef.rotationY = 283;
                itemDef.rotationX = 350;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 21;
                itemDef.colorChange = new double[]{0.7, 0, 0.7};
                break;
            case 23855:
                itemDef.copyItem(5074);
                itemDef.name = "Taranis Pet";
                itemDef.modelID = MobDefinition.get(11872).npcModels[0];
                itemDef.scaleX = 32;
                itemDef.scaleY = 32;
                itemDef.scaleZ = 32;
                itemDef.modelZoom = 2800;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 10;
                break;


            case 23856:
                itemDef.copyItem(17728);
                itemDef.name = "Alchemical Shield";
                itemDef.modelID = 32793;
                itemDef.maleEquip1 = 32667;
                itemDef.femaleEquip1 = 32667;
                itemDef.scaleX = 180;
                itemDef.scaleY = 180;
                itemDef.scaleZ = 180;
                itemDef.modelZoom = 1000;
                itemDef.rotationX = 525;
                itemDef.rotationY = 494;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleWieldX = -5;
                itemDef.maleWieldY = 55;
                itemDef.osrs = true;
                break;
            case 23857:
                itemDef.copyItem(5074);
                itemDef.name = "Hydra Pet";
                itemDef.modelID = 36197;
                itemDef.scaleX = 128;
                itemDef.scaleY = 128;
                itemDef.scaleZ = 128;
                itemDef.modelZoom = 3495;
                itemDef.rotationX = 1953;
                itemDef.rotationY = 67;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -36;
                itemDef.modelOffsetY = -49;
                itemDef.osrs = true;
                break;



            case 23858:
                itemDef.copyItem(13379);
                itemDef.name = "Anima Key Shards";
                itemDef.actions = new String[]{"Combine", null, null, null, null};
                itemDef.colorChange = new double[]{0.1, 0.1, 1.5};
                break;
            case 23859:
                itemDef.copyItem(18665);
                itemDef.name = "Anima Key";
                itemDef.colorChange = new double[]{0.1, 0.1, 0.6};
                itemDef.actions = new String[]{"Teleport", null, null, null, null};
                break;



            case 23860:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Wisdom helm";
                itemDef.modelID = 110880;
                itemDef.maleEquip1 = 111133;
                itemDef.femaleEquip1 = 111222;
                itemDef.modelZoom = 921;
                itemDef.rotationY = 209;
                itemDef.rotationX = 61;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -3;
                break;
            case 23861:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Wisdom body";
                itemDef.modelID = 110884;
                itemDef.maleEquip1 = 111150;
                itemDef.femaleEquip1 = 111238;
                itemDef.modelZoom = 1678;
                itemDef.rotationY = 515;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -2;
                break;

            case 23862:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Wisdom legs";
                itemDef.modelID = 110882;
                itemDef.maleEquip1 = 111141;
                itemDef.femaleEquip1 = 111230;
                itemDef.modelZoom = 2296;
                itemDef.rotationY = 434;
                itemDef.rotationX = 115;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;



            case 23863:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Wisdom helm (u)";
                itemDef.modelID = 110879;
                itemDef.maleEquip1 = 111134;
                itemDef.femaleEquip1 = 111221;
                itemDef.modelZoom = 1177;
                itemDef.rotationY = 245;
                itemDef.rotationX = 2037;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -3;
                break;
            case 23864:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Wisdom body (u)";
                itemDef.modelID = 110883;
                itemDef.maleEquip1 = 111151;
                itemDef.femaleEquip1 = 111237;
                itemDef.modelZoom = 1678;
                itemDef.rotationY = 515;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -2;
                break;
            case 23865:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Wisdom legs (u)";
                itemDef.modelID = 110881;
                itemDef.maleEquip1 = 111142;
                itemDef.femaleEquip1 = 111229;
                itemDef.modelZoom = 2296;
                itemDef.rotationY = 434;
                itemDef.rotationX = 115;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;

            case 23866:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Zarosian essence";
                itemDef.modelID = 110839;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 780;
                itemDef.rotationY = 368;
                itemDef.rotationX = 220;
                itemDef.rotationZ = 1860;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 7;
                break;

            case 23867:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Sliskean essence";
                itemDef.modelID = 110837;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 780;
                itemDef.rotationY = 368;
                itemDef.rotationX = 220;
                itemDef.rotationZ = 1860;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 7;
                break;

            case 23868:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Zamorakian essence";
                itemDef.modelID = 110838;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 780;
                itemDef.rotationY = 368;
                itemDef.rotationX = 220;
                itemDef.rotationZ = 1860;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 7;
                break;

            case 23869:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Serenic essence";
                itemDef.modelID = 110836;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.modelZoom = 780;
                itemDef.rotationY = 368;
                itemDef.rotationX = 220;
                itemDef.rotationZ = 1860;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 7;
                break;

            case 23870:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Vengeance helm";
                itemDef.modelID = 110853;
                itemDef.maleEquip1 = 111129;
                itemDef.femaleEquip1 = 111218;
                itemDef.modelZoom = 921;
                itemDef.rotationY = 191;
                itemDef.rotationX = 1791;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 9;
                break;

            case 23871:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Vengeance body";
                itemDef.modelID = 110857;
                itemDef.maleEquip1 = 111146;
                itemDef.femaleEquip1 = 111234;
                itemDef.modelZoom = 1678;
                itemDef.rotationY = 515;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -2;
                break;

            case 23872:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Vengeance legs";
                itemDef.modelID = 110855;
                itemDef.maleEquip1 = 111137;
                itemDef.femaleEquip1 = 111226;
                itemDef.modelZoom = 2296;
                itemDef.rotationY = 434;
                itemDef.rotationX = 115;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;

            case 23873:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Vengeance helm (u)";
                itemDef.modelID = 110852;
                itemDef.maleEquip1 = 111130;
                itemDef.femaleEquip1 = 111217;
                itemDef.modelZoom = 921;
                itemDef.rotationY = 191;
                itemDef.rotationX = 1791;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 9;
                break;

            case 23874:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Vengeance body (u)";
                itemDef.modelID = 110856;
                itemDef.maleEquip1 = 111147;
                itemDef.femaleEquip1 = 111233;
                itemDef.modelZoom = 1678;
                itemDef.rotationY = 515;
                itemDef.rotationX = 1;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -2;
                break;

            case 23875:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Vengeance legs (u)";
                itemDef.modelID = 110854;
                itemDef.maleEquip1 = 111138;
                itemDef.femaleEquip1 = 111225;
                itemDef.modelZoom = 2296;
                itemDef.rotationY = 434;
                itemDef.rotationX = 115;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;

            case 23876:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Destiny helm";
                itemDef.modelID = 110844;
                itemDef.maleEquip1 = 111127;
                itemDef.femaleEquip1 = 111216;
                itemDef.modelZoom = 800;
                itemDef.rotationY = 263;
                itemDef.rotationX = 1953;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 28;
                break;

            case 23877:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Destiny body";
                itemDef.modelID = 110848;
                itemDef.maleEquip1 = 111144;
                itemDef.femaleEquip1 = 111232;
                itemDef.modelZoom = 1762;
                itemDef.rotationY = 524;
                itemDef.rotationX = 9;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -4;
                break;

            case 23878:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Destiny legs";
                itemDef.modelID = 110846;
                itemDef.maleEquip1 = 111135;
                itemDef.femaleEquip1 = 111224;
                itemDef.modelZoom = 2296;
                itemDef.rotationY = 434;
                itemDef.rotationX = 115;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;

            case 23879:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Destiny helm (u)";
                itemDef.modelID = 110843;
                itemDef.maleEquip1 = 111128;
                itemDef.femaleEquip1 = 111215;
                itemDef.modelZoom = 800;
                itemDef.rotationY = 532;
                itemDef.rotationX = 14;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 1;
                break;

            case 23880:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 4;
                itemDef.name = "Destiny body (u)";
                itemDef.modelID = 110847;
                itemDef.maleEquip1 = 111145;
                itemDef.femaleEquip1 = 111231;
                itemDef.modelZoom = 1634;
                itemDef.rotationY = 371;
                itemDef.rotationX = 2045;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 7;
                break;

            case 23881:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 7;
                itemDef.name = "Destiny legs (u)";
                itemDef.modelID = 110845;
                itemDef.maleEquip1 = 111136;
                itemDef.femaleEquip1 = 111223;
                itemDef.modelZoom = 2296;
                itemDef.rotationY = 434;
                itemDef.rotationX = 115;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;


            case 23882:
                itemDef.copyItem(18768);
                itemDef.actions = new String[]{"Open", null, null, null, null};
                itemDef.name = "Autumn Box";
                itemDef.oldColors = new int[]{
                        2999, 926, 22410
                };
                itemDef.newColors = new int[]{
                        3967,  59314,  59314
                };
                break;

            case 23883:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 0;
                itemDef.name = "Coronet of Autumn";
                itemDef.modelID = 86900;
                itemDef.maleEquip1 = 86880;
                itemDef.femaleEquip1 = 86890;
                itemDef.modelZoom = 395;
                itemDef.rotationY = 190;
                itemDef.rotationX = 85;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -9;
                break;
            case 23884:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 1;
                itemDef.name = "Cloak of autumn";
                itemDef.modelID = 95814;
                itemDef.maleEquip1 = 95795;
                itemDef.femaleEquip1 = 95795;
                itemDef.modelZoom = 1974;
                itemDef.rotationY = 368;
                itemDef.rotationX = 228;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 4;
                break;


            case 23885:
                itemDef.copyItem(4051);
                itemDef.name = "Assassin's toolkit";
                itemDef.colorChange = new double[]{0.7, 0.3, 0.1};

                break;
            case 23886:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Crystal hatchet";
                itemDef.modelID = 100212;
                itemDef.maleEquip1 = 100150;
                itemDef.femaleEquip1 = 100150;
                itemDef.modelZoom = 1104;
                itemDef.rotationY = 206;
                itemDef.rotationX = 1967;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = -3;
                break;

            case 23887:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Crystal pickaxe";
                itemDef.modelID = 99074;
                itemDef.maleEquip1 = 98978;
                itemDef.femaleEquip1 = 98978;
                itemDef.modelZoom = 1349;
                itemDef.rotationY = 188;
                itemDef.rotationX = 282;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -18;
                itemDef.modelOffsetY = 5;
                break;


            case 23888:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 3;
                itemDef.name = "Assassin claws (u)";
                itemDef.modelID = 8270;
                itemDef.maleEquip1 = 8270;
                itemDef.maleEquip2 = 8272;
                itemDef.femaleEquip1 = 8270;
                itemDef.femaleEquip2 = 8272;
                itemDef.modelZoom = 848;
                itemDef.rotationY = 458;
                itemDef.rotationX = 1280;
                itemDef.rotationZ = 1800;
                itemDef.modelOffsetX = 20;
                itemDef.modelOffsetY = -31;
                itemDef.colorChange = new double[]{0.7, 0.3, 0.1};
                break;


            case 23889:
                itemDef.rs3 = true;
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.slot = 10;
                itemDef.name = "Silverhawk boots";
                itemDef.modelID = 93888;
                itemDef.maleEquip1 = 93881;
                itemDef.femaleEquip1 = 93881;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 157;
                itemDef.rotationX = 115;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -14;
                break;
            case 23890:
                itemDef.copyItem(5074);
                itemDef.name = "Silverhawk Pet";
                itemDef.modelID = 28873;
                itemDef.modelZoom = 976;
                itemDef.rotationX = 1892;
                itemDef.rotationY = 2042;
                itemDef.modelOffsetX = -20;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                itemDef.osrs = true;
                break;
            case 23891:
                itemDef.copyItem(22089);
                itemDef.name = "Tifs Gun";
                break;
        }
        return itemDef;
    }

    public static ItemDefinition itemDef(int id, ItemDefinition itemDef) {
        // TODO Auto-generated method stub
        return null;
    }
}
