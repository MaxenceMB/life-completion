package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import app.App;
import ihm.AchievementsView;
import ihm.MenuView;

public class MenuController implements ActionListener {
	
	private MenuView view;
	
	public MenuController(MenuView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton b = (JButton)e.getSource();
			
			switch(b.getName()) {
				case "Achievements":
					App.getApp().changeView(new AchievementsView()); 
					break;
			}
		}
		
	}

}
