package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.MarkDown;

public interface IMarkDownDAO extends JpaRepository<MarkDown,Integer> {
	// get all users
		@Query(nativeQuery = true, value = "SELECT * FROM heroku_e703500be6bb113.markdown;")
		public List<MarkDown> getAllMarkDown();
		
		// get thong tin sv by masv
		@Query(value = "select * from `markdown` where doctorinfoid = :indoctorid", nativeQuery = true)
		public MarkDown findByDoctorInfoID(@Param("indoctorid") int indoctorid);
//		
//		@Query(value = "select * from `markdown` where doctorid = :indoctorid", nativeQuery = true)
//		public MarkDown findByDoctorID(@Param("indoctorid") int indoctorid);
//		
		@Query(value = "select * from `markdown` where specialty_id = :inspecialty_id", nativeQuery = true)
		public List<MarkDown> findBySpecialtyID(@Param("inspecialty_id") int inspecialty_id);
		
		
		
		// xoa sinh vien theo masv
		@Modifying
		@Query(value = "delete from `markdown` where doctorid =:indoctorid", nativeQuery = true)
		public void deleteMarkdown(@Param("indoctorid") int indoctorid);
}
