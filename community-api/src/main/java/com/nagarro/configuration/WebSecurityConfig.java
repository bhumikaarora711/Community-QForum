package com.nagarro.configuration;
/*
 * @author Bhumika_arora 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nagarro.services.CustomUserDetailService;
/*
 * This class extends the WebSecurityConfigurerAdapter that enables both WebSecurity
 *  and HTTPSecurity to be customized.
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String STRING = "/**";
	private static final String POSTCOMMENT = "/postcomment";
	private static final String GETUSERSCOUNT = "/getuserscount";
	private static final String GETQUECOUNT = "/getquecount";
	private static final String SIGNUP = "/signup";
	private static final String TOKEN = "/token";

	@Autowired
	private CustomUserDetailService customUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()
			.cors()
			.disable()
			.authorizeRequests()
			.antMatchers(TOKEN).permitAll()
			.antMatchers(SIGNUP).permitAll()
			.antMatchers(GETQUECOUNT).permitAll()
			.antMatchers(GETUSERSCOUNT).permitAll()
			.antMatchers(POSTCOMMENT).permitAll()
			.antMatchers(HttpMethod.OPTIONS, STRING).permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling().authenticationEntryPoint(entryPoint);
		
		http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	

}
