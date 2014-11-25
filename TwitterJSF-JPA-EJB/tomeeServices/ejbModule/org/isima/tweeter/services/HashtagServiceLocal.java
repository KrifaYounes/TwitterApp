package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.Local;

import org.isima.twitter.model.Hashtag;

@Local
public interface HashtagServiceLocal {

	public List<Hashtag> getAllHashtagByNom(String nom_tag);
	public Hashtag getByNom(String nom_tag);
	public void create(Hashtag hashtag);
	public List<Hashtag> getAllHashtag();

	public void create(String nom_tag, int id_tweet);

}
