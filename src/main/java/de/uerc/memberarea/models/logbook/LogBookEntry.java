package de.uerc.memberarea.models.logbook;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Null;

import de.uerc.memberarea.models.SocialClub;
import de.uerc.memberarea.models.users.ClubMember;

public class LogBookEntry {

	@EmbeddedId
	private LogBookEntryId id;

	@OneToOne
	private SocialClub socialClub;

	@OneToOne
	private Boat boat;

	@OneToOne
	@Null
	private ClubMember coxswain;

	@OneToMany
	private Set<ClubMember> crew;

	private LocalDateTime start;

	private LocalDateTime end;

	private BigDecimal distance;

	@OneToOne
	@Null
	private Destination dest;

	private String destName;

}

@Embeddable
class LogBookEntryId {
	private Long year;
	private Long number;
}