package br.com.twtter.filter.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.social.twitter.api.SearchMetadata;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.javafaker.Faker;

import br.com.twtter.filter.config.ModelMapperConfig;
import br.com.twtter.filter.dto.TopUserFollowerCountDTO;
import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.mapper.TweetMapper;
import br.com.twtter.filter.repository.HashtagRepository;
import br.com.twtter.filter.repository.TweetRepository;

@ExtendWith(SpringExtension.class)
@Import(ModelMapperConfig.class)
@SpringBootTest(classes = { TweetService.class, TweetMapper.class })
class TweetServiceTest {

	@Autowired
	private TweetService service;

	@Autowired
	private TweetMapper mapper;

	@MockBean
	private HashtagRepository hashtagRepository;

	@MockBean
	private Twitter twitterApi;

	@MockBean
	private TweetRepository tweetRepository;

	@MockBean
	private SearchOperations searchOperation;

	@Test
	void shouldSaveTweets() {

		Tweet tweet = newTweet();
		br.com.twtter.filter.entity.Tweet tweetEntity = mapper.toEntity(tweet);
		tweetEntity.setId(1l);

		given(hashtagRepository.findAll()).willReturn(Arrays.asList(new Hashtag("Valor")));

		given(twitterApi.searchOperations()).willReturn(searchOperation);
		given(tweetRepository.findByHashtag(ArgumentMatchers.any())).willReturn(Arrays.asList(tweetEntity));
		given(searchOperation.search(anyString(), anyInt()))
				.willReturn(new SearchResults(Arrays.asList(tweet, newTweet()), new SearchMetadata(1, 1)));

		service.loadTwitters();

		verify(tweetRepository, atLeast(1)).saveAll(any());
	}

	@Test
	void shouldReturnTopUser() {
		LinkedList<br.com.twtter.filter.entity.Tweet> tweets = new LinkedList<>();
		tweets.add(newTweet("1 follower", 1));
		tweets.add(newTweet("2 follower2", 2));
		given(tweetRepository.findTop5ByOrderByProfileFollowersCountDesc())
				.willReturn(tweets);

		List<TopUserFollowerCountDTO> to5Profiles = service.findTo5Profiles();
		assertThat(to5Profiles.get(0).getProfileFollowersCount(), equalTo(1));
		assertThat(to5Profiles.get(0).getProfileName(), equalTo("1 follower"));
		assertThat(to5Profiles.get(1).getProfileFollowersCount(), equalTo(2));
		assertThat(to5Profiles.get(1).getProfileName(), equalTo("2 follower2"));
	}

	private br.com.twtter.filter.entity.Tweet newTweet(String profileName, Integer followers) {
		return br.com.twtter.filter.entity.Tweet.builder()
				.profileName(profileName)
				.profileFollowersCount(followers)
				.build();
	}

	private Tweet newTweet() {
		final Tweet tweet = tweet();
		tweetProfile(tweet);
		return tweet;
	}

	private void tweetProfile(final Tweet tweet) {
		final Faker faker = new Faker();
		final long id = faker.random().nextLong();
		final String screenName = faker.superhero().descriptor();
		final String name = faker.superhero().name();
		final String profileImageUrl = faker.avatar().image();
		final String url = faker.company().url();
		final String descriptor = faker.superhero().descriptor();
		final String location = faker.address().city();
		final Date createdAt = faker.date().past(1, TimeUnit.DAYS);
		tweet.setUser(new TwitterProfile(id, screenName, name, url, profileImageUrl, descriptor, location, createdAt));
	}

	private Tweet tweet() {
		final Faker faker = new Faker();
		final Long id = faker.random().nextLong();
		final String text = faker.shakespeare().hamletQuote();
		final Date createdAt = faker.date().past(1, TimeUnit.DAYS);
		final String fromUser = faker.artist().name();
		final String profileImageUrl = faker.avatar().image();
		final Long toUserId = faker.random().nextLong();
		final Long fromUserId = faker.random().nextLong();
		final String languageCode = "pt";
		final String source = faker.company().url();
		final Tweet tweet = new Tweet(id, text, createdAt, fromUser, profileImageUrl, toUserId, fromUserId,
				languageCode, source);
		return tweet;
	}

}
