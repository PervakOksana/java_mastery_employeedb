package com.mastery.java.task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mastery.java.task.dto.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE e.firstName = :first_name")
	public List<Employee> findEmployeeByFirstName(@Param("first_name") String firstName);

	@Query("SELECT e FROM Employee e WHERE e.lastName = :last_name")
	public List<Employee> findEmployeeByLastName(@Param("last_name") String lastName);
	
	@Query("SELECT e FROM Employee e WHERE e.firstName = :first_name OR e.lastName = :last_name")
	public List<Employee> findEmployeeByFullName(@Param("first_name") String firstName, @Param("last_name") String lastName);

}