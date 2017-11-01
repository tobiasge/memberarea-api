package de.uerc.memberarea.controllers;

import static de.uerc.memberarea.utils.JWTUtils.createToken;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uerc.memberarea.models.LoginUser;
import de.uerc.memberarea.models.Token;
import de.uerc.memberarea.service.UserService;

@RestController
@RequestMapping(path = "/auth")
public class TokenController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping(path = "/r")
	public Token refreshToken(@RequestBody Token token) {
		throw new NotYetImplementedException();
	}

	@PostMapping(path = "/v")
	public Token verifyToken(@RequestBody Token token) {
		throw new NotYetImplementedException();
	}

	@PostMapping(path = "/")
	public Token login(@RequestBody LoginUser loginUser) {
		UserDetails userDetails = userService.loadUserByUsername(loginUser.getUsername());
		if (this.bCryptPasswordEncoder.matches(loginUser.getPassword(), userDetails.getPassword())
				&& this.accountOk(userDetails)) {
			String token = createToken(userDetails);
			return new Token(token);
		}
		throw new BadCredentialsException("Username or Password");
	}

	private boolean accountOk(UserDetails userDetails) {
		if (!userDetails.isEnabled()) {
			throw new DisabledException("Account is disabled");
		}
		if (!userDetails.isAccountNonLocked()) {
			throw new LockedException("Account is locked");
		}
		if (!userDetails.isAccountNonExpired()) {
			throw new AccountExpiredException("Account is expired");
		}

		return true;
	}

}
