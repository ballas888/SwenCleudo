package cluedo.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import cluedo.render.Render;
import cluedo.render.Screen;

public class Game implements Runnable {		
	
	public static int width = 700;
	public static int height = 525;
	
	public static String title = "Cluedo";//"Cluedo Assignment 1 Swen222 Richard and Jono";
	
	private boolean running = true;
	
	private Screen screen;
	private Render ren;
	
	private Thread thread;
	private JFrame frame;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game(){		
		screen = new Screen(width, height);
		ren = new Render(width, height);
		frame = new JFrame();		
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		// Gets the time when the program is at this line.
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();

		//
		final double ns = 1000000000.0 / 60.0;
		final double mobNs = 1000000000.0 /20.0;

		// Used as the difference of the two times.
		double delta = 0;

		double mobDelta = 0;

		// Obvious is obvious.
		int frames = 0;

		// This is to keep track of how many times its going to call update.
		int updates = 0;
		int mobUpdates = 0;

		// Special code. Makes so that update gets called 60 times a second and
		// render at unlimited frames.
		while (running) {
			long now = System.nanoTime();

			// This gets the difference of the time and then divide by ns. when
			// this equal 1 then it calls update
			// This is also used to make sure that it catches up if something
			// slowed down. say then render slowed down something
			// and delta = 2 then this loop will call update twice to make it
			// catch up.
			delta += (now - lastTime) / ns;
			mobDelta += (now - lastTime) / mobNs;
			lastTime = now;
					

			screen.render(ren);
			frames++;

			// This shows the fps of the rendering and how many times it calls
			// update a second
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames
						+ " fps, " + mobUpdates + " mUps");
				updates = 0;
				frames = 0;
				mobUpdates = 0;
			}
		}
		stop();			
	}
	
	//private int x = 0, y = 0;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game.screen);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
		
	}

}
