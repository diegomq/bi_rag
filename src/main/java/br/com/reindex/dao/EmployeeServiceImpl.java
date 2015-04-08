package br.com.reindex.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.reindex.domain.Employee;
import br.com.reindex.repositories.EmployeeService;
import br.com.reindex.suri.framework.dao.DaoSupport;
import br.com.reindex.vo.EmployeeDTO;

@Service
@Component
public class EmployeeServiceImpl extends DaoSupport<Employee, Serializable> implements EmployeeService {


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
		getSession().save(e);
		System.out.println("########### After Saving service ##############");
	}

	@Override
	@Transactional
	public void delete(EmployeeDTO emp) {
		getSession().delete(emp);
	}

	@Override
	@Transactional
	public Employee search(Integer id) {
		return retrieve(id);
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return findAll();
	}
}