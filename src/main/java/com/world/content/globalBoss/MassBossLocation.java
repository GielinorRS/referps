package com.world.content.globalBoss;

import com.ruse.model.Position;

public class MassBossLocation extends Position {

		private String location, hint;

	public MassBossLocation(int x, int y, int z, String location, String hint) {
		super(x, y, z);
		setLocation(location);
		setHint(hint);
	}
	public MassBossLocation(int x, int y, int z, String location) {
		super(x, y, z);
		setLocation(location);
		setHint(location);
	}

		/**
		 *
		 * @return
		 */

		String getLocation() {
			return location;
		}

		String getPlayerPanelHint() {
			return hint;
		}

		/**
		 *
		 * @param location
		 */
		public void setLocation(String location) {
			this.location = location;
		}

		public void setHint(String hint) {
			this.hint = hint;
		}

	}