package com.tcs.hotelMgmt.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcs.hotelMgmt.entity.UserEntity;
import com.tcs.hotelMgmt.entity.UserPrinciple;
import com.tcs.hotelMgmt.repo.UserRepo;

@Service
public class UserService implements UserDetailsService{
  @Autowired
  UserRepo userRepo;
  
  @Autowired
  JWTService jwtService;
  
   @Autowired
   @Lazy
  AuthenticationManager authManager;
  
  BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user=userRepo.findByUsername(username);
		//kyonki UserPrinciple Internally implements UserDetails(UserDetails is a interface)
		return new UserPrinciple(user);
	}
	
   public UserEntity saveUser(UserEntity user)
   {
	   //it will store in data base in encripted form 
	   
	   user.setPassword(encoder.encode(user.getPassword()));
	   
	   userRepo.save(user);
	   return user;
   }

    public String variefy(UserEntity user) {
    	System.out.println("userName = "+user.getUsername()+" Password = "+user.getPassword());
    	Authentication authentication= authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    	System.out.println("after get password");
    	if(authentication.isAuthenticated()==true)
    	{
    		return jwtService.GenerateToken(user);
    	}
    	else
    		return "fails";
    }
   
   
}
