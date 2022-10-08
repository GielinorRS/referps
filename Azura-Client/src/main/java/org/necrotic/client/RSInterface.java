package org.necrotic.client;

import org.necrotic.ColorConstants;
import org.necrotic.Configuration;
import org.necrotic.client.accounts.Account;
import org.necrotic.client.cache.Archive;
import org.necrotic.client.cache.definition.ItemDefinition;
import org.necrotic.client.cache.definition.MobDefinition;
import org.necrotic.client.entity.player.Player;
import org.necrotic.client.graphics.Sprite;
import org.necrotic.client.graphics.fonts.RSFontSystem;
import org.necrotic.client.graphics.fonts.TextClass;
import org.necrotic.client.graphics.fonts.TextDrawingArea;
import org.necrotic.client.graphics.rsinterface.ClientConstants;
import org.necrotic.client.graphics.rsinterface.CustomInterfaces;
import org.necrotic.client.graphics.rsinterface.SummoningInterfaceData;
import org.necrotic.client.io.ByteBuffer;
import org.necrotic.client.world.Model;

public class RSInterface {

    public static void scratchCardInterface(TextDrawingArea[] font) {
        final int STARTING_POINT = 25400;
        RSInterface main = addInterface(STARTING_POINT);
        addSpriteLoader(STARTING_POINT + 1, 1534);
        addText(STARTING_POINT + 2, "Golden Scratch Card", font, 2, 0xff8624, true, true);
        addText(STARTING_POINT + 3, "Scratch these cards", font, 0, 0xff8624, true, true);
        addText(STARTING_POINT + 4, "to have a chance to win OP REWARDS!", font, 0, 0xff8624, true, true);
        main.totalChildren(14);
        main.child(0, STARTING_POINT + 1, 105, 45);
        main.child(1, STARTING_POINT + 2, 273, 55);
        main.child(2, STARTING_POINT + 3, 273, 89);
        main.child(3, STARTING_POINT + 4, 273, 99);

        for (int i = 0; i < 3; i++) {
            addSpriteLoader(STARTING_POINT + 5 + i, 1512);
            main.child(4 + i, STARTING_POINT + 5 + i, 125 + (i * 100), 120);
        }

        addHoverButton(STARTING_POINT + 8, 1514, 90, 25, "Collect Prize", -1, STARTING_POINT + 9, 1); // 1059
        addHoveredButton(STARTING_POINT + 9, 1515, 90, 25, STARTING_POINT + 10); // 1060

        main.child(7, STARTING_POINT + 8, 227, 230);
        main.child(8, STARTING_POINT + 9, 227, 230);

        addText(STARTING_POINT + 11, "Collect Prize", font, 0, 0xff8624, false, true);

        main.child(9, STARTING_POINT + 11, 240, 237);

        addToItemGroup(STARTING_POINT + 12, 3, 1, 70, 0, false, false, null, false);

        main.child(10, STARTING_POINT + 12, 140, 128);

        for (int i = 0; i < 3; i++) {
            addScratchcardSprite(STARTING_POINT + 13 + i, 1513);
            main.child(11 + i, STARTING_POINT + 13 + i, 125 + (i * 100), 120);
        }

        //addSlider(STARTING_POINT + 16, 10, 75, 10, 615, 606);
        //  addText(STARTING_POINT + 17, "Radius: 10", font, 1, 0xFFFFFF, false, true);
        //main.child(14, STARTING_POINT + 16, 115, 256);
        //  main.child(14, STARTING_POINT + 17, 145, 236);
    }

    public static void addScratchcardSprite(int childId, int spriteId) {
        RSInterface rsi = interfaceCache[childId] = new RSInterface();
        rsi.id = childId;
        rsi.parentID = childId;
        rsi.type = 100;
        rsi.atActionType = 0;
        rsi.contentType = 0;
        Sprite spriteToCopy = Client.cacheSprite[spriteId];
        if (spriteToCopy != null) {
            rsi.unrevealedSprite = Sprite.copy(spriteToCopy);
        }
        rsi.width = rsi.unrevealedSprite.myWidth;
        rsi.height = rsi.unrevealedSprite.myHeight - 2;
    }

    public Sprite unrevealedSprite;


    public static RSInterface addHoverableConfigSprite(int id, int mainSprite,
                                                       int hoverSprite, boolean clickable, String tooltip, int configId, int configFrame) {
        //  System.out.println("Added hoverable sprite with param: " + clickable);
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 111;
        tab.atActionType = 5;
        //   System.out.println(tab.atActionType + " ->");
        tab.contentType = 0;
        tab.opacity = (byte) 0;
        tab.hoverable = true;
        tab.disabledSprite = Client.cacheSprite[mainSprite];
        tab.enabledSprite = Client.cacheSprite[hoverSprite];
        tab.width = tab.disabledSprite.myWidth;
        tab.height = tab.disabledSprite.myHeight;
        tab.tooltip = tooltip;
        tab.valueCompareType = new int[1];
        tab.requiredValues = new int[1];
        tab.valueCompareType[0] = 1;
        tab.requiredValues[0] = configId;
        tab.valueIndexArray = new int[1][3];
        tab.valueIndexArray[0][0] = 5;
        tab.valueIndexArray[0][1] = configFrame;
        tab.valueIndexArray[0][2] = 0;
        return tab;
    }

    public boolean hoverable;
    public boolean selected;


    public static void addProgressBar(int id, int width, int height, int currentPercent, int firstColor, int secondColor) {
        RSInterface rsi = addInterface(id);
        rsi.type = 287;
        rsi.width = width;
        rsi.height = height;
        rsi.progress = currentPercent;
        rsi.firstColor = firstColor;
        rsi.secondColor = secondColor;
    }

    protected int progress;
    protected int firstColor;
    protected int secondColor;

    private static void perkOverlays(TextDrawingArea[] tda) {
        int STARTING_POINT = 42400;
        RSInterface main = addInterface(STARTING_POINT);
        addSpriteLoader(STARTING_POINT + 1, 1521);
        addText(STARTING_POINT + 2, "0:00 min", tda, 0, ColorConstants.ORANGE, true, true);
        addText(STARTING_POINT + 3, "Double Slayer", tda, 0, ColorConstants.SNOW_WHITE, true, true);

        main.totalChildren(3);
        main.child(0, STARTING_POINT + 1, 10, 5);
        main.child(1, STARTING_POINT + 2, 23, 33);
        main.child(2, STARTING_POINT + 3, 23, 43);
    }


    public static void addToItemGroup(int id, int w, int h, int x, int y, boolean hasActions, boolean displayAmount, String[] actions, boolean displayExamine) {
        RSInterface widget = addInterface(id);
        widget.width = w;
        widget.height = h;
        widget.inv = new int[w * h];
        widget.invStackSizes = new int[w * h];
        widget.usableItemInterface = false;
        widget.invSpritePadX = x;
        widget.invSpritePadY = y;
        widget.spritesX = new int[20];
        widget.spritesY = new int[20];
        widget.sprites = new Sprite[20];
        // rsi.actions = new String[5];
        if (hasActions)
            widget.actions = actions;
        widget.type = 2;
        widget.displayExamine = displayExamine;
    }

    public boolean displayExamine = true;

    private static CustomInterfaces customInterfaces;

    public static CustomInterfaces getCustomInterfaces() {
        return customInterfaces;
    }

    public void addStreamBoss(TextDrawingArea[] tda) {
        int baseX = 7;
        int baseY = 7;
        int interfaceId = 31500;
        int index = 1;
        RSInterface mainInterface = addInterface(interfaceId);
        addSpriteLoader(interfaceId + index++, 1416);
        hoverButton(interfaceId + index++, 1238, 1239, "Close interface", 1, 16750623, "");
        RSInterface.addText(interfaceId + index++, "Lunite's Event Boss", tda, 3, 16777215, true);
        RSInterface.addText(interfaceId + index++, "Ice Archer", tda, 1, 16777215, false);
        addNpc(interfaceId + index++, 131);
        RSInterface.addText(interfaceId + index++, "Npc ID: 201", tda, 0, 16777215, false);
        RSInterface.addText(interfaceId + index++, "Health: 5m", tda, 0, 16777215, false);
        hoverButton(interfaceId + index++, 1417, 1418, "Change", 0, 13150820, "Change");
        hoverButton(interfaceId + index++, 1417, 1418, "Change", 0, 13150820, "Change");
        itemGroup(interfaceId + index++, 7, 5, 8, 12, 14484);
        (RSInterface.interfaceCache[interfaceId + index - 1]).actions = new String[]{"Remove 1", "Remove 5", "Remove 10", "Remove X", "Remove All"};
        RSInterface.addText(interfaceId + index++, "All the items above will be dropped to the ground.", tda, 0, 16777215, true);
        hoverButton(interfaceId + index++, 1417, 1418, "Change", 0, 13150820, "Change");
        hoverButton(interfaceId + index++, 1415, 1414, "Spawn Boss", 1, 13150820, "Spawn Boss");
        mainInterface.totalChildren(index - 1);
        index = 0;
        mainInterface.child(index++, interfaceId + index, baseX + 1, baseY + 2);
        mainInterface.child(index++, interfaceId + index, baseX + 470, baseY + 11);
        mainInterface.child(index++, interfaceId + index, 244 + baseX, 11 + baseY);
        mainInterface.child(index++, interfaceId + index, 342 + baseX, 44 + baseY);
        mainInterface.child(index++, interfaceId + index, 323 + baseX, 88 + baseY);
        mainInterface.child(index++, interfaceId + index, 322 + baseX, 226 + baseY);
        mainInterface.child(index++, interfaceId + index, 322 + baseX, 249 + baseY);
        mainInterface.child(index++, interfaceId + index, 415 + baseX, 224 + baseY);
        mainInterface.child(index++, interfaceId + index, 415 + baseX, 247 + baseY);
        mainInterface.child(index++, interfaceId + index, 20 + baseX, 45 + baseY);
        mainInterface.child(index++, interfaceId + index, 155 + baseX, 272 + baseY);
        mainInterface.child(index++, interfaceId + index, 130 + baseX, 290 + baseY);
        mainInterface.child(index++, interfaceId + index, 328 + baseX, 280 + baseY);
    }

    public static void hoverButton(int id, int regularSpriteId, int hoveredSpriteId, String hoverTooltip, int font, int color, String buttonText) {
        RSInterface tab = addInterface(id);
        tab.tooltip = hoverTooltip;
        tab.atActionType = 1;
        tab.type = 42;
        tab.disabledSprite = Client.cacheSprite[regularSpriteId];
        tab.enabledSprite = Client.cacheSprite[hoveredSpriteId];
        tab.width = tab.disabledSprite.myWidth;
        tab.height = tab.disabledSprite.myHeight;
        tab.messageOffsetX = tab.width / 2;
        tab.messageOffsetY = tab.height / 2 + 5;
        tab.message = buttonText;
        tab.rsFont = getFont(font);
        tab.textDrawingAreas = getOldFont(font);
        tab.enabledColor = color;
        tab.centerText = true;
    }

    public static void hoverButton(int id, int spriteId, int hoverSprite, int width, int height, String hoverTooltip) {
        RSInterface tab = addInterface(id);
        tab.tooltip = hoverTooltip;
        tab.atActionType = 1;
        tab.type = 42;
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.enabledSprite = Client.cacheSprite[hoverSprite];
        tab.messageOffsetX = tab.width / 2;
        tab.messageOffsetY = (tab.height / 2) + 5;
        tab.disabledSpriteId = spriteId;
        tab.enabledSpriteId = hoverSprite;
        tab.width = width;
        tab.height = height;
    }


    public static void hoverButton(int id, int regularSpriteId, int hoveredSpriteId, String hoverTooltip) {
        hoverButton(id, regularSpriteId, hoveredSpriteId, hoverTooltip, 0, 0, "");
    }

    public static RSFontSystem getFont(int fontType) {
        switch (fontType) {
            case 0:
            default:
                return Client.instance.newSmallFont;
            case 1:
                return Client.instance.newRegularFont;
            case 2:
                return Client.instance.newBoldFont;
            case 3:
                return Client.instance.newFancyFont;
        }

    }

    public static TextDrawingArea getOldFont(int fontType) {
        switch (fontType) {
            case 0:
            default:
                return Client.instance.smallText;
            case 1:
                return Client.instance.normalText;
            case 2:
                return Client.instance.chatTextDrawingArea;
            case 3:
                return Client.instance.aTextDrawingArea_1273;
        }
    }

    public static void itemGroup(int id, int w, int h, int x, int y, int... itemId) {
        RSInterface rsi = addInterface(id);
        rsi.width = w;
        rsi.height = h;
        rsi.inv = new int[w * h];
        rsi.invStackSizes = new int[w * h];
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.invSpritePadX = x;
        rsi.invSpritePadY = y;
        rsi.spritesX = new int[20];
        rsi.spritesY = new int[20];
        rsi.sprites = new Sprite[20];
        rsi.type = 2;
        if (itemId != null && itemId.length > 0)
            for (int i = 0; i < rsi.inv.length; i++) {
                rsi.inv[i] = itemId[0] + 1;
                rsi.invStackSizes[i] = 1;
            }
    }

    //sec lets test the client thing
    public static void addNewCloseButton(TextDrawingArea[] tda) {
        hoverButton(14650, tda, 1238, 1239, "Close", 0, 16750623, "");
    }

    public static void killTracker(TextDrawingArea[] tda) {
        RSInterface main = addInterface(33300);
        addSpriteLoader(33301, 1400);

        hoverButton(33302, tda, 1401, 1402, "Show Monsters", 0, 16750623, "@or1@Show Monsters");
        hoverButton(33303, tda, 1401, 1402, "Show Bosses", 0, 16750623, "@or1@Show Bosses");
        hoverButton(33304, tda, 1401, 1402, "Show Others", 0, 16750623, "@or1@Show Others");
        hoverSpriteWithText(33305, tda, 1405, 1406, 0, 0, "");
        hoverSpriteWithText(33306, tda, 1403, 1404, 0, 0, ""); //2 different buttons
//these were diff btw not both supposed to be 1405 i guess
        addText(33307, "Your personal kill tracker", tda, 2, 16750623, false, true);
        addText(33308, "Currently Viewing: Chaos Elemental", tda, 1, 16750623, true, true);
        addText(33309, "Chaos Elemental kills: 15", tda, 1, 16750623, true, true);
        addText(33310, "Total NPC Kills: 15,521", tda, 0, 16750623, false, true);
        addNpc(33311, 50);
        addNewCloseButton(tda);


        main.totalChildren(13);
        main.child(0, 33301, 10, 10);
        main.child(1, 33302, 137, 50);
        main.child(2, 33303, 239, 50);
        main.child(3, 33304, 342, 50);
        main.child(4, 33305, 142, 115);
        main.child(5, 33306, 15, 291);

        main.child(6, 33307, 169, 21);
        main.child(7, 33308, 285, 95);
        main.child(8, 33309, 285, 283);
        main.child(9, 33310, 19, 298);
        main.child(10, 33311, 214, 134);
        main.child(11, 33320, 17, 52);
        main.child(12, 14650, 419, 19);

        RSInterface nameList = addInterface(33320);
        nameList.totalChildren(100);
        nameList.width = 106;
        nameList.height = 237;
        nameList.scrollMax = 1500;
        int y = 3;
        for (int i = 0; i < 100; i++) {
            hoverButton(33321 + i, tda, 1401, 1402, "Select", 1, 16750623, "");
            nameList.child(i, 33321 + i, 0, y);
            y += 29;
        }
    }

    public int messageOffsetX;

    public int messageOffsetY;

    public boolean widgetActive;

    public static void hoverSpriteWithText(int id, TextDrawingArea[] tda, int disabledSprite, int hoverSprite, int font, int color, String buttonText) {
        RSInterface tab = addInterface(id);
        tab.type = 42;
        tab.disabledSprite = Client.cacheSprite[disabledSprite];
        tab.enabledSprite = Client.cacheSprite[hoverSprite];
        tab.width = tab.disabledSprite.myWidth;
        tab.height = tab.disabledSprite.myHeight;
        tab.messageOffsetX = tab.width / 2;
        tab.messageOffsetY = (tab.height / 2) + 5;
        tab.message = buttonText;
        tab.textDrawingAreas = tda[font];
        tab.disabledColor = color;
        tab.centerText = true;
        tab.widgetActive = true;
    }

    public static void hoverButton(int id, TextDrawingArea[] tda, int disabledSprite, int hoverSprite, String hoverTooltip, int idx, int color, String buttonText) {
        RSInterface tab = addInterface(id);
        tab.tooltip = hoverTooltip;
        tab.atActionType = 1;
        tab.type = 42;
        tab.disabledSprite = Client.cacheSprite[disabledSprite];
        tab.enabledSprite = Client.cacheSprite[hoverSprite];
        tab.width = tab.disabledSprite.myWidth;
        tab.height = tab.disabledSprite.myHeight;
        tab.messageOffsetX = tab.width / 2;
        tab.messageOffsetY = (tab.height / 2) + 5;
        tab.message = buttonText;
        tab.textDrawingAreas = tda[idx];
        tab.disabledColor = color;
        tab.centerText = true;
        tab.widgetActive = true;
    }

    public static void goodiebagInterface(TextDrawingArea[] tda) { // open urcache,.
        final int STARTING_POINT = 49200;
        RSInterface main = addInterface(STARTING_POINT);

        final int ROWS = 5;
        final int COLUMNS = 4;
        addSpriteLoader(STARTING_POINT + 1, 1391);
        addText(STARTING_POINT + 2, "Lunite's Goodiebag", tda, 2, ColorConstants.ORANGE, false, true);
        addText(STARTING_POINT + 3, "Select a number\\nand then click\\n on @whi@Claim prize!", tda, 2, ColorConstants.ORANGE, false, true);
        addHoverButtonWSpriteLoader(STARTING_POINT + 4, 1394, 88, 36, "Claim Prize!", -1, STARTING_POINT + 5, 1);
        addHoveredImageWSpriteLoader(STARTING_POINT + 5, 1395, 88, 36, STARTING_POINT + 6);
        addText(STARTING_POINT + 7, "Claim Prize", tda, 2, ColorConstants.ORANGE, false, true);
        addText(STARTING_POINT + 8, "20 Total Prizes!", tda, 2, ColorConstants.ORANGE, false, true);
        addText(STARTING_POINT + 10, "Top Prize's! - 1\\nLegendary Prize's! - 2\\nUltimate Prize's! - 5\\nGreat Prize's! - 6\\nGood Prize's! - 6", tda, 1, ColorConstants.ORANGE, true, false);
        addCloseButtonSmall(STARTING_POINT + 71, STARTING_POINT + 72, STARTING_POINT + 73);
        main.totalChildren(12 + ((ROWS * COLUMNS) * 2));
        main.child(0, STARTING_POINT + 1, 75, 10);
        main.child(1, STARTING_POINT + 2, 145, 20);
        main.child(2, STARTING_POINT + 3, 305, 55);
        main.child(3, STARTING_POINT + 4, 320, 115);
        main.child(4, STARTING_POINT + 5, 320, 115);
        main.child(5, STARTING_POINT + 7, 330, 119);
        main.child(6, STARTING_POINT + 8, 315, 165);
        main.child(7, STARTING_POINT + 9, 315, 180);
        main.child(8, STARTING_POINT + 10, 367, 185);
        main.child(49, STARTING_POINT + 70, 94, 65);
        main.child(50, STARTING_POINT + 71, 410, 19);
        main.child(51, STARTING_POINT + 72, 410, 19);

        RSInterface line = addInterface(STARTING_POINT + 9);
        line.type = 3;
        line.width = 105;
        line.height = 1;
        line.color = ColorConstants.YELLOW;

        int childStart = 9;
        int yPos = 60;
        for (int y = 0; y < ROWS; y++) {
            int xPos = 85;
            for (int x = 0; x < COLUMNS; x++) {
                int id = x + y * COLUMNS;
                addButtonWSpriteLoader(STARTING_POINT + 11 + id, 1392, "Choose"); // this is why i needed the order so i can remember easily
                setBounds(STARTING_POINT + 11 + id, xPos, yPos, childStart + id, main);
                xPos += 54;
            }
            yPos += 50;
        }

        int xPos1 = 107;
        int yPos1 = 74;
        int childStart1 = 29;
        for (int i = 1; i <= 20; i++) {
            addText(STARTING_POINT + 32 + i, String.valueOf(i), tda, 2, ColorConstants.ORANGE, true, false);
            main.child(childStart1 + (i - 1), STARTING_POINT + 32 + i, xPos1, yPos1);
            xPos1 += 54;
            if (i % 4 == 0) {
                xPos1 = 105;
                yPos1 += 50;
            }
        }

        addToItemGroup(STARTING_POINT + 70, 4, 5, 22, 18, true, new String[]{null, null, null, null, null});


    }

    private static void ultimateIronmanBank(TextDrawingArea[] tda) {
        RSInterface main = addInterface(48700);
        addSpriteLoader(48701, 1390);
        addBigButtonSmall(48702, 48703, 48704);
        addText(48705, "Ultimate Ironman Collection box", tda, 2, ColorConstants.SNOW_WHITE, false, true);
        main.totalChildren(5);
        main.child(0, 48701, 80, 40);
        main.child(1, 48720, 0, 78);
        main.child(2, 48702, 376, 47);
        main.child(3, 48703, 376, 47);
        main.child(4, 48705, 130, 49);
        //its the money pouch
        RSInterface items = addInterface(48720);
        items.width = 380;
        items.height = 222;
        items.scrollMax = 750;

        items.totalChildren(1);
        items.child(0, 48721, 91, 0);
        addToItemGroup(48721, 7, 10, 10, 7, true, new String[]{"Withdraw 1", "Withdraw all", null, null, null});

    }

    public static void customCollectionLog(TextDrawingArea[] tda) {
        final int STARTING_POINT = 30360;
        RSInterface main = addInterface(STARTING_POINT);
        addSpriteLoader(STARTING_POINT + 1, 1409);
        addHoverButtonWSpriteLoader(STARTING_POINT + 2, 1410, 159, 29, "Search NPC", -1, STARTING_POINT + 3, 1);
        addHoveredImageWSpriteLoader(STARTING_POINT + 3, 1411, 159, 29, STARTING_POINT + 4);
        addText(STARTING_POINT + 5, "NPC's", tda, 2, ColorConstants.ORANGE, false, true);
        addText(STARTING_POINT + 6, "Search NPC", tda, 1, ColorConstants.ORANGE, false, true);
        addNpc(STARTING_POINT + 7, 1265);
        addText(STARTING_POINT + 8, "NPC Killcount", tda, 2, ColorConstants.ORANGE, false, true);
        addText(STARTING_POINT + 9, "Killcount: 159", tda, 1, ColorConstants.ORANGE, false, true);
        addText(STARTING_POINT + 10, "Collection Log", tda, 2, ColorConstants.ORANGE, false, true);
        addCloseButtonSmall(STARTING_POINT + 11, STARTING_POINT + 12, STARTING_POINT + 13);
        addText(STARTING_POINT + 14, "Collection Log", tda, 2, ColorConstants.ORANGE, false, true);
        addToItemGroup(STARTING_POINT + 15, 4, 5, 6, 6, true, new String[]{null, null, null, null, null});
        //RSInterface.interfaceCache[STARTING_POINT + 15].hideStackSize = true;
        main.totalChildren(14);
        main.child(0, STARTING_POINT + 1, 10, 10);
        main.child(1, STARTING_POINT + 2, 18, 278);
        main.child(2, STARTING_POINT + 3, 18, 278);
        main.child(3, STARTING_POINT + 5, 75, 51);
        main.child(4, STARTING_POINT + 6, 77, 285);
        main.child(5, STARTING_POINT + 7, 191, 85);
        main.child(6, STARTING_POINT + 8, 215, 211);
        main.child(7, STARTING_POINT + 9, 217, 258);
        main.child(8, STARTING_POINT + 10, 367, 52);
        main.child(9, STARTING_POINT + 11, 465, 20);
        main.child(10, STARTING_POINT + 12, 465, 20);
        main.child(11, STARTING_POINT + 14, 214, 20);
        main.child(12, STARTING_POINT + 30, 0, 70);
        main.child(13, STARTING_POINT + 15, 339, 77);

        RSInterface scroll = addInterface(STARTING_POINT + 30);
        scroll.totalChildren(300);
        scroll.width = 157;
        scroll.height = 204;
        scroll.scrollMax = 1100;

        int spriteY = 0;
        for (int i = 0; i < 150; i++) {
            addSpriteLoader(STARTING_POINT + 31 + i, i % 2 != 0 ? 1412 : 1413);
            scroll.child(i, STARTING_POINT + 31 + i, 19, spriteY);
            spriteY += 22;
        }
        int textY = 4;
        for (int i = 0; i < 150; i++) {
            addText(STARTING_POINT + 200 + i, "", fonts, 1, ColorConstants.ORANGE, false, true, ColorConstants.WHITE, "Select", 130);
            //public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean center,
            //boolean shadow, int hoverColour, String tooltip, int widthHover) {
            scroll.child(150 + i, STARTING_POINT + 200 + i, 23, textY);
            textY += 22;
        }

    }

    public static void addHoverableText(int id, String text, TextDrawingArea tda[], int idx, int color,
                                        int width, int height) {
        RSInterface Tab = addTabInterface(id);
        Tab.parentID = id;
        Tab.id = id;
        Tab.atActionType = 1;
        Tab.type = 4;
        Tab.width = width;
        Tab.height = height;
        Tab.contentType = 0;
        Tab.hoverType = 0;
        Tab.centerText = false;
        Tab.textDrawingAreas = tda[idx];
        Tab.message = text;
        Tab.disabledColor = color;
    }

    //add hoverable text method i copy pasted it and removed/renamed heaps of variables so might be it. sec
    public static void addHoverableText(int id, String text, String tooltip, TextDrawingArea tda[], int idx,
                                        boolean center, boolean textShadowed, int width, int disabledColor, int enabledColor) {
        RSInterface rsinterface = addInterface(id);
        rsinterface.id = id;
        rsinterface.parentID = id;
        rsinterface.type = 4;
        rsinterface.atActionType = 1;
        rsinterface.width = width;
        rsinterface.height = 13;
        rsinterface.contentType = 0;
        rsinterface.opacity = 0;
        rsinterface.hoverType = -1;
        rsinterface.centerText = center;
        rsinterface.textShadow = textShadowed;
        rsinterface.textDrawingAreas = tda[idx];
        rsinterface.message = text;
        rsinterface.inputText = "";
        rsinterface.disabledColor = disabledColor;
        rsinterface.enabledColor = 0;
        rsinterface.disabledMouseOverColor = enabledColor;
        rsinterface.enabledMouseOverColor = 0;
        rsinterface.tooltip = tooltip;
    }

    static void bestItems(TextDrawingArea[] tda) {
        int interID = 100000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 2;
        int y = 20;
        tab.totalChildren(25);
        addSpriteLoader(id, 1425);
        tab.child(c++, id++, 0 + x, 0 + y);
        addHoverButtonWSpriteLoader(id, 1238, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 486 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1239, 16, 16, id + 1);
        tab.child(c++, id++, 486 + x, 3 + y);
        id++;
        addText(id, "Highest Bonuses", tda, 2, 16750623, true, true);
        tab.child(c++, id++, 239 + x, 4 + y);
        addText(id, "Rank", tda, 1, 16750623, true, true);
        tab.child(c++, id++, 172 + x, 29 + y);
        addText(id, "Bonus", tda, 1, 16750623, true, true);
        tab.child(c++, id++, 227 + x, 29 + y);
        addText(id, "Item", tda, 1, 16750623, true, true);
        tab.child(c++, id++, 350 + x, 29 + y);
        y -= 5;
        addText(id, "Attack bonuses", tda, 2, 16746752, false, true);
        tab.child(c++, id++, 15 + x, 30 + y);
        y += 16;
        int i;
        for (i = 0; i < 5; i++) {
            addClickableText(id, "Check something", "Select", tda, 1, 16746752, false, true, 130);
            tab.child(c++, id++, 20 + x, 30 + y);
            y += 16;
        }
        y += 3;
        addText(id, "Defence bonuses", tda, 2, 16746752, false, true);
        tab.child(c++, id++, 15 + x, 30 + y);
        y += 16;
        for (i = 0; i < 5; i++) {
            addClickableText(id, "Check something", "Select", tda, 1, 16746752, false, true, 130);
            tab.child(c++, id++, 20 + x, 30 + y);
            y += 16;
        }
        y += 3;
        addText(id, "Other bonuses", tda, 2, 16746752, false, true);
        tab.child(c++, id++, 15 + x, 30 + y);
        y += 16;
        for (i = 0; i < 3; i++) {
            addClickableText(id, "Check something", "Select", tda, 1, 16746752, false, true, 130);
            tab.child(c++, id++, 20 + x, 30 + y);
            y += 16;
        }
        y = 20;
        tab.child(c++, 100050, 152 + x, 47 + y);
        tab.child(c++, 100600, x + 448, y + 24);
        interID = 100050;
        RSInterface list = addTabInterface(interID);
        list.totalChildren(450);
        list.height = 240;
        list.width = 286;
        list.scrollMax = 4000;
        id = interID + 1;
        y = 0;
        c = 0;
        int j;
        for (j = 0; j < 50; j++) {
            addSpriteLoader(id, 1426);
            list.child(c++, id++, 0, 0 + y);
            y += 80;
        }
        y = 12;
        for (j = 0; j < 100; j++) {
            addText(id, "#" + (j + 1), 16751360, true, true, 100, fonts, 1);
            list.child(c++, id++, 20, y);
            addText(id, "+4000", 16751360, true, true, 100, fonts, 1);
            list.child(c++, id++, 75, y);
            addText(id, "Name", 16751360, false, true, 100, fonts, 1);
            list.child(c++, id++, 150, y);
            dropGroup(id, 1, 1, 1, 1);
            list.child(c++, id++, 108, y - 6);
            y += 40;
        }

        interID = 100600;
        RSInterface list2 = addTabInterface(interID);
        list2.totalChildren(13);
        list2.height = 264;
        list2.width = 36;
        list2.scrollMax = 360;
        id = interID + 1;
        y = 0;
        c = 0;
        int sprite = 1621;
        for (j = 0; j < 13; j++) {
            addConfigButtonWSpriteLoader(id++, interID, sprite++, sprite++, 28, 28, "Select Slot", j, 5, 4187);
            list2.child(c++, id-1, 8, y);
            y += 30;
        }
    }

    public static void dropGroup(int id, int w, int h, int x, int y) {
        RSInterface rsi = addInterface(id);
        rsi.width = w;
        rsi.height = h;
        rsi.inv = new int[w * h];
        rsi.invStackSizes = new int[w * h];
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.invSpritePadX = x;
        rsi.invSpritePadY = y;
        rsi.spritesX = new int[20];
        rsi.spritesY = new int[20];
        rsi.sprites = new Sprite[20];
        rsi.type = 2;

        rsi.inv[0] = 6200;
        rsi.invStackSizes[0] = 2;
    }

    //they should not be seperate interfaces.
    private static void effectInterface(TextDrawingArea[] tda) {//wtf did u do with it lol and why are there several, it should all be in 1 interface
        RSInterface main = addInterface(48300);
        addSpriteLoader(48301, 1386);
        addText(48302, "000 Minutes", tda, 0, ColorConstants.ORA, false, true);
        addText(48303, "Super Ovl", tda, 0, ColorConstants.SNOW_WHITE, false, true);
        main.totalChildren(3);
        main.child(0, 48301, 35 + 100 - 8 + 330 + 5, 275 + 8 + 5 - 170);
        main.child(1, 48302, 11 + 102 + 330 + 5, 305 + 5 + 5 - 170);
        main.child(2, 48303, 11 + 106 + 330 + 5, 305 - 5 + 5 - 170);
    }

    private static void effectInterface2(TextDrawingArea[] tda) {
        RSInterface main = addInterface(48400);
        addSpriteLoader(48401, 1387);
        addText(48402, "000 Minutes", tda, 0, ColorConstants.ORA, false, true);
        addText(48403, "Exp Bonus", tda, 0, ColorConstants.SNOW_WHITE, false, true);
        main.totalChildren(3);
        main.child(0, 48401, 35 - 8 + 330 + 100 + 5, 275 + 8 + 5 - 135);
        main.child(1, 48402, 11 + 330 + 102 + 5, 305 + 5 + 5 - 135);
        main.child(2, 48403, 11 + 330 + 106 + 5, 305 - 5 + 5 - 135);
    }

