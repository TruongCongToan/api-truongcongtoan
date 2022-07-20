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
	
	@Query(nativeQuery = true, value = "SELECT * FROM `doctorinfo`")
	public List<DoctorInfo> getAllDoctorInfor();

	@Query(value = "select * from doctorinfo where doctorid = :inid", nativeQuery = true)
	public  DoctorInfo findByDoctorID(@Param("inid") int inid);
	
	@Query(value = "select * from doctorinfo where specialty_id = :inid and provinceid = :inprovinceid", nativeQuery = true)
	public  List<DoctorInfo>  findBySpecialIDLocation(@Param("inid") int inid , @Param("inprovinceid") String inprovinceid);
	
	@Query(value = "select * from doctorinfo where specialty_id = :inid", nativeQuery = true)
	public  List<DoctorInfo>  findBySpecialID(@Param("inid") int inid);
	
	@Query(value = "select * from doctorinfo where  clinic_id = :incid and specialty_id = :insid", nativeQuery = true)
	public  List<DoctorInfo>  findByClinicIDSpecialID(@Param("incid") int incid,@Param("insid") int insid);
	
	
	@Query(value = "select * from doctorinfo where clinic_id = :inid", nativeQuery = true)
	public  List<DoctorInfo>  findByClinicID(@Param("inid") int inid);
	

}