package com.army.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.army.model.EmployeeType;
import com.army.repo.EmployeeTypeRepo;
import com.army.service.PropertyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/employeetype")
public class EmployeeTypeController {
	@Autowired
	private EmployeeTypeRepo employeeTypeRepo;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private ObjectMapper jacksonObjectMapper;

	// @CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getall/", method = RequestMethod.GET)
	public String getTypeAll() {
		StringBuilder jsonStringB = new StringBuilder();
		try {
			jsonStringB.append(jacksonObjectMapper.writeValueAsString(employeeTypeRepo.findAll()));
		} catch (JsonProcessingException e) {
			jsonStringB.append(e);
			e.printStackTrace();
		}

		String jsonString = jsonStringB.toString();
//		String jsonString = mapper.writeValueAsString(value);
		return jsonString;
	}

	// @CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getbyname/{name}", method = RequestMethod.GET)
	public String getTypeByName(@PathVariable("name") String name) {
		StringBuilder jsonStringB = new StringBuilder();
		List<EmployeeType> employeeTypeList = employeeTypeRepo.findByTypeName(name);
		if (employeeTypeList != null && !employeeTypeList.isEmpty()) {
			try {
				jsonStringB.append(jacksonObjectMapper.writeValueAsString(employeeTypeList));
			} catch (JsonProcessingException e) {
				jsonStringB.append(e);
				e.printStackTrace();
			}
		} else {
			jsonStringB.append("Can't find employee type with this parameter");
		}
		String jsonString = jsonStringB.toString();
//		String jsonString = mapper.writeValueAsString(value);
		return jsonString;
	}

	// @CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
	public String getTypeById(@PathVariable("id") Long id) {
		Optional<EmployeeType> employeeTypeO = employeeTypeRepo.findById(id);
		StringBuilder jsonStringB = new StringBuilder();
		if (employeeTypeO.isPresent()) {
			EmployeeType employeeType = employeeTypeO.get();
			try {
				jsonStringB.append(jacksonObjectMapper.writeValueAsString(employeeType));
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

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveType(@RequestBody EmployeeType type) {
		StringBuilder jsonStringB = new StringBuilder();
		try {
			employeeTypeRepo.save(type);
			jsonStringB.append(jacksonObjectMapper.writeValueAsString(type));
		} catch (Exception e) {
			return jsonStringB.append("not suc" + e.getMessage()).toString();
		}
		return jsonStringB.toString();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public String updateType(@RequestBody EmployeeType type) {
		Optional<EmployeeType> entityTypeOptional = employeeTypeRepo.findById(type.getTypeId());
		StringBuilder jsonStringB = new StringBuilder();
		if (entityTypeOptional.isPresent()) {
			try {
				EmployeeType entityType = entityTypeOptional.get();
				String[] ignoreArray = getEmpTypeIgnoreFieldArray();
				BeanUtils.copyProperties(type, entityType, ignoreArray);
				employeeTypeRepo.save(entityType);
				jsonStringB.append(jacksonObjectMapper.writeValueAsString(entityType));
			} catch (Exception e) {
				jsonStringB.append("not suc : " + e.getMessage());
				String jsonString = jsonStringB.toString();
				return jsonString;
			}

			return jsonStringB.toString();
		}
		return jsonStringB.append("not suc : can't find entity").toString();
	}

	private String[] getEmpTypeIgnoreFieldArray() {
		String[] ignoreArray = { "employees", "typeId" };
		return ignoreArray;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteType(@RequestParam Long[] ids) {
		StringBuilder jsonStringB = new StringBuilder();
		StringBuilder cantFindIdStb = new StringBuilder();
		List<EmployeeType> employeeTypeList = new ArrayList<EmployeeType>();
		for(Long id : ids) {
			Optional<EmployeeType> employeeTypeO = employeeTypeRepo.findById(id);
			if (employeeTypeO.isPresent()) {
				EmployeeType employeeType = employeeTypeO.get();
				employeeTypeList.add(employeeType);
//				employeeTypeRepo.delete(employeeType);
				
			} else {
				cantFindIdStb.append(id.toString()+",");
			}

		}
		
		if(cantFindIdStb.length() == 0) {
			for(EmployeeType empType : employeeTypeList) {
				employeeTypeRepo.delete(empType);
			}
			jsonStringB.append("delete suc");
		}else {
			cantFindIdStb.setLength(cantFindIdStb.length()-1);
			jsonStringB.append("Can't find employee type for delete with this parameter : ");
			jsonStringB.append(cantFindIdStb.toString());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonStringB.toString());
		}
		
		
		return ResponseEntity.ok().build();
	}
}
