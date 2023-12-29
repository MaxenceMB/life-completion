package components;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ihm.Constants;

public class Popup extends JPanel {
	
	private JLabel text;
	
	public Popup() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		text = new JLabel("");
		text.setFont(Constants.FONT_POPUP);
		add(text);
		
		setEnabled(false);
	}
	
	public void setError(String message) {
		
		this.text.setText(message);
		this.text.setForeground(Constants.COLOR_ERROR);
		
		setBackground(Constants.COLOR_ERROR_BACK);
		setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Constants.COLOR_ERROR));
		
		setVisible(true);
	}
	
	public void setSuccess(String message) {
		
		this.text.setText(message);
		this.text.setForeground(Constants.COLOR_SUCCESS);
		
		setBackground(Constants.COLOR_SUCCESS_BACK);
		setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Constants.COLOR_SUCCESS));
		
		setVisible(true);
	}
	
	public void setPopup(String message) {
		
		this.text.setText(message);
		this.text.setForeground(Constants.COLOR_POPUP);
		
		setBackground(Constants.COLOR_POPUP_BACK);
		setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Constants.COLOR_POPUP));
		
		setVisible(true);
	}

}
