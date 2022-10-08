package org.necrotic.client.cache.definition;

import org.necrotic.Configuration;
import org.necrotic.client.Client;
import org.necrotic.client.FrameReader;
import org.necrotic.client.List;
import org.necrotic.client.cache.Archive;
import org.necrotic.client.io.ByteBuffer;
import org.necrotic.client.world.Model;

import java.util.Objects;

public final class MobDefinition {

    private static final int OSRS_NPCS_OFFSET = 20000;

    public int rdc = 0;
    public int rdc2 = 0;
    public int rdc3 = 0;
    private static MobDefinition[] cache;
    private static MobDefinition[] cacheOSRS;
    private static int cacheIndex;
    public static int cacheIndexOSRS;
    public static Client clientInstance;
    public static List mruNodes = new List(30);
    private static ByteBuffer buffer;
    private static ByteBuffer bufferOSRS;
    private static int[] streamIndices;
    private static int[] streamIndicesOSRS;


    public static int maximum = 9000;

    public static void applyTexturing1(Model model, int id) {
        switch (id) {
            case 3117:
              //  model.setTexture(12);
                break;
            case 1084:
                model.setTexture(52);
                break;
            case 1085:
                model.setTexture(30);
                break;
            case 1086:
                model.setTexture(51);
                break;
            //	case 1614:
            //model.setTexture(72);
            //	break;
        }
    }

    public static MobDefinition getOSRS(int id) {
        id -= OSRS_NPCS_OFFSET;
        for (int j = 0; j < 20; j++) {
            if (cacheOSRS[j].id == (long) id) {
                return cacheOSRS[j];
            }
        }
        cacheIndexOSRS = (cacheIndexOSRS + 1) % 20;

        MobDefinition npc = cacheOSRS[cacheIndexOSRS] = new MobDefinition();

        if (id >= streamIndicesOSRS.length) {
            return null;
        }

        bufferOSRS.position = streamIndicesOSRS[id];
        npc.id = OSRS_NPCS_OFFSET + id;
        npc.osrs = true;
        npc.rs3 = false;
        npc.readValues(bufferOSRS);
        if (npc.name != null && npc.name.contains("00ffff")) {
            npc.name = npc.name.replaceAll("<col=00ffff>", "@cya@").replaceAll("</col>", "");
        }

        switch (npc.id) {
            case 28025:
                npc.actions = new String[] {null, null, null, null, null};
                npc.name = "Leviathan Pet";
                break;
            case 28492:
                npc.actions = new String[] {null, null, null, null, null};
                npc.name = "Hydra Pet";
                break;
            case 28060:
                npc.name = "Leviathon";
                break;
            case 28609:
                npc.npcSizeInSquares = 2;
                npc.scaleXZ = 45;
                npc.scaleY = 45;
                break;
            case 28263:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.npcSizeInSquares = 3;
                npc.scaleXZ = 120;
                npc.scaleY = 120;
                break;
            case 27530:
                npc.npcSizeInSquares = 2;
                npc.scaleXZ = 75;
                npc.scaleY = 75;
                break;

        }
        return npc;
    }

