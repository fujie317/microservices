package com.me.microservices.api.composite.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@Getter
@RequiredArgsConstructor
public class ReviewSummary {
	private final int reviewId;
	private final String author;
	private final String subject;
}
