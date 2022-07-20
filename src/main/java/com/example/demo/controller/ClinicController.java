package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Clinic;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.ClinicModel;
import com.example.demo.service.impl.ClinicService;
@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class ClinicController {
	@Autowired
	ClinicService service;
	
	@GetMapping("/api/Clinic")
	public ResponseEntity<Object> getAllClinic() {
		HttpStatus httpStatus = null;
		List<Clinic> ClinicModels = new ArrayList<Clinic>();
		try {
			
			httpStatus = HttpStatus.OK;
			ClinicModels = service.getAllClinic();
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(ClinicModels, httpStatus);
	}
	
	@GetMapping("/api/Clinic/{id}")
	public ResponseEntity<Object> getClinicByID(@PathVariable("id") int id) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		Clinic Clinic = new Clinic();
		try {
			Clinic = service.getClinicbyID(id);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<Object>(Clinic, httpStatus);
	}
	
	@GetMapping("/api/ClinicSpecialties")
	public ResponseEntity<Object> getClinicSpecialties() {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Clinic> Clinic = new ArrayList<>();
		try {
			Clinic = service.getAllClinicWithSpecialties();
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<Object>(Clinic, httpStatus);
	}
	
	@PostMapping("/api/Clinic/{listSpecialtiesID}")
	public ResponseEntity<Object> addSpeciatlties(@Valid @RequestBody ClinicModel ClinicModel,
			@PathVariable("listSpecialtiesID") String[] listSpecialtiesID){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ClinicModel ClinicModelR = new ClinicModel();
		
		try {
			Clinic Clinic = service.addClinic(ClinicModel,listSpecialtiesID);
			if (Clinic != null) {
				httpStatus = HttpStatus.CREATED;
				
				ClinicModelR.setName(Clinic.getName());
				ClinicModelR.setImage(Clinic.getImage());
				ClinicModelR.setContentMarkDown(Clinic.getContentMarkDown());
				ClinicModelR.setCreated_at(Clinic.getCreated_at());
				ClinicModelR.setUpdated_at(Clinic.getUpdated_at());
				
			}
		} catch (Exception e) {
			 throw new DuplicateRecordException("Da co DotorID nay trong danh sach");		 
		}
		return new ResponseEntity<Object>(ClinicModelR, httpStatus);
	}

	@PutMapping("api/Clinic/{id}")
	public ResponseEntity<Object> editClinic(@Valid @RequestBody ClinicModel ClinicModel,
		@PathVariable("id") int id) {
		HttpStatus httpStatus = null;
		try {
			service.editClinic(ClinicModel, id);
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(httpStatus);
	}
	@DeleteMapping("api/Clinic/{idList}")

	public ResponseEntity<Object> delClinic(@PathVariable("idList") int idList) {
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		try {
			  
			service.deleteClinic(idList);
			httpStatus = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			
			  httpStatus = HttpStatus.NOT_FOUND;
			  throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}
		return new ResponseEntity<Object>(httpStatus);
	}
	
}
