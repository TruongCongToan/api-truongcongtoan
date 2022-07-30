package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Answer;

@Repository
@Transactional
public interface IAnswerDAO extends JpaRepository<Answer,Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM heroku_e703500be6bb113.answer;")
	public List<Answer> getAllAnswer();
	
	@Query(value = "select * from `answer` where id = :indoctorid", nativeQuery = true)
	public Answer findByID(@Param("indoctorid") int indoctorid);
	
	
	@Modifying
	@Query(value = "delete from `answer` where id = :inid", nativeQuery = true)
	public void deleteAnswer(@Param("inid") int inid);
	
	@Query(value = "select * from `answer` where question_id = :inid", nativeQuery = true)
	public List<Answer> findbyQuestionID(@Param("inid") int inid);
	
}
