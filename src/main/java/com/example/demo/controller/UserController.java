package com.example.demo.controller;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.IUserDAO;
import com.example.demo.JwtUtil.JwtUtil;
import com.example.demo.entity.Users;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.EmailDTO;
import com.example.demo.model.Token;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserPrincipal;
import com.example.demo.service.impl.EmailService;
import com.example.demo.service.impl.UserService;

import net.bytebuddy.utility.RandomString;



@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserService service;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
    private JwtUtil jwtUtil;
	

	@Autowired
	private EmailService emailService;
	
	String token = RandomString.make(45);
	
	 @PostMapping("/api/user/register")
	    public ResponseEntity<Object> register(@RequestBody UserModel userModel) throws SQLException{
		 userModel.setPassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));
		 System.out.println("Psssword "+userModel.getPassword());
		 Users users = new Users();
			HttpStatus httpStatus = null;
		 try {
	        	users = service.addUser(userModel);
	        	  UserPrincipal userPrincipal = service.findByUserEmail(users.getEmail());
	        	  
	        	  Token token = new Token();
	  	        token.setToken(jwtUtil.generateTokenLogin(userPrincipal.getUsername()));
	  	        token.setTokenExpDate(jwtUtil.generateExpirationDate());
	  	        token.setUserid(userPrincipal.getUserId());
	  	        httpStatus = HttpStatus.CREATED;
	  	      return  new ResponseEntity<Object>(token, httpStatus);
			} catch (Exception e) {
				throw new DuplicateRecordException("Tài khoản đã tồn tại trong hệ thong");
			}
	    }
	 
	    @PostMapping("/api/user/login")
	    public ResponseEntity<Object> login(@RequestBody UserModel userModel){
	        UserPrincipal userPrincipal = service.findByUserEmail(userModel.getEmail());
			HttpStatus httpStatus = null;

	        if (null == userModel || !new BCryptPasswordEncoder().matches(userModel.getPassword(), userPrincipal.getPassword())) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tài khoản hoặc mật khẩu không chính xác");
	        }
	        Token token = new Token();
	        token.setToken(jwtUtil.generateTokenLogin(userPrincipal.getUsername()));
	        token.setTokenExpDate(jwtUtil.generateExpirationDate());
	        token.setUserid(userPrincipal.getUserId());
	        httpStatus = HttpStatus.CREATED;
	        return  new ResponseEntity<Object>(token, httpStatus);
	    }
	
		
	    
	    
		@GetMapping("/api/users")
		@CrossOrigin(origins = "http://localhost:3000")
		public ResponseEntity<Object> getAllUsers() throws SQLException {
			HttpStatus httpStatus = null;
			List<Users> users = new ArrayList<Users>();
			try {
				users = service.getListUser();
				httpStatus = HttpStatus.OK;
				
			} catch (Exception e) {
				 throw new InternalServerException("Không được bỏ trống các trường !");
			}
			return new ResponseEntity<Object>(users, httpStatus);
		}

		@GetMapping("/api/users/email={email}")
		@CrossOrigin(origins = "http://localhost:3000")
		public ResponseEntity<Object> getListUsersByName(@PathVariable("email") String email) {
			HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			Users user = new Users();
			try {
				user = service.getUserByName(email);
				httpStatus = HttpStatus.OK;
			} catch (Exception e) {
				 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
			}
			return new ResponseEntity<Object>(user, httpStatus);
		}
		
		@GetMapping("/api/users/{user_id}")
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

		@PutMapping("api/users/{inid}")
		@CrossOrigin(origins = "http://localhost:3000")
		public ResponseEntity<Object> editUser(@Valid @RequestBody UserModel userModel,
			@PathVariable("inid") int inid) {
			HttpStatus httpStatus = null;
			Users user = new Users();		
			try {
				user = service.editUser(userModel, inid);
				httpStatus = HttpStatus.CREATED;

			} catch (Exception e) {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				 throw new InternalServerException("Không được bỏ trống các trường !");
			}
			return new ResponseEntity<Object>(user,httpStatus);
		}
	//xoa nguoi dung
		@CrossOrigin(origins = "http://localhost:3000")
		@DeleteMapping("api/users/{inid}")

		public ResponseEntity<Object> delUser(@PathVariable("inid") int inid) {
			HttpStatus httpStatus = HttpStatus.FORBIDDEN;
			try {
				service.deleteUser(inid);
				httpStatus = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				
				  httpStatus = HttpStatus.NOT_FOUND;
				  throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
			}
			return new ResponseEntity<Object>(httpStatus);
		}
		

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
		
		@GetMapping("/api/verify-user/{token}/{email}")
		@CrossOrigin(origins = "http://localhost:3000")
		public String VerifyBooking(@Valid @PathVariable("token") String token,  @PathVariable("email") String email) {
			Users users = new Users();
			
			try {
				users = service.VerifyResetPassword(token,email);
				 if (users != null) {
						return "Bạn đã xác nhận reset mật khẩu thành công! Bước tiếp theo xin mời đổi mật khẩu tại màn hình ứng dụng BKHcare .Xin cảm ơn và chúc bạn nhiều điều tốt đẹp.";
				}else {
					return "Thông báo : Xác  nhận reset mật khẩu thất bại !";
				}
			} catch (Exception e) {
				return "Thông báo : Xác  nhận reset mật khẩu thất bại! Xin vui lòng kiểm tra lại!";
				}
			}
		
		@GetMapping("/api/resetpassword/{userId}/{newpass}")
		@CrossOrigin(origins = "http://localhost:3000")
		ResponseEntity<Object>  UpdatePassword(@Valid @PathVariable("userId") int userId,  @PathVariable("newpass") String newpass) {
			Users users = new Users();
			HttpStatus httpStatus = HttpStatus.FORBIDDEN;

			try {
				users = service.updatPassword(userId,new BCryptPasswordEncoder().encode(newpass));
				 if (users != null) {
					 httpStatus = HttpStatus.ACCEPTED;
					 }else {
						  httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
						 throw new InternalServerException("Không được bỏ trống các trường !");
				}
			} catch (Exception e) {
				  httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				 throw new InternalServerException("Không được bỏ trống các trường !");
				}
			
			return new ResponseEntity<Object>(users,httpStatus);
			}
		@GetMapping("/api/users/getOTP/{userID}")
		public String GetOTP(@Valid @PathVariable("userID") int userID) throws SQLException{
			if (userDAO.getOTP(userID) != null) {
				return userDAO.getOTP(userID);
			}else {
				throw new NotFoundException("Khong tim thay ma OTP cua nguoi dung nay");
			}
	}

		
		@GetMapping("/api/users/forgotpassword/{email}")
		@CrossOrigin(origins = "http://localhost:3000")
		public ResponseEntity<Object> ForgotPassword(@Valid @PathVariable("email") String email) throws SQLException {
			HttpStatus httpStatus = null;
			Users users = new Users();
			users = userDAO.findByEmail(email);
			EmailDTO emailDTO = new EmailDTO();
			
			emailDTO.setTo(email);
			emailDTO.setSubject("BKHcare xin thông báo reset mật khẩu người dùng");
			String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));

			 try {
				if(users != null) {
					 service.updateResetPasswordToken(token, email,otp);
					 Map<String, Object> templateData = new HashMap<>();
//					String direct_url = String.format("http://api-truongcongtoan.herokuapp.com/api/verify-user/%s/%s",token,email);
					 templateData.put("name", users.getFull_name());
					 templateData.put("otp", otp);
					 
					 emailDTO.setEmailData(templateData);
						emailService.sendResetPasswordEmail(emailDTO);
					 httpStatus = HttpStatus.OK;
				}else {
					throw new InternalServerException("Không được bỏ trống các trường !");
				}
				
			} catch (Exception e) {
				 throw new InternalServerException("Không được bỏ trống các trường !");
			}
			return new ResponseEntity<Object>(users, httpStatus);
		}
		
}