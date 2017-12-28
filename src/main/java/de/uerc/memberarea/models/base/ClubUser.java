package de.uerc.memberarea.models.base;

import static de.uerc.memberarea.security.SecurityConstants.PASSWORD_EXPIRY_DAYS;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.uerc.memberarea.models.security.Role;

@MappedSuperclass
public abstract class ClubUser extends TimestampedEntity implements Authentication {

    private static final long serialVersionUID = 8872143177072080892L;

    private boolean enabled;

    private boolean locked;

    @JsonIgnore
    private String password;

    private LocalDate passwordChangedAt;

    @ManyToMany
    private final List<Role> roles = new ArrayList<>();

    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(role);
            authorities.addAll(role.getAllowedOperations());
        }
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    public abstract Long getId();

    public String getName() {
        return this.getUserType() + "/" + this.username;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public abstract UserType getUserType();

    public abstract boolean isAccountExpired();

    public boolean isAccountLocked() {
        return this.locked;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    public boolean isCredentialsExpired() {
        return this.passwordChangedAt.isBefore(LocalDate.now().minus(PASSWORD_EXPIRY_DAYS, ChronoUnit.DAYS));
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    public static enum UserType {
        ADMIN, MEMBER
    }
}
