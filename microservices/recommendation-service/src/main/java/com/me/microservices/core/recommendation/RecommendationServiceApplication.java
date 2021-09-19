package com.me.microservices.core.recommendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Fujie Zhang
 *
 * @since Aug 12, 2021
 */
@SpringBootApplication
public class RecommendationServiceApplication {

	/**
	 * Entry point for recommendation services
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SpringApplication.run(RecommendationServiceApplication.class, args);

	}

}
