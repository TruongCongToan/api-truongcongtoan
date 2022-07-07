package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
	
	//to convert from booking to bookingModel
	private List<BookingModel> getListBookingModels(List<Booking> bookings) {
		List<BookingModel> bookingModelList = new ArrayList<>();
		for (Booking booking : bookings) {
			BookingModel bookingModel = new BookingModel();
			
			bookingModel.setStatusId(booking.getStatusId());
			bookingModel.setDoctorid(booking.getDoctorid());
			bookingModel.setPatientid(booking.getPatientid());
			bookingModel.setDate(booking.getDate());
			bookingModel.setTimetype(booking.getTimetype());
			bookingModel.setUpdateat(booking.getUpdateat());
			bookingModel.setCreateat(booking.getCreateat());
			bookingModel.setToken(booking.getToken());
			bookingModel.setId(booking.getId());
			
			
			bookingModelList.add(bookingModel);
		}
		return bookingModelList;
	}
	
	
	
	//get list booking
	@Override
	public List<BookingModel> getListBooking() throws SQLException {
		List<Booking> bookingList = bookingDAO.getAllBooking();
	List<BookingModel> bookingModels = getListBookingModels(bookingList);
		return bookingModels;
	}


	//add new  booking
	@Override
	public Booking addBooking(BookingModel bookingModel) throws SQLException {
		
		if(bookingDAO.getBookingByID(bookingModel.getPatientid()) != null) {
			
		return editBooking(bookingModel,bookingModel.getPatientid());
		
		}else {
			
			Booking booking = new Booking();
			
			booking.setCreateat(new Date());
			booking.setUpdateat(new Date());

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
	public Booking editBooking(BookingModel bookingModel, int patientID) throws SQLException {
		System.out.println(bookingDAO.getBookingByID(bookingModel.getDoctorid()) );
		if(bookingDAO.getBookingByID(bookingModel.getDoctorid()) != null ) {
			Booking booking = bookingDAO.getBookingByID(patientID);
		
//			if (!booking.getDate().equals(bookingModel.getDate()) ) {
//					booking.setDate(bookingModel.getDate());
//			}
//			if(booking.getDoctorid() != bookingModel.getDoctorid()) {
//			booking.setDoctorid(bookingModel.getDoctorid());
//		}
//			booking.setStatusId(bookingModel.getStatusId());
//			booking.setTimetype(bookingModel.getTimetype());
////			if(!booking.getTimetype().equals(bookingModel.getTimetype())) {
////				booking.setTimetype(bookingModel.getTimetype());
////			}
//			if(!booking.getToken().equals(bookingModel.getToken())) {
//				booking.setToken(bookingModel.getToken());
//			}
//
//			booking.setCreateat(bookingModel.getCreateat());
//			booking.setUpdateat(bookingModel.getUpdateat());
			
			return bookingDAO.saveAndFlush(booking);
		}else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}		
	}


	@Override
	public void deleteBooking(int inID) throws SQLException {
		if (getBookingByID(inID) != null) {
			bookingDAO.deleteBooking(inID);
		}
		else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
		
	}


	@Override
	public Booking getBookingByID(int intID) throws SQLException {
		if (intID != 0) {
			Booking booking = bookingDAO.getBookingByID(intID);
			if (booking != null) {
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
			System.out.println("gia tri "+booking);
			if (booking != null) {
			BookingModel bookingModel= defineModelToken(booking);
			Booking UpdateBooking = editBooking(bookingModel, bookingModel.getPatientid());
			return UpdateBooking;
			}else {
				throw new NotFoundException("Khong the nhan lan 2");
			}
		}
	}

}
