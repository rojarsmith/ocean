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

	Service service = new Service();

	Spring spring = new Spring();

	@Data
	public static class Service {

		Owner owner = new Owner();

		@Data
		public static class Owner {
			String username;
			String password;
			String email;
		}
	}

	@Data
	public static class Spring {

		Profiles profiles = new Profiles();

		@Data
		public static class Profiles {
			String active;

		}

	}

	public boolean isDev() {
		if (spring.getProfiles().getActive().equals("dev")) {
			return true;
		}
		return false;
	}

	public boolean isTest() {
		if (spring.getProfiles().getActive().equals("test")) {
			return true;
		}
		return false;
	}

}
