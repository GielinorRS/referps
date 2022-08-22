package org.necrotic.client;

public class CasketOpening {

	public static final int INTERFACE_ID = 110000;
	private boolean spinClick;
	private int spins;

	public CasketOpening() {
	}

	public void setSpinClick(boolean spinClick) {
		this.spinClick = spinClick;
	}

	public void spin() {
		if (Client.openInterfaceID != INTERFACE_ID || spinClick == false) {
			return;
		}
		RSInterface items = RSInterface.interfaceCache[110500];

		if (spins < 40) {//400
			shift(items, 10);
		} else if (spins < 100) {//760
			shift(items, 6);
		} else if (spins < 150) {//860
			shift(items, 2);
		} else if (spins < 170) {//880
			shift(items, 1);
		} else {
			spinComplete();
		}
	}

	private void shift(RSInterface items, int shiftAmount) {
		items.childX[0] -= shiftAmount;
		spins++;
	}

	private void spinComplete() {
		spins = 0;
		spinClick = false;
	}

	public void handledPacket34(int frame, int itemId, int amount, int slot) {
		if (Client.openInterfaceID != INTERFACE_ID) {
			return;
		}
		RSInterface items = RSInterface.interfaceCache[frame];
		if (slot >= 0 && slot < items.inv.length) {
			items.inv[slot] = itemId;
			items.invStackSizes[slot] = amount;
		}
	}

	public void reset() {
		if (spinClick) {
			return;
		}
		RSInterface items = RSInterface.interfaceCache[110500];
		items.childX[0] = 7;
	}
}
