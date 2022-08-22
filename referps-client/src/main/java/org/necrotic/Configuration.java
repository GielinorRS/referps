package org.necrotic;

public class Configuration {
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
	
	public final static int CLIENT_VERSION = 3;
	public static boolean localHost = true;
	public static final boolean FORCE_CACHE_UPDATE = true;
	public static final boolean STOP_CACHE_UPDATES = false;
	public static final boolean DROPBOX_MODE = false;
	public final static String CLIENT_NAME = "REFER-PS";
	public final static String CACHE_DIRECTORY_NAME = "Solak"; //refer-ps-settings
	public static final String SETTINGS_DIRECTORY_NAME = "refer-ps-settings";
	public final static boolean JAGCACHED_ENABLED = false;
	public final static String JAGCACHED_HOST = "146.71.18.70";
	public final static int SERVER_PORT = 43595;
	public final static boolean DISPLAY_GAMEWORLD_ON_LOGIN = false;
	public final static int NPC_BITS = 18;
	public static final boolean SEND_HASH = true;
	public static final int[] REPACK_INDICIES = {
			//4
	};
	public static final int statMenuColor = 0x49bfff;
	public static final boolean CTRL_HOVER_HINT = true;
	public static final boolean USING_INTELLIJ = true;
	
	public static boolean SAVE_ACCOUNTS = true;
	public static boolean DISPLAY_HP_ABOVE_HEAD = false;
	public static boolean DISPLAY_USERNAMES_ABOVE_HEAD = false;
	public static boolean TWEENING_ENABLED = true;
	public static boolean NEW_HITMARKS = true;
	public static boolean CONSTITUTION_ENABLED = true;
	public static boolean NEW_HEALTH_BARS = true;
	public static boolean MONEY_POUCH_ENABLED = true;
	public static boolean SMILIES_ENABLED = false;
	public static boolean NOTIFICATIONS_ENABLED = false;
	public static boolean HIGHLIGHT_USERNAME = true;
	public static boolean NEW_CURSORS = false;
	public static boolean NEW_FUNCTION_KEYS = true;
	public static boolean PARTICLES_ENABLED = true;
	public static boolean FOG_ENABLED = true;
	public static boolean GROUND_TEXT = true;
	public static boolean HIGH_DETAIL = true;
	//public static boolean hdTexturing = true;
	public static boolean hdMinimap = false;
	public static boolean hdShading = true;
	public static boolean TOGGLE_ROOF_OFF = false;
	public static boolean TOGGLE_FOV = true;
	public static boolean DEPTH_BUFFER = false;
	public static String SERVER_HOST() {
		return localHost ? "127.0.0.1" : "146.71.18.70";//104.161.43.45 - 147.135.65.179
	}
}