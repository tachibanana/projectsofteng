package com.app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.model.User;

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
				User user = new User(
						result.getString("user_id"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("username"),
						result.getString("password"),
						result.getString("access_type"),
						(result.getInt("is_activated") == 1 ? true : false));
				listOfUser.add(user);
			}
			return listOfUser;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateUserPasswordById(String userId ,String newPassword){
		try{
			String sql="UPDATE tbluser SET password = ? WHERE user_id = ?;";
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, newPassword);
			pst.setString(2, userId);
			pst.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
