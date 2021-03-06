package br.com.twtter.filter.dto;

import java.time.LocalTime;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TweetTimeDTO {

	private LocalTime time;

	private Long count;

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("time", time);
		builder.append("count", count);
		return builder.toString();
	}

	
}
