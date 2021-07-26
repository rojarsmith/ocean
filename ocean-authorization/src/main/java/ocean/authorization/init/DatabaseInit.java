package ocean.authorization.init;

import java.time.Instant;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ocean.authorization.ServiceProperty;
import ocean.common.enums.MemberStatus;
import ocean.common.model.entity.Member;
import ocean.common.service.MemberService;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
@Component
@Slf4j
@Profile({ "dev", "test" })
public class DatabaseInit implements ApplicationRunner {
	@Autowired
	ServiceProperty serviceProperty;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	DataSource dataSource;

	@Autowired
	MemberService memberService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Profiles: " + serviceProperty.getSpring().getProfiles().getActive());

		initDatabase();
	}

	void initDatabase() {
		if (serviceProperty.isDev()) {
			ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8",
					new ClassPathResource("sql/oauth2-postgresql.sql"));
			resourceDatabasePopulator.execute(dataSource);
		}

		if (serviceProperty.isDevOrTest()) {
			// Creating user's account

			List<Member> members = new ArrayList<>();
			String utcTimeOwner = "2021-12-21T12:26:11Z";
			Instant instantOwner = Instant.parse(utcTimeOwner);

			Member memberOwner = new Member();
			memberOwner.setUsername(serviceProperty.getService().getOwner().getUsername());
			memberOwner.setPassword(passwordEncoder.encode(serviceProperty.getService().getOwner().getPassword()));
			memberOwner.setEmail(serviceProperty.getService().getOwner().getEmail());
			memberOwner.setRegistTime(instantOwner);
			memberOwner.setMemberStatus(EnumSet.of(MemberStatus.REGISTERD, MemberStatus.VERIFIED_EMAIL));
//			memberOwner.setRoleList(Arrays.asList(roleAdmin));
			members.add(memberOwner);

			Member member = new Member();
			member.setUsername("aaa");
			member.setPassword(passwordEncoder.encode("11112222"));
			member.setEmail("aaa@ocean.com");
			member.setRegistTime(Instant.now());
//			member.setMemberLevel(EnumSet.of(MemberStatus.REGISTERD));
//			member.setRoleList(Arrays.asList(roleMember));
			members.add(member);

			for (int i = 1; i <= 100; i++) {
				Member memberGen1 = new Member();
				memberGen1.setUsername("user" + i);
				memberGen1.setPassword(passwordEncoder.encode("11112222"));
				memberGen1.setEmail("email" + i + "@ocean.com");
//				if (i <= 50) {
//					memberGen1.setRegistTime(Instant.now());
//					memberGen1.setMemberLevel(EnumSet.of(MemberStatus.REGISTERD));
//				} else {
//					memberGen1.setRegistTime(Instant.now().plusSeconds(43200));
//					memberGen1.setMemberLevel(EnumSet.of(MemberStatus.REGISTERD, MemberStatus.ILLEGAL));
//				}
//				memberGen1.setRoleList(Arrays.asList(roleMember));
				members.add(memberGen1);
			}

			memberService.update(members);
		}
	}

}
