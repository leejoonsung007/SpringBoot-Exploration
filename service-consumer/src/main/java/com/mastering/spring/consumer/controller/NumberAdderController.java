package com.mastering.spring.consumer.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NumberAdderController {
	private Log log = LogFactory.getLog(NumberAdderController.class);
	
	@Value("${number.service.url}") // configure in application properties
	private String numberServiceUrl;
	@RequestMapping("/add")
	public Long add() {
		long sum = 0;
		ResponseEntity<Integer[]> responseEntity = new RestTemplate()
				.getForEntity(numberServiceUrl, Integer[].class);
		Integer[] numbers = responseEntity.getBody();
		
		for(int number: numbers) {
			sum += number;
		}
		
		log.warn("Returning " + sum);
		return sum;
	}
}
