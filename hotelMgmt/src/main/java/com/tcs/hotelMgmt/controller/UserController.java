package com.tcs.hotelMgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.hotelMgmt.entity.UserEntity;
import com.tcs.hotelMgmt.service.UserService;

@RestController
public class UserController {
   @Autowired
   UserService userSrevice;
   
	@PostMapping("/register")
	public UserEntity saveUser(@RequestBody UserEntity user)
	{
		
			return userSrevice.saveUser(user);
		
	}
}
