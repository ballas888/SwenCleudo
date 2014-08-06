package cluedo.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cluedo.load.LoadMap;
import cluedo.main.Tile;

public class TestCase_Load {
	@Test
	public void Test_Load_1(){
		LoadMap load = new LoadMap("MapCol", "MapRoom");
		Tile[][] tiles = load.get_tiles();

		int pos1 = 2;
		int pos2 = 1;

		Tile tile = tiles[pos1][pos2];

		assertTrue(tile.get_neighs().get(0).get_pos().getX() == 0);
		assertTrue(tile.get_neighs().get(0).get_pos().getY() == 2);

		assertTrue(tile.get_neighs().get(1).get_pos().getX() == 1);
		assertTrue(tile.get_neighs().get(1).get_pos().getY() == 1);

		assertTrue(tile.get_neighs().get(2).get_pos().getX() == 2);
		assertTrue(tile.get_neighs().get(2).get_pos().getY() == 2);

		assertTrue(tile.get_neighs().get(3).get_pos().getX() == 1);
		assertTrue(tile.get_neighs().get(3).get_pos().getY() == 3);

	}
}
