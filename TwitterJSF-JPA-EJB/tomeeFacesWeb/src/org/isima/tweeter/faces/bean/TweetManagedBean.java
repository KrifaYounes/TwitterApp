package org.isima.tweeter.faces.bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.isima.tweeter.services.HashtagServiceLocal;
import org.isima.tweeter.services.TweetServiceLocal;
import org.isima.twitter.model.Retweet;
import org.isima.twitter.model.Tweet;
import org.isima.twitter.model.User;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.twitter.Extractor;

@ManagedBean
@SessionScoped
public class TweetManagedBean {  
      
	@EJB
	private TweetServiceLocal tweetServiceLocal;
	
	@EJB
	private HashtagServiceLocal hashtagServiceLocal;
	
	private User user;
	private String message;
	private List<Tweet> listTweet;
	private StreamedContent myImage;
	private int nb_tweet;
	private Extractor extractor = new Extractor();

	public TweetManagedBean(){
		/**
		 * initialisation de user
		 * a partir du bean LoginManagedBean
		 */
		FacesContext ctx = FacesContext.getCurrentInstance();
		LoginManagedBean bean = (LoginManagedBean) ctx.getExternalContext().getSessionMap().get("loginManagedBean");
		user = bean.getUser();
	}
	
	/**
	 * initialisation de la liste des 
	 * tweets de l'utilisateur
	 */
	@PostConstruct
	public void initialisation(){
		try{
			listTweet = tweetServiceLocal.getAllTweetByUser(user);
			nb_tweet = listTweet.size();
		}catch(Exception e){
			e.printStackTrace();
			e.getLocalizedMessage();
		}
	}
	
	/**
	 * fonction qui permet de tweeter 
	 * un message qui peut contenir des hashtag, des liens
	 * web et des liens vers les utilisateurs
	 */
	public void tweeter(){
		try{
			List<String> listHashtagExtract = extraction();
			Tweet tweet = new Tweet(message,new Date(), user);
			tweet = tweetServiceLocal.create(tweet);
			listTweet = tweetServiceLocal.getAllTweetByUser(user);
			nb_tweet = listTweet.size();
			message = "@"+user.getLogin();
			
			try{								
				List<String> listHashtagUnique = new ArrayList<String>();
				for(String s : listHashtagExtract ){
				    if (!listHashtagUnique.contains(s))
				    	listHashtagUnique.add(s);
				}
				for(String nom_tag : listHashtagUnique){
					hashtagServiceLocal.create(nom_tag,tweet.getId());
				}
			}catch(Exception e){
				FacesMessage message = new FacesMessage( "Erreur lors la création des hashtag en base");
				FacesContext.getCurrentInstance().addMessage( null, message);
			}
			
			FacesMessage message = new FacesMessage( "Votre tweet a été posté avec succès");
			FacesContext.getCurrentInstance().addMessage( null, message);
			
			
		}catch(Exception e){
			FacesMessage message = new FacesMessage( "Votre tweet n'a pas pu etre posté " );
			FacesContext.getCurrentInstance().addMessage( null, message);
		}	
	}
	
	
	
	/**
	 * fonction qui permet de supprimer 
	 * un tweet 
	 * @param id
	 */
	public void supprimer(String id){
		int id_tweet = Integer.parseInt(id);
		try{
			tweetServiceLocal.delete(id_tweet);
			listTweet = tweetServiceLocal.getAllTweetByUser(user);
			nb_tweet = listTweet.size();
			FacesMessage message = new FacesMessage("Votre tweet a été supprimé avec succès");
			FacesContext.getCurrentInstance().addMessage( null, message);
		
		}catch(Exception e){
			FacesMessage message = new FacesMessage( "Votre tweet n'a pas pu etre supprimé " );
			FacesContext.getCurrentInstance().addMessage( null, message);
		}	
	}
	
	/**
	 * extraction des hashtag,liens web et
	 * liens utilisateur 
	 * On renvoie la liste des hashtag pour les créer
	 * en base de données
	 */
	public List<String> extraction(){
		List<String> listHashtagExtract = extractor.extractHashtags(message);
		List<String> listUserExtract = extractor.extractMentionedScreennames(message);
		List<String> listUrl =extractor.extractURLs(message);

		for(String user_name : listUserExtract){
			if (message.contains(user_name))
				message = message.replace("@"+user_name,"<a href=\"mur.xhtml?login="+user_name+"\" style=\"color:#30CDFD\">"+"@"+user_name+"</a>");
		}
		
		for(String nom_tag : listHashtagExtract){
			if (message.contains(nom_tag))
				message = message.replace("#"+nom_tag,"<a href=\"hashtag.xhtml?hashtag="+nom_tag+"\" style=\"color:#30CDFD\">"+"#"+nom_tag+"</a>");
		}
		
		for(String url : listUrl){
			if (message.contains(url))
				message = message.replace(url,"<a href="+url+" style=\"color:#30CDFD\">"+url+"</a>");
		}
		
		return listHashtagExtract;
	}
	
	/**
	 * marche pas
	 * @param id_tweet
	 */
	public void retweeter(String id_tweet){

		try{
			Retweet retweet = new Retweet();
			retweet.setDate_creation(new Date());
			retweet.setId_user_retweeter(user);
			
			tweetServiceLocal.create(retweet);

			FacesMessage message = new FacesMessage("Ce tweet a été retweeté correctement");
			FacesContext.getCurrentInstance().addMessage( null, message);
		}catch(Exception e){
			FacesMessage message = new FacesMessage( "Ce tweet n'a pas été retweeté " );
			FacesContext.getCurrentInstance().addMessage( null, message);
		}
	}
	
	/**
	 * 
	 */
	public void repondre(){	
	}
	
	
	public int getNb_tweet() {
		return nb_tweet;
	}

	public void setNb_tweet(int nb_tweet) {
		this.nb_tweet = nb_tweet;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
  	
}  