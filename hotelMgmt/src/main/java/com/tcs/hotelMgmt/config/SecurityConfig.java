package com.tcs.hotelMgmt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //Autowiring
	@Autowired
	UserDetailsService userDetailsService;
	
	 @Bean 
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	 {
		 return
		 http
		 .csrf(customizer->customizer.disable())
		 .authorizeHttpRequests(request->request.anyRequest().authenticated())
		 .httpBasic(cutomizer->Customizer.withDefaults())
		 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		 .build();
		 
	 }
	 
	 
	 //This is used for the user available in data base so it is not manually
	 @Bean
	 public AuthenticationProvider authenticationProvider()
	 {
		 //It internally implements AuthenticationProvider
		 DaoAuthenticationProvider provider=new  DaoAuthenticationProvider();
		 //for sending Password normal but it will store in data base in encripted form 
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		// provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		 provider.setUserDetailsService(userDetailsService);
		 return provider;
	 }
	 
	 
	 
	 //creating user,password for different user  
	 //But that is hardcoded 
	// @Bean
//	 public UserDetailsService userDetails()
//	 {
//		 //Basically UserDetailsService is a interface for interface ca not have object
//		 //for Creacting UserDetails(it is class)  onbject it internaly implemnents UserDetailsService Interface
//		 UserDetails user1= User.withDefaultPasswordEncoder().
//				 username("Nashir")
//				 .password("N@123")
//				 .roles("Admin")
//				 .build();
//		 
//		 UserDetails user2= User.withDefaultPasswordEncoder()
//				 .username("SHAHID")
//				 .password("S@123")
//				 .roles("USER")
//				 .build();
//		 
//		 return new InMemoryUserDetailsManager(user1,user2);
//		 
//	 }
}
