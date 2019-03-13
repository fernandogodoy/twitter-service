package br.com.twtter.filter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.twtter.filter.dto.HashtagResponseDTO;
import br.com.twtter.filter.entity.Hashtag;
import br.com.twtter.filter.repository.HashtagRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Fernando
 *
 */

@Service
@Slf4j
public class HashtagService {

	@Autowired
	private HashtagRepository repository;

	public List<HashtagResponseDTO> findAll() {
		return repository.findAll().stream()
				.map(hashtag -> new HashtagResponseDTO(hashtag.getId(), hashtag.getHashTag()))
				.collect(Collectors.toList());
	}

	public HashtagResponseDTO save(String hashtag) {
		Hashtag saved = repository.consistenceSave(new Hashtag(hashtag));
		return new HashtagResponseDTO(saved.getId(), saved.getHashTag());
	}

	public void deleteAll() {
		log.info("Quantidade de hashtags sendo removidas" + repository.count());
		repository.deleteAll();
	}

}
