package org.necrotic;

/**
 * The client's features can easily be toggled/changed here.
 *
 * @author Gabriel Hannason
 */

public class Configuration {
	public final static int CLIENT_VERSION = 40;
	public static boolean localHost = true;
	public static boolean beta = true;
	public static final boolean FORCE_CACHE_UPDATE = true;
	public static final boolean STOP_CACHE_UPDATES = false;
	public static final boolean DROPBOX_MODE = false;
	public final static String CLIENT_NAME = "Lunite";
	public final static String CACHE_DIRECTORY_NAME = "Lunite";
	public static final String SETTINGS_DIRECTORY_NAME = "Lunite_settings";
	public final static boolean JAGCACHED_ENABLED = false;
	public final static String JAGCACHED_HOST = "";
	public final static int SERVER_PORT = 9421;
	public final static boolean DISPLAY_GAMEWORLD_ON_LOGIN = false;
	public final static int NPC_BITS = 18;
	public static final boolean SEND_HASH = true;
	public static final int[] REPACK_INDICIES = {
			//4 // 6, 7, 8 //4  // 1
	};
	public static final int statMenuColor = 0x49bfff;
	public static final boolean CTRL_HOVER_HINT = true;
	public static boolean SAVE_ACCOUNTS = true;
	public static boolean DISPLAY_HP_ABOVE_HEAD = false;
	public static boolean DISPLAY_USERNAMES_ABOVE_HEAD = false;
	public static boolean TWEENING_ENABLED = true;
	public static boolean NEW_HITMARKS = true;
	public static boolean CONSTITUTION_ENABLED = false;
	public static boolean NEW_HEALTH_BARS = true;
	public static boolean MONEY_POUCH_ENABLED = false;
	public static boolean SMILIES_ENABLED = false;
	public static boolean NOTIFICATIONS_ENABLED = false;
	public static boolean HIGHLIGHT_USERNAME = true;
	public static boolean NEW_CURSORS = false;
	public static boolean NEW_FUNCTION_KEYS = true;
	public static boolean PARTICLES_ENABLED = true;
	public static boolean FOG_ENABLED = false;
	public static boolean GROUND_TEXT = true;
	public static boolean HIGH_DETAIL = false;
	public static boolean hdTexturing = true;
	public static boolean hdMinimap = false;
	public static boolean hdShading = false;
	public static boolean TOGGLE_ROOF_OFF = true;
	public static boolean TOGGLE_FOV = true;

	public static boolean ANIMATE_TEXTURES = true;
	public static boolean PLAYER_EQUIPMENT = true;
	public static boolean RENDER_PETS = true;


	public static int MAX_POLY_MODELS = 20000;
	public static boolean RENDER_PLAYER_BODY = true;


	public final static String SERVER_HOST() {
		return localHost ? "127.0.0.1" : beta ? "127.0.0.1" : "127.0.0.1";//104.161.43.45 - 147.135.65.179
	}


	/**
	 * Client Dimensions
	 */
	public static int
			clientSize = 0,
			clientWidth = 765,
			clientHeight = 503;

	public static int getClientWidth() {
		return clientWidth;
	}

	public static int getClientHeight() {
		return clientHeight;
	}

	public static final boolean IS_RUNNING_WINDOWS = true;

}