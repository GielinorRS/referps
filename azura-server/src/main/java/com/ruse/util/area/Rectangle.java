package com.ruse.util.area;


import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Shape {

	private Position northEast;

	private Position southWest;

	public Rectangle(Position northEast, Position southWest) {
		areas(new Position[] { northEast, southWest }).type(ShapeType.RECTENGLE);
		this.northEast = northEast;
		this.southWest = southWest;
	}

	@Override
	public boolean inside(Position Position) {

		if (areas()[0].getX() <= Position.getX() || areas()[1].getX() >= Position.getX())
			return false;

		if (areas()[0].getY() <= Position.getY() || areas()[1].getY() >= Position.getY())
			return false;

		return true;
	}

	public boolean inside(Position Position, int z) {
		if (areas()[0].getX() <= Position.getX() || areas()[1].getX() >= Position.getX())
			return false;

		if (areas()[0].getY() <= Position.getY() || areas()[1].getY() >= Position.getY())
			return false;

		if (areas()[0].getZ() != z)
			return false;

		return true;
	}

	public List<Position> getBorder(Position Position) {
		List<Position> border = new ArrayList<>();
		if (areas()[0].getX() - 1 <= Position.getX() || areas()[1].getX() + 1 >= Position.getX())
			border.add(Position);

		if (areas()[0].getY() - 1 <= Position.getY() || areas()[1].getY() + 1 >= Position.getY())
			border.add(Position);
		return border;
	}

	public int getNumberOfPlayers() {
		int count = 0;
		for (Player player : World.getPlayers()) {
			if (inside(player.getPosition())) {
				count++;
			}
		}
		return count;
	}

	public ArrayList<Position> positions() {
		ArrayList<Position> positions = new ArrayList<Position>();

		for (int x = southWest.getX(); x < northEast.getX(); x++)
			for (int y = southWest.getY(); y < northEast.getY(); y++)
				positions.add(new Position(x, y));

		return positions;
	}

	/*public ArrayList<Position> randomPositions(int amount, int z) {
		ArrayList<Position> positions = new ArrayList<Position>();

		int i = 0;

		while (!(i >= amount) && i < getNumberOfTiles()) {
			Position randomPosition = positions().get(Misc.getRandom(positions().size()-1));

			if (positions.contains(randomPosition))
				continue;

			if (RegionManager.getClipping(randomPosition.getX(), randomPosition.getY(), z) == RegionManager.OCEAN_TILE)
				continue;


			positions.add(new Position(randomPosition.getX(), randomPosition.getY(), z));
			i++;
		}

		return positions;
	}

	public ArrayList<Position> randomPositions(int amount, int z, Rectangle exclude) {
		ArrayList<Position> positions = new ArrayList<Position>();

		int i = 0;

		while (!(i >= amount) && i < getNumberOfTiles()) {
			Position randomPosition = positions().get(Misc.getRandom(positions().size()-1));

			if (positions.contains(randomPosition))
				continue;

			if (exclude.inside(randomPosition))
				continue;

			if (RegionManager.getClipping(randomPosition.getX(), randomPosition.getY(), z) == RegionManager.OCEAN_TILE)
				continue;

			positions.add(new Position(randomPosition.getX(), randomPosition.getY(), z));
			i++;
		}

		return positions;
	}*/

	public int getNumberOfTiles() {
		int count = 0;
		for (int x = southWest.getX(); x <= northEast.getX(); x++)
			for (int y = southWest.getY(); y <= northEast.getY(); y++)
				count++;


		return count;
	}

	public Position getNorthEast() {
		return northEast;
	}

	public Position getSouthWest() {
		return southWest;
	}
}
