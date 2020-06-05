package com.army.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private Long message_id;
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;
	private String message_body;
	public Long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Long message_id) {
		this.message_id = message_id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getMessage_body() {
		return message_body;
	}
	public void setMessage_body(String message_body) {
		this.message_body = message_body;
	}	
}
