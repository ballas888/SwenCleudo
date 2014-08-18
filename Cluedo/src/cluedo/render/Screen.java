package cluedo.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

import cluedo.character.Chars;
import cluedo.main.Data;
import cluedo.main.Tile;
//Richard

public class Screen extends Canvas{


	public Screen(int width, int height){
		Dimension size = new Dimension(width,height);
		setSize(size);
		setFocusable(true);
		setBackground(new Color(200,200,200));
	}

	public void createBStrategy(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			// This sets the buffering to do Double buffering
			createBufferStrategy(2);
			//return;
		}
	}

	public void render(Data as){
		BufferStrategy bs = getBufferStrategy();
//		if (bs == null) {
//			// This sets the buffering to do Double buffering
//			createBufferStrategy(2);
//			//return;
//		}

		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		//System.out.println("here");
		render_map(g, as);
		//render_grid(g, as);
		render_chars(g,as);
		render_path(g,as);
		bs.show();
	}

	private void render_grid(Graphics2D g, Data as){
		Tile[][] tiles = as.getTiles();

		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[0].length; j++){
				g.setColor(Color.red);
				Tile tile = tiles[i][j];
				Rectangle2D r2 = new Rectangle2D.Double(tile.getPos().getX(),tile.getPos().getY(),tile.getTileSize() , tile.getTileSize());
				g.draw(r2);
			}
		}

	}
	private void render_path(Graphics2D g, Data da){
		ArrayList<Point> points = da.getMousePath();
		Tile[][] tiles = da.getTiles();

		if(points.size()>0){
			for(int i = 0; i < points.size();i++){
				Point point = points.get(i);
				Tile tile = tiles[point.y][point.x];
				double ox = tile.getPos().getX();
				double oy = tile.getPos().getY();
				if(i == 0){
					double pSize = da.getTileSize()/2;
					g.setColor(Color.BLUE);
					double p = da.getTileSize()/2-pSize/2;
					Rectangle2D r2 = new Rectangle2D.Double(p+ox,p+oy,pSize,pSize);
					g.fill(r2);
				}else{
					double pSize = da.getTileSize()/3;
					g.setColor(Color.GREEN);
					double p = da.getTileSize()/2-pSize/2;
					Rectangle2D r2 = new Rectangle2D.Double(p+ox,p+oy,pSize,pSize);
					g.fill(r2);
				}


			}
		}
	}

	private void render_map(Graphics2D g, Data as){
		BufferedImage img = as.getMap_image();
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

	private void render_chars(Graphics2D g, Data as){
		ArrayList<Chars> chars = as.getPlayChars();
		Tile[][] tiles = as.getTiles();
		//System.out.println("here render char");
		for(Chars c : chars){
			//if(c.isPlayable()){
				Tile t = tiles[c.getPosition().y][c.getPosition().x];
				int x = (int)Math.round(t.getPos().getX());
				int y = (int)Math.round(t.getPos().getY());
				g.drawImage(c.getSprite(),x,y,(int)as.getTileSize(),(int)as.getTileSize(),null);
			//}
		}
	}

}
