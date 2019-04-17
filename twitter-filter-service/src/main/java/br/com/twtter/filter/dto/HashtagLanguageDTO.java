package br.com.twtter.filter.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HashtagLanguageDTO {

	private String hashtag;

	private String language;

	private Long count;

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
		builder.append("hashtag", hashtag);
		builder.append("language", language);
		builder.append("count", count);
		return builder.toString();
	}

}
