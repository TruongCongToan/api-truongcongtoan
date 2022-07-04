package com.example.demo.model;

import com.example.demo.entity.Users;

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
	private int specialtyID;
	private int clinicID;
	private String description;
	private Users users;
}
