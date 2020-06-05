package com.army.controller;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.army.model.Employee;
import com.army.model.EmployeeType;
import com.army.repo.EmployeeRepo;
import com.army.repo.EmployeeTypeRepo;
import com.army.service.ConverterService;
import com.army.service.PropertyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	EmployeeTypeRepo employeeTypeRepo;
	@Autowired
	private ObjectMapper jacksonObjectMapper;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private ConverterService converterService;
	
	//	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getall/",method = RequestMethod.GET)
	public String getAllEmployee() {
		StringBuilder jsonStringB = new StringBuilder();
		List<Employee> employeeList = converterService.convertIterableToList(employeeRepo.findAll())  ;
		if(employeeList != null && !employeeList.isEmpty()) {
			try {
				jsonStringB.append(jacksonObjectMapper.writeValueAsString(employeeList));
			} catch (JsonProcessingException e) {
				jsonStringB.append(e);
				e.printStackTrace();
			}
		}else {
			jsonStringB.append("Can't find employee with this parameter");
		}
		String jsonString = jsonStringB.toString();
//		String jsonString = mapper.writeValueAsString(value);
		return jsonString;
	}
	
	//	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getallbyid/",method = RequestMethod.GET)
	public String getEmployeeListById() {
		StringBuilder jsonStringB = new StringBuilder();
		List<Employee> employeeList = converterService.convertIterableToList(employeeRepo.findAll())  ;
		if(employeeList != null && !employeeList.isEmpty()) {
			try {
				jsonStringB.append(jacksonObjectMapper.writeValueAsString(employeeList));
			} catch (JsonProcessingException e) {
				jsonStringB.append(e);
				e.printStackTrace();
			}
		}else {
			jsonStringB.append("Can't find employee with this parameter");
		}
		String jsonString = jsonStringB.toString();
//		String jsonString = mapper.writeValueAsString(value);
		return jsonString;
	}
	
	//	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getbyname/{name}",method = RequestMethod.GET)
	public String getEmployeesByName(@PathVariable("name") String name) {
		StringBuilder jsonStringB = new StringBuilder();
		List<Employee> employeeList =  employeeRepo.findByName(name);
		if(employeeList != null && !employeeList.isEmpty()) {
			try {
				jsonStringB.append(jacksonObjectMapper.writeValueAsString(employeeList));
			} catch (JsonProcessingException e) {
				jsonStringB.append(e);
				e.printStackTrace();
			}
		}else {
			jsonStringB.append("Can't find employee with this parameter");
		}
		String jsonString = jsonStringB.toString();
//		String jsonString = mapper.writeValueAsString(value);
		return jsonString;
	}
	
	//	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getbyid/{id}",method = RequestMethod.GET)
	public String getEmployeeById(@PathVariable("id") Long id) {
		StringBuilder jsonStringB = new StringBuilder();
		Optional<Employee> employeeO = employeeRepo.findById(id);
		if(employeeO.isPresent()) {
			Employee employee = employeeO.get();
			try {
				jsonStringB.append(jacksonObjectMapper.writeValueAsString(employee));
			} catch (JsonProcessingException e) {
				
				jsonStringB.append(e);
				e.printStackTrace();
			}
		}else {
			jsonStringB.append("Can't find employee with this parameter");
		}
		
		String jsonString = jsonStringB.toString();
		return jsonString;
	}
	
	//	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String saveEmployee(@RequestBody Employee employee) {
		try {
			
			Optional<EmployeeType> empTypeO = employeeTypeRepo.findById(employee.getEmployeeType().getTypeId());
			if(empTypeO.isPresent()) {
				employee.setEmployeeType(empTypeO.get());
				employeeRepo.save(employee);
			}else {
				return "not suc : can't employee type";
			}
			
		}catch (Exception e) {
			return "not suc : " + e.getMessage();
		}
		return "suc";
	}
	
	//	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/update",method = RequestMethod.PATCH)
	public String updateEmployee(@RequestBody Employee employee) {
		Optional<Employee> entityEmployeeOptional = employeeRepo.findById(employee.getemployeeId());
		if(entityEmployeeOptional.isPresent()) {
			try {
				Employee entityEmployee = entityEmployeeOptional.get();
				Set<String> ignoreSet =  propertyService.getNullPropertyNames(employee);

				BeanUtils.copyProperties(employee, entityEmployee,(String[]) ignoreSet.toArray());
				employeeRepo.save(entityEmployee);
			}catch (Exception e) {
				return "not suc : "+ e.getMessage();
			}
			return "suc";
		}
		return "not suc : can't find entity";
	}

	
	//	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable("id") Long id) {
		Optional<Employee> entityEmployeeOptional = employeeRepo.findById(id);
		if(entityEmployeeOptional.isPresent()) {
			try {
				Employee entityEmployee = entityEmployeeOptional.get();
				employeeRepo.delete(entityEmployee);
			}catch (Exception e) {
				return "not suc : "+ e.getMessage();
			}
			return "suc";
		}
		return "not suc : can't find entity";

	}
	
}
