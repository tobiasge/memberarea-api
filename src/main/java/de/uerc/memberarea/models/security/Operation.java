package de.uerc.memberarea.models.security;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Operation implements GrantedAuthority {

    private static final long serialVersionUID = 6859526943604245960L;

    @Id
    private String id;

    @Override
    public String getAuthority() {
        return id;
    }

}