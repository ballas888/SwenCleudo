package cluedo.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cluedo.load.Load;
import cluedo.render.Render;
import cluedo.render.Screen;

public class Game implements Runnable {		
	
	public static int width = 16;
	public static int height = 16;
	private int scale = 2;
	
	public static String title = "Cluedo";//"Cluedo Assignment 1 Swen222 Richard the homo and Jono";
	
	private boolean running = true;
	
	private Screen screen;
	private Load load;
	
	
	private Thread thread;
	private JFrame frame;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game(){
		load = new Load("MapCol", "MapRoom");
		screen = new Screen(width*24, height*25);
		
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
					

			screen.render();
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
	

	public enum Room{
		KITCHEN, BALL_ROOM, CONSERVATORY, DINING_ROOM, BILLARD_ROOM,
		LIBRARY, LOUNGE, HALL, STUDY, FLOOR
	}
	//private int x = 0, y = 0;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(true);
		game.frame.setTitle(Game.title);
		//game.screen.setLocation(new Point(200,200));
		JPanel panel = new JPanel(null);
		panel.setPreferredSize(new Dimension(958,620));
		//panel.setLocation(new Point(0,0));
		game.frame.setContentPane(panel);
		game.screen.setLocation((958/2)-(580/2), 40);
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.pink);
		panel2.setSize(new Dimension(189,580));
		panel2.setLocation(new Point(958-189,40));
		
		JPanel card = new JPanel();
		card.setBackground(Color.blue);
		card.setSize(new Dimension(87,156));
		card.setLocation(new Point((int)panel2.getLocation().getX()+5,(int)panel2.getLocation().getY()+5));
		
		JPanel card2 = new JPanel();
		card2.setBackground(Color.green);
		card2.setSize(new Dimension(87,156));
		card2.setLocation(new Point((int)card.getLocation().getX()+card.getWidth()+5,(int)card.getLocation().getY()));
		
		System.out.println(card.getWidth()*2+10);
		
		panel.add(game.screen);
		panel.add(card2);
		panel.add(card);
		panel.add(panel2);
		
		
		//game.frame.add(panel);
		//game.frame.add(game.screen);
		
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
		
	}

}
