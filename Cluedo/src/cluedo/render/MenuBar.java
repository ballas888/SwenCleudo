package cluedo.render;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import cluedo.main.Data;
import cluedo.main.Game;
/*
 * Menu bar on the canvas
 */
public class MenuBar extends JMenuBar{
	private Data data;
	public MenuBar(Data data){
		this.data = data;
		JMenu file = new JMenu("File");

		file.add(addNewGame());
		file.add(addExit());

		this.add(file);
	}
	/*
	 * Adds exit button to the menu
	 */
	private JMenuItem addExit() {
		JMenuItem exit = new JMenuItem("Exit");

		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

		return exit;

	}
	/*
	 * Adds new game option
	 */
	private JMenuItem addNewGame() {
		JMenuItem newGame = new JMenuItem("New Game");

		newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	data.getFrame().dispose();
                Game game = new Game();
        		game.th.start();

            }
        });

		return newGame;

	}
}
