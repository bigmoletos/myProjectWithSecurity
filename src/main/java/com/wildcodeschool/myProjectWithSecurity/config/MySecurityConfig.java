/**
 * 
 *
 */
package com.wildcodeschool.myProjectWithSecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author franck Desmedt github/bigmoletos
 *
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
//	Mot de passe:
//	motdepasse
//	
//	champions:
//	Steve
//	Nick
//	flerken
//	
//	Rôles:
//	CHAMPION
//	DIRECTOR
//	ADMIN

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// encodeur de mot de passe obligatoire depuis spring5
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("Steve").password(encoder.encode("motdepasse")).roles("CHAMPION").and()
				.withUser("Nick").password(encoder.encode("flerken")).roles("DIRECTOR");

//		auth.inMemoryAuthentication().withUser("user").password(encoder.encode("")).roles("").and().withUser("admin")
//				.password(encoder.encode("12345678")).roles("ADMIN").and().withUser("steve")
//				.password(encoder.encode("motdepasse")).roles("CHAMPION").and().withUser("nick")
//				.password(encoder.encode("motdepasse")).roles("CHAMPION").and().withUser("flerken")
//				.password(encoder.encode("motdepasse")).roles("CHAMPION").and().withUser("director")
//				.password(encoder.encode("motdepasse")).roles("DIRECTOR");

		// System.out.println(encoder.encode("password"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/avengers/assemble*").hasRole("CHAMPION").antMatchers("/secret-bases/**")
//				.hasRole("DIRECTOR").antMatchers("/anonymous*").anonymous().antMatchers("/login*").permitAll()
//				.anyRequest().authenticated().and().formLogin()
////				.loginPage("/login.html")
//				.loginProcessingUrl("/login").defaultSuccessUrl("/index.html", true).failureUrl("/loginl?error=true")
//				// .failureHandler(authenticationFailureHandler())
//				.and().logout().logoutUrl("/perform_logout").deleteCookies("JSESSIONID");
		// .logoutSuccessHandler(logoutSuccessHandler());
//
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/avengers/assemble*")
				.access("hasRole( 'CHAMPION' )").antMatchers("/secret-bases/**").access("hasRole( 'DIRECTOR' )")
				.anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/").failureUrl("/login?error=true")
				.and().httpBasic();
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
