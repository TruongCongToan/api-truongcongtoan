package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.DAO.IStudentDAO;
import com.example.demo.entity.Sinhvien;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.SinhvienModel;
import com.example.demo.service.IStudentService;

@Service
public class StudentService implements IStudentService {
	@Autowired
	private IStudentDAO studentDAO;

	// lay tat ca sinh vien
	@Override
	public List<SinhvienModel> getListStudent() throws SQLException {
		// List<Sinhvien> sinhviens = studentDAO.findAll();
		List<Sinhvien> sinhviens = studentDAO.getAllSinhviens();
		List<SinhvienModel> sinhvienModels = getListSinhvienModels(sinhviens);
		return sinhvienModels;
	}
	// lay thong tin sinh vien bang masv

	@Override
	public Sinhvien getSinhvienByMaSV(String inmasv) throws SQLException {
		if (inmasv != null) {
			Sinhvien sinhvien = studentDAO.findByMasv(inmasv);
			if (sinhvien != null) {
				return sinhvien;
			}else {
				   throw new NotFoundException("Khong tim thay sinh vien nay");
			}
		} else {
			throw new NotFoundException("Khong tim thay sinh vien nay");
		}
	}

//xoa sinh vien
	@Override
	public void deleteSinhVien(String masv) throws SQLException {

		if (studentDAO.findByMasv(masv) != null) {
			studentDAO.deleteSinhVien(masv);
		}
		else {
			throw new NotFoundException("Khong tim thay sinh vien nay");
		}
	}

//them moi sinh vien
	@Override
	public Sinhvien addSinhvien(SinhvienModel sinhvienModel) throws SQLException {

		if (studentDAO.findByMasv(sinhvienModel.getMasv()) == null) {
			Sinhvien sinhvien = new Sinhvien();

			sinhvien.setHodem(sinhvienModel.getHodem());
			sinhvien.setTen(sinhvienModel.getTen());
			sinhvien.setDob(sinhvienModel.getDob());
			sinhvien.setGioitinh(sinhvienModel.getGioitinh());
			sinhvien.setTinh(sinhvienModel.getTinh());
			sinhvien.setMalop(sinhvienModel.getMalop());
			sinhvien.setMasv(sinhvienModel.getMasv());
			return studentDAO.save(sinhvien);
		}
		else {
			 throw new DuplicateRecordException("Da co sinh vien nay trong danh sach");
		}
		
	}

//sua thong tin sinh vien theo masv
	@Override
	public void editSinhvien(SinhvienModel sinhvienModel, String masv) throws SQLException {
		if (studentDAO.findByMasv(masv) != null) {
			Sinhvien sinhvien = studentDAO.findByMasv(masv);
			if (!sinhvien.getHodem().equals(sinhvienModel.getHodem()) && sinhvien.getHodem() != null) {
				sinhvien.setHodem(sinhvienModel.getHodem());
			}
			if (!sinhvien.getTen().equals(sinhvienModel.getTen()) && sinhvien.getTen() != null) {
				sinhvien.setTen(sinhvienModel.getTen());
			}
			if (!sinhvien.getDob().equals(sinhvienModel.getDob()) &&sinhvien.getDob() != null) {
				sinhvien.setDob(sinhvienModel.getDob());
			}
			if (!sinhvien.getGioitinh().equals(sinhvienModel.getGioitinh()) && sinhvien.getGioitinh() != null) {
				sinhvien.setGioitinh(sinhvienModel.getGioitinh());
			}
			if (!sinhvien.getTinh().equals(sinhvienModel.getTinh()) && sinhvien.getTinh() != null) {
				sinhvien.setTinh(sinhvienModel.getTinh());
			}
			if (!sinhvien.getMalop().equals(sinhvienModel.getMalop()) && sinhvien.getMalop() != null) {
				sinhvien.setMalop(sinhvienModel.getMalop());
			}
			studentDAO.saveAndFlush(sinhvien);
		}else {
			throw new NotFoundException("Khong tim thay sinh vien nay");
		}

	}
//get sinh vien nu
	@Override
	public List<SinhvienModel> getListStudentByMalop(String malop) throws SQLException {
		if (malop != null) {
			List<Sinhvien> sinhviens = studentDAO.findSvNu(malop);
			List<SinhvienModel> sinhvienModels = getListSinhvienModels(sinhviens);
			return sinhvienModels;
		} else {
			return null;
		}
	}

	// ham lay ra sinh vien
	private List<SinhvienModel> getListSinhvienModels(List<Sinhvien> sinhviens) {
		List<SinhvienModel> sinhvienModels = new ArrayList<>();
		for (Sinhvien sinhvien : sinhviens) {
			SinhvienModel sinhvienModel = new SinhvienModel();
			sinhvienModel.setId(sinhvien.getId());
			sinhvienModel.setHodem(sinhvien.getHodem());
			sinhvienModel.setTen(sinhvien.getTen());
			sinhvienModel.setDob(sinhvien.getDob());
			sinhvienModel.setGioitinh(sinhvien.getGioitinh());
			sinhvienModel.setTinh(sinhvien.getTinh());
			sinhvienModel.setMalop(sinhvien.getMalop());
			sinhvienModel.setMasv(sinhvien.getMasv());
			sinhvienModels.add(sinhvienModel);
		}
		return sinhvienModels;
	}
	


}