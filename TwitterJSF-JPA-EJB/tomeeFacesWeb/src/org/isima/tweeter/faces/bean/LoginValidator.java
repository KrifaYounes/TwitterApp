package org.isima.tweeter.faces.bean;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.isima.tweeter.services.UserServiceLocal;

/**
 * Classe qui permet de vérifier si le 
 * login d'un utilisateur n'existe pas
 * @author Younes
 *
 */
@ManagedBean
@RequestScoped
public class LoginValidator implements Validator{
	private static final String LOGIN_EXISTE_DEJA = "Ce nom d'utilisateur est déjà utilisé";
	
	@EJB
	private UserServiceLocal userService;
	
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value)
			throws ValidatorException {
		// TODO Auto-generated method stub
		String login = (String) value;
		try {
			if ( userService.getByLogin(login) != null) {
				throw new ValidatorException(
						new FacesMessage(
								FacesMessage.SEVERITY_INFO, LOGIN_EXISTE_DEJA, null ) );
			}
						
		} catch ( Exception e ) {
			FacesMessage message = new FacesMessage(
			FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage( component.getClientId(facesContext ), message );
		}
	}
		

}
