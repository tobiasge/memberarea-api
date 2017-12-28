package de.uerc.memberarea.security;

import static de.uerc.memberarea.security.SecurityConstants.HEADER_STRING;
import static de.uerc.memberarea.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import de.uerc.memberarea.models.base.ClubUser;
import de.uerc.memberarea.service.UserService;
import de.uerc.memberarea.utils.JWTUtils;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private UserService userService;

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        Authentication authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            
            Long clubUserId = JWTUtils.extractIdFromToken(token);
            ClubUser.UserType userType = JWTUtils.extractUserTypeFromToken(token);

            Optional<? extends ClubUser> optIClubUser = this.userService.findById(clubUserId, userType);

            if (optIClubUser.isPresent()) {
                return optIClubUser.get();
            }
            return null;
            
        }
        return null;
    }

}