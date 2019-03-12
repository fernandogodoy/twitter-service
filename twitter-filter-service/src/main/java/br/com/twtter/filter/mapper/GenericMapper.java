package br.com.twtter.filter.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Fernando
 *
 * @param <TO>
 * @param <FROM>
 */
public interface GenericMapper<TO, FROM> {

	TO toEntity(FROM from);

	FROM toObject(TO to);

	default List<TO> toEntityList(List<FROM> all) {
		return all.stream()
				.map(this::toEntity)
				.collect(Collectors.toList());
	}

}
