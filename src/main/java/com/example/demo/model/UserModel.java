package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	private int user_id;
	private String email;
	private String password;
	private String address;
	private String phone_number;
	private String gender;
	private String role;
	private String image;
	private Integer specialty_id;
	private String full_name;
	private String statusid;
	private String qrcode;
	private String uuid;
	
	@JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "created_at")
	private Date created_at;
	@JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "updated_at")
	private Date updated_at;
	
}