package br.com.twtter.filter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.twtter.filter.dto.TopUserFollowerCountDTO;
import br.com.twtter.filter.repository.TwitterProfileRepository;

@Service
public class TwitterProfileService {

	@Autowired
	private TwitterProfileRepository twitterProfileRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<TopUserFollowerCountDTO> findTo5Profiles() {
		return twitterProfileRepository.findTop5ByOrderByProfileFollowersCountDesc()
				.stream()
				.map(tweet -> modelMapper.map(tweet, TopUserFollowerCountDTO.class))
				.collect(Collectors.toList());
	}
}
