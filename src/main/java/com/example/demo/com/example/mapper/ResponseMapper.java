package com.example.demo.com.example.mapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("createResponseMapper")
public class ResponseMapper<K,V> implements Mapper<K,V>  {

	@Override
	public V convert(K obj) {
		
		
		Integer i= Integer.parseInt((String) obj);
		System.out.print("Response Mapper");
		return (V) i ;
	}

}
