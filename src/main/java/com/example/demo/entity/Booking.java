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
@Table(name = "booking")
@Data
public class Booking implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
		
	  @Column(name = "statusid")
	private String statusId;
	  
	  @Column(name = "doctorid")
	private int doctorid;
	  
	  @Column(name = "patientid")
		private int patientid;
	  
	  @Column(name = "date")
	private String date;
	  
	  @Column(name = "timetype")
	  private String timetype;
	  
	  @Column(name = "token")
	  private String token;
	  
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "createat")
	private Date createat;
	  
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "updateat")
	private Date updateat;
	  
	  

}
