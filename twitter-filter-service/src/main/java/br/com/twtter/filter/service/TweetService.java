package br.com.twtter.filter.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import br.com.twtter.filter.dto.TopUserFollowerCountDTO;
import br.com.twtter.filter.dto.TweetDTO;
import br.com.twtter.filter.dto.TweetTimeDTO;
import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.entity.Tweet;
import br.com.twtter.filter.mapper.TweetMapper;
import br.com.twtter.filter.repository.HashtagRepository;
import br.com.twtter.filter.repository.TweetRepository;
import br.com.twtter.filter.repository.TwitterProfileRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Fernando
 *
 */
@Service
@Slf4j
public class TweetService {

	@Autowired
	private Twitter twitterApi;

	@Autowired
	private HashtagRepository hashTagRepository;

	@Autowired
	private TweetMapper mapper;

	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private TwitterProfileRepository twitterProfileRepository;

	@Autowired
	private ModelMapper modelMapper;

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

	public List<TweetDTO> findAll() {
		return tweetRepository.findAll(Sort.by(Direction.DESC, "id"))
				.stream()
				.map(tweet -> modelMapper.map(tweet, TweetDTO.class))
				.collect(Collectors.toList());
	}

	public List<TopUserFollowerCountDTO> findTo5Profiles() {
		return twitterProfileRepository.findTop5ByOrderByProfileFollowersCountDesc()
				.stream()
				.map(tweet -> modelMapper.map(tweet, TopUserFollowerCountDTO.class))
				.collect(Collectors.toList());
	}

	public List<TweetTimeDTO> groupedByTime() {
		List<TweetTimeDTO> tweetTimeDTOs = new ArrayList<>();
		tweetRepository.findAll()
				.stream()
				.map(Tweet::getCreatedAt)
				.collect(Collectors.groupingBy(LocalDateTime::getHour, Collectors.counting()))
				.forEach((key, value) -> {
					tweetTimeDTOs.add(new TweetTimeDTO(LocalTime.of(key, 0), value));
				});
		return tweetTimeDTOs;
	}

}
