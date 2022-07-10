package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AllCode;

@Repository
@Transactional
public interface IAllCodeDAO extends JpaRepository<AllCode, Integer>  {
	//get all Code\
	@Query(nativeQuery = true, value = "SELECT * FROM `allcodes` ORDER BY id;")
	public List<AllCode> getAllCodeService();


	@Modifying
	@Query(value = "SELECT * FROM `allcodes` WHERE type =:intype",nativeQuery = true)
	public List<AllCode> findByType(@Param("intype") String intype);



}
