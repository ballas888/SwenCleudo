package cluedo.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cluedo.character.Chars;
import cluedo.load.LoadImage;
import cluedo.load.LoadMap;
import cluedo.render.Assets;
import cluedo.render.Screen;

public class Game implements Runnable {

	public static int width = 16;
	public static int height = 16;
	private int scale = 2;

	public static String title = "Cluedo";//"Cluedo Assignment 1 Swen222 Richard and Jono ";

	private boolean running = true;

	
	private LoadMap load_map;
	private LoadImage load_image;
	private BufferedImage map_image;

	private Thread thread;
	private JFrame frame;
	private JPanel main_panel;
	private Screen screen;
	private Update update;
	private ArrayList<Chars> allChars = new ArrayList<Chars>();
	private ArrayList<Chars> choosenChars = new ArrayList<Chars>();

	
	private Tile[][] tiles;
	private int grid_size;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game(){
		load_map = new LoadMap("MapCol", "MapRoom");
		load_image = new LoadImage();
		
		screen = new Screen(958*0.605, 620*0.935);

		width = load_map.get_width()*load_map.get_size();
		height = load_map.get_height()*load_map.get_size();
		//System.out.println(width+" "+height);
		//screen = new Screen(width, height);
		grid_size = load_map.get_size();
		tiles = load_map.get_tiles();
		allChars = load_map.getChars();
		update = new Update();

		map_image = load_image.load_map_image("CluedoBigMod.png");
		
		double size = grid_size;
		double w = screen.getWidth();
		double h = screen.getHeight();
		double t_w = tiles[0].length*size;
		double t_h = tiles.length*size;
		double width_ratio = w/t_w;
		double height_ratio = h/t_h;
		
		double ratio = Math.min(width_ratio, height_ratio);
		
		double grid_width = t_w*ratio;
		double grid_height = t_h*ratio;
		
		double pos_x = (screen.getWidth()/2)-(grid_width/2);
		double pos_y = (screen.getHeight()/2)-(grid_height/2);
		
		size = size * ratio;
		
		for(int i = 0;i < tiles.length; i++){
			for(int j = 0;j<tiles[0].length;j++){
				tiles[i][j].setPosition(new Point2D.Double(pos_x+(j*size),pos_y+(i*size)));
				tiles[i][j].setRatio(size);
			}
		}

		frame = new JFrame();
		//ChooseChars ch = new ChooseChars(allChars, frame);

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

			while (delta >= 1) {
				Assets as = new Assets();
				as.screen = screen;	
				as.tiles = tiles;
				as.grid_size = grid_size;
				update.update(as);
				updates++;
				delta--;
			}
			
			Assets as = new Assets();
			as.image = map_image;
			as.tiles = tiles;
			as.grid_size = grid_size;
			as.chars = allChars;
			
			//resizing screen
			double m_w = main_panel.getWidth();
			double m_h = main_panel.getHeight();
			double w = m_w*0.605;
			double h = m_h*0.935;
			double x = m_w/2-(int)Math.round(h)/2;
			double y = m_h*0.0645;			
			screen.setSize(new Dimension((int)Math.round(w),(int)Math.round(h)) );
			screen.setLocation(new Point((int)Math.round(x),(int)Math.round(y)));
			
			screen.render(as);

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
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.main_panel = new JPanel(null);
		game.main_panel.setPreferredSize(new Dimension(958,620));
		game.frame.setContentPane(game.main_panel);
		game.screen.setLocation(958/2-game.screen.getWidth()/2,40);
		game.main_panel.add(game.screen);
		//game.screen.setLocation(new Point(200,200));
		//JPanel panel = new JPanel(null);
		//panel.setPreferredSize(new Dimension(958,620));
		
		//System.out.println(580.0/958+" "+580.0/620);
		//panel.setLocation(new Point(0,0));
		//game.frame.setContentPane(panel);
		//game.screen.setLocation((958/2)-(580/2), 40);
//		JPanel panel2 = new JPanel();
//		panel2.setBackground(Color.pink);
//		panel2.setSize(new Dimension(189,580));
//		panel2.setLocation(new Point(958-189,40));
//
//		JPanel card = new JPanel();
//		card.setBackground(Color.blue);
//		card.setSize(new Dimension(87,156));
//		card.setLocation(new Point((int)panel2.getLocation().getX()+5,(int)panel2.getLocation().getY()+5));
//
//		JPanel card2 = new JPanel();
//		card2.setBackground(Color.green);
//		card2.setSize(new Dimension(87,156));
//		card2.setLocation(new Point((int)card.getLocation().getX()+card.getWidth()+5,(int)card.getLocation().getY()));

		//System.out.println(card.getWidth()*2+10);

		//panel.add(game.screen);
		//panel.add(card2);
		//panel.add(card);
		//panel.add(panel2);


		//game.frame.add(panel);
		//game.frame.add(game.screen);

		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();

	}

}
