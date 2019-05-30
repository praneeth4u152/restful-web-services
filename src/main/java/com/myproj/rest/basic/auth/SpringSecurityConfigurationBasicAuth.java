package com.myproj.rest.basic.auth;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest()
				.authenticated().and()
				// .formLogin().and()
				.httpBasic();*/
		http
    	.cors().and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/wording", "/login").permitAll()
        .anyRequest().authenticated()
        .and().formLogin().loginProcessingUrl("/login")        
        .and().httpBasic()
        .and().sessionManagement().disable();
	}

	

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(
				Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD",
	            "GET", "POST", "PUT", "DELETE", "PATCH"));
		configuration.setExposedHeaders(Arrays.asList("x-auth-token", "x-requested-with", "x-xsrf-token"));
		configuration.setAllowCredentials(true);
		configuration
				.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
