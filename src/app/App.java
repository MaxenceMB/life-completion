package app;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import db.ConnectDB;
import ihm.AchievementsView;
import ihm.Constants;
import ihm.MenuView;
import ihm.NewAchievementView;


public class App extends JFrame {

	private JPanel contentPane;
	
	private static String lastView;
	private static App instance;
	
	private MenuView           menu;
	private AchievementsView   achievements;
	private NewAchievementView newAchievement;
	
	
	public static void main(String[] args) {
		ConnectDB.getConnection();
		Constants.setup();
		
		// Starts the app
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					instance = new App();
					instance.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}}}
		);
	}
	
	// Singleton App to access it everywhere
	public static App getApp() {
		if(instance == null) {
			instance = new App();
		}
		return instance;
	}

	
	///// MAIN APP \\\\\
	public App() {
		
		///// WINDOW \\\\\
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Constants.SCREEN_X, Constants.SCREEN_Y, 1280, 720);
		setMinimumSize(new Dimension(1280, 720));
		
		// The panel that changes
		contentPane = new JPanel();
		contentPane.setLayout(new CardLayout());
		setContentPane(contentPane);
		
		// All the different pages
		menu           = new MenuView();            contentPane.add(menu,   		menu.getName());
		achievements   = new AchievementsView(); 	contentPane.add(achievements,   achievements.getName());
		newAchievement = new NewAchievementView(); 	contentPane.add(newAchievement, newAchievement.getName());
	}
	
	
	public void changeView(String viewName) {		
		CardLayout cl = (CardLayout) contentPane.getLayout();
		if(viewName.equals("Achievements")) achievements.fillTable();
		cl.show(contentPane, viewName);
	}
	
	public void setLastView(String name) {
		lastView = name;
	}
	
	public String getLastView() {
		return lastView;
	}

	public AchievementsView getAchievementsView() {
		return achievements;
	}

}
