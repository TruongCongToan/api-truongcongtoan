
package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDataModel {
	
	private int doctorid;
	private int patientid;
	private String full_name;
	private String gender;
	private String birth_year;
	private String email;
	private String phone_number;
	private String reason;
	private String address;
	private String price;
	private String ngaykham;
	private String doctor_name;
//	private String direct_url;
	private String date;
	private int booking_id;
	private String timetype;
	private Date created_at;
	private Date updated_at;
}
