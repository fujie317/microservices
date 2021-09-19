package com.me.microservices.api.core.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author Fujie Zhang
 *
 * @since Aug 12, 2021
 */
@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
public class Product {
	private final int productId;
	private final String name;
	private final int weight;
	private final String serviceAddress;
}
