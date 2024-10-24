package com.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auth.entity.AdminAllDetail;


@Repository
public interface AdminAllDetailRepository extends JpaRepository<AdminAllDetail,Integer>{
	
	@Query("SELECT u FROM AdminAllDetail u WHERE u.registerEmail = :email")
    List<AdminAllDetail> findAllAdminDetailsByEmail(@Param("email") String email);

}
