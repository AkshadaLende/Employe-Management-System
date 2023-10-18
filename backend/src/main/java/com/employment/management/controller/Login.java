package com.employment.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employment.management.entity.LoginRequest;
import com.employment.management.service.LoginService;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/login")
public class Login {
	@Autowired
	LoginService loginService;
    
	@RequestMapping("/register")
	    public ResponseEntity signup(@RequestBody LoginRequest loginRequest) {
		System.out.println("Enter into registraion process");
		 loginService.signup(loginRequest);
	        return new ResponseEntity(HttpStatus.OK);
	    }
	
	
	@RequestMapping("/authenticate")
	public String userLogin(@RequestBody LoginRequest loginRequest) {
	String token=	loginService.checkLogin(loginRequest);
	System.out.println(token);
		 return token;
		
	}
}
