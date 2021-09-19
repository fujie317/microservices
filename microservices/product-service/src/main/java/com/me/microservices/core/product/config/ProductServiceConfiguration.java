package com.me.microservices.core.product.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * This is project configuration data. Properties defined in project application.yml 
 * file can be picked up here. This file has no bearing on the project. It's here
 * to demonstrate the use of configuration data defined in application.yml file. 
 * Without it, non-standard entries would have a warning sign.
 * 
 * @author Fujie Zhang
 *
 */
@ConfigurationProperties(prefix="data")
@Configuration
@Data
public class ProductServiceConfiguration {
	
	private String name;

}
