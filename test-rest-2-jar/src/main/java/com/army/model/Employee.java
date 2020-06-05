package com.army.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

@Entity
public class Employee implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6490537651879085712L;
	@Id
	@GeneratedValue
	private Long employeeId;
	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn(name = "type_id",nullable = false)
	private EmployeeType employeeType;
	private String name;
	private BigDecimal salary;

	public Long getemployeeId() {
		return employeeId;
	}
	public void setemployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	
}
