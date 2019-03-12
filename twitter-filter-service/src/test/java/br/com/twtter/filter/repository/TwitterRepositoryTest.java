package br.com.twtter.filter.repository;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

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

}
