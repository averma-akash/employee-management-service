package emp.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emp.management.system.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

	Roles getById(Integer roleId);
}
