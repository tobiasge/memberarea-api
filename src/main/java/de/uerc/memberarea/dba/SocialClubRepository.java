package de.uerc.memberarea.dba;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.uerc.memberarea.models.SocialClub;

@Repository
public interface SocialClubRepository extends JpaRepository<SocialClub, Long> {

	public Optional<SocialClub> findByHost(String host);
}
