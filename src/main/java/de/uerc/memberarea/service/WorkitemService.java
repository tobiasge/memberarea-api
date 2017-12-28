package de.uerc.memberarea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import de.uerc.memberarea.dba.ClubMemberRepository;
import de.uerc.memberarea.dba.TagRepository;
import de.uerc.memberarea.dba.WorkitemAssignmentRepository;
import de.uerc.memberarea.dba.WorkitemRepository;
import de.uerc.memberarea.dto.ResponseStatus;
import de.uerc.memberarea.models.Tag;
import de.uerc.memberarea.models.users.ClubMember;
import de.uerc.memberarea.models.workitem.Workitem;
import de.uerc.memberarea.models.workitem.WorkitemAssignment;

@Service
public class WorkitemService {

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private WorkitemAssignmentRepository workitemAssignmentRepository;

    @Autowired
    private WorkitemRepository workitemRepository;

    public ResponseStatus addTag(final long workItemId, final long tagId) {
        final Tag tag = this.tagRepository.getOne(tagId);
        final Workitem workItem = this.workitemRepository.getOne(workItemId);
        return this.addTag(workItem, tag);
    }

    @PreAuthorize("hasRole('OP_WI_ADD_TAG') OR authentiation == workItem.createdBy")
    public ResponseStatus addTag(final Workitem workItem, final Tag tag) {
        workItem.addTag(tag);
        this.workitemRepository.save(workItem);
        return new ResponseStatus(true);
    }

    public void assignClubMember(final long workItemId, final long clubMemberId) {
        final ClubMember clubMember = this.clubMemberRepository.getOne(clubMemberId);
        final Workitem workItem = this.workitemRepository.getOne(workItemId);
        this.assignClubMember(workItem, clubMember);
    }

    @PreAuthorize("hasRole('ADMIN') OR authentiation == clubMember")
    public void assignClubMember(final Workitem workitem, final ClubMember clubMember) {
        if (this.isClubMemberAssigned(workitem, clubMember)) {
            return;
        }
        if (this.isMaxAssigneesReached(workitem)) {
            return;
        }

        final WorkitemAssignment wia = new WorkitemAssignment(workitem, clubMember);
        this.workitemAssignmentRepository.save(wia);
    }

    private boolean isClubMemberAssigned(final Workitem wi, final ClubMember cm) {
        return this.workitemAssignmentRepository.findByWorkitemAndAssignee(wi, cm).isPresent();
    }

    private boolean isMaxAssigneesReached(final Workitem wi) {
        return wi.getMaxAssignees() <= this.workitemAssignmentRepository.countByWorkitem(wi);
    }

    public void removeTag(final long workItemId, final long tagId) {
        final Tag tag = this.tagRepository.getOne(tagId);
        final Workitem workItem = this.workitemRepository.getOne(workItemId);
        this.addTag(workItem, tag);
    }

    @PreAuthorize("hasRole('OP_WI_REMOVE_TAG') OR authentiation == workItem.createdBy")
    public void removeTag(final Workitem workItem, final Tag tag) {
        workItem.removeTag(tag);
        this.workitemRepository.save(workItem);
    }

}
