package org.isima.twitter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Follower
 *
 */
@Entity

public class Follower implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade= { CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH} )
	@JoinColumn(name="id_follower")
	private User user_follower;
	
	@ManyToOne(cascade= { CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH} )
	@JoinColumn(name="id_following")
	private User user_following;
	
	@Column(name = "date_creation", nullable = false)
	private Date date_creation;
	

	public Follower() {
		super();
	}   
	
	public Follower(User user_follower, User user_following,
			Date date_creation) {
		super();
		this.user_follower = user_follower;
		this.user_following = user_following;
		this.date_creation = date_creation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser_follower() {
		return user_follower;
	}

	public void setUser_follower(User user_follower) {
		this.user_follower = user_follower;
	}

	public User getUser_following() {
		return user_following;
	}

	public void setUser_following(User user_following) {
		this.user_following = user_following;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}   

}
