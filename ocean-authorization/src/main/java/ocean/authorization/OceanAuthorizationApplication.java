package ocean.authorization;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableAuthorizationServer
@EnableResourceServer
@ComponentScan(basePackages = { "ocean.common", "ocean.authorization" })
@EnableJpaRepositories(basePackages = { "ocean.common" })
@EntityScan(basePackages = { "ocean.common" })
public class OceanAuthorizationApplication {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(OceanAuthorizationApplication.class, args);
	}

}
