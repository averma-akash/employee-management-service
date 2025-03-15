package emp.management.system.audit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emp.management.system.entity.AuditLogs;
import emp.management.system.repository.AuditRepository;
import jakarta.transaction.Transactional;

@Service
public class AuditService {

	@Autowired
	AuditRepository dao;

	@Transactional
	public List<AuditLogs> getAllLogs() {
		return dao.findAll();
	}

	@Transactional
	public AuditLogs getLogById(Integer id) {
		return dao.findById(id).orElse(null);
	}

}
