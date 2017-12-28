package de.uerc.memberarea.models.users;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import de.uerc.memberarea.models.base.ClubUser;
import de.uerc.memberarea.models.workitem.WorkitemAssignment;

@Entity
public class ClubMember extends ClubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "assignee")
    private Set<WorkitemAssignment> assignments = new HashSet<WorkitemAssignment>();

    public Long getId() {
        return id;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public long getMemberId() {
        return memberId;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public MemberState getState() {
        return state;
    }

    public String getEfaId() {
        return efaId;
    }

    // Personal information
    private String salutation;
    private String firstName;
    private String lastName;
    private Sex sex;
    private LocalDate birthday;
    private String email;

    // Club information
    private long memberId;
    private LocalDate entryDate;
    private LocalDate exitDate;
    private MemberState state;

    private String efaId;

    public static enum MemberState {
        Active, Passive
    }

    public static enum Sex {
        Male, Female, None
    }

    @Override
    public boolean isAccountExpired() {
        return this.exitDate != null && this.exitDate.isBefore(LocalDate.now());
    }

    @Override
    public UserType getUserType() {
        return UserType.MEMBER;
    }

    public String getDisplayName() {
        return this.firstName + " " + this.lastName;
    }

}
