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

import com.example.demo.DAO.IRatingDAO;
import com.example.demo.entity.Rating;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.RatingModel;
import com.example.demo.service.impl.RatingService;

@RestController 
public class RatingController {

	@Autowired
	IRatingDAO ratingDAO;
	
	@Autowired
	RatingService ratingService;
	
	@GetMapping("/api/rating")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllRating() throws SQLException {
		HttpStatus httpStatus = null;
		List<Rating> rating = ratingService.getListRating();
	
			httpStatus = HttpStatus.OK;
			
			if(rating.size() <= 0) {
				throw new NotFoundException("Khong tim thay nguoi dung nay");
			}
			
		return new ResponseEntity<Object>(rating, httpStatus);
	}
	
	@GetMapping("/api/rating/user/{doctor_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllRatingByUserID(@Valid @PathVariable("doctor_id")int doctor_id) throws SQLException {
		HttpStatus httpStatus = null;
		List<Rating> rating = new ArrayList<>();

			httpStatus = HttpStatus.OK;
			rating = ratingDAO.findByDoctorID(doctor_id);	
			if(rating.size() <= 0) {
				throw new NotFoundException("Khong tim thay nguoi dung nay");
			}
		
		return new ResponseEntity<Object>(rating, httpStatus);
	}
	
	@GetMapping("/api/rating/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getRatingByID(@Valid @PathVariable("id") int id) throws SQLException {
		HttpStatus httpStatus = null;
		Rating rating = ratingService.getRatingByID(id);

			httpStatus = HttpStatus.OK;
			if(rating == null) {
				throw new NotFoundException("Khong tim thay nguoi dung nay");
			}
		
		return new ResponseEntity<Object>(rating, httpStatus);
	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/api/rating/")
	public ResponseEntity<Object>  postRating(
			@Valid @RequestBody RatingModel ratingModel) throws SQLException {
		HttpStatus httpStatus = HttpStatus.CREATED;
		Rating rating = new Rating();
		rating = ratingService.postRating(ratingModel);
		return new ResponseEntity<Object>(rating,httpStatus);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/api/rating/{rating_id}")
	public ResponseEntity<Object> editRating(@Valid @RequestBody RatingModel ratingModel,
			@PathVariable("rating_id") int rating_id) throws SQLException {
			HttpStatus httpStatus = HttpStatus.ACCEPTED;
			Rating rating = new Rating();
			rating =	ratingService.editRating(ratingModel, rating_id);
			
			return new ResponseEntity<Object>(rating,httpStatus);
		}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("api/rating/{id}")
	public String deleteRating(@PathVariable("id") int id) {
		try {
			 ratingService.deleteRating(id);
			 return "detete";
		} catch (Exception e) {
			 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}
		
	}
	
}
