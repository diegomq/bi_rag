package com.empmgmnt.services;

import java.util.List;
import com.empmgmnt.domain.Employee;
import com.empmgmnt.transferobjects.EmployeeDTO;

public interface EmployeeService {
	public void create(EmployeeDTO emp);

	public void delete(EmployeeDTO emp);

	public Employee search(Integer id);

	public List<Employee> findAll();
}