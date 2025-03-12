package emp.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emp.management.system.entity.AuditLogs;

@Repository
public interface AuditRepository extends JpaRepository<AuditLogs, Integer>{

}
