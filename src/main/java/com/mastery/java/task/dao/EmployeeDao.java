package com.mastery.java.task.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mastery.java.task.dto.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {
	
	public Iterable<Employee> findEmployeeByFirstNameContainingAndLastNameContaining (String firstName,  String lastName);
	
}