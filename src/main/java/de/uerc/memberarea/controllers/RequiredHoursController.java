package de.uerc.memberarea.controllers;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uerc.memberarea.models.workitem.RequiredHours;

//@RestController
//@RequestMapping(path = "/requiredhours")
public class RequiredHoursController {

	
//	@PostMapping(path = "/")
	public RequiredHours saveNewWorkitem() {
		throw new NotYetImplementedException();
	}

	//@GetMapping(path = "/{requiredHoursId}")
	public RequiredHours getWorkitem(@PathVariable long requiredHoursId) {
		throw new NotYetImplementedException();
	}

	//@PutMapping(path = "/{requiredHoursId}")
	public RequiredHours updateWorkitem(@PathVariable long requiredHoursId) {
		throw new NotYetImplementedException();
	}
	
}
