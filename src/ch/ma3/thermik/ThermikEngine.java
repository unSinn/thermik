package ch.ma3.thermik;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class ThermikEngine implements InfoProvider, Runnable {

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

	private void generateThermik() {
		
		
		
		drawThermikImage();
	}

	private void drawThermikImage() {
		Graphics2D g2d = (Graphics2D) thermikImage.getGraphics();
		g2d.setColor(Color.RED);
		g2d.drawLine(10, 10, 100, 100);
	}

	@Override
	public String getInfo(int x, int y) {
		if (x < w && y < h) {
			return "GrayValue: " + data[x][y];
		}
		return "Out of range!";

	}

	@Override
	public void run() {
		while (true) {
			generateThermik();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