    private static void effectInterface3(TextDrawingArea[] tda) {
        RSInterface main = addInterface(48500);
        addSpriteLoader(48501, 1388);
        addText(48502, "(::opensale)", tda, 0, ColorConstants.ORA, false, true);
        addText(48503, "Active Sale", tda, 0, ColorConstants.SNOW_WHITE, false, true);

        main.totalChildren(3);
        main.child(0, 48501, 35 + 200 - 8 + 220, 275 + 8 + 5);
        main.child(1, 48502, 11 + 202 + 220, 305 + 5 + 5);
        main.child(2, 48503, 11 + 206 + 220, 305 - 5 + 5);
    }

    private static void startInterface(TextDrawingArea[] tda) { // thats java conventions
        RSInterface main = addInterface(48550);
        addSpriteLoader(48551, 1389);
        addText(48552, "Welcome Starter Interface", tda, 2, ColorConstants.ORA, false, true);
        addText(48553, "Starter Hub", tda, 2, ColorConstants.ORA, false, true);
        addText(48554, "These are quick teleports and -", tda, 0, ColorConstants.ORA, false, true);
        addText(48555, "Guides to help you out. We strive ", tda, 0, ColorConstants.ORA, false, true);
        addText(48556, "To make it better for new players.", tda, 0, ColorConstants.ORA, false, true);
        addText(48557, "If you need more assistance dont", tda, 0, ColorConstants.ORA, false, true);
        addText(48558, "Hesitate to ask the community,", tda, 0, ColorConstants.ORA, false, true);
        addText(48559, "We are a very friendly server.", tda, 0, ColorConstants.ORA, false, true);

        addHoverButtonWSpriteLoader(48560, 1367, 133, 23, "Teleport", -1, 48561, 1);
        addHoveredImageWSpriteLoader(48561, 1368, 133, 23, 48562);
        addText(48563, "<img=18>Starter Progression", tda, 0, ColorConstants.ORANGE, false, true);

        addHoverButtonWSpriteLoader(48564, 1367, 133, 23, "Teleport", -1, 48565, 1);
        addHoveredImageWSpriteLoader(48565, 1368, 133, 23, 48566);
        addText(48567, "Melee Teleport", tda, 0, ColorConstants.YELLOW, false, true);

        addHoverButtonWSpriteLoader(48568, 1367, 133, 23, "Teleport", -1, 48569, 1);
        addHoveredImageWSpriteLoader(48569, 1368, 133, 23, 48570);
        addText(48571, "Ranged Teleport", tda, 0, ColorConstants.YELLOW, false, true);

        addHoverButtonWSpriteLoader(48572, 1367, 133, 23, "Teleport", -1, 48573, 1);
        addHoveredImageWSpriteLoader(48573, 1368, 133, 23, 48574);
        addText(48575, "Magic Teleport", tda, 0, ColorConstants.YELLOW, false, true);

        addHoverButtonWSpriteLoader(48576, 1367, 133, 23, "Teleport", -1, 48577, 1);
        addHoveredImageWSpriteLoader(48577, 1368, 133, 23, 48578);
        addText(48579, "Npc Tier list", tda, 0, ColorConstants.YELLOW, false, true);

        addHoverButtonWSpriteLoader(48580, 1367, 133, 23, "Teleport", -1, 48581, 1);
        addHoveredImageWSpriteLoader(48581, 1368, 133, 23, 48582);
        addText(48583, "Penguin Minigame", tda, 0, ColorConstants.YELLOW, false, true);

        //
        addHoverButtonWSpriteLoader(48584, 1369, 76, 32, "Open Link", -1, 48585, 1);
        addHoveredImageWSpriteLoader(48585, 1370, 76, 32, 48586);
        addText(48587, "Starter guide", tda, 0, ColorConstants.YELLOW, false, true);

        addHoverButtonWSpriteLoader(48588, 1369, 76, 32, "Open Link", -1, 48589, 1);
        addHoveredImageWSpriteLoader(48589, 1370, 76, 32, 48590);
        addText(48591, "Price guide", tda, 0, ColorConstants.YELLOW, false, true);

        addHoverButtonWSpriteLoader(48592, 1369, 76, 32, "Open Link", -1, 48593, 1);
        addHoveredImageWSpriteLoader(48593, 1370, 76, 32, 48594);
        addText(48595, "Slayer Guide", tda, 0, ColorConstants.YELLOW, false, true);

        addText(48596, "- Quick Teleports -", tda, 2, ColorConstants.ORA, false, true);
        addText(48597, "- Useful Links -", tda, 2, ColorConstants.ORA, false, true);
        addButtonWSpriteLoader(48598, 714, "Close Window", 16, 16);
        main.totalChildren(39);
        main.child(0, 48551, 10, 10);
        //text
        main.child(1, 48552, 160, 18);
        main.child(2, 48553, 95 + 40, 75);
        main.child(3, 48554, 90, 75 + 20);
        main.child(4, 48555, 90, 75 + 30);
        main.child(5, 48556, 90, 75 + 40);
        main.child(6, 48557, 90, 75 + 50);
        main.child(7, 48558, 90, 75 + 60);
        main.child(8, 48559, 90, 75 + 70);
        //buttons training
        main.child(9, 48560, 296, 65);
        main.child(10, 48561, 296, 65);
        main.child(11, 48563, 310, 70);
        //buttons melee
        main.child(12, 48564, 296, 65 + 30);
        main.child(13, 48565, 296, 65 + 30);
        main.child(14, 48567, 322, 70 + 30);
        //buttons ranged
        main.child(15, 48568, 296, 65 + 30 + 30);
        main.child(16, 48569, 296, 65 + 30 + 30);
        main.child(17, 48571, 322, 70 + 30 + 30);
        //buttons magic
        main.child(18, 48572, 296, 65 + 30 + 30 + 30);
        main.child(19, 48573, 296, 65 + 30 + 30 + 30);
        main.child(20, 48575, 322, 70 + 30 + 30 + 30);
        //buttons moneymaking
        main.child(21, 48576, 296, 65 + 30 + 30 + 30 + 30);
        main.child(22, 48577, 296, 65 + 30 + 30 + 30 + 30);
        main.child(23, 48579, 322, 70 + 30 + 30 + 30 + 30);
        //buttons penguins
        main.child(24, 48580, 296, 65 + 30 + 30 + 30 + 30 + 30);
        main.child(25, 48581, 296, 65 + 30 + 30 + 30 + 30 + 30);
        main.child(26, 48583, 322, 70 + 30 + 30 + 30 + 30 + 30);
        //buttons
        main.child(27, 48584, 30, 205 + 16);
        main.child(28, 48585, 30, 205 + 16);
        main.child(29, 48587, 35, 215 + 16);
        //buttons
        main.child(30, 48588, 30 + 80, 205 + 16);
        main.child(31, 48589, 30 + 80, 205 + 16);
        main.child(32, 48591, 35 + 80, 215 + 16);
        //buttons
        main.child(33, 48592, 30 + 80 + 80, 205 + 16);
        main.child(34, 48593, 30 + 80 + 80, 205 + 16);
        main.child(35, 48595, 35 + 80 + 80, 215 + 16);
        //links
        main.child(36, 48596, 300, 47);
        main.child(37, 48597, 90, 200);
        main.child(38, 48598, 459, 20);
    }

    private static void mysteryBoxViewer(TextDrawingArea[] tda) {
        RSInterface main = addInterface(48030);
        addSpriteLoader(48031, 1383);
        addText(48032, "Common (55%)", tda, 1, 0x92d397, true, true);
        addText(48033, "Uncommon (35%)", tda, 1, 0xd3c692, true, true);
        addText(48034, "Very Rare (5%)", tda, 1, 0xbb92d3, true, true);
        addText(48035, "Mbox Name", tda, 1, 0xff8624, true, true);


        hoverButton(48036, 1457, 1458, "Open", 2, 0xff8624, "Open");
        addText(48037, "Mystery Box Opener", tda, 2, 0xff8624, true, true);


        hoverButton(48039, 1457, 1458, "Open All", 2, 0xff8624, "Open All");
        addText(48040, "", tda, 0, 0xff8624, true, true);

        //addHoverButtonWSpriteLoader(48036, 1384, 90, 25, "Open", -1, 48037, 1);
       // addHoveredImageWSpriteLoader(48037, 1385, 90, 25, 48038);

       // addHoverButtonWSpriteLoader(48039, 1384, 90, 25, "Open All", -1, 48040, 1);
       // addHoveredImageWSpriteLoader(48040, 1385, 90, 25, 48041);

        addText(48042, "", tda, 0, 0xff8624, true, true);
        addText(48043, "", tda, 0, 0xff8624, true, true);
        addButtonWSpriteLoader(48044, 714, "Close Window", 16, 16);

        //	addText(48044, "Mystery Box Interface", tda, 1, ColorConstants.YELLOW, true, true);
        addToItemGroup(48045, 1, 1, 1, 1, true, new String[]{null, null, null, null, null});
        main.totalChildren(16);
        int x = 20, y = 32;
        main.child(0, 48031, x + 0, y + 0);
        main.child(1, 48050, x + 12, y + 90);
        main.child(2, 48060, x + 163, y + 90);
        main.child(3, 48070, x + 314, y + 90);

        main.child(4, 48032, x + 83, y + 72);
        main.child(5, 48033, x + 234, y + 72);
        main.child(6, 48034, x + 385, y + 72);

        main.child(7, 48035, x + 102, y + 37);

        main.child(9, 48037, x + 235, y + 4);

        main.child(8, 48036, x + 179, y + 31);
        main.child(10, 48039, x + 323, y + 31);

        main.child(11, 48040, x + 386, y + 65);
        main.child(12, 48042, x + 330, y + 72);
        main.child(13, 48043, x + 431, y + 72);

        main.child(14, 48044, x + 447, y + 4);

        main.child(15, 48045, x + 13, y + 28);

        RSInterface commonScroll = addInterface(48050);
        commonScroll.width = 143 - 16;
        commonScroll.height = 165;
        commonScroll.scrollMax = 400;
        addToItemGroup(48051, 3, 100, 10, 1, true, new String[]{null, null, null, null, null});
        commonScroll.totalChildren(1);
        commonScroll.child(0, 48051, 3, 2);
        RSInterface mediumScroll = addInterface(48060);
        mediumScroll.width = 143 - 16;
        mediumScroll.height = 165;
        mediumScroll.scrollMax = 500;
        mediumScroll.totalChildren(1);
        mediumScroll.child(0, 48061, 3, 2);
        addToItemGroup(48061, 3, 50, 10, 1, true, new String[]{null, null, null, null, null});
        RSInterface rareScroll = addInterface(48070);
        rareScroll.width = 143 - 16;
        rareScroll.height = 165;
        rareScroll.scrollMax = 500;
        rareScroll.totalChildren(1);
        rareScroll.child(0, 48071, 3, 2);
        addToItemGroup(48071, 3, 50, 10, 1, true, new String[]{null, null, null, null, null});
    }


    public static void raidInterface(TextDrawingArea[] tda) {
        RSInterface main = addInterface(29050);
        addTransparentSpriteWSpriteLoader1(29051, 1363, 135);
        addTransparentSpriteWSpriteLoader1(29052, 1364, 135);
        //addSpriteLoader(29051, 1363);
        //addSpriteLoader(29052, 1364);
        addText(29053, "Name's Group", tda, 1, ColorConstants.WHITE, true, false);
        addText(29054, "Host: name", tda, 1, ColorConstants.WHITE, true, false);
        addText(29055, "Players", tda, 1, ColorConstants.WHITE, true, false);
        addText(29056, "::createparty - @whi@To create your own party", tda, 0, ColorConstants.GREEN, true, false);
        addText(29057, "::deleteparty -  @whi@To delete your party", tda, 0, ColorConstants.ORANGE, true, false);
        addText(29058, "::requestparty or rightclick -  @whi@to invite players to your party", tda, 0, ColorConstants.ORANGE, true, false);
        addText(29059, "::startraid -  @whi@to start raid", tda, 0, ColorConstants.YELLOW, true, false);
        main.totalChildren(19);
        main.child(0, 29051, 15, 35); // its open somewhere. hmm
        main.child(1, 29052, 15, 82);
        main.child(2, 29053, 77, 40);
        main.child(3, 29054, 77, 62);
        main.child(4, 29055, 77, 88);//
        main.child(5, 29056, 300, 220 + 8);//test
        main.child(6, 29057, 300, 240 + 8);//test
        main.child(7, 29058, 300, 260 + 8);//test
        main.child(8, 29059, 300, 280 + 8);//test

        int childStart = 9;
        int yPos = 111;
        for (int i = 0; i < 10; i++) {
            addText(29095 + i, "", tda, 1, ColorConstants.WHITE, true, false);
            main.child(childStart, 29095 + i, 75, yPos);
            childStart++;
            yPos += 20;
        }
    }

    public static void raidRewardInterface(TextDrawingArea[] tda) {
        RSInterface main = addInterface(29130);
        addSpriteLoader(29131, 1365);
        addToItemGroup(29132, 1, 1, 25, 5, true, new String[]{null, null, null, null, null});
        addButtonWSpriteLoader(29133, 714, "Close Window", 16, 16);
        addToItemGroup(29134, 1, 1, 25, 5, true, new String[]{null, null, null, null, null});
        addToItemGroup(29135, 1, 1, 25, 5, true, new String[]{null, null, null, null, null});
        addToItemGroup(29136, 1, 1, 25, 5, true, new String[]{null, null, null, null, null});
        main.totalChildren(6);
        main.child(0, 29131, 150, 50);
        main.child(1, 29132, 285, 100);
        main.child(2, 29133, 403, 58);
        main.child(3, 29134, 285 + 30, 100);
        main.child(4, 29135, 285 + 30 + 30, 100);
        main.child(5, 29136, 285 + 30 + 30 + 30, 100);
    }

    public static void bootyTrapDBZInterface(TextDrawingArea[] tda) {
        RSInterface main = addInterface(28130);
        addSpriteLoader(28131, 1380);
        addText(28132, "You have been booty trapped!", tda, 2, ColorConstants.YELLOW, true, false);

        addButtonWSpriteLoader(28133, 714, "Close Window", 16, 16);
        addText(28134, "DONT LET HIM FIND OUT!", tda, 1, ColorConstants.YELLOW, true, false);
        addText(28135, "hehe you perv", tda, 1, ColorConstants.YELLOW, true, false);
        addText(28136, "LOOoooOOLOLOOL!", tda, 1, ColorConstants.YELLOW, true, false);
        main.totalChildren(6);
        main.child(0, 28131, 150, 50);
        main.child(1, 28132, 285, 60);
        main.child(2, 28133, 403, 58);
        main.child(3, 28134, 90 + 140, 90);
        main.child(4, 28135, 200 + 8, 70 + 120);
        main.child(5, 28136, 200 + 167, 70 + 100);

    }

    public static void bootyTrapDBZInterface2(TextDrawingArea[] tda) {
        RSInterface main = addInterface(27130);
        addSpriteLoader(27131, 1381);
        addText(27132, "Piccolo has died in the battle!", tda, 2, ColorConstants.YELLOW, true, false);

        addButtonWSpriteLoader(27133, 714, "Close Window", 16, 16);
        addText(27134, "poor man!", tda, 1, ColorConstants.YELLOW, true, false);
        addText(27135, "blasted to peices", tda, 1, ColorConstants.YELLOW, true, false);
        addText(27136, "dont let him down!", tda, 1, ColorConstants.YELLOW, true, false);
        main.totalChildren(6);
        main.child(0, 27131, 150, 50);
        main.child(1, 27132, 285, 60);
        main.child(2, 27133, 403, 58);
        main.child(3, 27134, 90 + 140, 90);
        main.child(4, 27135, 200 + 8, 70 + 120);
        main.child(5, 27136, 200 + 167, 70 + 100);

    }

    public static void bootyTrapDBZInterface3(TextDrawingArea[] tda) {
        RSInterface main = addInterface(26130);
        addSpriteLoader(26131, 1382);
        addText(26132, "BOOO ! you are haunted!", tda, 2, ColorConstants.YELLOW, true, false);

        addButtonWSpriteLoader(26133, 714, "Close Window", 16, 16);
        addText(26134, "spooky wooowoo!", tda, 1, ColorConstants.YELLOW, true, false);
        addText(26135, "boOOOo gHoSt", tda, 1, ColorConstants.YELLOW, true, false);
        addText(26136, "dont be scared!", tda, 1, ColorConstants.YELLOW, true, false);
        main.totalChildren(6);
        main.child(0, 26131, 150, 50);
        main.child(1, 26132, 285, 60);
        main.child(2, 26133, 403, 58);
        main.child(3, 26134, 90 + 140, 90);
        main.child(4, 26135, 200 + 8, 70 + 120);
        main.child(5, 26136, 200 + 167, 70 + 100);

    }

    public RSFontSystem rsFont;

    public int currentFrame;

    private static Archive aClass44;
    private static List aMRUNodes_238;
    private static final List aMRUNodes_264 = new List(30);
    public static TextDrawingArea[] fonts;
    public static RSInterface[] interfaceCache;
    public InterfaceTextInput textInput;
    public boolean inputToggled = false;
    public String inputText = "";
    public String defaultText = "";
    public static RSInterface[] inputFields = new RSInterface[20];
    public boolean hasInputField = false;

    private int color;

    /**
     * Scratchcard
     *
     * @author Suic
     */

    public int[] itemsToDraw;

    public static void drawItemArray(int interfaceId, int[] items) {
        RSInterface rsi = addInterface(interfaceId);
        rsi.type = 282;
        rsi.itemsToDraw = items;

    }

    public static void newStarter(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(52750);
        addSpriteLoader(52751, 1341);
        addText(52752, "Lunite game mode selection screen", ColorConstants.ORANGE, true, true, 52, tda, 2);
        addText(52753, "Select a game mode", ColorConstants.ORANGE, true, true, 52, tda, 1);
        addText(52754, "Starter Pack", ColorConstants.ORANGE, true, true, 52,
                tda, 1);
        addText(52755, "Mode Description", ColorConstants.ORANGE, true, true, 52, tda, 1);
        addConfigButtonWSpriteLoader(52756, 51750, 1337, 1338, 150, 25, "Select Normal", 0, 5, 1085);
        addConfigButtonWSpriteLoader(52757, 51750, 1337, 1338, 150, 25, "Select Ironman", 1, 5, 1085);
        addConfigButtonWSpriteLoader(52758, 51750, 1337, 1338, 150, 25, "Select Ultimate Ironman", 2, 5, 1085);
        addConfigButtonWSpriteLoader(52773, 51750, 1337, 1338, 150, 25, "Select Veteran (Extreme Mode)", 3, 5, 1085);
        addConfigButtonWSpriteLoader(52776, 51750, 1337, 1338, 150, 25, "Select Group Ironman", 4, 5, 1085);
        addSpriteLoader(52759, 840);
        addSpriteLoader(52760, 839);
        addSpriteLoader(52775, 838);
        addSpriteLoader(52777, 1509);
        addText(52761, "Normal", ColorConstants.ORANGE2, false, true, 52, tda, 2);
        addText(52762, "  Ironman", ColorConstants.ORANGE2, false, true, 52, tda, 2);
        addText(52763, "  Ultimate Ironman", ColorConstants.ORANGE2, false, true, 52, tda, 2);
        addText(52774, "  Veteran", ColorConstants.ORANGE2, false, true, 52, tda, 2);
        addText(52778, "  Group Ironman", ColorConstants.ORANGE2, false, true, 52, tda, 2);
        addText(52764, "Play Lunite as an Iron man.", ColorConstants.ORANGE2, true, true, 52, tda, 0);
        addText(52765, "You will be restricted from trading, staking and looting items from killed players.", ColorConstants.ORANGE2,
                true, true, 52, tda, 0);
        addText(52766, "You will not get a npc drop if another player has done more damage.", ColorConstants.ORANGE2, true, true, 52,
                tda, 0);
        addText(52767, "You will have to rely on your starter, skilling, pvming, and shops.", ColorConstants.ORANGE2, true, true, 52,
                tda, 0);
        addText(52768, "This game mode is for players that love a challenge.", ColorConstants.ORANGE2, true, true, 52, tda, 0);
        addHoverButtonWSpriteLoader(52769, 1457, 118, 28, "Confirm selection", -1, 52770, 1);
        addHoveredImageWSpriteLoader(52770, 1458, 118, 28, 52771);
        addText(52772, "Confirm", ColorConstants.ORANGE, true, true, 52, tda, 2);

        addText(52798, "This game mode is for players that love a challenge.", ColorConstants.ORANGE2, true, true, 52, tda, 0);

        tab.totalChildren(55);
        int x = 15;
        int y = 0;
        tab.child(0, 52751, 7 + x, 8 + y);

        //HEADERS
        tab.child(1, 52752, 235 + x, 11 + y);
        tab.child(2, 52753, 105 + x, 38 + y);
        tab.child(3, 52754, 169 + x, 212 + y);
        tab.child(4, 52755, 325 + x, 38 + y);

        //TEXT
        tab.child(13, 52764, 325 + x, 60 + y);
        tab.child(14, 52765, 325 + x, 70 + y);
        tab.child(15, 52766, 325 + x, 80 + y);
        tab.child(16, 52767, 325 + x, 90 + y);
        tab.child(17, 52768, 325 + x, 100 + y);
        tab.child(54, 52798, 325 + x, 110 + y);


        //BUTTON
        tab.child(18, 52769, 330 + x, 247 + y);
        tab.child(19, 52770, 330 + x, 247 + y);
        tab.child(20, 52772, 385 + x, 253 + y);

        //SELECTION

        tab.child(5, 52756, 27 + x, 64 + y);
        tab.child(10, 52761, 50 + x, 64 + y);

        y += 29;

        tab.child(6, 52757, 27 + x, 64 + y);
        tab.child(8, 52759, 48 + x, 64 + y);
        tab.child(11, 52762, 64 + x, 64 + y);

        y += 29;

        tab.child(7, 52758, 27 + x, 64 + y);
        tab.child(9, 52760, 48 + x, 64 + y);
        tab.child(12, 52763, 64 + x, 64 + y);

        y += 29;

        tab.child(24, 52776, 27 + x, 64 + y);
        tab.child(26, 52777, 48 + x, 64 + y);
        tab.child(25, 52778, 64 + x, 64 + y);

        y += 29;

        tab.child(21, 52773, 27 + x, 64 + y);
        tab.child(23, 52775, 48 + x, 64 + y);
        tab.child(22, 52774, 64 + x, 64 + y);

        /**
         * First row of items
         */
        int child = 27;
        x = 40;
        for (int i = 59025; i < 59034; i++) {
            addItemOnInterface(i, 59000, new String[]{});
            tab.child(child, i, x, 237);
            child++;
            x += 33;
        }

        int child2 = 36;
        int x2 = 40;
        for (int i = 59034; i < 59043; i++) {
            addItemOnInterface(i, 59000, new String[]{});
            tab.child(child2, i, x2, 270);
            child2++;
            x2 += 33;
        }
        /**
         * third row of items
         */
        int child3 = 45;
        int x3 = 40;
        for (int i = 59043; i < 59052; i++) {
            addItemOnInterface(i, 59000, new String[]{});
            tab.child(child3, i, x3, 303);
            child3++;
            x3 += 33;
        }
    }

    public static void taxbagShopInterface(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(52300);
        addSpriteLoader(52301, 1360);
        addToItemGroup(52302, 13, 10, 4, 4, true, new String[]{"Value", null, null, null, null});
        addButtonWSpriteLoader(52303, 714, "Close Window", 16, 16);
        addText(52304, "Billz Exchange Market @red@(Items I'm Buying)", 0xf9f909, true, true, 100, tda, 2);
        tab.totalChildren(4);
        tab.child(0, 52301, 10, 10);
        tab.child(1, 52302, 20, 50);
        tab.child(2, 52303, 478, 10);
        tab.child(3, 52304, 251, 20);
    }

    public static void teleportInterface1(TextDrawingArea[] tda) {
        final int STARTING_POINT = 60000;

        RSInterface main = addInterface(STARTING_POINT);
        addSpriteLoader(STARTING_POINT + 1, 1444);

        String[] teleports = {"Bosses", "Monsters", "Minigames", "Dungeons", "Cities", "Globals"};
        main.totalChildren(36);
        main.child(0, STARTING_POINT + 1, 10, 10);
        int childStart = 1;
        int xPos = 18;
        int spot = 0;
        for (int i = 0; i < teleports.length * 6; i += 5) {

            addConfigButtonWSpriteLoader(STARTING_POINT + 2 + i, 60000, 1506, 1507, 79, 20, "Select", 0, 5, 2357);

           /* addHoverButtonWSpriteLoader(STARTING_POINT + 2 + i, 1448, 105, 21, teleports[spot], -1, STARTING_POINT + 3 + i, 1);
            addHoveredImageWSpriteLoader(STARTING_POINT + 3 + i, 1449, 105, 21, STARTING_POINT + 4 + i);*/
            main.child(childStart++, STARTING_POINT + 2 + i, xPos, 35);

            xPos += 93;
            spot++;
        }

        int textStart = 11;
        int textX = 55;
        for (int i = 0; i < teleports.length; i++) {
            addText(STARTING_POINT + 40 + i, teleports[i], tda, 1, ColorConstants.ORANGE, false, true);
            main.child(textStart, STARTING_POINT + 40 + i, textX, 39);
            textStart++;
            textX += 92;
        }

        addText(STARTING_POINT + 46, "Bosses", tda, 2, 0xb9a888, false, true);
        addText(STARTING_POINT + 47, "Npc Name", tda, 2, 0xb9a888, false, true);
        addText(STARTING_POINT + 48, "Drops", tda, 2, 0xb9a888, false, true);
        addText(STARTING_POINT + 49, "Description", tda, 2, 0xb9a888, false, true);
        addText(STARTING_POINT + 50, "Tier: ", tda, 3, 0xb9a888, false, true);
        main.child(16, STARTING_POINT + 100, 0, 93);

        main.child(17, STARTING_POINT + 46, 56, 73);
        main.child(18, STARTING_POINT + 47, 214, 73);
        main.child(19, STARTING_POINT + 48, 395, 73);
        main.child(20, STARTING_POINT + 49, 221, 246);
        main.child(21, STARTING_POINT + 50, 386, 247);

        main.child(22, STARTING_POINT + 51, 180, 267);
        main.child(23, STARTING_POINT + 52, 180, 282);
        main.child(24, STARTING_POINT + 53, 180, 297);
        main.child(25, STARTING_POINT + 54, 415, 267);
        main.child(26, STARTING_POINT + 55, 415, 282);
        main.child(27, STARTING_POINT + 56, 415, 297);

        main.child(28, STARTING_POINT + 57, 268, 20);

        main.child(29, STARTING_POINT + 58, 360, 210);
        main.child(30, STARTING_POINT + 59, 360, 210);
        main.child(31, STARTING_POINT + 61, 425, 217);
        main.child(32, STARTING_POINT + 62, 197, 122);
        main.child(33, STARTING_POINT + 200, 361, 95);

        main.child(34, STARTING_POINT + 64, 480, 19);
        main.child(35, STARTING_POINT + 65, 480, 19);

        addCloseButtonSmall(STARTING_POINT + 64, STARTING_POINT + 65, STARTING_POINT + 66);

        addText(STARTING_POINT + 51, "Attacks with: Melee", tda, 1, ColorConstants.ORANGE, false, true);
        addText(STARTING_POINT + 52, "Weakness: Range", tda, 1, ColorConstants.ORANGE, false, true);
        addText(STARTING_POINT + 53, "Health: 50000", tda, 1, ColorConstants.ORANGE, false, true);
        addText(STARTING_POINT + 54, "Max hit: 450", tda, 1, ColorConstants.ORANGE, true, true);
        addText(STARTING_POINT + 55, "Players here: 0", tda, 1, ColorConstants.ORANGE, true, true);
        addText(STARTING_POINT + 56, "Last dropped: Unknown", tda, 1, ColorConstants.ORANGE, true, true);

        addText(STARTING_POINT + 57, "Teleports", tda, 2, ColorConstants.YELLOW, true, true);

        addHoverButtonWSpriteLoader(STARTING_POINT + 58, 1445, 132, 29, "Teleport", -1, STARTING_POINT + 59, 1);
        addHoveredImageWSpriteLoader(STARTING_POINT + 59, 1446, 132, 29, STARTING_POINT + 60);

        addText(STARTING_POINT + 61, "Teleport", tda, 2, ColorConstants.ORANGE, true, true);

        addNpcOld(STARTING_POINT + 62, 252);


        RSInterface scroll = addInterface(STARTING_POINT + 100);
        scroll.totalChildren(66);
        int yPos = 1;
        for (int i = 0; i < 50; i++) {
            addSpriteLoader(STARTING_POINT + 101 + i, i % 2 == 0 ? 1450 : 1451);
            scroll.child(i, STARTING_POINT + 101 + i, 21, yPos);
            yPos += 21;
        }
        int textY = 7;
        for (int i = 0; i < 16; i++) {
            addHoverableText(STARTING_POINT + 152 + i, "Name: " + i, "Select", tda, 0, false, false, 100, ColorConstants.ORANGE, 0xb9a888);
            scroll.child(50 + i, STARTING_POINT + 152 + i, 25, textY);
            textY += 21;
        }

        scroll.width = 155;
        scroll.height = 221;
        scroll.scrollMax = 315;


        RSInterface scroll1 = addInterface(STARTING_POINT + 200);
        scroll1.totalChildren(1);
        addToItemGroup(STARTING_POINT + 63, 3, 50, 8, 5, true, new String[]{null, null, null, null, null}); // it looks oversized for some on apollo too, go look at the gif  u sent me. yeah but it has a max of a specific thing not super oversized.
        scroll1.child(0, STARTING_POINT + 63, 1, 5);

        scroll1.width = 130 - 16;
        scroll1.height = 113;
        scroll1.scrollMax = 1000;
    }


    public static void teleportInterface(TextDrawingArea[] tda) {
        String[] categories = new String[]{"Bosses", "Monsters", "Starters", "Skilling", "Minigames", "Cities"};
        RSInterface main = addInterface(50500);
        addSpriteLoader(50501, 1066);
        main.totalChildren(21);
        main.child(0, 50501, 7, 10);

        addClickableText(50502, categories[0], "Select", tda, 2, ColorConstants.ORANGE2, 55, 25);// bosses
        addClickableText(50503, categories[1], "Select", tda, 2, ColorConstants.ORANGE2, 55, 25);// monsters
        addClickableText(50504, categories[2], "Select", tda, 2, ColorConstants.ORANGE2, 55, 25);// starters
        addClickableText(50505, categories[3], "Select", tda, 2, ColorConstants.ORANGE2, 55, 25);
        addClickableText(50506, categories[4], "Select", tda, 2, ColorConstants.ORANGE2, 55, 25);
        addClickableText(50507, categories[5], "Select", tda, 2, ColorConstants.ORANGE2, 55, 25);
        main.child(1, 50502, 56 - 20, 22);
        main.child(2, 50503, 134 - 25, 22);
        main.child(3, 50504, 215 - 24, 22);
        main.child(4, 50505, 295 - 24, 22);
        main.child(5, 50506, 374 - 32, 22);
        main.child(6, 50507, 454 - 17, 22);
        main.child(7, 50600, 0, 71);
        main.child(8, 50508, 45, 58);
        main.child(9, 50512, 239, 300);
        main.child(10, 50510, 422, 94);
        main.child(11, 50511, 432, 199);
        main.child(12, 51250, 0 + 12, 212);

        main.child(18, 50509, 255, 302);
        main.child(19, 50513, 305, 305);
        main.child(20, 50514, 180, 85);

        addText(50508, "x names", ColorConstants.ORA, false, true, 52, tda, 0);
        addSpriteLoader(50509, 456);
        // addText(50509, "", 0, true, true, 100, tda, 0);
        addText(50510, "Tips/Description", ColorConstants.ORA, true, true, 100, tda, 0);
        addText(50511, "Notable drops viewer", ColorConstants.ORA, true, true, 100, tda, 0);

        addNpc(50514, 131);
        addButtonWSpriteLoader(50512, 1069, "Teleport");
        addText(50513, "Teleport", ColorConstants.ORANGE2, true, true, 100, tda, 2);
        int descriptionChild = 13;
        int descriptionX = 360;
        int descriptionY = 110;
        for (int descriptionText = 51200; descriptionText < 51205; descriptionText++) {
            addText(descriptionText, "Description here", ColorConstants.INT2, false, true, 100, tda, 0);
            main.child(descriptionChild, descriptionText, descriptionX, descriptionY);
            descriptionChild++;
            descriptionY += 10;

        }

        RSInterface dropsScroll = addInterface(51250);
        dropsScroll.width = 466;
        dropsScroll.height = 90;
        dropsScroll.scrollMax = 350;
        addToItemGroup(51251, 8, 20, 4, 4, true, new String[]{null, null, null, null, null});
        dropsScroll.totalChildren(1);
        dropsScroll.child(0, 51251, 160, 0);

        RSInterface nameScroll = addInterface(50600);
        nameScroll.width = 126;
        nameScroll.height = 252;
        nameScroll.scrollMax = 1410;
        int bossChild = 0;
        int overAllX = 18;
        int bossY = 3;
        nameScroll.totalChildren(99);
        for (int bossText = 50601; bossText < 50700; bossText++) {
            addClickableText(bossText, "", "Select", fonts, 1, ColorConstants.ORANGE2, 130, 13);
            nameScroll.child(bossChild, bossText, overAllX, bossY);
            bossChild++;
            bossY += 14;
        }

    }

