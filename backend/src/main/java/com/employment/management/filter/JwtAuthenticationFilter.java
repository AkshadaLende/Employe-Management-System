package com.employment.management.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employment.management.Jwt.JwtProvider;
import com.employment.management.serviceImpl.UserDetailsServiceImpl;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
	private JwtProvider jwtProvider;
    
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String authorization = request.getHeader("Authorization");
        String Token = null;
        String userName = null;

        if(null != authorization && authorization.startsWith("Bearer ")) {
            Token = authorization.substring(7);
            System.out.println("JWTTT :"+Token);
            userName = jwtProvider.getUsernameFromJWT(Token);
        }
		
		 if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails
	                    = userDetailsServiceImpl.loadUserByUsername(userName);

	            if(jwtProvider.validateToken(Token,userDetails)) {
	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
	                        = new UsernamePasswordAuthenticationToken(userDetails,
	                        null, userDetails.getAuthorities());

	                usernamePasswordAuthenticationToken.setDetails(
	                        new WebAuthenticationDetailsSource().buildDetails(request)
	                );

	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }

	        }
		
		filterChain.doFilter(request, response);
	}

	/*
	 * private String getJwtFromRequest(HttpServletRequest request) { String
	 * BearerToken= request.getHeader("Authorization");
	 * if(StringUtils.hasText(BearerToken) && BearerToken.startsWith("Bearer ") ) {
	 * return BearerToken.substring(7); } return BearerToken; // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */	

}
