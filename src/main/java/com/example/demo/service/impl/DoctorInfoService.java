package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IDoctorInfoDAO;
import com.example.demo.entity.DoctorInfo;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.DoctorInfoModel;
import com.example.demo.service.IDoctorInfoService;


@Service
public class DoctorInfoService implements IDoctorInfoService{

	@Autowired
	private IDoctorInfoDAO doctorInforDAO;
	//get all infor doctor
	public List<DoctorInfo> getListDoctorInfo() throws SQLException {
		List<DoctorInfo> doctorInfo = doctorInforDAO.findAll();
		return doctorInfo;
	}
	//get by doctor id
	@Override
	public DoctorInfo getInforByDoctorID(int doctorID) throws SQLException {
		DoctorInfo doctorInfo = doctorInforDAO.findByDoctorID(doctorID);
		return doctorInfo;
	}
	//add doctor info
	@Override
	public DoctorInfo postInforDoctor(DoctorInfoModel doctorInfoModel) throws SQLException {
	
		if(getInforByDoctorID(doctorInfoModel.getDoctorid()) != null) {
			
			return editDoctorInfo(doctorInfoModel,doctorInfoModel.getDoctorid());
			
		}else {
			DoctorInfo doctorInfo = new DoctorInfo();
			doctorInfo.setDoctorid(doctorInfoModel.getDoctorid());
			doctorInfo.setProvinceid(doctorInfoModel.getProvinceid());
			doctorInfo.setPriceid(doctorInfoModel.getPriceid());
			doctorInfo.setAddressclinicid(doctorInfoModel.getAddressclinicid());
			doctorInfo.setNameclinic(doctorInfoModel.getNameclinic());
			doctorInfo.setNote(doctorInfoModel.getNote());
			doctorInfo.setCount(doctorInfoModel.getCount());
			doctorInfo.setPayment(doctorInfoModel.getPayment());
			doctorInfo.setCreateat(new Date());
			doctorInfo.setUpdateat(new Date());
			
			doctorInforDAO.save(doctorInfo);
			return doctorInforDAO.save(doctorInfo);
		}
		
		
	}

	//edit doctor info
	@Override
	public DoctorInfo editDoctorInfo(DoctorInfoModel doctorInfoModel, int doctorID) throws SQLException {
		
		if (getInforByDoctorID(doctorID) != null) {
			DoctorInfo doctorInfo = getInforByDoctorID(doctorID);
			
			doctorInfo.setProvinceid(doctorInfoModel.getProvinceid());
			doctorInfo.setPriceid(doctorInfoModel.getPriceid());
			doctorInfo.setAddressclinicid(doctorInfoModel.getAddressclinicid());
			doctorInfo.setNameclinic(doctorInfoModel.getNameclinic());
			doctorInfo.setNote(doctorInfoModel.getNote());
			doctorInfo.setCount(doctorInfoModel.getCount());
			doctorInfo.setPayment(doctorInfoModel.getPayment());			
			doctorInfo.setCreateat(new Date());
			doctorInfo.setUpdateat(new Date());
			

			return doctorInforDAO.saveAndFlush(doctorInfo);
			
		}else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
		
	}

	//delete doctor info
	@Override
	public void deleteDoctorInfo(int doctorID) throws SQLException {
		DoctorInfo doctorInfo = getInforByDoctorID(doctorID);

		if(doctorInfo != null) {
			doctorInforDAO.delete(doctorInfo);
		}
	}
	

}