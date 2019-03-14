package br.com.twtter.filter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TwitterProfile implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String profileName;

	private Integer profileFollowersCount;

	private String profileUserLocation;

	private String profileLanguage;

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
		builder.append("id", id);
		builder.append("profileName", profileName);
		builder.append("profileFollowersCount", profileFollowersCount);
		builder.append("profileUserLocation", profileUserLocation);
		builder.append("profileLanguage", profileLanguage);
		return builder.toString();
	}

	public void updateFollowerCount(int followersCount) {
		this.profileFollowersCount = followersCount;
	}

}
