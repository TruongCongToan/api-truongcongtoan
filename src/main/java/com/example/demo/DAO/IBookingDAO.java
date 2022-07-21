package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Booking;

@Repository
@Transactional
public interface IBookingDAO extends JpaRepository<Booking, Integer> {
	// get all Booking
		@Query(nativeQuery = true, value = "select * from  `booking`")
		public List<Booking> getAllBooking();
		
		// get thong tin sv by patienid
		@Query(value = "select * from  `booking` where doctorid = :inid and date = :indate", nativeQuery = true)
		public List<Booking> getBookingByDoctorID(@Param("inid") int inid,@Param("indate") String indate);
		
		@Query(value = "select * from  `booking` where date = :indate", nativeQuery = true)
		public List<Booking> getBookingByAdmin(@Param("indate") String indate);
		
		@Query(value = "select * from  `booking` where patientid = :inid and date = :indate", nativeQuery = true)
		public List<Booking> getBookingByPatientID(@Param("inid") int inid,@Param("indate") String indate);
		
		@Query(value = "select * from  `booking` where doctorid = :inid and patientid =:inpatientid and date = :indate and timetype = :intime" , nativeQuery = true)
		public Booking getBookingByDoctorIdandPatienId(@Param("inid") int inid,@Param("inpatientid") int inpatientid,@Param("indate") String indate,@Param("intime") String intime);
		// verify
		@Query(value = "select * from  `booking` where token = :tokenin and doctorid = :indoctorid and statusId='S1'", nativeQuery = true)
		public Booking verifyBooking(@Param("tokenin") String tokenin, @Param("indoctorid") int indoctorid);
		
		// xoa sinh vien theo patienid
		@Modifying
		@Query(value = "delete from  `booking` where patientid =:inid", nativeQuery = true)
		public void deleteBooking(@Param("inid") int inid);


}
