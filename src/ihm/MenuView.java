package ihm;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MenuController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class MenuView extends JPanel {
	
	private MenuController controller;

	public MenuView() {
		
		this.controller = new MenuController(this);
		this.setName("Menu");
		
		setBorder(new EmptyBorder(15, 50, 15, 50));
		setLayout(new BorderLayout(0, 0));		
		
		///// MAIN PANEL \\\\\
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new FlowLayout());
		add(panelMain);
		
		// Button list
		JButton btnAchievementsList = new JButton("Achievements list");
		btnAchievementsList.addActionListener(controller);
		btnAchievementsList.setName("Achievements");
		panelMain.add(btnAchievementsList);
		
		// Button add
		JButton btnNewAchievement = new JButton("Add an Achievement");
		btnNewAchievement.addActionListener(controller);
		btnNewAchievement.setName("New Achievement");
		panelMain.add(btnNewAchievement);
	}
}
