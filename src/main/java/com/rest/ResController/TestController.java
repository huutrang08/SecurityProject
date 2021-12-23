package com.rest.ResController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Entity.MyUserDetails;
import com.rest.Entity.Users;
import com.rest.Services.JwtServices;
import com.rest.Services.UserServices;

@RestController
@RequestMapping("rest")
public class TestController {
	
	@Autowired
	UserServices userServices;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtServices jwtServices;
    @GetMapping("index")
    @ResponseBody
	public String get(){
        return "user/index";
    }
    
    @GetMapping(value = "list",produces = MediaType.APPLICATION_JSON_VALUE)
    public Users load() {
    	return userServices.findByusername("admin");
    }
    
    @PostMapping("login")
    @ResponseBody
    public String authenticateUser(@RequestBody Users loginRequest) {
      Authentication authentication = authenticationManager.authenticate(
    		  new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    		  SecurityContextHolder.getContext().setAuthentication(authentication);
    		  String jwt = jwtServices.generateTokenLogin(authentication);
    		  UserDetails userDetails = (UserDetails) authentication.getCredentials();
    		  
    		  Users users = userServices.findByusername(loginRequest.getUsername());
    		  if(jwt == null){
				  return "login fail";
			  }
    		  return jwt;
    }
}
