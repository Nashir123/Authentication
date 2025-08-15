package com.tcs.hotelMgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.hotelMgmt.entity.HotelMgmtEntity;
import com.tcs.hotelMgmt.service.HotelMgmtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
public class HotelMgmtController {
     
	@Autowired
	HotelMgmtService service;
	//Pagination to pass RequestParam dynamically
	@GetMapping("/getallrecord")
	public List<HotelMgmtEntity> getAllData(HttpServletRequest http,
			@RequestParam(value="pageNo" ,defaultValue="1",required=false) int pageNo,
			@RequestParam(value="pageSize" ,defaultValue="3",required=false) int pageSize
			)
	
	{
	    HttpSession session = http.getSession(true); // false means: don't create if it doesn't exist
	    if (session != null) {
	        System.out.println("Existing Session ID: " + session.getId());
	    } else {
	        System.out.println("No session exists");
	    }

		return service.getAllRecord(pageNo,pageSize);
	}
	
	@GetMapping("/getById/{id}")
	public HotelMgmtEntity getRecordById(@PathVariable int id)
	{
		return service.getRecordById(id);
	}
	
	@PostMapping("/saveRecord")
	public void saveRecord(@Valid @RequestBody HotelMgmtEntity entity)
	{
		service.saveRecord(entity);
	}
	
	@DeleteMapping("/deleteRecord/{id}")
	public void deleteRecord(@PathVariable int id)
	{
		service.deleteRecord(id);
	}
	
	@PostMapping("/updateRecordById/{id}")
	public void updateRecord(@RequestBody HotelMgmtEntity entity,@PathVariable int id)
	{
		service.updateRecord(entity, id);
	}
	
	//get csrf tken
	
	@GetMapping("/csrf")
	public CsrfToken getCsrf(HttpServletRequest http)
	{
		
		return (CsrfToken) http.getAttribute("_csrf");
	}
	
}
