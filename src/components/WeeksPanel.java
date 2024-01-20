package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ihm.Constants;

public class WeeksPanel extends JPanel {
	
	private final int SQUARE_NUMBER = 52;
	private List<JLabel> squares;
	
	public WeeksPanel() {
		super();
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		setBorder(new EmptyBorder(-10, -10, -10, -10));
		
		squares = new ArrayList<JLabel>();
		for(int i = 0; i < SQUARE_NUMBER; i++) {
			JLabel square = new JLabel(" ");
			square.setName("WEEK_" + (i+1));
			square.setOpaque(true);
			square.setBackground(Constants.COLOR_BLANK);
			square.setPreferredSize(new Dimension(40, 40));
			add(square);
			
			squares.add(square);
		}
	}
	
	public void setWeekColor(int weekNumber, Color weekColor) throws IllegalArgumentException {
		if(weekNumber - 1 < 0 || weekNumber - 1 > SQUARE_NUMBER) throw new IllegalArgumentException("Week number invalid");
		this.squares.get(weekNumber-1).setBackground(weekColor);
		repaint();
	}

}
