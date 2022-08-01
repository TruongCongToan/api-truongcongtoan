package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Convert;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.DAO.IScheduleDAO;
import com.example.demo.entity.BulkSchedule;
import com.example.demo.entity.Schedule;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.ScheduleModel;
import com.example.demo.service.impl.ScheduleService;

@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;
//	@Autowired
//	private IScheduleDAO scheduleDAO;
	
	//get all schedule
	@GetMapping("/api/schedules")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> getAllSchedule() throws SQLException {
		HttpStatus httpStatus = null;
		try {
			List<Schedule> schedules = scheduleService.getListSchedule();
			httpStatus = HttpStatus.OK;
			return new ResponseEntity<Object>(schedules, httpStatus);
		} catch (Exception e) {
			 throw new InternalServerException("Không được bỏ trống các trường !");
		}
	}

		
	
	//get by doctorid
	@GetMapping("/api/schedules/{doctorid}")
	@CrossOrigin(origins = "http://localhost:3000")
	@Convert
	public List<ScheduleModel>getByDoctorID(@PathVariable("doctorid") int doctorid) throws SQLException {

		List<ScheduleModel> schedulesList=scheduleService.getByDoctorID(doctorid);
//		System.out.println("Gia tri list schedule "+schedulesList.get(0).getDate());
		return schedulesList;
	}
	//add list schedule
	@PostMapping("/api/schedules")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object>  addSchedules(@Valid @RequestBody BulkSchedule bulkSchedule) throws SQLException {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Schedule> schedule = scheduleService.addSchedules(bulkSchedule);
		httpStatus = HttpStatus.CREATED;
		return new ResponseEntity<Object>(schedule, httpStatus);
	}
	//get schedule by date 
	@GetMapping("/api/schedules/{doctorid}/{indate}")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Schedule> getSchedulesByDate(
			@PathVariable("doctorid") int doctorid,
			@PathVariable("indate") String indate) throws SQLException {
			try {
				List<Schedule> resultList = scheduleService.getscheduleByDate(doctorid, indate);
				return resultList;
			} catch (Exception e) {
				// TODO: handle exception
				  throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
			}
	}
	//delete Schedule 
			@CrossOrigin(origins = "http://localhost:3000")
			@DeleteMapping("api/schedules/{doctorid}/{indate}/{intype}")

			public ResponseEntity<Object> deleteSchedule(
					@PathVariable("doctorid") int doctorid,
					@PathVariable("intype") String intype,
					@PathVariable("indate") String indate){
				HttpStatus httpStatus = HttpStatus.FORBIDDEN;
				try {
					List <Schedule> scheduleList=scheduleService.deleteSchedules(doctorid,indate,intype);
					httpStatus = HttpStatus.ACCEPTED;
					return new ResponseEntity<Object>(scheduleList, httpStatus);

				} catch (Exception e) {
					  httpStatus = HttpStatus.NOT_FOUND;
					  throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
				}
			}
	
}