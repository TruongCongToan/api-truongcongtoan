package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AnswerModel {
	private int id;
	private String answer;
	 private int question_id;
	 private int user_id;
	 private Date createat;
	 private Date updateat;
}
