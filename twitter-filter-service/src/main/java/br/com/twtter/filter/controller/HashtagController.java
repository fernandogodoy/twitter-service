package br.com.twtter.filter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.twtter.filter.dto.HashtagDTO;
import br.com.twtter.filter.service.HashtagService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Fernando
 *
 */

@Slf4j
@RestController
@RequestMapping("/hashtag")
public class HashtagController {

	@Autowired
	private HashtagService service;

	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<HashtagDTO>> findHashtags() {
		log.info("Listando hashtags");
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashtagDTO> save(@RequestBody String hashtag) {
		log.info("Incluindo hashtag");
		return ResponseEntity.ok(service.save(hashtag));
	}

}
