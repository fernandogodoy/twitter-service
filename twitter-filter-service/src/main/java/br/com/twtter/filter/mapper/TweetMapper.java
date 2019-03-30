package br.com.twtter.filter.mapper;

import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.entity.Tweet.TweetBuilder;
import br.com.twtter.filter.entity.TwitterProfile;
import br.com.twtter.filter.repository.TwitterProfileRepository;

/**
 * 
 * @author Fernando
 *
 */
@Component
public class TweetMapper implements GenericMapper<br.com.twtter.filter.entity.Tweet, Tweet> {

	@Autowired
	private TwitterProfileRepository twitterProfileRepository;

	@Override
	public br.com.twtter.filter.entity.Tweet toEntity(Tweet from) {
		return builder(from).build();
	}

	private TweetBuilder builder(Tweet from) {
		TwitterProfile twitterProfile = updateOrCreateProfile(from);

		return br.com.twtter.filter.entity.Tweet.builder()
				.createdAt(from.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.profile(twitterProfile)
				.text(from.getUnmodifiedText());
	}

	private TwitterProfile updateOrCreateProfile(Tweet from) {
		TwitterProfile twitterProfile = twitterProfileRepository.findByProfileName(from.getUser().getName());
		if (twitterProfile != null) {
			twitterProfile.updateFollowerCount(from.getUser().getFollowersCount());
		}else {
			twitterProfile = TwitterProfile.builder()
					.profileFollowersCount(from.getUser().getFollowersCount())
					.profileLanguage(from.getUser().getLanguage())
					.profileName(from.getUser().getName())
					.profileUserLocation(from.getUser().getLocation())
					.build();
		}
		return twitterProfileRepository.save(twitterProfile);
	}

	@Override
	public Tweet toObject(br.com.twtter.filter.entity.Tweet to) {
		throw new IllegalStateException();
	}

	public br.com.twtter.filter.entity.Tweet toEntity(Tweet tweet, Hashtag hashtag) {
		TweetBuilder tweetBuilder = builder(tweet);
		return tweetBuilder.hashtag(hashtag).build();
	}

}
