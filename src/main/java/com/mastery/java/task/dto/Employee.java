package com.mastery.java.task.dto;

import java.time.LocalDate;
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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeId;

	@NotEmpty(message = "Please provide a first name")
	@ApiModelProperty(value = "first name of the employee")
	private String firstName;

	@NotEmpty(message = "Please provide a last name")
	@ApiModelProperty(value = "last name of the employee")
	private String lastName;

	@NotNull(message = "Please provide a number of department")
	@ApiModelProperty(value = "departmentId of the employee")
	private Long departmentId;

	@NotEmpty(message = "Please provide a job title")
	@ApiModelProperty(value = "job title of the employee")
	private String jobTitle;

	@NotNull(message = "Please provide a gender")
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "gender of the employee")
	private Gender gender;

	@EmployeeAgeConstraint(message = "The employee must be over 18")
	@ApiModelProperty(value = "date birth of the employee")
	private LocalDate dateBirth;

}
