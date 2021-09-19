package com.me.microservices.core.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Fujie Zhang
 *
 * @since Aug 12, 2021
 */
@SpringBootApplication
public class ReviewServiceApplication {

	/**
	 * Entry point for review services
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ReviewServiceApplication.class, args);

	}

}
