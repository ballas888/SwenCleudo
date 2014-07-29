package cluedo.render;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class Screen extends Canvas{
	private Render ren;
	
	private BufferedImage image;// = new BufferedImage(width, height,
			//BufferedImage.TYPE_INT_RGB);

	// The pixels of the program
	private int[] pixels;// = ((DataBufferInt) image.getRaster().getDataBuffer())
			//.getData();
	
	public Screen(int width, int height){
		Dimension size = new Dimension(580, 580);
		ren = new Render(width, height);
		setSize(size);
		//setPreferredSize(size);
		setFocusable(true);
		image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB); 
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {

			// This sets the buffering to do Tripple buffering
			createBufferStrategy(3);
			return;
		}
		
		ren.clear();
		ren.render(0, 0);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = ren.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
}
