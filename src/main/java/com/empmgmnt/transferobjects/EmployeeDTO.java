package com.empmgmnt.transferobjects;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = -7549246418501536528L;
	private String name;
	private String address;
	private Integer age;
	private Integer salary;
	private Integer empid;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}