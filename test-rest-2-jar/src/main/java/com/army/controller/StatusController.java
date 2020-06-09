package com.army.controller;

import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.army.model.EmployeeType;
import com.army.model.Status;
import com.army.repo.EmployeeTypeRepo;
import com.army.repo.StatusRepo;
import com.army.service.PropertyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/status")
public class StatusController {
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private ObjectMapper jacksonObjectMapper;
	
	@RequestMapping(value = "/getall/", method = RequestMethod.GET)
	public String getAllStatus() {
		StringBuilder jsonStringB = new StringBuilder();
		try {
			Iterable<Status> statusList = statusRepo.findAll();
			
 			jsonStringB.append(jacksonObjectMapper.writeValueAsString(statusList));
			
		} catch (JsonProcessingException e) {
			jsonStringB.append(e);
			e.printStackTrace();
		}

		String jsonString = jsonStringB.toString();
//		String jsonString = mapper.writeValueAsString(value);
		return jsonString;
	}
	
	
	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
	public String getStatusById(@PathVariable("id") String id) {
		Optional<Status> statusTypeO = statusRepo.findById(id);
		
		StringBuilder jsonStringB = new StringBuilder();
		if (statusTypeO.isPresent()) {
			Status statusType = statusTypeO.get();
			try {
				jsonStringB.append(jacksonObjectMapper.writeValueAsString(statusType));
			} catch (JsonProcessingException e) {
				jsonStringB.append(e);
				e.printStackTrace();
			}
		} else {
			jsonStringB.append("Can't find employee type with this parameter");
		}
		String jsonString = jsonStringB.toString();
		return jsonString;
	}
}
