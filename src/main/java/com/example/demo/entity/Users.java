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
import javax.persistence.Lob;
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
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int user_id;

	  @Column(name = "email")
	private String email;
	  
	  @Column(name = "password")
		private String password;

	  @Column(name = "address")
	 private String address;
	  
	  @Column(name = "phone_number")
	private String phone_number;
	  
	  @Column(name = "gender")
	private String gender;
	  
	  @Column(name = "role")
	 private String role;
	  
	  @Column(name = "full_name")
	private String full_name;
	  
	  @Lob
	  @Column(name = "image",length = Integer.MAX_VALUE,nullable = true)
	private String image ;
	  
	  @Column(name = "position")
	private String position;
	  
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "created_at")
	private Date created_at;
	  
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "updated_at")
	private Date updated_at;
	  
	  @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "position", referencedColumnName = "key", insertable = false, updatable = false)
		private AllCode allCodePosition = null;
	  
	  @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "role", referencedColumnName = "key", insertable = false, updatable = false)
		private AllCode allCodeRole = null;
	  
//	  @OneToOne(cascade = CascadeType.ALL)
//			@JoinColumn(name = "doctorid")
//			private MarkDown markDown;
	  }