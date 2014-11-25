package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.isima.twitter.model.User;
import org.isima.twitter.model.User_;

@Stateless
public class UserService implements UserServiceLocal {
	
	// injected attributes
	
	@PersistenceContext
	private EntityManager em;
	
	
	// methods 
	
	/**
	 * méthode qui permet de vérifier si le nom d'utilisateur 
	 * et l'email entré par l'utilisateur sont unique 
	 * pour créer un utilisateur
	 */
	@Override
	public User login(String password, String email) {
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriabCriteriaQuery = criteriaBuilder.createQuery(User.class);
		// select from the User table
		Root<User> from = criteriabCriteriaQuery.from(User.class);
		// where 
		criteriabCriteriaQuery.where( 
				criteriaBuilder.and(
						criteriaBuilder.equal(from.get(User_.password), password),
						criteriaBuilder.equal(from.get(User_.email), email) )
				);
		
		//execute the query
		TypedQuery<User> query = em.createQuery(criteriabCriteriaQuery);
		List<User> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0){
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public User create(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public List<User> listAllUser(int id) {
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriabCriteriaQuery = criteriaBuilder.createQuery(User.class);
		// select from the User table	
		Root<User> from = criteriabCriteriaQuery.from(User.class);
		// where 
		criteriabCriteriaQuery.where( 
			criteriaBuilder.not(
				criteriaBuilder.equal(from.get(User_.id), id)
				));
		
		//execute the query
		TypedQuery<User> query = em.createQuery(criteriabCriteriaQuery);
		List<User> resultList = query.getResultList();	
	
		return resultList;
	}

	
	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return (User) em.find(User.class, id);
	}

	@Override
	public User getByLogin(String login) {
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriabCriteriaQuery = criteriaBuilder.createQuery(User.class);
		// select from the User table
		Root<User> from = criteriabCriteriaQuery.from(User.class);
		// where 
		criteriabCriteriaQuery.where( 
				criteriaBuilder.equal(from.get(User_.login), login) 
		);
				
		//execute the query
		TypedQuery<User> query = em.createQuery(criteriabCriteriaQuery);
		List<User> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0){
			return resultList.get(0);
		}
				
		return null;	
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriabCriteriaQuery = criteriaBuilder.createQuery(User.class);
		// select from the User table
		Root<User> from = criteriabCriteriaQuery.from(User.class);
		// where 
		criteriabCriteriaQuery.where( 
				criteriaBuilder.equal(from.get(User_.email), email) 
		);
				
		//execute the query
		TypedQuery<User> query = em.createQuery(criteriabCriteriaQuery);
		List<User> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0){
			return resultList.get(0);
		}
				
		return null;	
	}

	@Override
	public void update(int id,String email, String nom, byte[] photo) {
		// TODO Auto-generated method stub
		try{
			User userUpdate = em.find(User.class, id);
			userUpdate.setEmail(email);
			userUpdate.setNom(nom);
			userUpdate.setPicture(photo);
			em.merge(userUpdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public User getByNom(String nom) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriabCriteriaQuery = criteriaBuilder.createQuery(User.class);
		// select from the User table
		Root<User> from = criteriabCriteriaQuery.from(User.class);
		// where 
		criteriabCriteriaQuery.where( 
				criteriaBuilder.equal(from.get(User_.nom), nom) 
		);
				
		//execute the query
		TypedQuery<User> query = em.createQuery(criteriabCriteriaQuery);
		List<User> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0){
			return resultList.get(0);
		}
				
		return null;		
	}
	
}
