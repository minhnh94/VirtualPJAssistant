package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Image bgImage;

	public ImagePanel(String image){
		try {
            bgImage = ImageIO.read(getClass().getResource(image));
            Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
    		setPreferredSize(size);
    		setMinimumSize(size);
    		setMaximumSize(size);
    		setSize(size);
    		setLayout(null);
    		setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public void changeBg(Image image){
		bgImage = image;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bgImage, 0, 0, null);
	}
}