        public static MobDefinition get(int id) {

        if (id >= OSRS_NPCS_OFFSET) {
            return getOSRS(id);
        }

        for (int j = 0; j < 20; j++)
            if (cache[j].id == (long) id)
                return cache[j];
        cacheIndex = (cacheIndex + 1) % 20;
        MobDefinition definition = cache[cacheIndex] = new MobDefinition();
        if (id >= streamIndices.length)
            return null;
        buffer.position = streamIndices[id];
        definition.id = id;
        definition.readValues(buffer);
        if (definition.name != null && definition.name.toLowerCase().contains("bank")) {
            if (definition.actions != null) {
                for (int l = 0; l < definition.actions.length; l++) {
                    if (definition.actions[l] != null && definition.actions[l].equalsIgnoreCase("Collect"))
                        definition.actions[l] = null;
                }
            }
        }
        MobDefinition humanCopy = MobDefinition.get(1);
        MobDefinition humanCopy1 = MobDefinition.get(2292);
        MobDefinition batCopy = MobDefinition.get(4972);

        MobDefinition turkey = MobDefinition.get(8501);
        MobDefinition snowmen = MobDefinition.get(14207);
        MobDefinition snowmen1 = MobDefinition.get(14208);
        MobDefinition snowmen2 = MobDefinition.get(14209);

        MobDefinition santa = MobDefinition.get(9400);

        MobDefinition prysm = MobDefinition.get(3313);
        MobDefinition dragon = MobDefinition.get(51);

       definition = MobDefinition2.newIDS(definition, id);


            switch (id) {
            case 9767:
                definition.name = "Rammernaut";
                definition.combatLevel = 0;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 180;
                definition.scaleY = 180;
                definition.originalModelColours = new int[] {64790, 64536, 64568, 64573, 8498, 8522, 64907, 5290, 5301, 64788, 64662, 7440, 64640, 64795, 64565, 64566, 20287, 64671, 5305, 64643, 64521, 5296, 64556, 64552, 64541, 64529, 64576, 64526, 5280, 64522, 5281, 64539, 64802, 64900, 64652, 64679, 64791, 64530, 64551, 64534, 64782, 64571, 5311, 64580, 64546, 64772, 64548, 64547, 64561, 64649, 64670, 64807, 64654, 64554, 64516, 64512, 64656, 64569, 8514, 64684, 64812, 64669, 64524, 64528, 64792, 5318, 64564, 64560, 64675, };
                definition.changedModelColours = new int[] {40895, 64536, 64568, 64573, 8498, 8522, 25, 5290, 5301, 40895, 40895, 7440, 64640, 36799, 64565, 64566, 20287, 38847, 5305, 64643, 64521, 5296, 64556, 64552, 64541, 64529, 64576, 64526, 5280, 64522, 5281, 64539, 40895, 64900, 40895, 40895, 38847, 12, 64551, 64534, 40895, 64571, 5311, 64580, 64546, 25, 64548, 64547, 64561, 40652, 39871, 38847, 25, 64554, 25, 64512, 38847, 64569, 8514, 40895, 38847, 39871, 64524, 25, 36799, 5318, 64564, 64560, 38847, };

                break;
                case 7134:
                definition.name = "Bork";
                definition.combatLevel = MobDefinition.get(95).combatLevel;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                break;
            case 6142:
            case 6143:
            case 6144:
            case 6145:
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 140;
                definition.scaleY = 140;
                break;
            case 3782:
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 180;
                definition.scaleY = 180;
                break;
            case 9870:
                definition.setDefault();
                definition.name = "Nechryael";
                definition.npcModels = new int[]{139890};
                definition.combatLevel = 0;
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                break;
            case 9871:
                definition.setDefault();
                definition.name = "Evil Santa Claus";
                definition.npcModels = santa.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =santa.standAnimation;
                definition.walkAnimation = santa.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 300;
                definition.scaleY = 300;

                definition.originalModelColours = new int[]{666, 655, 671, 675, 670, 673, 680, 660 };
                definition.changedModelColours = new int[]{4, 8, 12, 0, 11, 7, 16, 10};
                break;
            case 9872:
                definition.setDefault();
                definition.name = "Snow Ranger pet";
                definition.npcModels = snowmen.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  snowmen.standAnimation;
                definition.walkAnimation = snowmen.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;
            case 9873:
                definition.setDefault();
                definition.name = "Snow Warrior pet";
                definition.npcModels = snowmen1.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  snowmen1.standAnimation;
                definition.walkAnimation = snowmen1.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;
            case 9874:
                definition.setDefault();
                definition.name = "Snow Mage pet";
                definition.npcModels = snowmen2.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  snowmen2.standAnimation;
                definition.walkAnimation = snowmen2.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;
            case 9875:
                definition.setDefault();
                definition.name = "Santa pet";
                definition.npcModels = santa.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  santa.standAnimation;
                definition.walkAnimation = santa.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;
            case 9876:
                definition.setDefault();
                definition.name = "Evil Santa pet";
                definition.npcModels = santa.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  santa.standAnimation;
                definition.walkAnimation = santa.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                definition.originalModelColours = new int[]{666, 655, 671, 675, 670, 673, 680, 660 };
                definition.changedModelColours = new int[]{4, 8, 12, 0, 11, 7, 16, 10};
                break;
            case 9877:
                definition.setDefault();
                definition.name = "Santa Claus";
                definition.npcModels = santa.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =santa.standAnimation;
                definition.walkAnimation = santa.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 100;
                definition.scaleY = 110;
                break;
            case 9878:
                definition.setDefault();
                definition.name = "Evil Santa Claus";
                definition.npcModels = santa.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =santa.standAnimation;
                definition.walkAnimation = santa.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 100;
                definition.scaleY = 110;
                definition.originalModelColours = new int[]{666, 655, 671, 675, 670, 673, 680, 660 };
                definition.changedModelColours = new int[]{4, 8, 12, 0, 11, 7, 16, 10};
                break;


            case 9880:
                definition.setDefault();
                definition.name = "Lunite Guardian";
                definition.npcModels = new int[]{139932, 139934, 139936, 139938, 139939, 139941};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 400;
                definition.scaleY = 400;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                break;

            case 9881:
                definition.setDefault();
                definition.name = "Mystic Werewolf";
                definition.npcModels = new int[]{91000};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 280;
                definition.scaleY = 280;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 0;
                break;
            case 9882:
                definition.setDefault();
                definition.name = "Midnight Ork";
                definition.npcModels = new int[]{91002};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 225;
                definition.scaleY = 225;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 0;
                break;
            case 9883:
                definition.setDefault();
                definition.name = "Demon Spawn";
                definition.npcModels = new int[]{91003};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 225;
                definition.scaleY = 225;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 1;
                break;
            case 9884:
                definition.setDefault();
                definition.name = "Hellish Bull";
                definition.npcModels = new int[]{91001};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 225;
                definition.scaleY = 225;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 2;
                break;

            case 9885:
                definition.setDefault();
                definition.npcModels = new int[]{62717};
                definition.name = "Ice Nihil";
                definition.combatLevel = 0;
                definition.standAnimation = 6320;
                definition.walkAnimation = 6319;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.colorChange = new double[]{0.2, 0.5, 1.0};
                break;
            case 9886:
                definition.setDefault();
                definition.name = "Mystic Werewolf";
                definition.npcModels = new int[]{91000};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 280;
                definition.scaleY = 280;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 1;
                break;
            case 9887:
                definition.setDefault();
                definition.name = "Mystic Werewolf";
                definition.npcModels = new int[]{91000};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 280;
                definition.scaleY = 280;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 2;
                break;


            case 9888:
                definition.setDefault();
                definition.name = "Witcher";
                definition.npcModels = new int[]{139991};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 280;
                definition.scaleY = 280;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 0;
                break;
            case 9889:
                definition.setDefault();
                definition.name = "Witcher";
                definition.npcModels = new int[]{139992};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 280;
                definition.scaleY = 280;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 0;
                break;


            case 9890:
                definition.setDefault();
                definition.npcModels = prysm.npcModels;
                definition.standAnimation =  prysm.standAnimation;
                definition.walkAnimation = prysm.walkAnimation;
                definition.actions = prysm.actions;
                definition.combatLevel = 0;
                definition.name = "Prysm";
                definition.scaleXZ = 125;
                definition.scaleY = 125;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 35734;//892435
                break;
            case 9891:
                definition.setDefault();
                definition.npcModels = prysm.npcModels;
                definition.standAnimation =  prysm.standAnimation;
                definition.walkAnimation = prysm.walkAnimation;
                definition.actions = prysm.actions;
                definition.combatLevel = 0;
                definition.name = "Dark Prysm";
                definition.scaleXZ = 230;
                definition.scaleY = 230;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 892435;
                break;

            case 9892:
                definition.setDefault();
                definition.npcModels = new int[]{117171};
                definition.rs3 = true;
                definition.standAnimation =  12248;
                definition.walkAnimation = 12247;
                definition.combatLevel = 0;
                definition.name = "Dragonstone Dragon";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 9893:
                definition.setDefault();
                definition.npcModels = new int[]{117173};
                definition.rs3 = true;
                definition.standAnimation =  12248;
                definition.walkAnimation = 12247;
                definition.combatLevel = 0;
                definition.name = "Onyx Dragon";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 9894:
                definition.setDefault();
                definition.npcModels = new int[]{117172};
                definition.rs3 = true;
                definition.standAnimation =  12248;
                definition.walkAnimation = 12247;
                definition.combatLevel = 0;
                definition.name = "Hydrix Dragon";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 9895:
                definition.setDefault();
                definition.npcModels = new int[]{114160, 114162, 114161, 114159, 114158, 114156};
                definition.rs3 = true;
                definition.standAnimation = humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;;
                definition.combatLevel = 0;
                definition.name = "Hanto Warrior";
                definition.scaleXZ = 300;
                definition.scaleY = 300;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 9896:
                definition.setDefault();
                definition.name = "Witcher";
                definition.npcModels = new int[]{139995};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 280;
                definition.scaleY = 280;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 0;
                break;

            case 9897:
                definition.setDefault();
                definition.name = "Ember Golem";
                definition.npcModels = new int[]{139996};
                definition.combatLevel = 0;
                definition.standAnimation =humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.originalModelColours = new int[]{4025};
                definition.changedModelColours = new int[]{3917};
                definition.headIcon = 0;
                break;

            case 10120:
                definition.name = "Jinx";
                definition.combatLevel = 0;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                break;
            case 10121:
                definition.name = "Jinx pet";
                definition.npcModels = MobDefinition.get(10120).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(10120).standAnimation;
                definition.walkAnimation =  MobDefinition.get(10120).walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                break;



            case 9898:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{102573,102574 , 102575, 102576, 102577};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 0;
                definition.name = "Mutated Shark";
                definition.scaleXZ = 150;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 9899:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{102565,102566 , 102567, 102568, 102569};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 0;
                definition.name = "Shadow Shark";
                definition.scaleXZ = 300;
                definition.scaleY = 250;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;


            case 10000:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{116183, 116182};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 5000;
                definition.name = "Saradomin";
                definition.scaleXZ = 350;
                definition.scaleY = 350;
                definition.npcSizeInSquares = 4;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 10001:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{102173};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 5000;
                definition.name = "Zamorak";
                definition.scaleXZ = 350;
                definition.scaleY = 350;
                definition.npcSizeInSquares = 4;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;



            case 10002:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{116183, 116182};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 0;
                definition.name = "Saradomin pet";
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                break;
            case 10003:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{102173};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 0;
                definition.name = "Zamorak pet";
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                break;


            case 10004:
                definition.name = "Leprechaun pet";
                definition.setDefault();
                definition.npcModels = new int[]{98001};
                definition.combatLevel = humanCopy1.combatLevel;
                definition.standAnimation =  humanCopy1.standAnimation;
                definition.walkAnimation = humanCopy1.walkAnimation;
                definition.combatLevel = 0;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 75;
                definition.scaleY = 75;
                break;
            case 10006:
                definition.name = "Discord Moderator";
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{71636};
                definition.combatLevel = humanCopy1.combatLevel;
                definition.standAnimation =  humanCopy1.standAnimation;
                definition.walkAnimation = humanCopy1.walkAnimation;
                definition.combatLevel = 0;
                definition.actions = new String[] {"Open", null, "Trade", null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                break;

            case 10007:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{9352,8266 , 8264, 8160, 8159};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 0;
                definition.name = "Faceless Assassin";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 10008:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{82362,82415 , 82367, 82356, 82359};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 0;
                definition.name = "Lotus Warrior";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 10009:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{83295,83309 , 83298, 83285, 83289};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 0;
                definition.name = "Shadow Hunter";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 10010:
                definition.setDefault();
                definition.rs3 = true;
                definition.npcModels = new int[]{83295,83309 , 83298, 83285, 83289, 83314};
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 0;
                definition.name = "Assassin Master";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{"Talk-to", null, null, null, null};
                break;


            case 10011:
                definition.setDefault();
                definition.name = "Bork pet";
                definition.npcModels = MobDefinition.get(7134).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(7134).standAnimation;
                definition.walkAnimation =  MobDefinition.get(7134).walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 35;
                definition.scaleY = 35;
                break;
            case 10012:
                definition.setDefault();
                definition.name = "Ember Golem pet";
                definition.npcModels = MobDefinition.get(9897).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(9897).standAnimation;
                definition.walkAnimation =  MobDefinition.get(9897).walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                break;

            case 10013:
                definition.setDefault();
                definition.npcModels = MobDefinition.get(13651).npcModels;
                definition.standAnimation =  11502;
                definition.walkAnimation = 11504;
                definition.actions = humanCopy.actions;
                definition.combatLevel = 0;
                definition.name = "Easter bunny pet";
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 110;
                definition.scaleY = 110;
                break;

            case 10014:
                definition.name = "Tanzanite";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 5;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 170;
                definition.scaleY = 170;
                definition.npcModels = new int[1];
                definition.npcModels[0] = 140011;
                definition.drawYellowDotOnMap = true;
                definition.originalModelColours = new int[]{40};
                definition.changedModelColours = new int[]{55};
                definition.headIcon = -1;
                break;



            case 10015:
                definition.setDefault();
                definition.npcModels = new int[]{94829};
                definition.rs3 = true;
                definition.standAnimation =  12248;
                definition.walkAnimation = 12247;
                definition.combatLevel = 0;
                definition.name = "Celestial Dragon";
                definition.scaleXZ = 115;
                definition.scaleY = 115;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;


            case 10016:
                definition.setDefault();
                definition.npcModels = new int[]{ 111928, 111930, 111929, 111926, 111927,     101856, 65297,20537,  };
                definition.rs3 = true;
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.combatLevel = 0;
                definition.name = "Celestial Being";
                definition.scaleXZ = 160;
                definition.scaleY = 160;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{"Talk-to", null, null, null, null};


                definition.originalModelColours = new int[]
                        {9155, -23410, -24416, -23496, -24426, -24498, -23401, -23523, -24509, -25578, -24477, -23493, -24499, -24489, -23513, 6592,

                                -22502, -25537, 4518, -31165, 2477, 3480, -22446, -22506, 3478, 3609, -20426, -26537, -22460, 4507, -22491, -27556, 3630, 4524, 2580, -22465, -31157, -6063, 4517, 5525, -16351, -30626, 4510, -23438, -24507, 5551, -26542, 5698, 4525, -31649, -31035, -24504, 4504, 5701, -22488, 2734, -22450, -7134, 127, -20414, -21170, -19398, 5524, 3471, -22457, -7120, 3477, 2619, -23473, -20421, 4520, -24488, -22449, 3475, 2730, 5566, 2604, 5702, -29607, 5528, 3482, -29877, -25515, -24503, 5530, -29599, -22481, -23482, 5703, 3474, -31283, -17360, 4508, -24490, 4629, -22451, 4501, -19379, 3472, 3476, -21470, 3615, -27431, -22445, 3486, -23485, -21430, 2467, -23475, 4503, -26541, 5553, 3483, -6082, -31285, 3488, 3613, -24498, -26530, -31289, -26554, -23463, -21276, -30625, -31411, -16288, 4506, -28582, -22486, -28580, -22459, -15262, 3485, -7102, -22438, -29597, -22441, -24502, 3622, 3602, 4502, 5704, 2461, 4509, 5565, -19411, 4505, -26527, -19365, 4499, -30642, -31149, 5554, -22448, -30623, -28575, 2746, 3484, 3612, 5835, 5562, -30634, -20399, -25521, -22463, -22436,

                                -322, -2534, 961, -336, -2544, 5441, 5708, -61, 762, -2513, -911, -350, -2518, 20287, -2542, 5706, -2522, -2525, 5683, 5437, -897, -2527, 954, 383, 5714, -2244, 5694, 5759, 5669, -2541, 5463, 5481, -2538, -2537, 767, 945,
                        };
                definition.changedModelColours = new int[]
                        {46003, -23410, -24416, 36799, -24426, 34644, -23401, -23523, 40895, -25578, 36591, 34662, 35775, 34653, -23513, 46003,

                                                                40895, -25537, 40895, -31165, 45759, 46003, -22446, 45759, 46003, 45759, -20426, -26537, -22460, 46003, -22491, -27556, 46003, 46003, 46003, -22465, -31157, -6063, 46003, 46003, 46003, -30626, 46003, -23438, -24507, 46003, -26542, 46003, 46003, -31649, -31035, -24504, 46003, 46003, -22488, 45759, -22450, -7134, 127, -20414, -21170, -19398, 45759, 45759, -22457, -7120, 45759, 46003, -23473, -20421, 46003, -24488, -22449, 45759, 46003, 46003, 45759, 46003, -29607, 45759, 46003, -29877, -25515, -24503, 46003, -29599, -22481, -23482, 45759, 46003, -31283, -17360, 46003, -24490, 46003, -22451, 45759, -19379, 46003, 46003, -21470, 46003, -27431, -22445, 46003, -23485, -21430, 45759, -23475, 4503, -26541, 46003, 45759, -6082, -31285, 45759, 46003, -24498, -26530, -31289, -26554, -23463, -21276, -30625, -31411, -16288, 46003, -28582, -22486, -28580, -22459, -15262, 46003, -7102, -22438, -29597, -22441, -24502, 46003, 45644, 46003, 45759, 46003, 45759, 46003, -19411, 45759, -26527, -19365, 45759, -30642, -31149, 46003, -22448, -30623, -28575, 45759, 46003, 45759, 46003, 45759, -30634, -20399, -25521, -22463, -22436,

                                33624, 46003, 46003, 36554, 46003, 40895, 46003, 127, 127, 36692, 46003, 36550, 46003, 127, 46003, 46003, 127, 36683, 46003, 40895, 127, 33464, 46003, 46003, 46003, 46003, 127, 76, 76, 40895, 127, 127, 35677, 40895, 127, 127,
                        };


                break;

            case 10017:
                definition.setDefault();
                definition.npcModels = new int[]{163, 254, 292, 320, 230, 247, 176, 181, };
                definition.originalModelColours = new int[]{25238, 8741, 9104, 4626,  };
                definition.changedModelColours = new int[]{0, 0, 20, 0,  };
                definition.rs3 = true;
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.combatLevel = 0;
                definition.name = "Travelling Merchant";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{"Trade",null,  "Items", null, null};
                break;


            case 10018:
                definition.setDefault();
                definition.npcModels = new int[]{72090};
                definition.rs3 = true;
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.combatLevel = 0;
                definition.name = "Items Exchange";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{"Trade",null,  null, null, null};
                break;

            case 10019:
                definition.setDefault();
                definition.npcModels = new int[]{ 84674, 84685, 84686, };
                definition.rs3 = true;
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.combatLevel = 0;
                definition.name = "Armadylean myrmidon";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.headIcon = 0;
                break;

            case 10020:
                definition.setDefault();
                definition.npcModels = new int[]{ 84675, 84681,  };
                definition.rs3 = true;
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.combatLevel = 0;
                definition.name = "Zamorakian bandit";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.headIcon = 1;
                break;

            case 10021:
                definition.setDefault();
                definition.npcModels = new int[]{ 84673, 84683,  };
                definition.rs3 = true;
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.combatLevel = 0;
                definition.name = "Saradominist thaumaturge";
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.headIcon = 2;
                break;



            case 10022:
                definition.setDefault();
                definition.npcModels = new int[]{96507, 44923, 251, 71836, 176, 72117, 185};

                definition.originalModelColours = new int[] {16311, 16323, 16335, 16347, -10304, 8741, 6798, };
                definition.changedModelColours = new int[] {-21612, -21600, -21588, -21576, -22407, -22407, -22407, };
                definition.rs3 = true;
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.combatLevel = 0;
                definition.name = "Uncle Sam";
                definition.scaleXZ = 300;
                definition.scaleY = 300;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;


            case 10023:
                definition.name = "Muspah";
                definition.npcModels = MobDefinition.get(7455).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(7455).standAnimation;
                definition.walkAnimation =  MobDefinition.get(7455).walkAnimation;
                definition.combatLevel = 0;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                break;
            case 10024:
                definition.name = "Muspah pet";
                definition.npcModels = MobDefinition.get(7455).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(7455).standAnimation;
                definition.walkAnimation =  MobDefinition.get(7455).walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                break;


            case 10025:
                definition.setDefault();
                definition.rs3 = true;
                definition.name = "Uncle Sam pet";
                definition.npcModels = MobDefinition.get(10022).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(10022).standAnimation;
                definition.walkAnimation =  MobDefinition.get(10022).walkAnimation;
                definition.changedModelColours =   MobDefinition.get(10022).changedModelColours;
                definition.originalModelColours =  MobDefinition.get(10022).originalModelColours;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                break;


            case 10026:
                definition.name = "Moon Shade";
                definition.npcModels = MobDefinition.get(1241).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(1241).standAnimation;
                definition.walkAnimation =  MobDefinition.get(1241).walkAnimation;
                definition.combatLevel = 0;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                break;

            case 10027:
                definition.name = "Twisted Spirit";
                definition.npcModels = MobDefinition.get(1540).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(1540).standAnimation;
                definition.walkAnimation =  MobDefinition.get(1540).walkAnimation;
                definition.combatLevel = 0;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                break;
            case 10028:
                definition.name = "Twisted Spirit pet";
                definition.npcModels = MobDefinition.get(1540).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(1540).standAnimation;
                definition.walkAnimation =  MobDefinition.get(1540).walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 65;
                definition.scaleY = 65;
                break;

            case 10029:
                definition.setDefault();
                definition.npcModels = new int[]{104389};
                definition.rs3 = true;
                definition.standAnimation =  12248;
                definition.walkAnimation = 12247;
                definition.combatLevel = 0;
                definition.name = "Rune Dragon";
                definition.scaleXZ = 115;
                definition.scaleY = 115;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 8168:
                definition.npcModels = new int[]{102851, };
                definition.rs3 = true;
                definition.standAnimation =  humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.combatLevel = 0;
                definition.actions = new String[]{"Talk-to",null,  null, null, null};
                break;

            case 11872:
                definition.name = "Taranis";
                definition.combatLevel = 0;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                break;
            case 11873:
                definition.name = "Taranis pet";
                definition.npcModels = MobDefinition.get(11872).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(11872).standAnimation;
                definition.walkAnimation =  MobDefinition.get(11872).walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 20;
                definition.scaleY = 20;
                break;
            case 11874:
                definition.name = "Thunder demon";
                definition.npcModels = MobDefinition.get(6208).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(6208).standAnimation;
                definition.walkAnimation =  MobDefinition.get(6208).walkAnimation;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = definition.scaleY = 55;
                definition.colorChange = new double[]{0.7, 0, 0.9};
                break;



            case 10030:
                definition.name = "Silverhawk";
                definition.npcModels = MobDefinition.get(6222).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(6222).standAnimation;
                definition.walkAnimation =  MobDefinition.get(6222).walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                break;
            case 10031:
                definition.name = "Silverhawk pet";
                definition.npcModels = MobDefinition.get(6222).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(6222).standAnimation;
                definition.walkAnimation =  MobDefinition.get(6222).walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 25;
                definition.scaleY = 25;
                break;
            case 10032:
                definition.name = "Avianse";
                definition.npcModels = MobDefinition.get(6246).npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =   MobDefinition.get(6246).standAnimation;
                definition.walkAnimation =  MobDefinition.get(6246).walkAnimation;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = definition.scaleY = 55;
                break;


            case 8504:
            case 8505:
            case 8506:
            case 8508:
            case 8510:
                definition.drawYellowDotOnMap = false;
                break;
            case 8499:
                definition.npcModels = turkey.npcModels;
                definition.standAnimation =  turkey.standAnimation;
                definition.walkAnimation = turkey.walkAnimation;
                definition.actions = turkey.actions;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 350;
                definition.scaleY = 350;
                definition.combatLevel = 0;
                break;
            case 8501:
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.combatLevel = 0;
                break;
            case 9861:
                definition.setDefault();
                definition.name = "Turkey pet";
                definition.npcModels = turkey.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  turkey.standAnimation;
                definition.walkAnimation = turkey.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;
            case 9862:
                definition.setDefault();
                definition.name = "Turkey pet (Mutated)";
                definition.npcModels = turkey.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  turkey.standAnimation;
                definition.walkAnimation = turkey.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.colorChange = new double[]{0.9, 0, 0};
                break;
            case 9863:
                definition.setDefault();
                definition.name = "Turkey pet (Mutated)";
                definition.npcModels = turkey.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  turkey.standAnimation;
                definition.walkAnimation = turkey.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.colorChange = new double[]{0, 0.8, 0};
                break;
            case 9864:
                definition.setDefault();
                definition.name = "Turkey pet (Mutated)";
                definition.npcModels = turkey.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  turkey.standAnimation;
                definition.walkAnimation = turkey.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.colorChange = new double[]{0, 0, 1.2};
                break;
            case 9865:
                definition.setDefault();
                definition.name = "Turkey pet (Mutated)";
                definition.npcModels = turkey.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  turkey.standAnimation;
                definition.walkAnimation = turkey.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.colorChange = new double[]{0.9, 0.5, 0};
                break;
            case 9866:
                definition.setDefault();
                definition.name = "Turkey pet (Mutated)";
                definition.npcModels = turkey.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  turkey.standAnimation;
                definition.walkAnimation = turkey.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.colorChange = new double[]{0.8, 0, 0.8};
                break;
            case 9867:
                definition.setDefault();
                definition.name = "Turkey pet (Mutated)";
                definition.npcModels = turkey.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  turkey.standAnimation;
                definition.walkAnimation = turkey.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.colorChange = new double[]{0.9, 0.9, 0};
                break;
            case 9868:
                definition.setDefault();
                definition.name = "Turkey pet (Mutated)";
                definition.npcModels = turkey.npcModels;
                definition.combatLevel = 0;
                definition.standAnimation =  turkey.standAnimation;
                definition.walkAnimation = turkey.walkAnimation;
                definition.actions = new String[] {null, null, null, null, null};
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.colorChange = new double[]{3, 3, 4};
                break;

            //HoV NPC's
            case 9810:
                definition.setDefault();
                definition.name = "Fenrir";
                definition.npcModels = new int[]{100198};
                definition.combatLevel = MobDefinition.get(95).combatLevel;
                definition.standAnimation = MobDefinition.get(95).standAnimation;
                definition.walkAnimation = MobDefinition.get(95).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 220;
                definition.scaleY = 220;
                break;
            case 9811:
                definition.setDefault();
                definition.name = "Fenrir";
                definition.npcModels = new int[]{100197};
                definition.combatLevel = MobDefinition.get(95).combatLevel;
                definition.standAnimation = MobDefinition.get(95).standAnimation;
                definition.walkAnimation = MobDefinition.get(95).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 220;
                definition.scaleY = 220;
                break;
            case 9812:
                definition.setDefault();
                definition.name = "Fenrir";
                definition.npcModels = new int[]{100196};
                definition.combatLevel = MobDefinition.get(95).combatLevel;
                definition.standAnimation = MobDefinition.get(95).standAnimation;
                definition.walkAnimation = MobDefinition.get(95).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 220;
                definition.scaleY = 220;
                definition.colorChange = new double[]{0.2,  0.2, 0.5};
                break;
            case 9813:
                definition.setDefault();
                definition.name = "Blood Odin";
                definition.npcModels = new int[]{100199};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 248;
                definition.scaleY = 248;
              //  definition.rdc3 = 900;
                definition.colorChange = new double[] {0.9,0.3,0.3};
                break;
            case 9814:
                definition.setDefault();
                definition.name = "Light Demon";
                definition.npcModels = new int[]{100281};
                definition.combatLevel = 200;
                definition.standAnimation = MobDefinition.get(1).standAnimation;
                definition.walkAnimation = MobDefinition.get(1).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                break;
            //HoV NPC End

            case 9830:
                definition.setDefault();
                definition.name = "Fenrir pet";
                definition.npcModels = new int[]{100196};
                definition.combatLevel = MobDefinition.get(95).combatLevel;
                definition.standAnimation = MobDefinition.get(95).standAnimation;
                definition.walkAnimation = MobDefinition.get(95).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 64;
                definition.scaleY = 64;
                break;
            case 9831:
                definition.setDefault();
                definition.name = "Green Fenrir pet";
                definition.npcModels = new int[]{100197};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(95).standAnimation;
                definition.walkAnimation = MobDefinition.get(95).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 64;
                definition.scaleY = 64;
                break;
            case 9832:
                definition.setDefault();
                definition.name = "Red Fenrir pet";
                definition.npcModels = new int[]{100198};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(95).standAnimation;
                definition.walkAnimation = MobDefinition.get(95).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 64;
                definition.scaleY = 64;
                break;
            case 9833:
                definition.setDefault();
                definition.name = "Blood Odin pet";
                definition.npcModels = new int[]{100199};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 64;
                definition.scaleY = 64;
                definition.colorChange =new double[] {0.9,0.3,0.3};
                break;
            case 9834:
                definition.setDefault();
                definition.name = "Heimdall pet";
                definition.npcModels = new int[]{100200};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 64;
                definition.scaleY = 64;
                break;


            case 9835:
                definition.setDefault();
                definition.name = "Demon of light";
                definition.npcModels = new int[]{100281};
                definition.combatLevel = 200;
                definition.standAnimation = MobDefinition.get(1).standAnimation;
                definition.walkAnimation = MobDefinition.get(1).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                break;
            case 9836:
                definition.setDefault();
                definition.name = "Bat of light";
                definition.npcModels = new int[]{100282};
                definition.combatLevel = 200;
                definition.standAnimation = 5022;
                definition.walkAnimation = 5022;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 32;
                definition.scaleY = 32;
                break;
            case 9837:
                definition.setDefault();
                definition.name = "Goblin of light";
                definition.npcModels = new int[]{100283};
                definition.combatLevel = 200;
                definition.standAnimation = MobDefinition.get(1).standAnimation;
                definition.walkAnimation = MobDefinition.get(1).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 32;
                definition.scaleY = 32;
                break;
            case 9838:
                definition.setDefault();
                definition.name = "Lava hound";
                definition.npcModels = new int[]{100306};
                definition.combatLevel = 200;
                definition.standAnimation = MobDefinition.get(49).standAnimation;
                definition.walkAnimation = MobDefinition.get(49).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 2;
                break;
            case 9839:
                definition.setDefault();
                definition.name = "Mutated hound";
                definition.npcModels = new int[]{100305};
                definition.combatLevel = 200;
                definition.standAnimation = MobDefinition.get(138).standAnimation;
                definition.walkAnimation = MobDefinition.get(138).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                break;

            //

            case 9840:
                definition.setDefault();
                definition.name = "Demon of light pet";
                definition.npcModels = new int[]{100281};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(1).standAnimation;
                definition.walkAnimation = MobDefinition.get(1).walkAnimation;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 32;
                definition.scaleY = 32;
                break;
            case 9841:
                definition.setDefault();
                definition.name = "Bat of light pet";
                definition.npcModels = new int[]{100282};
                definition.combatLevel = 0;
                definition.standAnimation = 5022;
                definition.walkAnimation = 5022;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 10;
                definition.scaleY = 10;
                break;
            case 9842:
                definition.setDefault();
                definition.name = "Goblin of light pet";
                definition.npcModels = new int[]{100283};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(1).standAnimation;
                definition.walkAnimation = MobDefinition.get(1).walkAnimation;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 32 / 4;
                definition.scaleY = 32 / 4;
                break;
            case 9843:
                definition.setDefault();
                definition.name = "Lava hound pet";
                definition.npcModels = new int[]{100306};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(49).standAnimation;
                definition.walkAnimation = MobDefinition.get(49).walkAnimation;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 45;
                definition.scaleY = 45;
                break;
            case 9844:
                definition.setDefault();
                definition.name = "Mutated hound pet";
                definition.npcModels = new int[]{100305};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(138).standAnimation;
                definition.walkAnimation = MobDefinition.get(138).walkAnimation;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 32;
                definition.scaleY = 32;
                break;


            case 9024:
                definition.setDefault();
                definition.name = "Kil'jaeden";
                definition.npcModels = new int[]{100077};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                break;
            case 9025:
                definition.setDefault();
                definition.name = "Skreeg";
                definition.npcModels = new int[]{100078};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 170;
                definition.scaleY = 170;
                definition.headIcon = 0;
                break;
            case 9026:
                definition.setDefault();
                definition.name = "Orix";
                definition.npcModels = new int[]{100079};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 170;
                definition.scaleY = 170;
                definition.headIcon = 1;
                break;
            case 9027:
                definition.setDefault();
                definition.name = "Crystal orc";
                definition.npcModels = new int[]{100080};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.headIcon = 2;
                break;

//
            case 9826:
                definition.setDefault();
                definition.name = "Kil'jaeden pet";
                definition.npcModels = new int[]{100077};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 32;
                definition.scaleY = 32;
                break;
            case 9827:
                definition.setDefault();
                definition.name = "Skreeg pet";
                definition.npcModels = new int[]{100078};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;
            case 9828:
                definition.setDefault();
                definition.name = "Orix pet";
                definition.npcModels = new int[]{100079};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;
            case 9829:
                definition.setDefault();
                definition.name = "Crystal orc pet";
                definition.npcModels = new int[]{100080};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;


            case 3709:
                definition.name = "Global Boss Store";
                definition.actions = new String[]{"Trade", null, null, null, null};
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.rdc2 = 123;
                break;
            case 648:
                definition.name = "High-Tier Exchange";
                definition.actions = new String[]{"Trade", null, null, null, null};
                break;
            //TH Mobs
            case 9815:
                definition.setDefault();
                definition.name = "Wicked Demon";
                definition.npcModels = new int[]{100237};
                definition.combatLevel = MobDefinition.get(82).combatLevel;
                definition.standAnimation = MobDefinition.get(82).standAnimation;
                definition.walkAnimation = MobDefinition.get(82).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 5;
                definition.scaleXZ = 256;
                definition.scaleY = 256;
                definition.headIcon = 0;
                break;
            case 9816:
                definition.setDefault();
                definition.name = "Evil Dragon";
                definition.npcModels = new int[]{100238};
                definition.combatLevel = MobDefinition.get(5022).combatLevel;
                definition.standAnimation = 5022;
                definition.walkAnimation = 5022;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 5;
                definition.scaleXZ = 96;
                definition.scaleY = 96;
                break;
            case 7743:
            case 9817:
                definition.setDefault();
                definition.name = "Obsidian Golem";
                definition.npcModels = new int[]{100239};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 5;
                definition.scaleXZ = 256;
                definition.scaleY = 256;
                definition.headIcon = 1;
                break;

            case 7742:
            case 9818:
                definition.setDefault();
                definition.name = "Red Rammus";
                definition.npcModels = new int[]{100240};
                definition.combatLevel = MobDefinition.get(2292).combatLevel;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 5;
                definition.scaleXZ = 256;
                definition.scaleY = 256;
                definition.headIcon = 2;
                break;
            //End of TH Mobs

            case 9819:
                definition.setDefault();
                definition.name = "Wicked Demon pet";
                definition.npcModels = new int[]{100237};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(82).standAnimation;
                definition.walkAnimation = MobDefinition.get(82).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 45;
                definition.scaleY = 45;
                break;
            case 9820:
                definition.setDefault();
                definition.name = "Evil Dragon pet";
                definition.npcModels = new int[]{100242};
                definition.combatLevel = 0;
                definition.standAnimation = 5022;
                definition.walkAnimation = 5022;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 64;
                definition.scaleY = 64;
                break;
            case 9821:
                definition.setDefault();
                definition.name = "Obsidian Golem pet";
                definition.npcModels = new int[]{100243};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 140;
                definition.scaleY = 140;
                break;
            case 9822:
                definition.setDefault();
                definition.name = "Red Rammus pet";
                definition.npcModels = new int[]{100244};
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(2292).standAnimation;
                definition.walkAnimation = MobDefinition.get(2292).walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 140;
                definition.scaleY = 140;
                break;

            case 9030:
                definition.name = "<col=eb6123>Spooky Bat";
                definition.npcModels = new int[] {130569};
                definition.combatLevel = 0;
                definition.standAnimation =  batCopy.standAnimation;
                definition.walkAnimation = batCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;
            case 9031:
                definition.name = "Spooky Kraken";
                definition.actions = new String[]{null, "Attack", null, null, null};
                MobDefinition eld = get(3847);
                definition.npcModels = new int[1];
                definition.npcModels[0] = 130562;
                definition.combatLevel = 291;
                definition.standAnimation = 3989;
                definition.walkAnimation = eld.walkAnimation;
                definition.scaleY = definition.scaleXZ = 140;
                definition.npcSizeInSquares = 5;
                break;
            case 9032:
                definition.name = "<col=eb6123>Spooky Bat pet";
                definition.npcModels = new int[] {130569};
                definition.combatLevel = 0;
                definition.standAnimation =  batCopy.standAnimation;
                definition.walkAnimation = batCopy.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 20;
                definition.scaleY = 20;
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;
            case 9033:
                definition.name = "Spooky Kraken";
                eld = get(3847);
                definition.npcModels = new int[1];
                definition.npcModels[0] = 130562;
                definition.combatLevel = 0;
                definition.standAnimation = 3989;
                definition.walkAnimation = 3989;
                definition.npcSizeInSquares = 1;
                definition.drawYellowDotOnMap = false;
                definition.scaleXZ = 20;
                definition.scaleY = 20;
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;

            case 9001:
                definition.name = "Phase [1]";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{130543};
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 10;
                definition.standAnimation = humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.drawYellowDotOnMap = true;
                break;
            case 9002:
                definition.name = "Phase [2]";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{130919};
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 50;
                definition.standAnimation = humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.scaleXZ = 115;
                definition.scaleY = 115;
                definition.drawYellowDotOnMap = true;
                break;
            case 9003:
                definition.name = "Phase [3]";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{130544};
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 100;
                definition.standAnimation = humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.scaleXZ = 110;
                definition.scaleY = 110;
                definition.drawYellowDotOnMap = true;
                break;
            case 9004:
                definition.name = "Phase [4]";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{130545};
                definition.npcSizeInSquares = 2;
                definition.combatLevel = 150;
                definition.standAnimation = humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.drawYellowDotOnMap = true;
                break;
            case 9005:
                definition.name = "Phase [5]";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{131443};
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 225;
                definition.standAnimation = humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.drawYellowDotOnMap = true;
                break;
            case 9006:
                definition.name = "Final Phase";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{131476};
                definition.npcSizeInSquares = 2;
                definition.combatLevel = 300;
                definition.standAnimation = humanCopy.standAnimation;
                definition.walkAnimation = humanCopy.walkAnimation;
                definition.scaleXZ = 165;
                definition.scaleY = 165;
                definition.drawYellowDotOnMap = true;
                break;
            case 12810:
                definition.npcSizeInSquares = 3;
                break;

            case 9000:
                definition.npcModels = get(12810).npcModels;
                definition.combatLevel = 0;
                definition.name = "<img=15>Boss Slayer Master";
                definition.actions = new String[]{"Talk-To", null, "Get-task", "Trade", "Cancel-task"};
                definition.standAnimation = get(12810).standAnimation;
                definition.walkAnimation = get(12810).walkAnimation;
                definition.npcSizeInSquares = 1;
                definition.scaleY = 64;
                definition.scaleXZ = 64;
                //definition.walkingBackwardsAnimation = 4;
                //definition.walkLeftAnimation = 4;
                //definition.walkRightAnimation = 4;
                break;

            case 1830:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.standAnimation = 12248;
                definition.walkAnimation = 12246;
                //definition.walkingBackwardsAnimation = 4;
                //definition.walkLeftAnimation = 4;
                //definition.walkRightAnimation = 4;
                break;
            case 770:
            case 771:
            case 769:
            case 6960:
            case 6958:
            case 6968:
            case 6964:
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;
            case 271:
                definition.name = "Scorpian pet";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.scaleXZ = 40;
                definition.scaleY = 40;
                definition.npcSizeInSquares = 1;
                definition.drawYellowDotOnMap = false;
                break;

            case 3712:
                definition.name = "Bully dragon";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{130486};
                definition.npcSizeInSquares = 2;
                definition.combatLevel = 0;
                definition.standAnimation = MobDefinition.get(4972).standAnimation;
                definition.walkAnimation = MobDefinition.get(4972).walkAnimation;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;

            case 504:
                definition.name = "Haven Beast (2)";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{65173};
                definition.npcSizeInSquares = 5;
                definition.combatLevel = 0;
                definition.standAnimation = 10056;
                definition.walkAnimation = 10055;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                break;

            case 688:
                definition.name = "Lunite Guard";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{131457};
                definition.npcSizeInSquares = 2;
                definition.combatLevel = 0;
                definition.standAnimation = 11973;
                definition.walkAnimation = 11975;
                break;

            case 125:
                definition.name = "Lunite Warrior";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{130920};
                definition.npcSizeInSquares = 2;
                definition.combatLevel = 0;
                definition.standAnimation = 11973;
                definition.walkAnimation = 11975;
                break;

            case 585:
                definition.name = "Raids Boss";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{131312};
                definition.npcSizeInSquares = 5;
                definition.combatLevel = 0;
                definition.standAnimation = 1338;
                definition.walkAnimation = 1339;
                break;

            case 587:
                definition.name = "Gemstone God";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{95170};
                definition.npcSizeInSquares = 5;
                definition.combatLevel = 0;
                definition.standAnimation = 1338;
                definition.walkAnimation = 1339;
                break;

            case 1880:
                definition.name = "@or2@YouTube Soldier";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{97666};
                definition.npcSizeInSquares = 5;
                definition.combatLevel = 0;
                definition.standAnimation = 11973;
                definition.walkAnimation = 11975;
                break;

            case 586:
                definition.name = "Dead Reaper";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{131468};
                definition.npcSizeInSquares = 2;
                definition.combatLevel = 0;
                definition.standAnimation = 11973;
                definition.walkAnimation = 11975;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                break;

            case 588:
                definition.name = "Lunite Minion";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[]{97666};
                definition.npcSizeInSquares = 2;
                definition.combatLevel = 0;
                definition.standAnimation = 11973;
                definition.walkAnimation = 11975;
                definition.scaleXZ = 40;
                definition.scaleY = 40;
                break;

            case 7553:
                definition.name = "Zemouregal";
                definition.combatLevel = 0;
                definition.walkAnimation = 11662;
                definition.standAnimation = 11667;
                definition.actions = new String[5];
                definition.actions[1] = "Attack";
                definition.npcModels = new int[1];
                definition.npcModels[0] = 46712;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 2;
                break;
            case 100:
                definition.rdc2 = 9999;
                break;
            case 406:
                definition.name = "General Champion";
                definition.description = "It's a General Khazard.".getBytes();
                definition.combatLevel = 112;
                definition.walkAnimation = 11662;
                definition.standAnimation = 11667;
                definition.actions = new String[5];

                definition.actions[1] = "Attack";
                definition.npcModels = new int[1];
                definition.npcModels[0] = 46712;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.npcSizeInSquares = 3;
                definition.rdc2 = 565;
                break;
            case 1120:
                definition.name = "Vasa Nistirio";
                definition.description = "Olm Dragon.".getBytes();
                definition.npcModels = new int[]{64328};
                definition.combatLevel = 333;
                definition.standAnimation = 303;
                definition.walkAnimation = 304;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 4;
                break;
            case 4466:
                definition.name = "@gre@USD";
                definition.npcModels = new int[]{96149};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 122;
                definition.walkAnimation = 11975;
                definition.standAnimation = 11973;
                definition.scaleXZ = 175;
                definition.scaleY = 175;
                break;
            case 4477:
                definition.name = "@whi@Ethereum";
                definition.npcModels = new int[]{94329};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 122;
                definition.walkAnimation = 11975;
                definition.standAnimation = 11973;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                break;
            case 4488:
                definition.name = "@yel@BTC";
                definition.npcModels = new int[]{95152};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 122;
                definition.walkAnimation = 11975;
                definition.standAnimation = 11973;
                definition.scaleXZ = 225;
                definition.scaleY = 225;
                break;
            case 4499:
                definition.name = "@yel@Crypto Final";
                definition.npcModels = new int[]{96019};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 122;
                definition.walkAnimation = 11975;
                definition.standAnimation = 11973;
                definition.scaleXZ = 250;
                definition.scaleY = 250;
                break;
            case 10005:
                definition.name = "@or2@Cryptos Minigame";
                definition.npcModels = new int[] {130179};
                definition.combatLevel = 0;
                definition.standAnimation = -1;
                definition.walkAnimation = -1;
                definition.actions = new String[5];
                definition.actions[0] = "Teleport";
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.degreesToTurn = 180;
                definition.npcSizeInSquares = 3;
                break;
            case 1121:
                definition.name = "Ice Demon";

                definition.npcModels = new int[]{64329};
                MobDefinition icedemon = get(82);
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.description = icedemon.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = icedemon.standAnimation;
                definition.walkAnimation = icedemon.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                break;
            case 1122:
                definition.name = "Revenant Hellhound";

                definition.npcModels = new int[]{64330};
                MobDefinition antwyv = get(49);
                definition.combatLevel = 40;
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                definition.npcSizeInSquares = 1;
                definition.description = antwyv.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = antwyv.standAnimation;
                definition.walkAnimation = antwyv.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                break;
            case 1123:
                definition.name = "Supreme Darkbeast";

                definition.npcModels = new int[]{64331};
                MobDefinition hoondon = get(2783);
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.description = hoondon.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = hoondon.standAnimation;
                definition.walkAnimation = hoondon.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                break;
            case 1124:
                definition.name = "Pestilent Bloat";

                definition.npcModels = new int[]{64332};
                MobDefinition justgaurd = get(7553);
                definition.combatLevel = 870;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.description = justgaurd.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = 502;
                definition.walkAnimation = 520;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                break;
            case 1125:
                definition.name = "TzHaar Ga'al";
                MobDefinition rek = get(2616);
                definition.npcModels = rek.npcModels;

                definition.combatLevel = 40;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.npcSizeInSquares = 1;
                definition.description = rek.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = rek.standAnimation;
                definition.walkAnimation = rek.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 9999;
                definition.npcSizeInSquares = 1;
                break;
            case 2017:
                definition.name = "OLM BOSS";
                definition.description = "Olm Dragon.".getBytes();
                definition.npcModels = new int[]{64327};
                definition.combatLevel = 83;
                definition.standAnimation = 12248;
                definition.walkAnimation = 12246;
                definition.scaleXZ = 210;
                definition.scaleY = 210;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                break;
            case 402:
                definition.name = "Olm adult";
                definition.description = "Olm Dragon.".getBytes();
                definition.npcModels = new int[]{64327};
                definition.combatLevel = 83;
                definition.standAnimation = 12248;
                definition.walkAnimation = 12246;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 9483;
                definition.npcSizeInSquares = 1;
                break;
            case 401:
                definition.name = "Olm teenager";
                definition.description = "Olm Dragon.".getBytes();
                definition.npcModels = new int[]{64327};
                definition.combatLevel = 83;
                definition.standAnimation = 12248;
                definition.walkAnimation = 12246;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 722;
                definition.npcSizeInSquares = 1;
                break;
            case 408:
                definition.name = "Olm baby";
                definition.description = "Olm Dragon.".getBytes();
                definition.npcModels = new int[]{64327};
                definition.combatLevel = 83;
                definition.standAnimation = 12248;
                definition.walkAnimation = 12246;
                definition.scaleXZ = 30;
                definition.scaleY = 30;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 11245;
                definition.npcSizeInSquares = 1;
                break;
            case 133:
                definition.name = "Black unicorn pet";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.npcSizeInSquares = 1;
                definition.drawYellowDotOnMap = false;

                break;
            case 105:
                definition.name = "Grizzly bear pet";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.npcSizeInSquares = 1;
                definition.drawYellowDotOnMap = false;

                break;
            case 4414:
                definition.name = "Wolf pet";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 1;
                definition.drawYellowDotOnMap = false;

                break;
            case 500:
                definition.npcModels = new int[]{64326};
                definition.scaleXZ = 250;
                definition.scaleY = 250;
                //definition.rdc2 = 56789876543;
                break;
            case 4972:
                definition.npcModels = new int[]{65332};
                definition.name = "Eternal Dragon";
                //definition.rdc2 = 2592952;
                definition.scaleXZ = 140;
                definition.scaleY = 140;
                definition.npcSizeInSquares = 6;

                break;
            case 4969:
                definition.npcModels = new int[]{65332};
                definition.name = "Eternal Dragon pet";
                definition.standAnimation = 5022;
                definition.walkAnimation = 5022;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.scaleXZ = 30;
                definition.scaleY = 30;
                //definition.rdc2 = 2592952;
                definition.npcSizeInSquares = 1;
                definition.drawYellowDotOnMap = false;

                break;
            case 3:
                definition.name = "<col=ff00fb>Dan's Present";
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[] {64098};
                definition.combatLevel = humanCopy1.combatLevel;
                definition.standAnimation =  humanCopy1.standAnimation;
                definition.walkAnimation = humanCopy1.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.rdc2 = 6233;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 30;
                definition.scaleY = 30;
                break;
            case 9010:
                definition.name = "<col=ff00fb>Valentine's Box";
                MobDefinition asdasd = MobDefinition.get(2292);
                definition.npcModels = new int[] {98000};
                definition.combatLevel = asdasd.combatLevel;
                definition.standAnimation =  asdasd.standAnimation;
                definition.walkAnimation = asdasd.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 5;
                definition.scaleXZ = 400;
                definition.scaleY = 400;
                break;
            case 9019:
                definition.name = "<col=ff00fb>St. Patrick Leprechaun";
                definition.npcModels = new int[] {98001};
                definition.combatLevel = humanCopy1.combatLevel;
                definition.standAnimation =  humanCopy1.standAnimation;
                definition.walkAnimation = humanCopy1.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                break;
            case 9020:
                definition.name = "<col=ff00fb>Armoured Bunny";
                definition.npcModels = new int[] {100050};
                definition.combatLevel = humanCopy1.combatLevel;
                definition.standAnimation =  humanCopy1.standAnimation;
                definition.walkAnimation = humanCopy1.walkAnimation;
                definition.actions = new String[] {null, "Attack", null, null, null};
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                break;
            case 9021:
                definition.name = "<col=ff00fb>Armoured Bunny Pet";
                definition.npcModels = new int[] {100050};
                definition.combatLevel = humanCopy1.combatLevel;
                definition.standAnimation =  humanCopy1.standAnimation;
                definition.walkAnimation = humanCopy1.walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 32;
                definition.scaleY = 32;
                break;
            case 9022:
                definition.name = "<col=ff00fb>Server Perks";
                definition.npcModels = new int[] {100055};
                definition.combatLevel = 0;
                definition.standAnimation = -1;
                definition.walkAnimation = -1;
                definition.actions = new String[5];
                definition.actions[0] = "Open";
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.degreesToTurn = 180;
                definition.npcSizeInSquares = 3;
                break;

            case 9023:
                definition.name = "<col=ff00fb>Demon";
                definition.npcModels = new int[] {100076};
                definition.combatLevel = humanCopy1.combatLevel;
                definition.standAnimation =  humanCopy1.standAnimation;
                definition.walkAnimation = humanCopy1.walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                break;

            case 13651:
               definition.degreesToTurn = humanCopy1.degreesToTurn;
                definition.name = "Easter Store";
                definition.npcSizeInSquares = 2;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.standAnimation =  11502;
                definition.walkAnimation = 11504;
                break;
            case 1835:
                definition.name = "Sell Easter Tickets";
                break;
            case 9015:
                definition.name = "Group Ironman Manager";
                definition.combatLevel = humanCopy1.combatLevel;
                definition.standAnimation =  humanCopy1.standAnimation;
                definition.walkAnimation = humanCopy1.walkAnimation;
                definition.actions = new String[] {"Talk-to", null, null, null, null};
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65441; //HEAD
                definition.npcModels[1] = 0; //JAW
                definition.npcModels[2] = 65443; //BODY
                definition.npcModels[3] = 0; //CAPE
                definition.npcModels[4] = 179; //GLOVES
                definition.npcModels[5] = 0; ////shield
                definition.npcModels[6] = 0; //WEP
                definition.npcModels[7] = 65445; //LEG
                definition.npcModels[8] = 185; //BOOT
                definition.scaleXZ = 128;
                definition.scaleY = 128;
                definition.rdc2 = 91291;
                break;

            case 3001:
                definition.name = "Gorilla pet";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.scaleXZ = 60;
                definition.npcModels = new int[]{65154};
                //	definition.combatLevel = 785;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleY = 60;
                definition.npcSizeInSquares = 1;
                definition.drawYellowDotOnMap = false;

                break;
            case 3377:
                definition.name = "Yoshi pet";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.scaleXZ = 100;
                //	definition.npcModels = new int[] {65154};
                //	definition.combatLevel = 785;
                //definition.standAnimation = 808;
                //definition.walkAnimation = 819;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.rdc2 = 7923;//blak 2822
                definition.drawYellowDotOnMap = false;

                break;
            case 3378:
                definition.name = "The Incredible Hulk";
                MobDefinition hulk2 = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64064};
                definition.combatLevel = hulk2.combatLevel;
                definition.standAnimation = hulk2.standAnimation;
                definition.walkAnimation = hulk2.walkAnimation;
                definition.scaleXZ = hulk2.scaleXZ;
                definition.scaleY = hulk2.scaleY;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 1112;
                definition.drawYellowDotOnMap = false;
                break;
            case 8009:
                definition.name = "Fused Zamasu";
                definition.description = "Fused.".getBytes();
                definition.npcModels = new int[9];
                definition.npcModels[0] = 130709; //HEAD
                definition.npcModels[1] = 0; //JAW
                definition.npcModels[2] = 130711; //BODY
                definition.npcModels[3] = 0; //CAPE
                definition.npcModels[4] = 0; //GLOVES
                definition.npcModels[5] = 130715; ////shield
                definition.npcModels[6] = 0; //WEP
                definition.npcModels[7] = 130713; //LEG
                definition.npcModels[8] = 130716; //BOOT
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 250;
                definition.scaleY = 250;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 5001:
                definition.name = "Vegeta pet";
                definition.combatLevel = 0;

                definition.scaleXZ = 80;
                definition.scaleY = 80;
                definition.walkAnimation = 1660;
                definition.standAnimation = 11973;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65304; //HEAD
                definition.npcModels[1] = 246; //JAW
                definition.npcModels[2] = 65305; //CHEST
                //	definition.npcModels[3] = -1; //CAPE
                //definition.npcModels[4] = -1; //ARM
                definition.npcModels[5] = 65308; //HAND//shield
                //	definition.npcModels[6] = -1; //WEP
                definition.npcModels[7] = 65306; //LEG
                definition.npcModels[8] = 65307; //BOOT

                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = false;

                break;
            case 5382:
                definition.name = "Ultimate Ironman Dungeoneer";
                definition.description = "He's discovered a way for UIM to do Dungeoneering.".getBytes();
                definition.actions = new String[]{"Talk-to", null, "Quick-store", "Quick-retrieve", null, null, null};
                definition.combatLevel = 0;
                break;
            case 220:
                definition.name = "Guild Expert";
                definition.actions = new String[]{"Talk-to", null, "Options2", "Options3", "Options4", null, null};
                break;
            case 788:
                definition.name = "Lottery Expert";
                definition.actions = new String[]{"Talk-to", null, "Enter Lottery", "Check current pot", null, null, null};
                break;
            case 2067:
                definition.name = "@red@AFK fishing spot";
                definition.actions = new String[]{"Net", null, null, null, null, null, null};
                definition.rdc2 = 633;
                break;
            case 1050:
                definition.name = "Deep sea ghost";
                definition.actions = new String[]{"Teleport", null, null, null, null, null, null};
                break;
            case 5748:
                definition.name = "Deep sea Fishing spot";
                definition.actions = new String[]{"Harpoon", null, null, null, null, null, null};
                definition.rdc2 = 633;
                break;
            case 2577:
                definition.name = "Xxsk1ll3zxx";
                definition.description = "I hope that's the last time I ever type your name.".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                break;
            case 2643:
                definition.name = "Kids Ranq";
                definition.description = "Stupid noob uses a magic shortbow? Lol l2pk scrub.".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                definition.combatLevel = 103;
                break;
            case 1066:
                definition.name = "Ticket Exchange";
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[1] = "Exchange";
                definition.actions[2] = "Trade";
                definition.combatLevel = 0;
                definition.drawYellowDotOnMap = true;
                definition.rdc2 = 14449;
                definition.modelShadowing = 22;
                break;

            case 5210:
                definition.name = "La'Roy-Jahnkins";
                definition.description = "At least he's got turkey.".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                break;
            case 2589:
                definition.name = "An0nymous26";
                definition.description = "Browses 4chan.".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                break;
            case 2576:
                definition.name = "Snakeskin Guy";
                definition.description = "He was born at a very young age.".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                break;
            case 5604:
                definition.name = "Boss Points Store";
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = "Trade";
                break;
            case 736:
                definition.name = "Emily0_o";
                definition.description = "A `professional` grill gamer.".getBytes();
                definition.actions = new String[5];
                definition.actions[0] = "Ban";
                definition.actions[2] = "Follow on Twitch";
                definition.actions[3] = "Subscribe ($4.99/mo)";
                break;
            case 2578:
                definition.name = "Ima GuYiRL";
                definition.description = "Will get married for 1m lolzzzz.".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                break;
            case 2575:
                definition.name = "OrcRogueIRL";
                definition.description = "You can't see him, he's stealthed! ;)".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                break;
            case 433:
                definition.name = "iTry2tribryd";
                definition.description = "I bet was tri-bridding before it was cool.".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                break;
            case 432:
                definition.name = "Hendrix";
                definition.description = "Hendrix! OPEN THE DAMN DOOR!".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                break;
            case 5861:
                definition.name = "The Riftsplitter";
                break;
            case 436:
                definition.name = "Sir Away";
                definition.npcModels = new int[]{19926};
                MobDefinition worsk = get(202);
                definition.combatLevel = 0;
                definition.scaleXZ = 120;
                definition.scaleY = 120;
                definition.npcSizeInSquares = 1;
                definition.description = worsk.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = worsk.standAnimation;
                definition.walkAnimation = worsk.walkAnimation;
                definition.actions = new String[]{"Talk-to", null, null, null, null};
                definition.npcSizeInSquares = 1;
                break;
            case 438:
                definition.name = "Dark Supreme";
                definition.npcModels = new int[]{64427};
                MobDefinition lightguild = get(202);
                definition.combatLevel = 0;
                definition.scaleXZ = 250;
                definition.scaleY = 250;
                definition.description = lightguild.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = lightguild.standAnimation;
                definition.walkAnimation = lightguild.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 3;
                break;
            case 440:
                definition.name = "Supreme Boss";
                definition.npcModels = new int[]{64426};
                MobDefinition lightguild1 = get(202);
                definition.combatLevel = 333;
                definition.scaleXZ = 175;
                definition.scaleY = 175;
                definition.npcSizeInSquares = 2;
                definition.description = lightguild1.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = lightguild1.standAnimation;
                definition.walkAnimation = lightguild1.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 19:
                definition.walkAnimation = 7046;
                break;//35.196.75.24

            case 434:
                definition.name = "Elvemage";
                definition.description = "Farcasting n3rd? Rofl get a lyfe. 1v1".getBytes();
                definition.actions = new String[6];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Follow";
                definition.actions[3] = "Trade";
                break;
            case 6247:
                definition.npcModels = new int[]{28057, 28071, 28078, 28056};
                break;

            case 5529:
                definition.rdc2 = 3253;
                definition.walkAnimation = 5781;
                definition.standAnimation = 5785;
                definition.npcSizeInSquares = 2;
                break;

            case 6873:
                //definition.rdc2 = 8248;
                definition.walkAnimation = 5781;
                definition.standAnimation = 5785;
                definition.npcSizeInSquares = 2;
                if (id == 6873) { //Yak summon
                    definition.actions = new String[]{"Store", null, null, null, null};
                }
                break;
            case 13465:
                definition.combatLevel = 91;
                break;
            case 13469:
                definition.combatLevel = 128;
                break;
            case 13474:
                definition.combatLevel = 173;
                break;
            case 13478:
                definition.combatLevel = 224;
                break;
            case 13479:
                definition.combatLevel = 301;
                break;
            case 519:
                definition.actions = new String[]{"Talk-to", null, null, null, null, null, null};
                break;//for the coorinations
            case 2938:
                definition.name = "Daily Rewards";
                break;
            case 4601:
                definition.name = "Loyalty Store";
                definition.actions = new String[]{"Trade", null, null, null, null};

                break;
            case 605:
                definition.name = "<img=28>Vote Manager";
                definition.actions = new String[]{"Trade", null, null, null, null};
                definition.combatLevel = 0;
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
                definition.originalModelColours = new int[1];
                definition.changedModelColours = new int[1];
                definition.originalModelColours[0] = 40; // current
                definition.changedModelColours[0] = 58; // New
                break;
            case 3777:
                definition.name = "<img=19>Donator Store";

                definition.actions = new String[]{null, null, "@cya@View Donator Shop", null, "@red@View Donation Deals", null, null};
                definition.combatLevel = 0;
                definition.walkAnimation = 1660;
                definition.standAnimation = 5761;//5316
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65131; //HEAD
                definition.npcModels[1] = 64031; //JAW
                definition.npcModels[2] = 65133; //CHEST
                definition.npcModels[3] = 9638; //CAPE
                definition.npcModels[4] = 65137; //HAND
                definition.npcModels[5] = 65128; ////shield
                definition.npcModels[6] = 64488; //WEP
                definition.npcModels[7] = 65135; //LEG
                definition.npcModels[8] = 65138; //BOOT
                definition.originalModelColours = new int[1];
                definition.changedModelColours = new int[1];
                definition.originalModelColours[0] = 40; // current
                definition.changedModelColours[0] = 71; // New
                break;
            case 14:
                definition.name = "<col=fce38a>Billbag+ store";


                break;
            case 1837:
                definition.name = "Ancient Mage";
                definition.description = "Creepy... yet helpful. Trade your Trio Tokens to him.".getBytes();
                definition.combatLevel = 0;
                definition.actions = new String[]{"Talk-to", null, "Quick-tele to Trio", null, null, null, null};
                definition.npcModels = new int[5];
                definition.npcModels[0] = 62737;
                definition.npcModels[1] = 62745;
                definition.npcModels[2] = 62740;
                definition.npcModels[3] = 62735;
                definition.npcModels[4] = 62734;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                break;
            case 3580:
                definition.combatLevel = 80;
                break;
            case 286:
                definition.npcModels = new int[]{23905};
                definition.name = "Mutant KFC";
                definition.actions = new String[5];
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 666;
                definition.standAnimation = 5386;
                definition.walkAnimation = 5385;
                definition.scaleXZ = 600;
                definition.scaleY = 600;
                definition.npcSizeInSquares = 3;
                break;
            case 285:
                definition.npcModels = new int[]{23905};
                definition.name = "Chicken Nugget";
                definition.actions = new String[5];
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 80;
                definition.standAnimation = 5386;
                definition.walkAnimation = 5385;
                definition.npcSizeInSquares = 1;
                break;
            case 2891:
                definition.name = "Whirpool";
                definition.description = "You do not want to fall in there!".getBytes();
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 26699;
                definition.scaleY = 100;
                definition.scaleXZ = 100;
                definition.npcSizeInSquares = 5;
                ObjectDefinition whirpool2 = ObjectDefinition.forID(25275);
                definition.walkAnimation = whirpool2.animationID;
                definition.standAnimation = whirpool2.animationID;
                definition.combatLevel = 0;
                break;
            case 2895:
                definition.name = "Whirpool";
                definition.description = "You do not want to fall in there!".getBytes();
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 26699;
                definition.scaleY = 50;
                definition.scaleXZ = 50;
                definition.npcSizeInSquares = 4;
                ObjectDefinition whirpool = ObjectDefinition.forID(25275);
                definition.walkAnimation = whirpool.animationID;
                definition.standAnimation = whirpool.animationID;
                definition.combatLevel = 0;
                break;
            case 2900:
                definition.name = "Whirpool";
                definition.description = "You do not want to fall in there!".getBytes();
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 26699;
                definition.scaleY = 50;
                definition.scaleXZ = 50;
                definition.npcSizeInSquares = 4;
                ObjectDefinition whirpool1 = ObjectDefinition.forID(25275);
                definition.walkAnimation = whirpool1.animationID;
                definition.standAnimation = whirpool1.animationID;
                definition.combatLevel = 0;
                break;
            case 2902:
                definition.name = "Whirpool";
                definition.description = "You do not want to fall in there!".getBytes();
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 26699;
                definition.scaleY = 50;
                definition.scaleXZ = 50;
                definition.npcSizeInSquares = 4;
                ObjectDefinition whirpool11 = ObjectDefinition.forID(25275);
                definition.walkAnimation = whirpool11.animationID;
                definition.standAnimation = whirpool11.animationID;
                definition.combatLevel = 0;
                break;
            case 2903:
                definition.name = "Whirpool";
                definition.description = "You do not want to fall in there!".getBytes();
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 26699;
                definition.scaleY = 50;
                definition.scaleXZ = 50;
                definition.npcSizeInSquares = 4;
                ObjectDefinition whirpool111 = ObjectDefinition.forID(25275);
                definition.walkAnimation = whirpool111.animationID;
                definition.standAnimation = whirpool111.animationID;
                definition.combatLevel = 0;
                break;
            case 2127:
                definition.name = "Snakeling";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14407;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.combatLevel = 1;
                definition.scaleY = 30;
                definition.scaleXZ = 30;
                break;
            case 2128:
                definition.name = "Snakeling";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14408;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.combatLevel = 725;
                definition.scaleY = 30;
                definition.scaleXZ = 30;
                break;
            case 2129:
                definition.name = "Snakeling";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14409;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.combatLevel = 725;
                definition.scaleY = 30;
                definition.scaleXZ = 30;
                break;
            case 2042://regular
                definition.name = "Zulrah";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14408;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.combatLevel = 725;
                definition.scaleY = 100;
                definition.scaleXZ = 100;
                break;
            case 2043://melee
                definition.name = "Zulrah";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14409;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.combatLevel = 725;
                definition.scaleY = 100;
                definition.scaleXZ = 100;
                break;
            case 2044://mage
                definition.name = "Zulrah";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 14407;
                definition.standAnimation = 5070;
                definition.walkAnimation = 5070;
                definition.combatLevel = 725;
                definition.scaleY = 100;
                definition.scaleXZ = 100;
                break;
            case 2000:
                definition.npcModels = new int[2];
                definition.npcModels[0] = 28294;
                definition.npcModels[1] = 28295;
                definition.name = "Venenatis";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleY = 200;
                definition.scaleXZ = 200;
                MobDefinition ven = get(2035);
                definition.standAnimation = ven.standAnimation;
                definition.walkAnimation = ven.walkAnimation;
                definition.combatLevel = 464;
                definition.npcSizeInSquares = 5;
                break;
            case 2001:
                definition.npcModels = new int[1];
                definition.npcModels[0] = 28293;
                definition.name = "Scorpia";
                definition.actions = new String[]{null, "Attack", null, null, null};
                MobDefinition scor = get(107);
                definition.standAnimation = scor.standAnimation;
                definition.walkAnimation = scor.walkAnimation;
                definition.combatLevel = 464;
                definition.npcSizeInSquares = 3;
                break;
            case 109:
                definition.name = "Baby Scorpion";
                definition.combatLevel = 80;
                break;
            case 2006:
                definition.npcModels = new int[1];
                definition.npcModels[0] = 28299;
                definition.name = "Vet'ion";
                definition.actions = new String[]{null, "Attack", null, null, null};
                MobDefinition vet = get(90);
                definition.standAnimation = vet.standAnimation;
                definition.walkAnimation = vet.walkAnimation;
                definition.combatLevel = 464;
                break;
            case 2007:
                definition.name = "Kraken";
                definition.actions = new String[]{null, "Attack", null, null, null};
                eld = get(3847);
                definition.npcModels = new int[1];
                definition.npcModels[0] = 28231;
                definition.combatLevel = 291;
                definition.standAnimation = 3989;
                definition.walkAnimation = eld.walkAnimation;
                definition.scaleY = definition.scaleXZ = 140;
                definition.npcSizeInSquares = 5;
                break;
            case 2008:
                definition.npcModels = new int[1];
                definition.npcModels[0] = 28231;
                definition.name = "Cave kraken";
                definition.actions = new String[]{null, "Attack", null, null, null};
                MobDefinition cave = get(3847);
                definition.npcModels = new int[1];
                definition.npcModels[0] = 28233;
                definition.combatLevel = 127;
                definition.standAnimation = 3989;
                definition.walkAnimation = cave.walkAnimation;
                definition.scaleY = definition.scaleXZ = 42;
                break;
            case 2009:
                definition.name = "Callisto";
                definition.npcModels = new int[]{28298};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 470;
                MobDefinition callisto = get(1326);
                definition.standAnimation = callisto.standAnimation;
                definition.walkAnimation = callisto.walkAnimation;
                definition.actions = callisto.actions;
                definition.scaleY = 110;
                definition.scaleXZ = 100;
                definition.npcSizeInSquares = 4;
                break;

            case 2010:
                definition.name = "Bellator";
                definition.description = "Still dedicated to Adam".getBytes();
                definition.combatLevel = 440;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[7];
                definition.npcModels[0] = 62738; //torvahelm
                definition.npcModels[1] = 62746; //torvaplate
                definition.npcModels[2] = 62743; //torvalegs
                definition.npcModels[3] = 65300; //maxcape
                definition.npcModels[4] = 13319; //bgloves
                definition.npcModels[5] = 27738; //dboots
                definition.npcModels[6] = 13999; //memeclaws
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                break;

            case 199:
                definition.name = "Sagittariis";
                definition.description = "Still dedicated to Twinky".getBytes();
                definition.combatLevel = 440;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[7];
                definition.npcModels[0] = 62739; //pern mask
                definition.npcModels[1] = 62744; //pern body
                definition.npcModels[2] = 62741; //pern legs
                definition.npcModels[3] = 65300; //max
                definition.npcModels[4] = 13319; //bgloves
                definition.npcModels[5] = 53309; //glaivens
                definition.npcModels[6] = 62750; //bow
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.rdc2 = 25252;
                break;

            case 198:
                definition.name = "Battle Brawl Master(Npc Tiers)";
                definition.description = "Still dedicated to Twinky".getBytes();
                definition.npcModels = new int[7];
                definition.npcModels[0] = 65530; //pern mask
                definition.npcModels[1] = 65275; //pern body
                definition.npcModels[2] = 65485; //pern legs
                definition.npcModels[3] = 65105; //max
                definition.npcModels[4] = 13319; //bgloves
                definition.npcModels[5] = 53309; //glaivens
                definition.npcModels[6] = 62750; //bow
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.originalModelColours = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                definition.changedModelColours = new int[1]; // same here
                definition.changedModelColours[0] = 40; // the texture that it currently has
                definition.originalModelColours[0] = 52; // the new texture u want it to have
                break;
            case 1821:
                definition.name = "@yel@Brawl Master";
                definition.description = "Still dedicated to Twinky".getBytes();
                definition.npcModels = new int[7];
                definition.npcModels[0] = 65530; //pern mask
                definition.npcModels[1] = 65275; //pern body
                definition.npcModels[2] = 65485; //pern legs
                definition.npcModels[3] = 65105; //max
                definition.npcModels[4] = 13319; //bgloves
                definition.npcModels[5] = 53309; //glaivens
                definition.npcModels[6] = 62750; //bow
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.originalModelColours = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                definition.changedModelColours = new int[1]; // same here
                definition.changedModelColours[0] = 40; // the texture that it currently has
                definition.originalModelColours[0] = 52; // the new texture u want it to have
                break;
            case 200:
                definition.name = "Venefica";
                definition.description = "Thanks for the memories.".getBytes();
                definition.combatLevel = 439;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[7];
                definition.npcModels[0] = 62736;
                definition.npcModels[1] = 62748;
                definition.npcModels[2] = 62742;
                definition.npcModels[3] = 65300;
                definition.npcModels[4] = 13319;
                definition.npcModels[5] = 53330;
                definition.npcModels[6] = 53577;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                break;

            case 457:
                definition.name = "Revenant Citizen";
                definition.rdc2 = 2929529;
                definition.actions = new String[]{"Talk-to", null, "Teleport", null, null};
                break;

            case 6139:
                definition.scaleXZ = definition.scaleY = 100;
                break;

            case 5417:
                definition.combatLevel = 210;
                break;

            case 5418:
                definition.combatLevel = 90;
                break;

            case 6715:
                definition.combatLevel = 14;
                break;

            case 6716:
                definition.combatLevel = 16;
                break;

            case 6701:
                definition.combatLevel = 53;
                break;

            case 6725:
                definition.combatLevel = 98;
                break;

            case 6691://dark beast
                definition.npcSizeInSquares = 2;
                definition.combatLevel = 112;
                break;

            case 8710:
            case 8707:
            case 8706:
            case 8705:
                definition.name = "Musician";
                definition.actions = new String[]{"Listen-to", null, null, null, null};
                break;

            case 131:
                definition.name = "Penguin";
                definition.scaleXZ = 120;
                definition.scaleY = 120;
                break;
            case 5188:
                definition.name = "Yeti";
                definition.scaleXZ = 120;
                definition.scaleY = 120;
                break;
            case 8405:
                definition.name = "<col=5af96d>Penguin Shop";
                definition.actions = new String[]{"Trade", null, null, null, null};
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                break;
            case 4651:
                definition.name = "<col=5af96d>Player Owned Shop Manager";
                definition.actions = new String[]{"Talk-to", null, "View Shops", "My Shop", "Claim Earnings"};
                break;

            case 4652:
                definition.name = "<col=aedaf9>Billbag Exchange";
                definition.actions = new String[]{"Talk-to", null, null, null, null};
                break;

            case 4896:
                definition.name = "Zeus";
                definition.npcModels = new int[]{58935};
                definition.actions = new String[]{"Talk-to", null, null, null, null};
                break;

            case 947:
                definition.name = "Grand Exchange clerk";
                break;

            case 9939:
                definition.combatLevel = 607;
                break;

            case 4540:
                definition.name = "@cya@Mega Avatar";
                definition.rdc2 = 9978;
                definition.npcSizeInSquares = 4;
                definition.scaleXZ = definition.scaleY = 175;
                definition.combatLevel = 299;
                break;

            case 3101:
                definition.scaleXZ = definition.scaleY = 80;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{"Talk-to", null, "Start", "Rewards", null};
                break;

            case 6222:
                definition.name = "Kree'arra";
                definition.npcSizeInSquares = 5;
                definition.standAnimation = 6972;
                definition.walkAnimation = 6973;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = definition.scaleY = 110;
                break;

            case 6203:
                //definition.npcModels = new int[]{27768, 27773, 27764, 27765, 27770};
                definition.name = "K'ril Tsutsaroth";
                definition.npcSizeInSquares = 2;
              //definition.standAnimation = 6943;
              //definition.walkAnimation = 6942;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = definition.scaleY = 105;
                break;

            case 6208:
                definition.name = "Battle demon";
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = definition.scaleY = 65;
                break;

            case 1610:
            case 491:
            case 10216:
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 7969:
                definition.actions = new String[]{"Talk-to", null, "Trade", null, null};
                break;

            case 8275:
                definition.name = "Duradel (Medium)";
                definition.actions = new String[]{"Talk-to", null, "Get-task", "Trade", "Rewards"};
                break;

            case 9085:
                definition.name = "Kuradal (Hard)";
                break;

            case 7780:
                definition.name = "Sumona (Elite)";
                break;

            case 925:
                definition.name = "<img=15>Slayer Grandmaster";
                definition.actions = new String[]{"Talk-to", null, "Get-task", "Trade", "Cancel Task"};
                definition.npcModels = new int[]{57778};
                definition.dialogueModels = null;
                definition.scaleXZ = 180;
                definition.scaleY = 180;
                break;

            case 1382:
                definition.name = "Glacor";
                definition.npcModels = new int[]{58940};
                definition.npcSizeInSquares = 3;
                definition.scaleY = definition.scaleXZ = 180;
                definition.standAnimation = 10869;
                definition.walkAnimation = 10867;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 123;
                definition.drawYellowDotOnMap = true;
                definition.combatLevel = 188;
                break;

            case 4249:
                definition.name = "Gambler";
                break;

            case 6970:
                definition.actions = new String[]{"Trade", null, null, null, null};
                break;

            case 4657:
                definition.actions = new String[]{"Talk-to", null, "Claim Items", "Check Total", "Teleport"};
                break;

            case 8549:
                definition.name = "Floreox Bird";
                definition.rdc2 = 9978;
                break;

            case 8591:
                definition.actions = new String[]{"Talk-to", null, "Trade", null, null};
                break;

            case 316:
                definition.name = "River fishing spot";
                definition.scaleXZ = 30;
                break;

            case 315:
                definition.scaleXZ = 30;
                break;

            case 309:
                definition.scaleXZ = 30;
                break;

            case 310:
                definition.scaleXZ = 30;
                break;

            case 314:
                definition.scaleXZ = 30;
                break;

            case 312:
                definition.name = "Sea fishing spot";
                definition.scaleXZ = 30;
                break;

            case 313:
                definition.name = "Ocean fishing spot";
                definition.scaleXZ = 30;
                break;

            case 318:
                definition.name = "Monk fishing spot";
                definition.scaleXZ = 30;
                definition.actions = new String[]{"Net", null, "Lure", null, null};
                break;

            case 805:
                definition.actions = new String[]{"Trade", null, "Tan hide", null, null};
                break;

            case 461:
            case 844:
            case 650:
            case 5112:
            case 3789:
            case 802:
            case 521:
            case 11226:
                definition.actions = new String[]{"Trade", null, null, null, null};
                break;

            case 520:
                definition.name = "General Store <col=bfa0fe>(@yel@Sell Junk<col=bfa0fe>)";
                definition.actions = new String[]{"Trade", null, null, null, null};
                break;

            case 13635:
                definition.name = "Troll King";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 800;
                definition.rdc2 = 800;
                definition.standAnimation = 10814;
                definition.walkAnimation = 10812;
                definition.npcSizeInSquares = 2;
                break;

            case 970:
                definition.actions = new String[]{"Event Shop", null, null, null, null};
                break;

            case 8022:
            case 8028:
                String color = id == 8022 ? "Yellow" : "Green";
                definition.name = "" + color + " energy source";
                definition.actions = new String[]{"Siphon", null, null, null, null};
                break;

            case 8444:
                definition.actions = new String[5];
                definition.actions[0] = "Trade";
                break;

            case 2579:
                definition.name = "Prestige Master";
                definition.description = "Prestige Master.".getBytes();
                definition.combatLevel = 126;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                definition.actions[2] = "Trade";
                definition.npcModels = new int[7];
                definition.npcModels[0] = 55761;
                definition.npcModels[1] = 62746;
                definition.npcModels[2] = 62743;
                definition.npcModels[3] = 65297;
                definition.npcModels[4] = 13319;
                definition.npcModels[5] = 27738;
                definition.npcModels[6] = 20147;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                break;

            case 810:
                definition.name = "Champion";
                definition.description = "Champion.".getBytes();
                definition.npcModels = new int[]{2545};
                definition.combatLevel = 624;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 350;
                definition.scaleY = 300;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 1112;
                break;

            case 811:
                definition.name = "Hades";
                definition.description = "Hades.".getBytes();
                definition.npcModels = new int[]{65028};
                definition.combatLevel = 624;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 240250;
                break;

            case 812:
                definition.name = "Stoned toad";
                definition.description = "stoned toad.".getBytes();
                definition.npcModels = new int[]{65145};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 8000:
                definition.name = "Galvek the great";
                definition.description = "Great Swordsman.".getBytes();
                definition.npcModels = new int[]{65172};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 180;
                definition.scaleY = 180;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 8617:
                definition.name = "Alchemical Hydra";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 426;
                definition.npcModels = new int[]{65318, 65319};
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                definition.standAnimation = 8240;
                definition.walkAnimation = 8239;
                definition.npcSizeInSquares = 6;
                break;

            case 8002:
                definition.name = "Malvek the wonderer";
                definition.description = "Great Swordsman.".getBytes();
                definition.npcModels = new int[]{65172};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 180;
                definition.scaleY = 180;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 73533;
                break;

            case 8004:
                definition.name = "Mahatma Gandhi";
                definition.description = "Mahatma Gandhi.".getBytes();
                definition.npcModels = new int[]{65173};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 8005:
                definition.name = "Blood sorceror";
                definition.description = "Blood sorceror.".getBytes();
                definition.npcModels = new int[]{65174};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 8006:
                definition.name = "Mist sorceror";
                definition.description = "Blood sorceror.".getBytes();
                definition.npcModels = new int[]{65174};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 19385;
                break;

            case 8007:
                definition.name = "Bob marley";
                definition.description = "Bob marley.".getBytes();
                definition.npcModels = new int[]{65175};
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 8008:
                definition.npcModels = new int[]{65176};
                definition.name = "Avatar Titan";
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 138;
                definition.standAnimation = 7949;
                definition.walkAnimation = 7952;
                definition.scaleXZ = 180;
                definition.scaleY = 180;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 2232356;
                break;


            case 3300:
                definition.name = "Corporeal Beast";
                MobDefinition definition2 = MobDefinition.get(8133);
                definition.description = "Corporeal beast.".getBytes();
                definition.npcModels = new int[]{64059};
                definition.combatLevel = definition2.combatLevel;
                definition.standAnimation = definition2.standAnimation;
                definition.walkAnimation = definition2.walkAnimation;
                definition.scaleXZ = definition2.scaleXZ;
                definition.scaleY = definition2.scaleY;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 3301:
                definition.name = "WildyWyrm 1";
                MobDefinition Wyrm2 = MobDefinition.get(3334);
                definition.description = "WildyWyrm.".getBytes();
                definition.npcModels = new int[]{64060};
                definition.combatLevel = Wyrm2.combatLevel;
                definition.standAnimation = Wyrm2.standAnimation;
                definition.walkAnimation = Wyrm2.walkAnimation;
                definition.scaleXZ = Wyrm2.scaleXZ;
                definition.scaleY = Wyrm2.scaleY;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 3302:
                definition.name = "WildyWyrm 2";
                MobDefinition Wyrm3 = MobDefinition.get(3334);
                definition.description = "WildyWyrm.".getBytes();
                definition.npcModels = new int[]{64061};
                definition.combatLevel = Wyrm3.combatLevel;
                definition.standAnimation = Wyrm3.standAnimation;
                definition.walkAnimation = Wyrm3.walkAnimation;
                definition.scaleXZ = Wyrm3.scaleXZ;
                definition.scaleY = Wyrm3.scaleY;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 3303:
                definition.name = "Donkey Kong";
                MobDefinition gwd = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64062};
                definition.combatLevel = gwd.combatLevel;
                definition.standAnimation = gwd.standAnimation;
                definition.walkAnimation = gwd.walkAnimation;
                definition.scaleXZ = gwd.scaleXZ;
                definition.scaleY = gwd.scaleY;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 3304:
                definition.name = "Thor";
                MobDefinition Thor = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64063};
                definition.combatLevel = Thor.combatLevel;
                definition.standAnimation = Thor.standAnimation;
                definition.walkAnimation = Thor.walkAnimation;
                definition.scaleXZ = Thor.scaleXZ;
                definition.scaleY = Thor.scaleY;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 3305:
                definition.name = "@bla@Onyx Panther";
                MobDefinition theincredhulk = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{65177};
                definition.combatLevel = theincredhulk.combatLevel;
                definition.standAnimation = theincredhulk.standAnimation;
                definition.walkAnimation = theincredhulk.walkAnimation;
                definition.scaleXZ = 270;
                definition.scaleY = 270;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                //definition.rdc2 = 6211;
                break;

