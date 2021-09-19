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
public class RecommendationSummary {
	private final int recommendationId;
	private final String author;
	private final int rate;
}
