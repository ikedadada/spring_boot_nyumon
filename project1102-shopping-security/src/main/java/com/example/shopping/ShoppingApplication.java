package com.example.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.shopping.enumeration.UserRole;

@SpringBootApplication
@EnableWebSecurity
public class ShoppingApplication {
	@Bean
	public UserDetailsService userDatailsService() {
		UserDetails tato = User.builder()
				.username("tato")
				.password("{noop}tato")
				.roles(UserRole.EMPLOYEE.name())
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password("{noop}admin")
				.roles(UserRole.MANAGER.name())
				.build();
		return new InMemoryUserDetailsManager(tato, admin);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth
						.requestMatchers(HttpMethod.GET, "/maintenance/product/**")
						.hasAnyRole(UserRole.MANAGER.name(), UserRole.EMPLOYEE.name())
						.requestMatchers("/maintenance/product/**").hasRole(UserRole.MANAGER.name())
						.requestMatchers("/maintenance/**").denyAll()
						.anyRequest().permitAll())
				.formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
					@Override
					public void customize(FormLoginConfigurer<HttpSecurity> login) {
						login.loginPage("/auth/login")
								.defaultSuccessUrl("/maintenance/product/display-list")
								.failureUrl("/auth/login?failure");
					}
				})
				.exceptionHandling(
						customizer -> customizer.accessDeniedPage("/auth/display-access-denied"))
				.logout(customizer -> customizer.logoutSuccessUrl("/auth/login?logout"));
		return http.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

}
