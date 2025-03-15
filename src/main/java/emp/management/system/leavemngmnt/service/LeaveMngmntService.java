package emp.management.system.leavemngmnt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emp.management.system.auth.service.UserService;
import emp.management.system.entity.AuditLogs;
import emp.management.system.entity.LeaveRequest;
import emp.management.system.leavemngmnt.pojo.LeaveRqst;
import emp.management.system.repository.AuditRepository;
import emp.management.system.repository.LeaveRepository;
import jakarta.transaction.Transactional;

@Service
public class LeaveMngmntService {

	@Autowired
	LeaveRepository dao;
	@Autowired
	UserService userService;
	@Autowired
	AuditRepository audit;

	@Transactional
	public Integer applyLeaves(LeaveRqst req) {

		Integer eMpId = userService.getEMpId();
		LeaveRequest entity = new LeaveRequest();
		entity.setCreatedAt(new Date());
		entity.setEmployeeId(eMpId);
		entity.setEndDate(req.getEndDate());
		entity.setLeaveType(req.getLeaveType());
		entity.setStartDate(req.getStartDate());
		entity.setStatus("Pending");

		LeaveRequest lvRes = dao.save(entity);

		AuditLogs log = new AuditLogs();
		log.setAction("Leaves Applied for Myself");
		log.setCreatedTime(new Date());
		log.setEmployeeId(eMpId);

		audit.save(log);

		return lvRes.getId();
	}

	@Transactional
	public List<LeaveRequest> getAllLeaveRequest() {

		return dao.findAll();

	}

	@Transactional
	public LeaveRequest getLeaveRqstById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Transactional
	public String approveLeave(Integer id) {

		LeaveRequest leave = getLeaveRqstById(id);

		if (null != leave) {

			leave.setStatus("Approved");
			dao.save(leave);

			AuditLogs log = new AuditLogs();
			log.setAction("Leaves Approved for emp Id : " + leave.getEmployeeId());
			log.setCreatedTime(new Date());
			log.setEmployeeId(userService.getEMpId());
			audit.save(log);

			return "Success";
		}
		return "Failed";
	}

	@Transactional
	public String rejectLeave(Integer id) {
		LeaveRequest leave = getLeaveRqstById(id);

		if (null != leave) {
			leave.setStatus("Rejected");
			dao.save(leave);

			AuditLogs log = new AuditLogs();
			log.setAction("Leaves Rejected for emp Id : " + leave.getEmployeeId());
			log.setCreatedTime(new Date());
			log.setEmployeeId(userService.getEMpId());

			audit.save(log);
			return "Success";
		}
		return "Failed";
	}

}
