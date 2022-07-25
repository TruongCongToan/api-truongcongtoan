package com.example.demo.service.impl;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.example.demo.DAO.IBookingDAO;
import com.example.demo.DAO.IEmailDAO;
import com.example.demo.entity.EmailData;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.EmailDTO;
import com.example.demo.model.EmailDataModel;
import com.example.demo.service.IEmailService;


@Service
public class EmailService implements IEmailService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;

	@Autowired
	IEmailDAO emailDAO;
	
	@Autowired
	IBookingDAO bookingDAO;
	
	public void sendWelcomeEmail(EmailDTO emailDTO) {
		System.out.println("##### Started sending welcome email ####");

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			String templateContent = FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfig.getConfiguration()
							.getTemplate("/email/doctor.ftlh"),
							emailDTO.getEmailData());

			helper.setTo(emailDTO.getTo());
			helper.setSubject(emailDTO.getSubject());
			helper.setText(templateContent, true);
			mailSender.send(mimeMessage);

			System.out.println("######## Welcome email sent ######");
		} catch (Exception e) {
			System.out.println("Sending welcome email failed, check log...");
			e.printStackTrace();
		}
	}

	public void sendResetPasswordEmail(EmailDTO emailDTO) {
		System.out.println("##### Started sending welcome email ####");

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			String templateContent = FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfig.getConfiguration()
							.getTemplate("/email/resetpassword.ftlh"),
							emailDTO.getEmailData());

			helper.setTo(emailDTO.getTo());
			helper.setSubject(emailDTO.getSubject());
			helper.setText(templateContent, true);
			mailSender.send(mimeMessage);

			System.out.println("######## Welcome email sent ######");
		} catch (Exception e) {
			System.out.println("Sending welcome email failed, check log...");
			e.printStackTrace();
		}
	}
	@Override
	public EmailData addEmailData(EmailDataModel emailDataModel) throws SQLException {
		System.out.println("Gia tri check "+emailDAO.getByDoctorID(emailDataModel.getPatientid(),emailDataModel.getDate(),emailDataModel.getDoctorid(),emailDataModel.getTimetype()));
		if (emailDAO.getByDoctorID(emailDataModel.getPatientid(),emailDataModel.getDate(),emailDataModel.getDoctorid(),emailDataModel.getTimetype()) == null) {
			EmailData emailData = new EmailData();
			
			emailData.setFull_name(emailDataModel.getFull_name());
			emailData.setGender(emailDataModel.getGender());
			emailData.setBirth_year(emailDataModel.getBirth_year());
			emailData.setEmail_address(emailDataModel.getEmail());
			emailData.setPhone_number(emailDataModel.getPhone_number());
			emailData.setReason(emailDataModel.getReason());
			emailData.setNgaykham(emailDataModel.getNgaykham());
			emailData.setDoctor_name(emailDataModel.getDoctor_name());
			emailData.setPrice(emailDataModel.getPrice());
			emailData.setDoctorid(emailDataModel.getDoctorid());
			emailData.setPatientid(emailDataModel.getPatientid());
			emailData.setDate(emailDataModel.getDate());
			emailData.setCreated_at(new Date());
			emailData.setTimetype(emailDataModel.getTimetype());
			emailData.setBooking(bookingDAO.getBookingByID(emailDataModel.getBooking_id()));
			
		 return	emailDAO.save(emailData);

		}else {
			throw new DuplicateRecordException("Da co user nay trong danh sach");
		}
		
	}

	@Override
	public EmailData editClinic(EmailDataModel emailDataModel, int patientid,String date,int doctorid) throws SQLException {
		if (emailDAO.getByDoctorID(patientid,date,doctorid,emailDataModel.getTimetype()) != null) {
			EmailData emailData = emailDAO.getByDoctorID(patientid,date,doctorid,emailDataModel.getTimetype());
			
			if (!emailData.getFull_name().equals(emailDataModel.getFull_name())) {
				emailData.setFull_name(emailDataModel.getFull_name());
 			}
			if (!emailData.getGender().equals(emailDataModel.getGender())) {
				emailData.setGender(emailDataModel.getGender());
 			}
			if (!emailData.getBirth_year().equals(emailDataModel.getBirth_year())) {
				emailData.setBirth_year(emailDataModel.getBirth_year());
 			}
			if (!emailData.getEmail_address().equals(emailDataModel.getEmail())) {
				emailData.setEmail_address(emailDataModel.getEmail());
 			}
			if (!emailData.getPhone_number().equals(emailDataModel.getPhone_number())) {
				emailData.setPhone_number(emailDataModel.getPhone_number());
 			}
			
			if (!emailData.getReason().equals(emailDataModel.getReason())) {
				emailData.setReason(emailDataModel.getReason());
 			}
			if (!emailData.getNgaykham().equals(emailDataModel.getNgaykham())) {
				emailData.setNgaykham(emailDataModel.getNgaykham());
 			}
			if (!emailData.getDoctor_name().equals(emailDataModel.getDoctor_name())) {
				emailData.setDoctor_name(emailDataModel.getDoctor_name());
 			}
			if (!emailData.getDoctor_name().equals(emailDataModel.getPrice())) {
				emailData.setPrice(emailDataModel.getPrice());
 			}
			if (emailData.getDoctorid() != emailDataModel.getDoctorid()) {
				emailData.setDoctorid(emailDataModel.getDoctorid());
 			}
			if (emailData.getPatientid() != emailDataModel.getPatientid()) {
				emailData.setPatientid(emailDataModel.getPatientid());
 			}
			if (emailData.getPatientid() != emailDataModel.getPatientid()) {
				emailData.setTimetype(emailDataModel.getTimetype());
 			}
			if (emailData.getBooking().equals(bookingDAO.getBookingByID(emailDataModel.getBooking_id())) == false) {
				emailData.setBooking(bookingDAO.getBookingByID(emailDataModel.getBooking_id()));
 			}
			emailData.setUpdated_at(new Date());
			
			return emailDAO.saveAndFlush(emailData);
		}else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
	}
}