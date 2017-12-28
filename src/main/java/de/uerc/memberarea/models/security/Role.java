package de.uerc.memberarea.models.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 6158500706533868054L;

    @ManyToMany
    private final List<Operation> allowedOperations = new ArrayList<>();

    @Id
    private String id;

    public Collection<Operation> getAllowedOperations() {
        return allowedOperations;
    }

    @Override
    public String getAuthority() {
        return id;
    }

}
