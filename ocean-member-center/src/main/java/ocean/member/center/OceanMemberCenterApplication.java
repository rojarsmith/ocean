package ocean.member.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-18
 */
@SpringBootApplication
//@ComponentScan(basePackages = { "ocean.common", "ocean.member.center" })
//@EnableJpaRepositories(basePackages = { "ocean.common" })
//@EntityScan(basePackages = { "ocean.common" })
public class OceanMemberCenterApplication {
	
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(OceanMemberCenterApplication.class);
//    }
//	
	public static void main(String[] args) {
		SpringApplication.run(OceanMemberCenterApplication.class, args);
	}

}
