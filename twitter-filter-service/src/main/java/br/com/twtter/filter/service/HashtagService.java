package br.com.twtter.filter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.twtter.filter.dto.HashtagDTO;
import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.repository.HashtagRepository;

/**
 * 
 * @author Fernando
 *
 */

@Service
public class HashtagService {

	@Autowired
	private HashtagRepository repository;

	public List<HashtagDTO> findAll() {
		return repository.findAll().stream()
				.map(hashtag -> new HashtagDTO(hashtag.getId(), hashtag.getHashTag()))
				.collect(Collectors.toList());
	}

	public HashtagDTO save(String hashtag) {
		Hashtag tag = Hashtag.builder()
				.hashTag(hashtag.replace("#", StringUtils.EMPTY))
				.build();
		Hashtag saved = repository.consistenceSave(tag);
		return new HashtagDTO(saved.getId(), saved.getHashTag());
	}

}
