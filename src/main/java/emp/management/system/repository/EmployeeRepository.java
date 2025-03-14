package emp.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import emp.management.system.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, PagingAndSortingRepository<Employee, Integer> {
	
	public boolean existsByEmail(String email); 

}
