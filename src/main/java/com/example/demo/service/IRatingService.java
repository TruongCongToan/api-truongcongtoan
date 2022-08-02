package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Rating;
import com.example.demo.model.RatingModel;

@Service
public interface IRatingService {

public List<Rating> getListRating() throws SQLException;
	
	public Rating postRating(RatingModel RatingModel) throws SQLException;

	public Rating getRatingByID(int Rating_id) throws SQLException;
	
	public Rating editRating(RatingModel RatingModel, int Rating_id) throws SQLException;

	public void deleteRating (int Rating_id) throws SQLException;
}
