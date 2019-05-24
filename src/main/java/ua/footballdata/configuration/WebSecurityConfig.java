package ua.footballdata.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
//@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

	@Autowired
	private DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		// The pages does not require login
		// http.authorizeRequests().antMatchers("/", "/login", "/api**").permitAll();

		// For all authorized
		http.authorizeRequests()
				.antMatchers("/user/**", "/logout", "/forgotPassword", "/resetPassword", "/competitions**")
				.authenticated().anyRequest().permitAll();

		// ROLE_ADMIN ROLE_MANAGER pages
		// http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_MANAGER',
		// 'ROLE_ADMIN')");

		// For ADMIN only.
		http.authorizeRequests().antMatchers("/users**", "/admin").access("hasRole('ROLE_ADMIN')").anyRequest()
				.authenticated();

		/*
		 * // All requests send to the Web Server request must be authenticated
		 * http.authorizeRequests().anyRequest().authenticated();
		 */
		// Use AuthenticationEntryPoint to authenticate user/password
		http.httpBasic().authenticationEntryPoint(authEntryPoint);

	}

	@Override
	// https://www.baeldung.com/security-none-filters-none-access-permitAll
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/api**");
	}
}
