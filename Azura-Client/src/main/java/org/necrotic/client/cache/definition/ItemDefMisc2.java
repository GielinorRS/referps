package org.necrotic.client.cache.definition;

public class ItemDefMisc2 {

    public static void setCustomItemDefData(int customId, ItemDefinition itemDef) {
        switch (customId) {

            case 19000:
                itemDef.copyItem(14639);
                itemDef.name = "Pet fragment";
                itemDef.actions = new String[]{"Exchange", null, null, null, null};
                break;
            case 19001:
                itemDef.copyItem(15262);
                itemDef.name = "Pet fragment pack (X250)";
                itemDef.stackable = false;
                itemDef.actions = new String[]{"Open", null, null, null, "Destroy"};
                itemDef.rdc2 = 487111;
                break;
            case 22000:
                itemDef.copyItem(15496);
                itemDef.name = "Boss Slayer Helmet [1]";
                itemDef.modelID = 100170;
                itemDef.maleEquip1 = 100171;
                itemDef.femaleEquip1 = 100171;
                itemDef.actions = new String[]{null, "Wear", "Upgrade", null, "Destroy"};
                break;
            case 22001:
                itemDef.copyItem(15496);
                itemDef.name = "Boss Slayer Helmet [2]";
                itemDef.modelID = 100168;
                itemDef.maleEquip1 = 100169;
                itemDef.femaleEquip1 = 100169;
                itemDef.actions = new String[]{null, "Wear", "Upgrade", null, "Destroy"};
                break;
            case 22002:
                itemDef.copyItem(15496);
                itemDef.name = "Boss Slayer Helmet [3]";
                itemDef.modelID = 100176;
                itemDef.maleEquip1 = 100177;
                itemDef.femaleEquip1 = 100177;
                itemDef.actions = new String[]{null, "Wear", "Upgrade", null, "Destroy"};
                break;
            case 22003:
                itemDef.copyItem(15496);
                itemDef.name = "Boss Slayer Helmet [4]";
                itemDef.modelID = 100172;
                itemDef.maleEquip1 = 100173;
                itemDef.femaleEquip1 = 100173;
                itemDef.actions = new String[]{null, "Wear", "Upgrade", null, "Destroy"};
                break;
            case 22004:
                itemDef.copyItem(15496);
                itemDef.name = "Boss Slayer Helmet [5]";
                itemDef.modelID = 100174;
                itemDef.maleEquip1 = 100175;
                itemDef.femaleEquip1 = 100175;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 22005:
                itemDef.copyItem(18818);
                itemDef.name = "Infernal ring";
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 22006: // Deathtouch darts
                itemDef.copyItem(11230);
                itemDef.name = "Deathtouch Darts";
                itemDef.newColors = new int[]{5409, 920, 914, 929, 10452, 10293};
                itemDef.oldColors = new int[]{943, 3866, 914, 3866, 943, 943};
                break;


            case 621:
                itemDef.name = "Boss Slayer Ticket";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                break;


            case 4278:
                itemDef.name = "Instance Token";
                itemDef.actions = new String[]{"Open", null, "Last instance",  null,"Destroy"};
                break;

            case 12855:
                itemDef.name = "@yel@Upgrade Tokens";
                itemDef.modelID = 139928;
                itemDef.modelZoom = 3000;
                itemDef.actions = new String[]{"Activate", null, "Exchange", "Exchange X", "Destroy"};
                break;
            case 12856:
                itemDef.name = "@or2@BTC";
                itemDef.modelID = 131139;
                itemDef.modelZoom = 500;
                itemDef.actions = new String[]{"Exchange", null, null, null, "Destroy"};
                break;
            case 12857:
                itemDef.name = "@whi@Ethereum";
                itemDef.modelID = 131140;
                itemDef.modelZoom = 250;
                itemDef.actions = new String[]{"Exchange", null, null, null, "Destroy"};
                break;
            case 12858:
                itemDef.name = "@yel@USD";
                itemDef.modelID = 130083;
                itemDef.modelZoom = 250;
                itemDef.actions = new String[]{"Exchange", null, null, null, "Destroy"};
                break;
            case 19094:
                itemDef.name = "@gre@Cash Room Key";
                itemDef.modelID = 130590;
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                break;
            case 1563:
            case 1564:
            case 1562:
            case 12486:
            case 12490:
            case 12496:
            case 12498:
            case 12522:
            case 12514:
            case 12512:
            case 12518:
            case 12520:

                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 456:
                itemDef.name = "Scorpian pet";
                itemDef.modelID = 24610;

                itemDef.modelZoom = 4000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 457:
                itemDef.name = "Unicorn pet";
                itemDef.modelID = 25754;

                itemDef.modelZoom = 3000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 458:
                itemDef.name = "Bear pet";
                itemDef.modelID = 18884;
                itemDef.modelZoom = 3000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 459:
                itemDef.name = "Wolf pet";
                itemDef.modelID = 26266;
                itemDef.modelZoom = 3000;

                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 460:
                itemDef.name = "Gorilla pet";
                itemDef.modelID = 65154;
                itemDef.modelZoom = 3000;

                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 461:
                itemDef.name = "Vegeta pet";
                itemDef.modelID = 65304;
                itemDef.modelZoom = 1100;
                itemDef.modelOffsetY = 160;

                itemDef.rotationZ = 1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 462:
                itemDef.modelID = 13850;

                itemDef.modelZoom = 900;
                itemDef.modelOffsetY = 160;

                itemDef.rotationZ = 1;
                itemDef.name = "Yoshi pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", "Upgrade-pet", "Destroy"};
                break;
            case 463:

                itemDef.modelID = 65332;
                itemDef.modelZoom = 6000;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.name = "@cya@Eternal dragon egg";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;

            case 20061:
                itemDef.name = "Welcome Crate";
                break;
            case 15682:
                itemDef.name = "Vote Crate";
                itemDef.actions = new String[]{"Open", null, null, null, "Destroy"};
                break;

            /*
             * case 13095: itemDef.name = "Crip Stick"; break;
             */
            case 16337:
                itemDef.name = "Impspawn bow";
                break;
            case 7927:
                itemDef.name = "Cyantrix ring";
                break;
            case 19887:
                itemDef.name = "Impspawn necklace";
                break;

            case 12601:
                itemDef.name = "Ring of the gods";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 393;
                itemDef.rotationX = 1589;
                itemDef.modelOffsetX = -9;
                itemDef.modelOffsetY = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.modelID = 33009;
                break;
            case 18365:
                itemDef.copyItem(4710);
                itemDef.name = "Starter sword";
                itemDef.modelID = 100299;
                itemDef.maleEquip1 = 100300;
                itemDef.femaleEquip1 = 100300;
                itemDef.modelZoom = 1500;
                itemDef.rotationX = 1100;
                itemDef.rotationY = 525;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 16133:
                itemDef.name = "KBD 2h sword";
                break;
            case 15920:
                itemDef.name = "KBD full helm";
                
                break;
            case 16879:
                itemDef.copyItem(4710);
                itemDef.name = "Starter bow";
                itemDef.modelID = 100295;
                itemDef.maleEquip1 = 100296;
                itemDef.femaleEquip1 = 100296;
                itemDef.modelZoom = 2300;
                itemDef.rotationX = 1100;
                itemDef.rotationY = 450;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 19886:
                itemDef.name = "Collector necklace";
                

                break;
            case 4446:
                itemDef.name = "Collector ring";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 285;
                itemDef.rotationX = 1163;
                

                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 13641:
                itemDef.copyItem(4710);
                itemDef.name = "Starter staff";
                itemDef.modelID = 100297;
                itemDef.maleEquip1 = 100298;
                itemDef.femaleEquip1 = 100298;
                itemDef.modelZoom = 2600;
                itemDef.rotationX = 1100;
                itemDef.rotationY = 400;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 2714:
                itemDef.name = "Gold Casket";
                itemDef.actions = new String[]{"Loot", null, null, null, "Destroy"};
                break;
            /*
             * case 1505: itemDef.name = "100m Scroll"; itemDef.actions = new String[5];
             * itemDef.actions[4] = "Destroy"; break;
             */
            case 607:
                itemDef.name = "Imbuement Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                break;

            /*
             * case 12961: itemDef.name = "Dark hood"; itemDef.actions = new String [] {
             * null, null, null, null, "Destroy" }; break;
             *
             * case 17680: itemDef.name = "Crushed whiteberries"; itemDef.actions = new
             * String [] { null, null, null, null, "Destroy" }; break;
             *
             * case 3057: itemDef.name = "Mask"; itemDef.actions = new String [] { null,
             * null, null, null, "Destroy" }; break;
             *
             * case 1633: itemDef.name = "Bonemeal"; itemDef.actions = new String [] { null,
             * null, null, null, "Destroy" }; break;
             *
             * case 2424: itemDef.actions = new String[] { "Create face", null, null, null,
             * "Destroy" }; break;
             */

            case 11180:
                itemDef.name = "Trio Token";
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                break;

            case 12603:
                itemDef.name = "Tyrannical ring";
                itemDef.modelZoom = 592;
                itemDef.rotationY = 285;
                itemDef.rotationX = 1163;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.modelID = 28823;
                break;

            case 20692:
                itemDef.name = "Barrel of fireworks";
                itemDef.actions = new String[]{"Fire", null, null, null, "Destroy"};
                break;
            case 18719:
                itemDef.name = "Potion of flight";
                itemDef.actions = new String[]{"Drink", null, null, null, "Destroy"};
                

                break;
            case 12605:
                itemDef.name = "Treasonous ring";
                itemDef.modelZoom = 750;
                itemDef.rotationY = 342;
                itemDef.rotationX = 252;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.modelID = 33010;
                break;
            case 12927:
                itemDef.name = "Serpentine visage";
                itemDef.modelZoom = 816;
                itemDef.rotationY = 498;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 7;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 19218;
                break;
            case 12922:
                itemDef.name = "Tanzanite fang";
                itemDef.modelZoom = 1411;
                itemDef.rotationY = 202;
                itemDef.rotationX = 1939;
                itemDef.modelOffsetX = -4;
                itemDef.modelOffsetY = -8;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 19228;
                break;

            case 12929:
                itemDef.name = "Serpentine helm (uncharged)";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 215;
                itemDef.rotationX = 1724;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.modelID = 19222;
                itemDef.maleEquip1 = 14396;
                itemDef.femaleEquip1 = 14397;
                break;

            case 12931:
                itemDef.name = "Serpentine helm";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 215;
                itemDef.rotationX = 1724;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 9;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, null};
                itemDef.modelID = 19220;
                itemDef.maleEquip1 = 14395;
                itemDef.femaleEquip1 = 14398;
                break;

            case 12932:
                itemDef.name = "Magic fang";
                itemDef.modelZoom = 1095;
                itemDef.rotationY = 579;
                itemDef.rotationX = 1832;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 4;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 19227;
                break;

            case 12934:
                itemDef.name = "Zulrah's scales";
                itemDef.description = "Used to charge toxic items.".getBytes();
                itemDef.modelZoom = 1370;
                itemDef.rotationY = 212;
                itemDef.rotationX = 148;
                itemDef.modelOffsetX = 7;
                itemDef.stackable = true;
                itemDef.actions = new String[5];
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 19212;
                break;

            case 19908:
                itemDef.name = "Trident of the seas (full)";
                itemDef.modelZoom = 2350;
                itemDef.rotationY = 1549;
                itemDef.rotationX = 1818;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Uncharge", "Destroy"};
                itemDef.modelID = 28232;
                itemDef.maleEquip1 = 28230;
                itemDef.femaleEquip1 = 28230;
                break;

            case 19906:
                itemDef.name = "Trident of the seas";
                itemDef.modelZoom = 2755;
                itemDef.rotationY = 420;
                itemDef.rotationX = 1130;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Uncharge", "Destroy"};
                itemDef.modelID = 28232;
                itemDef.maleEquip1 = 28230;
                itemDef.femaleEquip1 = 28230;
                break;
            case 12899:
                itemDef.name = "Trident of the swamp";
                itemDef.modelZoom = 2421;
                itemDef.rotationY = 1818;
                itemDef.rotationX = 1549;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 9;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Uncharge", "Destroy"};
                itemDef.modelID = 19223;
                itemDef.maleEquip1 = 19221;
                itemDef.femaleEquip1 = 19221;
                break;
            case 22007:
                itemDef.name = "Kraken tentacle";
                itemDef.modelZoom = 1095;
                itemDef.rotationY = 593;
                itemDef.rotationX = 741;
                itemDef.modelOffsetX = 4;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 28437;
                break;
            case 2970:
                itemDef.name = "Mort myre fungus";
                itemDef.modelZoom = 790;
                itemDef.rotationY = 60;
                itemDef.rotationX = 232;
                itemDef.modelOffsetX = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                itemDef.modelID = 3563;
                break;
            case 22008:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                
                itemDef.name = "Abyssal tentacle"; // Name
                itemDef.description = "An Abyssal whip fused with a Kraken tentacle.".getBytes();
                itemDef.newColors = new int[23];
                itemDef.oldColors = new int[23];
                itemDef.oldColors[0] = 944;
                itemDef.newColors[0] = 86933; // green
                itemDef.oldColors[1] = 9371;
                itemDef.newColors[1] = 9583; // cream
                itemDef.oldColors[2] = 9255;
                itemDef.newColors[2] = 9221; // black
                itemDef.oldColors[3] = 9240;
                itemDef.newColors[3] = 9221; // black
                itemDef.oldColors[4] = 9385;
                itemDef.newColors[4] = 9221; // black
                itemDef.oldColors[5] = 9395;
                itemDef.newColors[5] = 9583; // cream
                itemDef.oldColors[6] = 9239;
                itemDef.newColors[6] = 9221; // black
                itemDef.oldColors[7] = 9254;
                itemDef.newColors[7] = 9221; // black
                itemDef.oldColors[8] = 9359;
                itemDef.newColors[8] = 9583; // cream
                itemDef.oldColors[9] = 9237;
                itemDef.newColors[9] = 86933; // green
                itemDef.oldColors[10] = 8262;
                itemDef.newColors[10] = 86933; // green
                itemDef.oldColors[11] = 8244;
                itemDef.newColors[11] = 86933; // green
                itemDef.oldColors[12] = 8214;
                itemDef.newColors[12] = 9583; // cream
                itemDef.oldColors[13] = 9372;
                itemDef.newColors[13] = 9583; // cream
                itemDef.oldColors[14] = 9355;
                itemDef.newColors[14] = 9583; // cream
                itemDef.oldColors[15] = 9380;
                itemDef.newColors[15] = 9583; // cream
                itemDef.oldColors[16] = 9409;
                itemDef.newColors[16] = 9583; // cream
                itemDef.oldColors[17] = 9411;
                itemDef.newColors[17] = 9583; // cream
                itemDef.oldColors[18] = 9228;
                itemDef.newColors[18] = 9221; // black
                itemDef.oldColors[19] = 9428;
                itemDef.newColors[19] = 9583; // cream
                itemDef.oldColors[20] = 9412;
                itemDef.newColors[20] = 9583; // cream
                itemDef.oldColors[21] = 9364;
                itemDef.newColors[21] = 9583; // cream
                itemDef.oldColors[22] = 9425;
                itemDef.newColors[22] = 9583; // cream
                itemDef.rotationY = 280;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 56;
                itemDef.modelID = 5412;
                itemDef.maleEquip1 = 5409;
                itemDef.femaleEquip1 = 5409;
                itemDef.modelZoom = 840;
                break;
            case 247:
                itemDef.actions = new String[]{null, null, null, null, null};
                break;
            case 22009:
                itemDef.name = "Abyssal tentacle"; // Name
                itemDef.actions = new String[]{null, null, null, null, null};
                itemDef.noteTemplate = 22008;
                itemDef.note = 799;
                itemDef.rotationY = 552;
                itemDef.rotationX = 28;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 2;
                itemDef.modelID = 2429;
                break;
            case 12900:
                itemDef.name = "Uncharged toxic trident";
                itemDef.modelZoom = 2421;
                itemDef.rotationY = 1818;
                itemDef.rotationX = 1549;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 9;
                itemDef.stackable = false;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Dismantle", "Destroy"};
                itemDef.modelID = 19226;
                itemDef.maleEquip1 = 14401;
                itemDef.femaleEquip1 = 14401;
                break;
            case 4566:
                itemDef.actions = new String[]{null, "Wield", null, "Dance", "Destroy"};
                break;
            case 12902:
                itemDef.name = "Toxic staff (uncharged)";
                itemDef.modelID = 19224;
                itemDef.maleEquip1 = 14404;
                itemDef.femaleEquip1 = 14404;
                itemDef.modelZoom = 2150;
                itemDef.rotationX = 512;
                itemDef.rotationY = 1010;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.stackable = false;
                // itemDef.rotationZ = 229;
                itemDef.actions[1] = "Wield";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.oldColors = new int[1];
                itemDef.newColors = new int[1];
                break;
            case 12904:
                itemDef.name = "Toxic staff of the dead";
                itemDef.modelID = 19224;
                itemDef.maleEquip1 = 14402;
                itemDef.femaleEquip1 = 14402;
                itemDef.modelZoom = 2150;
                itemDef.rotationX = 512;
                itemDef.rotationY = 1010;
                // itemDef.rotationZ = 229;
                itemDef.actions[1] = "Wield";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                break;
            case 13740:
            case 13742:
                itemDef.actions = new String[]{null, "Wear", "Toggle", null, "Destroy"};
                break;
            case 13738:
            case 13744:
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 6818:
                itemDef.name = "Bow Sword of 1k Truths";
                itemDef.description = "How do you kill that which has no life?".getBytes();
                break;
            case 6797:
                itemDef.name = "Unlimited Watering Can";
                itemDef.description = "Great for member farmers!".getBytes();
                break;
            case 7510:
            case 7509:
                itemDef.actions = new String[]{"Munch", null, "Guzzle", null, "Destroy"};
                break;
            case 12926:
                itemDef.actions = new String[5];
                itemDef.modelID = 19219;
                itemDef.name = "Toxic blowpipe";
                itemDef.modelZoom = 1158;
                itemDef.rotationX = 189;
                itemDef.rotationY = 768;
                itemDef.modelOffsetX = -7;
                itemDef.modelOffsetY = 4;
                itemDef.value = 20000000;
                itemDef.maleEquip1 = 14403;
                itemDef.femaleEquip1 = 14403;
                itemDef.actions[1] = "Wield";
                // itemDef.actions[2] = "Uncharge";
                itemDef.actions[3] = "Uncharge";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                break;
            case 12924:
                itemDef.actions = new String[5];
                itemDef.modelID = 19221;
                itemDef.name = "Toxic blowpipe (empty)";
                itemDef.modelZoom = 1158;
                itemDef.rotationX = 189;
                itemDef.rotationY = 768;
                itemDef.modelOffsetX = -7;
                itemDef.modelOffsetY = 4;
                itemDef.value = 200000000;
                itemDef.maleEquip1 = 14405;
                itemDef.femaleEquip1 = 14405;
                // itemDef.actions[1] = "Wield";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                break;
            case 14023:
                itemDef.modelID = 64994;// 64994;
                itemDef.name = "Drygore Long-sword";
                itemDef.description = "A powerful sword made from the chitlin of the Kalphite King.".getBytes();
                itemDef.modelZoom = 1493;
                itemDef.rotationY = 618;
                itemDef.rotationX = 1086;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64995;
                itemDef.femaleEquip1 = 64996;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Destroy";
                break;


            case 1514:
            case 1515:
            case 1516:
            case 1517:
            case 1518:
            case 449:
            case 450:
            case 451:
            case 452:
            case 383:
            case 384:
            case 15270:
            case 15271:
            case 2362:
            case 2361:
            case 2364:
            case 2363:
            case 18199:
                itemDef.actions = new String[5];
                
                break;

            case 14024:
                itemDef.modelID = 64997;
                itemDef.name = "Drygore Rapier";
                itemDef.description = "A powerful rapier made from the chitlin of the Kalphite King.".getBytes();
                itemDef.modelZoom = 1493;
                itemDef.rotationY = 618;
                itemDef.rotationX = 996;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64998;
                itemDef.femaleEquip1 = 64999;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Destroy";
                
                break;

            case 16055:
            case 16077:
            case 16066:
            case 16011:
            case 16114:
                itemDef.name = itemDef.name.replace("Tyrannoleather", "Swoodoo");
                itemDef.name = itemDef.name.replace("(b)", "");
                itemDef.rdc2 = 328593;
                
