package ch.ma3.thermik;

import java.awt.image.BufferedImage;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to Thermik");

		BufferedImage heightImage = ImageLoader
				.loadImage("testdata/switzerland8bit.png");

		Gui gui = new Gui(heightImage);
		ThermikEngine thermikEngine = new ThermikEngine(heightImage, gui);
		gui.setThermikImage(thermikEngine.getThermikImage());

		while (true) {
			thermikEngine.generateThermik();
			gui.repaint();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
