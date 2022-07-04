package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Doctors;

public interface IDoctorDAO extends JpaRepository<Doctors,Integer> {

}
