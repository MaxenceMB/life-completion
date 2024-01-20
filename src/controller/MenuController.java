package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import app.App;
import ihm.AchievementsView;
import ihm.MenuView;
import ihm.NewAchievementView;

public class MenuController implements ActionListener {
	
	private MenuView view;
	private App app;
	
	public MenuController(MenuView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.app == null) this.app = App.getApp();
		
		if(e.getSource() instanceof JButton) {
			JButton b = (JButton)e.getSource();
			
			switch(b.getName()) {
				case "Achievements":
					app.changeView(new AchievementsView().getName()); 
					app.setLastView(this.view.getName());
					break;
					
				case "New Achievement":
					app.changeView(new NewAchievementView().getName()); 
					app.setLastView(this.view.getName());
					break;
			}
		}
		
	}
}
