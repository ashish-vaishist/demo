package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.com.example.task.Ticker;

@Service
public class FailSafeService {
	
	
	
	@Autowired
	Ticker ticker;
	
	
	public static  int timesliceFailureCount=0;
	
	
	static  int success=5;
	static int failure=0;
	
	
	String serviceStatus="UP";
	
	
	public String getStatus() {
		
		
		if(ticker.getStatus().equalsIgnoreCase("UP")) {
				
			success++;
			failure=0;
				
			
			
			if(success>=5 ) {
				
				success =5;
				serviceStatus="UP";
				
				
			}
			
		}
		else {
			
			success=0;
			failure++;
					
			timesliceFailureCount++;
			
			
			if(failure>=5) {
				failure=5;
				
				serviceStatus="DOWN";
			}
			
			
			
			
			
		}
		
		
		System.out.println("Success: "+ success +" Failure: " + failure +" Service Status:  "+serviceStatus);
		return serviceStatus;
		
		
		
	}
	
	
	

}
