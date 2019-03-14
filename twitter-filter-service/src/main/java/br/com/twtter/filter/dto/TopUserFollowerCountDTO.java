package br.com.twtter.filter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Fernando
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopUserFollowerCountDTO {

	private Integer profileFollowersCount;

	private String profileName;

}
