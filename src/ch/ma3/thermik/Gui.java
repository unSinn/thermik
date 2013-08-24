package ch.ma3.thermik;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame implements MouseMotionListener {

	private static final long serialVersionUID = 1516156985634156156L;
	private BufferedImage heightImage;
	private BufferedImage thermikImage;
	private ArrayList<InfoProvider> infoProviders;
	private String info = "";

	public Gui(BufferedImage heightImage) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Thermik");

		infoProviders = new ArrayList<>();

		this.heightImage = heightImage;
		thermikImage = generatePlaceholderThermikMap();

		this.add(new ImagePanel());

		this.addMouseMotionListener(this);
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

			g2d.drawString(info, 10, 10);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (InfoProvider i : infoProviders) {
			info = i.getInfo(e.getX(), e.getY());
		}
	}
}
