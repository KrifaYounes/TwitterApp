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
 * Classe qui permet de vérifier si l'email
 * entrer par l'utilisateur lors de l'inscription 
 * n'existe pas en base de données
 * @author Younes
 *
 */
@ManagedBean
@RequestScoped
public class EmailValidator implements Validator{
	private static final String EMAIL_EXISTE_DEJA = "Cette addresse email est déjà utilisée";
	
	@EJB
	private UserServiceLocal userService;
	
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value)
			throws ValidatorException {
		// TODO Auto-generated method stub
		String email = (String) value;
		try {
			if ( userService.getByEmail(email) != null) {
				throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_INFO, EMAIL_EXISTE_DEJA, null ) );
			}
						
		} catch ( Exception e ) {
				FacesMessage message = new FacesMessage(
				FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
				FacesContext facesContext =
				FacesContext.getCurrentInstance();
				facesContext.addMessage( component.getClientId(facesContext ), message );
		}
	}
		

}
