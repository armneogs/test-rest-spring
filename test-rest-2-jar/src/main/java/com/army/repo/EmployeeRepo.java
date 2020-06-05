package com.army.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.army.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
	@Query("select emp from Employee emp where emp.employeeType.typeId = ?1")
	List<Employee> findByTypeId(Long typeId);
	@Query("select emp from Employee emp where emp.name like %?1%")
	List<Employee> findByName(String name);
	@Query("select emp from Employee emp where emp.employeeId in(?1)")
	List<Employee> findByNameList(String[] values);

	
}
