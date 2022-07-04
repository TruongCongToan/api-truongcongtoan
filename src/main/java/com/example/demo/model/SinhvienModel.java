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
public class SinhvienModel {
	private int id;
	private String hodem;
	private String ten;
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "dob")
	private Date dob;
	private String gioitinh;
	private String tinh;
	private String malop;
	private String masv;
}
