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
import java.util.ArrayList;
import java.util.Random;

import cluedo.character.Chars;
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
		render_chars(g,as);
		bs.show();
	}
	
	private void render_grid(Graphics2D g, Assets as){
		Tile[][] tiles = as.tiles;
		
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[0].length; j++){
				g.setColor(Color.red);
				Tile tile = tiles[i][j];
				Rectangle2D r2 = new Rectangle2D.Double(tile.getPos().getX(),tile.getPos().getY(),tile.getRatio() , tile.getRatio());
				g.draw(r2);					
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
	
	private void render_chars(Graphics2D g, Assets as){
		ArrayList<Chars> chars = as.chars;
		Tile[][] tiles = as.tiles;

		for(Chars c : chars){
			if(c.isPlayable()){
				Tile t = tiles[c.getPosition().y][c.getPosition().x];
				g.drawImage(c.getSprite(),(int)t.getPos().getX(),(int)t.getPos().getY(),(int)t.getRatio(),(int)t.getRatio(),null);
			}
		}
	}

}
