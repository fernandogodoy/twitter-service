package br.com.twtter.filter.service.job;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.entity.Tweet;
import br.com.twtter.filter.mapper.TweetMapper;
import br.com.twtter.filter.repository.HashtagRepository;
import br.com.twtter.filter.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TwitterFilterJob {

	@Autowired
	private Twitter twitterApi;

	@Autowired
	private HashtagRepository hashTagRepository;

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private TweetMapper mapper;

	@Scheduled(fixedRate = 900000)
	public void loadTwitters() {
		log.info("Iniciando busca por novos tweets");

		List<Hashtag> hashTags = hashTagRepository.findAll();
		log.info("HashTags: " + hashTags.stream()
				.map(Hashtag::getHashTag)
				.map(Objects::toString)
				.collect(Collectors.joining(", ")));

		Set<Tweet> tweets = getTeets(hashTags);
		log.info("Quantidade de tweets filtrados: " + tweets.size());

		removeDuplicates(hashTags, tweets);
		log.info("Restante apos remocao de duplicados: " + tweets.size());

		tweetRepository.saveAll(tweets);
	}

	private void removeDuplicates(List<Hashtag> hashTags, Set<Tweet> tweets) {
		hashTags.forEach(hashTag -> {
			List<Tweet> savedTweets = tweetRepository.findByHashtag(hashTag);
			tweets.removeIf(tweet -> savedTweets.contains(tweet));
		});
	}

	private Set<Tweet> getTeets(List<Hashtag> hashTags) {
		Set<Tweet> tweets = new HashSet<>();
		hashTags.forEach(hashtag -> {
			twitterApi.searchOperations().search("#" + hashtag.getHashTag(), 100)
					.getTweets()
					.forEach(tweet -> tweets.add(mapper.toEntity(tweet, hashtag)));
		});
		return tweets;
	}
}
