package com.me.microservices.composite;

import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.me.microservices.api.core.Product;
import com.me.microservices.api.core.Recommendation;
import com.me.microservices.api.core.Review;
import com.me.microservices.api.exception.InvalidInputException;
import com.me.microservices.api.exception.NotFoundException;
import com.me.microservices.composite.product.ProductCompositeIntegration;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CompositeServiceApplicationTest {

	private static final int PRODUCT_ID_OK = 1;
	private static final int PRODUCT_ID_NOT_FOUND = 2;
	private static final int PRODUCT_ID_INVALID = 3;

	@Autowired
	private WebTestClient client;

	@MockBean
	private ProductCompositeIntegration compositeIntegration;

	@BeforeEach
	void setUp() {

		when(compositeIntegration.getProduct(PRODUCT_ID_OK))
				.thenReturn(new Product(PRODUCT_ID_OK, "name", 1, "mock-address"));
		when(compositeIntegration.getRecommendations(PRODUCT_ID_OK)).thenReturn(Collections
				.singletonList(new Recommendation(PRODUCT_ID_OK, 1, "author", 1, "content", "mock address")));
		when(compositeIntegration.getReviews(PRODUCT_ID_OK)).thenReturn(Collections
				.singletonList(new Review(PRODUCT_ID_OK, 1, "author", "subject", "content", "mock address")));

		when(compositeIntegration.getProduct(PRODUCT_ID_NOT_FOUND))
				.thenThrow(new NotFoundException("NOT FOUND: " + PRODUCT_ID_NOT_FOUND));

		when(compositeIntegration.getProduct(PRODUCT_ID_INVALID))
				.thenThrow(new InvalidInputException("INVALID: " + PRODUCT_ID_INVALID));
	}

	@Test
	void contextLoads() {
	}

	@Test
	void getProductById() {

		client.get().uri("/product-composite/" + PRODUCT_ID_OK).accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$.productId").isEqualTo(PRODUCT_ID_OK).jsonPath("$.recommendations.length()").isEqualTo(1)
				.jsonPath("$.reviews.length()").isEqualTo(1);
	}

	@Test
	void getProductNotFound() {

		client.get().uri("/product-composite/" + PRODUCT_ID_NOT_FOUND).accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isNotFound().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$.path").isEqualTo("/product-composite/" + PRODUCT_ID_NOT_FOUND).jsonPath("$.message")
				.isEqualTo("NOT FOUND: " + PRODUCT_ID_NOT_FOUND);
	}

	@Test
	void getProductInvalidInput() {

		client.get().uri("/product-composite/" + PRODUCT_ID_INVALID).accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY).expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.path")
				.isEqualTo("/product-composite/" + PRODUCT_ID_INVALID).jsonPath("$.message")
				.isEqualTo("INVALID: " + PRODUCT_ID_INVALID);
	}

}
