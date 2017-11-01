package de.uerc.memberarea.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

import de.uerc.memberarea.json.View;

@Entity
public class SocialClub extends TimestampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(value = View.Nested.class)
    private Long id;
    
    @JsonView(value = View.Nested.class)
    private String name;
    
}
