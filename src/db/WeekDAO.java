package db;

import java.util.List;

import objects.Week;

public interface WeekDAO extends DAO<Week, String> {
	
	public int getAmountDone();
	public List<Week> getGradeOver(float grade);
	public List<Week> getGradeUnder(float grade);

}
