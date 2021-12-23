package com.rest.Authentication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.rest.Services.UserDetailsServicesImpl;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	


	@Bean
    public UserDetailsServicesImpl userDetailsServicesImpl() {
        return new UserDetailsServicesImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    JwtAuthenticationFilter authenticationFilter() {
    	return new JwtAuthenticationFilter();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServicesImpl());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http .csrf().ignoringAntMatchers("/**");
         http.authorizeHttpRequests()
         .antMatchers("/rest/login").permitAll()
         .antMatchers("/test/new").permitAll()
         .anyRequest().authenticated().and()
         .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		 http.addFilterBefore(authenticationFilter(),UsernamePasswordAuthenticationFilter.class);
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 http.cors();
	}
    
    
}
