package de.uerc.memberarea.security;

public class SecurityConstants {


	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTH_URLS = "/auth";
	public static final long PASSWORD_EXPIRY_DAYS = 90;
}