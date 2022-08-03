package com.example.demo.controller;

import java.sql.Array;
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

import com.example.demo.DAO.IBookingDAO;
import com.example.demo.entity.Booking;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.BookingModel;
import com.example.demo.service.impl.BookingService;
import java.sql.Timestamp;    
import java.util.Date;   
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
	@Autowired
	BookingService bookingService;
	@Autowired
	IBookingDAO bookingDAO;

	//get all booking
	@GetMapping("/api/bookings")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllUsers() {
		HttpStatus httpStatus = null;
		List<Booking> bookingList = new ArrayList<> ();
		
		try {
			bookingList = bookingService.getListBooking();
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
	}
		return new ResponseEntity<Object>(bookingList, httpStatus);
	}


	@GetMapping("/api/bookings/count")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Integer> countBooking() {
		List<Booking> bookingList = new ArrayList<> ();
		List<Booking> bookingList1 = new ArrayList<> ();
		List<Integer> countList =  new ArrayList<> ();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());       

        
//        1661878800000
//        1659512500136
//        1659546000000
        System.out.println(timestamp.getTime());
		if((timestamp.getTime()) < 1661878800000L) {
			System.out.println("Yes");
				bookingList = bookingDAO.getAllBookingDaKham(timestamp.getTime(),1661878800000L);
				countList.add(bookingList.size());
			}else {
				bookingList = bookingDAO.getAllBookingDaKham(timestamp.getTime(),1661878800000L);
				bookingList1 = bookingDAO.getAllBookingDaKham(timestamp.getTime(),1664470800000L);
				countList.add(bookingList.size());
				countList.add(bookingList1.size());
				System.out.println("NO");
				System.out.println(countList);
			}
		
//		countList.add(1);
//		countList.add(2);
//		System.out.println(countList);
			return countList;
			
		
	}
	
	@GetMapping("/api/bookings/count/{doctor_id}/{date}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> countBookingByDate(@Valid @PathVariable("doctor_id") int doctor_id, @PathVariable("date") String date) {
		HttpStatus httpStatus = null;
		List<Booking> bookingList = new ArrayList<> ();
		
		try {
			bookingList = bookingDAO.getBookingByDoctorID(doctor_id,date);
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
	}
		return new ResponseEntity<Object>(bookingList.size(),httpStatus);
	}
	
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
	@GetMapping("/api/bookings/admin/{indate}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getBookingByAmind(@Valid @PathVariable("indate") String indate) {
		HttpStatus httpStatus = null;
		List<Booking> bookingList = new ArrayList<> ();
		bookingList = bookingDAO.getBookingByAdmin(indate);
		try {
			bookingList = bookingDAO.getBookingByAdmin(indate);
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
	}
		return new ResponseEntity<Object>(bookingList, httpStatus);
	}
	
	@GetMapping("/api/bookings/{intimetype}/{indate}/{doctorid}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getBookingByTDDoctor(@Valid 
			@PathVariable("intimetype") String intimetype,
			@PathVariable("indate") String indate,
			@PathVariable("doctorid") int doctorid
			) {
		HttpStatus httpStatus = null;
		Booking booking = new Booking();
		try {
			booking = bookingDAO.getBookingByDoctorIAdd(doctorid,indate,intimetype);
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
	}
		return new ResponseEntity<Object>(booking, httpStatus);
	}

			@GetMapping("/api/bookings/patient/{patient_id}/{date}")
			@CrossOrigin(origins = "http://localhost:3000")
			public ResponseEntity<Object> getBookingByPatientID(@PathVariable("patient_id") int patient_id,@PathVariable("date") String date) {
				HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				List<Booking> booking = new ArrayList<>();
				booking = bookingDAO.getBookingByPatientID(patient_id,date);
				try {
					httpStatus = HttpStatus.OK;
				} catch (Exception e) {
					 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
				}
				return new ResponseEntity<Object>(booking, httpStatus);
			}
			
			@GetMapping("/api/bookings/doctor/{patient_id}/{date}")
			@CrossOrigin(origins = "http://localhost:3000")
			public ResponseEntity<Object> getBookingByDoctorID(@PathVariable("patient_id") int patient_id,@PathVariable("date") String date) {
				HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				List<Booking> booking = new ArrayList<>();
				booking = bookingDAO.getBookingByDoctorID(patient_id, date);
				try {
					httpStatus = HttpStatus.OK;
				} catch (Exception e) {
					 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
				}
				return new ResponseEntity<Object>(booking, httpStatus);
			}

	

}
