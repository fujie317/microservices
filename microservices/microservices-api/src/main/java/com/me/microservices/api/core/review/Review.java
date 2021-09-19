package com.me.microservices.api.core.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
public class Review {
	private final int productId;
	private final int reviewId;
	private final String author;
	private final String subject;
	private final String content;
	private final String serviceAddress;
}
