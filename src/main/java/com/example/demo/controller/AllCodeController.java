package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AllCodeModel;
import com.example.demo.service.impl.AllCodeService;

@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class AllCodeController {
	@Autowired
	private AllCodeService service;
	
	//get all allcodes service
	@GetMapping("/api/allcode")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllCodes() {
		HttpStatus httpStatus = null;
		List<AllCodeModel> allCodeModels = new ArrayList<AllCodeModel>();
		try {
			allCodeModels = service.getListAllCodeService();
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println(e);
		}
		return new ResponseEntity<Object>(allCodeModels, httpStatus);
	}
	
	//get allcodeservice by type
	@GetMapping("api/allcode/{type}")
	@CrossOrigin(origins = "http://localhost:3000")
	//lay danh sach sinh vien nu 
	public ResponseEntity<Object> getListByType(@PathVariable("type") String type){
		HttpStatus httpStatus = null;
		List<AllCodeModel> allCodeModels = new ArrayList<AllCodeModel>();
		try {
			allCodeModels = service.getAllCodeByType(type);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println(e);
		}
		return new ResponseEntity<Object>(allCodeModels, httpStatus);
	}


}
