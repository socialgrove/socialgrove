package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import common.User;
import common.UserList;

public class UserDB {

	public UserList getUsers(){
		UserList list = new UserList();
		try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,"postgres","beer123");
            Statement stmt = conn.createStatement();
            ResultSet rs;
            int i=0;
            
            rs = stmt.executeQuery("select * from socialgrove.\"User\"");
            while ( rs.next() ) {
            	User user1 = new User();
            	user1.setUserId(rs.getInt(1));
            	user1.setUserName(rs.getString(2));
            	user1.setFeedURL(rs.getString(4));
            	user1.setImageURL(rs.getString(3));
                System.out.println(user1.getUserName());
                list.addUser(i, user1);
                i++;
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
        return list;
	}	
}
