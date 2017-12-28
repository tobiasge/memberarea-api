package de.uerc.memberarea.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import de.uerc.memberarea.models.base.TimestampedEntity;

@Entity
public class Tag extends TimestampedEntity implements Comparable<Tag> {

    private static final long serialVersionUID = -4442738827193280126L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private final String name;

    public Tag(final String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Tag rhs) {
        Tag lhs = this;
        return lhs.name.compareToIgnoreCase(rhs.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tag) {
            Tag rhs = (Tag) obj;
            Tag lhs = this;
            return new EqualsBuilder().append(lhs.name, rhs.name).isEquals();
        }
        return false;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(47, 53).append(this.name).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("id", this.id)
            .append("name", this.name).toString();
    }
}
