package br.com.twtter.filter.config;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.twtter.filter.TwitterServiceApp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TwitterServiceApp.class })
class TwitterFilterTest {

	@Autowired
	private Twitter twitterFilter;

	@Test
	void shouldReturnTwitters() {
		SearchResults searchResults = twitterFilter.searchOperations().search("#Openbanking", 100);
		assertThat(searchResults.getTweets().size(), equalTo(100));
	}

}
