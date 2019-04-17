package br.com.twtter.filter.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.twtter.filter.dto.HashtagLanguageDTO;
import br.com.twtter.filter.dto.TweetDTO;
import br.com.twtter.filter.dto.TweetTimeDTO;
import br.com.twtter.filter.entity.Tweet;
import br.com.twtter.filter.entity.Tweet.GroupedByHashTag;
import br.com.twtter.filter.repository.TweetRepository;

/**
 * 
 * @author Fernando
 *
 */
@Service
public class TweetService {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<TweetDTO> findAll() {
		return tweetRepository.findAllTweets()
				.stream()
				.map(tweet -> modelMapper.map(tweet, TweetDTO.class))
				.collect(Collectors.toList());
	}

	public List<TweetTimeDTO> groupedByTime() {
		List<TweetTimeDTO> tweetTimeDTOs = new ArrayList<>();
		tweetRepository.findAll()
				.stream()
				.map(Tweet::getCreatedAt)
				.collect(Collectors.groupingBy(LocalDateTime::getHour, Collectors.counting()))
				.forEach((key, value) -> tweetTimeDTOs.add(new TweetTimeDTO(LocalTime.of(key, 0), value)));
		return tweetTimeDTOs;
	}

	public List<HashtagLanguageDTO> groupedByLanguage() {
		List<HashtagLanguageDTO> hashtagsLanguage = new ArrayList<>();
		tweetRepository.findAll()
				.stream()
				.map(tweet -> new GroupedByHashTag(tweet.getLanguage(), tweet.getHashtag().getHashTag()))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.forEach((key, value) -> hashtagsLanguage
						.add(new HashtagLanguageDTO(key.getHashTag(), key.getProfileLanguage(), value)));
		Collections.sort(hashtagsLanguage,
				Comparator.comparing(HashtagLanguageDTO::getLanguage)
						.thenComparing(HashtagLanguageDTO::getCount)
						.thenComparing(HashtagLanguageDTO::getHashtag));
		return hashtagsLanguage;
	}

}
