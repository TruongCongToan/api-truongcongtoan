package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Question;
import com.example.demo.model.QuestionModel;

@Service
public interface IQuestionService {
	public List<Question> getListQuestion() throws SQLException;
	
	public Question postQuestion(QuestionModel questionModel) throws SQLException;

	public Question getQuestionByID(int question_id) throws SQLException;
	
	public Question editQuestion(QuestionModel questionModel, int question_id) throws SQLException;

	public void deleteQuestion (int question_id) throws SQLException;
	

}
