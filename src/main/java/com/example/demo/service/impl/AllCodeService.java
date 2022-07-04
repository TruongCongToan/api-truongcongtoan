package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IAllCodeDAO;
import com.example.demo.entity.AllCode;
import com.example.demo.model.AllCodeModel;
import com.example.demo.service.IAllCodeService;

@Service
public class AllCodeService implements IAllCodeService {
	@Autowired
	private IAllCodeDAO allCodeDAO;
	
	// ham lay ra users
	private List<AllCodeModel> getListAllCodeServiceModels(List<AllCode> allCodes) {
		List<AllCodeModel> allCodeModels = new ArrayList<>();
		for (AllCode allCode : allCodes) {
			AllCodeModel allCodeModel = new AllCodeModel();
			allCodeModel.setId(allCode.getId());
			allCodeModel.setKey(allCode.getKey());
			allCodeModel.setType(allCode.getType());
			allCodeModel.setValueen(allCode.getValueEn());;
			allCodeModel.setValuevi(allCode.getValuevi());
			allCodeModels.add(allCodeModel);
			
		}
		return allCodeModels;
	}
	
	@Override
	public List<AllCodeModel> getListAllCodeService() throws SQLException {
		List<AllCode> allCodes = allCodeDAO.getAllCodeService();
		List<AllCodeModel> allCodeModels = getListAllCodeServiceModels(allCodes);
		return allCodeModels;
	}

	@Override
	public List<AllCodeModel> getAllCodeByType(String type) throws SQLException {
		if (type != null) {
			List<AllCode> allCodes = allCodeDAO.findByType(type);
			List<AllCodeModel> allCodeModels = getListAllCodeServiceModels(allCodes);
			return allCodeModels;
		} else {
			return null;
		}
	}

	

}
