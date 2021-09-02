package com.mastery.java.task.dto;

public class Employee {

	private int employeeId;
	private String firstName;
	private String lastName;
	private Long departmentId;
	private String jobTitle;
	private Gender gender;	

	public Employee() {
		super();
	}

	public Employee(int employeeId, String firstName, String lastName, Long departmentId, String jobTitle,
			Gender gender) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = departmentId;
		this.jobTitle = jobTitle;
		this.gender = gender;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", departmentId=" + departmentId + ", jobTitle=" + jobTitle + ", gender=" + gender + "]";
	}

	public static class Builder {

		private int employeeId;
		private String firstName;
		private String lastName;
		private Long departmentId;
		private String jobTitle;
		private Gender gender;	

		public Builder setEmployeeId(int employeeId) {
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

        public Employee build(){
            return new Employee(employeeId, firstName, lastName, departmentId, jobTitle, gender);
        }
    }
}
