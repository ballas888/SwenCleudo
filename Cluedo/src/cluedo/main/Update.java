package cluedo.main;

import cluedo.character.PlayerMouse;
import cluedo.render.Assets;

public class Update {
	public void update(Assets as){
		PlayerMouse mouse = as.screen.get_mouse();
		if(mouse.clicked){
			mouse.clicked = false;
			int targetX = mouse.point.x;
			int targetY = mouse.point.y;
			
			double size = as.grid_size;
			double w = as.screen.getWidth();
			double h = as.screen.getHeight();
			double t_w = as.tiles[0].length*size;
			double t_h = as.tiles.length*size;
			double width_ratio = w/t_w;
			double height_ratio = h/t_h;
			
			double ratio = Math.min(width_ratio, height_ratio);
						
			size =size * ratio;
			int t_w_i = as.tiles[0].length;
			int t_h_i = as.tiles.length;
//			
			double t_w_s = size*t_w_i;
			double t_h_s = size*t_h_i;
			
			double ofset_x = (as.screen.getWidth()/2-(double)t_w_s/2);
			double ofset_y = (as.screen.getHeight()/2-(double)t_h_s/2);
//			System.out.println("mouse: "+targetX+" "+targetY);
//			System.out.println("ScreenS: "+as.screen.getWidth()+" "+as.screen.getHeight());
//			System.out.println("t_w_s: "+t_w_s+" "+t_h_s);
//			System.out.println("ofset: "+ofset_x+" "+ofset_y);
//			System.out.println("Size: "+size);
//			
			
			int tile_x = (int)((targetX-ofset_x)/size);
			int tile_y = (int)((targetY-ofset_y)/size);
			
			if(tile_x >=0 && tile_y< as.tiles.length){
				if(tile_x >=0 && tile_x < as.tiles[0].length){
					Tile tile = as.tiles[tile_y][tile_x];
					System.out.println(tile.get_room());
				}
			}
			
			System.out.println(tile_x+" "+tile_y);
		}
	}
}
