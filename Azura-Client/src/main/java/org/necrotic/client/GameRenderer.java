package org.necrotic.client;

import org.necrotic.Configuration;
import org.necrotic.client.graphics.CursorData;
import org.necrotic.client.graphics.RSImageProducer;
import org.necrotic.client.graphics.Sprite;
import org.necrotic.client.graphics.gameframe.GameFrame;
import org.necrotic.client.graphics.gameframe.GameFrame.ScreenMode;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class GameRenderer extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener, MouseWheelListener {

    private static final long serialVersionUID = 1L;
    private final long[] aLongArray7;
    private int anInt4;
    public boolean awtFocus;
    private final int[] charQueue;
    private int clickMode1;
    private int clickMode2;
    public int clickMode3;
    protected int clickX;
    protected int clickY;

    protected int lastClickX;
    protected int lastClickY;
    private int delayTime;
    int fps;
    RSImageProducer fullGameScreen;
    public int idleTime;
    final int[] keyArray;
    GameShell gameFrame;//mainframe
    int minDelay;
    public int mouseX;
    public int mouseY;
    public static int myHeight;
    public static int myWidth;
    private int readIndex;
    boolean resized;
    int saveClickX;
    int saveClickY;
    private boolean shouldClearScreen;
    private boolean shouldDebug;
    private int writeIndex;
    private long clickTime;
    protected boolean isApplet;
    public int forceWidth = -1;
    public int forceHeight = -1;
    public static int canvas_x = 0;
    public static int canvas_y = 0;
    Graphics graphics;

    public int getScreenWidth() {
        if (isApplet) {
            return canvas_width;
        }
        if (forceWidth >= 0) {
            return forceWidth;
        }
        return getRealScreenWidth();
    }

    public int getScreenHeight() {
        if (isApplet) {
            return canvas_height;
        }
        if (forceHeight >= 0) {
            return forceHeight;
        }

        return getRealScreenHeight();
    }

    public int getRealScreenWidth() {
        if (isApplet) {
            return canvas_width;
        }
        Component component = getGameComponent();
        if (component == null) {
            return forceWidth >= 0 ? forceWidth : 765;
        }

        int w = component.getWidth();
      /*  if (component instanceof java.awt.Container) {
            Insets insets = ((java.awt.Container) component).getInsets();
            w -= insets.left + insets.right;
        }*/
        return w;
    }

    public int getRealScreenHeight() {
        if (isApplet) {
            return canvas_height;
        }
        Component component = getGameComponent();
        if (component == null) {
            return forceHeight >= 0 ? forceHeight : 503;
        }

        int h = component.getHeight();
      /*  if (component instanceof java.awt.Container) {
            Insets insets = ((java.awt.Container) component).getInsets();
            h -= insets.top + insets.bottom;
        }*/
        return h;
    }

    GameRenderer() {
        delayTime = 20;
        minDelay = 1;
        aLongArray7 = new long[10];
        shouldDebug = false;
        shouldClearScreen = true;
        awtFocus = true;
        keyArray = new int[128];
        charQueue = new int[128];
    }

    void cleanUpForQuit() {
    }

    final void initApplet(int width, int height) {
        try {
            isApplet = true;
            myWidth = canvas_width = width;
            myHeight = canvas_height = height;
            forceWidth = myWidth;
            forceHeight = myHeight;
            getGameComponent().setBackground(Color.black);
            updateGraphics(true);
            fullGameScreen = new RSImageProducer(myWidth, myHeight, getGameComponent());
            startRunnable(this, 1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    final void createClientFrame(int w, int h) {
        isApplet = false;
        myWidth = w;
        myHeight = h;
        forceWidth = w;
        forceHeight = myHeight;

        setSize(w, h);
        setPreferredSize(new Dimension(w, h));
        try {
            SwingUtilities.invokeAndWait(() -> {
                gameFrame = new GameShell(this, myWidth, myHeight, false, false);
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
        getGameComponent().setBackground(Color.black);

        updateGraphics(true);
        fullGameScreen = new RSImageProducer(myWidth, myHeight, getGameComponent());
        startRunnable(this, 1);
    }

    public void updateGraphics(boolean clear) {
        //  canvas_x = (getRealScreenWidth() - getScreenWidth()) / 2;
        //  canvas_y = (getRealScreenHeight() - getScreenHeight()) / 2;
    }

    @Override
    public final void destroy() {
        anInt4 = -1;

        try {
            Thread.sleep(5000L);
        } catch (Exception _ex) {
        }

        if (anInt4 == -1) {
            exit();
        }
    }

    private static final Color FONT_COLOR = Color.white;
    private static final Font LOADING_FONT = new Font("Helvetica", 1, 13);
    private static final int barWidth = 300;
    private static final int barHeight = 30;
    private static final int barSpace = 2;
    private static final int barMax = barSpace + barHeight + barSpace + barWidth + barSpace + barHeight + barSpace;

    private static int currentLoadingColor = -1;
    private static int nextLoadingColor = -1;

    private Image loadingBuffer;

    private static void options(Graphics g) {
        try {
            if (g instanceof Graphics2D) {
                Graphics2D r = (Graphics2D) g;
                r.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                r.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            }
        } catch (Throwable t) {
        }
    }

    private static int randomColor() {
        int red = 0;
        int green = (int) (Math.random() * 17.0D);
        int blue = 0;

        green += 50 + (int) (Math.random() * 200.0D);

        return red << 16 | green << 8 | blue;
    }

    private long startTime = 0L;
    private long colorStart = 0L;

    private static int blend(int dst, int src, int src_alpha) {
        if (src_alpha <= 0) {
            return dst;
        }

        if (src_alpha >= 0xff) {
            return src;
        }

        int src_delta = 0xff - src_alpha;
        return ((0xff00ff00 & (0xff00ff & src) * src_alpha | 0xff0000 & (src & 0xff00) * src_alpha) >>> 8) + ((0xff0000 & src_delta * (dst & 0xff00) | src_delta * (dst & 0xff00ff) & 0xff00ff00) >>> 8);
    }

    private static final Color BACKGROUND_COLOR = Color.black;

    void resetGraphic() {

    }

    private void exit() {
        anInt4 = -2;
        cleanUpForQuit();

        if (gameFrame != null) {
            try {
                Thread.sleep(1000L);
            } catch (Exception _ex) {
            }

            try {
                System.exit(0);
            } catch (Throwable _ex) {
            }
        }
    }

    @Override
    public final void focusGained(FocusEvent event) {
        awtFocus = true;
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    @Override
    public final void focusLost(FocusEvent event) {
        awtFocus = false;

        for (int i = 0; i < 128; i++) {
            keyArray[i] = 0;
        }
    }

    public void rebuildFrame(boolean undecorated, int width, int height, boolean resizable, boolean full) {
        boolean createdByApplet = (isApplet && !full);
        myWidth = width;
        myHeight = height;
        if (gameFrame != null) {
            gameFrame.dispose();
        }
        if (!createdByApplet) {
            gameFrame = new GameShell(this, width, height, undecorated, resizable);
            gameFrame.addWindowListener(this);
        }
        graphics = (createdByApplet ? this : gameFrame).getGraphics();
        if (!createdByApplet) {
            setFocusTraversalKeysEnabled(false);
            getGameComponent().addMouseWheelListener(this);
            getGameComponent().addMouseListener(this);
            getGameComponent().addMouseMotionListener(this);
            getGameComponent().addKeyListener(this);
            getGameComponent().addFocusListener(this);
        }

    }


    public void recreateClientFrame(boolean decorative, int width, int height, boolean resizable) {
        Component component = getGameComponent();
        component.setBackground(Color.black);
        component.removeMouseWheelListener(this);
        component.removeMouseListener(this);
        component.removeMouseMotionListener(this);
        component.removeKeyListener(this);
        component.removeFocusListener(this);
        if (gameFrame != null) {
            gameFrame.removeWindowListener(this);
            gameFrame.setVisible(false);
            gameFrame.dispose();
            gameFrame = null;
        }
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        if (!isApplet || decorative) {
            try {
                SwingUtilities.invokeAndWait(() -> {
                    gameFrame = new GameShell(this, width, height, decorative, resizable);
                });
            } catch (Throwable e) {
                e.printStackTrace();
            }
            gameFrame.addWindowListener(this);
        }
        if (resizable && decorative) {
            if (!isApplet) {
                gameFrame.setMinimumSize(new Dimension(800, 600));
            }
        } else if (!resizable) {
            if (!isApplet) {
                gameFrame.setMinimumSize(new Dimension(765, 503));
            } else {
                setMinimumSize(new Dimension(765, 503));
            }
        }
        component = getGameComponent();
        component.setBackground(Color.black);
        component.addMouseWheelListener(this);
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        component.addKeyListener(this);
        component.addFocusListener(this);
        mouseX = mouseY = -1;


    }

    private int getChildHeight(RSInterface Interface, int Index) {
        return RSInterface.interfaceCache[Interface.children[Index]].height;
    }

    private int getChildWidth(RSInterface Interface, int Index) {
        return RSInterface.interfaceCache[Interface.children[Index]].width;
    }

    public Container getGameComponent() {
        return this;
    }

    private void interfaceScrollCheck(MouseWheelEvent e) {
        try {
            final int rot = e.getWheelRotation();
            final int TAB = 0, WINDOW = 1;
            final int POSX = 0, POSY = 1, WIDTH = 2, HEIGHT = 3, OFFX = 4, OFFY = 5, CHILD = 6, SENSITIVITY = 7;
            int tabInterfaceID = Client.tabInterfaceIDs[Client.tabID];
            if (tabInterfaceID == 11000) {
                tabInterfaceID = 1151;
            }
            int[] InterfaceID = {tabInterfaceID, Client.openInterfaceID};
            int[] variables = {0, 0, /* positionX, positionY */
                    0, 0, /* Width, Height */
                    canvas_x, canvas_y, 0, 15 /* Child ID, Sensitivity */};


            if (InterfaceID[WINDOW] != -1) {
                boolean resizable = GameFrame.getScreenMode() != ScreenMode.FIXED;
                int interfaceWidth = GameFrame.getScreenMode() != ScreenMode.FIXED ? getScreenWidth() : 516;
                int interfaceHeight = GameFrame.getScreenMode() != ScreenMode.FIXED ? getScreenHeight() : 338;
                RSInterface Window = RSInterface.interfaceCache[InterfaceID[WINDOW]];
                variables[OFFX] = resizable ? Client.instance.gameScreenDrawX + (interfaceWidth - 765) / 2 : 4;
                variables[OFFY] = resizable ? Client.instance.gameScreenDrawY + (interfaceHeight - 503) / 2 : 4;
                for (int Index = 0; Index < Window.children.length; Index++) {
                    if (RSInterface.interfaceCache[Window.children[Index]].scrollMax > 0) {
                        variables[CHILD] = Index;
                        variables[POSX] = Window.childX[Index];
                        variables[POSY] = Window.childY[Index];
                        variables[WIDTH] = getChildWidth(Window, Index);
                        variables[HEIGHT] = getChildHeight(Window, Index);
                        break;
                    }
                }
                if (mouseX > variables[OFFX] + variables[POSX] && mouseY > variables[OFFY] + variables[POSY] && mouseX < variables[OFFX] + variables[POSX] + variables[WIDTH] && mouseY < variables[OFFY] + variables[POSY] + variables[HEIGHT]) {
                    switch (InterfaceID[WINDOW]) {
                        default:
                            variables[SENSITIVITY] = 30;
                            break;
                    }

                    switch (rot) {
                        case -1:
                            if (RSInterface.interfaceCache[Window.children[variables[CHILD]]].scrollPosition != 0) {
                                RSInterface.interfaceCache[Window.children[variables[CHILD]]].scrollPosition += rot * variables[SENSITIVITY];
                                // Client.needDrawTabArea = true;
                                Client.tabAreaAltered = true;
                            }
                            break;

                        case 1:
                            if (RSInterface.interfaceCache[Window.children[variables[CHILD]]].scrollPosition != RSInterface.interfaceCache[Window.children[variables[CHILD]]].scrollMax - RSInterface.interfaceCache[Window.children[variables[CHILD]]].height) {
                                RSInterface.interfaceCache[Window.children[variables[CHILD]]].scrollPosition += rot * variables[SENSITIVITY];
                            }
                    }

                    RSInterface.interfaceCache[Window.children[variables[CHILD]]].scrollPosition += rot * 30;
                }
            }
            if (InterfaceID[TAB] != -1) {
                RSInterface Tab = RSInterface.interfaceCache[InterfaceID[TAB]];
                if (Tab == null || Tab.children == null) {
                    return;
                }
                variables[OFFX] = getRealScreenWidth() - 218;
                variables[OFFY] = getRealScreenHeight() - 298;

                for (int Index = 0; Index < Tab.children.length; Index++) {
                    if (RSInterface.interfaceCache[Tab.children[Index]].scrollMax > 0) {
                        variables[CHILD] = Index;
                        variables[POSX] = Tab.childX[Index];
                        variables[POSY] = Tab.childY[Index];
                        variables[WIDTH] = getChildWidth(Tab, Index);
                        variables[HEIGHT] = getChildHeight(Tab, Index);
                        break;
                    }
                }
                if (mouseX > variables[OFFX] + variables[POSX] && mouseY > variables[OFFY] + variables[POSY] && mouseX < variables[OFFX] + variables[POSX] + variables[WIDTH] && mouseY < variables[OFFY] + variables[POSY] + variables[HEIGHT]) {
                    switch (InterfaceID[TAB]) {
                        case 962: /* Music Tab */
                            variables[SENSITIVITY] = 30;
                            break;

                        case 638: /* Quest Tab */
                            variables[SENSITIVITY] = 30;
                            break;

                        case 1151: /* Magic Tab */
                            variables[SENSITIVITY] = 7;
                            break;

                        case 147: /* Emote Tab */
                            variables[SENSITIVITY] = 15;
                            break;

                        default:
                            variables[SENSITIVITY] = 15;
                            break;
                    }

                    switch (rot) {
                        case -1:
                            if (RSInterface.interfaceCache[Tab.children[variables[CHILD]]].scrollPosition != 0) {
                                RSInterface.interfaceCache[Tab.children[variables[CHILD]]].scrollPosition += rot * variables[SENSITIVITY];
                                // Client.needDrawTabArea = true;
                                Client.tabAreaAltered = true;
                            }
                            break;

                        case 1:
                            if (RSInterface.interfaceCache[Tab.children[variables[CHILD]]].scrollPosition != RSInterface.interfaceCache[Tab.children[variables[CHILD]]].scrollMax - RSInterface.interfaceCache[Tab.children[variables[CHILD]]].height) {
                                RSInterface.interfaceCache[Tab.children[variables[CHILD]]].scrollPosition += rot * variables[SENSITIVITY];
                                // Client.needDrawTabArea = true;
                                Client.tabAreaAltered = true;
                            }
                    }
                }
            }
            if (Client.getClient().inputDialogState == 3 && Client.getClient().getGrandExchange().searching) {
                Client.getClient().getGrandExchange().itemResultScrollPos += rot * 10;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public final void keyPressed(KeyEvent keyEvent) {
        String regex = "[a-zA-z\\s]*";
        idleTime = 0;
        int keyCode = keyEvent.getKeyCode();
        int keyChar = keyEvent.getKeyChar();
        if (keyChar == 96) {
            Client.consoleOpen = !Client.consoleOpen;
        }
        //copy pasting below
		/*if ((keyevent.isControlDown() && keyevent.getKeyCode() == KeyEvent.VK_V)) {
			int t1 = Client.inputString.length();
			String phrase = Client.getClipboardContents();
			int t2 = phrase.length();
			Pattern alphanumerical = Pattern.compile(regex);
			boolean hasSpecialChar = alphanumerical.matcher(Client.getClipboardContents()).find();
			if (hasSpecialChar) {
				return;
			}
			if (t1 + t2 > 80) {
				return;
			}
			Client.inputString += Client.getClipboardContents();
			Client.inputTaken = true;
		}*/

        if (keyEvent.isControlDown() && keyEvent.isShiftDown()) {
            Client.controlShiftTeleporting = true;
        }

        if (keyCode == KeyEvent.VK_SHIFT) {
            Client.shiftIsDown = true;
        }
        if (keyCode == KeyEvent.VK_CONTROL) {
            Client.controlIsDown = true;
        }
        if (keyEvent.isControlDown()) {
            if (keyCode == KeyEvent.VK_K) {
                sendCommand("kills");
                keyEvent.consume();
                return;
            } else if (keyCode == KeyEvent.VK_T) {
                sendCommand("teleport");
                keyEvent.consume();
                return;
            } else if (keyCode == KeyEvent.VK_H) {
                sendCommand("home");
                keyEvent.consume();
                return;
            } else if (keyCode == KeyEvent.VK_Z) {
                sendCommand("zones");
                keyEvent.consume();
                return;
            } else if (keyCode == KeyEvent.VK_P) {
                sendCommand("pos");
                keyEvent.consume();
                return;
            } else if (keyCode == KeyEvent.VK_U) {
                sendCommand("upgrade");
                keyEvent.consume();
                return;
            } else if (keyCode == KeyEvent.VK_B) {
                sendCommand("bank");
                keyEvent.consume();
                return;
            }  else if (keyCode == KeyEvent.VK_C) {
                sendCommand("collection");
                keyEvent.consume();
                return;
            } else if (keyCode == KeyEvent.VK_G) {
                sendCommand("gbank");
                keyEvent.consume();
                return;
            } else if (keyCode == KeyEvent.VK_S) {
                sendCommand("seasonpass");
                keyEvent.consume();
                return;
            }else if (keyCode == KeyEvent.VK_A) {
                sendCommand("daily");
                keyEvent.consume();
                return;
            }
        }
        if (keyCode == KeyEvent.VK_ESCAPE && Client.openInterfaceID != -1) {
            Client.instance.closeGameInterfaces();
            return;
        }
        if ((keyEvent.isControlDown() && keyEvent.getKeyCode() == KeyEvent.VK_C)) {
            Client.setClipboardContents(Client.inputString);
            Client.inputTaken = true;
        }
        if ((keyEvent.isControlDown() && keyEvent.getKeyCode() == KeyEvent.VK_X)) {
            Client.setClipboardContents(Client.inputString);
            Client.inputString = "";
            Client.inputTaken = true;
        }
        if ((keyEvent.isControlDown() && keyEvent.getKeyCode() == KeyEvent.VK_Z)) {
            Client.inputString = "";
        }
       /* if ((keyEvent.isControlDown() && keyEvent.getKeyCode() == KeyEvent.VK_A)) {
            String a = "::auth ";
            String replace = Client.getClipboardContents().replaceAll("\t", "").replaceAll("\n", "").replaceAll(" ", "");
            int t1 = replace.length();
            //Pattern alphanumerical = Pattern.compile(regex); //("[^a-zA-Z0-9]");
            boolean hasSpecialChar = false; //alphanumerical.matcher(Client.getClipboardContents()).find();
            if (hasSpecialChar) {
                return;
            }
            if (t1 + a.length() > 80) {
                return;
            }
            Client.inputString = a + replace;
            Client.inputTaken = true;
        }*/
        //copy pasting above copy and pasting copy/past copypasta
        if (!Configuration.NEW_FUNCTION_KEYS) {
            if (keyCode == KeyEvent.VK_ESCAPE) {
                Client.setTab(3);
            } else if (keyCode == KeyEvent.VK_F1) {
                Client.setTab(0);
            } else if (keyCode == KeyEvent.VK_F2) {
                Client.setTab(1);
            } else if (keyCode == KeyEvent.VK_F3) {
                Client.setTab(2);
            } else if (keyCode == KeyEvent.VK_F4) {
                Client.setTab(3);
            } else if (keyCode == KeyEvent.VK_F5) {
                Client.setTab(4);
            } else if (keyCode == KeyEvent.VK_F6) {
                Client.setTab(5);
            } else if (keyCode == KeyEvent.VK_F7) {
                Client.setTab(6);
            } else if (keyCode == KeyEvent.VK_F8) {
                Client.setTab(7);
            } else if (keyCode == KeyEvent.VK_F9) {
                Client.setTab(8);
            } else if (keyCode == KeyEvent.VK_F10) {
                Client.setTab(9);
            } else if (keyCode == KeyEvent.VK_F11) {
                Client.setTab(10);
            } else if (keyCode == KeyEvent.VK_F12) {
                Client.setTab(11);
            }
        } else {
            if (keyCode == KeyEvent.VK_ESCAPE) {
                Client.setTab(10);
            } else if (keyCode == KeyEvent.VK_F1) {
                Client.setTab(3);
            } else if (keyCode == KeyEvent.VK_F2) {
                Client.setTab(4);
            } else if (keyCode == KeyEvent.VK_F3) {
                Client.setTab(5);
            } else if (keyCode == KeyEvent.VK_F4) {
                Client.setTab(6);
            } else if (keyCode == KeyEvent.VK_F5) {
                Client.setTab(0);
            } else if (keyCode == KeyEvent.VK_F6) {
                Client.setTab(1);
            } else if (keyCode == KeyEvent.VK_F7) {
                Client.setTab(2);
            } else if (keyCode == KeyEvent.VK_F8) {
                Client.setTab(7);
            } else if (keyCode == KeyEvent.VK_F9) {
                Client.setTab(8);
            } else if (keyCode == KeyEvent.VK_F10) {
                Client.setTab(9);
            } else if (keyCode == KeyEvent.VK_F11) {
                Client.setTab(11);
            } else if (keyCode == KeyEvent.VK_F12) {
                Client.setTab(12);
            }
        }

        if (keyChar < 30) {
            keyChar = 0;
        }

        if (keyCode == 37) {
            keyChar = 1;
        }

        if (keyCode == 39) {
            keyChar = 2;
        }

        if (keyCode == 38) {
            keyChar = 3;
        }

        if (keyCode == 40) {
            keyChar = 4;
        }

        if (keyCode == 17) {
            keyChar = 5;
        }

        if (keyCode == 8) {
            keyChar = 8;
        }

        if (keyCode == 127) {
            keyChar = 8;
        }

        if (keyCode == 9) {
            keyChar = 9;
        }

        if (keyCode == 10) {
            keyChar = 10;
        }

        if (keyCode >= 112 && keyCode <= 123) {
            keyChar = 1008 + keyCode - 112;
        }

        if (keyCode == 36) {
            keyChar = 1000;
        }

        if (keyCode == 35) {
            keyChar = 1001;
        }

        if (keyCode == 33) {
            keyChar = 1002;
        }

        if (keyCode == 34) {
            keyChar = 1003;
        }

        if (keyChar > 0 && keyChar < 128) {
            keyArray[keyChar] = 1;
        }

        if (keyChar > 4) {
            charQueue[writeIndex] = keyChar;
            writeIndex = writeIndex + 1 & 0x7f;
        }
    }

    private void sendCommand(String cmd) {
        if (Client.instance.loggedIn) {
            synchronized (Client.getOut()) {
                Client.getOut().putOpcode(103);
                Client.getOut().putByte(cmd.length() + 1);
                Client.getOut().putString(cmd);
            }
        }
    }

    @Override
    public final void keyReleased(KeyEvent keyevent) {
        idleTime = 0;
        int keyCode = keyevent.getKeyCode();
        char keyChar = keyevent.getKeyChar();

        if (keyCode == KeyEvent.VK_SHIFT) {
            Client.shiftIsDown = false;
        }
        if (keyCode == KeyEvent.VK_CONTROL) {
            Client.controlIsDown = false;
        }
        if (!keyevent.isControlDown() || !keyevent.isShiftDown()) {
            Client.controlShiftTeleporting = false;
        }

        if (keyChar < '\036') {
            keyChar = '\0';
        }

        if (keyCode == 37) {
            keyChar = '\001';
        }

        if (keyCode == 39) {
            keyChar = '\002';
        }

        if (keyCode == 38) {
            keyChar = '\003';
        }

        if (keyCode == 40) {
            keyChar = '\004';
        }

        if (keyCode == 17) {
            keyChar = '\005';
        }

        if (keyCode == 8) {
            keyChar = '\b';
        }

        if (keyCode == 127) {
            keyChar = '\b';
        }

        if (keyCode == 9) {
            keyChar = '\t';
        }

        if (keyCode == 10) {
            keyChar = '\n';
        }

        if (keyChar > 0 && keyChar < '\200') {
            keyArray[keyChar] = 0;
        }
    }

    @Override
    public final void keyTyped(KeyEvent keyevent) {
    }

    final void method4(int i) {
        delayTime = 1000 / i;
    }

    @Override
    public final void mouseClicked(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();

        /*if (mainFrame != null) {
            Insets insets = mainFrame.getInsets();
            x -= insets.left;// 4
            y -= insets.top;// 22
        }*/
        idleTime = 0;

        clickX = x;
        clickY = y;

        if (clickX == lastClickX && clickY == lastClickY)
            sameSpotClick += 1;
        else
            sameSpotClick = 0;

        if (sameSpotClick >= 10 && sameSpotClick % 10 == 0){
            Client.getOut().putOpcode(203);
            Client.getOut().putShort(sameSpotClick);
        }

        lastClickX = x;
        lastClickY = y;
    }

    @Override
    public final void mouseDragged(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();

        /*if (mainFrame != null) {
            Insets insets = mainFrame.getInsets();
            x -= insets.left;// 4
            y -= insets.top;// 22
        }*/
        if (mouseWheelDown) {
            y = mouseWheelX - mouseevent.getX();
            int k = mouseWheelY - mouseevent.getY();
            mouseWheelDragged(y, -k);
            mouseWheelX = mouseevent.getX();
            mouseWheelY = mouseevent.getY();
            return;
        }

        if (x < 0 || y < 0) {
            return;
        }
        if (System.currentTimeMillis() - clickTime >= 250L || Math.abs(saveClickX - x) > 5 || Math.abs(saveClickY - y) > 5) {
            idleTime = 0;
            mouseX = x;
            mouseY = y;
        }
    }

    @Override
    public final void mouseEntered(MouseEvent mouseevent) {
    }

    @Override
    public final void mouseExited(MouseEvent mouseevent) {
        if (idleTime > 0) {
            idleTime = 0;
        }
        mouseX = -1;
        mouseY = -1;
    }

    @Override
    public final void mouseMoved(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();

       /* if (mainFrame != null) {
            Insets insets = mainFrame.getInsets();
            x -= insets.left;// 4
            y -= insets.top;// 22
        }*/
        if (x < 0 || y < 0) {
            return;
        }
        if (System.currentTimeMillis() - clickTime >= 250L || Math.abs(saveClickX - x) > 5 || Math.abs(saveClickY - y) > 5) {
            idleTime = 0;
            mouseX = x;
            mouseY = y;
        }
    }

    public int mouseWheelX;
    public int mouseWheelY;
    public boolean mouseWheelDown;


    public int sameSpotClick;

    @Override
    public final void mousePressed(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();

        Client.mousePressed = true;

        /*if (mainFrame != null) {
            Insets insets = mainFrame.getInsets();
            x -= insets.left;// 4
            y -= insets.top;// 22
        }*/
        idleTime = 0;

        clickX = x;
        clickY = y;
        clickTime = System.currentTimeMillis();
        // wheel
        int type = mouseevent.getButton();
        if (SwingUtilities.isMiddleMouseButton(mouseevent)) {
            //	if (type == 2) {
            mouseWheelDown = true;
            mouseWheelX = x;
            mouseWheelY = y;
            return;
        }
        // wheel

        //	if (mouseevent.isMetaDown()) {
        if (SwingUtilities.isRightMouseButton(mouseevent)) {
            clickMode1 = 2;
            setClickMode2(2);
            //} else {
        } else if (SwingUtilities.isLeftMouseButton(mouseevent)) {
            clickMode1 = 1;
            setClickMode2(1);
        }
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        idleTime = 0;
        mouseWheelDown = false;
        Client.mousePressed = false;
        setClickMode2(0);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = e.getWheelRotation();
        int xPos = Client.instance.chatArea.getxPos();
        int yPos = Client.instance.chatArea.getyPos();
        //interfaceScrollCheck(e);
        handleInterfaceScrolling(e);
        if (mouseX > xPos && mouseX < xPos + 512 && mouseY > yPos && mouseY < yPos + 140) {
            int ChatPosition = Client.anInt1089;
            ChatPosition -= rotation * 28;

            if (ChatPosition < 0) {
                ChatPosition = 0;
            }

            if (ChatPosition > Client.anInt1211 - 77) {
                ChatPosition = Client.anInt1211 - 77;
            }

            if (Client.anInt1089 != ChatPosition) {
                Client.anInt1089 = ChatPosition;
                Client.instance.setInputTaken(true);
            }
        } else if (Client.instance.loggedIn) {
            boolean zoom = GameFrame.getScreenMode() == ScreenMode.FIXED ? (mouseX < 512) : (mouseX < Client.clientWidth - 200);
            if (zoom && Client.openInterfaceID == -1) {
                Client.clientZoom += rotation * 30;

                if (Client.clientZoom < -630 && !(GameFrame.getScreenMode() == ScreenMode.FIXED)) {
                    Client.clientZoom = -630;
                } else if (Client.clientZoom < -790 && GameFrame.getScreenMode() == ScreenMode.FIXED) {
                    Client.clientZoom = -790;
                }
                if (Client.clientZoom > 1230) {
                    Client.clientZoom = 1230;
                }
            }
            Client.instance.setInputTaken(true);
        }
    }

    public boolean handleInterfaceScrolling(MouseWheelEvent event) {
        int rotation = event.getWheelRotation();
      /*  int positionX = 0;
        int positionY = 0;
        int width = 0;
        int height = 0;
        int childID = 0;

       */
        int offsetX = 0;
        int offsetY = 0;

        int[] positionX1 = new int[5];
        int[] positionY1 = new int[5];
        int[] width1 = new int[5];
        int[] height1 = new int[5];
        int[] offsetX1 = new int[5];
        int[] offsetY1 = new int[5];
        int[] childID1 = new int[5];

        /* Tab interface scrolling */
        int tabInterfaceID = Client.getClient().tabInterfaceIDs[Client.tabID];
        if (tabInterfaceID == 11000) {
            tabInterfaceID = 1151;
        }
        if (tabInterfaceID != -1) {
            RSInterface tab = RSInterface.interfaceCache[tabInterfaceID];
            offsetX = GameFrame.getScreenMode() == ScreenMode.FIXED ? Client.clientWidth - 218 : GameFrame.getScreenMode() == ScreenMode.FIXED ? 28 : Client.clientWidth - 197;
            offsetY = GameFrame.getScreenMode() == ScreenMode.FIXED ? Client.clientHeight - 298 : GameFrame.getScreenMode() == ScreenMode.FIXED ? 37 : Client.clientHeight - (Client.clientWidth >= 900 ? 37 : 74) - 267;
            int off = 0;
            for (int index = 0; index < tab.children.length; index++) {
                if (RSInterface.interfaceCache[tab.children[index]].scrollMax > 0) {
                    childID1[off] = index;
                    positionX1[off] = tab.childX[index];
                    positionY1[off] = tab.childY[index];
                    width1[off] = RSInterface.interfaceCache[tab.children[index]].width;
                    height1[off] = RSInterface.interfaceCache[tab.children[index]].height;
                    off++;
                }
            }

            for (int i = 0; i < childID1.length; i++) {
                if (mouseX > offsetX + positionX1[i] && mouseY > offsetY + positionY1[i] && mouseX < offsetX + positionX1[i] + width1[i] && mouseY < offsetY + positionY1[i] + height1[i]) {
                    if (RSInterface.interfaceCache[tab.children[childID1[i]]].scrollPosition > 0) {
                        RSInterface.interfaceCache[tab.children[childID1[i]]].scrollPosition += rotation * 30;
                        return true;
                    } else {
                        if (rotation > 0) {
                            RSInterface.interfaceCache[tab.children[childID1[i]]].scrollPosition += rotation * 30;
                            return true;
                        }
                    }
                }
            }
        }

        positionX1 = new int[5];
        positionY1 = new int[5];
        width1 = new int[5];
        height1 = new int[5];
        offsetX1 = new int[5];
        offsetY1 = new int[5];
        childID1 = new int[5];
        /* Main interface scrolling */
        if (Client.openInterfaceID != -1) {
            RSInterface rsi = RSInterface.interfaceCache[Client.openInterfaceID];

            offsetX = GameFrame.getScreenMode() == ScreenMode.FIXED ? 4 : Client.clientWidth / 2 - 360;
            offsetY = GameFrame.getScreenMode() == ScreenMode.FIXED ? 4 : Client.clientHeight / 2 - 235;
            int off = 0;
            for (int index = 0; index < rsi.children.length; index++) {
                if (RSInterface.interfaceCache[rsi.children[index]].scrollMax > 0) {
                    childID1[off] = index;
                    positionX1[off] = rsi.childX[index];
                    positionY1[off] = rsi.childY[index];
                    width1[off] = RSInterface.interfaceCache[rsi.children[index]].width;
                    height1[off] = RSInterface.interfaceCache[rsi.children[index]].height;
                    off++;
                }
            }

            for (int i = 0; i < childID1.length; i++) {
                if (mouseX > offsetX + positionX1[i] && mouseY > offsetY + positionY1[i] && mouseX < offsetX + positionX1[i] + width1[i] && mouseY < offsetY + positionY1[i] + height1[i]) {
                    if (RSInterface.interfaceCache[rsi.children[childID1[i]]].scrollPosition > 0) {
                        RSInterface.interfaceCache[rsi.children[childID1[i]]].scrollPosition += rotation * 30;
                        return true;
                    } else {
                        if (rotation > 0) {
                            RSInterface.interfaceCache[rsi.children[childID1[i]]].scrollPosition += rotation * 30;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    @Override
    public final void paint(Graphics g) {
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    void mouseWheelDragged(int param1, int param2) {

    }

    void processDrawing() {
    }

    void processGameLoop() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
    }

    void raiseWelcomeScreen() {
    }

    final int readChar(int dummy) {
        // while (dummy >= 0)
        // {
        // for (int j = 1; j > 0; j++)
        // {
        // }
        // }
        int k = -1;

        if (writeIndex != readIndex) {
            k = charQueue[readIndex];
            readIndex = readIndex + 1 & 0x7f;
        }

        return k;
    }

    private Thread thread;

    @Override
    public void run() {
        if (gameFrame != null) {
            gameFrame.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                    int result = JOptionPane.showConfirmDialog(gameFrame, "Are you sure you want to exit Lunite?",
                            "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        destroy();
                    } else {
                        gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                }
            });
        }
        thread = Thread.currentThread();
        getGameComponent().setFocusTraversalKeysEnabled(true);
        addCanvas();
        try {
            startUp();
            int i = 0;
            int j = 256;
            int k = 1;
            int i1 = 0;
            int j1 = 0;

            for (int k1 = 0; k1 < 10; k1++) {
                aLongArray7[k1] = System.currentTimeMillis();
            }

            while (anInt4 >= 0) {
                if (anInt4 > 0) {
                    anInt4--;

                    if (anInt4 == 0) {
                        exit();
                        return;
                    }
                }

                int i2 = j;
                int j2 = k;
                j = 300;
                k = 1;
                long l1 = System.currentTimeMillis();

                if (aLongArray7[i] == 0L) {
                    j = i2;
                    k = j2;
                } else if (l1 > aLongArray7[i]) {
                    j = (int) (2560 * delayTime / (l1 - aLongArray7[i]));
                }

                if (j < 25) {
                    j = 25;
                }

                if (j > 256) {
                    j = 256;
                    k = (int) (delayTime - (l1 - aLongArray7[i]) / 10L);
                }

                if (k > delayTime) {
                    k = delayTime;
                }

                aLongArray7[i] = l1;
                i = (i + 1) % 10;

                if (k > 1) {
                    for (int k2 = 0; k2 < 10; k2++) {
                        if (aLongArray7[k2] != 0L) {
                            aLongArray7[k2] += k;
                        }
                    }
                }

                if (k < minDelay) {
                    k = minDelay;
                }

                try {
                    Thread.sleep(k);
                } catch (InterruptedException _ex) {
                    j1++;
                }

                for (; i1 < 256; i1 += j) {
                    clickMode3 = clickMode1;
                    saveClickX = clickX;
                    saveClickY = clickY;
                    clickMode1 = 0;
                    processGameLoop();
                    readIndex = writeIndex;
                }

                i1 &= 0xFF;

                if (delayTime > 0) {
                    fps = 1000 * j / (delayTime * 256);
                }
                updateGraphics(false);
                processDrawing();

                if (shouldDebug) {
                    System.out.println("ntime:" + l1);

                    for (int l2 = 0; l2 < 10; l2++) {
                        int i3 = (i - l2 - 1 + 20) % 10;
                        System.out.println("otim" + i3 + ":" + aLongArray7[i3]);
                    }

                    System.out.println("fps:" + fps + " ratio:" + j + " count:" + i1);
                    System.out.println("del:" + k + " deltime:" + delayTime + " mindel:" + minDelay);
                    System.out.println("intex:" + j1 + " opos:" + i);
                    shouldDebug = false;
                    j1 = 0;
                }
            }

            if (anInt4 == -1) {
                exit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void start() {
        if (anInt4 >= 0) {
            anInt4 = 0;
        }
    }

    public void startRunnable(Runnable runnable, int priority) {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(priority);
    }

    void startUp() {
    }

    @Override
    public final void stop() {
        if (anInt4 >= 0) {
            anInt4 = 4000 / delayTime;
        }
    }

    @Override
    public final void update(Graphics graphics) {
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    @Override
    public final void windowActivated(WindowEvent event) {
    }

    @Override
    public final void windowClosed(WindowEvent event) {
    }

    @Override
    public final void windowClosing(WindowEvent event) {
        destroy();
    }

    @Override
    public final void windowDeactivated(WindowEvent event) {
    }

    @Override
    public final void windowDeiconified(WindowEvent event) {
    }

    @Override
    public final void windowIconified(WindowEvent event) {
    }

    @Override
    public final void windowOpened(WindowEvent event) {
    }

    public int getClickMode2() {
        return clickMode2;
    }

    public void setClickMode2(int clickMode2) {
        this.clickMode2 = clickMode2;
    }


    public void setCursor(CursorData cursor) {
        if (Client.getClient().oldCursor != null && Client.getClient().oldCursor == cursor)
            return;
        Sprite sprite = Client.cacheSprite[cursor.sprite];
        Image image = sprite.getImage();
        //Image image = getGameComponent().getToolkit().createImage(FileOperations.ReadFile(signlink.findcachedir() + "Sprites/Cursors/Cursor " + id + ".PNG"));
        getGameComponent().setCursor(getGameComponent().getToolkit().createCustomCursor(image, new Point(0, 0), null));
        Client.getClient().oldCursor = cursor;
    }

    public Thread getClientThread() {
        return thread;
    }

    public boolean isClientThread() {
        return thread == Thread.currentThread();
    }


    public static int max_display_width;
    public static int max_display_height;
    public static int canvas_width;
    public static int canvas_height;
    public static Canvas canvas;
    public static boolean full_redraw;
    public static boolean resize_canvas_requested;

    public static void set_max_display(int max_display_width, int max_display_height) {
        GameRenderer.max_display_width = max_display_width;
        GameRenderer.max_display_height = max_display_height;
        resize_canvas_requested = true;
    }

    public void checkResize() {
        Container container = getGameComponent();
        int width = container.getSize().width;
        int height = container.getSize().height;
        if (myWidth != width || myHeight != height || resize_canvas_requested) {
            resizeCanvas();
            resize_canvas_requested = false;
        }
        if (full_redraw) {
            clear_screen();
            full_redraw = false;
        }
    }

    public void resizeCanvas() {
        Container container = getGameComponent();
        if (container == null) {
            return;
        }
        myWidth = container.getWidth();
        myHeight = container.getHeight();
        if (myWidth <= 0) {
            myWidth = 1;
        }
        if (myHeight <= 0) {
            myHeight = 1;
        }
        canvas_width = Math.min(myWidth, max_display_width);
        canvas_height = Math.min(myHeight, max_display_height);
        canvas_x = (myWidth - canvas_width) / 2;
        canvas_y = 0;
        canvas.setSize(canvas_width, canvas_height);
        Client.clientWidth = canvas_width;
        Client.clientHeight = canvas_height;
        if (Client.instance != null) {
            Client.instance.updateScreen();
        }
        canvas.setLocation(canvas_x, canvas_y);
        clear_screen();
    }

    private synchronized void addCanvas() {
        removeCanvas();
        Container container = getGameComponent();
        canvas = new GameCanvas(container);
        addCanvas(container);
    }

    private void addCanvas(Container container) {
        container.setBackground(Color.black);
        container.setLayout(null);
        container.add(canvas);
        canvas.setSize(canvas_width, canvas_height);
        canvas.setVisible(true);
        canvas.setLocation(canvas_x, canvas_y);
        canvas.addMouseWheelListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addKeyListener(this);
        canvas.addFocusListener(this);
        canvas.requestFocus();
        canvas.setFocusTraversalKeysEnabled(false);
        //full_redraw = true;
    }

    private void removeCanvas() {
        if (canvas != null) {
            canvas.removeMouseWheelListener(this);
            canvas.removeMouseListener(this);
            canvas.removeMouseMotionListener(this);
            canvas.removeKeyListener(this);
            canvas.removeFocusListener(this);
            canvas.getParent().setBackground(Color.black);
            canvas.getParent().remove(canvas);
        }
    }

    public static void clear_screen() {
        int left = canvas_x;
        int top = canvas_y;
        int right = myWidth - canvas_width - left;
        int bottom = myHeight - canvas_height - top;
        if (left > 0 || right > 0 || top > 0 || bottom > 0) {
            try {
                Container container = Client.instance.getGameComponent();
                int x = 0;
                int y = 0;
                Graphics graphics = container.getGraphics();
                graphics.setColor(Color.black);
                if (left > 0) {
                    graphics.fillRect(x, y, left, myHeight);
                }
                if (top > 0) {
                    graphics.fillRect(x, y, myWidth, top);
                }
                if (right > 0) {
                    graphics.fillRect(x + myWidth - right, y, right, myHeight);
                }
                if (bottom > 0) {
                    graphics.fillRect(x, y + myHeight - bottom, myWidth, bottom);
                }
            } catch (Exception e) {
                //  NOOP
            }
        }
    }

}