package emp.management.system.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import emp.management.system.auth.pojo.LoginRequest;
import emp.management.system.auth.pojo.RegistrationRequest;
import emp.management.system.entity.AuditLogs;
import emp.management.system.entity.Roles;
import emp.management.system.entity.User;
import emp.management.system.repository.AuditRepository;
import emp.management.system.repository.EmployeeRepository;
import emp.management.system.repository.RolesRepository;
import emp.management.system.repository.UserRepository;
import emp.management.system.utils.CommonUtility;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserRepository userDao;
	@Autowired
	RolesRepository rolesDao;
	@Autowired
	EmployeeRepository empDao;
	@Autowired
	AuditRepository auditRepo;

	@Transactional
	public User findUser(LoginRequest request) {

		User userDtls = userDao.findByUsername(request.getUsername());
		if (null != userDtls && request.getPassword().equals(userDtls.getPassword())) {
			logger.info("User Authentication Successful!");
			return userDtls;
		}
		return null;
	}

	public Map<String, Object> createClaims(User user) {

		Map<String, Object> claims = new HashMap<>();
		Roles findById = rolesDao.getById(user.getRoleId());
		claims.put("employeeId", user.getEmpId());
		claims.put("username", user.getUsername());
		claims.put("role", findById != null ? findById.getRoleName() : null);
		return claims;
	}

	@Transactional
	public String userRegister(@Valid RegistrationRequest request) {

		User user = new User();
		String username = CommonUtility.generateUsername(request.getEmail());
		user.setCreatedAt(new Date());
		user.setEmpId(request.getEmpId());
		user.setPassword(CommonUtility.randomPassowrd());
		user.setRoleId(request.getRoleId());
		user.setUsername(username);

		userDao.save(user);

		AuditLogs audit = new AuditLogs();
		audit.setAction("Registered New User " + username);
		audit.setCreatedTime(new Date());
		audit.setEmployeeId(getEMpId());

		auditRepo.save(audit);
		return "Success";
	}

	public Integer getEMpId() {
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		User findByUsername = userDao.findByUsername(username);
		return findByUsername.getEmpId();
	}

}
