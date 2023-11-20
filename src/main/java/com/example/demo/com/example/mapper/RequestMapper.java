package com.example.demo.com.example.mapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("createRequestMapper")
public class RequestMapper<K,V> implements Mapper<K,V>  {

	@Override
	public V convert(K obj) {
		
		
		System.out.print("Request Mapper");
		
		String  i= (String) obj;
		
		return (V) i ;
	}

}
