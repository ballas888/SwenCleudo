package cluedo.load;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

import cluedo.main.Game.Room;
import cluedo.main.Tile;

public class LoadMap {
	private int width = 0;
	private int height = 0;
	private int size = 0;
	private String cur_layer = null;
	private int layer_level = 0;
	private int tile_count = 0;
	private Tile[][] tiles;
	private BufferedImage image;

	public LoadMap(String col, String rooms){
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

			scan = new Scanner(new File(rooms));
			tile_count = 0;
			while(scan.hasNext()){
				String[] data = scan.nextLine().split(",");
				for(int i = 0; i< width; i++){
					tiles[tile_count][i].set_room(get_room(data[i]));
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

		for(int i = 0; i< tiles.length; i++){
			for(int j = 0; j< tiles[0].length; j++){
				Tile t = tiles[i][j];
				if(t.can_move_left()){
					tiles[i][j].add_neigh(tiles[i][j-1]);
				}
				if(t.can_move_up()){
					tiles[i][j].add_neigh(tiles[i-1][j]);
				}
				if(t.can_move_right()){
					tiles[i][j].add_neigh(tiles[i][j+1]);
				}
				if(t.can_move_down()){
					tiles[i][j].add_neigh(tiles[i+1][j]);
				}
			}
		}
		int posx = 5;
		int posy = 1;

		Tile tile = tiles[posy][posx];
		System.out.println(tile.get_pos().getX()+","+tile.get_pos().getY()+" "+tile.get_room());
		for(int i = 0; i<tile.get_neighs().size();i++){
			System.out.println("	"+tile.get_neighs().get(i).get_pos().getX()+","+tile.get_neighs().get(i).get_pos().getY());
		}
	}

	public int get_width(){
		return this.width;
	}

	public int get_height(){
		return this.height;
	}

	public int get_size(){
		return this.size;
	}

	public Tile[][] get_tiles(){
		return this.tiles;
	}

	private Room get_room(String data){
		Room room = Room.FLOOR;
		int d = Integer.parseInt(data);

		if(d == 1){
			room = Room.KITCHEN;
		}else if(d == 2){
			room = Room.BALL_ROOM;
		}else if(d == 3){
			room = Room.CONSERVATORY;
		}else if(d == 4){
			room = Room.DINING_ROOM;
		}else if(d == 5){
			room = Room.BILLARD_ROOM;
		}else if(d == 6){
			room = Room.LIBRARY;
		}else if(d == 7){
			room = Room.LOUNGE;
		}else if(d == 8){
			room = Room.HALL;
		}else if(d == 9){
			room = Room.STUDY;
		}

		return room;
	}

	private Tile create_tile(int x, int y, String data){
		int d = Integer.parseInt(data);
		Tile tile = new Tile(x,y);
		if(d == 14){
			tile.set_can_down(true);
			tile.set_can_left(true);
			tile.set_can_up(true);
			tile.set_can_right(true);
		}else if(d == 0){
			tile.set_can_down(false);
			tile.set_can_left(false);
			tile.set_can_up(false);
			tile.set_can_right(false);
		}else{
			if(d == 1 || d == 7 || d == 8 || d == 9 || d == 10 || d == 11 || d == 12 || d == 16){
				tile.set_can_up(false);
			}
			if(d == 1 || d == 2 || d == 3 || d == 9 || d == 10 || d == 12 || d == 13 || d == 14){
				tile.set_can_right(false);
			}
			if(d == 3 || d == 4 || d == 5 || d == 9 || d == 11 || d == 12 || d == 13 || d == 16 ){
				tile.set_can_down(false);
			}
			if(d == 5 || d == 6 || d == 7 || d == 9 || d == 10 || d == 11 || d ==12 || d == 15){
				tile.set_can_left(false);
			}
		}

		return tile;
	}
}
