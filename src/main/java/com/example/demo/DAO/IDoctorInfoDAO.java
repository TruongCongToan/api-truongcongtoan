package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DoctorInfo;

@Repository
@Transactional
public interface IDoctorInfoDAO extends JpaRepository<DoctorInfo, Integer> {
	// get all users
	@Query(nativeQuery = true, value = "SELECT * FROM r8kVqeUlJf.doctorinfo;")
	public List<DoctorInfo> getAllDoctorInfor();

	// get thong tin sv by masv
	@Query(value = "select * from r8kVqeUlJf.doctorinfo where doctorid = :inid", nativeQuery = true)
	public  DoctorInfo findByDoctorID(@Param("inid") int inid);
	
	

}