package org.necrotic.client;

public class CardPack {

    public static final int INTERFACE_ID = 141500;
    public static final int INTERFACE_ID2 = 141000;
    private boolean expanding;
    private boolean expandingReward;
    private int scale = 1;
    private int scale2 = 0;

    public CardPack() {
    }

    /*public void spin() {
        spin2();
        if (Client.openInterfaceID != INTERFACE_ID || !expanding) {
            return;
        }
        if (scale <= 128) {
            scale += 5;
        } else
            scale += 6;

        if (scale >= 300) {
            scale = 300;
            expanding = false;
        }
    }*/
    public void spin() {
        if (Client.openInterfaceID != INTERFACE_ID2 || !expandingReward) {
            return;
        }
        if (scale2 <= 128) {
            scale2 += 5;
        } else
            scale2 += 6;

        if (scale2 >= 300) {
            scale2 = 0;
            expandingReward = false;
            Client.instance.sendPacket185(141007);
        }
    }

    public int getScale() {
        if (Client.openInterfaceID == INTERFACE_ID) {
            return 300;
        }
        if (Client.openInterfaceID == INTERFACE_ID2) {
            return scale2;
        }
        return 128;
    }

    public void startExpand() {
        /*if (Client.openInterfaceID == INTERFACE_ID) {
            expanding = true;
            scale = 1;
        }*/
        if (Client.openInterfaceID == INTERFACE_ID2) {
            expandingReward = true;
            scale2 = 1;
        }
    }

}
