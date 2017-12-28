package de.uerc.memberarea.models.workitem;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.uerc.memberarea.models.base.AuditedEntity;
import de.uerc.memberarea.models.serializer.NestedClubMemberSerializer;
import de.uerc.memberarea.models.serializer.NestedWorkitemSerializer;
import de.uerc.memberarea.models.users.ClubMember;

@Entity
@IdClass(WorkitemAssignmentPk.class)
public class WorkitemAssignment extends AuditedEntity {

    @Id
    @ManyToOne
    @JsonSerialize(using = NestedWorkitemSerializer.class)
    private final Workitem workitem;

    @Id
    @ManyToOne
    @JsonSerialize(using = NestedClubMemberSerializer.class)
    private final ClubMember assignee;

    private long durationReal;

    private LocalDateTime doneAt;

    @ManyToOne
    private ClubMember verifiedBy;

    private LocalDateTime verifiedAt;

    private String comment;

    public WorkitemAssignment(Workitem workitem, ClubMember assignee) {
        this.assignee = assignee;
        this.workitem = workitem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WorkitemAssignment) {
            WorkitemAssignment rhs = (WorkitemAssignment) obj;
            WorkitemAssignment lhs = this;
            return new EqualsBuilder().append(lhs.workitem.getId(), rhs.workitem.getId())
                .append(lhs.assignee.getId(), rhs.assignee.getId()).isEquals();
        }
        return false;
    }

    public ClubMember getAssignee() {
        return this.assignee;
    }

    public LocalDateTime getDoneAt() {
        return this.doneAt;
    }

    public long getDurationReal() {
        return this.durationReal;
    }

    public LocalDateTime getVerifiedAt() {
        return this.verifiedAt;
    }

    public ClubMember getVerifiedBy() {
        return this.verifiedBy;
    }

    public Workitem getWorkitem() {
        return this.workitem;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(5, 17).append(this.workitem.getId()).append(this.assignee.getId()).hashCode();
    }

    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }

    public void setDurationReal(long durationReal) {
        this.durationReal = durationReal;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public void setVerifiedBy(ClubMember verifiedBy) {
        this.verifiedBy = verifiedBy;
    }
}
