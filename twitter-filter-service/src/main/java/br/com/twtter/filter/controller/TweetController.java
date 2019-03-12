package br.com.twtter.filter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.twtter.filter.dto.TweetDTO;
import br.com.twtter.filter.service.TweetService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Fernando
 *
 */
@Slf4j
@RestController
@RequestMapping("/tweet")
public class TweetController {

	@Autowired
	private TweetService service;

	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TweetDTO>> findAll() {
		log.info("Listando tweets");
		return ResponseEntity.ok(service.findAll());
	}

}
