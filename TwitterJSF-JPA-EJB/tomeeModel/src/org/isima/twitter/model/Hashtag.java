package org.isima.twitter.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Hashtag
 *
 */
@Entity

public class Hashtag implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nom_tag;
	
	@ManyToOne(cascade= CascadeType.ALL )
	@JoinColumn(name="id_tweet")
	private Tweet tweet_hashtag;
	

	public Hashtag() {
		super();
	}   
	
	public Hashtag(String nom_tag) {
		this.nom_tag = nom_tag;
	}
	
	public Hashtag(String nom_tag,Tweet tweet) {
		this.nom_tag = nom_tag;
		this.tweet_hashtag = tweet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_tag() {
		return nom_tag;
	}

	public void setNom_tag(String nom_tag) {
		this.nom_tag = nom_tag;
	}

	public Tweet getTweet_hashtag() {
		return tweet_hashtag;
	}

	public void setTweet_hashtag(Tweet tweet_hashtag) {
		this.tweet_hashtag = tweet_hashtag;
	}   
	

	
}
