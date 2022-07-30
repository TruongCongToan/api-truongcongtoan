package com.example.demo.model;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionModel {
	
	private int id;
	private String question;
	private String subject;
	 private int user_id;
	 private Date createat;
	 private Date updateat;
}
