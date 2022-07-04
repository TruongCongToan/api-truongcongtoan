package com.example.demo.model;



import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleModel {
	private int maxnumber;
	@JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "date")
	private String date;
	private int doctorid;
	private String timetype;

}
