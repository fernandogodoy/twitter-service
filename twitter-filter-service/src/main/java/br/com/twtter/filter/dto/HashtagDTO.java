package br.com.twtter.filter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Fernando
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HashtagDTO {

	private Long id;
	
	public String value;

}
