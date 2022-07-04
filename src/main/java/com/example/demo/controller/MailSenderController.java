package com.example.demo.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmailDataModel;
import com.example.demo.service.impl.EmailSenderService;

@RestController 
@CrossOrigin(origins = "https://itss-random.herokuapp.com")
public class MailSenderController {
	
	@Autowired
	private EmailSenderService senderService;
	
	//get all doctor infor
	@PostMapping("/api/sendEmail")
//	@EventListener(ApplicationReadyEvent.class)
	public void sendMail(@Valid @RequestBody EmailDataModel emailDataModel) throws SQLException {
		System.out.println(emailDataModel);
		senderService.sendEmail(emailDataModel.getEmail(), "Health care thông báo đặt lịch khám thành công" , "Xin chao");
	}
}
