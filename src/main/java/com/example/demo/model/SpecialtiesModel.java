package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtiesModel {
		private int id;
		private String name;
		private String image ;
		private String contentMarkDown;
		private Date created_at;
		private Date updated_at;

}
