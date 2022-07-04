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

import com.example.demo.DAO.IUserDAO;
import com.example.demo.entity.Users;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.UserModel;
import com.example.demo.service.impl.UserService;

@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserService service;
	
	@Autowired
	private IUserDAO userDAO;
	// get all user
		@GetMapping("/api/users")
		@CrossOrigin(origins = "http://localhost:3000")
		public ResponseEntity<Object> getAllUsers() {
			HttpStatus httpStatus = null;
//			List<UserModel> userModels = new ArrayList<UserModel>();
			List<Users> users = userDAO.getAllUsers();
			try {
			
//				userModels = service.getListUser();
				httpStatus = HttpStatus.OK;
				
			} catch (Exception e) {
				 throw new InternalServerException("Không được bỏ trống các trường !");
			}
			return new ResponseEntity<Object>(users, httpStatus);
		}

//		public ResponseEntity<Object> getAllUsersData() {
//			HttpStatus httpStatus = null;
////			List<UserModel> userModels = new ArrayList<UserModel>();
//			List<UserData> users = userDAO.getAllUsersData();
//			try {
//			
////				userModels = service.getListUser();
//				httpStatus = HttpStatus.OK;
//				
//			} catch (Exception e) {
//				 throw new InternalServerException("Không được bỏ trống các trường !");
//			}
//			return new ResponseEntity<Object>(users, httpStatus);
//		}
		

		
	//tim user theo username
		@GetMapping("/api/users/{username}")
		@CrossOrigin(origins = "http://localhost:3000")
		public ResponseEntity<Object> getListUsersByName(@PathVariable("username") String username) {
			HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			Users user = new Users();
			try {
				user = service.getUserByName(username);
				httpStatus = HttpStatus.OK;
			} catch (Exception e) {
				 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
			}
			return new ResponseEntity<Object>(user, httpStatus);
		}
		///tim user theo username
		@GetMapping("/api/users/id={user_id}")
		@CrossOrigin(origins = "http://localhost:3000")
		public ResponseEntity<Object> getUserByID(@PathVariable("user_id") int user_id) {
			HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			Users user = new Users();
			try {
				user = service.getUserByID(user_id);
				httpStatus = HttpStatus.OK;
			} catch (Exception e) {
				 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
			}
			return new ResponseEntity<Object>(user, httpStatus);
		}
	
	
	// them user
		@PostMapping("/api/users")
		@CrossOrigin(origins = "http://localhost:3000")
		public ResponseEntity<Object> addUsers(@Valid @RequestBody UserModel userModel1) {
			HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			UserModel userModel = new UserModel();
			
			try {
				Users user = service.addUser(userModel1);
				if (null != user) {
					httpStatus = HttpStatus.CREATED;
					
//					userModel.setUser_id(user.getUser_id());
//					userModel.setEmail(user.getEmail());
//					userModel.setPassword(user.getPassword());
					userModel.setAddress(user.getAddress());
					userModel.setGender(user.getGender());
					userModel.setRole(user.getRole());
					
					userModel.setImage(user.getImage());
					userModel.setCreated_at(user.getCreated_at());
					userModel.setUpdated_at(user.getUpdated_at());
					
				}
			} catch (Exception e) {
				 throw new DuplicateRecordException("Da co DotorID nay trong danh sach");		 
			}
			return new ResponseEntity<Object>(userModel, httpStatus);
		}
		
	//sua thong tin nguoi dung

		@PutMapping("api/users/{username}")
		@CrossOrigin(origins = "http://localhost:3000")
		public ResponseEntity<Object> editUser(@Valid @RequestBody UserModel userModel,
			@PathVariable("username") String username) {
			HttpStatus httpStatus = null;
			try {
				service.editUser(userModel, username);
				httpStatus = HttpStatus.OK;

			} catch (Exception e) {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				 throw new InternalServerException("Không được bỏ trống các trường !");

			}
			return new ResponseEntity<Object>(httpStatus);
		}
	//xoa nguoi dung
		@CrossOrigin(origins = "http://localhost:3000")
		@DeleteMapping("api/users/{username}")

		public ResponseEntity<Object> delUser(@PathVariable("username") String username) {
			HttpStatus httpStatus = HttpStatus.FORBIDDEN;
			try {
				service.deleteUser(username);
				httpStatus = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				
				  httpStatus = HttpStatus.NOT_FOUND;
				  throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
			}
			return new ResponseEntity<Object>(httpStatus);
		}
		
		@CrossOrigin(origins = "http://localhost:3000")
		@DeleteMapping("api/userdata/{email}")

		public ResponseEntity<Object> delUserData(@PathVariable("email") String email) {
			HttpStatus httpStatus = HttpStatus.FORBIDDEN;
			try {
				userDAO.deleteUserData(email);
				httpStatus = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				
				  httpStatus = HttpStatus.NOT_FOUND;
				  throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
			}
			return new ResponseEntity<Object>(httpStatus);
		}
		
		//paginated users
		
		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("api/users/{pageNo}/{pageSize}")
		public List<Users> getPaginatedUser(@PathVariable int pageNo,@PathVariable int pageSize){
			return service.findPaginated(pageNo, pageSize);
			
		}
		//get all doctors
		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("api/users/doctors")
		public List<Users> getAllDoctors() throws SQLException {
	
		return service.getListDoctors();
		}
		
}