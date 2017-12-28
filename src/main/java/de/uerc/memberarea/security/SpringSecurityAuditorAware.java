package de.uerc.memberarea.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import de.uerc.memberarea.models.users.ClubMember;

public class SpringSecurityAuditorAware implements AuditorAware<ClubMember> {

    @Override
    public Optional<ClubMember> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable((ClubMember) authentication);
    }

}
