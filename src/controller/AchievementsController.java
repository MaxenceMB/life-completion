package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import app.App;
import db.AchievementJDBC;
import ihm.AchievementsView;
import ihm.NewAchievementView;
import objects.Achievement;

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
			
			// Sorts
			if(btnName.substring(btnName.length()-5).equals("Order")) {
				if(!b.getBackground().equals(Color.LIGHT_GRAY)) {
					for(JButton bs : this.view.getSortButtons()) {
						bs.setBackground(Color.WHITE);
					}
					b.setBackground(Color.LIGHT_GRAY);
				} else {
					String bText = (b.getText().substring(b.getText().length()-1).equals("▲")) ? 
						    b.getText().substring(0, b.getText().length()-1) + "▼" :
						    b.getText().substring(0, b.getText().length()-1) + "▲";
					b.setText(bText);
				}
				
				
				switch(btnName) {
					case "DateOrder":						
						if(b.getText().substring(b.getText().length()-1).equals("▲")) {
							this.view.getList().sort(Achievement.newerOnTopComparator());
						} else {
							this.view.getList().sort(Achievement.newerOnTopComparator().reversed());
						}
						this.view.fillTable();
						break;
						
					case "TypeOrder":						
						if(b.getText().substring(b.getText().length()-1).equals("▲")) {
							this.view.getList().sort(Achievement.binaryOnTopComparator());
						} else {
							this.view.getList().sort(Achievement.binaryOnTopComparator().reversed());
						}
						this.view.fillTable();
						break;
					
					case "LevelOrder":						
						if(b.getText().substring(b.getText().length()-1).equals("▲")) {
							this.view.getList().sort(Achievement.harderOnTopComparator());
						} else {
							this.view.getList().sort(Achievement.harderOnTopComparator().reversed());
						}
						this.view.fillTable();
						break;
					
					case "CompleteOrder":						
						if(b.getText().substring(b.getText().length()-1).equals("▲")) {
							this.view.getList().sort(Achievement.completedOnTopComparator());
						} else {
							this.view.getList().sort(Achievement.completedOnTopComparator().reversed());
						}
						this.view.fillTable();
						break;
				}
				
			// Actions
			} else {
				switch(btnName) {
					case "Edit":
						System.out.println(b.getName());
						break;
						
					case "Delete":
						jdbc.delete(jdbc.getById(id).get());
						this.view.fillTable();
						break;
						
					case "Add":
						app.changeView(new NewAchievementView().getName()); 
						app.setLastView(this.view.getName());
						break;
				}
				
			}
			
		}
	}

}
