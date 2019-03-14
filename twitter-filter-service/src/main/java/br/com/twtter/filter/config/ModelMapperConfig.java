package br.com.twtter.filter.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 
 * @author Fernando
 *
 */

@Configuration
public class ModelMapperConfig {

	@Bean
	@Primary
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
