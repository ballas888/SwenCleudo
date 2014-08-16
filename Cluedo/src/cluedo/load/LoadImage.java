package cluedo.load;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import cluedo.character.CardName;
import cluedo.character.CharsName;

public class LoadImage {
	public BufferedImage load_map_image(String map){
		BufferedImage img = null;
		ClassLoader classLoader = getClass().getClassLoader();		
		InputStream in = classLoader.getResourceAsStream("cluedo/assets/"+map);
		if(in == null){
			System.out.println("Map Image not found");
			System.exit(0);
		}else{
			try {
				img = ImageIO.read(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return img;
	}
	
	public BufferedImage load_image(CharsName name){
		BufferedImage img = null;
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream in = classLoader.getResourceAsStream("cluedo/assets/"+name+".png");
		if(in == null){
			System.out.println("Map Image not found");
			System.exit(0);
		}else{
			try {
				img = ImageIO.read(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return img;
	}
	
	public BufferedImage load_image(CardName name){
		BufferedImage img = null;
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream in = classLoader.getResourceAsStream("cluedo/assets/"+name+".png");
		if(in == null){
			System.out.println("Map Image not found");
			System.exit(0);
		}else{
			try {
				img = ImageIO.read(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return img;
	}
}
