package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import app.App;
import db.AchievementJDBC;
import ihm.AchievementsView;
import ihm.NewAchievementView;

public class AchievementsController implements ActionListener {

	private AchievementsView view;
	private App app;
	
	public AchievementsController(AchievementsView view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AchievementJDBC jdbc = new AchievementJDBC();
		if(this.app == null) this.app = App.getApp();
		
		if(e.getSource() instanceof JButton ) {
			JButton b = (JButton) e.getSource();
			
			String btnName = "";
			String id = "";
			int underscore =  b.getName().indexOf("_");

			if(underscore != -1) {
				btnName = b.getName().substring(0, underscore);
				id = b.getName().substring(underscore+1);
			} else {
				btnName = b.getName();
			}
			
			switch(btnName) {
			case "Edit":
				System.out.println(b.getName());
				break;
				
			case "Delete":
				jdbc.delete(jdbc.getById(id).get());
				view.fillTable();
				break;
				
			case "Add":
				app.changeView(new NewAchievementView().getName()); 
				app.setLastView(this.view.getName());
				break;
			}
		}
	}

}
