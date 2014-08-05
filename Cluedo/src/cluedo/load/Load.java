package cluedo.load;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cluedo.main.Tile;

public class Load {
	private int width = 0;
	private int height = 0;
	private int size = 0;
	private String cur_layer = null;
	private int layer_level = 0;
	private int tile_count = 0;
	private Tile[][] tiles;
	
	public Load(String col, String rooms){
		try {
			Scanner scan = new Scanner(new File(col));
			this.size = Integer.parseInt(scan.nextLine());
			this.width = Integer.parseInt(scan.nextLine());
			this.height = Integer.parseInt(scan.nextLine());
			tiles = new Tile[height][width];
			while(scan.hasNext()){
				String[] data = scan.nextLine().split(",");
				for(int i = 0; i< width; i++){
					tiles[tile_count][i] = create_tile(i,tile_count, data[i]);
					
				}
				tile_count++;
			}
			scan.close();
			
//			for(int i = 0; i < tiles.length; i++){
//				for(int j = 0; j< tiles[0].length;j++){
//					System.out.printf(tiles[i][j].get_pos().getX()+", "+tiles[i][j].get_pos().getY()+" | ");
//				}
//				System.out.println();
//			}
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
	}
	
	private Tile create_tile(int x, int y, String data){
		Tile tile = new Tile(x,y);
		
		
		return tile;
	}
}
