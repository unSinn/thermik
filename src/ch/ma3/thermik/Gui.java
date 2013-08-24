package ch.ma3.thermik;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame implements MouseMotionListener, InfoProvider {

	private static final long serialVersionUID = 1516156985634156156L;
	private BufferedImage heightImage;
	private BufferedImage thermikImage;
	private ConcurrentLinkedQueue<InfoProvider> infoProviders;
	private String info = "";
	private int mouseX;
	private int mouseY;

	public Gui(BufferedImage heightImage) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Thermik");

		infoProviders = new ConcurrentLinkedQueue<>();
		addInfoProvider(this);

		this.heightImage = heightImage;
		thermikImage = generatePlaceholderThermikMap();

		ImagePanel imagePanel = new ImagePanel();
		imagePanel.addMouseMotionListener(this);
		this.add(imagePanel);

		setSize(heightImage.getWidth(), heightImage.getHeight() * 2);
		setVisible(true);
	}

	public void addInfoProvider(InfoProvider infoProvider) {
		this.infoProviders.add(infoProvider);
	}

	public void setThermikImage(BufferedImage newThermikImage) {
		this.thermikImage = newThermikImage;
	}

	private BufferedImage generatePlaceholderThermikMap() {
		return new BufferedImage(heightImage.getWidth(),
				heightImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	}

	private class ImagePanel extends JPanel {

		private static final long serialVersionUID = -2773961843687530433L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(heightImage, null, 0, 0);
			g2d.drawImage(thermikImage, null, 0, heightImage.getHeight());

			int line = 20;
			for (InfoProvider i : infoProviders) {
				g2d.drawString(i.getInfo(mouseX, mouseY), 10, line);
				line += 12;
			}

		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// unused
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public String getInfo(int x, int y) {
		return "MouseX: " + mouseX + ", MouseY: " + mouseY;
	}
}
