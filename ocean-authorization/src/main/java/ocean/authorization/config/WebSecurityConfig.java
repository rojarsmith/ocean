package ocean.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ocean.authorization.ServiceProperty;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ServiceProperty serviceProperty;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (serviceProperty.isTest()) {
			http.csrf().disable()
					//
					.httpBasic().and()
					//
					.cors().and()
					//
					.headers().frameOptions().disable().and()
					//
					.formLogin().disable()
					//
					.authorizeRequests()
					//
					.antMatchers("/oauth/authorize**").permitAll()
					//
					.antMatchers("/oauth/token").permitAll()
					//
					.antMatchers("/oauth/token_key").permitAll()
					//
					.antMatchers("/oauth/check_token").permitAll()
					//
					.anyRequest().authenticated();
		} else {

		}
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		if (serviceProperty.isTest()) {
			web.ignoring()
					//
					.antMatchers("/h2-console/**");
		}
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
	 * Certificate management.
	 *
	 * @return Certificate manager
	 * @throws Exception Error info
	 */
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
