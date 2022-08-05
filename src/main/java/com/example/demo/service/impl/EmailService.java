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
import com.example.demo.entity.Booking;
import com.example.demo.entity.EmailData;
import com.example.demo.entity.Response;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.BookingModel;
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
	
	@Autowired
	BookingService bookingService;
	
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
	
	public void sendQRCodeEmail(EmailDTO emailDTO,String qrcode) {
		System.out.println("##### Started sending welcome email ####");

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
	      
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			String templateContent = FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfig.getConfiguration()
							.getTemplate("/email/qrcode.ftlh"),
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

		
	}
	
	public BookingModel defineBookingModel(String timetype, int patientid,String date,int doctorid,String statusid,String token) {
		BookingModel bookingModel = new BookingModel();
		bookingModel.setDate(date);
		bookingModel.setDoctorid(doctorid);	
		bookingModel.setPatientid(patientid);
		bookingModel.setStatusId(statusid);
		bookingModel.setTimetype(timetype);
		bookingModel.setToken(token);
		return bookingModel;
		
	}

	@Override
	public Response editClinic(String timetype, int patientid,String date,int doctorid,String statusid) throws SQLException {
		Response response = new Response();
		if (emailDAO.getByDoctorID(patientid,date,doctorid,timetype) != null) {
			EmailData emailData = emailDAO.getByDoctorID(patientid,date,doctorid,timetype);
			if(emailData != null) {
					
					BookingModel bookingModel =	defineBookingModel(timetype,patientid,date,doctorid,statusid,emailData.getBooking().getToken());
					bookingService.editBooking(bookingModel, doctorid);
					
					
					response.setErrorCode(200);
					response.setMessage("Update Success !");
					return response;
			}else {
				response.setErrorCode(400);
				response.setMessage(" Du lieu nhap vao khong the ho tro tim email Data!");
				return response;
			}
		
		}else {
			response.setErrorCode(400);
			response.setMessage(" Du lieu nhap vao khong the ho tro tim email Data!");
			return response;
		}
	}
}