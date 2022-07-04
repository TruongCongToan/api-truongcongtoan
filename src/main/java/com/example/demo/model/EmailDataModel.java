
package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDataModel {
	private int id;
	private String full_name;
	private String gender;
	private String birth_year;
	private String email;
	private String phone_number;
	private String reason;
	private String address;
	private String price;
}
