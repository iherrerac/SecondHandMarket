package com.secondhandmarket.spring.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SeguridadConfig{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(userDetailsService)
	      .passwordEncoder(passwordEncoder())
	      .and()
	      .build();
	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	
	
	//Configuracion de seguridad HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
          	.authorizeRequests()
          	.antMatchers("/", "/webjars/**", "/css/**", "/public/**", "/auth/**", "/files/**").permitAll()
          	.anyRequest().authenticated()
          
          
//	        .antMatchers(HttpMethod.DELETE)
//	        .hasRole("ADMIN")
//	        .antMatchers("/admin/**")
//	        .hasAnyRole("ADMIN")
//	        .antMatchers("/user/**")
//	        .hasAnyRole("USER", "ADMIN")
//	        .antMatchers("/login/**")
//	        .anonymous()
//          	.and()
//        	.httpBasic()
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
          	
        .formLogin()
        	.loginPage("/auth/login")
        	.defaultSuccessUrl("/public/index")
        	.loginProcessingUrl("/auth/login-post")//Controlador para manejar el login en vez de dejar un mapeo directo
        	.permitAll()
        	.and()
        .logout()
        	.logoutUrl("/auth/logout")
        	.logoutSuccessUrl("/public/index");
    	
    	//Consola H2, no se si necesario para Postgress
    	http
        .csrf().disable()
        .headers().frameOptions().disable();
    	


        return http.build();
    }

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//            .setType(EmbeddedDatabaseType.H2)
//            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//            .build();
//    }
//
//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        return users;
//    }
}
