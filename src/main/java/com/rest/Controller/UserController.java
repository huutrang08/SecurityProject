package com.rest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class UserController {
    @RequestMapping("new")
	public String load() {
    	return "user/index";
    }
	
}
