package app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import db.AchievementJDBC;
import objects.Achievement;
import objects.AchievementLevel;
import objects.AchievementType;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Achievement a1 = new Achievement("a1", "", AchievementLevel.GOLD, AchievementType.BINARY, 0);
		Thread.sleep(10);
		Achievement a2 = new Achievement("a2", "", AchievementLevel.GOLD, AchievementType.BINARY, 0);
		Thread.sleep(10);
		Achievement a3 = new Achievement("a3", "", AchievementLevel.GOLD, AchievementType.BINARY, 0);
		Thread.sleep(10);
		Achievement a4 = new Achievement("a4", "", AchievementLevel.GOLD, AchievementType.BINARY, 0);
		Thread.sleep(10);
		Achievement a5 = new Achievement("a5", "", AchievementLevel.GOLD, AchievementType.BINARY, 0);
		Thread.sleep(10);
		Achievement a6 = new Achievement("a6", "", AchievementLevel.GOLD, AchievementType.BINARY, 0);
		Thread.sleep(10);
		Achievement a7 = new Achievement("a7", "", AchievementLevel.GOLD, AchievementType.BINARY, 0);
		Thread.sleep(10);
		Achievement a8 = new Achievement("a8", "", AchievementLevel.GOLD, AchievementType.BINARY, 0);
		Thread.sleep(10);
		Achievement a9 = new Achievement("a9", "", AchievementLevel.GOLD, AchievementType.BINARY, 0);
		Thread.sleep(10);
		
		
		a3.complete();
		Thread.sleep(100);
		a5.complete();
		Thread.sleep(100);
		a8.complete();
		
		List<Achievement> list = new ArrayList<>();
		list.add(a1);
		list.add(a2);
		list.add(a3);
		list.add(a4);
		list.add(a5);
		list.add(a6);
		list.add(a7);
		list.add(a8);
		list.add(a9);
		
		list.sort(Achievement.newerOnTopComparator());
		System.out.println(list.toString());
	}

}
