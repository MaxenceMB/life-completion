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
import objects.Week;

public class WeekJDBC implements WeekDAO {

	@Override
	public List<Week> getAll() {
        List<Week> weeks = new ArrayList<>();
		try {
			Statement st = ConnectDB.getConnection().createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM Week");
	        
	        while (rs.next()) {
	        	weeks.add(new Week(rs.getString("idWeek"),						// ID
 					   			   LocalDateTime.parse(rs.getString("date")),	// Review date
	        					   rs.getString("text"),						// Text review
	        					   rs.getFloat("grade")							// Review grade
        						   ));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return weeks;
	}

	@Override
	public Optional<Week> getById(String id) {
		Optional<Week> week = Optional.empty();
		try {
			String req = "SELECT * "
					   + "FROM Week "
					   + "WHERE idWeek = ?";
			
			PreparedStatement st = ConnectDB.getConnection().prepareStatement(req);
			st.setString(1, id);
			 
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				week = Optional.ofNullable(new Week(rs.getString("idWeek"),						// ID
									   			    LocalDateTime.parse(rs.getString("date")),	// Review date
						    					    rs.getString("text"),						// Text review
						    					    rs.getFloat("grade")						// Review grade
												    ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return week;
	}

	@Override
	public boolean add(Week w) {
		boolean res = false;
		try {
			String addWeek = "INSERT INTO Week (idWeek, date, text, grade) "
						   + "VALUES (?, ?, ?, ?)";
			
			PreparedStatement st  = ConnectDB.getConnection().prepareStatement(addWeek);
			
			st.setString(1, w.getId());
			st.setString(2, w.getDate().toString());
			st.setString(3, w.getText());
			st.setFloat (4, w.getGrade());
			
			st.executeUpdate();

			System.out.println(String.format("[%s] added to database.", w.getId()));
			res = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean update(Week w) {
		boolean res = false;
		try {
			String updateWeek = "UPDATE Week "
					   		  + "SET date = ?, text = ?, grade = ? "
					   		  + "WHERE idWeek = ?";
			
			PreparedStatement st  = ConnectDB.getConnection().prepareStatement(updateWeek);

			st.setString(1, w.getDate().toString());
			st.setString(2, w.getText());
			st.setFloat (3, w.getGrade());
			
			st.setString(4, w.getId());
			
			st.executeUpdate();
			
			System.out.println(String.format("[%s] updated.", w.getId()));
			res = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean delete(Week w) {
		boolean res = false;
		try {
			String deleteWeek = "DELETE FROM Week WHERE idWeek = ?";
			
			PreparedStatement st  = ConnectDB.getConnection().prepareStatement(deleteWeek);
			st.setString(1, w.getId());
			
			st.executeUpdate();
			
			System.out.println(String.format("[%s] deleted.", w.getId()));
			w = null;
			res = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int getAmountDone() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Week> getGradeOver(float grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Week> getGradeUnder(float grade) {
		// TODO Auto-generated method stub
		return null;
	}

}
