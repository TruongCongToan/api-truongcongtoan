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
		public List<UserModel> getListUser() throws SQLException;
		//tim theo ten
		public Users getUserByName(String inname) throws SQLException;
		//get userByID
		public Users getUserByID(int intID) throws SQLException;

		//xoa user
		public void deleteUser(String inname) throws SQLException;
		//them moi user
		public Users addUser(UserModel userModel) throws SQLException;
		//update user
		public void editUser(UserModel userModel, String inname) throws SQLException;
		//kiem tra nguoi dung nay da dang nhap hay chua
		
		//getall doctors
		public List<Users> getListDoctors() throws SQLException;

		//pagination doctors
		public List<Users> findPaginated(int pageNo,int pageSize);
		
		UserPrincipal findByUserEmail(String email);

		

}