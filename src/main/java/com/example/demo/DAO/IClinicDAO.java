package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Clinic;

@Repository
@Transactional
public interface IClinicDAO extends JpaRepository<Clinic, Integer> {
	@Query(nativeQuery = true, value = "select * from `clinics`")
	public List<Clinic> getAllClinic();
	
	@Query(value = "select * from `clinics` where id = :inid", nativeQuery = true)
	public Clinic getClinicByID(@Param("inid") int inid);
	
	@Query(value = "select * from `clinics` where name = :inname", nativeQuery = true)
	public Clinic getClinicName(@Param("inname") String inname);
	
	@Modifying
	@Query(value = "delete from `clinics` where id =:inid", nativeQuery = true)
	public void deleteClinic(@Param("inid") int inid);
}
