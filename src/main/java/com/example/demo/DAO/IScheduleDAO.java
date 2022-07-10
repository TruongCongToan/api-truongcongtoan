package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Schedule;

@Repository
@Transactional
public interface IScheduleDAO extends CrudRepository<Schedule, Integer> {
	//get all schedules
	@Query(nativeQuery = true, value = "select * from `schedules`")
	public List<Schedule> getAllSchedule();
	//delete schedule
	@Modifying
	@Query(value = "delete from `schedules` where doctorid =:inid", nativeQuery = true)
	public void deleteSchedule(@Param("inid") int inid);
			
	
	//get list schedule by doctorid
	@Query(nativeQuery = true, value = "SELECT * FROM `schedules` WHERE doctorid = :inid")
	public List<Schedule> getScheduleByDoctorID(@Param("inid") int inid);
	
	//get schedule by date
	@Query(nativeQuery = true, value = "SELECT * FROM schedules WHERE doctorid = :inid and date = :indate")
	public List<Schedule> getScheduleByDate(@Param("inid") int inid,@Param("indate") Long inDate);
	

}