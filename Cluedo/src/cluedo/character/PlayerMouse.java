package cluedo.character;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerMouse implements MouseListener{

	public boolean clicked;	
	public Point point;
	
	public void mouseClicked(MouseEvent e) {		
		
	}

	
	public void mouseEntered(MouseEvent e) {		
		
	}

	
	public void mouseExited(MouseEvent e) {		
		
	}

	
	public void mousePressed(MouseEvent e) {		
		clicked = true;
		point = e.getPoint();
	}

	
	public void mouseReleased(MouseEvent e) {		
		
	}

}
