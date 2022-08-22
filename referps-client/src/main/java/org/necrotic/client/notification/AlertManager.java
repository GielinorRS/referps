package org.necrotic.client.notification;

import org.necrotic.client.Client;

public class AlertManager {
	private org.necrotic.client.notification.AlertBox alertBox;
	public Client client;
	public boolean close = false;

	public AlertManager(Client paramClient) {
		client = paramClient;
	}

	public void close() {
		alertBox.close();
	}

	public void processAlerts() {
		if (alertBox == null) {
			return;
		}
		if (alertBox.done()) {
			alertBox = null;
		} else {
			alertBox.draw();
		}
	}

	public void processMouse(int paramInt1, int paramInt2) {
		if (alertBox == null) {
			return;
		}
		alertBox.processMouse(paramInt1, paramInt2);
	}

	public void setAlert(org.necrotic.client.notification.AlertBox paramAlertBox) {
		alertBox = paramAlertBox;
	}
}
