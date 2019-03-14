package br.com.twtter.filter.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.twtter.filter.entity.Tweet;

@EnableAutoConfiguration
@SpringBootTest(classes = TweetRepository.class)
class TwitterRepositoryTest extends AbstractRepositoryContext {

	@Autowired
	private TweetRepository repository;

	private Tweet twitter;

	@BeforeEach
	public void init() {
		twitter = Tweet.builder()
				.createdAt(LocalDateTime.now())
				.text("Mensagem de teste")
				.profileFollowersCount(10)
				.build();

	}

	@Test
	void shouldSaveTwitterInfo() {
		Tweet saved = repository.save(twitter);
		assertThat(saved.getId(), notNullValue());
	}

	@Test
	public void shouldReturnTopFiveUsersTwitter() {
		final Tweet tweet1 = newTweet("10 Followers", 10);
		final Tweet tweet2 = newTweet("2 Followers", 2); 
		final Tweet tweet3 = newTweet("1 Followers", 1);  
		final Tweet tweet4 = newTweet("50 Followers", 50);   
		final Tweet tweet5 = newTweet("8 Followers", 8);
		final Tweet tweet6 = newTweet("4 Followers", 4); 
		
		final List<Tweet> tweets = Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6);
		Collections.shuffle(tweets);
		repository.saveAll(tweets);

		LinkedList<Tweet> top5tweets = repository.findTop5ByOrderByProfileFollowersCountDesc();
		assertThat(top5tweets.size(), equalTo(5));
		assertThat(top5tweets.get(0), equalTo(tweet4));
		assertThat(top5tweets.get(1), equalTo(tweet1));
		assertThat(top5tweets.get(2), equalTo(tweet5));
		assertThat(top5tweets.get(3), equalTo(tweet6));
		assertThat(top5tweets.get(4), equalTo(tweet2));
	}

	private Tweet newTweet(String profileName, Integer followers) {
		return Tweet.builder()
				.profileName(profileName)
				.profileFollowersCount(followers)
				.build();
	}

}
