package com.example.demo.service.impl;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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
			
			String data = "data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAApgAAAKYB3X3/OAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAANCSURBVEiJtZZPbBtFFMZ/M7ubXdtdb1xSFyeilBapySVU8h8OoFaooFSqiihIVIpQBKci6KEg9Q6H9kovIHoCIVQJJCKE1ENFjnAgcaSGC6rEnxBwA04Tx43t2FnvDAfjkNibxgHxnWb2e/u992bee7tCa00YFsffekFY+nUzFtjW0LrvjRXrCDIAaPLlW0nHL0SsZtVoaF98mLrx3pdhOqLtYPHChahZcYYO7KvPFxvRl5XPp1sN3adWiD1ZAqD6XYK1b/dvE5IWryTt2udLFedwc1+9kLp+vbbpoDh+6TklxBeAi9TL0taeWpdmZzQDry0AcO+jQ12RyohqqoYoo8RDwJrU+qXkjWtfi8Xxt58BdQuwQs9qC/afLwCw8tnQbqYAPsgxE1S6F3EAIXux2oQFKm0ihMsOF71dHYx+f3NND68ghCu1YIoePPQN1pGRABkJ6Bus96CutRZMydTl+TvuiRW1m3n0eDl0vRPcEysqdXn+jsQPsrHMquGeXEaY4Yk4wxWcY5V/9scqOMOVUFthatyTy8QyqwZ+kDURKoMWxNKr2EeqVKcTNOajqKoBgOE28U4tdQl5p5bwCw7BWquaZSzAPlwjlithJtp3pTImSqQRrb2Z8PHGigD4RZuNX6JYj6wj7O4TFLbCO/Mn/m8R+h6rYSUb3ekokRY6f/YukArN979jcW+V/S8g0eT/N3VN3kTqWbQ428m9/8k0P/1aIhF36PccEl6EhOcAUCrXKZXXWS3XKd2vc/TRBG9O5ELC17MmWubD2nKhUKZa26Ba2+D3P+4/MNCFwg59oWVeYhkzgN/JDR8deKBoD7Y+ljEjGZ0sosXVTvbc6RHirr2reNy1OXd6pJsQ+gqjk8VWFYmHrwBzW/n+uMPFiRwHB2I7ih8ciHFxIkd/3Omk5tCDV1t+2nNu5sxxpDFNx+huNhVT3/zMDz8usXC3ddaHBj1GHj/As08fwTS7Kt1HBTmyN29vdwAw+/wbwLVOJ3uAD1wi/dUH7Qei66PfyuRj4Ik9is+hglfbkbfR3cnZm7chlUWLdwmprtCohX4HUtlOcQjLYCu+fzGJH2QRKvP3UNz8bWk1qMxjGTOMThZ3kvgLI5AzFfo379UAAAAASUVORK5CYII=";
			String body = data.replace("data:image/png;base64",""); 
			byte[] imgBytes = Base64.getMimeDecoder().decode(body);
			ByteArrayDataSource dSource = new ByteArrayDataSource(imgBytes, "image/*");
			helper.addAttachment("QRCode.png", dSource);

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