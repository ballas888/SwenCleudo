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
		System.out.println("Dimension: "+width +" "+height);
		setSize(size);
		//setPreferredSize(size);
		setFocusable(true);
		image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public void render(BufferedImage img){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {

			// This sets the buffering to do Tripple buffering
			createBufferStrategy(1);
			return;
		}

		//ren.clear();
		//ren.render(0, 0);
		//ren.render_map(img);

//		for (int i = 0; i < pixels.length; i++) {
//			pixels[i] = ren.pixels[i];
//		}

		Graphics g = bs.getDrawGraphics();

		render_map(g, img);
		//g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		//g.dispose();
		bs.show();
	}

	private void render_map(Graphics g, BufferedImage img){
		double width_ratio = getWidth()/img.getWidth();
		double height_ration = getHeight()/img.getHeight();

		System.out.println(getWidth() + " "+ img.getWidth());
		System.out.println(getHeight() + " "+ img.getHeight());
		double ratio = Math.min(width_ratio,height_ration);

		//System.out.println(ratio);

		int image_width = (int) Math.round(img.getWidth()*ratio);
		int image_height = (int) Math.round(img.getHeight()*ratio);

		g.drawImage(img, 0,0,image_width, image_height, null);
	}

	}
