package de.uerc.memberarea.models.workitem;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import de.uerc.memberarea.models.Tag;
import de.uerc.memberarea.models.base.AuditedEntity;

@Entity
public class Workitem extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String description;
    private LocalDateTime published;
    private long duration_expected;

    private LocalDateTime due_at;

    private int maxAssignees;

    @OneToMany(mappedBy = "workitem")
    private Set<WorkitemAssignment> assignees = new HashSet<WorkitemAssignment>();
    
    @ManyToMany
    private Set<Tag> tags;

    private boolean deleted;

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public int getMaxAssignees() {
        return maxAssignees;
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public void setMaxAssignees(int maxAssignees) {
        this.maxAssignees = maxAssignees;
    }

    public Long getId() {
        return id;
    }

}
