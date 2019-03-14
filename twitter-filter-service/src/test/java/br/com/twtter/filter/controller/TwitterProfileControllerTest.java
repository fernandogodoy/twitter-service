package br.com.twtter.filter.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.github.javafaker.Faker;

import br.com.twtter.filter.dto.TopUserFollowerCountDTO;
import br.com.twtter.filter.service.TwitterProfileService;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TwitterProfileController.class)
class TwitterProfileControllerTest {

	@MockBean
	private TwitterProfileService service;
	
	@Autowired
	private MockMvc mvc;

	private Faker faker;

	@BeforeEach
	public void init() {
		faker = new Faker();
	}

	@Test
	void shouldReturnTop5UserFollowerCount() throws Exception {
		TopUserFollowerCountDTO top1 = new TopUserFollowerCountDTO(100, faker.gameOfThrones().character());
		TopUserFollowerCountDTO top2 = new TopUserFollowerCountDTO(50, faker.gameOfThrones().character());
		given(service.findTo5Profiles()).willReturn(Arrays.asList(top1, top2));

		mvc.perform(get("/twitter-profile/user-followers-count/top-5")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("[0].profileFollowersCount", equalTo(100)))
				.andExpect(jsonPath("[0].profileName", notNullValue()));
	}

}