                break;
            case 18352:
            case 18354:
            case 18350:
            case 18358:
            case 18356:
            case 18360:
                itemDef.name = itemDef.name.replace("(broken)", "");
                itemDef.name = itemDef.name.replace("Chaotic", "Chaos");
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.rdc2 = 876543;
                
                break;

            case 16184:
                itemDef.name = itemDef.name.replace("Primal", "Frozen");
                itemDef.name = itemDef.name.replace("(b)", "");
                itemDef.rdc2 = 61161;
                
                break;
            case 16045:
                itemDef.name = itemDef.name.replace("Primal", "Frozen");
                itemDef.name = itemDef.name.replace("(b)", "");
                itemDef.rdc2 = 61161;
                
                break;
            case 16217:
                itemDef.name = itemDef.name.replace("Primal", "Frozen");
                itemDef.name = itemDef.name.replace("(b)", "");
                itemDef.rdc2 = 61161;
                
                break;
            case 15785:
                itemDef.name = itemDef.name.replace("Sagittarian", "Frozen");
                itemDef.name = itemDef.name.replace("(b)", "");
                itemDef.rdc2 = 61161;
                
                break;


            case 18351:
            case 18353:
            case 18349:
            case 18357:
            case 18355:
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 19670:
                itemDef.name = "Vote scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                break;
            case 10944:
                itemDef.name = "Member scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                break;

