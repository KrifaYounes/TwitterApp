package org.isima.twitter.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-11T16:29:38.680+0100")
@StaticMetamodel(Follower.class)
public class Follower_ {
	public static volatile SingularAttribute<Follower, Integer> id;
	public static volatile SingularAttribute<Follower, User> user_follower;
	public static volatile SingularAttribute<Follower, User> user_following;
	public static volatile SingularAttribute<Follower, Date> date_creation;
}
