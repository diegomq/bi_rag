package com.empmgmnt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empmgmnt.domain.Employee;
import com.empmgmnt.repositories.EmployeeRepository;
import com.empmgmnt.transferobjects.EmployeeDTO;

@Service
@Component
public class EmployeeServiceImpl implements EmployeeService {
	private static Integer new_emp_id = 0;

	private synchronized static Integer getNewEmpId() {
		return new_emp_id++;
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	private Employee getEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setEmpid(employeeDTO.getEmpid());
		employee.setName(employeeDTO.getName());
		employee.setAge(employeeDTO.getAge());
		employee.setAddress(employeeDTO.getAddress());
		employee.setSalary(employeeDTO.getSalary());
		return employee;
	}

	@Override
	@Transactional
	public void create(EmployeeDTO emp) {
		System.out.println("############ Saving service ##############");
		Employee e = getEmployee(emp);
		e.setEmpid(EmployeeServiceImpl.getNewEmpId());
		employeeRepository.save(e);
		System.out.println("########### After Saving service ##############");
	}

	@Override
	@Transactional
	public void delete(EmployeeDTO emp) {
		employeeRepository.delete(getEmployee(emp));
	}

	@Override
	@Transactional
	public Employee search(Integer id) {
		return employeeRepository.findOne(Long.valueOf(id));
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
}