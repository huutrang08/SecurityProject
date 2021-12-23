package com.rest.ResController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Entity.Users;
import com.rest.Services.UserServices;

@RestController
@RequestMapping("check")
public class RestControl {
	
	@Autowired
	UserServices userServices;
	
	
	@RequestMapping(value = "user", method = RequestMethod.GET,
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_ATOM_XML_VALUE
			}
			)
	@ResponseBody
	public List<Users> list(){
		return userServices.findAll();
	}
	
	@RequestMapping(value = "user", method = RequestMethod.POST,
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_ATOM_XML_VALUE
			}
			)
	@ResponseBody
	public Users save(@RequestBody Users users){
		return userServices.save(users);
	}
	
	@RequestMapping(value = "list/{username}",method = RequestMethod.GET)
	@ResponseBody
	public Users find(@PathVariable("username") String usernam) {
		return userServices.findByusername(usernam);
	}
	
	@RequestMapping(value = "list/{username}",method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("username") String usernam) {
		Users users = userServices.findByusername(usernam);
		if(users == null){
			return "User not exsist";
		}
			userServices.delete(users);
			return "delete success";
	}
}
