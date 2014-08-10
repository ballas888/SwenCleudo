package cluedo.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import cluedo.character.PlayerKey;
import cluedo.character.PlayerMouse;
import cluedo.main.Tile;

public class Screen extends Canvas{
	private PlayerKey key;
	private PlayerMouse mouse;

	public Screen(double width, double height){
		Dimension size = new Dimension((int)Math.round(width),(int)Math.round(height));		
		System.out.println("Dimension: "+width +" "+height);
		setSize(size);		
		setFocusable(true);
		setBackground(new Color(200,200,200));
		key = new PlayerKey();
		mouse = new PlayerMouse();
		addKeyListener(key);
		addMouseListener(mouse);
	}
	
	public PlayerKey get_key(){
		return this.key;
	}
	
	public PlayerMouse get_mouse(){
		return this.mouse;
	}

	public void render(Assets as){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			// This sets the buffering to do Double buffering
			createBufferStrategy(2);
			return;
		}	
		
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();			
		render_map(g, as);	
		render_grid(g, as);		
		bs.show();
	}
	
	private void render_grid(Graphics2D g, Assets as){
		Tile[][] tiles = as.tiles;
		double size = as.grid_size;
		double w = getWidth();
		double h = getHeight();
		double t_w = tiles[0].length*size;
		double t_h = tiles.length*size;
		double width_ratio = w/t_w;
		double height_ratio = h/t_h;
		
		double ratio = Math.min(width_ratio, height_ratio);
		
		double grid_width = t_w*ratio;
		double grid_height = t_h*ratio;
		
		double pos_x = (getWidth()/2)-(grid_width/2);
		double pos_y = (getHeight()/2)-(grid_height/2);
		
		size = size * ratio;
		
		
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[0].length; j++){
				g.setColor(Color.red);
				Tile tile = tiles[i][j];
				Rectangle2D r2 = new Rectangle2D.Double(pos_x+(j*size),pos_y+(i*size),size , size);
				if(tile.is_trap()){
					int tx = tile.get_trap_loc().x;
					int ty = tile.get_trap_loc().y;
					Rectangle2D r1 = new Rectangle2D.Double(pos_x+(tx*size),pos_y+(ty*size),size , size);					
					g.fill(r2);	
					g.setColor(Color.blue);
					g.fill(r1);
				}else{
					g.draw(r2);
				}
				
							
			}
		}
		
	}

	private void render_map(Graphics2D g, Assets as){
		BufferedImage img = as.image;
		double w = getWidth();
		double h = getHeight();
		double width_ratio = w/img.getWidth();
		double height_ration = h/img.getHeight();
		double ratio = Math.min(width_ratio,height_ration);			
		
		int image_width = (int) Math.round(img.getWidth()*ratio);
		int image_height = (int) Math.round(img.getHeight()*ratio);
		int pos_x = (int)Math.round((getWidth()>>1)-(image_width>>1));
		int pos_y = (int)Math.round((getHeight()>>1)-(image_height>>1));		
		
		g.drawImage(img, pos_x, pos_y, image_width, image_height, null);		
	}

}
