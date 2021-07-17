package ocean.common.service.impl;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ocean.common.service.DataCacheService;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@Service
@Slf4j
public class DataCacheServiceImpl implements DataCacheService {

	@Autowired
	StringRedisTemplate redisTemplate;

	@Override
	public Optional<String> readString(String key) {
		try {
			ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			return Optional.ofNullable(valueOperations.get(key));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return Optional.empty();
		}
	}

	/**
	 * get expire time
	 *
	 * @param key index
	 * @return seconds, 0 will never expire.
	 */
	public Optional<Long> readExpire(String key) {
		try {
			return Optional.ofNullable(redisTemplate.getExpire(key, TimeUnit.SECONDS));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return Optional.empty();
		}
	}

	@Override
	public boolean update(String key, String value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(String key, String value, int seconds) {
		try {
			ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			valueOperations.set(key, value, seconds, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public Optional<Long> updateIncrement(String key, long delta) {
		try {
			ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			return Optional.ofNullable(valueOperations.increment(key, delta));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return Optional.empty();
		}
	}

	@Override
	public Optional<Long> updateDecrement(String key, long delta) {
		try {
			ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			return Optional.ofNullable(valueOperations.decrement(key, delta));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return Optional.empty();
		}
	}

	/**
	 * update expire time
	 *
	 * @param key  index
	 * @param time seconds
	 * @return
	 */
	public boolean updateExpire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(String key) {
		try {
			ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			return valueOperations.getOperations().delete(key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}

	/**
	 * if key exists
	 *
	 * @param key index
	 * @return true exists false not exists
	 */
	@Override
	public boolean isExists(String key) {
		try {
			ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			return valueOperations.getOperations().hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
	}

}
