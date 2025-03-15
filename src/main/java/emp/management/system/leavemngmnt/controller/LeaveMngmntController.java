package emp.management.system.leavemngmnt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emp.management.system.entity.LeaveRequest;
import emp.management.system.leavemngmnt.pojo.LeaveRqst;
import emp.management.system.leavemngmnt.service.LeaveMngmntService;
import emp.management.system.response.pojo.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leaves")
public class LeaveMngmntController {

	@Autowired
	LeaveMngmntService service;

	@PostMapping
	@PreAuthorize(value = "EMPLOYEE")
	@Operation(summary = "Employee applying Leaves")
	ResponseEntity<SuccessResponse> applyLeaves(@Valid @RequestBody LeaveRqst req) {

		return ResponseEntity.ok().body(new SuccessResponse("Leave Applied", HttpStatus.CREATED,
				Map.of("Requested Id : ", service.applyLeaves(req))));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@Operation(summary = "Get all Leaves Details")
	public ResponseEntity<List<LeaveRequest>> getAllLeaveRequest() {
		return ResponseEntity.ok().body(service.getAllLeaveRequest());
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Employee Leave By ID", description = "Leave Request By ID")
	public ResponseEntity<?> getLeaveRqstById(@PathVariable(value = "id", required = true) Integer id) {
		return ResponseEntity.ok().body(service.getLeaveRqstById(id));

	}
	
	@PutMapping(value = "/{id}/approve")
	@PreAuthorize(value = "MANAGER")
	@Operation(summary = "Approve Leave", description = "Managers Only")
	public ResponseEntity<String> approveLeave(@PathVariable(value = "id", required = true) Integer id) {
		return ResponseEntity.ok().body(service.approveLeave(id));

	}
	
	@PutMapping(value = "/{id}/reject")
	@PreAuthorize(value = "MANAGER")
	@Operation(summary = "Reject Leave", description = "Managers Only")
	public ResponseEntity<String> rejectLeave(@PathVariable(value = "id", required = true) Integer id) {
		return ResponseEntity.ok().body(service.rejectLeave(id));

	}

}
