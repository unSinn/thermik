package ch.ma3.thermik;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String imageName) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			e.printStackTrace();
			img = new BufferedImage(1024, 1024, BufferedImage.TYPE_BYTE_GRAY);
		}
		return img;
	}
}
