package br.com.twtter.filter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	
	@Query("SELECT o FROM Tweet o JOIN FETCH o.profile ORDER BY o.id DESC")
	List<Tweet> findAllTweets();

}
