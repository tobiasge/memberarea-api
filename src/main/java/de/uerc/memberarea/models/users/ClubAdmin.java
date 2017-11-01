package de.uerc.memberarea.models.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import de.uerc.memberarea.json.View;
import de.uerc.memberarea.models.SocialClub;

@Entity
public class ClubAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private SocialClub socialClub;

	private String username;

	private String password;

}
