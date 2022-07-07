package com.example.demo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

import com.example.demo.entity.Booking;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.BookingModel;
import com.example.demo.service.impl.BookingService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
	@Autowired
	BookingService bookingService;
	

	//get all booking
	@GetMapping("/api/bookings")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllUsers() {
		HttpStatus httpStatus = null;
		List<BookingModel> bookingList = new ArrayList<BookingModel> ();
		
		try {
			bookingList = bookingService.getListBooking();
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
	}
		return new ResponseEntity<Object>(bookingList, httpStatus);
	}

	//add new booking

	@PostMapping("/api/bookings")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> addBooking(@Valid @RequestBody BookingModel bookingModelIn) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		BookingModel bookingModel = new BookingModel();
		
		try {
			Booking booking = bookingService.addBooking(bookingModelIn);
			if (booking != null) {
				httpStatus = HttpStatus.CREATED;
				bookingModel.setCreateat(booking.getCreateat());
				bookingModel.setDate(booking.getDate());
				bookingModel.setDoctorid(booking.getDoctorid());
				bookingModel.setPatientid(booking.getPatientid());
				bookingModel.setStatusId(booking.getStatusId());
				bookingModel.setTimetype(booking.getTimetype());
				bookingModel.setUpdateat(booking.getUpdateat());
				bookingModel.setId(booking.getId());
				}
		} catch (Exception e) {
			 throw new DuplicateRecordException("Da co trong danh sach");		 
		}
		return new ResponseEntity<Object>(bookingModel, httpStatus);

		}
	@GetMapping("/api/verify-booking/{token}/{patientid}")
	@CrossOrigin(origins = "http://localhost:3000")
	public String VerifyBooking(@Valid @PathVariable("token") String token,  @PathVariable("patientid") int patientid) {
		Booking booking = new Booking();
		
		try {
			 booking = bookingService.VerifyBooking(token,patientid);
			 if (booking != null) {
					return "Bạn đã xác nhận lịch khám tại Health Care thành công ! Xin cảm ơn và chúc bạn nhiều điều tốt đẹp.";
			}else {
				return "Thông báo : Xác  nhận lịch đặt khám thất bại !";
			}
		} catch (Exception e) {
			return "Thông báo : Xác  nhận lịch đặt khám thất bại! Xin vui lòng kiểm tra lại!";
//			 throw new DuplicateRecordException("Da co trong danh sach");		 
			}
		}
	@PutMapping("api/bookings/{patientID}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> editBooking(@Valid @RequestBody BookingModel bookingModel,
			@PathVariable("patientID") int patientID) throws SQLException {
			HttpStatus httpStatus = null;
			Booking booking = new Booking();
			try {
				booking = bookingService.editBooking(bookingModel, patientID);
				httpStatus = HttpStatus.OK;

			} catch (Exception e) {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				 throw new InternalServerException("Không được bỏ trống các trường !");

			}
			return new ResponseEntity<Object>(booking,httpStatus);
	}
	
	///tim user theo username
			@GetMapping("/api/bookings/id={patient_id}")
			@CrossOrigin(origins = "http://localhost:3000")
			public ResponseEntity<Object> getBookingByID(@PathVariable("patient_id") int patient_id) {
				HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				Booking booking = new Booking();
				try {
					booking = bookingService.getBookingByID(patient_id);
					httpStatus = HttpStatus.OK;
				} catch (Exception e) {
					 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
				}
				return new ResponseEntity<Object>(booking, httpStatus);
			}
	

}
