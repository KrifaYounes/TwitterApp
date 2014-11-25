package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.Local;

import org.isima.twitter.model.Follower;
import org.isima.twitter.model.Tweet;
import org.isima.twitter.model.User;

@Local
public interface TweetServiceLocal {

	Tweet create(Tweet tweet);
	void delete(int id);
	List<Tweet> getAllTweetByUser(User user);
	List<Tweet> getAllTweetByFollowing(Follower follower);

	Tweet getTweetById(int id);
}
