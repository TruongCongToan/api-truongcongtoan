package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.MarkDown;

public interface IMarkDownDAO extends JpaRepository<MarkDown,Integer> {
	// get all users
		@Query(nativeQuery = true, value = "select * from markdown")
		public List<MarkDown> getAllMarkDown();
		
		// get thong tin sv by masv
		@Query(value = "select * from markdown where markdown_id = :markdown_id", nativeQuery = true)
		public MarkDown findByMarkDownID(@Param("markdown_id") int markdown_id);
		
		// xoa sinh vien theo masv
		@Modifying
		@Query(value = "delete from markdown where markdown_id =:markdown_id", nativeQuery = true)
		public void deleteMarkdown(@Param("markdown_id") int markdown_id);
}
