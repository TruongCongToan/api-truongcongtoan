package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.model.BookingModel;
@Service
public interface IBookingService {
	//get list booking
	public  List<Booking> getListBooking() throws SQLException; 
	
	//add new booking
	public Booking addBooking(BookingModel bookingModel) throws SQLException;
	//update booking
	public Booking editBooking(BookingModel bookingModel, int patientID) throws SQLException;
	
	//delet booking 
	public void deleteBooking(int inID) throws SQLException;
	
	//get patientID
	public List<Booking> getBookingByDoctorID(int intID,String date) throws SQLException;
	
	public Booking VerifyBooking(String token,int patientid) throws SQLException;

}

