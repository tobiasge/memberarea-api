package de.uerc.memberarea.dba;

import org.springframework.data.jpa.repository.JpaRepository;

import de.uerc.memberarea.models.workitem.Workitem;

public interface WorkitemRepository extends JpaRepository<Workitem, Long> {

}
