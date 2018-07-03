package org.hibernateBoard.security;

import org.hibernateBoard.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers(
						"/css/**",
						"/script/**",
						"/image/**",
						"/fonts/**",
						"/lib/**",
						"/images/**",
						"/webjars/**",
						"/**/favicon.ico",
						"/error");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			// .antMatchers("/user/**").authenticated()
			.antMatchers("/board/**").authenticated()
			.antMatchers("/ajax/**").permitAll()
			.antMatchers("/user/userForm").permitAll()
			// .antMatchers("/user/userCreate").permitAll()
			.antMatchers("/**").permitAll()
			.and().formLogin()
			.loginPage("/login/loginForm")
			.loginProcessingUrl("/login/loginAction")
			.defaultSuccessUrl("/")
	    	.failureUrl("/login/loginForm")
	    	.and()
	    	.logout();
		
		http.csrf().disable();
	}


}
