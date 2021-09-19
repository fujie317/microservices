package com.me.microservices.core.recommendation.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import com.me.microservices.api.core.recommendation.Recommendation;
import com.me.microservices.api.core.recommendation.RecommendationService;
import com.me.microservices.api.exception.InvalidInputException;
import com.me.microservices.util.http.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@RestController
@Slf4j
@ComponentScan(basePackages="com.me") //required to pick up components defined in util and api packages
public class RecommendationServiceImpl implements RecommendationService {

	private final ServiceUtil serviceUtil;

	/**
	 * @param serviceUtil
	 */
	@Autowired
	public RecommendationServiceImpl(ServiceUtil serviceUtil) {
		this.serviceUtil = serviceUtil;
	}

	@Override
	public List<Recommendation> getRecommendations(int productId) {

		if (productId < 1) {
			throw new InvalidInputException("Invalid productId: " + productId);
		}

		if (productId == 113) {
			log.debug("No recommendations found for productId: {}", productId);
			return new ArrayList<>();
		}

		List<Recommendation> list = new ArrayList<>();
		list.add(new Recommendation(productId, 1, "Author 1", 1, "Content 1", serviceUtil.getServiceAddress()));
		list.add(new Recommendation(productId, 2, "Author 2", 2, "Content 2", serviceUtil.getServiceAddress()));
		list.add(new Recommendation(productId, 3, "Author 3", 3, "Content 3", serviceUtil.getServiceAddress()));

		log.debug("/recommendation response size: {}", list.size());

		return list;
	}
}
