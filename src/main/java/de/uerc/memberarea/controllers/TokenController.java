package de.uerc.memberarea.controllers;

import static de.uerc.memberarea.utils.JWTUtils.createToken;

import java.util.Optional;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uerc.memberarea.models.LoginUser;
import de.uerc.memberarea.models.Token;
import de.uerc.memberarea.models.base.ClubUser;
import de.uerc.memberarea.models.base.ClubUser.UserType;
import de.uerc.memberarea.service.UserService;

@RestController
@RequestMapping(path = "/auth")
public class TokenController {

    @Autowired
    private UserService userService;

    @Autowired
    private StandardPasswordEncoder standardPasswordEncoder;

    @PostMapping(path = "/r")
    public Token refreshToken(@RequestBody Token token) {
        throw new NotYetImplementedException();
    }

    @PostMapping(path = "/v")
    public Token verifyToken(@RequestBody Token token) {
        throw new NotYetImplementedException();
    }

    @PostMapping(path = "/?")
    public Token login(@RequestBody LoginUser loginUser, @RequestHeader(name = "Host") String host) {

        ClubUser.UserType userType = UserType.valueOf(loginUser.getUserType());

        Optional<? extends ClubUser> optIClubUser = userService.findUserByUsername(loginUser.getUsername(), userType);
        if (!optIClubUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        ClubUser iClubUser = optIClubUser.get();

        if (this.standardPasswordEncoder.matches(loginUser.getPassword(), iClubUser.getCredentials().toString())
            && this.accountOk(iClubUser)) {
            String token = createToken(iClubUser);
            return new Token(token);
        }
        throw new BadCredentialsException("Username or Password");
    }

    private boolean accountOk(ClubUser iClubUser) {
        if (!iClubUser.isEnabled()) {
            throw new DisabledException("Account is disabled");
        }
        if (iClubUser.isAccountLocked()) {
            throw new LockedException("Account is locked");
        }
        if (iClubUser.isAccountExpired()) {
            throw new AccountExpiredException("Account is expired");
        }

        return true;
    }

}
