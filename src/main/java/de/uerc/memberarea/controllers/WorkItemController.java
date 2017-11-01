package de.uerc.memberarea.controllers;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uerc.memberarea.models.workitem.WorkItem;

@RestController
@RequestMapping(path = "/workitems")
public class WorkItemController {

	@PostMapping(path = "/")
	public WorkItem saveNewWorkitem() {
		throw new NotYetImplementedException();
	}

	@GetMapping(path = "/{workItemId}")
	public WorkItem getWorkitem(@PathVariable long workItemId) {
		throw new NotYetImplementedException();
	}

	@PutMapping(path = "/{workItemId}")
	public WorkItem updateWorkitem(@PathVariable long workItemId) {
		throw new NotYetImplementedException();
	}

	@PostMapping(path = "/{workItemId}/tag/{tagId}")
	public ResponseEntity<?> addTag(@PathVariable long workItemId, @PathVariable long tagId) {
		throw new NotYetImplementedException();
	}

	@DeleteMapping(path = "/{workItemId}/tag/{tagId}")
	public ResponseEntity<?> deleteTag(@PathVariable long workItemId, @PathVariable long tagId) {
		throw new NotYetImplementedException();
	}

	@PostMapping(path = "/{workItemId}/assign/{userId}")
	public ResponseEntity<?> assignClubMember(@PathVariable long workItemId, @PathVariable long userId) {
		throw new NotYetImplementedException();
	}

	@DeleteMapping(path = "/{workItemId}/assign/{userId}")
	public ResponseEntity<?> removeClubMember(@PathVariable long workItemId, @PathVariable long userId) {
		throw new NotYetImplementedException();
	}
}
