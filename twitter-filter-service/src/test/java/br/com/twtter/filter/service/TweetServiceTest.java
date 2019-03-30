package br.com.twtter.filter.service;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.javafaker.Faker;

import br.com.twtter.filter.config.ModelMapperConfig;
import br.com.twtter.filter.dto.HashtagLanguageDTO;
import br.com.twtter.filter.dto.TweetDTO;
import br.com.twtter.filter.dto.TweetTimeDTO;
import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.mapper.TweetMapper;
import br.com.twtter.filter.repository.HashtagRepository;
import br.com.twtter.filter.repository.TweetRepository;
import br.com.twtter.filter.repository.TwitterProfileRepository;

@ExtendWith(SpringExtension.class)
@Import(ModelMapperConfig.class)
@SpringBootTest(classes = { TweetService.class, TweetMapper.class })
class TweetServiceTest {

	@Autowired
	private TweetService service;

	@MockBean
	private HashtagRepository hashtagRepository;

	@MockBean
	private TwitterProfileRepository twitterProfileRepository;

	@MockBean
	private Twitter twitterApi;

	@MockBean
	private TweetRepository tweetRepository;

	@MockBean
	private SearchOperations searchOperation;

	@Test
	void shouldReturnGroupedByTime() {
		LocalDateTime now = LocalDateTime.now();
		br.com.twtter.filter.entity.Tweet tweet1 = br.com.twtter.filter.entity.Tweet.builder()
				.createdAt(now)
				.build();
		br.com.twtter.filter.entity.Tweet tweet3 = br.com.twtter.filter.entity.Tweet.builder()
				.createdAt(now.plusHours(2))
				.build();

		given(tweetRepository.findAll()).willReturn(Arrays.asList(tweet1, tweet1, tweet3));

		List<TweetTimeDTO> groupedBy = service.groupedByTime();
		assertThat(groupedBy.size(), Matchers.equalTo(2));
	}

	@Test
	void shouldReturnGroupedByLanguage() {
		LocalDateTime now = LocalDateTime.now();
		br.com.twtter.filter.entity.Tweet tweet1 = br.com.twtter.filter.entity.Tweet.builder()
				.createdAt(now)
				.hashtag(new Hashtag("open"))
				.profile(br.com.twtter.filter.entity.TwitterProfile.builder().profileLanguage("pt").build())
				.build();
		br.com.twtter.filter.entity.Tweet tweet3 = br.com.twtter.filter.entity.Tweet.builder()
				.createdAt(now.plusHours(2))
				.hashtag(new Hashtag("open"))
				.profile(br.com.twtter.filter.entity.TwitterProfile.builder().profileLanguage("en").build())
				.build();

		given(tweetRepository.findAll()).willReturn(Arrays.asList(tweet1, tweet1, tweet3));

		List<HashtagLanguageDTO> groupedBy = service.groupedByLanguage();
		System.out.println(groupedBy);
		assertThat(groupedBy.size(), Matchers.equalTo(2));
	}
	
	@Test
	void shouldListAll() {
		LocalDateTime now = LocalDateTime.now();
		br.com.twtter.filter.entity.Tweet tweet1 = br.com.twtter.filter.entity.Tweet.builder()
				.createdAt(now)
				.text(Faker.instance().shakespeare().hamletQuote())
				.hashtag(new Hashtag("open"))
				.profile(br.com.twtter.filter.entity.TwitterProfile.builder()
						.id(1l)
						.profileFollowersCount(123)
						.profileName(Faker.instance().dragonBall().character())
						.profileUserLocation("Brasil")
						.profileLanguage("pt")
						.build())
				.build();
		br.com.twtter.filter.entity.Tweet tweet3 = br.com.twtter.filter.entity.Tweet.builder()
				.createdAt(now.plusHours(2))
				.hashtag(new Hashtag("open"))
				.text(Faker.instance().shakespeare().hamletQuote())
				.profile(br.com.twtter.filter.entity.TwitterProfile.builder().profileLanguage("en").build())
				.build();

		given(tweetRepository.findAllTweets()).willReturn(Arrays.asList(tweet1, tweet1, tweet3));
		
		List<TweetDTO> all = service.findAll();
		assertTrue(all.size() == 3);
		
	}

}
