package emp.management.system.emp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import emp.management.system.auth.service.UserService;
import emp.management.system.emp.pojo.AddEmployee;
import emp.management.system.entity.AuditLogs;
import emp.management.system.entity.Employee;
import emp.management.system.repository.AuditRepository;
import emp.management.system.repository.EmployeeRepository;
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

}
