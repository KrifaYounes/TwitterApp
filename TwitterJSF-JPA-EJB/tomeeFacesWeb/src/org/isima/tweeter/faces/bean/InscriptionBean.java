package org.isima.tweeter.faces.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
 
import org.isima.tweeter.services.TweetServiceLocal;
import org.isima.tweeter.services.UserServiceLocal;
import org.isima.twitter.model.User;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * class qui permet de créer un utilisateur 
 * dans la base de données
 * Lors de la 1ere inscription des utilisateurs
 * sont créés 
 * @author Younes
 *
 */
@ManagedBean
@SessionScoped
public class InscriptionBean {

	@EJB
	private UserServiceLocal userServiceLocal;
	
	@EJB
	private TweetServiceLocal tweetServiceLocal;

	
	private User user ;
	private UploadedFile file;
	private byte[] image_byte;
	private boolean upload = false;
	
	
	public InscriptionBean(){
		user = new User();
	}

	/**
	 * fonction qui permet de créer un utilisateur 
	 * dans la base de données,l'utilisateur 
	 * doit choisir une photo de profil pour
	 * la création soit valide
	 */
	public String saveUser(){
		if(upload){
			try{
				user.setDate_creation(new Date());
				userServiceLocal.create(user);
				String mess = generer_utilisateur();
				return mess;
			}catch(Exception e){
				FacesMessage message = new FacesMessage( "Erreur lors de l'inscription !" +e.getMessage() );
				FacesContext.getCurrentInstance().addMessage( null, message);
				return "failedCreate";
			}
		}else{
			FacesMessage message = new FacesMessage( "Il faut sélectionner une photo " );
			FacesContext.getCurrentInstance().addMessage( null, message);
			return "failedCreate";
		}
	}
		
	/**
	 * fonction qui permet d'ajouter et/ou modifier
	 * une photo au profil de l'utilisateur
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		try{
			image_byte = event.getFile().getContents();
			user.setPicture(image_byte);
			upload = true;
			FacesMessage msg = new FacesMessage("Chargement du fichier", event.getFile().getFileName() + " réussi.");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
		}catch(Exception e){
			FacesMessage message = new FacesMessage( "Erreur lors du chargement du l'image" +e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, message);
		}
    }

	/**
	 * fonction qui permet de générer des utilisateurs
	 * lors de la création du 1er compte
	 */
	public String generer_utilisateur(){
		try{
			User userTest = userServiceLocal.getByEmail("younes.krifa@gmail.com");
			
			if(userTest == null){
				List<User> listUser = new ArrayList<User>();
				listUser.add(new User("Khaoula", "Belkaid Khaoula", "azerty", "khaoula@gmail.com", new Date(), null));
				listUser.add(new User("Younes", "Younes Krifa", "azerty", "younes.krifa@gmail.com", new Date(), null));
				listUser.add(new User("Jean", "Jean Dupond", "azerty", "jean@gmail.com", new Date(), null));
				listUser.add(new User("Marc", "Marc Dupond", "azerty", "marc@gmail.com", new Date(), null));
				listUser.add(new User("Jaques", "Jaques Dupond", "azerty", "jacques@gmail.com", new Date(), null));
					
				for(User user : listUser)
					userServiceLocal.create(user);
			}
			FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
			FacesContext.getCurrentInstance().addMessage( null, message);

			return "successCreate";
		}catch(Exception e){
			FacesMessage message = new FacesMessage( "Erreur lors de la génération des utilisateurs" +e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, message);
			return "failedCreate";
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public byte[] getImage_byte() {
		return image_byte;
	}

	public void setImage_byte(byte[] image_byte) {
		this.image_byte = image_byte;
	}

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	} 
	
}
