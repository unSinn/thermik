package ch.ma3.thermik;

public class Renderer implements Runnable, InfoProvider {

	private final static long FRAMERATE = 60l;
	private Gui gui;
	private long fps;

	public Renderer(Gui gui) {
		this.gui = gui;
		gui.addInfoProvider(this);
	}

	@Override
	public void run() {
		while (true) {
			long renderStart = System.currentTimeMillis();

			gui.repaint();

			try {
				long renderTime = System.currentTimeMillis() - renderStart;

				long throttle = 1000l / FRAMERATE - renderTime;

				if (throttle > 0) {
					Thread.sleep(throttle);
				} else {
					System.out.println("Can't keep up the framerate");
				}

				fps = 1000l / (System.currentTimeMillis() - renderStart);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public String getInfo(int x, int y) {
		return "FPS: " + fps;
	}
}
