package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emaildata")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailData implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	
	 @Column(name = "full_name")
		private String full_name;
	 @Column(name = "gender")
		private String gender;
	 @Column(name = "birth_year")
		private String birth_year;
	 @Column(name = "email_address")
		private String email_address;
	 @Column(name = "phone_number")
		private String phone_number;
	 @Column(name = "date")
		private String date;
	@Lob
	@Column(name = "reason")
	private String reason;
	@Column(name = "ngaykham")
	private String ngaykham;
	@Column(name = "price")
	private String price;
	@Column(name = "doctor_name")
	private String doctor_name;
	
	@Column(name = "doctorid")
	private int doctorid;
	
	@Column(name = "patientid")
	private int patientid;
	
	
	 @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "created_at")
	private Date created_at;
	  
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "updated_at")
	private Date updated_at;
	 
//	  @OneToOne(cascade = CascadeType.ALL)
//		@JoinColumn(name = "booking_id")
//		private Booking booking;
	
}
