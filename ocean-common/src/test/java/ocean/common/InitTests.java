package ocean.common;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ocean.common.model.entity.Member;
import ocean.common.service.MemberService;
import redis.embedded.RedisServer;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-14
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "classpath:service-spring.xml" })
public class InitTests {
	@Autowired
	MemberService memberService;

	
	@PostConstruct
	void init() {

		
		Member m1 = new Member();
		m1.setEmail("rojarsmith@gmail.com");
		m1.setUsername("Rojar");
		m1.setPassword("abc");
//		m1.setRoleList(Arrays.asList(role));
		memberService.update(m1);

		Member m2 = new Member();
		m2.setEmail("dev@aext.io");
		m2.setUsername("Dev かいはつ");
		m2.setPassword("abc");
//		m2.setRoleList(Arrays.asList(role));
		memberService.update(m2);
	}
}
