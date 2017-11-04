package de.uerc.memberarea.models.users;

import org.springframework.security.core.Authentication;

public interface IClubUser extends Authentication {

    public Long getId();

    public UserType getUserType();

    public boolean isAccountExpired();

    public boolean isAccountLocked();

    public boolean isCredentialsExpired();

    public boolean isEnabled();

    public static enum UserType {
        ADMIN, MEMBER
    }

}
