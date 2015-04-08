package br.com.reindex.managed.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.reindex.repositories.EmployeeService;
import br.com.reindex.suri.framework.controller.ControllerSupport;
import br.com.reindex.vo.EmployeeDTO;

@Component
@ManagedBean
@SessionScoped
public class EmployeeBean extends ControllerSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String address;
	private Integer age;
	private Integer salary;
	private Integer empid;

	@Inject
	@Autowired
	EmployeeService employeeServiceImpl;

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

	public void saveEmployee() {
		System.out.println("############# Saving ################");
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setAddress(this.getAddress());
		employeeDTO.setName(this.getName());
		employeeDTO.setAge(this.age);
		employeeDTO.setSalary(this.salary);
		System.out.println("############# impl ################ "
				+ employeeServiceImpl);
		employeeServiceImpl.create(employeeDTO);
		System.out.println("#################### After Saving ##############");
		
		
	}
}
