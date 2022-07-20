package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Specialties;

@Repository
@Transactional
public interface ISpecialtiesDAO extends JpaRepository<Specialties, Integer> {
	@Query(nativeQuery = true, value = "select * from `specialties`")
	public List<Specialties> getAllSpecialties();
	
	@Query(value = "select * from `specialties` where id = :inid", nativeQuery = true)
	public Specialties getSpecialtiesByID(@Param("inid") int inid);
	
	@Query(value = "select * from `specialties` where clinic_id = :inid", nativeQuery = true)
	public List<Specialties> getSpecialtiesByCLinicID(@Param("inid") int inid);
	
	@Query(value = "select * from `specialties` where name = :inname", nativeQuery = true)
	public Specialties getSpecialtiesName(@Param("inname") String inname);
	
	@Modifying
	@Query(value = "delete from `specialties` where id =:inid", nativeQuery = true)
	public void deleteSpecialties(@Param("inid") int inid);
}
