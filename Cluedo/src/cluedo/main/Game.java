package cluedo.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import cluedo.main.UpdatePlayer.mFunc;
import cluedo.render.CardHUD;
import cluedo.render.HUD;
import cluedo.render.InfoHud;
import cluedo.render.MenuBar;
import cluedo.render.Screen;

public class Game implements KeyListener, MouseListener, Runnable{

	private int mainWidth = 958;
	private int mainHeight = 620;
	private int screenWidth = 580;
	private int screenHeight = 580;

	//hud math
	private int hudHeight = screenHeight/2;
	private int hudWidth = (mainWidth - screenWidth)/2;
	private int hudX = 0;
	private int hudY = mainHeight - hudHeight;

	//card hud math
	private int cardHudHeight = screenHeight;
	private int cardHudWidth = (mainWidth - screenWidth)/2;
	private int cardHudX = ((mainWidth - screenWidth)/2) + screenWidth;
	private int cardHudY = mainHeight - cardHudHeight;

	//infoHud math
	private int infoHudHeight = screenHeight/2;
	private int infoHudWidth = (mainWidth - screenWidth)/2;
	private int infoHudX = 0;
	private int infoHudY = mainHeight - screenHeight;

	private Thread thread;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private Screen screen;
	private UpdatePlayer updatePlayerMove;
	private static Data data;

	//Hud panels
	private JPanel hud;
	private JPanel cardHud;
	private JPanel infoHud;
	private JMenuBar menu;


	public Game(){
		data = new Data();
		updatePlayerMove = new UpdatePlayer();
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Cluedo");

		mainPanel = new JPanel(null);
		mainPanel.setPreferredSize(new Dimension(mainWidth,mainHeight));

		mainFrame.setContentPane(mainPanel);
		screen = new Screen(screenWidth, screenHeight);
		screen.setLocation(mainWidth/2-screenWidth/2,40);
		screen.addKeyListener(this);
		screen.addMouseListener(this);

		//HUD
		hud = new HUD(hudWidth,hudHeight,new GridLayout(0,1,1,1),data,screen);
		hud.setLocation(hudX, hudY);

		//Pass through card data, which will have chars, which have the cards
		cardHud = new CardHUD(cardHudWidth, cardHudHeight, data, screen);
		cardHud.setLocation(cardHudX, cardHudY);

		infoHud = new InfoHud(cardHudWidth, cardHudHeight, data, screen);
		infoHud.setLocation(infoHudX, infoHudY);

		menu = new MenuBar();


		mainFrame.add(hud);
		mainFrame.add(cardHud);
		mainFrame.add(infoHud);
		mainFrame.setJMenuBar(menu);

		mainPanel.add(screen);

		//wtf
		setUpLoad();

		//let users select their players
		ChooseChars ch = new ChooseChars(data.getAllChars(), mainFrame);
		//populate the playable list with the choosen ones
		data.populateChoosen();

		//wtf
		setUpTilesPos();

		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		screen.createBStrategy();

		screen.requestFocus();
		screen.render(data);


	}

	private void setUpTilesPos() {
		Tile[][] tiles = data.getTiles();
		double size = data.getTileSize();
		double t_w = tiles[0].length*size;
		double t_h = tiles.length*size;
		double width_ratio = screenWidth/t_w;
		double height_ratio = screenHeight/t_h;

		double ratio = Math.min(width_ratio, height_ratio);

		double grid_width = t_w*ratio;
		double grid_height = t_h*ratio;

		double posX = (screenWidth/2)-(grid_width/2);
		double posY = (screenHeight/2)-(grid_height/2);

		size = size * ratio;
		data.setTileSize(size);

		for(int i = 0; i < tiles.length;i++){
			for(int j = 0; j < tiles[0].length; j++){
				tiles[i][j].setPosition(new Point2D.Double(posX+(j*size),posY+(i*size)));
				tiles[i][j].setTileSize(size);
			}
		}
	}

	private void setUpLoad() {
		data.loadMap.loadMap("MapCol", "MapRoom");
		data.setTileSize(data.loadMap.get_size());
		data.setTiles(data.loadMap.get_tiles());
		data.setAllChars(data.loadMap.getChars());
		data.setMap_image(data.loadImage.load_map_image("CluedoBigMod.png"));
		//data.setCurrentPlayer(data.getAllChars().get(0));
	}


	public synchronized void start() {
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
			screen.render(data);
	}

	public void mouseReleased(MouseEvent e) {
		if(data.isSearching()){
			System.out.println("Busy sreaching");
		}else{
			int targetX = e.getX();
			int targetY = e.getY();
			Tile[][] tiles = data.getTiles();
			double size = data.getTileSize();
			double w = screenWidth;
			double h = screenHeight;
			double t_w = tiles[0].length*size;
			double t_h = tiles.length*size;
			double width_ratio = w/t_w;
			double height_ratio = h/t_h;

			double ratio = Math.min(width_ratio, height_ratio);

			size =size * ratio;
			int t_w_i = tiles[0].length;
			int t_h_i = tiles.length;

			double t_w_s = size*t_w_i;
			double t_h_s = size*t_h_i;

			double ofset_x = (screenWidth/2-(double)t_w_s/2);
			double ofset_y = (screenHeight/2-(double)t_h_s/2);

			double tileX = ((targetX-ofset_x)/size);
			double tileY = ((targetY-ofset_y)/size);
			Stack<Point> points = new Stack<Point>();
			ArrayList<Point> pnts = new ArrayList<Point>();
			if(tileX >=0 && tileX <t_w_i && tileY >= 0 && tileY <t_h_i){
				int x =(int) tileX;
				int y = (int) tileY;
				points = updatePlayerMove.updatePlayerMove(mFunc.MOVE_MOUSE, data, new Point(x, y));
				for(Point p : points){
					pnts.add(p);
				}
				data.setMousePath(pnts);
				pnts = new ArrayList<Point>();
				while(!points.isEmpty()){
					Point p = points.pop();
					//System.out.println("Here");
					data.getCurrentPlayer().setPosition(p);
					for(Point pn : points){
						pnts.add(pn);
					}
					data.setMousePath(pnts);
					pnts = new ArrayList<Point>();
					screen.render(data);
					try {
						thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
			screen.render(data);
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			updatePlayerMove.updatePlayerMove(mFunc.MOVE_UP, data);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			updatePlayerMove.updatePlayerMove(mFunc.MOVE_DOWN, data);
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			updatePlayerMove.updatePlayerMove(mFunc.MOVE_LEFT, data);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			updatePlayerMove.updatePlayerMove(mFunc.MOVE_RIGHT, data);
		}

		screen.render(data);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public void mouseEntered(MouseEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void keyTyped(KeyEvent arg0) {}


}
