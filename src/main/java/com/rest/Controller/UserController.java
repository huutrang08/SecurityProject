package com.rest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class UserController {
    @RequestMapping("/403")
	public String load() {
    	return "Bạn không có quyền thục hiện";
    }
	
}