    public static void addNpc(int ID, int npcId) {
        RSInterface petCanvas = interfaceCache[ID] = new RSInterface();
        petCanvas.id = ID;
        petCanvas.parentID = ID;
        petCanvas.type = 6;
        petCanvas.atActionType = 0;
        petCanvas.contentType = 3291;
        petCanvas.width = 136;
        petCanvas.height = 168;
        // petCanvas.transparancy = 0;
        petCanvas.modelZoom = 1400;
        petCanvas.modelRotationY = 150;
        petCanvas.modelRotationX = 0;
        petCanvas.disabledAnimationId = -1;
        petCanvas.enabledAnimationId = -1;
        petCanvas.npcDisplay = npcId;
    }

    public static void addProperNpc(int ID, int npcId) {
        RSInterface petCanvas = interfaceCache[ID] = new RSInterface();
        petCanvas.id = ID;
        petCanvas.parentID = ID;
        petCanvas.type = 6;
        petCanvas.atActionType = 0;
        petCanvas.contentType = 3600;
        petCanvas.width = 136;
        petCanvas.height = 168;
        // petCanvas.transparancy = 0;
        petCanvas.modelZoom = 600;
        petCanvas.modelRotationY = 150;
        petCanvas.modelRotationX = 100;
        petCanvas.disabledAnimationId = -1;
        petCanvas.enabledAnimationId = -1;
        petCanvas.npcDisplay = npcId;
        petCanvas.contentId = npcId;
    }

    public static void addNpcOld(int ID, int npcId) {
        RSInterface petCanvas = interfaceCache[ID] = new RSInterface();
        petCanvas.id = ID;
        petCanvas.parentID = ID;
        petCanvas.type = 6;
        petCanvas.atActionType = 0;
        petCanvas.contentType = 32921;
        petCanvas.width = 136;
        petCanvas.height = 168;
        // petCanvas.transparancy = 0;
        petCanvas.modelZoom = 1400;
        petCanvas.modelRotationY = 150;
        petCanvas.modelRotationX = 0;
        petCanvas.disabledAnimationId = -1;
        petCanvas.enabledAnimationId = -1;
        petCanvas.npcDisplay = npcId;
    }

    public static void scratchCard(TextDrawingArea[] tda) {

        RSInterface main = addInterface(23630);
        addSpriteLoader(23631, 1326);
        addSpriteLoader(23632, 1324);
        addSpriteLoader(23633, 1324);
        addSpriteLoader(23634, 1324);
        drawItemArray(23635, null);
        addButtonWSpriteLoader(23636, 1328, "Scratch All");
        addText(23637, "Scratch", tda, 2, ColorConstants.SKY_BLUE, true, true);
        addText(23638, "Get 3 of the same and win the item!", tda, 2, ColorConstants.LIME, true, true);
        addSpriteLoader(23641, 1327);
        addSpriteLoader(23643, 1327);
        addText(23647, "Common Rewards", tda, 1, ColorConstants.DEEP_PINK, true, true);
        addText(23648, "Rare Rewards", tda, 1, ColorConstants.DEEP_PINK, true, true);
        main.totalChildren(14);
        main.child(0, 23631, 10, 10);
        main.child(1, 23632, 175, 65);
        main.child(2, 23633, 225, 65);
        main.child(3, 23634, 275, 65);
        main.child(4, 23635, 150, 65);
        main.child(5, 23636, 215, 120);
        main.child(6, 23637, 250, 128);
        main.child(7, 23638, 255, 45);
        main.child(8, 23641, 23, 65);
        main.child(9, 23640, 28, 66);
        main.child(10, 23643, 325, 65);
        main.child(11, 23644, 325, 66);
        main.child(12, 23647, 80, 50);
        main.child(13, 23648, 427, 50);

        RSInterface leftScroll = addInterface(23640);
        addToItemGroup(23642, 3, 30, 16, 10, true, new String[]{null, null, null, null, null});
        leftScroll.totalChildren(1);
        leftScroll.child(0, 23642, 0, 0);
        leftScroll.width = 129;
        leftScroll.height = 90;
        leftScroll.scrollMax = 500;

        RSInterface rightScroll = addInterface(23644);
        addToItemGroup(23645, 3, 30, 16, 10, true, new String[]{null, null, null, null, null});
        rightScroll.totalChildren(1);
        rightScroll.child(0, 23645, 3, 0);
        rightScroll.width = 135;
        rightScroll.height = 90;
        rightScroll.scrollMax = 500;
    }

    /**
     * @author Suic
     */

