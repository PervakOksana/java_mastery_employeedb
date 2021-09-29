package com.mastery.java.task.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mastery.java.task.dto.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long>{

}