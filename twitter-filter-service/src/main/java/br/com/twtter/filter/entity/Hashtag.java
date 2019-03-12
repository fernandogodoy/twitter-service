package br.com.twtter.filter.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 * 
 * @author Fernando
 *
 */

@Getter
@Entity
@Builder
@AllArgsConstructor
@EqualsAndHashCode(exclude = "tweets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String hashTag;

	@Singular
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Tweet.class, mappedBy = "hashtag")
	private List<Tweet> tweets;

	public String getFormatedHashTag() {
		return String.format("#%s", hashTag.toLowerCase());
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
		builder.append("id", id);
		builder.append("hashTag", hashTag);
		builder.append("tweets", tweets);
		return builder.toString();
	}

}
