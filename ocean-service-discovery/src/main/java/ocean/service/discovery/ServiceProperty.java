package ocean.service.discovery;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-26
 */
@Configuration
@ConfigurationProperties
@Data
public class ServiceProperty {

	Service service = new Service();

	Spring spring = new Spring();

	@Data
	public static class Service {

		Alarm alarm = new Alarm();

		@Data
		public static class Alarm {

			Email email = new Email();
			
			@Data
			public static class Email {

				boolean suppress;

			}

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
