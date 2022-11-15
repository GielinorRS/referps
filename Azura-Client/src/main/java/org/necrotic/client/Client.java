package org.necrotic.client;

//    private final int[] NPC_LIST = new int[]{1265,1023,1233,1234,13479,13478,13474,13747,12343,12886,10103,606,707,252,449,452,2341,3831,185,6430,187,3779,8013,3830};

//import javafx.application.Application;
import org.apache.commons.lang3.math.NumberUtils;
import org.necrotic.Configuration;
import org.necrotic.RichPresense;
import org.necrotic.client.Settings.Load;
import org.necrotic.client.Settings.Save;
import org.necrotic.client.accounts.Account;
import org.necrotic.client.accounts.AccountManager;
import org.necrotic.client.cache.Archive;
import org.necrotic.client.cache.definition.*;
import org.necrotic.client.cache.node.Deque;
import org.necrotic.client.cache.node.Node;
import org.necrotic.client.cache.ondemand.OnDemandFetcher;
import org.necrotic.client.cache.ondemand.OnDemandRequest;
import org.necrotic.client.constants.GameFrameConstants;
import org.necrotic.client.constants.GameFrameConstants.GameFrameType;
import org.necrotic.client.constants.SizeConstants;
import org.necrotic.client.entity.player.Player;
import org.necrotic.client.entity.player.PlayerHandler;
//import org.necrotic.client.fxscenes.ItemEditorApplication;
import org.necrotic.client.graphics.SpriteLoader;
import org.necrotic.client.graphics.*;
import org.necrotic.client.graphics.fonts.*;
import org.necrotic.client.graphics.gameframe.GameFrame;
import org.necrotic.client.graphics.gameframe.GameFrame.ScreenMode;
import org.necrotic.client.graphics.gameframe.impl.ChatArea;
import org.necrotic.client.graphics.gameframe.impl.MapArea;
import org.necrotic.client.graphics.gameframe.impl.TabArea;
import org.necrotic.client.graphics.rsinterface.*;
import org.necrotic.client.io.ByteBuffer;
import org.necrotic.client.io.ISAACCipher;
import org.necrotic.client.net.Connection;
import org.necrotic.client.net.HttpDownloadUtility;
import org.necrotic.client.notification.AlertBox;
import org.necrotic.client.notification.AlertManager;
import org.necrotic.client.renderable.*;
import org.necrotic.client.tools.FileUtilities;
import org.necrotic.client.tools.ItemEditor;
import org.necrotic.client.ui.skin.SubstanceRuneLiteLookAndFeel;
import org.necrotic.client.world.*;
import org.necrotic.client.world.background.ScriptManager;
import org.necrotic.client.world.music.Class56;
import org.necrotic.client.world.music.Class56_Sub1_Sub1;
import org.necrotic.client.world.sound.*;
import org.necrotic.client.youtube.Youtube;
import org.necrotic.client.youtube.YoutubeChannel;
import org.necrotic.client.youtube.YoutubeVideo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

public class Client extends GameRenderer {

    private int broadcastMinutes;
    private String broadcastMessage;

    public static final RichPresense RICH_PRESENCE = new RichPresense();
    public static final int CACHE_INDEX_COUNT = 9;
    public static final int[] anIntArray1204 = {9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145, 58654,
            5027, 1457, 16565, 34991, 25486};
    // GameFrame.setScreenMode(ScreenMode.FIXED);
    public static final int[][] anIntArrayArray1003 = {
            {6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 2983, 54193},
            {8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003, 25239},
            {25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003},
            {4626, 11146, 6439, 12, 4758, 10270}, {4550, 20165, 43678, 16895, 28416, 12231, 947, 60359, 32433}};// skins
    public final static int[] tabInterfaceIDs = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    public static final boolean Z_BUFFER_REPRESENTATION = false;
    public static final boolean GROUND_DECORATIONS = false;
    private static final int[] anIntArray1019;
    private static final int[] IDs = {1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573,
            1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388, 1397, 1404, 1583, 12038, 1414,
            1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878, 1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521,
            1530, 1544, 1553, 1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471,
            /* Ancients */
            12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062, 12952, 13000, 13070,
            12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096};
    private static final Pattern NAME_PATTERN = Pattern.compile("@.+@");
    private static final int[] runeChildren = {1202, 1203, 1209, 1210, 1211, 1218, 1219, 1220, 1227, 1228, 1234, 1235,
            1236, 1243, 1244, 1245, 1252, 1253, 1254, 1261, 1262, 1263, 1270, 1271, 1277, 1278, 1279, 1286, 1287, 1293,
            1294, 1295, 1302, 1303, 1304, 1311, 1312, 1318, 1319, 1320, 1327, 1328, 1329, 1336, 1337, 1343, 1344, 1345,
            1352, 1353, 1354, 1361, 1362, 1363, 1370, 1371, 1377, 1378, 1384, 1385, 1391, 1392, 1393, 1400, 1401, 1407,
            1408, 1410, 1417, 1418, 1424, 1425, 1426, 1433, 1434, 1440, 1441, 1442, 1449, 1450, 1456, 1457, 1463, 1464,
            1465, 1472, 1473, 1474, 1481, 1482, 1488, 1489, 1490, 1497, 1498, 1499, 1506, 1507, 1508, 1515, 1516, 1517,
            1524, 1525, 1526, 1533, 1534, 1535, 1547, 1548, 1549, 1556, 1557, 1558, 1566, 1567, 1568, 1576, 1577, 1578,
            1586, 1587, 1588, 1596, 1597, 1598, 1605, 1606, 1607, 1616, 1617, 1618, 1627, 1628, 1629, 1638, 1639, 1640,
            6007, 6008, 6011, 8673, 8674, 12041, 12042, 12429, 12430, 12431, 12439, 12440, 12441, 12449, 12450, 12451,
            12459, 12460, 15881, 15882, 15885, 18474, 18475, 18478};
    private static final long serialVersionUID = -1913853327056220406L;
    // private static final String validUserPassChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
    // private static final String VALID_CC_NAME_KEYS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";

    private static final String VALID_AUTH_KEYS = "0123456789";
    private static final String VALID_CC_NAME_KEYS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
    public static final String validUserPassChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";

    public static boolean controlShiftTeleporting = false;
    public static int chatIncreaseY = 0, chatIncreaseX = 0;
    public static boolean MBOX = false;
    public static int log_view_dist1 = 10;
    public static int clientZoom = 0;
    /* SHIFT DROPPING */
    public static boolean shiftIsDown = false;
    public static boolean controlIsDown = false;
    public static boolean shiftDrop = true;
    public static long aLong1432;
    public static int anInt1089;
    public static int anInt1211;
    public static int anInt1401 = 256;
    public static int anInt197;
    public static int[] anIntArray1232;
    public static int[] anIntArray385 = new int[]{12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800,
            12800, 12800, 12800, 12800, 12800, 12800, 12800};
    public static Sprite[] cacheSprite;
    public static int clientHeight = 503;
    public static int clientWidth = 765;
    public static boolean flagged;
    public static Client instance;
    public static int log_view_dist = (int) (Math.log(clientWidth) / Math.log(5));
    public static int loopCycle;
    public static Player myPlayer;
    public static int openInterfaceID;
    public static int portOff;
    public static HashMap<String, Boolean> options = new HashMap<String, Boolean>();
    public static boolean tabAreaAltered;
    public static int tabID;
    public static boolean LOOP_MUSIC;
    public static boolean consoleOpen;
    public static String inputString;
    public static int[] myHeadAndJaw = new int[2];
    public static boolean forceRegionLoad;
    public static int TotalRead = 0;
    public static String hash;
    static boolean inputTaken;
    private static boolean aBoolean475;
    private static boolean aBoolean995;
    private static byte[] aByteArray347;
    private static Sound aClass1418;
    private static Class25 aClass25_1948;
    private static Sound[] aClass26Array1468 = new Sound[50];
    private static Class3_Sub7 aClass3_Sub7_1345;
    private static Class3_Sub7_Sub1 aClass3_Sub7_Sub1_1493;
    private static Class5 aClass5_932;
    private static Class56 aClass56_749;
    private static int anInt1005;
    private static int anInt1117;
    private static int anInt1134;
    private static int anInt1142;
    private static int anInt1155;
    private static int anInt116;
    private static int anInt1175;
    private static int anInt1188;
    private static int anInt1226;
    private static int anInt1288;
    private static int anInt139;
    private static int anInt1408;
    private static int anInt1478;
    private static int anInt1526;
    private static int anInt155 = 0;
    private static int anInt2200 = 0;
    private static int anInt478 = -1;
    private static int anInt720 = 0;
    private static int anInt849;
    private static int anInt854;
    private static int anInt924;
    private static int anInt986;
    private static boolean fetchMusic = false;
    private static boolean isMembers = true;
    private static ArrayList<Character> letterArray = new ArrayList<>();
    private static boolean lowDetail;
    private static byte[] musicData;
    private static int musicVolume2;
    public static String myUsername;
    private static int nodeID = 10;
    private static ByteBuffer out;
    private static String[] skillNames = {"Attack", "Constitution", "Mining", "Strength", "Agility", "Smithing",
            "Defence", "Herblore", "Fishing", "Range", "Thieving", "Cooking", "Prayer", "Crafting", "Firemaking",
            "Magic", "Fletching", "Woodcutting", "Runecrafting", "Slayer", "Farming", "Invention", "Hunter",
            "Summoning"};
    private static int soundEffectVolume = 127;
    private static int spellID = 0;
    private static RSInterface childHovered = null;
    /**
     * The line ids in order
     */
    private static int[] lines = {640, 663, 7332, 7333, 7334, 7336, 7383, 7339, 7338, 7340, 7346, 7341, 7342, 7337,
            7343, 7335, 7344, 7345, 7347, 7348, 682, 12772, 673, 7352, 17510, 7353, 12129, 8438, 12852, 15841, 7354,
            7355, 7356, 8679, 7459, 16149, 6987, 7357, 12836, 7358, 7359, 14169, 10115, 14604, 7360, 12282, 13577,
            12839, 7361, 16128, 11857, 7362, 7363, 7364, 10135, 4508, 18517, 11907, 7365, 7366, 7367, 13389, 15487,
            7368, 11132, 7369, 12389, 13974, 6027, 7370, 8137, 7371, 12345, 7372, 8115, 18684, 15499, 18306, 668, 8576,
            12139, 14912, 7373, 7374, 8969, 15352, 7375, 7376, 15098, 15592, 249, 1740, 15235, 3278, 7378, 6518, 7379,
            7380, 7381, 11858, 191, 9927, 6024, 7349, 7350, 7351, 13356

    };
    public Sprite alertBack;
    public Sprite alertBorder;
    public Sprite alertBorderH;
    public AlertManager alertManager = new AlertManager(this);
    /*
        private String getMac() throws SocketException {
            getMac1();        if (isMac() || isUnix()) {
                return "MAC USER";
            }
            InetAddress ip;
            String macA = null;
            try {

                ip = InetAddress.getLocalHost();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);

                byte[] mac = network.getHardwareAddress();
                System.out.println("mac: " + mac);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                macA = sb.toString();

            } catch (UnknownHostException e) {

                e.printStackTrace();

            } catch (SocketException e) {

                e.printStackTrace();

            }

            System.out.println("NetworkInterface.getNetworkInterfaces().nextElement(): " +
                    NetworkInterface.getNetworkInterfaces().nextElement());
            return macA;
        }*/
    private static String OS = System.getProperty("os.name").toLowerCase();

    static {
        anIntArray1019 = new int[99];
        int points = 0;

        for (int i = 0; i < 99; i++) {
            int l = i + 1;
            int i1 = (int) (l + 300D * Math.pow(2D, l / 7D));
            points += i1;
            anIntArray1019[i] = points >> 2;
        }

        anIntArray1232 = new int[32];
        points = 2;

        for (int i = 0; i < 32; i++) {
            anIntArray1232[i] = points - 1;
            points += points;
        }
    }

    public final int[] anIntArray990;
    public final String[] atPlayerActions;
    public final boolean[] atPlayerArray;
    public final List<Integer>[] chatRights;
    public final int[] compassArray1;
    public final int[] compassArray2;
    public final int[] currentExp;
    public final int[] currentStats;
    public final Decompressor[] decompressors;
    public final int[] mapImagePixelCutLeft;
    public final int[] mapImagePixelCutRight;
    public final int[] maxStats;
    public final Sprite[] modIcons;
    public final int REGULAR_WIDTH = 765, REGULAR_HEIGHT = 503, RESIZABLE_WIDTH = 800, RESIZABLE_HEIGHT = 600;
    public final int[] chatColor = new int[500];
    public final int[] curseLevelRequirements = {50, 50, 52, 54, 56, 59, 62, 65, 68, 71, 74, 76, 78, 80, 82, 84, 86,
            89, 92, 95};
    public final String[] curseName = {"Protect Item", "Sap Warrior", "Sap Ranger", "Sap Mage", "Sap Spirit",
            "Berserker", "Deflect Summoning", "Deflect Magic", "Deflect Missiles", "Deflect Melee", "Leech Attack",
            "Leech Ranged", "Leech Magic", "Leech Defence", "Leech Strength", "Leech Energy", "Leech Special Attack",
            "Wrath", "Soul Split", "Turmoil"};
    public final int[] prayerLevelRequirements = {1, 4, 7, 8, 9, 10, 13, 16, 19, 22, 25, 26, 27, 28, 31, 34, 37, 40,
            43, 44, 45, 46, 49, 52, 60, 70, 80, 80};
    public final String[] prayerName = {"Thick Skin", "Burst of Strength", "Clarity of Thought", "Sharp Eye",
            "Mystic Will", "Rock Skin", "Superhuman Strength", "Improved Reflexes", "Rapid Restore", "Rapid Heal",
            "Protect Item", "Hawk Eye", "Mystic Lore", "Steel Skin", "Ultimate Strength", "Incredible Reflexes",
            "Protect from Magic", "Protect from Missiles", "Protect from Melee", "Eagle Eye", "Mystic Might",
            "Retribution", "Redemption", "Smite", "Chivalry", "Piety", "Rigour", "Augury"};
    private final String[] consoleMessages;
    private final boolean aBoolean848;
    private final boolean aBoolean994;
    private final boolean[] aBooleanArray876;
    private final RSInterface aClass9_1059;
    private final int anInt975;
    private final int[] anIntArray1030;
    private final int[] settings;
    private final int[] myAppearance;
    private final int[] anIntArray1177 = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3};
    private final int[] anIntArray1203;
    private final int[] anIntArray873;
    private final int[] anIntArray928;
    private final int[] anIntArray965 = {0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xffffff};
    private final int[] anIntArray976;
    private final int[] anIntArray977;
    private final int[] anIntArray978;
    private final int[] anIntArray979;
    private final int[] anIntArray980;
    private final int[] anIntArray981;
    private final int[] anIntArray982;
    private final int[][][] constructRegionData;
    private final String[] aStringArray983;
    private final CRC32 crc32Instance;
    private final int[] expectedCRCs;
    private final long[] ignoreListAsLongs;
    private final MapArea mapArea = new MapArea(519, 0, 0, 0);
    private final int maxPlayers;
    private final int myPlayerIndex;
    private final Sprite[] scrollBar;
    private final Sprite[] scrollPart;
    private final int[] sound;
    private final int[] soundDelay;
    private final int[] soundType;
    private final String[] menuPlayerName = new String[500];
    /**
     * Handles the login for the player
     *
     * @param username     The username of the player
     * @param password     The password if the player
     * @param reconnecting The player is reconnecting
     */
    private final Stopwatch clickDelay = new Stopwatch();
    public AlertHandler alertHandler;
    /* Modifable x */
    public int modifiableXValue;
    /**
     * The split chat color, by default its 65535
     */
    public int splitChatColor = 0;
    public int clanChatColor = 0;
    /**
     * Is looting bag open
     */
    public boolean lootingBag = false;
    /**
     * The split chat interface is open
     */
    public boolean splitChatInterfaceOpen = false;
    public ArrayList<Partyhat> active_list = new ArrayList<>();
    public ArrayList<CustomMinimapIcon> customMinimapIcons = new ArrayList<CustomMinimapIcon>();
    public boolean doingDungeoneering;
    public boolean isMale;
    public boolean aBoolean1149;
    public boolean aBoolean954;
    public Sprite[] aClass30_Sub2_Sub1_Sub1Array1140;
    public String amountOrNameInput;
    public int anInt1009;
    public int anInt1011;
    public int minimapStatus;
    public int anInt1054;
    public int drawMultiwayIcon;
    public int drawXPwayIcon;
    public int anInt1071;
    public int systemUpdateTimer;
    public int anInt1222;
    public int anInt841;
    public int previousPacket;
    public int anInt843;
    public int anInt855;
    public int anInt933;
    public int anInt934;
    public int anInt935;
    public int[] anIntArray1072;
    public int[] anIntArray1073;
    public int[] anIntArray1180;
    public int[] anIntArray1181;
    public int[] anIntArray1182;
    public String aString844;
    public int backDialogID;
    public int regionBaseX;
    public int regionBaseY;
    public int cButtonCPos;
    public int cButtonHPos;
    public RSImageProducer chatAreaIP;
    public String[] chatMessages;
    public String[] chatNames;
    public String[] chatTitles;
    public int[] chatPosition;
    public TextDrawingArea chatTextDrawingArea;
    public TextDrawingArea aTextDrawingArea_1273;
    public int[] chatTypes;
    public int chatTypeView;
    public String[] clanMembers = new String[100];
    public String clanName;
    public Sprite compass;
    public int destX;
    public int destY;
    public int dialogID;
    public int energy = 100;
    public int friendCount;
    public long[] friendsListAsLongs;
    public int[] friendsNodeIDs;
    public RSImageProducer gameScreenIP;
    public Deque[][][] groundArray;
    public int inputDialogState;
    public int invOverlayInterfaceID;
    public int itemSelected;
    public int loadingStage;
    public boolean loggedIn;
    public RSImageProducer mapAreaIP;
    public Sprite mapDotClan;
    public Sprite mapDotFriend;
    public Sprite mapDotItem;
    public Sprite mapDotNPC;
    public Sprite mapDotPlayer;
    public Sprite mapDotTeam;
    public Sprite mapFlag;
    public Sprite mapMarker;
    public int[] menuActionID;
    public String[] menuActionName;
    public String[] menuActionTitle;
    public int[] menuActionColor;
    public int menuActionRow;
    public boolean menuOpen;
    public int menuScreenArea;
    public boolean messagePromptRaised;
    public Sprite miniMapRegions;
    public int minimapRotation;
    public int minimapZoom;
    public MouseDetection mouseDetection;
    public int myRights;
    public int gameMode;
    public RSFontSystem newSmallFont, newRegularFont, newBoldFont, newFancyFont;
    public TextDrawingArea normalText;
    public NPC[] npcArray;
    public int npcCount;
    public int[] npcIndices;
    public OnDemandFetcher onDemandFetcher;
    public int pktSize;
    public int pktType;
    public int plane;
    public Player[] playerArray;
    public int playerCount;
    public int[] playerIndices;
    public int privateChatMode;
    public String promptInput;
    public String promptMessage;
    public int publicChatMode;
    public int reportAbuseInterfaceID;
    public TextDrawingArea smallText;
    public int soundCount;
    public int spellSelected;
    public int splitPrivateChat = 1;
    public TabArea tabArea = new TabArea(516, 168, 250, 335);
    public RSImageProducer tabAreaIP;
    public int terrainRegionX;
    public int terrainRegionY;
    public int tradeMode;
    public int variousSettings[];
    public int viewRotation;
    public int xCameraCurve;
    public int xCameraPos;
    public int yCameraCurve;
    public int yCameraPos;
    public int zCameraPos;
    public boolean autoCast = false;
    public int autocastId = 0;
    public boolean oksearchingplayers = false;
    public boolean oksearchingitems = false;
    public RSInterface inputFieldSelected = null;
    public String inputTitle = null;
    public boolean shiftLogin;
    public int duelStatus;
    public int gameScreenDrawX = 4, gameScreenDrawY = 4;
    public CursorData oldCursor;
    public int positions[] = new int[2000];
    public int landScapes[] = new int[2000];
    public int objects[] = new int[2000];
    public int[] quickPrayers = new int[28];
    public int[] quickCurses = new int[20];
    public int prayerInterfaceType;
    public int[] quickConfigIDs = {630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646,
            647, 648, 649, 650, 651, 652, 653, 654, 655, 658, 659};
    public int lastItemHover = -1;
    public boolean hoverShowing = false;
    ChatArea chatArea = new ChatArea(0, 338, 516, 150);
    public ArrayList<RSInterface> parallelWidgetList = new ArrayList<>();
    boolean loginHover;
    boolean rememberMeHover;
    boolean textArea1Hover, textArea2Hover;
    boolean backButtonHover;
    boolean[] accountHovers = new boolean[3];
    int lastNpcAmt = 0;
    int skillTabHoverChild;
    MobDefinition npcDisplay = null;
    private Sprite stock;
    private AccountManager accountManager;
    private GrandExchange grandExchange;
    private long updateVengTime = 0;
    private long clientId;
    private Sprite mapIcon;
    private boolean pinEnter = false;
    private boolean confirmEnter = false;
    private String consoleInput;
    private int mapX, mapY;
    private int vengTimer = -1;
    private long lastUpdate;
    private boolean vengToggle = true;
    private int cutCounter = 0;
    private int cutAmount = 0;
    private boolean aBoolean1017;
    private boolean aBoolean1031;
    private boolean aBoolean1080;
    private boolean aBoolean1141;
    private boolean requestMapReconstruct;
    private boolean cameraViewChanged;
    private boolean aBoolean1242;
    private volatile boolean aBoolean831;
    private boolean httpFallback;
    private boolean aBoolean972;
    private byte[] aByteArray912;
    private byte[][] terrainData;
    private byte[][] objectData;
    private CollisionData[] collisionData;
    private Deque aClass19_1013;
    private Deque aClass19_1056;
    private Deque aClass19_1179;
    private Sprite aClass30_Sub2_Sub1_Sub1_931;
    private Sprite aClass30_Sub2_Sub1_Sub1_932;
    private int activeInterfaceType;
    private long aLong824;
    private long aLong953;
    private int anInt1010;
    private int currentCameraDisplayX;
    private int currentCameraDisplayY;
    private int anInt1016;
    private int walkableInterfaceId;
    private int anInt1026;
    private int anInt1036;
    private int anInt1037;
    private int anInt1039;
    private int anInt1044;// 377
    private int anInt1046;
    private int anInt1048;
    private int currentRegionX;
    private int currentRegionY;
    private int anInt1079;
    private int modifiedWidgetId;
    private int anInt1085;
    private int anInt1087;
    private int anInt1088;
    private int spinPacketX;
    private int spinPacketY;
    private int spinPacketHeight;
    private int spinPacketConstantSpeed;
    private int spinPacketVariableSpeed;
    private int anInt1129;// 377
    private int selectedSpellId;
    private int cameraRotationZ;
    private int cameraRotationLeft;
    private int cameraRotationRight;
    private int anInt1193;
    private int anInt1213;
    private int anInt1249;
    private int anInt1251;
    private int anInt1253;
    private int anInt1264;
    private int anInt1265;
    private int anInt1268;
    private int anInt1269;
    private int anInt1283;
    private int anInt1284;
    private int anInt1285;
    private int anInt1315;// 377
    private int anInt1500;// 377
    private int anInt1501;// 377
    private int anInt839;
    private int anInt886;
    private int playersToUpdateCount;
    private int anInt900;
    private int anInt913;
    private int anInt936;
    private int anInt937;
    private int anInt938;
    private int anInt945;
    private int anInt984;
    private int lastKnownPlane;
    private int anInt989;
    private int moveCameraX;
    private int moveCameraY;
    private int moveCameraZ;
    private int moveCameraSpeed;
    private int moveCameraAngle;
    private int[] mapCoordinates;
    private int[] anIntArray840;
    private int[] playersToUpdate;
    private int[][] anIntArrayArray825;
    private int[][] anIntArrayArray901;
    private int[][] anIntArrayArray929;
    private int[] unlockedItems = {};
    private RSImageProducer aRSImageProducer_1107;
    private RSImageProducer aRSImageProducer_1125;
    private Socket aSocket832;
    private ByteBuffer aStream_834;
    private ByteBuffer[] aStreamArray895s;
    private int atInventoryIndex;
    private int atInventoryInterface;
    private int atInventoryInterfaceType;
    private int atInventoryLoopCycle;
    private int[] bigX;
    private int[] bigY;
    private byte[][][] tileFlags;
    private int cameraOffsetX;
    private int cameraOffsetY;
    private Connection connection;
    private ISAACCipher connectionCipher;
    private Sprite[] crosses;
    private int crossIndex;
    private int crossType;
    private int crossX;
    private int crossY;
    private int currentSong;
    private int daysSinceLastLogin;
    private int daysSinceRecovChange;
    private int drawCount;
    private volatile boolean drawFlames;
    private volatile boolean drawingFlames;
    private int[] floorMap;
    private String floorMaps = "";
    private String[] friendsList;
    private int friendsListAction;
    private int fullscreenInterfaceID;
    private int[] fullScreenTextureArray;
    private int gameAreaWidth = 512, gameAreaHeight = 334;
    private boolean gameFrameVisible = true;
    private Sprite[] headIcons;
    private Sprite[] headIconsHint;
    private int ignoreCount;
    private ByteBuffer inputBuffer;
    private int interfaceButtonAction = 0;
    private int[][][] tileHeights;
    private int lastActiveInvInterface;
    private RSImageProducer leftFrame;
    private boolean loadingError;
    private ByteBuffer loginBuffer;
    private int loginFailures;
    //private String loginMessage1;
    //private String loginMessage2;
    private String[] loginMessages = new String[]{""};
    private int loginScreenCursorPos;
    private int loginScreenState;
    private int loginState = -1;
    private Background mapBack;
    private Sprite mapEdge;
    private Sprite vengBar;
    private Sprite[] mapFunctions;
    private Background[] mapScenes;
    private int membersInt;
    private int[] menuActionCmd1;
    private int[] menuActionCmd2;
    private int[] menuActionCmd3;
    private int menuHeight;
    private int menuOffsetX;
    private int menuOffsetY;
    private int menuWidth;
    private String message;
    private int mouseInvInterfaceIndex;
    private Sprite multiOverlay;
    private Sprite XPOverlay;
    private int musicVolume = 255;
    private String name;
    private int nextSong;
    private int[] objectMap;
    private String objectMaps = "";
    public String password;
    private int prevSong;
    private RSImageProducer rightFrame;
    private int rights;
    private boolean running;
    private ScriptManager scriptManager;
    private String selectedItemName;
    private long serverSeed;
    private Sprite[] skullIcons;
    private String spellTooltip;
    private int spellUsableOn;
    private int spriteDrawX;
    private int spriteDrawY;
    private int titleAlpha;
    private int titleHeight = -1;
    private RSImageProducer titleScreenIP;
    private int[] titleScreenOffsets = null;
    private Archive titleStreamLoader;
    private int titleWidth = -1;
    private RSImageProducer topFrame;
    private int playerId;
    private int unreadMessages;
    private int viewRotationOffset;
    private int weight;
    private boolean welcomeScreenRaised;
    private WorldController scene;
    private boolean fpsOn;
    private boolean debug;
    private Sprite bankItemDragSprite;
    private int bankItemDragSpriteX, bankItemDragSpriteY;
    private long rotateTimer = System.currentTimeMillis();
    private TextDrawingArea smallHit;
    private TextDrawingArea bigHit;
    private int currentActionMenu;
    private int loadingPercentage;
    private BufferedImage[] loadingImages;
    private int loginFade;
    private boolean resizing;
    private boolean withdrawingMoneyFromPouch;
    private int[] menuActionCmd4;
    private boolean isLoading;
    private int[][] drawTimerPos = new int[][]{{475, 90}, {-40, 225}, {260, 334} // center =
            // http://i.imgur.com/R89oEM9.png
    };
    private int showClanOptions;
    private boolean loggingIn = false;
    private boolean hintMenu;
    private String hintName;
    private int hintId;
    private int[] defPray = {0, 5, 13, 24, 25, 26, 27};
    private int[] strPray = {1, 3, 4, 6, 11, 12, 14, 19, 20, 24, 25, 26, 27};
    private int[] atkPray = {2, 3, 4, 7, 11, 12, 15, 19, 20, 24, 25, 26, 27};
    private int[] rangeAndMagePray = {3, 4, 11, 12, 19, 20, 24, 25, 26, 27};
    private int[] rangeAndMagePrayOff = {1, 2, 3, 4, 6, 7, 11, 12, 14, 15, 19, 20, 24, 25, 26, 27};
    private int[] headPray = {16, 17, 18, 21, 22, 23};
    private int[] superiorPray = {0, 1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15, 19, 20, 24, 25, 26, 27};
    private int[][] prayer = {defPray, strPray, atkPray, rangeAndMagePray, headPray};
    private int[] sapCurse = {1, 2, 3, 4, 19};
    private int[] leechCurse = {10, 11, 12, 13, 14, 15, 16, 19};
    private int[] deflectCurse = {7, 8, 9, 17, 18};
    private Entity currentTarget;
    private int itemHover = -1;
    private CasketOpening casketOpening;
    public CardPack cardPack;
    private int pinCode = -1;
    private boolean showTwoFactorAuth;
    private boolean showCaptcha;
    private String currentPhoneNumber;
    private String currentPinCode = "";
    private Sprite captcha;
    private String serial = "null";

    public Client() {
        casketOpening = new CasketOpening();
        cardPack = new CardPack();

        accountManager = new AccountManager();
        grandExchange = new GrandExchange();
        loadingImages = new BufferedImage[4];
        menuActionCmd4 = new int[500];
        setFullscreenInterfaceID(-1);
        alertHandler = new AlertHandler(this);
        chatRights = new ArrayList[500];
        chatTypeView = 0;
        cButtonHPos = -1;
        cButtonCPos = 0;
        anIntArrayArray825 = new int[104][104];
        crc32Instance = new CRC32();
        groundArray = new Deque[4][104][104];
        aBoolean831 = false;
        aStream_834 = new ByteBuffer(new byte[5000]);
        npcArray = new NPC[50000];
        npcIndices = new int[50000];
        anIntArray840 = new int[1000];
        setLoginBuffer(ByteBuffer.create());
        aBoolean848 = true;
        openInterfaceID = -1;
        currentExp = new int[Skills.SKILL_COUNT];
        httpFallback = false;
        anIntArray873 = new int[5];
        aBooleanArray876 = new boolean[5];
        drawFlames = false;
        playerId = -1;
        menuOpen = false;
        inputString = "";
        maxPlayers = 2048;
        myPlayerIndex = 2047;
        friendsNodeIDs = new int[400];
        playerArray = new Player[getMaxPlayers()];
        playerIndices = new int[getMaxPlayers()];
        playersToUpdate = new int[getMaxPlayers()];
        setaStreamArray895s(new ByteBuffer[getMaxPlayers()]);
        anIntArrayArray901 = new int[104][104];
        aByteArray912 = new byte[16384];
        currentStats = new int[Skills.SKILL_COUNT];
        ignoreListAsLongs = new long[100];
        loadingError = false;
        anIntArray928 = new int[5];
        anIntArrayArray929 = new int[104][104];
        chatTypes = new int[500];
        chatNames = new String[500];
        chatMessages = new String[500];
        chatTitles = new String[500];
        chatPosition = new int[500];
        Arrays.fill(chatTitles, "");
        Arrays.fill(chatPosition, 0);
        Arrays.fill(chatColor, 0);
        aBoolean954 = true;
        friendsListAsLongs = new long[400];
        friendsList = new String[400];
        currentSong = -1;
        drawingFlames = false;
        spriteDrawX = -1;
        spriteDrawY = -1;
        compassArray1 = new int[33];
        decompressors = new Decompressor[CACHE_INDEX_COUNT];
        variousSettings = new int[9000];
        aBoolean972 = false;
        anInt975 = 79;
        anIntArray976 = new int[anInt975];
        anIntArray977 = new int[anInt975];
        anIntArray978 = new int[anInt975];
        anIntArray979 = new int[anInt975];
        anIntArray980 = new int[anInt975];
        anIntArray981 = new int[anInt975];
        anIntArray982 = new int[anInt975];
        aStringArray983 = new String[anInt975];
        setLastKnownPlane(-1);
        anIntArray990 = new int[5];
        aBoolean994 = false;
        amountOrNameInput = "";
        setaClass19_1013(new Deque());
        aBoolean1017 = false;
        setWalkableInterfaceId(-1);
        anIntArray1030 = new int[5];
        aBoolean1031 = false;
        mapFunctions = new Sprite[100];
        dialogID = -1;
        maxStats = new int[Skills.SKILL_COUNT];
        settings = new int[7000]; // use up from 2000 for custom client configs
        isMale = true;
        mapImagePixelCutLeft = new int[152];
        anInt1054 = -1;
        setaClass19_1056(new Deque());
        compassArray2 = new int[33];
        aClass9_1059 = new RSInterface();
        mapScenes = new Background[100];
        myAppearance = new int[7];
        anIntArray1072 = new int[1000];
        anIntArray1073 = new int[1000];
        aBoolean1080 = false;
        setInputBuffer(ByteBuffer.create());
        expectedCRCs = new int[9];
        menuActionCmd2 = new int[500];
        menuActionCmd3 = new int[500];
        menuActionID = new int[500];
        menuActionCmd1 = new int[500];
        headIcons = new Sprite[20];
        skullIcons = new Sprite[7];
        headIconsHint = new Sprite[20];
        scrollPart = new Sprite[12];
        scrollBar = new Sprite[6];
        tabAreaAltered = false;
        promptMessage = "";
        atPlayerActions = new String[7]; // oh i already implemented support for 6 for u, guess we need 7th then
        atPlayerArray = new boolean[7];
        constructRegionData = new int[4][13][13];
        aClass30_Sub2_Sub1_Sub1Array1140 = new Sprite[1000];
        aBoolean1141 = false;
        aBoolean1149 = false;
        crosses = new Sprite[8];
        loggedIn = false;
        requestMapReconstruct = false;
        cameraViewChanged = false;
        myUsername = "";

        setPassword("");
        reportAbuseInterfaceID = -1;
        setaClass19_1179(new Deque());
        cameraRotationZ = 128;
        invOverlayInterfaceID = -1;
        setOut(ByteBuffer.create());
        menuActionName = new String[500];
        menuActionTitle = new String[500];
        menuActionColor = new int[500];
        anIntArray1203 = new int[5];
        sound = new int[50];
        anInt1211 = 78;
        promptInput = "";
        modIcons = new Sprite[20];
        tabID = 3;
        setInputTaken(false);
        mapImagePixelCutRight = new int[152];
        collisionData = new CollisionData[4];
        soundType = new int[50];
        aBoolean1242 = false;
        soundDelay = new int[50];
        welcomeScreenRaised = false;
        messagePromptRaised = false;
        backDialogID = -1;
        consoleInput = "";
        consoleOpen = false;
        consoleMessages = new String[50];
        bigX = new int[4000];
        bigY = new int[4000];
        loginMessages = new String[]{""};
    }

    public static boolean getOption(String s) {
        return options.get(s).booleanValue();
    }

    public static String getFileNameWithoutExtension(String fileName) {
        File tmpFile = new File(fileName);
        tmpFile.getName();
        int whereDot = tmpFile.getName().lastIndexOf('.');
        if (0 < whereDot && whereDot <= tmpFile.getName().length() - 2) {
            return tmpFile.getName().substring(0, whereDot);
        }
        return "";
    }

    public static String capitalize(String s) {
        return s.length() > 0 ? Character.toUpperCase(s.charAt(0)) + s.substring(1) : s;
    }

    private static String combatDiffColor(int myCombatLevel, int targetCombatLevel) {
        int difference = myCombatLevel - targetCombatLevel;

        if (difference < -9) {
            return "@red@";
        }

        if (difference < -6) {
            return "@or3@";
        }

        if (difference < -3) {
            return "@or2@";
        }

        if (difference < 0) {
            return "@or1@";
        }

        if (difference > 9) {
            return "@gre@";
        }

        if (difference > 6) {
            return "@gr3@";
        }

        if (difference > 3) {
            return "@gr2@";
        }

        if (difference > 0) {
            return "@gr1@";
        }

        return "@yel@";
    }

    private static final boolean constructMusic() {
        anInt720 = 20;

        try {
            aClass56_749 = new Class56_Sub1_Sub1();
        } catch (Throwable throwable) {
            return false;
        }

        return true;
    }

    private static String formatValue(double value, int digits) {
        PlayerHandler.format.setMaximumFractionDigits(digits);
        return PlayerHandler.format.format(value);
    }

    private static final void handleSounds() {
        if (aClass5_932 != null) {
            long l = System.currentTimeMillis();

            if (l > aLong1432) {
                aClass5_932.method489(l);
                int i_0_ = (int) (-aLong1432 + l);
                aLong1432 = l;

                synchronized (Client.aClass1418 != null ? Client.aClass1418 : (Client.aClass1418 = new Sound())) {
                    anInt1526 += anInt197 * i_0_;
                    int i_1_ = (anInt1526 - anInt197 * 2000) / 1000;

                    if (i_1_ > 0) {
                        if (aClass3_Sub7_1345 != null) {
                            aClass3_Sub7_1345.method380(i_1_);
                        }

                        anInt1526 -= i_1_ * 1000;
                    }
                }
            }
        }
    }

    private static String intToKOrMil(int value) {
        if (value < 0x186a0) {
            return String.valueOf(value);
        }

        if (value < 0x989680) {
            return value / 1000 + "K";
        } else {
            return value / 0xf4240 + "M";
        }
    }

    private static String intToKOrMilLongName(int i) {
        String s = String.valueOf(i);

        for (int k = s.length() - 3; k > 0; k -= 3) {
            s = s.substring(0, k) + "," + s.substring(k);
        }

        if (s.length() > 8) {
            s = "@gre@" + s.substring(0, s.length() - 8) + " million @whi@(" + s + ")";
        } else if (s.length() > 4) {
            s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
        }

        return " " + s;
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        AutoGC.init();

        SwingUtilities.invokeAndWait(() -> {
            try {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                UIManager.setLookAndFeel(new SubstanceRuneLiteLookAndFeel());
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
        portOff = 0;
        if (!Configuration.HIGH_DETAIL) {
            setLowDetail();
        } else {
            setHighDetail();
        }
        isMembers = true;
        Signlink.storeid = 32;
        try {
            Signlink.startpriv(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            System.err.printf("Unable to determine localhost for your machine [localhost=%s]%n", e.getMessage());
        }
        GameFrame.setScreenMode(ScreenMode.FIXED);
        instance = new Client();
        instance.createClientFrame(clientWidth, clientHeight);
    }

    public static Client getClient() {
        return instance;
    }

    private static int method1004(int i) {
        return (int) (Math.log(i * 0.00390625) * 868.5889638065036 + 0.5);
    }

    private static void method368(int i) {
        if (aClass56_749 != null) {
            if (anInt478 < i) {
                if (anInt720 > 0) {
                    anInt720--;

                    if (anInt720 == 0) {
                        if (aByteArray347 == null) {
                            aClass56_749.method831(256);
                        } else {
                            aClass56_749.method831(anInt1478);
                            anInt478 = anInt1478;
                            aClass56_749.method827(anInt1478, aByteArray347, 0, aBoolean475);
                            aByteArray347 = null;
                        }

                        anInt155 = 0;
                    }
                }
            } else if (anInt720 > 0) {
                anInt155 += anInt2200;
                aClass56_749.method830(anInt478, anInt155);
                anInt720--;

                if (anInt720 == 0) {
                    aClass56_749.method833();
                    anInt720 = 20;
                    anInt478 = -1;
                }
            }

            aClass56_749.method832(i - 122);
        }
    }

    private static final Class3_Sub7_Sub1 method407(Component component) {
        Client.method509(component);
        Class3_Sub7_Sub1 class3_sub7_sub1 = new Class3_Sub7_Sub1();
        method484(class3_sub7_sub1);
        return class3_sub7_sub1;
    }

    private static final synchronized void method484(Class3_Sub7 class3_sub7) {
        aClass3_Sub7_1345 = class3_sub7;
    }

    public static final synchronized void method486(int[] is, int i) {
        int i_2_ = 0;
        i -= 7;

        while (i_2_ < i) {
            is[i_2_++] = 0;
            is[i_2_++] = 0;
            is[i_2_++] = 0;
            is[i_2_++] = 0;
            is[i_2_++] = 0;
            is[i_2_++] = 0;
            is[i_2_++] = 0;
            is[i_2_++] = 0;
        }

        i += 7;

        while (i_2_ < i) {
            is[i_2_++] = 0;
        }

        if (aClass3_Sub7_1345 != null) {
            aClass3_Sub7_1345.method378(is, 0, i);
        }

        method689(i);
    }

    private static final synchronized void method49() {
        if (musicIsntNull()) {
            if (fetchMusic) {
                byte[] is = musicData;

                if (is != null) {
                    if (anInt116 >= 0) {
                        method684(aBoolean995, anInt116, musicVolume2, is);
                    } else if (anInt139 >= 0) {
                        method899(anInt139, -1, aBoolean995, is, musicVolume2);
                    } else {
                        method853(musicVolume2, is, aBoolean995);
                    }

                    fetchMusic = false;
                }
            }

            method368(0);
        }
    }

    public static final synchronized void method493(int i) {
        if (aClass3_Sub7_1345 != null) {
            aClass3_Sub7_1345.method380(i);
        }

        method689(i);
    }

    private static final void method509(Component component) {
        try {
            Class5_Sub2 class5_sub2 = new Class5_Sub2_Sub2();
            class5_sub2.method502(2048);
            aClass5_932 = class5_sub2;
        } catch (Throwable throwable) {
            try {
                aClass5_932 = new Class5_Sub2_Sub1(component);
            } catch (Throwable throwable_16_) {
                do {
                    if (System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") >= 0) {
                        try {
                            aClass5_932 = new Class5_Sub1();
                        } catch (Throwable throwable_17_) {
                            break;
                        }

                        return;
                    }
                } while (false);

                aClass5_932 = new Class5(8000);
            }
        }
    }

    private static final synchronized void method55(boolean bool) {
        if (musicIsntNull()) {
            method891(bool);
            fetchMusic = false;
        }
    }

    public static final int method670(int i, int i_0_) {
        if (i > i_0_) {
            int i_2_ = i_0_;
            i_0_ = i;
            i = i_2_;
        }

        for (int i_3_; i != 0; i = i_3_) {
            i_3_ = i_0_ % i;
            i_0_ = i;
        }

        return i_0_;
    }

    private static final void method684(boolean bool, int i, int i_2_, byte[] is) {
        if (aClass56_749 != null) {
            if (anInt478 >= 0) {
                anInt2200 = i;

                if (anInt478 != 0) {
                    int i_4_ = method1004(anInt478);
                    i_4_ -= anInt155;
                    anInt720 = (i_4_ + 3600) / i;

                    if (anInt720 < 1) {
                        anInt720 = 1;
                    }
                } else {
                    anInt720 = 1;
                }

                aByteArray347 = is;
                anInt1478 = i_2_;
                aBoolean475 = bool;
            } else if (anInt720 == 0) {
                method853(i_2_, is, bool);
            } else {
                anInt1478 = i_2_;
                aBoolean475 = bool;
                aByteArray347 = is;
            }
        }
    }

    private static final void method689(int i) {
        Client.anInt1408 += i;

        while (Client.anInt1408 >= Client.anInt197) {
            Client.anInt1408 -= Client.anInt197;
            anInt1526 -= anInt1526 >> 2;
        }

        anInt1526 -= i * 1000;

        if (anInt1526 < 0) {
            anInt1526 = 0;
        }
    }

    public static final void method790() {
        if (aClass56_749 != null) {
            method891(false);

            if (anInt720 > 0) {
                aClass56_749.method831(256);
                anInt720 = 0;
            }

            aClass56_749.method828();
            aClass56_749 = null;
        }
    }

    private static final void method853(int i_2_, byte[] is, boolean bool) {
        if (aClass56_749 != null) {
            if (anInt478 >= 0) {
                aClass56_749.method833();
                anInt478 = -1;
                aByteArray347 = null;
                anInt720 = 20;
                anInt155 = 0;
            }

            if (is != null) {
                if (anInt720 > 0) {
                    aClass56_749.method831(i_2_);
                    anInt720 = 0;
                }

                anInt478 = i_2_;
                aClass56_749.method827(i_2_, is, 0, bool);
            }
        }
    }

    private static final void method891(boolean bool) {
        method853(0, null, bool);
    }

    private static final void method899(int i, int i_29_, boolean bool, byte[] is, int i_30_) {
        if (aClass56_749 != null) {
            if (i_29_ >= (anInt478 ^ 0xffffffff)) {
                i -= 20;

                if (i < 1) {
                    i = 1;
                }

                anInt720 = i;

                if (anInt478 == 0) {
                    anInt2200 = 0;
                } else {
                    int i_31_ = method1004(anInt478);
                    i_31_ -= anInt155;
                    anInt2200 = (anInt2200 - 1 + i_31_ + 3600) / anInt2200;
                }

                aBoolean475 = bool;
                aByteArray347 = is;
                anInt1478 = i_30_;
            } else if (anInt720 != 0) {
                aBoolean475 = bool;
                aByteArray347 = is;
                anInt1478 = i_30_;
            } else {
                method853(i_30_, is, bool);
            }
        }
    }

    private static final void method900(int i) {
        if (aClass56_749 != null) {
            if (anInt720 == 0) {
                if (anInt478 >= 0) {
                    anInt478 = i;
                    aClass56_749.method830(i, 0);
                }
            } else if (aByteArray347 != null) {
                anInt1478 = i;
            }
        }
    }

    private static final boolean musicIsntNull() {
        return aClass56_749 != null;
    }

    public static void setLowDetail() {
        setLowDetail(true);
        WorldController.lowDetail = true;
        Rasterizer.lowDetail = false;
        ObjectManager.lowDetail = true;
        ObjectDefinition.lowDetail = true;
        Configuration.HIGH_DETAIL = false;
        Configuration.hdTexturing = false;
        Configuration.hdMinimap = false;
        Configuration.hdShading = true;
    }

    public static void setHighDetail() {
        setLowDetail();
/*
        WorldController.lowDetail = false;
        Rasterizer.lowDetail = false;
        ObjectManager.lowDetail = false;
        ObjectDefinition.lowDetail = false;
        Configuration.hdTexturing = true;
        Configuration.hdMinimap = true;// k
        Configuration.hdShading = true;
        Configuration.HIGH_DETAIL = true;*/
    }

    public static void setTab(int id) {
        tabID = id;
        tabAreaAltered = true;
    }

    private static final void setVolume(int i) {
        if (musicIsntNull()) {
            if (fetchMusic) {
                musicVolume2 = i;
            } else {
                method900(i);
            }
        }
    }

    public static final void sleep(long time) {
        if (time > 0L) {
            if (time % 10L != 0L) {
                threadSleep(time);
            } else {
                threadSleep(time - 1L);
                threadSleep(1L);
            }
        }
    }

    private static final void threadSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
        }
    }

    public static String getClipboardContents() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
            /*
             * } if (myPrivilege >= 2) { return result; } else {
             */
            return result;
        }
        return "";
    }

    public static void setClipboardContents(String aString) {
        StringSelection stringSelection = new StringSelection(aString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static void disableInputFields() {
        for (int i = 0; i < 20; i++) {
            if (RSInterface.inputFields[i] != null) {
                RSInterface.inputFields[i].inputToggled = false;
                if (RSInterface.inputFields[i].inputText.equals("")) {
                    RSInterface.inputFields[i].inputText = RSInterface.inputFields[i].defaultText;
                }
            }
        }
    }

    public static String optimizeText(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                s = String.format("%s%s", Character.toUpperCase(s.charAt(0)), s.substring(1));
            }
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                if (i + 1 < s.length()) {
                    s = String.format("%s%s%s", s.subSequence(0, i + 1), Character.toUpperCase(s.charAt(i + 1)),
                            s.substring(i + 2));
                }
            }
        }
        return s.replace("_", " ");
    }

    public static String capitalizeFirstChar(String s) {
        try {
            if (s != "")
                return (s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase()).trim();
        } catch (Exception e) {
        }
        return s;
    }

    public static String getStars(String s) {
        StringBuffer stars = new StringBuffer();
        for (int i = 0; i < s.length(); i++)
            stars.append("*");
        return stars.toString();
    }

    public static int getChatColor(String hex) {
        int convertHexCode = Integer.parseInt(hex, 16);
        return convertHexCode;
    }

    protected static void processLoadingError(String... msgs) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    Signlink.release();
                    FileUtilities.delete(Signlink.getCacheDirectory().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    showErrorScreen(instance,
                            "A fatal error occured while attempting to fix the previous loading error",
                            "Please screenshot this message and report it to Crimson immediately", e.getMessage());
                }
            }
        });

        showErrorScreen(instance, msgs);

        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    private static void showErrorScreen(Client client, String... messages) {
        client.method4(1);
        Graphics g = canvas.getGraphics();
        Color titleTextColor = new Color(205, 200, 50);
        Color textColor = new Color(30, 191, 30);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 765, 503);
        g.setFont(new Font("Verdana", 1, 18));
        g.setColor(titleTextColor);
        g.drawString("An error has occured while starting " + Configuration.CLIENT_NAME + "...", 30, 35);
        g.drawLine(30, 40, 645, 40);
        g.setFont(new Font("Arial", 1, 90));
        g.setColor(textColor);
        g.setFont(new Font("Verdana", 1, 16));
        int startingX = 30, startingY = 40;
        for (String message : messages) {
            startingY += 40;
            g.drawString(message, startingX, startingY);
        }
    }

    public static final byte[] ReadFile(String s) {
        try {
            byte abyte0[];
            File file = new File(s);
            int i = (int) file.length();
            abyte0 = new byte[i];
            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
            datainputstream.readFully(abyte0, 0, i);
            datainputstream.close();
            TotalRead++;
            return abyte0;
        } catch (Exception e) {
            System.out.println(new StringBuilder().append("Read Error: ").append(s).toString());
            return null;
        }
    }

    public static void loadGoals(String name) {
        try {
            File file = new File(Signlink.getCacheDirectory().toString() + "/" + name.trim().toLowerCase() + ".goals");
            if (!file.exists()) {
                for (int index = 0; index < Skills.goalData.length; index++) {
                    Skills.goalData[index][0] = -1;
                    Skills.goalData[index][1] = -1;
                    Skills.goalData[index][2] = -1;
                }
                return;
            }
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            for (int index = 0; index < Skills.goalData.length; index++) {
                Skills.goalData[index][0] = in.readInt();
                Skills.goalData[index][1] = in.readInt();
                Skills.goalData[index][2] = in.readInt();
                Skills.goalType = in.readUTF();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getRandom(int number, boolean greaterThan0) {
        Random random = new Random();
        int randomNr = random.nextInt(number) + (greaterThan0 ? 1 : 0);
        return randomNr;
    }

    public static ByteBuffer getOut() {
        return out;
    }

    public static void setOut(ByteBuffer out) {
        Client.out = out;
    }

    public static boolean isLowDetail() {
        return lowDetail;
    }

    public static void setLowDetail(boolean lowDetail) {
        Client.lowDetail = lowDetail;
    }

    public static int getAnInt1226() {
        return anInt1226;
    }

    public static void setAnInt1226(int anInt1226) {
        Client.anInt1226 = anInt1226;
    }

    public static int getAnInt1155() {
        return anInt1155;
    }

    public static void setAnInt1155(int anInt1155) {
        Client.anInt1155 = anInt1155;
    }

    public static int getAnInt1188() {
        return anInt1188;
    }

    public static void setAnInt1188(int anInt1188) {
        Client.anInt1188 = anInt1188;
    }

    public static int getAnInt924() {
        return anInt924;
    }

    public static void setAnInt924(int anInt924) {
        Client.anInt924 = anInt924;
    }

    public static int getAnInt1288() {
        return anInt1288;
    }

    public static void setAnInt1288(int anInt1288) {
        Client.anInt1288 = anInt1288;
    }

    public static int getAnInt986() {
        return anInt986;
    }

    public static void setAnInt986(int anInt986) {
        Client.anInt986 = anInt986;
    }

    public static int getAnInt1134() {
        return anInt1134;
    }

    public static void setAnInt1134(int anInt1134) {
        Client.anInt1134 = anInt1134;
    }

    public static int getAnInt1175() {
        return anInt1175;
    }

    public static void setAnInt1175(int anInt1175) {
        Client.anInt1175 = anInt1175;
    }

    public static String getMac() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = networkInterfaces.nextElement();
            byte[] hardwareAddress = ni.getHardwareAddress();
            if (hardwareAddress != null) {
                String[] hexadecimalFormat = new String[hardwareAddress.length];
                for (int i = 0; i < hardwareAddress.length; i++) {
                    hexadecimalFormat[i] = String.format("%02X", hardwareAddress[i]);
                }
                return String.join("-", hexadecimalFormat);
            }
        }
        return "badvalue";
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    }

    public int getChatBackIncreaseY() {
        return chatIncreaseY = !(GameFrame.getScreenMode() == ScreenMode.FIXED) ? getScreenHeight() - 503 : 0;
    }

    public int mouseX() {
        return super.mouseX;
    }

    public int mouseY() {
        return super.mouseY;
    }

    public boolean mouseInRegion(int x1, int x2, int y1, int y2) {
        if (super.mouseX >= x1 && super.mouseX <= x2 && super.mouseY >= y1 && super.mouseY <= y2) {
            return true;
        }
        return false;
    }

    public void addObject(int x, int y, int objectId, int face, int type, int height) {
        int mX = mapX - 6;
        int mY = mapY - 6;
        int x2 = x - mX * 8;
        int y2 = y - mY * 8;
        int i15 = 40 >> 2;
        int l17 = anIntArray1177[i15];
        if (y2 > 0 && y2 < 103 && x2 > 0 && x2 < 103) {
            method130(-1, objectId, face, l17, y2, type, height, x2, 0);
        }
    }

    public String indexLocation(int cacheIndex, int index) {
        return Signlink.getCacheDirectory().toString() + "/index" + cacheIndex + "/"
                + (index != -1 ? index + ".gz" : "");

    }

    public void repackCacheIndex(int cacheIndex) {
        System.out.println("Started repacking index " + cacheIndex + ".");
        int indexLength = new File(indexLocation(cacheIndex, -1)).listFiles().length;
        File[] file = new File(indexLocation(cacheIndex, -1)).listFiles();
        if (file == null || file.length == 0)
            return;
        try {
            for (int index = 0; index < indexLength; index++) {
                int fileIndex = Integer.parseInt(getFileNameWithoutExtension(file[index].toString()));
                byte[] data = fileToByteArray(cacheIndex, fileIndex);
                if (data != null && data.length > 0) {
                    decompressors[cacheIndex].method234(data.length, data, fileIndex);
                    System.out.println("Repacked Archive: " + cacheIndex + " File: " + fileIndex + ".");
                } else {
                    System.out.println("Unable to locate index " + fileIndex + ".");
                }
            }
        } catch (Exception e) {
            System.out.println("Error packing cache index " + cacheIndex + ".");
        }
        System.out.println("Finished repacking " + cacheIndex + ".");
    }

    public void updateSetting(int settingI, boolean b) {
        int l2 = RSInterface.interfaceCache[settingI].valueIndexArray[0][1];
        variousSettings[l2] = b ? 1 : 0;
    }

    public void updateSettingsInterface() {
        updateSetting(26007, Configuration.NEW_FUNCTION_KEYS);
        updateSetting(26008, Configuration.NEW_HEALTH_BARS);
        updateSetting(26010, Configuration.NEW_CURSORS);
        updateSetting(26014, Configuration.NEW_HITMARKS);
        updateSetting(26026, Configuration.DISPLAY_HP_ABOVE_HEAD);
        updateSetting(26027, Configuration.DISPLAY_USERNAMES_ABOVE_HEAD);
        updateSetting(26029, Configuration.CONSTITUTION_ENABLED);
        updateSetting(26031, GameFrameConstants.gameframeType != GameFrameType.FRAME_525);
        updateSetting(26033, Configuration.HIGHLIGHT_USERNAME);


        updateSetting(41810, Configuration.NEW_FUNCTION_KEYS);
        updateSetting(41813, Configuration.NEW_CURSORS);
        updateSetting(41816, GameFrameConstants.gameframeType != GameFrameType.FRAME_525);
        updateSetting(41819, Configuration.NEW_HEALTH_BARS);
        updateSetting(41822, Configuration.NEW_HITMARKS);
        updateSetting(41825, Configuration.DISPLAY_HP_ABOVE_HEAD);
        updateSetting(41828, Configuration.DISPLAY_USERNAMES_ABOVE_HEAD);
        updateSetting(41831, Configuration.CONSTITUTION_ENABLED);
        updateSetting(41834, Configuration.HIGHLIGHT_USERNAME);
        updateSetting(41837, Configuration.FOG_ENABLED);
        updateSetting(41840, Configuration.GROUND_TEXT);
        updateSetting(41843, Configuration.ANIMATE_TEXTURES);
        updateSetting(41846, Configuration.PLAYER_EQUIPMENT);
        updateSetting(41849, Configuration.RENDER_PETS);
    }


    public byte[] fileToByteArray(int cacheIndex, int index) {
        try {
            if (indexLocation(cacheIndex, index).length() <= 0 || indexLocation(cacheIndex, index) == null) {
                return null;
            }
            File file = new File(indexLocation(cacheIndex, index));
            byte[] fileData = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(fileData);
            fis.close();
            return fileData;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean itemCollected(int id) {
        return unlockedItems == null ? false : Arrays.stream(unlockedItems).anyMatch(i -> i == id);
    }

    private void setAutoCastOff() {
        autoCast = false;
        autocastId = 0;
        getOut().putOpcode(185);
        getOut().putInt(6667);
    }

    private void drawConsole() {
        if (consoleOpen) {
            DrawingArea.transparentBox(334, 0, 0, 5320850, getGameComponent().getWidth(), 0, 97);
            DrawingArea.drawPixels(1, 315, 0, 16777215, getGameComponent().getWidth());
            newBoldFont.drawBasicString("-->", 11, 328, 16777215, 0, false);
            if (loopCycle % 20 < 10) {
                newBoldFont.drawBasicString(consoleInput + "|", 38, 328, 16777215, 0, false);
            } else {
                newBoldFont.drawBasicString(consoleInput, 38, 328, 16777215, 0, false);
            }
        }
    }

    private void drawConsoleArea() {
        if (consoleOpen) {
            for (int i = 0, j = 308; i < 17; i++, j -= 18) {
                if (consoleMessages[i] != null) {
                    newRegularFont.drawBasicString(consoleMessages[i], 9, j, 16777215, 0, false);
                    // textDrawingArea.method385(16777215,consoleMessages[i], 9,
                    // j);
                }
            }
        }
    }

    /**
     * @param cacheType
     * @param index
     * @return index location
     */
    public String indexLocation(String cacheType, int index) {
        return Signlink.getCacheDirectory() + "" + cacheType + "/" + (index != -1 ? index + ".gz" : "");

    }

    /**
     * The cache packing method.
     *
     * @param type
     */
    public void repackCache(PackingTypes type) {
        System.out.println("Started repacking the " + type.getType() + ".");
        int indexLength = new File(indexLocation(type.getType(), -1)).listFiles().length;
        File[] file = new File(indexLocation(type.getType(), -1)).listFiles();
        try {
            for (int index = 0; index < indexLength; index++) {
                int fileIndex = Integer.parseInt(getFileNameWithoutExtension(file[index].toString()));
                byte[] data = fileToByteArray(type.getType(), fileIndex);
                if (data != null && data.length > 0) {
                    decompressors[type.getIndex()].method234(data.length, data, fileIndex);
                    System.out.println("Repacked " + fileIndex + ".");
                } else {
                    System.out.println("Unable to locate index " + fileIndex + ".");
                }
            }
        } catch (Exception e) {
            System.out.println("Error packing cache index " + type.getIndex() + ".");
        }
        System.out.println("Finished repacking " + type.getIndex() + ".");
    }

    /**
     * @param cacheType
     * @param index
     * @return
     */
    public byte[] fileToByteArray(String cacheType, int index) {
        try {
            if (indexLocation(cacheType, index).length() <= 0 || indexLocation(cacheType, index) == null) {
                return null;
            }
            File file = new File(indexLocation(cacheType, index));
            byte[] fileData = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(fileData);
            fis.close();
            return fileData;
        } catch (Exception e) {
            return null;
        }
    }

    public void printConsoleMessage(String s, int i) {
        if (backDialogID == -1) {
            inputTaken = true;
        }
        for (int j = 16; j > 0; j--) {
            consoleMessages[j] = consoleMessages[j - 1];
        }
        if (i == 0) {
            consoleMessages[0] = date() + " " + s;
        } else {
            consoleMessages[0] = s;
        }
    }

    public String date() {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        return sd.format(date);
    }

    private void sendCommandPacket(String cmd) {
        if (cmd.equalsIgnoreCase("cls") || cmd.equalsIgnoreCase("clear")) {
            for (int j = 0; j < 17; j++) {
                consoleMessages[j] = null;
            }
        }
        if (cmd.startsWith("items")) {
            Player.mruNodes.unlinkAll();
            ItemDefinition.clearCache();
        }
        if (cmd.startsWith("vngt")) {
            vengTimer = ((int) (49 * 0.65D));
            pushMessage("Sent vengTimer", 0, "");
        }
        if (cmd.startsWith("groundtext")) {
            Configuration.GROUND_TEXT = !Configuration.GROUND_TEXT;
            pushMessage("You've set ground item text to: " + Configuration.GROUND_TEXT + ".", 0, "");
        }
        if (cmd.startsWith("dumpnpc")) {
            if (myRights >= 1 && myRights <= 4) {
                String cutCmd = cmd.substring(8, cmd.length());
                if (NumberUtils.isNumber(cutCmd)) {
                    MobDefinition.printDefinitionsForId(Integer.parseInt(cutCmd));
                }
            }
        }
        if (cmd.startsWith("dumpid") || cmd.startsWith("dpitem")) {
            if (myRights >= 1 && myRights <= 4) {
                String cutCmd = cmd.substring(7, cmd.length());
                if (NumberUtils.isNumber(cutCmd)) {
                    ItemDefinition.printDefinitionsForId(Integer.parseInt(cutCmd));
                }
            }
        }
        String[] args = cmd.split(" ");
        String commandStart = args[0].toLowerCase();
        switch (commandStart) {
            case "npcdef":
                int npcId = Integer.parseInt(args[1]);
                MobDefinition npcDef = MobDefinition.get(npcId);
                System.out.println("NpcId: " + npcId);
                System.out.println("idle: " + (npcDef.standAnimation));
                System.out.println("walk: " + (npcDef.walkAnimation));
                System.out.println("size: " + (npcDef.npcSizeInSquares));
                System.out.println("npcDef.npcModels[0]: " + (npcDef.npcModels[0]));


                if (npcDef.npcModels[0] > 0) {
                    Model getfaces = Model.fetchModel(npcDef.npcModels[0], npcDef.rs3, false);

                    ArrayList<Integer> faces = new ArrayList<>();
                    if (getfaces != null){
                        System.out.println("getfaces: " + getfaces);
                    System.out.println("getfaces.numberOfTriangleFaces: " + getfaces.numberOfTriangleFaces);
                    for (int i = 0; i < getfaces.face_color.length; i++) {
                        if (!faces.contains(getfaces.face_color[i])) {
                            faces.add(getfaces.face_color[i]);
                            System.out.println("i: " + getfaces.face_color[i]);
                        }}
                    }
                }

                break;
            case "objdef":
                int objId = Integer.parseInt(args[1]);
                ObjectDefinition objDef = ObjectDefinition.forID(objId);
                System.out.println("objId: " + objId);
                System.out.println("npcDef.npcModels[0]: " + (objDef.objectModelIDs[0]));


                if (objDef.objectModelIDs[0] > 0) {
                    Model getfaces = Model.fetchModel(objDef.objectModelIDs[0]);

                    ArrayList<Integer> faces = new ArrayList<>();
                    if (getfaces != null)
                        System.out.println("getfaces: " + getfaces);
                    System.out.println("getfaces.numberOfTriangleFaces: " + getfaces.numberOfTriangleFaces);
                    for (int i = 0; i < getfaces.face_color.length; i++) {
                        if (!faces.contains(getfaces.face_color[i])) {
                            faces.add(getfaces.face_color[i]);
                            System.out.println("i: " + getfaces.face_color[i]);
                        }
                    }
                }

                break;
            case "gfxdef":
                int gfxID = Integer.parseInt(args[1]);
                SpotAnimDefinition gfxDef = SpotAnimDefinition.cache[gfxID];
                System.out.println("model: " + gfxDef.modelId);
                System.out.println("animationId: " + gfxDef.animationId);

                if (gfxDef.modelId > 0) {
                    Model getfaces = Model.fetchModel(gfxDef.modelId);

                    ArrayList<Integer> faces = new ArrayList<>();
                    if (getfaces != null)
                        System.out.println("getfaces: " + getfaces);
                    System.out.println("getfaces.numberOfTriangleFaces: " + getfaces.numberOfTriangleFaces);
                    for (int i = 0; i < getfaces.face_color.length; i++) {
                        if (!faces.contains(getfaces.face_color[i])) {
                            faces.add(getfaces.face_color[i]);
                            System.out.println("i: " + getfaces.face_color[i]);
                        }
                    }
                }

                break;
            case "m":
                int itemid = Integer.parseInt(args[1]);
                ItemDefinition ittt = ItemDefinition.get(itemid);
                System.out.println("Itemid: " + itemid);
                System.out.println("name: " + ittt.name);
                System.out.println("model: " + (ittt.modelID));
                System.out.println("male model: " + (ittt.maleEquip1));
                System.out.println("female model: " + (ittt.femaleEquip1));

                if (ittt.modelID > 0) {
                    Model getfaces = Model.fetchModel(ittt.modelID, ittt.rs3, false);
                    ArrayList<Integer> faces = new ArrayList<>();
                    System.out.println("size: " + getfaces.numberOfVerticeCoordinates);

                    if (getfaces != null)
                        System.out.println("getfaces: " + getfaces);
                    for (int i = 0; i < getfaces.face_color.length; i++) {
                        if (!faces.contains(getfaces.face_color[i])) {
                            faces.add(getfaces.face_color[i]);
                            System.out.println("i: " + getfaces.face_color[i]);
                        }
                    }
                }
                break;
    /*        case "itemtool":
                System.out.println("ERE");
                Client.currentlyEditedItemId = Integer.parseInt(args[1]);
                if (Client.currentlyEditedItemId == 0) {
                    Client.itemEditing = false;
                    System.out.println("Edited item is 0");
                } else {
                    System.out.println("True");
                    Client.itemEditing = true;
                    // Client.needDrawTabArea = true;
                    RSInterface rsi = RSInterface.interfaceCache[3214];
                    for (int slot = 0; slot < 28; slot++) {
                        rsi.inv[slot] = Client.currentlyEditedItemId + 1;
                        rsi.invStackSizes[slot] = 1;
                    }
                    rsi = RSInterface.interfaceCache[1688];
                    for (int slot = 0; slot < 13; slot++) {
                        rsi.inv[slot] = Client.currentlyEditedItemId + 1;
                        rsi.invStackSizes[slot] = 1;
                    }


                    new Thread(() -> {
                        Application.launch(ItemEditorApplication.class);
                    }).start();
                }
                break;*/

        }


        switch (cmd) {

            case "repackanims":
                repackCache(PackingTypes.ANIMATIONS);
                break;
            case "repackmodels":
                repackCache(PackingTypes.MODELS);
                break;
            case "repacksounds":
                repackCache(PackingTypes.SOUNDS);
                break;
            case "repackmaps":
                repackCache(PackingTypes.MAPS);
                break;
            case "repackindex4":
                repackCacheIndex(4);

                // loadRegion();
                break;
            case "hitmarks":
                Configuration.NEW_HITMARKS = !Configuration.NEW_HITMARKS;
                break;
            case "customobj":
                CustomObjects.spawn();
                break;
            case "cursors":
                Configuration.NEW_CURSORS = !Configuration.NEW_CURSORS;
                break;
            case "debug":
                debug = !debug;
                break;
            case "printvertices":
                Model playerModel = myPlayer.getRotatedModel();
                int vertices = playerModel.numberOfVerticeCoordinates;
                int faces = playerModel.numberOfTriangleFaces;

                System.out.println("vertices " + vertices + " faces " + faces);
                break;
            case "fps":
            case "data":
                fpsOn = !fpsOn;
                break;

            case "dumpitemnames":
                ItemDefinition.dump();
                break;

            case "rsi":
                SpriteLoader.loadSprites();
                cacheSprite = SpriteLoader.sprites;
                SpriteLoader.sprites = null;
                Archive streamLoader_1 = getArchive(3, "interface", "interface", expectedCRCs[3], 35);
                Archive mediaArchive = getArchive(4, "2d graphics", "media", expectedCRCs[4], 40);
                TextDrawingArea[] aclass30_sub2_sub1_sub4s = {smallText, normalText, chatTextDrawingArea,
                        aTextDrawingArea_1273};
                RSFontSystem[] newFonts = {newSmallFont, newRegularFont, newBoldFont};
                RSInterface.unpack(streamLoader_1, aclass30_sub2_sub1_sub4s, mediaArchive, newFonts);
                break;
        }
        /** Add Commands Here **/
        if (loggedIn) {
            getOut().putOpcode(103);
            getOut().putByte(cmd.length() + 1);
            getOut().putString(cmd);
        }
    }

    private void addFriend(long nameAsLong) {
        try {
            if (nameAsLong == 0L) {
                return;
            }

            if (friendCount >= 200) {
                pushMessage("Your friendlist is full. Max of 200", 0, "");
                return;
            }

            String s = TextClass.fixName(TextClass.nameForLong(nameAsLong));

            if (s != null) {
                if (s.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    s = name.substring(prefixSubstring);
                }
                if (s.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    s = name.substring(prefixSubstring);
                }
            }

            for (int i = 0; i < friendCount; i++) {
                if (friendsListAsLongs[i] == nameAsLong) {
                    pushMessage(s + " is already on your friend list", 0, "");
                    return;
                }
            }

            for (int i = 0; i < ignoreCount; i++) {
                if (ignoreListAsLongs[i] == nameAsLong) {
                    pushMessage("Please remove " + s + " from your ignore list first", 0, "");
                    return;
                }
            }

            if (s.equals(myPlayer.name)) {
                return;
            } else {
                friendsList[friendCount] = s;
                friendsListAsLongs[friendCount] = nameAsLong;
                friendsNodeIDs[friendCount] = 0;
                friendCount++;
                getOut().putOpcode(188);
                getOut().putLong(nameAsLong);
                return;
            }
        } catch (RuntimeException ex) {
            Signlink.reportError("15283, " + (byte) 68 + ", " + nameAsLong + ", " + ex.toString());
            ex.printStackTrace();
        }

        throw new RuntimeException();
    }

    private void addIgnore(long nameAsLong) {
        try {
            if (nameAsLong == 0L) {
                return;
            }

            if (ignoreCount >= 100) {
                pushMessage("Your ignore list is full. Max of 100 hit", 0, "");
                return;
            }

            String name = TextClass.fixName(TextClass.nameForLong(nameAsLong));

            if (name != null) {
                if (name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    name = name.substring(prefixSubstring);
                }
                if (name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    name = name.substring(prefixSubstring);
                }
            }

            for (int i = 0; i < ignoreCount; i++) {
                if (ignoreListAsLongs[i] == nameAsLong) {
                    pushMessage(name + " is already on your ignore list", 0, "");
                    return;
                }
            }

            for (int i = 0; i < friendCount; i++) {
                if (friendsListAsLongs[i] == nameAsLong) {
                    pushMessage("Please remove " + name + " from your friend list first", 0, "");
                    return;
                }
            }

            ignoreListAsLongs[ignoreCount++] = nameAsLong;
            getOut().putOpcode(133);
            getOut().putLong(nameAsLong);
            return;
        } catch (RuntimeException ex) {
            Signlink.reportError("45688, " + nameAsLong + ", " + 4 + ", " + ex.toString());
        }

        throw new RuntimeException();
    }

    private void build3dScreenMenu() {
        if (itemSelected == 0 && spellSelected == 0) {
            menuActionName[menuActionRow] = "Walk here";
            menuActionID[menuActionRow] = 516;
            menuActionCmd2[menuActionRow] = super.mouseX;
            menuActionCmd3[menuActionRow] = super.mouseY;
            menuActionRow++;
        }
        int j = -1;
        for (int k = 0; k < Model.anInt1687; k++) {
            int modelData = Model.anIntArray1688[k];// data
            int x = modelData & 0x7f;// x
            int y = modelData >> 7 & 0x7f;// y
            int face = modelData >> 29 & 3;// face
            int index = -1;// objId
            if (face != 2) {
                index = modelData >> 14 & 32767;
            }
            if (modelData == j) {
                continue;
            }
            j = modelData;

            // objects
            if (face == 2 && scene.fetchObjectIDTagForPosition(plane, x, y, modelData) >= 0) {
                index = Model.mapObjIds[k];
                ObjectDefinition class46 = ObjectDefinition.forID(index);
                if (class46.configObjectIDs != null) {
                    class46 = class46.method580();
                }

                if (regionBaseX + x == 3090 && regionBaseY + y == 3956) {
                    menuActionName[menuActionRow] = "Pull @cya@Lever";
                    menuActionID[menuActionRow] = 502;
                    menuActionCmd1[menuActionRow] = modelData;
                    menuActionCmd2[menuActionRow] = x;
                    menuActionCmd3[menuActionRow] = y;
                    menuActionCmd4[menuActionRow] = 5959;
                    menuActionRow++;

                    menuActionName[menuActionRow] = "Examine @cya@ Lever";
                    menuActionID[menuActionRow] = 1226;
                    menuActionCmd1[menuActionRow] = 950;
                    menuActionCmd2[menuActionRow] = x;
                    menuActionCmd3[menuActionRow] = y;
                    menuActionCmd4[menuActionRow] = 5959;
                    menuActionRow++;
                    return;
                }
                if (regionBaseX + x == 2539 && regionBaseY + y == 4712) {

                    menuActionName[menuActionRow] = "Pull @cya@Lever";
                    menuActionID[menuActionRow] = 502;
                    menuActionCmd1[menuActionRow] = modelData;
                    menuActionCmd2[menuActionRow] = x;
                    menuActionCmd3[menuActionRow] = y;
                    menuActionCmd4[menuActionRow] = 5960;
                    menuActionRow++;

                    menuActionName[menuActionRow] = "Examine @cya@Lever";
                    menuActionID[menuActionRow] = 1226;
                    menuActionCmd1[menuActionRow] = 950;
                    menuActionCmd2[menuActionRow] = x;
                    menuActionCmd3[menuActionRow] = y;
                    menuActionCmd4[menuActionRow] = 5960;
                    menuActionRow++;
                    return;
                }
                if (class46 == null) {
                    continue;
                }
                if (itemSelected == 1) {
                    menuActionName[menuActionRow] = "Use " + selectedItemName + " with @cya@" + class46.name;
                    menuActionID[menuActionRow] = 62;
                    menuActionCmd1[menuActionRow] = modelData;
                    menuActionCmd2[menuActionRow] = x;
                    menuActionCmd3[menuActionRow] = y;
                    menuActionCmd4[menuActionRow] = index;
                    menuActionRow++;
                } else if (spellSelected == 1) {
                    if ((spellUsableOn & 4) == 4) {
                        menuActionName[menuActionRow] = spellTooltip + " @cya@" + class46.name;
                        menuActionID[menuActionRow] = 956;
                        menuActionCmd1[menuActionRow] = modelData;
                        menuActionCmd2[menuActionRow] = x;
                        menuActionCmd3[menuActionRow] = y;
                        menuActionCmd4[menuActionRow] = index;
                        menuActionRow++;
                    }
                } else {
                    if (class46 != null && class46.actions != null) {
                        for (int i2 = 4; i2 >= 0; i2--) {
                            if (class46.actions[i2] != null) {
                                menuActionName[menuActionRow] = class46.actions[i2] + " @cya@" + class46.name;

                                if (i2 == 0) {
                                    menuActionID[menuActionRow] = 502;
                                }

                                if (i2 == 1) {
                                    menuActionID[menuActionRow] = 900;
                                }

                                if (i2 == 2) {
                                    menuActionID[menuActionRow] = 113;
                                }

                                if (i2 == 3) {
                                    menuActionID[menuActionRow] = 872;
                                }

                                if (i2 == 4) {
                                    menuActionID[menuActionRow] = 1062;
                                }

                                menuActionCmd1[menuActionRow] = modelData;
                                menuActionCmd2[menuActionRow] = x;
                                menuActionCmd3[menuActionRow] = y;
                                menuActionCmd4[menuActionRow] = index;
                                menuActionRow++;
                            }
                        }
                    }

                    menuActionName[menuActionRow] = myRights == 4
                            ? "Examine @cya@" + class46.name + " @gre@(@whi@" + class46.type + "@gre@) (@whi@"
                            + (x + regionBaseX) + "," + (y + regionBaseY) + ") @or1@(" + class46.animationID
                            + ")" + "@gre@)"
                            : "Examine @cya@" + class46.name;
                    menuActionID[menuActionRow] = 1226;
                    menuActionCmd1[menuActionRow] = class46 == null ? -1 : class46.type << 14;
                    menuActionCmd2[menuActionRow] = x;
                    menuActionCmd3[menuActionRow] = y;
                    menuActionCmd4[menuActionRow] = index;
                    menuActionRow++;
                }
            }

            /**
             * npcs
             */
            if (face == 1) {
                NPC npc = npcArray[index];
                if (npc.definitionOverride.npcSizeInSquares == 1 && (npc.x & 0x7f) == 64 && (npc.y & 0x7f) == 64) {
                    for (int j2 = 0; j2 < npcCount; j2++) {
                        NPC npc2 = npcArray[npcIndices[j2]];
                        if (npc2 != null && npc2 != npc && npc2.definitionOverride.npcSizeInSquares == 1
                                && npc2.x == npc.x && npc2.y == npc.y) {
                            buildAtNPCMenu(npc2.definitionOverride, npcIndices[j2], y, x);
                        }
                    }

                    for (int l2 = 0; l2 < playerCount; l2++) {
                        Player player = playerArray[playerIndices[l2]];

                        if (player != null && player.x == npc.x && player.y == npc.y) {
                            buildAtPlayerMenu(x, playerIndices[l2], player, y);
                        }
                    }
                }
                buildAtNPCMenu(npc.definitionOverride, index, y, x);
            }

            /**
             * Players
             */
            if (face == 0) {
                Player player = playerArray[index];

                if ((player.x & 0x7f) == 64 && (player.y & 0x7f) == 64) {
                    for (int k2 = 0; k2 < npcCount; k2++) {
                        NPC class30_sub2_sub4_sub1_sub1_2 = npcArray[npcIndices[k2]];

                        try {
                            if (class30_sub2_sub4_sub1_sub1_2 != null
                                    && class30_sub2_sub4_sub1_sub1_2.definitionOverride.npcSizeInSquares == 1
                                    && class30_sub2_sub4_sub1_sub1_2.x == player.x
                                    && class30_sub2_sub4_sub1_sub1_2.y == player.y) {
                                buildAtNPCMenu(class30_sub2_sub4_sub1_sub1_2.definitionOverride, npcIndices[k2], y, x);
                            }
                        } catch (Exception _ex) {
                        }
                    }

                    for (int i3 = 0; i3 < playerCount; i3++) {
                        Player player1 = playerArray[playerIndices[i3]];

                        if (player1 != null && player1 != player && player1.x == player.x && player1.y == player.y) {
                            buildAtPlayerMenu(x, playerIndices[i3], player1, y);
                        }
                    }
                }

                buildAtPlayerMenu(x, index, player, y);

            }

            /**
             * i assume items
             */
            if (face == 3) {
                Deque class19 = groundArray[plane][x][y];

                if (class19 != null) {
                    for (Item item = (Item) class19.getFirst(); item != null; item = (Item) class19.getNext()) {
                        ItemDefinition itemDef = ItemDefinition.get(item.id);

                        if (itemSelected == 1) {
                            menuActionName[menuActionRow] = "Use " + selectedItemName + " with @lre@" + itemDef.name;
                            menuActionID[menuActionRow] = 511;
                            menuActionCmd1[menuActionRow] = item.id;
                            menuActionCmd2[menuActionRow] = x;
                            menuActionCmd3[menuActionRow] = y;
                            menuActionCmd4[menuActionRow] = index;
                            menuActionRow++;
                        } else if (spellSelected == 1) {
                            if ((spellUsableOn & 1) == 1) {
                                menuActionName[menuActionRow] = spellTooltip + " @lre@" + itemDef.name;
                                menuActionID[menuActionRow] = 94;
                                menuActionCmd1[menuActionRow] = item.id;
                                menuActionCmd2[menuActionRow] = x;
                                menuActionCmd3[menuActionRow] = y;
                                menuActionCmd4[menuActionRow] = index;
                                menuActionRow++;
                            }
                        } else {
                            for (int j3 = 4; j3 >= 0; j3--) {
                                if (itemDef.groundActions != null && itemDef.groundActions[j3] != null) {
                                    menuActionName[menuActionRow] = itemDef.groundActions[j3] + " @lre@" + itemDef.name;

                                    if (j3 == 0) {
                                        menuActionID[menuActionRow] = 652;
                                    }

                                    if (j3 == 1) {
                                        menuActionID[menuActionRow] = 567;
                                    }

                                    if (j3 == 2) {
                                        menuActionID[menuActionRow] = 234;
                                    }

                                    if (j3 == 3) {
                                        menuActionID[menuActionRow] = 244;
                                    }

                                    if (j3 == 4) {
                                        menuActionID[menuActionRow] = 213;
                                    }

                                    menuActionCmd1[menuActionRow] = item.id;
                                    menuActionCmd2[menuActionRow] = x;
                                    menuActionCmd3[menuActionRow] = y;
                                    menuActionCmd4[menuActionRow] = index;
                                    menuActionRow++;
                                } else if (j3 == 2) {
                                    menuActionName[menuActionRow] = "Take @lre@" + itemDef.name;
                                    menuActionID[menuActionRow] = 234;
                                    menuActionCmd1[menuActionRow] = item.id;
                                    menuActionCmd2[menuActionRow] = x;
                                    menuActionCmd3[menuActionRow] = y;
                                    menuActionCmd4[menuActionRow] = index;
                                    menuActionRow++;
                                }
                            }


                            menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name
                                    + (myRights == 4
                                    ? " (" + itemDef.id + ") @or1@(" + itemDef.maleEquip1 + ") @or2@("
                                    + itemDef.maleEquip1 + ")"
                                    : "");
                            menuActionID[menuActionRow] = 1448;
                            menuActionCmd1[menuActionRow] = item.id;
                            menuActionCmd2[menuActionRow] = x;
                            menuActionCmd3[menuActionRow] = y;
                            menuActionCmd4[menuActionRow] = index;
                            menuActionRow++;


                        }
                    }
                }
            }
        }
    }

    private void buildAtNPCMenu(MobDefinition entityDef, int index, int y, int x) {

        if (menuActionRow >= 400) {
            return;
        }

        if (entityDef.childrenIDs != null) {
            entityDef = entityDef.method161();
        }
        if (entityDef == null) {
            return;
        }
        if (!entityDef.disableRightClick) {
            return;
        }
        String s = entityDef.name;
        if (entityDef.combatLevel != 0)
            s = s + combatDiffColor(myPlayer.combatLevel, entityDef.combatLevel) + " (level: " + entityDef.combatLevel
                    + ")";

        if (itemSelected == 1) {
            menuActionName[menuActionRow] = "Use " + selectedItemName + " with @yel@" + s;
            menuActionID[menuActionRow] = 582;
            menuActionCmd1[menuActionRow] = index;
            menuActionCmd2[menuActionRow] = x;
            menuActionCmd3[menuActionRow] = y;
            menuActionRow++;
            return;
        }

        if (spellSelected == 1) {
            if ((spellUsableOn & 2) == 2) {
                menuActionName[menuActionRow] = spellTooltip + " @yel@" + s;
                menuActionID[menuActionRow] = 413;
                menuActionCmd1[menuActionRow] = index;
                menuActionCmd2[menuActionRow] = x;
                menuActionCmd3[menuActionRow] = y;
                menuActionRow++;
            }
        } else {
            if (entityDef.actions != null) {
                for (int l = 4; l >= 0; l--)
                    if (entityDef.actions[l] != null && !entityDef.actions[l].equalsIgnoreCase("attack")) {
                        menuActionName[menuActionRow] = entityDef.actions[l] + " @yel@" + s;
                        if (l == 0)
                            menuActionID[menuActionRow] = 20;
                        if (l == 1)
                            menuActionID[menuActionRow] = 412;
                        if (l == 2)
                            menuActionID[menuActionRow] = 225;
                        if (l == 3)
                            menuActionID[menuActionRow] = 965;
                        if (l == 4)
                            menuActionID[menuActionRow] = 478;
                        menuActionCmd1[menuActionRow] = index;
                        menuActionCmd2[menuActionRow] = x;
                        menuActionCmd3[menuActionRow] = y;
                        menuActionRow++;

                    }

            }

            boolean attackNPC = false;

            if (entityDef.actions != null) {
                for (int i1 = 4; i1 >= 0; i1--)
                    if (entityDef.actions[i1] != null && entityDef.actions[i1].equalsIgnoreCase("attack")) {
                        char c = '\0';
                      //  if (entityDef.id == 10000 || entityDef.id == 10001)
                       //     c = '\u07D0';
                        menuActionName[menuActionRow] = entityDef.actions[i1] + " @yel@" + s;
                        if (i1 == 0)
                            menuActionID[menuActionRow] = 20 + c;
                        if (i1 == 1)
                            menuActionID[menuActionRow] = 412 + c;
                        if (i1 == 2)
                            menuActionID[menuActionRow] = 225 + c;
                        if (i1 == 3)
                            menuActionID[menuActionRow] = 965 + c;
                        if (i1 == 4)
                            menuActionID[menuActionRow] = 478 + c;


                        menuActionCmd1[menuActionRow] = index;
                        menuActionCmd2[menuActionRow] = x;
                        menuActionCmd3[menuActionRow] = y;
                        menuActionRow++;
                        //if (entityDef.id != 10000 && entityDef.id != 10001){


                        if (myRights == 4) {
                            s += " @whi@(@gre@" + entityDef.id + "@whi@)";
                        }
                            menuActionName[menuActionRow] = "Drops @yel@" + s;
                        menuActionID[menuActionRow] = 1075;
                        menuActionCmd1[menuActionRow] = index;
                        menuActionCmd2[menuActionRow] = x;
                        menuActionCmd3[menuActionRow] = y;
                        menuActionRow += 1;
                    //}

                        attackNPC = true;
                    }

            }

            if (entityDef.id == 3062 || entityDef.id == 770 || entityDef.id == 771 || entityDef.id == 769
                    || entityDef.id == 6960 || entityDef.id == 6958 || entityDef.id == 6968 || entityDef.id == 6964
                    || entityDef.id == 350 || entityDef.id == 351 || entityDef.id == 352 || entityDef.id == 928
                    || entityDef.id == 4000 || entityDef.id == 4001 || entityDef.id == 271 || entityDef.id == 133
                    || entityDef.id == 105 || entityDef.id == 4414 || entityDef.id == 189 || entityDef.id == 4444
                    || entityDef.id == 1902 || entityDef.id == 8802 || entityDef.id == 3309 || entityDef.id == 1898
                    || entityDef.id == 302 || entityDef.id == 1890 || entityDef.id == 1894 || entityDef.id == 1893
                    || entityDef.id == 1892 || entityDef.id == 1904 || entityDef.id == 3001 || entityDef.id == 5001
                    || entityDef.id == 3377 || entityDef.id == 4969 || entityDef.id == 6913 || entityDef.id == 6919
                    || entityDef.id == 6942 || entityDef.id == 6945 || entityDef.id == 3033 || entityDef.id == 3030
                    || entityDef.id == 3031 || entityDef.id == 3032 || entityDef.id == 3034 || entityDef.id == 3035
                    || entityDef.id == 3036 || entityDef.id == 3037 || entityDef.id == 3038 || entityDef.id == 3039
                    || entityDef.id == 3040 || entityDef.id == 3047 || entityDef.id == 3048 || entityDef.id == 3050
                    || entityDef.id == 3051 || entityDef.id == 3052 || entityDef.id == 3053 || entityDef.id == 3054
                    || entityDef.id == 3055 || entityDef.id == 3056 || entityDef.id == 3057 || entityDef.id == 3058
                    || entityDef.id == 3059 || entityDef.id == 3060 || entityDef.id == 3061 || entityDef.id == 6302
                    || entityDef.id == 6303 || entityDef.id == 6304 || entityDef.id == 6305 || entityDef.id == 6306
                    || entityDef.id == 6307 || entityDef.id == 6308 || entityDef.id == 6309 || entityDef.id == 6310
                    || entityDef.id == 6312 || entityDef.id == 6313 || entityDef.id == 6314 || entityDef.id == 6315
                    || entityDef.id == 6316 || entityDef.id == 6317 || entityDef.id == 6318 || entityDef.id == 6319
                    || entityDef.id == 6320 || entityDef.id == 6311 || entityDef.id == 6322 || entityDef.id == 6323
                    || entityDef.id == 6324 || entityDef.id == 3062 || entityDef.id == 1906 || entityDef.id == 184
                    || entityDef.id == 6431 || entityDef.id == 1801 // null
                    || entityDef.id == 10 // null

                    || entityDef.id == 3062) {
                menuActionName[menuActionRow] = "@yel@Pick-up " + entityDef.name;
                menuActionID[menuActionRow] = 1075;
                menuActionCmd1[menuActionRow] = index;
                menuActionCmd2[menuActionRow] = x;
                menuActionCmd3[menuActionRow] = y;
                menuActionRow += 1;

            }
            if (myRights == 4) {
                s += " @whi@(@gre@" + entityDef.id + "@whi@)";
            }


            if (!attackNPC){

                menuActionName[menuActionRow] = "Examine @yel@" + s;
                menuActionID[menuActionRow] = 1025;
                menuActionCmd1[menuActionRow] = index;
                menuActionCmd2[menuActionRow] = x;
                menuActionCmd3[menuActionRow] = y;
                menuActionRow++;
            }


        }

    }

    private void buildAtPlayerMenu(int i, int j, Player player, int k) {
        if (player == myPlayer) {
            return;
        }

        if (menuActionRow >= 400) {
            return;
        }

        String menuTooltip = "";
        String title = "";
        String endTitle = "";

        if (player.loyaltyTitle != null && !player.loyaltyTitle.isEmpty()) {
            if (player.loyaltyTitle.contains("##")) {
                title = "";
                endTitle = " " + player.loyaltyTitle.trim().replace("##", "") + "</col>";
            } else {
                title = player.loyaltyTitle.trim() + "</col> ";
                endTitle = "";
            }
        }

        if (player.playerRights == 1) {
            menuTooltip = menuTooltip + "<ico=1>";
        }
        if (player.playerRights == 2) {
            menuTooltip = menuTooltip + "<ico=2>";
        }
        if (player.playerRights == 3) {
            menuTooltip = menuTooltip + "<ico=3>";
        }
        if (player.playerRights == 5) {
            menuTooltip = menuTooltip + "<ico=5>";
        }
        if (player.playerRights == 6) {
            menuTooltip = menuTooltip + "<ico=6>";
        }
        if (player.playerRights == 7) {
            menuTooltip = menuTooltip + "<ico=7>";
        }
        if (player.playerRights == 8) {
            menuTooltip = menuTooltip + "<ico=8>";
        }
        if (player.playerRights == 9) {
            menuTooltip = menuTooltip + "<ico=9>";
        }
        if (player.playerRights == 10) {
            menuTooltip = menuTooltip + "<ico=10>";
        }
        if (player.playerRights == 13) {
            menuTooltip = menuTooltip + "<ico=1508>";
        }
        if (player.playerRights == 15) {
            menuTooltip = menuTooltip + "<ico=1602>";
        }
        if (player.playerRights == 16) {
            menuTooltip = menuTooltip + "<ico=1658>";
        }
        if (player.playerRights == 4) {
            menuTooltip = "<ico=12>";
        }


        if (player.gameMode > 0) {
            int ironimg = -1;
            if (player.gameMode == 2) {
                ironimg = 840;
            } else if (player.gameMode == 1) {
                ironimg = 839;
            } else if (player.gameMode == 3) {
                ironimg = 838;
            } else if (player.gameMode == 4) {
                ironimg = 1509;
            }

            menuTooltip = menuTooltip + "<ico="+ironimg+">";
        }

        if (player.combatLevel == 0) {
            menuTooltip = title + player.name + endTitle + combatDiffColor(myPlayer.combatLevel, player.combatLevel) + " (level-"
                    + player.combatLevel + ")";
        } else {
            menuTooltip += title + player.name + endTitle+ combatDiffColor(myPlayer.combatLevel, player.combatLevel) + " (level-"
                    + player.combatLevel + ")";
        }

        if (itemSelected == 1) {
            menuActionName[menuActionRow] = "Use " + selectedItemName + " with @whi@" + menuTooltip;
            menuActionID[menuActionRow] = 491;
            menuActionCmd1[menuActionRow] = j;
            menuActionCmd2[menuActionRow] = i;
            menuActionCmd3[menuActionRow] = k;
            menuActionRow++;
        } else if (spellSelected == 1) {
            if ((spellUsableOn & 8) == 8) {
                menuActionName[menuActionRow] = spellTooltip + " @whi@" + menuTooltip;
                menuActionID[menuActionRow] = 365;
                menuActionCmd1[menuActionRow] = j;
                menuActionCmd2[menuActionRow] = i;
                menuActionCmd3[menuActionRow] = k;
                menuActionRow++;
            }
        } else {
            for (int index = 6; index >= 0; index--) {
                if (atPlayerActions[index] != null) {
                    menuActionName[menuActionRow] = atPlayerActions[index] + " @whi@" + menuTooltip;
                    char c = '\0';

                    if (atPlayerActions[index].equalsIgnoreCase("attack")) {
                        // if (player.combatLevel > myPlayer.combatLevel) { //1 CLICK ATTACK
                        // c = '\u07D0';
                        // }
                        if (myPlayer.team != 0 && player.team != 0) {
                            if (myPlayer.team == player.team) {
                                c = '\u07D0';
                            } else {
                                c = '\0';
                            }
                        }
                    } else if (atPlayerArray[index]) {
                        c = '\u07D0';
                    }

                    if (index == 0) {
                        menuActionID[menuActionRow] = 561 + c;
                    }

                    if (index == 1) {
                        menuActionID[menuActionRow] = 779 + c;
                    }

                    if (index == 2) {
                        menuActionID[menuActionRow] = 27 + c;
                    }

                    if (index == 3) {
                        menuActionID[menuActionRow] = 577 + c;
                    }

                    if (index == 4) {
                        menuActionID[menuActionRow] = 729 + c;
                    }

                    if (index == 5) {
                        menuActionID[menuActionRow] = 8300;
                    }
                    if (index == 6) {
                        menuActionID[menuActionRow] = 8305;
                    }
                    menuActionCmd1[menuActionRow] = j;
                    menuActionCmd2[menuActionRow] = i;
                    menuActionCmd3[menuActionRow] = k;
                    menuActionRow++;
                }
            }
        }

        for (int i1 = 0; i1 < menuActionRow; i1++) {
            if (menuActionID[i1] == 516) {
                menuActionName[i1] = "Walk here @whi@" + menuTooltip;
                return;
            }
        }
    }

    private void buildChatAreaMenu(int yOffset) {
        if (!isGameFrameVisible() || chatArea.componentHidden()) {
            return;
        }

        int messages = 0;

        for (int index = 0; index < 500; index++) {
            if (chatMessages[index] == null) {
                continue;
            }

            int currentType = chatTypes[index];
            int k1 = 70 - messages * 14 + 42 + anInt1089 + 4 + 5;
            if (k1 < -23) {
                break;
            }

            String player_name = chatNames[index];

            if (chatTypeView == 1) {
                buildPublicChat(yOffset);
                break;
            }

            if (chatTypeView == 2) {
                buildFriendChat(yOffset);
                break;
            }

            if (chatTypeView == 3 || chatTypeView == 4) {
                buildDuelorTrade(yOffset);
                break;
            }

            if (inputDialogState == 3) {
                getGrandExchange().buildItemSearch(yOffset);
                break;
            }

            if (chatTypeView == 5) {
                break;
            }

            if (player_name != null) {
                if (player_name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(player_name);
                    player_name = player_name.substring(prefixSubstring);
                }
                if (player_name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(player_name);
                    player_name = player_name.substring(prefixSubstring);
                }
            }
            if (player_name != null && player_name.startsWith("<col=")) {
                player_name = player_name.substring(player_name.indexOf("</col>") + 6);
            }
            if (currentType == 0) {
                messages++;
            }

            if ((currentType == 1 || currentType == 2) && (currentType == 1 || publicChatMode == 0
                    || publicChatMode == 1 && isFriendOrSelf(player_name))) {
                if (yOffset > k1 - 14 && yOffset <= k1
                        && !myPlayer.name.equals(NAME_PATTERN.matcher(player_name).replaceAll(""))) {
                    if (!isFriendOrSelf(player_name)) {
                        menuActionName[menuActionRow] = "Add ignore @whi@" + player_name;
                        menuActionID[menuActionRow] = 42;
                        menuActionRow++;
                        menuActionName[menuActionRow] = "Add friend @whi@" + player_name;
                        menuActionID[menuActionRow] = 337;
                        menuActionRow++;
                    } else if (isFriendOrSelf(player_name)) {
                        menuActionName[menuActionRow] = "Message @whi@" + player_name;
                        menuActionID[menuActionRow] = 2639;
                        menuActionRow++;
                    }
                }

                messages++;
            }

            if ((currentType == 3 || currentType == 7) && splitPrivateChat == 0 && (currentType == 7
                    || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(player_name))) {
                if (yOffset > k1 - 14 && yOffset <= k1) {
                    if (!isFriendOrSelf(player_name)) {
                        menuActionName[menuActionRow] = "Add ignore @whi@" + player_name;
                        menuActionID[menuActionRow] = 42;
                        menuActionRow++;
                        menuActionName[menuActionRow] = "Add friend @whi@" + player_name;
                        menuActionID[menuActionRow] = 337;
                        menuActionRow++;
                    } else if (isFriendOrSelf(player_name)) {
                        menuActionName[menuActionRow] = "Message @whi@" + player_name;
                        menuActionID[menuActionRow] = 2639;
                        menuActionRow++;
                    }

                }

                messages++;
            }

            if (currentType == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(player_name))) {
                if (yOffset > k1 - 14 && yOffset <= k1) {
                    menuActionName[menuActionRow] = "Accept trade @whi@" + player_name;
                    menuActionID[menuActionRow] = 484;
                    menuActionRow++;
                }

                messages++;
            }

            if ((currentType == 5 || currentType == 6) && splitPrivateChat == 0 && privateChatMode < 2) {
                messages++;
            }
            if (currentType == 8 && (duelStatus == 0 || duelStatus == 1 && isFriendOrSelf(player_name))) {
               /* if (yOffset > k1 - 14 && yOffset <= k1) {
                    menuActionName[menuActionRow] = "Accept challenge @whi@" + player_name;
                    menuActionID[menuActionRow] = 6;
                    menuActionRow++;
                }
*/
                messages++;
            }

            if (currentType == 21) {
                if (yOffset > k1 - 14 && yOffset <= k1) {
                    menuActionName[menuActionRow] = "Accept gamble @whi@" + player_name;
                    menuActionID[menuActionRow] = 1673;
                    menuActionRow++;
                }
                messages++;
            }

            if (currentType == 22) {
                if (yOffset > k1 - 14 && yOffset <= k1) {
                    menuActionName[menuActionRow] = "Accept raid request @whi@" + player_name;
                    menuActionID[menuActionRow] = 1773;
                    menuActionRow++;
                }
                messages++;
            }

        }
    }

    private void buildDuelorTrade(int yOffset) {
        int l = 0;

        for (int i1 = 0; i1 < 500; i1++) {
            if (chatMessages[i1] == null) {
                continue;
            }

            if (chatTypeView != 3 && chatTypeView != 4) {
                continue;
            }

            int chatType = chatTypes[i1];
            String name = chatNames[i1];
            int k1 = 70 - l * 14 + 42 + anInt1089 + 4 + 5;

            if (k1 < -23) {
                break;
            }

            if (name != null) {
                if (name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    name = name.substring(prefixSubstring);
                }
                if (name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    name = name.substring(prefixSubstring);
                }
            }

            if (chatTypeView == 3 && chatType == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(name))) {
                if (yOffset > k1 - 14 && yOffset <= k1) {
                    menuActionName[menuActionRow] = "Accept trade @whi@" + name;
                    menuActionID[menuActionRow] = 484;
                    menuActionRow++;
                }

                l++;
            }

            if (chatTypeView == 4 && chatType == 8 && (duelStatus == 0 || duelStatus == 1 && isFriendOrSelf(name))) {
              /*  if (yOffset > k1 - 14 && yOffset <= k1) {
                    menuActionName[menuActionRow] = "Accept challenge @whi@" + name;
                    menuActionID[menuActionRow] = 6;
                    menuActionRow++;
                }*/

                l++;
            }

            if (chatType == 12) {
                if (yOffset > k1 - 14 && yOffset <= k1) {
                    menuActionName[menuActionRow] = "Go-to @blu@" + name;
                    menuActionID[menuActionRow] = 915;
                    menuActionRow++;
                }

                l++;
            }

            if (chatType == 21) {
                if (yOffset > k1 - 14 && yOffset <= k1) {
                    menuActionName[menuActionRow] = "Accept gamble @whi@" + name;
                    menuActionID[menuActionRow] = 1673;
                    menuActionRow++;
                }
                l++;
            }

            if (chatType == 22) {
                if (yOffset > k1 - 14 && yOffset <= k1) {
                    menuActionName[menuActionRow] = "Accept raid request @whi@" + name;
                    menuActionID[menuActionRow] = 1773;
                    menuActionRow++;
                }
                l++;
            }

        }
    }

    private void drawParallelWidgets() {
        if (this.parallelWidgetList.size() <= 0) {
            return;
        }
        for (RSInterface widget : this.parallelWidgetList) {
            if (widget != null) {
                int xPosition = 512 / 2 - widget.width / 2;
                int yPosition = 334 / 2 - widget.height / 2;
                switch (widget.id) {
                    case 156000:
                        xPosition = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? 385 : clientWidth - 350; // 392
                        yPosition = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? 5 : 5;

                        int boxWidth = 122;
                        int boxLength = 78;

                        DrawingArea.method338(yPosition + 0, boxLength, 150, 0, boxWidth, xPosition - 1);
                        DrawingArea.method335(0x433A32, yPosition + 1, boxWidth - 2, boxLength - 2, 200, xPosition - 0);
                        break;
                    case 21005:
                        xPosition = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? 0 : clientWidth - 730; // 392
                        yPosition = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? 0 : 5;
                        break;
                    case 21100:
                        xPosition = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? 0 : clientWidth - 730; // 392
                        yPosition = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? 0 : 5;
                        break;
                    case 42400:

                        //439 : 293
                        xPosition = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? 460 : Client.clientWidth - 278 + 21;
                        yPosition = GameFrame.getScreenMode() == GameFrame.ScreenMode.FIXED ? 240 : Client.clientHeight - 170+31;

                         if (GameFrame.getScreenMode() != GameFrame.ScreenMode.FIXED && Client.instance.getWidth() >= 1000) {
                            yPosition = Client.clientHeight - 86 - 15;
                        }
                        DrawingArea.method338(yPosition + 0, 60, 150, 0, 52, xPosition - 3);
                        DrawingArea.method335(0x433A32, yPosition + 1, 50, 58, 150, xPosition - 2);

                        break;
                    case 28710:
                        if (RSInterface.interfaceCache[28714].message.length() < 1) {
                            continue;
                        }
                        xPosition = GameFrame.getScreenMode() == ScreenMode.FIXED ? 392 : clientWidth - 150; // 392
                        yPosition = GameFrame.getScreenMode() == ScreenMode.FIXED ? 280 : 180;
                        break;
                    case 25347:
                        yPosition += 185;
                        break;
                    case 48300:
                    case 48400:
                        xPosition = getScreenWidth() - 765 - (GameFrame.getScreenMode() != ScreenMode.FIXED ? 30 : 0);
                        break;
                }
                drawInterface(0, xPosition, widget, yPosition);
            }
        }
    }

    private void buildFriendChat(int yOffset) {
        int count = 0;

        for (int index = 0; index < 500; index++) {
            if (chatMessages[index] == null) {
                continue;
            }

            if (chatTypeView != 2) {
                continue;
            }

            int type = chatTypes[index];
            String player_name = chatNames[index];
            int k1 = 70 - count * 14 + 42 + anInt1089 + 4 + 5;
            if (k1 < -23) {
                break;
            }

            if (player_name != null) {
                if (player_name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    player_name = player_name.substring(prefixSubstring);
                }
                if (player_name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    player_name = player_name.substring(prefixSubstring);
                }
            }

            if ((type == 5 || type == 6) && (splitPrivateChat == 0 || chatTypeView == 2)
                    && (type == 6 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(player_name))) {
                count++;
            }

            if ((type == 3 || type == 7) && (splitPrivateChat == 0 || chatTypeView == 2)
                    && (type == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(player_name))) {
                if (yOffset > k1 - 14 && yOffset <= k1) {
                    if (!isFriendOrSelf(player_name)) {
                        menuActionName[menuActionRow] = "Add ignore @whi@" + player_name;
                        menuActionID[menuActionRow] = 42;
                        menuActionRow++;
                        menuActionName[menuActionRow] = "Add friend @whi@" + player_name;
                        menuActionID[menuActionRow] = 337;
                        menuActionRow++;
                    } else if (isFriendOrSelf(player_name)) {
                        menuActionName[menuActionRow] = "Message @whi@" + player_name;
                        menuActionID[menuActionRow] = 2639;
                        menuActionRow++;
                    }
                }

                count++;
            }
        }
    }

    private boolean buildFriendsListMenu(RSInterface class9) {
        int i = class9.contentType;

        if (i >= 1 && i <= 200 || i >= 701 && i <= 900) {
            if (i >= 801) {
                i -= 701;
            } else if (i >= 701) {
                i -= 601;
            } else if (i >= 101) {
                i -= 101;
            } else {
                i--;
            }

            menuActionName[menuActionRow] = "Remove @whi@" + friendsList[i];
            menuActionID[menuActionRow] = 792;
            menuActionRow++;
            menuActionName[menuActionRow] = "Message @whi@" + friendsList[i];
            menuActionID[menuActionRow] = 639;
            menuActionRow++;
            return true;
        }

        if (i >= 401 && i <= 500) {
            menuActionName[menuActionRow] = "Remove @whi@" + class9.message;
            menuActionID[menuActionRow] = 322;
            menuActionRow++;
            return true;
        } else {
            return false;
        }
    }

    public static boolean mousePressed;

    private void scratch(int x, int y, int radius, Sprite sprite) {
        int radiusSquare = radius * radius;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                if (dx * dx + dy * dy < radiusSquare) {
                    int finalX = x + dx;
                    int finalY = y + dy;

                    if (finalX < 0 || finalX >= sprite.myWidth) {
                        continue;
                    }
                    if (finalY < 0 || finalY >= sprite.myHeight) {
                        continue;
                    }
                    sprite.setPixels(finalX, finalY, 0);
                }
            }
        }
    }

    private void buildInterfaceMenu(int xPadding, RSInterface rsInterface, int xPos, int yPadding, int yPos,
                                    int scrollPoint) {
        if (rsInterface == null) {
            rsInterface = RSInterface.interfaceCache[21356];
        }

        if (rsInterface.type != 0 || rsInterface.children == null || rsInterface.interfaceShown || !rsInterface.isVisible()) {
            return;
        } // oh

        if (xPos < xPadding || yPos < yPadding || xPos > xPadding + rsInterface.width
                || yPos > yPadding + rsInterface.height) {
            return;
        }

        int totalChildren = rsInterface.children.length;

        for (int index = 0; index < totalChildren; index++) {
            int xSpritePos = (rsInterface.childX[index] + xPadding) - (rsInterface.sideScroll ? scrollPoint : 0);
            int ySpritePos = (rsInterface.childY[index] + yPadding) - (rsInterface.sideScroll ? 0 : scrollPoint);
            RSInterface child = RSInterface.interfaceCache[rsInterface.children[index]];
            if (child == null)
                continue;
            int offset = child.xOffset;
            xSpritePos += offset;
            ySpritePos += child.yOffset;

            if (super.clickMode3 != 0) {
                xPos = super.clickX;
                yPos = super.clickY;
            }

            if ((child.hoverType >= 0 || child.disabledMouseOverColor != 0) && xPos >= xSpritePos && yPos >= ySpritePos
                    && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) {
                if (child.hoverType >= 0) {
                    anInt886 = child.hoverType;
                } else {
                    anInt886 = child.id;
                }
            }

            if ((child.type == 8 || child.type == 10) && xPos >= xSpritePos && yPos >= ySpritePos
                    && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) {
                anInt1315 = child.id;
                if (child.id != skillTabHoverChild) {
                    skillTabHoverChild = child.id;
                }
            }
            if (child.type == 0) {
                buildInterfaceMenu(xSpritePos, child, xPos, ySpritePos, yPos, child.scrollPosition);

                if (child.sideScroll) {
                    if (child.scrollMax > child.width) {
                        scrollInterfaceBottom(xSpritePos, child.width, xPos, yPos, child, ySpritePos + child.height, true,
                                child.scrollMax);
                    }
                } else {
                    if (child.scrollMax > child.height) {
                        scrollInterface(xSpritePos + child.width, child.height, xPos, yPos, child, ySpritePos, true,
                                child.scrollMax);
                    }
                }
            } else {

                if (child.type == 100 && mousePressed && child.unrevealedSprite != null) {
                    // System.out.println("Called");
                    //   System.out.println("Isnt null!");
                    if (xPos >= xSpritePos && yPos >= ySpritePos
                            && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) { // checks if mouse is inside the sprite

                        int scratchX = mouseX - xSpritePos;
                        int scratchY = mouseY - ySpritePos;

                        scratch(scratchX, scratchY, 20, child.unrevealedSprite);
                    }
                }

                child.selected = false;

                if (child.hoverable && xPos >= xSpritePos && yPos >= ySpritePos
                        && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) {
                    child.selected = true;

                    if (child.atActionType == 0) {
                        String tooltip = child.tooltip;
                        if (tooltip != null) {
                            if (myRights == 4 || myRights == 3) {
                                if (!tooltip.isEmpty()) {
                                    tooltip += " - Id: " + child.id;
                                }
                            }
                            menuActionName[menuActionRow] = tooltip;
                            menuActionID[menuActionRow] = 777;
                            menuActionCmd3[menuActionRow] = child.id;
                            menuActionRow++;
                        }
                    }

                }


                if (child.atActionType == 1 && xPos >= xSpritePos && yPos >= ySpritePos
                        && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) {
                    if (child.parentID >= 113500 && child.parentID < 114000 && child.message.equalsIgnoreCase("")) {

                    } else {
                        if (child.type == 42)
                            childHovered = child;
                        boolean flag = false, flag1 = false;
                        if (child.contentType != 0) {
                            flag = buildFriendsListMenu(child);
                        }

                        if (!flag && !flag1) {
                            if (child.actions != null) {
                                for (int i = child.actions.length - 1; i >= 0; i--) {
                                    String s = child.actions[i];
                                    if (s != null) {
                                        menuActionName[menuActionRow] = s;
                                        menuActionID[menuActionRow] = 222;
                                        menuActionCmd3[menuActionRow] = child.id;
                                        currentActionMenu = menuActionRow;
                                        menuActionRow++;
                                    }
                                }
                            }
                            String tooltip = child.tooltip;
                            if (tooltip != null) {
                                if (myRights == 4) {
                                    tooltip += " - Id: " + child.id;
                                }
                                if (tooltip.contains("[GE")) {
                                    continue;
                                }
                                menuActionName[menuActionRow] = tooltip;
                                menuActionID[menuActionRow] = 315;
                                menuActionCmd3[menuActionRow] = child.id;
                                menuActionRow++;
                            }
                        }
                    }
                }
                if (child.atActionType == 2 && xPos >= xSpritePos && yPos >= ySpritePos
                        && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) {
                    String s = child.selectedActionName;

                    if (s.indexOf(" ") != -1) {
                        s = s.substring(0, s.indexOf(" "));
                    }

                    if (child.spellName.endsWith("Rush") || child.spellName.endsWith("Burst")
                            || child.spellName.endsWith("Blitz") || child.spellName.endsWith("Barrage")
                            || child.spellName.endsWith("strike") || child.spellName.endsWith("bolt")
                            || child.spellName.equals("Crumble undead") || child.spellName.endsWith("blast")
                            || child.spellName.endsWith("wave") || child.spellName.equals("Claws of Guthix")
                            || child.spellName.equals("Flames of Zamorak")
                            || child.spellName.equals("Magic Dart")) {
                        menuActionName[menuActionRow] = "Autocast @gre@" + child.spellName;
                        menuActionID[menuActionRow] = 104;
                        menuActionCmd3[menuActionRow] = child.id;
                        menuActionRow++;
                    }

                    menuActionName[menuActionRow] = s + " @gre@" + child.spellName
                            + (myRights == 4 ? ", " + child.id : "");
                    menuActionID[menuActionRow] = 626;
                    menuActionCmd3[menuActionRow] = child.id;
                    menuActionRow++;
                }

                if (child.atActionType == 3 && xPos >= xSpritePos && yPos >= ySpritePos
                        && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) {
                    menuActionName[menuActionRow] = "Close";
                    menuActionID[menuActionRow] = 200;
                    menuActionCmd3[menuActionRow] = child.id;
                    menuActionRow++;
                }

                if (child.atActionType == 4 && xPos >= xSpritePos && yPos >= ySpritePos
                        && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) {
                    menuActionName[menuActionRow] = child.tooltip + (myRights == 4 ? ", " + child.id : "");
                    menuActionID[menuActionRow] = 169;
                    menuActionCmd3[menuActionRow] = child.id;
                    menuActionRow++;

                    if (child.tooltipBoxText != null) {
                        // drawHoverBox(k, l, class9_1.hoverText);
                    }
                }

                if (child.atActionType == 5 && xPos >= xSpritePos && yPos >= ySpritePos
                        && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) {
                    childHovered = child;
                    menuActionName[menuActionRow] = child.tooltip + (myRights == 4 ? ", " + child.id : "");
                    menuActionID[menuActionRow] = 646;
                    menuActionCmd3[menuActionRow] = child.id;
                    menuActionRow++;
                }

                if (child.atActionType == 6 && !aBoolean1149 && xPos >= xSpritePos && yPos >= ySpritePos
                        && xPos < xSpritePos + child.width && yPos < ySpritePos + child.height) {
                    menuActionName[menuActionRow] = child.tooltip + (myRights == 4 ? ", " + child.id : "");
                    menuActionID[menuActionRow] = 679;
                    menuActionCmd3[menuActionRow] = child.id;
                    menuActionRow++;
                }

                if (child.type == 2 && child.parentID != 0) {
                    int ptr = 0;

                    for (int l2 = 0; l2 < child.height; l2++) {
                        for (int i3 = 0; i3 < child.width; i3++) {
                            int j3 = xSpritePos + i3 * (32 + child.invSpritePadX);
                            int k3 = ySpritePos + l2 * (32 + child.invSpritePadY);

                            if (ptr < child.spritesX.length) {
                                j3 += child.spritesX[ptr];
                                k3 += child.spritesY[ptr];
                            }

                            if (xPos >= j3 && yPos >= k3 && xPos < j3 + 32 && yPos < k3 + 32) {

                                if (!(child.id >= 22035 && child.id <= 22042)) {
                                    mouseInvInterfaceIndex = ptr;
                                    lastActiveInvInterface = child.id;
                                }

                                if (child.inv[ptr] > 0) {
                                    int id = child.inv[ptr] - 1;
                                    if (id > ItemDefinition.totalItems) {
                                        continue;
                                    }
                                    ItemDefinition itemDef = ItemDefinition.get(id);
                                    itemHover = child.inv[ptr] - 1;
                                    if (itemHover != lastItemHover) {
                                        updateItemHoverInterface(itemDef);
                                        lastItemHover = child.inv[ptr] - 1;
                                        hoverShowing = true;
                                    }

                                    if (itemSelected == 1 && child.isInventoryInterface) {
                                        if (child.id != anInt1284 || ptr != anInt1283) {
                                            menuActionName[menuActionRow] = "Use " + selectedItemName + " with @lre@"
                                                    + itemDef.name;
                                            menuActionID[menuActionRow] = 870;
                                            menuActionCmd1[menuActionRow] = itemDef.id;
                                            menuActionCmd2[menuActionRow] = ptr;
                                            menuActionCmd3[menuActionRow] = child.id;
                                            menuActionRow++;
                                        }
                                    } else if (spellSelected == 1 && child.isInventoryInterface) {
                                        if ((spellUsableOn & 0x10) == 16) {
                                            menuActionName[menuActionRow] = spellTooltip + " @lre@" + itemDef.name;
                                            menuActionID[menuActionRow] = 543;
                                            menuActionCmd1[menuActionRow] = itemDef.id;
                                            menuActionCmd2[menuActionRow] = ptr;
                                            menuActionCmd3[menuActionRow] = child.id;
                                            menuActionRow++;
                                        }
                                    } else {/*
                                     * if (children.isInventoryInterface) { if (openInterfaceID != 24700 &&
                                     * openInterfaceID != 2700) { for (int l3 = 4; l3 >= 3; l3--) { if
                                     * (definition.actions != null && definition.actions[l3] != null ||
                                     * lootingBag) { if (lootingBag) { menuActionCmd1[menuActionRow] =
                                     * definition.id; menuActionCmd2[menuActionRow] = k2;
                                     * menuActionCmd3[menuActionRow] = children.id;
                                     *
                                     * if (l3 == 1) { menuActionName[menuActionRow] = "Deposit" + " @lre@" +
                                     * definition.name; } else { menuActionName[menuActionRow] = null; }
                                     *
                                     * if (l3 == 3) { menuActionID[menuActionRow] = 493; }
                                     *
                                     * if (l3 == 4) { menuActionID[menuActionRow] = 847; }
                                     *
                                     * menuActionCmd1[menuActionRow] = definition.id;
                                     * menuActionCmd2[menuActionRow] = k2; menuActionCmd3[menuActionRow] =
                                     * children.id; menuActionRow++; continue; } menuActionName[menuActionRow] =
                                     * definition.actions[l3] + " @lre@" + definition.name;
                                     *
                                     * if (l3 == 3) { menuActionID[menuActionRow] = 493; }
                                     *
                                     * if (l3 == 4) { menuActionID[menuActionRow] = 847; }
                                     *
                                     * menuActionCmd1[menuActionRow] = definition.id;
                                     * menuActionCmd2[menuActionRow] = k2; menuActionCmd3[menuActionRow] =
                                     * children.id; menuActionRow++; } else if (l3 == 4) {
                                     * menuActionName[menuActionRow] = "Drop @lre@" + definition.name;
                                     * menuActionID[menuActionRow] = 847; menuActionCmd1[menuActionRow] =
                                     * definition.id; menuActionCmd2[menuActionRow] = k2;
                                     * menuActionCmd3[menuActionRow] = children.id; menuActionRow++; } } } }
                                     */

                                        if (child.isInventoryInterface && !(shiftIsDown && shiftDrop)) {
                                            if (openInterfaceID != 24700 && openInterfaceID != 2700) {
                                                for (int l3 = 4; l3 >= 3; l3--) {
                                                    if (itemDef.actions != null && itemDef.actions[l3] != null) {
                                                        menuActionName[menuActionRow] = itemDef.actions[l3] + " @lre@"
                                                                + itemDef.name;

                                                        if (l3 == 3) {
                                                            menuActionID[menuActionRow] = 493;
                                                        }

                                                        if (l3 == 4) {
                                                            menuActionID[menuActionRow] = 847;
                                                        }

                                                        menuActionCmd1[menuActionRow] = itemDef.id;
                                                        menuActionCmd2[menuActionRow] = ptr;
                                                        menuActionCmd3[menuActionRow] = child.id;
                                                        menuActionRow++;
                                                    } else if (l3 == 4) {
                                                        menuActionName[menuActionRow] = "Drop @lre@" + itemDef.name;
                                                        menuActionID[menuActionRow] = 847;
                                                        menuActionCmd1[menuActionRow] = itemDef.id;
                                                        menuActionCmd2[menuActionRow] = ptr;
                                                        menuActionCmd3[menuActionRow] = child.id;
                                                        menuActionRow++;
                                                    }
                                                }
                                            }
                                        }

                                        if (child.usableItemInterface) {
                                            if (openInterfaceID == 24700) {
                                                menuActionName[menuActionRow] = "Offer @lre@" + itemDef.name;
                                                menuActionID[menuActionRow] = 24700;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                getGrandExchange().itemSelected = itemDef.id;
                                            } else if (openInterfaceID == 2700) {
                                                menuActionName[menuActionRow] = "Store @lre@" + itemDef.name;
                                                menuActionID[menuActionRow] = 2700;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                            } else {
                                                menuActionName[menuActionRow] = "Use @lre@" + itemDef.name;
                                                menuActionID[menuActionRow] = 447;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                            }
                                            menuActionCmd2[menuActionRow] = ptr;
                                            menuActionCmd3[menuActionRow] = child.id;
                                            menuActionRow++;
                                        }

                                        if (openInterfaceID != 24700 && child.isInventoryInterface
                                                && itemDef.actions != null) {
                                            for (int i4 = 2; i4 >= 0; i4--) {
                                                if (itemDef.actions[i4] != null) {
                                                    menuActionName[menuActionRow] = itemDef.actions[i4] + " @lre@"
                                                            + itemDef.name;

                                                    if (i4 == 0) {
                                                        menuActionID[menuActionRow] = 74;
                                                    }

                                                    if (i4 == 1) {
                                                        menuActionID[menuActionRow] = 454;
                                                    }

                                                    if (i4 == 2) {
                                                        menuActionID[menuActionRow] = 539;
                                                    }

                                                    menuActionCmd1[menuActionRow] = itemDef.id;
                                                    menuActionCmd2[menuActionRow] = ptr;
                                                    menuActionCmd3[menuActionRow] = child.id;
                                                    menuActionRow++;
                                                }
                                            }
                                        }


                                        if (child.isInventoryInterface && shiftIsDown && shiftDrop) {
                                            for (int l3 = 4; l3 >= 3; l3--) {
                                                if (itemDef.actions != null && itemDef.actions[l3] != null) {
                                                    menuActionName[menuActionRow] = itemDef.actions[l3] + " @lre@"
                                                            + itemDef.name;

                                                    if (l3 == 3) {
                                                        menuActionID[menuActionRow] = 493;
                                                    }

                                                    if (l3 == 4) {
                                                        menuActionID[menuActionRow] = 847;
                                                    }

                                                    menuActionCmd1[menuActionRow] = itemDef.id;
                                                    menuActionCmd2[menuActionRow] = ptr;
                                                    menuActionCmd3[menuActionRow] = child.id;
                                                    menuActionRow++;
                                                } else if (l3 == 4) {
                                                    menuActionName[menuActionRow] = "Drop @lre@" + itemDef.name;
                                                    menuActionID[menuActionRow] = 847;
                                                    menuActionCmd1[menuActionRow] = itemDef.id;
                                                    menuActionCmd2[menuActionRow] = ptr;
                                                    menuActionCmd3[menuActionRow] = child.id;
                                                    menuActionRow++;
                                                }
                                            }
                                        }







                                        if (child.actions != null) {
                                            for (int j4 = 6; j4 >= 0; j4--) {
                                                if (j4 > child.actions.length - 1)
                                                    continue;
                                                if (child.actions[j4] != null) {
                                                    /*
                                                     * Bank action is affected here WITHDRAW X STORE X WITHDRAW ALL BUT
                                                     * ONE STORE ALL BUT ONE
                                                     *
                                                     * String s = myRights == 4 ? children.actions[j4] + " @lre@" +
                                                     * definition.name + " " + definition.id : children.actions[j4] +
                                                     * " @lre@" + definition.name; int interfaceId = children.id;
                                                     * if(children.parentID == 3321 && openInterfaceID == 42000) { s =
                                                     * s.replaceAll("Offer", "Pricecheck"); interfaceId = 2100; } else
                                                     * if(children.parentID == 3321 && openInterfaceID == 2700) { s =
                                                     * s.replaceAll("Offer", "Store"); interfaceId = 2700; }
                                                     *
                                                     * menuActionName[menuActionRow] = s; if (j4 == 0) {
                                                     * menuActionID[menuActionRow] = 632; }
                                                     *
                                                     * if (j4 == 1) { menuActionID[menuActionRow] = 78; }
                                                     *
                                                     * if (j4 == 2) { menuActionID[menuActionRow] = 867; }
                                                     *
                                                     * if (j4 == 3) { menuActionID[menuActionRow] = 431; }
                                                     *
                                                     * if (j4 == 4) { menuActionID[menuActionRow] = 53; }
                                                     *
                                                     * menuActionCmd1[menuActionRow] = definition.id;
                                                     * menuActionCmd2[menuActionRow] = k2; menuActionCmd3[menuActionRow]
                                                     * = interfaceId; menuActionRow++; } }
                                                     */
                                                    String s = myRights == 4
                                                            ? child.actions[j4] + " @lre@" + itemDef.name + " ("
                                                            + itemDef.id + ")"
                                                            : child.actions[j4] + " @lre@" + itemDef.name;

                                                    int interfaceId = child.id;
                                                    /**
                                                     * @author Crimson Store the children.id value so it can be
                                                     *         manipulated by the custom interfaces below. If it doesn't
                                                     *         need to be manipulated, will send default with the
                                                     *         packet.
                                                     */
                                                    if (child.parentID == 3321 && openInterfaceID == 31500) {
                                                        s = s.replaceAll("Offer", "Store");
                                                        interfaceId = 2900;
                                                    } else if (child.parentID == 3321 && openInterfaceID == 42000) {
                                                        s = s.replaceAll("Offer", "Pricecheck");
                                                        interfaceId = 2100;
                                                    } else if (child.parentID == 3321 && openInterfaceID == 2700) {
                                                        s = s.replaceAll("Offer", "Store");
                                                        interfaceId = 2700;
                                                    }

                                                    if (child.parentID == 994) {
                                                        child.actions = new String[]{"Make set", "Make 5 sets",
                                                                "Make 10 sets", "Make All sets", "Make X sets"};
                                                    }

                                                    if (child.parentID == 5292) {
                                                        if (modifiableXValue > 0) {
                                                            child.actions = new String[]{"Withdraw 1",
                                                                    "Withdraw 5", "Withdraw 10", "Withdraw All",
                                                                    "Withdraw All but one", "Withdraw X",
                                                                    "Withdraw " + formatAmount(modifiableXValue)};
                                                        } else {
                                                            child.actions = new String[]{"Withdraw 1",
                                                                    "Withdraw 5", "Withdraw 10", "Withdraw All",
                                                                    "Withdraw All but one", "Withdraw X"};
                                                        }
                                                    }
                                                    if (child.parentID == 5063) {
                                                        if (modifiableXValue > 0) {
                                                            child.actions = new String[]{"Store 1", "Store 5",
                                                                    "Store 10", "Store All", "Store All but one",
                                                                    "Store X",
                                                                    "Store " + formatAmount(modifiableXValue)};
                                                        } else {
                                                            child.actions = new String[]{"Store 1", "Store 5",
                                                                    "Store 10", "Store All", "Store All but one",
                                                                    "Store X"};
                                                        }
                                                    }

                                                    menuActionName[menuActionRow] = s;
                                                    if (j4 == 0)
                                                        menuActionID[menuActionRow] = 632;
                                                    if (j4 == 1)
                                                        menuActionID[menuActionRow] = 78;
                                                    if (j4 == 2)
                                                        menuActionID[menuActionRow] = 867;
                                                    if (j4 == 3)
                                                        menuActionID[menuActionRow] = 431;
                                                    if (j4 == 4)
                                                        menuActionID[menuActionRow] = 53;
                                                    if (j4 == 5)
                                                        menuActionID[menuActionRow] = 54;
                                                    if (child.parentID == 5292 || child.parentID == 5063) {
                                                        if (child.actions.length > 5) {
                                                            if (j4 == 6) {
                                                                menuActionID[menuActionRow] = 300;
                                                            }
                                                        }
                                                    }

                                                    menuActionCmd1[menuActionRow] = itemDef.id;
                                                    menuActionCmd2[menuActionRow] = ptr;
                                                    menuActionCmd3[menuActionRow] = interfaceId;
                                                    menuActionRow++;

                                                }
                                            }
                                        }


                                        if (!child.isInventoryInterface && shiftIsDown && shiftDrop) {
                                            if (openInterfaceID == 5292 && child.parentID == 5292) {
                                                menuActionName[menuActionRow] = "Withdraw All @lre@" + itemDef.name;
                                                menuActionID[menuActionRow] = 431;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                            }
                                        }
                                        if (!child.isInventoryInterface && shiftIsDown && shiftDrop) {
                                            if (openInterfaceID == 5292 && child.parentID == 5063) {
                                                menuActionName[menuActionRow] = "Store All @lre@" + itemDef.name;
                                                menuActionID[menuActionRow] = 431;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                            }
                                        }

                                        if (openInterfaceID != 24700) {
                                            if (!child.hideExamine && child.displayExamine) {
                                                menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name
                                                        + (myRights == 4
                                                        ? " (" + itemDef.id
                                                        + ")"
                                                        : "");
                                                menuActionID[menuActionRow] = 1125;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                            } else {
                                                if (child.parentID == 3822 && openInterfaceID == 3824) {
                                                    menuActionName[menuActionRow] = "Sell All @lre@" + itemDef.name;
                                                    menuActionID[menuActionRow] = 1126;
                                                    menuActionCmd1[menuActionRow] = itemDef.id;
                                                    menuActionCmd2[menuActionRow] = ptr;
                                                    menuActionCmd3[menuActionRow] = child.id;
                                                    menuActionRow++;
                                                }
                                            }
                                            if (child.id == 39672 && MBOX) {
                                                menuActionName[menuActionRow] = "Claim @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 431;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                            }
                                            if (child.id == 39673 && MBOX) {
                                                menuActionName[menuActionRow] = "Claim @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 431;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                            }
                                            if (child.id == 39674 && MBOX) {
                                                menuActionName[menuActionRow] = "Claim @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 431;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                            }
                                            if (child.id == 32621) {
                                                menuActionName[menuActionRow] = "Buy X @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 431;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Buy All @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 53;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Buy 10 @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 300;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Buy 5 @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 291;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Buy 1 @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 867;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Value @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 632;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                            }

                                            if (child.id == 33621) {
                                                menuActionName[menuActionRow] = "Withdraw X @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 431;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Withdraw All @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 78;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Withdraw 10 @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 300;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Withdraw 5 @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 291;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Withdraw 1 @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 867;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                                menuActionName[menuActionRow] = "Value @lre@" + itemDef.name + "";
                                                menuActionID[menuActionRow] = 632;
                                                menuActionCmd1[menuActionRow] = itemDef.id;
                                                menuActionCmd2[menuActionRow] = ptr;
                                                menuActionCmd3[menuActionRow] = child.id;
                                                menuActionRow++;
                                            }
                                        }

                                        //


                                        List<Integer> dontHover = new ArrayList<Integer>() {
                                            {
                                                add(1);
                                                add(2);
                                            }
                                        };
                                        itemHover = itemDef.id;


                                        if (itemHover > -1 && !dontHover.contains(openInterfaceID)) {
                                            int y = super.mouseY;
                                            int x = super.mouseX;
                                            int hoverWidth = RSInterface.interfaceCache[94021].width;
                                            int hoverWidth1 = RSInterface.interfaceCache[94086].width;

                                            if (ItemStats.itemstats[itemHover] != null) {
                                                if (ItemStats.itemstats[itemHover].type == 1) {
                                                    if (x + hoverWidth > 500) {
                                                        x = 500 - hoverWidth;
                                                    }
                                                    if (GameFrame.getScreenMode() == ScreenMode.FIXED && y > 180) {
                                                        y = 180;
                                                    }
                                                }
                                            }
                                            //if (clientSize == 1) {
                                            if (controlIsDown && ItemStats.itemstats[itemHover] != null && ItemStats.itemstats[itemHover].type == 1) {

                                                drawInterface(0, x, RSInterface.interfaceCache[94000], y);
                                            } else {
                                                drawInterface(0, x, RSInterface.interfaceCache[94020], y);
                                            }
                                        }


                                        if ((openInterfaceID == 5292 || openInterfaceID == 106000) && child.invStackSizes[ptr] == 0) {
                                            menuActionRow = 3;
                                            menuActionName[0] = "Cancel";
                                            menuActionID[0] = 1107;
                                            menuActionCmd1[0] = itemDef.id;
                                            menuActionCmd2[0] = ptr;
                                            menuActionCmd3[0] = child.id;

                                            if (child.displayExamine) {
                                                menuActionName[1] = "Examine @lre@" + itemDef.name;
                                                menuActionID[1] = 1125;
                                                menuActionCmd1[1] = itemDef.id;
                                                menuActionCmd2[1] = ptr;
                                                menuActionCmd3[1] = child.id;
                                            }

                                            menuActionName[2] = "Release @lre@" + itemDef.name;
                                            menuActionID[2] = 633;
                                            menuActionCmd1[2] = itemDef.id;
                                            menuActionCmd2[2] = ptr;
                                            menuActionCmd3[2] = child.id;
                                        }

                                    }
                                }
                            }

                            ptr++;
                        }
                    }
                }
            }
        }
    }

    private void buildPublicChat(int yOffset) {
        int messages = 0;

        for (int index = 0; index < 500; index++) {
            if (chatMessages[index] == null) {
                continue;
            }

            if (chatTypeView != 1) {
                continue;
            }

            int type = chatTypes[index];
            String name = chatNames[index];
            int k1 = 70 - messages * 14 + 42 + anInt1089 + 4 + 5;

            if (k1 < -23) {
                break;
            }

            if (name != null) {
                if (name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    name = name.substring(prefixSubstring);
                }
                if (name.indexOf("@") == 0) {
                    int prefixSubstring = getPrefixSubstringLength(name);
                    name = name.substring(prefixSubstring);
                }
            }

            if ((type == 1 || type == 2)
                    && (type == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(name))) {
                if (yOffset > k1 - 14 && yOffset <= k1 && !name.equals(myPlayer.name)) {
                    if (!isFriendOrSelf(name)) {
                        menuActionName[menuActionRow] = "Add ignore @whi@" + name;
                        menuActionID[menuActionRow] = 42;
                        menuActionRow++;
                        menuActionName[menuActionRow] = "Add friend @whi@" + name;
                        menuActionID[menuActionRow] = 337;
                        menuActionRow++;
                    } else if (isFriendOrSelf(name)) {
                        menuActionName[menuActionRow] = "Message @whi@" + name;
                        menuActionID[menuActionRow] = 2639;
                        menuActionRow++;
                    }
                }

                messages++;
            }
        }
    }

    private void buildSplitPrivateChatMenu() {
        if (splitPrivateChat == 0) {
            return;
        }

        int yOffsetPos = 0;

        if (systemUpdateTimer != 0) {
            yOffsetPos = 1;
        }
        if (broadcastMinutes != 0) {
            yOffsetPos += 1;
        }

        for (int index = 0; index < 500; index++) {
            if (chatMessages[index] != null) {
                int type = chatTypes[index];
                String name = chatNames[index];
                boolean fixed = GameFrame.getScreenMode() == ScreenMode.FIXED;

                if (name != null && name.indexOf("@") == 0) {
                    name = NAME_PATTERN.matcher(name).replaceAll("");
                }

                if ((type == 3 || type == 7)
                        && (type == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(name))) {
                    int l = chatArea.getyPos() - 9 - yOffsetPos * 13;

                    if (super.mouseX > (fixed ? 4 : 0) && super.mouseY - (fixed ? 4 : 0) > l - 10
                            && super.mouseY - (fixed ? 4 : 0) <= l + 3) {
                        int i1 = normalText.getTextWidth("From:  " + name + chatMessages[index]) + 25;

                        if (i1 > 450) {
                            i1 = 450;
                        }

                        if (super.mouseX < (fixed ? 4 : 0) + i1) {
                            if (!isFriendOrSelf(name)) {
                                menuActionName[menuActionRow] = "Add ignore @whi@" + name;
                                menuActionID[menuActionRow] = 2042;
                                menuActionRow++;
                                menuActionName[menuActionRow] = "Add friend @whi@" + name;
                                menuActionID[menuActionRow] = 2337;
                                menuActionRow++;
                            } else if (isFriendOrSelf(name)) {
                                menuActionName[menuActionRow] = "Message @whi@" + name;
                                menuActionID[menuActionRow] = 2639;
                                menuActionRow++;
                            }
                        }
                    }

                    if (++yOffsetPos >= 5) {
                        return;
                    }
                }

                if ((type == 5 || type == 6) && privateChatMode < 2 && ++yOffsetPos >= 5) {
                    return;
                }
            }
        }
    }

    private void calcCameraPos() {
        int xPos = spinPacketX * 128 + 64;
        int yPos = spinPacketY * 128 + 64;
        int zPos = method42(plane, yPos, xPos) - spinPacketHeight;

        if (xCameraPos < xPos) {
            xCameraPos += spinPacketConstantSpeed + (xPos - xCameraPos) * spinPacketVariableSpeed / 1000;

            if (xCameraPos > xPos) {
                xCameraPos = xPos;
            }
        }

        if (xCameraPos > xPos) {
            xCameraPos -= spinPacketConstantSpeed + (xCameraPos - xPos) * spinPacketVariableSpeed / 1000;

            if (xCameraPos < xPos) {
                xCameraPos = xPos;
            }
        }

        if (zCameraPos < zPos) {
            zCameraPos += spinPacketConstantSpeed + (zPos - zCameraPos) * spinPacketVariableSpeed / 1000;

            if (zCameraPos > zPos) {
                zCameraPos = zPos;
            }
        }

        if (zCameraPos > zPos) {
            zCameraPos -= spinPacketConstantSpeed + (zCameraPos - zPos) * spinPacketVariableSpeed / 1000;

            if (zCameraPos < zPos) {
                zCameraPos = zPos;
            }
        }

        if (yCameraPos < yPos) {
            yCameraPos += spinPacketConstantSpeed + (yPos - yCameraPos) * spinPacketVariableSpeed / 1000;

            if (yCameraPos > yPos) {
                yCameraPos = yPos;
            }
        }

        if (yCameraPos > yPos) {
            yCameraPos -= spinPacketConstantSpeed + (yCameraPos - yPos) * spinPacketVariableSpeed / 1000;

            if (yCameraPos < yPos) {
                yCameraPos = yPos;
            }
        }

        xPos = moveCameraX * 128 + 64;
        yPos = moveCameraY * 128 + 64;
        zPos = method42(plane, yPos, xPos) - moveCameraZ;
        int l = xPos - xCameraPos;
        int i1 = zPos - zCameraPos;
        int j1 = yPos - yCameraPos;
        int k1 = (int) Math.sqrt(l * l + j1 * j1);
        int l1 = (int) (Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
        int i2 = (int) (Math.atan2(l, j1) * -325.94900000000001D) & 0x7ff;

        if (l1 < 128) {
            l1 = 128;
        }

        if (l1 > 383) {
            l1 = 383;
        }

        if (yCameraCurve < l1) {
            yCameraCurve += moveCameraSpeed + (l1 - yCameraCurve) * moveCameraAngle / 1000;

            if (yCameraCurve > l1) {
                yCameraCurve = l1;
            }
        }

        if (yCameraCurve > l1) {
            yCameraCurve -= moveCameraSpeed + (yCameraCurve - l1) * moveCameraAngle / 1000;

            if (yCameraCurve < l1) {
                yCameraCurve = l1;
            }
        }

        int j2 = i2 - xCameraCurve;

        if (j2 > 1024) {
            j2 -= 2048;
        }

        if (j2 < -1024) {
            j2 += 2048;
        }

        if (j2 > 0) {
            xCameraCurve += moveCameraSpeed + j2 * moveCameraAngle / 1000;
            xCameraCurve &= 0x7ff;
        }

        if (j2 < 0) {
            xCameraCurve -= moveCameraSpeed + -j2 * moveCameraAngle / 1000;
            xCameraCurve &= 0x7ff;
        }

        int k2 = i2 - xCameraCurve;

        if (k2 > 1024) {
            k2 -= 2048;
        }

        if (k2 < -1024) {
            k2 += 2048;
        }

        if (k2 < 0 && j2 > 0 || k2 > 0 && j2 < 0) {
            xCameraCurve = i2;
        }
    }

    private void calcEntityScreenPos(int i, int j, int l) {
        if (i < 128 || l < 128 || i > 13056 || l > 13056) {
            spriteDrawX = -1;
            spriteDrawY = -1;
            return;
        }

        int i1 = method42(plane, l, i) - j;
        i -= xCameraPos;
        i1 -= zCameraPos;
        l -= yCameraPos;
        int j1 = Model.SINE[yCameraCurve];
        int k1 = Model.COSINE[yCameraCurve];
        int l1 = Model.SINE[xCameraCurve];
        int i2 = Model.COSINE[xCameraCurve];
        int j2 = l * l1 + i * i2 >> 16;
        l = l * i2 - i * l1 >> 16;
        i = j2;
        j2 = i1 * k1 - l * j1 >> 16;
        l = i1 * j1 + l * k1 >> 16;
        i1 = j2;

        if (l >= 50) {
            spriteDrawX = Rasterizer.centerX + (i << log_view_dist) / l;
            spriteDrawY = Rasterizer.centerY + (i1 << log_view_dist) / l;
        } else {
            spriteDrawX = -1;
            spriteDrawY = -1;
        }
    }

    private void chatJoin(long l) {
        try {
            if (l == 0L) {
                return;
            }

            getOut().putOpcode(60);
            getOut().putLong(l);
            return;
        } catch (RuntimeException runtimeexception) {
            Signlink.reportError("47229, " + 3 + ", " + l + ", " + runtimeexception.toString());
        }

        throw new RuntimeException();
    }

    public int ticksSinceLastResize = 0;

    public boolean isInResizeable() {
        return GameFrame.getScreenMode() == ScreenMode.RESIZABLE;//Configuration.clientSize == 1;
    }

    private void checkSize() {
        if (isInResizeable()) {
            if (Configuration.clientWidth != (isWebclient() ? getGameComponent().getWidth() : gameFrame.getFrameWidth())) {
                Configuration.clientWidth = (isWebclient() ? getGameComponent().getWidth() : gameFrame.getFrameWidth());
                gameAreaWidth = Configuration.clientWidth;
                ticksSinceLastResize = 0;
            }
            if (Configuration.clientHeight != (isWebclient() ? getGameComponent().getHeight() : gameFrame.getFrameHeight())) {
                Configuration.clientHeight = (isWebclient() ? getGameComponent().getHeight() : gameFrame.getFrameHeight());
                gameAreaHeight = Configuration.clientHeight;
                ticksSinceLastResize = 0;
            }
        }

        // Check on Resize for mac computers
        if (!Configuration.IS_RUNNING_WINDOWS) {
            if (ticksSinceLastResize == 50) {
                if (isInResizeable()) {
                    rebuildFrame(1, Configuration.clientWidth, Configuration.clientHeight);
                }
            }
        } else { // Check on Windows Computers
            if (ticksSinceLastResize == 10) {
                updateGameArea();
                //updateGame();
            }
        }

        ticksSinceLastResize++;
    }

    public void rebuildFrame(int size, int width, int height) {
        try {
            gameAreaWidth = (size == 0) ? 512 : width;
            gameAreaHeight = (size == 0) ? 334 : height;
            Configuration.clientWidth = width;
            Configuration.clientHeight = height;
            instance.rebuildFrame(size == 2, width, height, size == 1, size >= 1);
            updateGameArea();//updateGame();
            super.mouseX = super.mouseY = -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanUpForQuit() {
        Signlink.reporterror = false;
        mapIcon = null;
        try {
            if (getConnection() != null) {
                getConnection().close();
            }
        } catch (Exception _ex) {
        }

        cacheSprite = null;
        setConnection(null);
        stopMidi();

        if (mouseDetection != null) {
            mouseDetection.running = false;
        }

        mouseDetection = null;

        if (onDemandFetcher != null) {
            onDemandFetcher.dispose();
        }

        onDemandFetcher = null;
        aStream_834 = null;
        setOut(null);
        setLoginBuffer(null);
        setInputBuffer(null);
        mapCoordinates = null;
        terrainData = null;
        objectData = null;
        floorMap = null;
        objectMap = null;
        tileHeights = null;
        tileFlags = null;
        scene = null;
        collisionData = null;
        anIntArrayArray901 = null;
        anIntArrayArray825 = null;
        bigX = null;
        bigY = null;
        aByteArray912 = null;
        tabAreaIP = null;
        leftFrame = null;
        topFrame = null;
        rightFrame = null;
        mapAreaIP = null;
        gameScreenIP = null;
        chatAreaIP = null;
        aRSImageProducer_1125 = null;
        mapBack = null;
        compass = null;
        headIcons = null;
        skullIcons = null;
        headIconsHint = null;
        crosses = null;
        mapDotItem = null;
        mapDotNPC = null;
        mapDotPlayer = null;
        mapDotFriend = null;
        mapDotTeam = null;
        mapDotClan = null;
        alertBack = null;
        alertBorder = null;
        alertBorderH = null;
        mapScenes = null;
        mapFunctions = null;
        anIntArrayArray929 = null;
        playerArray = null;
        playerIndices = null;
        playersToUpdate = null;
        setaStreamArray895s(null);
        anIntArray840 = null;
        npcArray = null;
        npcIndices = null;
        groundArray = null;
        setaClass19_1179(null);
        setaClass19_1013(null);
        setaClass19_1056(null);
        menuActionCmd2 = null;
        menuActionCmd3 = null;
        menuActionCmd4 = null;
        menuActionID = null;
        menuActionCmd1 = null;
        menuActionName = null;
        variousSettings = null;
        anIntArray1072 = null;
        anIntArray1073 = null;
        aClass30_Sub2_Sub1_Sub1Array1140 = null;
        miniMapRegions = null;
        friendsList = null;
        friendsListAsLongs = null;
        friendsNodeIDs = null;
        aRSImageProducer_1107 = null;
        titleScreenIP = null;
        multiOverlay = null;
        XPOverlay = null;
        nullLoader();
        InterfaceNPC.reset();
        ObjectDefinition.nullify();
        MobDefinition.nullify();
        ItemDefinition.nullify();
        FrameReader.nullify();
        Flo.cache = null;
        IdentityKit.cache = null;
        RSInterface.interfaceCache = null;
        Animation.cache = null;
        SpotAnimDefinition.cache = null;
        SpotAnimDefinition.list = null;
        Varp.setCache(null);
        super.fullGameScreen = null;
        Player.mruNodes = null;
        Rasterizer.nullify();
        WorldController.nullify();
        Model.nullify();
        Texture.reset();
        System.gc();
    }

    public void closeGameInterfaces() {
        getOut().putOpcode(130);

        if (invOverlayInterfaceID != -1) {
            invOverlayInterfaceID = -1;
            aBoolean1149 = false;
            tabAreaAltered = true;
        }

        if (backDialogID != -1) {
            backDialogID = -1;
            inputTaken = true;
            aBoolean1149 = false;
        }

        openInterfaceID = -1;
        setFullscreenInterfaceID(-1);
    }

    private void compareCrcValues() {
        int secondsToWait = 5;
        expectedCRCs[8] = 0;
        int checksumCount = 0;

        while (expectedCRCs[8] == 0) {
            String error = "Unknown problem";
            //// drawSmoothLoading(20, "Connecting to web server");

            try {
                DataInputStream in = openJagGrabInputStream("crc" + (int) (Math.random() * 99999999D) + "-" + 317);
                ByteBuffer buffer = new ByteBuffer(new byte[40]);
                in.readFully(buffer.buffer, 0, 40);
                in.close();

                for (int index = 0; index < 9; index++) {
                    expectedCRCs[index] = buffer.getIntLittleEndian();
                }

                int checksumValue = buffer.getIntLittleEndian();
                int expectedValue = 1234;

                for (int index = 0; index < 9; index++) {
                    expectedValue = (expectedValue << 1) + expectedCRCs[index];
                }

                if (checksumValue != expectedValue) {
                    error = "checksum problem";
                    expectedCRCs[8] = 0;
                }
            } catch (EOFException _ex) {
                error = "EOF problem";
                expectedCRCs[8] = 0;
            } catch (IOException _ex) {
                error = "connection problem";
                expectedCRCs[8] = 0;
            } catch (Exception _ex) {
                error = "logic problem";
                expectedCRCs[8] = 0;

                if (!Signlink.reporterror) {
                    return;
                }
            }

            if (expectedCRCs[8] == 0) {
                checksumCount++;

                for (int seconds = secondsToWait; seconds > 0; seconds--) {
                    if (checksumCount >= 10) {
                        // drawSmoothLoading(10, "Game updated - please reload page");
                        seconds = 10;
                    } else {
                        // drawSmoothLoading(10, error + " - Will retry in " + seconds + " secs.");
                    }

                    try {
                        Thread.sleep(1000L);
                    } catch (Exception _ex) {
                    }
                }

                secondsToWait *= 2;

                if (secondsToWait > 60) {
                    secondsToWait = 60;
                }

                httpFallback = !httpFallback;
            }
        }
    }

    public Socket createFileServerSocket(int port) throws IOException {
        return new Socket(InetAddress.getByName(Configuration.JAGCACHED_HOST), port);
    }

    public Socket createGameServerSocket(int port) throws IOException {
        return new Socket(InetAddress.getByName(Configuration.SERVER_HOST()), port);
    }

    public void hitmarkDrawOld(int spriteDrawX, int spriteDrawY, int j1, Entity e) {
        if (spriteDrawX > -1) {
            if (j1 == 1)
                spriteDrawY -= 20;
            if (j1 == 2) {
                spriteDrawX -= 15;
                spriteDrawY -= 10;
            }
            if (j1 == 3) {
                spriteDrawX += 15;
                spriteDrawY -= 10;
            }
            int dmg = e.hitArray[j1];
            if (dmg > 0) {
                if (!Configuration.CONSTITUTION_ENABLED) {
                    dmg = dmg / 10;
                    if (dmg == 0)
                        dmg = 1;
                }
                cacheSprite[784].drawSprite(spriteDrawX - 11, spriteDrawY - 12);
            } else {
                cacheSprite[785].drawSprite(spriteDrawX - 12, spriteDrawY - 13);
            }
            smallText.drawText(0, String.valueOf(dmg), spriteDrawY + 4, spriteDrawX);
            smallText.drawText(0xffffff, String.valueOf(dmg), spriteDrawY + 3, spriteDrawX - 1);
        }
    }

    public void hitmarkDrawNew(Entity e, int hitLength, int type, int icon, int damage, int soak, int move, int opacity,
                               int mask) {
        if (spriteDrawX > -1) {
            int drawPos = 0;
            if (mask == 0) {
                e.hitMarkPos[0] = spriteDrawY + move;
                drawPos = e.hitMarkPos[0];
            }
            if (mask != 0) {
                e.hitMarkPos[mask] = e.hitMarkPos[0] + (19 * mask);
                drawPos = e.hitMarkPos[mask];
            }
            if (soak > 0) {
                // if(!getOption("absorb_damage")) {
                // soak = 0;
                // }
                soak = 0;
            }
            if (damage > 0) {
                Sprite end1 = null, middle = null, end2 = null;
                int x = 0;
                if (!Configuration.CONSTITUTION_ENABLED) {
                    damage = (damage / 10);
                    if (damage == 0) {
                        damage = 1;
                    }
                }
                switch (hitLength) {
                    /* Trial and error shit, terrible hardcoding :( */
                    case 1:
                        x = 8;
                        break;
                    case 2:
                        x = 4;
                        break;
                    case 3:
                        x = 1;
                        break;
                }
                if (soak > 0) {
                    x -= 16;
                }
                end1 = cacheSprite[792 + (type * 3)];
                middle = cacheSprite[792 + (type * 3) + 1];
                end2 = cacheSprite[792 + (type * 3) + 2];
                if (icon != 255 && icon != 8) {
                    cacheSprite[786 + icon].drawSprite3(spriteDrawX - 31 + x, drawPos - 9, opacity);
                }
                end1.drawSprite3(spriteDrawX - 12 + x, drawPos - 12, opacity);
                x += 4;
                for (int i = 0; i < hitLength * 2; i++) {
                    middle.drawSprite3(spriteDrawX - 12 + x, drawPos - 12, opacity);
                    x += 4;
                }
                end2.drawSprite3(spriteDrawX - 12 + x, drawPos - 12, opacity);

            /*    (type == 1 ? bigHit : smallHit).drawOpacityText(0xffffff, String.valueOf(damage),
                        drawPos + (type == 1 ? 2 : 32), spriteDrawX + 4 + (soak > 0 ? -16 : 0), opacity);*/

                if (hitLength == 1)
                    x += 10;
                else if (hitLength == 2)
                    x += 6;
                else if (hitLength == 3)
                    x += 2;

                (type == 1 ? bigHit : smallHit).drawCenteredText(0xffffff,
                        spriteDrawX - 12 + (x / 2) +
                                (soak > 0 ? -16 : 0)

                        , String.valueOf(damage),
                        drawPos + (type == 1 ? 2 : 32), true);

                if (soak > 0)
                    drawSoak(soak, opacity, drawPos, x);
            } else {
                int decrX = soak > 0 ? 26 : 12;
                cacheSprite[543].drawSprite3(spriteDrawX - decrX, drawPos - 14, opacity);
                if (soak > 0) {
                    drawSoak(soak, opacity, drawPos, 4);
                }
            }
        }
    }

    public void drawSoak(int damage, int opacity, int drawPos, int x) {
        x -= 12;
        int soakLength = String.valueOf(damage).length();
        cacheSprite[793 + 5].drawSprite3(spriteDrawX + x, drawPos - 12, opacity);
        x += 20;
        cacheSprite[792 + 30].drawSprite3(spriteDrawX + x, drawPos - 12, opacity);
        x += 4;
        for (int i = 0; i < soakLength * 2; i++) {
            cacheSprite[792 + 31].drawSprite3(spriteDrawX + x, drawPos - 12, opacity);
            x += 4;
        }
        cacheSprite[792 + 32].drawSprite3(spriteDrawX + x, drawPos - 10, opacity);
        if (damage > 99) {
            x -= 5;
        }
        if (damage > 999) {
            x -= 5;
        }
        smallHit.drawOpacityText(0xffffff, String.valueOf(damage), drawPos + 32,
                spriteDrawX - 8 + x + (soakLength == 1 ? 5 : 0), opacity);
    }

    private void delFriend(long nameHash) {
        try {
            if (nameHash == 0L) {
                return;
            }

            for (int i = 0; i < friendCount; i++) {
                if (friendsListAsLongs[i] != nameHash) {
                    continue;
                }

                friendCount--;

                for (int n = i; n < friendCount; n++) {
                    friendsList[n] = friendsList[n + 1];
                    friendsNodeIDs[n] = friendsNodeIDs[n + 1];
                    friendsListAsLongs[n] = friendsListAsLongs[n + 1];
                }

                getOut().putOpcode(215);
                getOut().putLong(nameHash);
                break;
            }
        } catch (RuntimeException runtimeexception) {
            Signlink.reportError("18622, " + false + ", " + nameHash + ", " + runtimeexception.toString());
            throw new RuntimeException();
        }
    }

    private void delIgnore(long nameHash) {
        try {
            if (nameHash == 0L) {
                return;
            }

            for (int i = 0; i < ignoreCount; i++) {
                if (ignoreListAsLongs[i] == nameHash) {
                    ignoreCount--;
                    System.arraycopy(ignoreListAsLongs, i + 1, ignoreListAsLongs, i, ignoreCount - i);
                    getOut().putOpcode(74);
                    getOut().putLong(nameHash);
                    return;
                }
            }

            return;
        } catch (RuntimeException runtimeexception) {
            Signlink.reportError("47229, " + 3 + ", " + nameHash + ", " + runtimeexception.toString());
        }

        throw new RuntimeException();
    }



    public static String removeColors(String text) {
        //System.out.println(text);
        text = replace(text, "@red@", "");
        text = replace(text, "@gre@", "");
        text = replace(text, "@blu@", "");
        text = replace(text, "@yel@", "");
        text = replace(text, "@cya@", "");
        text = replace(text, "@mag@", "");
        text = replace(text, "@whi@", "");
        text = replace(text, "@lre@", "");
        text = replace(text, "@dre@", "");
        text = replace(text, "@bla@", "");
        text = replace(text, "@or1@", "");
        text = replace(text, "@or2@", "");
        text = replace(text, "@pr2@", "");
        text = replace(text, "@or3@", "");
        text = replace(text, "@gr1@", "");
        text = replace(text, "@gr2@", "");
        text = replace(text, "@gr3@", "");
        text = replace(text, "@cr1@", "");
        text = replace(text, "@cr2@", "");
        text = replace(text, "@cr3@", "");
        text = replace(text, "@dev@", "");
        text = replace(text, "@des@", "");
        text = replace(text, "@vet@", "");
        text = replace(text, "@don@", "");
        text = replace(text, "@or2@", "");
        text = replace(text, "@purp@", "");

        text = replace(text, "@vea@", "");
        text = replace(text, "@eas@", "");
        text = replace(text, "@med@", "");
        text = replace(text, "@har@", "");
        text = replace(text, "@vha@", "");
        text = replace(text, "@bl2@", "");
        text = replace(text, "@gry@", "");
        text = replace(text, "@pnk@", "");
        text = replace(text, "@pr3@", "");
        text = replace(text, "@skb@", "");
        if (text != null) {
            while (text.contains("<")) {
                if (text.contains("<")) {
                    int index = text.indexOf("<");
                    int index1 = text.indexOf(">");
                    text = text.substring(0, index) + text.substring(index1 + 1);
                }
            }
        }

        return text;
    }
    private static String replace(String s, String from, String to) {
        if (from == "=D" && s.contains("col"))
            return s;
        return replaceAllString(s, from, to);

    }
    public static String replaceAllString(String strOrig, String strFind, String strReplace) {
        if (strOrig == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer(strOrig);
        String toReplace = "";

        if (strReplace == null)
            toReplace = "";
        else
            toReplace = strReplace;

        int pos = strOrig.length();

        while (pos > -1) {
            pos = strOrig.lastIndexOf(strFind, pos);
            if (pos > -1)
                sb.replace(pos, pos + strFind.length(), toReplace);
            pos = pos - strFind.length();
        }

        return sb.toString();
    }

    private void determineMenuSize() {
        int width = newBoldFont.getTextWidth("Choose Option");
        for (int index = 0; index < menuActionRow; index++) {
            int menuWidth = newBoldFont.getTextWidth(removeColors(menuActionName[index]));
            if (menuActionName[index].contains("<ico=")) {
                menuWidth += 23;
            }
            if (menuPlayerName[index] != null) {
                menuWidth += newBoldFont.getTextWidth(menuPlayerName[index]);
            }
            if (menuActionTitle[index] != null) {
                menuWidth += newBoldFont.getTextWidth(menuActionTitle[index]);
            }
            if (menuWidth > width) {
                width = menuWidth;
            }
        }

        width += 8;
        int menHeight = 15 * menuActionRow + 21;
        if (GameFrame.getScreenMode() == ScreenMode.FIXED) {
            if (super.saveClickX > 4 && super.saveClickY > 4 && super.saveClickX < 516 && super.saveClickY < 338) {
                int offsetX = super.saveClickX - 4 - width / 2;
                if (offsetX + width > 512) {
                    offsetX = 512 - width;
                }
                if (offsetX < 0) {
                    offsetX = 0;
                }
                int offsetY = super.saveClickY - 4;
                if (offsetY + menHeight > 334) {
                    offsetY = 334 - menHeight;
                }
                if (offsetY < 0) {
                    offsetY = 0;
                }
                menuOpen = true;
                menuScreenArea = 0;
                menuOffsetX = offsetX;
                menuOffsetY = offsetY;
                menuWidth = width;
                menuHeight = 15 * menuActionRow + 22;
            }
            if (super.saveClickX > 519 && super.saveClickY > 168 && super.saveClickX < 765 && super.saveClickY < 503) {
                int offsetX = super.saveClickX - 519 - width / 2;
                if (offsetX < 0) {
                    offsetX = 0;
                } else if (offsetX + width > 245) {
                    offsetX = 245 - width;
                }
                int offsetY = super.saveClickY - 168;
                if (offsetY < 0) {
                    offsetY = 0;
                } else if (offsetY + menHeight > 333) {
                    offsetY = 333 - menHeight;
                }
                menuOpen = true;
                menuScreenArea = 1;
                menuOffsetX = offsetX;
                menuOffsetY = offsetY;
                menuWidth = width;
                menuHeight = 15 * menuActionRow + 22;
            }
            if (super.saveClickX > 0 && super.saveClickY > 338 && super.saveClickX < 516 && super.saveClickY < 503) {
                int offsetX = super.saveClickX - 0 - width / 2;
                if (offsetX < 0) {
                    offsetX = 0;
                } else if (offsetX + width > 516) {
                    offsetX = 516 - width;
                }
                int offsetY = super.saveClickY - 338;
                if (offsetY < 0) {
                    offsetY = 0;
                } else if (offsetY + menHeight > 165) {
                    offsetY = 165 - menHeight;
                }
                menuOpen = true;
                menuScreenArea = 2;
                menuOffsetX = offsetX;
                menuOffsetY = offsetY;
                menuWidth = width;
                menuHeight = 15 * menuActionRow + 22;
            }
            // if(super.saveClickX > 0 && super.saveClickY > 338 &&
            // super.saveClickX < 516 && super.saveClickY < 503) {
            if (super.saveClickX > 519 && super.saveClickY > 0 && super.saveClickX < 765 && super.saveClickY < 168) {
                int offsetX = super.saveClickX - 519 - width / 2;
                if (offsetX < 0) {
                    offsetX = 0;
                } else if (offsetX + width > 245) {
                    offsetX = 245 - width;
                }
                int offsetY = super.saveClickY - 0;
                if (offsetY < 0) {
                    offsetY = 0;
                } else if (offsetY + menHeight > 168) {
                    offsetY = 168 - menHeight;
                }
                menuOpen = true;
                menuScreenArea = 3;
                menuOffsetX = offsetX;
                menuOffsetY = offsetY;
                menuWidth = width;
                menuHeight = 15 * menuActionRow + 22;
            }
        } else {
            if (super.saveClickX > 0 && super.saveClickY > 0 && super.saveClickX < getScreenWidth()
                    && super.saveClickY < getScreenHeight()) {
                int offsetX = super.saveClickX - 0 - width / 2;
                if (offsetX + width > getScreenWidth()) {
                    offsetX = getScreenWidth() - width;
                }
                if (offsetX < 0) {
                    offsetX = 0;
                }
                int offsetY = super.saveClickY - 0;
                if (offsetY + menHeight > getScreenHeight()) {
                    offsetY = getScreenHeight() - menHeight;
                }
                if (offsetY < 0) {
                    offsetY = 0;
                }
                menuOpen = true;
                menuScreenArea = 0;
                menuOffsetX = offsetX;
                menuOffsetY = offsetY;
                menuWidth = width;
                menuHeight = 15 * menuActionRow + 22;
            }
        }

    }

    /*
     * scene_draw_x = spriteDrawX scene_draw_y = spriteDrawY SRC:
     * https://www.rune-server.ee/runescape-development/rs2-client/help/656573-hp-
     * overlay.html
     */

    /*
     * private void get_raster_position(int raster_x, int height, int raster_y)
     * {//calcEntityScreenPos if (raster_x < 128 || raster_y < 128 || raster_x >
     * 13056 || raster_y > 13056) { scene_draw_x = -1; scene_draw_y = -1; return; }
     * int tile_bounds = method42(plane, raster_y, raster_x) - height; raster_x -=
     * absoluteX; tile_bounds -= anchor; raster_y -= absoluteY; int sine_y =
     * Model.SINE[yCameraCurve]; int cosine_y = Model.COSINE[yCameraCurve]; int
     * sine_x = Model.SINE[xCameraCurve]; int cosine_x = Model.COSINE[xCameraCurve];
     * int pos = raster_y * sine_x + raster_x * cosine_x >> 16; raster_y = raster_y
     * * cosine_x - raster_x * sine_x >> 16; raster_x = pos; pos = tile_bounds *
     * cosine_y - raster_y * sine_y >> 16; raster_y = tile_bounds * sine_y +
     * raster_y * cosine_y >> 16; tile_bounds = pos; if (raster_y >= 50) {
     * scene_draw_x = Rasterizer.textureInt1 + (raster_x << SceneGraph.viewDistance)
     * / raster_y; scene_draw_y = Rasterizer.textureInt2 + (tile_bounds <<
     * SceneGraph.viewDistance) / raster_y; } else { scene_draw_x = -1; scene_draw_y
     * = -1; } }
     */

    public String getMoneyInPouch() {
        if (RSInterface.interfaceCache[8135].message == null)
            return "";
        String Cash = RSInterface.interfaceCache[8135].message;

        try {
            long convertedMoney = Long.parseLong(Cash);
            Cash = formatAmount(convertedMoney);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return Cash;
    }

    public final String formatAmount(long amount) {
        String format = "Too high!";
        if (amount >= 0 && amount < 100000) {
            format = String.valueOf(amount);
        } else if (amount >= 100000 && amount < 1000000) {
            format = amount / 1000 + "K";
        } else if (amount >= 1000000 && amount < 1000000000L) {
            format = amount / 1000000 + "M";
        } else if (amount >= 1000000000L && amount < 1000000000000L) {
            format = amount / 1000000000 + "B";
        } else if (amount >= 10000000000000L && amount < 10000000000000000L) {
            format = amount / 1000000000000L + "T";
        } else if (amount >= 10000000000000000L && amount < 1000000000000000000L) {
            format = amount / 1000000000000000L + "QD";
        } else if (amount >= 1000000000000000000L && amount < Long.MAX_VALUE) {
            format = amount / 1000000000000000000L + "QT";
        }
        return format;
    }

    private void doAction(int actionId) {
        if (actionId < 0) {
            return;
        }

        if (inputDialogState != 0) {
            inputDialogState = 0;
            inputTaken = true;
        }

        bankItemDragSprite = null;

        int nodeId = menuActionCmd1[actionId];
        int slot = menuActionCmd2[actionId];
        int interfaceId = menuActionCmd3[actionId];
        int fourthMenuAction = menuActionCmd4[actionId];
        int action = menuActionID[actionId];
        int x = slot;
        int y = interfaceId;
        int id = nodeId > 0x7fff ? fourthMenuAction : nodeId >> 14 & 0x7fff;

        if (action == 1075) {
            getOut().putOpcode(18);
            getOut().writeUnsignedWordBigEndian(nodeId);
        }

        if (action == 291) {
            getOut().putOpcode(140);
            getOut().writeUnsignedWordBigEndian(interfaceId);
            getOut().writeUnsignedWordA(nodeId);
            getOut().writeUnsignedWordA(slot);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID)
                atInventoryInterfaceType = 1;
            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }

        if (action == 300) {
            getOut().putOpcode(141);
            getOut().writeUnsignedWordA(slot);
            getOut().putShort(interfaceId);
            getOut().writeUnsignedWordA(nodeId);
            getOut().putInt(modifiableXValue);
        }

        if (action == 1045) {// Toggle quick prayers / curses
            if (openInterfaceID != -1)
                pushMessage("Please close the open interface first.", 0, "");
            else {
                if ((currentStats[5] / 10) > 0)
                    handleQuickAidsActive();
                else
                    pushMessage("You need to recharge your Prayer points at an altar.", 0, "");
                return;
            }
            return;
        } else if (action == 1046) {// Select quick prayers / curses
            toggleQuickAidsSelection();
            return;
        }

        if (action >= 2000) {
            action -= 2000;
        }

        if (interfaceId == 24630 || interfaceId == 24632) {
            if (inputDialogState == 3) {
                getGrandExchange().searching = false;
                getGrandExchange().totalItemResults = 0;
                amountOrNameInput = "";
            }
        }

        if (action == 22700 || action == 1251) {
            getGrandExchange().searching = false;
            getOut().putOpcode(204);
            getOut().putShort(getGrandExchange().itemSelected);
            return;
        }

        if (action >= 990 && action <= 992) {
            getOut().putOpcode(8);
            getOut().putInt(action);
            inputString = "";
            privateChatMode = action - 990;
            return;
        }

        if (action == 712) {
           // Configuration.MONEY_POUCH_ENABLED = !Configuration.MONEY_POUCH_ENABLED;
        } else if (action == 714) {
            sendPacket185(27656);
        } else if (action == 713) {
            if (openInterfaceID > 0 && openInterfaceID != 3323 && openInterfaceID != 6575) {
                pushMessage("Please close the interface you have open before opening another one.", 0, "");
                return;
            }
            inputTitle = "Enter amount of coins to withdraw:";
            amountOrNameInput = "";
            interfaceButtonAction = 557;
            // showInput = false;
            inputDialogState = 1;
            inputTaken = true;
            withdrawingMoneyFromPouch = true;
            return;
        } else if (action == 715) {
            String cash = getMoneyInPouch();
            pushMessage("Your money pouch currently contains " + cash + " ("
                    + Long.parseLong(RSInterface.interfaceCache[8135].message) + ") coins.", 0, "");
        } else {
            withdrawingMoneyFromPouch = false;
        }

        switch (action) {
            case 1042:
            case 1716:
            case 1037:
            case 1038:
            case 1039:
            case 1040:
            case 1041:
            case 1095:
                getOut().putOpcode(185);
                getOut().putInt(action);
                break;
            case 1050:
                getOut().putOpcode(185);
                getOut().putInt(152);
                break;
            case 1013:
                PlayerHandler.totalXP = 0;
                getOut().putOpcode(185);
                getOut().putInt(1013);
                break;
            case 1036:
                getOut().putOpcode(185);
                getOut().putInt(1036);
                break;
        }

        if (action == 476) {
            alertHandler.close();
            alertManager.close();
        }

        if (action == 471) {
            broadcastMessage = null;
            broadcastMinutes = 0;
        }

        if (action == 1014) {
            setNorth();
        }

        if (action == 1007) {
            PlayerHandler.canGainXP = PlayerHandler.canGainXP ? false : true;
        }

        if (action == 1006 && !PlayerHandler.showBonus) {
            if (!PlayerHandler.gains.isEmpty()) {
                PlayerHandler.gains.removeAll(PlayerHandler.gains);
            }
            PlayerHandler.showXP = PlayerHandler.showXP ? false : true;
        }

        if (action == 1030 && !PlayerHandler.showXP) {
            PlayerHandler.showBonus = PlayerHandler.showBonus ? false : true;
        }

        if (action == 104) {
            spellID = interfaceId;
            if (!autoCast || (autocastId != spellID)) {
                autoCast = true;
                autocastId = spellID;
                sendPacket185(autocastId, -1, 135);
                // pushMessage("Autocast spell selected.", 0, "");
            } else if (autocastId == spellID) {
                setAutoCastOff();
            }
        }

        if (action == 582) {
            NPC npc = npcArray[nodeId];

            if (npc != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0], myPlayer.smallX[0], false, npc.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(57);
                getOut().writeUnsignedWordA(anInt1285);
                getOut().writeUnsignedWordA(nodeId);
                getOut().writeUnsignedWordBigEndian(anInt1283);
                getOut().writeUnsignedWordA(anInt1284);
            }
        }

        if (action == 234) {
            boolean flag1 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, interfaceId, myPlayer.smallX[0], false, x);

            if (!flag1) {
                flag1 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, interfaceId, myPlayer.smallX[0], false, x);
            }

            crossX = super.saveClickX;
            crossY = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            getOut().putOpcode(236);
            getOut().writeUnsignedWordBigEndian(interfaceId + regionBaseY);
            getOut().putShort(nodeId);
            getOut().writeUnsignedWordBigEndian(slot + regionBaseX);
        }

        if (action == 62 && method66(nodeId, y, x, id)) {
            getOut().putOpcode(192);
            getOut().putShort(anInt1284);
            getOut().putShort(id);
            getOut().writeSignedBigEndian(y + regionBaseY);
            getOut().writeUnsignedWordBigEndian(anInt1283);
            getOut().writeSignedBigEndian(x + regionBaseX);
            getOut().putShort(anInt1285);
        }

        if (action == 511) {
            boolean flag2 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, y, myPlayer.smallX[0], false, x);

            if (!flag2) {
                flag2 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, y, myPlayer.smallX[0], false, x);
            }

            crossX = super.saveClickX;
            crossY = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            getOut().putOpcode(25);
            getOut().writeUnsignedWordBigEndian(anInt1284);
            getOut().writeUnsignedWordA(anInt1285);
            getOut().putShort(nodeId);
            getOut().writeUnsignedWordA(y + regionBaseY);
            getOut().writeSignedBigEndian(anInt1283);
            getOut().putShort(x + regionBaseX);
        }

        if (action == 74) {
            getOut().putOpcode(122);
            getOut().putShort(interfaceId);
            getOut().putShort(slot);
            getOut().putShort(nodeId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = y;
            atInventoryIndex = x;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[y].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[y].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }
        if (action == 222) {
            getOut().putOpcode(222);
            getOut().putShort(interfaceId);
            getOut().putByte(currentActionMenu);
        }
        if (action == 315) {
            if (interfaceId > 72150 && interfaceId < 73150) {
                switch (Youtube.getCurrentType()) {
                    case "video":
                        int index = interfaceId - 72151;
                        if (index > 0) {
                            index /= 10;
                        }
                        launchURL("http://youtube.com/watch?v=" + Youtube.getVideos().get(index).getVideoId());
                        break;
                    case "channel":
                        int indexc = interfaceId - 72151;
                        if (indexc > 0) {
                            indexc /= 7;
                        }
                        launchURL("http://youtube.com/channel/" + Youtube.getChannels().get(indexc).getChannelId());

                        break;
                }
            }
            switch (interfaceId) {
                case 72103:
                    Youtube.setCurrentType("video");
                    updateYoutubeInterface();
                    break;
                case 72107:
                    Youtube.setCurrentType("channel");
                    updateYoutubeInterface();
                    break;
                case 24654:
                    amountOrNameInput = "";
                    getGrandExchange().totalItemResults = 0;
                    getGrandExchange().searching = !getGrandExchange().searching;// inputDialogState == 3 ? false : true;
                    inputDialogState = inputDialogState == 3 ? 0 : 3;
                    break;
                case 26016: // fixed
                case 41806: // fixed
                    toggleSize(ScreenMode.FIXED);
                    break;

                case 26019: // resizable
                case 41807: // resizable
                    toggleSize(ScreenMode.RESIZABLE);
                    break;

                case 26022: // fullscreen
                    toggleSize(ScreenMode.FULLSCREEN);
            }

            RSInterface class9 = RSInterface.interfaceCache[interfaceId];

            if (class9.type == 77) {
                boolean check = !class9.inputToggled;
                disableInputFields();
                class9.inputToggled = check;
                if (class9.inputToggled)
                    this.inputFieldSelected = class9;
                if (class9.inputToggled && class9.inputText.equals(class9.defaultText)) {
                    class9.inputText = "";
                } else if (!class9.inputToggled && class9.inputText.equalsIgnoreCase("")) {
                    class9.inputText = class9.defaultText;
                }
            }

            if (class9.id >= 44008 && class9.id <= 44027) {
                RSInterface.interfaceCache[44000].children[6] = ((class9.id - 44008) * 200) + 44100;
            }

            boolean flag8 = true;
            if ((class9.contentType == 1321) || (class9.contentType == 1322) || (class9.contentType == 1323)) {
                int index = class9.id - 79924;
                if (index >= 50)
                    index -= 50;
                if (index >= 25)
                    index -= 25;
                Skills.selectedSkillId = Skills.SKILL_ID_NAME(Skills.SKILL_NAMES[index]);
            }
            if (class9.contentType > 0) {
                flag8 = promptUserForInput(class9);
            }

            if (flag8) {
                switch (interfaceId) {
                    case 21341:
                        sendFrame248(21172, 3213);
                        resetInterfaceAnimation(21172);
                        inputTaken = true;
                        break;
                    case 17231:// Quick prayer confirm
                        saveQuickSelection();
                        break;
                    default:
                        if (interfaceId >= 17202 && interfaceId <= 17227 || interfaceId == 17279 || interfaceId == 17280) {
                            try {
                                togglePrayerState(interfaceId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            getOut().putOpcode(185);
                            getOut().putInt(interfaceId);
                        }
                        break;
                }
            }
        }

        if (action == 561) {
            Player player = playerArray[nodeId];

            if (player != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false,
                        player.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                setAnInt1188(getAnInt1188() + id);

                if (getAnInt1188() >= 90) {
                    getOut().putOpcode(136);
                    setAnInt1188(0);
                }

                getOut().putOpcode(128);
                getOut().putShort(nodeId);
            }
        }

        if (action == 20) {
            NPC npc = npcArray[nodeId];
            if (npc != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0], myPlayer.smallX[0], false, npc.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(155);
                getOut().writeUnsignedWordBigEndian(nodeId);
            }
        }

        if (action == 779) {
            Player player = playerArray[nodeId];

            if (player != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false,
                        player.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(153);
                getOut().writeUnsignedWordBigEndian(nodeId);
            }
        }

        if (action == 516) {
            if (!menuOpen) {
                scene.method312(super.saveClickY - 4, super.saveClickX - 4);
            } else {
                scene.method312(interfaceId - 4, slot - 4);
            }
        }

        if (action == 1062) {
            setAnInt924(getAnInt924() + regionBaseX);

            if (getAnInt924() >= 113) {
                getOut().putOpcode(183);
                getOut().putDWordBigEndian(0xe63271);
                setAnInt924(0);
            }

            method66(nodeId, y, x, id);
            getOut().putOpcode(228);
            getOut().writeUnsignedWordA(id);
            getOut().writeUnsignedWordA(y + regionBaseY);
            getOut().putShort(x + regionBaseX);
        }
        if (action == 679 && !aBoolean1149) {
            getOut().putOpcode(40);
            getOut().putShort(interfaceId);
            aBoolean1149 = true;
        }

        if (action == 431) {
            getOut().putOpcode(129);
            getOut().writeUnsignedWordA(slot);
            getOut().putShort(interfaceId);
            getOut().writeUnsignedWordA(nodeId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 337 || action == 42 || action == 792 || action == 322) {
            String s = menuActionName[actionId];
            int k1 = s.indexOf("@whi@");

            if (k1 != -1) {
                long l3 = TextClass.longForName(NAME_PATTERN.matcher(s.substring(k1 + 5)).replaceAll(""));

                if (action == 337) {
                    addFriend(l3);
                } else if (action == 42) {
                    addIgnore(l3);
                } else if (action == 792) {
                    delFriend(l3);
                } else if (action == 322) {
                    delIgnore(l3);
                }
            }
        }
        if (action == 53) {
            getOut().putOpcode(135);
            getOut().writeUnsignedWordBigEndian(slot);
            getOut().writeUnsignedWordA(interfaceId);
            getOut().writeUnsignedWordBigEndian(nodeId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 54) {
            int newId = RSInterface.interfaceCache[interfaceId].parentID == 5292 ? 11
                    : RSInterface.interfaceCache[interfaceId].parentID == 5063 ? 12 : interfaceId;
            getOut().putOpcode(135);
            getOut().writeUnsignedWordBigEndian(slot);
            getOut().writeUnsignedWordA(newId);
            getOut().writeUnsignedWordBigEndian(nodeId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID)
                atInventoryInterfaceType = 1;
            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }

        if (action == 539) {
            getOut().putOpcode(16);
            getOut().writeUnsignedWordA(nodeId);
            getOut().writeSignedBigEndian(slot);
            getOut().writeSignedBigEndian(interfaceId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 484 || action == 6) {
            String s1 = menuActionName[actionId];
            int l1 = s1.indexOf("@whi@");

            if (l1 != -1) {
                s1 = s1.substring(l1 + 5).trim();
                String s7 = TextClass.fixName(TextClass.nameForLong(TextClass.longForName(s1)));
                boolean flag9 = false;

                for (int j3 = 0; j3 < playerCount; j3++) {
                    Player player = playerArray[playerIndices[j3]];

                    if (player == null || player.name == null || !player.name.equalsIgnoreCase(s7)) {
                        continue;
                    }

                    doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false,
                            player.smallX[0]);

                    if (action == 484) {
                        getOut().putOpcode(139);
                        getOut().writeUnsignedWordBigEndian(playerIndices[j3]);
                    }

                    if (action == 6) {
                        setAnInt1188(getAnInt1188() + nodeId);

                        if (getAnInt1188() >= 90) {
                            getOut().putOpcode(136);
                            setAnInt1188(0);
                        }

                        getOut().putOpcode(128);
                        getOut().putShort(playerIndices[j3]);
                    }

                    flag9 = true;
                    break;
                }

                if (!flag9) {
                    pushMessage("Unable to find " + s7, 0, "");
                }
            }
        }

        if (action == 1673) {
            String s1 = menuActionName[actionId];
            int l1 = s1.indexOf("@whi@");
            if (l1 != -1) {
                s1 = s1.substring(l1 + 5).trim();
                String s7 = TextClass.fixName(TextClass.nameForLong(TextClass.longForName(s1)));
                boolean flag9 = false;
                for (int j3 = 0; j3 < playerCount; j3++) {
                    Player class30_sub2_sub4_sub1_sub2_7 = playerArray[playerIndices[j3]];
                    if (class30_sub2_sub4_sub1_sub2_7 == null || class30_sub2_sub4_sub1_sub2_7.name == null
                            || !class30_sub2_sub4_sub1_sub2_7.name.equalsIgnoreCase(s7)) {
                        continue;
                    }
                    doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_7.smallY[0],
                            myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_7.smallX[0]);
                    getOut().putOpcode(193);
                    getOut().writeUnsignedWordBigEndian(playerIndices[j3]);
                    flag9 = true;
                    break;
                }

                if (!flag9) {
                    pushMessage("Unable to find " + s7 + ".", 0, "");
                }
            }
        }

        if (action == 870) {
            getOut().putOpcode(53);
            getOut().putShort(slot);
            getOut().writeUnsignedWordA(anInt1283);
            getOut().writeSignedBigEndian(nodeId);
            getOut().putShort(anInt1284);
            getOut().writeUnsignedWordBigEndian(anInt1285);
            getOut().putShort(interfaceId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 1773) {
            String s1 = menuActionName[actionId];
            int l1 = s1.indexOf("@whi@");
            if (l1 != -1) {
                s1 = s1.substring(l1 + 5).trim();
                String s7 = TextClass.fixName(TextClass.nameForLong(TextClass.longForName(s1)));
                boolean flag9 = false;
                for (int j3 = 0; j3 < playerCount; j3++) {
                    Player class30_sub2_sub4_sub1_sub2_7 = playerArray[playerIndices[j3]];
                    if (class30_sub2_sub4_sub1_sub2_7 == null || class30_sub2_sub4_sub1_sub2_7.name == null
                            || !class30_sub2_sub4_sub1_sub2_7.name.equalsIgnoreCase(s7)) {
                        continue;
                    }
                    doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_7.smallY[0],
                            myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_7.smallX[0]);
                    getOut().putOpcode(220);
                    getOut().writeUnsignedWordBigEndian(playerIndices[j3]);
                    flag9 = true;
                    break;
                }

                if (!flag9) {
                    pushMessage("Unable to find " + s7 + ".", 0, "");
                }
            }
        }

        if (action == 847) {
            getOut().putOpcode(87);
            getOut().writeUnsignedWordA(nodeId);
            getOut().putShort(interfaceId);
            getOut().writeUnsignedWordA(slot);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 626) {
            RSInterface class9_1 = RSInterface.interfaceCache[interfaceId];
            int childId = interfaceId;
            spellSelected = 1;
            spellUsableOn = Integer.parseInt(MagicInterfaceData.getSpellData(childId, "spellUsableOn"));
            itemSelected = 0;
            spellID = childId;
            String s4 = MagicInterfaceData.getSpellData(childId, "selectedActionName");
            if (s4.indexOf(" ") != -1)
                s4 = s4.substring(0, s4.indexOf(" "));
            String s8 = MagicInterfaceData.getSpellData(childId, "selectedActionName");
            if (s8.indexOf(" ") != -1)
                s8 = s8.substring(s8.indexOf(" ") + 1);
            spellTooltip = s4 + " " + MagicInterfaceData.getSpellData(childId, "spellname") + " " + s8;
            if (spellUsableOn == 16) {
                tabID = 3;
                tabAreaAltered = true;
            }
            selectedSpellId = spellID;
            return;
        }

        if (action == 78) {
            getOut().putOpcode(117);
            getOut().writeSignedBigEndian(interfaceId);
            getOut().writeSignedBigEndian(nodeId);
            getOut().writeUnsignedWordBigEndian(slot);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 27) {
            Player class30_sub2_sub4_sub1_sub2_2 = playerArray[nodeId];

            if (class30_sub2_sub4_sub1_sub2_2 != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_2.smallY[0],
                        myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_2.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                setAnInt986(getAnInt986() + nodeId);

                if (getAnInt986() >= 54) {
                    getOut().putOpcode(189);
                    getOut().putByte(234);
                    setAnInt986(0);
                }

                getOut().putOpcode(73);
                getOut().writeUnsignedWordBigEndian(nodeId);
            }
        }

        if (action == 213) {
            boolean flag3 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, interfaceId, myPlayer.smallX[0], false,
                    slot);

            if (!flag3) {
                flag3 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, interfaceId, myPlayer.smallX[0], false, slot);
            }

            crossX = super.saveClickX;
            crossY = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            getOut().putOpcode(79);
            getOut().writeUnsignedWordBigEndian(interfaceId + regionBaseY);
            getOut().putShort(nodeId);
            getOut().writeUnsignedWordA(slot + regionBaseX);
        }

        if (action == 633) {
            if (menuOpen) {
                action = 632;
            } else {
                determineMenuSize();
            }
        }
        if (action == 632) {
            if (openInterfaceID == 53700 || openInterfaceID == 54700) {
                if (interfaceId == 53781) {
                    interfaceId = 2901;
                    slot = 0;
                } else if (interfaceId == 53782) {
                    interfaceId = 2901;
                    slot = 1;
                } else if (interfaceId == 54781) {
                    interfaceId = 2902;
                    slot = 0;
                } else if (interfaceId == 54782) {
                    interfaceId = 2902;
                    slot = 1;
                }
            }
            getOut().putOpcode(145);
            getOut().writeUnsignedWordA(interfaceId);
            getOut().writeUnsignedWordA(slot);
            getOut().writeUnsignedWordA(nodeId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 1004) {
            if (tabInterfaceIDs[10] != -1) {
                tabID = 10;
                tabAreaAltered = true;
            }
        }

        if (action == 1003) {
            inputTaken = true;
        }

        if (action == 1002) {
            inputTaken = true;
        }

        if (action == 1001) {
            inputTaken = true;
        }

        if (action == 1000) {
            chatArea.toggleButton(this, 4);
            cButtonCPos = 4;
            chatTypeView = 11;
            inputTaken = true;
        }

        if (action == 999) {
            chatArea.toggleButton(this, 0);
            cButtonCPos = 0;
            chatTypeView = 0;
            inputTaken = true;
        }

        if (action == 998) {
            chatArea.toggleButton(this, 1);
            cButtonCPos = 1;
            chatTypeView = 5;
            inputTaken = true;
        }

        if (action == 997) {
            publicChatMode = 3;
            inputTaken = true;
        }

        if (action == 996) {
            publicChatMode = 2;
            inputTaken = true;
        }

        if (action == 995) {
            publicChatMode = 1;
            inputTaken = true;
        }

        if (action == 994) {
            publicChatMode = 0;
            inputTaken = true;
        }

        if (action == 993) {
            chatArea.toggleButton(this, 2);
            cButtonCPos = 2;
            chatTypeView = 1;
            inputTaken = true;
        }

        if (action == 992) {
            privateChatMode = 2;
            inputTaken = true;
            privateChatMode = 2;
            getOut().putOpcode(95);
            getOut().putByte(publicChatMode);
            getOut().putByte(privateChatMode);
            getOut().putByte(tradeMode);
        }

        if (action == 991) {
            privateChatMode = 1;
            inputTaken = true;
            getOut().putOpcode(95);
            getOut().putByte(publicChatMode);
            getOut().putByte(privateChatMode);
            getOut().putByte(tradeMode);
        }

        if (action == 990) {
            privateChatMode = 0;
            inputTaken = true;
            getOut().putOpcode(95);
            getOut().putByte(publicChatMode);
            getOut().putByte(privateChatMode);
            getOut().putByte(tradeMode);
        }

        if (action == 989) {
            chatArea.toggleButton(this, 3);
            cButtonCPos = 3;
            chatTypeView = 2;
            inputTaken = true;
            getOut().putOpcode(95);
            getOut().putByte(publicChatMode);
            getOut().putByte(privateChatMode);
            getOut().putByte(tradeMode);
        }

        if (action == 987) {
            tradeMode = 2;
            inputTaken = true;
        }

        if (action == 986) {
            tradeMode = 1;
            inputTaken = true;
        }

        if (action == 985) {
            tradeMode = 0;
            inputTaken = true;
        }

        if (action == 984) {
            chatArea.toggleButton(this, 5);
            cButtonCPos = 5;
            chatTypeView = 3;
            inputTaken = true;
        }

        if (action == 983) {
            duelStatus = 2;
        }
        if (action == 982) {
            duelStatus = 1;
        }
        if (action == 981) {
            duelStatus = 0;
        }

        if (action == 980) {
            chatArea.toggleButton(this, 6);
            cButtonCPos = 6;
            chatTypeView = 4;
            inputTaken = true;
        }

        if (action == 983) {
            inputTaken = true;
        }

        if (action == 982) {
            inputTaken = true;
        }

        if (action == 981) {
            inputTaken = true;
        }

       /* if (action == 980) {
            chatArea.toggleButton(this, 6);
            cButtonCPos = 6;
            chatTypeView = 4;
            inputTaken = true;
        }*/

        if (action == 493) {
            getOut().putOpcode(75);
            getOut().writeSignedBigEndian(interfaceId);
            getOut().writeUnsignedWordBigEndian(slot);
            getOut().writeUnsignedWordA(nodeId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 652) {
            boolean flag4 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, interfaceId, myPlayer.smallX[0], false,
                    slot);

            if (!flag4) {
                flag4 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, interfaceId, myPlayer.smallX[0], false, slot);
            }

            crossX = super.saveClickX;
            crossY = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            getOut().putOpcode(156);
            getOut().writeUnsignedWordA(slot + regionBaseX);
            getOut().writeUnsignedWordBigEndian(interfaceId + regionBaseY);
            getOut().writeSignedBigEndian(nodeId);
        }

        if (action == 94) {
            boolean flag5 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, interfaceId, myPlayer.smallX[0], false,
                    slot);

            if (!flag5) {
                flag5 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, interfaceId, myPlayer.smallX[0], false, slot);
            }

            crossX = super.saveClickX;
            crossY = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            getOut().putOpcode(181);
            getOut().writeUnsignedWordBigEndian(interfaceId + regionBaseY);
            getOut().putShort(nodeId);
            getOut().writeUnsignedWordBigEndian(slot + regionBaseX);
            getOut().writeUnsignedWordA(selectedSpellId);
        }

        if (action == 646) {

            getOut().putOpcode(185);
            getOut().putInt(interfaceId);
            RSInterface class9_2 = RSInterface.interfaceCache[interfaceId];

            if (class9_2.valueIndexArray != null && class9_2.valueIndexArray[0][0] == 5) {
                int i2 = class9_2.valueIndexArray[0][1];

                if (variousSettings[i2] != class9_2.requiredValues[0]) {
                    variousSettings[i2] = class9_2.requiredValues[0];
                    updateConfig(i2);
                }

                switch (interfaceId) {
                        case 72103:
                            Youtube.setCurrentType("video");
                            updateYoutubeInterface();
                            break;
                        case 72107:
                            Youtube.setCurrentType("channel");
                            updateYoutubeInterface();
                            break;
                    case 25841:// More options

                        tabInterfaceIDs[11] = 41750;
                        prayerInterfaceType = tabInterfaceIDs[5];
                        tabAreaAltered = true;

                     /*   i2 = openInterfaceID == 26000 ? 0 : 1;
                        sendFrame36(175, i2);

                        if (i2 == 1) {
                            openInterfaceID = 26000;
                        } else {
                            openInterfaceID = -1;
                        }*/

                        break;
                }
            }
        }

        if (action == 225) {
            NPC npc = npcArray[nodeId];

            if (npc != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0], myPlayer.smallX[0], false, npc.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                setAnInt1226(getAnInt1226() + nodeId);
                getOut().putOpcode(17);
                getOut().writeSignedBigEndian(nodeId);
            }
        }

        if (action == 965) {
            NPC npc = npcArray[nodeId];

            if (npc != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0], myPlayer.smallX[0], false, npc.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                setAnInt1134(getAnInt1134() + 1);
                getOut().putOpcode(21);
                getOut().putShort(nodeId);
            }
        }

        if (action == 413) {
            NPC npc = npcArray[nodeId];

            if (npc != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0], myPlayer.smallX[0], false, npc.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(131);
                getOut().writeSignedBigEndian(nodeId);
                getOut().writeUnsignedWordA(selectedSpellId);
            }
        }

        if (action == 200) {
            closeGameInterfaces();
        }

        if (action == 1025) {
            NPC npc = npcArray[nodeId];
            if (npc != null) {
                MobDefinition entityDef = npc.definitionOverride;
                if (entityDef != null) {
                    getOut().putOpcode(6); // examine npc
                    getOut().putShort(entityDef.id);
                }
            }
        }

        if (action == 900) {
            method66(nodeId, y, x, id);
            getOut().putOpcode(252);
            getOut().writeSignedBigEndian(id);
            getOut().writeUnsignedWordBigEndian(y + regionBaseY);
            getOut().writeUnsignedWordA(x + regionBaseX);
        }

        if (action == 412) {
            NPC npc = npcArray[nodeId];

            if (npc != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0], myPlayer.smallX[0], false, npc.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(72);
                getOut().writeUnsignedWordA(nodeId);
            }
        }

        if (action == 365) {
            Player player = playerArray[nodeId];

            if (player != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false,
                        player.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(249);
                getOut().writeUnsignedWordA(nodeId);
                getOut().writeUnsignedWordBigEndian(selectedSpellId);
            }
        }

        if (action == 729) {
            Player player = playerArray[nodeId];

            if (player != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false,
                        player.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(39);
                getOut().writeUnsignedWordBigEndian(nodeId);
            }
        }

        if (action == 6300) {
            Player player = playerArray[nodeId]; // player index in the player array
            if (player != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false,
                        player.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(191);
                getOut().putShort(nodeId);
            }
        }

        if (action == 6305) {

            Player player = playerArray[nodeId]; // player index in the player array
            if (player != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false,
                        player.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(216);
                getOut().putShort(nodeId);
            }
        }

        if (action == 577) {
            Player player = playerArray[nodeId];

            if (player != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false,
                        player.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(139);
                getOut().writeUnsignedWordBigEndian(nodeId);
            }
        }

        if (action == 956 && method66(nodeId, y, x, id)) {
            getOut().putOpcode(35);
            getOut().writeUnsignedWordBigEndian(slot + regionBaseX);
            getOut().writeUnsignedWordA(selectedSpellId);
            getOut().writeUnsignedWordA(interfaceId + regionBaseY);
            getOut().writeUnsignedWordBigEndian(id);
        }

        if (action == 567) {
            boolean flag6 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, interfaceId, myPlayer.smallX[0], false,
                    slot);

            if (!flag6) {
                flag6 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, interfaceId, myPlayer.smallX[0], false, slot);
            }

            crossX = super.saveClickX;
            crossY = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            getOut().putOpcode(23);
            getOut().writeUnsignedWordBigEndian(interfaceId + regionBaseY);
            getOut().writeUnsignedWordBigEndian(nodeId);
            getOut().writeUnsignedWordBigEndian(slot + regionBaseX);
        }

        if (action == 867) {
            if ((id & 3) == 0) {
                setAnInt1175(getAnInt1175() + 1);
            }

            if (getAnInt1175() >= 59) {
                getOut().putOpcode(200);
                getOut().putShort(25501);
                setAnInt1175(0);
            }

            getOut().putOpcode(43);
            getOut().writeUnsignedWordBigEndian(interfaceId);
            getOut().writeUnsignedWordA(nodeId);
            getOut().writeUnsignedWordA(slot);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 543) {
            getOut().putOpcode(237);
            getOut().putShort(slot);
            getOut().writeUnsignedWordA(nodeId);
            getOut().putShort(interfaceId);
            getOut().writeUnsignedWordA(selectedSpellId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 491) {
            Player player = playerArray[nodeId];

            if (player != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false,
                        player.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                getOut().putOpcode(14);
                getOut().writeUnsignedWordA(anInt1284);
                getOut().putShort(nodeId);
                getOut().putShort(anInt1285);
                getOut().writeUnsignedWordBigEndian(anInt1283);
            }
        }

        if (action == 639) {
            String s3 = menuActionName[actionId];
            int k2 = s3.indexOf("@whi@");

            if (k2 != -1) {
                long l4 = TextClass.longForName(s3.substring(k2 + 5).trim());
                int k3 = -1;

                for (int i4 = 0; i4 < friendCount; i4++) {
                    if (friendsListAsLongs[i4] != l4) {
                        continue;
                    }
                    k3 = i4;
                    break;
                }

                if (k3 != -1 && friendsNodeIDs[k3] > 0) {
                    inputTaken = true;
                    inputDialogState = 0;
                    messagePromptRaised = true;
                    promptInput = "";
                    friendsListAction = 3;
                    aLong953 = friendsListAsLongs[k3];
                    promptMessage = "Enter message to send to " + friendsList[k3];
                }
            }
        }

        if (action == 454) {
            getOut().putOpcode(41);
            getOut().putShort(nodeId);
            getOut().writeUnsignedWordA(slot);
            getOut().writeUnsignedWordA(interfaceId);
            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;

            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }

            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (action == 478) {
            NPC npc = npcArray[nodeId];

            if (npc != null) {
                doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0], myPlayer.smallX[0], false, npc.smallX[0]);
                crossX = super.saveClickX;
                crossY = super.saveClickY;
                crossType = 2;
                crossIndex = 0;

                if ((id & 3) == 0) {
                    setAnInt1155(getAnInt1155() + 1);
                }

                if (getAnInt1155() >= 53) {
                    getOut().putOpcode(85);
                    getOut().putByte(66);
                    setAnInt1155(0);
                }

                getOut().putOpcode(18);
                getOut().writeUnsignedWordBigEndian(nodeId);
            }
        }

        if (action == 113) {
            method66(nodeId, y, x, id);
            getOut().putOpcode(70);
            getOut().writeUnsignedWordBigEndian(slot + regionBaseX);
            getOut().putShort(interfaceId + regionBaseY);
            getOut().writeSignedBigEndian(id);
        }

        if (action == 872) {
            method66(nodeId, y, x, id);
            getOut().putOpcode(234);
            getOut().writeSignedBigEndian(x + regionBaseX);
            getOut().writeUnsignedWordA(id);
            getOut().writeSignedBigEndian(y + regionBaseY);
        }

        if (action == 502) {
            method66(nodeId, y, x, id);
            getOut().putOpcode(132);
            getOut().writeSignedBigEndian(x + regionBaseX);
            getOut().putShort(id);
            getOut().writeUnsignedWordA(y + regionBaseY);
        }

        if (action == 1125) {
            ItemDefinition definition = ItemDefinition.get(nodeId);
            if (interfaceId == 38274) {
                getOut().putOpcode(122);
                getOut().putShort(interfaceId);
                getOut().putShort(slot);
                getOut().putShort(nodeId);
            } else {
                getOut().putOpcode(2); // examine item
                getOut().putShort(definition.id);
            }
        }
        if (action == 1126) {
            getOut().putOpcode(138);
            getOut().writeUnsignedWordA(interfaceId);
            getOut().writeUnsignedWordA(slot);
            getOut().writeUnsignedWordA(nodeId);

            atInventoryLoopCycle = 0;
            atInventoryInterface = interfaceId;
            atInventoryIndex = slot;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[interfaceId].parentID == openInterfaceID)
                atInventoryInterfaceType = 1;
            if (RSInterface.interfaceCache[interfaceId].parentID == backDialogID)
                atInventoryInterfaceType = 3;
        }
        if (action == 169) {
            switch (interfaceId) {
                case 26026:
                case 41825:
                    Configuration.DISPLAY_HP_ABOVE_HEAD = !Configuration.DISPLAY_HP_ABOVE_HEAD;
                    pushMessage(
                            "Displaying hp above head turned " + (Configuration.DISPLAY_HP_ABOVE_HEAD ? "on" : "off") + ".",
                            0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.DISPLAY_HP_ABOVE_HEAD);
                    break;
                case 26065:
                case 41840:
                    Configuration.GROUND_TEXT = !Configuration.GROUND_TEXT;
                    pushMessage("Displaying ground text turned " + (Configuration.GROUND_TEXT ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.GROUND_TEXT);
                    break;
                case 26067:
                case 41837:
                    Configuration.FOG_ENABLED = !Configuration.FOG_ENABLED;
                    pushMessage("Displaying fog turned " + (Configuration.FOG_ENABLED ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.FOG_ENABLED);
                    break;
                case 26027:
                case 41828:
                    Configuration.DISPLAY_USERNAMES_ABOVE_HEAD = !Configuration.DISPLAY_USERNAMES_ABOVE_HEAD;
                    pushMessage("Displaying usernames above head turned "
                            + (Configuration.DISPLAY_USERNAMES_ABOVE_HEAD ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.DISPLAY_USERNAMES_ABOVE_HEAD);
                    break;
                case 26031:
                case 41816:
                    GameFrameConstants.gameframeType = GameFrameConstants.gameframeType == GameFrameType.FRAME_525
                            ? GameFrameType.FRAME_554
                            : GameFrameType.FRAME_525;
                    pushMessage("Gameframe toggled.", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, GameFrameConstants.gameframeType == GameFrameType.FRAME_525);
                    break;
                case 26014:
                case 41822:
                    Configuration.NEW_HITMARKS = !Configuration.NEW_HITMARKS;
                    pushMessage("New hitmarks turned " + (Configuration.NEW_HITMARKS ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.NEW_HITMARKS);
                    break;
                case 26007:
                case 41810:
                    Configuration.NEW_FUNCTION_KEYS = !Configuration.NEW_FUNCTION_KEYS;
                    pushMessage("New function keys turned " + (Configuration.NEW_FUNCTION_KEYS ? "on" : "off") + ".", 0,
                            "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.NEW_FUNCTION_KEYS);
                    break;
                case 26010:
                case 41813:
                    Configuration.NEW_CURSORS = !Configuration.NEW_CURSORS;
                    pushMessage("New cursors turned " + (Configuration.NEW_CURSORS ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.NEW_CURSORS);
                    oldCursor = null;
                    if (!Configuration.NEW_CURSORS) {
                        getGameComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    } else {
                        super.setCursor(CursorData.CURSOR_0);
                    }
                    break;
                case 26008:
                case 41819:
                    Configuration.NEW_HEALTH_BARS = !Configuration.NEW_HEALTH_BARS;
                    pushMessage("New health bars turned " + (Configuration.NEW_HEALTH_BARS ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.NEW_HEALTH_BARS);
                    break;
                case 26029:
                case 41831:
                    Configuration.CONSTITUTION_ENABLED = !Configuration.CONSTITUTION_ENABLED;
                    pushMessage("Constitution turned " + (Configuration.CONSTITUTION_ENABLED ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.CONSTITUTION_ENABLED);
                    break;
                case 26033:
                case 41834:
                    /*
                     * Configuration.NOTIFICATIONS_ENABLED = !Configuration.NOTIFICATIONS_ENABLED;
                     * pushMessage("Push notifications turned " +
                     * (Configuration.NOTIFICATIONS_ENABLED ? "on" : "off") + ".", 0, "");
                     * savePlayerData(); updateSetting(interfaceId,
                     * !Configuration.NOTIFICATIONS_ENABLED);
                     */
                    // HIGHLIGHT_USERNAME
                    Configuration.HIGHLIGHT_USERNAME = !Configuration.HIGHLIGHT_USERNAME;
                    pushMessage("Username highlighting turned " + (Configuration.HIGHLIGHT_USERNAME ? "on" : "off") + ".",
                            0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.HIGHLIGHT_USERNAME);
                    break;
                case 26054:
                    setLowDetail();
                    loadRegion();
                    pushMessage("Set to low detail.", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, false);
                    updateSetting(26058, false);
                    break;
                case 26058:
                    setHighDetail();
                    loadRegion();
                    pushMessage("Set to high detail.", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, false);
                    updateSetting(26054, false);
                    break;
                case 41843:
                    Configuration.ANIMATE_TEXTURES = !Configuration.ANIMATE_TEXTURES;
                    pushMessage("Animated textures turned " + (Configuration.ANIMATE_TEXTURES ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.ANIMATE_TEXTURES);
                    break;
                case 41846:
                    Configuration.PLAYER_EQUIPMENT = !Configuration.PLAYER_EQUIPMENT;
                    pushMessage("Rendering Player equipment turned " + (Configuration.PLAYER_EQUIPMENT ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.PLAYER_EQUIPMENT);
                    if (Configuration.PLAYER_EQUIPMENT)
                        sendPacket185(41847);
                    else
                        sendPacket185(41848);
                    break;
                case 41849:
                    Configuration.RENDER_PETS = !Configuration.RENDER_PETS;
                    pushMessage("Rendering pets turned " + (Configuration.RENDER_PETS ? "on" : "off") + ".", 0, "");
                    Save.settings(Client.getClient());
                    updateSetting(interfaceId, !Configuration.RENDER_PETS);
                    if (Configuration.RENDER_PETS)
                        sendPacket185(41850);
                    else
                        sendPacket185(41851);
                    break;
                default:
                    getOut().putOpcode(185);
                    getOut().putInt(interfaceId);
                    if (interfaceId == 26003) {
                        return;
                    }
            }

            RSInterface rsinterface = RSInterface.interfaceCache[interfaceId];

            if (rsinterface.valueIndexArray != null && rsinterface.valueIndexArray[0][0] == 5) {
                int l2 = rsinterface.valueIndexArray[0][1];
                variousSettings[l2] = 1 - variousSettings[l2];
                updateConfig(l2);
            }
        }

        if (action == 447) {
            itemSelected = 1;
            anInt1283 = slot;
            anInt1284 = interfaceId;
            anInt1285 = nodeId;
            selectedItemName = ItemDefinition.get(nodeId).name;
            spellSelected = 0;
            return;
        }

        if (action == 1226) {
            ObjectDefinition definition = ObjectDefinition.forID(id);
            String examine;

            if (definition.description != null) {
                examine = new String(definition.description);
            } else {
                examine = "It's a " + definition.name + ".";
            }

            pushMessage(examine, 0, "");
        }

        if (action == 244) {
            boolean flag7 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, interfaceId, myPlayer.smallX[0], false,
                    slot);

            if (!flag7) {
                flag7 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, interfaceId, myPlayer.smallX[0], false, slot);
            }

            crossX = super.saveClickX;
            crossY = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            getOut().putOpcode(253);
            getOut().writeUnsignedWordBigEndian(slot + regionBaseX);
            getOut().writeSignedBigEndian(interfaceId + regionBaseY);
            getOut().writeUnsignedWordA(nodeId);
        }

        if (action == 1448) {
            ItemDefinition definition = ItemDefinition.get(nodeId);
            String examine;

            if (definition.description != null) {
                examine = new String(definition.description);
            } else {
                examine = "It's a " + definition.name + ".";
            }

            pushMessage(examine, 0, "");
        }

        itemSelected = 0; // RIGHT HERE

        if (action != 626) {
            spellSelected = 0;
        }
        if (interfaceId == 957) {
            variousSettings[287] = variousSettings[502] = variousSettings[502] == 1 ? 0 : 1;
            updateConfig(287);
            Save.settings(Client.getClient());
        }
    }

    private void doFlamesDrawing() {
    }


    public void sendPacket185(int buttonID) {
        getOut().putOpcode(185);
        getOut().putInt(buttonID);
        RSInterface rsi = RSInterface.interfaceCache[buttonID];
        if (rsi == null) {
            return;
        }
        if (rsi.valueIndexArray != null && rsi.valueIndexArray[0][0] == 5) {
            int configID = rsi.valueIndexArray[0][1];
            variousSettings[configID] = 1 - variousSettings[configID];
        }
    }

    public void sendPacket185(int button, int toggle, int type) {
        switch (type) {
            case 135:
                RSInterface class9 = RSInterface.interfaceCache[button];
                boolean flag8 = true;
                if (class9.contentType > 0)
                    flag8 = promptUserForInput(class9);
                if (flag8) {
                    getOut().putOpcode(185);
                    getOut().putInt(button);
                }
                break;
        }
    }

    private boolean doWalkTo(int type, int j, int k, int i1, int j1, int k1, int l1, int i2, int j2, boolean flag,
                             int k2) {
        byte byte0 = 104;
        byte byte1 = 104;

        for (int l2 = 0; l2 < byte0; l2++) {
            for (int i3 = 0; i3 < byte1; i3++) {
                anIntArrayArray901[l2][i3] = 0;
                anIntArrayArray825[l2][i3] = 0x5f5e0ff;
            }
        }

        int j3 = j2;
        int k3 = j1;
        if (j2 >= anIntArrayArray901.length || j1 >= anIntArrayArray901[j2].length)
            return true;
        anIntArrayArray901[j2][j1] = 99;//BUG IS HERE
        anIntArrayArray825[j2][j1] = 0;
        int l3 = 0;
        int i4 = 0;
        bigX[l3] = j2;
        bigY[l3++] = j1;
        boolean flag1 = false;
        int j4 = bigX.length;
        int[][] ai = collisionData[plane].flags;

        while (i4 != l3) {
            j3 = bigX[i4];
            k3 = bigY[i4];
            i4 = (i4 + 1) % j4;

            if (j3 == k2 && k3 == i2) {
                flag1 = true;
                break;
            }

            if (i1 != 0) {
                if ((i1 < 5 || i1 == 10) && collisionData[plane].method219(k2, j3, k3, j, i1 - 1, i2)) {
                    flag1 = true;
                    break;
                }

                if (i1 < 10 && collisionData[plane].method220(k2, i2, k3, i1 - 1, j, j3)) {
                    flag1 = true;
                    break;
                }
            }

            if (k1 != 0 && k != 0 && collisionData[plane].method221(i2, k2, j3, k, l1, k1, k3)) {
                flag1 = true;
                break;
            }

            int l4 = anIntArrayArray825[j3][k3] + 1;

            if (j3 > 0 && anIntArrayArray901[j3 - 1][k3] == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0) {
                bigX[l3] = j3 - 1;
                bigY[l3] = k3;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 - 1][k3] = 2;
                anIntArrayArray825[j3 - 1][k3] = l4;
            }

            if (j3 < byte0 - 1 && anIntArrayArray901[j3 + 1][k3] == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0) {
                bigX[l3] = j3 + 1;
                bigY[l3] = k3;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 + 1][k3] = 8;
                anIntArrayArray825[j3 + 1][k3] = l4;
            }

            if (k3 > 0 && anIntArrayArray901[j3][k3 - 1] == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0) {
                bigX[l3] = j3;
                bigY[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3][k3 - 1] = 1;
                anIntArrayArray825[j3][k3 - 1] = l4;
            }

            if (k3 < byte1 - 1 && anIntArrayArray901[j3][k3 + 1] == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0) {
                bigX[l3] = j3;
                bigY[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3][k3 + 1] = 4;
                anIntArrayArray825[j3][k3 + 1] = l4;
            }

            if (j3 > 0 && k3 > 0 && anIntArrayArray901[j3 - 1][k3 - 1] == 0 && (ai[j3 - 1][k3 - 1] & 0x128010e) == 0
                    && (ai[j3 - 1][k3] & 0x1280108) == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0) {
                bigX[l3] = j3 - 1;
                bigY[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 - 1][k3 - 1] = 3;
                anIntArrayArray825[j3 - 1][k3 - 1] = l4;
            }

            if (j3 < byte0 - 1 && k3 > 0 && anIntArrayArray901[j3 + 1][k3 - 1] == 0
                    && (ai[j3 + 1][k3 - 1] & 0x1280183) == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0
                    && (ai[j3][k3 - 1] & 0x1280102) == 0) {
                bigX[l3] = j3 + 1;
                bigY[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 + 1][k3 - 1] = 9;
                anIntArrayArray825[j3 + 1][k3 - 1] = l4;
            }

            if (j3 > 0 && k3 < byte1 - 1 && anIntArrayArray901[j3 - 1][k3 + 1] == 0
                    && (ai[j3 - 1][k3 + 1] & 0x1280138) == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0
                    && (ai[j3][k3 + 1] & 0x1280120) == 0) {
                bigX[l3] = j3 - 1;
                bigY[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 - 1][k3 + 1] = 6;
                anIntArrayArray825[j3 - 1][k3 + 1] = l4;
            }

            if (j3 < byte0 - 1 && k3 < byte1 - 1 && anIntArrayArray901[j3 + 1][k3 + 1] == 0
                    && (ai[j3 + 1][k3 + 1] & 0x12801e0) == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0
                    && (ai[j3][k3 + 1] & 0x1280120) == 0) {
                bigX[l3] = j3 + 1;
                bigY[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 + 1][k3 + 1] = 12;
                anIntArrayArray825[j3 + 1][k3 + 1] = l4;
            }
        }

        anInt1264 = 0;

        if (!flag1) {
            if (flag) {
                int i5 = 100;

                for (int k5 = 1; k5 < 2; k5++) {
                    for (int i6 = k2 - k5; i6 <= k2 + k5; i6++) {
                        for (int l6 = i2 - k5; l6 <= i2 + k5; l6++) {
                            if (i6 >= 0 && l6 >= 0 && i6 < 104 && l6 < 104 && anIntArrayArray825[i6][l6] < i5) {
                                i5 = anIntArrayArray825[i6][l6];
                                j3 = i6;
                                k3 = l6;
                                anInt1264 = 1;
                                flag1 = true;
                            }
                        }
                    }

                    if (flag1) {
                        break;
                    }
                }

            }

            if (!flag1) {
                return false;
            }
        }

        i4 = 0;
        bigX[i4] = j3;
        bigY[i4++] = k3;
        int l5;

        for (int j5 = l5 = anIntArrayArray901[j3][k3]; j3 != j2 || k3 != j1; j5 = anIntArrayArray901[j3][k3]) {
            if (j5 != l5) {
                l5 = j5;
                bigX[i4] = j3;
                bigY[i4++] = k3;
            }

            if ((j5 & 2) != 0) {
                j3++;
            } else if ((j5 & 8) != 0) {
                j3--;
            }

            if ((j5 & 1) != 0) {
                k3++;
            } else if ((j5 & 4) != 0) {
                k3--;
            }
        }

        if (i4 > 0) {
            int k4 = i4;

            if (k4 > 25) {
                k4 = 25;
            }

            i4--;
            int k6 = bigX[i4];
            int i7 = bigY[i4];
            setAnInt1288(getAnInt1288() + k4);

            if (getAnInt1288() >= 92) {
                getOut().putOpcode(36);
                getOut().putInt(0);
                setAnInt1288(0);
            }

            if (type == 0) {
                getOut().putOpcode(229);
                getOut().putByte(plane);
                getOut().putOpcode(164);
                getOut().putByte(k4 + k4 + 3);
            }

            if (type == 1) {
                getOut().putOpcode(229);
                getOut().putByte(plane);
                getOut().putOpcode(248);
                getOut().putByte(k4 + k4 + 3 + 14);
            }

            if (type == 2) {
                getOut().putOpcode(229);
                getOut().putByte(plane);
                getOut().putOpcode(98);
                getOut().putByte(k4 + k4 + 3);
            }

            getOut().writeSignedBigEndian(k6 + regionBaseX);
            destX = bigX[0];
            destY = bigY[0];

            for (int j7 = 1; j7 < k4; j7++) {
                i4--;
                getOut().putByte(bigX[i4] - k6);
                getOut().putByte(bigY[i4] - i7);
            }

            getOut().writeUnsignedWordBigEndian(i7 + regionBaseY);
            getOut().method424(super.keyArray[5] != 1 ? 0 : 1);
            return true;
        }

        return type != 1;
    }

    private void draw3dScreen() {
        EffectTimers.draw();

        if (!chatArea.componentHidden()) {
            drawSplitPrivateChat();
        }

        alertHandler.processAlerts();
        alertManager.processAlerts();

        if (crossType == 1) {
            crosses[crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
            anInt1142++;

            if (anInt1142 > 67) {
                anInt1142 = 0;
            }
        }

        if (crossType == 2) {
            crosses[4 + crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
        }
        if (MBOX) {
            drawInterface(0, 0, RSInterface.interfaceCache[39670], 0);
        }

        if (GameFrame.getScreenMode() != ScreenMode.FIXED && (walkableInterfaceId == 21119 || walkableInterfaceId == 21100)) {
            processInterfaceAnimation(anInt945, walkableInterfaceId);
            drawInterface(0, 0,
                    RSInterface.interfaceCache[getWalkableInterfaceId()], 0);
        } else  if (getWalkableInterfaceId() > 0) {
            processInterfaceAnimation(anInt945, getWalkableInterfaceId());

            if (walkableInterfaceId == 16210 || walkableInterfaceId == 21005) {
                int interfaceX = (int) (clientWidth / 1.6) - (clientWidth <= 396 ? 600
                        : clientWidth <= 735 ? 500 : clientWidth < 944 ? 400 : clientWidth <= 998 ? 350 : 200);
                int  interfaceY = clientHeight / 25;
                drawInterface(0, interfaceX,
                        RSInterface.interfaceCache[getWalkableInterfaceId()], interfaceY);
            } else if (walkableInterfaceId == 21100) {
               int  interfaceX = clientWidth - clientWidth - 330 - 1;
               int  interfaceY = clientHeight - clientHeight + 30;
                drawInterface(0, interfaceX,
                        RSInterface.interfaceCache[getWalkableInterfaceId()], interfaceY);
            }else if (getWalkableInterfaceId() == 15892 && GameFrame.getScreenMode() != ScreenMode.FIXED) {
                drawInterface(0, getScreenWidth() / 2 - RSInterface.interfaceCache[getWalkableInterfaceId()].width + 20,
                        RSInterface.interfaceCache[getWalkableInterfaceId()], 0);
            } else if ((getWalkableInterfaceId() == 201 || getWalkableInterfaceId() == 197)
                    && GameFrame.getScreenMode() != ScreenMode.FIXED) {
                drawInterface(0, getScreenWidth() - 765 + 15, RSInterface.interfaceCache[getWalkableInterfaceId()],
                        -255 + 10 + 4);
            } else if (getWalkableInterfaceId() == 197) {
                drawInterface(0, getScreenWidth() - 765 - (GameFrame.getScreenMode() != ScreenMode.FIXED ? 30 : 0),
                        RSInterface.interfaceCache[42020], 10);
            } else {
                drawInterface(0, 0, RSInterface.interfaceCache[getWalkableInterfaceId()], 0);
            }

        }

        drawParallelWidgets();
        if (openInterfaceID != -1) {
            processInterfaceAnimation(anInt945, openInterfaceID);
            RSInterface rsInterface = RSInterface.interfaceCache[openInterfaceID];
            int width = GameFrame.getScreenMode() != ScreenMode.FIXED ? getScreenWidth() : 516;
            int height = GameFrame.getScreenMode() != ScreenMode.FIXED ? getScreenHeight() : 338;

            if (GameFrame.getScreenMode() != ScreenMode.FIXED) {
                drawInterface(0, (width - 765) / 2, rsInterface, (height - 503) / 2);
            } else {
                drawInterface(0, 0, rsInterface, 0);// first 1
            }

            if (openInterfaceID == 5292) {
                if (bankItemDragSprite != null) {
                    bankItemDragSprite.drawSprite(bankItemDragSpriteX, bankItemDragSpriteY);
                }
            }
        }
        if (openInterfaceID == 5292) {
            drawOnBankInterface();
        }

        // method70();

        childHovered = null;




        if (!menuOpen) {
            processRightClick();
            drawTooltip();
        } else if (menuScreenArea == 0) {
            drawMenu();
        }

        if (drawMultiwayIcon == 1) {
            multiOverlay.drawSprite(GameFrame.getScreenMode() == ScreenMode.FIXED ? 472 : getScreenWidth() - 40,
                    GameFrame.getScreenMode() == ScreenMode.FIXED ? 296 : 175);
        }
        if (drawXPwayIcon == 1) {
            XPOverlay.drawSprite(GameFrame.getScreenMode() == ScreenMode.FIXED ? 472 : getScreenWidth() - 40,
                    GameFrame.getScreenMode() == ScreenMode.FIXED ? 296 : 175);
        }

        if (Objects.nonNull(currentTarget)) {
            showCombatBox();
        }

        int x = regionBaseX + (myPlayer.x - 6 >> 7);
        int y = regionBaseY + (myPlayer.y - 6 >> 7);
        if (debug) {
            int minus = 45;
            normalText.method385(0xffff00, "Fps: " + super.fps, 285 - minus, 5);
            Runtime runtime = Runtime.getRuntime();
            int textColor = 0xFFFF00;
            int memory = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
            if (memory > 0x2000000 && lowDetail) {
                textColor = 0xff0000;
            }

            normalText.method385(textColor, "Mem: " + memory + "k", 299 - minus, 5);
            normalText.method385(0xffff00, "Mouse X: " + super.mouseX + " , Mouse Y: " + super.mouseY, 314 - minus, 5);
            normalText.method385(0xffff00, "Coords: " + x + ", " + y, 329 - minus, 5);
            normalText.method385(0xffff00, "Client resolution: " + getScreenWidth() + "x" + getScreenHeight(),
                    344 - minus, 5);
            normalText.method385(0xffff00, "Object Maps: " + objectMaps + ";", 359 - minus, 5);
            normalText.method385(0xffff00, "Floor Maps: " + floorMaps + ";", 374 - minus, 5);
        }
        if (fpsOn) {
            int textX = mapArea.getxPos() - 90;
            int textY = 20;
            int textColor = 0xffff00;
            if (super.fps < 15) {
                textColor = 0xff0000;
            }
            normalText.method385(textColor, "Fps:" + super.fps, textY, textX);
            textY += 15;
            Runtime runtime = Runtime.getRuntime();
            int memory = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
            if (memory > 0x2000000 && lowDetail) {
                textColor = 0xff0000;
            }
            normalText.method385(textColor, "Mem:" + memory + "k", textY, textX);
            textY += 15;
            normalText.method385(textColor, "MouseX:" + super.mouseX + "", textY, textX);
            textY += 15;
            normalText.method385(textColor, "MouseY:" + super.mouseY + "", textY, textX);
            textY += 15;
            normalText.method385(0xffff00, "Object Maps: " + objectMaps + ";", textY, 5);
            textY += 15;
            normalText.method385(0xffff00, "Floor Maps: " + floorMaps + ";", textY, 5);
        }

        if (systemUpdateTimer != 0) {
            int j = systemUpdateTimer / 50;
            int l = j / 60;
            j %= 60;

            if (j < 10) {
                normalText.method385(0xffff00, "System update in: " + l + ":0" + j,
                        GameFrame.isFixed() ? 329 : getScreenHeight() - 168, 4);
            } else {
                normalText.method385(0xffff00, "System update in: " + l + ":" + j,
                        GameFrame.isFixed() ? 329 : getScreenHeight() - 168, 4);
            }

            if (++anInt849 > 75) {
                anInt849 = 0;
                getOut().putOpcode(148);
            }
        } else {
            if (broadcastMinutes == 0)
                broadcastMessage = null;

            if (broadcastMessage != null) {
                normalText.method385(0xffff00, broadcastMessage, GameFrame.isFixed() ? 329 : getScreenHeight() - 168, 4);
            }

        }
    }

    private void drawAnimatedWorldBackground(boolean display) {
        if (display) {
            int centerX = getScreenWidth() / 2;
            int centerY = getScreenHeight() / 2;

            if (getScriptManager() == null) {
                loginScreenBG(true);
            }

            int canvasCenterX = Rasterizer.centerX;
            int canvasCenterY = Rasterizer.centerY;
            int canvasPixels[] = Rasterizer.lineOffsets;

            if (titleScreenOffsets != null && (titleWidth != getScreenWidth() || titleHeight != getScreenHeight())) {
                titleScreenOffsets = null;
            }

            if (titleScreenOffsets == null) {
                titleWidth = getScreenWidth();
                titleHeight = getScreenHeight();
                titleScreenOffsets = Rasterizer.getOffsets(titleWidth, titleHeight);
            }

            Rasterizer.centerX = centerX;
            Rasterizer.centerY = centerY;
            Rasterizer.lineOffsets = titleScreenOffsets;

            if (loadingStage == 2 && ObjectManager.anInt131 != plane) {
                loadingStage = 1;
            }

            if (!loggedIn && loadingStage == 1) {
                getMapLoadingState();
            }

            if (!loggedIn && loadingStage == 2 && plane != getLastKnownPlane()) {
                setLastKnownPlane(plane);
                renderedMapScene(plane);
            }

            if (loadingStage == 2) {
                try {
                    scene.method313(xCameraPos, yCameraPos, xCameraCurve, zCameraPos, method121(), yCameraCurve);
                    scene.clearObj5Cache();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (getScriptManager() != null && loadingStage == 2 && plane == getLastKnownPlane() && !loggedIn) {
                getScriptManager().cycle();
            }

            Rasterizer.centerX = canvasCenterX;
            Rasterizer.centerY = canvasCenterY;
            Rasterizer.lineOffsets = canvasPixels;
        }
    }

    private void drawBlackBox(int x, int y) {
        DrawingArea.drawPixels(71, y - 1, x - 2, 0x726451, 1);
        DrawingArea.drawPixels(69, y, x + 174, 0x726451, 1);
        DrawingArea.drawPixels(1, y - 2, x - 2, 0x726451, 178);
        DrawingArea.drawPixels(1, y + 68, x, 0x726451, 174);
        DrawingArea.drawPixels(71, y - 1, x - 1, 0x2E2B23, 1);
        DrawingArea.drawPixels(71, y - 1, x + 175, 0x2E2B23, 1);
        DrawingArea.drawPixels(1, y - 1, x, 0x2E2B23, 175);
        DrawingArea.drawPixels(1, y + 69, x, 0x2E2B23, 175);
        DrawingArea.method335(0, y, 174, 68, 220, x);
    }

    private void drawChatArea() {
        chatArea.setxPos(0);
        chatArea.setyPos(GameFrame.getScreenMode() == ScreenMode.FIXED ? 338 : getScreenHeight() - 165);
        chatArea.render(this);
    }

    private void drawFlames() {
        drawingFlames = true;

        try {
            long l = System.currentTimeMillis();
            int i = 0;
            int j = 20;

            while (aBoolean831) {
                doFlamesDrawing();

                if (++i > 10) {
                    long l1 = System.currentTimeMillis();
                    int k = (int) (l1 - l) / 10 - j;
                    j = 40 - k;

                    if (j < 5) {
                        j = 5;
                    }

                    i = 0;
                    l = l1;
                }

                try {
                    Thread.sleep(j);
                } catch (Exception _ex) {
                }
            }
        } catch (Exception _ex) {
        }

        drawingFlames = false;
    }

    private void drawFriendsListOrWelcomeScreen(RSInterface class9) {
        int j = class9.contentType;

        if (j >= 205 && j <= 231) {
            j -= 205;
            class9.message = setMessage(j);
            return;
        }

        if (j >= 1 && j <= 100 || j >= 701 && j <= 800) {
            if (j == 1 && getAnInt900() == 0) {
                class9.message = "Loading friend list";
                class9.atActionType = 0;
                return;
            }

            if (j == 1 && getAnInt900() == 1) {
                class9.message = "Connecting to friendserver";
                class9.atActionType = 0;
                return;
            }

            if (j == 2 && getAnInt900() != 2) {
                class9.message = "Please wait...";
                class9.atActionType = 0;
                return;
            }

            int k = friendCount;

            if (getAnInt900() != 2) {
                k = 0;
            }

            if (j > 700) {
                j -= 601;
            } else {
                j--;
            }

            if (j >= k) {
                class9.message = "";
                class9.atActionType = 0;
                return;
            } else {
                class9.message = friendsList[j];
                class9.atActionType = 1;
                return;
            }
        }
        if (j >= 10000 && j <= 10400) {
            if (j == 1 && getAnInt900() == 0) {
                class9.message = "Loading friend list";
                class9.atActionType = 0;
                return;
            }

            if (j == 1 && getAnInt900() == 1) {
                class9.message = "Connecting to friendserver";
                class9.atActionType = 0;
                return;
            }

            if (j == 2 && getAnInt900() != 2) {
                class9.message = "Please wait...";
                class9.atActionType = 0;
                return;
            }

            int k = friendCount;

            if (getAnInt900() != 2) {
                k = 0;
            }

                j-= 10000;

            if (j >= k) {
                class9.message = "";
                class9.atActionType = 0;
                return;
            } else {
                class9.message = friendsList[j];
                class9.atActionType = 1;
                return;
            }
        }
        if (j >= 10401 && j <= 10800) {
            int l = friendCount;

            if (getAnInt900() != 2) {
                l = 0;
            }

                j -= 10401;

            if (j >= l) {
                class9.message = "";
                class9.atActionType = 0;
                return;
            }

            if (friendsNodeIDs[j] == 0) {
                class9.message = "@red@Offline";
            } else if (friendsNodeIDs[j] == nodeID) {
                class9.message = "@gre@Online"/* + (friendsNodeIDs[j] - 9) */;
            } else {
                class9.message = "@red@Offline"/* + (friendsNodeIDs[j] - 9) */;
            }

            class9.atActionType = 1;
            return;
        }

        if (j == 901) {
            class9.message = friendCount + "";
            return;
        }

        if (j == 902) {
            class9.message = ignoreCount + "";
            return;
        }

        if (j >= 101 && j <= 200 || j >= 801 && j <= 900) {
            int l = friendCount;

            if (getAnInt900() != 2) {
                l = 0;
            }

            if (j > 800) {
                j -= 701;
            } else {
                j -= 101;
            }

            if (j >= l) {
                class9.message = "";
                class9.atActionType = 0;
                return;
            }

            if (friendsNodeIDs[j] == 0) {
                class9.message = "@red@Offline";
            } else if (friendsNodeIDs[j] == nodeID) {
                class9.message = "@gre@Online"/* + (friendsNodeIDs[j] - 9) */;
            } else {
                class9.message = "@red@Offline"/* + (friendsNodeIDs[j] - 9) */;
            }

            class9.atActionType = 1;
            return;
        }

        if (j == 203) {
            int i1 = friendCount;

            if (getAnInt900() != 2) {
                i1 = 0;
            }

            class9.scrollMax = i1 * 15 + 20;

            if (class9.scrollMax <= class9.height) {
                class9.scrollMax = class9.height + 1;
            }

            return;
        }

        if (j >= 401 && j <= 500) {
            if ((j -= 401) == 0 && getAnInt900() == 0) {
                class9.message = "Loading ignore list";
                class9.atActionType = 0;
                return;
            }

            if (j == 1 && getAnInt900() == 0) {
                class9.message = "Please wait...";
                class9.atActionType = 0;
                return;
            }

            int j1 = ignoreCount;

            if (getAnInt900() == 0) {
                j1 = 0;
            }

            if (j >= j1) {
                class9.message = "";
                class9.atActionType = 0;
                return;
            } else {
                class9.message = TextClass.fixName(TextClass.nameForLong(ignoreListAsLongs[j]));
                class9.atActionType = 1;
                return;
            }
        }

        if (j == 503) {
            class9.scrollMax = ignoreCount * 15 + 20;

            if (class9.scrollMax <= class9.height) {
                class9.scrollMax = class9.height + 1;
            }

            return;
        }

        if (j == 327) {
            class9.modelRotationY = 150;
            class9.modelRotationX = (int) (Math.sin(loopCycle / 40D) * 256D) & 0x7FF;

            if (aBoolean1031) {
                for (int k1 = 0; k1 < 7; k1++) {
                    int l1 = myAppearance[k1];

                    if (l1 >= 0 && !IdentityKit.cache[l1].method537()) {
                        return;
                    }
                }

                aBoolean1031 = false;
                Model aclass30_sub2_sub4_sub6s[] = new Model[7];
                int i2 = 0;

                for (int j2 = 0; j2 < 7; j2++) {
                    int k2 = myAppearance[j2];

                    if (k2 >= 0) {
                        aclass30_sub2_sub4_sub6s[i2++] = IdentityKit.cache[k2].method538();
                    }
                }

                Model model = new Model(i2, aclass30_sub2_sub4_sub6s);

                for (int l2 = 0; l2 < 5; l2++) {
                    if (anIntArray990[l2] != 0) {
                        model.method476(anIntArrayArray1003[l2][0], anIntArrayArray1003[l2][anIntArray990[l2]]);

                        if (l2 == 1) {
                            model.method476(anIntArray1204[0], anIntArray1204[anIntArray990[l2]]);
                        }
                    }
                }

                model.createBones();
                model.applyTransform(Animation.cache[myPlayer.anInt1511].frameIDs[0]);
                model.light(64, 850, -30, -50, -30, true);
                class9.mediaType = 5;
                class9.mediaID = 0;
                RSInterface.clearModelCache(aBoolean994, model);
            }

            return;
        }

        if (j == 3292) {
            if (!(PetSystem.petSelected > 0)) {
                return;
            }
            PetSystem petDef = new PetSystem(MobDefinition.get(PetSystem.petSelected));
            RSInterface rsInterface = class9;
            int verticleTilt = 150;
            rsInterface.modelRotationY = verticleTilt;
            rsInterface.modelRotationX = (int) (double) (loopCycle / 100D * 1024D) & 2047;
            Model model;
            final Model[] parts = new Model[petDef.getModelArrayLength()];
            for (int i = 0; i < petDef.getModelArrayLength(); i++) {
                parts[i] = Model.fetchModel(petDef.getModelArray()[i]);
            }
            if (parts.length == 1) {
                model = parts[0];
            } else {
                model = new Model(parts.length, parts);
            }

            if (model == null) {
                return;
            }

            model.createBones();
            model.scale2((int) 1.5);
            model.applyTransform(Animation.cache[petDef.getAnimation()].frameIDs[PetSystem.animationFrame]);
            model.light(64, 850, -30, -50, -30, true);
            rsInterface.mediaType = 5;
            rsInterface.mediaID = 0;
            RSInterface.clearModelCache(aBoolean994, model);
            return;
        }
        if (j == 1430 && class9.scrollMax > 5) {
            if (class9.pauseTicks > 0) {
                class9.pauseTicks--;
                return;
            }
            RSInterface parent = RSInterface.interfaceCache[class9.parentID];
            if (class9.scrollPosition == -class9.scrollMax) {
                class9.endReached = true;
                class9.pauseTicks = 20;
            }
            if (class9.endReached) {
                if (class9.scrollPosition == 0) {
                    class9.endReached = false;
                    class9.pauseTicks = 20;
                }
                class9.scrollPosition++;
            } else {
                class9.scrollPosition--;
            }
            parent.childX[0] = class9.scrollPosition;
        }
        if (j == 3500) {
            class9.modelRotationY = ItemDefinition.get(class9.mediaID).rotationY;
            class9.modelRotationX = ItemDefinition.get(class9.mediaID).rotationX;
            class9.modelZoom = ItemDefinition.get(class9.mediaID).modelZoom / 2;
            class9.xOffset = 0;
            class9.yOffset = 0;
        }
        if (j == 3501) {
            class9.modelRotationY = ItemDefinition.get(class9.mediaID).rotationY;
            class9.modelRotationX = ItemDefinition.get(class9.mediaID).rotationX;
            class9.modelZoom = (int) (ItemDefinition.get(class9.mediaID).modelZoom / 2.5D);
            class9.xOffset = 35;
            class9.yOffset = 35;
        }
        if (j == 3600) {
            RSInterface rsInterface = class9;
            final MobDefinition npc = MobDefinition.get(class9.contentId);
            InterfaceNPC petDef = new InterfaceNPC(npc);
            Model model;
            final Model[] parts = new Model[petDef.getModelArrayLength()];
            for (int i = 0; i < petDef.getModelArrayLength(); i++) {
                parts[i] = Model.fetchModel(petDef.getModelArray()[i]);
            }
            if (parts.length == 1) {
                model = parts[0];
            } else {
                model = new Model(parts.length, parts);
            }
            if (model == null) {
                return;
            }
            if (npcDisplay != null) {
                if (npcDisplay.colorChange != null)
                    Objects.requireNonNull(model).tint(npcDisplay.colorChange);
                if (npcDisplay.originalModelColours != null) {
                    for (int k1 = 0; k1 < npcDisplay.originalModelColours.length; k1++) {
                        model.method476(npcDisplay.originalModelColours[k1], npcDisplay.changedModelColours[k1]);
                    }
                }
            }
            model.createBones();
            model.applyTransform(Animation.cache[petDef.getAnimation()].frameIDs[InterfaceNPC.animationFrame]);
            rsInterface.mediaType = 5;
            rsInterface.mediaID = 0;
            RSInterface.clearModelCache(aBoolean994, model);
            if (!InterfaceNPC.isPetAnimationRunning) {
                InterfaceNPC.updateAnimations();
            }
            return;
        }

        if (j == 3291) {
            RSInterface rsInterface = class9;
            // int npcDisplay = MobDefinition.get(npcDisplay);
            final MobDefinition npc = MobDefinition.get(class9.contentId);
            InterfaceNPC petDef = new InterfaceNPC(npc);
            int verticleTilt = 150;
            rsInterface.modelRotationY = verticleTilt;
            rsInterface.modelRotationX = (int) (double) (loopCycle / 100D * 1024D) & 2047;
            Model model;
            final Model[] parts = new Model[petDef.getModelArrayLength()];
            for (int i = 0; i < petDef.getModelArrayLength(); i++) {
                parts[i] = Model.fetchModel(petDef.getModelArray()[i]);
            }
            if (parts.length == 1) {
                model = parts[0];
            } else {
                model = new Model(parts.length, parts);
            }

            if (model == null) {
                return;
            }
            double size = 1.5;
//NPC MODEL SIZE - SNZ ARLO

            switch (npc.id) {

                case 1265:
                case 606:
                case 1614:

                    size = 0.2;
                    break;
                case 8018:
                    size = 0.05;
                    break;
                case 1023:
                case 1233:
                case 1234:
                case 13747:
                case 603:
                case 201:
                case 202:
                case 203:
                case 252:
                case 449:
                case 185:
                case 452:
                    size = 0.6;
                    break;
                case 12343:
                    size = 0.6;
                    break;
                case 2005:
                case 7869:
                case 5882:
                case 3117:

                    size = 2.0;
                    break;
                case 4540:
                case 8549:
                case 2060:
                case 3200:
                case 4556:
                case 50:
                case 2054:
                case 2882:
                case 2881:
                case 2883:
                case 2009:
                case 2006:
                case 2000:

                    size = 1.7;
                    break;
                case 2949:
                case 2950:
                    // case 3832:
                    size = 2.7;
                    break;
                case 8349:
                case 6247:
                    size = 1.7;
                    break;
                case 8000:
                case 6692:
                    size = 1.0;
                    break;

                default:
                    size = 1.5;
                    break;
            }

            if (npc.colorChange != null)
                Objects.requireNonNull(model).tint(npc.colorChange);

            if (npc.originalModelColours != null) {
                for (int k1 = 0; k1 < npc.originalModelColours.length; k1++) {
                    model.method476(npc.originalModelColours[k1], npc.changedModelColours[k1]);
                }
            }

            model.createBones();
            model.scale2((int) size);
            model.applyTransform(Animation.cache[petDef.getAnimation()].frameIDs[InterfaceNPC.animationFrame]);
            rsInterface.mediaType = 5;
            rsInterface.mediaID = 0;
            RSInterface.clearModelCache(aBoolean994, model);
            if (!InterfaceNPC.isPetAnimationRunning) {
                InterfaceNPC.updateAnimations();
            }
            return;
        }
        if (j == 32921) {
            RSInterface rsInterface = class9;
            npcDisplay = MobDefinition.get(rsInterface.npcDisplay);

            if (rsInterface.npcDisplay != -1 && rsInterface.npcDisplay != 5090) {
                InterfaceNPC petDef = new InterfaceNPC(npcDisplay);
                int verticleTilt = 150;
                rsInterface.modelRotationY = verticleTilt;
                rsInterface.modelRotationX = (int) (double) (loopCycle / 100D * 1024D) & 2047;
                Model model = InterfaceNPC.modelCache.get((long)npcDisplay.id);

                if (model == null) {
                    final Model[] parts = new Model[petDef.getModelArrayLength()];

                    boolean flag = false;

                    for (int i1 = 0; i1 < petDef.getModelArray().length; i1++) {
                        if (!Model.method463(petDef.getModelArray()[i1], npcDisplay.rs3, npcDisplay.osrs)) {
                            flag = true;
                        }
                    }

                    if (flag) {
                        rsInterface.mediaType = 5;
                        rsInterface.mediaID = 0;
                        RSInterface.clearModelCache(aBoolean994, null);
                        return;
                    }

                    for (int i = 0; i < petDef.getModelArrayLength(); i++) {
                        parts[i] = Model.fetchModel(petDef.getModelArray()[i], npcDisplay.rs3, npcDisplay.osrs);
                    }
                    if (parts.length == 1) {
                        model = parts[0];
                    } else {
                        //for (int i = 0 ; i < parts.length ; i ++)
                        //if (parts[i] == null)
                               // return;
                        model = new Model(parts.length, parts);
                    }
                    if (model == null) {
                        rsInterface.mediaType = 5;
                        rsInterface.mediaID = 0;
                        RSInterface.clearModelCache(aBoolean994, null);
                        return;
                    }
                    if (npcDisplay.originalModelColours != null) {
                        for (int k1 = 0; k1 < npcDisplay.originalModelColours.length; k1++)
                            model.recolour(npcDisplay.originalModelColours[k1], npcDisplay.changedModelColours[k1]);
                    }

                    if (npcDisplay.colorChange != null)
                        Objects.requireNonNull(model).tint(npcDisplay.colorChange);

                    if (npcDisplay.originalModelColours != null) {
                        for (int k1 = 0; k1 < npcDisplay.originalModelColours.length; k1++) {
                            model.method476(npcDisplay.originalModelColours[k1], npcDisplay.changedModelColours[k1]);
                        }
                    }
                    if (npcDisplay.rdc > 0)
                        model.method1337(npcDisplay.rdc);
                    if (npcDisplay.rdc2 != 0)
                        model.method1338(npcDisplay.rdc2);
                    if (npcDisplay.rdc3 != 0)
                        model.method1339(npcDisplay.rdc3);


                    if (npcDisplay.rs3)
                        model.createBonesRS3();
                    else
                        model.createBones();

                    InterfaceNPC.modelCache.put((long) npcDisplay.id, model);
                }

                Model model1 = Model.aModel_1621;
                model1.copy(model, false);

                model1.applyTransform(Animation.cache[petDef.getAnimation()].frameIDs[InterfaceNPC.animationFrame], npcDisplay.osrs);

                rsInterface.mediaType = 5;
                rsInterface.mediaID = 0;
                RSInterface.clearModelCache(aBoolean994, model1);
                if (!InterfaceNPC.isPetAnimationRunning) {
                    InterfaceNPC.updateAnimations();
                }
                return;
            } else {
                rsInterface.mediaType = 5;
                rsInterface.mediaID = 0;
                RSInterface.clearModelCache(aBoolean994, null);

            }
        }

        if (j == 328) {
            RSInterface rsInterface = class9;
            int verticleTilt = 150;
            int animationSpeed = (int) (Math.sin(loopCycle / 40D) * 256D) & 0x7ff;
            rsInterface.modelRotationY = verticleTilt;
            rsInterface.modelRotationX = animationSpeed;

            if (aBoolean1031) {
                Model characterDisplay = myPlayer.getRotatedModel();

                for (int l2 = 0; l2 < 5; l2++) {
                    if (anIntArray990[l2] != 0) {
                        characterDisplay.method476(anIntArrayArray1003[l2][0],
                                anIntArrayArray1003[l2][anIntArray990[l2]]);
                        if (l2 == 1) {
                            characterDisplay.method476(anIntArray1204[0], anIntArray1204[anIntArray990[l2]]);
                        }
                    }
                }

                int staticFrame = myPlayer.anInt1511;
                //characterDisplay.createBones();
               // characterDisplay.applyTransform(Animation.cache[staticFrame].frameIDs[0]);
                rsInterface.mediaType = 5;
                rsInterface.mediaID = 0;
                RSInterface.clearModelCache(aBoolean994, characterDisplay);
            }

            return;
        }

        if (j == 324) {
            if (aClass30_Sub2_Sub1_Sub1_931 == null) {
                aClass30_Sub2_Sub1_Sub1_931 = class9.disabledSprite;
                aClass30_Sub2_Sub1_Sub1_932 = class9.enabledSprite;
            }

            if (isMale) {
                class9.disabledSprite = aClass30_Sub2_Sub1_Sub1_932;
                return;
            } else {
                class9.disabledSprite = aClass30_Sub2_Sub1_Sub1_931;
                return;
            }
        }

        if (j == 325) {
            if (aClass30_Sub2_Sub1_Sub1_931 == null) {
                aClass30_Sub2_Sub1_Sub1_931 = class9.disabledSprite;
                aClass30_Sub2_Sub1_Sub1_932 = class9.enabledSprite;
            }

            if (isMale) {
                class9.disabledSprite = aClass30_Sub2_Sub1_Sub1_931;
                return;
            } else {
                class9.disabledSprite = aClass30_Sub2_Sub1_Sub1_932;
                return;
            }
        }

        if (j == 650 || j == 655) {
            if (anInt1193 != 0) {
                String lastVisit;

                if (daysSinceLastLogin == 0) {
                    lastVisit = "earlier today";
                } else if (daysSinceLastLogin == 1) {
                    lastVisit = "yesterday";
                } else {
                    lastVisit = daysSinceLastLogin + " days ago";
                }

                class9.message = "You last logged in " + lastVisit + " from: " + Signlink.dns;
            } else {
                class9.message = "";
            }
        }

        if (j == 651) {
            if (unreadMessages == 0) {
                class9.message = "0 unread messages";
                class9.disabledColor = 0xffff00;
            } else if (unreadMessages == 1) {
                class9.message = "1 unread message";
                class9.disabledColor = 65280;
            } else if (unreadMessages > 1) {
                class9.message = unreadMessages + " unread messages";
                class9.disabledColor = 65280;
            }
        }

        if (j == 652) {
            if (daysSinceRecovChange == 201) {
                if (membersInt == 1) {
                    class9.message = "@yel@This is a non-members world: @whi@Since you are a member we";
                } else {
                    class9.message = "";
                }
            } else if (daysSinceRecovChange == 200) {
                class9.message = "You have not yet set any password recovery questions.";
            } else {
                String s1;

                if (daysSinceRecovChange == 0) {
                    s1 = "Earlier today";
                } else if (daysSinceRecovChange == 1) {
                    s1 = "Yesterday";
                } else {
                    s1 = daysSinceRecovChange + " days ago";
                }

                class9.message = s1 + " you changed your recovery questions";
            }
        }

        if (j == 653) {
            if (daysSinceRecovChange == 201) {
                if (membersInt == 1) {
                    class9.message = "@whi@recommend you use a members world instead. You may use";
                } else {
                    class9.message = "";
                }
            } else if (daysSinceRecovChange == 200) {
                class9.message = "We strongly recommend you do so now to secure your account.";
            } else {
                class9.message = "If you do not remember making this change then cancel it immediately";
            }
        }

        if (j == 654) {
            if (daysSinceRecovChange == 201) {
                if (membersInt == 1) {
                    class9.message = "@whi@this world but member benefits are unavailable whilst here.";
                    return;
                } else {
                    class9.message = "";
                    return;
                }
            }

            if (daysSinceRecovChange == 200) {
                class9.message = "Do this from the 'account management' area on our front webpage";
                return;
            }

            class9.message = "Do this from the 'account management' area on our front webpage";
        }
    }

    private void drawGameScreen() {
        if (getFullscreenInterfaceID() != -1 && (loadingStage == 2 || super.fullGameScreen != null)) {
            if (loadingStage == 2) {
                processInterfaceAnimation(anInt945, getFullscreenInterfaceID());
                if (openInterfaceID != -1) {
                    processInterfaceAnimation(anInt945, openInterfaceID);
                }
                anInt945 = 0;
                resetAllImageProducers();
                super.fullGameScreen.initDrawingArea();
                Rasterizer.lineOffsets = fullScreenTextureArray;
                DrawingArea.setAllPixelsToZero();
                welcomeScreenRaised = true;
                if (openInterfaceID != -1) {
                    RSInterface rsInterface_1 = RSInterface.interfaceCache[openInterfaceID];
                    if (rsInterface_1.width == 512 && rsInterface_1.height == 334 && rsInterface_1.type == 0) {
                        rsInterface_1.width = 765;
                        rsInterface_1.height = 503;
                    }
                    drawInterface(0, 0, rsInterface_1, 8);
                }
                RSInterface rsInterface = RSInterface.interfaceCache[getFullscreenInterfaceID()];
                if (rsInterface.width == 512 && rsInterface.height == 334 && rsInterface.type == 0) {
                    rsInterface.width = 765;
                    rsInterface.height = 503;
                }
                drawInterface(0, 0, rsInterface, 8);

                if (!menuOpen) {
                    processRightClick();
                    drawTooltip();
                } else {
                    drawMenu();
                }
            }
            drawCount++;
            super.fullGameScreen.drawGraphics(canvas.getGraphics(), 0, 0);
            return;
        } else {
            if (drawCount != 0) {
                resetImageProducers2();
            }
        }
        if (welcomeScreenRaised) {
            welcomeScreenRaised = false;
            if (GameFrame.getScreenMode() == ScreenMode.FIXED) {
                topFrame.drawGraphics(canvas.getGraphics(), 0, 0);
                leftFrame.drawGraphics(canvas.getGraphics(), 0, 4);

                rightFrame.drawGraphics(canvas.getGraphics(), 516, 4);
                rightFrame.drawGraphics(canvas.getGraphics(), 515, 4);
            }

            setInputTaken(true);
            tabAreaAltered = true;

            if (loadingStage != 2) {
                if (!resizing) {
                    gameScreenIP.drawGraphics(canvas.getGraphics(), gameScreenDrawX, gameScreenDrawY);
                }
                mapAreaIP.drawGraphics(canvas.getGraphics(), mapArea.getxPos(), mapArea.getyPos());
            }
        }

        drawTabArea();

        if (backDialogID == -1 && inputDialogState == 3) {
            int position = getGrandExchange().totalItemResults * 14 + 7;
            aClass9_1059.scrollPosition = getGrandExchange().itemResultScrollPos;
            if (super.mouseX > 478 && super.mouseX < 580 && super.mouseY > (clientHeight - 161)) {
                scrollInterface(494, 110, super.mouseX - 0, super.mouseY - (clientHeight - 155), aClass9_1059, 0, false,
                        getGrandExchange().totalItemResults);
            }
            int scrollPosition = aClass9_1059.scrollPosition;
            if (scrollPosition < 0) {
                scrollPosition = 0;
            }
            if (scrollPosition > position - 110) {
                scrollPosition = position - 110;
            }
            if (getGrandExchange().itemResultScrollPos != scrollPosition) {
                getGrandExchange().itemResultScrollPos = scrollPosition;
                inputTaken = true;
            }
        }
        if (backDialogID == -1 && inputDialogState != 3) {
            aClass9_1059.scrollPosition = anInt1211 - anInt1089 - 110;
            if (super.mouseX > chatArea.getxPos() + 478 && super.mouseX < chatArea.getxPos() + 580
                    && super.mouseY > chatArea.getyPos() + 4) {
                scrollInterface(494, 110, super.mouseX - chatArea.getxPos(), super.mouseY - chatArea.getyPos() - 10,
                        aClass9_1059, 0, false, anInt1211);
            }
            int i = anInt1211 - 110 - aClass9_1059.scrollPosition;
            if (i < 0) {
                i = 0;
            }
            if (i > anInt1211 - 110) {
                i = anInt1211 - 110;
            }
            if (anInt1089 != i) {
                anInt1089 = i;
                setInputTaken(true);
            }
        }

        if (backDialogID != -1) {
            boolean flag2 = processInterfaceAnimation(anInt945, backDialogID);
            if (flag2) {
                setInputTaken(true);
            }
        }

        if (atInventoryInterfaceType == 3) {
            setInputTaken(true);
        }

        if (activeInterfaceType == 3) {
            setInputTaken(true);
        }

        if (aString844 != null) {
            setInputTaken(true);
        }

        if (menuOpen && menuScreenArea == 2) {
            setInputTaken(true);
        }

        if (inputTaken) {
            drawChatArea();
            drawConsoleArea();
            setInputTaken(false);
        }

        if (loadingStage == 2) {
            method146();
        }

        if (loadingStage == 2) {
            if (GameFrame.getScreenMode() == ScreenMode.FIXED) {
                drawMinimap();
                if (mapArea.isVisible()) {
                    mapAreaIP.drawGraphics(canvas.getGraphics(), mapArea.getxPos(), mapArea.getyPos());
                }
            }
        }

        if (anInt1054 != -1) {
            tabAreaAltered = true;
        }

        if (tabAreaAltered) {
            if (anInt1054 != -1 && anInt1054 == tabID) {
                anInt1054 = -1;
                getOut().putOpcode(120);
                getOut().putByte(tabID);
            }

            tabAreaAltered = false;
            aRSImageProducer_1125.initDrawingArea();
            gameScreenIP.initDrawingArea();
        }

        if (menuOpen) {
            drawMenu();
        }

        anInt945 = 0;
    }

    private void drawHeadIcon() {
        if (anInt855 != 2) {
            return;
        }

        calcEntityScreenPos((anInt934 - regionBaseX << 7) + anInt937, anInt936 * 2,
                (anInt935 - regionBaseY << 7) + anInt938);

        if (spriteDrawX > -1 && loopCycle % 20 < 10) {
            headIconsHint[0].drawSprite(spriteDrawX - 12, spriteDrawY - 28);
        }
    }

    private void drawModIcon(Player player) {

        if (!(player.name.equalsIgnoreCase("testicon"))) { // dirty hack. if player's name isn't testicon, then return.
            // System.out.println("what the fuck is this");
            return;
        }

        int intx = 200;
        int inty = 200;
        int sprite = 829;
        Client.cacheSprite[sprite].drawSprite(intx, inty); // (spriteDrawX - 12, spriteDrawY - 28);
        System.out.println("We drew cacheSprite[" + sprite + "] at : (" + intx + ", " + inty + ") (x,y)");
        System.out.println("Our player: " + player.name);
        System.out.println("Sending return packet...");
        return;
        // System.out.println("This should be unreachable");
    }

    private void drawHoverBox(int xPos, int yPos, String text) {
        String[] results = text.split("\n");
        int height = results.length * 16 + 6;
        int width = chatTextDrawingArea.getTextWidth(results[0]) + 6;

        for (int i = 1; i < results.length; i++) {
            if (width <= chatTextDrawingArea.getTextWidth(results[i]) + 6) {
                width = chatTextDrawingArea.getTextWidth(results[i]) + 6;
            }
        }

        DrawingArea.drawPixels(height, yPos, xPos, 0xFFFFA0, width);
        DrawingArea.fillPixels(xPos, width, height, 0, yPos);
        yPos += 14;

        for (String result : results) {
            normalText.drawRegularText(false, xPos + 3, 0, result, yPos);
            yPos += 16;
        }
    }

    private void drawProgressBar(int xPos, int yPos, int width, int height, int currentPercent, int firstColor,
                                 int secondColor, int strokeWidth) {
        DrawingArea.drawAlphaFilledPixels(xPos, yPos, width, height, firstColor, 255);
        DrawingArea.drawAlphaFilledPixels(xPos, yPos, (int) (width * (currentPercent / 100.0f)), height, secondColor,
                255);
        DrawingArea.drawStroke(xPos - strokeWidth, yPos, width + strokeWidth, height, 0x141414, strokeWidth);
    }


    public void drawInterface(int scrollOffset, int interfaceX, RSInterface class9, int interfaceY) {
        if (class9 == null)
            return;

        if (class9.children == null || class9.type != 0 || !class9.isVisible()) {
            return;
        }

        if (class9.interfaceShown && anInt1026 != class9.id && anInt1048 != class9.id && anInt1039 != class9.id) {
            return;
        }

        int i1 = DrawingArea.topX;
        int j1 = DrawingArea.topY;
        int k1 = DrawingArea.bottomX;
        int l1 = DrawingArea.bottomY;
        DrawingArea.setBounds(interfaceX, interfaceY, interfaceX + class9.width, interfaceY + class9.height);
        int i2 = class9.children.length;

        for (int j2 = 0; j2 < i2; j2++) {
            int childX = (class9.childX[j2] + interfaceX) - (class9.sideScroll ? scrollOffset : 0);
            int childY = (class9.childY[j2] + interfaceY) - (class9.sideScroll ? 0 : scrollOffset);
            RSInterface childInterface = RSInterface.interfaceCache[class9.children[j2]];
            if (childInterface == null)
                continue;
            // System.out.println(childInterface.type);
            childX += childInterface.xOffset;
            childY += childInterface.yOffset;

            if (childInterface.contentType > 0) {
                drawFriendsListOrWelcomeScreen(childInterface);
            }

            for (int m5 = 0; m5 < IDs.length; m5++) {
                if (childInterface.id == IDs[m5] + 1) {
                    if (m5 > 61) {
                        drawBlackBox(childX + 1, childY);
                    } else {
                        drawBlackBox(childX, childY + 1);
                    }
                }
            }

            for (int element : runeChildren) {
                if (childInterface.id == element) {
                    childInterface.modelZoom = 775;
                }
            }

            if (childInterface.id == 1194 || childInterface.id == 12856) // Removes black box when not hovering in
                // spellbooks
                continue;
            if (childInterface.type == 282) {
                if (childInterface.itemsToDraw != null) {
                    for (int i = 0; i < childInterface.itemsToDraw.length; i++) {
                        Sprite invSprite = ItemDefinition.getSprite(childInterface.itemsToDraw[i], 0, 0);
                        Sprite cuttedSprite = Sprite.getCutted(invSprite, invSprite.myWidth, cutAmount);
                        cuttedSprite.drawSprite(childX + 30 + i * 50, childY + 3); // dont ask me about the magic
                        // well i didnt even add anything client.java side // numbers
                    }

                    if (cutCounter % 3 == 0) {
                        cutAmount++;
                    }
                    cutCounter++;
                    System.out.println("Cut amount: " + cutAmount);
                    if (cutAmount >= 40) {
                        childInterface.itemsToDraw = null;
                        getOut().putOpcode(199);
                        getOut().putByte(1);
                        cutAmount = 0;// TODO rest.
                    }

                }
            }

            if (childInterface.type == 287) {
                drawProgressBar(childX, childY, childInterface.width, childInterface.height, childInterface.progress, childInterface.firstColor, childInterface.secondColor, 1);
                // System.out.println("Drawing: " + this.hueShift(particleTick).getRGB());
            }
            if (childInterface.type == 150) {
                childInterface.wheel.render(childX, childY);
            }

            if (childInterface.type == 100 && childInterface.unrevealedSprite != null) {

                childInterface.unrevealedSprite.drawSprite(childX + 2, childY + 2);
                // System.out.println("Unrevealed sprite drawn");
            }

            if (childInterface.type == 831) {
                DrawingArea.drawAlphaFilledPixels(childX, childY, childInterface.width, childInterface.height, childInterface.enabledColor,
                        childInterface.customOpacity);
            }
            if (childInterface.type == 0) {
                // System.out.println(childInterface.id);


                if (childInterface.sideScroll) {
                    if (childInterface.scrollPosition > childInterface.scrollMax - childInterface.width) {
                        childInterface.scrollPosition = childInterface.scrollMax - childInterface.width;
                    }
                } else {
                    if (childInterface.scrollPosition > childInterface.scrollMax - childInterface.height) {
                        childInterface.scrollPosition = childInterface.scrollMax - childInterface.height;
                    }
                }

                if (childInterface.scrollPosition < 0) {
                    childInterface.scrollPosition = 0;
                }

                drawInterface(childInterface.scrollPosition, childX, childInterface, childY);

                if (childInterface.sideScroll) {
                    if (childInterface.scrollMax > childInterface.width) {
                        drawScrollbarBottom(childInterface.width, childInterface.scrollPosition, childY + childInterface.height, childX,
                                childInterface.scrollMax, false, false);
                    }
                } else {
                    if (childInterface.scrollMax > childInterface.height) {
                        drawScrollbar(childInterface.height, childInterface.scrollPosition, childY,
                                childX + childInterface.width, childInterface.scrollMax, false, false);
                    }
                }
            } else if (childInterface.type != 1) {
                if (childInterface.type == 1319) {
                    DrawingArea.drawPixels(childX + 335, childY + 52, childX + 51, 0, 411);
                    miniMapRegions.drawSprite(childX, childY);

                    int mapX = childX + 40 + myPlayer.x / 32;
                    int mapY = childY + 458 - myPlayer.y / 32;
                    cacheSprite[1421].drawSprite(mapX - 10, mapY - 10);
                }
                if (childInterface.type == 2) {
                    int i3 = 0;
                    for (int y = 0; y < childInterface.height; y++) {
                        for (int x = 0; x < childInterface.width; x++) {
                            int itemSpriteX = childX + x * (32 + childInterface.invSpritePadX);
                            int itemSpriteY = childY + y * (32 + childInterface.invSpritePadY);



                            if (i3 < 20) {
                                itemSpriteX += childInterface.spritesX[i3];
                                itemSpriteY += childInterface.spritesY[i3];
                            }

                            if (i3 < childInterface.inv.length && childInterface.inv[i3] > 0) {
                                if ((childInterface.id >= 110101 && childInterface.id <= 110221)) { /*
                                 * Shop interface hardcode
                                 */
                                    if (stock == null) {
                                        stock = new Sprite("/ok/stock");
                                    }
                                    stock.drawSprite(itemSpriteX - 8, itemSpriteY - 4);
                                }
                                int k6 = 0;
                                int j7 = 0;
                                int j9 = childInterface.inv[i3] - 1;

                                if (itemSpriteX > DrawingArea.topX - 32 && itemSpriteX < DrawingArea.bottomX && itemSpriteY > DrawingArea.topY - 32
                                        && itemSpriteY < DrawingArea.bottomY || activeInterfaceType != 0 && anInt1085 == i3) {
                                    int l9 = 0;
                                    if (itemSelected == 1 && anInt1283 == i3 && anInt1284 == childInterface.id) {
                                        l9 = 0xffffff;
                                    }

                                    int itemOpacity = 256;

                                    if (openInterfaceID == 5292 || openInterfaceID == 106000 || openInterfaceID == 3824) {
                                        if (childInterface.invStackSizes[i3] == 0) {
                                            itemOpacity = 90;
                                        }
                                    }
                                    if (childInterface.parentID >= 131251 && childInterface.parentID <= 131500
                                            && childInterface.invStackSizes[i3] == 69696969) {
                                        itemOpacity = 70;
                                    }

                                    Sprite selectedItem;

                                    if (childInterface.id == 30375 && itemCollected(childInterface.inv[i3] - 1)) {
                                        childInterface.opacity = 50;
                                    }
                                    if (childInterface.id == 25412) {
                                        selectedItem = ItemDefinition.getSizedSprite(j9, childInterface.invStackSizes[i3],
                                                l9, 64, 64);
                                    } else {
                                        selectedItem = ItemDefinition.getSprite(j9, childInterface.invStackSizes[i3],
                                                l9);
                                    }
                                    if (selectedItem != null) {
                                        if (activeInterfaceType != 0 && anInt1085 == i3
                                                && modifiedWidgetId == childInterface.id) {
                                            k6 = super.mouseX - anInt1087;
                                            j7 = super.mouseY - anInt1088;

                                            if (k6 < 5 && k6 > -5) {
                                                k6 = 0;
                                            }

                                            if (j7 < 5 && j7 > -5) {
                                                j7 = 0;
                                            }

                                            if (anInt989 < 10) {
                                                k6 = 0;
                                                j7 = 0;
                                            }

                                            selectedItem.drawSprite1(itemSpriteX + k6, itemSpriteY + j7);
                                            int yy = GameFrame.getScreenMode() == ScreenMode.FIXED ? 40
                                                    : 17 + clientHeight / 2 - 235;

                                            if (openInterfaceID == 5292) {
                                                if (super.mouseY >= yy && super.mouseY <= yy + 37) {
                                                    bankItemDragSprite = selectedItem;
                                                    bankItemDragSpriteX = super.mouseX- 20;
                                                    bankItemDragSpriteY = super.mouseY - 20;
                                                } else {
                                                    bankItemDragSprite = null;
                                                }
                                            }
                                            if (itemSpriteY + j7 < DrawingArea.topY && class9.scrollPosition > 0) {
                                                int i10 = anInt945 * (DrawingArea.topY - itemSpriteY - j7) / 3;

                                                if (i10 > anInt945 * 10) {
                                                    i10 = anInt945 * 10;
                                                }

                                                if (i10 > class9.scrollPosition) {
                                                    i10 = class9.scrollPosition;
                                                }

                                                class9.scrollPosition -= i10;
                                                anInt1088 += i10;
                                            }

                                            if (itemSpriteY + j7 + 32 > DrawingArea.bottomY
                                                    && class9.scrollPosition < class9.scrollMax - class9.height) {
                                                int j10 = anInt945 * (itemSpriteY + j7 + 32 - DrawingArea.bottomY) / 3;

                                                if (j10 > anInt945 * 10) {
                                                    j10 = anInt945 * 10;
                                                }

                                                if (j10 > class9.scrollMax - class9.height - class9.scrollPosition) {
                                                    j10 = class9.scrollMax - class9.height - class9.scrollPosition;
                                                }

                                                class9.scrollPosition += j10;
                                                anInt1088 -= j10;
                                            }
                                        } else if (atInventoryInterfaceType != 0 && atInventoryIndex == i3
                                                && atInventoryInterface == childInterface.id) {
                                            selectedItem.drawSprite1(itemSpriteX, itemSpriteY);
                                        } else {
                                            if (childInterface.id == 30375 && childInterface.invStackSizes[i3] == 0) {
                                                selectedItem.drawSpriteWithOpacity(itemSpriteX, itemSpriteY, 75);
                                            } else {
                                                if (itemOpacity == 256) {
                                                    selectedItem.drawSprite(itemSpriteX, itemSpriteY);
                                                } else {
                                                    selectedItem.drawSpriteWithOpacity(itemSpriteX, itemSpriteY,
                                                            itemOpacity);
                                                }
                                            }
                                        }

                                        if (selectedItem.maxWidth == 33 || childInterface.invStackSizes[i3] > 1
                                                || (openInterfaceID == 5292 && childInterface.invStackSizes[i3] == 0)) {
                                            int k10 = childInterface.invStackSizes[i3];

                                            boolean bankTab = childInterface.id >= 22035 && childInterface.id <= 22042;

                                            if (childInterface.parentID >= 131251 && childInterface.parentID <= 131500
                                                    && childInterface.invStackSizes[i3] == 69696969) {
                                                k10 = 0;
                                            }
                                            if (!bankTab) {
                                                if ((openInterfaceID == 5292 || openInterfaceID == 106000) && k10 == 0) { // Placeholders
                                                    newSmallFont.drawBasicString(
                                                            "<trans=170>" + intToKOrMil(k10) + "", itemSpriteX + k6,
                                                            itemSpriteY + 9 + j7, 0xFFFF00, 0, false);
                                                } else if (k10 >= 1500000000 && childInterface.drawInfinity) {
                                                    cacheSprite[780].drawSprite(itemSpriteX, itemSpriteY);
                                                } else {
                                                    if (childInterface.parentID >= 131251 && childInterface.parentID <= 131500
                                                            && childInterface.invStackSizes[i3] == 69696969) {
                                                    } else {
                                                        smallText.method385(0, intToKOrMil(k10), itemSpriteY + 10 + j7, itemSpriteX + 1 + k6);
                                                        if (k10 > 99999 && k10 < 10000000)
                                                            smallText.method385(0xFFFFFF, intToKOrMil(k10), itemSpriteY + 9 + j7,
                                                                    itemSpriteX + k6);
                                                        else if (k10 > 9999999)
                                                            smallText.method385(0x00ff80, intToKOrMil(k10), itemSpriteY + 9 + j7,
                                                                    itemSpriteX + k6);
                                                        else
                                                            smallText.method385(0xFFFF00, intToKOrMil(k10), itemSpriteY + 9 + j7,
                                                                    itemSpriteX + k6);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (childInterface.sprites != null && i3 < 20) {
                                Sprite class30_sub2_sub1_sub1_1 = childInterface.sprites[i3];

                                if (class30_sub2_sub1_sub1_1 != null) {
                                    class30_sub2_sub1_sub1_1.drawSprite(itemSpriteX, itemSpriteY);
                                }
                            }

                            i3++;
                        }
                    }
                } else if (childInterface.type == 3) {
                    boolean flag = false;

                    if (anInt1039 == childInterface.id || anInt1048 == childInterface.id
                            || anInt1026 == childInterface.id || childInterface == childHovered) {
                        flag = true;
                    }

                    int j3;

                    if (interfaceIsSelected(childInterface)) {
                        j3 = childInterface.enabledColor;

                        if (flag && childInterface.enabledMouseOverColor != 0) {
                            j3 = childInterface.enabledMouseOverColor;
                        }
                    } else {
                        j3 = childInterface.disabledColor;

                        if (flag && childInterface.disabledMouseOverColor != 0) {
                            j3 = childInterface.disabledMouseOverColor;
                        }
                    }

                    if (childInterface.opacity == 0) {
                        if (childInterface.filled) {
                            DrawingArea.drawPixels(childInterface.height, childY, childX, j3, childInterface.width);
                        } else {
                            DrawingArea.fillPixels(childX, childInterface.width, childInterface.height, j3, childY);
                        }
                    } else if (childInterface.filled) {
                        DrawingArea.method335(j3, childY, childInterface.width, childInterface.height,
                                256 - (childInterface.opacity & 0xff), childX);
                    } else {
                        DrawingArea.method338(childY, childInterface.height, 256 - (childInterface.opacity & 0xff), j3,
                                childInterface.width, childX);
                    }
                } else if (childInterface.type == 4) {
                    TextDrawingArea textDrawingArea = childInterface.textDrawingAreas;
                    String s = childInterface.message;
                    int xOffset = 0;
                    int imageDraw = 0;
                    final String INITIAL_MESSAGE = s;
                    if (s.contains("<img=")) {
                        int prefix = s.indexOf("<img=");
                        int suffix = s.indexOf(">");
                        try {
                            imageDraw = Integer.parseInt(s.substring(prefix + 5, suffix));
                            s = s.replaceAll(s.substring(prefix + 5, suffix), "");
                            s = s.replaceAll("</img>", "");
                            s = s.replaceAll("<img=>", "");
                        } catch (NumberFormatException | IllegalStateException nfe) {
                            // System.out.println("Unable to draw player crown on interface. Unable to read
                            // rights.");
                            s = INITIAL_MESSAGE;
                        } // System.out.println("Unable to draw player crown on interface, rights too low
                        // or high.");

                        if (suffix > prefix) {
                            xOffset += 14;
                        }
                    }
                    boolean flag1 = false;
                    if (anInt1039 == childInterface.id || anInt1048 == childInterface.id
                            || anInt1026 == childInterface.id || childHovered == childInterface) {
                        flag1 = true;
                    }
                    int i4;
                    if (interfaceIsSelected(childInterface)) {
                        i4 = childInterface.enabledColor;
                        if (flag1 && childInterface.enabledMouseOverColor != 0) {
                            i4 = childInterface.enabledMouseOverColor;
                        }
                        if (childInterface.enabledMessage.length() > 0) {
                            s = childInterface.enabledMessage;
                        }
                    } else {
                        if (childInterface.drawSecondary) {
                            i4 = childInterface.enabledColor;
                        } else {
                            i4 = childInterface.disabledColor;
                        }
                        if (flag1 && childInterface.disabledMouseOverColor != 0) {
                            i4 = childInterface.disabledMouseOverColor;
                        }
                    }
                    if (childInterface.atActionType == 6 && aBoolean1149) {
                        s = "Please wait...";
                        i4 = childInterface.disabledColor;
                    }
                    if (DrawingArea.width == 516) {
                        if (i4 == 0xffff00) {
                            i4 = 255;
                        }
                        if (i4 == 49152) {
                            i4 = 0xffffff;
                        }
                    }


                    if (childInterface.id >= 35613 && childInterface.id < 35813) {
                        if (RSInterface.interfaceCache[3900].inv[childInterface.id - 35613] > 0) {
                            String[] data = s.split(",");
                            int currency = 0;
                            if (data.length > 1) {
                                currency = Integer.parseInt(data[1]);
                            }
                            if (currency != -1) {
                                if (currencyImage[currency] == null) {
                                    currencyImage[currency] = new Sprite("currency" + currency);
                                }
                                currencyImage[currency].drawSprite(childX - 19, childY);
                                int value = Integer.parseInt(data[0]);
                                if (value <= 0) {
                                    smallText.drawRegularText(true, childX - 7, 0xffff00, "FREE", childY + 10);
                                } else if (value < 100000) {
                                    if (value % 1000 == 0 && value >= 10000)
                                        smallText.drawRegularText(true, childX - 7, 0xffff00, (value / 1000) + "K", childY + 10);
                                    else
                                        smallText.drawRegularText(true, childX - 7, 0xffff00, value + "", childY + 10);
                                } else if (value < 10000000) {
                                    smallText.drawRegularText(true, childX - 7, 0xffff00, (value / 1000) + "K", childY + 10);
                                } else {
                                    smallText.drawRegularText(true, childX - 7, 0xffff00, (value / 1000000) + "M", childY + 10);
                                }

                            }
                        }
                        continue;
                    }
                    if (childInterface.parentID == 1151 || childInterface.parentID == 12855) {
                        switch (i4) {
                            case 16773120:
                                i4 = 0xFE981F;
                                break;
                            case 7040819:
                                i4 = 0xAF6A1A;
                                break;
                        }
                    }
                    for (int l6 = childY + textDrawingArea.anInt1497; s.length() > 0; l6 += textDrawingArea.anInt1497) {
                        if (s.contains("%")) {
                            do {
                                int k7 = s.indexOf("%1");
                                if (k7 == -1) {
                                    break;
                                }
                                if (childInterface.id < 4000 || childInterface.id > 5000 && childInterface.id != 13921
                                        && childInterface.id != 13922 && childInterface.id != 12171
                                        && childInterface.id != 12172) {
                                    s = s.substring(0, k7) + methodR(extractInterfaceValues(childInterface, 0))
                                            + s.substring(k7 + 2);
                                } else {
                                    s = s.substring(0, k7)
                                            + interfaceIntToString(extractInterfaceValues(childInterface, 0))
                                            + s.substring(k7 + 2);
                                }
                            } while (true);
                            do {
                                int l7 = s.indexOf("%2");
                                if (l7 == -1) {
                                    break;
                                }
                                s = s.substring(0, l7) + interfaceIntToString(extractInterfaceValues(childInterface, 1))
                                        + s.substring(l7 + 2);
                            } while (true);
                            do {
                                int i8 = s.indexOf("%3");
                                if (i8 == -1) {
                                    break;
                                }
                                s = s.substring(0, i8) + interfaceIntToString(extractInterfaceValues(childInterface, 2))
                                        + s.substring(i8 + 2);
                            } while (true);
                            do {
                                int j8 = s.indexOf("%4");
                                if (j8 == -1) {
                                    break;
                                }
                                s = s.substring(0, j8) + interfaceIntToString(extractInterfaceValues(childInterface, 3))
                                        + s.substring(j8 + 2);
                            } while (true);
                            do {
                                int k8 = s.indexOf("%5");
                                if (k8 == -1) {
                                    break;
                                }
                                s = s.substring(0, k8) + interfaceIntToString(extractInterfaceValues(childInterface, 4))
                                        + s.substring(k8 + 2);
                            } while (true);
                        }
                        int l8 = s.indexOf("\\n");
                        String s1;
                        if (l8 != -1) {
                            s1 = s.substring(0, l8);
                            s = s.substring(l8 + 2);
                        } else {
                            s1 = s;
                            s = "";
                        }
                        if (imageDraw > 0 && xOffset > 0) {
                            int drawImageY = childY + 14;
                            if (imageDraw >= 841 && imageDraw <= 849) { // Clan chat images
                                xOffset -= 5;
                                drawImageY -= 7;
                            }
                            newRegularFont.drawBasicString("<img=" + imageDraw + ">", childX, drawImageY, true);
                        }
                        if (childInterface.centerText) {
                            textDrawingArea.drawCenteredText(i4, childX + childInterface.width / 2 + xOffset, s1, l6,
                                    childInterface.textShadow);
                        } else if (childInterface.rightText) {
                            textDrawingArea.drawRightAlignedString(s1, childX + childInterface.width / 2 + xOffset, l6, i4, childInterface.textShadow);
                        } else {
                            textDrawingArea.drawRegularText(childInterface.textShadow, childX + xOffset, i4, s1, l6);
                        }
                    }
                } else if (childInterface.type == 42 && childInterface.isVisible()) {

                    boolean flag1 = false;
                    if (anInt1039 == childInterface.id || anInt1048 == childInterface.id
                            || anInt1026 == childInterface.id || childHovered == childInterface) {
                        flag1 = true;
                    }
                    if (!flag1) {
                        childInterface.disabledSprite.drawAdvancedSprite(childX, childY);
                    } else {
                        childInterface.enabledSprite.drawAdvancedSprite(childX, childY);
                    }


                    if (childInterface.message != null) {
                        if (childInterface.centerText) {
                            childInterface.textDrawingAreas.drawCenteredText(
                                    childInterface.enabledColor,
                                    childX + childInterface.messageOffsetX,
                                    childInterface.message,
                                    childY + childInterface.messageOffsetY,
                                    true);
                        } else {
                            childInterface.textDrawingAreas.drawText(childInterface.enabledColor, childInterface.message, childX + 5 + childInterface.messageOffsetX, childY + childInterface.messageOffsetY);
                        }
                    }
                }
                if (childInterface.type == 130) {
                    RSInterface mainSprite = RSInterface.interfaceCache[class9.children[0]];
                    DrawingArea.drawAlphaFilledPixels(childX, childY, mainSprite.width, mainSprite.height + 2, childInterface.layerColor, childInterface.layerTransparency);
                }

                if (childInterface.type == 5) {
                    Sprite sprite;

                    if (childInterface.itemSpriteId > 0 && childInterface.disabledSprite == null
                            && childInterface.enabledSprite == null) {
                        childInterface.disabledSprite = ItemDefinition.getSprite(childInterface.itemSpriteId, 1,
                                (childInterface.itemSpriteZoom == -1) ? 0 : -1, childInterface.itemSpriteZoom, false);
                        childInterface.enabledSprite = ItemDefinition.getSprite(childInterface.itemSpriteId, 1,
                                (childInterface.itemSpriteZoom == -1) ? 0 : -1, childInterface.itemSpriteZoom, false);

                    }

                    if (interfaceIsSelected(childInterface)) {
                        sprite = childInterface.enabledSprite;
                    } else {
                        sprite = childInterface.disabledSprite;
                    }

                    if (childInterface.id == 1164 || childInterface.id == 1167 || childInterface.id == 1170
                            || childInterface.id == 1174 || childInterface.id == 1540 || childInterface.id == 1541
                            || childInterface.id == 7455 || childInterface.id == 18470 || childInterface.id == 13035
                            || childInterface.id == 13045 || childInterface.id == 13053 || childInterface.id == 13061
                            || childInterface.id == 13069 || childInterface.id == 13079 || childInterface.id == 30064
                            || childInterface.id == 30075 || childInterface.id == 30083 || childInterface.id == 30106
                            || childInterface.id == 30114 || childInterface.id == 30106 || childInterface.id == 30170
                            || childInterface.id == 13087 || childInterface.id == 30162 || childInterface.id == 13095) {
                        sprite = childInterface.enabledSprite;
                    }

                    if (childInterface.summonReq > 0 && childInterface.disabledSprite != null
                            && childInterface.enabledSprite != null) {
                        childInterface.greyScale = (childInterface.summonReq > maxStats[23]);
                        if (childInterface.greyScale) {
                            childInterface.disabledSprite.greyScale();
                            sprite = childInterface.disabledSprite;
                        } else {
                            sprite = childInterface.enabledSprite;
                        }
                    }

                    if (spellSelected == 1 && childInterface.id == spellID && spellID != 0 && sprite != null) {
                        sprite.drawSprite(childX, childY, 0xffffff);
                    } else {
                        if (sprite != null) {
                            if (childInterface.advancedSprite) {
                                sprite.drawAdvancedSprite(childX, childY);
                            } else {
                                sprite.drawSprite(childX, childY);
                            }
                        }
                    }
                    if (autoCast && childInterface.id == autocastId) {
                        cacheSprite[497].drawSprite(childX - 3, childY - 3);
                    }
                } else if (childInterface.type == 553) {
                    Sprite sprite = null;
                    sprite = childInterface.disabledSprite;
                    sprite.drawHoverSprite(childX, childY, childInterface.enabledSprite);
                } else if (childInterface.type == 554) {

                    ProgressBar inter = (ProgressBar) childInterface;
                    int displayColor = inter.colorTypes[inter.progressBarState];
                    int defaultBarColor = inter.defaultColorTypes[inter.defaultBarState];

                    if (inter.drawHorizontal) {
                        int drawingWidth = inter.progressBarPercentage * inter.width / 100;
                        DrawingArea.drawPixels(inter.height, childY, childX, defaultBarColor, inter.width);
                        DrawingArea.drawPixels(inter.height, childY, childX, displayColor, drawingWidth);
                        DrawingArea.fillPixels(childX, inter.width, inter.height, 0, childY);

                        // DrawingArea.fillRect(childX, childY, inter.width, inter.height, defaultBarColor);
                        //  DrawingArea.fillRect(childX, childY, drawingWidth, inter.height, displayColor);
                        //  DrawingArea.drawRect(childX, childY, inter.width, inter.height, 0);
                    } else {
                        int drawingHeight = inter.progressBarPercentage * inter.height / 100;

                        RSGraphics.fillRect(childX, childY, inter.width, inter.height, 0x00000);
                        RSGraphics.fillRect(childX, childY, inter.width, drawingHeight, displayColor);
                        RSGraphics.drawRect(childX, childY, inter.width, inter.height, 0);

                        DrawingArea.drawPixels(inter.height, childY, childX, defaultBarColor, inter.width);
                        DrawingArea.drawPixels(drawingHeight,  childY+ inter.height - drawingHeight, childX, displayColor, inter.width);
                        DrawingArea.fillPixels(childX, inter.width, inter.height, 0, childY);
                    }
                } else if (childInterface.type == 6) {

                    int k3 = Rasterizer.centerX;
                    int j4 = Rasterizer.centerY;
                    Rasterizer.centerX = childX + childInterface.width / 2;
                    Rasterizer.centerY = childY + childInterface.height / 2;
                    int i5 = Rasterizer.SINE[childInterface.modelRotationY] * childInterface.modelZoom >> 16;
                    int l5 = Rasterizer.COSINE[childInterface.modelRotationY] * childInterface.modelZoom >> 16;
                    boolean flag2 = interfaceIsSelected(childInterface);
                    int i7;
                    if (flag2) {
                        i7 = childInterface.enabledAnimationId;
                    } else {
                        i7 = childInterface.disabledAnimationId;
                    }
                    Model model;
                    if (i7 == -1) {
                        model = childInterface.method209(-1, -1, flag2);
                    } else {
                        Animation animation = Animation.cache[i7];
                        model = childInterface.method209(animation.frameIDs2[childInterface.anInt246],
                                animation.frameIDs[childInterface.anInt246], flag2);
                    }
                    if (model != null) {
                        Rasterizer.clearDepthBuffer();
                        model.renderSingle(childInterface.modelRotationX, 0, childInterface.modelRotationY, 0, i5, l5);
                    }
                    Rasterizer.centerX = k3;
                    Rasterizer.centerY = j4;
                } else if (childInterface.type == 42 && childInterface.isVisible()) {
                    if (childInterface.widgetActive) {
                        if (mouseX >= childX && mouseY >= childY && mouseX < childX + childInterface.width
                                && mouseY < childY + childInterface.height) {
                            childInterface.enabledSprite.drawSprite(childX, childY);
                        } else {
                            childInterface.disabledSprite.drawSprite(childX, childY);
                        }
                        if (childInterface.centerText)
                            // childInterface.textDrawingAreas.drawText(colour, txt, x, y);
                            childInterface.textDrawingAreas.drawCenteredText(childInterface.disabledColor,
                                    childX + childInterface.messageOffsetX, childInterface.message,
                                    childY + childInterface.messageOffsetY, true);
                        else
                            childInterface.textDrawingAreas.drawText(childInterface.disabledColor, childInterface.message,
                                    childX + 5 + childInterface.messageOffsetX, childY + childInterface.messageOffsetY);
                    }
                } else if (childInterface.type == 7) { // sec pretty sure 1 of them libs aint used gotta open it up ill
                    // test 1 by 1 if u want
                    TextDrawingArea textDrawingArea_1 = childInterface.textDrawingAreas;
                    int k4 = 0;
                    for (int j5 = 0; j5 < childInterface.height; j5++) {
                        for (int i6 = 0; i6 < childInterface.width; i6++) {
                            if (childInterface.inv[k4] > 0) {
                                ItemDefinition itemDef = ItemDefinition.get(childInterface.inv[k4] - 1);
                                String s2 = itemDef.name;
                                if (itemDef.stackable || childInterface.invStackSizes[k4] != 1) {
                                    s2 = s2 + " x" + intToKOrMilLongName(childInterface.invStackSizes[k4]);
                                }
                                int i9 = childX + i6 * (115 + childInterface.invSpritePadX);
                                int k9 = childY + j5 * (12 + childInterface.invSpritePadY);
                                if (childInterface.centerText) {
                                    textDrawingArea_1.drawCenteredText(childInterface.disabledColor,
                                            i9 + childInterface.width / 2, s2, k9, childInterface.textShadow);
                                } else {
                                    textDrawingArea_1.drawRegularText(childInterface.textShadow, i9,
                                            childInterface.disabledColor, s2, k9);
                                }
                            }
                            k4++;
                        }
                    }
                } else if (childInterface.type == 77) {
                    if (childInterface.inputToggled)
                        DrawingArea.drawPixels(childInterface.height, childY, childX, -5331456, childInterface.width);
                    else
                        DrawingArea.drawPixels(childInterface.height, childY, childX, -14738666, childInterface.width);

                    DrawingArea.drawPixels(childInterface.height - 2, childY + 1, childX + 1, -13554910,
                            childInterface.width - 2);
                    DrawingArea.drawPixels(childInterface.height - 4, childY + 2, childX + 2, -13752543,
                            childInterface.width - 4);
                    if (childInterface.inputToggled)
                        childInterface.rsFont.drawBasicString(
                                (loopCycle % 40 < 20) ? childInterface.inputText + "|" : childInterface.inputText,
                                childX + 6, childY + (childInterface.height / 2) + 5, 0xffffff, 0, true);
                    else
                        childInterface.rsFont.drawBasicString(childInterface.inputText, childX + 6,
                                childY + (childInterface.height / 2) + 5, 0xffffff, 0, true);

                } else if (childInterface.type == 8 && (anInt1500 == childInterface.id || anInt1044 == childInterface.id
                        || anInt1129 == childInterface.id) && anInt1501 == 30 && !menuOpen) {
                    int boxWidth = 0;
                    int boxHeight = 0;
                    TextDrawingArea textDrawingArea_2 = normalText;
                    for (String s1 = childInterface.message; s1.length() > 0; ) {
                        if (s1.indexOf("%") != -1) {
                            do {
                                int k7 = s1.indexOf("%1");
                                if (k7 == -1) {
                                    break;
                                }
                                s1 = s1.substring(0, k7)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 0))
                                        + s1.substring(k7 + 2);
                            } while (true);
                            do {
                                int l7 = s1.indexOf("%2");
                                if (l7 == -1) {
                                    break;
                                }
                                s1 = s1.substring(0, l7)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 1))
                                        + s1.substring(l7 + 2);
                            } while (true);
                            do {
                                int i8 = s1.indexOf("%3");
                                if (i8 == -1) {
                                    break;
                                }
                                s1 = s1.substring(0, i8)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 2))
                                        + s1.substring(i8 + 2);
                            } while (true);
                            do {
                                int j8 = s1.indexOf("%4");
                                if (j8 == -1) {
                                    break;
                                }
                                s1 = s1.substring(0, j8)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 3))
                                        + s1.substring(j8 + 2);
                            } while (true);
                            do {
                                int k8 = s1.indexOf("%5");
                                if (k8 == -1) {
                                    break;
                                }
                                s1 = s1.substring(0, k8)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 4))
                                        + s1.substring(k8 + 2);
                            } while (true);
                        }
                        int l7 = s1.indexOf("\\n");
                        String s4;
                        if (l7 != -1) {
                            s4 = s1.substring(0, l7);
                            s1 = s1.substring(l7 + 2);
                        } else {
                            s4 = s1;
                            s1 = "";
                        }
                        int j10 = textDrawingArea_2.getTextWidth(s4);
                        if (j10 > boxWidth) {
                            boxWidth = j10;
                        }
                        boxHeight += textDrawingArea_2.anInt1497 + 1;
                    }
                    boxWidth += 6;
                    boxHeight += 7;
                    int xPos = childX + childInterface.width - 5 - boxWidth;
                    int yPos = childY + childInterface.height + 5;
                    if (xPos < childX + 5) {
                        xPos = childX + 5;
                    }
                    if (xPos + boxWidth > interfaceX + class9.width) {
                        xPos = interfaceX + class9.width - boxWidth;
                    }
                    if (yPos + boxHeight > interfaceY + class9.height) {
                        yPos = childY - boxHeight;
                    }
                    DrawingArea.drawPixels(boxHeight, yPos, xPos, 0xFFFFA0, boxWidth);
                    DrawingArea.fillPixels(xPos, boxWidth, boxHeight, 0, yPos);
                    String s2 = childInterface.message;
                    for (int j11 = yPos + textDrawingArea_2.anInt1497 + 2; s2
                            .length() > 0; j11 += textDrawingArea_2.anInt1497 + 1) {// anInt1497
                        if (s2.indexOf("%") != -1) {
                            do {
                                int k7 = s2.indexOf("%1");
                                if (k7 == -1) {
                                    break;
                                }
                                s2 = s2.substring(0, k7)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 0))
                                        + s2.substring(k7 + 2);
                            } while (true);
                            do {
                                int l7 = s2.indexOf("%2");
                                if (l7 == -1) {
                                    break;
                                }
                                s2 = s2.substring(0, l7)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 1))
                                        + s2.substring(l7 + 2);
                            } while (true);
                            do {
                                int i8 = s2.indexOf("%3");
                                if (i8 == -1) {
                                    break;
                                }
                                s2 = s2.substring(0, i8)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 2))
                                        + s2.substring(i8 + 2);
                            } while (true);
                            do {
                                int j8 = s2.indexOf("%4");
                                if (j8 == -1) {
                                    break;
                                }
                                s2 = s2.substring(0, j8)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 3))
                                        + s2.substring(j8 + 2);
                            } while (true);
                            do {
                                int k8 = s2.indexOf("%5");
                                if (k8 == -1) {
                                    break;
                                }
                                s2 = s2.substring(0, k8)
                                        + interfaceIntToString(extractInterfaceValues(childInterface, 4))
                                        + s2.substring(k8 + 2);
                            } while (true);
                        }
                        int l11 = s2.indexOf("\\n");
                        String s5;
                        if (l11 != -1) {
                            s5 = s2.substring(0, l11);
                            s2 = s2.substring(l11 + 2);
                        } else {
                            s5 = s2;
                            s2 = "";
                        }
                        if (childInterface.centerText) {
                            textDrawingArea_2.drawCenteredText(yPos, xPos + childInterface.width / 2, s5, j11, false);
                        } else {
                            if (s5.contains("\\r")) {
                                String text = s5.substring(0, s5.indexOf("\\r"));
                                String text2 = s5.substring(s5.indexOf("\\r") + 2);
                                textDrawingArea_2.drawRegularText(false, xPos + 3, 0, text, j11);
                                int rightX = boxWidth + xPos - textDrawingArea_2.getTextWidth(text2) - 2;
                                textDrawingArea_2.drawRegularText(false, rightX, 0, text2, j11);
                            } else {
                                textDrawingArea_2.drawRegularText(false, xPos + 3, 0, s5, j11);
                            }
                        }
                    }
                } else if (childInterface.type == 9) {
                    // if (interfaceIsSelected(childInterface)) {
                    // } else {
                    try {
                        drawHoverBox(childX, childY, childInterface.tooltipBoxText);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (childInterface.type == 88) {
                    Sprite sprite;
                    if (interfaceIsSelected(childInterface)) {
                        sprite = childInterface.enabledSprite;
                    } else {
                        sprite = childInterface.disabledSprite;
                    }
                    if (sprite != null) {
                        if (childInterface.drawsTransparent) {
                            sprite.drawTransparentSprite(childX, childY, childInterface.customOpacity);
                        } else {
                            sprite.drawSprite(childX, childY);
                        }
                    }
                } else if (childInterface.type == 10 && !menuOpen && skillTabHoverChild > 0 && anInt1501 == 30) {
                    if (childInterface.id != skillTabHoverChild)
                        continue;
                    int boxWidth = 0;
                    int boxHeight = 0;
                    TextDrawingArea textDrawingArea_2 = normalText;
                    for (String s1 = childInterface.message; s1.length() > 0; ) {
                        int l7 = s1.indexOf("\\n");
                        String s4;
                        if (l7 != -1) {
                            s4 = s1.substring(0, l7);
                            s1 = s1.substring(l7 + 2);
                        } else {
                            s4 = s1;
                            s1 = "";
                        }
                        int j10 = textDrawingArea_2.getTextWidth(s4);
                        if (j10 > boxWidth) {
                            boxWidth = j10;
                        }
                        boxHeight += textDrawingArea_2.anInt1497 + 1;
                    }
                    boxWidth += 6;
                    boxHeight += 7;
                    boolean canDrawPercent = currentExp[skillIdForButton(childInterface.id)] < 1000000000
                            && Skills.goalData[skillIdForButton(childInterface.id)][0] != -1
                            && Skills.goalData[skillIdForButton(childInterface.id)][1] != -1
                            && Skills.goalData[skillIdForButton(childInterface.id)][2] != -1;
                    int xPos = (childX + childInterface.width) - 5 - boxWidth;
                    int yPos = childY + childInterface.height + 5;
                    if (canDrawPercent && Skills.SKILL_ID(childInterface.id) == childInterface.id) {
                        boxHeight += canDrawPercent ? 11 : 0;
                    } else {
                        boxHeight += -2;
                        canDrawPercent = false;
                    }
                    if (GameFrame.getScreenMode() == ScreenMode.FIXED) {
                        if (xPos < childX + 5) {
                            xPos = childX + 5;
                        }
                        if (xPos + boxWidth > interfaceX + class9.width) {
                            xPos = (interfaceX + class9.width) - boxWidth;
                        }
                        if (yPos + boxHeight > interfaceY + class9.height) {
                            yPos = (childY - boxHeight);
                        }
                        if (Skills.SKILL_ID(childInterface.id) == childInterface.id
                                && xPos + boxWidth + interfaceX + class9.width > 765) {
                            xPos = 765 - boxWidth - interfaceX - class9.width - 3;
                        }
                        if (Skills.SKILL_ID(childInterface.id) == childInterface.id
                                && yPos + boxHeight > 503 - yPos + boxHeight - 118) {
                            yPos -= boxHeight + 35;
                        }
                    } else {
                        if (xPos < childX + 5) {
                            xPos = childX + 5;
                        }
                        if (xPos > 1560 && xPos < 1600) {
                            xPos -= 40;
                        } else if (xPos >= 1600) {
                            xPos -= 90;
                        }

                        if (xPos + boxWidth > clientWidth) {
                            xPos -= (xPos + boxWidth) - clientWidth;
                        }

                        if (yPos + boxHeight > interfaceY + class9.height) {
                            yPos = childY - boxHeight;
                        }

                    }
                    DrawingArea.drawPixels(boxHeight, yPos, xPos, 0xFFFFA0, boxWidth);
                    if (canDrawPercent && currentExp[skillIdForButton(childInterface.id)] < 1000000000
                            && Skills.goalData[skillIdForButton(childInterface.id)][0] != -1
                            && Skills.goalData[skillIdForButton(childInterface.id)][1] != -1
                            && Skills.goalData[skillIdForButton(childInterface.id)][2] != -1) {
                        int goalPercentage = Skills.goalData[skillIdForButton(childInterface.id)][2];
                        DrawingArea.fillPixels(xPos + 4, boxWidth - 7, 12, 0, yPos + boxHeight - 14);
                        DrawingArea.drawPixels(10, yPos + boxHeight - 13, 4 + xPos + 1, Color.RED.getRGB(),
                                boxWidth - 9);
                        DrawingArea.drawPixels(10, yPos + boxHeight - 13, 4 + xPos + 1, Color.GREEN.getRGB(),
                                (int) ((boxWidth - 7) * .01 * goalPercentage) - 2);
                        if (goalPercentage == 100) {
                            smallText.drawText(0, "Goal Completed!", yPos - 3 + boxHeight, (int) (xPos
                                    + (boxWidth - 7 - (textDrawingArea_2.getTextWidth("Goal Completed!") + 10)) / 2.0
                                    + 54));
                        } else {
                            smallText.drawText(0, goalPercentage + "%", yPos - 3 + boxHeight, (int) (xPos
                                    + (boxWidth - 6 - (textDrawingArea_2.getTextWidth(goalPercentage + "%") + 10)) / 2.0
                                    + 24));
                        }
                    }
                    DrawingArea.fillPixels(xPos, boxWidth, boxHeight, 0, yPos);
                    String s2 = childInterface.message;
                    for (int j11 = yPos + textDrawingArea_2.anInt1497 + 2; s2
                            .length() > 0; j11 += textDrawingArea_2.anInt1497 + 1) {
                        int l11 = s2.indexOf("\\n");
                        String s5;
                        if (l11 != -1) {
                            s5 = s2.substring(0, l11);
                            s2 = s2.substring(l11 + 2);
                        } else {
                            s5 = s2;
                            s2 = "";
                        }
                        textDrawingArea_2.drawRegularText(false, xPos + 3, 0, s5, j11);
                    }
                } else if (childInterface.type == 12) {
                    // if (interfaceIsSelected(childInterface)) {
                    // } else {
                    try {
                        drawHoverBox(childX, childY, childInterface.message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    /// }
                } else if (childInterface.type == 13) {
                    Sprite sprite;
                    if (interfaceIsSelected(childInterface)) {
                        sprite = childInterface.enabledSprite;
                    } else {
                        sprite = childInterface.disabledSprite;
                    }
                    if (sprite != null) {
                        if (childInterface.opacity > 0)
                            sprite.drawTransparentSprite(childX, childY, childInterface.opacity);
                        else
                            sprite.drawSprite(childX, childY);
                    }

                }

                if (childInterface.type == 111) {
                    //  System.out.println("Type ->");
                    boolean hovered = childInterface.selected;
                    Sprite sprite = childInterface.disabledSprite;
                    if (interfaceIsSelected(childInterface) || hovered) {
                        sprite = childInterface.enabledSprite;
                    }

                    sprite.drawSprite(childX, childY);
                }

                if (childInterface.type == 100 && childInterface.unrevealedSprite != null) {

                    childInterface.unrevealedSprite.drawSprite(childX + 2, childY + 2);
                    // System.out.println("Unrevealed sprite drawn");
                }


            }
        }
        DrawingArea.setBounds(i1, j1, k1, l1);
    }

    public void drawOnBankInterface() {
        if (openInterfaceID == 5292 && RSInterface.interfaceCache[27000].message.equals("1")) {
            int i = Integer.parseInt(RSInterface.interfaceCache[27001].message);
            int j = Integer.parseInt(RSInterface.interfaceCache[27002].message);
            for (int k = 0; k <= i; k++) {
                RSInterface.interfaceCache[27014 + k].disabledSprite = cacheSprite[1009];
                RSInterface.interfaceCache[27014 + k].message = (new StringBuilder())
                        .append("Click here to select tab ").append(k + 1).toString();
            }

            for (int l = i + 1; l <= 8; l++) {
                RSInterface.interfaceCache[27014 + l].disabledSprite = null;
                RSInterface.interfaceCache[27014 + l].message = "";
            }

            if (i != 8) {
                RSInterface.interfaceCache[27015 + i].disabledSprite = cacheSprite[1010];
                RSInterface.interfaceCache[27015 + i].message = "Drag an item here to create a new tab";
            }
            if (j == -1) {
                RSInterface.interfaceCache[27013].disabledSprite = cacheSprite[1011];
            } else if (j > 0) {
                RSInterface.interfaceCache[27014 + j].disabledSprite = cacheSprite[1012];
                RSInterface.interfaceCache[27014].disabledSprite = cacheSprite[1011];
            } else {
                RSInterface.interfaceCache[27014].disabledSprite = cacheSprite[1008];
            }
            RSInterface.interfaceCache[27000].message = "0";
        }
    }

    public void displayLoadingScreen() {
      /*  if (loadingImages[0] == null || loadingImages[1] == null || loadingImages[2] == null || loadingImages[3] == null) {
            return;
        }*/
        Graphics graphics = this.getGraphics() != null ? this.getGraphics() : canvas.getGraphics();//canvas == null ? this.getGraphics() : canvas.getGraphics();
        if (graphics == null) {
            return;
        }
        if (loadingImages[0] != null)
            graphics.drawImage(loadingImages[0], 0, 0, null);
        if (loadingPercentage > 0) {
            int scaleX = (loadingPercentage * 790 / 560) * 4;
            if (scaleX > 559) {
                scaleX = 559;
            }
            int addedX = 0;

            if (loadingImages[3] != null)
                graphics.drawImage(loadingImages[3], 72 + scaleX , 410 - 130, null);
            graphics.setFont(new Font("Serif", Font.BOLD, 16));

            int drawTextX = 80 + scaleX;
            if (loadingPercentage < 10) {
                drawTextX += 4;
            } else if (loadingPercentage == 100) {
                drawTextX -= 4;
            }

            graphics.drawString("" + loadingPercentage + "", drawTextX , 432 - 130 +2);

            if (loadingImages[2] != null)
                graphics.drawImage(loadingImages[2].getSubimage(0, 0,
                        scaleX + addedX >= loadingImages[2].getWidth() ? loadingImages[2].getWidth() : scaleX, 32),
                        89, 453 - 130, null);
            // canvas.getGraphics().drawImage(loadingImages[4], 18+scaleX, 453, null);
        }
    }

    public int skillIdForButton(int buttonId) {
        int[] buttonIds = {4040, 4076, 4112, 4046, 4082, 4118, 4052, 4088, 4124, 4058, 4094, 4130, 4064, 4100, 4136,
                4070, 4106, 4142, 4160, 2832, 13917, 28173, 28174, 28175, 28176};
        int[] skillID = {0, 3, 14, 2, 16, 13, 1, 15, 10, 4, 17, 7, 5, 12, 11, 6, 9, 8, 20, 18, 19, 21, 22, 23, 24,
                25};
        for (int i = 0; i < buttonIds.length; i++) {
            if (buttonIds[i] == buttonId) {
                buttonId = i;
                return skillID[buttonId];
            }
        }
        return 0;
    }

    public void recreateClientFrame(boolean undecorative, int width, int height, boolean resizable, int displayMode,
                                    boolean toggle) {
        recreateClientFrame(undecorative, width, height, resizable);
    }

    boolean otherHover;

    public void handleHovers(boolean alertScreen) {
        int cursor = -1;
        oldCursor = null;

        otherHover = loginHover = rememberMeHover = textArea1Hover = textArea2Hover = backButtonHover = false; // Reset hovers
        for (int i = 0; i < accountHovers.length; i++) {
            accountHovers[i] = false;
        }

        if (!alertScreen) {

            if (mouseX >= 296 && mouseX <= 467 && mouseY >= 416 && mouseY <= 461) {
                cursor = Configuration.NEW_CURSORS ? 1061 : Cursor.HAND_CURSOR;
                loginHover = true;
            }

            if (showTwoFactorAuth) {
                if (mouseX >= 265 && mouseX <= 305 && mouseY >= 213 && mouseY <= 235) {
                    cursor = Configuration.NEW_CURSORS ? 1061 : Cursor.HAND_CURSOR;
                    otherHover = true;
                }
            } else {
                if (mouseX >= 243 && mouseX <= 522) {
                    if (mouseY >= 258 && mouseY <= 297) {
                        cursor = Cursor.TEXT_CURSOR;
                        textArea1Hover = true;
                    } else if (mouseY >= 326 && mouseY <= 365) {
                        cursor = Cursor.TEXT_CURSOR;
                        textArea2Hover = true;
                    }
                }
                if (mouseX >= 248 && mouseX <= 267 && mouseY >= 378 && mouseY <= 397) {
                    cursor = Configuration.NEW_CURSORS ? 1061 : Cursor.HAND_CURSOR;
                    rememberMeHover = true;
                }
            }

            /*
            if (mouseX >= 205 && mouseX <= 565) {
                if (mouseY >= 400 && mouseY <= 450) {
                    accountHovers[0] = mouseX >= 260 && mouseX <= 300;
                    accountHovers[1] = mouseX >= 354 && mouseX <= 394;
                    accountHovers[2] = mouseX >= 455 && mouseX <= 500;

                    for (int i = 0; i < accountHovers.length; i++) {
                        if (accountHovers[i]) {
                            cursor = Configuration.NEW_CURSORS ? 1061 : Cursor.HAND_CURSOR;
                            break;
                        }
                    }
                }
            }*/
        } else {
            if (mouseX >= 330 && mouseX <= 440 && mouseY >= 270 && mouseY <= 325) {
                cursor = Configuration.NEW_CURSORS ? 1061 : Cursor.HAND_CURSOR;
                backButtonHover = true;
            }
        }

        if (cursor == Cursor.HAND_CURSOR || cursor == Cursor.TEXT_CURSOR) {
            getGameComponent().setCursor(new Cursor(cursor));
        } else if (cursor == 1061) {
            super.setCursor(CursorData.CURSOR_55);
        } else {
            if (Configuration.NEW_CURSORS) {
                super.setCursor(CursorData.CURSOR_0);
            } else {
                getGameComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    public void drawLoginScreen(boolean flag) {
        resetImageProducers();
        titleScreenIP.initDrawingArea();
        DrawingArea.drawFilledPixels(0, 0, getScreenWidth(), getScreenHeight(), 0x000000);
        int centerX = getScreenWidth() / 2;
        int centerY = getScreenHeight() / 2;
        // titleAlpha = 0;
        titleAlpha += titleAlpha < 250 ? 8 : 0;
        if (Configuration.DISPLAY_GAMEWORLD_ON_LOGIN) {
            drawAnimatedWorldBackground(true);
        } else {
            if (titleAlpha < 250) {
                cacheSprite[449].drawTransparentSprite(centerX - cacheSprite[449].myWidth / 2,
                        centerY - cacheSprite[449].myHeight / 2, titleAlpha);
            } else {
                // if (loginMessage1.isEmpty() && loginMessage2.isEmpty()) {
                handleHovers(false);
                cacheSprite[449].drawAdvancedSprite(0, 0);
                // cacheSprite[1177].drawAdvancedSprite(310, 115);

                if (loginHover) {
                    cacheSprite[1175].drawAdvancedSprite(298, 412);//LOGINEDIT
                } else {
                    cacheSprite[1174].drawAdvancedSprite(298, 412);//LOGINEDIT
                }

                if (!showTwoFactorAuth) {

                    if (textArea1Hover) {
                        cacheSprite[1179].drawAdvancedSprite(243, 258);
                    } else {
                        cacheSprite[1178].drawAdvancedSprite(243, 258);
                    }
                    if (textArea2Hover) {
                        cacheSprite[1179].drawAdvancedSprite(243, 327);
                    } else {
                        cacheSprite[1178].drawAdvancedSprite(243, 327);
                    }
                    if (Configuration.SAVE_ACCOUNTS) {
                        cacheSprite[1177].drawAdvancedSprite(250, 381);
                    } else {
                        cacheSprite[1176].drawAdvancedSprite(250, 381);
                    }
                }
                //   normalText.method385(0xffff00, "Mouse X: " + super.mouseX + " , Mouse Y: " + super.mouseY, 314, 5);


                if (showTwoFactorAuth) {

                    if (textArea1Hover) {
                        cacheSprite[1179].drawAdvancedSprite(243, 360);
                    } else {
                        cacheSprite[1178].drawAdvancedSprite(243, 360);
                    }

                    if (loginScreenCursorPos == 0 && loopCycle % 45 < 10) {
                        chatTextDrawingArea.drawRegularText(true, 260, 16777215, currentPinCode + "|", 385);
                    } else {
                        chatTextDrawingArea.drawRegularText(true, 260, 16777215, currentPinCode, 385);
                    }
                } else {

                    if (loginScreenCursorPos == 0 && loopCycle % 45 < 10) {
                        chatTextDrawingArea.drawRegularText(true, 255, 16777215, myUsername + "|", 283);
                    } else {
                        chatTextDrawingArea.drawRegularText(true, 255, 16777215, myUsername, 283);
                    }

                    if (loginScreenCursorPos == 1 && loopCycle % 45 < 10) {
                        chatTextDrawingArea.drawRegularText(true, 255, 16777215, getStars(password) + "|", 352);
                    } else {
                        chatTextDrawingArea.drawRegularText(true, 255, 16777215, getStars(password), 352);
                    }
                }
/*

                    int drawAccountX = 500;

                    for (int i = 0; i < accountHovers.length; i++) {
                        Account account = accountManager.getAccounts()[i];
                        if (account == null) {
                            if (accountManager != null) {
                                cacheSprite[1178].drawAdvancedSprite(999, 999);
                            } else {
                                if (accountHovers[i]) {
                                } else {
                                    cacheSprite[1178].drawAdvancedSprite(drawAccountX, 405);
                                }
                            }
                        } else {
                            cacheSprite[1452].drawAdvancedSprite(drawAccountX - 3, 405);
                            if (account.getHelmet() > 0) {
                                Sprite s = ItemDefinition.getSprite(account.getHelmet(), 0, 4, 100);
                                if (s != null) {
                                    s.drawSprite(drawAccountX, 405);
                                }
                            }
                            newSmallFont.drawCenteredString("" + account.getUsername(), drawAccountX + 15,
                                    455, 0xFFFFFF, 0);
                        }
                        drawAccountX += (i == 2 ? 98 : 97);

                    }
*/

             /*   } else {
                    handleHovers(true);
                    cacheSprite[1183].drawAdvancedSprite(0, 0);
                    cacheSprite[1181].drawAdvancedSprite(215, 150);
                    chatTextDrawingArea.drawRegularText(true, 240, 16777215, loginMessage1, 242);
                    chatTextDrawingArea.drawRegularText(true, 240, 16777215, loginMessage2, 262);
                }
*/

                if (!showTwoFactorAuth) {
                    for (int i = 0; i < loginMessages.length; i++) {
                        String s = loginMessages[i];
                        if (s == null || s.length() == 0) {
                            continue;
                        }
                        int y = clientHeight / 2 + i * 20 + 225;
                        chatTextDrawingArea.drawRegularText(true, clientWidth / 2 - chatTextDrawingArea.getTextWidth(s) / 2, 16777215, s, y);
                    }
                } else if (showCaptcha) {
                    String s = "Enter the captcha image below.";
                    chatTextDrawingArea.drawRegularText(true, (clientWidth - chatTextDrawingArea.getTextWidth(s)) / 2, 16777215,
                            s, 350);
                    drawColorBox(0xFFFFFF, (clientWidth - captcha.myWidth) / 2, 240, captcha.myWidth, captcha.myHeight + 10);
                    captcha.drawAdvancedSprite((clientWidth - captcha.myWidth) / 2, 245);

                    if (otherHover) {
                        newRegularFont.drawCenteredString("Back", 285, 230, 0xFFD700, 0);
                    } else {
                        newRegularFont.drawCenteredString("Back", 285, 230, 0xffffff, 0);
                    }
                }
                // normalText.drawText(0xffffff, "MouseX: "+mouseX+", MouseY: "+mouseY, 80,
                // 200);
            }
        }

        if (!resizing) {
            titleScreenIP.drawGraphics(canvas.getGraphics(), 0, 0);
        }
        if (welcomeScreenRaised) {
            welcomeScreenRaised = false;
        }
    }


    public void drawColorBox(int color, int xPos, int yPos, int width, int height) {
        DrawingArea.fillRectangle(color, yPos, width, height, 256, xPos);
    }


    public int getXTextOffset(int index) {
        switch (index) {
            case 1:
                return 280;
            case 2:
                return 351;
            case 3:
                return 427;
            case 4:
                return 495;
        }
        return 206;
    }

    private void handleAccountHeadRotation() {
        for (int i = 0; i < accountManager.getAccounts().length; i++) {
            RSInterface rsi = RSInterface.interfaceCache[31001 + i];
            if (rsi == null)
                continue;
            int x = RSInterface.interfaceCache[31000].childX[i];
            int y = RSInterface.interfaceCache[31000].childY[i];
            int diffX = mouseX - x;
            int diffY = y - mouseY;
            if (mouseX <= 0 && mouseY <= 0)
                return;
            if (diffY <= -100)
                diffY = -100;
            if (diffY >= 100)
                diffY = 100;
            if (diffX < 0)
                diffX = (-diffX);
            else
                diffX = 2000 - diffX;
            if (diffY < 0)
                diffY = (-diffY);
            else
                diffY = 2000 - diffY;

            if (diffX >= 2000)
                diffX = 2000;
            if (diffX <= 0)
                diffX = 0;

            if (diffY >= 2000)
                diffY = 2000;
            if (diffY <= 0)
                diffY = 0;
            rsi.modelRotationX = diffX;
            rsi.modelRotationY = diffY;
        }
    }

    private void drawLogo() {
        resetImageProducers();
        System.gc();
    }

    public void drawMenu() {
        int xPos = menuOffsetX;
        int yPos = menuOffsetY;
        int menuW = menuWidth;
        int x = super.mouseX;
        int y = super.mouseY;
        int menuH = menuHeight + 1;
        if (menuScreenArea == 1 && GameFrame.getScreenMode() != ScreenMode.FIXED) {
            xPos += 519;// +extraWidth;
            yPos += 168;// +extraHeight;
        }
        if (menuScreenArea == 2 && GameFrame.getScreenMode() != ScreenMode.FIXED) {
            yPos += 338;
        }
        if (menuScreenArea == 3 && GameFrame.getScreenMode() != ScreenMode.FIXED) {
            xPos += 515;
            yPos += 0;
        }
        if (menuScreenArea == 0) {
            x -= 4;
            y -= 4;
        }
        if (menuScreenArea == 1) {
            if (!(GameFrame.getScreenMode() != ScreenMode.FIXED)) {
                x -= 519;
                y -= 168;
            }
        }
        if (menuScreenArea == 2) {
            if (!(GameFrame.getScreenMode() != ScreenMode.FIXED)) {
                x -= 17;
                y -= 338;
            }
        }
        if (menuScreenArea == 3 && !(GameFrame.getScreenMode() != ScreenMode.FIXED)) {
            x -= 515;
            y -= 0;
        }
        DrawingArea.drawPixels(menuH - 4, yPos + 2, xPos, 0x706a5e, menuW);
        DrawingArea.drawPixels(menuH - 2, yPos + 1, xPos + 1, 0x706a5e, menuW - 2);
        DrawingArea.drawPixels(menuH, yPos, xPos + 2, 0x706a5e, menuW - 4);
        DrawingArea.drawPixels(menuH - 2, yPos + 1, xPos + 3, 0x2d2822, menuW - 6);
        DrawingArea.drawPixels(menuH - 4, yPos + 2, xPos + 2, 0x2d2822, menuW - 4);
        DrawingArea.drawPixels(menuH - 6, yPos + 3, xPos + 1, 0x2d2822, menuW - 2);
        DrawingArea.drawPixels(menuH - 22, yPos + 19, xPos + 2, 0x524a3d, menuW - 4);
        DrawingArea.drawPixels(menuH - 22, yPos + 20, xPos + 3, 0x524a3d, menuW - 6);
        DrawingArea.drawPixels(menuH - 23, yPos + 20, xPos + 3, 0x2b271c, menuW - 6);
        DrawingArea.fillPixels(xPos + 3, menuW - 6, 1, 0x2a291b, yPos + 2);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x2a261b, yPos + 3);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x252116, yPos + 4);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x211e15, yPos + 5);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x1e1b12, yPos + 6);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x1a170e, yPos + 7);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 2, 0x15120b, yPos + 8);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x100d08, yPos + 10);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 11);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x080703, yPos + 12);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 13);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x070802, yPos + 14);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 15);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x070802, yPos + 16);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 17);
        DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x2a291b, yPos + 18);
        DrawingArea.fillPixels(xPos + 3, menuW - 6, 1, 0x564943, yPos + 19);
        newBoldFont.drawBasicString("Choose Option", xPos + 3, yPos + 14, 0xc6b895, 0, true);
        int beforeX = xPos;
        for (int index = 0; index < menuActionRow; index++) {
            int yOffset = yPos + 31 + (menuActionRow - 1 - index) * 15;
            int color = 0xffffff;
            if (x > xPos && x < xPos + menuW && y > yOffset - 11 && y < yOffset + 5) {
                DrawingArea.drawPixels(15, yOffset - 11, xPos + 3, 0x6f695d, menuWidth - 6);
                detectCursor(menuActionName[index]);
                color = 0xffff00;
                currentActionMenu = index;
            }
            xPos += 3;
            newBoldFont.drawBasicString(menuActionName[index], xPos, yOffset + 1, color, 0, true);
            xPos = beforeX;
        }
    }

    public void detectCursor(String tooltip) {
        if (!Configuration.NEW_CURSORS)
            return;
        if (tooltip == null || tooltip.isEmpty())
            return;
        CursorData newCursor = null;
        tooltip = tooltip.replaceAll("@gre@", "");
        tooltip = tooltip.replaceAll("@yel@", "");
        for (CursorData cursorData : CursorData.values()) {
            if (tooltip.startsWith(cursorData.tooltip)) {
                newCursor = cursorData;
                break;
            }
        }
        if (newCursor == null) {
            newCursor = CursorData.CURSOR_0;
        }
        super.setCursor(newCursor);
    }

    private void drawMinimap() {
        mapArea.setxPos(GameFrame.getScreenMode() == ScreenMode.FIXED ? 519 : getScreenWidth() - 170);
        mapArea.setyPos(GameFrame.getScreenMode() == ScreenMode.FIXED ? 0 : 1);
        mapArea.render(this);
    }

    public void drawMinimapFlag(Sprite sprite, int j, int k) {
        int l = k * k + j * j;
        if (l > 4225 && l < 0x15f90) {
            int i1 = viewRotation + minimapRotation & 0x7ff;
            int j1 = Model.SINE[i1];
            int k1 = Model.COSINE[i1];
            j1 = j1 * 256 / (minimapZoom + 256);
            k1 = k1 * 256 / (minimapZoom + 256);
            int l1 = j * j1 + k * k1 >> 16;
            int i2 = j * k1 - k * j1 >> 16;
            double d = Math.atan2(l1, i2);
            int j2 = (int) (Math.sin(d) * 63D);
            int k2 = (int) (Math.cos(d) * 57D);
            mapEdge.rotate(83 - k2 - 20, d, 94 + j2 + 4 - 10);

        } else {
            markMinimap(sprite, k, j);
        }
    }


    private void drawScrollbarBottom(int barWidth, int scrollPos, int yPos, int xPos, int contentHeight,
                                     boolean newScroller, boolean isTransparent) {

        int backingAmount = (barWidth - 32) / 5;
        int scrollPartHeight = ((barWidth - 32) * barWidth) / contentHeight;

        if (scrollPartHeight < 10) {
            scrollPartHeight = 10;
        }

        int scrollPartAmount = (scrollPartHeight / 5) - 2;
        int scrollPartPos = ((barWidth - 32 - scrollPartHeight) * scrollPos) / (contentHeight - barWidth) + 16 + xPos;
        /* Bar fill */
        for (int i = 0, xxPos = xPos + 16; i <= backingAmount; i++, xxPos += 5) {
            cacheSprite[1108].drawSprite(xxPos, yPos);
        }
        /* Top of bar */
        cacheSprite[1107].drawSprite(scrollPartPos, yPos);//
        scrollPartPos += 5;
        /* Middle of bar */
        for (int i = 0; i <= scrollPartAmount; i++) {
            cacheSprite[1110].drawSprite(scrollPartPos, yPos);// scrollPartPos
            scrollPartPos += 5;
        }
        scrollPartPos = ((barWidth - 32 - scrollPartHeight) * scrollPos) / (contentHeight - barWidth) + 16 + xPos
                + (scrollPartHeight - 5);
        /* Bottom of bar */
        cacheSprite[1109].drawSprite(scrollPartPos, yPos);
        /* Arrows */
        cacheSprite[1112].drawSprite(xPos, yPos);

        cacheSprite[1111].drawSprite((xPos + barWidth) - 16, yPos);

    }


    public void drawScrollbar(int barHeight, int scrollPos, int yPos, int xPos, int contentHeight, boolean newScroller,
                              boolean isTransparent) {
        int backingAmount = (barHeight - 32) / 5;
        int scrollPartHeight = (barHeight - 32) * barHeight / contentHeight;
        int scrollerID;
        if (newScroller) {
            scrollerID = 4;
        } else if (isTransparent) {
            scrollerID = 8;
        } else {
            scrollerID = 0;
        }
        if (scrollPartHeight < 10) {
            scrollPartHeight = 10;
        }
        int scrollPartAmount = scrollPartHeight / 5 - 2;
        int scrollPartPos = (barHeight - 32 - scrollPartHeight) * scrollPos / (contentHeight - barHeight) + 16 + yPos;
        /* Bar fill */
        for (int i = 0, yyPos = yPos + 16; i <= backingAmount; i++, yyPos += 5) {
            scrollPart[scrollerID + 1].drawSprite(xPos, yyPos);
        }
        /* Top of bar */
        scrollPart[scrollerID + 2].drawSprite(xPos, scrollPartPos);
        scrollPartPos += 5;
        /* Middle of bar */
        for (int i = 0; i <= scrollPartAmount; i++) {
            scrollPart[scrollerID + 3].drawSprite(xPos, scrollPartPos);
            scrollPartPos += 5;
        }
        scrollPartPos = (barHeight - 32 - scrollPartHeight) * scrollPos / (contentHeight - barHeight) + 16 + yPos
                + scrollPartHeight - 5;
        /* Bottom of bar */
        scrollPart[scrollerID].drawSprite(xPos, scrollPartPos);
        /* Arrows */
        if (newScroller) {
            scrollBar[2].drawSprite(xPos, yPos);
            scrollBar[3].drawSprite(xPos, yPos + barHeight - 16);
        } else if (isTransparent) {
            scrollBar[4].drawSprite(xPos, yPos);
            scrollBar[5].drawSprite(xPos, yPos + barHeight - 16);
        } else {
            scrollBar[0].drawSprite(xPos, yPos);
            scrollBar[1].drawSprite(xPos, yPos + barHeight - 16);
        }
    }

    private void drawSplitPrivateChat() {
        if (splitPrivateChat == 0) {
            return;
        }
        TextDrawingArea textDrawingArea = normalText;
        int messages = 0;
        if (systemUpdateTimer != 0) {
            messages = 1;
        }

        if (broadcastMinutes != 0) {
            messages += 1;
        }
        for (int index = 0; index < 100; index++) {
            if (chatMessages[index] != null) {
                int type = chatTypes[index];
                String name = chatNames[index];
                String prefixName = name;
                int rights = 0;
                if (name != null) {
                    if (name.indexOf("@") == 0) {
                        int prefixSubstring = getPrefixSubstringLength(name);
                        name = name.substring(prefixSubstring);
                    }
                    if (name.indexOf("@") == 0) {
                        int prefixSubstring = getPrefixSubstringLength(name);
                        name = name.substring(prefixSubstring);
                    }
                }

                int paddingY = GameFrame.getScreenMode() == ScreenMode.FIXED ? 0 : getScreenHeight() - 500;

                if ((type == 3 || type == 7)
                        && (type == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(name))) {
                    int yOffset = 329 + paddingY - messages * 13;
                    int xOffset = 4;
                    textDrawingArea.method385(0, "From", yOffset, xOffset);
                    textDrawingArea.method385(ChatArea.SPLIT_CHAT_COLORS[splitChatColor], "From", yOffset - 1, xOffset);
                    xOffset += textDrawingArea.getTextWidth("From ");

                    if (prefixName != null && prefixName.indexOf("@") == 0) {
                        int substringLength = Client.getClient().getPrefixSubstringLength(prefixName);
                        rights = Client.getClient().getPrefixRights(prefixName.substring(0, prefixName.indexOf(name)),
                                substringLength == 6);
                    }

                    if (rights == 13 || rights == 15|| rights == 16) { //Zenyte Donator and hydrix
                        rights += 3;
                    }
                    if (rights == 14) { //manager
                        rights = 17;
                    }
                    if (rights != 0) {
                        modIcons[rights].drawTransparentSprite(xOffset, yOffset - 12, 255);
                        xOffset += modIcons[rights].maxWidth + 2;
                    }

                    textDrawingArea.method385(0, name + ": " + chatMessages[index], yOffset, xOffset);
                    textDrawingArea.method385(ChatArea.SPLIT_CHAT_COLORS[splitChatColor],
                            name + ": " + chatMessages[index], yOffset - 1, xOffset);

                    if (++messages >= 5) {
                        return;
                    }
                }

                if (type == 5 && privateChatMode < 2) {
                    int yOffset = 329 + paddingY - messages * 13;
                    textDrawingArea.method385(0, chatMessages[index], yOffset, 4);
                    textDrawingArea.method385(ChatArea.SPLIT_CHAT_COLORS[splitChatColor], chatMessages[index],
                            yOffset - 1, 4);

                    if (++messages >= 5) {
                        return;
                    }
                }

                if (type == 6 && privateChatMode < 2) {
                    int yOffset = 329 + paddingY - messages * 13;
                    int xOffset = 4;
                    textDrawingArea.method385(0, "To", yOffset, xOffset);
                    textDrawingArea.method385(ChatArea.SPLIT_CHAT_COLORS[splitChatColor], "To", yOffset - 1, xOffset);
                    xOffset += textDrawingArea.getTextWidth("To ");


                    if (rights == 14) { //manager
                        rights = 17;
                    }

                    if (rights != 0) {
                        modIcons[rights].drawTransparentSprite(xOffset, yOffset - 12, 255);
                        xOffset += 12;
                    }

                    textDrawingArea.method385(0, name + ": " + chatMessages[index], yOffset, xOffset);
                    textDrawingArea.method385(ChatArea.SPLIT_CHAT_COLORS[splitChatColor],
                            name + ": " + chatMessages[index], yOffset - 1, xOffset);

                    if (++messages >= 5) {
                        return;
                    }
                }
            }
        }
    }

    public void updateBankInterface() {
        int tabAmount = settings[2000] & 0xFF;
        int activeTab = settings[2000] >> 8 & 0xFF;
        Sprite activeTabSprite = cacheSprite[617];
        Sprite tabSprite = cacheSprite[613];
        Sprite newTabSprite = cacheSprite[614];
        Sprite emptySprite = new Sprite(0, 0);
        for (int i = 0; i < 9; i++) {
            if (i < tabAmount) {
                RSInterface.interfaceCache[41018 + i].disabledSprite = tabSprite;
                RSInterface.interfaceCache[41018 + i].tooltip = "Click here to select tab " + (i + 1);
            } else {
                RSInterface.interfaceCache[41018 + i].disabledSprite = emptySprite;
                RSInterface.interfaceCache[41018 + i].tooltip = "";
            }
            RSInterface.interfaceCache[41027 + i].inv[0] = -1;
            RSInterface.interfaceCache[41027 + i].invStackSizes[0] = 0;
        }
        if (tabAmount < 9) {
            RSInterface.interfaceCache[41018 + tabAmount].disabledSprite = newTabSprite;
            RSInterface.interfaceCache[41018 + tabAmount].tooltip = "Drag an item here to create a new tab";
        }
        RSInterface.interfaceCache[41018 + activeTab].disabledSprite = activeTabSprite;

    }

    public void processBankInterface() {
        if (openInterfaceID != 5292)
            return;
        boolean fixed = GameFrame.getScreenMode() == ScreenMode.FIXED;
        int offsetX = fixed ? 0 : (clientWidth - 765) / 2;
        int offsetY = fixed ? 0 : (clientHeight - 503) / 2;
        int[] offsets = {74, 121, 168, 215, 262, 309, 356, 403, 450};
        if (modifiedWidgetId == 5382 && super.mouseY >= 40 + offsetY && super.mouseY <= 77 + offsetY) {
            for (int i = 0; i < offsets.length; i++) {
                if (super.mouseX < offsets[i] + offsetX) {
                  /*  getOut().putOpcode(214);
                    getOut().writeSignedBigEndian(5383);
                    getOut().method424(0);
                    getOut().writeSignedBigEndian(anInt1085);
                    getOut().writeUnsignedWordBigEndian(i);*/
                    break;
                }
            }
        }
    }

    private void drawTabArea() {
        tabArea.setxPos(
                GameFrame.getScreenMode() == ScreenMode.FIXED ? 516 : getScreenWidth() - tabArea.getWidth() + 12);
        tabArea.setyPos(
                GameFrame.getScreenMode() == ScreenMode.FIXED ? 168 : getScreenHeight() - tabArea.getHeight() + 2);
        tabArea.render(this);
    }

    private void drawTooltip() {
        // XXX: here tooltip
        if (menuActionRow < 2 && itemSelected == 0 && spellSelected == 0) {
            if (Configuration.NEW_CURSORS) {
                super.setCursor(CursorData.CURSOR_0);
            }
            return;
        }
        String tooltip;
        if (itemSelected == 1 && menuActionRow < 2) {
            tooltip = "Use " + selectedItemName + " with...";
        } else if (spellSelected == 1 && menuActionRow < 2) {
            tooltip = spellTooltip + "...";
        } else {
            tooltip = menuActionName[menuActionRow - 1];
        }
        if (menuActionRow > 2) {
            tooltip = tooltip + "@whi@ / " + (menuActionRow - 2) + " more options";
        }
        newBoldFont.drawBasicString(tooltip, 4, 15, 0xFFFFFF, 0, true);
        // chatTextDrawingArea.method390(4, 0xffffff, tooltip, loopCycle / 1000,
        // 15);
        if (Configuration.NEW_CURSORS) {
            detectCursor(menuActionName[menuActionRow - 1]);
        }
    }

    private void dropClient() {
        if (anInt1011 > 0) {
            resetLogout();
            return;
        }
        if (gameScreenIP != null) {
            gameScreenIP.initDrawingArea();
        }
        // DrawingArea.fillPixels(2, 229, 39, 0xffffff, 2); // white box around
        // DrawingArea.drawPixels(37, 3, 3, 0, 227); // black fill
        // normalText.drawText(0, "Connection lost.", 19, 120);
        // normalText.drawText(0xffffff, "Connection lost.", 18, 119);
        // normalText.drawText(0, "Please wait - attempting to reestablish.", 34, 117);
        // normalText.drawText(0xffffff, "Please wait - attempting to reestablish.", 34,
        // 116);
        cacheSprite[1106].drawSprite(7, 4);
        if (!resizing && gameScreenIP != null) {
            gameScreenIP.drawGraphics(canvas.getGraphics(), gameScreenDrawX, gameScreenDrawY);
        }
        minimapStatus = 0;
        destX = 0;
        Connection rsSocket = getConnection();
        loggedIn = false;
        setLoginFailures(0);
        login(getPassword(), true, myUsername, this);

        if (!loggedIn) {
            resetLogout();
        }

        try {
            rsSocket.close();
        } catch (Exception _ex) {
        }
    }

    private int extractInterfaceValues(RSInterface class9, int j) {
        if (class9.valueIndexArray == null || j >= class9.valueIndexArray.length) {
            return -2;
        }

        try {
            int[] ai = class9.valueIndexArray[j];
            int k = 0;
            int l = 0;
            int i1 = 0;

            do {
                int j1 = ai[l++];
                int k1 = 0;
                byte byte0 = 0;
                if (j1 == 0) {
                    return k;
                }
                if (j1 == 1) {
                    k1 = currentStats[ai[l++]];
                }
                if (j1 == 2) {
                    k1 = maxStats[ai[l++]];
                }
                if (j1 == 3) {
                    k1 = currentExp[ai[l++]];
                }
                if (j1 == 4) {
                    RSInterface class9_1 = RSInterface.interfaceCache[ai[l++]];
                    int k2 = ai[l++];
                    if (k2 >= 0 && k2 < ItemDefinition.totalItems
                            && (!ItemDefinition.get(k2).membersObject || isMembers)) {
                        for (int j3 = 0; j3 < class9_1.inv.length; j3++) {
                            if (class9_1.inv[j3] == k2 + 1) {
                                k1 += class9_1.invStackSizes[j3];
                            }
                        }

                    }
                }
                if (j1 == 5 || j1 == 111) {
                    k1 = variousSettings[ai[l++]];
                }
                if (j1 == 6) {
                    k1 = anIntArray1019[maxStats[ai[l++]] - 1];
                }
                if (j1 == 7) {
                    k1 = variousSettings[ai[l++]] * 100 / 46875;
                }
                if (j1 == 8) {
                    k1 = myPlayer.combatLevel;
                }
                if (j1 == 9) {
                    for (int l1 = 0; l1 < Skills.SKILL_COUNT; l1++) {
                        if (Skills.SKILLS_ENABLED[l1]) {
                            k1 += maxStats[l1];
                        }
                    }

                }
                if (j1 == 10) {
                    RSInterface class9_2 = RSInterface.interfaceCache[ai[l++]];
                    int l2 = ai[l++] + 1;
                    if (l2 >= 0 && l2 < ItemDefinition.totalItems
                            && (!ItemDefinition.get(l2).membersObject || isMembers)) {
                        for (int element : class9_2.inv) {
                            if (element != l2) {
                                continue;
                            }
                            k1 = 0x3b9ac9ff;
                            break;
                        }

                    }
                }
                if (j1 == 11) {
                    k1 = energy;
                }
                if (j1 == 12) {
                    k1 = weight;
                }
                if (j1 == 13) {
                    int i2 = variousSettings[ai[l++]];
                    int i3 = ai[l++];
                    k1 = (i2 & 1 << i3) == 0 ? 0 : 1;
                }
                if (j1 == 14) {
                    int j2 = ai[l++];
                    VarBit varBit = VarBit.cache[j2];
                    int l3 = varBit.configId;
                    int i4 = varBit.configValue;
                    int j4 = varBit.anInt650;
                    int k4 = anIntArray1232[j4 - i4];
                    k1 = variousSettings[l3] >> i4 & k4;
                }
                if (j1 == 15) {
                    byte0 = 1;
                }
                if (j1 == 16) {
                    byte0 = 2;
                }
                if (j1 == 17) {
                    byte0 = 3;
                }
                if (j1 == 18) {
                    k1 = (myPlayer.x >> 7) + regionBaseX;
                }
                if (j1 == 19) {
                    k1 = (myPlayer.y >> 7) + regionBaseY;
                }
                if (j1 == 20) {
                    k1 = ai[l++];
                }
                if (byte0 == 0) {
                    if (i1 == 0) {
                        k += k1;
                    }
                    if (i1 == 1) {
                        k -= k1;
                    }
                    if (i1 == 2 && k1 != 0) {
                        k /= k1;
                    }
                    if (i1 == 3) {
                        k *= k1;
                    }
                    i1 = 0;
                } else {
                    i1 = byte0;
                }
            } while (true);
        } catch (Exception _ex) {
            return -1;
        }
    }

    public void generateWorld(int x, int y) {
        terrainRegionX = x;
        terrainRegionY = y;
        requestMapReconstruct = false;
        if (currentRegionX == x && currentRegionY == y && loadingStage == 2) {
            return;
        }
        currentRegionX = x;
        currentRegionY = y;
        regionBaseX = (currentRegionX - 6) * 8;
        regionBaseY = (currentRegionY - 6) * 8;
        aBoolean1141 = (currentRegionX / 8 == 48 || currentRegionX / 8 == 49) && currentRegionY / 8 == 48;
        if (currentRegionX / 8 == 48 && currentRegionY / 8 == 148) {
            aBoolean1141 = true;
        }
        loadingStage = 1;
        aLong824 = System.currentTimeMillis();
        int k16 = 0;
        for (int i21 = (currentRegionX - 6) / 8; i21 <= (currentRegionX + 6) / 8; i21++) {
            for (int k23 = (currentRegionY - 6) / 8; k23 <= (currentRegionY + 6) / 8; k23++) {
                k16++;
            }
        }
        terrainData = new byte[k16][];
        objectData = new byte[k16][];
        mapCoordinates = new int[k16];
        floorMap = new int[k16];
        objectMap = new int[k16];
        k16 = 0;
        for (int l23 = (currentRegionX - 6) / 8; l23 <= (currentRegionX + 6) / 8; l23++) {
            for (int j26 = (currentRegionY - 6) / 8; j26 <= (currentRegionY + 6) / 8; j26++) {
                mapCoordinates[k16] = (l23 << 8) + j26;
                if (aBoolean1141 && (j26 == 49 || j26 == 149 || j26 == 147 || l23 == 50 || l23 == 49 && j26 == 47)) {
                    floorMap[k16] = -1;
                    objectMap[k16] = -1;
                    k16++;
                } else {
                    int k28 = floorMap[k16] = onDemandFetcher.getMapCount(0, j26, l23);
                    if (k28 != -1) {
                        onDemandFetcher.requestFileData(3, k28);
                    }
                    int j30 = objectMap[k16] = onDemandFetcher.getMapCount(1, j26, l23);
                    if (j30 != -1) {
                        onDemandFetcher.requestFileData(3, j30);
                    }
                    k16++;
                }
            }
        }
        int i17 = regionBaseX - anInt1036;
        int j21 = regionBaseY - anInt1037;
        anInt1036 = regionBaseX;
        anInt1037 = regionBaseY;
        for (int j24 = 0; j24 < 16384; j24++) {
            NPC npc = npcArray[j24];
            if (npc != null) {
                for (int j29 = 0; j29 < 10; j29++) {
                    npc.smallX[j29] -= i17;
                    npc.smallY[j29] -= j21;
                }
                npc.x -= i17 * 128;
                npc.y -= j21 * 128;
            }
        }
        for (int i27 = 0; i27 < getMaxPlayers(); i27++) {
            Player player = playerArray[i27];
            if (player != null) {
                for (int i31 = 0; i31 < 10; i31++) {
                    player.smallX[i31] -= i17;
                    player.smallY[i31] -= j21;
                }
                player.x -= i17 * 128;
                player.y -= j21 * 128;
            }
        }
        aBoolean1080 = true;
        byte byte1 = 0;
        byte byte2 = 104;
        byte byte3 = 1;
        if (i17 < 0) {
            byte1 = 103;
            byte2 = -1;
            byte3 = -1;
        }
        byte byte4 = 0;
        byte byte5 = 104;
        byte byte6 = 1;
        if (j21 < 0) {
            byte4 = 103;
            byte5 = -1;
            byte6 = -1;
        }
        for (int k33 = byte1; k33 != byte2; k33 += byte3) {
            for (int l33 = byte4; l33 != byte5; l33 += byte6) {
                int i34 = k33 + i17;
                int j34 = l33 + j21;
                for (int k34 = 0; k34 < 4; k34++) {
                    if (i34 >= 0 && j34 >= 0 && i34 < 104 && j34 < 104) {
                        groundArray[k34][k33][l33] = groundArray[k34][i34][j34];
                    } else {
                        groundArray[k34][k33][l33] = null;
                    }
                }
            }
        }
        for (Class30_Sub1 class30_sub1_1 = (Class30_Sub1) getaClass19_1179()
                .reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1) getaClass19_1179()
                .reverseGetNext()) {
            class30_sub1_1.anInt1297 -= i17;
            class30_sub1_1.anInt1298 -= j21;
            if (class30_sub1_1.anInt1297 < 0 || class30_sub1_1.anInt1298 < 0 || class30_sub1_1.anInt1297 >= 104
                    || class30_sub1_1.anInt1298 >= 104) {
                class30_sub1_1.unlink();
            }
        }
        if (destX != 0) {
            destX -= i17;
            destY -= j21;
        }
        cameraViewChanged = false;
    }

    @Override
    public AppletContext getAppletContext() {
        if (Signlink.mainapp != null) {
            return Signlink.mainapp.getAppletContext();
        } else {
            return super.getAppletContext();
        }
    }

    private Archive getArchive(int index, String fileName, String cacheArchive, int crc, int loadingBarValue) {
        byte[] buffer = null;
        int timeToWait = 5;

        try {
            if (decompressors[0] != null) {
                buffer = decompressors[0].decompress(index);
            }
        } catch (Exception _ex) {
        }

        if (buffer != null) {
            if (Configuration.JAGCACHED_ENABLED) {
                crc32Instance.reset();
                crc32Instance.update(buffer);
                int crcValue = (int) crc32Instance.getValue();

                if (crcValue != crc) {
                    buffer = null;
                }
            }
        }

        if (buffer != null) {
            Archive streamLoader = new Archive(buffer);
            return streamLoader;
        }

        int errorCount = 0;

        while (buffer == null) {
            String error = "Unknown error";
            // drawSmoothLoading(loadingBarValue, "Requesting " + fileName);

            try {
                int lastPercentage = 0;
                DataInputStream datainputstream = openJagGrabInputStream(cacheArchive + crc);
                byte temp[] = new byte[6];
                datainputstream.readFully(temp, 0, 6);
                ByteBuffer stream = new ByteBuffer(temp);
                stream.position = 3;
                int totalLength = stream.getTribyte() + 6;
                int currentLength = 6;
                buffer = new byte[totalLength];
                System.arraycopy(temp, 0, buffer, 0, 6);

                while (currentLength < totalLength) {
                    int remainingAmount = totalLength - currentLength;

                    if (remainingAmount > 1000) {
                        remainingAmount = 1000;
                    }

                    int remaining = datainputstream.read(buffer, currentLength, remainingAmount);

                    if (remaining < 0) {
                        error = "Length error: " + currentLength + "/" + totalLength;
                        throw new IOException("EOF");
                    }

                    currentLength += remaining;
                    int percentage = currentLength * 100 / totalLength;

                    if (percentage != lastPercentage) {
                        // drawSmoothLoading(loadingBarValue, "Loading " + fileName + " - " + percentage
                        // + "%");
                    }

                    lastPercentage = percentage;
                }

                datainputstream.close();

                try {
                    if (decompressors[0] != null) {
                        decompressors[0].method234(buffer.length, buffer, index);
                    }
                } catch (Exception _ex) {
                    decompressors[0] = null;
                }

                if (buffer != null) {
                    if (Configuration.JAGCACHED_ENABLED) {
                        crc32Instance.reset();
                        crc32Instance.update(buffer);
                        int currentCrc = (int) crc32Instance.getValue();

                        if (currentCrc != crc) {
                            buffer = null;
                            errorCount++;
                            error = "Checksum error: " + currentCrc;
                        }
                    }
                }
            } catch (IOException ioexception) {
                if (error.equals("Unknown error")) {
                    error = "Connection error";
                }

                buffer = null;
            } catch (NullPointerException _ex) {
                error = "Null error";
                buffer = null;

                if (!Signlink.reporterror) {
                    return null;
                }
            } catch (ArrayIndexOutOfBoundsException _ex) {
                error = "Bounds error";
                buffer = null;

                if (!Signlink.reporterror) {
                    return null;
                }
            } catch (Exception _ex) {
                error = "Unexpected error";
                buffer = null;

                if (!Signlink.reporterror) {
                    return null;
                }
            }
            if (buffer == null) {
                for (int seconds = timeToWait; seconds > 0; seconds--) {
                    if (errorCount >= 3) {
                        // drawSmoothLoading(loadingBarValue, "Game updated - please reload page");
                        seconds = 10;
                    } else {
                        throw new RuntimeException("Unable to find archive: " + name);
                    }

                    try {
                        Thread.sleep(1000L);
                    } catch (Exception _ex) {
                    }
                }

                timeToWait *= 2;

                if (timeToWait > 60) {
                    timeToWait = 60;
                }

                httpFallback = !httpFallback;
            }
        }

        Archive archive = new Archive(buffer);
        return archive;
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL(Configuration.SERVER_HOST() + ":" + (80 + portOff));
        } catch (Exception _ex) {
        }
        return null;
    }

    public String getDocumentBaseHost() {
        return Signlink.mainapp != null ? Signlink.mainapp.getDocumentBase().getHost().toLowerCase() : "";
    }

    private int getExperienceForLevel(int level) {
        int points = 0;
        int output = 0;

        for (int lvl = 1; lvl <= level; lvl++) {
            points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));

            if (lvl >= level) {
                return output;
            }

            output = (int) Math.floor(points / 4);
        }

        return 0;
    }

    @Override
    public Container getGameComponent() {
        if (Signlink.mainapp != null) {
            return Signlink.mainapp;
        }
        return this;
    }

    @Override
    public String getParameter(String s) {
        if (Signlink.mainapp != null) {
            return Signlink.mainapp.getParameter(s);
        } else {
            return super.getParameter(s);
        }
    }

    public String getPrayerBook() {
        return tabInterfaceIDs[5] == 5608 ? "Prayers" : "Curses";
    }

    private String getPrefix(int rights) {
        String prefix = "cr";

        if (rights > 10) {
            prefix = "c";
        }

        return "@" + prefix + rights + "@";
    }

    public int getPrefixSubstringLength(String prefix) {
        if (prefix == null)
            return 5;
        return prefix.contains("cr10") || prefix.contains("cr11") ? 6 : 5;
    }

    public byte getPrefixRights(String prefix, boolean highRights) {
        byte rights = 0;
        int start = 3;
        int end = highRights ? 5 : 4;
        if (!prefix.contains("cr")) {
            start = 2;
        }
        try {
            rights = (byte) Integer.parseInt(prefix.substring(start, end));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rights;
    }

    public void mouseWheelDragged(int i, int j) {
        if (!mouseWheelDown)
            return;

        this.cameraRotationLeft += i * 3;
        this.cameraRotationRight += (j << 1);
    }

    private boolean inCircle(int circleX, int circleY, int clickX, int clickY, int radius) {
        return Math.pow(circleX + radius - clickX, 2) + Math.pow(circleY + radius - clickY, 2) < Math.pow(radius, 2);
    }

    @Override
    public void init() {
        try {
            nodeID = 10;
            portOff = 0;
            setHighDetail();
            isMembers = true;
            Signlink.storeid = 32;
            instance = this;
            Signlink.startpriv(InetAddress.getLocalHost());
            GameFrame.setScreenMode(ScreenMode.FIXED);
            initApplet(765, 503);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    public boolean inSprite(boolean Click, Sprite sprite, int xCoord, int yCoord) {
        if (Click && super.clickMode3 != 1) {
            return false;
        }

        return (Click ? saveClickX : mouseX) >= xCoord && (Click ? saveClickX : mouseX) <= xCoord + sprite.myWidth
                && (Click ? saveClickY : mouseY) >= yCoord && (Click ? saveClickY : mouseY) <= yCoord + sprite.myHeight;
    }

    private String interfaceIntToString(int j) {
        if (j < 0x3B9AC9FF) {
            return String.valueOf(j);
        } else {
            return "*";
        }
    }

    private boolean interfaceIsSelected(RSInterface class9) {
        if (class9.valueCompareType == null) {
            return false;
        }

        for (int i = 0; i < class9.valueCompareType.length; i++) {
            int j = extractInterfaceValues(class9, i);
            int k = class9.requiredValues[i];

            if (class9.valueCompareType[i] == 2) {
                if (j >= k) {
                    return false;
                }
            } else if (class9.valueCompareType[i] == 3) {
                if (j <= k) {
                    return false;
                }
            } else if (class9.valueCompareType[i] == 4) {
                if (j == k) {
                    return false;
                }
            } else if (j != k) {
                return false;
            }
        }

        return true;
    }

    public boolean isFriendOrSelf(String name) {
        if (name == null) {
            return false;
        }

        if (name.contains("@")) {
            name = NAME_PATTERN.matcher(name).replaceAll("");
        }

        for (int i = 0; i < friendCount; i++) {
            if (name.equalsIgnoreCase(friendsList[i])) {
                return true;
            }
        }

        return name.equalsIgnoreCase(myPlayer.name);
    }

    public boolean isGameFrameVisible() {
        return gameFrameVisible;
    }

    public boolean isWebclient() {
        return super.gameFrame == null;
    }


    private void launchURL(String url) {
        String osName = System.getProperty("os.name");

        try {
            if (osName.startsWith("Mac OS")) {
                Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[]{String.class});
                openURL.invoke(null, new Object[]{url});
            } else if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else {
                // assume Unix or Linux
                String[] browsers = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape", "safari"};
                String browser = null;

                for (int count = 0; count < browsers.length && browser == null; count++) {
                    if (Runtime.getRuntime().exec(new String[]{"which", browsers[count]}).waitFor() == 0) {
                        browser = browsers[count];
                    }
                }

                if (browser == null) {
                    throw new Exception("Could not find web browser");
                } else {
                    Runtime.getRuntime().exec(new String[]{browser, url});
                }
            }
        } catch (Exception e) {
            pushMessage("Failed to open URL.", 0, "");
        }
    }

    private void loadingStages() {
        if (isLowDetail() && loadingStage == 2 && ObjectManager.anInt131 != plane) {
            gameScreenIP.initDrawingArea();
            cacheSprite[1105].drawSprite(8, 9);
            if (!resizing) {
                gameScreenIP.drawGraphics(canvas.getGraphics(), gameScreenDrawX, gameScreenDrawY);
            }
            loadingStage = 1;
            aLong824 = System.currentTimeMillis();
        }

        if (loadingStage == 1) {
            int j = getMapLoadingState();
            if (j != 0 && System.currentTimeMillis() - aLong824 > 0x57e40L) {
                Signlink.reportError(myUsername + " glcfb " + getServerSeed() + "," + j + "," + isLowDetail() + ","
                        + decompressors[0] + "," + onDemandFetcher.getRemaining() + "," + plane + "," + currentRegionX
                        + "," + currentRegionY);
                aLong824 = System.currentTimeMillis();
            }
        }

        if (loadingStage == 2 && plane != getLastKnownPlane()) {
            setLastKnownPlane(plane);
            renderedMapScene(plane);
        }
    }

    private void loadTitleScreen() {
        /*
         * byte abyte0[] = titleStreamLoader.getDataForName("title.dat"); backGround =
         * new Sprite(abyte0, this); aBackground_966 = new Background(titleStreamLoader,
         * "titlebox", 0); aBackground_967 = new Background(titleStreamLoader,
         * "titlebutton", 0); aBackgroundArray1152s = new Background[12]; int j = 0; try
         * { j = Integer.parseInt(getParameter("fl_icon")); } catch (Exception _ex) { }
         * if (j == 0) { for (int k = 0; k < 12; k++) aBackgroundArray1152s[k] = new
         * Background(titleStreamLoader, "runes", k);
         *
         * } else { for (int l = 0; l < 12; l++) aBackgroundArray1152s[l] = new
         * Background(titleStreamLoader, "runes", 12 + (l & 3));
         *
         * } new Sprite(128, 265); new Sprite(128, 265);
         *
         * anIntArray851 = new int[256]; for (int k1 = 0; k1 < 64; k1++)
         * anIntArray851[k1] = k1 * 0x40000;
         *
         * for (int l1 = 0; l1 < 64; l1++) anIntArray851[l1 + 64] = 0xff0000 + 1024 *
         * l1;
         *
         * for (int i2 = 0; i2 < 64; i2++) anIntArray851[i2 + 128] = 0xffff00 + 4 * i2;
         *
         * for (int j2 = 0; j2 < 64; j2++) anIntArray851[j2 + 192] = 0xffffff;
         *
         * anIntArray852 = new int[256]; for (int k2 = 0; k2 < 64; k2++)
         * anIntArray852[k2] = k2 * 1024;
         *
         * for (int l2 = 0; l2 < 64; l2++) anIntArray852[l2 + 64] = 65280 + 4 * l2;
         *
         * for (int i3 = 0; i3 < 64; i3++) anIntArray852[i3 + 128] = 65535 + 0x40000 *
         * i3;
         *
         * for (int j3 = 0; j3 < 64; j3++) anIntArray852[j3 + 192] = 0xffffff;
         *
         * anIntArray853 = new int[256]; for (int k3 = 0; k3 < 64; k3++)
         * anIntArray853[k3] = k3 * 4;
         *
         * for (int l3 = 0; l3 < 64; l3++) anIntArray853[l3 + 64] = 255 + 0x40000 * l3;
         *
         * for (int i4 = 0; i4 < 64; i4++) anIntArray853[i4 + 128] = 0xff00ff + 1024 *
         * i4;
         *
         * for (int j4 = 0; j4 < 64; j4++) anIntArray853[j4 + 192] = 0xffffff;
         *
         * anIntArray1190 = new int[32768]; anIntArray1191 = new int[32768];
         */
        // randomizeBackground(null);
        // drawSmoothLoading(10, "Connecting to fileserver");
    }

    private void loginScreenBG(boolean b) {
        xCameraPos = 6100;
        yCameraPos = 6867;
        zCameraPos = -750;
        xCameraCurve = 2040;
        yCameraCurve = 383;
        resetWorld(0);

        if (b || getScriptManager() == null) {
            setScriptManager(new ScriptManager(this));
        } else {
            getScriptManager().update();
        }

        plane = getScriptManager().regionPlane;
        generateWorld(getScriptManager().terrainRegionX, getScriptManager().terrainRegionY);
        resetWorld(1);
    }

    private void magicOnItem(int id) {
        spellSelected = 1;
        spellID = id;
        selectedSpellId = id;
        spellUsableOn = 16;
        itemSelected = 0;
        spellTooltip = "Cast on";
        tabID = 3;
        tabAreaAltered = true;
    }

    private void mainGameProcessor() {
        if (openInterfaceID == 24600 && !getGrandExchange().searching && interfaceButtonAction != 1558
                && interfaceButtonAction != 1557 && inputDialogState != 1 && inputDialogState != 4) {
            inputDialogState = 0;
        }
        if (systemUpdateTimer > 1) {
            systemUpdateTimer--;
        }

        if (broadcastMinutes >= 1) {
            broadcastMinutes--;
        }

        if (anInt1011 > 0) {
            anInt1011--;
        }

        for (int j = 0; j < 100; j++) {
            if (!parsePacket()) {
                break;
            }
        }

        if (!loggedIn) {
            return;
        }
        casketOpening.spin();
        cardPack.spin();

        if (((vengTimer != -1)) && (System.currentTimeMillis() - lastUpdate > 1000L)) {
            if (vengTimer != -1) {
                vengTimer -= 1;
            }
            lastUpdate = System.currentTimeMillis();
        }

        synchronized (mouseDetection.locker) {
            if (!flagged) {
                mouseDetection.coordsIndex = 0;
            }
        }

        if (anInt1016 > 0) {
            anInt1016--;
        }

        if (super.keyArray[1] == 1 || super.keyArray[2] == 1 || super.keyArray[3] == 1 || super.keyArray[4] == 1) {
            aBoolean1017 = true;
        }

        if (aBoolean1017 && anInt1016 <= 0) {
            anInt1016 = 20;
            aBoolean1017 = false;
        }

        if (super.awtFocus && !aBoolean954) {
            aBoolean954 = true;
            getOut().putOpcode(3);
            getOut().putByte(1);
        }

        if (!super.awtFocus && aBoolean954) {
            aBoolean954 = false;
            getOut().putOpcode(3);
            getOut().putByte(0);
        }

        loadingStages();
        method115();
        method90();
        anInt1009++;

        if (anInt1009 > 750) {
            dropClient();
        }

        updatePlayerInstances();
        readNPCUpdateBlockForced();
        processTextCycles();

        if (tabID >= 0 && tabInterfaceIDs[tabID] == 29322) {
            if (!PetSystem.isPetAnimationRunning) {
                PetSystem.updateAnimations();
            }
        }

        anInt945++;

        if (crossType != 0) {
            crossIndex += 20;

            if (crossIndex >= 400) {
                crossType = 0;
            }
        }

        if (atInventoryInterfaceType != 0) {
            atInventoryLoopCycle++;

            if (atInventoryLoopCycle >= 15) {
                if (atInventoryInterfaceType == 3) {
                    setInputTaken(true);
                }
                atInventoryInterfaceType = 0;
            }
        }

        if (activeInterfaceType != 0) {
            anInt989++;

            if (super.mouseX > anInt1087 + 5 || super.mouseX < anInt1087 - 5 || super.mouseY > anInt1088 + 5
                    || super.mouseY < anInt1088 - 5) {
                aBoolean1242 = true;
            }

            if (super.getClickMode2() == 0) {
                if (activeInterfaceType == 3) {
                    setInputTaken(true);
                }

                activeInterfaceType = 0;

                if (aBoolean1242 && anInt989 >= 10) {
                    bankItemDragSprite = null;
                    lastActiveInvInterface = -1;
                    processRightClick();
                    processBankInterface();

                    bankItemDragSprite = null;
                    int x =  GameFrame.getScreenMode() == ScreenMode.FIXED ? 0 : clientWidth / 2 - 360;
                    int y =  GameFrame.getScreenMode() == ScreenMode.FIXED ? 40 :  40 + clientHeight / 2 - 235;
                    if (modifiedWidgetId == 5382 && super.mouseY >= y && super.mouseY <= y + 37) {// check
                        // if
                        // bank
                        // interface
                        if (super.mouseX >= 28 + x && super.mouseX <= 74 + x) {// tab
                            // 1
                            getOut().putOpcode(214);
                            getOut().method433(5);// 5 = maintab
                            getOut().method424(0);
                            getOut().method433(anInt1085);// Selected item slot
                            getOut().method431(mouseInvInterfaceIndex);// unused

                        }
                        if (super.mouseX >= 75 + x && super.mouseX <= 121 + x) {// tab
                            // 1
                            getOut().putOpcode(214);
                            getOut().method433(13);// tab # x 13 (originally
                            // movewindow)
                            getOut().method424(0);
                            getOut().method433(anInt1085);// Selected item slot
                            getOut().method431(mouseInvInterfaceIndex);// unused

                        }
                        if (super.mouseX >= 122 + x && super.mouseX <= 168 + x) {// tab
                            // 2
                            getOut().putOpcode(214);
                            getOut().method433(26);// tab # x 13 (originally
                            // movewindow)
                            getOut().method424(0);
                            getOut().method433(anInt1085);// Selected item slot
                            getOut().method431(mouseInvInterfaceIndex);// unused

                        }
                        if (super.mouseX >= 169 + x && super.mouseX <= 215 + x) {// tab
                            // 3
                            getOut().putOpcode(214);
                            getOut().method433(39);// tab # x 13 (originally
                            // movewindow)
                            getOut().method424(0);
                            getOut().method433(anInt1085);// Selected item slot
                            getOut().method431(mouseInvInterfaceIndex);// unused

                        }
                        if (super.mouseX >= 216 + x && super.mouseX <= 262 + x) {// tab
                            // 4
                            getOut().putOpcode(214);
                            getOut().method433(52);// tab # x 13 (originally
                            // movewindow)
                            getOut().method424(0);
                            getOut().method433(anInt1085);// Selected item slot
                            getOut().method431(mouseInvInterfaceIndex);// unused

                        }
                        if (super.mouseX >= 263 + x && super.mouseX <= 309 + x) {// tab
                            // 5
                            getOut().putOpcode(214);
                            getOut().method433(65);// tab # x 13 (originally
                            // movewindow)
                            getOut().method424(0);
                            getOut().method433(anInt1085);// Selected item slot
                            getOut().method431(mouseInvInterfaceIndex);// unused

                        }
                        if (super.mouseX >= 310 + x && super.mouseX <= 356 + x) {// tab
                            // 6
                            getOut().putOpcode(214);
                            getOut().method433(78);// tab # x 13 (originally
                            // movewindow)
                            getOut().method424(0);
                            getOut().method433(anInt1085);// Selected item slot
                            getOut().method431(mouseInvInterfaceIndex);// unused

                        }
                        if (super.mouseX >= 357 + x && super.mouseX <= 403 + x) {// tab
                            // 7
                            getOut().putOpcode(214);
                            getOut().method433(91);// tab # x 13 (originally
                            // movewindow)
                            getOut().method424(0);
                            getOut().method433(anInt1085);// Selected item slot
                            getOut().method431(mouseInvInterfaceIndex);// unused

                        }
                        if (super.mouseX >= 404 + x && super.mouseX <= 450 + x) {// tab
                            // 8
                            getOut().putOpcode(214);
                            getOut().method433(104);// tab # x 13 (originally
                            // movewindow)
                            getOut().method424(0);
                            getOut().method433(anInt1085);// Selected item slot
                            getOut().method431(mouseInvInterfaceIndex);// unused

                        }
                    }

                    if (lastActiveInvInterface == modifiedWidgetId && mouseInvInterfaceIndex != anInt1085) {
                        RSInterface class9 = RSInterface.interfaceCache[modifiedWidgetId];
                        int j1 = 0;
                        if (anInt913 == 1 && class9.contentType == 206) {
                            j1 = 1;
                        }
                        if (class9.inv[mouseInvInterfaceIndex] <= 0) {
                            j1 = 0;
                        }
                        if (class9.dragDeletes) {
                            int l2 = anInt1085;
                            int l3 = mouseInvInterfaceIndex;
                            class9.inv[l3] = class9.inv[l2];
                            class9.invStackSizes[l3] = class9.invStackSizes[l2];
                            class9.inv[l2] = -1;
                            class9.invStackSizes[l2] = 0;
                        } else if (j1 == 1) {
                            int i3 = anInt1085;
                            for (int i4 = mouseInvInterfaceIndex; i3 != i4; ) {
                                if (i3 > i4) {
                                    class9.swapInventoryItems(i3, i3 - 1);
                                    i3--;
                                } else if (i3 < i4) {
                                    class9.swapInventoryItems(i3, i3 + 1);
                                    i3++;
                                }
                            }

                        } else {
                            class9.swapInventoryItems(anInt1085, mouseInvInterfaceIndex);
                        }
                        getOut().putOpcode(214);
                        getOut().writeSignedBigEndian(modifiedWidgetId);
                        getOut().method424(j1);
                        getOut().writeSignedBigEndian(anInt1085);
                        getOut().writeUnsignedWordBigEndian(mouseInvInterfaceIndex);
                    }


                } else if ((anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2) {
                    determineMenuSize();
                } else if (menuActionRow > 0) {
                    doAction(menuActionRow - 1);
                }

                atInventoryLoopCycle = 10;
                super.clickMode3 = 0;
            }
        }

        if (WorldController.anInt470 != -1) {
            int k = WorldController.anInt470;
            int k1 = WorldController.anInt471;

            if ((myRights == 3 || myRights == 4) && controlShiftTeleporting) {
                teleport(regionBaseX + k, regionBaseY + k1);
                WorldController.anInt470 = -1;
            } else {
                boolean flag = doWalkTo(0, 0, 0, 0, myPlayer.smallY[0], 0, 0, k1, myPlayer.smallX[0], true, k);
                WorldController.anInt470 = -1;

                if (flag) {
                    crossX = super.saveClickX;
                    crossY = super.saveClickY;
                    crossType = 1;
                    crossIndex = 0;
                }
            }
        }

        if (super.clickMode3 == 1 && aString844 != null) {
            aString844 = null;
            setInputTaken(true);
            super.clickMode3 = 0;
        }

        if (!processMenuClick()) {
            processMainScreenClick();
            tabArea.processTabClick(this, GameFrame.getScreenMode());
        }

        if (super.getClickMode2() == 1 || super.clickMode3 == 1) {
            anInt1213++;
        }

        if (anInt1500 != 0 || anInt1044 != 0 || anInt1129 != 0) {
            if (anInt1501 < 30 && !menuOpen) {
                if (++anInt1501 == 30) {
                    if (anInt1500 != 0) {
                        inputTaken = true;
                    }
                }
            }
        } else if (anInt1501 > 0) {
            anInt1501--;
        }
        // System.out.println(":anInt150: "+anInt1500);

        if (PlayerHandler.quedBalloonX > -1 && PlayerHandler.quedBalloonY > -1) {
            int x = (myPlayer.x >> 7) + regionBaseX;
            int y = (myPlayer.y >> 7) + regionBaseY;

            if (PlayerHandler.quedBalloonX == x && PlayerHandler.quedBalloonY == y) {
                getOut().putOpcode(159);
                getOut().putShort(PlayerHandler.quedBalloonX);
                getOut().putShort(PlayerHandler.quedBalloonY);
                PlayerHandler.quedBalloonX = PlayerHandler.quedBalloonY = -1;
            }
        }

        if (loadingStage == 2) {
            method108();
        }

        if (loadingStage == 2 && cameraViewChanged) {
            calcCameraPos();
        }

        for (int i1 = 0; i1 < 5; i1++) {
            anIntArray1030[i1]++;
        }

        manageTextInput();
        super.idleTime++;

        if (super.idleTime > 7500) {
            anInt1011 = 250;
            super.idleTime = 0;
            getOut().putOpcode(202);
        }

        if (cameraOffsetX < -50) {
        }

        if (cameraOffsetX > 50) {
        }

        if (cameraOffsetY < -55) {
        }

        if (cameraOffsetY > 55) {
        }

        if (viewRotationOffset < -40) {
        }

        if (viewRotationOffset > 40) {
        }

        if (minimapRotation < -60) {
        }

        if (minimapRotation > 60) {
        }

        if (minimapZoom < -20) {
        }

        if (minimapZoom > 10) {
        }

        if (++anInt1010 > 50) {
            getOut().putOpcode(0);
        }

        try {
            if (getConnection() != null && getOut().position > 0) {
                getConnection().queueBytes(getOut().position, getOut().buffer);
                getOut().position = 0;
                anInt1010 = 0;
            }
        } catch (IOException _ex) {
            dropClient();
        } catch (Exception exception) {
            resetLogout();
        }
    }

    public void markMinimap(Sprite sprite, int x, int y) {
        if (sprite == null) {
            return;
        }

        boolean fixed = GameFrame.getScreenMode() == ScreenMode.FIXED;
        int rotation = viewRotation + minimapRotation & 0x7ff;
        int xPadding = mapArea.getOffSetX();
        int yPadding = mapArea.getyPos();
        int distance = x * x + y * y;

        if (distance > 6400) {
            return;
        }

        int spriteX = Model.SINE[rotation];
        int spriteY = Model.COSINE[rotation];
        spriteX = spriteX * 256 / (minimapZoom + 256);
        spriteY = spriteY * 256 / (minimapZoom + 256);
        int drawX = y * spriteX + x * spriteY >> 16;
        int drawY = y * spriteY - x * spriteX >> 16;
        int finalX = (fixed ? 104 : 83) + drawX - sprite.maxWidth / 2 + 4;
        int finalY = (fixed ? 89 : 85) - drawY - sprite.maxHeight / 2 - 4;

        try {
            sprite.drawSprite(finalX + xPadding, finalY + yPadding);

        } catch (Exception _ex) {
        }
    }

    private boolean menuHasAddFriend(int j) {
        if (j < 0) {
            return false;
        }

        int k = menuActionID[j];

        if (k >= 2000) {
            k -= 2000;
        }

        return k == 337;
    }

    private void appendFocusDest(Entity entity) {
        if (entity.anInt1504 == 0) {
            return;
        }
        if (entity.interactingEntity != -1 && entity.interactingEntity < 32768) {
            try {
                NPC npc = npcArray[entity.interactingEntity];

                if (npc != null) {
                    int i1 = entity.x - npc.x;
                    int k1 = entity.y - npc.y;

                    if (i1 != 0 || k1 != 0) {
                        entity.turnDirection = (int) (Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (entity.interactingEntity >= 32768) {
            int j = entity.interactingEntity - 32768;
            if (j == playerId) {
                j = myPlayerIndex;
            }
            Player player = playerArray[j];
            if (player != null) {
                int l1 = entity.x - player.x;
                int i2 = entity.y - player.y;
                if (l1 != 0 || i2 != 0) {
                    entity.turnDirection = (int) (Math.atan2(l1, i2) * 325.94900000000001D) & 0x7ff;
                }
            }
        }

        if ((entity.anInt1538 != 0 || entity.anInt1539 != 0) && (entity.smallXYIndex == 0 || entity.anInt1503 > 0)) {
            int k = entity.x - (entity.anInt1538 - regionBaseX - regionBaseX) * 64;
            int j1 = entity.y - (entity.anInt1539 - regionBaseY - regionBaseY) * 64;

            if (k != 0 || j1 != 0) {
                entity.turnDirection = (int) (Math.atan2(k, j1) * 325.94900000000001D) & 0x7ff;
            }

            entity.anInt1538 = 0;
            entity.anInt1539 = 0;
        }

        int l = entity.turnDirection - entity.anInt1552 & 0x7ff;

        if (l != 0) {
            if (l < entity.anInt1504 || l > 2048 - entity.anInt1504) {
                entity.anInt1552 = entity.turnDirection;
            } else if (l > 1024) {
                entity.anInt1552 -= entity.anInt1504;
            } else {
                entity.anInt1552 += entity.anInt1504;
            }

            entity.anInt1552 &= 0x7ff;

            if (entity.anInt1517 == entity.anInt1511 && entity.anInt1552 != entity.turnDirection) {
                if (entity.anInt1512 != -1) {
                    entity.anInt1517 = entity.anInt1512;
                    return;
                }

                entity.anInt1517 = entity.anInt1554;
            }
        }
    }

    private void appendAnimation(Entity entity) {
        try {
            entity.aBoolean1541 = false;

            if (entity.anInt1517 != -1) {
                if (entity.anInt1517 > Animation.cache.length)
                    entity.anInt1517 = 0;
                Animation animation = Animation.cache[entity.anInt1517];
                entity.frameDelay++;

                if (entity.currentForcedAnimFrame < animation.frameCount
                        && entity.frameDelay > animation.getFrameLength(entity.currentForcedAnimFrame)) {
                    entity.frameDelay = 1; // this is the frame delay. 0 is what it's normally at. higher number =
                    // faster animations.
                    entity.currentForcedAnimFrame++;
                    entity.nextIdleAnimationFrame++;
                }
                entity.nextIdleAnimationFrame = entity.currentForcedAnimFrame + 1;
                if (entity.nextIdleAnimationFrame >= animation.frameCount) {
                    if (entity.nextIdleAnimationFrame >= animation.frameCount) {
                        entity.nextIdleAnimationFrame = 0;
                    }
                }
                if (entity.currentForcedAnimFrame >= animation.frameCount) {
                    entity.frameDelay = 1;
                    entity.currentForcedAnimFrame = 0;
                }
            }

            if (entity.gfxId != -1 && loopCycle >= entity.graphicDelay) {
                if (entity.currentAnim < 0) {
                    entity.currentAnim = 0;
                }

                Animation animation_1 = SpotAnimDefinition.cache[entity.gfxId].animation;
                if (animation_1 != null){
                    for (entity.animCycle++; entity.currentAnim < animation_1.frameCount
                            && entity.animCycle > animation_1.getFrameLength(entity.currentAnim); entity.currentAnim++) {
                        entity.animCycle -= animation_1.getFrameLength(entity.currentAnim);
                    }

                    if (entity.currentAnim >= animation_1.frameCount
                            && (entity.currentAnim < 0 || entity.currentAnim >= animation_1.frameCount)) {
                        entity.gfxId = -1;
                    }
                    entity.nextGraphicsAnimationFrame = entity.currentAnim + 1;
                    if (entity.nextGraphicsAnimationFrame >= animation_1.frameCount) {
                        if (entity.nextGraphicsAnimationFrame < 0
                                || entity.nextGraphicsAnimationFrame >= animation_1.frameCount) {
                            entity.gfxId = -1;
                        }
                    }
                }
            }

            if (entity.anim != -1 && entity.animationDelay <= 1) {
                Animation animation_2 = Animation.cache[entity.anim];

                if (animation_2.resetWhenWalk == 1 && entity.anInt1542 > 0 && entity.anInt1547 <= loopCycle
                        && entity.anInt1548 < loopCycle) {
                    entity.animationDelay = 1;
                    return;
                }
            }

            if (entity.anim != -1 && entity.animationDelay == 0) {
                Animation animation_3 = Animation.cache[entity.anim];

                for (entity.anInt1528++; entity.currentAnimFrame < animation_3.frameCount
                        && entity.anInt1528 > animation_3
                        .getFrameLength(entity.currentAnimFrame); entity.currentAnimFrame++) {
                    entity.anInt1528 -= animation_3.getFrameLength(entity.currentAnimFrame);
                }

                if (entity.currentAnimFrame >= animation_3.frameCount) {
                    entity.currentAnimFrame -= animation_3.loopDelay;
                    entity.anInt1530++;

                    if (entity.anInt1530 >= animation_3.frameStep) {
                        entity.anim = -1;
                    }

                    if (entity.currentAnimFrame < 0 || entity.currentAnimFrame >= animation_3.frameCount) {
                        entity.anim = -1;
                    }
                }
                entity.nextAnimationFrame = entity.currentAnimFrame + 1;
                if (entity.nextAnimationFrame >= animation_3.frameCount) {
                    if (entity.anInt1530 >= animation_3.frameStep) {
                        entity.anim = -1;
                    }
                    if (entity.nextAnimationFrame < 0 || entity.nextAnimationFrame >= animation_3.frameCount) {
                        entity.anim = -1;
                    }
                }
                entity.aBoolean1541 = animation_3.oneSquareAnimation;
            }

            if (entity.animationDelay > 0) {
                entity.animationDelay--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void method104() {
        Animable_Sub3 class30_sub2_sub4_sub3 = (Animable_Sub3) getIncompleteAnimables().reverseGetFirst();

        for (; class30_sub2_sub4_sub3 != null; class30_sub2_sub4_sub3 = (Animable_Sub3) getIncompleteAnimables()
                .reverseGetNext()) {
            if (class30_sub2_sub4_sub3.anInt1560 != plane || class30_sub2_sub4_sub3.aBoolean1567) {
                class30_sub2_sub4_sub3.unlink();
            } else if (loopCycle >= class30_sub2_sub4_sub3.anInt1564) {
                class30_sub2_sub4_sub3.method454(anInt945);

                if (class30_sub2_sub4_sub3.aBoolean1567) {
                    class30_sub2_sub4_sub3.unlink();
                } else {
                    scene.method285(class30_sub2_sub4_sub3.anInt1560, 0, class30_sub2_sub4_sub3.anInt1563, -1,
                            class30_sub2_sub4_sub3.anInt1562, 60, class30_sub2_sub4_sub3.anInt1561,
                            class30_sub2_sub4_sub3, false);
                }
            }
        }
    }

    private void readPlayerUpdateMask(int bitmap, int j, ByteBuffer buffer, Player player) {
        if ((bitmap & 0x400) != 0) {
            player.anInt1543 = buffer.method428();
            player.anInt1545 = buffer.method428();
            player.anInt1544 = buffer.method428();
            player.anInt1546 = buffer.method428();
            player.anInt1547 = buffer.getShortBigEndianA() + loopCycle;
            player.anInt1548 = buffer.method435() + loopCycle;
            player.anInt1549 = buffer.method428();
            player.method446();
        }

        if ((bitmap & 0x100) != 0) {
            player.gfxId = buffer.getShortBigEndian(); // GFX Id
            int k = buffer.getIntLittleEndian();
            player.graphicHeight = k >> 16;
            player.graphicDelay = loopCycle + (k & 0xffff);
            player.currentAnim = 0;
            player.animCycle = 0;

            if (player.graphicDelay > loopCycle) {
                player.currentAnim = -1;
            }

            if (player.gfxId == 65535) {
                player.gfxId = -1;
            }

            try {
                if (FrameReader.animationlist[Integer
                        .parseInt(Integer.toHexString(SpotAnimDefinition.cache[player.gfxId].animation.frameIDs[0])
                                        .substring(0,
                                                Integer.toHexString(
                                                        SpotAnimDefinition.cache[player.gfxId].animation.frameIDs[0]).length()
                                                        - 4),
                                16)].length == 0)
                    onDemandFetcher.requestFileData(1, Integer.parseInt(
                            Integer.toHexString(SpotAnimDefinition.cache[player.gfxId].animation.frameIDs[0]).substring(
                                    0, Integer.toHexString(SpotAnimDefinition.cache[player.gfxId].animation.frameIDs[0])
                                            .length() - 4),
                            16));
            } catch (Exception e) {
            }
        }

        if ((bitmap & 8) != 0) {
            int l = buffer.getShortBigEndian();

            if (l == 65535) {
                l = -1;
            }

            int i2 = buffer.method427();

            if (l == player.anim && l != -1) {
                int i3 = Animation.cache[l].delayType;

                if (i3 == 1) {
                    player.currentAnimFrame = 0;
                    player.anInt1528 = 0;
                    player.animationDelay = i2;
                    player.anInt1530 = 0;
                }

                if (i3 == 2) {
                    player.anInt1530 = 0;
                }
            } else if (l == -1 || player.anim == -1
                    || Animation.cache[l].forcedPriority >= Animation.cache[player.anim].forcedPriority) {
                player.anim = l;
                player.currentAnimFrame = 0;
                player.anInt1528 = 0;
                player.animationDelay = i2;
                player.anInt1530 = 0;
                player.anInt1542 = player.smallXYIndex;

                try {
                    if (FrameReader.animationlist[Integer
                            .parseInt(
                                    Integer.toHexString(Animation.cache[l].frameIDs[0]).substring(0,
                                            Integer.toHexString(Animation.cache[l].frameIDs[0]).length() - 4),
                                    16)].length == 0)
                        onDemandFetcher
                                .requestFileData(1,
                                        Integer.parseInt(Integer.toHexString(Animation.cache[l].frameIDs[0]).substring(
                                                0, Integer.toHexString(Animation.cache[l].frameIDs[0]).length() - 4),
                                                16));
                } catch (Exception e) {
                }
            }
        }

        if ((bitmap & 4) != 0) {
            player.textSpoken = buffer.getString();

            if (player.textSpoken.charAt(0) == '~') {
                player.textSpoken = player.textSpoken.substring(1);
                pushMessage(player.textSpoken, 2, player.name);
            } else if (player == myPlayer) {
                pushMessage(player.textSpoken, 2, player.name);
            }

            player.anInt1513 = 0;
            player.anInt1531 = 0;
            player.textCycle = 150;
        }

        if ((bitmap & 0x80) != 0) {
            int effects = buffer.getShortBigEndian();
            int rights = buffer.getUnsignedByte();
            int ironman = buffer.getUnsignedByte();
            int chatTextSize = buffer.method427();
            int currentOffset = buffer.position;

            if (player.name != null && player.visible) {
                long l3 = TextClass.longForName(player.name);
                boolean flag = false;

                if (rights <= 1) {
                    for (int i = 0; i < ignoreCount; i++) {
                        if (ignoreListAsLongs[i] != l3) {
                            continue;
                        }

                        flag = true;
                        break;
                    }
                }

                if (!flag && anInt1251 == 0) {
                    try {
                        aStream_834.position = 0;
                        buffer.method442(chatTextSize, 0, aStream_834.buffer);
                        aStream_834.position = 0;
                        String s = TextInput.readChatboxText(chatTextSize, aStream_834);
                        player.textSpoken = s;
                        player.anInt1513 = effects >> 8;
                        // player.privilegeLevel = j2;
                        player.anInt1531 = effects & 0xff;
                        player.textCycle = 150;
                        int secondary = -1;
                        /*if (rights == 0 && ironman > 0) {
                            rights = 11 + ironman;
                        } else if (rights > 0 && ironman > 0) {
                            secondary = rights;
                            rights = 11 + ironman;
                        }*/
                        if (ironman > 0) {
                            secondary = 11 + ironman;
                        }
                        String prefix = getPrefix(rights) + (secondary > -1 ? getPrefix(secondary) : "");
                        List<Integer> chatRights = new ArrayList<>();
                        chatRights.add(rights);
                        chatRights.add(secondary);
                        if (Configuration.HIGHLIGHT_USERNAME) {
                            pushMessage(
                                    s.replace(myPlayer.name.toLowerCase(),
                                            "<col=ff0000>" + myPlayer.name.toLowerCase() + "</col>")
                                            .replace(myPlayer.name, "<col=ff0000>" + myPlayer.name + "</col>"),
                                    2, prefix + player.name, player.loyaltyTitle, player.loyaltyColor,
                                    player.loyaltyPosition, chatRights);
                        } else {
                            pushMessage(s, 2, prefix + player.name, player.loyaltyTitle, player.loyaltyColor,
                                    player.loyaltyPosition, chatRights);
                        }
                    } catch (Exception exception) {
                        Signlink.reportError("cde2");
                    }
                }
            }

            buffer.position = currentOffset + chatTextSize;
        }

        if ((bitmap & 1) != 0) {
            player.interactingEntity = buffer.getShortBigEndian();

            if (player.interactingEntity == 65535) {
                player.interactingEntity = -1;
            }
        }

        if ((bitmap & 0x10) != 0) {
            int j1 = buffer.method427();
            byte abyte0[] = new byte[j1];
            ByteBuffer stream_1 = new ByteBuffer(abyte0);
            buffer.getBytes(j1, 0, abyte0);
            getaStreamArray895s()[j] = stream_1;
            player.updatePlayer(stream_1);
        }

        if ((bitmap & 2) != 0) {
            player.anInt1538 = buffer.getShortBigEndianA();
            player.anInt1539 = buffer.getShortBigEndian();
        }

        if ((bitmap & 0x20) != 0) {

            int k1 = getInputBuffer().getByteA();
            int k2 = buffer.getUnsignedByte();
            int icon = buffer.getUnsignedByte();
            int soakDamage = getInputBuffer().getByteA();
            player.updateHitData(k2, k1, loopCycle, icon, soakDamage);
            player.loopCycleStatus = loopCycle + 300;
            player.currentHealth = getInputBuffer().getByteA();
            player.maxHealth = getInputBuffer().getByteA();
        }

        if ((bitmap & 0x200) != 0) {
            int l1 = buffer.getByteA();
            int l2 = buffer.getUnsignedByte();
            int icon = buffer.getUnsignedByte();
            int soakDamage = buffer.getByteA();
            player.updateHitData(l2, l1, loopCycle, icon, soakDamage);
            player.loopCycleStatus = loopCycle + 300;
            player.currentHealth = buffer.getByteA();
            player.maxHealth = buffer.getByteA();
        }
    }

    private void method108() {
        try {
            int cameraDisplayX = myPlayer.x + cameraOffsetX;
            int cameraDisplayY = myPlayer.y + cameraOffsetY;

            if (currentCameraDisplayX - cameraDisplayX < -500 || currentCameraDisplayX - cameraDisplayX > 500
                    || currentCameraDisplayY - cameraDisplayY < -500 || currentCameraDisplayY - cameraDisplayY > 500) {
                currentCameraDisplayX = cameraDisplayX;
                currentCameraDisplayY = cameraDisplayY;
            }

            if (currentCameraDisplayX != cameraDisplayX) {
                currentCameraDisplayX += (cameraDisplayX - currentCameraDisplayX) / 16;
            }

            if (currentCameraDisplayY != cameraDisplayY) {
                currentCameraDisplayY += (cameraDisplayY - currentCameraDisplayY) / 16;
            }

            if (super.keyArray[1] == 1) {
                cameraRotationLeft += (-24 - cameraRotationLeft) / 2;
            } else if (super.keyArray[2] == 1) {
                cameraRotationLeft += (24 - cameraRotationLeft) / 2;
            } else {
                cameraRotationLeft /= 2;
            }

            if (super.keyArray[3] == 1) {
                cameraRotationRight += (12 - cameraRotationRight) / 2;
            } else if (super.keyArray[4] == 1) {
                cameraRotationRight += (-12 - cameraRotationRight) / 2;
            } else {
                cameraRotationRight /= 2;
            }

            viewRotation = viewRotation + cameraRotationLeft / 2 & 0x7ff;
            cameraRotationZ += cameraRotationRight / 2;

            if (cameraRotationZ < 128) {
                cameraRotationZ = 128;
            }

            if (cameraRotationZ > 383) {
                cameraRotationZ = 383;
            }

            int l = currentCameraDisplayX >> 7;
            int i1 = currentCameraDisplayY >> 7;
            int j1 = method42(plane, currentCameraDisplayY, currentCameraDisplayX);
            int k1 = 0;

            if (l > 3 && i1 > 3 && l < 100 && i1 < 100) {
                for (int l1 = l - 4; l1 <= l + 4; l1++) {
                    for (int k2 = i1 - 4; k2 <= i1 + 4; k2++) {
                        int l2 = plane;

                        if (l2 < 3 && (tileFlags[1][l1][k2] & 2) == 2) {
                            l2++;
                        }

                        int i3 = j1 - tileHeights[l2][l1][k2];

                        if (i3 > k1) {
                            k1 = i3;
                        }
                    }
                }
            }

            anInt1005++;

            if (anInt1005 > 1512) {
                anInt1005 = 0;
                getOut().putOpcode(77);
                getOut().putByte(0);
                int i2 = getOut().position;
                getOut().putByte((int) (Math.random() * 256D));
                getOut().putByte(101);
                getOut().putByte(233);
                getOut().putShort(45092);

                if ((int) (Math.random() * 2D) == 0) {
                    getOut().putShort(35784);
                }

                getOut().putByte((int) (Math.random() * 256D));
                getOut().putByte(64);
                getOut().putByte(38);
                getOut().putShort((int) (Math.random() * 65536D));
                getOut().putShort((int) (Math.random() * 65536D));
                getOut().putVariableSizeByte(getOut().position - i2);
            }

            int j2 = k1 * 192;

            if (j2 > 0x17f00) {
                j2 = 0x17f00;
            }
            if (j2 < 32768) {
                j2 = 32768;
            }
            if (j2 > anInt984) {
                anInt984 += (j2 - anInt984) / 24;
                return;
            }
            if (j2 < anInt984) {
                anInt984 += (j2 - anInt984) / 80;
            }
        } catch (Exception _ex) {
            Signlink.reportError("glfc_ex " + myPlayer.x + "," + myPlayer.y + "," + currentCameraDisplayX + ","
                    + currentCameraDisplayY + "," + currentRegionX + "," + currentRegionY + "," + regionBaseX + ","
                    + regionBaseY);
            throw new RuntimeException("eek");
        }
    }

    private void updatePlayerInstances() {
        for (int i = -1; i < playerCount; i++) {
            int j;

            if (i == -1) {
                j = getMyPlayerIndex();
            } else {
                j = playerIndices[i];
            }

            Player player = playerArray[j];

            if (player != null) {
                entityUpdateBlock(player);
            }
        }
    }

    private void method115() {
        if (loadingStage == 2) {
            for (Class30_Sub1 class30_sub1 = (Class30_Sub1) getaClass19_1179()
                    .reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1) getaClass19_1179()
                    .reverseGetNext()) {
                if (class30_sub1.anInt1294 > 0) {
                    class30_sub1.anInt1294--;
                }
                if (class30_sub1.anInt1294 == 0) {
                    if (class30_sub1.anInt1299 < 0
                            || ObjectManager.method178(class30_sub1.anInt1299, class30_sub1.anInt1301)) {
                        method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1300,
                                class30_sub1.anInt1301, class30_sub1.anInt1297, class30_sub1.anInt1296,
                                class30_sub1.anInt1299);
                        class30_sub1.unlink();
                    }
                } else {
                    if (class30_sub1.anInt1302 > 0) {
                        class30_sub1.anInt1302--;
                    }
                    if (class30_sub1.anInt1302 == 0 && class30_sub1.anInt1297 >= 1 && class30_sub1.anInt1298 >= 1
                            && class30_sub1.anInt1297 <= 102 && class30_sub1.anInt1298 <= 102
                            && (class30_sub1.anInt1291 < 0
                            || ObjectManager.method178(class30_sub1.anInt1291, class30_sub1.anInt1293))) {
                        method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1292,
                                class30_sub1.anInt1293, class30_sub1.anInt1297, class30_sub1.anInt1296,
                                class30_sub1.anInt1291);
                        class30_sub1.anInt1302 = -1;
                        if (class30_sub1.anInt1291 == class30_sub1.anInt1299 && class30_sub1.anInt1299 == -1) {
                            class30_sub1.unlink();
                        } else if (class30_sub1.anInt1291 == class30_sub1.anInt1299
                                && class30_sub1.anInt1292 == class30_sub1.anInt1300
                                && class30_sub1.anInt1293 == class30_sub1.anInt1301) {
                            class30_sub1.unlink();
                        }
                    }
                }
            }

        }
    }

    private void updatePlayerMovement(ByteBuffer stream) {
        stream.initBitAccess();
        int j = stream.getBits(1);
        if (j == 0) {
            return;
        }
        int k = stream.getBits(2);
        if (k == 0) {
            playersToUpdate[playersToUpdateCount++] = getMyPlayerIndex();
            return;
        }
        if (k == 1) {
            int l = stream.getBits(3);
            myPlayer.moveInDir(false, l);
            int k1 = stream.getBits(1);
            if (k1 == 1) {
                playersToUpdate[playersToUpdateCount++] = getMyPlayerIndex();
            }
            return;
        }
        if (k == 2) {
            int i1 = stream.getBits(3);
            myPlayer.moveInDir(true, i1);
            int l1 = stream.getBits(3);
            myPlayer.moveInDir(true, l1);
            int j2 = stream.getBits(1);
            if (j2 == 1) {
                playersToUpdate[playersToUpdateCount++] = getMyPlayerIndex();
            }
            return;
        }
        if (k == 3) {
            plane = stream.getBits(2);
            if (getLastKnownPlane() != plane)
                loadingStage = 1;
            setLastKnownPlane(plane);
            int j1 = stream.getBits(1);
            int i2 = stream.getBits(1);
            if (i2 == 1) {
                playersToUpdate[playersToUpdateCount++] = getMyPlayerIndex();
            }
            int k2 = stream.getBits(7);
            int l2 = stream.getBits(7);
            myPlayer.setPos(l2, k2, j1 == 1);
        }
    }

    private boolean processInterfaceAnimation(int i, int j) {
        boolean flag1 = false;
        if (j < 0 || j > RSInterface.interfaceCache.length) {
            return false;
        }
        RSInterface class9 = RSInterface.interfaceCache[j];
        if (class9 == null)
            return false;
        if (class9.children == null)
            return false;
        for (int element : class9.children) {
            if (element == -1) {
                break;
            }
            RSInterface class9_1 = RSInterface.interfaceCache[element];
            if (class9_1.type == 1) {
                flag1 |= processInterfaceAnimation(i, class9_1.id);
            }
            if (class9_1.type == 6 && (class9_1.disabledAnimationId != -1 || class9_1.enabledAnimationId != -1)) {
                boolean flag2 = interfaceIsSelected(class9_1);
                int l;
                if (flag2) {
                    l = class9_1.enabledAnimationId;
                } else {
                    l = class9_1.disabledAnimationId;
                }
                if (l != -1) {
                    Animation animation = Animation.cache[l];
                    for (class9_1.anInt208 += i; class9_1.anInt208 > animation.getFrameLength(class9_1.anInt246); ) {
                        class9_1.anInt208 -= animation.getFrameLength(class9_1.anInt246) + 1;
                        class9_1.anInt246++;
                        if (class9_1.anInt246 >= animation.frameCount) {
                            class9_1.anInt246 -= animation.loopDelay;
                            if (class9_1.anInt246 < 0 || class9_1.anInt246 >= animation.frameCount) {
                                class9_1.anInt246 = 0;
                            }
                        }
                        flag1 = true;
                    }

                }
            }
        }

        return flag1;
    }

    private int method120() {
        int j = 3;
        if (yCameraCurve < 310) {
            int k = xCameraPos >> 7;
            int l = yCameraPos >> 7;
            int i1 = myPlayer.x >> 7;
            int j1 = myPlayer.y >> 7;
            if ((tileFlags[plane][k][l] & 4) != 0) {
                j = plane;
            }
            int k1;
            if (i1 > k) {
                k1 = i1 - k;
            } else {
                k1 = k - i1;
            }
            int l1;
            if (j1 > l) {
                l1 = j1 - l;
            } else {
                l1 = l - j1;
            }
            if (k1 > l1) {
                int i2 = l1 * 0x10000 / k1;
                int k2 = 32768;
                while (k != i1) {
                    if (k < i1) {
                        k++;
                    } else if (k > i1) {
                        k--;
                    }
                    if ((tileFlags[plane][k][l] & 4) != 0) {
                        j = plane;
                    }
                    k2 += i2;
                    if (k2 >= 0x10000) {
                        k2 -= 0x10000;
                        if (l < j1) {
                            l++;
                        } else if (l > j1) {
                            l--;
                        }
                        if ((tileFlags[plane][k][l] & 4) != 0) {
                            j = plane;
                        }
                    }
                }
            } else {
                int j2 = k1 * 0x10000 / l1;
                int l2 = 32768;
                while (l != j1) {
                    if (l < j1) {
                        l++;
                    } else if (l > j1) {
                        l--;
                    }
                    if ((tileFlags[plane][k][l] & 4) != 0) {
                        j = plane;
                    }
                    l2 += j2;
                    if (l2 >= 0x10000) {
                        l2 -= 0x10000;
                        if (k < i1) {
                            k++;
                        } else if (k > i1) {
                            k--;
                        }
                        if ((tileFlags[plane][k][l] & 4) != 0) {
                            j = plane;
                        }
                    }
                }
            }
        }
        if ((tileFlags[plane][myPlayer.x >> 7][myPlayer.y >> 7] & 4) != 0) {
            j = plane;
        }
        return j;
    }

    private int method121() {
        int j = method42(plane, yCameraPos, xCameraPos);
        if (j - zCameraPos < 800 && (tileFlags[plane][xCameraPos >> 7][yCameraPos >> 7] & 4) != 0) {
            return plane;
        } else {
            return 3;
        }
    }

    private void method130(int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int j2) {
        Class30_Sub1 class30_sub1 = null;
        for (Class30_Sub1 class30_sub1_1 = (Class30_Sub1) getaClass19_1179()
                .reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1) getaClass19_1179()
                .reverseGetNext()) {
            if (class30_sub1_1.anInt1295 != l1 || class30_sub1_1.anInt1297 != i2 || class30_sub1_1.anInt1298 != j1
                    || class30_sub1_1.anInt1296 != i1) {
                continue;
            }
            class30_sub1 = class30_sub1_1;
            break;
        }

        if (class30_sub1 == null) {
            class30_sub1 = new Class30_Sub1();
            class30_sub1.anInt1295 = l1;
            class30_sub1.anInt1296 = i1;
            class30_sub1.anInt1297 = i2;
            class30_sub1.anInt1298 = j1;
            method89(class30_sub1);
            getaClass19_1179().insertHead(class30_sub1);
        }
        class30_sub1.anInt1291 = k;
        class30_sub1.anInt1293 = k1;
        class30_sub1.anInt1292 = l;
        class30_sub1.anInt1302 = j2;
        class30_sub1.anInt1294 = j;
    }

    private void updatePlayer(ByteBuffer stream) {
        int j = stream.getBits(8);
        if (j < playerCount) {
            for (int k = j; k < playerCount; k++) {
                anIntArray840[anInt839++] = playerIndices[k];
            }

        }
        if (j > playerCount) {
            Signlink.reportError(myUsername + " Too many players");
            throw new RuntimeException("eek");
        }
        playerCount = 0;
        for (int l = 0; l < j; l++) {
            int i1 = playerIndices[l];
            Player player = playerArray[i1];
            int j1 = stream.getBits(1);
            if (j1 == 0) {
                playerIndices[playerCount++] = i1;
                player.loopCycle = loopCycle;
            } else {
                int k1 = stream.getBits(2);
                if (k1 == 0) {
                    playerIndices[playerCount++] = i1;
                    player.loopCycle = loopCycle;
                    playersToUpdate[playersToUpdateCount++] = i1;
                } else if (k1 == 1) {
                    playerIndices[playerCount++] = i1;
                    player.loopCycle = loopCycle;
                    int l1 = stream.getBits(3);
                    player.moveInDir(false, l1);
                    int j2 = stream.getBits(1);
                    if (j2 == 1) {
                        playersToUpdate[playersToUpdateCount++] = i1;
                    }
                } else if (k1 == 2) {
                    playerIndices[playerCount++] = i1;
                    player.loopCycle = loopCycle;
                    int i2 = stream.getBits(3);
                    player.moveInDir(true, i2);
                    int k2 = stream.getBits(3);
                    player.moveInDir(true, k2);
                    int l2 = stream.getBits(1);
                    if (l2 == 1) {
                        playersToUpdate[playersToUpdateCount++] = i1;
                    }
                } else if (k1 == 3) {
                    anIntArray840[anInt839++] = i1;
                }
            }
        }
    }

    private void render_ground_item_names() {
        if (Configuration.GROUND_TEXT) {
            for (int x = 0; x < 104; x++) {
                for (int y = 0; y < 104; y++) {
                    Deque node = groundArray[plane][x][y]; // groundarray confirmt because ground items were showing
                    // with fucked raster pos
                    int offset = -13;
                    if (node != null) {
                        for (Item item = (Item) node.getFirst(); item != null; item = (Item) node.getNext()) {
                            ItemDefinition itemDef = ItemDefinition.get(item.id);
                            calcEntityScreenPos((x << 7) + 64, 64, (y << 7) + 64);
                            newSmallFont.drawCenteredString(
                                    itemDef.name + (item.amount > 1 ? " (" + intToKOrMil(item.amount) + ")" : ""),
                                    spriteDrawX, spriteDrawY - offset, 0xffffff, 1);
                            offset += 12;
                        }
                    }
                }
            }
        }
    }

    private void parseEntityPacket(ByteBuffer stream, int j) {
        if (j == 84) {
            int k = stream.getUnsignedByte();
            int j3 = anInt1268 + (k >> 4 & 7);
            int i6 = anInt1269 + (k & 7);
            int l8 = stream.getUnsignedShort();
            int k11 = stream.getUnsignedShort();
            int l13 = stream.getUnsignedShort();
            if (j3 >= 0 && i6 >= 0 && j3 < 104 && i6 < 104) {
                Deque class19_1 = groundArray[plane][j3][i6];
                if (class19_1 != null) {
                    for (Item class30_sub2_sub4_sub2_3 = (Item) class19_1
                            .reverseGetFirst(); class30_sub2_sub4_sub2_3 != null; class30_sub2_sub4_sub2_3 = (Item) class19_1
                            .reverseGetNext()) {
                        if (class30_sub2_sub4_sub2_3.id != (l8 & 0x7fff) || class30_sub2_sub4_sub2_3.amount != k11) {
                            continue;
                        }
                        class30_sub2_sub4_sub2_3.amount = l13;
                        break;
                    }

                    spawnGroundItem(j3, i6);
                }
            }
            return;
        }
        if (j == 105) {
            int l = stream.getUnsignedByte();
            int k3 = anInt1268 + (l >> 4 & 7);
            int j6 = anInt1269 + (l & 7);
            int i9 = stream.getUnsignedShort();
            int l11 = stream.getUnsignedByte();
            int i14 = l11 >> 4 & 0xf;
            int i16 = l11 & 7;
            if (myPlayer.smallX[0] >= k3 - i14 && myPlayer.smallX[0] <= k3 + i14 && myPlayer.smallY[0] >= j6 - i14
                    && myPlayer.smallY[0] <= j6 + i14 && soundEffectVolume != 0 && aBoolean848 && !isLowDetail()
                    && soundCount < 50) {
                sound[soundCount] = i9;
                soundType[soundCount] = i16;
                soundDelay[soundCount] = Sounds.anIntArray326[i9];
                aClass26Array1468[soundCount] = null;
                soundCount++;
            }
        }
        if (j == 215) {
            int i1 = stream.method435();
            int l3 = stream.method428();
            int k6 = anInt1268 + (l3 >> 4 & 7);
            int j9 = anInt1269 + (l3 & 7);
            int i12 = stream.method435();
            int j14 = stream.getUnsignedShort();
            if (k6 >= 0 && j9 >= 0 && k6 < 104 && j9 < 104 && i12 != playerId) {
                Item class30_sub2_sub4_sub2_2 = new Item();
                class30_sub2_sub4_sub2_2.id = i1;
                class30_sub2_sub4_sub2_2.amount = j14;
                if (groundArray[plane][k6][j9] == null) {
                    groundArray[plane][k6][j9] = new Deque();
                }
                groundArray[plane][k6][j9].insertHead(class30_sub2_sub4_sub2_2);
                spawnGroundItem(k6, j9);
            }
            return;
        }
        if (j == 156) {
            int j1 = stream.method426();
            int i4 = anInt1268 + (j1 >> 4 & 7);
            int l6 = anInt1269 + (j1 & 7);
            int k9 = stream.getUnsignedShort();
            if (i4 >= 0 && l6 >= 0 && i4 < 104 && l6 < 104) {
                Deque class19 = groundArray[plane][i4][l6];
                if (class19 != null) {
                    for (Item item = (Item) class19.reverseGetFirst(); item != null; item = (Item) class19
                            .reverseGetNext()) {
                        if (item.id != (k9 & 0x7fff)) {
                            continue;
                        }
                        item.unlink();
                        break;
                    }

                    if (class19.reverseGetFirst() == null) {
                        groundArray[plane][i4][l6] = null;
                    }
                    spawnGroundItem(i4, l6);
                }
            }
            return;
        }
        if (j == 160) {
            int k1 = stream.method428();
            int j4 = anInt1268 + (k1 >> 4 & 7);
            int i7 = anInt1269 + (k1 & 7);
            int l9 = stream.method428();
            int j12 = l9 >> 2;
            int k14 = l9 & 3;
            int objectType = anIntArray1177[j12];
            int j17 = stream.method435();
            if (j4 >= 0 && i7 >= 0 && j4 < 103 && i7 < 103) {
                int j18 = tileHeights[plane][j4][i7];
                int i19 = tileHeights[plane][j4 + 1][i7];
                int l19 = tileHeights[plane][j4 + 1][i7 + 1];
                int k20 = tileHeights[plane][j4][i7 + 1];
                if (objectType == 0) {
                    Object1 class10 = scene.method296(plane, j4, i7);
                    if (class10 != null) {
                        int k21 = class10.uid >> 14 & 0x7fff;
                        if (j12 == 2) {
                            class10.aClass30_Sub2_Sub4_278 = new Animable_Sub5(k21, 4 + k14, 2, i19, l19, j18, k20, j17,
                                    false);
                            class10.aClass30_Sub2_Sub4_279 = new Animable_Sub5(k21, k14 + 1 & 3, 2, i19, l19, j18, k20,
                                    j17, false);
                        } else {
                            class10.aClass30_Sub2_Sub4_278 = new Animable_Sub5(k21, k14, j12, i19, l19, j18, k20, j17,
                                    false);
                        }
                    }
                }

                if (objectType == 1) {
                    Object2 class26 = scene.method297(j4, i7, plane);
                    if (class26 != null) {
                        class26.aClass30_Sub2_Sub4_504 = new Animable_Sub5(class26.uid >> 14 & 0x7fff, 0, 4, i19, l19,
                                j18, k20, j17, false);
                    }
                }

                if (objectType == 2) {
                    Object5 class28 = scene.method298(j4, i7, plane);

                    if (j12 == 11) {
                        j12 = 10;
                    }

                    if (class28 != null) {
                        class28.aClass30_Sub2_Sub4_521 = new Animable_Sub5(class28.uid >> 14 & 0x7fff, k14, j12, i19,
                                l19, j18, k20, j17, false);
                    }
                }

                if (objectType == 3) {
                    Object3 class49 = scene.method299(i7, j4, plane);

                    if (class49 != null) {
                        class49.aClass30_Sub2_Sub4_814 = new Animable_Sub5(class49.uid >> 14 & 0x7fff, k14, 22, i19,
                                l19, j18, k20, j17, false);
                    }
                }
            }

            return;
        }

        if (j == 147) {
            int l1 = stream.method428();
            int k4 = anInt1268 + (l1 >> 4 & 7);
            int j7 = anInt1269 + (l1 & 7);
            int i10 = stream.getUnsignedShort();
            byte byte0 = stream.method430();
            int l14 = stream.getShortBigEndian();
            byte byte1 = stream.method429();
            int k17 = stream.getUnsignedShort();
            int k18 = stream.method428();
            int j19 = k18 >> 2;
            int i20 = k18 & 3;
            int l20 = anIntArray1177[j19];
            byte byte2 = stream.getSignedByte();
            int l21 = stream.getUnsignedShort();
            byte byte3 = stream.method429();
            Player player;

            if (i10 == playerId) {
                player = myPlayer;
            } else {
                player = playerArray[i10];
            }

            if (player != null) {
                ObjectDefinition class46 = ObjectDefinition.forID(l21);
                int i22 = tileHeights[plane][k4][j7];
                int j22 = tileHeights[plane][k4 + 1][j7];
                int k22 = tileHeights[plane][k4 + 1][j7 + 1];
                int l22 = tileHeights[plane][k4][j7 + 1];
                Model model = class46.renderObject(j19, i20, i22, j22, k22, l22, -1);

                if (model != null) {
                    method130(k17 + 1, -1, 0, l20, j7, 0, plane, k4, l14 + 1);
                    player.anInt1707 = l14 + loopCycle;
                    player.anInt1708 = k17 + loopCycle;
                    player.aModel_1714 = model;
                    int i23 = class46.sizeX;
                    int j23 = class46.sizeY;

                    if (i20 == 1 || i20 == 3) {
                        i23 = class46.sizeY;
                        j23 = class46.sizeX;
                    }

                    player.anInt1711 = k4 * 128 + i23 * 64;
                    player.anInt1713 = j7 * 128 + j23 * 64;
                    player.anInt1712 = method42(plane, player.anInt1713, player.anInt1711);

                    if (byte2 > byte0) {
                        byte byte4 = byte2;
                        byte2 = byte0;
                        byte0 = byte4;
                    }

                    if (byte3 > byte1) {
                        byte byte5 = byte3;
                        byte3 = byte1;
                        byte1 = byte5;
                    }

                    player.anInt1719 = k4 + byte2;
                    player.anInt1721 = k4 + byte0;
                    player.anInt1720 = j7 + byte3;
                    player.anInt1722 = j7 + byte1;
                }
            }
        }

        if (j == 151) {
            int i2 = stream.method426();
            int l4 = anInt1268 + (i2 >> 4 & 7);
            int k7 = anInt1269 + (i2 & 7);
            int objectId = stream.getShortBigEndian();
            int k12 = stream.method428();
            int i15 = k12 >> 2;
            int k16 = k12 & 3;
            int l17 = anIntArray1177[i15];
            if (objectId < -1) {
                objectId = Short.MAX_VALUE + -objectId;
            }

            if (l4 >= 0 && k7 >= 0 && l4 < 104 && k7 < 104) {
                method130(-1, objectId, k16, l17, k7, i15, plane, l4, 0);
            }

            return;
        }

        if (j == 4) {
            int j2 = stream.getUnsignedByte();
            int i5 = anInt1268 + (j2 >> 4 & 7);
            int l7 = anInt1269 + (j2 & 7);
            int k10 = stream.getUnsignedShort();
            int l12 = stream.getUnsignedByte();
            int j15 = stream.getUnsignedShort();

            if (i5 >= 0 && l7 >= 0 && i5 < 104 && l7 < 104) {
                i5 = i5 * 128 + 64;
                l7 = l7 * 128 + 64;
                Animable_Sub3 class30_sub2_sub4_sub3 = new Animable_Sub3(plane, loopCycle, j15, k10,
                        method42(plane, l7, i5) - l12, l7, i5);
                getIncompleteAnimables().insertHead(class30_sub2_sub4_sub3);
            }

            return;
        }

        if (j == 44) {
            int k2 = stream.getShortBigEndianA();
            long j5 = stream.getLong(); // stream.getUnsignedShort();
            int i8 = stream.getUnsignedByte();
            int l10 = anInt1268 + (i8 >> 4 & 7);
            int i13 = anInt1269 + (i8 & 7);

            if (l10 >= 0 && i13 >= 0 && l10 < 104 && i13 < 104) {
                Item class30_sub2_sub4_sub2_1 = new Item();
                class30_sub2_sub4_sub2_1.id = k2;
                class30_sub2_sub4_sub2_1.amount = (int) j5;

                if (groundArray[plane][l10][i13] == null) {
                    groundArray[plane][l10][i13] = new Deque();
                }

                groundArray[plane][l10][i13].insertHead(class30_sub2_sub4_sub2_1);
                spawnGroundItem(l10, i13);
            }

            return;
        }

        if (j == 101) {
            int l2 = stream.method427();
            int k5 = l2 >> 2;
            int j8 = l2 & 3;
            int i11 = anIntArray1177[k5];
            int j13 = stream.getUnsignedByte();
            int k15 = anInt1268 + (j13 >> 4 & 7);
            int l16 = anInt1269 + (j13 & 7);

            if (k15 >= 0 && l16 >= 0 && k15 < 104 && l16 < 104) {
                method130(-1, -1, j8, i11, l16, k5, plane, k15, 0);
            }

            return;
        }

        if (j == 117) {
            int i3 = stream.getUnsignedByte();
            int l5 = anInt1268 + (i3 >> 4 & 7);
            int k8 = anInt1269 + (i3 & 7);
            int j11 = l5 + stream.getSignedByte();
            int k13 = k8 + stream.getSignedByte();
            int l15 = stream.getSignedShort();
            int i17 = stream.getUnsignedShort();
            int i18 = stream.getUnsignedByte() * 4;
            int l18 = stream.getUnsignedByte() * 4;
            int k19 = stream.getUnsignedShort();
            int j20 = stream.getUnsignedShort();
            int i21 = stream.getUnsignedByte();
            int j21 = stream.getUnsignedByte();

            if (l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104 && j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104
                    && i17 != 65535) {
                l5 = l5 * 128 + 64;
                k8 = k8 * 128 + 64;
                j11 = j11 * 128 + 64;
                k13 = k13 * 128 + 64;
                PlayerProjectile class30_sub2_sub4_sub4 = new PlayerProjectile(i21, l18, k19 + loopCycle,
                        j20 + loopCycle, j21, plane, method42(plane, k8, l5) - i18, k8, l5, l15, i17);
                class30_sub2_sub4_sub4.method455(k19 + loopCycle, k13, method42(plane, k13, j11) - l18, j11);
                getProjectiles().insertHead(class30_sub2_sub4_sub4);
            }
        }
    }

    private void updateNPCAmount(ByteBuffer stream) {
        stream.initBitAccess();
        int npcAmt = stream.getBits(8);
        if (npcAmt < npcCount) {
            for (int l = npcAmt; l < npcCount; l++)
                anIntArray840[anInt839++] = npcIndices[l];

        }
        if (npcAmt > npcCount) {
            System.out.println(myUsername + " Too many npcs");
            throw new RuntimeException("eek");
        }
        npcCount = 0;
        lastNpcAmt = npcAmt;
        for (int i1 = 0; i1 < npcAmt; i1++) {
            int j1 = npcIndices[i1];
            NPC npc = npcArray[j1];
            int k1 = stream.getBits(1);
            if (k1 == 0) {
                npcIndices[npcCount++] = j1;
                npc.loopCycle = loopCycle;
            } else {
                int l1 = stream.getBits(2);
                if (l1 == 0) {
                    npcIndices[npcCount++] = j1;
                    npc.loopCycle = loopCycle;
                    playersToUpdate[playersToUpdateCount++] = j1;
                } else if (l1 == 1) {
                    npcIndices[npcCount++] = j1;
                    npc.loopCycle = loopCycle;
                    int i2 = stream.getBits(3);
                    npc.moveInDir(false, i2);
                    int k2 = stream.getBits(1);
                    if (k2 == 1)
                        playersToUpdate[playersToUpdateCount++] = j1;
                } else if (l1 == 2) {
                    npcIndices[npcCount++] = j1;
                    npc.loopCycle = loopCycle;
                    int j2 = stream.getBits(3);
                    npc.moveInDir(true, j2);
                    int l2 = stream.getBits(3);
                    npc.moveInDir(true, l2);
                    int i3 = stream.getBits(1);
                    if (i3 == 1)
                        playersToUpdate[playersToUpdateCount++] = j1;
                } else if (l1 == 3)
                    anIntArray840[anInt839++] = j1;
            }
        }

    }

    private void method142(int i, int j, int k, int l, int i1, int j1, int k1) {
        if (i1 >= 1 && i >= 1 && i1 <= 102 && i <= 102) {
            if (isLowDetail() && j != plane) {
                return;
            }

            int i2 = 0;

            if (j1 == 0) {
                i2 = scene.method300(j, i1, i);
            }

            if (j1 == 1) {
                i2 = scene.method301(j, i1, i);
            }

            if (j1 == 2) {
                i2 = scene.method302(j, i1, i);
            }

            if (j1 == 3) {
                i2 = scene.method303(j, i1, i);
            }

            if (i2 != 0) {
                int i3 = scene.fetchObjectIDTagForPosition(j, i1, i, i2);
                int j2 = i2 >> 14 & 0x7fff;
                int k2 = i3 & 0x1f;
                int l2 = i3 >> 6;

                if (j1 == 0) {
                    scene.method291(i1, j, i, (byte) -119);
                    ObjectDefinition class46 = ObjectDefinition.forID(j2);

                    if (class46.isUnwalkable) {
                        collisionData[j].method215(l2, k2, class46.aBoolean757, i1, i);
                    }
                }

                if (j1 == 1) {
                    scene.method292(i, j, i1);
                }

                if (j1 == 2) {
                    scene.method293(j, i1, i);
                    ObjectDefinition class46_1 = ObjectDefinition.forID(j2);

                    if (i1 + class46_1.sizeX > 103 || i + class46_1.sizeX > 103 || i1 + class46_1.sizeY > 103
                            || i + class46_1.sizeY > 103) {
                        return;
                    }

                    if (class46_1.isUnwalkable) {
                        collisionData[j].method216(l2, class46_1.sizeX, i1, i, class46_1.sizeY, class46_1.aBoolean757);
                    }
                }

                if (j1 == 3) {
                    scene.method294(j, i, i1);
                    ObjectDefinition class46_2 = ObjectDefinition.forID(j2);

                    if (class46_2.isUnwalkable && class46_2.hasActions) {
                        collisionData[j].method218(i, i1);
                    }
                }
            }

            if (k1 >= 0) {
                int j3 = j;

                if (j3 < 3 && (tileFlags[1][i1][i] & 2) == 2) {
                    j3++;
                }

                if (j == 4) {
                    j = 0;
                }
                ObjectManager.method188(scene, k, i, l, j3, collisionData[j], tileHeights, i1, k1, j);
            }
        }
    }



    private void method146() {
        anInt1265++;
        int j = 0;
        int l = xCameraPos;
        int i1 = zCameraPos;
        int j1 = yCameraPos;
        int k1 = yCameraCurve;
        int l1 = xCameraCurve;

        if (loggedIn) {
            method47(true);
            method26(true);
            method47(false);
            method26(false);
            method55();
            method104();

            if (!cameraViewChanged) {
                int i = cameraRotationZ;

                if (anInt984 / 256 > i) {
                    i = anInt984 / 256;
                }

                if (aBooleanArray876[4] && anIntArray1203[4] + 128 > i) {
                    i = anIntArray1203[4] + 128;
                }

                if (Configuration.TOGGLE_FOV) {
                    log_view_dist = getScreenWidth() >= 1024 ? 10 : 9;
                } else {
                    log_view_dist = 9;
                }
                int k = viewRotation + viewRotationOffset & 0x7ff;
                int zoom = i + (Configuration.TOGGLE_FOV ? 1200 : 650) - getScreenHeight() / 400;
                zoom += clientZoom;
                setCameraPos(
                        GameFrame.getScreenMode() == ScreenMode.FIXED ? 600 + i * 3 + clientZoom
                                : getScreenWidth() >= 1024 ? zoom : 450 + i * 3 + clientZoom,
                        i, currentCameraDisplayX, method42(plane, myPlayer.y, myPlayer.x) - 50, k,
                        currentCameraDisplayY);
            }

            if (!cameraViewChanged) {
                j = method120();
            } else {
                j = method121();
            }

        }

        int k2 = Rasterizer.anInt1481;
        Model.aBoolean1684 = true;
        Model.anInt1687 = 0;
        Model.anInt1685 = super.mouseX - 4;
        Model.anInt1686 = super.mouseY - 4;

        if (loggedIn) {
            scene.method313(xCameraPos, yCameraPos, xCameraCurve, zCameraPos, j, yCameraCurve);
            scene.clearObj5Cache();

            if (Configuration.FOG_ENABLED) {
                Rasterizer.drawFog(0xc8c0a8, 2800, 3300);
            } else {
                Rasterizer.drawFog(0xc8c0a8, 7700, 7700);

            }

        }

        updateEntities();
        drawTimers();
        drawHeadIcon();

        // method37(k2, 1);
        if ( Configuration.ANIMATE_TEXTURES )
        animateTextures();
        // drawModIcon(myPlayer);

        if (GameFrame.getScreenMode() != ScreenMode.FIXED && loggedIn) {
            drawTabArea();
            drawChatArea();
            drawMinimap();
        }

        if (loggedIn) {
            render_ground_item_names();
            draw3dScreen();
            drawConsoleArea();
            drawConsole();
        }

        if (loggedIn && Configuration.MONEY_POUCH_ENABLED) {
            mapArea.displayMoneyPouch(this);
        }

        if (PlayerHandler.showXP && loggedIn) {
            mapArea.displayXPCounter(this);
        }

        if (loggedIn) {
            if (!resizing) {
                gameScreenIP.drawGraphics(canvas.getGraphics(), gameScreenDrawX, gameScreenDrawY);
            }
            xCameraPos = l;
            zCameraPos = i1;
            yCameraPos = j1;
            yCameraCurve = k1;
            xCameraCurve = l1;
        }
    }

    private void sendBasicPing() {
        getOut().putOpcode(0);
    }

    private void loadRegion() {//wait, when u undid the comment, it crashed teleing from 4 to 1? yeah but i was spam clicking the map so idk what exactly happened then its not related to the load region method, I have reported the minimap spam clicking when teleing bug and u said no need to fix it rn sec lets make sure
        try {
            setLastKnownPlane(-1);
            getIncompleteAnimables().removeAll();
            getProjectiles().removeAll();
            Rasterizer.clearTextureCache();
            unlickCaches();
            scene.initToNull();
            System.gc();

            for (int i = 0; i < 4; i++)
                collisionData[i].setDefault();

            for (int l = 0; l < 4; l++) {
                for (int k1 = 0; k1 < 104; k1++) {
                    for (int j2 = 0; j2 < 104; j2++) {
                        tileFlags[l][k1][j2] = 0;
                    }
                }
            }

            ObjectManager objectManager = new ObjectManager(tileFlags, tileHeights);
            int k2 = terrainData.length;

            if (loggedIn)
                sendBasicPing();

            if (!requestMapReconstruct) {
                for (int i3 = 0; i3 < k2; i3++) {
                    int i4 = (mapCoordinates[i3] >> 8) * 64 - regionBaseX;
                    int k5 = (mapCoordinates[i3] & 0xff) * 64 - regionBaseY;
                    byte abyte0[] = terrainData[i3];
                    if (abyte0 != null) {
                        objectManager.method180(mapCoordinates[i3], abyte0, k5, i4, (currentRegionX - 6) * 8, (currentRegionY - 6) * 8,
                                collisionData);
                    }
                }

                for (int j4 = 0; j4 < k2; j4++) {
                    int l5 = (mapCoordinates[j4] >> 8) * 62 - regionBaseX;
                    int k7 = (mapCoordinates[j4] & 0xff) * 62 - regionBaseY;
                    byte abyte2[] = terrainData[j4];

                    if (abyte2 == null && currentRegionY < 800) {
                        objectManager.initiateVertexHeights(k7, 64, 64, l5);
                    }
                }
                if (loggedIn)
                    sendBasicPing();

                for (int i6 = 0; i6 < k2; i6++) {
                    byte abyte1[] = objectData[i6];
                    if (abyte1 != null) {
                        int l8 = (mapCoordinates[i6] >> 8) * 64 - regionBaseX;
                        int k9 = (mapCoordinates[i6] & 0xff) * 64 - regionBaseY;
                        objectManager.method190(mapCoordinates[i6], l8, collisionData, k9, scene, abyte1);
                    }
                }

                CustomObjects.spawn();

            } else {
                for (int plane = 0; plane < 4; plane++) {
                    for (int x = 0; x < 13; x++) {
                        for (int y = 0; y < 13; y++) {
                            int chunkBits = constructRegionData[plane][x][y];

                            if (chunkBits != -1) {
                                int z = chunkBits >> 24 & 3;
                                int rotation = chunkBits >> 1 & 3;
                                int xCoord = chunkBits >> 14 & 0x3ff;
                                int yCoord = chunkBits >> 3 & 0x7ff;
                                int mapRegion = (xCoord / 8 << 8) + yCoord / 8;

                                for (int l11 = 0; l11 < mapCoordinates.length; l11++) {
                                    if (mapCoordinates[l11] != mapRegion || terrainData[l11] == null) {
                                        continue;
                                    }

                                    objectManager.loadMapChunk(mapCoordinates[l11], z, rotation, collisionData, x * 8, (xCoord & 7) * 8,
                                            terrainData[l11], (yCoord & 7) * 8, plane, y * 8);
                                    break;
                                }
                            }
                        }
                    }
                }

                for (int xChunk = 0; xChunk < 13; xChunk++) {
                    for (int yChunk = 0; yChunk < 13; yChunk++) {
                        int tileBits = constructRegionData[0][xChunk][yChunk];

                        if (tileBits == -1) {
                            objectManager.initiateVertexHeights(yChunk * 8, 8, 8, xChunk * 8);
                        }
                    }
                }

                if (loggedIn)
                    getOut().putOpcode(0);

                for (int chunkZ = 0; chunkZ < 4; chunkZ++) {
                    for (int chunkX = 0; chunkX < 13; chunkX++) {
                        for (int chunkY = 0; chunkY < 13; chunkY++) {
                            int tileBits = constructRegionData[chunkZ][chunkX][chunkY];

                            if (tileBits != -1) {
                                int plane = tileBits >> 24 & 3;
                                int rotation = tileBits >> 1 & 3;
                                int coordX = tileBits >> 14 & 0x3ff;
                                int coordY = tileBits >> 3 & 0x7ff;
                                int mapRegion = (coordX / 8 << 8) + coordY / 8;

                                for (int idx = 0; idx < mapCoordinates.length; idx++) {
                                    if (mapCoordinates[idx] != mapRegion || objectData[idx] == null) {
                                        continue;
                                    }
                                    objectManager.readObjectMap(collisionData, scene, plane, chunkX * 8,
                                            (coordY & 7) * 8, chunkZ, objectData[idx], (coordX & 7) * 8, rotation,
                                            chunkY * 8, mapCoordinates[idx]);
                                    break;
                                }
                            }
                        }
                    }
                }
                requestMapReconstruct = false;
            }

            if (loggedIn)
                getOut().putOpcode(0);

            sendBasicPing();
            objectManager.createRegionScene(collisionData, scene);

            if (loggedIn)
                gameScreenIP.initDrawingArea();

            if (loggedIn)
                sendBasicPing();

            int k3 = ObjectManager.maximumPlane;

            if (k3 > plane)
                k3 = plane;

            if (k3 < plane - 1)
                k3 = plane - 1;

            if (isLowDetail())
                scene.initTiles(ObjectManager.maximumPlane);
            else
                scene.initTiles(0);

            for (int i5 = 0; i5 < 104; i5++) {
                for (int i7 = 0; i7 < 104; i7++) {
                    spawnGroundItem(i5, i7);
                }
            }
            cleanObjectSpawnRequests();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        ObjectDefinition.baseModels.unlinkAll();
        /*
         * if (super.gameFrame != null && loggedIn) { getOut().putOpcode(210);
         * getOut().putInt(0x3f008edd); }
         */
        //ye idk this woul need time like the other one. ah shit uhm do i just undo everything we did here? na it should be good but it would have no change on the previous gameplay ah i see aight
        System.gc();
        Rasterizer.initiateRequestBuffers();
        onDemandFetcher.clearExtras();

        int startRegionX = (currentRegionX - 6) / 8 - 1;
        int endRegionX = (currentRegionX + 6) / 8 + 1;
        int startRegionY = (currentRegionY - 6) / 8 - 1;
        int endRegionY = (currentRegionY + 6) / 8 + 1;

        for (int regionX = startRegionX; regionX <= endRegionX; regionX++) {
            for (int regionY = startRegionY; regionY <= endRegionY; regionY++) {
                if (regionX == startRegionX || regionX == endRegionX || regionY == startRegionY
                        || regionY == endRegionY) {
                    int floorMapId = onDemandFetcher.getMapCount(0, regionY, regionX);

                    if (floorMapId != -1) {
                        onDemandFetcher.loadExtra(floorMapId, 3);
                    }

                    int objectMapId = onDemandFetcher.getMapCount(1, regionY, regionX);

                    if (objectMapId != -1) {
                        onDemandFetcher.loadExtra(objectMapId, 3);
                    }
                }
            }
        }
    }

    private void renderedMapScene(int i) {
        int[] ai = miniMapRegions.myPixels;
        int j = ai.length;

        for (int k = 0; k < j; k++) {
            ai[k] = 0;
        }

        for (int l = 1; l < 103; l++) {
            int i1 = 24628 + (103 - l) * 512 * 4;

            for (int k1 = 1; k1 < 103; k1++) {
                if ((tileFlags[i][k1][l] & 0x18) == 0) {
                    scene.method309(ai, i1, i, k1, l);
                }

                if (i < 3 && (tileFlags[i + 1][k1][l] & 8) != 0) {
                    scene.method309(ai, i1, i + 1, k1, l);
                }

                i1 += 4;
            }
        }

        int j1 = Color.WHITE.getRGB();
        int l1 = Color.RED.getRGB();

        if (loggedIn) {
            miniMapRegions.method343();
        }

        for (int i2 = 1; i2 < 103; i2++) {
            for (int j2 = 1; j2 < 103; j2++) {
                if ((tileFlags[i][j2][i2] & 0x18) == 0) {
                    method50(i2, j1, j2, l1, i);
                }

                if (i < 3 && (tileFlags[i + 1][j2][i2] & 8) != 0) {
                    method50(i2, j1, j2, l1, i + 1);
                }
            }
        }

        if (loggedIn) {
            gameScreenIP.initDrawingArea();
        }

        anInt1071 = 0;

        for (int k2 = 0; k2 < 104; k2++) {
            for (int l2 = 0; l2 < 104; l2++) {
                int i3 = scene.method303(plane, k2, l2);

                if (i3 != 0) {
                    i3 = i3 >> 14 & 0x7fff;
                    int j3 = ObjectDefinition.forID(i3).mapFunctionID;

                    if (j3 >= 0) {
                        aClass30_Sub2_Sub1_Sub1Array1140[anInt1071] = mapFunctions[j3];
                        anIntArray1072[anInt1071] = k2;
                        anIntArray1073[anInt1071] = l2;
                        anInt1071++;
                    }
                }
            }
        }
    }

    private void method26(boolean flag) {
        for (int i = 0; i < npcCount; i++) {
            NPC npc = npcArray[npcIndices[i]];
            int k = 0x20000000 + (npcIndices[i] << 14);

            if (npc == null || !npc.isVisible() || npc.definitionOverride.visibilityOrRendering != flag) {
                continue;
            }

            int l = npc.x >> 7;
            int i1 = npc.y >> 7;

            if (l < 0 || l >= 104 || i1 < 0 || i1 >= 104) {
                continue;
            }

            if (npc.anInt1540 == 1 && (npc.x & 0x7f) == 64 && (npc.y & 0x7f) == 64) {
                if (anIntArrayArray929[l][i1] == anInt1265) {
                    continue;
                }

                anIntArrayArray929[l][i1] = anInt1265;
            }

            if (!npc.definitionOverride.disableRightClick) {
                k += 0x80000000;
            }

            scene.method285(plane, npc.anInt1552, method42(plane, npc.y, npc.x), k, npc.y,
                    (npc.anInt1540 - 1) * 64 + 60, npc.x, npc, npc.aBoolean1541);
        }
    }

    private void animateTextures() {

        // textures
        AnimatedTextureStore[] var4;
        int var3 = (var4 = AnimatedTextureStore.values()).length;

        for (int var2 = 0; var2 < var3; ++var2) {
            AnimatedTextureStore data = var4[var2];
            if (Rasterizer.anIntArray1480[data.getId()] >= data.getId()) {
                Background background = Rasterizer.aBackgroundArray1474s[data.getId()];
                int indexes = background.imgWidth * background.imgHeight - 1;
                int noise = background.imgWidth * this.anInt945 * data.getSpeed();
                byte[] current = background.imgPixels;
                byte[] next = this.aByteArray912;

                for (int i2 = 0; i2 <= indexes; ++i2) {
                    next[i2] = current[i2 - noise & indexes];
                }

                background.imgPixels = next;
                this.aByteArray912 = current;
                Rasterizer.method370(data.getId());
            }
        }

    }

    public void method37(int j, int speed) {
        // Textures

        if (Rasterizer.anIntArray1480[17] >= j) {
            speed = 1;
            Background background = Rasterizer.aBackgroundArray1474s[17];
            int k = background.imgWidth * background.imgHeight - 1;
            int j1 = background.imgWidth * anInt945 * speed;
            byte abyte0[] = background.imgPixels;
            byte abyte3[] = aByteArray912;

            for (int i2 = 0; i2 <= k; i2++) {
                abyte3[i2] = abyte0[i2 - j1 & k];
            }

            background.imgPixels = abyte3;
            aByteArray912 = abyte0;
            Rasterizer.method370(17);
            anInt854++;

            if (anInt854 > 1235) {
                anInt854 = 0;
                getOut().putOpcode(226);
                getOut().putByte(0);
                int l2 = getOut().position;
                getOut().putShort(58722);
                getOut().putByte(240);
                getOut().putShort((int) (Math.random() * 65536D));
                getOut().putByte((int) (Math.random() * 256D));

                if ((int) (Math.random() * 2D) == 0) {
                    getOut().putShort(51825);
                }

                getOut().putByte((int) (Math.random() * 256D));
                getOut().putShort((int) (Math.random() * 65536D));
                getOut().putShort(7130);
                getOut().putShort((int) (Math.random() * 65536D));
                getOut().putShort(61657);
                getOut().putVariableSizeByte(getOut().position - l2);
            }
        }

        if (Rasterizer.anIntArray1480[24] >= j) {
            speed = 2;
            Background background_35 = Rasterizer.aBackgroundArray1474s[24];
            int l = background_35.imgWidth * background_35.imgHeight - 1;
            int k1 = background_35.imgWidth * anInt945 * speed;
            byte abyte1[] = background_35.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_35.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(24);
        }
        /// re-texture items DOPE
        if (Rasterizer.anIntArray1480[36] >= j) {
            speed = 2;
            Background background_34 = Rasterizer.aBackgroundArray1474s[36];
            int l = background_34.imgWidth * background_34.imgHeight - 1;
            int k1 = background_34.imgWidth * anInt945 * speed;
            byte abyte1[] = background_34.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_34.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(36);
        }
        if (Rasterizer.anIntArray1480[51] >= j) {
            speed = 2;
            Background background_33 = Rasterizer.aBackgroundArray1474s[51];
            int l = background_33.imgWidth * background_33.imgHeight - 1;
            int k1 = background_33.imgWidth * anInt945 * speed;
            byte abyte1[] = background_33.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_33.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(51);
        }
        if (Rasterizer.anIntArray1480[52] >= j) {
            speed = 2;
            Background background_32 = Rasterizer.aBackgroundArray1474s[52];
            int l = background_32.imgWidth * background_32.imgHeight - 1;
            int k1 = background_32.imgWidth * anInt945 * speed;
            byte abyte1[] = background_32.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_32.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(52);
        }
        if (Rasterizer.anIntArray1480[53] >= j) {
            speed = 2;
            Background background_31 = Rasterizer.aBackgroundArray1474s[53];
            int l = background_31.imgWidth * background_31.imgHeight - 1;
            int k1 = background_31.imgWidth * anInt945 * speed;
            byte abyte1[] = background_31.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_31.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(53);
        }
        if (Rasterizer.anIntArray1480[54] >= j) {
            speed = 2;
            Background background_30 = Rasterizer.aBackgroundArray1474s[54];
            int l = background_30.imgWidth * background_30.imgHeight - 1;
            int k1 = background_30.imgWidth * anInt945 * speed;
            byte abyte1[] = background_30.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_30.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(54);
        }
        if (Rasterizer.anIntArray1480[55] >= j) {
            speed = 2;
            Background background_29 = Rasterizer.aBackgroundArray1474s[55];
            int l = background_29.imgWidth * background_29.imgHeight - 1;
            int k1 = background_29.imgWidth * anInt945 * speed;
            byte abyte1[] = background_29.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_29.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(55);
        }
        if (Rasterizer.anIntArray1480[95] >= j) {
            speed = 2;
            Background background_29 = Rasterizer.aBackgroundArray1474s[95];
            int l = background_29.imgWidth * background_29.imgHeight - 1;
            int k1 = background_29.imgWidth * anInt945 * speed;
            byte abyte1[] = background_29.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_29.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(95);
        }
        if (Rasterizer.anIntArray1480[56] >= j) {
            speed = 2;
            Background background_28 = Rasterizer.aBackgroundArray1474s[56];
            int l = background_28.imgWidth * background_28.imgHeight - 1;
            int k1 = background_28.imgWidth * anInt945 * speed;
            byte abyte1[] = background_28.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_28.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(56);
        }
        if (Rasterizer.anIntArray1480[57] >= j) {
            speed = 2;
            Background background_27 = Rasterizer.aBackgroundArray1474s[57];
            int l = background_27.imgWidth * background_27.imgHeight - 1;
            int k1 = background_27.imgWidth * anInt945 * speed;
            byte abyte1[] = background_27.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_27.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(57);
        }
        if (Rasterizer.anIntArray1480[58] >= j) {
            speed = 2;
            Background background_26 = Rasterizer.aBackgroundArray1474s[58];
            int l = background_26.imgWidth * background_26.imgHeight - 1;
            int k1 = background_26.imgWidth * anInt945 * speed;
            byte abyte1[] = background_26.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_26.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(58);
        }
        if (Rasterizer.anIntArray1480[59] >= j) {
            speed = 2;
            Background background_25 = Rasterizer.aBackgroundArray1474s[59];
            int l = background_25.imgWidth * background_25.imgHeight - 1;
            int k1 = background_25.imgWidth * anInt945 * speed;
            byte abyte1[] = background_25.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_25.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(59);
        }
        if (Rasterizer.anIntArray1480[87] >= j) {
            speed = 2;
            Background background_25 = Rasterizer.aBackgroundArray1474s[87];
            int l = background_25.imgWidth * background_25.imgHeight - 1;
            int k1 = background_25.imgWidth * anInt945 * speed;
            byte abyte1[] = background_25.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_25.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(87);
        }
        if (Rasterizer.anIntArray1480[79] >= j) {
            speed = 2;
            Background background_25 = Rasterizer.aBackgroundArray1474s[79];
            int l = background_25.imgWidth * background_25.imgHeight - 1;
            int k1 = background_25.imgWidth * anInt945 * speed;
            byte abyte1[] = background_25.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_25.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(79);
        }

        if (Rasterizer.anIntArray1480[60] >= j) {
            speed = 2;
            Background background_24 = Rasterizer.aBackgroundArray1474s[60];
            int l = background_24.imgWidth * background_24.imgHeight - 1;
            int k1 = background_24.imgWidth * anInt945 * speed;
            byte abyte1[] = background_24.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_24.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(60);
        }
        if (Rasterizer.anIntArray1480[61] >= j) {
            speed = 2;
            Background background_23 = Rasterizer.aBackgroundArray1474s[61];
            int l = background_23.imgWidth * background_23.imgHeight - 1;
            int k1 = background_23.imgWidth * anInt945 * speed;
            byte abyte1[] = background_23.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_23.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(61);
        }
        if (Rasterizer.anIntArray1480[62] >= j) {
            speed = 2;
            Background background_22 = Rasterizer.aBackgroundArray1474s[62];
            int l = background_22.imgWidth * background_22.imgHeight - 1;
            int k1 = background_22.imgWidth * anInt945 * speed;
            byte abyte1[] = background_22.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_22.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(62);
        }
        if (Rasterizer.anIntArray1480[63] >= j) {
            speed = 2;
            Background background_21 = Rasterizer.aBackgroundArray1474s[63];
            int l = background_21.imgWidth * background_21.imgHeight - 1;
            int k1 = background_21.imgWidth * anInt945 * speed;
            byte abyte1[] = background_21.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_21.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(63);
        }
        if (Rasterizer.anIntArray1480[64] >= j) {
            speed = 2;
            Background background_20 = Rasterizer.aBackgroundArray1474s[64];
            int l = background_20.imgWidth * background_20.imgHeight - 1;
            int k1 = background_20.imgWidth * anInt945 * speed;
            byte abyte1[] = background_20.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_20.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(64);
        }
        if (Rasterizer.anIntArray1480[65] >= j) {
            speed = 2;
            Background background_19 = Rasterizer.aBackgroundArray1474s[65];
            int l = background_19.imgWidth * background_19.imgHeight - 1;
            int k1 = background_19.imgWidth * anInt945 * speed;
            byte abyte1[] = background_19.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_19.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(65);
        }
        if (Rasterizer.anIntArray1480[66] >= j) {
            speed = 2;
            Background background_18 = Rasterizer.aBackgroundArray1474s[66];
            int l = background_18.imgWidth * background_18.imgHeight - 1;
            int k1 = background_18.imgWidth * anInt945 * speed;
            byte abyte1[] = background_18.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_18.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(66);
        }
        if (Rasterizer.anIntArray1480[67] >= j) {
            speed = 2;
            Background background_17 = Rasterizer.aBackgroundArray1474s[67];
            int l = background_17.imgWidth * background_17.imgHeight - 1;
            int k1 = background_17.imgWidth * anInt945 * speed;
            byte abyte1[] = background_17.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_17.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(67);
        }
        if (Rasterizer.anIntArray1480[68] >= j) {
            speed = 2;
            Background background_16 = Rasterizer.aBackgroundArray1474s[68];
            int l = background_16.imgWidth * background_16.imgHeight - 1;
            int k1 = background_16.imgWidth * anInt945 * speed;
            byte abyte1[] = background_16.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_16.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(68);
        }
        if (Rasterizer.anIntArray1480[90] >= j) {
            speed = 2;
            Background background_16 = Rasterizer.aBackgroundArray1474s[90];
            int l = background_16.imgWidth * background_16.imgHeight - 1;
            int k1 = background_16.imgWidth * anInt945 * speed;
            byte abyte1[] = background_16.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_16.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(90);
        }
        if (Rasterizer.anIntArray1480[69] >= j) {
            speed = 2;
            Background background_15 = Rasterizer.aBackgroundArray1474s[69];
            int l = background_15.imgWidth * background_15.imgHeight - 1;
            int k1 = background_15.imgWidth * anInt945 * speed;
            byte abyte1[] = background_15.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_15.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(69);
        }
        if (Rasterizer.anIntArray1480[70] >= j) {
            speed = 2;
            Background background_14 = Rasterizer.aBackgroundArray1474s[70];
            int l = background_14.imgWidth * background_14.imgHeight - 1;
            int k1 = background_14.imgWidth * anInt945 * speed;
            byte abyte1[] = background_14.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_14.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(70);
        }
        if (Rasterizer.anIntArray1480[71] >= j) {
            speed = 2;
            Background background_13 = Rasterizer.aBackgroundArray1474s[71];
            int l = background_13.imgWidth * background_13.imgHeight - 1;
            int k1 = background_13.imgWidth * anInt945 * speed;
            byte abyte1[] = background_13.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_13.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(71);
        }
        if (Rasterizer.anIntArray1480[72] >= j) {
            speed = 2;
            Background background_12 = Rasterizer.aBackgroundArray1474s[72];
            int l = background_12.imgWidth * background_12.imgHeight - 1;
            int k1 = background_12.imgWidth * anInt945 * speed;
            byte abyte1[] = background_12.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_12.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(72);
        }
        if (Rasterizer.anIntArray1480[73] >= j) {
            speed = 2;
            Background background_11 = Rasterizer.aBackgroundArray1474s[73];
            int l = background_11.imgWidth * background_11.imgHeight - 1;
            int k1 = background_11.imgWidth * anInt945 * speed;
            byte abyte1[] = background_11.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_11.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(73);
        }
        if (Rasterizer.anIntArray1480[74] >= j) {
            speed = 2;
            Background background_10 = Rasterizer.aBackgroundArray1474s[74];
            int l = background_10.imgWidth * background_10.imgHeight - 1;
            int k1 = background_10.imgWidth * anInt945 * speed;
            byte abyte1[] = background_10.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_10.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(74);
        }
        if (Rasterizer.anIntArray1480[75] >= j) {
            speed = 2;
            Background background_9 = Rasterizer.aBackgroundArray1474s[75];
            int l = background_9.imgWidth * background_9.imgHeight - 1;
            int k1 = background_9.imgWidth * anInt945 * speed;
            byte abyte1[] = background_9.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_9.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(75);
        }
        if (Rasterizer.anIntArray1480[76] >= j) {
            speed = 2;
            Background background_8 = Rasterizer.aBackgroundArray1474s[76];
            int l = background_8.imgWidth * background_8.imgHeight - 1;
            int k1 = background_8.imgWidth * anInt945 * speed;
            byte abyte1[] = background_8.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_8.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(76);
        }
        if (Rasterizer.anIntArray1480[77] >= j) {
            speed = 2;
            Background background_7 = Rasterizer.aBackgroundArray1474s[77];
            int l = background_7.imgWidth * background_7.imgHeight - 1;
            int k1 = background_7.imgWidth * anInt945 * speed;
            byte abyte1[] = background_7.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_7.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(77);
        }
        if (Rasterizer.anIntArray1480[78] >= j) {
            speed = 2;
            Background background_6 = Rasterizer.aBackgroundArray1474s[78];
            int l = background_6.imgWidth * background_6.imgHeight - 1;
            int k1 = background_6.imgWidth * anInt945 * speed;
            byte abyte1[] = background_6.imgPixels;
            byte abyte4[] = aByteArray912;

            for (int j2 = 0; j2 <= l; j2++) {
                abyte4[j2] = abyte1[j2 - k1 & l];
            }

            background_6.imgPixels = abyte4;
            aByteArray912 = abyte1;
            Rasterizer.method370(78);
        }

        if (Rasterizer.anIntArray1480[34] >= j) {
            speed = 2;
            Background background_5 = Rasterizer.aBackgroundArray1474s[34];
            int i1 = background_5.imgWidth * background_5.imgHeight - 1;
            int l1 = background_5.imgWidth * anInt945 * speed;
            byte abyte2[] = background_5.imgPixels;
            byte abyte5[] = aByteArray912;

            for (int k2 = 0; k2 <= i1; k2++) {
                abyte5[k2] = abyte2[k2 - l1 & i1];
            }

            background_5.imgPixels = abyte5;
            aByteArray912 = abyte2;
            Rasterizer.method370(34);
        }
        if (Rasterizer.anIntArray1480[50] >= j) {
            speed = 2;
            Background background_4 = Rasterizer.aBackgroundArray1474s[50];
            int i1 = background_4.imgWidth * background_4.imgHeight - 1;
            int l1 = background_4.imgWidth * anInt945 * speed;
            byte abyte2[] = background_4.imgPixels;
            byte abyte5[] = aByteArray912;

            for (int k2 = 0; k2 <= i1; k2++) {
                abyte5[k2] = abyte2[k2 - l1 & i1];
            }

            background_4.imgPixels = abyte5;
            aByteArray912 = abyte2;
            Rasterizer.method370(50);
        }
        if (Rasterizer.anIntArray1480[1] >= j) {
            speed = 2;
            Background background_3 = Rasterizer.aBackgroundArray1474s[1];
            int i1 = background_3.imgWidth * background_3.imgHeight - 1;
            int l1 = background_3.imgWidth * anInt945 * speed;
            byte abyte2[] = background_3.imgPixels;
            byte abyte5[] = aByteArray912;

            for (int k2 = 0; k2 <= i1; k2++) {
                abyte5[k2] = abyte2[k2 - l1 & i1];
            }

            background_3.imgPixels = abyte5;
            aByteArray912 = abyte2;
            Rasterizer.method370(1);
        }

        if (Rasterizer.anIntArray1480[40] >= j) {
            speed = 1;
            Background background_2 = Rasterizer.aBackgroundArray1474s[40];
            int i1 = background_2.imgWidth * background_2.imgHeight - 1;
            int l1 = background_2.imgWidth * anInt945 * speed;
            byte abyte2[] = background_2.imgPixels;
            byte abyte5[] = aByteArray912;

            for (int k2 = 0; k2 <= i1; k2++) {
                abyte5[k2] = abyte2[k2 - l1 & i1];
            }

            background_2.imgPixels = abyte5;
            aByteArray912 = abyte2;
            Rasterizer.method370(40);
        }
    }

    private void processTextCycles() {
        for (int i = -1; i < playerCount; i++) {
            int j;
            if (i == -1) {
                j = getMyPlayerIndex();
            } else {
                j = playerIndices[i];
            }
            Player player = playerArray[j];
            if (player != null && player.textCycle > 0) {
                player.textCycle--;
                if (player.textCycle == 0) {
                    player.textSpoken = null;
                }
            }
        }
        for (int k = 0; k < npcCount; k++) {
            int l = npcIndices[k];
            NPC npc = npcArray[l];
            if (npc != null && npc.textCycle > 0) {
                npc.textCycle--;
                if (npc.textCycle == 0) {
                    npc.textSpoken = null;
                }
            }
        }
    }

    private int method42(int i, int j, int k) {
        int l = k >> 7;
        int i1 = j >> 7;
        if (l < 0 || i1 < 0 || l > 103 || i1 > 103) {
            return 0;
        }
        int j1 = i;
        if (j1 < 3 && (tileFlags[1][l][i1] & 2) == 2) {
            j1++;
        }
        int k1 = k & 0x7f;
        int l1 = j & 0x7f;
        int i2 = tileHeights[j1][l][i1] * (128 - k1) + tileHeights[j1][l + 1][i1] * k1 >> 7;
        int j2 = tileHeights[j1][l][i1 + 1] * (128 - k1) + tileHeights[j1][l + 1][i1 + 1] * k1 >> 7;
        return i2 * (128 - l1) + j2 * l1 >> 7;
    }

    public void method45() {
        aBoolean1031 = true;
        for (int j = 0; j < 7; j++) {
            myAppearance[j] = -1;
            for (int k = 0; k < IdentityKit.getLength(); k++) {
                if (IdentityKit.cache[k].isaBoolean662()
                        || IdentityKit.cache[k].getAnInt657() != j + (isMale ? 0 : 7)) {
                    continue;
                }
                myAppearance[j] = k;
                break;
            }
        }
    }

    private void updateNpcMovement(int i, ByteBuffer stream) {
        int asdf = 0;
        while (stream.bitPosition + 21 < i * 8) {
            int k = stream.getBits(14);
            if (k == 16383) {
                break;
            }
            if (npcArray[k] == null) {
                npcArray[k] = new NPC();
            }
            NPC npc = npcArray[k];
            npcIndices[npcCount++] = k;
            npc.loopCycle = loopCycle;
            int l = stream.getBits(5);
            if (l > 15) {
                l -= 32;
            }
            int i1 = stream.getBits(5);
            if (i1 > 15) {
                i1 -= 32;
            }
            int j1 = stream.getBits(1);
            int npcid = stream.getBits(Configuration.NPC_BITS);
            npc.definitionOverride = MobDefinition.get(npcid);
            int k1 = stream.getBits(1);
            if (k1 == 1) {
                playersToUpdate[playersToUpdateCount++] = k;
            }
            npc.anInt1540 = npc.definitionOverride.npcSizeInSquares;
            npc.anInt1504 = npc.definitionOverride.degreesToTurn;
            npc.anInt1554 = npc.definitionOverride.walkAnimation;
            npc.anInt1555 = npc.definitionOverride.walkingBackwardsAnimation;
            npc.anInt1556 = npc.definitionOverride.walkLeftAnimation;
            npc.anInt1557 = npc.definitionOverride.walkRightAnimation;
            npc.anInt1511 = npc.definitionOverride.standAnimation;
            npc.setPos(myPlayer.smallX[0] + i1, myPlayer.smallY[0] + l, j1 == 1);
        }
        stream.finishBitAccess();
    }

    private void method47(boolean flag) {
        if (myPlayer.x >> 7 == destX && myPlayer.y >> 7 == destY) {
            destX = 0;
        }
        int j = playerCount;
        if (flag) {
            j = 1;
        }
        for (int l = 0; l < j; l++) {
            Player player;
            int i1;
            if (flag) {
                player = myPlayer;
                i1 = getMyPlayerIndex() << 14;
            } else {
                player = playerArray[playerIndices[l]];
                i1 = playerIndices[l] << 14;
            }
            if (player == null || !player.isVisible()) {
                continue;
            }
            // here
            player.aBoolean1699 = playerCount > 200 && !flag && player.anInt1517 == player.anInt1511;
            int j1 = player.x >> 7;
            int k1 = player.y >> 7;
            if (j1 < 0 || j1 >= 104 || k1 < 0 || k1 >= 104) {
                continue;
            }
            if (player.aModel_1714 != null && loopCycle >= player.anInt1707 && loopCycle < player.anInt1708) {
                player.aBoolean1699 = false;
                player.anInt1709 = method42(plane, player.y, player.x);
                scene.method286(plane, player.y, player, player.anInt1552, player.anInt1722, player.x, player.anInt1709,
                        player.anInt1719, player.anInt1721, i1, player.anInt1720);
                continue;
            }
            if ((player.x & 0x7f) == 64 && (player.y & 0x7f) == 64) {
                if (anIntArrayArray929[j1][k1] == anInt1265) {
                    continue;
                }
                anIntArrayArray929[j1][k1] = anInt1265;
            }
            player.anInt1709 = method42(plane, player.y, player.x);
            scene.method285(plane, player.anInt1552, player.anInt1709, i1, player.y, 60, player.x, player,
                    player.aBoolean1541);
        }
    }

    private void processPlayerUpdating(ByteBuffer stream) {
        for (int j = 0; j < playersToUpdateCount; j++) {
            int k = playersToUpdate[j];
            Player player = playerArray[k];
            int l = stream.getUnsignedByte();
            if ((l & 0x40) != 0) {
                l += stream.getUnsignedByte() << 8;
            }
            readPlayerUpdateMask(l, k, stream, player);
        }
    }

    private void method50(int y, int primaryColor, int x, int secondaryColor, int z) {
        int uid = scene.method300(z, x, y);
        if ((uid ^ 0xffffffffffffffffL) != -1L) {
            int resourceTag = scene.fetchObjectIDTagForPosition(z, x, y, uid);
            int direction = resourceTag >> 6 & 3;// direction
            int type = resourceTag & 0x1f;// type
            int color = primaryColor;// color
            if (uid > 0) {
                color = secondaryColor;
            }
            int mapPixels[] = miniMapRegions.myPixels;
            int pixel = 24624 + x * 4 + (103 - y) * 512 * 4;
            int objectId = scene.fetchWallDecorationNewUID(z, x, y);
            ObjectDefinition objDef = ObjectDefinition.forID(objectId);
            if ((objDef.mapSceneID ^ 0xffffffff) == 0) {
                if (type == 0 || type == 2) {
                    if (direction == 0) {
                        mapPixels[pixel] = color;
                        mapPixels[pixel + 512] = color;
                        mapPixels[1024 + pixel] = color;
                        mapPixels[1536 + pixel] = color;
                    } else if ((direction ^ 0xffffffff) == -2) {
                        mapPixels[pixel] = color;
                        mapPixels[pixel + 1] = color;
                        mapPixels[pixel + 2] = color;
                        mapPixels[3 + pixel] = color;
                    } else if (direction == 2) {
                        mapPixels[pixel - -3] = color;
                        mapPixels[3 + pixel + 512] = color;
                        mapPixels[3 + pixel + 1024] = color;
                        mapPixels[1536 + pixel - -3] = color;
                    } else if (direction == 3) {
                        mapPixels[pixel + 1536] = color;
                        mapPixels[pixel + 1536 + 1] = color;
                        mapPixels[2 + pixel + 1536] = color;
                        mapPixels[pixel + 1539] = color;
                    }
                }
                if (type == 3) {
                    if (direction == 0) {
                        mapPixels[pixel] = color;
                    } else if (direction == 1) {
                        mapPixels[pixel + 3] = color;
                    } else if (direction == 2) {
                        mapPixels[pixel + 3 + 1536] = color;
                    } else if (direction == 3) {
                        mapPixels[pixel + 1536] = color;
                    }
                }
                if (type == 2) {
                    if (direction == 3) {
                        mapPixels[pixel] = color;
                        mapPixels[pixel + 512] = color;
                        mapPixels[pixel + 1024] = color;
                        mapPixels[pixel + 1536] = color;
                    } else if (direction == 0) {
                        mapPixels[pixel] = color;
                        mapPixels[pixel + 1] = color;
                        mapPixels[pixel + 2] = color;
                        mapPixels[pixel + 3] = color;
                    } else if (direction == 1) {
                        mapPixels[pixel + 3] = color;
                        mapPixels[pixel + 3 + 512] = color;
                        mapPixels[pixel + 3 + 1024] = color;
                        mapPixels[pixel + 3 + 1536] = color;
                    } else if (direction == 2) {
                        mapPixels[pixel + 1536] = color;
                        mapPixels[pixel + 1536 + 1] = color;
                        mapPixels[pixel + 1536 + 2] = color;
                        mapPixels[pixel + 1536 + 3] = color;
                    }
                }
            }
        }
        uid = scene.method302(z, x, y);
        if (uid != 0) {
            int resourceTag = scene.fetchObjectIDTagForPosition(z, x, y, uid);
            int direction = resourceTag >> 6 & 3;
            int type = resourceTag & 0x1f;
            int objectId = scene.fetchObjectMeshNewUID(z, x, y);
            ObjectDefinition objDef = ObjectDefinition.forID(objectId);
            if (objDef.mapSceneID != -1) {
                Background scene = mapScenes[objDef.mapSceneID];
                if (scene != null) {
                    int sceneX = (objDef.sizeX * 4 - scene.imgWidth) / 2;
                    int sceneY = (objDef.sizeY * 4 - scene.imgHeight) / 2;
                    scene.method361(48 + x * 4 + sceneX, 48 + (104 - y - objDef.sizeY) * 4 + sceneY);
                }
            } else if (type == 9) {
                int color = 0xeeeeee;
                if (uid > 0) {
                    color = 0xee0000;
                }
                int mapPixels[] = miniMapRegions.myPixels;
                int pixel = 24624 + x * 4 + (103 - y) * 512 * 4;
                if (direction == 0 || direction == 2) {
                    mapPixels[pixel + 1536] = color;
                    mapPixels[pixel + 1024 + 1] = color;
                    mapPixels[pixel + 512 + 2] = color;
                    mapPixels[pixel + 3] = color;
                } else {
                    mapPixels[pixel] = color;
                    mapPixels[pixel + 512 + 1] = color;
                    mapPixels[pixel + 1024 + 2] = color;
                    mapPixels[pixel + 1536 + 3] = color;
                }
            }
        }
        uid = scene.fetchGroundDecorationNewUID(z, x, y);
        if (uid > 0) {
            ObjectDefinition objDef = ObjectDefinition.forID(uid);
            if (objDef.mapSceneID != -1) {
                Background scene = mapScenes[objDef.mapSceneID];
                if (scene != null) {
                    int sceneX = (objDef.sizeX * 4 - scene.imgWidth) / 2;
                    int sceneY = (objDef.sizeY * 4 - scene.imgHeight) / 2;
                    scene.method361(48 + x * 4 + sceneX, 48 + (104 - y - objDef.sizeY) * 4 + sceneY);
                }
            }
        }
    }

    private int getMapLoadingState() {
        if (!floorMaps.equals("") || !objectMaps.equals("")) {
            floorMaps = "";
            objectMaps = "";
        }
        for (int i = 0; i < terrainData.length; i++) {
            floorMaps += "  " + floorMap[i];
            objectMaps += "  " + objectMap[i];
            if (terrainData[i] == null && floorMap[i] != -1) {
                return -1;
            }
            if (objectData[i] == null && objectMap[i] != -1) {
                return -2;
            }
        }
        boolean flag = true;
        for (int j = 0; j < terrainData.length; j++) {
            byte abyte0[] = objectData[j];
            if (abyte0 != null) {
                int k = (mapCoordinates[j] >> 8) * 64 - regionBaseX;
                int l = (mapCoordinates[j] & 0xff) * 64 - regionBaseY;
                if (requestMapReconstruct) {
                    k = 10;
                    l = 10;
                }
                flag &= ObjectManager.method189(k, abyte0, l, mapCoordinates[j]);
            }
        }
        if (!flag) {
            return -3;
        }
        if (aBoolean1080) {
            return -4;
        } else {
            loadingStage = 2;
            ObjectManager.anInt131 = plane;
            loadRegion();
            // if (loggedIn) {
            getOut().putOpcode(121);
            // }
            return 0;
        }
    }

    private void method55() {
        for (PlayerProjectile class30_sub2_sub4_sub4 = (PlayerProjectile) getProjectiles()
                .reverseGetFirst(); class30_sub2_sub4_sub4 != null; class30_sub2_sub4_sub4 = (PlayerProjectile) getProjectiles()
                .reverseGetNext()) {
            if (class30_sub2_sub4_sub4.anInt1597 != plane || loopCycle > class30_sub2_sub4_sub4.anInt1572) {
                class30_sub2_sub4_sub4.unlink();
            } else if (loopCycle >= class30_sub2_sub4_sub4.anInt1571) {
                if (class30_sub2_sub4_sub4.anInt1590 > 0) {
                    NPC npc = npcArray[class30_sub2_sub4_sub4.anInt1590 - 1];
                    if (npc != null && npc.x >= 0 && npc.x < 13312 && npc.y >= 0 && npc.y < 13312) {
                        class30_sub2_sub4_sub4.method455(loopCycle, npc.y,
                                method42(class30_sub2_sub4_sub4.anInt1597, npc.y, npc.x)
                                        - class30_sub2_sub4_sub4.anInt1583,
                                npc.x);
                    }
                }
                if (class30_sub2_sub4_sub4.anInt1590 < 0) {
                    int j = -class30_sub2_sub4_sub4.anInt1590 - 1;
                    Player player;
                    if (j == playerId) {
                        player = myPlayer;
                    } else {
                        player = playerArray[j];
                    }
                    if (player != null && player.x >= 0 && player.x < 13312 && player.y >= 0 && player.y < 13312) {
                        class30_sub2_sub4_sub4.method455(loopCycle, player.y,
                                method42(class30_sub2_sub4_sub4.anInt1597, player.y, player.x)
                                        - class30_sub2_sub4_sub4.anInt1583,
                                player.x);
                    }
                }
                class30_sub2_sub4_sub4.method456(anInt945);
                scene.method285(plane, class30_sub2_sub4_sub4.rotationY, (int) class30_sub2_sub4_sub4.aDouble1587, -1,
                        (int) class30_sub2_sub4_sub4.aDouble1586, 60, (int) class30_sub2_sub4_sub4.aDouble1585,
                        class30_sub2_sub4_sub4, false);
            }
        }

    }

    private final synchronized void method56(int i, boolean bool, int music) {
        if (musicIsntNull()) {
            nextSong = music;
            onDemandFetcher.requestFileData(2, nextSong);
            musicVolume2 = i;
            anInt139 = -1;
            aBoolean995 = true;
            anInt116 = -1;
        }
    }

    private final synchronized void method58(int i_30_, int volume, boolean bool, int music) {
        if (musicIsntNull()) {
            nextSong = music;
            onDemandFetcher.requestFileData(2, nextSong);
            musicVolume2 = volume;
            anInt139 = -1;
            aBoolean995 = true;
            anInt116 = i_30_;
        }
    }

    private void resetInterfaceAnimation(int i) {
        RSInterface class9 = RSInterface.interfaceCache[i];
        if (class9 == null || class9.children == null)
            return;
        for (int element : class9.children) {
            if (element == -1) {
                break;
            }
            RSInterface class9_1 = RSInterface.interfaceCache[element];
            if (class9_1.type == 1) {
                resetInterfaceAnimation(class9_1.id);
            }
            class9_1.anInt246 = 0;
            class9_1.anInt208 = 0;
        }
    }

    private void cleanObjectSpawnRequests() {
        Class30_Sub1 class30_sub1 = (Class30_Sub1) getaClass19_1179().reverseGetFirst();
        for (; class30_sub1 != null; class30_sub1 = (Class30_Sub1) getaClass19_1179().reverseGetNext()) {
            if (class30_sub1.anInt1294 == -1) {
                class30_sub1.anInt1302 = 0;
                method89(class30_sub1);
            } else {
                class30_sub1.unlink();
            }
        }

    }


    private void scrollInterfaceBottom(int maxWidth, int width, int mouseX, int mouseY, RSInterface class9,
                                       int maxHeight, boolean flag, int scrollMax) {
        int anInt992;
        if (aBoolean972) {
            anInt992 = 32;
        } else {
            anInt992 = 0;
        }
        aBoolean972 = false;

        if (mouseX >= maxWidth && mouseX < maxWidth + 16 && mouseY >= maxHeight && mouseY < maxHeight + 16) {
            class9.scrollPosition -= anInt1213 * 4;
            if (flag) {
                //  needDrawTabArea = true;
            }
        } else if (mouseY >= maxHeight && mouseY < maxHeight + 16 && mouseX >= (maxWidth + width) - 16
                && mouseX < maxWidth + width) {
            class9.scrollPosition += anInt1213 * 4;
            if (flag) {
                // needDrawTabArea = true;
            }
        } else if (mouseY >= maxHeight - anInt992 && mouseY < maxHeight + 16 + anInt992 && mouseX >= maxWidth + 16
                && mouseX < (maxWidth + width) - 16 && anInt1213 > 0) {
            int l1 = ((width - 32) * width) / scrollMax;
            if (l1 < 8) {
                l1 = 8;
            }
            int i2 = mouseX - maxWidth - 16 - l1 / 2;
            int j2 = width - 32 - l1;
            class9.scrollPosition = ((scrollMax - width) * i2) / j2;
            if (flag) {
                //  needDrawTabArea = true;
            }
            aBoolean972 = true;
        }
    }

    private void scrollInterface(int i, int j, int k, int l, RSInterface class9, int i1, boolean flag, int j1) {
        int anInt992;
        if (aBoolean972) {
            anInt992 = 32;
        } else {
            anInt992 = 0;
        }
        aBoolean972 = false;
        if (k >= i && k < i + 16 && l >= i1 && l < i1 + 16) {
            class9.scrollPosition -= anInt1213 * 4;
            if (flag) {
            }
        } else if (k >= i && k < i + 16 && l >= i1 + j - 16 && l < i1 + j) {
            class9.scrollPosition += anInt1213 * 4;
            if (flag) {
            }
        } else if (k >= i - anInt992 && k < i + 16 + anInt992 && l >= i1 + 16 && l < i1 + j - 16 && anInt1213 > 0) {
            int l1 = (j - 32) * j / j1;
            if (l1 < 8) {
                l1 = 8;
            }
            int i2 = l - i1 - 16 - l1 / 2;
            int j2 = j - 32 - l1;
            class9.scrollPosition = (j1 - j) * i2 / j2;
            if (flag) {
            }
            aBoolean972 = true;
        }
    }

    // might be needed
    private boolean method66(int i, int j, int k, int id) {
        int j1 = scene.fetchObjectIDTagForPosition(plane, k, j, i);
        if (i == -1) {
            return false;
        }
        int k1 = j1 & 0x1f;
        int l1 = j1 >> 6 & 3;
        if (k1 == 10 || k1 == 11 || k1 == 22) {
            ObjectDefinition class46 = ObjectDefinition.forID(id);
            int i2;
            int j2;
            if (l1 == 0 || l1 == 2) {
                i2 = class46.sizeX;
                j2 = class46.sizeY;
            } else {
                i2 = class46.sizeX;
                j2 = class46.sizeY;
            }
            int k2 = class46.plane;
            if (l1 != 0) {
                k2 = (k2 << l1 & 0xf) + (k2 >> 4 - l1);
            }
            doWalkTo(2, 0, j2, 0, myPlayer.smallY[0], i2, k2, j, myPlayer.smallX[0], false, k);
        } else {
            doWalkTo(2, l1, 0, k1 + 1, myPlayer.smallY[0], 0, 0, j, myPlayer.smallX[0], false, k);
        }
        crossX = super.saveClickX;
        crossY = super.saveClickY;
        crossType = 2;
        crossIndex = 0;
        return true;
    }

    private void method70() {
        /**
         * @depreciated This stops players from seeing messages in particular areas.
         *              Tutorial Island, etc.
         */
        anInt1251 = 0;
        int j = (myPlayer.x >> 7) + regionBaseX;
        int k = (myPlayer.y >> 7) + regionBaseY;
        if (j >= 3053 && j <= 3156 && k >= 3056 && k <= 3136) {
            anInt1251 = 1;
        }
        if (j >= 3072 && j <= 3118 && k >= 9492 && k <= 9535) {
            anInt1251 = 1;
        }
        if (anInt1251 == 1 && j >= 3139 && j <= 3199 && k >= 3008 && k <= 3062) {
            anInt1251 = 0;
        }
    }

    public void sendString(int identifier, String text) {
        text = identifier + "," + text;
        getOut().putOpcode(127);
        getOut().putByte(text.length() + 1);
        getOut().putString(text);
    }

    private void manageTextInput() {
        do {
            int key = readChar(-796);

            if (key == -1) {
                break;
            }
            if (openInterfaceID == 32600) {
                if (oksearchingplayers) {
                    RSInterface k = RSInterface.interfaceCache[32612];
                    String msg = k.message;
                    if (key >= 32 && key <= 122 && msg.length() < 20) {
                        msg += (char) key;
                        inputTaken = true;
                    }
                    if (key == 8 && msg.length() > 0) {
                        msg = msg.substring(0, msg.length() - 1);
                        inputTaken = true;
                    }
                    k.message = msg;
                    // sendString(32612, msg);
                    return;
                }
                if (oksearchingitems) {
                    RSInterface k = RSInterface.interfaceCache[32611];
                    String msg = k.message;
                    if (key >= 32 && key <= 122 && msg.length() < 20) {
                        msg += (char) key;
                        inputTaken = true;
                    }
                    if (key == 8 && msg.length() > 0) {
                        msg = msg.substring(0, msg.length() - 1);
                        inputTaken = true;
                    }
                    k.message = msg;
                    sendString(32611, msg);
                    return;
                }
            }
            if (key == 96) {
                break;
            }

            if (key == 167 || key == 96) {
                if (myRights >= 1 && myRights <= 4) {
                    consoleOpen = !consoleOpen;
                }
                return;
            }

           /* if (backDialogID == 310 || backDialogID == 306 || backDialogID == 315 || backDialogID == 321
                    || backDialogID == 4887 || backDialogID == 4900 || backDialogID == 6179 || backDialogID == 356
                    || backDialogID == 4882 || backDialogID == 356 || backDialogID == 359 || backDialogID == 363
                    || backDialogID == 368 || backDialogID == 374 || backDialogID == 4882 || backDialogID == 4887
                    || backDialogID == 4893 || backDialogID == 4900 || backDialogID == 968 || backDialogID == 973
                    || backDialogID == 979 || backDialogID == 986) {
                if (key == KeyEvent.VK_SPACE && clickDelay.elapsed(600)) {
                    clickDelay.reset();
                    getOut().putPacket(40);
                    getOut().p2(0);
                }
            }
            if (backDialogID != -1 && backDialogID != 2459 && backDialogID != 2480 && backDialogID != 2492
                    && backDialogID != 2469) {
                if (key == KeyEvent.VK_SPACE && clickDelay.elapsed(600)) {
                    clickDelay.reset();
                    getOut().putPacket(40);
                    getOut().p2(1);
                    aBoolean1149 = true;
                }
            }*/
            if (inputDialogState == 0) {
                if (backDialogID == 2459 || backDialogID == 2469 || backDialogID == 2480 || backDialogID == 2492
                        || backDialogID == 14170) {
                    if (key >= 49 && key <= 53 && clickDelay.elapsed(200)) {
                        clickDelay.reset();
                        sendPacket185(backDialogID + key - 47);
                    }
                }
            }


            if (consoleOpen) {
                if (key == 8 && consoleInput.length() > 0)
                    consoleInput = consoleInput.substring(0, consoleInput.length() - 1);
                if (key >= 32 && key <= 122 && consoleInput.length() < 80)
                    consoleInput += (char) key;

                if ((key == 13 || key == 10) && consoleInput.length() >= 1) {
                    printConsoleMessage(consoleInput, 0);
                    sendCommandPacket(consoleInput);
                    consoleInput = "";
                    inputTaken = true;
                }
                return;
            } else if (messagePromptRaised) {
                if (key >= 32 && key <= 122 && promptInput.length() < 80) {
                    promptInput += (char) key;
                    inputTaken = true;
                }

                if (key == 8 && promptInput.length() > 0) {
                    promptInput = promptInput.substring(0, promptInput.length() - 1);
                    inputTaken = true;
                }
                if (key == 13 || key == 10) {
                    messagePromptRaised = false;
                    inputTaken = true;

                    System.out.println("here");
                    if (friendsListAction == 1) {
                        long l = TextClass.longForName(promptInput);
                        addFriend(l);
                    }

                    if (friendsListAction == 2 && friendCount > 0) {
                        long l1 = TextClass.longForName(promptInput);
                        delFriend(l1);
                    }

                    if (interfaceButtonAction == 6199 && promptInput.length() > 0) {
                        String inp = "";
                        inp = inputString;
                        inputString = "::[CN] " + promptInput;
                        sendPacket(103);
                        inputString = inp;
                    }

                    if (interfaceButtonAction == 557 && promptInput.length() > 0) {
                        int length = promptInput.length();
                        char lastChar = promptInput.charAt(length - 1);

                        if (lastChar == 'k') {
                            promptInput = promptInput.substring(0, length - 1) + "000";
                        } else if (lastChar == 'm') {
                            promptInput = promptInput.substring(0, length - 1) + "000000";
                        } else if (lastChar == 'b') {
                            promptInput = promptInput.substring(0, length - 1) + "000000000";
                        }
                        inputString = "::mpremove " + promptInput;
                        sendPacket(103);
                    }

                    if (interfaceButtonAction == 558 && promptInput.length() > 0) {
                        int length = promptInput.length();
                        char lastChar = promptInput.charAt(length - 1);

                        if (lastChar == 'k') {
                            promptInput = promptInput.substring(0, length - 1) + "000";
                        } else if (lastChar == 'm') {
                            promptInput = promptInput.substring(0, length - 1) + "000000";
                        } else if (lastChar == 'b') {
                            promptInput = promptInput.substring(0, length - 1) + "000000000";
                        }
                        inputString = "::mpadd " + promptInput;
                        sendPacket(103);
                    }

                    if (interfaceButtonAction == 6199 && promptInput.length() > 0) {
                        String inp = inputString;
                        inputString = "::changeclanname " + promptInput;
                        sendPacket(103);
                        inputString = inp;
                    }

                    if (friendsListAction == 3 && promptInput.length() > 0) {
                        getOut().putOpcode(126);
                        getOut().putByte(0);
                        int k = getOut().position;
                        getOut().putLong(aLong953);
                        TextInput.writeChatboxText(promptInput, getOut());
                        getOut().putVariableSizeByte(getOut().position - k);
                        promptInput = TextInput.processText(promptInput);
                        // promptInput = Censor.doCensor(promptInput);
                        pushMessage(promptInput, 6, TextClass.fixName(TextClass.nameForLong(aLong953)));
                        if (privateChatMode == 2) {
                            privateChatMode = 1;
                            getOut().putOpcode(95);
                            getOut().putByte(publicChatMode);
                            getOut().putByte(privateChatMode);
                            getOut().putByte(tradeMode);
                        }
                    }

                    if (friendsListAction == 4 && ignoreCount < 100) {
                        long l2 = TextClass.longForName(promptInput);
                        addIgnore(l2);
                    }

                    if (friendsListAction == 5 && ignoreCount > 0) {
                        long l3 = TextClass.longForName(promptInput);
                        delIgnore(l3);
                    }

                    if (friendsListAction == 6) {
                        long l3 = TextClass.longForName(promptInput);
                        chatJoin(l3);
                    } else if ((this.friendsListAction == 12) && (this.promptInput.length() > 0)) {
                        if (promptInput.toLowerCase().matches("\\d+")) {
                            int goalLevel = Integer.parseInt(this.promptInput);
                            if (goalLevel > 99) {
                                goalLevel = 99;
                            }
                            int currentMaxLevel = maxStats[Skills.selectedSkillId];
                            if (Skills.selectedSkillId == 5 || Skills.selectedSkillId == 3) {
                                currentMaxLevel /= 100;
                            }
                            if ((goalLevel < 0) || (currentMaxLevel >= goalLevel)) {
                                Skills.selectedSkillId = -1;
                                return;
                            }
                            Skills.goalType = "Target Level: ";
                            Skills.goalData[Skills.selectedSkillId][0] = currentExp[Skills.selectedSkillId];
                            Skills.goalData[Skills.selectedSkillId][1] = PlayerHandler.getXPForLevel(goalLevel) + 1;
                            Skills.goalData[Skills.selectedSkillId][2] = (Skills.goalData[Skills.selectedSkillId][0]
                                    / Skills.goalData[Skills.selectedSkillId][1]) * 100;
                            saveGoals(myUsername);
                            Skills.selectedSkillId = -1;
                        }
                    } else if ((this.friendsListAction == 13) && (this.promptInput.length() > 0)
                            && ((this.promptInput.toLowerCase().matches("\\d+[a-z&&[kmb]]"))
                            || (this.promptInput.matches("\\d+")))) {
                        int goalExp = 0;
                        try {
                            goalExp = Integer.parseInt(promptInput.trim().toLowerCase().replaceAll("k", "000")
                                    .replaceAll("m", "000000").replaceAll("b", "000000000"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if ((goalExp <= 0) || (goalExp <= currentExp[Skills.selectedSkillId])) {
                            Skills.selectedSkillId = -1;
                            return;
                        } else if (goalExp > 1000000000) {
                            goalExp = 1000000000;
                        }
                        Skills.goalType = "Target Exp: ";
                        Skills.goalData[Skills.selectedSkillId][0] = currentExp[Skills.selectedSkillId];
                        Skills.goalData[Skills.selectedSkillId][1] = ((int) goalExp);
                        Skills.goalData[Skills.selectedSkillId][2] = (Skills.goalData[Skills.selectedSkillId][0]
                                / Skills.goalData[Skills.selectedSkillId][1] * 100);
                        saveGoals(myUsername);
                        Skills.selectedSkillId = -1;
                    }
                }
            } else if (inputFieldSelected != null
                    && ((openInterfaceID != -1 && RSInterface.interfaceCache[openInterfaceID] != null
                    && RSInterface.interfaceCache[openInterfaceID].hasInputField)
                    || (tabInterfaceIDs[tabID] != -1
                    && RSInterface.interfaceCache[tabInterfaceIDs[tabID]].hasInputField))) {
                RSInterface rsi = inputFieldSelected;
                if (rsi.inputToggled) {
                    if (key >= 32 && key <= 122 && rsi.rsFont.getTextWidth(rsi.inputText) < (rsi.width - 20)) {
                        rsi.inputText += (char) key;
                        inputTaken = true;
                        rsi.textInput.handleInput();
                    }
                    if (key == 8 && rsi.inputText.length() > 0) {
                        rsi.inputText = rsi.inputText.substring(0, rsi.inputText.length() - 1);
                        inputTaken = true;
                        rsi.textInput.handleInput();
                    }
                    if ((key == 13 || key == 10)) {
                        rsi.inputToggled = false;
                        inputTaken = true;
                        rsi.textInput.handleInput();
                        if (rsi.inputText.equals("")) {
                            rsi.inputText = rsi.defaultText;
                        }
                    }
                    return;
                }
            } else if (inputDialogState == 1) {
                if (key >= 48 && key <= 57 && amountOrNameInput.length() < 10) {
                    amountOrNameInput += (char) key;
                    inputTaken = true;
                }

                if (!amountOrNameInput.toLowerCase().contains("k") && !amountOrNameInput.toLowerCase().contains("m")
                        && !amountOrNameInput.toLowerCase().contains("b") && (key == 107 || key == 109) || key == 98) {
                    amountOrNameInput += (char) key;
                    inputTaken = true;
                }

                if (key == 8 && amountOrNameInput.length() > 0) {
                    amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
                    inputTaken = true;
                }

                if (key == 13 || key == 10) {
                    if (amountOrNameInput.length() > 0) {
                        if (amountOrNameInput.toLowerCase().contains("k")) {
                            amountOrNameInput = amountOrNameInput.replaceAll("k", "000");
                        } else if (amountOrNameInput.toLowerCase().contains("m")) {
                            amountOrNameInput = amountOrNameInput.replaceAll("m", "000000");
                        } else if (amountOrNameInput.toLowerCase().contains("b")) {
                            amountOrNameInput = amountOrNameInput.replaceAll("b", "000000000");

                        } else if (amountOrNameInput.toLowerCase().contains("t")) {
                            amountOrNameInput = amountOrNameInput.replaceAll("t", "000000000000");

                        }
                        long l = Long.valueOf(amountOrNameInput);

                        if (l > 2147483647) {
                            amountOrNameInput = "2147483647";
                        }

                        int amount = 0;
                        amount = Integer.parseInt(amountOrNameInput);
                        if (interfaceButtonAction == 557 && withdrawingMoneyFromPouch) {
                            getOut().putOpcode(7);
                            getOut().putInt(amount);
                            inputDialogState = 0;
                            inputTaken = true;
                            withdrawingMoneyFromPouch = false;
                            return;
                        }
                        getOut().putOpcode(208);
                        getOut().putInt(amount);
                        modifiableXValue = amount;
                    }

                    inputDialogState = 0;
                    inputTaken = true;
                }
            } else if (inputDialogState == 4) {
                System.out.println("inputDialogState == 4");
                if (key >= 48 && key <= 57 && amountOrNameInput.length() < 19) {
                    amountOrNameInput += (char) key;
                    inputTaken = true;
                }

                if (!amountOrNameInput.toLowerCase().contains("k") && !amountOrNameInput.toLowerCase().contains("m")
                        && !amountOrNameInput.toLowerCase().contains("b") && (key == 107 || key == 109) || key == 98) {
                    amountOrNameInput += (char) key;
                    inputTaken = true;
                }

                if (key == 8 && amountOrNameInput.length() > 0) {
                    amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
                    inputTaken = true;
                }

                if (key == 13 || key == 10) {
                    if (amountOrNameInput.length() > 0) {
                        if (amountOrNameInput.toLowerCase().contains("k")) {
                            amountOrNameInput = amountOrNameInput.replaceAll("k", "000");
                        } else if (amountOrNameInput.toLowerCase().contains("m")) {
                            amountOrNameInput = amountOrNameInput.replaceAll("m", "000000");
                        } else if (amountOrNameInput.toLowerCase().contains("b")) {
                            amountOrNameInput = amountOrNameInput.replaceAll("b", "000000000");
                        } else if (amountOrNameInput.toLowerCase().contains("t")) {
                            amountOrNameInput = amountOrNameInput.replaceAll("t", "000000000000");
                        }
                        BigInteger bi = new BigInteger(amountOrNameInput);
                        long amount = 0;
                        try {
                            bi.longValueExact();
                            amount = Long.valueOf(amountOrNameInput);
                        } catch (ArithmeticException e) {
                            amount = Long.MAX_VALUE;
                        }
                        getOut().putOpcode(209);
                        getOut().putLong(amount);
                        System.out.println("putting amount: " + amount);
                        modifiableXValue = (int) amount;
                    }

                    inputDialogState = 0;
                    inputTaken = true;
                }
            } else if (inputDialogState == 2) {
                if (key >= 32 && key <= 122 && amountOrNameInput.length() < 12) {
                    amountOrNameInput += (char) key;
                    inputTaken = true;
                }

                if (key == 8 && amountOrNameInput.length() > 0) {
                    amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
                    inputTaken = true;
                }

                if (key == 13 || key == 10) {
                    if (amountOrNameInput.length() > 0) {
                        getOut().putOpcode(60);
                        getOut().putByte(amountOrNameInput.length() + 1);
                        getOut().putString(amountOrNameInput);
                    }

                    inputDialogState = 0;
                    inputTaken = true;
                }
            } else if (inputDialogState == 3) {
                if (key == 10) {
                    getGrandExchange().totalItemResults = 0;
                    amountOrNameInput = "";
                    inputDialogState = 0;
                    inputTaken = true;
                }
                if (key == 13 || key == 10) {
                    if (amountOrNameInput.length() == 0) {
                        getGrandExchange().searching = false;
                        interfaceButtonAction = 0;
                    }
                }
                if (key >= 32 && key <= 122 && amountOrNameInput.length() < 40) {
                    amountOrNameInput += (char) key;
                    inputTaken = true;
                }
                if (key == 8 && amountOrNameInput.length() > 0) {
                    amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
                    inputTaken = true;
                }
            } else if (backDialogID == -1) {
                if (key >= 32 && key <= 122 && inputString.length() < 80) {
                    inputString += (char) key;
                    inputTaken = true;
                }

                if (key == 8 && inputString.length() > 0) {
                    inputString = inputString.substring(0, inputString.length() - 1);
                    inputTaken = true;
                }

                if (key == 9) {
                    tabToReplyPm();
                }

                if ((key == 13 || key == 10) && inputString.length() > 0) {
                   // System.out.println("inputString: " + inputString);
                    if (inputString.startsWith("/")) {
                        getOut().putOpcode(5);
                        getOut().putByte(inputString.substring(1).length() + 1);
                        getOut().putString(inputString.substring(1));
                        inputString = "";
                        return;
                    }

                    if (inputString.equalsIgnoreCase("::reload")) {
                        reloadInterface();
                        inputString = "";
                        return;
                    }

                    if (inputString.startsWith("::ie")) {
                        String string = inputString.replace("::ie ", "");
                        ItemEditor.getInstance(Integer.parseInt(string));
                        inputString = "";
                        return;
                    }

                    if (inputString.startsWith("::") && !inputString.startsWith("::[")) {
                        getOut().putOpcode(103);
                        getOut().putByte(inputString.length() - 1);
                        getOut().putString(inputString.substring(2));
                    } else {

                        String s = inputString.toLowerCase();
                        int j2 = 0;
                        if (s.startsWith("yellow:")) {
                            j2 = 0;
                            inputString = inputString.substring(7);
                        } else if (s.startsWith("red:")) {
                            j2 = 1;
                            inputString = inputString.substring(4);
                        } else if (s.startsWith("green:")) {
                            j2 = 2;
                            inputString = inputString.substring(6);
                        } else if (s.startsWith("cyan:")) {
                            j2 = 3;
                            inputString = inputString.substring(5);
                        } else if (s.startsWith("purple:")) {
                            j2 = 4;
                            inputString = inputString.substring(7);
                        } else if (s.startsWith("white:")) {
                            j2 = 5;
                            inputString = inputString.substring(6);
                        } else if (s.startsWith("flash1:")) {
                            j2 = 6;
                            inputString = inputString.substring(7);
                        } else if (s.startsWith("flash2:")) {
                            j2 = 7;
                            inputString = inputString.substring(7);
                        } else if (s.startsWith("flash3:")) {
                            j2 = 8;
                            inputString = inputString.substring(7);
                        } else if (s.startsWith("glow1:")) {
                            j2 = 9;
                            inputString = inputString.substring(6);
                        } else if (s.startsWith("glow2:")) {
                            j2 = 10;
                            inputString = inputString.substring(6);
                        } else if (s.startsWith("glow3:")) {
                            j2 = 11;
                            inputString = inputString.substring(6);
                        }
                        s = inputString.toLowerCase();
                        int i3 = 0;
                        if (s.startsWith("wave:")) {
                            i3 = 1;
                            inputString = inputString.substring(5);
                        } else if (s.startsWith("wave2:")) {
                            i3 = 2;
                            inputString = inputString.substring(6);
                        } else if (s.startsWith("shake:")) {
                            i3 = 3;
                            inputString = inputString.substring(6);
                        } else if (s.startsWith("scroll:")) {
                            i3 = 4;
                            inputString = inputString.substring(7);
                        } else if (s.startsWith("slide:")) {
                            i3 = 5;
                            inputString = inputString.substring(6);
                        }
                        getOut().putOpcode(4);
                        getOut().putByte(0);
                        int j3 = getOut().position;
                        getOut().method425(i3);
                        getOut().method425(j2);
                        aStream_834.position = 0;
                        TextInput.writeChatboxText(inputString, aStream_834);
                        getOut().method441(0, aStream_834.buffer, aStream_834.position);
                        getOut().putVariableSizeByte(getOut().position - j3);
                        inputString = TextInput.processText(inputString);
                        // inputString = Censor.doCensor(inputString);
                        myPlayer.textSpoken = inputString;
                        myPlayer.anInt1513 = j2;
                        myPlayer.anInt1531 = i3;
                        myPlayer.textCycle = 150;
                        int prefixRights = myRights;
                        int secondary = -1;
                        if (gameMode > 0) {
                            secondary = 11 + gameMode;
                        }
                        List<Integer> chatRights = new ArrayList<>();
                        chatRights.add(prefixRights);
                        chatRights.add(secondary);
                        String prefix = getPrefix(prefixRights) + (secondary > -1 ? getPrefix(secondary) : "");
                        pushMessage(myPlayer.textSpoken, 2, prefix + myPlayer.name,
                                myPlayer.loyaltyTitle, myPlayer.loyaltyColor, myPlayer.loyaltyPosition, chatRights);

                        if (publicChatMode == 2) {
                            publicChatMode = 3;
                            getOut().putOpcode(95);
                            getOut().putByte(publicChatMode);
                            getOut().putByte(privateChatMode);
                            getOut().putByte(tradeMode);
                        }
                    }
                    inputString = "";
                    inputTaken = true;
                    withdrawingMoneyFromPouch = false;
                }
            }
        } while (true);
    }

    public void reloadInterface() {
        Archive streamLoader_1 = getArchive(3, "interface", "interface", expectedCRCs[3], 35);
        Archive mediaArchive = getArchive(4, "2d graphics", "media", expectedCRCs[4], 40);
        TextDrawingArea[] aclass30_sub2_sub1_sub4s = {smallText, normalText, chatTextDrawingArea,
                aTextDrawingArea_1273};
        RSFontSystem[] newFonts = {newSmallFont, newRegularFont, newBoldFont};
        RSInterface.unpack(streamLoader_1, aclass30_sub2_sub1_sub4s, mediaArchive, newFonts);
    }

    private void readNPCUpdateMask(ByteBuffer stream) {
        for (int j = 0; j < playersToUpdateCount; j++) {
            int k = playersToUpdate[j];
            NPC npc = npcArray[k];
            int l = stream.getUnsignedByte();
            if ((l & 0x10) != 0) {
                int i1 = stream.getShortBigEndian();
                if (i1 == 65535) {
                    i1 = -1;
                }
                int i2 = stream.getUnsignedByte();
                if (i1 == npc.anim && i1 != -1) {
                    int l2 = Animation.cache[i1].delayType;
                    if (l2 == 1) {
                        npc.currentAnimFrame = 0;
                        npc.anInt1528 = 0;
                        npc.animationDelay = i2;
                        npc.anInt1530 = 0;
                    }
                    if (l2 == 2) {
                        npc.anInt1530 = 0;
                    }
                } else if (i1 == -1 || npc.anim == -1
                        || Animation.cache[i1].forcedPriority >= Animation.cache[npc.anim].forcedPriority) {
                    npc.anim = i1;
                    npc.currentAnimFrame = 0;
                    npc.anInt1528 = 0;
                    npc.animationDelay = i2;
                    npc.anInt1530 = 0;
                    npc.anInt1542 = npc.smallXYIndex;
                    try {
                        if (FrameReader.animationlist[Integer.parseInt(
                                Integer.toHexString(Animation.cache[i1].frameIDs[0]).substring(0,
                                        Integer.toHexString(Animation.cache[i1].frameIDs[0]).length() - 4),
                                16)].length == 0)
                            onDemandFetcher.requestFileData(1,
                                    Integer.parseInt(
                                            Integer.toHexString(Animation.cache[i1].frameIDs[0]).substring(0,
                                                    Integer.toHexString(Animation.cache[i1].frameIDs[0]).length() - 4),
                                            16));
                    } catch (Exception e) {
                    }
                }
            }
            if ((l & 8) != 0) {
                int j1 = getInputBuffer().readInt();
                int j2 = stream.method427();
                int icon = stream.getUnsignedByte();
                npc.updateHitData(j2, j1, loopCycle, icon, 0);
                npc.loopCycleStatus = loopCycle + 300;
                npc.currentHealth = getInputBuffer().getLong();
                npc.maxHealth = getInputBuffer().getLong();

            } // Restart Server and client for dev
            if ((l & 0x80) != 0) {
                npc.gfxId = stream.getUnsignedShort();
                int k1 = stream.getIntLittleEndian();
                npc.graphicHeight = k1 >> 16;
                npc.graphicDelay = loopCycle + (k1 & 0xffff);
                npc.currentAnim = 0;
                npc.animCycle = 0;
                if (npc.graphicDelay > loopCycle) {
                    npc.currentAnim = -1;
                }
                if (npc.gfxId == 65535) {
                    npc.gfxId = -1;
                }
                try {
                    if (FrameReader.animationlist[Integer
                            .parseInt(Integer.toHexString(SpotAnimDefinition.cache[npc.gfxId].animation.frameIDs[0])
                                            .substring(0,
                                                    Integer.toHexString(
                                                            SpotAnimDefinition.cache[npc.gfxId].animation.frameIDs[0]).length()
                                                            - 4),
                                    16)].length == 0)
                        onDemandFetcher.requestFileData(1, Integer.parseInt(Integer
                                        .toHexString(SpotAnimDefinition.cache[npc.gfxId].animation.frameIDs[0]).substring(0,
                                        Integer.toHexString(SpotAnimDefinition.cache[npc.gfxId].animation.frameIDs[0])
                                                .length() - 4),
                                16));
                } catch (Exception e) {
                }
            }
            if ((l & 0x20) != 0) {
                npc.interactingEntity = stream.getUnsignedShort();
                if (npc.interactingEntity == 65535) {
                    npc.interactingEntity = -1;
                }
            }
            if ((l & 1) != 0) {
                npc.textSpoken = stream.getString();
                npc.textCycle = 100;
            }
            if ((l & 0x40) != 0) {
                int l1 = getInputBuffer().readInt();
                int k2 = stream.method428();
                int icon = stream.getUnsignedByte();
                npc.updateHitData(k2, l1, loopCycle, icon, 0);
                npc.loopCycleStatus = loopCycle + 300;
                npc.currentHealth = getInputBuffer().getLong();
                npc.maxHealth = getInputBuffer().getLong();// As for damage on interface....
            }
            if ((l & 2) != 0) {
                npc.definitionOverride = MobDefinition.get(stream.getShortBigEndianA());
                npc.anInt1540 = npc.definitionOverride.npcSizeInSquares;
                npc.anInt1504 = npc.definitionOverride.degreesToTurn;
                npc.anInt1554 = npc.definitionOverride.walkAnimation;
                npc.anInt1555 = npc.definitionOverride.walkingBackwardsAnimation;
                npc.anInt1556 = npc.definitionOverride.walkLeftAnimation;
                npc.anInt1557 = npc.definitionOverride.walkRightAnimation;
                npc.anInt1511 = npc.definitionOverride.standAnimation;
            }
            if ((l & 4) != 0) {
                npc.anInt1538 = stream.getShortBigEndian();
                npc.anInt1539 = stream.getShortBigEndian();
            }
        }
    }

    private void method89(Class30_Sub1 class30_sub1) {
        int i = 0;
        int j = -1;
        int k = 0;
        int l = 0;
        if (class30_sub1.anInt1296 == 0) {
            i = scene.method300(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
        }
        if (class30_sub1.anInt1296 == 1) {
            i = scene.method301(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
        }
        if (class30_sub1.anInt1296 == 2) {
            i = scene.method302(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
        }
        if (class30_sub1.anInt1296 == 3) {
            i = scene.method303(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
        }
        if (i != 0) {
            int i1 = scene.fetchObjectIDTagForPosition(class30_sub1.anInt1295, class30_sub1.anInt1297,
                    class30_sub1.anInt1298, i);
            j = i >> 14 & 0x7fff;
            k = i1 & 0x1f;
            l = i1 >> 6;
        }
        class30_sub1.anInt1299 = j;
        class30_sub1.anInt1301 = k;
        class30_sub1.anInt1300 = l;
    }

    private final void method90() {
        for (int index = 0; soundCount > index; index++) {
            soundDelay[index]--;

            if (soundDelay[index] < -10) {
                soundCount--;

                for (int lastSound = index; soundCount > lastSound; lastSound++) {
                    sound[lastSound] = sound[lastSound + 1];
                    aClass26Array1468[lastSound] = aClass26Array1468[lastSound + 1];
                    soundType[lastSound] = soundType[lastSound + 1];
                    soundDelay[lastSound] = soundDelay[lastSound + 1];
                }

                index--;
            } else {
                Sound class26 = aClass26Array1468[index];

                if (class26 == null) {
                    class26 = Sound.cache[sound[index]];

                    if (class26 == null) {
                        continue;
                    }

                    soundDelay[index] += class26.method652();
                    aClass26Array1468[index] = class26;
                }

                if (soundDelay[index] < 0) {
                    Class3_Sub9_Sub1 class3_sub9_sub1 = class26.method651().method405(aClass25_1948);
                    Class3_Sub7_Sub2 class3_sub7_sub2 = Class3_Sub7_Sub2.method396(class3_sub9_sub1, 100,
                            soundEffectVolume);
                    class3_sub7_sub2.method394(soundType[index] - 1);
                    aClass3_Sub7_Sub1_1493.method384(class3_sub7_sub2);
                    soundDelay[index] = -100;
                }
            }
        }

        if (prevSong > 0) {
            prevSong -= 20;

            if (prevSong < 0) {
                prevSong = 0;
            }

            if (prevSong == 0 && musicVolume != 0 && currentSong != -1) {
                method56(musicVolume, false, currentSong);
            }
        }
    }

    private void updatePlayerMovement(ByteBuffer stream, int i) {
        while (stream.bitPosition + 10 < i * 8) {
            int j = stream.getBits(11);
            if (j == 2047) {
                break;
            }
            if (playerArray[j] == null) {
                playerArray[j] = new Player();
                if (getaStreamArray895s()[j] != null) {
                    playerArray[j].updatePlayer(getaStreamArray895s()[j]);
                }
            }
            playerIndices[playerCount++] = j;
            Player player = playerArray[j];
            player.loopCycle = loopCycle;
            int k = stream.getBits(1);
            if (k == 1) {
                playersToUpdate[playersToUpdateCount++] = j;
            }
            int l = stream.getBits(1);
            int i1 = stream.getBits(5);
            if (i1 > 15) {
                i1 -= 32;
            }
            int j1 = stream.getBits(5);
            if (j1 > 15) {
                j1 -= 32;
            }
            player.setPos(myPlayer.smallX[0] + j1, myPlayer.smallY[0] + i1, l == 1);
        }
        stream.finishBitAccess();
    }

    private void readNPCUpdateBlockForced() {
        for (int j = 0; j < npcCount; j++) {
            int k = npcIndices[j];
            NPC npc = npcArray[k];
            if (npc != null) {
                entityUpdateBlock(npc);
            }
        }
    }

    private void entityUpdateBlock(Entity entity) {
        if (entity.x < 128 || entity.y < 128 || entity.x >= 13184 || entity.y >= 13184) {
            entity.anim = -1;
            entity.gfxId = -1;
            entity.anInt1547 = 0;
            entity.anInt1548 = 0;
            entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
            entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
            entity.method446();
        }
        if (entity == myPlayer && (entity.x < 1536 || entity.y < 1536 || entity.x >= 11776 || entity.y >= 11776)) {
            entity.anim = -1;
            entity.gfxId = -1;
            entity.anInt1547 = 0;
            entity.anInt1548 = 0;
            entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
            entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
            entity.method446();
        }
        if (entity.anInt1547 > loopCycle) {
            updateEntityPos(entity);
        } else if (entity.anInt1548 >= loopCycle) {
            updateEntityFace(entity);
        } else {
            processWalkingStep(entity);
        }
        appendFocusDest(entity);
        appendAnimation(entity);
    }

    private void updateEntityPos(Entity entity) {
        int i = entity.anInt1547 - loopCycle;
        int j = entity.anInt1543 * 128 + entity.anInt1540 * 64;
        int k = entity.anInt1545 * 128 + entity.anInt1540 * 64;
        entity.x += (j - entity.x) / i;
        entity.y += (k - entity.y) / i;
        entity.anInt1503 = 0;
        if (entity.anInt1549 == 0) {
            entity.turnDirection = 1024;
        }
        if (entity.anInt1549 == 1) {
            entity.turnDirection = 1536;
        }
        if (entity.anInt1549 == 2) {
            entity.turnDirection = 0;
        }
        if (entity.anInt1549 == 3) {
            entity.turnDirection = 512;
        }
    }

    private void updateEntityFace(Entity entity) {
        if (entity.anInt1548 == loopCycle || entity.anim == -1 || entity.animationDelay != 0
                || entity.anInt1528 + 1 > Animation.cache[entity.anim].getFrameLength(entity.currentAnimFrame)) {
            int i = entity.anInt1548 - entity.anInt1547;
            int j = loopCycle - entity.anInt1547;
            int k = entity.anInt1543 * 128 + entity.anInt1540 * 64;
            int l = entity.anInt1545 * 128 + entity.anInt1540 * 64;
            int i1 = entity.anInt1544 * 128 + entity.anInt1540 * 64;
            int j1 = entity.anInt1546 * 128 + entity.anInt1540 * 64;
            entity.x = (k * (i - j) + i1 * j) / i;
            entity.y = (l * (i - j) + j1 * j) / i;
        }
        entity.anInt1503 = 0;
        if (entity.anInt1549 == 0) {
            entity.turnDirection = 1024;
        }
        if (entity.anInt1549 == 1) {
            entity.turnDirection = 1536;
        }
        if (entity.anInt1549 == 2) {
            entity.turnDirection = 0;
        }
        if (entity.anInt1549 == 3) {
            entity.turnDirection = 512;
        }
        entity.anInt1552 = entity.turnDirection;
    }

    private void processWalkingStep(Entity entity) {
        entity.anInt1517 = entity.anInt1511;
        if (entity.smallXYIndex == 0) {
            entity.anInt1503 = 0;
            return;
        }
        if (entity.anim != -1 && entity.animationDelay == 0) {
            Animation animation = Animation.cache[entity.anim];
            /*
             * for (int i = 0; i < animation.anIntArray357.length; i++) {
             * animation.anIntArray357[i] = -1; }
             */
            if (entity.anInt1542 > 0 && animation.resetWhenWalk == 0) {
                entity.anInt1503++;
                return;
            }
            if (entity.anInt1542 <= 0 && animation.priority == 0) {
                entity.anInt1503++;
                return;
            }
        }
        int i = entity.x;
        int j = entity.y;
        int k = entity.smallX[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
        int l = entity.smallY[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
        if (k - i > 256 || k - i < -256 || l - j > 256 || l - j < -256) {
            entity.x = k;
            entity.y = l;
            return;
        }
        if (i < k) {
            if (j < l) {
                entity.turnDirection = 1280;
            } else if (j > l) {
                entity.turnDirection = 1792;
            } else {
                entity.turnDirection = 1536;
            }
        } else if (i > k) {
            if (j < l) {
                entity.turnDirection = 768;
            } else if (j > l) {
                entity.turnDirection = 256;
            } else {
                entity.turnDirection = 512;
            }
        } else if (j < l) {
            entity.turnDirection = 1024;
        } else {
            entity.turnDirection = 0;
        }
        int i1 = entity.turnDirection - entity.anInt1552 & 0x7ff;
        if (i1 > 1024) {
            i1 -= 2048;
        }
        int j1 = entity.anInt1555;
        if (i1 >= -256 && i1 <= 256) {
            j1 = entity.anInt1554;
        } else if (i1 >= 256 && i1 < 768) {
            j1 = entity.anInt1557;
        } else if (i1 >= -768 && i1 <= -256) {
            j1 = entity.anInt1556;
        }
        if (j1 == -1) {
            j1 = entity.anInt1554;
        }
        entity.anInt1517 = j1;
        int k1 = 4;
        if (entity.anInt1552 != entity.turnDirection && entity.interactingEntity == -1 && entity.anInt1504 != 0) {
            k1 = 2;
        }
        if (entity.smallXYIndex > 2) {
            k1 = 6;
        }
        if (entity.smallXYIndex > 3) {
            k1 = 8;
        }
        if (entity.anInt1503 > 0 && entity.smallXYIndex > 1) {
            k1 = 8;
            entity.anInt1503--;
        }
        if (entity.aBooleanArray1553[entity.smallXYIndex - 1]) {
            k1 <<= 1;
        }
        if (k1 >= 8 && entity.anInt1517 == entity.anInt1554 && entity.runAnimation != -1) {
            entity.anInt1517 = entity.runAnimation;
        }
        if (i < k) {
            entity.x += k1;
            if (entity.x > k) {
                entity.x = k;
            }
        } else if (i > k) {
            entity.x -= k1;
            if (entity.x < k) {
                entity.x = k;
            }
        }
        if (j < l) {
            entity.y += k1;
            if (entity.y > l) {
                entity.y = l;
            }
        } else if (j > l) {
            entity.y -= k1;
            if (entity.y < l) {
                entity.y = l;
            }
        }
        if (entity.x == k && entity.y == l) {
            entity.smallXYIndex--;
            if (entity.anInt1542 > 0) {
                entity.anInt1542--;
            }
        }
    }

    public final String methodR(int i) {
        if (i >= 0 && i < 10000) {
            return String.valueOf(i);
        }

        if (i >= 10000 && i < 10000000) {
            return i / 1000 + "K";
        }

        if (i >= 10000000 && i <= Integer.MAX_VALUE) {
            return i / 1000000 + "M";
        }

        if (i > Integer.MAX_VALUE) {
            return "*";
        } else {
            return "?";
        }
    }

    public boolean mouseInCircle(int centerX, int centerY, int radius) {
        return (super.mouseX - centerX) * (super.mouseX - centerX)
                + (super.mouseY - centerY) * (super.mouseY - centerY) < radius * radius;
    }

    private void npcScreenPos(Entity entity, int i) {
        calcEntityScreenPos(entity.x, i, entity.y);
    }

    private void nullLoader() {
        aBoolean831 = false;
        while (drawingFlames) {
            aBoolean831 = false;
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }

        // anIntArray1190 = null;
        // anIntArray1191 = null;
    }

    private DataInputStream openJagGrabInputStream(String s) throws IOException {
        // if(!aBoolean872)
        // if(signlink.mainapp != null)
        // return signlink.openurl(s);
        // else
        // return new DataInputStream((new URL(getCodeBase(), s)).openStream());
        if (aSocket832 != null) {
            try {
                aSocket832.close();
            } catch (Exception _ex) {
            }
            aSocket832 = null;
        }
        aSocket832 = createFileServerSocket(9420);
        aSocket832.setSoTimeout(10000);
        java.io.InputStream inputstream = aSocket832.getInputStream();
        OutputStream outputstream = aSocket832.getOutputStream();
        outputstream.write(("JAGGRAB /" + s + "\n\n").getBytes());
        return new DataInputStream(inputstream);
    }

    private boolean parsePacket() {
        if (getConnection() == null) {
            return false;
        }
        try {
            int available = getConnection().available();

            if (available == 0) {
                return false;
            }

            if (pktType == -1) {
                getConnection().flushInputStream(getInputBuffer().buffer, 1);
                pktType = getInputBuffer().buffer[0] & 0xff;

                if (getConnectionCipher() != null) {
                    pktType = pktType - getConnectionCipher().next() & 0xff;
                }

                pktSize = SizeConstants.PACKET_SIZES[pktType];
                // System.out.println("Packet: " + pktType + " SIZE: " + pktSize);
                available--;
            }

            if (pktSize == -1) {
                if (available > 0) {
                    getConnection().flushInputStream(getInputBuffer().buffer, 1);
                    pktSize = getInputBuffer().buffer[0] & 0xff;
                    available--;
                } else {
                    return false;
                }
            }

            if (pktSize == -2) {
                if (available > 1) {
                    getConnection().flushInputStream(getInputBuffer().buffer, 2);
                    getInputBuffer().position = 0;
                    pktSize = getInputBuffer().getUnsignedShort();
                    available -= 2;
                } else {
                    return false;
                }
            }

            if (available < pktSize) {
                return false;
            }

            getInputBuffer().position = 0;
            getConnection().flushInputStream(getInputBuffer().buffer, pktSize);
            anInt1009 = 0;
            anInt843 = previousPacket;
            previousPacket = anInt841;
            anInt841 = pktType;

            switch (pktType) {
                case 81:
                    updatePlayers(pktSize, getInputBuffer());
                    sendFrame36(175, openInterfaceID == 26000 ? 1 : 0);
                    aBoolean1080 = false;
                    pktType = -1;
                    return true;

                case 88:
                    int xface = getInputBuffer().getSignedShort();
                    int yface = getInputBuffer().getSignedShort();
                    int npcindex = getInputBuffer().getShortBigEndian();
                    if (npcindex < npcArray.length) {
                        NPC npc = npcArray[npcindex];
                        if (npc != null) {
                            npc.anInt1538 = xface;
                            npc.anInt1539 = yface;
                        }
                    }
                    pktType = -1;
                    return true;

                case 190:
                    int intId = getInputBuffer().getInt();
                    int npcId = getInputBuffer().getShort();
                    int adjustedZoom = getInputBuffer().getShort();
                    RSInterface npcOnInterface1 = RSInterface.interfaceCache[intId];
                    npcOnInterface1.npcDisplay = npcId;
                  //  if (adjustedZoom > 999) {
                        npcOnInterface1.modelZoom = adjustedZoom;
                  //  } else {
                   //     npcOnInterface1.modelZoom = 1400;
                   // }
                    pktType = -1;
                    return true;

                case 199:
                    int firstItem = getInputBuffer().getShort();
                    int secondItem = getInputBuffer().getShort();
                    int thirdItem = getInputBuffer().getShort();
                    System.out.println("Reading from server: " + firstItem + "," + secondItem + "," + thirdItem);
                    RSInterface.interfaceCache[23635].itemsToDraw = new int[]{firstItem, secondItem, thirdItem};
                    pktType = -1;
                    return true;

                case 176:
                    daysSinceRecovChange = getInputBuffer().method427();
                    unreadMessages = getInputBuffer().method435();
                    membersInt = getInputBuffer().getUnsignedByte();
                    anInt1193 = getInputBuffer().method440();
                    daysSinceLastLogin = getInputBuffer().getUnsignedShort();

                    pktType = -1;
                    return true;

                case 64:
                    anInt1268 = getInputBuffer().method427();
                    anInt1269 = getInputBuffer().method428();

                    for (int j = anInt1268; j < anInt1268 + 8; j++) {
                        for (int l9 = anInt1269; l9 < anInt1269 + 8; l9++) {
                            if (groundArray[plane][j][l9] != null) {
                                groundArray[plane][j][l9] = null;
                                spawnGroundItem(j, l9);
                            }
                        }
                    }

                    for (Class30_Sub1 class30_sub1 = (Class30_Sub1) getaClass19_1179()
                            .reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1) getaClass19_1179()
                            .reverseGetNext()) {
                        if (class30_sub1.anInt1297 >= anInt1268 && class30_sub1.anInt1297 < anInt1268 + 8
                                && class30_sub1.anInt1298 >= anInt1269 && class30_sub1.anInt1298 < anInt1269 + 8
                                && class30_sub1.anInt1295 == plane) {
                            class30_sub1.anInt1294 = 0;
                        }
                    }
                    pktType = -1;
                    return true;

                case 185:

                    int k = getInputBuffer().getShortBigEndianA();

                    RSInterface.interfaceCache[k].mediaType = 3;
                    if (myPlayer.desc == null) {
                        RSInterface.interfaceCache[k].mediaID = (myPlayer.anIntArray1700[0] << 25)
                                + (myPlayer.anIntArray1700[4] << 20) + (myPlayer.equipment[0] << 15)
                                + (myPlayer.equipment[8] << 10) + (myPlayer.equipment[11] << 5) + myPlayer.equipment[1];
                    } else {
                        RSInterface.interfaceCache[k].mediaID = (int) (0x12345678L + myPlayer.desc.id);
                    }
                    pktType = -1;
                    return true;

                case 192:
                    String youtubeType = getInputBuffer().getString();
                    Youtube.handleYoutubeData(youtubeType);
                    pktType = -1;
                    return true;

                case 233:
                    int modelComponentId = getInputBuffer().getShort();
                    int item = getInputBuffer().getShort();
                    RSInterface.interfaceCache[modelComponentId].mediaID = item;
                    cardPack.startExpand();
                    pktType = -1;
                    return true;

                case 232:
                    int componentId = getInputBuffer().getInt();
                    boolean visible = getInputBuffer().getByte() == 1;
                    RSInterface.interfaceCache[componentId].setVisible(visible);
                    pktType = -1;
                    return true;

                case 225:
                    int widgetId = getInputBuffer().getShort();
                    WheelOfFortune wheel = RSInterface.interfaceCache[widgetId].wheel;
                    byte index = getInputBuffer().getByte();
                    byte len = getInputBuffer().getByte();
                    int[] items = new int[len];
                    for (int j = 0; j < len; j++) {
                        items[j] = getInputBuffer().getShort();
                    }
                    wheel.setItems(items);
                    wheel.setIndex(index);
                    wheel.init();
                    pktType = -1;
                    return true;

                /* Clan chat packet */
                case 217:
                    try {
                        name = getInputBuffer().getString();
                        message = getInputBuffer().getString();
                        clanName = getInputBuffer().getString();
                        rights = getInputBuffer().getUnsignedShort();
                        /*
                         * String addon = null; if(rights < 4) addon = "@cr"+rights+"@";
                         */
                        String tag = name.replaceAll("null", "");
                        message = TextInput.processText(message);
                        // message = Censor.doCensor(message);
                        pushMessage(message, 16, tag);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pktType = -1;
                    return true;

                case 107:
                    cameraViewChanged = false;
                    for (int l = 0; l < 5; l++) {
                        aBooleanArray876[l] = false;
                    }
                    pktType = -1;
                    return true;

                case 72:
                    int i1 = getInputBuffer().getShortBigEndian();
                    RSInterface class9 = RSInterface.interfaceCache[i1];
                    for (int k15 = 0; k15 < class9.inv.length; k15++) {
                        class9.inv[k15] = -1;
                        class9.inv[k15] = 0;
                    }
                    pktType = -1;
                    return true;

                case 214:
                    ignoreCount = getInputBuffer().getShort();
                    for (int i2 = 0; i2 < ignoreCount; i2++) {
                        ignoreListAsLongs[i2] = Long.parseLong(getInputBuffer().getString());
                    }
                    pktType = -1;
                    return true;

                case 244:
                    String data = getInputBuffer().getString();
                    getGrandExchange().update(data);
                    pktType = -1;
                    return true;

                case 166:
                    cameraViewChanged = true;
                    spinPacketX = getInputBuffer().getUnsignedByte();
                    spinPacketY = getInputBuffer().getUnsignedByte();
                    spinPacketHeight = getInputBuffer().getUnsignedShort();
                    spinPacketConstantSpeed = getInputBuffer().getUnsignedByte();
                    spinPacketVariableSpeed = getInputBuffer().getUnsignedByte();
                    if (spinPacketVariableSpeed >= 100) {
                        xCameraPos = spinPacketX * 128 + 64;
                        yCameraPos = spinPacketY * 128 + 64;

                        zCameraPos = method42(plane, yCameraPos, xCameraPos) - spinPacketHeight;
                    }
                    pktType = -1;
                    return true;

                case 134:
                    // needDrawTabArea = true;

                    int skillId = getInputBuffer().getUnsignedByte();
                    int exp = getInputBuffer().method439();
                    int level = getInputBuffer().getUnsignedShort();
                    int maxLevel = getInputBuffer().getUnsignedShort();
                    int gainedExperience = exp - currentExp[skillId];
                    currentExp[skillId] = exp;
                    currentStats[skillId] = level;
                    maxStats[skillId] = maxLevel;
                    if (gainedExperience > 0)
                        PlayerHandler.addXP(skillId, gainedExperience);
                    if (skillId == 23) {
                        setInterfaceText("" + maxLevel + "", 28171);
                    }
                    pktType = -1;
                    return true;

                case 71:
                    int l1 = getInputBuffer().readInt();
                    int j10 = getInputBuffer().method426();
                    if (l1 == 65535) {
                        l1 = -1;
                    }
                    tabInterfaceIDs[j10] = l1;
                    prayerInterfaceType = tabInterfaceIDs[5];
                    tabAreaAltered = true;
                    pktType = -1;
                    return true;

                case 74:
                    int songId = getInputBuffer().getShortBigEndian();
                    if (songId == 65535) {
                        songId = -1;
                    }
                    if (songId != -1 || prevSong != 0) {
                        if (songId != -1 && currentSong != songId && musicVolume != 0 && prevSong == 0) {
                            method58(10, musicVolume, false, songId);
                        }
                    } else {
                        method55(false);
                    }
                    currentSong = songId;
                    pktType = -1;
                    return true;

                case 121:
                    int tempSongId = getInputBuffer().getShortBigEndianA();
                    int tempSongDelay = getInputBuffer().method435();
                    if (tempSongId == 65535) {
                        tempSongId = -1;
                    }
                    if (musicVolume != 0 && tempSongDelay != -1) {
                        method56(musicVolume, false, tempSongId);
                        prevSong = tempSongDelay * 20;
                    }
                    pktType = -1;
                    return true;

                case 109:
                    resetLogout();
                    pktType = -1;
                    return false;

                case 70:
                    int modifierX = getInputBuffer().getSignedShort();
                    int modifierY = getInputBuffer().method437();
                    int widgetId1 = getInputBuffer().getShortBigEndian();
                    RSInterface widget = RSInterface.interfaceCache[widgetId1];
                    widget.xOffset = modifierX;
                    widget.yOffset = modifierY;
                    pktType = -1;
                    return true;

                case 73:
                case 241:
                    int l2 = currentRegionX;
                    int i11 = currentRegionY;
                    if (pktType == 73) {
                        l2 = mapX = getInputBuffer().method435();
                        i11 = mapY = getInputBuffer().getUnsignedShort();
                        requestMapReconstruct = false;
                    }
                    if (pktType == 241) {
                        i11 = getInputBuffer().method435();
                        getInputBuffer().initBitAccess();
                        for (int j16 = 0; j16 < 4; j16++) {
                            for (int l20 = 0; l20 < 13; l20++) {
                                for (int j23 = 0; j23 < 13; j23++) {
                                    int emptyFloor = getInputBuffer().getBits(1);
                                    if (emptyFloor == 1) {
                                        constructRegionData[j16][l20][j23] = getInputBuffer().getBits(26);
                                    } else {
                                        constructRegionData[j16][l20][j23] = -1;
                                    }
                                }
                            }
                        }
                        getInputBuffer().finishBitAccess();
                        l2 = getInputBuffer().getUnsignedShort();
                        requestMapReconstruct = true;
                    }
                    if (currentRegionX == l2 && currentRegionY == i11 && loadingStage == 2) {
                        pktType = -1;
                        return true;
                    }
                    currentRegionX = l2;
                    currentRegionY = i11;
                    regionBaseX = (currentRegionX - 6) * 8;
                    regionBaseY = (currentRegionY - 6) * 8;
                    aBoolean1141 = (currentRegionX / 8 == 48 || currentRegionX / 8 == 49) && currentRegionY / 8 == 48;
                    if (currentRegionX / 8 == 48 && currentRegionY / 8 == 148) {
                        aBoolean1141 = true;
                    }
                    loadingStage = 1;
                    aLong824 = System.currentTimeMillis();
                    gameScreenIP.initDrawingArea();
                    cacheSprite[1105].drawSprite(8, 9);
                    // drawLoadingMessages(1, "Loading - please wait.", null);
                    if (!resizing) {
                        gameScreenIP.drawGraphics(canvas.getGraphics(), gameScreenDrawX, gameScreenDrawY);
                    }
                    if (pktType == 73) {
                        int k16 = 0;
                        for (int i21 = (currentRegionX - 6) / 8; i21 <= (currentRegionX + 6) / 8; i21++) {
                            for (int k23 = (currentRegionY - 6) / 8; k23 <= (currentRegionY + 6) / 8; k23++) {
                                k16++;
                            }
                        }
                        terrainData = new byte[k16][];
                        objectData = new byte[k16][];
                        mapCoordinates = new int[k16];
                        floorMap = new int[k16];
                        objectMap = new int[k16];
                        k16 = 0;
                        for (int l23 = (currentRegionX - 6) / 8; l23 <= (currentRegionX + 6) / 8; l23++) {
                            for (int j26 = (currentRegionY - 6) / 8; j26 <= (currentRegionY + 6) / 8; j26++) {
                                mapCoordinates[k16] = (l23 << 8) + j26;
                                if (aBoolean1141
                                        && (j26 == 49 || j26 == 149 || j26 == 147 || l23 == 50 || l23 == 49 && j26 == 47)) {
                                    floorMap[k16] = -1;
                                    objectMap[k16] = -1;
                                    k16++;
                                } else {
                                    int k28 = floorMap[k16] = onDemandFetcher.getMapCount(0, j26, l23);
                                    int mapindex = 3;

                                    if (OldschoolMaps.isOldschoolRegion(mapCoordinates[k16])) {
                                        mapindex = 7;
                                    }

                                    if (k28 != -1) {
                                        onDemandFetcher.requestFileData(mapindex, k28);
                                    }
                                    int j30 = objectMap[k16] = onDemandFetcher.getMapCount(1, j26, l23);
                                    if (j30 != -1) {
                                        onDemandFetcher.requestFileData(mapindex, j30);
                                    }
                                    k16++;
                                }
                            }
                        }
                    }
                    if (pktType == 241) {
                        int l16 = 0;
                        int ai[] = new int[676];
                        for (int i24 = 0; i24 < 4; i24++) {
                            for (int k26 = 0; k26 < 13; k26++) {
                                for (int l28 = 0; l28 < 13; l28++) {
                                    int k30 = constructRegionData[i24][k26][l28];
                                    if (k30 != -1) {
                                        int k31 = k30 >> 14 & 0x3ff;
                                        int i32 = k30 >> 3 & 0x7ff;
                                        int k32 = (k31 / 8 << 8) + i32 / 8;
                                        for (int j33 = 0; j33 < l16; j33++) {
                                            if (ai[j33] != k32) {
                                                continue;
                                            }
                                            k32 = -1;

                                        }
                                        if (k32 != -1) {
                                            ai[l16++] = k32;
                                        }
                                    }
                                }
                            }
                        }
                        terrainData = new byte[l16][];
                        objectData = new byte[l16][];
                        mapCoordinates = new int[l16];
                        floorMap = new int[l16];
                        objectMap = new int[l16];
                        for (int l26 = 0; l26 < l16; l26++) {
                            int i29 = mapCoordinates[l26] = ai[l26];
                            int l30 = i29 >> 8 & 0xff;
                            int l31 = i29 & 0xff;
                            int j32 = floorMap[l26] = onDemandFetcher.getMapCount(0, l31, l30);
                            if (j32 != -1) {
                                onDemandFetcher.requestFileData(3, j32);
                            }
                            int i33 = objectMap[l26] = onDemandFetcher.getMapCount(1, l31, l30);
                            if (i33 != -1) {
                                onDemandFetcher.requestFileData(3, i33);
                            }
                        }
                    }
                    int i17 = regionBaseX - anInt1036;
                    int j21 = regionBaseY - anInt1037;
                    anInt1036 = regionBaseX;
                    anInt1037 = regionBaseY;
                    for (int j24 = 0; j24 < 16384; j24++) {
                        NPC npc = npcArray[j24];
                        if (npc != null) {
                            for (int j29 = 0; j29 < 10; j29++) {
                                npc.smallX[j29] -= i17;
                                npc.smallY[j29] -= j21;
                            }
                            npc.x -= i17 * 128;
                            npc.y -= j21 * 128;
                        }
                    }
                    for (int i27 = 0; i27 < getMaxPlayers(); i27++) {
                        Player player = playerArray[i27];
                        if (player != null) {
                            for (int i31 = 0; i31 < 10; i31++) {
                                player.smallX[i31] -= i17;
                                player.smallY[i31] -= j21;
                            }
                            player.x -= i17 * 128;
                            player.y -= j21 * 128;
                        }
                    }
                    aBoolean1080 = true;
                    byte byte1 = 0;
                    byte byte2 = 104;
                    byte byte3 = 1;
                    if (i17 < 0) {
                        byte1 = 103;
                        byte2 = -1;
                        byte3 = -1;
                    }
                    byte byte4 = 0;
                    byte byte5 = 104;
                    byte byte6 = 1;
                    if (j21 < 0) {
                        byte4 = 103;
                        byte5 = -1;
                        byte6 = -1;
                    }
                    for (int k33 = byte1; k33 != byte2; k33 += byte3) {
                        for (int l33 = byte4; l33 != byte5; l33 += byte6) {
                            int i34 = k33 + i17;
                            int j34 = l33 + j21;
                            for (int k34 = 0; k34 < 4; k34++) {
                                if (i34 >= 0 && j34 >= 0 && i34 < 104 && j34 < 104) {
                                    groundArray[k34][k33][l33] = groundArray[k34][i34][j34];
                                } else {
                                    groundArray[k34][k33][l33] = null;
                                }
                            }
                        }
                    }
                    for (Class30_Sub1 class30_sub1_1 = (Class30_Sub1) getaClass19_1179()
                            .reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1) getaClass19_1179()
                            .reverseGetNext()) {
                        class30_sub1_1.anInt1297 -= i17;
                        class30_sub1_1.anInt1298 -= j21;
                        if (class30_sub1_1.anInt1297 < 0 || class30_sub1_1.anInt1298 < 0 || class30_sub1_1.anInt1297 >= 104
                                || class30_sub1_1.anInt1298 >= 104) {
                            class30_sub1_1.unlink();
                        }
                    }
                    if (destX != 0) {
                        destX -= i17;
                        destY -= j21;
                    }
                    cameraViewChanged = false;
                    pktType = -1;
                    return true;

                case 80:
                    int childId2 = getInputBuffer().getUnsignedShort();
                    int size = getInputBuffer().getUnsignedShort();
                    final int[][] loot = new int[size][2];
                    for (int i = 0; i < size; i++) {
                        final int itemId = getInputBuffer().getUnsignedShort();
                        final int amount = getInputBuffer().getUnsignedShort();
                        System.out.println("itemId : " + itemId + " amount: " + amount);
                        loot[i][0] = itemId;
                        loot[i][1] = amount;
                    }
                    RSInterface.setScrollableItems(RSInterface.interfaceCache[childId2], loot);
                    pktType = -1;
                    return true;

                case 231:
                    final int npc_id = getInputBuffer().getUnsignedShort();
                    int interfaceId = getInputBuffer().getUnsignedShort();
                    final RSInterface npcOnInterface = RSInterface.interfaceCache[interfaceId];
                    if (npcOnInterface != null) {
                        npcOnInterface.contentId = npc_id;
                    }
                    pktType = -1;

                    return true;
                case 140:
                    int childId = getInputBuffer().readInt();
                    int interfaceState = getInputBuffer().getUnsignedShort();
                    int interfaceState3 = getInputBuffer().getUnsignedByte();
                    updateProgressBar(childId, interfaceState, interfaceState3, 0);
                    pktType = -1;
                    return true;

                case 209:
                    int interfaceId_ = getInputBuffer().getInt();
                    int newId = getInputBuffer().getShort();
                    if (RSInterface.interfaceCache[interfaceId_] != null) {
                        RSInterface.interfaceCache[interfaceId_].disabledSprite = newId == 65535 ? null : cacheSprite[newId];
                        ;
                        RSInterface.interfaceCache[interfaceId_].enabledSprite = newId == 65535 ? null : cacheSprite[newId];
                        ;
                    }

                    pktType = -1;
                    return true;

                case 208:

                    int interfaceId1 = getInputBuffer().readInt();
                    boolean add = getInputBuffer().readUnsignedWord() == 1 ? true : false;
                    RSInterface widget1 = RSInterface.interfaceCache[interfaceId1];

                    if (widget1 != null) {
                        if (add) {
                            if (!parallelWidgetList.contains(widget1)) {
                                parallelWidgetList.add(widget1);
                            }
                        } else {
                            parallelWidgetList.remove(widget1);
                        }
                    }
                    pktType = -1;
                    return true;
                case 188:
                    int length2 = getInputBuffer().getUnsignedByte();
                    unlockedItems = new int[length2];
                    for (int index1 = 0; index1 < length2; index1++) {
                        int itemId = getInputBuffer().getUnsignedShort();
                        unlockedItems[index1] = itemId;
                    }
                    pktType = -1;
                    return true;
                case 99:
                    minimapStatus = getInputBuffer().getUnsignedByte();
                    pktType = -1;
                    return true;

                case 75:
                    int j3 = getInputBuffer().getShortBigEndianA();
                    int j11 = getInputBuffer().getShortBigEndianA();
                    RSInterface.interfaceCache[j11].mediaType = 2;
                    RSInterface.interfaceCache[j11].mediaID = j3;
                    pktType = -1;
                    return true;

                case 114:
                    systemUpdateTimer = getInputBuffer().getShortBigEndian() * 30;
                    pktType = -1;
                    return true;

                case 116:
                    boolean prayersActive = getInputBuffer().getUnsignedByte() > 0;
                    turnOffPrayers();
                    mapArea.prayer.setOrbState(false);
                    pktType = -1;
                    return true;


                case 120:
                    String bcMessage = getInputBuffer().getString();
                    int broadcastTime = getInputBuffer().getInt();

                    broadcastMinutes = broadcastTime * 30;
                    broadcastMessage = bcMessage;

                    pktType = -1;
                    return true;

                case 60:
                    anInt1269 = getInputBuffer().getUnsignedByte();
                    anInt1268 = getInputBuffer().method427();
                    while (getInputBuffer().position < pktSize) {
                        int k3 = getInputBuffer().getUnsignedByte();
                        parseEntityPacket(getInputBuffer(), k3);
                    }
                    pktType = -1;
                    return true;

                case 35:
                    int l3 = getInputBuffer().getUnsignedByte();
                    int k11 = getInputBuffer().getUnsignedByte();
                    int j17 = getInputBuffer().getUnsignedByte();
                    int k21 = getInputBuffer().getUnsignedByte();
                    aBooleanArray876[l3] = true;
                    anIntArray873[l3] = k11;
                    anIntArray1203[l3] = j17;
                    anIntArray928[l3] = k21;
                    anIntArray1030[l3] = 0;
                    pktType = -1;
                    return true;

                case 174:
                    int id = getInputBuffer().getUnsignedShort();
                    int type = getInputBuffer().getUnsignedByte();
                    int delay = getInputBuffer().getUnsignedShort();
                    if (soundEffectVolume != 0 && type != 0 && soundCount < 50) {
                        sound[soundCount] = id;
                        soundType[soundCount] = type;
                        soundDelay[soundCount] = delay;
                        aClass26Array1468[soundCount] = null;
                        soundCount++;
                    }
                    pktType = -1;
                    return true;

                case 104:
                    int j4 = getInputBuffer().method427();
                    int i12 = getInputBuffer().method426();
                    String s6 = getInputBuffer().getString();
                    if (j4 >= 1 && j4 <= 7) {
                        if (s6.equalsIgnoreCase("null")) {
                            s6 = null;
                        }
                        atPlayerActions[j4 - 1] = s6;
                        atPlayerArray[j4 - 1] = i12 == 0;
                    }
                    pktType = -1;
                    return true;

                case 78:
                    destX = 0;
                    pktType = -1;
                    return true;
                case 253:
                    String s = getInputBuffer().getString();

                    if (s.startsWith("GODSHP##")) {
                        String[] boxargs = s.split("##");
                        if (boxargs[1] != null) {
                            updateGodsHitpoints(Integer.parseInt(boxargs[1]), Integer.parseInt(boxargs[2]));
                        }
                        pktType = -1;
                        return true;
                    }
                    if (s.startsWith("casketopening##")) {
                        String[] boxargs = s.split("##");
                        if (boxargs[1] != null) {
                            casketOpening.handledPacket34(Integer.parseInt(boxargs[1]), Integer.parseInt(boxargs[2]) + 1,
                                    Integer.parseInt(boxargs[3]), Integer.parseInt(boxargs[4]));
                        }
                        pktType = -1;
                        return true;
                    }
                    if (s.equals(":spinCasket")) {
                        casketOpening.setSpinClick(true);
                        pktType = -1;
                        return true;
                    }
                    if (s.equals(":resetCasket")) {
                        casketOpening.reset();
                        pktType = -1;
                        return true;
                    }

                    if (consoleOpen) {
                        printConsoleMessage(s, 0);
                    } else if (s.endsWith(":tradereq:")) {
                        String s3 = s.substring(0, s.indexOf(":"));
                        long l17 = TextClass.longForName(s3);
                        boolean flag2 = false;
                        for (int j27 = 0; j27 < ignoreCount; j27++) {
                            if (ignoreListAsLongs[j27] != l17) {
                                continue;
                            }
                            flag2 = true;

                        }
                        if (!flag2 && anInt1251 == 0) {
                            pushMessage("wishes to trade with you.", 4, s3);
                        }
                    }else if (s.startsWith(":staff:")) {
                        s = s.substring(7);

                        pushMessage(s, 8, "");

                      //  pushMessage(s, 22, "");
                    } else if (s.endsWith(":gamblereq:")) {
                        String s21 = s.substring(0, s.indexOf(":"));
                        long l21 = TextClass.longForName(s21);
                        boolean flag2 = false;
                        for (int j27 = 0; j27 < ignoreCount; j27++) {
                            if (ignoreListAsLongs[j27] != l21) {
                                continue;
                            }
                            flag2 = true;

                        }
                        if (!flag2 && s21.length() >= 2) {
                            pushMessage("wishes to gamble with you. Click here to accept the invitation.", 21, s21);
                        }
                    } else if (s.endsWith(":raidreq:")) {
                        String s3 = s.substring(0, s.indexOf(":"));
                        long l17 = TextClass.longForName(s3);
                        boolean flag2 = false;
                        for (int j27 = 0; j27 < ignoreCount; j27++) {
                            if (ignoreListAsLongs[j27] != l17) {
                                continue;
                            }
                            flag2 = true;

                        }
                        if (!flag2 && anInt1251 == 0) {
                            pushMessage("invited you to their Raids party. Click here to accept the invitation.", 22, s3);
                        }
                    } else if (s.startsWith(":clan:")) {
                        String bracketColor = "<col=16777215>";
                        String clanNameColor = "<col=255>";
                        String nameColor = "@red@";
                        int length = Integer.parseInt(s.substring(6, 8));
                        s = s.substring(8, s.length());
                        String originals = s;
                        // System.out.println(originals);
                        s = bracketColor + originals.substring(0, 1) + "@blu@" + originals.substring(1, length)
                                + bracketColor + originals.substring(length, length + 1)
                                + ChatArea.CLAN_CHAT_COLORS[clanChatColor] + "<shad=0>" + originals.substring(length + 1);
                        // System.out.println(originals);
                        /* originals.substring(length)+ChatArea.CLAN_CHAT_COLORS[splitChatColor]+ */
                        // originals.substring(5+length+ChatArea.CLAN_CHAT_COLORS[splitChatColor].length());

                        pushMessage(/*
                         * "<col="+ChatArea.CLAN_CHAT_COLORS[splitChatColor]+">"+ChatArea.
                         * CLAN_CHAT_COLORS[splitChatColor]+""+
                         */s, 16, "");
                    } else if (s.endsWith("#url#")) {
                        String link = s.substring(0, s.indexOf("#"));
                        pushMessage("Join us at: ", 9, link);
                    } else if (s.endsWith(":duelreq:")) {
                        String s4 = s.substring(0, s.indexOf(":"));
                        long l18 = TextClass.longForName(s4);
                        boolean flag3 = false;
                        for (int k27 = 0; k27 < ignoreCount; k27++) {
                            if (ignoreListAsLongs[k27] != l18) {
                                continue;
                            }
                            flag3 = true;

                        }
                        if (!flag3 && anInt1251 == 0) {
                            pushMessage("wishes to duel with you.", 8, s4);
                        }
                    } else if (s.endsWith(":chalreq:")) {
                        String s5 = s.substring(0, s.indexOf(":"));
                        long l19 = TextClass.longForName(s5);
                        boolean flag4 = false;
                        for (int l27 = 0; l27 < ignoreCount; l27++) {
                            if (ignoreListAsLongs[l27] != l19) {
                                continue;
                            }
                            flag4 = true;

                        }
                        if (!flag4 && anInt1251 == 0) {
                            String s8 = s.substring(s.indexOf(":") + 1, s.length() - 9);
                            pushMessage(s8, 8, s5);
                        }
                    } else if (s.equalsIgnoreCase(":helloworld")) {
                        MBOX = true;
                        System.err.println("1");
                    } else if (s.startsWith("Alert##")) {
                        String[] localObject3 = s.split("##");

                        System.out.println(localObject3.length);
                        //if (localObject3.length == 10) {
                        alertManager.setAlert(new AlertBox(localObject3[1], Integer.parseInt(localObject3[2]), Integer.parseInt(localObject3[3]),
                                Integer.parseInt(localObject3[4]), Integer.parseInt(localObject3[5]), Integer.parseInt(localObject3[6]),
                                new String[]{localObject3[7], localObject3[8], localObject3[9]}));

                        // }

                        pktType = -1;
                        return true;
                    } else {
                        pushMessage(s, 0, "");
                    }
                    pktType = -1;
                    return true;

                case 1:
                    for (int k4 = 0; k4 < playerArray.length; k4++) {
                        if (playerArray[k4] != null) {
                            playerArray[k4].anim = -1;
                        }
                    }
                    for (int j12 = 0; j12 < npcArray.length; j12++) {
                        if (npcArray[j12] != null) {
                            npcArray[j12].anim = -1;
                        }
                    }
                    pktType = -1;
                    return true;

                case 50:
                    long l4 = getInputBuffer().getLong();
                    int i18 = getInputBuffer().getUnsignedByte();
                    String s7 = TextClass.fixName(TextClass.nameForLong(l4));
                    for (int k24 = 0; k24 < friendCount; k24++) {
                        if (l4 != friendsListAsLongs[k24]) {
                            continue;
                        }
                        if (friendsNodeIDs[k24] != i18) {
                            friendsNodeIDs[k24] = i18;
                            /*
                             * if (i18 >= 2) { pushMessage(s7 + " has logged in.", 5, ""); } if (i18 <= 1) {
                             * pushMessage(s7 + " has logged out.", 5, ""); }
                             */
                        }
                        s7 = null;

                    }
                    if (s7 != null && friendCount < 200) {
                        friendsListAsLongs[friendCount] = l4;
                        friendsList[friendCount] = s7;
                        friendsNodeIDs[friendCount] = i18;
                        friendCount++;
                    }
                    for (boolean flag6 = false; !flag6; ) {
                        flag6 = true;
                        for (int k29 = 0; k29 < friendCount - 1; k29++) {
                            if (friendsNodeIDs[k29] != nodeID && friendsNodeIDs[k29 + 1] == nodeID
                                    || friendsNodeIDs[k29] == 0 && friendsNodeIDs[k29 + 1] != 0) {
                                int j31 = friendsNodeIDs[k29];
                                friendsNodeIDs[k29] = friendsNodeIDs[k29 + 1];
                                friendsNodeIDs[k29 + 1] = j31;
                                String s10 = friendsList[k29];
                                friendsList[k29] = friendsList[k29 + 1];
                                friendsList[k29 + 1] = s10;
                                long l32 = friendsListAsLongs[k29];
                                friendsListAsLongs[k29] = friendsListAsLongs[k29 + 1];
                                friendsListAsLongs[k29 + 1] = l32;
                                flag6 = false;
                            }
                        }
                    }
                    pktType = -1;
                    return true;

                case 110:
                    energy = getInputBuffer().getUnsignedByte();
                    pktType = -1;
                    return true;

                case 113:
                    running = getInputBuffer().getUnsignedByte() > 0;
                    variousSettings[173] = running ? 1 : 0;
                    mapArea.run.setOrbState(running);
                    pktType = -1;
                    return true;

                case 254:
                    anInt855 = getInputBuffer().getUnsignedByte();
                    if (anInt855 == 1) {
                        anInt1222 = getInputBuffer().getUnsignedShort();
                    }
                    if (anInt855 >= 2 && anInt855 <= 6) {
                        if (anInt855 == 2) {
                            anInt937 = 64;
                            anInt938 = 64;
                        }
                        if (anInt855 == 3) {
                            anInt937 = 0;
                            anInt938 = 64;
                        }
                        if (anInt855 == 4) {
                            anInt937 = 128;
                            anInt938 = 64;
                        }
                        if (anInt855 == 5) {
                            anInt937 = 64;
                            anInt938 = 0;
                        }
                        if (anInt855 == 6) {
                            anInt937 = 64;
                            anInt938 = 128;
                        }
                        anInt855 = 5;
                        anInt934 = getInputBuffer().getUnsignedShort();
                        anInt935 = getInputBuffer().getUnsignedShort();
                        anInt936 = getInputBuffer().getUnsignedByte();
                    }
                    if (anInt855 == 10) {
                        anInt933 = getInputBuffer().getUnsignedShort();
                    }
                    pktType = -1;
                    return true;

                case 248:
                    int i5 = getInputBuffer().getInt();
                    int k12 = getInputBuffer().getUnsignedShort();
                    if (backDialogID != -1) {
                        backDialogID = -1;
                        inputTaken = true;
                    }
                    if (inputDialogState != 0) {
                        inputDialogState = 0;
                        inputTaken = true;
                    }
                    openInterfaceID = i5;
                    invOverlayInterfaceID = k12;
                    tabAreaAltered = true;
                    aBoolean1149 = false;
                    pktType = -1;
                    return true;

                case 79:
                    int j5 = getInputBuffer().readInt();
                    int scrollAmount = getInputBuffer().method435();
                    RSInterface class9_3 = RSInterface.interfaceCache[j5];
                    if (class9_3 != null && class9_3.type == 0) {
                        if (scrollAmount < 0)
                            scrollAmount = 0;
                        class9_3.scrollMax = scrollAmount;
                    }
                    pktType = -1;
                    return true;

                case 68:
                    for (int k5 = 0; k5 < variousSettings.length; k5++) {
                        if (variousSettings[k5] != settings[k5]) {
                            variousSettings[k5] = settings[k5];
                            updateConfig(k5);
                        }
                    }

                    pktType = -1;
                    return true;

                case 196:
                    final long l5 = getInputBuffer().getLong();
                    getInputBuffer().getIntLittleEndian();
                    int playerRights = getInputBuffer().getUnsignedByte();
                    boolean flag5 = false;

                    if (playerRights <= 1) {
                        for (int l29 = 0; l29 < ignoreCount; l29++) {
                            if (ignoreListAsLongs[l29] != l5) {
                                continue;
                            }

                            flag5 = true;
                        }
                    }

                    if (!flag5 && anInt1251 == 0) {
                        try {
                            String message = TextInput.readChatboxText(pktSize - 13, getInputBuffer());
                            final String name = TextClass.fixName(TextClass.nameForLong(l5));
                            System.out.println(playerRights);
                            if (playerRights != 0) {
                                pushMessage(message, 7, getPrefix(playerRights) + name);
                            } else {
                                pushMessage(message, 3, name);
                            }
                        } catch (Exception exception1) {
                            exception1.printStackTrace();
                            Signlink.reportError("cde1");
                        }
                    }

                    pktType = -1;
                    return true;

                case 85:
                    anInt1269 = getInputBuffer().method427();
                    anInt1268 = getInputBuffer().method427();
                    pktType = -1;
                    return true;

                case 123:
                    printConsoleMessage(getInputBuffer().getString(), 1);
                    pktType = -1;
                    return true;

                case 54:
                    int duration = getInputBuffer().getShort();
                    int sprite1 = getInputBuffer().getInt();
                    EffectTimers.add(new EffectTimer(duration, sprite1));
                    pktType = -1;
                    return true;

                case 128:
                    currentTarget = null;
                    pktType = -1;
                    return true;

                case 125:
                    int targetIndex = getInputBuffer().getShort();
                    int targetType = getInputBuffer().getByte();
                    if (targetType == 0) { /* DONT READ DAMAGE LIST FOR PLRS */
                        currentTarget = targetIndex < playerArray.length ? playerArray[targetIndex] : null;
                        pktType = -1;
                        return true;
                    } else {
                        currentTarget = targetIndex < npcArray.length ? npcArray[targetIndex] : null;
                    }
                    if (currentTarget == null) {
                        pktType = -1;
                        return true;
                    }
                    NPC npc = (NPC) currentTarget;
                    npc.damageDealers.clear();
                    boolean readDamageList = getInputBuffer().getByte() == 1;
                    if (readDamageList) {
                        int length = getInputBuffer().getByte();
                        for (int t = 0; t < length; t++) {
                            String player = getInputBuffer().getString();
                            long damage = getInputBuffer().getInt(); // Reset again
                            npc.damageDealers.add(new DamageDealer(player, damage));
                        }
                    }
                    pktType = -1;
                    return true;

                case 24:
                    anInt1054 = getInputBuffer().method428();

                    if (anInt1054 == tabID) {
                        if (anInt1054 == 3) {
                            tabID = 1;
                        } else {
                            tabID = 3;
                        }
                    }

                    pktType = -1;
                    return true;

                case 246:
                    int i6 = getInputBuffer().getShortBigEndian();
                    int i13 = getInputBuffer().getUnsignedShort();
                    int k18 = getInputBuffer().getUnsignedShort();

                    if (k18 == 65535) {
                        RSInterface.interfaceCache[i6].mediaType = 0;
                        pktType = -1;
                        return true;
                    } else {
                        ItemDefinition itemDef = ItemDefinition.get(k18);
                        RSInterface.interfaceCache[i6].mediaType = 4;
                        RSInterface.interfaceCache[i6].mediaID = k18;
                        RSInterface.interfaceCache[i6].modelRotationY = itemDef.rotationY;
                        RSInterface.interfaceCache[i6].modelRotationX = itemDef.rotationX;
                        RSInterface.interfaceCache[i6].yOffset = 0;
                        if (k18 == 23570 )
                            RSInterface.interfaceCache[i6].yOffset = -14;
                        RSInterface.interfaceCache[i6].modelZoom = itemDef.modelZoom * 100 / i13;
                        pktType = -1;
                        return true;
                    }

                case 171:
                    boolean flag1 = getInputBuffer().getUnsignedByte() == 1;
                    int j13 = getInputBuffer().getUnsignedShort();
                    RSInterface.interfaceCache[j13].interfaceShown = flag1;
                    pktType = -1;
                    return true;

                case 142:
                    int j6 = getInputBuffer().getShortBigEndian();
                    resetInterfaceAnimation(j6);

                    if (backDialogID != -1) {
                        backDialogID = -1;
                        inputTaken = true;
                    }

                    if (inputDialogState != 0) {
                        inputDialogState = 0;
                        inputTaken = true;
                    }

                    invOverlayInterfaceID = j6;
                    tabAreaAltered = true;
                    openInterfaceID = -1;
                    aBoolean1149 = false;
                    pktType = -1;
                    return true;

                case 45:
                    long totalxp = getInputBuffer().getLong();
                    PlayerHandler.totalXP = totalxp;
                    pktType = -1;
                    return true;

                case 124:
                    int skillID = getInputBuffer().getUnsignedByte();
                    int gainedXP = getInputBuffer().getIntLittleEndian();
                    int totalEXP = getInputBuffer().getIntLittleEndian();
                    PlayerHandler.addXP(skillID, gainedXP);
                    PlayerHandler.totalXP = totalEXP;
                    pktType = -1;
                    return true;

                case 126:
                    String text = getInputBuffer().getString();
                    int frame = getInputBuffer().readInt();
                    if (text.startsWith("http://") || text.startsWith("www.") || text.startsWith("https://")) {
                        launchURL(text);
                        pktType = -1;
                        return true;
                    } else if (text.equals("[CLOSEMENU]") && frame == 0) {
                        menuOpen = false;
                        pktType = -1;
                        return true;
                    }
                    String vngt = "vngt:";
                    if (text.startsWith(vngt)) {
                        // args = text.split(":");
                        vengTimer = ((int) (Integer.parseInt(text.substring(vngt.length())) * 0.65D));
                        System.out.println("Creating vengTimer:" + Integer.parseInt(text.substring(vngt.length())));
                        pktType = -1;
                        return true;
                    }
                    if (frame >= 1675 && frame <= 1687 || frame >= 15115 && frame <= 15120) {
                        updateStrings(text, frame);
                    } // not fixed yet, just making sure interface isnt broke. It needs server update
                    // after this
                    setInterfaceText(text, frame);
                    pktType = -1;
                    return true;

                case 180:
                    int rankId = getInputBuffer().getUnsignedShort();
                    int frameId = getInputBuffer().getUnsignedShort();
                    int rankSpriteIDs[] = {93, 102, 96, 97, 98, 99, 100, 101, 94, -1, -1, 95};
                    /**
                     * 94 = owner 95 = admin 96 = one arrow up 97 = 2x arrow up 98 = 3 arrow up 99 =
                     * orange star 100 = silver star 101 = golden star 102 = friend
                     */
                    RSInterface icons = RSInterface.interfaceCache[frameId];

                    if (icons != null) {
                        icons.disabledSprite = icons.enabledSprite = Client.cacheSprite[rankSpriteIDs[rankId]];
                    }

                    rankSpriteIDs = null;
                    icons = null;
                    pktType = -1;
                    return true;

                case 181:

                    pktType = -1;
                    return true;

                case 182:
                    pktType = -1;
                    return true;

                case 206:
                    publicChatMode = getInputBuffer().getUnsignedByte();
                    privateChatMode = getInputBuffer().getUnsignedByte();
                    tradeMode = getInputBuffer().getUnsignedByte();
                    inputTaken = true;
                    pktType = -1;
                    return true;

                case 86:
                    int l = getInputBuffer().getUnsignedByte();
                    if (plane != l && l >= 0 && l < 4) {
                        plane = l;
                    }
                    pktType = -1;
                    return true;

                case 240:
                    weight = getInputBuffer().getSignedShort();
                    pktType = -1;
                    return true;

                case 8:
                    int k6 = getInputBuffer().getShortBigEndianA();
                    int l13 = getInputBuffer().getUnsignedShort();
                    RSInterface.interfaceCache[k6].mediaType = 1;
                    RSInterface.interfaceCache[k6].mediaID = l13;
                    pktType = -1;
                    return true;

                case 122:
                    int l6 = getInputBuffer().getShortBigEndianA();
                    int i14 = getInputBuffer().getShortBigEndianA();
                    int i19 = i14 >> 10 & 0x1f;
                    int i22 = i14 >> 5 & 0x1f;
                    int l24 = i14 & 0x1f;
                    RSInterface.interfaceCache[l6].disabledColor = (i19 << 19) + (i22 << 11) + (l24 << 3);
                    pktType = -1;
                    return true;

                case 53:
                    // needDrawTabArea = true;
                    try {

                        int rsi_frame = getInputBuffer().readInt();
                        RSInterface class9_1 = RSInterface.interfaceCache[rsi_frame];
                        int totalItems = getInputBuffer().getUnsignedShort();
                        if (class9_1 == null || class9_1.inv == null || class9_1.invStackSizes == null) {
                            pktType = -1;
                            return true;
                        }
                        int it = -1;
                        for (int idx = 0; idx < totalItems; idx++) {
                            int itemAmt = getInputBuffer().getUnsignedByte();
                            if (itemAmt == 255)
                                itemAmt = getInputBuffer().method440();
                            it = getInputBuffer().getShortBigEndianA();
                            class9_1.inv[idx] = it;
                            class9_1.invStackSizes[idx] = itemAmt;
                        }

                        for (int idx = totalItems; idx < class9_1.inv.length
                                && idx < class9_1.invStackSizes.length; idx++) {
                            class9_1.inv[idx] = 0;
                            class9_1.invStackSizes[idx] = 0;
                        }
                        if (rsi_frame == 24680) {
                            getGrandExchange().itemSelected = it;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pktType = -1;
                    return true;

                case 230:
                    int j7 = getInputBuffer().method435();
                    int j14 = getInputBuffer().getUnsignedShort();
                    int k19 = getInputBuffer().getUnsignedShort();
                    int k22 = getInputBuffer().getShortBigEndianA();
                    RSInterface.interfaceCache[j14].modelRotationY = k19;
                    RSInterface.interfaceCache[j14].modelRotationX = k22;
                    RSInterface.interfaceCache[j14].modelZoom = j7;
                    pktType = -1;
                    return true;

                case 221:
                    setAnInt900(getInputBuffer().getUnsignedByte());
                    pktType = -1;
                    return true;

                case 112:
                    gameMode = getInputBuffer().getUnsignedByte();
                    pktType = -1;
                    return true;

                case 115:
                    showClanOptions = getInputBuffer().getUnsignedByte();
                    updateClanChatTab();
                    pktType = -1;
                    return true;

                case 177:
                    cameraViewChanged = true;
                    moveCameraX = getInputBuffer().getUnsignedByte();
                    moveCameraY = getInputBuffer().getUnsignedByte();
                    moveCameraZ = getInputBuffer().getUnsignedShort();
                    moveCameraSpeed = getInputBuffer().getUnsignedByte();
                    moveCameraAngle = getInputBuffer().getUnsignedByte();

                    if (moveCameraAngle >= 100) {
                        int k7 = moveCameraX * 128 + 64;
                        int k14 = moveCameraY * 128 + 64;
                        int i20 = method42(plane, k14, k7) - moveCameraZ;
                        int l22 = k7 - xCameraPos;
                        int k25 = i20 - zCameraPos;
                        int j28 = k14 - yCameraPos;
                        int i30 = (int) Math.sqrt(l22 * l22 + j28 * j28);
                        yCameraCurve = (int) (Math.atan2(k25, i30) * 325.94900000000001D) & 0x7ff;
                        xCameraCurve = (int) (Math.atan2(l22, j28) * -325.94900000000001D) & 0x7ff;

                        if (yCameraCurve < 128) {
                            yCameraCurve = 128;
                        }

                        if (yCameraCurve > 383) {
                            yCameraCurve = 383;
                        }
                    }

                    pktType = -1;
                    return true;

                case 38:
                    int auto = getInputBuffer().getUnsignedShort();
                    if (auto == -1) {
                        autoCast = false;
                        autocastId = 0;
                    } else {
                        autoCast = true;
                        autocastId = auto;
                    }
                    pktType = -1;
                    return true;

                case 127:
                    myRights = getInputBuffer().getUnsignedByte();
                    pktType = -1;
                    return true;

                case 224:
                    for (int j = 0; j < 3; j++) {
                        RSInterface scratchCardWidget = RSInterface.interfaceCache[25413 + j];
                        scratchCardWidget.unrevealedSprite = Sprite.copy(cacheSprite[1513]);
                    }
                    pktType = -1;
                    return true;

                case 203:
                    int progressBarIntId = getInputBuffer().getShort();
                    int progress = getInputBuffer().getByte();
                    RSInterface.interfaceCache[progressBarIntId].progress = progress;
                    pktType = -1;
                    return true;


                case 249:
                    anInt1046 = getInputBuffer().method426();
                    playerId = getInputBuffer().getUnsignedShort();
                    pktType = -1;
                    return true;

                case 65:
                    updateNPCs(getInputBuffer(), pktSize);
                    pktType = -1;
                    return true;

                case 27:
                    inputTitle = new String(getInputBuffer().getString());
                    messagePromptRaised = false;
                    inputDialogState = 1;
                    amountOrNameInput = "";
                    inputTaken = true;
                    pktType = -1;
                    return true;

                case 189: // long amount input
                    inputTitle = new String(getInputBuffer().getString());
                    messagePromptRaised = false;
                    inputDialogState = 4;
                    amountOrNameInput = "";
                    inputTaken = true;
                    pktType = -1;
                    return true;

                case 187:
                    inputTitle = new String(getInputBuffer().getString());
                    if (inputTitle.contains("#confirmstatus")) {
                        confirmEnter = true;
                        inputTitle = inputTitle.replace("#confirmstatus", "");
                    } else {
                        confirmEnter = false;
                    }

                    if (inputTitle.contains("$pin")) {
                        pinEnter = true;
                        inputTitle = inputTitle.replace("$pin", "");
                    } else {
                        pinEnter = false;
                    }
                    inputTaken = false;
                    inputDialogState = 2;
                    //if (!getOption("save_input")) {
                    amountOrNameInput = "";
                    //}
                    inputTaken = true;
                    pktType = -1;
                    return true;

                case 97:
                    int l7 = getInputBuffer().readInt();
                    resetInterfaceAnimation(l7);

                    if (invOverlayInterfaceID != -1) {
                        invOverlayInterfaceID = -1;
                        tabAreaAltered = true;
                    }

                    if (backDialogID != -1) {
                        backDialogID = -1;
                        inputTaken = true;
                    }

                    if (inputDialogState != 0) {
                        inputDialogState = 0;
                        inputTaken = true;
                    }
                    openInterfaceID = l7;
                    aBoolean1149 = false;
                    pktType = -1;
                    return true;

                case 218:
                    int i8 = getInputBuffer().method438();
                    dialogID = i8;
                    inputTaken = true;
                    pktType = -1;
                    return true;

                case 87:
                    int conigId = getInputBuffer().getShortBigEndian();
                    int configValue = getInputBuffer().method439();
                    settings[conigId] = configValue;

                    switch (conigId) {
                        case 2000:
                            updateBankInterface();
                            break;
                    }

                    if (conigId < 2000) {
                        if (variousSettings[conigId] != configValue) {
                            variousSettings[conigId] = configValue;
                            updateConfig(conigId);

                            if (dialogID != -1) {
                                inputTaken = true;
                            }
                        }
                    }

                    pktType = -1;
                    return true;

                case 89:
                    loadRegion();
                    pktType = -1;
                    return true;

                case 36:
                    try {
                        int settingId = getInputBuffer().getInt();
                        byte settingValue = getInputBuffer().getSignedByte();

                        if (settingId == -55) {
                            for (int i : RSInterface.interfaceCache[3213].children) {
                                RSInterface.interfaceCache[i].invSpritePadY = settingValue >= 1 ? 0 : 6;
                            }
                            if (settingValue >= 1) {
                                RSInterface.interfaceCache[16546].message = settingValue == 1 ? "Add to bag"
                                        : "View bag contents";
                                lootingBag = true;
                            } else {
                                lootingBag = false;
                            }
                            pktType = -1;
                            return true;
                        }

                    settings[settingId] = settingValue;
                    switch (settingId) {
                        case 2000:
                            updateBankInterface();
                            break;
                        case 19:
                            LOOP_MUSIC = settingValue == 1 ? true : false;
                            break;
                        case 293:
                            int sprite = settingValue == 0 ? 607
                                    : settingValue == 1 ? 606 : settingValue == 2 ? 608 : settingValue == 3 ? 609 : 610;
                            RSInterface.interfaceCache[12348].disabledSprite = Client.cacheSprite[sprite];
                            break;
                    }
                    if (settingId < 5000) {
                        if (variousSettings[settingId] != settingValue) {
                            variousSettings[settingId] = settingValue;
                            updateConfig(settingId);
                            if (dialogID != -1) {
                                inputTaken = true;
                            }
                        }
                    }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pktType = -1;
                    return true;

                case 61:
                    drawMultiwayIcon = getInputBuffer().getUnsignedByte();
                    pktType = -1;
                    return true;
                case 62:
                    drawXPwayIcon = getInputBuffer().getUnsignedByte();
                    pktType = -1;
                    return true;
                case 103:
                    doingDungeoneering = getInputBuffer().getUnsignedByte() == 1;
                    pktType = -1;
                    return true;

                case 200:
                    int l8 = getInputBuffer().getUnsignedShort();
                    int animId = getInputBuffer().getSignedShort();
                    RSInterface class9_4 = RSInterface.interfaceCache[l8];
                    class9_4.disabledAnimationId = animId;
                    class9_4.modelZoom = 2000;

                    if (animId == -1) {
                        class9_4.anInt246 = 0;
                        class9_4.anInt208 = 0;
                    }

                    pktType = -1;
                    return true;

                case 219:
                    if (invOverlayInterfaceID != -1) {
                        invOverlayInterfaceID = -1;
                        tabAreaAltered = true;
                    }

                    if (backDialogID != -1) {
                        backDialogID = -1;
                        inputTaken = true;
                    }

                    if (inputDialogState != 0) {
                        inputDialogState = 0;
                        inputTaken = true;
                    }

                    openInterfaceID = -1;
                    aBoolean1149 = false;
                    pktType = -1;
                    return true;

                case 210:
                    int widgId = getInputBuffer().getUnsignedShort();
                    int receivedActive = getInputBuffer().getUnsignedShort();
                    RSInterface showWidget = RSInterface.interfaceCache[widgId];
                    boolean show = receivedActive == 0 ? false : true;
                    showWidget.widgetActive = show;
                    pktType = -1;
                    return true;

                case 34:
                    int rsIntId = getInputBuffer().getUnsignedShort();
                    RSInterface rsInt = RSInterface.interfaceCache[rsIntId];

                    while (getInputBuffer().position < pktSize) {
                        int itemSlot = getInputBuffer().getSmart();
                        int itemInvId = getInputBuffer().getUnsignedShort();
                        int itemAmount = getInputBuffer().getUnsignedByte();

                        if (itemAmount == 255) {
                            itemAmount = getInputBuffer().getIntLittleEndian();
                        }

                        if (itemSlot >= 0 && itemSlot < rsInt.inv.length) {
                            rsInt.inv[itemSlot] = itemInvId;
                            rsInt.invStackSizes[itemSlot] = itemAmount;
                        }
                    }

                    pktType = -1;
                    return true;

                case 4:
                case 44:
                case 84:
                case 101:
                case 105:
                case 117:
                case 147:
                case 151:
                case 156:
                case 160:
                case 215:
                    parseEntityPacket(getInputBuffer(), pktType);
                    pktType = -1;
                    return true;

                case 106:
                    tabID = getInputBuffer().method427();
                    tabAreaAltered = true;
                    pktType = -1;
                    return true;

                case 164:
                    int j9 = getInputBuffer().getShortBigEndian();
                    if (chatArea.componentHidden()) {
                        chatArea.setHideComponent(false);
                    }
                    resetInterfaceAnimation(j9);

                    if (invOverlayInterfaceID != -1) {
                        invOverlayInterfaceID = -1;
                        tabAreaAltered = true;
                    }

                    backDialogID = j9;
                    inputTaken = true;
                    openInterfaceID = -1;
                    aBoolean1149 = false;
                    pktType = -1;
                    return true;

            }

            Signlink.reportError("T1 - " + pktType + "," + pktSize + " - " + previousPacket + "," + anInt843);
            // resetLogout();
        } catch (IOException _ex) {
            dropClient();
        } catch (Exception exception) {
            String s2 = "T2 - " + pktType + "," + previousPacket + "," + anInt843 + " - " + pktSize + ","
                    + (regionBaseX + myPlayer.smallX[0]) + "," + (regionBaseY + myPlayer.smallY[0]) + " - ";

            for (int j15 = 0; j15 < pktSize && j15 < 50; j15++) {
                s2 = s2 + getInputBuffer().buffer[j15] + ",";
            }

            Signlink.reportError(s2);
        }

        pktType = -1;
        return true;
    }

    private void drawTimers() {
        boolean test = false;

        boolean rdy = (updateVengTime == 0 || System.currentTimeMillis() > updateVengTime + 3000);
        if (vengTimer != -1) {
            if (vengToggle) {
                if (test) {
                    vengBar.drawAdvancedSprite(mouseX, mouseY);
                    newSmallFont.drawBasicString(calculateInMinutes(vengTimer), mouseX, mouseY + 15, 16753920, 1, true);
                    if (mouseX != -1 && mouseY != -1 && rdy) {
                        updateVengTime = System.currentTimeMillis();
                        System.out.println("mouseX: " + mouseX + ", mouseY: " + mouseY + " (+15 @ y)");
                    }
                    return;
                }
                if (GameFrame.getScreenMode() == ScreenMode.FIXED) {
                    int x = drawTimerPos[0][0];
                    int y = drawTimerPos[0][1];

                    /*
                     * if (shiftIsDown) { x = mouseX; newSmallFont.drawBasicString("X: "+x, mouseX,
                     * mouseY+30, 16753920, 1, true); } if (controlIsDown) { y = mouseY;
                     * newSmallFont.drawBasicString("Y: "+y, mouseX, mouseY+45, 16753920, 1, true);
                     * }
                     */

                    vengBar.drawAdvancedSprite(x - vengBar.myWidth, y - vengBar.myHeight);
                    newSmallFont.drawBasicString(calculateInMinutes(vengTimer), x, y, 16753920, 1, true);
                } else if (GameFrame.getScreenMode() != ScreenMode.FIXED) {
                    int x = getScreenWidth() + drawTimerPos[1][0];
                    int y = drawTimerPos[1][1];

                    /*
                     * if (shiftIsDown) { x = mouseX; newSmallFont.drawBasicString("X: "+x, mouseX,
                     * mouseY+30, 16753920, 1, true); } if (controlIsDown) { y = mouseY;
                     * newSmallFont.drawBasicString("Y: "+y, mouseX, mouseY+45, 16753920, 1, true);
                     * }
                     */

                    vengBar.drawAdvancedSprite(x - vengBar.myWidth, y - vengBar.myHeight);
                    newSmallFont.drawBasicString(calculateInMinutes(vengTimer), x, y, 16753920, 1, true);
                }

                /*
                 * if (GameFrame.getScreenMode() == ScreenMode.FIXED) {
                 * vengBar.drawAdvancedSprite(455, 250);
                 * newSmallFont.drawBasicString(calculateInMinutes(vengTimer), 476, 250 + 15,
                 * 16753920, 1, true); } else { //handle VENG icon drawing for non-fixed }
                 */
            }
        }
        // System.out.println("mouseX: "+mouseX+", mouseY: "+mouseY);
    }

    private String calculateInMinutes(int paramInt) {
        int i = (int) Math.floor(paramInt / 60);
        int j = paramInt - i * 60;
        String str1 = "" + i;
        String str2 = "" + j;
        if (j < 10) {
            str2 = "0" + str2;
        }
        if (i < 10) {
            str1 = "0" + str1;
        }
        return str1 + ":" + str2;
    }

    private void updateClanChatTab() {
        if (showClanOptions > 0) {
            RSInterface.getCustomInterfaces().rebuildClanChatList(true, myUsername, showClanOptions == 2);
        } else {
            RSInterface.getCustomInterfaces().rebuildClanChatList(false, "", false);
        }
    }

    @Override
    public void processDrawing() {
        checkResize();
        if (loadingError) {
            processLoadingError("An internal error occured whilst loading the " + Configuration.CLIENT_NAME + " client",
                    "The client's common error quick fix system is attempting to repair the cause",
                    "The client will automatically close in 10 seconds...");
            return;
        }
        if (isLoading)
            return;
        if (!loggedIn) {
            drawLoginScreen(false);
        } else {
            drawGameScreen();

        }

        anInt1213 = 0;
    }

    @Override
    public void processGameLoop() {
        if (loadingError) {
            return;
        }

        loopCycle++;

        if (!loggedIn) {
            processLoginScreenInput();
        } else {
            mainGameProcessor();
        }

        processOnDemandQueue();
        if (!isApplet) {
            checkSize();
        }
        method49();
        handleSounds();
    }

    public boolean rememberUsername;
    public boolean rememberPassword;

    private void processLoginScreenInput() {
        if (super.clickMode3 == 1) {
            if (rememberMeHover) {
                rememberUsername = !rememberUsername;
                rememberPassword = !rememberPassword;
                Configuration.SAVE_ACCOUNTS = !Configuration.SAVE_ACCOUNTS;
                Save.settings(Client.getClient());
                return;
            } else if (textArea1Hover) {
                loginScreenCursorPos = 0;
                return;
            } else if (textArea2Hover) {
                loginScreenCursorPos = 1;
                return;
            } else if (loginHover) {
                login(password, false, myUsername, this);
                return;
            }

            if (otherHover) {
                showCaptcha = false;
                showTwoFactorAuth = false;
                loginMessages = new String[]{""};
            }
            for (int i = 0; i < accountHovers.length; i++) {
                if (accountHovers[i]) {
                    Account account = accountManager.getAccounts()[i];
                    if (account == null) {
                        continue;
                    } else if (accountHovers[i]) {
                        myUsername = account.getUsername();
                        password = account.getPassword();
                        login(password, false, myUsername, this);
                        break;
                    }
                }
            }
        }
        //  System.out.println("screen state" + getLoginScreenState());

        if (getLoginScreenState() == 0) {

            do {
                int keyChar = readChar(-796);

                if (keyChar == -1) {
                    return;
                }

                if (keyChar == 96) {
                    return;
                }

                if (loggedIn) {
                    return;
                }

              /*  if (!loginMessage1.isEmpty() || !loginMessage2.isEmpty()) {
                    if (keyChar == 32 || keyChar == 10 || keyChar == 8) {
                        loginMessage1 = loginMessage2 = "";
                        loggingIn = false;
                    }
                    return;
                }*/

                if (consoleOpen) {
                    if (keyChar == 8 && consoleInput.length() > 0) {
                        consoleInput = consoleInput.substring(0, consoleInput.length() - 1);
                    }
                    if (keyChar >= 32 && keyChar <= 122 && consoleInput.length() < 80) {
                        consoleInput += (char) keyChar;
                    }

                    if ((keyChar == 13 || keyChar == 10) && consoleInput.length() > 0) {
                        printConsoleMessage(consoleInput, 0);
                        sendCommandPacket(consoleInput);
                        consoleInput = "";
                        inputTaken = true;
                    }
                    return;
                }

                boolean flag1 = false;

                if (showTwoFactorAuth && !showCaptcha) {
                    for (int i2 = 0; i2 < VALID_AUTH_KEYS.length(); i2++) {
                        if (keyChar != VALID_AUTH_KEYS.charAt(i2)) {
                            continue;
                        }

                        flag1 = true;
                        break;
                    }
                } else {
                    for (int index = 0; index < validUserPassChars.length(); index++) {
                        if (keyChar != validUserPassChars.charAt(index)) {
                            continue;
                        }
                        flag1 = true;
                        break;
                    }
                }

                if (getLoginScreenCursorPos() == 0) {
                    if (showTwoFactorAuth) {
                        if (keyChar == 8 && currentPinCode.length() > 0) {
                            currentPinCode = currentPinCode.substring(0, currentPinCode.length() - 1);
                        }
                        if (flag1) {
                            currentPinCode = currentPinCode + (char) keyChar;
                        }
                        if (currentPinCode.length() > 4) {
                            currentPinCode = currentPinCode.substring(0, 4);
                        }
                        if (keyChar == 10 || keyChar == 13) {
                            if (currentPinCode.length() == 4) {
                                loginFailures = 0;
                                login(getPassword(), false, myUsername, this);
                                if (loggedIn) {
                                    return;
                                }
                            }
                        }
                    } else {
                        if (keyChar == 8 && myUsername.length() > 0) {
                            myUsername = myUsername.substring(0, myUsername.length() - 1);
                        }

                        if (keyChar == 9 || keyChar == 10 || keyChar == 13) {
                            setLoginScreenCursorPos(1);
                        }

                        if (myUsername.length() >= 24) {
                            letterArray.add((char) keyChar);
                        }

                        if (flag1) {
                            myUsername += (char) keyChar;
                            myUsername = optimizeText(myUsername);
                        }
                        if (myUsername.length() > 12) {
                            myUsername = myUsername.substring(0, 12);
                        }
                    }
                } else if (getLoginScreenCursorPos() == 1) {
                    if (keyChar == 8 && getPassword().length() > 0) {
                        setPassword(getPassword().substring(0, getPassword().length() - 1));
                    }

                    if (keyChar == 9 || keyChar == 10 || keyChar == 13) {
                        login(getPassword(), false, myUsername, this);
                        if (loggedIn)
                            return;
                    }

                    if (flag1) {
                        setPassword(getPassword() + (char) keyChar);
                    }

                    if (getPassword().length() > 15) {
                        setPassword(getPassword().substring(0, 15));
                    }
                }
            } while (true);

        }
    }

    private void login(String password, boolean reconnecting, String username, Client client) {

        if (!clickDelay.elapsed(1500)) {
            return;
        }
        clickDelay.reset();

        //    if (loggingIn) {
        //       return;
        //    }

        EffectTimers.getTimers().clear();

        username = TextClass.fixName(username);
        username = optimizeText(username);
        if (username.toLowerCase().contains("admin") || username.toLowerCase().contains("mod")
                || username.toLowerCase().contains("dev") || username.toLowerCase().contains("owner")
                || (username.toLowerCase().contains("test") && !username.equalsIgnoreCase("test"))) {
            if (!Configuration.localHost) {
                loginMessages = new String[]{"This username has been blocked", "and cannot be used."};
                return;
            }
        }
        if (username.startsWith(" ") || username.startsWith("_")) {
            loginMessages = new String[]{"Your username cannot start with a space."};
            return;
        }
        if (username.endsWith(" ") || username.endsWith("_")) {
            loginMessages = new String[]{"Your username cannot end with a space."};
            return;
        }
        if (username.length() < 1 && password.length() < 1) {
            loginMessages = new String[]{"Please enter a valid username and password."};
            return;
        } else if (password.length() < 3) {
            loginMessages = new String[]{"Your password is too short."};
            return;
        } else if (username.length() < 1) {
            loginMessages = new String[]{"Your username is too short."};
            return;
        } else if (username.length() > 12) {
            loginMessages = new String[]{"Your username is too long."};
            return;
        } else if (password.length() > 24) {
            loginMessages = new String[]{"Your password is too long."};
            return;
        }
        loginMessages = new String[]{"Attempting to login"};

        if (client.getLoginState() == 0) {
            client.setLoginFailures(0);
        }

        try {
            // loggingIn = true;
            loginMessages = new String[]{"Attempting to login"};
            drawLoginScreen(false);

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (loggingIn && !loggedIn && loginMessages[0].contains("Attempting to login")
                            && loginMessages[0].length() <= 29) {
                        try {
                            loginMessages[0] += ".";
                            drawLoginScreen(false);
                            Thread.sleep(250);
                        } catch (Exception e) {
                        }
                    }
                }
            }, "Login");
            t.start();

            initiateConnection(client);

            int responseCode = receiveResponse(client);

            handleResponse(client, responseCode, username, password, getMac(),
                    serial, reconnecting);

        } catch (IOException _ex) {
            loginMessages = new String[]{"Error connecting to server."};
            loggedIn = loggingIn = false;
        }
    }

    public int receiveResponse(Client client) throws IOException {
        int responseCode = client.getConnection().read();
        return responseCode;
    }

    public void initiateConnection(Client client) throws IOException {
        client.setConnection(new Connection(client, client.createGameServerSocket(Configuration.SERVER_PORT)));
        long l = TextClass.longForName(myUsername);
        int i = (int) (l >> 16 & 31L);
        Client.getOut().position = 0;
        Client.getOut().putByte(14);
        Client.getOut().putByte(i);
        client.getConnection().queueBytes(2, Client.getOut().buffer);

        for (int j = 0; j < 8; j++)
            client.getConnection().read();

    }

    public int[] handleEncryption(String password, Client client, String username, String mac, String uuid) {
        client.setServerSeed(client.getInputBuffer().getLong());
        int[] seed = new int[4];
        seed[0] = (int) (Math.random() * 99999999D);
        seed[1] = (int) (Math.random() * 99999999D);
        seed[2] = (int) (client.getServerSeed() >> 32);
        seed[3] = (int) client.getServerSeed();
        Client.getOut().position = 0;
        Client.getOut().putByte(10);
        Client.getOut().putInt(seed[0]);
        Client.getOut().putInt(seed[1]);
        Client.getOut().putInt(seed[2]);
        Client.getOut().putInt(seed[3]);
        Client.getOut().putInt(350 >> 2240);
        Client.getOut().putString(username);
        Client.getOut().putString(password);
        Client.getOut().putString("null000");
        Client.getOut().putString("null000");
        Client.getOut().putShort(222);
        Client.getOut().putByte(0);
        Client.getOut().doKeys();
        return seed;
    }

    private void writeData(boolean reconnecting, Client client) throws UnsupportedEncodingException {
        client.getLoginBuffer().position = 0;
        client.getLoginBuffer().putByte(reconnecting ? 18 : 16); // login type
        client.getLoginBuffer().putByte(Client.getOut().position + 36 + 1 + 1 + 2 + currentPinCode.length() + 1);
        client.getLoginBuffer().putByte(255);
        client.getLoginBuffer().putShort(Configuration.CLIENT_VERSION);
        client.getLoginBuffer().putByte(Client.isLowDetail() ? 1 : 0);

        if (Configuration.SEND_HASH) {
            byte[] bytes = hash.getBytes("UTF-8");
            getLoginBuffer().putByte(bytes.length);
            getLoginBuffer().putBytes(bytes, bytes.length, 0);
        }

        try {
            pinCode = currentPinCode == null || currentPinCode.length() == 0 ? -1
                    : Integer.parseInt(currentPinCode);
        } catch (NumberFormatException e) {
            pinCode = -1;
        }
        getLoginBuffer().putShort(pinCode);
        getLoginBuffer().putString(currentPinCode);

        for (int l1 = 0; l1 < 9; l1++) {
            getLoginBuffer().putInt(expectedCRCs[l1]);
        }

        client.getLoginBuffer().putBytes(Client.getOut().buffer, Client.getOut().position, 0);
    }

    /**
     * Handles the response from the client
     *
     * @param response     The response code from the client
     * @param username     The username of the player
     * @param password     The password of the player
     * @param reconnecting The player is reconnecting
     * @throws IOException
     */
    public boolean handleResponse(Client client, int response, String username, String password, String mac,
                                  String uuid, boolean reconnecting) throws IOException {
        int initialResponseCode = response;

        if (response == 0) {
            client.getConnection().flushInputStream(client.getInputBuffer().buffer, 8);
            client.getInputBuffer().position = 0;

            int[] seed = handleEncryption(password, client, username, mac, uuid);

            writeData(reconnecting, client);

            Client.getOut().encryption = new ISAACCipher(seed);

            for (int i = 0; i < 4; i++) {
                seed[i] += 50;
            }

            client.setConnectionCipher(new ISAACCipher(seed));

            client.getConnection().queueBytes(client.getLoginBuffer().position, client.getLoginBuffer().buffer);

            response = client.getConnection().read();
            showTwoFactorAuth = false;
            currentPinCode = "";
        }

        if (response == 1) {
            loggingIn = false;
            try {
                Thread.sleep(2000L);
            } catch (Exception _ex) {
            }

            login(password, reconnecting, username, client);
            return false;
        }

        if (response == 2) {
            finishLogin(client);
            PlayerHandler.load(client);
            return false;
        }

        if (!handleRejection(response, username, reconnecting, client, password)) {
            loggingIn = false;
            showCaptcha = false;
            showTwoFactorAuth = false;
            return false;
        }

        if (response == -1) {
            loggingIn = false;
            if (initialResponseCode == 0 && client.getLoginState() != 0) {
                if (client.getLoginFailures() < 2) {
                    try {
                        Thread.sleep(2000L);
                    } catch (Exception ignored) {
                    }

                    client.setLoginFailures(client.getLoginFailures() + 1);
                    login(password, reconnecting, username, client);
                } else {
                    loginMessages = new String[]{"Too many login attempts!"};
                }
                return false;
            } else {
                loginMessages = new String[]{"No response from server!"};
                return false;
            }
        } else {
            loginMessages = new String[]{"Unexpected server response.", "Please try using a different world."};
            return false;
        }
    }

    public boolean handleRejection(int loginCode, String username, boolean reconnecting, Client client, String password)
            throws IOException {
        if (loginCode == 3) {
            loginMessages = new String[]{"Invalid username or password."};
            return false;
        }
        if (loginCode == 4) {
            loginMessages = new String[]{"This account has been banned! Appeal on the forum."};
            return false;
        }
        if (loginCode == 5) {
            loginMessages = new String[]{"This account is already logged in.",
                    "Please try again in 60 seconds.."};
            return false;
        }
        if (loginCode == 6) {
            loginMessages = new String[]{Configuration.CLIENT_NAME + " is currently being updated.",
                    "Please try again in 60 seconds.."};
            return false;
        }
        if (loginCode == 7) {
            loginMessages = new String[]{Configuration.CLIENT_NAME + " is currently busy.", "Please try again."};
            return false;
        }
        if (loginCode == 8) {
            loginMessages = new String[]{Configuration.CLIENT_NAME + " login server is down.", "Please try again in 60 seconds.."};
            return false;
        }
        if (loginCode == 9) {
            loginMessages = new String[]{"Login limit exceeded. Too many connections", "from your address."};
            return false;
        }
        if (loginCode == 10) {
            loginMessages = new String[]{"Unable to connect!", "Server responded: bad session id!"};
            return false;
        }
        if (loginCode == 11) {
            loginMessages = new String[]{"Unable to connect!", "Server responded: rejected session!"};
            return false;
        }
        if (loginCode == 12) {
            loginMessages = new String[]{"You need to be a member to login to this world."};
            return false;
        }
        if (loginCode == 13) {
            loginMessages = new String[]{"Login could not be completed. Try again!"};
            return false;
        }
        if (loginCode == 14) {
            loginMessages = new String[]{Configuration.CLIENT_NAME + " is currently being updated.",
                    "Please try again in 60 seconds.."};
            return false;
        }
        if (loginCode == 23) {
            loginMessages = new String[]{Configuration.CLIENT_NAME + " is currently being launched.",
                    "Please try again in 60 seconds.."};
            return false;
        }
        if (loginCode == 27) {
            loginMessages = new String[]{"Your IP-Adress has been banned.", "Please appeal on the forums."};
            return false;
        }
        if (loginCode == 28) {
            loginMessages = new String[]{"Your username contains invalid letters.",
                    "Your username contains invalid letters."};
            return false;
        }
        if (loginCode == 29) {
            loginMessages = new String[]{"Old client usage detected.", "Download the latest one!"};
            return false;
        }
        if (loginCode == 31) {
            loginMessages = new String[]{"Your username cannot start with a space."};
            return false;
        }
        if (loginCode == 22) {
            loginMessages = new String[]{"This computer has been banned.", "Appeal on the forum!"};
            return false;
        }
        if (loginCode == 30) {
            loginMessages = new String[]{Configuration.CLIENT_NAME + " has been updated!",
                    isWebclient() ? "Refresh this page." : "Download the latest client."};
            return false;
        }
        if (loginCode == 16) {
            loginMessages = new String[]{"Login attempts exceeded.", "Please wait 1 minute and try again."};
            return false;
        }
        if (loginCode == 17) {
            loginMessages = new String[]{"You are standing in a members-only area.",
                    "To play on this world move to a free area first."};
            return false;
        }
        if (loginCode == 20) {
            loginMessages = new String[]{"Invalid loginserver requested", "Please try using a different world."};
            return false;
        }
        if (loginCode == 37) {
            loginMessages = new String[]{"This account does not exist. You can create",
                    "it by clicking the button below."};
            return false;
        }

        if (loginCode == 21) {
            for (int loginCode1 = client.getConnection().read(); loginCode1 >= 0; loginCode1--) {
                loginMessages = new String[]{"You have only just left another world",
                        "Your profile will be transferred in: " + loginCode1 + " seconds"};
                drawLoginScreen(false);
                try {
                    Thread.sleep(1000L);
                } catch (Exception _ex) {
                }
            }

            login(username, reconnecting, password, this);
            return false;
        }
        return true;
    }

    /**
     * Finishes the successful login for the player
     *
     * @throws IOException
     */
    public void finishLogin(Client client) throws IOException {
        client.myRights = client.getConnection().read();
        Client.flagged = client.getConnection().read() == 1;

        int captchaResponse = client.getConnection().read();
        boolean captcha = captchaResponse >= 1;

        if (captcha) {

            int length = ((client.getConnection().read() & 0xff) << 8) + (client.getConnection().read() & 0xff);

            if (length > 60000) {
                length = -65535 + length;
            }

            byte[] image = new byte[length];

            for (int n = 0; n < length; n++) {
                image[n] = (byte) client.getConnection().read();
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(image);
            BufferedImage buffer = ImageIO.read(bais);

            // ImageIO.write(buffer, "jpg", new File("test.jpg"));
            Client.this.captcha = new Sprite(image);
            showTwoFactorAuth = true;
            showCaptcha = true;
            loginScreenCursorPos = 0;

            return;

        }

        client.mouseDetection.coordsIndex = 0;
        client.awtFocus = true;
        client.aBoolean954 = true;
        client.loggedIn = true;
        Client.getOut().position = 0;
        client.getInputBuffer().position = 0;
        client.pktType = -1;
        client.anInt841 = -1;
        client.previousPacket = -1;
        client.anInt843 = -1;
        client.pktSize = 0;
        client.anInt1009 = 0;
        client.systemUpdateTimer = 0;
        client.broadcastMinutes = 0;
        client.broadcastMessage = null;
        client.anInt1011 = 0;
        client.anInt855 = 0;
        client.menuActionRow = 0;
        client.menuOpen = false;
        client.idleTime = 0;
        client.itemSelected = 0;
        client.spellSelected = 0;
        client.loadingStage = 0;
        client.soundCount = 0;
        client.setNorth();
        client.setScriptManager(null);
        client.minimapStatus = 0;
        client.setLastKnownPlane(-1);
        client.destX = 0;
        client.destY = 0;
        client.playerCount = 0;
        client.npcCount = 0;
        loadGoals(myUsername);
        for (int i = 0; i < client.getMaxPlayers(); i++) {
            client.playerArray[i] = null;
            client.getaStreamArray895s()[i] = null;
        }

        for (int i = 0; i < 16384; i++) {
            client.npcArray[i] = null;
        }

        Client.myPlayer = client.playerArray[client.getMyPlayerIndex()] = new Player();
        client.getProjectiles().removeAll();
        client.getIncompleteAnimables().removeAll();

        for (int l2 = 0; l2 < 4; l2++) {
            for (int i3 = 0; i3 < 104; i3++) {
                for (int k3 = 0; k3 < 104; k3++) {
                    client.groundArray[l2][i3][k3] = null;
                }
            }
        }

        client.setaClass19_1179(new Deque());
        client.setFullscreenInterfaceID(-1);
        client.setAnInt900(0);
        client.friendCount = 0;
        client.dialogID = -1;
        client.backDialogID = -1;
        Client.openInterfaceID = -1;
        client.invOverlayInterfaceID = -1;
        client.setWalkableInterfaceId(-1);
        client.aBoolean1149 = false;
        Client.tabID = 3;
        client.inputDialogState = 0;
        client.menuOpen = false;
        client.messagePromptRaised = false;
        client.aString844 = null;
        client.drawMultiwayIcon = 0;
        client.drawXPwayIcon = 0;
        client.anInt1054 = -1;
        client.isMale = true;
        client.method45();

        for (int j3 = 0; j3 < 5; j3++) {
            client.anIntArray990[j3] = 0;
        }

        for (int l3 = 0; l3 < 7; l3++) {
            client.atPlayerActions[l3] = null;
            client.atPlayerArray[l3] = false;
        }

        Client.setAnInt1175(0);
        Client.setAnInt1134(0);
        Client.setAnInt986(0);
        Client.setAnInt1288(0);
        Client.setAnInt924(0);
        Client.setAnInt1188(0);
        Client.setAnInt1155(0);
        Client.setAnInt1226(0);
        client.chatTypes = new int[500];
        client.chatNames = new String[500];
        client.chatMessages = new String[500];
        client.resetImageProducers2();
        client.updateGraphics(true);
        if (GameFrame.getScreenMode() == ScreenMode.FIXED) {
            client.updateGameArea();
        }
        updateSettingsInterface();

        if (Configuration.PLAYER_EQUIPMENT)
            sendPacket185(41847);
        else
            sendPacket185(41848);

        if (Configuration.RENDER_PETS)
            sendPacket185(41850);
        else
            sendPacket185(41851);
        loginMessages = new String[]{""};

        loggingIn = false;
    }

    private void processMainScreenClick() {
        if (loadingStage != 2) {
            // Disable clicking on minimap and scene
            // when the map is still loading.
            return;
        }
        if (minimapStatus != 0) {
            return;
        }

        if (super.clickMode3 == 1) {
            int clickX = super.saveClickX
                    - (GameFrame.getScreenMode() == ScreenMode.FIXED ? 553 : mapArea.getOffSetX() + 14);
            int clickY = super.saveClickY - (GameFrame.getScreenMode() == ScreenMode.FIXED ? 9 : 5);

            if (inCircle(0, 0, clickX, clickY, 76)) {
                clickX -= 73;
                clickY -= 75;
                int k = viewRotation + minimapRotation & 0x7ff;
                int i1 = Rasterizer.SINE[k];
                int j1 = Rasterizer.COSINE[k];
                i1 = i1 * (minimapZoom + 256) >> 8;
                j1 = j1 * (minimapZoom + 256) >> 8;
                int k1 = clickY * i1 + clickX * j1 >> 11;
                int l1 = clickY * j1 - clickX * i1 >> 11;
                int i2 = myPlayer.x + k1 >> 7;
                int j2 = myPlayer.y - l1 >> 7;
                if ((myRights == 3 || myRights == 4) && controlShiftTeleporting) {
                    teleport(regionBaseX + i2, regionBaseY + j2);
                } else {
                    boolean flag1 = doWalkTo(1, 0, 0, 0, myPlayer.smallY[0], 0, 0, j2, myPlayer.smallX[0], true, i2);

                    if (flag1) {
                        getOut().getByte(clickX);
                        getOut().getByte(clickY);
                        getOut().putShort(viewRotation);
                        getOut().putByte(57);
                        getOut().putByte(minimapRotation);
                        getOut().putByte(minimapZoom);
                        getOut().getByte(89);
                        getOut().putShort(myPlayer.x);
                        getOut().putShort(myPlayer.y);
                        getOut().getByte(anInt1264);
                        getOut().getByte(63);
                    }
                }
            }

            anInt1117++;

            if (anInt1117 > 1151) {
                anInt1117 = 0;
                getOut().putOpcode(246);
                getOut().getByte(0);
                int l = getOut().position;

                if ((int) (Math.random() * 2D) == 0) {
                    getOut().getByte(101);
                }

                getOut().getByte(197);
                getOut().putShort((int) (Math.random() * 65536D));
                getOut().getByte((int) (Math.random() * 256D));
                getOut().getByte(67);
                getOut().putShort(14214);

                if ((int) (Math.random() * 2D) == 0) {
                    getOut().putShort(29487);
                }

                getOut().putShort((int) (Math.random() * 65536D));

                if ((int) (Math.random() * 2D) == 0) {
                    getOut().getByte(220);
                }

                getOut().getByte(180);
                getOut().putVariableSizeByte(getOut().position - l);
            }
        }
    }

    private boolean processMenuClick() {
        if (activeInterfaceType != 0) {
            return false;
        }

        int clickType = super.clickMode3;

        if (spellSelected == 1 && super.saveClickX >= 516 && super.saveClickY >= 160 && super.saveClickX <= 765
                && super.saveClickY <= 205) {
            clickType = 0;
        }

        if (menuOpen) {
            if (clickType != 1) {
                int clickX = super.mouseX;
                int clickY = super.mouseY;

                if (menuScreenArea == 0) {
                    clickX -= GameFrame.getScreenMode() == ScreenMode.FIXED ? 4 : 0;
                    clickY -= GameFrame.getScreenMode() == ScreenMode.FIXED ? 4 : 0;
                }

                if (menuScreenArea == 1) {
                    clickX -= 519;
                    clickY -= 168;
                }

                if (menuScreenArea == 2) {
                    clickX -= 17;
                    clickY -= 338;
                }

                if (menuScreenArea == 3) {
                    clickX -= 519;
                    clickY -= 0;
                }

                if (clickX < menuOffsetX - 10 || clickX > menuOffsetX + menuWidth + 10 || clickY < menuOffsetY - 10
                        || clickY > menuOffsetY + menuHeight + 10) {
                    menuOpen = false;
                    // if (menuScreenArea == 1)
                    // {
                    // needDrawTabArea = true;
                    // }
                    if (menuScreenArea == 2) {
                        inputTaken = true;
                    }
                }
            }

            if (clickType == 1) {
                int xOffset = menuOffsetX;
                int yOffset = menuOffsetY;
                int width = menuWidth;
                int clickX = super.saveClickX;
                int clickY = super.saveClickY;

                if (menuScreenArea == 0) {
                    clickX -= GameFrame.getScreenMode() == ScreenMode.FIXED ? 4 : 0;
                    clickY -= GameFrame.getScreenMode() == ScreenMode.FIXED ? 4 : 0;
                }

                if (menuScreenArea == 1) {
                    clickX -= 519;
                    clickY -= 168;
                }

                if (menuScreenArea == 2) {
                    clickX -= 17;
                    clickY -= 338;
                }

                if (menuScreenArea == 3) {
                    clickX -= 519;
                    clickY -= 0;
                }

                int actionIndex = -1;

                for (int index = 0; index < menuActionRow; index++) {
                    int row = yOffset + 31 + (menuActionRow - 1 - index) * 15;

                    if (clickX > xOffset && clickX < xOffset + width && clickY > row - 11 && clickY < row + 5) {
                        actionIndex = index;
                    }
                }

                if (actionIndex != -1) {
                    doAction(actionIndex);
                }

                menuOpen = false;

                if (menuScreenArea == 2) {
                    inputTaken = true;
                }
            }

            return true;
        } else {
            if (clickType == 1 && menuActionRow > 0) {
                int actionId = menuActionID[menuActionRow - 1];

                if (actionId == 632 || actionId == 78 || actionId == 867 || actionId == 431 || actionId == 53
                        || actionId == 74 || actionId == 454 || actionId == 539 || actionId == 493 || actionId == 847
                        || actionId == 447 || actionId == 1125 || actionId == 633) {
                    int actionOne = menuActionCmd2[menuActionRow - 1];
                    int actionTwo = menuActionCmd3[menuActionRow - 1];
                    RSInterface rsi = RSInterface.interfaceCache[actionTwo];

                    if (rsi.allowSwapItems || rsi.deletesTargetSlot) {
                        aBoolean1242 = false;
                        anInt989 = 0;
                        modifiedWidgetId = actionTwo;
                        anInt1085 = actionOne;
                        activeInterfaceType = 2;
                        anInt1087 = super.saveClickX;
                        anInt1088 = super.saveClickY;

                        if (RSInterface.interfaceCache[actionTwo].parentID == openInterfaceID) {
                            activeInterfaceType = 1;
                        }

                        if (RSInterface.interfaceCache[actionTwo].parentID == backDialogID) {
                            activeInterfaceType = 3;
                        }

                        return true;
                    }
                }
            }

            if (clickType == 1 && (anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2) {
                clickType = 2;
            }

            if (clickType == 1 && menuActionRow > 0) {
                doAction(menuActionRow - 1);
            }

            if (clickType == 2 && menuActionRow > 0) {
                determineMenuSize();
            }

            return false;
        }
    }


    public static final int MAP_IDX = 4;
    public static final int MODEL_IDX = 1, CONFIG_IDX = 0, OSRS_MODEL_IDX = 6, OSRS_ANIM_IDX = 7, OSRS_MAP_IDX = 8;
    public static final int ANIM_IDX = 2, AUDIO_IDX = 3, IMAGE_IDX = 5;

    private void processOnDemandQueue() {
        do {
            OnDemandRequest onDemandData;

            do {
                onDemandData = onDemandFetcher.getNextNode();

                if (onDemandData == null) {
                    return;
                }

                if (onDemandData.getDataType() == MODEL_IDX - 1) {
                    Model.method460(onDemandData.getBuffer(), onDemandData.getId(), Model.modelsArray);
                   // Model.method460(onDemandData.getBuffer(), onDemandData.getId(), Model.modelsArrayOSRS);
                    if (backDialogID != -1) {
                        setInputTaken(true);
                    }
                }
                /**
                 * OSRS Models Loading *
                 */
                if (onDemandData.getDataType() == OSRS_MODEL_IDX - 1) {
                    Model.method460(onDemandData.getBuffer(), onDemandData.getId(), Model.modelsArrayOSRS);
                }

                if (onDemandData.getDataType() == ANIM_IDX - 1) {
                    FrameReader.load(onDemandData.getId(), onDemandData.getBuffer(), false);
                }
                if (onDemandData.getDataType() == OSRS_ANIM_IDX - 1) {
                    FrameReader.load(onDemandData.getId(), onDemandData.getBuffer(), true);
                }

                if (onDemandData.getDataType() == AUDIO_IDX - 1 && onDemandData.getId() == nextSong
                        && onDemandData.getBuffer() != null) {
                    musicData = new byte[onDemandData.getBuffer().length];
                    ArrayUtils.arraycopy(onDemandData.getBuffer(), 0, musicData, 0, musicData.length);
                    fetchMusic = true;
                }

                if (onDemandData.getDataType() == MAP_IDX - 1 || onDemandData.getDataType() == OSRS_MAP_IDX - 1 && loadingStage == 1) {
                    for (int i = 0; i < terrainData.length; i++) {
                        if (floorMap[i] == onDemandData.getId()) {
                            terrainData[i] = onDemandData.getBuffer();

                            if (onDemandData.getBuffer() == null) {
                                floorMap[i] = -1;
                            }

                            break;
                        }

                        if (objectMap[i] != onDemandData.getId()) {
                            continue;
                        }

                        objectData[i] = onDemandData.getBuffer();

                        if (onDemandData.getBuffer() == null) {
                            objectMap[i] = -1;
                        }

                        break;
                    }
                }
                if (onDemandData.getDataType() == IMAGE_IDX - 1) {
                    Texture.decode(onDemandData.getId(), onDemandData.getBuffer());
                }
            } while (onDemandData.getDataType() != 93 || !onDemandFetcher.method564(onDemandData.getId()));

            DataType dataType = DataType.REGULAR;
            if (onDemandData.getDataType() == 7) {
                dataType = DataType.OLDSCHOOL;
            }


            ObjectManager.method173(new ByteBuffer(onDemandData.getBuffer()), onDemandFetcher, dataType);
        } while (true);
    }

    private void processRightClick() {
        if (activeInterfaceType != 0) {
            return;
        }

        menuActionName[0] = "Cancel";
        menuActionID[0] = 1107;
        menuActionRow = 1;

        int splitBoxX = 495;
        int splitBoxY = 122 + (GameFrame.getScreenMode() == ScreenMode.FIXED ? 0 : 2);

        if (getFullscreenInterfaceID() != -1) {
            anInt886 = 0;
            anInt1315 = 0;
            skillTabHoverChild = 0;
            buildInterfaceMenu(clientWidth / 2 - 765 / 2, RSInterface.interfaceCache[fullscreenInterfaceID],
                    super.mouseX, clientHeight / 2 - 503 / 2, super.mouseY, 0);
           /* buildInterfaceMenu(
                    GameFrame.getScreenMode() == ScreenMode.FIXED ? 8
                            : getScreenWidth() / 2 - RSInterface.interfaceCache[getFullscreenInterfaceID()].width / 2,
                    RSInterface.interfaceCache[getFullscreenInterfaceID()], super.mouseX,
                    GameFrame.getScreenMode() == ScreenMode.FIXED ? 8
                            : getScreenHeight() / 2 - RSInterface.interfaceCache[getFullscreenInterfaceID()].height / 2,
                    super.mouseY, 0);*/

            if (anInt886 != anInt1026) {
                anInt1026 = anInt886;
            }

            if (anInt1315 != anInt1129) {
                anInt1129 = anInt1315;
            }

            return;
        }

        if (!chatArea.componentHidden() && isGameFrameVisible()) {
            buildSplitPrivateChatMenu();
        }

        boolean inSplitChatSelectionBox = mouseX >= splitBoxX && mouseX <= splitBoxX + 15
                && mouseY >= splitBoxY + chatArea.getyPos() && mouseY <= splitBoxY + chatArea.getyPos() + 13;
        if (inSplitChatSelectionBox) {
            chatArea.channel.processChatModeActions(this, GameFrame.getScreenMode());
            return;
        }

        anInt886 = 0;
        anInt1315 = 0;
        skillTabHoverChild = 0;
        int width = GameFrame.getScreenMode() != ScreenMode.FIXED ? getScreenWidth() : 516;
        int height = GameFrame.getScreenMode() != ScreenMode.FIXED ? getScreenHeight() : 338;

        if (super.mouseX > gameScreenDrawX && super.mouseY > gameScreenDrawY && super.mouseX < width
                && super.mouseY < height) {
            if (openInterfaceID != -1 && isGameFrameVisible()) {
                RSInterface rsInterface = RSInterface.interfaceCache[openInterfaceID];

                if (GameFrame.getScreenMode() != ScreenMode.FIXED) {
                    int interfaceWidth = GameFrame.getScreenMode() != ScreenMode.FIXED ? getScreenWidth() : 516;
                    int interfaceHeight = GameFrame.getScreenMode() != ScreenMode.FIXED ? getScreenHeight() : 338;
                    buildInterfaceMenu(gameScreenDrawX + (interfaceWidth - 765) / 2, rsInterface, super.mouseX,
                            gameScreenDrawY + (interfaceHeight - 503) / 2, super.mouseY, 0);
                } else {
                    buildInterfaceMenu(4, rsInterface, super.mouseX, 4, super.mouseY, 0);
                }
            } else {
                try {
                    if (GameFrame.getScreenMode() != ScreenMode.FIXED) {
                        if (!mapArea.isHovering(this, GameFrame.getScreenMode())
                                && !chatArea.isHovering(this, GameFrame.getScreenMode())
                                && !tabArea.isHovering(this, GameFrame.getScreenMode())) {
                            build3dScreenMenu();
                        }
                    } else {
                        build3dScreenMenu();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        alertHandler.processMouse(super.mouseX, super.mouseY);
        alertManager.processMouse(mouseX, mouseY);

        if (broadcastMessage != null) {
            normalText.method385(0xffff00, broadcastMessage, GameFrame.isFixed() ? 329 : getScreenHeight() - 168, 4);
            if (mouseX >= 4 && mouseX <= 503 &&
                    mouseY >= (GameFrame.isFixed() ? 320 : getScreenHeight() - 168)
                    && mouseY <= (GameFrame.isFixed() ? 337 : getScreenHeight() - (168 + 17))) {
                Client.instance.menuActionName[0] = "Dismiss";
                Client.instance.menuActionID[0] = 471;
            }
        }

        chatArea.channel.processChatModeActions(this, GameFrame.getScreenMode());

        if (anInt886 != anInt1026) {
            anInt1026 = anInt886;
        }

        if (anInt1315 != anInt1129) {
            anInt1129 = anInt1315;
        }

        anInt886 = 0;
        anInt1315 = 0;
        skillTabHoverChild = 0;
        if (tabArea.isHovering(this, GameFrame.getScreenMode()) && !tabArea.componentHidden()) {
            if (invOverlayInterfaceID != -1) {
                buildInterfaceMenu(tabArea.getxPos() + (GameFrame.getScreenMode() == ScreenMode.FIXED ? 31 : 40),
                        RSInterface.interfaceCache[invOverlayInterfaceID], super.mouseX,
                        tabArea.getyPos() + (GameFrame.getScreenMode() == ScreenMode.FIXED ? 36
                                : getScreenWidth() <= GameFrameConstants.smallTabs ? -6 : 30),
                        super.mouseY, 0);
            } else if (tabInterfaceIDs[tabID] != -1) {
                buildInterfaceMenu(tabArea.getxPos() + (GameFrame.getScreenMode() == ScreenMode.FIXED ? 31 : 40),
                        RSInterface.interfaceCache[tabInterfaceIDs[tabID]], super.mouseX,
                        tabArea.getyPos() + (GameFrame.getScreenMode() == ScreenMode.FIXED ? 36
                                : getScreenWidth() <= GameFrameConstants.smallTabs ? -6 : 30),
                        super.mouseY, 0);
            }
        }

        if (anInt886 != anInt1048) {
            // needDrawTabArea = true;
            tabAreaAltered = true;
            anInt1048 = anInt886;
        }

        if (anInt1315 != anInt1044) {
            // needDrawTabArea = true;
            tabAreaAltered = true;
            anInt1044 = anInt1315;
        }

        anInt886 = 0;
        anInt1315 = 0;
        if (super.mouseX > chatArea.getxPos() && super.mouseY > chatArea.getyPos()
                && super.mouseX < chatArea.getxPos() + 490 && super.mouseY < chatArea.getyPos() + 125) {
            if (backDialogID != -1) {
                buildInterfaceMenu(20, RSInterface.interfaceCache[backDialogID], super.mouseX, chatArea.getyPos() + 20,
                        super.mouseY, chatArea.getxPos());
            } else if (super.mouseY < chatArea.getyPos() + 125 && super.mouseX < chatArea.getxPos() + 490) {
                buildChatAreaMenu(super.mouseY - chatArea.getyPos());
            }
        }

        if (backDialogID != -1 && anInt886 != anInt1039) {
            setInputTaken(true);
            anInt1039 = anInt886;
        }

        if (backDialogID != -1 && anInt1315 != anInt1500) {
            setInputTaken(true);
            anInt1500 = anInt1315;
        }

        mapArea.processMinimapActions(this);
        boolean flag = false;

        while (!flag) {
            flag = true;

            for (int j = 0; j < menuActionRow - 1; j++) {
                if (menuActionID[j] < 1000 && menuActionID[j + 1] > 1000) {
                    String s = menuActionName[j];
                    menuActionName[j] = menuActionName[j + 1];
                    menuActionName[j + 1] = s;
                    int k = menuActionID[j];
                    menuActionID[j] = menuActionID[j + 1];
                    menuActionID[j + 1] = k;
                    k = menuActionCmd2[j];
                    menuActionCmd2[j] = menuActionCmd2[j + 1];
                    menuActionCmd2[j + 1] = k;
                    k = menuActionCmd3[j];
                    menuActionCmd3[j] = menuActionCmd3[j + 1];
                    menuActionCmd3[j + 1] = k;
                    k = menuActionCmd4[j];
                    menuActionCmd4[j] = menuActionCmd4[j + 1];
                    menuActionCmd4[j + 1] = k;
                    k = menuActionCmd1[j];
                    menuActionCmd1[j] = menuActionCmd1[j + 1];
                    menuActionCmd1[j + 1] = k;
                    flag = false;
                }
            }
        }
    }

    private boolean promptUserForInput(RSInterface rsinterface) {
        int id = rsinterface.contentType;
        int index = rsinterface.id - 79924;
        // System.out.println("pUFI: "+id+", "+index);
        System.out.println("HEREE");
        if (getAnInt900() == 2) {
            if (id == 201) {
                inputTaken = true;
                inputDialogState = 0;
                messagePromptRaised = true;
                promptInput = "";
                friendsListAction = 1;
                promptMessage = "Enter name of friend to add to list";
            }

            if (id == 202) {
                inputTaken = true;
                inputDialogState = 0;
                messagePromptRaised = true;
                promptInput = "";
                friendsListAction = 2;
                promptMessage = "Enter name of friend to delete from list";
            }
        }

        if (id == 205) {
            anInt1011 = 250;
            return true;
        }

        if (id == 501) {
            inputTaken = true;
            inputDialogState = 0;
            messagePromptRaised = true;
            promptInput = "";
            friendsListAction = 4;
            promptMessage = "Enter the name of a player to add to the list";
        }

        if (id == 502) {
            inputTaken = true;
            inputDialogState = 0;
            messagePromptRaised = true;
            promptInput = "";
            friendsListAction = 5;
            promptMessage = "Enter the name of a player to delete from the list";
        }
        if (id == 1321) {
            inputTaken = true;
            inputDialogState = 0;
            messagePromptRaised = true;
            promptInput = "";
            friendsListAction = 12;
            promptMessage = "Enter your " + Skills.SKILL_NAMES[index] + " level goal below.";
        }
        if (id == 1322) {
            inputTaken = true;
            inputDialogState = 0;
            messagePromptRaised = true;
            promptInput = "";
            friendsListAction = 13;
            promptMessage = "Enter your experience goal below.";
        }
        if (id == 1323) {
            if (Skills.goalData[Skills.selectedSkillId][0] == -1 && Skills.goalData[Skills.selectedSkillId][1] == -1
                    && Skills.goalData[Skills.selectedSkillId][2] == -1) {
                pushMessage("You do not have a goal to clear for that level.", 0, "");
            }
            if (Skills.selectedSkillId > -1) {
                Skills.goalData[Skills.selectedSkillId][0] = -1;
                Skills.goalData[Skills.selectedSkillId][1] = -1;
                Skills.goalData[Skills.selectedSkillId][2] = -1;
                saveGoals(myUsername);
            }
        } else if (id >= 5000 && id <= 5025) {
            getOut().putOpcode(223);
            index = id - 5000;
            getOut().putShort(index);
            return true;
        }

        if (id == 550) {
            if (RSInterface.interfaceCache[18135].message.startsWith("Join")) {
                inputTaken = true;
                inputDialogState = 0;
                messagePromptRaised = true;
                promptInput = "";
                friendsListAction = 6;
                promptMessage = "Enter the name of the chat you wish to join";
            } else {
                getOut().putOpcode(185);
                getOut().putInt(49627);
            }
        }

        if (id == 22222) {
            inputTaken = true;
            messagePromptRaised = true;
            amountOrNameInput = "";
            promptInput = "";
            inputDialogState = 0;
            interfaceButtonAction = 6199;
            promptMessage = "Enter a name for the clan chat.";
        }

        if (id == 677) {
            inputTaken = true;
            messagePromptRaised = true;
            amountOrNameInput = "";
            promptInput = "";
            inputDialogState = 0;
            interfaceButtonAction = 6200;
            promptMessage = "Enter name of the player you would like kicked.";
        }

        if (id >= 300 && id <= 313) {
            int k = (id - 300) / 2;
            int j1 = id & 1;
            int i2 = myAppearance[k];

            if (i2 != -1) {
                do {
                    if (j1 == 0 && --i2 < 0) {
                        i2 = IdentityKit.getLength() - 1;
                    }

                    if (j1 == 1 && ++i2 >= IdentityKit.getLength()) {
                        i2 = 0;
                    }
                } while (IdentityKit.cache[i2].isaBoolean662()
                        || IdentityKit.cache[i2].getAnInt657() != k + (isMale ? 0 : 7));

                myAppearance[k] = i2;
                aBoolean1031 = true;
            }
        }

        if (id >= 314 && id <= 323) {
            int l = (id - 314) / 2;
            int k1 = id & 1;
            int j2 = anIntArray990[l];

            if (k1 == 0 && --j2 < 0) {
                j2 = anIntArrayArray1003[l].length - 1;
            }

            if (k1 == 1 && ++j2 >= anIntArrayArray1003[l].length) {
                j2 = 0;
            }

            anIntArray990[l] = j2;
            aBoolean1031 = true;
        }

        if (id == 324 && !isMale) {
            isMale = true;
            method45();
        }

        if (id == 325 && isMale) {
            isMale = false;
            method45();
        }

        if (id == 326) {
            String s = " " + (isMale ? 0 : 1) + "";
            for (int i1 = 0; i1 < 7; i1++)
                s += " " + (myAppearance[i1]);
            for (int l1 = 0; l1 < 5; l1++)
                s += " " + (anIntArray990[l1]);
            getOut().putOpcode(11);
            getOut().putByte(s.substring(1).length() + 1);
            getOut().putString(s.substring(1));
            return true;
        }

        return false;
    }

    public void pushMessage(String chatMessage, int chatType, String chatName, String title, int color, int position,
                            List<Integer> rights) {
        if (chatType == 0 && dialogID != -1) {
            aString844 = chatMessage;
            super.clickMode3 = 0;
        }


        if (backDialogID == -1) {
            inputTaken = true;
        }

        for (int j = 499; j > 0; j--) {
            chatTypes[j] = chatTypes[j - 1];
            chatNames[j] = chatNames[j - 1];
            chatMessages[j] = chatMessages[j - 1];
            chatRights[j] = chatRights[j - 1];
            chatTitles[j] = chatTitles[j - 1];
            chatPosition[j] = chatPosition[j - 1];
            chatColor[j] = chatColor[j - 1];
        }

        if (chatType == 2 || chatType == 16 || chatType == 0) {
            chatMessage = RSFontSystem.handleOldSyntax(chatMessage);
        }

        chatTypes[0] = chatType;
        chatNames[0] = chatName;
        chatMessages[0] = chatMessage.trim();
        chatRights[0] = rights;
        chatTitles[0] = title;
        chatColor[0] = color;
        chatPosition[0] = position;
    }

    public void pushMessage(String chatMessage, int chatType, String chatName) {
        if (chatType == 0 && dialogID != -1) {
            aString844 = chatMessage;
            super.clickMode3 = 0;
        }

        if (backDialogID == -1) {
            inputTaken = true;
        }

        for (int j = 499; j > 0; j--) {
            chatTypes[j] = chatTypes[j - 1];
            chatNames[j] = chatNames[j - 1];
            chatMessages[j] = chatMessages[j - 1];
            chatRights[j] = chatRights[j - 1];
            chatTitles[j] = chatTitles[j - 1];
            chatPosition[j] = chatPosition[j - 1];
            chatColor[j] = chatColor[j - 1];
        }

        chatTypes[0] = chatType;
        chatNames[0] = chatName;
        chatMessages[0] = chatMessage.trim();
        chatRights[0] = new ArrayList<>();
        chatTitles[0] = "";
        chatColor[0] = 0;
        chatPosition[0] = 0;
    }

    @Override
    public void raiseWelcomeScreen() {
        welcomeScreenRaised = true;
    }

    private void resetAllImageProducers() {
        if (super.fullGameScreen != null) {
            return;
        }

        chatAreaIP = null;
        mapAreaIP = null;
        tabAreaIP = null;
        gameScreenIP = null;
        aRSImageProducer_1125 = null;
        aRSImageProducer_1107 = null;
        titleScreenIP = null;
        super.fullGameScreen = new RSImageProducer(765, 503, getGameComponent());
        welcomeScreenRaised = true;
    }

    private void resetImageProducers() {
        if (aRSImageProducer_1107 != null) {
            return;
        }
        super.fullGameScreen = null;
        chatAreaIP = null;
        mapAreaIP = null;
        tabAreaIP = null;
        gameScreenIP = null;
        aRSImageProducer_1125 = null;
        aRSImageProducer_1107 = new RSImageProducer(509, 171, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        titleScreenIP = new RSImageProducer(getScreenWidth(), getScreenHeight(), getGameComponent());
        DrawingArea.setAllPixelsToZero();
        new RSImageProducer(203, 238, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        new RSImageProducer(74, 94, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        if (titleStreamLoader != null) {
            drawLogo();
            loadTitleScreen();
        }
        welcomeScreenRaised = true;
    }

    public void resetImageProducers2() {
        if (chatAreaIP != null) {
            return;
        }
        nullLoader();
        super.fullGameScreen = null;
        aRSImageProducer_1107 = null;
        titleScreenIP = null;
        chatAreaIP = new RSImageProducer(516, 165, getGameComponent());
        mapAreaIP = new RSImageProducer(249, 168, getGameComponent());
        DrawingArea.setAllPixelsToZero();
        if (GameFrame.getScreenMode() == ScreenMode.FIXED) {
            cacheSprite[14].drawSprite(0, 0);
        }
        tabAreaIP = new RSImageProducer(250, 335, getGameComponent());
        gameScreenIP = new RSImageProducer(
                GameFrame.getScreenMode() == ScreenMode.FIXED && isGameFrameVisible() ? 512 : getScreenWidth(),
                GameFrame.getScreenMode() == ScreenMode.FIXED && isGameFrameVisible() ? 334 : getScreenHeight(),
                getGameComponent());
        DrawingArea.setAllPixelsToZero();
        aRSImageProducer_1125 = new RSImageProducer(249, 45, getGameComponent());
        welcomeScreenRaised = true;
    }

    public void resetLogout() {
        try {
            if (getConnection() != null) {
                getConnection().close();
            }
        } catch (Exception _ex) {
        }

        if (Configuration.SAVE_ACCOUNTS) {

            boolean updated = false;
            for (Account account : accountManager.getAccounts()) {
                if (account == null)
                    continue;
                if (account.getUsername().equalsIgnoreCase(myUsername)) {
                    account.setIDKHead(
                            myPlayer.equipment[0] < 1164 ? myAppearance[0] == 0 ? myHeadAndJaw[0] : myAppearance[0]
                                    : 0);

                    account.setHelmet(myPlayer.equipment[0] >= 1164 ? myPlayer.equipment[0] - 512 : 0);
                    account.setJaw(myHeadAndJaw[1]);
                    account.setGender(myPlayer.myGender);
                    account.setPassword(password);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                accountManager.addAccount(new Account(myUsername, password, myPlayer.myGender,
                        myPlayer.equipment[0] >= 1164 ? myPlayer.equipment[0] - 512 : 0,
                        myPlayer.equipment[0] < 1164 ? myAppearance[0] == 0 ? myHeadAndJaw[0] : myAppearance[0] : 0,
                        myAppearance[1] == 0 ? myHeadAndJaw[1] : myAppearance[1]), false);
            } else {
                accountManager.updateInterface();
            }

            accountManager.save();
        }
        alertHandler.alert = null;
        alertManager.setAlert(null);
        currentTarget = null;
        loggingIn = false;
        loginMessages = new String[]{""};
        consoleOpen = false;
        lootingBag = false;
        setConnection(null);
        loggedIn = false;
        setLoginScreenState(0);
        setLoginScreenCursorPos(0);
        unlickCaches();
        scene.initToNull();

        for (int i = 0; i < 4; i++) {
            collisionData[i].setDefault();
        }

        System.gc();
        stopMidi();
        currentSong = -1;
        nextSong = -1;
        prevSong = 0;
        if (!GameFrame.isFixed()) {
            updateScreen();
        }
        method58(10, 0, false, 0);
        updateGraphics(true);
        Save.settings(Client.getClient());
    }

    public void resetWorld(int stage) {
        if (stage == 0) {
            soundCount = 0;
            cameraOffsetX = 0;
            cameraOffsetY = 0;
            viewRotationOffset = 0;
            minimapRotation = 0;
            minimapZoom = 0;
            viewRotation = 0;
            minimapStatus = 0;
            loadingStage = 1;
        } else if (stage == 1) {
            aBoolean1080 = false;
        }
    }

    //60 is the texture number we dont have :)
    @Override
    public void run() {
        if (drawFlames) {
            drawFlames();
        } else {
            super.run(); // ill get you the proper libs after gonna do interface
        }
    }

    private void sendFrame248(int interfaceID, int sideInterfaceID) {
        if (backDialogID != -1) {
            backDialogID = -1;
            inputTaken = true;
        }
        if (inputDialogState != 0) {
            inputDialogState = 0;
            inputTaken = true;
        }
        openInterfaceID = interfaceID;
        invOverlayInterfaceID = sideInterfaceID;
        tabAreaAltered = true;
        aBoolean1149 = false;
    }

    private void sendFrame36(int id, int state) {
        settings[id] = state;

        if (variousSettings[id] != state) {
            variousSettings[id] = state;
            updateConfig(id);

            if (dialogID != -1) {
                inputTaken = true;
            }
        }
    }

    private void sendPacket(int packet) {
        if (packet == 103) {
            getOut().putOpcode(103);
            getOut().putByte(inputString.length() - 1);
            getOut().putString(inputString.substring(2));
            inputString = "";
            promptInput = "";
            interfaceButtonAction = 0;
        }

        if (packet == 1003) {
            getOut().putOpcode(103);
            inputString = "::" + inputString;
            getOut().putByte(inputString.length() - 1);
            getOut().putString(inputString.substring(2));
            inputString = "";
            promptInput = "";
            interfaceButtonAction = 0;
        }
    }

    private void setCameraPos(int j, int k, int l, int i1, int j1, int k1) {
        int l1 = 2048 - k & 0x7ff;
        int i2 = 2048 - j1 & 0x7ff;
        int j2 = 0;
        int k2 = 0;
        int l2 = j;
        if (l1 != 0) {
            int i3 = Model.SINE[l1];
            int k3 = Model.COSINE[l1];
            int i4 = k2 * k3 - l2 * i3 >> 16;
            l2 = k2 * i3 + l2 * k3 >> 16;
            k2 = i4;
        }
        if (i2 != 0) {
            int j3 = Model.SINE[i2];
            int l3 = Model.COSINE[i2];
            int j4 = l2 * j3 + j2 * l3 >> 16;
            l2 = l2 * l3 - j2 * j3 >> 16;
            j2 = j4;
        }
        xCameraPos = l - j2;
        zCameraPos = i1 - k2;
        yCameraPos = k1 - l2;
        yCameraCurve = k;
        xCameraCurve = j1;
    }

    private void setGameFrameVisible(boolean visible, GameFrame[] area) {
        for (GameFrame a : area) {
            a.setVisible(visible);
        }

        gameAreaWidth = GameFrame.getScreenMode() == ScreenMode.FIXED && visible ? 512 : getScreenWidth();
        gameAreaHeight = GameFrame.getScreenMode() == ScreenMode.FIXED && visible ? 334 : getScreenHeight();
        gameFrameVisible = visible;
    }

    public void setInputTaken(boolean inputTaken) {
        this.inputTaken = inputTaken;
    }

    public void setInterfaceText(String str, int i) {
        try {
            RSInterface _interface = RSInterface.interfaceCache[i];
            _interface.message = str;

            // if (_interface.parentID == tabInterfaceIDs[tabID])
            // {
            // needDrawTabArea = true;
            // }

            if (_interface.type == 9) {
                _interface.tooltipBoxText = str;
            }

            if (_interface.secondaryText != null && _interface.secondaryText.length() > 0) {
                _interface.secondaryText = "";
            }
        } catch (Exception exception) {
            exception.getStackTrace();
            // System.out.println(i);
        }
    }

    private String setMessage(int skillLevel) {
        if (skillLevel == 26) {
            long totalXp = 0;
            for (int i = 0; i < currentExp.length; i++) {
                totalXp += currentExp[i];
            }
            return "Total XP: " + String.format("%, d", totalXp) + "";
        }
        String[] getToolTipText = new String[6];
        String toolTiptext = "";
        int[] getSkillIds = {0, 3, 14, 2, 16, 13, 1, 15, 10, 4, 17, 7, 5, 12, 11, 6, 9, 8, 20, 18, 19, 21, 22, 23, 24,
                25};
        int init = Skills.goalData[getSkillIds[skillLevel]][0];
        int goal = Skills.goalData[getSkillIds[skillLevel]][1];
        int stat = getSkillIds[skillLevel];
        int currentLevel = currentStats[stat];
        int maxLevel = maxStats[stat];
        if (stat == 3 || stat == 5) {
            currentLevel /= 10;
            maxLevel /= 10;
        }
        getToolTipText[0] = (Skills.SKILL_NAMES[skillLevel] + ": " + currentLevel + "/" + maxLevel + "\\n");
        getToolTipText[1] = ("Current Exp: " + (maxLevel < 99 ? "" : "")
                + String.format("%, d", currentExp[getSkillIds[skillLevel]]) + "\\n");
        getToolTipText[2] = ("Next level: " + String.format("%, d",
                PlayerHandler.getXPForLevel(maxLevel + 1) - currentExp[getSkillIds[skillLevel]]));
        toolTiptext = getToolTipText[0] + getToolTipText[1];
        boolean onNewLine = false;
        if (maxLevel < 99) {
            toolTiptext += getToolTipText[2]; // + getToolTipText[3];
            onNewLine = true;
        }
        if ((currentExp[getSkillIds[skillLevel]] < 1000000000) && init > -1 && goal > -1) {
            getToolTipText[4] = ((onNewLine ? "\\n" : "") + Skills.goalType + ""
                    + (Skills.goalType.endsWith("Level: ") ? Integer.valueOf(PlayerHandler.getLevelForXP(goal))
                    : String.format("%,d", goal))
                    + "\\n");
            int remainder = goal - currentExp[getSkillIds[skillLevel]] - (Skills.goalType.endsWith("Level: ") ? 1 : 0);
            if (remainder < 0) {
                remainder = 0;
            }
            getToolTipText[5] = ("Remainder: " + String.format("%,d", remainder));
            Skills.goalData[getSkillIds[skillLevel]][2] = (int) (((currentExp[getSkillIds[skillLevel]] - init)
                    / (double) (goal - init)) * 100);
            if (Skills.goalData[getSkillIds[skillLevel]][2] > 100) {
                Skills.goalData[getSkillIds[skillLevel]][2] = 100;
            }
            toolTiptext += getToolTipText[4] + getToolTipText[5];
        }
        return toolTiptext;
    }

    public void setNorth() {
        cameraOffsetX = 0;
        cameraOffsetY = 0;
        viewRotationOffset = 0;
        viewRotation = 0;
        minimapRotation = 0;
        minimapZoom = 0;
    }

    private void spawnGroundItem(int i, int j) {
        Deque class19 = groundArray[plane][i][j];
        if (class19 == null) {
            scene.method295(plane, i, j);
            return;
        }
        int k = 0xfa0a1f01;
        Object obj = null;
        for (Item item = (Item) class19.reverseGetFirst(); item != null; item = (Item) class19.reverseGetNext()) {
            ItemDefinition itemDef = ItemDefinition.get(item.id);
            int l = itemDef.value;
            if (itemDef.stackable) {
                l *= item.amount + 1;
            }
            if (l > k) {
                k = l;
                obj = item;
            }
        }

        class19.insertTail((Node) obj);
        Object obj1 = null;
        Object obj2 = null;
        for (Item class30_sub2_sub4_sub2_1 = (Item) class19
                .reverseGetFirst(); class30_sub2_sub4_sub2_1 != null; class30_sub2_sub4_sub2_1 = (Item) class19
                .reverseGetNext()) {
            if (class30_sub2_sub4_sub2_1.id != ((Item) obj).id && obj1 == null) {
                obj1 = class30_sub2_sub4_sub2_1;
            }
            if (class30_sub2_sub4_sub2_1.id != ((Item) obj).id && class30_sub2_sub4_sub2_1.id != ((Item) obj1).id
                    && obj2 == null) {
                obj2 = class30_sub2_sub4_sub2_1;
            }
        }

        int i1 = i + (j << 7) + 0x60000000;
        scene.method281(i, i1, (Animable) obj1, method42(plane, j * 128 + 64, i * 128 + 64), (Animable) obj2,
                (Animable) obj, plane, j);
    }

    @Override
    public void startRunnable(Runnable runnable, int i) {
        if (i > 10) {
            i = 10;
        }
        if (Signlink.mainapp != null) {
            Signlink.startthread(runnable, i);
        } else {
            super.startRunnable(runnable, i);
        }
    }

    public void setLoadingPercentage(int loadingPercentage) {
        this.loadingPercentage = loadingPercentage;
    }

    void processLoadingScreen() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoading) {
                    try {

                        if (!CacheDownloader.UPDATING) {
                            loadingPercentage += 2;
                            if (loadingPercentage > 100) {
                                loadingPercentage = 100;
                                if (isLoading) {
                                    // loadingPercentage = 0;
                                }
                            }
                        }

                        displayLoadingScreen();

                        // if (onDemandFetcher != null) {
                        // processOnDemandQueue();
                        // }

                        Thread.sleep(20);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setPriority(10);
        t.start();
    }


    private int currencyImageAmount = 13;
    private Sprite[] currencyImage = new Sprite[currencyImageAmount];
    private int boxImageAmount = 6;
    private Sprite[] boxImage = new Sprite[boxImageAmount];

    @Override
    void startUp() {
        // ClientUpdater updater = new ClientUpdater(this);

        // if(updater.checkVersion())
        // return;
        GeneratedValues.createValue();
        isLoading = true;
        super.resetGraphic();
        processLoadingScreen();
        RICH_PRESENCE.initiate();
        if (Signlink.sunjava) {
            super.minDelay = 5;
        }
        getDocumentBaseHost();

        /** CACHE DOWNLOADS **/
        for (int i = 0; i < loadingImages.length; i++) {
            File dir = new File(Signlink.getCacheDirectory() + i + ".png");
            if (!dir.exists()) {
               boolean downloadedImage = HttpDownloadUtility
                        .downloadFile(CacheDownloader.URL_TO_LOADING_IMAGES + i + ".png", Signlink.getCacheDirectory());
                if (!downloadedImage) {
                    HttpDownloadUtility.downloadFile("https://www.dropbox.com/s/nwioykg2bbt7xru/0.png?dl=1",
                            Signlink.getCacheDirectory());
                    HttpDownloadUtility.downloadFile("https://www.dropbox.com/s/50xgxfayxyukd08/1.png?dl=1",
                            Signlink.getCacheDirectory());
                    HttpDownloadUtility.downloadFile("https://www.dropbox.com/s/3ux32jqjn7k8136/2.png?dl=1",
                            Signlink.getCacheDirectory());
                    HttpDownloadUtility.downloadFile("https://www.dropbox.com/s/185w2zrb0m7xql4/3.png?dl=1",
                            Signlink.getCacheDirectory());
                }
            }
            try {
                loadingImages[i] = ImageIO.read(dir);
            } catch (Exception e) {
            }
        }

        if (CacheDownloader.updatedCache()) {
            if (super.gameFrame != null) {
                super.gameFrame.setClientIcon();
            }
            setLoadingPercentage(0);
        }

        if (Signlink.cache_dat != null) {
            for (int i = 0; i < Client.CACHE_INDEX_COUNT; i++) {
                decompressors[i] = new Decompressor(Signlink.cache_dat, Signlink.cache_idx[i], i + 1);
            }
        }


        String cacheLoc = Signlink.getCacheDirectory() + "/Shop/"; // "./cache/";
        File cacheDir = new File(cacheLoc);
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }


        try {
            //// drawSmoothLoading(10, "Getting archives...");
            titleStreamLoader = getArchive(1, "title screen", "title", expectedCRCs[1], 25);
            smallText = new TextDrawingArea(false, "p11_full", titleStreamLoader);
            normalText = new TextDrawingArea(false, "p12_full", titleStreamLoader);
            smallHit = new TextDrawingArea(false, "hit_full", titleStreamLoader);
            bigHit = new TextDrawingArea(true, "critical_full", titleStreamLoader);
            chatTextDrawingArea = new TextDrawingArea(false, "b12_full", titleStreamLoader);
            aTextDrawingArea_1273 = new TextDrawingArea(true, "q8_full", titleStreamLoader);
            newSmallFont = new RSFontSystem(false, "p11_full", titleStreamLoader);
            newRegularFont = new RSFontSystem(false, "p12_full", titleStreamLoader);
            newBoldFont = new RSFontSystem(false, "b12_full", titleStreamLoader);
            newFancyFont = new RSFontSystem(true, "q8_full", titleStreamLoader);
            drawLogo();
            loadTitleScreen();
            aClass3_Sub7_Sub1_1493 = method407(instance);
            aClass25_1948 = new Class25(22050, anInt197);
            Archive streamLoader = getArchive(2, "config", "config", expectedCRCs[2], 30);
            Archive streamLoader_1 = getArchive(3, "interface", "interface", expectedCRCs[3], 35);
            Archive mediaArchive = getArchive(4, "2d graphics", "media", expectedCRCs[4], 40);
            Archive streamLoader_3 = getArchive(6, "textures", "textures", expectedCRCs[6], 45);
            Archive streamLoader_4 = getArchive(7, "chat system", "wordenc", expectedCRCs[7], 50);
            Archive soundArchive = getArchive(8, "sound effects", "sounds", expectedCRCs[8], 55);
            tileFlags = new byte[4][104][104];
            tileHeights = new int[4][105][105];
            scene = new WorldController(tileHeights);

            for (int j = 0; j < 4; j++) {
                collisionData[j] = new CollisionData();
            }

            miniMapRegions = new Sprite(512, 512);
            Archive streamLoader_6 = getArchive(5, "update list", "versionlist", expectedCRCs[5], 60);
            //// drawSmoothLoading(10, "Unpacking archives..");
            onDemandFetcher = new OnDemandFetcher();
            onDemandFetcher.start(streamLoader_6, this);
            Model.method459(onDemandFetcher.getFileCount(0), onDemandFetcher);
            //// drawSmoothLoading(20, "Unpacked archives!");
            constructMusic();
            //// drawSmoothLoading(30, "Unpacking media..");
            mapIcon = new Sprite(mediaArchive, "mapfunction", 5);
            SpriteLoader.loadSprites();
            cacheSprite = SpriteLoader.sprites;
            SpriteLoader.sprites = null;
            //// drawSmoothLoading(40, "Unpacked media!");
            mapBack = new Background(mediaArchive, "mapback", 0);

            mapEdge = new Sprite(mediaArchive, "mapedge", 0);
            mapEdge.method345();

            try {
                for (int k3 = 0; k3 < 100; k3++) {
                    mapScenes[k3] = new Background(mediaArchive, "mapscene", k3);
                }
            } catch (Exception _ex) {
            }

            try {
                for (int l3 = 0; l3 < 100; l3++) {
                    if (l3 < 75) {
                        mapFunctions[l3] = new Sprite(mediaArchive, "mapfunction", l3);
                    } else {
                        mapFunctions[l3] = new Sprite(mediaArchive, "mapfunctions2", l3 - 76);
                    }
                }
            } catch (Exception _ex) {
            }

            try {
                for (int j4 = 0; j4 <= 8; j4++) {
                    headIcons[j4] = new Sprite(mediaArchive, "headicons_prayer", j4);
                }

                for (int j6 = 9; j6 < 18; j6++) {
                    headIcons[j6] = Client.cacheSprite[j6 + 357 - 9];
                }

                for (int j45 = 0; j45 < 7; j45++) {
                    skullIcons[j45] = new Sprite(mediaArchive, "headicons_pk", j45);
                }
            } catch (Exception _ex) {
            }

            mapFlag = new Sprite(mediaArchive, "mapmarker", 0);
            mapMarker = new Sprite(mediaArchive, "mapmarker", 1);

            for (int k4 = 0; k4 < 8; k4++) {
                crosses[k4] = new Sprite(mediaArchive, "cross", k4);
            }

            mapDotItem = new Sprite(mediaArchive, "mapdots", 0);
            mapDotNPC = new Sprite(mediaArchive, "mapdots", 1);
            mapDotPlayer = new Sprite(mediaArchive, "mapdots", 2);
            mapDotFriend = new Sprite(mediaArchive, "mapdots", 3);
            mapDotTeam = new Sprite(mediaArchive, "mapdots", 4);
            alertBack = new Sprite("alertback");
            alertBorder = new Sprite("alertborder");
            alertBorderH = new Sprite("alertborderh");
            customMinimapIcons.add(new CustomMinimapIcon(2378, 4014, new Sprite(mediaArchive, "mapfunction", 12)));
            customMinimapIcons.add(new CustomMinimapIcon(2378, 4028, new Sprite(mediaArchive, "mapfunction", 12)));
            customMinimapIcons.add(new CustomMinimapIcon(3663, 2981, new Sprite(mediaArchive, "mapfunction", 5)));
            customMinimapIcons.add(new CustomMinimapIcon(3674, 2970, new Sprite(mediaArchive, "mapfunction", 51)));
            customMinimapIcons.add(new CustomMinimapIcon(3676, 2989, new Sprite(mediaArchive, "mapfunction", 54)));
            customMinimapIcons.add(new CustomMinimapIcon(3685, 2977, new Sprite(mediaArchive, "mapfunction", 0)));
            // customMinimapIcons.add(new CustomMinimapIcon(3659, 2989, new
            // Sprite(mediaArchive, "mapfunction", 72))); event icon
            customMinimapIcons.add(new CustomMinimapIcon(2574, 3880, new Sprite(mediaArchive, "mapfunction", 5)));
            customMinimapIcons.add(new CustomMinimapIcon(2550, 3858, new Sprite(mediaArchive, "mapfunction", 5)));
            customMinimapIcons.add(new CustomMinimapIcon(2557, 3886, new Sprite(mediaArchive, "mapfunction", 34)));

            /*
             * media.jag / 1615310064 dump: 0 = summoning wolf icon 1 = spirit shard icon 2
             * = grand exchange icon 3 = herblore decanter icon 4 = alternative wood icon 5
             * = warrior guild icon http://i.imgur.com/jcOC1tv.png 6 = pile of rock 7 = raw
             * meat icon 8 = runecrafting icon 16 = pet icon 17 = summoning obelisk icon 18
             * = yellow flower icon
             */

            /*
             * media.jag / MAPfunction.dat dump: 0 = General Store 1 = Sword Icon 2 = Magic
             * (fire spell) Icon 3 = Axe Icon 4 = Med helm Icon 5 = Bank ($) icon 6 = Quest
             * Icon 7 = Necklace icon 8 = Mining Icon 9 = Smithing (Furnace) Icon 10 =
             * Smithing (Anvil) Icon 11 = Training (dummy) Icon 12 =
             * Dungeon/Danger/exclamation 13 = Staff (Magic) Icon 14 = grey Chest/shirt icon
             * 15 = grey Leg/pant icon 16 = Dagger icon 17 = Arrow Icon 18 = Shield Icon 19
             * = Prayer Icon 20 = Herb Icon 21 = Ring Icon 22 = Gem Icon 23 = Chisel Icon 24
             * = Light (candle) Icon 25 = Fishing Pole Icon 26 = Fishing Fish Icon 27 =
             * Green Shirt Icon 28 = Apothecary Icon / purple vial 29 = Purple Silk icon 30
             * = A bump? http://i.imgur.com/oYSmFbK.png 31 = Beer 32 = Hammer/mace icon?
             * http://i.imgur.com/IwBCzZO.png 33 = Brown shirt icon 34 = Tree icon 35 =
             * Spinning Icon 36 = Tanning Icon 37 = Fork and Knife icon 38 = Red Quest
             * symbol/Minigame 39 = Water Icon 40 = Cooking (pot) icon 41 = Skirt icon 42 =
             * Pottery Icon 43 = Mill icon 44 = Yellow Mining Icon 45 = LITERAL Chain mail
             * icon 46 = Spice Icon 47 = Fur stall icon 48 = Bowl of shit/blast furnace
             * http://i.imgur.com/nTpX7O2.png 49 = Agility Icon 50 = Green Apple Icon 51 =
             * Slayer Icon 52 = Sissors Icon 53 = Spade Icon 54 = Face Icon 55 = Blue
             * questionmark/guide icon 56 = Shortcut/travel icon 57 = Purple Quest/star Icon
             * 58 = Pot Icon 59 = Weaving icon 60 = Bucket/Pot icon/Churn 61 = Butter
             * churning icon 62 = Green water icon 63 = Hunter paw icon 64 = House portal
             * icon 65 = Fur icon 66 = "i" icon 67 = Real estate icon 68 = Sawmill icon 69 =
             * hammer and rock icon http://i.imgur.com/qINevEx.png 70 = Agility shortcut
             * icon 71 = some kind of tower icon http://i.imgur.com/EubhvvJ.png 72 =
             * Present/event icon 73 = Sandpit icon 74 = Green quest icon 75 = 2 Steel bar
             * icon
             */
            mapDotClan = cacheSprite[398];
            vengBar = cacheSprite[297];

            for (int j3 = 0; j3 < 12; j3++) {
                scrollPart[j3] = new Sprite(mediaArchive, "scrollpart", j3);
            }

            for (int id = 0; id < 6; id++) {
                scrollBar[id] = new Sprite(mediaArchive, "scrollbar", id);
            }

            for (int l4 = 0; l4 < modIcons.length; l4++) {
                modIcons[l4] = cacheSprite[827 + l4];
            }
            if (cacheSprite.length >= 1510) {
                modIcons[15] = cacheSprite[1509];
                modIcons[16] = cacheSprite[1508];
                modIcons[17] = cacheSprite[1541];
                modIcons[18] = cacheSprite[1602];
                modIcons[19] = cacheSprite[1658];
            }


            multiOverlay = cacheSprite[1025];
            XPOverlay = cacheSprite[1025];

            Sprite sprite = new Sprite(mediaArchive, "screenframe", 0);
            leftFrame = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(mediaArchive, "screenframe", 1);
            topFrame = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(mediaArchive, "screenframe", 2);
            rightFrame = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            sprite = new Sprite(mediaArchive, "mapedge", 0);
            new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
            sprite.method346(0, 0);
            compass = new Sprite(mediaArchive, "compass", 0);
            clientId = Signlink.uid;

            //// drawSmoothLoading(50, "Unpacking textures..");
            Rasterizer.method368(streamLoader_3);
            Rasterizer.method372(0.80000000000000004D);
            Rasterizer.initiateRequestBuffers();
            //// drawSmoothLoading(60, "Unpacked textures!");
            FrameReader.initialise(onDemandFetcher.getAnimCount());
            Animation.unpackConfig(streamLoader);
            preloadModels();
            preloadModels2();
            //// drawSmoothLoading(70, "Unpacking config..");
            ObjectDefinition.unpackConfig(streamLoader);
            Flo.unpackConfig(streamLoader);
            FloorDefinitionOSRS.unpackConfig(streamLoader);
            OverLayFlo317.load(streamLoader);
            ItemDefinition.unpackConfig(streamLoader);
            MobDefinition.load(streamLoader);
            IdentityKit.unpackConfig(streamLoader);
            SpotAnimDefinition.load(streamLoader);
            Varp.load(streamLoader);
            VarBit.unpackConfig(streamLoader);
            ItemDefinition.isMembers = isMembers;
            //// drawSmoothLoading(80, "Unpacked config!");
            // ItemDefinition.dumpItemModelsForId(13653);
            // onDemandFetcher.dump();

            for (int i : Configuration.REPACK_INDICIES)
                repackCacheIndex(i);
            if (!isLowDetail()) {
                //// drawSmoothLoading(85, "Unpacking sounds..");
                byte[] data = soundArchive.get("sounds.dat");
                ByteBuffer buffer = new ByteBuffer(data);
                Sound.unpack(buffer);
                //// drawSmoothLoading(90, "Unpacked sounds!");

            }

            //// drawSmoothLoading(95, "Unpacking interfaces");
            TextDrawingArea[] aclass30_sub2_sub1_sub4s = {smallText, normalText, chatTextDrawingArea,
                    aTextDrawingArea_1273};

            try {
                RSFontSystem[] newFonts = {newSmallFont, newRegularFont, newBoldFont};
                RSInterface.unpack(streamLoader_1, aclass30_sub2_sub1_sub4s, mediaArchive, newFonts);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //// drawSmoothLoading(100, "Unpacked interfaces!");

            try {
                for (int j6 = 0; j6 < 33; j6++) {
                    int k6 = 999;
                    int i7 = 0;

                    for (int k7 = 0; k7 < 34; k7++) {
                        if (mapBack.imgPixels[k7 + j6 * mapBack.imgWidth] == 0) {
                            if (k6 == 999) {
                                k6 = k7;
                            }

                            continue;
                        }

                        if (k6 == 999) {
                            continue;
                        }

                        i7 = k7;
                        break;
                    }

                    compassArray1[j6] = k6;
                    compassArray2[j6] = i7 - k6;
                }

                for (int l6 = 5; l6 < 156; l6++) {
                    int j7 = 999;
                    int l7 = 0;

                    for (int j8 = 20; j8 < 172; j8++) {
                        if (mapBack.imgPixels[j8 + l6 * mapBack.imgWidth] == 0 && (j8 > 34 || l6 > 34)) {
                            if (j7 == 999) {
                                j7 = j8;
                            }

                            continue;
                        }

                        if (j7 == 999) {
                            continue;
                        }

                        l7 = j8;
                        break;
                    }

                    mapImagePixelCutLeft[l6 - 5] = j7 - 20;
                    mapImagePixelCutRight[l6 - 5] = l7 - j7;

                    if (mapImagePixelCutRight[l6 - 5] == -20) {
                        mapImagePixelCutRight[l6 - 5] = 152;
                    }
                }
            } catch (Exception _ex) {
            }
            ItemStats.readDefinitions();
            updateGameArea();
            Censor.loadConfig(streamLoader_4);
            mouseDetection = new MouseDetection(this);
            startRunnable(mouseDetection, 10);
            Animable_Sub5.clientInstance = this;
            ObjectDefinition.clientInstance = this;
            MobDefinition.clientInstance = this;
            Load.settings(Client.getClient());
            try {
                serial = CreateUID.generateUID();
            } catch (Exception e) {
            }
            try {
                hash = ClassCheck.generate();
            } catch (Throwable e1) {
                e1.printStackTrace();
            }


            for (int i = 0; i < currencyImageAmount; i++) {
                currencyImage[i] = new Sprite("/Shop/currency" + i);
            }
            for (int i = 0; i < boxImageAmount; i++) {
                boxImage[i] = new Sprite("/Shop/box" + i);
            }

            // loadPlayerData();
            isLoading = false;
            if (Configuration.NEW_CURSORS) {
                super.setCursor(CursorData.CURSOR_0);
            }
            return;
        } catch (Exception exception) {
            isLoading = false;
            exception.printStackTrace();
            Signlink.reportError("loaderror " + anInt1079);
        }



        loadingError = true;
    }

    public void dumpArchive(int archive) {
        int[] ids = new int[]{};
        for (int i : ids) {
            try {
                byte abyte[] = decompressors[archive].decompress(onDemandFetcher.getModelIndex(i));
                File map = new File(
                        Signlink.getCacheDirectory() + "/itemdata/" + onDemandFetcher.getModelIndex(i) + ".gz");
                FileOutputStream fos = new FileOutputStream(map);
                fos.write(abyte);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void stopMidi() {
        // Signlink.midifade = 0;
        // Signlink.midi = "stop";
    }

    private void tabToReplyPm() {
        String name = null;

        for (int k = 0; k < 100; k++) {
            if (chatMessages[k] == null) {
                continue;
            }

            int l = chatTypes[k];

            if (l == 3 || l == 7) {
                name = chatNames[k];
                break;
            }
        }

        if (name == null) {
            pushMessage("You haven't received any messages to which you can reply.", 0, "");
            return;
        }

        if (name.indexOf("@") == 0) {
            int prefixSubstring = getPrefixSubstringLength(name);
            name = name.substring(prefixSubstring);
        }
        if (name.indexOf("@") == 0) {
            int prefixSubstring = getPrefixSubstringLength(name);
            name = name.substring(prefixSubstring);
        }

        long nameAsLong = TextClass.longForName(name.trim());

        if (nameAsLong != -1) {
            inputTaken = true;
            inputDialogState = 0;
            messagePromptRaised = true;
            promptInput = "";
            friendsListAction = 3;
            aLong953 = nameAsLong;
            promptMessage = "Enter message to send to " + TextClass.fixName(TextClass.nameForLong(nameAsLong));
        }
    }

    public void updateScreen() {
        gameAreaWidth = GameFrame.isFixed() ? 512 : getScreenWidth();
        gameAreaHeight = GameFrame.isFixed() ? 334 : getScreenHeight();
        if (gameScreenIP == null || gameScreenIP.anInt316 != gameAreaWidth || gameScreenIP.anInt317 != gameAreaHeight) {
            gameScreenIP = new RSImageProducer(gameAreaWidth, gameAreaHeight, getGameComponent());
        }
        updateGameArea();
        updateGraphics(false);
    }

    public void setResizing(boolean resizing) {
        if (resizing) {
            this.resizing = true;
            (loggedIn ? gameScreenIP : titleScreenIP).initDrawingArea();
        } else {
            this.resizing = false;
            (loggedIn ? gameScreenIP : titleScreenIP).drawGraphics(canvas.getGraphics(), GameFrame.isFixed() ? 4 : gameScreenDrawX, gameScreenDrawY
            );
        }
    }

    public void toggleSize(ScreenMode mode) {
        if (isApplet) {
            GameFrame.setScreenMode(mode);
            if (mode == ScreenMode.FIXED) {
                sendFrame36(652, 0);
                gameScreenDrawX = 4;
                gameScreenDrawY = 4;
            } else if (mode == ScreenMode.RESIZABLE) {
                sendFrame36(652, 2);
                gameScreenDrawX = 0;
                gameScreenDrawY = 0;
            } else {
                sendFrame36(652, 1);
                gameScreenDrawX = 0;
                gameScreenDrawY = 0;
            }
            return;
        }
        if (GameFrame.getScreenMode() != mode) {
            setResizing(true);
            super.setClickMode2(0);
            if (mode == ScreenMode.FIXED) {
                GameFrame.setScreenMode(mode);
                gameScreenDrawX = 4;
                gameScreenDrawY = 4;
                forceWidth = REGULAR_WIDTH;
                forceHeight = REGULAR_HEIGHT;
                clientWidth = REGULAR_WIDTH;
                clientHeight = REGULAR_HEIGHT;
                sendFrame36(652, 0);
                recreateClientFrame(false, 765, 503, false, 1, false);
                welcomeScreenRaised = true;
                clientZoom = 0;
            } else if (mode == ScreenMode.RESIZABLE) {
                GameFrame.setScreenMode(mode);
                gameScreenDrawX = 0;
                gameScreenDrawY = 0;
                forceWidth = forceHeight = -1;
                sendFrame36(652, 2);
                recreateClientFrame(false, RESIZABLE_WIDTH, RESIZABLE_HEIGHT, true, 0, true);
                clientZoom = 480;
            } else {
                GameFrame.setScreenMode(ScreenMode.FULLSCREEN);
                gameScreenDrawX = 0;
                gameScreenDrawY = 0;
                sendFrame36(652, 1);
                Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                clientWidth = size.width;
                clientHeight = size.height;
                recreateClientFrame(true, size.width, size.height, false, 0, true);
                forceWidth = forceHeight = -1;
                myWidth = size.width;
                myHeight = size.height;
                clientZoom = 480;
            }
            if (mode == ScreenMode.FIXED) {
                inputTaken = true;
            }
            if (!loggedIn) {
                resetImageProducers();
            } else {
                resetImageProducers2();
            }
            chatArea.setHideComponent(false);
            tabArea.setHideComponent(false);
            updateScreen();


            setResizing(false);
        }
    }

    //
    private void unlickCaches() {
        ObjectDefinition.baseModels.unlinkAll();
        ObjectDefinition.mruNodes2.unlinkAll();
        MobDefinition.mruNodes.unlinkAll();
        ItemDefinition.mruNodes2.unlinkAll();
        ItemDefinition.mruNodes1.unlinkAll();
        Player.mruNodes.unlinkAll();
        SpotAnimDefinition.list.unlinkAll();
    }

    public void updateConfig(int configId) {
        try {
            int j = 0;
            if (configId < Varp.getCache().length)
                j = Varp.getCache()[configId].getAnInt709();

            if (j == 0)
                return;

            int k = variousSettings[configId];

            if (j == 1) {
                if (k == 1) {
                    Rasterizer.method372(0.90000000000000002D);
                } else if (k == 2) {
                    Rasterizer.method372(0.80000000000000004D);
                } else if (k == 3) {
                    Rasterizer.method372(0.69999999999999996D);
                } else if (k == 4) {
                    Rasterizer.method372(0.59999999999999998D);
                }

                ItemDefinition.mruNodes1.unlinkAll();
                ItemDefinition.spriteCacheEffectTimers.clear();
                welcomeScreenRaised = true;
            } else if (j == 3) {
                int volume = 0;

                if (k == 0) {
                    volume = 255;
                } else if (k == 1) {
                    volume = 192;
                } else if (k == 2) {
                    volume = 128;
                } else if (k == 3) {
                    volume = 64;
                } else if (k == 4) {
                    volume = 0;
                }

                if (volume != musicVolume) {
                    if (musicVolume != 0 || currentSong == -1) {
                        if (volume != 0) {
                            setVolume(volume);
                        } else {
                            method55(false);
                            prevSong = 0;
                        }
                    } else {
                        method56(volume, false, currentSong);
                        prevSong = 0;
                    }

                    musicVolume = volume;
                }
            } else if (j == 4) {
                if (k == 0) {
                    soundEffectVolume = 127;
                } else if (k == 1) {
                    soundEffectVolume = 96;
                } else if (k == 2) {
                    soundEffectVolume = 64;
                } else if (k == 3) {
                    soundEffectVolume = 32;
                } else if (k == 4) {
                    soundEffectVolume = 0;
                }
            } else if (j == 5) {
                anInt1253 = k;
            } else if (j == 6) {
                anInt1249 = k;
            } else if (j == 7) {
                running = !running;
                final boolean run = running;
                mapArea.run.setOrbState(run);
            } else if (j == 8) {
                splitPrivateChat = k;
                inputTaken = true;
            } else if (j == 9) {
                anInt913 = k;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateEntities() {
        try {
            int anInt974 = 0;
            for (int j = -1; j < playerCount + npcCount; j++) {
                Object obj;
                if (j == -1) {
                    obj = myPlayer;
                } else if (j < playerCount) {
                    obj = playerArray[playerIndices[j]];
                } else {
                    obj = npcArray[npcIndices[j - playerCount]];
                }
                if (obj == null || !((Entity) obj).isVisible()) {
                    continue;
                }
                if (obj instanceof NPC) {
                    MobDefinition entityDef = ((NPC) obj).definitionOverride;
                    npcScreenPos((Entity) obj, ((Entity) obj).height + 5);
                    if (spriteDrawX > -1) {

                        // draw sprites on npc
                        if (entityDef.id == 6537) {
                            cacheSprite[607].drawSprite(spriteDrawX - 12, spriteDrawY - 30);
                        }
                        if (entityDef.id == 8000 || entityDef.id == 8002) {
                            cacheSprite[134].drawSprite(spriteDrawX - 12, spriteDrawY - 30);
                        }
                        if (entityDef.id == 13738) {
                            cacheSprite[519].drawSprite(spriteDrawX - 12, spriteDrawY - 30);

                        }
                        if (entityDef.id == 1086) {
                            cacheSprite[638].drawSprite(spriteDrawX - 12, spriteDrawY - 40);

                        }
                        if (entityDef.id == 1085) {
                            cacheSprite[641].drawSprite(spriteDrawX - 12, spriteDrawY - 40);

                        }
                        if (entityDef.id == 1084) {
                            cacheSprite[625].drawSprite(spriteDrawX - 12, spriteDrawY - 40);

                        }
                        if (entityDef.id == 3777) {
                            cacheSprite[857].drawSprite(spriteDrawX - 4, spriteDrawY - 16);

                        }
                        if (entityDef.id == 585) {
                            cacheSprite[915].drawSprite(spriteDrawX - 4, spriteDrawY - 32);

                        }
                        if (entityDef.id == 688) {
                            cacheSprite[916].drawSprite(spriteDrawX - 4, spriteDrawY - 32);

                        }
                        if (entityDef.id == 125) {
                            cacheSprite[917].drawSprite(spriteDrawX - 4, spriteDrawY - 32);

                        }
                        if (entityDef.id == 1821) {
                            cacheSprite[869].drawSprite(spriteDrawX - 4, spriteDrawY - 5);

                        }
                        if (entityDef.id == 925 || entityDef.id == 9000 || entityDef.id == 1988) {
                            cacheSprite[853].drawSprite(spriteDrawX - 4, spriteDrawY - 16);

                        }
                        if (entityDef.id == 198) {
                            cacheSprite[856].drawSprite(spriteDrawX - 4, spriteDrawY - 16);

                        }
                        if (entityDef.id == 3306) {
                            cacheSprite[876].drawSprite(spriteDrawX - 4, spriteDrawY - 16);

                        }
                        if (entityDef.id == 4651) {
                            cacheSprite[868].drawSprite(spriteDrawX - 4, spriteDrawY - 16);

                        }
                        if (entityDef.id == 6692) {
                            cacheSprite[364].drawSprite(spriteDrawX - 12, spriteDrawY - 30);

                        }
                        if (entityDef.id == 3112) {
                            cacheSprite[1229].drawSprite(spriteDrawX - 12, spriteDrawY - 30);

                        }

                        if (entityDef.id == 605) {
                            cacheSprite[866].drawSprite(spriteDrawX - 4, spriteDrawY - 16);
                        }

                        if (entityDef.id == 2579) {
                            cacheSprite[838].drawSprite(spriteDrawX - 4, spriteDrawY - 16);
                        }
                        if (entityDef.id == 547) {
                            cacheSprite[870].drawSprite(spriteDrawX - 4, spriteDrawY - 16);
                        }

                        // rock tail are objects
                    }

                    if (entityDef.childrenIDs != null) {
                        entityDef = entityDef.method161();
                    }
                    if (entityDef == null) {
                        continue;
                    }
                }
                if (j < playerCount) {
                    int l = Configuration.DISPLAY_HP_ABOVE_HEAD || Configuration.DISPLAY_USERNAMES_ABOVE_HEAD ? 42 : 32;
                    if (Configuration.DISPLAY_HP_ABOVE_HEAD) {
                        l += 20;
                    }
                    if (Configuration.DISPLAY_USERNAMES_ABOVE_HEAD) {
                        l += 10;
                    }
                    Player player = (Player) obj;
                    if (player.headIcon >= 0) {
                        npcScreenPos((Entity) obj, ((Entity) obj).height + 15);
                        if (spriteDrawX > -1) {
                            if (player.skulled) {
                                l += 2;
                                skullIcons[0].drawSprite(spriteDrawX - 12, spriteDrawY - l);
                                l += 19;
                            }
                            if (player.bountyHunterIcon >= 0 && player.bountyHunterIcon <= 4) {
                                cacheSprite[1026 + player.bountyHunterIcon]
                                        .drawSprite(spriteDrawX - (player.skulled ? 8 : 10), spriteDrawY - l);
                                l += 28;
                            }
                            if (player.headIcon < 20) {
                                headIcons[player.headIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
                                l += 29;
                            }

                            if (Configuration.DISPLAY_USERNAMES_ABOVE_HEAD) {
                                if ((player.playerRights >= 1) && (player.playerRights <= 2)
                                        && (getOverheadPlayerTitle(player).startsWith("<col"))) {
                                    newSmallFont.drawCenteredString(
                                            player.name != null ? getOverheadPlayerTitle(player) + player.name
                                                    : "null username",
                                            spriteDrawX, spriteDrawY - 10, 0x15FF00, 0x000000);
                                } else if ((player.playerRights >= 1) && (player.playerRights <= 2)) {
                                    newSmallFont.drawCenteredString(
                                            player.name != null ? getOverheadPlayerTitle(player) + player.name
                                                    : "null username",
                                            spriteDrawX + 20, spriteDrawY - 10, 0x15FF00, 0x000000);
                                } else if (player.playerRights == 10) {
                                    newSmallFont.drawCenteredString(
                                            player.name != null ? getOverheadPlayerTitle(player) + player.name
                                                    : "null username",
                                            spriteDrawX + 10, spriteDrawY - 10, 0x15FF00, 0x000000);
                                } else if (player.playerRights == 6 || player.playerRights == 8 || player.playerRights == 9) {
                                    newSmallFont.drawCenteredString(
                                            player.name != null ? getOverheadPlayerTitle(player) + player.name
                                                    : "null username",
                                            spriteDrawX + 10, spriteDrawY - 10, 0x15FF00, 0x000000);
                                } else if (player.playerRights == 7) {
                                    newSmallFont.drawCenteredString(
                                            player.name != null ? getOverheadPlayerTitle(player) + player.name
                                                    : "null username",
                                            spriteDrawX + 85, spriteDrawY - 10, 0x15FF00, 0x000000);
                                } else if ((player.playerRights >= 3) && (player.playerRights <= 4)) {
                                    newSmallFont.drawCenteredString(
                                            player.name != null ? getOverheadPlayerTitle(player) + player.name
                                                    : "null username",
                                            spriteDrawX + 250, spriteDrawY - 10, 0x15FF00, 0x000000);
                                } else if (player.playerRights == 13 || player.playerRights == 14 || player.playerRights == 15 || player.playerRights == 16) {
                                    newSmallFont.drawCenteredString(
                                            player.name != null ? getOverheadPlayerTitle(player) + player.name
                                                    : "null username",
                                            spriteDrawX, spriteDrawY - 10, 0x15FF00, 0x000000);
                                } else if ((player.playerRights == 5)) {
                                    newSmallFont.drawCenteredString(
                                            player.name != null ? getOverheadPlayerTitle(player) + player.name
                                                    : "null username",
                                            spriteDrawX + 120, spriteDrawY - 10, 0x15FF00, 0x000000);
                                } else {
                                    newSmallFont.drawCenteredString(
                                            (player.name != null ? player.name : "null username"), spriteDrawX,
                                            spriteDrawY - 10, 0x15FF00, 0x000000);
                                }
                            }
                        }
                    }
                    if (j >= 0 && anInt855 == 10 && anInt933 == playerIndices[j]) {
                        npcScreenPos((Entity) obj, ((Entity) obj).height + 15);
                        if (spriteDrawX > -1) {
                            headIcons[7].drawSprite(spriteDrawX - 12, spriteDrawY - l);
                        }
                    }
                } else {
                    MobDefinition entityDef_1 = ((NPC) obj).definitionOverride;
                    if (entityDef_1.headIcon >= 0 && entityDef_1.headIcon < headIcons.length) {
                        npcScreenPos((Entity) obj, ((Entity) obj).height + 15);
                        if (spriteDrawX > -1) {
                            headIcons[entityDef_1.headIcon].drawSprite(spriteDrawX - 12, spriteDrawY - 30);
                        }
                    }
                    if (anInt855 == 1 && anInt1222 == npcIndices[j - playerCount] && loopCycle % 20 < 10) {
                        npcScreenPos((Entity) obj, ((Entity) obj).height + 15);
                        if (spriteDrawX > -1) {
                            headIcons[2].drawSprite(spriteDrawX - 12, spriteDrawY - 28);
                        }
                    }
                }
                if (((Entity) obj).textSpoken != null && (j >= playerCount || publicChatMode == 0 || publicChatMode == 3
                        || publicChatMode == 1 && isFriendOrSelf(((Player) obj).name))) {
                    npcScreenPos((Entity) obj, ((Entity) obj).height);
                    if (spriteDrawX > -1 && anInt974 < anInt975) {
                        anIntArray979[anInt974] = chatTextDrawingArea.method384(((Entity) obj).textSpoken) / 2;
                        anIntArray978[anInt974] = chatTextDrawingArea.anInt1497;
                        anIntArray976[anInt974] = spriteDrawX;
                        anIntArray977[anInt974] = spriteDrawY;
                        anIntArray980[anInt974] = ((Entity) obj).anInt1513;
                        anIntArray981[anInt974] = ((Entity) obj).anInt1531;
                        anIntArray982[anInt974] = ((Entity) obj).textCycle;
                        aStringArray983[anInt974++] = ((Entity) obj).textSpoken;
                        if (anInt1249 == 0 && ((Entity) obj).anInt1531 >= 1 && ((Entity) obj).anInt1531 <= 3) {
                            anIntArray978[anInt974] += 10;
                            anIntArray977[anInt974] += 5;
                        }
                        if (anInt1249 == 0 && ((Entity) obj).anInt1531 == 4) {
                            anIntArray979[anInt974] = 60;
                        }
                        if (anInt1249 == 0 && ((Entity) obj).anInt1531 == 5) {
                            anIntArray978[anInt974] += 5;
                        }
                    }
                }
                if (((Entity) obj).loopCycleStatus > loopCycle) {

                    try {
                        npcScreenPos((Entity) obj, ((Entity) obj).height + 15);
                        if (spriteDrawX > -1) {
                            if (Configuration.NEW_HEALTH_BARS) {
                                double hpWidth;
                                long cur = ((Entity) (obj)).currentHealth;
                                long max = ((Entity) (obj)).maxHealth;
                                if (cur > 0) {
                                    hpWidth = 56 * ((double) cur / max);
                                } else {
                                    hpWidth = 0;
                                }
                                long currentHealth = ((Entity) obj).currentHealth;
                                long maxHealth = ((Entity) obj).maxHealth;
                                if (maxHealth > 0) {
                                    if (((Entity) obj) instanceof Player) {
                                        currentHealth = currentHealth / 10;
                                        maxHealth = maxHealth / 10;
                                    }
                                }
                                long i1 = (currentHealth * 30 / maxHealth);
                                if (i1 > 30) {
                                    i1 = 30;
                                }
                                long hpPercent = (currentHealth * 90 / maxHealth);
                                if (hpPercent > 90) {
                                    hpPercent = 90;
                                }
                                long HpPercent = (currentHealth * 56 / maxHealth);
                                if (HpPercent > 56) {
                                    HpPercent = 56;
                                }
                                if (maxHealth >= 2000) {
                                    cacheSprite[349].drawSprite(spriteDrawX - 44, spriteDrawY - 5);
                                    Sprite HPBarFull = new Sprite(SpriteLoader.cache[397].spriteData,
                                            (int) Math.min(hpWidth, 56), 7, 1);
                                    HPBarFull.drawSprite(spriteDrawX - 44, spriteDrawY - 5);
                                    HPBarFull = null;
                                } else {
                                    cacheSprite[349].drawSprite(spriteDrawX - 28, spriteDrawY - 5);
                                    Sprite HPBarFull = new Sprite(SpriteLoader.cache[348].spriteData,
                                            (int) Math.min(hpWidth, 56), 7, 1);
                                    HPBarFull.drawSprite(spriteDrawX - 28, spriteDrawY - 5);
                                    HPBarFull = null;
                                }
                            } else {

                                long i1 = ((Entity) obj).currentHealth * 30 / ((Entity) obj).maxHealth;
                                if (i1 > 30) {
                                    i1 = 30;
                                }
                                DrawingArea.drawPixels(5, spriteDrawY - 3, spriteDrawX - 15, 65280, (int) i1);
                                DrawingArea.drawPixels(5, spriteDrawY - 3, (int) (spriteDrawX - 15 + i1), 0xff0000, (int)(30 - i1));
                            }
                            if (Configuration.DISPLAY_HP_ABOVE_HEAD && obj instanceof Player) {
                                Entity o = (Entity) obj;
                                double currentHitpoints = o.currentHealth;
                                double maxHitpoints = o.maxHealth;
                                double percentage = (int) (currentHitpoints / maxHitpoints * 100);
                                int color = calculateHitpointsColor((int) Math.round(percentage));
                                newRegularFont.drawCenteredString(
                                        new StringBuilder().append((int) currentHitpoints).append("/")
                                                .append((int) maxHitpoints).toString(),
                                        spriteDrawX, spriteDrawY - 22, color, 0x000000);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                /**
                 * Hitmarks
                 */
                for (int j1 = 0; j1 < 4; j1++) {
                    if (((Entity) obj).hitsLoopCycle[j1] > loopCycle) {
                        Entity entity = (Entity) obj;
                        npcScreenPos(entity, entity.height / 2);

                        if (!Configuration.NEW_HITMARKS) {

                            hitmarkDrawOld(spriteDrawX, spriteDrawY, j1, entity);

                        } else {

                            if (entity.moveTimer[j1] == 0) {
                                if (entity.hitmarkMove[j1] > -14)
                                    entity.hitmarkMove[j1]--;
                                entity.moveTimer[j1] = 2;
                            } else {
                                entity.moveTimer[j1]--;
                            }
                            if (entity.hitmarkMove[j1] <= -14)
                                entity.hitmarkTrans[j1] -= 10;

                            hitmarkDrawNew(entity, String.valueOf(
                                    !Configuration.CONSTITUTION_ENABLED ? entity.hitArray[j1] / 10 :
                                            entity.hitArray[j1]).length(),
                                    entity.hitMarkTypes[j1], entity.hitIcon[j1], entity.hitArray[j1],
                                    entity.soakDamage[j1], entity.hitmarkMove[j1], entity.hitmarkTrans[j1], j1);
                        }

                    }
                }
            }

            for (int k = 0; k < anInt974; k++) {
                int k1 = anIntArray976[k];
                int l1 = anIntArray977[k];
                int j2 = anIntArray979[k];
                int k2 = anIntArray978[k];
                boolean flag = true;

                while (flag) {
                    flag = false;

                    for (int l2 = 0; l2 < k; l2++) {
                        if (l1 + 2 > anIntArray977[l2] - anIntArray978[l2] && l1 - k2 < anIntArray977[l2] + 2
                                && k1 - j2 < anIntArray976[l2] + anIntArray979[l2]
                                && k1 + j2 > anIntArray976[l2] - anIntArray979[l2]
                                && anIntArray977[l2] - anIntArray978[l2] < l1) {
                            l1 = anIntArray977[l2] - anIntArray978[l2];
                            flag = true;
                        }
                    }
                }

                spriteDrawX = anIntArray976[k];
                spriteDrawY = anIntArray977[k] = l1;
                String s = aStringArray983[k];

                if (anInt1249 == 0) {
                    int i3 = 0xffff00;

                    if (anIntArray980[k] < 6) {
                        i3 = anIntArray965[anIntArray980[k]];
                    }

                    if (anIntArray980[k] == 6) {
                        i3 = anInt1265 % 20 >= 10 ? 0xffff00 : 0xff0000;
                    }

                    if (anIntArray980[k] == 7) {
                        i3 = anInt1265 % 20 >= 10 ? 65535 : 255;
                    }

                    if (anIntArray980[k] == 8) {
                        i3 = anInt1265 % 20 >= 10 ? 0x80ff80 : 45056;
                    }

                    if (anIntArray980[k] == 9) {
                        int j3 = 150 - anIntArray982[k];

                        if (j3 < 50) {
                            i3 = 0xff0000 + 1280 * j3;
                        } else if (j3 < 100) {
                            i3 = 0xffff00 - 0x50000 * (j3 - 50);
                        } else if (j3 < 150) {
                            i3 = 65280 + 5 * (j3 - 100);
                        }
                    }

                    if (anIntArray980[k] == 10) {
                        int k3 = 150 - anIntArray982[k];

                        if (k3 < 50) {
                            i3 = 0xff0000 + 5 * k3;
                        } else if (k3 < 100) {
                            i3 = 0xff00ff - 0x50000 * (k3 - 50);
                        } else if (k3 < 150) {
                            i3 = 255 + 0x50000 * (k3 - 100) - 5 * (k3 - 100);
                        }
                    }

                    if (anIntArray980[k] == 11) {
                        int l3 = 150 - anIntArray982[k];

                        if (l3 < 50) {
                            i3 = 0xffffff - 0x50005 * l3;
                        } else if (l3 < 100) {
                            i3 = 65280 + 0x50005 * (l3 - 50);
                        } else if (l3 < 150) {
                            i3 = 0xffffff - 0x50000 * (l3 - 100);
                        }
                    }

                    if (anIntArray981[k] == 0) {
                        chatTextDrawingArea.drawText(0, s, spriteDrawY + 1, spriteDrawX + 1);
                        chatTextDrawingArea.drawText(i3, s, spriteDrawY, spriteDrawX);
                    }

                    if (anIntArray981[k] == 1) {
                        chatTextDrawingArea.method386(0, s, spriteDrawX + 1, anInt1265, spriteDrawY + 1);
                        chatTextDrawingArea.method386(i3, s, spriteDrawX, anInt1265, spriteDrawY);
                    }

                    if (anIntArray981[k] == 2) {
                        chatTextDrawingArea.method387(spriteDrawX + 1, s, anInt1265, spriteDrawY + 1, 0);
                        chatTextDrawingArea.method387(spriteDrawX, s, anInt1265, spriteDrawY, i3);
                    }

                    if (anIntArray981[k] == 3) {
                        chatTextDrawingArea.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY + 1,
                                spriteDrawX + 1, 0);
                        chatTextDrawingArea.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY, spriteDrawX,
                                i3);
                    }

                    if (anIntArray981[k] == 4) {
                        int i4 = chatTextDrawingArea.method384(s);
                        int k4 = (150 - anIntArray982[k]) * (i4 + 100) / 150;
                        DrawingArea.setBounds(spriteDrawX - 50, 0, spriteDrawX + 50, 334);
                        chatTextDrawingArea.method385(0, s, spriteDrawY + 1, spriteDrawX + 51 - k4);
                        chatTextDrawingArea.method385(i3, s, spriteDrawY, spriteDrawX + 50 - k4);
                        DrawingArea.defaultDrawingAreaSize();
                    }

                    if (anIntArray981[k] == 5) {
                        int j4 = 150 - anIntArray982[k];
                        int l4 = 0;

                        if (j4 < 25) {
                            l4 = j4 - 25;
                        } else if (j4 > 125) {
                            l4 = j4 - 125;
                        }

                        DrawingArea.setBounds(0, spriteDrawY - chatTextDrawingArea.anInt1497 - 1, 512, spriteDrawY + 5);
                        chatTextDrawingArea.drawText(0, s, spriteDrawY + 1 + l4, spriteDrawX + 1);
                        chatTextDrawingArea.drawText(i3, s, spriteDrawY + l4, spriteDrawX);
                        DrawingArea.defaultDrawingAreaSize();
                    }
                } else {
                    chatTextDrawingArea.drawText(0, s, spriteDrawY + 1, spriteDrawX + 1);
                    chatTextDrawingArea.drawText(0xffff00, s, spriteDrawY, spriteDrawX);
                }
            }
        } catch (Exception e) {
        }
    }

    private String getOverheadPlayerTitle(Player player) {

        if (player.playerRights == 0) {
            return "@whi@ ";
        }
        if (player.playerRights == 1) {
            return "<img=1><col=20B2AA>Moderator@whi@ ";
        }

        if (player.playerRights == 2) {
            return "<img=2>@yel@Admin@whi@ ";
        }


        if (player.playerRights == 14) {
            return "<img=1541>@or2@Manager@whi@ ";
        }


        if (player.playerRights == 3) {
            return "<img=3><shad=1>@bla@Onyx Donator@whi@ ";
        }
        if (player.playerRights == 13) {
            return "<img=1508><shad=1>@or2@Zenyte Donator@whi@ ";
        }
        if (player.playerRights == 15) {
            return "<img=1602><shad=1>@mag@Tanzanite Donator@whi@ ";
        }
        if (player.playerRights == 16) {
            return "<img=1658><shad=1><col=e9c401>Hydrix Donator@whi@ ";
        }

        if (player.playerRights == 4) {
            return "<img=4><col=ff0000>Founder@whi@ ";
        }

        if (player.playerRights == 5) {
            return "<img=5>@mag@Helper@whi@ ";
        }
        if (player.playerRights == 6) {
            return "<img=6>@blu@Sapphire Donator@whi@ ";
        }

        if (player.playerRights == 7) {
            return "<img=7>@gre@Emerald Donator@whi@ ";
        }

        if (player.playerRights == 8) {
            return "<img=8>@red@Ruby Donator@whi@ ";
        }

        if (player.playerRights == 9) {
            return "<img=9>@whi@Diamond Donator@whi@ ";
        }
        if (player.playerRights == 3) {
            return "<img=3><shad=1>@bla@Onyx Donator@whi@ ";
        }
        if (player.playerRights == 3) {
            return "<img=13><shad=1>@or2@Zenyte Donator@whi@ ";
        }
        if (player.playerRights == 10 && player.name.equalsIgnoreCase("Fpk merk")) {
            return "<img=10>@red@Media Manager@whi@ ";
        }
        if (player.playerRights == 10) {
            return "<img=10>@red@Youtuber@whi@ ";
        }

        if (player.playerRights == 12) {
            return "<img=5>@mag@Support@whi@ ";
        }

        return "";
    }

    private int calculateHitpointsColor(int percentage) {
        int[] colorArray = new int[]{0x01DF01, 0x3ADF00, 0x74DF00, 0xA5DF00, 0xD7DF01, 0xDBA901, 0xDF7401, 0xDF3A01,
                0xFA5858, 0xFE2E2E, 0xDF0101,};
        int color = colorArray[0];
        for (int i = 0; i < colorArray.length; i++) {
            if (percentage >= 100 - i * 10) {
                color = colorArray[i];
                break;
            }
        }
        return color;
    }

    public void updateGameArea() {
        try {
            Rasterizer.setBounds(getScreenWidth(), getScreenHeight());
            fullScreenTextureArray = Rasterizer.lineOffsets;
            Rasterizer.setBounds(
                    GameFrame.getScreenMode() == ScreenMode.FIXED && isGameFrameVisible()
                            ? chatAreaIP != null ? chatAreaIP.anInt316 : 519
                            : getScreenWidth(),
                    GameFrame.getScreenMode() == ScreenMode.FIXED && isGameFrameVisible()
                            ? chatAreaIP != null ? chatAreaIP.anInt317 : 165
                            : getScreenHeight());
            anIntArray1180 = Rasterizer.lineOffsets;
            Rasterizer.setBounds(
                    GameFrame.getScreenMode() == ScreenMode.FIXED && isGameFrameVisible()
                            ? tabAreaIP != null ? tabAreaIP.anInt316 : 346
                            : getScreenWidth(),
                    GameFrame.getScreenMode() == ScreenMode.FIXED && isGameFrameVisible()
                            ? tabAreaIP != null ? tabAreaIP.anInt317 : 335
                            : getScreenHeight());
            anIntArray1181 = Rasterizer.lineOffsets;
            Rasterizer.setBounds(gameAreaWidth, gameAreaHeight);
            anIntArray1182 = Rasterizer.lineOffsets;
            int ai[] = new int[9];

            for (int i8 = 0; i8 < 9; i8++) {
                int k8 = 128 + i8 * 32 + 15;
                int l8 = 600 + k8 * 3;
                int i9 = Rasterizer.SINE[k8];
                ai[i8] = l8 * i9 >> 16;
            }

            WorldController.method310(500, 800, !loggedIn ? getScreenWidth() : gameAreaWidth,
                    !loggedIn ? getScreenHeight() : gameAreaHeight, ai);

            if (loggedIn) {
                gameScreenIP = new RSImageProducer(gameAreaWidth, gameAreaHeight, getGameComponent());
            } else {
                titleScreenIP = new RSImageProducer(getScreenWidth(), getScreenHeight(), getGameComponent());
            }
            updateGraphics(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateNPCs(ByteBuffer buffer, int i) {
        anInt839 = 0;
        playersToUpdateCount = 0;
        updateNPCAmount(buffer);
        updateNpcMovement(i, buffer);
        readNPCUpdateMask(buffer);

        for (int k = 0; k < anInt839; k++) {
            int l = anIntArray840[k];

            if (npcArray[l].loopCycle != loopCycle) {
                npcArray[l].definitionOverride = null;
                npcArray[l] = null;
            }
        }

        if (buffer.position != i) {
            Signlink.reportError(myUsername + " size mismatch in getnpcpos - pos:" + buffer.position + " psize:" + i);
            throw new RuntimeException("eek");
        }

        for (int i1 = 0; i1 < npcCount; i1++) {
            if (npcArray[npcIndices[i1]] == null) {
                Signlink.reportError(myUsername + " null entry in npc list - pos:" + i1 + " size:" + npcCount);
                throw new RuntimeException("eek");
            }
        }
    }

    private void updatePlayers(int i, ByteBuffer buffer) {
        anInt839 = 0;
        playersToUpdateCount = 0;
        updatePlayerMovement(buffer);
        updatePlayer(buffer);
        updatePlayerMovement(buffer, i);
        processPlayerUpdating(buffer);

        for (int k = 0; k < anInt839; k++) {
            int l = anIntArray840[k];

            if (playerArray[l].loopCycle != loopCycle) {
                playerArray[l] = null;
            }
        }

        if (buffer.position != i) {
            Signlink.reportError("Error packet size mismatch in getplayer pos:" + buffer.position + " psize:" + i);
            throw new RuntimeException("eek");
        }

        for (int i1 = 0; i1 < playerCount; i1++) {
            if (playerArray[playerIndices[i1]] == null) {
                Signlink.reportError(myUsername + " null entry in pl list - pos:" + i1 + " size:" + playerCount);
                throw new RuntimeException("eek");
            }
        }

    }

    private boolean rotateTime() {
        if (System.currentTimeMillis() - rotateTimer > 60000) {
            // System.out.println("Old rotateTimer: "+rotateTimer);
            rotateTimer = System.currentTimeMillis();
            // System.out.println("New rotateTimer: "+rotateTimer);
            return true;
        }
        return false;
    }

    public void rotateCam() {
        if (rotateTime()) {
            // int left = cameraRotationLeft;
            cameraRotationLeft += 25;
            // System.out.println("O.L: "+left+", C.L: "+cameraRotationLeft);
            // System.out.println("["+System.currentTimeMillis()+"] Done rotating camera.");
        }
    }

    private void handleQuickAidsActive() {
        int toggle = -1;
        if (prayerInterfaceType == 5608) {
            if (getQuickPrayersSet() > 0) {
                if (!mapArea.prayer.getOrbState()) {
                    for (int i = 0; i < quickPrayers.length; i++) {
                        int button = i == 26 ? 25104 : i == 27 ? 25108 : (i * 2) + 25000;
                        RSInterface rsInterface = RSInterface.interfaceCache[button];
                        if (rsInterface.valueIndexArray != null && rsInterface.valueIndexArray[0][0] == 5) {
                            toggle = rsInterface.valueIndexArray[0][1];
                            if (variousSettings[toggle] == 0 && quickPrayers[i] == 1) {
                                getOut().putOpcode(185);
                                getOut().putInt(button);
                                mapArea.prayer.setOrbState(true);
                            } else if (quickPrayers[i] == 1 && variousSettings[toggle] == 1) {
                                mapArea.prayer.setOrbState(true);
                            }
                        }
                    }
                } else {
                    turnOffPrayers();
                    mapArea.prayer.setOrbState(false);
                }
            } else {
                if (mapArea.prayer.getOrbState()) {
                    turnOffPrayers();
                } else {
                    pushMessage("You don't have any quick prayers selected.", 0, "");
                    pushMessage("Right-click the Prayer button next to the minimap to select some.", 0, "");
                }
                mapArea.prayer.setOrbState(false);
            }
        } else if (prayerInterfaceType == 32500) {
            if (getQuickCursesSet() > 0) {
                if (!mapArea.prayer.getOrbState()) {
                    for (int i = 0; i < quickCurses.length; i++) {
                        int button = (i * 2) + 32500 + 3;
                        RSInterface rsInterface = RSInterface.interfaceCache[button];
                        if (rsInterface.valueIndexArray != null && rsInterface.valueIndexArray[0][0] == 5) {
                            toggle = rsInterface.valueIndexArray[0][1];
                            if (variousSettings[toggle] == 0 && quickCurses[i] == 1) {
                                getOut().putOpcode(185);
                                getOut().putInt(button);
                                mapArea.prayer.setOrbState(true);
                            }
                        } else if (quickCurses[i] == 1 && variousSettings[toggle] == 1) {
                            mapArea.prayer.setOrbState(true);
                        }
                    }
                } else {
                    turnOffCurses();
                    mapArea.prayer.setOrbState(false);
                }
            } else {
                if (mapArea.prayer.getOrbState()) {
                    turnOffCurses();
                    mapArea.prayer.setOrbState(false);
                } else {
                    pushMessage("You don't have any quick curses selected.", 0, "");
                    pushMessage("Right-click the Curses button next to the minimap to select some.", 0, "");
                }
            }
        }
    }

    private int getQuickPrayersSet() {
        int amount = 0;
        for (int i = 0; i < quickPrayers.length; i++)
            if (quickPrayers[i] == 1) {
                amount++;
            }
        return amount;
    }

    private int getQuickCursesSet() {
        int amount = 0;
        for (int i = 0; i < quickCurses.length; i++)
            if (quickCurses[i] == 1) {
                amount++;
            }
        return amount;
    }

    private int[] getCurseTypeForIndex(int index) {
        int[] types = null;
        for (int g = 0; g < leechCurse.length; g++) {
            if (index == leechCurse[g])
                types = sapCurse;
        }
        for (int g = 0; g < sapCurse.length; g++) {
            if (index == sapCurse[g])
                types = leechCurse;
        }
        for (int g = 0; g < deflectCurse.length; g++) {
            if (index == deflectCurse[g])
                types = deflectCurse;
        }
        if (index == 6) {
            int[] type = {17, 18};
            types = type;
        }
        if (index == 17 || index == 18) {
            int[] type = {6, 7, 8, 9, 17, 18};
            types = type;
        }
        if (index == 19) {
            int[] turmoilCurseOff = {1, 2, 3, 4, 10, 11, 12, 13, 14, 15, 16, 19};
            types = turmoilCurseOff;
        }
        return types;
    }

    public void togglePrayerState(int button) {
        int index = button == 17279 ? 26 : button == 17280 ? 27 : button - 17202;
        if (prayerInterfaceType == 5608) {
            if ((maxStats[5] / 10) >= prayerLevelRequirements[index]) {
                int[] types = getPrayerTypeForIndex(index);
                if (types != null) {
                    for (int g = 0; g < rangeAndMagePray.length; g++) {
                        if (index == rangeAndMagePray[g]) {
                            types = rangeAndMagePrayOff;
                        }
                    }
                    for (int i = 0; i < types.length; i++) {
                        if (index != types[i]) {
                            if (index == 24 || index == 25) {
                                types = superiorPray;
                            }
                            quickPrayers[types[i]] = 0;
                            variousSettings[quickConfigIDs[types[i]]] = 0;
                            updateConfig(quickConfigIDs[types[i]]);
                            if (dialogID != -1)
                                inputTaken = true;
                        } else {
                            quickPrayers[index] = (quickPrayers[index] + 1) % 2;
                            variousSettings[quickConfigIDs[index]] = quickPrayers[index];
                            updateConfig(quickConfigIDs[index]);
                            if (dialogID != -1)
                                inputTaken = true;
                        }
                    }
                } else {
                    quickPrayers[index] = (quickPrayers[index] + 1) % 2;
                    variousSettings[quickConfigIDs[index]] = quickPrayers[index];
                    updateConfig(quickConfigIDs[index]);
                    if (dialogID != -1)
                        inputTaken = true;
                }
            } else {
                pushMessage("You need a Prayer level of atleast " + prayerLevelRequirements[index] + " to use "
                        + prayerName[index] + ".", 0, "");
            }
        } else if (prayerInterfaceType == 32500) {
            if ((maxStats[5] / 10) >= curseLevelRequirements[index]) {
                int[] types = getCurseTypeForIndex(index);
                if (types != null) {
                    for (int i = 0; i < types.length; i++) {
                        if (index != types[i]) {
                            quickCurses[types[i]] = 0;
                            variousSettings[quickConfigIDs[types[i]]] = 0;
                            updateConfig(quickConfigIDs[types[i]]);
                            if (dialogID != -1)
                                inputTaken = true;
                        }
                    }
                }
                quickCurses[index] = (quickCurses[index] + 1) % 2;
                variousSettings[quickConfigIDs[index]] = quickCurses[index];
                updateConfig(quickConfigIDs[index]);
                if (dialogID != -1)
                    inputTaken = true;
            } else {
                pushMessage("You need a Prayer level of atleast " + curseLevelRequirements[index] + " to use "
                        + curseName[index] + ".", 0, "");
            }
        }
    }

    private void turnOffPrayers() {
        int toggle = -1;
        for (int i = 0; i < quickPrayers.length; i++) {
            int x = i == 26 ? 25104 : i == 27 ? 25108 : (i * 2) + 25000;
            ;
            RSInterface rsInterface = RSInterface.interfaceCache[x];
            if (rsInterface.valueIndexArray != null && rsInterface.valueIndexArray[0][0] == 5) {
                toggle = rsInterface.valueIndexArray[0][1];
                if (variousSettings[toggle] == 1 && quickPrayers[i] == 1) {
                    getOut().putOpcode(185);
                    getOut().putInt(x);
                }
            }
        }
    }

    private void turnOffCurses() {
        int toggle = -1;
        for (int i = 0; i < quickCurses.length; i++) {
            RSInterface rsInterface = RSInterface.interfaceCache[(i * 2) + 32503];
            if (rsInterface.valueIndexArray != null && rsInterface.valueIndexArray[0][0] == 5) {
                toggle = rsInterface.valueIndexArray[0][1];
                if (variousSettings[toggle] == 1 && quickCurses[i] == 1) {
                    getOut().putOpcode(185);
                    getOut().putInt((i * 2) + 32503);
                }
            }
        }
    }

    private int[] getPrayerTypeForIndex(int index) {
        int[] type = null;
        for (int i = 0; i < prayer.length; i++) {
            for (int il = 0; il < prayer[i].length; il++) {
                if (index == prayer[i][il]) {
                    type = prayer[i];
                }
            }
        }
        return type;
    }

    private void saveQuickSelection() {
        Save.settings(Client.getClient());
        tabInterfaceIDs[5] = prayerInterfaceType;
    }

    public void toggleQuickAidsSelection() {
        boolean inProcess = (tabInterfaceIDs[5] == 17200 || tabInterfaceIDs[5] == 17234);
        if (inProcess)
            saveQuickSelection();
        else {
            if (prayerInterfaceType == 5608) {
                if (!inProcess) {
                    tabInterfaceIDs[5] = 17200;
                    tabAreaAltered = true;
                } else {
                    tabInterfaceIDs[5] = 5608;
                    tabAreaAltered = true;
                }
            } else if (prayerInterfaceType == 32500) {
                if (!inProcess) {
                    tabInterfaceIDs[5] = 17234;
                    tabAreaAltered = true;
                } else {
                    tabInterfaceIDs[5] = 32500;
                    tabAreaAltered = true;
                }
            }
            tabID = 5;
        }
    }

    private void saveGoals(String name) {
        try {
            File file = new File(Signlink.getCacheDirectory().toString() + "/" + name.trim().toLowerCase() + ".goals");
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            for (int index = 0; index < Skills.goalData.length; index++) {
                out.writeInt(Skills.goalData[index][0]);
                out.writeInt(Skills.goalData[index][1]);
                out.writeInt(Skills.goalData[index][2]);
                out.writeUTF(Skills.goalType);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStrings(String str, int i) {
        switch (i) {
            case 1675:
                setInterfaceText(str, 17508);
                break;// Stab

            case 1676:
                setInterfaceText(str, 17509);
                break;// Slash

            case 1677:
                setInterfaceText(str, 17510);
                break;// Crush

            case 1678:
                setInterfaceText(str, 17511);
                break;// Magic

            case 1679:
                setInterfaceText(str, 17512);
                break;// Range

            case 1680:
                setInterfaceText(str, 17513);
                break;// Stab

            case 1681:
                setInterfaceText(str, 17514);
                break;// Slash

            case 1682:
                setInterfaceText(str, 17515);
                break;// Crush

            case 1683:
                setInterfaceText(str, 17516);
                break;// Magic

            case 1684:
                setInterfaceText(str, 17517);
                break;// Range

            case 1686:
                setInterfaceText(str, 17518);
                break;// Strength

            case 15115: // summoning
            case 15116: // absorb melee
            case 15117: // absorb magic
            case 15118: // absorb range
            case 15119:
            case 15120:
                RSInterface.addText(i, str, RSInterface.fonts, 1, 0xFF9200, false, true);// 19153
                break;// magic damage
        }
    }

    public void showCombatBox() {
        if (openInterfaceID > 0) {
            return;
        }
        long currentHp = currentTarget.currentHealth;
        long maxHp = currentTarget.maxHealth;
        boolean rounded = false;
        int height = 35;
        int width = 106;
        int xPos = 10;
        int yPos = 30;
        if (!Configuration.CONSTITUTION_ENABLED) {
            currentHp = Math.round(currentHp / 10);
            maxHp /= 10;
            rounded = true;
        }
        int hpBarXPps = (xPos + 3);
        if (currentTarget instanceof Player) {
            Player player = (Player) currentTarget;
            drawCombatBox(xPos, yPos, width, height, currentHp, maxHp, hpBarXPps);
            newSmallFont.drawCenteredString(player.name, xPos + (width / 2), yPos + 10, 16777215, 0);
        } else {
            NPC npc = (NPC) currentTarget;
            boolean boss = maxHp > (rounded ? 250 : 2500);
            if (boss) {
                hpBarXPps = (xPos + 13);
                height = 25 + getYPosAddition(npc.damageDealers.size());
                width = 125;
            }

            if (parallelWidgetList.contains(RSInterface.interfaceCache[21100])){
                yPos += 50;
            }
            if (parallelWidgetList.contains(RSInterface.interfaceCache[144900])){
                yPos += 30;
            }

            drawCombatBox(xPos, yPos, width, height, currentHp, maxHp, hpBarXPps);
            if (npc.definitionOverride == null) {
                currentTarget = null;
                return;
            }
            newSmallFont.drawCenteredString(npc.definitionOverride.name, xPos + (width / 2), yPos + 10, 16777215, 0);
            if (boss) {
                yPos += 10;
                xPos += 5;
                for (int i = 0; i < 5; i++) {
                    newSmallFont.drawBasicString("", xPos, yPos + getYPosAddition(i), false);
                    newSmallFont.drawBasicString("", xPos + 90, yPos + getYPosAddition(i), false);
                    if (i >= npc.damageDealers.size()) {
                        continue;
                    }
                    String p = npc.damageDealers.get(i).getPlayer();
                    long damage = npc.damageDealers.get(i).getDamage();
                    if (!Configuration.CONSTITUTION_ENABLED && damage > 0) {
                        damage /= 10;
                    }
                    DecimalFormat df = new DecimalFormat("#");
                    newSmallFont.drawBasicString(p, xPos, yPos + getYPosAddition(i), false);
                    //newSmallFont.drawBasicString("" + df.format(damage) + "", xPos + 90, yPos + getYPosAddition(i),
                    //        false);
                    smallText.drawRightAlignedString("" + df.format(damage) + "", xPos + 120, yPos + getYPosAddition(i), 0xffffff,
                            true);
                }
            }
        }
    }

    public void drawCombatBox(int xPos, int yPos, int width, int height, long currentHp, long maxHp, int hpBarXPps) {
        double percentOfHpLeft = (((double) currentHp / (double) maxHp) * 100);
        double percentOfHpLost = 100 - percentOfHpLeft;
        int hpBarYPos = yPos + height - 20;
        if (percentOfHpLeft >= width) {
            percentOfHpLeft = width - 25;
        }
        TextDrawingArea.drawAlphaFilledPixels(xPos, yPos, width, height, 000000, 75);
        DrawingArea.drawPixels(15, hpBarYPos, hpBarXPps + (int) percentOfHpLeft, 11740160, (int) percentOfHpLost);// red
        DrawingArea.drawPixels(15, hpBarYPos, hpBarXPps/*-(int)percentOfHpLost*/, 31744, (int) percentOfHpLeft);// green
        if (maxHp > 0)
            newBoldFont.drawCenteredString((int) percentOfHpLeft + "%", xPos + (width / 2), hpBarYPos + 13,
                    16777215, 0);
        else
            newBoldFont.drawCenteredString(currentHp + "/" + maxHp, xPos + (width / 2), hpBarYPos + 13, 16777215, 0);
    }

    public int getYPosAddition(int index) {
        return (index * 10) + 10;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getLoginState() {
        return loginState;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }

    public int getLoginScreenState() {
        return loginScreenState;
    }

    public void setLoginScreenState(int loginScreenState) {
        this.loginScreenState = loginScreenState;
    }

    public int getLoginScreenCursorPos() {
        return loginScreenCursorPos;
    }

    public void setLoginScreenCursorPos(int loginScreenCursorPos) {
        this.loginScreenCursorPos = loginScreenCursorPos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ByteBuffer getInputBuffer() {
        return inputBuffer;
    }

    public void setInputBuffer(ByteBuffer inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    public long getServerSeed() {
        return serverSeed;
    }

    public void setServerSeed(long serverSeed) {
        this.serverSeed = serverSeed;
    }

    public ByteBuffer getLoginBuffer() {
        return loginBuffer;
    }

    public void setLoginBuffer(ByteBuffer loginBuffer) {
        this.loginBuffer = loginBuffer;
    }

    public ISAACCipher getConnectionCipher() {
        return connectionCipher;
    }

    public void setConnectionCipher(ISAACCipher connectionCipher) {
        this.connectionCipher = connectionCipher;
    }

    public int getLoginFailures() {
        return loginFailures;
    }

    public void setLoginFailures(int loginFailures) {
        this.loginFailures = loginFailures;
    }

    public int getWalkableInterfaceId() {
        return walkableInterfaceId;
    }

    public void setWalkableInterfaceId(int anInt1018) {
        this.walkableInterfaceId = anInt1018;
    }

    public int getAnInt900() {
        return anInt900;
    }

    public void setAnInt900(int anInt900) {
        this.anInt900 = anInt900;
    }

    public int getFullscreenInterfaceID() {
        return fullscreenInterfaceID;
    }

    public void setFullscreenInterfaceID(int fullscreenInterfaceID) {
        this.fullscreenInterfaceID = fullscreenInterfaceID;
    }

    public Deque getaClass19_1179() {
        return aClass19_1179;
    }

    public void setaClass19_1179(Deque aClass19_1179) {
        this.aClass19_1179 = aClass19_1179;
    }

    public int getLastKnownPlane() {
        return lastKnownPlane;
    }

    public void setLastKnownPlane(int anInt985) {
        this.lastKnownPlane = anInt985;
    }

    public int getMyPlayerIndex() {
        return myPlayerIndex;
    }

    public Deque getProjectiles() {
        return aClass19_1013;
    }

    public void setaClass19_1013(Deque aClass19_1013) {
        this.aClass19_1013 = aClass19_1013;
    }

    public ByteBuffer[] getaStreamArray895s() {
        return aStreamArray895s;
    }

    public void setaStreamArray895s(ByteBuffer[] aStreamArray895s) {
        this.aStreamArray895s = aStreamArray895s;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public Deque getIncompleteAnimables() {
        return aClass19_1056;
    }

    public void setaClass19_1056(Deque aClass19_1056) {
        this.aClass19_1056 = aClass19_1056;
    }

    public ScriptManager getScriptManager() {
        return scriptManager;
    }

    public void setScriptManager(ScriptManager scriptManager) {
        this.scriptManager = scriptManager;
    }

    public GrandExchange getGrandExchange() {
        return grandExchange;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public void updateProgressBar(int childId, int interfaceState, int percentage, int interfaceState2) {
        RSInterface rsi = RSInterface.interfaceCache[childId];
        if (rsi == null) {
            return;
        }

        ProgressBar inter = (ProgressBar) rsi;
        inter.progressBarState = interfaceState;
        inter.progressBarPercentage = percentage;
        inter.defaultBarState = interfaceState2;
    }

    public int getPlane() {
        return plane;
    }

    public int getBaseX() {
        return regionBaseX;
    }

    public int getBaseY() {
        return regionBaseY;
    }

    public boolean isInInstancedRegion() {
        return requestMapReconstruct;
    }

    public int[][][] getInstanceTemplateChunks() {
        return constructRegionData;
    }

    public CollisionData[] getCollisionMaps() {
        return collisionData;
    }

    public Player getLocalPlayer() {
        return myPlayer;
    }

    public void stopNow() {
        destroy();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void teleport(int x, int y) {
        String string = "::tele " + x + " " + y;
        getOut().putOpcode(103);
        getOut().putByte(string.length() - 1);
        getOut().putString(string.substring(2));
    }

    public void preloadModels() {
        String cacheDir = Signlink.getCacheDirectory();
        File file = new File(cacheDir + "data" + File.separator + "raw" + File.separator);
        File[] fileArray = file.listFiles();
        if (fileArray == null)
            return;
        int length = fileArray.length;
        try {

            for (int fileIndex = 0; fileIndex < length; fileIndex++) {
                String fileName = fileArray[fileIndex].getName();
                if (!fileName.contains(".dat")) {
                    System.out.println("Invalid model ignored while preloading: " + fileName);
                    return;
                }

                byte[] buffer = ReadFile(cacheDir + "data" + File.separator + "raw" + File.separator + fileName);
                try {
                    int modelNumber = Integer.parseInt(fileName.replace(".dat", ""));
                    Model.method460(buffer, modelNumber, Model.modelsArray);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

            }
            System.out.println("Preloaded models");

        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public void preloadModels2() {
        String cacheDir = Signlink.getCacheDirectory();
        File file = new File(cacheDir + "data" + File.separator + "raw2" + File.separator);
        File[] fileArray = file.listFiles();
        if (fileArray == null)
            return;
        int length = fileArray.length;
        try {

            for (int fileIndex = 0; fileIndex < length; fileIndex++) {
                String fileName = fileArray[fileIndex].getName();
                if (!fileName.contains(".dat")) {
                    System.out.println("Invalid model ignored while preloading: " + fileName);
                    return;
                }

                byte[] buffer = ReadFile(cacheDir + "data" + File.separator + "raw2" + File.separator + fileName);
                try {
                    int modelNumber = Integer.parseInt(fileName.replace(".dat", ""));
                    Model.method460(buffer, modelNumber, Model.rs3ModelsArray);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

            }
            System.out.println("Preloaded models");

        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public int getItemHoverX(boolean inventory, int dx, boolean check) {
        int x = super.mouseX - dx;
        int width = RSInterface.interfaceCache[94021].width;
        if (inventory) {
            if (x > 240 - width)
                x = 240 - width;
            if (GameFrame.getScreenMode() == ScreenMode.FIXED) {
                if (x + width > 460) {
                    x = 460 - width;
                }
            }
        }
        return x;
    }

    public int getItemHoverY(boolean inventory, int dy, boolean check) {
        int y = super.mouseY - dy;
        int height = RSInterface.interfaceCache[94021].height;
        if (inventory) {
            int max = check ? 290 : 180;
            if (y > max)
                y = max;
            max = check ? (getHeight() - 240) : (getHeight() - 330);
            if (y > max)
                y = max;
        }
        return y;
    }

    public void updateItemHoverInterface(ItemDefinition def) {

        String action = "";
        if (itemHover > 0 && def != null && ItemStats.itemstats.length > itemHover) {
            if (def.actions != null) {
                action = def.actions[1] == null ? "" : def.actions[1];
            }

            RSInterface.interfaceCache[94002].message = " @lre@" + RSFontSystem.removeColors(def.name);

            if (ItemStats.itemstats[itemHover] != null) {
                if (ItemStats.itemstats[itemHover].type == 1) {
                    RSInterface.interfaceCache[94006].message = "";
                    String bonus = "";
                    for (int i = 0; i < 5; ++i) {
                        if (ItemStats.itemstats[itemHover].attackBonus[i] > 0) {
                            bonus = "+";
                        } else {
                            bonus = "";
                        }
                        RSInterface.interfaceCache[94006].message += "@whi@" + bonus + "" + ItemStats.itemstats[itemHover].attackBonus[i] + "\\n";
                    }
                    RSInterface.interfaceCache[94007].message = "";
                    for (int i = 0; i < 5; ++i) {
                        if (ItemStats.itemstats[itemHover].defenceBonus[i] > 0) {
                            bonus = "+";
                        } else {
                            bonus = "";
                        }
                        RSInterface.interfaceCache[94007].message += "@whi@" + bonus + "" + ItemStats.itemstats[itemHover].defenceBonus[i] + "\\n";
                    }
                    RSInterface.interfaceCache[94010].message = "@whi@" + (ItemStats.itemstats[itemHover].strengthBonus > 0 ? "+" : "") + "" + ItemStats.itemstats[itemHover].strengthBonus + "\\n@whi@" + (ItemStats.itemstats[itemHover].rangeBonus > 0 ? "+" : "") + "" + ItemStats.itemstats[itemHover].rangeBonus + "" + "\\n@whi@" + (ItemStats.itemstats[itemHover].prayerBonus > 0 ? "+" : "") + "" + ItemStats.itemstats[itemHover].prayerBonus + "" + "\\n@whi@" + (ItemStats.itemstats[itemHover].magicBonus > 0 ? "+" : "") + "" + ItemStats.itemstats[itemHover].magicBonus + ""


                    ;
                    RSInterface.interfaceCache[94021].height = 28;
                    RSInterface.interfaceCache[94021].width = 145;
                    RSInterface.interfaceCache[94020].childX[1] = 80;
                    RSInterface.interfaceCache[94022].message = "@whi@Press " + "@gre@CTRL" + "@whi@ to view stats";
                    RSInterface.interfaceCache[94022].centerText = false;
                    RSInterface.interfaceCache[94020].childX[2] = 10;
                } else if (ItemStats.itemstats[itemHover].type == 2) {
                    RSInterface.interfaceCache[94021].height = 28;
                    int width = newSmallFont.getTextWidth(def.name) + newSmallFont.getTextWidth(action) + 8;
                    if (width < 58) {
                        width = 58;
                    }
                    RSInterface.interfaceCache[94021].width = width;
                    RSInterface.interfaceCache[94020].childX[1] = 8 + (width / 2);
                    RSInterface.interfaceCache[94020].childX[2] = 8 + (width / 2);
                    RSInterface.interfaceCache[94022].message = "@whi@Heals: @gre@" + ItemStats.itemstats[itemHover].healAmount;
                    RSInterface.interfaceCache[94022].centerText = true;
                } else if (ItemStats.itemstats[itemHover].type == 3) {
                    System.out.println(ItemStats.itemstats[itemHover].rewards);
                    RSInterface.setScrollableItems(RSInterface.interfaceCache[94089], ItemStats.itemstats[itemHover].rewards);
                    RSInterface.interfaceCache[94087].message = ItemStats.itemstats[itemHover].information;
                }
            } else {
                RSInterface.interfaceCache[94021].height = 16;
                RSInterface.interfaceCache[94021].width = newSmallFont.getTextWidth(def.name) + newSmallFont.getTextWidth(action) + 8;
                RSInterface.interfaceCache[94020].childX[1] = 8 + (RSInterface.interfaceCache[94021].width / 2);
                RSInterface.interfaceCache[94022].message = "";
            }
        }
    }


    public void updateYoutubeInterface() {
        int ch = 0;
        int ch2 = 72151;
        int y = 0;
        int total = Youtube.getCurrentType().equalsIgnoreCase("video") ? Youtube.getVideos().size()
                : Youtube.getChannels().size();
        TextDrawingArea fonts[] = {smallText, normalText, chatTextDrawingArea};
        for (int i = 72151; i < 73151; i++) {
            RSInterface.interfaceCache[i] = null;
        }
        RSInterface rsi_1 = RSInterface.interfaceCache[72150];
        RSInterface.interfaceCache[72150].scrollMax = ((78 * total) - 2) < 192 ? 192 : ((78 * total) - 2);
        if (Youtube.getCurrentType().equalsIgnoreCase("video")) {
            RSInterface.interfaceCache[72115].message = "Recent Videos";

            rsi_1.totalChildren((9 * total));
            for (YoutubeVideo video : Youtube.getVideos()) {

                RSInterface.hoverButton(ch2, 1577, 1582, "Open Video");

                RSInterface.addForceSprite(ch2 + 1, Youtube.thumbnailForVideoId(video.getVideoId()));

                RSInterface.addText(ch2 + 2, toLength(video.getName(), 30), 0xffffff, false, false, 52, fonts, 0);

                RSInterface.addText(ch2 + 3, toLength("Channel:", 35), 0xffffff, false, false,
                        52, fonts, 0);

                RSInterface.addText(ch2 + 8, toLength(video.getChannelName(), 30), 0xFFFF00, false, false,
                        52, fonts, 0);

                RSInterface.addText(ch2 + 4, toLength(video.getViews() + " views", 24), 0xffffff, false, false, 52,
                        fonts, 0);

                RSInterface.addText(ch2 + 5, toLength(video.getLikes() + " likes", 24), 0xffffff, false, false, 52,
                        fonts, 0);

                RSInterface.addText(ch2 + 6, toLength(video.getComments() + " comments", 24), 0xffffff, false, false,
                        52, fonts, 0);

                RSInterface.addTextRight(ch2 + 7, toLength(video.getDate(), 30), fonts, 0, 0xFF981F, true);


                rsi_1.child(ch, ch2, 0, 0 + y);
                rsi_1.child(ch + 1, ch2 + 1, 4, 4 + y);
                rsi_1.child(ch + 2, ch2 + 2, 129, 2 + y);
                rsi_1.child(ch + 3, ch2 + 3, 129, 14 + y);
                rsi_1.child(ch + 4, ch2 + 4, 150, 28 + y);
                rsi_1.child(ch + 5, ch2 + 5, 150, 44 + y);
                rsi_1.child(ch + 6, ch2 + 6, 150, 61 + y);
                rsi_1.child(ch + 7, ch2 + 7, 305, 61 + y);
                rsi_1.child(ch + 8, ch2 + 8, 175, 14 + y);

                ch += 9;
                ch2 += 10;
                y += 78;
            }
        } else if (Youtube.getCurrentType().equalsIgnoreCase("channel")) {

            rsi_1.totalChildren((4 * total));
            RSInterface.interfaceCache[72115].message = "Youtube Channels";
            for (YoutubeChannel channel : Youtube.getChannels()) {
                RSInterface.hoverButton(ch2, 1578, 1583, "Open Channel");

                RSInterface.addForceSprite(ch2 + 1, Youtube.thumbnailForChannelId(channel.getChannelId()));

                RSInterface.addText(ch2 + 2, toLength("Channel: " + channel.getName(), 35), 0xffffff, false, false, 52,
                        fonts, 0);

                RSInterface.addText(ch2 + 3, toLength("Subscribers: " + channel.getSubscribers(), 35), 0xffffff, false,
                        false, 52, fonts, 0);

                rsi_1.child(ch, ch2, 0, 0 + y);
                rsi_1.child(ch + 1, ch2 + 1, 5, 5 + y);
                rsi_1.child(ch + 2, ch2 + 2, 78, 25 + y);
                rsi_1.child(ch + 3, ch2 + 3, 102, 48 + y);

                ch += 4;
                ch2 += 7;
                y += 78;
            }
        }
    }

    public void updateGodsHitpoints(int sara, int zammy) {

        RSInterface.interfaceCache[144906].message = sara + "%";
        RSInterface.interfaceCache[144907].message = zammy + "%";

        int saraPercent = (int) (169D * ((double)sara / 100D));
        int zammyPercent = (int) (169D * ((double)zammy / 100D));
        if (saraPercent >= 169)
            saraPercent = 169;
        if (zammyPercent >= 169)
            zammyPercent = 169;

        Sprite saraSprite = Sprite.getCutted(cacheSprite[1590], saraPercent, 24);
        Sprite zammySprite = Sprite.getCutted(cacheSprite[1591], zammyPercent, 24);
        RSInterface.addForceSprite(144904, saraSprite);
        RSInterface.addForceSprite(144905, zammySprite);

    }

    public static String toLength(String string, int length) {
        if (string.length() > length) {
            string = string.substring(0, length) + "..";
        }

        return string;
    }


    public static boolean itemEditing = false;
    public static int debuggingItem = 0, iRotx = 0, iRoty = 0, iZoom = 1000, iOffx = 0, iOffy = 0;
    public static int currentlyEditedItemId = 0;

}
