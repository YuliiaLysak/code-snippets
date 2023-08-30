package edu.lysak.uppercase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class Config {

	@Bean
	public Function<String, String> echo() {
		return payload -> payload;
	}

	@Bean
	public Function<String, String> uppercase() {
		return payload -> payload.toUpperCase();
	}
}

