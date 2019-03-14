package br.com.twtter.filter.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TweetTimeDTO {

	private LocalTime time;

	private Long count;

}
