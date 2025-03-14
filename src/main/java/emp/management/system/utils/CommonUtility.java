package emp.management.system.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import emp.management.system.emp.pojo.AddEmployee;
import emp.management.system.entity.Employee;
import emp.management.system.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;

public class CommonUtility {
	
	@Autowired
	UserRepository userDao;

	public static List<String> getAssignedRoles() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<String> roles = new ArrayList<>();
		String role = null;
		if (authentication != null && authentication.getPrincipal() instanceof Claims) {
			Claims claims = (Claims) authentication.getPrincipal();
			role = claims.get("role", String.class);
			roles.add(role);
		}
		if (authentication.getAuthorities() != null) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			authorities.forEach(e -> roles.add(e.getAuthority()));
		}
		return roles;
	}
	
	public static Employee setEmployee(@Valid AddEmployee request) {
		
		Employee emp = new Employee();
		emp.setCreatedDate(new Date());
		emp.setDepartmentId(request.getDepartmentId());
		emp.setEmail(request.getEmail());
		emp.setFirstName(request.getFirstName());
		emp.setHireDate(request.getHireDate());
		emp.setLastName(request.getLastName());
		emp.setManagerId(request.getManagerId());
		emp.setPhoneNo(request.getPhoneNo());
		emp.setRoleId(request.getRoleId());
		emp.setSalary(request.getSalary());
		return emp;
		
	}
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$@_";
	
	public static String randomPassowrd() {
	    StringBuilder sb = new StringBuilder(8);
	    SecureRandom random = new SecureRandom();
	    for (int i = 0; i < 8; i++) {
	        int index = random.nextInt(CHARACTERS.length());
	        sb.append(CHARACTERS.charAt(index));
	    }
	    return sb.toString();
	}

	public static String generateUsername(String email) {
		
		String[] username = email.split("@");
		return username[0].toString();
		
	}

}
