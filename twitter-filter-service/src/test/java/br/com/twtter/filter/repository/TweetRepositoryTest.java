package br.com.twtter.filter.repository;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.javafaker.Faker;

import br.com.twtter.filter.entity.Tweet;
import br.com.twtter.filter.entity.TwitterProfile;

@EnableAutoConfiguration
@SpringBootTest(classes = { TweetRepository.class, TwitterProfileRepository.class })
class TweetRepositoryTest extends AbstractRepositoryContext {

	@Autowired
	private TweetRepository repository;

	@Autowired
	private TwitterProfileRepository profileRepository;

	private Tweet twitter;

	@BeforeEach
	public void init() {
		Faker faker = Faker.instance();
		
		TwitterProfile twitterProfile = TwitterProfile.builder()
				.profileName(faker.gameOfThrones().character())
				.profileFollowersCount(123)
				.profileLanguage("pt")
				.profileUserLocation("Brasil")
				.build();
		twitterProfile = profileRepository.save(twitterProfile);
		
		twitter = Tweet.builder()
				.createdAt(LocalDateTime.now())
				.profile(twitterProfile)
				.text(faker.shakespeare().hamletQuote())
				.build();

	}

	@AfterEach
	public void end() {
		repository.deleteAll();
		profileRepository.deleteAll();
	}

	@Test
	void shouldSaveTwitterInfo() {
		Tweet saved = repository.save(twitter);
		assertThat(saved.getId(), notNullValue());
		assertThat(saved.getProfile().getId(), notNullValue());

		saved.getProfile().updateFollowerCount(10);
		Tweet updated = repository.save(saved);
		assertThat(updated.getProfile().getProfileFollowersCount(), notNullValue());
	}

	@Test
	public void shouldFindSorted() {
		repository.save(twitter);

		List<Tweet> all = repository.findAllTweets();
		System.out.println(all);
	}

}
