package org.necrotic.client.cache.definition;

import org.necrotic.Configuration;
import org.necrotic.client.Client;
import org.necrotic.client.FrameReader;
import org.necrotic.client.List;
import org.necrotic.client.cache.Archive;
import org.necrotic.client.io.ByteBuffer;
import org.necrotic.client.world.Model;

import java.util.Objects;

public final class MobDefinition2 {

    public static MobDefinition newIDS(MobDefinition definition, int id) {
        
        switch (id){
            case 8003:
                definition.name = "Crazy Electro";
                definition.description = "Crazy Electro.".getBytes();
                definition.npcModels = new int[]{65178};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 273:
                definition.name = "Slayer Instructor";
                definition.description = "Creepy guy, but 200m Slayer XP will do that to you.".getBytes();
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                break;

            case 1685:
                MobDefinition shop22 = MobDefinition.get(410);
                definition.npcModels = shop22.npcModels;
                definition.name = "Pure";
                definition.actions = new String[]{"Trade", null, null, null, null};
                break;

            case 3030:
                definition.name = "King black dragon";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{17414, 17415, 17429, 17422};
                definition.combatLevel = 276;
                definition.standAnimation = 90;
                definition.walkAnimation = 4635;
                definition.scaleXZ = 63;
                definition.scaleY = 63;
                definition.npcSizeInSquares = 3;
                break;

            case 3031:
                definition.name = "General graardor";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{27785, 27789};
                definition.combatLevel = 624;
                definition.standAnimation = 7059;
                definition.walkAnimation = 7058;
                definition.scaleXZ = 40;
                definition.scaleY = 40;
                break;


            case 3032:
                definition.name = "TzTok-Jad";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{34131};
                definition.combatLevel = 702;
                definition.standAnimation = 9274;
                definition.walkAnimation = 9273;
                definition.scaleXZ = 45;
                definition.scaleY = 45;
                definition.npcSizeInSquares = 2;
                break;

            case 3033:
                definition.name = "Chaos elemental";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{11216};
                definition.combatLevel = 305;
                definition.standAnimation = 3144;
                definition.walkAnimation = 3145;
                definition.scaleXZ = 62;
                definition.scaleY = 62;
                break;
            case 3034:
                definition.name = "Corporeal beast";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{40955};
                definition.combatLevel = 785;
                definition.standAnimation = 10056;
                definition.walkAnimation = 10055;
                definition.scaleXZ = 45;
                definition.scaleY = 45;
                definition.npcSizeInSquares = 2;
                break;

            case 3035:
                definition.name = "Kree'arra";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{28003, 28004};
                definition.combatLevel = 580;
                definition.standAnimation = 6972;
                definition.walkAnimation = 6973;
                definition.scaleXZ = 43;
                definition.scaleY = 43;
                definition.npcSizeInSquares = 2;
                break;

            case 3036:
                definition.name = "K'ril tsutsaroth";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{27768, 27773, 27764, 27765, 27770};
                definition.combatLevel = 650;
                definition.standAnimation = 6943;
                definition.walkAnimation = 6942;
                definition.scaleXZ = 43;
                definition.scaleY = 43;
                definition.npcSizeInSquares = 2;
                break;
            case 804:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "Darth Vaider";
                definition.npcModels = new int[]{65150};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                definition.npcSizeInSquares = 2;
                break;
            case 3000:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "King Kong";
                definition.npcModels = new int[]{65154};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 400;
                definition.scaleY = 400;
                definition.npcSizeInSquares = 2;
                break;

            case 3002:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "GodZilla";
                definition.npcModels = new int[]{65155};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 300;
                definition.scaleY = 300;
                definition.npcSizeInSquares = 3;
                break;
            case 3115:
                definition.name = "Evil Lucien";
                definition.combatLevel = 785;
                definition.standAnimation = 66;
                definition.walkAnimation = 63;
                //definition.type = 51;
                definition.degreesToTurn = 32;
                definition.npcModels = new int[]{65158};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = 72;
                definition.scaleY = 72;
                definition.npcSizeInSquares = 2;
                definition.rdc2 = 7656789;
                break;
            case 3116:
                definition.name = "Skotizo";
                definition.combatLevel = 785;
                definition.standAnimation = 66;
                definition.walkAnimation = 63;
                //definition.type = 51;
                definition.degreesToTurn = 32;
                definition.npcModels = new int[]{65158};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = 72;
                definition.scaleY = 72;
                definition.npcSizeInSquares = 2;
                definition.rdc2 = 987896875;
                break;
            case 3117:
                definition.name = "Lucien Crypt";
                definition.combatLevel = 785;
                definition.standAnimation = 66;
                definition.walkAnimation = 63;
                //definition.type = 51;
                definition.degreesToTurn = 32;
                definition.npcModels = new int[]{65158};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = 52;
                definition.scaleY = 52;
                definition.npcSizeInSquares = 2;

                break;
            case 3113:
                definition.name = "Doomega";
                definition.combatLevel = 492;
                definition.walkAnimation = 1660;
                definition.standAnimation = 11973;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 55750; //HEAD
                definition.npcModels[1] = 45194; //JAW
                definition.npcModels[2] = 55890; //CHEST
                definition.npcModels[3] = 65067; //CAPE
                //definition.npcModels[4] = -1; //ARM
                definition.npcModels[5] = 56304; //HAND//shield
                definition.npcModels[6] = 65002; //WEP
                definition.npcModels[7] = 55806; //LEG
                definition.npcModels[8] = 55682; //BOOT
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.actions[1] = "Attack";
                definition.actions[2] = null;
                definition.actions[3] = null;
                definition.actions[4] = null;
                break;
            case 3003:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "BabyZilla";
                definition.npcModels = new int[]{65155};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 2;
                definition.rdc2 = 2424;
                break;
            case 3779:
                definition.name = "EarthQuake";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcModels = new int[]{130418};
                definition.npcSizeInSquares = 4;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                break;
            case 3830:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "Optimus Prime";
                definition.npcModels = new int[]{64392};
                definition.combatLevel = 785;
                definition.npcSizeInSquares = 4;
                //definition.rdc2 = 29492;
                break;
            case 3831:
                definition.name = "Azure beast";
                //definition.description = "MegaBeast".getBytes();
                definition.npcModels = new int[]{65209};
                definition.combatLevel = 800;
                definition.standAnimation = 10921;
                definition.walkAnimation = 10920;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 2;
                definition.degreesToTurn = 32;

                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 2949:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "Scarlet Falcon";
                definition.npcModels = new int[]{64477};
                MobDefinition Scarlet = MobDefinition.get(4972);
                definition.combatLevel = 800;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 3;
                definition.description = Scarlet.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = Scarlet.standAnimation;
                definition.walkAnimation = Scarlet.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                //definition.rdc2 = 29492;
                break;
            case 2950:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "herbal minion";
                definition.npcModels = new int[]{64478};
                MobDefinition Herbal = MobDefinition.get(20);
                definition.combatLevel = 100;
                definition.scaleXZ = 23;
                definition.scaleY = 23;
                definition.npcSizeInSquares = 1;
                definition.description = Herbal.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                //definition.rdc2 = 29492;
                break;
            case 2340:
                definition.name = "azure minion";
                //definition.description = "MegaBeast".getBytes();
                definition.npcModels = new int[]{65209};
                definition.combatLevel = 100;
                definition.standAnimation = 10921;
                definition.walkAnimation = 10920;
                definition.scaleXZ = 35;
                definition.scaleY = 35;
                definition.npcSizeInSquares = 1;
                definition.degreesToTurn = 32;

                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 2341:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "scarlet minion";
                definition.npcModels = new int[]{64477};
                MobDefinition Scarlet1 = MobDefinition.get(4972);
                definition.combatLevel = 100;
                definition.scaleXZ = 35;
                definition.scaleY = 35;
                definition.npcSizeInSquares = 1;
                definition.description = Scarlet1.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = Scarlet1.standAnimation;
                definition.walkAnimation = Scarlet1.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                //definition.rdc2 = 29492;
                break;
            case 2342:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "Herbal Rogue";
                definition.npcModels = new int[]{64478};
                MobDefinition Herbal1 = MobDefinition.get(20);
                definition.combatLevel = 800;
                definition.scaleXZ = 45;
                definition.scaleY = 45;
                definition.npcSizeInSquares = 2;
                definition.description = Herbal1.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.actions = new String[]{null, "Attack", null, null, null};
                //definition.rdc2 = 29492;
                break;
            case 3814:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "Juggernaut";
                definition.npcModels = new int[]{19927};
                MobDefinition worsk1 = MobDefinition.get(202);
                definition.combatLevel = 40;
                definition.scaleXZ = 300;
                definition.scaleY = 300;
                definition.npcSizeInSquares = 2;
                definition.description = worsk1.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = worsk1.standAnimation;
                definition.walkAnimation = worsk1.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};

                //.	definition.rdc2 = 238;
                break;
            case 1850:
                definition.npcModels = new int[]{3293};
                definition.combatLevel = 0;
                definition.standAnimation = 0;
                definition.walkAnimation = 0;
                break;
            case 1851:
                definition.npcModels = new int[]{65439};
                definition.combatLevel = 0;
                definition.standAnimation = 0;
                definition.walkAnimation = 0;
                break;
            case 1852:
                definition.npcModels = new int[]{37192};
                definition.combatLevel = 0;
                definition.standAnimation = 0;
                definition.walkAnimation = 0;
                break;
            case 1853://hidden
                definition.npcModels = new int[]{15885};
                definition.combatLevel = 0;
                definition.standAnimation = 0;
                definition.walkAnimation = 0;
                break;
            case 1854://curse
                definition.npcModels = new int[]{15848};
                definition.combatLevel = 0;
                definition.standAnimation = 0;
                definition.walkAnimation = 0;
                break;
            case 1855://damned
                definition.npcModels = new int[]{5494};
                definition.combatLevel = 0;
                definition.standAnimation = 0;
                definition.walkAnimation = 0;
                break;
            case 1856://betrayed
                definition.npcModels = new int[]{15567};
                definition.combatLevel = 0;
                definition.standAnimation = 0;
                definition.walkAnimation = 0;
                break;
            case 201:
                definition.name = "Hercules";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 2;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 64102; //HEAD
                definition.npcModels[1] = 64110; //JAW
                definition.npcModels[2] = 64104; //BODY
                definition.npcModels[3] = 64110; //CAPE
                definition.npcModels[4] = 64112; //HAND
                definition.npcModels[5] = 64108; ////shield
                definition.npcModels[6] = 65321; //WEP
                definition.npcModels[7] = 64106; //LEG
                definition.npcModels[8] = 64113; //BOOT
                definition.originalModelColours = new int[1];
                definition.changedModelColours = new int[1];
                definition.originalModelColours[0] = 40; // current
                definition.changedModelColours[0] = 60; // New
                break;
            case 202:
                definition.name = "Satan";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 2;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 64115; //HEAD
                definition.npcModels[1] = 62575; //JAW
                definition.npcModels[2] = 64117; //BODY
                definition.npcModels[3] = 65505; //CAPE
                definition.npcModels[4] = 64121; //HAND
                definition.npcModels[5] = 65507; ////shield
                definition.npcModels[6] = 65429; //WEP
                definition.npcModels[7] = 64119; //LEG
                definition.npcModels[8] = 64122; //BOOT
                definition.originalModelColours = new int[2];
                definition.changedModelColours = new int[2];
                definition.originalModelColours[0] = 40; // current
                definition.changedModelColours[0] = 54; // New
                definition.originalModelColours[1] = 52; // current
                definition.changedModelColours[1] = 54; // New
                //definition.npcModels[4] = definition.rdc2 = 2044;
                break;
            case 203:
                definition.name = "Almighty Zeus";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 2;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 190;
                definition.scaleY = 190;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 64260; //HEAD
                definition.npcModels[1] = 64009; //JAW
                definition.npcModels[2] = 64262; //BODY
                definition.npcModels[3] = 64110; //CAPE
                definition.npcModels[4] = 64176; //HAND
                definition.npcModels[5] = 64268; ////shield
                definition.npcModels[6] = 64266; //WEP
                definition.npcModels[7] = 64264; //LEG
                definition.npcModels[8] = 64178; //BOOT
                definition.originalModelColours = new int[1];
                definition.changedModelColours = new int[1];
                definition.originalModelColours[0] = 40; // current
                definition.changedModelColours[0] = 96; // New
                break;
            case 186:
                definition.name = "Mr. Grinch";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 8900;
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 230;
                definition.scaleY = 230;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 64307; //HEAD
                definition.npcModels[1] = 0; //JAW
                definition.npcModels[2] = 64309; //BODY
                definition.npcModels[3] = 0; //CAPE
                definition.npcModels[4] = 64313; //GLOVES
                definition.npcModels[5] = 0; ////shield
                definition.npcModels[6] = 0; //WEP
                definition.npcModels[7] = 64311; //LEG
                definition.npcModels[8] = 64314; //BOOT

                break;
            case 187:
                definition.name = "Goku (Dragonballz)";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 2;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 64124; //HEAD
                definition.npcModels[1] = 0; //JAW
                definition.npcModels[2] = 64126; //BODY
                definition.npcModels[3] = 0; //CAPE
                definition.npcModels[4] = 64130; //GLOVES
                definition.npcModels[5] = 0; ////shield
                definition.npcModels[6] = 0; //WEP
                definition.npcModels[7] = 64128; //LEG
                definition.npcModels[8] = 64131; //BOOT
                break;

            case 185:
                definition.name = "Joker";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 225;
                definition.scaleY = 225;
                definition.standAnimation = 8832;
                definition.walkAnimation = 8831;
                definition.npcModels = new int[]{64508};

                break;
            case 6430:
                definition.name = "Crystal Queen";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 170;
                definition.scaleY = 170;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.npcModels = new int[]{64512};

                break;
            case 6431:
                definition.name = "Crystal Queen pet";
                definition.actions = null;
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.npcModels = new int[]{64512};

                break;
            case 184:
                definition.name = "Joker pet";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 8832;
                definition.walkAnimation = 8831;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcModels = new int[]{64508};

                break;
            case 188:
                definition.name = "Byakuya (bleach)";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 170;
                definition.scaleY = 170;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 64133; //HEAD
                definition.npcModels[1] = 0; //JAW
                definition.npcModels[2] = 64135; //BODY
                definition.npcModels[3] = 0; //CAPE
                definition.npcModels[4] = 64130; //GLOVES
                definition.npcModels[5] = 0; ////shield
                definition.npcModels[6] = 64140; //WEP
                definition.npcModels[7] = 64137; //LEG
                definition.npcModels[8] = 64138; //BOOT

                break;
            case 3006:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "BabyZilla";
                definition.npcModels = new int[]{65155};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 2;
                definition.rdc2 = 9023;
                break;
            case 3004:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "BabyZilla";
                definition.npcModels = new int[]{65155};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 2;
                definition.rdc2 = 6178;
                break;


            case 8133:
                definition.rdc2 = 2143611;
                break;
            case 3112:
                definition.name = "Ezkel-Nojad";
                definition.actions = new String[]{null, "Attack", null, null, null};

                definition.npcModels = new int[]{34131};
                definition.combatLevel = 702;
                definition.standAnimation = 9274;
                definition.walkAnimation = 9273;
                definition.scaleXZ = 145;
                definition.scaleY = 145;
                definition.npcSizeInSquares = 2;
                definition.rdc2 = 3333;
                break;
            case 3005:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "BabyZilla";
                definition.npcModels = new int[]{65155};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 2;
                definition.rdc2 = 6464;
                break;
            case 603:
                definition.name = "Inferior Elite Lord";
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 492;
                definition.walkAnimation = 1660;
                definition.standAnimation = 11973;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65131; //HEAD
                definition.npcModels[1] = 246; //JAW
                definition.npcModels[2] = 65133; //CHEST
                //	definition.npcModels[3] = -1; //CAPE
                //definition.npcModels[4] = -1; //ARM
                definition.npcModels[5] = 65128; //HAND//shield
                definition.npcModels[6] = 65142; //WEP
                definition.npcModels[7] = 65135; //LEG
                definition.npcModels[8] = 65138; //BOOT
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.actions[1] = "Attack";
                definition.actions[2] = null;
                definition.actions[3] = null;
                definition.actions[4] = null;
                break;
            case 606:
                definition.name = "<col=7d9781>Shadow Warrior";
                definition.combatLevel = 284;
                definition.walkAnimation = 1660;
                definition.standAnimation = 11973;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65499; //HEAD
                definition.npcModels[1] = 55820; //JAW
                definition.npcModels[2] = 65501; //CHEST
                definition.npcModels[3] = 65505; //CAPE
                definition.npcModels[4] = 64090; //ARM
                definition.npcModels[5] = 65507; //HAND//shield
                definition.npcModels[6] = 65510; //WEP
                definition.npcModels[7] = 65503; //LEG
                definition.npcModels[8] = 65508; //BOOT
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.actions[1] = "Attack";
                definition.actions[2] = null;
                definition.actions[3] = null;
                definition.actions[4] = null;
                break;
            case 607:
                definition.name = "<col=E3E3E3>COL Warrior";
                definition.combatLevel = 284;
                definition.walkAnimation = 1660;
                definition.standAnimation = 11973;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65499; //HEAD
                definition.npcModels[1] = 55820; //JAW
                definition.npcModels[2] = 65501; //CHEST
                definition.npcModels[3] = 65505; //CAPE
                definition.npcModels[4] = 64082; //ARM
                definition.npcModels[5] = 65507; //HAND//shield
                definition.npcModels[6] = 65510; //WEP
                definition.npcModels[7] = 65503; //LEG
                definition.npcModels[8] = 65508; //BOOT
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.actions[1] = "Attack";
                definition.actions[2] = null;
                definition.actions[3] = null;
                definition.actions[4] = null;
                definition.rdc2 = 8239113;
                break;

            case 5002:
                definition.name = "Maxiblood @red@(Melee)";
                definition.combatLevel = 138;
                definition.walkAnimation = 1660;
                definition.standAnimation = 11973;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65131; //HEAD
                //definition.npcModels[1] = 246; //JAW
                definition.npcModels[2] = 65133; //CHEST
                definition.npcModels[3] = 9638; //CAPE
                definition.npcModels[4] = 65137; //HAND
                definition.npcModels[5] = 65128; ////shield
                definition.npcModels[6] = 65142; //WEP
                definition.npcModels[7] = 65135; //LEG
                definition.npcModels[8] = 65138; //BOOT
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.actions[1] = "Attack";
                definition.actions[2] = null;
                definition.actions[3] = null;
                definition.actions[4] = null;
                definition.originalModelColours = new int[1];
                definition.changedModelColours = new int[1];
                definition.originalModelColours[0] = 40; // current
                definition.changedModelColours[0] = 67; // New

                break;
            case 783:
                definition.rdc2 = 63664;
                definition.scaleXZ = 140;
                definition.scaleY = 140;
                definition.name = "<col=ff4f4f><shad=222>Starter Tasks<shad=-1>";
                break;
	/*	case 8013:
			definition.adjustVertextPointsXOrY = 90;
			definition.adjustVertextPointZ = 200;
			definition.name = "@bla@<shad=f9f6f6>Darkath Boss<shad=-1>";
			definition.combatLevel = 2043;
			definition.walkAnimation = 1660;
			definition.standAnimation = 11973;
			definition.npcModels = new int[9];
			definition.npcModels[0] = 65421; //HEAD
			//definition.npcModels[1] = 246; //JAW
			definition.npcModels[2] = 65423; //CHEST
		definition.npcModels[3] = 65039; //CAPE
			definition.npcModels[4] = 65199; //HAND
			definition.npcModels[5] = 65432; ////shield
			definition.npcModels[6] = 65202; //WEP
			definition.npcModels[7] = 65425; //LEG
			definition.npcModels[8] = 65200; //BOOT
			definition.actions = new String[5];
			definition.actions[0] = null;
			definition.actions[1] = "Attack";
			definition.actions[2] = null;
			definition.actions[3] = null;
			definition.actions[4] = null;
			 definition.originalModelColours = new int[1];
	            definition.changedModelColours = new int[1];
	            definition.originalModelColours[0] = 52; // current
	            definition.changedModelColours[0] = 66; // New
			break;*/
            case 8013:
                definition.name = "<shad=f9f6f6>Vote Boss<shad=-1>";
                MobDefinition votingbosser = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{130620};
                definition.combatLevel = votingbosser.combatLevel;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                //definition.adjustVertextPointsXOrY = darkath.adjustVertextPointsXOrY;
                //  definition.adjustVertextPointZ = darkath.adjustVertextPointZ;
                definition.actions = new String[]{null, "Attack", null, null, null};
                //definition.rdc2 = 21415;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                break;
            case 5000:
                definition.name = "Moonlight @cya@(Magic)";
                definition.combatLevel = 138;
                definition.walkAnimation = 1660;
                definition.standAnimation = 11973;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65297; //HEAD
                //definition.npcModels[1] = 246; //JAW
                definition.npcModels[2] = 65299; //CHEST
                definition.npcModels[3] = 9638; //CAPE
                definition.npcModels[4] = 65137; //HAND
                definition.npcModels[5] = 65227; ////shield
                definition.npcModels[6] = 65295; //WEP
                definition.npcModels[7] = 65301; //LEG
                definition.npcModels[8] = 65138; //BOOT
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.actions[1] = "Attack";
                definition.actions[2] = null;
                definition.actions[3] = null;
                definition.actions[4] = null;
                definition.originalModelColours = new int[3];
                definition.changedModelColours = new int[3];
                definition.originalModelColours[0] = 40; // current
                definition.changedModelColours[0] = 66; // New
                definition.originalModelColours[1] = 60; // current
                definition.changedModelColours[1] = 66; // New
                definition.originalModelColours[2] = 62; // current
                definition.changedModelColours[2] = 66; // New

                break;
            case 9172:
                definition.rdc2 = 9929;
                break;
            case 4999:
                definition.name = "Archie @gre@(Ranger)";
                definition.combatLevel = 138;
                definition.walkAnimation = 1660;
                definition.standAnimation = 11973;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65232; //HEAD
                //definition.npcModels[1] = 246; //JAW
                definition.npcModels[2] = 65234; //CHEST
                definition.npcModels[3] = 9638; //CAPE
                definition.npcModels[4] = 65137; //HAND
                //definition.npcModels[5] = 65227; ////shield
                definition.npcModels[6] = 65211; //WEP
                definition.npcModels[7] = 65236; //LEG
                definition.npcModels[8] = 65138; //BOOT
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.actions[1] = "Attack";
                definition.actions[2] = null;
                definition.actions[3] = null;
                definition.actions[4] = null;
                definition.originalModelColours = new int[2];
                definition.changedModelColours = new int[2];
                definition.originalModelColours[0] = 40; // current
                definition.changedModelColours[0] = 58; // New
                definition.originalModelColours[1] = 60; // current
                definition.changedModelColours[1] = 58; // New

                break;
            case 5040:
                definition.npcModels = new int[]{51848, 51850};
                definition.name = "dzone strykewyrm";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 130;
                definition.standAnimation = 12790;
                definition.walkAnimation = 12790;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 87654;
                break;
            case 2050:
                definition.name = "Ezone skeleton";
                definition.combatLevel = 1000;
                break;
            case 2051:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "Ezone creeper";

                definition.npcModels = new int[]{64537};
                definition.combatLevel = 1000;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 190;
                definition.scaleY = 190;
                definition.npcSizeInSquares = 2;

                break;
            case 600:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "Storm trooper";
                definition.npcModels = new int[]{65151};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 190;
                definition.scaleY = 190;

                break;
            case 601:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "Luke Skywalker";
                definition.npcModels = new int[]{65152};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                break;
            case 602:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "Force Priestesses";
                definition.npcModels = new int[]{65153};
                definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                //definition.npcSizeInSquares = 2;
                break;
            case 3037:
                definition.name = "Commander zilyana";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{28057, 28071, 28078, 28056};
                definition.combatLevel = 596;
                definition.standAnimation = 6963;
                definition.walkAnimation = 6962;

                definition.scaleXZ = 103;
                definition.scaleY = 103;
                definition.npcSizeInSquares = 2;
                break;
            case 4444:
                definition.name = "Hooker pet";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{36071};
                definition.combatLevel = 0;
                definition.standAnimation = 6963;
                definition.walkAnimation = 6962;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                break;
            case 3038:
                definition.name = "Dagannoth supreme";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{9941, 9943};
                definition.combatLevel = 303;
                definition.standAnimation = 2850;
                definition.walkAnimation = 2849;
                definition.scaleXZ = 105;
                definition.scaleY = 105;
                definition.npcSizeInSquares = 2;
                break;

            case 3039:
                definition.name = "Dagannoth prime"; //9940, 9943, 9942
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{9940, 9943, 9942};
                definition.originalModelColours = new int[]{11930, 27144, 16536, 16540};
                definition.changedModelColours = new int[]{5931, 1688, 21530, 21534};
                definition.combatLevel = 303;
                definition.standAnimation = 2850;
                definition.walkAnimation = 2849;
                definition.scaleXZ = 105;
                definition.scaleY = 105;
                definition.npcSizeInSquares = 2;
                break;

            case 3040:
                definition.name = "Dagannoth rex";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{9941};
                definition.originalModelColours = new int[]{16536, 16540, 27144, 2477};
                definition.changedModelColours = new int[]{7322, 7326, 10403, 2595};
                definition.combatLevel = 303;
                definition.standAnimation = 2850;
                definition.walkAnimation = 2849;
                definition.scaleXZ = 105;
                definition.scaleY = 105;
                definition.npcSizeInSquares = 2;
                break;
            case 3047:
                definition.name = "Frost dragon";
                definition.combatLevel = 166;
                definition.standAnimation = 13156;
                definition.walkAnimation = 13157;
                definition.walkingBackwardsAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.walkLeftAnimation = -1;
                //definition.type = 51;
                definition.degreesToTurn = 32;
                definition.npcModels = new int[]{56767, 55294};
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.scaleXZ = 72;
                definition.scaleY = 72;
                definition.npcSizeInSquares = 2;
                break;

            case 3048:
                definition.npcModels = new int[]{44733};
                definition.name = "Tormented demon";
                definition.combatLevel = 450;
                definition.standAnimation = 10921;
                definition.walkAnimation = 10920;
                definition.walkingBackwardsAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.walkLeftAnimation = -1;
                //	definition.type = 8349;
                definition.degreesToTurn = 32;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.npcSizeInSquares = 2;
                break;
            case 8349:
                definition.name = "Tormented Demon";
                break;
            case 3114:
                definition.npcModels = new int[]{44733};
                definition.name = "Corrupted  Tormented demon";
                definition.combatLevel = 450;
                definition.standAnimation = 10921;
                definition.walkAnimation = 10920;
                definition.walkingBackwardsAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.walkLeftAnimation = -1;
                //	definition.type = 8349;
                definition.degreesToTurn = 32;
                definition.actions = new String[5];
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 2;
                definition.rdc2 = 8876;
                break;
            case 3050:
                definition.npcModels = new int[]{24602, 24605, 24606};
                definition.name = "Kalphite queen";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 333;
                definition.standAnimation = 6236;
                definition.walkAnimation = 6236;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 2;
                break;

            case 3051:
                definition.npcModels = new int[]{46141};
                definition.name = "Slash bash";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 111;
                definition.standAnimation = 11460;
                definition.walkAnimation = 11461;
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                definition.npcSizeInSquares = 2;
                break;
            case 3052:
                definition.npcModels = new int[]{45412};
                definition.name = "Phoenix";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 235;
                definition.standAnimation = 11074;
                definition.walkAnimation = 11075;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 2;
                break;
            case 3053:
                definition.npcModels = new int[]{46058, 46057};
                definition.name = "Bandos avatar";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 299;
                definition.standAnimation = 11242;
                definition.walkAnimation = 11255;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 2;
                break;
            case 3054:
                definition.npcModels = new int[]{62717};
                definition.name = "Nex";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 565;
                definition.standAnimation = 6320;
                definition.walkAnimation = 6319;
                definition.scaleXZ = 95;
                definition.scaleY = 95;
                definition.npcSizeInSquares = 1;
                break;
            case 13453:
            case 13451:
            case 13452:
            case 13454:
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                //	definition.rdc2 = 546546546;
                break;
            case 13738:

                definition.actions = new String[]{"Talk-to", null, null, null, null};
                definition.name = "<img=101><col=C3C0B2>Upgrade Lady";
                break;
            case 12649:


                definition.name = "Shukarhazh";
                definition.scaleY = 60;
                definition.scaleXZ = 60;
                definition.npcSizeInSquares = 1;
                break;
            case 10103:

                definition.npcSizeInSquares = 2;
                definition.name = "Bulwark";
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                //definition.rdc2 = 60;
                break;
            case 58:
                definition.name = "Super Mini Zulrah";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14409;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.combatLevel = 725;
                definition.scaleY = 50;
                definition.scaleXZ = 50;
                definition.rdc2 = 34563;
                definition.drawYellowDotOnMap = true;
                break;

            case 13447://325325325 gold dark purp
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                //	definition.rdc2 = 546546546;
                definition.name = "<col=ff6f6f>Supreme Nex";
                definition.npcModels = new int[]{65353};
                break;
            case 3055:
                definition.npcModels = new int[]{51852, 51853};
                definition.name = "Jungle strykewyrm";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 110;
                definition.standAnimation = 12790;
                definition.walkAnimation = 12790;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.npcSizeInSquares = 1;
                break;
            case 3056:
                definition.npcModels = new int[]{51848, 51850};
                definition.name = "Desert strykewyrm";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 130;
                definition.standAnimation = 12790;
                definition.walkAnimation = 12790;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.npcSizeInSquares = 1;
                break;
            case 3057:
                definition.npcModels = new int[]{51847, 51849};
                definition.name = "Ice strykewyrm";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 210;
                definition.standAnimation = 12790;
                definition.walkAnimation = 12790;
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                definition.npcSizeInSquares = 1;
                break;
            case 3058:
                definition.npcModels = new int[]{49142, 49144};
                definition.name = "Green dragon";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 79;
                definition.standAnimation = 12248;
                definition.walkAnimation = 12246;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 2;
                break;
            case 3059:
                definition.npcModels = new int[]{57937};
                definition.name = "Baby blue dragon";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 48;
                definition.standAnimation = 14267;
                definition.walkAnimation = 14268;
                definition.scaleXZ = 85;
                definition.scaleY = 85;
                definition.npcSizeInSquares = 1;
                break;
            case 53:
                definition.name = "Joyx Dragon";
                definition.rdc2 = 23945;
                definition.scaleXZ = 99;
                //definition.npcSizeInSquares = 4;
                definition.npcSizeInSquares = 2;
                definition.scaleY = 99;
                break;
            case 3060:
                definition.npcModels = new int[]{49137, 49144};
                definition.name = "Blue dragon";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 111;
                definition.standAnimation = 12248;
                definition.walkAnimation = 12246;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 2;
                break;
            case 3061:
                definition.npcModels = new int[]{14294, 49144};
                definition.name = "Black dragon";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 227;
                definition.standAnimation = 12248;
                definition.walkAnimation = 12246;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 2;
                break;
            case 3062:
                definition.npcModels = new int[]{63604, 63606};
                definition.name = "WildyWyrm Jr";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 382;
                definition.standAnimation = 12790;
                definition.walkAnimation = 12790;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 2;
                break;
            case 3334:
                definition.rdc2 = 92581;//this is how its recoloured.
                definition.name = "Deadly WildyWyrm";
                break;
            // boss pets
            case 6302:
                definition.npcModels = new int[]{26262};
                definition.name = "Skeleton hellhound";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 97;
                definition.standAnimation = 6580;
                definition.walkAnimation = 6577;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Prayer skill.".getBytes();
                break;
            case 6303:
                definition.npcModels = new int[]{10633, 10640, 10637, 10638, 10632};
                definition.name = "Maze Guardian";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.standAnimation = 3033;
                definition.walkAnimation = 3034;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Runecrafting skill.".getBytes();
                break;
            case 6304:
                definition.npcModels = new int[]{26631, 26636, 26641, 23932, 26624, 11788};
                definition.name = "Skeleton Warlord";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 97;
                definition.standAnimation = 2065;
                definition.walkAnimation = 2064;
                definition.scaleXZ = 85;
                definition.scaleY = 85;
                definition.npcSizeInSquares = 1;
                break;
            case 6305:
                definition.npcModels = new int[]{21547};
                definition.name = "Penguin";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 2;
                definition.standAnimation = 5668;
                definition.walkAnimation = 5666;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Agility skill.".getBytes();
                break;
            case 6306:
                definition.npcModels = new int[]{217, 181, 250, 292, 170, 176, 260};
                definition.name = "Druid";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.originalModelColours = new int[3];
                definition.originalModelColours[0] = 25238;
                definition.originalModelColours[1] = 8741;
                definition.originalModelColours[2] = 6798;
                definition.changedModelColours = new int[3];
                definition.changedModelColours[0] = 127;
                definition.changedModelColours[1] = 127;
                definition.changedModelColours[2] = 127;
                definition.description = "A skilling pet from the Herblore skill.".getBytes();
                break;
            case 6307:
                definition.npcModels = new int[]{4821, 4828, 4833};
                definition.name = "Monkey guard";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 149;
                definition.standAnimation = 1386;
                definition.walkAnimation = 1380;
                definition.scaleXZ = 125;
                definition.scaleY = 125;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Thieving skill.".getBytes();
                break;
            case 6308:
                definition.npcModels = new int[]{14104, 14103};
                definition.name = "Clockwork cat";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.standAnimation = 317;
                definition.walkAnimation = 314;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Crafting skill.".getBytes();
                break;
            case 6309:
                definition.npcModels = new int[]{26863};
                definition.name = "Jubbly bird";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 9;
                definition.standAnimation = 6803;
                definition.walkAnimation = 6804;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Fletching skill.".getBytes();
                break;
            case 6310:
                definition.npcModels = new int[]{5076, 5077};
                definition.name = "Dust devil";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 122;
                definition.standAnimation = 1556;
                definition.walkAnimation = 1554;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Slayer skill.".getBytes();
                break;
            case 6312:
                definition.npcModels = new int[]{19371};
                definition.name = "Chinchompa";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 2;
                definition.standAnimation = 5182;
                definition.walkAnimation = 5181;
                definition.scaleXZ = 125;
                definition.scaleY = 125;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Hunter skill.".getBytes();
                break;
            case 6313:
                definition.npcModels = new int[]{6146, 6148, 6150};
                definition.name = "Clay Golem";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.standAnimation = 1913;
                definition.walkAnimation = 1912;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Mining skill.".getBytes();
                break;
            case 6314:
                definition.npcModels = new int[]{2970, 2982, 2977, 2983, 2985, 2989, 2993, 2991};
                definition.name = "Chaos Dwarf";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 97;
                definition.standAnimation = 101;
                definition.walkAnimation = 98;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Smithing skill.".getBytes();
                break;
            case 6315:
                definition.npcModels = new int[]{2848};
                definition.name = "Shark";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.standAnimation = 10;
                definition.walkAnimation = 10;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Fishing skill.".getBytes();
                break;
            case 6316:
                definition.npcModels = new int[]{24461, 24490, 24439, 24480, 24446, 24452, 24503};
                definition.name = "Goblin cook";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.standAnimation = 6181;
                definition.walkAnimation = 6180;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Cooking skill.".getBytes();
                break;
            case 6317:
                definition.npcModels = new int[]{335};
                definition.name = "Fire elemental";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 35;
                definition.standAnimation = 1027;
                definition.walkAnimation = 1028;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Firemaking skill.".getBytes();
                break;
            case 6318:
                definition.npcModels = new int[]{21150};
                definition.name = "Tree spirit";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 101;
                definition.standAnimation = 5530;
                definition.walkAnimation = 5531;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Woodcutting skill.".getBytes();
                break;
            case 6319:
                definition.npcModels = new int[]{10220};
                definition.name = "Leprechaun";//3021
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.standAnimation = 2904;
                definition.walkAnimation = 189;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Farming skill.".getBytes();
                break;
            case 6320:
                definition.name = "Night spider @bla@x1.5 EXP - X1.5 DMG";
                MobDefinition nightspider = MobDefinition.get(63);
                definition.description = "night spider.".getBytes();
                definition.npcModels = nightspider.npcModels;
                definition.combatLevel = nightspider.combatLevel;
                definition.standAnimation = nightspider.standAnimation;
                definition.walkAnimation = nightspider.walkAnimation;
                definition.degreesToTurn = 32;
                definition.scaleXZ = nightspider.scaleXZ;
                definition.scaleY = nightspider.scaleY;
                definition.rdc2 = 23523;
                definition.combatLevel = 144;
                definition.description = "A halloween pet gives bonus Exp and bonus dmg boost.".getBytes();
                break;
            case 6311:
                definition.npcModels = new int[]{5062};
                definition.name = "Abyssal demon";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 124;
                definition.standAnimation = 1536;
                definition.walkAnimation = 1534;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Slayer skill.".getBytes();
                break;
            case 6440:
                definition.npcModels = new int[]{65176};
                definition.name = "Ice titan";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 138;
                definition.standAnimation = 7949;
                definition.walkAnimation = 7952;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                definition.npcSizeInSquares = 1;
                definition.description = "A skilling pet from the Slayer skill.".getBytes();
                break;
            case 6322:
                definition.name = "Serpentine Spawn";
                definition.actions = new String[]{null, null, null, null, null};
                definition.npcModels = new int[1];
                definition.combatLevel = 53;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.scaleY = 25;
                definition.scaleXZ = 25;
                definition.npcSizeInSquares = 2;
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14408;
                break;
            case 6323:
                definition.name = "Tanzanite Spawn";
                definition.actions = new String[]{null, null, null, null, null};
                definition.npcModels = new int[1];
                definition.combatLevel = 52;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.scaleY = 25;
                definition.scaleXZ = 25;
                definition.npcSizeInSquares = 2;
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14407;
                break;
            case 6324:
                definition.name = "Magma Spawn";
                definition.actions = new String[]{null, null, null, null, null};
                definition.npcModels = new int[1];
                definition.combatLevel = 52;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.scaleY = 25;
                definition.scaleXZ = 25;
                definition.npcSizeInSquares = 2;
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14409;
                break;
            case 6830:
            case 6841:
            case 6796:
            case 7331:
            case 6831:
            case 7361:
            case 6847:
            case 6872:
            case 7353:
            case 6835:
            case 6845:
            case 6808:
            case 7370:
            case 7333:
            case 7351:
            case 7367:
            case 6853:
            case 6855:
            case 6857:
            case 6859:
            case 6861:
            case 6863:
            case 9481:
            case 6827:
            case 6889:
            case 6813:
            case 6817:
            case 7372:
            case 6839:
            case 8575:
            case 7345:
            case 6799:
            case 7335:
            case 7347:
            case 6800:
            case 9488:
            case 6804:
            case 6822:
            case 6849:
            case 7355:
            case 7357:
            case 7359:
            case 7341:
            case 7329:
            case 7339:
            case 7349:
            case 7375:
            case 7343:
            case 6820:
            case 6865:
            case 6809:
            case 7363:
            case 7337:
            case 7365:
            case 6991:
            case 6992:
            case 6869:
            case 6818:
            case 6843:
            case 6823:
            case 7377:
            case 6887:
            case 6885:
            case 6883:
            case 6881:
            case 6879:
            case 6877:
            case 6875:
            case 6833:
            case 6851:
            case 5079:
            case 5080:
            case 6824:
                definition.actions = new String[]{null, null, null, null, null};
                break;
            case 6806: // thorny snail
            case 6807:
            case 6994: // spirit kalphite
            case 6995:
            case 6867: // bull ant
            case 6868:
            case 6794: // spirit terrorbird
            case 6795:
            case 6815: // war tortoise
            case 6816:
            case 6874:// pack yak
            case 3594: // yak
            case 3590: // war tortoise
            case 3596: // terrorbird
                definition.actions = new String[]{"Store", null, null, null, null};
                break;
            case 12239:
                definition.name = "Golden Oozau";
                definition.combatLevel = 1087;
                definition.description = "That thing can't be mortal...".getBytes();
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.npcSizeInSquares = 3;
                definition.npcModels = new int[]{130717};
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 91:
                MobDefinition other = MobDefinition.get(6250);
                definition.name = "Leo the Lion";
                definition.combatLevel = 333;
                definition.description = "That thing can't be mortal...".getBytes();
                definition.walkAnimation = other.walkAnimation;
                definition.standAnimation = other.standAnimation;
                definition.npcSizeInSquares = 4;

                definition.npcModels = new int[]{64095};
                definition.rdc = 2;
                break;
            case 6250:
                MobDefinition other2 = MobDefinition.get(6250);
                definition.name = "Leo the Lion";
                definition.combatLevel = 333;
                definition.description = "That thing can't be mortal...".getBytes();
                definition.walkAnimation = other2.walkAnimation;
                definition.standAnimation = other2.standAnimation;

                definition.npcModels = new int[]{64095};

                break;
            case 548:
                MobDefinition shop2 = MobDefinition.get(3900);
                definition.npcModels = shop2.npcModels;
                definition.actions = new String[]{"Trade", null, null, null, null};
                break;

            case 3299:
            case 437:
                definition.actions = new String[]{"Trade", null, null, null, null};
                break;
            case 1265:
                MobDefinition other3 = MobDefinition.get(6250);
                definition.name = "Lunite Lion";
                definition.drawYellowDotOnMap = true;
                definition.walkAnimation = other3.walkAnimation;
                definition.standAnimation = other3.standAnimation;
                definition.npcModels = new int[]{64095};//64091
                break;
            case 1267:
                definition.drawYellowDotOnMap = true;
                definition.scaleY = 180;
                definition.scaleXZ = 180;
                definition.combatLevel = 26;
                break;
            case 1677:
                //	definition.rdc2 = 35323;
                break;
            case 1674:
                //definition.rdc2 = 18142;
                break;
            case 8459:
                definition.drawYellowDotOnMap = true;
                definition.actions = new String[]{"Shop", null, "Decant", null, null};
                break;
            case 961:
                definition.actions = new String[]{null, null, "Buy Consumables", "Restore Stats", "Buy Food"};
                definition.name = "Healer";
                break;
            case 705:
                MobDefinition shop3 = MobDefinition.get(14010);
                definition.npcModels = shop3.npcModels;

                definition.actions = new String[]{null, null, "Buy Armour", "Buy Weapons", "Buy Jewelries"};
                definition.name = "Warrior";
                break;
            case 1861:
                definition.actions = new String[]{null, null, "Buy Equipment", "Buy Ammunition", null};
                definition.name = "Archer";
                break;
            case 946:
                MobDefinition shop4 = MobDefinition.get(14013);
                definition.npcModels = shop4.npcModels;
                definition.actions = new String[]{null, null, "Buy Equipment", "Buy Runes", "Buy Teleports"};
                definition.name = "Mage";
                break;
            case 2253:
                definition.actions = new String[]{null, null, "Buy Skillcapes", "Buy Skillcapes (t)", "Buy Hoods"};
                break;
            case 2292:
                definition.actions = new String[]{"Trade", null, null, null, null};
                definition.name = "Merchant";
                break;
            case 2676:
                definition.actions = new String[]{"Makeover", null, null, null, null};
                break;

            case 494:
            case 1360:

                definition.actions = new String[]{"Talk-to", null, null, null, null};
                break;
            case 3089:
                definition.name = "Pvm Ticket Shop";
                definition.actions = new String[]{"Trade", null, null, null, null};
                break;
            //LOL
            case 3390:
                definition.name = "Prince Black Dragon";
                definition.scaleY = 30;
                definition.scaleXZ = 30;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = null;
                definition.npcModels = new int[4];
                definition.npcModels[0] = 17414;
                definition.npcModels[1] = 17415;
                definition.npcModels[2] = 17429;
                definition.npcModels[3] = 17422;
                definition.standAnimation = 90;
                definition.walkAnimation = 4635;
                definition.combatLevel = 0;
                definition.description = "A miniature King Black Dragon!".getBytes();
                definition.varBitChild = -1;
                break;
            case 3391:// stop before 5902
                definition.varBitChild = -1;
                definition.name = "Chaos Elemental Jr.";
                definition.scaleY = 30;
                definition.scaleXZ = 30;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = null;
                definition.npcModels = new int[1];
                definition.npcModels[0] = 11216;
                definition.standAnimation = 3144;
                definition.walkAnimation = 3145;
                definition.combatLevel = 0;
                definition.description = "A miniature Chaos Elemental!".getBytes();
                break;
            case 3392:// stop before 5902
                definition.varBitChild = -1;
                definition.name = "Baby Mole";
                definition.scaleY = 30;
                definition.scaleXZ = 30;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = null;
                definition.npcModels = new int[4];
                definition.npcModels[0] = 12076;
                definition.npcModels[1] = 12075;
                definition.npcModels[2] = 12074;
                definition.npcModels[3] = 12077;
                definition.standAnimation = 3309;
                definition.walkAnimation = 3313;
                definition.combatLevel = 0;
                definition.description = "A miniature Giant Mole!".getBytes();
                break;
            case 3393:// stop before 5902
                definition.varBitChild = -1;
                definition.name = "Baby Dagannoth Supreme";
                definition.scaleY = 40;
                definition.scaleXZ = 40;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = null;
                definition.npcModels = new int[2];
                definition.npcModels[0] = 9941;
                definition.npcModels[1] = 9943;
                definition.standAnimation = 2850;
                definition.walkAnimation = 2849;
                definition.combatLevel = 0;
                definition.description = "A miniature Dagannoth Supreme!".getBytes();
                break;

            case 3394:// stop before 5902
                definition.varBitChild = -1;
                definition.name = "Dagannoth Prime Jr.";
                definition.scaleY = 40;
                definition.scaleXZ = 40;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = null;
                definition.npcModels = new int[3];
                definition.npcModels[0] = 9940;
                definition.npcModels[1] = 9943;
                definition.npcModels[2] = 9942;
                definition.standAnimation = 2850;
                definition.walkAnimation = 2849;
                definition.combatLevel = 0;
                definition.description = "A miniature Dagannoth Prime!".getBytes();
                break;
            case 3395:// stop before 5902
                definition.varBitChild = -1;
                definition.name = "Baby Dagannoth Rex";
                definition.scaleY = 40;
                definition.scaleXZ = 40;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = null;
                definition.npcModels = new int[1];
                definition.npcModels[0] = 9941;
                definition.standAnimation = 2850;
                definition.walkAnimation = 2849;
                definition.combatLevel = 0;
                definition.description = "A miniature Dagannoth Rex!".getBytes();
                break;
            case 3396:
                definition.varBitChild = -1;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = null;
                definition.npcModels = new int[2];
                definition.npcModels[0] = 28003;
                definition.npcModels[1] = 28004;
                definition.scaleY = 25;
                definition.scaleXZ = 25;
                definition.standAnimation = 6972;
                definition.walkAnimation = 6973;
                definition.name = "Kree'arra Jr.";
                definition.combatLevel = 0;
                definition.description = "A mini Kree'arra!".getBytes();
                definition.npcSizeInSquares = 1;
                break;
            case 3397:// stop before 5902
                definition.varBitChild = -1;
                definition.name = "General Graardor Jr.";
                definition.scaleY = 30;
                definition.scaleXZ = 30;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = null;
                definition.npcModels = new int[2];
                definition.npcModels[0] = 27785;
                definition.npcModels[1] = 27789;
                definition.standAnimation = 7059;
                definition.walkAnimation = 7058;
                definition.combatLevel = 0;
                definition.description = "A miniature General Graardor!".getBytes();
                break;
            case 3688:
                definition.name = "Brandon Jr.";
                break;
            case 3398:// stop before 5902
                definition.varBitChild = -1;
                definition.name = "Penance Pet";
                definition.scaleY = 30;
                definition.scaleXZ = 30;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[5];
                //	definition.actions[0] = "Talk-to";
                definition.actions[2] = null;
                definition.npcModels = new int[8];
                definition.npcModels[0] = 20717;
                definition.npcModels[1] = 20715;
                definition.npcModels[2] = 20714;
                definition.npcModels[3] = 20709;
                definition.npcModels[4] = 20713;
                definition.npcModels[5] = 20712;
                definition.npcModels[6] = 20711;
                definition.npcModels[7] = 20710;
                definition.standAnimation = 5410;
                definition.walkAnimation = 5409;
                definition.combatLevel = 0;
                definition.description = "A miniature Penance Queen!".getBytes();
                break;
            case 3400:// stop before 5902
                definition.varBitChild = -1;
                definition.name = "Zilyana Jr.";
                definition.scaleY = 40;
                definition.scaleXZ = 40;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[5];
                definition.actions[0] = null;

                definition.npcModels = new int[4];
                definition.npcModels[0] = 28057;
                definition.npcModels[1] = 28071;
                definition.npcModels[2] = 28078;
                definition.npcModels[3] = 28056;
                definition.standAnimation = 6963;
                definition.walkAnimation = 6962;
                definition.combatLevel = 0;
                definition.description = "A miniature Commander Zilyana!".getBytes();
                break;
            //custom pets go here
            case 659:
                definition.scaleY = 180;
                definition.scaleXZ = 180;

                definition.actions = new String[]{"Open-Halloween Event", "Event-points", null, null, null};
                break;
            case 6750:
                definition.name = "Pet Insurance Agent";
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;
            case 6325:
                definition.name = "Superior Abyssal Demon";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{5062, 51602};
                definition.originalModelColours = new int[]{22439, /*4015*/};
                definition.changedModelColours = new int[]{947, /*528*/};
                definition.combatLevel = 124;
                definition.standAnimation = 1536;
                definition.walkAnimation = 1534;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.description = "A denizen of the Abyss!".getBytes();
                break;
            case 6335:
                definition.name = "Zamorakian Mage";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 84;
                definition.npcModels = new int[]{9981, 250, 25676, 23916, 176, 23934, 181,};
                definition.originalModelColours = new int[]{24, 16, 8, 920, 28, 12};
                definition.changedModelColours = new int[]{801, 65428, 788, 906, 119, 65428};
                definition.actions = new String[]{null, "attack", null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.walkingBackwardsAnimation = -1;
                definition.walkLeftAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.configChild = -1;
                definition.varBitChild = -1;
                definition.modelLightning = 0;
                definition.modelShadowing = 0;
                definition.drawYellowDotOnMap = true;
                definition.disableRightClick = true;
                definition.visibilityOrRendering = false;
                break;
            case 6334:
                definition.name = "Abyssal Titan";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 0;
                definition.npcModels = new int[]{30484};
                definition.actions = new String[]{null, null, null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 2;
                definition.standAnimation = 7690;
                definition.walkAnimation = 7687;
                definition.walkingBackwardsAnimation = -1;
                definition.walkLeftAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.configChild = -1;
                definition.varBitChild = -1;
                definition.modelLightning = 25;
                definition.modelShadowing = 0;
                definition.drawYellowDotOnMap = false;
                definition.disableRightClick = true;
                definition.visibilityOrRendering = false;
                break;
            case 7000:
                definition.name = "Moss Titan";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 0;
                definition.npcModels = new int[]{30742};
                definition.actions = new String[]{null, null, null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 2;
                definition.standAnimation = 7841;
                definition.walkAnimation = 7838;
                definition.walkingBackwardsAnimation = -1;
                definition.walkLeftAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.configChild = -1;
                definition.varBitChild = -1;
                definition.modelLightning = 25;
                definition.modelShadowing = 0;
                definition.drawYellowDotOnMap = false;
                definition.disableRightClick = true;
                definition.visibilityOrRendering = false;
                break;
            case 1597:
                definition.name = "Vannaka (Easy)";
                break;
            case 6332:
                definition.name = "Lava Titan";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 0;
                definition.npcModels = new int[]{30481};
                definition.actions = new String[]{null, null, null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 2;
                definition.standAnimation = 7978;
                definition.walkAnimation = 7977;
                definition.walkingBackwardsAnimation = -1;
                definition.walkLeftAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.configChild = -1;
                definition.varBitChild = -1;
                definition.modelLightning = 30;
                definition.modelShadowing = 150;
                definition.drawYellowDotOnMap = false;
                definition.disableRightClick = true;
                definition.visibilityOrRendering = false;
                break;

            case 6331:
                definition.name = "Ice Titan";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 0;
                definition.npcModels = new int[]{30470};
                definition.actions = new String[]{null, null, null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 2;
                definition.standAnimation = 8186;
                definition.walkAnimation = 7847;
                definition.walkingBackwardsAnimation = -1;
                definition.walkLeftAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.configChild = -1;
                definition.varBitChild = -1;
                definition.modelLightning = 35;
                definition.modelShadowing = 0;
                definition.drawYellowDotOnMap = false;
                definition.disableRightClick = true;
                definition.visibilityOrRendering = false;
                break;
            case 6330:
                definition.name = "Giant Skeleton";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 303;
                definition.npcModels = new int[]{21168, 21179, 21160, 21157, 21182, 21188, 21202};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 5483;
                definition.walkAnimation = 5479;
                definition.walkingBackwardsAnimation = -1;
                definition.walkLeftAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.configChild = -1;
                definition.varBitChild = -1;
                definition.modelLightning = 0;
                definition.modelShadowing = 0;
                definition.drawYellowDotOnMap = true;
                definition.disableRightClick = true;
                definition.visibilityOrRendering = false;
                break;
            case 6329:
                definition.name = "Skeleton Brute";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 132;
                definition.npcModels = new int[]{26628, 26637, 26642, 23932, 26623};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.walkingBackwardsAnimation = -1;
                definition.walkLeftAnimation = -1;
                definition.walkRightAnimation = -1;
                definition.configChild = -1;
                definition.varBitChild = -1;
                definition.modelLightning = 0;
                definition.modelShadowing = 0;
                definition.drawYellowDotOnMap = true;
                definition.disableRightClick = true;
                definition.visibilityOrRendering = false;
                break;
        }
        
        return definition;
    }

    }