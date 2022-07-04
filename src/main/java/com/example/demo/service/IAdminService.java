package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AdminModel;
@Service
public interface IAdminService {
	//get all user 
	public List<AdminModel> getListAdmin() throws SQLException;
	//update admin 
	public void editAdmin(AdminModel adminModel, String inname) throws SQLException;
}
