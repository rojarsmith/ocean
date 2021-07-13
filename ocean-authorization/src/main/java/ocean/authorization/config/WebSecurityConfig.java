package ocean.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import ocean.authorization.init.ServiceProperty;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ServiceProperty serviceProperty;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (serviceProperty.isTest()) {
			http.csrf().disable()
			//
			.formLogin()
			.and()
					//
					.authorizeRequests()
					//
					.antMatchers("/oauth/authorize").permitAll()
					//
					.antMatchers("/oauth/token").permitAll()
					//
					.antMatchers("/oauth/token_key").permitAll()
					//
					.antMatchers("/oauth/check_token").permitAll()
					//
					.antMatchers("/h2-console").permitAll()
					//
					.anyRequest().authenticated();

		} else {

		}

//		if (serviceProperty.isTest()) {
//			http.headers().frameOptions().disable();
//			http.authorizeRequests()
//					//
//					.antMatchers("/h2-console").permitAll();
//		}

//		http.authorizeRequests().anyRequest().authenticated();
	}

	/**
	 * Must encrypt password in Spring 5.
	 *
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Create 3 user
	 *
	 * @return InMemoryUserDetailsManager
	 */
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("123").password(passwordEncoder().encode("1234")).authorities("ROLE_USER").build());
		manager.createUser(
				User.withUsername("user").password(passwordEncoder().encode("12345")).authorities("ROLE_USER").build());
		manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin"))
				.authorities("ROLE_ADMIN").build());
		return manager;
	}

	/**
	 * Certificate management.
	 *
	 * @return Certificate manager
	 * @throws Exception Error info
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
