package app;

import java.util.ArrayList;
import java.util.List;

import db.JDBCAchievement;

import objects.Achievement;
import objects.Level;

public class Main {

	public static void main(String[] args) {
		Achievement test1 = new Achievement("A 1", "This is an achievement test.", Level.BRONZE);
		Achievement test2 = new Achievement("Achiement test 2", "This is an achievement test.", Level.SILVER, 5);
					test2.stepDone(2);
		Achievement test3 = new Achievement("Achievem 3", "This is an achievement test.", Level.GOLD);
					test3.complete();
		Achievement test4 = new Achievement("A seesffes1", "This is an achievement test.", Level.BRONZE);
		Achievement test5 = new Achievement("efsqefesfsefsfes test ", "This is an achievement test.", Level.SILVER, 5);
					test5.stepDone(5);
		Achievement test6 = new Achievement("aaaaaaaaaaaa 3", "This is an achievement test.", Level.GOLD);
					test6.complete();
		
		JDBCAchievement jdbc = new JDBCAchievement();
		jdbc.add(test1);
		jdbc.add(test2);
		jdbc.add(test3);
		jdbc.add(test4);
		jdbc.add(test5);
		jdbc.add(test6);
		
		List<Achievement> list = new ArrayList<Achievement>();
		
		System.out.println("\n\nGET ALL");
		list = jdbc.getAll();
		for(Achievement a : list) {
			System.out.println(a.toDetailedString());
		}
		
		System.out.println("\n\nGET BINARY");
		list = jdbc.getAllBinary();
		for(Achievement a : list) {
			System.out.println(a.toDetailedString());
		}
		
		System.out.println("\n\nGET COMPLETION");
		list = jdbc.getAllCompletion();
		for(Achievement a : list) {
			System.out.println(a.toDetailedString());
		}
		
		System.out.println("\n\nGET COMPLETED");
		list = jdbc.getAllCompleted();
		for(Achievement a : list) {
			System.out.println(a.toDetailedString());
		}
		
		System.out.println("\n\nGET NOT COMPLETED");
		list = jdbc.getAllNotCompleted();
		for(Achievement a : list) {
			System.out.println(a.toDetailedString());
		}
	}

}
