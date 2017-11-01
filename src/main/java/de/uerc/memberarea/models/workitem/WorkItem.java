package de.uerc.memberarea.models.workitem;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.uerc.memberarea.json.NestedSerializer;
import de.uerc.memberarea.json.View;
import de.uerc.memberarea.models.SocialClub;
import de.uerc.memberarea.models.Tag;
import de.uerc.memberarea.models.TimestampedEntity;
import de.uerc.memberarea.models.users.ClubMember;

@Entity
public class WorkItem extends TimestampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(value = View.Nested.class)
    private Long id;

    @OneToOne
    private SocialClub socialClub;

    @JsonView(value = View.Nested.class)
    private String title;
    private String description;
    private LocalDateTime published;
    private long duration_expected;

    @OneToOne
    private ClubMember created_by;

    private LocalDateTime due_at;

    private int max_assignees;

    @OneToMany
    @JsonSerialize(using = NestedSerializer.class)
    private Set<Tag> tags;
    
    private boolean deleted;

}
