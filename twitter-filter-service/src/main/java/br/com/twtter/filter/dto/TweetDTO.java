package br.com.twtter.filter.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetDTO {

	private Long id;

	private String text;

	private LocalDateTime createdAt;

	private String profileName;

	private Integer profileFollowersCount;

	private String profileUserLocation;

	private String profileLanguage;

	private String hashtagHashtag;

}
