package ocean.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@SpringBootApplication
@EnableConfigurationProperties
//@EnableAuthorizationServer
public class OceanAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceanAuthorizationApplication.class, args);
	}

}