            case 1890:
                definition.name = "Charizard pet";
                MobDefinition chari = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64067};

                definition.combatLevel = 0;

                definition.standAnimation = chari.standAnimation;
                definition.walkAnimation = chari.walkAnimation;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.actions = new String[5];
                definition.actions[0] = null;
                //definition.rdc2 = 664466;
                break;

            case 1892:
                definition.name = "Mario pet";
                MobDefinition chari1 = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64223};
                definition.combatLevel = chari1.combatLevel;
                definition.standAnimation = chari1.standAnimation;
                definition.walkAnimation = chari1.walkAnimation;
                definition.scaleXZ = 100;
                definition.drawYellowDotOnMap = false;
                definition.scaleY = 100;
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = null;
                //definition.rdc2 = 664466;
                break;

            case 1902:
                definition.name = "Bumble bee pet";
                MobDefinition chevycama = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64304};
                definition.combatLevel = chevycama.combatLevel;
                definition.standAnimation = chevycama.standAnimation;
                definition.walkAnimation = chevycama.walkAnimation;
                definition.scaleXZ = 80;
                definition.drawYellowDotOnMap = false;
                definition.scaleY = 80;
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.rdc2 = 664466;
                break;
            case 1801:
                definition.name = "Raichu Pet";//this is raichu
                MobDefinition adminspet = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64448}; // where is this.
                definition.combatLevel = adminspet.combatLevel;
                definition.standAnimation = adminspet.standAnimation;
                definition.walkAnimation = adminspet.walkAnimation;
                definition.scaleXZ = 80;
                definition.drawYellowDotOnMap = false;
                definition.scaleY = 80;
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = null;

                break;
            case 1904:
                definition.name = "Super star pet";
                MobDefinition sueprstrar = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64305};
                definition.combatLevel = sueprstrar.combatLevel;
                definition.standAnimation = sueprstrar.standAnimation;
                definition.walkAnimation = sueprstrar.walkAnimation;
                definition.scaleXZ = 80;
                definition.drawYellowDotOnMap = false;
                definition.scaleY = 80;
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = null;
                //definition.rdc2 = 664466;
                break;
            case 1905:
                definition.name = "Car";
                MobDefinition grandchevy = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64304};
                definition.combatLevel = grandchevy.combatLevel;
                definition.standAnimation = grandchevy.standAnimation;
                definition.walkAnimation = grandchevy.walkAnimation;
                definition.scaleXZ = 100;
                definition.drawYellowDotOnMap = false;
                definition.scaleY = 100;
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.rdc2 = 99733;
                break;
            case 1901:
                definition.name = "Universal star pet";
                MobDefinition cars2 = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64304};
                definition.combatLevel = cars2.combatLevel;
                definition.standAnimation = cars2.standAnimation;
                definition.walkAnimation = cars2.walkAnimation;
                definition.scaleXZ = 100;
                definition.drawYellowDotOnMap = false;
                definition.scaleY = 100;
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.rdc2 = 441199;
                //definition.rdc2 = 664466;
                break;
            case 1906:
                definition.name = "Zorbak (Elite)";
                MobDefinition zorkak = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64476};
                definition.combatLevel = zorkak.combatLevel;
                definition.standAnimation = zorkak.standAnimation;
                definition.walkAnimation = zorkak.walkAnimation;
                definition.scaleXZ = 120;
                definition.drawYellowDotOnMap = false;
                definition.scaleY = 120;
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = null;
                //   definition.rdc2 = 441199;
                //definition.rdc2 = 664466;
                break;
            case 1910:
                definition.name = "@or2@Youtube Junior";

                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.npcModels = new int[]{97666};
                definition.npcSizeInSquares = 1;
                definition.combatLevel = 0;
                definition.standAnimation = 11973;
                definition.walkAnimation = 11975;
                definition.scaleXZ = 30;
                definition.scaleY = 30;
                break;
            case 1893:
                definition.name = "Sonic pet";
                MobDefinition sonicx = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64224};
                definition.combatLevel = sonicx.combatLevel;
                definition.standAnimation = sonicx.standAnimation;
                definition.walkAnimation = sonicx.walkAnimation;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.drawYellowDotOnMap = false;
                definition.actions = new String[5];
                definition.combatLevel = 0;
                definition.actions[0] = null;
                //definition.rdc2 = 664466;
                break;
            case 1894:
                definition.name = "Donkeykong pet";
                MobDefinition donkeyk = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64225};
                definition.combatLevel = donkeyk.combatLevel;
                definition.standAnimation = donkeyk.standAnimation;
                definition.walkAnimation = donkeyk.walkAnimation;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.combatLevel = 0;
                definition.drawYellowDotOnMap = false;
                //definition.rdc2 = 664466;
                break;
            case 1896:
                definition.name = "Maximus pet";
                MobDefinition chari11 = MobDefinition.get(53);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64226};
                definition.combatLevel = chari11.combatLevel;
                definition.standAnimation = chari11.standAnimation;
                definition.walkAnimation = chari11.walkAnimation;
                definition.scaleXZ = 20;
                definition.scaleY = 20;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = false;
                definition.combatLevel = 0;
                definition.actions[0] = null;
                //definition.rdc2 = 664466;
                break;
            case 1897:
                definition.name = "Spyro";
                MobDefinition mixmiads = MobDefinition.get(53);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64290};
                definition.combatLevel = mixmiads.combatLevel;
                definition.standAnimation = mixmiads.standAnimation;
                definition.walkAnimation = mixmiads.walkAnimation;
                definition.scaleXZ = 120;
                definition.scaleY = 120;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = false;
                definition.actions[0] = null;
                //definition.rdc2 = 664466;
                break;

            case 1394:
                definition.name = "Rich Cookie monster";
                MobDefinition coookieoeee = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64303};

                definition.standAnimation = 842;
                definition.walkAnimation = coookieoeee.walkAnimation;
                definition.scaleXZ = 140;
                definition.drawYellowDotOnMap = true;
                definition.scaleY = 140;
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = null;
                //definition.rdc2 = 664466;
                definition.actions = new String[5];
                definition.actions[0] = "Open Shop";
                definition.actions[2] = "<col=629658>View Market";

                break;


            case 1988:
                definition.name = "<img=15>Slayer Progression";
                MobDefinition spryi2 = MobDefinition.get(53);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64290};
                definition.combatLevel = 0;
                definition.standAnimation = spryi2.standAnimation;
                definition.walkAnimation = spryi2.walkAnimation;
                definition.scaleXZ = 110;
                definition.scaleY = 110;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = true;
                definition.actions[0] = null;
                definition.rdc2 = 252525;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                break;
            case 1898:
                definition.name = "Slayer Drako Pet";
                MobDefinition spryi21 = MobDefinition.get(53);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64290};

                definition.standAnimation = spryi21.standAnimation;
                definition.walkAnimation = spryi21.walkAnimation;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = false;
                definition.actions[0] = null;
                definition.rdc2 = 252525;
                definition.combatLevel = 0;
                definition.actions = new String[5];
                definition.actions[0] = "Talk-to";
                break;
            case 3309:
                definition.npcModels = new int[]{64100};
                definition.name = "<col=f8ac00>double afk pet";
                MobDefinition doubldon = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                //definition.npcModels = new int[] {64067};
                definition.combatLevel = doubldon.combatLevel;
                definition.standAnimation = doubldon.standAnimation;
                definition.walkAnimation = doubldon.walkAnimation;
                definition.scaleXZ = 20;
                definition.drawYellowDotOnMap = false;

                definition.scaleY = 20;
                definition.actions = new String[5];
                definition.actions[0] = null;

                break;
            case 189:
                definition.name = "@red@King Dan";
                MobDefinition grinch = MobDefinition.get(186);
                definition.description = "GWD.".getBytes();
                definition.combatLevel = grinch.combatLevel;
                definition.standAnimation = 7531;
                definition.walkAnimation = grinch.walkAnimation;
                definition.npcModels = grinch.npcModels;
                definition.drawYellowDotOnMap = true;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.actions = new String[5];

                definition.combatLevel = 0;

                break;
            case 8802:
                definition.name = "<col=f8ac00>Double vote pet";
                MobDefinition darkath = MobDefinition.get(2292);
                definition.npcModels = new int[]{130620};
                definition.combatLevel = darkath.combatLevel;
                definition.standAnimation = darkath.standAnimation;
                definition.walkAnimation = darkath.walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 35;
                definition.scaleY = 35;
                break;
            case 9011:
                definition.name = "Mini lucifer";
                MobDefinition miniLucifer = MobDefinition.get(2292);
                definition.npcModels = new int[]{65232, 65234, 65236, 100003, 100004, 100005};
                definition.combatLevel = miniLucifer.combatLevel;
                definition.standAnimation = miniLucifer.standAnimation;
                definition.walkAnimation = miniLucifer.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 120;
                definition.scaleY = 120;
                definition.originalModelColours = new int[]{60};
                definition.changedModelColours = new int[]{52};
                break;
            case 9012:
                definition.name = "Lucifer";
                MobDefinition lucifer = MobDefinition.get(2292);
                definition.npcModels = new int[]{65232, 65234, 65236, 100003, 100004, 100005};
                definition.combatLevel = lucifer.combatLevel;
                definition.standAnimation = lucifer.standAnimation;
                definition.walkAnimation = lucifer.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = true;
                definition.npcSizeInSquares = 3;
                definition.scaleXZ = 225;
                definition.scaleY = 225;
                definition.originalModelColours = new int[]{60};
                definition.changedModelColours = new int[]{52};
                break;
            case 9013:
                definition.name = "Lucifer pet";
                MobDefinition luciferPet = MobDefinition.get(2292);
                definition.npcModels = new int[]{65232, 65234, 65236, 100003, 100004, 100005};
                definition.combatLevel = luciferPet.combatLevel;
                definition.standAnimation = luciferPet.standAnimation;
                definition.walkAnimation = luciferPet.walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                definition.originalModelColours = new int[]{60};
                definition.changedModelColours = new int[]{52};
                break;
            case 9014:
                definition.name = "Iron";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 3;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.npcModels = new int[1];
                definition.npcModels[0] = 100026;
                definition.drawYellowDotOnMap = true;

                break;
            case 9016:
                definition.name = "Iron pet";
                MobDefinition ironPet = MobDefinition.get(2292);
                definition.npcModels = new int[]{100026};
                definition.combatLevel = ironPet.combatLevel;
                definition.standAnimation = ironPet.standAnimation;
                definition.walkAnimation = ironPet.walkAnimation;
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.drawYellowDotOnMap = false;
                definition.npcSizeInSquares = 1;
                definition.scaleXZ = 50;
                definition.scaleY = 50;
                break;
            case 9017:
                definition.name = "Zenyte";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.combatLevel = 456;
                definition.npcSizeInSquares = 5;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 170;
                definition.scaleY = 170;
                definition.npcModels = new int[1];
                definition.npcModels[0] = 100027;
                definition.drawYellowDotOnMap = true;

                break;
            case 302:
                definition.name = "Double KC pet";
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 170;
                definition.scaleY = 170;
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
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.actions = new String[5];
                definition.drawYellowDotOnMap = false;

                definition.actions[0] = null;

                break;
            case 3310:
                definition.name = "Yoshi";
                MobDefinition drv = MobDefinition.get(2292);
                definition.description = "npc.".getBytes();
                definition.npcModels = new int[]{64068};
                definition.combatLevel = drv.combatLevel;
                definition.standAnimation = drv.standAnimation;
                definition.walkAnimation = drv.walkAnimation;
                definition.scaleXZ = drv.scaleXZ;
                definition.scaleY = drv.scaleY;
                definition.actions = new String[]{null, "Attack", null, null, null};

                break;
            case 3313:
                definition.name = "Prysm";
                definition.combatLevel = 324;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.npcSizeInSquares = 3;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 34734;
                break;

            case 3306:
                definition.name = "Event Organizer";
                MobDefinition drv1 = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64065};
                definition.combatLevel = drv1.combatLevel;
                definition.standAnimation = drv1.standAnimation;
                definition.walkAnimation = drv1.walkAnimation;
                definition.scaleXZ = drv1.scaleXZ;
                definition.scaleY = drv1.scaleY;
                definition.actions = new String[]{null, null, "Event Points Shop", null, null};
                break;

            case 130:
                definition.name = "Locked Santa";
                MobDefinition xmas = MobDefinition.get(2292);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{51156};
                definition.combatLevel = xmas.combatLevel;
                definition.standAnimation = 12686;
                definition.walkAnimation = xmas.walkAnimation;
                definition.scaleXZ = xmas.scaleXZ;
                definition.scaleY = xmas.scaleY;
                definition.actions = new String[]{"Open-Xmas", null, "Event Points Shop", null, null};
                break;

            case 3307:
                definition.name = "Darkrealm Gatekeeper";
                break;

            case 3308:
                definition.name = "Angel Lugia";
                MobDefinition poke = MobDefinition.get(4972);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64066};
                definition.combatLevel = poke.combatLevel;
                definition.standAnimation = poke.standAnimation;
                definition.walkAnimation = poke.walkAnimation;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 664466;
                break;

            case 3312:
                definition.name = "Darkrealm Lugia";
                MobDefinition lugiatextured = MobDefinition.get(4972);
                definition.description = "GWD.".getBytes();
                definition.npcModels = new int[]{64096};
                definition.combatLevel = lugiatextured.combatLevel;
                definition.standAnimation = lugiatextured.standAnimation;
                definition.walkAnimation = lugiatextured.walkAnimation;
                definition.scaleXZ = lugiatextured.scaleXZ;
                definition.scaleY = lugiatextured.scaleY;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.changedModelColours[0] = 40; // the texture that it currently has
                definition.originalModelColours[0] = 52; // the new texture u want it to have
                break;

            case 12241:
                definition.name = "Kermit the frog";
                definition.scaleXZ = 162;
                definition.scaleY = 162;
                definition.actions = new String[]{"Upgrade Shop", null, null, null, null};
                break;

            case 8010:
                definition.name = "Groudon";
                definition.description = "Groudon.".getBytes();
                definition.npcModels = new int[]{65191};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.npcSizeInSquares = 2;
                definition.originalModelColours = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                definition.changedModelColours = new int[1]; // same here
                definition.changedModelColours[0] = 40; // the texture that it currently has
                definition.originalModelColours[0] = 40; // the new texture u want it to have
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 350:
                definition.name = "Frieza (first form)";
                definition.description = "Frieza.".getBytes();
                definition.npcModels = new int[]{64362};
                definition.combatLevel = 0;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 50 + 20;
                definition.scaleY = 50 + 20;
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;

            case 351:
                definition.name = "Frieza (second form)";
                definition.description = "Frieza.".getBytes();
                definition.npcModels = new int[]{64363};
                definition.combatLevel = 0;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 70 + 20;
                definition.scaleY = 70 + 20;
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;

            case 352:
                definition.name = "Frieza (final form)";
                definition.description = "Frieza.".getBytes();
                definition.npcModels = new int[]{64364};
                definition.combatLevel = 0;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 60 + 20;
                definition.scaleY = 60 + 20;
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;

            case 928:
                definition.name = "Perfect Cell";
                definition.description = "Cell.".getBytes();
                definition.npcModels = new int[]{64385};
                definition.combatLevel = 200;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                //   definition.rdc2 = 2931;
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;
            case 4000:
                definition.name = "Majin Buu";
                definition.description = "Buu.".getBytes();
                definition.npcModels = new int[]{64381};
                definition.combatLevel = 200;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                //   definition.rdc2 = 2931;
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;
            case 4001:
                definition.name = "Super Buu";
                definition.description = "Buu.".getBytes();
                definition.npcModels = new int[]{64383};
                definition.combatLevel = 200;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                //   definition.rdc2 = 2931;
                definition.actions = new String[5];
                definition.actions[0] = null;
                break;
            case 450:
                definition.name = "Majin Buu";
                definition.description = "Buu.".getBytes();
                definition.npcModels = new int[]{64381};//64381
                definition.combatLevel = 100;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 145;
                definition.scaleY = 145;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 451:
                definition.name = "Kidd Buu";
                definition.description = "Buu.".getBytes();
                definition.npcModels = new int[]{64382};
                definition.combatLevel = 200;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 145;
                definition.scaleY = 145;
                //   definition.rdc2 = 2931;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 449:
                definition.name = "Perfect Cell";
                definition.description = "Cell.".getBytes();
                definition.npcModels = new int[]{64385};
                definition.combatLevel = 200;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 185;
                definition.scaleY = 185;
                //   definition.rdc2 = 2931;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 452:
                definition.name = "Super Buu";
                definition.description = "Buu.".getBytes();
                definition.npcModels = new int[]{64383};
                definition.combatLevel = 300;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 180;
                definition.scaleY = 180;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 250:
                definition.name = "Frieza (first form)";
                definition.description = "Frieza.".getBytes();
                definition.npcModels = new int[]{64362};
                definition.combatLevel = 100;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 150;
                definition.scaleY = 150;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 251:
                definition.name = "Frieza (second form)";
                definition.description = "Frieza.".getBytes();
                definition.npcModels = new int[]{64363};
                definition.combatLevel = 200;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 170;
                definition.scaleY = 170;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 252:
                definition.name = "Frieza (final form)";
                definition.description = "Frieza.".getBytes();
                definition.npcModels = new int[]{64364};
                definition.combatLevel = 300;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 190;
                definition.scaleY = 190;
                definition.npcSizeInSquares = 2;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 1023:
                definition.name = "Dark bloodveld";
                MobDefinition slayer10 = MobDefinition.get(1618);
                definition.description = "GWD.".getBytes();
                //definition.npcModels = new int[] {64064};
                definition.combatLevel = 40;
                definition.scaleXZ = 110;
                definition.scaleY = 110;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer10.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer10.npcModels.clone();
                definition.standAnimation = slayer10.standAnimation;
                definition.walkAnimation = slayer10.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 48754;
                break;
            case 1233://2643
                MobDefinition gargoyle = get(1610);
                definition.name = "Rusted Gargoyle";
                definition.combatLevel = 40;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = gargoyle.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = gargoyle.npcModels.clone();
                definition.standAnimation = gargoyle.standAnimation;
                definition.walkAnimation = gargoyle.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 2467;
                break;
            case 1234://2643
                MobDefinition slayer5 = get(1612);
                definition.name = "Crazy witch";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer5.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer5.npcModels.clone();
                definition.standAnimation = slayer5.standAnimation;
                definition.walkAnimation = slayer5.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 2345;
                break;
            /**
             * 	definition.name = "Starter Boss";
             definition.description = "Groudon.".getBytes();
             definition.npcModels = new int[] {65191};
             definition.combatLevel = 699;
             definition.standAnimation = 15;
             definition.walkAnimation = 13;
             definition.adjustVertextPointsXOrY = 75;
             definition.adjustVertextPointZ = 75;
             //originalModelColours
             //
             definition.changedModelColours = new int[] { 64, 64 };
             definition.originalModelColours = new int[] { 40, 24 };
             //  definition.originalModelColours = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
             //   definition.changedModelColours = new int[1]; // same here
             //  definition.changedModelColours[0] = 58; // the texture that it currently has
             //  definition.originalModelColours[0] = 40; // the new texture u want it to have


             */
            case 6692:
                definition.name = "Revenant Tarragon";
                definition.combatLevel = 126;
                definition.walkAnimation = 8590;
                definition.standAnimation = 8589;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcModels = new int[1];
                definition.npcModels[0] = 31721;
                definition.rdc2 = 385238;
                definition.scaleXZ = 190;
                definition.scaleY = 190;
                break;

            case 5510:
                definition.name = "Groudon";
                definition.description = "Groudon.".getBytes();
                definition.npcModels = new int[]{65191};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                //originalModelColours
                //
                definition.originalModelColours = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                definition.changedModelColours = new int[1]; // same here
                definition.changedModelColours[0] = 71; // the texture that it currently has
                definition.originalModelColours[0] = 40; // the new texture u want it to have

                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 5511:
                definition.name = "Groudon";
                definition.description = "Groudon.".getBytes();
                definition.npcModels = new int[]{65191};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.originalModelColours = new int[1]; // if only 1 texture is modified this has to be 1, if 2 then 2 etc
                definition.changedModelColours = new int[1]; // same here
                definition.changedModelColours[0] = 95; // the texture that it currently has
                definition.originalModelColours[0] = 40; // the new texture u want it to have
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;

            case 1614:
                definition.name = "Imps";
                definition.scaleXZ = 190;
                definition.scaleY = 190;
                break;

            case 8011:
                definition.name = "Event box";
                definition.description = "Event Box.".getBytes();
                definition.npcModels = new int[]{65205};
                definition.combatLevel = 699;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 12886:
                definition.name = "Cyantrix";
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                definition.npcSizeInSquares = 3;
                break;
            case 8014:
                definition.name = "Klatooinian";
                definition.description = "Klatooinian.".getBytes();
                definition.npcModels = new int[]{65207};
                definition.combatLevel = 83;
                definition.standAnimation = 808;
                definition.walkAnimation = 819;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 12843:
                definition.name = "Fractite demon";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = 85;
                definition.scaleY = 85;
                definition.npcSizeInSquares = 2;
                break;
            case 12343:
                definition.name = "Predator";
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.scaleXZ = 300;
                definition.scaleY = 300;
                definition.npcSizeInSquares = 2;
                definition.combatLevel = 427;
                definition.degreesToTurn = 32;
                break;
            case 13747:
                definition.name = "Ice Demon";
                definition.npcModels = new int[]{64329};
                MobDefinition icedemon2 = get(82);
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 2;
                definition.description = icedemon2.description;
                definition.drawYellowDotOnMap = true;
                definition.standAnimation = icedemon2.standAnimation;
                definition.walkAnimation = icedemon2.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                break;
            case 8018:
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.name = "MageBeast";
                definition.npcModels = new int[]{64425};
                MobDefinition icebeasts = get(2783);
                definition.combatLevel = 130;
                definition.scaleXZ = 500;
                definition.scaleY = 500;
                definition.npcSizeInSquares = 2;
                definition.description = icebeasts.description;
                definition.drawYellowDotOnMap = true;
                definition.degreesToTurn = 32;
                definition.standAnimation = icebeasts.standAnimation;
                definition.walkAnimation = icebeasts.walkAnimation;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.npcSizeInSquares = 1;
                //definition.rdc2 = 29492;
                break;
            case 662:
                definition.name = "Lottie (Lottery)";
                break;
            case 8015:
                definition.name = "@cya@Elite Dragon";
                definition.description = "Evil Dragon.".getBytes();
                definition.npcModels = new int[]{65208};
                definition.combatLevel = 333;
                definition.standAnimation = 12248;
                definition.walkAnimation = 12246;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.rdc2 = 33132;
                definition.npcSizeInSquares = 4;
                break;
            case 8012:
                definition.name = "Delectro";
                definition.combatLevel = 492;
                definition.walkAnimation = 1660;
                definition.standAnimation = 11973;
                definition.npcModels = new int[9];
                definition.npcModels[0] = 65193; //HEAD
                definition.npcModels[1] = 65204; //JAW
                definition.npcModels[2] = 65195; //CHEST
                definition.npcModels[3] = -1; //CAPE
                //	definition.npcModels[4] = 65199; //ARM
                definition.npcModels[5] = -1; //HAND//shield
                definition.npcModels[6] = 65202; //WEP
                definition.npcModels[7] = 65197; //LEG
                definition.npcModels[8] = 65200; //BOOT
                definition.actions = new String[5];
                definition.actions[0] = null;
                definition.actions[1] = "Attack";
                definition.actions[2] = null;
                definition.actions[3] = null;
                definition.actions[4] = null;
                break;

            case 1471:
                definition.name = "Monkey Skeleton";
                break;
            case 6328:
                definition.name = "Skeleton Mage";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 64;
                definition.npcModels = new int[]{21196};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 5483;
                definition.walkAnimation = 5476;
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
            case 6327:
                definition.name = "Skeleton fremennik";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 60;
                definition.npcModels = new int[]{24181, 24189, 24185, 24053};
                definition.originalModelColours = new int[]{8404, 12694, 12574, 8400, 10531};
                definition.changedModelColours = new int[]{10299, 8472, 7452, 10299, 7335};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 6113;
                definition.walkAnimation = 6112;
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
            case 1700:
                MobDefinition slayer1 = get(6796);
                definition.name = "Granite crab";
                definition.combatLevel = 40;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer1.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer1.npcModels.clone();
                definition.standAnimation = slayer1.standAnimation;
                definition.walkAnimation = slayer1.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 8088;
                break;
            case 1701:
                MobDefinition slayer2 = get(78);
                definition.name = "Icicle Bat";
                definition.combatLevel = 40;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer2.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer2.npcModels.clone();
                definition.standAnimation = slayer2.standAnimation;
                definition.walkAnimation = slayer2.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 36462;
                break;
            case 1702:
                MobDefinition slayer3 = get(3499);
                definition.name = "Lavannoth";
                definition.combatLevel = 40;
                definition.scaleXZ = 70;
                definition.scaleY = 70;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer3.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer3.npcModels.clone();
                definition.standAnimation = slayer3.standAnimation;
                definition.walkAnimation = slayer3.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 5674;
                break;
            case 1703:
                MobDefinition slayer4 = get(1652);
                definition.name = "Vampyre Hands";
                definition.combatLevel = 40;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer4.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer4.npcModels.clone();
                definition.standAnimation = slayer4.standAnimation;
                definition.walkAnimation = slayer4.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 34567;
                break;
            case 1704:
                MobDefinition slayer55 = get(1612);
                definition.name = "Crazy witch";
                definition.combatLevel = 40;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer55.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer55.npcModels.clone();
                definition.standAnimation = slayer55.standAnimation;
                definition.walkAnimation = slayer55.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 2345;
                break;
            case 1705:
                MobDefinition slayer6 = get(6813);
                definition.name = "Bunyip";
                definition.combatLevel = 40;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer6.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer6.npcModels.clone();
                definition.standAnimation = slayer6.standAnimation;
                definition.walkAnimation = slayer6.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 267;//236773
                break;
            case 1706:
                MobDefinition slayer7 = get(52);
                definition.name = "Baby bronze dragon";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer7.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer7.npcModels.clone();
                definition.standAnimation = slayer7.standAnimation;
                definition.walkAnimation = slayer7.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 90774;
                break;
            case 1707:
                MobDefinition slayer8 = get(4275);
                definition.name = "Tribal goblin";
                definition.combatLevel = 40;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer8.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer8.npcModels.clone();
                definition.standAnimation = slayer8.standAnimation;
                definition.walkAnimation = slayer8.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 32562;
                break;
            case 1708:
                MobDefinition slayer9 = get(1604);
                definition.name = "Ghoulord";
                definition.combatLevel = 40;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer9.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer9.npcModels.clone();
                definition.standAnimation = slayer9.standAnimation;
                definition.walkAnimation = slayer9.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 34543;
                break;
            case 1709:
                definition.name = "Pelican bird";
                MobDefinition slayer61 = MobDefinition.get(6795);
                definition.description = "GWD.".getBytes();
                //definition.npcModels = new int[] {64064};
                definition.combatLevel = 40;
                definition.scaleXZ = 140;
                definition.scaleY = 140;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer61.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer61.npcModels.clone();
                definition.standAnimation = slayer61.standAnimation;
                definition.walkAnimation = slayer61.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 857;
                break;
            case 1710:
                definition.name = "Dark bloodveld";
                MobDefinition slayer110 = MobDefinition.get(1618);
                definition.description = "GWD.".getBytes();
                //definition.npcModels = new int[] {64064};
                definition.combatLevel = 40;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = slayer110.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = slayer110.npcModels.clone();
                definition.standAnimation = slayer110.standAnimation;
                definition.walkAnimation = slayer110.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 48754;
                break;

            case 1711:
                MobDefinition firebat = get(7335);
                definition.name = "Flaming butterfly";
                definition.combatLevel = 40;
                definition.scaleXZ = 110;
                definition.scaleY = 110;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = firebat.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = firebat.npcModels.clone();
                definition.standAnimation = firebat.standAnimation;
                definition.walkAnimation = firebat.walkAnimation;
                definition.degreesToTurn = 32;
                //definition.rdc2 = 36462;
                break;
            case 1712:
                MobDefinition gargoyle2 = get(1610);
                definition.name = "Rusted Gargoyle";
                definition.combatLevel = 40;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = gargoyle2.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = gargoyle2.npcModels.clone();
                definition.standAnimation = gargoyle2.standAnimation;
                definition.walkAnimation = gargoyle2.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 2467;
                break;
            case 1713:
                MobDefinition b1712 = get(7331);
                definition.name = "Mosquito";
                definition.combatLevel = 40;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1712.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1712.npcModels.clone();
                definition.standAnimation = b1712.standAnimation;
                definition.walkAnimation = b1712.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 2467;
                break;
            case 1714:
                MobDefinition b1714 = get(1615);
                definition.name = "PVM Demon";
                definition.combatLevel = 40;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1714.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1714.npcModels.clone();
                definition.standAnimation = b1714.standAnimation;
                definition.walkAnimation = b1714.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 342;
                break;
            case 1715:
                MobDefinition b1715 = get(84);
                definition.name = "Native demon";
                definition.combatLevel = 40;
                definition.scaleXZ = 90;
                definition.scaleY = 90;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1715.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1715.npcModels.clone();
                definition.standAnimation = b1715.standAnimation;
                definition.walkAnimation = b1715.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 1245;
                break;
            case 1716:
                MobDefinition b1716 = get(6831);
                definition.name = "Chinese dragon";
                definition.combatLevel = 40;
                definition.scaleXZ = 190;
                definition.scaleY = 190;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1716.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1716.npcModels.clone();
                definition.standAnimation = b1716.standAnimation;
                definition.walkAnimation = b1716.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 9876;
                break;
            case 1717:
                MobDefinition b1717 = get(6837);
                definition.name = "Flesh Scorpian";
                definition.combatLevel = 40;
                definition.scaleXZ = 190;
                definition.scaleY = 190;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1717.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1717.npcModels.clone();
                definition.standAnimation = b1717.standAnimation;
                definition.walkAnimation = b1717.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 2345;
                break;

            case 1718:
                MobDefinition b1718 = get(6872);
                definition.name = "Stinky blobb";
                definition.combatLevel = 40;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1718.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1718.npcModels.clone();
                definition.standAnimation = b1718.standAnimation;
                definition.walkAnimation = b1718.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 4364;
                break;
            case 1719:
                MobDefinition b1719 = get(6860);
                definition.name = "Armoured minotaur";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1719.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1719.npcModels.clone();
                definition.standAnimation = b1719.standAnimation;
                definition.walkAnimation = b1719.walkAnimation;
                definition.degreesToTurn = 32;
                //definition.rdc2 = 15;
                break;
            case 1721:
                MobDefinition b17120 = get(7333);
                definition.name = "Jellyfish";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b17120.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b17120.npcModels.clone();
                definition.standAnimation = b17120.standAnimation;
                definition.walkAnimation = b17120.walkAnimation;
                definition.degreesToTurn = 32;
                //	definition.rdc2 = 964;
                break;
            case 1723:
                MobDefinition b17122 = get(7353);
                definition.name = "Evil chinchompa";
                definition.combatLevel = 40;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b17122.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b17122.npcModels.clone();
                definition.standAnimation = b17122.standAnimation;
                definition.walkAnimation = b17122.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 7454;
                break;
            case 1724:
                MobDefinition b17124 = get(6867);
                definition.name = "Ant worker";
                definition.combatLevel = 40;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b17124.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b17124.npcModels.clone();
                definition.standAnimation = b17124.standAnimation;
                definition.walkAnimation = b17124.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 7454;
                break;
            case 1725:
                MobDefinition b17125 = get(6881);
                definition.name = "Zamorak bird";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b17125.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b17125.npcModels.clone();
                definition.standAnimation = b17125.standAnimation;
                definition.walkAnimation = b17125.walkAnimation;
                definition.degreesToTurn = 32;
                //	definition.rdc2 = 111;
                break;
            case 1726:
                MobDefinition b1726 = get(6833);
                definition.name = "Crying turnip";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1726.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1726.npcModels.clone();
                definition.standAnimation = b1726.standAnimation;
                definition.walkAnimation = b1726.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 25236;
                break;

            case 1727:
                MobDefinition b1727 = get(7377);
                definition.name = "Symbiote";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1727.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1727.npcModels.clone();
                definition.standAnimation = b1727.standAnimation;
                definition.walkAnimation = b1727.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 273523;
                break;

            case 1729:
                MobDefinition b6992 = get(6992);
                definition.name = "Jello";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b6992.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b6992.npcModels.clone();
                definition.standAnimation = b6992.standAnimation;
                definition.walkAnimation = b6992.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 25236;
                break;

            case 1730:
                MobDefinition b1730 = get(6991);
                definition.name = "Tycoons bird";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1730.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1730.npcModels.clone();
                definition.standAnimation = b1730.standAnimation;
                definition.walkAnimation = b1730.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 9;
                break;
            case 1731:
                MobDefinition b6931 = get(7365);
                definition.name = "Sabertooth";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b6931.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b6931.npcModels.clone();
                definition.standAnimation = b6931.standAnimation;
                definition.walkAnimation = b6931.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 25212;
                break;

            case 1733:
                MobDefinition b1732 = get(7337);
                definition.name = "Leopard";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1732.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1732.npcModels.clone();
                definition.standAnimation = b1732.standAnimation;
                definition.walkAnimation = b1732.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 9;
                break;
            case 1734:
                MobDefinition b69334 = get(7363);
                definition.name = "Wild Graahk";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b69334.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b69334.npcModels.clone();
                definition.standAnimation = b69334.standAnimation;
                definition.walkAnimation = b69334.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 152;
                break;

            case 1735:
                MobDefinition b1734 = get(6809);
                definition.name = "Sea creature";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1734.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1734.npcModels.clone();
                definition.standAnimation = b1734.standAnimation;
                definition.walkAnimation = b1734.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 9;
                break;
            case 1736:
                MobDefinition b6936 = get(6865);
                definition.name = "Kree devil";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b6936.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b6936.npcModels.clone();
                definition.standAnimation = b6936.standAnimation;
                definition.walkAnimation = b6936.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 273234;
                break;

            case 1737:
                MobDefinition b1737 = get(6827);
                definition.name = "War plant";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1737.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1737.npcModels.clone();
                definition.standAnimation = b1737.standAnimation;
                definition.walkAnimation = b1737.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 22;
                break;
            case 1738:
                MobDefinition b6938 = get(6889);
                definition.name = "Hallucination toad";
                definition.combatLevel = 40;
                definition.scaleXZ = 190;
                definition.scaleY = 170;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b6938.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b6938.npcModels.clone();
                definition.standAnimation = b6938.standAnimation;
                definition.walkAnimation = b6938.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 2367;
                break;

            case 1739:
                MobDefinition b1739 = get(7339);
                definition.name = "Blast cloud";
                definition.combatLevel = 40;
                definition.scaleXZ = 60;
                definition.scaleY = 60;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b1739.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b1739.npcModels.clone();
                definition.standAnimation = b1739.standAnimation;
                definition.walkAnimation = b1739.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 12345;
                break;
            case 1740:
                MobDefinition b69340 = get(7357);
                definition.name = "Elemental moss";
                definition.combatLevel = 40;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b69340.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b69340.npcModels.clone();
                definition.standAnimation = b69340.standAnimation;
                definition.walkAnimation = b69340.walkAnimation;
                definition.degreesToTurn = 32;
                //	definition.rdc2 = 2367;
                break;

            case 1741:
                MobDefinition b17341 = get(7355);
                definition.name = "Elemental fire";
                definition.combatLevel = 40;
                definition.scaleXZ = 80;
                definition.scaleY = 80;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b17341.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b17341.npcModels.clone();
                definition.standAnimation = b17341.standAnimation;
                definition.walkAnimation = b17341.walkAnimation;
                definition.degreesToTurn = 32;
                //definition.rdc2 = 12345;
                break;
            case 1742:
                MobDefinition b6932 = get(6822);
                definition.name = "Nature unicorn";
                definition.combatLevel = 40;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b6932.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b6932.npcModels.clone();
                definition.standAnimation = b6932.standAnimation;
                definition.walkAnimation = b6932.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 9090;
                break;

            case 1743:
                MobDefinition b17343 = get(9488);
                definition.name = "Hyndra";
                definition.combatLevel = 40;
                definition.scaleXZ = 200;
                definition.scaleY = 200;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b17343.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b17343.npcModels.clone();
                definition.standAnimation = b17343.standAnimation;
                definition.walkAnimation = b17343.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 23733;
                break;
            case 1744:
                MobDefinition b6935 = get(6800);
                definition.name = "Grooter";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b6935.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b6935.npcModels.clone();
                definition.standAnimation = b6935.standAnimation;
                definition.walkAnimation = b6935.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 3216;
                break;

            case 1745:
                MobDefinition b17346 = get(6816);
                definition.name = "Runite Turtle";
                definition.combatLevel = 40;
                definition.scaleXZ = 130;
                definition.scaleY = 130;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b17346.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b17346.npcModels.clone();
                definition.standAnimation = b17346.standAnimation;
                definition.walkAnimation = b17346.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 9532352;
                break;
            case 1746:
                MobDefinition b17376 = get(8549);
                definition.name = "ohhoo";
                definition.combatLevel = 40;
                definition.scaleXZ = 100;
                definition.scaleY = 100;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.description = b17376.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = b17376.npcModels.clone();
                definition.standAnimation = b17376.standAnimation;
                definition.walkAnimation = b17376.walkAnimation;
                definition.degreesToTurn = 32;
                definition.rdc2 = 36323;
                break;
            case 6326:
                definition.name = "Skeleton fremennik";
                definition.description = "stuff".getBytes();
                definition.combatLevel = 40;
                definition.npcModels = new int[]{24180, 24187, 24184, 24054};
                definition.actions = new String[]{null, "Attack", null, null, null};
                definition.degreesToTurn = 32;
                definition.headIcon = -1;
                definition.npcSizeInSquares = 1;
                definition.standAnimation = 6113;
                definition.walkAnimation = 6112;
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
            case 1084:
                MobDefinition warriorshop = get(652);
                definition.name = "Warrior Shop";
                definition.combatLevel = 0;
                definition.scaleXZ = 190;
                definition.scaleY = 190;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{"Talk-to", null, null, null, null};
                definition.description = warriorshop.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = new int[]{64509};
                definition.standAnimation = warriorshop.standAnimation;
                definition.walkAnimation = warriorshop.walkAnimation;
                definition.degreesToTurn = 32;
                //	definition.rdc2 = 4444444;
                break;
            case 1085:
                MobDefinition archer = get(652);
                definition.name = "Archer shop";
                definition.combatLevel = 0;
                definition.scaleXZ = 190;
                definition.scaleY = 190;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{"Talk-to", null, null, null, null};
                definition.description = archer.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = new int[]{64510};
                definition.standAnimation = archer.standAnimation;
                definition.walkAnimation = archer.walkAnimation;
                definition.degreesToTurn = 32;
                //definition.rdc2 = 777777777;
                break;
            case 1086:
                MobDefinition wizaardo1 = get(652);
                definition.name = "Crazy wizard shop";
                definition.combatLevel = 40;
                definition.scaleXZ = 155;
                definition.scaleY = 155;
                definition.npcSizeInSquares = 1;
                definition.actions = new String[]{"Talk-to", null, null, null, null};
                definition.description = wizaardo1.description;
                definition.drawYellowDotOnMap = true;
                definition.npcModels = new int[]{64511};
                definition.standAnimation = wizaardo1.standAnimation;
                definition.walkAnimation = wizaardo1.walkAnimation;
                definition.degreesToTurn = 32;
                break;
        }

        return definition;
    }

    public void copy(int id) {
        MobDefinition other = get(id);
        changedModelColours = other.changedModelColours.clone();
        childrenIDs = other.childrenIDs.clone();
        combatLevel = other.combatLevel;
        configChild = other.configChild;
        degreesToTurn = other.degreesToTurn;
        description = other.description;
        dialogueModels = other.dialogueModels;
        disableRightClick = false;
        drawYellowDotOnMap = other.drawYellowDotOnMap;
        headIcon = other.headIcon;
        modelLightning = other.modelLightning;
        modelShadowing = other.modelShadowing;
        npcModels = other.npcModels.clone();
        originalModelColours = other.originalModelColours.clone();
        standAnimation = other.standAnimation;
        varBitChild = other.varBitChild;
        visibilityOrRendering = other.visibilityOrRendering;
        walkAnimation = other.walkAnimation;
        walkingBackwardsAnimation = other.walkingBackwardsAnimation;
        walkLeftAnimation = other.walkLeftAnimation;
        walkRightAnimation = other.walkRightAnimation;
        scaleXZ = other.scaleXZ;
        scaleY = other.scaleY;
        npcSizeInSquares = other.npcSizeInSquares;
    }

    public static void nullify() {
        mruNodes = null;
        streamIndices = null;
        cache = null;
        buffer = null;
    }

    public static void load(Archive archive) {
        buffer = new ByteBuffer(archive.get("npc.dat"));
        ByteBuffer stream2 = new ByteBuffer(archive.get("npc.idx"));

        bufferOSRS = new ByteBuffer(archive.get("npcosrs.dat"));
        ByteBuffer streamOsrs = new ByteBuffer(archive.get("npcosrs.idx"));

        int totalNPCs = stream2.getUnsignedShort();
        int totalOSRSNPCs = streamOsrs.readUnsignedWord();
        streamIndices = new int[totalNPCs];
        streamIndicesOSRS = new int[totalOSRSNPCs];
        int position = 2;

        for (int i = 0; i < totalNPCs; i++) {
            streamIndices[i] = position;
            position += stream2.getUnsignedShort();
        }

        position = 2;

        for (int j = 0; j < totalOSRSNPCs; j++) {
            streamIndicesOSRS[j] = position;
            position += streamOsrs.readUnsignedWord();
        }

        cache = new MobDefinition[20];
        cacheOSRS = new MobDefinition[20];

        for (int i = 0; i < 20; i++) {
            cache[i] = new MobDefinition();
            cacheOSRS[i] = new MobDefinition();
        }
    }

    public String[] actions;
    public int scaleXZ;
    public int scaleY;
    public int[] changedModelColours;
    public int[] childrenIDs;
    public int combatLevel;
    public int configChild;
    public int degreesToTurn;
    public byte[] description;
    public int[] dialogueModels;
    public boolean disableRightClick;
    public boolean drawYellowDotOnMap;
    public int headIcon;
    public int modelLightning;
    public int modelShadowing;
    public String name;
    public int[] npcModels;
    public byte npcSizeInSquares;
    public int[] originalModelColours;
    public int standAnimation;
    public int id;
    public int varBitChild;
    public boolean visibilityOrRendering;
    public int walkAnimation;

    public int getStandAnimation() {
        return standAnimation;
    }

    public int getWalkAnimation() {
        return walkAnimation;
    }

    public int walkingBackwardsAnimation;
    public int walkLeftAnimation;
    public int walkRightAnimation;

    private MobDefinition() {
        walkRightAnimation = -1;
        varBitChild = -1;
        walkingBackwardsAnimation = -1;
        configChild = -1;
        combatLevel = -1;
        walkAnimation = -1;
        npcSizeInSquares = 1;
        headIcon = -1;
        standAnimation = -1;
        id = -1;
        degreesToTurn = 32;
        walkLeftAnimation = -1;
        disableRightClick = true;
        scaleY = 128;
        drawYellowDotOnMap = true;
        scaleXZ = 128;
        visibilityOrRendering = false;
        rdc = 0;
        rdc2 = 0;
        rdc3 = 0;

    }

    public Model method160() {
        if (childrenIDs != null) {
            MobDefinition definition = method161();

            if (definition == null) {
                return null;
            } else {
                return definition.method160();
            }
        }

        if (dialogueModels == null) {
            return null;
        }

        boolean flag1 = false;

        for (int i = 0; i < dialogueModels.length; i++) {
            if (!Model.method463(dialogueModels[i])) {
                flag1 = true;
            }
        }

        if (flag1) {
            return null;
        }

        Model aclass30_sub2_sub4_sub6s[] = new Model[dialogueModels.length];

        for (int j = 0; j < dialogueModels.length; j++) {
            aclass30_sub2_sub4_sub6s[j] = Model.fetchModel(dialogueModels[j], rs3, osrs);
        }

        Model model;

        if (aclass30_sub2_sub4_sub6s.length == 1) {
            model = aclass30_sub2_sub4_sub6s[0];
        } else {
            model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
        }

        if (originalModelColours != null) {
            for (int k = 0; k < originalModelColours.length; k++) {
                model.method476(originalModelColours[k], changedModelColours[k]);
            }
        }
        if (rdc > 0)
            model.method1337(rdc);
        if (rdc2 != 0)
            model.method1338(rdc2);
        if (rdc3 != 0)
            model.method1339(rdc3);
        applyTexturing1(model, id);


        if (colorChange != null)
            Objects.requireNonNull(model).tint(colorChange);

        return model;
    }

    private void applyTexturing(Model model, int id) {
        // TODO Auto-generated method stub

    }

    public MobDefinition method161() {
        int j = -1;

        try {
            if (varBitChild != -1 && varBitChild < VarBit.cache.length) {
                VarBit varBit = VarBit.cache[varBitChild];
                int k = varBit.configId;
                int l = varBit.configValue;
                int i1 = varBit.anInt650;
                int j1 = Client.anIntArray1232[i1 - l];
                // System.out.println("k: " + k + " l: " + l);
                j = clientInstance.variousSettings[k] >> l & j1;
            } else if (configChild != -1) {
                j = clientInstance.variousSettings[configChild];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
            return null;
        } else {
            return get(childrenIDs[j]);
        }
    }

    public Model method164(int j, int frame, int ai[], int nextFrame, int cycle1, int cycle2) {
        if (childrenIDs != null) {
            MobDefinition entityDef = method161();

            if (entityDef == null) {
                return null;
            } else {
                return entityDef.method164(j, frame, ai, nextFrame, cycle1, cycle2);
            }
        }

        Model model = (Model) mruNodes.insertFromCache(id);

        if (model == null) {
            boolean flag = false;

            for (int i1 = 0; i1 < npcModels.length; i1++) {
                if (!Model.method463(npcModels[i1], rs3, osrs)) {
                    flag = true;
                }
            }

            if (flag) {
                return null;
            }


            Model aclass30_sub2_sub4_sub6s[] = new Model[npcModels.length];

            for (int j1 = 0; j1 < npcModels.length; j1++) {
                aclass30_sub2_sub4_sub6s[j1] = Model.fetchModel(npcModels[j1], rs3, osrs);
            }

            if (aclass30_sub2_sub4_sub6s.length == 1) {
                model = aclass30_sub2_sub4_sub6s[0];
            } else {
                model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
            }

            if (originalModelColours != null) {
                for (int k1 = 0; k1 < originalModelColours.length; k1++) {
                    model.method476(originalModelColours[k1], changedModelColours[k1]);
                }
            }
            if (rdc > 0)
                model.method1337(rdc);
            if (rdc2 != 0)
                model.method1338(rdc2);
            if (rdc3 != 0)
                model.method1339(rdc3);
            applyTexturing1(model, id);

            if (colorChange != null)
                Objects.requireNonNull(model).tint(colorChange);

            if (rs3)
                model.createBonesRS3();
            else
                model.createBones();

            model.light(84 + modelLightning, 1000 + modelShadowing, -90, -580, -90, true);
            mruNodes.removeFromCache(model, id);
        }

        Model model_1 = Model.aModel_1621;
        model_1.method464(model, FrameReader.isNullFrame(frame) & FrameReader.isNullFrame(j));
/*
		if (frame != -1 && j != -1) {
			model_1.method471(ai, j, frame);
		} else if (frame != -1 && !Configuration.TWEENING_ENABLED) {
			model_1.applyTransform(frame);
		} else if (frame != -1 && nextFrame != -1 && Configuration.TWEENING_ENABLED) {
			model_1.interpolateFrames(frame, nextFrame, cycle1, cycle2);
		}*/

        if (frame != -1 && j != -1)
            model_1.method471(ai, j, frame, osrs);
        else if (frame != -1 && nextFrame != -1 && Configuration.TWEENING_ENABLED)
            model_1.interpolateFrames(frame, nextFrame, cycle1, cycle2, osrs);
        else if (frame != -1)
            model_1.applyTransform(frame, osrs);

        if (scaleXZ != 128 || scaleY != 128) {
            model_1.scaleT(scaleXZ, scaleXZ, scaleY);
        }

        model_1.method466();
        model_1.triangleSkin = null;
        model_1.vertexSkin = null;

        if (npcSizeInSquares == 1) {
            model_1.aBoolean1659 = true;
        }

        return model_1;
    }

    private void readValues(ByteBuffer buffer) {
        boolean osrs = buffer == bufferOSRS;
        do {
            final int opcode = buffer.getUnsignedByte();

            if (opcode == 0) {
                return;
            }

            if (opcode == 1) {
                int j = buffer.getUnsignedByte();
                npcModels = new int[j];

                for (int j1 = 0; j1 < j; j1++) {
                    npcModels[j1] = buffer.getUnsignedShort();
                }
            } else if (opcode == 2) {
                name = buffer.getString();
            } else if (opcode == 3) {
                description = buffer.getBytes();
            } else if (opcode == 12) {
                npcSizeInSquares = buffer.getSignedByte();
            } else if (opcode == 13) {
                standAnimation = buffer.getUnsignedShort();
                if (osrs) {
                    standAnimation += Animation.OSRS_ANIM_OFFSET;
                }
            } else if (opcode == 14) {
                walkAnimation = buffer.getUnsignedShort();
                if (osrs) {
                    walkAnimation += Animation.OSRS_ANIM_OFFSET;
                }
            } else if (opcode == 17) {
                walkAnimation = buffer.getUnsignedShort();
                walkingBackwardsAnimation = buffer.getUnsignedShort();
                walkLeftAnimation = buffer.getUnsignedShort();
                walkRightAnimation = buffer.getUnsignedShort();

                if (walkAnimation == 65535) {
                    walkAnimation = -1;
                }

                if (walkingBackwardsAnimation == 65535) {
                    walkingBackwardsAnimation = -1;
                }

                if (walkLeftAnimation == 65535) {
                    walkLeftAnimation = -1;
                }

                if (walkRightAnimation == 65535) {
                    walkRightAnimation = -1;
                }
                if (osrs) {
                    if (walkAnimation != -1)
                        walkAnimation += Animation.OSRS_ANIM_OFFSET;
                    if (walkingBackwardsAnimation != -1)
                        walkingBackwardsAnimation += Animation.OSRS_ANIM_OFFSET;
                    if (walkLeftAnimation != -1)
                        walkLeftAnimation += Animation.OSRS_ANIM_OFFSET;
                    if (walkRightAnimation != -1)
                        walkRightAnimation += Animation.OSRS_ANIM_OFFSET;
                }

            } else if (opcode >= 30 && opcode < 40) {
                if (actions == null) {
                    actions = new String[5];
                }

                actions[opcode - 30] = buffer.getString();

                if (actions[opcode - 30].equalsIgnoreCase("hidden")) {
                    actions[opcode - 30] = null;
                }
            } else if (opcode == 40) {
                int length = buffer.getUnsignedByte();
                changedModelColours = new int[length];
                originalModelColours = new int[length];

                for (int i = 0; i < length; i++) {
                    originalModelColours[i] = buffer.getUnsignedShort();
                    changedModelColours[i] = buffer.getUnsignedShort();
                }
            } else if (opcode == 60) {
                int length = buffer.getUnsignedByte();
                dialogueModels = new int[length];

                for (int i = 0; i < length; i++) {
                    dialogueModels[i] = buffer.getUnsignedShort();
                }
            } else if (opcode == 90) {
                buffer.getUnsignedShort();
            } else if (opcode == 91) {
                buffer.getUnsignedShort();
            } else if (opcode == 92) {
                buffer.getUnsignedShort();
            } else if (opcode == 93) {
                drawYellowDotOnMap = false;
            } else if (opcode == 95) {
                combatLevel = buffer.getUnsignedShort();
            } else if (opcode == 97) {
                scaleXZ = buffer.getUnsignedShort();
            } else if (opcode == 98) {
                scaleY = buffer.getUnsignedShort();
            } else if (opcode == 99) {
                visibilityOrRendering = true;
            } else if (opcode == 100) {
                modelLightning = buffer.getSignedByte();
            } else if (opcode == 101) {
                modelShadowing = buffer.getSignedByte() * 5;
            } else if (opcode == 102) {
                headIcon = buffer.getUnsignedShort();
            } else if (opcode == 103) {
                degreesToTurn = buffer.getUnsignedShort();
            } else if (opcode == 106) {
                varBitChild = buffer.getUnsignedShort();

                if (varBitChild == 65535) {
                    varBitChild = -1;
                }

                configChild = buffer.getUnsignedShort();

                if (configChild == 65535) {
                    configChild = -1;
                }

                int length = buffer.getUnsignedByte();
                childrenIDs = new int[length + 1];

                for (int i = 0; i <= length; i++) {
                    childrenIDs[i] = buffer.getUnsignedShort();

                    if (childrenIDs[i] == 65535) {
                        childrenIDs[i] = -1;
                    }
                }
            } else if (opcode == 107) {
                disableRightClick = false;
            }
        } while (true);
    }

    public static void printDefinitionsForId(int mobId) {
        /*Print out Grain*/
        MobDefinition dump = MobDefinition.get(mobId);
        if (dump.name != null) {
            System.out.println("Dumping: " + dump.name);
        } else {
            System.out.println("MobDefinition.get(" + mobId + ").name == null");
        }
        System.out.println("combatlevel: " + dump.combatLevel);
        System.out.println("id: " + dump.id);
        if (dump.npcModels != null) {
            for (int i = 0; i < dump.npcModels.length; i++) {
                System.out.println("npcModels[" + i + "]: " + dump.npcModels[i]);
            }
        }
        if (dump.actions != null) {
            for (int i = 0; i < dump.actions.length; i++) {
                System.out.println("Action[" + i + "]: " + dump.actions[i]);
            }
        }
        System.out.println("degreesToTurn: " + dump.degreesToTurn);
        System.out.println("headIcon: " + dump.headIcon);
        System.out.println("npcSizeInSquares: " + dump.npcSizeInSquares);
        System.out.println("standAnimation: " + dump.standAnimation);
        System.out.println("walkAnimation: " + dump.walkAnimation);
        System.out.println("walkingBackwardsAnimation: " + dump.walkingBackwardsAnimation);
        System.out.println("walkLeftAnimation: " + dump.walkLeftAnimation);
        System.out.println("walkRightAnimation: " + dump.walkRightAnimation);
        if (dump.originalModelColours != null) {
            for (int i = 0; i < dump.originalModelColours.length; i++) {
                System.out.println("originalModelColours[" + i + "]: " + dump.originalModelColours[i]);
            }
        }
        if (dump.changedModelColours != null) {
            for (int i = 0; i < dump.changedModelColours.length; i++) {
                System.out.println("changedModelColours[" + i + "]: " + dump.changedModelColours[i]);
            }
        }
        if (dump.childrenIDs != null) {
            for (int i = 0; i < dump.childrenIDs.length; i++) {
                System.out.println("childrenIDs[" + i + "]: " + dump.changedModelColours[i]);
            }
        }
        System.out.println("configChild: " + dump.configChild);
        System.out.println("varBitChild: " + dump.varBitChild);
        System.out.println("modelLightning: " + dump.modelLightning);
        System.out.println("modelShadowing: " + dump.modelShadowing);
        System.out.println("drawYellowDotOnMap: " + dump.drawYellowDotOnMap);
        System.out.println("disableRightClick: " + dump.disableRightClick);
        System.out.println("visibilityOrRendering: " + dump.visibilityOrRendering);


    }



    private void setDefault() {
        walkRightAnimation = -1;
        varBitChild = -1;
        walkingBackwardsAnimation = -1;
        configChild = -1;
        combatLevel = -1;
        walkAnimation = -1;
        npcSizeInSquares = 1;
        headIcon = -1;
        standAnimation = -1;
        degreesToTurn = 32;
        walkLeftAnimation = -1;
        disableRightClick = true;
        scaleY = 128;
        drawYellowDotOnMap = true;
        scaleXZ = 128;
        visibilityOrRendering = false;
        rdc = 0;
        rdc2 = 0;
        rdc3 = 0;
        rs3 = false;
        osrs = false;

    }


    public double[] colorChange = null;
    public boolean rs3 = false;
    public boolean osrs = false;

}