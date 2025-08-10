package com.tcs.hotelMgmt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.hotelMgmt.entity.HotelMgmtEntity;
@Repository
public interface HotelMgmtRepo extends JpaRepository<HotelMgmtEntity, Integer> 
{
   
}