    public static void donationDeals(TextDrawingArea[] tda) {

        RSInterface main = addInterface(57265);
        addSpriteLoader(57266, 1322);

        addText(57267, "Promotions: @or1@(::Handlerewards)", ColorConstants.ORANGE, false, true, 52, tda, 2);
        addText(57268, "You need to donate within 24 hours to get the items, Counts from you first donation",
                ColorConstants.YELLOW, false, true, 52, tda, 0);
        addText(57269, "NOTE:", 0xd17c15, false, true, 52, tda, 1);
        addText(57270, "This only counts if you donate directly from ::donate!         of the day!", ColorConstants.YELLOW, false, true,
                52, tda, 0);
        addSpriteLoader(57271, 1323);
        addSpriteLoader(57272, 1325);
        addText(57277, "$0", ColorConstants.YELLOW, false, true, 52, tda, 0);
        addText(57278, "$150", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addText(57279, "$300", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addText(57280, "$500", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addHoverText(57281, "Close", "Close", tda, 1, 0xACACAC, false, true, 100, 14);
        addHoverText(57282, "Visit Store (Donate)", "Donate", tda, 1, ColorConstants.LIME_GREEN, false, true, 100, 14);
        main.totalChildren(17);
        main.child(0, 57266, 35, 100 + 110);
        main.child(1, 57267, 65, 105 + 110);
        main.child(2, 57268, 65, 125 + 110);
        main.child(3, 57269, 105, 135 + 110);
        main.child(4, 57270, 138, 137 + 110);
        main.child(5, 57271, 40, 165 + 110);
        main.child(6, 57272, 37, 156 + 110);
        // 7,8,9 is in use for the sprite
        main.child(10, 57276, 170, 152 + 110);
        main.child(11, 57277, 45, 195 + 110);
        main.child(12, 57278, 172, 195 + 110);
        main.child(13, 57279, 291, 195 + 110);
        main.child(14, 57280, 414, 195 + 110);
        main.child(15, 57281, 430, 195 + 20);
        main.child(16, 57282, 290, 195 + 20);

        int id = 57273;
        int child = 7;
        int xPos = 165;

        for (int i = 0; i < 3; i++) {
            addSpriteLoader(id + i, 1324);
            main.child(child + i, id + i, xPos, 150 + 110);
            xPos += 120;
        }

        addToItemGroup(57276, 5, 10, 88, 4, true, new String[]{null, null, null, null, null});

    }

    public static void gamblingInterface(TextDrawingArea[] tda) {

        RSInterface main = addInterface(57150);

        // main sprite
        addSpriteLoader(57151, 1330);
        main.totalChildren(21);
        main.child(0, 57151, 10, 10);
        main.child(1, 57152, 235, 63);
        main.child(2, 57153, 235, 113);
        main.child(3, 57154, 17, 20);
        main.child(4, 57155, 72, 177);
        main.child(5, 57156, 200, 177);

        main.child(6, 57157, 200, 200);
        main.child(7, 57158, 200, 235);
        main.child(8, 57159, 200, 270);
        // scroll(right side item scroll)
        main.child(9, 57170, 0, 45);
        // scroll (left side item scroll)
        main.child(10, 57180, 0, 45);
        main.child(11, 57175, 191, 150);

        // game types
        main.child(12, 57190, 20, 200);
        main.child(13, 57191, 20, 220);

        main.child(14, 57192, 43, 202);
        main.child(15, 57193, 43, 222);

        main.child(16, 57194, 20, 240);

        main.child(17, 57195, 43, 242);

        main.child(18, 57196, 20, 260);

        main.child(19, 57197, 43, 262);

        main.child(20, 57198, 205, 295);

        // accept, deciline clickable text
        addClickableText(57152, "Accept", "Select", tda, 1, 0x00C000, 45, 13);
        addClickableText(57153, "Deciline", "Select", tda, 1, 0xC00000, 45, 13);

        // gambling with child
        addText(57154, "Gambling with: name", 0xff9933, false, true, 52, tda, 2); // first true to false TODO: ->

        // Select a game type child
        addText(57155, "Select a game type", 0xff9933, true, true, 52, tda, 1);
        // -Player name has accepted-
        addText(57175, "---a", ColorConstants.WHITE, false, true, 52, tda, 0);

        // rules for "game"
        final String rules = " \"Game Name\" ";
        addText(57156, "Rules for" + rules, 0xff9933, false, true, 52, tda, 1);
        // description fields(under rules)
        addText(57157, "First Line description(can use a extra line)", ColorConstants.WHITE, false, true, 52, tda, 1);
        addText(57158, "Second Line(can use a extra line)", ColorConstants.WHITE, false, true, 52, tda, 1);
        addText(57159, "Third Line(can use a extra line)", ColorConstants.WHITE, false, true, 52, tda, 1);

        // right side item scroll
        RSInterface rightScroll = addInterface(57170);
        rightScroll.width = 154;
        rightScroll.height = 121;
        rightScroll.scrollMax = 250;
        rightScroll.totalChildren(1);
        rightScroll.child(0, 57171, 18, 0);
        addToItemGroup(57171, 3, 10, 14, 16, true,
                new String[]{"Remove 1", "Remove 5", "Remove 10", "Remove All", "Remove X"});

        // left side item scroll
        RSInterface leftScroll = addInterface(57180);
        leftScroll.width = 475;
        leftScroll.height = 121;
        leftScroll.scrollMax = 250;
        leftScroll.totalChildren(1);
        leftScroll.child(0, 57181, 340, 0);
        addToItemGroup(57181, 3, 10, 14, 16, true,
                new String[]{"Remove 1", "Remove 5", "Remove 10", "Remove All", "Remove X"});

        // game types
        // addConfigButtonWSpriteLoader(57190, 57150, 1077, 1078, 119, 15, "Select
        // 55x2(U host)", 1700, 1, 1430);
        // addConfigButtonWSpriteLoader(57191, 57150, 1077, 1078, 119, 15, "Select
        // 55x2(Other player hosts)", 1701, 1, 1430);
        // int id, int sprite, int setconfig, int width, int height, String s
        addToggleButton1(57190, 1332, 1700, 119, 15, "Flower Poker");
        addToggleButton1(57191, 1332, 1701, 119, 15, "Dice Duel(ft3)");

        // game type descriptions
        addText(57192, "Flower poker", ColorConstants.WHITE, false, true, 52, tda, 0);
        addText(57193, "Dice Duel(ft3)", ColorConstants.WHITE, false, true, 52, tda, 0);

        addToggleButton1(57194, 1332, 1702, 119, 15, "Blackjack");
        addText(57195, "Blackjack (playername hosts)", ColorConstants.WHITE, false, true, 52, tda, 0);
        addToggleButton1(57196, 1332, 1703, 119, 15, "Blackjack");
        addText(57197, "55x2 (playername hosts)", ColorConstants.WHITE, false, true, 52, tda, 0);

        addText(57198, "NOTE: Who hosts depends on who accepts first", ColorConstants.LIME, false, true, 52, tda, 0);

    }

    public static void addToggleButton1(int id, int sprite, int setconfig, int width, int height, String s) {
        RSInterface rsi = addInterface(id);
        rsi.disabledSprite = Client.cacheSprite[sprite];
        rsi.enabledSprite = Client.cacheSprite[sprite + 1];
        rsi.requiredValues = new int[1];
        rsi.requiredValues[0] = 1;
        rsi.valueCompareType = new int[1];
        rsi.valueCompareType[0] = 1;
        rsi.valueIndexArray = new int[1][3];
        rsi.valueIndexArray[0][0] = 5;
        rsi.valueIndexArray[0][1] = setconfig;
        rsi.valueIndexArray[0][2] = 0;
        rsi.atActionType = 4;
        rsi.width = width;
        rsi.hoverType = -1;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
        rsi.tooltip = s;
    }

    /**
     * @author Suic
     */

    public static void dailyRewards(TextDrawingArea[] tda) {

        RSInterface main = addInterface(57210);

        addSpriteLoader(57211, 1334);

        addSpriteLoader(57212, 1335);
        addSpriteLoader(57213, 1335);
        addSpriteLoader(57214, 1335);
        addSpriteLoader(57215, 1335);
        addSpriteLoader(57216, 1335);
        addSpriteLoader(57217, 1335);
        addText(57218, "Reedem a vote scroll to\\nget a vote reward", 0xff9933, false, true, 52, tda, 1);
        addSpriteLoader(57219, 1336);
        addText(57220, "Claim all login and vote rewards for 7 days for:", 0xff9933, false, true, 52, tda, 0);
        addText(57221, "Time until next login reward:", 0xff9933, false, true, 52, tda, 1);
        addText(57222, "24:00:00", ColorConstants.WHITE, false, true, 52, tda, 1);

        addToItemGroup(57223, 5, 10, 7, 4, true, new String[]{null, null, null, null, null});

        addHoverText(57224, "Claim Day 1", "Claim", tda, 1, ColorConstants.ORANGE, false, true, 100, 14);

        addHoverText(57225, "Claim Day 2", "Claim", tda, 1, ColorConstants.ORANGE, false, true, 100, 14);

        addHoverText(57226, "Claim Day 3", "Claim", tda, 1, ColorConstants.ORANGE, false, true, 100, 14);

        addHoverText(57227, "Claim Day 4", "Claim", tda, 1, ColorConstants.ORANGE, false, true, 100, 14);

        addHoverText(57228, "Claim Day 5", "Claim", tda, 1, ColorConstants.ORANGE, false, true, 100, 14);

        addHoverText(57229, "Claim Day 6", "Claim", tda, 1, ColorConstants.ORANGE, false, true, 100, 14);

        addText(57230, "Daily Reward", ColorConstants.ORANGE, false, true, 52, tda, 2);

        addHoverText(57231, "Close Window", "Close", tda, 1, 0xACACAC, false, true, 100, 14);

        addToItemGroup(57232, 4, 10, 6, 4, true, new String[]{null, null, null, null, null});
        addToItemGroup(57233, 4, 10, 6, 4, true, new String[]{null, null, null, null, null});
        addToItemGroup(57234, 4, 10, 6, 4, true, new String[]{null, null, null, null, null});
        addToItemGroup(57235, 4, 10, 6, 4, true, new String[]{null, null, null, null, null});
        addToItemGroup(57236, 4, 10, 6, 4, true, new String[]{null, null, null, null, null});
        addToItemGroup(57237, 4, 10, 6, 4, true, new String[]{null, null, null, null, null});

        addText(57238, "Login", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57239, 57210, 1337, 1338, 119, 21, "", 1, 0, 1810);
        addText(57240, "Vote", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57241, 57210, 1337, 1338, 119, 21, "", 1, 0, 1811);

        addText(57242, "Login", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57243, 57210, 1337, 1338, 119, 21, "", 1, 0, 1812);
        addText(57244, "Vote", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57245, 57210, 1337, 1338, 119, 21, "", 1, 0, 1813);

        addText(57246, "Login", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57247, 57210, 1337, 1338, 119, 21, "", 1, 0, 1814);
        addText(57248, "Vote", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57249, 57210, 1337, 1338, 119, 21, "", 1, 0, 1815);

        addText(57250, "Login", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57251, 57210, 1337, 1338, 119, 21, "", 1, 0, 1816);
        addText(57252, "Vote", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57253, 57210, 1337, 1338, 119, 21, "", 1, 0, 1817);

        addText(57254, "Login", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57255, 57210, 1337, 1338, 119, 21, "", 1, 0, 1818);
        addText(57256, "Vote", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57257, 57210, 1337, 1338, 119, 21, "", 1, 0, 1819);

        addText(57258, "Login", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57259, 57210, 1337, 1338, 119, 21, "", 1, 0, 1820);
        addText(57260, "Vote", ColorConstants.ORANGE, false, true, 52, tda, 0);
        addConfigButtonWSpriteLoader(57261, 57210, 1337, 1338, 119, 21, "", 1, 0, 1821);

        addHoverText(57262, "Claim Day 7", "Claim", tda, 0, ColorConstants.GREEN, false, true, 100, 14);

        main.totalChildren(52);
        main.child(0, 57211, 10, 10);

        main.child(1, 57212, 45, 48);
        main.child(2, 57213, 195, 48);
        main.child(3, 57214, 345, 48);
        main.child(4, 57215, 45, 145);
        main.child(5, 57216, 195, 145);
        main.child(6, 57217, 345, 145);
        main.child(7, 57218, 45, 245);
        main.child(8, 57219, 195, 245);
        main.child(9, 57220, 210, 255);
        main.child(10, 57221, 25, 275);
        main.child(11, 57222, 77, 290);
        main.child(12, 57223, 225, 265);

        main.child(13, 57224, 75, 63);
        main.child(14, 57225, 225, 63);
        main.child(15, 57226, 375, 63);
        main.child(16, 57227, 75, 155);
        main.child(17, 57228, 225, 155);
        main.child(18, 57229, 375, 155);
        main.child(19, 57230, 220, 20);
        main.child(20, 57231, 420, 20);

        main.child(21, 57232, 70, 83);
        main.child(22, 57233, 220, 83);
        main.child(23, 57234, 370, 83);
        main.child(24, 57235, 70, 175);
        main.child(25, 57236, 220, 175);
        main.child(26, 57237, 370, 175);

        main.child(27, 57238, 55, 117);
        main.child(28, 57239, 85, 117);
        main.child(29, 57240, 105, 117);
        main.child(30, 57241, 130, 117);

        main.child(31, 57242, 205, 117);
        main.child(32, 57243, 235, 117);
        main.child(33, 57244, 255, 117);
        main.child(34, 57245, 280, 117);

        main.child(35, 57246, 355, 117);
        main.child(36, 57247, 385, 117);
        main.child(37, 57248, 405, 117);
        main.child(38, 57249, 430, 117);

        main.child(39, 57250, 55, 213);
        main.child(40, 57251, 85, 213);
        main.child(41, 57252, 105, 213);
        main.child(42, 57253, 130, 213);

        main.child(43, 57254, 205, 213);
        main.child(44, 57255, 235, 213);
        main.child(45, 57256, 255, 213);
        main.child(46, 57257, 280, 213);

        main.child(47, 57258, 355, 213);
        main.child(48, 57259, 385, 213);
        main.child(49, 57260, 405, 213);
        main.child(50, 57261, 430, 213);
        main.child(51, 57262, 285, 245);
    }

    public static void addOutlinedColorBox(int id, int color, int width, int height, int transparency) {
        RSInterface tab = addInterface(id);
        tab.width = width;
        tab.height = height;
        tab.color = color;
        tab.type = 19;
        tab.opacity = (byte) transparency;
    }

    private boolean petFlag;

    public static void setScrollableItems(RSInterface tab, int[][] loot) {
        RSInterface parent = interfaceCache[tab.parentID];
        tab.atActionType = 1430;
        tab.contentType = 1430;
        tab.scrollMax = ((loot.length * 32) + (loot.length * tab.invSpritePadX)) - parent.width;
        for (int i = 0; i < loot.length; ++i) {
            tab.inv[i] = loot[i][0] + 1;
            tab.invStackSizes[i] = loot[i][1];
        }
    }


    public static void upgradeSystem(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(48270);
        addSprite(48271, 1313);

        // int id, int contentType, int width, int height, int xPad, int yPad, int
        // opacity,
        // boolean move, boolean displayAmount, boolean displayExamine, String... action
        // RSInterface.addToItemGroup(48275, 4, 5, 2, 3, false, null);
        addToItemGroup(48275, 4, 10, 17, 4, true, new String[]{"Select", null, null, null, null});

        RSInterface.addToItemGroup(48276, 3, 1, 22, 1, false, null);

        addHoverButton(48272, 1311, 0, 40, 40, "", -1, 48273, 1);
        addHoveredButton(48273, 1312, 1, 40, 40, 48274);

        addHoverButton(48277, 1311, 0, 40, 40, "", -1, 48278, 1);
        addHoveredButton(48278, 1312, 1, 40, 40, 48279);

        addHoverButton(48280, 1311, 0, 40, 40, "", -1, 48281, 1);
        addHoveredButton(48281, 1312, 1, 40, 40, 48282);

        // addContainer(48283, 3, 1, 22, 1, false, true, false, null, null, null, null,
        // null);
        RSInterface.addToItemGroup(48283, 3, 1, 22, 1, false, null);

        // addContainer(48284, 3, 1, 22, 1, false, true, false, null, null, null, null,
        // null);
        RSInterface.addToItemGroup(48284, 3, 1, 22, 1, false, null);

        addHoverButton(48285, 1309, 0, 137, 21, "Confirm", -1, 48286, 1);
        addHoveredButton(48286, 1310, 1, 137, 21, 48287);

        addText(48288, "Upgrade", tda, 2, 0xff9040, true, true);
        addText(48289, "Upgradable Items", tda, 1, 0xff9040, true, true);
        addText(48290, "Requirements", tda, 1, 0xff9040, true, true);
        addText(48291, "Upgrade", tda, 1, 0xff9040, true, true);
        addText(48292, "Chance", tda, 1, 0xff9040, true, true);

        addHoverButton(49293, 1238, 21, 21, "Close", -1, 49294, 1);
        addHoveredButton(49294, 1239, 21, 21, 49295);

        tab.totalChildren(20);
        tab.child(0, 48271, 105, 35);
        tab.child(1, 48272, 325, 105);
        tab.child(2, 48273, 325, 105);
        tab.child(3, 48275, 118, 95);
        tab.child(4, 48276, 325, 105);

        tab.child(5, 48277, 272, 147);
        tab.child(6, 48278, 272, 147);

        tab.child(7, 48280, 370, 147);
        tab.child(8, 48281, 370, 147);

        tab.child(9, 48283, 272, 147);
        tab.child(10, 48284, 370, 147);

        tab.child(11, 48285, 270, 230);
        tab.child(12, 48286, 270, 230);

        tab.child(13, 48288, 265, 45);
        tab.child(14, 48289, 185, 78);
        tab.child(15, 48290, 340, 78);
        tab.child(16, 48291, 340, 232);
        tab.child(17, 48292, 335, 182);
        tab.child(18, 49293, 392, 43);
        tab.child(19, 49294, 392, 43);

    }

    public static void InstanceManager(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(35000);
        RSInterface scrollTableft = RSInterface.addInterface(35070);
        RSInterface.addSprite(35001, 1267);
        RSInterface.addText(35002, "Instance Manager", tda, 2, ClientConstants.GOLD, false, true);
        RSInterface.addButton1(35003, 1245, 1246, "Select");
        RSInterface.drawNpcOnInterface(35004, 100, 1000);
        RSInterface.addText(35005, "Start Instance", tda, 2, ClientConstants.GOLD, false, true);
        itemGroup(35006, 0, 8, 8, 5);

        RSInterface.setChildren(7, tab);
        tab.child(0, 35001, 5, 5);
        tab.child(1, 35002, 221 - 7, 29 - 13);
        tab.child(2, 35003, 242, 304 - 13);
        tab.child(3, 35070, 15, 52 - 13);
        tab.child(4, 35004, 240, 83 + 15);
        tab.child(5, 35005, 273, 311 - 13);
        tab.child(6, 35006, 171, 254 - 13);

        // Scoll bar size, witdh and scrolling size.
        scrollTableft.width = 133 - 15;
        scrollTableft.height = 281;
        scrollTableft.scrollMax = 450;

        int npcList = 50;
        int y = 1;
        for (int i = 0; i < npcList; i++) {
            RSInterface.addText(35071 + i, "boss", tda, 1, ClientConstants.ORANGE, false, false, ClientConstants.WHITE,
                    "Select", 150);

        }
        RSInterface.setChildren(npcList, scrollTableft);

        for (int i = 0; i < npcList; i++) {
            scrollTableft.child(i, 35071 + i, 0, y);
            y += 15;
        }

    }

    public static void BossEventInterfaceTracker(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(37400);
        RSInterface.addSprite(37401, 1264);
        RSInterface.addText(37402, "Daily Boss Event Task", tda, 2, ClientConstants.GOLD, false, true);
        RSInterface.addText(37403, "Information", tda, 1, ClientConstants.GOLD, false, true);
        RSInterface.addText(37404, "kill-count: 4", tda, 0, ClientConstants.ORANGE, false, true);

        RSInterface.addText(37405, "Rewards", tda, 1, ClientConstants.GOLD, false, true);
        RSInterface.addText(37406, "Rare Drop Scroll", tda, 0, ClientConstants.ORANGE, false, true);

        RSInterface.addText(37407, "Boss", tda, 1, ClientConstants.GOLD, false, true);
        RSInterface.addText(37408, "NPC Information", tda, 1, ClientConstants.GOLD, false, true);

        RSInterface.addText(37409, "- N/A", tda, 0, ClientConstants.ORANGE, false, true);
        RSInterface.addText(37410, "- Combat Lvl: N/A", tda, 0, ClientConstants.ORANGE, false, true);
        RSInterface.addText(37411, "- Health: N/A", tda, 0, ClientConstants.ORANGE, false, true);
        RSInterface.addText(37412, "- Max Hit: N/A", tda, 0, ClientConstants.ORANGE, false, true);
        RSInterface.addText(37413, "- Weakness: Melee, Stab", tda, 0, ClientConstants.ORANGE, false, true);
        // RSInterface.addText(37414, "- Attack: 10", tda, 0, ClientConstants.ORANGE,
        // false, true);
        RSInterface.addText(37415, "- Task Amount: N/A", tda, 0, ClientConstants.ORANGE, false, true);

        RSInterface.drawNpcOnInterface(37416, 1691, 500); // it should show npc 100 regardless you using packet or not.
        // when you open interface npc 100 should pop up..
        // rock crab
        // RSInterface.modelViewer(37416, 1, 23889, 1250); //how did you have cow
        // before? here you dont even define the cow. you changed it elsewhere?
        // just to be sure try a man or idk.. something you know works kk
        RSInterface.addText(37417, "", tda, 0, ClientConstants.ORANGE, false, true);
        RSInterface.itemGroup(37418, 1, 1, 1, 1);
        RSInterface.addText(37420, "Note: First three to\\nComplete Task wins", tda, 0, ClientConstants.RED, false,
                true);
        RSInterface.addHoverButton(37421, 1265, 88, 36, "Teleport", -1, 37422, 1);
        RSInterface.addHoveredButton(37422, 1266, 88, 36, 37423);
        RSInterface.addText(37424, "Teleport", tda, 2, ClientConstants.GOLD, false, true);
        RSInterface.addText(37425, "- Amount Left: N/A", tda, 0, ClientConstants.ORANGE, false, true);

        RSInterface.addHoverButton(37426, 1238, 16, 16, "Close", 250, 37427, 3);
        RSInterface.addHoveredButton(37427, 1239, 16, 16, 37428);

        RSInterface.setChildren(24, tab);
        tab.child(0, 37401, 68, 25);
        tab.child(1, 37402, 178, 50 - 13);
        tab.child(2, 37403, 130, 230 - 13);
        tab.child(3, 37404, 117, 246 - 13);
        tab.child(4, 37405, 337, 230 - 13);
        tab.child(5, 37406, 337 - 13, 246 - 13);

        tab.child(6, 37407, 103, 78 - 13);
        tab.child(7, 37408, 282, 78 - 13);
        tab.child(8, 37416, 95, 105 - 13);

        tab.child(9, 37409, 260, 95 - 13);
        tab.child(10, 37410, 260, 110 - 13);
        tab.child(11, 37411, 260, 125 - 13);
        tab.child(12, 37412, 260, 140 - 13);
        tab.child(13, 37413, 260, 155 - 13);
        tab.child(14, 37415, 260, 170 - 13);
        tab.child(15, 37417, 260, 190 - 13);
        tab.child(16, 37418, 255, 230 - 13);
        tab.child(17, 37420, 260, 200 - 13);

        tab.child(18, 37421, 209, 269 - 13);
        tab.child(19, 37422, 209, 269 - 13);
        tab.child(20, 37424, 227, 280 - 13);
        tab.child(21, 37425, 260, 185 - 13);

        tab.child(22, 37426, 409, 37);
        tab.child(23, 37427, 409, 37);

    }

    public static void BoxRewardList(TextDrawingArea[] textDrawingAreas) {
        // String dir = "ok/ok";

        int interfaceId = 17550;
        int xOffset = 4;
        int yOffset = -8;
        RSInterface interfaces = RSInterface.addTabInterface(interfaceId);
        RSInterface scrollTableft = RSInterface.addInterface(17650);
        RSInterface scrollTabright = RSInterface.addInterface(17750);

        addSpriteLoader(17551, 1261);

        RSInterface.addText(17552, "Lunite Rewards List", textDrawingAreas, 2, 0xEAC117, true);
        // RSInterface.addButton(17553, 148, 149, "Close");

        addHoverButton(17553, 1238, 1, 16, 16, "Close Window", 250, 17557, 3);
        addHoveredButton(17557, 1239, 2, 16, 16, 17558);

        RSInterface.addText(17554, "Browse through various reward tables", textDrawingAreas, 1, 0xFF9900, true, true);

        RSInterface.addText(17555, "Lists:", textDrawingAreas, 1, 0xFF9900, true, true);

        RSInterface.addText(17556, "Rewards From: ", textDrawingAreas, 1, 0xFF9900, true, true);
        // drawNpcOnInterface(17558, 100, 1250);

        scrollTableft.width = 101;
        scrollTableft.height = 225;
        scrollTableft.scrollMax = 450;

        scrollTabright.width = 334;
        scrollTabright.height = 225;
        scrollTabright.scrollMax = 900;

        int npcList = 75;
        int y = 1;

        for (int i = 0; i < npcList; i++) {
            RSInterface.addText(17651 + i, "", textDrawingAreas, 1, ClientConstants.ORANGE, false, false,
                    ClientConstants.WHITE, "Select", 150);
        }

        itemGroup(17752, 6, 6, 5, 5);

        RSInterface.setChildren(9, interfaces);

        interfaces.child(0, 17551, 66 + xOffset, 15 + yOffset);
        interfaces.child(1, 17552, 253 + xOffset, 27 + yOffset);
        interfaces.child(2, 17553, 412 + xOffset, 23 + yOffset);
        interfaces.child(3, 17554, 253 + xOffset, 54 + yOffset);
        interfaces.child(4, 17650, 71 + xOffset, 99 + yOffset);
        interfaces.child(5, 17750, 83 + xOffset, 99 + yOffset);
        interfaces.child(6, 17555, 130 + xOffset, 78 + yOffset);
        interfaces.child(7, 17556, 315 + xOffset, 78 + yOffset);
        interfaces.child(8, 17557, 412 + xOffset, 23 + yOffset);

        RSInterface.setChildren(npcList, scrollTableft);

        for (int i = 0; i < npcList; i++) {
            scrollTableft.child(i, 17651 + i, 6, y);
            y += 15;
        }

        RSInterface.setChildren(1, scrollTabright);

        scrollTabright.child(0, 17752, 110, 10);

    }

    private static final int CLOSE_BUTTON = 1238, CLOSE_BUTTON_HOVER = 1239;

    public static void examineTab(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(52100);

        // addSprite(52101, 0, "/Sprites/ExamineTab");

        /// addSprite1(52101, 0, "Interfaces/ExamineTab/SPRITE");
        addSpriteLoader(52101, 1257);

        // addContainer(52102, 0, 1, 80, 30, 2, 100, false, false, false, null, null,
        // null, null, null);
        RSInterface.itemGroup(52102, 1, 80, 30, 2);

        addText(52113, "", tda, 2, 0xff8a1f, false, true);
        addText(52114, "Item Bonuses", tda, 2, 0xff8a1f, false, true);
        addText(52128, "Attack", tda, 2, 0xff8a1f, false, true);
        addText(52129, "Defence", tda, 2, 0xff8a1f, false, true);
        addText(52130, "Other Bonuses", tda, 2, 0xff8a1f, false, true);
        addText(52143, "", tda, 2, 0xff8a1f, false, true);

        addText(52144, "Strength", tda, 1, 0xff8a1f, false, true);
        addText(52145, "Prayer", tda, 1, 0xff8a1f, false, true);

        int y = 142;
        for (int i = 0; i < 9; i++) {
            addText(52103 + i, "", tda, 1, 0xff8a1f, false, true);
        }

        int yy = 116;
        String[] text = {"Stab", "Slash", "Crush", "Magic", "Range"};

        for (int i = 0; i < 5; i++) {
            addText(52131 + i, text[i], tda, 1, 0xff8a1f, false, true);
        }

        int yyy = 116;
        for (int i = 0; i < 5; i++) {
            addText(52138 + i, text[i], tda, 1, 0xff8a1f, false, true);
        }

        addHoverButton(52146, CLOSE_BUTTON, CLOSE_BUTTON, 16, 16, "Close Window", 0, 52147, 1);
        addHoveredButton(52147, CLOSE_BUTTON_HOVER, CLOSE_BUTTON_HOVER, 16, 16, 52148);

        tab.totalChildren(31);
        int childNum = 0;
        setBounds(52101, 75, 20, childNum++, tab);
        setBounds(52102, 104, 71, childNum++, tab);
        setBounds(52113, 110, 120, childNum++, tab);
        setBounds(52114, 320, 70, childNum++, tab);
        setBounds(52128, 279, 94, childNum++, tab);
        setBounds(52129, 369, 94, childNum++, tab);
        setBounds(52130, 320, 220, childNum++, tab);
        setBounds(52143, 154, 86, childNum++, tab);
        setBounds(52144, 275, 240, childNum++, tab);
        setBounds(52145, 275, 255, childNum++, tab);
        setBounds(52146, 445, 28, childNum++, tab);
        setBounds(52147, 445, 28, childNum++, tab);

        for (int i = 0; i < 9; i++) {
            setBounds(52103 + i, 106, y, childNum++, tab);
            y += 15;
        }

        for (int i = 0; i < 5; i++) {
            setBounds(52131 + i, 275, yy, childNum++, tab);
            yy += 15;
        }

        for (int i = 0; i < 5; i++) {
            setBounds(52138 + i, 366, yyy, childNum++, tab);
            yyy += 15;
        }

    }

    public int npcDisplay = 0;

    public int contentId;

    public static void drawNpcOnInterface(int childId, int npcId, int zoom, boolean pet) {
        final RSInterface rsInterface = interfaceCache[childId] = new RSInterface();
        rsInterface.id = childId;
        rsInterface.parentID = childId;
        rsInterface.type = 6;
        rsInterface.atActionType = 3291;
        rsInterface.contentId = npcId;
        rsInterface.petFlag = pet;
        rsInterface.width = 136;
        rsInterface.height = 168;
        rsInterface.opacity = 0;
        rsInterface.disabledMouseOverColor = 0;
        rsInterface.modelZoom = zoom;
        rsInterface.modelRotationY = 150; // these should be right, didnt cross reference but yh
        rsInterface.modelRotationX = 0;
        rsInterface.npcDisplay = rsInterface.npcDisplay = MobDefinition.get(npcId).standAnimation;
    }

    private int anInt257;
    private int anInt258;

    private int actionType;

    private int isMouseoverTriggered;

    public static void drawNpcOnInterface(int childId, int npcId, int zoom) {
        final RSInterface rsInterface = interfaceCache[childId] = new RSInterface();
        rsInterface.id = childId;
        rsInterface.parentID = childId;
        rsInterface.type = 6;
        rsInterface.atActionType = 3291;
        rsInterface.contentType = 3291;
        rsInterface.contentId = npcId;
        rsInterface.width = 136;
        rsInterface.height = 168;
        rsInterface.opacity = 0;
        rsInterface.disabledMouseOverColor = 0;
        rsInterface.modelZoom = zoom;
        rsInterface.modelRotationY = 150;
        rsInterface.modelRotationX = 0;
        rsInterface.npcDisplay = rsInterface.npcDisplay = MobDefinition.get(npcId).walkAnimation;
    }

    public static void GoldenScratchCardNew(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(39300);
        // String dir = "ScratchCard/Button";
        // addSprite1(39301, 0, dir);
        addSpriteLoader(39301, 1258);

        RSInterface.addText(39302, "Artex RSPS Golden Scratch Card", tda, 2, 0xFF9900, true, true); // 20014
        RSInterface.addText(39303, "Match 3 to Win!", tda, 2, 0xFF9900, true, true); // 20014
        RSInterface.addText(39304, "Win Amazing Prizes!", tda, 0, 0xFF9900, true, true); // 20014

        // RSInterface.addButton(39305, 1595, 1596, "Scratch");
        RSInterface.addText(39305, "", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(39306, "$", tda, 2, 0xFF9900, true, true);
        // RSInterface.addToItemGroup(39307, 0, 1, 80, 30, false, null, null, null);
        // itemGroup(39307, 0, 1, 80, 30);
        RSInterface.addText(39307, "", tda, 0, 0xFF9900, true, true); // 20014

        RSInterface.addText(39308, "Bonus Prize", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(39309, "Guaranteed Win", tda, 2, 0xFF9900, true, true);

        // RSInterface.addButton(39310, 1595, 1596, "Scratch");
        RSInterface.addText(39310, "", tda, 2, 0xFF9900, true, true);
        // RSInterface.addToItemGroup(39311, 0, 1, 80, 30, false, null, null, null);
        // itemGroup(39311, 0, 1, 80, 30);
        RSInterface.addText(39311, "", tda, 0, 0xFF9900, true, true); // 20014

        RSInterface.addText(39316, "$", tda, 2, 0xFF9900, true, true);

        // RSInterface.addButton(39312, 1595, 1596, "Scratch");
        RSInterface.addText(39312, "", tda, 2, 0xFF9900, true, true);

        // RSInterface.addToItemGroup(39317, 0, 1, 80, 30, false, null, null, null);
        // itemGroup(39317, 0, 1, 80, 30);
        RSInterface.addText(39317, "", tda, 0, 0xFF9900, true, true); // 20014

        // RSInterface.addButton(39313, 1595, 1596, "Scratch");
        RSInterface.addText(39313, "", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(39314, "$", tda, 2, 0xFF9900, true, true);
        // RSInterface.addToItemGroup(39315, 0, 1, 80, 30, false, null, null, null);
        // itemGroup(39315, 0, 1, 80, 30);
        RSInterface.addText(39315, "", tda, 0, 0xFF9900, true, true); // 20014

        // addHoverButton(39318, dir, 1, 57, 57, "Scratch", 250, 39319, 1);
        // addHoveredButton(39319, dir, 2, 16, 16, 39320);

        addHoverButton(39318, 1259, 1, 57, 57, "Scratch", -1, 39319, 1);
        addHoveredButton(39319, 1260, 2, 57, 57, 39320);

        addHoverButton(39321, 1259, 1, 57, 57, "Scratch", -1, 39322, 1);
        addHoveredButton(39322, 1260, 2, 57, 57, 39323);

        addHoverButton(39324, 1259, 1, 57, 57, "Scratch", -1, 39325, 1);
        addHoveredButton(39325, 1260, 2, 57, 57, 39326);

        addHoverButton(39327, 1259, 1, 57, 57, "Scratch", -1, 39328, 1);
        addHoveredButton(39328, 1260, 2, 57, 57, 39329);

        itemGroup(39330, 6, 6, 5, 5);

        itemGroup(39331, 6, 6, 5, 5);

        itemGroup(39332, 6, 6, 5, 5);

        itemGroup(39333, 6, 6, 5, 5);

        RSInterface.setChildren(29, tab);
        int startX = 150;
        int startY = 110;

        tab.child(0, 39301, 90, 15);
        tab.child(1, 39302, 255, 26);
        tab.child(2, 39303, 255, 55);
        tab.child(3, 39304, 255, 70);

        tab.child(4, 39305, startX, startY);
        tab.child(5, 39306, startX + 28, startY + 20);
        tab.child(6, 39307, startX + 13, startY - 20);
        tab.child(7, 39308, 255, 198);
        tab.child(8, 39309, 255, 213);

        tab.child(9, 39310, startX + 75, startY);
        tab.child(10, 39311, startX + 85, startY - 20); // second
        tab.child(11, 39316, startX + 28 + 75, startY + 20);

        tab.child(12, 39312, 227, 245);

        tab.child(13, 39313, startX + 150, startY);
        tab.child(14, 39314, startX + 28 + 150, startY + 20);
        tab.child(15, 39315, startX + 165, startY - 20);
        tab.child(16, 39317, 240, 225); // == to the bonus reward positioning item group

        tab.child(17, 39318, startX, startY);
        tab.child(18, 39319, startX, startY);

        tab.child(19, 39321, startX + 75, startY);
        tab.child(20, 39322, startX + 75, startY);

        tab.child(21, 39324, 227, 245);
        tab.child(22, 39325, 227, 245);

        tab.child(23, 39327, startX + 150, startY);
        tab.child(24, 39328, startX + 150, startY);

        tab.child(25, 39330, startX - 18, startY + 8);
        tab.child(26, 39331, startX + 53, startY + 8);
        tab.child(27, 39332, 203, 255); // == to the bonus reward positioning item group
        tab.child(28, 39333, startX + 130, startY + 8);

    }

    public static void addTextInput(int id, int w, int h, int text, String defaultText, RSInterface parent,
                                    InterfaceTextInput input) {
        RSInterface i = addInterface(id);
        i.type = 77;
        i.width = w;
        i.height = h;
        i.rsFont = newFonts[text];
        i.textInput = input;
        i.tooltip = "Toggle input";
        i.atActionType = 1;
        i.contentType = 0;
        i.inputText = i.defaultText = defaultText;
        parent.hasInputField = true;
        for (int b = 0; b < 20; b++) {
            if (inputFields[b] == null) {
                inputFields[b] = i;
                break;
            }
        }
    }

    private static void addActionButton(int id, int sprite, int sprite2, int width, int height, String s) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.disabledSprite = Client.cacheSprite[sprite];

        if (sprite2 != -1) {
            rsi.enabledSprite = Client.cacheSprite[sprite == sprite2 ? sprite + 1 : sprite2];
        }

        rsi.tooltip = s;
        rsi.contentType = 0;
        rsi.atActionType = 1;
        rsi.width = width;
        rsi.hoverType = 52;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
    }

    public static void addClanChatListTextWithOptions(int id, String text, String ignore, boolean owner,
                                                      TextDrawingArea tda[], int idx, int color, int width, int height) {
        RSInterface Tab = addTabInterface(id);
        Tab.parentID = id;
        Tab.id = id;
        Tab.type = 4;
        Tab.atActionType = 1;
        Tab.width = width;
        Tab.height = height;
        Tab.contentType = 0;
        Tab.hoverType = 0;
        // Tab.mOverInterToTrigger = -1;
        Tab.centerText = false;
        // Tab.enabledText = true;
        Tab.textDrawingAreas = tda[idx];
        Tab.message = text;
        // Tab.aString228 = "";
        Tab.disabledColor = color;
        String s = Tab.message;
        if (s.contains("<img")) {
            int prefix = s.indexOf("<img=");
            int suffix = s.indexOf(">");
            s = s.replaceAll(s.substring(prefix + 5, suffix), "");
            s = s.replaceAll("</img>", "");
            s = s.replaceAll("<img=>", "");
        }
        if (!s.equals(ignore)) {
            if (owner) {
                Tab.actions = new String[]{"Promote to Recruit @or1@" + s + "", "Promote to Corporal @or1@" + s + "",
                        "Promote to Sergeant @or1@" + s + "", "Promote to Lieutenant @or1@" + s + "",
                        "Promote to Captain @or1@" + s + "", "Promote to General @or1@" + s + "",
                        "Demote @or1@" + s + "", "Kick @or1@" + s + ""};
            } else {
                Tab.actions = new String[]{"Kick @or1@" + s + ""};
            }
        }
    }

    public static void addToItemGroup(RSInterface rsi, int w, int h, int x, int y, boolean actions, String action1,
                                      String action2, String action3) {
        rsi.width = w;
        rsi.height = h;
        rsi.inv = new int[w * h];
        rsi.invStackSizes = new int[w * h];
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.invSpritePadX = x;
        rsi.invSpritePadY = y;
        rsi.spritesX = new int[20];
        rsi.spritesY = new int[20];
        rsi.sprites = new Sprite[20];
        rsi.actions = new String[5];
        if (actions) {
            rsi.actions[0] = action1;
            rsi.actions[1] = action2;
            rsi.actions[2] = action3;
        }
        rsi.type = 2;
    }

    public static void addPrayerWithTooltip(int i, int configId, int configFrame, int requiredValues,
                                            int prayerSpriteID, int Hover, String tooltip) {
        RSInterface Interface = addTabInterface(i);
        Interface.id = i;
        Interface.parentID = 5608;
        Interface.type = 5;
        Interface.atActionType = 4;
        Interface.contentType = 0;
        Interface.opacity = 0;
        Interface.hoverType = Hover;
        Interface.disabledSprite = Client.cacheSprite[927];
        Interface.width = 34;
        Interface.height = 34;
        Interface.valueCompareType = new int[1];
        Interface.requiredValues = new int[1];
        Interface.valueCompareType[0] = 1;
        Interface.requiredValues[0] = configId;
        Interface.valueIndexArray = new int[1][3];
        Interface.valueIndexArray[0][0] = 5;
        Interface.valueIndexArray[0][1] = configFrame;
        Interface.valueIndexArray[0][2] = 0;
        Interface.tooltip = tooltip;
        Interface = addTabInterface(i + 1);
        Interface.id = i + 1;
        Interface.parentID = 5608;
        Interface.type = 5;
        Interface.atActionType = 0;
        Interface.contentType = 0;
        Interface.opacity = 0;
        Interface.disabledSprite = Client.cacheSprite[899 + prayerSpriteID];
        Interface.enabledSprite = Client.cacheSprite[873 + prayerSpriteID];
        if (prayerSpriteID == 26) {
            Interface.enabledSprite = Client.cacheSprite[892];
        } else if (prayerSpriteID == 27) {
            Interface.enabledSprite = Client.cacheSprite[893];
        }
        Interface.width = 34;
        Interface.height = 34;
        Interface.valueCompareType = new int[1];
        Interface.requiredValues = new int[1];
        Interface.valueCompareType[0] = 2;
        Interface.requiredValues[0] = requiredValues + 1;
        Interface.valueIndexArray = new int[1][3];
        Interface.valueIndexArray[0][0] = 2;
        Interface.valueIndexArray[0][1] = 5;
        Interface.valueIndexArray[0][2] = 0;
    }

    public static void addCurseWithTooltip(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID,
                                           String PrayerName, int Hover) {
        RSInterface Interface = addTabInterface(i);
        Interface.id = i;
        Interface.parentID = 22500;
        Interface.type = 5;
        Interface.atActionType = 4;
        Interface.contentType = 0;
        Interface.opacity = 0;
        Interface.hoverType = Hover;
        Interface.disabledSprite = Client.cacheSprite[927];
        Interface.width = 34;
        Interface.height = 34;
        Interface.valueCompareType = new int[1];
        Interface.requiredValues = new int[1];
        Interface.valueCompareType[0] = 1;
        Interface.requiredValues[0] = configId;
        Interface.valueIndexArray = new int[1][3];
        Interface.valueIndexArray[0][0] = 5;
        Interface.valueIndexArray[0][1] = configFrame;
        Interface.valueIndexArray[0][2] = 0;
        Interface.tooltip = "Activate@lre@ " + PrayerName;
        Interface = addTabInterface(i + 1);
        Interface.id = i + 1;
        Interface.parentID = 22500;
        Interface.type = 5;
        Interface.atActionType = 0;
        Interface.contentType = 0;
        Interface.opacity = 0;
        Interface.disabledSprite = Client.cacheSprite[947 + prayerSpriteID];
        Interface.enabledSprite = Client.cacheSprite[927 + prayerSpriteID];

        Interface.width = 34;
        Interface.height = 34;
        Interface.valueCompareType = new int[1];
        Interface.requiredValues = new int[1];
        Interface.valueCompareType[0] = 2;
        Interface.requiredValues[0] = requiredValues + 1;
        Interface.valueIndexArray = new int[1][3];
        Interface.valueIndexArray[0][0] = 2;
        Interface.valueIndexArray[0][1] = 5;
        Interface.valueIndexArray[0][2] = 0;
    }

    public static void setBoundry(int frame, int ID, int X, int Y, RSInterface RSInterface) {
        RSInterface.children[frame] = ID;
        RSInterface.childX[frame] = X;
        RSInterface.childY[frame] = Y;
    }

    protected static final int[] SETTING_CONFIGS = {516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528,
            529, 530, 531, 532};

    public static void addCheckmarkHover(int interfaceID, int actionType, int hoverid, int spriteId, int spriteId2,
                                         int Width, int Height, int configFrame, int configId, String Tooltip, int hoverId2, int hoverSpriteId,
                                         int hoverSpriteId2, int hoverId3, String hoverDisabledText, String hoverEnabledText, int X, int Y) {
        RSInterface hover = addTabInterface(interfaceID);
        hover.id = interfaceID;
        hover.parentID = interfaceID;
        hover.type = 5;
        hover.atActionType = actionType;
        hover.contentType = 0;
        hover.opacity = 0;
        hover.hoverType = hoverid;
        hover.disabledSprite = Client.cacheSprite[spriteId];
        hover.enabledSprite = Client.cacheSprite[spriteId2];
        hover.width = Width;
        hover.tooltip = Tooltip;
        hover.height = Height;
        hover.valueCompareType = new int[1];
        hover.requiredValues = new int[1];
        hover.valueCompareType[0] = 1;
        hover.requiredValues[0] = configId;
        hover.valueIndexArray = new int[1][3];
        hover.valueIndexArray[0][0] = 5;
        hover.valueIndexArray[0][1] = configFrame;
        hover.valueIndexArray[0][2] = 0;
        hover = addTabInterface(hoverid);
        hover.parentID = hoverid;
        hover.id = hoverid;
        hover.type = 0;
        hover.atActionType = 0;
        hover.width = 550;
        hover.height = 334;
        hover.interfaceShown = true;
        hover.hoverType = -1;
        addSprite(hoverId2, hoverSpriteId, hoverSpriteId2, configId, configFrame);
        addHoverBox(hoverId3, interfaceID, hoverDisabledText, hoverEnabledText, configId, configFrame);
        setChildren(2, hover);
        setBounds(hoverId2, 0, 0, 0, hover);
        setBounds(hoverId3, X, Y, 1, hover);

    }

    public static void addBankHover(int interfaceID, int actionType, int hoverid, int spriteId, int spriteId2,
                                    int Width, int Height, int configFrame, int configId, String Tooltip, int hoverId2, int hoverSpriteId,
                                    int hoverSpriteId2, int hoverId3, String hoverDisabledText, String hoverEnabledText, int X, int Y) {
        RSInterface hover = addTabInterface(interfaceID);
        hover.id = interfaceID;
        hover.parentID = interfaceID;
        hover.type = 5;
        hover.atActionType = actionType;
        hover.contentType = 0;
        hover.opacity = 0;
        hover.hoverType = hoverid;
        hover.disabledSprite = Client.cacheSprite[spriteId];
        hover.enabledSprite = Client.cacheSprite[spriteId2];
        hover.width = Width;
        hover.tooltip = Tooltip;
        hover.height = Height;
        hover.valueCompareType = new int[1];
        hover.requiredValues = new int[1];
        hover.valueCompareType[0] = 1;
        hover.requiredValues[0] = configId;
        hover.valueIndexArray = new int[1][3];
        hover.valueIndexArray[0][0] = 5;
        hover.valueIndexArray[0][1] = configFrame;
        hover.valueIndexArray[0][2] = 0;
        hover = addTabInterface(hoverid);
        hover.parentID = hoverid;
        hover.id = hoverid;
        hover.type = 0;
        hover.atActionType = 0;
        hover.width = 550;
        hover.height = 334;
        hover.interfaceShown = true;
        hover.hoverType = -1;
        addSprite(hoverId2, hoverSpriteId, hoverSpriteId2, configId, configFrame);
        addHoverBox(hoverId3, interfaceID, hoverDisabledText, hoverEnabledText, configId, configFrame);
        setChildren(2, hover);
        setBounds(hoverId2, 15, 60, 0, hover);
        setBounds(hoverId3, X, Y, 1, hover);
    }

    public static void addBankHover(int interfaceID, int actionType, int hoverid, int spriteId, int spriteId2,
                                    String NAME, int Width, int Height, int configFrame, int configId, String Tooltip, int hoverId2,
                                    int hoverSpriteId, int hoverSpriteId2, String hoverSpriteName, int hoverId3, String hoverDisabledText,
                                    String hoverEnabledText, int X, int Y, int sprite1, int sprite2) {
        RSInterface hover = addTabInterface(interfaceID);
        hover.id = interfaceID;
        hover.parentID = interfaceID;
        hover.type = 5;
        hover.atActionType = actionType;
        hover.contentType = 0;
        hover.hoverType = hoverid;
        hover.enabledSprite = Client.cacheSprite[sprite1];
        hover.disabledSprite = Client.cacheSprite[sprite2];
        hover.width = Width;
        hover.tooltip = Tooltip;
        hover.height = Height;
        hover.valueCompareType = new int[1];
        hover.requiredValues = new int[1];
        hover.valueCompareType[0] = 1;
        hover.requiredValues[0] = configId;
        hover.valueIndexArray = new int[1][3];
        hover.valueIndexArray[0][0] = 5;
        hover.valueIndexArray[0][1] = configFrame;
        hover.valueIndexArray[0][2] = 0;
        hover = addTabInterface(hoverid);
        hover.parentID = hoverid;
        hover.id = hoverid;
        hover.type = 0;
        hover.atActionType = 0;
        hover.width = 550;
        hover.height = 334;
        hover.interfaceShown = true;
        hover.hoverType = -1;
    }

    public static void addClickableTextHeight(int id, String text, String tooltip, TextDrawingArea tda[], int idx,
                                              int color, boolean center, boolean shadow, int width, int height) {
        RSInterface tab = addTabInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.type = 4;
        tab.atActionType = 1;
        tab.width = width;
        tab.height = height;
        tab.contentType = 0;
        // tab.aByte254 = 0;
        // tab.mOverInterToTrigger = -1;
        tab.centerText = center;
        tab.textShadow = shadow;
        tab.textDrawingAreas = tda[idx];
        tab.message = text;
        tab.enabledMessage = "";
        tab.disabledColor = color;
        tab.enabledColor = 0;
        tab.disabledMouseOverColor = 0xffffff;
        tab.enabledMouseOverColor = 0;
        tab.tooltip = tooltip;
    }

    public static void addSprite(int ID, int i, int i2, String name, int configId, int configFrame, int sprite1,
                                 int sprite2) {
        RSInterface Tab = addTabInterface(ID);
        Tab.id = ID;
        Tab.parentID = ID;
        Tab.type = 5;
        Tab.atActionType = 0;
        Tab.contentType = 0;
        Tab.width = 512;
        Tab.height = 334;
        Tab.hoverType = -1;
        Tab.valueCompareType = new int[1];
        Tab.requiredValues = new int[1];
        Tab.valueCompareType[0] = 1;
        Tab.requiredValues[0] = configId;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        if (name == null) {
            /*
             * Tab.itemSpriteZoom1 = -1; Tab.itemSpriteId1 = i; Tab.itemSpriteZoom2 = 70;
             * Tab.itemSpriteId2 = i2;
             */
        } else {
            // Tab.disabledSprite = imageLoader(i, name);
            // Tab.enabledSprite = imageLoader(i2, name);
            Tab.enabledSprite = Client.cacheSprite[sprite1];
            Tab.disabledSprite = Client.cacheSprite[sprite2];
        }
    }

    public static void addPet(int ID) {
        RSInterface petCanvas = interfaceCache[ID] = new RSInterface();
        petCanvas.id = ID;
        petCanvas.parentID = ID;
        petCanvas.type = 6;
        petCanvas.atActionType = 0;
        petCanvas.contentType = 3291;
        petCanvas.width = 136;
        petCanvas.height = 168;
        petCanvas.hoverType = 0;
        petCanvas.modelZoom = 875;
        petCanvas.modelRotationY = 40;
        petCanvas.modelRotationX = 1800;
        petCanvas.mediaType = 2;
        petCanvas.mediaID = 4000;
    }

    public static void addBobStorage(int index) {
        RSInterface rsi = interfaceCache[index] = new RSInterface();
        rsi.actions = new String[5];
        rsi.spritesX = new int[20];
        rsi.invStackSizes = new int[30];
        rsi.inv = new int[30];
        rsi.spritesY = new int[20];

        rsi.children = new int[0];
        rsi.childX = new int[0];
        rsi.childY = new int[0];

        rsi.actions[0] = "Remove 1";
        rsi.actions[1] = "Remove 5";
        rsi.actions[2] = "Remove 10";
        rsi.actions[3] = "Remove All";
        rsi.actions[4] = "Remove X";
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        // rsi.aBoolean251 = false;
        rsi.filled = false;
        rsi.deletesTargetSlot = false;
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.allowSwapItems = true;
        // rsi.interfaceShown = false;
        rsi.type = -1;
        rsi.invSpritePadX = 24;
        rsi.invSpritePadY = 24;
        rsi.height = 5;
        rsi.width = 6;
        rsi.parentID = 2702;
        rsi.id = index;
        rsi.type = 2;
    }

    public static void addFamiliarHead(int interfaceID, int width, int height, int zoom) {
        RSInterface rsi = addTabInterface(interfaceID);
        rsi.type = 6;
        rsi.mediaType = 2;
        rsi.mediaID = 4000;
        rsi.modelZoom = zoom;
        rsi.modelRotationY = 40;
        rsi.modelRotationX = 1800;
        rsi.height = height;
        rsi.width = width;
    }

    public static void addButton(int id, int sid, String tooltip, int w, int h) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.atActionType = 1;
        tab.contentType = 0;
        tab.opacity = (byte) 0;
        tab.hoverType = 52;
        tab.disabledSprite = Client.cacheSprite[sid];
        tab.width = w;
        tab.height = h;
        tab.tooltip = tooltip;
    }

    public static void addSkillChatSprite(int id, int skill) {
        addSpriteLoader(id, 755 + skill);
    }

    public static void addRectangle(int id, int opacity, int color, boolean filled, int width, int height) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.disabledColor = color;
        tab.filled = filled;
        tab.id = id;
        tab.parentID = id;
        tab.type = 3;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = (byte) opacity;
        tab.width = width;
        tab.height = height;
    }

    public static void addRectangle(int id, int width, int height, int colour, int alpha, boolean filled) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.disabledColor = colour;
        tab.filled = filled;
        tab.id = id;
        tab.parentID = id;
        tab.type = 3;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = (byte) alpha;
        tab.width = width;
        tab.height = height;
    }

    public static void addBankItem(int index) {
        RSInterface rsi = interfaceCache[index] = new RSInterface();
        rsi.actions = new String[5];
        rsi.spritesX = new int[20];
        rsi.invStackSizes = new int[30];
        rsi.inv = new int[30];
        rsi.spritesY = new int[20];
        rsi.children = new int[0];
        rsi.childX = new int[0];
        rsi.childY = new int[0];
        rsi.invSpritePadX = 24;
        rsi.invSpritePadY = 24;
        rsi.height = 5;
        rsi.width = 6;
        rsi.id = index;
        rsi.type = 2;
        rsi.hideStackSize = true;
        rsi.hideExamine = true;
    }

    public static void itemGroup1(int id, int w, int h, int x, int y, boolean drag, boolean examine) {
        RSInterface rsi = addInterface(id);
        rsi.width = w;
        rsi.height = h;
        rsi.inv = new int[w * h];
        rsi.invStackSizes = new int[w * h];
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.invSpritePadX = x;
        rsi.invSpritePadY = y;
        rsi.spritesX = new int[20];
        rsi.spritesY = new int[20];
        rsi.sprites = new Sprite[20];
        /*
         * rsi.aBoolean235 = drag; rsi.examine = examine;
         */
        rsi.type = 2;
    }

    public static void addButtonWSpriteLoader(int id, int sprite, String tooltip, int w, int h) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.atActionType = 1;
        tab.contentType = 0;
        tab.opacity = (byte) 0;
        tab.hoverType = 52;
        if (sprite != -1) {
            tab.disabledSprite = Client.cacheSprite[sprite];
            tab.enabledSprite = Client.cacheSprite[sprite];
        }
        tab.width = w;
        tab.height = h;
        tab.tooltip = tooltip;
    }

    public static void addHoverButtonWSpriteLoader(int i, int spriteId, int width, int height, String text,
                                                   int contentType, int hoverOver, int aT) {// hoverable
        // button
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = aT;
        tab.contentType = contentType;
        tab.opacity = 0;
        tab.hoverType = hoverOver;
        tab.enabledSprite = Client.cacheSprite[spriteId];
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.width = width;
        tab.height = height;
        tab.tooltip = text;
    }

    public static void createHover(int id, int x, int width) {
        RSInterface hover = addInterface(id);
        hover.type = 10;
        hover.contentType = x;
        hover.width = width;
        hover.height = 28;
    }

    public static void createSkillHover(int id, int x) {
        RSInterface hover = addInterface(id);
        hover.type = 10;
        hover.contentType = x;
        hover.width = 60;
        hover.height = 28;
    }

    public static void addSkillText(int id, boolean max, int skill) {
        RSInterface text = addInterface(id);
        text.id = id;
        text.parentID = id;
        text.type = 4;
        text.atActionType = 0;
        text.width = 15;
        text.height = 12;
        text.textDrawingAreas = fonts[0];
        text.textShadow = true;
        text.centerText = true;
        text.disabledColor = 16776960;
        if (!max) {
            text.valueIndexArray = new int[1][];
            text.valueIndexArray[0] = new int[3];
            text.valueIndexArray[0][0] = 1;
            text.valueIndexArray[0][1] = skill;
            text.valueIndexArray[0][2] = 0;
        } else {
            text.valueIndexArray = new int[2][];
            text.valueIndexArray[0] = new int[3];
            text.valueIndexArray[0][0] = 1;
            text.valueIndexArray[0][1] = skill;
            text.valueIndexArray[0][2] = 0;
            text.valueIndexArray[1] = new int[1];
            text.valueIndexArray[1][0] = 0;
        }
        text.message = "%1";
    }

    public static void addButton(int id, int spriteId, int spriteId1, String tooltip) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 553;
        tab.atActionType = 1;
        tab.actionType = spriteId;
        tab.opacity = (byte) 0;
        tab.isMouseoverTriggered = 52;
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.enabledSprite = Client.cacheSprite[spriteId1];
        tab.width = tab.disabledSprite.myWidth;
        tab.height = tab.enabledSprite.myHeight;
        tab.tooltip = tooltip;
    }

