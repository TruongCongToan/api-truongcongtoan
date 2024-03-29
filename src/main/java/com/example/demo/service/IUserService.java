package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserPrincipal;

@Service
public interface IUserService {
	//lay thong tin nguoi dung
		public List<Users> getListUser() throws SQLException;
		//tim theo ten
		public Users getUserByName(String inname) throws SQLException;
		//get userByID
		public Users getUserByID(int intID) throws SQLException;

		//xoa user
		public void deleteUser(int inname) throws SQLException;
		//them moi user
		public Users addUser(UserModel userModel) throws SQLException;
		//update user
		public Users editUser(UserModel userModel, int inid) throws SQLException;
		//kiem tra nguoi dung nay da dang nhap hay chua
		
		//getall doctors
		public List<Users> getListDoctors() throws SQLException;

		//pagination doctors
		public List<Users> findPaginated(int pageNo,int pageSize);
		
		public Users VerifyResetPassword(String token,String email) throws SQLException;
		
		UserPrincipal findByUserEmail(String email);

		

}