package com.example.demo.com.example.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.service.FailSafeService;

@Configuration
@EnableScheduling
public class DynamicSchedulingConfig {
	
	
	
	@Autowired
	Ticker ticker;
	
	
	
	int x=0;
	
	@Scheduled(fixedDelay = 3000)
	public void scheduleFixedDelayTask() {
		ticker.setStatus();
	  //  System.out.println("Fixed delay task - " + ticker.getStatus() +"  ");
	
	
		
	}

}	




