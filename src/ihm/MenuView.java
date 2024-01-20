package ihm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import components.WeeksPanel;
import controller.MenuController;
import db.WeekJDBC;
import objects.Week;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Random;

public class MenuView extends JPanel {
	
	private MenuController controller;
	private WeeksPanel panelSquares;

	public MenuView() {
		
		this.controller = new MenuController(this);
		this.setName("Menu");
		
		///// MAIN PANEL \\\\\		
		setBorder(new EmptyBorder(50, 50, 50, 50));
		setLayout(new BorderLayout(0, 35));	
		
		///// PANEL ACHIEVEMENTS \\\\\
		JPanel panelAchievements = new JPanel();
		panelAchievements.setLayout(new BorderLayout(10, 10));
		add(panelAchievements, BorderLayout.CENTER);
		
		// Achievement title
		JLabel titleAchievements = new JLabel("Life Achievements");
		titleAchievements.setBorder(new MatteBorder(0, 0, 2, 0, Constants.COLOR_BLACK));
		titleAchievements.setFont(Constants.FONT_MENU_TITLE);
		panelAchievements.add(titleAchievements, BorderLayout.NORTH);
		
		// Panel achievements, completion and percentage
		JPanel panelInfosAchievements = new JPanel();
		panelInfosAchievements.setLayout(new BorderLayout(0, 20));
		panelAchievements.add(panelInfosAchievements, BorderLayout.CENTER);
		
		// Achievement completion bar
		JLabel completionBar = new JLabel(" ");
		completionBar.setOpaque(true);
		completionBar.setPreferredSize(new Dimension(getPreferredSize().width, 40));
		completionBar.setBackground(Color.GRAY);
		panelInfosAchievements.add(completionBar, BorderLayout.NORTH);
		
		// Panel latest + title
		JPanel panelLatests = new JPanel();
		panelLatests.setLayout(new BorderLayout(25, 5));
		panelInfosAchievements.add(panelLatests, BorderLayout.CENTER);
		
		// Title latests
		JLabel titleLatests = new JLabel("Latest completed");
		titleLatests.setFont(Constants.FONT_INPUT_LABEL);
		panelLatests.add(titleLatests, BorderLayout.NORTH);
		
		// Panel latest completed
		JPanel panelLatestAchievements = new JPanel();
		panelLatestAchievements.setLayout(new GridLayout(2, 1, 0, 10));
		panelLatests.add(panelLatestAchievements, BorderLayout.CENTER);
		
		// Achievements
		JLabel a1 = new JLabel(" ");
		a1.setOpaque(true);
		a1.setBackground(Color.GRAY);
		panelLatestAchievements.add(a1);
		
		JLabel a2 = new JLabel(" ");
		a2.setOpaque(true);
		a2.setBackground(Color.GRAY);
		panelLatestAchievements.add(a2);
		
		// Panel buttons
		JPanel panelButtonsAchievements = new JPanel();
		panelButtonsAchievements.setLayout(new GridLayout(3, 1, 0, 5));
		panelButtonsAchievements.setPreferredSize(new Dimension(200, getPreferredSize().height));
		panelLatests.add(panelButtonsAchievements, BorderLayout.EAST);
		
		// Buttons
		JButton btnAchievementList = new JButton("Show List");
		btnAchievementList.setFont(Constants.FONT_BUTTON);
		panelButtonsAchievements.add(btnAchievementList);
		
		JButton btnNewAchievement = new JButton("Add New");
		btnNewAchievement.setFont(Constants.FONT_BUTTON);
		panelButtonsAchievements.add(btnNewAchievement);
		
		JButton btnAchievementStats = new JButton("Statistics");
		btnAchievementStats.setFont(Constants.FONT_BUTTON);
		panelButtonsAchievements.add(btnAchievementStats);
		
		
		///// PANEL WEEKS \\\\\
		JPanel panelWeeks = new JPanel();
		panelWeeks.setLayout(new BorderLayout(10, 10));
		panelWeeks.setPreferredSize(new Dimension(getPreferredSize().width, 225));
		add(panelWeeks, BorderLayout.SOUTH);
		
		// Weeks title
		JLabel titleWeeks = new JLabel("A year in weeks");
		titleWeeks.setBorder(new MatteBorder(0, 0, 2, 0, Constants.COLOR_BLACK));
		titleWeeks.setFont(Constants.FONT_MENU_TITLE);
		panelWeeks.add(titleWeeks, BorderLayout.NORTH);
		
		// Panel Weeks, squares stuff, top and buttons
		JPanel panelInfosWeeks = new JPanel();
		panelInfosWeeks.setLayout(new BorderLayout(25, 10));
		panelWeeks.add(panelInfosWeeks, BorderLayout.CENTER);
		
		// Panel weeks with the square stuff and top
		JPanel panelWeeksStats = new JPanel();
		panelWeeksStats.setLayout(new BorderLayout(10, 10));
		panelInfosWeeks.add(panelWeeksStats, BorderLayout.CENTER);
		
		// Panel all squares
		this.panelSquares = new WeeksPanel();
		panelWeeksStats.add(panelSquares, BorderLayout.CENTER);
		
		// Panel ranking
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.GRAY);
		panelWeeksStats.add(panelTop, BorderLayout.EAST);
		
		// Panel buttons
		JPanel panelButtonsWeeks = new JPanel();
		panelButtonsWeeks.setLayout(new GridLayout(3, 1, 0, 5));
		panelButtonsWeeks.setPreferredSize(new Dimension(200, getPreferredSize().height));
		panelInfosWeeks.add(panelButtonsWeeks, BorderLayout.EAST);
		
		// Buttons
		JButton btnWeektList = new JButton("Show List");
		btnWeektList.setFont(Constants.FONT_BUTTON);
		panelButtonsWeeks.add(btnWeektList);
		
		JButton btnNewReview = new JButton("New Review");
		btnNewReview.setFont(Constants.FONT_BUTTON);
		panelButtonsWeeks.add(btnNewReview);
		
		JButton btnWeekStats = new JButton("Statistics");
		btnWeekStats.setFont(Constants.FONT_BUTTON);
		panelButtonsWeeks.add(btnWeekStats);
		
		fillWeekSquares();
	}
	
	private void fillWeekSquares() {
		WeekJDBC weekjdbc = new WeekJDBC();
		List<Week> weeks = weekjdbc.getAll();
		
		for(Week w : weeks) {
			this.panelSquares.setWeekColor(w.getNumber(), w.getColor());
		}
	}
	
	private void testColor() {
		Random r = new Random();
		
		for(int i = 1; i <= 40; i++) {
			float grade = Float.valueOf(r.nextInt(10) + "." + r.nextInt(10));
			
			int red   = (grade > 5) ? Constants.clamp(Math.pow(2, 10-(grade-3.5f)), 10, 200) : 200;
			int green = (grade < 5) ? Constants.clamp(Math.pow(2, grade+3), 10, 200) : 200;
			int blue  = 50;
			
			this.panelSquares.setWeekColor(i, new Color(red, green, blue));
			System.out.println(String.format("[WEEK_%02d] %.2f - (%3d, %3d, %3d)", i, grade, red, green, blue));
		}
	}
}
