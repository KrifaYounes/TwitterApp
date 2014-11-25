package org.isima.twitter.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-15T01:43:16.416+0100")
@StaticMetamodel(Hashtag.class)
public class Hashtag_ {
	public static volatile SingularAttribute<Hashtag, Integer> id;
	public static volatile SingularAttribute<Hashtag, String> nom_tag;
	public static volatile SingularAttribute<Hashtag, Tweet> tweet_hashtag;
}
