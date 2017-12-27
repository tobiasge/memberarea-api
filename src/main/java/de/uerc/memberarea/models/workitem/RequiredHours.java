package de.uerc.memberarea.models.workitem;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.lang.Nullable;

import de.uerc.memberarea.models.base.AuditedEntity;

@Entity
public class RequiredHours extends AuditedEntity implements Comparable<RequiredHours> {

    private static final long serialVersionUID = -7197176689310342376L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * This is the year in which the {@link RequiredHours} have to be performed.
     */
    private final int year;

    @NotNull
    private BigDecimal hours;

    @NotNull
    private BigDecimal missingHoursPrice;

    @Nullable
    private Integer fromYearOfBirth;

    @Nullable
    private Integer toYearOfBirth;

    public RequiredHours(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(RequiredHours rhs) {
        RequiredHours lhs = this;
        // First sort order is the year
        if (lhs.year < rhs.year) {
            return -1;
        }
        if (lhs.year > rhs.year) {
            return 1;
        }
        // lhs.year == rhs.year
        // Second sort order is the fromYearOfBirth
        boolean bothNull = false;
        if (lhs.fromYearOfBirth == null && rhs.fromYearOfBirth == null) {
            bothNull = true;
        }
        if (!bothNull && lhs.fromYearOfBirth == null) {
            return -1;
        }
        if (!bothNull && rhs.fromYearOfBirth == null) {
            return 1;
        }
        if (!bothNull && lhs.fromYearOfBirth < rhs.fromYearOfBirth) {
            return -1;
        }
        if (!bothNull && lhs.fromYearOfBirth > rhs.fromYearOfBirth) {
            return 1;
        }
        // lhs.fromYearOfBirth == rhs.fromYearOfBirth
        // Third sort order is the toYearOfBirth
        if (lhs.toYearOfBirth == null && rhs.toYearOfBirth == null) {
            return 0;
        }
        if (lhs.toYearOfBirth == null) {
            return 1;
        }
        if (rhs.toYearOfBirth == null) {
            return -1;
        }
        if (lhs.toYearOfBirth < rhs.toYearOfBirth) {
            return 1;
        }
        if (lhs.toYearOfBirth > rhs.toYearOfBirth) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RequiredHours) {
            RequiredHours rhs = (RequiredHours) obj;
            RequiredHours lhs = this;
            return new EqualsBuilder().append(lhs.year, rhs.year).append(lhs.fromYearOfBirth, rhs.fromYearOfBirth)
                .append(lhs.toYearOfBirth, rhs.toYearOfBirth).isEquals();
        }
        return false;
    }

    public Integer getFromYearOfBirth() {
        return fromYearOfBirth;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getMissingHoursPrice() {
        return missingHoursPrice;
    }

    public Integer getToYearOfBirth() {
        return toYearOfBirth;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(45, 47).append(this.year).append(this.fromYearOfBirth).append(this.toYearOfBirth)
            .toHashCode();
    }

    public boolean isValidForYearOfBirth(int yearOfBirth) {
        if (this.fromYearOfBirth == null && this.toYearOfBirth == null) {
            return true;
        }
        if (this.fromYearOfBirth == null) {
            return yearOfBirth < this.toYearOfBirth;
        }
        if (this.toYearOfBirth == null) {
            return this.fromYearOfBirth <= yearOfBirth;
        }
        return this.fromYearOfBirth <= yearOfBirth && yearOfBirth < this.toYearOfBirth;
    }

    public boolean overlapsWith(RequiredHours rhs) {
        RequiredHours lhs = this;
        // If the year is not equal there can't be an overlap
        if (lhs.year != rhs.year) {
            return false;
        }
        // If ranges are open they overlap
        if ((lhs.fromYearOfBirth == null && rhs.fromYearOfBirth == null)
            || (lhs.toYearOfBirth == null && rhs.toYearOfBirth == null)) {
            return true;
        }
        // TODO Further checks
        return false;
    }

    public void setFromYearOfBirth(Integer fromYearOfBirth) {
        this.fromYearOfBirth = fromYearOfBirth;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public void setMissingHoursPrice(BigDecimal missingHoursPrice) {
        this.missingHoursPrice = missingHoursPrice;
    }

    public void setToYearOfBirth(Integer toYearOfBirth) {
        this.toYearOfBirth = toYearOfBirth;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("id", this.id)
            .append("year", this.year).append("fromYearOfBirth", this.fromYearOfBirth)
            .append("toYearOfBirth", this.toYearOfBirth).toString();
    }
}
