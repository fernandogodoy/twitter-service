package br.com.twtter.filter.repository;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

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
@SpringBootTest(classes = TweetRepository.class)
class TweetRepositoryTest extends AbstractRepositoryContext {

	@Autowired
	private TweetRepository repository;

	private Tweet twitter;

	@BeforeEach
	public void init() {
		Faker faker = Faker.instance();
		TwitterProfile twitterProfile = TwitterProfile.builder()
				.profileName(faker.gameOfThrones().character())
				.build();

		twitter = Tweet.builder()
				.createdAt(LocalDateTime.now())
				.profile(twitterProfile)
				.text("Mensagem de teste")
				.build();

	}

	@AfterEach
	public void end() {
		repository.deleteAll();
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

}
