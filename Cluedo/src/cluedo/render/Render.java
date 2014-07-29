package cluedo.render;

import java.util.Random;

public class Render {
	private int width,height;
	public int[] pixels;	
	
	public final int MAP_SIZE = 16;
	public final int MAP_SIZE_MASK = MAP_SIZE -1;
	
	//The amount of times that is going to be in this map.
	public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
	
	private Random random = new Random();
	
	public Render(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for(int i = 0; i< MAP_SIZE*MAP_SIZE;i++){
			int colorMinus = random.nextInt(50);
			int color = 255 - colorMinus;
			tiles[i] = color << 16 | color << 8 | color;
			//tiles[i] = //random.nextInt(0xffffff); //Sets a random color to the current tile being rendered.
		}
	}
	
	public void clear(){
		for(int i = 0; i< pixels.length;i++){
			pixels[i] = 0;
		}
	}
	
	public void render(int x1, int y1){
		int tile_size = 3; // Tile size 3 = 8, 4 = 16, 2 = 4. You get the picture
		
		for(int y= 0; y<height;y++){
			
			//The pixels array is a 1d array so this gets the y position of where that pixel would be if it was a 2d array.
			int yy = y+y1; 
			
			//When the tile is out of the screen it doenst render it
			if(y<0 || y >=height) break;			
			for(int x = 0; x<width;x++){
				
				//Same as what yy does but for the x position.
				int xx = x+x1;
				
				//When the tile is out of the screen it doenst render it
				if(x<0 || x>=width) break;
				
				//this gets where the tiles actual position is on the screen. xx >> 4 = xx / 16. bitwise faster.
				int tileIndex = (((xx>>tile_size)& MAP_SIZE_MASK) + ((yy>>tile_size)&MAP_SIZE_MASK) *MAP_SIZE);
				
				//gets the position of x and y in the 1d pixel array.
				//then sets that pixel to what tile that is being called.
				pixels[x+y*width] = tiles[tileIndex];							
			}
		}		
	}
}
