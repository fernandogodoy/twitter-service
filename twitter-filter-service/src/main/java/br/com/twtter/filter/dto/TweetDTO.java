package br.com.twtter.filter.dto;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetDTO {

	private Long id;

	private String text;

	private LocalDateTime createdAt;

	private String profileProfileName;

	private Integer profileProfileFollowersCount;

	private String profileProfileUserLocation;

	private String profileProfileLanguage;

	private String hashtagHashtag;

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
		builder.append("id", id);
		builder.append("text", text);
		builder.append("createdAt", createdAt);
		builder.append("profileProfileName", profileProfileName);
		builder.append("profileProfileFollowersCount", profileProfileFollowersCount);
		builder.append("profileProfileUserLocation", profileProfileUserLocation);
		builder.append("profileProfileLanguage", profileProfileLanguage);
		builder.append("hashtagHashtag", hashtagHashtag);
		return builder.toString();
	}
	
	

}
