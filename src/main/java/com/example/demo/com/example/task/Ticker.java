package com.example.demo.com.example.task;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;

@Service
public class Ticker{
	
	
	 String status="UP";
	
	
	
	
	
	
	Supplier<Integer> getDelay= ()->{
		
		return  (int) ((Math.random() * (9 - 1)) + 1) ;
		
	};
	
	
	
	
	
	public void setStatus() {
		
		
		int x= getDelay.get();
		
		if(x%2==0) {
			if(status.equals("UP")) {
				this.status="DOWN";
			}
			
			else if(status.equals("DOWN")) {
				this.status="UP";
			}
		}
		
	}
	
	
	
	public String getStatus() {

		return this.status;
	}
	
	
}
