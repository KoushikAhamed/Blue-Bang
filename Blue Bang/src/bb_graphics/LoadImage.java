package bb_graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class LoadImage {
	
	public static BufferedImage image;
	public static BufferedImage player_image;
	public static BufferedImage enemy_image;
	
	public static void init(){
		image= imageLoader("/back.jpg");
		player_image = imageLoader("/p1.png");
		enemy_image = imageLoader("/e3.png");
	}
	
	public static BufferedImage imageLoader(String path){
		try {
			return ImageIO.read(LoadImage.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
