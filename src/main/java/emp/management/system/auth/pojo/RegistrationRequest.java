package emp.management.system.auth.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegistrationRequest {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	private BigDecimal salary;
	private Date hireDate;
	private Integer departmentId;
	private Integer managerId;
	private Integer roleId;
	
}
