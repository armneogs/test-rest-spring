package com.army.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ConverterService {
	public <Clazz> List<Clazz> convertIterableToList(Iterable<Clazz> Iterable){
		List<Clazz> list = new ArrayList<Clazz>();
		if(Iterable == null) return list;
		
		for(Clazz obj : Iterable) {
			list.add(obj);
		}
		
		return list;

	}
}
