package ocean.authorization.init;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@Configuration
@ConfigurationProperties
@Data
public class ServiceProperty {

	String serviceOwnerUsername;

	Service service = new Service();

	@Data
	public static class Service {
		String ownerUsername;

		Owner owner = new Owner();

		@Data
		public static class Owner {
			String username;
			String password;
			String email;
		}
	}

}