    public static void addButton(int ID, int type, int hoverID, int dS, int eS, int W, int H, String text,
                                 int configFrame, int configId) {
        RSInterface rsinterface = addInterface(ID);
        rsinterface.id = ID;
        rsinterface.parentID = ID;
        rsinterface.type = 5;
        rsinterface.atActionType = type;
        rsinterface.opacity = 0;
        rsinterface.hoverType = hoverID;
        if (dS >= 0) {
            rsinterface.disabledSprite = Client.cacheSprite[dS];
        }
        if (eS >= 0) {
            rsinterface.enabledSprite = Client.cacheSprite[eS];
        }
        rsinterface.width = W;
        rsinterface.height = H;
        rsinterface.tooltip = text;
        rsinterface.interfaceShown = true;
        rsinterface.valueCompareType = new int[1];
        rsinterface.requiredValues = new int[1];
        rsinterface.valueCompareType[0] = 1;
        rsinterface.requiredValues[0] = configId;
        rsinterface.valueIndexArray = new int[1][3];
        rsinterface.valueIndexArray[0][0] = 5;
        rsinterface.valueIndexArray[0][1] = configFrame;
        rsinterface.valueIndexArray[0][2] = 0;
    }

    private static void addCacheSprite(int id, int sprite1, int sprite2, String sprites) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.disabledSprite = method207(sprite1, aClass44, sprites);
        rsi.enabledSprite = method207(sprite2, aClass44, sprites);
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
    }

    public static void addAPlayerHead(int interfaceID, Account a) {
        RSInterface rsi = addTabInterface(interfaceID);
        rsi.type = 6;
        rsi.mediaType = (a.getIDKHead() <= 0 ? 11 : 10);
        int anim = headAnims[Client.getRandom(headAnims.length - 1, false)];
        rsi.enabledAnimationId = rsi.disabledAnimationId = anim;
        rsi.mediaID = (a.getIDKHead() <= 0 ? a.getHelmet() : a.getIDKHead());
        rsi.plrJaw = a.getJaw();
        if (a.getGender() == 1)
            rsi.plrJaw = -1;
        rsi.gender = a.getGender();
        rsi.modelZoom = 2000;
        rsi.modelRotationY = 40;
        rsi.modelRotationX = 1800;
        rsi.height = 150;
        rsi.width = 150;
    }

    private final static int[] headAnims = new int[]{9846, 9742, 9827, 9841, 9851, 9745, 9785, 9805, 9810, 9815, 9820,
            9860, 9835, 9845, 9850, 9855, 9864, 9851};
    public int plrJaw, gender;

    private int textSize;

    private int colorEnabled;

    private int hoverColorDisabled;

    private int hoverColorEnabled;

    public static void addCharEquipment(int ID) {
        RSInterface t = interfaceCache[ID] = new RSInterface();
        t.id = ID;
        t.parentID = ID;
        t.type = 6;
        t.atActionType = 0;
        t.contentType = 328;
        t.width = 136;
        t.height = 168;
        t.opacity = 0;
        t.hoverType = 0;
        t.modelZoom = 560;
        t.modelRotationY = 500;
        t.modelRotationX = 0;
        t.disabledAnimationId = -1;
        t.enabledAnimationId = -1;
    }

    public static void addChar(int ID) {
        RSInterface t = interfaceCache[ID] = new RSInterface();
        t.id = ID;
        t.parentID = ID;
        t.type = 6;
        t.atActionType = 0;
        t.contentType = 328;
        t.width = 136;
        t.height = 168;
        t.opacity = 0;
        t.hoverType = 0;
        t.modelZoom = 560;
        t.modelRotationY = 150;
        t.modelRotationX = 0;
        t.disabledAnimationId = -1;
        t.enabledAnimationId = -1;
    }

    public static void addConfigButton(int ID, int pID, int bID, int bID2, int width, int height, String tT,
                                       int configID, int aT, int configFrame) {
        RSInterface Tab = addTabInterface(ID);
        Tab.parentID = pID;
        Tab.id = ID;
        Tab.type = 5;
        Tab.atActionType = aT;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.opacity = 0;
        Tab.hoverType = -1;
        Tab.valueCompareType = new int[1];
        Tab.requiredValues = new int[1];
        Tab.valueCompareType[0] = 1;
        Tab.requiredValues[0] = configID;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        Tab.disabledSprite = Client.cacheSprite[bID];
        Tab.enabledSprite = Client.cacheSprite[bID2];
        Tab.tooltip = tT;
    }

    public static void addText(int childId, String text, int color, boolean center, boolean shadow,
                               TextDrawingArea rsFont) {
        RSInterface rsi = RSInterface.addInterface(childId);
        rsi.parentID = childId;
        rsi.id = childId;
        rsi.type = 4;
        rsi.atActionType = 0;
        rsi.width = 0;
        rsi.height = 11;
        rsi.contentType = 0;
        rsi.opacity = 0;
        rsi.hoverType = -1;
        rsi.centerText = center;
        rsi.textShadow = shadow;
        rsi.textDrawingAreas = rsFont;
        rsi.message = "";
        rsi.disabledColor = color;
    }

    public static void addCloseButtonSmall(int child, int hoverChild, int hoverImageChild) {
        addHoverButtonWSpriteLoader(child, 714, 16, 16, "Close", 250, hoverChild, 3);
        addHoveredImageWSpriteLoader(hoverChild, 715, 16, 16, hoverImageChild);
    }

    public static void addBigButtonSmall(int child, int hoverChild, int hoverImageChild) {
        addHoverButtonWSpriteLoader(child, 1208, 21, 21, "Close", 250, hoverChild, 3);
        addHoveredImageWSpriteLoader(hoverChild, 1209, 21, 21, hoverImageChild);
    }

    public static void zoneTasksInterface(TextDrawingArea[] tda) {

        RSInterface main = addInterface(19720);
        addSpriteLoader(19721, 1356);
        addCloseButtonSmall(19741, 19742, 19743);
        addText(19744, "Progression Manager", tda, 1, ColorConstants.SKY_BLUE); // open bsp
        main.totalChildren(5);
        main.child(0, 19721, 10, 10);
        main.child(1, 19740, 0, 45);
        main.child(2, 19741, 465, 18);
        main.child(3, 19742, 465, 18);
        main.child(4, 19744, 210, 19);

        RSInterface scroll = addInterface(19740);
        scroll.width = 481;
        scroll.height = 265;
        scroll.scrollMax = 1110;
        scroll.totalChildren(391);
        int xPos = 25;
        int yPos = 5;
        for (int i = 0; i < 30; i++) { // child 0-29 - main sprite.
            addSpriteLoader(19745 + i, 1357);
            scroll.child(i, 19745 + i, xPos, yPos);
            yPos += 65;
        }

        int textY = 10;
        int childStart = 30;
        for (int i = 0; i < 30; i++) {
            addText(19785 + i, "", tda, 2, ColorConstants.ORANGE);
            scroll.child(childStart + i, 19785 + i, 35, textY);
            textY += 65;
        }
        int descY = 28;
        int descChild = 60;
        for (int i = 0; i < 30; i++) {
            addText(19830 + i, "", tda, 0, ColorConstants.WHITE);
            scroll.child(descChild + i, 19830 + i, 35, descY);
            descY += 65;
        }
        int descY1 = 42;
        int descChild1 = 90;
        for (int i = 0; i < 30; i++) {
            addText(19860 + i, "", tda, 0, ColorConstants.WHITE);
            scroll.child(descChild1 + i, 19860 + i, 35, descY1);
            descY1 += 65;
        }

        int descY2 = 57;
        int descChild2 = 120;
        for (int i = 0; i < 30; i++) {
            addText(19890 + i, "", tda, 0, ColorConstants.WHITE);
            scroll.child(descChild2 + i, 19890 + i, 35, descY2);
            descY2 += 65;
        }
        int spriteY = 8;
        int spriteChild = 150;
        for (int i = 0; i < 30; i++) {
            addSpriteLoader(19925 + i, 1358);
            scroll.child(spriteChild + i, 19925 + i, 150, spriteY);
            spriteY += 65;
        }
        int spriteY1 = 8;
        int spriteChild1 = 180;

        for (int i = 0; i < 30; i++) {
            addSpriteLoader(19955 + i, 1358);
            scroll.child(spriteChild1 + i, 19925 + i, 150 + 85, spriteY1);
            spriteY1 += 65;
        }
        int spriteY2 = 8;
        int spriteChild2 = 210;
        for (int i = 0; i < 30; i++) {
            addSpriteLoader(19985 + i, 1358);
            scroll.child(spriteChild2 + i, 19925 + i, 235 + 85, spriteY2);
            spriteY2 += 65;
        }

        int claimY = 30;
        int claimChild = 240;

        for (int i = 0; i < 30; i++) {
            addButtonWSpriteLoader(19960 + i, 1359, "Claim Reward");
            scroll.child(claimChild + i, 19960 + i, 379, claimY);
            claimY += 65;
        }

        int claimTextY = 33;
        int claimTextChild = 270;

        for (int i = 0; i < 30; i++) {
            addText(19990 + i, "Claim", tda, 1, ColorConstants.ORANGE);
            scroll.child(claimTextChild + i, 19990 + i, 400, claimTextY);
            claimTextY += 65;
        }

        int percenTextY = 54;
        int percentChild = 300;

        for (int i = 0; i < 30; i++) {
            addText(20030 + i, "0%", tda, 0, ColorConstants.WHITE, true, true);
            scroll.child(percentChild + i, 20030 + i, 172, percenTextY);
            percenTextY += 65;
        }
        int percenTextY1 = 54;
        int percentChild1 = 330;
        for (int i = 0; i < 30; i++) {
            addText(20060 + i, "0%", tda, 0, ColorConstants.WHITE, true, true);
            scroll.child(percentChild1 + i, 20060 + i, 172 + 85, percenTextY1);
            percenTextY1 += 65;
        }
        int percenTextY2 = 54;
        int percentChild2 = 360;
        for (int i = 0; i < 30; i++) {
            addText(20090 + i, "0%", tda, 0, ColorConstants.WHITE, true, true);
            scroll.child(percentChild2 + i, 20090 + i, 172 + 170, percenTextY2);
            percenTextY2 += 65;
        }

        scroll.child(390, 20125, 153, 12);

        addToItemGroup(20125, 3, 30, 55, 33, true, new String[]{null, null, null, null, null});

    }

    /**
     * @author Suic
     * @since June
     */
    public static void starterTaskInterface(TextDrawingArea[] tda) {
        RSInterface main = addInterface(53200);
        addSpriteLoader(53201, 1255);
        addButtonWSpriteLoader(53250, 580, "Close Interface");
        addText(53202, "Starter Book Tasks", ColorConstants.ORANGE, true, true, 100, tda, 2);
        addText(53203, "Task List", ColorConstants.ORANGE, true, true, 100, tda, 1);
        addText(53220, "Click on a task", 0xffa256, false, true, 100, tda, 1);
        addText(53221, "To see it's Description", ColorConstants.ORANGE, false, true, 100, tda, 1);
        addText(53222, "NOTE: you get the reward", ColorConstants.ORANGE, false, true, 100, tda, 1);
        addText(53223, "Once you complete all tasks", ColorConstants.ORANGE, false, true, 100, tda, 1);
        addText(53224, "Task Description", ColorConstants.ORANGE, false, true, 100, tda, 1);
        addText(53225, "", 0x1eb0b2, false, true, 100, tda, 0);
        addText(53226, "", 0xfc2d11, false, true, 100, tda, 0);
        addText(53227, "Complete all tasks for these rewards", 0x09f90d, false, false, 100, tda, 1);
        addText(53230, "Claim Reward", 0x5aff51, false, true, 100, tda, 2);
        addButtonWSpriteLoader(53229, 1256, "Claim Rewards");
        main.totalChildren(16);
        main.child(0, 53201, 13, 15);
        main.child(1, 53202, 250, 18);
        main.child(2, 53203, 97, 42);
        main.child(3, 53204, 205, 236);
        main.child(4, 53206, 0, 64);

        /**
         * Text childs for description
         */
        main.child(5, 53220, 215, 85);
        main.child(6, 53221, 215, 105);
        main.child(7, 53222, 215, 125);
        main.child(8, 53223, 215, 145);
        main.child(9, 53224, 275, 55);
        main.child(10, 53225, 215, 190);
        main.child(11, 53226, 215, 165);
        main.child(12, 53227, 310 - 50, 220);
        main.child(13, 53229, 22, 278); // button claim reward
        main.child(14, 53230, 59, 285); // text claim reward
        main.child(15, 53250, 484, 16);

        RSInterface scroll = addTabInterface(53204); // item scroll
        scroll.totalChildren(1);
        scroll.child(0, 53205, 8, 5);
        scroll.addToItemGroup(53205, 6, 6, 10, 15, true, new String[5]);
        scroll.height = 73;
        scroll.width = 255;
        scroll.scrollMax = 250;

        RSInterface tasks = addTabInterface(53206); // task scroll
        tasks.totalChildren(13);
        tasks.height = 215;
        tasks.width = 167;
        tasks.scrollMax = 500;
        int id = 53207;
        int index = 0;
        int child = 0;
        int x = 27;
        int y = 0;
        for (int i = 0; i < 13; i++) {
            addClickableText(id, "" + id, "Select", tda, 1, 0xeb981f, false, true, 632);
            tasks.child(child++, id++, x, y + 2);

            y += 15;
        }
    }


    private static void customCombiner(TextDrawingArea[] tda) {
        final int STARTING_POINT = 30330;
        RSInterface main = addInterface(STARTING_POINT);
        addSpriteLoader(STARTING_POINT + 1, 1538);

        //addHoverButtonWSpriteLoader(STARTING_POINT + 2, 1457, 127, 34, "Invent", -1, STARTING_POINT + 3, 1);
        // addHoveredImageWSpriteLoader(STARTING_POINT + 3, 1458, 127, 34, STARTING_POINT + 4);
        //addText(STARTING_POINT + 5, "Invent", tda, 2, ColorConstants.ORANGE);

        hoverButton(STARTING_POINT + 2, 1457, 1458, "Upgrade", 2, 0xff8624, "Upgrade");

        addText(STARTING_POINT + 3, "", tda, 2, ColorConstants.ORANGE);
        addText(STARTING_POINT + 5, "", tda, 2, ColorConstants.ORANGE);


        addToItemGroup(STARTING_POINT + 6, 1, 1, 1, 1, true, new String[]{null, null, null, null, null});
        addText(STARTING_POINT + 7, "", tda, 2, ColorConstants.ORANGE);
        addText(STARTING_POINT + 8, "Ingredients required", tda, 1, ColorConstants.ORANGE, true);
        addText(STARTING_POINT + 9, "Product", tda, 1, ColorConstants.ORANGE, true);

        addToItemGroup(STARTING_POINT + 10, 4, 4, 9, 7, true, new String[]{null, null, null, null, null});
        addCloseButtonSmall(STARTING_POINT + 11, STARTING_POINT + 12, STARTING_POINT + 13);
        addText(STARTING_POINT + 14, "", tda, 2, ColorConstants.ORANGE);
        addText(STARTING_POINT + 15, "Item to create", tda, 1, ColorConstants.ORANGE, true);
        addText(STARTING_POINT + 16, "", tda, 0, ColorConstants.WHITE);
        addText(STARTING_POINT + 17, "Success Rate: @yel@100%", tda, 1, ColorConstants.WHITE, true);

        main.totalChildren(30);
        main.child(0, STARTING_POINT + 1, 10, 10);
        main.child(1, STARTING_POINT + 2, 343, 278);
        main.child(2, STARTING_POINT + 3, 280, 273);
        main.child(3, STARTING_POINT + 5, 319, 282);
        main.child(4, STARTING_POINT + 6, 385, 232);
        main.child(5, STARTING_POINT + 20, 32, 86);
        main.child(6, STARTING_POINT + 7, 40, 20);
        main.child(7, STARTING_POINT + 8, 401, 68);
        main.child(8, STARTING_POINT + 9, 401, 210);
        main.child(9, STARTING_POINT + 10, 320, 90);
        main.child(10, STARTING_POINT + 11, 480, 13);
        main.child(11, STARTING_POINT + 12, 480, 13);
        main.child(12, STARTING_POINT + 14, 150, 14);
        main.child(13, STARTING_POINT + 15, 169, 68);
        main.child(14, STARTING_POINT + 16, 27, 43 + 13);
        main.child(15, STARTING_POINT + 17, 401, 190);


        main.child(16, 62241, 21, 35);// 00 hover
        main.child(17, 62235, 21 + (68 * 1), 35);//01 hover
        main.child(18, 62236, 21 + (68 * 2), 35);//02 hover
        main.child(19, 62240, 21 + (68 * 3), 35);//03 hover
        main.child(20, 62243, 21 + (68 * 4), 35);// 04 hover
        main.child(21, 62244, 21 + (68 * 5), 35);// MAX hover
        main.child(22, 62204, 21 + (68 * 6), 35); // empty
        main.child(23, 62242, 28, 39);// 00 text
        main.child(24, 62237, 28 + (68 * 1), 39);// 01 text
        main.child(25, 62238, 28 + (68 * 2), 39);// 02 text
        main.child(26, 62239, 28 + (68 * 3), 39);//03 text
        main.child(27, 62245, 28 + (68 * 4), 39);// 04 text
        main.child(28, 62246, 28 + (68 * 5), 39);// max text
        main.child(29, 62206, 28 + (68 * 6), 39);//upgrade x1 text

        RSInterface scroll = addInterface(STARTING_POINT + 20);
        scroll.width = 273 - 16;
        scroll.height = 218;
        scroll.scrollMax =  3 + (37 * 17);

        scroll.totalChildren(1);
        scroll.child(0, STARTING_POINT + 21, 7, 3);

        addToItemGroup(STARTING_POINT + 21, 6, 20, 10, 5, true, new String[]{"Select", null, null, null, null});
    }

    protected static void combiner(TextDrawingArea[] tda) {
        RSInterface main = addInterface(19700);

        addSpriteLoader(19701, 1340);
        addText(19702, "Grand Max Invention Interface", tda, 2, ColorConstants.ORANGE, true, true);

        addText(19703, "Grand Max items", tda, 0, ColorConstants.BLACK, true, true);

        addText(19704, "Grand Max Requirements", tda, 0, ColorConstants.BLACK, true, true);

        addHoverText(19705, "Close", "Close", tda, 1, 0xACACAC, false, true, 100, 14);

        RSInterface scroll = addInterface(19800);
        scroll.width = 200;
        scroll.height = 185;
        scroll.scrollMax = 400;

        addClickableText(19706, "Invent Item", "Invent Item", tda, 1, ColorConstants.BLACK, true, true, 100);
        addClickableText(19707, "Invent Item", "Invent Item", tda, 1, ColorConstants.BLACK, true, true, 100);
        addClickableText(19708, "Invent Item", "Invent Item", tda, 1, ColorConstants.BLACK, true, true, 100);
        addClickableText(19709, "Invent Item", "Invent Item", tda, 1, ColorConstants.BLACK, true, true, 100);

        /**
         * Crafted item itemgroup
         */

        addToItemGroup(19710, 1, 4, 0, 23, true, new String[]{null, null, null, null, null}); // int id, int w, int
        // h, int x, int y,
        // boolean
        // hasActions,
        // String[] actions

        /**
         * Requirements itemgroup
         */

        addToItemGroup(19711, 4, 12, 28, 21, true, new String[]{null, null, null, null, null});


        main.totalChildren(11);

        main.child(0, 19701, 2, 10);
        main.child(1, 19702, 270, 18);
        main.child(2, 19703, 60, 60);
        main.child(3, 19704, 245, 60);
        main.child(4, 19705, 440, 18);

        main.child(5, 19706, 385, 95);
        main.child(6, 19707, 385, 145);
        main.child(7, 19708, 385, 200);
        main.child(8, 19709, 385, 258);
        main.child(9, 19710, 40, 80);
        main.child(10, 19711, 132, 88);
    }

    public static void addHoverImage(int i, int j, int k, String name) {
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.width = 512;
        tab.height = 334;
        tab.opacity = 0;
        tab.hoverType = 52;
        tab.enabledSprite = method207(j, aClass44, name);
        tab.disabledSprite = method207(k, aClass44, name);
    }

    public static void addHoverImage(int a, int i, int j, int k, String name) {
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.width = 512;
        tab.height = 334;
        tab.opacity = 0;
        tab.hoverType = 52;
        tab.disabledSpriteId = j;
        tab.enabledSpriteId = k;
        // tab.setSprite = 638;
        // tab.savedFirstSpriteId = j;
    }

    public static void addHoveredButton(int i, String imageName, int j, int w, int h, int IMAGEID) {
        RSInterface tab = addTabInterface(i);
        tab.parentID = i;
        tab.id = i;
        tab.type = 0;
        tab.atActionType = 0;
        tab.width = w;
        tab.height = h;
        tab.interfaceShown = true;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.scrollMax = 0;
        if (i != 24655) {
            addHoverImage(IMAGEID, j, j, imageName);
        } else {
            addHoverImage(1, IMAGEID, j, j, imageName);
        }
        tab.totalChildren(1);
        tab.child(0, IMAGEID, 0, 0);
    }

    public static void addHovered(int i, int j, String imageName, int w, int h, int IMAGEID) {
        addHoveredButton(i, imageName, j, w, h, IMAGEID);
    }

    private static void addHover(int interfaceId, int actionType, int contentType, int hoverid, int spriteId, int W,
                                 int H, String tip) {
        RSInterface rsinterfaceHover = addInterface(interfaceId);
        rsinterfaceHover.id = interfaceId;
        rsinterfaceHover.parentID = interfaceId;
        rsinterfaceHover.type = 5;
        rsinterfaceHover.atActionType = actionType;
        rsinterfaceHover.contentType = contentType;
        rsinterfaceHover.hoverType = hoverid;
        rsinterfaceHover.disabledSprite = rsinterfaceHover.enabledSprite = Client.cacheSprite[spriteId];
        rsinterfaceHover.width = W;
        rsinterfaceHover.height = H;
        rsinterfaceHover.tooltip = tip;
    }

    private static void addHoverBox(int id, int ParentID, String text, String text2, int configId, int configFrame) {
        RSInterface rsi = addTabInterface(id);
        rsi.id = id;
        rsi.parentID = ParentID;
        rsi.type = 8;
        rsi.enabledMessage = text;// disabledText
        rsi.tooltipBoxText = text2;
        rsi.message = text;
        rsi.valueCompareType = new int[1];
        rsi.requiredValues = new int[1];
        rsi.valueCompareType[0] = 1;
        rsi.requiredValues[0] = configId;
        rsi.valueIndexArray = new int[1][3];
        rsi.valueIndexArray[0][0] = 5;
        rsi.valueIndexArray[0][1] = configFrame;
        rsi.valueIndexArray[0][2] = 0;
    }

    public static void addItemOnInterface(int childId, int interfaceId, String[] options) {
        RSInterface rsi = interfaceCache[childId] = new RSInterface();
        rsi.actions = new String[10];
        rsi.spritesX = new int[20];
        rsi.inv = new int[30];
        rsi.invStackSizes = new int[25];
        rsi.spritesY = new int[20];
        rsi.children = new int[0];
        rsi.childX = new int[0];
        rsi.childY = new int[0];
        for (int i = 0; i < rsi.actions.length; i++) {
            if (i < options.length) {
                if (options[i] != null) {
                    rsi.actions[i] = options[i];
                }
            }
        }
        rsi.centerText = true;
        rsi.filled = false;
        rsi.deletesTargetSlot = false;
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.allowSwapItems = false;
        rsi.invSpritePadX = 23;
        rsi.invSpritePadY = 22;
        rsi.height = 5;
        rsi.width = 6;
        rsi.parentID = interfaceId;
        rsi.id = childId;
        rsi.type = 2;
    }

    public static void addHoverText(int id, String text, String tooltip, TextDrawingArea tda[], int idx, int color,
                                    boolean center, boolean textShadowed, int width, int height) {
        RSInterface rsinterface = addInterface(id);
        rsinterface.id = id;
        rsinterface.parentID = id;
        rsinterface.type = 4;
        rsinterface.atActionType = 1;
        rsinterface.width = width;
        rsinterface.height = height;
        rsinterface.contentType = 0;
        rsinterface.opacity = 0;
        rsinterface.hoverType = -1;
        rsinterface.centerText = center;
        rsinterface.textShadow = textShadowed;
        rsinterface.textDrawingAreas = tda[idx];
        rsinterface.message = text;
        rsinterface.disabledColor = color;
        rsinterface.tooltip = tooltip;
    }

    public static void addHoverButton(int interfaceId, int spriteId, int width, int height, String text,
                                      int contentType, int hoverOver, int actionType) {
        // hoverable button
        RSInterface tab = addTabInterface(interfaceId);
        tab.id = interfaceId;
        tab.parentID = interfaceId;
        tab.type = 5;
        tab.atActionType = actionType;
        tab.contentType = contentType;
        tab.opacity = 0;
        tab.hoverType = hoverOver;

        if (spriteId >= 0) {
            tab.disabledSprite = Client.cacheSprite[spriteId];
            tab.enabledSprite = Client.cacheSprite[spriteId];
        }

        tab.width = width;
        tab.height = height;
        tab.tooltip = text;
    }

    public static void addHoverButton(int i, int disabledSprite, int enabledSprite, int width, int height, String text,
                                      int contentType, int hoverOver, int aT) {// hoverable
        // button
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = aT;
        tab.contentType = contentType;
        tab.opacity = 0;
        tab.hoverType = hoverOver;
        tab.disabledSprite = Client.cacheSprite[disabledSprite];
        tab.enabledSprite = Client.cacheSprite[enabledSprite];
        tab.width = width;
        tab.height = height;
        tab.tooltip = text;
    }

    public static void addHoveredButton(int i, int disabledSprite, int enabledSprite, int w, int h, int IMAGEID) {
        RSInterface tab = addTabInterface(i);
        tab.parentID = i;
        tab.id = i;
        tab.type = 0;
        tab.atActionType = 0;
        tab.width = w;
        tab.height = h;
        tab.interfaceShown = true;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.scrollMax = 0;
        addHoverImage(IMAGEID, disabledSprite, enabledSprite);
        tab.totalChildren(1);
        tab.child(0, IMAGEID, 0, 0);
    }

    public static void addHoveredButton(int i, int j, int w, int h, int IMAGEID) {
        // hoverable button
        RSInterface tab = addTabInterface(i);
        tab.parentID = i;
        tab.id = i;
        tab.type = 0;
        tab.atActionType = 0;
        tab.width = w;
        tab.height = h;
        tab.interfaceShown = true;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.scrollMax = 0;
        addHoverImage(IMAGEID, j, j);
        tab.totalChildren(1);
        tab.child(0, IMAGEID, 0, 0);
    }

    private static void addHoverImage(int i, int j, int k) {
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.width = 512;
        tab.height = 334;
        tab.opacity = 0;
        tab.hoverType = 52;
        tab.disabledSprite = Client.cacheSprite[j];
        tab.enabledSprite = Client.cacheSprite[k];
    }

    public static RSInterface addInterface(int id) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.id = id;
        rsi.parentID = id;
        rsi.width = 512;
        rsi.height = 334;
        return rsi;
    }

    // done
    public static void addLunar2RunesSmallBox(int ID, int r1, int r2, int ra1, int ra2, int rune1, int lvl, String name,
                                              String descr, TextDrawingArea[] TDA, int sid, int suo, int type) {
        RSInterface rsInterface = addInterface(ID);
        rsInterface.id = ID;
        rsInterface.parentID = 1151;
        rsInterface.type = 5;
        rsInterface.atActionType = type;
        rsInterface.contentType = 0;
        rsInterface.hoverType = ID + 1;
        rsInterface.spellUsableOn = suo;
        rsInterface.selectedActionName = "Cast On";
        rsInterface.width = 20;
        rsInterface.height = 20;
        rsInterface.tooltip = "Cast @gre@" + name;
        rsInterface.spellName = name;
        rsInterface.valueCompareType = new int[3];
        rsInterface.requiredValues = new int[3];
        rsInterface.valueCompareType[0] = 3;
        rsInterface.requiredValues[0] = ra1;
        rsInterface.valueCompareType[1] = 3;
        rsInterface.requiredValues[1] = ra2;
        rsInterface.valueCompareType[2] = 3;
        rsInterface.requiredValues[2] = lvl;
        rsInterface.valueIndexArray = new int[3][];
        rsInterface.valueIndexArray[0] = new int[4];
        rsInterface.valueIndexArray[0][0] = 4;
        rsInterface.valueIndexArray[0][1] = 3214;
        rsInterface.valueIndexArray[0][2] = r1;
        rsInterface.valueIndexArray[0][3] = 0;
        rsInterface.valueIndexArray[1] = new int[4];
        rsInterface.valueIndexArray[1][0] = 4;
        rsInterface.valueIndexArray[1][1] = 3214;
        rsInterface.valueIndexArray[1][2] = r2;
        rsInterface.valueIndexArray[1][3] = 0;
        rsInterface.valueIndexArray[2] = new int[3];
        rsInterface.valueIndexArray[2][0] = 1;
        rsInterface.valueIndexArray[2][1] = 6;
        rsInterface.valueIndexArray[2][2] = 0;
        rsInterface.disabledSprite = Client.cacheSprite[sid];
        rsInterface.enabledSprite = Client.cacheSprite[sid + 39];
        RSInterface INT = addInterface(ID + 1);
        INT.interfaceShown = true;
        INT.hoverType = -1;
        setChildren(7, INT);
        addLunarSprite(ID + 2, 205);
        setBounds(ID + 2, 0, 0, 0, INT);
        addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true, true, 52, TDA, 1);
        setBounds(ID + 3, 90, 4, 1, INT);
        addText(ID + 4, descr, 0xAF6A1A, true, true, 52, TDA, 0);
        setBounds(ID + 4, 90, 19, 2, INT);
        setBounds(30016, 37, 35, 3, INT);// Rune
        setBounds(rune1, 112, 35, 4, INT);// Rune
        addRuneText(ID + 5, ra1 + 1, r1, TDA);
        setBounds(ID + 5, 50, 66, 5, INT);
        addRuneText(ID + 6, ra2 + 1, r2, TDA);
        setBounds(ID + 6, 123, 66, 6, INT);
    }

    // done
    public static void addLunar3RunesBigBox(int ID, int r1, int r2, int r3, int ra1, int ra2, int ra3, int rune1,
                                            int rune2, int lvl, String name, String descr, TextDrawingArea[] TDA, int sid, int suo, int type) {
        RSInterface rsInterface = addInterface(ID);
        rsInterface.id = ID;
        rsInterface.parentID = 1151;
        rsInterface.type = 5;
        rsInterface.atActionType = type;
        rsInterface.contentType = 0;
        rsInterface.hoverType = ID + 1;
        rsInterface.spellUsableOn = suo;
        rsInterface.selectedActionName = "Cast on";
        rsInterface.width = 20;
        rsInterface.height = 20;
        rsInterface.tooltip = "Cast @gre@" + name;
        rsInterface.spellName = name;
        rsInterface.valueCompareType = new int[4];
        rsInterface.requiredValues = new int[4];
        rsInterface.valueCompareType[0] = 3;
        rsInterface.requiredValues[0] = ra1;
        rsInterface.valueCompareType[1] = 3;
        rsInterface.requiredValues[1] = ra2;
        rsInterface.valueCompareType[2] = 3;
        rsInterface.requiredValues[2] = ra3;
        rsInterface.valueCompareType[3] = 3;
        rsInterface.requiredValues[3] = lvl;
        rsInterface.valueIndexArray = new int[4][];
        rsInterface.valueIndexArray[0] = new int[4];
        rsInterface.valueIndexArray[0][0] = 4;
        rsInterface.valueIndexArray[0][1] = 3214;
        rsInterface.valueIndexArray[0][2] = r1;
        rsInterface.valueIndexArray[0][3] = 0;
        rsInterface.valueIndexArray[1] = new int[4];
        rsInterface.valueIndexArray[1][0] = 4;
        rsInterface.valueIndexArray[1][1] = 3214;
        rsInterface.valueIndexArray[1][2] = r2;
        rsInterface.valueIndexArray[1][3] = 0;
        rsInterface.valueIndexArray[2] = new int[4];
        rsInterface.valueIndexArray[2][0] = 4;
        rsInterface.valueIndexArray[2][1] = 3214;
        rsInterface.valueIndexArray[2][2] = r3;
        rsInterface.valueIndexArray[2][3] = 0;
        rsInterface.valueIndexArray[3] = new int[3];
        rsInterface.valueIndexArray[3][0] = 1;
        rsInterface.valueIndexArray[3][1] = 6;
        rsInterface.valueIndexArray[3][2] = 0;
        rsInterface.disabledSprite = Client.cacheSprite[sid];
        rsInterface.enabledSprite = Client.cacheSprite[sid + 39];
        RSInterface INT = addInterface(ID + 1);
        INT.interfaceShown = true;
        INT.hoverType = -1;
        setChildren(9, INT);
        addLunarSprite(ID + 2, 206);
        setBounds(ID + 2, 0, 0, 0, INT);
        addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true, true, 52, TDA, 1);
        setBounds(ID + 3, 90, 4, 1, INT);
        addText(ID + 4, descr, 0xAF6A1A, true, true, 52, TDA, 0);
        setBounds(ID + 4, 90, 21, 2, INT);
        setBounds(30016, 14, 48, 3, INT);
        setBounds(rune1, 74, 48, 4, INT);
        setBounds(rune2, 130, 48, 5, INT);
        addRuneText(ID + 5, ra1 + 1, r1, TDA);
        setBounds(ID + 5, 26, 79, 6, INT);
        addRuneText(ID + 6, ra2 + 1, r2, TDA);
        setBounds(ID + 6, 87, 79, 7, INT);
        addRuneText(ID + 7, ra3 + 1, r3, TDA);
        setBounds(ID + 7, 142, 79, 8, INT);
    }

    // done
    public static void addLunar3RunesLargeBox(int ID, int r1, int r2, int r3, int ra1, int ra2, int ra3, int rune1,
                                              int rune2, int lvl, String name, String descr, TextDrawingArea[] TDA, int sid, int suo, int type) {
        RSInterface rsInterface = addInterface(ID);
        rsInterface.id = ID;
        rsInterface.parentID = 1151;
        rsInterface.type = 5;
        rsInterface.atActionType = type;
        rsInterface.contentType = 0;
        rsInterface.hoverType = ID + 1;
        rsInterface.spellUsableOn = suo;
        rsInterface.selectedActionName = "Cast on";
        rsInterface.width = 20;
        rsInterface.height = 20;
        rsInterface.tooltip = "Cast @gre@" + name;
        rsInterface.spellName = name;
        rsInterface.valueCompareType = new int[4];
        rsInterface.requiredValues = new int[4];
        rsInterface.valueCompareType[0] = 3;
        rsInterface.requiredValues[0] = ra1;
        rsInterface.valueCompareType[1] = 3;
        rsInterface.requiredValues[1] = ra2;
        rsInterface.valueCompareType[2] = 3;
        rsInterface.requiredValues[2] = ra3;
        rsInterface.valueCompareType[3] = 3;
        rsInterface.requiredValues[3] = lvl;
        rsInterface.valueIndexArray = new int[4][];
        rsInterface.valueIndexArray[0] = new int[4];
        rsInterface.valueIndexArray[0][0] = 4;
        rsInterface.valueIndexArray[0][1] = 3214;
        rsInterface.valueIndexArray[0][2] = r1;
        rsInterface.valueIndexArray[0][3] = 0;
        rsInterface.valueIndexArray[1] = new int[4];
        rsInterface.valueIndexArray[1][0] = 4;
        rsInterface.valueIndexArray[1][1] = 3214;
        rsInterface.valueIndexArray[1][2] = r2;
        rsInterface.valueIndexArray[1][3] = 0;
        rsInterface.valueIndexArray[2] = new int[4];
        rsInterface.valueIndexArray[2][0] = 4;
        rsInterface.valueIndexArray[2][1] = 3214;
        rsInterface.valueIndexArray[2][2] = r3;
        rsInterface.valueIndexArray[2][3] = 0;
        rsInterface.valueIndexArray[3] = new int[3];
        rsInterface.valueIndexArray[3][0] = 1;
        rsInterface.valueIndexArray[3][1] = 6;
        rsInterface.valueIndexArray[3][2] = 0;
        rsInterface.disabledSprite = Client.cacheSprite[sid];
        rsInterface.enabledSprite = Client.cacheSprite[sid + 39];
        RSInterface INT = addInterface(ID + 1);
        INT.interfaceShown = true;
        INT.hoverType = -1;
        setChildren(9, INT);
        addLunarSprite(ID + 2, 207);
        setBounds(ID + 2, 0, 0, 0, INT);
        addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true, true, 52, TDA, 1);
        setBounds(ID + 3, 90, 4, 1, INT);
        addText(ID + 4, descr, 0xAF6A1A, true, true, 52, TDA, 0);
        setBounds(ID + 4, 90, 34, 2, INT);
        setBounds(30016, 14, 61, 3, INT);
        setBounds(rune1, 74, 61, 4, INT);
        setBounds(rune2, 130, 61, 5, INT);
        addRuneText(ID + 5, ra1 + 1, r1, TDA);
        setBounds(ID + 5, 26, 92, 6, INT);
        addRuneText(ID + 6, ra2 + 1, r2, TDA);
        setBounds(ID + 6, 87, 92, 7, INT);
        addRuneText(ID + 7, ra3 + 1, r3, TDA);
        setBounds(ID + 7, 142, 92, 8, INT);
    }

    // done
    public static void addLunar3RunesSmallBox(int ID, int r1, int r2, int r3, int ra1, int ra2, int ra3, int rune1,
                                              int rune2, int lvl, String name, String descr, TextDrawingArea[] TDA, int sid, int suo, int type) {
        RSInterface rsInterface = addInterface(ID);
        rsInterface.id = ID;
        rsInterface.parentID = 1151;
        rsInterface.type = 5;
        rsInterface.atActionType = type;
        rsInterface.contentType = 0;
        rsInterface.hoverType = ID + 1;
        rsInterface.spellUsableOn = suo;
        rsInterface.selectedActionName = "Cast on";
        rsInterface.width = 20;
        rsInterface.height = 20;
        rsInterface.tooltip = "Cast @gre@" + name;
        rsInterface.spellName = name;
        rsInterface.valueCompareType = new int[4];
        rsInterface.requiredValues = new int[4];
        rsInterface.valueCompareType[0] = 3;
        rsInterface.requiredValues[0] = ra1;
        rsInterface.valueCompareType[1] = 3;
        rsInterface.requiredValues[1] = ra2;
        rsInterface.valueCompareType[2] = 3;
        rsInterface.requiredValues[2] = ra3;
        rsInterface.valueCompareType[3] = 3;
        rsInterface.requiredValues[3] = lvl;
        rsInterface.valueIndexArray = new int[4][];
        rsInterface.valueIndexArray[0] = new int[4];
        rsInterface.valueIndexArray[0][0] = 4;
        rsInterface.valueIndexArray[0][1] = 3214;
        rsInterface.valueIndexArray[0][2] = r1;
        rsInterface.valueIndexArray[0][3] = 0;
        rsInterface.valueIndexArray[1] = new int[4];
        rsInterface.valueIndexArray[1][0] = 4;
        rsInterface.valueIndexArray[1][1] = 3214;
        rsInterface.valueIndexArray[1][2] = r2;
        rsInterface.valueIndexArray[1][3] = 0;
        rsInterface.valueIndexArray[2] = new int[4];
        rsInterface.valueIndexArray[2][0] = 4;
        rsInterface.valueIndexArray[2][1] = 3214;
        rsInterface.valueIndexArray[2][2] = r3;
        rsInterface.valueIndexArray[2][3] = 0;
        rsInterface.valueIndexArray[3] = new int[3];
        rsInterface.valueIndexArray[3][0] = 1;
        rsInterface.valueIndexArray[3][1] = 6;
        rsInterface.valueIndexArray[3][2] = 0;
        rsInterface.disabledSprite = Client.cacheSprite[sid];
        rsInterface.enabledSprite = Client.cacheSprite[sid + 39];
        RSInterface INT = addInterface(ID + 1);
        INT.interfaceShown = true;
        INT.hoverType = -1;
        setChildren(9, INT);
        addLunarSprite(ID + 2, 205);
        setBounds(ID + 2, 0, 0, 0, INT);
        addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true, true, 52, TDA, 1);
        setBounds(ID + 3, 90, 4, 1, INT);
        addText(ID + 4, descr, 0xAF6A1A, true, true, 52, TDA, 0);
        setBounds(ID + 4, 90, 19, 2, INT);
        setBounds(30016, 14, 35, 3, INT);
        setBounds(rune1, 74, 35, 4, INT);
        setBounds(rune2, 130, 35, 5, INT);
        addRuneText(ID + 5, ra1 + 1, r1, TDA);
        setBounds(ID + 5, 26, 66, 6, INT);
        addRuneText(ID + 6, ra2 + 1, r2, TDA);
        setBounds(ID + 6, 87, 66, 7, INT);
        addRuneText(ID + 7, ra3 + 1, r3, TDA);
        setBounds(ID + 7, 142, 66, 8, INT);
    }

    // done
    private static void addLunarSprite(int i, int j) {
        RSInterface RSInterface = addInterface(i);
        RSInterface.id = i;
        RSInterface.parentID = i;
        RSInterface.type = 5;
        RSInterface.atActionType = 0;
        RSInterface.contentType = 0;
        RSInterface.opacity = 0;
        RSInterface.hoverType = 52;
        RSInterface.disabledSprite = Client.cacheSprite[j];
        RSInterface.width = 500;
        RSInterface.height = 500;
        RSInterface.tooltip = "";
    }

    // done
    private static void addRuneText(int ID, int runeAmount, int RuneID, TextDrawingArea[] font) {
        RSInterface rsInterface = addInterface(ID);
        rsInterface.id = ID;
        rsInterface.parentID = 1151;
        rsInterface.type = 4;
        rsInterface.atActionType = 0;
        rsInterface.contentType = 0;
        rsInterface.width = 0;
        rsInterface.height = 14;
        rsInterface.opacity = 0;
        rsInterface.hoverType = -1;
        rsInterface.valueCompareType = new int[1];
        rsInterface.requiredValues = new int[1];
        rsInterface.valueCompareType[0] = 3;
        rsInterface.requiredValues[0] = runeAmount;
        rsInterface.valueIndexArray = new int[1][4];
        rsInterface.valueIndexArray[0][0] = 4;
        rsInterface.valueIndexArray[0][1] = 3214;
        rsInterface.valueIndexArray[0][2] = RuneID;
        rsInterface.valueIndexArray[0][3] = 0;
        rsInterface.centerText = true;
        rsInterface.textDrawingAreas = font[0];
        rsInterface.textShadow = true;
        rsInterface.message = "%1/" + runeAmount + "";
        rsInterface.enabledMessage = "";
        rsInterface.disabledColor = 12582912;
        rsInterface.enabledColor = 49152;
    }

    public static void addSprite(int id, int spriteId) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = (byte) 0;
        tab.hoverType = 52;

        if (spriteId != -1) {
            tab.disabledSprite = Client.cacheSprite[spriteId];
            tab.enabledSprite = Client.cacheSprite[spriteId];
        }

        tab.width = 512;
        tab.height = 334;
    }

    public static void addTransparentSprite(int id, int spriteId, int spriteId2, int op) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 13;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.hoverType = 52;
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.enabledSprite = Client.cacheSprite[spriteId2];
        tab.width = 512;
        tab.height = 334;
        tab.opacity = (byte) op;
    }

    public static void addSprite(int ID, int i, int i2, int configId, int configFrame) {
        RSInterface Tab = addTabInterface(ID);
        Tab.id = ID;
        Tab.parentID = ID;
        Tab.type = 5;
        Tab.atActionType = 0;
        Tab.contentType = 0;
        Tab.width = 512;
        Tab.height = 334;
        Tab.opacity = (byte) 0;
        Tab.hoverType = -1;
        Tab.valueCompareType = new int[1];
        Tab.requiredValues = new int[1];
        Tab.valueCompareType[0] = 1;
        Tab.requiredValues[0] = configId;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        Tab.disabledSprite = Client.cacheSprite[i];
        Tab.enabledSprite = Client.cacheSprite[i2];
    }

    public static RSInterface addTabInterface(int id) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;// 250
        tab.parentID = id;// 236
        tab.type = 0;// 262
        tab.atActionType = 0;// 217
        tab.contentType = 0;
        tab.width = 512;// 220
        tab.height = 700;// 267
        tab.opacity = (byte) 0;
        tab.hoverType = -1;// Int 230
        return tab;
    }

    public static void addClickableText(int id, String text, String tooltip, TextDrawingArea tda[], int idx, int color,
                                        int width, int height) {
        RSInterface Tab = addTabInterface(id);
        Tab.parentID = id;
        Tab.id = id;
        Tab.type = 4;
        Tab.atActionType = 1;
        Tab.width = width;
        Tab.height = height;
        Tab.contentType = 0;
        Tab.hoverType = 0;
        // Tab.mOverInterToTrigger = -1;
        Tab.centerText = false;
        // Tab.enabledText = true;
        Tab.textDrawingAreas = tda[idx];
        Tab.message = text;
        Tab.tooltip = tooltip;
        // Tab.aString228 = "";
        Tab.disabledColor = color;
    }

    public static void addSkillButton(int id, String skillGuide) {
        RSInterface button = addTabInterface(id);
        button.type = 5;
        button.atActionType = 5;
        button.contentType = 0;
        button.width = 60;
        button.height = 28;
        // button.disabledSprite = getSprite("Interfaces/Skilltab/Button");
        button.tooltip = "@whi@View @or1@" + skillGuide + " @whi@Options";
    }

    public static void addClickableText(int id, String text, String tooltip, TextDrawingArea tda[], int idx, int color,
                                        boolean center, boolean shadow, int width) {
        RSInterface tab = addTabInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.type = 4;
        tab.atActionType = 1;
        tab.width = width;
        tab.height = 12;
        tab.contentType = 0;
        // tab.aByte254 = 0;
        // tab.mOverInterToTrigger = -1;
        tab.centerText = center;
        tab.textShadow = shadow;
        tab.textDrawingAreas = tda[idx];
        tab.message = text;
        tab.enabledMessage = "";
        tab.disabledColor = color;
        tab.enabledColor = 0;
        tab.disabledMouseOverColor = 0xffffff;
        tab.enabledMouseOverColor = 0;
        tab.tooltip = tooltip;
    }

    public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean center,
                               boolean shadow, int hoverColour, String tooltip, int widthHover) {
        RSInterface tab = addTabInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.type = 4;
        tab.atActionType = 1;
        tab.width = widthHover;
        tab.height = 15;
        tab.actionType = 0;
        tab.contentType = 0;
        tab.opacity = 0;
        tab.isMouseoverTriggered = -1;
        tab.centerText = center;
        tab.textShadow = shadow;
        tab.textDrawingAreas = tda[idx];
        tab.textSize = idx;
        tab.message = text;
        tab.enabledMessage = "";
        tab.disabledColor = color;
        tab.colorEnabled = 0;
        tab.hoverColorDisabled = hoverColour;
        tab.hoverColorEnabled = 0;
        tab.enabledColor = 0;
        tab.disabledMouseOverColor = 0xffffff;
        tab.enabledMouseOverColor = 0;
        tab.tooltip = tooltip;
    }

    public static void addText(int i, String s, int k, boolean l, boolean m, int a, TextDrawingArea[] TDA, int j) {
        RSInterface RSInterface = addInterface(i);
        RSInterface.parentID = i;
        RSInterface.id = i;
        RSInterface.type = 4;
        RSInterface.atActionType = 0;
        RSInterface.width = 0;
        RSInterface.height = 0;
        RSInterface.contentType = 0;
        RSInterface.opacity = 0;
        RSInterface.hoverType = a;
        RSInterface.centerText = l;
        RSInterface.textShadow = m;
        RSInterface.textDrawingAreas = TDA[j];
        RSInterface.message = s;
        RSInterface.enabledMessage = "";
        RSInterface.disabledColor = k;
    }

    public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean centered) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();

        if (centered) {
            rsi.centerText = true;
        }

        rsi.textShadow = true;
        rsi.textDrawingAreas = tda[idx];
        rsi.message = text;
        rsi.disabledColor = color;
        rsi.id = id;
        rsi.type = 4;
    }

    public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean center,
                               boolean shadow) {
        RSInterface tab = addTabInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.type = 4;
        tab.atActionType = 0;
        tab.width = 0;
        tab.height = 11;
        tab.contentType = 0;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.centerText = center;
        tab.textShadow = shadow;
        tab.textDrawingAreas = tda[idx];
        tab.message = text;
        tab.enabledMessage = "";
        tab.disabledColor = color;
        tab.enabledColor = 0;
        tab.disabledMouseOverColor = 0;
        tab.enabledMouseOverColor = 0;
    }


    public static RSInterface addTextRight(int id, String text, TextDrawingArea tda[], int idx, int color, boolean shadow) {
        RSInterface tab = addTabInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.type = 4;
        tab.atActionType = 0;
        tab.width = 0;
        tab.height = 11;
        tab.contentType = 0;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.centerText = false;
        tab.rightText = true;
        tab.textShadow = shadow;
        tab.textDrawingAreas = tda[idx];
        tab.message = text;
        tab.enabledMessage = "";
        tab.disabledColor = color;
        tab.enabledColor = 0;
        tab.disabledMouseOverColor = 0;
        tab.enabledMouseOverColor = 0;
        return tab;
    }


    public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean center,
                               boolean shadow, int s, int t) {
        RSInterface tab = addTabInterface(id);
        tab.parentID = id;
        tab.id = id;
        tab.type = 4;
        tab.atActionType = 0;
        tab.width = 0;
        tab.height = 11;
        tab.contentType = 0;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.centerText = center;
        tab.textShadow = shadow;
        tab.textDrawingAreas = tda[idx];
        tab.message = text;
        tab.enabledMessage = "";
        tab.disabledColor = color;
        tab.enabledColor = 0;
        tab.disabledMouseOverColor = 0;
        tab.enabledMouseOverColor = 0;
    }

    public static void addText(int id, String text, TextDrawingArea wid[], int idx, int color) {
        RSInterface rsinterface = addTabInterface(id);
        rsinterface.id = id;
        rsinterface.parentID = id;
        rsinterface.type = 4;
        rsinterface.atActionType = 0;
        rsinterface.width = 174;
        rsinterface.height = 11;
        rsinterface.contentType = 0;
        rsinterface.hoverType = -1;
        rsinterface.centerText = false;
        rsinterface.textShadow = true;
        rsinterface.textDrawingAreas = wid[idx];
        rsinterface.message = text;
        rsinterface.disabledColor = color;
    }

    public static void addTransparentSpriteWSpriteLoader(int id, int spriteId, int opacity) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 3;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = (byte) opacity;
        tab.customOpacity = opacity;
        tab.hoverType = 52;
        tab.enabledSprite = Client.cacheSprite[spriteId];
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.width = tab.enabledSprite.myWidth;
        tab.height = tab.enabledSprite.myHeight - 2;
        // tab.drawsTransparent = true;
    }

    public static void addTransparentSpriteWSpriteLoader1(int id, int spriteId, int opacity) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 88;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = (byte) opacity;
        tab.customOpacity = opacity;
        tab.hoverType = 52;
        tab.enabledSprite = Client.cacheSprite[spriteId];
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.width = tab.enabledSprite.myWidth;
        tab.height = tab.enabledSprite.myHeight - 2;
        tab.drawsTransparent = true;
    }

    public boolean drawsTransparent = false;


    public int customOpacity = 0;

    public static void addConfigButtonWSpriteLoader(int ID, int pID, int bID, int bID2, int width, int height,
                                                    String tT, int configID, int aT, int configFrame) {
        RSInterface Tab = addTabInterface(ID);
        Tab.parentID = pID;
        Tab.id = ID;
        Tab.type = 5;
        Tab.atActionType = aT;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.hoverType = -1;
        Tab.valueCompareType = new int[1];
        Tab.requiredValues = new int[1];
        Tab.valueCompareType[0] = 1;
        Tab.requiredValues[0] = configID;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        Tab.disabledSprite = Client.cacheSprite[bID];
        Tab.enabledSprite = Client.cacheSprite[bID2];
        Tab.tooltip = tT;
    }

    public static void addButtonWSpriteLoader(int id, int spriteId, String tooltip) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.atActionType = 1;
        tab.contentType = 0;
        tab.opacity = (byte) 0;
        tab.hoverType = 52;
        tab.enabledSprite = Client.cacheSprite[spriteId];
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.width = tab.enabledSprite.myWidth;
        tab.height = tab.disabledSprite.myHeight - 2;
        tab.tooltip = tooltip;
    }

    public static void addButton1(int id, int spriteId, int spriteId2, String tooltip) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 553;
        tab.atActionType = 1;
        tab.contentType = 0;
        tab.opacity = (byte) 0;
        tab.hoverType = 52;
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.enabledSprite = Client.cacheSprite[spriteId2];
        tab.width = tab.enabledSprite.myWidth;
        tab.height = tab.disabledSprite.myHeight - 2;
        tab.tooltip = tooltip;
    }

    public static void addToggleButton(int id, int sprite, int setconfig, int width, int height, String s) {
        RSInterface rsi = addInterface(id);
        rsi.disabledSprite = Client.cacheSprite[sprite];
        rsi.enabledSprite = Client.cacheSprite[sprite + 1];
        rsi.requiredValues = new int[1];
        rsi.requiredValues[0] = 1;
        rsi.valueCompareType = new int[1];
        rsi.valueCompareType[0] = 1;
        rsi.valueIndexArray = new int[1][3];
        rsi.valueIndexArray[0][0] = 5;
        rsi.valueIndexArray[0][1] = setconfig;
        rsi.valueIndexArray[0][2] = 0;
        rsi.atActionType = 4;
        rsi.width = width;
        rsi.hoverType = -1;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
        rsi.tooltip = s;
    }

    public static void addTooltip(int id, String text) {
        RSInterface rsi = addInterface(id);
        rsi.id = id;
        rsi.type = 0;
        rsi.interfaceShown = true;
        rsi.hoverType = -1;
        addTooltipBox(id + 1, text);
        rsi.totalChildren(1);
        rsi.child(0, id + 1, 0, 0);
    }

    public static void addBankItem(int index, Boolean hasOption) {
        RSInterface rsi = interfaceCache[index] = new RSInterface();
        rsi.actions = new String[5];
        rsi.spritesX = new int[20];
        rsi.invStackSizes = new int[30];
        rsi.inv = new int[30];
        rsi.spritesY = new int[20];

        rsi.children = new int[0];
        rsi.childX = new int[0];
        rsi.childY = new int[0];

        // rsi.hasExamine = false;

        rsi.invSpritePadX = 24;
        rsi.invSpritePadY = 24;
        rsi.height = 5;
        rsi.width = 6;
        rsi.parentID = 5292;
        rsi.id = index;
        rsi.type = 2;
    }

    private static void addTooltipBox(int id, String text) {
        RSInterface rsi = addInterface(id);
        rsi.id = id;
        rsi.parentID = id;
        rsi.type = 9;
        rsi.tooltipBoxText = text;
    }

    public void constructLunar() {
        RSInterface Interface = addTabInterface(29999);
        setChildren(62, Interface);
        setBounds(30017, 11, 10, 0, Interface);
        setBounds(30025, 40, 10, 1, Interface);
        setBounds(30032, 71, 12, 2, Interface);
        setBounds(30040, 103, 10, 3, Interface);
        setBounds(30048, 135, 12, 4, Interface);
        setBounds(30056, 165, 10, 5, Interface);
        setBounds(30099, 8, 38, 6, Interface);
        setBounds(30122, 39, 39, 7, Interface);
        setBounds(30130, 71, 39, 8, Interface);
        setBounds(30154, 103, 39, 9, Interface);
        setBounds(30178, 135, 39, 10, Interface);
        setBounds(30186, 165, 37, 11, Interface);
        setBounds(30194, 12, 68, 12, Interface);
        setBounds(30202, 42, 68, 13, Interface);
        setBounds(30210, 71, 68, 14, Interface);
        setBounds(30218, 103, 68, 15, Interface);
        setBounds(30242, 135, 68, 16, Interface);
        setBounds(30282, 165, 68, 17, Interface);
        setBounds(30290, 11, 97, 18, Interface);//
        setBounds(30298, 41, 97, 19, Interface);//
        setBounds(30306, 70, 97, 20, Interface);
        setBounds(30322, 100, 97, 21, Interface);

        // setBounds(30322, 104, 184, 39, Interface);
        setBounds(30001, 6, 120, 22, Interface);// hover
        setBounds(30018, 5, 120, 23, Interface);// hover
        setBounds(30026, 5, 120, 24, Interface);// hover
        setBounds(30033, 5, 120, 25, Interface);// hover
        setBounds(30041, 5, 120, 26, Interface);// hover
        setBounds(30049, 5, 120, 27, Interface);// hover
        setBounds(30057, 5, 120, 28, Interface);// hover
        setBounds(30065, 5, 120, 29, Interface);// hover
        setBounds(30076, 5, 120, 30, Interface);// hover
        setBounds(30084, 5, 120, 31, Interface);// hover
        setBounds(30092, 5, 120, 32, Interface);// hover
        setBounds(30100, 5, 120, 33, Interface);// hover
        setBounds(30107, 5, 120, 34, Interface);// hover
        setBounds(30115, 5, 120, 35, Interface);// hover
        setBounds(30123, 5, 120, 36, Interface);// hover
        setBounds(30131, 5, 120, 37, Interface);// hover
        setBounds(30139, 5, 120, 38, Interface);// hover
        setBounds(30147, 5, 120, 39, Interface);// hover
        setBounds(30155, 5, 120, 40, Interface);// hover
        setBounds(30163, 5, 120, 41, Interface);// hover
        setBounds(30171, 5, 120, 42, Interface);// hover
        setBounds(30179, 5, 120, 43, Interface);// hover
        setBounds(30187, 5, 120, 44, Interface);// hover
        setBounds(30195, 5, 120, 45, Interface);// hover
        setBounds(30203, 5, 120, 46, Interface);// hover
        setBounds(30211, 5, 120, 47, Interface);// hover
        setBounds(30219, 5, 120, 48, Interface);// hover
        setBounds(30227, 5, 120, 49, Interface);// hover
        setBounds(30235, 5, 120, 50, Interface);// hover
        setBounds(30243, 5, 120, 51, Interface);// hover
        setBounds(30251, 5, 120, 52, Interface);// hover
        setBounds(30259, 5, 120, 53, Interface);// hover
        setBounds(30267, 5, 120, 54, Interface);// hover
        setBounds(30275, 5, 120, 55, Interface);// hover
        setBounds(30283, 5, 120, 56, Interface);// hover
        setBounds(30291, 5, 120, 57, Interface);// hover
        setBounds(30299, 5, 5, 58, Interface);// hover
        setBounds(30307, 5, 5, 59, Interface);// hover
        setBounds(30323, 5, 5, 60, Interface);// hover
        setBounds(30315, 5, 5, 61, Interface);// hover
    }

    public static void drawRune(int i, int id) {
        RSInterface RSInterface = addInterface(i);
        RSInterface.type = 5;
        RSInterface.atActionType = 0;
        RSInterface.contentType = 0;
        RSInterface.opacity = 0;
        RSInterface.hoverType = 52;
        RSInterface.disabledSprite = Client.cacheSprite[id];
        RSInterface.width = 500;
        RSInterface.height = 500;
    }

    private static Sprite method207(int i, Archive streamLoader, String s) {
        long l = (TextClass.method585(s) << 8) + i;
        Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
        if (sprite != null) {
            return sprite;
        }
        try {
            sprite = new Sprite(streamLoader, s, i);
            aMRUNodes_238.removeFromCache(sprite, l);
        } catch (Exception _ex) {
            return null;
        }
        return sprite;
    }

    public static void clearModelCache(boolean flag, Model model) {
        int i = 0;// was parameter
        int j = 5;// was parameter
        if (flag) {
            return;
        }
        aMRUNodes_264.unlinkAll();
        if (model != null && j != 4) {
            aMRUNodes_264.removeFromCache(model, (j << 16) + i);
        }
    }

    public static void removeSomething(int id) {
        interfaceCache[id] = new RSInterface();
    }

    public static void removeSomething(RSInterface child) {
        interfaceCache[child.id] = new RSInterface();
    }

    public static void setBounds(int id, int x, int y, int index, RSInterface RSinterface) {
        RSinterface.children[index] = id;
        RSinterface.childX[index] = x;
        RSinterface.childY[index] = y;
    }

    public static void setChildren(int total, RSInterface i) {
        i.children = new int[total];
        i.childX = new int[total];
        i.childY = new int[total];
    }

    public static void addSpriteLoaderButtonWithTooltipBox(int childId, int spriteId, String tooltip, int hoverSpriteId,
                                                           int tooltipBoxChildId, String tooltipBoxText, int tooltipx, int tooltipy) {
        RSInterface rsi = RSInterface.interfaceCache[childId] = new RSInterface();
        rsi.id = childId;
        rsi.parentID = childId;
        rsi.type = 5;
        rsi.atActionType = 1;
        rsi.contentType = 0;
        rsi.hoverType = tooltipBoxChildId;
        rsi.enabledSprite = Client.cacheSprite[spriteId];
        rsi.disabledSprite = Client.cacheSprite[spriteId];
        rsi.width = rsi.enabledSprite.myWidth;
        rsi.height = rsi.disabledSprite.myHeight - 2;
        rsi.tooltip = tooltip;
        // rsi.isFalseTooltip = true;
        addTooltip2(tooltipBoxChildId, tooltipBoxText, tooltipx, tooltipy);
    }

    public static void addTooltip2(int id, String text, int x, int y) {
        RSInterface rsinterface = addTabInterface(id);
        rsinterface.parentID = id;
        rsinterface.type = 0;
        rsinterface.interfaceShown = true;
        rsinterface.hoverType = -1;
        addTooltipBox2(id + 1, text);
        rsinterface.totalChildren(1);
        rsinterface.child(0, id + 1, x, y);
    }

    public static void addTooltipBox2(int id, String text) {
        RSInterface rsi = addInterface(id);
        rsi.id = id;
        rsi.parentID = id;
        rsi.type = 12;
        rsi.message = text;
    }


    public static void addForceSprite(int id, Sprite sprite) {
        RSInterface rsi = RSInterface.interfaceCache[id] = new RSInterface();
        rsi.enabledSprite = sprite;
        rsi.disabledSprite = sprite;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
    }

    public static void addSpriteLoader(int childId, int spriteId) {
        RSInterface rsi = RSInterface.interfaceCache[childId] = new RSInterface();
        rsi.id = childId;
        rsi.parentID = childId;
        rsi.type = 5;
        rsi.atActionType = 0;
        rsi.contentType = 0;
        rsi.enabledSprite = Client.cacheSprite[spriteId];
        rsi.disabledSprite = Client.cacheSprite[spriteId];

        // rsi.sprite1.spriteLoader = rsi.sprite2.spriteLoader = true;
        // rsi.hoverSprite1 = Client.cacheSprite[hoverSpriteId];
        // rsi.hoverSprite2 = Client.cacheSprite[hoverSpriteId];
        // rsi.hoverSprite1.spriteLoader = rsi.hoverSprite2.spriteLoader = true;
        // rsi.sprite1 = rsi.sprite2 = spriteId;
        // rsi.hoverSprite1Id = rsi.hoverSprite2Id = hoverSpriteId;
        rsi.width = rsi.enabledSprite.myWidth;
        rsi.height = rsi.disabledSprite.myHeight - 2;
        // rsi.isFalseTooltip = true;
    }

    public static void addCloseButton(int child, int hoverChild, int hoverImageChild) {
        addHoverButtonWSpriteLoader(child, 652, 21, 21, "Close", 250, hoverChild, 3);
        addHoveredImageWSpriteLoader(hoverChild, 653, 21, 21, hoverImageChild);
    }

    public static void addHoveredImageWSpriteLoader(int i, int spriteId, int w, int h, int imgInterface) {
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 0;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.scrollMax = 0;
        tab.interfaceShown = true;
        tab.width = w;
        tab.height = h;
        addHoverImageWSpriteLoader(imgInterface, spriteId);
        tab.totalChildren(1);
        tab.child(0, imgInterface, 0, 0);
    }

    public static void addHoverImageWSpriteLoader(int i, int spriteId) {
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.width = 512;
        tab.height = 334;
        tab.opacity = 0;
        tab.hoverType = 52;
        tab.enabledSprite = Client.cacheSprite[spriteId];
        tab.disabledSprite = Client.cacheSprite[spriteId];
    }

    public static void addHoverSpriteLoaderButton(int i, int spriteId, int width, int height, String text,
                                                  int contentType, int hoverOver, int aT) {// hoverable
        // button
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = aT;
        tab.contentType = contentType;
        tab.opacity = 0;
        tab.hoverType = hoverOver;
        tab.enabledSprite = Client.cacheSprite[spriteId];
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.width = width;
        tab.height = height;
        tab.tooltip = text;
    }

    public static void addHoveredSpriteLoaderButton(int i, int w, int h, int IMAGEID, int spriteId) {
        RSInterface tab = addTabInterface(i);
        tab.parentID = i;
        tab.id = i;
        tab.type = 0;
        tab.atActionType = 0;
        tab.width = w;
        tab.height = h;
        tab.interfaceShown = true;
        tab.opacity = 0;
        tab.hoverType = -1;
        tab.scrollMax = 0;
        tab.enabledSprite = Client.cacheSprite[spriteId];
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.totalChildren(1);
        tab.child(0, IMAGEID, 0, 0);
    }

    public static void Sidebar0a(int id, int id2, int id3, String text1, String text2, String text3, String text4,
                                 int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y, int img1x,
                                 int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y, TextDrawingArea[] tda) // 4button
    // spec
    {
        RSInterface rsi = addInterface(id); // 2423
        /* addText(ID, "Text", tda, Size, Colour, Centered); */
        addText(id2, "", tda, 3, 0xff981f, true); // 2426
        addText(id2 + 11, text1, tda, 0, 0xff981f, false);
        addText(id2 + 12, text2, tda, 0, 0xff981f, false);
        addText(id2 + 13, text3, tda, 0, 0xff981f, false);
        addText(id2 + 14, text4, tda, 0, 0xff981f, false);
        /* specialBar(ID); */
        rsi.specialBar(id3); // 7599

        rsi.width = 190;
        rsi.height = 261;

        int last = 15;
        int frame = 0;
        rsi.totalChildren(last, last, last);

        rsi.child(frame, id2 + 3, 21, 46);
        frame++; // 2429
        rsi.child(frame, id2 + 4, 104, 99);
        frame++; // 2430
        rsi.child(frame, id2 + 5, 21, 99);
        frame++; // 2431
        rsi.child(frame, id2 + 6, 105, 46);
        frame++; // 2432

        rsi.child(frame, id2 + 7, img1x, img1y);
        frame++; // bottomright 2433
        rsi.child(frame, id2 + 8, img2x, img2y);
        frame++; // topleft 2434
        rsi.child(frame, id2 + 9, img3x, img3y);
        frame++; // bottomleft 2435
        rsi.child(frame, id2 + 10, img4x, img4y);
        frame++; // topright 2436

        rsi.child(frame, id2 + 11, str1x, str1y);
        frame++; // chop 2437
        rsi.child(frame, id2 + 12, str2x, str2y);
        frame++; // slash 2438
        rsi.child(frame, id2 + 13, str3x, str3y);
        frame++; // lunge 2439
        rsi.child(frame, id2 + 14, str4x, str4y);
        frame++; // block 2440

        rsi.child(frame, 19300, 0, 0);
        frame++; // stuffs
        rsi.child(frame, id2, 94, 4);
        frame++; // weapon 2426
        rsi.child(frame, id3, 21, 205);
        frame++; // special attack 7599

        for (int i = id2 + 3; i < id2 + 7; i++) { // 2429 - 2433
            rsi = interfaceCache[i];
            rsi.disabledSprite = Client.cacheSprite[82];
            rsi.enabledSprite = Client.cacheSprite[83];
            rsi.width = 68;
            rsi.height = 44;
        }
    }

    public static void Sidebar0b(int id, int id2, String text1, String text2, String text3, String text4, int str1x,
                                 int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y, int img1x, int img1y,
                                 int img2x, int img2y, int img3x, int img3y, int img4x, int img4y, TextDrawingArea[] tda) // 4button
    // nospec
    {
        RSInterface rsi = addInterface(id); // 2423
        /* addText(ID, "Text", tda, Size, Colour, Centered); */
        addText(id2, "", tda, 3, 0xff981f, true); // 2426
        addText(id2 + 11, text1, tda, 0, 0xff981f, false);
        addText(id2 + 12, text2, tda, 0, 0xff981f, false);
        addText(id2 + 13, text3, tda, 0, 0xff981f, false);
        addText(id2 + 14, text4, tda, 0, 0xff981f, false);

        rsi.width = 190;
        rsi.height = 261;

        int last = 14;
        int frame = 0;
        rsi.totalChildren(last, last, last);

        rsi.child(frame, id2 + 3, 21, 46);
        frame++; // 2429
        rsi.child(frame, id2 + 4, 104, 99);
        frame++; // 2430
        rsi.child(frame, id2 + 5, 21, 99);
        frame++; // 2431
        rsi.child(frame, id2 + 6, 105, 46);
        frame++; // 2432

        rsi.child(frame, id2 + 7, img1x, img1y);
        frame++; // bottomright 2433
        rsi.child(frame, id2 + 8, img2x, img2y);
        frame++; // topleft 2434
        rsi.child(frame, id2 + 9, img3x, img3y);
        frame++; // bottomleft 2435
        rsi.child(frame, id2 + 10, img4x, img4y);
        frame++; // topright 2436

        rsi.child(frame, id2 + 11, str1x, str1y);
        frame++; // chop 2437
        rsi.child(frame, id2 + 12, str2x, str2y);
        frame++; // slash 2438
        rsi.child(frame, id2 + 13, str3x, str3y);
        frame++; // lunge 2439
        rsi.child(frame, id2 + 14, str4x, str4y);
        frame++; // block 2440

        rsi.child(frame, 19300, 0, 0);
        frame++; // stuffs
        rsi.child(frame, id2, 94, 4);
        frame++; // weapon 2426

        for (int i = id2 + 3; i < id2 + 7; i++) { // 2429 - 2433
            rsi = interfaceCache[i];
            rsi.disabledSprite = Client.cacheSprite[82];
            rsi.enabledSprite = Client.cacheSprite[83];
            rsi.width = 68;
            rsi.height = 44;
        }
    }

    public static void Sidebar0c(int id, int id2, int id3, String text1, String text2, String text3, int str1x,
                                 int str1y, int str2x, int str2y, int str3x, int str3y, int img1x, int img1y, int img2x, int img2y,
                                 int img3x, int img3y, TextDrawingArea[] tda) // 3button
    // spec
    {
        RSInterface rsi = addInterface(id); // 2423
        /* addText(ID, "Text", tda, Size, Colour, Centered); */
        addText(id2, "", tda, 3, 0xff981f, true); // 2426
        addText(id2 + 9, text1, tda, 0, 0xff981f, false);
        addText(id2 + 10, text2, tda, 0, 0xff981f, false);
        addText(id2 + 11, text3, tda, 0, 0xff981f, false);
        /* specialBar(ID); */
        rsi.specialBar(id3); // 7599

        rsi.width = 190;
        rsi.height = 261;

        int last = 12;
        int frame = 0;
        rsi.totalChildren(last, last, last);

        rsi.child(frame, id2 + 3, 21, 99);
        frame++;
        rsi.child(frame, id2 + 4, 105, 46);
        frame++;
        rsi.child(frame, id2 + 5, 21, 46);
        frame++;

        rsi.child(frame, id2 + 6, img1x, img1y);
        frame++; // topleft
        rsi.child(frame, id2 + 7, img2x, img2y);
        frame++; // bottomleft
        rsi.child(frame, id2 + 8, img3x, img3y);
        frame++; // topright

        rsi.child(frame, id2 + 9, str1x, str1y);
        frame++; // chop
        rsi.child(frame, id2 + 10, str2x, str2y);
        frame++; // slash
        rsi.child(frame, id2 + 11, str3x, str3y);
        frame++; // lunge

        rsi.child(frame, 19300, 0, 0);
        frame++; // stuffs
        rsi.child(frame, id2, 94, 4);
        frame++; // weapon
        rsi.child(frame, id3, 21, 205);
        frame++; // special attack 7599

        for (int i = id2 + 3; i < id2 + 6; i++) {
            rsi = interfaceCache[i];
            rsi.disabledSprite = Client.cacheSprite[82];
            rsi.enabledSprite = Client.cacheSprite[83];
            rsi.width = 68;
            rsi.height = 44;
        }
    }

    public static void itemGroup(int id, int w, int h, int x, int y, boolean drag, boolean examine) {
        RSInterface rsi = addInterface(id);
        rsi.width = w;
        rsi.height = h;
        rsi.inv = new int[w * h];
        rsi.invStackSizes = new int[w * h];
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.invSpritePadX = x;
        rsi.invSpritePadY = y;
        rsi.spritesX = new int[20];
        rsi.spritesY = new int[20];
        rsi.sprites = new Sprite[20];
        /*
         * rsi.aBoolean235 = drag; rsi.examine = examine;
         */
        rsi.type = 2;
    }

    public static void itemGroup(int id, int w, int h, int x, int y) {
        RSInterface rsi = addInterface(id);
        rsi.width = w;
        rsi.height = h;
        rsi.inv = new int[w * h];
        rsi.invStackSizes = new int[w * h];
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.invSpritePadX = x;
        rsi.invSpritePadY = y;
        rsi.spritesX = new int[20];
        rsi.spritesY = new int[20];
        rsi.sprites = new Sprite[20];
        rsi.type = 2;

        /*rsi.inv[0] = 6200;
        rsi.invStackSizes[0] = 2;*/
    }

    public static void addToItemGroup(int id, int w, int h, int x, int y, boolean actions, String action1,
                                      String action2, String action3) {
        RSInterface rsi = addInterface(id);
        rsi.width = w;
        rsi.height = h;
        rsi.inv = new int[w * h];
        rsi.invStackSizes = new int[w * h];
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.interfaceShown = false;
        rsi.invSpritePadX = x;
        rsi.invSpritePadY = y;
        rsi.spritesX = new int[20];
        rsi.spritesY = new int[20];
        rsi.sprites = new Sprite[20];
        rsi.actions = new String[5];
        if (actions) {
            rsi.actions[0] = action1;
            rsi.actions[1] = action2;
            rsi.actions[2] = action3;
        }
        rsi.type = 2;
    }

    public static void addInventoryItemGroup2(int id, int h, int w) {
        RSInterface Tab = interfaceCache[id] = new RSInterface();
        Tab.inv = new int[w * h];
        Tab.invStackSizes = new int[w * h];
        for (int i1 = 0; i1 < w * h; i1++) {
            Tab.invStackSizes[i1] = 0; // inv item stack size
            Tab.inv[i1] = 0; // inv item ids
        }
        Tab.spritesY = new int[28];
        Tab.spritesX = new int[28];
        for (int i2 = 0; i2 < 28; i2++) {
            Tab.spritesY[i2] = 8;
            Tab.spritesX[i2] = 16;
        }
        Tab.invSpritePadX = 7;
        Tab.invSpritePadY = 4;
        Tab.actions = new String[]{"Store 1", "Store 5", "Store 10", "Store All", "Store X"};
        Tab.width = w;
        Tab.hoverType = -1;
        Tab.parentID = id;
        Tab.id = id;
        Tab.scrollMax = 0;
        Tab.type = 2;
        Tab.height = h;
    }

    public static void addToItemGroup(int id, int w, int h, int x, int y, boolean hasActions, String[] actions) {
        RSInterface rsi = addInterface(id);
        rsi.width = w;
        rsi.height = h;
        rsi.inv = new int[w * h];
        rsi.invStackSizes = new int[w * h];
        rsi.usableItemInterface = false;
        rsi.isInventoryInterface = false;
        rsi.interfaceShown = false;
        rsi.invSpritePadX = x;
        rsi.invSpritePadY = y;
        rsi.spritesX = new int[20];
        rsi.spritesY = new int[20];
        rsi.sprites = new Sprite[20];
        if (hasActions)
            rsi.actions = actions;
        rsi.type = 2;
/*
        rsi.inv[0] = 6200;
        rsi.invStackSizes[0] = 2;*/

    }

    public static void opacityInterface() {
        RSInterface rsi = addTabInterface(35555);
        setChildren(1, rsi);
        addRectangle(35556, 128, 0x000000, true, 30, 34);
        setBounds(35556, 0, 0, 0, rsi);
    }

    public static void Sidebar0d(int id, int id2, String text1, String text2, String text3, int str1x, int str1y,
                                 int str2x, int str2y, int str3x, int str3y, int img1x, int img1y, int img2x, int img2y, int img3x,
                                 int img3y, TextDrawingArea[] tda) {
        RSInterface rsi = addInterface(id); // 2423
        /* addText(ID, "Text", tda, Size, Colour, Centered); */
        addText(id2, "", tda, 3, 0xff981f, true); // 2426
        addText(id2 + 9, text1, tda, 0, 0xff981f, false);
        addText(id2 + 10, text2, tda, 0, 0xff981f, false);
        addText(id2 + 11, text3, tda, 0, 0xff981f, false);

        // addText(353, "Spell", tda, 0, 0xff981f, false);
        removeSomething(353);
        addText(354, "Spell", tda, 0, 0xff981f, false);

        addCacheSprite(337, 19, 0, "combaticons");
        addCacheSprite(338, 13, 0, "combaticons2");
        addCacheSprite(339, 14, 0, "combaticons2");

        /* addToggleButton(id, sprite, config, width, height, tooltip); */
        // addToggleButton(349, 349, 108, 68, 44, "Select");
        removeSomething(349);
        addToggleButton(350, 68, 108, 68, 44, "Select");

        rsi.width = 190;
        rsi.height = 261;

        int last = 15;
        int frame = 0;
        rsi.totalChildren(last, last, last);

        rsi.child(frame, id2 + 3, 20, 115);
        frame++;
        rsi.child(frame, id2 + 4, 20, 80);
        frame++;
        rsi.child(frame, id2 + 5, 20, 45);
        frame++;

        rsi.child(frame, id2 + 6, img1x, img1y);
        frame++; // topleft
        rsi.child(frame, id2 + 7, img2x, img2y);
        frame++; // bottomleft
        rsi.child(frame, id2 + 8, img3x, img3y);
        frame++; // topright

        rsi.child(frame, id2 + 9, str1x, str1y);
        frame++; // bash
        rsi.child(frame, id2 + 10, str2x, str2y);
        frame++; // pound
        rsi.child(frame, id2 + 11, str3x, str3y);
        frame++; // focus

        rsi.child(frame, 349, 105, 46);
        frame++; // spell1
        rsi.child(frame, 350, 104, 106);
        frame++; // spell2

        rsi.child(frame, 353, 125, 74);
        frame++; // spell
        rsi.child(frame, 354, 125, 134);
        frame++; // spell

        rsi.child(frame, 19300, 0, 0);
        frame++; // stuffs
        rsi.child(frame, id2, 94, 4);
        frame++; // weapon
    }

    private static void sprite1(int id, int sprite) {
        RSInterface class9 = interfaceCache[id];
        class9.disabledSprite = Client.cacheSprite[sprite];
    }

    public static void textSize(int id, TextDrawingArea tda[], int idx) {
        RSInterface rsi = interfaceCache[id];
        rsi.textDrawingAreas = tda[idx];
    }

    public static RSFontSystem[] newFonts;

    static void unpack(Archive streamLoader, TextDrawingArea textDrawingAreas[], Archive streamLoader_1,
                       RSFontSystem[] newFontSystem) {
        aMRUNodes_238 = new List(50000);
        fonts = textDrawingAreas;
        newFonts = newFontSystem;
        ByteBuffer stream = new ByteBuffer(streamLoader.get("data"));
        int i = -1;
        stream.getUnsignedShort();
        // int j = stream.getUnsignedShort();
        interfaceCache = new RSInterface[160000];
        while (stream.position < stream.buffer.length) {
            int k = stream.getUnsignedShort();
            if (k == 65535) {
                i = stream.getUnsignedShort();
                k = stream.getUnsignedShort();
            }
            RSInterface rsInterface = interfaceCache[k] = new RSInterface();
            rsInterface.id = k;
            rsInterface.parentID = i;
            rsInterface.type = stream.getUnsignedByte();
            rsInterface.atActionType = stream.getUnsignedByte();
            rsInterface.contentType = stream.getUnsignedShort();
            rsInterface.width = stream.getUnsignedShort();
            rsInterface.height = stream.getUnsignedShort();
            rsInterface.opacity = (byte) stream.getUnsignedByte();
            rsInterface.hoverType = stream.getUnsignedByte();
            if (rsInterface.hoverType != 0) {
                rsInterface.hoverType = (rsInterface.hoverType - 1 << 8) + stream.getUnsignedByte();
            } else {
                rsInterface.hoverType = -1;
            }

            int i1 = stream.getUnsignedByte();

            if (i1 > 0) {
                rsInterface.valueCompareType = new int[i1];
                rsInterface.requiredValues = new int[i1];

                for (int j1 = 0; j1 < i1; j1++) {
                    rsInterface.valueCompareType[j1] = stream.getUnsignedByte();
                    rsInterface.requiredValues[j1] = stream.getUnsignedShort();
                }
            }

            int k1 = stream.getUnsignedByte();

            if (k1 > 0) {
                rsInterface.valueIndexArray = new int[k1][];
                for (int l1 = 0; l1 < k1; l1++) {
                    int i3 = stream.getUnsignedShort();
                    rsInterface.valueIndexArray[l1] = new int[i3];
                    for (int l4 = 0; l4 < i3; l4++) {
                        rsInterface.valueIndexArray[l1][l4] = stream.getUnsignedShort();
                    }
                }
            }

            if (rsInterface.type == 0) {
                rsInterface.scrollMax = stream.getUnsignedShort();
                rsInterface.interfaceShown = stream.getUnsignedByte() == 1;
                int i2 = stream.getUnsignedShort();
                rsInterface.children = new int[i2];
                rsInterface.childX = new int[i2];
                rsInterface.childY = new int[i2];

                for (int j3 = 0; j3 < i2; j3++) {
                    rsInterface.children[j3] = stream.getUnsignedShort();
                    rsInterface.childX[j3] = stream.getSignedShort();
                    rsInterface.childY[j3] = stream.getSignedShort();
                }
            }

            if (rsInterface.type == 1) {
                stream.getUnsignedShort();
                stream.getUnsignedByte();
            }

            if (rsInterface.type == 2) {
                rsInterface.inv = new int[rsInterface.width * rsInterface.height];
                rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
                rsInterface.allowSwapItems = stream.getUnsignedByte() == 1;
                rsInterface.isInventoryInterface = stream.getUnsignedByte() == 1;
                rsInterface.usableItemInterface = stream.getUnsignedByte() == 1;
                rsInterface.deletesTargetSlot = stream.getUnsignedByte() == 1;
                rsInterface.invSpritePadX = stream.getUnsignedByte();
                rsInterface.invSpritePadY = stream.getUnsignedByte();
                rsInterface.spritesX = new int[20];
                rsInterface.spritesY = new int[20];
                rsInterface.sprites = new Sprite[20];

                for (int j2 = 0; j2 < 20; j2++) {
                    int k3 = stream.getUnsignedByte();

                    if (k3 == 1) {
                        rsInterface.spritesX[j2] = stream.getSignedShort();
                        rsInterface.spritesY[j2] = stream.getSignedShort();
                        String s1 = stream.getString();

                        if (streamLoader_1 != null && s1.length() > 0) {
                            int i5 = s1.lastIndexOf(",");
                            rsInterface.sprites[j2] = method207(Integer.parseInt(s1.substring(i5 + 1)), streamLoader_1,
                                    s1.substring(0, i5));
                        }
                    }
                }

                rsInterface.actions = new String[5];

                for (int l3 = 0; l3 < 5; l3++) {
                    rsInterface.actions[l3] = stream.getString();
                    if (rsInterface.actions[l3].length() == 0) {
                        rsInterface.actions[l3] = null;
                    }
                    if (rsInterface.parentID == 3824) {
                        rsInterface.actions[4] = "Buy X";
                    }
                    if (rsInterface.parentID == 3822) {
                        rsInterface.actions[4] = "Sell X";
                        rsInterface.hideExamine = true;
                    }
                }

                if (rsInterface.parentID == 1644) {
                    rsInterface.actions[2] = "Operate";
                }
            }

            if (rsInterface.type == 3) {
                rsInterface.filled = stream.getUnsignedByte() == 1;
            }

            if (rsInterface.type == 4 || rsInterface.type == 1) {
                rsInterface.centerText = stream.getUnsignedByte() == 1;
                int k2 = stream.getUnsignedByte();
                if (textDrawingAreas != null) {
                    rsInterface.textDrawingAreas = textDrawingAreas[k2];
                }
                rsInterface.textShadow = stream.getUnsignedByte() == 1;
            }

            if (rsInterface.type == 4) {
                rsInterface.message = stream.getString().replaceAll("RuneScape", Configuration.CLIENT_NAME);
                rsInterface.enabledMessage = stream.getString();
            }

            if (rsInterface.type == 1 || rsInterface.type == 3 || rsInterface.type == 4) {
                rsInterface.disabledColor = stream.getIntLittleEndian();
            }

            if (rsInterface.type == 3 || rsInterface.type == 4) {
                rsInterface.enabledColor = stream.getIntLittleEndian();
                rsInterface.disabledMouseOverColor = stream.getIntLittleEndian();
                rsInterface.enabledMouseOverColor = stream.getIntLittleEndian();
            }

            if (rsInterface.type == 5) {
                String s = stream.getString();

                if (streamLoader_1 != null && s.length() > 0) {
                    int i4 = s.lastIndexOf(",");
                    rsInterface.disabledSprite = method207(Integer.parseInt(s.substring(i4 + 1)), streamLoader_1,
                            s.substring(0, i4));
                }

                s = stream.getString();

                if (streamLoader_1 != null && s.length() > 0) {
                    int j4 = s.lastIndexOf(",");
                    rsInterface.enabledSprite = method207(Integer.parseInt(s.substring(j4 + 1)), streamLoader_1,
                            s.substring(0, j4));
                }
            }

            if (rsInterface.type == 6) {
                int l = stream.getUnsignedByte();

                if (l != 0) {
                    rsInterface.mediaType = 1;
                    rsInterface.mediaID = (l - 1 << 8) + stream.getUnsignedByte();
                }

                l = stream.getUnsignedByte();

                if (l != 0) {
                    rsInterface.anInt255 = 1;
                    rsInterface.anInt256 = (l - 1 << 8) + stream.getUnsignedByte();
                }

                l = stream.getUnsignedByte();

                if (l != 0) {
                    rsInterface.disabledAnimationId = (l - 1 << 8) + stream.getUnsignedByte();
                } else {
                    rsInterface.disabledAnimationId = -1;
                }

                l = stream.getUnsignedByte();

                if (l != 0) {
                    rsInterface.enabledAnimationId = (l - 1 << 8) + stream.getUnsignedByte();
                } else {
                    rsInterface.enabledAnimationId = -1;
                }

                rsInterface.modelZoom = stream.getUnsignedShort();
                rsInterface.modelRotationY = stream.getUnsignedShort();
                rsInterface.modelRotationX = stream.getUnsignedShort();
            }

            if (rsInterface.type == 7) {
                rsInterface.inv = new int[rsInterface.width * rsInterface.height];
                rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
                rsInterface.centerText = stream.getUnsignedByte() == 1;
                int l2 = stream.getUnsignedByte();
                if (textDrawingAreas != null) {
                    rsInterface.textDrawingAreas = textDrawingAreas[l2];
                }
                rsInterface.textShadow = stream.getUnsignedByte() == 1;
                rsInterface.disabledColor = stream.getIntLittleEndian();
                rsInterface.invSpritePadX = stream.getSignedShort();
                rsInterface.invSpritePadY = stream.getSignedShort();
                rsInterface.isInventoryInterface = stream.getUnsignedByte() == 1;
                rsInterface.actions = new String[5];

                for (int k4 = 0; k4 < 5; k4++) {
                    rsInterface.actions[k4] = stream.getString();
                    if (rsInterface.actions[k4].length() == 0) {
                        rsInterface.actions[k4] = null;
                    }
                }
            }

            if (rsInterface.atActionType == 2 || rsInterface.type == 2) {
                rsInterface.selectedActionName = stream.getString();
                rsInterface.spellName = stream.getString();
                rsInterface.spellUsableOn = stream.getUnsignedShort();
            }

            if (rsInterface.type == 8) {
                rsInterface.message = stream.getString();
            }

            if (rsInterface.atActionType == 1 || rsInterface.atActionType == 4 || rsInterface.atActionType == 5
                    || rsInterface.atActionType == 6) {
                rsInterface.tooltip = stream.getString();

                if (rsInterface.tooltip.length() == 0) {
                    if (rsInterface.atActionType == 1) {
                        rsInterface.tooltip = "Ok";
                    }
                    if (rsInterface.atActionType == 4) {
                        rsInterface.tooltip = "Select";
                    }
                    if (rsInterface.atActionType == 5) {
                        rsInterface.tooltip = "Select";
                    }
                    if (rsInterface.atActionType == 6) {
                        rsInterface.tooltip = "Continue";
                    }
                }
            }
        }

        aClass44 = streamLoader;
        try {
            //teleportInterface(textDrawingAreas);
            //teleportInterface1(textDrawingAreas);

            taxbagShopInterface(textDrawingAreas);
            mysteryBoxViewer(textDrawingAreas);
            effectInterface(textDrawingAreas);
            effectInterface2(textDrawingAreas);
            effectInterface3(textDrawingAreas);
            startInterface(textDrawingAreas);
            ultimateIronmanBank(textDrawingAreas);
            //customCollectionLog(textDrawingAreas);
            customCombiner(textDrawingAreas);
            bestItems(textDrawingAreas);
            itemHoverBox(textDrawingAreas);
            killTracker(textDrawingAreas);
            scratchCardInterface(textDrawingAreas);
            perkOverlays(textDrawingAreas);

            customInterfaces = new CustomInterfaces(textDrawingAreas);
            customInterfaces.loadCustoms();
        } catch (Exception e) {
            e.printStackTrace();
        }
        aMRUNodes_238 = null;
    }

    public static final int BH_OFFSET = 17;

    public static void addSummoningText(int i, String s, int k, boolean l, boolean m, int a, TextDrawingArea[] TDA,
                                        int j) {

        RSInterface RSInterface = addTabInterface(i);
        RSInterface.parentID = i;
        RSInterface.id = i;
        RSInterface.type = 4;
        RSInterface.atActionType = 0;
        RSInterface.width = 0;
        RSInterface.height = 0;
        RSInterface.contentType = 0;
        RSInterface.hoverType = a;
        RSInterface.centerText = l;
        // RSInterface.dis = m;
        RSInterface.textDrawingAreas = TDA[j];
        RSInterface.message = s;
        RSInterface.message = s;
        RSInterface.disabledColor = k;
        RSInterface.interfaceShown = true;
        RSInterface.hoverType = -1;
    }

    public static void addInAreaHoverSpriteLoader(int i, int sprite, int w, int h, String text, int contentType,
                                                  int actionType) {
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = actionType;
        tab.contentType = contentType;
        tab.hoverType = i;
        tab.disabledSprite = Client.cacheSprite[sprite];
        tab.width = w;
        tab.height = h;
        tab.tooltip = text;
    }

    public static void addPouch(int ID, int r1[], int ra1, int r2, int lvl, String name, TextDrawingArea[] TDA,
                                int imageID, int type) {
        RSInterface rsInterface = addTabInterface(ID);
        rsInterface.id = ID;
        rsInterface.parentID = 1151;
        rsInterface.type = 5;
        rsInterface.atActionType = type;
        rsInterface.contentType = 0;
        rsInterface.hoverType = ID + 1;
        rsInterface.width = 32;
        rsInterface.height = 32;
        rsInterface.tooltip = (new StringBuilder()).append("Infuse @or1@").append(name).toString();
        rsInterface.spellName = name;
        rsInterface.valueCompareType = new int[2];
        rsInterface.requiredValues = new int[2];
        rsInterface.valueCompareType[0] = 3;
        rsInterface.requiredValues[0] = ra1;
        rsInterface.valueCompareType[1] = 3;
        rsInterface.requiredValues[1] = lvl - 1;
        rsInterface.summonReq = lvl - 1;
        rsInterface.valueIndexArray = new int[2 + r1.length][];
        for (int i = 0; i < r1.length; i++) {
            rsInterface.valueIndexArray[i] = new int[4];
            rsInterface.valueIndexArray[i][0] = 4;
            rsInterface.valueIndexArray[i][1] = 3214;
            rsInterface.valueIndexArray[i][2] = r1[i];
            rsInterface.valueIndexArray[i][3] = 0;
        }
        rsInterface.valueIndexArray[1] = new int[3];
        rsInterface.valueIndexArray[1][0] = 1;
        rsInterface.valueIndexArray[1][1] = 6;
        rsInterface.valueIndexArray[1][2] = 0;
        rsInterface.itemSpriteZoom = 150;
        rsInterface.itemSpriteId = r2;
        rsInterface.itemSpriteIndex = imageID;
        // rsInterface.greyScale = true;
        RSInterface hover = addTabInterface(ID + 1);
        hover.hoverType = -1;
        hover.interfaceShown = true;
        if (imageID < SummoningInterfaceData.summoningItemRequirements.length) {
            addSprite(ID + 6, SummoningInterfaceData.summoningItemRequirements[imageID][0], null, 150, 150);
            addSprite(ID - 1200, SummoningInterfaceData.summoningItemRequirements[imageID][1], null, 150, 150);
            addSprite(ID - 1201, SummoningInterfaceData.summoningItemRequirements[imageID][2], null, 150, 150);
            addRuneText(ID - 1202, SummoningInterfaceData.summoningItemAmountRequirements[imageID][0],
                    SummoningInterfaceData.summoningItemRequirements[imageID][0], TDA);
            addRuneText(ID - 1203, SummoningInterfaceData.summoningItemAmountRequirements[imageID][1],
                    SummoningInterfaceData.summoningItemRequirements[imageID][1], TDA);
            if (SummoningInterfaceData.summoningItemAmountRequirements[imageID][2] > 0)
                addRuneText(ID - 1204, SummoningInterfaceData.summoningItemAmountRequirements[imageID][2],
                        SummoningInterfaceData.summoningItemRequirements[imageID][2], TDA);
            setChildren(SummoningInterfaceData.summoningItemAmountRequirements[imageID][2] > 0 ? 9 : 8, hover);
            setBounds(ID + 6, 14, 33, 3, hover);
            setBounds(ID - 1200, 70, 33, 4, hover);
            setBounds(ID - 1201, 120, 33, 5, hover);
            setBounds(ID - 1202, 30, 65, 6, hover);
            setBounds(ID - 1203, 85, 65, 7, hover);
            if (SummoningInterfaceData.summoningItemAmountRequirements[imageID][2] > 0)
                setBounds(ID - 1204, 133, 65, 8, hover);
        } else
            setChildren(3, hover);
        addSpriteLoader(ID + 2, 1007);
        addText(ID + 3, (new StringBuilder()).append("Level ").append(lvl).append(": ").append(name).toString(),
                0xff981f, true, true, 52, 1);
        addText(ID + 4, "This item requires:", 0xaf6a1a, true, true, 52, 0);
        setBounds(ID + 2, 0, 0, 0, hover);
        setBounds(ID + 3, 90, 4, 1, hover);
        setBounds(ID + 4, 90, 19, 2, hover);
    }

    public static void addSprite1(int id, int spriteId, String spriteName) {
        addSprite(id, spriteId, spriteName, -1, -1);
    }

    public static void addSprite(int id, int spriteId, String spriteName, int zoom1, int zoom2) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = (byte) 0;
        tab.hoverType = 52;
        if (spriteName == null) {
            tab.itemSpriteZoom = zoom1;
            tab.itemSpriteId = spriteId;
        }
        tab.width = 512;
        tab.height = 334;
    }

    public static void addSprite(int a, int id, int spriteId, String spriteName, boolean l) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = (byte) 0;
        tab.hoverType = 52;
        if (spriteId > 0) {
            tab.disabledSprite = Client.cacheSprite[spriteId];
            tab.enabledSprite = Client.cacheSprite[spriteId];
        }
        tab.width = 512;
        tab.height = 334;
    }

    public static void addText(int i, String s, int k, boolean l, boolean m, int a, int j) {
        try {
            RSInterface rsinterface = addTabInterface(i);
            rsinterface.parentID = i;
            rsinterface.id = i;
            rsinterface.type = 4;
            rsinterface.atActionType = 0;
            rsinterface.width = 0;
            rsinterface.height = 0;
            rsinterface.contentType = 0;
            rsinterface.hoverType = a;
            rsinterface.centerText = l;
            rsinterface.textShadow = m;
            rsinterface.textDrawingAreas = RSInterface.fonts[j];
            rsinterface.message = s;
            rsinterface.disabledColor = k;
        } catch (Exception e) {
        }
    }

    public static void addHoverButton(int i, String imageName, int j, int width, int height, String text,
                                      int contentType, int hoverOver, int aT) {// hoverable
        // button
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = aT;
        tab.contentType = contentType;
        tab.opacity = 0;
        tab.hoverType = hoverOver;
        // tab.disabledSprite = imageLoader(j, imageName);
        // tab.enabledSprite = imageLoader(j, imageName);
        tab.width = width;
        tab.height = height;
        tab.tooltip = text;
    }

    public static void addHDSprite(int id, int spriteId, int sprite2) {
        RSInterface tab = interfaceCache[id] = new RSInterface();
        tab.id = id;
        tab.parentID = id;
        tab.type = 5;
        tab.advancedSprite = true;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.opacity = (byte) 0;
        tab.hoverType = 52;
        tab.disabledSprite = Client.cacheSprite[spriteId];
        tab.enabledSprite = Client.cacheSprite[sprite2];
        tab.width = 512;
        tab.height = 1024;
    }

    /**
     * Insert a new child into an already existing interface
     *
     * @param inter
     * @param child
     */
    public static void insertNewChild(RSInterface inter, int id, int x, int y) {
        int[] newChildren = new int[inter.children.length + 1];
        int[] newChildX = new int[inter.childX.length + 1];
        int[] newChildY = new int[inter.childY.length + 1];
        for (int i = 0; i < inter.children.length; i++) {
            newChildren[i] = inter.children[i];
        }
        for (int i = 0; i < inter.childX.length; i++) {
            newChildX[i] = inter.childX[i];
        }
        for (int i = 0; i < inter.childY.length; i++) {
            newChildY[i] = inter.childY[i];
        }

        inter.children = newChildren;
        inter.childX = newChildX;
        inter.childY = newChildY;
        inter.children[inter.children.length - 1] = id;
        inter.childX[inter.childX.length - 1] = x;
        inter.childY[inter.childY.length - 1] = y;
    }

    boolean filled;
    public String[] actions;
    boolean allowSwapItems;
    int anInt208;
    int disabledMouseOverColor;
    int enabledColor;
    public int mediaType;
    int enabledMouseOverColor;
    int anInt246;
    private int anInt255;
    private int anInt256;
    int disabledAnimationId;
    int enabledAnimationId;
    String enabledMessage;
    public int atActionType;
    public boolean centerText;
    public boolean rightText;
    public int[] children;
    public int[] childX;
    public int[] childY;
    public int contentType;
    boolean deletesTargetSlot;
    boolean drawSecondary;
    public int height;
    int hoverType;
    public int id;
    public boolean interfaceShown;
    public int[] inv;
    public int invSpritePadX;
    public int invSpritePadY;
    public int[] invStackSizes;
    public boolean isInventoryInterface;
    public boolean drawInfinity;
    public int mediaID;
    public String message;
    public int modelRotationY;
    public int modelRotationX;
    public int modelRotationZ;
    public int modelZoom;
    byte opacity;

    public int parentID;
    int[] requiredValues;
    public int scrollMax;
    public boolean sideScroll;
    public int scrollPosition;
    String secondaryText;
    String selectedActionName;
    String spellName;
    int spellUsableOn;
    public Sprite disabledSprite;
    public Sprite enabledSprite;
    public Sprite sprite3;
    Sprite[] sprites;
    int[] spritesX;
    int[] spritesY;
    public int disabledColor;
    TextDrawingArea textDrawingAreas;
    boolean textShadow;
    public String tooltip;
    String tooltipBoxText;
    public int type;
    boolean usableItemInterface;
    int[] valueCompareType;
    int[][] valueIndexArray;
    public int width;
    public int xOffset;
    public int yOffset;
    boolean hideStackSize, hideExamine;
    public boolean advancedSprite;
    public int summonReq;
    public boolean greyScale;
    public int itemSpriteId;
    public int itemSpriteZoom;
    public int itemSpriteIndex;

    public RSInterface() {
        enabledSpriteId = disabledSpriteId = -1;
    }

    public int enabledSpriteId, disabledSpriteId;

    public int pauseTicks = 20;
    public boolean endReached = false;

    public boolean displayedOnInterface = true;

    public static void addInAreaHover(int i, int sId, int sId2, int w, int h, String text, int contentType,
                                      int actionType) {
        RSInterface tab = addTabInterface(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = actionType;
        tab.contentType = contentType;
        tab.opacity = 0;
        tab.hoverType = i;
        if (sId != -1) {
            tab.disabledSprite = Client.cacheSprite[sId];
        }
        tab.enabledSprite = Client.cacheSprite[sId2];
        tab.width = w;
        tab.height = h;
        tab.tooltip = text;
    }

    public void child(int id, int interID, int x, int y) {
        children[id] = interID;
        childX[id] = x;
        childY[id] = y;
    }

    private Model getMediaModel(int i, int j) {
        Model model = (Model) aMRUNodes_264.insertFromCache((i << 16) + j);
        if (model != null) {
            return model;
        }
        if (i == 1) {
            model = Model.fetchModel(j);
        }
        if (i == 2) {
            model = MobDefinition.get(j).method160();
        }
        if (i == 3) {
            model = Client.myPlayer.getPlayerModel();
        }
        if (i == 4) {
            model = ItemDefinition.get(j).getInterfaceModel(50);
        }
        if (i == 5) {
            model = null;
        }
        if (model != null) {
            aMRUNodes_264.removeFromCache(model, (i << 16) + j);
        }
        if (i == 10 || i == 11) {
            Player p = new Player();
            p.visible = true;
            p.equipment[0] = i == 10 ? (j + 256) : (j + 512);
            if (p.myGender == 0)
                p.equipment[1] = plrJaw + 256;
            p.myGender = gender;
            model = p.getPlayerModel();
        }
        return model;
    }

    Model method209(int j, int k, boolean flag) {
        Model model;
        if (flag) {
            model = getMediaModel(anInt255, anInt256);
        } else {
            //here
            model = getMediaModel(mediaType, mediaID);
        }

        if (model == null) {
            return null;
        }

        if (k == -1 && j == -1 && model.face_color == null) {
            return model;
        }
        Model model_1 = new Model(true, FrameReader.isNullFrame(k) & FrameReader.isNullFrame(j), false, model);

        if (Client.instance.cardPack.getScale() != 128)
            model_1.scaleT(Client.instance.cardPack.getScale(), Client.instance.cardPack.getScale(), Client.instance.cardPack.getScale());

        if (contentType != 3501) {
            if (k != -1 || j != -1) {
                model_1.createBones();
            }
            if (k != -1) {
                model_1.applyTransform(k);
            }
            if (j != -1) {
                model_1.applyTransform(j);
            }
        }
        model_1.light(64, 768, -50, -10, -50, true);
        return model_1;
    }

    private void specialBar(int id) // 7599
    {
        addActionButton(id - 12, 70, -1, 150, 26, "Use @gre@Special Attack");

        for (int i = id - 11; i < id; i++) {
            removeSomething(i);
        }

        RSInterface rsi = interfaceCache[id - 12];
        rsi.width = 150;
        rsi.height = 26;
        rsi = interfaceCache[id];
        rsi.width = 150;
        rsi.height = 26;
        rsi.child(0, id - 12, 0, 0);
        rsi.child(12, id + 1, 3, 7);
        rsi.child(23, id + 12, 16, 8);

        for (int i = 13; i < 23; i++) {
            rsi.childY[i] -= 1;
        }

        rsi = interfaceCache[id + 1];
        rsi.type = 5;
        rsi.disabledSprite = Client.cacheSprite[71];

        for (int i = id + 2; i < id + 12; i++) {
            rsi = interfaceCache[i];
            rsi.type = 5;
        }

        sprite1(id + 2, 72);
        sprite1(id + 3, 73);
        sprite1(id + 4, 74);
        sprite1(id + 5, 75);
        sprite1(id + 6, 76);
        sprite1(id + 7, 77);
        sprite1(id + 8, 78);
        sprite1(id + 9, 79);
        sprite1(id + 10, 80);
        sprite1(id + 11, 81);
    }

    void swapInventoryItems(int i, int j) {
        int k = inv[i];
        inv[i] = inv[j];
        inv[j] = k;
        k = invStackSizes[i];
        invStackSizes[i] = invStackSizes[j];
        invStackSizes[j] = k;
    }

    public void totalChildren(int t) {
        children = new int[t];
        childX = new int[t];
        childY = new int[t];
    }

    public void totalChildren(int id, int x, int y) {
        children = new int[id];
        childX = new int[x];
        childY = new int[y];
    }



    public static void teleportText(int id, String text, String[] tooltip, TextDrawingArea tda[], int idx, int color,
                                    boolean center, boolean textShadowed, int width, int height, int contentType) {
        RSInterface rsinterface = addInterface(id);
        rsinterface.id = id;
        rsinterface.parentID = id;
        rsinterface.type = 4;
        rsinterface.atActionType = 1;
        rsinterface.width = width;
        rsinterface.height = height;
        rsinterface.contentType = contentType;
        rsinterface.opacity = 0;
        rsinterface.hoverType = -1;
        rsinterface.centerText = center;
        rsinterface.textShadow = textShadowed;
        rsinterface.textDrawingAreas = tda[idx];
        rsinterface.message = text;
        rsinterface.enabledMessage = "";
        rsinterface.disabledColor = color;
        rsinterface.enabledColor = 0;
        rsinterface.disabledMouseOverColor = 0xffffff;
        rsinterface.enabledMouseOverColor = 0;
        //rsinterface.tooltip = tooltip;
        rsinterface.actions = tooltip;
    }

    public static void teleportText(int id, String text, String tooltip, TextDrawingArea tda[], int idx, int color,
                                    boolean center, boolean textShadowed, int width, int height) {
        RSInterface rsinterface = addInterface(id);
        rsinterface.id = id;
        rsinterface.parentID = id;
        rsinterface.type = 4;
        rsinterface.atActionType = 1;
        rsinterface.width = width;
        rsinterface.height = height;
        rsinterface.contentType = 0;
        rsinterface.opacity = 0;
        rsinterface.hoverType = -1;
        rsinterface.centerText = center;
        rsinterface.textShadow = textShadowed;
        rsinterface.textDrawingAreas = tda[idx];
        rsinterface.message = text;
        rsinterface.enabledMessage = "";
        rsinterface.disabledColor = color;
        rsinterface.enabledColor = 0;
        rsinterface.disabledMouseOverColor = 0xEECB38;
        rsinterface.enabledMouseOverColor = 0;
        rsinterface.tooltip = tooltip;
    }

    public static void hoverText(int id, String text, String tooltip, TextDrawingArea tda[], int idx, int color,
                                 boolean center, boolean textShadowed, int width, int height, boolean u) {
        RSInterface rsinterface = addInterface(id);
        rsinterface.id = id;
        rsinterface.parentID = id;
        rsinterface.type = 4;
        rsinterface.atActionType = 1;
        rsinterface.width = width;
        rsinterface.height = height;
        rsinterface.contentType = 0;
        rsinterface.opacity = 0;
        rsinterface.hoverType = -1;
        rsinterface.centerText = true;
        rsinterface.textShadow = textShadowed;
        rsinterface.textDrawingAreas = tda[idx];
        rsinterface.message = text;
        rsinterface.enabledMessage = "";
        rsinterface.disabledColor = color;
        rsinterface.enabledColor = 0;
        rsinterface.disabledMouseOverColor = 0xffffff;
        rsinterface.enabledMouseOverColor = 0;
        rsinterface.tooltip = tooltip;
    }

    public static void addCloseButton2(int child, int hoverChild, int hoverImageChild) {
        addHoverButtonWSpriteLoader(child, 1016, 21, 21, "Close", 250, hoverChild, 3);
        addHoveredImageWSpriteLoader(hoverChild, 1017, 21, 21, hoverImageChild); //12x12 for bank
    }

    private final static int GREEN_TEXT = 0x65280;
    private final static int BLUE_TEXT = 0x255;
    private static final int WHITE_TEXT = 0xffffff;
    private static final int GREY_TEXT = 0xB9B855;
    public static final int YELLOW_TEXT = 0xff9040;
    private static final int ORANGE_TEXT = 0xff981f;

    public static void achievementPopup2(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(36000);
        addSpriteLoader(36001, 1443); // Background
        addCloseButton2(36002, 36003, 36004);
        addText(36005, "Achievements/Daily Tasks", tda, 2, ORANGE_TEXT, true, true);
        addText(36550, "Item Rewards", tda, 2, ORANGE_TEXT, true, true);
        addText(36551, "Point Rewards", tda, 2, ORANGE_TEXT, true, true);
        int x = 15, y = 25;
        tab.totalChildren(20);
        tab.child(0, 36001, x, y);
        tab.child(1, 36002, 473, 29);
        tab.child(2, 36003, 473, 29);
        tab.child(3, 36005, 240 + x, 4 + y);

        int xx = 35;
        int yy = 20;
        int hover = 50;
        addText(46010, "Easy", tda, 1, ORANGE_TEXT, true, true);
        tab.child(4, 46010, xx + x - 6, yy + y + 14);

        addConfigButtonWSpriteLoader(46006, 36000, 1442, 1442, 29, 23, "Show Easy", 0, 5, 1210, 46006 + hover, 128);
        tab.child(5, 46006, xx + x - 23, yy + y + 7);
        xx += 37;

        addText(46011, "Med.", tda, 1, ORANGE_TEXT, true, true);
        tab.child(6, 46011, xx + x - 8, yy + y + 14);

        addConfigButtonWSpriteLoader(46007, 36000, 1442, 1442, 29, 23, "Show Medium", 1, 5, 1210, 46007 + hover, 128);
        tab.child(7, 46007, xx + x - 25, yy + y + 7);
        xx += 37;

        addText(46012, "Hard", tda, 1, ORANGE_TEXT, true, true);
        tab.child(8, 46012, xx + x - 10, yy + y + 14);

        addConfigButtonWSpriteLoader(46008, 36000, 1442, 1442, 29, 23, "Show Hard", 2, 5, 1210, 46008 + hover, 128);
        tab.child(9, 46008, xx + x - 27, yy + y + 7);
        xx += 37;

        addText(46013, "Daily", tda, 1, ORANGE_TEXT, true, true);
        tab.child(10, 46013, xx + x - 12, yy + y + 14);

        addConfigButtonWSpriteLoader(46009, 36000, 1442, 1442, 29, 23, "Show Dailys", 3, 5, 1210, 46009 + hover, 128);
        tab.child(11, 46009, xx + x - 29, yy + y + 7);

        addText(36501, "Title Of Achievement", tda, 2, ORANGE_TEXT, true, true);
        tab.child(12, 36023, 12 + x, 58 + y); //scrollbar
        tab.child(13, 36500, 170 + x, 48 + y); //info
        tab.child(14, 36510, 223 + x, 242 + y); //idk???
        tab.child(15, 36520, 170 + x, 184 + y); //items scroll bar?
        tab.child(16, 36550, 240 + x, 165 + y); //item rewards text
        tab.child(17, 36551, 395 + x, 165 + y); //point rewards text
        tab.child(18, 36511, 324 + x, 184 + y); // point rewards scroll bar i think
        tab.child(19, 36501, 318 + x, 30 + y); //title

        RSInterface info = addInterface(36500);
        addText(36502, "Progress: @gre@0% (0/0)", tda, 1, WHITE_TEXT, false, true);
        addText(36503, "Daily Time Left: UNAVAILABLE", tda, 0, WHITE_TEXT, false, true);
        addHoverButtonWSpriteLoader(36504, 1440, 37, 25, "Complete Achievement", -1, 36505, 5);
        addHoveredImageWSpriteLoader(36505, 1441, 37, 25, 36506);
        info.totalChildren(8);
        info.child(0, 36502, 7, 7);
        info.child(1, 36503, 7, 90);
        info.child(2, 36504, 257, 77);
        info.child(3, 36505, 257, 77); // claim reward button
        for (int i = 0; i < 4; i++) {
            addText(36507 + i, "Description #" + i, tda, 0, WHITE_TEXT, true, true);
            info.child(4 + i, 36507 + i, 150, 30 + (i * 13)); //achiev descript
        }

        RSInterface exp = addInterface(36511);
        exp.totalChildren(5);
        for (int i = 0; i < 5; i++) {
            addText(36512 + i, "Text", tda, 0, WHITE_TEXT, false, true);
            exp.child(i, 36512 + i, 5, (i * 13) + 3); //point rewards
        }
        exp.width = 142 - 16;
        exp.height = 85;
        exp.scrollMax = 200;

        RSInterface items = addInterface(36520);
        items.totalChildren(1);
        itemGroup1(36521, 3, 3, 4, 5, true, true);
        interfaceCache[36521].contentType = 206;
        items.child(0, 36521, 5, 5); //items?
        items.width = 146 - 16;
        items.height = 85;
        items.scrollMax = 200;

        for (int i = 0; i < 3; i++) {
            RSInterface scroll = addInterface(36023 + i);
            scroll.totalChildren(100);
            for (int j = 0; j < 100; j++) {
                addClickableText(36037 + j + (i * 100), "Achievement: " + j, "Select", tda, 0, YELLOW_TEXT, false, true,
                        200);
                scroll.child(j, 36037 + j + (i * 100), 2, 3 + (j * 13));//list text
            }
            scroll.width = 139 - 16;
            scroll.height = 211;
            scroll.scrollMax = 405;
        }
    }

    public static void addConfigButtonWSpriteLoader(int ID, int pID, int bID, int bID2, int width, int height,
                                                    String tT, int configID, int aT, int configFrame, int hoverType, int opacity) {
        RSInterface Tab = addTabInterface(ID);
        Tab.parentID = pID;
        Tab.id = ID;
        Tab.type = 5;
        Tab.atActionType = aT;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.hoverType = hoverType;
        Tab.valueCompareType = new int[1];
        Tab.requiredValues = new int[1];
        Tab.valueCompareType[0] = 1;
        Tab.requiredValues[0] = configID;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        Tab.advancedSprite = true;
        Tab.disabledSprite = Client.cacheSprite[bID];
        Tab.enabledSprite = Client.cacheSprite[bID2];
        Tab.tooltip = tT;
        Tab.opacity = (byte) opacity;
        Tab.customOpacity = opacity;
        Tab.drawsTransparent = true;
    }

    private static void itemHoverBox(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(94000);
//        addColorBox(94001, 145, 145, 0x534a40);
        addTransparentOverlay(94001, 145, 142, 0x352e26, 200);
        addText(94002, "Item name", tda, 0, 0xff9e09, true, true);
        addText(94003, "Attack", tda, 0, 0xff9e09, false, true);
        addText(94004, "Defence", tda, 0, 0xff9e09, false, true);
        addText(94005, "Stab:\\nSlash:\\nCrush:\\nMagic:\\nRange:", tda, 0, 0xff9040, false, true);
        addText(94006, "0\\n0\\n0\\n0\\n0", tda, 0, 0xffffff, true, true);
        addText(94007, "0\\n0\\n0\\n0\\n0", tda, 0, 0xffffff, true, true);
        addText(94008, "Other Bonuses", tda, 0, 0xff9e09, false, true);
        addText(94009, "Strength Bonus:\\nRanged Strength:\\nPrayer Bonus:\\nMagic Damage:", tda, 0, 0xff9040, false, true);
        addText(94010, "0\\n0", tda, 0, 0xffffff, true, true);
        addLine(94011, 0xB7B7B7, 32);
        addLine(94012, 0xB7B7B7, 41);
        addLine(94013, 0xffffff, 74);
        int x = 8, y = 8;
        tab.totalChildren(13);
        tab.child(0, 94001, x, y);
        tab.child(1, 94002, 72 + x, 2 + y);
        tab.child(2, 94003, 48 + x, 16 + y);
        tab.child(3, 94004, 93 + x, 16 + y);
        tab.child(4, 94005, 2 + x, 30 + y);
        tab.child(5, 94006, 64 + x, 30 + y);
        tab.child(6, 94007, 114 + x, 30 + y);
        tab.child(7, 94008, 2 + x, 85 + y);
        tab.child(8, 94009, 2 + x, 97 + y);
        tab.child(9, 94010, 110 + x, 97 + y);
        tab.child(10, 94011, 48 + x, 27 + y);
        tab.child(11, 94012, 93 + x, 27 + y);
        tab.child(12, 94013, 2 + x, 109 + y);

        tab = addInterface(94020);
//        addColorBox(94021, 145, 16, 0x534a40);
        addTransparentOverlay(94021, 145, 16, 0x352e26, 200);
        addText(94022, "Press CTRL to view stats", tda, 0, 0xB7B7B7, false, true);
        tab.totalChildren(3);
        tab.child(0, 94021, x, y);
        tab.child(1, 94002, 72 + x, 2 + y);
        tab.child(2, 94022, 2 + x, 14 + y);

        tab = addInterface(94085);
//        addColorBox(94086, 145, 100, 0x534a40);
        addTransparentOverlay(94086, 145, 80, 0x352e26, 200);
        addText(94087, "Should list info about the item", tda, 0, 0xffffff, false, true);
        x = 8;
        y = 8;
        tab.totalChildren(4);
        tab.child(0, 94086, x, y);
        tab.child(1, 94002, 72 + x, 2 + y);
        tab.child(2, 94087, 4 + x, 17 + y);
        tab.child(3, 94088, x, 55);

        RSInterface items = addInterface(94088);
        itemGroup1(94089, 40, 1, 10, 1, false, false);
        interfaceCache[94089].parentID = 94088;
        items.totalChildren(1);
        items.child(0, 94089, 0, 0);
        items.width = 145;
        items.height = 50;
    }

    private static void addTransparentOverlay(int id, int width, int height, int color, int opacity) {
        RSInterface rsi = RSInterface.interfaceCache[id] = new RSInterface();
        rsi.type = 831;
        rsi.width = width;
        rsi.height = height;
        rsi.enabledColor = color;
        rsi.customOpacity = opacity;
    }

    public static void addLine(int id, int color, int width) {
        RSInterface tab = addInterface(id);
        tab.width = width;
        tab.enabledColor = color;
        tab.type = 22;
        tab.contentType = 0;
    }

    public static void accountSetup() {
        int id = 47000;
        RSInterface tab = addTabInterface(id);
        id++;
        int frame = 0;
        addSpriteLoader(id, 1048);

        tab.totalChildren(55);

        tab.child(frame, id, 9, 25);
        frame++;
        id++;

        addText(id,
                "NO Restrictions\\n\\nNO Benefitss\\n\\n\\nRecommended Mode",
                fonts, 0, WHITE_TEXT, true, true);
        tab.child(frame, id, 93, 225);
        frame++;
        id++;

        addText(id,
                "Exp. Rate: x1000\\n\\nNO Challenge\\nNO Benefits\\n\\nRecommended Mode",
                fonts, 0, WHITE_TEXT, true, true);
        tab.child(frame, id, 255, 225);
        frame++;
        id++;

        addConfigButtonWSpriteLoader(id, 47000, 1049, 1050, 124, 34, "Select Normal Mode", 0, 5, 1085, id + 200);
        setBounds(id, 31, 99, frame, tab);
        frame++;
        addHoveredImageWSpriteLoader(id + 200, 1050, 124, 34, id + 201);
        setBounds(id + 200, 31, 99, frame, tab);
        frame++;
        id++;

        addConfigButtonWSpriteLoader(id, 47000, 1049, 1050, 124, 34, "Select Ironman Mode", 1, 5, 1085, id + 300);
        setBounds(id, 31, 137, frame, tab);
        frame++;
        addHoveredImageWSpriteLoader(id + 300, 1050, 124, 34, id + 301);
        setBounds(id + 300, 31, 137, frame, tab);
        frame++;
        id++;

        addText(id, "", fonts, 0, 0xFF981F, true, true);
        //  addConfigButtonWSpriteLoader(id, 47000, 1049, 1050, 124, 34, "Select Hardcore Mode", 2, 5, 1085, id + 400);
        setBounds(id, 31, 156, frame, tab);
        frame++;
        addText(id + 400, "", fonts, 0, 0xFF981F, true, true);
//        addHoveredImageWSpriteLoader(id + 400, 1050, 124, 34, id + 401);
        setBounds(id + 400, 31, 156, frame, tab);
        frame++;
        id++;

        addConfigButtonWSpriteLoader(id, 47001, 1049, 1050, 124, 34, "Select Easy Exp. Rate", 0, 5, 1086, id + 500);
        setBounds(id, 193, 80, frame, tab);
        frame++;
        addHoveredImageWSpriteLoader(id + 500, 1050, 124, 34, id + 501);
        setBounds(id + 500, 193, 80, frame, tab);
        frame++;
        id++;

        addConfigButtonWSpriteLoader(id, 47001, 1049, 1050, 124, 34, "Select Intermediate Exp. Rate", 1, 5, 1086, id + 600);
        setBounds(id, 193, 118, frame, tab);
        frame++;
        addHoveredImageWSpriteLoader(id + 600, 1050, 124, 34, id + 601);
        setBounds(id + 600, 193, 118, frame, tab);
        frame++;
        id++;

        addConfigButtonWSpriteLoader(id, 47001, 1049, 1050, 124, 34, "Select Hard Exp. Rate", 2, 5, 1086, id + 700);
        setBounds(id, 193, 156, frame, tab);
        frame++;
        addHoveredImageWSpriteLoader(id + 700, 1050, 124, 34, id + 701);
        setBounds(id + 700, 193, 156, frame, tab);
        frame++;
        id++;

        hoverButton(id, 1376, 1377, "Confirm selections", 2, 0xFF981F, "Confirm");
        setBounds(id, 359, 271, frame, tab);
        frame++;
        id++;

        addText(id, "Welcome to Lunite", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 254, 29);
        frame++;
        id++;


        addText(id, "Game Modes", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 92, 51);
        frame++;
        id++;

        addText(id, "Exp Rates", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 254, 51);
        frame++;
        id++;

        addText(id, "Starter Kit", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 416, 51);
        frame++;
        id++;


        addText(id, "Game Mode Info", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 92, 200);
        frame++;
        id++;

        addText(id, "Exp Rate Info", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 254, 200);
        frame++;
        id++;


        addText(id, "Normal", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 92, 108);
        frame++;
        id++;
        addText(id, "Ironman", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 92, 146);
        frame++;
        id++;
        addText(id, "", fonts, 0, 0xFF981F, true, true);
        tab.child(frame, id, 92, 165);
        frame++;
        id++;


        addText(id, "Easy", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 254, 89);
        frame++;
        id++;
        addText(id, "Intermediate", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 254, 127);
        frame++;
        id++;
        addText(id, "Hard", fonts, 2, 0xFF981F, true, true);
        tab.child(frame, id, 254, 165);
        frame++;
        id++;


        id += 100;

        /**
         * First row of items
         */
        int x = 353;
        for (int i = 59025; i < 59029; i++) {
            tab.child(frame, i, x, 87);
            frame++;
            x += 33;
        }

        int x2 = 353;
        for (int i = 59029; i < 59033; i++) {
            tab.child(frame, i, x2, 121);
            frame++;
            x2 += 33;
        }

        int x3 = 353;
        for (int i = 59033; i < 59037; i++) {
            tab.child(frame, i, x3, 155);
            frame++;
            x3 += 33;
        }

        int x4 = 353;
        for (int i = 59037; i < 59041; i++) {
            tab.child(frame, i, x4, 189);
            frame++;
            x4 += 33;
        }

        int x5 = 353;
        for (int i = 59041; i < 59045; i++) {
            tab.child(frame, i, x5, 223);
            frame++;
            x5 += 33;
        }

        int x6 = 353;
        for (int i = 59045; i < 59052; i++) {
            tab.child(frame, i, x6, 257);
            frame++;
            x6 += 33;
        }
    }

    public static void addConfigButtonWSpriteLoader(int ID, int pID, int bID, int bID2, int width, int height,
                                                    String tT, int configID, int aT, int configFrame, int hoverType) {
        RSInterface Tab = addTabInterface(ID);
        Tab.parentID = pID;
        Tab.id = ID;
        Tab.type = 5;
        Tab.atActionType = aT;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.hoverType = hoverType;
        Tab.valueCompareType = new int[1];
        Tab.requiredValues = new int[1];
        Tab.valueCompareType[0] = 1;
        Tab.requiredValues[0] = configID;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        Tab.advancedSprite = true;
        Tab.disabledSprite = Client.cacheSprite[bID];
        Tab.enabledSprite = Client.cacheSprite[bID2];
        Tab.tooltip = tT;
    }


    public static RSInterface copy(int id, int id1) {
        RSInterface tab = addTabInterface(id);
        RSInterface copy = RSInterface.interfaceCache[id1];
        tab.parentID = id;
        tab.id = id;
        tab.type = copy.type;
        tab.atActionType = copy.atActionType;
        tab.width = copy.width;
        tab.height = copy.height;
        tab.contentType = copy.contentType;
        tab.opacity = copy.opacity;
        tab.hoverType = copy.hoverType;
        tab.centerText = copy.centerText;
        tab.textDrawingAreas = copy.textDrawingAreas;
        tab.message = copy.message;
        tab.enabledMessage = copy.enabledMessage;
        tab.disabledColor = copy.disabledColor;
        tab.enabledColor = copy.enabledColor;
        tab.disabledMouseOverColor = copy.disabledMouseOverColor;
        tab.enabledMouseOverColor = copy.enabledMouseOverColor;

        tab.inv = copy.inv;
        tab.invStackSizes = copy.invStackSizes;
        tab.usableItemInterface = copy.usableItemInterface;
        tab.isInventoryInterface = copy.isInventoryInterface;
        tab.invSpritePadX = copy.invSpritePadX;
        tab.invSpritePadY = copy.invSpritePadY;
        tab.spritesX = copy.spritesX;
        tab.spritesY = copy.spritesY;
        tab.sprites = copy.sprites;
        tab.valueCompareType = copy.valueCompareType;
        tab.requiredValues = copy.requiredValues;
        tab.valueIndexArray = copy.valueIndexArray;
        tab.disabledSpriteId = copy.disabledSpriteId;
        tab.enabledSpriteId = copy.enabledSpriteId;
        tab.tooltip = copy.tooltip;

        tab.actions = copy.actions;
        tab.allowSwapItems = copy.allowSwapItems;
        return tab;
    }

    //WHEEL

    private boolean visible = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public WheelOfFortune wheel;
    public int layerColor = -1;
    public int layerTransparency = -1;
    public static final int DRAW_REGULAR_MODEL = 1, DRAW_NPC_MODEL = 2, DRAW_PLAYER_MODEL = 3, DRAW_ITEM_MODEL = 4;
    ;

    public boolean dragDeletes;


    public static void addColorBox(int interfaceID, int width, int height, int color) {
        RSInterface rsi = addTabInterface(interfaceID);
        rsi.type = 13;
        rsi.height = height;
        rsi.width = width;
        rsi.enabledColor = color;
    }


}