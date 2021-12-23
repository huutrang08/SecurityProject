package com.rest.ResController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("edit")
public class RestControl {
	@GetMapping(name = "say")
	@ResponseBody
    public String edit() {
    	return"say hello";
    }
}
