package com.me.microservices.api.core.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor(force = true)
public class Recommendation {
	private final int productId;
	private final int recommendationId;
	private final String author;
	private final int rate;
	private final String content;
	private final String serviceAddress;
}
