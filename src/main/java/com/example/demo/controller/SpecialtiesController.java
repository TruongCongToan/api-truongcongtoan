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

import com.example.demo.entity.Specialties;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.SpecialtiesModel;
import com.example.demo.service.impl.specialtiesService;

@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class SpecialtiesController {
	@Autowired
	specialtiesService service;
	
	@GetMapping("/api/specialties")
	public ResponseEntity<Object> getAllSpecialties() {
		HttpStatus httpStatus = null;
		List<SpecialtiesModel> specialtiesModels = new ArrayList<SpecialtiesModel>();
		try {
			
			httpStatus = HttpStatus.OK;
			specialtiesModels = service.getAllSpecialties();
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(specialtiesModels, httpStatus);
	}
	
	@GetMapping("/api/specialties/{id}")
	public ResponseEntity<Object> getSpecialtiesByID(@PathVariable("id") int id) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		Specialties specialties = new Specialties();
		try {
			specialties = service.getSpecialtiesbyID(id);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<Object>(specialties, httpStatus);
	}
	
	@PostMapping("/api/specialties")
	public ResponseEntity<Object> addSpeciatlties(@Valid @RequestBody SpecialtiesModel specialtiesModel){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		SpecialtiesModel specialtiesModelR = new SpecialtiesModel();
		
		try {
			Specialties specialties = service.addSpecialties(specialtiesModel);
			if (specialties != null) {
				httpStatus = HttpStatus.CREATED;
				
				specialtiesModelR.setName(specialties.getName());
				specialtiesModelR.setImage(specialties.getImage());
				specialtiesModelR.setContentMarkDown(specialties.getContentMarkDown());
				specialtiesModelR.setCreated_at(specialties.getCreated_at());
				specialtiesModelR.setUpdated_at(specialties.getUpdated_at());
				
			}
		} catch (Exception e) {
			 throw new DuplicateRecordException("Da co DotorID nay trong danh sach");		 
		}
		return new ResponseEntity<Object>(specialtiesModelR, httpStatus);
	}

	@PutMapping("api/specialties/{id}")
	public ResponseEntity<Object> editSpecialties(@Valid @RequestBody SpecialtiesModel specialtiesModel,
		@PathVariable("id") int id) {
		HttpStatus httpStatus = null;
		try {
			service.editSpecialties(specialtiesModel, id);
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(httpStatus);
	}
	@DeleteMapping("api/specialties/{id}")

	public ResponseEntity<Object> delSpecialties(@PathVariable("id") int id) {
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		try {
			service.deleteSpecialties(id);
			httpStatus = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			
			  httpStatus = HttpStatus.NOT_FOUND;
			  throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}
		return new ResponseEntity<Object>(httpStatus);
	}
	
}
