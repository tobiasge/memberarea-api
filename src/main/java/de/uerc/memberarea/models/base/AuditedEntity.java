package de.uerc.memberarea.models.base;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.uerc.memberarea.models.serializer.NestedClubMemberSerializer;
import de.uerc.memberarea.models.users.ClubMember;

@MappedSuperclass
public class AuditedEntity extends TimestampedEntity {

    private static final long serialVersionUID = -6666812745863429397L;

    @CreatedBy
    @ManyToOne
    @JsonSerialize(using = NestedClubMemberSerializer.class)
    private ClubMember createdBy;

    @LastModifiedBy
    @ManyToOne
    @JsonSerialize(using = NestedClubMemberSerializer.class)
    private ClubMember updatedBy;

    public ClubMember getCreatedBy() {
        return this.createdBy;
    }

    public ClubMember getUpdatedBy() {
        return this.updatedBy;
    }

}
