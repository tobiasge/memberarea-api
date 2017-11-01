package de.uerc.memberarea.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class TimestampedEntity implements Serializable {

	private static final long serialVersionUID = -8327235991203336067L;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	@PrePersist
	private void setCreatedAtIfNeeded() {
		if (this.createdAt == null) {
			this.createdAt = LocalDateTime.now();
		}
	}

	@PreUpdate
	private void setUpdatedAt() {
		this.updatedAt = LocalDateTime.now();
	}
}
