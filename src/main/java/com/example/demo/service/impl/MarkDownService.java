package com.example.demo.service.impl;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IMarkDownDAO;
import com.example.demo.entity.MarkDown;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.MarkDownModel;
import com.example.demo.service.IMarkDownSevice;
@Service
public class MarkDownService implements IMarkDownSevice  {
	@Autowired
	private IMarkDownDAO markDownDAO;
//	
//	//  ham lay ra users
//	private List<MarkDownModel> getListMarkDownModel(List<MarkDown> markDowns) {
//		List<MarkDownModel> markDownModels= new ArrayList<>();
//		for (MarkDown markDown: markDowns) {
//			MarkDownModel markDownModel = new MarkDownModel();
//			
//			markDownModel.setMarkdown_id(markDown.getMarkdown_id());
//			markDownModel.setContentHTML(markDown.getContentHTML());
//			markDownModel.setContentMarkDown(markDown.getContentMarkDown());
//			markDownModel.setDescription(markDown.getDescription());
////			markDownModel.setDoctorID(markDown.getDoctorID());
//			
//			markDownModel.setClinicID(markDown.getClinicID());
//			markDownModel.setSpecialtyID(markDown.getSpecialtyID());
//			
//			markDownModels.add(markDownModel);
//		}
//		return markDownModels;
//	}

	//get all markdown
	@Override
	public List<MarkDown> getLisMarkDown() throws SQLException {
		List<MarkDown> markDowns = markDownDAO.getAllMarkDown();
//		List<MarkDownModel> markDownModels = getListMarkDownModel(markDowns);
		return markDowns;
	}

	//post doctor infor
	@Override
	public MarkDown postInforDoctor(MarkDownModel markDownModel) throws SQLException {
	
		MarkDown markDown = new MarkDown();
		markDown.setMarkdown_id(markDownModel.getMarkdown_id());

		if (markDownDAO.findByMarkDownID(markDownModel.getMarkdown_id()) != null) {
			return editDoctorInfo(markDownModel,markDownModel.getMarkdown_id());
		}else {
			System.out.println(" null");

			markDown.setContentHTML(markDownModel.getContentHTML());
			markDown.setContentMarkDown(markDownModel.getContentMarkDown());
			markDown.setDescription(markDownModel.getDescription());
			markDown.setUser(markDownModel.getUsers());

			System.out.println("markdown "+markDown);

		}
		return markDownDAO.save(markDown);
}
//get markdow by doctorID

@Override
public MarkDown getMarkDownByDoctorID(int doctorID) throws SQLException {
	if (doctorID != 0) {
		

		MarkDown markDown = markDownDAO.findByMarkDownID(doctorID);
		if (markDown != null) {
			return markDown;
		}else {
			 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}
	} else {
		 throw new NotFoundException("Dữ liệu nhập vào không được phép null !");
	}
}

//edit markdown
@Override
public MarkDown editDoctorInfo(MarkDownModel markDownModel, int markdown_id) throws SQLException {
	
	if (getMarkDownByDoctorID(markdown_id) != null) {
		MarkDown markDown = getMarkDownByDoctorID(markdown_id);
		
		markDown.setContentMarkDown(markDownModel.getContentMarkDown());
		markDown.setSpecialtyID(markDownModel.getSpecialtyID());
		markDown.setDescription(markDownModel.getDescription());
		markDown.setContentHTML(markDownModel.getContentHTML());
		markDown.setClinicID(markDownModel.getClinicID());
		markDown.setUser(markDownModel.getUsers());

		
//		if (!markDown.getContentHTML().equals(markDownModel.getContentHTML()) ) {
//			markDown.setContentHTML(markDownModel.getContentHTML());	
//			}
//		if (!markDown.getContentMarkDown().equals(markDownModel.getContentMarkDown())) {
//			markDown.setContentMarkDown(markDownModel.getContentMarkDown());
//		}
//		if (!markDown.getDescription().equals(markDownModel.getDescription())) {
//			markDown.setDescription(markDownModel.getDescription());
//		}
//		if (markDown.getSpecialtyID() != (markDownModel.getSpecialtyID())) {
//			markDown.setSpecialtyID(markDownModel.getSpecialtyID());
//
//		}
//		if (markDown.getClinicID() != (markDownModel.getClinicID())) {
//			markDown.setClinicID(markDownModel.getClinicID());
//		}
//		if (markDown.getUser() != null) {
//			markDown.setUser(markDownModel.getUsers());
//		}
//		if (markDown.getDoctorID() != markDownModel.getDoctorID() &&markDown.getDoctorID()!=0) {
//			markDown.setDoctorID(markDownModel.getDoctorID());
//		}
		 return markDownDAO.saveAndFlush(markDown);
	}else {
		throw new NotFoundException("Khong tim thay nguoi dung nay");
	}
}
//xoa mark down
	@Override
	public void deleteMarkdown(int markdown_id) throws SQLException {

		if (markDownDAO.findByMarkDownID(markdown_id) != null) {
//			System.out.println(markDownDAO.findByDoctorID(markdown_id) );
			markDownDAO.deleteMarkdown(markdown_id);
			

		}
		else {
			throw new NotFoundException("Khong tim thay sinh vien nay");
		}
	}



}
