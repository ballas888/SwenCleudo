package cluedo.render;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{

	public MenuBar(){
		JMenu file = new JMenu("File");
		
		file.add(addExit());
		
		this.add(file);
	}

	private JMenuItem addExit() {
		// TODO Auto-generated method stub
		JMenuItem exit = new JMenuItem("Exit");
		
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
		
		return exit;

	}
}
