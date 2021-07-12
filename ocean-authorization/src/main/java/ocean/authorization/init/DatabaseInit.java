package ocean.authorization.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@Component
@Slf4j
@Profile("dev")
public class DatabaseInit implements ApplicationRunner {
	@Autowired
	ServiceProperty serviceProperty;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Profiles: " + serviceProperty.getSpring().getProfiles().getActive());
	}

}
