package de.uerc.memberarea.utils;

import static de.uerc.memberarea.security.SecurityConstants.EXPIRATION_TIME;
import static de.uerc.memberarea.security.SecurityConstants.SECRET;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

	public static String createToken(UserDetails userDetails) {
		String token = Jwts.builder().setSubject(userDetails.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
		return token;
	}

}
