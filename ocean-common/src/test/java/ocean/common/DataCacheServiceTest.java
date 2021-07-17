package ocean.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ocean.common.model.entity.Member;
import ocean.common.service.DataCacheService;
import ocean.common.util.JSON;
import redis.embedded.RedisServer;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-16
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "classpath:service-spring.xml" })
public class DataCacheServiceTest {

	@Autowired
	DataCacheService dataCacheService;

	static RedisServer redisServer;

	@BeforeAll
	static void startRedis() {
		redisServer = RedisServer.builder().port(16379).setting("maxmemory 8M").build();
		redisServer.start();
	}

	@Test
	public void commonTest() {
		dataCacheService.update("KEY1", "Rojar", 999);
		Optional<String> value1 = dataCacheService.readString("KEY1");
		assertEquals(value1.get().equals("Rojar"), true);
		dataCacheService.update("KEY2", "1", 999);
		dataCacheService.updateIncrement("KEY2", 10);
		Optional<String> value2 = dataCacheService.readString("KEY2");
		assertEquals(value2.get().equals("11"), true);
		dataCacheService.updateDecrement("KEY2", 6);
		value2 = dataCacheService.readString("KEY2");
		assertEquals(value2.get().equals("5"), true);
	}

	@Test
	public void storeObjectTest() {
		Member m1 = new Member();
		m1.setEmail("rojarsmith@gmail.com");
		m1.setUsername("Rojar");
		m1.setPassword("abc");
		Optional<String> json1 = JSON.stringify(m1);
		dataCacheService.update("KEY3", json1.get());
		Optional<String> json2 = dataCacheService.readString("KEY3");
		Optional<Member> m2 = JSON.parse(json2.get(), Member.class);
		assertEquals(m2.get().getUsername().equals("Rojar"), true);
	}

	@AfterAll
	static void stopRedis() {
		redisServer.stop();
	}

}
