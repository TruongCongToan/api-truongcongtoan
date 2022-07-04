package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name = "doctors")
@Data
public class Doctors implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	  @Column(name = "username")
	private String username;
	  @Column(name = "chuyenkhoa")
	private String chuyenkhoa;
	  @Column(name = "donvicongtac")
	private String donvicongtac	;
	  @Column(name = "kinhnghiem")
	 private String kinhnghiem;
	  @Column(name = "danhgia")
	private String danhgia;
	  @Column(name = "diemdanhgia")
	private Double diemdanhgia;
	  @Column(name = "sotaikhoan")
	 private String sotaikhoan;
	 


}