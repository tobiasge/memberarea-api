package de.uerc.memberarea.models.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.uerc.memberarea.models.base.ClubUser;

@Entity
public class ClubAdmin extends ClubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    private String password;

    private String username;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.getUserType() + "/" + this.username;
    }

    @Override
    public ClubUser.UserType getUserType() {
        return UserType.ADMIN;
    }

    @Override
    public boolean isAccountExpired() {
        // TODO Auto-generated method stub
        return false;
    }

}
