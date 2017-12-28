package de.uerc.memberarea.models.workitem;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class WorkitemAssignmentPk implements Serializable {

    private static final long serialVersionUID = -9218423691111079436L;

    private final Long workitem;

    private final Long assignee;

    public WorkitemAssignmentPk(Long workitem, Long assignee) {
        this.workitem = workitem;
        this.assignee = assignee;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WorkitemAssignmentPk) {
            WorkitemAssignmentPk rhs = (WorkitemAssignmentPk) obj;
            WorkitemAssignmentPk lhs = this;
            return new EqualsBuilder().append(lhs.workitem, rhs.workitem).append(lhs.assignee, rhs.assignee).isEquals();
        }
        return false;
    }

    public Long getAssignee() {
        return assignee;
    }

    public Long getWorkitem() {
        return workitem;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(5, 17).append(this.workitem).append(this.assignee).hashCode();
    }

}
