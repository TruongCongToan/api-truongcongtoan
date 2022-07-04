package com.example.demo.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IScheduleDAO;
import com.example.demo.entity.BulkSchedule;
import com.example.demo.entity.Schedule;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.ScheduleModel;
import com.example.demo.service.IScheduleService;


@Service
public class ScheduleService implements IScheduleService {
	
	@Autowired
	private IScheduleDAO scheduleDAO;

	//add list schedule
	public List<Schedule> addSchedules(BulkSchedule bulkSchedule) throws SQLException {
		List<Schedule> scheduleEntityList = new ArrayList<Schedule>();
		List<Schedule> schedules = getListSchedule();
	
		bulkSchedule.getBulkSchedules().forEach(
				each ->{
					Schedule schedule = new Schedule();
					
					schedule.setCurrentnumber(each.getCurrentnumber());
					schedule.setMaxnumber(each.getMaxnumber());
					schedule.setTimetype(each.getTimetype());
					schedule.setCreateat(each.getCreateat());
					schedule.setUpdateat(each.getUpdateat());
					schedule.setDate(each.getDate());
					schedule.setDoctorid(each.getDoctorid());
					
					scheduleEntityList.add(schedule);
					scheduleDAO.saveAll(scheduleEntityList);
				});
		
		return schedules;		
	}

	
//get list schedule
	@Override
	public List<Schedule> getListSchedule() throws SQLException {
		List<Schedule> schedules = scheduleDAO.getAllSchedule();
		return schedules;
	}
	

	//get scheduce by doctorId and date
	public List<Schedule> getscheduleByDate(int inid,String indate) throws SQLException, ParseException{
		List<Schedule> schedulesList=scheduleDAO.getScheduleByDoctorID(inid);
//		long dateLong = Long.valueOf(indate).longValue();
////		String dateFormated = new SimpleDateFormat("yyyy-MM-dd").format();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		
////	     String dateFormated = dateFormat.format(dateLong * 1000L);
//	     String dateFormated = dateFormat.format(dateLong);
	     
		List<Schedule> resultList = new ArrayList<Schedule>() ;
		
		if(schedulesList != null) {
			schedulesList.forEach(each -> {
				if(indate.equals(each.getDate())) {
					resultList.add(each);
				}
			});
		}else {
			System.out.println("Khong tin thay thong tin");
		}
		return resultList;

	}
	
	
//delete schedule
	@Override
	public List<Schedule> deleteSchedules(int inid,String indate,String intype) throws SQLException {
		List<Schedule> schedulesList=scheduleDAO.getScheduleByDoctorID(inid);
		String timestamp = "1647043200";
		long testtimestamp = Long.valueOf(timestamp).longValue();
		 
		String dateFormated = new SimpleDateFormat("yyyy-MM-dd").format(testtimestamp);
		
		System.out.println("Date format "+dateFormated);
//		System.out.println(schedulesList);
//		
		
		if (schedulesList != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			scheduleDAO.deleteSchedule(inid,indate,intype);
			schedulesList.forEach(each->{
			
				if(dateFormat.format(each.getDate()).equals(indate) == true) {
					
					if(each.getTimetype().equals(intype)) {
						scheduleDAO.deleteSchedule(each.getId());
						System.out.println(each.getId() + each.getTimetype());
					}
				}else {
					System.out.println("Error");
				}
			});
			return schedulesList;
		}
		else {
			throw new NotFoundException("Khong tim thay nguoi dung nay");
		}

	}
	// ham lay ra sinh vien
		private List<ScheduleModel> getListScheduleByDoctorID(List<Schedule> schedules) {
			List<ScheduleModel> scheduleModels = new ArrayList<>();
			for (Schedule schedule : schedules) {
				ScheduleModel scheduleModel = new ScheduleModel();
				
				scheduleModel.setDate(schedule.getDate());
				scheduleModel.setDoctorid(schedule.getDoctorid());
				scheduleModel.setMaxnumber(schedule.getMaxnumber());
				scheduleModel.setTimetype(schedule.getTimetype());
				
				scheduleModels.add(scheduleModel);
			}
			return scheduleModels;
		}

	@Override
	public List<ScheduleModel> getByDoctorID(int inid) throws SQLException {
		if (inid != 0) {
			
				List<Schedule> schedules = scheduleDAO.getScheduleByDoctorID(inid);
				List<ScheduleModel> scheduleModels = getListScheduleByDoctorID(schedules);
				return scheduleModels;
				}else {
					return null;
				}
	}
}