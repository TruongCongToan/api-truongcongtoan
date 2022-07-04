package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Sinhvien;
import com.example.demo.model.SinhvienModel;

@Service
public interface IStudentService  {
	//lay het tat cac cac sinh vien
	public List<SinhvienModel> getListStudent() throws SQLException; 
	//tim theo masv
	public Sinhvien getSinhvienByMaSV(String inmasv) throws SQLException;
	//xoa sv
	public void deleteSinhVien(String masv) throws SQLException;
	//them moi sinh vien
	public Sinhvien addSinhvien(SinhvienModel sinhvienModel) throws SQLException;
	//update sv
	public void editSinhvien(SinhvienModel sinhvienModel, String masv) throws SQLException;
	//lay sinh vien nu by malop
	public List<SinhvienModel> getListStudentByMalop(String malop) throws SQLException;	
	
}
