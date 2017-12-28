package de.uerc.memberarea.dba;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.uerc.memberarea.models.workitem.RequiredHours;

@Repository
public interface RequiredHoursRepository extends JpaRepository<RequiredHours, Long> {

    List<RequiredHours> findByYear(int year);
}
