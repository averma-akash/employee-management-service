package emp.management.system.emp.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emp.management.system.emp.pojo.AddEmployee;
import emp.management.system.emp.service.EmployeeService;
import emp.management.system.entity.Employee;
import emp.management.system.response.pojo.ErrorResponse;
import emp.management.system.response.pojo.SuccessResponse;
import emp.management.system.utils.CommonUtility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@PostMapping(value = "employees")
	@Operation(summary = "Add New employee", responses = {
			@ApiResponse(responseCode = "200", description = "Successfully registered"),
			@ApiResponse(responseCode = "401", description = "Unauthorized Access"),
			@ApiResponse(responseCode = "409", description = "Employee Already exist"),
			@ApiResponse(responseCode = "400", description = "Bad Request") })
	public ResponseEntity<?> addEmployee(@RequestBody AddEmployee newEmp) {

		List<String> roles = CommonUtility.getAssignedRoles();

		if (StringUtils.isEmpty(newEmp.getEmail())) {
			return ResponseEntity.badRequest().body(new ErrorResponse("Failure", HttpStatus.BAD_REQUEST));
		}
		if (CollectionUtils.isEmpty(roles) || !roles.contains("ROLE_ADMIN")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponse("Unauthorized Access", HttpStatus.UNAUTHORIZED));
		}
		Map<String, String> response = service.addEmployee(newEmp);
		if ("success".equalsIgnoreCase(response.get("status"))) {
			return ResponseEntity.ok()
					.body(new SuccessResponse(response.get("status"), HttpStatus.CREATED, response.get("email")));
		} else {
			return ResponseEntity.ok().body(new ErrorResponse(response.get("email"), HttpStatus.CONFLICT));
		}
	}

	@GetMapping(value = "employees")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@Operation(summary = "Get all employees (Paginated)", description = "Fetches a paginated list of employees, accessible only by ADMIN and MANAGER.", responses = {
			@ApiResponse(responseCode = "200", description = "List of employees retrieved successfully"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Insufficient permissions", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	public ResponseEntity<Page<Employee>> getAllEmployees(@PageableDefault(size = 10, sort = "id") Pageable pageable) {

		Page<Employee> employees = service.getAllEmployees(pageable);
		return ResponseEntity.ok(employees);
	}

}
