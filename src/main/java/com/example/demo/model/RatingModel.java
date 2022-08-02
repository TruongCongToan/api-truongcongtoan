package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingModel {

	 private int id;
	 private String rating ;
	 private Date createat;
	 private Date updateat;
	 private int user_id;
	 private int doctor_id;
}
