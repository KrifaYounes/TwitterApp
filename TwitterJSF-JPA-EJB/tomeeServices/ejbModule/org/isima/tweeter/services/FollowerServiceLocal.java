package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.Local;

import org.isima.twitter.model.Follower;
import org.isima.twitter.model.User;

@Local
public interface FollowerServiceLocal {

	public Follower create(Follower follower);
	public void delete(int id);
	public List<Follower> getFollowerByUserFollower(User user);
	public List<Follower> getFollowerByUserFollowing(User user);
	public Follower getFollower(User user_follower, User user_following);
}
