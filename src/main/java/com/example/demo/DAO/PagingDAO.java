package com.example.demo.DAO;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;
@Repository
@Transactional
public interface PagingDAO extends JpaRepository<Users, Integer>{
//	@Query(value = "SELECT * FROM users WHERE role ='R2' ORDER BY ?#{#pageable}",
	@Query(  value = "SELECT * FROM users WHERE role='R2' ORDER BY username",
		       countQuery = "SELECT count(*) FROM users WHERE role = 'R2'",
		       nativeQuery = true)
		   Page<Users> findByRole( Pageable pageable);

}
