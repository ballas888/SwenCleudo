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

		
	}
}
