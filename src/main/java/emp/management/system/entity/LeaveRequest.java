package emp.management.system.entity;

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

@Entity(name = "LEAVE_REQUESTS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LeaveRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="LEAVE_REQUEST_SEQ", allocationSize=1)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "EMPLOYEE_ID")
	private Integer employeeId;
	@Column(name = "LEAVE_TYPE")
	private String leaveType;
	@Column(name = "START_DATE")
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "CREATED_AT")
	private Date createdAt;

}
