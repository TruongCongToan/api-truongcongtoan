package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sinhvien;

@Repository
@Transactional
public interface IStudentDAO extends JpaRepository<Sinhvien, Integer> {
	// get all sinh vien
	@Query(nativeQuery = true, value = "select * from sinhvien order by hodem,ten")
	public List<Sinhvien> getAllSinhviens();
	
	// get thong tin sv by masv
	@Query(value = "select * from sinhvien where masv = :inmasv", nativeQuery = true)
	public Sinhvien findByMasv(@Param("inmasv") String inmasv);

	// xoa sinh vien theo masv
	@Modifying
	@Query(value = "delete from sinhvien where masv =:inmasv", nativeQuery = true)
	public void deleteSinhVien(@Param("inmasv") String inmasv);
	
	//lay danh sach sinh vien nu
	@Modifying
	@Query(value = "SELECT * FROM sinhvien WHERE gioitinh ='Nu' and malop =:inmalop order by ten,hodem",nativeQuery = true)
	public List<Sinhvien> findSvNu(@Param("inmalop") String inmalop);
	
	

}
