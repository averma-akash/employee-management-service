package emp.management.system.deprtmnt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emp.management.system.auth.service.UserService;
import emp.management.system.deprtmnt.pojo.AddDprtmnt;
import emp.management.system.entity.AuditLogs;
import emp.management.system.entity.Department;
import emp.management.system.repository.AuditRepository;
import emp.management.system.repository.DepartmentRepository;
import jakarta.transaction.Transactional;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository dao;
	@Autowired
	AuditRepository audit;
	@Autowired
	UserService userService;

	@Transactional
	public String addDptmnt(AddDprtmnt dptmnt) {

		Department dpt = createDptEntity(dptmnt);
		dao.save(dpt);
		AuditLogs log = new AuditLogs();
		log.setAction("Added Department : " + dptmnt.getName());
		log.setCreatedTime(new Date());
		log.setEmployeeId(userService.getEMpId());
		audit.save(log);
		return "Success";
	}

	private Department createDptEntity(AddDprtmnt dptmnt) {

		Department dpt = new Department();
		dpt.setDeptName(dptmnt.getName());
		dpt.setCreatedAt(new Date());
		return dpt;
	}

	@Transactional
	public List<Department> getDepartments() {
		return dao.findAll();
	}

	@Transactional
	public Department getDepartmentById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Transactional
	public String updateDepartmentById(Integer id, String name) {

		Department dpt = getDepartmentById(id);
		if (null != dpt) {
			dpt.setDeptName(name);
			dao.save(dpt);
			return "Success";
		}
		return "No Data to Update";
	}

	@Transactional
	public String deleteDepartmentById(Integer id) {
		dao.deleteById(id);
		return "Success";
	}

}
