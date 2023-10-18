package com.employment.management.serviceImpl;

import java.util.Collection;
import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employment.management.model.User;
import com.employment.management.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired 
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	User user=	userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("No User Found" +username));
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthenties("ROLE_USER"));
	}
	private Collection<? extends GrantedAuthority> getAuthenties(String role_user) {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority(role_user));
	}

}
