package br.com.twtter.filter.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.twtter.filter.config.ModelMapperConfig;
import br.com.twtter.filter.dto.TopUserFollowerCountDTO;
import br.com.twtter.filter.mapper.TweetMapper;
import br.com.twtter.filter.repository.TwitterProfileRepository;

@ExtendWith(SpringExtension.class)
@Import(ModelMapperConfig.class)
@SpringBootTest(classes = { TwitterProfileService.class, TweetMapper.class })
class TwitterProfileServiceTest {
	
	@MockBean
	private TwitterProfileRepository twitterProfileRepository;
	
	@Autowired
	private TwitterProfileService service;

	@Test
	void shouldReturnTopUser() {
		LinkedList<br.com.twtter.filter.entity.TwitterProfile> tweets = new LinkedList<>();
		tweets.add(newTweet("1 follower", 1));
		tweets.add(newTweet("2 follower2", 2));
		given(twitterProfileRepository.findTop5ByOrderByProfileFollowersCountDesc())
				.willReturn(tweets);

		List<TopUserFollowerCountDTO> to5Profiles = service.findTo5Profiles();
		assertThat(to5Profiles.get(0).getProfileFollowersCount(), equalTo(1));
		assertThat(to5Profiles.get(0).getProfileName(), equalTo("1 follower"));
		assertThat(to5Profiles.get(1).getProfileFollowersCount(), equalTo(2));
		assertThat(to5Profiles.get(1).getProfileName(), equalTo("2 follower2"));
	}
	
	private br.com.twtter.filter.entity.TwitterProfile newTweet(String profileName, Integer followers) {
		return br.com.twtter.filter.entity.TwitterProfile.builder()
				.profileName(profileName)
				.profileFollowersCount(followers)
				.build();
	}

}
