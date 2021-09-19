package com.me.microservices.core.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import com.me.microservices.api.core.product.Product;
import com.me.microservices.api.core.product.ProductService;
import com.me.microservices.api.exception.InvalidInputException;
import com.me.microservices.api.exception.NotFoundException;
import com.me.microservices.core.product.config.ProductServiceConfiguration;
import com.me.microservices.util.http.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * This is really the controller. It's unfortunate to name it service
 * The interface is defined in api package
 * 
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */

@RestController
@Slf4j
@ComponentScan(basePackages="com.me") //required to pick up components defined in util and api packages
public class ProductServiceImpl implements ProductService {

	private ServiceUtil serviceUtil;
	
	private ProductServiceConfiguration productServiceConfiguration;

	/**
	 * @param serviceUtil bean to be auto injected
	 */
	@Autowired
	public ProductServiceImpl(ServiceUtil serviceUtil) {
		this.serviceUtil = serviceUtil;
	}
	
	@Autowired
	public void setProductServiceConfiguration(ProductServiceConfiguration productServiceConfiguration) {
		this.productServiceConfiguration = productServiceConfiguration;
	}

	@Override
	public Product getProduct(int productId) {
		log.debug("/product return the found product for productId={}", productId);
		log.info("user name: {}", productServiceConfiguration.getName());

		if (productId < 1) {
			throw new InvalidInputException("Invalid productId: " + productId);
		}

		if (productId == 13) {
			throw new NotFoundException("No product found for productId: " + productId);
		}

		return new Product(productId, "name-" + productId, 123, serviceUtil.getServiceAddress());
	}
}
