package de.uerc.memberarea.models.users;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;

import de.uerc.memberarea.models.SocialClub;

@Entity
public class ClubAdmin implements IClubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private SocialClub socialClub;

    private String username;

    private String password;

    @Override
    public String getName() {
        return this.socialClub.getHost() + "/" + this.getUserType() + "/" + this.username;
    }

    @Override
    public IClubUser.UserType getUserType() {
        return UserType.ADMIN;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isAccountExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

}
