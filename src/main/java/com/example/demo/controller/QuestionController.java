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

import com.example.demo.DAO.IQuestionDAO;
import com.example.demo.entity.Question;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.QuestionModel;
import com.example.demo.service.impl.QuestionService;

@RestController 
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private IQuestionDAO questionDAO;
	
	@GetMapping("/api/question")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllQuestion() throws SQLException {
		HttpStatus httpStatus = null;
		List<Question> questions = questionService.getListQuestion();
		try {
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(questions, httpStatus);
	}
	
	@GetMapping("/api/question/user/{user_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllQuestionByUserID(@Valid @PathVariable("user_id")int user_id) throws SQLException {
		HttpStatus httpStatus = null;
		List<Question> questions = questionDAO.findByUserID(user_id);	
		try {
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(questions, httpStatus);
	}
	
	
	@GetMapping("/api/question/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getQuestionByID(@Valid @PathVariable("id") int id) throws SQLException {
		HttpStatus httpStatus = null;
		Question questions = questionService.getQuestionByID(id);
		try {
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(questions, httpStatus);
	}
	
	@GetMapping("/api/question/{id}/{user_id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getQuestionByID(@Valid @PathVariable("id") int id,@PathVariable("user_id") int user_id) throws SQLException {
		HttpStatus httpStatus = null;
		Question questions = questionDAO.findByUserID(id,user_id);
		try {
			httpStatus = HttpStatus.OK;
			
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
		return new ResponseEntity<Object>(questions, httpStatus);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/api/question/")
	public ResponseEntity<Object>  postQuestion(
			@Valid @RequestBody QuestionModel questionModel) throws SQLException {
		HttpStatus httpStatus = HttpStatus.CREATED;
		Question question = new Question();
		question = questionService.postQuestion(questionModel);
		return new ResponseEntity<Object>(question,httpStatus);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/api/question/{question_id}")
	public ResponseEntity<Object> editQuestion(@Valid @RequestBody QuestionModel questionModel,
			@PathVariable("question_id") int question_id) throws SQLException {
			HttpStatus httpStatus = HttpStatus.ACCEPTED;
			Question question = new Question();
			question =	questionService.editQuestion(questionModel, question_id);
			
			return new ResponseEntity<Object>(question,httpStatus);
		}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("api/question/{id}")

	public String deleteQuestion(@PathVariable("id") int id) {
		
		try {
			 questionService.deleteQuestion(id);
			 return "detete";
		} catch (Exception e) {
			 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}
		
	}
	
}
