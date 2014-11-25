package org.isima.twitter.model;


import java.io.Serializable;
import java.lang.String;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.apache.bval.constraints.Email;
import org.apache.bval.constraints.NotEmpty;



/**
 * Entity implementation class for Entity: Utilisateur
 * Classe qui permet de définir un utilisateur de
 * l'application
 */
@Entity
@Table(name="TWEET_USER") 
public class User implements Serializable {

	/**
	 *   
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "login", nullable = false  )
	@NotEmpty(message = "Veuillez entrer un nom d'utilisateur")
	private String login;
	
	@Column(name = "nom", nullable = false)
	@NotEmpty(message = "Veuillez entrer un nom ")
	private String nom;

	@Column( name = "password", nullable = false)
	@Size(min = 6 , message = "Le mot de passe doit contenir au moins 6 caractères")
	private String password;
	
	@Column(name = "email", nullable = false)
	@Email(message = "Entrer une adresse mail valide")
	@NotEmpty(message = "Veuillez entrer une adresse email")
	private String email;
	
	//@Basic(fetch=FetchType.EAGER)
	@Lob
	@Column(name="picture")
    private byte[] picture;
	
	@Column(name = "date_creation", nullable = false)
	private Date date_creation;
	
	@OneToMany(mappedBy="user_follower" )
	private List<Follower> listFollower ;
	
	@OneToMany(mappedBy="user_following" )
	private List<Follower> listFollowing ;

	@OneToMany(mappedBy="user_tweeter" )
	private List<Tweet> listTweet;
	
	@OneToMany(mappedBy="id_user_retweeter" )
	private List<Retweet> listRetweet;
	
	public User(){
		
	}
	
	public User(String login, String nom, String password, String email,
			Date date_creation, byte[] picture) {
		super();
		this.login = login;
		this.nom = nom;
		this.password = password;
		this.email = email;
		this.date_creation = date_creation;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public List<Follower> getListFollower() {
		return listFollower;
	}

	public void setListFollower(List<Follower> listFollower) {
		this.listFollower = listFollower;
	}

	public List<Follower> getListFollowing() {
		return listFollowing;
	}

	public void setListFollowing(List<Follower> listFollowing) {
		this.listFollowing = listFollowing;
	}

	public List<Retweet> getListRetweet() {
		return listRetweet;
	}

	public void setListRetweet(List<Retweet> listRetweet) {
		this.listRetweet = listRetweet;
	}

	public List<Tweet> getListTweet() {
		return listTweet;
	}

	public void setListTweet(List<Tweet> listTweet) {
		this.listTweet = listTweet;
	}

	@Override 
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", nom=" + nom
				+ ", password=" + password + ", email=" + email + ", picture="
				+ Arrays.toString(picture) + ", date_creation=" + date_creation
				+ "]";
	}

	
}








