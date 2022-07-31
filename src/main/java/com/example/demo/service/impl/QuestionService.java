package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IQuestionDAO;
import com.example.demo.DAO.IUserDAO;
import com.example.demo.entity.Question;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.QuestionModel;
import com.example.demo.service.IQuestionService;

@Service
@Component
public class QuestionService implements IQuestionService {

	@Autowired
	IQuestionDAO questionDAO;
	@Autowired
	IUserDAO userDAO;
	@Override
	public List<Question> getListQuestion() throws SQLException {
		List<Question> questions = questionDAO.getAllQuestion();
		if (questions.isEmpty()) {
		throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}else {
			return questions;	
		}
	}

	@Override
	public Question postQuestion(QuestionModel questionModel) throws SQLException {
		if (questionDAO.findByID(questionModel.getId()) == null) {
			Question question = new Question();
			question.setQuestion(questionModel.getQuestion());
			question.setSubject(questionModel.getSubject());
			question.setUsers(userDAO.findbyId(questionModel.getUser_id()));
			question.setImage(questionModel.getImage());
			question.setCreateat(new Date());
			return questionDAO.save(question);
		}else {
			throw new DuplicateRecordException("Da co trong danh sach");
		}
	}

	@Override
	public Question getQuestionByID(int question_id) throws SQLException {
		Question question = questionDAO.findByID(question_id);
			if (question == null) {
				throw new NotFoundException("Khong tim thay nguoi dung nay");
			}else {
				return question;
			}
	}

	@Override
	public Question editQuestion(QuestionModel questionModel, int question_id) throws SQLException {
		System.out.println(questionModel + "va -------" +question_id);
		Question question = questionDAO.findByID(question_id);
		if (question != null) {
			if (questionModel.getQuestion() == null) {
				question.setQuestion(question.getQuestion());
			} else {
				if (question.getQuestion() == null) {
					question.setQuestion(questionModel.getQuestion());
				}else {
					if (!question.getQuestion().equals(questionModel.getQuestion()) ) {
						question.setQuestion(questionModel.getQuestion());
					}
				}
			}
			if (questionModel.getSubject() == null) {
				question.setSubject(question.getSubject());
			} else {
				if (question.getSubject() == null) {
					question.setSubject(questionModel.getSubject());
				}else {
					if (!question.getSubject().equals(questionModel.getSubject()) ) {
						question.setSubject(questionModel.getSubject());
					}
				}
			}
			
			if (questionModel.getImage() == null) {
				question.setImage(question.getImage());
			} else {
				if (question.getImage() == null) {
					question.setImage(questionModel.getImage());
				}else {
					if (!question.getImage().equals(questionModel.getImage()) ) {
						question.setImage(questionModel.getImage());
					}
				}
			}
			
			
			question.setUpdateat(new Date());
			
			return questionDAO.saveAndFlush(question);
		} else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}
		
	}

	@Override
	public void deleteQuestion(int question_id) throws SQLException {
			Question question = questionDAO.findByID(question_id);
			
			if(question != null) {
			 questionDAO.deleteQuestion(question_id);
			}
	}

	
}
