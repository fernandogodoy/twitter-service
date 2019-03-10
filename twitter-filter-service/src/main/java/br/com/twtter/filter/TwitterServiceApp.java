package br.com.twtter.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.social.config.annotation.EnableSocial;

@EnableSocial
@ComponentScan
@SpringBootApplication
public class TwitterServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(TwitterServiceApp.class, args);
	}
}
