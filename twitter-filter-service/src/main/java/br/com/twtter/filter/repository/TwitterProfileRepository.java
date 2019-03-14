package br.com.twtter.filter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.twtter.filter.entity.TwitterProfile;

@Repository
public interface TwitterProfileRepository extends GenericRepository<TwitterProfile> {

	Optional<TwitterProfile> findByProfileName(String profileName);
	
	
	List<TwitterProfile> findTop5ByOrderByProfileFollowersCountDesc();
}
