package ihm;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MenuController;

import java.awt.BorderLayout;
import java.awt.Color;

public class MenuView extends JPanel {

	public MenuView() {
		
		setBorder(new EmptyBorder(15, 50, 15, 50));
		setLayout(new BorderLayout(0, 0));		
		
		///// MAIN PANEL \\\\\
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout(0, 0));
		add(panelMain, BorderLayout.CENTER);
		
		
		/// TITLE PANEL \\\
		JPanel panelTitle = new JPanel();
		panelMain.add(panelTitle, BorderLayout.NORTH);
		
		// Button add
		JButton btnAchievementsList = new JButton("Achievements list");
		btnAchievementsList.addActionListener(new MenuController(this));
		btnAchievementsList.setName("Achievements");
		panelTitle.add(btnAchievementsList);
		
		/// TABLE ACHIEVEMENTS \\\			
		JPanel testPanel = new JPanel();
		testPanel.setBackground(Color.BLUE);
		panelMain.add(testPanel, BorderLayout.CENTER);	
	}
}
