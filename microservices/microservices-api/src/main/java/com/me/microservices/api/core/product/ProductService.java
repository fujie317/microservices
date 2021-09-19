/**
 * 
 */
package com.me.microservices.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface specifying service api for Product
 * 
 * @author Fujie Zhang
 *
 * @since Aug 12, 2021
 */
public interface ProductService {
	/**
	 * Sample Usage: curl $HOST:$PORT/product/1
	 * 
	 * @param productId Id of product
	 * @return the product to be returned
	 */
	@GetMapping(value = "/product/{productId}", produces = "application/json")
	Product getProduct(@PathVariable int productId);
}
