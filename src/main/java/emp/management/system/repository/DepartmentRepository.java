package emp.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emp.management.system.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
