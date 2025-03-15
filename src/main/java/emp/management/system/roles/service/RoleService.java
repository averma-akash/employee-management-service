package emp.management.system.roles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emp.management.system.entity.Roles;
import emp.management.system.repository.RolesRepository;
import jakarta.transaction.Transactional;

@Service
public class RoleService {

	@Autowired
	RolesRepository dao;

	@Transactional
	public List<Roles> getAllRoles() {
		return dao.findAll();
	}

	@Transactional
	public Roles getRoleById(Integer id) {

		return dao.findById(id).orElse(null);
	}

}
