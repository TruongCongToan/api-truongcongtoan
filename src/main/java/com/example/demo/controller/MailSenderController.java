package com.example.demo.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.IBookingDAO;
import com.example.demo.entity.Booking;
import com.example.demo.model.BookingModel;
import com.example.demo.model.EmailDTO;
import com.example.demo.model.EmailDataModel;
import com.example.demo.service.impl.BookingService;
import com.example.demo.service.impl.EmailService;

@RestController 
@CrossOrigin(origins = "https://itss-random.herokuapp.com")
public class MailSenderController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BookingService bookingService;
	@Autowired
	private IBookingDAO bookingDAO;
	
	@Transient
	private UUID corrId = UUID.randomUUID();
	
	
	private BookingModel defineModelToken(Booking booking,UUID token) {
		
		BookingModel bookingModel = new BookingModel();
		bookingModel.setPatientid(booking.getPatientid());
		bookingModel.setDoctorid(booking.getDoctorid());
		bookingModel.setStatusId(booking.getStatusId());
		bookingModel.setDate(booking.getDate());
		bookingModel.setToken(token.toString());
		bookingModel.setTimetype(booking.getTimetype());
		bookingModel.setCreateat(booking.getCreateat());
		bookingModel.setUpdateat(booking.getUpdateat());
		
		return bookingModel;
	}
	//get all doctor infor
	@PostMapping("/api/sendEmail")
//	@EventListener(ApplicationReadyEvent.class)
	public void sendMail(@Valid @RequestBody EmailDataModel emailDataModel) throws SQLException {

		EmailDTO email = new EmailDTO();
//		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
		Booking booking = bookingDAO.getBookingByDoctorIdandPatienId(emailDataModel.getDoctorid(),emailDataModel.getPatientid(),emailDataModel.getDate(),emailDataModel.getTimetype());
		BookingModel bookingModel = defineModelToken(booking,corrId);
		Booking bookingResultBooking = bookingService.editBooking(bookingModel, emailDataModel.getDoctorid());
		System.out.println("Gia tri cua booking sau khi uodate "+bookingResultBooking);
		
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
				String direct_url = String.format("http://api-truongcongtoan.herokuapp.com/api/verify-booking/%s/%d",corrId,emailDataModel.getDoctorid());
				// List of team members...
				List<String> booking_info = Arrays.asList(full_name,gender,birth_year,email_address,phone_number,reason,ngaykham,price,doctor_name);
				
				templateData.put("booking_info", booking_info);
				templateData.put("direct_url", direct_url);
				email.setEmailData(templateData);
				
				emailService.sendWelcomeEmail(email);
				
	}
}
