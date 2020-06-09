package com.army.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.army.model.EmployeeType;
import com.army.model.Status;

public interface StatusRepo extends  CrudRepository<Status, String> {

}
