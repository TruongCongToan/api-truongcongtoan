package com.example.demo.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmailDTO;
import com.example.demo.model.EmailDataModel;
import com.example.demo.service.impl.EmailService;

@RestController 
@CrossOrigin(origins = "https://itss-random.herokuapp.com")
public class MailSenderController {
	
	@Autowired
//	private EmailSenderService senderService;
	private EmailService emailService;
	
	//get all doctor infor
	@PostMapping("/api/sendEmail")
//	@EventListener(ApplicationReadyEvent.class)
	public void sendMail(@Valid @RequestBody EmailDataModel emailDataModel) throws SQLException {

		EmailDTO email = new EmailDTO();
		
		email.setTo(emailDataModel.getEmail());
		email.setSubject("Health care xin thông báo đặt lịch khám thành công");
		// Populate the template data
				Map<String, Object> templateData = new HashMap<>();
				templateData.put("name", emailDataModel.getFull_name());
				
				String full_name =String.format("Họ và tên bệnh nhân : %s", emailDataModel.getFull_name()); 
				String gender = String.format("Giới tính : %s",emailDataModel.getGender());		
				String birth_year = String.format("Năm sinh : %s",emailDataModel.getBirth_year());
				String email_address = String.format("Địa chỉ email : %s",emailDataModel.getEmail());
				String phone_number = String.format("Số điện thoại : %s",emailDataModel.getPhone_number());
				String reason = String.format("Lý do khám : %s ", emailDataModel.getReason());
				String ngaykham = String.format("Ngày khám : %s", emailDataModel.getNgaykham());
				String price = String.format("Giá khám : %s VNĐ", emailDataModel.getPrice());
				String doctor_name = String.format("Bác sĩ khám : Bác sĩ chuyên khoa %s",emailDataModel.getDoctor_name());
				
				// List of team members...
				List<String> booking_info = Arrays.asList(full_name,gender,birth_year,email_address,phone_number,reason,ngaykham,price,doctor_name);
				
				templateData.put("booking_info", booking_info);
				
				email.setEmailData(templateData);
				
				emailService.sendWelcomeEmail(email);
				
	}
}
