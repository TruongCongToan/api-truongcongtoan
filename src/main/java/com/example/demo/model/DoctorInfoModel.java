package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInfoModel {
	private int id;
	private int doctorid;
	 private String priceid;
	 private String addressclinicid;
	 private String provinceid;
	 private String nameclinic;
	 private String note;
	 private int count;
	 private String payment;
	 private Date createat;
	 private Date updateat;
}