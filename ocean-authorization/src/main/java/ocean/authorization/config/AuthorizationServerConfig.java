package ocean.authorization.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ocean.authorization.ServiceProperty;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	ServiceProperty serviceProperty;

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	private @NonNull AuthenticationManager authenticationManager;
	private final @NonNull DataSource dataSource;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetails());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager).tokenStore(jdbcTokenStore())
				//
				.tokenStore(tokenStore())
				//
				.accessTokenConverter(jwtAccessTokenConverter());
		;
	}

	@Bean
	public ClientDetailsService clientDetails() {
		return new JdbcClientDetailsService(dataSource);
	}

	@Bean
	public TokenStore jdbcTokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	/**
	 * Token converter.
	 * 
	 * Check key : keytool -list -rfc -keystore oauth2-dev.jks
	 *
	 * @return JwtAccessTokenConverter
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		Resource key = null;
		if (serviceProperty.isTest()) {
			key = resourceLoader
					.getResource("classpath:key/" + serviceProperty.getService().getOauth2().getKeyStore().getFile());
		} else {
			key = resourceLoader.getResource("file:" + serviceProperty.getSpring().getConfig().getAdditionalLocation()
					+ serviceProperty.getService().getOauth2().getKeyStore().getFile());
		}
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
				//
				key,
				//
				serviceProperty.getService().getOauth2().getKeyStore().getPassword().toCharArray());
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair(
				//
				serviceProperty.getService().getOauth2().getKeyStore().getPair()));
		return converter;
	}

	/**
	 * Token store.
	 *
	 * @return JwtTokenStore
	 */
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

}
