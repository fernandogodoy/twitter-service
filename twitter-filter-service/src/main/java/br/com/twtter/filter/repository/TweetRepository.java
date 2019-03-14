package br.com.twtter.filter.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.entity.Tweet;

/**
 * Twitter Repository
 * 
 * @author Fernando
 *
 */
@Repository
public interface TweetRepository extends GenericRepository<Tweet> {

	List<Tweet> findByHashtag(Hashtag hashtag);

	LinkedList<Tweet> findTop5ByOrderByProfileFollowersCountDesc();

}
