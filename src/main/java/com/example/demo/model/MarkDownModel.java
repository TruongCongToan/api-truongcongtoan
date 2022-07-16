package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkDownModel {
	private int markdown_id;
	private String contentHTML;
	private String contentMarkDown;
	private int specialty_id;
	private int clinic_id;
	private int doctorid;
	
	private String description;
}
