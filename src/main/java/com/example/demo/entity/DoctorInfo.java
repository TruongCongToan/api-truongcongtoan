package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "doctorinfo")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 @Column(name = "doctorid")
	 private int doctorid;
	 
	 @Column(name = "priceid")
	 private String priceid;
	 
	 @Column(name = "provinceid")
	 private String provinceid;
	 
	 @Column(name = "addressclinicid")
	 private String addressclinicid;
	 
	 @Column(name = "nameclinic")
	 private String nameclinic;
	 
	 @Column(name = "note")
	 private String note;
	 
	 @Column(name = "payment")
	 private String payment;
	 
	 @Column(name = "count")
	 private int count;
	 
	 @Column(name = "specialty_id")
	 private Integer specialty_id;
	 
	 @Column(name = "clinic_id")
	 private Integer clinic_id;
	 
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "createat")
	private Date createat;
	  
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "updateat")
	private Date updateat;
	   
	  @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "priceid", referencedColumnName = "key", insertable = false, updatable = false)
		private AllCode allCodePrice = null;
	  
	  @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "provinceid", referencedColumnName = "key", insertable = false, updatable = false)
		private AllCode allCodeProvince = null;
	  
	  @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "payment", referencedColumnName = "key", insertable = false, updatable = false)
		private AllCode allCodePayment = null;

	  @ManyToOne
	  @JoinColumn(name="specialty_id", referencedColumnName = "id", nullable=false,insertable = false, updatable =false)
	  private Specialties specialties;

//	  @OneToOne(cascade = CascadeType.ALL)
//		@JoinColumn(name = "doctorid", referencedColumnName = "doctorid", insertable = false, updatable = false)
//		private MarkDown markdown = null;
	  
}
