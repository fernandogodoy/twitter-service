package br.com.twtter.filter.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.twtter.filter.dto.HashtagResponseDTO;
import br.com.twtter.filter.service.HashtagService;

@AutoConfigureMockMvc
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = HashtagController.class)
class HashtagControllerTest {

	@MockBean
	private HashtagService service;

	@Autowired
	private MockMvc mvc;

	@Test
	void shouldReturnHashtags() throws Exception {
		given(service.findAll()).willReturn(Arrays.asList(new HashtagResponseDTO(1L, "openbanking"), new HashtagResponseDTO(2L, "openAPI")));

		mvc.perform(get("/hashtag/list")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("[0].value", equalTo("openbanking")))
				.andExpect(jsonPath("[1].value", equalTo("openAPI")));
	}

	
	@Test
	void shouldSaveNewHashTag() throws Exception {
		given(service.save(ArgumentMatchers.anyString())).willReturn(new HashtagResponseDTO(1L, "openbanking"));

		mvc.perform(post("/hashtag/save")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{\"hashtag\": \"openBanking\"}"))
				.andDo(print())
				.andExpect(jsonPath("value", equalTo("openbanking")))
				.andExpect(jsonPath("id", equalTo(1)));
	}
}
