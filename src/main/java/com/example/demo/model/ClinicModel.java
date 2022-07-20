package com.example.demo.model;

import java.util.Date;

import com.example.demo.entity.Specialties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicModel {
		private int id;
		private String name;
		private String image ;
		private String contentMarkDown;
		private String themanhchuyenkhoa;
		private String address;
		private Specialties specialties;
		private Date created_at;
		private Date updated_at;

}
