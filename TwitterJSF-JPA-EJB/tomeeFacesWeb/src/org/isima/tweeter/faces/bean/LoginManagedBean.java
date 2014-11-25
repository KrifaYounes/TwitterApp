package org.isima.tweeter.faces.bean;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.isima.tweeter.services.UserServiceLocal;
import org.isima.twitter.model.User;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class LoginManagedBean {

	@EJB
	private UserServiceLocal userService;
	
	private User user ;
	private String email;
	private String password;
	private byte[] image_byte;

	public LoginManagedBean(){		
	}

	// actions
	/**
	 * fonction qui permet de vérifier si 
	 * le mot de passe et l'adresse mail entré par
	 * l'utilisateur sont correctes pour se connecter
	 * @return
	 */
	public String login(){
		try{
			// try to log in
			user = userService.login(password, email);
			//user = userService.login("azerty", "younes@gmail.com");

			if(user != null){
				return "loginSuccess";
			}else{
				FacesMessage message = new FacesMessage( "Adresse email ou mot de passe incorrecte" );
				FacesContext.getCurrentInstance().addMessage( null, message);
				return "loginFailed";
			}
		}
		catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", e.getMessage()) );
			return "loginFailed";
		}		
	}
	
	/**
	 * fonction qui permet de déconnecter 
	 * l'utilisateur de l'application
	 * @return
	 */
	public String logout(){
		try{
		 ((javax.servlet.http.HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(true)).invalidate();
			return "logoutSuccess";
		}
		catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", e.getMessage()) );
			return "logoutFailed";
		}	
	}
	
	/**
	 * fonction qui permet d'éditer le profil
	 * d'un utilisateur
	 * @return
	 */
	public String updateUser(){
		try{
			userService.update(user.getId(),user.getEmail(),user.getNom(),user.getPicture());
			return "successUpdate";
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur lors de la mise à jour", e.getMessage()) );
			return "failedUpdate";
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
			FacesMessage msg = new FacesMessage("Chargement du fichier", event.getFile().getFileName() + " réussi.");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
		}catch(Exception e){
			FacesMessage message = new FacesMessage( "Erreur lors du chargement du l'image" +e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, message);
			//return "failedCreate";
		}

    }
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getImage_byte() {
		return image_byte;
	}

	public void setImage_byte(byte[] image_byte) {
		this.image_byte = image_byte;
	}
	
}
