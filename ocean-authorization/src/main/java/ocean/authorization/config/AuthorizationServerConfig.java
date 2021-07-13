package ocean.authorization.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private @NonNull AuthenticationManager authenticationManager;
	private final @NonNull DataSource dataSource;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetails());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager).tokenStore(jdbcTokenStore());
	}

	@Bean
	public ClientDetailsService clientDetails() {
		return new JdbcClientDetailsService(dataSource);
	}

	@Bean
	public TokenStore jdbcTokenStore() {
		return new JdbcTokenStore(dataSource);
	}
}
