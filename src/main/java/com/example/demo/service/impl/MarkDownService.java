package com.example.demo.service.impl;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IMarkDownDAO;
import com.example.demo.entity.MarkDown;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.MarkDownModel;
import com.example.demo.service.IMarkDownSevice;
@Service
@Component
public class MarkDownService implements IMarkDownSevice  {
	@Autowired
	private IMarkDownDAO markDownDAO;
	
	@Autowired
	private DoctorInfoService doctorInfoService;

	@Override
	public List<MarkDown> getLisMarkDown() throws SQLException {
		List<MarkDown> markDowns = markDownDAO.getAllMarkDown();
		System.out.println("gia tri thu duoc "+markDowns);
		if (markDowns.isEmpty()) {
		throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
		}else {
			return markDowns;	
		}
	}

	@Override
	public MarkDown postInforDoctor(MarkDownModel markDownModel) throws SQLException {
	
		MarkDown markDown = new MarkDown();
		
			markDown.setDoctorInfo(doctorInfoService.getInforByDoctorID(markDownModel.getDoctorid()));
			markDown.setContentMarkDown(markDownModel.getContentMarkDown());
			markDown.setDescription(markDownModel.getDescription());

		return markDownDAO.save(markDown);
}
@Override
public MarkDown getMarkDownByDoctorID(int doctorID) throws SQLException {
	if (doctorID != 0) {
		
		MarkDown markDown = markDownDAO.findByDoctorInfoID(doctorID);
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
		
		if (markDown.getContentMarkDown() == null) {
			markDown.setContentMarkDown(markDownModel.getContentMarkDown());
		}else {
			if (!markDown.getContentMarkDown().equals(markDownModel.getContentMarkDown())) {
				markDown.setContentMarkDown(markDownModel.getContentMarkDown());
 			}
		}
//		if (markDown.getSpecialty_id() == null) {
//			markDown.setSpecialty_id(markDownModel.getSpecialty_id());
//		}else {
//			if (!markDown.getSpecialty_id().equals(markDownModel.getSpecialty_id())) {
//				markDown.setSpecialty_id(markDownModel.getSpecialty_id());
// 			}
//		}
		if (markDown.getDescription() == null) {
			markDown.setDescription(markDownModel.getDescription());
		}else {
			if (!markDown.getDescription().equals(markDownModel.getDescription())) {
				markDown.setDescription(markDownModel.getDescription());
 			}
		}
		if (markDown.getContentHTML() == null) {
			markDown.setContentHTML(markDownModel.getContentHTML());
		}else {
			if (!markDown.getContentHTML().equals(markDownModel.getContentHTML())) {
				markDown.setContentHTML(markDownModel.getContentHTML());
 			}
		}
		if (markDown.getClinic_id() == null) {
			markDown.setClinic_id(markDownModel.getClinic_id());
		}else {
			if (!markDown.getClinic_id().equals(markDownModel.getClinic_id())) {
				markDown.setClinic_id(markDownModel.getClinic_id());
 			}
		}

		 return markDownDAO.saveAndFlush(markDown);
	}else {
		throw new NotFoundException("Khong tim thay nguoi dung nay");
	}
}
//xoa mark down
	@Override
	public void deleteMarkdown(int markdown_id) throws SQLException {

		if (markDownDAO.findByDoctorInfoID(markdown_id) != null) {
		markDownDAO.deleteMarkdown(markdown_id);
		}
		else {
			throw new NotFoundException("Khong tim thay sinh vien nay");
		}
	}

	@Override
	public List<MarkDown> getMarkDownBySpecialtyID(int specialty_id) throws SQLException {
		if (specialty_id != 0) {
			List<MarkDown> markDown = markDownDAO.findBySpecialtyID(specialty_id);
			if (markDown.isEmpty()) {
				 throw new NotFoundException("Không tìm thấy thông tin trong danh sách !");
			}else {
				return markDown;
			}
		} else {
			 throw new NotFoundException("Dữ liệu nhập vào không được phép null !");
		}
	}
}
