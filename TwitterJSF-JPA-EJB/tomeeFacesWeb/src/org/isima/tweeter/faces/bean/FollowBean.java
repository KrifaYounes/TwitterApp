package org.isima.tweeter.faces.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.isima.tweeter.services.FollowerServiceLocal;
import org.isima.tweeter.services.UserServiceLocal;
import org.isima.twitter.model.Follower;
import org.isima.twitter.model.User;

/**
 * Classe qui contient des méthodes
 * qui permettent de s'abonner et se désabonner
 * à des utilisateurs
 * @author Younes
 *
 */
@ManagedBean
@SessionScoped
public class FollowBean {

	@EJB
	private FollowerServiceLocal followerLocal;
	
	@EJB
	private UserServiceLocal userLocal;
	
	private List<Follower> listFollowing;
	private List<Follower> listFollower;
	private List<User> listUserNotFollow;
	private int nb_following ; 
	private int nb_follower;
	private User user;
	
	public FollowBean(){
		/**
		 * initialisation de user
		 * a partir du bean LoginManagedBean
		 */
		FacesContext ctx = FacesContext.getCurrentInstance();
		LoginManagedBean bean = (LoginManagedBean) ctx.getExternalContext().getSessionMap().get("loginManagedBean");
		user = bean.getUser();
	}
	
	@PostConstruct
	public void init(){
		try{
			listFollowing = followerLocal.getFollowerByUserFollower(user);
			listFollower = followerLocal.getFollowerByUserFollowing(user);
			listUserNotFollow = userLocal.listAllUser(user.getId());

		}catch(Exception e){
			FacesMessage message = new FacesMessage( "Erreur !" );
			FacesContext.getCurrentInstance().addMessage( null, message);
		}
	}
	
	/**
	 * fonction qui permet de s'abonner
	 * à un utilisateur
	 * @param email
	 */
	public void suivre(String email){
		try{
			User user_following = userLocal.getByEmail(email);
			Follower follower = followerLocal.getFollower(user, user_following);
			if(follower == null){
				Follower following = new Follower(user, user_following, new Date());
			
				followerLocal.create(following);
				listFollowing = followerLocal.getFollowerByUserFollower(user);
	
				FacesMessage message = new FacesMessage( "Vous suivez désormais " + user_following.getLogin() );
				FacesContext.getCurrentInstance().addMessage( null, message);
			}else{
				FacesMessage message = new FacesMessage( "Vous suivez déjà " + user_following.getLogin() );
				FacesContext.getCurrentInstance().addMessage( null, message);
			}

		}catch(Exception e){
			FacesMessage message = new FacesMessage( "Erreur !" );
			FacesContext.getCurrentInstance().addMessage( null, message);
		}
	}
	
	/**
	 * fonction qui permet de se désabonner
	 * d'un utilisateur
	 * @param id
	 */
	public void desabonner(String id){
		int id_follower = Integer.parseInt(id);
		try{
			followerLocal.delete(id_follower);
			listFollowing = followerLocal.getFollowerByUserFollower(user);
			nb_following = listFollowing.size();
			FacesMessage message = new FacesMessage( "Vous ne suivez plus cette personne");
			FacesContext.getCurrentInstance().addMessage( null, message);

		}catch(Exception e){
			FacesMessage message = new FacesMessage( "Erreur !" );
			FacesContext.getCurrentInstance().addMessage( null, message);
		}
	}
	
	
	public int getNb_follower() {
		return listFollower.size();
	}

	public void setNb_follower(int nb_follower) {
		this.nb_follower = nb_follower;
	}

	public int getNb_following() {
		return listFollowing.size();
	}

	public void setNb_following(int nb_following) {
		this.nb_following = nb_following;
	}

	public List<Follower> getListFollower() {
		return listFollower;
	}

	public void setListFollower(List<Follower> listFollower) {
		this.listFollower = listFollower;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Follower> getListFollowing() {
		return listFollowing;
	}

	public void setListFollowing(List<Follower> listFollowing) {
		this.listFollowing = listFollowing;
	}

	public List<User> getListUserNotFollow() {
		return listUserNotFollow;
	}

	public void setListUserNotFollow(List<User> listUserNotFollow) {
		this.listUserNotFollow = listUserNotFollow;
	}

}
