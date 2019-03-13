package br.com.twtter.filter.entity;

import java.util.ArrayList;
import java.util.Arrays;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Fernando
 *
 */

@Getter
@Entity
@AllArgsConstructor
@EqualsAndHashCode(exclude = "tweets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String hashTag;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Tweet.class, mappedBy = "hashtag")
	private List<Tweet> tweets = new ArrayList<>();

	public Hashtag(String hashtag) {
		this.hashTag = hashtag;
	}

	public Hashtag(String hashtag, List<Tweet> tweets) {
		this.hashTag = hashtag;
		this.tweets = tweets;
	}

	public Hashtag(String hashtag, Tweet tweet) {
		this.hashTag = hashtag;
		this.tweets = Arrays.asList(tweet);
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
