package view;

import java.awt.Color;
import java.awt.Font;

public class Theme {

	private static Color[] palette = {Color.WHITE, new Color(Integer.parseInt("1A1A1A", 16)),
		new Color(Integer.parseInt("1F93FF", 16)), new Color(Integer.parseInt("E23220", 16)),
		new Color(Integer.parseInt("FFD61F", 16))};
	
	private static Color[] progressBar = {new Color(Integer.parseInt("FF0000", 16)), new Color(Integer.parseInt("FF1C00", 16)),
		new Color(Integer.parseInt("FF3900", 16)), new Color(Integer.parseInt("FF9C00", 16)), new Color(Integer.parseInt("FFFF00", 16)),
		new Color(Integer.parseInt("CCFF00", 16)), new Color(Integer.parseInt("99FF00", 16)),	new Color(Integer.parseInt("66FF00", 16)),
		new Color(Integer.parseInt("33FF00", 16)), new Color(Integer.parseInt("00FF00", 16))};
	
	public static final Font BIGGER_FONT = new Font(Font.DIALOG, Font.BOLD, 30);
	public static final Font SMALLER_FONT = new Font(Font.DIALOG, Font.PLAIN, 18);
	public static final Font EXTRA_SMALLER_FONT = new Font(Font.DIALOG, Font.PLAIN, 14);
	
//	public static final BufferedImage MAIN_ICON = ImageIO.read(getClass().getResource(CommonString.MAINICON));
	
	public static Color getColor(int colorID){
		return palette[colorID];		
	}
	
	public static Color getProbarColor(int colorID){
		return progressBar[colorID];		
	}

}
