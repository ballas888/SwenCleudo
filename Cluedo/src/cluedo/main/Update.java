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
			
			int t_w = as.tiles[0].length;
			int t_h = as.tiles.length;
			
			int t_w_s = as.grid_size*t_w;
			int t_h_s = as.grid_size*t_h;
			
			System.out.println(t_w_s+" t_s "+t_h_s);
			
			double w = as.screen.getWidth();
			double h = as.screen.getHeight();
			double width_ratio = w/t_w_s;
			double height_ratio = h/t_h_s;
			
			double ratio = Math.min(width_ratio, height_ratio);
			double size = as.grid_size*ratio;
			
			double grid_width = t_w*ratio;
			double grid_height = t_h*ratio;
			
			double pos_x = Math.round((as.screen.getWidth()/2)-(grid_width/2));
			double pos_y = Math.round((as.screen.getHeight()/2)-(grid_height/2));
			
			
			double tile_x = ((targetX - pos_x)/(double)as.tiles[0].length);
			double tile_y = ((targetY - pos_y)/(double)as.tiles.length);
			
			System.out.println(as.grid_size);
			System.out.println(targetX+ "=x    y: " + targetY);
		    System.out.println(tile_x+" "+tile_y + "     offsetX: " + pos_x + "   offsetY: " + pos_y);
		}
	}
}
