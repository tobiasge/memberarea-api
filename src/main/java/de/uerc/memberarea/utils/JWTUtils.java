package de.uerc.memberarea.utils;

import static de.uerc.memberarea.security.SecurityConstants.EXPIRATION_TIME;
import static de.uerc.memberarea.security.SecurityConstants.SECRET;
import static de.uerc.memberarea.security.SecurityConstants.TOKEN_PREFIX;

import java.util.Date;

import de.uerc.memberarea.models.users.IClubUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

    private static final String USER_TYPE_KEY = "userType";

    public static String createToken(IClubUser clubUser) {
        String token = Jwts.builder().setSubject(clubUser.getId().toString())
            .setHeaderParam(USER_TYPE_KEY, clubUser.getUserType().toString())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
        return token;
    }

    public static Long extractIdFromToken(String token) {
        return Long.valueOf(readToken(token).getBody().getSubject());
    }

    @SuppressWarnings("unchecked")
    public static IClubUser.UserType extractUserTypeFromToken(String token) {
        String userType = readToken(token).getHeader().getOrDefault(USER_TYPE_KEY, IClubUser.UserType.MEMBER.toString())
            .toString();
        return IClubUser.UserType.valueOf(IClubUser.UserType.class, userType);
    }

    private static Jws<Claims> readToken(String token) {
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
    }

}
