package emp.management.system.leavemngmnt.pojo;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LeaveRqst {
	
	@NotNull
	private String leaveType;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;

}
