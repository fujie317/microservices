package com.me.microservices.util.http;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@Component
@Slf4j
public class ServiceUtil {

	private final String port;

	private String serviceAddress = null;

	/**
	 * @param port port number of server specified in an application.properties file
	 */
	@Autowired
	public ServiceUtil(@Value("${server.port}") String port) {

		this.port = port;
	}

	/**
	 * @return IPAddress of service host
	 */
	public String getServiceAddress() {
		if (serviceAddress == null) {
			serviceAddress = findMyHostname() + "/" + findMyIpAddress() + ":" + port;
		}
		return serviceAddress;
	}

	private String findMyHostname() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "unknown host name";
		}
	}

	private String findMyIpAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "unknown IP address";
		}
	}
}
