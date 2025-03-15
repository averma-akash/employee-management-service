package emp.management.system.deprtmnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emp.management.system.deprtmnt.pojo.AddDprtmnt;
import emp.management.system.deprtmnt.service.DepartmentService;
import emp.management.system.entity.Department;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	@Autowired
	DepartmentService service;

	@PostMapping
	@Operation(summary = "Add New Department")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addDptmnt(@RequestBody AddDprtmnt dptmnt) {

		return ResponseEntity.ok().body(service.addDptmnt(dptmnt));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@Operation(summary = "Get Department Data", description = "Get All Department")
	public ResponseEntity<List<Department>> getDepartments() {

		return ResponseEntity.ok().body(service.getDepartments());
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@Operation(summary = "Get Department Data", description = "Get Department By Id")
	public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id", required = true) Integer id) {

		return ResponseEntity.ok().body(service.getDepartmentById(id));
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Update Department Data", description = "update Department By Id")
	public ResponseEntity<String> updateDepartmentById(@PathVariable(value = "id", required = true) Integer id,
			@PathVariable(value = "name", required = true) String name) {

		return ResponseEntity.ok().body(service.updateDepartmentById(id, name));
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Delete Department Data", description = "Delete Department By Id")
	public ResponseEntity<String> deleteDepartmentById(@PathVariable(value = "id", required = true) Integer id) {

		return ResponseEntity.ok().body(service.deleteDepartmentById(id));
	}

}
