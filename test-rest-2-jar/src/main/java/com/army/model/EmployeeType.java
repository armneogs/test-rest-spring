package com.army.model;
// Generated Jun 9, 2020 1:26:19 PM by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * EmployeeType generated by hbm2java
 */
@Entity
@Table(name = "employee_type", schema = "public")
public class EmployeeType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3892664727289337205L;
	private long typeId;
	private Status status;
	private String typeName;
	private Set<Employee> employees = new HashSet<Employee>(0);

	public EmployeeType() {
	}

	public EmployeeType(long typeId, Status status) {
		this.typeId = typeId;
		this.status = status;
	}

	public EmployeeType(long typeId, Status status, String typeName, Set<Employee> employees) {
		this.typeId = typeId;
		this.status = status;
		this.typeName = typeName;
		this.employees = employees;
	}

	@Id

	@Column(name = "type_id", unique = true, nullable = false)
	public long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "type_name")
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeType")
	@JsonIgnore
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
