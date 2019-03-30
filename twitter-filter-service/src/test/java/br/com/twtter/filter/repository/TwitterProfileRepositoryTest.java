package br.com.twtter.filter.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.javafaker.Faker;

import br.com.twtter.filter.entity.TwitterProfile;

@EnableAutoConfiguration
@SpringBootTest(classes = TwitterProfileRepository.class)
class TwitterProfileRepositoryTest extends AbstractRepositoryContext {

	@Autowired
	private TwitterProfileRepository repository;

	@BeforeEach
	private void init() {
		repository.save(TwitterProfile.builder().profileName("User 1").build());
	}

	@Test
	void shouldFindByName() {
		TwitterProfile profile = repository.findByProfileName("User 1");
		assertTrue(profile != null);
	}
	
	@Test
	public void shouldReturnTopFiveUsersTwitter() {
		Faker instance = Faker.instance();
		final TwitterProfile tweet1 = newTweet(instance.gameOfThrones().character(), 10);
		final TwitterProfile tweet2 = newTweet(instance.gameOfThrones().character(), 2); 
		final TwitterProfile tweet3 = newTweet(instance.gameOfThrones().character(), 1);  
		final TwitterProfile tweet4 = newTweet(instance.gameOfThrones().character(), 50);   
		final TwitterProfile tweet5 = newTweet(instance.gameOfThrones().character(), 8);
		final TwitterProfile tweet6 = newTweet(instance.gameOfThrones().character(), 4); 
		
		final List<TwitterProfile> tweets = Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6);
		Collections.shuffle(tweets);
		repository.saveAll(tweets);

		List<TwitterProfile> top5tweets = repository.findTop5ByOrderByProfileFollowersCountDesc();
		assertThat(top5tweets.size(), equalTo(5));
		assertThat(top5tweets.get(0), equalTo(tweet4));
		assertThat(top5tweets.get(1), equalTo(tweet1));
		assertThat(top5tweets.get(2), equalTo(tweet5));
		assertThat(top5tweets.get(3), equalTo(tweet6));
		assertThat(top5tweets.get(4), equalTo(tweet2));
	}

	private TwitterProfile newTweet(String profileName, Integer followers) {
		return TwitterProfile.builder()
				.profileName(profileName)
				.profileFollowersCount(followers)
				.build();
	}


}
