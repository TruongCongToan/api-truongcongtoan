package com.example.demo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.IBookingDAO;
import com.example.demo.DAO.IEmailDAO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.EmailData;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
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
	@Autowired
	private IEmailDAO emailDAO;
	
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
		email.setSubject("BKHcare xin thông báo đặt lịch khám thành công");
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
				String price = String.format("Giá khám : %s", emailDataModel.getPrice());
				String doctor_name = String.format("Bác sĩ khám : Bác sĩ chuyên khoa %s",emailDataModel.getDoctor_name());
				String direct_url = String.format("http://api-truongcongtoan.herokuapp.com/api/verify-booking/%s/%d",corrId,emailDataModel.getDoctorid());
				// List of team members...
				List<String> booking_info = Arrays.asList(full_name,gender,birth_year,email_address,phone_number,reason,ngaykham,price,doctor_name);
				
				templateData.put("booking_info", booking_info);
				templateData.put("direct_url", direct_url);
				email.setEmailData(templateData);
				
				emailService.sendWelcomeEmail(email);
				
	}
	
	@GetMapping("/api/mail/{patientid}")
	public ResponseEntity<Object> getAllEMailData(@Valid @PathVariable("patientid") int patientid) {
		HttpStatus httpStatus = null;
		List<EmailData> emailDatas = new ArrayList<EmailData>();
		emailDatas = emailDAO.getByPatientID(patientid);
		
		try {
			
			httpStatus = HttpStatus.OK;
//			emailDatas = emailDAO.getByPatientID(patientid);
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(emailDatas, httpStatus);
	}
	
		@GetMapping("/api/mail/all")
		public ResponseEntity<Object> getAllEMailData() {
			HttpStatus httpStatus = null;
			List<EmailData> emailDatas = new ArrayList<EmailData>();
			emailDatas = emailDAO.getAll();
			
			try {
				
				httpStatus = HttpStatus.OK;
				
			} catch (Exception e) {
				 throw new InternalServerException("Không được bỏ trống các trường !");
			}
			return new ResponseEntity<Object>(emailDatas, httpStatus);
	}
	@GetMapping("/api/mail/doctorID/{doctorid}")
	public ResponseEntity<Object> getAllEMailDataByDoctorid(@Valid @PathVariable("doctorid") int doctorid) {
		HttpStatus httpStatus = null;
		List<EmailData> emailDatas = new ArrayList<EmailData>();
		emailDatas = emailDAO.getByDoctorID(doctorid);
		
		try {
			
			httpStatus = HttpStatus.OK;
//			emailDatas = emailDAO.getByPatientID(patientid);
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(emailDatas, httpStatus);
	}
	
	@PostMapping("/api/emaildata/")
	public ResponseEntity<Object> addSpeciatlties(@Valid @RequestBody EmailDataModel emailDataModel){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		EmailData emailData = new EmailData();
		
		try {
			emailData = emailService.addEmailData(emailDataModel);
			httpStatus = HttpStatus.CREATED;
		} catch (Exception e) {
			 throw new DuplicateRecordException("Da co DotorID nay trong danh sach");		 
		}
		return new ResponseEntity<Object>(emailData, httpStatus);
	}
	@PutMapping("api/emaildata/{patientid}/{date}/{doctorid}/{timetype}/{statusid}")
	public ResponseEntity<Object> editClinic(@Valid 
		@PathVariable("patientid") int patientid,
		@PathVariable("date") String date,
		@PathVariable("doctorid") int doctorid,
		@PathVariable("timetype") String timetype,
		@PathVariable("statusid") String statusid
		) {
		HttpStatus httpStatus = null;
		Booking booking = new Booking();
		try {
			booking =	emailService.editClinic(timetype, patientid,date,doctorid,statusid);
			httpStatus = HttpStatus.CREATED;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(booking,httpStatus);
	}
	
}
