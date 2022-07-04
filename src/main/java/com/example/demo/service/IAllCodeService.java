package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AllCodeModel;

@Service
public interface IAllCodeService {
	//lay thong tin nguoi dung
	public List<AllCodeModel> getListAllCodeService() throws SQLException;
	//find by type of allcodeservices
	public List<AllCodeModel> getAllCodeByType(String type) throws SQLException;	
	

}
