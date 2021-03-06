package de.uerc.memberarea.dba;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.uerc.memberarea.models.users.ClubAdmin;

public interface ClubAdminRepository extends JpaRepository<ClubAdmin, Long> {

    Optional<ClubAdmin> findByUsername(String username);

}
