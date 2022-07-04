package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Schedule;
import com.example.demo.model.ScheduleModel;

@Service
public interface IScheduleService {
	//get all schedule
	public List<Schedule> getListSchedule() throws SQLException;
	//delete schedule
	public List<Schedule> deleteSchedules(int inid,String indate,String intype) throws SQLException;
	//get schedule by doctorid
	public  List<ScheduleModel> getByDoctorID(int inid) throws SQLException;

}