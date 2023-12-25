package db;

import java.util.List;

import objects.Achievement;

public interface DAOAchievement extends DAO<Achievement, Integer> {
	
	public List<Achievement> getAllBinary();
	public List<Achievement> getAllCompletion();
	public List<Achievement> getAllCompleted();
	public List<Achievement> getAllNotCompleted();
	
}
