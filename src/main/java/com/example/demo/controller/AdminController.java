package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AdminModel;
import com.example.demo.service.impl.AdminService;

@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	@Autowired
	private AdminService service;
	
	// get all admin
	@GetMapping("/api/admin")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllAdmins() {
		HttpStatus httpStatus = null;
		List<AdminModel> adminModels = new ArrayList<AdminModel>();
		try {
			adminModels = service.getListAdmin();
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println(e);
		}
		return new ResponseEntity<Object>(adminModels, httpStatus);
	}
	
	//sua thong tin nguoi dung

	@PutMapping("api/admin/{username}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> editUser(@Valid @RequestBody AdminModel adminModel,
		@PathVariable("username") String username) {
		HttpStatus httpStatus = null;
		try {
			service.editAdmin(adminModel, username);
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Object>(httpStatus);
	}
	
}
