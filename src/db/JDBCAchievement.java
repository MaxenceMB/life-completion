package db;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import objects.Achievement;
import objects.AchievementType;
import objects.Constants;
import objects.Level;

public class JDBCAchievement implements DAOAchievement {

	@Override
	public List<Achievement> getAll() {
        List<Achievement> achievements = new ArrayList<>();
		try {
			Statement st = ConnectDB.getConnection().createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM Achievement");
	        
	        while (rs.next()) {
        		achievements.add(new Achievement(rs.getInt("idAchievement"),								// ID
					    		 AchievementType.valueOf(rs.getString("type")),								// Type of achievement
					    		 rs.getString("title"),														// Title
					    		 rs.getString("description"),												// Description
					    		 Level.valueOf(rs.getString("level")),										// Level
					    		 (rs.getInt("completed") == 1) ? true : false,								// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate"), Constants.dateFormat),		// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate"), Constants.dateFormat),	// Completion date
					    		 rs.getInt("stepsNeeded"),													// Steps needed
					    		 rs.getInt("stepsDone"))													// Steps done
        						 );
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return achievements;
	}

	@Override
	public Optional<Achievement> getById(Integer id) {
		Optional<Achievement> achievement = Optional.empty();
		try {
			String req = "SELECT * "
					   + "FROM Achievement "
					   + "WHERE idAchievement = ?";
			
			CallableStatement cs = ConnectDB.getConnection().prepareCall(req);
			cs.setInt(1, id);
			 
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
				achievement = Optional.ofNullable(new Achievement(rs.getInt("idAchievement"),								// ID
			    		 						  AchievementType.valueOf(rs.getString("type")),							// Type of achievement
			    		 						  rs.getString("title"),													// Title
			    		 						  rs.getString("description"),												// Description
			    		 						  Level.valueOf(rs.getString("level")),										// Level
			    		 						  (rs.getInt("completed") == 1) ? true : false,								// Completed or no
			    		 						  LocalDateTime.parse(rs.getString("createDate"), Constants.dateFormat),	// Creation date
			    		 						  LocalDateTime.parse(rs.getString("completeDate"), Constants.dateFormat),	// Completion date
			    		 						  rs.getInt("stepsNeeded"),													// Steps needed
			    		 						  rs.getInt("stepsDone")													// Steps done
						 	                      ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return achievement;
	}

	@Override
	public boolean add(Achievement a) {
		boolean res = false;
		try {
			String addAchievement = "INSERT INTO Achievement (idAchievement, type, title, description, level, completed, createDate, completeDate, stepsNeeded, stepsDone) "
							      + "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st  = ConnectDB.getConnection().prepareStatement(addAchievement);
			
			LocalDateTime cd = a.getCompleteDate();
			if(cd == null) cd = LocalDateTime.parse("01/01/0001 at 00:00", Constants.dateFormat);
			
			st.setString(1, a.getType().toString());
			st.setString(2, a.getTitle());
			st.setString(3, a.getDescription());
			st.setString(4, a.getLevel().toString());
			st.setInt   (5,(a.isCompleted()) ? 1 : 0);
			st.setString(6, a.getCreatedDate().format(Constants.dateFormat).toString());
			st.setString(7, cd.format(Constants.dateFormat).toString());
			st.setInt   (8, a.getStepsNeeded());
			st.setInt   (9, a.getStepsDone());
			
			st.executeUpdate();
			
			// ID
			String req = "SELECT idAchievement "
					   + "FROM Achievement "
					   + "WHERE title = ?";
			
			st = ConnectDB.getConnection().prepareStatement(req);
			st.setString(1, a.getTitle());
			 
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				a.setId(rs.getInt(1));
			}

			System.out.println(String.format("Achievement [%d] added to database.", a.getId()));
			res = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean update(Achievement a) {
		boolean res = false;
		try {
			String updateAchievement = "UPDATE Achievement "
					   		         + "SET type = ?, title = ?, description = ?, level = ?, completed = ?, createDate = ?, completeDate = ?, stepsNeeded = ?, stepsDone = ? "
					   		         + "WHERE idAchievement = ?";
			
			PreparedStatement st  = ConnectDB.getConnection().prepareStatement(updateAchievement);

			LocalDateTime cd = a.getCompleteDate();
			if(cd == null) cd = LocalDateTime.parse("01/01/0001 at 00:00", Constants.dateFormat);
			
			st.setString(1, a.getType().toString());
			st.setString(2, a.getTitle());
			st.setString(3, a.getDescription());
			st.setString(4, a.getLevel().toString());
			st.setInt   (5,(a.isCompleted()) ? 1 : 0);
			st.setString(6, a.getCreatedDate().format(Constants.dateFormat).toString());
			st.setString(7, cd.format(Constants.dateFormat).toString());
			st.setInt   (8, a.getStepsNeeded());
			st.setInt   (9, a.getStepsDone());
			st.setInt(  10, a.getId());
			
			st.executeUpdate();
			
			System.out.println(String.format("Achievement [%d] updated.", a.getId()));
			res = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean delete(Achievement a) {
		boolean res = false;
		try {
			String updateAchievement = "DELETE FROM Achievement WHERE idAchievement = ?";
			
			PreparedStatement st  = ConnectDB.getConnection().prepareStatement(updateAchievement);
			st.setInt(1, a.getId());
			
			st.executeUpdate();
			
			System.out.println(String.format("Achievement [%d] deleted.", a.getId()));
			a = null;
			res = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public List<Achievement> getAllBinary() {
		List<Achievement> achievements = new ArrayList<>();
		try {
			PreparedStatement st = ConnectDB.getConnection().prepareStatement("SELECT * FROM Achievement WHERE type = ?");
			st.setString(1, AchievementType.BINARY.toString());

			ResultSet rs = st.executeQuery();
	        
	        while (rs.next()) {
        		achievements.add(new Achievement(rs.getInt("idAchievement"),								// ID
					    		 AchievementType.valueOf(rs.getString("type")),								// Type of achievement
					    		 rs.getString("title"),														// Title
					    		 rs.getString("description"),												// Description
					    		 Level.valueOf(rs.getString("level")),										// Level
					    		 (rs.getInt("completed") == 1) ? true : false,								// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate"), Constants.dateFormat),		// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate"), Constants.dateFormat),	// Completion date
					    		 rs.getInt("stepsNeeded"),													// Steps needed
					    		 rs.getInt("stepsDone"))													// Steps done
        						 );
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return achievements;
	}

	@Override
	public List<Achievement> getAllCompletion() {
		List<Achievement> achievements = new ArrayList<>();
		try {
			PreparedStatement st = ConnectDB.getConnection().prepareStatement("SELECT * FROM Achievement WHERE type = ?");
			st.setString(1, AchievementType.COMPLETION.toString());

			ResultSet rs = st.executeQuery();
	        
	        while (rs.next()) {
        		achievements.add(new Achievement(rs.getInt("idAchievement"),								// ID
					    		 AchievementType.valueOf(rs.getString("type")),								// Type of achievement
					    		 rs.getString("title"),														// Title
					    		 rs.getString("description"),												// Description
					    		 Level.valueOf(rs.getString("level")),										// Level
					    		 (rs.getInt("completed") == 1) ? true : false,								// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate"), Constants.dateFormat),		// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate"), Constants.dateFormat),	// Completion date
					    		 rs.getInt("stepsNeeded"),													// Steps needed
					    		 rs.getInt("stepsDone"))													// Steps done
        						 );
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return achievements;
	}

	@Override
	public List<Achievement> getAllCompleted() {
		List<Achievement> achievements = new ArrayList<>();
		try {
			Statement st = ConnectDB.getConnection().createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM Achievement WHERE completed = 1");
	        
	        while (rs.next()) {
        		achievements.add(new Achievement(rs.getInt("idAchievement"),								// ID
					    		 AchievementType.valueOf(rs.getString("type")),								// Type of achievement
					    		 rs.getString("title"),														// Title
					    		 rs.getString("description"),												// Description
					    		 Level.valueOf(rs.getString("level")),										// Level
					    		 (rs.getInt("completed") == 1) ? true : false,								// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate"), Constants.dateFormat),		// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate"), Constants.dateFormat),	// Completion date
					    		 rs.getInt("stepsNeeded"),													// Steps needed
					    		 rs.getInt("stepsDone"))													// Steps done
        						 );
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return achievements;
	}

	@Override
	public List<Achievement> getAllNotCompleted() {
		List<Achievement> achievements = new ArrayList<>();
		try {
			Statement st = ConnectDB.getConnection().createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM Achievement WHERE completed = 0");
	        
	        while (rs.next()) {
        		achievements.add(new Achievement(rs.getInt("idAchievement"),								// ID
					    		 AchievementType.valueOf(rs.getString("type")),								// Type of achievement
					    		 rs.getString("title"),														// Title
					    		 rs.getString("description"),												// Description
					    		 Level.valueOf(rs.getString("level")),										// Level
					    		 (rs.getInt("completed") == 1) ? true : false,								// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate"), Constants.dateFormat),		// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate"), Constants.dateFormat),	// Completion date
					    		 rs.getInt("stepsNeeded"),													// Steps needed
					    		 rs.getInt("stepsDone"))													// Steps done
        						 );
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return achievements;
	}

}
