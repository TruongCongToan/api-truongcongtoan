package com.example.demo.controller;

import java.sql.SQLException;
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

import com.example.demo.entity.MarkDown;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.MarkDownModel;
import com.example.demo.service.impl.MarkDownService;

@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class MarkDownController {
	@Autowired
	private MarkDownService markDownService;
//	@Autowired 
//	private IMarkDownDAO markDownDAO;
	
	// get all markdowns
	@GetMapping("/api/markdowns")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllMarkDowns() {
		HttpStatus httpStatus = null;
		List<MarkDown> markDownModels = new ArrayList<MarkDown>();
	
		httpStatus = HttpStatus.OK;
//		markDownModels = markDownDAO.getAllMarkDown();
		try {
			markDownModels = markDownService.getLisMarkDown();
			System.out.println("gia tri lay duoc tu service "+markDownModels);
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		
		}
		return new ResponseEntity<Object>(markDownModels, httpStatus);
	}
	
	//post doctor Info MarkDown
			@CrossOrigin(origins = "http://localhost:3000")
			@PostMapping("/api/markdowns")
			public ResponseEntity<Object>  postInforDoctor(
					@Valid @RequestBody MarkDownModel markDownModel) throws SQLException {
				HttpStatus httpStatus = null;
				MarkDown markDown = new MarkDown();
				try {
					markDown = markDownService.postInforDoctor(markDownModel);
					httpStatus = HttpStatus.CREATED;				
							} catch (Exception e) {
					 throw new DuplicateRecordException("bad resquest");		 
				}
				return new ResponseEntity<Object>(markDown,httpStatus);
		}
	//edit doctor info
			@CrossOrigin(origins = "http://localhost:3000")
			@PutMapping("/api/markdowns/{doctorID}")
			public ResponseEntity<Object> editDoctorInfo(@Valid @RequestBody MarkDownModel markDownModel,
					@PathVariable("doctorID") int doctorID) {
					HttpStatus httpStatus = null;
					System.out.println("gia tri dau vao " +markDownModel);
					MarkDown markDown = new MarkDown();
					try {
						markDown = markDownService.editDoctorInfo(markDownModel, doctorID);
						httpStatus = HttpStatus.CREATED;

					} catch (Exception e) {
						
						 throw new InternalServerException("Không được bỏ trống các trường !");

					}
					return new ResponseEntity<Object>(markDown,httpStatus);
				}
			
		
			
			//tim user theo doctorID
			@GetMapping("/api/markdowns/{doctorID}")
			@CrossOrigin(origins = "http://localhost:3000")
			public ResponseEntity<Object> getMarkDownbyDoctorid(@PathVariable("doctorID") int doctorID) {
				HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				
				MarkDown markDown = new MarkDown();
				try {
					markDown = markDownService.getMarkDownByDoctorID(doctorID);
					httpStatus = HttpStatus.OK;
				} catch (Exception e) {
					  httpStatus = HttpStatus.NOT_FOUND;

					 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
				}
				return new ResponseEntity<Object>(markDown, httpStatus);
			}
			
			
			@GetMapping("/api/markdowns/specialties/{specialty_id}")
			@CrossOrigin(origins = "http://localhost:3000")
			public ResponseEntity<Object> getListUsersBySpecialtyID(@PathVariable("specialty_id") int specialty_id) {
				HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				

				List<MarkDown> markDown = new  ArrayList<MarkDown>();
				try {
					markDown = markDownService.getMarkDownBySpecialtyID(specialty_id);
					httpStatus = HttpStatus.OK;
				} catch (Exception e) {
					  httpStatus = HttpStatus.NOT_FOUND;

					 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
				}
				return new ResponseEntity<Object>(markDown, httpStatus);
			}

		//delete markdown
			@CrossOrigin(origins = "http://localhost:3000")
			@DeleteMapping("api/markdowns/{markdown_id}")

			public ResponseEntity<Object> delStudent(@PathVariable("markdown_id") int markdown_id) {
				HttpStatus httpStatus = HttpStatus.FORBIDDEN;
				
				try {
					markDownService.deleteMarkdown(markdown_id);
					httpStatus = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					e.getStackTrace();
//					 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
				}
				return new ResponseEntity<Object>(httpStatus);
			}
			
}
