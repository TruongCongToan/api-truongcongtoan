package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clinics")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clinic implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	
	 @Column(name = "name")
		private String name;
	@Lob
	@Column(name = "image",length = Integer.MAX_VALUE,nullable = true)
	private String image ;
	@Lob
	  @Column(name = "contentMarkDown")
	private String contentMarkDown;
	
	@Lob
	 @Column(name = "themanhchuyenkhoa")
	private String themanhchuyenkhoa;
	

	@Lob
	 @Column(name = "address")
	private String address;
	
	 @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "created_at")
	private Date created_at;
	  
	  @JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	  @Column(name = "updated_at")
	private Date updated_at;
	 
	  
	  @ManyToMany(cascade = CascadeType.ALL)
		@JoinTable(name = "specialties_clinic",
		joinColumns = @JoinColumn(name = "clinic_id"),
		inverseJoinColumns = @JoinColumn(name = "specialty_id")
				)
	  @JsonManagedReference
	  private Set<Specialties> likedSpecialties;
	
}
