package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
@Entity
@Table(name = "sinhvien")
@Data
public class Sinhvien implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	  @Column(name = "hodem")
	private String hodem;
	  @Column(name = "ten")
	private String ten;
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "dob")
	private Date dob;
	  
	  @Column(name = "gioitinh")
	private String gioitinh;
	  @Column(name = "tinh")
	private String tinh;
	  @Column(name = "malop")
	private String malop;
	  @Column(name = "masv")
	private String masv;

}
