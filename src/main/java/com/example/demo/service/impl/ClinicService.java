package com.example.demo.service.impl;

import java.sql.SQLException;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IClinicDAO;
import com.example.demo.DAO.ISpecialtiesDAO;
import com.example.demo.entity.Clinic;
import com.example.demo.entity.Specialties;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.ClinicModel;
import com.example.demo.service.IClinicService;

@Service
public class ClinicService implements IClinicService{

	@Autowired
	IClinicDAO ClinicDAO;
	@Autowired
	ISpecialtiesDAO specialtiesDAO;

	@Override
	public List<Clinic> getAllClinic() throws SQLException {
		List<Clinic> Clinic = ClinicDAO.getAllClinic();
//		List<ClinicModel> ClinicModels = getListClinicModel(Clinic);
		return Clinic;
	}

	@Override
	public Clinic getClinicbyID(int intID) throws SQLException {
		if (intID != 0) {
			Clinic Clinic = ClinicDAO.getClinicByID(intID);
			if (Clinic != null) {
				return Clinic;
			}else {
				   throw new NotFoundException("Khong tim thay nguoi dung nay");
			}
		} else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
	}

	@Override
	public void deleteClinic(int id) throws SQLException {
		if (ClinicDAO.getClinicByID(id) != null) {
			ClinicDAO.deleteClinic(id);
		}
		else {
			throw new NotFoundException("not found");
		}		
	}

	@Override
	public Clinic addClinic(ClinicModel ClinicModel,String[] listSpecialtyID) throws SQLException {

//		List<Integer> specialtyList = Arrays.asList(listSpecialtyID.split(","));

		if (ClinicDAO.getClinicName(ClinicModel.getName()) == null
	
				) {
			
			Clinic Clinic = new Clinic();
			
			Clinic.setName(ClinicModel.getName());
			Clinic.setImage(ClinicModel.getImage());
			Clinic.setContentMarkDown(ClinicModel.getContentMarkDown());
			Clinic.setThemanhchuyenkhoa(ClinicModel.getThemanhchuyenkhoa());
			Clinic.setAddress(ClinicModel.getAddress());
			Clinic.setCreated_at(new Date());
						
			Set<Specialties> specialtiesList = new HashSet<>();
			for(String specialty_id : listSpecialtyID) {
				Specialties specialties = specialtiesDAO.getSpecialtiesByID(Integer.parseInt(specialty_id));
				specialtiesList.add(specialties);
			}
			Clinic.setLikedSpecialties(specialtiesList);
			return ClinicDAO.save(Clinic);
		}
		else {
			 throw new DuplicateRecordException("Da co user nay trong danh sach");
		}
			
	}

	@Override
	public void editClinic(ClinicModel ClinicModel, int id) throws SQLException {
		if (ClinicDAO.getClinicByID(id) != null) {
			Clinic Clinic = ClinicDAO.getClinicByID(id);
			
			if (!Clinic.getName().equals(ClinicModel.getName())) {
 				Clinic.setName(ClinicModel.getName());
 			}
			
			if (!Clinic.getImage().equals(ClinicModel.getImage())) {
				Clinic.setImage(ClinicModel.getImage());
 			}
			
			if (!Clinic.getContentMarkDown().equals(ClinicModel.getContentMarkDown())) {
 				Clinic.setName(ClinicModel.getName());
 			}
			if (!Clinic.getThemanhchuyenkhoa().equals(ClinicModel.getThemanhchuyenkhoa())) {
 				Clinic.setThemanhchuyenkhoa(ClinicModel.getThemanhchuyenkhoa());
 			}
			if (!Clinic.getAddress().equals(ClinicModel.getAddress())) {
 				Clinic.setAddress(ClinicModel.getAddress());
 			}
			Clinic.setUpdated_at(new Date());
			
			ClinicDAO.saveAndFlush(Clinic);
		}else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
		
	}

}
