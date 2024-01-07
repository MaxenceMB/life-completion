package ihm;

import java.awt.GridLayout;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import components.ListLayout;
import controller.AchievementsController;
import db.AchievementJDBC;
import objects.Achievement;
import objects.AchievementLevel;
import objects.AchievementType;

public class AchievementsView extends JPanel {
	
	private AchievementsController controller;
	private JPanel listAchievements;
	private JButton[] sortButtons = new JButton[4];
	
	public List<Achievement> list;

	public AchievementsView() {

		AchievementJDBC jdbc = new AchievementJDBC();
		list =  jdbc.getAll();
		Collections.shuffle(list);
		
		this.controller = new AchievementsController(this);
		this.setName("Achievements");
		
		setBorder(new EmptyBorder(15, 150, 15, 150));
		setLayout(new BorderLayout(0, 0));
		
		
		///// MAIN PANEL \\\\\
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout(0, 0));
		add(panelMain, BorderLayout.CENTER);
		
		
		/// TITLE PANEL \\\
		JPanel panelTitle = new JPanel();
		panelMain.add(panelTitle, BorderLayout.NORTH);
		
		// Order
		JButton dateOrder = new JButton();
		dateOrder.setName("DateOrder");
		dateOrder.setText("Sort by date ▲");
		dateOrder.setFocusable(false);
		dateOrder.setBackground(Color.WHITE);
		dateOrder.addActionListener(controller);
		sortButtons[0] = dateOrder;
		panelTitle.add(dateOrder);
		
		JButton typeOrder = new JButton();
		typeOrder.setName("TypeOrder");
		typeOrder.setText("Sort by type ▲");
		typeOrder.setFocusable(false);
		typeOrder.setBackground(Color.WHITE);
		typeOrder.addActionListener(controller);
		sortButtons[1] = typeOrder;
		panelTitle.add(typeOrder);
		
		JButton levelOrder = new JButton();
		levelOrder.setName("LevelOrder");
		levelOrder.setText("Sort by level ▲");
		levelOrder.setFocusable(false);
		levelOrder.setBackground(Color.WHITE);
		levelOrder.addActionListener(controller);
		sortButtons[2] = levelOrder;
		panelTitle.add(levelOrder);
		
		JButton completeOrder = new JButton();
		completeOrder.setName("CompleteOrder");
		completeOrder.setText("Sort by completion ▲");
		completeOrder.setFocusable(false);
		completeOrder.setBackground(Color.WHITE);
		completeOrder.addActionListener(controller);
		sortButtons[3] = completeOrder;
		panelTitle.add(completeOrder);
		
		// Button add
		JButton btnAdd = new JButton("+");
		btnAdd.setName("Add");
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
		
		// Make panel for each of them
		for(Achievement a : list) {			
			listAchievements.add(achievementPanel(a));
		}			
		
		validate();
		repaint();
	}
	
	
	private JPanel achievementPanel(Achievement a) {
		
		Color borderColor = Color.WHITE;
	     	  if(a.getLevel().equals(AchievementLevel.BRONZE)) borderColor = new Color(205, 127,  50);
	     else if(a.getLevel().equals(AchievementLevel.SILVER)) borderColor = new Color(210, 210, 210);
	     else if(a.getLevel().equals(AchievementLevel.GOLD))   borderColor = new Color(255, 215,   0);
		
		// THE Panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5, 0));  
		panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, 100));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 0, borderColor));
		
		// Achievement image on left
		JLabel image = new JLabel("IMG");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setPreferredSize(new Dimension(100, 100));
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
		String titleText = (a.getType().equals(AchievementType.BINARY)) ? a.getTitle() : a.getTitle() + " (" + a.getStepsDone() + "/" + a.getStepsNeeded() + ")";
		JLabel title = new JLabel(titleText);
		title.setFont(Constants.FONT_ACHIEVEMENT_NAME);
		panelInfos.add(title);
					
		// Achievement title
		JTextArea description = new JTextArea(a.getDescription());
		description.setFont(Constants.FONT_ACHIEVEMENT_DESC);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		description.setOpaque(false);
		description.setEditable(false);
		description.setFocusable(false);
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
	
	public List<Achievement> getList() {
		return list;
	}
	
	public void setList(List<Achievement> list) {
		this.list = list;
	}


	public JButton[] getSortButtons() {
		return sortButtons;
	}
}
