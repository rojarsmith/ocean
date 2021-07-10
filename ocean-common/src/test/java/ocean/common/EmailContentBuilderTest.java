package ocean.common;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ocean.common.service.EmailContentBuilder;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-10
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "classpath:service-spring.xml" })
public class EmailContentBuilderTest {
	@Autowired
	EmailContentBuilder emailContentBuilder;

	@Test
	public void commonTest() {
		Map<String, Object> data = new HashMap<>();
		data.put("subject", "This is test メール");
		data.put("title", "A TEST メール");
		data.put("message", "123456");
		Optional<String> context1 = emailContentBuilder.generateMailContent(data, "emailSimpleMessage");
		assertTrue(context1.isPresent());
		assertTrue(context1.get().contains("A TEST メール"));
	}
}
