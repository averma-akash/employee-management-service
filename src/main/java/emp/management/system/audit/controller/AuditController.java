package emp.management.system.audit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emp.management.system.audit.service.AuditService;
import emp.management.system.entity.AuditLogs;

@RestController
@RequestMapping("/api/logs")
public class AuditController {

	@Autowired
	AuditService service;

	@GetMapping
	@PreAuthorize(value = "ADMIN")
	ResponseEntity<List<AuditLogs>> getAllLogs() {
		return ResponseEntity.ok().body(service.getAllLogs());
	}

	@GetMapping(value = "{id}")
	@PreAuthorize(value = "ADMIN")
	ResponseEntity<AuditLogs> getLogById(@PathVariable(value = "id", required = true) Integer id) {
		return ResponseEntity.ok().body(service.getLogById(id));
	}

}
