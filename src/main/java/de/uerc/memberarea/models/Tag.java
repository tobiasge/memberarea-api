package de.uerc.memberarea.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import de.uerc.memberarea.json.View;

@Entity
public class Tag extends TimestampedEntity {

	private static final long serialVersionUID = -4442738827193280126L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(value = View.Nested.class)
	private Long id;

	@OneToOne
	private SocialClub socialClub;

	@JsonView(value = View.Nested.class)
	private String name;

	Tag() {
	}

	public Tag(String name) {
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

}
