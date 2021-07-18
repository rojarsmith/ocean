package ocean.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@SpringBootApplication
//@ComponentScan(basePackages = { "ocean.common", "ocean.gateway" })
//@EntityScan(basePackages = { "ocean.common" })
public class OceanGatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OceanGatewayApplication.class, args);
	}

}
