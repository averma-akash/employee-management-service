package emp.management.system.roles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emp.management.system.entity.Roles;
import emp.management.system.roles.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	RoleService service;

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER)")
	@Operation(summary = "Delete Employee By ID", description = "Delete Employee details in User and employee from table")

	public ResponseEntity<List<Roles>> getAllRoles() {

		return ResponseEntity.ok().body(service.getAllRoles());
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER)")
	public ResponseEntity<Roles> getRoleById(@PathVariable(value = "id", required = false) Integer id) {

		return ResponseEntity.ok().body(service.getRoleById(id));
	}

}
