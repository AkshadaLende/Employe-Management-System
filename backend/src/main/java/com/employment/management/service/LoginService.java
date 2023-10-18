package com.employment.management.service;

import com.employment.management.entity.LoginRequest;
import com.employment.management.model.User;

public interface LoginService{

	String checkLogin(LoginRequest loginRequest);

	void signup(LoginRequest loginRequest);

}
