package com.example.demo.service.impl;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IUserDAO;
import com.example.demo.DAO.PagingDAO;
import com.example.demo.entity.Users;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserPrincipal;
import com.example.demo.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private PagingDAO pagingDAO;
		
	@Override
	public List<Users> getListUser() throws SQLException {
		List<Users> users = userDAO.getAllUsers();
		if (users.isEmpty()) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			return users;
		}
	}
	
	private List<Users> getListUsersOut(List<Users> userIn) {
		List<Users> users2 = new ArrayList<>();

		Users usersOut = new Users();
		for(Users users : userIn) {
			usersOut.setUser_id(users.getUser_id());
			usersOut.setEmail(users.getEmail());
			usersOut.setAddress(users.getAddress());
			usersOut.setGender(users.getGender());
			usersOut.setRole(users.getRole());
			usersOut.setFull_name(users.getFull_name());
			usersOut.setImage(users.getImage());
			usersOut.setPosition(users.getPosition());
			usersOut.setCreated_at(users.getCreated_at());
			usersOut.setUpdated_at(users.getUpdated_at());
			usersOut.setAllCodeRole(users.getAllCodeRole());
			usersOut.setAllCodePosition(users.getAllCodePosition());
			
			users2.add(usersOut);
			}
		
		System.out.println("Gia tri thu duoc "+users2);
		return users2;
	}

	public Users defineToUser(Users users) throws SQLException {
		Users usersOut = new Users();	
		usersOut.setUser_id(users.getUser_id());
		usersOut.setEmail(users.getEmail());
		usersOut.setAddress(users.getAddress());
		usersOut.setGender(users.getGender());
		usersOut.setRole(users.getRole());
		usersOut.setFull_name(users.getFull_name());
		usersOut.setImage(users.getImage());
		usersOut.setPosition(users.getPosition());
		usersOut.setCreated_at(users.getCreated_at());
		usersOut.setUpdated_at(users.getUpdated_at());
		usersOut.setAllCodeRole(users.getAllCodeRole());
		usersOut.setAllCodePosition(users.getAllCodePosition());
		return usersOut;
	}
	
	@Override
	public Users addUser(UserModel userModel) throws SQLException {
		if (userDAO.findByName(userModel.getEmail()) == null) {
			Users user = new Users();
			
			user.setPassword(userModel.getPassword());
			user.setEmail(userModel.getEmail());
			
			user.setAddress(userModel.getAddress());
			user.setPhone_number(userModel.getPhone_number());
			user.setGender(userModel.getGender());
			user.setRole(userModel.getRole());
			user.setFull_name(userModel.getFull_name());
			user.setImage(userModel.getImage());
			user.setCreated_at(new Date());
			
//			 userDAO.save(user);
			 return userDAO.save(user);
		}
		else {
			 throw new DuplicateRecordException("Da co user nay trong danh sach");
		}
	}
	// get user by username
	@Override
	public Users getUserByName(String inname) throws SQLException {
		if (inname != null) {
			Users user = userDAO.findByName(inname);
			if (user != null) {
				return user;
			}else {
				   throw new NotFoundException("Khong tim thay nguoi dung nay");
			}
		} else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
	}

	@Override
	public void deleteUser(int inname) throws SQLException {
		if (userDAO.findbyId(inname) != null) {
			userDAO.deleteUser(inname);
		}
		else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}		
	}
 //update user
	@Override
	public Users  editUser(UserModel userModel, int inid) throws SQLException {
//		System.out.println("gia tri dau vao "+userModel);
		if (userDAO.findbyId(inid) != null) {
			Users user = userDAO.findbyId(inid);
 			
 			if (user.getAddress() == null) {
				user.setAddress(userModel.getAddress());
			}else {
				if (!user.getAddress().equals(userModel.getAddress())) {
	 				user.setAddress(userModel.getAddress());
	 			}
			}
 			if (user.getPhone_number() == null) {
				user.setPhone_number(userModel.getPhone_number());
			}else {
				if (!user.getPhone_number().equals(userModel.getPhone_number())) {
					user.setPhone_number(userModel.getPhone_number());
	 			}
			}
 			if (user.getGender() == null) {
				user.setGender(userModel.getGender());
			}else {
	 			if (!user.getGender().equals(userModel.getGender()) ) {
	 				user.setGender(userModel.getGender());
	 			}
			}
 			if (user.getRole() == null) {
				user.setRole(userModel.getRole());
			}else {
	 			if(!user.getRole().equals(userModel.getRole())) {
	 				user.setRole(userModel.getRole());
	 			}
			}
 			
 			if (user.getImage() == null) {
				user.setImage(userModel.getImage());
			}else {
				if(!user.getImage().equals(userModel.getImage())) {
	 				user.setImage(userModel.getImage());
	 			}
			}
 			if (user.getFull_name() == null) {
				user.setFull_name(userModel.getFull_name());
			}else {
				if(!user.getFull_name().equals(userModel.getFull_name())) {
					user.setFull_name(userModel.getFull_name());
	 			}
			}

 			user.setUpdated_at(new Date());
 			
 		 return	 userDAO.saveAndFlush(user);
		}else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
		
	}
//pagination 
@Override
public List<Users> findPaginated(int pageNo, int pageSize) {
	Pageable paging = PageRequest.of(pageNo, pageSize);
	Page<Users> pageResult = pagingDAO.findByRole(paging);
	return pageResult.toList();
}
//get all doctors
@Override
public List<Users> getListDoctors() throws SQLException {
	if (userDAO.getAllDoctors() != null) {
		List<Users> doctorList = userDAO.getAllDoctors();
		return doctorList;
	}else
	return null;
}
//get user by username
@Override
public Users getUserByID(int intID) throws SQLException {
	if (intID != 0) {
		Users user = userDAO.findbyId(intID);		
		if (user != null) {
			return user;
		}else {
			   throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
	} else {
		throw new NotFoundException("Khong tim thay nguoi dung nay");
	}
}

@Override
public UserPrincipal findByUserEmail(String email) {
	 Users user = userDAO.findByName(email);
	 
     UserPrincipal userPrincipal = new UserPrincipal();
     if (null != user) {
       
         userPrincipal.setUserId(user.getUser_id());
         userPrincipal.setUsername(user.getEmail());
         userPrincipal.setPassword(user.getPassword());
     }

     return userPrincipal;
}


}