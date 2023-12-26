package app;

import java.util.List;
import java.util.concurrent.TimeUnit;

import db.AchievementJDBC;
import objects.Achievement;
import objects.AchievementLevel;

public class Main {

	public static void main(String[] args) throws InterruptedException {		
		AchievementJDBC jdbc = new AchievementJDBC();
		
		Achievement startingPoint = new Achievement("Starting Point", "Reached the starting point", AchievementLevel.BRONZE);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement nextLevel = new Achievement("Next Level", "Advanced to the next level", AchievementLevel.SILVER, 10);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement prestigiousStatus = new Achievement("Prestigious Status", "Attained a prestigious status", AchievementLevel.GOLD);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement masteredSkill = new Achievement("Mastered Skill", "Mastered a skill", AchievementLevel.SILVER);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement milestoneAccomplished = new Achievement("Milestone Accomplished", "Accomplished a milestone", AchievementLevel.BRONZE, 20);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement overcomeChallenge = new Achievement("Overcome Challenge", "Overcame a challenge", AchievementLevel.GOLD);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement newHeightReached = new Achievement("New Height Reached", "Reached a new height", AchievementLevel.SILVER);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement secretUnlocked = new Achievement("Secret Unlocked", "Unlocked a secret", AchievementLevel.BRONZE);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement difficultTaskCompleted = new Achievement("Difficult Task Completed", "Completed a difficult task", AchievementLevel.GOLD, 15);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement hiddenKnowledgeDiscovered = new Achievement("Hidden Knowledge Discovered", "Discovered hidden knowledge", AchievementLevel.SILVER);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement performanceExcellence = new Achievement("Performance Excellence", "Excelled in performance", AchievementLevel.BRONZE, 25);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement formidableFoeConquered = new Achievement("Formidable Foe Conquered", "Conquered a formidable foe", AchievementLevel.SILVER);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement marathonCompleted = new Achievement("Marathon Completed", "Completed a marathon", AchievementLevel.GOLD);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement complexPuzzleSolved = new Achievement("Complex Puzzle Solved", "Solved a complex puzzle", AchievementLevel.BRONZE);
		TimeUnit.MILLISECONDS.sleep(1);

		Achievement legendBecame = new Achievement("Legend Became", "Became a legend", AchievementLevel.GOLD, 30);
		TimeUnit.MILLISECONDS.sleep(1);

		jdbc.add(startingPoint);
		jdbc.add(nextLevel);
		jdbc.add(prestigiousStatus);
		jdbc.add(masteredSkill);
		jdbc.add(milestoneAccomplished);
		jdbc.add(overcomeChallenge);
		jdbc.add(newHeightReached);
		jdbc.add(secretUnlocked);
		jdbc.add(difficultTaskCompleted);
		jdbc.add(hiddenKnowledgeDiscovered);
		jdbc.add(performanceExcellence);
		jdbc.add(formidableFoeConquered);
		jdbc.add(marathonCompleted);
		jdbc.add(complexPuzzleSolved);
		jdbc.add(legendBecame);

		
		List<Achievement> list = jdbc.getAll();		
		for(Achievement a : list) {
			System.out.println(a.toString());
		}
	}

}
