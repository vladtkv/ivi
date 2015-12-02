package ivi.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ImageComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Dimension dimension;

	public ImageComponent(String imgSrc, Dimension d) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imgSrc));
		} catch (IOException e) {
			e.printStackTrace();
		}
		dimension = d;
		setMinimumSize(dimension);
		setPreferredSize(dimension);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, (int) dimension.getWidth(),
				(int) dimension.getHeight(), null);
	}
}
