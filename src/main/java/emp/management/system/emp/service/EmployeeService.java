package emp.management.system.emp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import emp.management.system.auth.service.UserService;
import emp.management.system.emp.pojo.AddEmployee;
import emp.management.system.emp.pojo.SearchEmployee;
import emp.management.system.emp.pojo.UpdateEmployee;
import emp.management.system.entity.AuditLogs;
import emp.management.system.entity.Employee;
import emp.management.system.repository.AuditRepository;
import emp.management.system.repository.EmployeeRepository;
import emp.management.system.repository.UserRepository;
import emp.management.system.response.pojo.ErrorResponse;
import emp.management.system.utils.CommonUtility;
import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empDao;
	@Autowired
	UserService userService;
	@Autowired
	AuditRepository auditRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	EmployeeDao dao;

	@Transactional
	public Map<String, String> addEmployee(AddEmployee newEmp) {

		Map<String, String> response = new HashMap<>();

		if (!empDao.existsByEmail(newEmp.getEmail())) {
			Employee emp = CommonUtility.setEmployee(newEmp);
			empDao.save(emp);

			AuditLogs audit = new AuditLogs();
			audit.setAction("Added New User " + newEmp.getEmail());
			audit.setCreatedTime(new Date());
			audit.setEmployeeId(userService.getEMpId());

			auditRepo.save(audit);

			response.put("status", "success");
			response.put("email", newEmp.getEmail() + " Added Successfuly");
			return response;
		}
		response.put("status", "Failure");
		response.put("email", newEmp.getEmail() + " Already Exist !");
		return response;

	}

	@Transactional
	public Page<Employee> getAllEmployees(Pageable pageable) {

		return empDao.findAll(pageable);

	}

	@Transactional
	public Employee getEmployee(Integer id) {
		try {
			return empDao.findById(id).get();
		} catch (NoSuchElementException e) {
			AuditLogs audit = new AuditLogs();
			audit.setAction("No Reult found for emp Id : " + id);
			audit.setCreatedTime(new Date());
			audit.setEmployeeId(userService.getEMpId());
			auditRepo.save(audit);
			return null;
		}
	}

	@Transactional
	public Object updateEmp(Integer id, UpdateEmployee emp) {

		Employee employee = getEmployee(id);
		if (null == employee) {
			return new ErrorResponse("No Employee Found with Id : " + id, HttpStatus.NOT_FOUND);
		}

		Optional.ofNullable(emp.getEmail()).filter(StringUtils::isNotBlank).ifPresent(employee::setEmail);
		Optional.ofNullable(emp.getPhoneNo()).filter(StringUtils::isNotBlank).ifPresent(employee::setPhoneNo);
		Optional.ofNullable(emp.getDepartmentId()).ifPresent(employee::setDepartmentId);
		Optional.ofNullable(emp.getManagerId()).ifPresent(employee::setManagerId);
		Optional.ofNullable(emp.getRoleId()).ifPresent(employee::setRoleId);
		Optional.ofNullable(emp.getSalary()).ifPresent(employee::setSalary);

		empDao.save(employee);

		AuditLogs audit = new AuditLogs();
		audit.setAction("Employee Details Updated for emp Id : " + id);
		audit.setCreatedTime(new Date());
		audit.setEmployeeId(userService.getEMpId());
		auditRepo.save(audit);

		return "Success";

	}

	@Transactional
	public Object deleteEmp(Integer id) {

		userRepo.deleteById(id);
		empDao.deleteById(id);
		AuditLogs audit = new AuditLogs();
		audit.setAction("Employee Deleted : " + id);
		audit.setCreatedTime(new Date());
		audit.setEmployeeId(userService.getEMpId());
		auditRepo.save(audit);
		return "Success";

	}
	@Transactional
	public List<Employee> searchEmployee(SearchEmployee searchEmp) {
		return dao.searchEmployee(searchEmp);
	}

}
