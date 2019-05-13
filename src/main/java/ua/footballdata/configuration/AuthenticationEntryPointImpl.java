package ua.footballdata.configuration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPointImpl extends BasicAuthenticationEntryPoint {
 
    private static final String FOOTBALL_DATA_API = "FootballDataAPI";

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());
    }
 
    @Override
    public void afterPropertiesSet() throws Exception {
        // RealmName appears in the login window (Firefox).
        setRealmName(FOOTBALL_DATA_API);
        super.afterPropertiesSet();
    }

}
