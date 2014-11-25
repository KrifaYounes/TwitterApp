package org.isima.twitter.model;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.isima.twitter.model.User;

/**
 * Entity implementation class for Entity: Tweet
 *
 */
@Entity

public class Tweet implements Serializable {
	   
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(length=4000)
	private String message;
	private Date date_creation;
	
	@ManyToOne(cascade= { CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH})
	@JoinColumn(name="id_user_tweeter")
	private User user_tweeter;
	
	@OneToMany(mappedBy="tweet_initial")
	private List<Response> listResponse;
	
	@OneToMany(cascade= CascadeType.ALL ,mappedBy="tweet_hashtag")
	private List<Hashtag> lisHashtag;

	private static final long serialVersionUID = 1L;

	public Tweet() {
		super();
	}   
	
	public Tweet(String message, Date date_creation, User user_tweeter) {
		super();
		this.message = message;
		this.date_creation = date_creation;
		this.user_tweeter = user_tweeter;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}   
	public Date getDate_creation() {
		return this.date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}   
	public User getUser_tweeter() {
		return this.user_tweeter;
	}

	public void setUser_tweeter(User user_tweeter) {
		this.user_tweeter = user_tweeter;
	}
	public List<Response> getListResponse() {
		return listResponse;
	}
	public void setListResponse(List<Response> listResponse) {
		this.listResponse = listResponse;
	}
	public List<Hashtag> getLisHashtag() {
		return lisHashtag;
	}
	public void setLisHashtag(List<Hashtag> lisHashtag) {
		this.lisHashtag = lisHashtag;
	}

}
