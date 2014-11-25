package org.isima.twitter.model;

import java.io.Serializable;
import javax.persistence.*;

import org.isima.twitter.model.Tweet;
import org.isima.twitter.model.User;

/**
 * Entity implementation class for Entity: Retweet
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Retweet extends Tweet implements Serializable {
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_tweet_retweeter" )
	private Tweet id_tweet_retweeter;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_user_retweeter")
	private User id_user_retweeter;

	private static final long serialVersionUID = 1L;

	public Retweet() {
		super();
	}   
  

	public Tweet getId_tweet_retweeter() {
		return id_tweet_retweeter;
	}
	public void setId_tweet_retweeter(Tweet id_tweet_retweeter) {
		this.id_tweet_retweeter = id_tweet_retweeter;
	}
	public User getId_user_retweeter() {
		return id_user_retweeter;
	}
	public void setId_user_retweeter(User id_user_retweeter) {
		this.id_user_retweeter = id_user_retweeter;
	}
   
}