            case 10034:
            case 10033:
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                break;
            case 13727:
                itemDef.actions = new String[]{null, null, null, null, "Destroy"};
                break;
            case 6500:
                itemDef.modelID = 9123;
                itemDef.name = "Charming imp";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, null, "Check", "Config", "Destroy"};
                break;
            case 15332:
                itemDef.oldColors = new int[1];
                itemDef.newColors = new int[1];
                itemDef.oldColors[0] = 61;
                itemDef.newColors[0] = 0;
                itemDef.modelID = 2789;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = -5;
                itemDef.rotationY = 84;
                itemDef.rotationX = 1996;
                itemDef.modelZoom = 550;
                break;
            case 15331:
                itemDef.name = "<shad=1><col=ff0000>Super Overload";
                break;

            case 11995:
                itemDef.name = "Pet Chaos elemental";
                ItemDefinition itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11996:
                itemDef.name = "Pet King black dragon";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11997:
                itemDef.name = "Pet General graardor";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11978:
                itemDef.name = "Pet TzTok-Jad";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 12001:
                itemDef.name = "Pet Corporeal beast";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 12002:
                itemDef.name = "Pet Kree'arra";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 12003:
                itemDef.name = "Pet K'ril tsutsaroth";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;

                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 12004:
                itemDef.name = "Pet Commander zilyana";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 12005:
                itemDef.name = "Pet Dagannoth supreme";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 12006:
                itemDef.name = "Pet Dagannoth prime";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 17992:
                itemDef.name = "Pet Naked lady";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11990:
                itemDef.name = "Pet Dagannoth rex";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11991:
                itemDef.name = "Pet Frost dragon";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11992:
                itemDef.name = "Pet Tormented demon";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11993:
                itemDef.name = "Pet Kalphite queen";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11994:
                itemDef.name = "Pet Slash bash";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;

            case 989:
                itemDef.actions = new String[]{"@cya@Teleport To Chest", null, null, null, null};
                break;
            case 11989:
                itemDef.name = "Pet Phoenix";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11988:
                itemDef.name = "Pet Bandos avatar";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11987:
                itemDef.name = "Pet Nex";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11986:
                itemDef.name = "Pet Jungle strykewyrm";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11985:
                itemDef.name = "Pet Desert strykewyrm";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11984:
                itemDef.name = "Pet Ice strykewyrm";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11983:
                itemDef.name = "Pet Green dragon";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11982:
                itemDef.name = "Pet Baby blue dragon";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11981:
                itemDef.name = "Pet Blue dragon";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 11979:
                itemDef.name = "Pet Black dragon";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            // custom pets
            case 22014:
                itemDef.name = "Pet Skeleton Hellhound";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22015:
                itemDef.name = "Pet Maze Guardian";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22016:
                itemDef.name = "Pet Skeleton Warlord";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22017:
                itemDef.name = "Pet Penguin";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22018:
                itemDef.name = "Pet Druid";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22019:
                itemDef.name = "Pet Monkey Guard";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22020:
                itemDef.name = "Pet Clockwork Cat";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22021:
                itemDef.name = "Pet Jubbly Bird";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22022:
                itemDef.name = "Pet Dust Devil";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22023:
                itemDef.name = "Pet Abyssal Demon";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22024:
                itemDef.name = "Pet Chinchompa";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22025:
                itemDef.name = "Pet Golem";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22026:
                itemDef.name = "Pet Chaos Dwarf";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22027:
                itemDef.name = "Pet Shark";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22028:
                itemDef.name = "Pet Goblin Cook";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22029:
                itemDef.name = "Pet Fire Elemental";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22030:
                itemDef.name = "Pet Tree Spirit";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22031:
                itemDef.name = "Pet Leprechaun";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;

            case 22032:

                itemDef.name = "Night spider pet (@bla@x1.5 EXP - X1.5 DMG BOOST@or1@)";
                itemDef21 = ItemDefinition.get(18504);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;

            case 22033:
                itemDef.name = "Pet serpentine";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22049:
                itemDef.name = "Pet tanzanite";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22051:
                itemDef.name = "<img=101> @cya@Bunny's letter";
                itemDef.modelID = 10013;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 7;
                itemDef.rotationY = 316;
                itemDef.rotationX = 184;
                itemDef.modelZoom = 960;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{"Read", null, null, null, "Destroy"};
                break;
            case 22050:
                itemDef.name = "Pet magma";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 22055:
                itemDef.name = "Pet WildyWyrm";
                itemDef21 = ItemDefinition.get(12458);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Destroy"};
                break;
            case 14667:
                itemDef.name = "Zombie fragment";
                itemDef.modelID = ItemDefinition.get(14639).modelID;
                break;
            case 20054:
                itemDef.name = "Starter ring";
                itemDef.rdc2 = 20402;
                break;
            case 2550:
                itemDef.actions = new String[]{null, "Wear", "Check-charges", "Break", null, null};
                break;
            case 15182:
                itemDef.actions[0] = "Bury";
                break;
            case 15084:
                itemDef.actions[0] = "Roll";
                itemDef.name = "Dice (up to 100)";
                itemDef21 = ItemDefinition.get(15098);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                break;
            case 2996:
                itemDef.name = "Agility ticket";
                break;
            case 5510:
            case 5512:
            case 5509:
            case 5514:
                itemDef.actions = new String[]{"Fill", null, "Empty", "Check", null, null};
                break;
            case 11998:
                itemDef.name = "Scimitar";
                itemDef.actions = new String[5];
                
                break;
            case 11999:
                itemDef.name = "Scimitar";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 700;
                itemDef.rotationY = 0;
                itemDef.rotationX = 350;
                itemDef.modelID = 2429;
                itemDef.rotationZ = itemDef.modelOffsetX = 0;
                itemDef.stackable = true;
                itemDef.noteTemplate = 11998;
                itemDef.note = 799;
                break;
            case 1389:
                itemDef.name = "Staff";
                itemDef.actions = new String[5];
                
                break;
            case 1390:
                itemDef.name = "Staff";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 17401:
                itemDef.name = "Damaged Hammer";
                itemDef.actions = new String[5];
                
                break;
            case 17402:
                itemDef.name = "Damaged Hammer";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationY = 28;
                itemDef.rotationX = 552;
                itemDef.modelID = 2429;
                itemDef.rotationZ = itemDef.modelOffsetX = 0;
                itemDef.stackable = true;
                itemDef.noteTemplate = 17401;
                itemDef.note = 799;
                break;
            case 15009:
                itemDef.name = "Gold Ring";
                itemDef.actions = new String[5];
                                break;
            case 15010:
                itemDef.modelID = 2429;
                itemDef.name = "Gold Ring";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationY = 28;
                itemDef.rotationX = 552;
                itemDef.rotationZ = itemDef.modelOffsetX = 0;
                itemDef.stackable = true;
                itemDef.noteTemplate = 15009;
                itemDef.note = 799;
                break;
            case 11884:
            case 15420:
                itemDef.actions = new String[]{"Open", null, null, null, null, null};
                break;

            case 15262:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.actions[2] = "Open-All";
                break;
            case 6570:
                itemDef.actions[2] = "Upgrade";
                break;
            case 4155:
                itemDef.name = "Slayer gem";
                itemDef.actions = new String[]{"Activate", null, "Check", "Partner", "Destroy"};
                break;
            case 13663:
                itemDef.name = "Stat reset cert.";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Open";
                break;
            case 13653:
                itemDef.name = "Energy fragment";
                break;
            case 292:
                itemDef.name = "Ingredients book";
                break;
            case 4882:

                itemDef.name = "Amonite helm";
                // itemDef.rdc2 = 57306150;
                
                break;
            case 4888:
                itemDef.name = "Amonite axe";
                
                // itemDef.rdc2 = 57306150;
                break;
            case 4894:
                itemDef.name = "Amonite platebody";
                
                // itemDef.rdc2 = 57306150;
                break;
            case 4900:
                itemDef.name = "Amonite platelegs";
                
                // itemDef.rdc2 = 57306150;
                break;
            case 18747:
                itemDef.name = "Amonite shield";
                
                // itemDef.rdc2 = 57306150;
                break;
            case 20460:
                itemDef.name = "Amonite glove";
                
                // itemDef.rdc2 = 57306150;
                break;
            case 20456:
                itemDef.name = "Amonite boots";
                
                // itemDef.rdc2 = 57306150;
                break;
            case 15707:
                itemDef.actions = new String[5];
                // itemDef.actions[1] = "Wear";
                // itemDef.actions[0] = "Create Party";
                break;
            case 14501:
                itemDef.modelID = 44574;
                itemDef.maleEquip1 = 43693;
                itemDef.femaleEquip1 = 43693;
                break;
            case 19111:
                itemDef.name = "TokHaar-Kal";
                itemDef.maleEquip1 = 62575;
                itemDef.femaleEquip1 = 62582;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.modelOffsetX = -4;
                itemDef.modelID = 62592;
                itemDef.description = "A cape made of ancient, enchanted rocks.".getBytes();
                itemDef.modelZoom = 1616;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.rotationZ = 0;
                itemDef.rotationY = 339;
                
                itemDef.rotationX = 192;
                // itemDef.rdc2 = 16368;//this is for you dope :)
                break;
            case 6769:
                itemDef.name = "@gre@$5 Bond";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                itemDef.actions[3] = "Exchange-All";
                itemDef.modelID = 99900;
                itemDef.modelZoom = 2400;
                itemDef21 = ItemDefinition.get(10942);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                break;
            case 10942:
                itemDef.name = "@red@$10 Bond";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                itemDef.actions[3] = "Exchange-All";
                itemDef.modelID = 99901;;
                itemDef.modelZoom = 2400;
                break;
            case 10934:
                itemDef.name = "@yel@$25 Bond";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                itemDef.actions[3] = "Exchange-All";
                itemDef.modelID = 99903;
                itemDef21 = ItemDefinition.get(10942);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelZoom = 2400;
                break;
            case 10935:
                itemDef.name = "@blu@$50 Bond";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                itemDef.actions[3] = "Exchange-All";
                itemDef.modelID = 99902;
                itemDef21 = ItemDefinition.get(10942);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelZoom = 2400;
                break;
            case 10943:
                itemDef.name = "@mag@$100 Bond";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                itemDef.modelID = 99902;
                itemDef.modelZoom = 2300;
                itemDef.rdc2 = 661177;//661177

                break;
            case 7630:
                itemDef.name = "Zulrah's Scale Box";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(7630);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                break;
            case 11261:
            case 1748:
            case 1750:
            case 1752:
            case 1754:
            case 228:
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Un-note";
                break;
            case 13150:
                itemDef.name = "Spooky Box";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(13150);
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;

                break;
            case 9013:
                itemDef.name = "Skull sceptre";
                itemDef.actions = new String[]{"Spook", "Wear", null, null, "Destroy"};
                itemDef21 = ItemDefinition.get(9013);
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                break;
            case 995:
                itemDef.name = "Coins";
                itemDef.actions = new String[5];
                itemDef.actions[3] = "@gre@Convert to 1-bill";
                itemDef.actions[2] = "Convert to 1-mill";
                itemDef.actions[0] = "Add to pouch";
                break;

            case 17291:
                itemDef.name = "Naturespawn necklace";
                itemDef.rdc = 25262;
                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                

                break;
            case 20084:
                itemDef.name = "Golden Maul";
                break;
            case 6199:
                itemDef.name = "Mystery Box";
                itemDef.actions = new String[5];

                itemDef.actions[0] = "Open";
                break;

            case 290:
                itemDef.name = "Loot box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 6198:
                itemDef.name = "3% Droprate Pet";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{66};
                itemDef.modelID = 65284;
                break;
            case 10947:
                itemDef.name = "@red@Supreme Attachment";

                itemDef21 = ItemDefinition.get(12159);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.modelID = itemDef21.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.rdc2 = 416546661;
                itemDef.stackable = true;
                break;
            case 10949:
                itemDef.name = "@whi@Light Attachment";

                itemDef21 = ItemDefinition.get(12159);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.modelID = itemDef21.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.rdc2 = 123412;
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, null, "Disassemble", null, "Destroy"};
                break;
            case 15501:
                itemDef.name = "Vote Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = 1600;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                //	itemDef.modifiedModelColors = new int[] { 63 };
                //	itemDef.originalModelColors = new int[] { 62 };
                itemDef.modelID = 64099;
                break;
            case 20488:
                itemDef.name = "<col=2299099><shad=12992>OP Chest<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = 20;
                itemDef.rotationZ = 50;
                itemDef.modelOffsetY = -50;
                itemDef.modelZoom = 2555;
                // itemDef.modelID =8itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{62, 60};
                itemDef.newColors = new int[]{78, 57};

                itemDef.modelID = 64050;
                ;
                //	itemDef.rdc2 = 88898;
                break;
            case 20489:
                itemDef.name = "<col=C02FFE><shad=0>Launch Chest<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 1;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = 100;
                itemDef.rotationX = 90;
                itemDef.modelID = 64300;
                itemDef.rdc2 = 12111;

                break;

            case 15002:
                itemDef.name = "@red@<shad=0>Ruby Casket<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 1;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = 100;
                itemDef.rotationX = 90;
                itemDef.modelID = 64300;
                itemDef.rdc2 = 92111;
                break;
            case 15355:
                itemDef.name = "@gre@<shad=0>100% DR Scroll (1 Hour)<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Activate";
                itemDef.rdc2 = 921211;
                break;
            case 15356:
                itemDef.name = "@gre@<shad=0>Double DDR Scroll (1 Hour)<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Activate";
                itemDef.rdc2 = 348132;
                break;
            case 15357:
                itemDef.name = "@gre@<shad=0>50% Damage Scroll (1 Hour)<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Activate";
                itemDef.rdc2 = 23333;
                break;
            case 15358:
                itemDef.name = "@gre@<shad=0>100% DR Scroll (30min)<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Activate";
                itemDef.rdc2 = 663712;
                break;
            case 15359:
                itemDef.name = "@gre@<shad=0>50% Damage Scroll (30min)<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Activate";
                itemDef.rdc2 = 12411;
                break;
            case 15003:
                itemDef.name = "<col=C5CEDF><shad=0>Silver Casket<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 1;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = 100;
                itemDef.rotationX = 90;
                itemDef.modelID = 64300;
                itemDef.rdc2 = 988331;
                break;
            case 6542:
                itemDef.name = "@bla@<shad=0>Dan's Present<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 1;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = 100;
                itemDef.rotationX = 90;
                itemDef.rdc2 = 28312;
                break;
            case 15004:
                itemDef.name = "@cya@<shad=0>Diamond Casket<shad-1>";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 1;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = 100;
                itemDef.rotationX = 90;
                itemDef.modelID = 64300;
                itemDef.rdc2 = 129911;
                break;
            case 19659:
                itemDef.name = "Super Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = 1600;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;

                itemDef.modelID = 64099;
                itemDef.rdc2 = 32521;
                break;
            case 19114:
                itemDef.name = "Grand Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{66};
                itemDef.modelID = 65284;
                break;
            case 19117:
                itemDef.name = "@whi@Execution Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{87};
                itemDef.modelID = 65284;
                break;
            case 18404:
                itemDef.name = "@whi@Raid Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{76};
                itemDef.modelID = 65284;
                break;
            case 19115:
                itemDef.name = "Extreme Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{64};
                itemDef.modelID = 65284;
                break;
            case 19116:
                itemDef.name = "Super Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{65};
                itemDef.modelID = 65284;
                
                break;
            case 6568: // To replace Transparent black with opaque black.
                itemDef.oldColors = new int[1];
                itemDef.newColors = new int[1];
                itemDef.oldColors[0] = 0;
                itemDef.newColors[0] = 2059;
                break;
            case 13262:
                itemDef.name = "Dragon Defender";
                itemDef21 = ItemDefinition.get(20072);
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.name = itemDef2.name;
                itemDef.actions = itemDef21.actions;
                break;
            case 996:
            case 997:
            case 998:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            case 1003:
            case 1004:
                itemDef.name = "Coins";
                break;

            case 4442:
                itemDef21 = ItemDefinition.get(19890);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.name = "@red@X1.5 Damage Booster";
                itemDef.rdc2 = 99252;

                break;
            case 4440:
                itemDef21 = ItemDefinition.get(19890);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.name = "@cya@X1.5 DR Booster";
                itemDef.rdc2 = 35252;

                break;
            case 4438:
                itemDef21 = ItemDefinition.get(19890);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.name = "@gre@X2 DDR Booster";
                itemDef.rdc2 = 19252;

                break;
            case 14017:
                itemDef.name = "Brackish blade";
                itemDef.modelZoom = 1488;
                itemDef.rotationX = 276;
                itemDef.rotationY = 1580;
                itemDef.rotationX = 1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Destroy"};
                itemDef.modelID = 64593;
                itemDef.maleEquip1 = 64704;
                itemDef.femaleEquip1 = 64704;
                break;
            case 15220:
                itemDef.name = "Berserker ring (i)";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1916;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -15;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.modelID = 7735;
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                break;
            case 14019:
                itemDef.modelID = 65333;
                itemDef.name = "Max Cape";
                itemDef.description = "A cape worn by those who've achieved 99 in all skills.".getBytes();
                itemDef.modelZoom = 1385;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65334;
                itemDef.femaleEquip1 = 65334;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 14020:
                itemDef.name = "Veteran hood";
                itemDef.description = "A hood worn by Etherear veterans.".getBytes();
                itemDef.modelZoom = 760;
                itemDef.rotationY = 11;
                itemDef.rotationX = 81;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.modelID = 65271;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65314;
                break;
            case 14021:
                // itemDef.modelID = 65261;
                itemDef.name = "Veteran Cape";
                itemDef.description = "A cape worn by Necrotic's veterans.".getBytes();
                itemDef.modelZoom = 760;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                // itemDef.maleWearId = 65305;
                itemDef.modelID = 65333;
                itemDef.maleEquip1 = 65334;
                itemDef.femaleEquip1 = 65334;
                // itemDef.femaleWearId = 65318;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.rdc2 = 99252;
                break;
            case 14022:
                // itemDef.modelID = 65270;
                itemDef.name = "Completionist Cape";
                itemDef.description = "We'd pat you on the back, but this cape would get in the way.".getBytes();
                itemDef.modelZoom = 1385;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                // itemDef.modifiedModelColors = new int[]{65214, 65200, 65186, 62995};
                // itemDef.originalModelColors = new int[]{44988, 44988, 32463, 44988};
                // itemDef.maleWearId = 65297;
                // itemDef.femaleWearId = 65297;
                itemDef.modelID = 65333;
                itemDef.maleEquip1 = 65334;
                itemDef.femaleEquip1 = 65334;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.rdc2 = 25262;
                break;
            case 14004:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.oldColors = new int[11];
                itemDef.newColors = new int[11];
                itemDef.oldColors[0] = 7860;
                itemDef.newColors[0] = 38310;
                itemDef.oldColors[1] = 7876;
                itemDef.newColors[1] = 38310;
                itemDef.oldColors[2] = 7892;
                itemDef.newColors[2] = 38310;
                itemDef.oldColors[3] = 7884;
                itemDef.newColors[3] = 38310;
                itemDef.oldColors[4] = 7868;
                itemDef.newColors[4] = 38310;
                itemDef.oldColors[5] = 7864;
                itemDef.newColors[5] = 38310;
                itemDef.oldColors[6] = 7880;
                itemDef.newColors[6] = 38310;
                itemDef.oldColors[7] = 7848;
                itemDef.newColors[7] = 38310;
                itemDef.oldColors[8] = 7888;
                itemDef.newColors[8] = 38310;
                itemDef.oldColors[9] = 7872;
                itemDef.newColors[9] = 38310;
                itemDef.oldColors[10] = 7856;
                itemDef.newColors[10] = 38310;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                break;

            case 14005:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.oldColors = new int[11];
                itemDef.newColors = new int[11];
                itemDef.oldColors[0] = 7860;
                itemDef.newColors[0] = 432;
                itemDef.oldColors[1] = 7876;
                itemDef.newColors[1] = 432;
                itemDef.oldColors[2] = 7892;
                itemDef.newColors[2] = 432;
                itemDef.oldColors[3] = 7884;
                itemDef.newColors[3] = 432;
                itemDef.oldColors[4] = 7868;
                itemDef.newColors[4] = 432;
                itemDef.oldColors[5] = 7864;
                itemDef.newColors[5] = 432;
                itemDef.oldColors[6] = 7880;
                itemDef.newColors[6] = 432;
                itemDef.oldColors[7] = 7848;
                itemDef.newColors[7] = 432;
                itemDef.oldColors[8] = 7888;
                itemDef.newColors[8] = 432;
                itemDef.oldColors[9] = 7872;
                itemDef.newColors[9] = 432;
                itemDef.oldColors[10] = 7856;
                itemDef.newColors[10] = 432;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                break;

            case 14006:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.oldColors = new int[11];
                itemDef.newColors = new int[11];
                itemDef.oldColors[0] = 7860;
                itemDef.newColors[0] = 24006;
                itemDef.oldColors[1] = 7876;
                itemDef.newColors[1] = 24006;
                itemDef.oldColors[2] = 7892;
                itemDef.newColors[2] = 24006;
                itemDef.oldColors[3] = 7884;
                itemDef.newColors[3] = 24006;
                itemDef.oldColors[4] = 7868;
                itemDef.newColors[4] = 24006;
                itemDef.oldColors[5] = 7864;
                itemDef.newColors[5] = 24006;
                itemDef.oldColors[6] = 7880;
                itemDef.newColors[6] = 24006;
                itemDef.oldColors[7] = 7848;
                itemDef.newColors[7] = 24006;
                itemDef.oldColors[8] = 7888;
                itemDef.newColors[8] = 24006;
                itemDef.oldColors[9] = 7872;
                itemDef.newColors[9] = 24006;
                itemDef.oldColors[10] = 7856;
                itemDef.newColors[10] = 24006;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                break;
            case 14007:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.oldColors = new int[11];
                itemDef.newColors = new int[11];
                itemDef.oldColors[0] = 7860;
                itemDef.newColors[0] = 14285;
                itemDef.oldColors[1] = 7876;
                itemDef.newColors[1] = 14285;
                itemDef.oldColors[2] = 7892;
                itemDef.newColors[2] = 14285;
                itemDef.oldColors[3] = 7884;
                itemDef.newColors[3] = 14285;
                itemDef.oldColors[4] = 7868;
                itemDef.newColors[4] = 14285;
                itemDef.oldColors[5] = 7864;
                itemDef.newColors[5] = 14285;
                itemDef.oldColors[6] = 7880;
                itemDef.newColors[6] = 14285;
                itemDef.oldColors[7] = 7848;
                itemDef.newColors[7] = 14285;
                itemDef.oldColors[8] = 7888;
                itemDef.newColors[8] = 14285;
                itemDef.oldColors[9] = 7872;
                itemDef.newColors[9] = 14285;
                itemDef.oldColors[10] = 7856;
                itemDef.newColors[10] = 14285;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                break;
            case 14003:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.oldColors = new int[3];
                itemDef.newColors = new int[3];
                itemDef.oldColors[0] = 15009;
                itemDef.newColors[0] = 30847;
                itemDef.oldColors[1] = 17294;
                itemDef.newColors[1] = 32895;
                itemDef.oldColors[2] = 15252;
                itemDef.newColors[2] = 30847;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                break;

            case 14001:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.oldColors = new int[3];
                itemDef.newColors = new int[3];
                itemDef.oldColors[0] = 15009;
                itemDef.newColors[0] = 10015;
                itemDef.oldColors[1] = 17294;
                itemDef.newColors[1] = 7730;
                itemDef.oldColors[2] = 15252;
                itemDef.newColors[2] = 7973;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                break;

            case 14002:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.oldColors = new int[3];
                itemDef.newColors = new int[3];
                itemDef.oldColors[0] = 15009;
                itemDef.newColors[0] = 35489;
                itemDef.oldColors[1] = 17294;
                itemDef.newColors[1] = 37774;
                itemDef.oldColors[2] = 15252;
                itemDef.newColors[2] = 35732;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                break;

            case 14000:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.oldColors = new int[3];
                itemDef.newColors = new int[3];
                itemDef.oldColors[0] = 15009;
                itemDef.newColors[0] = 3745;
                itemDef.oldColors[1] = 17294;
                itemDef.newColors[1] = 3982;
                itemDef.oldColors[2] = 15252;
                itemDef.newColors[2] = 3988;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetY = -5;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                break;
            case 20000:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.modelID = 53835;
                itemDef.name = "Steadfast boots";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = 53327;
                itemDef.femaleEquip1 = 53643;
                itemDef.description = "A pair of Steadfast boots.".getBytes();
                break;

            case 20001:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.modelID = 53828;
                itemDef.name = "Glaiven boots";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -7;
                itemDef.femaleEquip1 = 53309;
                itemDef.maleEquip1 = 53309;
                itemDef.description = "A pair of Glaiven boots.".getBytes();
                break;

            case 20002:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.description = "A pair of Ragefire boots.".getBytes();
                itemDef.modelID = 53897;
                itemDef.name = "Ragefire boots";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = 53330;
                itemDef.femaleEquip1 = 53651;
                break;
            case 14018:
                itemDef.modelID = 6277;

                itemDef.name = "@whi@<shad=255>Tornado Katana<shad=-1>";
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
                

                break;
            case 14008:
                itemDef.modelID = 62714;
                itemDef.name = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 62738;
                itemDef.femaleEquip1 = 62754;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                

                break;
            case 14009:
                itemDef.modelID = 62699;
                itemDef.name = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 62746;
                itemDef.femaleEquip1 = 62762;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                

                break;

            case 14010:
                itemDef.modelID = 62701;
                itemDef.name = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 62743;
                itemDef.femaleEquip1 = 62760;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                

                break;

            case 20556:
                itemDef.modelID = 65341;
                itemDef.maleEquip1 = 65342;
                itemDef.femaleEquip1 = 65342;
                itemDef.name = "<col=ff6f6f>Super Glaive";
                //	itemDef.rdc2 = 8008;
                break;
            case 19749:
                itemDef.modelID = 65345;
                itemDef.maleEquip1 = 65346;
                itemDef.femaleEquip1 = 65346;
                itemDef.name = "Wing kitesield";
                
                break;
            case 10638:
                itemDef.modelID = 65349;
                itemDef.maleEquip1 = 65350;
                itemDef.femaleEquip1 = 65350;
                itemDef.name = "<col=ff6f6f>Inferno Cape";
                break;
            case 12634:
                itemDef.modelID = 65351;
                itemDef.maleEquip1 = 65352;
                itemDef.femaleEquip1 = 65352;
                itemDef.name = "<col=ff6f6f>Inferno Wings";
                
                break;
            case 20549:
                itemDef.modelID = 65347;
                itemDef.maleEquip1 = 65348;
                itemDef.femaleEquip1 = 65348;
                itemDef.name = "Shikruu Katana";
                itemDef.newColors = new int[1]; // same here
                itemDef.oldColors = new int[1];
                itemDef.oldColors[0] = 63; // the texture that it currently has
                itemDef.newColors[0] = 59;
                
                break;
            case 20173:
                itemDef.modelID = 65343;
                itemDef.maleEquip1 = 65344;
                itemDef.femaleEquip1 = 65344;
                itemDef.name = "Sorrow Bow";
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{68, 78};
                itemDef.actions[2] = null;
                
                break;
            case 20557:
                itemDef.modelID = 65341;
                itemDef.maleEquip1 = 65342;
                itemDef.femaleEquip1 = 65342;
                itemDef.name = "<col=ff6f6f>Nex Glaive";
                itemDef.oldColors = new int[]{60, 62};
                itemDef.newColors = new int[]{66, 66};
                
                break;
            case 20552:
                itemDef.modelID = 65341;
                itemDef.maleEquip1 = 65342;
                itemDef.femaleEquip1 = 65342;
                itemDef.name = "Soul Glaive";
                itemDef.oldColors = new int[]{60, 62};
                itemDef.newColors = new int[]{96, 96};
                ///	itemDef.rdc2 = 5006;
                break;
            case 6927:
                itemDef.modelID = 65335;
                itemDef.name = "Torva full helm";
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.maleEquip1 = 65336;
                itemDef.femaleEquip1 = 65336;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{61, 61};
                itemDef.stackable = false;
                /// itemDef.rdc2 = 607607607;
                break;
            case 6928:
                itemDef.stackable = false;
                itemDef.modelID = 65337;
                itemDef.name = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65338;
                itemDef.femaleEquip1 = 65338;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{61, 61};
                break;

            case 6929:
                itemDef.stackable = false;
                itemDef.modelID = 65339;
                itemDef.name = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 65340;
                itemDef.femaleEquip1 = 65340;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{61, 61};
                break;
            case 6930:
                itemDef.stackable = false;
                itemDef.modelID = 65212;
                itemDef.maleEquip1 = 65213;
                itemDef.femaleEquip1 = 65213;
                itemDef.name = "Pernix cowl";
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                itemDef.maleDialogue = 62731;
                itemDef.femaleDialogue = 62727;
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{68, 59};
                

                break;
            case 6931:
                itemDef.stackable = false;
                itemDef.modelID = 65214;
                itemDef.maleEquip1 = 65215;
                itemDef.femaleEquip1 = 65215;
                itemDef.name = "Pernix body";
                itemDef.modelZoom = 1378;
                itemDef.rotationY = 485;
                itemDef.rotationX = 2042;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 7;

                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{68, 59};
                

                break;

            case 6932:
                itemDef.stackable = false;
                itemDef.modelID = 65216;
                itemDef.maleEquip1 = 65217;
                itemDef.femaleEquip1 = 65217;
                itemDef.name = "Pernix chaps";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 504;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 3;

                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{68, 59};
                

                break;
            case 6933:// arlox
                itemDef.stackable = false;
                itemDef.modelID = 65218;
                itemDef.maleEquip1 = 65219;
                itemDef.femaleEquip1 = 65219;
                itemDef.name = "Virtus mask";
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                itemDef.maleDialogue = 62728;
                itemDef.femaleDialogue = 62728;
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{69, 60};
                

                break;

            case 6934:
                itemDef.stackable = false;
                itemDef.modelID = 65220;
                itemDef.maleEquip1 = 65221;
                itemDef.femaleEquip1 = 65221;
                itemDef.name = "Virtus robe top";
                itemDef.modelZoom = 1122;
                itemDef.rotationY = 488;
                itemDef.rotationX = 3;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{69, 60};
                break;

            case 6935:
                itemDef.stackable = false;
                itemDef.modelID = 65222;
                itemDef.maleEquip1 = 65223;
                itemDef.femaleEquip1 = 65223;
                itemDef.name = "Virtus robe legs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 498;
                itemDef.rotationX = 2045;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 4;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                itemDef.oldColors = new int[]{24, 40};
                itemDef.newColors = new int[]{69, 60};
                break;
            case 14011:
                itemDef.modelID = 62693;
                itemDef.name = "Pernix cowl";
                itemDef.modelZoom = 800;
                itemDef.rotationY = 532;
                itemDef.rotationX = 14;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 62739;
                itemDef.femaleEquip1 = 62756;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.maleDialogue = 62731;
                itemDef.femaleDialogue = 62727;
                

                /*
                 * itemDef.originalModelColors = new int[2]; itemDef.modifiedModelColors = new
                 * int[2]; itemDef.modifiedModelColors[0] = 48543;
                 * itemDef.originalModelColors[0] = 86933; itemDef.modifiedModelColors[1] =
                 * 49567; itemDef.originalModelColors[1] = 86933;
                 */
                break;
            case 14012:
                itemDef.modelID = 62709;
                itemDef.name = "Pernix body";
                itemDef.modelZoom = 1378;
                itemDef.rotationY = 485;
                itemDef.rotationX = 2042;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 7;
                itemDef.maleEquip1 = 62744;
                itemDef.femaleEquip1 = 62765;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                

                break;

            case 14013:
                itemDef.modelID = 62695;
                itemDef.name = "Pernix chaps";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 504;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 3;
                itemDef.maleEquip1 = 62741;
                itemDef.femaleEquip1 = 62757;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                // itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Destroy";
                

                break;
            case 22036:
                itemDef.modelID = 5419;// 62693;
                itemDef.name = "Death's hood";
                itemDef.modelZoom = 730;// 800;
                itemDef.rotationY = 0;// 532;
                itemDef.rotationX = 2036;// 14;
                itemDef.modelOffsetX = 0;// -1;
                itemDef.modelOffsetY = 0;// 1;
                itemDef.maleEquip1 = 62739;
                itemDef.femaleEquip1 = 62756;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.maleDialogue = 62731;
                itemDef.femaleDialogue = 62727;
                itemDef.newColors = new int[10];
                itemDef.oldColors = new int[10];
                itemDef.oldColors[0] = 48543; // NORM (TRIM)
                itemDef.newColors[0] = 1030; // CHANGE (TRIM)
                itemDef.oldColors[1] = 49567; // NORM (TRIM)
                itemDef.newColors[1] = 1030; // CHANGE (TRIM)
                itemDef.oldColors[2] = 8741; // NORM (INV MODEL)
                itemDef.newColors[2] = 10; // CHANGE (INV MODEL)
                itemDef.oldColors[3] = 0; // NORM (FACE)
                itemDef.newColors[3] = 100; // CHANGE (FACE) -- 25 = grey, 100 = white, 0 = black
                itemDef.oldColors[4] = 13; // NORM
                itemDef.newColors[4] = 1030; // CHANGE
                itemDef.oldColors[5] = 11; // NORM
                itemDef.newColors[5] = 1030; // CHANGE
                itemDef.oldColors[6] = 18; // NORM
                itemDef.newColors[6] = 1030; // CHANGE
                itemDef.oldColors[7] = 10; // NORM
                itemDef.newColors[7] = 1030; // CHANGE
                itemDef.oldColors[8] = 16; // NORM
                itemDef.newColors[8] = 1030; // CHANGE
                itemDef.oldColors[8] = 1032; // NORM
                itemDef.newColors[8] = 16; // CHANGE
                break;

            case 22037:
                itemDef.modelID = 6578;// 62693;
                itemDef.name = "Death's robe top";
                itemDef.modelZoom = 1250;// 800;
                itemDef.rotationY = 468;// 532;
                itemDef.rotationX = 0;// 14;
                itemDef.modelOffsetX = 0;// -1;
                itemDef.modelOffsetY = 3;// 1;
                itemDef.maleEquip1 = 6669;
                itemDef.femaleEquip1 = 6669;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[5];
                itemDef.oldColors = new int[5];
                itemDef.oldColors[0] = 14490; // NORM
                itemDef.newColors[0] = 1030; // CHANGE
                itemDef.oldColors[1] = 10396; // NORM
                itemDef.newColors[1] = 1; // CHANGE
                itemDef.oldColors[2] = 10388; // NORM
                itemDef.newColors[2] = 4; // CHANGE
                itemDef.oldColors[3] = 8741; // NORM
                itemDef.newColors[3] = 1030; // CHANGE
                itemDef.oldColors[4] = 16652; // NORM
                itemDef.newColors[4] = 1; // CHANGE
                itemDef.maleEquip2 = 170;
                break;

            case 22038:
                itemDef.modelID = 6577;// 62693;
                itemDef.name = "Death's robe bottoms";
                itemDef.modelZoom = 1600;// 800;
                itemDef.rotationY = 500;// 532;
                itemDef.rotationX = 2036;// 14;
                itemDef.modelOffsetX = 0;// -1;
                itemDef.modelOffsetY = 0;// 1;
                itemDef.maleEquip1 = 6659;
                itemDef.femaleEquip1 = 6659;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[3];
                itemDef.oldColors = new int[3];
                itemDef.oldColors[0] = 14490; // NORM
                itemDef.newColors[0] = 1030; // CHANGE
                itemDef.oldColors[1] = 10396; // NORM
                itemDef.newColors[1] = 1; // CHANGE
                itemDef.oldColors[2] = 10388; // NORM
                itemDef.newColors[2] = 4; // CHANGE
                break;

            case 22039:
                itemDef.modelID = 2718;// 62693;
                itemDef.name = "Defiled shank";
                itemDef.modelZoom = 760;// 800;
                itemDef.rotationY = 472;// 532;
                itemDef.rotationX = 1276;// 14;
                itemDef.modelOffsetX = 0;// -1;
                itemDef.modelOffsetY = 0;// 1;
                itemDef.maleEquip1 = 539;
                itemDef.femaleEquip1 = 539;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Destroy"};
                itemDef.newColors = new int[4];
                itemDef.oldColors = new int[4];
                itemDef.oldColors[0] = 37; // NORM
                itemDef.newColors[0] = 1030; // CHANGE
                itemDef.oldColors[1] = 6036; // NORM
                itemDef.newColors[1] = 1030; // CHANGE
                itemDef.oldColors[2] = 924; // NORM
                itemDef.newColors[2] = 127; // CHANGE
                itemDef.oldColors[3] = 22459; // NORM
                itemDef.newColors[3] = 924; // CHANGE
                break;

            case 22052:
                itemDef.name = "@bla@[@yel@Prestige cape@bla@]@whi@";
                itemDef.modelID = 56785;
                itemDef.maleEquip1 = 55904;
                itemDef.femaleEquip1 = 56557;
                itemDef.newColors = new int[17];
                itemDef.oldColors = new int[17];
                itemDef.oldColors[0] = 54352; // NORM
                itemDef.newColors[0] = 11200; // CHANGE
                itemDef.oldColors[1] = 54307; // NORM
                itemDef.newColors[1] = 10; // CHANGE
                itemDef.oldColors[2] = 54317; // NORM
                itemDef.newColors[2] = 20; // CHANGE
                itemDef.oldColors[3] = 54312; // NORM
                itemDef.newColors[3] = 15; // CHANGE
                itemDef.oldColors[4] = 54302; // NORM
                itemDef.newColors[4] = 8; // CHANGE
                itemDef.oldColors[5] = 54322; // NORM
                itemDef.newColors[5] = 28; // CHANGE
                itemDef.oldColors[6] = 54315; // NORM
                itemDef.newColors[6] = 21; // CHANGE
                itemDef.oldColors[7] = 54310; // NORM
                itemDef.newColors[7] = 13; // CHANGE
                itemDef.oldColors[8] = 54297; // NORM
                itemDef.newColors[8] = 1; // CHANGE
                itemDef.oldColors[9] = 54292; // NORM
                itemDef.newColors[9] = 1; // CHANGE
                itemDef.oldColors[10] = 54316; // NORM
                itemDef.newColors[10] = 9; // CHANGE
                itemDef.oldColors[11] = 54311; // NORM
                itemDef.newColors[11] = 4; // CHANGE
                itemDef.oldColors[12] = 54318; // NORM
                itemDef.newColors[12] = 20; // CHANGE
                itemDef.oldColors[13] = 54313; // NORM
                itemDef.newColors[13] = 14; // CHANGE
                itemDef.oldColors[14] = 54308; // NORM
                itemDef.newColors[14] = 11; // CHANGE
                itemDef.oldColors[15] = 54319; // NORM
                itemDef.newColors[15] = 22; // CHANGE
                itemDef.oldColors[16] = 54320; // NORM
                itemDef.newColors[16] = 23; // CHANGE
                itemDef.modelOffsetX = -1;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                itemDef.rotationY = 252;
                itemDef.rotationX = 54;
                itemDef.modelZoom = 1616;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};

                break;

            case 22064:
                itemDef.name = "Malediction ward";
                itemDef.modelID = 9354;
                itemDef.modelZoom = 1200;
                itemDef.rotationY = 568;
                itemDef.rotationX = 1836;
                itemDef.rotationZ = 2;
                itemDef.modelOffsetY = 3;
                itemDef.oldColors = new int[]{908};
                itemDef.newColors = new int[]{-21608};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.maleEquip1 = 9347;
                itemDef.femaleEquip1 = 9347;
                break;

            case 22066:
                itemDef.name = "Odium ward";
                itemDef.modelID = 9354;
                itemDef.modelZoom = 1200;
                itemDef.rotationY = 568;
                itemDef.rotationX = 1836;
                itemDef.rotationZ = 2;
                itemDef.modelOffsetY = 3;
                itemDef.oldColors = new int[]{908};
                itemDef.newColors = new int[]{15252};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.maleEquip1 = 9347;
                itemDef.femaleEquip1 = 9347;
                break;

            case 22041:
                itemDef.name = "Black h'ween mask";
                itemDef.modelID = 2438;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = -10;
                itemDef.rotationY = 516;
                itemDef.rotationX = 0;
                itemDef.modelZoom = 730;
                itemDef.newColors = new int[2];
                itemDef.oldColors = new int[2];
                itemDef.oldColors[0] = 0; // NORM
                itemDef.newColors[0] = 11200; // CHANGE --EYES
                itemDef.oldColors[1] = 926; // NORM
                itemDef.newColors[1] = 4; // CHANGE --MASK COLOR
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.maleEquip1 = 3188;
                itemDef.femaleEquip1 = 3192;
                break;

            case 22045:
                itemDef.name = "Dragonstone ring (e)";
                itemDef.modelID = 47752;
                itemDef.modelOffsetX = -1;
                itemDef.rotationZ = 2042;
                itemDef.modelOffsetY = 1;
                itemDef.rotationY = 322;
                itemDef.rotationX = 135;
                itemDef.modelZoom = 830;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                break;

            case 22047:
                itemDef.name = "Giant snake spine";
                itemDef.modelID = 48229;
                itemDef.modelOffsetX = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;
                itemDef.rotationY = 201;
                itemDef.rotationX = 1649;
                itemDef.modelZoom = 2000;
                itemDef.actions = new String[]{"Bury", null, null, null, "Destroy"};
                itemDef.maleEquip1 = -1;
                itemDef.femaleEquip1 = -1;
                break;

            case 22040:
                itemDef.modelID = 2543;// 62693;
                itemDef.name = "The Lunite book";
                itemDef.modelZoom = 760;// 800;
                itemDef.rotationY = 244;// 532;
                itemDef.rotationX = 116;// 14;
                itemDef.modelOffsetX = 1;// -1;
                itemDef.modelOffsetY = 0;// 1;
                itemDef.maleEquip1 = 556;
                itemDef.femaleEquip1 = 556;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Destroy"};
                itemDef.newColors = new int[4];
                itemDef.oldColors = new int[4];
                itemDef.oldColors[0] = 11177; // NORM
                itemDef.newColors[0] = 1030; // CHANGE
                itemDef.oldColors[1] = 61; // NORM
                itemDef.newColors[1] = 1030; // CHANGE
                itemDef.oldColors[2] = 5018; // NORM
                itemDef.newColors[2] = 16; // CHANGE
                itemDef.oldColors[3] = 10351; // NORM
                itemDef.newColors[3] = 50; // CHANGE
                break;

            case 22043:
                itemDef.modelID = 7702;
                itemDef.name = "Santa sack";
                itemDef.modelZoom = 2280;// 800;
                itemDef.rotationY = 64;// 532;
                itemDef.rotationX = 112;// 14;
                itemDef.modelOffsetX = 0;// -1;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetY = 0;// 1;
                itemDef.maleEquip1 = 7122;
                itemDef.femaleEquip1 = 7122;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[4];
                itemDef.oldColors = new int[4];
                itemDef.oldColors[0] = 6674; // NORM
                itemDef.newColors[0] = 30; // CHANGE --Yellow Trim = 11200
                itemDef.oldColors[1] = 6430; // NORM
                itemDef.newColors[1] = 933; // CHANGE
                itemDef.oldColors[2] = 6554; // NORM
                itemDef.newColors[2] = 933; // CHANGE
                itemDef.oldColors[3] = 6550; // NORM
                itemDef.newColors[3] = 933; // CHANGE
                break;

            case 14014:
                itemDef.modelID = 62710;
                itemDef.name = "Virtus mask";
                itemDef.modelZoom = 928;
                itemDef.rotationY = 406;
                itemDef.rotationX = 2041;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 62736;
                itemDef.femaleEquip1 = 62755;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                itemDef.maleDialogue = 62728;
                itemDef.femaleDialogue = 62728;
                break;

            case 14015:
                itemDef.modelID = 62704;
                itemDef.name = "Virtus robe top";
                itemDef.modelZoom = 1122;
                itemDef.rotationY = 488;
                itemDef.rotationX = 3;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 62748;
                itemDef.femaleEquip1 = 62764;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                break;

            case 14016:
                itemDef.modelID = 62700;
                itemDef.name = "Virtus robe legs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 498;
                itemDef.rotationX = 2045;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 4;
                itemDef.maleEquip1 = 62742;
                itemDef.femaleEquip1 = 62758;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                break;

            case 6082:
                itemDef.name = "RPG";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Destroy";
                itemDef.rdc2 = 2252;
                break;

            case 15901:
                itemDef.name = "Saradomin hood (b)";
                break;

            case 14140:
            case 14141:
                itemDef.name = "Sacred clay axe";
                break;
            case 14130:
            case 14131:
                itemDef.name = "Sacred clay pickaxe";
                break;

            case 16753:
                itemDef.name = "Saradomin hood";
                
                break;

            case 16754:
                itemDef.name = "Saradomin hood";
                

                break;

            case 15846:
                itemDef.name = "Saradomin robe top (b)";
                
                break;

            case 17235:
                itemDef.name = "Saradomin robe top";
                
                break;

            case 17236:
                itemDef.name = "Saradomin robe top";
                
                break;

            case 15806:
                itemDef.name = "Saradomin robe bottom (b)";
                
                break;

            case 16863:
                itemDef.name = "Saradomin robe bottom";
                
                break;

            case 16864:
                itemDef.name = "Saradomin robe bottom";
                
                break;
            case 11846:
            case 11848:
            case 11850:
            case 11852:
            case 11854:
            case 11856:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.actions[2] = "Open-All";
                
                break;
            case 9666:
            case 11814:
            case 11816:
            case 11818:
            case 11820:
            case 11822:
            case 11824:
            case 11826:
            case 11828:
            case 11830:
            case 11832:
            case 11834:
            case 11836:
            case 11838:
            case 11840:
            case 11842:
            case 11844:
            case 11858:
            case 11860:
            case 19580:
            case 11862:
            case 11864:
            case 11866:
            case 11868:
            case 11870:
            case 11874:
            case 11876:
            case 11878:
            case 11882:
            case 11886:
            case 11890:
            case 11894:
            case 11898:
            case 11902:
            case 11904:
            case 11906:
            case 11926:
            case 11928:
            case 11930:
            case 11938:
            case 11942:
            case 11944:
            case 11946:
            case 14525:
            case 14527:
            case 14529:
            case 14531:
            case 19588:
            case 19592:
            case 19596:
            case 11908:
            case 11910:
            case 11912:
            case 11914:
            case 11916:
            case 11618:
            case 11920:
            case 11922:
            case 11924:
            case 11960:
            case 11962:
            case 11967:
            case 19586:
            case 19584:
            case 19590:
            case 19594:
            case 19598:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 7587:
                itemDef.name = "Coffin of the Damned";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                
                break;
            case 7980:
                itemDef.name = "KBD heads";
                itemDef.description = "I should get these stuffed!".getBytes();
                itemDef.actions = new String[5];
                break;
            case 8141:
                itemDef.name = "Baby Dagannoth Prime";
                itemDef.description = "A miniature Dagannoth Prime.".getBytes();
                break;
            case 8142:
                itemDef.name = "Baby Dagannoth Rex";
                itemDef.description = "A miniature Dagannoth Rex.".getBytes();

                ItemDefinition def2 = ItemDefinition.get(8141);
                itemDef.modelID = def2.modelID;
                itemDef.modelZoom = def2.modelZoom;
                itemDef.rotationY = def2.rotationY;
                itemDef.rotationX = def2.rotationX;
                itemDef.modelOffsetX = def2.modelOffsetX;
                itemDef.modelOffsetY = def2.modelOffsetY;
                break;
            case 8143:
                itemDef.name = "Baby Dagannoth Supreme";
                itemDef.description = "A miniature Dagannoth Supreme.".getBytes();

                ItemDefinition def3 = ItemDefinition.get(8141);
                itemDef.modelID = def3.modelID;
                itemDef.modelZoom = def3.modelZoom;
                itemDef.rotationY = def3.rotationY;
                itemDef.rotationX = def3.rotationX;
                itemDef.modelOffsetX = def3.modelOffsetX;
                itemDef.modelOffsetY = def3.modelOffsetY;
                break;
            case 8144:
                // 11216
                itemDef.name = "Baby Chaos Elemental";
                itemDef.description = "A miniature Chaos Elemental.".getBytes();

                ItemDefinition def4 = ItemDefinition.get(8141);
                itemDef.modelID = 11216;
                itemDef.modelZoom = def4.modelZoom;
                itemDef.rotationY = def4.rotationY;
                itemDef.rotationX = def4.rotationX;
                itemDef.modelOffsetX = def4.modelOffsetX;
                itemDef.modelOffsetY = def4.modelOffsetY;
                break;
            // 28057
            case 8145:
                // 11216
                itemDef.name = "Baby Commander Zilyana";
                itemDef.description = "A miniature Commander Zilyana.".getBytes();

                ItemDefinition def5 = ItemDefinition.get(8141);
                itemDef.modelID = 28078;
                itemDef.modelZoom = 2000;
                // System.out.println("" + def5.modelZoom);
                itemDef.rotationY = def5.rotationY;
                itemDef.rotationX = def5.rotationX;
                itemDef.modelOffsetX = 7;
                itemDef.modelOffsetY = 7;
                break;
            case 8146:
                // 11216
                itemDef.name = "Baby Penance Queen";
                itemDef.description = "A miniature Penance Queen.".getBytes();

                ItemDefinition def6 = ItemDefinition.get(8141);
                itemDef.modelID = 20715;
                itemDef.modelZoom = def6.modelZoom;
                itemDef.rotationY = def6.rotationY;
                itemDef.rotationX = def6.rotationX;
                itemDef.modelOffsetX = def6.modelOffsetX;
                itemDef.modelOffsetY = def6.modelOffsetY;
                break;
            case 8147:
                // 11216
                itemDef.name = "Baby General Graardor";
                itemDef.description = "A miniature General Graardor.".getBytes();

                ItemDefinition def7 = ItemDefinition.get(8141);
                itemDef.modelID = 27785;
                itemDef.modelZoom = def7.modelZoom;
                itemDef.rotationY = def7.rotationY;
                itemDef.rotationX = def7.rotationX;
                itemDef.modelOffsetX = def7.modelOffsetX;
                itemDef.modelOffsetY = def7.modelOffsetY;
                break;
            case 8148:
                // 11216
                itemDef.name = "Baby Kree'arra";
                itemDef.description = "A miniature Kree'arra.".getBytes();
                ItemDefinition def8 = ItemDefinition.get(8141);
                itemDef.modelID = 28004;
                itemDef.modelZoom = def8.modelZoom;
                itemDef.rotationY = def8.rotationY;
                itemDef.rotationX = def8.rotationX;
                itemDef.modelOffsetX = def8.modelOffsetX;
                itemDef.modelOffsetY = def8.modelOffsetY;
                break;
            case 8149:
                // 11216
                itemDef.name = "Baby Giant Mole";
                itemDef.description = "A miniature Giant Mole.".getBytes();

                ItemDefinition def9 = ItemDefinition.get(8141);
                itemDef.modelID = 12076;
                itemDef.modelZoom = 3500;
                itemDef.rotationY = def9.rotationY;
                itemDef.rotationX = def9.rotationX;
                itemDef.modelOffsetX = def9.modelOffsetX;
                itemDef.modelOffsetY = def9.modelOffsetY;
                break;
            case 15109:
                itemDef.name = "Jar of the swamp";
                break;
            case 1513:
                itemDef.actions = new String[5];
                
                itemDef.newColors = new int[2];
                itemDef.oldColors = new int[2];
                itemDef.oldColors[0] = 127;
                itemDef.newColors[0] = 8481;
                break;
            case 303:
                itemDef.newColors = new int[2];
                itemDef.oldColors = new int[2];
                itemDef.oldColors[0] = 127; // white plague
                itemDef.newColors[0] = 7100;
            case 305:
                itemDef.newColors = new int[2];
                itemDef.oldColors = new int[2];
                itemDef.oldColors[0] = 127; // white plague
                itemDef.newColors[0] = 7446;
                break;
            case 10284:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[2];
                itemDef.oldColors = new int[2];
                itemDef.oldColors[0] = 933;
                itemDef.newColors[0] = 6;
                itemDef.modelID = 2537;
                itemDef.modelZoom = 540;
                itemDef.rotationY = 72;
                itemDef.rotationX = 136;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.maleEquip2 = -1;
                itemDef.femaleEquip1 = 366;
                itemDef.femaleEquip2 = -1;
                itemDef.maleDialogue = 69;
                itemDef.femaleDialogue = 127;
                itemDef.stackable = false;
                itemDef.name = "Black santa hat";
                itemDef.description = "Black santa hat.".getBytes();
                break;
            case 20110:// tpig
                itemDef.name = "Pig mask";
                itemDef.modelID = 65087;
                itemDef.maleEquip1 = 65088;
                itemDef.femaleEquip1 = 65088;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                // itemDef.rdc2 = 6666;
                break;
            case 22056:
                itemDef.name = "swag chest"; // temp replace later
                itemDef.modelID = 27746;
                itemDef.maleEquip1 = 27746;
                itemDef.femaleEquip1 = 27746;
                break;
            case 22057:
                itemDef.name = "swag chest"; // temp replace later
                itemDef.modelID = 27733;
                itemDef.maleEquip1 = 27733;
                itemDef.femaleEquip1 = 27733;
                break;
            case 20427:// tetsu
                itemDef.name = "Dom!nat!on helm";
                itemDef.modelID = 65420;
                itemDef.maleEquip1 = 65421;
                itemDef.femaleEquip1 = 65421;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{67};
                break;
            case 20260:// tetsu
                itemDef.name = "Dom!nat!on body";
                itemDef.modelID = 65422;
                itemDef.maleEquip1 = 65423;
                itemDef.femaleEquip1 = 65423;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{67};
                break;
            case 20095:// tetsu
                itemDef.name = "Dom!nat!on legs";
                itemDef.modelID = 65424;
                itemDef.maleEquip1 = 65425;
                itemDef.femaleEquip1 = 65425;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.oldColors = new int[]{52};
                itemDef.newColors = new int[]{67};
                break;
            case 19828:
                itemDef.name = "Quatrix helm";
                itemDef.modelID = 65192;
                itemDef.maleEquip1 = 65193;
                itemDef.femaleEquip1 = 65193;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19823:
                itemDef.name = "Quatrix body";
                itemDef.modelID = 65194;
                itemDef.maleEquip1 = 65195;
                itemDef.femaleEquip1 = 65195;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19822:
                itemDef.name = "Quatrix legs";
                itemDef.modelID = 65196;
                itemDef.maleEquip1 = 65197;
                itemDef.femaleEquip1 = 65197;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19815:
                itemDef.name = "Quatrix gloves";
                itemDef.modelID = 65198;
                itemDef.maleEquip1 = 65199;
                itemDef.femaleEquip1 = 65199;
                itemDef21 = ItemDefinition.get(7462);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19817:
                itemDef.name = "Quatrix boots";
                itemDef.modelID = 65200;
                itemDef.maleEquip1 = 65200;
                itemDef.femaleEquip1 = 65200;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19818:
                itemDef.name = "Quatrix spear";
                itemDef.modelID = 65201;
                itemDef.maleEquip1 = 65202;
                itemDef.femaleEquip1 = 65202;
                itemDef21 = ItemDefinition.get(1237);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19813:
                itemDef.name = "Dice chain";
                itemDef.modelID = 65203;
                itemDef.maleEquip1 = 65204;
                itemDef.femaleEquip1 = 65204;
                itemDef21 = ItemDefinition.get(1);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19829:
                itemDef.name = "textured pernix cowl";
                itemDef.modelID = 65212;
                itemDef.maleEquip1 = 65213;
                itemDef.femaleEquip1 = 65213;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19827:
                itemDef.name = "textured pernix body";
                itemDef.modelID = 65214;
                itemDef.maleEquip1 = 65215;
                itemDef.femaleEquip1 = 65215;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19826:
                itemDef.name = "textured pernix legs";
                itemDef.modelID = 65216;
                itemDef.maleEquip1 = 65217;
                itemDef.femaleEquip1 = 65217;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19619:
                itemDef.name = "Archie helm";
                itemDef.modelID = 65231;
                itemDef.maleEquip1 = 65232;
                itemDef.femaleEquip1 = 65232;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.oldColors = new int[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61};
                itemDef.newColors = new int[]{64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 64; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19618:
                itemDef.name = "Archie body";
                itemDef.modelID = 65233;
                itemDef.maleEquip1 = 65234;
                itemDef.femaleEquip1 = 65234;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};

                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 64; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19474:
                itemDef.name = "Archie chaps";
                itemDef.modelID = 65235;
                itemDef.maleEquip1 = 65236;
                itemDef.femaleEquip1 = 65236;// im not lagging
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};

                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 64; //
                itemDef.stackable = false;
                break;
            case 19821:
                itemDef.name = "Shadow Hood";
                itemDef.modelID = 65218;
                itemDef.maleEquip1 = 65219;
                itemDef.femaleEquip1 = 65219;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[2]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 78;
                itemDef.oldColors[1] = 1; // the texture that it currently has
                itemDef.newColors[1] = 54;// the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19820:
                itemDef.name = "Shadow body";
                itemDef.modelID = 65220;
                itemDef.maleEquip1 = 65221;
                itemDef.femaleEquip1 = 65221;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[2]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 78;
                itemDef.oldColors[1] = 1; // the texture that it currently has
                itemDef.newColors[1] = 54;// the it to have
                itemDef.stackable = false;
                break;
            case 19816:
                itemDef.name = "Shadow legs";
                itemDef.modelID = 65222;
                itemDef.maleEquip1 = 65223;
                itemDef.femaleEquip1 = 65223;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[2]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 78;
                itemDef.oldColors[1] = 1; // the texture that it currently has
                itemDef.newColors[1] = 54;// the u want it to have
                itemDef.stackable = false;
                break;
            case 19812:
                itemDef.name = "Lava Speed pickaxe";
                itemDef.modelID = 65224;
                itemDef.maleEquip1 = 65225;
                itemDef.femaleEquip1 = 65225;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 40; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 12657:
                itemDef.name = "Penguin pebbles";
                itemDef.rdc2 = 995555;
                break;
            case 12845:
                itemDef.name = "Multiplier @cya@+2";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[2] = "Redeem";

                //itemDef.rdc2 = 4633;
                break;

            case 19620:
                itemDef.name = "Shadow boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 71; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19131:
                itemDef.name = "Bronze boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[3] = "Upgrade-boots";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 58; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19130:
                itemDef.name = "Iron boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[3] = "Upgrade-boots";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 60; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19129:
                itemDef.name = "Steel boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[3] = "Upgrade-boots";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 62; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19128:
                itemDef.name = "Black boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[3] = "Upgrade-boots";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 64; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19127:
                itemDef.name = "Mithril boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[3] = "Upgrade-boots";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 66; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19126:
                itemDef.name = "Adamant boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[3] = "Upgrade-boots";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 68; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19125:
                itemDef.name = "Rune boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[3] = "Upgrade-boots";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 70; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19124:
                itemDef.name = "Dragon boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[3] = "Upgrade-boots";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 72; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19123:
                itemDef.name = "Tainted boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 58; // the new texture u want it to have
                itemDef.stackable = false;
                

                break;
            case 15418:
                itemDef.name = "Ice Fury";
                

                break;
            case 6937:
                itemDef.name = "Vixie boots";
                itemDef.modelID = 65226;
                itemDef.maleEquip1 = 65226;
                itemDef.femaleEquip1 = 65226;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 63; // the new texture u want it to have
                itemDef.stackable = false;
                

                break;

            case 18889:
                itemDef.name = "@cya@Mega spirit shield";
                itemDef.modelID = 65228;
                itemDef.maleEquip1 = 65227;
                itemDef.femaleEquip1 = 65227;
                itemDef21 = ItemDefinition.get(10666);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 62; // the texture that it currently has
                itemDef.newColors[0] = 78; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19810:
                itemDef.name = "Supreme spirit shield";
                itemDef.modelID = 65228;
                itemDef.maleEquip1 = 65227;
                itemDef.femaleEquip1 = 65227;
                itemDef21 = ItemDefinition.get(10666);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 62; // the texture that it currently has
                itemDef.newColors[0] = 76; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 6293:
                itemDef.name = "Shadow spirit shield";
                itemDef.modelID = 65228;
                itemDef.maleEquip1 = 65227;
                itemDef.femaleEquip1 = 65227;
                itemDef21 = ItemDefinition.get(10666);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 62; // the texture that it currently has
                itemDef.newColors[0] = 71; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18754:
                itemDef.name = "Hellfire spirit shield";
                itemDef.modelID = 65228;
                itemDef.maleEquip1 = 65227;
                itemDef.femaleEquip1 = 65227;
                itemDef21 = ItemDefinition.get(10666);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 62; // the texture that it currently has
                itemDef.newColors[0] = 54; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18784:
                itemDef.name = "Shadow sigil";
                // itemDef.modelID = 65228;
                itemDef.maleEquip1 = 65227;
                itemDef.femaleEquip1 = 65227;
                itemDef21 = ItemDefinition.get(13748);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.stackable = false;
                break;
            case 18783:
                itemDef.name = "Hellfire sigil";
                // itemDef.modelID = 65228;
                itemDef.maleEquip1 = 65227;
                itemDef.femaleEquip1 = 65227;
                itemDef21 = ItemDefinition.get(13746);
                itemDef.modelID = itemDef21.modelID;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.stackable = false;
                break;
            case 20057:
                itemDef.name = "Moonlight spirit shield";
                itemDef.modelID = 65228;
                itemDef.maleEquip1 = 65227;
                itemDef.femaleEquip1 = 65227;
                itemDef21 = ItemDefinition.get(10666);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 62; // the texture that it currently has
                itemDef.newColors[0] = 66; // the new texture u want it to have

                itemDef.stackable = false;
                break;

            case 19753:
                itemDef.name = "Lava axe";
                itemDef.modelID = 65229;
                itemDef.maleEquip1 = 65230;
                itemDef.femaleEquip1 = 65230;
                itemDef21 = ItemDefinition.get(1265);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 40; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19952:
                itemDef.name = "Mega helm";
                itemDef.modelID = 65095;
                itemDef.maleEquip1 = 65096;
                itemDef.femaleEquip1 = 65096;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19133:
                itemDef.name = "Thunder Partyhat";
                itemDef.modelID = 65286;
                itemDef.maleEquip1 = 65287;
                itemDef.femaleEquip1 = 65287;
                itemDef21 = ItemDefinition.get(1040);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 56; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18415:
                itemDef.name = "Lava Partyhat";
                itemDef.modelID = 65286;
                itemDef.maleEquip1 = 65287;
                itemDef.femaleEquip1 = 65287;
                itemDef21 = ItemDefinition.get(1040);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 40; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18416:
                itemDef.name = "Water Partyhat";
                itemDef.modelID = 65286;
                itemDef.maleEquip1 = 65287;
                itemDef.femaleEquip1 = 65287;
                itemDef21 = ItemDefinition.get(1040);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 51; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18417:
                itemDef.name = "Firehell Partyhat";
                itemDef.modelID = 65286;
                itemDef.maleEquip1 = 65287;
                itemDef.femaleEquip1 = 65287;
                itemDef21 = ItemDefinition.get(1040);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 52; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18418:
                itemDef.name = "@mag@Velvet Partyhat";
                itemDef.modelID = 65286;
                itemDef.maleEquip1 = 65287;
                itemDef.femaleEquip1 = 65287;
                itemDef21 = ItemDefinition.get(1040);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 54; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18419:
                itemDef.name = "Universal Partyhat";
                itemDef.modelID = 65286;
                itemDef.maleEquip1 = 65287;
                itemDef.femaleEquip1 = 65287;
                itemDef21 = ItemDefinition.get(1040);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 66; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19134:
                itemDef.name = "Staff Santa hat";
                itemDef.modelID = 65288;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65289;
                itemDef21 = ItemDefinition.get(1050);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 74; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18410:
                itemDef.name = "Universal Santa hat";
                itemDef.modelID = 65288;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65289;
                itemDef21 = ItemDefinition.get(1050);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 66; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18411:
                itemDef.name = "Skyrocket Santa hat";
                itemDef.modelID = 65288;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65289;
                itemDef21 = ItemDefinition.get(1050);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 57; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18412:
                itemDef.name = "Hellfire Santa hat";
                itemDef.modelID = 65288;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65289;
                itemDef21 = ItemDefinition.get(1050);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 54; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18413:
                itemDef.name = "Leafy Santa hat";
                itemDef.modelID = 65288;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65289;
                itemDef21 = ItemDefinition.get(1050);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 55; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18414:
                itemDef.name = "Velvet Santa hat";
                itemDef.modelID = 65288;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65289;
                itemDef21 = ItemDefinition.get(1050);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 56; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19132:
                itemDef.name = "Staff H'ween mask";
                itemDef.modelID = 65290;
                itemDef.maleEquip1 = 65291;
                itemDef.femaleEquip1 = 65291;
                itemDef21 = ItemDefinition.get(4716);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 74; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 20104:
                itemDef.name = "<col=a3d4f6>Key 1";
                itemDef.rdc2 = 11112;
                break;
            case 20105:
                itemDef.name = "<col=a3d4f6>Key 2";
                itemDef.rdc2 = 66667;
                break;
            case 20106:
                itemDef.name = "<col=a3d4f6>Key 3";
                itemDef.rdc2 = 22223;// 22223
                break;
            case 20107:
                itemDef.name = "<col=a3d4f6>Key 4";
                itemDef.rdc2 = 44445;
                break;
            case 20108:
                itemDef.name = "<col=a3d4f6>Key 5";
                itemDef.rdc2 = 55556;
                break;
            case 20109:
                itemDef.name = "<col=a3d4f6>Key 6";
                itemDef.rdc2 = 33334;// 33334
                break;
            case 20103:
                itemDef.name = "<col=a3d4f6>Boss Key";
                itemDef.rdc2 = 6662;// 33334
                break;

            case 11795:
                itemDef.name = "@whi@<shad=355>Chest Key<shad=-1>";
                itemDef.rdc2 = 1261;
                break;
            case 18405:
                itemDef.name = "Universal H'ween mask";
                itemDef.modelID = 65290;
                itemDef.maleEquip1 = 65291;
                itemDef.femaleEquip1 = 65291;
                itemDef21 = ItemDefinition.get(4716);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 66; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18406:
                itemDef.name = "Skyrocket H'ween mask";
                itemDef.modelID = 65290;
                itemDef.maleEquip1 = 65291;
                itemDef.femaleEquip1 = 65291;
                itemDef21 = ItemDefinition.get(4716);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 57; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18407:
                itemDef.name = "Hellfire H'ween mask";
                itemDef.modelID = 65290;
                itemDef.maleEquip1 = 65291;
                itemDef.femaleEquip1 = 65291;
                itemDef21 = ItemDefinition.get(4716);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 54; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18408:
                itemDef.name = "Leafy H'ween mask";
                itemDef.modelID = 65290;
                itemDef.maleEquip1 = 65291;
                itemDef.femaleEquip1 = 65291;
                itemDef21 = ItemDefinition.get(4716);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 55; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 18409:
                itemDef.name = "Velvet H'ween mask";
                itemDef.modelID = 65290;
                itemDef.maleEquip1 = 65291;
                itemDef.femaleEquip1 = 65291;
                itemDef21 = ItemDefinition.get(4716);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 56; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 19953:
                itemDef.name = "<col=a69eb6>Avatar titan platebody";
                itemDef.modelID = 65097;
                itemDef.maleEquip1 = 65098;
                itemDef.femaleEquip1 = 65098;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};

                //	itemDef.modifiedModelColors[0] = 52; // the texture that it currently has
                //	itemDef.originalModelColors[0] = 71; // the new text
                itemDef.stackable = false;

                break;
            case 19951:
                itemDef.name = "Mega legs";
                itemDef.modelID = 65099;
                itemDef.maleEquip1 = 65100;
                itemDef.femaleEquip1 = 65100;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19468:
                itemDef.name = "Darthvader mask";
                itemDef.modelID = 65249;
                itemDef.maleEquip1 = 65250;
                itemDef.femaleEquip1 = 65250;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19166:
                itemDef.name = "Darthvader body";
                itemDef.modelID = 65251;
                itemDef.maleEquip1 = 65252;
                itemDef.femaleEquip1 = 65252;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

                break;
            case 19165:
                itemDef.name = "Darthvader legs";
                itemDef.modelID = 65253;
                itemDef.maleEquip1 = 65253;
                itemDef.femaleEquip1 = 65253;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19156:
                itemDef.name = "Keencher helm";
                itemDef.modelID = 65266;
                itemDef.maleEquip1 = 65267;
                itemDef.femaleEquip1 = 65267;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 2952592;
                break;
            case 19155:
                itemDef.name = "Keencher body";
                itemDef.modelID = 65268;
                itemDef.maleEquip1 = 65269;
                itemDef.femaleEquip1 = 65269;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 3253;

                break;
            case 19154:
                itemDef.name = "Keencher legs";
                itemDef.modelID = 65270;
                itemDef.maleEquip1 = 65271;
                itemDef.femaleEquip1 = 65271;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 35622;

                break;
            case 19153:
                itemDef.name = "Joyxicular helm";
                itemDef.modelID = 65272;
                itemDef.maleEquip1 = 65273;
                itemDef.femaleEquip1 = 65273;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                itemDef.stackable = false;
                // itemDef.rdc2 = 23945;
                break;
            case 19142:
                itemDef.name = "Joyxicular body";
                itemDef.modelID = 65274;
                itemDef.maleEquip1 = 65275;
                itemDef.femaleEquip1 = 65275;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                itemDef.stackable = false;
                // itemDef.rdc2 = 23945;
                break;
            case 19141:
                itemDef.name = "Joyxicular legs";
                itemDef.modelID = 65276;
                itemDef.maleEquip1 = 65277;
                itemDef.femaleEquip1 = 65277;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                itemDef.stackable = false;
                // itemDef.rdc2 = 23945;
                break;
            case 19160:
                itemDef.name = "Galvek helm";
                itemDef.modelID = 65260;
                itemDef.maleEquip1 = 65261;
                itemDef.femaleEquip1 = 65261;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 19159:
                itemDef.name = "Galvek body";
                itemDef.modelID = 65262;
                itemDef.maleEquip1 = 65263;
                itemDef.femaleEquip1 = 65263;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 19158:
                itemDef.name = "Galvek legs";
                itemDef.modelID = 65264;
                itemDef.maleEquip1 = 65265;
                itemDef.femaleEquip1 = 65265;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 19140:
                itemDef.name = "Magebeast helm";
                itemDef.modelID = 65279;
                itemDef.maleEquip1 = 65278;
                itemDef.femaleEquip1 = 65278;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;

                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                break;
            case 19139:
                itemDef.name = "Magebeast body";
                itemDef.modelID = 65281;
                itemDef.maleEquip1 = 65280;
                itemDef.femaleEquip1 = 65280;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                break;
            case 19138:
                itemDef.name = "Magebeast legs";
                itemDef.modelID = 65283;
                itemDef.maleEquip1 = 65282;
                itemDef.femaleEquip1 = 65282;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                
                itemDef.actions[4] = "Destroy";
                itemDef.stackable = false;
                break;
            case 19164:
                itemDef.name = "Ellumemage hood";
                itemDef.modelID = 65254;
                itemDef.maleEquip1 = 65255;
                itemDef.femaleEquip1 = 65255;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

                break;
            case 19163:
                itemDef.name = "Ellumemage body";
                itemDef.modelID = 65256;
                itemDef.maleEquip1 = 65257;
                itemDef.femaleEquip1 = 65257;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                //	itemDef.rdc2 = 6642;
                break;
            case 19161:
                itemDef.name = "Ellumemage legs";
                itemDef.modelID = 65258;
                itemDef.maleEquip1 = 65259;
                itemDef.femaleEquip1 = 65259;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;

                break;
            case 19471:
                itemDef.name = "Inferior helm";
                itemDef.modelID = 65243;
                itemDef.maleEquip1 = 65244;
                itemDef.femaleEquip1 = 65244;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;
            case 19470:
                itemDef.name = "Inferior body";
                itemDef.modelID = 65245;
                itemDef.maleEquip1 = 65246;
                itemDef.femaleEquip1 = 65246;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 19469:
                itemDef.name = "Inferior legs";
                itemDef.modelID = 65247;
                itemDef.maleEquip1 = 65248;
                itemDef.femaleEquip1 = 65248;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;
            case 19811:
                itemDef.name = "Dragonslayer helm";
                itemDef.modelID = 65237;
                itemDef.maleEquip1 = 65238;
                itemDef.femaleEquip1 = 65238;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 19473:
                itemDef.name = "Dragonslayer body";
                itemDef.modelID = 65239;
                itemDef.maleEquip1 = 65240;
                itemDef.femaleEquip1 = 65240;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                


                break;
            case 19472:
                itemDef.name = "Dragonslayer legs";
                itemDef.modelID = 65241;
                itemDef.maleEquip1 = 65242;
                itemDef.femaleEquip1 = 65242;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                

                break;
            case 19946:
                itemDef.name = "<col=a69eb6>Avatar titan gloves";
                itemDef.modelID = 65101;
                itemDef.maleEquip1 = 65102;
                itemDef.femaleEquip1 = 65102;
                itemDef21 = ItemDefinition.get(7462);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 71; // the new text
                
                break;
            case 19945:
                itemDef.name = "<col=a69eb6>Avatar titan boots";
                itemDef.modelID = 65103;
                itemDef.maleEquip1 = 65103;
                itemDef.femaleEquip1 = 65103;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 71; // the new text
                
                break;
            case 19944:
                itemDef.name = "<col=a69eb6>Avatar titan cape";
                itemDef.modelID = 65104;
                itemDef.maleEquip1 = 65105;
                itemDef.femaleEquip1 = 65105;
                itemDef21 = ItemDefinition.get(1007);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 71; // the new text
                
                break;
            case 19921:
                itemDef.name = "wrath helm";
                itemDef.modelID = 65118;
                itemDef.maleEquip1 = 65119;
                itemDef.femaleEquip1 = 65119;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19915:
                itemDef.name = "wrath body";
                itemDef.modelID = 65120;
                itemDef.maleEquip1 = 65121;
                itemDef.femaleEquip1 = 65121;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19920:
                itemDef.name = "wrath legs";
                itemDef.modelID = 65122;
                itemDef.maleEquip1 = 65123;
                itemDef.femaleEquip1 = 65123;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 15914:
                itemDef.name = "KanX full helm";
                itemDef.rdc2 = 7248247;
                break;
            case 16262:
                itemDef.name = "KanX boots";
                itemDef.rdc2 = 7248247;
                break;
            case 16207:
                itemDef.name = "KanX battleaxe";
                itemDef.rdc2 = 7248247;
                break;
            case 15936:
                itemDef.name = "KanX gauntles";
                itemDef.rdc2 = 7248247;
                break;
            case 16024:
                itemDef.name = "KanX longsword";
                itemDef.rdc2 = 7248247;
                break;
            case 15925:
                itemDef.name = "KanX platelegs";
                itemDef.rdc2 = 7248247;
                break;
            case 16142:
                itemDef.name = "KanX pickaxe";
                itemDef.rdc2 = 7248247;
                break;
            case 16080:
                itemDef.name = "KanX chainbody";
                itemDef.rdc2 = 7248247;
                break;
            case 16013:
                itemDef.name = "KanX platebody";
                itemDef.rdc2 = 7248247;
                break;
            case 16174:
                itemDef.name = "KanX maul";
                itemDef.rdc2 = 7248247;
                break;
            case 16196:
                itemDef.name = "KanX plateskirt";
                itemDef.rdc2 = 7248247;
                break;

            case 19930://
                itemDef.name = "Halloween helm";
                itemDef.modelID = 65166;
                itemDef.maleEquip1 = 65167;
                itemDef.femaleEquip1 = 65167;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19929:
                itemDef.name = "Halloween body";
                itemDef.modelID = 65168;
                itemDef.maleEquip1 = 65169;
                itemDef.femaleEquip1 = 65169;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19928:
                itemDef.name = "Halloween legs";
                itemDef.modelID = 65170;
                itemDef.maleEquip1 = 65171;
                itemDef.femaleEquip1 = 65171;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19943:
                itemDef.name = "Maxiblood helm";
                itemDef.modelID = 65130;
                itemDef.maleEquip1 = 65131;
                itemDef.femaleEquip1 = 65131;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                break;
            case 19941:
                itemDef.name = "Maxiblood platebody";
                itemDef.modelID = 65132;
                itemDef.maleEquip1 = 65133;
                itemDef.femaleEquip1 = 65133;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                break;
            case 19940:
                itemDef.name = "Maxiblood legs";
                itemDef.modelID = 65134;
                itemDef.maleEquip1 = 65135;
                itemDef.femaleEquip1 = 65135;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                break;
            case 18840:
                itemDef.name = "Moonlight hood";
                itemDef.modelID = 65296;
                itemDef.maleEquip1 = 65297;
                itemDef.femaleEquip1 = 65297;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 66;
                itemDef.stackable = false;
                break;
            case 18837:
                itemDef.name = "Moonlight robetop";
                itemDef.modelID = 65298;
                itemDef.maleEquip1 = 65299;
                itemDef.femaleEquip1 = 65299;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 66;
                itemDef.stackable = false;
                break;
            case 18836:
                itemDef.name = "Moonlight robebottom";
                itemDef.modelID = 65300;
                itemDef.maleEquip1 = 65301;
                itemDef.femaleEquip1 = 65301;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 66;
                itemDef.stackable = false;
                break;
            case 3904:
                itemDef.name = "Starter Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{71};
                itemDef.modelID = 65284;
                break;
            case 18830:
                itemDef.name = "Elite dragonbone";
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{78};
                itemDef.modelID = 65285;
                break;
            case 3906:
                itemDef.name = "Maxiblood Package";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{61};
                itemDef.modelID = 65284;
                break;
            case 3908:
                itemDef.name = "Moonlight Package";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{60};
                itemDef.modelID = 65284;
                break;
            case 3910:
                itemDef.name = "Archie Package";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef21 = ItemDefinition.get(6199);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                // itemDef.modelID = itemDef2.modelID;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.oldColors = new int[]{63};
                itemDef.newColors = new int[]{59};
                itemDef.modelID = 65284;
                break;
            case 19939:
                itemDef.name = "Maxiblood gloves";
                itemDef.modelID = 65136;
                itemDef.maleEquip1 = 65137;
                itemDef.femaleEquip1 = 65137;
                itemDef21 = ItemDefinition.get(7462);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;
            case 18766:
                itemDef.name = "Archie gloves";
                itemDef.modelID = 65136;
                itemDef.maleEquip1 = 65137;
                itemDef.femaleEquip1 = 65137;
                itemDef21 = ItemDefinition.get(7462);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 64;
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;
            case 18802:
                itemDef.name = "Moonlight gloves";
                itemDef.modelID = 65136;
                itemDef.maleEquip1 = 65137;
                itemDef.femaleEquip1 = 65137;
                itemDef21 = ItemDefinition.get(7462);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 66;
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;
            case 19938:
                itemDef.name = "Maxiblood boots";
                itemDef.modelID = 65138;
                itemDef.maleEquip1 = 65138;
                itemDef.femaleEquip1 = 65138;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;
            case 18755:
                itemDef.name = "Archie boots";
                itemDef.modelID = 65138;
                itemDef.maleEquip1 = 65138;
                itemDef.femaleEquip1 = 65138;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 64;
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;
            case 18785:
                itemDef.name = "Moonlight boots";
                itemDef.modelID = 65138;
                itemDef.maleEquip1 = 65138;
                itemDef.femaleEquip1 = 65138;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 66;
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;
            case 2178:
                itemDef.name = "Maxiblood Cape";
                itemDef21 = ItemDefinition.get(6570);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = 65104;
                itemDef.maleEquip1 = 65105;
                itemDef.femaleEquip1 = 65105;
                itemDef.actions = itemDef21.actions;
                itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[2]; // same here
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.oldColors[1] = 920; // the texture that it currently has
                itemDef.newColors[1] = 50; // the new texture u want it to have
                itemDef.stackable = false;
                itemDef.value = 1;
                break;
            case 4355:
                itemDef.name = "Archie Cape";
                itemDef21 = ItemDefinition.get(6570);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = 65104;
                itemDef.maleEquip1 = 65105;
                itemDef.femaleEquip1 = 65105;
                itemDef.actions = itemDef21.actions;
                itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[2]; // same here
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 64;
                itemDef.oldColors[1] = 920; // the texture that it currently has
                itemDef.newColors[1] = 39; // the new texture u want it to have
                itemDef.stackable = false;
                itemDef.value = 1;
                break;
            case 4357:
                itemDef.name = "Moonlight Cape";
                itemDef21 = ItemDefinition.get(6570);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = 65104;
                itemDef.maleEquip1 = 65105;
                itemDef.femaleEquip1 = 65105;
                itemDef.actions = itemDef21.actions;
                itemDef.newColors = new int[2]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[2]; // same here
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 66;
                itemDef.oldColors[1] = 920; // the texture that it currently has
                itemDef.newColors[1] = 50; // the new texture u want it to have
                itemDef.stackable = false;
                itemDef.value = 1;
                break;

            case 15401:
                itemDef.name = "Lava Ring";
                itemDef.modelID = 65303;
                itemDef.maleEquip1 = 65303;
                itemDef.femaleEquip1 = 65303;
                itemDef.newColors = new int[1]; // same here
                itemDef.oldColors = new int[1];
                itemDef.oldColors[0] = 51; // the texture that it currently has
                itemDef.newColors[0] = 40;
                

                break;

            case 5497:
                itemDef.name = "Lava Sled";
                itemDef21 = ItemDefinition.get(4083);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = 65302;
                itemDef.femaleEquip1 = 65302;
                itemDef.maleEquip1 = 65302;

                itemDef.actions = new String[]{null, "Ride", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 40;
                itemDef.stackable = false;
                itemDef.value = 1;
                break;
            case 18437:
                itemDef.name = "Universal Sled";
                itemDef21 = ItemDefinition.get(4083);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = 65302;
                itemDef.femaleEquip1 = 65302;
                itemDef.maleEquip1 = 65302;

                itemDef.actions = new String[]{null, "Ride", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 60; // the texture that it currently has
                itemDef.newColors[0] = 61;
                itemDef.stackable = false;
                itemDef.value = 1;
                break;

            /*
             * case 19936: itemDef.name = "Shadow cape"; itemDef.modelID = 65139;
             * itemDef.maleWearId = 65140;// its right itemDef.femaleWearId = 65140;
             * itemDef21 = ItemDefinition.get(1007); itemDef.modelOffset1 =
             * itemDef21.modelOffset1; itemDef.modelOffsetX = itemDef21.modelOffsetX;
             * itemDef.modelOffsetY = itemDef21.modelOffsetY; itemDef.modelZoom =
             * itemDef21.modelZoom; itemDef.modelRotationY = itemDef21.modelRotationY;
             * itemDef.modelRotationX = itemDef21.modelRotationX; itemDef.actions = new
             * String[] { null, "Wear", null, null, "Destroy" }; itemDef.originalModelColors =
             * new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2
             * etc itemDef.modifiedModelColors = new int[1]; // same here
             * itemDef.modifiedModelColors[0] = 40; // the texture that it currently has
             * itemDef.originalModelColors[0] = 71; itemDef.stackable = false; itemDef.value
             * = 11; // itemDef.rdc2 = 8822; break;
             */
            case 18838:
                itemDef.name = "Dollar Chain";
                itemDef.modelID = 65292;
                itemDef.maleEquip1 = 65293;// its right
                itemDef.femaleEquip1 = 65293;
                itemDef21 = ItemDefinition.get(14599);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 58; // the texture that it currently has
                itemDef.newColors[0] = 86;
                itemDef.stackable = false;
                itemDef.value = 1;
                // itemDef.rdc2 = 8822;
                break;
            case 19919:
                itemDef.name = "Arc rapier";
                itemDef.modelID = 65124;
                itemDef.maleEquip1 = 65125;
                itemDef.femaleEquip1 = 65125;
                itemDef21 = ItemDefinition.get(1323);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19917:
                itemDef.name = "Arc mace";
                itemDef.modelID = 65126;
                itemDef.maleEquip1 = 65129;
                itemDef.femaleEquip1 = 65129;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19914:
                itemDef.name = "Maxiblood Defender";
                itemDef.modelID = 65127;
                itemDef.maleEquip1 = 65128;
                itemDef.femaleEquip1 = 65128;
                itemDef21 = ItemDefinition.get(1321);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65;
                itemDef.stackable = false;
                break;
            case 4178:
                itemDef.name = "Maxiblood whip";
                itemDef.modelID = 65141;
                itemDef.maleEquip1 = 65142;
                itemDef.femaleEquip1 = 65142;
                itemDef21 = ItemDefinition.get(13131);
                itemDef.modelOffsetX = 10;
                //itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.modelOffsetY = 1;
                itemDef.modelZoom = 1000;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = 120;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 65; // the new texture u want it to have
                itemDef.stackable = false;
                
                break;
            case 20438:
                itemDef.name = "Eternal shield";
                

                break;
            case 20534:
                itemDef.name = "Eternal whip";
                itemDef.modelID = 65141;
                itemDef.maleEquip1 = 65142;
                itemDef.femaleEquip1 = 65142;
                itemDef21 = ItemDefinition.get(13147);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 57; // the new texture u want it to have
                itemDef.stackable = false;
                break;
            case 20535:
                itemDef.name = "Brutal whip";
                itemDef.modelID = 65141;
                itemDef.maleEquip1 = 65142;
                itemDef.femaleEquip1 = 65142;
                itemDef21 = ItemDefinition.get(21371);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 40; // the texture that it currently has
                itemDef.newColors[0] = 60; // the new texture u want it to have
                itemDef.stackable = false;
                break;

            case 3323:
                itemDef.name = "Bonecrusher (i)";

                itemDef21 = ItemDefinition.get(18337);
                itemDef.modelID = itemDef21.modelID;

                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.rdc2 = 6541;
                itemDef.stackable = false;
                break;
            case 3322:
                itemDef.name = "Fighter Torso (i)";

                itemDef21 = ItemDefinition.get(10551);
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.rdc2 = 98241;
                itemDef.stackable = false;
                
                break;
            case 3321:
                itemDef.name = "Fighter hat (i)";
                itemDef21 = ItemDefinition.get(10548);
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.rdc2 = 98241;
                itemDef.stackable = false;
                

                break;
            //			
            case 3318:
                itemDef.name = "Barrow gloves (i)";

                itemDef21 = ItemDefinition.get(7462);
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.rdc2 = 98241;
                
                itemDef.stackable = false;
                break;
            case 7462:
                itemDef.name = "Barrow gloves";
                itemDef21 = ItemDefinition.get(7462);
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.stackable = false;
                break;
            case 3320:
                itemDef.name = "Runner hat (i)";

                itemDef21 = ItemDefinition.get(10549);
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.rdc2 = 98241;
                itemDef.stackable = false;
                

                break;
            case 3319:
                itemDef.name = "Ranger hat (i)";

                itemDef21 = ItemDefinition.get(10550);
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.rdc2 = 98241;
                itemDef.stackable = false;
                

                break;
            case 3324:
                itemDef.name = "Ring of wealth (ii)";

                itemDef21 = ItemDefinition.get(2572);
                itemDef.modelID = itemDef21.modelID;
                

                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = itemDef21.actions;
                itemDef.rdc2 = 6541;
                itemDef.stackable = false;
                break;
            /*
             * case 20536: itemDef.name = "Steel whip"; itemDef.modelID = 65141;
             * itemDef.maleWearId = 65142; itemDef.femaleWearId = 65142; itemDef21 =
             * ItemDefinition.get(4755); itemDef.modelOffset1 = itemDef21.modelOffset1;
             * itemDef.modelOffsetX = itemDef21.modelOffsetX; itemDef.modelOffsetY =
             * itemDef21.modelOffsetY; itemDef.modelZoom = itemDef21.modelZoom;
             * itemDef.modelRotationY = itemDef21.modelRotationY; itemDef.modelRotationX =
             * itemDef21.modelRotationX; itemDef.actions = new String[5]; itemDef.actions[4]
             * = "Destroy"; itemDef.actions[3] = "Upgrade-whip"; itemDef.actions[1] = "Wear";
             * itemDef.originalModelColors = new int[1]; // if only 1 texture is modified
             * this has to be 1, if 2 then 2 etc itemDef.modifiedModelColors = new int[1];
             * // same here itemDef.modifiedModelColors[0] = 40; // the texture that it
             * currently has itemDef.originalModelColors[0] = 64; // the new texture u want
             * it to have itemDef.stackable = false; break;
             */
            case 7640:
                itemDef.name = "Moonlight staff";
                itemDef.modelID = 65294;
                itemDef.maleEquip1 = 65295;
                itemDef.femaleEquip1 = 65295;
                itemDef21 = ItemDefinition.get(4755);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 62; // the texture that it currently has
                itemDef.newColors[0] = 66; // the new texture u want it to have
                itemDef.stackable = false;
                
                break;
            case 6936:
                itemDef.name = "Brutal staff";
                itemDef.modelID = 65294;
                itemDef.maleEquip1 = 65295;
                itemDef.femaleEquip1 = 65295;
                itemDef21 = ItemDefinition.get(4755);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 62; // the texture that it currently has
                itemDef.newColors[0] = 66; // the new texture u want it to have
                itemDef.stackable = false;
                

                break;
            case 5095:
                itemDef.name = "Skoll staff";
                itemDef.modelID = 65294;
                itemDef.maleEquip1 = 65295;
                itemDef.femaleEquip1 = 65295;
                itemDef21 = ItemDefinition.get(4755);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 62; // the texture that it currently has
                itemDef.newColors[0] = 78; // the new texture u want it to have
                itemDef.stackable = false;
                

                break;
            case 17017:
                itemDef.name = "Blaster staff";
                itemDef.rdc2 = 666;
                
                break;
            case 21371:
                itemDef.name = "Vine Whip";
                
                break;
            case 21372:
                
                break;
            case 21373:
                
                break;
            case 21374:
                
                break;
            case 5096:
                itemDef.name = "Evil staff";
                itemDef.modelID = 65294;
                itemDef.maleEquip1 = 65295;
                itemDef.femaleEquip1 = 65295;
                itemDef21 = ItemDefinition.get(4755);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[1] = "Wear";
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 62; // the texture that it currently has
                itemDef.newColors[0] = 64; // the new texture u want it to have
                itemDef.stackable = false;
                

                break;
            /*
             * case 20537: itemDef.name = "Black whip"; itemDef.modelID = 65141;
             * itemDef.maleWearId = 65142; itemDef.femaleWearId = 65142; itemDef21 =
             * ItemDefinition.get(21371); itemDef.modelOffset1 = itemDef21.modelOffset1;
             * itemDef.modelOffsetX = itemDef21.modelOffsetX; itemDef.modelOffsetY =
             * itemDef21.modelOffsetY; itemDef.modelZoom = itemDef21.modelZoom;
             * itemDef.modelRotationY = itemDef21.modelRotationY; itemDef.modelRotationX =
             * itemDef21.modelRotationX; itemDef.actions = new String[5]; itemDef.actions[4]
             * = "Destroy"; itemDef.actions[3] = "Upgrade-whip"; itemDef.actions[1] = "Wear";
             * itemDef.originalModelColors = new int[1]; // if only 1 texture is modified
             * this has to be 1, if 2 then 2 etc itemDef.modifiedModelColors = new int[1];
             * // same here itemDef.modifiedModelColors[0] = 40; // the texture that it
             * currently has itemDef.originalModelColors[0] = 66; // the new texture u want
             * it to have itemDef.stackable = false; break; case 20538: itemDef.name =
             * "Mithril whip"; itemDef.modelID = 65141; itemDef.maleWearId = 65142;
             * itemDef.femaleWearId = 65142; itemDef21 = ItemDefinition.get(21371);
             * itemDef.modelOffset1 = itemDef21.modelOffset1; itemDef.modelOffsetX =
             * itemDef21.modelOffsetX; itemDef.modelOffsetY = itemDef21.modelOffsetY;
             * itemDef.modelZoom = itemDef21.modelZoom; itemDef.modelRotationY =
             * itemDef21.modelRotationY; itemDef.modelRotationX = itemDef21.modelRotationX;
             * itemDef.actions = new String[5]; itemDef.actions[4] = "Destroy";
             * itemDef.actions[3] = "Upgrade-whip"; itemDef.actions[1] = "Wear";
             * itemDef.originalModelColors = new int[1]; // if only 1 texture is modified
             * this has to be 1, if 2 then 2 etc itemDef.modifiedModelColors = new int[1];
             * // same here itemDef.modifiedModelColors[0] = 40; // the texture that it
             * currently has itemDef.originalModelColors[0] = 68; // the new texture u want
             * it to have itemDef.stackable = false; break; case 20539: itemDef.name =
             * "Adamant whip"; itemDef.modelID = 65141; itemDef.maleWearId = 65142;
             * itemDef.femaleWearId = 65142; itemDef21 = ItemDefinition.get(21371);
             * itemDef.modelOffset1 = itemDef21.modelOffset1; itemDef.modelOffsetX =
             * itemDef21.modelOffsetX; itemDef.modelOffsetY = itemDef21.modelOffsetY;
             * itemDef.modelZoom = itemDef21.modelZoom; itemDef.modelRotationY =
             * itemDef21.modelRotationY; itemDef.modelRotationX = itemDef21.modelRotationX;
             * itemDef.actions = new String[5]; itemDef.actions[4] = "Destroy";
             * itemDef.actions[3] = "Upgrade-whip"; itemDef.actions[1] = "Wear";
             * itemDef.originalModelColors = new int[1]; // if only 1 texture is modified
             * this has to be 1, if 2 then 2 etc itemDef.modifiedModelColors = new int[1];
             * // same here itemDef.modifiedModelColors[0] = 40; // the texture that it
             * currently has itemDef.originalModelColors[0] = 70; // the new texture u want
             * it to have itemDef.stackable = false; break; case 20540: itemDef.name =
             * "Rune whip"; itemDef.modelID = 65141; itemDef.maleWearId = 65142;
             * itemDef.femaleWearId = 65142; itemDef21 = ItemDefinition.get(21371);
             * itemDef.modelOffset1 = itemDef21.modelOffset1; itemDef.modelOffsetX =
             * itemDef21.modelOffsetX; itemDef.modelOffsetY = itemDef21.modelOffsetY;
             * itemDef.modelZoom = itemDef21.modelZoom; itemDef.modelRotationY =
             * itemDef21.modelRotationY; itemDef.modelRotationX = itemDef21.modelRotationX;
             * itemDef.actions = new String[5]; itemDef.actions[4] = "Destroy";
             * itemDef.actions[3] = "Upgrade-whip"; itemDef.actions[1] = "Wear";
             * itemDef.originalModelColors = new int[1]; // if only 1 texture is modified
             * this has to be 1, if 2 then 2 etc itemDef.modifiedModelColors = new int[1];
             * // same here itemDef.modifiedModelColors[0] = 40; // the texture that it
             * currently has itemDef.originalModelColors[0] = 72; // the new texture u want
             * it to have itemDef.stackable = false; break; case 20541: itemDef.name =
             * "Dragon whip"; itemDef.modelID = 65141; itemDef.maleWearId = 65142;
             * itemDef.femaleWearId = 65142; itemDef21 = ItemDefinition.get(21371);
             * itemDef.modelOffset1 = itemDef21.modelOffset1; itemDef.modelOffsetX =
             * itemDef21.modelOffsetX; itemDef.modelOffsetY = itemDef21.modelOffsetY;
             * itemDef.modelZoom = itemDef21.modelZoom; itemDef.modelRotationY =
             * itemDef21.modelRotationY; itemDef.modelRotationX = itemDef21.modelRotationX;
             * itemDef.actions = new String[5]; itemDef.actions[4] = "Destroy";
             * itemDef.actions[3] = "Upgrade-whip"; itemDef.actions[1] = "Wear";
             * itemDef.originalModelColors = new int[1]; // if only 1 texture is modified
             * this has to be 1, if 2 then 2 etc itemDef.modifiedModelColors = new int[1];
             * // same here itemDef.modifiedModelColors[0] = 40; // the texture that it
             * currently has itemDef.originalModelColors[0] = 74; // the new texture u want
             * it to have itemDef.stackable = false; break;
             */

            case 19910:
                itemDef.name = "Thantor helm";
                itemDef.modelID = 65106;
                itemDef.maleEquip1 = 65107;
                itemDef.femaleEquip1 = 65107;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                // itemDef.setTexture(matid, textureid);
                // itemDef.applyTexturing(model, id);
                // itemDef.rdc2 = 8822;
                break;
            case 19916:
                itemDef.name = "Thantor body";
                itemDef.modelID = 65108;
                itemDef.maleEquip1 = 65109;
                itemDef.femaleEquip1 = 65109;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;
            case 19911:
                itemDef.name = "Thantor legs";
                itemDef.modelID = 65110;
                itemDef.maleEquip1 = 65111;
                itemDef.femaleEquip1 = 65111;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                // itemDef.rdc2 = 8822;
                break;

            case 15877:
                itemDef.name = "Nuetron Dagger";
                itemDef.rdc2 = 24326;// 24326
                break;
            case 15921:
                itemDef.name = "Nuetron helmet";
                itemDef.rdc2 = 24326;// 24326
                break;
            case 16269:
                itemDef.name = "Nuetron boots";
                itemDef.rdc2 = 24326;// 24326
                break;
            case 15943:
                itemDef.name = "Nuetron gauntlets";
                itemDef.rdc2 = 24326;// 24326
                break;
            case 15815:
                itemDef.name = "Nuetron shield";
                itemDef.rdc2 = 24326;// 24326
                break;

            case 19924:
                itemDef.name = "Ryan's shield";
                itemDef21 = ItemDefinition.get(16933);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = 65179;
                itemDef.maleEquip1 = 65180;
                itemDef.femaleEquip1 = 65180;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19898:
                itemDef.name = "Barrel hat";
                itemDef21 = ItemDefinition.get(15921);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = 65181;
                itemDef.maleEquip1 = 65182;
                itemDef.femaleEquip1 = 65182;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19899:
                itemDef.name = "Beats headphones";
                itemDef21 = ItemDefinition.get(15921);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = 65183;
                itemDef.maleEquip1 = 65184;
                itemDef.femaleEquip1 = 65184;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;
            case 19825:
                itemDef.name = "Necromancer hood";
                itemDef21 = ItemDefinition.get(15921);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = 65185;
                itemDef.maleEquip1 = 65186;
                itemDef.femaleEquip1 = 65186;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                break;

            case 19900:
                itemDef.name = "Lumberjack hat";
                itemDef21 = ItemDefinition.get(9920);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = itemDef21.modelID;
                itemDef.maleEquip1 = itemDef21.maleEquip1;
                itemDef.femaleEquip1 = itemDef21.femaleEquip1;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.rdc2 = 8843;

                break;
            case 19931:
                itemDef.name = "Cyan black Torva helm";
                itemDef.modelID = 65159;
                itemDef.maleEquip1 = 65160;
                itemDef.femaleEquip1 = 65160;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                // itemDef.applyTexturing(model, id);
                // itemDef.rdc2 = 8822;
                break;
            case 19933:
                itemDef.name = "Cyan black Torva body";
                itemDef.modelID = 65161;
                itemDef.maleEquip1 = 65162;
                itemDef.femaleEquip1 = 65162;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;
            case 19934:
                itemDef.name = "Cyan black Torva legs";
                itemDef.modelID = 65163;
                itemDef.maleEquip1 = 65164;
                itemDef.femaleEquip1 = 65164;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;

            case 13922:// stat help
                itemDef.name = "Frost full helm";
                
                itemDef.rdc2 = 623522;
                break;
            case 13928:// stat help
                itemDef.name = "Frost war hammer";
                
                itemDef.rdc2 = 623522;
                break;
            case 13916:// stat help
                itemDef.name = "Frost platelegs";
                
                itemDef.rdc2 = 623522;
                break;
            case 13910:// stat help
                itemDef.name = "Frost platebody";
                itemDef.rdc2 = 623522;
                
                break;
            case 13952:
                itemDef.rdc2 = 638528482;
                itemDef.name = "Lime coif";
                
                break;
            case 13946:
                itemDef.rdc2 = 638528482;
                itemDef.name = "Lime leather body";
                
                break;
            case 13949:
                itemDef.rdc2 = 638528482;
                itemDef.name = "Lime leather chaps";
                
                break;
            case 16043:
                itemDef.name = "Penguin rapier";
                break;
            case 20118:
                itemDef.name = "Penguin gloves";
                break;
            case 13959:
                itemDef.rdc2 = 638528482;
                itemDef.name = "Lime javelin";
                
                break;// 236799 purp - 2356 gold / 773red
            case 13940:
                itemDef.name = "Pink hood";
                itemDef.rdc2 = 2483285;
                
                break;
            case 13943:
                itemDef.name = "Pink staff";
                itemDef.rdc2 = 2483285;
                
                break;
            case 13934:
                itemDef.name = "Pink robe top";
                itemDef.rdc2 = 2483285;
                
                break;
            case 13937:
                itemDef.name = "Pink robe bottom";
                itemDef.rdc2 = 2483285;
                
                break;
            case 13996:
                //itemDef.rdc2 = 23339;
                
                break;
            case 13931:
                itemDef.name = "Gilded spear";
                

                itemDef.rdc2 = 2356;
                break;
            case 13913:
                itemDef.name = "Gilded chainbody";
                itemDef.rdc2 = 2356;
                
                break;
            case 13925:
                itemDef.name = "Gilded longsword";
                
                itemDef.rdc2 = 2356;
                break;
            case 13919:
                itemDef.name = "Gilded plateskirt";
                
                itemDef.rdc2 = 2356;
                break;

            case 19901:
                itemDef.name = "Eternal helm";
                itemDef.modelID = 65095;
                itemDef.maleEquip1 = 65096;
                itemDef.femaleEquip1 = 65096;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.newColors = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                itemDef.oldColors = new int[1]; // same here
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 60; // the new texture u want it to have
                
                break;
            case 19902:
                itemDef.name = "Eternal body";
                itemDef.modelID = 65097;
                itemDef.maleEquip1 = 65098;
                itemDef.femaleEquip1 = 65098;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.stackAmounts = new int[10];
                itemDef.stackIDs = new int[10];
                itemDef.stackAmounts[0] = 2;
                itemDef.stackIDs[0] = 19902;
                itemDef.stackAmounts[1] = 3;
                itemDef.stackIDs[1] = 19902;
                itemDef.stackAmounts[2] = 4;
                itemDef.stackIDs[2] = 19902;
                itemDef.stackAmounts[3] = 5;
                itemDef.stackIDs[3] = 19902;
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 60; // the new texture u want it to have
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                break;
            case 19903:
                itemDef.name = "Eternal legs";
                itemDef.modelID = 65099;
                itemDef.maleEquip1 = 65100;
                itemDef.femaleEquip1 = 65100;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 60; // the new texture u want it to have
                break;
            case 19904:
                itemDef.name = "Eternal gloves";
                itemDef.modelID = 65101;
                itemDef.maleEquip1 = 65102;
                itemDef.femaleEquip1 = 65102;
                itemDef21 = ItemDefinition.get(7462);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 60; // the new texture u want it to have
                
                break;
            case 19905:
                itemDef.name = "Eternal boots";
                itemDef.modelID = 65103;
                itemDef.maleEquip1 = 65103;
                itemDef.femaleEquip1 = 65103;
                itemDef21 = ItemDefinition.get(3791);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                //	itemDef.rdc2 = 724733835;
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 60; // the new texture u want it to have
                break;
            case 19909:
                itemDef.name = "Eternal cape";
                itemDef.modelID = 65104;
                itemDef.maleEquip1 = 65105;
                itemDef.femaleEquip1 = 65105;
                itemDef21 = ItemDefinition.get(1007);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                
                //	itemDef.rdc2 = 724733835;
                itemDef.oldColors[0] = 52; // the texture that it currently has
                itemDef.newColors[0] = 60; // the new texture u want it to have
                itemDef.oldColors[1] = 920; // the texture that it currently has
                itemDef.newColors[1] = 36133; // the new texture u want it to have
                break;
            case 10138:
                itemDef.name = "Fish";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                break;
            case 17634:
                itemDef.name = "Ore";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                break;
            case 14803:
                itemDef.name = "Eternal hat";
                itemDef.modelID = 65326;
                itemDef.maleEquip1 = 65327;
                itemDef.femaleEquip1 = 65327;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                // itemDef.rdc2 = 9303;
                break;
            case 14804:
                /*
                 * itemDef.name = "Eternal robetop"; itemDef.modelID = 65328; itemDef.maleWearId
                 * = 65329; itemDef.femaleWearId = 65329; itemDef2 = ItemDefinition.get(4894);
                 * itemDef.modelOffset1 = itemDef2.modelOffset1; itemDef.modelOffsetX =
                 * itemDef2.modelOffsetX; itemDef.modelOffsetY = itemDef2.modelOffsetY;
                 * itemDef.modelZoom = itemDef2.modelZoom; itemDef.modelRotationY =
                 * itemDef2.modelRotationY; itemDef.modelRotationX = itemDef2.modelRotationX;
                 * itemDef.actions = new String[] { null, "Wear", null, null, "Destroy" };
                 * itemDef.stackable = false;
                 */
                // itemDef.rdc2 = 9303;
                break;
            case 14805:
                itemDef.name = "Eternal robebottom";
                itemDef.modelID = 65330;
                itemDef.maleEquip1 = 65331;
                itemDef.femaleEquip1 = 65331;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.stackable = false;
                // itemDef.rdc2 = 9303;
                break;

            case 19956:
                itemDef.name = "Primrose helm";
                itemDef.modelID = 65094;
                itemDef.maleEquip1 = 65090;
                itemDef.femaleEquip1 = 65090;
                itemDef21 = ItemDefinition.get(12960);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.rdc2 = 888;
                break;
            case 19955:
                itemDef.name = "Primrose body";
                itemDef.modelID = 65091;
                itemDef.maleEquip1 = 65092;
                itemDef.femaleEquip1 = 65092;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.rdc2 = 888;
                break;
            case 19954:
                itemDef.name = "Primrose legs";
                itemDef.modelID = 65093;
                itemDef.maleEquip1 = 65089;// itemDef.modelID = 65089;65094
                itemDef.femaleEquip1 = 65089;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.rdc2 = 888;
                break;
            case 20259:
                itemDef.name = "UltraPernix helm";
                itemDef.modelID = 65094;
                itemDef.maleEquip1 = 65090;
                itemDef.femaleEquip1 = 65090;
                itemDef21 = ItemDefinition.get(12960);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 20097:
                itemDef.name = "UltraPernix body";
                itemDef.modelID = 65091;
                itemDef.maleEquip1 = 65092;
                itemDef.femaleEquip1 = 65092;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 19960:
                itemDef.name = "UltraPernix legs";
                itemDef.modelID = 65093;
                itemDef.maleEquip1 = 65089;// itemDef.modelID = 65089;65094
                itemDef.femaleEquip1 = 65089;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 19959:// tetsu
                itemDef.name = "Tetsu helm";
                itemDef.modelID = 65078;
                itemDef.maleEquip1 = 65079;
                itemDef.femaleEquip1 = 65079;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                //	itemDef.rdc2 = 9999;
                itemDef.oldColors = new int[]{55};
                itemDef.newColors = new int[]{54};
                break;
            case 19958:// tetsu
                itemDef.name = "Tetsu body";
                itemDef.modelID = 65080;
                itemDef.maleEquip1 = 65081;
                itemDef.femaleEquip1 = 65081;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                //itemDef.rdc2 = 9999;
                itemDef.oldColors = new int[]{55};
                itemDef.newColors = new int[]{54};
                break;
            case 19957:// tetsu
                itemDef.name = "Tetsu legs";
                itemDef.modelID = 65082;
                itemDef.maleEquip1 = 65083;
                itemDef.femaleEquip1 = 65083;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.oldColors = new int[]{55};
                itemDef.newColors = new int[]{54};
                //	itemDef.rdc2 = 9999;
                break;
            case 19119:
                itemDef.name = "<img=6>Donator Rank Ticket";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef21 = ItemDefinition.get(10944);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = itemDef21.modelID;
                itemDef.actions = new String[]{"Inspect", null, "Claim-rank", null, "Destroy"};
                // itemDef.rdc2 = 55555;
                break;
            case 19120:
                itemDef.name = "<img=7>Super Rank Ticket";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef21 = ItemDefinition.get(10944);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = itemDef21.modelID;
                itemDef.actions = new String[]{"Inspect", null, "Claim-rank", null, "Destroy"};
                // itemDef.rdc2 = 22222;
                break;
            case 19121:
                itemDef.name = "<img=8>Extreme Rank Ticket";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef21 = ItemDefinition.get(10944);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = itemDef21.modelID;
                itemDef.actions = new String[]{"Inspect", null, "Claim-rank", null, "Destroy"};
                // itemDef.rdc2 = 11111;
                break;
            case 19122:
                itemDef.name = "<img=9>Sponsor Rank Ticket";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Destroy";
                itemDef.actions[0] = "Claim";
                itemDef21 = ItemDefinition.get(10944);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.modelID = itemDef21.modelID;
                itemDef.actions = new String[]{"Inspect", null, "Claim-rank", null, "Destroy"};
                // itemDef.rdc2 = 44444;
                break;
            case 20094:// tetsu
                itemDef.name = "Extetsu helm";
                itemDef.modelID = 65412;
                itemDef.maleEquip1 = 65413;
                itemDef.femaleEquip1 = 65413;
                itemDef21 = ItemDefinition.get(4882);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                //	itemDef.rdc2 = 6666;

                break;
            case 20082:// tetsu
                itemDef.name = "Extetsu body";
                itemDef.modelID = 65414;
                itemDef.maleEquip1 = 65415;
                itemDef.femaleEquip1 = 65415;
                itemDef21 = ItemDefinition.get(4894);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                //	itemDef.rdc2 = 6666;
                break;
            case 19961:// tetsu
                itemDef.name = "Extetsu legs";
                itemDef.modelID = 65416;
                itemDef.maleEquip1 = 65417;
                itemDef.femaleEquip1 = 65417;
                itemDef21 = ItemDefinition.get(4900);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                //	itemDef.rdc2 = 6666;
                break;
            case 10709:// wolf
                itemDef.name = "Wolf helm";
                itemDef.modelID = 65084;
                itemDef.maleEquip1 = 65084;
                itemDef.femaleEquip1 = 65084;
                itemDef21 = ItemDefinition.get(4387);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;

                break;
            case 19173:// wilf
                itemDef.name = "Wolf body";
                itemDef.modelID = 65085;
                itemDef.maleEquip1 = 65085;
                itemDef.femaleEquip1 = 65085;
                break;
            case 19175:// wolf
                itemDef.name = "Wolf legs";
                itemDef.modelID = 65086;
                itemDef.maleEquip1 = 65086;
                itemDef.femaleEquip1 = 65086;
                break;
            case 14367:// wolf
                itemDef.name = "Greenfox helm";
                itemDef.modelID = 65084;
                itemDef.maleEquip1 = 65084;
                itemDef.femaleEquip1 = 65084;
                itemDef21 = ItemDefinition.get(4387);
                itemDef.modelOffsetX = itemDef21.modelOffsetX;
                itemDef.rotationZ = itemDef21.rotationZ;
                itemDef.modelOffsetY = itemDef21.modelOffsetY;
                itemDef.modelZoom = itemDef21.modelZoom;
                itemDef.rotationY = itemDef21.rotationY;
                itemDef.rotationX = itemDef21.rotationX;
                itemDef.rdc = 822;

                break;
            case 14347:// wilf
                itemDef.name = "Greenfox body";
                itemDef.modelID = 65085;
                itemDef.maleEquip1 = 65085;
                itemDef.femaleEquip1 = 65085;
                itemDef.rdc2 = 822;
                break;
            case 14357:// wolf
                itemDef.name = "Greenfox legs";
                itemDef.modelID = 65086;
                itemDef.maleEquip1 = 65086;
                itemDef.femaleEquip1 = 65086;
                itemDef.rdc2 = 822;
                break;
            case 4413:// cape
                itemDef.name = "RedSaphron Wings";
                itemDef.modelID = 65030;
                itemDef.maleEquip1 = 65031;
                itemDef.femaleEquip1 = 65031;
                // itemDef.rdc2 = 82222;
                break;
            case 4353:// cape
                itemDef.name = "dragon rider cape";
                itemDef.modelID = 65187;
                itemDef.maleEquip1 = 65188;
                itemDef.femaleEquip1 = 65188;
                // itemDef.rdc2 = 82222;
                break;
            case 12608:// super sayian
                itemDef.name = "SS Aura";
                itemDef.modelID = 65074;
                itemDef.maleEquip1 = 65074;
                itemDef.femaleEquip1 = 65074;
                itemDef.modelZoom = 1500;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.oldColors = new int[]{50, 10478};
                itemDef.newColors = new int[]{51, 10};
                break;
            case 12630:// super sayian
                itemDef.name = "@yel@Donator Aura";
                itemDef.modelID = 65074;
                itemDef.maleEquip1 = 65074;
                itemDef.femaleEquip1 = 65074;
                itemDef.modelZoom = 1500;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                itemDef.oldColors = new int[]{50, 10478};
                itemDef.newColors = new int[]{56, 0};
                break;
            case 12610:// super sayian
                itemDef.name = "SS-BLACK";
                itemDef.modelID = 65075;
                itemDef.maleEquip1 = 65075;
                itemDef.femaleEquip1 = 65075;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 12612:// super sayian
                itemDef.name = "SS-4";
                itemDef.modelID = 65076;
                itemDef.maleEquip1 = 65076;
                itemDef.femaleEquip1 = 65076;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 12614:// super sayian
                itemDef.name = "SS-GOD";
                itemDef.modelID = 65077;
                itemDef.maleEquip1 = 65077;
                itemDef.femaleEquip1 = 65077;
                itemDef.actions = new String[]{null, "Wear", null, null, "Destroy"};
                break;
            case 4405:
                itemDef.name = "swoodoo";
                itemDef.modelID = 65032;
                itemDef.maleEquip1 = 65033;
                itemDef.femaleEquip1 = 65033;
                
                break;
            case 4371:
                itemDef.name = "Blue wings";
                itemDef.modelID = 65036;
                itemDef.maleEquip1 = 65037;
                itemDef.femaleEquip1 = 65037;
                break;
            case 4373:
                itemDef.name = "<col=000222>Elite Winged Aura"; // slayer
                itemDef.modelID = 65038;
                itemDef.maleEquip1 = 65039;
                itemDef.femaleEquip1 = 65039;
                break;
            case 4315:
                itemDef.name = "Craft Wings"; // slayer
                itemDef.modelID = 65038;
                itemDef.maleEquip1 = 65039;
                itemDef.femaleEquip1 = 65039;
                itemDef.rdc2 = 2252;
                break;
            case 4375:
                itemDef.name = "Angelic Wings"; // slayer
                itemDef.modelID = 65040;
                itemDef.maleEquip1 = 65041;
                itemDef.femaleEquip1 = 65041;
                break;
            case 4377:
                itemDef.name = "Hit cape"; //
                itemDef.modelID = 65042;
                itemDef.maleEquip1 = 65043;
                itemDef.femaleEquip1 = 65043;
                break;
            case 4379:
                itemDef.name = "Cyan Wings"; //
                itemDef.modelID = 65044;
                itemDef.maleEquip1 = 65045;
                itemDef.femaleEquip1 = 65045;
                break;
            case 4407:
                itemDef.name = "Donator wingcape"; //
                itemDef.modelID = 65046;
                itemDef.maleEquip1 = 65047;
                itemDef.femaleEquip1 = 65047;
                break;
            case 4409:
                itemDef.name = "Yellow wings"; //
                itemDef.modelID = 65050;
                itemDef.maleEquip1 = 65051;
                itemDef.femaleEquip1 = 65051;
                break;
            case 4385:
                itemDef.name = "Purple wings"; //
                itemDef.modelID = 65052;
                itemDef.maleEquip1 = 65053;
                itemDef.femaleEquip1 = 65053;
                break;
            case 4387:
                itemDef.name = "Camo wings"; //
                itemDef.modelID = 65054;
                itemDef.maleEquip1 = 65055;
                itemDef.femaleEquip1 = 65055;
                break;
            case 4319:
                itemDef.name = "cryswamp wings"; //
                itemDef.modelID = 65054;
                itemDef.maleEquip1 = 65055;
                itemDef.femaleEquip1 = 65055;
                itemDef.rdc2 = 92020;
                break;
            case 4389:
                itemDef.name = "Dragonslayer wings"; //
                itemDef.modelID = 65056;
                itemDef.maleEquip1 = 65057;
                itemDef.femaleEquip1 = 65057;
                break;
            case 4391:
                itemDef.name = "Red fairy wings"; //
                itemDef.modelID = 65058;
                itemDef.maleEquip1 = 65059;
                itemDef.femaleEquip1 = 65059;
                break;
            case 4321:
                itemDef.name = "dark fairy wings"; //
                itemDef.modelID = 65058;
                itemDef.maleEquip1 = 65059;
                itemDef.femaleEquip1 = 65059;
                itemDef.rdc2 = 1013;
                break;
            case 4393:
                itemDef.name = "Inferior wings"; //
                itemDef.modelID = 65060;
                itemDef.maleEquip1 = 65061;
                itemDef.femaleEquip1 = 65061;
                break;
            case 4395:
                itemDef.name = "Demonic wings"; //
                itemDef.modelID = 65062;
                itemDef.maleEquip1 = 65062;
                itemDef.femaleEquip1 = 65063;
                break;
            case 4403:
                itemDef.name = "Tawn wings"; //
                itemDef.modelID = 65062;
                itemDef.maleEquip1 = 65062;
                itemDef.femaleEquip1 = 65063;
                itemDef.rdc2 = 357782;
                break;
            case 4401:
                itemDef.name = "Angel wings"; //
                itemDef.modelID = 65071;
                itemDef.maleEquip1 = 65071;
                itemDef.femaleEquip1 = 65072;
                break;
            case 4411:
                itemDef.name = "Impspawn wings"; //
                itemDef.modelID = 65064;
                itemDef.maleEquip1 = 65065;
                itemDef.femaleEquip1 = 65065;
                

                break;
            case 4397:
                itemDef.name = "Green wingcape"; //
                itemDef.modelID = 65066;
                itemDef.maleEquip1 = 65067;
                itemDef.femaleEquip1 = 65067;
                break;
            case 4399:
                itemDef.name = "Big bird wings"; //
                itemDef.modelID = 65069;
                itemDef.maleEquip1 = 65068;
                itemDef.femaleEquip1 = 65068;
                break;
            case 4381:
                itemDef.name = "Chicken feathers"; //
                itemDef.modelID = 65048;
                itemDef.maleEquip1 = 65048;
                itemDef.femaleEquip1 = 65048;
                break;
            case 4383:
                itemDef.name = "Royal cape"; //
                itemDef.modelID = 65049;
                itemDef.maleEquip1 = 65049;
                itemDef.femaleEquip1 = 65049;
                break;
            case 4317:
                itemDef.name = "Neptune cape"; //
                itemDef.modelID = 65049;
                itemDef.maleEquip1 = 65049;
                itemDef.femaleEquip1 = 65049;
                itemDef.rdc2 = 32523;
                break;

            case 4369:
                itemDef.name = "Fractite wings";
                itemDef.modelID = 65034;
                itemDef.maleEquip1 = 65035;
                itemDef.femaleEquip1 = 65035;
                break;

            case 22058:
                itemDef.name = "swag chest"; // temp replace later
                itemDef.modelID = 27725;
                itemDef.maleEquip1 = 27725;
                itemDef.femaleEquip1 = 27725;
                break;
            case 22059:
                itemDef.name = "swag chest"; // temp replace later
                itemDef.modelID = 27740;
                itemDef.maleEquip1 = 27740;
                itemDef.femaleEquip1 = 27740;
                break;
            case 22054:
                itemDef.name = "Tuxedo";
                itemDef.modelID = 12752;
                itemDef.maleEquip1 = 12752;
                itemDef.femaleEquip1 = 12752;
                itemDef.maleEquip2 = 10301;
                itemDef.newColors = new int[1];
                itemDef.oldColors = new int[1];
                itemDef.oldColors[0] = 8741; // NORM
                itemDef.newColors[0] = 920; // CHANGE
                itemDef.rotationY = 200;
                itemDef.rotationX = 0;
                itemDef.modelZoom = 1180;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 120;
                break;
            case 18686:
                ItemDefinition itemTormented = ItemDefinition.get(22008);
                itemDef.modelID = itemTormented.modelID;
                itemDef.femaleEquip1 = itemTormented.femaleEquip1;
                itemDef.maleEquip1 = itemTormented.maleEquip1;
                itemDef.modelOffsetX = itemTormented.modelOffsetX;
                itemDef.rotationZ = itemTormented.rotationZ;
                itemDef.modelOffsetY = itemTormented.modelOffsetY;
                itemDef.modelZoom = itemTormented.modelZoom;
                itemDef.rotationY = itemTormented.rotationY;
                itemDef.rotationX = itemTormented.rotationX;
                itemDef.actions = itemTormented.actions;
                itemDef.name = "Tormented tentacle";
                itemDef.rdc2 = 53664333;
                
                break;
            case 18683:
                ItemDefinition itemTormented1 = ItemDefinition.get(19111);
                itemDef.modelID = itemTormented1.modelID;
                itemDef.femaleEquip1 = itemTormented1.femaleEquip1;
                itemDef.maleEquip1 = itemTormented1.maleEquip1;
                itemDef.modelOffsetX = itemTormented1.modelOffsetX;
                itemDef.rotationZ = itemTormented1.rotationZ;
                itemDef.modelOffsetY = itemTormented1.modelOffsetY;
                itemDef.modelZoom = itemTormented1.modelZoom;
                itemDef.rotationY = itemTormented1.rotationY;
                itemDef.rotationX = itemTormented1.rotationX;
                itemDef.actions = itemTormented1.actions;
                itemDef.name = "Satanic tokhaar-kal";
                itemDef.rdc2 = 883563;
                break;
            case 17273:
                
                break;
            case 18684:
                ItemDefinition itemTormented11 = ItemDefinition.get(17273);
                itemDef.modelID = itemTormented11.modelID;
                itemDef.femaleEquip1 = itemTormented11.femaleEquip1;
                itemDef.maleEquip1 = itemTormented11.maleEquip1;
                itemDef.modelOffsetX = itemTormented11.modelOffsetX;
                itemDef.rotationZ = itemTormented11.rotationZ;
                itemDef.modelOffsetY = itemTormented11.modelOffsetY;
                itemDef.modelZoom = itemTormented11.modelZoom;
                itemDef.rotationY = itemTormented11.rotationY;
                itemDef.rotationX = itemTormented11.rotationX;
                itemDef.actions = itemTormented11.actions;
                itemDef.name = "Tormented defender";
                itemDef.rdc2 = 53664333;
                
                break;
            case 11617:
                ItemDefinition itemFury = ItemDefinition.get(19335);
                itemDef.modelID = itemFury.modelID;
                itemDef.femaleEquip1 = itemFury.femaleEquip1;
                itemDef.maleEquip1 = itemFury.maleEquip1;
                itemDef.modelOffsetX = itemFury.modelOffsetX;
                itemDef.rotationZ = itemFury.rotationZ;
                itemDef.modelOffsetY = itemFury.modelOffsetY;
                itemDef.modelZoom = itemFury.modelZoom;
                itemDef.rotationY = itemFury.rotationY;
                itemDef.rotationX = itemFury.rotationX;
                itemDef.actions = itemFury.actions;
                itemDef.name = "Amulet of Fury (z)";
                itemDef.rdc2 = 32523;
                
                break;
            case 3909:
                ItemDefinition itemFury1 = ItemDefinition.get(15220);
                itemDef.modelID = itemFury1.modelID;
                itemDef.femaleEquip1 = itemFury1.femaleEquip1;
                itemDef.maleEquip1 = itemFury1.maleEquip1;
                itemDef.modelOffsetX = itemFury1.modelOffsetX;
                itemDef.rotationZ = itemFury1.rotationZ;
                itemDef.modelOffsetY = itemFury1.modelOffsetY;
                itemDef.modelZoom = itemFury1.modelZoom;
                itemDef.rotationY = itemFury1.rotationY;
                itemDef.rotationX = itemFury1.rotationX;
                itemDef.actions = itemFury1.actions;
                itemDef.name = "Berserker ring (z)";
                itemDef.rdc2 = 66235;
                

                break;
            case 6737:
                ItemDefinition itemFury2 = ItemDefinition.get(15220);
                itemDef.modelID = itemFury2.modelID;
                itemDef.femaleEquip1 = itemFury2.femaleEquip1;
                itemDef.maleEquip1 = itemFury2.maleEquip1;
                itemDef.modelOffsetX = itemFury2.modelOffsetX;
                itemDef.rotationZ = itemFury2.rotationZ;
                itemDef.modelOffsetY = itemFury2.modelOffsetY;
                itemDef.modelZoom = itemFury2.modelZoom;
                itemDef.rotationY = itemFury2.rotationY;
                itemDef.rotationX = itemFury2.rotationX;
                itemDef.actions = itemFury2.actions;
                itemDef.name = "Berserker ring";
                
                break;
            case 3905:
                ItemDefinition itemFury11 = ItemDefinition.get(13007);
                itemDef.modelID = itemFury11.modelID;
                itemDef.femaleEquip1 = itemFury11.femaleEquip1;
                itemDef.maleEquip1 = itemFury11.maleEquip1;
                itemDef.modelOffsetX = itemFury11.modelOffsetX;
                itemDef.rotationZ = itemFury11.rotationZ;
                itemDef.modelOffsetY = itemFury11.modelOffsetY;
                itemDef.modelZoom = itemFury11.modelZoom;
                itemDef.rotationY = itemFury11.rotationY;
                itemDef.rotationX = itemFury11.rotationX;
                itemDef.actions = itemFury11.actions;
                itemDef.name = "Boss Gloves";
                itemDef.rdc2 = 3262;
                

                break;
            case 3805:
                ItemDefinition itemFury111 = ItemDefinition.get(20068);
                itemDef.modelID = itemFury111.modelID;
                itemDef.femaleEquip1 = itemFury111.femaleEquip1;
                itemDef.maleEquip1 = itemFury111.maleEquip1;
                itemDef.modelOffsetX = itemFury111.modelOffsetX;
                itemDef.rotationZ = itemFury111.rotationZ;
                itemDef.modelOffsetY = itemFury111.modelOffsetY;
                itemDef.modelZoom = itemFury111.modelZoom;
                itemDef.rotationY = itemFury111.rotationY;
                itemDef.rotationX = itemFury111.rotationX;
                itemDef.actions = itemFury111.actions;
                itemDef.name = "Zara's accumulator";
                itemDef.rdc2 = 7234;
                break;
            case 3907:
                ItemDefinition itemFury1111 = ItemDefinition.get(18782);
                itemDef.modelID = itemFury1111.modelID;
                itemDef.femaleEquip1 = itemFury1111.femaleEquip1;
                itemDef.maleEquip1 = itemFury1111.maleEquip1;
                itemDef.modelOffsetX = itemFury1111.modelOffsetX;
                itemDef.rotationZ = itemFury1111.rotationZ;
                itemDef.modelOffsetY = itemFury1111.modelOffsetY;
                itemDef.modelZoom = itemFury1111.modelZoom;
                itemDef.rotationY = itemFury1111.rotationY;
                itemDef.rotationX = itemFury1111.rotationX;
                itemDef.actions = itemFury1111.actions;
                itemDef.name = "Zara's xp lamp";
                itemDef.rdc2 = 7623;
                break;
            case 18685:
                ItemDefinition itemTormented111 = ItemDefinition.get(14484);
                itemDef.modelID = itemTormented111.modelID;
                itemDef.femaleEquip1 = itemTormented111.femaleEquip1;
                itemDef.maleEquip1 = itemTormented111.maleEquip1;
                itemDef.modelOffsetX = itemTormented111.modelOffsetX;
                itemDef.rotationZ = itemTormented111.rotationZ;
                itemDef.modelOffsetY = itemTormented111.modelOffsetY;
                itemDef.modelZoom = itemTormented111.modelZoom;
                itemDef.rotationY = itemTormented111.rotationY;
                itemDef.rotationX = itemTormented111.rotationX;
                itemDef.actions = itemTormented111.actions;
                itemDef.name = "Tormented claws";
                itemDef.rdc2 = 53664333;
                
                break;
            case 22053:
                itemDef.name = "Ecumenical key";
                itemDef.modelID = 2372;
                itemDef.stackable = false;
                itemDef.actions = null;
                itemDef.groundActions = null;
                itemDef.rotationY = 338;
                itemDef.rotationX = 1701;
                itemDef.modelZoom = 775;
                itemDef.modelOffsetX = -2;
                // itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -7;
                itemDef.oldColors = new int[2];
                itemDef.newColors = new int[2];
                itemDef.oldColors[0] = 8128;
                itemDef.newColors[0] = 41189;// 43069;
                /*
                 * Dumping: Crystal key itemId: 989 modelId: 2372 maleWearId: -1 femaleWearId:
                 * -1 modelOffset1: -3 modelOffSetX: 0 modelOffSetY: 5 modelRotationY: 328
                 * modelRotationX: 20 modelZoom: 700 modifiedModelColors[0]: 8128
                 * originalModelColors[0]: 43069 Action[0]: null Action[1]: null Action[2]: null
                 * Action[3]: null Action[4]: Drop groundAction[0]: null groundAction[1]: null
                 * groundAction[2]: Take groundAction[3]: null groundAction[4]: null
                 */
                break;
            case 78:
                itemDef.name = "Soul arrow";
                break;
            case 22034:
                
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.newColors = new int[6];
                itemDef.oldColors = new int[6];
                itemDef.noteTemplate = -1;
                itemDef.note = -1;
                itemDef.stackable = false;
                /*
                 * itemDef.modifiedModelColors[0] = 115; itemDef.modifiedModelColors[1] = 107;
                 * itemDef.modifiedModelColors[2] = 10167; itemDef.modifiedModelColors[3] =
                 * 10171; itemDef.originalModelColors[0] = 5409; itemDef.originalModelColors[1]
                 * = 5404; itemDef.originalModelColors[2] = 6449; itemDef.originalModelColors[3]
                 * = 7390;
                 */
                itemDef.oldColors[0] = 5409;
                itemDef.oldColors[1] = 5404;
                itemDef.oldColors[2] = 6449;
                itemDef.oldColors[3] = 7390;
                itemDef.newColors[0] = 115;
                itemDef.newColors[1] = 107;
                itemDef.newColors[2] = 10167;
                itemDef.newColors[3] = 10171;
                itemDef.modelID = 19967;
                itemDef.modelZoom = 1325;
                itemDef.rotationY = 240;
                itemDef.rotationX = 110;
                // itemDef.modelOffsetX = 0;
                // itemDef.modelOffset1 = 0;
                // itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 19839;
                // itemDef.anInt188 = -1;
                itemDef.femaleEquip1 = 19839;
                // itemDef.anInt164 = -1;
                // itemDef.maleDialogue = 69;
                // itemDef.femaleDialogue = 127;
                itemDef.stackable = false;
                itemDef.name = "Armadyl Crossbow";
                itemDef.description = "Black santa hat.".getBytes();
                break;
            case 5020:
                itemDef.name = "<col=ddc99f>AFK ticket";
                // itemDef.rdc2 = 999122;
                itemDef.stackable = true;
                break;
            case 3686:
                itemDef.name = "<col=4689fe>100M ticket";
                itemDef.rdc2 = 999122;
                ItemDefinition itemDefticket11 = ItemDefinition.get(5020);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemDefticket11.modelID;
                itemDef.femaleEquip1 = itemDefticket11.femaleEquip1;
                itemDef.maleEquip1 = itemDefticket11.maleEquip1;
                itemDef.modelOffsetX = itemDefticket11.modelOffsetX;
                itemDef.rotationZ = itemDefticket11.rotationZ;
                itemDef.modelOffsetY = itemDefticket11.modelOffsetY;
                itemDef.modelZoom = itemDefticket11.modelZoom;
                itemDef.rotationY = itemDefticket11.rotationY;
                itemDef.rotationX = itemDefticket11.rotationX;
                // itemDef.rdc2 = ;
                itemDef.stackable = true;
                break;
            case 3687:
                itemDef.name = "<col=c762fb>1B ticket";
                ItemDefinition itemDefticket = ItemDefinition.get(5020);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemDefticket.modelID;
                itemDef.femaleEquip1 = itemDefticket.femaleEquip1;
                itemDef.maleEquip1 = itemDefticket.maleEquip1;
                itemDef.modelOffsetX = itemDefticket.modelOffsetX;
                itemDef.actions = itemDefticket.actions;
                itemDef.rotationZ = itemDefticket.rotationZ;
                itemDef.modelOffsetY = itemDefticket.modelOffsetY;
                itemDef.modelZoom = itemDefticket.modelZoom;
                itemDef.rotationY = itemDefticket.rotationY;
                itemDef.rotationX = itemDefticket.rotationX;
                itemDef.rdc2 = 2352;
                itemDef.stackable = true;
                break;
            case 18835:
                ItemDefinition itemDefticket1 = ItemDefinition.get(11694);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemDefticket1.modelID;
                itemDef.femaleEquip1 = itemDefticket1.femaleEquip1;
                itemDef.maleEquip1 = itemDefticket1.maleEquip1;
                itemDef.modelOffsetX = itemDefticket1.modelOffsetX;
                itemDef.rotationZ = itemDefticket1.rotationZ;
                itemDef.modelOffsetY = itemDefticket1.modelOffsetY;
                itemDef.modelZoom = itemDefticket1.modelZoom;
                itemDef.rotationY = itemDefticket1.rotationY;
                itemDef.rotationX = itemDefticket1.rotationX;
                itemDef.actions = itemDefticket1.actions;
                itemDef.name = "Kree'arra godsword";
                
                itemDef.rdc2 = 6235;
                break;
            case 18834:
                ItemDefinition itemGWD11 = ItemDefinition.get(11718);
                itemDef.modelID = itemGWD11.modelID;
                itemDef.femaleEquip1 = itemGWD11.femaleEquip1;
                itemDef.maleEquip1 = itemGWD11.maleEquip1;
                itemDef.modelOffsetX = itemGWD11.modelOffsetX;
                itemDef.rotationZ = itemGWD11.rotationZ;
                itemDef.modelOffsetY = itemGWD11.modelOffsetY;
                itemDef.modelZoom = itemGWD11.modelZoom;
                itemDef.rotationY = itemGWD11.rotationY;
                itemDef.rotationX = itemGWD11.rotationX;
                itemDef.actions = itemGWD11.actions;
                itemDef.name = "Kree'arra helmet";
                
                itemDef.rdc2 = 6235;
                break;
            case 18801:
                ItemDefinition itemGWD111 = ItemDefinition.get(11720);
                itemDef.modelID = itemGWD111.modelID;
                itemDef.femaleEquip1 = itemGWD111.femaleEquip1;
                itemDef.maleEquip1 = itemGWD111.maleEquip1;
                itemDef.modelOffsetX = itemGWD111.modelOffsetX;
                itemDef.rotationZ = itemGWD111.rotationZ;
                itemDef.modelOffsetY = itemGWD111.modelOffsetY;
                itemDef.modelZoom = itemGWD111.modelZoom;
                itemDef.rotationY = itemGWD111.rotationY;
                itemDef.rotationX = itemGWD111.rotationX;
                itemDef.actions = itemGWD111.actions;
                itemDef.name = "Kree'arra chestplate";
                
                itemDef.rdc2 = 6235;
                break;
            case 18800:
                ItemDefinition itemGWD1111 = ItemDefinition.get(11722);
                itemDef.modelID = itemGWD1111.modelID;
                itemDef.femaleEquip1 = itemGWD1111.femaleEquip1;
                itemDef.maleEquip1 = itemGWD1111.maleEquip1;
                itemDef.modelOffsetX = itemGWD1111.modelOffsetX;
                itemDef.rotationZ = itemGWD1111.rotationZ;
                itemDef.modelOffsetY = itemGWD1111.modelOffsetY;
                itemDef.modelZoom = itemGWD1111.modelZoom;
                itemDef.rotationY = itemGWD1111.rotationY;
                itemDef.rotationX = itemGWD1111.rotationX;
                itemDef.actions = itemGWD1111.actions;
                itemDef.name = "Kree'arra chainskirt";
                
                itemDef.rdc2 = 6235;
                break;
            case 18799:
                ItemDefinition itemGWD11111 = ItemDefinition.get(22034);
                itemDef.modelID = itemGWD11111.modelID;
                itemDef.femaleEquip1 = itemGWD11111.femaleEquip1;
                itemDef.maleEquip1 = itemGWD11111.maleEquip1;
                itemDef.modelOffsetX = itemGWD11111.modelOffsetX;
                itemDef.rotationZ = itemGWD11111.rotationZ;
                itemDef.modelOffsetY = itemGWD11111.modelOffsetY;
                itemDef.modelZoom = itemGWD11111.modelZoom;
                itemDef.rotationY = itemGWD11111.rotationY;
                itemDef.rotationX = itemGWD11111.rotationX;
                itemDef.actions = itemGWD11111.actions;
                itemDef.name = "Kree'arra crossbow";
                
                itemDef.rdc2 = 6235;
                break;
            case 18798:
                ItemDefinition itemBando = ItemDefinition.get(11696);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemBando.modelID;
                itemDef.femaleEquip1 = itemBando.femaleEquip1;
                itemDef.maleEquip1 = itemBando.maleEquip1;
                itemDef.modelOffsetX = itemBando.modelOffsetX;
                itemDef.rotationZ = itemBando.rotationZ;
                itemDef.modelOffsetY = itemBando.modelOffsetY;
                itemDef.modelZoom = itemBando.modelZoom;
                itemDef.rotationY = itemBando.rotationY;
                itemDef.rotationX = itemBando.rotationX;
                itemDef.actions = itemBando.actions;
                itemDef.name = "General godsword";
                
                itemDef.rdc2 = 4488;
                break;
            case 18797:
                ItemDefinition itemBando1 = ItemDefinition.get(11724);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemBando1.modelID;
                itemDef.femaleEquip1 = itemBando1.femaleEquip1;
                itemDef.maleEquip1 = itemBando1.maleEquip1;
                itemDef.modelOffsetX = itemBando1.modelOffsetX;
                itemDef.rotationZ = itemBando1.rotationZ;
                itemDef.modelOffsetY = itemBando1.modelOffsetY;
                itemDef.modelZoom = itemBando1.modelZoom;
                itemDef.rotationY = itemBando1.rotationY;
                itemDef.rotationX = itemBando1.rotationX;
                itemDef.actions = itemBando1.actions;
                itemDef.name = "General chestplate";
                
                itemDef.rdc2 = 445488;
                break;
            case 18795:
                ItemDefinition itemBando11 = ItemDefinition.get(11726);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemBando11.modelID;
                itemDef.femaleEquip1 = itemBando11.femaleEquip1;
                itemDef.maleEquip1 = itemBando11.maleEquip1;
                itemDef.modelOffsetX = itemBando11.modelOffsetX;
                itemDef.rotationZ = itemBando11.rotationZ;
                itemDef.modelOffsetY = itemBando11.modelOffsetY;
                itemDef.modelZoom = itemBando11.modelZoom;
                itemDef.rotationY = itemBando11.rotationY;
                itemDef.rotationX = itemBando11.rotationX;
                itemDef.actions = itemBando11.actions;
                
                itemDef.name = "General tassets";
                itemDef.rdc2 = 445488;
                break;
            case 19794:
                ItemDefinition itemBando111 = ItemDefinition.get(11728);
                // itemDef.modelID = itemDef2.modelID;
                itemDef.modelID = itemBando111.modelID;
                itemDef.femaleEquip1 = itemBando111.femaleEquip1;
                itemDef.maleEquip1 = itemBando111.maleEquip1;
                itemDef.modelOffsetX = itemBando111.modelOffsetX;
                itemDef.rotationZ = itemBando111.rotationZ;
                itemDef.modelOffsetY = itemBando111.modelOffsetY;
                itemDef.modelZoom = itemBando111.modelZoom;
                itemDef.rotationY = itemBando111.rotationY;
                itemDef.rotationX = itemBando111.rotationX;
                itemDef.actions = itemBando111.actions;
                itemDef.name = "General boots";
                
                itemDef.rdc2 = 445488;
                break;

            case 18792:
                ItemDefinition itemZara = ItemDefinition.get(11730);
                itemDef.modelID = itemZara.modelID;
                itemDef.femaleEquip1 = itemZara.femaleEquip1;
                itemDef.maleEquip1 = itemZara.maleEquip1;
                itemDef.modelOffsetX = itemZara.modelOffsetX;
                itemDef.rotationZ = itemZara.rotationZ;
                itemDef.modelOffsetY = itemZara.modelOffsetY;
                itemDef.modelZoom = itemZara.modelZoom;
                itemDef.rotationY = itemZara.rotationY;
                itemDef.rotationX = itemZara.rotationX;
                itemDef.actions = itemZara.actions;
                itemDef.name = "Zilyana sword";
                
                itemDef.rdc2 = 53633;
                break;

        }

    }
}
