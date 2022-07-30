package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Question;

@Repository
@Transactional
public interface IQuestionDAO extends JpaRepository<Question,Integer> {

//	FKihlrxg5dc6wc9tthg259kc7ta
	
	@Query(nativeQuery = true, value = "SELECT * FROM heroku_e703500be6bb113.question;")
	public List<Question> getAllQuestion();
	
	@Query(value = "select * from `question` where id = :indoctorid", nativeQuery = true)
	public Question findByID(@Param("indoctorid") int indoctorid);
	
	@Query(value = "select * from `question` where user_id = :indoctorid", nativeQuery = true)
	public List<Question> findByUserID(@Param("indoctorid") int indoctorid);
	
	@Query(value = "select * from `question` where id = :question_id and user_id = :inuser_id", nativeQuery = true)
	public Question findByUserID(@Param("question_id") int question_id,@Param("inuser_id") int inuser_id);
	
	@Modifying
	@Query(value = "delete from `question` where id =:indoctorid", nativeQuery = true)
	public void deleteQuestion(@Param("indoctorid") int indoctorid);
}
