package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DoctorInfo;
import com.example.demo.model.DoctorInfoModel;

@Service
public interface IDoctorInfoService {
	//lay thong tin nguoi dung
		public List<DoctorInfo> getListDoctorInfo() throws SQLException;
	//tim theo ten
		public DoctorInfo getInforByDoctorID(int doctorID) throws SQLException;
	//them thong tin 
		public DoctorInfo postInforDoctor(DoctorInfoModel doctorInfoModel) throws SQLException;
	//edit doctor info
		public DoctorInfo editDoctorInfo(DoctorInfoModel doctorInfoModel, int doctorID) throws SQLException;

	//delete doctor info
		public void deleteDoctorInfo (int doctorID) throws SQLException;
	}