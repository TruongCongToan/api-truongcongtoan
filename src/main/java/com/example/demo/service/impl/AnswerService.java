package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IAnswerDAO;
import com.example.demo.DAO.IQuestionDAO;
import com.example.demo.DAO.IUserDAO;
import com.example.demo.entity.Answer;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.AnswerModel;
import com.example.demo.service.IAnswerService;

@Service
public class AnswerService implements IAnswerService{

	@Autowired
	IAnswerDAO answerDAO;
	
	@Autowired
	IQuestionDAO questionDAO;
	
	@Autowired
	IUserDAO userDAO;
	
	@Override
	public List<Answer> getListAnswer() throws SQLException {
		List<Answer> answers = answerDAO.getAllAnswer();
		if (answers.isEmpty()) {
		throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}else {
			return answers;	
		}
	}

	@Override
	public Answer postAnswer(AnswerModel answerModel) throws SQLException {
		if (answerDAO.findByID(answerModel.getId()) == null) {
			Answer answer = new Answer();
			
			answer.setAnswer(answerModel.getAnswer());
			answer.setQuestions(questionDAO.findByID(answerModel.getQuestion_id()));
			answer.setUsers(userDAO.findbyId(answerModel.getUser_id()));
			
			answer.setCreateat(new Date());
			
			
			return answerDAO.save(answer);
		}else {
			throw new DuplicateRecordException("Da co trong danh sach");
		}
	}

	@Override
	public Answer getAnswerByID(int answer_id) throws SQLException {
		Answer answer = answerDAO.findByID(answer_id);
		if (answer == null) {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}else {
			return answer;
		}
	}

	@Override
	public Answer editAnswer(AnswerModel answerModel, int answer_id) throws SQLException {
		Answer answer = answerDAO.findByID(answer_id);
		if (answer != null) {
			if (answerModel.getAnswer() == null) {
				answer.setAnswer(answer.getAnswer());
			} else {
				if (answer.getAnswer() == null) {
					answer.setAnswer(answerModel.getAnswer());				
					}else {
					if (!answer.getAnswer().equals(answerModel.getAnswer()) ) {
						answer.setAnswer(answerModel.getAnswer());	
					}
				}
			}
			answer.setUpdateat(new Date());
			
			return answerDAO.saveAndFlush(answer);
		} else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
		
	}

	@Override
	public void deleteAnswer(int answer_id) throws SQLException {
		if (answerDAO.findByID(answer_id) != null) {
	 answerDAO.deleteAnswer(answer_id);
		}
		else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}	
	}

	@Override
	public List<Answer> getListAnswersByQuestionID(int question_id) throws SQLException {
		List<Answer> answers = answerDAO.findbyQuestionID(question_id);
		
		if (answers.isEmpty()) {
		throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}else {
			return answers;	
		}
	}

}
