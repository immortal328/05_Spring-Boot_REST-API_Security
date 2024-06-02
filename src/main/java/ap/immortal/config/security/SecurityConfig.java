package ap.immortal.config.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	//Use JDBC Databse 
	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	
	//FilterChains which helper Spring, for what role which endpoints are allowed
	@Bean
	SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
		http.authorizeHttpRequests(configurer ->
				configurer
					.requestMatchers(HttpMethod.GET, "/v2/api/hello").hasAnyRole("MANAGER")					
					.requestMatchers(HttpMethod.GET, "/v2/api//employees").hasAnyRole("EMPLOYEE","MANAGER")
					.requestMatchers(HttpMethod.GET, "/v2/api//employees/**").hasAnyRole("EMPLOYEE","MANAGER")					
					.requestMatchers(HttpMethod.POST, "/v2/api/employees").hasRole("MANAGER")
					.requestMatchers(HttpMethod.PUT, "/v2/api/employees/**").hasAnyRole("MANAGER")					
					.requestMatchers(HttpMethod.DELETE, "/v2/api/employees").hasAnyRole("ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/v2/api/employees/**").hasAnyRole("ADMIN"));
		//user http Basic Authentication
		http.httpBasic(Customizer.withDefaults());
		
		//In General CSRF is not requaired for stateless REST APIs that user POST, PUT, DELETE or PATCH
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}

}
