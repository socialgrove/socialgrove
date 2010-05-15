package feeds;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.User;
import common.UserList;

import db.UserDB;

public class ListingServlet extends HttpServlet {
	private static final long serialVersionUID = 2132731135996613711L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			doPost(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	UserList list = new UserList();
    	UserDB db = new UserDB();
    	
    	list = db.getUsers();
    	
    	//Simple User Addition
    	//list.addUser(0, getUser(0,"David Finch","http://feeds.feedburner.com/davidsfinch","images/davidfinch.jpg"));
    	//list.addUser(1, getUser(1,"Jason Falls","http://feeds.feedburner.com/SocialMediaExplorer","images/jasonfalls.jpg"));
    	
    	request.setAttribute("Users", list);
    	request.getRequestDispatcher("list.jsp").forward(request, response);
    	
    }
    
    //Used for simple test cases 
    private User getUser(int id,String name,String feedURL,String imageURL){
    	User user1 = new User();
    	user1.setUserId(id);
    	user1.setUserName(name);
    	user1.setFeedURL(feedURL);
    	user1.setImageURL(imageURL);
    	
    	return user1;
    }
    
}
