package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IRatingDAO;
import com.example.demo.DAO.IUserDAO;
import com.example.demo.entity.Rating;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.RatingModel;
import com.example.demo.service.IRatingService;

@Service
@Component
public class RatingService implements IRatingService {

	@Autowired
	IRatingDAO ratingDAO;
	@Autowired
	IUserDAO userDAO;
	
	@Override
	public List<Rating> getListRating() throws SQLException {
		List<Rating> rating = ratingDAO.getAllRating();
		if (rating.isEmpty()) {
		throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}else {
			return rating;	
		}
	}

	@Override
	public Rating postRating(RatingModel RatingModel) throws SQLException {
//		System.out.println("gia tri thu duoc " + ratingDAO.findByID(RatingModel.getId()));
		if (ratingDAO.findByID(RatingModel.getId()) == null) {
			Rating rating = new Rating();
			rating.setRating(RatingModel.getRating());
			rating.setUsers(userDAO.findbyId(RatingModel.getUser_id()));
			rating.setDoctors(userDAO.findbyId(RatingModel.getDoctor_id()));
			rating.setCreateat(new Date());

			return ratingDAO.save(rating);
		}else {
			throw new DuplicateRecordException("Da co trong danh sach");
		}
	}

	@Override
	public Rating getRatingByID(int Rating_id) throws SQLException {
		Rating question = ratingDAO.findByID(Rating_id);
		if (question == null) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			return question;
		}
	}

	@Override
	public Rating editRating(RatingModel RatingModel, int Rating_id) throws SQLException {
		Rating rating = ratingDAO.findByID(Rating_id);
		if (rating != null) {
			if (RatingModel.getRating() == null) {
				rating.setRating(rating.getRating());	
				} else {
				if (rating.getRating() == null) {
					rating.setRating(RatingModel.getRating());	
				}else {
					if (!rating.getRating().equals(RatingModel.getRating()) ) {
						rating.setRating(RatingModel.getRating());
					}
				}
			}
			
			rating.setUpdateat(new Date());
			return ratingDAO.saveAndFlush(rating);
		}else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
	}

	@Override
	public void deleteRating(int Rating_id) throws SQLException {
		Rating rating = ratingDAO.findByID(Rating_id);
		
		if(rating != null) {
			ratingDAO.deleteRating(Rating_id);
		}
	}

}
