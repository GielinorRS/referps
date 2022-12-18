package org.necrotic.client.cache.definition;

public class FixedDefinitions {

    public static ItemDefinition fix(ItemDefinition itemDef, int id) {
        switch (id) {
            case 23568:
            case 23578:
            case 23601:
            case 23642:
            case 23643:
            case 23785:
            case 23817:
            case 23841:
                itemDef.switchHands = true;
                if (!ItemDefinition.switchHandsModels.contains(itemDef.maleEquip1))
                    ItemDefinition.switchHandsModels.add(itemDef.maleEquip1);
                break;
        }

        switch (id) {
            case 1053:
            case 1057:
            case 1055:
            case 1038:
            case 1040:
            case 1042:
            case 1044:
            case 1046:
            case 1048:
            case 1050:
            case 14008:
            case 14009:
            case 14010:
            case 14484:
            case 19115:
            case 19114:
            case 13736:
            case 13744:
            case 13738:
            case 13742:
            case 13740:
            case 6293:
            case 18754:
            case 11694:
            case 11696:
            case 11698:
            case 11700:
            case 15018:
            case 15019:
            case 15020:
            case 15220:
            case 12601:
            case 12603:
            case 12605:
            case 20000:
            case 20001:
            case 20002:

                break;


            case 13556: //RS3 WEPS
            case 23537: //
            case 23566: //
            case 23567: //
            case 23568: //
                itemDef.maleWieldX = -1;
                itemDef.maleWieldY = 6;
                break;
        }

            switch (id) {
            case 6937: //Vixie boots
            case 19123: //Tainted boots
            case 8811: //Lunite boots
            case 18881: //Supreme boots
            case 15236: //electric boots
            case 18887: //
            case 14053: //
                itemDef.modelZoom = 869;
                itemDef.rotationX = 160;
                itemDef.rotationY = 152;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -13;
                break;
            case 19136: //Brutal minigun
            case 8253: //Defiled minigun
            case 19135: //Mithril minigun
            case 19137: //Iron minigun
            case 19843: //archie minigun
                itemDef.modelZoom = 2051;
                itemDef.rotationX = 1618;
                itemDef.rotationY = 449;
                itemDef.rotationZ = 108;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = 9;
                break;
            case 12535: //Execution Vitur
            case 12284: //Herc scythe
            case 22115: //dark scythe
            case 12537: //light scythe
                itemDef.modelZoom = 3338;
                itemDef.rotationX = 923;
                itemDef.rotationY = 336;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -28;
                itemDef.modelOffsetY = 9;
                break;
            case 18754: //Hellfire spirit shield
            case 19810: //Supreme spirit shield
            case 6293: //shadow spirit shield
            case 18889:
            case 20057:
                itemDef.modelZoom = 1560;
                itemDef.rotationX = 1104;
                itemDef.rotationY = 344;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = 24;
                break;
            case 21065: //Ruthless spirit shield
            case 21019:
            case 20090:
            case 19987:
                itemDef.modelZoom = 1560;
                itemDef.rotationX = 991;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 11;
                break;
            case 4017: //<shad=1><col=00e673>Extreme Glaive
            case 20551: //
            case 20552: //
            case 20556: //
            case 20557: //
            case 20558: //
                itemDef.modelZoom = 2425;
                itemDef.rotationX = 1838;
                itemDef.rotationY = 313;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -25;
                itemDef.modelOffsetY = -38;
                break;

            case 20400: //@cya@Enraged Cape
            case 20099: //
            case 19994: //
                itemDef.modelZoom = 2902;
                itemDef.rotationX = 1050;
                itemDef.rotationY = 400;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 36;
                break;
            case 8087: //<col=eed81d><shad=9283>Dragon Rider lance<shad-1>
                itemDef.modelZoom = 2108;
                itemDef.rotationX = 1059;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 8088: //<col=fb9ae5><shad=9283>Judicator crossbow<shad-1>
                itemDef.modelZoom = 1171;
                itemDef.rotationX = 1237;
                itemDef.rotationY = 440;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 8089: //<col=57d0fd><shad=9283>Purifier staff<shad-1>
                itemDef.modelZoom = 2911;
                itemDef.rotationX = 1627;
                itemDef.rotationY = 533;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -16;
                itemDef.modelOffsetY = 0;
                break;
            case 8410: //Elite Sword (AoE)
                itemDef.modelZoom = 2476;
                itemDef.rotationX = 474;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -16;
                itemDef.modelOffsetY = -1;
                break;
            case 8411: //Elite Crossbow (AoE)
                itemDef.modelZoom = 1137;
                itemDef.rotationX = 1271;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = 4;
                break;
            case 8412: //Elite Staff (AoE)
                itemDef.modelZoom = 3280;
                itemDef.rotationX = 59;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -23;
                itemDef.modelOffsetY = -3;
                break;
            case 8828: //Elite helm (melee)
                itemDef.modelZoom = 970;
                itemDef.rotationX = 110;
                itemDef.rotationY = 8;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = -6;
                break;
            case 15645: //Elite helm (mage)
                itemDef.modelZoom = 869;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 82;
                break;
            case 15642: //Elite helm (ranged)
                itemDef.modelZoom = 568;
                itemDef.rotationX = 2036;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                break;
            case 15643: //Elite body (ranged)
                itemDef.modelZoom = 1271;
                itemDef.rotationX = 0;
                itemDef.rotationY = 468;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 3;
                break;
            case 15646: //Elite body (mage)
                itemDef.modelZoom = 1438;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 45;
                break;
            case 15644: //Elite legs (ranged)
                itemDef.modelZoom = 1706;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;
            case 15647: //Elite legs (mage)
                itemDef.modelZoom = 1840;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;

            case 18750: //<col=eed81d><shad=9283>Corrupt Maxiblood Scythe<shad-1>
                itemDef.modelZoom = 3012;
                itemDef.rotationX = 999;
                itemDef.rotationY = 330;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 18751: //<col=eed81d><shad=9283>Corrupt Maxiblood legs<shad-1>
                itemDef.modelZoom = 1706;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                break;
            case 18752: //<col=eed81d><shad=9283>Corrupt Maxiblood platebody<shad-1>
                itemDef.modelZoom = 1472;
                itemDef.rotationX = 0;
                itemDef.rotationY = 468;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 3;
                break;
            case 18753: //<col=eed81d><shad=9283>Corrupt Maxiblood helm<shad-1>
                itemDef.modelZoom = 769;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -6;
                break;
            case 18755: //Archie boots
                itemDef.modelZoom = 770;
                itemDef.rotationX = 160;
                itemDef.rotationY = 152;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -6;
                break;
            case 18637: //<col=57d0fd><shad=9283>Corrupt moonlight robetop<shad-1>
                itemDef.modelZoom = 1260;
                itemDef.rotationX = 0;
                itemDef.rotationY = 464;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 4;
                break;
            case 18638: //<col=fb9ae5><shad=9283>Corrupt Archie chaps<shad-1>
                itemDef.modelZoom = 1800;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                break;
            case 18749: //<col=fb9ae5><shad=9283>Corrupt Archie helm<shad-1>
                itemDef.modelZoom = 936;
                itemDef.rotationX = 93;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                break;
            case 18748: //<col=fb9ae5><shad=9283>Corrupt Archie body<shad-1>
                itemDef.modelZoom = 1673;
                itemDef.rotationX = 0;
                itemDef.rotationY = 468;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                break;
            case 18629: //<col=57d0fd><shad=9283>Corrupt moonlight staff<shad-1>
                itemDef.modelZoom = 2443;
                itemDef.rotationX = 991;
                itemDef.rotationY = 262;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 38;
                break;
            case 22077: //Brutal Tentacle
                itemDef.modelZoom = 970;
                itemDef.rotationX = 169;
                itemDef.rotationY = 84;
                itemDef.rotationZ = 489;
                itemDef.modelOffsetX = 21;
                itemDef.modelOffsetY = -33;
                break;
            case 6927: //Torva full helm
            case 19931: //
            case 17614: //
                itemDef.modelZoom = 1037;
                itemDef.rotationX = 110;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -10;
                break;
            case 6928: //Torva platebody
            case 19933: //
            case 17616: //
                itemDef.modelZoom = 1438;
                itemDef.rotationX = 0;
                itemDef.rotationY = 473;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;
            case 6929: //Torva platelegs
            case 19934: //
            case 17618: //
                itemDef.modelZoom = 1807;
                itemDef.rotationX = 2045;
                itemDef.rotationY = 474;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                break;
            case 6933: //Virtus mask
                itemDef.modelZoom = 903;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -3;
                break;
            case 6934: //Virtus robe top
                itemDef.modelZoom = 1238;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 6935: //Virtus robe legs
                itemDef.modelZoom = 1673;
                itemDef.rotationX = 2045;
                itemDef.rotationY = 498;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -6;
                break;
            case 6936: //Brutal staff
            case 5095:
            case 5096:
                itemDef.modelZoom = 2811;
                itemDef.rotationX = 1000;
                itemDef.rotationY = 338;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = -3;
                break;
            case 19140: //Magebeast helm
                itemDef.modelZoom = 602;
                itemDef.rotationX = 0;
                itemDef.rotationY = 533;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -6;
                break;
            case 22078: //Demon maul
            case 22079: //ankoue maul
                itemDef.modelZoom = 2208;
                itemDef.rotationX = 550;
                itemDef.rotationY = 501;
                itemDef.rotationZ = 108;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 1;
                break;
            case 12634: //<col=ff6f6f>Inferno Wings
                itemDef.modelZoom = 3213;
                itemDef.rotationX = 1016;
                itemDef.rotationY = 400;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 6;
                break;
            case 20549: //Shikruu Katana
                itemDef.modelZoom = 1673;
                itemDef.rotationX = 1525;
                itemDef.rotationY = 313;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 8800: //Shikruu helm
                itemDef.modelZoom = 903;
                itemDef.rotationX = 127;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -6;
                break;
            case 8801: //Shikruu body
                itemDef.modelZoom = 1505;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                break;
            case 8802: //Shikruu legs
                itemDef.modelZoom = 1673;
                itemDef.rotationX = 1796;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 1;
                break;
            case 8803: //Sorrow horn-coif
                itemDef.modelZoom = 702;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -3;
                break;
            case 8804: //Sorrow body
                itemDef.modelZoom = 1511;
                itemDef.rotationX = 0;
                itemDef.rotationY = 464;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 4;
                break;
            case 8805: //Sorrow chaps
                itemDef.modelZoom = 1781;
                itemDef.rotationX = 1796;
                itemDef.rotationY = 483;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 19;
                break;
            case 8807: //Ganopurp poncho
                itemDef.modelZoom = 1325;
                itemDef.rotationX = 0;
                itemDef.rotationY = 464;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 4;
                break;
            case 8808: //Ganopurp legging
                itemDef.modelZoom = 1636;
                itemDef.rotationX = 262;
                itemDef.rotationY = 456;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                break;
            case 8809: //Ganopurp staff
                itemDef.modelZoom = 3463;
                itemDef.rotationX = 1042;
                itemDef.rotationY = 500;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -15;
                itemDef.modelOffsetY = 1;
                break;
            case 8810: //<col=7f39f2>Lunite Wings
                itemDef.modelZoom = 3297;
                itemDef.rotationX = 0;
                itemDef.rotationY = 1550;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;

            case 19749: //Wing kitesield
                itemDef.modelZoom = 2591;
                itemDef.rotationX = 1127;
                itemDef.rotationY = 705;
                itemDef.rotationZ = 97;
                itemDef.modelOffsetX = 8;
                itemDef.modelOffsetY = -4;
                break;
            case 22080: //Heavy chainsaw
                itemDef.modelZoom = 1636;
                itemDef.rotationX = 618;
                itemDef.rotationY = 669;
                itemDef.rotationZ = 108;
                itemDef.modelOffsetX = 11;
                itemDef.modelOffsetY = 1;
                break;
            case 15401: //Lava Ring
                itemDef.modelZoom = 847;
                itemDef.rotationX = 135;
                itemDef.rotationY = 533;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                break;
            case 8812: //Lunite gloves
                itemDef.modelZoom = 888;
                itemDef.rotationX = 1016;
                itemDef.rotationY = 559;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = -1;
                break;
            case 20173: //Sorrow Bow
                itemDef.modelZoom = 2674;
                itemDef.rotationX = 355;
                itemDef.rotationY = 644;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -11;
                itemDef.modelOffsetY = 7;
                break;
            case 3107: //Groudon-flame boots
                itemDef.modelZoom = 805;
                itemDef.rotationX = 169;
                itemDef.rotationY = 127;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 0;
                break;

            case 5071: //Joker gloves
                itemDef.modelZoom = 764;
                itemDef.rotationX = 1050;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 286;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -1;
                break;
            case 12608: //SS Aura
            case 12630: //Donator Aura
                itemDef.modelZoom = 3754;
                itemDef.rotationX = 135;
                itemDef.rotationY = 67;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -10;
                break;
            case 15832: //Super buu ward
                itemDef.modelZoom = 1241;
                itemDef.rotationX = 220;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 1;
                break;
            case 20591: //@gre@Rage Cape
                itemDef.modelZoom = 1968;
                itemDef.rotationX = 237;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -1;
                break;
            case 7995: //Owner's cape
                itemDef.modelZoom = 2757;
                itemDef.rotationX = 1271;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 0;
                break;
            case 17700: //Wrath Hammer Offhand
                itemDef.modelZoom = 2736;
                itemDef.rotationX = 1711;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 31;
                itemDef.modelOffsetX = -45;
                itemDef.modelOffsetY = 10;
                break;
            case 2025: //Dragonballz Bones
            case 2023: //
                itemDef.modelZoom = 1885;
                itemDef.rotationX = 0;
                itemDef.rotationY = 372;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 11;
                itemDef.modelOffsetY = 16;
                break;
            case 21060: //Slayer dualsaber
            case 21061: //dualsaber
                itemDef.modelZoom = 3484;
                itemDef.rotationX = 398;
                itemDef.rotationY = 796;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -6;
                break;
            case 14827: //American pernix hood
                itemDef.modelZoom = 1013;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 6;
                break;
            case 20488: //<col=2299099><shad=12992>OVERPOWERED CHEST<shad-1>
                itemDef.modelZoom = 3006;
                itemDef.rotationX = 172;
                itemDef.rotationY = 160;
                itemDef.rotationZ = 50;
                itemDef.modelOffsetX = 16;
                itemDef.modelOffsetY = -50;
                break;
            case 8834: //Predator Reaper
                itemDef.modelZoom = 1823;
                itemDef.rotationX = 1010;
                itemDef.rotationY = 500;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = 2;
                break;
            case 8835: //Predator Offhand
                itemDef.modelZoom = 1823;
                itemDef.rotationX = 0;
                itemDef.rotationY = 500;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 6;
                itemDef.modelOffsetY = -3;
                break;
            case 18636: //<col=fb9ae5><shad=9283>Corrupt Archie bow<shad-1>
                itemDef.modelZoom = 2259;
                itemDef.rotationX = 1508;
                itemDef.rotationY = 516;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 14;
                itemDef.modelOffsetY = 21;
                break;
            case 1486: //Creeper Cape
                itemDef.modelZoom = 3027;
                itemDef.rotationX = 1016;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 2;
                break;
            case 4411: //Impspawn wings
                itemDef.modelZoom = 1387;
                itemDef.rotationX = 1533;
                itemDef.rotationY = 466;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 4;
                break;
            case 19165: //Darthvader legs
                itemDef.modelZoom = 1781;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 7;
                break;
            case 19166: //Darthvader body
                itemDef.modelZoom = 1428;
                itemDef.rotationX = 0;
                itemDef.rotationY = 464;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 9;
                break;
            case 19468: //Darthvader mask
                itemDef.modelZoom = 847;
                itemDef.rotationX = 84;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;
            case 20554: //Viggora's chainmace
                itemDef.modelZoom = 1428;
                itemDef.rotationX = 1296;
                itemDef.rotationY = 516;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = 14;
                break;
            case 677: //Lord helmet
                itemDef.modelZoom = 826;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 1;
                break;
            case 7544: //Perfect Cell Teddybear
            case 21610: //
                itemDef.modelZoom = 1055;
                itemDef.rotationX = 0;
                itemDef.rotationY = 415;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 9;
                break;
            case 11766: //Freiza gloves
                itemDef.modelZoom = 847;
                itemDef.rotationX = 398;
                itemDef.rotationY = 135;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -1;
                break;
            case 18883: //Supreme gloves
                itemDef.modelZoom = 789;
                itemDef.rotationX = 1762;
                itemDef.rotationY = 296;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 53;
                break;
            case 15922: //Maleficent helm
                itemDef.modelZoom = 619;
                itemDef.rotationX = 0;
                itemDef.rotationY = 108;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -6;
                break;
            case 19946: //<col=a69eb6>Avatar titan gloves
                itemDef.modelZoom = 722;
                itemDef.rotationX = 1254;
                itemDef.rotationY = 609;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = -8;
                break;
            case 703: //<col=a69eb6>Avatar titan helm
                itemDef.modelZoom = 1013;
                itemDef.rotationX = 135;
                itemDef.rotationY = 81;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -10;
                break;
            case 704: //<col=a69eb6>Avatar titan body
                itemDef.modelZoom = 1511;
                itemDef.rotationX = 0;
                itemDef.rotationY = 464;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 11;
                break;
            case 705: //<col=a69eb6>Avatar titan legs
                itemDef.modelZoom = 2051;
                itemDef.rotationX = 0;
                itemDef.rotationY = 456;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 6;
                break;
            case 8806: //Ganopurp visor
                itemDef.modelZoom = 868;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -1;
                break;
            case 17712: //<col=b5aaaa>Angels Flamethrower
                itemDef.modelZoom = 1864;
                itemDef.rotationX = 1000;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -8;
                break;
            case 22075: //Starlight sword
                itemDef.modelZoom = 1553;
                itemDef.rotationX = 516;
                itemDef.rotationY = 516;
                itemDef.rotationZ = 108;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 1;
                break;
            case 19471: //Inferior helm
                itemDef.modelZoom = 826;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;
            case 4369: //Fractite wings
                itemDef.modelZoom = 2487;
                itemDef.rotationX = 262;
                itemDef.rotationY = 516;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -10;
                itemDef.modelOffsetY = -13;
                break;
            case 15877: //Nuetron Dagger
                itemDef.modelZoom = 722;
                itemDef.rotationX = 237;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 1495;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 12;
                break;
            case 2021: //<col=b5aaaa>Angelic wings
                itemDef.modelZoom = 2923;
                itemDef.rotationX = 1033;
                itemDef.rotationY = 483;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -11;
                break;

            case 15230: //Zeus full helm
            case 21031: //
                itemDef.modelZoom = 1055;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -1;
                break;
            case 15231: //Zeus body
            case 21032: //
                itemDef.modelZoom = 1304;
                itemDef.rotationX = 0;
                itemDef.rotationY = 488;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 0;
                break;
            case 15232: //Zeus legs
            case 21033: //
                itemDef.modelZoom = 1844;
                itemDef.rotationX = 0;
                itemDef.rotationY = 431;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 12;
                break;
            case 15233: //Zeus Hammer
                itemDef.modelZoom = 1345;
                itemDef.rotationX = 1457;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -3;
                break;
            case 15234: //Zeus shield
            case 21035: //
                itemDef.modelZoom = 1428;
                itemDef.rotationX = 533;
                itemDef.rotationY = 381;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 2;
                break;
            case 15235: //Electric gloves
                itemDef.modelZoom = 847;
                itemDef.rotationX = 610;
                itemDef.rotationY = 898;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;

            case 17662: //zeus flagcape
                itemDef.modelZoom = 1449;
                itemDef.rotationX = 872;
                itemDef.rotationY = 504;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 1;
                break;

            case 19928: //jack legs
                itemDef.modelZoom = 1660;
                itemDef.rotationX = 186;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 4;
                break;
            case 23100: //Halloween basket
                itemDef.modelZoom = 722;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                break;
            case 23101: //Halloween gloves
                itemDef.modelZoom = 722;
                itemDef.rotationX = 601;
                itemDef.rotationY = 101;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = -6;
                break;
            case 23102: //Spooky Bat Pet
                itemDef.modelZoom = 1885;
                itemDef.rotationX = 1677;
                itemDef.rotationY = 177;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -49;
                break;
            case 23103: //Spooky Kraken Pet
                itemDef.modelZoom = 2944;
                itemDef.rotationX = 1694;
                itemDef.rotationY = 152;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -54;
                break;
            case 10723: //Halloween mask
                itemDef.modelZoom = 619;
                itemDef.rotationX = 1964;
                itemDef.rotationY = 2047;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -2;
                itemDef.modelOffsetY = -1;
                break;
            case 11790: //Reaper Hood (u)
                itemDef.modelZoom = 1781;
                itemDef.rotationX = 1152;
                itemDef.rotationY = 84;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -38;
                break;
            case 12858: //@yel@USD
                itemDef.modelZoom = 785;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;
            case 12857: //@whi@Ethereum
                itemDef.modelZoom = 722;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 1;
                break;
            case 12856: //@or2@BTC
                itemDef.modelZoom = 743;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;
            case 19094: //@gre@Cash Room Key
                itemDef.modelZoom = 1761;
                itemDef.rotationX = 1067;
                itemDef.rotationY = 276;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 73;
                break;
            case 22107: //Lucifer's pet
                itemDef.modelZoom = 494;
                itemDef.rotationX = 1889;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 85;
                break;
            case 9054: //Creeper helm
            case 14060: //
                itemDef.modelZoom = 1262;
                itemDef.rotationX = 0;
                itemDef.rotationY = 338;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 6;
                break;
            case 15115: //<shad=1><col=00e673>Extreme helm
                itemDef.modelZoom = 826;
                itemDef.rotationX = 0;
                itemDef.rotationY = 81;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -7;
                break;
            case 21607: //Custom Extreme helm
            case 19984: //
                itemDef.modelZoom = 805;
                itemDef.rotationX = 0;
                itemDef.rotationY = 81;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -3;
                break;
            case 14910: //Demonic Torva full helm
                itemDef.modelZoom = 702;
                itemDef.rotationX = 1867;
                itemDef.rotationY = 85;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -6;
                break;
            case 14921: //Icey virtus mask
                itemDef.modelZoom = 928;
                itemDef.rotationX = 2041;
                itemDef.rotationY = 406;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 2;
                break;
            case 21608: //Custom Extreme body
            case 19985: //
                itemDef.modelZoom = 1594;
                itemDef.rotationX = 0;
                itemDef.rotationY = 488;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 0;
                break;
            case 15116: //<shad=1><col=00e673>Extreme body
                itemDef.modelZoom = 1428;
                itemDef.rotationX = 0;
                itemDef.rotationY = 464;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 7;
                break;
            case 9055: //Creeper body
            case 14061: //
                itemDef.modelZoom = 1636;
                itemDef.rotationX = 0;
                itemDef.rotationY = 464;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 7;
                itemDef.modelOffsetY = 7;
                break;
            case 9056: //Creeper legs
            case 14062: //
                itemDef.modelZoom = 1761;
                itemDef.rotationX = 0;
                itemDef.rotationY = 456;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 7;
                break;
            case 15117: //<shad=1><col=00e673>Extreme legs
                itemDef.modelZoom = 1740;
                itemDef.rotationX = 1796;
                itemDef.rotationY = 456;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 6;
                break;
            case 21609: //Custom Extreme legs
            case 19986: //
                itemDef.modelZoom = 1844;
                itemDef.rotationX = 0;
                itemDef.rotationY = 431;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 9;
                break;
            case 20098: //Cursed scimitar
                itemDef.modelZoom = 1740;
                itemDef.rotationX = 1991;
                itemDef.rotationY = 566;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 9;
                itemDef.modelOffsetY = -1;
                break;
            case 3260: //@red@Supreme Blade
            case 7885: //@red@mac's Blade
                itemDef.modelZoom = 1532;
                itemDef.rotationX = 1042;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -10;
                itemDef.modelOffsetY = 1;
                break;
            case 5010: //Dan's Limited Bow
                itemDef.modelZoom = 2840;
                itemDef.rotationX = 559;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -3;
                break;
            case 17011: //Sanguinesti Execution Staff
            case 22114: //<shad=1>@bla@Dark Sanguinesti Staff
            case 17013: //@whi@Light Sanguinesti Staff
                itemDef.modelZoom = 2238;
                itemDef.rotationX = 1059;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 0;
                break;
            case 4018: //<shad=1><col=00e673>Extreme Offhand
                itemDef.modelZoom = 1802;
                itemDef.rotationX = 1010;
                itemDef.rotationY = 500;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = 2;
                break;
            case 4373: //<col=000222>Elite Winged Aura
                itemDef.modelZoom = 1989;
                itemDef.rotationX = 1033;
                itemDef.rotationY = 1500;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -3;
                break;
            case 17696: //Victory Hammer Offhand
                itemDef.modelZoom = 3235;
                itemDef.rotationX = 1635;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -35;
                itemDef.modelOffsetY = 17;
                break;
            case 22117: //Zenyte shield
                itemDef.modelZoom = 1636;
                itemDef.rotationX = 1025;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -6;
                itemDef.modelOffsetY = 4;
                break;
            case 20092: //Cursed amulet
                itemDef.modelZoom = 620;
                itemDef.rotationX = 68;
                itemDef.rotationY = 424;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 7;
                break;
            case 22105: //Lucifer's wings
                itemDef.modelZoom = 3982;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                break;
            case 22109: //Upgraded Owner's Cape
                itemDef.modelZoom = 1947;
                itemDef.rotationX = 1254;
                itemDef.rotationY = 400;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 6;
                break;
            case 22111: //Owner's Aura
                itemDef.modelZoom = 2383;
                itemDef.rotationX = 1025;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                break;
            case 9057: //Creeper gloves
            case 14063: //
                itemDef.modelZoom = 619;
                itemDef.rotationX = 542;
                itemDef.rotationY = 118;
                itemDef.rotationZ = 1239;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -20;
                break;
            case 15118: //<shad=1><col=00e673>Extreme gloves
                itemDef.modelZoom = 805;
                itemDef.rotationX = 983;
                itemDef.rotationY = 627;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = -3;
                break;
            case 9058: //Creeper boots
            case 14064: //
                itemDef.modelZoom = 702;
                itemDef.rotationX = 118;
                itemDef.rotationY = 101;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                break;
            case 22103: //Lucifer's boots
                itemDef.modelZoom = 722;
                itemDef.rotationX = 106;
                itemDef.rotationY = 63;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -1;
                break;
            case 21034: //Hades Hammer
                itemDef.modelZoom = 1304;
                itemDef.rotationX = 1042;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = 0;
                break;
            case 20542: //Satanic hellblade
                itemDef.modelZoom = 5000;
                itemDef.rotationX = 1288;
                itemDef.rotationY = 508;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -37;
                itemDef.modelOffsetY = -71;
                break;
            case 14065: //Crystalized sword
                itemDef.modelZoom = 1470;
                itemDef.rotationX = 500;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 1;
                break;
            case 17698: //Wrath Hammer
                itemDef.modelZoom = 2819;
                itemDef.rotationX = 483;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 26;
                itemDef.modelOffsetY = 2;
                break;
            case 21058: //dualsaber
                itemDef.modelZoom = 3297;
                itemDef.rotationX = 381;
                itemDef.rotationY = 169;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -40;
                break;
            case 734: //<col=b3b4f2>Demon big maul
                itemDef.modelZoom = 3775;
                itemDef.rotationX = 1542;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -10;
                itemDef.modelOffsetY = 28;
                break;
            case 17708: //<col=b5f2b3>Joyxox Sword
                itemDef.modelZoom = 2197;
                itemDef.rotationX = 1474;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -3;
                break;
            case 7543: //Perfect cell Rifle
            case 22089: //
                itemDef.modelZoom = 2030;
                itemDef.rotationX = 245;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -10;
                itemDef.modelOffsetY = 14;
                break;
            case 9478: //Super buu helm
                itemDef.modelZoom = 743;
                itemDef.rotationX = 110;
                itemDef.rotationY = 81;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;
            case 9481: //Perfect cell helm
                itemDef.modelZoom = 826;
                itemDef.rotationX = 93;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -3;
                break;
            case 11763: //Freiza helm
                itemDef.modelZoom = 619;
                itemDef.rotationX = 59;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;
            case 13323: //Goku head
                itemDef.modelZoom = 1117;
                itemDef.rotationX = 1932;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -1;
                break;
            case 3737: //Scarlet AoE Sword
                itemDef.modelZoom = 2965;
                itemDef.rotationX = 1966;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 9;
                break;
            case 3739: //Azure AoE Staff
                itemDef.modelZoom = 3775;
                itemDef.rotationX = 991;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -10;
                itemDef.modelOffsetY = -3;
                break;
            case 3738: //Herbal AoE Bow
                itemDef.modelZoom = 2363;
                itemDef.rotationX = 1940;
                itemDef.rotationY = 471;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -10;
                itemDef.modelOffsetY = 14;
                break;
            case 7640: //Moonlight staff
                itemDef.modelZoom = 1989;
                itemDef.rotationX = 610;
                itemDef.rotationY = 177;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -23;
                break;
            case 5073: //Joker Rpg
                itemDef.modelZoom = 909;
                itemDef.rotationX = 84;
                itemDef.rotationY = 118;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -11;
                break;
            case 21054: //Cursed Ballista
            case 21053: //
            case 21055: //
            case 21056: //
                itemDef.modelZoom = 1408;
                itemDef.rotationX = 110;
                itemDef.rotationY = 228;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -74;
                break;
            case 20553: //PVM blowpipe
                itemDef.modelZoom = 1158;
                itemDef.rotationX = 189;
                itemDef.rotationY = 768;
                itemDef.rotationZ = 108;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 1;
                break;
            case 8816: //Bulwark helm
                itemDef.modelZoom = 1013;
                itemDef.rotationX = 108;
                itemDef.rotationY = 81;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -11;
                break;
            case 8817: //Bulwark body
                itemDef.modelZoom = 1449;
                itemDef.rotationX = 0;
                itemDef.rotationY = 464;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 14;
                break;
            case 8818: //Bulwark legs
                itemDef.modelZoom = 1989;
                itemDef.rotationX = 0;
                itemDef.rotationY = 456;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 6;
                break;
            case 8819: //Bulwark gloves
                itemDef.modelZoom = 888;
                itemDef.rotationX = 1674;
                itemDef.rotationY = 346;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 24;
                break;
            case 8820: //Bulwark boots
                itemDef.modelZoom = 743;
                itemDef.rotationX = 72;
                itemDef.rotationY = 99;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -8;
                break;
            case 17622: //Shadow boots
                itemDef.modelZoom = 805;
                itemDef.rotationX = 118;
                itemDef.rotationY = 110;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -3;
                break;
            case 17624: //Shadow whip
                itemDef.modelZoom = 1241;
                itemDef.rotationX = 627;
                itemDef.rotationY = 1042;
                itemDef.rotationZ = 552;
                itemDef.modelOffsetX = -2;
                itemDef.modelOffsetY = -18;
                break;
            case 17620: //Shadow kiteshield
                itemDef.modelZoom = 1823;
                itemDef.rotationX = 1152;
                itemDef.rotationY = 504;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -11;
                break;
            case 17606: //<col=7d9781>Shadow wings
                itemDef.modelZoom = 2923;
                itemDef.rotationX = 0;
                itemDef.rotationY = 504;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -8;
                break;
            case 17734: //GreatRealm Axe
                itemDef.modelZoom = 2280;
                itemDef.rotationX = 1313;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = 2;
                break;
            case 17718: //Batman's bow
                itemDef.modelZoom = 2238;
                itemDef.rotationX = 983;
                itemDef.rotationY = 661;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 7;
                break;
            case 19811: //Dragonslayer helm
                itemDef.modelZoom = 764;
                itemDef.rotationX = 108;
                itemDef.rotationY = 81;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 6;
                break;
            case 7927: //Cyantrix ring
                itemDef.modelZoom = 743;
                itemDef.rotationX = 194;
                itemDef.rotationY = 581;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 0;
                break;
            case 19917: //Arc mace
                itemDef.modelZoom = 1221;
                itemDef.rotationX = 508;
                itemDef.rotationY = 466;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 2;
                break;
            case 19919: //Arc rapier
                itemDef.modelZoom = 1304;
                itemDef.rotationX = 40;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 2;
                break;
            case 22084: //Trinity hammers
                itemDef.modelZoom = 1511;
                itemDef.rotationX = 1152;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -6;
                break;
            case 22083: //Blast bow
                itemDef.modelZoom = 1719;
                itemDef.rotationX = 466;
                itemDef.rotationY = 338;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -13;
                itemDef.modelOffsetY = 6;
                break;
            case 22092: //Art's Staff
                itemDef.modelZoom = 2155;
                itemDef.rotationX = 1474;
                itemDef.rotationY = 305;
                itemDef.rotationZ = 10;
                itemDef.modelOffsetX = 17;
                itemDef.modelOffsetY = 114;
                break;
            case 18623: //<col=57d0fd><shad=9283>Corrupt moonlight robebottom<shad-1>
                itemDef.modelZoom = 1740;
                itemDef.rotationX = 0;
                itemDef.rotationY = 456;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 7;
                break;
            case 18631: //<col=57d0fd><shad=9283>Corrupt moonlight hood<shad-1>
                itemDef.modelZoom = 702;
                itemDef.rotationX = 108;
                itemDef.rotationY = 76;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -7;
                break;
            case 6500: //Charming imp
                itemDef.modelZoom = 1698;
                itemDef.rotationX = 2008;
                itemDef.rotationY = 280;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                break;
            case 21018: //Supreme bow
                itemDef.modelZoom = 2280;
                itemDef.rotationX = 211;
                itemDef.rotationY = 288;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -40;
                itemDef.modelOffsetY = 200;
                break;
            case 11001: //Hiddenvally coif
                itemDef.modelZoom = 639;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -1;
                break;

            case 23411: //Blood Odin Pet
                itemDef.modelZoom = 3878;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -10;
                break;
            case 22116: //Iron's pet
                itemDef.modelZoom = 4958;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 7;
                itemDef.modelOffsetY = -7;
                break;
            case 11318: //@yel@Raichu pet
                itemDef.modelZoom = 3775;
                itemDef.rotationX = 135;
                itemDef.rotationY = 110;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -20;
                break;
            case 11317: //Bumble bee pet
                itemDef.modelZoom = 3276;
                itemDef.rotationX = 135;
                itemDef.rotationY = 194;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -30;
                break;
            case 5560: //Super Star pet
                itemDef.modelZoom = 2217;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 43;
                break;
            case 11319: //Zorbak (elite) pet
                itemDef.modelZoom = 2155;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -3;
                break;
            case 5504: //Charizard pet
                itemDef.modelZoom = 5000;
                itemDef.rotationX = 76;
                itemDef.rotationY = 128;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = 1;
                break;
            case 462: //Yoshi pet
                itemDef.modelZoom = 702;
                itemDef.rotationX = 1904;
                itemDef.rotationY = 156;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 150;
                break;
            case 11314: //<img=1354><col=f8ac00>Goku Double Killcount Pet<img=1354>
                itemDef.modelZoom = 1000;
                itemDef.rotationX = 10;
                itemDef.rotationY = 10;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -1;
                break;
            case 5506: //Donkeykong pet
                itemDef.modelZoom = 4730;
                itemDef.rotationX = 59;
                itemDef.rotationY = 186;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -54;
                break;
            case 460: //Gorilla pet
                itemDef.modelZoom = 3442;
                itemDef.rotationX = 93;
                itemDef.rotationY = 186;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -30;
                break;
            case 23300: //Turkey Pet
                itemDef.modelZoom = 909;
                itemDef.rotationX = 161;
                itemDef.rotationY = 144;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -1;
                break;
            case 11310: //Youtube JR Pet
                itemDef.modelZoom = 2799;
                itemDef.rotationX = 67;
                itemDef.rotationY = 144;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -16;
                break;
            case 11315: //<img=1355><col=f8ac00>double afk pet
                itemDef.modelZoom = 3858;
                itemDef.rotationX = 2000;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -5;
                itemDef.modelOffsetY = -20;
                break;
            case 461: //Vegeta pet
                itemDef.modelZoom = 888;
                itemDef.rotationX = 1932;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 1;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 82;
                break;

            case 12855: //@yel@Upgrade Tokens
                itemDef.modelZoom = 1075;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = -1;
                break;
            case 23313: //Thanksgiving Amulet
                itemDef.modelZoom = 473;
                itemDef.rotationX = 1923;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -5;
                break;
            case 23314: //<col=d94401>Zenyte Casket</col>
                itemDef.modelZoom = 1553;
                itemDef.rotationX = 144;
                itemDef.rotationY = 100;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -11;
                break;

            case 23351: //Owner Amulet
                itemDef.modelZoom = 473;
                itemDef.rotationX = 50;
                itemDef.rotationY = 313;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -3;
                break;

            case 23352: //Owner Ring
                itemDef.modelZoom = 515;
                itemDef.rotationX = 1940;
                itemDef.rotationY = 508;
                itemDef.rotationZ = 1927;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 4;
                break;
            case 23353: //Owner Bracelet
                itemDef.modelZoom = 515;
                itemDef.rotationX = 228;
                itemDef.rotationY = 389;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 2;
                break;
            case 23204: //Owner Jewelry Goodiebag
                itemDef.modelZoom = 847;
                itemDef.rotationX = 1576;
                itemDef.rotationY = 381;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                break;

            case 23362: //Elf hat
                itemDef.modelZoom = 681;
                itemDef.rotationX = 203;
                itemDef.rotationY = 228;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -6;
                break;
            case 23363: //Elf body
                itemDef.modelZoom = 1283;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 1;
                break;
            case 23364: //Elf legs
                itemDef.modelZoom = 1802;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 7;
                break;
            case 23365: //Elf gloves
                itemDef.modelZoom = 494;
                itemDef.rotationX = 1703;
                itemDef.rotationY = 152;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 90;
                break;
            case 23366: //Elf boots
                itemDef.modelZoom = 743;
                itemDef.rotationX = 203;
                itemDef.rotationY = 152;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -13;
                break;
            case 23367: //Elf hat
                itemDef.modelZoom = 785;
                itemDef.rotationX = 0;
                itemDef.rotationY = 423;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -8;
                break;
            case 23368: //Elf body
                itemDef.modelZoom = 1719;
                itemDef.rotationX = 0;
                itemDef.rotationY = 576;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -1;
                break;
            case 23369: //Elf legs
                itemDef.modelZoom = 1823;
                itemDef.rotationX = 0;
                itemDef.rotationY = 533;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 4;
                break;
            case 23370: //Elf gloves
                itemDef.modelZoom = 743;
                itemDef.rotationX = 1916;
                itemDef.rotationY = 324;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 6;
                break;
            case 23371: //Elf boots
                itemDef.modelZoom = 722;
                itemDef.rotationX = 118;
                itemDef.rotationY = 177;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -23;
                break;
            case 23372: //Elf cape
                itemDef.modelZoom = 1574;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 9;
                break;

            case 23382: //Snow Ranger Pet
                itemDef.modelZoom = 826;
                itemDef.rotationX = 93;
                itemDef.rotationY = 172;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 1;
                break;
            case 23384: //Snow Ranger Pet
                itemDef.modelZoom = 930;
                itemDef.rotationX = 118;
                itemDef.rotationY = 144;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 7;
                itemDef.modelOffsetY = -1;
                break;
            case 23383: //Snow Ranger Pet
                itemDef.modelZoom = 826;
                itemDef.rotationX = 110;
                itemDef.rotationY = 172;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -5;
                break;

            case 23385: //Santa Pet
            case 23386: //Santa Pet
                itemDef.modelZoom = 972 * 4;
                itemDef.rotationX = 84;
                itemDef.rotationY = 135;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                break;

            case 13025: //Grinch head
                itemDef.modelZoom = 847;
                itemDef.rotationX = 93;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -3;
                break;
            case 13027: //Grinch body
                itemDef.modelZoom = 1387;
                itemDef.rotationX = 0;
                itemDef.rotationY = 488;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 7;
                break;
            case 13023: //Grinch legs
                itemDef.modelZoom = 1740;
                itemDef.rotationX = 0;
                itemDef.rotationY = 431;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                break;
            case 13029: //Grinch gloves
                itemDef.modelZoom = 619;
                itemDef.rotationX = 805;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 275;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -3;
                break;
            case 13031: //Grinch boots
                itemDef.modelZoom = 681;
                itemDef.rotationX = 177;
                itemDef.rotationY = 135;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -10;
                break;
            case 23390: //Costume Box
                itemDef.modelZoom = 2695;
                itemDef.rotationX = 1576;
                itemDef.rotationY = 42;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 9;
                itemDef.modelOffsetY = -93;
                break;

            case 23378: //Evil Santa Top
                itemDef.modelZoom = 1179;
                itemDef.rotationX = 6;
                itemDef.rotationY = 561;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 7;
                itemDef.newColors = new int[] {4, 9, 8, 0, 13, };
                itemDef.oldColors = new int[] {924, 933, 928, 912, 940, };
                break;

            case 23356: //Lunite helm
                itemDef.modelZoom = 598;
                itemDef.rotationX = 0;
                itemDef.rotationY = 500;
                itemDef.rotationZ = 84;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = 0;
                break;
            case 23357: //Lunite body
                itemDef.modelZoom = 1408;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 6;
                break;
            case 23358: //Lunite legs
                itemDef.modelZoom = 1719;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 4;
                break;
            case 23359: //Lunite gloves
                itemDef.modelZoom = 494;
                itemDef.rotationX = 1916;
                itemDef.rotationY = 324;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = -3;
                break;
            case 23360: //Lunite boots
                itemDef.modelZoom = 764;
                itemDef.rotationX = 169;
                itemDef.rotationY = 135;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffsetY = -8;
                break;
            case 23361: //Lunite shield
                itemDef.modelZoom = 1324;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 0;
                break;

            case 20592: //Dan's Limited Mask
                itemDef.modelZoom = 577;
                itemDef.rotationX = 67;
                itemDef.rotationY = 81;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -5;
                break;

            case 23455: //Sang Bow
                itemDef.modelZoom = 2321;
                itemDef.rotationX = 432;
                itemDef.rotationY = 406;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -8;
                itemDef.modelOffsetY = 28;
                break;

            case 22100: //Lucifer's head
                itemDef.modelZoom = 702;
                itemDef.rotationX = 1915;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 80;
                itemDef.oldColors = new int[] {60, };
                itemDef.newColors = new int[] {52, };
                break;
            case 22101: //Lucifer's body
                itemDef.modelZoom = 1470;
                itemDef.rotationX = 2;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 51;
                itemDef.oldColors = new int[] {60, };
                itemDef.newColors = new int[] {52, };
                break;
            case 22102: //Lucifer's legs
                itemDef.modelZoom = 1885;
                itemDef.rotationX = 2;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                itemDef.oldColors = new int[] {60, };
                itemDef.newColors = new int[] {52, };
                break;

            case 23459: //Divine's Mask
                itemDef.modelZoom = 432;
                itemDef.rotationX = 0;
                itemDef.rotationY = 81;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 110;
                break;

            case 23465: //Mystic helm
                itemDef.modelZoom = 764;
                itemDef.rotationX = 118;
                itemDef.rotationY = 127;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = -5;
                break;
            case 23466: //Mystic body
                itemDef.modelZoom = 1511;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                break;
            case 23467: //Mystic legs
                itemDef.modelZoom = 1781;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -3;
                break;
            case 23468: //Hellish helm
                itemDef.modelZoom = 639;
                itemDef.rotationX = 161;
                itemDef.rotationY = 0;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = -3;
                break;
            case 23469: //Hellish body
                itemDef.modelZoom = 1304;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 23470: //Hellish legs
                itemDef.modelZoom = 1698;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 2;
                break;
            case 23471: //Midnight helm
                itemDef.modelZoom = 722;
                itemDef.rotationX = 0;
                itemDef.rotationY = 93;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                break;
            case 23472: //Midnight body
                itemDef.modelZoom = 1449;
                itemDef.rotationX = 0;
                itemDef.rotationY = 533;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 9;
                break;
            case 23473: //Midnight legs
                itemDef.modelZoom = 1636;
                itemDef.rotationX = 93;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = 4;
                break;

            case 779: //Crest part
                itemDef.modelZoom = 1428;
                itemDef.rotationX = 148;
                itemDef.rotationY = 356;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -11;
                itemDef.modelOffsetY = -40;
                break;
            case 780: //Crest part
                itemDef.modelZoom = 1221;
                itemDef.rotationX = 148;
                itemDef.rotationY = 356;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 23;
                itemDef.modelOffsetY = -16;
                break;
            case 781: //Crest part
                itemDef.modelZoom = 1532;
                itemDef.rotationX = 148;
                itemDef.rotationY = 356;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -3;
                itemDef.modelOffsetY = 36;
                break;
            case 782: //Family crest
                itemDef.modelZoom = 1511;
                itemDef.rotationX = 148;
                itemDef.rotationY = 356;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 14;
                break;

            case 23501: //Reaper helm
                itemDef.modelZoom = 764;
                itemDef.rotationX = 127;
                itemDef.rotationY = 59;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -1;
                break;
            case 23502: //Reaper body
                itemDef.modelZoom = 1491;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 4;
                break;
            case 23503: //Reaper legs
                itemDef.modelZoom = 1781;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -1;
                break;
            case 23504: //Reaper gloves
                itemDef.modelZoom = 743;
                itemDef.rotationX = 381;
                itemDef.rotationY = 186;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -13;
                break;
            case 23505: //Reaper boots
                itemDef.modelZoom = 743;
                itemDef.rotationX = 110;
                itemDef.rotationY = 169;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -15;
                break;
            case 23498: //Grandmaster body
                itemDef.modelZoom = 1387;
                itemDef.rotationX = 0;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 2;
                break;
            case 23499: //Grandmaster legs
                itemDef.modelZoom = 1636;
                itemDef.rotationX = 2000;
                itemDef.rotationY = 525;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 2;
                break;
            case 23497: //Grandmaster hood
                itemDef.modelZoom = 868;
                itemDef.rotationX = 110;
                itemDef.rotationY = 152;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -15;
                break;

            case 8101: //Light guild body
            case 8106:
                itemDef.modelZoom = 1532;
                itemDef.rotationX = 0;
                itemDef.rotationY = 464;
                itemDef.rotationZ = 0;
                itemDef.modelOffsetX = -1;
                itemDef.modelOffsetY = 4;
                break;
                case 23621: //Wolf Helm
                    itemDef.modelZoom = 743;
                    itemDef.rotationX = 0;
                    itemDef.rotationY = 0;
                    itemDef.rotationZ = 0;
                    itemDef.modelOffsetX = -1;
                    itemDef.modelOffsetY = 80;
                    break;
                case 23619: //Fenrir's Katana
                    itemDef.modelZoom = 1449;
                    itemDef.rotationX = 1161;
                    itemDef.rotationY = 872;
                    itemDef.rotationZ = 0;
                    itemDef.modelOffsetX = -11;
                    itemDef.modelOffsetY = -13;
                    break;


                case 23658: //Saradomin Pet
                    itemDef.modelZoom = 1055;
                    itemDef.rotationX = 0;
                    itemDef.rotationY = 172;
                    itemDef.rotationZ = 0;
                    itemDef.modelOffsetX = 2;
                    itemDef.modelOffsetY = -11;
                    break;
                case 23659: //Zamorak Pet
                    itemDef.modelZoom = 1075;
                    itemDef.rotationX = 60;
                    itemDef.rotationY = 172;
                    itemDef.rotationZ = 0;
                    itemDef.modelOffsetX = 0;
                    itemDef.modelOffsetY = -6;
                    break;

            }

        return itemDef;

    }
}
