package br.com.twtter.filter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.twtter.filter.dto.HashtagRequestTDTO;
import br.com.twtter.filter.dto.HashtagResponseDTO;
import br.com.twtter.filter.service.HashtagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Fernando
 *
 */

@Api
@Slf4j
@RestController
@RequestMapping("/hashtag")
public class HashtagController {

	@Autowired
	private HashtagService service;

	@ApiOperation( value = "List all hashtags")
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<HashtagResponseDTO>> findHashtags() {
		log.info("Listando hashtags");
		List<HashtagResponseDTO> hashtags = service.findAll();
		log.info("Hashtags encontradas: " + hashtags.size());
		return ResponseEntity.ok(service.findAll());
	}
	
	@ApiOperation( value = "Save a new hashtag")
	@PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashtagResponseDTO> save(@RequestBody HashtagRequestTDTO dto) {
		log.info("Incluindo hashtag");
		return ResponseEntity.ok(service.save(dto.getHashtag()));
	}
	
	@ApiOperation( value = "Delete all hashtags")
	@DeleteMapping(path = "/delete")
	public ResponseEntity<Void> cleanDataBase() {
		log.info("Removendo hastags");
		service.deleteAll();
		return ResponseEntity.ok().build();
	}

}
