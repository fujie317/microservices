/**
 * 
 */
package com.me.microservices.api.composite.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@Tag(name = "ProductComposite", description = "REST API for composite product information.")
public interface ProductCompositeService {
	/**
	 * Sample Usage: curl $HOST:$PORT/product-composite/1
	 * 
	 * @param productId product id
	 * @return product if found, else null
	 */
	@Operation(summary = "${api.product-composite.get-composite-product.description}", description = "${api.product-composite.get-composite-product.notes}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
			@ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
			@ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}") })
	@GetMapping(value = "/product-composite/{productId}", produces = "application/json")
	ProductAggregate getProduct(@PathVariable int productId);
}
