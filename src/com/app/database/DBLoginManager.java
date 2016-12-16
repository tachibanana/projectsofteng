package com.app.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.model.User;
import com.mysql.jdbc.PreparedStatement;

public class DBLoginManager extends DBManager{
	
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
			PreparedStatement pst = (PreparedStatement) getConnection().prepareStatement(sql);
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

}
