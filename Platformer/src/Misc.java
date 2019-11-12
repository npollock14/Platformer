
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Misc {

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Misc.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1); // if image is not loaded dont run game
		}
		return null;
	}

}
