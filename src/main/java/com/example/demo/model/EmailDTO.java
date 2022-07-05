package com.example.demo.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

	private String to;
	private String subject;
	private Map<String, Object> emailData;

	// Generate Getters and Setters...
}