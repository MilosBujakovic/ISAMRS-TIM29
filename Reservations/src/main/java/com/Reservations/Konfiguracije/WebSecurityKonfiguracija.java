//package com.Reservations.Konfiguracije;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityKonfiguracija extends WebSecurityConfigurerAdapter {
//	// PasswordEncoder implemented using BCrypt hashing function
//	// By default, BCrypt does 10 rounds of hashing
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Autowired
//	private CustomUserDetailsService jwtUserDetailsService;
//	
//	// Unauthorized access to protected resources
//	@Autowired
//	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//	
//
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	// Define way of authentication
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//	}
//
//	@Autowired
//	TokenUtils tokenUtils;
//	
//	// Define access rights for specific URLs
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			// client-server communication is stateless
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//			
//			// send 401 error for unauthorized requests
//			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
//			
//			.authorizeRequests()
//			.antMatchers("/login").permitAll()
//			.antMatchers("/register/**").permitAll()
//			.antMatchers("/brodovi/profil/**").permitAll()
//			.antMatchers("/vikendice/profil/**").permitAll()
//			.antMatchers("/instruktori/profil/**").permitAll()
//			
//			// every request must be authorized
//			.anyRequest().authenticated().and()
//			
//			// intercept every request via a filter
//			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);
//
//		http.csrf().disable();
//	}
//
//	// General app security
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// TokenAuthenticationFilter will ignore the following paths
//		web.ignoring().antMatchers(HttpMethod.POST, "/login");
//		web.ignoring().antMatchers(HttpMethod.POST, "/register/**");
//		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
//	}	
//}
//
