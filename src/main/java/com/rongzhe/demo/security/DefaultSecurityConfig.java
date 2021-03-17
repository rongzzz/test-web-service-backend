package com.rongzhe.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rongzhe.demo.mappers.UserMapper;

@Configuration
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
				.accessDeniedHandler(new AccessDeniedHandlerImpl()).and()
				.addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()//
				.antMatchers("/admin/**").hasRole("ADMIN")//
				.antMatchers("/user/**").hasRole("USER")//
				.and().logout().logoutUrl("/logout").invalidateHttpSession(true)
				.logoutSuccessHandler(new LogoutSuccessHandlerImpl()).and().csrf().disable();
		// TODO
		// http.authorizeRequests().antMatchers("").hasRole("");
	}

	@Bean
	LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
		final LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandlerImpl());
		filter.setAuthenticationFailureHandler(new AuthenticationFailureHandlerImpl());
		filter.setFilterProcessesUrl("/login");
		return filter;
	}

}
