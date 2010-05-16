package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

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
	
	public boolean checkTwitterUser(int twitterId){
		try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,"postgres","beer123");
            String sql = "select * from socialgrove.\"twitterUser\" where \"twitterId\"= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, twitterId);
            ResultSet rs;
            rs = stmt.executeQuery();
            while ( rs.next() ) {
            	return true;
            }
		} catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean insertTwitterUser(int twitterId, String twitterName, String twitterImageURL, String twitterScreenName, String twitterLocation, String twitterURL){
		try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,"postgres","beer123");
            String sql = "insert into socialgrove.\"twitterUser\" values(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, twitterId);
            stmt.setString(2, twitterName);
            stmt.setString(3, twitterImageURL);
            stmt.setString(4, twitterScreenName);
            stmt.setString(5, twitterLocation);
            stmt.setString(6, twitterURL);
            stmt.executeUpdate();
            return true;
		} catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
		return false;
	}
}
