package ocean.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ocean.common.model.entity.Member;
import ocean.common.util.JSON;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-16
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "classpath:service-spring.xml" })
public class JSONTest {
	@Test
	public void commonTest() {
		Member m1 = new Member();
		m1.setEmail("rojarsmith@gmail.com");
		m1.setUsername("Rojar");
		m1.setPassword("abc");
		Optional<String> json1 = JSON.stringify(m1);
		Optional<Member> m2 = JSON.parse(json1.get(), Member.class);
		assertEquals(m2.get().getUsername().equals("Rojar"), true);
	}
}
