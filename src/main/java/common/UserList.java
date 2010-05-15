package common;

import java.util.ArrayList;

public class UserList {
	
	private ArrayList userList = new ArrayList();
	
	public void addUser(int i,User user){
		userList.add(i,user);
	}

	public User getUser(int userId){
		User user = (User) userList.get(userId);
		return user;
	}
	
	public int count(){
		return userList.size();
	}
}
