package cluedo.main;

import cluedo.character.PlayerMouse;
import cluedo.render.Assets;

public class Update {
	public void update(Assets as){
		PlayerMouse mouse = as.screen.get_mouse();
		if(mouse.clicked){
			mouse.clicked = false;
			int targetX = mouse.point.x;
			int targetY = mouse.point.y;
			System.out.println(targetX+" "+targetY);
		}
	}
}
