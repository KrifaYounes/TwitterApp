package org.isima.twitter.model;

import java.io.Serializable;
import javax.persistence.*;

import org.isima.twitter.model.Tweet;

/**
 * Entity implementation class for Entity: Response
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Response extends Tweet implements Serializable {

	@ManyToOne(cascade= { CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH} )
	@JoinColumn(name="id_tweet_initial")
	private Tweet tweet_initial; 
	
	private static final long serialVersionUID = 1L;

	public Response() {
		super();
	}

	public Tweet getTweet_initial() {
		return tweet_initial;
	}

	public void setTweet_initial(Tweet tweet_initial) {
		this.tweet_initial = tweet_initial;
	}   

	
}
