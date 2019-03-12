package br.com.twtter.filter.mapper;

import java.time.ZoneId;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.entity.Tweet.TweetBuilder;

/**
 * 
 * @author Fernando
 *
 */
@Component
public class TweetMapper implements GenericMapper<br.com.twtter.filter.entity.Tweet, Tweet> {

	@Override
	public br.com.twtter.filter.entity.Tweet toEntity(Tweet from) {
		return builder(from).build();
	}

	private TweetBuilder builder(Tweet from) {
		return br.com.twtter.filter.entity.Tweet.builder()
				.createdAt(from.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.profileFollowersCount(from.getUser().getFollowersCount())
				.profileLanguage(from.getUser().getLanguage())
				.profileUserLocation(from.getUser().getLocation())
				.profileName(from.getUser().getName())
				.text(from.getUnmodifiedText());
	}

	@Override
	public Tweet toObject(br.com.twtter.filter.entity.Tweet to) {
		throw new IllegalStateException();
	}

	public br.com.twtter.filter.entity.Tweet  toEntity(Tweet tweet, Hashtag hashtag) {
		TweetBuilder tweetBuilder = builder(tweet);
		return tweetBuilder.hashtag(hashtag).build();
	}

}
