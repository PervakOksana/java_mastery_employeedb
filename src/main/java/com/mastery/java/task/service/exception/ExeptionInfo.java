package com.mastery.java.task.service.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.dto.Employee.Builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExeptionInfo {

	@JsonProperty("timestamp")
	private Date timestamp;
	
	@JsonProperty
	private List<String> messages = new ArrayList<>();
	

	public static class Builder {
	
		private Date timestamp;
		private List<String> messages = new ArrayList<>();
		

		public Builder messages(List<String> messages) {
			this.messages = messages;
			return this;
		}
		
		public Builder messages(String message) {
			this.messages.add(message);
			return this;
		}

		public Builder timestamp(Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		

		public ExeptionInfo build() {
			return new ExeptionInfo(timestamp,messages);
		}
	}

}
