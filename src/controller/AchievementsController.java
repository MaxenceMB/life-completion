package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ihm.AchievementsView;

public class AchievementsController implements ActionListener {

	private AchievementsView view;
	
	public AchievementsController(AchievementsView view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton ) {
			JButton b = (JButton) e.getSource();
			
			String btnName = b.getName();
			int underscore = btnName.indexOf("_");

			
			if (underscore != -1) {
				switch(btnName.substring(0, underscore)) {
				case "Edit":
					System.out.println("Edit");
					break;
				case "Delete":
					System.out.println("Delete");
					break;
				}
			}
		}
	}

}
