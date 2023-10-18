package com.employment.management.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employment.management.Jwt.JwtProvider;
import com.employment.management.entity.LoginRequest;
import com.employment.management.model.User;
import com.employment.management.repository.UserRepository;
import com.employment.management.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
	 @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    AuthenticationManager authenticationManager;
	    @Autowired
	    JwtProvider jwtProvider;
	    @Autowired
	    UserDetailsServiceImpl userDetailsServiceImpl;
	   

	@Override
	public String checkLogin(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
				Authentication authentication =	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				//System.out.println(jwtProvider.generateToken(authentication));;
				final UserDetails userDetails
                = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUsername());

        final String token =
        		jwtProvider.generateToken(authentication);
				return token;
	}

	@Override
	public void signup(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUserName(loginRequest.getUsername());
		user.setEmail(loginRequest.getEmail());
		user.setPassword(encodePassword(loginRequest.getPassword()));
		userRepository.save(user);
		
	}
	
	 private String encodePassword(String password) {
	    	return passwordEncoder.encode(password);
	    }

}
