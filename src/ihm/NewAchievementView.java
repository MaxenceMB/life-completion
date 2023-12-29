package ihm;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import components.Popup;
import controller.NewAchievementController;
import objects.AchievementLevel;
import objects.AchievementType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class NewAchievementView extends JPanel {
	
	private NewAchievementController controller;
	private Popup popup;
	
	private JTextField nameField;
	private JTextArea  descField;
	private JComboBox<String>  levelField;
	private JComboBox<String>  typeField;
	private JSpinner   stepsField;
	
	private JPanel stepsPanel;
	private ButtonGroup stepsTypeGroup = new ButtonGroup();
	
	public NewAchievementView() {
		
		this.controller = new NewAchievementController(this);
		this.setName("New Achievement");
		
		///// FULL PANEL \\\\\
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		
		
		///// MAIN PANEL \\\\\
		JPanel panelInfos = new JPanel();
		panelInfos.setBorder(new EmptyBorder(50, 150, 100, 150));
		panelInfos.setLayout(new BorderLayout(0, 0));
		add(panelInfos, BorderLayout.CENTER);
		
		// Panel error + title
		JPanel panelTitleError = new JPanel();
		panelTitleError.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelTitleError.setLayout(new BorderLayout(0, 10));
		panelInfos.add(panelTitleError, BorderLayout.NORTH);

		this.popup = new Popup();
		this.popup.setPreferredSize(new Dimension(this.popup.getPreferredSize().width, 30));
		panelTitleError.add(this.popup, BorderLayout.NORTH);
		
		// Panel title
		JPanel panelTitle = new JPanel();
		panelTitle.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelTitle.setLayout(new GridLayout(2, 1, 0, 10));
		panelTitleError.add(panelTitle, BorderLayout.CENTER);
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setFont(Constants.FONT_INPUT_LABEL);
		titleLabel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		panelTitle.add(titleLabel);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setFont(Constants.FONT_INPUT_TEXT);
		nameField.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelTitle.add(nameField);
		
		
		// Panel Desc + Type & Level 
		JPanel panelDescTypeLevel = new JPanel();
		panelDescTypeLevel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelDescTypeLevel.setLayout(new BorderLayout(0, 10));
		panelInfos.add(panelDescTypeLevel, BorderLayout.CENTER);
		
		// Panel desc
		JPanel panelDesc = new JPanel();
		panelDesc.setLayout(new BorderLayout(0, 10));
		panelDescTypeLevel.add(panelDesc, BorderLayout.CENTER);
		
		JLabel descLabel = new JLabel("Description");
		descLabel.setFont(Constants.FONT_INPUT_LABEL);
		descLabel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		panelDesc.add(descLabel, BorderLayout.NORTH);
		
		descField = new JTextArea();
		descField.setWrapStyleWord(true);
		descField.setLineWrap(true);
		descField.setBorder(new EmptyBorder(5, 5, 5, 5));
		descField.setFont(Constants.FONT_INPUT_TEXT);
		panelDesc.add(descField, BorderLayout.CENTER);
		
		
		// Panel type & level
		JPanel panelTypeLevel = new JPanel();
		panelTypeLevel.setBorder(new EmptyBorder(10, 0, 10, 0));
		panelDescTypeLevel.add(panelTypeLevel, BorderLayout.SOUTH);
		panelTypeLevel.setLayout(new GridLayout(2, 2, 10, 10));

		JLabel levelLabel = new JLabel("Level");
		levelLabel.setFont(Constants.FONT_INPUT_LABEL);
		levelLabel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		panelTypeLevel.add(levelLabel);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(Constants.FONT_INPUT_LABEL);
		typeLabel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		panelTypeLevel.add(typeLabel);
		
		levelField = new JComboBox<String>();
		levelField.setFont(Constants.FONT_INPUT_LABEL);
		levelField.setName("Level");
		levelField.addItem("Select a level...");
		for(AchievementLevel al : AchievementLevel.values()) {
			levelField.addItem(al.toString());
		}
		levelField.addActionListener(controller);
		panelTypeLevel.add(levelField);
		
		typeField = new JComboBox<String>();
		typeField.setFont(Constants.FONT_INPUT_LABEL);
		typeField.setName("Type");
		typeField.addItem("Select a type...");
		for(AchievementType at : AchievementType.values()) {
			typeField.addItem(at.toString());
		}
		typeField.addActionListener(controller);
		panelTypeLevel.add(typeField);
		
		
		// Panel steps & buttons
		JPanel panelStepsButtons = new JPanel();
		panelStepsButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelStepsButtons.setLayout(new GridLayout(0, 2, 0, 0));
		panelInfos.add(panelStepsButtons, BorderLayout.SOUTH);
		
		// Panel steps
		JPanel panelStepsDetails = new JPanel();
		panelStepsDetails.setBorder(new EmptyBorder(2, 0, 2, 0));
		panelStepsDetails.setLayout(new BorderLayout(0, 10));
		panelStepsButtons.add(panelStepsDetails);
		
		JLabel stepsLabel = new JLabel("Steps");
		stepsLabel.setFont(new Font("Arial Nova", Font.BOLD, 20));
		stepsLabel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		panelStepsDetails.add(stepsLabel, BorderLayout.NORTH);
		
		// Panel options for steps
		stepsPanel = new JPanel();
		stepsPanel.setLayout(new GridLayout(0, 3, 0, 0));
		panelStepsDetails.add(stepsPanel);
		
		JRadioButton percentageButton = new JRadioButton("Percentage");
		percentageButton.setName("Percentage");
		percentageButton.setFont(Constants.FONT_INPUT_TEXT);
		percentageButton.addActionListener(controller);
		stepsTypeGroup.add(percentageButton);
		stepsPanel.add(percentageButton);
		
		JRadioButton stepbystepButton = new JRadioButton("Step by Step");
		stepbystepButton.setName("StepByStep");
		stepbystepButton.setFont(Constants.FONT_INPUT_TEXT);
		stepbystepButton.addActionListener(controller);
		stepsTypeGroup.add(stepbystepButton);
		stepsPanel.add(stepbystepButton);
		
		stepsField = new JSpinner();
		stepsField.setName("StepsSpinner");
		stepsField.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		stepsField.setFont(new Font("Arial", Font.PLAIN, 20));
		stepsField.setBorder(new EmptyBorder(5, 5, 5, 5));
		stepsPanel.add(stepsField);
		
		// Panel button
		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(new EmptyBorder(30, 30, 0, 0));
		panelButtons.setLayout(new GridLayout(0, 2, 5, 0));
		panelStepsButtons.add(panelButtons);
		
		JButton btnCancel = new JButton("Back");
		btnCancel.setName("Back");
		btnCancel.setFont(Constants.FONT_BUTTON);
		btnCancel.addActionListener(controller);
		panelButtons.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setName("Save");
		btnSave.setFont(Constants.FONT_BUTTON);
		btnSave.addActionListener(controller);
		panelButtons.add(btnSave);
		
		stepsField.setEnabled(false);
		setStepsPanelActive(false);
	}
	
	
	// Set the steps panel active, instead of the spinner
	public void setStepsPanelActive(boolean active) {
		for(Component c : stepsPanel.getComponents()) {
			if(!c.getName().equals("StepsSpinner")) {
				c.setEnabled(active);
			}
		}
	}
	
	// gives all the fields
	public JComponent[] getFields() {
		JComponent[] fields = { nameField, descField, levelField, typeField, stepsField };
		return fields;
	}
	
	// gives the popup panel
	public Popup getPopup() {
		return this.popup;
	}

}
