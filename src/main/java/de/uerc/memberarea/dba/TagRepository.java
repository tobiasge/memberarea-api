package de.uerc.memberarea.dba;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.uerc.memberarea.models.SocialClub;
import de.uerc.memberarea.models.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	Tag findByName(String name);

	List<Tag> findBySocialClub(SocialClub socialClub);
}
