package com.mastery.java.task.dto;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.mastery.java.task.dto.annatation.EmployeeAgeConstraint;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeId;

	@Column(name = "first_name")
	@NotEmpty(message = "Please provide a first name")
	@ApiModelProperty(value = "first name of the employee")
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message = "Please provide a last name")
	@ApiModelProperty(value = "last name of the employee")
	private String lastName;

	@Column(name = "department_id")
	@NotNull(message = "Please provide a number of department")
	@ApiModelProperty(value = "departmentId of the employee")
	private Long departmentId;

	@Column(name = "job_title")
	@NotEmpty(message = "Please provide a job title")
	@ApiModelProperty(value = "job title of the employee")
	private String jobTitle;

	@Column(name = "gender")
	@NotNull(message = "Please provide a gender")
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "gender of the employee")
	private Gender gender;

	@Column(name = "date_birth")
	@EmployeeAgeConstraint(message = "The employee must be over 18")
	@ApiModelProperty(value = "date birth of the employee")
	private LocalDate dateBirth;

	public static class Builder {

		private long employeeId;
		private String firstName;
		private String lastName;
		private Long departmentId;
		private String jobTitle;
		private Gender gender;
		private LocalDate dateBirth;

		public Builder setEmployeeId(long employeeId) {
			this.employeeId = employeeId;
			return this;
		}

		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder setDepartmentId(Long departmentId) {
			this.departmentId = departmentId;
			return this;
		}

		public Builder setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
			return this;
		}

		public Builder setGender(Gender gender) {
			this.gender = gender;
			return this;
		}

		public Builder setDateBirth(LocalDate dateBirth) {
			this.dateBirth = dateBirth;
			return this;
		}

		public Employee build() {
			return new Employee(employeeId, firstName, lastName, departmentId, jobTitle, gender, dateBirth);
		}
	}

}
