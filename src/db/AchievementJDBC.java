package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ihm.Constants;
import objects.Achievement;
import objects.AchievementLevel;
import objects.AchievementType;

public class AchievementJDBC implements AchievementDAO {

	@Override
	public List<Achievement> getAll() {
        List<Achievement> achievements = new ArrayList<>();
		try {
			Statement st = ConnectDB.getConnection().createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM Achievement");
	        
	        while (rs.next()) {
        		achievements.add(new Achievement(rs.getString("idAchievement"),		// ID
					    		 rs.getString("title"),								// Title
					    		 rs.getString("description"),						// Description
					    		 AchievementType.valueOf(rs.getString("type")),		// Type of achievement
					    		 AchievementLevel.valueOf(rs.getString("level")),	// Level
					    		 (rs.getInt("completed") == 1) ? true : false,		// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate")),	// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate")),	// Completion date
					    		 rs.getInt("stepsNeeded"),							// Steps needed
					    		 rs.getInt("stepsDone"))							// Steps done
        						 );
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return achievements;
	}

	@Override
	public Optional<Achievement> getById(String id) {
		Optional<Achievement> achievement = Optional.empty();
		try {
			String req = "SELECT * "
					   + "FROM Achievement "
					   + "WHERE idAchievement = ?";
			
			PreparedStatement st = ConnectDB.getConnection().prepareStatement(req);
			st.setString(1, id);
			 
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				achievement = Optional.ofNullable(new Achievement(rs.getString("idAchievement"), 		// ID
			    		 						  rs.getString("title"),								// Title
			    		 						  rs.getString("description"),							// Description
			    		 						  AchievementType.valueOf(rs.getString("type")),		// Type of achievement
			    		 						  AchievementLevel.valueOf(rs.getString("level")),		// Level
			    		 						  (rs.getInt("completed") == 1) ? true : false,			// Completed or no
			    		 						  LocalDateTime.parse(rs.getString("createDate")),		// Creation date
			    		 						  LocalDateTime.parse(rs.getString("completeDate")),	// Completion date
			    		 						  rs.getInt("stepsNeeded"),								// Steps needed
			    		 						  rs.getInt("stepsDone")								// Steps done
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
			String addAchievement = "INSERT INTO Achievement (idAchievement, title, description, type, level, completed, createDate, completeDate, stepsNeeded, stepsDone) "
							      + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st  = ConnectDB.getConnection().prepareStatement(addAchievement);
			
			st.setString(1, a.getId());
			st.setString(2, a.getTitle());
			st.setString(3, a.getDescription());
			st.setString(4, a.getType().toString());
			st.setString(5, a.getLevel().toString());
			st.setInt   (6,(a.isCompleted()) ? 1 : 0);
			st.setString(7, a.getCreatedDate().toString());
			st.setString(8, a.getCompletedDate().toString());
			st.setInt   (9, a.getStepsNeeded());
			st.setInt  (10, a.getStepsDone());
			
			st.executeUpdate();

			System.out.println(String.format("Achievement [%s] added to database.", a.getId()));
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
					   		         + "SET title = ?, description = ?, type = ?, level = ?, completed = ?, createDate = ?, completeDate = ?, stepsNeeded = ?, stepsDone = ? "
					   		         + "WHERE idAchievement = ?";
			
			PreparedStatement st  = ConnectDB.getConnection().prepareStatement(updateAchievement);

			st.setString(1, a.getTitle());
			st.setString(2, a.getDescription());
			st.setString(3, a.getType().toString());
			st.setString(4, a.getLevel().toString());
			st.setInt   (5,(a.isCompleted()) ? 1 : 0);
			st.setString(6, a.getCreatedDate().toString());
			st.setString(7, a.getCompletedDate().toString());
			st.setInt   (8, a.getStepsNeeded());
			st.setInt   (9, a.getStepsDone());
			
			st.setString(10, a.getId());
			
			st.executeUpdate();
			
			System.out.println(String.format("Achievement [%s] updated.", a.getId()));
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
			st.setString(1, a.getId());
			
			st.executeUpdate();
			
			System.out.println(String.format("Achievement [%s] deleted.", a.getId()));
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
        		achievements.add(new Achievement(rs.getString("idAchievement"),		// ID
					    		 rs.getString("title"),								// Title
					    		 rs.getString("description"),						// Description
					    		 AchievementType.valueOf(rs.getString("type")),		// Type of achievement
					    		 AchievementLevel.valueOf(rs.getString("level")),	// Level
					    		 (rs.getInt("completed") == 1) ? true : false,		// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate")),	// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate")),	// Completion date
					    		 rs.getInt("stepsNeeded"),							// Steps needed
					    		 rs.getInt("stepsDone"))							// Steps done
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
        		achievements.add(new Achievement(rs.getString("idAchievement"),		// ID
					    		 rs.getString("title"),								// Title
					    		 rs.getString("description"),						// Description
					    		 AchievementType.valueOf(rs.getString("type")),		// Type of achievement
					    		 AchievementLevel.valueOf(rs.getString("level")),	// Level
					    		 (rs.getInt("completed") == 1) ? true : false,		// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate")),	// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate")),	// Completion date
					    		 rs.getInt("stepsNeeded"),							// Steps needed
					    		 rs.getInt("stepsDone"))							// Steps done
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
        		achievements.add(new Achievement(rs.getString("idAchievement"),		// ID
					    		 rs.getString("title"),								// Title
					    		 rs.getString("description"),						// Description
					    		 AchievementType.valueOf(rs.getString("type")),		// Type of achievement
					    		 AchievementLevel.valueOf(rs.getString("level")),	// Level
					    		 (rs.getInt("completed") == 1) ? true : false,		// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate")),	// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate")),	// Completion date
					    		 rs.getInt("stepsNeeded"),							// Steps needed
					    		 rs.getInt("stepsDone"))							// Steps done
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
        		achievements.add(new Achievement(rs.getString("idAchievement"),		// ID
					    		 rs.getString("title"),								// Title
					    		 rs.getString("description"),						// Description
					    		 AchievementType.valueOf(rs.getString("type")),		// Type of achievement
					    		 AchievementLevel.valueOf(rs.getString("level")),	// Level
					    		 (rs.getInt("completed") == 1) ? true : false,		// Completed or no
					    		 LocalDateTime.parse(rs.getString("createDate")),	// Creation date
					    		 LocalDateTime.parse(rs.getString("completeDate")),	// Completion date
					    		 rs.getInt("stepsNeeded"),							// Steps needed
					    		 rs.getInt("stepsDone"))							// Steps done
        						 );
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return achievements;
	}

}
