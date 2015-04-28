package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageButton extends JButton {
	private static final long serialVersionUID = 1L;

	private ImageIcon defaultIcon;
	private ImageIcon hoverIcon;
	private boolean state;
	
	/**
	 * Create a normal JButton.
	 * */
	
	public ImageButton(){
		super();
	}
	
	/**
	 * Create a newHoverButton Object.
	 * @param img1 url of normal image of the button
	 * @param img2 url hover image of the button
	 * @param width button width
	 * @param height button height
	 * */
	
	public ImageButton(String img1, String img2){
		super();
		try {
			BufferedImage defaultImg = ImageIO.read(getClass().getResource(img1));
			BufferedImage hoverImg = ImageIO.read(getClass().getResource(img2));
			defaultIcon = new ImageIcon(defaultImg);
			hoverIcon = new ImageIcon(hoverImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		state = false;
		setIcon(defaultIcon);
		setRolloverIcon(hoverIcon);
		setBorder(null);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
	
	/**
	 * Hover the button.
	 * */
	
	public void hover(){
		if(state){
			setIcon(hoverIcon);
		} else {
			setIcon(defaultIcon);
		}
	}
}
