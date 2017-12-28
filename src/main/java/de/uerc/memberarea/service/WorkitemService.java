package de.uerc.memberarea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import de.uerc.memberarea.dba.ClubMemberRepository;
import de.uerc.memberarea.dba.TagRepository;
import de.uerc.memberarea.dba.WorkitemAssignmentRepository;
import de.uerc.memberarea.dba.WorkitemRepository;
import de.uerc.memberarea.models.Tag;
import de.uerc.memberarea.models.users.ClubMember;
import de.uerc.memberarea.models.workitem.Workitem;
import de.uerc.memberarea.models.workitem.WorkitemAssignment;

@Service
public class WorkitemService {

    @Autowired
    private WorkitemRepository workitemRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private WorkitemAssignmentRepository workitemAssignmentRepository;

    public void addTag(long workItemId, long tagId) {
        Tag tag = this.tagRepository.getOne(tagId);
        Workitem workItem = this.workitemRepository.getOne(workItemId);
        this.addTag(workItem, tag);
    }

    @PreAuthorize("hasRole('OP_WI_ADD_TAG') OR authentiation == workItem.createdBy")
    public void addTag(Workitem workItem, Tag tag) {
        workItem.addTag(tag);
        this.workitemRepository.save(workItem);
    }

    public void assignClubMember(long workItemId, long clubMemberId) {
        ClubMember clubMember = this.clubMemberRepository.getOne(clubMemberId);
        Workitem workItem = this.workitemRepository.getOne(workItemId);
        this.assignClubMember(workItem, clubMember);
    }

    @PreAuthorize("hasRole('ADMIN') OR authentiation == clubMember")
    public void assignClubMember(Workitem workitem, ClubMember clubMember) {
        if (this.isClubMemberAssigned(workitem, clubMember)) {
            return;
        }
        if (this.isMaxAssigneesReached(workitem)) {
            return;
        }

        WorkitemAssignment wia = new WorkitemAssignment(workitem, clubMember);
        this.workitemAssignmentRepository.save(wia);
    }

    public void removeTag(long workItemId, long tagId) {
        Tag tag = this.tagRepository.getOne(tagId);
        Workitem workItem = this.workitemRepository.getOne(workItemId);
        this.addTag(workItem, tag);
    }

    @PreAuthorize("hasRole('OP_WI_REMOVE_TAG') OR authentiation == workItem.createdBy")
    public void removeTag(Workitem workItem, Tag tag) {
        workItem.removeTag(tag);
        this.workitemRepository.save(workItem);
    }

    private boolean isClubMemberAssigned(Workitem wi, ClubMember cm) {
        return this.workitemAssignmentRepository.findByWorkitemAndAssignee(wi, cm).isPresent();
    }

    private boolean isMaxAssigneesReached(Workitem wi) {
        return wi.getMaxAssignees() <= this.workitemAssignmentRepository.countByWorkitem(wi);
    }

}
