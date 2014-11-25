package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.isima.twitter.model.Follower;
import org.isima.twitter.model.Follower_;
import org.isima.twitter.model.User;

/**
 * Session Bean implementation class FollowerService
 */
@Stateless
public class FollowerService implements FollowerServiceLocal {

	@PersistenceContext 
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public FollowerService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Follower create(Follower follower) {
		// TODO Auto-generated method stub
		em.persist(follower);
		return follower;
	}

	@Override
	public List<Follower> getFollowerByUserFollower(User user) {
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Follower> criteriabCriteriaQuery = criteriaBuilder.createQuery(Follower.class);
		// select from the User table
		Root<Follower> from = criteriabCriteriaQuery.from(Follower.class);
			// where 
			criteriabCriteriaQuery.where( 
					criteriaBuilder.equal(from.get(Follower_.user_follower), user));

		//execute the query
		TypedQuery<Follower> query = em.createQuery(criteriabCriteriaQuery);
		List<Follower> resultList = query.getResultList();
		if(resultList != null ){
			return resultList;
		}
		return null;
	}

	
	@Override
	public List<Follower> getFollowerByUserFollowing(User user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Follower> criteriabCriteriaQuery = criteriaBuilder.createQuery(Follower.class);
		// select from the User table
		Root<Follower> from = criteriabCriteriaQuery.from(Follower.class);
			// where 
			criteriabCriteriaQuery.where( 
					criteriaBuilder.equal(from.get(Follower_.user_following), user));

		//execute the query
			
		TypedQuery<Follower> query = em.createQuery(criteriabCriteriaQuery);
		List<Follower> resultList = query.getResultList();
		if(resultList != null ){
			return resultList;
		}
		return null;
	}
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Follower follower = em.find(Follower.class, id);
		em.remove(follower);
	}

	@Override
	public Follower getFollower(User user_follower, User user_following) {
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Follower> criteriabCriteriaQuery = criteriaBuilder.createQuery(Follower.class);
		// select from the User table
		Root<Follower> from = criteriabCriteriaQuery.from(Follower.class);
		// where 
		criteriabCriteriaQuery.where( 
				criteriaBuilder.and(
						criteriaBuilder.equal(from.get(Follower_.user_follower), user_follower),
						criteriaBuilder.equal(from.get(Follower_.user_following), user_following) )
				);
		
		//execute the query
		TypedQuery<Follower> query = em.createQuery(criteriabCriteriaQuery);
		List<Follower> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0){
			return resultList.get(0);
		}
		return null;
	}

	
	
	
	
	
	

}
