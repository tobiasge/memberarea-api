package de.uerc.memberarea.controllers;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uerc.memberarea.models.workitem.Workitem;
import de.uerc.memberarea.service.WorkitemService;

@RestController
@RequestMapping(path = "/workitems")
public class WorkItemController {

	@Autowired
	private WorkitemService workitemService;

	@PostMapping(path = "/{workItemId}/tag/{tagId}")
	
	public HttpEntity<?> addTag(@PathVariable long workItemId, @PathVariable long tagId) {
		this.workitemService.addTag(workItemId, tagId);

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping(path = "/{workItemId}/assign/{userId}")
	public ResponseEntity<?> assignClubMember(@PathVariable long workItemId, @PathVariable long userId) {
		throw new NotYetImplementedException();
	}

	@DeleteMapping(path = "/{workItemId}/tag/{tagId}")
	public ResponseEntity<?> deleteTag(@PathVariable long workItemId, @PathVariable long tagId) {
		this.workitemService.removeTag(workItemId, tagId);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(path = "/{workItemId}")
	public Workitem getWorkitem(@PathVariable long workItemId) {
		throw new NotYetImplementedException();
	}

	@DeleteMapping(path = "/{workItemId}/assign/{userId}")
	public ResponseEntity<?> removeClubMember(@PathVariable long workItemId, @PathVariable long userId) {
		throw new NotYetImplementedException();
	}

	@PostMapping(path = "/")
	public Workitem saveNewWorkitem() {
		throw new NotYetImplementedException();
	}

	@PutMapping(path = "/{workItemId}")
	public Workitem updateWorkitem(@PathVariable long workItemId) {
		throw new NotYetImplementedException();
	}
}
