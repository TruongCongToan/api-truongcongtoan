package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Answer;
import com.example.demo.model.AnswerModel;

@Service

public interface IAnswerService {
public List<Answer> getListAnswer() throws SQLException;
	
	public Answer postAnswer(AnswerModel answerModel) throws SQLException;

	public Answer getAnswerByID(int answer_id) throws SQLException;
	
	public Answer editAnswer(AnswerModel answerModel, int answer_id) throws SQLException;

	public void deleteAnswer (int answer_id) throws SQLException;
	
	public List<Answer> getListAnswersByQuestionID(int question_id) throws SQLException;

}

