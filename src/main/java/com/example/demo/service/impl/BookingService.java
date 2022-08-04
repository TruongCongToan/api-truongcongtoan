package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IBookingDAO;
import com.example.demo.entity.Booking;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.BookingModel;
import com.example.demo.service.IBookingService;

@Service
public class BookingService implements IBookingService {

	@Autowired
	private IBookingDAO bookingDAO;

		
	//get list booking
	@Override
	public List<Booking> getListBooking() throws SQLException {
		List<Booking> bookingList = bookingDAO.getAllBooking();
		return bookingList;
	}


	//add new  booking
	@Override
	public Booking addBooking(BookingModel bookingModel) throws SQLException {
		
		if(bookingDAO.getBookingByDoctorIAdd(bookingModel.getDoctorid(),bookingModel.getDate(),
				bookingModel.getTimetype()) != null) {
			
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		
		}else {
			
			Booking booking = new Booking();
			
			booking.setCreateat(new Date());

			booking.setDate(bookingModel.getDate());
			booking.setDoctorid(bookingModel.getDoctorid());
			booking.setPatientid(bookingModel.getPatientid());
			booking.setStatusId(bookingModel.getStatusId());
			booking.setTimetype(bookingModel.getTimetype());
			booking.setToken(bookingModel.getToken());
			
			return bookingDAO.save(booking);
		}
	}


	//update booking info
	@Override
	public Booking editBooking(BookingModel bookingModel, int doctorID) throws SQLException {
		Booking booking = bookingDAO.getBookingByDoctorIdandPatienId(doctorID,bookingModel.getPatientid(),bookingModel.getDate(),bookingModel.getTimetype());
		System.out.println(bookingModel.getToken()==null);
		
				if(booking != null ) {		
			if (!booking.getDate().equals(bookingModel.getDate()) ) {
					booking.setDate(bookingModel.getDate());
			}
			if(booking.getDoctorid() != bookingModel.getDoctorid()) {
			booking.setDoctorid(bookingModel.getDoctorid());
		}
			if (booking.getStatusId() != bookingModel.getStatusId()) {
				booking.setStatusId(bookingModel.getStatusId());
			}
			if(!booking.getTimetype().equals(bookingModel.getTimetype())) {
				booking.setTimetype(bookingModel.getTimetype());
			}

			if(bookingModel.getToken()==null) {
				booking.setToken(booking.getToken());
			}else {
				if(booking.getToken() == null) {
					booking.setToken(bookingModel.getToken());
				}else
				if(!booking.getToken().equals(bookingModel.getToken())) {
					booking.setToken(bookingModel.getToken());
				}
			}
			
			booking.setUpdateat(new Date());
			
			return bookingDAO.saveAndFlush(booking);
		}else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}		
	}


	@Override
	public void deleteBooking(int inID) throws SQLException {
//		if (getBookingByID(inID) != null) {
//			bookingDAO.deleteBooking(inID);
//		}
//		else {
//			throw new NotFoundException("Khong tim thay nguoi dung nay");
//		}
		
	}


	@Override
	public List<Booking> getBookingByDoctorID(int intID,String date) throws SQLException {
		if (intID != 0) {
			List<Booking> booking = bookingDAO.getBookingByDoctorID(intID,date);
			if (booking.isEmpty() == false) {
				return booking;
			}else {
				   throw new NotFoundException("Khong tim thay nguoi dung nay");
				   
			}
		} else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
	}



	private BookingModel defineModelToken(Booking booking) {
		
		BookingModel bookingModel = new BookingModel();
		bookingModel.setPatientid(booking.getPatientid());
		bookingModel.setDoctorid(booking.getDoctorid());
		bookingModel.setStatusId("S2");
		bookingModel.setDate(booking.getDate());
		bookingModel.setToken(booking.getToken());
		bookingModel.setTimetype(booking.getTimetype());
		bookingModel.setCreateat(booking.getCreateat());
		bookingModel.setUpdateat(booking.getUpdateat());
		
		return bookingModel;
	}
	
	@Override
	public Booking VerifyBooking(String token, int patientid) throws SQLException {
		
		if (token == null && patientid == 0) {
			throw new NotFoundException("khong the verify");
		}else {
			Booking booking = bookingDAO.verifyBooking(token,patientid);
		
			if (booking != null) {
			BookingModel bookingModel= defineModelToken(booking);
			System.out.println("gia tri "+booking);
				if (booking.getStatusId().equals("S1")) {
					Booking UpdateBooking = editBooking(bookingModel, bookingModel.getDoctorid());
					return UpdateBooking;
				}else {
					throw new NotFoundException("Khong the nhan lan 2");
				}
			}else {
				throw new NotFoundException("Khong the nhan lan 2");
			}
		}
	}

}
