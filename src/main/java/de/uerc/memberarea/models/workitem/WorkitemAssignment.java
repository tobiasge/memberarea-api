package de.uerc.memberarea.models.workitem;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.uerc.memberarea.json.NestedSerializer;
import de.uerc.memberarea.json.View;
import de.uerc.memberarea.models.SocialClub;
import de.uerc.memberarea.models.TimestampedEntity;
import de.uerc.memberarea.models.users.ClubMember;

@Entity
public class WorkitemAssignment extends TimestampedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(value = View.Nested.class)
	private Long id;

	@OneToOne
	private SocialClub socialClub;

	@OneToOne(optional = false)
	@JsonSerialize(using = NestedSerializer.class)
	private WorkItem workitem;

	@OneToOne(optional = false)
	@JsonSerialize(using = NestedSerializer.class)
	private ClubMember assignee;

	private long durationReal;

	private LocalDateTime doneAt;

	@OneToOne(optional = false)
	@JsonSerialize(using = NestedSerializer.class)
	private ClubMember verifiedBy;

	private LocalDateTime verifiedAt;

	private String comment;
	
	public WorkItem getWorkitem() {
		return this.workitem;
	}

	public void setWorkitem(WorkItem workitem) {
		this.workitem = workitem;
	}

	public ClubMember getAssignee() {
		return this.assignee;
	}

	public void setAssignee(ClubMember assignee) {
		this.assignee = assignee;
	}

	public long getDurationReal() {
		return this.durationReal;
	}

	public void setDurationReal(long durationReal) {
		this.durationReal = durationReal;
	}

	public LocalDateTime getDoneAt() {
		return this.doneAt;
	}

	public void setDoneAt(LocalDateTime doneAt) {
		this.doneAt = doneAt;
	}

	public ClubMember getVerifiedBy() {
		return this.verifiedBy;
	}

	public void setVerifiedBy(ClubMember verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public LocalDateTime getVerifiedAt() {
		return this.verifiedAt;
	}

	public void setVerifiedAt(LocalDateTime verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

}
