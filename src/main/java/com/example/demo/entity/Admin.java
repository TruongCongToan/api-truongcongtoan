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
@Table(name = "admin")
@Data
public class Admin implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	  @Column(name = "username")
	private String username;
	  @Column(name = "password")
		private String password;
	  @Column(name = "email")
	private String email;
	  @Column(name = "phonenumber")
	private String phonenumber;
	@Column(name="active")
	private int active;
	  @Column(name = "image")
	private String image;
	  
}