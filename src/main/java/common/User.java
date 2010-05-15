package common;

public class User {
	
	private int userId;
	private String userName;
	private String twitterId;
	private String feedURL;
	private String imageURL;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public String getFeedURL() {
		return feedURL;
	}
	public void setFeedURL(String feedURL) {
		this.feedURL = feedURL;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
