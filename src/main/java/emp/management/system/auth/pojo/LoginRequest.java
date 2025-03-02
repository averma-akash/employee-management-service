package emp.management.system.auth.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull
public class LoginRequest {
	
	private String username;
	private String password;

}
