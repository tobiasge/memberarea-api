package de.uerc.memberarea.models.users;

import static de.uerc.memberarea.security.SecurityConstants.PASSWORD_EXPIRY_DAYS;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonView;

import de.uerc.memberarea.json.View;
import de.uerc.memberarea.models.SocialClub;
import de.uerc.memberarea.models.TimestampedEntity;

@Entity
public class ClubMember extends TimestampedEntity implements IClubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(value = View.Nested.class)
    private Long id;

    public Long getId() {
        return id;
    }

    public SocialClub getSocialClub() {
        return socialClub;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public long getMember_id() {
        return member_id;
    }

    public LocalDate getEntry_date() {
        return entry_date;
    }

    public LocalDate getExit_date() {
        return exit_date;
    }

    public MemberState getState() {
        return state;
    }

    public String getEfaId() {
        return efaId;
    }

    public LocalDate getPw_changed_at() {
        return pw_changed_at;
    }

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
    public boolean isAccountExpired() {
        return this.exit_date != null && this.exit_date.isBefore(LocalDate.now());
    }

    @Override
    public boolean isAccountLocked() {
        return this.locked;
    }

    @Override
    public boolean isCredentialsExpired() {
        return this.pw_changed_at.isBefore(LocalDate.now().minus(PASSWORD_EXPIRY_DAYS, ChronoUnit.DAYS));
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String getName() {
        return this.socialClub.getHost() + "/" + this.getUserType() + "/" + this.username;
    }

    @Override
    public UserType getUserType() {
        return UserType.MEMBER;
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
