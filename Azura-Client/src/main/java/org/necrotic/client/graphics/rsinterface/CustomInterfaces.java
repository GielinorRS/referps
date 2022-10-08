package org.necrotic.client.graphics.rsinterface;

import org.necrotic.ColorConstants;
import org.necrotic.Configuration;
import org.necrotic.client.*;
import org.necrotic.client.accounts.Account;
import org.necrotic.client.cache.definition.ItemDefinition;
import org.necrotic.client.graphics.fonts.TextDrawingArea;

import java.util.ArrayList;


public class CustomInterfaces extends RSInterface {

    private static final int CLOSE_BUTTON = 580, CLOSE_BUTTON_HOVER = 581;

    public static TextDrawingArea tda[];

    public CustomInterfaces(TextDrawingArea tda[]) {
        this.tda = tda;
    }

    public void loadCustoms() {
        chambersInterface();
        leaderboardInterface();
        titlesInterface();
        questStart();
        questInfo();
        waveMinigameInterface();

        dailyLoginInterface();
        starterTasksInterface();
        raffleInterface();
        partyChest();
        assassinsInterface();
        assassinSacrifice();
        assassinCostumePick();
        godsHitpoints();
        godsRewards();
        godsCoffer();
        godsInterface();
        discordConnect();
        discordComponents();
        youtubeVideos();

        bossLog();
        settings();
        valentinesInterface();
        teleportInterface();
        cardPacks();
        cardPacksRewards();
        playerViewInterfaceMain();
        playerViewInterfaceBank();
        serverPerks();
        sanctumRewards();
        bossCollectionLog();
        afkCheckerInterface();
        afkInfoInterface();

        seasonalPass(tda);
        raids();
        panelInterface();
        panelInterfaceAccountInfo();
        panelInterfaceInterfaces();
        possibleLoot();
        casketInterface();
        //	Client.getClient().getAccountManager().updateInterface();
        BossEventInterfaceTracker(tda);
        combiner(tda);
        GoldenScratchCardNew(tda);
        BoxRewardList(tda);
        examineTab(tda);
        raidInterface(tda);
        bootyTrapDBZInterface(tda);
        bootyTrapDBZInterface2(tda);
        bootyTrapDBZInterface3(tda);
        raidRewardInterface(tda);
        playersOnline();
        starterTaskInterface(tda);
        editClan();
        zoneTasksInterface(tda);
        trainingTeleports(tda);
        dungeonInfo();
        formParty();
        dungParty();
        expRewardInterface();
        scratchCard(tda);
        donationDeals(tda);
        gamblingInterface(tda);
        achievementPopup2(tda);
        newStarter(tda);
        dailyRewards(tda);
        //slayerBuyInterface();
        barrowsInterface();
        pestControlInterfaces();
        skillTabInterface();
        playerPanel();
        killsTracker();
        friendsTabInterface();
        ignoreTabInterface();
        equipmentTab();
        teleportGUI();
        upgradeInterface(tda);
        equipmentScreenInterface();
        itemsKeptOnDeathInterface();
        clanChatTabInterface();
        configureLunar();
        redoSpellBooks();
        shopInterface();
        bankInterface();
        playerPanelv3();

        summoningBoBInterface();
        summoningTabInterface();
        priceCheckerInterface();
        goodiebagInterface(tda);
        achievementsInterface();
        loyaltyShop();
        //teleportWidget(tda);

        emoteTab();
        prayerTabInterface();
        curseTabInterface();
        //	pouchCreation();
        opacityInterface();
        staffTabInterface(tda);
        levelUpInterfaces();
        godWars();
        optionTab();
        //settingsInterface();
        bountyInterface();
        sidebarInterfaces();
        pouchCreation();
        settingsInterface2();
        mainGe();
        buyGe();
        collectBuyGe();
        sellGe();
        collectSellGe();
        quickCursesInterface();
        quickPrayersInterface();
        OsDropViewer();

        playerOwnedShopInterface(tda);
        playerOwnedShopInterface2(tda);
        playerOwnedShopInterface3(tda);
        SlayerDuo(tda);
        DailyTaskInterface(tda);
        instanceManager();
        //TeleportInterfaceNewest(tda);
        WellOfGoodWill(tda);
        DailyMboxes(tda);
        commands(tda);
        loyaltyStreakRewards(tda);
        pollingInterface(tda);
        createPoll(tda);
        allOrNothing(tda);
        grandLottery(tda);
        BattleBrawlTele(tda);
        TeleportInterfaceNewest(tda);
        addStreamBoss(tda);
        worldMap();
        progressionInterface();

        posInterface();
        posListingInterface();
        DissolvablesInterface.handle(tda);
        posHistoryInterface();


        groupIronman(tda);
        groupbankInterface();

        wheelOfFortune();
        wheelOfFortune1();
        showNpcModel();
        slayerFavourites();
        dailyTaskInterface();
    }

    public static void TeleportInterfaceNewest(TextDrawingArea[] tda) {
        int yOffset = -8;

        RSInterface tab = RSInterface.addInterface(28200);
        RSInterface scrollInterface = RSInterface.addTabInterface(28230);
        RSInterface.addSprite(28201, 1366);
        RSInterface.addHoverButton(28202, 1016, 16, 16, "Close", 250, 28203, 3);
        RSInterface.addHoveredButton(28203, 1017, 16, 16, 28204);
        RSInterface.addText(28205, "Teleports", tda, 2, 0xc1c1c1, true, true);

        String[] bossText = {"@cya@Teleport Name", "", "", ""};
        for (int i = 0; i < bossText.length; i++) {
            RSInterface.addText(28206 + i, bossText[i], tda,
                    (bossText[i].equalsIgnoreCase("@cya@Teleport Name")
                            || bossText[i].equalsIgnoreCase("@cya@Boss Information")) ? 0 : 0, 0xc1c1c1, true, true);
        }

        RSInterface.addButton(28210, 1373, 1374, "Teleport");
        RSInterface.addText(28211, "", tda, 2, ClientConstants.GOLD, true, true);
        RSInterface.addText(28212, "Teleport", tda, 2, ClientConstants.GOLD, true, true);
        RSInterface.addText(28213, "", tda, 2, ClientConstants.GOLD, true, true);
        RSInterface.drawNpcOnInterface(28214, 100, 1250);
        RSInterface.addText(28208, "@cya@Possible Drops", tda, 0, 0xc1c1c1, false, true);
        RSInterface.addText(28209, "@cya@NPC Display", tda, 0, 0xc1c1c1, false, true);


        String[] names = {"Monsters", "Bosses", "Dungeons", "Zones", "Minigames", "Other"};
        for (int i = 0; i < names.length; i++) {
            RSInterface.addButton(28215 + i, 1369, 1370, names[i]);
        }
        String[] text = {"Monsters", "Bosses", "Dungeons", "Zones", "Minigames", "Other", ""};
        for (int i = 0; i < text.length; i++) {
            RSInterface.addText(28221 + i, text[i], tda, 2, ClientConstants.GOLD, true, true);
        }
        /** This handles the scrollable Items On Interface **/
        RSInterface items = RSInterface.addInterface(28228);
        RSInterface.itemGroup(28229, 10, 1, 10, 1);
        RSInterface.interfaceCache[28229].parentID = 28228;
        items.totalChildren(1);
        items.child(0, 28229, 1, 1);
        items.width = 312;
        items.height = 100;

        RSInterface.setChildren(28, tab);
        tab.child(0, 28201, 7, 10);
        tab.child(1, 28202, 479, 13);
        tab.child(2, 28203, 479, 13);
        tab.child(3, 28205, 270, 15);
        tab.child(4, 28230, 27 - 7, 100 + yOffset);
        tab.child(5, 28206, 70, 76);
        tab.child(6, 28207, 110, 231);
        tab.child(7, 28208, 190, 90 - 13);
        tab.child(8, 28209, 190, 157 - 11);
        tab.child(9, 28210, 255, 287);
        tab.child(10, 28211, 341 - 150, 287);
        tab.child(11, 28212, 284 + 28, 294);
        tab.child(12, 28213, 383 - 170 + 10, 294);
        tab.child(13, 28214, 265, 178 - 6);
        tab.child(14, 28215, 18, 37);
        tab.child(15, 28216, 97, 37);
        tab.child(16, 28217, 97 + 79, 37);
        tab.child(17, 28218, 97 + 79 + 79, 37);
        tab.child(18, 28219, 97 + 79 + 79 + 79, 37);
        tab.child(19, 28220, 97 + 79 + 79 + 79 + 79, 37);
        tab.child(20, 28221, 57, 52 - 7);
        tab.child(21, 28222, 135, 52 - 7);
        tab.child(22, 28223, 215, 52 - 7);
        tab.child(23, 28224, 294, 52 - 7);
        tab.child(24, 28225, 372, 52 - 7);
        tab.child(25, 28226, 449, 52 - 7);
        tab.child(26, 28227, 112, 165);
        tab.child(27, 28228, 179, 102);

        //Scoll bar size, witdh and scrolling size.
        scrollInterface.width = 129 + 5;
        scrollInterface.height = 226;
        scrollInterface.scrollMax = 750;

        int npcList = 75;
        int y1 = 1;
        int y2 = 6;

        RSInterface.setChildren(npcList * 2, scrollInterface);

        for (int j = 0; j < npcList; j++) {
            RSInterface.addButton(28231 + j, 1367, 1368, "Select");
            scrollInterface.child(j, 28231 + j, 0, y1);
            y1 += 25;
        }

        for (int i = 0; i < npcList; i++) {
            RSInterface.addText(28307 + i, "Vorkath " + i, tda, i == 0 ? 1 : 1,
                    i == 0 ? ClientConstants.GOLD : ClientConstants.GOLD, false, false, ClientConstants.WHITE,
                    "Select", 150);
            scrollInterface.child(npcList + i, 28307 + i, 7, y2);
            y2 += 25;

        }
    }

    private static void worldMap() {
        RSInterface map = addInterface(57350);
        map.totalChildren(3);

        addWorldMap(57351);
        addSpriteLoader(57352, 1422);

        setBounds(57351, 0, -52, 0, map);
        setBounds(57352, 46, 0, 1, map);
        map.child(2, 14650, 442, 6);
    }

    public static void addWorldMap(int id) {
        RSInterface box = addInterface(id);
        box.type = 1319;
        box.width = 400;
        box.height = 400;
        box.atActionType = 1;
    }


    public static void BattleBrawlTele(TextDrawingArea[] TDA) {
        int interID = 28002;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 110;
        int y = 10;
        tab.totalChildren(5);

        addSpriteLoader(id, 1537);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 265 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 265 + x, 3 + y);
        id++;

        addText(id, "Npc Tier List", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 136 + x, 4 + y);

        tab.child(c++, 28010, 12 + x, 27 + y);

        interID = 28010;
        RSInterface list = addInterface(interID);
        list.width = 267 - 16;
        list.height = 275;
        list.scrollMax = 435 + 31;
        id = interID + 1;
        c = 0;
        x = 1;
        y = 1;
        list.totalChildren(64);

        String[] titles = new String[]{
                "Imp Teleport - @gre@Lvl 1",
                "Lord Teleport - @gre@Lvl 1",
                "Demon Teleport - @yel@Lvl 2",
                "Dragon Teleport - @yel@Lvl 3",
                "Beast Teleport - @or1@Lvl 3",
                "King Teleport - @or1@Lvl 4",
                "Avatar Teleport - @or2@Lvl 5",
                "Angel Teleport - @or2@Lvl 6",
                "Lucien Teleport - @red@Lvl 7",
                "Hercules Teleport - @red@Lvl 8",
                "Satan Teleport - @mag@Lvl 9",
                "Zeus Teleport - @mag@Lvl 10",
                "Groudon Teleport - @cya@Lvl 11",
                "Fenrir Teleport - @cya@Lvl 12",
                "Bork Teleport - @cya@Lvl 13",
                ""
        };

        String[] requirement = new String[]{
                "50 Npc killcount",
                "100 Imp kills",
                "200 Lord kills",
                "300 Demon kills",
                "400 Dragon kills",
                "500 Beast kills",
                "1000 King kills",
                "1200 Titan kills",
                "1500 Angel kills",
                "2500 Lucien kills",
                "3500 Herc kills",
                "5000 Satan kills",
                "15000 Zeus kills",
                "25k Groudon kills",
                "50k Fenrir kills",
                "",
        };

        for (int i = 0; i < 16; i++) {
            hoverButton(id, 1535, 1536, "Teleport");
            list.child(c++, id++, 0 + x, 0 + y);
            addText(id, titles[i], tda, 1, 0xcfa75a, false, true);
            list.child(c++, id++, 6 + x, 6 + y);
            addText(id, "Requirement:", tda, 0, ColorConstants.BLACK, true, true);
            list.child(c++, id++, 200 + x, 3 + y);
            addText(id, requirement[i], tda, 0, 0xFF981F, true, true);
            list.child(c++, id++, 200 + x, 15 + y);
            y += 31;
        }

    }

    public static void grandLottery(TextDrawingArea[] TDA) {
        RSInterface widget = addInterface(30900);
        addSprite(30901, 1317);

        addHoverButton(30902, 1318, 0, 151, 29, "Enter lottery", -1, 30903, 1);
        addHoveredButton(30903, 1319, 1, 151, 29, 30904);

        addButton1(30905, 1016, 1017, "close");


        addText(30906, "Lunite Grand Lottery", TDA, 2, 0xFF981F, true, true);

        addText(30907, "1st Prize", TDA, 1, 0xFF981F, true, true);
        addText(30908, "2nd Prize", TDA, 1, 0xFF981F, true, true);
        addText(30909, "3rd Prize", TDA, 1, 0xFF981F, true, true);

        addText(30910, "Static Prize", TDA, 0, 0xFF981F, true, true);

        addText(30911, "Top Entries", TDA, 2, 0xFF981F, true, true);


        addText(30912, "1) ", TDA, 1, 0xFF981F, false, true);
        addText(30913, "2) ", TDA, 1, 0xFF981F, false, true);
        addText(30914, "3) ", TDA, 1, 0xFF981F, false, true);

        addText(30915, "Your entries: 0", TDA, 0, 0xFF981F, true, true);

        addText(30916, "Special 1st Prize", TDA, 1, 0xFF981F, true, true);
        addText(30917, "Unlocks at 25 entries", TDA, 0, 0xFF981F, true, true);


        addText(30918, "Available entry balance: @yel@50.00", TDA, 0, 0xFF981F, false, true);
        addText(30919, "Single entry cost: @yel@5.00", TDA, 0, 0xFF981F, false, true);

        addText(30920, "Total Entries: @yel@15", TDA, 0, 0xFF981F, false, true);
        addText(30921, "Time left to draw: @yel@30 minutes", TDA, 0, 0xFF981F, false, true);

        addText(30922, "You have 10 entries left", TDA, 1, 0xFF981F, true, true);

        addText(30923, "Enter Lottery", TDA, 2, 0xFF981F, true, true);

        addToItemGroup(30924, 1, 1, 32, 32, false, null);//1

        addToItemGroup(30925, 1, 1, 32, 32, false, null);//2

        addToItemGroup(30926, 1, 1, 32, 32, false, null);//3

        addToItemGroup(30927, 1, 1, 32, 32, false, null);//static

        addToItemGroup(30928, 1, 1, 32, 32, false, null);//special


        addText(30929, "Brief system explanation:", TDA, 1, 0xFF981F, false, true);

        addText(30930, "When you donate the sum of the", TDA, 0, 14329120, false, true);
        addText(30931, "donation will be added towards your", TDA, 0, 14329120, false, true);
        addText(30932, "available entry balance which can", TDA, 0, 14329120, false, true);
        addText(30933, "be used to enter the lottery by the", TDA, 0, 14329120, false, true);
        addText(30934, "cost of a \"Single entry cost\".", TDA, 0, 14329120, false, true);
        addHoverButton(30935, 1307, 0, 72, 32, "Buy lottery ticket", -1, 30936, 1);
        addHoveredButton(30936, 1308, 1, 72, 32, 30937);

        addText(30938, "Buy ticket", TDA, 2, 0xFF981F, true, true);
        addText(30939, "(Costs 50m)", TDA, 2, 0x5acd4b, true, true);
        widget.totalChildren(37);

        widget.child(0, 30901, 7, 7);
        widget.child(1, 30902, 120, 267);
        widget.child(2, 30903, 120, 267);
        widget.child(3, 30905, 480, 16);

        widget.child(4, 30906, 243, 16);//title

        widget.child(5, 30907, 233, 56);//1
        widget.child(6, 30908, 151, 70);//2
        widget.child(7, 30909, 316, 86);//3

        widget.child(8, 30910, 55, 85);//static

        widget.child(9, 30911, 442, 43);//top entries
        widget.child(10, 30912, 388, 64);//1st entry
        widget.child(11, 30913, 388, 85);//2nd entry
        widget.child(12, 30914, 388, 106);//3rd entry
        widget.child(13, 30915, 438, 128);//3rd entry

        widget.child(14, 30916, 441, 149);//special 1st
        widget.child(15, 30917, 442, 200);//unlocks at

        widget.child(16, 30918, 17, 152);//unlocks at
        widget.child(17, 30919, 17, 166);//unlocks at

        widget.child(18, 30920, 17, 188);//unlocks at
        widget.child(19, 30921, 17, 202);//unlocks at

        widget.child(20, 30922, 194, 250);//unlocks at

        widget.child(21, 30923, 195, 274);//unlocks at

        widget.child(22, 30924, 216, 88);//1st prize
        widget.child(23, 30925, 216 - 82, 102);//2nd prize
        widget.child(24, 30926, 216 + 83, 103);//3rd prize
        widget.child(25, 30927, 39, 101);//static prize
        widget.child(26, 30928, 426, 164);//special prize

        widget.child(27, 30929, 199, 149);//special prize

        widget.child(28, 30930, 199, 164);//desc
        widget.child(29, 30931, 199, 174);//desc
        widget.child(30, 30932, 199, 184);//desc
        widget.child(31, 30933, 199, 194);//desc
        widget.child(32, 30934, 199, 204);//desc
        widget.child(33, 30935, 406, 260);//buy lottery
        widget.child(34, 30936, 406, 260);//buy lottery
        widget.child(35, 30938, 443, 270);//unlocks at
        widget.child(36, 30939, 443, 294);//unlocks at
    }


    public static void allOrNothing(TextDrawingArea[] TDA) {
        RSInterface widget = addInterface(22087);
        int childId = 22088;

        addSprite(childId++, 1306);

        addText(childId++, "Artex Double or Nothing Box", 0xff9933, true, true, -1, TDA, 2);

        addHoverButton(childId++, 1016, 1, 16, 16, "Close", -1, childId, 1);
        addHoveredButton(childId++, 1017, 2, 16, 16, childId++);

        addText(childId++, "Congratulations!", 0xff9933, true, true, -1, TDA, 2);
        addText(childId++, "You've won an Abyssal whip!", 0xff9933, true, true, -1, TDA, 1);

        addHoverButton(childId++, 1307, 1, 72, 32, "Gamble", -1, childId, 1);
        addHoveredButton(childId++, 1308, 2, 72, 32, childId++);

        addHoverButton(childId++, 1307, 1, 72, 32, "Keep", -1, childId, 1);
        addHoveredButton(childId++, 1308, 2, 72, 32, childId++);

        addText(childId++, "Gamble", 0xff9933, true, true, -1, TDA, 2);
        addText(childId++, "Keep", 0xff9933, true, true, -1, TDA, 2);

        addText(childId++, "Reward List", 0xff9933, true, true, -1, TDA, 2);
        addText(childId++, "Possible Rewards From Gambling", 0xff9933, true, true, -1, TDA, 2);

        addToItemGroup(childId++, 1, 1, 16, 7, false, new String[]{null, null, null, null, null});


        String rarities[] = {"@gre@Common", "@red@Uncommon", "@blu@Ultimate", "@yel@Legendary", "@pur@Dream"};
        for (int i = 0; i < rarities.length; i++) {
            addText(childId++, rarities[i], 0xff9933, false, true, -1, TDA, 0);
        }

        RSInterface scrollLeft = addInterface(childId++);
        RSInterface scrollRight = addInterface(childId++);

        scrollLeft.width = 141;
        scrollLeft.height = 233;
        scrollLeft.scrollMax = 2000;

        scrollRight.width = 213;
        scrollRight.height = 95;
        scrollRight.scrollMax = 200;

        int scrollChildLeft = childId;
        int scrollFrameLeft = 0;
        int startY = 2;
        scrollLeft.totalChildren(125);
        for (int i = 0; i < 125; i++) {
            addText(scrollChildLeft, "Abyssal Whip (Uncommon)", 0xffffff, false, true, -1, TDA, 0);
            scrollLeft.child(scrollFrameLeft++, scrollChildLeft++, 2, startY);
            startY += 13;
        }

        int scrollChildRight = scrollChildLeft;
        int scrolLFrameRight = 0;
        scrollRight.totalChildren(1);
        addToItemGroup(scrollChildRight, 6, 5, 4, 4, false, new String[]{null, null, null, null, null});

        scrollRight.child(scrolLFrameRight++, scrollChildRight++, 2, 2);

        int totalChildren = childId - 22088 - 3;
        widget.totalChildren(totalChildren);

        childId = 22088;
        int frame = 0;
        widget.child(frame++, childId++, 11, 11);//Background
        widget.child(frame++, childId++, 255, 21);//Title

        widget.child(frame++, childId++, 470, 20);//Close
        widget.child(frame++, childId++, 470, 20);
        childId++;

        widget.child(frame++, childId++, 340, 65);//Congrats
        widget.child(frame++, childId++, 340, 90);//Item won

        widget.child(frame++, childId++, 212, 127);//Gamble
        widget.child(frame++, childId++, 212, 127);
        childId++;

        widget.child(frame++, childId++, 392, 127);//Keep
        widget.child(frame++, childId++, 392, 127);
        childId++;

        widget.child(frame++, childId++, 247, 135);//Gamble Text
        widget.child(frame++, childId++, 428, 135);//Keep Text

        widget.child(frame++, childId++, 96, 53);//Reward List Text

        widget.child(frame++, childId++, 340, 187);//Possible rewards text

        widget.child(frame++, childId++, 321, 128);//Rewarded Item

        int startYRarity = 69;
        for (int i = 0; i < 5; i++) {//rarities
            widget.child(frame++, childId++, 187, startYRarity);
            startYRarity += 11;
        }

        widget.child(frame++, childId++, 25, 71);//Left Scroll Window
        widget.child(frame++, childId++, 223, 206);//Right Scroll Window
    }

    public static void createPoll(TextDrawingArea[] TDA) {
        RSInterface widget = addInterface(19627);

        int childId = 19628;

        addSprite(childId++, 1294);//, "Interfaces/Polling/BACKGROUND"

        addText(childId++, "Artex Poll Creator", 0xff9933, true, true, -1, TDA, 2);

        addHoverButton(childId++, 1016, 1, 16, 16, "Close", -1, childId, 1);
        addHoveredButton(childId++, 1017, 2, 16, 16, childId++);

        for (int i = 0; i < 4; i++) {
            addHoverButton(childId++, 1304, 2, 65, 24, "Edit", -1, childId, 1);
            addHoveredButton(childId++, 1305, 3, 65, 24, childId++);
            addText(childId++, "Edit", 0xff9933, true, true, -1, TDA, 2);
        }

        addHoverButton(childId++, 1302, 0, 72, 32, "Launch Poll", -1, childId, 1);
        addHoveredButton(childId++, 1303, 1, 72, 32, childId++);
        addText(childId++, "Launch", 0xff9933, true, true, -1, TDA, 2);
        addText(childId++, "Poll", 0xff9933, true, true, -1, TDA, 2);

        addHoverButton(childId++, 1302, 0, 72, 32, "Reset Poll", -1, childId, 1);
        addHoveredButton(childId++, 1303, 1, 72, 32, childId++);
        addText(childId++, "Reset", 0xff9933, true, true, -1, TDA, 2);
        addText(childId++, "Poll", 0xff9933, true, true, -1, TDA, 2);

        addText(childId++, "Current question: @whi@lorem ipsum lorem ipsum lorem ipsum lorem", 0xff9933, false, true, -1, TDA, 1);
        addText(childId++, "@whi@lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum", 0xff9933, false, true, -1, TDA, 1);

        addText(childId++, "Option 1: @whi@lorem ipsum lorem ipsum lorem ipsum lorem ipsum", 0xff9933, false, true, -1, TDA, 1);

        addText(childId++, "Option 2: @whi@lorem ipsum lorem ipsum lorem ipsum lorem ipsum", 0xff9933, false, true, -1, TDA, 1);

        addText(childId++, "Time for poll: @whi@lorem ipsum lorem ipsum lorem ipsum lorem ipsum", 0xff9933, false, true, -1, TDA, 1);

        widget.totalChildren(childId - 19628 - 7);

        childId = 19628;

        int frame = 0;
        widget.child(frame++, childId++, 11, 11);//BG

        widget.child(frame++, childId++, 255, 21);//Title

        widget.child(frame++, childId++, 470, 20);//Close
        widget.child(frame++, childId++, 470, 20);
        childId++;

        int startX = 402;
        int startY = 60;
        for (int i = 0; i < 4; i++) {
            widget.child(frame++, childId++, startX, startY);//Edit buttons
            widget.child(frame++, childId++, startX, startY);
            childId++;
            widget.child(frame++, childId++, startX + 30, startY + 4);
            startY += 50;
        }

        widget.child(frame++, childId++, 60, 250);//Launch button
        widget.child(frame++, childId++, 60, 250);
        childId++;
        widget.child(frame++, childId++, 92, 252);
        widget.child(frame++, childId++, 92, 265);

        widget.child(frame++, childId++, 160, 250);//Reset button
        widget.child(frame++, childId++, 160, 250);
        childId++;
        widget.child(frame++, childId++, 192, 252);
        widget.child(frame++, childId++, 192, 265);

        startX = 25;
        widget.child(frame++, childId++, startX, 60);//Text fields
        widget.child(frame++, childId++, startX, 75);
        widget.child(frame++, childId++, startX, 114);
        widget.child(frame++, childId++, startX, 164);
        widget.child(frame++, childId++, startX, 213);
    }

    public static void pollingInterface(TextDrawingArea[] TDA) {
        RSInterface widget = addInterface(22239);

        int childId = 22240;

        addSprite(childId++, 1294); //"Interfaces/Polling/BACKGROUND"

        addText(childId++, "Artex Update Polls", 0xff9933, true, true, -1, TDA, 2);

        addHoverButton(childId++, 1016, 1, 16, 16, "Close", -1, childId, 1);
        addHoveredButton(childId++, 1017, 2, 16, 16, childId++);

        RSInterface scroll = addInterface(childId++);
        scroll.width = 455;
        scroll.height = 261;
        scroll.scrollMax = 425;

        scroll.totalChildren(7 * 17);

        int scrollFrame = 0;
        int scrollChildId = childId;
        int scrollStartY = 0;
        for (int i = 0; i < 7; i++) {
            addSprite(scrollChildId, 1295); //"Interfaces/Polling/SCROLL"
            scroll.child(scrollFrame++, scrollChildId++, 0, scrollStartY);

            addText(scrollChildId, "Question", 0xff9933, false, true, -1, TDA, 0);
            scroll.child(scrollFrame++, scrollChildId++, 5, scrollStartY + 1);

            addText(scrollChildId, "Time Remaining: @whi@4 days 5 hours", 0xff9933, false, true, -1, TDA, 0);
            scroll.child(scrollFrame++, scrollChildId++, 5, scrollStartY + 45);

            addText(scrollChildId, "Should we add this polling system?", 0xff9933, false, true, -1, TDA, 1);
            scroll.child(scrollFrame++, scrollChildId++, 5, scrollStartY + 12);
            addText(scrollChildId, "How does one poll for a poll?!?!", 0xff9933, false, true, -1, TDA, 1);
            scroll.child(scrollFrame++, scrollChildId++, 5, scrollStartY + 25);

            new ProgressBar(scrollChildId, 150, 13, new int[]{0x00ff00}, true, false, "", new int[]{0xff0000});
            scroll.child(scrollFrame++, scrollChildId++, 230, scrollStartY + 42);

            addText(scrollChildId, "@whi@Total (0)", 0xff9933, true, true, -1, TDA, 0);
            scroll.child(scrollFrame++, scrollChildId++, 408, scrollStartY + 45);

            addText(scrollChildId, "Option 1: @gre@Yes, obviously.", 0xff9933, false, true, -1, TDA, 0);
            scroll.child(scrollFrame++, scrollChildId++, 230, scrollStartY + 14);
            addText(scrollChildId, "Option 2: @red@No, don't add it.", 0xff9933, false, true, -1, TDA, 0);
            scroll.child(scrollFrame++, scrollChildId++, 230, scrollStartY + 27);

            addHoverButton(scrollChildId++, 1296, 0, 14, 14, "Vote Option 1", -1, scrollChildId, 1);
            addHoveredButton(scrollChildId++, 1297, 3, 14, 14, scrollChildId++);
            scrollChildId -= 3;
            scroll.child(scrollFrame++, scrollChildId++, 385, scrollStartY + 12);
            scroll.child(scrollFrame++, scrollChildId++, 385, scrollStartY + 12);
            scrollChildId++;

            addHoverButton(scrollChildId++, 1298, 1, 14, 14, "Vote Option 2", -1, scrollChildId, 1);
            addHoveredButton(scrollChildId++, 1299, 2, 14, 14, scrollChildId++);
            scrollChildId -= 3;
            scroll.child(scrollFrame++, scrollChildId++, 385, scrollStartY + 26);
            scroll.child(scrollFrame++, scrollChildId++, 385, scrollStartY + 26);
            scrollChildId++;

            addHoverButton(scrollChildId++, 1300, 0, 6, 6, "Close Poll", -1, scrollChildId, 1);
            addHoveredButton(scrollChildId++, 1301, 1, 6, 6, scrollChildId++);
            scrollChildId -= 3;
            scroll.child(scrollFrame++, scrollChildId++, 445, scrollStartY + 2);
            scroll.child(scrollFrame++, scrollChildId++, 445, scrollStartY + 2);
            scrollChildId++;

            addText(scrollChildId, "@gre@50% (100)", 0xff9933, false, true, -1, TDA, 0);
            scroll.child(scrollFrame++, scrollChildId++, 401, scrollStartY + 14);
            addText(scrollChildId, "@red@50% (100)", 0xff9933, false, true, -1, TDA, 0);
            scroll.child(scrollFrame++, scrollChildId++, 401, scrollStartY + 27);

            scrollStartY += 61;
        }

        widget.totalChildren(childId - 22240 - 1);

        childId = 22240;
        int frame = 0;

        widget.child(frame++, childId++, 11, 11);//Background

        widget.child(frame++, childId++, 255, 21);//Title

        widget.child(frame++, childId++, 473, 20);//Close
        widget.child(frame++, childId++, 473, 20);
        childId++;

        widget.child(frame++, childId++, 18, 47);//Scroll frame
    }

    public static void loyaltyStreakRewards(TextDrawingArea[] TDA) {
        RSInterface widget = addInterface(22406);

        int childId = 22407;

        addSprite(childId++, 1291); //Interfaces/LoyaltyStreak/BACKGROUND

        addText(childId++, "Artex Loyalty Streak Rewards", 0xff9933, true, true, -1, TDA, 2);

        addHoverButton(childId++, 1016, 1, 16, 16, "Close", 250, childId, 3);
        addHoveredButton(childId++, 1017, 2, 16, 16, childId++);

        addText(childId++, "Thank you for being a loyal player of Artex!", 0xff9933, true, true, -1, TDA, 0);

        String text[] = {"Current Streak:", "@whi@#", "Longest Streak:", "@whi@#", "Total Claimed", "Rewards:", "@whi@#", "Total Playtime:",
                "@whi@#", "Time Until Next", "Reward:", "@whi@#", "Voted in last", "24 hours:", "@whi@#"};

        for (int i = 0; i < text.length; i++) {
            addText(childId++, text[i], 0xff9933, true, true, -1, TDA, 1);
        }

        addHoverButton(childId++, 1285, 0, 16, 16, "Refresh", -1, childId, 1);
        addHoveredButton(childId++, 1286, 1, 16, 16, childId++);

        RSInterface scroll = addInterface(childId++);
        int scrollChildId = childId;
        int scrollFrame = 0;
        int scrollX = 3;
        int scrollY = 3;
        scroll.width = 274;
        scroll.height = 231;
        scroll.scrollMax = 425;
        scroll.totalChildren(5 * 7);

        for (int i = 0; i < 7; i++) {
            addHoverButton(scrollChildId++, 1292, 0, 270, 53, "Claim", -1, scrollChildId, 1);
            addHoveredButton(scrollChildId++, 1293, 1, 270, 53, scrollChildId++);

            scrollChildId -= 3;

            scroll.child(scrollFrame++, scrollChildId++, scrollX, scrollY);
            scroll.child(scrollFrame++, scrollChildId++, scrollX, scrollY);
            scrollChildId++;

            addText(scrollChildId, "Day " + (i + 1), 0xff9933, false, true, -1, TDA, 2);
            scroll.child(scrollFrame++, scrollChildId++, scrollX + 10, scrollY + 6);

            addText(scrollChildId, "Reward text goes here blah blah blah" + (i + 1), 0xff9933, false, true, -1, TDA, 1);
            scroll.child(scrollFrame++, scrollChildId++, scrollX + 10, scrollY + 22);

            addToItemGroup(scrollChildId, 1, 1, 16, 7, true, new String[]{"Select", null, null, null, null});
            scroll.child(scrollFrame++, scrollChildId++, scrollX + 210, scrollY + 12);

            scrollY += 60;
        }

        widget.totalChildren(childId - 22407 - 2);

        childId = 22407;
        int frame = 0;

        widget.child(frame++, childId++, 11, 11);//Background

        widget.child(frame++, childId++, 255, 21);//Title

        widget.child(frame++, childId++, 473, 20);//Close
        widget.child(frame++, childId++, 473, 20);
        childId++;

        widget.child(frame++, childId++, 255, 48);//Title header

        int startX = 411;
        int startY = 71;

        for (int i = 0; i < text.length; i++) {
            widget.child(frame++, childId++, startX, startY);
            if (i == 4 || i == 9 || i == 12) {
                startY += 13;
            } else {
                startY += 15;
            }
        }

        widget.child(frame++, childId++, 455, 20);//Refresh
        widget.child(frame++, childId++, 455, 20);
        childId++;

        widget.child(frame++, childId++, 28, 69);
    }

    public static void commands(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(39800);
        RSInterface scrollTableft = RSInterface.addInterface(39840);
        RSInterface.addSprite(39801, 44);
        RSInterface.addText(39802, "Commands", 0xff9933, true, true, -1, tda, 0);

        //Scoll bar size, witdh and scrolling size.
        scrollTableft.width = 140;
        scrollTableft.height = 227;
        scrollTableft.scrollMax = 450;


        int npcList = 50;
        int y = 1;

        for (int i = 0; i < npcList; i++) {
            RSInterface.addText(39841 + i, "hey", tda, 1, ClientConstants.GOLD, false, false, ClientConstants.WHITE,
                    "Select", 150);
            //	addClickableText(39371 + i, "", "Select", tda, 2, 0xeb981f, false, true, 632);

        }

        int text = 50;
        int y1 = 1;

        RSInterface.setChildren(npcList, scrollTableft);

        for (int i = 0; i < text; i++) {
            scrollTableft.child(i, 39841 + i, 3, y1);
            y1 += 15;
        }


        RSInterface.setChildren(3, tab);
        tab.child(0, 39801, 10, 10);
        tab.child(1, 39802, 244, 15);
        tab.child(2, 39840, 471, 38);


    }

    public static void DailyMboxes(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(39670);
        RSInterface.addOutlinedColorBox(39671, 0x534a40, 135, 178, 50);
        RSInterface.itemGroup(39672, 1, 1, 1, 1);
        RSInterface.itemGroup(39673, 1, 1, 1, 1);
        RSInterface.itemGroup(39674, 1, 1, 1, 1);

        RSInterface.addText(39675, "Vote to claim\\nReward", 0xff9933, true, true, -1, tda, 0);
        RSInterface.addText(39676, "Vote to claim\\nReward", 0xff9933, true, true, -1, tda, 0);
        RSInterface.addText(39677, "Vote to claim\\nReward", 0xff9933, true, true, -1, tda, 0);

        RSInterface.setChildren(7, tab);
        tab.child(0, 39671, 10, 10);
        tab.child(1, 39672, 17, 110);
        tab.child(2, 39673, 17, 165);
        tab.child(3, 39674, 17, 215);

        tab.child(4, 39675, 33, 156 - 13);
        tab.child(5, 39676, 33, 208 - 13);
        tab.child(6, 39677, 33, 259 - 13);

    }

    public static void WellOfGoodWill(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(16550);
        RSInterface.addSprite(16551, 1287);
        RSInterface.addText(16552, "Well of Global Bosses", 0xff9933, true, true, -1, tda, 2);
        RSInterface.addHoverButton(16553, 1288, 104, 30, "Contribute", 0, 16554, 1);
        RSInterface.addHoveredButton(16554, 1289, 104, 30, 16555);
        RSInterface.addOutlinedColorBox(16556, 0x534a40, 160, 106, 100);
        RSInterface.addHoverButton(16558, 1288, 104, 30, "Options", 0, 16559, 1);
        RSInterface.addHoveredButton(16559, 1289, 104, 30, 16560);
        RSInterface.addText(16561, "Top Contributor:", tda, 1, 0xFF9900, true, true);
        RSInterface.addText(16562, "- ", tda, 0, 0xFFFFFF, true, true);
        RSInterface.addText(16563, "- ", tda, 0, 0xFFFFFF, true, true);
        RSInterface.addText(16564, "- ", tda, 0, 0xFFFFFF, true, true);
        RSInterface.addText(16565, "Last Contributor:", tda, 1, 0xFF9900, true, true);
        RSInterface.addText(16566, "- ", tda, 0, 0xFFFFFF, true, true);
        RSInterface.addText(16567, "%", tda, 1, 0xFFFFFF, true, true);
        RSInterface.addSprite(16568, 1290);
        RSInterface.addText(16569, "Contribute", tda, 1, 0xFF9900, true, true);
        RSInterface.addText(16570, "Options", tda, 1, 0xFF9900, true, true);
        RSInterface.addButton1(16571, 1016, 1017, "Close");

        RSInterface.setChildren(18, tab);
        tab.child(0, 16551, 120, 50);
        tab.child(1, 16552, 257, 62);
        tab.child(2, 16553, 163, 253);
        tab.child(3, 16554, 163, 253);
        tab.child(4, 16556, 181, 105);
        tab.child(5, 16558, 273, 253);
        tab.child(6, 16559, 273, 253);
        tab.child(7, 16561, 263, 108); // 135 - 155 = 20
        tab.child(8, 16562, 263, 128);
        tab.child(9, 16563, 263, 143);
        tab.child(10, 16564, 263, 158);
        tab.child(11, 16565, 263, 173);
        tab.child(12, 16566, 263, 193);
        tab.child(14, 16567, 371, 238 - 13);
        tab.child(13, 16568, 135, 218);
        tab.child(15, 16569, 207, 257);
        tab.child(16, 16570, 317, 257);
        tab.child(17, 16571, 378, 73 - 13);
    }

    private static void DailyTaskInterface(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(39330);
        RSInterface scrollTableft = RSInterface.addInterface(39370);
        RSInterface scrollTabright = RSInterface.addInterface(39360);
        RSInterface scrollTabmiddle = RSInterface.addInterface(39430);

        int xOffset = 4;
        int yOffset = -8;

        RSInterface.addSprite(39331, 1278);
        RSInterface.addText(39332, "Daily Money Making Activities", tda, 2, ClientConstants.GOLD, true, true);
        RSInterface.addText(39333, "", tda, 2, ClientConstants.YELLOW, true, true);

        RSInterface.addText(39334, "Activities", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(39335, "Activity Name", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(39336, "Task Information", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(39337, "Fill up the bar for rewards", tda, 2, 0xFF9900, true, true);


        RSInterface.addButton1(39338, 1279, 1280, "Easy");
        RSInterface.addButton1(39339, 1279, 1280, "Medium");
        RSInterface.addButton1(39340, 1279, 1280, "Hard");


        RSInterface.addText(39341, "Kill 250 Cows", tda, 2, ClientConstants.YELLOW, true, true);
        RSInterface.addText(39342, "", tda, 2, ClientConstants.YELLOW, true, true);

        RSInterface.addText(39343, "Easy", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(39344, "Medium", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(39345, "Hard", tda, 2, 0xFF9900, true, true);


        RSInterface.addButton1(39346, 1281, 1282, "Teleport");
        RSInterface.addButton1(39347, 1283, 1284, "Select Task");

        // RSInterface.addToItemGroup(39361, 0, 8, 8, 5, 5, false, null, null, null, true, true);
        RSInterface.itemGroup(39361, 1, 1, 1, 1);


        RSInterface.addText(39348, "Teleport", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(39349, "Task", tda, 2, 0xFF9900, true, true);


        RSInterface.addText(39350, "Information", tda, 2, 0xFF9900, true, true);

        //	RSInterface.modelViewer(39351, 1, 23889, 1500);
        RSInterface.drawNpcOnInterface(39351, 100, 1500);
        RSInterface.addText(39352, "Progress:", tda, 2, ClientConstants.YELLOW, true, true);

        RSInterface.addButton1(39353, 1285, 1286, "View Information");

        //39333
        RSInterface.addHoverButton(39354, 1016, 16, 16, "Close", 250, 39355, 3);
        RSInterface.addHoveredButton(39355, 1017, 16, 16, 39356);


        RSInterface.setChildren(28, tab);
        tab.child(0, 39331, 7, 10);
        tab.child(1, 39332, 270, 21);
        tab.child(2, 39333, 480, 22);

        tab.child(3, 39334, 96, 76);
        tab.child(4, 39335, 226, 76);
        tab.child(5, 39336, 249, 196);
        tab.child(6, 39337, 278, 257);

        tab.child(7, 39338, 14, 47);
        tab.child(8, 39339, 95, 47);
        tab.child(9, 39340, 176, 47);


        tab.child(10, 39341, 329, 215);
        tab.child(11, 39342, 365, 199);

        tab.child(12, 39343, 51, 52);
        tab.child(13, 39344, 134, 52);
        tab.child(14, 39345, 215, 52);

        tab.child(15, 39346, 405, 90);
        tab.child(16, 39347, 405, 135);

        tab.child(17, 39370, 4 + xOffset, 99 + yOffset);
        tab.child(18, 39360, 150, 273);

        tab.child(19, 39348, 462, 100);
        tab.child(20, 39349, 452, 145);
        tab.child(21, 39350, 343, 76);
        tab.child(22, 39430, 290, 91);
        tab.child(23, 39351, 150, 70);
        tab.child(24, 39352, 324, 235);
        tab.child(25, 39353, 463, 22);
        tab.child(26, 39354, 480, 22);
        tab.child(27, 39355, 480, 22);


        //Scoll bar size, witdh and scrolling size.
        scrollTableft.width = 140;
        scrollTableft.height = 227;
        scrollTableft.scrollMax = 450;

        //Scroll bar size, width and scrolling size.
        scrollTabright.width = 334;
        scrollTabright.height = 45;
        scrollTabright.scrollMax = 200;


        //Scroll bar size, width and scrolling size.
        scrollTabmiddle.width = 86;
        scrollTabmiddle.height = 100;
        scrollTabmiddle.scrollMax = 200;


        int npcList = 50;
        int y = 1;

        for (int i = 0; i < npcList; i++) {
            RSInterface.addText(39371 + i, "", tda, 1, ClientConstants.ORANGE, false, false, ClientConstants.WHITE,
                    "Select", 150);
            //	addClickableText(39371 + i, "", "Select", tda, 2, 0xeb981f, false, true, 632);

        }

        RSInterface.setChildren(npcList, scrollTableft);

        for (int i = 0; i < npcList; i++) {
            scrollTableft.child(i, 39371 + i, 30, y);
            y += 15;
        }


        RSInterface.setChildren(1, scrollTabright);

        scrollTabright.child(0, 39361, 25, 8);


        int text = 50;
        int y1 = 1;

        for (int i = 0; i < text; i++) {
            RSInterface.addText(39431 + i, "", tda, 1, ClientConstants.ORANGE, false, false, ClientConstants.WHITE,
                    "Select", 150);
            //addClickableText(39431 + i, "", "Select", tda, 2, 0xeb981f, false, true, 632);

        }

        RSInterface.setChildren(text, scrollTabmiddle);

        for (int i = 0; i < text; i++) {
            scrollTabmiddle.child(i, 39431 + i, 3, y1);
            y1 += 15;
        }

    }

    public static void SlayerDuo(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(17450);
        RSInterface.addSprite(17451, 1275);

        RSInterface.addHoverButton(17452, 1276, 88, 24, "New Partner", 0, 17453, 1);
        RSInterface.addHoveredButton(17453, 1277, 88, 24, 17454);

        RSInterface.addText(17455, "Current partner: ", 0xff9933, true, true, -1, tda, 1);
        RSInterface.addText(17456, "", 0xCC0000, true, true, -1, tda, 1);

        RSInterface.addText(17457, "Use the button to set yourself a Slayer Partner.", 0xff9933, true, true, -1, tda,
                1);

        RSInterface.addText(17458, "If your partner's Slayer level is as high as yours", 0xff9933, true, true, -1, tda,
                1);
        RSInterface.addText(17459, "whenever a task is assigned to them, you'll receive the", 0xff9933, true, true, -1,
                tda, 1);
        RSInterface.addText(17460, "same task, as long as you are eligible for it.", 0xff9933, true, true, -1, tda, 1);

        RSInterface.addText(17461, "If your Slayer level is as high as your partner's,", 0xff9933, true, true, -1, tda,
                1);
        RSInterface.addText(17462, "whenever a task is assigned to you, they'll receive the", 0xff9933, true, true, -1,
                tda, 1);
        RSInterface.addText(17463, "same task, as long as you are eligible for it.", 0xff9933, true, true, -1, tda, 1);

        RSInterface.addText(17464, "Slayer Partner", 0xff9933, true, true, -1, tda, 2);
        RSInterface.addText(17465, "Select Partner", 0xff9933, true, true, 1, tda, 2);

        RSInterface.setChildren(14, tab);
        tab.child(0, 17451, 95, 50);
        tab.child(1, 17452, 220, 245);
        tab.child(2, 17453, 220, 245);

        tab.child(3, 17455, 155, 105);
        tab.child(4, 17456, 220, 105);
        tab.child(5, 17457, 243, 125);

        tab.child(6, 17458, 243, 150);
        tab.child(7, 17459, 260, 163);
        tab.child(8, 17460, 227, 176);

        tab.child(9, 17461, 241, 200);
        tab.child(10, 17462, 259, 213);
        tab.child(11, 17463, 227, 226);

        tab.child(12, 17464, 270, 60);
        tab.child(13, 17465, 263, 250);
    }


    public static void playerOwnedShopInterface(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(32600);
        String dir = "ok/ok";

        addSpriteLoader(32601, 1494);

        addText(32610, "Player Owned Shop Interface", tda, 2, 0xeb981f, true, true);

        hoverButton(32611, 1502, 1502, "Back", 0, 0xff8624, "");

        int x = 37, y = 37;
        tab.totalChildren(6);
        tab.child(0, 32601, 0 + y, 0 + y);
        tab.child(1, 33606, 411 + x, 9 + y);
        tab.child(2, 33607, 411 + x, 9 + y);
        tab.child(3, 32620, 6 + x, 34 + y);
        tab.child(4, 32610, 218 + x, 10 + y);
        tab.child(5, 32611, 9 + x, 9 + y);

        RSInterface tab2 = addInterface(32620);

        tab2.width = 424 - 16;
        tab2.height = 220;
        tab2.scrollMax = 400;
        itemGroup(32621, 10, 10, 7, 7);
        tab2.totalChildren(1);
        tab2.child(0, 32621, 5, 5);

    }

    public static void playerOwnedShopInterface2(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(33600);
        RSInterface tab2 = addInterface(33620);
        String dir = "ok/ok";
        addSpriteLoader(33601, 1494);


        addHoveredImageWSpriteLoader(33606, 1251, 164, 22, 33607);
        addHoverSpriteLoaderButton(33607, 714, 16, 16, "Close", -1,
                32608, 1);

        addHoverButtonWSpriteLoader(33606, 1016, 16, 16, "Close Window", 0, 33607, 3);
        addHoveredImageWSpriteLoader(33607, 1017, 16, 16, 32608);

        hoverButton(33611, 1502, 1502, "Back", 0, 0xff8624, "");

        addText(33610, "My Store", tda, 2, 0xeb981f, true, true);
        int x = 37, y = 37;
        tab.totalChildren(6);
        tab.child(0, 33601, 0 + x, 0 + y);
        tab.child(1, 33606, 411 + x, 9 + y);
        tab.child(2, 33607, 411 + x, 9 + y);
        tab.child(3, 33620, 6 + x, 34 + y);
        tab.child(4, 33610, 218 + x, 10 + y);
        tab.child(5, 33611, 9 + x, 9 + y);
        tab2.width = 424 - 16;
        tab2.height = 220;
        tab2.scrollMax = 400;
        itemGroup(33621, 10, 8, 7, 7);
        tab2.totalChildren(1);
        tab2.child(0, 33621, 7, 6);
    }

    public static void playerOwnedShopInterface3(TextDrawingArea[] tda) {
		   /*RSInterface rsi = addInterface(37100);
		   addSprite(37101, 0, "Interfaces/ResourceBag/SPRITE");
		   addSprite(37102, 1, "Interfaces/ResourceBag/SPRITE");
		   for (int i = 0; i < 28; i++)
		       addToItemGroup(37103 + i, 1, 1, 0, 0, true, new String[] { "Withdraw 1", "Withdraw 5", "Withdraw 10", "Withdraw all", "Withdraw all to bank" });
	       setChildren(30, rsi);
	       setBounds(37101, 147, 38, 0, rsi);
	       setBounds(37102, 140, 31, 1, rsi);
	       int x = 0;
	       int y = 0;
	       int counter = 0;
	       for (int i = 0; i < 28; i++) {
	           setBounds(37103 + i, 162 + x, 46 + y, 2 + i, rsi);
	           x += 42;
	           counter++;
	           if (counter == 4) {
	               x = 0;
	               y += 34;
	               counter = 0;
	           }
	       }*/
        RSInterface tab = addTabInterface(37153);
        addInventoryItemGroup2(37154, 7, 4);
        setChildren(1, tab);
        setBounds(37154, 0, 0, 0, tab);
    }

    public static void trainingTeleports(TextDrawingArea[] paramArrayOfTextDrawingArea) {
        RSInterface localRSInterface = addInterface(60600);
        addSpriteLoader(60601, 1233);
        addHoverButtonWSpriteLoader(60602, 1234, 114, 35, "Select", 0, 60603, 1);
        addHoveredImageWSpriteLoader(60603, 1235, 114, 35, 60604);
        addHoverButtonWSpriteLoader(60605, 1234, 114, 35, "Select", 0, 60606, 1);
        addHoveredImageWSpriteLoader(60606, 1235, 114, 35, 60607);
        addHoverButtonWSpriteLoader(60608, 1234, 114, 35, "Select", 0, 60609, 1);
        addHoveredImageWSpriteLoader(60609, 1235, 114, 35, 60610);
        addHoverButtonWSpriteLoader(60611, 1234, 114, 35, "Select", 0, 60612, 1);
        addHoveredImageWSpriteLoader(60612, 1235, 114, 35, 60613);
        addHoverButtonWSpriteLoader(60614, 1234, 114, 35, "Select", 0, 60615, 1);
        addHoveredImageWSpriteLoader(60615, 1235, 114, 35, 60616);
        addHoverButtonWSpriteLoader(60617, 1234, 114, 35, "Select", 0, 60618, 1);
        addHoveredImageWSpriteLoader(60618, 1235, 114, 35, 60619);
        addHoverButtonWSpriteLoader(60622, 1236, 90, 32, "Select", 0, 60623, 1);
        addHoveredImageWSpriteLoader(60623, 1237, 90, 32, 60624);
        addHoverButtonWSpriteLoader(60625, 1236, 90, 32, "Select", 0, 60626, 1);
        addHoveredImageWSpriteLoader(60626, 1237, 90, 32, 60627);
        addHoverButtonWSpriteLoader(60628, 1236, 90, 32, "Select", 0, 60629, 1);
        addHoveredImageWSpriteLoader(60629, 1237, 90, 32, 60630);
        addHoverButtonWSpriteLoader(60631, 1236, 90, 32, "Select", 0, 60632, 1);
        addHoveredImageWSpriteLoader(60632, 1237, 90, 32, 60633);
        addHoverButtonWSpriteLoader(60634, 1236, 90, 32, "Select", 0, 60635, 1);
        addHoveredImageWSpriteLoader(60635, 1237, 90, 32, 60636);
        addHoverButtonWSpriteLoader(60637, 1236, 90, 32, "Select", 0, 60638, 1);
        addHoveredImageWSpriteLoader(60638, 1237, 90, 32, 60639);
        addHoverButtonWSpriteLoader(60640, 1236, 90, 32, "Select", 0, 60641, 1);
        addHoveredImageWSpriteLoader(60641, 1237, 90, 32, 60642);
        addHoverButtonWSpriteLoader(60643, 1236, 90, 32, "Select", 0, 60644, 1);
        addHoveredImageWSpriteLoader(60644, 1237, 90, 32, 60645);
        addHoverButtonWSpriteLoader(60646, 1236, 90, 32, "Select", 0, 60647, 1);
        addHoveredImageWSpriteLoader(60647, 1237, 90, 32, 60648);
        addHoverButtonWSpriteLoader(60691, 1236, 90, 32, "Select", 0, 60692, 1);
        addHoveredImageWSpriteLoader(60692, 1237, 90, 32, 60693);
        addHoverButtonWSpriteLoader(60694, 1236, 90, 32, "Select", 0, 60695, 1);
        addHoveredImageWSpriteLoader(60695, 1237, 90, 32, 60696);
        addHoverButtonWSpriteLoader(60697, 1236, 90, 32, "Select", 0, 60698, 1);
        addHoveredImageWSpriteLoader(60698, 1237, 90, 32, 60699);
        addHoverButtonWSpriteLoader(60652, 1238, 16, 16, "Select", 0, 60653, 1);
        addHoveredImageWSpriteLoader(60653, 1239, 16, 16, 60654);
        addText(60656, "Monsters", paramArrayOfTextDrawingArea, 1, 16252462, true, true);
        addText(60657, "Minigames", paramArrayOfTextDrawingArea, 1, 16252462, true, true);
        addText(60658, "Bosses", paramArrayOfTextDrawingArea, 1, 16252462, true, true);
        addText(60659, "Bosses 2", paramArrayOfTextDrawingArea, 1, 16252462, true, true);
        addText(60660, "Wilderness", paramArrayOfTextDrawingArea, 1, 16252462, true, true);
        addText(60661, "Dungeons", paramArrayOfTextDrawingArea, 1, 16252462, true, true);
        addText(60662, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60663, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60664, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60665, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60666, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60667, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60668, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60669, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60670, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60671, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60672, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60673, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60674, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60675, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60676, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60677, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60678, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60679, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(18374, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60701, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60702, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60703, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60704, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60705, "", paramArrayOfTextDrawingArea, 1, 16750623, true, true);
        addText(60690, "MisthalinPK Teleports", paramArrayOfTextDrawingArea, 2, 16750623, true, true);
        setChildren(70, localRSInterface);

        localRSInterface.child(0, 60601, 6, 14);
        localRSInterface.child(1, 60602, 19, 41);
        localRSInterface.child(2, 60603, 19, 41);
        localRSInterface.child(3, 60605, 19, 76);
        localRSInterface.child(4, 60606, 19, 76);
        localRSInterface.child(5, 60608, 19, 111);
        localRSInterface.child(6, 60609, 19, 111);
        localRSInterface.child(7, 60611, 19, 146);
        localRSInterface.child(8, 60612, 19, 146);
        localRSInterface.child(9, 60614, 19, 181);
        localRSInterface.child(10, 60615, 19, 181);
        localRSInterface.child(11, 60617, 19, 216);
        localRSInterface.child(12, 60618, 19, 216);
        localRSInterface.child(13, 60622, 153, 60);
        localRSInterface.child(14, 60623, 153, 60);
        localRSInterface.child(15, 60625, 267, 60);
        localRSInterface.child(16, 60626, 267, 60);
        localRSInterface.child(17, 60628, 383, 60);
        localRSInterface.child(18, 60629, 383, 60);
        localRSInterface.child(19, 60631, 153, 108);
        localRSInterface.child(20, 60632, 153, 108);
        localRSInterface.child(21, 60634, 267, 108);
        localRSInterface.child(22, 60635, 267, 108);
        localRSInterface.child(23, 60637, 383, 108);
        localRSInterface.child(24, 60638, 383, 108);
        localRSInterface.child(25, 60640, 153, 156);
        localRSInterface.child(26, 60641, 153, 156);
        localRSInterface.child(27, 60643, 267, 156);
        localRSInterface.child(28, 60644, 267, 156);
        localRSInterface.child(29, 60646, 383, 156);
        localRSInterface.child(30, 60647, 383, 156);
        localRSInterface.child(31, 60652, 484, 17);
        localRSInterface.child(32, 60653, 484, 17);
        localRSInterface.child(33, 60656, 75, 50);
        localRSInterface.child(34, 60657, 75, 85);
        localRSInterface.child(35, 60658, 75, 120);
        localRSInterface.child(36, 60659, 75, 155);
        localRSInterface.child(37, 60660, 75, 190);
        localRSInterface.child(38, 60661, 75, 225);
        localRSInterface.child(39, 60662, 198, 61);
        localRSInterface.child(40, 60663, 198, 75);
        localRSInterface.child(41, 60664, 313, 61);
        localRSInterface.child(42, 60665, 313, 75);
        localRSInterface.child(43, 60666, 429, 61);
        localRSInterface.child(44, 60667, 429, 75);
        localRSInterface.child(45, 60668, 198, 109);
        localRSInterface.child(46, 60669, 198, 122);
        localRSInterface.child(47, 60670, 313, 109);
        localRSInterface.child(48, 60671, 313, 123);
        localRSInterface.child(49, 60672, 429, 109);
        localRSInterface.child(50, 60673, 429, 123);
        localRSInterface.child(51, 60674, 198, 157);
        localRSInterface.child(52, 60675, 198, 171);
        localRSInterface.child(53, 60676, 313, 157);
        localRSInterface.child(54, 60677, 313, 171);
        localRSInterface.child(55, 60678, 429, 157);
        localRSInterface.child(56, 60679, 429, 171);
        localRSInterface.child(57, 60690, 258, 18);
        localRSInterface.child(58, 60691, 153, 204);
        localRSInterface.child(59, 60692, 153, 204);
        localRSInterface.child(60, 60694, 267, 204);
        localRSInterface.child(61, 60695, 267, 204);
        localRSInterface.child(62, 60697, 383, 204);
        localRSInterface.child(63, 60698, 383, 204);
        localRSInterface.child(64, 18374, 198, 205);
        localRSInterface.child(65, 60701, 198, 219);
        localRSInterface.child(66, 60702, 313, 205);
        localRSInterface.child(67, 60703, 313, 219);
        localRSInterface.child(68, 60704, 429, 205);
        localRSInterface.child(69, 60705, 429, 219);
    }

    public static void minigameTeleports(TextDrawingArea[] paramArrayOfTextDrawingArea) {
        RSInterface localRSInterface = addInterface(60700);
        setChildren(70, localRSInterface);
        localRSInterface.child(0, 60601, 6, 14);
        localRSInterface.child(1, 60602, 19, 41);
        localRSInterface.child(2, 60603, 19, 41);
        localRSInterface.child(3, 60605, 19, 76);
        localRSInterface.child(4, 60606, 19, 76);
        localRSInterface.child(5, 60608, 19, 111);
        localRSInterface.child(6, 60609, 19, 111);
        localRSInterface.child(7, 60611, 19, 146);
        localRSInterface.child(8, 60612, 19, 146);
        localRSInterface.child(9, 60614, 19, 181);
        localRSInterface.child(10, 60615, 19, 181);
        localRSInterface.child(11, 60617, 19, 216);
        localRSInterface.child(12, 60618, 19, 216);
        localRSInterface.child(13, 60622, 153, 60);
        localRSInterface.child(14, 60623, 153, 60);
        localRSInterface.child(15, 60625, 267, 60);
        localRSInterface.child(16, 60626, 267, 60);
        localRSInterface.child(17, 60628, 383, 60);
        localRSInterface.child(18, 60629, 383, 60);
        localRSInterface.child(19, 60631, 153, 108);
        localRSInterface.child(20, 60632, 153, 108);
        localRSInterface.child(21, 60634, 267, 108);
        localRSInterface.child(22, 60635, 267, 108);
        localRSInterface.child(23, 60637, 383, 108);
        localRSInterface.child(24, 60638, 383, 108);
        localRSInterface.child(25, 60640, 153, 156);
        localRSInterface.child(26, 60641, 153, 156);
        localRSInterface.child(27, 60643, 267, 156);
        localRSInterface.child(28, 60644, 267, 156);
        localRSInterface.child(29, 60646, 383, 156);
        localRSInterface.child(30, 60647, 383, 156);
        localRSInterface.child(31, 60652, 484, 17);
        localRSInterface.child(32, 60653, 484, 17);
        localRSInterface.child(33, 60656, 75, 50);
        localRSInterface.child(34, 60657, 75, 85);
        localRSInterface.child(35, 60658, 75, 120);
        localRSInterface.child(36, 60659, 75, 155);
        localRSInterface.child(37, 60660, 75, 190);
        localRSInterface.child(38, 60661, 75, 225);
        localRSInterface.child(39, 60662, 198, 61);
        localRSInterface.child(40, 60663, 198, 75);
        localRSInterface.child(41, 60664, 313, 61);
        localRSInterface.child(42, 60665, 313, 75);
        localRSInterface.child(43, 60666, 429, 61);
        localRSInterface.child(44, 60667, 429, 75);
        localRSInterface.child(45, 60668, 198, 109);
        localRSInterface.child(46, 60669, 198, 122);
        localRSInterface.child(47, 60670, 313, 109);
        localRSInterface.child(48, 60671, 313, 123);
        localRSInterface.child(49, 60672, 429, 109);
        localRSInterface.child(50, 60673, 429, 123);
        localRSInterface.child(51, 60674, 198, 157);
        localRSInterface.child(52, 60675, 198, 171);
        localRSInterface.child(53, 60676, 313, 157);
        localRSInterface.child(54, 60677, 313, 171);
        localRSInterface.child(55, 60678, 429, 157);
        localRSInterface.child(56, 60679, 429, 171);
        localRSInterface.child(57, 60690, 258, 18);
        localRSInterface.child(58, 60691, 153, 204);
        localRSInterface.child(59, 60692, 153, 204);
        localRSInterface.child(60, 60694, 267, 204);
        localRSInterface.child(61, 60695, 267, 204);
        localRSInterface.child(62, 60697, 383, 204);
        localRSInterface.child(63, 60698, 383, 204);
        localRSInterface.child(64, 18374, 198, 205);
        localRSInterface.child(65, 60701, 198, 219);
        localRSInterface.child(66, 60702, 313, 205);
        localRSInterface.child(67, 60703, 313, 219);
        localRSInterface.child(68, 60704, 429, 205);
        localRSInterface.child(69, 60705, 429, 219);

    }

    public static void bossesTeleports(TextDrawingArea[] paramArrayOfTextDrawingArea) {
        RSInterface localRSInterface = addInterface(60800);
        setChildren(70, localRSInterface);
        localRSInterface.child(0, 60601, 6, 14);
        localRSInterface.child(1, 60602, 19, 41);
        localRSInterface.child(2, 60603, 19, 41);
        localRSInterface.child(3, 60605, 19, 76);
        localRSInterface.child(4, 60606, 19, 76);
        localRSInterface.child(5, 60608, 19, 111);
        localRSInterface.child(6, 60609, 19, 111);
        localRSInterface.child(7, 60611, 19, 146);
        localRSInterface.child(8, 60612, 19, 146);
        localRSInterface.child(9, 60614, 19, 181);
        localRSInterface.child(10, 60615, 19, 181);
        localRSInterface.child(11, 60617, 19, 216);
        localRSInterface.child(12, 60618, 19, 216);
        localRSInterface.child(13, 60622, 153, 60);
        localRSInterface.child(14, 60623, 153, 60);
        localRSInterface.child(15, 60625, 267, 60);
        localRSInterface.child(16, 60626, 267, 60);
        localRSInterface.child(17, 60628, 383, 60);
        localRSInterface.child(18, 60629, 383, 60);
        localRSInterface.child(19, 60631, 153, 108);
        localRSInterface.child(20, 60632, 153, 108);
        localRSInterface.child(21, 60634, 267, 108);
        localRSInterface.child(22, 60635, 267, 108);
        localRSInterface.child(23, 60637, 383, 108);
        localRSInterface.child(24, 60638, 383, 108);
        localRSInterface.child(25, 60640, 153, 156);
        localRSInterface.child(26, 60641, 153, 156);
        localRSInterface.child(27, 60643, 267, 156);
        localRSInterface.child(28, 60644, 267, 156);
        localRSInterface.child(29, 60646, 383, 156);
        localRSInterface.child(30, 60647, 383, 156);
        localRSInterface.child(31, 60652, 484, 17);
        localRSInterface.child(32, 60653, 484, 17);
        localRSInterface.child(33, 60656, 75, 50);
        localRSInterface.child(34, 60657, 75, 85);
        localRSInterface.child(35, 60658, 75, 120);
        localRSInterface.child(36, 60659, 75, 155);
        localRSInterface.child(37, 60660, 75, 190);
        localRSInterface.child(38, 60661, 75, 225);
        localRSInterface.child(39, 60662, 198, 61);
        localRSInterface.child(40, 60663, 198, 75);
        localRSInterface.child(41, 60664, 313, 61);
        localRSInterface.child(42, 60665, 313, 75);
        localRSInterface.child(43, 60666, 429, 61);
        localRSInterface.child(44, 60667, 429, 75);
        localRSInterface.child(45, 60668, 198, 109);
        localRSInterface.child(46, 60669, 198, 122);
        localRSInterface.child(47, 60670, 313, 109);
        localRSInterface.child(48, 60671, 313, 123);
        localRSInterface.child(49, 60672, 429, 109);
        localRSInterface.child(50, 60673, 429, 123);
        localRSInterface.child(51, 60674, 198, 157);
        localRSInterface.child(52, 60675, 198, 171);
        localRSInterface.child(53, 60676, 313, 157);
        localRSInterface.child(54, 60677, 313, 171);
        localRSInterface.child(55, 60678, 429, 157);
        localRSInterface.child(56, 60679, 429, 171);
        localRSInterface.child(57, 60690, 258, 18);
        localRSInterface.child(58, 60691, 153, 204);
        localRSInterface.child(59, 60692, 153, 204);
        localRSInterface.child(60, 60694, 267, 204);
        localRSInterface.child(61, 60695, 267, 204);
        localRSInterface.child(62, 60697, 383, 204);
        localRSInterface.child(63, 60698, 383, 204);
        localRSInterface.child(64, 18374, 198, 205);
        localRSInterface.child(65, 60701, 198, 219);
        localRSInterface.child(66, 60702, 313, 205);
        localRSInterface.child(67, 60703, 313, 219);
        localRSInterface.child(68, 60704, 429, 205);
        localRSInterface.child(69, 60705, 429, 219);
    }

    public static void playerKillingTeleports(TextDrawingArea[] paramArrayOfTextDrawingArea) {
        RSInterface localRSInterface = addInterface(60900);
        setChildren(70, localRSInterface);
        localRSInterface.child(0, 60601, 6, 14);
        localRSInterface.child(1, 60602, 19, 41);
        localRSInterface.child(2, 60603, 19, 41);
        localRSInterface.child(3, 60605, 19, 76);
        localRSInterface.child(4, 60606, 19, 76);
        localRSInterface.child(5, 60608, 19, 111);
        localRSInterface.child(6, 60609, 19, 111);
        localRSInterface.child(7, 60611, 19, 146);
        localRSInterface.child(8, 60612, 19, 146);
        localRSInterface.child(9, 60614, 19, 181);
        localRSInterface.child(10, 60615, 19, 181);
        localRSInterface.child(11, 60617, 19, 216);
        localRSInterface.child(12, 60618, 19, 216);
        localRSInterface.child(13, 60622, 153, 60);
        localRSInterface.child(14, 60623, 153, 60);
        localRSInterface.child(15, 60625, 267, 60);
        localRSInterface.child(16, 60626, 267, 60);
        localRSInterface.child(17, 60628, 383, 60);
        localRSInterface.child(18, 60629, 383, 60);
        localRSInterface.child(19, 60631, 153, 108);
        localRSInterface.child(20, 60632, 153, 108);
        localRSInterface.child(21, 60634, 267, 108);
        localRSInterface.child(22, 60635, 267, 108);
        localRSInterface.child(23, 60637, 383, 108);
        localRSInterface.child(24, 60638, 383, 108);
        localRSInterface.child(25, 60640, 153, 156);
        localRSInterface.child(26, 60641, 153, 156);
        localRSInterface.child(27, 60643, 267, 156);
        localRSInterface.child(28, 60644, 267, 156);
        localRSInterface.child(29, 60646, 383, 156);
        localRSInterface.child(30, 60647, 383, 156);
        localRSInterface.child(31, 60652, 484, 17);
        localRSInterface.child(32, 60653, 484, 17);
        localRSInterface.child(33, 60656, 75, 50);
        localRSInterface.child(34, 60657, 75, 85);
        localRSInterface.child(35, 60658, 75, 120);
        localRSInterface.child(36, 60659, 75, 155);
        localRSInterface.child(37, 60660, 75, 190);
        localRSInterface.child(38, 60661, 75, 225);
        localRSInterface.child(39, 60662, 198, 61);
        localRSInterface.child(40, 60663, 198, 75);
        localRSInterface.child(41, 60664, 313, 61);
        localRSInterface.child(42, 60665, 313, 75);
        localRSInterface.child(43, 60666, 429, 61);
        localRSInterface.child(44, 60667, 429, 75);
        localRSInterface.child(45, 60668, 198, 109);
        localRSInterface.child(46, 60669, 198, 122);
        localRSInterface.child(47, 60670, 313, 109);
        localRSInterface.child(48, 60671, 313, 123);
        localRSInterface.child(49, 60672, 429, 109);
        localRSInterface.child(50, 60673, 429, 123);
        localRSInterface.child(51, 60674, 198, 157);
        localRSInterface.child(52, 60675, 198, 171);
        localRSInterface.child(53, 60676, 313, 157);
        localRSInterface.child(54, 60677, 313, 171);
        localRSInterface.child(55, 60678, 429, 157);
        localRSInterface.child(56, 60679, 429, 171);
        localRSInterface.child(57, 60690, 258, 18);
        localRSInterface.child(58, 60691, 153, 204);
        localRSInterface.child(59, 60692, 153, 204);
        localRSInterface.child(60, 60694, 267, 204);
        localRSInterface.child(61, 60695, 267, 204);
        localRSInterface.child(62, 60697, 383, 204);
        localRSInterface.child(63, 60698, 383, 204);
        localRSInterface.child(64, 18374, 198, 205);
        localRSInterface.child(65, 60701, 198, 219);
        localRSInterface.child(66, 60702, 313, 205);
        localRSInterface.child(67, 60703, 313, 219);
        localRSInterface.child(68, 60704, 429, 205);
        localRSInterface.child(69, 60705, 429, 219);
    }

    public static void skillingTeleports(TextDrawingArea[] paramArrayOfTextDrawingArea) {
        RSInterface localRSInterface = addInterface(61000);
        setChildren(70, localRSInterface);
        localRSInterface.child(0, 60601, 6, 14);
        localRSInterface.child(1, 60602, 19, 41);
        localRSInterface.child(2, 60603, 19, 41);
        localRSInterface.child(3, 60605, 19, 76);
        localRSInterface.child(4, 60606, 19, 76);
        localRSInterface.child(5, 60608, 19, 111);
        localRSInterface.child(6, 60609, 19, 111);
        localRSInterface.child(7, 60611, 19, 146);
        localRSInterface.child(8, 60612, 19, 146);
        localRSInterface.child(9, 60614, 19, 181);
        localRSInterface.child(10, 60615, 19, 181);
        localRSInterface.child(11, 60617, 19, 216);
        localRSInterface.child(12, 60618, 19, 216);
        localRSInterface.child(13, 60622, 153, 60);
        localRSInterface.child(14, 60623, 153, 60);
        localRSInterface.child(15, 60625, 267, 60);
        localRSInterface.child(16, 60626, 267, 60);
        localRSInterface.child(17, 60628, 383, 60);
        localRSInterface.child(18, 60629, 383, 60);
        localRSInterface.child(19, 60631, 153, 108);
        localRSInterface.child(20, 60632, 153, 108);
        localRSInterface.child(21, 60634, 267, 108);
        localRSInterface.child(22, 60635, 267, 108);
        localRSInterface.child(23, 60637, 383, 108);
        localRSInterface.child(24, 60638, 383, 108);
        localRSInterface.child(25, 60640, 153, 156);
        localRSInterface.child(26, 60641, 153, 156);
        localRSInterface.child(27, 60643, 267, 156);
        localRSInterface.child(28, 60644, 267, 156);
        localRSInterface.child(29, 60646, 383, 156);
        localRSInterface.child(30, 60647, 383, 156);
        localRSInterface.child(31, 60652, 484, 17);
        localRSInterface.child(32, 60653, 484, 17);
        localRSInterface.child(33, 60656, 75, 50);
        localRSInterface.child(34, 60657, 75, 85);
        localRSInterface.child(35, 60658, 75, 120);
        localRSInterface.child(36, 60659, 75, 155);
        localRSInterface.child(37, 60660, 75, 190);
        localRSInterface.child(38, 60661, 75, 225);
        localRSInterface.child(39, 60662, 198, 61);
        localRSInterface.child(40, 60663, 198, 75);
        localRSInterface.child(41, 60664, 313, 61);
        localRSInterface.child(42, 60665, 313, 75);
        localRSInterface.child(43, 60666, 429, 61);
        localRSInterface.child(44, 60667, 429, 75);
        localRSInterface.child(45, 60668, 198, 109);
        localRSInterface.child(46, 60669, 198, 122);
        localRSInterface.child(47, 60670, 313, 109);
        localRSInterface.child(48, 60671, 313, 123);
        localRSInterface.child(49, 60672, 429, 109);
        localRSInterface.child(50, 60673, 429, 123);
        localRSInterface.child(51, 60674, 198, 157);
        localRSInterface.child(52, 60675, 198, 171);
        localRSInterface.child(53, 60676, 313, 157);
        localRSInterface.child(54, 60677, 313, 171);
        localRSInterface.child(55, 60678, 429, 157);
        localRSInterface.child(56, 60679, 429, 171);
        localRSInterface.child(57, 60690, 258, 18);
        localRSInterface.child(58, 60691, 153, 204);
        localRSInterface.child(59, 60692, 153, 204);
        localRSInterface.child(60, 60694, 267, 204);
        localRSInterface.child(61, 60695, 267, 204);
        localRSInterface.child(62, 60697, 383, 204);
        localRSInterface.child(63, 60698, 383, 204);
        localRSInterface.child(64, 18374, 198, 205);
        localRSInterface.child(65, 60701, 198, 219);
        localRSInterface.child(66, 60702, 313, 205);
        localRSInterface.child(67, 60703, 313, 219);
        localRSInterface.child(68, 60704, 429, 205);
        localRSInterface.child(69, 60705, 429, 219);
    }

    public static void donatorTeleports(TextDrawingArea[] paramArrayOfTextDrawingArea) {
        RSInterface localRSInterface = addInterface(61100);
        setChildren(70, localRSInterface);
        localRSInterface.child(0, 60601, 6, 14);
        localRSInterface.child(1, 60602, 19, 41);
        localRSInterface.child(2, 60603, 19, 41);
        localRSInterface.child(3, 60605, 19, 76);
        localRSInterface.child(4, 60606, 19, 76);
        localRSInterface.child(5, 60608, 19, 111);
        localRSInterface.child(6, 60609, 19, 111);
        localRSInterface.child(7, 60611, 19, 146);
        localRSInterface.child(8, 60612, 19, 146);
        localRSInterface.child(9, 60614, 19, 181);
        localRSInterface.child(10, 60615, 19, 181);
        localRSInterface.child(11, 60617, 19, 216);
        localRSInterface.child(12, 60618, 19, 216);
        localRSInterface.child(13, 60622, 153, 60);
        localRSInterface.child(14, 60623, 153, 60);
        localRSInterface.child(15, 60625, 267, 60);
        localRSInterface.child(16, 60626, 267, 60);
        localRSInterface.child(17, 60628, 383, 60);
        localRSInterface.child(18, 60629, 383, 60);
        localRSInterface.child(19, 60631, 153, 108);
        localRSInterface.child(20, 60632, 153, 108);
        localRSInterface.child(21, 60634, 267, 108);
        localRSInterface.child(22, 60635, 267, 108);
        localRSInterface.child(23, 60637, 383, 108);
        localRSInterface.child(24, 60638, 383, 108);
        localRSInterface.child(25, 60640, 153, 156);
        localRSInterface.child(26, 60641, 153, 156);
        localRSInterface.child(27, 60643, 267, 156);
        localRSInterface.child(28, 60644, 267, 156);
        localRSInterface.child(29, 60646, 383, 156);
        localRSInterface.child(30, 60647, 383, 156);
        localRSInterface.child(31, 60652, 484, 17);
        localRSInterface.child(32, 60653, 484, 17);
        localRSInterface.child(33, 60656, 75, 50);
        localRSInterface.child(34, 60657, 75, 85);
        localRSInterface.child(35, 60658, 75, 120);
        localRSInterface.child(36, 60659, 75, 155);
        localRSInterface.child(37, 60660, 75, 190);
        localRSInterface.child(38, 60661, 75, 225);
        localRSInterface.child(39, 60662, 198, 61);
        localRSInterface.child(40, 60663, 198, 75);
        localRSInterface.child(41, 60664, 313, 61);
        localRSInterface.child(42, 60665, 313, 75);
        localRSInterface.child(43, 60666, 429, 61);
        localRSInterface.child(44, 60667, 429, 75);
        localRSInterface.child(45, 60668, 198, 109);
        localRSInterface.child(46, 60669, 198, 122);
        localRSInterface.child(47, 60670, 313, 109);
        localRSInterface.child(48, 60671, 313, 123);
        localRSInterface.child(49, 60672, 429, 109);
        localRSInterface.child(50, 60673, 429, 123);
        localRSInterface.child(51, 60674, 198, 157);
        localRSInterface.child(52, 60675, 198, 171);
        localRSInterface.child(53, 60676, 313, 157);
        localRSInterface.child(54, 60677, 313, 171);
        localRSInterface.child(55, 60678, 429, 157);
        localRSInterface.child(56, 60679, 429, 171);
        localRSInterface.child(57, 60690, 258, 18);
        localRSInterface.child(58, 60691, 153, 204);
        localRSInterface.child(59, 60692, 153, 204);
        localRSInterface.child(60, 60694, 267, 204);
        localRSInterface.child(61, 60695, 267, 204);
        localRSInterface.child(62, 60697, 383, 204);
        localRSInterface.child(63, 60698, 383, 204);
        localRSInterface.child(64, 18374, 198, 205);
        localRSInterface.child(65, 60701, 198, 219);
        localRSInterface.child(66, 60702, 313, 205);
        localRSInterface.child(67, 60703, 313, 219);
        localRSInterface.child(68, 60704, 429, 205);
        localRSInterface.child(69, 60705, 429, 219);
    }


    public void OsDropViewer() {
        RSInterface tab = addInterface(33000);
        //String dir = "DropViewer/SPRITE";
        addSprite(33001, 1217); //changeid
        addHoverButton(33002, 1208, 21, 21, "Close", 0, 33003, 1); //changeid
        addHoveredButton(33003, 1209, 21, 21, 33004); //changeid
        addText(33005, "Loot Viewer", tda, 2, 0xFFA500, true, true);
        int x = 7, y = 7;
        tab.totalChildren(9);
        tab.child(0, 33001, 0 + x, 0 + y);
        tab.child(1, 33002, 472 + x, 7 + y);
        tab.child(2, 33003, 472 + x, 7 + y);
        tab.child(3, 33005, 250 + x, 10 + y);
        tab.child(4, 33006, 8 + x, 41 + y);
        tab.child(5, 34000, 150 + x, 35 + y);
        tab.child(6, 33007, 6 + x, 58 + y);

        addText(33250, "Include DR Bonus", tda, 0, 0xFFA500, false, true);
        tab.child(7, 33250, 26 + x, 13 + y);

        addConfigButtonWSpriteLoader(33251, 33000, 1333, 1332, 15, 15, "Select", 0, 4, 2451);
        tab.child(8, 33251, 7 + x, 10 + y);


        final RSInterface results = addInterface(33007);
        results.width = 122;
        results.height = 258;
        results.scrollMax = 300;

        results.totalChildren(100);
        for (int j = 0; j < 100; j++) {
            addClickableText(33008 + j, "", "View Drops", tda, 0, 0xff0000, false, true, 110);
            results.child(j, 33008 + j, 3, 2 + (j * 14));
        }

        RSInterface main = addInterface(34000);
        main.totalChildren(720);
        main.width = 328;
        main.height = 281;
        main.scrollMax = 2560;
        addSprite(34001, 1210); //changeid
        addSprite(34002, 1211); //changeid
        for (int i = 0; i < 40; i++) {
            main.child(i, 34001, 0, (i * 64));
            main.child(i + 40, 34002, 0, 32 + (i * 64));
        }
        addText(34003, "Amount:", tda, 0, 0xff9040, true, true);
        addText(34004, "Chance:", tda, 0, 0xff9040, true, true);
        addText(34005, "Value:", tda, 0, 0xff9040, true, true);
        for (int i = 0; i < 80; i++) {
            itemGroup(34010 + i, 1, 1, 1, 1, false, false);
            interfaceCache[34010 + i].inv[0] = 14485;
            interfaceCache[34010 + i].invStackSizes[0] = 1;
            addText(34100 + i, "Name", tda, 1, 0xFFA500, false, true);
            addText(34200 + i, "Amount", tda, 0, 0xffffff, true, true);
            addText(34300 + i, "Chance", tda, 0, 0xffffff, true, true);
            addText(34400 + i, "Value", tda, 0, 0xffffff, true, true);
            int yy = (i * 32);
            main.child(80 + i, 34010 + i, 1, 0 + yy);
            main.child(160 + i, 34100 + i, 39, 6 + yy);
            main.child(240 + i, 34003, 175, 2 + yy);
            main.child(320 + i, 34004, 234, 2 + yy);
            main.child(400 + i, 34005, 293, 2 + yy);
            main.child(480 + i, 34200 + i, 175, 14 + yy);
            main.child(560 + i, 34300 + i, 234, 14 + yy);
            main.child(640 + i, 34400 + i, 293, 14 + yy);
        }

        addClickableTextHeight(33006, "@whi@Search for an NPC", "Filter", tda, 0, 0xff0000, true, true, 134, 10);

		/*addTextInput(33006, 134, 19, 0, "Search for an NPC", tab, new InterfaceTextInput() {
			@Override
			public void handleInput() {
				int amount = 0;
				results.scrollMax = 0;
				for (int b = 0; b < 100; b++) {
					interfaceCache[33008 + b].message = "";
				}
				if (!interfaceCache[33006].inputText.equals("")) {
					for (int b = 0; b < MobDefinition.maximum; b++) {
						//MobDefinition e = MobDefinition.get(b);
						if (MobDefinition.get(b) != null && MobDefinition.get(b).name != null && MobDefinition.get(b).name.toLowerCase().contains(interfaceCache[33006].inputText.toLowerCase())) {
							interfaceCache[33008 + amount].message = "@yel@"+MobDefinition.get(b).name;
							amount++;
							results.scrollMax += 14;
							if (amount == 100)
								break;
						}
					}
				}
			}
		});*/
    }


    private void equipmentTab() {
        int[] remove = {
                1672, 1669, 1670, 1671, 1673, 1674, 1675,
                1676, 1677, 1678, 1679, 1680, 1681, 1682,
                1683, 1684, 1685, 1686, 1687, 1668};
        for (int i : remove) {
            removeSomething(i);
        }
        RSInterface newTab = addTabInterface(15000);
        addButtonWSpriteLoader(15001, 618, "Show Equipment Stats", 41, 40);
        addButtonWSpriteLoader(15009, 619, "Toggle Override", 41, 40);
        addButtonWSpriteLoader(15003, 620, "Open Items kept on Death", 41, 40);
        //addButtonWSpriteLoader(15004, 621, "Toggle Experience", 41, 40);

        addButtonWSpriteLoader(15002, 1545, null, 36, 36);
        addButtonWSpriteLoader(15005, 1545, null, 36, 36);
        addSpriteLoader(15006, 1546);
        addSpriteLoader(15007, 1547);

        newTab.totalChildren(5);
        int c = 0;
        setBounds(1644, 0, 0, c++, newTab);
        setBounds(15001, 22, 205, c++, newTab);
        setBounds(15003, 130, 205, c++, newTab);
        setBounds(15004, 999, 205, c++, newTab);
        setBounds(15009, 76, 205, c++, newTab);
        //   setBounds(15005, 120, 120, c++, newTab);
        //   setBounds(15006, 26, 126, c++, newTab);
        //   setBounds(15007, 120, 120, c++, newTab);


        RSInterface asdd = interfaceCache[1644];
        // asdd.totalChildren(28);

        for (int i = 0; i < 28; i++) {
            //   asdd.children[i] = 1688;
            // childX = new int[t];
            // childY = new int[t];
        }

        asdd.children = new int[]{
                1645, 1646, 1647, 1648, 1649,
                1650, 1651, 1652, 1653, 1654,
                1655, 1656, 1657, 1658, 1659,
                1660, 1661, 1662, 1663, 1664,
                1665, 1666, 1667, 1668, 1669,
                1670, 1671, 15002, 15005, 15006, 15007,
                1688};

        asdd.childX = new int[]{
                78, 78, 78, 78, 22,
                22, 134, 134, 48, 107,
                58, 112, 78, 37, 78,
                119, 22, 78, 134, 78,
                78, 22, 134, 8, 8,
                9, 9,
                22, 134, 28, 143
                , 24
        };
        asdd.childY = new int[]{
                39, 68, 105, 146, 118
                , 154, 118, 154, 81, 81
                , 42, 41, 4, 43, 43
                , 43, 82, 82, 82, 122
                , 162, 162, 162, 200, 200
                , 201, 201,
                122, 122, 128, 126
                , 44
        };

        RSInterface equipm = interfaceCache[1644];
        RSInterface equipm1 = interfaceCache[1688];

        //setBounds(1669, 0, 0, 5, newTab);
    }

    private void duelingInterface() {
        RSInterface newTab = addTabInterface(6575);
        addConfigButtonWSpriteLoader(37890, 6575, 1, 1, 10, 10, "Head", 286, 1, 286);
    }

    private void skillTabInterface() {
        RSInterface skill = addTabInterface(3917);
        addSpriteLoader(28100, 623);
        skill.totalChildren(1);
        skill.child(0, 28100, 72, 237);
        int[] logoutID = {2450, 2451, 2452};
        int[] logoutID2 = {2458};
        for (int i : logoutID) {
            RSInterface Logout = interfaceCache[i];
            Logout.disabledColor = 0xFF981F;
            Logout.contentType = 0;
        }
        for (int i : logoutID2) {
            RSInterface Logout = interfaceCache[i];
            Logout.contentType = 0;
        }
        int[] buttons = {8654, 8655, 8656, 8657, 8658, 8659, 8660, 8861, 8662, 8663, 8664, 8665, 8666, 8667, 8668, 8669, 8670, 8671, 8672, 12162, 13928, 28177, 28178, 28179, 28180};
        int[] hovers = {4040, 4076, 4112, 4046, 4082, 4118, 4052, 4088, 4124, 4058, 4094, 4130, 4064, 4100, 4136, 4070, 4106, 4142, 4160, 2832, 13917, 28173, 28174, 28175, 28176};
        int[][] text = {{4004, 4005}, {4016, 4017}, {4028, 4029},
                {4006, 4007}, {4018, 4019}, {4030, 4031}, {4008, 4009},
                {4020, 4021}, {4032, 4033}, {4010, 4011}, {4022, 4023},
                {4034, 4035}, {4012, 4013}, {4024, 4025}, {4036, 4037},
                {4014, 4015}, {4026, 4027}, {4038, 4039}, {4152, 4153},
                {12166, 12167}, {13926, 13927}, {28165, 28169},
                {28166, 28170}, {28167, 28171}, {28168, 28172}};

        int[] icons = {3965, 3966, 3967, 3968, 3969, 3970, 3971, 3972, 3973,
                3974, 3975, 3976, 3977, 3978, 3979, 3980, 3981, 3982, 4151,
                12165, 13925, 28181, 28182, 28183, 28184};

        int[][] buttonCoords = {{3, 5}, {65, 5}, {127, 5}, {3, 33},
                {65, 33}, {127, 33}, {3, 61}, {65, 61}, {127, 61},
                {3, 89}, {65, 89}, {127, 89}, {3, 117}, {65, 117},
                {127, 117}, {3, 145}, {65, 145}, {127, 145},
                {3, 173}, {65, 173}, {127, 173}, {3, 201}, {65, 201},
                {127, 201}, {3, 229}};
        int[][] iconCoords = {{5, 7}, {68, 8}, {130, 7}, {8, 35},
                {67, 34}, {130, 37}, {8, 65}, {66, 64}, {130, 62},
                {6, 92}, {67, 97}, {132, 91}, {5, 119}, {69, 121},
                {129, 119}, {5, 148}, {68, 147}, {131, 147},
                {5, 174}, {68, 174}, {129, 175}, {5, 203}, {68, 202},
                {130, 203}, {5, 231}};
        int[][] textCoords = {{29, 7, 44, 19}, {91, 7, 106, 19},
                {153, 7, 168, 19}, {29, 35, 44, 47}, {91, 35, 106, 47},
                {153, 35, 168, 47}, {29, 63, 44, 75}, {91, 63, 106, 75},
                {153, 63, 168, 75}, {29, 91, 44, 103},
                {91, 91, 106, 103}, {153, 91, 168, 103},
                {29, 119, 44, 131}, {91, 119, 106, 131},
                {153, 119, 168, 131}, {29, 147, 44, 159},
                {91, 147, 106, 159}, {153, 147, 168, 159},
                {29, 175, 44, 187}, {91, 175, 106, 187},
                {153, 175, 168, 187}, {29, 203, 44, 215},
                {91, 203, 106, 215}, {153, 203, 168, 215},
                {29, 231, 44, 243}};
        int[][] newText = {{28165, 28166, 28167, 28168},
                {28169, 28170, 28171, 28172}};
        int[] spriteIds = {625, 636, 639, 645, 624, 644, 629, 635, 633, 641, 647, 627, 640, 628, 632, 638, 634, 648, 642, 643, 631, 626, 637, 646, 630};
        int frame = 0;

        for (int i = 0; i < hovers.length; i++) {
            addSkillButton(buttons[i], Skills.SKILL_NAMES[i]);
            createSkillHover(hovers[i], 205 + i);

            addHoverButtonWSpriteLoader(79924 + i, 622, 60, 27, "Set Level Goal", 1321, -1, 1);
            addHoverButtonWSpriteLoader(79949 + i, 622, 60, 27, "Set Exp Goal", 1322, -1, 1);
            addHoverButtonWSpriteLoader(79974 + i, 622, 60, 27, "Clear Goal", 1323, -1, 1);
            addHoverButtonWSpriteLoader(80000 + i, 622, 60, 27, "Prestige", 5000 + i, -1, 1);
			/*
			addHoverButton(79924 + i, getSprite("Interfaces/Skilltab/Button"), 0, 60, 27, "Set Level Goal", 1321, -1, 1);
			addHoverButton(79949 + i, getSprite("Interfaces/Skilltab/Button"), 0, 60, 27, "Set Exp Goal", 1322, -1, 1);
			addHoverButton(79974 + i, getSprite("Interfaces/Skilltab/Button"), 0, 60, 27, "Clear Goal", 1323, -1, 1);
			addHoverButton(80000 + i, getSprite("Interfaces/Skilltab/Button"), 0, 60, 27, "Prestige", 5000+i, -1, 1);


			addSprite(icons[i], getSprite("Interfaces/Skilltab/" + spriteNames[i]));
			 */
            addSpriteLoader(icons[i], spriteIds[i]);
        }

        for (int i = 0; i < 4; i++) {
            addSkillText(newText[0][i], false, i + 21);
            addSkillText(newText[1][i], true, i + 21);
        }
        skill.totalChildren(icons.length + (text.length * 2) + hovers.length + buttons.length * 5 + 1);


        RSInterface totalLevel = addInterface(3984);
        addSpriteLoader(31196, 649);
        createHover(31192, 231, 120);
        addText(31199, "Total Level:", 0xFFEE33, false, true, 52, tda, 0);
        addText(31200, "2475", 0xFFEE33, false, true, 52, tda, 0);

        totalLevel.totalChildren(4);
        totalLevel.child(0, 31196, 65, 229);
        totalLevel.child(1, 31199, 106, 231);
        totalLevel.child(2, 31200, 117, 243);
        totalLevel.child(3, 31192, 38, 230);
        skill.child(frame, 3984, 0, 0);
        frame++;
        for (int i = 0; i < buttons.length; i++) {
            skill.child(frame++, 80000 + i, buttonCoords[i][0], buttonCoords[i][1]);
            skill.child(frame++, 79974 + i, buttonCoords[i][0], buttonCoords[i][1]);
            skill.child(frame++, 79949 + i, buttonCoords[i][0], buttonCoords[i][1]);
            skill.child(frame++, 79924 + i, buttonCoords[i][0], buttonCoords[i][1]);
            skill.child(frame, buttons[i], buttonCoords[i][0], buttonCoords[i][1]);
            frame++;
        }
        for (int i = 0; i < icons.length; i++) {
            skill.child(frame, icons[i], iconCoords[i][0], iconCoords[i][1]);
            frame++;
        }
        for (int i = 0; i < text.length; i++) {
            skill.child(frame, text[i][0], textCoords[i][0], textCoords[i][1]);
            frame++;
        }
        for (int i = 0; i < text.length; i++) {
            skill.child(frame, text[i][1], textCoords[i][2], textCoords[i][3]);
            frame++;
        }
        for (int i = 0; i < hovers.length; i++) {
            skill.child(frame, hovers[i], buttonCoords[i][0], buttonCoords[i][1]);
            frame++;
        }
    }

    /*
     * Quest tab [PLAYER PANEL]
     */
    private void playerPanel() {
        RSInterface tab = addTabInterface(639);
        RSInterface scroll = addTabInterface(15016);
        addText(39155, "Lunite.io", tda, 0, ColorConstants.AQUA, false, true);
        addSpriteLoader(39156, 650);
        addSpriteLoader(39157, 651);
        addSpriteLoader(39158, 650);
        addButtonWSpriteLoader(23835, 1346, "World & Events tab");
        addButtonWSpriteLoader(23836, 1347, "Account info tab");
        addButtonWSpriteLoader(23837, 1348, "Points & Statistics tab");
        addButtonWSpriteLoader(23838, 1349, "Slayer tab");
        scroll.totalChildren(55);
        scroll.width = 174;
        scroll.height = 224;
        scroll.scrollMax = 835;
        tab.totalChildren(9);

        tab.child(0, 39155, 50, 251);
        tab.child(1, 39156, 0, 22);
        tab.child(2, 39157, 0, 25);
        tab.child(3, 39158, 0, 249);
        tab.child(4, 15016, 0, 25);
        tab.child(5, 23835, 5 + 3, 2); // blue.
        tab.child(6, 23836, 50 + 3, 2); // green.
        tab.child(7, 23837, 95 + 3, 2); // red.
        tab.child(8, 23838, 140 + 3, 2); // yello.

        int k = 0;
        int y = 0;
        for (int i = 39159; i < 39214; i++) {
            scroll.child(k, i, 6, y);
            y += 16;
            k++;
			/*if(i == 39162) {
				addClickableText(i, "", "View", tda, 1, 0xff0000, 167, 13);
			} else if(i == 39165) {
				addClickableText(i, "", "Check", tda, 1, 0xff0000, 167, 13);
			} else if(i == 39173 || i == 39174) {
				addClickableText(i, "", "Toggle", tda, 1, 0xff0000, 167, 13);
			} else if(i == 39187 || i ==39188 || i >= 39196 && i <= 39202) {
				addClickableText(i, "", "Open", tda, 1, 0xff0000, 167, 13);
			} else {
				addText(i, "", tda, 1, 0xff0000, false, true);
			}*/
            if (i == 39160) {
                addText(i, "", tda, 2, 0xff0000, false, true);
            } else {
                addText(i, "", tda, 0, 0xff0000, false, true);
            }
        }
    }


    private void killsTracker() {
        RSInterface tab = addTabInterface(55000);
        RSInterface scroll = addTabInterface(55010);
        addText(55001, "Kills Tracker", tda, 2, 16750623, false, true);
        addSpriteLoader(55002, 650);
        addSpriteLoader(55003, 651);
        addSpriteLoader(55004, 650);
        addButtonWSpriteLoader(55005, 1024, "Go Back", 26, 26);

        scroll.totalChildren(44);
        scroll.width = 174;
        scroll.height = 224;
        scroll.scrollMax = 710;
        tab.totalChildren(6);

        tab.child(0, 55001, 5, 3);
        tab.child(1, 55002, 0, 22);
        tab.child(2, 55003, 0, 25);
        tab.child(3, 55004, 0, 249);
        tab.child(4, 55005, 164, 0);
        tab.child(5, 55010, 0, 25);

        int k = 0;
        int y = 0;
        for (int i = 55020; i < 55064; i++) {
            scroll.child(k, i, 6, y);
            y += 16;
            k++;
            addText(i, "", tda, 1, 0xff0000, false, true);
        }
    }

    private void priceCheckerInterface() {
        RSInterface rsi = addTabInterface(42000);
        final String[] options = {"Remove 1", "Remove 5", "Remove 10", "Remove All", "Remove X"};
        addSpriteLoader(18245, 654);
        addCloseButton(18247, 18535, 18536);
        addItemOnInterface(18246, 4393, options);
        rsi.totalChildren(88);
        rsi.child(0, 18245, 10, 20);//was 10 so + 10
        rsi.child(1, 18246, 100, 50);
        rsi.child(2, 18247, 472, 21);
        addText(18248, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(3, 18248, 472, 23);
        addText(18350, "Total value:", tda, 0, 0xFFFFFF, false, true);
        rsi.child(4, 18350, 225, 295); // Open Text
        addText(18351, "0", tda, 0, 0xFFFFFF, true, true);
        rsi.child(5, 18351, 251, 306);
        addText(18352, "", tda, 0, 0xFFFFFF, false, true);
        rsi.child(6, 18352, 120, 150);
        addText(18353, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(7, 18353, 120, 85);
        addText(18354, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(8, 18354, 120, 95);
        addText(18355, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(9, 18355, 120, 105);
        addText(18356, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(10, 18356, 190, 85);
        addText(18357, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(11, 18357, 190, 95);
        addText(18358, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(12, 18358, 190, 105);
        addText(18359, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(13, 18359, 260, 85);
        addText(18360, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(14, 18360, 260, 95);
        addText(18361, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(15, 18361, 260, 105);
        addText(18362, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(16, 18362, 330, 85);
        addText(18363, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(17, 18363, 330, 95);
        addText(18364, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(18, 18364, 330, 105);
        addText(18365, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(19, 18365, 400, 85);
        addText(18366, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(20, 18366, 400, 95);
        addText(18367, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(21, 18367, 400, 105);
        addText(18368, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(22, 18368, 120, 145);
        addText(18369, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(23, 18369, 120, 155);
        addText(18370, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(24, 18370, 120, 165);
        addText(18371, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(25, 18371, 190, 145);
        addText(18372, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(26, 18372, 190, 155);
        addText(18373, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(27, 18373, 190, 165);
        addText(18374, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(28, 18374, 260, 145);
        addText(18375, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(29, 18375, 260, 155);
        addText(18376, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(30, 18376, 260, 165);
        addText(18377, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(31, 18377, 330, 145);
        addText(18378, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(32, 18378, 330, 155);
        addText(18379, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(33, 18379, 330, 165);
        addText(18380, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(34, 18380, 400, 145);
        addText(18381, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(35, 18381, 400, 155);
        addText(18382, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(36, 18382, 400, 165);
        addText(18383, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(37, 18383, 120, 205);
        addText(18384, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(38, 18384, 120, 215);
        addText(18385, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(39, 18385, 120, 225);
        addText(18386, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(40, 18386, 190, 205);
        addText(18387, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(41, 18387, 190, 215);
        addText(18388, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(42, 18388, 190, 225);
        addText(18389, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(43, 18389, 260, 205);
        addText(18390, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(44, 18390, 260, 215);
        addText(18391, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(45, 18391, 260, 225);
        addText(18392, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(46, 18392, 330, 205);
        addText(18393, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(47, 18393, 330, 215);
        addText(18394, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(48, 18394, 330, 225);
        addText(18395, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(49, 18395, 400, 205);
        addText(18396, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(50, 18396, 400, 215);
        addText(18397, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(51, 18397, 400, 225);
        addText(18398, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(52, 18398, 120, 260);
        addText(18399, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(53, 18399, 120, 270);
        addText(18400, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(54, 18400, 120, 280);
        addText(18401, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(55, 18401, 190, 260);
        addText(18402, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(56, 18402, 190, 270);
        addText(18403, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(57, 18403, 190, 280);
        addText(18404, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(58, 18404, 260, 260);
        addText(18405, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(59, 18405, 260, 270);
        addText(18406, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(60, 18406, 260, 280);
        addText(18407, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(61, 18407, 330, 260);
        addText(18408, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(62, 18408, 330, 270);
        addText(18409, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(63, 18409, 330, 280);
        addText(18410, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(64, 18410, 400, 260);
        addText(18411, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(65, 18411, 400, 270);
        addText(18412, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(66, 18412, 400, 280);
        addText(18413, "", tda, 0, 0xFFFFFF, true, true);
        rsi.child(67, 18413, 260, 155);
        /**
         * First row of items
         */
        int child = 68;
        int x = 170;
        for (int i = 18500; i <= 18503; i++) {
            addItemOnInterface(i, 4393, options);
            rsi.child(child, i, x, 50);
            child++;
            x += 70;
        }
        /**
         * Second row of items
         */
        child = 72;
        x = 100;
        for (int i = 0; i <= 4; i++) {
            addItemOnInterface(18504 + i, 4393, options);
            rsi.child(child, 18504 + i, x, 110);
            child++;
            x += 70;
        }
        /*
         * Third row of items
         */
        child = 77;
        x = 100;
        for (int i = 0; i <= 4; i++) {
            addItemOnInterface(18509 + i, 4393, options);
            rsi.child(child, 18509 + i, x, 170);
            child++;
            x += 70;
        }
        /**
         * Fourth row of items
         */
        child = 82;
        x = 100;
        for (int i = 0; i <= 4; i++) {
            addItemOnInterface(18514 + i, 4393, options);
            rsi.child(child, 18514 + i, x, 230);
            child++;
            x += 70;
        }
        rsi.child(87, 18535, 472, 21);
    }

    private void playersOnline() {
        RSInterface tab = addInterface(57000);
        addSpriteLoader(57001, 655);
        addText(57002, "Players Online", 0xff9933, true, true, -1, tda, 2);
        addText(57003, "", 0xff9933, true, true, -1, tda, 1);
        addText(57007, "", 0xff9933, true, true, -1, tda, 0);
        addText(57008, "", 0xff9933, true, true, -1, tda, 3);
        addText(57009, "", 0xff9933, false, true, -1, tda, 0);
        addText(57010, "", 0xff9933, false, true, -1, tda, 0);
        addText(57011, "", 0xff9933, false, true, -1, tda, 0);
        addText(57012, "", 0xff9933, false, true, -1, tda, 0);
        addText(57013, "", 0xff9933, false, true, -1, tda, 0);
        addText(57014, "", 0xff9933, false, true, -1, tda, 0);
        addText(57015, "", 0xff9933, false, true, -1, tda, 0);
        addText(57016, "", 0xff9933, false, true, -1, tda, 0);
        addText(57017, "", 0xff9933, false, true, -1, tda, 0);
        addText(57018, "", 0xff9933, false, true, -1, tda, 0);
        addText(57019, "", 0xff9933, false, true, -1, tda, 0);
        addText(57020, "", 0xff9933, false, true, -1, tda, 0);
        addText(57021, "", 0xff9933, false, true, -1, tda, 0);
        addText(57022, "", 0xff9933, false, true, -1, tda, 0);
        addText(57023, "", 0xff9933, false, true, -1, tda, 0);
        addText(57024, "", 0xff9933, false, true, -1, tda, 0);
        addCloseButton(57004, 57005, 57006);
        addHoverButtonWSpriteLoader(57025, 656, 73, 35, "Add", -1, 57026, 3);
        addHoveredImageWSpriteLoader(57026, 657, 73, 35, 57027);
        addHoverButtonWSpriteLoader(57028, 656, 73, 35, "Ignore", -1, 57029, 3);
        addHoveredImageWSpriteLoader(57029, 657, 73, 35, 57030);
        addText(57031, "Add", 0xff9933, true, true, -1, tda, 0);
        addText(57032, "Ignore", 0xff9933, true, true, -1, tda, 0);
        tab.totalChildren(30);
        tab.child(0, 57001, 14, 5);
        tab.child(1, 57040, 176, 90);
        tab.child(2, 57002, 175, 18);
        tab.child(3, 57003, 415, 58);
        tab.child(4, 57004, 470, 16);
        tab.child(5, 57005, 470, 16);
        tab.child(6, 57007, 415, 20);
        tab.child(7, 57008, 175, 65);
        tab.child(8, 57009, 60, 110);
        tab.child(9, 57010, 60, 125);
        tab.child(10, 57011, 60, 140);
        tab.child(11, 57012, 60, 155);
        tab.child(12, 57013, 60, 170);
        tab.child(13, 57014, 60, 185);
        tab.child(14, 57015, 60, 200);
        tab.child(15, 57016, 60, 215);
        tab.child(16, 57017, 187, 110);
        tab.child(17, 57018, 187, 125);
        tab.child(18, 57019, 187, 140);
        tab.child(19, 57020, 187, 155);
        tab.child(20, 57021, 187, 170);
        tab.child(21, 57022, 187, 185);
        tab.child(22, 57023, 187, 200);
        tab.child(23, 57024, 187, 215);
        tab.child(24, 57025, 80, 262);
        tab.child(25, 57026, 80, 262);
        tab.child(26, 57028, 202, 262);
        tab.child(27, 57029, 202, 262);
        tab.child(28, 57031, 114, 272);
        tab.child(29, 57032, 238, 272);
        RSInterface scrollInterface = addTabInterface(57040);
        scrollInterface.width = 300;
        scrollInterface.height = 231;
        scrollInterface.scrollMax = 850;
        setChildren(100, scrollInterface);
        /**
         * just need to remove that last line
         */


        int y = -4;
        for (int i = 0; i < 100; i++) {
            addHoverText(57041 + i, "", "Select", tda, 3, 0xff9633, false, true, 100, 14);
            setBounds(57041 + i, 178, y, i, scrollInterface);
            y += 15;
        }
    }

    private void godWars() {
        RSInterface rsinterface = addTabInterface(16210);
        addText(16211, " Your killcount", tda, 0, 0xff9040, true, true);
        addText(16212, "Armadyl kills", tda, 0, 0xff9040, true, true);
        addText(16213, "Bandos kills", tda, 0, 0xff9040, true, true);
        addText(16214, "     Saradomin kills", tda, 0, 0xff9040, true, true);
        addText(16215, "  Zamorak kills", tda, 0, 0xff9040, true, true);
        addText(16220, "Zaros kills", tda, 0, 0xff9040, true, true);
        addText(16216, "0", tda, 0, 0x66FFFF, true, true);//armadyl
        addText(16217, "0", tda, 0, 0x66FFFF, true, true);//bandos
        addText(16218, "0", tda, 0, 0x66FFFF, true, true);//saradomin
        addText(16219, "0", tda, 0, 0x66FFFF, true, true);//zamorak
        addText(16221, "0", tda, 0, 0x66FFFF, true, true);//zaros
        rsinterface.scrollMax = 0;
        rsinterface.children = new int[9];
        rsinterface.childX = new int[9];
        rsinterface.childY = new int[9];
        rsinterface.children[0] = 16211;
        rsinterface.childX[0] = -52 + 375 + 30;
        rsinterface.childY[0] = 12;
        rsinterface.children[1] = 16212;
        rsinterface.childX[1] = -52 + 375 + 30;
        rsinterface.childY[1] = 30;
        rsinterface.children[2] = 16213;
        rsinterface.childX[2] = -52 + 375 + 30;
        rsinterface.childY[2] = 44;
        rsinterface.children[3] = 16214;
        rsinterface.childX[3] = -52 + 375 + 30;
        rsinterface.childY[3] = 58;
        rsinterface.children[4] = 16215;
        rsinterface.childX[4] = -52 + 375 + 30;
        rsinterface.childY[4] = 73;
        rsinterface.children[5] = 16216;
        rsinterface.childX[5] = -52 + 460 + 60;
        rsinterface.childY[5] = 31;
        rsinterface.children[6] = 16217;
        rsinterface.childX[6] = -52 + 460 + 60;
        rsinterface.childY[6] = 45;
        rsinterface.children[7] = 16218;
        rsinterface.childX[7] = -52 + 460 + 60;
        rsinterface.childY[7] = 59;
        rsinterface.children[8] = 16219;
        rsinterface.childX[8] = -52 + 460 + 60;
        rsinterface.childY[8] = 74;
    }

    private void editClan() {
        RSInterface tab = addTabInterface(40172);
        addSpriteLoader(47251, 658);
        addHoverSpriteLoaderButton(47252, 659, 150, 35, "Set name", 22222, 47253, 1);
        addHoveredSpriteLoaderButton(47253, 150, 35, 47254, 659);
        addHoverSpriteLoaderButton(47255, 659, 150, 35, "Anyone", -1,
                47256, 1);
        addHoveredSpriteLoaderButton(47256, 150, 35, 47257, 659);

        addHoverButton(48000, "b", 1, 150, 35, "Only me", -1, 47999, 1);
        addHoverButton(48001, "b", 1, 150, 35, "General+", -1, 47999, 1);
        addHoverButton(48002, "b", 1, 150, 35, "Captain+", -1, 47999, 1);
        addHoverButton(48003, "b", 1, 150, 35, "Lieutenant+", -1, 47999, 1);
        addHoverButton(48004, "b", 1, 150, 35, "Sergeant+", -1, 47999, 1);
        addHoverButton(48005, "b", 1, 150, 35, "Corporal+", -1, 47999, 1);
        addHoverButton(48006, "b", 1, 150, 35, "Recruit+", -1, 47999, 1);
        addHoverButton(48007, "b", 1, 150, 35, "Any friends", -1, 47999, 1);

        addHoverSpriteLoaderButton(47258, 660, 150, 35, "Anyone", -1,
                47259, 1);
        addHoveredSpriteLoaderButton(47259, 659, 35, 17260, 727);

        addHoverButton(48010, "b", 1, 150, 35, "Only me", -1, 47999, 1);
        addHoverButton(48011, "b", 1, 150, 35, "General+", -1, 47999, 1);
        addHoverButton(48012, "b", 1, 150, 35, "Captain+", -1, 47999, 1);
        addHoverButton(48013, "b", 1, 150, 35, "Lieutenant+", -1, 47999, 1);
        addHoverButton(48014, "b", 1, 150, 35, "Sergeant+", -1, 47999, 1);
        addHoverButton(48015, "b", 1, 150, 35, "Corporal+", -1, 47999, 1);
        addHoverButton(48016, "b", 1, 150, 35, "Recruit+", -1, 47999, 1);
        addHoverButton(48017, "b", 1, 150, 35, "Any friends", -1, 47999, 1);

        addHoverSpriteLoaderButton(47261, 660, 150, 35, "Only me", -1,
                47262, 1);
        addHoveredSpriteLoaderButton(47262, 150, 35, 47263, 659);

        // addHoverButton(48020, "b", 1, 150, 35, "Only me", -1, 47999, 1);
        addHoverButton(48021, "b", 1, 150, 35, "General+", -1, 47999, 1);
        addHoverButton(48022, "b", 1, 150, 35, "Captain+", -1, 47999, 1);
        addHoverButton(48023, "b", 1, 150, 35, "Lieutenant+", -1, 47999, 1);
        addHoverButton(48024, "b", 1, 150, 35, "Sergeant+", -1, 47999, 1);
        addHoverButton(48025, "b", 1, 150, 35, "Corporal+", -1, 47999, 1);
        addHoverButton(48026, "b", 1, 150, 35, "Recruit+", -1, 47999, 1);

        addHoverSpriteLoaderButton(47267, 661, 16, 16, "Close", -1,
                47268, 1);
        addHoveredSpriteLoaderButton(47268, 16, 16, 47269, 662);

        addText(47800, "Clan name:", tda, 0, 0xff981f, false, true);
        addText(47801, "Who can enter chat?", tda, 0, 0xff981f, false, true);
        addText(47812, "Who can talk in chat?", tda, 0, 0xff981f, false, true);
        addText(47813, "Who can kick from chat?", tda, 0, 0xff981f, false, true);
        addText(47814, "Name", tda, 0, 0xffffff, true, true);
        addText(47815, "Anyone", tda, 0, 0xffffff, true, true);
        addText(47816, "Anyone", tda, 0, 0xffffff, true, true);
        addText(47817, "Only me", tda, 0, 0xffffff, true, true);
        tab.totalChildren(40);
        tab.child(0, 47251, 180, 15);
        tab.child(1, 47252, 190, 47 + 20);
        tab.child(2, 47253, 190, 47 + 20);
        tab.child(3, 47267, 327, 22);
        tab.child(4, 47268, 327, 22);
        tab.child(5, 48000, 190, 87 + 25);
        tab.child(6, 48001, 190, 87 + 25);
        tab.child(7, 48002, 190, 87 + 25);
        tab.child(8, 48003, 190, 87 + 25);
        tab.child(9, 48004, 190, 87 + 25);
        tab.child(10, 48005, 190, 87 + 25);
        tab.child(11, 48006, 190, 87 + 25);
        tab.child(12, 48007, 190, 87 + 25);
        tab.child(13, 47255, 190, 87 + 25);
        tab.child(14, 47256, 190, 87 + 25);
        tab.child(15, 48010, 190, 128 + 30);
        tab.child(16, 48011, 190, 128 + 30);
        tab.child(17, 48012, 190, 128 + 30);
        tab.child(18, 48013, 190, 128 + 30);
        tab.child(19, 48014, 190, 128 + 30);
        tab.child(20, 48015, 190, 128 + 30);
        tab.child(21, 48016, 190, 128 + 30);
        tab.child(22, 48017, 190, 128 + 30);
        tab.child(23, 47258, 190, 128 + 30);
        //tab.child(24, 47259, 190, 128 + 30);
        // tab.child(25, 48020, 25, 168+35);
        tab.child(24, 48021, 190, 168 + 35);
        tab.child(25, 48022, 190, 168 + 35);
        tab.child(26, 48023, 190, 168 + 35);
        tab.child(27, 48024, 190, 168 + 35);
        tab.child(28, 48025, 190, 168 + 35);
        tab.child(29, 48026, 190, 168 + 35);
        tab.child(30, 47261, 190, 168 + 35);
        tab.child(31, 47262, 190, 168 + 35);
        tab.child(32, 47800, 238, 54 + 20);
        tab.child(33, 47801, 215, 95 + 25);
        tab.child(34, 47812, 215, 136 + 30);
        tab.child(35, 47813, 215, 177 + 35);
        tab.child(36, 47814, 265, 54 + 20 + 12);
        tab.child(37, 47815, 265, 95 + 25 + 12);
        tab.child(38, 47816, 265, 136 + 30 + 12);
        tab.child(39, 47817, 265, 177 + 35 + 12);

    }


    private void dungeonInfo() {
        RSInterface tab = addTabInterface(37500);
        addText(37508, "Party deaths: 0", tda, 2, 0x86B404, true, true);
        addText(37509, "Party kills: 0", tda, 2, 0x86B404, true, true);
        tab.totalChildren(2);
        tab.child(0, 37508, 50, 299);
        tab.child(1, 37509, 41, 315);
    }

    private void formParty() {
        RSInterface tab = addTabInterface(27224);
        addHDSprite(27225, 1013, 1013);
		/*addHoverButton(27326, 141, 141, 16, 16, "Exit", 250, 27227, 5);
		addHoveredButton(27227, 142, 142, 16, 16, 27228);*/

        addHoverButton(27229, 1014, 1014, 180, 32, "Form Party", 250, 27230, 5);
        addHoveredButton(27230, 1015, 1015, 180, 32, 27231);

		/*addHoverButton(27132, 146, 146, 52, 25, "Reset", 250, 27133, 5);
		addHoveredButton(27133, 147, 147, 52, 25, 27134);*/

        addText(27235, "", tda, 1, 0xffffff, true, true);
        addText(27236, "", tda, 1, 0xffffff, true, true);
        addText(27237, "", tda, 1, 0xffffff, true, true);
        addText(27238, "", tda, 1, 0xffffff, true, true);
        addText(27239, "", tda, 1, 0xffffff, true, true);
        addText(27240, "", tda, 2, 0xffffff, false, true);
        addText(27241, "", tda, 2, 0xffffff, true, true);
        addText(27242, "-", tda, 1, 0xffffff, false, true);
        addText(27243, "-", tda, 1, 0xffffff, false, true);
        int[][] data = {{27225, 0, 0},/* { 27326, 171, 1 }, { 27227, 171, 1 },*/
                {27229, 5, 111}, {27230, 5, 111}, /*{ 27132, 132, 230 },
				{ 27133, 132, 230 }, */{27235, 91, 29}, {27236, 91, 44},
                {27237, 91, 59}, {27238, 91, 75}, {27239, 91, 90},
                {27240, 99, 156}, {27241, 103, 183}, {27242, 112, 229},
                {27243, 112, 245}};
        tab.totalChildren(12); // 14, 16
        for (int i = 0; i < data.length; i++) {
            tab.child(i, data[i][0], data[i][1], data[i][2]);
        }
    }

    private void dungParty() {
        RSInterface tab = addTabInterface(26224);
        addHDSprite(26225, 1013, 1013);
        addHoverButton(26226, 1016, 1016, 16, 16, "Exit", 250, 26227, 5);
        addHoveredButton(26227, 1017, 1017, 16, 16, 26228);
        addHoverButton(26229, 1018, 1018, 90, 32, "Leave Party", 250, 26230, 5);
        addHoveredButton(26230, 1019, 1019, 90, 32, 26231);
        addText(26235, "", tda, 1, 0xffffff, true, true);
        addText(26236, "", tda, 1, 0xffffff, true, true);
        addText(26237, "", tda, 1, 0xffffff, true, true);
        addText(26238, "", tda, 1, 0xffffff, true, true);
        addText(26239, "", tda, 1, 0xffffff, true, true);
        addText(26240, "0", tda, 2, 0xffffff, false, true);
        addText(26241, "-", tda, 2, 0xffffff, true, true);
        addText(26242, "-", tda, 1, 0xffffff, false, true);
        addText(26243, "-", tda, 1, 0xffffff, false, true);
        addHoverButton(26244, 1022, 1022, 61, 20, "Change", 250, 26245, 5);
        addHoveredButton(26245, 1023, 1023, 61, 20, 26246);
        addHoverButton(26247, 1022, 1022, 61, 20, "Change", 250, 26248, 5);
        addHoveredButton(26248, 1023, 1023, 61, 20, 26249);
        addHoverButton(26250, 1020, 1020, 90, 32, "Invite player", 250, 26251, 5);
        addHoveredButton(26251, 1021, 1021, 90, 32, 26252);
        int[][] data = {{26225, 0, 0}, {26226, 171, 1}, {26227, 171, 1},
                {26229, 5, 111}, {26230, 5, 111}, /*{ 26232, 132, 230 },
				{ 26233, 132, 230 },*/ {26235, 91, 29}, {26236, 91, 44},
                {26237, 91, 59}, {26238, 91, 75}, {26239, 91, 90},
                {26240, 99, 156}, {26241, 103, 183}, {26242, 112, 229},
                {26243, 112, 245}, {26244, 121, 152}, {26245, 121, 152},
                {26247, 121, 180}, {26248, 121, 180}, {26250, 95, 111},
                {26251, 95, 111}};
        tab.totalChildren(20);
        for (int i = 0; i < data.length; i++) {
            tab.child(i, data[i][0], data[i][1], data[i][2]);
        }
    }

    private void teleportGUI() {
        RSInterface rsInterface = addTabInterface(13999);
        addSpriteLoader(49001, 1190);

        addHoverButtonWSpriteLoader(49002, 652, 17, 17, "Close Window", 0, 49003, 1);
        addHoveredImageWSpriteLoader(49003, 653, 17, 17, 49004);

        //text
        addText(49005, "Necrotic Teleports", tda, 2, 0xFFFFFF);
        addText(49050, "Training", tda, 2, 0xFFFFFF);
        addText(49007, "Skilling", tda, 2, 0xFFFFFF);
        addText(49008, "Dungeons", tda, 2, 0xFFFFFF);
        addText(49009, "Bosses", tda, 2, 0xFFFFFF);
        addText(49010, "Minigames", tda, 2, 0xFFFFFF);
        addText(49011, "Wilderness", tda, 2, 0xFFFFFF);
        //buttons
        addHoverButtonWSpriteLoader(49012, 1191, 112, 23, "Training teleports", 0, 49018, 1);
        addHoveredImageWSpriteLoader(49018, 1192, 112, 23, 36010);

        addHoverButtonWSpriteLoader(49013, 1191, 112, 23, "Skilling teleports", 0, 49019, 1);
        addHoveredImageWSpriteLoader(49019, 1192, 112, 23, 36010);

        addHoverButtonWSpriteLoader(49014, 1191, 112, 23, "Dungeon teleports", 0, 49020, 1);
        addHoveredImageWSpriteLoader(49020, 1192, 112, 23, 36010);

        addHoverButtonWSpriteLoader(49015, 1191, 112, 23, "Boss teleports", 0, 49021, 1);
        addHoveredImageWSpriteLoader(49021, 1192, 112, 23, 36010);

        addHoverButtonWSpriteLoader(49016, 1191, 112, 23, "Minigame teleports", 0, 49022, 1);
        addHoveredImageWSpriteLoader(49022, 1192, 112, 23, 36010);

        addHoverButtonWSpriteLoader(49017, 1191, 112, 23, "Wilderness teleports", 0, 49023, 1);
        addHoveredImageWSpriteLoader(49023, 1192, 112, 23, 36010);

        setChildren(22, rsInterface);
        rsInterface.child(0, 49001, 6, 7); //Background
        rsInterface.child(1, 49002, 460, 18); //Close Button
        rsInterface.child(2, 49003, 460, 18); // Close Button Hover
        rsInterface.child(3, 49012, 13, 47); //training button
        rsInterface.child(4, 49013, 13, 74); //skilling button
        rsInterface.child(5, 49014, 13, 102); //dungeons button
        rsInterface.child(6, 49015, 13, 126); //bosses button
        rsInterface.child(7, 49016, 13, 153); //Minigames button
        rsInterface.child(8, 49017, 13, 178); //wilderness button
        rsInterface.child(9, 49018, 13, 48); //training button hover
        rsInterface.child(10, 49019, 13, 74); //skilling button hover
        rsInterface.child(11, 49020, 13, 102); //dungeons button hover
        rsInterface.child(12, 49021, 13, 126); //bosses button hover
        rsInterface.child(13, 49022, 13, 153); //Minigames button hover
        rsInterface.child(14, 49023, 13, 178); //wilderness button
        rsInterface.child(15, 49005, 225, 22); //title
        rsInterface.child(16, 49050, 48, 53); //training
        rsInterface.child(17, 49007, 48, 80); //skilling
        rsInterface.child(18, 49008, 42, 105); //dungeons
        rsInterface.child(19, 49009, 48, 130); //bosses
        rsInterface.child(20, 49010, 37, 156); //Minigames
        rsInterface.child(21, 49011, 35, 183); //wilderness
    }

    private void slayerBuyInterface() {
        RSInterface rsInterface = addTabInterface(36000);
        addSpriteLoader(36001, 663);

        addHoverButtonWSpriteLoader(36002, 664, 17, 17, "Close Window", 0, 36003, 1);
        addHoveredImageWSpriteLoader(36003, 665, 17, 17, 36004);

        //Tab Buttons

        addHoverButtonWSpriteLoader(36008, 667, 112, 23, "Experience", 0, 36009, 1);
        addHoveredImageWSpriteLoader(36009, 667, 112, 23, 36010);

        addHoverButtonWSpriteLoader(36005, 666, 112, 23, "Items", 0, 36006, 1);
        addHoveredImageWSpriteLoader(36006, 667, 112, 23, 36007);

        addHoverButtonWSpriteLoader(36014, 668, 72, 19, "Buy", 0, 36015, 1);
        addHoveredImageWSpriteLoader(36015, 669, 72, 19, 36016);

        addHoverButtonWSpriteLoader(36017, 668, 72, 19, "Buy", 0, 36018, 1);
        addHoveredImageWSpriteLoader(36018, 669, 72, 19, 36019);

        //Text
        addText(36029, "Slayer Shop", tda, 2, 0xFFFFFF);
        addText(36030, "Current Points:   0", tda, 2, 0xD8D8D8);
        addText(36031, "Slayer XP: 10,000 (Stacks with votes & double exp)", tda, 1, 0xFFFFFF);
        addText(36032, "10 Points", tda, 1, 0xD8D8D8);
        addText(36020, "Experience", tda, 0, 0xffffff, false, true);
        addText(36021, "Items", tda, 0, 0xffffff, false, true);
        addText(36033, "Permanent: Double Slayer XP", tda, 1, 0xFFFFFF);
        addText(36034, "300 Points", tda, 1, 0xD8D8D8);
		/*addText(36037, "Broad Bolts: 250", fonts, 1, 0xFFFFFF);
		addText(36038, "65 Points", fonts, 1, 0xD8D8D8);
		addText(36039, "Broad Arrows: 250", fonts, 1, 0xFFFFFF);
		addText(36040, "35 Points", fonts, 1, 0xD8D8D8);


		addText(36041, "Slayer Helmet", fonts, 1, 0xFFFFFF);
		addText(36042, "375 Points", fonts, 1, 0xD8D8D8);*/
        setChildren(19, rsInterface);
        rsInterface.child(0, 36001, 6, 7); //Background
        rsInterface.child(1, 36002, 483, 10); //Close Button
        rsInterface.child(2, 36003, 483, 10); // Close Button Hover
        rsInterface.child(3, 36005, 138, 41); // Learn Tab
        rsInterface.child(4, 36006, 138, 41); // Learn Tab Hover
        rsInterface.child(5, 36008, 20, 41); // Learn Tab
        rsInterface.child(6, 36009, 20, 41); // Learn Tab Hover
        //	rsInterface.child(5, 36008, 263, 41); // Assignment Tab
        //	rsInterface.child(6, 36009, 263, 41); // Assignment Tab Hover
        //	rsInterface.child(7, 36011, 384, 41); // Co-op Tab
        //	rsInterface.child(8, 36012, 384, 41); // Co-op Tab Hover
        rsInterface.child(7, 36014, 174, 107); // Slayer Buy
        rsInterface.child(8, 36015, 174, 107); // Slayer Buy Hover
        rsInterface.child(9, 36017, 174, 177); // Ring Buy
        rsInterface.child(10, 36018, 174, 177); // Ring Hover
        rsInterface.child(11, 36029, 215, 15); // Title
        rsInterface.child(12, 36030, 22, 283); // Points
        rsInterface.child(13, 36031, 74, 87); // Slayer XP
        rsInterface.child(14, 36032, 74, 102); // 400 Points
        rsInterface.child(15, 36033, 74, 157); //Slay Ring
        rsInterface.child(16, 36034, 74, 172); // 75 Points
        rsInterface.child(17, 36020, 48, 48);
        rsInterface.child(18, 36021, 180, 48);
    }

    private void expRewardInterface() {
        RSInterface Interface = addTabInterface(38000);
        setChildren(37, Interface);
        addSpriteLoader(38001, 670);

        addHoverButtonWSpriteLoader(38002, 672, 21, 21, "Exit", 0, 38003, 1);
        addHoveredImageWSpriteLoader(38003, 673, 21, 21, 38004);

        addSpriteLoader(38005, 671);
        addText(38006, "Choose XP Type...", tda, 1, 0xE3CCCF, true, true);
        addText(38090, "What sort of XP would you like?", tda, 1, 0xE3CCCF, true, true);
        //Line 1

        addButtonWSpriteLoader(38007, 675, "Choose Attack", 46, 44);
        addButtonWSpriteLoader(38010, 675, "Choose Magic", 46, 44);
        addButtonWSpriteLoader(38013, 675, "Choose Mining", 46, 44);
        addButtonWSpriteLoader(38016, 675, "Choose Woodcutting", 46, 44);
        addButtonWSpriteLoader(38019, 675, "Choose Agility", 46, 44);
        addButtonWSpriteLoader(38022, 675, "Choose Fletching", 46, 44);
        addButtonWSpriteLoader(38025, 675, "Choose Thieving", 46, 44);
        addButtonWSpriteLoader(38028, 675, "Choose Strength", 46, 44);
        addButtonWSpriteLoader(38031, 675, "Choose Ranged", 46, 44);
        addButtonWSpriteLoader(38034, 675, "Choose Smithing", 46, 44);
        addButtonWSpriteLoader(38037, 675, "Choose Firemaking", 46, 44);
        addButtonWSpriteLoader(38040, 675, "Choose Herblore", 46, 44);
        addButtonWSpriteLoader(38043, 675, "Choose Slayer", 46, 44);
        addButtonWSpriteLoader(38046, 675, "Choose Invention", 46, 44);
        addButtonWSpriteLoader(38049, 675, "Choose Defence", 46, 44);
        addButtonWSpriteLoader(38052, 675, "Choose Prayer", 46, 44);
        addButtonWSpriteLoader(38043, 675, "Choose Slayer", 46, 44);
        addButtonWSpriteLoader(38055, 675, "Choose Fishing", 46, 44);
        addButtonWSpriteLoader(38058, 675, "Choose Crafting", 46, 44);
        addButtonWSpriteLoader(38061, 675, "Choose Farming", 46, 44);
        addButtonWSpriteLoader(38064, 675, "Choose Hunter", 46, 44);
        addButtonWSpriteLoader(38067, 675, "Choose Summoning", 46, 44);
        addButtonWSpriteLoader(38070, 675, "Choose Constitution", 46, 44);
        addButtonWSpriteLoader(38073, 675, "Choose PVP", 46, 44);
        addButtonWSpriteLoader(38076, 675, "Choose Cooking", 46, 44);
        addButtonWSpriteLoader(38079, 675, "Choose Runecrafting", 46, 44);

        //Other Stuff

        addHoverButtonWSpriteLoader(38082, 676, 127, 21, "Cancel", -1, 38083, 1);
        addHoveredImageWSpriteLoader(38083, 677, 127, 21, 38084);

        addHoverButtonWSpriteLoader(38085, 678, 127, 21, "Confirm", -1, 38086, 1);
        addHoveredImageWSpriteLoader(38086, 679, 127, 21, 38087);

        addText(38088, "Confirm", tda, 1, 0xE3CCCF, false, true);
        addText(38089, "Not right now", tda, 1, 0xE3CCCF, false, true);

        setBounds(38001, 10, 14, 0, Interface);//background
        setBounds(38002, 470, 20, 1, Interface);//Close Button
        setBounds(38003, 470, 20, 2, Interface);//Close Button
        setBounds(38005, 181, 48, 3, Interface);
        setBounds(38006, 255, 52, 4, Interface);
        //Line 1
        setBounds(38007, 37, 80, 5, Interface);

        setBounds(38010, 102, 80, 6, Interface);
        setBounds(38013, 167, 80, 7, Interface);
        setBounds(38016, 232, 80, 8, Interface);
        setBounds(38019, 297, 80, 9, Interface);
        setBounds(38022, 362, 80, 10, Interface);
        setBounds(38025, 427, 80, 11, Interface);
        //Line 2
        setBounds(38028, 37, 138, 12, Interface);
        setBounds(38031, 102, 138, 13, Interface);
        setBounds(38034, 167, 138, 14, Interface);
        setBounds(38037, 232, 138, 15, Interface);
        setBounds(38040, 297, 138, 16, Interface);
        setBounds(38043, 362, 138, 17, Interface);
        setBounds(38046, 427, 138, 18, Interface);
        //Line 3
        setBounds(38049, 37, 196, 19, Interface);
        setBounds(38052, 102, 196, 20, Interface);
        setBounds(38055, 167, 196, 21, Interface);
        setBounds(38058, 232, 196, 22, Interface);
        setBounds(38061, 297, 196, 23, Interface);
        setBounds(38064, 362, 196, 24, Interface);
        setBounds(38067, 427, 196, 25, Interface);
        //Line 4
        setBounds(38070, 37, 254, 26, Interface);
        setBounds(38073, 102, 254, 27, Interface);
        setBounds(38076, 167, 254, 28, Interface);
        setBounds(38079, 232, 254, 29, Interface);
        //Other Stuff
        setBounds(38082, 322, 280, 30, Interface);
        setBounds(38083, 322, 280, 31, Interface);
        setBounds(38085, 322, 250, 32, Interface);
        setBounds(38086, 322, 250, 33, Interface);
        setBounds(38088, 360, 253, 34, Interface);
        setBounds(38089, 350, 283, 35, Interface);
        setBounds(38090, 256, 24, 36, Interface);
    }

    private void barrowsInterface() {
        RSInterface tab = addTabInterface(37200);
        addText(37201, "Barrow Brothers:", tda, 2, 0xff981f, true, true);
        addText(37202, "Dharoks", tda, 1, 0x86B404, true, true);
        addText(37203, "Veracs", tda, 1, 0x86B404, true, true);
        addText(37204, "Ahrims", tda, 1, 0x86B404, true, true);
        addText(37205, "Torags", tda, 1, 0x86B404, true, true);
        addText(37206, "Guthans", tda, 1, 0x86B404, true, true);
        addText(37207, "Karils", tda, 1, 0x86B404, true, true);
        addText(37208, "Killcount: 2", tda, 2, 0xff981f, true, true);
        tab.totalChildren(8);
        tab.child(0, 37201, 447, 206);
        tab.child(1, 37202, 450, 221);
        tab.child(2, 37203, 450, 236);
        tab.child(3, 37204, 450, 251);
        tab.child(4, 37205, 450, 266);
        tab.child(5, 37206, 450, 281);
        tab.child(6, 37207, 450, 296);
        tab.child(7, 37208, 435, 315);
    }


    private void pestControlInterfaces() {
        RSInterface rsinterface = addTabInterface(21100);
        addSpriteLoader(21101, 680);
        addSpriteLoader(21102, 681);
        addSpriteLoader(21103, 682);
        addSpriteLoader(21104, 683);
        addSpriteLoader(21105, 684);
        addSpriteLoader(21106, 685);
        addText(21107, "W", 0xcc00cc, true, true, 52, tda, 1);
        addText(21108, "E", 0x0000FF, true, true, 52, tda, 1);
        addText(21109, "S", 0xffff44, true, true, 52, tda, 1);
        addText(21110, "N", 0xcc0000, true, true, 52, tda, 1);
        addText(21111, "250", 0x99ff33, true, true, 52, tda, 1);
        addText(21112, "250", 0x99ff33, true, true, 52, tda, 1);
        addText(21113, "250", 0x99ff33, true, true, 52, tda, 1);
        addText(21114, "250", 0x99ff33, true, true, 52, tda, 1);
        addText(21115, "***", 0x99ff33, false, true, 52, tda, 1);
        addText(21116, "***", 0x99ff33, false, true, 52, tda, 1);
        addText(21117, "Time Remaining:", 0xffffff, false, true, 52, tda, 0);
        addText(21118, "", 0xffffff, false, true, 52, tda, 0);
        byte byte0 = 18;
        rsinterface.children = new int[byte0];
        rsinterface.childX = new int[byte0];
        rsinterface.childY = new int[byte0];
        setBounds(21101, 355, 26, 0, rsinterface);
        setBounds(21102, 395, 26, 1, rsinterface);
        setBounds(21103, 435, 26, 2, rsinterface);
        setBounds(21104, 475, 26, 3, rsinterface);
        setBounds(21105, 3, 21, 4, rsinterface);
        setBounds(21106, 3, 50, 5, rsinterface);

        setBounds(21107, 367, 57, 6, rsinterface);
        setBounds(21108, 407, 57, 7, rsinterface);
        setBounds(21109, 447, 57, 8, rsinterface);
        setBounds(21110, 487, 57, 9, rsinterface);

        setBounds(21111, 367, 10, 10, rsinterface);
        setBounds(21112, 407, 10, 11, rsinterface);
        setBounds(21113, 447, 10, 12, rsinterface);
        setBounds(21114, 487, 10, 13, rsinterface);

        setBounds(21115, 32, 26, 14, rsinterface);
        setBounds(21116, 32, 56, 15, rsinterface);
        setBounds(21117, 8, 88, 16, rsinterface);
        setBounds(21118, 87, 88, 17, rsinterface);
        RSInterface rsinterface2 = addTabInterface(21005);
        addText(21006, "Next Departure:", 0xCCCBCB, false, true, 52, tda, 1);
        addText(21007, "Players Ready:", 0x5BD230, false, true, 52, tda, 1);
        addText(21008, "(Need 5 to 25 players)", 0xDED36A, false, true, 52, tda, 1);
        addText(21009, "Commendations:", 0x99FFFF, false, true, 52, tda, 1);
        byte0 = 4;
        rsinterface2.children = new int[byte0];
        rsinterface2.childX = new int[byte0];
        rsinterface2.childY = new int[byte0];
        setBounds(21006, 15, 13, 0, rsinterface2);
        setBounds(21007, 15, 33, 1, rsinterface2);
        setBounds(21008, 15, 51, 2, rsinterface2);
        setBounds(21009, 15, 69, 3, rsinterface2);

        RSInterface tab1 = addTabInterface(18730);
        addSpriteLoader(18732, 686);

        addButtonWSpriteLoader(18733, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18734, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18735, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18737, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18740, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18741, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18742, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18745, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18743, 688, "Enchant");

        addButtonWSpriteLoader(18728, 689, "Close Window", 16, 16);

        addText(18729, "", tda, 0, 0xFFA500, false, true);

        // addButton(18776, 0, "PestControl/X", 102, 22, "extra", 1);
        tab1.totalChildren(13);
        tab1.child(0, 18731, 4, 16);
        tab1.child(1, 18732, 4, 16);
        tab1.child(2, 18733, 30, 127);
        tab1.child(3, 18734, 30, 201);
        tab1.child(4, 18735, 184, 127);
        tab1.child(5, 18737, 184, 201);

        tab1.child(6, 18740, 184, 274);
        tab1.child(7, 18741, 338, 127);
        tab1.child(8, 18742, 338, 201);
        tab1.child(9, 18743, 56, 231);
        tab1.child(10, 18728, 480, 20);
        tab1.child(11, 18729, 370, 47);
        tab1.child(12, 18745, 338, 274);

        //tab1.child(17, 18776, 334, 46);
        /* Equipment Tab Void */
        RSInterface tab2 = addTabInterface(18746);

        addSpriteLoader(18747, 690);
        addButtonWSpriteLoader(18748, 691, "Back");

        addButtonWSpriteLoader(18749, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18750, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18751, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18752, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18753, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18754, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18755, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18756, 687, "Purchase", 142, 14);
        addButtonWSpriteLoader(18728, 689, "Close Window", 16, 16);

        // addButton(18776, 0, "PestControl/X", 102, 22, "extra", 1);
        tab2.totalChildren(12);
        tab2.child(0, 18747, 4, 16);
        tab2.child(1, 18748, 56, 231);
        tab2.child(2, 18749, 30, 127);
        tab2.child(3, 18750, 184, 127);
        tab2.child(4, 18751, 340, 127);
        tab2.child(5, 18752, 29, 209);
        tab2.child(6, 18753, 184, 209);
        tab2.child(7, 18754, 339, 209);
        tab2.child(8, 18755, 185, 277);
        tab2.child(9, 18756, 340, 277);
        tab2.child(10, 18728, 480, 20);
        tab2.child(11, 18729, 370, 47);

    }

    private void friendsTabInterface() {
        RSInterface tab = addTabInterface(5065);
        RSInterface list = interfaceCache[5066];
        addText(5067, "Friends List", tda, 1, 0xff9933, true, true);
        addText(5070, "Add Friend", tda, 0, 0xff9933, false, true);
        addText(5071, "Delete Friend", tda, 0, 0xff9933, false, true);
        addSpriteLoader(16126, 692);
        addSpriteLoader(16127, 693);
        addHoverButtonWSpriteLoader(5068, 694, 72, 32, "Add Friend", 201, 5072, 1);
        addHoveredImageWSpriteLoader(5072, 695, 72, 32, 5073);
        addHoverButtonWSpriteLoader(5069, 694, 72, 32, "Delete Friend", 202, 5074, 1);
        addHoveredImageWSpriteLoader(5074, 695, 72, 32, 5075);
        tab.totalChildren(11);
        tab.child(0, 5067, 95, 4);
        tab.child(1, 16127, 0, 25);
        tab.child(2, 16126, 0, 221);
        tab.child(3, 5066, 0, 24);
        tab.child(4, 16126, 0, 22);
        tab.child(5, 5068, 15, 226);
        tab.child(6, 5072, 15, 226);
        tab.child(7, 5069, 103, 226);
        tab.child(8, 5074, 103, 226);
        tab.child(9, 5070, 25, 237);
        tab.child(10, 5071, 106, 237);
        list.height = 196;
        list.width = 174;
        int id = 5092;
        for (int i = 0; id <= 5191 && i <= 99; i++) {
            list.children[i] = id;
            list.childX[i] = 3;
            list.childY[i] = list.childY[i] - 7;

            id++;
        }

        id = 5192;
        for (int i = 100; id <= 5291 && i <= 199; i++) {
            list.children[i] = id;
            list.childX[i] = 131;
            list.childY[i] = list.childY[i] - 7;
            id++;
        }



        int interID = 155000;
        RSInterface list1 = addTabInterface(interID);
        list1.totalChildren(800);
        list1.height = 196;
        list1.width = 174;
        list1.scrollMax = 1650;
        id = interID + 1;
        int y = 1;
        int c = 0;
        for (int j = 0; j < 400; j++) {
            teleportText(id, "", new String[]{"Message", "Remove"}, fonts, 1,
                    0xFFffff, false, true, 100, 15, 10000 + j);
            list1.child(c++, id++, 3, y);
            teleportText(id, "", new String[]{"Message", "Remove"}, fonts, 1,
                    0xFFffff, false, true, 100, 15,  10401 + j);
            list1.child(c++, id++, 131, y);
            y += 15;
        }



    }

    private void ignoreTabInterface() {
        RSInterface tab = addTabInterface(5715);
        RSInterface list = interfaceCache[5716];
        addText(5717, "Ignore List", tda, 1, 0xff9933, true, true);
        addText(5720, "Add Name", tda, 0, 0xff9933, false, true);
        addText(5721, "Delete Name", tda, 0, 0xff9933, false, true);
        addHoverButtonWSpriteLoader(5718, 694, 72, 32, "Add Name", 501, 5722, 1);
        addHoveredImageWSpriteLoader(5722, 695, 72, 32, 5723);
        addHoverButtonWSpriteLoader(5719, 694, 72, 32, "Delete Name", 502, 5724, 1);
        addHoveredImageWSpriteLoader(5724, 695, 72, 32, 5725);
        tab.totalChildren(11);
        tab.child(0, 5717, 95, 4);
        tab.child(1, 16127, 0, 25);
        tab.child(2, 16126, 0, 221);
        tab.child(3, 5716, 0, 24);
        tab.child(4, 16126, 0, 22);
        tab.child(5, 5718, 15, 226);
        tab.child(6, 5722, 15, 226);
        tab.child(7, 5719, 103, 226);
        tab.child(8, 5724, 103, 226);
        tab.child(9, 5720, 27, 237);
        tab.child(10, 5721, 108, 237);
        list.height = 196;
        list.width = 174;
        int id = 5742;
        for (int i = 0; id <= 5841 && i <= 99; i++) {
            list.children[i] = id;
            list.childX[i] = 3;
            list.childY[i] = list.childY[i] - 7;
            id++;
        }
    }

    public static void upgradeInterface(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(62200);
        addSpriteLoader(62201, 1244);
        tab.addText(62211, "How to get Upgrade Tokens?", tda, 1, 0xFFA500, true, true);

        addButtonWSpriteLoader(62202, 1245, "Upgrade X1", 241, 30);
        addButtonWSpriteLoader(62247, 1245, "Upgrade All", 241, 30);

        hoverButton(62202, 1457, 1458, "Upgrade x1", 2, 0xff8624, "Upgrade x1");
        hoverButton(62247, 1457, 1458, "Upgrade All", 2, 0xff8624, "Upgrade All");

        addButtonWSpriteLoader(62241, 1344, "Select 0", 87, 33);
        addButtonWSpriteLoader(62235, 1344, "Select 1", 87, 32);
        addButtonWSpriteLoader(62236, 1344, "Select 2", 87, 32);
        addButtonWSpriteLoader(62240, 1344, "Select 3", 87, 32);
        addButtonWSpriteLoader(62243, 1344, "Select 4", 87, 32);
        addButtonWSpriteLoader(62244, 1345, "Select MAX", 50, 32);

        addConfigButtonWSpriteLoader(62241, 62200, 1539, 1540, 68, 20, "Select", 0, 5, 1355);
        addConfigButtonWSpriteLoader(62235, 62200, 1539, 1540, 68, 20, "Select", 1, 5, 1355);
        addConfigButtonWSpriteLoader(62236, 62200, 1539, 1540, 68, 20, "Select", 2, 5, 1355);
        addConfigButtonWSpriteLoader(62240, 62200, 1539, 1540, 68, 20, "Select", 3, 5, 1355);
        addConfigButtonWSpriteLoader(62243, 62200, 1539, 1540, 68, 20, "Select", 4, 5, 1355);
        addConfigButtonWSpriteLoader(62244, 62200, 1539, 1540, 68, 20, "Select", 5, 5, 1355);

        addConfigButtonWSpriteLoader(62204, 62200, 1539, 1540, 68, 20, "Select", 6, 5, 1355);


        tab.addText(62205, "Select the item you want to upgrade", tda, 1, 0xFFA500, true, true);
        tab.addText(62248, "", tda, 2, 0xFFA500);
        tab.addText(62203, "Product", tda, 2, 0xFFA500, true, true);
        tab.addText(62231, "Tokens required:", tda, 1, 0x3bce76, true, true);
        tab.addText(62234, "Success rate: ", tda, 1, 0x3bce76, true, true);
        tab.addText(62232, "The item must be in your inventory", tda, 0, 0xe6e5e6, true, true);
        tab.addText(62233, "Upgrade tokens are obtained from dissolving items, PvM drops.", tda, 0, 0xe6e5e6, true, true);
        tab.addText(62242, "Tier 1", tda, 1, 0xFFA500);
        tab.addText(62237, "Tier 2", tda, 1, 0xFFA500);
        tab.addText(62238, "Tier 3", tda, 1, 0xFFA500);
        tab.addText(62239, "Tier 4", tda, 1, 0xFFA500);
        tab.addText(62245, "Tier 5", tda, 1, 0xFFA500);
        tab.addText(62246, "Tier 6", tda, 1, 0xFFA500);
        tab.addText(62206, "Lvl 120", tda, 1, 0xFFA500);


        addToItemGroup(62209, 6, 15, 10, 5, true, new String[]{"Select", null, null, null, null});

        addToItemGroup(62210, 3, 10, 17, 3, true, new String[]{null, null, null, null, null}); // try now.

        addButtonWSpriteLoader(62230, 1238, "Close Interface");

        tab.totalChildren(28);
        tab.child(0, 62201, 10, 10); // sprite bg

        tab.child(2, 62203, 373 + 29, 70); // reward if successful

        tab.child(4, 62205, 141 + 18, 66); //select the item you want to upgrade
        tab.child(11, 62232, 141 + 18, 79);//the item must be in ur inventory

        tab.child(1, 62202, 314 + 29, 206);//upgrade x1

        tab.child(6, 62208, 32, 92); // items
        tab.child(7, 62210, 355 + 29, 111); // reward item
        tab.child(9, 62230, 480, 13);

        tab.child(10, 62231, 373 + 29, 170);//dust requred
        tab.child(13, 62234, 373 + 29, 185);//upgrade %

        tab.child(8, 62211, 259, 284);//how to get pixue
        tab.child(12, 62233, 259, 298);//pixue dust is obtained from


        tab.child(14, 62241, 21, 35);// 00 hover
        tab.child(15, 62235, 21 + (68 * 1), 35);//01 hover
        tab.child(16, 62236, 21 + (68 * 2), 35);//02 hover
        tab.child(17, 62240, 21 + (68 * 3), 35);//03 hover
        tab.child(18, 62243, 21 + (68 * 4), 35);// 04 hover
        tab.child(19, 62244, 21 + (68 * 5), 35);// MAX hover
        tab.child(3, 62204, 21 + (68 * 6), 35); // empty

        tab.child(20, 62242, 28, 39);// 00 text
        tab.child(21, 62237, 28 + (68 * 1), 39);// 01 text
        tab.child(22, 62238, 28 + (68 * 2), 39);// 02 text
        tab.child(23, 62239, 28 + (68 * 3), 39);//03 text
        tab.child(24, 62245, 28 + (68 * 4), 39);// 04 text
        tab.child(25, 62246, 28 + (68 * 5), 39);// max text
        tab.child(5, 62206, 28 + (68 * 6), 39);//upgrade x1 text

        tab.child(26, 62247, 343, 237); // upgrade all
        tab.child(27, 62248, 360, 259); // upgrade all text

        RSInterface scrollInterface = addTabInterface(62208);
        scrollInterface.parentID = 62200;
        scrollInterface.width = 273 - 16;
        scrollInterface.height = 170;
        scrollInterface.scrollMax = 1000;
        scrollInterface.totalChildren(1);
        scrollInterface.child(0, 62209, 5, 5);

    }

    private static void equipmentScreenInterface() {
        RSInterface tab = addTabInterface(21172);
        // addSpriteLoader(21173, 857);
        // addBackground(21173, 488, 324, false);
        addSpriteLoader(21173, 696);// TODO change to transparent box?

        //  addText(21181, "asd", fonts, 2, 0xff981f, true, true);

        addCloseButton(21174, 21175, 21176);
        addText(21177, "Equipment Bonuses", fonts, 2, 0xff981f, true, true);

        addText(21178, "Attack bonuses", fonts, 2, 0xFF8900, false, true);
        addText(21179, "Defence bonuses", fonts, 2, 0xFF8900, false, true);
        addText(21180, "Strength bonuses", fonts, 2, 0xFF8900, false, true);
        addText(21181, "Damage modifiers", fonts, 2, 0xFF8900, false, true);

        setChildren(33, tab);
        int index = 0;
        setBounds(21173, 15, 5, index++, tab);
        setBounds(21174, 477, 8, index++, tab);
        setBounds(21175, 477, 8, index++, tab);
        setBounds(21177, 258, 8, index++, tab);

        setBounds(21178, 25, 28, index++, tab);
        setBounds(21179, 25, 110, index++, tab);
        setBounds(21180, 25, 194, index++, tab);
        setBounds(21181, 25, 253, index++, tab);

        for (int i = 0; i < 5; i++) {
            addText(21190 + i, "", fonts, 1, 0xFF9200, false, true);
            setBounds(21190 + i, 29, 42 + (i * 13), index++, tab);
        }

        for (int i = 0; i < 5; i++) {
            addText(21195 + i, "", fonts, 1, 0xFF9200, false, true);
            setBounds(21195 + i, 29, 125 + (i * 13), index++, tab);
        }

        for (int i = 0; i < 4; i++) {
            addText(21204 + i, "", fonts, 1, 0xFF9200, false, true);
            setBounds(21204 + i, 29, 209 + (i * 13), index++, tab);
        }

        for (int i = 0; i < 4; i++) {
            addText(21200 + i, "", fonts, 1, 0xFF9200, false, true);
            setBounds(21200 + i, 29, 267 + (i * 13), index++, tab);
        }


        addCharEquipment(21208);
        setBounds(21208, 190, 200, index++, tab);
        setBounds(1644, 321, 55, index++, tab);

        addText(66106, "Attack Speed:", fonts, 1, 0xFF9200, true, true);// 19153
        tab.child(index++, 66106, 415, 260);
        addText(66107, "", fonts, 1, 0xFF9200, true, true);// 19153
        tab.child(index++, 66107, 415, 270);
        addText(66108, "Melee Maxhit:", fonts, 0, 0xFF9200, true, true);// 19153
        tab.child(index++, 66108, 415, 284);
        addText(66109, "Ranged Maxhit:", fonts, 0, 0xFF9200, true, true);// 19153
        tab.child(index++, 66109, 415, 296);
        addText(66110, "Magic Maxhit:", fonts, 0, 0xFF9200, true, true);// 19153
        tab.child(index++, 66110, 415, 308);

    }

    private void itemsKeptOnDeathInterface() {
        RSInterface rsinterface = addTabInterface(17100);
        RSInterface scroll = addTabInterface(17149);
        scroll.width = 300;
        scroll.height = 183;
        scroll.scrollMax = 220;
        addSpriteLoader(17101, 697);
        addCloseButton(16999, 17000, 17001);
        addText(17103, "Items Kept on Death", tda, 2, 0xff981f, false, false);
        addText(17104, "Items you will keep on death:", tda, 1, 0xff981f, false, false);
        addText(17105, "Items you will lose on death:", tda, 1, 0xff981f, false, false);
        addText(17106, "Info", tda, 1, 0xff981f, false, false);
        addText(17107, "", tda, 1, 16711680, false, false);
        String[] options = {null};
        /*
         * Items on interface
         */

        //Top Row
        for (int top = 17108; top <= 17111; top++) {
            addItemOnInterface(top, top, options);
        }
        //1st row
        for (int top = 17112; top <= 17119; top++) {
            addItemOnInterface(top, top, options);
        }
        //2nd row
        for (int top = 17120; top <= 17127; top++) {
            addItemOnInterface(top, top, options);
        }
        //3rd row
        for (int top = 17128; top <= 17135; top++) {
            addItemOnInterface(top, top, options);
        }
        //4th row
        for (int top = 17135; top <= 17142; top++) {
            addItemOnInterface(top, top, options);
        }
        //5th row
        for (int top = 17150; top <= 17156; top++) {
            addItemOnInterface(top, top, options);
        }

        //6th row (4 items)
        for (int top = 17157; top <= 17160; top++) {
            addItemOnInterface(top, top, options);
        }

        for (int info = 17143; info <= 17148; info++) {
            addText(info, "" + info, tda, 1, 16776960, false, false);
        }

        setChildren(19, rsinterface);
        setBounds(17101, 7, 8, 0, rsinterface);
        setBounds(16999, 478, 14, 1, rsinterface);
        setBounds(17103, 185, 18, 2, rsinterface);
        setBounds(17104, 22, 50, 3, rsinterface);
        setBounds(17105, 22, 110, 4, rsinterface);
        setBounds(17106, 347, 50, 5, rsinterface);
        setBounds(17107, 22, 84, 6, rsinterface);
        setBounds(17149, 23, 132, 7, rsinterface);
        int count = 0;
        for (int info = 17143; info <= 17148; info++) {
            setBounds(info, 350, 74 + (count * 22), 8 + count, rsinterface);
            count++;
        }


        //Positions for  item on interface (items kept on death)
        count = 0;
        for (int topPos = 26; topPos <= 158; topPos += 44) {
            setBounds(17108 + count, topPos, 72, 14 + count, rsinterface);
            count++;
        }
        setChildren(39, scroll);
        count = 0;
        //Positions for item on interface (1st row)
        for (int topPos = 0; topPos <= 264; topPos += 44) {
            setBounds(17112 + count, topPos, 0, 0 + count, scroll);
            count++;
        }
        count = 0;
        //Positions for item on interface (2nd row)
        for (int topPos = 0; topPos <= 264; topPos += 44) {
            setBounds(17120 + count, topPos, 35, 7 + count, scroll);
            count++;
        }
        count = 0;
        //Positions for item on interface (3rd row)
        for (int topPos = 0; topPos <= 264; topPos += 44) {
            setBounds(17128 + count, topPos, 70, 14 + count, scroll);
            count++;
        }
        count = 0;
        //Positions for item on interface (4th row)
        for (int topPos = 0; topPos <= 264; topPos += 44) {
            setBounds(17135 + count, topPos, 105, 21 + count, scroll);
            count++;
        }
        count = 0;
        // Positions for item on interface (5th row)
        for (int topPos = 0; topPos <= 264; topPos += 44) {
            setBounds(17150 + count, topPos, 140, 28 + count, scroll);
            count++;
        }
        count = 0;
        // Positions for item on interface (6th row)
        for (int topPos = 0; topPos <= 132; topPos += 44) {
            setBounds(17157 + count, topPos, 175, 35 + count, scroll);
            count++;
        }
        setBounds(17000, 478, 14, 18, rsinterface);
    }

    private void clanChatTabInterface() {
        RSInterface tab = addInterface(29328);
        addHoverButtonWSpriteLoader(29329, 698, 18, 18, "Join Clan", -1, 29330, 1);
        addHoveredImageWSpriteLoader(29330, 699, 18, 18, 29331);
        addHoverButtonWSpriteLoader(29332, 700, 18, 18, "Leave Clan", -1, 29333, 1);
        addHoveredImageWSpriteLoader(29333, 701, 18, 18, 29334);
        addHoverButtonWSpriteLoader(29335, 702, 18, 18, "Settings", -1, 29336, 1);
        addHoveredImageWSpriteLoader(29336, 703, 18, 18, 29337);

        addText(29340, "Talking in: @whi@Not in chat", 0xff9b00, true, true, tda[0]);

        addSpriteLoader(29339, 1593);
        tab.totalChildren(18);
        int c = 0;

        tab.child(c++, 29339, 0, 0);

        tab.child(c++, 29343, 8, 95);

        tab.child(c++, 29329, 9, 244);
        tab.child(c++, 29330, 9, 244);
        tab.child(c++, 29332, 27, 244);
        tab.child(c++, 29333, 27, 244);
        tab.child(c++, 29335, 45, 244);
        tab.child(c++, 29336, 45, 244);

        tab.child(c++, 29340, 95, 80);

        addTextRight(29849, "Clan Size: (1/600)", tda, 0, 0xFF981F, true);
        tab.child(c++, 29849, 210, 246);

        int id = 29850;
        int y = 8;
        String[] titles = new String[]{"Help", "Market", "Discussion", "No CC/Other"};
        for (int i = 0; i < 4; i++) {

            if (i == 3)
                addText(id, titles[i], tda, 0, 0xFF9900, false, true);
            else
                teleportText(id, titles[i], "Enter Clan", fonts, 0, 0xFF9900, false, true, 172, 17);

            tab.child(c++, id++, 11, y);

            addTextRight(id, "0 ppl", tda, 0, 0xFF981F, true);
            tab.child(c++, id++, 179, y);
            y += 16;
        }


        rebuildClanChatList(false, "", false);
    }

    public void rebuildClanChatList(boolean clickable, String ignore, boolean owner) {
        /* Text area */
        for (int i = 29344; i < 29844; i++) {
            if (clickable && RSInterface.interfaceCache[i].message.length() > 0) {
                addClanChatListTextWithOptions(i, RSInterface.interfaceCache[i].message, ignore, owner, tda, 0, 0xffffff, 200, 11);
            } else {
                addText(i, RSInterface.interfaceCache[i] == null ? "" + i + "" : RSInterface.interfaceCache[i].message, tda, 0, 0xffffff, false, true);
            }
        }
        RSInterface list = addInterface(29343);
        list.totalChildren(500);
        for (int id = 29344, i = 0; id <= 29843 && i <= 499; id++, i++) {
            list.child(id - 29344, id, 5, -1);
            for (int id2 = 29344, i2 = 1; id2 <= 29843 && i2 <= 499; id2++, i2++) {
                list.childY[0] = 4;
                list.childY[i2] = list.childY[i2 - 1] + 14;
            }
        }
        list.height = 146;
        list.width = 173 - 16;
        list.scrollMax = 1405;
    }


    private static void shopInterface() {
        RSInterface shopInventory = interfaceCache[3900];
        shopInventory.inv = new int[5000];
        shopInventory.invStackSizes = new int[5000];
        // shopInventory.transparencyItems = new int[1000];
        shopInventory.drawInfinity = true;
        shopInventory.width = 8;
        shopInventory.height = 500;
        shopInventory.invSpritePadX = 24;
        shopInventory.invSpritePadY = 24;

        RSInterface message = interfaceCache[3903];
        message.centerText = true;

        // The scroll, add the shop inventory to it.
        RSInterface scroll = addTabInterface(29995);
        scroll.height = 220;
        scroll.width = 445;
        scroll.scrollMax = 210;

        scroll.totalChildren(321);
        setBounds(3900, 8, 5, 0, scroll);
        for (int i = 0; i < 320; i++) {
            addText(35613 + i, "-1,0", 0xffff00, false, false, 52, fonts, 0);
        }
        int x = 23;
        int ys = 39;

        for (int xy = 0; xy < 40; xy++) {
            x = 23;
            ys = (39 + xy) + (55 * xy);
            for (int i = 0; i < 8; i++) {
                setBounds(35613 + i + (8 * xy), x, ys, 1 + i + (8 * xy), scroll);
                x += 56;
            }
        }

        // Position the scroll bar interface (containing the container) in the actual
        // shop interface
        setBounds(29995, 26, 65, 75, interfaceCache[3824]);
        RSInterface points = addTabInterface(29996);
        points.totalChildren(1);
        setBounds(3901, 220, 30, 76, interfaceCache[3824]);
        addText(29996, "", fonts, 1, 16750623, false, true);
    }
	/*
	private void shopInterface() {
		RSInterface rsinterface = addTabInterface(3824);
		setChildren(6, rsinterface);
		addSpriteLoader(3825, 736);
		addCloseButton(3902, 19689, 19690);
		setBounds(3825, 6, 8, 0, rsinterface);
		setBounds(3902, 478, 9, 1, rsinterface);
		setBounds(3902, 478, 9, 2, rsinterface);
		setBounds(3900, 26, 44, 3, rsinterface);
		setBounds(3901, 240, 11, 4, rsinterface);
		setBounds(19689, 478, 9, 5, rsinterface);
		rsinterface = interfaceCache[3900];
		rsinterface.inv = new int[50];
		rsinterface.invStackSizes = new int[50];
		rsinterface.drawInfinity = true;
		rsinterface.invSpritePadX = 15;
		rsinterface.width = 10;
		rsinterface.height = 4;
		rsinterface.invSpritePadY = 25;

	}*/

    private void redoSpellBooks() {
        RSInterface newInterface = addTabInterface(11000);
        RSInterface spellButtons = interfaceCache[1151];
        newInterface.totalChildren(7);
        /**
         * Modern spellbook
         */
        spellButtons.scrollMax = 0;
        spellButtons.height = 260;
        spellButtons.width = 190;
        newInterface.child(0, 1151, 5, 34);
        interfaceCache[1164] = interfaceCache[1165];
        interfaceCache[1165] = interfaceCache[1166];
        interfaceCache[1166] = interfaceCache[1168];
        interfaceCache[1167] = interfaceCache[1169];
        interfaceCache[1168] = interfaceCache[1171];
        interfaceCache[1169] = interfaceCache[1172];
        interfaceCache[1170] = interfaceCache[1173];
        interfaceCache[1171] = interfaceCache[1175];
        interfaceCache[1172] = interfaceCache[1176];
        interfaceCache[1173] = interfaceCache[1539];
        interfaceCache[1174] = interfaceCache[1582];
        interfaceCache[1175] = interfaceCache[12037];
        interfaceCache[1176] = interfaceCache[1177];
        interfaceCache[1539] = interfaceCache[1178];
        interfaceCache[1582] = interfaceCache[1179];
        interfaceCache[12037] = interfaceCache[1180];
        interfaceCache[1540] = interfaceCache[1181];
        interfaceCache[1177] = interfaceCache[1182];
        interfaceCache[1178] = interfaceCache[15877];
        interfaceCache[1179] = interfaceCache[1190];
        interfaceCache[1180] = interfaceCache[1191];
        interfaceCache[1541] = interfaceCache[1192];
        interfaceCache[1181] = interfaceCache[1183];
        interfaceCache[1182] = interfaceCache[1184];
        interfaceCache[15877] = interfaceCache[1185];
        interfaceCache[1190] = interfaceCache[1186];
        interfaceCache[1191] = interfaceCache[1542];
        interfaceCache[1192] = interfaceCache[1187];
        interfaceCache[7455] = interfaceCache[1188];
        interfaceCache[1183] = interfaceCache[1543];
        interfaceCache[1184] = interfaceCache[12425];
        interfaceCache[18470] = interfaceCache[1189];
        interfaceCache[1185] = interfaceCache[1592];
        interfaceCache[1186] = interfaceCache[1562];
        interfaceCache[1542] = interfaceCache[1193];
        interfaceCache[1187] = interfaceCache[12435];
        interfaceCache[1188] = interfaceCache[12445];
        interfaceCache[1543] = interfaceCache[6003];
        interfaceCache[12425] = interfaceCache[12455];
        removeSomething(1189);
        removeSomething(1592);
        removeSomething(1562);
        removeSomething(1193);
        removeSomething(12435);
        removeSomething(12445);
        removeSomething(6003);
        removeSomething(12455);
        addHoverButtonWSpriteLoader(11001, 1375, 65, 28, "Select", -1, 11002, 1);
        addTooltip(11002, "Home Teleport\nTeleport to default home location.");
        addText(11008, "Home", tda, 2, ClientConstants.GOLD, true, true);
        newInterface.child(1, 11001, 7, 16 - 10);
        newInterface.child(2, 11002, 1, 39);
        newInterface.child(3, 11008, 7 + 38, 16 + 6 - 8);
        addHoverButtonWSpriteLoader(11004, 1374, 122, 28, "Select", -1, 11005, 1);
        addTooltip(11005, "Teleport Interface\nOpen options of different \nteleports.");
        addText(11009, "Teleports", tda, 2, ClientConstants.GOLD, true, true);

        newInterface.child(4, 11004, 100, 16 - 10);
        newInterface.child(5, 11005, 30, 39);
        newInterface.child(6, 11009, 75 + 63, 16 + 6 - 8);
/*	   addHoverButtonWSpriteLoader(11004, 707, 18, 18, "Select", -1, 11005, 1);
		addTooltip(11005, "Skills Teleport\nOpen options of different \nskilling teleports.");
		newInterface.child(3, 11004, 34, 8);
		newInterface.child(4, 11005, 30, 39);
		addHoverButtonWSpriteLoader(11008, 708, 18, 18, "Select", -1, 11009, 1);
		addTooltip(11009, "Training Teleport\nOpen options of different \ntraining teleports.");
		newInterface.child(5, 11008, 60, 8);
		newInterface.child(6, 11009, 40, 39);
		addHoverButtonWSpriteLoader(11011, 709, 18, 18, "Select", -1, 11012, 1);
		addTooltip(11012, "Dungeon Teleport\nOpen options of different\ndungeon teleports.");
		newInterface.child(7, 11011, 86, 8);
		newInterface.child(8, 11012, 23, 39);
		addHoverButtonWSpriteLoader(11014, 710, 18, 18, "Select", -1, 11015, 1);
		addTooltip(11015, "Boss Teleport\nOpen options of different\nboss teleports.");
		newInterface.child(9, 11014, 112, 8);
		newInterface.child(10, 11015, 23, 39);
		addHoverButtonWSpriteLoader(11017, 711, 18, 18, "Select", -1, 11018, 1);
		addTooltip(11018, "Minigame Teleport\nOpen options of different\nminigame teleports.");
		newInterface.child(11, 11017, 138, 8);
		newInterface.child(12, 11018, 34, 39);
		addHoverButtonWSpriteLoader(11020, 712, 18, 18, "Select", -1, 11021, 1);
		addTooltip(11021, "Wilderness Teleport\nOpen options of different\nwilderness teleports.");
		newInterface.child(13, 11020, 164, 8);
		newInterface.child(14, 11021, 40, 39);*/
        /**
         * Ancient spellbook
         */
        newInterface = addTabInterface(11500);
        spellButtons = interfaceCache[12855];
        newInterface.totalChildren(7);
        spellButtons.scrollMax = 0;
        spellButtons.height = 260;
        spellButtons.width = 190;
        newInterface.child(0, 12855, 0, 40);
        interfaceCache[13035] = interfaceCache[12901];
        interfaceCache[12901] = interfaceCache[12861];
        interfaceCache[12861] = interfaceCache[12963];
        interfaceCache[13045] = interfaceCache[13011];
        interfaceCache[12963] = interfaceCache[12919];
        interfaceCache[13011] = interfaceCache[12881];
        interfaceCache[13053] = interfaceCache[12951];
        interfaceCache[12919] = interfaceCache[12999];
        interfaceCache[12881] = interfaceCache[12911];
        interfaceCache[13061] = interfaceCache[12871];
        interfaceCache[12951] = interfaceCache[12975];
        interfaceCache[12999] = interfaceCache[13023];
        interfaceCache[13069] = interfaceCache[12929];
        interfaceCache[12911] = interfaceCache[12891];
        removeSomething(interfaceCache[12871]);
        removeSomething(interfaceCache[13079]);
        removeSomething(interfaceCache[12975]);
        removeSomething(interfaceCache[13023]);
        removeSomething(interfaceCache[13087]);
        removeSomething(interfaceCache[12929]);
        removeSomething(interfaceCache[12891]);
        removeSomething(interfaceCache[13095]);
        /**
         * Add teleports
         */
        addHoverButtonWSpriteLoader(11001, 1375, 65, 28, "Select", -1, 11002, 1);
        addTooltip(11002, "Home Teleport\nTeleport to default home location.");
        addText(11008, "Home", tda, 2, ClientConstants.GOLD, true, true);
        newInterface.child(1, 11001, 7, 16 - 10);
        newInterface.child(2, 11002, 1, 39);
        newInterface.child(3, 11008, 7 + 35, 16 + 6 - 10);
        addHoverButtonWSpriteLoader(11004, 1374, 122, 28, "Select", -1, 11005, 1);
        addTooltip(11005, "Teleport Interface\nOpen options of different \nteleports.");
        addText(11009, "Teleports", tda, 2, ClientConstants.GOLD, true, true);

        newInterface.child(4, 11004, 100, 16 - 10);
        newInterface.child(5, 11005, 30, 39);
        newInterface.child(6, 11009, 75 + 55, 16 + 6 - 10);
		/*	addHoverButtonWSpriteLoader(11008, 708, 18, 18, "Select", -1, 11009, 1);
		addTooltip(11009, "Training Teleport\nOpen options of different \ntraining teleports.");
		newInterface.child(5, 11008, 60, 16);
		newInterface.child(6, 11009, 40, 39);
		addHoverButtonWSpriteLoader(11011, 709, 18, 18, "Select", -1, 11012, 1);
		addTooltip(11012, "Dungeon Teleport\nOpen options of different\ndungeon teleports.");
		newInterface.child(7, 11011, 86, 16);
		newInterface.child(8, 11012, 23, 39);
		addHoverButtonWSpriteLoader(11014, 710, 18, 18, "Select", -1, 11015, 1);
		addTooltip(11015, "Boss Teleport\nOpen options of different\nboss teleports.");
		newInterface.child(9, 11014, 112, 16);
		newInterface.child(10, 11015, 23, 39);
		addHoverButtonWSpriteLoader(11017, 711, 18, 18, "Select", -1, 11018, 1);
		addTooltip(11018, "Minigame Teleport\nOpen options of different\nminigame teleports.");
		newInterface.child(11, 11017, 138, 16);
		newInterface.child(12, 11018, 34, 39);
		addHoverButtonWSpriteLoader(11020, 712, 18, 18, "Select", -1, 11021, 1);
		addTooltip(11021, "Wilderness Teleport\nOpen options of different\nWilderness teleports.");
		newInterface.child(13, 11020, 164, 16);
		newInterface.child(14, 11021, 40, 39);*/

        /**
         * Lunar
         */
        newInterface = addInterface(11800);
        spellButtons = interfaceCache[29999];
        newInterface.totalChildren(7);
        /**
         * Change spellbook
         */
        spellButtons.scrollMax = 0;
        spellButtons.height = 260;
        spellButtons.width = 190;
        newInterface.child(0, 29999, 0, 45);
        /**
         * Add teleports
         */
        addHoverButtonWSpriteLoader(11001, 1369, 68, 25, "Select", -1, 11002, 1);
        addTooltip(11002, "Home Teleport\nTeleport to default home location.");
        addText(11008, "Home", tda, 2, ClientConstants.GOLD, true, true);
        newInterface.child(1, 11001, 7, 16 - 10);
        newInterface.child(2, 11002, 1, 39);
        newInterface.child(3, 11008, 7 + 35, 16 + 6 - 10);
        addHoverButtonWSpriteLoader(11004, 1369, 140, 25, "Select", -1, 11005, 1);
        addTooltip(11005, "Teleport Interface\nOpen options of different \nteleports.");
        addText(11009, "Teleports", tda, 2, ClientConstants.GOLD, true, true);

        newInterface.child(4, 11004, 100, 16 - 10);
        newInterface.child(5, 11005, 30, 39);
        newInterface.child(6, 11009, 75 + 55, 16 + 6 - 10);
		/*	addHoverButtonWSpriteLoader(11004, 707, 18, 18, "Select", -1, 11005, 1);
		addTooltip(11005, "Skills Teleport\nOpen options of different \nskilling teleports.");
		newInterface.child(3, 11004, 34, 16);
		newInterface.child(4, 11005, 30, 39);
		addHoverButtonWSpriteLoader(11008, 708, 18, 18, "Select", -1, 11009, 1);
		addTooltip(11009, "Training Teleport\nOpen options of different \ntraining teleports.");
		newInterface.child(5, 11008, 60, 16);
		newInterface.child(6, 11009, 40, 39);
		addHoverButtonWSpriteLoader(11011, 709, 18, 18, "Select", -1, 11012, 1);
		addTooltip(11012, "Dungeon Teleport\nOpen options of different\ndungeon teleports.");
		newInterface.child(7, 11011, 86, 16);
		newInterface.child(8, 11012, 23, 39);
		addHoverButtonWSpriteLoader(11014, 710, 18, 18, "Select", -1, 11015, 1);
		addTooltip(11015, "Boss Teleport\nOpen options of different\nboss teleports.");
		newInterface.child(9, 11014, 112, 16);
		newInterface.child(10, 11015, 23, 39);
		addHoverButtonWSpriteLoader(11017, 711, 18, 18, "Select", -1, 11018, 1);
		addTooltip(11018, "Minigame Teleport\nOpen options of different\nminigame teleports.");
		newInterface.child(11, 11017, 138, 16);
		newInterface.child(12, 11018, 34, 39);
		addHoverButtonWSpriteLoader(11020, 712, 18, 18, "Select", -1, 11021, 1);
		addTooltip(11021, "Wilderness Teleport\nOpen options of different\nWilderness teleports.");
		newInterface.child(13, 11020, 164, 16);
		newInterface.child(14, 11021, 40, 39);*/
    }

    private void bankInterface() {
        interfaceCache[5383].message = "     The Bank Of " + Configuration.CLIENT_NAME + "";
        RSInterface rsinterface = addTabInterface(5292);
        setChildren(40, rsinterface);
        setBounds(5383, 170, 15, 1, rsinterface);
        interfaceCache[5385].scrollMax = 1900;
        interfaceCache[5385].height = 206;
        interfaceCache[5385].width = 474;
        interfaceCache[5382].width = 10;
        interfaceCache[5382].invSpritePadX = 12;
        interfaceCache[5382].height = 100;
        interfaceCache[5382].inv = new int[1000];
        interfaceCache[5382].invStackSizes = new int[1000];
        setBounds(5385, 0, 74, 2, rsinterface);
        addSpriteLoader(5293, 713);
        setBounds(5293, 13, 13, 0, rsinterface);

        addHoverButtonWSpriteLoader(5384, 714, 17, 17, "Close Window", 0, 5380, 1);
        addHoveredImageWSpriteLoader(5380, 715, 17, 17, 5379);

        setBounds(5384, 476, 16, 3, rsinterface);
        setBounds(5380, 476, 16, 4, rsinterface);
        //addButton(5294, 3, "Interfaces/BANK/BANK", "Click here to handle Bank PIN");
        addHoverButtonWSpriteLoader(5294, 716, 114, 25, "Manage Bank PIN", -1, 22045, 1);
        addHoveredImageWSpriteLoader(22045, 717, 114, 25, 22046);

        setBounds(5294, 110, 285, 5, rsinterface);
        setBounds(22045, 110, 285, 37, rsinterface);
        //(27651, "CUSTOM", "Equipment", 1, 40, 40, "Show Equipment Screen", 0, 27652, 1);
        //addHoverButton(22000, "Interfaces/BANK/BANK", 5, 35, 25, "Deposit Money-Pouch", 0, 22001, 4);
        //addHoveredButton(22001, "Interfaces/BANK/BANK", 8 ,35, 25, 22002);
        addHoverButtonWSpriteLoader(27009, 718, 35, 25, "Deposit Money-Pouch", -1, 27010, 1);
        addHoveredImageWSpriteLoader(27010, 719, 35, 25, 27011);

        //addBankHover(22000, 4, 22001, 5, 8, "Interfaces/BANK/BANK", 35, 25, 304, 1, "Deposit Money-Pouch", 22002, 7, 6, "Interfaces/BANK/BANK", 22003, "Switch to insert items \nmode", "Switch to swap items \nmode.", 12, 20);
        setBounds(27009, 25, 285, 6, rsinterface);
        setBounds(27010, 25, 285, 7, rsinterface);
        addBankHover(22004, 4, 22005, 13, 15, "Interfaces/BANK/BANK", 35, 25, 117, 1, "Search", 22006, 14, 16, "Interfaces/BANK/BANK", 22007, "Click here to search your \nbank", "Click here to search your \nbank", 12, 20, 720, 721);

        setBounds(22004, 65, 285, 8, rsinterface);
        setBounds(22005, 50, 225, 9, rsinterface);
        addBankHover(22008, 4, 22009, 9, 11, "Interfaces/BANK/BANK", 35, 25, 115, 1, "Withdraw as Note", 22010, 10, 12, "Interfaces/BANK/BANK", 22011, "Switch to note withdrawal \nmode", "Switch to item withdrawal \nmode", 12, 20, 722, 723);
        setBounds(22008, 285, 285, 10, rsinterface);
        setBounds(22009, 225, 225, 11, rsinterface);
        //addBankHover1(22012, 5, 22013, 17, "Interfaces/BANK/BANK", 35, 25, "Deposit carried tems", 22014, 18, "Interfaces/BANK/BANK", 22015, "Empty your backpack into\nyour bank", 0, 20);

        addHoverButtonWSpriteLoader(22012, 724, 35, 25, "Deposit carried items", -1, 22013, 1);
        addHoveredImageWSpriteLoader(22013, 725, 35, 25, 22014);

        setBounds(22012, 375, 285, 12, rsinterface);
        setBounds(22013, 375, 285, 13, rsinterface);
        //addBankHover1(22016, 5, 22017, 19, "Interfaces/BANK/BANK", 35, 25, "Deposit worn items", 22018, 20, "Interfaces/BANK/BANK", 22019, "Empty the items your are\nwearing into your bank", 0, 20);

        addHoverButtonWSpriteLoader(27005, 726, 35, 25, "Deposit worn items", -1, 27006, 1);
        addHoveredImageWSpriteLoader(27006, 727, 35, 25, 27007);

        setBounds(27005, 415, 285, 14, rsinterface);
        setBounds(27006, 415, 285, 15, rsinterface);
        //addBankHover1(22020, 5, 22021, 21, "Interfaces/BANK/BANK", 35, 25, "Deposit Beast of Burden's inventory.", 22022, 22, "Interfaces/BANK/BANK", 22023, "Empty your BoB's inventory\ninto your bank", 0, 20);

        addHoverButtonWSpriteLoader(27023, 728, 35, 25, "Deposit Beast of Burden's inventory", -1, 27024, 1);
        addHoveredImageWSpriteLoader(27024, 729, 35, 25, 27025);

        setBounds(27023, 455, 285, 16, rsinterface);
        setBounds(27024, 455, 285, 17, rsinterface);

        addButtonWSpriteLoader(27014, 730, "Click here to view the full contents of your bank");
        setBounds(27014, 22, 36, 18, rsinterface);
        addButtonWSpriteLoader(27015, 731, "Drag an item here to create a new tab");
        setBounds(27015, 70, 36, 19, rsinterface);
        addButtonWSpriteLoader(27016, 731, "Drag an item here to create a new tab");
        setBounds(27016, 118, 36, 20, rsinterface);
        addButtonWSpriteLoader(27017, 731, "Drag an item here to create a new tab");
        setBounds(27017, 166, 36, 21, rsinterface);
        addButtonWSpriteLoader(27018, 731, "Drag an item here to create a new tab");
        setBounds(27018, 214, 36, 22, rsinterface);
        addButtonWSpriteLoader(27019, 731, "Drag an item here to create a new tab");
        setBounds(27019, 262, 36, 23, rsinterface);
        addButtonWSpriteLoader(27020, 731, "Drag an item here to create a new tab");
        setBounds(27020, 310, 36, 24, rsinterface);
        addButtonWSpriteLoader(27021, 731, "Drag an item here to create a new tab");
        setBounds(27021, 358, 36, 25, rsinterface);
        addButtonWSpriteLoader(27022, 731, "Drag an item here to create a new tab");

        setBounds(27022, 406, 36, 26, rsinterface);
        addText(22033, "134", tda, 0, 0xb4965a, true, false);
        setBounds(22033, 473, 42, 27, rsinterface);
        addText(22034, "496", tda, 0, 0xb4965a, true, false);
        setBounds(22034, 473, 57, 28, rsinterface);
        addBankItem(22035, Boolean.valueOf(false));
        setBounds(22035, 77, 39, 29, rsinterface);
        addBankItem(22036, Boolean.valueOf(false));
        setBounds(22036, 125, 39, 30, rsinterface);
        addBankItem(22037, Boolean.valueOf(false));
        setBounds(22037, 173, 39, 31, rsinterface);
        addBankItem(22038, Boolean.valueOf(false));
        setBounds(22038, 221, 39, 32, rsinterface);
        addBankItem(22039, Boolean.valueOf(false));
        setBounds(22039, 269, 39, 33, rsinterface);
        addBankItem(22040, Boolean.valueOf(false));
        setBounds(22040, 317, 39, 34, rsinterface);
        addBankItem(22041, Boolean.valueOf(false));
        setBounds(22041, 365, 39, 35, rsinterface);
        addBankItem(22042, Boolean.valueOf(false));
        setBounds(22042, 413, 39, 36, rsinterface);
        addBankHover(21000, 4, 21001, 431, 432, 35, 25, 304, 1, "Swap Withdraw Mode", 21002, 174, 173, 21003, "Switch to insert items \nmode", "Switch to swap items \nmode.", 12, 20);
        setBounds(21000, 240, 285, 37, rsinterface);
        addText(27000, "0", 0xff981f, false, true, 52, tda, 1);
        addText(27001, "0", 0xff981f, false, true, 52, tda, 1);
        addText(27002, "0", 0xff981f, false, true, 52, tda, 1);

        addBankHover(78395, 4, 78396, 1453, 1454, "", 35, 25,
                111, 1, "Toggle placeholders", 78397, 14, 16, "", 78398,
                "Toggle placeholders", "Toggle placeholders", 12, 20, 1453, 1455);
        setBounds(78395, 329, 285, 38, rsinterface);
        setBounds(78396, 329, 285, 39, rsinterface);
    }

    private void playerPanelv3() { //Keith interface
        //final int PAGE_AMOUNT = 12;
        final int[] PLAYERS_PER_PAGE = {14, 20, 8, 30, 10, 7};//, 6, 6, 6, 6, 6, 6};

        RSInterface tab = addInterface(44000);
        addInterface(44006);
        addSprite(44001, 1198);
        addHoverButton(44002, 1199, 16, 16, "Close", 0, 44003, 1);
        addHoveredButton(44003, 1200, 16, 16, 44004);
        addText(44005, "", tda, 2, 0xece383, true, true);
        int x = 10, y = 10;
        tab.totalChildren(8);
        tab.child(0, 44001, x, y);
        tab.child(1, 44002, 463 + x, 4 + y);
        tab.child(2, 44003, 463 + x, 4 + y);
        tab.child(3, 44005, 243 + x, 5 + y);
        tab.child(4, 44006, 15 + x, 29 + y);
        tab.child(5, 44085, 152 + x, 220 + y);
        tab.child(6, 44100, 152 + x, 29 + y);
        tab.child(7, 44007, 15 + x, 29 + y);

        RSInterface pageList = addInterface(44007);
        pageList.totalChildren(PLAYERS_PER_PAGE.length * 3);
        for (int i = 0; i < PLAYERS_PER_PAGE.length; i++) {
            addHoverButton(44008 + i, 1219 + i, 123, 39, "Filter", 0, 44028 + i, 1);
            addHoveredButton(44028 + i, 1225, 29, 39, 44084);
            //addText(44048 + i, "", tda, 3, 0xff7000, false, true);
            pageList.child(i, 44008 + i, 0, i * 40);
            pageList.child(i + (PLAYERS_PER_PAGE.length), 44028 + i, 0, i * 40);
            pageList.child(i + (PLAYERS_PER_PAGE.length * 2), 44048 + i, 4, 7 + i * 40);
        }
        pageList.width = 107;
        pageList.height = 260;
        pageList.scrollMax = (PLAYERS_PER_PAGE.length * 40) <= 260 ? 270 : (PLAYERS_PER_PAGE.length * 40);

        for (int i = 0; i < PLAYERS_PER_PAGE.length; i++) {
            int b = (i * 200);
            RSInterface page = addInterface(44100 + b);

            page.totalChildren(3 * PLAYERS_PER_PAGE[i]);
            page.child(0, 44101 + b, 158, 17);
            int yy = 0;
            for (int j = 0; j < PLAYERS_PER_PAGE[i]; j++) {
                addHoverButton(44102 + j + b, 1226, 316, 38, "Select", 0, 44152 + j + b, 1);
                addHoveredButton(44152 + j + b, 1227, 316, 38, 44099);
                addText(44202 + j + b, "", tda, 1, 0xff981f, false, true);
                page.child(j, 44102 + j + b, 0, yy);
                page.child(j + (PLAYERS_PER_PAGE[i]), 44152 + j + b, 0, yy);
                page.child(j + (PLAYERS_PER_PAGE[i] * 2), 44202 + j + b, 10, 10 + yy);
                yy += 39;
            }
            page.width = 300;
            page.height = 180;
            page.scrollMax = yy > 200 ? yy : 200;
        }

        RSInterface info = addInterface(44085);
        addText(44086, "", tda, 2, 0xece383, true, true);
        addText(44087, "", tda, 0, 0xf3f0f0, true, true);
        addText(44088, "", tda, 0, 0xf3f0f0, true, true);
        addClickableText(44090, "", "Select", tda, 0, 0xff981f, false, true, 80);
        addClickableText(44091, "", "Select", tda, 0, 0xff981f, false, true, 80);
        addClickableText(44092, "", "Select", tda, 0, 0xff981f, false, true, 80);
        addClickableText(44098, "", "Select", tda, 0, 0xff981f, false, true, 80);
        addText(44089, "", tda, 2, 0xece383, true, true);
        addHoverButton(44093, 1203, 127, 24, "", 0, 44094, 1);
        addHoveredButton(44094, 1204, 127, 24, 44095);
        addText(44096, "", tda, 1, 65280, true, true);
        info.totalChildren(11);
        info.child(0, 44086, 112, 3);
        info.child(1, 44087, 112, 19);
        info.child(2, 44088, 112, 31);
        info.child(3, 44090, 230, 19);
        info.child(4, 44091, 230, 31);
        info.child(5, 44092, 230, 43);
        info.child(6, 44098, 230, 55);
        info.child(7, 44089, 271, 3);
        info.child(8, 44093, 50, 45);
        info.child(9, 44094, 50, 45);
        info.child(10, 44096, 113, 49);
    }

    private void summoningBoBInterface() {
        RSInterface rsi = addTabInterface(2700);
        addSpriteLoader(2701, 737);
        addButtonWSpriteLoader(2735, 738, "Withdraw all items");
        addCloseButton(2734, 2736, 2737);
        rsi.totalChildren(36);
        /**
         * Bob storage, starting with first row.
         */
        for (int i = 2702; i < 2710; i++)
            addBobStorage(i);
        /**
         * Second row
         */
        for (int i = 2710; i < 2716; i++)
            addBobStorage(i);
        /**
         * Third row
         */
        for (int i = 2716; i < 2722; i++)
            addBobStorage(i);

        /**
         * Fourth row
         */
        for (int i = 2722; i < 2728; i++)
            addBobStorage(i);

        /**
         * Fifth row
         */
        for (int i = 2728; i < 2734; i++)
            addBobStorage(i);

        rsi.child(0, 2701, 90, 14);
        rsi.child(1, 2735, 424, 290);
        rsi.child(2, 2703, 431, 23);
        rsi.child(3, 2704, 427, 285);
        /**
         * Bob storage first row
         */
        rsi.child(4, 2702, 105, 56);
        rsi.child(5, 2705, 160, 56);
        rsi.child(6, 2706, 215, 56);
        rsi.child(7, 2707, 270, 56);
        rsi.child(8, 2708, 320, 56);
        rsi.child(9, 2709, 375, 56);

        /**
         * Bob storage second row
         */
        rsi.child(10, 2710, 105, 110);
        rsi.child(11, 2711, 160, 110);
        rsi.child(12, 2712, 215, 110);
        rsi.child(13, 2713, 270, 110);
        rsi.child(14, 2714, 320, 110);
        rsi.child(15, 2715, 375, 110);

        /**
         * Bob storage third row
         */
        rsi.child(16, 2716, 105, 163);
        rsi.child(17, 2717, 160, 163);
        rsi.child(18, 2718, 215, 163);
        rsi.child(19, 2719, 270, 163);
        rsi.child(20, 2720, 320, 163);
        rsi.child(21, 2721, 375, 163);

        /**
         * Bob storage fourth row
         */
        rsi.child(22, 2722, 105, 216);
        rsi.child(23, 2723, 160, 216);
        rsi.child(24, 2724, 215, 216);
        rsi.child(25, 2725, 270, 216);
        rsi.child(26, 2726, 320, 216);
        rsi.child(27, 2727, 375, 216);

        /**
         * Bob storage fifth row
         */
        rsi.child(28, 2728, 105, 269);
        rsi.child(29, 2729, 160, 269);
        rsi.child(30, 2730, 215, 269);
        rsi.child(31, 2731, 270, 269);
        rsi.child(32, 2732, 320, 269);
        rsi.child(33, 2733, 375, 269);

        //Close
        rsi.child(34, 2734, 429, 22);
        rsi.child(35, 2736, 429, 22);
    }

    /*private static void teleportWidget(TextDrawingArea[] tda) {
        RSInterface widget = addInterface(29322);

        addSprite(49113, 1197);

        addHoverButtonWSpriteLoader(49114, 1198, 16, 16, "Close", -1, 49115, 1);
        addHoveredImageWSpriteLoader(49115, 1199, 16, 16, 49116);

        addHoverButtonWSpriteLoader(49117, 1206, 90, 25, "Teleport", -1, 49118, 1);
        addHoveredImageWSpriteLoader(49118, 1207, 90, 25, 49119);

        addSpriteLoader(49120, 1200);
        addText(49121, "1,000,000 Coins", tda, 3, 0xFF981F, true, true);

        addSpriteLoader(49122, 1201);
        addText(49123, "Dangerous", tda, 3, 0xFF981F, true, true);

        addSpriteLoader(49124, 1202);
        addText(49125, "Inside Wilderness", tda, 3, 0xFF981F, true, true);

        addText(49126, "King Black Dragon", tda, 2, 0xFF981F, true, true);

        addText(49127, "Teleport", tda, 3, 0xFF981F, true, true);

        //addNpcToWidget(43006);
        widget.totalChildren(14);
        widget.child(0, 49113, 56, 5);
        widget.child(1, 49114, 443, 9);
        widget.child(2, 49115, 443, 9);
        widget.child(3, 49117, 125, 280);
        widget.child(4, 49118, 125, 280);
        widget.child(5, 49120, 80, 65);
        widget.child(6, 49121, 170, 65);
        widget.child(7, 49122, 85, 90);
        widget.child(8, 49123, 152, 90);
        widget.child(9, 49124, 83, 115);
        widget.child(10, 49125, 175, 115);
        widget.child(11, 49126, 140, 35);
        widget.child(12, 49127, 170, 283);
        widget.child(13, 49128, 185, 77);


        RSInterface scrollTab = addTabInterface(49128);
        scrollTab.width = 244;
        scrollTab.height = 230;
        scrollTab.scrollMax = 550;

        scrollTab.totalChildren(50);

        for (int i = 0; i < 50; i += 2) {
            addButtonWSpriteLoader(49129 + i, 1204, "Select", 140, 22);
            scrollTab.child(i, 49129 + i, 105, i / 2 * 22);
            addText(49129 + i + 1, "King Black Dragon", tda, 1, 0xFF981F, true, true);
            scrollTab.child(i + 1, 49129 + i + 1, 175, 3 + (i / 2 * 22));
        }

    }*/
    private void summoningTabInterface() {
        RSInterface rsi = addTabInterface(54017);
        addText(54019, "Summoning Familiar", tda, 2, 16750623, true, true);
        addSpriteLoader(54020, 739);

        addFamiliarHead(54021, 75, 50, 875);
        //	addPet(54021);
        addSpriteLoader(54027, 740);
        addText(54028, "", tda, 2, 0xFF981F, true, false);

        addHoverButtonWSpriteLoader(54029, 741, 38, 38, "Withdraw BoB", -1, 54030, 1);
        addHoveredImageWSpriteLoader(54030, 742, 38, 38, 54031);

        addHoverButtonWSpriteLoader(54032, 743, 38, 38, "Renew familiar", -1, 54033, 1);
        addHoveredImageWSpriteLoader(54033, 744, 38, 38, 54034);

        addHoverButtonWSpriteLoader(54035, 745, 38, 38, "Call familiar", -1, 54036, 1);
        addHoveredImageWSpriteLoader(54036, 746, 38, 38, 54037);

        addHoverButtonWSpriteLoader(54038, 747, 38, 38, "Dismiss familiar", -1, 54039, 1);
        addHoveredImageWSpriteLoader(54039, 748, 38, 38, 54040);

        addSpriteLoader(54041, 749);
        addSpriteLoader(54042, 750);

        addText(54043, "", tda, 0, 0xB9B855, false, true);
        addSpriteLoader(54044, 751);
        addText(54045, "", tda, 0, 0xB9B855, false, true);
        setChildren(18, rsi);
        setBounds(54020, 10, 32, 0, rsi);
        setBounds(54021, 63, 60, 1, rsi);
        setBounds(54027, 12, 144, 2, rsi);
        setBounds(54028, 93, 146, 3, rsi);
        setBounds(54029, 23, 168, 4, rsi);
        setBounds(54030, 23, 168, 5, rsi);
        setBounds(54032, 75, 168, 6, rsi);
        setBounds(54033, 75, 168, 7, rsi);
        setBounds(54035, 23, 213, 8, rsi);
        setBounds(54036, 23, 213, 9, rsi);
        setBounds(54038, 75, 213, 10, rsi);
        setBounds(54039, 75, 213, 11, rsi);
        setBounds(54041, 130, 168, 12, rsi);
        setBounds(54042, 153, 170, 13, rsi);
        setBounds(54043, 148, 198, 14, rsi);
        setBounds(54044, 149, 213, 15, rsi);
        setBounds(54045, 145, 241, 16, rsi);
        setBounds(54019, 91, 9, 17, rsi);
    }

    private void achievementsInterface() {
        RSInterface tab = addTabInterface(37000);
        RSInterface scroll = addTabInterface(36999);
        addText(37001, "Achievements", tda, 2, 16750623, false, true);
        addSpriteLoader(37002, 650);
        addSpriteLoader(37003, 651);
        addSpriteLoader(37004, 650);
        tab.totalChildren(5);
        tab.child(0, 37001, 5, 3);
        tab.child(1, 37002, 0, 22);
        tab.child(2, 37003, 0, 25);
        tab.child(3, 37004, 0, 249);
        tab.child(4, 36999, 0, 25);

        scroll.totalChildren(112);
        scroll.width = 174;
        scroll.height = 224;
        scroll.scrollMax = 1790;


        /** TEXT **/
        int k = 0;
        int y = 25;
        for (int i = 37005; i < 37116; i++) {
            scroll.child(k, i, 6, y);
            y += 16;
            k++;
            if (i == 37035 || i == 37068 || i == 37102 || i >= 37112) {
                addText(i, "", tda, 0, 0xFF8900, false, true);
            } else {
                addClickableText(i, "", "Select", tda, 0, 0xFF8900, 130, 13);
            }
        }
        addText(30777, "Easy Tasks", tda, 1, 0xFF9900, false, true);
        scroll.child(k, 30777, 7, 6);
        addText(37036, "Medium Tasks", tda, 1, 0xFF9900, false, true);
        addText(37069, "Hard Tasks", tda, 1, 0xFF9900, false, true);
        addText(37103, "Elite Tasks", tda, 1, 0xFF9900, false, true);
    }

    private void loyaltyShop() {
        RSInterface rsi = addTabInterface(43000);
        rsi.totalChildren(74);
        addSpriteLoader(43001, 752);
        addText(43002, "Loyalty Titles", tda, 2, 16750623, false, true);
        addCloseButton(43003, 43121, 43122);
        rsi.child(0, 43001, 10, 20);
        rsi.child(1, 43002, 210, 22);
        rsi.child(2, 43003, 466, 19);

        /** BUY BUTTONS **/
        int id = 43004, child = 3;
        for (int i = 0; i < 12; i++) {
            int y = i == 1 ? 62 : i == 2 ? 83 : i == 3 ? 105 : i == 4 ? 127 : i == 5 ? 149 : i == 6 ? 171 : i == 7 ? 193 : i == 8 ? 215 : i == 9 ? 237 : i == 10 ? 259 : i == 11 ? 281 : -1;

            if (id != 43004) {
                addHoverButtonWSpriteLoader(id, 754, 32, 17, "Buy", -1, id + 1, 1);
                addHoveredImageWSpriteLoader(id + 1, 753, 32, 17, id + 2);
            } else {
                removeSomething(id);
                removeSomething(id + 1);
            }

            addText(id + 3, "", tda, 0, 0xB9B855, false, true);

            rsi.child(child, id, 213, y);
            rsi.child(child + 1, id + 1, 213, y);
            rsi.child(child + 2, id + 3, 158, y + 5);
            child += 3;
            id += 4;
        }
        for (int i = 1; i < 12; i++) {
            int y = i == 1 ? 62 : i == 2 ? 83 : i == 3 ? 105 : i == 4 ? 127 : i == 5 ? 149 : i == 6 ? 171 : i == 7 ? 193 : i == 8 ? 215 : i == 9 ? 237 : i == 10 ? 259 : i == 11 ? 281 : -1;


            addHoverButtonWSpriteLoader(id, 754, 32, 17, "Buy", -1, id + 1, 1);
            addHoveredImageWSpriteLoader(id + 1, 753, 32, 17, id + 2);

            addText(id + 3, "", tda, 0, 0xB9B855, false, true);

            rsi.child(child, id, 428, y);
            rsi.child(child + 1, id + 1, 428, y);
            rsi.child(child + 2, id + 3, 373, y + 5);

            child += 3;
            id += 4;
        }
        addText(43120, "Your Loyalty Points: 0", tda, 0, 0xB9B855, false, true);
        rsi.child(72, 43120, 195, 43);
        rsi.child(73, 43121, 466, 19);
    }

    private void emoteTab() {
        RSInterface tab = addTabInterface(147);
        RSInterface scroll = addTabInterface(148);
        tab.totalChildren(1);
        tab.child(0, 148, 0, 1);
        addButton(168, 968, "Yes", 41, 47);
        addButton(169, 969, "No", 41, 47);
        addButton(164, 970, "Bow", 41, 47);
        addButton(165, 971, "Angry", 41, 47);
        addButton(162, 972, "Think", 41, 47);
        addButton(163, 973, "Wave", 41, 47);
        addButton(13370, 974, "Shrug", 41, 47);
        addButton(171, 975, "Cheer", 41, 47);
        addButton(167, 976, "Beckon", 41, 47);
        addButton(170, 977, "Laugh", 41, 47);
        addButton(13366, 978, "Jump for Joy", 41, 47);
        addButton(13368, 1000, "Yawn", 41, 47);
        addButton(166, 979, "Dance", 41, 47);
        addButton(13363, 980, "Jig", 41, 47);
        addButton(13364, 981, "Spin", 41, 47);
        addButton(13365, 982, "Headbang", 41, 47);
        addButton(161, 983, "Cry", 41, 47);
        addButton(11100, 984, "Blow kiss", 41, 47);
        addButton(13362, 985, "Panic", 41, 47);
        addButton(13367, 986, "Raspberry", 41, 47);
        addButton(172, 987, "Clap", 41, 47);
        addButton(13369, 988, "Salute", 41, 47);
        addButton(13383, 989, "Goblin Bow", 41, 47);
        addButton(13384, 990, "Goblin Salute", 41, 47);
        addButton(667, 991, "Glass Box", 41, 47);
        addButton(6503, 992, "Climb Rope", 41, 47);
        addButton(6506, 993, "Lean On Air", 41, 47);
        addButton(666, 994, "Glass Wall", 41, 47);
        addButton(18464, 995, "Zombie Walk", 41, 47);
        addButton(18465, 996, "Zombie Dance", 41, 47);
        addButton(15166, 997, "Scared", 41, 47);
        addButton(18686, 998, "Rabbit Hop", 41, 47);
        addButton(154, 999, "Skillcape Emote", 41, 47);
        scroll.totalChildren(33);
        scroll.child(0, 168, 10, 7);
        scroll.child(1, 169, 54, 7);
        scroll.child(2, 164, 98, 14);
        scroll.child(3, 165, 137, 7);
        scroll.child(4, 162, 9, 56);
        scroll.child(5, 163, 48, 56);
        scroll.child(6, 13370, 95, 56);
        scroll.child(7, 171, 137, 56);
        scroll.child(8, 167, 7, 105);
        scroll.child(9, 170, 51, 105);
        scroll.child(10, 13366, 95, 104);
        scroll.child(11, 13368, 139, 105);
        scroll.child(12, 166, 6, 154);
        scroll.child(13, 13363, 50, 154);
        scroll.child(14, 13364, 90, 154);
        scroll.child(15, 13365, 135, 154);
        scroll.child(16, 161, 8, 204);
        scroll.child(17, 11100, 51, 203);
        scroll.child(18, 13362, 99, 204);
        scroll.child(19, 13367, 137, 203);
        scroll.child(20, 172, 10, 253);
        scroll.child(21, 13369, 53, 253);
        scroll.child(22, 13383, 88, 258);
        scroll.child(23, 13384, 138, 252);
        scroll.child(24, 667, 2, 303);
        scroll.child(25, 6503, 49, 302);
        scroll.child(26, 6506, 93, 302);
        scroll.child(27, 666, 137, 302);
        scroll.child(28, 18464, 9, 352);
        scroll.child(29, 18465, 50, 352);
        scroll.child(30, 15166, 94, 354);
        scroll.child(31, 18686, 141, 353);
        scroll.child(32, 154, 5, 401);
        scroll.width = 173;
        scroll.height = 258;
        scroll.scrollMax = 460;
    }

    public static void opacityInterface() {
        RSInterface rsi = addTabInterface(35555);
        setChildren(1, rsi);
        addRectangle(35556, 128, 0x000000, true, 30, 34);
        setBounds(35556, 0, 0, 0, rsi);
    }

    private void levelUpInterfaces() {
        RSInterface attack = interfaceCache[6247];
        RSInterface defence = interfaceCache[6253];
        RSInterface str = interfaceCache[6206];
        RSInterface hits = interfaceCache[6216];
        RSInterface rng = interfaceCache[4443];
        RSInterface pray = interfaceCache[6242];
        RSInterface mage = interfaceCache[6211];
        RSInterface cook = interfaceCache[6226];
        RSInterface wood = interfaceCache[4272];
        RSInterface flet = interfaceCache[6231];
        RSInterface fish = interfaceCache[6258];
        RSInterface fire = interfaceCache[4282];
        RSInterface craf = interfaceCache[6263];
        RSInterface smit = interfaceCache[6221];
        RSInterface mine = interfaceCache[4416];
        RSInterface herb = interfaceCache[6237];
        RSInterface agil = interfaceCache[4277];
        RSInterface thie = interfaceCache[4261];
        RSInterface slay = interfaceCache[12122];
        RSInterface farm = addTabInterface(5267);
        RSInterface rune = interfaceCache[4267];
        RSInterface cons = interfaceCache[7267];
        RSInterface hunt = addTabInterface(8267);
        RSInterface summ = addTabInterface(9267);
        RSInterface dung = addTabInterface(10267);
        addSkillChatSprite(29578, 0);
        addSkillChatSprite(29579, 1);
        addSkillChatSprite(29580, 2);
        addSkillChatSprite(29581, 3);
        addSkillChatSprite(29582, 4);
        addSkillChatSprite(29583, 5);
        addSkillChatSprite(29584, 6);
        addSkillChatSprite(29585, 7);
        addSkillChatSprite(29586, 8);
        addSkillChatSprite(29587, 9);
        addSkillChatSprite(29588, 10);
        addSkillChatSprite(29589, 11);
        addSkillChatSprite(29590, 12);
        addSkillChatSprite(29591, 13);
        addSkillChatSprite(29592, 14);
        addSkillChatSprite(29593, 15);
        addSkillChatSprite(29594, 16);
        addSkillChatSprite(29595, 17);
        addSkillChatSprite(29596, 18);
        addSkillChatSprite(11897, 19);
        addSkillChatSprite(29598, 20);
        addSkillChatSprite(29599, 21);
        addSkillChatSprite(29600, 22);
        addSkillChatSprite(29601, 23);
        addSkillChatSprite(29602, 24);
        setChildren(4, attack);
        setBounds(29578, 20, 30, 0, attack);
        setBounds(4268, 80, 15, 1, attack);
        setBounds(4269, 80, 45, 2, attack);
        setBounds(358, 95, 75, 3, attack);
        setChildren(4, defence);
        setBounds(29579, 20, 30, 0, defence);
        setBounds(4268, 80, 15, 1, defence);
        setBounds(4269, 80, 45, 2, defence);
        setBounds(358, 95, 75, 3, defence);
        setChildren(4, str);
        setBounds(29580, 20, 30, 0, str);
        setBounds(4268, 80, 15, 1, str);
        setBounds(4269, 80, 45, 2, str);
        setBounds(358, 95, 75, 3, str);
        setChildren(4, hits);
        setBounds(29581, 20, 30, 0, hits);
        setBounds(4268, 80, 15, 1, hits);
        setBounds(4269, 80, 45, 2, hits);
        setBounds(358, 95, 75, 3, hits);
        setChildren(4, rng);
        setBounds(29582, 20, 30, 0, rng);
        setBounds(4268, 80, 15, 1, rng);
        setBounds(4269, 80, 45, 2, rng);
        setBounds(358, 95, 75, 3, rng);
        setChildren(4, pray);
        setBounds(29583, 20, 30, 0, pray);
        setBounds(4268, 80, 15, 1, pray);
        setBounds(4269, 80, 45, 2, pray);
        setBounds(358, 95, 75, 3, pray);
        setChildren(4, mage);
        setBounds(29584, 20, 30, 0, mage);
        setBounds(4268, 80, 15, 1, mage);
        setBounds(4269, 80, 45, 2, mage);
        setBounds(358, 95, 75, 3, mage);
        setChildren(4, cook);
        setBounds(29585, 20, 30, 0, cook);
        setBounds(4268, 80, 15, 1, cook);
        setBounds(4269, 80, 45, 2, cook);
        setBounds(358, 95, 75, 3, cook);
        setChildren(4, wood);
        setBounds(29586, 20, 30, 0, wood);
        setBounds(4268, 80, 15, 1, wood);
        setBounds(4269, 80, 45, 2, wood);
        setBounds(358, 95, 75, 3, wood);
        setChildren(4, flet);
        setBounds(29587, 20, 30, 0, flet);
        setBounds(4268, 80, 15, 1, flet);
        setBounds(4269, 80, 45, 2, flet);
        setBounds(358, 95, 75, 3, flet);
        setChildren(4, fish);
        setBounds(29588, 20, 30, 0, fish);
        setBounds(4268, 80, 15, 1, fish);
        setBounds(4269, 80, 45, 2, fish);
        setBounds(358, 95, 75, 3, fish);
        setChildren(4, fire);
        setBounds(29589, 20, 30, 0, fire);
        setBounds(4268, 80, 15, 1, fire);
        setBounds(4269, 80, 45, 2, fire);
        setBounds(358, 95, 75, 3, fire);
        setChildren(4, craf);
        setBounds(29590, 20, 30, 0, craf);
        setBounds(4268, 80, 15, 1, craf);
        setBounds(4269, 80, 45, 2, craf);
        setBounds(358, 95, 75, 3, craf);
        setChildren(4, smit);
        setBounds(29591, 20, 30, 0, smit);
        setBounds(4268, 80, 15, 1, smit);
        setBounds(4269, 80, 45, 2, smit);
        setBounds(358, 95, 75, 3, smit);
        setChildren(4, mine);
        setBounds(29592, 20, 30, 0, mine);
        setBounds(4268, 80, 15, 1, mine);
        setBounds(4269, 80, 45, 2, mine);
        setBounds(358, 95, 75, 3, mine);
        setChildren(4, herb);
        setBounds(29593, 20, 30, 0, herb);
        setBounds(4268, 80, 15, 1, herb);
        setBounds(4269, 80, 45, 2, herb);
        setBounds(358, 95, 75, 3, herb);
        setChildren(4, agil);
        setBounds(29594, 20, 30, 0, agil);
        setBounds(4268, 80, 15, 1, agil);
        setBounds(4269, 80, 45, 2, agil);
        setBounds(358, 95, 75, 3, agil);
        setChildren(4, thie);
        setBounds(29595, 20, 30, 0, thie);
        setBounds(4268, 80, 15, 1, thie);
        setBounds(4269, 80, 45, 2, thie);
        setBounds(358, 95, 75, 3, thie);
        setChildren(4, slay);
        setBounds(29596, 20, 30, 0, slay);
        setBounds(4268, 80, 15, 1, slay);
        setBounds(4269, 80, 45, 2, slay);
        setBounds(358, 95, 75, 3, slay);
        setChildren(4, farm);
        setBounds(11897, 20, 30, 0, farm);
        setBounds(4268, 80, 15, 1, farm);
        setBounds(4269, 80, 45, 2, farm);
        setBounds(358, 95, 75, 3, farm);
        setChildren(4, rune);
        setBounds(29598, 20, 30, 0, rune);
        setBounds(4268, 80, 15, 1, rune);
        setBounds(4269, 80, 45, 2, rune);
        setBounds(358, 95, 75, 3, rune);
        setChildren(3, cons);
        setBounds(4268, 80, 15, 0, cons);
        setBounds(4269, 80, 45, 1, cons);
        setBounds(358, 95, 75, 2, cons);
        setChildren(4, hunt);
        setBounds(29600, 20, 30, 0, hunt);
        setBounds(4268, 80, 15, 1, hunt);
        setBounds(4269, 80, 45, 2, hunt);
        setBounds(358, 95, 75, 3, hunt);
        setChildren(4, summ);
        setBounds(29601, 20, 30, 0, summ);
        setBounds(4268, 80, 15, 1, summ);
        setBounds(4269, 80, 45, 2, summ);
        setBounds(358, 95, 75, 3, summ);
        setChildren(4, dung);
        setBounds(29602, 20, 30, 0, dung);
        setBounds(4268, 80, 15, 1, dung);
        setBounds(4269, 80, 45, 2, dung);
        setBounds(358, 95, 75, 3, dung);
    }

    private void optionTab() {
        RSInterface tab = addTabInterface(904);
        RSInterface energy = interfaceCache[149];
        energy.disabledColor = 0xff9933;
        addSprite(905, 300);
        addSprite(907, 301);
        addSprite(909, 302);
        addSprite(951, 303);
        addSprite(953, 304);
        addSprite(955, 305);
        addSprite(947, 335);
        addSprite(949, 306);
        addSprite(950, 496);
        // run button here
        addConfigButton(152, 904, 307, 308, 40, 40, "Toggle-run", 1, 5, 173);

        addConfigButton(25841, 904, 307, 308, 40, 40, "Settings", 1, 5, 175);

        addConfigButton(906, 904, 309, 313, 32, 16, "Brightness - Dark", 1, 5, 166);
        addConfigButton(908, 904, 310, 314, 32, 16, "Brightness - Normal", 2, 5, 166);
        addConfigButton(910, 904, 311, 315, 32, 16, "Brightness - Bright", 3, 5, 166);
        addConfigButton(912, 904, 312, 316, 32, 16, "Brightness - Very Bright", 4, 5, 166);

        addConfigButton(930, 904, 317, 322, 26, 16, "Music Off", 4, 5, 168);
        addConfigButton(931, 904, 318, 323, 26, 16, "Music Level-1", 3, 5, 168);
        addConfigButton(932, 904, 319, 324, 26, 16, "Music Level-2", 2, 5, 168);
        addConfigButton(933, 904, 320, 325, 26, 16, "Music Level-3", 1, 5, 168);
        addConfigButton(934, 904, 321, 326, 24, 16, "Music Level-4", 0, 5, 168);

        addConfigButton(941, 904, 317, 322, 26, 16, "Sound Effects Off", 4, 5, 169);
        addConfigButton(942, 904, 318, 323, 26, 16, "Sound Effects Level-1", 3, 5, 169);
        addConfigButton(943, 904, 319, 324, 26, 16, "Sound Effects Level-2", 2, 5, 169);
        addConfigButton(944, 904, 320, 325, 26, 16, "Sound Effects Level-3", 1, 5, 169);
        addConfigButton(945, 904, 321, 326, 24, 16, "Sound Effects Level-4", 0, 5, 169);
        addTooltip(25843, "More client options,\\nincluding fullscreen");

        addConfigButton(913, 904, 307, 308, 40, 40, "Toggle-Mouse Buttons", 0, 5, 170);
        addConfigButton(915, 904, 307, 308, 40, 40, "Toggle-Chat Effects", 0, 5, 171);
        addConfigButton(957, 904, 307, 308, 40, 40, "Toggle-Split Private Chat", 1, 5, 287);
        addConfigButton(12464, 904, 307, 308, 40, 40, "Toggle-Accept Aid", 0, 5, 427);
        tab.totalChildren(30);
        int x = 0;
        int y = 2;
        tab.child(0, 905, 13 + x, 10 + y);
        tab.child(1, 906, 48 + x, 18 + y);
        tab.child(2, 908, 80 + x, 18 + y);
        tab.child(3, 910, 112 + x, 18 + y);
        tab.child(4, 912, 144 + x, 18 + y);
        tab.child(5, 907, 14 + x, 55 + y);
        tab.child(6, 930, 49 + x, 61 + y);
        tab.child(7, 931, 75 + x, 61 + y);
        tab.child(8, 932, 101 + x, 61 + y);
        tab.child(9, 933, 127 + x, 61 + y);
        tab.child(10, 934, 151 + x, 61 + y);
        tab.child(11, 909, 13 + x, 99 + y);
        tab.child(12, 941, 49 + x, 104 + y);
        tab.child(13, 942, 75 + x, 104 + y);
        tab.child(14, 943, 101 + x, 104 + y);
        tab.child(15, 944, 127 + x, 104 + y);
        tab.child(16, 945, 151 + x, 104 + y);
        tab.child(17, 913, 15, 153);
        tab.child(18, 955, 19, 159);
        tab.child(19, 915, 75, 153);
        tab.child(20, 953, 79, 160);
        tab.child(21, 957, 135, 153);
        tab.child(22, 951, 139, 159);
        tab.child(23, 12464, 15, 208);
        tab.child(24, 949, 20, 213);
        tab.child(25, 152, 75, 208);
        tab.child(26, 947, 87, 212);
        tab.child(27, 149, 80, 231);
        tab.child(28, 25841, 135, 208);
        tab.child(29, 950, 140, 213);
    }

    private void settingsInterface() {
        RSInterface tab = addTabInterface(24000);
        addSprite(24001, 571, 571, -1, -1);
        addText(24002, "Graphics Settings", tda, 2, 0xeb981f, true, true);

        addText(24027, "If the game runs slowly on your computer, try reducing these settings.", tda, 1, 0xeb981f, true, true);
        addText(24028, "Low Detail", tda, 0, 0xeb981f, false, true);
        addText(24029, "High Detail", tda, 0, 0xeb981f, false, true);
        addText(24031, "Fixed", tda, 0, 0xeb981f, false, true);
        addText(24032, "Resizable", tda, 0, 0xeb981f, false, true);
        addText(24033, "Fullscreen", tda, 0, 0xeb981f, false, true);
        addText(24030, "Adjust additional settings below.", tda, 1, 0xeb981f, true, true);

        String[] text = {"New GameFrame", "Names Above Head", "HP Above Head",
                "New Hitpoints", "New Hitmarks", "New Function Keys",
                "x10 Damage", "Fog", "Tweening", "Roofs Off", "Toggle FOV",
                "Push notifications", "Unused", "Unused", "Unused",};
        for (int i = 0; i < text.length; i++) {
            addText(24003 + i, text[i], tda, 0, 0xeb981f, true, true);
        }
        addHoverButton(24034, 580, 16, 16, "Close", -1, 24035, 3);
        addHoveredButton(24035, 581, 16, 16, 24036);

        addHoverButton(24018, 486, 50, 39, "Set to Fixed Mode", -1, 24019, 1);
        addHoveredButton(24019, 487, 50, 39, 24020);
        addHoverButton(24021, 488, 50, 39, "Set to Resizable Mode", -1, 24022, 1);
        addHoveredButton(24022, 489, 50, 39, 24023);
        addHoverButton(24024, 490, 50, 39, "Set to Fullscreen Mode", -1, 24025, 1);
        addHoveredButton(24025, 491, 50, 39, 24026);

        addCheckmarkHover(24159, 4, 24160, 576, 577, 57, 35, SETTING_CONFIGS[15], 1, "Low Detail", 24161, 577, 577, 24162, "", "", 12, 20);
        addCheckmarkHover(24163, 4, 24164, 578, 579, 57, 35, SETTING_CONFIGS[16], 1, "High Detail", 24165, 579, 579, 24166, "", "", 12, 20);

        addCheckmarkHover(24099, 4, 24100, 572, 573, 104, 26, SETTING_CONFIGS[0], 1, "Change Gameframe", 24101, 574, 575, 24102, "", "", 12, 20);
        addCheckmarkHover(24103, 4, 24104, 572, 573, 104, 26, SETTING_CONFIGS[1], 1, "Names above Head", 24105, 574, 575, 24106, "", "", 12, 20);
        addCheckmarkHover(24107, 4, 24108, 572, 573, 104, 26, SETTING_CONFIGS[2], 1, "Hitpoints above Head", 24109, 574, 575, 24110, "", "", 12, 20);
        addCheckmarkHover(24111, 4, 24112, 572, 573, 104, 26, SETTING_CONFIGS[3], 1, "New Hitpoints", 24113, 574, 575, 24114, "", "", 12, 20);
        addCheckmarkHover(24115, 4, 24116, 572, 573, 104, 26, SETTING_CONFIGS[4], 1, "New Hitmarks", 24117, 574, 575, 24118, "", "", 12, 20);
        addCheckmarkHover(24119, 4, 24120, 572, 573, 104, 26, SETTING_CONFIGS[5], 1, "New Function Keys", 24121, 574, 575, 24122, "", "", 12, 20);
        addCheckmarkHover(24123, 4, 24124, 572, 573, 104, 26, SETTING_CONFIGS[6], 1, "x10 Hitpoints", 24125, 574, 575, 24126, "", "", 12, 20);
        addCheckmarkHover(24127, 4, 24128, 572, 573, 104, 26, SETTING_CONFIGS[7], 1, "Fog", 24129, 574, 575, 24130, "", "", 12, 20);
        addCheckmarkHover(24131, 4, 24132, 572, 573, 104, 26, SETTING_CONFIGS[8], 1, "Tweening", 24133, 574, 575, 24134, "", "", 12, 20);
        addCheckmarkHover(24135, 4, 24136, 572, 573, 104, 26, SETTING_CONFIGS[9], 1, "Toggle Roofs", 24137, 574, 575, 24138, "", "", 12, 20);
        addCheckmarkHover(24139, 4, 24140, 572, 573, 104, 26, SETTING_CONFIGS[10], 1, "Toggle FOV", 24141, 574, 575, 24142, "", "", 12, 20);
        addCheckmarkHover(24143, 4, 24144, 572, 573, 104, 26, SETTING_CONFIGS[11], 1, "Push notifications", 24145, 574, 575, 24146, "", "", 12, 20);
        addCheckmarkHover(24147, 4, 24148, 572, 573, 104, 26, SETTING_CONFIGS[12], 1, "Option 2", 24149, 574, 575, 24150, "", "", 12, 20);
        addCheckmarkHover(24151, 4, 24152, 572, 573, 104, 26, SETTING_CONFIGS[13], 1, "Option 3", 24153, 574, 575, 24154, "", "", 12, 20);
        addCheckmarkHover(24155, 4, 24156, 572, 573, 104, 26, SETTING_CONFIGS[14], 1, "Option 4", 24157, 574, 575, 24158, "", "", 12, 20);

        int x = 97;
        int y = 143;
        int configX = 45;
        int configY = 135;
        int seperationX = 160;
        int seperationY = 40;
        tab.totalChildren(66);
        tab.child(0, 24001, 0, 0); // background sprite
        tab.child(1, 24099, configX, configY); // gameframe
        tab.child(2, 24100, configX, configY); // gameframe
        tab.child(3, 24103, configX + seperationX, configY); // name above head
        tab.child(4, 24104, configX + seperationX, configY); // name above head
        tab.child(5, 24107, configX + seperationX * 2, configY); // hitpoints
        // above
        // head
        tab.child(6, 24108, configX + seperationX * 2, configY); // hitpoints
        // above
        // head
        tab.child(7, 24111, configX, configY + seperationY); // new hitpoints
        tab.child(8, 24112, configX, configY + seperationY); // new hitpoints
        tab.child(9, 24115, configX + seperationX, configY + seperationY); // new
        // hitmarks
        tab.child(10, 24116, configX + seperationX, configY + seperationY); // new
        // hitmarks
        tab.child(11, 24119, configX + seperationX * 2, configY + seperationY); // new
        // function
        // keys
        tab.child(12, 24120, configX + seperationX * 2, configY + seperationY); // new
        // function
        // keys
        tab.child(13, 24123, configX, configY + seperationY * 2); // x10
        // hitpoints
        tab.child(14, 24124, configX, configY + seperationY * 2); // x10
        // hitpoints
        tab.child(15, 24127, configX + seperationX, configY + seperationY * 2); // fog
        tab.child(16, 24128, configX + seperationX, configY + seperationY * 2); // fog
        tab.child(17, 24131, configX + seperationX * 2, configY + seperationY * 2); // tweening
        tab.child(18, 24132, configX + seperationX * 2, configY + seperationY * 2); // tweening
        tab.child(19, 24135, configX, configY + seperationY * 3); // toggle
        // roofs
        tab.child(20, 24136, configX, configY + seperationY * 3); // toggle
        // roofs
        tab.child(21, 24139, configX + seperationX, configY + seperationY * 3); // toggle
        // fov
        tab.child(22, 24140, configX + seperationX, configY + seperationY * 3); // toggle
        // fov
        tab.child(23, 24143, configX + seperationX * 2, configY + seperationY * 3); // option
        // 1
        tab.child(24, 24144, configX + seperationX * 2, configY + seperationY * 3); // option
        // 1
        tab.child(25, 24147, configX, configY + seperationY * 4); // option 2
        tab.child(26, 24148, configX, configY + seperationY * 4); // option 2
        tab.child(27, 24151, configX + seperationX, configY + seperationY * 4); // option
        // 3
        tab.child(28, 24152, configX + seperationX, configY + seperationY * 4); // option
        // 3
        tab.child(29, 24155, configX + seperationX * 2, configY + seperationY * 4); // option
        // 4
        tab.child(30, 24156, configX + seperationX * 2, configY + seperationY * 4); // option
        // 4
        tab.child(31, 24018, 76, 62);
        tab.child(32, 24019, 76, 62);
        tab.child(33, 24021, 166, 62);
        tab.child(34, 24022, 166, 62);
        tab.child(35, 24024, 261, 62);
        tab.child(36, 24025, 261, 62);
        tab.child(37, 24159, 340, 64); // low detail
        tab.child(38, 24160, 340, 64); // low detail
        tab.child(39, 24163, 410, 64); // high detail
        tab.child(40, 24164, 410, 64); // high detail
        tab.child(41, 24002, 258, 4); // title
        tab.child(42, 24030, 258, 118); // title2
        tab.child(43, 24028, 341, 102); // Low detail text
        tab.child(44, 24029, 411, 102); // High detail text
        tab.child(45, 24031, 87, 102); // Fixed text
        tab.child(46, 24032, 167, 102); // Resizable text
        tab.child(47, 24033, 262, 102); // Fullscreen text
        tab.child(48, 24027, 258, 24); // text below title
        tab.child(49, 24034, 492, 3); // Close button
        tab.child(50, 24035, 492, 3); // Close hover

        for (int i = 0; i < text.length; i++) {
            tab.child(i + 51, i + 24003, x + i % 3 * seperationX, y + i / 3 * seperationY);
        }
    }

    private void lootingBag() {
        RSInterface rsi = addInterface(16545);
        RSInterface.addText(16546, "Add to bag", 0xff9b00, true, true, -1, tda, 2);
        RSInterface.addSprite(16547, 612, 612, -1, -1);
        addHoverButton(5384, 55, 17, 17, "Close Window", 250, 5380, 3);
        addHoveredButton(5380, 90, 17, 17, 5379);

        rsi.totalChildren(5);

        setBounds(16546, 88, 5, 0, rsi);
        setBounds(16547, 7, 25, 1, rsi);
        setBounds(5384, 155, 5, 2, rsi);
        setBounds(5380, 155, 5, 3, rsi);
        setBounds(3213, 0, 22, 4, rsi);
    }

    private void bountyInterface() {
        RSInterface rsi = addInterface(42020);
        RSInterface.addTransparentSprite(42021, 611, 611, 125);
        RSInterface.addText(42022, "None", 0xD40000, true, true, -1, tda, 0);
        RSInterface.addText(42023, "5", 0xD40000, false, true, -1, tda, 0);
        RSInterface.addText(42024, "100%", 0xD40000, false, true, -1, tda, 0);
        RSInterface.addText(42025, "Target:", Integer.MAX_VALUE, true, true, -1, tda, 0);
        RSInterface.addText(42026, "Wilderness Level:", Integer.MAX_VALUE, false, true, -1, tda, 0);
        RSInterface.addText(42027, "Target Percentage:", Integer.MAX_VALUE, false, true, -1, tda, 0);
        rsi.totalChildren(7);
        setBounds(42021, 310, 5, 0, rsi);
        setBounds(42022, 430, 9, 1, rsi);
        setBounds(42023, 430, 25, 2, rsi);
        setBounds(42024, 430, 40, 3, rsi);
        setBounds(42025, 334, 10, 4, rsi);
        setBounds(42026, 316, 25, 5, rsi);
        setBounds(42027, 316, 39, 6, rsi);
    }

    private void sidebarInterfaces() {
        Sidebar0a(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block", 42, 75, 127, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        Sidebar0a(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block", 43, 75, 124, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        Sidebar0a(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        Sidebar0a(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block", 39, 75, 121, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        Sidebar0a(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block", 40, 75, 124, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        Sidebar0a(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 42, 75, 125, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        Sidebar0a(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block", 41, 75, 123, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        Sidebar0a(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        Sidebar0b(776, 779, "Reap", "Chop", "Jab", "Block", 42, 75, 126, 75, 46, 128, 125, 128, 122, 103, 122, 50, 40, 103, 40, 50, tda);
        Sidebar0c(425, 428, 7474, "Pound", "Pummel", "Block", 39, 75, 121, 75, 42, 128, 40, 103, 40, 50, 122, 50, tda);
        Sidebar0c(1749, 1752, 7524, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
        Sidebar0c(1764, 1767, 7549, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
        Sidebar0c(4446, 4449, 7649, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
        Sidebar0c(5855, 5857, 7749, "Punch", "Kick", "Block", 40, 75, 129, 75, 42, 128, 40, 50, 122, 50, 40, 103, tda);
        Sidebar0c(6103, 6132, 6117, "Bash", "Pound", "Block", 43, 75, 124, 75, 42, 128, 40, 103, 40, 50, 122, 50, tda);
        Sidebar0c(8460, 8463, 8493, "Jab", "Swipe", "Fend", 46, 75, 124, 75, 43, 128, 40, 103, 40, 50, 122, 50, tda);
        Sidebar0c(12290, 12293, 12323, "Flick", "Lash", "Deflect", 44, 75, 127, 75, 36, 128, 40, 50, 40, 103, 122, 50, tda);
        Sidebar0d(328, 331, "Bash", "Pound", "Focus", 42, 66, 39, 101, 41, 136, 40, 120, 40, 50, 40, 85, tda);

        RSInterface rsi = addInterface(19300);
        /* textSize(ID, wid, Size); */
        textSize(3983, tda, 0);
        /* addToggleButton(id, sprite, config, width, height, wid); */
        addToggleButton(150, 64, 172, 150, 44, "Auto Retaliate");

        rsi.totalChildren(2, 2, 2);
        rsi.child(0, 3983, 52, 25); // combat level
        rsi.child(1, 150, 21, 153); // auto retaliate

        rsi = interfaceCache[3983];
        rsi.centerText = true;
        rsi.disabledColor = 0xff981f;
    }

    private void prayerTabInterface() {
        RSInterface prayerMenu = addTabInterface(5608);
        int index = 0;
        int prayIndex = 0;
        int firstRowXPos = 10;
        int firstRowYPos = 50;
        int secondRowXPos = 10;
        int secondRowYPos = 86;
        int thirdRowXPos = 10;
        int thirdRowYPos = 122;
        int fourthRowXPos = 10;
        int fourthRowYPos = 159;
        int fifthRowXPos = 10;
        int fifthRowYPos = 86;
        int sixthRowXPos = 1;
        int sixthRowYPos = 52;
        addText(687, "", 0xff981f, false, true, -1, tda, 1);
        addSpriteLoader(25112, 870);
        addPrayerWithTooltip(25000, 0, 83, 0, prayIndex, 25052,
                "Activate @lre@Thick Skin");
        prayIndex++;
        addPrayerWithTooltip(25002, 0, 84, 3, prayIndex, 25054,
                "Activate @lre@Burst of Strength");
        prayIndex++;
        addPrayerWithTooltip(25004, 0, 85, 6, prayIndex, 25056,
                "Activate @lre@Clarity of Thought");
        prayIndex++;
        addPrayerWithTooltip(25006, 0, 601, 7, prayIndex, 25058,
                "Activate @lre@Sharp Eye");
        prayIndex++;
        addPrayerWithTooltip(25008, 0, 602, 8, prayIndex, 25060,
                "Activate @lre@Mystic Will");
        prayIndex++;
        addPrayerWithTooltip(25010, 0, 86, 9, prayIndex, 25062,
                "Activate @lre@Rock Skin");
        prayIndex++;
        addPrayerWithTooltip(25012, 0, 87, 12, prayIndex, 25064,
                "Activate @lre@Superhuman Strength");
        prayIndex++;
        addPrayerWithTooltip(25014, 0, 88, 15, prayIndex, 25066,
                "Activate @lre@Improved Reflexes");
        prayIndex++;
        addPrayerWithTooltip(25016, 0, 89, 18, prayIndex, 25068,
                "Activate @lre@Rapid Restore");
        prayIndex++;
        addPrayerWithTooltip(25018, 0, 90, 21, prayIndex, 25070,
                "Activate @lre@Rapid Heal");
        prayIndex++;
        addPrayerWithTooltip(25020, 0, 91, 24, prayIndex, 25072,
                "Activate @lre@Protect Item");
        prayIndex++;
        addPrayerWithTooltip(25022, 0, 603, 25, prayIndex, 25074,
                "Activate @lre@Hawk Eye");
        prayIndex++;
        addPrayerWithTooltip(25024, 0, 604, 26, prayIndex, 25076,
                "Activate @lre@Mystic Lore");
        prayIndex++;
        addPrayerWithTooltip(25026, 0, 92, 27, prayIndex, 25078,
                "Activate @lre@Steel Skin");
        prayIndex++;
        addPrayerWithTooltip(25028, 0, 93, 30, prayIndex, 25080,
                "Activate @lre@Ultimate Strength");
        prayIndex++;
        addPrayerWithTooltip(25030, 0, 94, 33, prayIndex, 25082,
                "Activate @lre@Incredible Reflexes");
        prayIndex++;
        addPrayerWithTooltip(25032, 0, 95, 36, prayIndex, 25084,
                "Activate @lre@Protect from Magic");
        prayIndex++;
        addPrayerWithTooltip(25034, 0, 96, 39, prayIndex, 25086,
                "Activate @lre@Protect from Missles");
        prayIndex++;
        addPrayerWithTooltip(25036, 0, 97, 42, prayIndex, 25088,
                "Activate @lre@Protect from Melee");
        prayIndex++;
        addPrayerWithTooltip(25038, 0, 605, 43, prayIndex, 25090,
                "Activate @lre@Eagle Eye");
        prayIndex++;
        addPrayerWithTooltip(25040, 0, 606, 44, prayIndex, 25092,
                "Activate @lre@Mystic Might");
        prayIndex++;
        addPrayerWithTooltip(25042, 0, 98, 45, prayIndex, 25094,
                "Activate @lre@Retribution");
        prayIndex++;
        addPrayerWithTooltip(25044, 0, 99, 48, prayIndex, 25096,
                "Activate @lre@Redemption");
        prayIndex++;
        addPrayerWithTooltip(25046, 0, 100, 51, prayIndex, 25098,
                "Activate @lre@Smite");
        prayIndex++;
        addPrayerWithTooltip(25048, 0, 607, 59, prayIndex, 25100,
                "Activate @lre@Chivalry");
        prayIndex++;
        addPrayerWithTooltip(25050, 0, 608, 69, prayIndex, 25102,
                "Activate @lre@Piety");
        prayIndex++;
        addPrayerWithTooltip(25104, 0, 609, 79, prayIndex, 25106, "Activate @lre@Rigour");
        prayIndex++;
        addPrayerWithTooltip(25108, 0, 610, 89, prayIndex, 25110, "Activate @lre@Augury");
        prayIndex++;
        addTooltip(25052, "Level 01\nThick Skin\nIncreases your Defence by 5%");
        addTooltip(25054,
                "Level 04\nBurst of Strength\nIncreases your Strength by 5%");
        addTooltip(25056,
                "Level 07\nClarity of Thought\nIncreases your Attack by 5%");
        addTooltip(25058, "Level 08\nSharp Eye\nIncreases your Ranged by 5%");
        addTooltip(25060, "Level 09\nMystic Will\nIncreases your Magic by 5%");
        addTooltip(25062, "Level 10\nRock Skin\nIncreases your Defence by 10%");
        addTooltip(25064,
                "Level 13\nSuperhuman Strength\nIncreases your Strength by 10%");
        addTooltip(25066,
                "Level 16\nImproved Reflexes\nIncreases your Attack by 10%");
        addTooltip(
                25068,
                "Level 19\nRapid Restore\n2x restore rate for all stats\nexcept Hitpoints, Summoning\nand Prayer");
        addTooltip(25070,
                "Level 22\nRapid Heal\n2x restore rate for the\nHitpoints stat");
        addTooltip(25072,
                "Level 25\nProtect Item\nKeep 1 extra item if you die");
        addTooltip(25074, "Level 26\nHawk Eye\nIncreases your Ranged by 10%");
        addTooltip(25076, "Level 27\nMystic Lore\nIncreases your Magic by 10%");
        addTooltip(25078, "Level 28\nSteel Skin\nIncreases your Defence by 15%");
        addTooltip(25080,
                "Level 31\nUltimate Strength\nIncreases your Strength by 15%");
        addTooltip(25082,
                "Level 34\nIncredible Reflexes\nIncreases your Attack by 15%");
        addTooltip(25084,
                "Level 37\nProtect from Magic\nProtection from magical attacks");
        addTooltip(25086,
                "Level 40\nProtect from Missles\nProtection from ranged attacks");
        addTooltip(25088,
                "Level 43\nProtect from Melee\nProtection from melee attacks");
        addTooltip(25090, "Level 44\nEagle Eye\nIncreases your Ranged by 15%");
        addTooltip(25092, "Level 45\nMystic Might\nIncreases your Magic by 15%");
        addTooltip(25094,
                "Level 46\nRetribution\nInflicts damage to nearby\ntargets if you die");
        addTooltip(25096,
                "Level 49\nRedemption\nHeals you when damaged\nand Hitpoints falls\nbelow 10%");
        addTooltip(25098,
                "Level 52\nSmite\n1/4 of damage dealt is\nalso removed from\nopponent's Prayer");
        addTooltip(
                25100,
                "Level 60\nChivalry\nIncreases your Defence by 20%,\nStrength by 18%, and Attack by\n15%");
        addTooltip(
                25102,
                "Level 70\nPiety\nIncreases your Defence by 25%,\nStrength by 23%, and Attack by\n20%");
        addTooltip(
                25106,
                "Level 80\nRigour\nIncreases your Ranging by 22%\nand Defence by 25%");
        addTooltip(
                25110,
                "Level 80\nAugury\nIncreases your Magic by 22%\nand Defence by 25%");
        setChildren(86, prayerMenu);
        setBounds(687, 85, 241, index, prayerMenu);
        index++;
        setBounds(25112, 65, 241, index, prayerMenu);
        index++;
        setBounds(25000, 2, 5, index, prayerMenu);
        index++;
        setBounds(25001, 5, 8, index, prayerMenu);
        index++;
        setBounds(25002, 40, 5, index, prayerMenu);
        index++;
        setBounds(25003, 44, 8, index, prayerMenu);
        index++;
        setBounds(25004, 76, 5, index, prayerMenu);
        index++;
        setBounds(25005, 79, 11, index, prayerMenu);
        index++;
        setBounds(25006, 113, 5, index, prayerMenu);
        index++;
        setBounds(25007, 116, 10, index, prayerMenu);
        index++;
        setBounds(25008, 150, 5, index, prayerMenu);
        index++;
        setBounds(25009, 153, 9, index, prayerMenu);
        index++;
        setBounds(25010, 2, 45, index, prayerMenu);
        index++;
        setBounds(25011, 5, 48, index, prayerMenu);
        index++;
        setBounds(25012, 39, 45, index, prayerMenu);
        index++;
        setBounds(25013, 44, 47, index, prayerMenu);
        index++;
        setBounds(25014, 76, 45, index, prayerMenu);
        index++;
        setBounds(25015, 79, 49, index, prayerMenu);
        index++;
        setBounds(25016, 113, 45, index, prayerMenu);
        index++;
        setBounds(25017, 116, 50, index, prayerMenu);
        index++;
        setBounds(25018, 151, 45, index, prayerMenu);
        index++;
        setBounds(25019, 154, 50, index, prayerMenu);
        index++;
        setBounds(25020, 2, 82, index, prayerMenu);
        index++;
        setBounds(25021, 4, 84, index, prayerMenu);
        index++;
        setBounds(25022, 40, 82, index, prayerMenu);
        index++;
        setBounds(25023, 44, 87, index, prayerMenu);
        index++;
        setBounds(25024, 77, 82, index, prayerMenu);
        index++;
        setBounds(25025, 81, 85, index, prayerMenu);
        index++;
        setBounds(25026, 114, 83, index, prayerMenu);
        index++;
        setBounds(25027, 117, 85, index, prayerMenu);
        index++;
        setBounds(25028, 153, 83, index, prayerMenu);
        index++;
        setBounds(25029, 156, 87, index, prayerMenu);
        index++;
        setBounds(25030, 2, 120, index, prayerMenu);
        index++;
        setBounds(25031, 5, 125, index, prayerMenu);
        index++;
        setBounds(25032, 40, 120, index, prayerMenu);
        index++;
        setBounds(25033, 43, 124, index, prayerMenu);
        index++;
        setBounds(25034, 78, 120, index, prayerMenu);
        index++;
        setBounds(25035, 83, 124, index, prayerMenu);
        index++;
        setBounds(25036, 114, 120, index, prayerMenu);
        index++;
        setBounds(25037, 115, 121, index, prayerMenu);
        index++;
        setBounds(25038, 151, 120, index, prayerMenu);
        index++;
        setBounds(25039, 154, 124, index, prayerMenu);
        index++;
        setBounds(25040, 2, 158, index, prayerMenu);
        index++;
        setBounds(25041, 5, 160, index, prayerMenu);
        index++;
        setBounds(25042, 39, 158, index, prayerMenu);
        index++;
        setBounds(25043, 41, 158, index, prayerMenu);
        index++;
        setBounds(25044, 76, 158, index, prayerMenu);
        index++;
        setBounds(25045, 79, 163, index, prayerMenu);
        index++;
        setBounds(25046, 114, 158, index, prayerMenu);
        index++;
        setBounds(25047, 116, 158, index, prayerMenu);
        index++;
        setBounds(25048, 153, 158, index, prayerMenu);
        index++;
        setBounds(25049, 161, 160, index, prayerMenu);
        index++;
        setBounds(25050, 2, 196, index, prayerMenu);
        index++;
        setBounds(25104, 40, 196, index, prayerMenu);
        index++;
        setBounds(25105, 43, 201, index, prayerMenu);
        index++;
        setBounds(25108, 77, 197, index, prayerMenu);
        index++;
        setBounds(25109, 80, 201, index, prayerMenu);
        index++;
        setBounds(25051, 4, 207, index, prayerMenu);
        setBoundry(++index, 25052, firstRowXPos - 2, firstRowYPos, prayerMenu);
        setBoundry(++index, 25054, firstRowXPos - 5, firstRowYPos, prayerMenu);
        setBoundry(++index, 25056, firstRowXPos, firstRowYPos, prayerMenu);
        setBoundry(++index, 25058, firstRowXPos, firstRowYPos, prayerMenu);
        setBoundry(++index, 25060, firstRowXPos, firstRowYPos, prayerMenu);
        setBoundry(++index, 25062, secondRowXPos - 9, secondRowYPos, prayerMenu);
        setBoundry(++index, 25064, secondRowXPos - 11, secondRowYPos,
                prayerMenu);
        setBoundry(++index, 25066, secondRowXPos, secondRowYPos, prayerMenu);
        setBoundry(++index, 25068, secondRowXPos, secondRowYPos, prayerMenu);
        setBoundry(++index, 25070, secondRowXPos + 25, secondRowYPos,
                prayerMenu);
        setBoundry(++index, 25072, thirdRowXPos, thirdRowYPos, prayerMenu);
        setBoundry(++index, 25074, thirdRowXPos - 2, thirdRowYPos, prayerMenu);
        setBoundry(++index, 25076, thirdRowXPos, thirdRowYPos, prayerMenu);
        setBoundry(++index, 25078, thirdRowXPos - 7, thirdRowYPos, prayerMenu);
        setBoundry(++index, 25080, thirdRowXPos - 10, thirdRowYPos, prayerMenu);
        setBoundry(++index, 25082, fourthRowXPos, fourthRowYPos, prayerMenu);
        setBoundry(++index, 25084, fourthRowXPos - 8, fourthRowYPos, prayerMenu);
        setBoundry(++index, 25086, fourthRowXPos - 7, fourthRowYPos, prayerMenu);
        setBoundry(++index, 25088, fourthRowXPos - 2, fourthRowYPos, prayerMenu);
        setBoundry(++index, 25090, fourthRowXPos - 2, fourthRowYPos, prayerMenu);
        setBoundry(++index, 25092, fifthRowXPos, fifthRowYPos, prayerMenu);
        setBoundry(++index, 25094, fifthRowXPos, fifthRowYPos - 20, prayerMenu);
        setBoundry(++index, 25096, fifthRowXPos, fifthRowYPos - 25, prayerMenu);
        setBoundry(++index, 25098, fifthRowXPos + 15, fifthRowYPos - 25,
                prayerMenu);
        setBoundry(++index, 25100, fifthRowXPos - 12, fifthRowYPos - 20,
                prayerMenu);
        setBoundry(++index, 25102, sixthRowXPos - 2, sixthRowYPos + 50, prayerMenu);
        setBoundry(++index, 25106, sixthRowXPos - 2, sixthRowYPos + 50, prayerMenu);
        setBoundry(++index, 25110, sixthRowXPos - 2, sixthRowYPos + 50, prayerMenu);
        index++;
    }

    private void curseTabInterface() {
        RSInterface Interface = addTabInterface(32500);
        int index = 0;
        addSpriteLoader(688, 871);
        //addTooltip(19021, "This is the effect that prayers\nand curses have during combat.\nIt includes curses that have\nbeen used against you. The\nadjustment has no effect\noutside of combat. The\npercentage shown is relative to\n your skill level, and may vary\ndepending on the enemy you are\nfighting, and the prayers or\n curses used. Partial\npercentages are not shown.");
        addSpriteLoader(689, 872);
        addText(19025, "  Stat Adjustments", 0xFFCC00, false, true, 52, tda, 0);
        addText(690, "690", 0xFF981F, false, false, -1, tda, 0);
        addText(691, "691", 0xFF981F, false, false, -1, tda, 0);
        addText(692, "692", 0xFF981F, false, false, -1, tda, 0);
        addText(693, "693", 0xFF981F, false, false, -1, tda, 0);
        addText(694, "694", 0xFF981F, false, false, -1, tda, 0);
        addText(687, "99/99", 0xFF981F, false, false, -1, tda, 1);
        addSpriteLoader(32502, 870);
        addCurseWithTooltip(32503, 0, 610, 49, 7, "Protect Item", 32582);
        addCurseWithTooltip(32505, 0, 611, 49, 4, "Sap Warrior", 32544);
        addCurseWithTooltip(32507, 0, 612, 51, 5, "Sap Ranger", 32546);
        addCurseWithTooltip(32509, 0, 613, 53, 3, "Sap Mage", 32548);
        addCurseWithTooltip(32511, 0, 614, 55, 2, "Sap Spirit", 32550);
        addCurseWithTooltip(32513, 0, 615, 58, 18, "Berserker", 32552);
        addCurseWithTooltip(32515, 0, 616, 61, 15, "Deflect Summoning", 32554);
        addCurseWithTooltip(32517, 0, 617, 64, 17, "Deflect Magic", 32556);
        addCurseWithTooltip(32519, 0, 618, 67, 16, "Deflect Missiles", 32558);
        addCurseWithTooltip(32521, 0, 619, 70, 6, "Deflect Melee", 32560);
        addCurseWithTooltip(32523, 0, 620, 73, 9, "Leech Attack", 32562);
        addCurseWithTooltip(32525, 0, 621, 75, 10, "Leech Ranged", 32564);
        addCurseWithTooltip(32527, 0, 622, 77, 11, "Leech Magic", 32566);
        addCurseWithTooltip(32529, 0, 623, 79, 12, "Leech Defence", 32568);
        addCurseWithTooltip(32531, 0, 624, 81, 13, "Leech Strength", 32570);
        addCurseWithTooltip(32533, 0, 625, 83, 14, "Leech Energy", 32572);
        addCurseWithTooltip(32535, 0, 626, 85, 19, "Leech Special Attack", 32574);
        addCurseWithTooltip(32537, 0, 627, 88, 1, "Wrath", 32576);
        addCurseWithTooltip(32539, 0, 628, 91, 8, "Soul Split", 32578);
        addCurseWithTooltip(32541, 0, 629, 94, 20, "Turmoil", 32580);
        addTooltip(32582, "Level 50\nProtect Item\nKeep 1 extra item if you die");
        addTooltip(32544, "Level 50\nSap Warrior\nDrains 10% of enemy Attack,\nStrength and Defence,\nincreasing to 20% over time");
        addTooltip(32546, "Level 52\nSap Ranger\nDrains 10% of enemy Ranged\nand Defence, increasing to 20%\nover time");
        addTooltip(32548, "Level 54\nSap Mage\nDrains 10% of enemy Magic\nand Defence, increasing to 20%\nover time");
        addTooltip(32550, "Level 56\nSap Spirit\nDrains enenmy special attack\nenergy");
        addTooltip(32552, "Level 59\nBerserker\nBoosted stats last 15% longer");
        addTooltip(32554, "Level 62\nDeflect Summoning\nReduces damage dealt from\nSummoning scrolls, prevents the\nuse of a familiar's special\nattack, and can deflect some of\ndamage back to the attacker");
        addTooltip(32556, "Level 65\nDeflect Magic\nProtects against magical attacks\nand can deflect some of the\ndamage back to the attacker");
        addTooltip(32558, "Level 68\nDeflect Missiles\nProtects against ranged attacks\nand can deflect some of the\ndamage back to the attacker");
        addTooltip(32560, "Level 71\nDeflect Melee\nProtects against melee attacks\nand can deflect some of the\ndamage back to the attacker");
        addTooltip(32562, "Level 74\nLeech Attack\nBoosts Attack by 5%, increasing\nto 10% over time, while draining\nenemy Attack by 10%, increasing\nto 25% over time");
        addTooltip(32564, "Level 76\nLeech Ranged\nBoosts Ranged by 5%, increasing\nto 10% over time, while draining\nenemy Ranged by 10%,\nincreasing to 25% over\ntime");
        addTooltip(32566, "Level 78\nLeech Magic\nBoosts Magic by 5%, increasing\nto 10% over time, while draining\nenemy Magic by 10%, increasing\nto 25% over time");
        addTooltip(32568, "Level 80\nLeech Defence\nBoosts Defence by 5%, increasing\nto 10% over time, while draining\n enemy Defence by10%,\nincreasing to 25% over\ntime");
        addTooltip(32570, "Level 82\nLeech Strength\nBoosts Strength by 5%, increasing\nto 10% over time, while draining\nenemy Strength by 10%, increasing\n to 25% over time");
        addTooltip(32572, "Level 84\nLeech Energy\nDrains enemy run energy, while\nincreasing your own");
        addTooltip(32574, "Level 86\nLeech Special Attack\nDrains enemy special attack\nenergy, while increasing your\nown");
        addTooltip(32576, "Level 89\nWrath\nInflicts damage to nearby\ntargets if you die");
        addTooltip(32578, "Level 92\nSoul Split\n1/4 of damage dealt is also removed\nfrom opponent's Prayer and\nadded to your Hitpoints");
        addTooltip(32580, "Level 95\nTurmoil\nIncreases Attack and Defence\nby 15%, plus 15% of enemy's\nlevel, increases Range,Magicby 15%\nand Strength by 23%");
        setChildren(70, Interface);
        /*curse start*/
        setBounds(689, 0, 217, index, Interface);
        index++;
        //setBounds(701, 0, 217, index, Interface);index++;
        setBounds(687, 85, 241, index, Interface);
        index++;
        setBounds(688, 0, 170, index, Interface);
        index++;
        setBounds(690, 2, 200, index, Interface);
        index++;
        setBounds(691, 41, 200, index, Interface);
        index++;
        setBounds(692, 79, 200, index, Interface);
        index++;
        setBounds(693, 117, 200, index, Interface);
        index++;
        setBounds(694, 160, 200, index, Interface);
        index++;
        setBounds(19025, 47, 218, index, Interface);
        index++;
        //setBounds(19030, 47, 219, index, Interface);index++;
        setBounds(32502, 65, 241, index, Interface);
        index++;
        setBounds(32503, 2, 5, index, Interface);
        index++;
        setBounds(32504, 8, 8, index, Interface);
        index++;
        setBounds(32505, 40, 5, index, Interface);
        index++;
        setBounds(32506, 47, 12, index, Interface);
        index++;
        setBounds(32507, 76, 5, index, Interface);
        index++;
        setBounds(32508, 82, 11, index, Interface);
        index++;
        setBounds(32509, 113, 5, index, Interface);
        index++;
        setBounds(32510, 116, 8, index, Interface);
        index++;
        setBounds(32511, 150, 5, index, Interface);
        index++;
        setBounds(32512, 155, 10, index, Interface);
        index++;
        setBounds(32513, 2, 45, index, Interface);
        index++;
        setBounds(32514, 9, 48, index, Interface);
        index++;
        setBounds(32515, 39, 45, index, Interface);
        index++;
        setBounds(32516, 42, 47, index, Interface);
        index++;
        setBounds(32517, 76, 45, index, Interface);
        index++;
        setBounds(32518, 79, 48, index, Interface);
        index++;
        setBounds(32519, 113, 45, index, Interface);
        index++;
        setBounds(32520, 116, 48, index, Interface);
        index++;
        setBounds(32521, 151, 45, index, Interface);
        index++;
        setBounds(32522, 154, 48, index, Interface);
        index++;
        setBounds(32523, 2, 82, index, Interface);
        index++;
        setBounds(32524, 6, 86, index, Interface);
        index++;
        setBounds(32525, 40, 82, index, Interface);
        index++;
        setBounds(32526, 42, 86, index, Interface);
        index++;
        setBounds(32527, 77, 82, index, Interface);
        index++;
        setBounds(32528, 79, 86, index, Interface);
        index++;
        setBounds(32529, 114, 83, index, Interface);
        index++;
        setBounds(32530, 119, 87, index, Interface);
        index++;
        setBounds(32531, 153, 83, index, Interface);
        index++;
        setBounds(32532, 156, 86, index, Interface);
        index++;
        setBounds(32533, 2, 120, index, Interface);
        index++;
        setBounds(32534, 7, 125, index, Interface);
        index++;
        setBounds(32535, 40, 120, index, Interface);
        index++;
        setBounds(32536, 45, 124, index, Interface);
        index++;
        setBounds(32537, 78, 120, index, Interface);
        index++;
        setBounds(32538, 86, 124, index, Interface);
        index++;
        setBounds(32539, 114, 120, index, Interface);
        index++;
        setBounds(32540, 120, 125, index, Interface);
        index++;
        setBounds(32541, 151, 120, index, Interface);
        index++;
        setBounds(32542, 153, 127, index, Interface);
        index++;
        setBounds(32582, 10, 40, index, Interface);
        index++;
        setBounds(32544, 20, 40, index, Interface);
        index++;
        setBounds(32546, 20, 40, index, Interface);
        index++;
        setBounds(32548, 20, 40, index, Interface);
        index++;
        setBounds(32550, 20, 40, index, Interface);
        index++;
        setBounds(32552, 10, 80, index, Interface);
        index++;
        setBounds(32554, 10, 80, index, Interface);
        index++;
        setBounds(32556, 10, 80, index, Interface);
        index++;
        setBounds(32558, 10, 80, index, Interface);
        index++;
        setBounds(32560, 10, 80, index, Interface);
        index++;
        setBounds(32562, 10, 120, index, Interface);
        index++;
        setBounds(32564, 10, 120, index, Interface);
        index++;
        setBounds(32566, 10, 120, index, Interface);
        index++;
        setBounds(32568, 5, 120, index, Interface);
        index++;
        setBounds(32570, 5, 120, index, Interface);
        index++;
        setBounds(32572, 10, 160, index, Interface);
        index++;
        setBounds(32574, 10, 160, index, Interface);
        index++;
        setBounds(32576, 10, 160, index, Interface);
        index++;
        setBounds(32578, 10, 160, index, Interface);
        index++;
        setBounds(32580, 10, 160, index, Interface);
        index++;
    }

    private void configureLunar() {
        constructLunar();
        drawRune(30003, 208);
        drawRune(30004, 209);
        drawRune(30005, 210);
        drawRune(30006, 211);
        drawRune(30007, 212);
        drawRune(30008, 213);
        drawRune(30009, 214);
        drawRune(30010, 215);
        drawRune(30011, 216);
        drawRune(30012, 217);
        drawRune(30013, 218);
        drawRune(30014, 219);
        drawRune(30015, 220);
        drawRune(30016, 221);
        addLunar3RunesSmallBox(30017, 9075, 554, 555, 0, 4, 3, 30003, 30004, 64, "Bake Pie", "Bake pies without a stove", tda, 222, 16, 2);
        addLunar2RunesSmallBox(30025, 9075, 557, 0, 7, 30006, 65, "Cure Plant", "Cure disease on farming patch", tda, 223, 4, 2);
        addLunar3RunesBigBox(30032, 9075, 564, 558, 0, 0, 0, 30013, 30007, 65, "Monster Examine", "Detect the combat statistics of a\\nmonster", tda, 224, 2, 2);
        addLunar3RunesSmallBox(30040, 9075, 564, 556, 0, 0, 1, 30013, 30005, 66, "NPC Contact", "Speak with varied NPCs", tda, 225, 0, 2);
        addLunar3RunesSmallBox(30048, 9075, 563, 557, 0, 0, 9, 30012, 30006, 67, "Cure Other", "Cure poisoned players", tda, 226, 8, 2);
        addLunar3RunesSmallBox(30056, 9075, 555, 554, 0, 2, 0, 30004, 30003, 67, "Humidify", "fills certain vessels with water", tda, 227, 0, 5);
        addLunar3RunesSmallBox(30064, 9075, 563, 557, 1, 0, 1, 30012, 30006, 68, "Training Teleport", "Teleport to training areas", tda, 228, 0, 5);
        addLunar3RunesBigBox(30075, 9075, 563, 557, 1, 0, 3, 30012, 30006, 69, "Bosses Teleport", "Teleports to various bosses", tda, 229, 0, 5);
        addLunar3RunesSmallBox(30083, 9075, 563, 557, 1, 0, 5, 30012, 30006, 70, "Skills Teleport", "Teleports to skilling areas", tda, 230, 0, 5);
        addLunar3RunesSmallBox(30091, 9075, 564, 563, 1, 1, 0, 30013, 30012, 70, "Cure Me", "Cures Poison", tda, 231, 0, 5);
        addLunar2RunesSmallBox(30099, 9075, 557, 1, 1, 30006, 70, "Hunter Kit", "Get a kit of hunting gear", tda, 232, 0, 5);
        addLunar3RunesSmallBox(30106, 9075, 563, 555, 1, 0, 0, 30012, 30004, 71, "Mini-Games Teleport", "Teleport to fun Mini-Games", tda, 233, 0, 5);
        addLunar3RunesBigBox(30114, 9075, 563, 555, 1, 0, 4, 30012, 30004, 72, "Pking Teleport", "Teleports to pking areas", tda, 234, 0, 5);
        addLunar3RunesSmallBox(30122, 9075, 564, 563, 1, 1, 1, 30013, 30012, 73, "Cure Group", "Cures Poison on players", tda, 235, 0, 5);
        addLunar3RunesBigBox(30130, 9075, 564, 559, 1, 1, 4, 30013, 30008, 74, "Stat Spy", "Cast on another player to see their\\nskill levels", tda, 236, 8, 2);
        addLunar3RunesBigBox(30138, 9075, 563, 554, 1, 1, 2, 30012, 30003, 74, "Skill teleport", "", tda, 237, 0, 5);
        addLunar3RunesBigBox(30146, 9075, 563, 554, 1, 1, 5, 30012, 30003, 75, "Tele Group Barbarian", "Teleports players to the Barbarian\\noutpost", tda, 238, 0, 5);
        addLunar3RunesSmallBox(30154, 9075, 554, 556, 1, 5, 9, 30003, 30005, 76, "Superglass Make", "Make glass without a furnace", tda, 239, 16, 2);
        addLunar3RunesSmallBox(30162, 9075, 563, 555, 1, 1, 3, 30012, 30004, 77, "City teleport", "", tda, 240, 0, 5);
        addLunar3RunesSmallBox(30170, 9075, 563, 555, 1, 1, 7, 30012, 30004, 78, "Tele Group Khazard", "Teleports players to Port khazard", tda, 241, 0, 5);
        addLunar3RunesBigBox(30178, 9075, 564, 559, 1, 0, 4, 30013, 30008, 78, "Dream", "Take a rest and restore hitpoints 3\\n times faster", tda, 242, 0, 5);
        addLunar3RunesSmallBox(30186, 9075, 557, 555, 1, 9, 4, 30006, 30004, 79, "String Jewellery", "String amulets without wool", tda, 243, 0, 5);
        addLunar3RunesLargeBox(30194, 9075, 557, 555, 1, 9, 9, 30006, 30004, 80, "Stat Restore Pot\\nShare", "Share a potion with up to 4 nearby\\nplayers", tda, 244, 0, 5);
        addLunar3RunesSmallBox(30202, 9075, 554, 555, 1, 6, 6, 30003, 30004, 81, "Magic Imbue", "Combine runes without a talisman", tda, 245, 0, 5);
        addLunar3RunesBigBox(30210, 9075, 561, 557, 2, 1, 14, 30010, 30006, 82, "Fertile Soil", "Fertilise a farming patch with super\\ncompost", tda, 246, 4, 2);
        addLunar3RunesBigBox(30218, 9075, 557, 555, 2, 11, 9, 30006, 30004, 83, "Boost Potion Share", "Shares a potion with up to 4 nearby\\nplayers", tda, 247, 0, 5);
        addLunar3RunesSmallBox(30226, 9075, 563, 555, 2, 2, 9, 30012, 30004, 84, "Fishing Guild Teleport", "Teleports you to the fishing guild", tda, 248, 0, 5);
        addLunar3RunesLargeBox(30234, 9075, 563, 555, 1, 2, 13, 30012, 30004, 85, "Tele Group Fishing\\nGuild", "Teleports players to the Fishing\\nGuild", tda, 249, 0, 5);
        addLunar3RunesSmallBox(30242, 9075, 557, 561, 2, 14, 0, 30006, 30010, 85, "Plank Make", "Turn Logs into planks", tda, 250, 16, 5);
        /******** Cut Off Limit **********/
        addLunar3RunesSmallBox(30250, 9075, 563, 555, 2, 2, 9, 30012, 30004, 86, "Catherby Teleport", "Teleports you to Catherby", tda, 251, 0, 5);
        addLunar3RunesSmallBox(30258, 9075, 563, 555, 2, 2, 14, 30012, 30004, 87, "Tele Group Catherby", "Teleports players to Catherby", tda, 252, 0, 5);
        addLunar3RunesSmallBox(30266, 9075, 563, 555, 2, 2, 7, 30012, 30004, 88, "Ice Plateau Teleport", "Teleports you to Ice Plateau", tda, 253, 0, 5);
        addLunar3RunesBigBox(30274, 9075, 563, 555, 2, 2, 15, 30012, 30004, 89, "Tele Group Ice Plateau", "Teleports players to Ice Plateau", tda, 254, 0, 5);
        addLunar3RunesBigBox(30282, 9075, 563, 561, 2, 1, 0, 30012, 30010, 90, "Energy Transfer", "Spend HP and SA energy to\\n give another SA and run energy", tda, 255, 8, 2);
        addLunar3RunesBigBox(30290, 9075, 563, 565, 2, 2, 0, 30012, 30014, 91, "Heal Other", "Transfer up to 75% of hitpoints\\n to another player", tda, 256, 8, 2);
        addLunar3RunesBigBox(30298, 9075, 560, 557, 2, 1, 9, 30009, 30006, 92, "Vengeance Other", "Allows another player to rebound\\ndamage to an opponent", tda, 257, 8, 2);
        addLunar3RunesSmallBox(30306, 9075, 560, 557, 3, 1, 9, 30009, 30006, 93, "Vengeance", "Rebound damage to an opponent", tda, 258, 0, 5);
        addLunar3RunesBigBox(30314, 9075, 565, 563, 3, 2, 5, 30014, 30012, 94, "Heal Group", "Transfer up to 75% of hitpoints to a group", tda, 259, 0, 5);
        addLunar3RunesBigBox(30322, 9075, 564, 563, 2, 1, 0, 30013, 30012, 95, "Spellbook Swap", "Change to another spellbook for 1\\nspell cast", tda, 260, 0, 5);
    }

    public static void staffTabInterface(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(46343);
        RSInterface scroll = addTabInterface(32385);
        addText(32386, "Staff List", tda, 1, 0xff9933, true, true);
        //"Friends List", tda, 1, 0xff9933, true, true);
        addText(32394, "You are viewing the staff online", tda, 0, 0xff9933, true, true);
        addText(32395, "Private msg if you need assistance.", tda, 0, 0xFF0000, true, true);
        addText(32396, "Online Staff: 1", tda, 2, 0xff9933, true, true);

        addSpriteLoader(32391, 1240);
        addSpriteLoader(32392, 1241);
        addSpriteLoader(32393, 1240);

        scroll.totalChildren(50);
        scroll.width = 174;
        scroll.height = 164;
        scroll.scrollMax = 176;
        tab.totalChildren(9);

        tab.child(0, 32386, 95, 4);// title
        tab.child(1, 32392, 0, 25);// bg
        tab.child(2, 32391, 0, 25);// first line
        tab.child(3, 32393, 0, 193);// middle line
        tab.child(4, 32393, 0, 250);// bottom line
        tab.child(5, 32385, 0, 28);// scroll
        tab.child(6, 32394, 87, 200);
        tab.child(7, 32395, 94, 210);
        tab.child(8, 32396, 94, 230);

        int child = 0;
        int y = 3;
        for (int i = 0; i < 50; i++) {
            scroll.child(child + i, 32410 + i, 15, y);
            y += 16;
            addClickableText(32410 + i, "", null, tda, 1, 0xFF8900, 130, 13);
            //addText(32410 + i, "Staff - " + (i + 1), tda, 1, 0xff9933, true, false);
        }
    }

    private void pouchCreation() {
        int totalScrolls = SummoningInterfaceData.pouchItems.length;
        int xPadding = 53;
        int yPadding = 57;
        int xPos = 13;
        int yPos = 20;
        RSInterface rsinterface = addTabInterface(63471);
        setChildren(8, rsinterface);
        addCloseButton(63450, 63451, 63452);
        addButtonWSpriteLoader(63475, 1005, "Transform Scrolls");
        addSpriteLoader(63474, 1002);
        addSpriteLoader(63476, 1003);
        addSpriteLoader(63473, 1004);
        addSpriteLoader(63472, 1001);
        RSInterface scroll = addTabInterface(63478);
        setChildren(4 * totalScrolls, scroll);
        scroll.interfaceShown = false;
        int req[] = {1, 2, 3};
        for (int i = 0; i < totalScrolls; i++) {
            addInAreaHoverSpriteLoader(63479 + i * 8, 1006, 48, 52, "nothing", -1, 0);
            addPouch(63480 + i * 8, req, 1, SummoningInterfaceData.pouchItems[i], SummoningInterfaceData.summoningLevelRequirements[i], SummoningInterfaceData.pouchNames[i], tda, i, 5);
            setBounds(63479 + i * 8, 36 + (i % 8) * xPadding, 0 + (i / 8) * yPadding, i, scroll);
            setBounds(63480 + i * 8, 43 + (i % 8) * xPadding, 1 + (i / 8) * yPadding, 156 + i, scroll);
        }
        for (int i = 0; i < SummoningInterfaceData.shards.length; i++) {
            addSummoningText(72001 + i, "" + SummoningInterfaceData.shards[i][0], 0xCCCBCB, false, true, 52, tda, 0);
            setBounds(72001 + i, SummoningInterfaceData.shards[i][1], SummoningInterfaceData.shards[i][2], 78 + i, scroll);
        }
        for (int i = 0; i < totalScrolls; i++) {
            int drawX = 5 + (i % 8) * xPadding;
            if (drawX > 292)
                drawX -= 90;
            int drawY = 55 + (i / 8) * yPadding;
            if (drawY > 160)
                drawY -= 80;
            setBounds(63481 + i * 8, drawX, drawY, 234 + i, scroll);
        }
        scroll.parentID = 63478;
        scroll.id = 63478;
        scroll.atActionType = 0;
        scroll.contentType = 0;
        scroll.width = 474;
        scroll.height = 257;
        scroll.scrollMax = 570;
        setBounds(63472, xPos, yPos, 0, rsinterface);
        setBounds(63473, xPos + 9, yPos + 9, 1, rsinterface);
        setBounds(63474, xPos + 29, yPos + 10, 2, rsinterface);
        setBounds(63475, xPos + 79, yPos + 9, 3, rsinterface);
        setBounds(63476, xPos + 106, yPos + 10, 4, rsinterface);
        setBounds(63450, xPos + 460, yPos + 8, 5, rsinterface);
        setBounds(63451, xPos + 460, yPos + 8, 6, rsinterface);
        setBounds(63478, 0, yPos + 39, 7, rsinterface);
    }

    private void settingsInterface2() {
        RSInterface rsinterface = addInterface(26000);
        int x = 30;
        int y = 49;
        addSpriteLoader(26001, 59);
        addText(26002, "Settings", 0xe4a146, true, true, 52, 2);
        // addInAreaHover(26003, 427, 428, 16, 16, "Close", 250, 3);
        addButton(26003, 4, -1, 427, 427, 15, 15, "Close", 427, 1);
        addText(26004, "Use New Function Keys.", 0xe4a146, false, true, 52, 0);
        addSprite(26005, 494);
        addSprite(26011, 495);
        addText(26006, "Display New Health Bars.", 0xe4a146, false, true, 52, 0);
        addText(26009, "Enable New Cursors.", 0xe4a146, false, true, 52, 0);
        addText(26015, "Display New Hitmarks.", 0xe4a146, false, true, 52, 0);
        addText(26028, "Display Hitpoints Above \\nHead.", 0xe4a146, false, true, 52, 0);
        addText(26025, "Display Usernames Above \\nHead.", 0xe4a146, false, true, 52, 0);
        addText(26030, "Enable Constitution.\\n(x10 damage)", 0xe4a146, false, true, 52, 0);
        addText(26032, "New Gameframe.", 0xe4a146, false, true, 52, 0);
        addText(26034, "Enable highlighting \\nyour username\\nwhen mentioned-\\nin chat.", 0xe4a146, false, true, 52, 0);
        addButton(26007, 4, -1, 484, 485, 15, 15, "Toggle function keys", 650, 1);
        addButton(26008, 4, -1, 484, 485, 15, 15, "Toggle health bars", 651, 1);
        addButton(26010, 4, -1, 484, 485, 15, 15, "Toggle cursors", 652, 1);
        addButton(26014, 4, -1, 484, 485, 15, 15, "Toggle hitmarks", 654, 1);
        addButton(26026, 4, -1, 484, 485, 15, 15, "Toggle hitpoints above head", 655, 1);
        addButton(26027, 4, -1, 484, 485, 15, 15, "Toggle usernames above head", 656, 1);
        addButton(26029, 4, -1, 484, 485, 15, 15, "Toggle constitution", 657, 1);
        addButton(26031, 4, -1, 484, 485, 15, 15, "Toggle gameframe", 658, 1);
        addButton(26033, 4, -1, 484, 485, 15, 15, "Toggle push-notifications", 659, 1);
        addButton(26065, 4, -1, 484, 485, 15, 15, "Toggle ground ground text", 660, 1);
        addButton(26067, 4, -1, 484, 485, 15, 15, "Toggle Fog", 661, 1);
        addButton(26069, 4, -1, 484, 485, 15, 15, "Toggle Particles", 662, 1);
        addCheckmarkHover(26054, 4, 26055, 576, 577, 57, 35, SETTING_CONFIGS[15], 1, "Low Detail", 26056, 577, 577, 26057, "", "", 12, 20);
        addCheckmarkHover(26058, 4, 26059, 578, 579, 57, 35, SETTING_CONFIGS[16], 1, "High Detail", 26060, 579, 579, 26061, "", "", 12, 20);

        addText(26050, "If the game runs slowly on your computer, try reducing these settings.", tda, 1, 0xeb981f, true, true);
        addText(26051, "@yel@Resizable", tda, 0, 0xeb981f, false, true);
        addText(26052, "@yel@Fullscreen", tda, 0, 0xeb981f, false, true);
        addText(26053, "@yel@Fixed", tda, 0, 0xeb981f, false, true);

        addText(26062, "@yel@Low Detail", tda, 0, 0xeb981f, false, true);
        addText(26063, "@yel@High Detail", tda, 0, 0xeb981f, false, true);
        addText(26064, "Show ground text.", 0xe4a146, false, true, 52, 0);
        addText(26066, "Toggle Fog.", 0xe4a146, false, true, 52, 0);
        addText(26068, "Toggle Particles.", 0xe4a146, false, true, 52, 0);
        /**
         * Fixed buttons
         */
        addHoverButton(26016, 486, 50, 39, "Fixed Mode", -1, 26017, 1);
        addHoveredButton(26017, 487, 50, 39, 26018);
        /**
         * Resizable buttons
         */
        addHoverButton(26019, 488, 50, 39, "Resizable Mode", -1, 26020, 1);
        addHoveredButton(26020, 489, 50, 39, 26021);
        /**
         * Fullscreen buttons
         */
        addHoverButton(26022, 490, 50, 39, "Fullscreen Mode", -1, 26023, 1);
        addHoveredButton(26023, 491, 50, 39, 26024);

        setChildren(61, rsinterface);
        int i = 0;
        // background
        setBounds(26001, x + -15, y + -30, i, rsinterface);
        i++;
        // more options text
        setBounds(26002, x + 230, y + -20, i, rsinterface);
        i++;
        // close button
        setBounds(26003, x + 445, y + -20, i, rsinterface);
        i++;
        // use new function key text
        setBounds(26004, x + 9, y + 107, i, rsinterface);
        i++;
        // new health bars text
        setBounds(26006, x + 9, y + 127, i, rsinterface);
        i++;
        // toggle function keys
        setBounds(26007, x + 154, y + 104, i, rsinterface);
        i++;
        // display fog toggle
        setBounds(26014, x + 154, y + 148, i, rsinterface);
        i++;
        // toggle health bars
        setBounds(26008, x + 154, y + 124, i, rsinterface);
        i++;
        // display damage text
        setBounds(26009, x + 175, y + 110, i, rsinterface);
        i++;
        // enable tweening text
        setBounds(26030, x + 9, y + 171, i, rsinterface);
        i++;
        setBounds(26032, x + 339, y + 110, i, rsinterface);
        i++;
        setBounds(26034, x + 339, y + 135, i, rsinterface);
        i++;
        // gameframe button
        setBounds(26031, x + 425, y + 110, i, rsinterface);
        i++;
        // hitmarks
        setBounds(26033, x + 425, y + 159, i, rsinterface);
        i++;
        // drop text
        setBounds(26065, x + 425, y + 200, i, rsinterface);
        i++;
        // fog
        setBounds(26067, x + 155, y + 200, i, rsinterface);
        i++;
        // particle
        setBounds(26069, x + 285, y + 200, i, rsinterface);
        i++;
        // display health above heads
        setBounds(26028, x + 175, y + 135, i, rsinterface);
        i++;
        // display titles above heads
        setBounds(26025, x + 175, y + 167, i, rsinterface);
        i++;
        // toggle health above heads
        setBounds(26026, x + 315, y + 138, i, rsinterface);
        i++;
        // toggle title above heads
        setBounds(26027, x + 315, y + 170, i, rsinterface);
        i++;
        // display fog text
        setBounds(26015, x + 9, y + 152, i, rsinterface);
        i++;
        // toggle damage
        setBounds(26010, x + 315, y + 110, i, rsinterface);
        i++;
        // toggle tweening
        setBounds(26029, x + 154, y + 172, i, rsinterface);
        i++;
        // first row horizontal line
        setBounds(26005, x + 7, y + 120, i, rsinterface);
        i++;
        setBounds(26005, x + 7, y + 144, i, rsinterface);
        i++;
        setBounds(26005, x + 7, y + 168, i, rsinterface);
        i++;
        // second row horizontal line
        setBounds(26005, x + 170, y + 161, i, rsinterface);
        i++;
        setBounds(26005, x + 170, y + 130, i, rsinterface);
        i++;
        // first row vertical line
        setBounds(26011, x + 151, y + 105, i, rsinterface);
        i++;
        setBounds(26011, x + 151, y + 134, i, rsinterface);
        i++;
        setBounds(26011, x + 151, y + 161, i, rsinterface);
        i++;
        // second row vertical line
        setBounds(26011, x + 170, y + 161, i, rsinterface);
        i++;
        setBounds(26011, x + 170, y + 134, i, rsinterface);
        i++;
        setBounds(26011, x + 170, y + 105, i, rsinterface);
        i++;
        // third row vertical line
        setBounds(26011, x + 312, y + 105, i, rsinterface);
        i++;
        setBounds(26011, x + 312, y + 134, i, rsinterface);
        i++;
        setBounds(26011, x + 312, y + 161, i, rsinterface);
        i++;
        // fourth row vertical line
        setBounds(26011, x + 331, y + 105, i, rsinterface);
        i++;
        setBounds(26011, x + 331, y + 134, i, rsinterface);
        i++;
        setBounds(26011, x + 331, y + 161, i, rsinterface);
        i++;
        /**
         * Fixed
         */
        setBounds(26016, x + 30, y + 38, i, rsinterface);
        i++;
        setBounds(26017, x + 30, y + 38, i, rsinterface);
        i++;
        /**
         * Resizable
         */
        setBounds(26019, x + 110, y + 38, i, rsinterface);
        i++;
        setBounds(26020, x + 110, y + 38, i, rsinterface);
        i++;
        /**
         * Fullscreen
         */
        setBounds(26022, x + 190, y + 38, i, rsinterface);
        i++;
        setBounds(26023, x + 190, y + 38, i, rsinterface);
        i++;
        setBounds(26005, x + 280, y + 130, i, rsinterface);
        i++;
        setBounds(26050, 250, 133, i, rsinterface);
        i++;
        setBounds(26051, 142, 74, i, rsinterface);
        i++;
        setBounds(26052, 220, 74, i, rsinterface);
        i++;
        setBounds(26053, 70, 74, i, rsinterface);
        i++;
        setBounds(26054, 314, 88, i, rsinterface);
        i++;
        setBounds(26055, 314, 88, i, rsinterface);
        i++;
        setBounds(26058, 384, 88, i, rsinterface);
        i++;
        setBounds(26059, 384, 88, i, rsinterface);
        i++;
        setBounds(26062, 316, 75, i, rsinterface);
        i++;
        setBounds(26063, 385, 75, i, rsinterface);
        i++;
        setBounds(26064, 368, 250, i, rsinterface);
        i++;
        setBounds(26066, 40, 250, i, rsinterface);
        i++;
        setBounds(26068, 230, 250, i, rsinterface);

    }

    private void mainGe() {
        RSInterface Interface = addTabInterface(24500);
        setChildren(51, Interface);
        addHoverButton(24541, "", 3, 138, 108, "Abort offer", 0, 24542, 1);
        addHoverButton(24543, "", 3, 138, 108, "View offer", 0, 24544, 1);
        addHoverButton(24545, "", 3, 138, 108, "Abort offer", 0, 24546, 1);
        addHoverButton(24547, "", 3, 138, 108, "View offer", 0, 24548, 1);
        addHoverButton(24549, "", 3, 138, 108, "Abort offer", 0, 24550, 1);
        addHoverButton(24551, "", 3, 138, 108, "View offer", 0, 24552, 1);
        addHoverButton(24553, "", 3, 138, 108, "Abort offer", 0, 24554, 1);
        addHoverButton(24555, "", 3, 138, 108, "View offer", 0, 24556, 1);
        addHoverButton(24557, "", 3, 138, 108, "Abort offer", 0, 24558, 1);
        addHoverButton(24559, "", 3, 138, 108, "View offer", 0, 24560, 1);
        addHoverButton(24561, "", 3, 138, 108, "Abort offer", 0, 24562, 1);
        addHoverButton(24563, "", 3, 138, 108, "View offer", 0, 24564, 1);

        addSprite(1, 24579, -1, "", false);
        addSprite(1, 24580, -1, "", false);
        addSprite(1, 24581, -1, "", false);
        addSprite(1, 24582, -1, "", false);
        addSprite(1, 24583, -1, "", false);
        addSprite(1, 24584, -1, "", false);

        addHDSprite(24501, 1107, 1107);
        addHoverButton(24502, CLOSE_BUTTON, CLOSE_BUTTON, 21, 21, "Close", 250,
                24503, 3);
        addHoveredButton(24503, CLOSE_BUTTON_HOVER, CLOSE_BUTTON_HOVER, 21, 21,
                24504);
        addHoverButton(24505, 1108, 1108, 50, 50, "Buy", 0, 24506, 1);
        addHoveredButton(24506, 1110, 1110, 50, 50, 24507);
        addHoverButton(24508, 1108, 1108, 50, 50, "Buy", 0, 24509, 1);
        addHoveredButton(24509, 1110, 1110, 50, 50, 24510);
        addHoverButton(24514, 1108, 1108, 50, 50, "Buy", 0, 24515, 1);
        addHoveredButton(24515, 1110, 1110, 50, 50, 24516);
        addHoverButton(24517, 1108, 1108, 50, 50, "Buy", 0, 24518, 1);
        addHoveredButton(24518, 1110, 1110, 50, 50, 24519);
        addHoverButton(24520, 1108, 1108, 50, 50, "Buy", 0, 24521, 1);
        addHoveredButton(24521, 1110, 1110, 50, 50, 24522);
        addHoverButton(24523, 1108, 1108, 50, 50, "Buy", 0, 24524, 1);
        addHoveredButton(24524, 1110, 1110, 50, 50, 24525);
        addHoverButton(24511, 1109, 1109, 50, 50, "Sell", 0, 24512, 1);
        addHoveredButton(24512, 1111, 1111, 50, 50, 24513);
        addHoverButton(24526, 1109, 1109, 50, 50, "Sell", 0, 24527, 1);
        addHoveredButton(24527, 1111, 1111, 50, 50, 24528);
        addHoverButton(24529, 1109, 1109, 50, 50, "Sell", 0, 24530, 1);
        addHoveredButton(24530, 1111, 1111, 50, 50, 24531);
        addHoverButton(24532, 1109, 1109, 50, 50, "Sell", 0, 24533, 1);
        addHoveredButton(24533, 1111, 1111, 50, 50, 24534);
        addHoverButton(24535, 1109, 1109, 50, 50, "Sell", 0, 24536, 1);
        addHoveredButton(24536, 1111, 1111, 50, 50, 24537);
        addHoverButton(24538, 1109, 1109, 50, 50, "Sell", 0, 24539, 1);
        addHoveredButton(24539, 1111, 1111, 50, 50, 24540);

        RSInterface add = addInterface(24567);
        addToItemGroup(add, 1, 1, 24, 24, true, "[GE]", "[GE]", "[GE]");
        add = addInterface(24569);
        addToItemGroup(add, 1, 1, 24, 24, true, "[GE]", "[GE]", "[GE]");
        add = addInterface(24571);
        addToItemGroup(add, 1, 1, 24, 24, true, "[GE]", "[GE]", "[GE]");
        add = addInterface(24573);
        addToItemGroup(add, 1, 1, 24, 24, true, "[GE]", "[GE]", "[GE]");
        add = addInterface(24575);
        addToItemGroup(add, 1, 1, 24, 24, true, "[GE]", "[GE]", "[GE]");
        add = addInterface(24577);
        addToItemGroup(add, 1, 1, 24, 24, true, "[GE]", "[GE]", "[GE]");

        setBounds(24541, 30, 74, 0, Interface);
        setBounds(24543, 30, 74, 1, Interface);
        setBounds(24545, 186, 74, 2, Interface);
        setBounds(24547, 186, 74, 3, Interface);
        setBounds(24549, 342, 74, 4, Interface);
        setBounds(24551, 342, 74, 5, Interface);
        setBounds(24553, 30, 194, 6, Interface);
        setBounds(24555, 30, 194, 7, Interface);
        setBounds(24557, 186, 194, 8, Interface);
        setBounds(24559, 186, 194, 9, Interface);
        setBounds(24561, 339, 194, 10, Interface);
        setBounds(24563, 339, 194, 11, Interface);
        setBounds(24501, 4, 23, 12, Interface);
        setBounds(24579, 30 + 6, 74 + 30, 13, Interface);
        setBounds(24580, 186 + 6, 74 + 30, 14, Interface);
        setBounds(24581, 342 + 6, 74 + 30, 15, Interface);
        setBounds(24582, 30 + 6, 194 + 30, 16, Interface);
        setBounds(24583, 186 + 6, 194 + 30, 17, Interface);
        setBounds(24584, 342 + 6, 194 + 30, 18, Interface);
        setBounds(24502, 480, 32, 19, Interface);
        setBounds(24503, 480, 32, 20, Interface);
        setBounds(24505, 45, 115, 21, Interface);
        setBounds(24506, 45, 106, 22, Interface);
        setBounds(24508, 45, 234, 23, Interface);
        setBounds(24509, 45, 225, 24, Interface);
        setBounds(24511, 105, 115, 25, Interface);
        setBounds(24512, 105, 106, 26, Interface);
        setBounds(24514, 358, 115, 27, Interface);
        setBounds(24515, 358, 106, 28, Interface);
        setBounds(24517, 202, 234, 29, Interface);
        setBounds(24518, 202, 225, 30, Interface);
        setBounds(24520, 358, 234, 31, Interface);
        setBounds(24521, 358, 225, 32, Interface);
        setBounds(24523, 202, 115, 33, Interface);
        setBounds(24524, 202, 106, 34, Interface);
        setBounds(24526, 261, 115, 35, Interface);
        setBounds(24527, 261, 106, 36, Interface);
        setBounds(24529, 417, 115, 37, Interface);
        setBounds(24530, 417, 106, 38, Interface);
        setBounds(24532, 105, 234, 39, Interface);
        setBounds(24533, 105, 225, 40, Interface);
        setBounds(24535, 261, 234, 41, Interface);
        setBounds(24536, 261, 225, 42, Interface);
        setBounds(24538, 417, 234, 43, Interface);
        setBounds(24539, 417, 225, 44, Interface);

        setBounds(24567, 39, 106, 45, Interface);
        setBounds(24569, 46 + 156 - 7, 114 - 7, 46, Interface);
        setBounds(24571, 46 + 156 + 156 - 7, 114 - 7, 47, Interface);
        setBounds(24573, 39, 234 - 7, 48, Interface);
        setBounds(24575, 46 + 156 - 7, 234 - 7, 49, Interface);
        setBounds(24577, 46 + 156 + 156 - 7, 234 - 7, 50, Interface);
        for (int i = 0; i < 7; i++) {
            addInterface(32000 + i);
            interfaceCache[32000 + i].message = "";
            addInterface(33000 + i);
            interfaceCache[33000 + i].message = "";
            addInterface(33100 + i);
            interfaceCache[33100 + i].message = "";
        }
    }

    private void buyGe() {
        RSInterface rsinterface = addTabInterface(24600);
        int x = 9;
        addHDSprite(24601, 1112, 1112);
        addHoverButton(24602, CLOSE_BUTTON, CLOSE_BUTTON, 16, 16, "Close", 0,
                24603, 1);
        addHoveredButton(24603, CLOSE_BUTTON_HOVER, CLOSE_BUTTON_HOVER, 16, 16,
                24604);
        addHoverButton(24606, 1113, 1113, 13, 13, "Decrease Quantity", 0, 24607,
                1);
        addHoveredButton(24607, 1114, 1114, 13, 13, 24608);
        addHoverButton(24610, 1115, 1115, 13, 13, "Increase Quantity", 0, 24611,
                1);
        addHoveredButton(24611, 1116, 1116, 13, 13, 24612);
        addHoverButton(24614, 1117, 1117, 35, 25, "Add 1", 0, 24615, 1);
        addHoveredButton(24615, 1118, 1118, 35, 25, 24616);
        addHoverButton(24618, 1119, 1119, 35, 25, "Add 10", 0, 24619, 1);
        addHoveredButton(24619, 1120, 1120, 35, 25, 24620);
        addHoverButton(24622, 1121, 1121, 35, 25, "Add 100", 0, 24623, 1);
        addHoveredButton(24623, 1122, 1122, 35, 25, 24624);
        addHoverButton(24626, 1125, 1125, 35, 25, "Add 1000", 0, 24627, 1);
        addHoveredButton(24627, 1126, 1126, 35, 25, 24628);
        addHoverButton(24630, 1127, 1127, 35, 25, "Edit Quantity", -1, 24631, 1);
        addHoveredButton(24631, 1128, 1128, 35, 25, 24632);
        addHoverButton(24634, 1129, 1129, 35, 25, "Decrease Price", 0, 24635, 1);
        addHoveredButton(24635, 1130, 1130, 35, 25, 24636);
        addHoverButton(24638, 1131, 1131, 35, 25, "Offer Guild Price", 0, 24639,
                1);
        addHoveredButton(24639, 1132, 1132, 35, 25, 24640);
        addHoverButton(24642, 1127, 1127, 35, 25, "Edit Price", -1, 24643, 1);
        addHoveredButton(24643, 1128, 1128, 35, 25, 24644);
        addHoverButton(24646, 1133, 1133, 35, 25, "Increase Price", 0, 24647, 1);
        addHoveredButton(24647, 1134, 1134, 35, 25, 24648);
        addHoverButton(24650, 1135, 1135, 120, 43, "Confirm Offer", 0, 24651, 1);
        addHoveredButton(24651, 1136, 1136, 120, 43, 24652);

        addHoverButton(24654, 1137, 1137, 40, 36, "Search", 0, 24655, 1);
        addHoveredButton(24655, 1138, 1138, 40, 36, 24656);

        addHoverButton(24658, 1139, 1139, 29, 23, "Back", 0, 24659, 1);
        addHoveredButton(24659, 1140, 1140, 29, 23, 24660);
        addHoverButton(24662, 1113, 1113, 13, 13, "Decrease Price", 0, 24663, 1);
        addHoveredButton(24663, 1114, 1114, 13, 13, 24664);
        addHoverButton(24665, 1115, 1115, 13, 13, "Increase Price", 0, 24666, 1);
        addHoveredButton(24666, 1116, 1116, 13, 13, 24667);
        addText(24669, "Choose an item to exchange", tda, 0, 0x96731A, false,
                true);
        addText(24670, "Click the icon to the left to search for items.", tda,
                0, 0x958E60, false, true);
        addText(24671, "0", tda, 0, 0xB58338, true, true);
        addText(24672, "1 gp", tda, 0, 0xB58338, true, true);
        addText(24673, "0 gp", tda, 0, 0xB58338, true, true);
        //RSInterface add = addInterface(24680);

        addItemOnInterface(24680, 3323, new String[]{null});
        //addToItemGroup(add, 1, 1, 24, 24, true, "[GE]", "[GE]", "[GE]");


        addText(24682, "N/A", tda, 0, 0xB58338, false, true);
        rsinterface.totalChildren(42);
        rsinterface.child(0, 24601, 4 + x, 23);
        rsinterface.child(1, 24602, 464 + x, 33);
        rsinterface.child(2, 24603, 464 + x, 33);
        rsinterface.child(3, 24606, 46 + x, 184);
        rsinterface.child(4, 24607, 46 + x, 184);
        rsinterface.child(5, 24610, 226 + x, 184);
        rsinterface.child(6, 24611, 226 + x, 184);
        rsinterface.child(7, 24614, 43 + x, 208);
        rsinterface.child(8, 24615, 43 + x, 208);
        rsinterface.child(9, 24618, 84 + x, 208);
        rsinterface.child(10, 24619, 84 + x, 208);
        rsinterface.child(11, 24622, 125 + x, 208);
        rsinterface.child(12, 24623, 125 + x, 208);
        rsinterface.child(13, 24626, 166 + x, 208);
        rsinterface.child(14, 24627, 166 + x, 208);
        rsinterface.child(15, 24630, 207 + x, 208);
        rsinterface.child(16, 24631, 207 + x, 208);
        rsinterface.child(17, 24634, 260 + x, 208);
        rsinterface.child(18, 24635, 260 + x, 208);
        rsinterface.child(19, 24638, 316 + x, 208);
        rsinterface.child(20, 24639, 316 + x, 208);
        rsinterface.child(21, 24642, 357 + x, 208);
        rsinterface.child(22, 24643, 357 + x, 208);
        rsinterface.child(23, 24646, 413 + x, 208);
        rsinterface.child(24, 24647, 413 + x, 208);
        rsinterface.child(25, 24650, 191 + x, 273);
        rsinterface.child(26, 24651, 191 + x, 273);
        rsinterface.child(27, 24654, 93 + x, 95);
        rsinterface.child(28, 24655, 93 + x, 95);
        rsinterface.child(29, 24658, 19 + x, 284);
        rsinterface.child(30, 24659, 19 + x, 284);
        rsinterface.child(31, 24662, 260 + x, 184);
        rsinterface.child(32, 24663, 260 + x, 184);
        rsinterface.child(33, 24665, 435 + x, 184);
        rsinterface.child(34, 24666, 435 + x, 184);
        rsinterface.child(35, 24669, 202 + x, 71);
        rsinterface.child(36, 24670, 202 + x, 98);
        rsinterface.child(37, 24671, 142 + x, 185);
        rsinterface.child(38, 24672, 354 + x, 185);
        rsinterface.child(39, 24673, 252 + x, 246);
        rsinterface.child(40, 24680, 97 + x, 97);
        rsinterface.child(41, 24682, 121, 136);
    }

    private void sellGe() {
        RSInterface rsinterface = addTabInterface(24700);
        int x = 9;
        addHDSprite(24701, 1141, 1141);
        addHoverButton(24702, CLOSE_BUTTON, CLOSE_BUTTON_HOVER, 16, 16,
                "Close", 0, 24703, 1);
        addHoveredButton(24703, CLOSE_BUTTON_HOVER, CLOSE_BUTTON_HOVER, 16, 16,
                24704);
        addHoverButton(24706, 1113, 1113, 13, 13, "Decrease Quantity", 0, 24707,
                1);
        addHoveredButton(24707, 1114, 1114, 13, 13, 24708);
        addHoverButton(24710, 1115, 1115, 13, 13, "Increase Quantity", 0, 24711,
                1);
        addHoveredButton(24711, 1116, 1116, 13, 13, 24712);
        addHoverButton(24714, 1117, 1117, 35, 25, "Sell 1", 0, 24715, 1);
        addHoveredButton(24715, 1118, 1118, 35, 25, 24716);
        addHoverButton(24718, 1119, 1119, 35, 25, "Sell 10", 0, 24719, 1);
        addHoveredButton(24719, 1120, 1120, 35, 25, 24720);
        addHoverButton(24722, 1121, 1121, 35, 25, "Sell 100", 0, 24723, 1);
        addHoveredButton(24723, 1122, 1122, 35, 25, 24724);
        addHoverButton(24726, 1123, 1123, 35, 25, "Sell All", 0, 24727, 1);
        addHoveredButton(24727, 1124, 1124, 35, 25, 24728);
        addHoverButton(24730, 1127, 1127, 35, 25, "Edit Quantity", -1, 24731, 1);
        addHoveredButton(24731, 1128, 1128, 35, 25, 24732);
        addHoverButton(24734, 1129, 1129, 35, 25, "Decrease Price", 0, 24735, 1);
        addHoveredButton(24735, 1130, 1130, 35, 25, 24736);
        addHoverButton(24738, 1131, 1131, 35, 25, "Offer Guild Price", 0, 24739,
                1);
        addHoveredButton(24739, 1132, 1132, 35, 25, 24740);
        addHoverButton(24742, 1127, 1127, 35, 25, "Edit Price", -1, 24743, 1);
        addHoveredButton(24743, 1128, 1128, 35, 25, 24744);
        addHoverButton(24746, 1133, 1133, 35, 25, "Increase Price", 0, 24747, 1);
        addHoveredButton(24747, 1134, 1134, 35, 25, 24748);
        addHoverButton(24750, 1135, 1135, 120, 43, "Confirm Offer", 0, 24751, 1);
        addHoveredButton(24751, 1136, 1136, 120, 43, 24752);
        addHoverButton(24758, 1139, 1139, 29, 23, "Back", 0, 24759, 1);
        addHoveredButton(24759, 1140, 1140, 29, 23, 24760);
        addHoverButton(24762, 1113, 1113, 13, 13, "Decrease Price", 0, 24763, 1);
        addHoveredButton(24763, 1114, 1114, 13, 13, 24764);
        addHoverButton(24765, 1115, 1115, 13, 13, "Increase Price", 0, 24766, 1);
        addHoveredButton(24766, 1116, 1116, 13, 13, 24767);
        addText(24769, "Choose an item to exchange", tda, 0, 0x96731A, false,
                true);
        addText(24770, "Select an item from your inventory to sell.", tda, 0,
                0x958E60, false, true);
        addText(24771, "0", tda, 0, 0xB58338, true, true);
        addText(24772, "1 gp", tda, 0, 0xB58338, true, true);
        addText(24773, "0 gp", tda, 0, 0xB58338, true, true);
        addItemOnInterface(24780, 3323, new String[]{null});
        addText(24782, "N/A", tda, 0, 0xB58338, false, true);
        rsinterface.totalChildren(40);
        rsinterface.child(0, 24701, 4 + x, 23);
        rsinterface.child(1, 24702, 464 + x, 33);
        rsinterface.child(2, 24703, 464 + x, 33);
        rsinterface.child(3, 24706, 46 + x, 184);
        rsinterface.child(4, 24707, 46 + x, 184);
        rsinterface.child(5, 24710, 226 + x, 184);
        rsinterface.child(6, 24711, 226 + x, 184);
        rsinterface.child(7, 24714, 43 + x, 208);
        rsinterface.child(8, 24715, 43 + x, 208);
        rsinterface.child(9, 24718, 84 + x, 208);
        rsinterface.child(10, 24719, 84 + x, 208);
        rsinterface.child(11, 24722, 125 + x, 208);
        rsinterface.child(12, 24723, 125 + x, 208);
        rsinterface.child(13, 24726, 166 + x, 208);
        rsinterface.child(14, 24727, 166 + x, 208);
        rsinterface.child(15, 24730, 207 + x, 208);
        rsinterface.child(16, 24731, 207 + x, 208);
        rsinterface.child(17, 24734, 260 + x, 208);
        rsinterface.child(18, 24735, 260 + x, 208);
        rsinterface.child(19, 24738, 316 + x, 208);
        rsinterface.child(20, 24739, 316 + x, 208);
        rsinterface.child(21, 24742, 357 + x, 208);
        rsinterface.child(22, 24743, 357 + x, 208);
        rsinterface.child(23, 24746, 413 + x, 208);
        rsinterface.child(24, 24747, 413 + x, 208);
        rsinterface.child(25, 24750, 191 + x, 273);
        rsinterface.child(26, 24751, 191 + x, 273);
        rsinterface.child(27, 24758, 19 + x, 284);
        rsinterface.child(28, 24759, 19 + x, 284);
        rsinterface.child(29, 24762, 260 + x, 184);
        rsinterface.child(30, 24763, 260 + x, 184);
        rsinterface.child(31, 24765, 435 + x, 184);
        rsinterface.child(32, 24766, 435 + x, 184);
        rsinterface.child(33, 24769, 202 + x, 71);
        rsinterface.child(34, 24770, 202 + x, 98);
        rsinterface.child(35, 24771, 142 + x, 185);
        rsinterface.child(36, 24772, 354 + x, 185);
        rsinterface.child(37, 24773, 252 + x, 246);
        rsinterface.child(38, 24780, 97 + x, 97);
        rsinterface.child(39, 24782, 121, 136);
    }

    private void collectSellGe() {
        RSInterface rsinterface = addTabInterface(54700);
        int x = 9;
        addHDSprite(54701, 1142, 1142);

        addHoverButton(54702, CLOSE_BUTTON, CLOSE_BUTTON, 16, 16, "Close", 0,
                54703, 1);
        addHoveredButton(54703, CLOSE_BUTTON_HOVER, CLOSE_BUTTON_HOVER, 16, 16,
                54704);
        addHoverButton(54758, 1139, 1139, 29, 23, "Back", 0, 54759, 1);
        addHoveredButton(54759, 1140, 1140, 29, 23, 54760);
        addText(54769, "Choose an item to exchange", tda, 0, 0x96731A, false,
                true);
        addText(54770, "Select an item from your invertory to sell.", tda, 0,
                0x958E60, false, true);
        addText(54771, "0", tda, 0, 0xB58338, true, true);
        addText(54772, "1 gp", tda, 0, 0xB58338, true, true);
        addText(54773, "0 gp", tda, 0, 0xB58338, true, true);
        addHoverButton(54793, 1144, 1144, 40, 36, "[GE]", 0, 54794, 1);
        addHoveredButton(54794, 1143, 1143, 40, 36, 54795);
        addHoverButton(54796, 1144, 1144, 40, 36, "[GE]", 0, 54797, 1);
        addHoveredButton(54797, 1143, 1143, 40, 36, 54798);
        addItemOnInterface(54780, 2903, new String[]{null});
        addItemOnInterface(54781, 2903, new String[]{"Collect"});
        addItemOnInterface(54782, 2904, new String[]{"Collect"});
        addText(54784, "", tda, 0, 0xFFFF00, false, true);
        addText(54785, "", tda, 0, 0xFFFF00, false, true);
        addText(54787, "N/A", tda, 0, 0xB58338, false, true);
        addText(54788, "", tda, 0, 0xFFFF00, true, true);
        addText(54789, "", tda, 0, 0xFFFF00, true, true);
        addHoverButton(54800, 1145, 1145, 20, 20, "Abort offer", 0, 54801, 1);
        addHoveredButton(54801, 1146, 1146, 20, 20, 54802);
        rsinterface.totalChildren(24);
        rsinterface.child(0, 54701, 4 + x, 23);// 385, 260
        rsinterface.child(1, 54702, 464 + x, 33);// 435, 260
        rsinterface.child(2, 54703, 464 + x, 33);
        rsinterface.child(3, 54758, 19 + x, 284);
        rsinterface.child(4, 54759, 19 + x, 284);
        rsinterface.child(5, 54769, 202 + x, 71);
        rsinterface.child(6, 54770, 202 + x, 98);
        rsinterface.child(7, 54771, 142 + x, 185);
        rsinterface.child(8, 54772, 354 + x, 185);
        rsinterface.child(9, 54773, 252 + x, 246);
        rsinterface.child(10, 54793, 386 + x, 256 + 23);
        rsinterface.child(11, 54794, 386 + x, 256 + 23);
        rsinterface.child(12, 54796, 435 + x, 256 + 23);
        rsinterface.child(13, 54797, 435 + x, 256 + 23);
        rsinterface.child(14, 54780, 97 + x, 97);
        rsinterface.child(15, 54781, 385 + 4 + x, 260 + 23);
        rsinterface.child(16, 54782, 435 + 4 + x, 260 + 23);
        rsinterface.child(17, 54784, 385 + 4 + x, 260 + 23);
        rsinterface.child(18, 54785, 435 + 4 + x, 260 + 23);
        rsinterface.child(19, 54787, 108, 136);
        rsinterface.child(20, 54788, 214 + x, 249 + 23);
        rsinterface.child(21, 54789, 214 + x, 263 + 23);
        rsinterface.child(22, 54800, 345 + x, 250 + 23);
        rsinterface.child(23, 54801, 345 + x, 250 + 23);
    }

    private void collectBuyGe() {
        RSInterface rsinterface = addTabInterface(53700);
        int x = 9;
        addHDSprite(53701, 1147, 1147);
        addHoverButton(53702, CLOSE_BUTTON, CLOSE_BUTTON, 16, 16, "Close", 0,
                53703, 1);
        addHoveredButton(53703, CLOSE_BUTTON_HOVER, CLOSE_BUTTON_HOVER, 16, 16,
                53704);
        addHoverButton(53758, 1139, 1139, 29, 23, "Back", 0, 53759, 1);
        addHoveredButton(53759, 1140, 1140, 29, 23, 53760);
        addText(53769, "Choose an item to exchange", tda, 0, 0x96731A, false,
                true);
        addText(53770, "Select an item from your invertory to sell.", tda, 0,
                0x958E60, false, true);
        addText(53771, "0", tda, 0, 0xB58338, true, true);
        addText(53772, "1 gp", tda, 0, 0xB58338, true, true);
        addText(53773, "0 gp", tda, 0, 0xB58338, true, true);
        addHoverButton(53793, 1144, 1144, 40, 36, "[GE]", 0, 53794, 1);
        addHoveredButton(53794, 1143, 1143, 40, 36, 53795);
        addHoverButton(53796, 1144, 1144, 40, 36, "[GE]", 0, 53797, 1);
        addHoveredButton(53797, 1143, 1143, 40, 36, 53798);
        addItemOnInterface(53780, 2901, new String[]{null});
        addItemOnInterface(53781, 2901, new String[]{"Collect"});
        addItemOnInterface(53782, 2902, new String[]{"Collect"});
        addText(53784, "", tda, 0, 0xFFFF00, false, true);
        addText(53785, "", tda, 0, 0xFFFF00, false, true);
        addText(53787, "N/A", tda, 0, 0xB58338, false, true);
        addText(53788, "", tda, 0, 0xFFFF00, true, true);
        addText(53789, "", tda, 0, 0xFFFF00, true, true);
        addHoverButton(53800, 1145, 1145, 20, 20, "Abort offer", 0, 53801, 1);
        addHoveredButton(53801, 1146, 1146, 20, 20, 53802);
        rsinterface.totalChildren(24);
        rsinterface.child(0, 53701, 4 + x, 23);// 385, 260
        rsinterface.child(1, 53702, 464 + x, 33);// 435, 260
        rsinterface.child(2, 53703, 464 + x, 33);
        rsinterface.child(3, 53758, 19 + x, 284);
        rsinterface.child(4, 53759, 19 + x, 284);
        rsinterface.child(5, 53769, 202 + x, 71);
        rsinterface.child(6, 53770, 202 + x, 98);
        rsinterface.child(7, 53771, 142 + x, 185);
        rsinterface.child(8, 53772, 354 + x, 185);
        rsinterface.child(9, 53773, 252 + x, 246);
        rsinterface.child(10, 53793, 386 + x, 256 + 23);
        rsinterface.child(11, 53794, 386 + x, 256 + 23);
        rsinterface.child(12, 53796, 435 + x, 256 + 23);
        rsinterface.child(13, 53797, 435 + x, 256 + 23);
        rsinterface.child(14, 53780, 97 + x, 97);
        rsinterface.child(15, 53781, 385 + 4 + x, 260 + 23);
        rsinterface.child(16, 53782, 435 + 4 + x, 260 + 23);
        rsinterface.child(17, 53784, 385 + 4 + x, 260 + 23);
        rsinterface.child(18, 53785, 435 + 4 + x, 260 + 23);
        rsinterface.child(19, 53787, 108, 136);
        rsinterface.child(20, 53788, 214 + x, 249 + 23);
        rsinterface.child(21, 53789, 214 + x, 263 + 23);
        rsinterface.child(22, 53800, 345 + x, 250 + 23);
        rsinterface.child(23, 53801, 345 + x, 250 + 23);
    }

    public void buildPlayerMenu(ArrayList<Account> accountList) {
        RSInterface rsi = addTabInterface(31000);
        setChildren(accountList.size(), rsi);
        int interId = 31001;
        int frame = 0;
        int x = 157, y = 306;
        for (Account a_ : accountList) {
            addAPlayerHead(interId, a_);
            setBounds(interId, x - 20, y - 10, frame, rsi);
            frame++;
            interId++;

            x += 50;//Configuration.CHARACTERS_SEPARATOR_WIDTH;
        }
    }

    private void quickPrayersInterface() {
        int frame = 0;
        RSInterface tab = addTabInterface(17200);
        addSpriteLoader(17201, 1188);
        addText(17230, "Select your quick prayers:", tda, 0, 0xff981f, false, true);
        addTransparentSpriteWSpriteLoader(17229, 1189, 50);
        int i = 17202;
        for (int j = 630; j <= 659; j++) {
            addConfigButtonWSpriteLoader(i, 17200, 1185, 1184, 14, 15, "Select", 0, 1, j);
            i += i == 17229 ? 50 : 1;
        }

        addHoverButtonWSpriteLoader(17231, 1186, 190, 24, "Confirm Selection", -1, 17232, 1);
        addHoveredImageWSpriteLoader(17232, 1187, 190, 24, 17233);

        setChildren(62, tab);
        setBounds(25001, 5, 28, frame++, tab);
        setBounds(25003, 44, 28, frame++, tab);
        setBounds(25005, 79, 31, frame++, tab);
        setBounds(25007, 116, 30, frame++, tab);
        setBounds(25009, 153, 29, frame++, tab);
        setBounds(25011, 5, 68, frame++, tab);
        setBounds(25013, 44, 67, frame++, tab);
        setBounds(25015, 79, 69, frame++, tab);
        setBounds(25017, 116, 70, frame++, tab);
        setBounds(25019, 154, 70, frame++, tab);
        setBounds(25021, 4, 104, frame++, tab);
        setBounds(25023, 44, 107, frame++, tab);
        setBounds(25025, 81, 105, frame++, tab);
        setBounds(25027, 117, 105, frame++, tab);
        setBounds(25029, 156, 107, frame++, tab);
        setBounds(25031, 5, 145, frame++, tab);
        setBounds(25033, 43, 144, frame++, tab);
        setBounds(25035, 83, 144, frame++, tab);
        setBounds(25037, 115, 141, frame++, tab);
        setBounds(25039, 154, 144, frame++, tab);
        setBounds(25041, 5, 180, frame++, tab);
        setBounds(25043, 41, 178, frame++, tab);
        setBounds(25045, 79, 183, frame++, tab);
        setBounds(25047, 116, 178, frame++, tab);
        setBounds(25049, 161, 180, frame++, tab);
        setBounds(25051, 4, 219, frame++, tab);
        setBounds(25105, 44, 214, frame++, tab);
        setBounds(25109, 80, 214, frame++, tab);
        setBounds(17229, 0, 25, frame++, tab);
        setBounds(17201, 0, 22, frame++, tab);
        setBounds(17201, 0, 237, frame++, tab);
        setBounds(17202, 2, 25, frame++, tab);
        setBounds(17203, 41, 25, frame++, tab);
        setBounds(17204, 76, 25, frame++, tab);
        setBounds(17205, 113, 25, frame++, tab);
        setBounds(17206, 150, 25, frame++, tab);
        setBounds(17207, 2, 65, frame++, tab);
        setBounds(17208, 41, 65, frame++, tab);
        setBounds(17209, 76, 65, frame++, tab);
        setBounds(17210, 113, 65, frame++, tab);
        setBounds(17211, 150, 65, frame++, tab);
        setBounds(17212, 2, 102, frame++, tab);
        setBounds(17213, 41, 102, frame++, tab);
        setBounds(17214, 76, 102, frame++, tab);
        setBounds(17215, 113, 102, frame++, tab);
        setBounds(17216, 150, 102, frame++, tab);
        setBounds(17217, 2, 141, frame++, tab);
        setBounds(17218, 41, 141, frame++, tab);
        setBounds(17219, 76, 141, frame++, tab);
        setBounds(17220, 113, 141, frame++, tab);
        setBounds(17221, 150, 141, frame++, tab);
        setBounds(17222, 2, 177, frame++, tab);
        setBounds(17223, 41, 177, frame++, tab);
        setBounds(17224, 76, 177, frame++, tab);
        setBounds(17225, 113, 177, frame++, tab);
        setBounds(17226, 150, 177, frame++, tab);
        setBounds(17227, 1, 211, frame++, tab);
        setBounds(17230, 5, 5, frame++, tab);
        setBounds(17231, 0, 237, frame++, tab);
        setBounds(17232, 0, 237, frame++, tab);
        setBounds(17279, 41, 211, frame++, tab);
        setBounds(17280, 76, 211, frame++, tab);
    }

    private void quickCursesInterface() {
        int frame = 0;
        RSInterface tab = addTabInterface(17234);
        addText(17235, "Select your quick curses:", tda, 0, 0xff981f, false,
                true);
        int i = 17202;
        for (int j = 630; i <= 17222 || j <= 656; j++) {
            addConfigButtonWSpriteLoader(i, 17200, 1185, 1184, 14, 15, "Select", 0, 1, j);
            i++;
        }

        addHoverButtonWSpriteLoader(17231, 1186, 190, 24, "Confirm Selection", -1, 17232, 1);
        addHoveredImageWSpriteLoader(17232, 1187, 190, 24, 17233);


        setChildren(46, tab);
        setBounds(32504, 5, 8 + 17, frame++, tab);
        setBounds(32506, 44, 8 + 20, frame++, tab);
        setBounds(32508, 79, 11 + 19, frame++, tab);
        setBounds(32510, 116, 10 + 18, frame++, tab);
        setBounds(32512, 153, 9 + 20, frame++, tab);
        setBounds(32514, 5, 48 + 18, frame++, tab);
        setBounds(32516, 44, 47 + 21, frame++, tab);
        setBounds(32518, 79, 49 + 20, frame++, tab);
        setBounds(32520, 116, 50 + 19, frame++, tab);
        setBounds(32522, 154, 50 + 20, frame++, tab);
        setBounds(32524, 4, 84 + 21, frame++, tab);
        setBounds(32526, 44, 87 + 19, frame++, tab);
        setBounds(32528, 81, 85 + 20, frame++, tab);
        setBounds(32530, 117, 85 + 20, frame++, tab);
        setBounds(32532, 156, 87 + 18, frame++, tab);
        setBounds(32534, 5, 125 + 19, frame++, tab);
        setBounds(32536, 43, 124 + 19, frame++, tab);
        setBounds(32538, 83, 124 + 20, frame++, tab);
        setBounds(32540, 115, 125 + 21, frame++, tab);
        setBounds(32542, 154, 126 + 22, frame++, tab);
        setBounds(17229, 0, 25, frame++, tab);
        setBounds(17201, 0, 22, frame++, tab);
        setBounds(17201, 0, 237, frame++, tab);
        setBounds(17202, 2, 25, frame++, tab);
        setBounds(17203, 41, 25, frame++, tab);
        setBounds(17204, 76, 25, frame++, tab);
        setBounds(17205, 113, 25, frame++, tab);
        setBounds(17206, 150, 25, frame++, tab);
        setBounds(17207, 2, 65, frame++, tab);
        setBounds(17208, 41, 65, frame++, tab);
        setBounds(17209, 76, 65, frame++, tab);
        setBounds(17210, 113, 65, frame++, tab);
        setBounds(17211, 150, 65, frame++, tab);
        setBounds(17212, 2, 102, frame++, tab);
        setBounds(17213, 41, 102, frame++, tab);
        setBounds(17214, 76, 102, frame++, tab);
        setBounds(17215, 113, 102, frame++, tab);
        setBounds(17216, 150, 102, frame++, tab);
        setBounds(17217, 2, 141, frame++, tab);
        setBounds(17218, 41, 141, frame++, tab);
        setBounds(17219, 76, 141, frame++, tab);
        setBounds(17220, 113, 141, frame++, tab);
        setBounds(17221, 150, 141, frame++, tab);
        setBounds(17235, 5, 5, frame++, tab);
        setBounds(17231, 0, 237, frame++, tab);
        setBounds(17232, 0, 237, frame++, tab);
    }


    public static void panelInterfaceInterfaces() {
        int interID = 111500;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 0;
        int y = 0;
        tab.totalChildren(5);

        addSpriteLoader(id, 1427);
        tab.child(c++, id++, 0 + x, 0 + y);

        tab.child(c++, 111100, 0 + x, 0 + y);

        addText(id, "Interfaces", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 95 + x, 27 + y);

        tab.child(c++, 111600, 3 + x, 44 + y);

        addSpriteLoader(id, 1435);
        tab.child(c++, id++, 3 + x, 44 + y);

        interID = 111600;
        RSInterface info = addInterface(interID);
        info.width = 168;
        info.height = 213;
        info.scrollMax = (17 * 17) + 7;
        id = interID + 1;
        c = 0;
        x = 0;
        y = 7;
        info.totalChildren(17);

        for (int i = 0; i < 17; i++) {
            teleportText(id, "", "Select", fonts, 1, 0xFF9900, true, true, 169, 17);
            info.child(c++, id++, 0 + x, 0 + y);
            y += 17;
        }
    }

    public static void raids() {
        int interID = 111700;
        RSInterface tab = addInterface(interID);
        int c = 0;
        int x = 0;
        int y = 0;
        tab.totalChildren(10);
        int id = interID + 1;

        tab.child(c++, 111100, 0 + x, 0 + y);

        y += 15;
        addSpriteLoader(id, 1438);
        tab.child(c++, id++, 8 + x, 35 + y);


        addText(id, "Raiding Party: 0", fonts, 2, 16750623, true, true);
        tab.child(c++, id++, 95 + x, 14 + y);


        tab.child(c++, 111715, 2 + x, 47 + y);

        addHoverButtonWSpriteLoader(id, 1436, 72, 24, "Invite", -1, id + 1, 1);
        tab.child(c++, id++, 20 + x, 200 + y);
        addHoveredImageWSpriteLoader(id, 1437, 72, 24, id + 1);
        tab.child(c++, id++, 20 + x, 200 + y);
        id++;

        addHoverButtonWSpriteLoader(id, 1436, 72, 24, "Leave", -1, id + 1, 1);
        tab.child(c++, id++, 100 + x, 200 + y);
        addHoveredImageWSpriteLoader(id, 1437, 72, 24, id + 1);
        tab.child(c++, id++, 100 + x, 200 + y);
        id++;

        addText(id, "Create", tda, 1, 0xFFA500, true, true);
        tab.child(c++, id++, 55 + x, 205 + y);
        addText(id, "Leave", tda, 1, 0xFFA500, true, true);
        tab.child(c++, id++, 135 + x, 205 + y);

        RSInterface scroll = addInterface(111715);

        scroll.totalChildren(36);
        scroll.width = 164;
        scroll.height = 132;
        scroll.scrollMax = 133;
        y = 0;
        c = 0;
        id = 111716;
        for (int i = 0; i < 12; i++) {
            hoverText(id, "---", "Kick", tda, 0, 0xFFA500, false, true, 100, 11, false);
            // addText(id, "---", tda, 0, 0xFFA500, true, true);
            scroll.child(c++, id++, 13 + x, 0 + y);
            addText(id, "--", tda, 0, 0xFFA500, true, true);
            scroll.child(c++, id++, 120 + x, 0 + y);
            addText(id, "-", tda, 0, 0xFFA500, true, true);
            scroll.child(c++, id++, 152 + x, 0 + y);
            y += 11;
        }
    }

    public static void panelInterfaceAccountInfo() {
        int interID = 111300;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 0;
        int y = 0;
        tab.totalChildren(5);

        addSpriteLoader(id, 1427);
        tab.child(c++, id++, 0 + x, 0 + y);

        tab.child(c++, 111100, 0 + x, 0 + y);

        addText(id, "Account Information", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 95 + x, 27 + y);

        tab.child(c++, 111400, 3 + x, 44 + y);

        addSpriteLoader(id, 1435);
        tab.child(c++, id++, 3 + x, 44 + y);

        interID = 111400;
        RSInterface info = addInterface(interID);
        info.width = 168;
        info.height = 213;
        info.scrollMax =620;
        id = interID + 1;
        c = 0;
        x = -1;
        y = 7;
        info.totalChildren(36);

        addText(id, "Main", tda, 2, 0xFF981F, true, true);
        info.child(c++, id++, 85 + x, 0 + y);
        y += 20;

        for (int i = 0; i < 11; i++) {
            addText(id, "id: " + id, tda, 0, 0xFF981F, false, true);
            info.child(c++, id++, 10 + x, 0 + y);
            y += 17;
        }

        y += 5;
        addText(id, "Slayer", tda, 2, 0xFF981F, true, true);
        info.child(c++, id++, 85 + x, 0 + y);
        y += 20;

        for (int i = 0; i < 8; i++) {
            addText(id, "id: " + id, tda, 0, 0xFF981F, false, true);
            info.child(c++, id++, 10 + x, 0 + y);
            y += 17;
        }

        y += 5;
        addText(id, "Points", tda, 2, 0xFF981F, true, true);
        info.child(c++, id++, 85 + x, 0 + y);
        y += 20;

        for (int i = 0; i < 10; i++) {
            addText(id, "id: " + id, tda, 0, 0xFF981F, false, true);
            info.child(c++, id++, 10 + x, 0 + y);
            y += 17;
        }

        y += 5;
        addText(id, "Quests", tda, 2, 0xFF981F, true, true);
        info.child(c++, id++, 85 + x, 0 + y);
        y += 20;

        for (int i = 0; i < 3; i++) {
            teleportText(id, "", "Select", fonts, 0, 0xFF9900, false, true, 154, 17);
            info.child(c++, id++, 10 + x, 0 + y);
            y += 17;
        }

    }

    public static void panelInterface() {
        int interID = 111000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 0;
        int y = 0;
        tab.totalChildren(5);

        addSpriteLoader(id, 1427);
        tab.child(c++, id++, 0 + x, 0 + y);

        tab.child(c++, 111100, 0 + x, 0 + y);

        addText(id, "World Information", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 95 + x, 27 + y);

        tab.child(c++, 111200, 3 + x, 44 + y);

        addSpriteLoader(id, 1435);
        tab.child(c++, id++, 3 + x, 44 + y);

        interID = 111200;
        RSInterface info = addInterface(interID);
        info.width = 168;
        info.height = 213;
        info.scrollMax = 360;
        id = interID + 1;
        c = 0;
        x = -1;
        y = 7;
        info.totalChildren(19);

        addText(id, "Main", tda, 2, 0xFF981F, true, true);
        info.child(c++, id++, 85 + x, 0 + y);
        y += 20;

        for (int i = 0; i < 2; i++) {
            addText(id, "id: " + id, tda, 0, 0xFF981F, false, true);
            info.child(c++, id++, 10 + x, 0 + y);
            y += 17;
        }


        y += 5;
        addText(id, "Events", tda, 2, 0xFF981F, true, true);
        info.child(c++, id++, 85 + x, 0 + y);
        y += 20;

        for (int i = 0; i < 4; i++) {
            addText(id, "id: " + id, tda, 0, 0xFF981F, false, true);
            info.child(c++, id++, 10 + x, 0 + y);
            y += 17;
        }


        y += 5;
        addText(id, "Globals", tda, 2, 0xFF981F, true, true);
        info.child(c++, id++, 85 + x, 0 + y);
        y += 20;

        for (int i = 0; i < 10; i++) {
            teleportText(id, "", "Teleport", fonts, 0, 0xFF9900, false, true, 154, 17);
            info.child(c++, id++, 10 + x, 0 + y);
            y += 17;
        }


        interID = 111100;
        RSInterface list = addInterface(interID);
        list.width = 190;
        list.height = 44;
        list.scrollMax = 44;
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;
        list.totalChildren(10);

        addConfigButtonWSpriteLoader(id, interID, 1428, 1429, 35, 21, "View World Information", 0, 5, 6000);
        list.child(c++, id++, 6 + x, 3 + y);
        addConfigButtonWSpriteLoader(id, interID, 1428, 1429, 35, 21, "View Account Information", 1, 5, 6000);
        list.child(c++, id++, 41 + x, 3 + y);
        addConfigButtonWSpriteLoader(id, interID, 1428, 1429, 35, 21, "View Interfaces", 2, 5, 6000);
        list.child(c++, id++, 77 + x, 3 + y);
        addConfigButtonWSpriteLoader(id, interID, 1428, 1429, 35, 21, "View Achievements", 3, 5, 6000);
        list.child(c++, id++, 112 + x, 3 + y);
        addConfigButtonWSpriteLoader(id, interID, 1428, 1429, 35, 21, "View Raids", 4, 5, 6000);
        list.child(c++, id++, 148 + x, 3 + y);


        addSpriteLoader(id, 1430);
        list.child(c++, id++, 14 + x, 5 + y);
        addSpriteLoader(id, 1433);
        list.child(c++, id++, 49 + x, 5 + y);
        addSpriteLoader(id, 1432);
        list.child(c++, id++, 85 + x, 5 + y);
        addSpriteLoader(id, 1431);
        list.child(c++, id++, 120 + x, 5 + y);
        addSpriteLoader(id, 1434);
        list.child(c++, id++, 156 + x, 5 + y);


    }


    static void possibleLoot() {
        int interID = 101000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 35;
        int y = 35;
        tab.totalChildren(8);

        addSpriteLoader(id, 1439);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 418 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 418 + x, 3 + y);
        id++;

        addText(id, "Possible Loot", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 239 + x, 4 + y);

        addText(id, "Boxes", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 85 + x, 29 + y);

        addText(id, "Rewards", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 295 + x, 29 + y);

        tab.child(c++, 101100, 11 + x, 47 + y);
        tab.child(c++, 101250, 162 + x, 47 + y);

        interID = 101250;
        RSInterface items = addTabInterface(interID);
        items.totalChildren(350);
        items.height = 208;
        items.width = 269 - 16;
        items.scrollMax = 500;
        id = interID + 1;
        y = 2;
        c = 0;
        x = 1;

        for (int z = 0; z < 50; z++) {
            for (int i = 0; i < 7; i++) {
                dropGroup(id, 1, 1, 1, 1);
                items.child(c++, id++, 0 + x, 0 + y);
                x += 36;
            }
            x = 0;
            y += 35;
        }
        x = 25;
        y = 20;

        interID = 101100;
        RSInterface list = addTabInterface(interID);
        list.totalChildren(125);
        list.height = 208;
        list.width = 144 - 16;
        list.scrollMax = 480;
        id = interID + 1;
        y = 0;
        c = 0;
        for (int i = 0; i < 25; i++) {
            addSpriteLoader(id, 1426);
            list.child(c++, id++, 0, 0 + y);
            y += 80;
        }

        y = 3;
        for (int i = 0; i < 50; i++) {
            dropGroup(id, 1, 1, 1, 1);
            list.child(c++, id++, 1, y);
            addClickableText(id, "Mystery box", "Select", tda, 1, 0xFF8900, false, true, 130);
            list.child(c++, id++, 5, y + 9);
            y += 40;
        }

    }


    public static void progressionInterface() {
        int interID = 112000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 115;
        int y = 270;
        tab.totalChildren(8);

        addTransparentSpriteWSpriteLoader1(id, 1477, 150);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "NPC Name", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 85 + x, 6 + y);

        addText(id, "Next Rewards", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 222 + x, 4 + y);

        new ProgressBar(id, 160, 23, new int[]{0x009a1a}, true, false, "", new int[]{0x9a0000});
        tab.child(c++, id++, 5 + x, 26 + y);

        addText(id, "50% (10/20)", tda, 0, 0xFFFFFF, true, true);
        tab.child(c++, id++, 85 + x, 32 + y);

        dropGroup(id, 1, 1, 1, 1);
        tab.child(c++, id++, 169 + x, 21 + y);
        dropGroup(id, 1, 1, 1, 1);
        tab.child(c++, id++, 206 + x, 21 + y);
        dropGroup(id, 1, 1, 1, 1);
        tab.child(c++, id++, 243 + x, 21 + y);

    }

    public static void casketInterface() {
        int interID = 110000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 10;
        int y = 14;
        tab.totalChildren(9);

        addSpriteLoader(id, 1459);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 465 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 465 + x, 3 + y);
        id++;

        addText(id, "Box Opening", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 247 + x, 4 + y);

        addText(id, "Rewards", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 247 + x, 111 + y);

        hoverButton(id, 1457, 1458, "Open", 1, 0xFF981F, "Open");
        tab.child(c++, id++, 348 + x, 34 + y);
        hoverButton(id, 1457, 1458, "Quick-Open", 1, 0xFF981F, "Quick-Open");
        tab.child(c++, id++, 348 + x, 66 + y);

        tab.child(c++, 110100, 13 + x, 163 - 34 + y);
        tab.child(c++, 110500, 28 + x, 40 + y);

        interID = 110100;
        RSInterface list = addInterface(interID);
        list.width = 462 - 16;
        list.height = 164;
        list.scrollMax = 257;
        id = interID + 1;
        c = 0;
        x = 13;
        y = 9;
        list.totalChildren(320);

        for (int i = 0; i < 20; i++) {
            for (int r = 0; r < 8; r++) {
                dropGroup(id, 1, 1, 1, 1);
                list.child(c++, id++, 2 + x, 0 + y);
                x += 55;
            }
            x = 13;
            y += 56;
        }

        for (int xy = 0; xy < 20; xy++) {
            x = 30;
            int ys = 43 + (56 * xy);
            for (int i = 0; i < 8; i++) {
                addText(id, "1/10", 0xffff00, true, false, 52, fonts, 0);
                list.child(c++, id++, x, ys);
                x += 55;
            }
        }

        interID = 110500;
        RSInterface boxes = addInterface(interID);
        boxes.width = 309;
        boxes.height = 48;
        boxes.scrollMax = 48;
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;
        boxes.totalChildren(2);

        addToItemGroup(id, 1750, 1, 12, 10, false, null, null, null);
        boxes.child(c++, id++, 7 + x, 7 + y);

        addSpriteLoader(id, 1460);
        boxes.child(c++, id++, 150 + x, 1 + y);

    }


    public static void posInterface() {
        int interID = 113000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 37;
        int y = 37;
        tab.totalChildren(11);

        addSpriteLoader(id, 1478);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 411 + x, 9 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 411 + x, 9 + y);
        id++;

        addText(id, "My Panel", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 217 + x, 10 + y);

        addText(id, "294,242,234M", tda, 0, 0xff8624, false, true);
        tab.child(c++, id++, 33 + x, 12 + y);

        hoverButton(id, 1488, 1489, "Claim Money", 0, 0xff8624, " Claim Money");
        tab.child(c++, id++, 11 + x, 39 + y);

        hoverButton(id, 1490, 1491, "Recent Listing", 0, 0xff8624, "   Recent Listing");
        tab.child(c++, id++, 115 + x, 39 + y);

        hoverButton(id, 1486, 1487, "Search Item", 0, 0xff8624, " Search Item");
        tab.child(c++, id++, 11 + x, 69 + y);

        hoverButton(id, 1486, 1487, "Search Player", 0, 0xff8624, "    Search Player");
        tab.child(c++, id++, 115 + x, 69 + y);

        tab.child(c++, 113100, 12 + x, 101 + y);
        tab.child(c++, 113500, 222 + x, 40 + y);

        interID = 113100;
        RSInterface list = addInterface(interID);
        list.width = 202 - 16;
        list.height = 147;
        list.scrollMax = 2570;
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;
        list.totalChildren(300);


        for (int i = 0; i < 50; i++) {
            addSpriteLoader(id, 1479 + ((i % 2) * 2));
            list.child(c++, id++, 0 + x, 0 + y);
            y += 37;
        }
        y = 0;

        for (int i = 0; i < 50; i++) {
            dropGroup(id, 1, 1, 1, 1);
            list.child(c++, id++, 4 + x, 3 + y);
            addText(id, "Sold to Herb" + id, tda, 0, 0xcfa75a, false, true);
            list.child(c++, id++, 53 + x, 6 + y);
            addText(id, "500k" + id, tda, 0, 0xaeaaa5, false, true);
            list.child(c++, id++, 53 + x, 21 + y);
            addSpriteLoader(id, 1485);
            list.child(c++, id++, 40 + x, 3 + y);
            addSpriteLoader(id, 1483);
            list.child(c++, id++, 38 + x, 20 + y);
            y += 37;
        }

        interID = 113500;
        RSInterface boxes = addInterface(interID);
        boxes.width = 202 - 16;
        boxes.height = 208;
        boxes.scrollMax = 4800;
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;
        boxes.totalChildren(500);

        for (int i = 0; i < 50; i++) {
            addSpriteLoader(id, 1480 + ((i % 2) * 2));
            boxes.child(c++, id++, 0 + x, 0 + y);
            y += 59;
        }
        y = 0;

        for (int i = 0; i < 50; i++) {
            addSpriteLoader(id, 1484);
            boxes.child(c++, id++, 4 + x, 4 + y);
            dropGroup(id, 1, 1, 1, 1);
            boxes.child(c++, id++, 9 + x, 9 + y);
            addText(id, "Mystery Box" + id, tda, 0, 0xcfa75a, false, true);
            boxes.child(c++, id++, 52 + x, 8 + y);
            addText(id, "500k" + id, tda, 0, 0xaeaaa5, false, true);
            boxes.child(c++, id++, 65 + x, 27 + y);
            addSpriteLoader(id, 1483);
            boxes.child(c++, id++, 50 + x, 26 + y);

            new ProgressBar(id, 178, 6, new int[]{0x00ff00}, true, false, "", new int[]{0x000000});
            boxes.child(c++, id++, 4 + x, 48 + y);
            addText(id, "7/10" + id, tda, 1, 0xff8624, true, true);
            boxes.child(c++, id++, 93 + x, 44 + y);

            addSpriteLoader(id, 1493);
            boxes.child(c++, id++, 0 + x, 0 + y);
            teleportText(id, "Click to list an item\\nfor sale", "Select", fonts, 2, 0xff8624, true, true, 188, 30);
            boxes.child(c++, id++, 0 + x, 15 + y);

            y += 59;
        }


    }

    public static void posListingInterface() {
        int interID = 114000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 20;
        int y = 35;
        tab.totalChildren(17);

        addSpriteLoader(id, 1495);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 449 + x, 9 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 449 + x, 9 + y);
        id++;

        addText(id, "Active Listing", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 232 + x, 10 + y);

        hoverButton(id, 1502, 1502, "Back", 0, 0xff8624, "");
        tab.child(c++, id++, 9 + x, 9 + y);

        hoverButton(id, 1501, 1500, "Refresh", 0, 0xff8624, " Refresh");
        tab.child(c++, id++, 87 + x, 231 + y);

        hoverButton(id, 1486, 1487, "Search Item", 0, 0xff8624, " Search Item");
        tab.child(c++, id++, 191 + x, 231 + y);

        hoverButton(id, 1486, 1487, "Search Player", 0, 0xff8624, "    Search Player");
        tab.child(c++, id++, 295 + x, 231 + y);


        teleportText(id, "Quantity", "Select", fonts, 0, 0xff8624, false, true, 60, 20);
        tab.child(c++, id++, 14 + x, 41 + y);
        teleportText(id, "Name", "Select", fonts, 0, 0xff8624, false, true, 50, 20);
        tab.child(c++, id++, 107 + x, 41 + y);
        teleportText(id, "Price", "Select", fonts, 0, 0xff8624, false, true, 50, 20);
        tab.child(c++, id++, 241 + x, 41 + y);
        teleportText(id, "Seller", "Select", fonts, 0, 0xff8624, false, true, 50, 20);
        tab.child(c++, id++, 335 + x, 41 + y);


        addSpriteLoader(id, 1499);
        tab.child(c++, id++, 60 + x, 44 + y);
        addSpriteLoader(id, 1499);
        tab.child(c++, id++, 137 + x, 44 + y);
        addSpriteLoader(id, 1499);
        tab.child(c++, id++, 271 + x, 44 + y);
        addSpriteLoader(id, 1499);
        tab.child(c++, id++, 369 + x, 44 + y);


        tab.child(c++, 114100, 12 + x, 54 + y);

        interID = 114100;
        RSInterface list = addInterface(interID);
        list.width = 450 - 16;
        list.height = 171;
        list.scrollMax = 3500;
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;
        list.totalChildren(600);


        for (int i = 0; i < 100; i++) {
            addSpriteLoader(id, 1496 + (i % 2));
            list.child(c++, id++, 0 + x, 0 + y);
            y += 35;
        }
        y = 0;

        for (int i = 0; i < 100; i++) {
            dropGroup(id, 1, 1, 1, 1);
            list.child(c++, id++, 4 + x, 2 + y);
            addText(id, "Frozen abyssal whip" + id, tda, 1, 0xcfa75a, true, true);
            list.child(c++, id++, 118 + x, 9 + y);
            addText(id, "1,534,352" + id, tda, 0, 0x09BF05, true, true);
            list.child(c++, id++, 247 + x, 11 + y);
            addText(id, "Herb" + id, tda, 0, 0xcfa75a, true, true);
            list.child(c++, id++, 343 + x, 11 + y);
            teleportText(id, "Open", "Open Shop", fonts, 0, 0xAEAAA5, true, true, 40, 20);
            list.child(c++, id++, 390 + x, 11 + y);
            y += 35;
        }


    }


    static void seasonalPass(TextDrawingArea[] tda) {
        int interID = 105000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 25;
        int y = 40;
        tab.totalChildren(12);

        addSpriteLoader(id, 1115);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 440 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 440 + x, 3 + y);
        id++;

        addText(id, "Seasonal Pass", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 230 + x, 4 + y);

        addText(id, "Tier", tda, 1, 0xd4d4d4, true, true);
        tab.child(c++, id++, 45 + x, 36 + y);

        addText(id, "XP: 0/10", tda, 0, 0xFFFFFF, false, true);
        tab.child(c++, id++, 90 + x, 33 + y);

        addText(id, "10", tda, 2, 0xFFFFFF, true, true);
        tab.child(c++, id++, 45 + x, 55 + y);

        addText(id, "SEASON 1", tda, 2, 0xFFFFFF, true, true);
        tab.child(c++, id++, 285 + x, 26 + y);
        addText(id, "Season Ends: 10 days", tda, 0, 0xd4d4d4, true, true);
        tab.child(c++, id++, 285 + x, 45 + y);

        teleportText(id, "Information", "Select", fonts, 0, 0xFF9900, true, true, 65, 17);
        //	addText(id, "Information", tda, 0, 0xd4d4d4, true, true);
        tab.child(c++, id++, 15 + x, 225 + y);

        new ProgressBar(id, 61, 8, new int[]{0x009a1a}, true, false, "", new int[]{0x9a0000});
        tab.child(c++, id++, 89 + x, 47 + y);

        tab.child(c++, 105100, 89 + x, 61 + y);

        interID = 105100;

        RSInterface scroll = addInterface(interID);

        scroll.totalChildren(400);
        scroll.width = 361;
        scroll.height = 160;
        scroll.scrollMax = 2600;
        scroll.sideScroll = true;
        y = 0;
        c = 0;
        id = interID + 1;
        x = 0;
        for (int i = 0; i < 50; i++) {
            addSpriteLoader(id, 1106);
            scroll.child(c++, id++, 0 + x, 0 + y);
            addText(id, "" + (i + 1), tda, 0, 0xFFFFFF, true, true);
            scroll.child(c++, id++, 24 + x, 4 + y);

            addRectangle(id, 49, 66, 0x8BD0F1, 200, true);
            scroll.child(c++, id++, 0 + x, 21 + y);
            addRectangle(id, 49, 66, 0xFED161, 200, true);
            scroll.child(c++, id++, 0 + x, 90 + y);

            itemGroup(id, 1, 1, 1, 1);
            scroll.child(c++, id++, 8 + x, 50 + y);
            itemGroup(id, 1, 1, 1, 1);
            scroll.child(c++, id++, 8 + x, 119 + y);

            addConfigButtonWSpriteLoader(id, interID, 1113, 1114, 20, 20, ".", 0, 0, 1714 + i);
            scroll.child(c++, id++, 15 + x, 25 + y);
            addConfigButtonWSpriteLoader(id, interID, 1113, 1114, 20, 20, ".", 0, 0, 1814 + i);
            scroll.child(c++, id++, 15 + x, 96 + y);

            x += 52;
        }

    }


    static void groupIronman(TextDrawingArea[] tda) {
        int interID = 104000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 110;
        int y = 60;
        tab.totalChildren(29);

        addSpriteLoader(id, 1510);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 268 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 268 + x, 3 + y);
        id++;

        addText(id, "Group Ironman", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 146 + x, 4 + y);

        addText(id, "Group Name: Something", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 146 + x, 131 + 18 + y);

        addText(id, "Name", tda, 0, 0xFF981F, false, true);
        tab.child(c++, id++, 20 + x, 23 + y);
        addText(id, "Total Level", tda, 0, 0xFF981F, true, true);
        tab.child(c++, id++, 160 + x, 23 + y);
        addText(id, "Status", tda, 0, 0xFF981F, true, true);
        tab.child(c++, id++, 250 + x, 23 + y);


        addText(id, "", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 146 + x, 132 + y);

        hoverButton(id, 1457, 1458, "Change Name", 2, 0xffef0a, "Change Name");
        tab.child(c++, id++, 22 + x, 158 + 18 + y);
        hoverButton(id, 1457, 1458, "Invite Player", 2, 0xffef0a, "Invite Player");
        tab.child(c++, id++, 151 + x, 158 + 18 + y);


        for (int i = 0; i < 6; i++) {
            addText(id, "Someone", tda, 1, 0xFF981F, false, true);
            tab.child(c++, id++, 20 + x, 37 + y);
            addText(id, "3000", tda, 1, 0xFF981F, true, true);
            tab.child(c++, id++, 160 + x, 37 + y);
            addText(id, "Online", tda, 1, 0xFF981F, true, true);
            tab.child(c++, id++, 250 + x, 37 + y);
            y += 18;
        }

    }


    private static void groupbankInterface() {
        int interID = 106000;
        RSInterface rsinterface = addTabInterface(interID);
        setChildren(16, rsinterface);

        int c = 0;
        int x = 35;
        int y = 17;
        int id = interID + 1;

        addSpriteLoader(id, 1511);
        setBounds(id++, 13 + x, 13 + y, c++, rsinterface);

        RSInterface thing = copy(id, 5385);
        interfaceCache[id].height = 208;
        interfaceCache[id].width = 380;
        interfaceCache[id].scrollMax = 1000;
        setBounds(id++, 24 + x, 35 + y, c++, rsinterface);

        setChildren(1, thing);

        copy(id, 5382);
        interfaceCache[id].width = 10;
        interfaceCache[id].invSpritePadX = 6;
        interfaceCache[id].height = 200;
        interfaceCache[id].inv = new int[10 * 200];
        interfaceCache[id].invStackSizes = new int[10 * 200];
        interfaceCache[id].height = 200;
        interfaceCache[id].actions = new String[]{"Withdraw-1", "Withdraw-5", "Withdraw-10", "Withdraw-All",
                "Withdraw-All but one", "Withdraw-X"};
        setBounds(id++, 0, 3, 0, thing);


        addText(id, "Group Ironman Bank", fonts, 2, 0xFF981F, true, true);
        setBounds(id++, 220 + x, 16 + y, c++, rsinterface);


        addHoverButtonWSpriteLoader(id, 1016, 17, 17, "Close Window", 0, id + 1, 1);
        setBounds(id++, 404 + x, 16 + y, c++, rsinterface);
        addHoveredImageWSpriteLoader(id, 1017, 17, 17, id + 1);
        setBounds(id++, 404 + x, 16 + y, c++, rsinterface);

        id++;

        setBounds(22008, 240 + x, 248 + y, c++, rsinterface);
        setBounds(22009, 240 + x, 248 + y, c++, rsinterface);

        setBounds(21000, 195 + x, 248 + y, c++, rsinterface);

        setBounds(78395, 285 + x, 248 + y, c++, rsinterface);
        setBounds(78396, 285 + x, 248 + y, c++, rsinterface);

        id++;
        addHoverButtonWSpriteLoader(id, 724, 35, 25, "Deposit carried items", -1, id + 1, 1);
        setBounds(id++, 340 + x, 248 + y, c++, rsinterface);
        addHoveredImageWSpriteLoader(id, 725, 35, 25, id + 1);
        setBounds(id++, 340 + x, 248 + y, c++, rsinterface);


        id++;
        addHoverButtonWSpriteLoader(id, 726, 35, 25, "Deposit worn items", -1, id + 1, 1);
        setBounds(id++, 380 + x, 248 + y, c++, rsinterface);
        addHoveredImageWSpriteLoader(id, 727, 35, 25, id + 1);
        setBounds(id++, 380 + x, 248 + y, c++, rsinterface);


        id++;

        addText(id, "0", fonts, 0, 0xb4965a, true, false);
        setBounds(id++, 42 + x, 249 + y, c++, rsinterface);
        addText(id, "100", fonts, 0, 0xb4965a, true, false);
        setBounds(id++, 42 + x, 263 + y, c++, rsinterface);


    }

    public static void posHistoryInterface() {
        int interID = 115000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 20;
        int y = 35;
        tab.totalChildren(15);

        addSpriteLoader(id, 1495);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 449 + x, 9 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 449 + x, 9 + y);
        id++;

        addText(id, "History Listing", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 232 + x, 10 + y);

        hoverButton(id, 1502, 1502, "Back", 0, 0xff8624, "");
        tab.child(c++, id++, 9 + x, 9 + y);

        hoverButton(id, 1501, 1500, "Refresh", 0, 0xff8624, " Refresh");
        tab.child(c++, id++, 180 + x, 231 + y);


        teleportText(id, "Quantity", "Select", fonts, 0, 0xff8624, false, true, 60, 20);
        tab.child(c++, id++, 14 + x, 41 + y);
        teleportText(id, "Name", "Select", fonts, 0, 0xff8624, false, true, 50, 20);
        tab.child(c++, id++, 107 + x, 41 + y);
        teleportText(id, "Price", "Select", fonts, 0, 0xff8624, false, true, 50, 20);
        tab.child(c++, id++, 241 + x, 41 + y);
        teleportText(id, "Buyer", "Select", fonts, 0, 0xff8624, false, true, 50, 20);
        tab.child(c++, id++, 335 + x, 41 + y);

        addSpriteLoader(id, 1499);
        tab.child(c++, id++, 60 + x, 44 + y);
        addSpriteLoader(id, 1499);
        tab.child(c++, id++, 137 + x, 44 + y);
        addSpriteLoader(id, 1499);
        tab.child(c++, id++, 271 + x, 44 + y);
        addSpriteLoader(id, 1499);
        tab.child(c++, id++, 369 + x, 44 + y);


        tab.child(c++, 115100, 12 + x, 54 + y);

        interID = 115100;
        RSInterface list = addInterface(interID);
        list.width = 450 - 16;
        list.height = 171;
        list.scrollMax = 3500;
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;
        list.totalChildren(500);


        for (int i = 0; i < 100; i++) {
            addSpriteLoader(id, 1496 + (i % 2));
            list.child(c++, id++, 0 + x, 0 + y);
            y += 35;
        }
        y = 0;

        for (int i = 0; i < 100; i++) {
            dropGroup(id, 1, 1, 1, 1);
            list.child(c++, id++, 4 + x, 2 + y);
            addText(id, "Frozen abyssal whip" + id, tda, 1, 0xcfa75a, true, true);
            list.child(c++, id++, 118 + x, 9 + y);
            addText(id, "1,534,352" + id, tda, 0, 0x09BF05, true, true);
            list.child(c++, id++, 247 + x, 11 + y);
            addText(id, "Herb" + id, tda, 0, 0xcfa75a, true, true);
            list.child(c++, id++, 343 + x, 11 + y);
            y += 35;
        }

    }

    //


    public static void addModel(int interfaceId, int width, int height, int itemId, int zoom, int contentType) {
        RSInterface rsi = addInterface(interfaceId);
        rsi.type = 6;
        rsi.mediaType = RSInterface.DRAW_ITEM_MODEL;
        ItemDefinition def = ItemDefinition.get(itemId);
        rsi.mediaID = itemId;
        rsi.modelZoom = zoom;
        rsi.modelRotationX = def.rotationX;
        rsi.modelRotationY = def.rotationY;
        rsi.width = width;
        rsi.height = height;
        rsi.contentType = contentType;
    }

    public static void addTransparentLayer(int id, int layerColor, int layerTransparency) {
        RSInterface transparentLayer = addInterface(id);
        transparentLayer.layerColor = layerColor;
        transparentLayer.layerTransparency = layerTransparency;
        transparentLayer.type = 130;
        transparentLayer.setVisible(true);
    }

    public static void addWheel(int id, int width, int height, int strokeWidth, int color,
                                int alpha, int closure, boolean fill, int segments, int spriteId) {
        RSInterface widget = addInterface(id);
        widget.wheel = new WheelOfFortune(width, height, strokeWidth, color, alpha, closure, fill, segments, Client.cacheSprite[spriteId]);
        widget.type = 150;
    }


    private static void wheelOfFortune() {
        final int STARTING_POINT = 21350;
        RSInterface main = addInterface(STARTING_POINT);

        addSpriteLoader(STARTING_POINT + 1, 1526);
        addWheel(STARTING_POINT + 2, 210, 210, 2, 0x3f372c, 255, 2, true, 10, 1527);
        main.totalChildren(13);
        main.child(0, STARTING_POINT + 1, 50, 10);
        main.child(1, STARTING_POINT + 2, 80, 65);
        hoverButton(STARTING_POINT + 3, 1519, 1520, "Spin Wheel");
        main.child(2, STARTING_POINT + 3, 146, 283);
        addText(STARTING_POINT + 4, "Spin Wheel!", fonts, 0, ColorConstants.RS_ORANGE, false, true, 0, 0);
        main.child(3, STARTING_POINT + 4, 163, 290);

        //addNpc(STARTING_POINT + 7, 2998);
        // main.child(6, STARTING_POINT + 7, 340, 186);

        addProperNpc(STARTING_POINT + 5, 2998);
        main.child(4, STARTING_POINT + 5, 335, 186);

        addSpriteLoader(STARTING_POINT + 6, 1528);
        main.child(5, STARTING_POINT + 6, 301, 168);
        addSpriteLoader(STARTING_POINT + 7, 1532);
        main.child(6, STARTING_POINT + 7, 282, 62);
        addText(STARTING_POINT + 8, "Spin the wheel", fonts, 0, ColorConstants.RS_ORANGE, false, true, 0, 0);
        main.child(7, STARTING_POINT + 8, 291, 72);
        addText(STARTING_POINT + 9, "for a reward!", fonts, 0, ColorConstants.RS_ORANGE, false, true, 0, 0);
        main.child(8, STARTING_POINT + 9, 296, 84);
        hoverButton(STARTING_POINT + 10, 1016, 1017, "Close");
        main.child(9, STARTING_POINT + 10, 441, 19);

        addText(STARTING_POINT + 11, "Wheel of fortune", fonts, 2, ColorConstants.RS_ORANGE, true, true, 0, 0);
        main.child(10, STARTING_POINT + 11, 268, 19);
        addTransparentLayer(STARTING_POINT + 12, 0, 0);
        main.child(11, STARTING_POINT + 12, 50, 10);
        main.child(12, STARTING_POINT + 20, 173, 48);

        RSInterface rewardInterface = addInterface(STARTING_POINT + 20);
        rewardInterface.setVisible(false);
        rewardInterface.totalChildren(7);
        addSpriteLoader(STARTING_POINT + 21, 1531);
        rewardInterface.child(0, STARTING_POINT + 21, 0, 0);
        hoverButton(STARTING_POINT + 22, 1016, 1017, "Close");
        rewardInterface.child(1, STARTING_POINT + 22, 164, 9);
        addText(STARTING_POINT + 23, "Reward", fonts, 2, ColorConstants.RS_ORANGE, true, true, 0, 0);
        rewardInterface.child(2, STARTING_POINT + 23, 94, 10);
        addText(STARTING_POINT + 24, "You've won:", fonts, 2, ColorConstants.RS_ORANGE, false, true, 0, 0);
        rewardInterface.child(3, STARTING_POINT + 24, 57, 43);
        hoverButton(STARTING_POINT + 25, 1519, 1520, "Spin Again");
        rewardInterface.child(4, STARTING_POINT + 25, 51, 198);
        addText(STARTING_POINT + 26, "Spin Again!", fonts, 0, ColorConstants.RS_ORANGE, false, true, 0, 0);
        rewardInterface.child(5, STARTING_POINT + 26, 69, 205);

        addModel(STARTING_POINT + 27, 75, 50, 4447, 750, 3500);
        rewardInterface.child(6, STARTING_POINT + 27, 63, 105);


    }


    private static void wheelOfFortune1() {
        final int STARTING_POINT = 25355;
        RSInterface main = addInterface(STARTING_POINT);

        main.totalChildren(2);
        addSpriteLoader(STARTING_POINT + 1, 1542);
        main.child(0, STARTING_POINT + 1, 0, 0);

        addModel(STARTING_POINT + 2, 125, 125, 4151, 500, 3501);
        main.child(1, STARTING_POINT + 2, 50, 50);
    }

    private static void showNpcModel() {
        final int STARTING_POINT = 25350;
        RSInterface main = addInterface(STARTING_POINT);

        main.totalChildren(2);
        addSpriteLoader(STARTING_POINT + 1, 1542);
        main.child(0, STARTING_POINT + 1, 0, 0);

        addNpcOld(STARTING_POINT + 2, 1);
        main.child(1, STARTING_POINT + 2, 80, 180);
    }


    public static void afkCheckerInterface() {
        int interID = 116000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 130;
        int y = 70;
        tab.totalChildren(7);

        addSpriteLoader(id, 1548);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "AFK Checker", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 126 + x, 4 + y);

        addText(id, "Question", tda, 1, 0xcfa75a, true, true);
        tab.child(c++, id++, 126 + x, 29 + y);

        addText(id, "Heres whats up", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 126 + x, 80 + y);

        hoverButton(id, 1307, 1308, "Answer", 2, 0xff8624, "Answer");
        tab.child(c++, id++, 15 + x, 144 + y);

        hoverButton(id, 1307, 1308, "Answer", 2, 0xff8624, "Answer");
        tab.child(c++, id++, 90 + x, 144 + y);

        hoverButton(id, 1307, 1308, "Answer", 2, 0xff8624, "Answer");
        tab.child(c++, id++, 165 + x, 144 + y);

    }


    public static void afkInfoInterface() {
        int interID = 117000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 130;
        int y = 70;
        tab.totalChildren(7);

        addSpriteLoader(id, 1549);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "AFK Alert", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 126 + x, 4 + y);

        addText(id, "Information", tda, 1, 0xcfa75a, true, true);
        tab.child(c++, id++, 126 + x, 29 + y);

        addText(id, "You were AFK in combat\\n for over 15 minutes", tda, 1, 0xcfa75a, true, true);
        tab.child(c++, id++, 126 + x, 50 + y);

        addText(id, "Answered", tda, 1, 0xcfa75a, true, true);
        tab.child(c++, id++, 126 + x, 90 + y);

        addText(id, "Total offenses: ", tda, 1, 0xcfa75a, true, true);
        tab.child(c++, id++, 126 + x, 110 + y);

        hoverButton(id, 1457, 1458, "I Understand", 2, 0xff8624, "I Understand");
        tab.child(c++, id++, 67 + x, 148 + y);

    }


    public static void instanceManager() {
        int interID = 35000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 75;
        int y = 10;
        tab.totalChildren(17);

        addSpriteLoader(id, 1550);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Instance Manager", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 182 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 340 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 340 + x, 3 + y);
        id++;

        addText(id, "Monsters", tda, 1, 0xff8624, true, true);
        tab.child(c++, id++, 92 + x, 29 + y);

        addText(id, "Preview", tda, 1, 0xff8624, true, true);
        tab.child(c++, id++, 264 + x, 29 + y);

        dropGroup(id, 1, 1, 1, 1);
        tab.child(c++, id++, 185 + x, 179 + y);

        addText(id, "Instance token", tda, 1, 0xff8624, false, true);
        tab.child(c++, id++, 223 + x, 178 + y);

        addText(id, "Spawns: 45", tda, 1, 0xff8624, false, true);
        tab.child(c++, id++, 223 + x, 194 + y);

        addText(id, "Cost: 5k Upgrade tokens", tda, 1, 0xff8624, true, true);
        tab.child(c++, id++, 263 + x, 217 + y);


        addConfigButtonWSpriteLoader(id, interID, 1307, 1308, 72, 32, "Select", 0, 5, 1355);
        tab.child(c++, id++, 188 + x, 236 + y);
        addConfigButtonWSpriteLoader(id, interID, 1307, 1308, 72, 32, "Select", 1, 5, 1355);
        tab.child(c++, id++, 268 + x, 236 + y);


        addText(id, "3x3 Grid", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 188 + 36 + x, 236 + 8 + y);

        addText(id, "4x4 Grid", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 268 + 36 + x, 236 + 8 + y);

        hoverButton(id, 1457, 1458, "Start Instance", 2, 0xff8624, "Start Instance");
        tab.child(c++, id++, 199 + x, 277 + y);

        addNpcOld(id, 252);
//        RSInterface.drawNpcOnInterface(id, 100, 1000);
        tab.child(c++, id++, 195 + x, 60 + y);

        tab.child(c++, 35070, 12 + x, 47 + y);


        interID = 35070;
        RSInterface list = addInterface(interID);
        list.width = 160 - 16;
        list.height = 256;
        list.scrollMax = 500;
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;
        list.totalChildren(200);

        id = 35171;
        for (int i = 0; i < 100; i++) {
            addSpriteLoader(id, 1551);
            list.child(c++, id++, 0 + x, 0 + y);
            y += 36;
        }

        y = 0;
        id = 35071;
        for (int i = 0; i < 100; i++) {
            teleportText(id, "", "Select", fonts, 1, 0xFF9900, false, true, 169, 17);
            list.child(c++, id++, 2 + x, 2 + y);
            y += 18;
        }

    }


    public static void sanctumRewards() {
        int interID = 118000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 50;
        int y = 32;
        tab.totalChildren(13);

        addSpriteLoader(id, 1552);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Sanctum of Death", tda, 2, 0xff8624, true, true);
        tab.child(c++, id++, 207 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 392 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 392 + x, 3 + y);
        id++;

        addText(id, "Rewards", tda, 1, 0xff8624, true, true);
        tab.child(c++, id++, 131 + x, 72 + y);

        addText(id, "Rare (1/1000)", tda, 1, 0xff8624, true, true);
        tab.child(c++, id++, 329 + x, 72 + y);

        dropGroup(id, 1, 1, 1, 1);
        tab.child(c++, id++, 14 + x, 29 + y);

        addText(id, "Sanctum Key", tda, 1, 0xff8624, false, true);
        tab.child(c++, id++, 63 + x, 37 + y);

        hoverButton(id, 1457, 1458, "Open", 2, 0xff8624, "Open");
        tab.child(c++, id++, 161 + x, 31 + y);

        hoverButton(id, 1457, 1458, "Open All", 2, 0xff8624, "Open All");
        tab.child(c++, id++, 285 + x, 31 + y);

        teleportText(id, "Increase Chances", "Select", fonts, 0, 0xFFFF00, true, true, 144, 18);
        tab.child(c++, id++, 257 + x, 241 + y);

        tab.child(c++, 118100, 12 + x, 90 + y);
        tab.child(c++, 118500, 257 + x, 90 + y);

        interID = 118100;
        RSInterface list = addInterface(interID);
        list.width = 237 - 16;
        list.height = 165;
        list.scrollMax = 166;
        id = interID + 1;
        c = 0;
        x = 3;
        y = 3;
        list.totalChildren(60);

        for (int z = 0; z < 10; z++) {
            for (int i = 0; i < 6; i++) {
                dropGroup(id, 1, 1, 1, 1);
                list.child(c++, id++, 0 + x, 0 + y);
                x += 37;
            }
            x = 3;
            y += 40;
        }

        interID = 118500;
        RSInterface list1 = addInterface(interID);
        list1.width = 144 - 16;
        list1.height = 145;
        list1.scrollMax = 146;
        id = interID + 1;
        c = 0;
        x = 3;
        y = 3;
        list1.totalChildren(30);

        for (int z = 0; z < 10; z++) {
            for (int i = 0; i < 3; i++) {
                dropGroup(id, 1, 1, 1, 1);
                list1.child(c++, id++, 0 + x, 0 + y);
                x += 43;
            }
            x = 3;
            y += 37;
        }

    }


    public static void bossCollectionLog() {
        int interID = 131000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 35;
        int y = 25;
        tab.totalChildren(23);

        addSpriteLoader(id, 1592);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 405 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 405 + x, 3 + y);
        id++;

        addText(id, "Collection Log", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 216 + x, 4 + y);

        addText(id, "Total: (0/0)", tda, 0, 0xFF981F, false, true);
        tab.child(c++, id++, 10 + x, 6 + y);

        addText(id, "Name", tda, 2, 0xFF981F, false, true);
        tab.child(c++, id++, 183 + x, 60 + y);

        new ProgressBar(id, 120, 15, new int[]{0x009a1a}, true, false, "", new int[]{0x9a0000});
        tab.child(c++, id++, 181 + x, 79 + y);

        addTextRight(id, "Kills: 0", tda, 0, 0xFF981F, true);
        tab.child(c++, id++, 450 + x, 82 + y);

        String[] tabs = new String[]{"Monsters", "Zones", "Bosses", "Minigames", "Boxes"};

        for (int i = 0; i < 5; i++) {
            addConfigButtonWSpriteLoader(id, interID, 1506, 1507, 79, 20, tabs[i], i, 5, 2388);
            tab.child(c++, id++, 7 + x, 26 + y);
            addText(id, tabs[i], 0xFFA500, false, true, 100, tda, 1);
            tab.child(c++, id++, 12 + x, 29 + y);
            x += 79;
        }
        x = 35;

        addText(id, "Obtained: ", tda, 0, 0xFF981F, true, true);
        tab.child(c++, id++, 240 + x, 81 + y);

        addText(id, "Reward: ", tda, 2, 0xFF981F, false, true);
        tab.child(c++, id++, 250 + x, 245 + y);

        itemGroup(id, 1, 1, 1, 1);
        tab.child(c++, id++, 305 + x, 238 + y);

        tab.child(c++, 131050, 11 + x, 58 + y);
        tab.child(c++, 131250, 179 + x, 97 + y);

        interID = 131050;
        RSInterface list = addTabInterface(interID);
        list.totalChildren(200);
        list.height = 213;
        list.width = 160 - 16;
        list.scrollMax = 259;
        id = interID + 1;
        x = 0;
        y = 0;
        c = 0;
        for (int i = 0; i < 100; i++) {
            addConfigButtonWSpriteLoader(id, interID, (i % 2 == 0) ? 1555 : 1554, 1556, 188, 17, "Select", i, 5, 2451);
            list.child(c++, id++, 0 + x, 0 + y);
            addText(id, "", 0xFFA500, false, true, 100, tda, 1);
            list.child(c++, id++, 1 + x, 1 + y);
            y += 17;
        }

        interID = 131250;
        RSInterface list2 = addTabInterface(interID);
        list2.totalChildren(180);
        list2.height = 138;
        list2.width = 240 - 16;
        list2.scrollMax = 290;
        id = interID + 1;
        x = 0;
        y = 0;
        c = 0;

        for (int z = 0; z < 30; z++) {
            for (int r = 0; r < 6; r++) {
                itemGroup(id, 1, 1, 1, 1);
                list2.child(c++, id++, 2 + x, 3 + y);
                x += 38;
            }
            x = 0;
            y += 36;
        }

    }


    public static void serverPerks() {
        int interID = 42050;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 140;
        int y = 55;
        tab.totalChildren(12);

        addSpriteLoader(id, 1557);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 208 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 208 + x, 3 + y);
        id++;

        addText(id, "Server-Wide Perks", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 117 + x, 4 + y);


        addText(id, "The perk will be activated once filled", tda, 0, 16746020, true, true);
        tab.child(c++, id++, 116 + x, 71 + y);

        addProgressBar(id, 213, 20, 50, 16711680, 5492557);
        tab.child(c++, id++, 10 + x, 46 + y);

        addText(id, "25m/50m", tda, 1, 16777215, true, true);
        tab.child(c++, id++, 117 + x, 48 + y);


        hoverButton(id, 1457, 1458, "Contribute", 2, 0xff8624, "Contribute");
        tab.child(c++, id++, 57 + x, 92 + y);


        addSpriteLoader(id, 1558);
        tab.child(c++, id++, 8 + x, 26 + y);

        addText(id, "No Cooldown", tda, 0, 16746020, false, true);
        tab.child(c++, id++, 19 + x, 27 + y);

        addText(id, "Current Perk", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 117 + x, 131 + y);

        tab.child(c++, 42100, 12 + x, 150 + y);

        interID = 42100;
        RSInterface list = addTabInterface(interID);
        list.totalChildren(4);
        list.width = 211 - 16;
        list.height = 153;
        list.scrollMax = 153;
        id = interID + 1;
        x = 11;
        y = 11;
        c = 0;
        for (int i = 0; i < 1; i++) {
            addSpriteLoader(id, 1517);
            list.child(c++, id++, 0 + x, 0 + y);

            addText(id, "Double Drops", tda, 2, 0xFFA500, true, true);
            list.child(c++, id++, 93 + x, 12 + y);

            addSpriteLoader(id, 1523);
            list.child(c++, id++, 2 + x, 7 + y);
            addSpriteLoader(id, 1523);
            list.child(c++, id++, 157 + x, 7 + y);
            y += 43;
        }


    }


    private static void customServerPerks(TextDrawingArea[] font) {
        int STARTING_POINT = 42050;
        RSInterface main = addInterface(STARTING_POINT);
        addSpriteLoader(STARTING_POINT + 1, 1516); // 1229
        addText(STARTING_POINT + 2, "Server Perks", font, 2, 16746020, true, true);
        addText(STARTING_POINT + 4, "Once the well has been filled up,", font, 0, 16746020, true, true);
        addText(STARTING_POINT + 5, "perk will be activated Worldwide!", font, 0, 16746020, true, true);
        addHoverButtonWSpriteLoader(STARTING_POINT + 6, 1519, 90, 35, "Contribute", -1, STARTING_POINT + 7, 1);
        addHoveredImageWSpriteLoader(STARTING_POINT + 7, 1520, 90, 35, STARTING_POINT + 8);
        addText(STARTING_POINT + 9, "Contribute!", font, 0, 16746020, true, true);
        addProgressBar(STARTING_POINT + 10, 165, 40, 75, 16711680, 5492557);
        addText(STARTING_POINT + 11, "250Q / 250Q", font, 1, 16777215, true, true);
        main.totalChildren(10);
        main.child(0, STARTING_POINT + 1, 155, 10);
        main.child(1, STARTING_POINT + 2, 254, 20);
        main.child(2, STARTING_POINT + 4, 254, 235);
        main.child(3, STARTING_POINT + 5, 254, 245);
        main.child(4, STARTING_POINT + 6, 210, 263);
        main.child(5, STARTING_POINT + 7, 210, 263);
        main.child(6, STARTING_POINT + 9, 254, 270);
        main.child(7, STARTING_POINT + 15, 120, 49);
        main.child(8, STARTING_POINT + 10, 173, 189);
        main.child(9, STARTING_POINT + 11, 254, 200);
        RSInterface perkScroll = addInterface(STARTING_POINT + 15);
        perkScroll.totalChildren(18);
        int idStart = STARTING_POINT + 16;
        int yPos = 0;

        for (int i = 0; i < 6; i++) {
            addHoverableConfigSprite(idStart + i, 1517, 1518, true, "Select", i, 4000);
            perkScroll.child(i, idStart + i, 53, yPos);
            yPos += 42;
        }

        String[] PERK_NAMES = {"X2 Bonds Donations", "X1.5 Damage", "X2 Npc kills", "X1.5 DR", "X3 XP", "Double Drops"};
        int child = 6;
        int index = 0;
        yPos = 5;
        idStart = STARTING_POINT + 50;
        for (int j = 0; j < 12; j += 2) {
            addText(idStart + j, PERK_NAMES[index], font, 2, 16746020, true, true);
            addText(idStart + j + 1, "@gre@Perk", font, 1, 32768, false, true);
            perkScroll.child(child, idStart + j, 125, yPos);
            perkScroll.child(child + 1, idStart + j + 1, 110, yPos + 14);
            child += 2;
            yPos += 42;
            index++;
        }
        perkScroll.width = 200;
        perkScroll.height = 137;
        perkScroll.scrollMax = 252;
    }


    static void playerViewInterfaceMain() {
        RSInterface tab = addInterface(108000);
        int id = 108001;
        int c = 0;
        int x = 10;
        int y = 17;
        tab.totalChildren(13);

        addSpriteLoader(id, 1560);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Player Viewer", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 250 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 460 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 460 + x, 3 + y);
        id++;

        addConfigButtonWSpriteLoader(id, 140000, 1506, 1507, 79, 20, "Inventory", 0, 5, 2357);
        tab.child(c++, id++, 12 + x, 25 + y);

        addConfigButtonWSpriteLoader(id, 140000, 1506, 1507, 79, 20, "Equipment", 1, 5, 2357);
        tab.child(c++, id++, 91 + x, 25 + y);

        addConfigButtonWSpriteLoader(id, 140000, 1506, 1507, 79, 20, "Bank", 2, 5, 2357);
        tab.child(c++, id++, 170 + x, 25 + y);

        addText(id, "Inventory", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 17 + x, 28 + y);

        addText(id, "Equipment", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 96 + x, 28 + y);

        addText(id, "Bank", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 176 + x, 28 + y);

        addText(id, "Information", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 365 + x, 32 + y);

        tab.child(c++, 108100, 14 + x, 46 + y);
        tab.child(c++, 108050, 258 + x, 51 + y);

        RSInterface info = addInterface(108100);
        info.width = 234 - 16;
        info.height = 244;
        info.scrollMax = 244;

        info.totalChildren(2);
        id = 108101;
        c = 0;
        x = 40;
        y = 18;

        addSpriteLoader(id, 1561);
        info.child(c++, id++, 0 + x, 0 + y);

        info.child(c++, 13824, 3 + x, 40 + y);

        RSInterface inv = addInterface(108050);
        inv.width = 212 - 16;
        inv.height = 239;
        inv.scrollMax = 500;

        inv.totalChildren(35);
        id = 108051;
        c = 0;
        x = 0;
        y = 0;

        for (int i = 0; i < 35; i++) {
            addText(id, "", tda, 0, 16750643, false, true);
            inv.child(c++, id++, 3 + x, 3 + y);
            y += 15;
        }


    }


    static void playerViewInterfaceBank() {
        RSInterface tab = addInterface(140000);
        int id = 140001;
        int c = 0;
        int x = 10;
        int y = 17;
        tab.totalChildren(13);

        addSpriteLoader(id, 1560);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Player Viewer", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 250 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 460 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 460 + x, 3 + y);
        id++;

        addConfigButtonWSpriteLoader(id, 140000, 1506, 1507, 79, 20, "Inventory", 0, 5, 2357);
        tab.child(c++, id++, 12 + x, 25 + y);

        addConfigButtonWSpriteLoader(id, 140000, 1506, 1507, 79, 20, "Equipment", 1, 5, 2357);
        tab.child(c++, id++, 91 + x, 25 + y);

        addConfigButtonWSpriteLoader(id, 140000, 1506, 1507, 79, 20, "Bank", 2, 5, 2357);
        tab.child(c++, id++, 170 + x, 25 + y);

        addText(id, "Inventory", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 17 + x, 28 + y);

        addText(id, "Equipment", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 96 + x, 28 + y);

        addText(id, "Bank", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 176 + x, 28 + y);

        addText(id, "Information", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 365 + x, 32 + y);

        tab.child(c++, 140100, 14 + x, 46 + y);
        tab.child(c++, 108050, 258 + x, 51 + y);

        RSInterface bank = addInterface(140100);
        bank.width = 234 - 16;
        bank.height = 244;
        bank.scrollMax = 285;

        bank.totalChildren(600);
        id = 140101;
        c = 0;
        x = 3;
        y = 3;

        for (int z = 0; z < 100; z++) {
            for (int i = 0; i < 6; i++) {
                itemGroup(id, 1, 1, 1, 1);
                bank.child(c++, id++, 0 + x, 0 + y);
                x += 35;
            }
            x = 6;
            y += 4 + 30;
        }

    }


    public static void cardPacksRewards() {
        int interID = 141000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 130;
        int y = 30;
        tab.totalChildren(18);

        addSpriteLoader(id, 1562);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 230 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 230 + x, 3 + y);
        id++;

        addText(id, "Card Pack", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 127 + x, 4 + y);

        hoverButton(id, 1457, 1458, "Open Again", 2, 0xff8624, "Open Again");
        tab.child(c++, id++, 67 + x, 225 + y);
        for (int col = 0; col < 2; col++) {
            for (int row = 0; row < 3; row++) {
                addSpriteLoader(id, 1565 + row + col);
                tab.child(c++, id++, 21 + x, 33 + y);
                itemGroup(id, 1, 1, 1, 1);
                tab.child(c++, id++, 21 + 16 + x, 33 + 27 + y);
                x += 73;
            }
            x = 130;
            y += 96;
        }

        x = 130;
        y = 30;

        addModel(25359, 125, 125, 4151, 100, 3501);
        tab.child(c++, 25359, 29 + x, 26 + y);


    }


    public static void cardPacks() {
        int interID = 141500;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 130;
        int y = 30;
        tab.totalChildren(6);

        addSpriteLoader(id, 1562);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 230 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 230 + x, 3 + y);
        id++;

        addText(id, "Card Pack", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 127 + x, 4 + y);

        hoverButton(id, 1457, 1458, "Open Pack", 2, 0xff8624, "Open Pack");
        tab.child(c++, id++, 67 + x, 225 + y);

        addModel(25358, 125, 125, 4151, 100, 3501);
        tab.child(c++, 25358, 29 + x, 26 + y);

    }


    public static void valentinesInterface() {
        int interID = 143000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 30;
        int y = 20;
        tab.totalChildren(33);

        addSpriteLoader(id, 1569);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 430 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 430 + x, 3 + y);
        id++;

        addText(id, "Valentines Event", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 227 + x, 4 + y);

        addText(id, "Love", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 69 + x, 29 + y);

        addText(id, "Hate", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 386 + x, 29 + y);

        addText(id, "Rose Petals", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 227 + x, 151 + y);

        addText(id, "0", tda, 0, 0xffffff, true, true);
        tab.child(c++, id++, 227 + x, 167 + y);

        hoverButton(id, 1570, 1571, "Sprinkle", 0, 0xffffff, "Sprinkle");
        tab.child(c++, id++, 163 + x, 183 + y);

        hoverButton(id, 1570, 1571, "Crush", 0, 0xffffff, "Crush");
        tab.child(c++, id++, 239 + x, 183 + y);

        new ProgressBar(id, 39, 221, new int[]{0xa4101d}, false, false, "", new int[]{0x473d33});
        tab.child(c++, id++, 20 + x, 54 + y);

        new ProgressBar(id, 39, 221, new int[]{0xa715c9}, false, false, "", new int[]{0x473d33});
        tab.child(c++, id++, 396 + x, 54 + y);

        dropGroup(id, 1, 1, 1, 1);
        tab.child(c++, id++, 211 + x, 36 + y);

        addText(id, "1,000", tda, 0, 0xFFffff, true, true);
        tab.child(c++, id++, 39 + x, 148 + y);

        addText(id, "(max 5k)", tda, 0, 0xFFffff, true, true);
        tab.child(c++, id++, 163 + 26 + x, 199 + y);

        addText(id, "1,000", tda, 0, 0xFFffff, true, true);
        tab.child(c++, id++, 415 + x, 148 + y);

        addText(id, "(max 5k)", tda, 0, 0xFFffff, true, true);
        tab.child(c++, id++, 239 + 26 + x, 199 + y);

        for (int i = 0; i < 4; i++) {
            dropGroup(id, 1, 1, 1, 1);
            tab.child(c++, id++, 82 + x, 57 + y);
            addText(id, "", tda, 0, 0xFF981F, true, true);
            tab.child(c++, id++, 82 + 17 + x, 57 + 40 + y);
            y += 57;
        }

        y = 20;

        for (int i = 0; i < 4; i++) {
            dropGroup(id, 1, 1, 1, 1);
            tab.child(c++, id++, 340 + x, 57 + y);
            addText(id, "", tda, 0, 0xFF981F, true, true);
            tab.child(c++, id++, 340 + 17 + x, 57 + 40 + y);
            y += 57;
        }
    }


    static void teleportInterface() {
        int interID = 60000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 10;
        int y = 10;
        tab.totalChildren(27);

        addSpriteLoader(id, 1444);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Teleports", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 248 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 471 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 471 + x, 3 + y);
        id++;

        String[] tabs = new String[]{"Bosses", "Monsters", "Minigames", "Dungeons", "Cities", "Globals"};

        for (int i = 0; i < tabs.length; i++) {
            addConfigButtonWSpriteLoader(id, interID, 1506, 1507, 79, 20, tabs[i], i, 5, 2388);
            tab.child(c++, id++, 10 + x, 24 + y);
            addText(id, tabs[i], 0xFFA500, false, true, 100, tda, 1);
            tab.child(c++, id++, 15 + x, 27 + y);
            x += 79;
        }
        x = 10;

        addText(id, "Bosses", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 87 + x, 58 + y);

        addText(id, "Npc Name", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 257 + x, 58 + y);

        addText(id, "Rewards", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 417 + x, 58 + y);

        addText(id, "Description", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 257 + x, 234 + y);


        hoverButton(id, 1457, 1458, "Teleport", 2, 0xff8624, "Teleport");
        tab.child(c++, id++, 359 + x, 273 + y);

        addNpcOld(id, 252);
        tab.child(c++, id++, 190 + x, 116 + y);

        tab.child(c++, 60100, 12 + x, 76 + y);
        tab.child(c++, 60300, 352 + x, 76 + y);

        for (int i = 0; i < 3; i++) {
            addText(id, "Info", tda, 1, 16750643, false, true);
            tab.child(c++, id++, 173 + x, 253 + y);
            y += 18;
        }

        interID = 60100;
        RSInterface list = addInterface(interID);
        list.width = 150 - 16;
        list.height = 228;
        list.scrollMax = 315;

        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;
        list.totalChildren(150);

        for (int i = 0; i < 50; i++) {
            addSpriteLoader(id, 1551);
            list.child(c++, id++, 0 + x, 0 + y);
            y += 36;
        }

        y = 0;
        for (int i = 0; i < 100; i++) {
            addHoverableText(id, "Name: " + i, "Select", tda, 0, false, true, 200, ColorConstants.ORANGE, 0xb9a888);
            list.child(c++, id++, 2 + x, 3 + y);
            y += 18;
        }

        interID = 60300;
        RSInterface scroll1 = addInterface(interID);
        scroll1.width = 131 - 16;
        scroll1.height = 186;
        scroll1.scrollMax = 1000;
        scroll1.totalChildren(1);
        id = interID + 1;
        c = 0;
        x = 2;
        y = 2;
        addToItemGroup(id, 3, 50, 6, 5, true, new String[]{null, null, null, null, null}); // it looks oversized for some on apollo too, go look at the gif  u sent me. yeah but it has a max of a specific thing not super oversized.
        scroll1.child(c++, id++, 0 + x, 0 + y);

    }


    public static void settings() {
        RSInterface tab = addInterface(41750);
        int c = 0;
        int x = 0;
        int y = 0;
        tab.totalChildren(7);
        int id = 41751;

        addSpriteLoader(id, 650);
        tab.child(c++, id++, 0 + x, 22 + y);

        addSpriteLoader(id, 651);
        tab.child(c++, id++, 0 + x, 25 + y);

        addSpriteLoader(id, 650);
        tab.child(c++, id++, 0 + x, 245 + y);

        addText(id, "Client Settings", fonts, 2, 16750623, false, true);
        tab.child(c++, id++, 10 + x, 3 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 1);
        tab.child(c++, id++, 170 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 170 + x, 3 + y);
        id++;

        tab.child(c++, 41800, 0 + x, 25 + y);

        RSInterface options = addInterface(41800);
        options.width = 174;
        options.height = 220;
        options.scrollMax = 59 + (14 * 26);
        options.totalChildren(60);

        c = 0;
        x = 2;
        y = 3;
        int iz = 0;

        id = 41915;

        addRectangle(id, 256, 0x000000, true, 168, 52);
        options.child(c++, id++, 1 + x, y);

        y += 56;

        for (int i = 0; i < 14; i++) {
            addRectangle(id, 256, 0x000000, true, 168, 23);
            options.child(c++, id++, 1 + x, y);
            y += 26;
        }

        id = 41805;
        y = 3;
        addRectangle(id, 256, 0x463D32, true, 166, 50);
        options.child(c++, id++, 2 + x, y + 1);

        hoverButton(id, 486, 487, 50, 39, "Fixed");
        options.child(c++, id++, 20 + x, y + 5);

        hoverButton(id, 488, 489, 50, 39, "Resizable");
        options.child(c++, id++, 100 + x, y + 5);

        y = 59;

        String[] names = new String[]{"New Function Keys", "New Cursors", "New Gameframe", "New HP Bars", "New Hitmarks",
                "HP Text Above Head", "Username Above Heads", "x10 Hits", "Highlight Name in Chat",
                "Toggle Fog", "Show Ground Text", "Animate Textures", "Render Player Equipment", "Render Pets"
        };

        for (int i = 0; i < 14; i++) {
            addRectangle(id, 256, 0x463D32, true, 166, 21);
            options.child(c++, id++, 2 + x, y + 1);
            addText(id, names[i] + "", 0xff9b00, false, true, 100, tda, 1);
            options.child(c++, id++, 21 + x, y + 4);
            addToggleButton(id, 1332, 1200 + i, 15, 15, "Toggle");
            options.child(c++, id++, 4 + x, y + 4);
            y += 26;
            iz++;
        }

    }


    private static void bossLog() {
        RSInterface Interface = addTabInterface(142400);
        RSInterface logs = addInterface(142415);

        setChildren(6, Interface);
        int x = 85;
        int y = 17;
        int c = 0;
        int id = 142401;
        addSpriteLoader(id, 1573);
        setBounds(id++, 0 + x, 0 + y, c++, Interface);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 1);
        setBounds(id++, 321 + x, 10 + y, c++, Interface);

        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        setBounds(id++, 321 + x, 10 + y, c++, Interface);

        id++;

        addText(id, "Boss Kill Log", tda, 2, 0xFFA500, true, true);
        setBounds(id++, 174 + x, 10 + y, c++, Interface);

        addText(id, "Total Npc Kills: ", tda, 2, 0xFFA500, true, true);
        setBounds(id++, 174 + x, 273 + y, c++, Interface);

        setBounds(142415, 10 + x, 37 + y, c++, Interface);

        logs.width = 311;
        logs.height = 235;
        logs.scrollMax = 486;// 1790

        setChildren(404, logs);
        id = 142416;
        int xx = 0;
        y = 0;
        c = 0;
        addSpriteLoader(id, 1574);
        setBounds(id++, xx, y, c++, logs);

        addText(id, "Monster", tda, 2, 0xFFA500, false, true);
        setBounds(id++, xx, y + 0, c++, logs);
        addTextRight(id, "Kills", tda, 2, 0xFFA500, true);
        setBounds(id++, xx + 275 + 31, y + 0, c++, logs);
        addTextRight(id, "Best Time", tda, 2, 0xFFA500, true);
        setBounds(id++, xx + 365 + 66, y + 0, c++, logs);

        for (int i = 0; i < 200; i++) {
            addText(id, "", tda, 1, 0xFFA500, false, true);
            setBounds(id++, xx + 1, y + 22, c++, logs);

            addTextRight(id, "0", tda, 1, 0xFFA500, true);
            setBounds(id++, xx + 303, y + 22, c++, logs);

            xx = 0;
            y += 18;
        }

    }

    public void youtubeVideos() {
        RSInterface main = addInterface(72100);
        addSpriteLoader(72101, 1576);

        addText(72102, "YouTube on Lunite!", 0xffffff, true, true, 52, tda, 2);


        addConfigButtonWSpriteLoader(72103, 72100, 1457, 1458, 118, 28, "View Videos", 0, 5, 891);

        addConfigButtonWSpriteLoader(72107, 72100, 1457, 1458, 118, 28, "View Channels", 1, 5, 891);


        //hoverButton(72103, 1457, 1458, "View Videos", 1, 0xff8624, "View Videos");
        addText(72104, "View Videos", 0xff8624, true, true, 52, tda, 1);

        // hoverButton(72107, 1457, 1458, "View Channels", 1, 0xff8624, "View Channels");
        addText(72108, "View Channels", 0xff8624, true, true, 52, tda, 1);


        addText(72115, "YouTube Videos", 0xffffff, true, true, 52, tda, 0);

        main.totalChildren(10);
        int c = 0;
        int x = 80;
        int y = 25;
        main.child(c++, 72101, 0 + x, 0 + y);
        main.child(c++, 72102, 175 + x, 5 + y);

        main.child(c++, 142402, 324 + x, 3 + y);
        main.child(c++, 142403, 324 + x, 3 + y);

        main.child(c++, 72103, 26 + x, 27 + y);
        main.child(c++, 72107, 195 + x, 27 + y);

        main.child(c++, 72104, 85 + x, 34 + y);
        main.child(c++, 72108, 254 + x, 34 + y);

        main.child(c++, 72115, 175 + x, 65 + y);
        main.child(c++, 72150, 12 + x, 80 + y);
        RSInterface scroll = addInterface(72150);
        scroll.height = 190;
        scroll.scrollMax = 191;
        scroll.width = 325 - 16;

    }


    public void discordComponents() {
        addSpriteLoader(19120, 1584);

        addText(19121, "Discord Integration", 16750643, true, true, 52, tda, 2);


        hoverButton(19122, 1457, 1458, "Link Account", 2, 0xff8624, "Link Account");


        hoverButton(19125, 1457, 1458, "Open Channel", 2, 0xff8624, "Open Channel");

    }

    public void discordConnect() {

        addText(19136, "How to Link your Discord:", tda, 1, 0xff8624, true, true);

        addText(19131,
                "1. Click the Open Channel button"
                        + "\\n2. Type !connect in #discord-connect for code"
                        + "\\n3. Click the Link button and enter the code"
                ,
                0xf0aa37, true, true, 52, tda, 0);


        addText(19132, "Discord Account:", tda, 2, 16750643, true, true);

        addText(19133, "N/A", tda, 2, 16750643, true, true);


        addText(19134, "Benefits:", tda, 1, 0xff8624, true, true);


        addText(19135, "- Receive free Donator rank for linking"
                + "\\n- Receive 1 point every 5 minutes once linked"
                + "\\n- Receive 2 additional points by:"
                + "\\nHaving \"Lunite\" in your custom status", tda, 0, 0xf0aa37, true, true);


        addToggleButton(19137, 1337, 3244, 15, 15, "Auto Retaliate");

        addText(19138, "Recieve message when Lunite updates", tda, 0, 0xf0aa37, true, true);


        RSInterface main = addInterface(19130);

        main.totalChildren(14);
        int c = 0;
        int x = 124, y = 40;
        main.child(c++, 19120, 0 + x, 0 + y);

        main.child(c++, 19121, 135 + x, 5 + y);

        main.child(c++, 19122, 13 + x, 227 + y);
        main.child(c++, 19125, 138 + x, 227 + y);

        main.child(c++, 142402, 247 + x, 4 + y);
        main.child(c++, 142403, 247 + x, 4 + y);

        main.child(c++, 19136, 135 + x, 29 + y);
        main.child(c++, 19131, 135 + x, 44 + y);

        main.child(c++, 19134, 135 + x, 85 + y);
        main.child(c++, 19135, 135 + x, 100 + y);

        main.child(c++, 19132, 135 + x, 148 + y);
        main.child(c++, 19133, 135 + x, 173 + y);

        main.child(c++, 19137, 27 + x, 199 + y);
        main.child(c++, 19138, 140 + x, 201 + y);

    }


    static void godsInterface() {
        int interID = 144000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 130;
        int y = 13;
        tab.totalChildren(18);

        addSpriteLoader(id, 1585);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Gods Interface", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 129 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 233 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 233 + x, 3 + y);
        id++;

        String[] tabs = new String[]{"Easy", "Medium", "Hard"};

        for (int i = 0; i < tabs.length; i++) {
            addConfigButtonWSpriteLoader(id, interID, 1506, 1507, 79, 20, tabs[i], i, 5, 4511);
            tab.child(c++, id++, 10 + x, 25 + y);
            addText(id, tabs[i], 0xFFA500, false, true, 100, tda, 1);
            tab.child(c++, id++, 15 + x, 28 + y);
            x += 79;
        }
        x = 130;

        addText(id, "Information:", tda, 2, 16750643, false, true);
        tab.child(c++, id++, 16 + x, 58 + y);

        addText(id, "- Boss hitpoints scale per difficulty \\n- Different loot tables per difficulty \\n- No respawns on death \\n", tda, 1, 0xEECB38, false, true);
        tab.child(c++, id++, 18 + x, 73 + y);

        addText(id, "Restrictions:", tda, 2, 16750643, false, true);
        tab.child(c++, id++, 16 + x, 117 + y);

        addText(id, "- Maximum Party size: 6 \\n- No Soulsplit allowed \\n- No Pets allowed \\n- AOE won't work", tda, 1, 0xEECB38, false, true);
        tab.child(c++, id++, 18 + x, 132 + y);

        addText(id, "Requirements", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 129 + x, 190 + y);

        addText(id, "10,000 Blood odin kills", tda, 1, 0xFFFFFF, true, true);
        tab.child(c++, id++, 129 + x, 211 + y);

        addText(id, "100k Upgrade Tokens", tda, 1, 0xFFFFFF, true, true);
        tab.child(c++, id++, 129 + x, 226 + y);


        hoverButton(id, 1457, 1458, "Start Fight", 2, 0xff8624, "Start Fight");
        tab.child(c++, id++, 68 + x, 263 + y);

    }


    static void godsCoffer() {
        int interID = 144100;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 63;
        int y = 13;
        tab.totalChildren(12);

        addSpriteLoader(id, 1586);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Isle of the Gods Coffer", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 192 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 362 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 362 + x, 3 + y);
        id++;

        String[] tabs = new String[]{"Coffer", "Rewards"};

        for (int i = 0; i < tabs.length; i++) {
            addConfigButtonWSpriteLoader(id, interID, 1506, 1507, 79, 20, tabs[i], i, 5, 4511);
            tab.child(c++, id++, 10 + x, 25 + y);
            addText(id, tabs[i], 0xFFA500, false, true, 100, tda, 1);
            tab.child(c++, id++, 15 + x, 28 + y);
            x += 79;
        }
        x = 63;

        hoverButton(id, 1457, 1458, "Take All", 2, 0xff8624, "Take All");
        tab.child(c++, id++, 60 + x, 267 + y);

        hoverButton(id, 1457, 1458, "Bank All", 2, 0xff8624, "Bank All");
        tab.child(c++, id++, 201 + x, 267 + y);

        addText(id, "Drop Rate", tda, 1, 0xFFA500, true, true);
        tab.child(c++, id++, 275 + x, 26 + y);

        tab.child(c++, 144150, 12 + x, 57 + y);

        interID = 144150;
        RSInterface scroll1 = addInterface(interID);
        scroll1.width = 360 - 16;
        scroll1.height = 204;
        scroll1.scrollMax = 1000;
        scroll1.totalChildren(160);
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;

        for (int i = 0; i < 20; i++) {
            for (int r = 0; r < 8; r++) {
                addToItemGroup(id, 1, 1, 1, 1, true, new String[]{"Take", null, null, null, null}); //
                scroll1.child(c++, id++, 3 + x, 3 + y);
                x += 43;
            }
            x = 0;
            y += 41;
        }

    }


    static void godsRewards() {
        int interID = 144500;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 63;
        int y = 13;
        tab.totalChildren(16);

        addSpriteLoader(id, 1586);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Isle of the Gods Rewards", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 192 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 362 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 362 + x, 3 + y);
        id++;

        String[] tabs = new String[]{"Coffer", "Rewards"};

        for (int i = 0; i < tabs.length; i++) {
            addConfigButtonWSpriteLoader(id, interID, 1506, 1507, 79, 20, tabs[i], i, 5, 4511);
            tab.child(c++, id++, 10 + x, 25 + y);
            addText(id, tabs[i], 0xFFA500, false, true, 100, tda, 1);
            tab.child(c++, id++, 15 + x, 28 + y);
            x += 79;
        }
        x = 63;


        addConfigButtonWSpriteLoader(id, interID, 1457, 1458, 118, 28, "Easy Rewards", 0, 5, 4512);
        tab.child(c++, id++, 10 + x, 267 + y);
        addText(id, "Easy Rewards", tda, 2, 0xFFA500, true, true);
        tab.child(c++, id++, 69 + x, 273 + y);
        //  hoverButton(id, 1457, 1458, "Easy Rewards", 2, 0xff8624, "Easy Rewards");
        // tab.child(c++, id++, 10 + x, 267 + y);


        addConfigButtonWSpriteLoader(id, interID, 1457, 1458, 118, 28, "Medium Rewards", 1, 5, 4512);
        tab.child(c++, id++, 133 + x, 267 + y);
        addText(id, "Medium Rewards", tda, 2, 0xFFA500, true, true);
        tab.child(c++, id++, 192 + x, 273 + y);
        //hoverButton(id, 1457, 1458, "Medium Rewards", 2, 0xff8624, "Medium Rewards");
        // tab.child(c++, id++, 133 + x, 267 + y);


        addConfigButtonWSpriteLoader(id, interID, 1457, 1458, 118, 28, "Hard Rewards", 2, 5, 4512);
        tab.child(c++, id++, 256 + x, 267 + y);
        addText(id, "Hard Rewards", tda, 2, 0xFFA500, true, true);
        tab.child(c++, id++, 315 + x, 273 + y);
        // hoverButton(id, 1457, 1458, "Hard Rewards", 2, 0xff8624, "Hard Rewards");
        // tab.child(c++, id++, 256 + x, 267 + y);


        addText(id, "Drop Rate", tda, 1, 0xFFA500, true, true);
        tab.child(c++, id++, 275 + x, 26 + y);

        tab.child(c++, 144550, 12 + x, 57 + y);

        interID = 144550;
        RSInterface scroll1 = addInterface(interID);
        scroll1.width = 360 - 16;
        scroll1.height = 204;
        scroll1.scrollMax = 1000;
        scroll1.totalChildren(320);
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;

        for (int i = 0; i < 20; i++) {
            for (int r = 0; r < 8; r++) {
                addToItemGroup(id, 1, 1, 1, 1, false, new String[]{null, null, null, null, null}); //
                scroll1.child(c++, id++, 5 + x, 3 + y);
                addText(id, "1/100", tda, 0, 16750643, true, true);
                scroll1.child(c++, id++, 22 + x, 35 + y);
                x += 43;
            }
            x = 0;
            y += 51;
        }

    }

    static void godsHitpoints() {
        int interID = 144900;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 32;
        int y = 21;
        tab.totalChildren(7);

        addSpriteLoader(id, 1587);
        tab.child(c++, id++, 0 + x, 0 + y);

        addSpriteLoader(id, 1589);
        tab.child(c++, id++, 14 + x, 7 + y);

        addSpriteLoader(id, 1588);
        tab.child(c++, id++, 407 + x, 7 + y);

        addSpriteLoader(id, 1590);
        tab.child(c++, id++, 43 + x, 2 + y);

        addSpriteLoader(id, 1591);
        tab.child(c++, id++, 227 + x, 2 + y);

        addText(id, "100%", tda, 1, 0xffffff, true, true);
        tab.child(c++, id++, 127 + x, 6 + y);

        addText(id, "100%", tda, 1, 0xffffff, true, true);
        tab.child(c++, id++, 311 + x, 6 + y);


    }


    static void assassinsInterface() {
        int interID = 145000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 125;
        int y = 25;
        tab.totalChildren(22);

        addSpriteLoader(id, 1594);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Assassins Guild", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 127 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 230 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 230 + x, 3 + y);
        id++;

        hoverButton(id, 1457, 1458, "Level Up", 2, 0xff8624, "Level Up");
        tab.child(c++, id++, 68 + x, 245 + y);

        addText(id, "Information", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 127 + x, 60 + y);

        addText(id, "Reward For Next Tier", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 127 + x, 177 + y);

        String[] tabs = new String[]{"Tier 1", "Tier 2", "Tier 3"};

        for (int i = 0; i < tabs.length; i++) {
            addConfigButtonWSpriteLoader(id, interID, 1506, 1507, 79, 20, tabs[i], i, 5, 4511);
            tab.child(c++, id++, 9 + x, 26 + y);
            addText(id, tabs[i], 0xFFA500, false, true, 100, tda, 1);
            tab.child(c++, id++, 14 + x, 29 + y);
            x += 79;
        }


        x = 125;
        y = 25;
        addSpriteLoader(id, 1596);
        tab.child(c++, id++, 137 + x, 29 + y);

        addSpriteLoader(id, 1596);
        tab.child(c++, id++, 216 + x, 29 + y);

        x = 125;
        y = 25;

        for (int i = 0; i < 5; i++) {
            addText(id, "Information" + id, tda, 1, 16750643, false, true);
            tab.child(c++, id++, 16 + x, 83 + y);
            y += 15;
        }

        x = 125;
        y = 25;

        for (int i = 0; i < 2; i++) {
            addText(id, "Information", tda, 1, 16750643, false, true);
            tab.child(c++, id++, 16 + x, 200 + y);
            y += 15;
        }
    }


    static void assassinSacrifice() {
        int interID = 145500;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 125;
        int y = 45;
        tab.totalChildren(15);

        addSpriteLoader(id, 1595);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Assassins Guild", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 127 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 230 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 230 + x, 3 + y);
        id++;

        hoverButton(id, 1457, 1458, "Sacrifice", 2, 0xff8624, "Sacrifice");
        tab.child(c++, id++, 68 + x, 214 + y);

        addText(id, "Information", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 127 + x, 29 + y);

        addText(id, "Requirements", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 127 + x, 140 + y);


        for (int i = 0; i < 5; i++) {
            addText(id, "Information", tda, 1, 16750643, false, true);
            tab.child(c++, id++, 16 + x, 50 + y);
            y += 15;
        }

        y = 45;

        for (int i = 0; i < 3; i++) {
            addText(id, "Information", tda, 1, 16750643, false, true);
            tab.child(c++, id++, 16 + x, 160 + y);
            y += 15;
        }

    }


    static void assassinCostumePick() {
        int interID = 145600;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 10;
        int y = 10;
        tab.totalChildren(11);

        addSpriteLoader(id, 1597);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Choose an Assassin Costume", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 245 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 468 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 468 + x, 3 + y);
        id++;

        hoverButton(id, 1457, 1458, "Faceless", 2, 0xff8624, "Faceless");
        tab.child(c++, id++, 26 + x, 271 + y);

        hoverButton(id, 1457, 1458, "Death Lotus", 2, 0xff8624, "Death Lotus");
        tab.child(c++, id++, 184 + x, 271 + y);

        hoverButton(id, 1457, 1458, "Shadow Hunter", 2, 0xff8624, "Shadow Hunter");
        tab.child(c++, id++, 342 + x, 271 + y);

        addText(id, "You may only pick one costume (They all provide 25% Damage boost and 45% Drop Rate Boost)", tda, 0, 16750643, true, true);
        tab.child(c++, id++, 245 + x, 28 + y);

        addNpcOld(id, 10007);
        tab.child(c++, id++, 20 + x, 155 + y);

        addNpcOld(id, 10008);
        tab.child(c++, id++, 178 + x, 155 + y);

        addNpcOld(id, 10009);
        tab.child(c++, id++, 336 + x, 155 + y);


    }


    static void partyChest() {
        int interID = 146000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 63;
        int y = 13;
        tab.totalChildren(18);

        addSpriteLoader(id, 1598);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Party Drop Chest", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 192 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 359 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 359 + x, 3 + y);
        id++;


        addText(id, "Chest Dropping items in: 1hr 20mins", tda, 1, 16750643, true, true);
        tab.child(c++, id++, 192 + x, 23 + y);

        addText(id, "Items Being Dropped", tda, 1, 16750643, true, true);
        tab.child(c++, id++, 185 + x, 44 + y);
        addText(id, "Contribute to Chest", tda, 1, 16750643, true, true);
        tab.child(c++, id++, 185 + x, 229 + y);
        addText(id, "(Once you press Accept items can't be taken out of the chest)", tda, 0, 0x999896, true, true);
        tab.child(c++, id++, 185 + x, 285 + y);


        hoverButton(id, 1599, 1600, "Accept", 2, 0xff8624, "Accept");
        tab.child(c++, id++, 299 + x, 254 + y);

        tab.child(c++, 146050, 12 + x, 62 + y);

        for (int r = 0; r < 8; r++) {
            addToItemGroup(id, 1, 1, 1, 1, true, new String[]{"Remove", null, null, null, null}); //
            tab.child(c++, id++, 15 + x, 253 + y);
            x += 35;
        }

        interID = 146050;
        RSInterface scroll1 = addInterface(interID);
        scroll1.width = 358 - 16;
        scroll1.height = 157;
        scroll1.scrollMax = 1000;
        scroll1.totalChildren(400);
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;

        for (int i = 0; i < 50; i++) {
            for (int r = 0; r < 8; r++) {
                addToItemGroup(id, 1, 1, 1, 1, false, new String[]{null, null, null, null, null}); //
                scroll1.child(c++, id++, 5 + x, 3 + y);
                x += 43;
            }
            x = 0;
            y += 40;
        }

    }


    public static void raffleInterface() {
        int interID = 147000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 95;
        int y = 40;
        tab.totalChildren(27);

        addSpriteLoader(id, 1601);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 295 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 295 + x, 3 + y);
        id++;

        String[] tabs = new String[]{"Perks", "Voting", "Donating"};

        for (int i = 0; i < tabs.length; i++) {
            addConfigButtonWSpriteLoader(id, interID, 1506, 1507, 79, 20, tabs[i], i, 5, 4511);
            tab.child(c++, id++, 9 + x, 26 + y);
            addText(id, tabs[i], 0xFFA500, false, true, 100, tda, 1);
            tab.child(c++, id++, 14 + x, 29 + y);
            x += 79;
        }
        x = 95;

        addText(id, "Raffles End:", tda, 0, 0xFF981F, true, true);
        tab.child(c++, id++, 277 + x, 22 + y);
        addText(id, "@whi@5d 2h 10m", tda, 0, 0xFF981F, true, true);
        tab.child(c++, id++, 277 + x, 34 + y);

        addText(id, "Weekly Raffle", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 160 + x, 4 + y);


        addText(id, "Information", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 95 + x, 60 + y);
        addText(id, "Obtain entries by: \\n@whi@Donating to Server Perks", tda, 0, 0xFF981F, true, true);
        tab.child(c++, id++, 95 + x, 79 + y);
        addText(id, "Total Entries: @whi@102", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 95 + x, 110 + y);
        addText(id, "Players Entered: @whi@2", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 95 + x, 128 + y);


        addText(id, "Rewards", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 95 + x, 149 + y);
        addToItemGroup(id, 4, 3, 6, 1, false, null);
        tab.child(c++, id++, 22 + x, 170 + y);


        addText(id, "Personal", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 246 + x, 60 + y);
        addText(id, "Total Entries: @whi@100", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 246 + x, 80 + y);
        addText(id, "Entry Limit: @whi@None", tda, 1, 0xFF981F, true, true);
        tab.child(c++, id++, 246 + x, 98 + y);


        addText(id, "Top Entries", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 246 + x, 150 + y);

        for (int xy = 0; xy < 5; xy++) {
            addText(id, "1. Test", tda, 0, 0xFF981F, false, true);
            tab.child(c++, id++, 189 + x, 172 + y);
            y += 14;
        }

    }


    public static void starterTasksInterface() {
        int interID = 148000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 47;
        int y = 25;
        tab.totalChildren(5);

        addSpriteLoader(id, 1603);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 387 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 387 + x, 3 + y);
        id++;

        addText(id, "Starter Tasks", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 207 + x, 4 + y);

        tab.child(c++, 148050, 12 + x, 27 + y);


        interID = 148050;
        RSInterface scroll = addInterface(interID);
        scroll.width = 390 - 16;
        scroll.height = 244;
        scroll.scrollMax = 500;
        scroll.totalChildren(180);
        id = interID + 1;
        c = 0;
        x = 4;
        y = 4;

        for (int z = 0; z < 20; z++) {

            addSpriteLoader(id, 1604);
            scroll.child(c++, id++, 0 + x, 0 + y);

            hoverButton(id, 1599, 1600, "Claim", 2, 0xff8624, "Claim");
            scroll.child(c++, id++, 289 + x, 14 + y);

            addText(id, "Claimed", tda, 2, 0x38761d, true, true);
            scroll.child(c++, id++, 322 + x, 20 + y);

            addText(id, "Kill Stuff", tda, 0, 0xFF981F, true, true);
            scroll.child(c++, id++, 86 + x, 10 + y);

            new ProgressBar(id, 159, 20, new int[]{0x38761d}, true, true, "asd", new int[]{0x8b0000});
            scroll.child(c++, id++, 6 + x, 25 + y);

            addText(id, "(0/100)", tda, 0, 0xFFFFFF, true, true);
            scroll.child(c++, id++, 86 + x, 30 + y);

            for (int r = 0; r < 3; r++) {
                dropGroup(id, 1, 1, 1, 1); //
                scroll.child(c++, id++, 172 + x, 12 + y);
                x += 36;
            }

            y += 60;
            x = 4;
        }


    }


    public static void dailyLoginInterface() {
        int interID = 149000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 60;
        int y = 7;
        tab.totalChildren(96);

        addSpriteLoader(id, 1605);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 367 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 367 + x, 3 + y);
        id++;

        addText(id, "Vote Streak Rewards", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 194 + x, 3 + y);

        addText(id, "Time till reset: 23h 35m", tda, 0, 0xFFffff, true, true);
        tab.child(c++, id++, 325 + x, 24 + y);


        addText(id, "Vote everyday to get rewards", tda, 1, 0xFF981F, false, true);
        tab.child(c++, id++, 11 + x, 21 + y);

        int day = 1;
        x += 11;
        y += 39;

        for (int z = 0; z < 5; z++) {
            for (int r = 0; r < 6; r++) {

                addSpriteLoader(id, day <= 5 ? 1607 : day == 6 ? 1606 : 1563);
                tab.child(c++, id++, 0 + x, 0 + y);

                addText(id, "Day " + (day++), tda, 2, 0xFF981F, true, true);
                tab.child(c++, id++, 29 + x, 0 + y);

                dropGroup(id, 1, 1, 1, 1); //
                tab.child(c++, id++, 14 + x, 17 + y);
                x += 62;
            }
            y += 55;
            x = 72;
        }
    }


    public static void waveMinigameInterface() {
        int interID = 150000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 180;
        int y = 270;
        tab.totalChildren(4);

        addTransparentSpriteWSpriteLoader1(id, 1608, 150);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Wave: 10", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 75 + x, 4 + y);

        addText(id, "Tokens earned:", tda, 1, 0xFF981F, false, true);
        tab.child(c++, id++, 5 + x, 18 + y);

        addText(id, "Task: Kill monsters (0/10)", tda, 1, 0xFF981F, false, true);
        tab.child(c++, id++, 5 + x, 32 + y);

    }




    static void questStart() {
        int interID = 151000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 135;
        int y = 30;
        tab.totalChildren(16);

        addSpriteLoader(id, 1609);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Quest Selection", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 115 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 205 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 205 + x, 3 + y);
        id++;

        hoverButton(id, 1457, 1458, "Accept Quest", 2, 0xff8624, "Accept Quest");
        tab.child(c++, id++, 56 + x, 228 + y);

        addText(id, "Information", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 115 + x, 29 + y);

        addText(id, "Rewards", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 115 + x, 156 + y);


        for (int i = 0; i < 6; i++) {
            addText(id, "Information", tda, 1, 16750643, true, true);
            tab.child(c++, id++, 115 + x, 50 + y);
            y += 15;
        }

        y = 30;

        for (int i = 0; i < 3; i++) {
            addText(id, "Information", tda, 1, 16750643, true, true);
            tab.child(c++, id++, 115 + x, 175 + y);
            y += 15;
        }

    }



    static void questInfo() {
        int interID = 151500;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 95;
        int y = 35;
        tab.totalChildren(6);

        addSpriteLoader(id, 1610);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Quest Information", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 165 + x, 4 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 305 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 305 + x, 3 + y);
        id++;

        addText(id, "Information", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 165 + x, 29 + y);

        tab.child(c++, 151600, 12 + x, 47 + y);

        interID = 151600;
        RSInterface scroll = addInterface(interID);
        scroll.width = 305 - 16;
        scroll.height = 202;
        scroll.scrollMax = 500;
        scroll.totalChildren(200);
        id = interID + 1;
        c = 0;
        x = 0;
        y = 3;

        for (int i = 0; i < 200; i++) {
            addText(id, "", tda, 1, 16750643, true, true);
            scroll.child(c++, id++, 152 + x, 0 + y);
            y += 18;
        }
    }





    public static void titlesInterface() {
        int interID = 152000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 80;
        int y = 45;
        tab.totalChildren(21);

        addSpriteLoader(id, 1611);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 332 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 332 + x, 3 + y);
        id++;

        String[] tabs = new String[]{"Loyalty", "Bosses", "Misc"};

        for (int i = 0; i < tabs.length; i++) {
            addConfigButtonWSpriteLoader(id, interID, 1506, 1507, 79, 20, tabs[i], i, 5, 4511);
            tab.child(c++, id++, 9 + x, 26 + y);
            addText(id, tabs[i], 0xFFA500, false, true, 100, tda, 1);
            tab.child(c++, id++, 14 + x, 29 + y);
            x += 79;
        }
        x = 80;

        addText(id, "Titles Manager", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 180 + x, 4 + y);

        teleportText(id, "Reset title", "Select", fonts, 0, 0xFFFF00, false, true, 80, 18);
        tab.child(c++, id++, 270 + x, 29 + y);


        addText(id, "Total: (0/0)", tda, 0, 0xFF981F, false, true);
        tab.child(c++, id++, 10 + x, 6 + y);

        addText(id, "Titles", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 95 + x, 60 + y);

        addText(id, "Requirements", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 266 + x, 64 + y);

        addText(id, "Preview", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 266 + x, 138 + y);

        hoverButton(id, 1457, 1458, "Select");
        tab.child(c++, id++, 207 + x, 199 + y);

        addText(id, "Something", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 266 + x, 206 + y);

        addText(id, "- 100k Blood odin KC", tda, 0, 0xFF981F, false, true);
        tab.child(c++, id++, 189 + x, 88 + y);

        addText(id, "- Blood Odin Collection Log", tda, 0, 0xFF981F, false, true);
        tab.child(c++, id++, 189 + x, 106 + y);

        addText(id, "Test the Billionaire", tda, 1, 0xFFffff, true, true);
        tab.child(c++, id++, 266 + x, 164 + y);

        tab.child(c++, 152100, 12 + x, 78 + y);

        interID = 152100;
        RSInterface scroll = addInterface(interID);
        scroll.width = 166 - 16;
        scroll.height = 154;
        scroll.scrollMax = 500;
        scroll.totalChildren(250);
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;


        for (int i = 0; i < 50; i++) {
            addSpriteLoader(id, 1551);
            scroll.child(c++, id++, 0 + x, 0 + y);
            y += 36;
        }
        y = 0;

        for (int i = 0; i < 100; i++) {
            addSpriteLoader(id, 1596);
            scroll.child(c++, id++, 1 + x, 2 + y);
            teleportText(id, "Test title", "Select", fonts, 1, 0xFF9900, false, true, 169, 17);
            scroll.child(c++, id++, 2 + x, 2 + y);
            y += 18;
        }
    }

    public static void slayerFavourites() {
        RSInterface main = addInterface(152500);
        addSpriteLoader(152501, 1612);
        addText(152502, "Favourite Slayer Tasks", tda, 2, 16750643, true, true);

        addHoverButtonWSpriteLoader(152503, 1016, 16, 16, "Close Window", 0, 152504, 3);
        addHoveredImageWSpriteLoader(152504, 1017, 16, 16, 152505);

        String[] categories = new String[] {"Easy", "Medium", "Hard", "Boss Slayer"};
        for (int i = 0; i < 4; i++)
            hoverButton(152506+i, 1613, 1614, "Select Category", 0, 16750643, categories[i]);

        addText(152510, "Favourites", tda, 0, 16750643, true, true);

        addText(152511, "Blocked", tda, 0, 16750643, true, true);

        addText(152512, "Task List", tda, 0, 16750643, true, true);

        addText(152513, "You are more\\nlikely to get a\\nslayer task if it\\nis in your favourites!\\nYou can have up to\\n4 favourites at once.", tda, 0, 16750643, true, true);

        addText(152514, "If you have blocked\\na task, you will not\\nreceive it at all.\\nYou can have up to\\n4 blocked tasks at\\nonce.", tda, 0, 16750643, true, true);

        int child = 152515;
        for (int i = 0; i < 4; i++) {
            addText(child++, "Empty Slot", tda, 0, 16750643, false, true);
            hoverButton(child++, 1615, 1616, "Remove Favourite", 0, 16750643, "");
        }

        for (int i = 0; i < 4; i++) {
            addText(child++, "Empty Slot", tda, 0, 16750643, false, true);
            hoverButton(child++, 1615, 1616, "Remove Blocked Task", 0, 16750643, "");
        }

        RSInterface taskList = addInterface(152550);
        taskList.width = 142;
        taskList.height = 188;
        taskList.scrollMax = 400;
        taskList.totalChildren(80);
        addSpriteLoader(152551, 1619);
        addSpriteLoader(152552, 1620);
        child = 152555;
        int frame = 0;
        int y = 0;
        for (int i = 0; i < 20; i++) {
            taskList.child(frame++, (i % 2 == 0 ? 152551 : 152552), 0, y);
            addText(child++, "Task Slot", tda, 0, 16750643, false, true);
            taskList.child(frame++, child-1, 4, y+4);
            hoverButton(child++, 1617, 1617, "Favourite Task", 0, 16750643, "");
            taskList.child(frame++, child-1, 110, y+4);
            hoverButton(child++, 1618, 1618, "Block Task", 0, 16750643, "");
            taskList.child(frame++, child-1, 127, y+4);
            y+= 20;
        }

        main.totalChildren(30);
        main.child(0, 152501, 17, 24);
        main.child(1, 152502, 256, 34);
        main.child(2, 152503, 468, 33);
        main.child(3, 152504, 468, 33);
        for (int i = 0; i < 4; i++)
            main.child(4+i, 152506+i, 28+(i*114)+(i > 1 ? 2 : 0), 64);
        main.child(8, 152510, 99, 95);
        main.child(9, 152511, 246, 95);
        main.child(10, 152512, 402, 95);
        main.child(11, 152513, 99, 213);
        main.child(12, 152514, 246, 214);
        y = 111;
        frame = 13;
        child = 152515;
        for (int i = 0; i < 4; i++) {
            main.child(frame++, child++, 35, y+4);
            main.child(frame++, child++, 147, y);
            y+= 20;
        }
        y= 111;
        for (int i = 0; i < 4; i++) {
            main.child(frame++, child++, 182, y+4);
            main.child(frame++, child++, 294, y);
            y+= 20;
        }
        main.child(29, 152550, 324, 111);

    }

    private static void dailyTaskInterface() {
        RSInterface main = addInterface(153000);
        addSpriteLoader(153001, 1647);
        addText(153002, "Lunite's Daily Tasks", tda, 2, 0xff981f, true, true);


        int childId = 153007;
        String text = "Easy";
        for (int i = 0; i < 5; i++) {
            addHoverButtonWSpriteLoader(childId++, 1648, 87, 25, "Select", -1, childId, 1);
            addHoveredImageWSpriteLoader(childId++, 1649, 87, 25, childId++);
            switch (i) {
                case 1:
                    text = "Medium";
                    break;
                case 2:
                    text = "Hard";
                    break;
                case 3:
                    text = "Elite";
                    break;
                case 4:
                    text = "Master";
                    break;
            }
            addText(childId++, text, tda, 1, 0xff981f, true, true);
        }

        for (int i = 0; i < 5; i++) {
            addHoverButtonWSpriteLoader(childId++, 1654, 142, 40, "Select", -1, childId, 1);
            addHoveredImageWSpriteLoader(childId++, 1655, 142, 40, childId++);
            addText(childId++, "Task Number "+(i+1), tda, 0, 0xff981f, true, true);
        }


        addText(153064, "", tda, 0, 0xff981f, false, true);
        addText(153066, "00d 00h 00m", tda, 0, 0xffffff, false, true);

        addText(153067, "Short Task Description Here", tda, 2, 0xff981f, true, true);
        addText(153068, "Task Completion: 0/2000", tda, 0, 0xffffff, true, true);

        addText(153070, "Rewards upon completion:", tda, 0, 0xff981f, false, true);

        addHoverButtonWSpriteLoader(153071, 1652, 35, 25, "Claim Rewards", -1, 153072, 1);
        addHoveredImageWSpriteLoader(153072, 1653, 35, 25, 153073);

        addHoverButtonWSpriteLoader(153074, 1650, 35, 25, "Teleport", -1, 153075, 1);
        addHoveredImageWSpriteLoader(153075, 1651, 35, 25, 153076);

        addText(153077, "Misc. Information", tda, 2, 0xff981f, true, true);
        addText(153078, "Suggested Combat:", tda, 0, 0xff981f, true, true);
        addText(153079, "@gre@Combat Lvl 126", tda, 0, 0xff981f, true, true);
        addText(153080, "Location:", tda, 0, 0xff981f, true, true);
        addText(153081, "@gre@Safe Area", tda, 0, 0xff981f, true, true);
        addText(153082, "Teleport", tda, 2, 0xff981f, true, true);

        addToItemGroup(153083, 4, 2, 4, 4, true, new String[] { null, null, null, null, null });


        main.totalChildren(50);
        main.child(0, 153001, 17, 14);
        main.child(1, 153002, 256, 25);
        childId = 153007;
        int frame = 2;
        int x = 24;
        int y = 55;
        for (int i = 0; i < 5; i ++) {
            main.child(frame++, childId++, x, y);
            main.child(frame++, childId++, x, y);
            childId++;
            main.child(frame++, childId++, x+43, y+5);
            x+=94;
        }

        x = 31;
        y = 85;
        for (int i = 0; i < 5; i++) {
            main.child(frame++, childId++, x, y);
            main.child(frame++, childId++, x, y);
            childId++;
            main.child(frame++, childId++, x+71, y+10);
            y+= 43;
        }
        main.child(frame++, 153064, 202, 90);
        main.child(frame++, 153066, 42, 28);
        main.child(frame++, 153067, 329, 90);
        main.child(frame++, 153068, 329, 126);
        main.child(frame++, 153070, 202, 157);
        main.child(frame++, 153071, 291, 266);
        main.child(frame++, 153072, 291, 266);
        main.child(frame++, 153074, 390, 266);
        main.child(frame++, 153075, 390, 266);
        main.child(frame++, 153077, 408, 158);
        main.child(frame++, 153078, 408, 178);
        main.child(frame++, 153079, 408, 190);
        main.child(frame++, 153080, 408, 212);
        main.child(frame++, 153081, 408, 222);
        main.child(frame++, 153082, 408, 246);
        main.child(frame++, 149002, 468, 24);
        main.child(frame++, 149003, 468, 24);
        main.child(frame++, 153083, 182, 177);


    }




    public static void leaderboardInterface() {
        int interID = 154000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 40;
        int y = 25;
        tab.totalChildren(15);

        addSpriteLoader(id, 1656);
        tab.child(c++, id++, 0 + x, 0 + y);

        addHoverButtonWSpriteLoader(id, 1016, 16, 16, "Close Window", 0, id + 1, 3);
        tab.child(c++, id++, 412 + x, 3 + y);
        addHoveredImageWSpriteLoader(id, 1017, 16, 16, id + 1);
        tab.child(c++, id++, 412 + x, 3 + y);
        id++;

        String[] tabs = new String[]{"Main", "Monsters"};

        for (int i = 0; i < tabs.length; i++) {
            addConfigButtonWSpriteLoader(id, interID, 1506, 1507, 79, 20, tabs[i], i, 5, 4511);
            tab.child(c++, id++, 9 + x, 24 + y);
            addText(id, tabs[i], 0xFFA500, false, true, 100, tda, 1);
            tab.child(c++, id++, 14 + x, 27 + y);
            x += 79;
        }
        x = 40;

        addText(id, "Lunite Leaderboard", tda, 2, 16750643, true, true);
        tab.child(c++, id++, 228 + x, 4 + y);

        addText(id, "Sections", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 87 + x, 58 + y);

        addText(id, "Rank", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 192 + x, 58 + y);

        addText(id, "Value", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 256 + x, 58 + y);

        addText(id, "Username", tda, 2, 0xFF981F, true, true);
        tab.child(c++, id++, 351 + x, 58 + y);


        addTextRight(id, "Personal: ", tda, 1, 0xFF981F, true);
        tab.child(c++, id++, 420 + x, 25 + y);


        tab.child(c++, 154100, 12 + x, 76 + y);
        tab.child(c++, 154500, 170 + x, 77 + y);

        interID = 154100;
        RSInterface scroll = addInterface(interID);
        scroll.width = 150 - 16;
        scroll.height = 194;
        scroll.scrollMax = 500;
        scroll.totalChildren(150);
        id = interID + 1;
        c = 0;
        x = 0;
        y = 0;


        for (int i = 0; i < 50; i++) {
            addSpriteLoader(id, 1551);
            scroll.child(c++, id++, 0 + x, 0 + y);
            y += 36;
        }
        y = 0;

        for (int i = 0; i < 100; i++) {
            teleportText(id, "Test", "Select", fonts, 0, 0xFF9900, false, true, 169, 17);
            scroll.child(c++, id++, 2 + x, 3 + y);
            y += 18;
        }



        interID = 154500;
        RSInterface list = addTabInterface(interID);
        list.totalChildren(350);
        list.width = 252 - 16;
        list.height = 193;
        list.scrollMax = 1650;
        id = interID + 1;
        y = 0;
        c = 0;
        int j;
        for (j = 0; j < 50; j++) {
            addSpriteLoader(id, 1657);
            list.child(c++, id++, 0, 0 + y);
            y += 66;
        }
        y = 10;
        for (j = 0; j < 100; j++) {
            addText(id, "#" + (j + 1), 16751360, true, true, 100, fonts, 1);
            list.child(c++, id++, 20, y);
            addText(id, "1,000", 16751360, true, true, 100, fonts, 1);
            list.child(c++, id++, 85, y);
            addText(id, "Test", 16751360, true, true, 100, fonts, 1);
            list.child(c++, id++, 180, y);
            y += 33;
        }




    }



    static void chambersInterface() {
        int interID = 156000;
        RSInterface tab = addInterface(interID);
        int id = interID + 1;
        int c = 0;
        int x = 2;
        int y = 2;
        tab.totalChildren(9);

        addSpriteLoader(id, 498);
        tab.child(c++, id++, 0 + x, 0 + y);

        addText(id, "Time: ", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 25 + x, 4 + y);

        addText(id, "Total Damage: ", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 0 + x, 22 + y);

        addText(id, "Your Damage: ", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 0 + x, 40 + y);

        addText(id, "Deaths: ", tda, 1, 16750643, false, true);
        tab.child(c++, id++, 0 + x, 58 + y);


        addTextRight(id, "05:29", tda, 1, 0xFFFFFF, true);
        tab.child(c++, id++, 112 + x, 4 + y);

        addTextRight(id, "100M", tda, 1, 0xFFFFFF, true);
        tab.child(c++, id++, 112 + x, 22 + y);

        addTextRight(id, "75M", tda, 1, 0xFFFFFF, true);
        tab.child(c++, id++, 112 + x, 40 + y);

        addTextRight(id, "3", tda, 1, 0xFFFFFF, true);
        tab.child(c++, id++, 112 + x, 58 + y);


    }

}
