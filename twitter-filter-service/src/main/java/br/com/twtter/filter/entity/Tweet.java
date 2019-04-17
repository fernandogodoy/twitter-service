package br.com.twtter.filter.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Fernando
 *
 */
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = { "hashtag", "id" })
public class Tweet implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;

	private LocalDateTime createdAt;

	@ManyToOne
	private TwitterProfile profile;

	@ManyToOne
	private Hashtag hashtag;

	public String getLanguage() {
		return profile.getProfileLanguage();
	}
	
	@Getter
	@EqualsAndHashCode
	@AllArgsConstructor
	@NoArgsConstructor
	public static class GroupedByHashTag{
		
		private String profileLanguage;
		
		private String hashTag;
		
		@Override
		public String toString() {
			ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
			builder.append("profileLanguage", profileLanguage);
			builder.append("hashTag", hashTag);
			return builder.toString();
		}
		
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
		builder.append("id", id);
		builder.append("text", text);
		builder.append("createdAt", createdAt);
		builder.append("hashtag", hashtag);
		return builder.toString();
	}

}
