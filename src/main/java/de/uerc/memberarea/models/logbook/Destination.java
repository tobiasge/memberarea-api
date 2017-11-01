package de.uerc.memberarea.models.logbook;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import de.uerc.memberarea.json.View;
import de.uerc.memberarea.models.SocialClub;

public class Destination {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(value = View.Nested.class)
	private Long id;
	
	@OneToOne
	private SocialClub socialClub;
	
	private String name;	
	
	private String efaId;
	
	private BigDecimal distance;
	
}
