package com.employment.management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employment.management.filter.JwtAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired 
	private UserDetailsService userDetailsService;
	 @Autowired
	 private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	 @Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		  @Bean
		  @Override 
		  public AuthenticationManager authenticationManagerBean() throws
		  Exception { return super.authenticationManagerBean(); }
		 
	 @Override
	 public void configure(AuthenticationManagerBuilder authrnticationManagerBuilder)throws Exception {
		//Database Auth
		 authrnticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	 }
	 
	 @Override
		public void configure(HttpSecurity http) throws Exception  {
		 http.csrf().disable().authorizeRequests()
		 
	     .antMatchers("/login/**" ,"/api/**")
	     .permitAll()
	     .anyRequest()
	     .authenticated().and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	 }
	
	}
