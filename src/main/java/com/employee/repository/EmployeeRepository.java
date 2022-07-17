package com.employee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("SELECT u FROM Employee u WHERE CONCAT(u.id,' ',u.email,' ',u.firstName,' ',u.lastName) LIKE %?1%")
	public Page<Employee> findAll(String keyword, Pageable pageable);

}
