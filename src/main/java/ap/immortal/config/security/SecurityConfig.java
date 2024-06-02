package ap.immortal.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {	
		UserDetails amit = User.builder().username("amit").password("{noop}amit").roles("EMPLOYEE").build();
		UserDetails avi = User.builder().username("avi").password("{noop}avi").roles("MANAGER").build();
		UserDetails amar = User.builder().username("amar").password("{noop}amar").roles("ADMIN").build();					
		return new InMemoryUserDetailsManager(amar,amit,avi);
	}
	
	@Bean
	SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
		http.authorizeHttpRequests(configurer ->
				configurer
					.requestMatchers(HttpMethod.GET, "/v2/api/hello").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")					
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
