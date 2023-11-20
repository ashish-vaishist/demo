package com.example.demo.com.example.enity;



import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.example.library.annotation.MyFieldLevelAnnotation;

import jakarta.validation.constraints.NotBlank;



public class Subject1 {
	

	@NotBlank (message="id can not be empty")
	private String id;
	
	@MyFieldLevelAnnotation
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
