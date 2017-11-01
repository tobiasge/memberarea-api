package de.uerc.memberarea.dba;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.uerc.memberarea.models.users.ClubMember;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {

	Optional<ClubMember> findByUsername(String username);
	
}
