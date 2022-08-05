package com.example.demo.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EmailData;
import com.example.demo.entity.Response;
import com.example.demo.model.EmailDTO;
import com.example.demo.model.EmailDataModel;

@Service
public interface IEmailService {

	public void sendWelcomeEmail(EmailDTO emailDTO) throws SQLException;
public EmailData addEmailData( EmailDataModel emailDataModel) throws SQLException;
	
	public Response editClinic(String timetype, int patientid,String date,int doctorid,String statusid) throws SQLException;
}
