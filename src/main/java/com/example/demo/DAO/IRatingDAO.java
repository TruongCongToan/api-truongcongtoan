package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Rating;

@Repository
@Transactional
public interface IRatingDAO extends JpaRepository<Rating,Integer>  {

	@Query(nativeQuery = true, value = "SELECT * FROM heroku_e703500be6bb113.rating;")
	public List<Rating> getAllRating();
	
	@Query(value = "select * from `rating` where id = :indoctorid", nativeQuery = true)
	public Rating findByID(@Param("indoctorid") int indoctorid);
	
	@Query(value = "select * from `rating` where doctor_id = :inuser_id", nativeQuery = true)
	public List<Rating> findByDoctorID(@Param("inuser_id") int inuser_id);
	
	@Query(value = "select * from `rating` where id = :rating_id and user_id = :inuser_id and doctor_id = :indoctorid", nativeQuery = true)
	public Rating findByUserDoctorID(@Param("rating_id") int rating_id,@Param("inuser_id") int inuser_id,@Param("indoctorid") int indoctorid);
	
	@Modifying
	@Query(value = "delete from `rating` where id =:rating_id", nativeQuery = true)
	public void deleteRating(@Param("rating_id") int rating_id);
}
