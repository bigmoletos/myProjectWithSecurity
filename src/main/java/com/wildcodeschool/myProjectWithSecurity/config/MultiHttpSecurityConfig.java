/**
 * 
 *
 */
package com.wildcodeschool.myProjectWithSecurity.config;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author franck Desmedt github/bigmoletos
 *
 */
//@EnableWebSecurity
public class MultiHttpSecurityConfig {
//	@Bean
//	public UserDetailsService userDetailsService() throws Exception {
//		// ensure the passwords are encoded properly
//		// UserBuilder users = User.withDefaultPasswordEncoder();//methode depreciée car
//		// non securisée
//
//		UserDetails users = User.withUsername("champion")
////				.password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG").roles("USER").build();
//				.password("motdepasse").roles("").build();
//		InMemoryUserDetailsManager champion = new InMemoryUserDetailsManager();
//
//		champion.createUser(users.username("user").password("123456").roles("SHIELD").build());
//		champion.createUser(users.username("admin").password("motdepasse").roles("CHAMPION", "ADMIN").build());
//		return champion;
//	}

//****************************
//	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

// outputs {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
// remember the password that is printed out and use in the next step

//	UserDetails user = User.witUsername("user")
//			.password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG").roles("USER").build();
//
//	Returns:
//	a UserBuilder
//	that automatically
//	encodes the
//	password with the default
//	PasswordEncoder
//	withUserDetails
//
//	public static User.UserBuilder withUserDetails(UserDetails userDetails)
//	

//****************************
//	@Configuration
//	// @Order(value=100)
//	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.antMatcher("/api/**")
//					.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().hasRole("ADMIN"))
//					.httpBasic();
//			// .httpBasic(withDefaults());
//		}
//	}
//
//	@Configuration
//	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated()).formLogin();
//			// .formLogin(withDefaults());
//		}
//	}
}
