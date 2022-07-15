package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Entity
@Table(name = "schedules")
@Data
public class Schedule implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "currentnumber")
	private Integer currentnumber;
	@Column(name = "maxnumber")
	private Integer maxnumber;
	@Column(name = "timetype")
	private String timetype;

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	
	@Column(name = "date")
	private String date;
	@Column(name = "doctorid")
	private Integer doctorid;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Column(name = "createat")
	private Timestamp createat;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Column(name = "updateat")
	private Timestamp updateat;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "timetype", referencedColumnName = "key", insertable = false, updatable = false)
	private AllCode allCode = null;
//
//	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
//    @JoinColumn(name="doctorid",insertable=false, updatable=false)
//    private Users users;
}
