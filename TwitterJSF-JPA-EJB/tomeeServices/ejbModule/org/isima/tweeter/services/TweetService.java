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
import org.isima.twitter.model.Tweet;
import org.isima.twitter.model.Tweet_;
import org.isima.twitter.model.User;

/**
 * Session Bean implementation class TweetService
 */
@Stateless
public class TweetService implements TweetServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Tweet create(Tweet tweet) {
		// TODO Auto-generated method stub
		try{
			em.persist(tweet);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tweet;	
	}

	@Override
	public List<Tweet> getAllTweetByUser(User user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Tweet> criteriabCriteriaQuery = criteriaBuilder.createQuery(Tweet.class);
		// select from the User table
		Root<Tweet> from = criteriabCriteriaQuery.from(Tweet.class);
		// where 
		criteriabCriteriaQuery.where(
				criteriaBuilder.equal(from.get(Tweet_.user_tweeter), user)	

			);
				
		//on tri sur la date de creation des tweet
		criteriabCriteriaQuery.orderBy(criteriaBuilder.desc(from.get(Tweet_.date_creation)));
		//execute the query
		TypedQuery<Tweet> query = em.createQuery(criteriabCriteriaQuery);
		List<Tweet> resultList = query.getResultList();	
			
		return resultList;	
	}

	@Override
	public Tweet getTweetById(int id) {
		// TODO Auto-generated method stub
		return (Tweet)em.find(Tweet.class,id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Tweet tweet = em.find(Tweet.class, id);
		em.remove(tweet);
		
		
	}

	@Override
	public List<Tweet> getAllTweetByFollowing(Follower follower) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Tweet> criteriabCriteriaQuery = criteriaBuilder.createQuery(Tweet.class);
		// select from the User table
		Root<Tweet> from = criteriabCriteriaQuery.from(Tweet.class);
		// where 
		criteriabCriteriaQuery.where(
				criteriaBuilder.equal(from.get(Tweet_.user_tweeter), follower.getUser_following())	
				
			);
				
		//on tri sur la date de creation des tweet
		criteriabCriteriaQuery.orderBy(criteriaBuilder.desc(from.get(Tweet_.date_creation)));
		//execute the query
		TypedQuery<Tweet> query = em.createQuery(criteriabCriteriaQuery);
		List<Tweet> resultList = query.getResultList();	
			
		return resultList;	
	}



}
