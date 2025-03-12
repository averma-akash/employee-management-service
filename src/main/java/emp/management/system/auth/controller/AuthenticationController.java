package emp.management.system.auth.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emp.management.system.auth.pojo.LoginRequest;
import emp.management.system.auth.pojo.RegistrationRequest;
import emp.management.system.auth.service.UserService;
import emp.management.system.entity.User;
import emp.management.system.response.pojo.ErrorResponse;
import emp.management.system.response.pojo.LoginResponse;
import emp.management.system.security.JWTUtils;
import emp.management.system.utils.CommonUtility;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
public class AuthenticationController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserService userService;
	@Autowired
	JWTUtils jwt;

	@PostMapping(value = "/login")
	public ResponseEntity<?> userLogin(@RequestBody @Valid LoginRequest request) throws ParseException {

		User usrDtl = userService.findUser(request);
		if (null == usrDtl) {
			logger.info("**** Incorrect User Details ! ****");
			return ResponseEntity.badRequest().body("**** Invalid Username or Password ****");
		}
		Map<String, Object> claims = userService.createClaims(usrDtl);

		ResponseCookie responseCookie = jwt.generateJwtToken(claims, request.getUsername());

		return ResponseEntity.ok().header("Set-Cookie", responseCookie.toString())
				.body(new LoginResponse(usrDtl.getUsername(), new Date()));

	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> userRegister(@RequestBody @Valid RegistrationRequest request) throws ParseException {

		List<String> roles = CommonUtility.getAssignedRoles();

		if (CollectionUtils.isEmpty(roles) || !roles.contains("ADMIN")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponse("Unauthorized Access", HttpStatus.UNAUTHORIZED));
		}

		return ResponseEntity.ok().body(userService.userRegister(request));
	}
}
