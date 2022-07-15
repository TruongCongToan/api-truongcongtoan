package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IDoctorInfoDAO;
import com.example.demo.entity.DoctorInfo;
import com.example.demo.exception.DuplicateRecordException;
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
		if (doctorInfo == null) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			return doctorInfo;
		}
		
	}
	//add doctor info
	@Override
	public DoctorInfo postInforDoctor(DoctorInfoModel doctorInfoModel) throws SQLException {
	
		if(doctorInforDAO.findByDoctorID(doctorInfoModel.getDoctorid()) != null) {
			 throw new DuplicateRecordException("Da co user nay trong danh sach");

		}else {

			DoctorInfo doctorInfo = new DoctorInfo();
			
//			doctorInfo.setDoctorid(doctorInfoModel.getDoctorid());
			doctorInfo.setProvinceid(doctorInfoModel.getProvinceid());
			doctorInfo.setPriceid(doctorInfoModel.getPriceid());
			doctorInfo.setAddressclinicid(doctorInfoModel.getAddressclinicid());
			doctorInfo.setNameclinic(doctorInfoModel.getNameclinic());
			doctorInfo.setNote(doctorInfoModel.getNote());
			doctorInfo.setCount(doctorInfoModel.getCount());
			doctorInfo.setPayment(doctorInfoModel.getPayment());
			doctorInfo.setClinic_id(doctorInfoModel.getClinic_id());
//			doctorInfo.setSpecialty_id(doctorInfoModel.getSpecialty_id());
			
			doctorInfo.setCreateat(new Date());
						
			doctorInforDAO.save(doctorInfo);
			return doctorInforDAO.save(doctorInfo);
		}
		
	}

	//edit doctor info
	@Override
	public DoctorInfo editDoctorInfo(DoctorInfoModel doctorInfoModel, int doctorID) throws SQLException {
		
		if (doctorInforDAO.findByDoctorID(doctorID) != null) {
			DoctorInfo doctorInfo = getInforByDoctorID(doctorID);
			
			doctorInfo.setProvinceid(doctorInfoModel.getProvinceid());
			doctorInfo.setPriceid(doctorInfoModel.getPriceid());
			doctorInfo.setAddressclinicid(doctorInfoModel.getAddressclinicid());
			doctorInfo.setNameclinic(doctorInfoModel.getNameclinic());
			doctorInfo.setNote(doctorInfoModel.getNote());
			doctorInfo.setCount(doctorInfoModel.getCount());
			doctorInfo.setPayment(doctorInfoModel.getPayment());		
			doctorInfo.setClinic_id(doctorInfoModel.getClinic_id());
//			doctorInfo.setSpecialty_id(doctorInfoModel.getSpecialty_id());
			
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
	@Override
	public List<DoctorInfo>  getInforByClinicID(int clinicID) throws SQLException {
		List<DoctorInfo>  doctorInfo = doctorInforDAO.findByClinicID(clinicID);
		if (doctorInfo.isEmpty()) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			return doctorInfo;
		}
	}
	
//	public  DoctorSpecialty DoctorInfoSpecialty (DoctorInfo doctorInfo) throws SQLException{
//		DoctorSpecialty doctorSpecialty = new  DoctorSpecialty ();
//		doctorSpecialty.setDoctorid(doctorInfo.getDoctorid());
//		doctorSpecialty.setProvinceid(doctorInfo.getProvinceid());
//		return doctorSpecialty;
//	}
	
	@Override
	public List<DoctorInfo>  getInforBySpecialID(int specialID,String inprovinceid) throws SQLException {
		if (specialID == 0 || inprovinceid == null) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			if (inprovinceid.equals("ALL")) {
				List<DoctorInfo>  doctorInfo = doctorInforDAO.findBySpecialID(specialID);
				if (doctorInfo.isEmpty()) {
					throw new NotFoundException("Khong tim thay nguoi dung nay");
				}else {
					return doctorInfo;
				}
			}else {
				List<DoctorInfo>  doctorInfo = doctorInforDAO.findBySpecialIDLocation(specialID,inprovinceid);
				if (doctorInfo.isEmpty()) {
					throw new NotFoundException("Khong tim thay nguoi dung nay");
				}else {
					return doctorInfo;
				}
			}
		}
	}
	

}