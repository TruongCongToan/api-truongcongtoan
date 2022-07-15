package com.example.demo.entity;

import java.io.Serializable;

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


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "markdown")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkDown implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int markdown_id;
		@Lob
	  @Column(name = "contentHTML")
	private String contentHTML;
		@Lob
	  @Column(name = "contentMarkDown")
	private String contentMarkDown;
	  @Column(name = "description")
	  @Lob
	private String description;
	 
	  @Column(name = "clinic_id")
		 private Integer clinic_id;
	 
//	  @Column(name = "doctorid")
//		 private Integer doctorid;
	  @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "doctorid")
		private Users users;
	
	   


}