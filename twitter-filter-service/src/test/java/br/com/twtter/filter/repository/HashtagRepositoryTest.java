package br.com.twtter.filter.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.entity.Tweet;

@EnableAutoConfiguration
@SpringBootTest(classes = HashtagRepository.class)
class HashtagRepositoryTest extends AbstractRepositoryContext {

	@Autowired
	private HashtagRepository repository;

	private Hashtag hashTag;

	@BeforeEach
	public void init() {
		hashTag = new Hashtag("default", Tweet.builder().id(2L).build());
	}

	@AfterEach
	public void end() {
		repository.deleteAll();
	}

	@Test
	void shouldFindByHashtag() {
		repository.save(hashTag);

		Hashtag hashTah = repository.findByHashTag("default");
		assertThat(hashTah.getHashTag(), equalTo("default"));
	}

	@Test
	void shouldConsistenceSaveHashtag() {
		repository.save(hashTag);

		Hashtag newHashTag = new Hashtag("default");

		repository.consistenceSave(newHashTag);
		long counted = repository.count();
		assertThat(counted, equalTo(1L));
	}

}
