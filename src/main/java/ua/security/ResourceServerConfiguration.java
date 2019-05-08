package ua.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "footballData_rest_api";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//http.anonymous().disable();
		http.csrf().disable();

		// The pages does not require login
		//http.requestMatchers().antMatchers("/api/competition/", "/api/competition/**").and().authorizeRequests(). permitAll();
		http.authorizeRequests().antMatchers("/api/competition/**").permitAll();
        // default protection for all resources (including /oauth/authorize)
		// .and().authorizeRequests().anyRequest().hasRole("USER")
		

		/*http.requestMatchers().antMatchers("/users/**").and().authorizeRequests().antMatchers("/users/**")
				.access("hasRole('ADMIN')").and().exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());*/

		// Config Remember Me.
		/*
		 * http.authorizeRequests().and() // .rememberMe()//
		 * .rememberMeCookieName("vetal-remember-me")//
		 * .tokenRepository(this.persistentTokenRepository()) // .tokenValiditySeconds(1
		 * * 24 * 60 * 60); // 24h
		 */
	}

}
