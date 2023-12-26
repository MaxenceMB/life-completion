package ihm;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import components.ListLayout;
import controller.AchievementsController;
import db.AchievementJDBC;
import objects.Achievement;

public class AchievementsView extends JPanel {
	
	private AchievementsController controller;
	private JPanel listAchievements;

	public AchievementsView() {
		
		this.controller = new AchievementsController(this);
		
		setBorder(new EmptyBorder(15, 150, 15, 150));
		setLayout(new BorderLayout(0, 0));
		
		
		///// MAIN PANEL \\\\\
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout(0, 0));
		add(panelMain, BorderLayout.CENTER);
		
		
		/// TITLE PANEL \\\
		JPanel panelTitle = new JPanel();
		panelMain.add(panelTitle, BorderLayout.NORTH);
		
		// Button add
		JButton btnAdd = new JButton("+");
		btnAdd.setName("Add_");
		btnAdd.addActionListener(controller);
		panelTitle.add(btnAdd);
		
		
		/// TABLE ACHIEVEMENTS \\\			
		listAchievements = new JPanel();
		listAchievements.setLayout(new ListLayout(5));
		
		JScrollPane scrollPaneAchievements = new JScrollPane();
		scrollPaneAchievements.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPaneAchievements.setOpaque(false);
		scrollPaneAchievements.setViewportView(listAchievements);
		scrollPaneAchievements.setSize(scrollPaneAchievements.getViewport().getWidth(), scrollPaneAchievements.getPreferredSize().height);
		scrollPaneAchievements.getVerticalScrollBar().setUnitIncrement(5);
		panelMain.add(scrollPaneAchievements, BorderLayout.CENTER);	
		
		fillTable();
	}
	
	
	public void fillTable() {
		
		// Remove all children if there is
		for(Component c : listAchievements.getComponents()) {
			listAchievements.remove(c);
		}
		
		// All achievements list
		AchievementJDBC jdbc = new AchievementJDBC();
		List<Achievement> list =  jdbc.getAll();
		
		// Make panel for each of them
		for(Achievement a : list) {			
			listAchievements.add(achievementPanel(a));
		}			
		
		validate();
		repaint();
	}
	
	
	private JPanel achievementPanel(Achievement a) {
		
		// THE Panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5, 0));  
		panel.setBackground(Color.LIGHT_GRAY);
		
		// Achievement image on left
		JLabel image = new JLabel("IMG");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setPreferredSize(new Dimension(80, 80));
		image.setOpaque(true);
		image.setBackground(Color.GRAY);
		panel.add(image, BorderLayout.WEST);
		
		// Main panel in the center
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout(0, 2));
		panelMain.setOpaque(false);
		panel.add(panelMain, BorderLayout.CENTER);
			
		// Panel title + description
		JPanel panelInfos = new JPanel();
		panelInfos.setLayout(new GridLayout(0, 1));
		panelInfos.setOpaque(false);
		panelInfos.setBorder(new EmptyBorder(10, 0, 10, 0));
		panelMain.add(panelInfos, BorderLayout.CENTER);
				
		// Achievement title
		JLabel title = new JLabel(a.getTitle());
		title.setFont(Constants.FONT_ACHIEVEMENT_NAME);
		panelInfos.add(title);
					
		// Achievement title
		JLabel description = new JLabel(a.getDescription());
		description.setFont(Constants.FONT_ACHIEVEMENT_DESC);
		panelInfos.add(description);
				
		// Panel Dates
		JPanel panelDates = new JPanel();
		panelDates.setLayout(new GridLayout(0, 1));
		panelDates.setOpaque(false);
		panelDates.setBorder(new EmptyBorder(15, 0, 15, 0));
		panelMain.add(panelDates, BorderLayout.EAST);

		// Achievement create date
		JLabel createDate = new JLabel("Created the " + a.getCreatedDate().format(Constants.showDateFormat));
		createDate.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDates.add(createDate);
					
		// Achievement title
		if(a.isCompleted()) {
			JLabel completeDate = new JLabel("Completed the " + a.getCompletedDate().format(Constants.showDateFormat));
			completeDate.setHorizontalAlignment(SwingConstants.RIGHT);
			panelDates.add(completeDate);
		}
					
		// Buttons
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(2, 1, 0, 5));
		panelButtons.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelButtons.setOpaque(true);
		panelButtons.setBackground(Color.GRAY);
		panel.add(panelButtons, BorderLayout.EAST);
		
		JButton edit = new JButton("!");
		edit.setName("Edit_" + a.getId());
		edit.addActionListener(controller);
		panelButtons.add(edit);
		
		JButton delete = new JButton("X");
		delete.setName("Delete_" + a.getId());
		delete.addActionListener(controller);
		panelButtons.add(delete);
		
		return panel;
	}
}
