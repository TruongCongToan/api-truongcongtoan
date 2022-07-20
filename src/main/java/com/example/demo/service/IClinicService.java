package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Clinic;
import com.example.demo.model.ClinicModel;

@Service
public interface IClinicService {
	
	public List<Clinic> getAllClinic() throws SQLException;
	
	public Clinic getClinicbyID(int intID) throws SQLException;
	
	public void deleteClinic(int id) throws SQLException;
	
	public Clinic addClinic(ClinicModel ClinicModel,String[] listSpecialtyID) throws SQLException;
	
	public void editClinic(ClinicModel ClinicModel, int id) throws SQLException;

}
