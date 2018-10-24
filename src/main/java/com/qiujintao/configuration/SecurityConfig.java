package com.qiujintao.configuration;

import javax.sql.DataSource;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.qiujintao.handler.RefererRedirectionAuthenticationSuccessHandler;
import com.qiujintao.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private MyUserDetailsService MyUserDetailsService; 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(MyUserDetailsService).passwordEncoder(passwordEncoder());
	}
	@Value("${server.http.port}")
	private int httpPort;
	@Value("${server.port}")
	private int httpsPort;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		.csrf()
//		.disable()
		.requiresChannel().anyRequest().requiresSecure()
		.and()
        .portMapper()               
        .http(httpPort).mapsTo(httpsPort)
		.and()
		.authorizeRequests().antMatchers("/new-post").authenticated()
//		.antMatchers("/admin/").hasAuthority("ADMIN")
		.antMatchers("/**").permitAll()
//		.and().requiresChannel().antMatchers("/login").requiresInsecure()
		.and()
		.formLogin().loginPage("/login")
		.usernameParameter("email")
		.passwordParameter("password")
		.successHandler(new RefererRedirectionAuthenticationSuccessHandler("/"))
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/access-denied");
		
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web
//	       .ignoring()
//	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//	}


}
