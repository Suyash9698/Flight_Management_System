package com.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auth.entity.AdminCurrentlyLogged;
import com.auth.entity.AdminDetail;

public interface AdminCurrentlyLoggedRepository extends JpaRepository<AdminCurrentlyLogged,Integer>{
	
	@Query("SELECT ac from AdminCurrentlyLogged ac")
	public List<AdminCurrentlyLogged> findCurrentAdmin();

}
