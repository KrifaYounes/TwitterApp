package org.isima.twitter.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-02-28T15:53:38.359+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, String> nom;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, byte[]> picture;
	public static volatile SingularAttribute<User, Date> date_creation;
	public static volatile ListAttribute<User, Follower> listFollower;
	public static volatile ListAttribute<User, Follower> listFollowing;
	public static volatile ListAttribute<User, Tweet> listTweet;
	public static volatile ListAttribute<User, Retweet> listRetweet;
}
