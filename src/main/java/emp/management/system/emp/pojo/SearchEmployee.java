package emp.management.system.emp.pojo;

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
public class SearchEmployee {
	
	private String email;
	private Integer departmentId;
	private Integer managerId;
	private String firstName;
	private String lastName;
	private Date hireDate;

}
