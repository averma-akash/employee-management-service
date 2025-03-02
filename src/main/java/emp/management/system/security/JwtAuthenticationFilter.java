package emp.management.system.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import emp.management.system.auth.service.UserService;
import emp.management.system.entity.User;
import emp.management.system.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JWTUtils jwtUtils;
	private final UserRepository userDao;
	private final UserService userService;

	public JwtAuthenticationFilter(JWTUtils jwtUtils, UserRepository userDao, UserService userService) {
		this.jwtUtils = jwtUtils;
		this.userDao = userDao;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if (null != jwt) {
				String username = jwtUtils.extractUsername(jwt);
				if (null != username && jwtUtils.validateJwtToken(jwt, username)) {
					Map<String, Object> claims = createClaims(username);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims,
							null, null);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String jwt = jwtUtils.getJwtFromCookies(request);
		return jwt;
	}

	public Map<String, Object> createClaims(String username) {

		User user = userDao.findByUsername(username);
		return userService.createClaims(user);
	}

}
