package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmailData;

@Repository
@Transactional
public interface IEmailDAO extends JpaRepository<EmailData, Integer>{
	
	@Query(value = "select * from `emaildata` where patientid = :inid", nativeQuery = true)
	public List<EmailData> getByPatientID(@Param("inid") int inid);
	
	@Query(value = "select * from `emaildata` where patientid = :inid and date =:indate and doctorid =:indoctorid and timetype = :intimetype", nativeQuery = true)
	public EmailData getByDoctorID(@Param("inid") int inid,@Param("indate") String indate,@Param("indoctorid") int indoctorid, @Param("intimetype") String intimetype);
}
