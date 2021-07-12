package ocean.authorization;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-12
 */
public class PasswordTest {
	@Test
	public void commonTest() {
		System.out.println(new BCryptPasswordEncoder().encode("oauth2"));
		System.out.println(new BCryptPasswordEncoder().encode("ocean"));
		System.out.println(new BCryptPasswordEncoder().encode("12341234"));
	}
}
