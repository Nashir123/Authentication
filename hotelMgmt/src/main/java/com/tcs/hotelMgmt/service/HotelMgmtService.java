package com.tcs.hotelMgmt.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.tcs.hotelMgmt.entity.HotelMgmtEntity;
import com.tcs.hotelMgmt.repo.HotelMgmtRepo;
@Service
public class HotelMgmtService {

     @Autowired
     HotelMgmtRepo repo;
     
    public List<HotelMgmtEntity> getAllRecord(){
    	 return repo.findAll();
     }
     
   public  HotelMgmtEntity getRecordById(int id)
     {
    	 return repo.findById(id).get();
     }
    
  public   void saveRecord( HotelMgmtEntity entity)
     {
    	 repo.save(entity);
     }
     
   public  void deleteRecord(int id)
     {
    	 repo.deleteById(id);
     }
     
   public  void updateRecord(HotelMgmtEntity entity ,int id)
     {
    	 HotelMgmtEntity newEntity=repo.findById(id).get();
    	 newEntity.setName(entity.getName());
    	 newEntity.setPhone(entity.getPhone());
    	 repo.save(newEntity);
     }
    
}
