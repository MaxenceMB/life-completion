package db;

import java.util.List;

import objects.Achievement;

public interface AchievementDAO extends DAO<Achievement, String> {
	
	public List<Achievement> getAllBinary();
	public List<Achievement> getAllCompletion();
	public List<Achievement> getAllCompleted();
	public List<Achievement> getAllNotCompleted();
	
}
