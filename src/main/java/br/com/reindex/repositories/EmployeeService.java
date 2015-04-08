package br.com.reindex.repositories;

import java.util.List;

import br.com.reindex.domain.Employee;
import br.com.reindex.vo.EmployeeDTO;

public interface EmployeeService {
	public void create(EmployeeDTO emp);

	public void delete(EmployeeDTO emp);

	public Employee search(Integer id);

	public List<Employee> findAll();
}