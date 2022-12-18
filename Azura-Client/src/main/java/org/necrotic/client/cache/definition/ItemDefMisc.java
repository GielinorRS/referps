package org.necrotic.client.cache.definition;

public class ItemDefMisc {

    public static void setCustomItemDefData(int customId, ItemDefinition itemDef) {
        ItemDefinition itemDef21 = ItemDefinition.get(12458);

        switch (customId) {
            case 15900:
                itemDef.name = "Zilyana hood";

                itemDef.rdc2 = 87295;
                break;
            case 15845:
                itemDef.name = "Zilyana robe top";

                itemDef.rdc2 = 87295;
                break;
            case 15805:
                itemDef.name = "Zilyana robe bottom";

                itemDef.rdc2 = 87295;
                break;
            case 18796:
                ItemDefinition itemSara1 = ItemDefinition.get(11698);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemSara1.modelID;
                itemDef.femaleEquip1 = itemSara1.femaleEquip1;
                itemDef.maleEquip1 = itemSara1.maleEquip1;
                itemDef.modelOffsetX = itemSara1.modelOffsetX;
                itemDef.rotationZ = itemSara1.rotationZ;
                itemDef.modelOffsetY = itemSara1.modelOffsetY;
                itemDef.modelZoom = itemSara1.modelZoom;
                itemDef.rotationY = itemSara1.rotationY;
                itemDef.rotationX = itemSara1.rotationX;
                itemDef.actions = itemSara1.actions;

                itemDef.name = "Zilyana godsword";
                itemDef.rdc2 = 53633;
                break;
            case 18790:
                ItemDefinition itemZammy = ItemDefinition.get(11716);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemZammy.modelID;
                itemDef.femaleEquip1 = itemZammy.femaleEquip1;
                itemDef.maleEquip1 = itemZammy.maleEquip1;
                itemDef.modelOffsetX = itemZammy.modelOffsetX;
                itemDef.rotationZ = itemZammy.rotationZ;
                itemDef.modelOffsetY = itemZammy.modelOffsetY;
                itemDef.modelZoom = itemZammy.modelZoom;
                itemDef.rotationY = itemZammy.rotationY;
                itemDef.rotationX = itemZammy.rotationX;
                itemDef.actions = itemZammy.actions;

                itemDef.name = "Tsutsaroth spear";

                itemDef.rdc2 = 11332;
                break;
            case 18789:
                ItemDefinition itemZammy1 = ItemDefinition.get(11700);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemZammy1.modelID;
                itemDef.femaleEquip1 = itemZammy1.femaleEquip1;
                itemDef.maleEquip1 = itemZammy1.maleEquip1;
                itemDef.modelOffsetX = itemZammy1.modelOffsetX;
                itemDef.rotationZ = itemZammy1.rotationZ;
                itemDef.modelOffsetY = itemZammy1.modelOffsetY;
                itemDef.modelZoom = itemZammy1.modelZoom;
                itemDef.rotationY = itemZammy1.rotationY;
                itemDef.rotationX = itemZammy1.rotationX;
                itemDef.actions = itemZammy1.actions;
                itemDef.name = "Tsutsaroth godsword";

                itemDef.rdc2 = 33333;
                break;
            case 18791:
                ItemDefinition itemZammy11 = ItemDefinition.get(18746);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemZammy11.modelID;
                itemDef.femaleEquip1 = itemZammy11.femaleEquip1;
                itemDef.maleEquip1 = itemZammy11.maleEquip1;
                itemDef.modelOffsetX = itemZammy11.modelOffsetX;
                itemDef.rotationZ = itemZammy11.rotationZ;
                itemDef.modelOffsetY = itemZammy11.modelOffsetY;
                itemDef.modelZoom = itemZammy11.modelZoom;
                itemDef.rotationY = itemZammy11.rotationY;
                itemDef.rotationX = itemZammy11.rotationX;
                itemDef.actions = itemZammy11.actions;
                itemDef.name = itemZammy11.name;
                itemDef.rdc2 = 55555;

                itemDef.name = "Tsutsaroth halo";
                break;
            case 18787:
                ItemDefinition itemZammy111 = ItemDefinition.get(20000);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemZammy111.modelID;
                itemDef.femaleEquip1 = itemZammy111.femaleEquip1;
                itemDef.maleEquip1 = itemZammy111.maleEquip1;
                itemDef.modelOffsetX = itemZammy111.modelOffsetX;
                itemDef.rotationZ = itemZammy111.rotationZ;
                itemDef.modelOffsetY = itemZammy111.modelOffsetY;
                itemDef.modelZoom = itemZammy111.modelZoom;
                itemDef.rotationY = itemZammy111.rotationY;
                itemDef.rotationX = itemZammy111.rotationX;
                itemDef.actions = itemZammy111.actions;
                itemDef.name = "Tsutsaroth boots";

                itemDef.rdc2 = 77777;
                break;
            case 5021:
                itemDef.name = "1M ticket";
                //itemDef.rdc2 = 23452;//333233 gold
                itemDef.stackable = true;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Add-to-pouch";
                itemDef.actions[2] = "Convert-to-coin";
                itemDef21 = ItemDefinition.get(18652);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.modelID = itemDef21.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{67};
                break;
            case 10835:
                itemDef.name = "1B bag";
                //itemDef.rdc2 = 333233;
                itemDef.stackable = true;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Convert-to-coin";
                itemDef.actions[2] = "Open-all";

                itemDef.stackable = true;
                break;
            case 5022:
                itemDef.name = "<col=ff4f4f>PVM ticket";
                itemDef.rdc2 = 325111;
                itemDef.stackable = true;
                itemDef21 = ItemDefinition.get(18652);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.modelID = itemDef21.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;

                break;
            case 5023:
                itemDef.name = "<col=aaaaaa><shad=2>Slayer ticket";
                itemDef.rdc2 = 23452;//666455
                itemDef.stackable = true;
                itemDef21 = ItemDefinition.get(18652);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.modelID = itemDef21.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{67};
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Claim";
                break;
            case 22044:
                itemDef.modelID = 65270;
                itemDef.name = "Completionist Cape";
                itemDef.description = "We'd pat you on the back, but this cape would get in the way.".getBytes();
                itemDef.modelZoom = 1385;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.oldColors = new int[]{65214, 65200, 65186, 62995};
                itemDef.newColors = new int[]{44988, 44988, 32463, 44988};
                itemDef.maleEquip1 = 65297;
                itemDef.femaleEquip1 = 65297;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;

            case 2722:
            case 2723:
            case 2725:
            case 2727:
            case 2729:
            case 2731:
            case 2733:
            case 2735:
            case 2737:
            case 2739:
            case 2741:
            case 2743:
            case 2745:
            case 2747:
            case 2773:
            case 2774:
            case 2776:
            case 2778:
            case 2780:
            case 2782:
            case 2783:
            case 2785:
            case 2786:
            case 2788:
            case 2790:
            case 2792:
            case 2793:
            case 2794:
            case 2796:
            case 2797:
            case 2799:
            case 3520:
            case 3522:
            case 3524:
            case 3525:
            case 3526:
            case 3528:
            case 3530:
            case 3532:
            case 3534:
            case 3536:
            case 3538:
            case 3540:
            case 3542:
            case 3544:
            case 3546:
            case 3548:
            case 3550:
                itemDef.name = "Clue scroll";
                break;
            case 15752:
            case 15751:
            case 15750:
                itemDef.name = "Saber Crystal";
                itemDef.actions = new String[]{"Break-crystal", null, null, null, "Destroy"};
                break;
            case 2946:
                itemDef.actions = new String[]{"Open", null, null, null, "Destroy"};
                break;
            case 1561:
                itemDef.name = "Pet return";
                itemDef.actions = new String[]{"Claim pets", null, null, null, "Destroy"};
                break;
            case 9004:
                itemDef.name = "Tome of Inquisition";
                break;
            case 9003:
                itemDef.name = "Tome of Inquisition";
                itemDef.actions = new String[]{"Instructions", null, "View drops", null, "Destroy"};
                break;
            case 554:
                itemDef.rotationY = 528;
                itemDef.rotationX = 1012;
                break;
            case 22060:
                itemDef.name = "Mahogany logs";
                itemDef.description = "Some well-cut mahogany logs.".getBytes();
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.groundActions = new String[]{null, null, "Take", "Light", null};
                itemDef.newColors = new int[]{6585, 4758, 5006};
                itemDef.oldColors = new int[]{5665, 5784, 5559};
                itemDef.modelID = 7760;
                itemDef.modelZoom = 1180;
                itemDef.rotationY = 120;
                itemDef.rotationX = 1852;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = -1;
                itemDef.maleEquip2 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.femaleEquip2 = -1;
                itemDef.maleDialogue = -1;
                itemDef.femaleDialogue = -1;
                itemDef.stackable = false;
                break;
            case 22061:
                itemDef.name = "Mahogany logs";
                itemDef.description = "Some well-cut mahogany logs.".getBytes();
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.groundActions = new String[]{null, null, null, null, null};
                itemDef.newColors = new int[]{6585, 4758, 5006};
                itemDef.oldColors = new int[]{5665, 5784, 5559};

                itemDef.noteTemplate = 22060;
                itemDef.note = 799;
                itemDef.rotationY = 552;
                itemDef.rotationX = 28;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 2;
                // itemDef.modelID = 2429;
                itemDef.modelZoom = 1385;

                itemDef.modelID = 7760;
                // itemDef.modelZoom = 1180;
                // itemDef.modelRotationY = 120;
                // itemDef.modelRotationX = 1852;
                // itemDef.modelOffsetX = 0;
                // itemDef.modelOffset1 = 0;
                // itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = -1;
                itemDef.maleEquip2 = -1;
                itemDef.femaleEquip1 = -1;
                itemDef.femaleEquip2 = -1;
                itemDef.maleDialogue = -1;
                itemDef.femaleDialogue = -1;
                itemDef.stackable = true;
                break;
            case 22062:
                itemDef.name = "barb axe";
                itemDef.description = "something.".getBytes();
                itemDef.modelID = 11788;
                itemDef.maleEquip1 = 11788;
                itemDef.femaleEquip1 = 11788;
                itemDef.actions[1] = "Wield";
                break;
            case 113:
            case 2428:
            case 2430:
            case 2432:
            case 2434:
            case 2436:
            case 2438:
            case 2440:
            case 2442:
            case 2444:
            case 2446:
            case 2448:
            case 2450:
            case 2452:
            case 3008:
            case 3016:
            case 3024:
            case 3032:
            case 3040:
            case 3408:
            case 3422:
            case 3430:
            case 4842:
            case 5943:
            case 5952:
            case 6470:
            case 6685:
            case 7660:
            case 9739:
            case 9998:
            case 10925:
            case 12140:
            case 14838:
            case 14846:
            case 15300:
            case 15304:
            case 15308:
            case 15312:
            case 15316:
            case 15320:
            case 15328:

            case 21630:
                // potion (4) doses
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = -5;
                itemDef.rotationY = 84;
                itemDef.rotationX = 1996;
                itemDef.modelZoom = 550;
                break;
        }

    }
}
