package br.com.epa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.empmgmnt.domain.Employee;

public class Util {
	
	public static void main(String[] args) {
		EntityManagerFactory factory =  Persistence.createEntityManagerFactory("empmgmnt");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Employee employee = new Employee();
		employee.setAddress("platina");
		employee.setAge(26);
		employee.setName("diego");
		employee.setSalary(0);
		employee.setEmpid(1);
		
		manager.persist(employee);
		trx.commit();
		manager.close();
		
	}

}
