package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IClinicDAO;
import com.example.demo.entity.Clinic;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.ClinicModel;
import com.example.demo.service.IClinicService;

@Service
public class ClinicService implements IClinicService{

	@Autowired
	IClinicDAO ClinicDAO;
	
	private List<ClinicModel> getListClinicModel(List<Clinic> Clinic) {
		List<ClinicModel> ClinicModels = new ArrayList<>();
		for (Clinic specialty : Clinic) {
			ClinicModel ClinicModel = new ClinicModel();
			
			ClinicModel.setId(specialty.getId());
			ClinicModel.setName(specialty.getName());
			ClinicModel.setImage(specialty.getImage());
			ClinicModel.setContentMarkDown(specialty.getContentMarkDown());
			ClinicModel.setThemanhchuyenkhoa(specialty.getThemanhchuyenkhoa());
			ClinicModel.setAddress(specialty.getAddress());
			ClinicModel.setCreated_at(specialty.getCreated_at());
			ClinicModel.setUpdated_at(specialty.getUpdated_at());
			
			ClinicModels.add(ClinicModel);
		}
		return ClinicModels;
	}
	
	@Override
	public List<ClinicModel> getAllClinic() throws SQLException {
		List<Clinic> Clinic = ClinicDAO.getAllClinic();
		List<ClinicModel> ClinicModels = getListClinicModel(Clinic);
		return ClinicModels;
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
	public Clinic addClinic(ClinicModel ClinicModel) throws SQLException {

		if (ClinicDAO.getClinicName(ClinicModel.getName()) == null
	
				) {
			
			Clinic Clinic = new Clinic();
			
			Clinic.setName(ClinicModel.getName());
			Clinic.setImage(ClinicModel.getImage());
			Clinic.setContentMarkDown(ClinicModel.getContentMarkDown());
			Clinic.setThemanhchuyenkhoa(ClinicModel.getThemanhchuyenkhoa());
			Clinic.setAddress(ClinicModel.getAddress());
			Clinic.setCreated_at(new Date());
						
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
