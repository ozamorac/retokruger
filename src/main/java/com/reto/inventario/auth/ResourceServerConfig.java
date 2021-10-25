package com.reto.inventario.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/empleados").hasAnyRole("ADMINISTRADOR")
		.antMatchers(HttpMethod.POST, "/api/empleados/crear").hasAnyRole("ADMINISTRADOR")
		.antMatchers(HttpMethod.PUT, "/api/empleados/{empleadoCodigo}").hasAnyRole("ADMINISTRADOR","EMPLEADO")
		.antMatchers(HttpMethod.DELETE, "/api/empleados/{empleadoCodigo}").hasAnyRole("ADMINISTRADOR")
		.antMatchers(HttpMethod.DELETE, "/").permitAll()
		.antMatchers(AUTH_WHITELIST).permitAll()
		.anyRequest().authenticated();
	}

	
}
