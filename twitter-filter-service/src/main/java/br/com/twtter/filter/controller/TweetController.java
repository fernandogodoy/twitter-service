package br.com.twtter.filter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.twtter.filter.dto.HashtagLanguageDTO;
import br.com.twtter.filter.dto.TweetDTO;
import br.com.twtter.filter.dto.TweetTimeDTO;
import br.com.twtter.filter.service.TweetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Fernando
 *
 */
@Slf4j
@Api
@RestController
@RequestMapping("/tweet")	
public class TweetController {

	@Autowired
	private TweetService service;

	@ApiOperation( value = "List all tweets")
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TweetDTO>> findAll() {
		log.info("Listando tweets");
		return ResponseEntity.ok(service.findAll());
	}
	
	@ApiOperation( value = "List tweets grouped by time")
	@GetMapping(path = "/list/grouped-time", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TweetTimeDTO>> findGroupedBuTime() {
		log.info("Listando tweets agrupados por time");
		return ResponseEntity.ok(service.groupedByTime());
	}

	@ApiOperation( value = "List tweets grouped by language and hashtag")
	@GetMapping(path = "/list/grouped-language", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HashtagLanguageDTO>> findGroupedByLanguage() {
		log.info("Listando tweets agrupados por idioma");
		return ResponseEntity.ok(service.groupedByLanguage());
	}

	
}
