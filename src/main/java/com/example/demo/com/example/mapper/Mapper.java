package com.example.demo.com.example.mapper;

@FunctionalInterface
public interface Mapper<K,V> {
	
	
	
	public V convert(K obj);

}
