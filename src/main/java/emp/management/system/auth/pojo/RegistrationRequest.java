package emp.management.system.auth.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegistrationRequest {

	private Integer empId;
	private String email;
	private Integer roleId;
	
}
