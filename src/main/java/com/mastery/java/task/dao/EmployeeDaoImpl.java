package com.mastery.java.task.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Employee> findAll() {
		return getEmployees("SELECT * FROM employee");
	}

	@Override
	public Employee findById(int id) {
		List<Employee> employees = getEmployees("SELECT * FROM employee WHERE employee_id =", id);
		if (employees.size() == 1) {
			return employees.get(0);
		}
		return null;
	}

	@Override
	public Employee save(Employee employee) {

		String sql = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender ) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(),
				employee.getJobTitle(), employee.getGender().toString());
		employee.setEmployeeId(maxEmployeeId());
		return employee;

	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM employee WHERE employee_id=?", id);
	}

	@Override
	public Employee update(Employee employee) {
		String sql = "UPDATE employee SET first_name=?, last_name=?, department_id=?, job_title=?, gender=? WHERE employee_id=? ";
		jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(),
				employee.getJobTitle(), employee.getGender().toString(), employee.getEmployeeId());
		return employee;

	}

	@Override
	public int maxEmployeeId() {
		return jdbcTemplate.queryForObject("SELECT MAX(employee_id) FROM employee", Integer.class);
	}

	private List<Employee> getEmployees(String sql) {
		return jdbcTemplate.query(sql,
				(rs, rowNum) -> new Employee(rs.getInt("employee_id"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getLong("department_id"), rs.getString("job_title"),
						Gender.valueOf(rs.getString("gender").trim())));
	}

	private List<Employee> getEmployees(String sql, int id) {
		return jdbcTemplate.query(sql + id,
				(rs, rowNum) -> new Employee(rs.getInt("employee_id"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getLong("department_id"), rs.getString("job_title"),
						Gender.valueOf(rs.getString("gender").trim())));
	}
}
