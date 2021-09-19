package com.me.microservices.api.composite.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class ServiceAddresses {
	private final String cmp;
	private final String pro;
	private final String rev;
	private final String rec;
}
