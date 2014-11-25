package org.isima.tweeter.faces.bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.isima.tweeter.services.FollowerServiceLocal;
import org.isima.tweeter.services.TweetServiceLocal;
import org.isima.tweeter.services.UserServiceLocal;
import org.isima.twitter.model.Follower;
import org.isima.twitter.model.Tweet;
import org.isima.twitter.model.User;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class TwitterUserBean {

	@EJB
	private UserServiceLocal userLocal;
	
	@EJB
	private TweetServiceLocal tweetLocal;
	
	@EJB
	private FollowerServiceLocal followerLocal;

	private User user;
	private List<Tweet> listTweet;
	private List<Follower> listFollowing;
	private List<Follower> listFollower;
	private String login;
	private StreamedContent myImage;
	private int nb_following ; 
	private int nb_follower;
	private int nb_tweet;

	public TwitterUserBean(){	
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		login = request.getParameter("login");
	}
	
	@PostConstruct
	public void init(){
		user = userLocal.getByLogin(login);
		if(user != null){
			listTweet = tweetLocal.getAllTweetByUser(user);
			listFollowing = followerLocal.getFollowerByUserFollower(user);
			listFollower = followerLocal.getFollowerByUserFollowing(user);
			nb_following = listFollowing.size();
			nb_follower = listFollower.size();
			nb_tweet = listTweet.size();
		}
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<Tweet> getListTweet() {
		return listTweet;
	}

	public void setListTweet(List<Tweet> listTweet) {
		this.listTweet = listTweet;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public StreamedContent getMyImage() {
		FacesContext context = FacesContext.getCurrentInstance();

        if (context.getRenderResponse()) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
			InputStream stream = new ByteArrayInputStream(user.getPicture());
			myImage = new DefaultStreamedContent(stream);
			return myImage;    
        }
	}
	
	public void setMyImage(StreamedContent myImage) {
		this.myImage = myImage;
	}

	public List<Follower> getListFollowing() {
		return listFollowing;
	}

	public void setListFollowing(List<Follower> listFollowing) {
		this.listFollowing = listFollowing;
	}

	public List<Follower> getListFollower() {
		return listFollower;
	}

	public void setListFollower(List<Follower> listFollower) {
		this.listFollower = listFollower;
	}

	public int getNb_following() {
		return nb_following;
	}

	public void setNb_following(int nb_following) {
		this.nb_following = nb_following;
	}

	public int getNb_follower() {
		return nb_follower;
	}

	public void setNb_follower(int nb_follower) {
		this.nb_follower = nb_follower;
	}

	public int getNb_tweet() {
		return nb_tweet;
	}

	public void setNb_tweet(int nb_tweet) {
		this.nb_tweet = nb_tweet;
	}
	
}
