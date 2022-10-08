package org.necrotic.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import org.necrotic.Configuration;

final class GameShell extends JFrame {

    private static final long serialVersionUID = 1L;

    public GameShell(GameRenderer applet, int width, int height, boolean undecorative, boolean resizable) {
        setTitle(Configuration.CLIENT_NAME);
        setFocusTraversalKeysEnabled(false);
        if (undecorative) {
            setUndecorated(true);
        }
        setResizable(resizable);
        add(applet, BorderLayout.CENTER);
        /*if (width > 0 && height > 0) {
            setSize(width, height);
        } else {*/
        pack();
        /* }*/
        setVisible(true);
        //Insets insets = getInsets();
        //setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
        setLocationRelativeTo(null);
        requestFocus();
        toFront();
        setBackground(Color.BLACK);
        setClientIcon();
    }

    public int getFrameWidth() {
        Insets insets = this.getInsets();
        return getWidth() - (insets.left + insets.right);
    }

    public int getFrameHeight() {
        Insets insets = this.getInsets();
        return getHeight() - (insets.top + insets.bottom);
    }

    public void setClientIcon() {
        try {
            URL url = new URL("https://i.imgur.com/YDaKE78.png");
            Toolkit kit = Toolkit.getDefaultToolkit();
            Image img = kit.createImage(url);
            setIconImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}