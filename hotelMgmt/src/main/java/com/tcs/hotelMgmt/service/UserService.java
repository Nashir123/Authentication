package com.tcs.hotelMgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tcs.hotelMgmt.entity.UserEntity;
import com.tcs.hotelMgmt.entity.UserPrinciple;
import com.tcs.hotelMgmt.repo.UserRepo;

@Service
public class UserService implements UserDetailsService{
  @Autowired
  UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user=userRepo.findByUsername(username);
		//kyonki UserPrinciple Internally implements UserDetails(UserDetails is a interface)
		return new UserPrinciple(user);
	}
  
}
