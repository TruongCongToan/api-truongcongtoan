package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Specialties;
import com.example.demo.model.SpecialtiesModel;

@Service
public interface ISpecialties {
	
	public List<SpecialtiesModel> getAllSpecialties() throws SQLException;
	
	public Specialties getSpecialtiesbyID(int intID) throws SQLException;
	
	public List<Specialties> getClinicSpecialties(int clinic_id) throws SQLException;

	public void deleteSpecialties(int id) throws SQLException;
	
	public Specialties addSpecialties(SpecialtiesModel specialtiesModel) throws SQLException;
	
	public void editSpecialties(SpecialtiesModel specialtiesModel, int id) throws SQLException;

}
