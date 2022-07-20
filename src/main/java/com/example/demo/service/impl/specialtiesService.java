package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.ISpecialtiesDAO;
import com.example.demo.entity.Specialties;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.SpecialtiesModel;
import com.example.demo.service.ISpecialties;

@Service
public class specialtiesService implements ISpecialties{

	@Autowired
	ISpecialtiesDAO specialtiesDAO;
	
	private List<SpecialtiesModel> getListSpecialtiesModel(List<Specialties> specialties) {
		List<SpecialtiesModel> specialtiesModels = new ArrayList<>();
		for (Specialties specialty : specialties) {
			SpecialtiesModel specialtiesModel = new SpecialtiesModel();
			
			specialtiesModel.setId(specialty.getId());
			specialtiesModel.setName(specialty.getName());
			specialtiesModel.setImage(specialty.getImage());
			specialtiesModel.setContentMarkDown(specialty.getContentMarkDown());
			specialtiesModel.setCreated_at(specialty.getCreated_at());
			specialtiesModel.setUpdated_at(specialty.getUpdated_at());
			
			specialtiesModels.add(specialtiesModel);
		}
		return specialtiesModels;
	}
	
	@Override
	public List<SpecialtiesModel> getAllSpecialties() throws SQLException {
		List<Specialties> specialties = specialtiesDAO.getAllSpecialties();
		List<SpecialtiesModel> specialtiesModels = getListSpecialtiesModel(specialties);
		return specialtiesModels;
	}

	@Override
	public Specialties getSpecialtiesbyID(int intID) throws SQLException {
		if (intID != 0) {
			Specialties specialties = specialtiesDAO.getSpecialtiesByID(intID);
			if (specialties != null) {
				return specialties;
			}else {
				   throw new NotFoundException("Khong tim thay nguoi dung nay");
			}
		} else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
	}

	@Override
	public void deleteSpecialties(int id) throws SQLException {
		if (specialtiesDAO.getSpecialtiesByID(id) != null) {
			specialtiesDAO.deleteSpecialties(id);
		}
		else {
			throw new NotFoundException("not found");
		}		
	}

	@Override
	public Specialties addSpecialties(SpecialtiesModel specialtiesModel) throws SQLException {
		System.out.println(specialtiesDAO.getSpecialtiesName(specialtiesModel.getName()));

		if (specialtiesDAO.getSpecialtiesName(specialtiesModel.getName()) == null) {
			
			Specialties specialties = new Specialties();
			
			specialties.setName(specialtiesModel.getName());
			specialties.setImage(specialtiesModel.getImage());
			specialties.setContentMarkDown(specialtiesModel.getContentMarkDown());
			specialties.setCreated_at(new Date());
						
			return specialtiesDAO.save(specialties);
		}
		else {
			 throw new DuplicateRecordException("Da co user nay trong danh sach");
		}
			
	}

	@Override
	public void editSpecialties(SpecialtiesModel specialtiesModel, int id) throws SQLException {
		if (specialtiesDAO.getSpecialtiesByID(id) != null) {
			Specialties specialties = specialtiesDAO.getSpecialtiesByID(id);
			
			if (!specialties.getName().equals(specialtiesModel.getName())) {
 				specialties.setName(specialtiesModel.getName());
 			}
			specialties.setImage(specialtiesModel.getImage());
			
			if (!specialties.getContentMarkDown().equals(specialtiesModel.getContentMarkDown())) {
 				specialties.setName(specialtiesModel.getName());
 			}
			specialties.setUpdated_at(new Date());
			
			specialtiesDAO.saveAndFlush(specialties);
		}else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
		
	}

	@Override
	public List<Specialties> getClinicSpecialties(int clinic_id) throws SQLException {
		if (clinic_id == 0){
			throw new NotFoundException("Gia tri nhap vao khong hop le");
		}else {
			List<Specialties> clinicSpecialties = specialtiesDAO.getSpecialtiesByCLinicID(clinic_id);
			if (clinicSpecialties != null) {
				return clinicSpecialties;
			}else {
				   throw new NotFoundException("Khong tim thay nguoi dung nay");
			}
		}
	}

}
