package ocean.authorization;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-15
 */
@Configuration
@ConfigurationProperties
@Data
public class ServiceProperty {

	Service service = new Service();

	Spring spring = new Spring();

	@Data
	public static class Service {

		Oauth2 oauth2 = new Oauth2();
		
		Owner owner = new Owner();

		@Data
		public static class Oauth2 {
			KeyStore keyStore = new KeyStore();
			
			@Data
			public static class KeyStore {
				String file;
				String password;
				String pair;
			}
		}
		
		@Data
		public static class Owner {
			String username;
			String password;
			String email;
		}
	}

	@Data
	public static class Spring {

		Config config = new Config();

		Profiles profiles = new Profiles();

		@Data
		public static class Config {
			String additionalLocation;

		}

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

	public boolean isDevOrTest() {
		if (isDev() || isTest()) {
			return true;
		}
		return false;
	}
}
