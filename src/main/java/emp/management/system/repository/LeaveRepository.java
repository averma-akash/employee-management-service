package emp.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emp.management.system.entity.LeaveRequest;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRequest, Integer> {

}
