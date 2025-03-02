package emp.management.system.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emp.management.system.auth.pojo.LoginRequest;
import emp.management.system.entity.User;
import emp.management.system.repository.UserRepository;

@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserRepository userDao;
	
	public User findUser(LoginRequest request) {
		
		User userDtls = userDao.findByUsername(request.getUsername());
		if(null !=userDtls && request.getPassword().equals(userDtls.getPassword())) {
			logger.info("User Authentication Successful!");
			return userDtls;
		} 
		return null;
	}
	
public Map<String, Object> createClaims(User user) {
		
	Map<String, Object> claims = new HashMap<>();
	claims.put("employeeId", user.getEmpId());
	claims.put("username", user.getUsername());
	claims.put("role", user.getRoleId());
	claims.put("creationDt", user.getCreatedAt());
	return claims;
	}

}
