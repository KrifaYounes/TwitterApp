package org.isima.twitter.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-14T00:57:13.107+0100")
@StaticMetamodel(Tweet.class)
public class Tweet_ {
	public static volatile SingularAttribute<Tweet, Integer> id;
	public static volatile SingularAttribute<Tweet, String> message;
	public static volatile SingularAttribute<Tweet, Date> date_creation;
	public static volatile SingularAttribute<Tweet, User> user_tweeter;
	public static volatile ListAttribute<Tweet, Response> listResponse;
	public static volatile ListAttribute<Tweet, Hashtag> lisHashtag;
}
