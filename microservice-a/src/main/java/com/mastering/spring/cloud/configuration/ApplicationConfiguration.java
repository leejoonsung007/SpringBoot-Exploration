package com.mastering.spring.cloud.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application") // Defines a class defining bootstrap.properties
public class ApplicationConfiguration {
	private String message; // The value can be configured in bootstrap.properties with application.message as key

	
	public String getmessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
