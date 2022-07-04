package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MarkDown;
import com.example.demo.model.MarkDownModel;
@Service
public interface IMarkDownSevice {
	//get list markdown
	public List<MarkDown> getLisMarkDown() throws SQLException;
	//post info doctor
	public MarkDown postInforDoctor(MarkDownModel markDownModel) throws SQLException;
	//tim theo ten
	public MarkDown getMarkDownByDoctorID(int doctorID) throws SQLException;
	
	//edit doctor info
	public MarkDown editDoctorInfo(MarkDownModel markDownModel, int doctorID) throws SQLException;

	//delete markdown
	public void deleteMarkdown (int markdown_id) throws SQLException;
}

