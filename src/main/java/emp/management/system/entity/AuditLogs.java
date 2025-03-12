package emp.management.system.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "AUDIT_LOGS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuditLogs implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "AUDIT_LOG_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "EMPLOYEE_ID")
	private Integer employeeId;

	@Column(name = "ACTION")
	private String action;

	@Column(name = "TIMESTAMP")
	private Date createdTime;
}
