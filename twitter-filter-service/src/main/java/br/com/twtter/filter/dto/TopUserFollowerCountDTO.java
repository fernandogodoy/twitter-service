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
public class TopUserFollowerCountDTO {

	private Integer profileFollowersCount;

	private String profileName;

}
