package edu.jong.server.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {

	private String message;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
	private LocalDateTime timestamp;

	@Builder
	public ErrorResponse(String message) {
		super();
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}
	
}
