package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class ImageLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public ImageLabel() {
		super();
	}
	
	public ImageLabel(String image, int width, int height) {
		super();
		try {
			Image img = ImageIO.read(getClass().getResource(image));
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(img, 0, 0, width, height, null);
			ImageIcon icon = new ImageIcon(bi);
			setIcon(icon);
		} catch (IOException e) {
			setText("Image not found");
			e.printStackTrace();
		}
	}
	
	public void changeImage(String image){
		try {
			Image img = ImageIO.read(getClass().getResource(image));
			BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
			ImageIcon icon = new ImageIcon(bi);
			setIcon(icon);
		} catch (IOException e) {
			setText("Image not found");
			e.printStackTrace();
		}
	}

}

