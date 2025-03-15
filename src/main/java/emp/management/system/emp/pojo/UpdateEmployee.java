package emp.management.system.emp.pojo;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@NotNull
public class UpdateEmployee {
	
	private String email;
	private String phoneNo;
	private BigDecimal salary;
	private Integer departmentId;
	private Integer managerId;
	private Integer roleId;

}
