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
@Table(name = "allcodes")
@Data
public class AllCode implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	  @Column(name = "key")
	private String key;
	  @Column(name = "type")
	private String type;
	  @Column(name = "valueen")
	private String valueEn;
	  @Column(name = "valuevi")
	  private String valuevi;
	  

}
