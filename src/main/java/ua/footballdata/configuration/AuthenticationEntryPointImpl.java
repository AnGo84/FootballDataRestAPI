package ua.footballdata.configuration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPointImpl extends BasicAuthenticationEntryPoint {
	public static final Logger logger = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);
	private static final String FOOTBALL_DATA_API = "FootballDataAPI";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException, ServletException {
		logger.info("AuthenticationEntryPointImpl commence");
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 - " + authEx.getMessage());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("AuthenticationEntryPointImpl afterPropertiesSet");
		// RealmName appears in the login window (Firefox).
		setRealmName(FOOTBALL_DATA_API);
		super.afterPropertiesSet();
	}

}
