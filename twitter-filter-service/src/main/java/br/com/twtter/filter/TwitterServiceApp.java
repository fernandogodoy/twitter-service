package br.com.twtter.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.social.config.annotation.EnableSocial;

@EnableSocial
@ComponentScan
@EnableScheduling
@EnableJpaRepositories
@SpringBootApplication
@EntityScan(basePackages = "br.com.twtter.filter.entity")
public class TwitterServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(TwitterServiceApp.class, args);
	}
}
