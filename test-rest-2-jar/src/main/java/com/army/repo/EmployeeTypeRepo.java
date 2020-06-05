package com.army.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.army.model.EmployeeType;

public interface EmployeeTypeRepo extends CrudRepository<EmployeeType, Long> {
	@Query("select emp_type from EmployeeType emp_type where emp_type.typeName = ?1")
	List<EmployeeType> findByTypeName(String name);
	
	
}
