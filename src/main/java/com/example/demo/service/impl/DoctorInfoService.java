package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IClinicDAO;
import com.example.demo.DAO.IDoctorInfoDAO;
import com.example.demo.DAO.IMarkDownDAO;
import com.example.demo.DAO.ISpecialtiesDAO;
import com.example.demo.DAO.IUserDAO;
import com.example.demo.entity.DoctorInfo;
import com.example.demo.entity.MarkDown;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.DoctorInfoModel;
import com.example.demo.service.IDoctorInfoService;


@Service
public class DoctorInfoService implements IDoctorInfoService{

	@Autowired
	private IDoctorInfoDAO doctorInforDAO;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IMarkDownDAO markDownDAO;
	
	@Autowired
	private ISpecialtiesDAO specialtiesDAO;
	
	@Autowired
	private IClinicDAO clinicDAO;
	
	public List<DoctorInfo> getListDoctorInfo() throws SQLException {
		List<DoctorInfo> doctorInfo = doctorInforDAO.findAll();
		return doctorInfo;
	}
	@Override
	public DoctorInfo getInforByDoctorID(int doctorID) throws SQLException {
		DoctorInfo doctorInfo = doctorInforDAO.findByDoctorID(doctorID);
		if (doctorInfo == null) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			return doctorInfo;
		}
		
	}
	@Override
	public DoctorInfo postInforDoctor(DoctorInfoModel doctorInfoModel) throws SQLException {
	
		if(doctorInforDAO.findByDoctorID(doctorInfoModel.getDoctorid()) != null) {
			 throw new DuplicateRecordException("Da co user nay trong danh sach");

		}else {

			DoctorInfo doctorInfo = new DoctorInfo();
			
			doctorInfo.setUser(userDAO.findbyId(doctorInfoModel.getDoctorid()));
			doctorInfo.setProvinceid(doctorInfoModel.getProvinceid());
			doctorInfo.setPriceid(doctorInfoModel.getPriceid());
			doctorInfo.setAddressclinicid(doctorInfoModel.getAddressclinicid());
			doctorInfo.setNote(doctorInfoModel.getNote());
			doctorInfo.setCount(doctorInfoModel.getCount());
			doctorInfo.setPayment(doctorInfoModel.getPayment());
			doctorInfo.setClinic(clinicDAO.getClinicByID(doctorInfoModel.getClinic_id()));
			doctorInfo.setSpecialties(specialtiesDAO.getSpecialtiesByID(doctorInfoModel.getSpecialty_id()));
			
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

			if (doctorInfo.getProvinceid() == null) {
				doctorInfo.setProvinceid(doctorInfoModel.getProvinceid());
			}else {
				if (!doctorInfo.getProvinceid().equals(doctorInfoModel.getProvinceid())) {
					doctorInfo.setProvinceid(doctorInfoModel.getProvinceid());
	 			}
			}
			if (doctorInfo.getPriceid() == null) {
				doctorInfo.setPriceid(doctorInfoModel.getPriceid());
			}else {
				if (!doctorInfo.getPriceid().equals(doctorInfoModel.getPriceid())) {
					doctorInfo.setPriceid(doctorInfoModel.getPriceid());
	 			}
			}
			if (doctorInfo.getAddressclinicid() == null) {
				doctorInfo.setAddressclinicid(doctorInfoModel.getAddressclinicid());
			}else {
				if (!doctorInfo.getAddressclinicid().equals(doctorInfoModel.getAddressclinicid())) {
					doctorInfo.setAddressclinicid(doctorInfoModel.getAddressclinicid());
	 			}
			}
		
			if (doctorInfo.getNote() == null) {
				doctorInfo.setNote(doctorInfoModel.getNote());
			}else {
				if (!doctorInfo.getNote().equals(doctorInfoModel.getNote())) {
					doctorInfo.setNote(doctorInfoModel.getNote());
	 			}
			}

			if (doctorInfo.getCount() == 0) {
				doctorInfo.setCount(doctorInfoModel.getCount());
			}else {
				if (doctorInfo.getCount() != doctorInfoModel.getCount()) {
					doctorInfo.setCount(doctorInfoModel.getCount());
	 			}
			}
			if (doctorInfo.getPayment() == null) {
				doctorInfo.setPayment(doctorInfoModel.getPayment());		
			}else {
				if (!doctorInfo.getPayment().equals(doctorInfoModel.getPayment())) {
					doctorInfo.setPayment(doctorInfoModel.getPayment());		
	 			}
			}
			
			if (doctorInfo.getClinic() == null) {
				doctorInfo.setClinic(clinicDAO.getClinicByID(doctorInfoModel.getClinic_id()));		
			}else {
				if (!doctorInfo.getClinic().equals(clinicDAO.getClinicByID(doctorInfoModel.getClinic_id()))) {
					doctorInfo.setClinic(clinicDAO.getClinicByID(doctorInfoModel.getClinic_id()));		
	 			}
			}
			if (doctorInfo.getSpecialties() == null) {
				doctorInfo.setSpecialties(specialtiesDAO.getSpecialtiesByID(doctorInfoModel.getSpecialty_id()));		
			}else {
				if (!doctorInfo.getSpecialties().equals(specialtiesDAO.getSpecialtiesByID(doctorInfoModel.getSpecialty_id()))) {
					doctorInfo.setSpecialties(specialtiesDAO.getSpecialtiesByID(doctorInfoModel.getSpecialty_id()));		
	 			}
			}

			doctorInfo.setUpdateat(new Date());
			
			return doctorInforDAO.saveAndFlush(doctorInfo);
			
		}else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
		
	}

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
	
	@Override
	public List<MarkDown>  getInforBySpecialID(int specialID,String inprovinceid) throws SQLException {
		if (specialID == 0 || inprovinceid == null) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			if (inprovinceid.equals("ALL")) {
				List<DoctorInfo>  doctorInfo = doctorInforDAO.findBySpecialID(specialID);
				if (doctorInfo.isEmpty()) {
					throw new NotFoundException("Khong tim thay nguoi dung nay");
				}else {
					List<MarkDown> markDowns = new ArrayList<MarkDown>();

					for(DoctorInfo each : doctorInfo) {
						MarkDown markDown = markDownDAO.findByDoctorInfoID(each.getId());	
						markDowns.add(markDown);
						}
					return markDowns;			
					}
			}else {
				List<DoctorInfo>  doctorInfo = doctorInforDAO.findBySpecialIDLocation(specialID,inprovinceid);
				if (doctorInfo.isEmpty()) {
					throw new NotFoundException("Khong tim thay nguoi dung nay");
				}else {
					List<MarkDown> markDowns = new ArrayList<MarkDown>();

					for(DoctorInfo each : doctorInfo) {
						MarkDown markDown = markDownDAO.findByDoctorInfoID(each.getId());	
						markDowns.add(markDown);
						}
					return markDowns;
				}
			}
		}
	}
	@Override
	public List<MarkDown> getInforByClinicID(int clinicID, int specialID) throws SQLException {
		if (specialID == 0 || clinicID == 0 ) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			List<MarkDown> markDowns = new ArrayList<MarkDown>();
			List<DoctorInfo>  doctorInfo = doctorInforDAO.findByClinicIDSpecialID(clinicID,specialID);
			if (doctorInfo.isEmpty()) {
				throw new NotFoundException("Khong tim thay nguoi dung nay");
			}else {
				for(DoctorInfo  each : doctorInfo) {
					MarkDown markDown = markDownDAO.findByDoctorInfoID(each.getId());	
					markDowns.add(markDown);
					}
				return markDowns;
			}
		}
	}
	@Override
	public List<MarkDown> findByClinicSpecialByLocation(int clinicID, int specialID, String location)
			throws SQLException {
		if (specialID == 0 || clinicID == 0 || location == null ) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			List<MarkDown> markDowns = new ArrayList<MarkDown>();
			List<DoctorInfo>  doctorInfo = doctorInforDAO.findByClinicSpecialByLocation(clinicID,specialID,location);
			if (doctorInfo.isEmpty()) {
				throw new NotFoundException("Khong tim thay nguoi dung nay");
			}else {
				for(DoctorInfo  each : doctorInfo) {
					MarkDown markDown = markDownDAO.findByDoctorInfoID(each.getId());	
					markDowns.add(markDown);
					}
				return markDowns;
			}
		}
	}
	

}