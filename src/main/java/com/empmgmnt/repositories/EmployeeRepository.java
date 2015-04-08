package com.empmgmnt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empmgmnt.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
