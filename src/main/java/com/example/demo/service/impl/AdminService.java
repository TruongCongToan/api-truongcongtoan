package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IAdminDAO;
import com.example.demo.entity.Admin;
import com.example.demo.model.AdminModel;
import com.example.demo.service.IAdminService;

@Service
public class AdminService implements IAdminService {
	@Autowired
	private IAdminDAO adminDAO;
	
//  ham tao ra users
			private List<AdminModel> getListAdminModels(List<Admin> admins) {
				List<AdminModel> adminModels = new ArrayList<>();
				for (Admin admin : admins) {
					AdminModel adminModel = new AdminModel();
					adminModel.setId(admin.getId());
					adminModel.setUsername(admin.getUsername());
					adminModel.setEmail(admin.getEmail());
					adminModel.setPassword(admin.getPassword());	
					adminModel.setPhonenumber(admin.getPhonenumber());
					adminModel.setImage(admin.getImage());
					adminModel.setActive(admin.getActive());
				
					adminModels.add(adminModel);
				}
				return adminModels;
			}
			//getall admin
	@Override
	public List<AdminModel> getListAdmin() throws SQLException {
		List<Admin> admins = adminDAO.getAllAdmin();
		List<AdminModel> adminModels = getListAdminModels(admins);
		return adminModels;
	}
	//edit admin
			@Override
			public void editAdmin(AdminModel adminModel, String inname) throws SQLException {
				if (adminDAO.findByName(inname) != null) {
					Admin admin = adminDAO.findByName(inname);
				
					System.out.println(adminModel);
					
					admin.setEmail(adminModel.getEmail());	
					admin.setEmail(adminModel.getEmail());	
					admin.setEmail(adminModel.getEmail());	
					admin.setImage(adminModel.getImage());
					admin.setActive(adminModel.getActive());
					
					adminDAO.saveAndFlush(admin);
				
//					if (!admin.getEmail().equals(adminModel.getEmail()) ) {
//						admin.setEmail(adminModel.getEmail());	
//		 			}
//					if (!admin.getPassword().equals(adminModel.getPassword()) ) {
//						admin.setEmail(adminModel.getEmail());	
//		 			}
//					if(!admin.getPhonenumber().equals(adminModel.getPhonenumber())) {
//					admin.setEmail(adminModel.getEmail());	
//		 			}
//					admin.setImage(adminModel.getImage());
//					admin.setActive(adminModel.getActive());

				}
				
			}
		
}
