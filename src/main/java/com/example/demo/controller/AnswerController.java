package com.example.demo.controller;

import java.sql.SQLException;
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

import com.example.demo.DAO.IAnswerDAO;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.AnswerModel;
import com.example.demo.model.QuestionModel;
import com.example.demo.service.impl.AnswerService;

@RestController 
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	
//	@Autowired
//	private IAnswerDAO answerDAO;
	
	@GetMapping("/api/answer")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllAnswer() throws SQLException {
		HttpStatus httpStatus = null;
		List<Answer> answers = answerService.getListAnswer();
		try {
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(answers, httpStatus);
	}
	
	@GetMapping("/api/answer/question/{question_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllAnswerByQuestionID(@Valid @PathVariable("question_id") int question_id) throws SQLException {
		HttpStatus httpStatus = null;
		List<Answer> answers = answerService.getListAnswersByQuestionID(question_id);
		try {
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(answers, httpStatus);
	}
	
	@GetMapping("/api/answer/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAnswerByID(@Valid @PathVariable("id") int id) throws SQLException {
		HttpStatus httpStatus = null;
		Answer answer = answerService.getAnswerByID(id);
		try {
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(answer, httpStatus);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/api/answer/")
	public ResponseEntity<Object>  postAnswer(
			@Valid @RequestBody AnswerModel answerModel) throws SQLException {
		HttpStatus httpStatus = HttpStatus.CREATED;
		Answer answer = new Answer();
		answer = answerService.postAnswer(answerModel);
		return new ResponseEntity<Object>(answer,httpStatus);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/api/answer/{answer_id}")
	public ResponseEntity<Object> editQuestion(@Valid @RequestBody AnswerModel answerModel,
			@PathVariable("answer_id") int answer_id) {
			HttpStatus httpStatus = null;
			Answer answer = new Answer();

			try {
				answer =	answerService.editAnswer(answerModel, answer_id);
				httpStatus = HttpStatus.OK;
			} catch (Exception e) {
				
				 throw new InternalServerException("Không được bỏ trống các trường !");
			}
			return new ResponseEntity<Object>(answer,httpStatus);
		}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("api/answer/{id}")

	public String deleteAnswer(@PathVariable("id") int id) throws SQLException {
		try {
			answerService.deleteAnswer(id);
			return "detete";
		} catch (Exception e) {
			 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}
	}
}
