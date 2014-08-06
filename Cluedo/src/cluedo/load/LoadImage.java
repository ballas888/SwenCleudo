package cluedo.load;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadImage {
	public BufferedImage load_map_image(String map){
		BufferedImage img = null;
		InputStream in = this.getClass().getResourceAsStream(map);
		if(in == null){
			System.out.println("Image not found");
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
