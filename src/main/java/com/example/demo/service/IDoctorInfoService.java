package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DoctorInfo;
import com.example.demo.entity.MarkDown;
import com.example.demo.model.DoctorInfoModel;

@Service
public interface IDoctorInfoService {
	
		public List<DoctorInfo> getListDoctorInfo() throws SQLException;
		
		public DoctorInfo getInforByDoctorID(int doctorID) throws SQLException;
		
		public DoctorInfo postInforDoctor(DoctorInfoModel doctorInfoModel) throws SQLException;
		
		public DoctorInfo editDoctorInfo(DoctorInfoModel doctorInfoModel, int doctorID) throws SQLException;

		public List<DoctorInfo> getInforByClinicID(int clinicID) throws SQLException;
		
		public List<MarkDown>  getInforBySpecialID(int specialID, String location) throws SQLException;
		
		
		public List<MarkDown>  findByClinicSpecialByLocation(int clinicID, int specialID,String location) throws SQLException;

		public List<MarkDown>  getInforByClinicID(int clinicID, int specialID) throws SQLException;
		public void deleteDoctorInfo (int doctorID) throws SQLException;
	}