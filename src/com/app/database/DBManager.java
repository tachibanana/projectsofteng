package com.app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.model.user.Admin;
import com.app.model.user.Course;
import com.app.model.user.Student;
import com.app.model.user.User;
import com.app.model.user.Year;

public class DBManager {
	
	private DatabaseConnection dbconn;
	private  Connection conn;
	private static DBManager manager;
	
	public static final DBManager getInstance(){
		if(manager == null)
			manager = new DBManager();
		return manager;
		
	}
	public final void setDatabaseConnection(DatabaseConnection connection){
		dbconn = connection;
	}
	
	public final void openConnection(){
		conn = dbconn.getConnection();
	}
	
	public final void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public final DatabaseConnection getDatabaseConnection(){
		return dbconn;
	}
	
	public User getUserWithUsernameAndPassword(String username,String password){
		try{
			User user = null;
			for(User isThisUser : getListOfUser()){
				if(username.equals(isThisUser.getUsername()) && password.equals(isThisUser.getPassword())){
					user = isThisUser;
				}
			}
			return user;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getListOfUser(){
		try{
			List<User> listOfUser = new ArrayList<User>();
			String sql = "SELECT * FROM tbluser";
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet result = pst.executeQuery();
			while(result.next()){
				User user = null;
				String username = result.getString("username");
				String password = result.getString("password");
				String accessType = result.getString("access_type");
				boolean isActivate = (result.getInt("is_activated") == 1);
				
				if(accessType.equals("ADMIN")){
					
					user = new Admin();
					user.setUsername(username);
					user.setPassword(password);
					user.setAccessType(accessType);
					user.setActivate(isActivate);
			
				}else if(accessType.equals("STUDENT")){
					
					user = new Student();
					user.setUsername(username);
					user.setPassword(password);
					user.setAccessType(accessType);
					user.setActivate(isActivate);
					
				}
				listOfUser.add(user);
			}
			return listOfUser;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateUserPasswordByWithUsername(String username ,String newPassword){
		try{
			String sql="UPDATE tbluser SET password = ? WHERE username = ?;";
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, newPassword);
			pst.setString(2, username);
			pst.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Course> getListOfCourse(){
		try{
			List<Course> listOfCourse = new ArrayList<Course>();
			String sql = "SELECT * FROM tblcourse";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				
				String courseId = rs.getString("course_id");
				String courseCode = rs.getString("course_code");
				String courseDefinition = rs.getString("course_definition");
				List<Year> listOfYear = getListOfYearByCourseId(courseId);
				Course course = new Course(courseId , 
						courseCode , courseDefinition , listOfYear);
				
				listOfCourse.add(course);
			}
			
			return listOfCourse;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Year> getListOfYearByCourseId(String courseId){
		try{
			List<Year> listOfYear = new ArrayList<Year>();
			String code = (courseId != null ? courseId : "");
			String sql = "SELECT * FROM tblyear where course_id = ?";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, code);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				Year year = new Year(rs.getString("year_id") , rs.getString("year_code"));
				listOfYear.add(year);
			}
			
			return listOfYear;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
