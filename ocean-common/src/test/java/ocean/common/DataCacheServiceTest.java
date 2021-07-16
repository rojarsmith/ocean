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

import ocean.common.service.DataCacheService;
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
	}

	@AfterAll
	static void stopRedis() {
		redisServer.stop();
	}

}
