package com.mastery.java.task.service.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionInfo {

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

		public ExceptionInfo build() {
			return new ExceptionInfo(timestamp, messages);
		}
	}

}
