package br.com.twtter.filter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.twtter.filter.dto.TopUserFollowerCountDTO;
import br.com.twtter.filter.service.TwitterProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api
@RestController
@RequestMapping("/twitter-profile")
public class TwitterProfileController {
	
	@Autowired
	private TwitterProfileService service;

	@ApiOperation( value = "List Top 5 users with mode followers")
	@GetMapping(path = "/user-followers-count/top-5", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TopUserFollowerCountDTO>> findTop5UserFollower() {
		log.info("Listando 5 usuarios com mais seguidores");
		return ResponseEntity.ok(service.findTo5Profiles());
	}
}
