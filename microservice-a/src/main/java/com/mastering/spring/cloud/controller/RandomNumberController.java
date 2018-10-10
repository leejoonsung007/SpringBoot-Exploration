package com.mastering.spring.cloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomNumberController {
	private Log log = LogFactory.getLog(RandomNumberController.class);
	@RequestMapping("/random") // return a list of random numbers
	public List<Integer> random(){
		List<Integer> numbers = new ArrayList<>();
		for(int i = 1; i <= 5; i++) {
			numbers.add(generateRandomNumber());
		}
		log.warn("Returning" + numbers);
		return numbers;
	}
	// generates random number between 0 and 1000
	private Integer generateRandomNumber() {
		return (int) (Math.random() * 1000);
	}
}
