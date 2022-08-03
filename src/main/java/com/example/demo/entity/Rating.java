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
@Table(name = "rating")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	
	@Lob
	@Column(name = "rating")
	private String rating;
	
	 @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "createat")
	private Date createat;
	  
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "updateat")
	private Date updateat;
	  
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "user_id")
	  private Users users;
	  
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "doctor_id")
	  private Users doctors;
	  
	  @OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "doctor_id", referencedColumnName = "doctorid", insertable = false, updatable = false)
		private EmailData emailData = null;
	  
	  
	
}
