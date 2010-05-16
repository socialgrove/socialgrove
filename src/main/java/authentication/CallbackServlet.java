package authentication;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.http.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserDB;

import java.io.IOException;

public class CallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1657390011452788111L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        try {
            twitter.getOAuthAccessToken(requestToken, verifier);
            request.getSession().removeAttribute("requestToken");
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
        User user;
        UserDB userDB = new UserDB();
		try {
			user = twitter.showUser(twitter.getId());
			System.out.println("user.getId(): "+user.getId());
			System.out.println("twitter.getId(): "+twitter.getId());
			if(!userDB.checkTwitterUser(user.getId())){
				System.out.println("Storing Twitter User Details");
				userDB.insertTwitterUser(user.getId(), user.getName(), user.getProfileImageURL().toString(), user.getScreenName(), user.getLocation(), user.getURL().toString());
			}
			
			System.out.println("Screen Name: "+user.getScreenName());
			System.out.println("Image URL: "+user.getProfileImageURL());
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
        response.sendRedirect(request.getContextPath()+ "/main.html");
    }
}
