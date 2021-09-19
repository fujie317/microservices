/**
 * 
 */
package com.me.microservices.api.core.review;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
public interface ReviewService {
	/**
	 * Sample Usage: curl $HOST:$PORT/review?productId=1
	 * 
	 * @param productId if of product to review
	 * @return review of a particular product
	 */
	@GetMapping(value = "/review", produces = "application/json")
	List<Review> getReviews(@RequestParam(value = "productId", required = true) int productId);
}
