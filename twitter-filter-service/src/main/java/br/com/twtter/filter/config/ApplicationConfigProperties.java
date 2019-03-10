package br.com.twtter.filter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

/**
 * Configuration for Twitter properties
 * 
 * @author Fernando
 *
 */
@Getter
@Configuration
public class ApplicationConfigProperties {

	@Value("${twitter.consumerKey}")
	public String consumerKey;

	@Value("${twitter.consumerSecret}")
	public String consumerSecret;

}
