package org.isima.tweeter.faces.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.isima.tweeter.services.HashtagServiceLocal;
import org.isima.twitter.model.Hashtag;

/**
 * classe qui permet d'avoir la liste 
 * des tweets ou apparait un hashtag selectionné
 * par l'utilisateur
 * @author Younes
 *
 */
@ManagedBean
public class HashtagBean {

	@EJB
	private HashtagServiceLocal hashtagServiceLocal;

	private String nom_tag;
	
	private List<Hashtag> listHashtag;
	
	//liste des hashtag utiliser pour la rubrique tendance du moment
	private List<Hashtag> listAllHashtag;

	public HashtagBean(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		nom_tag = request.getParameter("hashtag");
	} 

	@PostConstruct
	public void init(){
		try{
			listHashtag = hashtagServiceLocal.getAllHashtagByNom(nom_tag);
			listAllHashtag = hashtagServiceLocal.getAllHashtag();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getNom_tag() {
		return nom_tag;
	}

	public void setNom_tag(String nom_tag) {
		this.nom_tag = nom_tag;
	}

	public List<Hashtag> getListHashtag() {
		return listHashtag;
	}

	public void setListHashtag(List<Hashtag> listHashtag) {
		this.listHashtag = listHashtag;
	}

	public List<Hashtag> getListAllHashtag() {
		return listAllHashtag;
	}

	public void setListAllHashtag(List<Hashtag> listAllHashtag) {
		this.listAllHashtag = listAllHashtag;
	}

	
}
