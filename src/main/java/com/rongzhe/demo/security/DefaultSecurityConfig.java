package com.rongzhe.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO change to check database user data
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN").and().withUser("user")
				.password("123").roles("USER");
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		// TODO change
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
				.accessDeniedHandler(new AccessDeniedHandlerImpl()).and()
				.addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()//
				.antMatchers("/admin/**").hasRole("ADMIN")//
				.antMatchers("/user/**").hasRole("USER")//
				.and().logout().logoutUrl("/api/logout").invalidateHttpSession(true)
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
