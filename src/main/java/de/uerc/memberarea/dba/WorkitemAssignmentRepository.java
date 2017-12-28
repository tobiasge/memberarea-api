package de.uerc.memberarea.dba;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.uerc.memberarea.models.users.ClubMember;
import de.uerc.memberarea.models.workitem.Workitem;
import de.uerc.memberarea.models.workitem.WorkitemAssignment;

public interface WorkitemAssignmentRepository extends JpaRepository<WorkitemAssignment, Long> {

    int countByWorkitem(Workitem wi);

    Optional<WorkitemAssignment> findByWorkitemAndAssignee(Workitem wi, ClubMember cm);

}
