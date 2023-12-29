package objects;

import java.time.LocalDateTime;

import ihm.Constants;

public class Achievement {
	
	///// ATTRIBUTES \\\\\		
	private String id;					// ID of the achievement
	
	private String  title;				// Title of the achievement
	private String  description;		// Description of the achievement
	private AchievementType  type;   	// Type of the achievement (Binary or Completion)
	private AchievementLevel level;		// Level (Rarity, Difficulty) of the achievement
	private boolean completed;			// True if completed
	
	private LocalDateTime createDate;	// Date of creation
	private LocalDateTime completeDate; // Date when completed
	
	private int stepsNeeded;			// Steps needed to complete achievement (Completion only)
	private int stepsDone;				// Steps already done (Completion only)
	
	
	///// CONSTRUCTORS \\\\\
	// New object
	public Achievement(String title, String desc, AchievementLevel level, AchievementType type, int stepsNeeded) {
		this.title       = title;
		this.description = desc;
		this.type        = type;
		this.level       = level;
		
		this.completed = false;
		
		this.createDate   = LocalDateTime.now();
		this.completeDate = LocalDateTime.MIN;
		
		this.stepsNeeded = stepsNeeded;
		this.stepsDone = 0;
		
		this.id = genId();
	}
	
	// Full (DB)
	public Achievement(String id, String title, String desc, AchievementType type, AchievementLevel level, boolean completed, LocalDateTime createDate, LocalDateTime completeDate, int stepsNeeded, int stepsDone) {
		this.id           = id;
		this.title        = title;
		this.description  = desc;
		this.type         = type;
		this.level        = level;
		this.completed    = completed;
		this.createDate   = createDate;
		this.completeDate = completeDate;
		this.stepsNeeded  = stepsNeeded;
		this.stepsDone    = stepsDone;
	}
	
	
	///// FUNCTIONS \\\\\
	// Generate ID
	private String genId() {
		String id = "";
		
		id += this.type.toString().substring(0, 3);
		id += "_";
		id += this.createDate.getNano();
		
		return id;
	}
	
	// Complete
	public void complete() {
		if(!this.completed) {
			this.completed = true;
			this.completeDate = LocalDateTime.now();
		} else {
			System.out.println("This achievement was already completed.");
		}
	}
	
	// Steps
	public void stepDone() {
		stepDone(1);
	}
	
	public void stepDone(int steps) {
		if(this.type == AchievementType.COMPLETION) {
			this.stepsDone += steps;
			if(this.stepsDone >= this.stepsNeeded) {
				complete();
			}
		} else {
			System.out.println("This achievement doesn't have any steps.");
		}
	}
	
	public int stepLeft() {
		if(this.type == AchievementType.COMPLETION) {
			return this.stepsNeeded - this.stepsDone;
		} else {
			System.out.println("This achievement doesn't have any steps.");
			return -1;
		}
	}
	
	
	///// GETTERS AND SETTERS \\\\\
	// ID
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	// Title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// Description
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Level
	public AchievementLevel getLevel() {
		return level;
	}

	public void setLevel(AchievementLevel level) {
		this.level = level;
	}

	// Completed
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	// Dates
	public LocalDateTime getCompletedDate() {
		return completeDate;
	}

	public void setCompletedDate(LocalDateTime date) {
		this.completeDate = date;
	}
	
	public LocalDateTime getCreatedDate() {
		return this.createDate;
	}
	
	// Type
	public AchievementType getType() {
		return type;
	}

	public void setType(AchievementType type) {
		this.type = type;
	}
	
	// Steps done
	public int getStepsDone() {
		return stepsDone;
	}

	public void setStepsDone(int stepsDone) {
		this.stepsDone = stepsDone;
	}

	// Steps needed
	public int getStepsNeeded() {
		return stepsNeeded;
	}

	public void setStepsNeeded(int stepsNeeded) {
		this.stepsNeeded = stepsNeeded;
	}


	///// OVERRIDES \\\\\
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof Achievement) {
			Achievement a = (Achievement) o;
			return this.title.equals(a.title);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return String.format("[ACHIEVEMENT (%s)] %s (%s)", this.type.toString(), this.title, (this.completed) ? "Completed" : "Not completed");
	}
	
	public String toDetailedString() {
		return String.format("ACHIEVEMENT \n"
				           + "    Title = %s \n"
				           + "     Desc = %s \n"
				           + "     Type = %s \n"
				           + "    Level = %s \n"
				           + "Completed = %s \n"
				           + "Created the %s \n",
				           this.title,
				           this.description,
				           (this.type.toString().equals(AchievementType.BINARY.toString())) ? AchievementType.BINARY.toString() : AchievementType.COMPLETION.toString() + "(" + this.stepsDone + "/" + this.stepsNeeded + ")",
				           this.level.toString(),
				           (this.completed) ? "Completed (" + this.completeDate.format(Constants.showDateFormat) + ")" : "Not completed", 
						   this.createDate.format(Constants.showDateFormat));
	}

}
