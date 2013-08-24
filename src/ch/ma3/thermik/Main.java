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

		Renderer renderer = new Renderer(gui);
		Thread renderThread = new Thread(renderer);
		renderThread.start();

		Thread thermikThread = new Thread(thermikEngine);
		thermikThread.start();

	}
}
