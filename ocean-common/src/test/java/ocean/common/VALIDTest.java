package ocean.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ocean.common.util.VALID;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
public class VALIDTest {

	@Test
	public void validateUserName() {
		assertTrue(VALID.validateUserName("asdfz_012349"));
		assertFalse(VALID.validateUserName("asdf1A$1zol"));
	}

	@Test
	public void validateEmail() {
		assertTrue(VALID.validateEmail("rojar@edt.com.tw"));
		assertFalse(VALID.validateEmail(" testmail@mail.com"));
		assertFalse(VALID.validateEmail("testm ail@mail.com"));
	}

	@Test
	public void validatePassword() {
		assertTrue(VALID.validatePassword("^@},hJu>[4Bo7TGX"));
		assertFalse(VALID.validatePassword("12345678 aA@"));
		assertFalse(VALID.validatePassword("12345678aA@ "));
		assertFalse(VALID.validatePassword(" 12345678aA@"));
	}

}
