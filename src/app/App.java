package app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import db.ConnectDB;
import ihm.MenuView;


public class App extends JFrame {

	private JPanel panel;
	private static App instance;
	
	public static void main(String[] args) {
		ConnectDB.getConnection();
		
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
		setBounds(100, 100, 1280, 720);
		
		// The panel that changes
		panel = new MenuView();
		setContentPane(panel);
	}
	
	
	public void changeView(JPanel view) {
		setContentPane(view);
		validate();
		repaint();
	}

}
