package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sinhvien")
@Data
public class SinhvienByName {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	private String hodem;
	private String ten;

}
