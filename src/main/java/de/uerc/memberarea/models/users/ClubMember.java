package de.uerc.memberarea.models.users;

import static de.uerc.memberarea.security.SecurityConstants.PASSWORD_EXPIRY_DAYS;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;

import de.uerc.memberarea.json.View;
import de.uerc.memberarea.models.SocialClub;
import de.uerc.memberarea.models.TimestampedEntity;

@Entity
public class ClubMember extends TimestampedEntity implements UserDetails, CredentialsContainer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(value = View.Nested.class)
	private Long id;

	@OneToOne
	private SocialClub socialClub;

	// Personal information
	private String salutation;
	private String first_name;
	private String last_name;
	private Sex sex;
	private LocalDate birthday;
	private String email;

	// Club information
	private long member_id;
	private LocalDate entry_date;
	private LocalDate exit_date;
	private MemberState state;

	private String efaId;

	// Security fields
	private String username;
	private String password;
	private LocalDate pw_changed_at;
	private boolean enabled;
	private boolean locked;

	public static enum MemberState {
		Active, Passive
	}

	public static enum Sex {
		Male, Female, None
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !(this.exit_date != null && this.exit_date.isBefore(LocalDate.now()));
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !this.pw_changed_at.isBefore(LocalDate.now().minus(PASSWORD_EXPIRY_DAYS, ChronoUnit.DAYS));
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public void eraseCredentials() {
		this.password = null;
	}
}
