package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminModel {
	private int id;
	private String username;
	private String password;
	private String email;
	private String phonenumber;
	private int active;
	private String image;
	
	
}
