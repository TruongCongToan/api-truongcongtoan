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

import com.example.demo.entity.Sinhvien;
import com.example.demo.model.SinhvienModel;
import com.example.demo.service.impl.StudentService;


@RestController 
@CrossOrigin(origins = "https://itss-random.herokuapp.com")
public class StudentController {
	@Autowired
	private StudentService service;

	// get all student
	@GetMapping("/api/students")
	@CrossOrigin(origins = "https://itss-random.herokuapp.com")
	public ResponseEntity<Object> getAllStudent() {
		HttpStatus httpStatus = null;
		List<SinhvienModel> sinhvienModels = new ArrayList<SinhvienModel>();
		try {
			sinhvienModels = service.getListStudent();
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println(e);
		}
		return new ResponseEntity<Object>(sinhvienModels, httpStatus);
	}

	// get student by masv

	@GetMapping("/api/students/{masv}")
	@CrossOrigin(origins = "https://itss-random.herokuapp.com")
	public ResponseEntity<Object> getListStudentByMasv(@PathVariable("masv") String masv) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		Sinhvien sinhvien = new Sinhvien();
		try {
			sinhvien = service.getSinhvienByMaSV(masv);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<Object>(sinhvien, httpStatus);
	}

	// them sinh vien
	@PostMapping("/api/students")
	@CrossOrigin(origins = "https://itss-random.herokuapp.com")
	public ResponseEntity<Object> addStudent(@Valid @RequestBody SinhvienModel sinhvienModel1) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		SinhvienModel sinhvienModel = new SinhvienModel();
		
		try {
			Sinhvien sinhvien = service.addSinhvien(sinhvienModel1);
			if (null != sinhvien) {
				httpStatus = HttpStatus.CREATED;
				sinhvienModel.setId(sinhvien.getId());
				sinhvienModel.setHodem(sinhvien.getHodem());
				sinhvienModel.setTen(sinhvien.getTen());
				sinhvienModel.setDob(sinhvien.getDob());
				sinhvienModel.setGioitinh(sinhvien.getGioitinh());
				sinhvienModel.setTinh(sinhvien.getTinh());
				sinhvienModel.setMalop(sinhvien.getMalop());
				sinhvienModel.setMasv(sinhvien.getMasv());
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		
		
		return new ResponseEntity<Object>(sinhvienModel, httpStatus);
	}

	// sua thong tin sinh vien
	@PutMapping("api/students/{masv}")
	@CrossOrigin(origins = "https://itss-random.herokuapp.com")
	public ResponseEntity<Object> editStudent(@Valid @RequestBody SinhvienModel sinhvienModel,
			@PathVariable("masv") String masv) {
		HttpStatus httpStatus = null;
		try {
			service.editSinhvien(sinhvienModel, masv);
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Object>(httpStatus);
	}

	// xoa sinh vien
	@CrossOrigin(origins = "https://itss-random.herokuapp.com")
	@DeleteMapping("api/students/{masv}")

	public ResponseEntity<Object> delStudent(@PathVariable("masv") String masv) {
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		try {
			service.deleteSinhVien(masv);
			httpStatus = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<Object>(httpStatus);
	}
	
	@GetMapping("api/studentsNu/{malop}")
	@CrossOrigin(origins = "https://itss-random.herokuapp.com")
	//lay danh sach sinh vien nu 
	public ResponseEntity<Object> getListSVNu(@PathVariable("malop") String malop){
		HttpStatus httpStatus = null;
		List<SinhvienModel> sinhvienModels = new ArrayList<SinhvienModel>();
		try {
			sinhvienModels = service.getListStudentByMalop(malop);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println(e);
		}
		return new ResponseEntity<Object>(sinhvienModels, httpStatus);
	}

}
