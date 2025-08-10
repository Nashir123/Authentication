package com.tcs.hotelMgmt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.hotelMgmt.entity.UserEntity;
@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUsername(String username);
}
