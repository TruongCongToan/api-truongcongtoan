package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
@Transactional
public interface IUserDAO extends JpaRepository<Users, Integer> {
	// get all users
	@Query(nativeQuery = true, value = "select * from `users`")
	public List<Users> getAllUsers();
	
	// get thong tin sv by masv
	@Query(value = "select * from `users` where email = :inname", nativeQuery = true)
	public Users findByName(@Param("inname") String inname);
	
	@Query(value = "select * from `users` where uuid = :inuuid", nativeQuery = true)
	public Users findByUUID(@Param("inuuid") String inuuid);
	
	// get thong tin sv by masv
	@Query(value = "select * from `users` where user_id = :inid", nativeQuery = true)
	public Users findbyId(@Param("inid") int inid);
	
	@Query(value = "select statusId from `users` where user_id = :inid", nativeQuery = true)
	public String getOTP(@Param("inid") int inid);
	// xoa sinh vien theo masv
	@Modifying
	@Query(value = "delete from `users` where user_id =:inid", nativeQuery = true)
	public int deleteUser(@Param("inid") int inid);
	
	
	//get all doctors
	@Query(nativeQuery = true, value = "SELECT * FROM `users` WHERE role='R2'")
	public List<Users> getAllDoctors();
	
	//get all doctors by email
	@Query(nativeQuery = true, value = "SELECT * FROM `users` WHERE role='R2' and email = :inname")
	public Users getDoctorByName(@Param("inname") String inname);

	
	@Query(nativeQuery = true, value = "SELECT * FROM `users` WHERE email = :email")
	public Users findByEmail(@Param("email") String email);
	
	public Users findByResetPasswordToken(String token);
	
	@Query(value = "select * from  `users` where reset_password_token = :tokenin and email = :email", nativeQuery = true)
	public Users verifyResetPassword(@Param("tokenin") String tokenin, @Param("email") String email);
	

}