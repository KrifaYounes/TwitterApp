package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.isima.twitter.model.Hashtag;
import org.isima.twitter.model.Hashtag_;
import org.isima.twitter.model.Tweet;

/**
 * Session Bean implementation class HashtagService
 */
@Stateless
public class HashtagService implements HashtagServiceLocal {

	@PersistenceContext 
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public HashtagService() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public List<Hashtag> getAllHashtagByNom(String nom_tag) {
			// TODO Auto-generated method stub
			//use the criteria builder to create the query expression
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Hashtag> criteriabCriteriaQuery = criteriaBuilder.createQuery(Hashtag.class);
			// select from the User table
			Root<Hashtag> from = criteriabCriteriaQuery.from(Hashtag.class);
			// where 
			criteriabCriteriaQuery.where( 
					criteriaBuilder.equal(from.get(Hashtag_.nom_tag), nom_tag));
			criteriabCriteriaQuery.orderBy(criteriaBuilder.desc(from.get(Hashtag_.tweet_hashtag)));

			//execute the query
			TypedQuery<Hashtag> query = em.createQuery(criteriabCriteriaQuery);
			List<Hashtag> resultList = query.getResultList();	
		
			return resultList;
	}
	
	@Override
	public void create(Hashtag hashtag) {
		// TODO Auto-generated method stub
		try{	
			em.persist(hashtag);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void create(String nom_tag, int id_tweet) {
		// TODO Auto-generated method stub
		try{
			Tweet tweet = em.find(Tweet.class, id_tweet);
			Hashtag hashtag = new Hashtag(nom_tag,tweet);
		em.persist(hashtag);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public Hashtag getByNom(String nom_tag) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Hashtag> criteriabCriteriaQuery = criteriaBuilder.createQuery(Hashtag.class);
		// select from the User table
		Root<Hashtag> from = criteriabCriteriaQuery.from(Hashtag.class);
		// where 
		criteriabCriteriaQuery.where( 
				criteriaBuilder.equal(from.get(Hashtag_.nom_tag), nom_tag) 
		);
				
		//execute the query
		TypedQuery<Hashtag> query = em.createQuery(criteriabCriteriaQuery);
		List<Hashtag> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0){
			return resultList.get(0);
		}
				
		return null;	
	}
	@Override
	public List<Hashtag> getAllHashtag() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Hashtag> criteriabCriteriaQuery = criteriaBuilder.createQuery(Hashtag.class);
		// select from the User table	
		Root<Hashtag> from = criteriabCriteriaQuery.from(Hashtag.class);

		criteriabCriteriaQuery.orderBy(criteriaBuilder.desc(from.get(Hashtag_.tweet_hashtag)));

		//execute the query
		TypedQuery<Hashtag> query = em.createQuery(criteriabCriteriaQuery);
		List<Hashtag> resultList = query.getResultList();	
	
		return resultList;
	}

	
}
