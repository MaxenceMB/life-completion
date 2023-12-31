package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.App;
import db.AchievementJDBC;
import ihm.NewAchievementView;
import objects.Achievement;
import objects.AchievementLevel;
import objects.AchievementType;

public class NewAchievementController implements ActionListener {
	
	private NewAchievementView view;
	private App app;
	
	public NewAchievementController(NewAchievementView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.app == null) this.app = App.getApp();
		
		// Buttons
		if(e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			
			switch(b.getName()) {
				case "Back":
					app.changeView(app.getLastView());
					if(app.getLastView().equals("Achievements")) 
					app.setLastView(this.view.getName());
					break;
					
				case "Save":
					if(checkInfos()) {						
						JComponent[] fields = this.view.getFields();
						JTextField title = (JTextField) fields[0];
						JTextArea   desc = (JTextArea)  fields[1];
						JComboBox  level = (JComboBox)  fields[2];
						JComboBox   type = (JComboBox)  fields[3];
						JSpinner   steps = (JSpinner)   fields[4];
						
						Achievement a = new Achievement(title.getText(),
														desc.getText(),
														AchievementLevel.valueOf((String)level.getSelectedItem()), 
														AchievementType.valueOf((String)type.getSelectedItem()),
														(type.getSelectedItem().equals("BINARY")) ? 0 : (int)steps.getValue());
						
						new AchievementJDBC().add(a);
						this.view.getPopup().setSuccess("Achievement " + a.getId() + " has been created !");
					}
					break;
			}
			
		// Combo box
		} else if(e.getSource() instanceof JComboBox) {
			JComboBox combo = (JComboBox) e.getSource();
			if(combo.getName().equals("Type")) {
				if(combo.getSelectedItem().equals("COMPLETION")) {
					this.view.setStepsPanelActive(true);
				} else {
					this.view.setStepsPanelActive(false);
				}
			}
			
			// Combo box
			} else if(e.getSource() instanceof JRadioButton) {
				JRadioButton radio = (JRadioButton) e.getSource();
				
				if(radio.getName().equals("StepByStep")) {
					guessSteps();
					this.view.getFields()[4].setEnabled(true);
				} else {
					this.view.getFields()[4].setEnabled(false);
				}
			}
		
	}

	private boolean checkInfos() {
		JComponent[] fields = this.view.getFields();
		
		// Title
		JTextField title = (JTextField) fields[0];
		if(title.getText().isEmpty()) { this.view.getPopup().setError("The title can't be null"); return false; }
		if(title.getText().length() > 100) { this.view.getPopup().setError("The title is too long"); return false; }
		if(new AchievementJDBC().isTitleTaken(title.getText())) { this.view.getPopup().setError("This title is already taken."); return false; }
		
		// Description
		JTextArea description = (JTextArea) fields[1];
		if(description.getText().length() > 500) { this.view.getPopup().setError("The description is too long"); return false; }
		
		// Level
		JComboBox<String> level = (JComboBox<String>) fields[2];
		if(level.getSelectedItem().equals("Select a level...")) { this.view.getPopup().setError("You must select a level"); return false; }
		
		// Type
		JComboBox<String> type = (JComboBox<String>) fields[3];
		if(type.getSelectedItem().equals("Select a type...")) { this.view.getPopup().setError("You must select a type"); return false; }
		// Steps (if needed)
		if(type.getSelectedItem().equals("COMPLETION")) {
			JSpinner steps = (JSpinner) fields[4];
			if((int)steps.getValue() <= 0) { this.view.getPopup().setError("Steps value is incorrect"); return false; }
		}
		
		return true;
	}
	
	
	private void guessSteps() {
		JComponent[] fields = this.view.getFields();
		JTextField title    = (JTextField) fields[0];
		JSpinner steps      = (JSpinner) fields[4];
		
		char[] titleChars = title.getText().toCharArray();
		String num = "";
		boolean numStarted = false;
		for(char c : titleChars) {
			if(Character.isDigit(c)) {
				if(!numStarted) numStarted = true;
				num += c;
			} else if(numStarted) {
				break;
			}
		}
		
		if(num.length() > 0) steps.setValue(Integer.valueOf(num));
	}

}
