package org.isima.twitter.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-02-18T02:34:27.350+0100")
@StaticMetamodel(Retweet.class)
public class Retweet_ extends Tweet_ {
	public static volatile SingularAttribute<Retweet, Tweet> id_tweet_retweeter;
	public static volatile SingularAttribute<Retweet, User> id_user_retweeter;
}
