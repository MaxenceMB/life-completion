package objects;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Week {
	
	///// ATTRIBUTES \\\\\		
	private String id;			// ID of the week in database
	
	private LocalDateTime date;	// Creation date of the week review
	private String text;		// Week review text
	private float grade;		// Week review grade
	
	
	///// CONSTRUCTORS \\\\\		
	// New object
	public Week(String text, float grade) {
		this.text  = text;
		this.grade = grade;
		
		this.date = LocalDateTime.now();
		this.id   = "WEEK_" + String.format("%02d", date.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
	}
	
	// Full DB
	public Week(String id, LocalDateTime date, String text, float grade) {
		this.id    = id;
		this.date  = date;
		this.text  = text;
		this.grade = grade;
	}
	
	
	///// FUNCTIONS \\\\\\
	public Color getColor() {
		int red   = Math.round((this.grade > 5) ? 150 - 10*(this.grade % 5) : 150);
		int green = Math.round((this.grade < 5) ? 150 - 10*(this.grade % 5) : 150);
		int blue  = 50;
		
		return new Color(red, green, blue);
	}
	
	
	///// GETTERS AND SETTERS \\\\\\
	// ID
	public String getId() {
		return this.id;
	}
	
	// Date
	public LocalDateTime getDate() {
		return this.date;
	}
	
	public int getWeek() {
		return Integer.valueOf(this.id.substring(5));
	}
	
	// Text
	public String getText() {
		return this.text;
	}
	
	public void setText(String newText) {
		this.text = newText;
	}
	
	// Grade
	public float getGrade() {
		return this.grade;
	}
	
	public void setGrade(float newGrade) {
		this.grade = newGrade;
	}
	
	
	///// OVERRIDES \\\\\
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof Week) {
			Week w = (Week) o;
			return this.id.equals(w.id);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %.2f/10 (%d characters)", this.id, this.grade, this.text.toCharArray().length);
	}
	
}
