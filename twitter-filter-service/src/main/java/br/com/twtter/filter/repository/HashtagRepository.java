package br.com.twtter.filter.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.twtter.filter.entity.Hashtag;

/**
 * 
 * @author Fernando
 *
 */

@Repository
public interface HashtagRepository extends GenericRepository<Hashtag> {

	Hashtag findByHashTag(String hashTag);

	default Hashtag consistenceSave(Hashtag hashtag) {
		Optional<Hashtag> optHashTag = Optional.ofNullable(findByHashTag(hashtag.getHashTag()));
		if (!optHashTag.isPresent()) {
			return this.save(hashtag);
		}
		return optHashTag.get();
	}

}
