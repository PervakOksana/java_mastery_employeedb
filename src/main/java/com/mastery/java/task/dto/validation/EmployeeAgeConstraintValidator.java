package com.mastery.java.task.dto.validation;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.mastery.java.task.dto.annatation.EmployeeAgeConstraint;

public class EmployeeAgeConstraintValidator implements ConstraintValidator<EmployeeAgeConstraint, LocalDate>{

	@Override
	public boolean isValid(LocalDate dateBirth, ConstraintValidatorContext context) {
		if (dateBirth == null) return false;
		return LocalDate.now().minusYears(18).isAfter(dateBirth);
	}

}
