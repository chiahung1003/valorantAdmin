package com.valorant.login.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author KenLi
 * @date 2019-06-26
 */
@Controller
@Log4j2
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}


	@GetMapping(value = { "/", "/index" })
	public String root() {
		return "index";
	}
	
}
