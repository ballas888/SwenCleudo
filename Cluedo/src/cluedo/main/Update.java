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
			
			int t_w = as.tiles.length;
			int t_h = as.tiles[0].length;
			
			int t_w_s = as.grid_size*t_w;
			int t_h_s = as.grid_size*t_h;
			
			int ofset_x = as.screen.getWidth()/2-t_w_s/2;
			int ofset_y = as.screen.getHeight()/2-t_h_s/2;
			
			int tile_x = (int)((targetX)/(double)as.tiles.length);
			int tile_y = (int)((targetY)/(double)as.tiles[0].length);
			
			System.out.println(tile_x+" "+tile_y);
		}
	}
}
