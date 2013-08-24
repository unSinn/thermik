package ch.ma3.thermik;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class ThermikEngine implements InfoProvider {

	private BufferedImage thermikImage;
	private int[][] data;
	private int w;
	private int h;

	public ThermikEngine(BufferedImage heightImage, Gui gui) {
		w = heightImage.getWidth();
		h = heightImage.getHeight();
		thermikImage = new BufferedImage(heightImage.getWidth(),
				heightImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

		convertToDataArray(heightImage);

		gui.addInfoProvider(this);
	}

	private void convertToDataArray(BufferedImage heightImage) {
		BufferedImage grayImage = new BufferedImage(heightImage.getWidth(),
				heightImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		grayImage.getGraphics().drawImage(heightImage, 0, 0, null);

		Raster raster = grayImage.getData();
		data = new int[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				data[x][y] = raster.getSample(x, y, 0);
			}
		}
	}

	public BufferedImage getThermikImage() {
		return thermikImage;
	}

	public void generateThermik() {
		System.out.println("Data: " + data[0][0]);
		thermikImage.getGraphics().drawLine(10, 10, 100, 100);
	}

	@Override
	public String getInfo(int x, int y) {
		if (x < w && y < h) {
			return "GrayValue: " + data[x][y];
		}
		return "Out of range";

	}
}
