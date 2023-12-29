package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.format.DateTimeFormatter;

public class Constants {

	// Formats
	public static final DateTimeFormatter showDateFormat  = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm");
	
	// Colors
	public static final Color COLOR_ERROR        = new Color(255,   0,   0);
	public static final Color COLOR_ERROR_BACK   = new Color(255, 200, 200);
	public static final Color COLOR_SUCCESS      = new Color(  0, 200, 100);
	public static final Color COLOR_SUCCESS_BACK = new Color(175, 255, 225);
	public static final Color COLOR_POPUP        = new Color( 50,  50, 200);
	public static final Color COLOR_POPUP_BACK   = new Color(200, 200, 255);
	
	// Fonts
	public static final Font FONT_ACHIEVEMENT_NAME = new Font("Arial Nova Light", Font.BOLD,  20);
	public static final Font FONT_ACHIEVEMENT_DESC = new Font("Arial Nova Light", Font.PLAIN, 16);
	
	public static final Font FONT_INPUT_LABEL = new Font("Arial Nova", Font.BOLD,  20);
	public static final Font FONT_INPUT_TEXT  = new Font("Arial",      Font.PLAIN, 20);
	public static final Font FONT_BUTTON      = new Font("Arial",      Font.PLAIN, 20);
	public static final Font FONT_POPUP       = new Font("Arial",      Font.PLAIN, 17);
	
	// Screen infos
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static int SCREEN_X;
	public static int SCREEN_Y;
	
	
	public static void setup() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
	    
		SCREEN_WIDTH  = (int)size.getWidth(); 
		SCREEN_HEIGHT = (int)size.getHeight(); 
		
		SCREEN_X = (int)size.getWidth()/2 - 1280/2; 
		SCREEN_Y = (int)size.getHeight()/2 - 720/2; 
	}
	
}
