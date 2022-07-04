package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

@Repository
@Transactional
public interface IAdminDAO extends JpaRepository<Admin, Integer> {
	// get all users
	@Query(nativeQuery = true, value = "SELECT * FROM r8kVqeUlJf.admin;")
	public List<Admin> getAllAdmin();

	// get thong tin sv by name
	@Query(value = "SELECT * FROM admin where username = :inname", nativeQuery = true)
	public Admin findByName(@Param("inname") String inname);


}
