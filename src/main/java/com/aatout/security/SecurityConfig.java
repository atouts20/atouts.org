package com.aatout.security;

import com.aatout.security.JWTAuthenticationFilter;
import com.aatout.security.JWTAuthorizationFilter;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http.csrf().disable();

		//Pour deactiver la securit� par session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.formLogin();
		http.cors().configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.addAllowedOrigin("*");
				config.setAllowCredentials(true);
				return config;
			}
		});
		
		
		http.authorizeRequests()
		.antMatchers(
				"/forgot-password/**","/api/v1/sms/**",
				"/reset-password/**", "/pays/**", "/metier/**","/user-names/**").permitAll()
		.antMatchers("/login/**","/swagger-ui.html/**","/listEchanges/**", "/downloadFile/{fileId}/**", 
				"/register/**", "/confirm", "/detail-echange/{id}/**", "/detail-produit/{id}/**","/detail-service/{id}/**","/produits-page/**","/services-page/**", "/echanges-page", "/pays/*", "/metier/*").permitAll()
		.antMatchers("/",
				"/favicon.ico",
				"/**/*.png",
				"/**/*.gif",
				"/**/*.svg",
				"/**/*.jpg",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js")
		.permitAll()
		.antMatchers(AUTH_WHITELIST).permitAll().  // whitelist Swagger UI resources
		// ... here goes your custom security configuration
		antMatchers("/**").authenticated();
		// .antMatchers(HttpMethod.GET,"/listUtilisateurs/**").permitAll()
		// .antMatchers("/produits-page/**","/services-page/**","/echanges-page/**").permitAll()
		/*http.authorizeRequests().antMatchers("/login/**","/swagger-ui.html/**","/listEchanges/**", "/downloadFile/{fileId}/**", 
        		   "/register/**", "/confirm", "/confirm", "/produits-page","/services-page", "/echanges-page").permitAll();          
		 *///.antMatchers(HttpMethod.GET,"/listUtilisateurs/**","/export/**").hasAuthority("ADMIN")
		http.authorizeRequests().anyRequest().authenticated();           
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);



	}
	private static final String[] AUTH_WHITELIST = {
			// -- swagger ui
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**"
			// other public endpoints of your API may be appended to this array
	};

}
