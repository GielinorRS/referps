package org.necrotic.client;

import java.awt.*;

/**
 * Represents game display area canvas.
 *
 * @author Walied K. Yassen
 */
public final class GameCanvas extends Canvas {

	/**
	 * The component of the game.
	 */
	private final Component component;

	/**
	 * Constructs a new {@link GameCanvas} type object instance.
	 *
	 * @param component the component which this canvas is delegating to.
	 */
	public GameCanvas(Component component) {
		this.component = component;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Graphics g) {
		component.update(g);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint(Graphics g) {
		component.paint(g);
	}
}
