package com.me.microservices.composite.product.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.microservices.api.core.product.Product;
import com.me.microservices.api.core.product.ProductService;
import com.me.microservices.api.core.recommendation.Recommendation;
import com.me.microservices.api.core.recommendation.RecommendationService;
import com.me.microservices.api.core.review.Review;
import com.me.microservices.api.core.review.ReviewService;
import com.me.microservices.api.exception.InvalidInputException;
import com.me.microservices.api.exception.NotFoundException;
import com.me.microservices.util.http.HttpErrorInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@Component
@Slf4j
public class ProductCompositeIntegration implements ProductService, ReviewService, RecommendationService {

	private final RestTemplate restTemplate;
	private final ObjectMapper mapper;

	private final String productServiceUrl;
	private final String recommendationServiceUrl;
	private final String reviewServiceUrl;

	/**
	 * @param restTemplate              rest template to make rest calls
	 * @param mapper                    json object mapper
	 * @param productServiceHost        product service host address
	 * @param productServicePort        product service port number
	 * @param recommendationServiceHost recommendation service host address
	 * @param recommendationServicePort recommendation service port number
	 * @param reviewServiceHost         review service host address
	 * @param reviewServicePort         review service port number
	 */
	@Autowired
	public ProductCompositeIntegration(RestTemplate restTemplate, ObjectMapper mapper,
			@Value("${app.product-service.host}") String productServiceHost,
			@Value("${app.product-service.port}") int productServicePort,
			@Value("${app.recommendation-service.host}") String recommendationServiceHost,
			@Value("${app.recommendation-service.port}") int recommendationServicePort,
			@Value("${app.review-service.host}") String reviewServiceHost,
			@Value("${app.review-service.port}") int reviewServicePort) {

		this.restTemplate = restTemplate;
		this.mapper = mapper;

		productServiceUrl = "http://" + productServiceHost + ":" + productServicePort + "/product/";
		recommendationServiceUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort
				+ "/recommendation?productId=";
		reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort + "/review?productId=";
	}

	private String getErrorMessage(HttpClientErrorException ex) {
		try {
			return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
		} catch (IOException ioex) {
			return ex.getMessage();
		}
	}

	@Override
	public List<Recommendation> getRecommendations(int productId) {

		try {
			String url = recommendationServiceUrl + productId;

			log.debug("Will call getRecommendations API on URL: {}", url);
			List<Recommendation> recommendations = restTemplate
					.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recommendation>>() {
					}).getBody();

			log.debug("Found {} recommendations for a product with id: {}", recommendations.size(), productId);
			return recommendations;

		} catch (Exception ex) {
			log.warn("Got an exception while requesting recommendations, return zero recommendations: {}",
					ex.getMessage());
			return new ArrayList<>();
		}
	}

	@Override
	public List<Review> getReviews(int productId) {

		try {
			String url = reviewServiceUrl + productId;

			log.debug("Will call getReviews API on URL: {}", url);
			List<Review> reviews = restTemplate
					.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
					}).getBody();

			log.debug("Found {} reviews for a product with id: {}", reviews.size(), productId);
			return reviews;

		} catch (Exception ex) {
			log.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
			return new ArrayList<>();
		}
	}

	@Override
	public Product getProduct(int productId) {

		try {
			String url = productServiceUrl + productId;
			log.debug("Will call getProduct API on URL: {}", url);

			Product product = restTemplate.getForObject(url, Product.class);
			log.debug("Found a product with id: {}", product.getProductId());

			return product;

		} catch (HttpClientErrorException ex) {

			switch (ex.getStatusCode()) {
			case NOT_FOUND:
				throw new NotFoundException(getErrorMessage(ex));

			case UNPROCESSABLE_ENTITY:
				throw new InvalidInputException(getErrorMessage(ex));

			default:
				log.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
				log.warn("Error body: {}", ex.getResponseBodyAsString());
				throw ex;
			}
		}
	}
}
