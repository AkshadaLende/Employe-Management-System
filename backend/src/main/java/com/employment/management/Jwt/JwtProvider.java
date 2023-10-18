package com.employment.management.Jwt;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider implements Serializable {

private Key key;
public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;	
	@PostConstruct
	public void init() {
		key=Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}

	public String generateToken(Authentication authentication) {
		// TODO Auto-generated method stub
		User principal=(User) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(principal.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(key)
				.compact();
	}
	
    public boolean validateToken(String jwt, UserDetails userDetails) {
    	//Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
    	final String username = getUsernameFromJWT(jwt);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(jwt));
   
    }

	private boolean isTokenExpired(String jwt) {
		final Date expiration = getExpirationDateFromToken(jwt);
        return expiration.before(new Date());
	}

	private Date getExpirationDateFromToken(String jwt) {
		// TODO Auto-generated method stub
		return getClaimFromToken(jwt, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

	 private Claims getAllClaimsFromToken(String token) {
	        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	    }

	public String getUsernameFromJWT(String token) {
		// TODO Auto-generated method stub
		Claims claims=Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
				return claims.getSubject();
		
	}
}