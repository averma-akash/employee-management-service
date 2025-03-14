package emp.management.system.response.pojo;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {

	private String message;
	private HttpStatus status;
	private Object responseData;
}
